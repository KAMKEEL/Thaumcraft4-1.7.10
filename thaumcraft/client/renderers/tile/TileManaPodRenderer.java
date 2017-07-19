/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.ModelManaPod;
/*     */ import thaumcraft.common.tiles.TileManaPod;
/*     */ 
/*     */ public class TileManaPodRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private ModelManaPod model;
/*  24 */   private static final ResourceLocation pod0tex = new ResourceLocation("thaumcraft", "textures/models/manapod_0.png");
/*     */   
/*  26 */   private static final ResourceLocation pod2tex = new ResourceLocation("thaumcraft", "textures/models/manapod_2.png");
/*     */   
/*     */   public TileManaPodRenderer()
/*     */   {
/*  30 */     this.model = new ModelManaPod();
/*     */   }
/*     */   
/*     */   public void renderEntityAt(TileManaPod pod, double x, double y, double z, float fq)
/*     */   {
/*  35 */     int meta = 0;
/*  36 */     int bright = 20;
/*  37 */     Aspect aspect = Aspect.PLANT;
/*  38 */     if (pod.func_145831_w() == null) {
/*  39 */       meta = 5;
/*     */     } else {
/*  41 */       meta = pod.func_145832_p();
/*  42 */       if (pod.aspect != null)
/*  43 */         aspect = pod.aspect;
/*  44 */       bright = pod.func_145838_q().func_149677_c(pod.func_145831_w(), pod.field_145851_c, pod.field_145848_d, pod.field_145849_e);
/*     */     }
/*     */     
/*  47 */     if (meta > 1) {
/*  48 */       float br = 0.14509805F;
/*  49 */       float bg = 0.6156863F;
/*  50 */       float bb = 0.45882353F;
/*     */       
/*  52 */       float fr = br;
/*  53 */       float fg = bg;
/*  54 */       float fb = bb;
/*     */       
/*  56 */       if (pod.aspect != null) {
/*  57 */         Color color = new Color(aspect.getColor());
/*  58 */         float ar = color.getRed() / 255.0F;
/*  59 */         float ag = color.getGreen() / 255.0F;
/*  60 */         float ab = color.getBlue() / 255.0F;
/*     */         
/*  62 */         if (meta == 7) {
/*  63 */           fr = ar;
/*  64 */           fg = ag;
/*  65 */           fb = ab;
/*     */         } else {
/*  67 */           float m = meta - 2;
/*  68 */           fr = (br + ar * m) / (m + 1.0F);
/*  69 */           fg = (bg + ag * m) / (m + 1.0F);
/*  70 */           fb = (bb + ab * m) / (m + 1.0F);
/*     */         }
/*     */       }
/*     */       
/*  74 */       Minecraft mc = FMLClientHandler.instance().getClient();
/*     */       
/*  76 */       GL11.glPushMatrix();
/*  77 */       GL11.glEnable(2977);
/*  78 */       GL11.glEnable(3042);
/*  79 */       GL11.glEnable(32826);
/*  80 */       GL11.glBlendFunc(770, 771);
/*  81 */       GL11.glTranslated(x + 0.5D, y + 0.75D, z + 0.5D);
/*  82 */       GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */       
/*  84 */       if (meta > 2)
/*     */       {
/*     */ 
/*  87 */         EntityPlayer p = Minecraft.func_71410_x().field_71439_g;
/*  88 */         float scale = MathHelper.func_76126_a((p.field_70173_aa + pod.hashCode() % 100) / 8.0F) * 0.1F + 0.9F;
/*  89 */         GL11.glPushMatrix();
/*     */         
/*  91 */         float bs = MathHelper.func_76126_a((p.field_70173_aa + pod.hashCode() % 100) / 8.0F) * 0.3F + 0.7F;
/*  92 */         int j = meta * 10 + (int)(150.0F * scale);
/*  93 */         int k = j % 65536;
/*  94 */         int l = j / 65536;
/*  95 */         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */         
/*  97 */         GL11.glTranslated(0.0D, 0.1D, 0.0D);
/*  98 */         GL11.glScaled(0.125D * meta * scale, 0.125D * meta * scale, 0.125D * meta * scale);
/*     */         
/* 100 */         UtilsFX.bindTexture(pod0tex);
/* 101 */         this.model.pod0.func_78785_a(0.0625F);
/* 102 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/* 105 */       GL11.glScaled(0.15D * meta, 0.15D * meta, 0.15D * meta);
/* 106 */       GL11.glColor4f(fr, fg, fb, 0.9F);
/* 107 */       UtilsFX.bindTexture(pod2tex);
/* 108 */       this.model.pod2.func_78785_a(0.0625F);
/*     */       
/* 110 */       GL11.glDisable(32826);
/* 111 */       GL11.glDisable(3042);
/* 112 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f)
/*     */   {
/* 122 */     renderEntityAt((TileManaPod)tileentity, d, d1, d2, f);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileManaPodRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */