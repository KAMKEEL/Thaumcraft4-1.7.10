/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class FXWispArcing extends EntityFX
/*     */ {
/*     */   private double field_70568_aq;
/*     */   private double field_70567_ar;
/*     */   private double field_70566_as;
/*     */   float moteParticleScale;
/*     */   int moteHalfLife;
/*     */   
/*     */   public FXWispArcing(World world, double d, double d1, double d2, float f, float red, float green, float blue)
/*     */   {
/*  20 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*  21 */     if (red == 0.0F) {
/*  22 */       red = 1.0F;
/*     */     }
/*  24 */     this.field_70568_aq = (this.field_70165_t = d);
/*  25 */     this.field_70567_ar = (this.field_70163_u = d1);
/*  26 */     this.field_70566_as = (this.field_70161_v = d2);
/*     */     
/*  28 */     this.field_70552_h = red;
/*  29 */     this.field_70553_i = green;
/*  30 */     this.field_70551_j = blue;
/*  31 */     this.field_70545_g = 0.0F;
/*  32 */     this.field_70544_f *= f;
/*  33 */     this.moteParticleScale = this.field_70544_f;
/*  34 */     this.field_70547_e = ((int)(36.0D / (Math.random() * 0.3D + 0.7D)));
/*     */     
/*  36 */     this.moteHalfLife = (this.field_70547_e / 2);
/*  37 */     this.field_70145_X = false;
/*  38 */     func_70105_a(0.01F, 0.01F);
/*  39 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  40 */     int visibleDistance = 50;
/*  41 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j)
/*  42 */       visibleDistance = 25;
/*  43 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance)
/*  44 */       this.field_70547_e = 0;
/*  45 */     this.field_70169_q = this.field_70165_t;
/*  46 */     this.field_70167_r = this.field_70163_u;
/*  47 */     this.field_70166_s = this.field_70161_v;
/*     */   }
/*     */   
/*     */   public FXWispArcing(World world, double d, double d1, double d2, double x, double y, double z, float f, float red, float green, float blue)
/*     */   {
/*  52 */     this(world, d, d1, d2, f, red, green, blue);
/*  53 */     this.field_70159_w = (x - d);
/*  54 */     this.field_70181_x = (y - d1);
/*  55 */     this.field_70179_y = (z - d2);
/*  56 */     func_70012_b(x, y, z, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  62 */     float agescale = 0.0F;
/*     */     
/*  64 */     agescale = this.field_70546_d / this.moteHalfLife;
/*  65 */     if (agescale > 1.0F) {
/*  66 */       agescale = 2.0F - agescale;
/*     */     }
/*  68 */     this.field_70544_f = (this.moteParticleScale * agescale);
/*     */     
/*  70 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*     */     
/*  72 */     float f10 = 0.5F * this.field_70544_f;
/*  73 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  74 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  75 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  77 */     float var8 = 0.0F;
/*  78 */     float var9 = 0.0F;
/*  79 */     float var10 = 0.875F;
/*  80 */     float var11 = 1.0F;
/*     */     
/*  82 */     tessellator.func_78380_c(240);
/*     */     
/*  84 */     tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5F);
/*     */     
/*  86 */     tessellator.func_78374_a(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, var9, var10);
/*     */     
/*  88 */     tessellator.func_78374_a(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10, var9, var11);
/*     */     
/*  90 */     tessellator.func_78374_a(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, var8, var10);
/*     */     
/*  92 */     tessellator.func_78374_a(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10, var8, var11);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/*  99 */     return this.blendmode == 1 ? 0 : 1;
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/* 103 */     this.field_70169_q = this.field_70165_t;
/* 104 */     this.field_70167_r = this.field_70163_u;
/* 105 */     this.field_70166_s = this.field_70161_v;
/* 106 */     float var1 = this.field_70546_d / this.field_70547_e;
/* 107 */     float var2 = this.field_70546_d / (this.field_70547_e / 2.0F);
/*     */     
/* 109 */     var1 = 1.0F - var1;
/* 110 */     var2 = 1.0F - var2;
/* 111 */     var2 *= var2;
/*     */     
/* 113 */     this.field_70165_t = (this.field_70568_aq + this.field_70159_w * var1);
/* 114 */     this.field_70163_u = (this.field_70567_ar + this.field_70181_x * var1 - var2 + 1.0D);
/*     */     
/* 116 */     this.field_70161_v = (this.field_70566_as + this.field_70179_y * var1);
/*     */     
/* 118 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 119 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 125 */     this.field_70545_g = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 130 */   public boolean tinkle = false;
/* 131 */   public int blendmode = 1;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXWispArcing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */