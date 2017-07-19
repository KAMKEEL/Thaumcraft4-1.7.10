/*     */ package thaumcraft.common.lib.world;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSapling;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenBigMagicTree
/*     */   extends WorldGenAbstractTree
/*     */ {
/*  19 */   static final byte[] otherCoordPairs = { 2, 0, 0, 1, 2, 1 };
/*     */   
/*     */ 
/*  22 */   Random rand = new Random();
/*     */   
/*     */   World worldObj;
/*     */   
/*  26 */   int[] basePos = { 0, 0, 0 };
/*     */   int heightLimit;
/*     */   int height;
/*  29 */   double heightAttenuation = 0.6618D;
/*  30 */   double branchDensity = 1.0D;
/*  31 */   double branchSlope = 0.381D;
/*  32 */   double scaleWidth = 1.0D;
/*  33 */   double leafDensity = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  38 */   int trunkSize = 1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  43 */   int heightLimitLimit = 12;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  48 */   int leafDistanceLimit = 3;
/*     */   
/*     */   int[][] leafNodes;
/*     */   
/*     */ 
/*     */   public WorldGenBigMagicTree(boolean par1)
/*     */   {
/*  55 */     super(par1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNodeList()
/*     */   {
/*  63 */     this.height = ((int)(this.heightLimit * this.heightAttenuation));
/*     */     
/*  65 */     if (this.height >= this.heightLimit)
/*     */     {
/*  67 */       this.height = (this.heightLimit - 1);
/*     */     }
/*     */     
/*  70 */     int i = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
/*     */     
/*  72 */     if (i < 1)
/*     */     {
/*  74 */       i = 1;
/*     */     }
/*     */     
/*  77 */     int[][] aint = new int[i * this.heightLimit][4];
/*  78 */     int j = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
/*  79 */     int k = 1;
/*  80 */     int l = this.basePos[1] + this.height;
/*  81 */     int i1 = j - this.basePos[1];
/*  82 */     aint[0][0] = this.basePos[0];
/*  83 */     aint[0][1] = j;
/*  84 */     aint[0][2] = this.basePos[2];
/*  85 */     aint[0][3] = l;
/*  86 */     j--;
/*     */     
/*  88 */     while (i1 >= 0)
/*     */     {
/*  90 */       int j1 = 0;
/*  91 */       float f = layerSize(i1);
/*     */       
/*  93 */       if (f < 0.0F)
/*     */       {
/*  95 */         j--;
/*  96 */         i1--;
/*     */       }
/*     */       else
/*     */       {
/* 100 */         for (double d0 = 0.5D; j1 < i; j1++)
/*     */         {
/* 102 */           double d1 = this.scaleWidth * f * (this.rand.nextFloat() + 0.328D);
/* 103 */           double d2 = this.rand.nextFloat() * 2.0D * 3.141592653589793D;
/* 104 */           int k1 = MathHelper.func_76128_c(d1 * Math.sin(d2) + this.basePos[0] + d0);
/* 105 */           int l1 = MathHelper.func_76128_c(d1 * Math.cos(d2) + this.basePos[2] + d0);
/* 106 */           int[] aint1 = { k1, j, l1 };
/* 107 */           int[] aint2 = { k1, j + this.leafDistanceLimit, l1 };
/*     */           
/* 109 */           if (checkBlockLine(aint1, aint2) == -1)
/*     */           {
/* 111 */             int[] aint3 = { this.basePos[0], this.basePos[1], this.basePos[2] };
/* 112 */             double d3 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - aint1[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - aint1[2]), 2.0D));
/* 113 */             double d4 = d3 * this.branchSlope;
/*     */             
/* 115 */             if (aint1[1] - d4 > l)
/*     */             {
/* 117 */               aint3[1] = l;
/*     */             }
/*     */             else
/*     */             {
/* 121 */               aint3[1] = ((int)(aint1[1] - d4));
/*     */             }
/*     */             
/* 124 */             if (checkBlockLine(aint3, aint1) == -1)
/*     */             {
/* 126 */               aint[k][0] = k1;
/* 127 */               aint[k][1] = j;
/* 128 */               aint[k][2] = l1;
/* 129 */               aint[k][3] = aint3[1];
/* 130 */               k++;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 135 */         j--;
/* 136 */         i1--;
/*     */       }
/*     */     }
/*     */     
/* 140 */     this.leafNodes = new int[k][4];
/* 141 */     System.arraycopy(aint, 0, this.leafNodes, 0, k);
/*     */   }
/*     */   
/*     */   void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, Block par6)
/*     */   {
/* 146 */     int i1 = (int)(par4 + 0.618D);
/* 147 */     byte b1 = otherCoordPairs[par5];
/* 148 */     byte b2 = otherCoordPairs[(par5 + 3)];
/* 149 */     int[] aint = { par1, par2, par3 };
/* 150 */     int[] aint1 = { 0, 0, 0 };
/* 151 */     int j1 = -i1;
/* 152 */     int k1 = -i1;
/*     */     
/* 154 */     for (aint1[par5] = aint[par5]; j1 <= i1; j1++)
/*     */     {
/* 156 */       aint[b1] += j1;
/* 157 */       k1 = -i1;
/*     */       
/* 159 */       while (k1 <= i1)
/*     */       {
/* 161 */         double d0 = Math.pow(Math.abs(j1) + 0.5D, 2.0D) + Math.pow(Math.abs(k1) + 0.5D, 2.0D);
/*     */         
/* 163 */         if (d0 > par4 * par4)
/*     */         {
/* 165 */           k1++;
/*     */         }
/*     */         else
/*     */         {
/* 169 */           aint[b2] += k1;
/*     */           try {
/* 171 */             Block l1 = this.worldObj.func_147439_a(aint1[0], aint1[1], aint1[2]);
/*     */             
/* 173 */             if ((l1 != Blocks.field_150350_a) && (l1 != Blocks.field_150362_t))
/*     */             {
/* 175 */               k1++;
/*     */             }
/*     */             else
/*     */             {
/* 179 */               func_150516_a(this.worldObj, aint1[0], aint1[1], aint1[2], par6, 0);
/* 180 */               k1++;
/*     */             }
/*     */           }
/*     */           catch (Exception e) {}
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
/* 194 */     if (par1 < this.heightLimit * 0.3D)
/*     */     {
/* 196 */       return -1.618F;
/*     */     }
/*     */     
/*     */ 
/* 200 */     float f = this.heightLimit / 2.0F;
/* 201 */     float f1 = this.heightLimit / 2.0F - par1;
/*     */     float f2;
/*     */     float f2;
/* 204 */     if (f1 == 0.0F)
/*     */     {
/* 206 */       f2 = f;
/*     */     } else { float f2;
/* 208 */       if (Math.abs(f1) >= f)
/*     */       {
/* 210 */         f2 = 0.0F;
/*     */       }
/*     */       else
/*     */       {
/* 214 */         f2 = (float)Math.sqrt(Math.pow(Math.abs(f), 2.0D) - Math.pow(Math.abs(f1), 2.0D));
/*     */       }
/*     */     }
/* 217 */     f2 *= 0.5F;
/* 218 */     return f2;
/*     */   }
/*     */   
/*     */ 
/*     */   float leafSize(int par1)
/*     */   {
/* 224 */     return (par1 >= 0) && (par1 < this.leafDistanceLimit) ? 2.0F : (par1 != 0) && (par1 != this.leafDistanceLimit - 1) ? 3.0F : -1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNode(int par1, int par2, int par3)
/*     */   {
/* 232 */     int l = par2;
/*     */     
/* 234 */     for (int i1 = par2 + this.leafDistanceLimit; l < i1; l++)
/*     */     {
/* 236 */       float f = leafSize(l - par2);
/* 237 */       genTreeLayer(par1, l, par3, f, (byte)1, Blocks.field_150362_t);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block par3)
/*     */   {
/* 246 */     int[] aint2 = { 0, 0, 0 };
/* 247 */     byte b0 = 0;
/*     */     
/*     */ 
/* 250 */     for (byte b1 = 0; b0 < 3; b0 = (byte)(b0 + 1))
/*     */     {
/* 252 */       par2ArrayOfInteger[b0] -= par1ArrayOfInteger[b0];
/*     */       
/* 254 */       if (Math.abs(aint2[b0]) > Math.abs(aint2[b1]))
/*     */       {
/* 256 */         b1 = b0;
/*     */       }
/*     */     }
/*     */     
/* 260 */     if (aint2[b1] != 0)
/*     */     {
/* 262 */       byte b2 = otherCoordPairs[b1];
/* 263 */       byte b3 = otherCoordPairs[(b1 + 3)];
/*     */       byte b4;
/*     */       byte b4;
/* 266 */       if (aint2[b1] > 0)
/*     */       {
/* 268 */         b4 = 1;
/*     */       }
/*     */       else
/*     */       {
/* 272 */         b4 = -1;
/*     */       }
/*     */       
/* 275 */       double d0 = aint2[b2] / aint2[b1];
/* 276 */       double d1 = aint2[b3] / aint2[b1];
/* 277 */       int[] aint3 = { 0, 0, 0 };
/* 278 */       int j = 0;
/*     */       
/* 280 */       for (int k = aint2[b1] + b4; j != k; j += b4)
/*     */       {
/* 282 */         aint3[b1] = MathHelper.func_76128_c(par1ArrayOfInteger[b1] + j + 0.5D);
/* 283 */         aint3[b2] = MathHelper.func_76128_c(par1ArrayOfInteger[b2] + j * d0 + 0.5D);
/* 284 */         aint3[b3] = MathHelper.func_76128_c(par1ArrayOfInteger[b3] + j * d1 + 0.5D);
/* 285 */         byte b5 = 0;
/* 286 */         int l = Math.abs(aint3[0] - par1ArrayOfInteger[0]);
/* 287 */         int i1 = Math.abs(aint3[2] - par1ArrayOfInteger[2]);
/* 288 */         int j1 = Math.max(l, i1);
/*     */         
/* 290 */         if (j1 > 0)
/*     */         {
/* 292 */           if (l == j1)
/*     */           {
/* 294 */             b5 = 4;
/*     */           }
/* 296 */           else if (i1 == j1)
/*     */           {
/* 298 */             b5 = 8;
/*     */           }
/*     */         }
/*     */         
/* 302 */         func_150516_a(this.worldObj, aint3[0], aint3[1], aint3[2], par3, b5);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeaves()
/*     */   {
/* 312 */     int i = 0;
/*     */     try
/*     */     {
/* 315 */       for (int j = this.leafNodes.length; i < j; i++)
/*     */       {
/* 317 */         int k = this.leafNodes[i][0];
/* 318 */         int l = this.leafNodes[i][1];
/* 319 */         int i1 = this.leafNodes[i][2];
/* 320 */         generateLeafNode(k, l, i1);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
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
/* 340 */     int i = this.basePos[0];
/* 341 */     int j = this.basePos[1];
/* 342 */     int k = this.basePos[1] + this.height;
/* 343 */     int l = this.basePos[2];
/* 344 */     int[] aint = { i, j, l };
/* 345 */     int[] aint1 = { i, k, l };
/* 346 */     placeBlockLine(aint, aint1, Blocks.field_150364_r);
/*     */     
/* 348 */     if (this.trunkSize == 2)
/*     */     {
/* 350 */       aint[0] += 1;
/* 351 */       aint1[0] += 1;
/* 352 */       placeBlockLine(aint, aint1, Blocks.field_150364_r);
/* 353 */       aint[2] += 1;
/* 354 */       aint1[2] += 1;
/* 355 */       placeBlockLine(aint, aint1, Blocks.field_150364_r);
/* 356 */       aint[0] += -1;
/* 357 */       aint1[0] += -1;
/* 358 */       placeBlockLine(aint, aint1, Blocks.field_150364_r);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNodeBases()
/*     */   {
/* 367 */     int i = 0;
/* 368 */     int j = this.leafNodes.length;
/*     */     
/* 370 */     for (int[] aint = { this.basePos[0], this.basePos[1], this.basePos[2] }; i < j; i++)
/*     */     {
/* 372 */       int[] aint1 = this.leafNodes[i];
/* 373 */       int[] aint2 = { aint1[0], aint1[1], aint1[2] };
/* 374 */       aint[1] = aint1[3];
/* 375 */       int k = aint[1] - this.basePos[1];
/*     */       
/* 377 */       if (leafNodeNeedsBase(k))
/*     */       {
/* 379 */         placeBlockLine(aint, aint2, Blocks.field_150364_r);
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
/* 390 */     int[] aint2 = { 0, 0, 0 };
/* 391 */     byte b0 = 0;
/*     */     
/*     */ 
/* 394 */     for (byte b1 = 0; b0 < 3; b0 = (byte)(b0 + 1))
/*     */     {
/* 396 */       par2ArrayOfInteger[b0] -= par1ArrayOfInteger[b0];
/*     */       
/* 398 */       if (Math.abs(aint2[b0]) > Math.abs(aint2[b1]))
/*     */       {
/* 400 */         b1 = b0;
/*     */       }
/*     */     }
/*     */     
/* 404 */     if (aint2[b1] == 0)
/*     */     {
/* 406 */       return -1;
/*     */     }
/*     */     
/*     */ 
/* 410 */     byte b2 = otherCoordPairs[b1];
/* 411 */     byte b3 = otherCoordPairs[(b1 + 3)];
/*     */     byte b4;
/*     */     byte b4;
/* 414 */     if (aint2[b1] > 0)
/*     */     {
/* 416 */       b4 = 1;
/*     */     }
/*     */     else
/*     */     {
/* 420 */       b4 = -1;
/*     */     }
/*     */     
/* 423 */     double d0 = aint2[b2] / aint2[b1];
/* 424 */     double d1 = aint2[b3] / aint2[b1];
/* 425 */     int[] aint3 = { 0, 0, 0 };
/* 426 */     int i = 0;
/* 427 */     int j = 0;
/*     */     try
/*     */     {
/* 430 */       for (j = aint2[b1] + b4; i != j; i += b4) {
/* 431 */         par1ArrayOfInteger[b1] += i;
/* 432 */         aint3[b2] = MathHelper.func_76128_c(par1ArrayOfInteger[b2] + i * d0);
/*     */         
/*     */ 
/* 435 */         aint3[b3] = MathHelper.func_76128_c(par1ArrayOfInteger[b3] + i * d1);
/*     */         
/*     */ 
/* 438 */         Block k = this.worldObj.func_147439_a(aint3[0], aint3[1], aint3[2]);
/*     */         
/*     */ 
/* 441 */         if ((k != Blocks.field_150350_a) && (k != Blocks.field_150362_t)) {
/*     */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/* 447 */     return i == j ? -1 : Math.abs(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean validTreeLocation()
/*     */   {
/* 457 */     int[] aint = { this.basePos[0], this.basePos[1], this.basePos[2] };
/* 458 */     int[] aint1 = { this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2] };
/* 459 */     Block soil = this.worldObj.func_147439_a(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
/*     */     
/* 461 */     boolean isValidSoil = (soil != null) && (soil.canSustainPlant(this.worldObj, this.basePos[0], this.basePos[1] - 1, this.basePos[2], ForgeDirection.UP, (BlockSapling)Blocks.field_150345_g));
/* 462 */     if (!isValidSoil)
/*     */     {
/* 464 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 468 */     int j = checkBlockLine(aint, aint1);
/*     */     
/* 470 */     if (j == -1)
/*     */     {
/* 472 */       return true;
/*     */     }
/* 474 */     if (j < 6)
/*     */     {
/* 476 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 480 */     this.heightLimit = j;
/* 481 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76487_a(double par1, double par3, double par5)
/*     */   {
/* 491 */     this.heightLimitLimit = ((int)(par1 * 12.0D));
/*     */     
/* 493 */     if (par1 > 0.5D)
/*     */     {
/* 495 */       this.leafDistanceLimit = 3;
/*     */     }
/*     */     
/* 498 */     this.scaleWidth = par3;
/* 499 */     this.leafDensity = (par5 * 0.8D);
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5)
/*     */   {
/* 504 */     this.worldObj = par1World;
/* 505 */     long l = par2Random.nextLong();
/* 506 */     this.rand.setSeed(l);
/* 507 */     this.basePos[0] = par3;
/* 508 */     this.basePos[1] = par4;
/* 509 */     this.basePos[2] = par5;
/*     */     
/* 511 */     if (this.heightLimit == 0)
/*     */     {
/* 513 */       this.heightLimit = (11 + this.rand.nextInt(this.heightLimitLimit));
/*     */     }
/*     */     
/* 516 */     if (!validTreeLocation())
/*     */     {
/* 518 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 522 */     generateLeafNodeList();
/* 523 */     generateLeaves();
/* 524 */     generateTrunk();
/* 525 */     generateLeafNodeBases();
/* 526 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/WorldGenBigMagicTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */