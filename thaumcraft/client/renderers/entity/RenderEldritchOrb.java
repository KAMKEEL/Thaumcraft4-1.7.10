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
/*     */ 
/*     */ public class RenderEldritchOrb
/*     */   extends Render
/*     */ {
/*     */   public RenderEldritchOrb()
/*     */   {
/*  27 */     this.field_76989_e = 0.0F;
/*     */   }
/*     */   
/*  30 */   private Random random = new Random();
/*     */   
/*     */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks)
/*     */   {
/*  34 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/*  36 */     this.random.setSeed(187L);
/*     */     
/*  38 */     GL11.glPushMatrix();
/*     */     
/*  40 */     RenderHelper.func_74518_a();
/*  41 */     float f1 = entity.field_70173_aa / 80.0F;
/*  42 */     float f3 = 0.9F;
/*  43 */     float f2 = 0.0F;
/*     */     
/*  45 */     Random random = new Random(entity.func_145782_y());
/*  46 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  47 */     GL11.glDisable(3553);
/*  48 */     GL11.glShadeModel(7425);
/*  49 */     GL11.glEnable(3042);
/*  50 */     GL11.glBlendFunc(770, 1);
/*  51 */     GL11.glDisable(3008);
/*  52 */     GL11.glEnable(2884);
/*  53 */     GL11.glDepthMask(false);
/*  54 */     GL11.glPushMatrix();
/*  55 */     for (int i = 0; i < 12; i++) {
/*  56 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  57 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  58 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
/*  59 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  60 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  61 */       GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
/*     */       
/*  63 */       tessellator.func_78371_b(6);
/*  64 */       float fa = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
/*  65 */       float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
/*  66 */       fa /= 30.0F / (Math.min(entity.field_70173_aa, 10) / 10.0F);
/*  67 */       f4 /= 30.0F / (Math.min(entity.field_70173_aa, 10) / 10.0F);
/*  68 */       tessellator.func_78384_a(16777215, (int)(255.0F * (1.0F - f2)));
/*  69 */       tessellator.func_78377_a(0.0D, 0.0D, 0.0D);
/*  70 */       tessellator.func_78384_a(thaumcraft.common.blocks.BlockCustomOreItem.colors[5], 0);
/*  71 */       tessellator.func_78377_a(-0.866D * f4, fa, -0.5F * f4);
/*  72 */       tessellator.func_78377_a(0.866D * f4, fa, -0.5F * f4);
/*  73 */       tessellator.func_78377_a(0.0D, fa, 1.0F * f4);
/*  74 */       tessellator.func_78377_a(-0.866D * f4, fa, -0.5F * f4);
/*  75 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*  78 */     GL11.glPopMatrix();
/*  79 */     GL11.glDepthMask(true);
/*  80 */     GL11.glDisable(2884);
/*  81 */     GL11.glDisable(3042);
/*  82 */     GL11.glShadeModel(7424);
/*  83 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  84 */     GL11.glEnable(3553);
/*  85 */     GL11.glEnable(3008);
/*  86 */     RenderHelper.func_74519_b();
/*     */     
/*  88 */     GL11.glPopMatrix();
/*     */     
/*  90 */     GL11.glPushMatrix();
/*  91 */     GL11.glTranslated(x, y, z);
/*  92 */     GL11.glEnable(3042);
/*  93 */     GL11.glBlendFunc(770, 771);
/*  94 */     UtilsFX.bindTexture(ParticleEngine.particleTexture);
/*  95 */     f2 = entity.field_70173_aa % 13 / 16.0F;
/*  96 */     f3 = f2 + 0.0624375F;
/*  97 */     float f4 = 0.1875F;
/*  98 */     float f5 = f4 + 0.0624375F;
/*  99 */     float f6 = 1.0F;
/* 100 */     float f7 = 0.5F;
/* 101 */     float f8 = 0.5F;
/* 102 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 103 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 104 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 105 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 106 */     tessellator.func_78382_b();
/* 107 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 108 */     tessellator.func_78374_a(0.0F - f7, 0.0F - f8, 0.0D, f2, f5);
/* 109 */     tessellator.func_78374_a(f6 - f7, 0.0F - f8, 0.0D, f3, f5);
/* 110 */     tessellator.func_78374_a(f6 - f7, 1.0F - f8, 0.0D, f3, f4);
/* 111 */     tessellator.func_78374_a(0.0F - f7, 1.0F - f8, 0.0D, f2, f4);
/* 112 */     tessellator.func_78381_a();
/* 113 */     GL11.glDisable(3042);
/* 114 */     GL11.glDisable(32826);
/* 115 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*     */   {
/* 122 */     renderEntityAt(entity, d, d1, d2, f, f1);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 128 */     return AbstractClientPlayer.field_110314_b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderEldritchOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */