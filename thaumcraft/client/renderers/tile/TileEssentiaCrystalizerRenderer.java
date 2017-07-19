/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.tiles.TileEssentiaCrystalizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileEssentiaCrystalizerRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private IModelCustom model;
/*     */   private IModelCustom model2;
/*  29 */   private static final ResourceLocation RELAY = new ResourceLocation("thaumcraft", "textures/models/crystalizer.obj");
/*  30 */   private static final ResourceLocation CRYSTAL = new ResourceLocation("thaumcraft", "textures/models/vis_relay.obj");
/*     */   
/*  32 */   public TileEssentiaCrystalizerRenderer() { this.model = AdvancedModelLoader.loadModel(RELAY);
/*  33 */     this.model2 = AdvancedModelLoader.loadModel(CRYSTAL);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderTileEntityAt(TileEssentiaCrystalizer tile, double par2, double par4, double par6, float par8)
/*     */   {
/*  42 */     int facing = tile.facing.ordinal();
/*     */     
/*  44 */     int ticks = Minecraft.func_71410_x().field_71451_h.field_70173_aa;
/*     */     
/*  46 */     GL11.glPushMatrix();
/*  47 */     translateFromOrientation(par2, par4, par6, facing);
/*  48 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  49 */     GL11.glPushMatrix();
/*  50 */     UtilsFX.bindTexture("textures/models/crystalizer.png");
/*     */     
/*  52 */     this.model.renderAll();
/*     */     
/*     */ 
/*  55 */     GL11.glEnable(3042);
/*  56 */     GL11.glBlendFunc(770, 771);
/*  57 */     UtilsFX.bindTexture("textures/models/vis_relay.png");
/*  58 */     GL11.glColor3f(tile.cr, tile.cg, tile.cb);
/*  59 */     for (int q = 0; q < 4; q++) {
/*  60 */       GL11.glPushMatrix();
/*  61 */       GL11.glScaled(0.75D, 0.75D, 0.75D);
/*  62 */       float glow = MathHelper.func_76126_a((ticks + par8 + q * 10) / 2.0F) * 0.05F + 0.95F;
/*  63 */       int j = 50 + (int)(150.0F * glow);
/*  64 */       int k = j % 65536;
/*  65 */       int l = j / 65536;
/*  66 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */       
/*     */ 
/*  69 */       GL11.glRotatef(90 * q, 0.0F, 0.0F, 1.0F);
/*  70 */       GL11.glTranslated(0.34D, 0.0D, 1.2125D);
/*  71 */       GL11.glRotatef(tile.spin + tile.spinInc * par8, 0.0F, 0.0F, 1.0F);
/*     */       
/*  73 */       this.model2.renderPart("Crystal");
/*     */       
/*  75 */       GL11.glPopMatrix();
/*     */     }
/*  77 */     GL11.glDisable(3042);
/*     */     
/*  79 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/*  82 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void translateFromOrientation(double x, double y, double z, int orientation)
/*     */   {
/*  87 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*  88 */     if (orientation == 0) {
/*  89 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  90 */     } else if (orientation == 1) {
/*  91 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  92 */     } else if (orientation != 2) {
/*  93 */       if (orientation == 3) {
/*  94 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  95 */       } else if (orientation == 4) {
/*  96 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  97 */       } else if (orientation == 5)
/*  98 */         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */     }
/* 100 */     GL11.glTranslated(0.0D, 0.0D, -0.5D);
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*     */   {
/* 105 */     renderTileEntityAt((TileEssentiaCrystalizer)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileEssentiaCrystalizerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */