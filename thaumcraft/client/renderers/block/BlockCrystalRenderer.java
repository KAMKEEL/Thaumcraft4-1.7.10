/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.tiles.TileCrystal;
/*    */ import thaumcraft.common.tiles.TileEldritchCrystal;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCrystalRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*    */   {
/* 21 */     if (metadata <= 6) {
/* 22 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 23 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 24 */       TileCrystal tc = new TileCrystal();
/* 25 */       tc.field_145847_g = metadata;
/* 26 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(tc, 0.0D, 0.0D, 0.0D, 0.0F);
/* 27 */       GL11.glEnable(32826);
/*    */     }
/* 29 */     if (metadata == 7) {
/* 30 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 31 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileEldritchCrystal(), 0.0D, 0.0D, 0.0D, 0.0F);
/* 32 */       GL11.glEnable(32826);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 39 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 50 */     return ConfigBlocks.blockCrystalRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockCrystalRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */