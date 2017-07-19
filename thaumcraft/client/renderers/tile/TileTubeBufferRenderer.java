/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelTubeValve;
/*    */ import thaumcraft.common.tiles.TileTubeBuffer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileTubeBufferRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private ModelTubeValve model;
/*    */   
/*    */   public TileTubeBufferRenderer()
/*    */   {
/* 23 */     this.model = new ModelTubeValve();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void renderEntityAt(TileTubeBuffer buffer, double x, double y, double z, float fq)
/*    */   {
/* 30 */     UtilsFX.bindTexture("textures/models/valve.png");
/*    */     
/* 32 */     if (buffer.func_145831_w() != null) {
/* 33 */       for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/* 34 */         if ((buffer.chokedSides[dir.ordinal()] != 0) && (buffer.openSides[dir.ordinal()] != 0) && (ThaumcraftApiHelper.getConnectableTile(buffer.func_145831_w(), buffer.field_145851_c, buffer.field_145848_d, buffer.field_145849_e, dir) != null))
/*    */         {
/*    */ 
/*    */ 
/*    */ 
/* 39 */           GL11.glPushMatrix();
/* 40 */           GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*    */           
/* 42 */           if (dir.getOpposite().offsetY == 0) {
/* 43 */             GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*    */           } else {
/* 45 */             GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 46 */             GL11.glRotatef(90.0F, dir.getOpposite().offsetY, 0.0F, 0.0F);
/*    */           }
/* 48 */           GL11.glRotatef(90.0F, dir.getOpposite().offsetX, dir.getOpposite().offsetY, dir.getOpposite().offsetZ);
/*    */           
/* 50 */           GL11.glPushMatrix();
/* 51 */           if (buffer.chokedSides[dir.ordinal()] == 2) {
/* 52 */             GL11.glColor3f(1.0F, 0.3F, 0.3F);
/*    */           } else {
/* 54 */             GL11.glColor3f(0.3F, 0.3F, 1.0F);
/*    */           }
/*    */           
/* 57 */           GL11.glScaled(1.2D, 1.0D, 1.2D);
/*    */           
/* 59 */           GL11.glTranslated(0.0D, -0.5D, 0.0D);
/* 60 */           this.model.render();
/* 61 */           GL11.glPopMatrix();
/*    */           
/* 63 */           GL11.glPopMatrix();
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f)
/*    */   {
/* 71 */     renderEntityAt((TileTubeBuffer)tileentity, d, d1, d2, f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileTubeBufferRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */