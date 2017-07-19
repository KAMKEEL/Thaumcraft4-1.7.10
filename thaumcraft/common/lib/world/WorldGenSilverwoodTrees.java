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
/*     */ public class WorldGenSilverwoodTrees
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private final int minTreeHeight;
/*     */   private final int randomTreeHeight;
/*  19 */   boolean worldgen = false;
/*     */   
/*     */   public WorldGenSilverwoodTrees(boolean doBlockNotify, int minTreeHeight, int randomTreeHeight)
/*     */   {
/*  23 */     super(doBlockNotify);
/*     */     
/*  25 */     this.worldgen = (!doBlockNotify);
/*  26 */     this.minTreeHeight = minTreeHeight;
/*  27 */     this.randomTreeHeight = randomTreeHeight;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_76484_a(World world, Random random, int x, int y, int z)
/*     */   {
/*  35 */     int height = random.nextInt(this.randomTreeHeight) + this.minTreeHeight;
/*     */     
/*  37 */     boolean flag = true;
/*     */     
/*  39 */     if ((y >= 1) && (y + height + 1 <= 256))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  44 */       for (int i1 = y; i1 <= y + 1 + height; i1++)
/*     */       {
/*  46 */         byte spread = 1;
/*     */         
/*  48 */         if (i1 == y)
/*     */         {
/*  50 */           spread = 0;
/*     */         }
/*     */         
/*  53 */         if (i1 >= y + 1 + height - 2)
/*     */         {
/*  55 */           spread = 3;
/*     */         }
/*     */         
/*  58 */         for (int j1 = x - spread; (j1 <= x + spread) && (flag); j1++)
/*     */         {
/*  60 */           for (int k1 = z - spread; (k1 <= z + spread) && (flag); k1++)
/*     */           {
/*  62 */             if ((i1 >= 0) && (i1 < 256))
/*     */             {
/*  64 */               Block block = world.func_147439_a(j1, i1, k1);
/*     */               
/*  66 */               if ((!block.isAir(world, j1, i1, k1)) && (!block.isLeaves(world, j1, i1, k1)) && (!block.isReplaceable(world, j1, i1, k1)))
/*     */               {
/*     */ 
/*  69 */                 if (i1 > y)
/*     */                 {
/*  71 */                   flag = false;
/*     */                 }
/*     */               }
/*     */             }
/*     */             else
/*     */             {
/*  77 */               flag = false;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  83 */       if (!flag)
/*     */       {
/*  85 */         return false;
/*     */       }
/*     */       
/*     */ 
/*  89 */       Block block1 = world.func_147439_a(x, y - 1, z);
/*     */       
/*  91 */       boolean isSoil = block1.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling)Blocks.field_150345_g);
/*  92 */       if ((isSoil) && (y < 256 - height - 1))
/*     */       {
/*  94 */         block1.onPlantGrow(world, x, y - 1, z, x, y, z);
/*  95 */         int start = y + height - 5;
/*  96 */         int end = y + height + 3 + random.nextInt(3);
/*     */         
/*     */ 
/*  99 */         for (int k2 = start; k2 <= end; k2++)
/*     */         {
/* 101 */           int cty = MathHelper.func_76125_a(k2, y + height - 3, y + height);
/*     */           
/* 103 */           for (int xx = x - 5; xx <= x + 5; xx++)
/*     */           {
/* 105 */             for (int zz = z - 5; zz <= z + 5; zz++)
/*     */             {
/* 107 */               double d3 = xx - x;
/* 108 */               double d4 = k2 - cty;
/* 109 */               double d5 = zz - z;
/* 110 */               double dist = d3 * d3 + d4 * d4 + d5 * d5;
/*     */               
/* 112 */               if ((dist < 10 + random.nextInt(8)) && (world.func_147439_a(xx, k2, zz).canBeReplacedByLeaves(world, xx, k2, zz)))
/*     */               {
/*     */ 
/* 115 */                 func_150516_a(world, xx, k2, zz, ConfigBlocks.blockMagicalLeaves, 1);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 123 */         int chance = (int)(height * 1.5D);
/* 124 */         boolean lastblock = false;
/* 125 */         for (k2 = 0; k2 < height; k2++)
/*     */         {
/* 127 */           Block block2 = world.func_147439_a(x, y + k2, z);
/*     */           
/* 129 */           if ((block2.isAir(world, x, y + k2, z)) || (block2.isLeaves(world, x, y + k2, z)) || (block2.isReplaceable(world, x, y + k2, z)))
/*     */           {
/*     */ 
/* 132 */             if ((k2 > 0) && (!lastblock) && (random.nextInt(chance) == 0)) {
/* 133 */               func_150516_a(world, x, y + k2, z, ConfigBlocks.blockMagicalLog, 2);
/* 134 */               ThaumcraftWorldGenerator.createRandomNodeAt(world, x, y + k2, z, random, true, false, false);
/* 135 */               chance += height;
/* 136 */               lastblock = true;
/*     */             } else {
/* 138 */               func_150516_a(world, x, y + k2, z, ConfigBlocks.blockMagicalLog, 1);
/* 139 */               lastblock = false;
/*     */             }
/* 141 */             func_150516_a(world, x - 1, y + k2, z, ConfigBlocks.blockMagicalLog, 1);
/* 142 */             func_150516_a(world, x + 1, y + k2, z, ConfigBlocks.blockMagicalLog, 1);
/* 143 */             func_150516_a(world, x, y + k2, z - 1, ConfigBlocks.blockMagicalLog, 1);
/* 144 */             func_150516_a(world, x, y + k2, z + 1, ConfigBlocks.blockMagicalLog, 1);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 149 */         func_150516_a(world, x, y + k2, z, ConfigBlocks.blockMagicalLog, 1);
/* 150 */         func_150516_a(world, x - 1, y, z - 1, ConfigBlocks.blockMagicalLog, 1);
/* 151 */         func_150516_a(world, x + 1, y, z + 1, ConfigBlocks.blockMagicalLog, 1);
/* 152 */         func_150516_a(world, x - 1, y, z + 1, ConfigBlocks.blockMagicalLog, 1);
/* 153 */         func_150516_a(world, x + 1, y, z - 1, ConfigBlocks.blockMagicalLog, 1);
/* 154 */         if (random.nextInt(3) != 0) func_150516_a(world, x - 1, y + 1, z - 1, ConfigBlocks.blockMagicalLog, 1);
/* 155 */         if (random.nextInt(3) != 0) func_150516_a(world, x + 1, y + 1, z + 1, ConfigBlocks.blockMagicalLog, 1);
/* 156 */         if (random.nextInt(3) != 0) func_150516_a(world, x - 1, y + 1, z + 1, ConfigBlocks.blockMagicalLog, 1);
/* 157 */         if (random.nextInt(3) != 0) func_150516_a(world, x + 1, y + 1, z - 1, ConfigBlocks.blockMagicalLog, 1);
/* 158 */         func_150516_a(world, x - 2, y, z, ConfigBlocks.blockMagicalLog, 5);
/* 159 */         func_150516_a(world, x + 2, y, z, ConfigBlocks.blockMagicalLog, 5);
/* 160 */         func_150516_a(world, x, y, z - 2, ConfigBlocks.blockMagicalLog, 9);
/* 161 */         func_150516_a(world, x, y, z + 2, ConfigBlocks.blockMagicalLog, 9);
/* 162 */         func_150516_a(world, x - 2, y - 1, z, ConfigBlocks.blockMagicalLog, 1);
/* 163 */         func_150516_a(world, x + 2, y - 1, z, ConfigBlocks.blockMagicalLog, 1);
/* 164 */         func_150516_a(world, x, y - 1, z - 2, ConfigBlocks.blockMagicalLog, 1);
/* 165 */         func_150516_a(world, x, y - 1, z + 2, ConfigBlocks.blockMagicalLog, 1);
/* 166 */         func_150516_a(world, x - 1, y + (height - 4), z - 1, ConfigBlocks.blockMagicalLog, 1);
/* 167 */         func_150516_a(world, x + 1, y + (height - 4), z + 1, ConfigBlocks.blockMagicalLog, 1);
/* 168 */         func_150516_a(world, x - 1, y + (height - 4), z + 1, ConfigBlocks.blockMagicalLog, 1);
/* 169 */         func_150516_a(world, x + 1, y + (height - 4), z - 1, ConfigBlocks.blockMagicalLog, 1);
/* 170 */         if (random.nextInt(3) == 0) func_150516_a(world, x - 1, y + (height - 5), z - 1, ConfigBlocks.blockMagicalLog, 1);
/* 171 */         if (random.nextInt(3) == 0) func_150516_a(world, x + 1, y + (height - 5), z + 1, ConfigBlocks.blockMagicalLog, 1);
/* 172 */         if (random.nextInt(3) == 0) func_150516_a(world, x - 1, y + (height - 5), z + 1, ConfigBlocks.blockMagicalLog, 1);
/* 173 */         if (random.nextInt(3) == 0) func_150516_a(world, x + 1, y + (height - 5), z - 1, ConfigBlocks.blockMagicalLog, 1);
/* 174 */         func_150516_a(world, x - 2, y + (height - 4), z, ConfigBlocks.blockMagicalLog, 5);
/* 175 */         func_150516_a(world, x + 2, y + (height - 4), z, ConfigBlocks.blockMagicalLog, 5);
/* 176 */         func_150516_a(world, x, y + (height - 4), z - 2, ConfigBlocks.blockMagicalLog, 9);
/* 177 */         func_150516_a(world, x, y + (height - 4), z + 2, ConfigBlocks.blockMagicalLog, 9);
/*     */         
/* 179 */         if (this.worldgen) {
/* 180 */           WorldGenerator flowers = new WorldGenCustomFlowers(ConfigBlocks.blockCustomPlant, 2);
/* 181 */           flowers.func_76484_a(world, random, x, y, z);
/*     */         }
/*     */         
/* 184 */         return true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 190 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 196 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/WorldGenSilverwoodTrees.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */