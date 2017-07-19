/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraftforge.fluids.FluidTank;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.tiles.TileCrucible;
/*    */ 
/*    */ public class TileCrucibleRenderer extends TileEntitySpecialRenderer
/*    */ {
/*    */   public void renderEntityAt(TileCrucible cr, double x, double y, double z, float fq)
/*    */   {
/* 18 */     if (cr.tank.getFluidAmount() > 0) renderFluid(cr, x, y, z);
/*    */   }
/*    */   
/*    */   public void renderFluid(TileCrucible cr, double x, double y, double z) {
/* 22 */     IIcon icon = Blocks.field_150355_j.func_149691_a(0, 0);
/*    */     
/* 24 */     GL11.glPushMatrix();
/* 25 */     GL11.glTranslated(x, y + cr.getFluidHeight(), z + 1.0D);
/* 26 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 27 */     if (cr.tank.getFluidAmount() > 0)
/*    */     {
/* 29 */       cr.getClass();float recolor = cr.tagAmount() / 100.0F;
/* 30 */       if (recolor > 0.0F) recolor = 0.5F + recolor / 2.0F;
/* 31 */       UtilsFX.renderQuadFromIcon(true, icon, 1.0F, 1.0F - recolor / 3.0F, 1.0F - recolor, 1.0F - recolor / 2.0F, ConfigBlocks.blockMetalDevice.func_149677_c(cr.func_145831_w(), cr.field_145851_c, cr.field_145848_d, cr.field_145849_e), 771, 1.0F);
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 37 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_147500_a(TileEntity te, double d, double d1, double d2, float f)
/*    */   {
/* 43 */     renderEntityAt((TileCrucible)te, d, d1, d2, f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileCrucibleRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */