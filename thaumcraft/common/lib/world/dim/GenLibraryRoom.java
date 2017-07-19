/*    */ package thaumcraft.common.lib.world.dim;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GenLibraryRoom
/*    */   extends GenCommon
/*    */ {
/*    */   static void generateRoom(World world, Random random, int cx, int cz, int y, Cell cell)
/*    */   {
/* 15 */     int x = cx * 16;
/* 16 */     int z = cz * 16;
/*    */     
/* 18 */     for (int a = 1; a <= 15; a++) for (int b = 1; b <= 15; b++) for (int c = 0; c < 13; c++) {
/* 19 */           if ((a == 1) || (a == 15) || (b == 1) || (b == 15)) {
/* 20 */             placeBlock(world, x + a, y + c, z + b, 1, cell);
/*    */           }
/*    */         }
/* 23 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) for (int c = 1; c < 12; c++) {
/* 24 */           if (((a == 2) || (a == 14) || (b == 2) || (b == 14)) && 
/* 25 */             ((a != 2) || (b <= 3) || (b >= 12) || (!cell.west) || (c >= 10)) && 
/* 26 */             ((a != 14) || (b <= 3) || (b >= 12) || (!cell.east) || (c >= 10)) && 
/* 27 */             ((b != 2) || (a <= 3) || (a >= 12) || (!cell.north) || (c >= 10)) && (
/* 28 */             (b != 14) || (a <= 3) || (a >= 12) || (!cell.south) || (c >= 10))) {
/* 29 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*    */           }
/*    */         }
/* 32 */     for (int a = 3; a <= 13; a++) for (int b = 3; b <= 13; b++) { for (int c = 2; c < 11; c++) {
/* 33 */           if ((a == 3) || (a == 13) || (b == 3) || (b == 13)) {
/* 34 */             placeBlock(world, x + a, y + c, z + b, 2, cell);
/*    */           }
/*    */         }
/*    */       }
/* 38 */     for (int a = 2; a <= 14; a++) { for (int b = 2; b <= 14; b++) {
/* 39 */         placeBlock(world, x + a, y - 1, z + b, 1, cell);
/* 40 */         placeBlock(world, x + a, y, z + b, 8, cell);
/* 41 */         placeBlock(world, x + a, y + 1, z + b, 2, cell);
/* 42 */         placeBlock(world, x + a, y + 12, z + b, 1, cell);
/* 43 */         placeBlock(world, x + a, y + 11, z + b, 8, cell);
/* 44 */         placeBlock(world, x + a, y + 10, z + b, 2, cell);
/*    */         
/* 46 */         if ((a > 3) && (a < 13) && (b > 3) && (b < 13)) {
/* 47 */           if (((a <= 5) && (b <= 5)) || ((a <= 5) && (b >= 11)) || ((a >= 11) && (b <= 5)) || ((a >= 11) && (b >= 11))) {
/* 48 */             placeBlock(world, x + a, y + 2, z + b, 2, cell);
/* 49 */             placeBlock(world, x + a, y + 9, z + b, 2, cell);
/*    */           }
/* 51 */           if (((a == 5) && (b == 5)) || ((a == 5) && (b == 11)) || ((a == 11) && (b == 5)) || ((a == 11) && (b == 11))) {
/* 52 */             world.func_147465_d(x + a, y + 3, z + b, ConfigBlocks.blockCosmeticSolid, 15, 3);
/* 53 */             world.func_147465_d(x + a, y + 8, z + b, ConfigBlocks.blockCosmeticSolid, 15, 3);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 58 */     for (int g = 0; g < 5; g++) {
/* 59 */       placeBlock(world, x + 6 + g, y + 2, z + 4, 10, ForgeDirection.NORTH, cell);
/* 60 */       placeBlock(world, x + 6 + g, y + 2, z + 12, 10, ForgeDirection.SOUTH, cell);
/* 61 */       placeBlock(world, x + 12, y + 2, z + 6 + g, 10, ForgeDirection.EAST, cell);
/* 62 */       placeBlock(world, x + 4, y + 2, z + 6 + g, 10, ForgeDirection.WEST, cell);
/*    */       
/* 64 */       placeBlock(world, x + 6 + g, y + 9, z + 4, 11, ForgeDirection.NORTH, cell);
/* 65 */       placeBlock(world, x + 6 + g, y + 9, z + 12, 11, ForgeDirection.SOUTH, cell);
/* 66 */       placeBlock(world, x + 12, y + 9, z + 6 + g, 11, ForgeDirection.EAST, cell);
/* 67 */       placeBlock(world, x + 4, y + 9, z + 6 + g, 11, ForgeDirection.WEST, cell);
/*    */     }
/*    */     
/* 70 */     world.func_147465_d(x + 5, y + 4, z + 5, ConfigBlocks.blockEldritch, 5, 3);
/* 71 */     world.func_147465_d(x + 5, y + 5, z + 5, ConfigBlocks.blockSlabStone, 1, 3);
/* 72 */     world.func_147465_d(x + 5, y + 4, z + 11, ConfigBlocks.blockEldritch, 5, 3);
/* 73 */     world.func_147465_d(x + 5, y + 5, z + 11, ConfigBlocks.blockSlabStone, 1, 3);
/* 74 */     world.func_147465_d(x + 11, y + 4, z + 5, ConfigBlocks.blockEldritch, 5, 3);
/* 75 */     world.func_147465_d(x + 11, y + 5, z + 5, ConfigBlocks.blockSlabStone, 1, 3);
/* 76 */     world.func_147465_d(x + 11, y + 4, z + 11, ConfigBlocks.blockEldritch, 5, 3);
/* 77 */     world.func_147465_d(x + 11, y + 5, z + 11, ConfigBlocks.blockSlabStone, 1, 3);
/*    */     
/* 79 */     world.func_147465_d(x + 5, y + 7, z + 5, ConfigBlocks.blockEldritch, 5, 3);
/* 80 */     world.func_147465_d(x + 5, y + 6, z + 5, ConfigBlocks.blockSlabStone, 9, 3);
/* 81 */     world.func_147465_d(x + 5, y + 7, z + 11, ConfigBlocks.blockEldritch, 5, 3);
/* 82 */     world.func_147465_d(x + 5, y + 6, z + 11, ConfigBlocks.blockSlabStone, 9, 3);
/* 83 */     world.func_147465_d(x + 11, y + 7, z + 5, ConfigBlocks.blockEldritch, 5, 3);
/* 84 */     world.func_147465_d(x + 11, y + 6, z + 5, ConfigBlocks.blockSlabStone, 9, 3);
/* 85 */     world.func_147465_d(x + 11, y + 7, z + 11, ConfigBlocks.blockEldritch, 5, 3);
/* 86 */     world.func_147465_d(x + 11, y + 6, z + 11, ConfigBlocks.blockSlabStone, 9, 3);
/*    */     
/* 88 */     world.func_147465_d(x + 8, y + 2, z + 8, ConfigBlocks.blockCosmeticSolid, 15, 3);
/* 89 */     world.func_147465_d(x + 8, y + 3, z + 8, ConfigBlocks.blockEldritch, 5, 3);
/* 90 */     world.func_147465_d(x + 8, y + 4, z + 8, ConfigBlocks.blockSlabStone, 1, 3);
/*    */     
/* 92 */     world.func_147465_d(x + 8, y + 9, z + 8, ConfigBlocks.blockCosmeticSolid, 15, 3);
/* 93 */     world.func_147465_d(x + 8, y + 8, z + 8, ConfigBlocks.blockEldritch, 5, 3);
/* 94 */     world.func_147465_d(x + 8, y + 7, z + 8, ConfigBlocks.blockSlabStone, 9, 3);
/*    */     
/* 96 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/GenLibraryRoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */