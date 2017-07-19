/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entities.ModelEldritchCrab;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderEldritchCrab
/*    */   extends RenderLiving
/*    */ {
/* 19 */   private static final ResourceLocation[] skin = { new ResourceLocation("thaumcraft", "textures/models/crab.png"), new ResourceLocation("thaumcraft", "textures/models/craboverlay.png") };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public RenderEldritchCrab()
/*    */   {
/* 26 */     super(new ModelEldritchCrab(), 1.0F);
/* 27 */     func_77042_a(new ModelEldritchCrab());
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 32 */     return skin[0];
/*    */   }
/*    */   
/*    */   public void renderCrab(EntityLiving crab, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 37 */     super.func_76986_a(crab, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3)
/*    */   {
/* 42 */     if (par2 != 0) {
/* 43 */       return -1;
/*    */     }
/* 45 */     func_110776_a(skin[1]);
/* 46 */     GL11.glEnable(3042);
/* 47 */     GL11.glBlendFunc(770, 771);
/* 48 */     if (par1EntityLiving.func_82150_aj()) {
/* 49 */       GL11.glDepthMask(false);
/*    */     } else {
/* 51 */       GL11.glDepthMask(true);
/*    */     }
/*    */     
/* 54 */     char c0 = 'È';
/* 55 */     int j = c0 % 65536;
/* 56 */     int k = c0 / 65536;
/* 57 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/* 58 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 59 */     return 1;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 66 */     renderCrab((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderEldritchCrab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */