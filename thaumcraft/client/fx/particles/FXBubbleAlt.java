/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXBubbleAlt
/*     */   extends EntityFX
/*     */ {
/*  16 */   public int particle = 25;
/*     */   
/*     */   public FXBubbleAlt(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int age)
/*     */   {
/*  20 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*  21 */     this.field_70552_h = 1.0F;
/*  22 */     this.field_70553_i = 0.0F;
/*  23 */     this.field_70551_j = 0.5F;
/*  24 */     func_70105_a(0.02F, 0.02F);
/*  25 */     this.field_70145_X = true;
/*  26 */     this.field_70544_f *= (this.field_70146_Z.nextFloat() * 0.3F + 0.2F);
/*  27 */     this.field_70159_w = (par8 * 0.20000000298023224D + (float)(Math.random() * 2.0D - 1.0D) * 0.02F);
/*  28 */     this.field_70181_x = (par10 * 0.20000000298023224D + (float)Math.random() * 0.02F);
/*  29 */     this.field_70179_y = (par12 * 0.20000000298023224D + (float)(Math.random() * 2.0D - 1.0D) * 0.02F);
/*  30 */     this.field_70547_e = ((int)(age + 2 + 8.0D / (Math.random() * 0.8D + 0.2D)));
/*  31 */     this.field_70546_d = 0;
/*  32 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  33 */     int visibleDistance = 50;
/*  34 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 25;
/*  35 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) { this.field_70547_e = 0;
/*     */     }
/*  37 */     this.field_70169_q = this.field_70165_t;
/*  38 */     this.field_70167_r = this.field_70163_u;
/*  39 */     this.field_70166_s = this.field_70161_v;
/*     */   }
/*     */   
/*  42 */   public double bubblespeed = 1.0E-4D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  49 */     this.field_70169_q = this.field_70165_t;
/*  50 */     this.field_70167_r = this.field_70163_u;
/*  51 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  53 */     this.field_70159_w += (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.001F;
/*  54 */     this.field_70179_y += (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.001F;
/*     */     
/*  56 */     this.field_70165_t += this.field_70159_w;
/*  57 */     this.field_70163_u += this.field_70181_x;
/*  58 */     this.field_70161_v += this.field_70179_y;
/*     */     
/*  60 */     this.field_70159_w *= 0.8500000238418579D;
/*  61 */     this.field_70181_x *= 0.8500000238418579D;
/*  62 */     this.field_70179_y *= 0.8500000238418579D;
/*     */     
/*  64 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/*  66 */       func_70106_y();
/*     */     }
/*     */     
/*  69 */     if (this.field_70546_d == this.field_70547_e - 2) {
/*  70 */       this.particle = 17;
/*     */     }
/*  72 */     else if (this.field_70546_d == this.field_70547_e - 1) {
/*  73 */       this.particle = 18;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b)
/*     */   {
/*  79 */     this.field_70552_h = r;
/*  80 */     this.field_70553_i = g;
/*  81 */     this.field_70551_j = b;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  87 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as);
/*     */     
/*  89 */     float var8 = this.particle % 16 / 16.0F;
/*  90 */     float var9 = var8 + 0.0624375F;
/*  91 */     float var10 = this.particle / 16 / 16.0F;
/*  92 */     float var11 = var10 + 0.0624375F;
/*  93 */     float var12 = 0.2F * (this.field_70544_f * (this.field_70546_d / this.field_70547_e));
/*  94 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  95 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  96 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*  97 */     float var16 = 1.0F;
/*     */     
/*  99 */     tessellator.func_78380_c(240);
/*     */     
/* 101 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as);
/* 102 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/* 103 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/* 104 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/* 105 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXBubbleAlt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */