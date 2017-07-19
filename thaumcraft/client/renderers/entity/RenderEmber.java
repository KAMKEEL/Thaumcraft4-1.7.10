/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.entities.projectile.EntityEmber;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderEmber
/*    */   extends Render
/*    */ {
/*    */   public RenderEmber()
/*    */   {
/* 27 */     this.field_76989_e = 0.0F;
/*    */   }
/*    */   
/* 30 */   private Random random = new Random();
/*    */   
/*    */   public void renderEntityAt(EntityEmber entity, double x, double y, double z, float fq, float pticks)
/*    */   {
/* 34 */     Tessellator tessellator = Tessellator.field_78398_a;
/*    */     
/* 36 */     GL11.glPushMatrix();
/* 37 */     GL11.glTranslated(x, y, z);
/* 38 */     GL11.glEnable(3042);
/* 39 */     GL11.glBlendFunc(770, 1);
/*    */     
/* 41 */     UtilsFX.bindTexture(ParticleEngine.particleTexture);
/* 42 */     int p = (int)(8.0F * (entity.field_70173_aa / entity.duration));
/* 43 */     float f2 = (7 + p) / 16.0F;
/* 44 */     float f3 = f2 + 0.0625F;
/* 45 */     float f4 = 0.5625F;
/* 46 */     float f5 = f4 + 0.0625F;
/*    */     
/* 48 */     float f6 = 1.0F;
/* 49 */     float f7 = 0.5F;
/* 50 */     float f8 = 0.5F;
/*    */     
/* 52 */     float fc = entity.field_70173_aa / entity.duration;
/* 53 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
/*    */     
/* 55 */     float particleScale = 0.25F + fc;
/*    */     
/* 57 */     GL11.glScalef(particleScale, particleScale, particleScale);
/*    */     
/* 59 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 60 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 61 */     tessellator.func_78382_b();
/* 62 */     tessellator.func_78380_c(220);
/* 63 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 64 */     tessellator.func_78369_a(1.0F, 1.0F, 1.0F, 0.9F);
/* 65 */     tessellator.func_78374_a(-f7, -f8, 0.0D, f2, f5);
/* 66 */     tessellator.func_78374_a(f6 - f7, -f8, 0.0D, f3, f5);
/* 67 */     tessellator.func_78374_a(f6 - f7, 1.0F - f8, 0.0D, f3, f4);
/* 68 */     tessellator.func_78374_a(-f7, 1.0F - f8, 0.0D, f2, f4);
/* 69 */     tessellator.func_78381_a();
/* 70 */     GL11.glDisable(3042);
/* 71 */     GL11.glDisable(32826);
/*    */     
/*    */ 
/*    */ 
/* 75 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*    */   {
/* 82 */     renderEntityAt((EntityEmber)entity, d, d1, d2, f, f1);
/*    */   }
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 88 */     return AbstractClientPlayer.field_110314_b;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderEmber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */