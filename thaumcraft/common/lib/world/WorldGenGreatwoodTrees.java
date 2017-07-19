/*     */ package thaumcraft.common.lib.world;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSapling;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.MobSpawnerBaseLogic;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraftforge.common.ChestGenHooks;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenGreatwoodTrees
/*     */   extends WorldGenAbstractTree
/*     */ {
/*  25 */   static final byte[] otherCoordPairs = { 2, 0, 0, 1, 2, 1 };
/*     */   
/*     */ 
/*  28 */   Random rand = new Random();
/*     */   
/*     */   World worldObj;
/*     */   
/*  32 */   int[] basePos = { 0, 0, 0 };
/*  33 */   int heightLimit = 0;
/*     */   int height;
/*  35 */   double heightAttenuation = 0.618D;
/*  36 */   double branchDensity = 1.0D;
/*  37 */   double branchSlope = 0.38D;
/*  38 */   double scaleWidth = 1.2D;
/*  39 */   double leafDensity = 0.9D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  44 */   int trunkSize = 2;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  49 */   int heightLimitLimit = 11;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  54 */   int leafDistanceLimit = 4;
/*     */   
/*     */   int[][] leafNodes;
/*     */   
/*     */ 
/*     */   public WorldGenGreatwoodTrees(boolean par1)
/*     */   {
/*  61 */     super(par1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNodeList()
/*     */   {
/*  69 */     this.height = ((int)(this.heightLimit * this.heightAttenuation));
/*     */     
/*  71 */     if (this.height >= this.heightLimit)
/*     */     {
/*  73 */       this.height = (this.heightLimit - 1);
/*     */     }
/*     */     
/*  76 */     int var1 = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
/*     */     
/*  78 */     if (var1 < 1)
/*     */     {
/*  80 */       var1 = 1;
/*     */     }
/*     */     
/*  83 */     int[][] var2 = new int[var1 * this.heightLimit][4];
/*  84 */     int var3 = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
/*  85 */     int var4 = 1;
/*  86 */     int var5 = this.basePos[1] + this.height;
/*  87 */     int var6 = var3 - this.basePos[1];
/*  88 */     var2[0][0] = this.basePos[0];
/*  89 */     var2[0][1] = var3;
/*  90 */     var2[0][2] = this.basePos[2];
/*  91 */     var2[0][3] = var5;
/*  92 */     var3--;
/*     */     
/*  94 */     while (var6 >= 0)
/*     */     {
/*  96 */       int var7 = 0;
/*  97 */       float var8 = layerSize(var6);
/*     */       
/*  99 */       if (var8 < 0.0F)
/*     */       {
/* 101 */         var3--;
/* 102 */         var6--;
/*     */       }
/*     */       else
/*     */       {
/* 106 */         for (double var9 = 0.5D; var7 < var1; var7++)
/*     */         {
/* 108 */           double var11 = this.scaleWidth * var8 * (this.rand.nextFloat() + 0.328D);
/* 109 */           double var13 = this.rand.nextFloat() * 2.0D * 3.141592653589793D;
/* 110 */           int var15 = MathHelper.func_76128_c(var11 * Math.sin(var13) + this.basePos[0] + var9);
/* 111 */           int var16 = MathHelper.func_76128_c(var11 * Math.cos(var13) + this.basePos[2] + var9);
/* 112 */           int[] var17 = { var15, var3, var16 };
/* 113 */           int[] var18 = { var15, var3 + this.leafDistanceLimit, var16 };
/*     */           
/* 115 */           if (checkBlockLine(var17, var18) == -1)
/*     */           {
/* 117 */             int[] var19 = { this.basePos[0], this.basePos[1], this.basePos[2] };
/* 118 */             double var20 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - var17[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - var17[2]), 2.0D));
/* 119 */             double var22 = var20 * this.branchSlope;
/*     */             
/* 121 */             if (var17[1] - var22 > var5)
/*     */             {
/* 123 */               var19[1] = var5;
/*     */             }
/*     */             else
/*     */             {
/* 127 */               var19[1] = ((int)(var17[1] - var22));
/*     */             }
/*     */             
/* 130 */             if (checkBlockLine(var19, var17) == -1)
/*     */             {
/* 132 */               var2[var4][0] = var15;
/* 133 */               var2[var4][1] = var3;
/* 134 */               var2[var4][2] = var16;
/* 135 */               var2[var4][3] = var19[1];
/* 136 */               var4++;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 141 */         var3--;
/* 142 */         var6--;
/*     */       }
/*     */     }
/*     */     
/* 146 */     this.leafNodes = new int[var4][4];
/* 147 */     System.arraycopy(var2, 0, this.leafNodes, 0, var4);
/*     */   }
/*     */   
/*     */   void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, Block par6)
/*     */   {
/* 152 */     int var7 = (int)(par4 + 0.618D);
/* 153 */     byte var8 = otherCoordPairs[par5];
/* 154 */     byte var9 = otherCoordPairs[(par5 + 3)];
/* 155 */     int[] var10 = { par1, par2, par3 };
/* 156 */     int[] var11 = { 0, 0, 0 };
/* 157 */     int var12 = -var7;
/* 158 */     int var13 = -var7;
/*     */     
/* 160 */     for (var11[par5] = var10[par5]; var12 <= var7; var12++)
/*     */     {
/* 162 */       var10[var8] += var12;
/* 163 */       var13 = -var7;
/*     */       
/* 165 */       while (var13 <= var7)
/*     */       {
/* 167 */         double var15 = Math.pow(Math.abs(var12) + 0.5D, 2.0D) + Math.pow(Math.abs(var13) + 0.5D, 2.0D);
/*     */         
/* 169 */         if (var15 > par4 * par4)
/*     */         {
/* 171 */           var13++;
/*     */         }
/*     */         else
/*     */         {
/*     */           try {
/* 176 */             var10[var9] += var13;
/* 177 */             Block block = this.worldObj.func_147439_a(var11[0], var11[1], var11[2]);
/*     */             
/* 179 */             if ((block == Blocks.field_150350_a) || (block == ConfigBlocks.blockMagicalLeaves))
/*     */             {
/*     */ 
/* 182 */               if ((block == null) || (block.canBeReplacedByLeaves(this.worldObj, var11[0], var11[1], var11[2])))
/*     */               {
/* 184 */                 func_150516_a(this.worldObj, var11[0], var11[1], var11[2], par6, 0);
/*     */               }
/*     */             }
/*     */           } catch (Exception e) {}
/* 188 */           var13++;
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
/* 199 */     if (par1 < this.heightLimit * 0.3D)
/*     */     {
/* 201 */       return -1.618F;
/*     */     }
/*     */     
/*     */ 
/* 205 */     float var2 = this.heightLimit / 2.0F;
/* 206 */     float var3 = this.heightLimit / 2.0F - par1;
/*     */     float var4;
/*     */     float var4;
/* 209 */     if (var3 == 0.0F)
/*     */     {
/* 211 */       var4 = var2;
/*     */     } else { float var4;
/* 213 */       if (Math.abs(var3) >= var2)
/*     */       {
/* 215 */         var4 = 0.0F;
/*     */       }
/*     */       else
/*     */       {
/* 219 */         var4 = (float)Math.sqrt(Math.pow(Math.abs(var2), 2.0D) - Math.pow(Math.abs(var3), 2.0D));
/*     */       }
/*     */     }
/* 222 */     var4 *= 0.5F;
/* 223 */     return var4;
/*     */   }
/*     */   
/*     */ 
/*     */   float leafSize(int par1)
/*     */   {
/* 229 */     return (par1 >= 0) && (par1 < this.leafDistanceLimit) ? 2.0F : (par1 != 0) && (par1 != this.leafDistanceLimit - 1) ? 3.0F : -1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNode(int par1, int par2, int par3)
/*     */   {
/* 237 */     int var4 = par2;
/*     */     
/* 239 */     for (int var5 = par2 + this.leafDistanceLimit; var4 < var5; var4++)
/*     */     {
/* 241 */       float var6 = leafSize(var4 - par2);
/* 242 */       genTreeLayer(par1, var4, par3, var6, (byte)1, ConfigBlocks.blockMagicalLeaves);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block par3)
/*     */   {
/* 251 */     int[] var4 = { 0, 0, 0 };
/* 252 */     byte var5 = 0;
/*     */     
/*     */ 
/* 255 */     for (byte var6 = 0; var5 < 3; var5 = (byte)(var5 + 1))
/*     */     {
/* 257 */       par2ArrayOfInteger[var5] -= par1ArrayOfInteger[var5];
/*     */       
/* 259 */       if (Math.abs(var4[var5]) > Math.abs(var4[var6]))
/*     */       {
/* 261 */         var6 = var5;
/*     */       }
/*     */     }
/*     */     
/* 265 */     if (var4[var6] != 0)
/*     */     {
/* 267 */       byte var7 = otherCoordPairs[var6];
/* 268 */       byte var8 = otherCoordPairs[(var6 + 3)];
/*     */       byte var9;
/*     */       byte var9;
/* 271 */       if (var4[var6] > 0)
/*     */       {
/* 273 */         var9 = 1;
/*     */       }
/*     */       else
/*     */       {
/* 277 */         var9 = -1;
/*     */       }
/*     */       
/* 280 */       double var10 = var4[var7] / var4[var6];
/* 281 */       double var12 = var4[var8] / var4[var6];
/* 282 */       int[] var14 = { 0, 0, 0 };
/* 283 */       int var15 = 0;
/*     */       
/* 285 */       for (int var16 = var4[var6] + var9; var15 != var16; var15 += var9)
/*     */       {
/* 287 */         var14[var6] = MathHelper.func_76128_c(par1ArrayOfInteger[var6] + var15 + 0.5D);
/* 288 */         var14[var7] = MathHelper.func_76128_c(par1ArrayOfInteger[var7] + var15 * var10 + 0.5D);
/* 289 */         var14[var8] = MathHelper.func_76128_c(par1ArrayOfInteger[var8] + var15 * var12 + 0.5D);
/* 290 */         byte var17 = 0;
/* 291 */         int var18 = Math.abs(var14[0] - par1ArrayOfInteger[0]);
/* 292 */         int var19 = Math.abs(var14[2] - par1ArrayOfInteger[2]);
/* 293 */         int var20 = Math.max(var18, var19);
/*     */         
/* 295 */         if (var20 > 0)
/*     */         {
/* 297 */           if (var18 == var20)
/*     */           {
/* 299 */             var17 = 4;
/*     */           }
/* 301 */           else if (var19 == var20)
/*     */           {
/* 303 */             var17 = 8;
/*     */           }
/*     */         }
/*     */         
/* 307 */         func_150516_a(this.worldObj, var14[0], var14[1], var14[2], par3, var17);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeaves()
/*     */   {
/* 317 */     int var1 = 0;
/*     */     
/* 319 */     for (int var2 = this.leafNodes.length; var1 < var2; var1++)
/*     */     {
/* 321 */       int var3 = this.leafNodes[var1][0];
/* 322 */       int var4 = this.leafNodes[var1][1];
/* 323 */       int var5 = this.leafNodes[var1][2];
/* 324 */       generateLeafNode(var3, var4, var5);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean leafNodeNeedsBase(int par1)
/*     */   {
/* 333 */     return par1 >= this.heightLimit * 0.2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateTrunk()
/*     */   {
/* 342 */     int var1 = this.basePos[0];
/* 343 */     int var2 = this.basePos[1];
/* 344 */     int var3 = this.basePos[1] + this.height;
/* 345 */     int var4 = this.basePos[2];
/* 346 */     int[] var5 = { var1, var2, var4 };
/* 347 */     int[] var6 = { var1, var3, var4 };
/* 348 */     placeBlockLine(var5, var6, ConfigBlocks.blockMagicalLog);
/*     */     
/* 350 */     if (this.trunkSize == 2)
/*     */     {
/* 352 */       var5[0] += 1;
/* 353 */       var6[0] += 1;
/* 354 */       placeBlockLine(var5, var6, ConfigBlocks.blockMagicalLog);
/* 355 */       var5[2] += 1;
/* 356 */       var6[2] += 1;
/* 357 */       placeBlockLine(var5, var6, ConfigBlocks.blockMagicalLog);
/* 358 */       var5[0] += -1;
/* 359 */       var6[0] += -1;
/* 360 */       placeBlockLine(var5, var6, ConfigBlocks.blockMagicalLog);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNodeBases()
/*     */   {
/* 369 */     int var1 = 0;
/* 370 */     int var2 = this.leafNodes.length;
/*     */     
/* 372 */     for (int[] var3 = { this.basePos[0], this.basePos[1], this.basePos[2] }; var1 < var2; var1++)
/*     */     {
/* 374 */       int[] var4 = this.leafNodes[var1];
/* 375 */       int[] var5 = { var4[0], var4[1], var4[2] };
/* 376 */       var3[1] = var4[3];
/* 377 */       int var6 = var3[1] - this.basePos[1];
/*     */       
/* 379 */       if (leafNodeNeedsBase(var6))
/*     */       {
/* 381 */         placeBlockLine(var3, var5, ConfigBlocks.blockMagicalLog);
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
/* 392 */     int[] var3 = { 0, 0, 0 };
/* 393 */     byte var4 = 0;
/*     */     
/*     */ 
/* 396 */     for (byte var5 = 0; var4 < 3; var4 = (byte)(var4 + 1))
/*     */     {
/* 398 */       par2ArrayOfInteger[var4] -= par1ArrayOfInteger[var4];
/*     */       
/* 400 */       if (Math.abs(var3[var4]) > Math.abs(var3[var5]))
/*     */       {
/* 402 */         var5 = var4;
/*     */       }
/*     */     }
/*     */     
/* 406 */     if (var3[var5] == 0)
/*     */     {
/* 408 */       return -1;
/*     */     }
/*     */     
/*     */ 
/* 412 */     byte var6 = otherCoordPairs[var5];
/* 413 */     byte var7 = otherCoordPairs[(var5 + 3)];
/*     */     byte var8;
/*     */     byte var8;
/* 416 */     if (var3[var5] > 0)
/*     */     {
/* 418 */       var8 = 1;
/*     */     }
/*     */     else
/*     */     {
/* 422 */       var8 = -1;
/*     */     }
/*     */     
/* 425 */     double var9 = var3[var6] / var3[var5];
/* 426 */     double var11 = var3[var7] / var3[var5];
/* 427 */     int[] var13 = { 0, 0, 0 };
/* 428 */     int var14 = 0;
/*     */     
/*     */ 
/* 431 */     for (int var15 = var3[var5] + var8; var14 != var15; var14 += var8)
/*     */     {
/* 433 */       par1ArrayOfInteger[var5] += var14;
/* 434 */       var13[var6] = MathHelper.func_76128_c(par1ArrayOfInteger[var6] + var14 * var9);
/* 435 */       var13[var7] = MathHelper.func_76128_c(par1ArrayOfInteger[var7] + var14 * var11);
/*     */       try {
/* 437 */         Block var16 = this.worldObj.func_147439_a(var13[0], var13[1], var13[2]);
/*     */         
/* 439 */         if ((var16 != Blocks.field_150350_a) && (var16 != ConfigBlocks.blockMagicalLeaves)) {
/*     */           break;
/*     */         }
/*     */       }
/*     */       catch (Exception e) {}
/*     */     }
/*     */     
/*     */ 
/* 447 */     return var14 == var15 ? -1 : Math.abs(var14);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean validTreeLocation(int x, int z)
/*     */   {
/* 457 */     int[] var1 = { this.basePos[0] + x, this.basePos[1], this.basePos[2] + z };
/* 458 */     int[] var2 = { this.basePos[0] + x, this.basePos[1] + this.heightLimit - 1, this.basePos[2] + z };
/*     */     try {
/* 460 */       Block var3 = this.worldObj.func_147439_a(this.basePos[0] + x, this.basePos[1] - 1, this.basePos[2] + z);
/* 461 */       boolean isSoil = var3.canSustainPlant(this.worldObj, this.basePos[0] + x, this.basePos[1] - 1, this.basePos[2] + z, ForgeDirection.UP, (BlockSapling)Blocks.field_150345_g);
/* 462 */       if (!isSoil)
/*     */       {
/* 464 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 468 */       int var4 = checkBlockLine(var1, var2);
/*     */       
/* 470 */       if (var4 == -1)
/*     */       {
/* 472 */         return true;
/*     */       }
/* 474 */       if (var4 < 6)
/*     */       {
/* 476 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 480 */       this.heightLimit = var4;
/* 481 */       return true;
/*     */     }
/*     */     catch (Exception e) {}
/*     */     
/* 485 */     return false;
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
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5, boolean spiders)
/*     */   {
/* 507 */     this.worldObj = par1World;
/* 508 */     long var6 = par2Random.nextLong();
/* 509 */     this.rand.setSeed(var6);
/* 510 */     this.basePos[0] = par3;
/* 511 */     this.basePos[1] = par4;
/* 512 */     this.basePos[2] = par5;
/*     */     
/* 514 */     if (this.heightLimit == 0)
/*     */     {
/* 516 */       this.heightLimit = (this.heightLimitLimit + this.rand.nextInt(this.heightLimitLimit));
/*     */     }
/*     */     
/* 519 */     int x = 0;
/* 520 */     int z = 0;
/* 521 */     boolean valid = false;
/*     */     
/*     */ 
/* 524 */     for (int a = -1; a < 2; a++) {
/*     */       label189:
/* 526 */       for (int b = -1; b < 2; b++) {
/* 527 */         for (x = 0; x < this.trunkSize; x++) {
/* 528 */           for (z = 0; z < this.trunkSize; z++) {
/* 529 */             if (!validTreeLocation(x + a, z + b))
/*     */               break label189;
/*     */           }
/*     */         }
/* 533 */         valid = true;
/* 534 */         this.basePos[0] += a;
/* 535 */         this.basePos[2] += b;
/*     */         
/*     */         break label201;
/*     */       }
/*     */     }
/*     */     label201:
/* 541 */     if (!valid) { return false;
/*     */     }
/* 543 */     generateLeafNodeList();
/* 544 */     generateLeaves();
/* 545 */     generateLeafNodeBases();
/* 546 */     generateTrunk();
/*     */     
/* 548 */     this.scaleWidth = 1.66D;
/*     */     
/* 550 */     this.basePos[0] = par3;
/* 551 */     this.basePos[1] = (par4 + this.height);
/* 552 */     this.basePos[2] = par5;
/* 553 */     generateLeafNodeList();
/* 554 */     generateLeaves();
/* 555 */     generateLeafNodeBases();
/* 556 */     generateTrunk();
/*     */     
/* 558 */     if (spiders) {
/* 559 */       this.worldObj.func_147465_d(par3, par4 - 1, par5, Blocks.field_150474_ac, 0, 3);
/* 560 */       TileEntityMobSpawner var14 = (TileEntityMobSpawner)par1World.func_147438_o(par3, par4 - 1, par5);
/* 561 */       if (var14 != null)
/*     */       {
/* 563 */         var14.func_145881_a().func_98272_a("CaveSpider");
/* 564 */         for (int a = 0; a < 50; a++) {
/* 565 */           int xx = par3 - 7 + par2Random.nextInt(14);
/* 566 */           int yy = par4 + par2Random.nextInt(10);
/* 567 */           int zz = par5 - 7 + par2Random.nextInt(14);
/* 568 */           if ((par1World.func_147437_c(xx, yy, zz)) && ((BlockUtils.isBlockTouching(par1World, xx, yy, zz, ConfigBlocks.blockMagicalLeaves)) || (BlockUtils.isBlockTouching(par1World, xx, yy, zz, ConfigBlocks.blockMagicalLog))))
/*     */           {
/*     */ 
/* 571 */             this.worldObj.func_147465_d(xx, yy, zz, Blocks.field_150321_G, 0, 3);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 578 */         par1World.func_147465_d(par3, par4 - 2, par5, Blocks.field_150486_ae, 0, 3);
/* 579 */         TileEntityChest var16 = (TileEntityChest)par1World.func_147438_o(par3, par4 - 2, par5);
/*     */         
/* 581 */         if (var16 != null)
/*     */         {
/* 583 */           ChestGenHooks loot = ChestGenHooks.getInfo("dungeonChest");
/* 584 */           WeightedRandomChestContent.func_76293_a(this.rand, loot.getItems(this.rand), var16, loot.getCount(this.rand));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 591 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World var1, Random var2, int var3, int var4, int var5)
/*     */   {
/* 596 */     return generate(var1, var2, var3, var4, var5, var2.nextInt(8) == 0);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/WorldGenGreatwoodTrees.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */