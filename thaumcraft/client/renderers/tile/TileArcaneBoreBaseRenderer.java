/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelBoreBase;
/*    */ import thaumcraft.common.tiles.TileArcaneBoreBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileArcaneBoreBaseRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private ModelBoreBase model;
/*    */   
/*    */   public TileArcaneBoreBaseRenderer()
/*    */   {
/* 23 */     this.model = new ModelBoreBase();
/*    */   }
/*    */   
/*    */ 
/*    */   public void renderEntityAt(TileArcaneBoreBase bore, double x, double y, double z, float fq)
/*    */   {
/* 29 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*    */     
/* 31 */     UtilsFX.bindTexture("textures/models/Bore.png");
/*    */     
/* 33 */     GL11.glPushMatrix();
/*    */     
/* 35 */     GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
/*    */     
/* 37 */     GL11.glPushMatrix();
/* 38 */     this.model.render();
/* 39 */     GL11.glPopMatrix();
/*    */     
/* 41 */     GL11.glPushMatrix();
/* 42 */     switch (bore.orientation.ordinal()) {
/* 43 */     case 2:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 44 */     case 3:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 45 */     case 4:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/* 46 */     case 5:  GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/*    */     }
/* 48 */     this.model.renderNozzle();
/* 49 */     GL11.glPopMatrix();
/*    */     
/*    */ 
/* 52 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f)
/*    */   {
/* 59 */     renderEntityAt((TileArcaneBoreBase)tileentity, d, d1, d2, f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileArcaneBoreBaseRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */