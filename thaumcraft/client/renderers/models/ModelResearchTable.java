/*     */ package thaumcraft.client.renderers.models;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelResearchTable
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Top;
/*     */   ModelRenderer Leg1;
/*     */   ModelRenderer Leg2;
/*     */   ModelRenderer Leg3;
/*     */   ModelRenderer Leg4;
/*     */   ModelRenderer Crossbar;
/*     */   ModelRenderer Inkwell;
/*     */   ModelRenderer ScrollTube;
/*     */   ModelRenderer ScrollRibbon;
/*     */   
/*     */   public ModelResearchTable()
/*     */   {
/*  27 */     this.field_78090_t = 128;
/*  28 */     this.field_78089_u = 64;
/*     */     
/*  30 */     this.Top = new ModelRenderer(this, 0, 0);
/*  31 */     this.Top.func_78789_a(0.0F, 0.0F, 0.0F, 32, 4, 16);
/*  32 */     this.Top.func_78793_a(-8.0F, 0.0F, -8.0F);
/*  33 */     this.Top.func_78787_b(128, 64);
/*  34 */     this.Top.field_78809_i = true;
/*  35 */     setRotation(this.Top, 0.0F, 0.0F, 0.0F);
/*  36 */     this.Leg1 = new ModelRenderer(this, 0, 24);
/*  37 */     this.Leg1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 12, 4);
/*  38 */     this.Leg1.func_78793_a(-6.0F, 4.0F, -6.0F);
/*  39 */     this.Leg1.func_78787_b(128, 64);
/*  40 */     this.Leg1.field_78809_i = true;
/*  41 */     setRotation(this.Leg1, 0.0F, 0.0F, 0.0F);
/*  42 */     this.Leg2 = new ModelRenderer(this, 0, 24);
/*  43 */     this.Leg2.func_78789_a(0.0F, 0.0F, 0.0F, 4, 12, 4);
/*  44 */     this.Leg2.func_78793_a(-6.0F, 4.0F, 2.0F);
/*  45 */     this.Leg2.func_78787_b(128, 64);
/*  46 */     this.Leg2.field_78809_i = true;
/*  47 */     setRotation(this.Leg2, 0.0F, 0.0F, 0.0F);
/*  48 */     this.Leg3 = new ModelRenderer(this, 0, 24);
/*  49 */     this.Leg3.func_78789_a(0.0F, 0.0F, 0.0F, 4, 12, 4);
/*  50 */     this.Leg3.func_78793_a(18.0F, 4.0F, -6.0F);
/*  51 */     this.Leg3.func_78787_b(128, 64);
/*  52 */     this.Leg3.field_78809_i = true;
/*  53 */     setRotation(this.Leg3, 0.0F, 0.0F, 0.0F);
/*  54 */     this.Leg4 = new ModelRenderer(this, 0, 24);
/*  55 */     this.Leg4.func_78789_a(0.0F, 0.0F, 0.0F, 4, 12, 4);
/*  56 */     this.Leg4.func_78793_a(18.0F, 4.0F, 2.0F);
/*  57 */     this.Leg4.func_78787_b(128, 64);
/*  58 */     this.Leg4.field_78809_i = true;
/*  59 */     setRotation(this.Leg4, 0.0F, 0.0F, 0.0F);
/*  60 */     this.Crossbar = new ModelRenderer(this, 24, 24);
/*  61 */     this.Crossbar.func_78789_a(0.0F, 0.0F, 0.0F, 24, 4, 4);
/*  62 */     this.Crossbar.func_78793_a(-4.0F, 10.0F, -2.0F);
/*  63 */     this.Crossbar.func_78787_b(128, 64);
/*  64 */     this.Crossbar.field_78809_i = true;
/*  65 */     setRotation(this.Crossbar, 0.0F, 0.0F, 0.0F);
/*  66 */     this.Inkwell = new ModelRenderer(this, 0, 44);
/*  67 */     this.Inkwell.func_78789_a(0.0F, 0.0F, 0.0F, 3, 2, 3);
/*  68 */     this.Inkwell.func_78793_a(-6.0F, -2.0F, 3.0F);
/*  69 */     this.Inkwell.func_78787_b(128, 64);
/*  70 */     this.Inkwell.field_78809_i = true;
/*  71 */     setRotation(this.Inkwell, 0.0F, 0.0F, 0.0F);
/*     */     
/*  73 */     this.ScrollTube = new ModelRenderer(this, 0, 0);
/*  74 */     this.ScrollTube.func_78789_a(-21.0F, -0.5F, -8.0F, 8, 2, 2);
/*  75 */     this.ScrollTube.func_78793_a(-2.0F, -2.0F, 2.0F);
/*  76 */     this.ScrollTube.func_78787_b(128, 64);
/*  77 */     this.ScrollTube.field_78809_i = true;
/*  78 */     setRotation(this.ScrollTube, 0.0F, 10.0F, 0.0F);
/*     */     
/*  80 */     this.ScrollRibbon = new ModelRenderer(this, 0, 4);
/*  81 */     this.ScrollRibbon.func_78789_a(-15.1F, -0.275F, -6.75F, 1, 2, 2);
/*  82 */     this.ScrollRibbon.func_78793_a(-2.0F, -2.0F, 2.0F);
/*  83 */     this.ScrollRibbon.func_78787_b(128, 64);
/*  84 */     this.ScrollRibbon.field_78809_i = true;
/*  85 */     setRotation(this.ScrollRibbon, 0.0F, 10.0F, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderAll()
/*     */   {
/*  93 */     this.Top.func_78785_a(0.0625F);
/*  94 */     this.Leg1.func_78785_a(0.0625F);
/*  95 */     this.Leg2.func_78785_a(0.0625F);
/*  96 */     this.Leg3.func_78785_a(0.0625F);
/*  97 */     this.Leg4.func_78785_a(0.0625F);
/*  98 */     this.Crossbar.func_78785_a(0.0625F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderInkwell()
/*     */   {
/* 105 */     GL11.glPushMatrix();
/* 106 */     GL11.glEnable(3042);
/* 107 */     GL11.glBlendFunc(770, 771);
/* 108 */     this.Inkwell.func_78785_a(0.0625F);
/* 109 */     GL11.glDisable(3042);
/* 110 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void renderScroll(int color)
/*     */   {
/* 115 */     GL11.glPushMatrix();
/* 116 */     this.ScrollTube.func_78785_a(0.0625F);
/* 117 */     Color c = new Color(color);
/* 118 */     GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/* 119 */     GL11.glScalef(1.2F, 1.2F, 1.2F);
/* 120 */     this.ScrollRibbon.func_78785_a(0.0625F);
/* 121 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 122 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 127 */     model.field_78795_f = x;
/* 128 */     model.field_78796_g = y;
/* 129 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelResearchTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */