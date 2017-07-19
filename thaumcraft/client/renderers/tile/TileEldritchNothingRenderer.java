/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEldritchNothingRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   FloatBuffer fBuffer;
/*     */   private boolean inrange;
/*     */   
/*     */   public TileEldritchNothingRenderer()
/*     */   {
/*  29 */     this.fBuffer = GLAllocation.func_74529_h(16);
/*     */   }
/*     */   
/*  32 */   private String t1 = "textures/misc/tunnel.png";
/*  33 */   private String t2 = "textures/misc/particlefield.png";
/*  34 */   private String t3 = "textures/misc/particlefield32.png";
/*     */   
/*     */   public void func_147500_a(TileEntity te, double x, double y, double z, float f)
/*     */   {
/*  38 */     this.inrange = (Minecraft.func_71410_x().field_71451_h.func_70092_e(te.field_145851_c + 0.5D, te.field_145848_d + 0.5D, te.field_145849_e + 0.5D) < 512.0D);
/*  39 */     GL11.glDisable(2912);
/*  40 */     if (!te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d + 1, te.field_145849_e).func_149662_c()) {
/*  41 */       drawPlaneYNeg(x, y, z, f);
/*     */     }
/*  43 */     if (!te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d - 1, te.field_145849_e).func_149662_c()) {
/*  44 */       drawPlaneYPos(x, y, z, f);
/*     */     }
/*  46 */     if (!te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e - 1).func_149662_c()) {
/*  47 */       drawPlaneZPos(x, y, z, f);
/*     */     }
/*  49 */     if (!te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e + 1).func_149662_c()) {
/*  50 */       drawPlaneZNeg(x, y, z, f);
/*     */     }
/*  52 */     if (!te.func_145831_w().func_147439_a(te.field_145851_c - 1, te.field_145848_d, te.field_145849_e).func_149662_c()) {
/*  53 */       drawPlaneXPos(x, y, z, f);
/*     */     }
/*  55 */     if (!te.func_145831_w().func_147439_a(te.field_145851_c + 1, te.field_145848_d, te.field_145849_e).func_149662_c()) {
/*  56 */       drawPlaneXNeg(x, y, z, f);
/*     */     }
/*  58 */     GL11.glEnable(2912);
/*     */   }
/*     */   
/*     */   public void drawPlaneYPos(double x, double y, double z, float f)
/*     */   {
/*  63 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/*  64 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/*  65 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/*  66 */     GL11.glDisable(2896);
/*  67 */     Random random = new Random(31100L);
/*  68 */     float offset = 0.0F;
/*  69 */     if (this.inrange) {
/*  70 */       for (int i = 0; i < 16; i++)
/*     */       {
/*  72 */         GL11.glPushMatrix();
/*  73 */         float f5 = 16 - i;
/*  74 */         float f6 = 0.0625F;
/*  75 */         float f7 = 1.0F / (f5 + 1.0F);
/*  76 */         if (i == 0)
/*     */         {
/*  78 */           UtilsFX.bindTexture(this.t1);
/*  79 */           f7 = 0.1F;
/*  80 */           f5 = 65.0F;
/*  81 */           f6 = 0.125F;
/*  82 */           GL11.glEnable(3042);
/*  83 */           GL11.glBlendFunc(770, 771);
/*     */         }
/*  85 */         if (i == 1)
/*     */         {
/*  87 */           UtilsFX.bindTexture(this.t2);
/*  88 */           GL11.glEnable(3042);
/*  89 */           GL11.glBlendFunc(1, 1);
/*  90 */           f6 = 0.5F;
/*     */         }
/*  92 */         float f8 = (float)(y + offset);
/*  93 */         float f9 = f8 - ActiveRenderInfo.field_74590_b;
/*  94 */         float f10 = f8 + f5 - ActiveRenderInfo.field_74590_b;
/*  95 */         float f11 = f9 / f10;
/*  96 */         f11 = (float)(y + offset) + f11;
/*  97 */         GL11.glTranslatef(px, f11, pz);
/*  98 */         GL11.glTexGeni(8192, 9472, 9217);
/*  99 */         GL11.glTexGeni(8193, 9472, 9217);
/* 100 */         GL11.glTexGeni(8194, 9472, 9217);
/* 101 */         GL11.glTexGeni(8195, 9472, 9216);
/* 102 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 103 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 104 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 105 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 106 */         GL11.glEnable(3168);
/* 107 */         GL11.glEnable(3169);
/* 108 */         GL11.glEnable(3170);
/* 109 */         GL11.glEnable(3171);
/* 110 */         GL11.glPopMatrix();
/* 111 */         GL11.glMatrixMode(5890);
/* 112 */         GL11.glPushMatrix();
/* 113 */         GL11.glLoadIdentity();
/* 114 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 115 */         GL11.glScalef(f6, f6, f6);
/* 116 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 117 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 118 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 119 */         GL11.glTranslatef(-px, -pz, -py);
/*     */         
/*     */ 
/* 122 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74591_c * f5 / f9, -py);
/*     */         
/* 124 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 125 */         tessellator.func_78382_b();
/* 126 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 127 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 128 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 129 */         if (i == 0)
/*     */         {
/* 131 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 134 */         tessellator.func_78380_c(180);
/* 135 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 137 */         tessellator.func_78377_a(x, y + offset, z + 1.0D);
/* 138 */         tessellator.func_78377_a(x, y + offset, z);
/* 139 */         tessellator.func_78377_a(x + 1.0D, y + offset, z);
/* 140 */         tessellator.func_78377_a(x + 1.0D, y + offset, z + 1.0D);
/*     */         
/* 142 */         tessellator.func_78381_a();
/* 143 */         GL11.glPopMatrix();
/* 144 */         GL11.glMatrixMode(5888);
/*     */       }
/*     */     } else {
/* 147 */       GL11.glPushMatrix();
/* 148 */       UtilsFX.bindTexture(this.t3);
/* 149 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 150 */       tessellator.func_78382_b();
/*     */       
/* 152 */       tessellator.func_78380_c(180);
/*     */       
/* 154 */       tessellator.func_78374_a(x, y + offset, z + 1.0D, 1.0D, 1.0D);
/* 155 */       tessellator.func_78374_a(x, y + offset, z, 1.0D, 0.0D);
/* 156 */       tessellator.func_78374_a(x + 1.0D, y + offset, z, 0.0D, 0.0D);
/* 157 */       tessellator.func_78374_a(x + 1.0D, y + offset, z + 1.0D, 0.0D, 1.0D);
/*     */       
/* 159 */       tessellator.func_78381_a();
/* 160 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 163 */     GL11.glDisable(3042);
/* 164 */     GL11.glDisable(3168);
/* 165 */     GL11.glDisable(3169);
/* 166 */     GL11.glDisable(3170);
/* 167 */     GL11.glDisable(3171);
/* 168 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneYNeg(double x, double y, double z, float f)
/*     */   {
/* 173 */     float f1 = (float)TileEntityRendererDispatcher.field_147554_b;
/* 174 */     float f2 = (float)TileEntityRendererDispatcher.field_147555_c;
/* 175 */     float f3 = (float)TileEntityRendererDispatcher.field_147552_d;
/* 176 */     GL11.glDisable(2896);
/* 177 */     Random random = new Random(31100L);
/* 178 */     float offset = 1.0F;
/* 179 */     if (this.inrange) {
/* 180 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 182 */         GL11.glPushMatrix();
/* 183 */         float f5 = 16 - i;
/* 184 */         float f6 = 0.0625F;
/* 185 */         float f7 = 1.0F / (f5 + 1.0F);
/* 186 */         if (i == 0)
/*     */         {
/* 188 */           UtilsFX.bindTexture(this.t1);
/* 189 */           f7 = 0.1F;
/* 190 */           f5 = 65.0F;
/* 191 */           f6 = 0.125F;
/* 192 */           GL11.glEnable(3042);
/* 193 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 195 */         if (i == 1)
/*     */         {
/* 197 */           UtilsFX.bindTexture(this.t2);
/* 198 */           GL11.glEnable(3042);
/* 199 */           GL11.glBlendFunc(1, 1);
/* 200 */           f6 = 0.5F;
/*     */         }
/* 202 */         float f8 = (float)-(y + offset);
/* 203 */         float f9 = f8 + ActiveRenderInfo.field_74590_b;
/* 204 */         float f10 = f8 + f5 + ActiveRenderInfo.field_74590_b;
/* 205 */         float f11 = f9 / f10;
/* 206 */         f11 = (float)(y + offset) + f11;
/* 207 */         GL11.glTranslatef(f1, f11, f3);
/* 208 */         GL11.glTexGeni(8192, 9472, 9217);
/* 209 */         GL11.glTexGeni(8193, 9472, 9217);
/* 210 */         GL11.glTexGeni(8194, 9472, 9217);
/* 211 */         GL11.glTexGeni(8195, 9472, 9216);
/* 212 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 213 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 214 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 215 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 216 */         GL11.glEnable(3168);
/* 217 */         GL11.glEnable(3169);
/* 218 */         GL11.glEnable(3170);
/* 219 */         GL11.glEnable(3171);
/* 220 */         GL11.glPopMatrix();
/* 221 */         GL11.glMatrixMode(5890);
/* 222 */         GL11.glPushMatrix();
/* 223 */         GL11.glLoadIdentity();
/* 224 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 225 */         GL11.glScalef(f6, f6, f6);
/* 226 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 227 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 228 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 229 */         GL11.glTranslatef(-f1, -f3, -f2);
/*     */         
/*     */ 
/* 232 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74591_c * f5 / f9, -f2);
/* 233 */         Tessellator tessellator = Tessellator.field_78398_a;
/*     */         
/* 235 */         tessellator.func_78382_b();
/* 236 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 237 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 238 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 239 */         if (i == 0)
/*     */         {
/* 241 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 244 */         tessellator.func_78380_c(180);
/* 245 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 247 */         tessellator.func_78377_a(x, y + offset, z);
/* 248 */         tessellator.func_78377_a(x, y + offset, z + 1.0D);
/* 249 */         tessellator.func_78377_a(x + 1.0D, y + offset, z + 1.0D);
/* 250 */         tessellator.func_78377_a(x + 1.0D, y + offset, z);
/*     */         
/*     */ 
/* 253 */         tessellator.func_78381_a();
/* 254 */         GL11.glPopMatrix();
/* 255 */         GL11.glMatrixMode(5888);
/*     */       }
/*     */     } else {
/* 258 */       GL11.glPushMatrix();
/* 259 */       UtilsFX.bindTexture(this.t3);
/* 260 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 261 */       tessellator.func_78382_b();
/*     */       
/* 263 */       tessellator.func_78380_c(180);
/*     */       
/* 265 */       tessellator.func_78374_a(x, y + offset, z, 1.0D, 1.0D);
/* 266 */       tessellator.func_78374_a(x, y + offset, z + 1.0D, 1.0D, 0.0D);
/* 267 */       tessellator.func_78374_a(x + 1.0D, y + offset, z + 1.0D, 0.0D, 0.0D);
/* 268 */       tessellator.func_78374_a(x + 1.0D, y + offset, z, 0.0D, 1.0D);
/*     */       
/* 270 */       tessellator.func_78381_a();
/* 271 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 274 */     GL11.glDisable(3042);
/* 275 */     GL11.glDisable(3168);
/* 276 */     GL11.glDisable(3169);
/* 277 */     GL11.glDisable(3170);
/* 278 */     GL11.glDisable(3171);
/* 279 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneZNeg(double x, double y, double z, float f)
/*     */   {
/* 284 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 285 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 286 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 287 */     GL11.glDisable(2896);
/* 288 */     Random random = new Random(31100L);
/* 289 */     float offset = 1.0F;
/* 290 */     if (this.inrange) {
/* 291 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 293 */         GL11.glPushMatrix();
/* 294 */         float f5 = 16 - i;
/* 295 */         float f6 = 0.0625F;
/* 296 */         float f7 = 1.0F / (f5 + 1.0F);
/* 297 */         if (i == 0)
/*     */         {
/* 299 */           UtilsFX.bindTexture(this.t1);
/* 300 */           f7 = 0.1F;
/* 301 */           f5 = 65.0F;
/* 302 */           f6 = 0.125F;
/* 303 */           GL11.glEnable(3042);
/* 304 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 306 */         if (i == 1)
/*     */         {
/* 308 */           UtilsFX.bindTexture(this.t2);
/* 309 */           GL11.glEnable(3042);
/* 310 */           GL11.glBlendFunc(1, 1);
/* 311 */           f6 = 0.5F;
/*     */         }
/* 313 */         float f8 = (float)-(z + offset);
/* 314 */         float f9 = f8 + ActiveRenderInfo.field_74591_c;
/* 315 */         float f10 = f8 + f5 + ActiveRenderInfo.field_74591_c;
/* 316 */         float f11 = f9 / f10;
/* 317 */         f11 = (float)(z + offset) + f11;
/* 318 */         GL11.glTranslatef(px, py, f11);
/* 319 */         GL11.glTexGeni(8192, 9472, 9217);
/* 320 */         GL11.glTexGeni(8193, 9472, 9217);
/* 321 */         GL11.glTexGeni(8194, 9472, 9217);
/* 322 */         GL11.glTexGeni(8195, 9472, 9216);
/* 323 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 324 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 325 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */         
/* 327 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 328 */         GL11.glEnable(3168);
/* 329 */         GL11.glEnable(3169);
/* 330 */         GL11.glEnable(3170);
/* 331 */         GL11.glEnable(3171);
/* 332 */         GL11.glPopMatrix();
/* 333 */         GL11.glMatrixMode(5890);
/* 334 */         GL11.glPushMatrix();
/* 335 */         GL11.glLoadIdentity();
/* 336 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 337 */         GL11.glScalef(f6, f6, f6);
/* 338 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 339 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 340 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 341 */         GL11.glTranslatef(-px, -py, -pz);
/*     */         
/*     */ 
/* 344 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -pz);
/*     */         
/*     */ 
/* 347 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 348 */         tessellator.func_78382_b();
/* 349 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 350 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 351 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 352 */         if (i == 0)
/*     */         {
/* 354 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 357 */         tessellator.func_78380_c(180);
/* 358 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 360 */         tessellator.func_78377_a(x, y + 1.0D, z + offset);
/* 361 */         tessellator.func_78377_a(x, y, z + offset);
/* 362 */         tessellator.func_78377_a(x + 1.0D, y, z + offset);
/* 363 */         tessellator.func_78377_a(x + 1.0D, y + 1.0D, z + offset);
/*     */         
/* 365 */         tessellator.func_78381_a();
/* 366 */         GL11.glPopMatrix();
/* 367 */         GL11.glMatrixMode(5888);
/*     */       }
/*     */     } else {
/* 370 */       GL11.glPushMatrix();
/* 371 */       UtilsFX.bindTexture(this.t3);
/* 372 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 373 */       tessellator.func_78382_b();
/*     */       
/* 375 */       tessellator.func_78380_c(180);
/*     */       
/* 377 */       tessellator.func_78374_a(x, y + 1.0D, z + offset, 1.0D, 1.0D);
/* 378 */       tessellator.func_78374_a(x, y, z + offset, 1.0D, 0.0D);
/* 379 */       tessellator.func_78374_a(x + 1.0D, y, z + offset, 0.0D, 0.0D);
/* 380 */       tessellator.func_78374_a(x + 1.0D, y + 1.0D, z + offset, 0.0D, 1.0D);
/*     */       
/* 382 */       tessellator.func_78381_a();
/* 383 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 386 */     GL11.glDisable(3042);
/* 387 */     GL11.glDisable(3168);
/* 388 */     GL11.glDisable(3169);
/* 389 */     GL11.glDisable(3170);
/* 390 */     GL11.glDisable(3171);
/* 391 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneZPos(double x, double y, double z, float f)
/*     */   {
/* 396 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 397 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 398 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 399 */     GL11.glDisable(2896);
/* 400 */     Random random = new Random(31100L);
/* 401 */     float offset = 0.0F;
/* 402 */     if (this.inrange) {
/* 403 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 405 */         GL11.glPushMatrix();
/* 406 */         float f5 = 16 - i;
/* 407 */         float f6 = 0.0625F;
/* 408 */         float f7 = 1.0F / (f5 + 1.0F);
/* 409 */         if (i == 0)
/*     */         {
/* 411 */           UtilsFX.bindTexture(this.t1);
/* 412 */           f7 = 0.1F;
/* 413 */           f5 = 65.0F;
/* 414 */           f6 = 0.125F;
/* 415 */           GL11.glEnable(3042);
/* 416 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 418 */         if (i == 1)
/*     */         {
/* 420 */           UtilsFX.bindTexture(this.t2);
/* 421 */           GL11.glEnable(3042);
/* 422 */           GL11.glBlendFunc(1, 1);
/* 423 */           f6 = 0.5F;
/*     */         }
/* 425 */         float f8 = (float)(z + offset);
/* 426 */         float f9 = f8 - ActiveRenderInfo.field_74591_c;
/* 427 */         float f10 = f8 + f5 - ActiveRenderInfo.field_74591_c;
/* 428 */         float f11 = f9 / f10;
/* 429 */         f11 = (float)(z + offset) + f11;
/* 430 */         GL11.glTranslatef(px, py, f11);
/* 431 */         GL11.glTexGeni(8192, 9472, 9217);
/* 432 */         GL11.glTexGeni(8193, 9472, 9217);
/* 433 */         GL11.glTexGeni(8194, 9472, 9217);
/* 434 */         GL11.glTexGeni(8195, 9472, 9216);
/* 435 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 436 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 437 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */         
/* 439 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 440 */         GL11.glEnable(3168);
/* 441 */         GL11.glEnable(3169);
/* 442 */         GL11.glEnable(3170);
/* 443 */         GL11.glEnable(3171);
/* 444 */         GL11.glPopMatrix();
/* 445 */         GL11.glMatrixMode(5890);
/* 446 */         GL11.glPushMatrix();
/* 447 */         GL11.glLoadIdentity();
/* 448 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 449 */         GL11.glScalef(f6, f6, f6);
/* 450 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 451 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 452 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 453 */         GL11.glTranslatef(-px, -py, -pz);
/*     */         
/*     */ 
/* 456 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -pz);
/*     */         
/* 458 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 459 */         tessellator.func_78382_b();
/* 460 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 461 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 462 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 463 */         if (i == 0)
/*     */         {
/* 465 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 468 */         tessellator.func_78380_c(180);
/* 469 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 471 */         tessellator.func_78377_a(x, y, z + offset);
/* 472 */         tessellator.func_78377_a(x, y + 1.0D, z + offset);
/* 473 */         tessellator.func_78377_a(x + 1.0D, y + 1.0D, z + offset);
/* 474 */         tessellator.func_78377_a(x + 1.0D, y, z + offset);
/*     */         
/* 476 */         tessellator.func_78381_a();
/* 477 */         GL11.glPopMatrix();
/* 478 */         GL11.glMatrixMode(5888);
/*     */       }
/*     */     } else {
/* 481 */       GL11.glPushMatrix();
/* 482 */       UtilsFX.bindTexture(this.t3);
/* 483 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 484 */       tessellator.func_78382_b();
/*     */       
/* 486 */       tessellator.func_78380_c(180);
/*     */       
/* 488 */       tessellator.func_78374_a(x, y, z + offset, 1.0D, 1.0D);
/* 489 */       tessellator.func_78374_a(x, y + 1.0D, z + offset, 1.0D, 0.0D);
/* 490 */       tessellator.func_78374_a(x + 1.0D, y + 1.0D, z + offset, 0.0D, 0.0D);
/* 491 */       tessellator.func_78374_a(x + 1.0D, y, z + offset, 0.0D, 1.0D);
/*     */       
/* 493 */       tessellator.func_78381_a();
/* 494 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 497 */     GL11.glDisable(3042);
/* 498 */     GL11.glDisable(3168);
/* 499 */     GL11.glDisable(3169);
/* 500 */     GL11.glDisable(3170);
/* 501 */     GL11.glDisable(3171);
/* 502 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXNeg(double x, double y, double z, float f)
/*     */   {
/* 507 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 508 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 509 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 510 */     GL11.glDisable(2896);
/* 511 */     Random random = new Random(31100L);
/* 512 */     float offset = 1.0F;
/* 513 */     if (this.inrange) {
/* 514 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 516 */         GL11.glPushMatrix();
/*     */         
/* 518 */         float f5 = 16 - i;
/* 519 */         float f6 = 0.0625F;
/* 520 */         float f7 = 1.0F / (f5 + 1.0F);
/* 521 */         if (i == 0)
/*     */         {
/* 523 */           UtilsFX.bindTexture(this.t1);
/* 524 */           f7 = 0.1F;
/* 525 */           f5 = 65.0F;
/* 526 */           f6 = 0.125F;
/* 527 */           GL11.glEnable(3042);
/* 528 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 530 */         if (i == 1)
/*     */         {
/* 532 */           UtilsFX.bindTexture(this.t2);
/* 533 */           GL11.glEnable(3042);
/* 534 */           GL11.glBlendFunc(1, 1);
/* 535 */           f6 = 0.5F;
/*     */         }
/* 537 */         float f8 = (float)-(x + offset);
/* 538 */         float f9 = f8 + ActiveRenderInfo.field_74592_a;
/* 539 */         float f10 = f8 + f5 + ActiveRenderInfo.field_74592_a;
/* 540 */         float f11 = f9 / f10;
/* 541 */         f11 = (float)(x + offset) + f11;
/* 542 */         GL11.glTranslatef(f11, py, pz);
/* 543 */         GL11.glTexGeni(8192, 9472, 9217);
/* 544 */         GL11.glTexGeni(8193, 9472, 9217);
/* 545 */         GL11.glTexGeni(8194, 9472, 9217);
/* 546 */         GL11.glTexGeni(8195, 9472, 9216);
/*     */         
/*     */ 
/* 549 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 550 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 551 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 552 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 553 */         GL11.glEnable(3168);
/* 554 */         GL11.glEnable(3169);
/* 555 */         GL11.glEnable(3170);
/* 556 */         GL11.glEnable(3171);
/* 557 */         GL11.glPopMatrix();
/* 558 */         GL11.glMatrixMode(5890);
/* 559 */         GL11.glPushMatrix();
/* 560 */         GL11.glLoadIdentity();
/* 561 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 562 */         GL11.glScalef(f6, f6, f6);
/* 563 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 564 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 565 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 566 */         GL11.glTranslatef(-pz, -py, -px);
/*     */         
/*     */ 
/* 569 */         GL11.glTranslatef(ActiveRenderInfo.field_74591_c * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -px);
/*     */         
/*     */ 
/* 572 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 573 */         tessellator.func_78382_b();
/* 574 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 575 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 576 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 577 */         if (i == 0)
/*     */         {
/* 579 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 582 */         tessellator.func_78380_c(180);
/* 583 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 585 */         tessellator.func_78377_a(x + offset, y + 1.0D, z);
/* 586 */         tessellator.func_78377_a(x + offset, y + 1.0D, z + 1.0D);
/* 587 */         tessellator.func_78377_a(x + offset, y, z + 1.0D);
/* 588 */         tessellator.func_78377_a(x + offset, y, z);
/*     */         
/* 590 */         tessellator.func_78381_a();
/* 591 */         GL11.glPopMatrix();
/* 592 */         GL11.glMatrixMode(5888);
/*     */       }
/*     */     } else {
/* 595 */       GL11.glPushMatrix();
/* 596 */       UtilsFX.bindTexture(this.t3);
/* 597 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 598 */       tessellator.func_78382_b();
/*     */       
/* 600 */       tessellator.func_78380_c(180);
/*     */       
/* 602 */       tessellator.func_78374_a(x + offset, y + 1.0D, z, 1.0D, 1.0D);
/* 603 */       tessellator.func_78374_a(x + offset, y + 1.0D, z + 1.0D, 1.0D, 0.0D);
/* 604 */       tessellator.func_78374_a(x + offset, y, z + 1.0D, 0.0D, 0.0D);
/* 605 */       tessellator.func_78374_a(x + offset, y, z, 0.0D, 1.0D);
/*     */       
/* 607 */       tessellator.func_78381_a();
/* 608 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 611 */     GL11.glDisable(3042);
/* 612 */     GL11.glDisable(3168);
/* 613 */     GL11.glDisable(3169);
/* 614 */     GL11.glDisable(3170);
/* 615 */     GL11.glDisable(3171);
/* 616 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXPos(double x, double y, double z, float f)
/*     */   {
/* 621 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 622 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 623 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 624 */     GL11.glDisable(2896);
/* 625 */     Random random = new Random(31100L);
/* 626 */     float offset = 0.0F;
/* 627 */     if (this.inrange) {
/* 628 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 630 */         GL11.glPushMatrix();
/* 631 */         float f5 = 16 - i;
/* 632 */         float f6 = 0.0625F;
/* 633 */         float f7 = 1.0F / (f5 + 1.0F);
/* 634 */         if (i == 0)
/*     */         {
/* 636 */           UtilsFX.bindTexture(this.t1);
/* 637 */           f7 = 0.1F;
/* 638 */           f5 = 65.0F;
/* 639 */           f6 = 0.125F;
/* 640 */           GL11.glEnable(3042);
/* 641 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 643 */         if (i == 1)
/*     */         {
/* 645 */           UtilsFX.bindTexture(this.t2);
/* 646 */           GL11.glEnable(3042);
/* 647 */           GL11.glBlendFunc(1, 1);
/* 648 */           f6 = 0.5F;
/*     */         }
/* 650 */         float f8 = (float)(x + offset);
/* 651 */         float f9 = f8 - ActiveRenderInfo.field_74592_a;
/* 652 */         float f10 = f8 + f5 - ActiveRenderInfo.field_74592_a;
/* 653 */         float f11 = f9 / f10;
/* 654 */         f11 = (float)(x + offset) + f11;
/* 655 */         GL11.glTranslatef(f11, py, pz);
/* 656 */         GL11.glTexGeni(8192, 9472, 9217);
/* 657 */         GL11.glTexGeni(8193, 9472, 9217);
/* 658 */         GL11.glTexGeni(8194, 9472, 9217);
/* 659 */         GL11.glTexGeni(8195, 9472, 9216);
/*     */         
/*     */ 
/* 662 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 663 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 664 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 665 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 666 */         GL11.glEnable(3168);
/* 667 */         GL11.glEnable(3169);
/* 668 */         GL11.glEnable(3170);
/* 669 */         GL11.glEnable(3171);
/* 670 */         GL11.glPopMatrix();
/* 671 */         GL11.glMatrixMode(5890);
/* 672 */         GL11.glPushMatrix();
/* 673 */         GL11.glLoadIdentity();
/* 674 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 675 */         GL11.glScalef(f6, f6, f6);
/* 676 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 677 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 678 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 679 */         GL11.glTranslatef(-pz, -py, -px);
/*     */         
/*     */ 
/* 682 */         GL11.glTranslatef(ActiveRenderInfo.field_74591_c * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -px);
/*     */         
/*     */ 
/* 685 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 686 */         tessellator.func_78382_b();
/* 687 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 688 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 689 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 690 */         if (i == 0)
/*     */         {
/* 692 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 695 */         tessellator.func_78380_c(180);
/* 696 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 698 */         tessellator.func_78377_a(x + offset, y, z);
/* 699 */         tessellator.func_78377_a(x + offset, y, z + 1.0D);
/* 700 */         tessellator.func_78377_a(x + offset, y + 1.0D, z + 1.0D);
/* 701 */         tessellator.func_78377_a(x + offset, y + 1.0D, z);
/*     */         
/* 703 */         tessellator.func_78381_a();
/* 704 */         GL11.glPopMatrix();
/* 705 */         GL11.glMatrixMode(5888);
/*     */       }
/*     */     } else {
/* 708 */       GL11.glPushMatrix();
/* 709 */       UtilsFX.bindTexture(this.t3);
/* 710 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 711 */       tessellator.func_78382_b();
/*     */       
/* 713 */       tessellator.func_78380_c(180);
/*     */       
/* 715 */       tessellator.func_78374_a(x + offset, y, z, 1.0D, 1.0D);
/* 716 */       tessellator.func_78374_a(x + offset, y, z + 1.0D, 1.0D, 0.0D);
/* 717 */       tessellator.func_78374_a(x + offset, y + 1.0D, z + 1.0D, 0.0D, 0.0D);
/* 718 */       tessellator.func_78374_a(x + offset, y + 1.0D, z, 0.0D, 1.0D);
/*     */       
/* 720 */       tessellator.func_78381_a();
/* 721 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 724 */     GL11.glDisable(3042);
/* 725 */     GL11.glDisable(3168);
/* 726 */     GL11.glDisable(3169);
/* 727 */     GL11.glDisable(3170);
/* 728 */     GL11.glDisable(3171);
/* 729 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   private FloatBuffer calcFloatBuffer(float f, float f1, float f2, float f3)
/*     */   {
/* 734 */     this.fBuffer.clear();
/* 735 */     this.fBuffer.put(f).put(f1).put(f2).put(f3);
/* 736 */     this.fBuffer.flip();
/* 737 */     return this.fBuffer;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileEldritchNothingRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */