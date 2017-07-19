/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.ModelBoreBase;
/*     */ import thaumcraft.common.tiles.TileAlembic;
/*     */ import thaumcraft.common.tiles.TileTube;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileAlembicRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private IModelCustom model;
/*  26 */   private static final ResourceLocation ALEMBIC = new ResourceLocation("thaumcraft", "textures/models/alembic.obj");
/*     */   private ModelBoreBase modelBore;
/*     */   
/*  29 */   public TileAlembicRenderer() { this.model = AdvancedModelLoader.loadModel(ALEMBIC);
/*  30 */     this.modelBore = new ModelBoreBase();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderTileEntityAt(TileAlembic tile, double par2, double par4, double par6, float par8)
/*     */   {
/*  39 */     GL11.glPushMatrix();
/*  40 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4, (float)par6 + 0.5F);
/*  41 */     GL11.glPushMatrix();
/*  42 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/*  43 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  45 */     UtilsFX.bindTexture("textures/models/alembic.png");
/*     */     
/*  47 */     int md = 0;
/*  48 */     if (tile.func_145831_w() != null) {
/*  49 */       switch (tile.facing) {
/*  50 */       case 5:  GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F); break;
/*  51 */       case 3:  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F); break;
/*  52 */       case 2:  GL11.glRotatef(270.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/*  55 */       if (tile.aboveFurnace) {
/*  56 */         this.model.renderPart("TubeMain");
/*  57 */         this.model.renderPart("Legs");
/*     */       }
/*  59 */       else if (tile.aboveAlembic) {
/*  60 */         this.model.renderPart("TubeMain");
/*  61 */         this.model.renderPart("TubeSmall");
/*     */       } else {
/*  63 */         this.model.renderPart("Legs");
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/*  69 */       GL11.glTranslatef(0.0F, 0.0F, -0.4F);
/*  70 */       this.model.renderPart("Legs");
/*     */     }
/*     */     
/*  73 */     this.model.renderPart("Pot");
/*  74 */     this.model.renderPart("Panel");
/*  75 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/*     */ 
/*  79 */     if (tile.aspectFilter != null) {
/*  80 */       GL11.glPushMatrix();
/*  81 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  82 */       switch (tile.facing) {
/*  83 */       case 5:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/*  84 */       case 3:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/*  85 */       case 2:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       
/*     */       
/*  89 */       GL11.glPushMatrix();
/*  90 */       GL11.glTranslatef(0.0F, 0.468F, -0.409F);
/*  91 */       UtilsFX.renderQuadCenteredFromTexture("textures/models/label.png", 0.27F, 1.0F, 1.0F, 1.0F, -99, 771, 1.0F);
/*  92 */       GL11.glPopMatrix();
/*     */       
/*  94 */       GL11.glPushMatrix();
/*  95 */       GL11.glTranslatef(0.0F, 0.468F, -0.41F);
/*  96 */       GL11.glScaled(0.013D, 0.013D, 0.013D);
/*  97 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*     */       
/*  99 */       UtilsFX.drawTag(-8, -8, tile.aspectFilter);
/* 100 */       GL11.glPopMatrix();
/*     */       
/* 102 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 105 */     GL11.glPopMatrix();
/*     */     
/* 107 */     if (tile.func_145831_w() != null) {
/* 108 */       UtilsFX.bindTexture("textures/models/Bore.png");
/* 109 */       for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/* 110 */         if (tile.canOutputTo(dir)) {
/* 111 */           TileEntity te = ThaumcraftApiHelper.getConnectableTile(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, dir);
/*     */           
/* 113 */           if ((te != null) && ((te instanceof IEssentiaTransport)) && (!(te instanceof TileTube))) {
/* 114 */             GL11.glPushMatrix();
/* 115 */             GL11.glTranslatef((float)par2 + 0.5F, (float)par4, (float)par6 + 0.5F);
/*     */             
/*     */ 
/* 118 */             switch (dir.ordinal()) {
/* 119 */             case 0:  GL11.glTranslatef(-0.5F, 0.5F, 0.0F);
/* 120 */               GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F); break;
/* 121 */             case 1:  GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 122 */               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F); break;
/* 123 */             case 2:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 124 */             case 3:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 125 */             case 4:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/* 126 */             case 5:  GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/*     */             }
/* 128 */             this.modelBore.renderNozzle();
/* 129 */             GL11.glPopMatrix();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*     */   {
/* 138 */     renderTileEntityAt((TileAlembic)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileAlembicRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */