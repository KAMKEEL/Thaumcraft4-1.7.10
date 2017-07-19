/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderElectricOrb
/*    */   extends Render
/*    */ {
/*    */   public RenderElectricOrb()
/*    */   {
/* 26 */     this.field_76989_e = 0.0F;
/*    */   }
/*    */   
/* 29 */   private Random random = new Random();
/*    */   
/*    */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks)
/*    */   {
/* 33 */     Tessellator tessellator = Tessellator.field_78398_a;
/*    */     
/* 35 */     GL11.glPushMatrix();
/* 36 */     GL11.glTranslated(x, y, z);
/* 37 */     GL11.glEnable(3042);
/* 38 */     GL11.glBlendFunc(770, 1);
/*    */     
/* 40 */     UtilsFX.bindTexture(ParticleEngine.particleTexture);
/*    */     
/* 42 */     float f2 = (1 + entity.field_70173_aa % 6) / 8.0F;
/* 43 */     float f3 = f2 + 0.125F;
/* 44 */     float f4 = 0.875F;
/* 45 */     if (((entity instanceof EntityGolemOrb)) && (((EntityGolemOrb)entity).red)) {
/* 46 */       f4 = 0.75F;
/*    */     }
/* 48 */     float f5 = f4 + 0.125F;
/*    */     
/* 50 */     float f6 = 1.0F;
/* 51 */     float f7 = 0.5F;
/* 52 */     float f8 = 0.5F;
/*    */     
/* 54 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
/*    */     
/* 56 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 57 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 58 */     float bob = MathHelper.func_76126_a(entity.field_70173_aa / 5.0F) * 0.2F + 0.2F;
/* 59 */     GL11.glScalef(1.0F + bob, 1.0F + bob, 1.0F + bob);
/* 60 */     tessellator.func_78382_b();
/* 61 */     tessellator.func_78380_c(220);
/* 62 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 63 */     tessellator.func_78374_a(-f7, -f8, 0.0D, f2, f5);
/* 64 */     tessellator.func_78374_a(f6 - f7, -f8, 0.0D, f3, f5);
/* 65 */     tessellator.func_78374_a(f6 - f7, 1.0F - f8, 0.0D, f3, f4);
/* 66 */     tessellator.func_78374_a(-f7, 1.0F - f8, 0.0D, f2, f4);
/* 67 */     tessellator.func_78381_a();
/* 68 */     GL11.glDisable(3042);
/* 69 */     GL11.glDisable(32826);
/*    */     
/*    */ 
/*    */ 
/* 73 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*    */   {
/* 80 */     renderEntityAt(entity, d, d1, d2, f, f1);
/*    */   }
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 86 */     return AbstractClientPlayer.field_110314_b;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderElectricOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */