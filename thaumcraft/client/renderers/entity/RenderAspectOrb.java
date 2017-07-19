/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.entities.EntityAspectOrb;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderAspectOrb
/*    */   extends Render
/*    */ {
/*    */   public RenderAspectOrb()
/*    */   {
/* 24 */     this.field_76989_e = 0.1F;
/* 25 */     this.field_76987_f = 0.5F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderAspectOrb(EntityAspectOrb orb, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 34 */     GL11.glPushMatrix();
/* 35 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/*    */     
/*    */ 
/* 38 */     GL11.glEnable(3042);
/* 39 */     if (orb.getAspect() != null) {
/* 40 */       GL11.glBlendFunc(770, orb.getAspect().getBlend());
/*    */     } else {
/* 42 */       GL11.glBlendFunc(770, 1);
/*    */     }
/*    */     
/* 45 */     UtilsFX.bindTexture(ParticleEngine.particleTexture);
/* 46 */     int i = (int)(System.nanoTime() / 25000000L % 16L);
/* 47 */     Tessellator tessellator = Tessellator.field_78398_a;
/*    */     
/* 49 */     float f2 = i / 16.0F;
/* 50 */     float f3 = (i + 1) / 16.0F;
/* 51 */     float f4 = 0.5F;
/* 52 */     float f5 = 0.5625F;
/*    */     
/* 54 */     float f6 = 1.0F;
/* 55 */     float f7 = 0.5F;
/* 56 */     float f8 = 0.25F;
/*    */     
/* 58 */     int j = orb.func_70070_b(par9);
/* 59 */     int k = j % 65536;
/* 60 */     int l = j / 65536;
/* 61 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/* 62 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 63 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 64 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 65 */     float f11 = 0.1F + 0.3F * ((orb.orbMaxAge - orb.orbAge) / orb.orbMaxAge);
/* 66 */     GL11.glScalef(f11, f11, f11);
/* 67 */     tessellator.func_78382_b();
/* 68 */     if (orb.getAspect() != null) {
/* 69 */       tessellator.func_78384_a(orb.getAspect().getColor(), 128);
/*    */     }
/* 71 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 72 */     tessellator.func_78374_a(0.0F - f7, 0.0F - f8, 0.0D, f2, f5);
/* 73 */     tessellator.func_78374_a(f6 - f7, 0.0F - f8, 0.0D, f3, f5);
/* 74 */     tessellator.func_78374_a(f6 - f7, 1.0F - f8, 0.0D, f3, f4);
/* 75 */     tessellator.func_78374_a(0.0F - f7, 1.0F - f8, 0.0D, f2, f4);
/* 76 */     tessellator.func_78381_a();
/* 77 */     GL11.glDisable(3042);
/*    */     
/* 79 */     GL11.glDisable(32826);
/* 80 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 91 */     renderAspectOrb((EntityAspectOrb)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 97 */     return AbstractClientPlayer.field_110314_b;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderAspectOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */