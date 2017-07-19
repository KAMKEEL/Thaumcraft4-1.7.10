/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelCentrifuge
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer Crossbar;
/*    */   ModelRenderer Dingus1;
/*    */   ModelRenderer Dingus2;
/*    */   ModelRenderer Core;
/*    */   ModelRenderer Top;
/*    */   ModelRenderer Bottom;
/*    */   
/*    */   public ModelCentrifuge()
/*    */   {
/* 20 */     this.field_78090_t = 64;
/* 21 */     this.field_78089_u = 32;
/*    */     
/* 23 */     this.Crossbar = new ModelRenderer(this, 16, 0);
/* 24 */     this.Crossbar.func_78789_a(-4.0F, -1.0F, -1.0F, 8, 2, 2);
/* 25 */     this.Crossbar.func_78793_a(0.0F, 0.0F, 0.0F);
/* 26 */     this.Crossbar.func_78787_b(64, 32);
/* 27 */     this.Crossbar.field_78809_i = true;
/* 28 */     setRotation(this.Crossbar, 0.0F, 0.0F, 0.0F);
/* 29 */     this.Dingus1 = new ModelRenderer(this, 0, 16);
/* 30 */     this.Dingus1.func_78789_a(4.0F, -3.0F, -2.0F, 4, 6, 4);
/* 31 */     this.Dingus1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 32 */     this.Dingus1.func_78787_b(64, 32);
/* 33 */     this.Dingus1.field_78809_i = true;
/* 34 */     setRotation(this.Dingus1, 0.0F, 0.0F, 0.0F);
/* 35 */     this.Dingus2 = new ModelRenderer(this, 0, 16);
/* 36 */     this.Dingus2.func_78789_a(-8.0F, -3.0F, -2.0F, 4, 6, 4);
/* 37 */     this.Dingus2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 38 */     this.Dingus2.func_78787_b(64, 32);
/* 39 */     this.Dingus2.field_78809_i = true;
/* 40 */     setRotation(this.Dingus2, 0.0F, 0.0F, 0.0F);
/* 41 */     this.Core = new ModelRenderer(this, 0, 0);
/* 42 */     this.Core.func_78789_a(-1.5F, -4.0F, -1.5F, 3, 8, 3);
/* 43 */     this.Core.func_78793_a(0.0F, 0.0F, 0.0F);
/* 44 */     this.Core.func_78787_b(64, 32);
/* 45 */     this.Core.field_78809_i = true;
/* 46 */     setRotation(this.Core, 0.0F, 0.0F, 0.0F);
/* 47 */     this.Top = new ModelRenderer(this, 20, 16);
/* 48 */     this.Top.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 4, 8);
/* 49 */     this.Top.func_78793_a(0.0F, 0.0F, 0.0F);
/* 50 */     this.Top.func_78787_b(64, 32);
/* 51 */     this.Top.field_78809_i = true;
/* 52 */     setRotation(this.Top, 0.0F, 0.0F, 0.0F);
/* 53 */     this.Bottom = new ModelRenderer(this, 20, 16);
/* 54 */     this.Bottom.func_78789_a(-4.0F, 4.0F, -4.0F, 8, 4, 8);
/* 55 */     this.Bottom.func_78793_a(0.0F, 0.0F, 0.0F);
/* 56 */     this.Bottom.func_78787_b(64, 32);
/* 57 */     this.Bottom.field_78809_i = true;
/* 58 */     setRotation(this.Bottom, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */ 
/*    */   public void renderBoxes()
/*    */   {
/* 64 */     this.Top.func_78785_a(0.0625F);
/* 65 */     this.Bottom.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   public void renderSpinnyBit()
/*    */   {
/* 70 */     this.Crossbar.func_78785_a(0.0625F);
/* 71 */     this.Dingus1.func_78785_a(0.0625F);
/* 72 */     this.Dingus2.func_78785_a(0.0625F);
/* 73 */     this.Core.func_78785_a(0.0625F);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 78 */     model.field_78795_f = x;
/* 79 */     model.field_78796_g = y;
/* 80 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelCentrifuge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */