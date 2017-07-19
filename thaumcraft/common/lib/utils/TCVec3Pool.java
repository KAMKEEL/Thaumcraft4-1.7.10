/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TCVec3Pool
/*     */ {
/*     */   private final int truncateArrayResetThreshold;
/*     */   private final int minimumSize;
/*  16 */   private final List vec3Cache = new ArrayList();
/*  17 */   private int nextFreeSpace = 0;
/*  18 */   private int maximumSizeSinceLastTruncation = 0;
/*  19 */   private int resetCount = 0;
/*     */   
/*     */   public TCVec3Pool(int par1, int par2)
/*     */   {
/*  23 */     this.truncateArrayResetThreshold = par1;
/*  24 */     this.minimumSize = par2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 getVecFromPool(double par1, double par3, double par5)
/*     */   {
/*  32 */     if (func_82589_e())
/*     */     {
/*  34 */       return new TCVec3(this, par1, par3, par5);
/*     */     }
/*     */     
/*     */ 
/*     */     TCVec3 var7;
/*     */     
/*  40 */     if (this.nextFreeSpace >= this.vec3Cache.size())
/*     */     {
/*  42 */       TCVec3 var7 = new TCVec3(this, par1, par3, par5);
/*  43 */       this.vec3Cache.add(var7);
/*     */     }
/*     */     else
/*     */     {
/*  47 */       var7 = (TCVec3)this.vec3Cache.get(this.nextFreeSpace);
/*  48 */       var7.setComponents(par1, par3, par5);
/*     */     }
/*     */     
/*  51 */     this.nextFreeSpace += 1;
/*  52 */     return var7;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  61 */     if (!func_82589_e())
/*     */     {
/*  63 */       if (this.nextFreeSpace > this.maximumSizeSinceLastTruncation)
/*     */       {
/*  65 */         this.maximumSizeSinceLastTruncation = this.nextFreeSpace;
/*     */       }
/*     */       
/*  68 */       if (this.resetCount++ == this.truncateArrayResetThreshold)
/*     */       {
/*  70 */         int var1 = Math.max(this.maximumSizeSinceLastTruncation, this.vec3Cache.size() - this.minimumSize);
/*     */         
/*  72 */         while (this.vec3Cache.size() > var1)
/*     */         {
/*  74 */           this.vec3Cache.remove(var1);
/*     */         }
/*     */         
/*  77 */         this.maximumSizeSinceLastTruncation = 0;
/*  78 */         this.resetCount = 0;
/*     */       }
/*     */       
/*  81 */       this.nextFreeSpace = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void clearAndFreeCache()
/*     */   {
/*  88 */     if (!func_82589_e())
/*     */     {
/*  90 */       this.nextFreeSpace = 0;
/*  91 */       this.vec3Cache.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public int getPoolSize()
/*     */   {
/*  97 */     return this.vec3Cache.size();
/*     */   }
/*     */   
/*     */   public int func_82590_d()
/*     */   {
/* 102 */     return this.nextFreeSpace;
/*     */   }
/*     */   
/*     */   private boolean func_82589_e()
/*     */   {
/* 107 */     return (this.minimumSize < 0) || (this.truncateArrayResetThreshold < 0);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/utils/TCVec3Pool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */