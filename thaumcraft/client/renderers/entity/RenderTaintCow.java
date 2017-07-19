/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.common.entities.monster.EntityTaintCow;
/*    */ 
/*    */ public class RenderTaintCow extends RenderLiving
/*    */ {
/*    */   public RenderTaintCow(ModelBase par1ModelBase, float par2)
/*    */   {
/* 14 */     super(par1ModelBase, par2);
/*    */   }
/*    */   
/* 17 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/cow.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 20 */     return rl;
/*    */   }
/*    */   
/*    */   public void renderCow(EntityTaintCow par1EntityCow, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 25 */     super.func_76986_a(par1EntityCow, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 31 */     renderCow((EntityTaintCow)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 42 */     renderCow((EntityTaintCow)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderTaintCow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */