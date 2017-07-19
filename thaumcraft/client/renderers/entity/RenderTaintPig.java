/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.common.entities.monster.EntityTaintPig;
/*    */ 
/*    */ public class RenderTaintPig
/*    */   extends RenderLiving
/*    */ {
/*    */   public RenderTaintPig(ModelBase par1ModelBase, float par3)
/*    */   {
/* 14 */     super(par1ModelBase, par3);
/*    */   }
/*    */   
/* 17 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/pig.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 20 */     return rl;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_40286_a(EntityTaintPig par1EntityPig, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 26 */     super.func_76986_a(par1EntityPig, par2, par4, par6, par8, par9);
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
/* 38 */     func_40286_a((EntityTaintPig)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderTaintPig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */