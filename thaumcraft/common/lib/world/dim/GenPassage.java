/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.MobSpawnerBaseLogic;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class GenPassage extends GenCommon
/*     */ {
/*     */   static void generateDefaultPassage(World world, Random random, int cx, int cz, int y, Cell cell)
/*     */   {
/*  17 */     int x = cx * 16;
/*  18 */     int z = cz * 16;
/*     */     
/*  20 */     generateConnections(world, random, cx, cz, y, cell, 4, false);
/*     */     
/*  22 */     int mod = 0;
/*  23 */     if ((cell.north) && (cell.south) && (cell.west) && (cell.east) && (random.nextBoolean())) {
/*  24 */       mod = 1;
/*     */     }
/*     */     
/*  27 */     for (int w = 1; w < 8; w++) { for (int h = 1; h < 8; h++) {
/*  28 */         if ((w == 4) && (h == 4) && (mod == 1)) {
/*  29 */           placeBlock(world, x + 4 + w, y + 2, z + 4 + h, 7, cell);
/*  30 */           placeBlock(world, x + 4 + w, y + 8, z + 4 + h, 7, cell);
/*     */         } else {
/*  32 */           placeBlock(world, x + 4 + w, y + 2, z + 4 + h, (cell.feature == 11) && (random.nextInt(3) == 0) ? 20 : 2, cell);
/*  33 */           placeBlock(world, x + 4 + w, y + 8, z + 4 + h, (cell.feature == 11) && (random.nextInt(3) == 0) ? 20 : 2, cell);
/*     */         }
/*  35 */         placeBlock(world, x + 4 + w, y, z + 4 + h, 1, cell);
/*  36 */         placeBlock(world, x + 4 + w, y + 10, z + 4 + h, 1, cell);
/*     */         
/*  38 */         placeBlock(world, x + 4 + w, y + 1, z + 4 + h, 8, cell);
/*  39 */         placeBlock(world, x + 4 + w, y + 9, z + 4 + h, 8, cell);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  44 */     if (cell.north) {
/*  45 */       for (int w = 2 + mod; w < 9 - mod; w++) for (int h = 2 + mod; h < 9 - mod; h++) {
/*  46 */           placeBlock(world, x + 3 + w, y + 10 - h, z + 5, PAT_CONNECT[h][w], ForgeDirection.NORTH, cell);
/*     */         }
/*  48 */       if (mod == 0) {
/*  49 */         if (cell.west) {
/*  50 */           placeBlock(world, x + 6, y + 3, z + 6, 3, ForgeDirection.EAST, cell);
/*  51 */           placeBlock(world, x + 6, y + 7, z + 6, 5, ForgeDirection.EAST, cell);
/*     */         }
/*  53 */         if (cell.east) {
/*  54 */           placeBlock(world, x + 10, y + 3, z + 6, 3, ForgeDirection.EAST, cell);
/*  55 */           placeBlock(world, x + 10, y + 7, z + 6, 5, ForgeDirection.EAST, cell);
/*     */         }
/*     */       }
/*     */     } else {
/*  59 */       for (int w = 1; w < 8; w++) for (int h = 1; h < 8; h++) {
/*  60 */           placeBlock(world, x + 4 + w, y + 9 - h, z + 5, (cell.feature == 11) && (random.nextInt(3) == 0) ? 20 : 2, cell);
/*  61 */           placeBlock(world, x + 4 + w, y + 9 - h, z + 4, 8, cell);
/*  62 */           placeBlock(world, x + 4 + w, y + 9 - h, z + 3, 1, cell);
/*  63 */           if (h == 7) {
/*  64 */             placeBlock(world, x + 4 + w, y + 1, z + 4, 1, cell);
/*  65 */             placeBlock(world, x + 4 + w, y + 9, z + 4, 1, cell);
/*     */           }
/*  67 */           if (w == 7) {
/*  68 */             placeBlock(world, x + 4, y + 9 - h, z + 4, 1, cell);
/*  69 */             placeBlock(world, x + 12, y + 9 - h, z + 4, 1, cell);
/*     */           }
/*     */         }
/*  72 */       for (int w = 2; w < 7; w++) {
/*  73 */         placeBlock(world, x + 4 + w, y + 3, z + 6, 3, ForgeDirection.EAST, cell);
/*  74 */         placeBlock(world, x + 4 + w, y + 7, z + 6, 5, ForgeDirection.EAST, cell);
/*     */       }
/*     */     }
/*     */     
/*  78 */     if (cell.south) {
/*  79 */       for (int w = 2 + mod; w < 9 - mod; w++) for (int h = 2 + mod; h < 9 - mod; h++) {
/*  80 */           placeBlock(world, x + 3 + w, y + 10 - h, z + 11, PAT_CONNECT[h][w], ForgeDirection.SOUTH, cell);
/*     */         }
/*  82 */       if (mod == 0) {
/*  83 */         if (cell.west) {
/*  84 */           placeBlock(world, x + 6, y + 3, z + 10, 4, ForgeDirection.EAST, cell);
/*  85 */           placeBlock(world, x + 6, y + 7, z + 10, 6, ForgeDirection.EAST, cell);
/*     */         }
/*  87 */         if (cell.east) {
/*  88 */           placeBlock(world, x + 10, y + 3, z + 10, 4, ForgeDirection.EAST, cell);
/*  89 */           placeBlock(world, x + 10, y + 7, z + 10, 6, ForgeDirection.EAST, cell);
/*     */         }
/*     */       }
/*     */     } else {
/*  93 */       for (int w = 1; w < 8; w++) for (int h = 1; h < 8; h++) {
/*  94 */           placeBlock(world, x + 4 + w, y + 9 - h, z + 11, (cell.feature == 11) && (random.nextInt(3) == 0) ? 20 : 2, cell);
/*  95 */           placeBlock(world, x + 4 + w, y + 9 - h, z + 12, 8, cell);
/*  96 */           placeBlock(world, x + 4 + w, y + 9 - h, z + 13, 1, cell);
/*  97 */           if (h == 7) {
/*  98 */             placeBlock(world, x + 4 + w, y + 1, z + 12, 1, cell);
/*  99 */             placeBlock(world, x + 4 + w, y + 9, z + 12, 1, cell);
/*     */           }
/* 101 */           if (w == 7) {
/* 102 */             placeBlock(world, x + 4, y + 9 - h, z + 12, 1, cell);
/* 103 */             placeBlock(world, x + 12, y + 9 - h, z + 12, 1, cell);
/*     */           }
/*     */         }
/* 106 */       for (int w = 2; w < 7; w++) {
/* 107 */         placeBlock(world, x + 4 + w, y + 3, z + 10, 4, ForgeDirection.EAST, cell);
/* 108 */         placeBlock(world, x + 4 + w, y + 7, z + 10, 6, ForgeDirection.EAST, cell);
/*     */       }
/*     */     }
/*     */     
/* 112 */     if (cell.east) {
/* 113 */       for (int w = 2 + mod; w < 9 - mod; w++) for (int h = 2 + mod; h < 9 - mod; h++) {
/* 114 */           placeBlock(world, x + 11, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], ForgeDirection.EAST, cell);
/*     */         }
/* 116 */       if (mod == 0) {
/* 117 */         if (cell.north) {
/* 118 */           placeBlock(world, x + 10, y + 3, z + 6, 4, ForgeDirection.NORTH, cell);
/* 119 */           placeBlock(world, x + 10, y + 7, z + 6, 6, ForgeDirection.NORTH, cell);
/*     */         }
/* 121 */         if (cell.south) {
/* 122 */           placeBlock(world, x + 10, y + 3, z + 10, 4, ForgeDirection.NORTH, cell);
/* 123 */           placeBlock(world, x + 10, y + 7, z + 10, 6, ForgeDirection.NORTH, cell);
/*     */         }
/*     */       }
/*     */     } else {
/* 127 */       for (int w = 1; w < 8; w++) for (int h = 1; h < 8; h++) {
/* 128 */           placeBlock(world, x + 11, y + 9 - h, z + 4 + w, (cell.feature == 11) && (random.nextInt(3) == 0) ? 20 : 2, cell);
/* 129 */           placeBlock(world, x + 12, y + 9 - h, z + 4 + w, 8, cell);
/* 130 */           placeBlock(world, x + 13, y + 9 - h, z + 4 + w, 1, cell);
/* 131 */           if (h == 7) {
/* 132 */             placeBlock(world, x + 12, y + 1, z + 4 + w, 1, cell);
/* 133 */             placeBlock(world, x + 12, y + 9, z + 4 + w, 1, cell);
/*     */           }
/* 135 */           if (w == 7) {
/* 136 */             placeBlock(world, x + 12, y + 9 - h, z + 4, 1, cell);
/* 137 */             placeBlock(world, x + 12, y + 9 - h, z + 12, 1, cell);
/*     */           }
/*     */         }
/* 140 */       for (int w = 2; w < 7; w++) {
/* 141 */         placeBlock(world, x + 10, y + 3, z + 4 + w, 4, ForgeDirection.NORTH, cell);
/* 142 */         placeBlock(world, x + 10, y + 7, z + 4 + w, 6, ForgeDirection.NORTH, cell);
/*     */       }
/*     */     }
/*     */     
/* 146 */     if (cell.west) {
/* 147 */       for (int w = 2 + mod; w < 9 - mod; w++) for (int h = 2 + mod; h < 9 - mod; h++) {
/* 148 */           placeBlock(world, x + 5, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], ForgeDirection.WEST, cell);
/*     */         }
/* 150 */       if (mod == 0) {
/* 151 */         if (cell.north) {
/* 152 */           placeBlock(world, x + 6, y + 3, z + 6, 3, ForgeDirection.NORTH, cell);
/* 153 */           placeBlock(world, x + 6, y + 7, z + 6, 5, ForgeDirection.NORTH, cell);
/*     */         }
/* 155 */         if (cell.south) {
/* 156 */           placeBlock(world, x + 6, y + 3, z + 10, 3, ForgeDirection.NORTH, cell);
/* 157 */           placeBlock(world, x + 6, y + 7, z + 10, 5, ForgeDirection.NORTH, cell);
/*     */         }
/*     */       }
/*     */     } else {
/* 161 */       for (int w = 1; w < 8; w++) for (int h = 1; h < 8; h++) {
/* 162 */           placeBlock(world, x + 5, y + 9 - h, z + 4 + w, (cell.feature == 11) && (random.nextInt(3) == 0) ? 20 : 2, cell);
/* 163 */           placeBlock(world, x + 4, y + 9 - h, z + 4 + w, 8, cell);
/* 164 */           placeBlock(world, x + 3, y + 9 - h, z + 4 + w, 1, cell);
/* 165 */           if (h == 7) {
/* 166 */             placeBlock(world, x + 4, y + 1, z + 4 + w, 1, cell);
/* 167 */             placeBlock(world, x + 4, y + 9, z + 4 + w, 1, cell);
/*     */           }
/* 169 */           if (w == 7) {
/* 170 */             placeBlock(world, x + 4, y + 9 - h, z + 4, 1, cell);
/* 171 */             placeBlock(world, x + 4, y + 9 - h, z + 12, 1, cell);
/*     */           }
/*     */         }
/* 174 */       for (int w = 2; w < 7; w++) {
/* 175 */         placeBlock(world, x + 6, y + 3, z + 4 + w, 3, ForgeDirection.NORTH, cell);
/* 176 */         placeBlock(world, x + 6, y + 7, z + 4 + w, 5, ForgeDirection.NORTH, cell);
/*     */       }
/*     */     }
/*     */     
/* 180 */     if (mod == 1) {
/* 181 */       placeBlock(world, x + 5, y + 3, z + 5, 3, ForgeDirection.EAST, cell);
/* 182 */       placeBlock(world, x + 5, y + 7, z + 5, 5, ForgeDirection.EAST, cell);
/* 183 */       placeBlock(world, x + 5, y + 3, z + 6, 3, ForgeDirection.NORTH, cell);
/* 184 */       placeBlock(world, x + 5, y + 7, z + 6, 5, ForgeDirection.NORTH, cell);
/*     */       
/* 186 */       placeBlock(world, x + 11, y + 3, z + 5, 3, ForgeDirection.EAST, cell);
/* 187 */       placeBlock(world, x + 11, y + 7, z + 5, 5, ForgeDirection.EAST, cell);
/* 188 */       placeBlock(world, x + 11, y + 3, z + 6, 4, ForgeDirection.NORTH, cell);
/* 189 */       placeBlock(world, x + 11, y + 7, z + 6, 6, ForgeDirection.NORTH, cell);
/*     */       
/* 191 */       placeBlock(world, x + 5, y + 3, z + 11, 3, ForgeDirection.NORTH, cell);
/* 192 */       placeBlock(world, x + 5, y + 7, z + 11, 5, ForgeDirection.NORTH, cell);
/* 193 */       placeBlock(world, x + 6, y + 3, z + 11, 4, ForgeDirection.EAST, cell);
/* 194 */       placeBlock(world, x + 6, y + 7, z + 11, 6, ForgeDirection.EAST, cell);
/*     */       
/* 196 */       placeBlock(world, x + 11, y + 3, z + 11, 4, ForgeDirection.NORTH, cell);
/* 197 */       placeBlock(world, x + 11, y + 7, z + 11, 6, ForgeDirection.NORTH, cell);
/* 198 */       placeBlock(world, x + 10, y + 3, z + 11, 4, ForgeDirection.EAST, cell);
/* 199 */       placeBlock(world, x + 10, y + 7, z + 11, 6, ForgeDirection.EAST, cell);
/*     */     }
/*     */     
/*     */ 
/* 203 */     if (cell.feature == 12) {
/* 204 */       for (int w = -4; w <= 4; w++) for (int h = -4; h < 5; h++) { for (int j = -4; j <= 4; j++) {
/* 205 */             if (((world.func_147437_c(x + 8 + w, y + 4 + h, z + 8 + j)) || (world.func_147439_a(x + 8 + w, y + 4 + h, z + 8 + j) == ConfigBlocks.blockCosmeticSolid) || (world.func_147439_a(x + 8 + w, y + 4 + h, z + 8 + j) == ConfigBlocks.blockStairsEldritch)) && (random.nextBoolean()))
/*     */             {
/*     */ 
/*     */ 
/* 209 */               placeBlock(world, x + 8 + w, y + 4 + h, z + 8 + j, 21, cell);
/*     */             }
/*     */           }
/*     */         }
/*     */     }
/* 214 */     if (cell.feature == 13) {
/* 215 */       for (int w = -4; w <= 4; w++) for (int h = -3; h <= 3; h++) { for (int j = -4; j <= 4; j++) {
/* 216 */             if ((world.func_147437_c(x + 8 + w, y + 4 + h, z + 8 + j)) && (thaumcraft.common.lib.utils.BlockUtils.isAdjacentToSolidBlock(world, x + 8 + w, y + 4 + h, z + 8 + j))) {
/* 217 */               if (random.nextInt(3) != 0) world.func_147465_d(x + 8 + w, y + 4 + h, z + 8 + j, ConfigBlocks.blockTaintFibres, random.nextInt(4) == 0 ? 1 : 0, 3);
/* 218 */               Utils.setBiomeAt(world, x + 8 + w, z + 8 + j, ThaumcraftWorldGenerator.biomeTaint);
/*     */             }
/*     */           }
/*     */         }
/*     */     }
/* 223 */     if (cell.feature == 14) {
/* 224 */       for (int w = -3; w <= 3; w++) for (int h = -3; h <= 3; h++) for (int j = -3; j <= 3; j++) {
/* 225 */             if ((world.func_147437_c(x + 8 + w, y + 4 + h, z + 8 + j)) && (random.nextFloat() < 0.35F)) {
/* 226 */               world.func_147449_b(x + 8 + w, y + 4 + h, z + 8 + j, Blocks.field_150321_G);
/*     */             }
/* 228 */             world.func_147449_b(x + 8, y + 4, z + 8, Blocks.field_150474_ac);
/* 229 */             TileEntityMobSpawner var12 = (TileEntityMobSpawner)world.func_147438_o(x + 8, y + 4, z + 8);
/* 230 */             if (var12 != null) var12.func_145881_a().func_98272_a("Thaumcraft.MindSpider");
/*     */           }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/GenPassage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */