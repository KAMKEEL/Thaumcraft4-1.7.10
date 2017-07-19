/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.blocks.BlockMirror;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileMirror;
/*     */ import thaumcraft.common.tiles.TileMirrorEssentia;
/*     */ 
/*     */ 
/*     */ public class TileMirrorRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   FloatBuffer fBuffer;
/*     */   
/*     */   public TileMirrorRenderer()
/*     */   {
/*  35 */     this.fBuffer = GLAllocation.func_74529_h(16);
/*     */   }
/*     */   
/*  38 */   private String t1 = "textures/misc/tunnel.png";
/*  39 */   private String t2 = "textures/misc/particlefield.png";
/*     */   
/*     */   public void drawPlaneYPos(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/*  43 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/*  44 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/*  45 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/*  46 */     GL11.glDisable(2896);
/*  47 */     Random random = new Random(31100L);
/*  48 */     float offset = 0.99F;
/*  49 */     float p = 0.1875F;
/*  50 */     for (int i = 0; i < 16; i++)
/*     */     {
/*  52 */       GL11.glPushMatrix();
/*  53 */       float f5 = 16 - i;
/*  54 */       float f6 = 0.0625F;
/*  55 */       float f7 = 1.0F / (f5 + 1.0F);
/*  56 */       if (i == 0)
/*     */       {
/*  58 */         UtilsFX.bindTexture(this.t1);
/*  59 */         f7 = 0.1F;
/*  60 */         f5 = 65.0F;
/*  61 */         f6 = 0.125F;
/*  62 */         GL11.glEnable(3042);
/*  63 */         GL11.glBlendFunc(770, 771);
/*     */       }
/*  65 */       if (i == 1)
/*     */       {
/*  67 */         UtilsFX.bindTexture(this.t2);
/*  68 */         GL11.glEnable(3042);
/*  69 */         GL11.glBlendFunc(1, 1);
/*  70 */         f6 = 0.5F;
/*     */       }
/*  72 */       float f8 = (float)(y + offset);
/*  73 */       float f9 = f8 - ActiveRenderInfo.field_74590_b;
/*  74 */       float f10 = f8 + f5 - ActiveRenderInfo.field_74590_b;
/*  75 */       float f11 = f9 / f10;
/*  76 */       f11 = (float)(y + offset) + f11;
/*  77 */       GL11.glTranslatef(px, f11, pz);
/*  78 */       GL11.glTexGeni(8192, 9472, 9217);
/*  79 */       GL11.glTexGeni(8193, 9472, 9217);
/*  80 */       GL11.glTexGeni(8194, 9472, 9217);
/*  81 */       GL11.glTexGeni(8195, 9472, 9216);
/*  82 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/*  83 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/*  84 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*  85 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/*  86 */       GL11.glEnable(3168);
/*  87 */       GL11.glEnable(3169);
/*  88 */       GL11.glEnable(3170);
/*  89 */       GL11.glEnable(3171);
/*  90 */       GL11.glPopMatrix();
/*  91 */       GL11.glMatrixMode(5890);
/*  92 */       GL11.glPushMatrix();
/*  93 */       GL11.glLoadIdentity();
/*  94 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/*  95 */       GL11.glScalef(f6, f6, f6);
/*  96 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/*  97 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/*  98 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/*  99 */       GL11.glTranslatef(-px, -pz, -py);
/*     */       
/*     */ 
/* 102 */       GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74591_c * f5 / f9, -py);
/*     */       
/* 104 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 105 */       tessellator.func_78382_b();
/* 106 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 107 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 108 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 109 */       if (i == 0)
/*     */       {
/* 111 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 114 */       tessellator.func_78380_c(180);
/* 115 */       tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */       
/* 117 */       tessellator.func_78377_a(x + p, y + offset, z + 1.0D - p);
/* 118 */       tessellator.func_78377_a(x + p, y + offset, z + p);
/* 119 */       tessellator.func_78377_a(x + 1.0D - p, y + offset, z + p);
/* 120 */       tessellator.func_78377_a(x + 1.0D - p, y + offset, z + 1.0D - p);
/*     */       
/* 122 */       tessellator.func_78381_a();
/* 123 */       GL11.glPopMatrix();
/* 124 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 127 */     GL11.glDisable(3042);
/* 128 */     GL11.glDisable(3168);
/* 129 */     GL11.glDisable(3169);
/* 130 */     GL11.glDisable(3170);
/* 131 */     GL11.glDisable(3171);
/* 132 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneYNeg(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 137 */     float f1 = (float)TileEntityRendererDispatcher.field_147554_b;
/* 138 */     float f2 = (float)TileEntityRendererDispatcher.field_147555_c;
/* 139 */     float f3 = (float)TileEntityRendererDispatcher.field_147552_d;
/* 140 */     GL11.glDisable(2896);
/* 141 */     Random random = new Random(31100L);
/* 142 */     float offset = 0.01F;
/* 143 */     float p = 0.1875F;
/* 144 */     for (int i = 0; i < 16; i++)
/*     */     {
/* 146 */       GL11.glPushMatrix();
/* 147 */       float f5 = 16 - i;
/* 148 */       float f6 = 0.0625F;
/* 149 */       float f7 = 1.0F / (f5 + 1.0F);
/* 150 */       if (i == 0)
/*     */       {
/* 152 */         UtilsFX.bindTexture(this.t1);
/* 153 */         f7 = 0.1F;
/* 154 */         f5 = 65.0F;
/* 155 */         f6 = 0.125F;
/* 156 */         GL11.glEnable(3042);
/* 157 */         GL11.glBlendFunc(770, 771);
/*     */       }
/* 159 */       if (i == 1)
/*     */       {
/* 161 */         UtilsFX.bindTexture(this.t2);
/* 162 */         GL11.glEnable(3042);
/* 163 */         GL11.glBlendFunc(1, 1);
/* 164 */         f6 = 0.5F;
/*     */       }
/* 166 */       float f8 = (float)-(y + offset);
/* 167 */       float f9 = f8 + ActiveRenderInfo.field_74590_b;
/* 168 */       float f10 = f8 + f5 + ActiveRenderInfo.field_74590_b;
/* 169 */       float f11 = f9 / f10;
/* 170 */       f11 = (float)(y + offset) + f11;
/* 171 */       GL11.glTranslatef(f1, f11, f3);
/* 172 */       GL11.glTexGeni(8192, 9472, 9217);
/* 173 */       GL11.glTexGeni(8193, 9472, 9217);
/* 174 */       GL11.glTexGeni(8194, 9472, 9217);
/* 175 */       GL11.glTexGeni(8195, 9472, 9216);
/* 176 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 177 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 178 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 179 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 180 */       GL11.glEnable(3168);
/* 181 */       GL11.glEnable(3169);
/* 182 */       GL11.glEnable(3170);
/* 183 */       GL11.glEnable(3171);
/* 184 */       GL11.glPopMatrix();
/* 185 */       GL11.glMatrixMode(5890);
/* 186 */       GL11.glPushMatrix();
/* 187 */       GL11.glLoadIdentity();
/* 188 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 189 */       GL11.glScalef(f6, f6, f6);
/* 190 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 191 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 192 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 193 */       GL11.glTranslatef(-f1, -f3, -f2);
/*     */       
/*     */ 
/* 196 */       GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74591_c * f5 / f9, -f2);
/* 197 */       Tessellator tessellator = Tessellator.field_78398_a;
/*     */       
/* 199 */       tessellator.func_78382_b();
/* 200 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 201 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 202 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 203 */       if (i == 0)
/*     */       {
/* 205 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 208 */       tessellator.func_78380_c(180);
/* 209 */       tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */       
/* 211 */       tessellator.func_78377_a(x + p, y + offset, z + p);
/* 212 */       tessellator.func_78377_a(x + p, y + offset, z + 1.0D - p);
/* 213 */       tessellator.func_78377_a(x + 1.0D - p, y + offset, z + 1.0D - p);
/* 214 */       tessellator.func_78377_a(x + 1.0D - p, y + offset, z + p);
/*     */       
/* 216 */       tessellator.func_78381_a();
/* 217 */       GL11.glPopMatrix();
/* 218 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 221 */     GL11.glDisable(3042);
/* 222 */     GL11.glDisable(3168);
/* 223 */     GL11.glDisable(3169);
/* 224 */     GL11.glDisable(3170);
/* 225 */     GL11.glDisable(3171);
/* 226 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneZNeg(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 231 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 232 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 233 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 234 */     GL11.glDisable(2896);
/* 235 */     Random random = new Random(31100L);
/* 236 */     float offset = 0.01F;
/* 237 */     float p = 0.1875F;
/* 238 */     for (int i = 0; i < 16; i++)
/*     */     {
/* 240 */       GL11.glPushMatrix();
/* 241 */       float f5 = 16 - i;
/* 242 */       float f6 = 0.0625F;
/* 243 */       float f7 = 1.0F / (f5 + 1.0F);
/* 244 */       if (i == 0)
/*     */       {
/* 246 */         UtilsFX.bindTexture(this.t1);
/* 247 */         f7 = 0.1F;
/* 248 */         f5 = 65.0F;
/* 249 */         f6 = 0.125F;
/* 250 */         GL11.glEnable(3042);
/* 251 */         GL11.glBlendFunc(770, 771);
/*     */       }
/* 253 */       if (i == 1)
/*     */       {
/* 255 */         UtilsFX.bindTexture(this.t2);
/* 256 */         GL11.glEnable(3042);
/* 257 */         GL11.glBlendFunc(1, 1);
/* 258 */         f6 = 0.5F;
/*     */       }
/* 260 */       float f8 = (float)-(z + offset);
/* 261 */       float f9 = f8 + ActiveRenderInfo.field_74591_c;
/* 262 */       float f10 = f8 + f5 + ActiveRenderInfo.field_74591_c;
/* 263 */       float f11 = f9 / f10;
/* 264 */       f11 = (float)(z + offset) + f11;
/* 265 */       GL11.glTranslatef(px, py, f11);
/* 266 */       GL11.glTexGeni(8192, 9472, 9217);
/* 267 */       GL11.glTexGeni(8193, 9472, 9217);
/* 268 */       GL11.glTexGeni(8194, 9472, 9217);
/* 269 */       GL11.glTexGeni(8195, 9472, 9216);
/* 270 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 271 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 272 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */       
/* 274 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 275 */       GL11.glEnable(3168);
/* 276 */       GL11.glEnable(3169);
/* 277 */       GL11.glEnable(3170);
/* 278 */       GL11.glEnable(3171);
/* 279 */       GL11.glPopMatrix();
/* 280 */       GL11.glMatrixMode(5890);
/* 281 */       GL11.glPushMatrix();
/* 282 */       GL11.glLoadIdentity();
/* 283 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 284 */       GL11.glScalef(f6, f6, f6);
/* 285 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 286 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 287 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 288 */       GL11.glTranslatef(-px, -py, -pz);
/*     */       
/*     */ 
/* 291 */       GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -pz);
/*     */       
/*     */ 
/* 294 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 295 */       tessellator.func_78382_b();
/* 296 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 297 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 298 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 299 */       if (i == 0)
/*     */       {
/* 301 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 304 */       tessellator.func_78380_c(180);
/* 305 */       tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */       
/* 307 */       tessellator.func_78377_a(x + p, y + 1.0D - p, z + offset);
/* 308 */       tessellator.func_78377_a(x + p, y + p, z + offset);
/* 309 */       tessellator.func_78377_a(x + 1.0D - p, y + p, z + offset);
/* 310 */       tessellator.func_78377_a(x + 1.0D - p, y + 1.0D - p, z + offset);
/*     */       
/* 312 */       tessellator.func_78381_a();
/* 313 */       GL11.glPopMatrix();
/* 314 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 317 */     GL11.glDisable(3042);
/* 318 */     GL11.glDisable(3168);
/* 319 */     GL11.glDisable(3169);
/* 320 */     GL11.glDisable(3170);
/* 321 */     GL11.glDisable(3171);
/* 322 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneZPos(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 327 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 328 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 329 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 330 */     GL11.glDisable(2896);
/* 331 */     Random random = new Random(31100L);
/* 332 */     float offset = 0.99F;
/* 333 */     float p = 0.1875F;
/* 334 */     for (int i = 0; i < 16; i++)
/*     */     {
/* 336 */       GL11.glPushMatrix();
/* 337 */       float f5 = 16 - i;
/* 338 */       float f6 = 0.0625F;
/* 339 */       float f7 = 1.0F / (f5 + 1.0F);
/* 340 */       if (i == 0)
/*     */       {
/* 342 */         UtilsFX.bindTexture(this.t1);
/* 343 */         f7 = 0.1F;
/* 344 */         f5 = 65.0F;
/* 345 */         f6 = 0.125F;
/* 346 */         GL11.glEnable(3042);
/* 347 */         GL11.glBlendFunc(770, 771);
/*     */       }
/* 349 */       if (i == 1)
/*     */       {
/* 351 */         UtilsFX.bindTexture(this.t2);
/* 352 */         GL11.glEnable(3042);
/* 353 */         GL11.glBlendFunc(1, 1);
/* 354 */         f6 = 0.5F;
/*     */       }
/* 356 */       float f8 = (float)(z + offset);
/* 357 */       float f9 = f8 - ActiveRenderInfo.field_74591_c;
/* 358 */       float f10 = f8 + f5 - ActiveRenderInfo.field_74591_c;
/* 359 */       float f11 = f9 / f10;
/* 360 */       f11 = (float)(z + offset) + f11;
/* 361 */       GL11.glTranslatef(px, py, f11);
/* 362 */       GL11.glTexGeni(8192, 9472, 9217);
/* 363 */       GL11.glTexGeni(8193, 9472, 9217);
/* 364 */       GL11.glTexGeni(8194, 9472, 9217);
/* 365 */       GL11.glTexGeni(8195, 9472, 9216);
/* 366 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 367 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 368 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */       
/* 370 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 371 */       GL11.glEnable(3168);
/* 372 */       GL11.glEnable(3169);
/* 373 */       GL11.glEnable(3170);
/* 374 */       GL11.glEnable(3171);
/* 375 */       GL11.glPopMatrix();
/* 376 */       GL11.glMatrixMode(5890);
/* 377 */       GL11.glPushMatrix();
/* 378 */       GL11.glLoadIdentity();
/* 379 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 380 */       GL11.glScalef(f6, f6, f6);
/* 381 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 382 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 383 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 384 */       GL11.glTranslatef(-px, -py, -pz);
/*     */       
/*     */ 
/* 387 */       GL11.glTranslatef(ActiveRenderInfo.field_74592_a * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -pz);
/*     */       
/* 389 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 390 */       tessellator.func_78382_b();
/* 391 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 392 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 393 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 394 */       if (i == 0)
/*     */       {
/* 396 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 399 */       tessellator.func_78380_c(180);
/* 400 */       tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */       
/* 402 */       tessellator.func_78377_a(x + p, y + p, z + offset);
/* 403 */       tessellator.func_78377_a(x + p, y + 1.0D - p, z + offset);
/* 404 */       tessellator.func_78377_a(x + 1.0D - p, y + 1.0D - p, z + offset);
/* 405 */       tessellator.func_78377_a(x + 1.0D - p, y + p, z + offset);
/*     */       
/* 407 */       tessellator.func_78381_a();
/* 408 */       GL11.glPopMatrix();
/* 409 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 412 */     GL11.glDisable(3042);
/* 413 */     GL11.glDisable(3168);
/* 414 */     GL11.glDisable(3169);
/* 415 */     GL11.glDisable(3170);
/* 416 */     GL11.glDisable(3171);
/* 417 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXNeg(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 422 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 423 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 424 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 425 */     GL11.glDisable(2896);
/* 426 */     Random random = new Random(31100L);
/* 427 */     float offset = 0.01F;
/* 428 */     float p = 0.1875F;
/* 429 */     for (int i = 0; i < 16; i++)
/*     */     {
/* 431 */       GL11.glPushMatrix();
/* 432 */       float f5 = 16 - i;
/* 433 */       float f6 = 0.0625F;
/* 434 */       float f7 = 1.0F / (f5 + 1.0F);
/* 435 */       if (i == 0)
/*     */       {
/* 437 */         UtilsFX.bindTexture(this.t1);
/* 438 */         f7 = 0.1F;
/* 439 */         f5 = 65.0F;
/* 440 */         f6 = 0.125F;
/* 441 */         GL11.glEnable(3042);
/* 442 */         GL11.glBlendFunc(770, 771);
/*     */       }
/* 444 */       if (i == 1)
/*     */       {
/* 446 */         UtilsFX.bindTexture(this.t2);
/* 447 */         GL11.glEnable(3042);
/* 448 */         GL11.glBlendFunc(1, 1);
/* 449 */         f6 = 0.5F;
/*     */       }
/* 451 */       float f8 = (float)-(x + offset);
/* 452 */       float f9 = f8 + ActiveRenderInfo.field_74592_a;
/* 453 */       float f10 = f8 + f5 + ActiveRenderInfo.field_74592_a;
/* 454 */       float f11 = f9 / f10;
/* 455 */       f11 = (float)(x + offset) + f11;
/* 456 */       GL11.glTranslatef(f11, py, pz);
/* 457 */       GL11.glTexGeni(8192, 9472, 9217);
/* 458 */       GL11.glTexGeni(8193, 9472, 9217);
/* 459 */       GL11.glTexGeni(8194, 9472, 9217);
/* 460 */       GL11.glTexGeni(8195, 9472, 9216);
/*     */       
/*     */ 
/* 463 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 464 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 465 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 466 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 467 */       GL11.glEnable(3168);
/* 468 */       GL11.glEnable(3169);
/* 469 */       GL11.glEnable(3170);
/* 470 */       GL11.glEnable(3171);
/* 471 */       GL11.glPopMatrix();
/* 472 */       GL11.glMatrixMode(5890);
/* 473 */       GL11.glPushMatrix();
/* 474 */       GL11.glLoadIdentity();
/* 475 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 476 */       GL11.glScalef(f6, f6, f6);
/* 477 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 478 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 479 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 480 */       GL11.glTranslatef(-pz, -py, -px);
/*     */       
/*     */ 
/* 483 */       GL11.glTranslatef(ActiveRenderInfo.field_74591_c * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -px);
/*     */       
/*     */ 
/* 486 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 487 */       tessellator.func_78382_b();
/* 488 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 489 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 490 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 491 */       if (i == 0)
/*     */       {
/* 493 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 496 */       tessellator.func_78380_c(180);
/* 497 */       tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */       
/* 499 */       tessellator.func_78377_a(x + offset, y + 1.0D - p, z + p);
/* 500 */       tessellator.func_78377_a(x + offset, y + 1.0D - p, z + 1.0D - p);
/* 501 */       tessellator.func_78377_a(x + offset, y + p, z + 1.0D - p);
/* 502 */       tessellator.func_78377_a(x + offset, y + p, z + p);
/*     */       
/* 504 */       tessellator.func_78381_a();
/* 505 */       GL11.glPopMatrix();
/* 506 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 509 */     GL11.glDisable(3042);
/* 510 */     GL11.glDisable(3168);
/* 511 */     GL11.glDisable(3169);
/* 512 */     GL11.glDisable(3170);
/* 513 */     GL11.glDisable(3171);
/* 514 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   public void drawPlaneXPos(TileEntity tileentityendportal, double x, double y, double z, float f)
/*     */   {
/* 519 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 520 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 521 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 522 */     GL11.glDisable(2896);
/* 523 */     Random random = new Random(31100L);
/* 524 */     float offset = 0.99F;
/* 525 */     float p = 0.1875F;
/* 526 */     for (int i = 0; i < 16; i++)
/*     */     {
/* 528 */       GL11.glPushMatrix();
/* 529 */       float f5 = 16 - i;
/* 530 */       float f6 = 0.0625F;
/* 531 */       float f7 = 1.0F / (f5 + 1.0F);
/* 532 */       if (i == 0)
/*     */       {
/* 534 */         UtilsFX.bindTexture(this.t1);
/* 535 */         f7 = 0.1F;
/* 536 */         f5 = 65.0F;
/* 537 */         f6 = 0.125F;
/* 538 */         GL11.glEnable(3042);
/* 539 */         GL11.glBlendFunc(770, 771);
/*     */       }
/* 541 */       if (i == 1)
/*     */       {
/* 543 */         UtilsFX.bindTexture(this.t2);
/* 544 */         GL11.glEnable(3042);
/* 545 */         GL11.glBlendFunc(1, 1);
/* 546 */         f6 = 0.5F;
/*     */       }
/* 548 */       float f8 = (float)(x + offset);
/* 549 */       float f9 = f8 - ActiveRenderInfo.field_74592_a;
/* 550 */       float f10 = f8 + f5 - ActiveRenderInfo.field_74592_a;
/* 551 */       float f11 = f9 / f10;
/* 552 */       f11 = (float)(x + offset) + f11;
/* 553 */       GL11.glTranslatef(f11, py, pz);
/* 554 */       GL11.glTexGeni(8192, 9472, 9217);
/* 555 */       GL11.glTexGeni(8193, 9472, 9217);
/* 556 */       GL11.glTexGeni(8194, 9472, 9217);
/* 557 */       GL11.glTexGeni(8195, 9472, 9216);
/*     */       
/*     */ 
/* 560 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 561 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 562 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 563 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 564 */       GL11.glEnable(3168);
/* 565 */       GL11.glEnable(3169);
/* 566 */       GL11.glEnable(3170);
/* 567 */       GL11.glEnable(3171);
/* 568 */       GL11.glPopMatrix();
/* 569 */       GL11.glMatrixMode(5890);
/* 570 */       GL11.glPushMatrix();
/* 571 */       GL11.glLoadIdentity();
/* 572 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 573 */       GL11.glScalef(f6, f6, f6);
/* 574 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 575 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 576 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 577 */       GL11.glTranslatef(-pz, -py, -px);
/*     */       
/*     */ 
/* 580 */       GL11.glTranslatef(ActiveRenderInfo.field_74591_c * f5 / f9, ActiveRenderInfo.field_74590_b * f5 / f9, -px);
/*     */       
/*     */ 
/* 583 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 584 */       tessellator.func_78382_b();
/* 585 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 586 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 587 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 588 */       if (i == 0)
/*     */       {
/* 590 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 593 */       tessellator.func_78380_c(180);
/* 594 */       tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/*     */       
/* 596 */       tessellator.func_78377_a(x + offset, y + p, z + p);
/* 597 */       tessellator.func_78377_a(x + offset, y + p, z + 1.0D - p);
/* 598 */       tessellator.func_78377_a(x + offset, y + 1.0D - p, z + 1.0D - p);
/* 599 */       tessellator.func_78377_a(x + offset, y + 1.0D - p, z + p);
/*     */       
/* 601 */       tessellator.func_78381_a();
/* 602 */       GL11.glPopMatrix();
/* 603 */       GL11.glMatrixMode(5888);
/*     */     }
/*     */     
/* 606 */     GL11.glDisable(3042);
/* 607 */     GL11.glDisable(3168);
/* 608 */     GL11.glDisable(3169);
/* 609 */     GL11.glDisable(3170);
/* 610 */     GL11.glDisable(3171);
/* 611 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/*     */   private FloatBuffer calcFloatBuffer(float f, float f1, float f2, float f3)
/*     */   {
/* 616 */     this.fBuffer.clear();
/* 617 */     this.fBuffer.put(f).put(f1).put(f2).put(f3);
/* 618 */     this.fBuffer.flip();
/* 619 */     return this.fBuffer;
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity te, double x, double y, double z, float f)
/*     */   {
/* 624 */     ForgeDirection dir = ForgeDirection.getOrientation(te.func_145832_p() % 6);
/* 625 */     boolean linked = false;
/* 626 */     float instability = 0.0F;
/* 627 */     if ((te instanceof TileMirror)) {
/* 628 */       linked = ((TileMirror)te).linked;
/* 629 */       if (((TileMirror)te).instability > 0)
/* 630 */         instability = Minecraft.func_71410_x().field_71441_e.field_73012_v.nextFloat() * (((TileMirror)te).instability / 10000.0F);
/*     */     }
/* 632 */     if ((te instanceof TileMirrorEssentia)) linked = ((TileMirrorEssentia)te).linked;
/* 633 */     int b = ConfigBlocks.blockMirror.func_149677_c(te.func_145831_w(), te.field_145851_c, te.field_145848_d, te.field_145849_e);
/*     */     
/*     */ 
/* 636 */     if ((linked) && (UtilsFX.isVisibleTo(1.5F, FMLClientHandler.instance().getClient().field_71439_g, te.field_145851_c + 0.5D, te.field_145848_d + 0.5D, te.field_145849_e + 0.5D))) {
/* 637 */       GL11.glPushMatrix();
/* 638 */       switch (dir) {
/* 639 */       case DOWN:  drawPlaneYPos(te, x, y, z, f); break;
/* 640 */       case UP:  drawPlaneYNeg(te, x, y, z, f); break;
/* 641 */       case WEST:  drawPlaneXPos(te, x, y, z, f); break;
/* 642 */       case EAST:  drawPlaneXNeg(te, x, y, z, f); break;
/* 643 */       case NORTH:  drawPlaneZPos(te, x, y, z, f); break;
/* 644 */       case SOUTH:  drawPlaneZNeg(te, x, y, z, f);
/*     */       }
/* 646 */       GL11.glPopMatrix();
/* 647 */       GL11.glPushMatrix();
/* 648 */       translateFromOrientation((float)x, (float)y, (float)z, dir.ordinal(), 0.02F + instability);
/* 649 */       UtilsFX.renderQuadFromTexture("textures/blocks/mirrorpanetrans.png", 1, 0, 1.0F, 1.0F, 1.0F, 1.0F, b, 771, 1.0F);
/* 650 */       GL11.glPopMatrix();
/*     */     } else {
/* 652 */       GL11.glPushMatrix();
/* 653 */       translateFromOrientation((float)x, (float)y, (float)z, dir.ordinal(), 0.02F + instability);
/* 654 */       UtilsFX.renderQuadFromTexture("textures/blocks/mirrorpane.png", 1, 0, 1.0F, 1.0F, 1.0F, 1.0F, b, 771, 1.0F);
/* 655 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 658 */     GL11.glPushMatrix();
/* 659 */     translateFromOrientation((float)x, (float)y, (float)z, dir.ordinal(), 0.0F);
/* 660 */     IIcon icon = ((BlockMirror)ConfigBlocks.blockMirror).func_149691_a(0, te.func_145832_p());
/* 661 */     float f1 = icon.func_94212_f();
/* 662 */     float f2 = icon.func_94206_g();
/* 663 */     float f3 = icon.func_94209_e();
/* 664 */     float f4 = icon.func_94210_h();
/* 665 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 666 */     this.field_147501_a.field_147553_e.func_110577_a(TextureMap.field_110575_b);
/* 667 */     ItemRenderer.func_78439_a(tessellator, f1, f2, f3, f4, icon.func_94211_a(), icon.func_94216_b(), 0.0625F);
/*     */     
/* 669 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void translateFromOrientation(float x, float y, float z, int orientation, float off)
/*     */   {
/* 674 */     if (orientation == 0)
/*     */     {
/* 676 */       GL11.glTranslatef(x, y + 1.0F, z + 1.0F);
/* 677 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*     */     }
/* 679 */     else if (orientation == 1)
/*     */     {
/* 681 */       GL11.glTranslatef(x, y, z);
/* 682 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 683 */     } else if (orientation == 2)
/*     */     {
/* 685 */       GL11.glTranslatef(x, y, z + 1.0F);
/* 686 */     } else if (orientation == 3)
/*     */     {
/* 688 */       GL11.glTranslatef(x + 1.0F, y, z);
/* 689 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 690 */     } else if (orientation == 4)
/*     */     {
/* 692 */       GL11.glTranslatef(x + 1.0F, y, z + 1.0F);
/* 693 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 694 */     } else if (orientation == 5)
/*     */     {
/* 696 */       GL11.glTranslatef(x, y, z);
/* 697 */       GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */     }
/* 699 */     GL11.glTranslatef(0.0F, 0.0F, -off);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileMirrorRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */