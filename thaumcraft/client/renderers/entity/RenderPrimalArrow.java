/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalArrow;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderPrimalArrow
/*     */   extends Render
/*     */ {
/*  24 */   private static final ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");
/*  25 */   int size1 = 0;
/*  26 */   int size2 = 0;
/*     */   
/*     */   public void renderArrow(EntityPrimalArrow arrow, double x, double y, double z, float ns, float prt)
/*     */   {
/*  30 */     Color color = new Color(thaumcraft.common.blocks.BlockCustomOreItem.colors[(arrow.type + 1)]);
/*     */     
/*  32 */     func_110777_b(arrow);
/*  33 */     GL11.glPushMatrix();
/*     */     
/*  35 */     GL11.glEnable(3042);
/*  36 */     GL11.glBlendFunc(770, 1);
/*     */     
/*     */ 
/*  39 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  40 */     GL11.glRotatef(arrow.field_70126_B + (arrow.field_70177_z - arrow.field_70126_B) * prt - 90.0F, 0.0F, 1.0F, 0.0F);
/*  41 */     GL11.glRotatef(arrow.field_70127_C + (arrow.field_70125_A - arrow.field_70127_C) * prt, 0.0F, 0.0F, 1.0F);
/*  42 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  43 */     byte b0 = 0;
/*  44 */     float f2 = 0.0F;
/*  45 */     float f3 = 0.5F;
/*  46 */     float f4 = (0 + b0 * 10) / 32.0F;
/*  47 */     float f5 = (5 + b0 * 10) / 32.0F;
/*  48 */     float f6 = 0.0F;
/*  49 */     float f7 = 0.15625F;
/*  50 */     float f8 = (5 + b0 * 10) / 32.0F;
/*  51 */     float f9 = (10 + b0 * 10) / 32.0F;
/*  52 */     float f10 = 0.05625F;
/*  53 */     GL11.glEnable(32826);
/*  54 */     float f11 = arrow.field_70249_b - prt;
/*     */     
/*  56 */     if (f11 > 0.0F)
/*     */     {
/*  58 */       float f12 = -MathHelper.func_76126_a(f11 * 3.0F) * f11;
/*  59 */       GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */     
/*  62 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, (100.0F - arrow.field_70252_j) / 100.0F);
/*     */     
/*  64 */     GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
/*  65 */     GL11.glScalef(f10, f10, f10);
/*  66 */     GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
/*  67 */     GL11.glNormal3f(f10, 0.0F, 0.0F);
/*  68 */     tessellator.func_78382_b();
/*  69 */     tessellator.func_78374_a(-7.0D, -2.0D, -2.0D, f6, f8);
/*  70 */     tessellator.func_78374_a(-7.0D, -2.0D, 2.0D, f7, f8);
/*  71 */     tessellator.func_78374_a(-7.0D, 2.0D, 2.0D, f7, f9);
/*  72 */     tessellator.func_78374_a(-7.0D, 2.0D, -2.0D, f6, f9);
/*  73 */     tessellator.func_78381_a();
/*  74 */     GL11.glNormal3f(-f10, 0.0F, 0.0F);
/*  75 */     tessellator.func_78382_b();
/*  76 */     tessellator.func_78374_a(-7.0D, 2.0D, -2.0D, f6, f8);
/*  77 */     tessellator.func_78374_a(-7.0D, 2.0D, 2.0D, f7, f8);
/*  78 */     tessellator.func_78374_a(-7.0D, -2.0D, 2.0D, f7, f9);
/*  79 */     tessellator.func_78374_a(-7.0D, -2.0D, -2.0D, f6, f9);
/*  80 */     tessellator.func_78381_a();
/*     */     
/*  82 */     for (int i = 0; i < 4; i++)
/*     */     {
/*  84 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  85 */       GL11.glNormal3f(0.0F, 0.0F, f10);
/*  86 */       tessellator.func_78382_b();
/*  87 */       tessellator.func_78374_a(-8.0D, -2.0D, 0.0D, f2, f4);
/*  88 */       tessellator.func_78374_a(8.0D, -2.0D, 0.0D, f3, f4);
/*  89 */       tessellator.func_78374_a(8.0D, 2.0D, 0.0D, f3, f5);
/*  90 */       tessellator.func_78374_a(-8.0D, 2.0D, 0.0D, f2, f5);
/*  91 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*  94 */     GL11.glDisable(32826);
/*     */     
/*  96 */     GL11.glDisable(3042);
/*  97 */     GL11.glPopMatrix();
/*     */     
/*  99 */     float f1 = ActiveRenderInfo.field_74588_d;
/* 100 */     f2 = ActiveRenderInfo.field_74589_e;
/* 101 */     f3 = ActiveRenderInfo.field_74586_f;
/* 102 */     f4 = ActiveRenderInfo.field_74587_g;
/* 103 */     f5 = ActiveRenderInfo.field_74596_h;
/*     */     
/*     */ 
/* 106 */     f10 = 0.5F;
/* 107 */     GL11.glPushMatrix();
/* 108 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*     */     
/* 110 */     GL11.glDepthMask(false);
/* 111 */     GL11.glEnable(3042);
/* 112 */     if (arrow.type < 5) {
/* 113 */       GL11.glBlendFunc(770, 1);
/*     */     } else {
/* 115 */       GL11.glBlendFunc(770, 771);
/*     */     }
/*     */     
/* 118 */     UtilsFX.bindTexture("textures/misc/wisp.png");
/*     */     
/* 120 */     int i = arrow.field_70173_aa % 16;
/*     */     
/* 122 */     float size4 = this.size1 * 4;
/* 123 */     float float_sizeMinus0_01 = this.size1 - 0.01F;
/* 124 */     float float_texNudge = 1.0F / (this.size1 * this.size1 * 2.0F);
/* 125 */     float float_reciprocal = 1.0F / this.size1;
/*     */     
/* 127 */     float x0 = (i % 4 * this.size1 + 0.0F) / size4;
/* 128 */     float x1 = (i % 4 * this.size1 + float_sizeMinus0_01) / size4;
/* 129 */     float x2 = (i / 4 * this.size1 + 0.0F) / size4;
/* 130 */     float x3 = (i / 4 * this.size1 + float_sizeMinus0_01) / size4;
/*     */     
/* 132 */     tessellator.func_78382_b();
/* 133 */     tessellator.func_78380_c(240);
/* 134 */     tessellator.func_78369_a(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, (100.0F - arrow.field_70252_j) / 100.0F);
/* 135 */     tessellator.func_78374_a(-f1 * f10 - f4 * f10, -f2 * f10, -f3 * f10 - f5 * f10, x1, x3);
/* 136 */     tessellator.func_78374_a(-f1 * f10 + f4 * f10, f2 * f10, -f3 * f10 + f5 * f10, x1, x2);
/* 137 */     tessellator.func_78374_a(f1 * f10 + f4 * f10, f2 * f10, f3 * f10 + f5 * f10, x0, x2);
/* 138 */     tessellator.func_78374_a(f1 * f10 - f4 * f10, -f2 * f10, f3 * f10 - f5 * f10, x0, x3);
/* 139 */     tessellator.func_78381_a();
/*     */     
/* 141 */     GL11.glDisable(3042);
/* 142 */     GL11.glDepthMask(true);
/* 143 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation getArrowTextures(EntityPrimalArrow par1EntityArrow)
/*     */   {
/* 149 */     return arrowTextures;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/* 157 */     return getArrowTextures((EntityPrimalArrow)par1Entity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 168 */     if (this.size1 == 0) this.size1 = UtilsFX.getTextureSize("textures/misc/wisp.png", 64);
/* 169 */     renderArrow((EntityPrimalArrow)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderPrimalArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */