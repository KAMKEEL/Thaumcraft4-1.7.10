/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.common.blocks.BlockCosmeticOpaque;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class BlockCosmeticOpaqueRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*    */   {
/* 15 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 16 */     renderer.func_147775_a(block);
/* 17 */     drawFaces(renderer, block, block.func_149691_a(0, metadata), false);
/*    */   }
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 22 */     if ((block == null) || (!(block instanceof BlockCosmeticOpaque))) return false;
/* 23 */     int bb = setBrightness(world, x, y, z, block);
/* 24 */     int metadata = world.func_72805_g(x, y, z);
/*    */     
/* 26 */     if (((BlockCosmeticOpaque)block).currentPass == 1)
/*    */     {
/* 28 */       if (metadata <= 1) {
/* 29 */         block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 30 */         renderer.func_147775_a(block);
/* 31 */         renderer.func_147784_q(block, x, y, z);
/*    */       }
/* 33 */       else if (metadata == 2) {
/* 34 */         block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 35 */         renderer.func_147775_a(block);
/*    */         
/* 37 */         for (int d = 0; d < 6; d++) {
/* 38 */           ForgeDirection dir1 = ForgeDirection.getOrientation(d);
/* 39 */           if (block.func_149646_a(world, x + dir1.offsetX, y + dir1.offsetY, z + dir1.offsetZ, d)) {
/* 40 */             switch (d) {
/* 41 */             case 0:  renderer.func_147768_a(block, x, y, z, block.func_149673_e(world, x, y, z, d)); break;
/* 42 */             case 1:  renderer.func_147806_b(block, x, y, z, block.func_149673_e(world, x, y, z, d)); break;
/* 43 */             case 2:  renderer.func_147761_c(block, x, y, z, block.func_149673_e(world, x, y, z, d)); break;
/* 44 */             case 3:  renderer.func_147734_d(block, x, y, z, block.func_149673_e(world, x, y, z, d)); break;
/* 45 */             case 4:  renderer.func_147798_e(block, x, y, z, block.func_149673_e(world, x, y, z, d)); break;
/* 46 */             case 5:  renderer.func_147764_f(block, x, y, z, block.func_149673_e(world, x, y, z, d));
/*    */             }
/* 48 */             renderer.field_147842_e = false;
/*    */           }
/*    */         }
/*    */       }
/* 52 */       renderer.func_147771_a();
/* 53 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 54 */       renderer.func_147775_a(block);
/* 55 */       return true;
/*    */     }
/*    */     
/* 58 */     renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 59 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 67 */     return true;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 72 */     return ConfigBlocks.blockCosmeticOpaqueRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockCosmeticOpaqueRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */