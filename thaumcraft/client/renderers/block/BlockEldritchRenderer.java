/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class BlockEldritchRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*    */   {
/* 13 */     if ((metadata == 4) || (metadata == 5)) {
/* 14 */       block.func_149676_a(W2, W2, W2, W14, W14, W14);
/* 15 */       renderer.func_147775_a(block);
/* 16 */       drawFaces(renderer, block, block.func_149691_a(0, metadata), false);
/*    */     }
/* 18 */     if (metadata == 6) {
/* 19 */       block.func_149676_a(W2, W2, W2, W14, W14, W14);
/* 20 */       renderer.func_147775_a(block);
/* 21 */       drawFaces(renderer, block, block.func_149691_a(0, metadata), false);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 27 */     int metadata = world.func_72805_g(x, y, z);
/*    */     
/* 29 */     if ((metadata == 4) || (metadata == 5) || (metadata == 6)) {
/* 30 */       renderer.field_152631_f = true;
/* 31 */       setBrightness(world, x, y, z, block);
/* 32 */       float s1 = 0.0F;float s2 = 0.0F;float s3 = 0.0F;
/* 33 */       float s4 = 1.0F;float s5 = 1.0F;float s6 = 1.0F;
/* 34 */       if (!block.func_149747_d(world, x + 1, y, z, 4)) s4 -= W2;
/* 35 */       if (!block.func_149747_d(world, x - 1, y, z, 5)) s1 += W2;
/* 36 */       if (!block.func_149747_d(world, x, y, z + 1, 2)) s6 -= W2;
/* 37 */       if (!block.func_149747_d(world, x, y, z - 1, 3)) s3 += W2;
/* 38 */       if (!block.func_149747_d(world, x, y + 1, z, 0)) s5 -= W2;
/* 39 */       if (!block.func_149747_d(world, x, y - 1, z, 1)) { s2 += W2;
/*    */       }
/* 41 */       block.func_149676_a(s1, s2, s3, s4, s5, s6);
/* 42 */       renderer.func_147775_a(block);
/* 43 */       renderAllSides(world, x, y, z, block, renderer, false);
/*    */       
/* 45 */       renderer.field_152631_f = false;
/*    */       
/* 47 */       renderer.func_147771_a();
/* 48 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 49 */       renderer.func_147775_a(block);
/*    */     }
/* 51 */     if ((metadata == 7) || (metadata == 8) || (metadata == 9) || (metadata == 10)) {
/* 52 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 53 */       renderer.func_147775_a(block);
/* 54 */       renderer.func_147784_q(block, x, y, z);
/* 55 */       renderer.func_147771_a();
/* 56 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 57 */       renderer.func_147775_a(block);
/*    */     }
/* 59 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 64 */     return true;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 69 */     return ConfigBlocks.blockEldritchRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockEldritchRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */