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
/*    */ import thaumcraft.common.tiles.TileInfusionPillar;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileInfusionPillarRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private IModelCustom model;
/* 21 */   private static final ResourceLocation PILLAR = new ResourceLocation("thaumcraft", "textures/models/pillar.obj");
/*    */   
/* 23 */   public TileInfusionPillarRenderer() { this.model = AdvancedModelLoader.loadModel(PILLAR); }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileInfusionPillar tile, double par2, double par4, double par6, float par8)
/*    */   {
/* 33 */     GL11.glPushMatrix();
/* 34 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4, (float)par6 + 0.5F);
/* 35 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 36 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 38 */     UtilsFX.bindTexture("textures/models/pillar.png");
/*    */     
/* 40 */     if (tile.orientation == 3) {
/* 41 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*    */     }
/* 43 */     else if (tile.orientation == 4) {
/* 44 */       GL11.glRotatef(270.0F, 0.0F, 0.0F, 1.0F);
/*    */     }
/* 46 */     else if (tile.orientation == 5) {
/* 47 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     }
/*    */     
/*    */ 
/* 51 */     this.model.renderAll();
/*    */     
/*    */ 
/* 54 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 60 */     renderTileEntityAt((TileInfusionPillar)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileInfusionPillarRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */