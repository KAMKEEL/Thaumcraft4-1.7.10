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
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileHole;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileHoleRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   FloatBuffer fBuffer;
/*     */   
/*     */   public TileHoleRenderer()
/*     */   {
/*  29 */     this.fBuffer = GLAllocation.func_74529_h(16);
/*     */   }
/*     */   
/*  32 */   private String t1 = "textures/misc/tunnel.png";
/*  33 */   private String t2 = "textures/misc/particlefield.png";
/*  34 */   private String t3 = "textures/misc/particlefield32.png";
/*     */   private boolean inrange;
/*     */   
/*     */   public void drawPlaneYPos(TileHole tileentityendportal, double x, double y, double z, float f) {
/*  38 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/*  39 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/*  40 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/*  41 */     GL11.glDisable(2896);
/*  42 */     Random random = new Random(31100L);
/*  43 */     float offset = 0.999F;
/*  44 */     if (this.inrange) {
/*  45 */       for (int i = 0; i < 16; i++)
/*     */       {
/*  47 */         GL11.glPushMatrix();
/*  48 */         float f5 = 16 - i;
/*  49 */         float f6 = 0.0625F;
/*  50 */         float f7 = 1.0F / (f5 + 1.0F);
/*  51 */         if (i == 0)
/*     */         {
/*  53 */           UtilsFX.bindTexture(this.t1);
/*  54 */           f7 = 0.1F;
/*  55 */           f5 = 65.0F;
/*  56 */           f6 = 0.125F;
/*  57 */           GL11.glEnable(3042);
/*  58 */           GL11.glBlendFunc(770, 771);
/*     */         }
/*  60 */         if (i == 1)
/*     */         {
/*  62 */           UtilsFX.bindTexture(this.t2);
/*  63 */           GL11.glEnable(3042);
/*  64 */           GL11.glBlendFunc(1, 1);
/*  65 */           f6 = 0.5F;
/*     */         }
/*  67 */         float f8 = (float)(y + offset);
/*  68 */         float f9 = f8 - ActiveRenderInfo.field_74590_b;
/*  69 */         float f10 = f8 + f5 - ActiveRenderInfo.field_74590_b;
/*  70 */         float f11 = f9 / f10;
/*  71 */         f11 = (float)(y + offset) + f11;
/*  72 */         GL11.glTranslatef(px, f11, pz);
/*  73 */         GL11.glTexGeni(8192, 9472, 9217);
/*  74 */         GL11.glTexGeni(8193, 9472, 9217);
/*  75 */         GL11.glTexGeni(8194, 9472, 9217);
/*  76 */         GL11.glTexGeni(8195, 9472, 9216);
/*  77 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/*  78 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/*  79 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*  80 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/*  81 */         GL11.glEnable(3168);
/*  82 */         GL11.glEnable(3169);
/*  83 */         GL11.glEnable(3170);
/*  84 */         GL11.glEnable(3171);
/*  85 */         GL11.glPopMatrix();
/*  86 */         GL11.glMatrixMode(5890);
/*  87 */         GL11.glPushMatrix();
/*  88 */         GL11.glLoadIdentity();
/*  89 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/*  90 */         GL11.glScalef(f6, f6, f6);
/*  91 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/*  92 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/*  93 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/*  94 */         GL11.glTranslatef(-px, -pz, -py);
/*     */         
/*     */ 
/*  97 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74591_c * f5 / f9, -py);
/*     */         
/*  99 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 100 */         tessellator.func_78382_b();
/* 101 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 102 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 103 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 104 */         if (i == 0)
/*     */         {
/* 106 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 109 */         tessellator.func_78380_c(180);
/* 110 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 112 */         tessellator.func_78377_a(x, y + offset, z + 1.0D);
/* 113 */         tessellator.func_78377_a(x, y + offset, z);
/* 114 */         tessellator.func_78377_a(x + 1.0D, y + offset, z);
/* 115 */         tessellator.func_78377_a(x + 1.0D, y + offset, z + 1.0D);
/*     */         
/* 117 */         tessellator.func_78381_a();
/* 118 */         GL11.glPopMatrix();
/* 119 */         GL11.glMatrixMode(5888);
/*     */       }
/* 121 */     } else { GL11.glPushMatrix();
/* 122 */       UtilsFX.bindTexture(this.t3);
/* 123 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 124 */       tessellator.func_78382_b();
/*     */       
/* 126 */       tessellator.func_78380_c(180);
/* 127 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 129 */       tessellator.func_78374_a(x, y + offset, z + 1.0D, 1.0D, 1.0D);
/* 130 */       tessellator.func_78374_a(x, y + offset, z, 1.0D, 0.0D);
/* 131 */       tessellator.func_78374_a(x + 1.0D, y + offset, z, 0.0D, 0.0D);
/* 132 */       tessellator.func_78374_a(x + 1.0D, y + offset, z + 1.0D, 0.0D, 1.0D);
/*     */       
/* 134 */       tessellator.func_78381_a();
/* 135 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 138 */     GL11.glDisable(3042);
/* 139 */     GL11.glDisable(3168);
/* 140 */     GL11.glDisable(3169);
/* 141 */     GL11.glDisable(3170);
/* 142 */     GL11.glDisable(3171);
/* 143 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneYNeg(TileHole tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 148 */     float f1 = (float)TileEntityRendererDispatcher.field_147554_b;
/* 149 */     float f2 = (float)TileEntityRendererDispatcher.field_147555_c;
/* 150 */     float f3 = (float)TileEntityRendererDispatcher.field_147552_d;
/* 151 */     GL11.glDisable(2896);
/* 152 */     Random random = new Random(31100L);
/* 153 */     float offset = 0.001F;
/* 154 */     if (this.inrange) {
/* 155 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 157 */         GL11.glPushMatrix();
/* 158 */         float f5 = 16 - i;
/* 159 */         float f6 = 0.0625F;
/* 160 */         float f7 = 1.0F / (f5 + 1.0F);
/* 161 */         if (i == 0)
/*     */         {
/* 163 */           UtilsFX.bindTexture(this.t1);
/* 164 */           f7 = 0.1F;
/* 165 */           f5 = 65.0F;
/* 166 */           f6 = 0.125F;
/* 167 */           GL11.glEnable(3042);
/* 168 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 170 */         if (i == 1)
/*     */         {
/* 172 */           UtilsFX.bindTexture(this.t2);
/* 173 */           GL11.glEnable(3042);
/* 174 */           GL11.glBlendFunc(1, 1);
/* 175 */           f6 = 0.5F;
/*     */         }
/* 177 */         float f8 = (float)-(y + offset);
/* 178 */         float f9 = f8 + ActiveRenderInfo.field_74590_b;
/* 179 */         float f10 = f8 + f5 + ActiveRenderInfo.field_74590_b;
/* 180 */         float f11 = f9 / f10;
/* 181 */         f11 = (float)(y + offset) + f11;
/* 182 */         GL11.glTranslatef(f1, f11, f3);
/* 183 */         GL11.glTexGeni(8192, 9472, 9217);
/* 184 */         GL11.glTexGeni(8193, 9472, 9217);
/* 185 */         GL11.glTexGeni(8194, 9472, 9217);
/* 186 */         GL11.glTexGeni(8195, 9472, 9216);
/* 187 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 188 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 189 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 190 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 191 */         GL11.glEnable(3168);
/* 192 */         GL11.glEnable(3169);
/* 193 */         GL11.glEnable(3170);
/* 194 */         GL11.glEnable(3171);
/* 195 */         GL11.glPopMatrix();
/* 196 */         GL11.glMatrixMode(5890);
/* 197 */         GL11.glPushMatrix();
/* 198 */         GL11.glLoadIdentity();
/* 199 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 200 */         GL11.glScalef(f6, f6, f6);
/* 201 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 202 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 203 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 204 */         GL11.glTranslatef(-f1, -f3, -f2);
/*     */         
/*     */ 
/* 207 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74591_c * f5 / f9, -f2);
/* 208 */         Tessellator tessellator = Tessellator.field_78398_a;
/*     */         
/* 210 */         tessellator.func_78382_b();
/* 211 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 212 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 213 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 214 */         if (i == 0)
/*     */         {
/* 216 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 219 */         tessellator.func_78380_c(180);
/* 220 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 222 */         tessellator.func_78377_a(x, y + offset, z);
/* 223 */         tessellator.func_78377_a(x, y + offset, z + 1.0D);
/* 224 */         tessellator.func_78377_a(x + 1.0D, y + offset, z + 1.0D);
/* 225 */         tessellator.func_78377_a(x + 1.0D, y + offset, z);
/*     */         
/* 227 */         tessellator.func_78381_a();
/* 228 */         GL11.glPopMatrix();
/* 229 */         GL11.glMatrixMode(5888);
/*     */       }
/* 231 */     } else { GL11.glPushMatrix();
/* 232 */       UtilsFX.bindTexture(this.t3);
/* 233 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 234 */       tessellator.func_78382_b();
/*     */       
/* 236 */       tessellator.func_78380_c(180);
/* 237 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 239 */       tessellator.func_78374_a(x, y + offset, z, 1.0D, 1.0D);
/* 240 */       tessellator.func_78374_a(x, y + offset, z + 1.0D, 1.0D, 0.0D);
/* 241 */       tessellator.func_78374_a(x + 1.0D, y + offset, z + 1.0D, 0.0D, 0.0D);
/* 242 */       tessellator.func_78374_a(x + 1.0D, y + offset, z, 0.0D, 1.0D);
/*     */       
/* 244 */       tessellator.func_78381_a();
/* 245 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 248 */     GL11.glDisable(3042);
/* 249 */     GL11.glDisable(3168);
/* 250 */     GL11.glDisable(3169);
/* 251 */     GL11.glDisable(3170);
/* 252 */     GL11.glDisable(3171);
/* 253 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneZNeg(TileHole tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 258 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 259 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 260 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 261 */     GL11.glDisable(2896);
/* 262 */     Random random = new Random(31100L);
/* 263 */     float offset = 0.001F;
/* 264 */     if (this.inrange) {
/* 265 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 267 */         GL11.glPushMatrix();
/* 268 */         float f5 = 16 - i;
/* 269 */         float f6 = 0.0625F;
/* 270 */         float f7 = 1.0F / (f5 + 1.0F);
/* 271 */         if (i == 0)
/*     */         {
/* 273 */           UtilsFX.bindTexture(this.t1);
/* 274 */           f7 = 0.1F;
/* 275 */           f5 = 65.0F;
/* 276 */           f6 = 0.125F;
/* 277 */           GL11.glEnable(3042);
/* 278 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 280 */         if (i == 1)
/*     */         {
/* 282 */           UtilsFX.bindTexture(this.t2);
/* 283 */           GL11.glEnable(3042);
/* 284 */           GL11.glBlendFunc(1, 1);
/* 285 */           f6 = 0.5F;
/*     */         }
/* 287 */         float f8 = (float)-(z + offset);
/* 288 */         float f9 = f8 + ActiveRenderInfo.field_74591_c;
/* 289 */         float f10 = f8 + f5 + ActiveRenderInfo.field_74591_c;
/* 290 */         float f11 = f9 / f10;
/* 291 */         f11 = (float)(z + offset) + f11;
/* 292 */         GL11.glTranslatef(px, py, f11);
/* 293 */         GL11.glTexGeni(8192, 9472, 9217);
/* 294 */         GL11.glTexGeni(8193, 9472, 9217);
/* 295 */         GL11.glTexGeni(8194, 9472, 9217);
/* 296 */         GL11.glTexGeni(8195, 9472, 9216);
/* 297 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 298 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 299 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */         
/* 301 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 302 */         GL11.glEnable(3168);
/* 303 */         GL11.glEnable(3169);
/* 304 */         GL11.glEnable(3170);
/* 305 */         GL11.glEnable(3171);
/* 306 */         GL11.glPopMatrix();
/* 307 */         GL11.glMatrixMode(5890);
/* 308 */         GL11.glPushMatrix();
/* 309 */         GL11.glLoadIdentity();
/* 310 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 311 */         GL11.glScalef(f6, f6, f6);
/* 312 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 313 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 314 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 315 */         GL11.glTranslatef(-px, -py, -pz);
/*     */         
/*     */ 
/* 318 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -pz);
/*     */         
/*     */ 
/* 321 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 322 */         tessellator.func_78382_b();
/* 323 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 324 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 325 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 326 */         if (i == 0)
/*     */         {
/* 328 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 331 */         tessellator.func_78380_c(180);
/* 332 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 334 */         tessellator.func_78377_a(x, y + 1.0D, z + offset);
/* 335 */         tessellator.func_78377_a(x, y, z + offset);
/* 336 */         tessellator.func_78377_a(x + 1.0D, y, z + offset);
/* 337 */         tessellator.func_78377_a(x + 1.0D, y + 1.0D, z + offset);
/*     */         
/* 339 */         tessellator.func_78381_a();
/* 340 */         GL11.glPopMatrix();
/* 341 */         GL11.glMatrixMode(5888);
/*     */       }
/* 343 */     } else { GL11.glPushMatrix();
/* 344 */       UtilsFX.bindTexture(this.t3);
/* 345 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 346 */       tessellator.func_78382_b();
/*     */       
/* 348 */       tessellator.func_78380_c(180);
/* 349 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 351 */       tessellator.func_78374_a(x, y + 1.0D, z + offset, 1.0D, 1.0D);
/* 352 */       tessellator.func_78374_a(x, y, z + offset, 1.0D, 0.0D);
/* 353 */       tessellator.func_78374_a(x + 1.0D, y, z + offset, 0.0D, 0.0D);
/* 354 */       tessellator.func_78374_a(x + 1.0D, y + 1.0D, z + offset, 0.0D, 1.0D);
/*     */       
/* 356 */       tessellator.func_78381_a();
/* 357 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 360 */     GL11.glDisable(3042);
/* 361 */     GL11.glDisable(3168);
/* 362 */     GL11.glDisable(3169);
/* 363 */     GL11.glDisable(3170);
/* 364 */     GL11.glDisable(3171);
/* 365 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneZPos(TileHole tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 370 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 371 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 372 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 373 */     GL11.glDisable(2896);
/* 374 */     Random random = new Random(31100L);
/* 375 */     float offset = 0.999F;
/* 376 */     if (this.inrange) {
/* 377 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 379 */         GL11.glPushMatrix();
/* 380 */         float f5 = 16 - i;
/* 381 */         float f6 = 0.0625F;
/* 382 */         float f7 = 1.0F / (f5 + 1.0F);
/* 383 */         if (i == 0)
/*     */         {
/* 385 */           UtilsFX.bindTexture(this.t1);
/* 386 */           f7 = 0.1F;
/* 387 */           f5 = 65.0F;
/* 388 */           f6 = 0.125F;
/* 389 */           GL11.glEnable(3042);
/* 390 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 392 */         if (i == 1)
/*     */         {
/* 394 */           UtilsFX.bindTexture(this.t2);
/* 395 */           GL11.glEnable(3042);
/* 396 */           GL11.glBlendFunc(1, 1);
/* 397 */           f6 = 0.5F;
/*     */         }
/* 399 */         float f8 = (float)(z + offset);
/* 400 */         float f9 = f8 - ActiveRenderInfo.field_74591_c;
/* 401 */         float f10 = f8 + f5 - ActiveRenderInfo.field_74591_c;
/* 402 */         float f11 = f9 / f10;
/* 403 */         f11 = (float)(z + offset) + f11;
/* 404 */         GL11.glTranslatef(px, py, f11);
/* 405 */         GL11.glTexGeni(8192, 9472, 9217);
/* 406 */         GL11.glTexGeni(8193, 9472, 9217);
/* 407 */         GL11.glTexGeni(8194, 9472, 9217);
/* 408 */         GL11.glTexGeni(8195, 9472, 9216);
/* 409 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 410 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 411 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */         
/* 413 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 414 */         GL11.glEnable(3168);
/* 415 */         GL11.glEnable(3169);
/* 416 */         GL11.glEnable(3170);
/* 417 */         GL11.glEnable(3171);
/* 418 */         GL11.glPopMatrix();
/* 419 */         GL11.glMatrixMode(5890);
/* 420 */         GL11.glPushMatrix();
/* 421 */         GL11.glLoadIdentity();
/* 422 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 423 */         GL11.glScalef(f6, f6, f6);
/* 424 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 425 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 426 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 427 */         GL11.glTranslatef(-px, -py, -pz);
/*     */         
/*     */ 
/* 430 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -pz);
/*     */         
/* 432 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 433 */         tessellator.func_78382_b();
/* 434 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 435 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 436 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 437 */         if (i == 0)
/*     */         {
/* 439 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 442 */         tessellator.func_78380_c(180);
/* 443 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 445 */         tessellator.func_78377_a(x, y, z + offset);
/* 446 */         tessellator.func_78377_a(x, y + 1.0D, z + offset);
/* 447 */         tessellator.func_78377_a(x + 1.0D, y + 1.0D, z + offset);
/* 448 */         tessellator.func_78377_a(x + 1.0D, y, z + offset);
/*     */         
/* 450 */         tessellator.func_78381_a();
/* 451 */         GL11.glPopMatrix();
/* 452 */         GL11.glMatrixMode(5888);
/*     */       }
/* 454 */     } else { GL11.glPushMatrix();
/* 455 */       UtilsFX.bindTexture(this.t3);
/* 456 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 457 */       tessellator.func_78382_b();
/*     */       
/* 459 */       tessellator.func_78380_c(180);
/* 460 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 462 */       tessellator.func_78374_a(x, y, z + offset, 1.0D, 1.0D);
/* 463 */       tessellator.func_78374_a(x, y + 1.0D, z + offset, 1.0D, 0.0D);
/* 464 */       tessellator.func_78374_a(x + 1.0D, y + 1.0D, z + offset, 0.0D, 0.0D);
/* 465 */       tessellator.func_78374_a(x + 1.0D, y, z + offset, 0.0D, 1.0D);
/*     */       
/* 467 */       tessellator.func_78381_a();
/* 468 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 471 */     GL11.glDisable(3042);
/* 472 */     GL11.glDisable(3168);
/* 473 */     GL11.glDisable(3169);
/* 474 */     GL11.glDisable(3170);
/* 475 */     GL11.glDisable(3171);
/* 476 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXNeg(TileHole tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 481 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 482 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 483 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 484 */     GL11.glDisable(2896);
/* 485 */     Random random = new Random(31100L);
/* 486 */     float offset = 0.001F;
/* 487 */     if (this.inrange) {
/* 488 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 490 */         GL11.glPushMatrix();
/* 491 */         float f5 = 16 - i;
/* 492 */         float f6 = 0.0625F;
/* 493 */         float f7 = 1.0F / (f5 + 1.0F);
/* 494 */         if (i == 0)
/*     */         {
/* 496 */           UtilsFX.bindTexture(this.t1);
/* 497 */           f7 = 0.1F;
/* 498 */           f5 = 65.0F;
/* 499 */           f6 = 0.125F;
/* 500 */           GL11.glEnable(3042);
/* 501 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 503 */         if (i == 1)
/*     */         {
/* 505 */           UtilsFX.bindTexture(this.t2);
/* 506 */           GL11.glEnable(3042);
/* 507 */           GL11.glBlendFunc(1, 1);
/* 508 */           f6 = 0.5F;
/*     */         }
/* 510 */         float f8 = (float)-(x + offset);
/* 511 */         float f9 = f8 + ActiveRenderInfo.field_74592_a;
/* 512 */         float f10 = f8 + f5 + ActiveRenderInfo.field_74592_a;
/* 513 */         float f11 = f9 / f10;
/* 514 */         f11 = (float)(x + offset) + f11;
/* 515 */         GL11.glTranslatef(f11, py, pz);
/* 516 */         GL11.glTexGeni(8192, 9472, 9217);
/* 517 */         GL11.glTexGeni(8193, 9472, 9217);
/* 518 */         GL11.glTexGeni(8194, 9472, 9217);
/* 519 */         GL11.glTexGeni(8195, 9472, 9216);
/*     */         
/*     */ 
/* 522 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 523 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 524 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 525 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 526 */         GL11.glEnable(3168);
/* 527 */         GL11.glEnable(3169);
/* 528 */         GL11.glEnable(3170);
/* 529 */         GL11.glEnable(3171);
/* 530 */         GL11.glPopMatrix();
/* 531 */         GL11.glMatrixMode(5890);
/* 532 */         GL11.glPushMatrix();
/* 533 */         GL11.glLoadIdentity();
/* 534 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 535 */         GL11.glScalef(f6, f6, f6);
/* 536 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 537 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 538 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 539 */         GL11.glTranslatef(-pz, -py, -px);
/*     */         
/*     */ 
/* 542 */         GL11.glTranslatef(ActiveRenderInfo.field_74591_c * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -px);
/*     */         
/*     */ 
/* 545 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 546 */         tessellator.func_78382_b();
/* 547 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 548 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 549 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 550 */         if (i == 0)
/*     */         {
/* 552 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 555 */         tessellator.func_78380_c(180);
/* 556 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 558 */         tessellator.func_78377_a(x + offset, y + 1.0D, z);
/* 559 */         tessellator.func_78377_a(x + offset, y + 1.0D, z + 1.0D);
/* 560 */         tessellator.func_78377_a(x + offset, y, z + 1.0D);
/* 561 */         tessellator.func_78377_a(x + offset, y, z);
/*     */         
/* 563 */         tessellator.func_78381_a();
/* 564 */         GL11.glPopMatrix();
/* 565 */         GL11.glMatrixMode(5888);
/*     */       }
/* 567 */     } else { GL11.glPushMatrix();
/* 568 */       UtilsFX.bindTexture(this.t3);
/* 569 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 570 */       tessellator.func_78382_b();
/*     */       
/* 572 */       tessellator.func_78380_c(180);
/* 573 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 575 */       tessellator.func_78374_a(x + offset, y + 1.0D, z, 1.0D, 1.0D);
/* 576 */       tessellator.func_78374_a(x + offset, y + 1.0D, z + 1.0D, 1.0D, 0.0D);
/* 577 */       tessellator.func_78374_a(x + offset, y, z + 1.0D, 0.0D, 0.0D);
/* 578 */       tessellator.func_78374_a(x + offset, y, z, 0.0D, 1.0D);
/*     */       
/* 580 */       tessellator.func_78381_a();
/* 581 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 584 */     GL11.glDisable(3042);
/* 585 */     GL11.glDisable(3168);
/* 586 */     GL11.glDisable(3169);
/* 587 */     GL11.glDisable(3170);
/* 588 */     GL11.glDisable(3171);
/* 589 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXPos(TileHole tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 594 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 595 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 596 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 597 */     GL11.glDisable(2896);
/* 598 */     Random random = new Random(31100L);
/* 599 */     float offset = 0.999F;
/* 600 */     if (this.inrange) {
/* 601 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 603 */         GL11.glPushMatrix();
/* 604 */         float f5 = 16 - i;
/* 605 */         float f6 = 0.0625F;
/* 606 */         float f7 = 1.0F / (f5 + 1.0F);
/* 607 */         if (i == 0)
/*     */         {
/* 609 */           UtilsFX.bindTexture(this.t1);
/* 610 */           f7 = 0.1F;
/* 611 */           f5 = 65.0F;
/* 612 */           f6 = 0.125F;
/* 613 */           GL11.glEnable(3042);
/* 614 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 616 */         if (i == 1)
/*     */         {
/* 618 */           UtilsFX.bindTexture(this.t2);
/* 619 */           GL11.glEnable(3042);
/* 620 */           GL11.glBlendFunc(1, 1);
/* 621 */           f6 = 0.5F;
/*     */         }
/* 623 */         float f8 = (float)(x + offset);
/* 624 */         float f9 = f8 - ActiveRenderInfo.field_74592_a;
/* 625 */         float f10 = f8 + f5 - ActiveRenderInfo.field_74592_a;
/* 626 */         float f11 = f9 / f10;
/* 627 */         f11 = (float)(x + offset) + f11;
/* 628 */         GL11.glTranslatef(f11, py, pz);
/* 629 */         GL11.glTexGeni(8192, 9472, 9217);
/* 630 */         GL11.glTexGeni(8193, 9472, 9217);
/* 631 */         GL11.glTexGeni(8194, 9472, 9217);
/* 632 */         GL11.glTexGeni(8195, 9472, 9216);
/*     */         
/*     */ 
/* 635 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 636 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 637 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 638 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 639 */         GL11.glEnable(3168);
/* 640 */         GL11.glEnable(3169);
/* 641 */         GL11.glEnable(3170);
/* 642 */         GL11.glEnable(3171);
/* 643 */         GL11.glPopMatrix();
/* 644 */         GL11.glMatrixMode(5890);
/* 645 */         GL11.glPushMatrix();
/* 646 */         GL11.glLoadIdentity();
/* 647 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 648 */         GL11.glScalef(f6, f6, f6);
/* 649 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 650 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 651 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 652 */         GL11.glTranslatef(-pz, -py, -px);
/*     */         
/*     */ 
/* 655 */         GL11.glTranslatef(ActiveRenderInfo.field_74591_c * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -px);
/*     */         
/*     */ 
/* 658 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 659 */         tessellator.func_78382_b();
/* 660 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 661 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 662 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 663 */         if (i == 0)
/*     */         {
/* 665 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 668 */         tessellator.func_78380_c(180);
/* 669 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 671 */         tessellator.func_78377_a(x + offset, y, z);
/* 672 */         tessellator.func_78377_a(x + offset, y, z + 1.0D);
/* 673 */         tessellator.func_78377_a(x + offset, y + 1.0D, z + 1.0D);
/* 674 */         tessellator.func_78377_a(x + offset, y + 1.0D, z);
/*     */         
/* 676 */         tessellator.func_78381_a();
/* 677 */         GL11.glPopMatrix();
/* 678 */         GL11.glMatrixMode(5888);
/*     */       }
/* 680 */     } else { GL11.glPushMatrix();
/* 681 */       UtilsFX.bindTexture(this.t3);
/* 682 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 683 */       tessellator.func_78382_b();
/*     */       
/* 685 */       tessellator.func_78380_c(180);
/* 686 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 688 */       tessellator.func_78374_a(x + offset, y, z, 1.0D, 1.0D);
/* 689 */       tessellator.func_78374_a(x + offset, y, z + 1.0D, 1.0D, 0.0D);
/* 690 */       tessellator.func_78374_a(x + offset, y + 1.0D, z + 1.0D, 0.0D, 0.0D);
/* 691 */       tessellator.func_78374_a(x + offset, y + 1.0D, z, 0.0D, 1.0D);
/*     */       
/* 693 */       tessellator.func_78381_a();
/* 694 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 697 */     GL11.glDisable(3042);
/* 698 */     GL11.glDisable(3168);
/* 699 */     GL11.glDisable(3169);
/* 700 */     GL11.glDisable(3170);
/* 701 */     GL11.glDisable(3171);
/* 702 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   private FloatBuffer calcFloatBuffer(float f, float f1, float f2, float f3)
/*     */   {
/* 707 */     this.fBuffer.clear();
/* 708 */     this.fBuffer.put(f).put(f1).put(f2).put(f3);
/* 709 */     this.fBuffer.flip();
/* 710 */     return this.fBuffer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_147500_a(TileEntity te, double x, double y, double z, float f)
/*     */   {
/* 717 */     this.inrange = (Minecraft.func_71410_x().field_71451_h.func_70092_e(te.field_145851_c + 0.5D, te.field_145848_d + 0.5D, te.field_145849_e + 0.5D) < 512.0D);
/* 718 */     GL11.glDisable(2912);
/* 719 */     if ((te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d + 1, te.field_145849_e).func_149662_c()) && (te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d + 1, te.field_145849_e) != ConfigBlocks.blockHole))
/*     */     {
/* 721 */       drawPlaneYPos((TileHole)te, x, y, z, f);
/*     */     }
/* 723 */     if ((te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d - 1, te.field_145849_e).func_149662_c()) && (te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d - 1, te.field_145849_e) != ConfigBlocks.blockHole))
/*     */     {
/* 725 */       drawPlaneYNeg((TileHole)te, x, y, z, f);
/*     */     }
/* 727 */     if ((te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e - 1).func_149662_c()) && (te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e - 1) != ConfigBlocks.blockHole))
/*     */     {
/* 729 */       drawPlaneZNeg((TileHole)te, x, y, z, f);
/*     */     }
/* 731 */     if ((te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e + 1).func_149662_c()) && (te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e + 1) != ConfigBlocks.blockHole))
/*     */     {
/* 733 */       drawPlaneZPos((TileHole)te, x, y, z, f);
/*     */     }
/* 735 */     if ((te.func_145831_w().func_147439_a(te.field_145851_c - 1, te.field_145848_d, te.field_145849_e).func_149662_c()) && (te.func_145831_w().func_147439_a(te.field_145851_c - 1, te.field_145848_d, te.field_145849_e) != ConfigBlocks.blockHole))
/*     */     {
/* 737 */       drawPlaneXNeg((TileHole)te, x, y, z, f);
/*     */     }
/* 739 */     if ((te.func_145831_w().func_147439_a(te.field_145851_c + 1, te.field_145848_d, te.field_145849_e).func_149662_c()) && (te.func_145831_w().func_147439_a(te.field_145851_c + 1, te.field_145848_d, te.field_145849_e) != ConfigBlocks.blockHole))
/*     */     {
/* 741 */       drawPlaneXPos((TileHole)te, x, y, z, f); }
/* 742 */     GL11.glEnable(2912);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileHoleRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */