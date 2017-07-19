/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelQuadruped;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import thaumcraft.common.entities.monster.EntityTaintSheep;
/*    */ 
/*    */ public class ModelTaintSheep1 extends ModelQuadruped
/*    */ {
/*    */   private float field_44016_o;
/*    */   
/*    */   public ModelTaintSheep1()
/*    */   {
/* 15 */     super(12, 0.0F);
/* 16 */     this.field_78150_a = new ModelRenderer(this, 0, 0);
/* 17 */     this.field_78150_a.func_78790_a(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
/* 18 */     this.field_78150_a.func_78793_a(0.0F, 6.0F, -8.0F);
/* 19 */     this.field_78148_b = new ModelRenderer(this, 28, 8);
/* 20 */     this.field_78148_b.func_78790_a(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
/* 21 */     this.field_78148_b.func_78793_a(0.0F, 5.0F, 2.0F);
/* 22 */     float var1 = 0.5F;
/* 23 */     this.field_78149_c = new ModelRenderer(this, 0, 16);
/* 24 */     this.field_78149_c.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
/* 25 */     this.field_78149_c.func_78793_a(-3.0F, 12.0F, 7.0F);
/* 26 */     this.field_78146_d = new ModelRenderer(this, 0, 16);
/* 27 */     this.field_78146_d.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
/* 28 */     this.field_78146_d.func_78793_a(3.0F, 12.0F, 7.0F);
/* 29 */     this.field_78147_e = new ModelRenderer(this, 0, 16);
/* 30 */     this.field_78147_e.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
/* 31 */     this.field_78147_e.func_78793_a(-3.0F, 12.0F, -5.0F);
/* 32 */     this.field_78144_f = new ModelRenderer(this, 0, 16);
/* 33 */     this.field_78144_f.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
/* 34 */     this.field_78144_f.func_78793_a(3.0F, 12.0F, -5.0F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
/*    */   {
/* 44 */     super.func_78086_a(par1EntityLiving, par2, par3, par4);
/* 45 */     this.field_78150_a.field_78797_d = (6.0F + ((EntityTaintSheep)par1EntityLiving).func_44003_c(par4) * 9.0F);
/* 46 */     this.field_44016_o = ((EntityTaintSheep)par1EntityLiving).func_44002_d(par4);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity e)
/*    */   {
/* 54 */     super.func_78087_a(par1, par2, par3, par4, par5, par6, e);
/* 55 */     this.field_78150_a.field_78795_f = this.field_44016_o;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelTaintSheep1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */