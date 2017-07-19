/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelArcaneWorkbench
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer Top;
/*    */   ModelRenderer Base;
/*    */   ModelRenderer Leg1;
/*    */   ModelRenderer Leg2;
/*    */   ModelRenderer Leg3;
/*    */   ModelRenderer Leg4;
/*    */   
/*    */   public ModelArcaneWorkbench()
/*    */   {
/* 20 */     this.field_78090_t = 128;
/* 21 */     this.field_78089_u = 64;
/*    */     
/* 23 */     this.Top = new ModelRenderer(this, 0, 0);
/* 24 */     this.Top.func_78789_a(0.0F, 0.0F, 0.0F, 16, 8, 16);
/* 25 */     this.Top.func_78793_a(-8.0F, 0.0F, -8.0F);
/* 26 */     this.Top.func_78787_b(128, 64);
/* 27 */     this.Top.field_78809_i = true;
/* 28 */     setRotation(this.Top, 0.0F, 0.0F, 0.0F);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 35 */     this.Base = new ModelRenderer(this, 0, 32);
/* 36 */     this.Base.func_78789_a(0.0F, 0.0F, 0.0F, 16, 4, 16);
/* 37 */     this.Base.func_78793_a(-8.0F, 12.0F, -8.0F);
/* 38 */     this.Base.func_78787_b(128, 64);
/* 39 */     this.Base.field_78809_i = true;
/* 40 */     setRotation(this.Base, 0.0F, 0.0F, 0.0F);
/* 41 */     this.Leg1 = new ModelRenderer(this, 72, 0);
/* 42 */     this.Leg1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 4);
/* 43 */     this.Leg1.func_78793_a(3.0F, 8.0F, -7.0F);
/* 44 */     this.Leg1.func_78787_b(128, 64);
/* 45 */     this.Leg1.field_78809_i = true;
/* 46 */     setRotation(this.Leg1, 0.0F, 0.0F, 0.0F);
/* 47 */     this.Leg2 = new ModelRenderer(this, 72, 0);
/* 48 */     this.Leg2.func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 4);
/* 49 */     this.Leg2.func_78793_a(-7.0F, 8.0F, 3.0F);
/* 50 */     this.Leg2.func_78787_b(128, 64);
/* 51 */     this.Leg2.field_78809_i = true;
/* 52 */     setRotation(this.Leg2, 0.0F, 0.0F, 0.0F);
/* 53 */     this.Leg3 = new ModelRenderer(this, 72, 0);
/* 54 */     this.Leg3.func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 4);
/* 55 */     this.Leg3.func_78793_a(3.0F, 8.0F, 3.0F);
/* 56 */     this.Leg3.func_78787_b(128, 64);
/* 57 */     this.Leg3.field_78809_i = true;
/* 58 */     setRotation(this.Leg3, 0.0F, 0.0F, 0.0F);
/* 59 */     this.Leg4 = new ModelRenderer(this, 72, 0);
/* 60 */     this.Leg4.func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 4);
/* 61 */     this.Leg4.func_78793_a(-7.0F, 8.0F, -7.0F);
/* 62 */     this.Leg4.func_78787_b(128, 64);
/* 63 */     this.Leg4.field_78809_i = true;
/* 64 */     setRotation(this.Leg4, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderAll()
/*    */   {
/* 72 */     this.Top.func_78785_a(0.0625F);
/* 73 */     this.Base.func_78785_a(0.0625F);
/* 74 */     this.Leg1.func_78785_a(0.0625F);
/* 75 */     this.Leg2.func_78785_a(0.0625F);
/* 76 */     this.Leg3.func_78785_a(0.0625F);
/* 77 */     this.Leg4.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 88 */     model.field_78795_f = x;
/* 89 */     model.field_78796_g = y;
/* 90 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelArcaneWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */