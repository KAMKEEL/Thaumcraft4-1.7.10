/*     */ package thaumcraft.client.fx.other;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ public class FXBlockWard extends EntityFX
/*     */ {
/*     */   ForgeDirection side;
/*     */   
/*     */   public FXBlockWard(World world, double d, double d1, double d2, ForgeDirection side, float f, float f1, float f2)
/*     */   {
/*  19 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     
/*  21 */     this.side = side;
/*     */     
/*  23 */     this.field_70545_g = 0.0F;
/*  24 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  25 */     this.field_70547_e = (12 + this.field_70146_Z.nextInt(5));
/*  26 */     this.field_70145_X = false;
/*  27 */     func_70105_a(0.01F, 0.01F);
/*  28 */     this.field_70169_q = this.field_70165_t;
/*  29 */     this.field_70167_r = this.field_70163_u;
/*  30 */     this.field_70166_s = this.field_70161_v;
/*  31 */     this.field_70145_X = true;
/*  32 */     this.field_70544_f = ((float)(1.4D + this.field_70146_Z.nextGaussian() * 0.30000001192092896D));
/*  33 */     this.rotation = this.field_70146_Z.nextInt(360);
/*  34 */     this.sx = MathHelper.func_76131_a(f - 0.6F + this.field_70146_Z.nextFloat() * 0.2F, -0.4F, 0.4F);
/*  35 */     this.sy = MathHelper.func_76131_a(f1 - 0.6F + this.field_70146_Z.nextFloat() * 0.2F, -0.4F, 0.4F);
/*  36 */     this.sz = MathHelper.func_76131_a(f2 - 0.6F + this.field_70146_Z.nextFloat() * 0.2F, -0.4F, 0.4F);
/*  37 */     if (side.offsetX != 0) this.sx = 0.0F;
/*  38 */     if (side.offsetY != 0) this.sy = 0.0F;
/*  39 */     if (side.offsetZ != 0) { this.sz = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*  43 */   int rotation = 0;
/*  44 */   float sx = 0.0F;
/*  45 */   float sy = 0.0F;
/*  46 */   float sz = 0.0F;
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  51 */     tessellator.func_78381_a();
/*  52 */     GL11.glPushMatrix();
/*  53 */     float fade = (this.field_70546_d + f) / this.field_70547_e;
/*  54 */     int frame = Math.min(15, (int)(15.0F * fade));
/*  55 */     UtilsFX.bindTexture("textures/models/hemis" + frame + ".png");
/*     */     
/*  57 */     GL11.glDepthMask(false);
/*  58 */     GL11.glEnable(3042);
/*  59 */     GL11.glBlendFunc(770, 1);
/*     */     
/*  61 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as / 2.0F);
/*     */     
/*  63 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  64 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  65 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  67 */     GL11.glTranslated(var13 + this.sx, var14 + this.sy, var15 + this.sz);
/*     */     
/*  69 */     GL11.glRotatef(90.0F, this.side.offsetY, -this.side.offsetX, this.side.offsetZ);
/*  70 */     GL11.glRotatef(this.rotation, 0.0F, 0.0F, 1.0F);
/*  71 */     if (this.side.offsetZ > 0) {
/*  72 */       GL11.glTranslated(0.0D, 0.0D, 0.5049999952316284D);
/*  73 */       GL11.glRotatef(180.0F, 0.0F, -1.0F, 0.0F);
/*     */     } else {
/*  75 */       GL11.glTranslated(0.0D, 0.0D, -0.5049999952316284D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  80 */     float var12 = this.field_70544_f;
/*     */     
/*  82 */     float var16 = 1.0F;
/*     */     
/*  84 */     tessellator.func_78382_b();
/*  85 */     tessellator.func_78380_c(240);
/*  86 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F);
/*     */     
/*  88 */     tessellator.func_78374_a(-0.5D * var12, 0.5D * var12, 0.0D, 0.0D, 1.0D);
/*  89 */     tessellator.func_78374_a(0.5D * var12, 0.5D * var12, 0.0D, 1.0D, 1.0D);
/*  90 */     tessellator.func_78374_a(0.5D * var12, -0.5D * var12, 0.0D, 1.0D, 0.0D);
/*  91 */     tessellator.func_78374_a(-0.5D * var12, -0.5D * var12, 0.0D, 0.0D, 0.0D);
/*  92 */     tessellator.func_78381_a();
/*     */     
/*  94 */     GL11.glDisable(3042);
/*  95 */     GL11.glDepthMask(true);
/*     */     
/*  97 */     GL11.glPopMatrix();
/*  98 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
/*  99 */     tessellator.func_78382_b();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 106 */     this.field_70169_q = this.field_70165_t;
/* 107 */     this.field_70167_r = this.field_70163_u;
/* 108 */     this.field_70166_s = this.field_70161_v;
/* 109 */     float threshold = this.field_70547_e / 5.0F;
/* 110 */     if (this.field_70546_d <= threshold) {
/* 111 */       this.field_82339_as = (this.field_70546_d / threshold);
/*     */     } else {
/* 113 */       this.field_82339_as = ((this.field_70547_e - this.field_70546_d) / this.field_70547_e);
/*     */     }
/* 115 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 117 */       func_70106_y();
/*     */     }
/*     */     
/* 120 */     this.field_70181_x -= 0.04D * this.field_70545_g;
/*     */     
/* 122 */     this.field_70165_t += this.field_70159_w;
/* 123 */     this.field_70163_u += this.field_70181_x;
/* 124 */     this.field_70161_v += this.field_70179_y;
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 129 */     this.field_70545_g = value;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/other/FXBlockWard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */