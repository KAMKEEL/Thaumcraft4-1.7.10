/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class FXScorch extends EntityFX
/*     */ {
/*     */   public FXScorch(World world, double x, double y, double z, Vec3 v, float spread, boolean lance)
/*     */   {
/*  15 */     super(world, x, y, z, 0.0D, 0.0D, 0.0D);
/*  16 */     this.field_70165_t = x;
/*  17 */     this.field_70163_u = y;
/*  18 */     this.field_70161_v = z;
/*  19 */     this.lance = lance;
/*  20 */     this.px = (x + v.field_72450_a * 100.0D);
/*  21 */     this.py = (y + v.field_72448_b * 100.0D);
/*  22 */     this.pz = (z + v.field_72449_c * 100.0D);
/*     */     
/*  24 */     if (!lance) {
/*  25 */       this.px += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * spread;
/*  26 */       this.py += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * spread;
/*  27 */       this.pz += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * spread;
/*     */     } else {
/*  29 */       this.px += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5D;
/*  30 */       this.py += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5D;
/*  31 */       this.pz += (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5D;
/*     */     }
/*     */     
/*  34 */     this.transferParticleScale = (this.field_70544_f = this.field_70146_Z.nextFloat() * 0.5F + 2.0F);
/*  35 */     if (!lance) {
/*  36 */       this.transferParticleScale = (this.field_70544_f = this.field_70146_Z.nextFloat() + 3.0F);
/*     */     }
/*  38 */     this.field_70547_e = 50;
/*  39 */     func_70105_a(0.1F, 0.1F);
/*     */     
/*  41 */     func_70536_a(151);
/*     */     
/*  43 */     this.field_70145_X = false;
/*  44 */     this.field_70169_q = this.field_70165_t;
/*  45 */     this.field_70167_r = this.field_70163_u;
/*  46 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  48 */     this.field_82339_as = 0.66F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70070_b(float par1)
/*     */   {
/*  55 */     return 210;
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70013_c(float par1)
/*     */   {
/*  61 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  67 */     double dx = this.px - this.field_70165_t;
/*  68 */     double dy = this.py - this.field_70163_u;
/*  69 */     double dz = this.pz - this.field_70161_v;
/*     */     
/*  71 */     double distance = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/*  73 */     this.field_70159_w = (dx /= distance * 1.25D);
/*  74 */     this.field_70181_x = (dy /= distance * 1.25D);
/*  75 */     this.field_70179_y = (dz /= distance * 1.25D);
/*     */     
/*  77 */     this.field_70159_w *= (this.field_70547_e - this.field_70546_d) / this.field_70547_e;
/*  78 */     this.field_70181_x *= (this.field_70547_e - this.field_70546_d) / this.field_70547_e;
/*  79 */     this.field_70179_y *= (this.field_70547_e - this.field_70546_d) / this.field_70547_e;
/*     */     
/*  81 */     this.field_70169_q = this.field_70165_t;
/*  82 */     this.field_70167_r = this.field_70163_u;
/*  83 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  85 */     this.field_70159_w += this.field_70146_Z.nextFloat() * 0.07F - 0.035F;
/*  86 */     this.field_70181_x += this.field_70146_Z.nextFloat() * 0.07F - 0.035F;
/*  87 */     this.field_70179_y += this.field_70146_Z.nextFloat() * 0.07F - 0.035F;
/*     */     
/*  89 */     int var7 = MathHelper.func_76128_c(this.field_70165_t);
/*  90 */     int var8 = MathHelper.func_76128_c(this.field_70163_u);
/*  91 */     int var9 = MathHelper.func_76128_c(this.field_70161_v);
/*  92 */     if ((this.field_70546_d > 1) && (this.field_70170_p.func_147439_a(var7, var8, var9).func_149662_c()))
/*     */     {
/*  94 */       this.field_70159_w = 0.0D;
/*  95 */       this.field_70181_x = 0.0D;
/*  96 */       this.field_70179_y = 0.0D;
/*  97 */       this.field_70546_d += 10;
/*     */     }
/*     */     
/* 100 */     func_145771_j(this.field_70165_t, (this.field_70121_D.field_72338_b + this.field_70121_D.field_72337_e) / 2.0D, this.field_70161_v);
/*     */     
/* 102 */     this.field_70165_t += this.field_70159_w;
/* 103 */     this.field_70163_u += this.field_70181_x;
/* 104 */     this.field_70161_v += this.field_70179_y;
/*     */     
/* 106 */     this.field_70546_d += 1;
/* 107 */     if (this.field_70546_d >= this.field_70547_e)
/*     */     {
/* 109 */       func_70106_y();
/*     */     }
/*     */     
/* 112 */     float fs = this.field_70546_d / (this.field_70547_e - 9);
/* 113 */     if (fs <= 1.0F) {
/* 114 */       func_70536_a((int)(151.0F + fs * 6.0F));
/*     */     } else {
/* 116 */       func_70536_a(159 - (this.field_70547_e - this.field_70546_d) / 3);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 123 */     float fs = this.field_70546_d / this.field_70547_e;
/* 124 */     this.field_70544_f = (this.transferParticleScale * (fs + 0.25F) * 2.0F);
/*     */     
/* 126 */     float fc = this.field_70546_d * 9.0F / this.field_70547_e;
/* 127 */     if (fc > 1.0F) fc = 1.0F;
/* 128 */     this.field_70552_h = (this.field_70553_i = fc);
/* 129 */     this.field_70551_j = 1.0F;
/* 130 */     super.func_70539_a(tessellator, f, f1, f2, f3, f4, f5);
/*     */   }
/*     */   
/* 133 */   public boolean pvp = true;
/* 134 */   public boolean mobs = true;
/* 135 */   public boolean animals = true;
/*     */   
/*     */   private double px;
/*     */   private double py;
/*     */   private double pz;
/*     */   private float transferParticleScale;
/*     */   net.minecraft.entity.Entity partDestEnt;
/* 142 */   public boolean lance = false;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXScorch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */