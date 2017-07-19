/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class FXVisSparkle extends EntityFX
/*     */ {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   
/*     */   public FXVisSparkle(World par1World, double par2, double par4, double par6, double tx, double ty, double tz)
/*     */   {
/*  20 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  21 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 0.6F);
/*  22 */     this.field_70544_f = 0.0F;
/*     */     
/*  24 */     this.targetX = tx;
/*  25 */     this.targetY = ty;
/*  26 */     this.targetZ = tz;
/*     */     
/*  28 */     this.field_70547_e = 1000;
/*     */     
/*  30 */     float f3 = 0.01F;
/*  31 */     this.field_70159_w = ((float)this.field_70146_Z.nextGaussian() * f3);
/*  32 */     this.field_70181_x = ((float)this.field_70146_Z.nextGaussian() * f3);
/*  33 */     this.field_70179_y = ((float)this.field_70146_Z.nextGaussian() * f3);
/*  34 */     this.sizeMod = (45 + this.field_70146_Z.nextInt(15));
/*  35 */     this.field_70552_h = 0.2F;
/*  36 */     this.field_70553_i = (0.6F + this.field_70146_Z.nextFloat() * 0.3F);
/*  37 */     this.field_70551_j = 0.2F;
/*     */     
/*  39 */     this.field_70545_g = 0.2F;
/*  40 */     this.field_70145_X = true;
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
/*  51 */   float sizeMod = 0.0F;
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  57 */     float bob = MathHelper.func_76126_a(this.field_70546_d / 3.0F) * 0.3F + 6.0F;
/*     */     
/*  59 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*  60 */     int part = this.field_70546_d % 16;
/*  61 */     float var8 = part / 16.0F;
/*  62 */     float var9 = var8 + 0.0624375F;
/*  63 */     float var10 = 0.5F;
/*  64 */     float var11 = var10 + 0.0624375F;
/*     */     
/*  66 */     float var12 = 0.1F * this.field_70544_f * bob;
/*     */     
/*  68 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/*  70 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/*  72 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  74 */     float var16 = 1.0F;
/*     */     
/*  76 */     tessellator.func_78380_c(240);
/*     */     
/*  78 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F);
/*     */     
/*  80 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/*     */     
/*     */ 
/*     */ 
/*  84 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/*     */     
/*     */ 
/*     */ 
/*  88 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/*     */     
/*     */ 
/*     */ 
/*  92 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 101 */     this.field_70169_q = this.field_70165_t;
/* 102 */     this.field_70167_r = this.field_70163_u;
/* 103 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 105 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 106 */       func_70106_y();
/* 107 */       return;
/*     */     }
/*     */     
/* 110 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 112 */     this.field_70159_w *= 0.985D;
/* 113 */     this.field_70181_x *= 0.985D;
/* 114 */     this.field_70179_y *= 0.985D;
/*     */     
/* 116 */     double dx = this.targetX - this.field_70165_t;
/* 117 */     double dy = this.targetY - this.field_70163_u;
/* 118 */     double dz = this.targetZ - this.field_70161_v;
/* 119 */     double d13 = 0.10000000149011612D;
/* 120 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/* 121 */     if (d11 < 2.0D) this.field_70544_f *= 0.95F;
/* 122 */     if (d11 < 0.2D) this.field_70547_e = this.field_70546_d;
/* 123 */     if (this.field_70546_d < 10) this.field_70544_f = (this.field_70546_d / this.sizeMod);
/* 124 */     dx /= d11;
/* 125 */     dy /= d11;
/* 126 */     dz /= d11;
/*     */     
/* 128 */     this.field_70159_w += dx * d13;
/* 129 */     this.field_70181_x += dy * d13;
/* 130 */     this.field_70179_y += dz * d13;
/*     */     
/* 132 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.1F, 0.1F);
/* 133 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.1F, 0.1F);
/* 134 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.1F, 0.1F);
/*     */   }
/*     */   
/*     */   public void setGravity(float value) {
/* 138 */     this.field_70545_g = value;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXVisSparkle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */