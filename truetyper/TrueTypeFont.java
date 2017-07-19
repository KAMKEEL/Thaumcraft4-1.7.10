/*     */ package truetyper;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.Raster;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.glu.GLU;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TrueTypeFont
/*     */ {
/*     */   public static final int ALIGN_LEFT = 0;
/*     */   public static final int ALIGN_RIGHT = 1;
/*     */   public static final int ALIGN_CENTER = 2;
/*  41 */   private FloatObject[] charArray = new FloatObject['Ā'];
/*     */   
/*     */ 
/*  44 */   private Map customChars = new HashMap();
/*     */   
/*     */ 
/*     */   protected boolean antiAlias;
/*     */   
/*     */ 
/*  50 */   private float fontSize = 0.0F;
/*     */   
/*     */ 
/*  53 */   private float fontHeight = 0.0F;
/*     */   
/*     */ 
/*     */   private int fontTextureID;
/*     */   
/*     */ 
/*  59 */   private int textureWidth = 1024;
/*     */   
/*     */ 
/*  62 */   private int textureHeight = 1024;
/*     */   
/*     */ 
/*     */   protected Font font;
/*     */   
/*     */ 
/*     */   private FontMetrics fontMetrics;
/*     */   
/*     */ 
/*  71 */   private int correctL = 9; private int correctR = 8;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TrueTypeFont(Font font, boolean antiAlias, char[] additionalChars)
/*     */   {
/*  89 */     this.font = font;
/*  90 */     this.fontSize = (font.getSize() + 3);
/*  91 */     this.antiAlias = antiAlias;
/*     */     
/*  93 */     createSet(additionalChars);
/*  94 */     System.out.println("TrueTypeFont loaded: " + font + " - AntiAlias = " + antiAlias);
/*  95 */     this.fontHeight -= 1.0F;
/*  96 */     if (this.fontHeight <= 0.0F) { this.fontHeight = 1.0F;
/*     */     }
/*     */   }
/*     */   
/* 100 */   public TrueTypeFont(Font font, boolean antiAlias) { this(font, antiAlias, null); }
/*     */   
/*     */   public void setCorrection(boolean on) {
/* 103 */     if (on) {
/* 104 */       this.correctL = 2;
/* 105 */       this.correctR = 1;
/*     */     } else {
/* 107 */       this.correctL = 0;
/* 108 */       this.correctR = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   private BufferedImage getFontImage(char ch)
/*     */   {
/* 114 */     BufferedImage tempfontImage = new BufferedImage(1, 1, 2);
/*     */     
/* 116 */     Graphics2D g = (Graphics2D)tempfontImage.getGraphics();
/* 117 */     if (this.antiAlias == true) {
/* 118 */       g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     }
/*     */     
/* 121 */     g.setFont(this.font);
/* 122 */     this.fontMetrics = g.getFontMetrics();
/* 123 */     float charwidth = this.fontMetrics.charWidth(ch) + 8;
/*     */     
/* 125 */     if (charwidth <= 0.0F) {
/* 126 */       charwidth = 7.0F;
/*     */     }
/* 128 */     float charheight = this.fontMetrics.getHeight() + 3;
/* 129 */     if (charheight <= 0.0F) {
/* 130 */       charheight = this.fontSize;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 135 */     BufferedImage fontImage = new BufferedImage((int)charwidth, (int)charheight, 2);
/*     */     
/* 137 */     Graphics2D gt = (Graphics2D)fontImage.getGraphics();
/* 138 */     if (this.antiAlias == true) {
/* 139 */       gt.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     }
/*     */     
/* 142 */     gt.setFont(this.font);
/*     */     
/* 144 */     gt.setColor(Color.WHITE);
/* 145 */     int charx = 3;
/* 146 */     int chary = 1;
/* 147 */     gt.drawString(String.valueOf(ch), charx, chary + this.fontMetrics.getAscent());
/*     */     
/*     */ 
/* 150 */     return fontImage;
/*     */   }
/*     */   
/*     */ 
/*     */   private void createSet(char[] customCharsArray)
/*     */   {
/* 156 */     if ((customCharsArray != null) && (customCharsArray.length > 0)) {
/* 157 */       this.textureWidth *= 2;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 166 */       BufferedImage imgTemp = new BufferedImage(this.textureWidth, this.textureHeight, 2);
/* 167 */       Graphics2D g = (Graphics2D)imgTemp.getGraphics();
/*     */       
/* 169 */       g.setColor(new Color(0, 0, 0, 1));
/* 170 */       g.fillRect(0, 0, this.textureWidth, this.textureHeight);
/*     */       
/* 172 */       float rowHeight = 0.0F;
/* 173 */       float positionX = 0.0F;
/* 174 */       float positionY = 0.0F;
/*     */       
/* 176 */       int customCharsLength = customCharsArray != null ? customCharsArray.length : 0;
/*     */       
/* 178 */       for (int i = 0; i < 256 + customCharsLength; i++)
/*     */       {
/*     */ 
/* 181 */         char ch = i < 256 ? (char)i : customCharsArray[(i - 256)];
/*     */         
/* 183 */         BufferedImage fontImage = getFontImage(ch);
/*     */         
/* 185 */         FloatObject newIntObject = new FloatObject(null);
/*     */         
/* 187 */         newIntObject.width = fontImage.getWidth();
/* 188 */         newIntObject.height = fontImage.getHeight();
/*     */         
/* 190 */         if (positionX + newIntObject.width >= this.textureWidth) {
/* 191 */           positionX = 0.0F;
/* 192 */           positionY += rowHeight;
/* 193 */           rowHeight = 0.0F;
/*     */         }
/*     */         
/* 196 */         newIntObject.storedX = positionX;
/* 197 */         newIntObject.storedY = positionY;
/*     */         
/* 199 */         if (newIntObject.height > this.fontHeight) {
/* 200 */           this.fontHeight = newIntObject.height;
/*     */         }
/*     */         
/* 203 */         if (newIntObject.height > rowHeight) {
/* 204 */           rowHeight = newIntObject.height;
/*     */         }
/*     */         
/*     */ 
/* 208 */         g.drawImage(fontImage, (int)positionX, (int)positionY, null);
/*     */         
/* 210 */         positionX += newIntObject.width;
/*     */         
/* 212 */         if (i < 256) {
/* 213 */           this.charArray[i] = newIntObject;
/*     */         } else {
/* 215 */           this.customChars.put(new Character(ch), newIntObject);
/*     */         }
/*     */         
/* 218 */         fontImage = null;
/*     */       }
/*     */       
/* 221 */       this.fontTextureID = loadImage(imgTemp);
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */ 
/* 228 */       System.err.println("Failed to create font.");
/* 229 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawQuad(float drawX, float drawY, float drawX2, float drawY2, float srcX, float srcY, float srcX2, float srcY2)
/*     */   {
/* 235 */     float DrawWidth = drawX2 - drawX;
/* 236 */     float DrawHeight = drawY2 - drawY;
/* 237 */     float TextureSrcX = srcX / this.textureWidth;
/* 238 */     float TextureSrcY = srcY / this.textureHeight;
/* 239 */     float SrcWidth = srcX2 - srcX;
/* 240 */     float SrcHeight = srcY2 - srcY;
/* 241 */     float RenderWidth = SrcWidth / this.textureWidth;
/* 242 */     float RenderHeight = SrcHeight / this.textureHeight;
/* 243 */     Tessellator t = Tessellator.field_78398_a;
/*     */     
/*     */ 
/*     */ 
/* 247 */     t.func_78374_a(drawX, drawY, 0.0D, TextureSrcX, TextureSrcY);
/*     */     
/*     */ 
/*     */ 
/* 251 */     t.func_78374_a(drawX, drawY + DrawHeight, 0.0D, TextureSrcX, TextureSrcY + RenderHeight);
/*     */     
/*     */ 
/*     */ 
/* 255 */     t.func_78374_a(drawX + DrawWidth, drawY + DrawHeight, 0.0D, TextureSrcX + RenderWidth, TextureSrcY + RenderHeight);
/*     */     
/*     */ 
/*     */ 
/* 259 */     t.func_78374_a(drawX + DrawWidth, drawY, 0.0D, TextureSrcX + RenderWidth, TextureSrcY);
/*     */   }
/*     */   
/*     */ 
/*     */   public float getWidth(String whatchars)
/*     */   {
/* 265 */     float totalwidth = 0.0F;
/* 266 */     FloatObject floatObject = null;
/* 267 */     int currentChar = 0;
/* 268 */     float lastWidth = -10.0F;
/* 269 */     for (int i = 0; i < whatchars.length(); i++) {
/* 270 */       currentChar = whatchars.charAt(i);
/* 271 */       if (currentChar < 256) {
/* 272 */         floatObject = this.charArray[currentChar];
/*     */       } else {
/* 274 */         floatObject = (FloatObject)this.customChars.get(new Character((char)currentChar));
/*     */       }
/*     */       
/* 277 */       if (floatObject != null) {
/* 278 */         totalwidth += floatObject.width / 2.0F;
/* 279 */         lastWidth = floatObject.width;
/*     */       }
/*     */     }
/*     */     
/* 283 */     return this.fontMetrics.stringWidth(whatchars);
/*     */   }
/*     */   
/*     */   public float getHeight()
/*     */   {
/* 288 */     return this.fontHeight;
/*     */   }
/*     */   
/*     */   public float getHeight(String HeightString)
/*     */   {
/* 293 */     return this.fontHeight;
/*     */   }
/*     */   
/*     */   public float getLineHeight() {
/* 297 */     return this.fontHeight;
/*     */   }
/*     */   
/*     */   public void drawString(float x, float y, String whatchars, float scaleX, float scaleY, float... rgba) {
/* 301 */     if (rgba.length == 0) rgba = new float[] { 1.0F, 1.0F, 1.0F, 1.0F };
/* 302 */     drawString(x, y, whatchars, 0, whatchars.length() - 1, scaleX, scaleY, 0, rgba);
/*     */   }
/*     */   
/* 305 */   public void drawString(float x, float y, String whatchars, float scaleX, float scaleY, int format, float... rgba) { if (rgba.length == 0) { rgba = new float[] { 1.0F, 1.0F, 1.0F, 1.0F };
/*     */     }
/* 307 */     drawString(x, y, whatchars, 0, whatchars.length() - 1, scaleX, scaleY, format, rgba);
/*     */   }
/*     */   
/*     */   public void drawString(float x, float y, String whatchars, int startIndex, int endIndex, float scaleX, float scaleY, int format, float... rgba)
/*     */   {
/* 312 */     if (rgba.length == 0) rgba = new float[] { 1.0F, 1.0F, 1.0F, 1.0F };
/* 313 */     GL11.glPushMatrix();
/* 314 */     GL11.glScalef(scaleX, scaleY, 1.0F);
/*     */     
/* 316 */     FloatObject floatObject = null;
/*     */     
/*     */ 
/*     */ 
/* 320 */     float totalwidth = 0.0F;
/* 321 */     int i = startIndex;
/* 322 */     float startY = 0.0F;
/*     */     
/*     */     int d;
/*     */     int c;
/* 326 */     switch (format) {
/*     */     case 1: 
/* 328 */       d = -1;
/* 329 */       c = this.correctR;
/*     */     }
/* 331 */     while (i < endIndex) {
/* 332 */       if (whatchars.charAt(i) == '\n') startY -= this.fontHeight;
/* 333 */       i++; continue;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 338 */       for (int l = startIndex; l <= endIndex; l++) {
/* 339 */         int charCurrent = whatchars.charAt(l);
/* 340 */         if (charCurrent == 10) break;
/* 341 */         if (charCurrent < 256) {
/* 342 */           floatObject = this.charArray[charCurrent];
/*     */         } else {
/* 344 */           floatObject = (FloatObject)this.customChars.get(new Character((char)charCurrent));
/*     */         }
/* 346 */         totalwidth += floatObject.width - this.correctL;
/*     */       }
/* 348 */       totalwidth /= -2.0F;
/*     */       
/*     */ 
/*     */ 
/* 352 */       d = 1;
/* 353 */       c = this.correctL;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 358 */     GL11.glBindTexture(3553, this.fontTextureID);
/* 359 */     Tessellator t = Tessellator.field_78398_a;
/* 360 */     t.func_78382_b();
/*     */     
/* 362 */     if (rgba.length == 4)
/* 363 */       t.func_78369_a(rgba[0], rgba[1], rgba[2], rgba[3]);
/* 364 */     while ((i >= startIndex) && (i <= endIndex))
/*     */     {
/* 366 */       int charCurrent = whatchars.charAt(i);
/* 367 */       if (charCurrent < 256) {
/* 368 */         floatObject = this.charArray[charCurrent];
/*     */       } else {
/* 370 */         floatObject = (FloatObject)this.customChars.get(new Character((char)charCurrent));
/*     */       }
/*     */       
/* 373 */       if (floatObject != null) {
/* 374 */         if (d < 0) totalwidth += (floatObject.width - c) * d;
/* 375 */         if (charCurrent == 10) {
/* 376 */           startY -= this.fontHeight * d;
/* 377 */           totalwidth = 0.0F;
/* 378 */           if (format == 2) {
/* 379 */             for (int l = i + 1; l <= endIndex; l++) {
/* 380 */               charCurrent = whatchars.charAt(l);
/* 381 */               if (charCurrent == 10) break;
/* 382 */               if (charCurrent < 256) {
/* 383 */                 floatObject = this.charArray[charCurrent];
/*     */               } else {
/* 385 */                 floatObject = (FloatObject)this.customChars.get(new Character((char)charCurrent));
/*     */               }
/* 387 */               totalwidth += floatObject.width - this.correctL;
/*     */             }
/* 389 */             totalwidth /= -2.0F;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 394 */           drawQuad(totalwidth + floatObject.width + x / scaleX, startY + y / scaleY, totalwidth + x / scaleX, startY + floatObject.height + y / scaleY, floatObject.storedX + floatObject.width, floatObject.storedY + floatObject.height, floatObject.storedX, floatObject.storedY);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 402 */           if (d > 0) totalwidth += (floatObject.width - c) * d;
/*     */         }
/* 404 */         i += d;
/*     */       }
/*     */     }
/*     */     
/* 408 */     t.func_78381_a();
/*     */     
/*     */ 
/* 411 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static int loadImage(BufferedImage bufferedImage) {
/* 415 */     try { short width = (short)bufferedImage.getWidth();
/* 416 */       short height = (short)bufferedImage.getHeight();
/*     */       
/* 418 */       int bpp = (byte)bufferedImage.getColorModel().getPixelSize();
/*     */       
/* 420 */       DataBuffer db = bufferedImage.getData().getDataBuffer();
/* 421 */       ByteBuffer byteBuffer; ByteBuffer byteBuffer; if ((db instanceof DataBufferInt)) {
/* 422 */         int[] intI = ((DataBufferInt)bufferedImage.getData().getDataBuffer()).getData();
/* 423 */         byte[] newI = new byte[intI.length * 4];
/* 424 */         for (int i = 0; i < intI.length; i++) {
/* 425 */           byte[] b = intToByteArray(intI[i]);
/* 426 */           int newIndex = i * 4;
/*     */           
/* 428 */           newI[newIndex] = b[1];
/* 429 */           newI[(newIndex + 1)] = b[2];
/* 430 */           newI[(newIndex + 2)] = b[3];
/* 431 */           newI[(newIndex + 3)] = b[0];
/*     */         }
/*     */         
/* 434 */         byteBuffer = ByteBuffer.allocateDirect(width * height * (bpp / 8)).order(ByteOrder.nativeOrder()).put(newI);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 439 */         byteBuffer = ByteBuffer.allocateDirect(width * height * (bpp / 8)).order(ByteOrder.nativeOrder()).put(((DataBufferByte)bufferedImage.getData().getDataBuffer()).getData());
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 444 */       byteBuffer.flip();
/*     */       
/*     */ 
/* 447 */       int internalFormat = 32856;
/* 448 */       int format = 6408;
/* 449 */       IntBuffer textureId = BufferUtils.createIntBuffer(1);
/* 450 */       GL11.glGenTextures(textureId);
/* 451 */       GL11.glBindTexture(3553, textureId.get(0));
/*     */       
/* 453 */       GL11.glTexParameteri(3553, 10242, 10496);
/* 454 */       GL11.glTexParameteri(3553, 10243, 10496);
/*     */       
/*     */ 
/* 457 */       GL11.glTexParameteri(3553, 10240, 9728);
/* 458 */       GL11.glTexParameteri(3553, 10241, 9728);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 466 */       GL11.glTexEnvf(8960, 8704, 8448.0F);
/*     */       
/* 468 */       GLU.gluBuild2DMipmaps(3553, internalFormat, width, height, format, 5121, byteBuffer);
/* 469 */       return textureId.get(0);
/*     */     }
/*     */     catch (Exception e) {
/* 472 */       e.printStackTrace();
/* 473 */       System.exit(-1);
/*     */     }
/*     */     
/* 476 */     return -1;
/*     */   }
/*     */   
/* 479 */   public static boolean isSupported(String fontname) { Font[] font = getFonts();
/* 480 */     for (int i = font.length - 1; i >= 0; i--) {
/* 481 */       if (font[i].getName().equalsIgnoreCase(fontname))
/* 482 */         return true;
/*     */     }
/* 484 */     return false;
/*     */   }
/*     */   
/* 487 */   public static Font[] getFonts() { return GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts(); }
/*     */   
/*     */   public static byte[] intToByteArray(int value) {
/* 490 */     return new byte[] { (byte)(value >>> 24), (byte)(value >>> 16), (byte)(value >>> 8), (byte)value };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void destroy()
/*     */   {
/* 498 */     IntBuffer scratch = BufferUtils.createIntBuffer(1);
/* 499 */     scratch.put(0, this.fontTextureID);
/* 500 */     GL11.glBindTexture(3553, 0);
/* 501 */     GL11.glDeleteTextures(scratch);
/*     */   }
/*     */   
/*     */   private class FloatObject
/*     */   {
/*     */     public float width;
/*     */     public float height;
/*     */     public float storedX;
/*     */     public float storedY;
/*     */     
/*     */     private FloatObject() {}
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/truetyper/TrueTypeFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */