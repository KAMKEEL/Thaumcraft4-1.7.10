/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.ModelCube;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.tiles.TileEldritchLock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEldritchLockRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   FloatBuffer fBuffer;
/*     */   private boolean inrange;
/*  34 */   private ModelCube model = new ModelCube(0);
/*     */   
/*     */   public TileEldritchLockRenderer()
/*     */   {
/*  38 */     this.fBuffer = GLAllocation.func_74529_h(16);
/*     */   }
/*     */   
/*  41 */   private String t1 = "textures/misc/tunnel.png";
/*  42 */   private String t2 = "textures/misc/particlefield.png";
/*  43 */   private String t3 = "textures/misc/particlefield32.png";
/*     */   
/*  45 */   ItemStack is = null;
/*  46 */   EntityItem entityitem = null;
/*     */   
/*     */ 
/*     */   public void func_147500_a(TileEntity te, double x, double y, double z, float f)
/*     */   {
/*  51 */     this.inrange = (Minecraft.func_71410_x().field_71451_h.func_70092_e(te.field_145851_c + 0.5D, te.field_145848_d + 0.5D, te.field_145849_e + 0.5D) < 512.0D);
/*     */     
/*     */ 
/*  54 */     if (this.is == null) { this.is = new ItemStack(ConfigItems.itemEldritchObject, 1, 2);
/*     */     }
/*  56 */     float bob = 0.0F;
/*  57 */     float count = Minecraft.func_71410_x().field_71451_h.field_70173_aa + f;
/*     */     
/*  59 */     GL11.glPushMatrix();
/*  60 */     UtilsFX.bindTexture("textures/models/eldritch_cube.png");
/*  61 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*  62 */     ForgeDirection dir = ForgeDirection.getOrientation(((TileEldritchLock)te).getFacing());
/*  63 */     for (int u = 0; u < 4; u++) {
/*  64 */       GL11.glPushMatrix();
/*  65 */       GL11.glRotated(90 * u, dir.offsetX, dir.offsetY, dir.offsetZ);
/*  66 */       for (int a = 1; a < 5 - (((TileEldritchLock)te).count + u * 5) / 20; a++) {
/*  67 */         GL11.glPushMatrix();
/*  68 */         GL11.glTranslated(0.0D, 0.25F + 0.5F * a, 0.0D);
/*  69 */         float w = MathHelper.func_76126_a((count + a * 10 + u * 20) / 20.0F) * 0.1F;
/*  70 */         if ((a == 1) || (a == 4)) w = w / 2.0F + 0.2F;
/*  71 */         GL11.glScaled(0.5D + w, 0.5D, 0.5D + w);
/*  72 */         this.model.render();
/*  73 */         GL11.glPopMatrix();
/*     */       }
/*  75 */       GL11.glPopMatrix();
/*     */     }
/*  77 */     GL11.glPopMatrix();
/*     */     
/*  79 */     if (((TileEldritchLock)te).count >= 0) {
/*  80 */       GL11.glPushMatrix();
/*  81 */       GL11.glTranslatef((float)x + 0.5F + dir.offsetX * 0.525F, (float)y + 0.285F, (float)z + 0.5F + dir.offsetZ * 0.525F);
/*  82 */       switch (dir.ordinal()) {
/*  83 */       case 5:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/*  84 */       case 4:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/*  85 */       case 2:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*  87 */       GL11.glScaled(1.0D, 1.0D, 1.0D);
/*  88 */       if (this.entityitem == null)
/*  89 */         this.entityitem = new EntityItem(te.func_145831_w(), 0.0D, 0.0D, 0.0D, this.is);
/*  90 */       this.entityitem.field_70290_d = 0.0F;
/*     */       
/*  92 */       net.minecraft.client.renderer.entity.RenderItem.field_82407_g = true;
/*  93 */       RenderManager.field_78727_a.func_147940_a(this.entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  94 */       net.minecraft.client.renderer.entity.RenderItem.field_82407_g = false;
/*  95 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*  98 */     GL11.glPushMatrix();
/*  99 */     GL11.glDisable(2912);
/* 100 */     switch (((TileEldritchLock)te).getFacing()) {
/* 101 */     case 2:  drawPlaneZNeg(x, y, z, f, 3); break;
/* 102 */     case 3:  drawPlaneZPos(x, y, z, f, 3); break;
/* 103 */     case 4:  drawPlaneXNeg(x, y, z, f, 3); break;
/* 104 */     case 5:  drawPlaneXPos(x, y, z, f, 3);
/*     */     }
/* 106 */     GL11.glEnable(2912);
/* 107 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawPlaneZPos(double x, double y, double z, float f, int height)
/*     */   {
/* 116 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 117 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 118 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 119 */     GL11.glDisable(2896);
/* 120 */     Random random = new Random(31100L);
/* 121 */     float offset = 0.5F;
/* 122 */     if (this.inrange) {
/* 123 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 125 */         GL11.glPushMatrix();
/* 126 */         float f5 = 16 - i;
/* 127 */         float f6 = 0.0625F;
/* 128 */         float f7 = 1.0F / (f5 + 1.0F);
/* 129 */         if (i == 0)
/*     */         {
/* 131 */           UtilsFX.bindTexture(this.t1);
/* 132 */           f7 = 0.1F;
/* 133 */           f5 = 65.0F;
/* 134 */           f6 = 0.125F;
/* 135 */           GL11.glEnable(3042);
/* 136 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 138 */         if (i == 1)
/*     */         {
/* 140 */           UtilsFX.bindTexture(this.t2);
/* 141 */           GL11.glEnable(3042);
/* 142 */           GL11.glBlendFunc(1, 1);
/* 143 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 146 */         float f8 = (float)-(z + offset);
/* 147 */         float f9 = f8 + ActiveRenderInfo.field_74591_c;
/* 148 */         float f10 = f8 + f5 + ActiveRenderInfo.field_74591_c;
/* 149 */         float f11 = f9 / f10;
/* 150 */         f11 = (float)(z + offset) + f11;
/* 151 */         GL11.glTranslatef(px, py, f11);
/* 152 */         GL11.glTexGeni(8192, 9472, 9217);
/* 153 */         GL11.glTexGeni(8193, 9472, 9217);
/* 154 */         GL11.glTexGeni(8194, 9472, 9217);
/* 155 */         GL11.glTexGeni(8195, 9472, 9216);
/* 156 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 157 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 158 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */         
/* 160 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 161 */         GL11.glEnable(3168);
/* 162 */         GL11.glEnable(3169);
/* 163 */         GL11.glEnable(3170);
/* 164 */         GL11.glEnable(3171);
/* 165 */         GL11.glPopMatrix();
/* 166 */         GL11.glMatrixMode(5890);
/* 167 */         GL11.glPushMatrix();
/* 168 */         GL11.glLoadIdentity();
/* 169 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 170 */         GL11.glScalef(f6, f6, f6);
/* 171 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 172 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 173 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 174 */         GL11.glTranslatef(-px, -py, -pz);
/*     */         
/*     */ 
/* 177 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -pz);
/*     */         
/*     */ 
/* 180 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 181 */         tessellator.func_78382_b();
/* 182 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 183 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 184 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 185 */         if (i == 0)
/*     */         {
/* 187 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 190 */         tessellator.func_78380_c(180);
/* 191 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 193 */         tessellator.func_78377_a(x - 2.0D, y + 3.0D, z + offset);
/* 194 */         tessellator.func_78377_a(x - 2.0D, y - 2.0D, z + offset);
/* 195 */         tessellator.func_78377_a(x + 3.0D, y - 2.0D, z + offset);
/* 196 */         tessellator.func_78377_a(x + 3.0D, y + 3.0D, z + offset);
/*     */         
/* 198 */         tessellator.func_78381_a();
/* 199 */         GL11.glPopMatrix();
/* 200 */         GL11.glMatrixMode(5888);
/*     */       }
/*     */     } else {
/* 203 */       GL11.glPushMatrix();
/* 204 */       UtilsFX.bindTexture(this.t3);
/* 205 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 206 */       tessellator.func_78382_b();
/*     */       
/* 208 */       tessellator.func_78380_c(180);
/* 209 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 211 */       tessellator.func_78374_a(x - 2.0D, y + 3.0D, z + offset, 1.0D, 1.0D);
/* 212 */       tessellator.func_78374_a(x - 2.0D, y - 2.0D, z + offset, 1.0D, 0.0D);
/* 213 */       tessellator.func_78374_a(x + 3.0D, y - 2.0D, z + offset, 0.0D, 0.0D);
/* 214 */       tessellator.func_78374_a(x + 3.0D, y + 3.0D, z + offset, 0.0D, 1.0D);
/*     */       
/* 216 */       tessellator.func_78381_a();
/* 217 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 220 */     GL11.glDisable(3042);
/* 221 */     GL11.glDisable(3168);
/* 222 */     GL11.glDisable(3169);
/* 223 */     GL11.glDisable(3170);
/* 224 */     GL11.glDisable(3171);
/* 225 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneZNeg(double x, double y, double z, float f, int height)
/*     */   {
/* 230 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 231 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 232 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 233 */     GL11.glDisable(2896);
/* 234 */     Random random = new Random(31100L);
/* 235 */     float offset = 0.5F;
/* 236 */     if (this.inrange) {
/* 237 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 239 */         GL11.glPushMatrix();
/* 240 */         float f5 = 16 - i;
/* 241 */         float f6 = 0.0625F;
/* 242 */         float f7 = 1.0F / (f5 + 1.0F);
/* 243 */         if (i == 0)
/*     */         {
/* 245 */           UtilsFX.bindTexture(this.t1);
/* 246 */           f7 = 0.1F;
/* 247 */           f5 = 65.0F;
/* 248 */           f6 = 0.125F;
/* 249 */           GL11.glEnable(3042);
/* 250 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 252 */         if (i == 1)
/*     */         {
/* 254 */           UtilsFX.bindTexture(this.t2);
/* 255 */           GL11.glEnable(3042);
/* 256 */           GL11.glBlendFunc(1, 1);
/* 257 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 260 */         float f8 = (float)(z + offset);
/* 261 */         float f9 = f8 - ActiveRenderInfo.field_74591_c;
/* 262 */         float f10 = f8 + f5 - ActiveRenderInfo.field_74591_c;
/* 263 */         float f11 = f9 / f10;
/* 264 */         f11 = (float)(z + offset) + f11;
/* 265 */         GL11.glTranslatef(px, py, f11);
/* 266 */         GL11.glTexGeni(8192, 9472, 9217);
/* 267 */         GL11.glTexGeni(8193, 9472, 9217);
/* 268 */         GL11.glTexGeni(8194, 9472, 9217);
/* 269 */         GL11.glTexGeni(8195, 9472, 9216);
/* 270 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 271 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 272 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */         
/* 274 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 275 */         GL11.glEnable(3168);
/* 276 */         GL11.glEnable(3169);
/* 277 */         GL11.glEnable(3170);
/* 278 */         GL11.glEnable(3171);
/* 279 */         GL11.glPopMatrix();
/* 280 */         GL11.glMatrixMode(5890);
/* 281 */         GL11.glPushMatrix();
/* 282 */         GL11.glLoadIdentity();
/* 283 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 284 */         GL11.glScalef(f6, f6, f6);
/* 285 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 286 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 287 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 288 */         GL11.glTranslatef(-px, -py, -pz);
/*     */         
/*     */ 
/* 291 */         GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -pz);
/*     */         
/* 293 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 294 */         tessellator.func_78382_b();
/* 295 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 296 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 297 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 298 */         if (i == 0)
/*     */         {
/* 300 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 303 */         tessellator.func_78380_c(180);
/* 304 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 306 */         tessellator.func_78377_a(x - 2.0D, y - 2.0D, z + offset);
/* 307 */         tessellator.func_78377_a(x - 2.0D, y + 3.0D, z + offset);
/* 308 */         tessellator.func_78377_a(x + 3.0D, y + 3.0D, z + offset);
/* 309 */         tessellator.func_78377_a(x + 3.0D, y - 2.0D, z + offset);
/*     */         
/* 311 */         tessellator.func_78381_a();
/* 312 */         GL11.glPopMatrix();
/* 313 */         GL11.glMatrixMode(5888);
/*     */       }
/* 315 */     } else { GL11.glPushMatrix();
/* 316 */       UtilsFX.bindTexture(this.t3);
/* 317 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 318 */       tessellator.func_78382_b();
/*     */       
/* 320 */       tessellator.func_78380_c(180);
/* 321 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 323 */       tessellator.func_78374_a(x - 2.0D, y - 2.0D, z + offset, 1.0D, 1.0D);
/* 324 */       tessellator.func_78374_a(x - 2.0D, y + 3.0D, z + offset, 1.0D, 0.0D);
/* 325 */       tessellator.func_78374_a(x + 3.0D, y + 3.0D, z + offset, 0.0D, 0.0D);
/* 326 */       tessellator.func_78374_a(x + 3.0D, y - 2.0D, z + offset, 0.0D, 1.0D);
/*     */       
/* 328 */       tessellator.func_78381_a();
/* 329 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 332 */     GL11.glDisable(3042);
/* 333 */     GL11.glDisable(3168);
/* 334 */     GL11.glDisable(3169);
/* 335 */     GL11.glDisable(3170);
/* 336 */     GL11.glDisable(3171);
/* 337 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXPos(double x, double y, double z, float f, int height)
/*     */   {
/* 342 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 343 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 344 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 345 */     GL11.glDisable(2896);
/* 346 */     Random random = new Random(31100L);
/* 347 */     float offset = 0.5F;
/* 348 */     if (this.inrange) {
/* 349 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 351 */         GL11.glPushMatrix();
/* 352 */         float f5 = 16 - i;
/* 353 */         float f6 = 0.0625F;
/* 354 */         float f7 = 1.0F / (f5 + 1.0F);
/* 355 */         if (i == 0)
/*     */         {
/* 357 */           UtilsFX.bindTexture(this.t1);
/* 358 */           f7 = 0.1F;
/* 359 */           f5 = 65.0F;
/* 360 */           f6 = 0.125F;
/* 361 */           GL11.glEnable(3042);
/* 362 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 364 */         if (i == 1)
/*     */         {
/* 366 */           UtilsFX.bindTexture(this.t2);
/* 367 */           GL11.glEnable(3042);
/* 368 */           GL11.glBlendFunc(1, 1);
/* 369 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 372 */         float f8 = (float)-(x + offset);
/* 373 */         float f9 = f8 + ActiveRenderInfo.field_74592_a;
/* 374 */         float f10 = f8 + f5 + ActiveRenderInfo.field_74592_a;
/* 375 */         float f11 = f9 / f10;
/* 376 */         f11 = (float)(x + offset) + f11;
/* 377 */         GL11.glTranslatef(f11, py, pz);
/* 378 */         GL11.glTexGeni(8192, 9472, 9217);
/* 379 */         GL11.glTexGeni(8193, 9472, 9217);
/* 380 */         GL11.glTexGeni(8194, 9472, 9217);
/* 381 */         GL11.glTexGeni(8195, 9472, 9216);
/*     */         
/*     */ 
/* 384 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 385 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 386 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 387 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 388 */         GL11.glEnable(3168);
/* 389 */         GL11.glEnable(3169);
/* 390 */         GL11.glEnable(3170);
/* 391 */         GL11.glEnable(3171);
/* 392 */         GL11.glPopMatrix();
/* 393 */         GL11.glMatrixMode(5890);
/* 394 */         GL11.glPushMatrix();
/* 395 */         GL11.glLoadIdentity();
/* 396 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 397 */         GL11.glScalef(f6, f6, f6);
/* 398 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 399 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 400 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 401 */         GL11.glTranslatef(-pz, -py, -px);
/*     */         
/*     */ 
/* 404 */         GL11.glTranslatef(ActiveRenderInfo.field_74591_c * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -px);
/*     */         
/*     */ 
/* 407 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 408 */         tessellator.func_78382_b();
/* 409 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 410 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 411 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 412 */         if (i == 0)
/*     */         {
/* 414 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 417 */         tessellator.func_78380_c(180);
/* 418 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 420 */         tessellator.func_78377_a(x + offset, y + 3.0D, z - 2.0D);
/* 421 */         tessellator.func_78377_a(x + offset, y + 3.0D, z + 3.0D);
/* 422 */         tessellator.func_78377_a(x + offset, y - 2.0D, z + 3.0D);
/* 423 */         tessellator.func_78377_a(x + offset, y - 2.0D, z - 2.0D);
/*     */         
/* 425 */         tessellator.func_78381_a();
/* 426 */         GL11.glPopMatrix();
/* 427 */         GL11.glMatrixMode(5888);
/*     */       }
/* 429 */     } else { GL11.glPushMatrix();
/* 430 */       UtilsFX.bindTexture(this.t3);
/* 431 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 432 */       tessellator.func_78382_b();
/*     */       
/* 434 */       tessellator.func_78380_c(180);
/* 435 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 437 */       tessellator.func_78374_a(x + offset, y + 3.0D, z - 2.0D, 1.0D, 1.0D);
/* 438 */       tessellator.func_78374_a(x + offset, y + 3.0D, z + 3.0D, 1.0D, 0.0D);
/* 439 */       tessellator.func_78374_a(x + offset, y - 2.0D, z + 3.0D, 0.0D, 0.0D);
/* 440 */       tessellator.func_78374_a(x + offset, y - 2.0D, z - 2.0D, 0.0D, 1.0D);
/*     */       
/* 442 */       tessellator.func_78381_a();
/* 443 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 446 */     GL11.glDisable(3042);
/* 447 */     GL11.glDisable(3168);
/* 448 */     GL11.glDisable(3169);
/* 449 */     GL11.glDisable(3170);
/* 450 */     GL11.glDisable(3171);
/* 451 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXNeg(double x, double y, double z, float f, int height)
/*     */   {
/* 456 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 457 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 458 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 459 */     GL11.glDisable(2896);
/* 460 */     Random random = new Random(31100L);
/* 461 */     float offset = 0.5F;
/* 462 */     if (this.inrange) {
/* 463 */       for (int i = 0; i < 16; i++)
/*     */       {
/* 465 */         GL11.glPushMatrix();
/* 466 */         float f5 = 16 - i;
/* 467 */         float f6 = 0.0625F;
/* 468 */         float f7 = 1.0F / (f5 + 1.0F);
/* 469 */         if (i == 0)
/*     */         {
/* 471 */           UtilsFX.bindTexture(this.t1);
/* 472 */           f7 = 0.1F;
/* 473 */           f5 = 65.0F;
/* 474 */           f6 = 0.125F;
/* 475 */           GL11.glEnable(3042);
/* 476 */           GL11.glBlendFunc(770, 771);
/*     */         }
/* 478 */         if (i == 1)
/*     */         {
/* 480 */           UtilsFX.bindTexture(this.t2);
/* 481 */           GL11.glEnable(3042);
/* 482 */           GL11.glBlendFunc(1, 1);
/* 483 */           f6 = 0.5F;
/*     */         }
/*     */         
/* 486 */         float f8 = (float)(x + offset);
/* 487 */         float f9 = f8 - ActiveRenderInfo.field_74592_a;
/* 488 */         float f10 = f8 + f5 - ActiveRenderInfo.field_74592_a;
/* 489 */         float f11 = f9 / f10;
/* 490 */         f11 = (float)(x + offset) + f11;
/* 491 */         GL11.glTranslatef(f11, py, pz);
/* 492 */         GL11.glTexGeni(8192, 9472, 9217);
/* 493 */         GL11.glTexGeni(8193, 9472, 9217);
/* 494 */         GL11.glTexGeni(8194, 9472, 9217);
/* 495 */         GL11.glTexGeni(8195, 9472, 9216);
/*     */         
/*     */ 
/* 498 */         GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 499 */         GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 500 */         GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 501 */         GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 502 */         GL11.glEnable(3168);
/* 503 */         GL11.glEnable(3169);
/* 504 */         GL11.glEnable(3170);
/* 505 */         GL11.glEnable(3171);
/* 506 */         GL11.glPopMatrix();
/* 507 */         GL11.glMatrixMode(5890);
/* 508 */         GL11.glPushMatrix();
/* 509 */         GL11.glLoadIdentity();
/* 510 */         GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 511 */         GL11.glScalef(f6, f6, f6);
/* 512 */         GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 513 */         GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 514 */         GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 515 */         GL11.glTranslatef(-pz, -py, -px);
/*     */         
/*     */ 
/* 518 */         GL11.glTranslatef(ActiveRenderInfo.field_74591_c * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -px);
/*     */         
/*     */ 
/* 521 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 522 */         tessellator.func_78382_b();
/* 523 */         f11 = random.nextFloat() * 0.5F + 0.1F;
/* 524 */         float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 525 */         float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 526 */         if (i == 0)
/*     */         {
/* 528 */           f11 = f12 = f13 = 1.0F;
/*     */         }
/*     */         
/* 531 */         tessellator.func_78380_c(180);
/* 532 */         tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */         
/* 534 */         tessellator.func_78377_a(x + offset, y - 2.0D, z - 2.0D);
/* 535 */         tessellator.func_78377_a(x + offset, y - 2.0D, z + 3.0D);
/* 536 */         tessellator.func_78377_a(x + offset, y + 3.0D, z + 3.0D);
/* 537 */         tessellator.func_78377_a(x + offset, y + 3.0D, z - 2.0D);
/*     */         
/* 539 */         tessellator.func_78381_a();
/* 540 */         GL11.glPopMatrix();
/* 541 */         GL11.glMatrixMode(5888);
/*     */       }
/* 543 */     } else { GL11.glPushMatrix();
/* 544 */       UtilsFX.bindTexture(this.t3);
/* 545 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 546 */       tessellator.func_78382_b();
/*     */       
/* 548 */       tessellator.func_78380_c(180);
/* 549 */       tessellator.func_78369_a(0.5F, 0.5F, 0.5F, 1.0F);
/*     */       
/* 551 */       tessellator.func_78374_a(x + offset, y - 2.0D, z - 2.0D, 1.0D, 1.0D);
/* 552 */       tessellator.func_78374_a(x + offset, y - 2.0D, z + 3.0D, 1.0D, 0.0D);
/* 553 */       tessellator.func_78374_a(x + offset, y + 3.0D, z + 3.0D, 0.0D, 0.0D);
/* 554 */       tessellator.func_78374_a(x + offset, y + 3.0D, z - 2.0D, 0.0D, 1.0D);
/*     */       
/* 556 */       tessellator.func_78381_a();
/* 557 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 560 */     GL11.glDisable(3042);
/* 561 */     GL11.glDisable(3168);
/* 562 */     GL11.glDisable(3169);
/* 563 */     GL11.glDisable(3170);
/* 564 */     GL11.glDisable(3171);
/* 565 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   private FloatBuffer calcFloatBuffer(float f, float f1, float f2, float f3)
/*     */   {
/* 570 */     this.fBuffer.clear();
/* 571 */     this.fBuffer.put(f).put(f1).put(f2).put(f3);
/* 572 */     this.fBuffer.flip();
/* 573 */     return this.fBuffer;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileEldritchLockRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */