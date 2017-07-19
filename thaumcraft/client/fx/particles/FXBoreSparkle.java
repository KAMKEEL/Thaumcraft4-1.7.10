/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXBoreSparkle extends EntityFX
/*     */ {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   
/*     */   public FXBoreSparkle(World par1World, double par2, double par4, double par6, double tx, double ty, double tz)
/*     */   {
/*  22 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  23 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 0.6F);
/*  24 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.5F + 0.5F);
/*     */     
/*  26 */     this.targetX = tx;
/*  27 */     this.targetY = ty;
/*  28 */     this.targetZ = tz;
/*     */     
/*  30 */     double dx = tx - this.field_70165_t;
/*  31 */     double dy = ty - this.field_70163_u;
/*  32 */     double dz = tz - this.field_70161_v;
/*  33 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 3.0F);
/*  34 */     if (base < 1)
/*  35 */       base = 1;
/*  36 */     this.field_70547_e = (base / 2 + this.field_70146_Z.nextInt(base));
/*     */     
/*  38 */     float f3 = 0.01F;
/*  39 */     this.field_70159_w = ((float)this.field_70146_Z.nextGaussian() * f3);
/*  40 */     this.field_70181_x = ((float)this.field_70146_Z.nextGaussian() * f3);
/*  41 */     this.field_70179_y = ((float)this.field_70146_Z.nextGaussian() * f3);
/*     */     
/*  43 */     this.field_70552_h = 0.2F;
/*  44 */     this.field_70553_i = (0.6F + this.field_70146_Z.nextFloat() * 0.3F);
/*  45 */     this.field_70551_j = 0.2F;
/*     */     
/*  47 */     this.field_70545_g = 0.2F;
/*  48 */     this.field_70145_X = false;
/*     */     
/*  50 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  51 */     int visibleDistance = 64;
/*  52 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j)
/*  53 */       visibleDistance = 32;
/*  54 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) {
/*  55 */       this.field_70547_e = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
/*  60 */     float bob = MathHelper.func_76126_a(this.field_70546_d / 3.0F) * 0.5F + 1.0F;
/*     */     
/*  62 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*  63 */     int part = this.field_70546_d % 4;
/*     */     
/*  65 */     float var8 = part / 16.0F;
/*  66 */     float var9 = var8 + 0.0624375F;
/*  67 */     float var10 = 0.25F;
/*  68 */     float var11 = var10 + 0.0624375F;
/*  69 */     float var12 = 0.1F * this.field_70544_f * bob;
/*     */     
/*  71 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/*  73 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/*  75 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  77 */     float var16 = 1.0F;
/*     */     
/*  79 */     tessellator.func_78380_c(240);
/*     */     
/*  81 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F);
/*     */     
/*  83 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/*     */     
/*     */ 
/*     */ 
/*  87 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/*     */     
/*     */ 
/*     */ 
/*  91 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/*     */     
/*     */ 
/*     */ 
/*  95 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 103 */     this.field_70169_q = this.field_70165_t;
/* 104 */     this.field_70167_r = this.field_70163_u;
/* 105 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 107 */     if ((this.field_70546_d++ >= this.field_70547_e) || ((MathHelper.func_76128_c(this.field_70165_t) == MathHelper.func_76128_c(this.targetX)) && (MathHelper.func_76128_c(this.field_70163_u) == MathHelper.func_76128_c(this.targetY)) && (MathHelper.func_76128_c(this.field_70161_v) == MathHelper.func_76128_c(this.targetZ))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 113 */       func_70106_y();
/* 114 */       return;
/*     */     }
/*     */     
/*     */ 
/* 118 */     if (!this.field_70145_X)
/* 119 */       pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 120 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 122 */     this.field_70159_w *= 0.985D;
/* 123 */     this.field_70181_x *= 0.985D;
/* 124 */     this.field_70179_y *= 0.985D;
/*     */     
/* 126 */     double dx = this.targetX - this.field_70165_t;
/* 127 */     double dy = this.targetY - this.field_70163_u;
/* 128 */     double dz = this.targetZ - this.field_70161_v;
/* 129 */     double d13 = 0.3D;
/* 130 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 132 */     if (d11 < 4.0D) {
/* 133 */       this.field_70544_f *= 0.9F;
/* 134 */       d13 = 0.6D;
/*     */     }
/*     */     
/* 137 */     dx /= d11;
/* 138 */     dy /= d11;
/* 139 */     dz /= d11;
/*     */     
/* 141 */     this.field_70159_w += dx * d13;
/* 142 */     this.field_70181_x += dy * d13;
/* 143 */     this.field_70179_y += dz * d13;
/*     */     
/* 145 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.35F, 0.35F);
/* 146 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.35F, 0.35F);
/* 147 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.35F, 0.35F);
/*     */   }
/*     */   
/*     */   public void setGravity(float value) {
/* 151 */     this.field_70545_g = value;
/*     */   }
/*     */   
/*     */   protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
/* 155 */     int var7 = MathHelper.func_76128_c(par1);
/* 156 */     int var8 = MathHelper.func_76128_c(par3);
/* 157 */     int var9 = MathHelper.func_76128_c(par5);
/* 158 */     double var10 = par1 - var7;
/* 159 */     double var12 = par3 - var8;
/* 160 */     double var14 = par5 - var9;
/*     */     
/* 162 */     if ((!this.field_70170_p.func_147437_c(var7, var8, var9)) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */     {
/* 164 */       boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, true);
/*     */       
/* 166 */       boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, true);
/*     */       
/* 168 */       boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, true);
/*     */       
/* 170 */       boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, true);
/*     */       
/* 172 */       boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, true);
/*     */       
/* 174 */       boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, true);
/*     */       
/* 176 */       byte var22 = -1;
/* 177 */       double var23 = 9999.0D;
/*     */       
/* 179 */       if ((var16) && (var10 < var23)) {
/* 180 */         var23 = var10;
/* 181 */         var22 = 0;
/*     */       }
/*     */       
/* 184 */       if ((var17) && (1.0D - var10 < var23)) {
/* 185 */         var23 = 1.0D - var10;
/* 186 */         var22 = 1;
/*     */       }
/*     */       
/* 189 */       if ((var18) && (var12 < var23)) {
/* 190 */         var23 = var12;
/* 191 */         var22 = 2;
/*     */       }
/*     */       
/* 194 */       if ((var19) && (1.0D - var12 < var23)) {
/* 195 */         var23 = 1.0D - var12;
/* 196 */         var22 = 3;
/*     */       }
/*     */       
/* 199 */       if ((var20) && (var14 < var23)) {
/* 200 */         var23 = var14;
/* 201 */         var22 = 4;
/*     */       }
/*     */       
/* 204 */       if ((var21) && (1.0D - var14 < var23)) {
/* 205 */         var23 = 1.0D - var14;
/* 206 */         var22 = 5;
/*     */       }
/*     */       
/* 209 */       float var25 = this.field_70146_Z.nextFloat() * 0.05F + 0.025F;
/* 210 */       float var26 = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*     */       
/* 212 */       if (var22 == 0) {
/* 213 */         this.field_70159_w = (-var25);
/* 214 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 217 */       if (var22 == 1) {
/* 218 */         this.field_70159_w = var25;
/* 219 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 222 */       if (var22 == 2) {
/* 223 */         this.field_70181_x = (-var25);
/* 224 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 227 */       if (var22 == 3) {
/* 228 */         this.field_70181_x = var25;
/* 229 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 232 */       if (var22 == 4) {
/* 233 */         this.field_70179_y = (-var25);
/* 234 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 237 */       if (var22 == 5) {
/* 238 */         this.field_70179_y = var25;
/* 239 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 242 */       return true;
/*     */     }
/* 244 */     return false;
/*     */   }
/*     */   
/*     */ 
/* 248 */   public int particle = 24;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXBoreSparkle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */