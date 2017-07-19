/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelJar
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer Core;
/*    */   public ModelRenderer Brine;
/*    */   public ModelRenderer Lid;
/*    */   
/*    */   public ModelJar()
/*    */   {
/* 18 */     this.field_78090_t = 64;
/* 19 */     this.field_78089_u = 32;
/*    */     
/* 21 */     this.Core = new ModelRenderer(this, 0, 0);
/* 22 */     this.Core.func_78789_a(-5.0F, -12.0F, -5.0F, 10, 12, 10);
/* 23 */     this.Core.func_78793_a(0.0F, 0.0F, 0.0F);
/* 24 */     this.Core.func_78787_b(64, 32);
/* 25 */     this.Core.field_78809_i = true;
/* 26 */     setRotation(this.Core, 0.0F, 0.0F, 0.0F);
/*    */     
/* 28 */     this.Brine = new ModelRenderer(this, 0, 0);
/* 29 */     this.Brine.func_78789_a(-4.0F, -11.0F, -4.0F, 8, 10, 8);
/* 30 */     this.Brine.func_78793_a(0.0F, 0.0F, 0.0F);
/* 31 */     this.Brine.func_78787_b(64, 32);
/* 32 */     this.Brine.field_78809_i = true;
/* 33 */     setRotation(this.Brine, 0.0F, 0.0F, 0.0F);
/*    */     
/* 35 */     this.Lid = new ModelRenderer(this, 0, 24);
/* 36 */     this.Lid.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 2, 6);
/* 37 */     this.Lid.func_78793_a(0.0F, -14.0F, 0.0F);
/* 38 */     this.Lid.func_78787_b(64, 32);
/* 39 */     this.Lid.field_78809_i = true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void renderBrine()
/*    */   {
/* 46 */     GL11.glEnable(3042);
/* 47 */     GL11.glBlendFunc(770, 771);
/* 48 */     this.Brine.func_78785_a(0.0625F);
/* 49 */     GL11.glDisable(3042);
/*    */   }
/*    */   
/*    */ 
/*    */   public void renderAll()
/*    */   {
/* 55 */     this.Lid.func_78785_a(0.0625F);
/*    */     
/*    */ 
/* 58 */     GL11.glEnable(3042);
/* 59 */     GL11.glBlendFunc(770, 771);
/* 60 */     this.Core.func_78785_a(0.0625F);
/* 61 */     GL11.glDisable(3042);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*    */   {
/* 68 */     model.field_78795_f = x;
/* 69 */     model.field_78796_g = y;
/* 70 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelJar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */