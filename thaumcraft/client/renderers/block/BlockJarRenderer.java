/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.blocks.BlockJar;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.tiles.TileJarBrain;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockJarRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*    */   {
/* 27 */     if (metadata == 1) {
/* 28 */       GL11.glPushMatrix();
/* 29 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 30 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 31 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileJarBrain(), 0.0D, 0.0D, 0.0D, 0.0F);
/* 32 */       GL11.glEnable(32826);
/* 33 */       GL11.glPopMatrix();
/*    */     }
/*    */     
/* 36 */     GL11.glPushMatrix();
/* 37 */     GL11.glEnable(3042);
/* 38 */     GL11.glBlendFunc(770, 771);
/* 39 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
/* 40 */     IIcon i1 = ((BlockJar)block).iconJarTop;
/* 41 */     IIcon i2 = ((BlockJar)block).iconJarSide;
/* 42 */     if (metadata == 3) {
/* 43 */       i1 = ((BlockJar)block).iconJarTopVoid;
/* 44 */       i2 = ((BlockJar)block).iconJarSideVoid;
/*    */     }
/* 46 */     block.func_149676_a(W3, 0.0F, W3, W13, W12, W13);
/* 47 */     renderer.func_147775_a(block);
/* 48 */     drawFaces(renderer, block, ((BlockJar)block).iconJarBottom, i1, i2, i2, i2, i2, true);
/* 49 */     block.func_149676_a(W5, W12, W5, W11, W14, W11);
/* 50 */     renderer.func_147775_a(block);
/* 51 */     drawFaces(renderer, block, ((BlockJar)block).iconJarBottom, i1, i2, i2, i2, i2, true);
/* 52 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 58 */     int bb = setBrightness(world, x, y, z, block);
/* 59 */     int metadata = world.func_72805_g(x, y, z);
/* 60 */     block.func_149676_a(W3, 0.0F, W3, W13, W12, W13);
/* 61 */     renderer.func_147775_a(block);
/* 62 */     renderer.func_147784_q(block, x, y, z);
/* 63 */     block.func_149676_a(W5, W12, W5, W11, W14, W11);
/* 64 */     renderer.func_147775_a(block);
/* 65 */     renderer.func_147784_q(block, x, y, z);
/*    */     
/* 67 */     renderer.func_147771_a();
/* 68 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 69 */     renderer.func_147775_a(block);
/* 70 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 75 */     return true;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 80 */     return ConfigBlocks.blockJarRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockJarRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */