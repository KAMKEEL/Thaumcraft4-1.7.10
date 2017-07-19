/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockFire;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockArcaneFurnaceRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 22 */     setBrightness(world, x, y, z, block);
/* 23 */     int md = world.func_72805_g(x, y, z);
/* 24 */     if (md <= 9) {
/* 25 */       if (md == 0) {
/* 26 */         setBrightness(world, x, y, z, block);
/* 27 */         renderer.field_147840_d = Blocks.field_150353_l.func_149733_h(0);
/*    */       }
/* 29 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 30 */       renderer.func_147775_a(block);
/* 31 */       renderer.func_147784_q(block, x, y, z);
/* 32 */     } else if (md == 10)
/*    */     {
/* 34 */       if ((world.func_147439_a(x - 1, y, z) == block) && (world.func_72805_g(x - 1, y, z) == 0))
/*    */       {
/*    */ 
/* 37 */         renderer.func_147764_f(block, x - W10, y, z, ((thaumcraft.common.blocks.BlockArcaneFurnace)block).icon[13]);
/* 38 */         renderer.func_147764_f(block, x - 0.8F, y, z, ((thaumcraft.common.blocks.BlockArcaneFurnace)block).icon[15]);
/* 39 */         setBrightness(world, x, y, z, block);
/* 40 */         block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.5F, 1.0F);
/* 41 */         renderer.func_147775_a(block);
/* 42 */         renderer.func_147764_f(block, x - 0.9F, y, z, Blocks.field_150480_ab.func_149733_h(0));
/*    */       }
/* 44 */       else if ((world.func_147439_a(x + 1, y, z) == block) && (world.func_72805_g(x + 1, y, z) == 0))
/*    */       {
/*    */ 
/* 47 */         renderer.func_147798_e(block, x + W10, y, z, ((thaumcraft.common.blocks.BlockArcaneFurnace)block).icon[13]);
/* 48 */         renderer.func_147798_e(block, x + 0.8F, y, z, ((thaumcraft.common.blocks.BlockArcaneFurnace)block).icon[15]);
/* 49 */         setBrightness(world, x, y, z, block);
/* 50 */         block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.5F, 1.0F);
/* 51 */         renderer.func_147775_a(block);
/* 52 */         renderer.func_147798_e(block, x + 0.9F, y, z, Blocks.field_150480_ab.func_149733_h(0));
/*    */       }
/* 54 */       else if ((world.func_147439_a(x, y, z - 1) == block) && (world.func_72805_g(x, y, z - 1) == 0))
/*    */       {
/*    */ 
/* 57 */         renderer.func_147734_d(block, x, y, z - W10, ((thaumcraft.common.blocks.BlockArcaneFurnace)block).icon[13]);
/* 58 */         renderer.func_147734_d(block, x, y, z - 0.8F, ((thaumcraft.common.blocks.BlockArcaneFurnace)block).icon[15]);
/* 59 */         setBrightness(world, x, y, z, block);
/* 60 */         block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.5F, 1.0F);
/* 61 */         renderer.func_147775_a(block);
/* 62 */         renderer.func_147734_d(block, x, y, z - 0.9F, Blocks.field_150480_ab.func_149733_h(0));
/*    */       }
/*    */       else {
/* 65 */         renderer.func_147761_c(block, x, y, z + W10, ((thaumcraft.common.blocks.BlockArcaneFurnace)block).icon[13]);
/* 66 */         renderer.func_147761_c(block, x, y, z + 0.8F, ((thaumcraft.common.blocks.BlockArcaneFurnace)block).icon[15]);
/* 67 */         setBrightness(world, x, y, z, block);
/* 68 */         block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.5F, 1.0F);
/* 69 */         renderer.func_147775_a(block);
/* 70 */         renderer.func_147761_c(block, x, y, z + 0.9F, Blocks.field_150480_ab.func_149733_h(0));
/*    */       }
/*    */     }
/*    */     
/* 74 */     renderer.func_147771_a();
/* 75 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 76 */     renderer.func_147775_a(block);
/* 77 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 82 */     return false;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 87 */     return ConfigBlocks.blockArcaneFurnaceRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockArcaneFurnaceRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */