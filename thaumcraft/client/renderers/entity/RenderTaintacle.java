/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.BossStatus;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entities.ModelTaintacle;
/*    */ import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderTaintacle
/*    */   extends RenderLiving
/*    */ {
/*    */   public RenderTaintacle(float shadow, int length)
/*    */   {
/* 20 */     super(new ModelTaintacle(length), shadow);
/*    */   }
/*    */   
/* 23 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/taintacle.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 26 */     return rl;
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*    */   {
/* 32 */     if ((par1EntityLiving instanceof EntityTaintacleGiant)) {
/* 33 */       BossStatus.func_82824_a((EntityTaintacleGiant)par1EntityLiving, false);
/* 34 */       GL11.glScalef(1.33F, 1.33F, 1.33F);
/*    */     }
/* 36 */     super.func_77041_b(par1EntityLiving, par2);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderTaintacle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */