/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*    */ import net.minecraftforge.client.model.IModelCustom;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.tiles.TileFluxScrubber;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileFluxScrubberRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private IModelCustom model;
/* 25 */   private static final ResourceLocation CAP = new ResourceLocation("thaumcraft", "textures/models/obelisk_cap.obj");
/*    */   
/*    */   public TileFluxScrubberRenderer()
/*    */   {
/* 29 */     this.model = AdvancedModelLoader.loadModel(CAP);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_147500_a(TileEntity te, double x, double y, double z, float f)
/*    */   {
/* 35 */     GL11.glPushMatrix();
/* 36 */     int facing = ((TileFluxScrubber)te).facing.ordinal();
/* 37 */     translateFromOrientation(x, y, z, facing);
/* 38 */     UtilsFX.bindTexture("textures/models/fluxscrubber.png");
/* 39 */     this.model.renderPart("Cap");
/* 40 */     float q = Minecraft.func_71410_x().field_71451_h.field_70173_aa + f + ((TileFluxScrubber)te).count;
/* 41 */     float bob = MathHelper.func_76126_a(q / 8.0F) * 0.075F + 0.075F;
/* 42 */     GL11.glTranslated(0.0D, 0.0D, -bob);
/* 43 */     this.model.renderPart("Tip");
/* 44 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   private void translateFromOrientation(double x, double y, double z, int orientation) {
/* 48 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/* 49 */     if (orientation == 0) {
/* 50 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 51 */     } else if (orientation == 1) {
/* 52 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 53 */     } else if (orientation != 2) {
/* 54 */       if (orientation == 3) {
/* 55 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 56 */       } else if (orientation == 4) {
/* 57 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 58 */       } else if (orientation == 5)
/* 59 */         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*    */     }
/* 61 */     GL11.glTranslated(0.0D, 0.0D, -0.5D);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileFluxScrubberRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */