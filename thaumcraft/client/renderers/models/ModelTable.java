/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ public class ModelTable
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer Top;
/*    */   ModelRenderer Leg1;
/*    */   ModelRenderer Leg2;
/*    */   ModelRenderer Crossbar;
/*    */   
/*    */   public ModelTable()
/*    */   {
/* 17 */     this.field_78090_t = 64;
/* 18 */     this.field_78089_u = 32;
/*    */     
/* 20 */     this.Top = new ModelRenderer(this, 0, 0);
/* 21 */     this.Top.func_78789_a(0.0F, 0.0F, 0.0F, 16, 4, 16);
/* 22 */     this.Top.func_78793_a(-8.0F, 0.0F, -8.0F);
/* 23 */     this.Top.func_78787_b(64, 32);
/* 24 */     this.Top.field_78809_i = true;
/* 25 */     setRotation(this.Top, 0.0F, 0.0F, 0.0F);
/* 26 */     this.Leg1 = new ModelRenderer(this, 0, 20);
/* 27 */     this.Leg1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 8, 4);
/* 28 */     this.Leg1.func_78793_a(2.0F, 4.0F, -2.0F);
/* 29 */     this.Leg1.func_78787_b(64, 32);
/* 30 */     this.Leg1.field_78809_i = true;
/* 31 */     setRotation(this.Leg1, 0.0F, 0.0F, 0.0F);
/* 32 */     this.Leg2 = new ModelRenderer(this, 0, 20);
/* 33 */     this.Leg2.func_78789_a(0.0F, 0.0F, 0.0F, 4, 8, 4);
/* 34 */     this.Leg2.func_78793_a(-6.0F, 4.0F, -2.0F);
/* 35 */     this.Leg2.func_78787_b(64, 32);
/* 36 */     this.Leg2.field_78809_i = true;
/* 37 */     setRotation(this.Leg2, 0.0F, 0.0F, 0.0F);
/* 38 */     this.Crossbar = new ModelRenderer(this, 16, 20);
/* 39 */     this.Crossbar.func_78789_a(0.0F, 0.0F, 0.0F, 16, 4, 8);
/* 40 */     this.Crossbar.func_78793_a(-8.0F, 12.0F, -4.0F);
/* 41 */     this.Crossbar.func_78787_b(64, 32);
/* 42 */     this.Crossbar.field_78809_i = true;
/* 43 */     setRotation(this.Crossbar, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */ 
/*    */   public void renderAll()
/*    */   {
/* 49 */     this.Top.func_78785_a(0.0625F);
/* 50 */     this.Leg1.func_78785_a(0.0625F);
/* 51 */     this.Leg2.func_78785_a(0.0625F);
/* 52 */     this.Crossbar.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 57 */     model.field_78795_f = x;
/* 58 */     model.field_78796_g = y;
/* 59 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */