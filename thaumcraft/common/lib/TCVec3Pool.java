/*     */ package thaumcraft.common.lib;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TCVec3Pool
/*     */ {
/*     */   private final int truncateArrayResetThreshold;
/*     */   private final int minimumSize;
/*  15 */   private final List vec3Cache = new ArrayList();
/*  16 */   private int nextFreeSpace = 0;
/*  17 */   private int maximumSizeSinceLastTruncation = 0;
/*  18 */   private int resetCount = 0;
/*     */   
/*     */   public TCVec3Pool(int par1, int par2)
/*     */   {
/*  22 */     this.truncateArrayResetThreshold = par1;
/*  23 */     this.minimumSize = par2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TCVec3 getVecFromPool(double par1, double par3, double par5)
/*     */   {
/*  31 */     if (func_82589_e())
/*     */     {
/*  33 */       return new TCVec3(this, par1, par3, par5);
/*     */     }
/*     */     
/*     */ 
/*     */     TCVec3 var7;
/*     */     
/*  39 */     if (this.nextFreeSpace >= this.vec3Cache.size())
/*     */     {
/*  41 */       TCVec3 var7 = new TCVec3(this, par1, par3, par5);
/*  42 */       this.vec3Cache.add(var7);
/*     */     }
/*     */     else
/*     */     {
/*  46 */       var7 = (TCVec3)this.vec3Cache.get(this.nextFreeSpace);
/*  47 */       var7.setComponents(par1, par3, par5);
/*     */     }
/*     */     
/*  50 */     this.nextFreeSpace += 1;
/*  51 */     return var7;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  60 */     if (!func_82589_e())
/*     */     {
/*  62 */       if (this.nextFreeSpace > this.maximumSizeSinceLastTruncation)
/*     */       {
/*  64 */         this.maximumSizeSinceLastTruncation = this.nextFreeSpace;
/*     */       }
/*     */       
/*  67 */       if (this.resetCount++ == this.truncateArrayResetThreshold)
/*     */       {
/*  69 */         int var1 = Math.max(this.maximumSizeSinceLastTruncation, this.vec3Cache.size() - this.minimumSize);
/*     */         
/*  71 */         while (this.vec3Cache.size() > var1)
/*     */         {
/*  73 */           this.vec3Cache.remove(var1);
/*     */         }
/*     */         
/*  76 */         this.maximumSizeSinceLastTruncation = 0;
/*  77 */         this.resetCount = 0;
/*     */       }
/*     */       
/*  80 */       this.nextFreeSpace = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void clearAndFreeCache()
/*     */   {
/*  87 */     if (!func_82589_e())
/*     */     {
/*  89 */       this.nextFreeSpace = 0;
/*  90 */       this.vec3Cache.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public int getPoolSize()
/*     */   {
/*  96 */     return this.vec3Cache.size();
/*     */   }
/*     */   
/*     */   public int func_82590_d()
/*     */   {
/* 101 */     return this.nextFreeSpace;
/*     */   }
/*     */   
/*     */   private boolean func_82589_e()
/*     */   {
/* 106 */     return (this.minimumSize < 0) || (this.truncateArrayResetThreshold < 0);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/TCVec3Pool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */