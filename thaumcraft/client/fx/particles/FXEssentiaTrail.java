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
/*     */ 
/*     */ public class FXEssentiaTrail extends EntityFX
/*     */ {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*  19 */   private int count = 0;
/*     */   
/*     */ 
/*     */   public FXEssentiaTrail(World par1World, double par2, double par4, double par6, double tx, double ty, double tz, int count, int color, float scale)
/*     */   {
/*  24 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  25 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 0.6F);
/*  26 */     this.field_70544_f = ((MathHelper.func_76126_a(count / 2.0F) * 0.1F + 1.0F) * scale);
/*     */     
/*  28 */     this.count = count;
/*  29 */     this.targetX = tx;
/*  30 */     this.targetY = ty;
/*  31 */     this.targetZ = tz;
/*     */     
/*  33 */     double dx = tx - this.field_70165_t;
/*  34 */     double dy = ty - this.field_70163_u;
/*  35 */     double dz = tz - this.field_70161_v;
/*  36 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 30.0F);
/*  37 */     if (base < 1)
/*  38 */       base = 1;
/*  39 */     this.field_70547_e = (base / 2 + this.field_70146_Z.nextInt(base));
/*     */     
/*  41 */     this.field_70159_w = (MathHelper.func_76126_a(count / 4.0F) * 0.015F + this.field_70146_Z.nextGaussian() * 0.0020000000949949026D);
/*     */     
/*  43 */     this.field_70181_x = (0.1F + MathHelper.func_76126_a(count / 3.0F) * 0.01F);
/*  44 */     this.field_70179_y = (MathHelper.func_76126_a(count / 2.0F) * 0.015F + this.field_70146_Z.nextGaussian() * 0.0020000000949949026D);
/*     */     
/*     */ 
/*  47 */     Color c = new Color(color);
/*  48 */     float mr = c.getRed() / 255.0F * 0.2F;
/*  49 */     float mg = c.getGreen() / 255.0F * 0.2F;
/*  50 */     float mb = c.getBlue() / 255.0F * 0.2F;
/*  51 */     this.field_70552_h = (c.getRed() / 255.0F - mr + this.field_70146_Z.nextFloat() * mr);
/*  52 */     this.field_70553_i = (c.getGreen() / 255.0F - mg + this.field_70146_Z.nextFloat() * mg);
/*  53 */     this.field_70551_j = (c.getBlue() / 255.0F - mb + this.field_70146_Z.nextFloat() * mb);
/*     */     
/*  55 */     this.field_70545_g = 0.2F;
/*  56 */     this.field_70145_X = false;
/*     */     try
/*     */     {
/*  59 */       EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*     */       
/*  61 */       int visibleDistance = 64;
/*  62 */       if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j)
/*  63 */         visibleDistance = 32;
/*  64 */       if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) {
/*  65 */         this.field_70547_e = 0;
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  74 */     float t2 = 0.5625F;
/*  75 */     float t3 = 0.625F;
/*  76 */     float t4 = 0.0625F;
/*  77 */     float t5 = 0.125F;
/*     */     
/*  79 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/*  80 */     int part = this.particle + this.field_70546_d % 16;
/*     */     
/*  82 */     float s = MathHelper.func_76126_a((this.field_70546_d - this.count) / 5.0F) * 0.25F + 1.0F;
/*     */     
/*  84 */     float var12 = 0.1F * this.field_70544_f * s;
/*     */     
/*  86 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*     */     
/*  88 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*     */     
/*  90 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*     */     
/*  92 */     float var16 = 1.0F;
/*     */     
/*  94 */     tessellator.func_78380_c(240);
/*     */     
/*  96 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F);
/*     */     
/*  98 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, t2, t5);
/*     */     
/*     */ 
/* 101 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, t3, t5);
/*     */     
/*     */ 
/* 104 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, t3, t4);
/*     */     
/*     */ 
/* 107 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, t2, t4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 115 */     return 1;
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/* 119 */     this.field_70169_q = this.field_70165_t;
/* 120 */     this.field_70167_r = this.field_70163_u;
/* 121 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 123 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 124 */       func_70106_y();
/* 125 */       return;
/*     */     }
/*     */     
/* 128 */     this.field_70181_x += 0.01D * this.field_70545_g;
/* 129 */     if (!this.field_70145_X)
/* 130 */       pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 131 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 133 */     this.field_70159_w *= 0.985D;
/* 134 */     this.field_70181_x *= 0.985D;
/* 135 */     this.field_70179_y *= 0.985D;
/*     */     
/* 137 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.05F, 0.05F);
/* 138 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.05F, 0.05F);
/* 139 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.05F, 0.05F);
/*     */     
/* 141 */     double dx = this.targetX - this.field_70165_t;
/* 142 */     double dy = this.targetY - this.field_70163_u;
/* 143 */     double dz = this.targetZ - this.field_70161_v;
/* 144 */     double d13 = 0.01D;
/* 145 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 147 */     if (d11 < 2.0D) {
/* 148 */       this.field_70544_f *= 0.98F;
/*     */     }
/*     */     
/* 151 */     if (this.field_70544_f < 0.2F) {
/* 152 */       func_70106_y();
/* 153 */       return;
/*     */     }
/*     */     
/* 156 */     dx /= d11;
/* 157 */     dy /= d11;
/* 158 */     dz /= d11;
/*     */     
/* 160 */     this.field_70159_w += dx * (d13 / Math.min(1.0D, d11));
/* 161 */     this.field_70181_x += dy * (d13 / Math.min(1.0D, d11));
/* 162 */     this.field_70179_y += dz * (d13 / Math.min(1.0D, d11));
/*     */   }
/*     */   
/*     */   public void setGravity(float value)
/*     */   {
/* 167 */     this.field_70545_g = value;
/*     */   }
/*     */   
/*     */   protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
/* 171 */     int var7 = MathHelper.func_76128_c(par1);
/* 172 */     int var8 = MathHelper.func_76128_c(par3);
/* 173 */     int var9 = MathHelper.func_76128_c(par5);
/* 174 */     double var10 = par1 - var7;
/* 175 */     double var12 = par3 - var8;
/* 176 */     double var14 = par5 - var9;
/*     */     
/* 178 */     if ((!this.field_70170_p.func_147437_c(var7, var8, var9)) && (this.field_70170_p.func_147445_c(var7, var8, var9, true)) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */     {
/*     */ 
/* 181 */       boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, true);
/*     */       
/* 183 */       boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, true);
/*     */       
/* 185 */       boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, true);
/*     */       
/* 187 */       boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, true);
/*     */       
/* 189 */       boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, true);
/*     */       
/* 191 */       boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, true);
/*     */       
/* 193 */       byte var22 = -1;
/* 194 */       double var23 = 9999.0D;
/*     */       
/* 196 */       if ((var16) && (var10 < var23)) {
/* 197 */         var23 = var10;
/* 198 */         var22 = 0;
/*     */       }
/*     */       
/* 201 */       if ((var17) && (1.0D - var10 < var23)) {
/* 202 */         var23 = 1.0D - var10;
/* 203 */         var22 = 1;
/*     */       }
/*     */       
/* 206 */       if ((var18) && (var12 < var23)) {
/* 207 */         var23 = var12;
/* 208 */         var22 = 2;
/*     */       }
/*     */       
/* 211 */       if ((var19) && (1.0D - var12 < var23)) {
/* 212 */         var23 = 1.0D - var12;
/* 213 */         var22 = 3;
/*     */       }
/*     */       
/* 216 */       if ((var20) && (var14 < var23)) {
/* 217 */         var23 = var14;
/* 218 */         var22 = 4;
/*     */       }
/*     */       
/* 221 */       if ((var21) && (1.0D - var14 < var23)) {
/* 222 */         var23 = 1.0D - var14;
/* 223 */         var22 = 5;
/*     */       }
/*     */       
/* 226 */       float var25 = this.field_70146_Z.nextFloat() * 0.05F + 0.025F;
/* 227 */       float var26 = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*     */       
/* 229 */       if (var22 == 0) {
/* 230 */         this.field_70159_w = (-var25);
/* 231 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 234 */       if (var22 == 1) {
/* 235 */         this.field_70159_w = var25;
/* 236 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 239 */       if (var22 == 2) {
/* 240 */         this.field_70181_x = (-var25);
/* 241 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 244 */       if (var22 == 3) {
/* 245 */         this.field_70181_x = var25;
/* 246 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 249 */       if (var22 == 4) {
/* 250 */         this.field_70179_y = (-var25);
/* 251 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 254 */       if (var22 == 5) {
/* 255 */         this.field_70179_y = var25;
/* 256 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 259 */       return true;
/*     */     }
/* 261 */     return false;
/*     */   }
/*     */   
/*     */ 
/* 265 */   public int particle = 24;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXEssentiaTrail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */