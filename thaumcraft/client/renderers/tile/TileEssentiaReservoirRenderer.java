/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.block.BlockRenderer;
/*     */ import thaumcraft.common.blocks.BlockJar;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileEssentiaReservoir;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileEssentiaReservoirRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private IModelCustom model;
/*  31 */   private static final ResourceLocation RELAY = new ResourceLocation("thaumcraft", "textures/models/reservoir.obj");
/*     */   
/*  33 */   public TileEssentiaReservoirRenderer() { this.model = AdvancedModelLoader.loadModel(RELAY); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderTileEntityAt(TileEssentiaReservoir tile, double par2, double par4, double par6, float par8)
/*     */   {
/*  42 */     int facing = tile.facing.ordinal();
/*     */     
/*  44 */     GL11.glPushMatrix();
/*  45 */     translateFromOrientation(par2, par4, par6, facing);
/*  46 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  47 */     UtilsFX.bindTexture("textures/models/reservoir.png");
/*  48 */     this.model.renderAll();
/*  49 */     GL11.glPopMatrix();
/*     */     
/*  51 */     GL11.glPushMatrix();
/*  52 */     GL11.glTranslated(par2, par4 - 0.5D, par6);
/*  53 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  54 */     renderLiquid(tile, par2, par4, par6, par8);
/*  55 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void renderLiquid(TileEssentiaReservoir te, double x, double y, double z, float f) {
/*  59 */     if ((this.field_147501_a.field_147553_e == null) || (te.displayAspect == null) || (te.essentia.visSize() == 0)) { return;
/*     */     }
/*  61 */     GL11.glPushMatrix();
/*  62 */     GL11.glEnable(3042);
/*  63 */     GL11.glBlendFunc(770, 771);
/*  64 */     World world = te.func_145831_w();
/*  65 */     RenderBlocks renderBlocks = new RenderBlocks();
/*     */     
/*  67 */     GL11.glDisable(2896);
/*     */     
/*  69 */     float level = te.essentia.visSize() / te.maxAmount;
/*     */     
/*  71 */     Tessellator t = Tessellator.field_78398_a;
/*     */     
/*  73 */     renderBlocks.func_147782_a(BlockRenderer.W3, BlockRenderer.W3, BlockRenderer.W3, BlockRenderer.W13, BlockRenderer.W3 + BlockRenderer.W10 * level, BlockRenderer.W13);
/*     */     
/*     */ 
/*     */ 
/*  77 */     t.func_78382_b();
/*     */     
/*  79 */     t.func_78369_a(te.cr, te.cg, te.cb, 0.9F);
/*     */     
/*  81 */     int bright = 200;
/*     */     
/*  83 */     t.func_78380_c(200);
/*     */     
/*  85 */     IIcon icon = ((BlockJar)ConfigBlocks.blockJar).iconLiquid;
/*     */     
/*  87 */     this.field_147501_a.field_147553_e.func_110577_a(TextureMap.field_110575_b);
/*     */     
/*  89 */     renderBlocks.func_147768_a(ConfigBlocks.blockEssentiaReservoir, 0.0D, 0.5D, 0.0D, icon);
/*  90 */     renderBlocks.func_147806_b(ConfigBlocks.blockEssentiaReservoir, 0.0D, 0.5D, 0.0D, icon);
/*  91 */     renderBlocks.func_147761_c(ConfigBlocks.blockEssentiaReservoir, 0.0D, 0.5D, 0.0D, icon);
/*  92 */     renderBlocks.func_147734_d(ConfigBlocks.blockEssentiaReservoir, 0.0D, 0.5D, 0.0D, icon);
/*  93 */     renderBlocks.func_147798_e(ConfigBlocks.blockEssentiaReservoir, 0.0D, 0.5D, 0.0D, icon);
/*  94 */     renderBlocks.func_147764_f(ConfigBlocks.blockEssentiaReservoir, 0.0D, 0.5D, 0.0D, icon);
/*     */     
/*  96 */     t.func_78381_a();
/*     */     
/*     */ 
/*  99 */     GL11.glEnable(2896);
/* 100 */     GL11.glDisable(3042);
/* 101 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void translateFromOrientation(double x, double y, double z, int orientation)
/*     */   {
/* 106 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/* 107 */     if (orientation == 0) {
/* 108 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 109 */     } else if (orientation == 1) {
/* 110 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 111 */     } else if (orientation != 2) {
/* 112 */       if (orientation == 3) {
/* 113 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 114 */       } else if (orientation == 4) {
/* 115 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 116 */       } else if (orientation == 5)
/* 117 */         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */     }
/* 119 */     GL11.glTranslated(0.0D, 0.0D, -0.5D);
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*     */   {
/* 124 */     renderTileEntityAt((TileEssentiaReservoir)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileEssentiaReservoirRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */