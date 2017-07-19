/*     */ package thaumcraft.client.renderers.block;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.blocks.BlockTaintFibres;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTaintFibreRenderer
/*     */   extends BlockRenderer
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*     */   {
/*  26 */     boolean fix = true;
/*  27 */     int metadata = world.func_72805_g(x, y, z);
/*  28 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  29 */     renderer.func_147775_a(block);
/*  30 */     setBrightness(world, x, y, z, block);
/*  31 */     Tessellator t = Tessellator.field_78398_a;
/*     */     
/*  33 */     if (metadata <= 4) {
/*  34 */       if ((world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true)) && (world.func_147439_a(x - 1, y, z) != ConfigBlocks.blockTaint))
/*     */       {
/*  36 */         fix = false;
/*  37 */         renderer.func_147764_f(block, x - 0.995F, y, z, block.func_149691_a(0, 0));
/*     */       }
/*  39 */       if ((world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true)) && (world.func_147439_a(x + 1, y, z) != ConfigBlocks.blockTaint))
/*     */       {
/*  41 */         fix = false;
/*  42 */         renderer.func_147798_e(block, x + 0.995F, y, z, block.func_149691_a(0, 0));
/*     */       }
/*  44 */       if ((world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true)) && (world.func_147439_a(x, y, z - 1) != ConfigBlocks.blockTaint))
/*     */       {
/*  46 */         fix = false;
/*  47 */         renderer.func_147734_d(block, x, y, z - 0.995F, block.func_149691_a(0, 0));
/*     */       }
/*  49 */       if ((world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true)) && (world.func_147439_a(x, y, z + 1) != ConfigBlocks.blockTaint))
/*     */       {
/*  51 */         fix = false;
/*  52 */         renderer.func_147761_c(block, x, y, z + 0.995F, block.func_149691_a(0, 0));
/*     */       }
/*  54 */       if ((world.isSideSolid(x, y - 1, z, ForgeDirection.UP, true)) && (world.func_147439_a(x, y - 1, z) != ConfigBlocks.blockTaint))
/*     */       {
/*  56 */         fix = false;
/*  57 */         renderer.func_147806_b(block, x, y - 0.995F, z, block.func_149691_a(0, 0));
/*     */       }
/*  59 */       if ((world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN, true)) && (world.func_147439_a(x, y + 1, z) != ConfigBlocks.blockTaint))
/*     */       {
/*  61 */         fix = false;
/*  62 */         renderer.func_147768_a(block, x, y + 0.995F, z, block.func_149691_a(0, 0));
/*     */       }
/*     */     }
/*     */     
/*  66 */     if ((metadata == 0) && (Config.glowyTaint)) {
/*  67 */       t.func_78386_a(1.0F, 1.0F, 1.0F);
/*  68 */       t.func_78380_c(200);
/*  69 */       if ((world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true)) && (world.func_147439_a(x - 1, y, z) != ConfigBlocks.blockTaint))
/*     */       {
/*  71 */         fix = false;
/*  72 */         renderer.func_147764_f(block, x - 0.98F, y, z, ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 4));
/*     */       }
/*  74 */       if ((world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true)) && (world.func_147439_a(x + 1, y, z) != ConfigBlocks.blockTaint))
/*     */       {
/*  76 */         fix = false;
/*  77 */         renderer.func_147798_e(block, x + 0.98F, y, z, ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 5));
/*     */       }
/*  79 */       if ((world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true)) && (world.func_147439_a(x, y, z - 1) != ConfigBlocks.blockTaint))
/*     */       {
/*  81 */         fix = false;
/*  82 */         renderer.func_147734_d(block, x, y, z - 0.98F, ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 2));
/*     */       }
/*  84 */       if ((world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true)) && (world.func_147439_a(x, y, z + 1) != ConfigBlocks.blockTaint))
/*     */       {
/*  86 */         fix = false;
/*  87 */         renderer.func_147761_c(block, x, y, z + 0.98F, ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 3));
/*     */       }
/*  89 */       if ((world.isSideSolid(x, y - 1, z, ForgeDirection.UP, true)) && (world.func_147439_a(x, y - 1, z) != ConfigBlocks.blockTaint))
/*     */       {
/*  91 */         fix = false;
/*  92 */         renderer.func_147806_b(block, x, y - 0.98F, z, ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 0));
/*     */       }
/*  94 */       if ((world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN, true)) && (world.func_147439_a(x, y + 1, z) != ConfigBlocks.blockTaint))
/*     */       {
/*  96 */         fix = false;
/*  97 */         renderer.func_147768_a(block, x, y + 0.98F, z, ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 1));
/*     */       }
/*     */     }
/*     */     
/* 101 */     if (((metadata == 1) || (metadata == 2)) && (world.isSideSolid(x, y - 1, z, ForgeDirection.UP, true))) {
/* 102 */       double d0 = x;
/* 103 */       double d1 = y;
/* 104 */       double d2 = z;
/*     */       
/* 106 */       long i1 = x * 3129871 ^ z * 116129781L ^ y;
/* 107 */       i1 = i1 * i1 * 42317861L + i1 * 11L;
/* 108 */       d0 += ((float)(i1 >> 16 & 0xF) / 15.0F - 0.5D) * 0.5D;
/* 109 */       d2 += ((float)(i1 >> 24 & 0xF) / 15.0F - 0.5D) * 0.5D;
/* 110 */       fix = false;
/* 111 */       renderer.func_147765_a(block.func_149691_a(0, metadata), d0, d1, d2, 1.0F);
/*     */     }
/*     */     
/* 114 */     if ((metadata == 3) || (metadata == 4)) {
/* 115 */       fix = false;
/* 116 */       renderer.func_147746_l(block, x, y, z);
/*     */     }
/*     */     
/* 119 */     if (fix) {
/* 120 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/* 121 */       renderer.func_147775_a(block);
/* 122 */       renderer.func_147784_q(block, x, y, z);
/*     */     }
/*     */     
/* 125 */     renderer.func_147771_a();
/* 126 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 127 */     renderer.func_147775_a(block);
/* 128 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId)
/*     */   {
/* 133 */     return false;
/*     */   }
/*     */   
/*     */   public int getRenderId()
/*     */   {
/* 138 */     return ConfigBlocks.blockTaintFibreRI;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockTaintFibreRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */