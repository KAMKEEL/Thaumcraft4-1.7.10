/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
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
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.tiles.TileNodeConverter;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileNodeConverterRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private IModelCustom model;
/*  24 */   private static final ResourceLocation MODEL = new ResourceLocation("thaumcraft", "textures/models/node_stabilizer.obj");
/*     */   
/*  26 */   public TileNodeConverterRenderer() { this.model = AdvancedModelLoader.loadModel(MODEL); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderTileEntityAt(TileNodeConverter tile, double par2, double par4, double par6, float par8)
/*     */   {
/*  34 */     int bright = 20;
/*  35 */     if (tile.func_145831_w() != null) {
/*  36 */       bright = tile.func_145838_q().func_149677_c(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
/*     */     }
/*  38 */     GL11.glPushMatrix();
/*  39 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.0F, (float)par6 + 0.5F);
/*  40 */     UtilsFX.bindTexture("textures/models/node_converter.png");
/*  41 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  42 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  43 */     float v = Math.min(50, tile.count) / 137.0F;
/*  44 */     this.model.renderPart("lock");
/*     */     
/*  46 */     if (tile.status == 2) {
/*  47 */       GL11.glColor4f(1.0F, 0.0F, 0.3F, 1.0F);
/*  48 */     } else if (tile.status == 1) {
/*  49 */       GL11.glColor4f(1.0F, 0.6F, 0.1F, 1.0F);
/*     */     } else {
/*  51 */       GL11.glColor4f(0.5F, 1.0F, 0.5F, 1.0F);
/*     */     }
/*  53 */     if (tile.func_145831_w() != null) {
/*  54 */       float scale = MathHelper.func_76126_a(Minecraft.func_71410_x().field_71451_h.field_70173_aa / 3.0F) * 0.1F + 0.9F;
/*  55 */       int j = 50 + (int)(170.0F * (v * 2.5F * scale));
/*  56 */       int k = j % 65536;
/*  57 */       int l = j / 65536;
/*  58 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */     }
/*  60 */     UtilsFX.bindTexture("textures/models/node_converter_over.png");
/*  61 */     this.model.renderPart("lock");
/*  62 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  64 */     for (int a = 0; a < 4; a++) {
/*  65 */       GL11.glPushMatrix();
/*  66 */       if (tile.func_145831_w() != null) {
/*  67 */         int j = bright;
/*  68 */         int k = j % 65536;
/*  69 */         int l = j / 65536;
/*  70 */         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */       }
/*     */       
/*  73 */       GL11.glRotatef(90 * a, 0.0F, 0.0F, 1.0F);
/*  74 */       GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  75 */       GL11.glTranslatef(0.0F, 0.0F, v);
/*  76 */       UtilsFX.bindTexture("textures/models/node_converter.png");
/*  77 */       this.model.renderPart("piston");
/*     */       
/*  79 */       if (tile.status == 2) {
/*  80 */         GL11.glColor4f(1.0F, 0.0F, 0.3F, 1.0F);
/*  81 */       } else if (tile.status == 1) {
/*  82 */         GL11.glColor4f(1.0F, 0.6F, 0.1F, 1.0F);
/*     */       } else {
/*  84 */         GL11.glColor4f(0.5F, 1.0F, 0.5F, 1.0F);
/*     */       }
/*     */       
/*  87 */       if (tile.func_145831_w() != null) {
/*  88 */         float scale = MathHelper.func_76126_a((Minecraft.func_71410_x().field_71451_h.field_70173_aa + a * 5) / 3.0F) * 0.1F + 0.9F;
/*  89 */         int j = 50 + (int)(170.0F * (v * 2.5F * scale));
/*  90 */         int k = j % 65536;
/*  91 */         int l = j / 65536;
/*  92 */         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */       }
/*     */       
/*  95 */       UtilsFX.bindTexture("textures/models/node_converter_over.png");
/*  96 */       this.model.renderPart("piston");
/*  97 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  98 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 101 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*     */   {
/* 109 */     renderTileEntityAt((TileNodeConverter)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileNodeConverterRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */