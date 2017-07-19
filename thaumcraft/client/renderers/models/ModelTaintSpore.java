/*    */ package thaumcraft.client.renderers.models;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelTaintSpore
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer cube;
/*    */   
/*    */   public ModelTaintSpore()
/*    */   {
/* 19 */     this.field_78090_t = 64;
/* 20 */     this.field_78089_u = 64;
/*    */     
/* 22 */     this.cube = new ModelRenderer(this, 0, 0);
/*    */     
/* 24 */     this.cube.func_78789_a(-6.0F, 2.0F, -6.0F, 12, 12, 12);
/* 25 */     this.cube.func_78789_a(-8.0F, 0.0F, -8.0F, 16, 16, 16);
/* 26 */     this.cube.func_78793_a(0.0F, 24.0F, 0.0F);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*    */   {
/* 32 */     func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
/* 33 */     GL11.glPushMatrix();
/* 34 */     GL11.glEnable(3042);
/* 35 */     GL11.glBlendFunc(770, 771);
/* 36 */     this.cube.func_78785_a(par7);
/* 37 */     GL11.glDisable(3042);
/* 38 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*    */   {
/* 44 */     float intensity = 0.02F;
/* 45 */     if (((EntityLivingBase)entity).field_70737_aN > 0) intensity = 0.04F;
/* 46 */     this.cube.field_78795_f = (intensity * MathHelper.func_76126_a(par3 * 0.05F));
/* 47 */     this.cube.field_78808_h = (intensity * MathHelper.func_76126_a(par3 * 0.1F));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelTaintSpore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */