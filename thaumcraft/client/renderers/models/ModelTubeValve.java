/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ public class ModelTubeValve
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer ValveRod;
/*    */   ModelRenderer ValveRing;
/*    */   
/*    */   public ModelTubeValve()
/*    */   {
/* 15 */     this.field_78090_t = 64;
/* 16 */     this.field_78089_u = 32;
/*    */     
/* 18 */     this.ValveRod = new ModelRenderer(this, 0, 10);
/* 19 */     this.ValveRod.func_78789_a(-1.0F, 2.0F, -1.0F, 2, 2, 2);
/* 20 */     this.ValveRod.func_78793_a(0.0F, 0.0F, 0.0F);
/* 21 */     this.ValveRod.func_78787_b(64, 32);
/* 22 */     this.ValveRod.field_78809_i = true;
/* 23 */     setRotation(this.ValveRod, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void render()
/*    */   {
/* 28 */     this.ValveRod.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 33 */     model.field_78795_f = x;
/* 34 */     model.field_78796_g = y;
/* 35 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelTubeValve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */