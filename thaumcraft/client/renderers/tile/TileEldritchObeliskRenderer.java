/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.config.Config;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEldritchObeliskRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   FloatBuffer fBuffer;
/*     */   private boolean inrange;
/*     */   private IModelCustom model;
/*  33 */   private static final ResourceLocation CAP = new ResourceLocation("thaumcraft", "textures/models/obelisk_cap.obj");
/*     */   
/*     */   public TileEldritchObeliskRenderer()
/*     */   {
/*  37 */     this.fBuffer = GLAllocation.func_74529_h(16);
/*  38 */     this.model = AdvancedModelLoader.loadModel(CAP);
/*     */   }
/*     */   
/*  41 */   private String t1 = "textures/misc/tunnel.png";
/*  42 */   private String t2 = "textures/misc/particlefield.png";
/*  43 */   private String t3 = "textures/misc/particlefield32.png";
/*  44 */   private String t4 = "textures/models/obelisk_side.png";
/*  45 */   private String t5 = "textures/models/obelisk_cap.png";
/*  46 */   private String t6 = "textures/models/obelisk_side_2.png";
/*  47 */   private String t7 = "textures/models/obelisk_cap_2.png";
/*     */   
/*     */ 
/*     */   public void func_147500_a(TileEntity te, double x, double y, double z, float f)
/*     */   {
/*  52 */     this.inrange = (Minecraft.func_71410_x().field_71451_h.func_70092_e(te.field_145851_c + 0.5D, te.field_145848_d + 0.5D, te.field_145849_e + 0.5D) < 512.0D);
/*     */     
/*     */ 
/*  55 */     float bob = 0.0F;
/*  56 */     float count = Minecraft.func_71410_x().field_71451_h.field_70173_aa + f;
/*  57 */     bob = MathHelper.func_76126_a(count / 10.0F) * 0.1F + 0.1F;
/*     */     
/*  59 */     GL11.glPushMatrix();
/*  60 */     GL11.glDisable(2912);
/*  61 */     drawPlaneZNeg(x, y + 1.0D + bob, z, f, 3);
/*  62 */     drawPlaneZPos(x, y + 1.0D + bob, z, f, 3);
/*  63 */     drawPlaneXNeg(x, y + 1.0D + bob, z, f, 3);
/*  64 */     drawPlaneXPos(x, y + 1.0D + bob, z, f, 3);
/*  65 */     GL11.glEnable(2912);
/*  66 */     GL11.glPopMatrix();
/*     */     
/*  68 */     GL11.glPushMatrix();
/*     */     
/*  70 */     GL11.glEnable(2896);
/*     */     
/*  72 */     GL11.glEnable(32826);
/*  73 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  75 */     GL11.glEnable(3042);
/*  76 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  78 */     String tempTex1 = this.t4;
/*  79 */     String tempTex2 = this.t5;
/*  80 */     if (te.func_145831_w() != null) {
/*  81 */       int j = te.func_145838_q().func_149677_c(te.func_145831_w(), te.field_145851_c, te.field_145848_d + 5, te.field_145849_e);
/*  82 */       int k = j % 65536;
/*  83 */       int l = j / 65536;
/*  84 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */       
/*  86 */       if (te.func_145831_w().field_73011_w.field_76574_g == Config.dimensionOuterId) {
/*  87 */         tempTex1 = this.t6;
/*  88 */         tempTex2 = this.t7;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  93 */     GL11.glPushMatrix();
/*  94 */     UtilsFX.bindTexture(tempTex1);
/*  95 */     GL11.glTranslated(x + 0.5D, y + 1.0D + bob, z + 0.5D);
/*  96 */     for (int a = 0; a < 4; a++) {
/*  97 */       GL11.glPushMatrix();
/*  98 */       GL11.glRotatef(a * 90, 0.0F, 1.0F, 0.0F);
/*  99 */       GL11.glTranslated(0.0D, 0.0D, -0.5D);
/* 100 */       renderSide(3);
/* 101 */       GL11.glPopMatrix();
/*     */     }
/* 103 */     GL11.glPopMatrix();
/*     */     
/* 105 */     GL11.glPushMatrix();
/*     */     
/* 107 */     GL11.glTranslated(x + 0.5D, y + 1.0D + bob, z + 0.5D);
/* 108 */     GL11.glRotated(90.0D, 1.0D, 0.0D, 0.0D);
/* 109 */     UtilsFX.bindTexture(tempTex2);
/* 110 */     this.model.renderPart("Cap");
/* 111 */     GL11.glPopMatrix();
/* 112 */     GL11.glPushMatrix();
/* 113 */     GL11.glTranslated(x + 0.5D, y + 4.0D + bob, z + 0.5D);
/* 114 */     GL11.glRotated(90.0D, -1.0D, 0.0D, 0.0D);
/* 115 */     this.model.renderPart("Cap");
/* 116 */     GL11.glPopMatrix();
/* 117 */     GL11.glDisable(3042);
/* 118 */     GL11.glDisable(32826);
/* 119 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   public void drawPlaneZPos(double x, double y, double z, float f, int height)
/*     */   {
/* 125 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 126 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 127 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 128 */     GL11.glDisable(2896);
/* 129 */     Random random = new Random(31100L);
/* 130 */     float offset = 0.99F;
/* 131 */     if (this.inrange) {
/* 132 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 134 */         GL11.glPushMatrix();
/* 135 */         float f5 = 16 - i;
/* 136 */         float f6 = 0.0625F;
/* 137 */         float f7 = 1.0F / (f5 + 1.0F);
/* 138 */         if (i == 0)
/*     */         {
/* 140 */           UtilsFX.bindTexture(this.t1);
/* 141 */           f7 = 0.1F;
/* 142 */           f5 = 65.0F;
/* 143 */           f6 = 0.125F;
/* 144 */           GL11.glEnable(3042);
/* 145 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 147 */         if (i == 1)
/*     */         {
/* 149 */           UtilsFX.bindTexture(this.t2);
/* 150 */           GL11.glEnable(3042);
/* 151 */           GL11.glBlendFunc(1, 1);
/* 152 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 155 */         float f8 = (float)-(z + offset);
/* 156 */         float f9 = f8 + ActiveRenderInfo.field_74591_c;
/* 157 */         float f10 = f8 + f5 + ActiveRenderInfo.field_74591_c;
/* 158 */         float f11 = f9 / f10;
/* 159 */         f11 = (float)(z + offset) + f11;
/* 160 */         GL11.glTranslatef(px, py, f11);
/* 161 */         GL11.glTexGeni(8192, 9472, 9217);
/* 162 */         GL11.glTexGeni(8193, 9472, 9217);
/* 163 */         GL11.glTexGeni(8194, 9472, 9217);
/* 164 */         GL11.glTexGeni(8195, 9472, 9216);
/* 165 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 166 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 167 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */         
/* 169 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 170 */         GL11.glEnable(3168);
/* 171 */         GL11.glEnable(3169);
/* 172 */         GL11.glEnable(3170);
/* 173 */         GL11.glEnable(3171);
/* 174 */         GL11.glPopMatrix();
/* 175 */         GL11.glMatrixMode(5890);
/* 176 */         GL11.glPushMatrix();
/* 177 */         GL11.glLoadIdentity();
/* 178 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 179 */         GL11.glScalef(f6, f6, f6);
/* 180 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 181 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 182 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 183 */         GL11.glTranslatef(-px, -py, -pz);
/*     */         
/*     */ 
/* 186 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -pz);
/*     */         
/*     */ 
/* 189 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 190 */         tessellator.func_78382_b();
/* 191 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 192 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 193 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 194 */         if (i == 0)
/*     */         {
/* 196 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 199 */         tessellator.func_78380_c(180);
/* 200 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 202 */         tessellator.func_78377_a(x, y + height, z + offset);
/* 203 */         tessellator.func_78377_a(x, y, z + offset);
/* 204 */         tessellator.func_78377_a(x + 1.0D, y, z + offset);
/* 205 */         tessellator.func_78377_a(x + 1.0D, y + height, z + offset);
/*     */         
/* 207 */         tessellator.func_78381_a();
/* 208 */         GL11.glPopMatrix();
/* 209 */         GL11.glMatrixMode(5888);
/*     */       }
/*     */     } else {
/* 212 */       GL11.glPushMatrix();
/* 213 */       UtilsFX.bindTexture(this.t3);
/* 214 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 215 */       tessellator.func_78382_b();
/*     */       
/* 217 */       tessellator.func_78380_c(180);
/* 218 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 220 */       tessellator.func_78374_a(x, y + height, z + offset, 1.0D, 1.0D);
/* 221 */       tessellator.func_78374_a(x, y, z + offset, 1.0D, 0.0D);
/* 222 */       tessellator.func_78374_a(x + 1.0D, y, z + offset, 0.0D, 0.0D);
/* 223 */       tessellator.func_78374_a(x + 1.0D, y + height, z + offset, 0.0D, 1.0D);
/*     */       
/* 225 */       tessellator.func_78381_a();
/* 226 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 229 */     GL11.glDisable(3042);
/* 230 */     GL11.glDisable(3168);
/* 231 */     GL11.glDisable(3169);
/* 232 */     GL11.glDisable(3170);
/* 233 */     GL11.glDisable(3171);
/* 234 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneZNeg(double x, double y, double z, float f, int height)
/*     */   {
/* 239 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 240 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 241 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 242 */     GL11.glDisable(2896);
/* 243 */     Random random = new Random(31100L);
/* 244 */     float offset = 0.01F;
/* 245 */     if (this.inrange) {
/* 246 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 248 */         GL11.glPushMatrix();
/* 249 */         float f5 = 16 - i;
/* 250 */         float f6 = 0.0625F;
/* 251 */         float f7 = 1.0F / (f5 + 1.0F);
/* 252 */         if (i == 0)
/*     */         {
/* 254 */           UtilsFX.bindTexture(this.t1);
/* 255 */           f7 = 0.1F;
/* 256 */           f5 = 65.0F;
/* 257 */           f6 = 0.125F;
/* 258 */           GL11.glEnable(3042);
/* 259 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 261 */         if (i == 1)
/*     */         {
/* 263 */           UtilsFX.bindTexture(this.t2);
/* 264 */           GL11.glEnable(3042);
/* 265 */           GL11.glBlendFunc(1, 1);
/* 266 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 269 */         float f8 = (float)(z + offset);
/* 270 */         float f9 = f8 - ActiveRenderInfo.field_74591_c;
/* 271 */         float f10 = f8 + f5 - ActiveRenderInfo.field_74591_c;
/* 272 */         float f11 = f9 / f10;
/* 273 */         f11 = (float)(z + offset) + f11;
/* 274 */         GL11.glTranslatef(px, py, f11);
/* 275 */         GL11.glTexGeni(8192, 9472, 9217);
/* 276 */         GL11.glTexGeni(8193, 9472, 9217);
/* 277 */         GL11.glTexGeni(8194, 9472, 9217);
/* 278 */         GL11.glTexGeni(8195, 9472, 9216);
/* 279 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 280 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 281 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */         
/* 283 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 284 */         GL11.glEnable(3168);
/* 285 */         GL11.glEnable(3169);
/* 286 */         GL11.glEnable(3170);
/* 287 */         GL11.glEnable(3171);
/* 288 */         GL11.glPopMatrix();
/* 289 */         GL11.glMatrixMode(5890);
/* 290 */         GL11.glPushMatrix();
/* 291 */         GL11.glLoadIdentity();
/* 292 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 293 */         GL11.glScalef(f6, f6, f6);
/* 294 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 295 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 296 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 297 */         GL11.glTranslatef(-px, -py, -pz);
/*     */         
/*     */ 
/* 300 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -pz);
/*     */         
/* 302 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 303 */         tessellator.func_78382_b();
/* 304 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 305 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 306 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 307 */         if (i == 0)
/*     */         {
/* 309 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 312 */         tessellator.func_78380_c(180);
/* 313 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 315 */         tessellator.func_78377_a(x, y, z + offset);
/* 316 */         tessellator.func_78377_a(x, y + height, z + offset);
/* 317 */         tessellator.func_78377_a(x + 1.0D, y + height, z + offset);
/* 318 */         tessellator.func_78377_a(x + 1.0D, y, z + offset);
/*     */         
/* 320 */         tessellator.func_78381_a();
/* 321 */         GL11.glPopMatrix();
/* 322 */         GL11.glMatrixMode(5888);
/*     */       }
/* 324 */     } else { GL11.glPushMatrix();
/* 325 */       UtilsFX.bindTexture(this.t3);
/* 326 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 327 */       tessellator.func_78382_b();
/*     */       
/* 329 */       tessellator.func_78380_c(180);
/* 330 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 332 */       tessellator.func_78374_a(x, y, z + offset, 1.0D, 1.0D);
/* 333 */       tessellator.func_78374_a(x, y + height, z + offset, 1.0D, 0.0D);
/* 334 */       tessellator.func_78374_a(x + 1.0D, y + height, z + offset, 0.0D, 0.0D);
/* 335 */       tessellator.func_78374_a(x + 1.0D, y, z + offset, 0.0D, 1.0D);
/*     */       
/* 337 */       tessellator.func_78381_a();
/* 338 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 341 */     GL11.glDisable(3042);
/* 342 */     GL11.glDisable(3168);
/* 343 */     GL11.glDisable(3169);
/* 344 */     GL11.glDisable(3170);
/* 345 */     GL11.glDisable(3171);
/* 346 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXPos(double x, double y, double z, float f, int height)
/*     */   {
/* 351 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 352 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 353 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 354 */     GL11.glDisable(2896);
/* 355 */     Random random = new Random(31100L);
/* 356 */     float offset = 0.99F;
/* 357 */     if (this.inrange) {
/* 358 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 360 */         GL11.glPushMatrix();
/* 361 */         float f5 = 16 - i;
/* 362 */         float f6 = 0.0625F;
/* 363 */         float f7 = 1.0F / (f5 + 1.0F);
/* 364 */         if (i == 0)
/*     */         {
/* 366 */           UtilsFX.bindTexture(this.t1);
/* 367 */           f7 = 0.1F;
/* 368 */           f5 = 65.0F;
/* 369 */           f6 = 0.125F;
/* 370 */           GL11.glEnable(3042);
/* 371 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 373 */         if (i == 1)
/*     */         {
/* 375 */           UtilsFX.bindTexture(this.t2);
/* 376 */           GL11.glEnable(3042);
/* 377 */           GL11.glBlendFunc(1, 1);
/* 378 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 381 */         float f8 = (float)-(x + offset);
/* 382 */         float f9 = f8 + ActiveRenderInfo.field_74592_a;
/* 383 */         float f10 = f8 + f5 + ActiveRenderInfo.field_74592_a;
/* 384 */         float f11 = f9 / f10;
/* 385 */         f11 = (float)(x + offset) + f11;
/* 386 */         GL11.glTranslatef(f11, py, pz);
/* 387 */         GL11.glTexGeni(8192, 9472, 9217);
/* 388 */         GL11.glTexGeni(8193, 9472, 9217);
/* 389 */         GL11.glTexGeni(8194, 9472, 9217);
/* 390 */         GL11.glTexGeni(8195, 9472, 9216);
/*     */         
/*     */ 
/* 393 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 394 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 395 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 396 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 397 */         GL11.glEnable(3168);
/* 398 */         GL11.glEnable(3169);
/* 399 */         GL11.glEnable(3170);
/* 400 */         GL11.glEnable(3171);
/* 401 */         GL11.glPopMatrix();
/* 402 */         GL11.glMatrixMode(5890);
/* 403 */         GL11.glPushMatrix();
/* 404 */         GL11.glLoadIdentity();
/* 405 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 406 */         GL11.glScalef(f6, f6, f6);
/* 407 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 408 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 409 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 410 */         GL11.glTranslatef(-pz, -py, -px);
/*     */         
/*     */ 
/* 413 */         GL11.glTranslatef(ActiveRenderInfo.field_74591_c * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -px);
/*     */         
/*     */ 
/* 416 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 417 */         tessellator.func_78382_b();
/* 418 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 419 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 420 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 421 */         if (i == 0)
/*     */         {
/* 423 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 426 */         tessellator.func_78380_c(180);
/* 427 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 429 */         tessellator.func_78377_a(x + offset, y + height, z);
/* 430 */         tessellator.func_78377_a(x + offset, y + height, z + 1.0D);
/* 431 */         tessellator.func_78377_a(x + offset, y, z + 1.0D);
/* 432 */         tessellator.func_78377_a(x + offset, y, z);
/*     */         
/* 434 */         tessellator.func_78381_a();
/* 435 */         GL11.glPopMatrix();
/* 436 */         GL11.glMatrixMode(5888);
/*     */       }
/* 438 */     } else { GL11.glPushMatrix();
/* 439 */       UtilsFX.bindTexture(this.t3);
/* 440 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 441 */       tessellator.func_78382_b();
/*     */       
/* 443 */       tessellator.func_78380_c(180);
/* 444 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 446 */       tessellator.func_78374_a(x + offset, y + height, z, 1.0D, 1.0D);
/* 447 */       tessellator.func_78374_a(x + offset, y + height, z + 1.0D, 1.0D, 0.0D);
/* 448 */       tessellator.func_78374_a(x + offset, y, z + 1.0D, 0.0D, 0.0D);
/* 449 */       tessellator.func_78374_a(x + offset, y, z, 0.0D, 1.0D);
/*     */       
/* 451 */       tessellator.func_78381_a();
/* 452 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 455 */     GL11.glDisable(3042);
/* 456 */     GL11.glDisable(3168);
/* 457 */     GL11.glDisable(3169);
/* 458 */     GL11.glDisable(3170);
/* 459 */     GL11.glDisable(3171);
/* 460 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXNeg(double x, double y, double z, float f, int height)
/*     */   {
/* 465 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 466 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 467 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 468 */     GL11.glDisable(2896);
/* 469 */     Random random = new Random(31100L);
/* 470 */     float offset = 0.01F;
/* 471 */     if (this.inrange) {
/* 472 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 474 */         GL11.glPushMatrix();
/* 475 */         float f5 = 16 - i;
/* 476 */         float f6 = 0.0625F;
/* 477 */         float f7 = 1.0F / (f5 + 1.0F);
/* 478 */         if (i == 0)
/*     */         {
/* 480 */           UtilsFX.bindTexture(this.t1);
/* 481 */           f7 = 0.1F;
/* 482 */           f5 = 65.0F;
/* 483 */           f6 = 0.125F;
/* 484 */           GL11.glEnable(3042);
/* 485 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 487 */         if (i == 1)
/*     */         {
/* 489 */           UtilsFX.bindTexture(this.t2);
/* 490 */           GL11.glEnable(3042);
/* 491 */           GL11.glBlendFunc(1, 1);
/* 492 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 495 */         float f8 = (float)(x + offset);
/* 496 */         float f9 = f8 - ActiveRenderInfo.field_74592_a;
/* 497 */         float f10 = f8 + f5 - ActiveRenderInfo.field_74592_a;
/* 498 */         float f11 = f9 / f10;
/* 499 */         f11 = (float)(x + offset) + f11;
/* 500 */         GL11.glTranslatef(f11, py, pz);
/* 501 */         GL11.glTexGeni(8192, 9472, 9217);
/* 502 */         GL11.glTexGeni(8193, 9472, 9217);
/* 503 */         GL11.glTexGeni(8194, 9472, 9217);
/* 504 */         GL11.glTexGeni(8195, 9472, 9216);
/*     */         
/*     */ 
/* 507 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 508 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 509 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 510 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 511 */         GL11.glEnable(3168);
/* 512 */         GL11.glEnable(3169);
/* 513 */         GL11.glEnable(3170);
/* 514 */         GL11.glEnable(3171);
/* 515 */         GL11.glPopMatrix();
/* 516 */         GL11.glMatrixMode(5890);
/* 517 */         GL11.glPushMatrix();
/* 518 */         GL11.glLoadIdentity();
/* 519 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 520 */         GL11.glScalef(f6, f6, f6);
/* 521 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 522 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 523 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 524 */         GL11.glTranslatef(-pz, -py, -px);
/*     */         
/*     */ 
/* 527 */         GL11.glTranslatef(ActiveRenderInfo.field_74591_c * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -px);
/*     */         
/*     */ 
/* 530 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 531 */         tessellator.func_78382_b();
/* 532 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 533 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 534 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 535 */         if (i == 0)
/*     */         {
/* 537 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 540 */         tessellator.func_78380_c(180);
/* 541 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 543 */         tessellator.func_78377_a(x + offset, y, z);
/* 544 */         tessellator.func_78377_a(x + offset, y, z + 1.0D);
/* 545 */         tessellator.func_78377_a(x + offset, y + height, z + 1.0D);
/* 546 */         tessellator.func_78377_a(x + offset, y + height, z);
/*     */         
/* 548 */         tessellator.func_78381_a();
/* 549 */         GL11.glPopMatrix();
/* 550 */         GL11.glMatrixMode(5888);
/*     */       }
/* 552 */     } else { GL11.glPushMatrix();
/* 553 */       UtilsFX.bindTexture(this.t3);
/* 554 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 555 */       tessellator.func_78382_b();
/*     */       
/* 557 */       tessellator.func_78380_c(180);
/* 558 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 560 */       tessellator.func_78374_a(x + offset, y, z, 1.0D, 1.0D);
/* 561 */       tessellator.func_78374_a(x + offset, y, z + 1.0D, 1.0D, 0.0D);
/* 562 */       tessellator.func_78374_a(x + offset, y + height, z + 1.0D, 0.0D, 0.0D);
/* 563 */       tessellator.func_78374_a(x + offset, y + height, z, 0.0D, 1.0D);
/*     */       
/* 565 */       tessellator.func_78381_a();
/* 566 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 569 */     GL11.glDisable(3042);
/* 570 */     GL11.glDisable(3168);
/* 571 */     GL11.glDisable(3169);
/* 572 */     GL11.glDisable(3170);
/* 573 */     GL11.glDisable(3171);
/* 574 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   private FloatBuffer calcFloatBuffer(float f, float f1, float f2, float f3)
/*     */   {
/* 579 */     this.fBuffer.clear();
/* 580 */     this.fBuffer.put(f).put(f1).put(f2).put(f3);
/* 581 */     this.fBuffer.flip();
/* 582 */     return this.fBuffer;
/*     */   }
/*     */   
/*     */ 
/*     */   public void renderSide(int h)
/*     */   {
/* 588 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 589 */     GL11.glEnable(32826);
/* 590 */     GL11.glEnable(3042);
/* 591 */     GL11.glBlendFunc(770, 771);
/* 592 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 593 */     tessellator.func_78382_b();
/* 594 */     tessellator.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 596 */     tessellator.func_78374_a(-0.5D, h, 0.0D, 0.0D, 1.0D);
/* 597 */     tessellator.func_78374_a(0.5D, h, 0.0D, 1.0D, 1.0D);
/* 598 */     tessellator.func_78374_a(0.5D, 0.0D, 0.0D, 1.0D, 0.0D);
/* 599 */     tessellator.func_78374_a(-0.5D, 0.0D, 0.0D, 0.0D, 0.0D);
/* 600 */     tessellator.func_78381_a();
/* 601 */     GL11.glDisable(3042);
/* 602 */     GL11.glDisable(32826);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileEldritchObeliskRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */