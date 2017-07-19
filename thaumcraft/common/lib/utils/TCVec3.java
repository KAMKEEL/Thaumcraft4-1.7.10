/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class TCVec3
/*     */ {
/*   9 */   public static final TCVec3Pool vec3dPool = new TCVec3Pool(-1, -1);
/*     */   
/*     */ 
/*     */   public final TCVec3Pool myVec3LocalPool;
/*     */   
/*     */ 
/*     */   public double xCoord;
/*     */   
/*     */ 
/*     */   public double yCoord;
/*     */   
/*     */ 
/*     */   public double zCoord;
/*     */   
/*     */ 
/*     */ 
/*     */   public static TCVec3 createVectorHelper(double par0, double par2, double par4)
/*     */   {
/*  27 */     return new TCVec3(vec3dPool, par0, par2, par4);
/*     */   }
/*     */   
/*     */   protected TCVec3(TCVec3Pool par1Vec3Pool, double par2, double par4, double par6)
/*     */   {
/*  32 */     if (par2 == -0.0D)
/*     */     {
/*  34 */       par2 = 0.0D;
/*     */     }
/*     */     
/*  37 */     if (par4 == -0.0D)
/*     */     {
/*  39 */       par4 = 0.0D;
/*     */     }
/*     */     
/*  42 */     if (par6 == -0.0D)
/*     */     {
/*  44 */       par6 = 0.0D;
/*     */     }
/*     */     
/*  47 */     this.xCoord = par2;
/*  48 */     this.yCoord = par4;
/*  49 */     this.zCoord = par6;
/*  50 */     this.myVec3LocalPool = par1Vec3Pool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected TCVec3 setComponents(double par1, double par3, double par5)
/*     */   {
/*  58 */     this.xCoord = par1;
/*  59 */     this.yCoord = par3;
/*  60 */     this.zCoord = par5;
/*  61 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public TCVec3 subtract(TCVec3 par1Vec3)
/*     */   {
/*  71 */     return this.myVec3LocalPool.getVecFromPool(par1Vec3.xCoord - this.xCoord, par1Vec3.yCoord - this.yCoord, par1Vec3.zCoord - this.zCoord);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 normalize()
/*     */   {
/*  79 */     double var1 = MathHelper.func_76133_a(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
/*  80 */     return var1 < 1.0E-4D ? this.myVec3LocalPool.getVecFromPool(0.0D, 0.0D, 0.0D) : this.myVec3LocalPool.getVecFromPool(this.xCoord / var1, this.yCoord / var1, this.zCoord / var1);
/*     */   }
/*     */   
/*     */   public double dotProduct(TCVec3 par1Vec3)
/*     */   {
/*  85 */     return this.xCoord * par1Vec3.xCoord + this.yCoord * par1Vec3.yCoord + this.zCoord * par1Vec3.zCoord;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public TCVec3 crossProduct(TCVec3 par1Vec3)
/*     */   {
/*  95 */     return this.myVec3LocalPool.getVecFromPool(this.yCoord * par1Vec3.zCoord - this.zCoord * par1Vec3.yCoord, this.zCoord * par1Vec3.xCoord - this.xCoord * par1Vec3.zCoord, this.xCoord * par1Vec3.yCoord - this.yCoord * par1Vec3.xCoord);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 addVector(double par1, double par3, double par5)
/*     */   {
/* 104 */     return this.myVec3LocalPool.getVecFromPool(this.xCoord + par1, this.yCoord + par3, this.zCoord + par5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double distanceTo(TCVec3 par1Vec3)
/*     */   {
/* 112 */     double var2 = par1Vec3.xCoord - this.xCoord;
/* 113 */     double var4 = par1Vec3.yCoord - this.yCoord;
/* 114 */     double var6 = par1Vec3.zCoord - this.zCoord;
/* 115 */     return MathHelper.func_76133_a(var2 * var2 + var4 * var4 + var6 * var6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double squareDistanceTo(TCVec3 par1Vec3)
/*     */   {
/* 123 */     double var2 = par1Vec3.xCoord - this.xCoord;
/* 124 */     double var4 = par1Vec3.yCoord - this.yCoord;
/* 125 */     double var6 = par1Vec3.zCoord - this.zCoord;
/* 126 */     return var2 * var2 + var4 * var4 + var6 * var6;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double squareDistanceTo(double par1, double par3, double par5)
/*     */   {
/* 134 */     double var7 = par1 - this.xCoord;
/* 135 */     double var9 = par3 - this.yCoord;
/* 136 */     double var11 = par5 - this.zCoord;
/* 137 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double lengthVector()
/*     */   {
/* 145 */     return MathHelper.func_76133_a(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 getIntermediateWithXValue(TCVec3 par1Vec3, double par2)
/*     */   {
/* 154 */     double var4 = par1Vec3.xCoord - this.xCoord;
/* 155 */     double var6 = par1Vec3.yCoord - this.yCoord;
/* 156 */     double var8 = par1Vec3.zCoord - this.zCoord;
/*     */     
/* 158 */     if (var4 * var4 < 1.0000000116860974E-7D)
/*     */     {
/* 160 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 164 */     double var10 = (par2 - this.xCoord) / var4;
/* 165 */     return (var10 >= 0.0D) && (var10 <= 1.0D) ? this.myVec3LocalPool.getVecFromPool(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 getIntermediateWithYValue(TCVec3 par1Vec3, double par2)
/*     */   {
/* 175 */     double var4 = par1Vec3.xCoord - this.xCoord;
/* 176 */     double var6 = par1Vec3.yCoord - this.yCoord;
/* 177 */     double var8 = par1Vec3.zCoord - this.zCoord;
/*     */     
/* 179 */     if (var6 * var6 < 1.0000000116860974E-7D)
/*     */     {
/* 181 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 185 */     double var10 = (par2 - this.yCoord) / var6;
/* 186 */     return (var10 >= 0.0D) && (var10 <= 1.0D) ? this.myVec3LocalPool.getVecFromPool(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 getIntermediateWithZValue(TCVec3 par1Vec3, double par2)
/*     */   {
/* 196 */     double var4 = par1Vec3.xCoord - this.xCoord;
/* 197 */     double var6 = par1Vec3.yCoord - this.yCoord;
/* 198 */     double var8 = par1Vec3.zCoord - this.zCoord;
/*     */     
/* 200 */     if (var8 * var8 < 1.0000000116860974E-7D)
/*     */     {
/* 202 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 206 */     double var10 = (par2 - this.zCoord) / var8;
/* 207 */     return (var10 >= 0.0D) && (var10 <= 1.0D) ? this.myVec3LocalPool.getVecFromPool(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 213 */     return "(" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void rotateAroundX(float par1)
/*     */   {
/* 221 */     float var2 = MathHelper.func_76134_b(par1);
/* 222 */     float var3 = MathHelper.func_76126_a(par1);
/* 223 */     double var4 = this.xCoord;
/* 224 */     double var6 = this.yCoord * var2 + this.zCoord * var3;
/* 225 */     double var8 = this.zCoord * var2 - this.yCoord * var3;
/* 226 */     this.xCoord = var4;
/* 227 */     this.yCoord = var6;
/* 228 */     this.zCoord = var8;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void rotateAroundY(float par1)
/*     */   {
/* 236 */     float var2 = MathHelper.func_76134_b(par1);
/* 237 */     float var3 = MathHelper.func_76126_a(par1);
/* 238 */     double var4 = this.xCoord * var2 + this.zCoord * var3;
/* 239 */     double var6 = this.yCoord;
/* 240 */     double var8 = this.zCoord * var2 - this.xCoord * var3;
/* 241 */     this.xCoord = var4;
/* 242 */     this.yCoord = var6;
/* 243 */     this.zCoord = var8;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void rotateAroundZ(float par1)
/*     */   {
/* 252 */     float var2 = MathHelper.func_76134_b(par1);
/* 253 */     float var3 = MathHelper.func_76126_a(par1);
/* 254 */     double var4 = this.xCoord * var2 + this.yCoord * var3;
/* 255 */     double var6 = this.yCoord * var2 - this.xCoord * var3;
/* 256 */     double var8 = this.zCoord;
/* 257 */     this.xCoord = var4;
/* 258 */     this.yCoord = var6;
/* 259 */     this.zCoord = var8;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/utils/TCVec3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */