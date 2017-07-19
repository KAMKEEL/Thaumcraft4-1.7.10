/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelSpider;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.monster.EntityMindSpider;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class RenderMindSpider extends RenderLiving
/*     */ {
/*     */   public RenderMindSpider()
/*     */   {
/*  22 */     super(new ModelSpider(), 0.0F);
/*  23 */     func_77042_a(new ModelSpider());
/*     */   }
/*     */   
/*     */   protected float setSpiderDeathMaxRotation(EntitySpider par1EntitySpider) {
/*  27 */     return 180.0F;
/*     */   }
/*     */   
/*  30 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/taint_spider.png");
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/*  35 */     return rl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
/*     */   {
/*  42 */     if ((((EntityMindSpider)p_76986_1_).getViewer().length() == 0) || (((EntityMindSpider)p_76986_1_).getViewer().equals(this.field_76990_c.field_78734_h.func_70005_c_())))
/*     */     {
/*     */ 
/*  45 */       super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
/*     */   {
/*  54 */     if ((((EntityMindSpider)p_76986_1_).getViewer().length() == 0) || (((EntityMindSpider)p_76986_1_).getViewer().equals(this.field_76990_c.field_78734_h.func_70005_c_())))
/*     */     {
/*     */ 
/*  57 */       super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76986_a(EntityLivingBase entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
/*     */   {
/*  67 */     if ((((EntityMindSpider)entity).getViewer().length() == 0) || (((EntityMindSpider)entity).getViewer().equals(this.field_76990_c.field_78734_h.func_70005_c_())))
/*     */     {
/*     */ 
/*     */ 
/*  71 */       super.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_77036_a(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
/*     */   {
/*  80 */     func_110777_b(entity);
/*  81 */     GL11.glPushMatrix();
/*  82 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, Math.min(0.1F, entity.field_70173_aa / 100.0F));
/*     */     
/*  84 */     GL11.glDepthMask(false);
/*  85 */     GL11.glEnable(3042);
/*  86 */     GL11.glBlendFunc(770, 771);
/*  87 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  88 */     this.field_77045_g.func_78088_a(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
/*     */     
/*  90 */     GL11.glDisable(3042);
/*  91 */     GL11.glAlphaFunc(516, 0.1F);
/*  92 */     GL11.glPopMatrix();
/*  93 */     GL11.glDepthMask(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_77033_b(EntityLivingBase p_77033_1_, double p_77033_2_, double p_77033_4_, double p_77033_6_)
/*     */   {
/* 100 */     super.func_77033_b(p_77033_1_, p_77033_2_, p_77033_4_, p_77033_6_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int setSpiderEyeBrightness(EntitySpider par1EntitySpider, int par2, float par3)
/*     */   {
/* 108 */     if (par2 != 0) {
/* 109 */       return -1;
/*     */     }
/* 111 */     UtilsFX.bindTexture("textures/models/taint_spider_eyes.png");
/* 112 */     float f1 = 1.0F;
/* 113 */     GL11.glEnable(3042);
/* 114 */     GL11.glDisable(3008);
/* 115 */     GL11.glBlendFunc(1, 1);
/*     */     
/* 117 */     if (par1EntitySpider.func_82150_aj()) {
/* 118 */       GL11.glDepthMask(false);
/*     */     } else {
/* 120 */       GL11.glDepthMask(true);
/*     */     }
/*     */     
/* 123 */     char c0 = 61680;
/* 124 */     int j = c0 % 65536;
/* 125 */     int k = c0 / 65536;
/* 126 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/*     */     
/* 128 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 129 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
/* 130 */     return 1;
/*     */   }
/*     */   
/*     */   protected void scaleSpider(EntityMindSpider par1EntitySpider, float par2)
/*     */   {
/* 135 */     float f1 = par1EntitySpider.spiderScaleAmount();
/* 136 */     GL11.glScalef(f1, f1, f1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*     */   {
/* 146 */     scaleSpider((EntityMindSpider)par1EntityLiving, par2);
/*     */   }
/*     */   
/*     */   protected float func_77037_a(EntityLivingBase par1EntityLiving)
/*     */   {
/* 151 */     return setSpiderDeathMaxRotation((EntitySpider)par1EntityLiving);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3)
/*     */   {
/* 160 */     return setSpiderEyeBrightness((EntitySpider)par1EntityLiving, par2, par3);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderMindSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */