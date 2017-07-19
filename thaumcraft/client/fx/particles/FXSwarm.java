/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ public class FXSwarm extends EntityFX
/*     */ {
/*     */   private Entity target;
/*  21 */   private float turnSpeed = 10.0F;
/*  22 */   private float speed = 0.2F;
/*     */   
/*     */   public FXSwarm(World par1World, double x, double y, double z, Entity target, float r, float g, float b)
/*     */   {
/*  26 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
/*  27 */     this.field_70552_h = r;
/*  28 */     this.field_70553_i = g;
/*  29 */     this.field_70551_j = b;
/*  30 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.5F + 1.0F);
/*     */     
/*  32 */     this.target = target;
/*     */     
/*  34 */     float f3 = 0.2F;
/*  35 */     this.field_70159_w = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*  36 */     this.field_70181_x = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*  37 */     this.field_70179_y = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*     */     
/*  39 */     this.field_70545_g = 0.1F;
/*  40 */     this.field_70145_X = false;
/*     */     
/*  42 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  43 */     int visibleDistance = 64;
/*  44 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j)
/*  45 */       visibleDistance = 32;
/*  46 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) {
/*  47 */       this.field_70547_e = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public FXSwarm(World par1World, double x, double y, double z, Entity target, float r, float g, float b, float sp, float ts, float pg)
/*     */   {
/*  53 */     this(par1World, x, y, z, target, r, g, b);
/*  54 */     this.speed = sp;
/*     */     
/*  56 */     this.field_70545_g = pg;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  62 */     float bob = MathHelper.func_76126_a(this.field_70546_d / 3.0F) * 0.25F + 1.0F;
/*     */     
/*  64 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*  65 */     int part = 7 + this.field_70546_d % 8;
/*     */     
/*  67 */     float var8 = part / 16.0F;
/*  68 */     float var9 = var8 + 0.0624375F;
/*  69 */     float var10 = 0.25F;
/*  70 */     float var11 = var10 + 0.0624375F;
/*  71 */     float var12 = 0.1F * this.field_70544_f * bob;
/*     */     
/*  73 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/*  75 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/*  77 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  79 */     float var16 = 1.0F;
/*     */     
/*  81 */     float trans = (50.0F - this.deathtimer) / 50.0F;
/*     */     
/*  83 */     tessellator.func_78380_c(240);
/*  84 */     if (((this.target instanceof EntityLivingBase)) && (((EntityLivingBase)this.target).field_70737_aN <= 0))
/*     */     {
/*  86 */       tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, trans);
/*     */     }
/*     */     else
/*     */     {
/*  90 */       tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16 / 2.0F, this.field_70551_j * var16 / 2.0F, trans);
/*     */     }
/*     */     
/*  93 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/*     */     
/*     */ 
/*     */ 
/*  97 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/*     */     
/*     */ 
/*     */ 
/* 101 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/*     */     
/*     */ 
/*     */ 
/* 105 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 114 */     return 1;
/*     */   }
/*     */   
/* 117 */   int deathtimer = 0;
/*     */   
/*     */   public void func_70071_h_() {
/* 120 */     this.field_70169_q = this.field_70165_t;
/* 121 */     this.field_70167_r = this.field_70163_u;
/* 122 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 124 */     this.field_70546_d += 1;
/*     */     
/* 126 */     if ((this.target == null) || (this.target.field_70128_L) || (((this.target instanceof EntityLivingBase)) && (((EntityLivingBase)this.target).field_70725_aQ > 0)))
/*     */     {
/*     */ 
/* 129 */       this.deathtimer += 1;
/* 130 */       this.field_70181_x -= this.field_70545_g / 2.0F;
/* 131 */       if (this.deathtimer > 50) {
/* 132 */         func_70106_y();
/*     */       }
/*     */     } else {
/* 135 */       this.field_70181_x += this.field_70545_g;
/*     */     }
/*     */     
/* 138 */     pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 139 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 141 */     this.field_70159_w *= 0.985D;
/* 142 */     this.field_70181_x *= 0.985D;
/* 143 */     this.field_70179_y *= 0.985D;
/*     */     
/* 145 */     if ((this.target != null) && (!this.target.field_70128_L) && ((!(this.target instanceof EntityLivingBase)) || (((EntityLivingBase)this.target).field_70725_aQ <= 0)))
/*     */     {
/*     */ 
/* 148 */       boolean hurt = false;
/*     */       
/* 150 */       if ((this.target instanceof EntityLivingBase))
/* 151 */         hurt = ((EntityLivingBase)this.target).field_70737_aN > 0;
/* 152 */       if ((func_70068_e(this.target) > this.target.field_70130_N) && (!hurt)) {
/* 153 */         faceEntity(this.target, this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F)), this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F)));
/*     */       }
/*     */       else
/*     */       {
/* 157 */         faceEntity(this.target, -(this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F))), -(this.turnSpeed / 2.0F + this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0F))));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 163 */       this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/*     */       
/*     */ 
/* 166 */       this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/*     */       
/*     */ 
/* 169 */       this.field_70181_x = (-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F));
/*     */       
/* 171 */       setHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.speed, 15.0F);
/*     */     }
/*     */     
/* 174 */     if ((buzzcount.size() < 3) && (this.field_70146_Z.nextInt(50) == 0) && (this.field_70170_p.func_72890_a(this, 8.0D) != null))
/*     */     {
/* 176 */       this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:fly", 0.03F, 0.5F + this.field_70146_Z.nextFloat() * 0.4F, false);
/*     */       
/* 178 */       buzzcount.add(Long.valueOf(System.nanoTime() + 1500000L));
/*     */     }
/*     */     
/* 181 */     if ((buzzcount.size() >= 3) && (((Long)buzzcount.get(0)).longValue() < System.nanoTime()))
/* 182 */       buzzcount.remove(0);
/*     */   }
/*     */   
/* 185 */   private static ArrayList<Long> buzzcount = new ArrayList();
/*     */   
/*     */   public void faceEntity(Entity par1Entity, float par2, float par3) {
/* 188 */     double d0 = par1Entity.field_70165_t - this.field_70165_t;
/* 189 */     double d1 = par1Entity.field_70161_v - this.field_70161_v;
/* 190 */     double d2 = (par1Entity.field_70121_D.field_72338_b + par1Entity.field_70121_D.field_72337_e) / 2.0D - (this.field_70121_D.field_72338_b + this.field_70121_D.field_72337_e) / 2.0D;
/*     */     
/*     */ 
/* 193 */     double d3 = MathHelper.func_76133_a(d0 * d0 + d1 * d1);
/* 194 */     float f2 = (float)(Math.atan2(d1, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 195 */     float f3 = (float)-(Math.atan2(d2, d3) * 180.0D / 3.141592653589793D);
/* 196 */     this.field_70125_A = updateRotation(this.field_70125_A, f3, par3);
/* 197 */     this.field_70177_z = updateRotation(this.field_70177_z, f2, par2);
/*     */   }
/*     */   
/*     */   private float updateRotation(float par1, float par2, float par3) {
/* 201 */     float f3 = MathHelper.func_76142_g(par2 - par1);
/*     */     
/* 203 */     if (f3 > par3) {
/* 204 */       f3 = par3;
/*     */     }
/*     */     
/* 207 */     if (f3 < -par3) {
/* 208 */       f3 = -par3;
/*     */     }
/*     */     
/* 211 */     return par1 + f3;
/*     */   }
/*     */   
/*     */   public void setHeading(double par1, double par3, double par5, float par7, float par8)
/*     */   {
/* 216 */     float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/*     */     
/* 218 */     par1 /= f2;
/* 219 */     par3 /= f2;
/* 220 */     par5 /= f2;
/* 221 */     par1 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*     */     
/*     */ 
/* 224 */     par3 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*     */     
/*     */ 
/* 227 */     par5 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*     */     
/*     */ 
/* 230 */     par1 *= par7;
/* 231 */     par3 *= par7;
/* 232 */     par5 *= par7;
/* 233 */     this.field_70159_w = par1;
/* 234 */     this.field_70181_x = par3;
/* 235 */     this.field_70179_y = par5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
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
/* 251 */     if ((this.field_70170_p.func_147439_a(var7, var8, var9) != ConfigBlocks.blockTaintFibres) && (!this.field_70170_p.func_147437_c(var7, var8, var9)) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
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
/*     */ 
/* 338 */   public int particle = 40;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXSwarm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */