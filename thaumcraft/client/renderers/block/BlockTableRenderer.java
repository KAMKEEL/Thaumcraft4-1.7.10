/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.tiles.TileArcaneWorkbench;
/*    */ import thaumcraft.common.tiles.TileDeconstructionTable;
/*    */ import thaumcraft.common.tiles.TileResearchTable;
/*    */ import thaumcraft.common.tiles.TileTable;
/*    */ 
/*    */ public class BlockTableRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public BlockTableRenderer()
/*    */   {
/* 23 */     this.trt.contents[0] = new ItemStack(ConfigItems.itemInkwell);
/*    */     
/* 25 */     this.trt.contents[1] = new ItemStack(ConfigItems.itemResearchNotes);
/*    */   }
/*    */   
/* 28 */   TileResearchTable trt = new TileResearchTable();
/*    */   
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*    */   {
/* 32 */     GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 33 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 34 */     if (metadata == 0) {
/* 35 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileTable(), 0.0D, 0.0D, 0.0D, 0.0F);
/* 36 */     } else if (metadata == 14) {
/* 37 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileDeconstructionTable(), 0.0D, 0.0D, 0.0D, 0.0F);
/* 38 */     } else if (metadata == 15) {
/* 39 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileArcaneWorkbench(), 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     } else {
/* 41 */       GL11.glTranslatef(-0.5F, 0.0F, 0.0F);
/* 42 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.trt, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     }
/* 44 */     GL11.glEnable(32826);
/*    */   }
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 49 */     return false;
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 54 */     return true;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 59 */     return ConfigBlocks.blockTableRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockTableRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */