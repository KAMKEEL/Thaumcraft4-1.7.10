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
/*     */ public class FXWispEG
/*     */   extends EntityFX
/*     */ {
/*  18 */   Entity target = null;
/*  19 */   double rx = 0.0D;
/*  20 */   double ry = 0.0D;
/*  21 */   double rz = 0.0D;
/*     */   
/*     */   public FXWispEG(World worldObj, double posX, double posY, double posZ, Entity target2)
/*     */   {
/*  25 */     super(worldObj, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/*     */     
/*  27 */     this.target = target2;
/*     */     
/*  29 */     this.field_70159_w = (this.field_70146_Z.nextGaussian() * 0.03D);
/*  30 */     this.field_70181_x = -0.05D;
/*  31 */     this.field_70179_y = (this.field_70146_Z.nextGaussian() * 0.03D);
/*     */     
/*  33 */     this.field_70544_f *= 0.4F;
/*  34 */     this.field_70547_e = ((int)(40.0D / (Math.random() * 0.3D + 0.7D)));
/*     */     
/*  36 */     this.field_70145_X = false;
/*  37 */     func_70105_a(0.01F, 0.01F);
/*  38 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  39 */     int visibleDistance = 50;
/*  40 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j)
/*  41 */       visibleDistance = 25;
/*  42 */     if (renderentity.func_70011_f(posX, posY, posZ) > visibleDistance)
/*  43 */       this.field_70547_e = 0;
/*  44 */     this.field_70169_q = posX;
/*  45 */     this.field_70167_r = posY;
/*  46 */     this.field_70166_s = posZ;
/*  47 */     this.blendmode = 771;
/*  48 */     this.field_70552_h = (this.field_70146_Z.nextFloat() * 0.05F);
/*  49 */     this.field_70553_i = (this.field_70146_Z.nextFloat() * 0.05F);
/*  50 */     this.field_70551_j = (this.field_70146_Z.nextFloat() * 0.05F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  56 */     Entity e = Minecraft.func_71410_x().field_71451_h;
/*  57 */     float agescale = 1.0F - this.field_70546_d / this.field_70547_e;
/*  58 */     float d6 = 1024.0F;
/*  59 */     float base = (float)(1.0D - Math.min(d6, func_70092_e(e.field_70165_t, e.field_70163_u, e.field_70161_v)) / d6);
/*     */     
/*  61 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F * base);
/*     */     
/*  63 */     float f10 = 0.5F * this.field_70544_f;
/*  64 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  65 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  66 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  68 */     float var8 = this.field_70546_d % 13 / 16.0F;
/*  69 */     float var9 = var8 + 0.0624375F;
/*  70 */     float var10 = 0.1875F;
/*  71 */     float var11 = var10 + 0.0624375F;
/*     */     
/*  73 */     tessellator.func_78380_c(240);
/*     */     
/*  75 */     tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.2F * agescale * base);
/*     */     
/*  77 */     tessellator.func_78374_a(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, var9, var11);
/*     */     
/*  79 */     tessellator.func_78374_a(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10, var9, var10);
/*     */     
/*  81 */     tessellator.func_78374_a(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, var8, var10);
/*     */     
/*  83 */     tessellator.func_78374_a(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10, var8, var11);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/*  90 */     return this.blendmode == 1 ? 0 : 1;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  95 */     this.field_70169_q = this.field_70165_t;
/*  96 */     this.field_70167_r = this.field_70163_u;
/*  97 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  99 */     if ((this.target != null) && (!this.field_70122_E)) {
/* 100 */       this.field_70165_t += this.target.field_70159_w;
/* 101 */       this.field_70161_v += this.target.field_70179_y;
/*     */     }
/*     */     
/*     */ 
/* 105 */     pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 106 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 108 */     this.field_70159_w *= 0.9800000190734863D;
/* 109 */     this.field_70181_x *= 0.9800000190734863D;
/* 110 */     this.field_70179_y *= 0.9800000190734863D;
/* 111 */     if (this.field_70122_E) {
/* 112 */       this.field_70159_w *= 0.8500000190734863D;
/* 113 */       this.field_70179_y *= 0.8500000190734863D;
/*     */     }
/*     */     
/*     */ 
/* 117 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 119 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
/* 124 */     int var7 = MathHelper.func_76128_c(par1);
/* 125 */     int var8 = MathHelper.func_76128_c(par3);
/* 126 */     int var9 = MathHelper.func_76128_c(par5);
/* 127 */     double var10 = par1 - var7;
/* 128 */     double var12 = par3 - var8;
/* 129 */     double var14 = par5 - var9;
/*     */     
/* 131 */     if ((!this.field_70170_p.func_147437_c(var7, var8, var9)) && (this.field_70170_p.func_147445_c(var7, var8, var9, true)) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */     {
/*     */ 
/* 134 */       boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, true);
/*     */       
/* 136 */       boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, true);
/*     */       
/* 138 */       boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, true);
/*     */       
/* 140 */       boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, true);
/*     */       
/* 142 */       boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, true);
/*     */       
/* 144 */       boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, true);
/*     */       
/* 146 */       byte var22 = -1;
/* 147 */       double var23 = 9999.0D;
/*     */       
/* 149 */       if ((var16) && (var10 < var23)) {
/* 150 */         var23 = var10;
/* 151 */         var22 = 0;
/*     */       }
/*     */       
/* 154 */       if ((var17) && (1.0D - var10 < var23)) {
/* 155 */         var23 = 1.0D - var10;
/* 156 */         var22 = 1;
/*     */       }
/*     */       
/* 159 */       if ((var18) && (var12 < var23)) {
/* 160 */         var23 = var12;
/* 161 */         var22 = 2;
/*     */       }
/*     */       
/* 164 */       if ((var19) && (1.0D - var12 < var23)) {
/* 165 */         var23 = 1.0D - var12;
/* 166 */         var22 = 3;
/*     */       }
/*     */       
/* 169 */       if ((var20) && (var14 < var23)) {
/* 170 */         var23 = var14;
/* 171 */         var22 = 4;
/*     */       }
/*     */       
/* 174 */       if ((var21) && (1.0D - var14 < var23)) {
/* 175 */         var23 = 1.0D - var14;
/* 176 */         var22 = 5;
/*     */       }
/*     */       
/* 179 */       float var25 = this.field_70146_Z.nextFloat() * 0.05F + 0.025F;
/* 180 */       float var26 = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*     */       
/* 182 */       if (var22 == 0) {
/* 183 */         this.field_70159_w = (-var25);
/* 184 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 187 */       if (var22 == 1) {
/* 188 */         this.field_70159_w = var25;
/* 189 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 192 */       if (var22 == 2) {
/* 193 */         this.field_70181_x = (-var25);
/* 194 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 197 */       if (var22 == 3) {
/* 198 */         this.field_70181_x = var25;
/* 199 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 202 */       if (var22 == 4) {
/* 203 */         this.field_70179_y = (-var25);
/* 204 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 207 */       if (var22 == 5) {
/* 208 */         this.field_70179_y = var25;
/* 209 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 212 */       return true;
/*     */     }
/* 214 */     return false;
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 219 */     this.field_70545_g = value;
/*     */   }
/*     */   
/* 222 */   public int blendmode = 1;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXWispEG.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */