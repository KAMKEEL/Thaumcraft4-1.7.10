/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.tiles.TileNodeEnergized;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileNodeEnergizedRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 19 */   static String tx1 = "textures/items/lightningringv.png";
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_147500_a(TileEntity tile, double x, double y, double z, float partialTicks)
/*    */   {
/* 25 */     EntityLivingBase viewer = Minecraft.func_71410_x().field_71451_h;
/*    */     
/*    */ 
/* 28 */     TileNodeRenderer.renderNode(viewer, 64.0D, true, false, 1.0F, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, partialTicks, ((TileNodeEnergized)tile).getAuraBase(), ((TileNodeEnergized)tile).getNodeType(), ((TileNodeEnergized)tile).getNodeModifier());
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 34 */     GL11.glPushMatrix();
/* 35 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 36 */     GL11.glEnable(3042);
/* 37 */     GL11.glBlendFunc(770, 1);
/*    */     
/* 39 */     long nt = System.nanoTime();
/* 40 */     UtilsFX.bindTexture(tx1);
/* 41 */     int frames = UtilsFX.getTextureAnimationSize(tx1);
/* 42 */     int i = (int)((nt / 40000000L + x) % frames);
/* 43 */     UtilsFX.renderFacingQuad(tile.field_145851_c + 0.5D, tile.field_145848_d + 0.5D, tile.field_145849_e + 0.5D, 0.0F, 0.33F, 0.9F, frames, i, partialTicks, 16777215);
/*    */     
/*    */ 
/* 46 */     GL11.glDisable(3042);
/* 47 */     GL11.glAlphaFunc(516, 0.1F);
/* 48 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileNodeEnergizedRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */