/*     */ package thaumcraft.common.lib.world;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSapling;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenSilverwoodTreesOld
/*     */   extends WorldGenAbstractTree
/*     */ {
/*  21 */   static final byte[] otherCoordPairs = { 2, 0, 0, 1, 2, 1 };
/*     */   
/*     */ 
/*  24 */   Random rand = new Random();
/*     */   
/*     */   World worldObj;
/*     */   
/*  28 */   int[] basePos = { 0, 0, 0 };
/*  29 */   int heightLimit = 0;
/*     */   int height;
/*  31 */   double heightAttenuation = 0.618D;
/*  32 */   double branchDensity = 1.0D;
/*  33 */   double branchSlope = -0.3D;
/*  34 */   double scaleWidth = 0.9D;
/*  35 */   double leafDensity = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  40 */   int trunkSize = 1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  45 */   int heightLimitLimit = 8;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  50 */   int leafDistanceLimit = 4;
/*     */   
/*     */   int[][] leafNodes;
/*     */   
/*     */ 
/*     */   public WorldGenSilverwoodTreesOld(boolean par1)
/*     */   {
/*  57 */     super(par1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNodeList()
/*     */   {
/*  65 */     this.height = ((int)(this.heightLimit * this.heightAttenuation));
/*     */     
/*  67 */     if (this.height >= this.heightLimit)
/*     */     {
/*  69 */       this.height = (this.heightLimit - 1);
/*     */     }
/*     */     
/*  72 */     int var1 = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
/*     */     
/*  74 */     if (var1 < 1)
/*     */     {
/*  76 */       var1 = 1;
/*     */     }
/*     */     
/*  79 */     int[][] var2 = new int[var1 * this.heightLimit][4];
/*  80 */     int var3 = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
/*  81 */     int var4 = 1;
/*  82 */     int var5 = this.basePos[1] + this.height;
/*  83 */     int var6 = var3 - this.basePos[1];
/*  84 */     var2[0][0] = this.basePos[0];
/*  85 */     var2[0][1] = var3;
/*  86 */     var2[0][2] = this.basePos[2];
/*  87 */     var2[0][3] = var5;
/*  88 */     var3--;
/*     */     
/*  90 */     while (var6 >= 0)
/*     */     {
/*  92 */       int var7 = 0;
/*  93 */       float var8 = layerSize(var6);
/*     */       
/*  95 */       if (var8 < 0.0F)
/*     */       {
/*  97 */         var3--;
/*  98 */         var6--;
/*     */       }
/*     */       else
/*     */       {
/* 102 */         for (double var9 = 0.5D; var7 < var1; var7++)
/*     */         {
/* 104 */           double var11 = this.scaleWidth * var8 * (this.rand.nextFloat() + 0.328D);
/* 105 */           double var13 = this.rand.nextFloat() * 2.0D * 3.141592653589793D;
/* 106 */           int var15 = MathHelper.func_76128_c(var11 * Math.sin(var13) + this.basePos[0] + var9);
/* 107 */           int var16 = MathHelper.func_76128_c(var11 * Math.cos(var13) + this.basePos[2] + var9);
/* 108 */           int[] var17 = { var15, var3, var16 };
/* 109 */           int[] var18 = { var15, var3 + this.leafDistanceLimit, var16 };
/*     */           
/* 111 */           if (checkBlockLine(var17, var18) == -1)
/*     */           {
/* 113 */             int[] var19 = { this.basePos[0], this.basePos[1], this.basePos[2] };
/* 114 */             double var20 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - var17[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - var17[2]), 2.0D));
/* 115 */             double var22 = var20 * this.branchSlope;
/*     */             
/* 117 */             if (var17[1] - var22 > var5)
/*     */             {
/* 119 */               var19[1] = var5;
/*     */             }
/*     */             else
/*     */             {
/* 123 */               var19[1] = ((int)(var17[1] - var22));
/*     */             }
/*     */             
/* 126 */             if (checkBlockLine(var19, var17) == -1)
/*     */             {
/* 128 */               var2[var4][0] = var15;
/* 129 */               var2[var4][1] = var3;
/* 130 */               var2[var4][2] = var16;
/* 131 */               var2[var4][3] = var19[1];
/* 132 */               var4++;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 137 */         var3--;
/* 138 */         var6--;
/*     */       }
/*     */     }
/*     */     
/* 142 */     this.leafNodes = new int[var4][4];
/* 143 */     System.arraycopy(var2, 0, this.leafNodes, 0, var4);
/*     */   }
/*     */   
/*     */   void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, Block par6)
/*     */   {
/* 148 */     int var7 = (int)(par4 + 0.618D);
/* 149 */     byte var8 = otherCoordPairs[par5];
/* 150 */     byte var9 = otherCoordPairs[(par5 + 3)];
/* 151 */     int[] var10 = { par1, par2, par3 };
/* 152 */     int[] var11 = { 0, 0, 0 };
/* 153 */     int var12 = -var7;
/* 154 */     int var13 = -var7;
/*     */     
/* 156 */     for (var11[par5] = var10[par5]; var12 <= var7; var12++)
/*     */     {
/* 158 */       var10[var8] += var12;
/* 159 */       var13 = -var7;
/*     */       
/* 161 */       while (var13 <= var7)
/*     */       {
/* 163 */         double var15 = Math.pow(Math.abs(var12) + 0.5D, 2.0D) + Math.pow(Math.abs(var13) + 0.5D, 2.0D);
/*     */         
/* 165 */         if (var15 > par4 * par4)
/*     */         {
/* 167 */           var13++;
/*     */         }
/*     */         else
/*     */         {
/* 171 */           var10[var9] += var13;
/* 172 */           Block block = this.worldObj.func_147439_a(var11[0], var11[1], var11[2]);
/*     */           
/* 174 */           if ((block == Blocks.field_150350_a) || (block == ConfigBlocks.blockMagicalLeaves))
/*     */           {
/*     */ 
/* 177 */             if ((block == null) || (block.canBeReplacedByLeaves(this.worldObj, var11[0], var11[1], var11[2])))
/*     */             {
/* 179 */               func_150516_a(this.worldObj, var11[0], var11[1], var11[2], par6, 1);
/*     */             }
/*     */           }
/* 182 */           var13++;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   float layerSize(int par1)
/*     */   {
/* 193 */     if (par1 < this.heightLimit * 0.3D)
/*     */     {
/* 195 */       return -1.618F;
/*     */     }
/*     */     
/*     */ 
/* 199 */     float var2 = this.heightLimit / 2.0F;
/* 200 */     float var3 = this.heightLimit / 2.0F - par1;
/*     */     float var4;
/*     */     float var4;
/* 203 */     if (var3 == 0.0F)
/*     */     {
/* 205 */       var4 = var2;
/*     */     } else { float var4;
/* 207 */       if (Math.abs(var3) >= var2)
/*     */       {
/* 209 */         var4 = 0.0F;
/*     */       }
/*     */       else
/*     */       {
/* 213 */         var4 = (float)Math.sqrt(Math.pow(Math.abs(var2), 2.0D) - Math.pow(Math.abs(var3), 2.0D));
/*     */       }
/*     */     }
/* 216 */     var4 *= 0.5F;
/* 217 */     return var4;
/*     */   }
/*     */   
/*     */ 
/*     */   float leafSize(int par1)
/*     */   {
/* 223 */     return (par1 >= 0) && (par1 < this.leafDistanceLimit) ? 2.0F : (par1 != 0) && (par1 != this.leafDistanceLimit - 1) ? 3.0F : -1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNode(int par1, int par2, int par3)
/*     */   {
/* 231 */     int var4 = par2;
/*     */     
/* 233 */     for (int var5 = par2 + this.leafDistanceLimit; var4 < var5; var4++)
/*     */     {
/* 235 */       float var6 = leafSize(var4 - par2);
/* 236 */       genTreeLayer(par1, var4, par3, var6, (byte)1, ConfigBlocks.blockMagicalLeaves);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block par3)
/*     */   {
/* 245 */     int[] var4 = { 0, 0, 0 };
/* 246 */     byte var5 = 0;
/*     */     
/*     */ 
/* 249 */     for (byte var6 = 0; var5 < 3; var5 = (byte)(var5 + 1))
/*     */     {
/* 251 */       par2ArrayOfInteger[var5] -= par1ArrayOfInteger[var5];
/*     */       
/* 253 */       if (Math.abs(var4[var5]) > Math.abs(var4[var6]))
/*     */       {
/* 255 */         var6 = var5;
/*     */       }
/*     */     }
/*     */     
/* 259 */     if (var4[var6] != 0)
/*     */     {
/* 261 */       byte var7 = otherCoordPairs[var6];
/* 262 */       byte var8 = otherCoordPairs[(var6 + 3)];
/*     */       byte var9;
/*     */       byte var9;
/* 265 */       if (var4[var6] > 0)
/*     */       {
/* 267 */         var9 = 1;
/*     */       }
/*     */       else
/*     */       {
/* 271 */         var9 = -1;
/*     */       }
/*     */       
/* 274 */       double var10 = var4[var7] / var4[var6];
/* 275 */       double var12 = var4[var8] / var4[var6];
/* 276 */       int[] var14 = { 0, 0, 0 };
/* 277 */       int var15 = 0;
/*     */       
/* 279 */       for (int var16 = var4[var6] + var9; var15 != var16; var15 += var9)
/*     */       {
/* 281 */         var14[var6] = MathHelper.func_76128_c(par1ArrayOfInteger[var6] + var15 + 0.5D);
/* 282 */         var14[var7] = MathHelper.func_76128_c(par1ArrayOfInteger[var7] + var15 * var10 + 0.5D);
/* 283 */         var14[var8] = MathHelper.func_76128_c(par1ArrayOfInteger[var8] + var15 * var12 + 0.5D);
/* 284 */         byte var17 = 0;
/* 285 */         int var18 = Math.abs(var14[0] - par1ArrayOfInteger[0]);
/* 286 */         int var19 = Math.abs(var14[2] - par1ArrayOfInteger[2]);
/* 287 */         int var20 = Math.max(var18, var19);
/*     */         
/* 289 */         if (var20 > 0)
/*     */         {
/* 291 */           if (var18 == var20)
/*     */           {
/* 293 */             var17 = 4;
/*     */           }
/* 295 */           else if (var19 == var20)
/*     */           {
/* 297 */             var17 = 8;
/*     */           }
/*     */         }
/* 300 */         if (this.rand.nextInt(50) == 0) {
/* 301 */           func_150516_a(this.worldObj, var14[0], var14[1], var14[2], par3, var17 + 2);
/* 302 */           ThaumcraftWorldGenerator.createRandomNodeAt(this.worldObj, var14[0], var14[1], var14[2], this.rand, true, false, false);
/*     */         }
/*     */         else {
/* 305 */           func_150516_a(this.worldObj, var14[0], var14[1], var14[2], par3, var17 + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void generateLeaves()
/*     */   {
/* 315 */     int var1 = 0;
/*     */     
/* 317 */     for (int var2 = this.leafNodes.length; var1 < var2; var1++)
/*     */     {
/* 319 */       int var3 = this.leafNodes[var1][0];
/* 320 */       int var4 = this.leafNodes[var1][1];
/* 321 */       int var5 = this.leafNodes[var1][2];
/* 322 */       generateLeafNode(var3, var4, var5);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean leafNodeNeedsBase(int par1)
/*     */   {
/* 331 */     return par1 >= this.heightLimit * 0.2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateTrunk()
/*     */   {
/* 340 */     int var1 = this.basePos[0];
/* 341 */     int var2 = this.basePos[1];
/* 342 */     int var3 = this.basePos[1] + this.height;
/* 343 */     int var4 = this.basePos[2];
/* 344 */     int[] var5 = { var1, var2, var4 };
/* 345 */     int[] var6 = { var1, var3, var4 };
/* 346 */     placeBlockLine(var5, var6, ConfigBlocks.blockMagicalLog);
/*     */     
/* 348 */     if (this.trunkSize == 2)
/*     */     {
/* 350 */       var5[0] += 1;
/* 351 */       var6[0] += 1;
/* 352 */       placeBlockLine(var5, var6, ConfigBlocks.blockMagicalLog);
/* 353 */       var5[2] += 1;
/* 354 */       var6[2] += 1;
/* 355 */       placeBlockLine(var5, var6, ConfigBlocks.blockMagicalLog);
/* 356 */       var5[0] += -1;
/* 357 */       var6[0] += -1;
/* 358 */       placeBlockLine(var5, var6, ConfigBlocks.blockMagicalLog);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNodeBases()
/*     */   {
/* 367 */     int var1 = 0;
/* 368 */     int var2 = this.leafNodes.length;
/*     */     
/* 370 */     for (int[] var3 = { this.basePos[0], this.basePos[1], this.basePos[2] }; var1 < var2; var1++)
/*     */     {
/* 372 */       int[] var4 = this.leafNodes[var1];
/* 373 */       int[] var5 = { var4[0], var4[1], var4[2] };
/* 374 */       var3[1] = var4[3];
/* 375 */       int var6 = var3[1] - this.basePos[1];
/*     */       
/* 377 */       if (leafNodeNeedsBase(var6))
/*     */       {
/* 379 */         placeBlockLine(var3, var5, ConfigBlocks.blockMagicalLog);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger)
/*     */   {
/* 390 */     int[] var3 = { 0, 0, 0 };
/* 391 */     byte var4 = 0;
/*     */     
/*     */ 
/* 394 */     for (byte var5 = 0; var4 < 3; var4 = (byte)(var4 + 1))
/*     */     {
/* 396 */       par2ArrayOfInteger[var4] -= par1ArrayOfInteger[var4];
/*     */       
/* 398 */       if (Math.abs(var3[var4]) > Math.abs(var3[var5]))
/*     */       {
/* 400 */         var5 = var4;
/*     */       }
/*     */     }
/*     */     
/* 404 */     if (var3[var5] == 0)
/*     */     {
/* 406 */       return -1;
/*     */     }
/*     */     
/*     */ 
/* 410 */     byte var6 = otherCoordPairs[var5];
/* 411 */     byte var7 = otherCoordPairs[(var5 + 3)];
/*     */     byte var8;
/*     */     byte var8;
/* 414 */     if (var3[var5] > 0)
/*     */     {
/* 416 */       var8 = 1;
/*     */     }
/*     */     else
/*     */     {
/* 420 */       var8 = -1;
/*     */     }
/*     */     
/* 423 */     double var9 = var3[var6] / var3[var5];
/* 424 */     double var11 = var3[var7] / var3[var5];
/* 425 */     int[] var13 = { 0, 0, 0 };
/* 426 */     int var14 = 0;
/*     */     
/*     */ 
/* 429 */     for (int var15 = var3[var5] + var8; var14 != var15; var14 += var8)
/*     */     {
/* 431 */       par1ArrayOfInteger[var5] += var14;
/* 432 */       var13[var6] = MathHelper.func_76128_c(par1ArrayOfInteger[var6] + var14 * var9);
/* 433 */       var13[var7] = MathHelper.func_76128_c(par1ArrayOfInteger[var7] + var14 * var11);
/* 434 */       Block var16 = this.worldObj.func_147439_a(var13[0], var13[1], var13[2]);
/*     */       
/* 436 */       if ((var16 != Blocks.field_150350_a) && (var16 != ConfigBlocks.blockMagicalLeaves)) {
/*     */         break;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 442 */     return var14 == var15 ? -1 : Math.abs(var14);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean validTreeLocation(int x, int z)
/*     */   {
/* 452 */     int[] var1 = { this.basePos[0] + x, this.basePos[1], this.basePos[2] + z };
/* 453 */     int[] var2 = { this.basePos[0] + x, this.basePos[1] + this.heightLimit - 1, this.basePos[2] + z };
/* 454 */     Block var3 = this.worldObj.func_147439_a(this.basePos[0] + x, this.basePos[1] - 1, this.basePos[2] + z);
/* 455 */     boolean isSoil = var3.canSustainPlant(this.worldObj, this.basePos[0] + x, this.basePos[1] - 1, this.basePos[2] + z, ForgeDirection.UP, (BlockSapling)Blocks.field_150345_g);
/* 456 */     if (!isSoil)
/*     */     {
/* 458 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 462 */     int var4 = checkBlockLine(var1, var2);
/*     */     
/* 464 */     if (var4 == -1)
/*     */     {
/* 466 */       return true;
/*     */     }
/* 468 */     if (var4 < 6)
/*     */     {
/* 470 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 474 */     this.heightLimit = var4;
/* 475 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76487_a(double par1, double par3, double par5) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5)
/*     */   {
/* 498 */     this.worldObj = par1World;
/* 499 */     long var6 = par2Random.nextLong();
/*     */     
/* 501 */     this.rand.setSeed(var6);
/* 502 */     this.basePos[0] = par3;
/* 503 */     this.basePos[1] = par4;
/* 504 */     this.basePos[2] = par5;
/*     */     
/* 506 */     if (this.heightLimit == 0)
/*     */     {
/*     */ 
/* 509 */       this.heightLimit = (this.heightLimitLimit / 2 + this.rand.nextInt(this.heightLimitLimit));
/*     */     }
/*     */     
/* 512 */     int x = 0;
/* 513 */     int z = 0;
/* 514 */     for (x = 0; x < this.trunkSize; x++) {
/* 515 */       for (z = 0; z < this.trunkSize; z++) {
/* 516 */         if (!validTreeLocation(x, z))
/*     */         {
/* 518 */           return false; }
/*     */       }
/*     */     }
/* 521 */     generateLeafNodeList();
/* 522 */     generateLeaves();
/* 523 */     generateLeafNodeBases();
/* 524 */     generateTrunk();
/*     */     
/* 526 */     this.basePos[0] = par3;
/* 527 */     this.basePos[1] = (par4 + this.height);
/* 528 */     this.basePos[2] = par5;
/* 529 */     generateLeafNodeList();
/* 530 */     generateLeaves();
/* 531 */     generateLeafNodeBases();
/* 532 */     generateTrunk();
/*     */     
/* 534 */     WorldGenerator flowers = new WorldGenCustomFlowers(ConfigBlocks.blockCustomPlant, 2);
/* 535 */     flowers.func_76484_a(par1World, par2Random, par3, par4, par5);
/*     */     
/* 537 */     this.basePos[0] = par3;
/*     */     
/* 539 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/WorldGenSilverwoodTreesOld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */