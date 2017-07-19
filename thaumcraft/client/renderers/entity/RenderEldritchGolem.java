/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.BossStatus;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entities.ModelEldritchGolem;
/*    */ import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderEldritchGolem
/*    */   extends RenderLiving
/*    */ {
/*    */   protected ModelEldritchGolem modelMain;
/* 25 */   private static final ResourceLocation skin = new ResourceLocation("thaumcraft", "textures/models/eldritch_golem.png");
/*    */   
/*    */   public RenderEldritchGolem(ModelEldritchGolem par1ModelBiped, float par2)
/*    */   {
/* 29 */     super(par1ModelBiped, par2);
/* 30 */     this.modelMain = par1ModelBiped;
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 35 */     return skin;
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*    */   {
/* 41 */     BossStatus.func_82824_a((EntityEldritchGolem)par1EntityLiving, false);
/* 42 */     GL11.glScalef(2.15F, 2.15F, 2.15F);
/*    */   }
/*    */   
/*    */ 
/*    */   public void doRenderLiving(EntityLiving golem, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 48 */     GL11.glEnable(3042);
/* 49 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 50 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 52 */     double d3 = par4 - golem.field_70129_M;
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 58 */     super.func_76986_a(golem, par2, d3, par6, par8, par9);
/*    */     
/* 60 */     GL11.glDisable(3042);
/* 61 */     GL11.glAlphaFunc(516, 0.1F);
/*    */   }
/*    */   
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
/* 74 */     doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderEldritchGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */