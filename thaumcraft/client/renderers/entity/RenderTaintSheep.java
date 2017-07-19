/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.entities.monster.EntityTaintSheep;
/*    */ 
/*    */ public class RenderTaintSheep extends RenderLiving
/*    */ {
/*    */   public RenderTaintSheep(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
/*    */   {
/* 16 */     super(par1ModelBase, par3);
/* 17 */     func_77042_a(par2ModelBase); }
/*    */   
/* 19 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/sheep.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 23 */     return rl;
/*    */   }
/*    */   
/*    */   protected int setWoolColorAndRender(EntityTaintSheep par1EntitySheep, int par2, float par3)
/*    */   {
/* 28 */     if ((par2 == 0) && (!par1EntitySheep.getSheared()))
/*    */     {
/* 30 */       UtilsFX.bindTexture("textures/models/sheep_fur.png");
/* 31 */       float var4 = 1.0F;
/*    */       
/* 33 */       return 1;
/*    */     }
/*    */     
/*    */ 
/* 37 */     return -1;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_40271_a(EntityTaintSheep par1EntitySheep, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 43 */     super.func_76986_a(par1EntitySheep, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3)
/*    */   {
/* 52 */     return setWoolColorAndRender((EntityTaintSheep)par1EntityLiving, par2, par3);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 58 */     func_40271_a((EntityTaintSheep)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 70 */     func_40271_a((EntityTaintSheep)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderTaintSheep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */