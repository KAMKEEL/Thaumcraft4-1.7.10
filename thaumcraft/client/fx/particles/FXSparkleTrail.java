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
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXSparkleTrail extends EntityFX
/*     */ {
/*     */   private Entity target;
/*     */   
/*     */   public FXSparkleTrail(World par1World, double x, double y, double z, Entity target, float r, float g, float b)
/*     */   {
/*  21 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
/*  22 */     this.field_70552_h = r;
/*  23 */     this.field_70553_i = g;
/*  24 */     this.field_70551_j = b;
/*  25 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.5F + 0.5F);
/*     */     
/*  27 */     this.target = target;
/*     */     
/*  29 */     double dx = target.field_70165_t - this.field_70165_t;
/*  30 */     double dy = target.field_70163_u - this.field_70163_u;
/*  31 */     double dz = target.field_70161_v - this.field_70161_v;
/*  32 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 3.0F);
/*  33 */     if (base < 1)
/*  34 */       base = 1;
/*  35 */     this.field_70547_e = (base / 2 + this.field_70146_Z.nextInt(base));
/*     */     
/*  37 */     float f3 = 0.1F;
/*  38 */     this.field_70159_w = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*  39 */     this.field_70181_x = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*  40 */     this.field_70179_y = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3);
/*     */     
/*  42 */     this.field_70545_g = 0.2F;
/*  43 */     this.field_70145_X = false;
/*     */     
/*  45 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  46 */     int visibleDistance = 64;
/*  47 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j)
/*  48 */       visibleDistance = 32;
/*  49 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) {
/*  50 */       this.field_70547_e = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  56 */     float bob = MathHelper.func_76126_a(this.field_70546_d / 3.0F) * 0.5F + 1.0F;
/*     */     
/*  58 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*  59 */     int part = this.particle + this.field_70546_d % 16;
/*     */     
/*  61 */     float var8 = part % 8 / 8.0F;
/*  62 */     float var9 = var8 + 0.124875F;
/*  63 */     float var10 = part / 8 / 8.0F;
/*  64 */     float var11 = var10 + 0.124875F;
/*  65 */     float var12 = 0.1F * this.field_70544_f * bob;
/*     */     
/*  67 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/*  69 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/*  71 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  73 */     float var16 = 1.0F;
/*     */     
/*  75 */     tessellator.func_78380_c(240);
/*     */     
/*  77 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F);
/*     */     
/*  79 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/*     */     
/*     */ 
/*     */ 
/*  83 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/*     */     
/*     */ 
/*     */ 
/*  87 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/*     */     
/*     */ 
/*     */ 
/*  91 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  99 */     this.field_70169_q = this.field_70165_t;
/* 100 */     this.field_70167_r = this.field_70163_u;
/* 101 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 103 */     if ((this.field_70546_d++ >= this.field_70547_e) || (func_70068_e(this.target) < 1.0D))
/*     */     {
/* 105 */       func_70106_y();
/* 106 */       return;
/*     */     }
/*     */     
/*     */ 
/* 110 */     if (!this.field_70145_X)
/* 111 */       pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 112 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 114 */     this.field_70159_w *= 0.985D;
/* 115 */     this.field_70181_x *= 0.985D;
/* 116 */     this.field_70179_y *= 0.985D;
/*     */     
/* 118 */     double dx = this.target.field_70165_t - this.field_70165_t;
/* 119 */     double dy = this.target.field_70163_u - this.field_70163_u;
/* 120 */     double dz = this.target.field_70161_v - this.field_70161_v;
/* 121 */     double d13 = 0.3D;
/* 122 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 124 */     if (d11 < 4.0D) {
/* 125 */       this.field_70544_f *= 0.9F;
/* 126 */       d13 = 0.6D;
/*     */     }
/*     */     
/* 129 */     dx /= d11;
/* 130 */     dy /= d11;
/* 131 */     dz /= d11;
/*     */     
/* 133 */     this.field_70159_w += dx * d13;
/* 134 */     this.field_70181_x += dy * d13;
/* 135 */     this.field_70179_y += dz * d13;
/*     */     
/* 137 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.35F, 0.35F);
/* 138 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.35F, 0.35F);
/* 139 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.35F, 0.35F);
/*     */   }
/*     */   
/*     */   public void setGravity(float value) {
/* 143 */     this.field_70545_g = value;
/*     */   }
/*     */   
/*     */   protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
/* 147 */     int var7 = MathHelper.func_76128_c(par1);
/* 148 */     int var8 = MathHelper.func_76128_c(par3);
/* 149 */     int var9 = MathHelper.func_76128_c(par5);
/* 150 */     double var10 = par1 - var7;
/* 151 */     double var12 = par3 - var8;
/* 152 */     double var14 = par5 - var9;
/*     */     
/* 154 */     if ((!this.field_70170_p.func_147437_c(var7, var8, var9)) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */     {
/* 156 */       boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, true);
/*     */       
/* 158 */       boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, true);
/*     */       
/* 160 */       boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, true);
/*     */       
/* 162 */       boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, true);
/*     */       
/* 164 */       boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, true);
/*     */       
/* 166 */       boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, true);
/*     */       
/* 168 */       byte var22 = -1;
/* 169 */       double var23 = 9999.0D;
/*     */       
/* 171 */       if ((var16) && (var10 < var23)) {
/* 172 */         var23 = var10;
/* 173 */         var22 = 0;
/*     */       }
/*     */       
/* 176 */       if ((var17) && (1.0D - var10 < var23)) {
/* 177 */         var23 = 1.0D - var10;
/* 178 */         var22 = 1;
/*     */       }
/*     */       
/* 181 */       if ((var18) && (var12 < var23)) {
/* 182 */         var23 = var12;
/* 183 */         var22 = 2;
/*     */       }
/*     */       
/* 186 */       if ((var19) && (1.0D - var12 < var23)) {
/* 187 */         var23 = 1.0D - var12;
/* 188 */         var22 = 3;
/*     */       }
/*     */       
/* 191 */       if ((var20) && (var14 < var23)) {
/* 192 */         var23 = var14;
/* 193 */         var22 = 4;
/*     */       }
/*     */       
/* 196 */       if ((var21) && (1.0D - var14 < var23)) {
/* 197 */         var23 = 1.0D - var14;
/* 198 */         var22 = 5;
/*     */       }
/*     */       
/* 201 */       float var25 = this.field_70146_Z.nextFloat() * 0.05F + 0.025F;
/* 202 */       float var26 = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*     */       
/* 204 */       if (var22 == 0) {
/* 205 */         this.field_70159_w = (-var25);
/* 206 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 209 */       if (var22 == 1) {
/* 210 */         this.field_70159_w = var25;
/* 211 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 214 */       if (var22 == 2) {
/* 215 */         this.field_70181_x = (-var25);
/* 216 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 219 */       if (var22 == 3) {
/* 220 */         this.field_70181_x = var25;
/* 221 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 224 */       if (var22 == 4) {
/* 225 */         this.field_70179_y = (-var25);
/* 226 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 229 */       if (var22 == 5) {
/* 230 */         this.field_70179_y = var25;
/* 231 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 234 */       return true;
/*     */     }
/* 236 */     return false;
/*     */   }
/*     */   
/*     */ 
/* 240 */   public int particle = 24;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXSparkleTrail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */