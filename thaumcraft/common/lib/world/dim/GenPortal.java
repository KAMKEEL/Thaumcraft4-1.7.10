/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ 
/*     */ public class GenPortal
/*     */   extends GenCommon
/*     */ {
/*     */   static void generatePortal(World world, Random random, int cx, int cz, int y, Cell cell)
/*     */   {
/*  15 */     int x = cx * 16;
/*  16 */     int z = cz * 16;
/*     */     
/*  18 */     for (int a = 1; a <= 15; a++) for (int b = 1; b <= 15; b++) for (int c = 0; c < 13; c++) {
/*  19 */           if ((a == 1) || (a == 15) || (b == 1) || (b == 15)) {
/*  20 */             placeBlock(world, x + a, y + c, z + b, 1, cell);
/*     */           }
/*     */         }
/*  23 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) for (int c = 1; c < 12; c++) {
/*  24 */           if (((a == 2) || (a == 14) || (b == 2) || (b == 14)) && 
/*  25 */             ((a != 2) || (b <= 3) || (b >= 12) || (!cell.west) || (c >= 10)) && 
/*  26 */             ((a != 14) || (b <= 3) || (b >= 12) || (!cell.east) || (c >= 10)) && 
/*  27 */             ((b != 2) || (a <= 3) || (a >= 12) || (!cell.north) || (c >= 10)) && (
/*  28 */             (b != 14) || (a <= 3) || (a >= 12) || (!cell.south) || (c >= 10))) {
/*  29 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/*  32 */     for (int a = 3; a <= 13; a++) for (int b = 3; b <= 13; b++) { for (int c = 2; c < 11; c++) {
/*  33 */           if (((a == 3) || (a == 13) || (b == 3) || (b == 13)) && ((a > 4) || (b > 4)) && ((a > 4) || (b < 12)) && ((a < 12) || (b > 4)) && ((a < 12) || (b < 12)))
/*     */           {
/*     */ 
/*  36 */             placeBlock(world, x + a, y + c, z + b, 2, cell);
/*     */           }
/*     */         }
/*     */       }
/*  40 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) {
/*  41 */         placeBlock(world, x + a, y - 1, z + b, 1, cell);
/*  42 */         placeBlock(world, x + a, y, z + b, 8, cell);
/*  43 */         placeBlock(world, x + a, y + 1, z + b, 19, cell);
/*  44 */         placeBlock(world, x + a, y + 13, z + b, 1, cell);
/*  45 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/*  46 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */         
/*  48 */         if ((a > 1) && (a < 15) && (b > 1) && (b < 15)) {
/*  49 */           int q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
/*  50 */           for (int g = 0; g < q - 1; g++) { placeBlock(world, x + a, y + 1 + g, z + b, 19, cell);
/*     */           }
/*     */         }
/*  53 */         if ((a > 3) && (a < 13) && (b > 3) && (b < 13)) {
/*  54 */           int q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
/*  55 */           for (int g = 0; g < q; g++) { placeBlock(world, x + a, y + 11 - g, z + b, 19, cell);
/*     */           }
/*     */         }
/*     */       }
/*  59 */     for (int g = 0; g < 5; g++) {
/*  60 */       placeBlock(world, x + 6 + g, y + 2, z + 4, 10, ForgeDirection.NORTH, cell);
/*  61 */       placeBlock(world, x + 6 + g, y + 2, z + 12, 10, ForgeDirection.SOUTH, cell);
/*  62 */       placeBlock(world, x + 12, y + 2, z + 6 + g, 10, ForgeDirection.EAST, cell);
/*  63 */       placeBlock(world, x + 4, y + 2, z + 6 + g, 10, ForgeDirection.WEST, cell);
/*     */     }
/*     */     
/*     */ 
/*  67 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*     */     
/*  69 */     for (int a = 3; a <= 13; a++) for (int b = 3; b <= 13; b++) { for (int c = 1; c < 12; c++) {
/*  70 */           if (((a <= 4) && (b <= 4)) || ((a <= 4) && (b >= 12)) || ((a >= 12) && (b <= 4)) || ((a >= 12) && (b >= 12)))
/*     */           {
/*  72 */             placeBlock(world, x + a, y + c, z + b, 9, cell);
/*  73 */             world.func_147444_c(x + a, y + c, z + b, Blocks.field_150350_a);
/*     */           }
/*     */         }
/*     */       }
/*  77 */     placeBlock(world, x + 5, y + 3, z + 5, 10, ForgeDirection.NORTH, cell);
/*  78 */     placeBlock(world, x + 4, y + 3, z + 5, 10, ForgeDirection.NORTH, cell);
/*  79 */     placeBlock(world, x + 5, y + 3, z + 4, 10, ForgeDirection.WEST, cell);
/*  80 */     placeBlock(world, x + 5, y + 8, z + 5, 11, ForgeDirection.NORTH, cell);
/*  81 */     placeBlock(world, x + 4, y + 8, z + 5, 11, ForgeDirection.NORTH, cell);
/*  82 */     placeBlock(world, x + 5, y + 8, z + 4, 11, ForgeDirection.WEST, cell);
/*     */     
/*  84 */     placeBlock(world, x + 12, y + 3, z + 5, 10, ForgeDirection.NORTH, cell);
/*  85 */     placeBlock(world, x + 11, y + 3, z + 5, 10, ForgeDirection.NORTH, cell);
/*  86 */     placeBlock(world, x + 11, y + 3, z + 4, 10, ForgeDirection.EAST, cell);
/*  87 */     placeBlock(world, x + 12, y + 8, z + 5, 11, ForgeDirection.NORTH, cell);
/*  88 */     placeBlock(world, x + 11, y + 8, z + 5, 11, ForgeDirection.NORTH, cell);
/*  89 */     placeBlock(world, x + 11, y + 8, z + 4, 11, ForgeDirection.EAST, cell);
/*     */     
/*  91 */     placeBlock(world, x + 5, y + 3, z + 11, 10, ForgeDirection.SOUTH, cell);
/*  92 */     placeBlock(world, x + 4, y + 3, z + 11, 10, ForgeDirection.SOUTH, cell);
/*  93 */     placeBlock(world, x + 5, y + 3, z + 12, 10, ForgeDirection.WEST, cell);
/*  94 */     placeBlock(world, x + 5, y + 8, z + 11, 11, ForgeDirection.SOUTH, cell);
/*  95 */     placeBlock(world, x + 4, y + 8, z + 11, 11, ForgeDirection.SOUTH, cell);
/*  96 */     placeBlock(world, x + 5, y + 8, z + 12, 11, ForgeDirection.WEST, cell);
/*     */     
/*  98 */     placeBlock(world, x + 12, y + 3, z + 11, 10, ForgeDirection.SOUTH, cell);
/*  99 */     placeBlock(world, x + 11, y + 3, z + 11, 10, ForgeDirection.SOUTH, cell);
/* 100 */     placeBlock(world, x + 11, y + 3, z + 12, 10, ForgeDirection.EAST, cell);
/* 101 */     placeBlock(world, x + 12, y + 8, z + 11, 11, ForgeDirection.SOUTH, cell);
/* 102 */     placeBlock(world, x + 11, y + 8, z + 11, 11, ForgeDirection.SOUTH, cell);
/* 103 */     placeBlock(world, x + 11, y + 8, z + 12, 11, ForgeDirection.EAST, cell);
/*     */     
/* 105 */     world.func_147465_d(x + 8, y + 2, z + 8, ConfigBlocks.blockEldritch, 3, 3);
/* 106 */     world.func_147449_b(x + 8, y + 3, z + 8, ConfigBlocks.blockEldritchPortal);
/* 107 */     genObelisk(world, x + 8, y + 4, z + 8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/GenPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */