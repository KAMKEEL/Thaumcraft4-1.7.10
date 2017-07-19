/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.blocks.BlockLifter;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.tiles.TileLifter;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockLifterRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*    */   {
/* 23 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 24 */     renderer.func_147775_a(block);
/* 25 */     drawFaces(renderer, block, ((BlockLifter)block).iconBottom, ((BlockLifter)block).iconTop, ((BlockLifter)block).iconSide, ((BlockLifter)block).iconSide, ((BlockLifter)block).iconSide, ((BlockLifter)block).iconSide, false);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 30 */     Color c = new Color(thaumcraft.common.blocks.BlockCustomOreItem.colors[4]);
/* 31 */     float r = c.getRed() / 255.0F;
/* 32 */     float g = c.getGreen() / 255.0F;
/* 33 */     float b = c.getBlue() / 255.0F;
/* 34 */     GL11.glColor3f(r, g, b);
/* 35 */     block.func_149676_a(0.01F, 0.9F, 0.01F, 0.99F, 0.99F, 0.99F);
/* 36 */     renderer.func_147775_a(block);
/* 37 */     drawFaces(renderer, block, ((BlockLifter)block).iconGlow, false);
/*    */     
/*    */ 
/* 40 */     c = new Color(thaumcraft.common.blocks.BlockCustomOreItem.colors[5]);
/* 41 */     r = c.getRed() / 255.0F;
/* 42 */     g = c.getGreen() / 255.0F;
/* 43 */     b = c.getBlue() / 255.0F;
/* 44 */     GL11.glColor3f(r, g, b);
/* 45 */     block.func_149676_a(0.01F, 0.1F, 0.01F, 0.99F, 0.9F, 0.99F);
/* 46 */     renderer.func_147775_a(block);
/* 47 */     drawFaces(renderer, block, ((BlockLifter)block).iconGlow, false);
/*    */     
/* 49 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 56 */     int bb = setBrightness(world, x, y, z, block);
/* 57 */     int metadata = world.func_72805_g(x, y, z);
/* 58 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 59 */     renderer.func_147775_a(block);
/* 60 */     renderer.func_147784_q(block, x, y, z);
/*    */     
/* 62 */     Tessellator t = Tessellator.field_78398_a;
/* 63 */     t.func_78378_d(thaumcraft.common.blocks.BlockCustomOreItem.colors[4]);
/* 64 */     TileEntity te = world.func_147438_o(x, y, z);
/* 65 */     if ((te != null) && ((te instanceof TileLifter)) && (!((TileLifter)te).gettingPower())) { bb = 180;
/*    */     }
/* 67 */     t.func_78380_c(bb);
/*    */     
/* 69 */     if (block.func_149646_a(world, x, y + 1, z, 6)) renderer.func_147806_b(block, x, y - 0.01F, z, ((BlockLifter)block).iconGlow);
/* 70 */     t.func_78378_d(14488063);
/* 71 */     if (block.func_149646_a(world, x + 1, y, z, 6)) renderer.func_147764_f(block, x - 0.01F, y, z, ((BlockLifter)block).iconGlow);
/* 72 */     if (block.func_149646_a(world, x - 1, y, z, 6)) renderer.func_147798_e(block, x + 0.01F, y, z, ((BlockLifter)block).iconGlow);
/* 73 */     if (block.func_149646_a(world, x, y, z + 1, 6)) renderer.func_147734_d(block, x, y, z - 0.01F, ((BlockLifter)block).iconGlow);
/* 74 */     if (block.func_149646_a(world, x, y, z - 1, 6)) { renderer.func_147761_c(block, x, y, z + 0.01F, ((BlockLifter)block).iconGlow);
/*    */     }
/* 76 */     renderer.func_147771_a();
/* 77 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 78 */     renderer.func_147775_a(block);
/* 79 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 84 */     return true;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 89 */     return ConfigBlocks.blockLifterRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockLifterRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */