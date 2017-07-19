/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXSparkle extends EntityFX
/*     */ {
/*     */   public FXSparkle(World world, double d, double d1, double d2, float f, float f1, float f2, float f3, int m)
/*     */   {
/*  18 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*  19 */     if (f1 == 0.0F) {
/*  20 */       f1 = 1.0F;
/*     */     }
/*  22 */     this.field_70552_h = f1;
/*  23 */     this.field_70553_i = f2;
/*  24 */     this.field_70551_j = f3;
/*  25 */     this.field_70545_g = 0.0F;
/*  26 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  27 */     this.field_70544_f *= f;
/*  28 */     this.field_70547_e = (3 * m);
/*  29 */     this.multiplier = m;
/*  30 */     this.field_70145_X = false;
/*  31 */     func_70105_a(0.01F, 0.01F);
/*  32 */     this.field_70169_q = this.field_70165_t;
/*  33 */     this.field_70167_r = this.field_70163_u;
/*  34 */     this.field_70166_s = this.field_70161_v;
/*     */   }
/*     */   
/*     */   public FXSparkle(World world, double d, double d1, double d2, float f, int type, int m)
/*     */   {
/*  39 */     this(world, d, d1, d2, f, 0.0F, 0.0F, 0.0F, m);
/*  40 */     this.currentColor = type;
/*  41 */     switch (type) {
/*     */     case 0: 
/*  43 */       this.field_70552_h = (0.75F + world.field_73012_v.nextFloat() * 0.25F);
/*  44 */       this.field_70553_i = (0.25F + world.field_73012_v.nextFloat() * 0.25F);
/*  45 */       this.field_70551_j = (0.75F + world.field_73012_v.nextFloat() * 0.25F);
/*  46 */       break;
/*     */     case 1: 
/*  48 */       this.field_70552_h = (0.5F + world.field_73012_v.nextFloat() * 0.3F);
/*  49 */       this.field_70553_i = (0.5F + world.field_73012_v.nextFloat() * 0.3F);
/*  50 */       this.field_70551_j = 0.2F;
/*  51 */       break;
/*     */     case 2: 
/*  53 */       this.field_70552_h = 0.2F;
/*  54 */       this.field_70553_i = 0.2F;
/*  55 */       this.field_70551_j = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  56 */       break;
/*     */     case 3: 
/*  58 */       this.field_70552_h = 0.2F;
/*  59 */       this.field_70553_i = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  60 */       this.field_70551_j = 0.2F;
/*  61 */       break;
/*     */     case 4: 
/*  63 */       this.field_70552_h = (0.7F + world.field_73012_v.nextFloat() * 0.3F);
/*  64 */       this.field_70553_i = 0.2F;
/*  65 */       this.field_70551_j = 0.2F;
/*  66 */       break;
/*     */     case 5: 
/*  68 */       this.blendmode = 771;
/*  69 */       this.field_70552_h = (world.field_73012_v.nextFloat() * 0.1F);
/*  70 */       this.field_70553_i = (world.field_73012_v.nextFloat() * 0.1F);
/*  71 */       this.field_70551_j = (world.field_73012_v.nextFloat() * 0.1F);
/*  72 */       break;
/*     */     case 6: 
/*  74 */       this.field_70552_h = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  75 */       this.field_70553_i = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  76 */       this.field_70551_j = (0.8F + world.field_73012_v.nextFloat() * 0.2F);
/*  77 */       break;
/*     */     case 7: 
/*  79 */       this.field_70552_h = 0.2F;
/*  80 */       this.field_70553_i = (0.5F + world.field_73012_v.nextFloat() * 0.3F);
/*  81 */       this.field_70551_j = (0.6F + world.field_73012_v.nextFloat() * 0.3F);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   public FXSparkle(World world, double d, double d1, double d2, double x, double y, double z, float f, int type, int m)
/*     */   {
/*  89 */     this(world, d, d1, d2, f, type, m);
/*     */     
/*  91 */     double dx = x - this.field_70165_t;
/*  92 */     double dy = y - this.field_70163_u;
/*  93 */     double dz = z - this.field_70161_v;
/*     */     
/*  95 */     this.field_70159_w = (dx / this.field_70547_e);
/*  96 */     this.field_70181_x = (dy / this.field_70547_e);
/*  97 */     this.field_70179_y = (dz / this.field_70547_e);
/*     */   }
/*     */   
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 102 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/* 103 */     int part = this.particle + this.field_70546_d / this.multiplier;
/*     */     
/* 105 */     float var8 = part % 4 / 16.0F;
/* 106 */     float var9 = var8 + 0.0624375F;
/* 107 */     float var10 = 0.25F;
/* 108 */     float var11 = var10 + 0.0624375F;
/* 109 */     float var12 = 0.1F * this.field_70544_f;
/* 110 */     if (this.shrink)
/* 111 */       var12 *= (this.field_70547_e - this.field_70546_d + 1) / this.field_70547_e;
/* 112 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/* 114 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/* 116 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/* 118 */     float var16 = 1.0F;
/*     */     
/* 120 */     tessellator.func_78380_c(240);
/*     */     
/* 122 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F);
/*     */     
/* 124 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/*     */     
/*     */ 
/*     */ 
/* 128 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/*     */     
/*     */ 
/*     */ 
/* 132 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/*     */     
/*     */ 
/*     */ 
/* 136 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 145 */     return this.blendmode == 1 ? 0 : 1;
/*     */   }
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
/*     */   public void func_70071_h_()
/*     */   {
/* 160 */     this.field_70169_q = this.field_70165_t;
/* 161 */     this.field_70167_r = this.field_70163_u;
/* 162 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 164 */     if ((this.field_70546_d == 0) && (this.tinkle) && (this.field_70170_p.field_73012_v.nextInt(10) == 0)) {
/* 165 */       this.field_70170_p.func_72956_a(this, "random.orb", 0.02F, 0.7F * ((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.6F + 2.0F));
/*     */     }
/*     */     
/*     */ 
/* 169 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 171 */       func_70106_y();
/*     */     }
/*     */     
/* 174 */     this.field_70181_x -= 0.04D * this.field_70545_g;
/* 175 */     if (!this.field_70145_X) {
/* 176 */       pushOutOfBlocks(this.field_70165_t, (this.field_70121_D.field_72338_b + this.field_70121_D.field_72337_e) / 2.0D, this.field_70161_v);
/*     */     }
/*     */     
/*     */ 
/* 180 */     this.field_70165_t += this.field_70159_w;
/* 181 */     this.field_70163_u += this.field_70181_x;
/* 182 */     this.field_70161_v += this.field_70179_y;
/* 183 */     if (this.slowdown) {
/* 184 */       this.field_70159_w *= 0.9080000019073486D;
/* 185 */       this.field_70181_x *= 0.9080000019073486D;
/* 186 */       this.field_70179_y *= 0.9080000019073486D;
/* 187 */       if (this.field_70122_E) {
/* 188 */         this.field_70159_w *= 0.699999988079071D;
/* 189 */         this.field_70179_y *= 0.699999988079071D;
/*     */       }
/*     */     }
/*     */     
/* 193 */     if (this.leyLineEffect)
/*     */     {
/* 195 */       FXSparkle fx = new FXSparkle(this.field_70170_p, this.field_70169_q + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, this.field_70167_r + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, this.field_70166_s + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, 1.0F, this.currentColor, 3 + this.field_70170_p.field_73012_v.nextInt(3));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 202 */       fx.field_70145_X = true;
/* 203 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fx);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 209 */     this.field_70545_g = value;
/*     */   }
/*     */   
/*     */   protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
/* 213 */     int var7 = MathHelper.func_76128_c(par1);
/* 214 */     int var8 = MathHelper.func_76128_c(par3);
/* 215 */     int var9 = MathHelper.func_76128_c(par5);
/* 216 */     double var10 = par1 - var7;
/* 217 */     double var12 = par3 - var8;
/* 218 */     double var14 = par5 - var9;
/*     */     
/* 220 */     if (!this.field_70170_p.func_147437_c(var7, var8, var9)) {
/* 221 */       boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, true);
/*     */       
/* 223 */       boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, true);
/*     */       
/* 225 */       boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, true);
/*     */       
/* 227 */       boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, true);
/*     */       
/* 229 */       boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, true);
/*     */       
/* 231 */       boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, true);
/*     */       
/* 233 */       byte var22 = -1;
/* 234 */       double var23 = 9999.0D;
/*     */       
/* 236 */       if ((var16) && (var10 < var23)) {
/* 237 */         var23 = var10;
/* 238 */         var22 = 0;
/*     */       }
/*     */       
/* 241 */       if ((var17) && (1.0D - var10 < var23)) {
/* 242 */         var23 = 1.0D - var10;
/* 243 */         var22 = 1;
/*     */       }
/*     */       
/* 246 */       if ((var18) && (var12 < var23)) {
/* 247 */         var23 = var12;
/* 248 */         var22 = 2;
/*     */       }
/*     */       
/* 251 */       if ((var19) && (1.0D - var12 < var23)) {
/* 252 */         var23 = 1.0D - var12;
/* 253 */         var22 = 3;
/*     */       }
/*     */       
/* 256 */       if ((var20) && (var14 < var23)) {
/* 257 */         var23 = var14;
/* 258 */         var22 = 4;
/*     */       }
/*     */       
/* 261 */       if ((var21) && (1.0D - var14 < var23)) {
/* 262 */         var23 = 1.0D - var14;
/* 263 */         var22 = 5;
/*     */       }
/*     */       
/* 266 */       float var25 = this.field_70146_Z.nextFloat() * 0.05F + 0.025F;
/* 267 */       float var26 = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*     */       
/* 269 */       if (var22 == 0) {
/* 270 */         this.field_70159_w = (-var25);
/* 271 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 274 */       if (var22 == 1) {
/* 275 */         this.field_70159_w = var25;
/* 276 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 279 */       if (var22 == 2) {
/* 280 */         this.field_70181_x = (-var25);
/* 281 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 284 */       if (var22 == 3) {
/* 285 */         this.field_70181_x = var25;
/* 286 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 289 */       if (var22 == 4) {
/* 290 */         this.field_70179_y = (-var25);
/* 291 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 294 */       if (var22 == 5) {
/* 295 */         this.field_70179_y = var25;
/* 296 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 299 */       return true;
/*     */     }
/* 301 */     return false;
/*     */   }
/*     */   
/*     */ 
/* 305 */   public boolean leyLineEffect = false;
/* 306 */   public int multiplier = 2;
/* 307 */   public boolean shrink = true;
/* 308 */   public int particle = 16;
/* 309 */   public boolean tinkle = false;
/* 310 */   public int blendmode = 1;
/* 311 */   public boolean slowdown = true;
/* 312 */   public int currentColor = 0;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXSparkle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */