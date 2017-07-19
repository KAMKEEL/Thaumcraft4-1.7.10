/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Gen2x2
/*     */   extends GenCommon
/*     */ {
/*     */   static void generateUpperLeft(World world, Random random, int cx, int cz, int y, Cell cell)
/*     */   {
/*  14 */     int x = cx * 16;
/*  15 */     int z = cz * 16;
/*     */     
/*  17 */     for (int a = 1; a <= 15; a++) for (int b = 1; b <= 15; b++) for (int c = 0; c < 13; c++) {
/*  18 */           if ((a == 1) || (b == 1)) {
/*  19 */             placeBlock(world, x + a, y + c, z + b, 1, cell);
/*     */           }
/*     */         }
/*  22 */     for (int a = 2; a <= 15; a++) for (int b = 2; b <= 15; b++) for (int c = 1; c < 12; c++) {
/*  23 */           if (((a == 2) || (b == 2)) && 
/*  24 */             ((a != 2) || (b <= 4) || (b >= 12) || (!cell.west) || (c >= 10)) && (
/*  25 */             (b != 2) || (a <= 4) || (a >= 12) || (!cell.north) || (c >= 10))) {
/*  26 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/*  29 */     for (int a = 3; a <= 15; a++) for (int b = 3; b <= 15; b++) { for (int c = 2; c < 11; c++) {
/*  30 */           if ((a == 3) || (b == 3)) {
/*  31 */             placeBlock(world, x + a, y + c, z + b, 18, cell);
/*     */           }
/*     */         }
/*     */       }
/*  35 */     for (int a = 2; a <= 15; a++) { for (int b = 2; b <= 15; b++) {
/*  36 */         placeBlock(world, x + a, y - 1, z + b, 1, cell);
/*  37 */         placeBlock(world, x + a, y, z + b, 8, cell);
/*  38 */         placeBlock(world, x + a, y + 1, z + b, 19, cell);
/*  39 */         placeBlock(world, x + a, y + 13, z + b, 1, cell);
/*  40 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/*  41 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */       }
/*     */     }
/*  44 */     for (int g = 4; g <= 15; g++) {
/*  45 */       placeBlock(world, x + g, y + 2, z + 4, 10, ForgeDirection.NORTH, cell);
/*  46 */       placeBlock(world, x + g, y + 10, z + 4, 11, ForgeDirection.NORTH, cell);
/*     */     }
/*  48 */     for (int g = 4; g <= 15; g++) {
/*  49 */       placeBlock(world, x + 4, y + 2, z + g, 10, ForgeDirection.WEST, cell);
/*  50 */       placeBlock(world, x + 4, y + 10, z + g, 11, ForgeDirection.WEST, cell);
/*     */     }
/*     */     
/*  53 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*     */   }
/*     */   
/*     */   static void generateUpperRight(World world, Random random, int cx, int cz, int y, Cell cell) {
/*  57 */     int x = cx * 16;
/*  58 */     int z = cz * 16;
/*     */     
/*  60 */     for (int a = 0; a <= 15; a++) for (int b = 1; b <= 15; b++) for (int c = 0; c < 13; c++) {
/*  61 */           if ((a == 15) || (b == 1)) {
/*  62 */             placeBlock(world, x + a, y + c, z + b, 1, cell);
/*     */           }
/*     */         }
/*  65 */     for (int a = 0; a <= 14; a++) for (int b = 2; b <= 15; b++) for (int c = 1; c < 12; c++) {
/*  66 */           if (((a == 14) || (b == 2)) && 
/*  67 */             ((a != 14) || (b <= 4) || (b >= 12) || (!cell.east) || (c >= 10)) && (
/*  68 */             (b != 2) || (a <= 4) || (a >= 12) || (!cell.north) || (c >= 10))) {
/*  69 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/*  72 */     for (int a = 0; a <= 13; a++) for (int b = 3; b <= 15; b++) { for (int c = 2; c < 11; c++) {
/*  73 */           if ((a == 13) || (b == 3)) {
/*  74 */             placeBlock(world, x + a, y + c, z + b, 18, cell);
/*     */           }
/*     */         }
/*     */       }
/*  78 */     for (int a = 0; a <= 14; a++) { for (int b = 2; b <= 15; b++) {
/*  79 */         placeBlock(world, x + a, y - 1, z + b, 1, cell);
/*  80 */         placeBlock(world, x + a, y, z + b, 8, cell);
/*  81 */         placeBlock(world, x + a, y + 1, z + b, 19, cell);
/*  82 */         placeBlock(world, x + a, y + 13, z + b, 1, cell);
/*  83 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/*  84 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */       }
/*     */     }
/*  87 */     for (int g = 0; g <= 11; g++) {
/*  88 */       placeBlock(world, x + g, y + 2, z + 4, 10, ForgeDirection.NORTH, cell);
/*  89 */       placeBlock(world, x + g, y + 10, z + 4, 11, ForgeDirection.NORTH, cell);
/*     */     }
/*  91 */     for (int g = 4; g <= 15; g++) {
/*  92 */       placeBlock(world, x + 12, y + 2, z + g, 10, ForgeDirection.EAST, cell);
/*  93 */       placeBlock(world, x + 12, y + 10, z + g, 11, ForgeDirection.EAST, cell);
/*     */     }
/*     */     
/*  96 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*     */   }
/*     */   
/*     */   static void generateLowerLeft(World world, Random random, int cx, int cz, int y, Cell cell) {
/* 100 */     int x = cx * 16;
/* 101 */     int z = cz * 16;
/*     */     
/* 103 */     for (int a = 1; a <= 15; a++) for (int b = 0; b <= 15; b++) for (int c = 0; c < 13; c++) {
/* 104 */           if ((a == 1) || (b == 15)) {
/* 105 */             placeBlock(world, x + a, y + c, z + b, 1, cell);
/*     */           }
/*     */         }
/* 108 */     for (int a = 2; a <= 15; a++) for (int b = 0; b <= 14; b++) for (int c = 1; c < 12; c++) {
/* 109 */           if (((a == 2) || (b == 14)) && 
/* 110 */             ((a != 2) || (b <= 4) || (b >= 12) || (!cell.west) || (c >= 10)) && (
/* 111 */             (b != 14) || (a <= 4) || (a >= 12) || (!cell.south) || (c >= 10))) {
/* 112 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/* 115 */     for (int a = 3; a <= 15; a++) for (int b = 0; b <= 13; b++) { for (int c = 2; c < 11; c++) {
/* 116 */           if ((a == 3) || (b == 13)) {
/* 117 */             placeBlock(world, x + a, y + c, z + b, 18, cell);
/*     */           }
/*     */         }
/*     */       }
/* 121 */     for (int a = 2; a <= 15; a++) { for (int b = 0; b <= 14; b++) {
/* 122 */         placeBlock(world, x + a, y - 1, z + b, 1, cell);
/* 123 */         placeBlock(world, x + a, y, z + b, 8, cell);
/* 124 */         placeBlock(world, x + a, y + 1, z + b, 19, cell);
/* 125 */         placeBlock(world, x + a, y + 13, z + b, 1, cell);
/* 126 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/* 127 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */       }
/*     */     }
/* 130 */     for (int g = 4; g <= 15; g++) {
/* 131 */       placeBlock(world, x + g, y + 2, z + 12, 10, ForgeDirection.SOUTH, cell);
/* 132 */       placeBlock(world, x + g, y + 10, z + 12, 11, ForgeDirection.SOUTH, cell);
/*     */     }
/* 134 */     for (int g = 0; g <= 11; g++) {
/* 135 */       placeBlock(world, x + 4, y + 2, z + g, 10, ForgeDirection.WEST, cell);
/* 136 */       placeBlock(world, x + 4, y + 10, z + g, 11, ForgeDirection.WEST, cell);
/*     */     }
/*     */     
/* 139 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*     */   }
/*     */   
/*     */   static void generateLowerRight(World world, Random random, int cx, int cz, int y, Cell cell) {
/* 143 */     int x = cx * 16;
/* 144 */     int z = cz * 16;
/*     */     
/* 146 */     for (int a = 0; a <= 15; a++) for (int b = 0; b <= 15; b++) for (int c = 0; c < 13; c++) {
/* 147 */           if ((a == 15) || (b == 15)) {
/* 148 */             placeBlock(world, x + a, y + c, z + b, 1, cell);
/*     */           }
/*     */         }
/* 151 */     for (int a = 0; a <= 14; a++) for (int b = 0; b <= 14; b++) for (int c = 1; c < 12; c++) {
/* 152 */           if (((a == 14) || (b == 14)) && 
/* 153 */             ((a != 14) || (b <= 4) || (b >= 12) || (!cell.east) || (c >= 10)) && (
/* 154 */             (b != 14) || (a <= 4) || (a >= 12) || (!cell.south) || (c >= 10))) {
/* 155 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/* 158 */     for (int a = 0; a <= 13; a++) for (int b = 0; b <= 13; b++) { for (int c = 2; c < 11; c++) {
/* 159 */           if ((a == 13) || (b == 13)) {
/* 160 */             placeBlock(world, x + a, y + c, z + b, 18, cell);
/*     */           }
/*     */         }
/*     */       }
/* 164 */     for (int a = 0; a <= 14; a++) { for (int b = 0; b <= 14; b++) {
/* 165 */         placeBlock(world, x + a, y - 1, z + b, 1, cell);
/* 166 */         placeBlock(world, x + a, y, z + b, 8, cell);
/* 167 */         placeBlock(world, x + a, y + 1, z + b, 19, cell);
/* 168 */         placeBlock(world, x + a, y + 13, z + b, 1, cell);
/* 169 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/* 170 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */       }
/*     */     }
/* 173 */     for (int g = 0; g <= 11; g++) {
/* 174 */       placeBlock(world, x + g, y + 2, z + 12, 10, ForgeDirection.SOUTH, cell);
/* 175 */       placeBlock(world, x + g, y + 10, z + 12, 11, ForgeDirection.SOUTH, cell);
/*     */     }
/* 177 */     for (int g = 0; g <= 12; g++) {
/* 178 */       placeBlock(world, x + 12, y + 2, z + g, 10, ForgeDirection.EAST, cell);
/* 179 */       placeBlock(world, x + 12, y + 10, z + g, 11, ForgeDirection.EAST, cell);
/*     */     }
/*     */     
/* 182 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/Gen2x2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */