/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ 
/*    */ public class BlockLootCrateRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 18 */     int metadata = world.func_72805_g(x, y, z);
/* 19 */     block.func_149676_a(W1, 0.0F, W1, W15, W14, W15);
/* 20 */     renderer.func_147775_a(block);
/* 21 */     renderer.func_147784_q(block, x, y, z);
/*    */     
/* 23 */     renderer.func_147771_a();
/* 24 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 25 */     renderer.func_147775_a(block);
/* 26 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 36 */     return ConfigBlocks.blockLootCrateRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockLootCrateRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */