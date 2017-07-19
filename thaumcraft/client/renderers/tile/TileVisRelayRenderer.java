/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.visnet.VisNetHandler;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.tiles.TileVisRelay;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileVisRelayRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private IModelCustom model;
/*  27 */   private static final ResourceLocation RELAY = new ResourceLocation("thaumcraft", "textures/models/vis_relay.obj");
/*     */   
/*  29 */   public TileVisRelayRenderer() { this.model = AdvancedModelLoader.loadModel(RELAY); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderTileEntityAt(TileVisRelay tile, double par2, double par4, double par6, float par8)
/*     */   {
/*  39 */     int facing = 1;
/*  40 */     if (tile.func_145831_w() != null) {
/*  41 */       facing = tile.orientation;
/*     */     }
/*     */     
/*  44 */     int ticks = Minecraft.func_71410_x().field_71451_h.field_70173_aa;
/*     */     
/*  46 */     GL11.glPushMatrix();
/*  47 */     translateFromOrientation(par2, par4, par6, facing);
/*  48 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*  49 */     GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
/*  50 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  51 */     GL11.glPushMatrix();
/*  52 */     UtilsFX.bindTexture("textures/models/vis_relay.png");
/*     */     
/*  54 */     GL11.glPushMatrix();
/*  55 */     GL11.glScaled(0.75D, 0.75D, 0.75D);
/*  56 */     GL11.glTranslated(0.0D, 0.0D, -0.16D);
/*  57 */     this.model.renderPart("RingBase");
/*  58 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/*     */ 
/*  62 */     this.model.renderPart("RingFloat");
/*     */     
/*  64 */     GL11.glEnable(3042);
/*  65 */     GL11.glBlendFunc(770, 771);
/*  66 */     if (tile.color >= 0) {
/*  67 */       Color c = new Color(TileVisRelay.colors[tile.color]);
/*  68 */       GL11.glColor3f(c.getRed() / 200.0F, c.getGreen() / 200.0F, c.getBlue() / 200.0F);
/*     */     }
/*  70 */     float scale = MathHelper.func_76126_a((ticks + par8) / 2.0F) * 0.05F + 0.95F;
/*  71 */     int j = (VisNetHandler.isNodeValid(tile.getParent()) ? 50 : 0) + (int)(150.0F * scale);
/*  72 */     int k = j % 65536;
/*  73 */     int l = j / 65536;
/*  74 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*  75 */     this.model.renderPart("Crystal");
/*     */     
/*  77 */     GL11.glDisable(3042);
/*  78 */     GL11.glPopMatrix();
/*  79 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void translateFromOrientation(double x, double y, double z, int orientation)
/*     */   {
/*  84 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*  85 */     if (orientation == 0) {
/*  86 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  87 */     } else if (orientation == 1) {
/*  88 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  89 */     } else if (orientation != 2) {
/*  90 */       if (orientation == 3) {
/*  91 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  92 */       } else if (orientation == 4) {
/*  93 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  94 */       } else if (orientation == 5) {
/*  95 */         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 101 */     renderTileEntityAt((TileVisRelay)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileVisRelayRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */