/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelManaPod
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer pod0;
/*    */   public ModelRenderer pod1;
/*    */   public ModelRenderer pod2;
/*    */   
/*    */   public ModelManaPod()
/*    */   {
/* 27 */     this.field_78090_t = 32;
/* 28 */     this.field_78089_u = 32;
/*    */     
/* 30 */     this.pod0 = new ModelRenderer(this, 0, 0);
/* 31 */     this.pod0.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 5, 4);
/* 32 */     this.pod0.func_78793_a(0.0F, 0.0F, 0.0F);
/* 33 */     this.pod0.func_78787_b(32, 32);
/* 34 */     this.pod0.field_78809_i = true;
/* 35 */     setRotation(this.pod0, 0.0F, 0.0F, 0.0F);
/* 36 */     this.pod1 = new ModelRenderer(this, 0, 0);
/* 37 */     this.pod1.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 7, 6);
/* 38 */     this.pod1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 39 */     this.pod1.func_78787_b(32, 32);
/* 40 */     this.pod1.field_78809_i = true;
/* 41 */     setRotation(this.pod1, 0.0F, 0.0F, 0.0F);
/* 42 */     this.pod2 = new ModelRenderer(this, 0, 0);
/* 43 */     this.pod2.func_78789_a(-3.5F, 0.0F, -3.5F, 7, 9, 7);
/* 44 */     this.pod2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 45 */     this.pod2.func_78787_b(32, 32);
/* 46 */     this.pod2.field_78809_i = true;
/* 47 */     setRotation(this.pod2, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 52 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 53 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 54 */     this.pod0.func_78785_a(f5);
/* 55 */     this.pod1.func_78785_a(f5);
/* 56 */     this.pod2.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 61 */     model.field_78795_f = x;
/* 62 */     model.field_78796_g = y;
/* 63 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelManaPod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */