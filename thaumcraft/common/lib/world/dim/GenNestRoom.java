/*    */ package thaumcraft.common.lib.world.dim;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.tiles.TileCrystal;
/*    */ 
/*    */ public class GenNestRoom extends GenCommon
/*    */ {
/*    */   static void generateRoom(World world, Random random, int cx, int cz, int y, Cell cell)
/*    */   {
/* 13 */     int x = cx * 16;
/* 14 */     int z = cz * 16;
/*    */     
/* 16 */     for (int a = 1; a <= 15; a++) for (int b = 1; b <= 15; b++) for (int c = 0; c < 11; c++) {
/* 17 */           if ((a == 1) || (a == 15) || (b == 1) || (b == 15)) {
/* 18 */             placeBlock(world, x + a, y + c, z + b, 1, cell);
/*    */           }
/*    */         }
/* 21 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) for (int c = 1; c < 10; c++) {
/* 22 */           if (((a == 2) || (a == 14) || (b == 2) || (b == 14)) && 
/* 23 */             ((a != 2) || (b <= 3) || (b >= 12) || (!cell.west) || (c >= 10)) && 
/* 24 */             ((a != 14) || (b <= 3) || (b >= 12) || (!cell.east) || (c >= 10)) && 
/* 25 */             ((b != 2) || (a <= 3) || (a >= 12) || (!cell.north) || (c >= 10)) && (
/* 26 */             (b != 14) || (a <= 3) || (a >= 12) || (!cell.south) || (c >= 10))) {
/* 27 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*    */           }
/*    */         }
/* 30 */     for (int a = 3; a <= 13; a++) for (int b = 3; b <= 13; b++) { for (int c = 2; c < 9; c++) {
/* 31 */           if ((a == 3) || (a == 13) || (b == 3) || (b == 13)) {
/* 32 */             placeBlock(world, x + a, y + c, z + b, 21, cell);
/*    */           }
/* 34 */           if (((a == 4) && (!cell.west)) || ((a == 12) && (!cell.east)) || ((b == 4) && (!cell.north)) || ((b == 12) && (!cell.south) && (random.nextBoolean()))) {
/* 35 */             placeBlock(world, x + a, y + c, z + b, 21, cell);
/*    */           }
/*    */         }
/*    */       }
/* 39 */     for (int a = 2; a <= 14; a++) { for (int b = 2; b <= 14; b++) {
/* 40 */         placeBlock(world, x + a, y - 1, z + b, 1, cell);
/* 41 */         placeBlock(world, x + a, y, z + b, 8, cell);
/* 42 */         placeBlock(world, x + a, y + 1, z + b, 21, cell);
/* 43 */         placeBlock(world, x + a, y + 11, z + b, 1, cell);
/* 44 */         placeBlock(world, x + a, y + 10, z + b, 8, cell);
/* 45 */         placeBlock(world, x + a, y + 9, z + b, 21, cell);
/* 46 */         if (random.nextBoolean()) {
/* 47 */           placeBlock(world, x + a, y + 8, z + b, 21, cell);
/*    */         }
/* 49 */         else if ((random.nextBoolean()) && 
/* 50 */           (world.func_147437_c(x + a, y + 8, z + b))) {
/* 51 */           world.func_147465_d(x + a, y + 8, z + b, ConfigBlocks.blockCrystal, 7, 3);
/* 52 */           TileCrystal te = (TileCrystal)world.func_147438_o(x + a, y + 8, z + b);
/* 53 */           te.orientation = ((short)ForgeDirection.DOWN.ordinal());
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 59 */     placeBlock(world, x + 8, y + 2, z + 8, 21, cell);
/* 60 */     placeBlock(world, x + 8, y + 3, z + 8, 21, cell);
/* 61 */     placeBlock(world, x + 8, y + 4, z + 8, 21, cell);
/* 62 */     placeBlock(world, x + 7, y + 2, z + 8, 21, cell);
/* 63 */     placeBlock(world, x + 8, y + 2, z + 7, 21, cell);
/* 64 */     placeBlock(world, x + 9, y + 2, z + 8, 21, cell);
/* 65 */     placeBlock(world, x + 8, y + 2, z + 9, 21, cell);
/* 66 */     if (random.nextBoolean()) placeBlock(world, x + 7, y + 3, z + 8, 21, cell);
/* 67 */     if (random.nextBoolean()) placeBlock(world, x + 8, y + 3, z + 7, 21, cell);
/* 68 */     if (random.nextBoolean()) placeBlock(world, x + 9, y + 3, z + 8, 21, cell);
/* 69 */     if (random.nextBoolean()) { placeBlock(world, x + 8, y + 3, z + 9, 21, cell);
/*    */     }
/* 71 */     if (random.nextBoolean()) { placeBlock(world, x + 8, y + 5, z + 8, 7, cell);
/*    */     }
/* 73 */     placeBlock(world, x + 8, y + 8, z + 8, 21, cell);
/* 74 */     placeBlock(world, x + 8, y + 7, z + 8, 21, cell);
/* 75 */     placeBlock(world, x + 8, y + 6, z + 8, 21, cell);
/* 76 */     placeBlock(world, x + 7, y + 8, z + 8, 21, cell);
/* 77 */     placeBlock(world, x + 8, y + 8, z + 7, 21, cell);
/* 78 */     placeBlock(world, x + 9, y + 8, z + 8, 21, cell);
/* 79 */     placeBlock(world, x + 8, y + 8, z + 9, 21, cell);
/* 80 */     if (random.nextBoolean()) placeBlock(world, x + 7, y + 7, z + 8, 21, cell);
/* 81 */     if (random.nextBoolean()) placeBlock(world, x + 8, y + 7, z + 7, 21, cell);
/* 82 */     if (random.nextBoolean()) placeBlock(world, x + 9, y + 7, z + 8, 21, cell);
/* 83 */     if (random.nextBoolean()) { placeBlock(world, x + 8, y + 7, z + 9, 21, cell);
/*    */     }
/* 85 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*    */     
/* 87 */     for (int a = -5; a <= 5; a++) {
/* 88 */       for (int b = -5; b <= 5; b++) {
/* 89 */         if ((random.nextFloat() < 0.15F) && (world.func_147437_c(x + 8 + a, y + 2, z + 8 + b))) {
/* 90 */           float rr = random.nextFloat();
/* 91 */           int md = rr < 0.4F ? 1 : rr < 0.15F ? 2 : 0;
/* 92 */           world.func_147465_d(x + 8 + a, y + 2, z + 8 + b, random.nextFloat() < 0.2F ? ConfigBlocks.blockLootCrate : ConfigBlocks.blockLootUrn, md, 3);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/GenNestRoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */