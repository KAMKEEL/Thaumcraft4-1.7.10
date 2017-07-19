/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import java.awt.Color;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.blocks.BlockCandle;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCandleRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*    */   {
/* 22 */     Color c = new Color(thaumcraft.common.lib.utils.Utils.colors[metadata]);
/* 23 */     float r = c.getRed() / 255.0F;
/* 24 */     float g = c.getGreen() / 255.0F;
/* 25 */     float b = c.getBlue() / 255.0F;
/* 26 */     GL11.glColor3f(r, g, b);
/* 27 */     block.func_149676_a(W6, 0.0F, W6, W10, 0.5F, W10);
/* 28 */     renderer.func_147775_a(block);
/* 29 */     drawFaces(renderer, block, ((BlockCandle)block).icon, true);
/* 30 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*    */     
/* 32 */     block.func_149676_a(0.475F, 0.5F, 0.475F, 0.525F, W10, 0.525F);
/* 33 */     renderer.func_147775_a(block);
/* 34 */     drawFaces(renderer, block, ((BlockCandle)block).iconStub, true);
/*    */   }
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 39 */     int type = 0;
/*    */     
/* 41 */     block.func_149676_a(W6, 0.0F, W6, W10, 0.5F, W10);
/* 42 */     renderer.func_147775_a(block);
/* 43 */     renderer.func_147784_q(block, x, y, z);
/*    */     
/* 45 */     Random rr = new Random(x + y * z);
/* 46 */     int q = 1 + rr.nextInt(5);
/* 47 */     for (int a = 0; a < q; a++) {
/* 48 */       boolean side = rr.nextBoolean();
/* 49 */       int loc = 2 + rr.nextInt(2);
/* 50 */       if (a % 2 == 0) {
/* 51 */         block.func_149676_a(W5 + W1 * loc, 0.0F, side ? W5 : W10, W6 + W1 * loc, W1 * (1 + rr.nextInt(3)), side ? W6 : W11);
/*    */         
/*    */ 
/* 54 */         renderer.func_147775_a(block);
/* 55 */         renderer.func_147784_q(block, x, y, z);
/*    */       } else {
/* 57 */         block.func_149676_a(side ? W5 : W10, 0.0F, W5 + W1 * loc, side ? W6 : W11, W1 * (1 + rr.nextInt(3)), W6 + W1 * loc);
/*    */         
/*    */ 
/* 60 */         renderer.func_147775_a(block);
/* 61 */         renderer.func_147784_q(block, x, y, z);
/*    */       }
/*    */     }
/*    */     
/* 65 */     renderer.field_147840_d = ((BlockCandle)block).iconStub;
/* 66 */     block.func_149676_a(0.475F, 0.5F, 0.475F, 0.525F, W10, 0.525F);
/* 67 */     renderer.func_147775_a(block);
/* 68 */     renderer.func_147736_d(block, x, y, z, 1.0F, 1.0F, 1.0F);
/*    */     
/* 70 */     renderer.func_147771_a();
/* 71 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 72 */     renderer.func_147775_a(block);
/* 73 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 78 */     return true;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 83 */     return ConfigBlocks.blockCandleRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockCandleRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */