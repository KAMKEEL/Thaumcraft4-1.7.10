/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*    */ import net.minecraftforge.client.model.IModelCustom;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.tiles.TileEldritchCrabSpawner;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileEldritchCrabSpawnerRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private IModelCustom model;
/* 22 */   private static final ResourceLocation VENT = new ResourceLocation("thaumcraft", "textures/models/crabvent.obj");
/*    */   
/* 24 */   public TileEldritchCrabSpawnerRenderer() { this.model = AdvancedModelLoader.loadModel(VENT); }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileEldritchCrabSpawner tile, double par2, double par4, double par6, float par8)
/*    */   {
/* 32 */     GL11.glPushMatrix();
/* 33 */     translateFromOrientation(par2, par4, par6, tile.getFacing());
/* 34 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 35 */     UtilsFX.bindTexture("textures/models/crabvent.png");
/* 36 */     this.model.renderAll();
/* 37 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   private void translateFromOrientation(double x, double y, double z, int orientation) {
/* 41 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/* 42 */     if (orientation == 0) {
/* 43 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 44 */     } else if (orientation == 1) {
/* 45 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 46 */     } else if (orientation == 2) {
/* 47 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 48 */     } else if (orientation != 3) {
/* 49 */       if (orientation == 4) {
/* 50 */         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/* 51 */       } else if (orientation == 5) {
/* 52 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 58 */     renderTileEntityAt((TileEldritchCrabSpawner)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileEldritchCrabSpawnerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */