/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelTubeValve;
/*    */ import thaumcraft.common.tiles.TileTubeOneway;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileTubeOnewayRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private ModelTubeValve model;
/*    */   
/*    */   public TileTubeOnewayRenderer()
/*    */   {
/* 23 */     this.model = new ModelTubeValve();
/*    */   }
/*    */   
/* 26 */   ForgeDirection fd = null;
/*    */   
/*    */ 
/*    */   public void renderEntityAt(TileTubeOneway valve, double x, double y, double z, float fq)
/*    */   {
/* 31 */     UtilsFX.bindTexture("textures/models/valve.png");
/* 32 */     if ((valve.func_145831_w() != null) && (ThaumcraftApiHelper.getConnectableTile(valve.func_145831_w(), valve.field_145851_c, valve.field_145848_d, valve.field_145849_e, valve.facing.getOpposite()) == null)) {
/* 33 */       return;
/*    */     }
/*    */     
/* 36 */     GL11.glPushMatrix();
/* 37 */     this.fd = valve.facing;
/* 38 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*    */     
/* 40 */     if (this.fd.offsetY == 0) {
/* 41 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*    */     } else {
/* 43 */       GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 44 */       GL11.glRotatef(90.0F, this.fd.offsetY, 0.0F, 0.0F);
/*    */     }
/* 46 */     GL11.glRotatef(90.0F, this.fd.offsetX, this.fd.offsetY, this.fd.offsetZ);
/*    */     
/*    */ 
/* 49 */     GL11.glPushMatrix();
/* 50 */     GL11.glColor3f(0.45F, 0.5F, 1.0F);
/* 51 */     GL11.glScaled(1.1D, 0.5D, 1.1D);
/* 52 */     GL11.glTranslated(0.0D, -0.5D, 0.0D);
/* 53 */     this.model.render();
/* 54 */     GL11.glTranslated(0.0D, -0.25D, 0.0D);
/* 55 */     this.model.render();
/* 56 */     GL11.glTranslated(0.0D, -0.25D, 0.0D);
/* 57 */     this.model.render();
/* 58 */     GL11.glPopMatrix();
/*    */     
/* 60 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f)
/*    */   {
/* 67 */     renderEntityAt((TileTubeOneway)tileentity, d, d1, d2, f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileTubeOnewayRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */