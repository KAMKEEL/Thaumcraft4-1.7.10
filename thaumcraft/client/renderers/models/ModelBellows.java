/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelBellows
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer BottomPlank;
/*    */   public ModelRenderer MiddlePlank;
/*    */   public ModelRenderer TopPlank;
/*    */   public ModelRenderer Bag;
/*    */   public ModelRenderer Nozzle;
/*    */   
/*    */   public ModelBellows()
/*    */   {
/* 19 */     this.field_78090_t = 128;
/* 20 */     this.field_78089_u = 64;
/*    */     
/* 22 */     this.BottomPlank = new ModelRenderer(this, 0, 0);
/* 23 */     this.BottomPlank.func_78789_a(-6.0F, 0.0F, -6.0F, 12, 2, 12);
/* 24 */     this.BottomPlank.func_78793_a(0.0F, 22.0F, 0.0F);
/* 25 */     this.BottomPlank.func_78787_b(128, 64);
/* 26 */     this.BottomPlank.field_78809_i = true;
/* 27 */     setRotation(this.BottomPlank, 0.0F, 0.0F, 0.0F);
/*    */     
/* 29 */     this.MiddlePlank = new ModelRenderer(this, 0, 0);
/* 30 */     this.MiddlePlank.func_78789_a(-6.0F, -1.0F, -6.0F, 12, 2, 12);
/* 31 */     this.MiddlePlank.func_78793_a(0.0F, 16.0F, 0.0F);
/* 32 */     this.MiddlePlank.func_78787_b(128, 64);
/* 33 */     this.MiddlePlank.field_78809_i = true;
/* 34 */     setRotation(this.MiddlePlank, 0.0F, 0.0F, 0.0F);
/*    */     
/* 36 */     this.TopPlank = new ModelRenderer(this, 0, 0);
/* 37 */     this.TopPlank.func_78789_a(-6.0F, 0.0F, -6.0F, 12, 2, 12);
/* 38 */     this.TopPlank.func_78793_a(0.0F, 8.0F, 0.0F);
/* 39 */     this.TopPlank.func_78787_b(128, 64);
/* 40 */     this.TopPlank.field_78809_i = true;
/* 41 */     setRotation(this.TopPlank, 0.0F, 0.0F, 0.0F);
/*    */     
/* 43 */     this.Bag = new ModelRenderer(this, 48, 0);
/* 44 */     this.Bag.func_78789_a(-10.0F, -12.03333F, -10.0F, 20, 24, 20);
/* 45 */     this.Bag.func_78793_a(0.0F, 16.0F, 0.0F);
/* 46 */     this.Bag.func_78787_b(64, 32);
/* 47 */     this.Bag.field_78809_i = true;
/* 48 */     setRotation(this.Bag, 0.0F, 0.0F, 0.0F);
/*    */     
/* 50 */     this.Nozzle = new ModelRenderer(this, 0, 36);
/* 51 */     this.Nozzle.func_78789_a(-2.0F, -2.0F, 0.0F, 4, 4, 2);
/* 52 */     this.Nozzle.func_78793_a(0.0F, 16.0F, 6.0F);
/* 53 */     this.Nozzle.func_78787_b(128, 64);
/* 54 */     this.Nozzle.field_78809_i = true;
/* 55 */     setRotation(this.Nozzle, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void render()
/*    */   {
/* 60 */     this.MiddlePlank.func_78785_a(0.0625F);
/* 61 */     this.Nozzle.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 66 */     model.field_78795_f = x;
/* 67 */     model.field_78796_g = y;
/* 68 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelBellows.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */