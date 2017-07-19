/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.entities.monster.EntityThaumicSlime;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderThaumicSlime
/*    */   extends RenderLiving
/*    */ {
/*    */   private ModelBase scaleAmount;
/* 19 */   private static final ResourceLocation field_110897_a = new ResourceLocation("thaumcraft", "textures/models/tslime.png");
/*    */   
/*    */   public RenderThaumicSlime(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
/*    */   {
/* 23 */     super(par1ModelBase, par3);
/* 24 */     this.scaleAmount = par2ModelBase;
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 29 */     return field_110897_a;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected int shouldSlimeRenderPass(EntityThaumicSlime par1EntitySlime, int par2, float par3)
/*    */   {
/* 37 */     if (par1EntitySlime.func_82150_aj())
/*    */     {
/* 39 */       return 0;
/*    */     }
/* 41 */     if (par2 == 0)
/*    */     {
/* 43 */       func_77042_a(this.scaleAmount);
/* 44 */       GL11.glEnable(2977);
/* 45 */       GL11.glEnable(3042);
/* 46 */       GL11.glBlendFunc(770, 771);
/* 47 */       return 1;
/*    */     }
/*    */     
/*    */ 
/* 51 */     if (par2 == 1)
/*    */     {
/* 53 */       GL11.glDisable(3042);
/* 54 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     }
/*    */     
/* 57 */     return -1;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void scaleSlime(EntityThaumicSlime par1EntitySlime, float par2)
/*    */   {
/* 66 */     float f1 = (float)Math.sqrt(par1EntitySlime.getSlimeSize());
/* 67 */     float f2 = (par1EntitySlime.field_70812_c + (par1EntitySlime.field_70811_b - par1EntitySlime.field_70812_c) * par2) / (f1 * 0.25F + 1.0F);
/* 68 */     float f3 = 1.0F / (f2 + 1.0F);
/* 69 */     GL11.glScalef(f3 * f1 + 0.1F, 1.0F / f3 * f1 + 0.1F, f3 * f1 + 0.1F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*    */   {
/* 79 */     scaleSlime((EntityThaumicSlime)par1EntityLiving, par2);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3)
/*    */   {
/* 88 */     return shouldSlimeRenderPass((EntityThaumicSlime)par1EntityLiving, par2, par3);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderThaumicSlime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */