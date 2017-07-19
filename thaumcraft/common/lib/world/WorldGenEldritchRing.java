/*     */ package thaumcraft.common.lib.world;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.world.dim.MazeHandler;
/*     */ import thaumcraft.common.tiles.TileBanner;
/*     */ import thaumcraft.common.tiles.TileEldritchAltar;
/*     */ 
/*     */ public class WorldGenEldritchRing extends WorldGenerator
/*     */ {
/*     */   public int chunkX;
/*     */   public int chunkZ;
/*     */   public int width;
/*     */   
/*     */   protected Block[] GetValidSpawnBlocks()
/*     */   {
/*  24 */     return new Block[] { Blocks.field_150348_b, Blocks.field_150354_m, Blocks.field_150403_cj, Blocks.field_150349_c, Blocks.field_150351_n, Blocks.field_150346_d };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean LocationIsValidSpawn(World world, int i, int j, int k)
/*     */   {
/*  36 */     int distanceToAir = 0;
/*  37 */     Block checkID = world.func_147439_a(i, j, k);
/*     */     
/*  39 */     while (checkID != Blocks.field_150350_a) {
/*  40 */       distanceToAir++;
/*  41 */       checkID = world.func_147439_a(i, j + distanceToAir, k);
/*     */     }
/*     */     
/*  44 */     if (distanceToAir > 2) {
/*  45 */       return false;
/*     */     }
/*  47 */     j += distanceToAir - 1;
/*     */     
/*  49 */     Block blockID = world.func_147439_a(i, j, k);
/*  50 */     Block blockIDAbove = world.func_147439_a(i, j + 1, k);
/*  51 */     Block blockIDBelow = world.func_147439_a(i, j - 1, k);
/*  52 */     for (Block x : GetValidSpawnBlocks()) {
/*  53 */       if (blockIDAbove != Blocks.field_150350_a) {
/*  54 */         return false;
/*     */       }
/*  56 */       if (blockID == x)
/*  57 */         return true;
/*  58 */       if (((blockID == Blocks.field_150431_aC) || (blockID == Blocks.field_150329_H)) && (blockIDBelow == x)) {
/*  59 */         return true;
/*     */       }
/*     */     }
/*  62 */     return false;
/*     */   }
/*     */   
/*  65 */   public int height = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_76484_a(World world, Random rand, int i, int j, int k)
/*     */   {
/*  71 */     if ((!LocationIsValidSpawn(world, i - 3, j, k - 3)) || (!LocationIsValidSpawn(world, i, j, k)) || (!LocationIsValidSpawn(world, i + 3, j, k)) || (!LocationIsValidSpawn(world, i + 3, j, k + 3)) || (!LocationIsValidSpawn(world, i, j, k + 3)) || (MazeHandler.mazesInRange(this.chunkX, this.chunkZ, this.width, this.height)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  79 */       return false;
/*     */     }
/*     */     
/*  82 */     Block replaceBlock = world.func_72807_a(i, k).field_76752_A;
/*     */     
/*  84 */     for (int x = i - 3; x <= i + 3; x++) {
/*  85 */       for (int z = k - 3; z <= k + 3; z++) {
/*  86 */         if (((x != i - 3) && (x != i + 3)) || ((z != k - 3) && (z != k + 3)))
/*     */         {
/*  88 */           for (int q = -4; q < 5; q++) {
/*  89 */             Block bb = world.func_147439_a(x, j + q, z);
/*  90 */             if ((q <= 0) || (bb.isReplaceable(world, x, j + q, z)) || (!bb.func_149688_o().func_76230_c()) || (bb.isFoliage(world, x, j + q, z)))
/*     */             {
/*  92 */               if (rand.nextInt(4) == 0) {
/*  93 */                 world.func_147449_b(x, j + q, z, Blocks.field_150343_Z);
/*     */               } else
/*  95 */                 world.func_147465_d(x, j + q, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
/*     */             }
/*  97 */             if (q > 0) {
/*  98 */               world.func_147468_f(x, j + q, z);
/*     */             }
/*     */           }
/*     */           
/* 102 */           if ((x == i) && (z == k))
/*     */           {
/* 104 */             world.func_147465_d(x, j + 1, z, ConfigBlocks.blockEldritch, 0, 3);
/* 105 */             world.func_147465_d(x, j, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
/* 106 */             int r = rand.nextInt(10);
/* 107 */             TileEntity te = world.func_147438_o(x, j + 1, z);
/* 108 */             if ((te != null) && ((te instanceof TileEldritchAltar))) {
/* 109 */               switch (r) {
/*     */               case 1: case 2: case 3: case 4: 
/* 111 */                 ((TileEldritchAltar)te).setSpawner(true);
/* 112 */                 ((TileEldritchAltar)te).setSpawnType((byte)0);
/* 113 */                 for (int a = 2; a < 6; a++)
/*     */                 {
/* 115 */                   ForgeDirection dir = ForgeDirection.getOrientation(a);
/*     */                   
/* 117 */                   world.func_147465_d(x - dir.offsetX * 3, j + 1, z + dir.offsetZ * 3, ConfigBlocks.blockWoodenDevice, 8, 3);
/* 118 */                   te = world.func_147438_o(x - dir.offsetX * 3, j + 1, z + dir.offsetZ * 3);
/* 119 */                   if ((te != null) && ((te instanceof TileBanner))) {
/* 120 */                     int face = 0;
/*     */                     
/* 122 */                     switch (a) {
/* 123 */                     case 2:  face = 8; break;
/* 124 */                     case 3:  face = 0; break;
/* 125 */                     case 4:  face = 12; break;
/* 126 */                     case 5:  face = 4; break;
/*     */                     }
/* 128 */                     ((TileBanner)te).setFacing((byte)face);
/*     */                   }
/*     */                 }
/* 131 */                 break;
/*     */               case 6: case 7: 
/* 133 */                 ((TileEldritchAltar)te).setSpawner(true);
/* 134 */                 ((TileEldritchAltar)te).setSpawnType((byte)1);
/*     */               }
/*     */               
/*     */             }
/*     */             
/*     */ 
/*     */ 
/*     */ 
/* 142 */             world.func_147465_d(x, j + 3, z, ConfigBlocks.blockEldritch, 1, 3);
/* 143 */             world.func_147465_d(x, j + 4, z, ConfigBlocks.blockEldritch, 2, 3);
/* 144 */             world.func_147465_d(x, j + 5, z, ConfigBlocks.blockEldritch, 2, 3);
/* 145 */             world.func_147465_d(x, j + 6, z, ConfigBlocks.blockEldritch, 2, 3);
/* 146 */             world.func_147465_d(x, j + 7, z, ConfigBlocks.blockEldritch, 2, 3);
/*     */ 
/*     */           }
/* 149 */           else if (((x != i - 3) && (x != i + 3)) || (((Math.abs((z - k) % 2) == 1) || (((z == k - 3) || (z == k + 3)) && (Math.abs((x - i) % 2) == 1))) && (Math.abs(x - i) != Math.abs(z - k))))
/*     */           {
/*     */ 
/* 152 */             world.func_147465_d(x, j, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
/* 153 */             world.func_147465_d(x, j + 1, z, ConfigBlocks.blockEldritch, 3, 3);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 159 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/WorldGenEldritchRing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */