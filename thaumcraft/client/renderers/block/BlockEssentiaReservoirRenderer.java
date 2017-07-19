/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.blocks.BlockEssentiaReservoir;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.tiles.TileEssentiaReservoir;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockEssentiaReservoirRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*    */   {
/* 21 */     block.func_149676_a(W2, W2, W2, W14, W14, W14);
/* 22 */     renderer.func_147775_a(block);
/* 23 */     drawFaces(renderer, block, ((BlockEssentiaReservoir)block).icon, true);
/*    */     
/* 25 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 26 */     TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileEssentiaReservoir(), 0.0D, 0.0D, 0.0D, 0.0F);
/* 27 */     GL11.glEnable(32826);
/*    */   }
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 32 */     block.func_149676_a(W2, W2, W2, W14, W14, W14);
/* 33 */     renderer.func_147775_a(block);
/* 34 */     renderer.func_147784_q(block, x, y, z);
/*    */     
/* 36 */     renderer.func_147771_a();
/* 37 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 38 */     renderer.func_147775_a(block);
/* 39 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 44 */     return true;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 49 */     return ConfigBlocks.blockEssentiaReservoirRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockEssentiaReservoirRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */