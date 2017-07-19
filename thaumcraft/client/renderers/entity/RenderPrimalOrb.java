/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderPrimalOrb
/*     */   extends Render
/*     */ {
/*     */   public RenderPrimalOrb()
/*     */   {
/*  26 */     this.field_76989_e = 0.0F;
/*     */   }
/*     */   
/*     */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks)
/*     */   {
/*  31 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/*  33 */     GL11.glPushMatrix();
/*     */     
/*  35 */     RenderHelper.func_74518_a();
/*  36 */     float f1 = entity.field_70173_aa / 80.0F;
/*  37 */     float f3 = 0.9F;
/*  38 */     float f2 = 0.0F;
/*     */     
/*  40 */     Random random = new Random(entity.func_145782_y());
/*  41 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  42 */     GL11.glDisable(3553);
/*  43 */     GL11.glShadeModel(7425);
/*  44 */     GL11.glEnable(3042);
/*  45 */     GL11.glBlendFunc(770, 1);
/*  46 */     GL11.glDisable(3008);
/*  47 */     GL11.glEnable(2884);
/*  48 */     GL11.glDepthMask(false);
/*  49 */     GL11.glPushMatrix();
/*  50 */     for (int i = 0; i < 12; i++) {
/*  51 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  52 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  53 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
/*  54 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  55 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  56 */       GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
/*     */       
/*  58 */       tessellator.func_78371_b(6);
/*  59 */       float fa = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
/*  60 */       float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
/*  61 */       fa /= 30.0F / (Math.min(entity.field_70173_aa, 10) / 10.0F);
/*  62 */       f4 /= 30.0F / (Math.min(entity.field_70173_aa, 10) / 10.0F);
/*  63 */       tessellator.func_78384_a(16777215, (int)(255.0F * (1.0F - f2)));
/*  64 */       tessellator.func_78377_a(0.0D, 0.0D, 0.0D);
/*  65 */       tessellator.func_78384_a(thaumcraft.common.blocks.BlockCustomOreItem.colors[(i / 2 + 1)], 0);
/*     */       
/*  67 */       tessellator.func_78377_a(-0.866D * f4, fa, -0.5F * f4);
/*  68 */       tessellator.func_78377_a(0.866D * f4, fa, -0.5F * f4);
/*  69 */       tessellator.func_78377_a(0.0D, fa, 1.0F * f4);
/*  70 */       tessellator.func_78377_a(-0.866D * f4, fa, -0.5F * f4);
/*  71 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*  74 */     GL11.glPopMatrix();
/*  75 */     GL11.glDepthMask(true);
/*  76 */     GL11.glDisable(2884);
/*  77 */     GL11.glDisable(3042);
/*  78 */     GL11.glShadeModel(7424);
/*  79 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  80 */     GL11.glEnable(3553);
/*  81 */     GL11.glEnable(3008);
/*  82 */     RenderHelper.func_74519_b();
/*  83 */     GL11.glPopMatrix();
/*     */     
/*  85 */     GL11.glPushMatrix();
/*  86 */     GL11.glTranslated(x, y, z);
/*  87 */     GL11.glEnable(3042);
/*  88 */     GL11.glBlendFunc(770, 1);
/*  89 */     UtilsFX.bindTexture(ParticleEngine.particleTexture);
/*  90 */     f2 = entity.field_70173_aa % 13 / 16.0F;
/*  91 */     f3 = f2 + 0.0624375F;
/*  92 */     float f4 = 0.125F;
/*  93 */     float f5 = f4 + 0.0624375F;
/*  94 */     float f6 = 1.0F;
/*  95 */     float f7 = 0.5F;
/*  96 */     float f8 = 0.5F;
/*  97 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
/*  98 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  99 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 100 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 101 */     tessellator.func_78382_b();
/* 102 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 103 */     tessellator.func_78374_a(0.0F - f7, 0.0F - f8, 0.0D, f2, f5);
/* 104 */     tessellator.func_78374_a(f6 - f7, 0.0F - f8, 0.0D, f3, f5);
/* 105 */     tessellator.func_78374_a(f6 - f7, 1.0F - f8, 0.0D, f3, f4);
/* 106 */     tessellator.func_78374_a(0.0F - f7, 1.0F - f8, 0.0D, f2, f4);
/* 107 */     tessellator.func_78381_a();
/* 108 */     GL11.glDisable(3042);
/* 109 */     GL11.glDisable(32826);
/* 110 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*     */   {
/* 118 */     renderEntityAt(entity, d, d1, d2, f, f1);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 124 */     return AbstractClientPlayer.field_110314_b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderPrimalOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */