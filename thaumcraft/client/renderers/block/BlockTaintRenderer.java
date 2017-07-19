/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.common.blocks.BlockTaintFibres;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class BlockTaintRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*    */   {
/* 18 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 19 */     renderer.func_147775_a(block);
/*    */     
/* 21 */     drawFaces(renderer, block, block.func_149691_a(0, metadata), block.func_149691_a(1, metadata), block.func_149691_a(2, metadata), block.func_149691_a(3, metadata), block.func_149691_a(4, metadata), block.func_149691_a(5, metadata), false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 33 */     int metadata = world.func_72805_g(x, y, z);
/*    */     
/* 35 */     if (block.func_149701_w() == 0) {
/* 36 */       if ((metadata == 0) || (metadata == 1) || (metadata == 2)) {
/* 37 */         block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 38 */         renderer.func_147775_a(block);
/* 39 */         renderer.func_147784_q(block, x, y, z);
/*    */       }
/*    */     }
/* 42 */     else if ((block.func_149701_w() == 1) && (
/* 43 */       (metadata == 0) || (metadata == 1))) {
/* 44 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 45 */       renderer.func_147775_a(block);
/* 46 */       BlockTaintFibres b = (BlockTaintFibres)ConfigBlocks.blockTaintFibres;
/* 47 */       Tessellator t = Tessellator.field_78398_a;
/* 48 */       t.func_78386_a(1.0F, 1.0F, 1.0F);
/* 49 */       t.func_78380_c(200);
/* 50 */       if (block.func_149646_a(world, x + 1, y, z, ForgeDirection.EAST.ordinal())) renderer.func_147764_f(block, x, y, z, b.getOverlayBlockTexture(x, y, z, 4));
/* 51 */       if (block.func_149646_a(world, x - 1, y, z, ForgeDirection.WEST.ordinal())) renderer.func_147798_e(block, x, y, z, b.getOverlayBlockTexture(x, y, z, 5));
/* 52 */       if (block.func_149646_a(world, x, y, z + 1, ForgeDirection.SOUTH.ordinal())) renderer.func_147734_d(block, x, y, z, b.getOverlayBlockTexture(x, y, z, 2));
/* 53 */       if (block.func_149646_a(world, x, y, z - 1, ForgeDirection.NORTH.ordinal())) renderer.func_147761_c(block, x, y, z, b.getOverlayBlockTexture(x, y, z, 3));
/* 54 */       if (block.func_149646_a(world, x, y + 1, z, ForgeDirection.UP.ordinal())) renderer.func_147806_b(block, x, y, z, b.getOverlayBlockTexture(x, y, z, 0));
/* 55 */       if (block.func_149646_a(world, x, y - 1, z, ForgeDirection.DOWN.ordinal())) { renderer.func_147768_a(block, x, y, z, b.getOverlayBlockTexture(x, y, z, 1));
/*    */       }
/*    */     }
/*    */     
/* 59 */     renderer.func_147771_a();
/* 60 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 61 */     renderer.func_147775_a(block);
/* 62 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 67 */     return true;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 72 */     return ConfigBlocks.blockTaintRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockTaintRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */