/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entities.ModelTaintSpore;
/*    */ import thaumcraft.common.entities.monster.EntityTaintSpore;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderTaintSpore
/*    */   extends RenderLiving
/*    */ {
/*    */   public RenderTaintSpore()
/*    */   {
/* 21 */     super(new ModelTaintSpore(), 0.25F);
/*    */   }
/*    */   
/* 24 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/taint_spore.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 28 */     return rl;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*    */   {
/* 35 */     scaleSpore((EntityTaintSpore)par1EntityLiving, par2);
/*    */   }
/*    */   
/*    */   protected void scaleSpore(EntityTaintSpore spore, float par2)
/*    */   {
/* 40 */     float f1 = spore.displaySize;
/* 41 */     if (spore.displaySize < spore.getSporeSize()) f1 += 0.02F * par2;
/* 42 */     float f3 = -0.12F;
/* 43 */     float pulse = 0.025F * MathHelper.func_76126_a(spore.field_70173_aa * 0.075F);
/* 44 */     GL11.glScalef(f3 * f1 - pulse, f3 * f1 + pulse, f3 * f1 - pulse);
/*    */   }
/*    */   
/*    */   protected float func_77037_a(EntityLivingBase par1EntityLiving)
/*    */   {
/* 49 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderTaintSpore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */