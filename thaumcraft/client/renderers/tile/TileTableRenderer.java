/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelTable;
/*    */ import thaumcraft.common.tiles.TileTable;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileTableRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 18 */   private ModelTable tableModel = new ModelTable();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileTable table, double par2, double par4, double par6, float par8)
/*    */   {
/* 26 */     int md = 0;
/* 27 */     if (table.func_145831_w() != null) {
/* 28 */       md = table.func_145832_p();
/*    */     }
/* 30 */     if (md >= 6) return;
/* 31 */     GL11.glPushMatrix();
/* 32 */     UtilsFX.bindTexture("textures/models/table.png");
/* 33 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.0F, (float)par6 + 0.5F);
/* 34 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 35 */     if (md == 1) {
/* 36 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*    */     }
/* 38 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 39 */     this.tableModel.renderAll();
/*    */     
/* 41 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 47 */     renderTileEntityAt((TileTable)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileTableRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */