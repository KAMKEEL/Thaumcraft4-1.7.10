/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entities.ModelTrunk;
/*    */ import thaumcraft.common.entities.golems.EntityTravelingTrunk;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderTravelingTrunk
/*    */   extends RenderLiving
/*    */ {
/*    */   private ModelTrunk trunkModel;
/*    */   
/*    */   public RenderTravelingTrunk(ModelBase modelbase, float f)
/*    */   {
/* 22 */     super(modelbase, f);
/* 23 */     this.trunkModel = ((ModelTrunk)modelbase);
/*    */   }
/*    */   
/*    */   protected void adjustTrunk(EntityTravelingTrunk entity, float f)
/*    */   {
/* 28 */     int i = 2;
/* 29 */     float f1 = (entity.field_767_b + (entity.field_768_a - entity.field_767_b) * f) / (i * 0.5F + 1.0F);
/* 30 */     float f2 = 1.0F / (f1 + 1.0F);
/* 31 */     float f3 = i;
/* 32 */     f1 = (float)(f1 / 1.5D);
/* 33 */     f2 = (float)(f2 / 1.4D);
/* 34 */     if (entity.getUpgrade() == 1) f3 = (float)(f3 / 1.33D); else { f3 = (float)(f3 / 1.5D);
/*    */     }
/* 36 */     GL11.glScalef(f2 * f3, 0.5F / f2 * f3, f2 * f3);
/* 37 */     GL11.glTranslatef(-0.5F, 0.5F, -0.5F);
/*    */     
/* 39 */     f1 = 1.0F - entity.lidrot;
/* 40 */     f1 = 1.0F - f1 * f1 * f1;
/* 41 */     this.trunkModel.chestLid.field_78795_f = (-(f1 * 3.141593F / 2.0F));
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float f)
/*    */   {
/* 47 */     adjustTrunk((EntityTravelingTrunk)par1EntityLivingBase, f);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
/*    */   {
/* 53 */     super.func_76986_a(entityliving, d, d1, d2, f, f1);
/* 54 */     GL11.glTranslatef(0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 59 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/trunk.png");
/* 60 */   private static final ResourceLocation rl_a = new ResourceLocation("thaumcraft", "textures/models/trunkangry.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 64 */     if (((EntityTravelingTrunk)entity).getAnger() > 0) return rl_a;
/* 65 */     return rl;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderTravelingTrunk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */