/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockCrops;
/*     */ import net.minecraft.block.BlockStem;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CropUtils
/*     */ {
/*  16 */   public static ArrayList<String> clickableCrops = new ArrayList();
/*  17 */   public static ArrayList<String> standardCrops = new ArrayList();
/*  18 */   public static ArrayList<String> stackedCrops = new ArrayList();
/*  19 */   public static ArrayList<String> lampBlacklist = new ArrayList();
/*     */   
/*     */   public static void addStandardCrop(ItemStack stack, int grownMeta) {
/*  22 */     if (Block.func_149634_a(stack.func_77973_b()) == null) return;
/*  23 */     if (grownMeta == 32767) {
/*  24 */       for (int a = 0; a < 16; a++) standardCrops.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + a);
/*     */     } else {
/*  26 */       standardCrops.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + grownMeta);
/*     */     }
/*  28 */     if (((Block.func_149634_a(stack.func_77973_b()) instanceof BlockCrops)) && (grownMeta != 7))
/*  29 */       standardCrops.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + "7");
/*     */   }
/*     */   
/*     */   public static void addClickableCrop(ItemStack stack, int grownMeta) {
/*  33 */     if (Block.func_149634_a(stack.func_77973_b()) == null) return;
/*  34 */     if (grownMeta == 32767) {
/*  35 */       for (int a = 0; a < 16; a++) clickableCrops.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + a);
/*     */     } else {
/*  37 */       clickableCrops.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + grownMeta);
/*     */     }
/*  39 */     if (((Block.func_149634_a(stack.func_77973_b()) instanceof BlockCrops)) && (grownMeta != 7))
/*  40 */       clickableCrops.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + "7");
/*     */   }
/*     */   
/*     */   public static void addStackedCrop(ItemStack stack, int grownMeta) {
/*  44 */     if (Block.func_149634_a(stack.func_77973_b()) == null) return;
/*  45 */     addStackedCrop(Block.func_149634_a(stack.func_77973_b()), grownMeta);
/*     */   }
/*     */   
/*     */   public static void addStackedCrop(Block block, int grownMeta) {
/*  49 */     if (grownMeta == 32767) {
/*  50 */       for (int a = 0; a < 16; a++) stackedCrops.add(block.func_149739_a() + a);
/*     */     } else {
/*  52 */       stackedCrops.add(block.func_149739_a() + grownMeta);
/*     */     }
/*  54 */     if (((block instanceof BlockCrops)) && (grownMeta != 7))
/*  55 */       stackedCrops.add(block.func_149739_a() + "7");
/*     */   }
/*     */   
/*     */   public static boolean isGrownCrop(World world, int x, int y, int z) {
/*  59 */     if (world.func_147437_c(x, y, z)) return false;
/*  60 */     boolean found = false;
/*  61 */     Block bi = world.func_147439_a(x, y, z);
/*  62 */     for (int a = 0; a < 16; a++) {
/*  63 */       if ((standardCrops.contains(bi.func_149739_a() + a)) || (clickableCrops.contains(bi.func_149739_a() + a)) || (stackedCrops.contains(bi.func_149739_a() + a)))
/*     */       {
/*     */ 
/*  66 */         found = true;
/*  67 */         break;
/*     */       }
/*     */     }
/*     */     
/*  71 */     Block biA = world.func_147439_a(x, y + 1, z);
/*  72 */     Block biB = world.func_147439_a(x, y - 1, z);
/*  73 */     int md = world.func_72805_g(x, y, z);
/*  74 */     if ((((bi instanceof IGrowable)) && (!((IGrowable)bi).func_149851_a(world, x, y, z, world.field_72995_K)) && (!(bi instanceof BlockStem))) || (((bi instanceof BlockCrops)) && (md == 7) && (!found)) || ((bi == Blocks.field_150388_bm) && (md >= 3)) || ((bi == Blocks.field_150375_by) && ((md & 0xC) >> 2 >= 2)) || (standardCrops.contains(bi.func_149739_a() + md)) || (clickableCrops.contains(bi.func_149739_a() + md)) || ((stackedCrops.contains(bi.func_149739_a() + md)) && (biB == bi)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  88 */       return true;
/*     */     }
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public static void blacklistLamp(ItemStack stack, int meta) {
/*  94 */     if (Block.func_149634_a(stack.func_77973_b()) == null) return;
/*  95 */     if (meta == 32767) {
/*  96 */       for (int a = 0; a < 16; a++) lampBlacklist.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + a);
/*     */     } else {
/*  98 */       lampBlacklist.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + meta);
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean doesLampGrow(World world, int x, int y, int z) {
/* 103 */     Block bi = world.func_147439_a(x, y, z);
/* 104 */     int md = world.func_72805_g(x, y, z);
/* 105 */     if (lampBlacklist.contains(bi.func_149739_a() + md)) {
/* 106 */       return false;
/*     */     }
/* 108 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/utils/CropUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */