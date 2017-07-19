/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXSmokeSpiral extends EntityFX
/*     */ {
/*  12 */   private float radius = 1.0F;
/*  13 */   private int start = 0;
/*  14 */   private int miny = 0;
/*     */   
/*     */   public FXSmokeSpiral(World world, double d, double d1, double d2, float radius, int start, int miny)
/*     */   {
/*  18 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     
/*  20 */     this.field_70545_g = -0.01F;
/*  21 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  22 */     this.field_70544_f *= 1.0F;
/*  23 */     this.field_70547_e = (20 + world.field_73012_v.nextInt(10));
/*  24 */     this.field_70145_X = false;
/*  25 */     func_70105_a(0.01F, 0.01F);
/*  26 */     this.field_70169_q = this.field_70165_t;
/*  27 */     this.field_70167_r = this.field_70163_u;
/*  28 */     this.field_70166_s = this.field_70161_v;
/*  29 */     this.radius = radius;
/*  30 */     this.start = start;
/*  31 */     this.miny = miny;
/*     */   }
/*     */   
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  36 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F * this.field_82339_as);
/*     */     
/*  38 */     int particle = (int)(1.0F + this.field_70546_d / this.field_70547_e * 4.0F);
/*     */     
/*  40 */     float r1 = this.start + 720.0F * ((this.field_70546_d + f) / this.field_70547_e);
/*  41 */     float r2 = 90.0F - 180.0F * ((this.field_70546_d + f) / this.field_70547_e);
/*     */     
/*  43 */     float mX = -MathHelper.func_76126_a(r1 / 180.0F * 3.1415927F) * MathHelper.func_76134_b(r2 / 180.0F * 3.1415927F);
/*     */     
/*  45 */     float mZ = MathHelper.func_76134_b(r1 / 180.0F * 3.1415927F) * MathHelper.func_76134_b(r2 / 180.0F * 3.1415927F);
/*     */     
/*  47 */     float mY = -MathHelper.func_76126_a(r2 / 180.0F * 3.1415927F);
/*  48 */     mX *= this.radius;
/*  49 */     mY *= this.radius;
/*  50 */     mZ *= this.radius;
/*     */     
/*  52 */     float var8 = particle % 16 / 16.0F;
/*  53 */     float var9 = var8 + 0.0624375F;
/*  54 */     float var10 = particle / 16 / 16.0F;
/*  55 */     float var11 = var10 + 0.0624375F;
/*  56 */     float var12 = 0.15F * this.field_70544_f;
/*  57 */     float var13 = (float)(this.field_70165_t + mX - field_70556_an);
/*  58 */     float var14 = (float)(Math.max(this.field_70163_u + mY, this.miny + 0.1F) - field_70554_ao);
/*  59 */     float var15 = (float)(this.field_70161_v + mZ - field_70555_ap);
/*  60 */     float var16 = 1.0F;
/*     */     
/*  62 */     tessellator.func_78380_c(func_70070_b(f));
/*  63 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.66F * this.field_82339_as);
/*     */     
/*  65 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/*     */     
/*     */ 
/*     */ 
/*  69 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/*     */     
/*     */ 
/*     */ 
/*  73 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/*     */     
/*     */ 
/*     */ 
/*  77 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/*  86 */     return 1;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  91 */     func_82338_g((this.field_70547_e - this.field_70546_d) / this.field_70547_e);
/*  92 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/*  94 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean pushOutOfBlocks(double par1, double par3, double par5)
/*     */   {
/* 100 */     int var7 = MathHelper.func_76128_c(par1);
/* 101 */     int var8 = MathHelper.func_76128_c(par3);
/* 102 */     int var9 = MathHelper.func_76128_c(par5);
/* 103 */     double var10 = par1 - var7;
/* 104 */     double var12 = par3 - var8;
/* 105 */     double var14 = par5 - var9;
/*     */     
/* 107 */     if (!this.field_70170_p.func_147437_c(var7, var8, var9)) {
/* 108 */       boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, true);
/*     */       
/* 110 */       boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, true);
/*     */       
/* 112 */       boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, true);
/*     */       
/* 114 */       boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, true);
/*     */       
/* 116 */       boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, true);
/*     */       
/* 118 */       boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, true);
/*     */       
/* 120 */       byte var22 = -1;
/* 121 */       double var23 = 9999.0D;
/*     */       
/* 123 */       if ((var16) && (var10 < var23)) {
/* 124 */         var23 = var10;
/* 125 */         var22 = 0;
/*     */       }
/*     */       
/* 128 */       if ((var17) && (1.0D - var10 < var23)) {
/* 129 */         var23 = 1.0D - var10;
/* 130 */         var22 = 1;
/*     */       }
/*     */       
/* 133 */       if ((var18) && (var12 < var23)) {
/* 134 */         var23 = var12;
/* 135 */         var22 = 2;
/*     */       }
/*     */       
/* 138 */       if ((var19) && (1.0D - var12 < var23)) {
/* 139 */         var23 = 1.0D - var12;
/* 140 */         var22 = 3;
/*     */       }
/*     */       
/* 143 */       if ((var20) && (var14 < var23)) {
/* 144 */         var23 = var14;
/* 145 */         var22 = 4;
/*     */       }
/*     */       
/* 148 */       if ((var21) && (1.0D - var14 < var23)) {
/* 149 */         var23 = 1.0D - var14;
/* 150 */         var22 = 5;
/*     */       }
/*     */       
/* 153 */       float var25 = this.field_70146_Z.nextFloat() * 0.05F + 0.025F;
/* 154 */       float var26 = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*     */       
/* 156 */       if (var22 == 0) {
/* 157 */         this.field_70159_w = (-var25);
/* 158 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 161 */       if (var22 == 1) {
/* 162 */         this.field_70159_w = var25;
/* 163 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 166 */       if (var22 == 2) {
/* 167 */         this.field_70181_x = (-var25);
/* 168 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 171 */       if (var22 == 3) {
/* 172 */         this.field_70181_x = var25;
/* 173 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 176 */       if (var22 == 4) {
/* 177 */         this.field_70179_y = (-var25);
/* 178 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 181 */       if (var22 == 5) {
/* 182 */         this.field_70179_y = var25;
/* 183 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 186 */       return true;
/*     */     }
/* 188 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXSmokeSpiral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */