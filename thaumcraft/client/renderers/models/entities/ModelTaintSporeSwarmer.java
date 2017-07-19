/*    */ package thaumcraft.client.renderers.models.entities;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.entities.monster.EntityTaintSporeSwarmer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelTaintSporeSwarmer
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer cube;
/*    */   ModelRenderer cube2;
/*    */   
/*    */   public ModelTaintSporeSwarmer()
/*    */   {
/* 23 */     this.field_78090_t = 64;
/* 24 */     this.field_78089_u = 64;
/*    */     
/* 26 */     this.cube = new ModelRenderer(this, 0, 0);
/* 27 */     this.cube.func_78789_a(-8.0F, 0.0F, -8.0F, 16, 16, 16);
/* 28 */     this.cube.func_78793_a(0.0F, 0.0F, 0.0F);
/*    */     
/* 30 */     this.cube2 = new ModelRenderer(this, 0, 32);
/* 31 */     this.cube2.func_78789_a(-8.0F, -8.0F, -8.0F, 16, 16, 16);
/* 32 */     this.cube2.func_78793_a(0.0F, 16.0F, 0.0F);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*    */   {
/* 38 */     func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
/*    */     
/* 40 */     EntityTaintSporeSwarmer spore = (EntityTaintSporeSwarmer)par1Entity;
/* 41 */     GL11.glPushMatrix();
/* 42 */     GL11.glEnable(3042);
/* 43 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 45 */     GL11.glPushMatrix();
/* 46 */     float f1 = spore.displaySize;
/* 47 */     float f3 = -0.07F;
/* 48 */     float pulse = 0.025F * MathHelper.func_76126_a(spore.field_70173_aa * 0.075F);
/* 49 */     GL11.glTranslatef(0.0F, 1.6F, 0.0F);
/* 50 */     GL11.glScalef(f3 * f1 - pulse, f3 * f1 + pulse, f3 * f1 - pulse);
/* 51 */     GL11.glTranslatef(0.0F, -(f3 * f1 + pulse) / 2.0F, 0.0F);
/* 52 */     int j = 15728880;
/* 53 */     int k = j % 65536;
/* 54 */     int l = j / 65536;
/* 55 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/* 56 */     this.cube.func_78785_a(par7);
/* 57 */     GL11.glPopMatrix();
/* 58 */     GL11.glPushMatrix();
/* 59 */     j = spore.func_70070_b(par7);
/* 60 */     k = j % 65536;
/* 61 */     l = j / 65536;
/* 62 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/* 63 */     this.cube2.func_78785_a(par7);
/* 64 */     GL11.glPopMatrix();
/* 65 */     GL11.glDisable(3042);
/* 66 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*    */   {
/* 72 */     float intensity = 0.02F;
/* 73 */     if (((EntityLivingBase)entity).field_70737_aN > 0) intensity = 0.04F;
/* 74 */     this.cube.field_78795_f = (intensity * MathHelper.func_76126_a(par3 * 0.05F));
/* 75 */     this.cube.field_78808_h = (intensity * MathHelper.func_76126_a(par3 * 0.1F));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/entities/ModelTaintSporeSwarmer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */