/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*    */ import net.minecraftforge.client.model.IModelCustom;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.tiles.TileCrystal;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileEldritchCrystalRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private IModelCustom model;
/* 25 */   private static final ResourceLocation CRYSTAL = new ResourceLocation("thaumcraft", "textures/models/vcrystal.obj");
/*    */   
/* 27 */   public TileEldritchCrystalRenderer() { this.model = AdvancedModelLoader.loadModel(CRYSTAL); }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileCrystal tile, double par2, double par4, double par6, float par8)
/*    */   {
/* 35 */     GL11.glPushMatrix();
/* 36 */     translateFromOrientation(par2, par4, par6, tile.orientation, (tile.func_145831_w() == null ? 0 : tile.hashCode()) % 4);
/* 37 */     EntityPlayer p = Minecraft.func_71410_x().field_71439_g;
/*    */     
/* 39 */     UtilsFX.bindTexture("textures/blocks/crust.png");
/* 40 */     this.model.renderPart("Base");
/*    */     
/* 42 */     float shade = MathHelper.func_76126_a(p.field_70173_aa / 6.0F) * 0.075F + 0.925F;
/* 43 */     int var19 = (int)(210.0F * shade);
/* 44 */     int var20 = var19 % 65536;
/* 45 */     int var21 = var19 / 65536;
/* 46 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, var20 / 1.0F, var21 / 1.0F);
/* 47 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.7F);
/* 48 */     UtilsFX.bindTexture("textures/models/vcrystal.png");
/* 49 */     this.model.renderPart("Crystal");
/*    */     
/* 51 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 52 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   private void translateFromOrientation(double x, double y, double z, int orientation, int r) {
/* 56 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/* 57 */     if (orientation == 0) {
/* 58 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 59 */     } else if (orientation == 1) {
/* 60 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 61 */     } else if (orientation == 2) {
/* 62 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 63 */     } else if (orientation != 3) {
/* 64 */       if (orientation == 4) {
/* 65 */         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/* 66 */       } else if (orientation == 5)
/* 67 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*    */     }
/* 69 */     GL11.glTranslated(0.0D, 0.0D, -0.5D);
/* 70 */     GL11.glRotatef(90.0F * r, 0.0F, 0.0F, 1.0F);
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 75 */     renderTileEntityAt((TileCrystal)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileEldritchCrystalRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */