/*     */ package thaumcraft.common.lib.world;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.MobSpawnerBaseLogic;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraftforge.common.ChestGenHooks;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenHilltopStones
/*     */   extends WorldGenerator
/*     */ {
/*     */   protected Block[] GetValidSpawnBlocks()
/*     */   {
/*  23 */     return new Block[] { Blocks.field_150348_b, Blocks.field_150349_c, Blocks.field_150346_d };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean LocationIsValidSpawn(World world, int i, int j, int k)
/*     */   {
/*  31 */     if (j < 85) { return false;
/*     */     }
/*  33 */     int distanceToAir = 0;
/*  34 */     Block checkID = world.func_147439_a(i, j, k);
/*     */     
/*  36 */     while (checkID != Blocks.field_150350_a) {
/*  37 */       distanceToAir++;
/*  38 */       checkID = world.func_147439_a(i, j + distanceToAir, k);
/*     */     }
/*     */     
/*  41 */     if (distanceToAir > 2) {
/*  42 */       return false;
/*     */     }
/*  44 */     j += distanceToAir - 1;
/*     */     
/*  46 */     Block blockID = world.func_147439_a(i, j, k);
/*  47 */     Block blockIDAbove = world.func_147439_a(i, j + 1, k);
/*  48 */     Block blockIDBelow = world.func_147439_a(i, j - 1, k);
/*  49 */     for (Block x : GetValidSpawnBlocks()) {
/*  50 */       if (blockIDAbove != Blocks.field_150350_a) {
/*  51 */         return false;
/*     */       }
/*  53 */       if (blockID == x)
/*  54 */         return true;
/*  55 */       if (((blockID == Blocks.field_150431_aC) || (blockID == Blocks.field_150329_H)) && (blockIDBelow == x)) {
/*  56 */         return true;
/*     */       }
/*     */     }
/*  59 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_76484_a(World world, Random rand, int i, int j, int k)
/*     */   {
/*  65 */     if ((!LocationIsValidSpawn(world, i - 2, j, k - 2)) || (!LocationIsValidSpawn(world, i, j, k)) || (!LocationIsValidSpawn(world, i + 2, j, k)) || (!LocationIsValidSpawn(world, i + 2, j, k + 2)) || (!LocationIsValidSpawn(world, i, j, k + 2)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  72 */       return false;
/*     */     }
/*     */     
/*  75 */     Block replaceBlock = world.func_72807_a(i, k).field_76752_A;
/*  76 */     boolean genVines = !world.func_72807_a(i, k).func_76746_c();
/*  77 */     for (int x = i - 3; x <= i + 3; x++) {
/*  78 */       for (int z = k - 3; z <= k + 3; z++) {
/*  79 */         if (((x != i - 3) && (x != i + 3)) || ((z != k - 3) && (z != k + 3)))
/*     */         {
/*  81 */           if (rand.nextBoolean()) {
/*  82 */             world.func_147465_d(x, j, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
/*     */           } else {
/*  84 */             world.func_147465_d(x, j, z, Blocks.field_150343_Z, 0, 3);
/*     */           }
/*  86 */           boolean stop = false;
/*  87 */           for (int y = 1; y < 5; y++) {
/*  88 */             if (j - y >= 0) {
/*  89 */               Block blockID = world.func_147439_a(x, j - y, z);
/*  90 */               if (((replaceBlock != null) && (blockID == Blocks.field_150431_aC)) || (blockID == Blocks.field_150328_O) || (blockID == Blocks.field_150327_N) || (blockID == Blocks.field_150329_H) || (blockID == Blocks.field_150350_a))
/*     */               {
/*     */ 
/*  93 */                 world.func_147465_d(x, j - y, z, replaceBlock, 0, 3);
/*     */               }
/*     */               
/*  96 */               if ((x == i) && (z == k) && (y == 1)) {
/*  97 */                 world.func_147465_d(x, j + y, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
/*     */                 
/*  99 */                 ChestGenHooks info1 = ChestGenHooks.getInfo("dungeonChest");
/* 100 */                 ChestGenHooks info2 = ChestGenHooks.getInfo("dungeonChest");
/* 101 */                 world.func_147465_d(x, j + y + 1, z, Blocks.field_150486_ae, 0, 3);
/* 102 */                 TileEntityChest chest = (TileEntityChest)world.func_147438_o(x, j + y + 1, z);
/* 103 */                 if (chest != null) {
/* 104 */                   WeightedRandomChestContent.func_76293_a(rand, info1.getItems(rand), chest, info1.getCount(rand));
/* 105 */                   WeightedRandomChestContent.func_76293_a(rand, info2.getItems(rand), chest, info2.getCount(rand));
/*     */                 }
/*     */                 
/*     */ 
/* 109 */                 world.func_147465_d(x, j + y - 1, z, Blocks.field_150474_ac, 0, 3);
/* 110 */                 TileEntityMobSpawner var12 = (TileEntityMobSpawner)world.func_147438_o(x, j + y - 1, z);
/* 111 */                 if (var12 != null) { var12.func_145881_a().func_98272_a("Thaumcraft.Wisp");
/*     */                 }
/*     */               }
/* 114 */               if ((!stop) && (((x != i - 3) && (x != i + 3)) || ((Math.abs((z - k) % 2) == 1) || (((z == k - 3) || (z == k + 3)) && (Math.abs((x - i) % 2) == 1))))) {
/* 115 */                 world.func_147465_d(x, j + y, z, ConfigBlocks.blockCosmeticSolid, 0, 3);
/* 116 */                 if ((y >= 2) && (rand.nextBoolean())) {
/* 117 */                   stop = true;
/*     */                   
/* 119 */                   if (genVines) {
/* 120 */                     if ((rand.nextInt(3) == 0) && (world.func_147437_c(x - 1, j + y, z)))
/*     */                     {
/* 122 */                       growVines(world, x - 1, j + y, z, 8);
/*     */                     }
/*     */                     
/* 125 */                     if ((rand.nextInt(3) == 0) && (world.func_147437_c(x + 1, j + y, z)))
/*     */                     {
/* 127 */                       growVines(world, x + 1, j + y, z, 2);
/*     */                     }
/*     */                     
/* 130 */                     if ((rand.nextInt(3) == 0) && (world.func_147437_c(x, j + y, z - 1)))
/*     */                     {
/* 132 */                       growVines(world, x, j + y, z - 1, 1);
/*     */                     }
/*     */                     
/* 135 */                     if ((rand.nextInt(3) == 0) && (world.func_147437_c(x, j + y, z + 1)))
/*     */                     {
/* 137 */                       growVines(world, x, j + y, z + 1, 4);
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   private void growVines(World par1World, int par2, int par3, int par4, int par5)
/*     */   {
/* 153 */     func_150516_a(par1World, par2, par3, par4, Blocks.field_150395_bd, par5);
/* 154 */     int var6 = 4;
/*     */     
/*     */     for (;;)
/*     */     {
/* 158 */       par3--;
/*     */       
/* 160 */       if ((!par1World.func_147437_c(par2, par3, par4)) || (var6 <= 0))
/*     */       {
/* 162 */         return;
/*     */       }
/*     */       
/* 165 */       func_150516_a(par1World, par2, par3, par4, Blocks.field_150395_bd, par5);
/* 166 */       var6--;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/WorldGenHilltopStones.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */