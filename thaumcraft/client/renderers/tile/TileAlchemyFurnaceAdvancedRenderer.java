/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFire;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileAlchemyFurnaceAdvanced;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileAlchemyFurnaceAdvancedRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private IModelCustom model;
/*  39 */   private static final ResourceLocation FURNACE = new ResourceLocation("thaumcraft", "textures/models/adv_alch_furnace.obj");
/*     */   
/*  41 */   public TileAlchemyFurnaceAdvancedRenderer() { this.model = AdvancedModelLoader.loadModel(FURNACE); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderTileEntityAt(TileAlchemyFurnaceAdvanced tile, double par2, double par4, double par6, float par8)
/*     */   {
/*  50 */     GL11.glPushMatrix();
/*  51 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4, (float)par6 + 0.5F);
/*     */     
/*  53 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/*  54 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  56 */     if (tile.heat <= 100) {
/*  57 */       UtilsFX.bindTexture("textures/models/alch_furnace.png");
/*     */     } else {
/*  59 */       UtilsFX.bindTexture("textures/models/alch_furnace_on.png");
/*     */     }
/*     */     
/*  62 */     this.model.renderPart("Base");
/*     */     
/*  64 */     if (tile.vis <= 0) {
/*  65 */       UtilsFX.bindTexture("textures/models/alch_furnace_tank.png");
/*     */     } else {
/*  67 */       UtilsFX.bindTexture("textures/models/alch_furnace_tank_on.png");
/*     */     }
/*     */     
/*  70 */     for (int a = 0; a < 4; a++) {
/*  71 */       GL11.glPushMatrix();
/*  72 */       GL11.glRotatef(90 * a, 0.0F, 0.0F, 1.0F);
/*  73 */       this.model.renderPart("Tank");
/*  74 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  79 */     if (tile.vis > 0) {
/*  80 */       GL11.glPushMatrix();
/*  81 */       GL11.glTranslatef(0.5F, -0.5F, 1.1F);
/*  82 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  83 */       renderQuadCenteredFromIcon(ConfigBlocks.FLUXGOO.getIcon(), 190, 0.0F);
/*  84 */       GL11.glPopMatrix();
/*     */       
/*  86 */       GL11.glPushMatrix();
/*  87 */       float f = 1.0F - tile.vis / tile.maxVis;
/*  88 */       for (int a = 0; a < 4; a++) {
/*  89 */         GL11.glPushMatrix();
/*  90 */         GL11.glPushMatrix();
/*  91 */         GL11.glRotatef(90 * a, 0.0F, 0.0F, 1.0F);
/*  92 */         GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/*  93 */         GL11.glTranslatef(0.85F, -1.8F, -1.4F);
/*  94 */         GL11.glScaled(0.3D, 0.6D, 1.0D);
/*  95 */         renderQuadCenteredFromIcon(tile.func_145838_q().func_149691_a(0, 0), 150, 0.0F);
/*  96 */         GL11.glTranslatef(0.0F, 0.0F, -0.01F);
/*  97 */         renderQuadCenteredFromIcon(ConfigBlocks.FLUXGOO.getIcon(), 190, f);
/*  98 */         GL11.glPopMatrix();
/*     */         
/* 100 */         GL11.glPushMatrix();
/* 101 */         GL11.glRotatef(90 * a, 0.0F, 0.0F, -1.0F);
/* 102 */         GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 103 */         GL11.glTranslatef(1.15F, 1.8F, -1.4F);
/* 104 */         GL11.glScaled(-0.3D, -0.6D, -1.0D);
/* 105 */         renderQuadCenteredFromIcon(tile.func_145838_q().func_149691_a(0, 0), 150, 0.0F);
/* 106 */         GL11.glTranslatef(0.0F, 0.0F, 0.01F);
/* 107 */         renderQuadCenteredFromIcon(ConfigBlocks.FLUXGOO.getIcon(), 190, f);
/* 108 */         GL11.glPopMatrix();
/* 109 */         GL11.glPopMatrix();
/*     */       }
/* 111 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*     */ 
/* 115 */     if (tile.heat > 100) {
/* 116 */       GL11.glPushMatrix();
/* 117 */       GL11.glTranslatef(0.0F, 0.0F, 1.0F);
/* 118 */       for (int a = 0; a < 4; a++) {
/* 119 */         GL11.glPushMatrix();
/* 120 */         GL11.glRotatef(90 * a, 0.0F, 0.0F, 1.0F);
/* 121 */         GL11.glRotatef(135.0F, 1.0F, 0.0F, 0.0F);
/* 122 */         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 123 */         GL11.glTranslatef(-0.5F, 0.0F, -1.0F);
/* 124 */         GL11.glPushMatrix();
/* 125 */         renderQuadCenteredFromIcon(Blocks.field_150480_ab.func_149691_a(0, 0), 220, 1.0F - Math.min(1.0F, tile.heat / tile.maxPower));
/* 126 */         GL11.glPopMatrix();
/* 127 */         GL11.glTranslatef(0.0F, 0.0F, 0.05F);
/* 128 */         renderQuadCenteredFromIcon(tile.func_145838_q().func_149691_a(0, 0), 150, 0.0F);
/* 129 */         GL11.glPopMatrix();
/*     */       }
/* 131 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 134 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 135 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderQuadCenteredFromIcon(IIcon icon, int brightness, float width)
/*     */   {
/* 142 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
/* 143 */     RenderHelper.func_74518_a();
/* 144 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 146 */     float f1 = icon.func_94212_f();
/* 147 */     float f2 = icon.func_94206_g();
/* 148 */     float f3 = icon.func_94209_e();
/* 149 */     float f4 = icon.func_94210_h();
/*     */     
/* 151 */     GL11.glEnable(32826);
/* 152 */     GL11.glEnable(3042);
/* 153 */     GL11.glBlendFunc(770, 771);
/* 154 */     tessellator.func_78382_b();
/* 155 */     tessellator.func_78380_c(brightness);
/* 156 */     tessellator.func_78386_a(1.0F, 1.0F, 1.0F);
/* 157 */     tessellator.func_78374_a(0.0D, 1.0D, 0.0D, f1, f4);
/* 158 */     tessellator.func_78374_a(1.0D, 1.0D, 0.0D, f3, f4);
/* 159 */     tessellator.func_78374_a(1.0D, width, 0.0D, f3, f2);
/* 160 */     tessellator.func_78374_a(0.0D, width, 0.0D, f1, f2);
/* 161 */     tessellator.func_78381_a();
/* 162 */     GL11.glDisable(3042);
/* 163 */     GL11.glDisable(32826);
/* 164 */     RenderHelper.func_74519_b();
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*     */   {
/* 169 */     renderTileEntityAt((TileAlchemyFurnaceAdvanced)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileAlchemyFurnaceAdvancedRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */