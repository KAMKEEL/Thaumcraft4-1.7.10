/*    */ package thaumcraft.client.renderers.block;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCustomOreRenderer
/*    */   extends BlockRenderer
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*    */   {
/* 24 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 25 */     renderer.func_147775_a(block);
/*    */     
/* 27 */     if (metadata == 0) {
/* 28 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockCustomOre)block).icon[0], false);
/*    */     }
/* 30 */     else if (metadata == 7) {
/* 31 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockCustomOre)block).icon[3], false);
/* 32 */     } else if (metadata < 7) {
/* 33 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockCustomOre)block).icon[1], false);
/* 34 */       Color c = new Color(thaumcraft.common.blocks.BlockCustomOreItem.colors[metadata]);
/* 35 */       float r = c.getRed() / 255.0F;
/* 36 */       float g = c.getGreen() / 255.0F;
/* 37 */       float b = c.getBlue() / 255.0F;
/* 38 */       GL11.glColor3f(r, g, b);
/* 39 */       block.func_149676_a(0.005F, 0.005F, 0.005F, 0.995F, 0.995F, 0.995F);
/* 40 */       renderer.func_147775_a(block);
/* 41 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockCustomOre)block).icon[2], false);
/* 42 */       GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*    */   {
/* 50 */     int bb = setBrightness(world, x, y, z, block);
/* 51 */     int metadata = world.func_72805_g(x, y, z);
/* 52 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 53 */     renderer.func_147775_a(block);
/* 54 */     renderer.func_147784_q(block, x, y, z);
/*    */     
/* 56 */     if ((metadata != 0) && (metadata != 7) && (metadata < 7)) {
/* 57 */       Tessellator t = Tessellator.field_78398_a;
/* 58 */       t.func_78378_d(thaumcraft.common.blocks.BlockCustomOreItem.colors[metadata]);
/* 59 */       t.func_78380_c(Math.max(bb, 160));
/* 60 */       renderAllSides(world, x, y, z, block, renderer, ((thaumcraft.common.blocks.BlockCustomOre)block).icon[2], false);
/*    */       
/* 62 */       if (Minecraft.func_71410_x().field_71474_y.field_151443_J > 1) {
/* 63 */         block.func_149676_a(0.005F, 0.005F, 0.005F, 0.995F, 0.995F, 0.995F);
/* 64 */         renderer.func_147775_a(block);
/* 65 */         t.func_78380_c(bb);
/* 66 */         renderAllSides(world, x, y, z, block, renderer, Blocks.field_150348_b.func_149691_a(0, 0), false);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 71 */     renderer.func_147771_a();
/* 72 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 73 */     renderer.func_147775_a(block);
/* 74 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId)
/*    */   {
/* 79 */     return true;
/*    */   }
/*    */   
/*    */   public int getRenderId()
/*    */   {
/* 84 */     return ConfigBlocks.blockCustomOreRI;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockCustomOreRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */