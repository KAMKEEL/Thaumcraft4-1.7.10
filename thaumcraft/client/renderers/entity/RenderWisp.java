/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.monster.EntityWisp;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderWisp
/*     */   extends Render
/*     */ {
/*     */   public RenderWisp()
/*     */   {
/*  31 */     this.field_76989_e = 0.0F;
/*     */   }
/*     */   
/*  34 */   int size1 = 0;
/*  35 */   int size2 = 0;
/*     */   
/*     */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks)
/*     */   {
/*  39 */     if (((EntityLiving)entity).func_110143_aJ() <= 0.0F) { return;
/*     */     }
/*  41 */     float f1 = ActiveRenderInfo.field_74588_d;
/*  42 */     float f2 = ActiveRenderInfo.field_74589_e;
/*  43 */     float f3 = ActiveRenderInfo.field_74586_f;
/*  44 */     float f4 = ActiveRenderInfo.field_74587_g;
/*  45 */     float f5 = ActiveRenderInfo.field_74596_h;
/*     */     
/*     */ 
/*  48 */     float f10 = 1.0F;
/*  49 */     float f11 = (float)x;
/*  50 */     float f12 = (float)y + 0.45F;
/*  51 */     float f13 = (float)z;
/*     */     
/*  53 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/*  55 */     Color color = new Color(0);
/*     */     
/*  57 */     if (Aspect.getAspect(((EntityWisp)entity).getType()) != null) {
/*  58 */       color = new Color(Aspect.getAspect(((EntityWisp)entity).getType()).getColor());
/*     */     }
/*     */     
/*  61 */     GL11.glPushMatrix();
/*  62 */     GL11.glDepthMask(false);
/*  63 */     GL11.glEnable(3042);
/*  64 */     GL11.glBlendFunc(770, 1);
/*     */     
/*  66 */     UtilsFX.bindTexture("textures/misc/wisp.png");
/*     */     
/*  68 */     int i = entity.field_70173_aa % 16;
/*     */     
/*  70 */     float size4 = this.size1 * 4;
/*  71 */     float float_sizeMinus0_01 = this.size1 - 0.01F;
/*  72 */     float float_texNudge = 1.0F / (this.size1 * this.size1 * 2.0F);
/*  73 */     float float_reciprocal = 1.0F / this.size1;
/*     */     
/*  75 */     float x0 = (i % 4 * this.size1 + 0.0F) / size4;
/*  76 */     float x1 = (i % 4 * this.size1 + float_sizeMinus0_01) / size4;
/*  77 */     float x2 = (i / 4 * this.size1 + 0.0F) / size4;
/*  78 */     float x3 = (i / 4 * this.size1 + float_sizeMinus0_01) / size4;
/*     */     
/*  80 */     tessellator.func_78382_b();
/*  81 */     tessellator.func_78380_c(240);
/*  82 */     if (((EntityLiving)entity).field_70737_aN > 0) {
/*  83 */       tessellator.func_78369_a(1.0F, color.getGreen() / 300.0F, color.getBlue() / 300.0F, 1.0F);
/*     */     } else
/*  85 */       tessellator.func_78369_a(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
/*  86 */     tessellator.func_78374_a(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, x1, x3);
/*  87 */     tessellator.func_78374_a(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10, x1, x2);
/*  88 */     tessellator.func_78374_a(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, x0, x2);
/*  89 */     tessellator.func_78374_a(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10, x0, x3);
/*  90 */     tessellator.func_78381_a();
/*     */     
/*  92 */     GL11.glDisable(3042);
/*  93 */     GL11.glDepthMask(true);
/*  94 */     GL11.glPopMatrix();
/*     */     
/*  96 */     GL11.glPushMatrix();
/*  97 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  98 */     GL11.glDepthMask(false);
/*  99 */     GL11.glEnable(3042);
/* 100 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 102 */     UtilsFX.bindTexture(ParticleEngine.particleTexture);
/*     */     
/* 104 */     int qq = entity.field_70173_aa % 16;
/*     */     
/*     */ 
/* 107 */     float size8 = 16.0F;
/* 108 */     x0 = qq / size8;
/* 109 */     x1 = (qq + 1) / size8;
/* 110 */     x2 = 5.0F / size8;
/* 111 */     x3 = 6.0F / size8;
/*     */     
/* 113 */     float var11 = MathHelper.func_76126_a((entity.field_70173_aa + pticks) / 10.0F) * 0.1F;
/*     */     
/* 115 */     f10 = 0.4F + var11;
/*     */     
/* 117 */     tessellator.func_78382_b();
/* 118 */     tessellator.func_78380_c(240);
/*     */     
/* 120 */     tessellator.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F);
/* 121 */     tessellator.func_78374_a(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, x1, x3);
/* 122 */     tessellator.func_78374_a(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10, x1, x2);
/* 123 */     tessellator.func_78374_a(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, x0, x2);
/* 124 */     tessellator.func_78374_a(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10, x0, x3);
/* 125 */     tessellator.func_78381_a();
/*     */     
/* 127 */     GL11.glDisable(3042);
/* 128 */     GL11.glDepthMask(true);
/* 129 */     GL11.glAlphaFunc(516, 0.1F);
/* 130 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*     */   {
/* 140 */     if (this.size1 == 0) {
/* 141 */       this.size1 = UtilsFX.getTextureSize("textures/misc/wisp.png", 64);
/*     */     }
/* 143 */     renderEntityAt(entity, d, d1, d2, f, f1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 150 */     return AbstractClientPlayer.field_110314_b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderWisp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */