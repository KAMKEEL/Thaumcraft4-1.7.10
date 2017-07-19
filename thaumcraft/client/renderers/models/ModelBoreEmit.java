/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelBoreEmit
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer Knob;
/*    */   ModelRenderer Cross1;
/*    */   ModelRenderer Cross3;
/*    */   ModelRenderer Cross2;
/*    */   ModelRenderer Rod;
/*    */   
/*    */   public ModelBoreEmit()
/*    */   {
/* 19 */     this.field_78090_t = 128;
/* 20 */     this.field_78089_u = 64;
/*    */     
/* 22 */     this.Knob = new ModelRenderer(this, 66, 0);
/* 23 */     this.Knob.func_78789_a(-2.0F, 12.0F, -2.0F, 4, 4, 4);
/* 24 */     this.Knob.func_78793_a(0.0F, 0.0F, 0.0F);
/* 25 */     this.Knob.func_78787_b(128, 64);
/* 26 */     this.Knob.field_78809_i = true;
/* 27 */     setRotation(this.Knob, 0.0F, 0.0F, 0.0F);
/* 28 */     this.Cross1 = new ModelRenderer(this, 56, 16);
/* 29 */     this.Cross1.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 1, 4);
/* 30 */     this.Cross1.func_78793_a(0.0F, 8.0F, 0.0F);
/* 31 */     this.Cross1.func_78787_b(128, 64);
/* 32 */     this.Cross1.field_78809_i = true;
/* 33 */     setRotation(this.Cross1, 0.0F, 0.0F, 0.0F);
/* 34 */     this.Cross3 = new ModelRenderer(this, 56, 16);
/* 35 */     this.Cross3.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 1, 4);
/* 36 */     this.Cross3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 37 */     this.Cross3.func_78787_b(128, 64);
/* 38 */     this.Cross3.field_78809_i = true;
/* 39 */     setRotation(this.Cross3, 0.0F, 0.0F, 0.0F);
/* 40 */     this.Cross2 = new ModelRenderer(this, 56, 24);
/* 41 */     this.Cross2.func_78789_a(-3.0F, 4.0F, -3.0F, 6, 1, 6);
/* 42 */     this.Cross2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 43 */     this.Cross2.func_78787_b(128, 64);
/* 44 */     this.Cross2.field_78809_i = true;
/* 45 */     setRotation(this.Cross2, 0.0F, 0.0F, 0.0F);
/* 46 */     this.Rod = new ModelRenderer(this, 56, 0);
/* 47 */     this.Rod.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 11, 2);
/* 48 */     this.Rod.func_78793_a(0.0F, 0.0F, 0.0F);
/* 49 */     this.Rod.func_78787_b(128, 64);
/* 50 */     this.Rod.field_78809_i = true;
/* 51 */     setRotation(this.Rod, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void render(boolean focus)
/*    */   {
/* 56 */     float f5 = 0.0625F;
/* 57 */     if (focus) this.Knob.func_78785_a(f5);
/* 58 */     this.Cross1.func_78785_a(f5);
/* 59 */     this.Cross3.func_78785_a(f5);
/* 60 */     this.Cross2.func_78785_a(f5);
/* 61 */     this.Rod.func_78785_a(f5);
/*    */   }
/*    */   
/*    */ 
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 67 */     model.field_78795_f = x;
/* 68 */     model.field_78796_g = y;
/* 69 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelBoreEmit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */