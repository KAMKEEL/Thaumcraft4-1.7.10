/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelBanner;
/*    */ import thaumcraft.common.tiles.TileBanner;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileBannerRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 22 */   private ModelBanner model = new ModelBanner();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileBanner banner, double par2, double par4, double par6, float par8)
/*    */   {
/* 30 */     boolean flag = banner.func_145831_w() != null;
/* 31 */     long k = flag ? banner.func_145831_w().func_82737_E() : 0L;
/*    */     
/* 33 */     GL11.glPushMatrix();
/* 34 */     if ((banner.getAspect() == null) && (banner.getColor() == -1)) {
/* 35 */       UtilsFX.bindTexture("textures/models/banner_cultist.png");
/*    */     } else {
/* 37 */       UtilsFX.bindTexture("textures/models/banner_blank.png");
/*    */     }
/* 39 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.5F, (float)par6 + 0.5F);
/* 40 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 41 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 43 */     if (banner.func_145831_w() != null) {
/* 44 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 45 */       float f2 = banner.getFacing() * 360 / 16.0F;
/* 46 */       GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 49 */     if (!banner.getWall()) {
/* 50 */       this.model.renderPole();
/*    */     } else {
/* 52 */       GL11.glTranslated(0.0D, 0.0D, -0.4125D);
/*    */     }
/*    */     
/* 55 */     this.model.renderBeam();
/*    */     
/* 57 */     if (banner.getColor() != -1) {
/* 58 */       Color c = new Color(thaumcraft.common.lib.utils.Utils.colors[banner.getColor()]);
/* 59 */       GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/*    */     }
/*    */     
/* 62 */     this.model.renderTabs();
/*    */     
/* 64 */     float f3 = banner.field_145851_c * 7 + banner.field_145848_d * 9 + banner.field_145849_e * 13 + (float)k + par8;
/* 65 */     float rx = (0.005F + 0.005F * MathHelper.func_76134_b(f3 * 3.1415927F * 0.02F)) * 3.1415927F;
/* 66 */     this.model.Banner.field_78795_f = rx;
/* 67 */     this.model.renderBanner();
/*    */     
/* 69 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 71 */     if (banner.getAspect() != null) {
/* 72 */       GL11.glPushMatrix();
/* 73 */       GL11.glTranslatef(0.0F, 0.0F, 0.05001F);
/* 74 */       GL11.glScaled(0.0375D, 0.0375D, 0.0375D);
/* 75 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 76 */       GL11.glRotatef(-rx * 57.295776F * 2.0F, 1.0F, 0.0F, 0.0F);
/*    */       
/* 78 */       UtilsFX.drawTag(-8, 4, banner.getAspect(), 0.0F, 0, 0.0D, 771, 0.75F, false);
/* 79 */       GL11.glPopMatrix();
/*    */     }
/*    */     
/*    */ 
/* 83 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 89 */     renderTileEntityAt((TileBanner)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileBannerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */