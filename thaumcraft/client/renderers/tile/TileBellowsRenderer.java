/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.ModelBellows;
/*     */ import thaumcraft.common.tiles.TileBellows;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileBellowsRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private ModelBellows model;
/*     */   
/*     */   public TileBellowsRenderer()
/*     */   {
/*  25 */     this.model = new ModelBellows();
/*     */   }
/*     */   
/*     */   private void translateFromOrientation(double x, double y, double z, int orientation)
/*     */   {
/*  30 */     GL11.glTranslatef((float)x + 0.5F, (float)y - 0.5F, (float)z + 0.5F);
/*  31 */     if (orientation == 0) {
/*  32 */       GL11.glTranslatef(0.0F, 1.0F, -1.0F);
/*  33 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  34 */     } else if (orientation == 1) {
/*  35 */       GL11.glTranslatef(0.0F, 1.0F, 1.0F);
/*  36 */       GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
/*  37 */     } else if (orientation == 2) {
/*  38 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  39 */     } else if (orientation == 4) {
/*  40 */       GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
/*  41 */     } else if (orientation == 5) {
/*  42 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void renderEntityAt(TileBellows bellows, double x, double y, double z, float fq)
/*     */   {
/*  49 */     float scale = 0.0F;
/*     */     
/*  51 */     if (bellows.func_145831_w() == null) {
/*  52 */       EntityPlayer p = Minecraft.func_71410_x().field_71439_g;
/*  53 */       scale = MathHelper.func_76126_a(p.field_70173_aa / 8.0F) * 0.3F + 0.7F;
/*  54 */       bellows.orientation = 2;
/*     */     } else {
/*  56 */       scale = bellows.inflation;
/*     */     }
/*     */     
/*  59 */     float tscale = 0.125F + scale * 0.875F;
/*     */     
/*  61 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*     */     
/*  63 */     UtilsFX.bindTexture("textures/models/bellows.png");
/*  64 */     GL11.glPushMatrix();
/*  65 */     GL11.glEnable(2977);
/*  66 */     GL11.glEnable(3042);
/*  67 */     GL11.glEnable(32826);
/*  68 */     GL11.glBlendFunc(770, 771);
/*  69 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  70 */     translateFromOrientation((float)x, (float)y, (float)z, bellows.orientation);
/*  71 */     GL11.glTranslatef(0.0F, 1.0F, 0.0F);
/*     */     
/*  73 */     GL11.glPushMatrix();
/*  74 */     GL11.glScalef(0.5F, (scale + 0.1F) / 2.0F, 0.5F);
/*  75 */     this.model.Bag.func_78793_a(0.0F, 0.5F, 0.0F);
/*  76 */     this.model.Bag.func_78785_a(0.0625F);
/*  77 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  78 */     GL11.glPopMatrix();
/*  79 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/*     */     
/*  81 */     GL11.glPushMatrix();
/*  82 */     GL11.glTranslatef(0.0F, -tscale / 2.0F + 0.5F, 0.0F);
/*  83 */     this.model.TopPlank.func_78785_a(0.0625F);
/*  84 */     GL11.glTranslatef(0.0F, tscale / 2.0F - 0.5F, 0.0F);
/*  85 */     GL11.glPopMatrix();
/*     */     
/*  87 */     GL11.glPushMatrix();
/*  88 */     GL11.glTranslatef(0.0F, tscale / 2.0F - 0.5F, 0.0F);
/*  89 */     this.model.BottomPlank.func_78785_a(0.0625F);
/*  90 */     GL11.glTranslatef(0.0F, -tscale / 2.0F + 0.5F, 0.0F);
/*  91 */     GL11.glPopMatrix();
/*     */     
/*  93 */     this.model.render();
/*  94 */     GL11.glDisable(32826);
/*  95 */     GL11.glDisable(3042);
/*  96 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  97 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f)
/*     */   {
/* 104 */     renderEntityAt((TileBellows)tileentity, d, d1, d2, f);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileBellowsRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */