/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ 
/*    */ public class BlockLootUrnRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 18 */     int metadata = world.func_72805_g(x, y, z);
/* 19 */     block.func_149676_a(W3, 0.0F, W3, W13, W1, W13);
/* 20 */     renderer.func_147775_a(block);
/* 21 */     renderer.func_147784_q(block, x, y, z);
/*    */     
/* 23 */     block.func_149676_a(W2, W1, W2, W14, W13, W14);
/* 24 */     renderer.func_147775_a(block);
/* 25 */     renderer.func_147784_q(block, x, y, z);
/*    */     
/* 27 */     block.func_149676_a(W4, W13, W4, W12, 1.0F, W12);
/* 28 */     renderer.func_147775_a(block);
/* 29 */     renderer.func_147784_q(block, x, y, z);
/*    */     
/* 31 */     renderer.func_147771_a();
/* 32 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 33 */     renderer.func_147775_a(block);
/* 34 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 39 */     return false;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 44 */     return ConfigBlocks.blockLootUrnRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockLootUrnRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */