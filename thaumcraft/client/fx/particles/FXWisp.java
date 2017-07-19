/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class FXWisp extends EntityFX
/*     */ {
/*     */   public FXWisp(World world, double d, double d1, double d2, float f, float f1, float f2)
/*     */   {
/*  18 */     this(world, d, d1, d2, 1.0F, f, f1, f2);
/*     */   }
/*     */   
/*     */ 
/*  22 */   Entity target = null;
/*     */   
/*     */   public FXWisp(World world, double d, double d1, double d2, float f, float red, float green, float blue)
/*     */   {
/*  26 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*  27 */     if (red == 0.0F) {
/*  28 */       red = 1.0F;
/*     */     }
/*  30 */     this.field_70552_h = red;
/*  31 */     this.field_70553_i = green;
/*  32 */     this.field_70551_j = blue;
/*  33 */     this.field_70545_g = 0.0F;
/*  34 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  35 */     this.field_70544_f *= f;
/*  36 */     this.moteParticleScale = this.field_70544_f;
/*  37 */     this.field_70547_e = ((int)(36.0D / (Math.random() * 0.3D + 0.7D)));
/*  38 */     this.moteHalfLife = (this.field_70547_e / 2);
/*  39 */     this.field_70145_X = false;
/*  40 */     func_70105_a(0.1F, 0.1F);
/*  41 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  42 */     int visibleDistance = 50;
/*  43 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j)
/*  44 */       visibleDistance = 25;
/*  45 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance)
/*  46 */       this.field_70547_e = 0;
/*  47 */     this.field_70169_q = this.field_70165_t;
/*  48 */     this.field_70167_r = this.field_70163_u;
/*  49 */     this.field_70166_s = this.field_70161_v;
/*     */   }
/*     */   
/*     */   public FXWisp(World world, double d, double d1, double d2, float f, int type) {
/*  53 */     this(world, d, d1, d2, f, 0.0F, 0.0F, 0.0F);
/*     */     
/*  55 */     switch (type) {
/*     */     case 0: 
/*  57 */       this.field_70552_h = (0.75F + world.field_73012_v.nextFloat() * 0.25F);
/*  58 */       this.field_70553_i = (0.25F + world.field_73012_v.nextFloat() * 0.25F);
/*  59 */       this.field_70551_j = (0.75F + world.field_73012_v.nextFloat() * 0.25F);
/*  60 */       break;
/*     */     case 1: 
/*  62 */       this.field_70552_h = (0.5F + world.field_73012_v.nextFloat() * 0.3F);
/*  63 */       this.field_70553_i = (0.5F + world.field_73012_v.nextFloat() * 0.3F);
/*  64 */       this.field_70551_j = 0.2F;
/*  65 */       break;
/*     */     case 2: 
/*  67 */       this.field_70552_h = 0.2F;
/*  68 */       this.field_70553_i = 0.2F;
/*  69 */       this.field_70551_j = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  70 */       break;
/*     */     case 3: 
/*  72 */       this.field_70552_h = 0.2F;
/*  73 */       this.field_70553_i = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  74 */       this.field_70551_j = 0.2F;
/*  75 */       break;
/*     */     case 4: 
/*  77 */       this.field_70552_h = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  78 */       this.field_70553_i = 0.2F;
/*  79 */       this.field_70551_j = 0.2F;
/*  80 */       break;
/*     */     case 5: 
/*  82 */       this.blendmode = 771;
/*  83 */       this.field_70552_h = (world.field_73012_v.nextFloat() * 0.1F);
/*  84 */       this.field_70553_i = (world.field_73012_v.nextFloat() * 0.1F);
/*  85 */       this.field_70551_j = (world.field_73012_v.nextFloat() * 0.1F);
/*  86 */       break;
/*     */     case 6: 
/*  88 */       this.field_70552_h = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  89 */       this.field_70553_i = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  90 */       this.field_70551_j = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  91 */       break;
/*     */     case 7: 
/*  93 */       this.field_70552_h = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  94 */       this.field_70553_i = (0.5F + world.field_73012_v.nextFloat() * 0.2F);
/*  95 */       this.field_70551_j = (0.3F + world.field_73012_v.nextFloat() * 0.1F);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   public FXWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, int type)
/*     */   {
/* 103 */     this(world, d, d1, d2, f, type);
/* 104 */     if (this.field_70547_e > 0) {
/* 105 */       double dx = x - this.field_70165_t;
/* 106 */       double dy = y - this.field_70163_u;
/* 107 */       double dz = z - this.field_70161_v;
/*     */       
/* 109 */       this.field_70159_w = (dx / this.field_70547_e);
/* 110 */       this.field_70181_x = (dy / this.field_70547_e);
/* 111 */       this.field_70179_y = (dz / this.field_70547_e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public FXWisp(World world, double d, double d1, double d2, Entity tar, int type)
/*     */   {
/* 118 */     this(world, d, d1, d2, 0.4F, type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FXWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, float red, float green, float blue)
/*     */   {
/* 125 */     this(world, d, d1, d2, f, red, green, blue);
/* 126 */     if (this.field_70547_e > 0) {
/* 127 */       double dx = x - this.field_70165_t;
/* 128 */       double dy = y - this.field_70163_u;
/* 129 */       double dz = z - this.field_70161_v;
/*     */       
/* 131 */       this.field_70159_w = (dx / this.field_70547_e);
/* 132 */       this.field_70181_x = (dy / this.field_70547_e);
/* 133 */       this.field_70179_y = (dz / this.field_70547_e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 140 */     float agescale = 0.0F;
/* 141 */     if (this.shrink) {
/* 142 */       agescale = (this.field_70547_e - this.field_70546_d) / this.field_70547_e;
/*     */     }
/*     */     else {
/* 145 */       agescale = this.field_70546_d / this.moteHalfLife;
/* 146 */       if (agescale > 1.0F) {
/* 147 */         agescale = 2.0F - agescale;
/*     */       }
/*     */     }
/* 150 */     this.field_70544_f = (this.moteParticleScale * agescale);
/*     */     
/* 152 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*     */     
/* 154 */     float f10 = 0.5F * this.field_70544_f;
/* 155 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 156 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 157 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/* 159 */     float var8 = 0.0F;
/* 160 */     float var9 = 0.125F;
/* 161 */     float var10 = 0.875F;
/* 162 */     float var11 = 1.0F;
/*     */     
/* 164 */     tessellator.func_78380_c(240);
/*     */     
/* 166 */     tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5F);
/*     */     
/* 168 */     tessellator.func_78374_a(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, var9, var11);
/*     */     
/* 170 */     tessellator.func_78374_a(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10, var9, var10);
/*     */     
/* 172 */     tessellator.func_78374_a(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, var8, var10);
/*     */     
/* 174 */     tessellator.func_78374_a(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10, var8, var11);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 181 */     return this.blendmode == 1 ? 0 : 1;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 186 */     this.field_70169_q = this.field_70165_t;
/* 187 */     this.field_70167_r = this.field_70163_u;
/* 188 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 190 */     if ((this.field_70546_d == 0) && (this.tinkle) && (this.field_70170_p.field_73012_v.nextInt(3) == 0)) {
/* 191 */       this.field_70170_p.func_72956_a(this, "random.orb", 0.02F, 0.5F * ((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.6F + 2.0F));
/*     */     }
/*     */     
/*     */ 
/* 195 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 197 */       func_70106_y();
/*     */     }
/*     */     
/* 200 */     this.field_70181_x -= 0.04D * this.field_70545_g;
/*     */     
/* 202 */     if (!this.field_70145_X)
/* 203 */       pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 204 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 206 */     if (this.target != null)
/*     */     {
/*     */ 
/* 209 */       this.field_70159_w *= 0.985D;
/* 210 */       this.field_70181_x *= 0.985D;
/* 211 */       this.field_70179_y *= 0.985D;
/*     */       
/* 213 */       double dx = this.target.field_70165_t - this.field_70165_t;
/* 214 */       double dy = this.target.field_70163_u + this.target.field_70131_O / 2.0F - this.field_70163_u;
/* 215 */       double dz = this.target.field_70161_v - this.field_70161_v;
/* 216 */       double d13 = 0.2D;
/* 217 */       double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */       
/* 219 */       dx /= d11;
/* 220 */       dy /= d11;
/* 221 */       dz /= d11;
/*     */       
/* 223 */       this.field_70159_w += dx * d13;
/* 224 */       this.field_70181_x += dy * d13;
/* 225 */       this.field_70179_y += dz * d13;
/*     */       
/* 227 */       this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.2F, 0.2F);
/* 228 */       this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.2F, 0.2F);
/* 229 */       this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.2F, 0.2F);
/*     */     }
/*     */     else {
/* 232 */       this.field_70159_w *= 0.9800000190734863D;
/* 233 */       this.field_70181_x *= 0.9800000190734863D;
/* 234 */       this.field_70179_y *= 0.9800000190734863D;
/* 235 */       if (this.field_70122_E) {
/* 236 */         this.field_70159_w *= 0.699999988079071D;
/* 237 */         this.field_70179_y *= 0.699999988079071D;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean pushOutOfBlocks(double par1, double par3, double par5)
/*     */   {
/* 244 */     int var7 = MathHelper.func_76128_c(par1);
/* 245 */     int var8 = MathHelper.func_76128_c(par3);
/* 246 */     int var9 = MathHelper.func_76128_c(par5);
/* 247 */     double var10 = par1 - var7;
/* 248 */     double var12 = par3 - var8;
/* 249 */     double var14 = par5 - var9;
/*     */     
/* 251 */     if ((!this.field_70170_p.func_147437_c(var7, var8, var9)) && (this.field_70170_p.func_147445_c(var7, var8, var9, true)) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */     {
/*     */ 
/* 254 */       boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, true);
/*     */       
/* 256 */       boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, true);
/*     */       
/* 258 */       boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, true);
/*     */       
/* 260 */       boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, true);
/*     */       
/* 262 */       boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, true);
/*     */       
/* 264 */       boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, true);
/*     */       
/* 266 */       byte var22 = -1;
/* 267 */       double var23 = 9999.0D;
/*     */       
/* 269 */       if ((var16) && (var10 < var23)) {
/* 270 */         var23 = var10;
/* 271 */         var22 = 0;
/*     */       }
/*     */       
/* 274 */       if ((var17) && (1.0D - var10 < var23)) {
/* 275 */         var23 = 1.0D - var10;
/* 276 */         var22 = 1;
/*     */       }
/*     */       
/* 279 */       if ((var18) && (var12 < var23)) {
/* 280 */         var23 = var12;
/* 281 */         var22 = 2;
/*     */       }
/*     */       
/* 284 */       if ((var19) && (1.0D - var12 < var23)) {
/* 285 */         var23 = 1.0D - var12;
/* 286 */         var22 = 3;
/*     */       }
/*     */       
/* 289 */       if ((var20) && (var14 < var23)) {
/* 290 */         var23 = var14;
/* 291 */         var22 = 4;
/*     */       }
/*     */       
/* 294 */       if ((var21) && (1.0D - var14 < var23)) {
/* 295 */         var23 = 1.0D - var14;
/* 296 */         var22 = 5;
/*     */       }
/*     */       
/* 299 */       float var25 = this.field_70146_Z.nextFloat() * 0.05F + 0.025F;
/* 300 */       float var26 = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*     */       
/* 302 */       if (var22 == 0) {
/* 303 */         this.field_70159_w = (-var25);
/* 304 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 307 */       if (var22 == 1) {
/* 308 */         this.field_70159_w = var25;
/* 309 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 312 */       if (var22 == 2) {
/* 313 */         this.field_70181_x = (-var25);
/* 314 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 317 */       if (var22 == 3) {
/* 318 */         this.field_70181_x = var25;
/* 319 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 322 */       if (var22 == 4) {
/* 323 */         this.field_70179_y = (-var25);
/* 324 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 327 */       if (var22 == 5) {
/* 328 */         this.field_70179_y = var25;
/* 329 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 332 */       return true;
/*     */     }
/* 334 */     return false;
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 339 */     this.field_70545_g = value;
/*     */   }
/*     */   
/* 342 */   public boolean shrink = false;
/*     */   float moteParticleScale;
/*     */   int moteHalfLife;
/* 345 */   public boolean tinkle = false;
/* 346 */   public int blendmode = 1;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXWisp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */