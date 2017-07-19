/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.ModelCube;
/*     */ import thaumcraft.common.blocks.BlockCustomPlant;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileEtherealBloom;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileEtherealBloomRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  20 */   String tx2 = "textures/models/crystalcapacitor.png";
/*     */   BlockCustomPlant block;
/*  22 */   private ModelCube model = new ModelCube();
/*     */   
/*  24 */   public TileEtherealBloomRenderer() { this.block = ((BlockCustomPlant)ConfigBlocks.blockCustomPlant); }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_147500_a(TileEntity tile, double x, double y, double z, float par8)
/*     */   {
/*  30 */     float rc1 = ((TileEtherealBloom)tile).growthCounter + par8;
/*  31 */     float rc2 = rc1;
/*  32 */     float rc3 = rc1 - 33.0F;
/*  33 */     float rc4 = rc1 - 66.0F;
/*     */     
/*  35 */     if (rc1 > 100.0F) rc1 = 100.0F;
/*  36 */     if (rc2 > 50.0F) { rc2 = 50.0F;
/*     */     }
/*  38 */     if (rc3 < 0.0F) rc3 = 0.0F;
/*  39 */     if (rc3 > 33.0F) { rc3 = 33.0F;
/*     */     }
/*  41 */     if (rc4 < 0.0F) rc4 = 0.0F;
/*  42 */     if (rc4 > 33.0F) { rc4 = 33.0F;
/*     */     }
/*  44 */     float scale1 = rc1 / 100.0F;
/*  45 */     float scale2 = rc2 / 60.0F + 0.1666666F;
/*  46 */     float scale3 = rc3 / 33.0F;
/*  47 */     float scale4 = rc4 / 33.0F * 0.7F;
/*     */     
/*  49 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/*     */ 
/*  52 */     GL11.glPushMatrix();
/*  53 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  54 */     GL11.glEnable(3042);
/*  55 */     GL11.glBlendFunc(770, 1);
/*     */     
/*     */ 
/*  58 */     GL11.glPushMatrix();
/*  59 */     GL11.glDepthMask(false);
/*  60 */     GL11.glDisable(2884);
/*  61 */     int i = ((TileEtherealBloom)tile).counter % 32;
/*  62 */     UtilsFX.bindTexture(TileNodeRenderer.nodetex);
/*  63 */     UtilsFX.renderFacingStrip(tile.field_145851_c + 0.5D, tile.field_145848_d + scale1, tile.field_145849_e + 0.5D, 0.0F, scale1, 1.0F, 32, 6, i, par8, 11197951);
/*     */     
/*     */ 
/*  66 */     GL11.glEnable(2884);
/*  67 */     GL11.glDepthMask(true);
/*  68 */     GL11.glPopMatrix();
/*     */     
/*  70 */     GL11.glPushMatrix();
/*  71 */     GL11.glTranslated(x + 0.5D - scale4 / 8.0F, y + scale1 - scale4 / 6.0F, z + 0.5D - scale4 / 8.0F);
/*  72 */     GL11.glScaled(scale4 / 4.0F, scale4 / 3.0F, scale4 / 4.0F);
/*  73 */     UtilsFX.bindTexture(this.tx2);
/*  74 */     this.model.render();
/*  75 */     GL11.glPopMatrix();
/*     */     
/*  77 */     GL11.glPushMatrix();
/*  78 */     GL11.glTranslated(x + 0.5D, y + 0.25D, z + 0.5D);
/*  79 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*  80 */     for (int a = 0; a < 4; a++) {
/*  81 */       GL11.glPushMatrix();
/*  82 */       GL11.glScaled(scale3, scale1, scale3);
/*  83 */       GL11.glRotatef(90 * a, 0.0F, 1.0F, 0.0F);
/*  84 */       UtilsFX.renderQuadCenteredFromIcon(true, this.block.iconLeaves, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/*  85 */       GL11.glPopMatrix();
/*     */     }
/*  87 */     GL11.glPopMatrix();
/*     */     
/*  89 */     GL11.glPushMatrix();
/*  90 */     GL11.glTranslated(x + 0.5D, y + 0.6D, z + 0.5D);
/*  91 */     GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  92 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*  93 */     for (int a = 0; a < 4; a++) {
/*  94 */       GL11.glPushMatrix();
/*  95 */       GL11.glScaled(scale4, scale1 * 0.7F, scale4);
/*  96 */       GL11.glRotatef(90 * a, 0.0F, 1.0F, 0.0F);
/*  97 */       UtilsFX.renderQuadCenteredFromIcon(true, this.block.iconLeaves, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/*  98 */       GL11.glPopMatrix();
/*     */     }
/* 100 */     GL11.glPopMatrix();
/*     */     
/* 102 */     GL11.glPushMatrix();
/* 103 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/* 104 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 105 */     for (int a = 0; a < 4; a++) {
/* 106 */       GL11.glPushMatrix();
/* 107 */       GL11.glTranslated(0.0D, (1.0F - scale1) / 2.0F, 0.0D);
/* 108 */       GL11.glScaled(scale2, scale1, scale2);
/* 109 */       GL11.glRotatef(90 * a, 0.0F, 1.0F, 0.0F);
/* 110 */       UtilsFX.renderQuadCenteredFromIcon(true, this.block.iconStalk, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/* 111 */       GL11.glPopMatrix();
/*     */     }
/* 113 */     GL11.glPopMatrix();
/*     */     
/* 115 */     GL11.glDisable(3042);
/* 116 */     GL11.glAlphaFunc(516, 0.1F);
/* 117 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileEtherealBloomRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */