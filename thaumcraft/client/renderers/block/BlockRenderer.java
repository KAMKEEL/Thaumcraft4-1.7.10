/*     */ package thaumcraft.client.renderers.block;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class BlockRenderer
/*     */ {
/*  14 */   public static float W1 = 0.0625F;
/*  15 */   public static float W2 = 0.125F;
/*  16 */   public static float W3 = 0.1875F;
/*  17 */   public static float W4 = 0.25F;
/*  18 */   public static float W5 = 0.3125F;
/*  19 */   public static float W6 = 0.375F;
/*  20 */   public static float W7 = 0.4375F;
/*  21 */   public static float W8 = 0.5F;
/*  22 */   public static float W9 = 0.5625F;
/*  23 */   public static float W10 = 0.625F;
/*  24 */   public static float W11 = 0.6875F;
/*  25 */   public static float W12 = 0.75F;
/*  26 */   public static float W13 = 0.8125F;
/*  27 */   public static float W14 = 0.875F;
/*  28 */   public static float W15 = 0.9375F;
/*     */   
/*     */   public static void drawFaces(RenderBlocks renderblocks, Block block, IIcon icon, boolean st)
/*     */   {
/*  32 */     drawFaces(renderblocks, block, icon, icon, icon, icon, icon, icon, st);
/*     */   }
/*     */   
/*     */   public static void drawFaces(RenderBlocks renderblocks, Block block, IIcon i1, IIcon i2, IIcon i3, IIcon i4, IIcon i5, IIcon i6, boolean solidtop)
/*     */   {
/*  37 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  38 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  39 */     tessellator.func_78382_b();
/*  40 */     tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
/*  41 */     renderblocks.func_147768_a(block, 0.0D, 0.0D, 0.0D, i1);
/*  42 */     tessellator.func_78381_a();
/*  43 */     if (solidtop) GL11.glDisable(3008);
/*  44 */     tessellator.func_78382_b();
/*  45 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/*  46 */     renderblocks.func_147806_b(block, 0.0D, 0.0D, 0.0D, i2);
/*  47 */     tessellator.func_78381_a();
/*  48 */     if (solidtop) GL11.glEnable(3008);
/*  49 */     tessellator.func_78382_b();
/*  50 */     tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/*  51 */     renderblocks.func_147798_e(block, 0.0D, 0.0D, 0.0D, i3);
/*  52 */     tessellator.func_78381_a();
/*  53 */     tessellator.func_78382_b();
/*  54 */     tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  55 */     renderblocks.func_147764_f(block, 0.0D, 0.0D, 0.0D, i4);
/*  56 */     tessellator.func_78381_a();
/*  57 */     tessellator.func_78382_b();
/*  58 */     tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/*  59 */     renderblocks.func_147761_c(block, 0.0D, 0.0D, 0.0D, i5);
/*  60 */     tessellator.func_78381_a();
/*  61 */     tessellator.func_78382_b();
/*  62 */     tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/*  63 */     renderblocks.func_147734_d(block, 0.0D, 0.0D, 0.0D, i6);
/*  64 */     tessellator.func_78381_a();
/*  65 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */   
/*     */   public static int setBrightness(IBlockAccess blockAccess, int i, int j, int k, Block block) {
/*  69 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  70 */     int mb = block.func_149677_c(blockAccess, i, j, k);
/*  71 */     tessellator.func_78380_c(mb);
/*     */     
/*  73 */     float f = 1.0F;
/*     */     
/*  75 */     int l = block.func_149720_d(blockAccess, i, j, k);
/*  76 */     float f1 = (l >> 16 & 0xFF) / 255.0F;
/*  77 */     float f2 = (l >> 8 & 0xFF) / 255.0F;
/*  78 */     float f3 = (l & 0xFF) / 255.0F;
/*  79 */     if (EntityRenderer.field_78517_a)
/*     */     {
/*  81 */       float f6 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
/*  82 */       float f4 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
/*  83 */       float f7 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
/*  84 */       f1 = f6;
/*  85 */       f2 = f4;
/*  86 */       f3 = f7;
/*     */     }
/*  88 */     tessellator.func_78386_a(f * f1, f * f2, f * f3);
/*  89 */     return mb;
/*     */   }
/*     */   
/*     */ 
/*     */   protected static void renderAllSides(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, IIcon tex)
/*     */   {
/*  95 */     renderAllSides(world, x, y, z, block, renderer, tex, true);
/*     */   }
/*     */   
/*     */   protected static void renderAllSides(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, IIcon tex, boolean allsides) {
/*  99 */     if ((allsides) || (block.func_149646_a(world, x + 1, y, z, 6))) renderer.func_147764_f(block, x, y, z, tex);
/* 100 */     if ((allsides) || (block.func_149646_a(world, x - 1, y, z, 6))) renderer.func_147798_e(block, x, y, z, tex);
/* 101 */     if ((allsides) || (block.func_149646_a(world, x, y, z + 1, 6))) renderer.func_147734_d(block, x, y, z, tex);
/* 102 */     if ((allsides) || (block.func_149646_a(world, x, y, z - 1, 6))) renderer.func_147761_c(block, x, y, z, tex);
/* 103 */     if ((allsides) || (block.func_149646_a(world, x, y + 1, z, 6))) renderer.func_147806_b(block, x, y, z, tex);
/* 104 */     if ((allsides) || (block.func_149646_a(world, x, y - 1, z, 6))) renderer.func_147768_a(block, x, y, z, tex);
/*     */   }
/*     */   
/*     */   protected static void renderAllSides(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, boolean allsides) {
/* 108 */     if ((allsides) || (block.func_149646_a(world, x + 1, y, z, 6))) renderer.func_147764_f(block, x, y, z, block.func_149673_e(world, x, y, z, 5));
/* 109 */     if ((allsides) || (block.func_149646_a(world, x - 1, y, z, 6))) renderer.func_147798_e(block, x, y, z, block.func_149673_e(world, x, y, z, 4));
/* 110 */     if ((allsides) || (block.func_149646_a(world, x, y, z + 1, 6))) renderer.func_147734_d(block, x, y, z, block.func_149673_e(world, x, y, z, 3));
/* 111 */     if ((allsides) || (block.func_149646_a(world, x, y, z - 1, 6))) renderer.func_147761_c(block, x, y, z, block.func_149673_e(world, x, y, z, 2));
/* 112 */     if ((allsides) || (block.func_149646_a(world, x, y + 1, z, 6))) renderer.func_147806_b(block, x, y, z, block.func_149673_e(world, x, y, z, 1));
/* 113 */     if ((allsides) || (block.func_149646_a(world, x, y - 1, z, 6))) renderer.func_147768_a(block, x, y, z, block.func_149673_e(world, x, y, z, 0));
/*     */   }
/*     */   
/*     */   protected static void renderAllSidesInverted(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, IIcon tex, boolean allsides)
/*     */   {
/* 118 */     if ((allsides) || (!block.func_149646_a(world, x - 1, y, z, 6))) renderer.func_147764_f(block, x - 1, y, z, tex);
/* 119 */     if ((allsides) || (!block.func_149646_a(world, x + 1, y, z, 6))) renderer.func_147798_e(block, x + 1, y, z, tex);
/* 120 */     if ((allsides) || (!block.func_149646_a(world, x, y, z - 1, 6))) renderer.func_147734_d(block, x, y, z - 1, tex);
/* 121 */     if ((allsides) || (!block.func_149646_a(world, x, y, z + 1, 6))) renderer.func_147761_c(block, x, y, z + 1, tex);
/* 122 */     if ((allsides) || (!block.func_149646_a(world, x, y - 1, z, 6))) renderer.func_147806_b(block, x, y - 1, z, tex);
/* 123 */     if ((allsides) || (!block.func_149646_a(world, x, y + 1, z, 6))) renderer.func_147768_a(block, x, y + 1, z, tex);
/*     */   }
/*     */   
/*     */   protected static void renderAllSides(int x, int y, int z, Block block, RenderBlocks renderer, IIcon tex)
/*     */   {
/* 128 */     renderer.func_147764_f(block, x - 1, y, z, tex);
/* 129 */     renderer.func_147798_e(block, x + 1, y, z, tex);
/* 130 */     renderer.func_147734_d(block, x, y, z - 1, tex);
/* 131 */     renderer.func_147761_c(block, x, y, z + 1, tex);
/* 132 */     renderer.func_147806_b(block, x, y - 1, z, tex);
/* 133 */     renderer.func_147768_a(block, x, y + 1, z, tex);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */