/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.BossStatus;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.EnumDifficulty;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entities.ModelEldritchGuardian;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderEldritchGuardian extends RenderLiving
/*    */ {
/*    */   protected ModelEldritchGuardian modelMain;
/* 24 */   private static final ResourceLocation[] skin = { new ResourceLocation("thaumcraft", "textures/models/eldritch_guardian.png"), new ResourceLocation("thaumcraft", "textures/models/eldritch_warden.png") };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public RenderEldritchGuardian(ModelEldritchGuardian par1ModelBiped, float par2)
/*    */   {
/* 31 */     super(par1ModelBiped, par2);
/* 32 */     this.modelMain = par1ModelBiped;
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 37 */     return (entity instanceof EntityEldritchWarden) ? skin[1] : skin[0];
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*    */   {
/* 43 */     if ((par1EntityLiving instanceof EntityEldritchWarden)) {
/* 44 */       BossStatus.func_82824_a((EntityEldritchWarden)par1EntityLiving, false);
/* 45 */       GL11.glScalef(1.5F, 1.5F, 1.5F);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void doRenderLiving(EntityLiving guardian, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 52 */     GL11.glEnable(3042);
/* 53 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 54 */     GL11.glBlendFunc(770, 771);
/* 55 */     float base = 1.0F;
/* 56 */     double d3 = par4 - guardian.field_70129_M;
/*    */     
/* 58 */     if ((guardian instanceof EntityEldritchWarden)) {
/* 59 */       d3 -= guardian.field_70131_O * (((EntityEldritchWarden)guardian).getSpawnTimer() / 150.0F);
/*    */     } else {
/* 61 */       Entity e = Minecraft.func_71410_x().field_71451_h;
/* 62 */       float d6 = e.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 576.0F : 1024.0F;
/* 63 */       float d7 = 256.0F;
/* 64 */       if ((guardian.field_70170_p != null) && (guardian.field_70170_p.field_73011_w.field_76574_g == Config.dimensionOuterId)) {
/* 65 */         base = 1.0F;
/*    */       } else {
/* 67 */         double d8 = guardian.func_70092_e(e.field_70165_t, e.field_70163_u, e.field_70161_v);
/* 68 */         if (d8 < 256.0D) {
/* 69 */           base = 0.6F;
/*    */         } else {
/* 71 */           base = (float)(1.0D - Math.min(d6 - d7, d8 - d7) / (d6 - d7)) * 0.6F;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 76 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, base);
/*    */     
/* 78 */     super.func_76986_a(guardian, par2, d3, par6, par8, par9);
/*    */     
/* 80 */     GL11.glDisable(3042);
/* 81 */     GL11.glAlphaFunc(516, 0.1F);
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
/* 94 */     doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderEldritchGuardian.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */