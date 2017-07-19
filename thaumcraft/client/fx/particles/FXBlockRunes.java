/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FXBlockRunes
/*     */   extends EntityFX
/*     */ {
/*     */   public FXBlockRunes(World world, double d, double d1, double d2, float f1, float f2, float f3, int m)
/*     */   {
/*  16 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*  17 */     if (f1 == 0.0F) {
/*  18 */       f1 = 1.0F;
/*     */     }
/*  20 */     this.rotation = (this.field_70146_Z.nextInt(4) * 90);
/*     */     
/*  22 */     this.field_70552_h = f1;
/*  23 */     this.field_70553_i = f2;
/*  24 */     this.field_70551_j = f3;
/*  25 */     this.field_70545_g = 0.0F;
/*  26 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  27 */     this.field_70547_e = (3 * m);
/*  28 */     this.field_70145_X = false;
/*  29 */     func_70105_a(0.01F, 0.01F);
/*  30 */     this.field_70169_q = this.field_70165_t;
/*  31 */     this.field_70167_r = this.field_70163_u;
/*  32 */     this.field_70166_s = this.field_70161_v;
/*  33 */     this.field_70145_X = true;
/*  34 */     this.runeIndex = ((int)(Math.random() * 16.0D + 224.0D));
/*  35 */     this.ofx = (this.field_70146_Z.nextFloat() * 0.2D);
/*  36 */     this.ofy = (-0.3D + this.field_70146_Z.nextFloat() * 0.6D);
/*  37 */     this.field_70544_f = ((float)(1.0D + this.field_70146_Z.nextGaussian() * 0.10000000149011612D));
/*  38 */     this.field_82339_as = 0.0F;
/*     */   }
/*     */   
/*  41 */   double ofx = 0.0D;
/*  42 */   double ofy = 0.0D;
/*  43 */   float rotation = 0.0F;
/*  44 */   int runeIndex = 0;
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  49 */     tessellator.func_78381_a();
/*  50 */     GL11.glPushMatrix();
/*     */     
/*  52 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as / 2.0F);
/*     */     
/*  54 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/*  56 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/*  58 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*     */ 
/*  61 */     GL11.glTranslated(var13, var14, var15);
/*  62 */     GL11.glRotatef(this.rotation, 0.0F, 1.0F, 0.0F);
/*  63 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  64 */     GL11.glTranslated(this.ofx, this.ofy, -0.51D);
/*     */     
/*  66 */     float var8 = this.runeIndex % 16 / 16.0F;
/*  67 */     float var9 = var8 + 0.0624375F;
/*  68 */     float var10 = 0.375F;
/*  69 */     float var11 = var10 + 0.0624375F;
/*  70 */     float var12 = 0.3F * this.field_70544_f;
/*     */     
/*  72 */     float var16 = 1.0F;
/*     */     
/*  74 */     tessellator.func_78382_b();
/*  75 */     tessellator.func_78380_c(240);
/*  76 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F);
/*     */     
/*     */ 
/*  79 */     tessellator.func_78374_a(-0.5D * var12, 0.5D * var12, 0.0D, var9, var11);
/*     */     
/*  81 */     tessellator.func_78374_a(0.5D * var12, 0.5D * var12, 0.0D, var9, var10);
/*     */     
/*  83 */     tessellator.func_78374_a(0.5D * var12, -0.5D * var12, 0.0D, var8, var10);
/*     */     
/*  85 */     tessellator.func_78374_a(-0.5D * var12, -0.5D * var12, 0.0D, var8, var11);
/*     */     
/*  87 */     tessellator.func_78381_a();
/*     */     
/*  89 */     GL11.glPopMatrix();
/*  90 */     tessellator.func_78382_b();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  96 */     this.field_70169_q = this.field_70165_t;
/*  97 */     this.field_70167_r = this.field_70163_u;
/*  98 */     this.field_70166_s = this.field_70161_v;
/*  99 */     float threshold = this.field_70547_e / 5.0F;
/*     */     
/* 101 */     if (this.field_70546_d <= threshold) {
/* 102 */       this.field_82339_as = (this.field_70546_d / threshold);
/*     */     } else {
/* 104 */       this.field_82339_as = ((this.field_70547_e - this.field_70546_d) / this.field_70547_e);
/*     */     }
/*     */     
/* 107 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 108 */       func_70106_y();
/*     */     }
/*     */     
/* 111 */     this.field_70181_x -= 0.04D * this.field_70545_g;
/*     */     
/* 113 */     this.field_70165_t += this.field_70159_w;
/* 114 */     this.field_70163_u += this.field_70181_x;
/* 115 */     this.field_70161_v += this.field_70179_y;
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 120 */     this.field_70545_g = value;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXBlockRunes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */