/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ public class ModelBanner
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer B1;
/*    */   ModelRenderer B2;
/*    */   ModelRenderer Beam;
/*    */   public ModelRenderer Banner;
/*    */   ModelRenderer Pole;
/*    */   
/*    */   public ModelBanner()
/*    */   {
/* 18 */     this.field_78090_t = 128;
/* 19 */     this.field_78089_u = 64;
/*    */     
/* 21 */     this.B1 = new ModelRenderer(this, 0, 29);
/* 22 */     this.B1.func_78789_a(-5.0F, -7.5F, -1.5F, 2, 3, 3);
/* 23 */     this.B1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 24 */     this.B1.func_78787_b(128, 64);
/* 25 */     this.B1.field_78809_i = true;
/* 26 */     setRotation(this.B1, 0.0F, 0.0F, 0.0F);
/* 27 */     this.B2 = new ModelRenderer(this, 0, 29);
/* 28 */     this.B2.func_78789_a(3.0F, -7.5F, -1.5F, 2, 3, 3);
/* 29 */     this.B2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 30 */     this.B2.func_78787_b(128, 64);
/* 31 */     this.B2.field_78809_i = true;
/* 32 */     setRotation(this.B2, 0.0F, 0.0F, 0.0F);
/* 33 */     this.Beam = new ModelRenderer(this, 30, 0);
/* 34 */     this.Beam.func_78789_a(-7.0F, -7.0F, -1.0F, 14, 2, 2);
/* 35 */     this.Beam.func_78793_a(0.0F, 0.0F, 0.0F);
/* 36 */     this.Beam.func_78787_b(128, 64);
/* 37 */     this.Beam.field_78809_i = true;
/* 38 */     setRotation(this.Beam, 0.0F, 0.0F, 0.0F);
/* 39 */     this.Banner = new ModelRenderer(this, 0, 0);
/* 40 */     this.Banner.func_78789_a(-7.0F, 0.0F, -0.5F, 14, 28, 1);
/* 41 */     this.Banner.func_78793_a(0.0F, -5.0F, 0.0F);
/* 42 */     this.Banner.func_78787_b(128, 64);
/* 43 */     this.Banner.field_78809_i = true;
/* 44 */     setRotation(this.Banner, 0.0F, 0.0F, 0.0F);
/* 45 */     this.Pole = new ModelRenderer(this, 62, 0);
/* 46 */     this.Pole.func_78789_a(0.0F, 0.0F, -1.0F, 2, 31, 2);
/* 47 */     this.Pole.func_78793_a(-1.0F, -7.0F, -2.0F);
/* 48 */     this.Pole.func_78787_b(128, 64);
/* 49 */     this.Pole.field_78809_i = true;
/* 50 */     setRotation(this.Pole, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void renderPole()
/*    */   {
/* 55 */     this.Pole.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   public void renderBeam()
/*    */   {
/* 60 */     this.Beam.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   public void renderTabs()
/*    */   {
/* 65 */     this.B1.func_78785_a(0.0625F);
/* 66 */     this.B2.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   public void renderBanner() {
/* 70 */     this.Banner.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 75 */     model.field_78795_f = x;
/* 76 */     model.field_78796_g = y;
/* 77 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelBanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */