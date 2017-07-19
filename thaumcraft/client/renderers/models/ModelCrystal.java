/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ public class ModelCrystal
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer Crystal;
/*    */   
/*    */   public ModelCrystal()
/*    */   {
/* 14 */     this.field_78090_t = 64;
/* 15 */     this.field_78089_u = 32;
/*    */     
/* 17 */     this.Crystal = new ModelRenderer(this, 0, 0);
/* 18 */     this.Crystal.func_78789_a(-16.0F, -16.0F, 0.0F, 16, 16, 16);
/* 19 */     this.Crystal.func_78793_a(0.0F, 32.0F, 0.0F);
/* 20 */     this.Crystal.func_78787_b(64, 32);
/* 21 */     this.Crystal.field_78809_i = true;
/* 22 */     setRotation(this.Crystal, 0.7071F, 0.0F, 0.7071F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void render()
/*    */   {
/* 29 */     this.Crystal.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   public void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 34 */     model.field_78795_f = x;
/* 35 */     model.field_78796_g = y;
/* 36 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelCrystal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */