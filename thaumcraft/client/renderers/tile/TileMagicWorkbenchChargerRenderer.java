/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*    */ import net.minecraftforge.client.model.IModelCustom;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.visnet.VisNetHandler;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.tiles.TileMagicWorkbenchCharger;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileMagicWorkbenchChargerRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private IModelCustom model;
/* 27 */   private static final ResourceLocation RELAY = new ResourceLocation("thaumcraft", "textures/models/vis_relay.obj");
/*    */   
/* 29 */   public TileMagicWorkbenchChargerRenderer() { this.model = AdvancedModelLoader.loadModel(RELAY); }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileMagicWorkbenchCharger tile, double par2, double par4, double par6, float par8)
/*    */   {
/* 38 */     int facing = 1;
/* 39 */     if (tile.func_145831_w() != null) {
/* 40 */       facing = tile.orientation;
/*    */     }
/*    */     
/* 43 */     int ticks = Minecraft.func_71410_x().field_71451_h.field_70173_aa;
/*    */     
/* 45 */     GL11.glPushMatrix();
/* 46 */     GL11.glTranslated(par2 + 0.5D, par4 + 0.5D, par6 + 0.5D);
/* 47 */     GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 48 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 49 */     GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
/* 50 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 51 */     GL11.glPushMatrix();
/* 52 */     UtilsFX.bindTexture("textures/models/vis_relay.png");
/*    */     
/* 54 */     this.model.renderPart("RingFloat");
/*    */     
/* 56 */     GL11.glPushMatrix();
/* 57 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 58 */     GL11.glTranslated(0.0D, 0.0D, 0.5D);
/* 59 */     for (int a = 0; a < 4; a++) {
/* 60 */       this.model.renderPart("Support");
/* 61 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*    */     }
/* 63 */     GL11.glPopMatrix();
/*    */     
/* 65 */     GL11.glEnable(3042);
/* 66 */     GL11.glBlendFunc(770, 771);
/* 67 */     if (tile.color >= 0) {
/* 68 */       Color c = new Color(TileMagicWorkbenchCharger.colors[tile.color]);
/* 69 */       GL11.glColor3f(c.getRed() / 200.0F, c.getGreen() / 200.0F, c.getBlue() / 200.0F);
/*    */     }
/* 71 */     float scale = MathHelper.func_76126_a((ticks + par8) / 2.0F) * 0.05F + 0.95F;
/* 72 */     int j = (VisNetHandler.isNodeValid(tile.getParent()) ? 50 : 0) + (int)(150.0F * scale);
/* 73 */     int k = j % 65536;
/* 74 */     int l = j / 65536;
/* 75 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/* 76 */     this.model.renderPart("Crystal");
/*    */     
/* 78 */     GL11.glDisable(3042);
/* 79 */     GL11.glPopMatrix();
/* 80 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 86 */     renderTileEntityAt((TileMagicWorkbenchCharger)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileMagicWorkbenchChargerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */