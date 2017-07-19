/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class FXSmokeTrail extends EntityFX
/*     */ {
/*     */   private Entity target;
/*     */   
/*     */   public FXSmokeTrail(World par1World, double x, double y, double z, Entity target, float r, float g, float b)
/*     */   {
/*  19 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
/*  20 */     this.field_70552_h = r;
/*  21 */     this.field_70553_i = g;
/*  22 */     this.field_70551_j = b;
/*  23 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.5F + 0.5F);
/*     */     
/*  25 */     this.target = target;
/*     */     
/*  27 */     double dx = target.field_70165_t - this.field_70165_t;
/*  28 */     double dy = target.field_70163_u - this.field_70163_u;
/*  29 */     double dz = target.field_70161_v - this.field_70161_v;
/*  30 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 3.0F);
/*  31 */     if (base < 1)
/*  32 */       base = 1;
/*  33 */     this.field_70547_e = (base / 2 + this.field_70146_Z.nextInt(base));
/*     */     
/*  35 */     float f3 = 0.1F;
/*  36 */     this.field_70159_w = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*  37 */     this.field_70181_x = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*  38 */     this.field_70179_y = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*     */     
/*  40 */     this.field_70545_g = 0.2F;
/*  41 */     this.field_70145_X = false;
/*     */     
/*  43 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  44 */     int visibleDistance = 64;
/*  45 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j)
/*  46 */       visibleDistance = 32;
/*  47 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) {
/*  48 */       this.field_70547_e = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  54 */     float bob = MathHelper.func_76126_a(this.field_70546_d / 3.0F) * 0.33F + 0.66F;
/*     */     
/*  56 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.33F);
/*  57 */     int part = (int)(1.0F + this.field_70546_d / this.field_70547_e * 4.0F);
/*     */     
/*  59 */     float var8 = part % 16 / 16.0F;
/*  60 */     float var9 = var8 + 0.0624375F;
/*  61 */     float var10 = part / 16 / 16.0F;
/*  62 */     float var11 = var10 + 0.0624375F;
/*  63 */     float var12 = 0.3F * this.field_70544_f * bob;
/*     */     
/*  65 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/*  67 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/*  69 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  71 */     float var16 = 1.0F;
/*     */     
/*  73 */     tessellator.func_78380_c(func_70070_b(f));
/*     */     
/*  75 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.33F);
/*     */     
/*  77 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/*     */     
/*     */ 
/*     */ 
/*  81 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/*     */     
/*     */ 
/*     */ 
/*  85 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/*     */     
/*     */ 
/*     */ 
/*  89 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/*  98 */     return 1;
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/* 102 */     this.field_70169_q = this.field_70165_t;
/* 103 */     this.field_70167_r = this.field_70163_u;
/* 104 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 106 */     if ((this.field_70546_d++ >= this.field_70547_e) || (func_70068_e(this.target) < 1.0D))
/*     */     {
/* 108 */       func_70106_y();
/* 109 */       return;
/*     */     }
/*     */     
/*     */ 
/* 113 */     if (!this.field_70145_X)
/* 114 */       pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 115 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 117 */     this.field_70159_w *= 0.985D;
/* 118 */     this.field_70181_x *= 0.985D;
/* 119 */     this.field_70179_y *= 0.985D;
/*     */     
/* 121 */     double dx = this.target.field_70165_t - this.field_70165_t;
/* 122 */     double dy = this.target.field_70163_u - this.field_70163_u;
/* 123 */     double dz = this.target.field_70161_v - this.field_70161_v;
/* 124 */     double d13 = 0.3D;
/* 125 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 127 */     if (d11 < 4.0D) {
/* 128 */       this.field_70544_f *= 0.9F;
/* 129 */       d13 = 0.6D;
/*     */     }
/*     */     
/* 132 */     dx /= d11;
/* 133 */     dy /= d11;
/* 134 */     dz /= d11;
/*     */     
/* 136 */     this.field_70159_w += dx * d13;
/* 137 */     this.field_70181_x += dy * d13;
/* 138 */     this.field_70179_y += dz * d13;
/*     */     
/* 140 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.35F, 0.35F);
/* 141 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.35F, 0.35F);
/* 142 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.35F, 0.35F);
/*     */   }
/*     */   
/*     */   public void setGravity(float value) {
/* 146 */     this.field_70545_g = value;
/*     */   }
/*     */   
/*     */   protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
/* 150 */     int var7 = MathHelper.func_76128_c(par1);
/* 151 */     int var8 = MathHelper.func_76128_c(par3);
/* 152 */     int var9 = MathHelper.func_76128_c(par5);
/* 153 */     double var10 = par1 - var7;
/* 154 */     double var12 = par3 - var8;
/* 155 */     double var14 = par5 - var9;
/*     */     
/* 157 */     if ((!this.field_70170_p.func_147437_c(var7, var8, var9)) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */     {
/* 159 */       boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, true);
/*     */       
/* 161 */       boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, true);
/*     */       
/* 163 */       boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, true);
/*     */       
/* 165 */       boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, true);
/*     */       
/* 167 */       boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, true);
/*     */       
/* 169 */       boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, true);
/*     */       
/* 171 */       byte var22 = -1;
/* 172 */       double var23 = 9999.0D;
/*     */       
/* 174 */       if ((var16) && (var10 < var23)) {
/* 175 */         var23 = var10;
/* 176 */         var22 = 0;
/*     */       }
/*     */       
/* 179 */       if ((var17) && (1.0D - var10 < var23)) {
/* 180 */         var23 = 1.0D - var10;
/* 181 */         var22 = 1;
/*     */       }
/*     */       
/* 184 */       if ((var18) && (var12 < var23)) {
/* 185 */         var23 = var12;
/* 186 */         var22 = 2;
/*     */       }
/*     */       
/* 189 */       if ((var19) && (1.0D - var12 < var23)) {
/* 190 */         var23 = 1.0D - var12;
/* 191 */         var22 = 3;
/*     */       }
/*     */       
/* 194 */       if ((var20) && (var14 < var23)) {
/* 195 */         var23 = var14;
/* 196 */         var22 = 4;
/*     */       }
/*     */       
/* 199 */       if ((var21) && (1.0D - var14 < var23)) {
/* 200 */         var23 = 1.0D - var14;
/* 201 */         var22 = 5;
/*     */       }
/*     */       
/* 204 */       float var25 = this.field_70146_Z.nextFloat() * 0.05F + 0.025F;
/* 205 */       float var26 = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*     */       
/* 207 */       if (var22 == 0) {
/* 208 */         this.field_70159_w = (-var25);
/* 209 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 212 */       if (var22 == 1) {
/* 213 */         this.field_70159_w = var25;
/* 214 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 217 */       if (var22 == 2) {
/* 218 */         this.field_70181_x = (-var25);
/* 219 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 222 */       if (var22 == 3) {
/* 223 */         this.field_70181_x = var25;
/* 224 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 227 */       if (var22 == 4) {
/* 228 */         this.field_70179_y = (-var25);
/* 229 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 232 */       if (var22 == 5) {
/* 233 */         this.field_70179_y = var25;
/* 234 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 237 */       return true;
/*     */     }
/* 239 */     return false;
/*     */   }
/*     */   
/*     */ 
/* 243 */   public int particle = 24;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXSmokeTrail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */