/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.EntityPermanentItem;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class GenKeyRoom
/*     */   extends GenCommon
/*     */ {
/*     */   static void generateRoom(World world, Random random, int cx, int cz, int y, Cell cell)
/*     */   {
/*  21 */     int x = cx * 16;
/*  22 */     int z = cz * 16;
/*     */     
/*  24 */     for (int a = 1; a <= 15; a++) for (int b = 1; b <= 15; b++) for (int c = 0; c < 13; c++) {
/*  25 */           if ((a == 1) || (a == 15) || (b == 1) || (b == 15)) {
/*  26 */             placeBlock(world, x + a, y + c, z + b, 1, cell);
/*     */           }
/*     */         }
/*  29 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) for (int c = 1; c < 12; c++) {
/*  30 */           if (((a == 2) || (a == 14) || (b == 2) || (b == 14)) && 
/*  31 */             ((a != 2) || (b <= 3) || (b >= 12) || (!cell.west) || (c >= 10)) && 
/*  32 */             ((a != 14) || (b <= 3) || (b >= 12) || (!cell.east) || (c >= 10)) && 
/*  33 */             ((b != 2) || (a <= 3) || (a >= 12) || (!cell.north) || (c >= 10)) && (
/*  34 */             (b != 14) || (a <= 3) || (a >= 12) || (!cell.south) || (c >= 10))) {
/*  35 */             placeBlock(world, x + a, y + c, z + b, 8, cell);
/*     */           }
/*     */         }
/*  38 */     for (int a = 3; a <= 13; a++) for (int b = 3; b <= 13; b++) for (int c = 2; c < 11; c++) {
/*  39 */           if ((a == 3) || (a == 13) || (b == 3) || (b == 13)) {
/*  40 */             if (((c > 3) && (c < 9) && ((a == 8) || (b == 8))) || ((c > 4) && (c < 8) && ((a == 7) || (b == 7) || (a == 9) || (b == 9)))) {
/*  41 */               if (((a != 8) && (b != 8)) || (c != 6)) placeBlock(world, x + a, y + c, z + b, 19, cell);
/*     */             } else {
/*  43 */               placeBlock(world, x + a, y + c, z + b, 18, cell);
/*     */             }
/*     */           }
/*     */         }
/*  47 */     for (int a = 2; a <= 14; a++) for (int b = 2; b <= 14; b++) {
/*  48 */         placeBlock(world, x + a, y - 1, z + b, 1, cell);
/*  49 */         placeBlock(world, x + a, y, z + b, 8, cell);
/*  50 */         placeBlock(world, x + a, y + 1, z + b, 2, cell);
/*  51 */         placeBlock(world, x + a, y + 13, z + b, 1, cell);
/*  52 */         placeBlock(world, x + a, y + 12, z + b, 8, cell);
/*  53 */         placeBlock(world, x + a, y + 11, z + b, 2, cell);
/*     */         
/*  55 */         if ((a > 1) && (a < 15) && (b > 1) && (b < 15)) {
/*  56 */           int q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
/*  57 */           for (int g = 0; g < q - 1; g++) { placeBlock(world, x + a, y + 1 + g, z + b, 2, cell);
/*     */           }
/*     */         }
/*  60 */         if ((a > 3) && (a < 13) && (b > 3) && (b < 13)) {
/*  61 */           int q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
/*  62 */           for (int g = 0; g < q; g++) { placeBlock(world, x + a, y + 11 - g, z + b, 2, cell);
/*     */           }
/*     */         }
/*     */       }
/*  66 */     for (int g = 0; g < 5; g++) {
/*  67 */       placeBlock(world, x + 6 + g, y + 2, z + 4, 10, ForgeDirection.NORTH, cell);
/*  68 */       placeBlock(world, x + 6 + g, y + 2, z + 12, 10, ForgeDirection.SOUTH, cell);
/*  69 */       placeBlock(world, x + 12, y + 2, z + 6 + g, 10, ForgeDirection.EAST, cell);
/*  70 */       placeBlock(world, x + 4, y + 2, z + 6 + g, 10, ForgeDirection.WEST, cell);
/*     */     }
/*     */     
/*     */ 
/*  74 */     GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
/*     */     
/*  76 */     world.func_147465_d(x + 8, y + 2, z + 8, ConfigBlocks.blockEldritch, 3, 3);
/*     */     
/*  78 */     EntityPermanentItem entityitem = new EntityPermanentItem(world, x + 8.5D, y + 3.5D, z + 8.5D, new ItemStack(ConfigItems.itemEldritchObject, 1, 2));
/*     */     
/*     */ 
/*  81 */     entityitem.field_70181_x = 0.0D;
/*  82 */     entityitem.field_70159_w = 0.0D;
/*  83 */     entityitem.field_70179_y = 0.0D;
/*  84 */     world.func_72838_d(entityitem);
/*  85 */     int zz = 2 + (world.field_73013_u == EnumDifficulty.NORMAL ? 1 : world.field_73013_u == EnumDifficulty.HARD ? 2 : 0);
/*  86 */     for (int qq = 0; qq < zz; qq++)
/*     */     {
/*  88 */       EntityEldritchGuardian eg = new EntityEldritchGuardian(world);
/*  89 */       double i1 = x + 8.5D + MathHelper.func_76136_a(world.field_73012_v, 1, 3) * MathHelper.func_76136_a(world.field_73012_v, -1, 1);
/*  90 */       double j1 = y + 2;
/*  91 */       double k1 = z + 8.5D + MathHelper.func_76136_a(world.field_73012_v, 1, 3) * MathHelper.func_76136_a(world.field_73012_v, -1, 1);
/*     */       
/*  93 */       eg.func_70107_b(i1, j1, k1);
/*     */       
/*  95 */       eg.func_110161_a((IEntityLivingData)null);
/*     */       
/*  97 */       eg.func_110171_b(x + 8, y + 2, z + 8, 16);
/*     */       
/*  99 */       if ((qq == 0) && (zz >= 4)) { EntityUtils.makeChampion(eg, true);
/*     */       }
/* 101 */       world.func_72838_d(eg);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/GenKeyRoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */