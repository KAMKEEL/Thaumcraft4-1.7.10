/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelCentrifuge;
/*    */ import thaumcraft.common.tiles.TileCentrifuge;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileCentrifugeRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private ModelCentrifuge model;
/*    */   
/*    */   public TileCentrifugeRenderer()
/*    */   {
/* 23 */     this.model = new ModelCentrifuge();
/*    */   }
/*    */   
/*    */ 
/*    */   public void renderEntityAt(TileCentrifuge cf, double x, double y, double z, float fq)
/*    */   {
/* 29 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*    */     
/* 31 */     UtilsFX.bindTexture("textures/models/centrifuge.png");
/*    */     
/* 33 */     GL11.glPushMatrix();
/*    */     
/* 35 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/* 36 */     this.model.renderBoxes();
/* 37 */     GL11.glRotated(cf.rotation, 0.0D, 1.0D, 0.0D);
/* 38 */     this.model.renderSpinnyBit();
/*    */     
/* 40 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f)
/*    */   {
/* 48 */     renderEntityAt((TileCentrifuge)tileentity, d, d1, d2, f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileCentrifugeRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */