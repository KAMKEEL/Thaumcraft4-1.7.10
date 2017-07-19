/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.client.renderers.models.entities.ModelTaintSporeSwarmer;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderTaintSporeSwarmer extends RenderLiving
/*    */ {
/*    */   public RenderTaintSporeSwarmer()
/*    */   {
/* 16 */     super(new ModelTaintSporeSwarmer(), 0.25F);
/*    */   }
/*    */   
/* 19 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/taint_spore.png");
/*    */   
/*    */   protected float func_77037_a(EntityLivingBase par1EntityLiving)
/*    */   {
/* 23 */     return 0.0F;
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 28 */     return rl;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderTaintSporeSwarmer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */