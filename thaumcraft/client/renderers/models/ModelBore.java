/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
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
/*    */ 
/*    */ public class ModelBore
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer Base;
/*    */   ModelRenderer Side1;
/*    */   ModelRenderer Side2;
/*    */   ModelRenderer NozCrossbar;
/*    */   ModelRenderer NozFront;
/*    */   ModelRenderer NozMid;
/*    */   
/*    */   public ModelBore()
/*    */   {
/* 30 */     this.field_78090_t = 128;
/* 31 */     this.field_78089_u = 64;
/*    */     
/* 33 */     this.Base = new ModelRenderer(this, 0, 32);
/* 34 */     this.Base.func_78789_a(-6.0F, 0.0F, -6.0F, 12, 2, 12);
/* 35 */     this.Base.func_78793_a(0.0F, 0.0F, 0.0F);
/* 36 */     this.Base.func_78787_b(64, 32);
/* 37 */     this.Base.field_78809_i = true;
/* 38 */     setRotation(this.Base, 0.0F, 0.0F, 0.0F);
/* 39 */     this.Side1 = new ModelRenderer(this, 0, 0);
/* 40 */     this.Side1.func_78789_a(-2.0F, 2.0F, -5.5F, 4, 8, 1);
/* 41 */     this.Side1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 42 */     this.Side1.func_78787_b(64, 32);
/* 43 */     this.Side1.field_78809_i = true;
/* 44 */     setRotation(this.Side1, 0.0F, 0.0F, 0.0F);
/* 45 */     this.Side2 = new ModelRenderer(this, 0, 0);
/* 46 */     this.Side2.func_78789_a(-2.0F, 2.0F, 4.5F, 4, 8, 1);
/* 47 */     this.Side2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 48 */     this.Side2.func_78787_b(64, 32);
/* 49 */     this.Side2.field_78809_i = true;
/* 50 */     setRotation(this.Side2, 0.0F, 0.0F, 0.0F);
/* 51 */     this.NozCrossbar = new ModelRenderer(this, 0, 48);
/* 52 */     this.NozCrossbar.func_78789_a(-1.0F, -1.0F, -6.0F, 2, 2, 12);
/* 53 */     this.NozCrossbar.func_78793_a(0.0F, 8.0F, 0.0F);
/* 54 */     this.NozCrossbar.func_78787_b(64, 32);
/* 55 */     this.NozCrossbar.field_78809_i = true;
/* 56 */     setRotation(this.NozCrossbar, 0.0F, 0.0F, 0.0F);
/* 57 */     this.NozFront = new ModelRenderer(this, 30, 14);
/* 58 */     this.NozFront.func_78789_a(4.0F, -2.5F, -2.5F, 4, 5, 5);
/* 59 */     this.NozFront.func_78793_a(0.0F, 8.0F, 0.0F);
/* 60 */     this.NozFront.func_78787_b(64, 32);
/* 61 */     this.NozFront.field_78809_i = true;
/* 62 */     setRotation(this.NozFront, 0.0F, 0.0F, 0.0F);
/* 63 */     this.NozMid = new ModelRenderer(this, 0, 14);
/* 64 */     this.NozMid.func_78789_a(-2.0F, -4.0F, -4.0F, 6, 8, 8);
/* 65 */     this.NozMid.func_78793_a(0.0F, 8.0F, 0.0F);
/* 66 */     this.NozMid.func_78787_b(64, 32);
/* 67 */     this.NozMid.field_78809_i = true;
/* 68 */     setRotation(this.NozMid, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void renderBase()
/*    */   {
/* 73 */     float f5 = 0.0625F;
/* 74 */     this.Base.func_78785_a(f5);
/* 75 */     this.Side1.func_78785_a(f5);
/* 76 */     this.Side2.func_78785_a(f5);
/* 77 */     this.NozCrossbar.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   public void renderNozzle()
/*    */   {
/* 82 */     float f5 = 0.0625F;
/* 83 */     this.NozFront.func_78785_a(f5);
/* 84 */     this.NozMid.func_78785_a(f5);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 91 */     model.field_78795_f = x;
/* 92 */     model.field_78796_g = y;
/* 93 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelBore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */