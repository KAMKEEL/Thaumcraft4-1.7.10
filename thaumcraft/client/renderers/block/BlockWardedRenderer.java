/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.blocks.BlockWarded;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ 
/*    */ public class BlockWardedRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 19 */     return renderer.func_147805_b(((BlockWarded)block).getBlock(world, x, y, z), x, y, z);
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 29 */     return ConfigBlocks.blockWardedRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockWardedRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */