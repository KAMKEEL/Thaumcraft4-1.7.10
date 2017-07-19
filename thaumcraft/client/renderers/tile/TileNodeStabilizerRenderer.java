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
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.tiles.TileNodeStabilizer;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileNodeStabilizerRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private IModelCustom model;
/*  25 */   private static final ResourceLocation MODEL = new ResourceLocation("thaumcraft", "textures/models/node_stabilizer.obj");
/*     */   
/*  27 */   public TileNodeStabilizerRenderer() { this.model = AdvancedModelLoader.loadModel(MODEL); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderTileEntityAt(TileNodeStabilizer tile, double par2, double par4, double par6, float par8)
/*     */   {
/*  35 */     int lock = 1;
/*  36 */     int bright = 20;
/*  37 */     if (tile.func_145831_w() != null) {
/*  38 */       if (tile.func_145831_w().func_72805_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e) == 10) {
/*  39 */         lock = 2;
/*     */       }
/*  41 */       bright = tile.func_145838_q().func_149677_c(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
/*     */     } else {
/*  43 */       lock = tile.lock;
/*     */     }
/*  45 */     GL11.glPushMatrix();
/*  46 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4, (float)par6 + 0.5F);
/*     */     
/*  48 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/*  49 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  51 */     UtilsFX.bindTexture("textures/models/node_stabilizer.png");
/*     */     
/*  53 */     this.model.renderPart("lock");
/*  54 */     for (int a = 0; a < 4; a++) {
/*  55 */       GL11.glPushMatrix();
/*  56 */       if (tile.func_145831_w() != null) {
/*  57 */         int j = bright;
/*  58 */         int k = j % 65536;
/*  59 */         int l = j / 65536;
/*  60 */         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */       }
/*     */       
/*  63 */       GL11.glRotatef(90 * a, 0.0F, 0.0F, 1.0F);
/*  64 */       GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  65 */       GL11.glTranslatef(0.0F, 0.0F, tile.count / 100.0F);
/*  66 */       UtilsFX.bindTexture("textures/models/node_stabilizer.png");
/*  67 */       this.model.renderPart("piston");
/*     */       
/*  69 */       if (lock == 2) {
/*  70 */         GL11.glColor4f(1.0F, 0.2F, 0.2F, 1.0F);
/*     */       }
/*     */       
/*  73 */       if (tile.func_145831_w() != null) {
/*  74 */         float scale = MathHelper.func_76126_a((Minecraft.func_71410_x().field_71451_h.field_70173_aa + a * 5) / 3.0F) * 0.1F + 0.9F;
/*  75 */         int j = 50 + (int)(170.0F * (tile.count / 37.0F * scale));
/*  76 */         int k = j % 65536;
/*  77 */         int l = j / 65536;
/*  78 */         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */       }
/*     */       
/*  81 */       UtilsFX.bindTexture("textures/models/node_stabilizer_over.png");
/*  82 */       this.model.renderPart("piston");
/*  83 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  84 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*  87 */     GL11.glPopMatrix();
/*     */     
/*  89 */     if (tile.count > 0) {
/*  90 */       GL11.glPushMatrix();
/*  91 */       GL11.glAlphaFunc(516, 0.003921569F);
/*  92 */       GL11.glEnable(3042);
/*  93 */       GL11.glBlendFunc(770, 1);
/*  94 */       GL11.glDepthMask(false);
/*  95 */       float alpha = MathHelper.func_76126_a(Minecraft.func_71410_x().field_71451_h.field_70173_aa / 8.0F) * 0.1F + 0.5F;
/*  96 */       UtilsFX.bindTexture("textures/misc/node_bubble.png");
/*  97 */       UtilsFX.renderFacingQuad(tile.field_145851_c + 0.5D, tile.field_145848_d + 1.5D, tile.field_145849_e + 0.5D, 0.0F, 0.9F, tile.count / 37.0F * alpha, 1, 0, par8, lock == 1 ? 16777215 : 16729156);
/*     */       
/*     */ 
/* 100 */       GL11.glDepthMask(true);
/* 101 */       GL11.glDisable(3042);
/* 102 */       GL11.glAlphaFunc(516, 0.1F);
/* 103 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*     */   {
/* 112 */     renderTileEntityAt((TileNodeStabilizer)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileNodeStabilizerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */