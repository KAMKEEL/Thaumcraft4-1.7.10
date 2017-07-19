/*     */ package thaumcraft.client.renderers.models;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ 
/*     */ 
/*     */ public class ModelBoreBase
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Base1;
/*     */   ModelRenderer Base2;
/*     */   ModelRenderer PillarMid;
/*     */   ModelRenderer Pillar2;
/*     */   ModelRenderer Pillar3;
/*     */   ModelRenderer Pillar4;
/*     */   ModelRenderer Pillar1;
/*     */   ModelRenderer Nozzle1;
/*     */   ModelRenderer Nozzle2;
/*     */   
/*     */   public ModelBoreBase()
/*     */   {
/*  22 */     this.field_78090_t = 128;
/*  23 */     this.field_78089_u = 64;
/*     */     
/*  25 */     this.Base1 = new ModelRenderer(this, 64, 24);
/*  26 */     this.Base1.func_78789_a(-8.0F, 0.0F, -8.0F, 16, 2, 16);
/*  27 */     this.Base1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  28 */     this.Base1.func_78787_b(128, 64);
/*  29 */     this.Base1.field_78809_i = true;
/*  30 */     setRotation(this.Base1, 0.0F, 0.0F, 0.0F);
/*  31 */     this.Base2 = new ModelRenderer(this, 64, 24);
/*  32 */     this.Base2.func_78789_a(-8.0F, 0.0F, -8.0F, 16, 2, 16);
/*  33 */     this.Base2.func_78793_a(0.0F, 14.0F, 0.0F);
/*  34 */     this.Base2.func_78787_b(128, 64);
/*  35 */     this.Base2.field_78809_i = true;
/*  36 */     setRotation(this.Base2, 0.0F, 0.0F, 0.0F);
/*  37 */     this.PillarMid = new ModelRenderer(this, 84, 42);
/*  38 */     this.PillarMid.func_78789_a(-2.5F, 0.0F, -2.5F, 5, 12, 5);
/*  39 */     this.PillarMid.func_78793_a(0.0F, 2.0F, 0.0F);
/*  40 */     this.PillarMid.func_78787_b(128, 64);
/*  41 */     this.PillarMid.field_78809_i = true;
/*  42 */     setRotation(this.PillarMid, 0.0F, 0.0F, 0.0F);
/*  43 */     this.Pillar2 = new ModelRenderer(this, 64, 42);
/*  44 */     this.Pillar2.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
/*  45 */     this.Pillar2.func_78793_a(-5.0F, 2.0F, -5.0F);
/*  46 */     this.Pillar2.func_78787_b(128, 64);
/*  47 */     this.Pillar2.field_78809_i = true;
/*  48 */     setRotation(this.Pillar2, 0.0F, 0.0F, 0.0F);
/*  49 */     this.Pillar3 = new ModelRenderer(this, 64, 42);
/*  50 */     this.Pillar3.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
/*  51 */     this.Pillar3.func_78793_a(-5.0F, 2.0F, 5.0F);
/*  52 */     this.Pillar3.func_78787_b(128, 64);
/*  53 */     this.Pillar3.field_78809_i = true;
/*  54 */     setRotation(this.Pillar3, 0.0F, 0.0F, 0.0F);
/*  55 */     this.Pillar4 = new ModelRenderer(this, 64, 42);
/*  56 */     this.Pillar4.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
/*  57 */     this.Pillar4.func_78793_a(5.0F, 2.0F, 5.0F);
/*  58 */     this.Pillar4.func_78787_b(128, 64);
/*  59 */     this.Pillar4.field_78809_i = true;
/*  60 */     setRotation(this.Pillar4, 0.0F, 0.0F, 0.0F);
/*  61 */     this.Pillar1 = new ModelRenderer(this, 64, 42);
/*  62 */     this.Pillar1.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
/*  63 */     this.Pillar1.func_78793_a(5.0F, 2.0F, -5.0F);
/*  64 */     this.Pillar1.func_78787_b(128, 64);
/*  65 */     this.Pillar1.field_78809_i = true;
/*  66 */     setRotation(this.Pillar1, 0.0F, 0.0F, 0.0F);
/*  67 */     this.Nozzle1 = new ModelRenderer(this, 106, 42);
/*  68 */     this.Nozzle1.func_78789_a(2.5F, -2.0F, -2.0F, 5, 4, 4);
/*  69 */     this.Nozzle1.func_78793_a(0.0F, 8.0F, 0.0F);
/*  70 */     this.Nozzle1.func_78787_b(128, 64);
/*  71 */     this.Nozzle1.field_78809_i = true;
/*  72 */     setRotation(this.Nozzle1, 0.0F, 0.0F, 0.0F);
/*  73 */     this.Nozzle2 = new ModelRenderer(this, 106, 51);
/*  74 */     this.Nozzle2.func_78789_a(7.0F, -2.5F, -2.5F, 1, 5, 5);
/*  75 */     this.Nozzle2.func_78793_a(0.0F, 8.0F, 0.0F);
/*  76 */     this.Nozzle2.func_78787_b(128, 64);
/*  77 */     this.Nozzle2.field_78809_i = true;
/*  78 */     setRotation(this.Nozzle2, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void render()
/*     */   {
/*  84 */     float f5 = 0.0625F;
/*  85 */     this.Base1.func_78785_a(f5);
/*  86 */     this.Base2.func_78785_a(f5);
/*  87 */     this.PillarMid.func_78785_a(f5);
/*  88 */     this.Pillar2.func_78785_a(f5);
/*  89 */     this.Pillar3.func_78785_a(f5);
/*  90 */     this.Pillar4.func_78785_a(f5);
/*  91 */     this.Pillar1.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   public void renderNozzle()
/*     */   {
/*  96 */     float f5 = 0.0625F;
/*  97 */     this.Nozzle1.func_78785_a(f5);
/*  98 */     this.Nozzle2.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 103 */     model.field_78795_f = x;
/* 104 */     model.field_78796_g = y;
/* 105 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelBoreBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */