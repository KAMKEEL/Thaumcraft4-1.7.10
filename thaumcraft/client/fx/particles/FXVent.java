/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.awt.Color;
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
/*     */ 
/*     */ 
/*     */ public class FXVent
/*     */   extends EntityFX
/*     */ {
/*     */   public FXVent(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int color)
/*     */   {
/*  22 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*  23 */     func_70105_a(0.02F, 0.02F);
/*  24 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.1F + 0.05F);
/*  25 */     this.field_70159_w = par8;
/*  26 */     this.field_70181_x = par10;
/*  27 */     this.field_70179_y = par12;
/*  28 */     this.field_70145_X = true;
/*     */     
/*  30 */     Color c = new Color(color);
/*  31 */     this.field_70552_h = (c.getRed() / 255.0F);
/*  32 */     this.field_70551_j = (c.getBlue() / 255.0F);
/*  33 */     this.field_70553_i = (c.getGreen() / 255.0F);
/*     */     
/*  35 */     setHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, 0.125F, 5.0F);
/*     */     
/*  37 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  38 */     int visibleDistance = 50;
/*  39 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 25;
/*  40 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) { this.field_70547_e = 0;
/*     */     }
/*  42 */     this.field_70169_q = this.field_70165_t;
/*  43 */     this.field_70167_r = this.field_70163_u;
/*  44 */     this.field_70166_s = this.field_70161_v;
/*     */   }
/*     */   
/*  47 */   float psm = 1.0F;
/*     */   
/*  49 */   public void setScale(float f) { this.field_70544_f *= f;
/*  50 */     this.psm *= f;
/*     */   }
/*     */   
/*     */   public void setHeading(double par1, double par3, double par5, float par7, float par8)
/*     */   {
/*  55 */     float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/*  56 */     par1 /= f2;
/*  57 */     par3 /= f2;
/*  58 */     par5 /= f2;
/*  59 */     par1 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*  60 */     par3 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*  61 */     par5 += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
/*  62 */     par1 *= par7;
/*  63 */     par3 *= par7;
/*  64 */     par5 *= par7;
/*  65 */     this.field_70159_w = par1;
/*  66 */     this.field_70181_x = par3;
/*  67 */     this.field_70179_y = par5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  75 */     this.field_70169_q = this.field_70165_t;
/*  76 */     this.field_70167_r = this.field_70163_u;
/*  77 */     this.field_70166_s = this.field_70161_v;
/*  78 */     this.field_70546_d += 1;
/*  79 */     if (this.field_70544_f > this.psm)
/*     */     {
/*  81 */       func_70106_y();
/*     */     }
/*     */     
/*  84 */     this.field_70181_x += 0.0025D;
/*  85 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*  86 */     this.field_70159_w *= 0.8500000190734863D;
/*  87 */     this.field_70181_x *= 0.8500000190734863D;
/*  88 */     this.field_70179_y *= 0.8500000190734863D;
/*  89 */     if (this.field_70544_f < this.psm) { this.field_70544_f = ((float)(this.field_70544_f * 1.15D));
/*     */     }
/*  91 */     if (this.field_70122_E)
/*     */     {
/*  93 */       this.field_70159_w *= 0.699999988079071D;
/*  94 */       this.field_70179_y *= 0.699999988079071D;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/*  99 */     this.field_70552_h = r;
/* 100 */     this.field_70553_i = g;
/* 101 */     this.field_70551_j = b;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 107 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.33F);
/* 108 */     int part = (int)(1.0F + this.field_70544_f / this.psm * 4.0F);
/* 109 */     float var8 = part % 16 / 16.0F;
/* 110 */     float var9 = var8 + 0.0624375F;
/* 111 */     float var10 = part / 16 / 16.0F;
/* 112 */     float var11 = var10 + 0.0624375F;
/* 113 */     float var12 = 0.3F * this.field_70544_f;
/*     */     
/* 115 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 116 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 117 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/* 118 */     float var16 = 1.0F;
/*     */     
/* 120 */     tessellator.func_78380_c(func_70070_b(f));
/* 121 */     float alpha = this.field_82339_as * ((this.psm - this.field_70544_f) / this.psm);
/* 122 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, alpha);
/* 123 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/* 124 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/* 125 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/* 126 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 133 */     return 1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXVent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */