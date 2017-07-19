/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ public class ModelBrain
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer Shape1;
/*    */   ModelRenderer Shape2;
/*    */   ModelRenderer Shape3;
/*    */   
/*    */   public ModelBrain()
/*    */   {
/* 16 */     this.field_78090_t = 128;
/* 17 */     this.field_78089_u = 64;
/*    */     
/* 19 */     this.Shape1 = new ModelRenderer(this, 0, 0);
/* 20 */     this.Shape1.func_78789_a(0.0F, 0.0F, 0.0F, 12, 10, 16);
/* 21 */     this.Shape1.func_78793_a(-6.0F, 8.0F, -8.0F);
/* 22 */     this.Shape1.func_78787_b(128, 64);
/* 23 */     this.Shape1.field_78809_i = true;
/* 24 */     setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
/* 25 */     this.Shape2 = new ModelRenderer(this, 64, 0);
/* 26 */     this.Shape2.func_78789_a(0.0F, 0.0F, 0.0F, 8, 3, 7);
/* 27 */     this.Shape2.func_78793_a(-4.0F, 18.0F, 0.0F);
/* 28 */     this.Shape2.func_78787_b(128, 64);
/* 29 */     this.Shape2.field_78809_i = true;
/* 30 */     setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
/* 31 */     this.Shape3 = new ModelRenderer(this, 0, 32);
/* 32 */     this.Shape3.func_78789_a(0.0F, 0.0F, 0.0F, 2, 6, 2);
/* 33 */     this.Shape3.func_78793_a(-1.0F, 18.0F, -2.0F);
/* 34 */     this.Shape3.func_78787_b(128, 64);
/* 35 */     this.Shape3.field_78809_i = true;
/* 36 */     setRotation(this.Shape3, 0.4089647F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */ 
/*    */   public void render()
/*    */   {
/* 42 */     this.Shape1.func_78785_a(0.0625F);
/* 43 */     this.Shape2.func_78785_a(0.0625F);
/* 44 */     this.Shape3.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 49 */     model.field_78795_f = x;
/* 50 */     model.field_78796_g = y;
/* 51 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelBrain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */