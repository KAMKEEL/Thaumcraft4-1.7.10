/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.tiles.TileEldritchAltar;
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
/*     */ 
/*     */ 
/*     */ public class TileEldritchCapRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private IModelCustom model;
/*  36 */   private static final ResourceLocation CAP = new ResourceLocation("thaumcraft", "textures/models/obelisk_cap.obj");
/*     */   
/*     */   public TileEldritchCapRenderer(String texture)
/*     */   {
/*  40 */     this.tex = texture;
/*  41 */     this.model = AdvancedModelLoader.loadModel(CAP);
/*     */   }
/*     */   
/*     */   public TileEldritchCapRenderer()
/*     */   {
/*  46 */     this.model = AdvancedModelLoader.loadModel(CAP);
/*     */   }
/*     */   
/*  49 */   private String tex = "textures/models/obelisk_cap.png";
/*  50 */   private String tex2 = "textures/models/obelisk_cap_2.png";
/*     */   
/*  52 */   private ItemStack eye = null;
/*  53 */   EntityItem entityitem = null;
/*     */   
/*     */ 
/*     */   public void func_147500_a(TileEntity te, double x, double y, double z, float f)
/*     */   {
/*  58 */     String tempTex = this.tex;
/*  59 */     GL11.glPushMatrix();
/*     */     
/*  61 */     if (te.func_145831_w() != null) {
/*  62 */       int j = te.func_145838_q().func_149677_c(te.func_145831_w(), te.field_145851_c, te.field_145848_d, te.field_145849_e);
/*  63 */       int k = j % 65536;
/*  64 */       int l = j / 65536;
/*  65 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*  66 */       if (te.func_145831_w().field_73011_w.field_76574_g == Config.dimensionOuterId) { tempTex = this.tex2;
/*     */       }
/*     */     }
/*  69 */     GL11.glPushMatrix();
/*  70 */     UtilsFX.bindTexture(tempTex);
/*  71 */     GL11.glTranslated(x + 0.5D, y, z + 0.5D);
/*  72 */     GL11.glRotated(90.0D, -1.0D, 0.0D, 0.0D);
/*  73 */     this.model.renderPart("Cap");
/*  74 */     GL11.glPopMatrix();
/*     */     
/*  76 */     if ((te.func_145831_w() != null) && ((te instanceof TileEldritchAltar)) && (((TileEldritchAltar)te).getEyes() > 0))
/*     */     {
/*  78 */       GL11.glPushMatrix();
/*  79 */       GL11.glTranslatef((float)x + 0.5F, (float)y + 0.0F, (float)z + 0.5F);
/*     */       
/*  81 */       if ((this.entityitem == null) || (this.eye == null)) {
/*  82 */         this.eye = new ItemStack(ConfigItems.itemEldritchObject, 1, 0);
/*  83 */         this.entityitem = new EntityItem(te.func_145831_w(), 0.0D, 0.0D, 0.0D, this.eye);
/*  84 */         this.entityitem.field_70290_d = 0.0F;
/*     */       }
/*     */       
/*  87 */       if ((this.entityitem != null) && (this.eye != null)) {
/*  88 */         for (int a = 0; a < ((TileEldritchAltar)te).getEyes(); a++) {
/*  89 */           GL11.glPushMatrix();
/*  90 */           GL11.glRotated(a * 90, 0.0D, 1.0D, 0.0D);
/*  91 */           GL11.glTranslatef(0.46F, 0.2F, 0.0F);
/*  92 */           GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
/*  93 */           GL11.glRotated(18.0D, -1.0D, 0.0D, 0.0D);
/*  94 */           net.minecraft.client.renderer.entity.RenderItem.field_82407_g = true;
/*  95 */           RenderManager.field_78727_a.func_147940_a(this.entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*  96 */           net.minecraft.client.renderer.entity.RenderItem.field_82407_g = false;
/*  97 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/*     */       
/* 101 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*     */ 
/* 105 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileEldritchCapRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */