/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.projectile.EntityArrow;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderDart
/*    */   extends Render
/*    */ {
/*    */   public void renderArrow(EntityArrow par1EntityArrow, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 21 */     func_110777_b(par1EntityArrow);
/* 22 */     GL11.glPushMatrix();
/* 23 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/* 24 */     GL11.glRotatef(par1EntityArrow.field_70126_B + (par1EntityArrow.field_70177_z - par1EntityArrow.field_70126_B) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
/* 25 */     GL11.glRotatef(par1EntityArrow.field_70127_C + (par1EntityArrow.field_70125_A - par1EntityArrow.field_70127_C) * par9, 0.0F, 0.0F, 1.0F);
/* 26 */     Tessellator var10 = Tessellator.field_78398_a;
/* 27 */     byte var11 = 0;
/* 28 */     float var12 = 0.0F;
/* 29 */     float var13 = 0.5F;
/* 30 */     float var14 = (0 + var11 * 10) / 32.0F;
/* 31 */     float var15 = (5 + var11 * 10) / 32.0F;
/* 32 */     float var16 = 0.0F;
/* 33 */     float var17 = 0.15625F;
/* 34 */     float var18 = (5 + var11 * 10) / 32.0F;
/* 35 */     float var19 = (10 + var11 * 10) / 32.0F;
/* 36 */     float var20 = 0.025625F;
/* 37 */     GL11.glEnable(32826);
/* 38 */     float var21 = par1EntityArrow.field_70249_b - par9;
/*    */     
/* 40 */     if (var21 > 0.0F)
/*    */     {
/* 42 */       float var22 = -MathHelper.func_76126_a(var21 * 3.0F) * var21;
/* 43 */       GL11.glRotatef(var22, 0.0F, 0.0F, 1.0F);
/*    */     }
/* 45 */     GL11.glColor3f(0.5F, 0.5F, 0.6F);
/* 46 */     GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
/* 47 */     GL11.glScalef(var20 * 0.75F, var20, var20);
/* 48 */     GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
/* 49 */     GL11.glNormal3f(var20, 0.0F, 0.0F);
/* 50 */     var10.func_78382_b();
/* 51 */     var10.func_78374_a(-7.0D, -2.0D, -2.0D, var16, var18);
/* 52 */     var10.func_78374_a(-7.0D, -2.0D, 2.0D, var17, var18);
/* 53 */     var10.func_78374_a(-7.0D, 2.0D, 2.0D, var17, var19);
/* 54 */     var10.func_78374_a(-7.0D, 2.0D, -2.0D, var16, var19);
/* 55 */     var10.func_78381_a();
/* 56 */     GL11.glNormal3f(-var20, 0.0F, 0.0F);
/* 57 */     var10.func_78382_b();
/* 58 */     var10.func_78374_a(-7.0D, 2.0D, -2.0D, var16, var18);
/* 59 */     var10.func_78374_a(-7.0D, 2.0D, 2.0D, var17, var18);
/* 60 */     var10.func_78374_a(-7.0D, -2.0D, 2.0D, var17, var19);
/* 61 */     var10.func_78374_a(-7.0D, -2.0D, -2.0D, var16, var19);
/* 62 */     var10.func_78381_a();
/*    */     
/* 64 */     for (int var23 = 0; var23 < 4; var23++)
/*    */     {
/* 66 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 67 */       GL11.glNormal3f(0.0F, 0.0F, var20);
/* 68 */       var10.func_78382_b();
/* 69 */       var10.func_78374_a(-8.0D, -2.0D, 0.0D, var12, var14);
/* 70 */       var10.func_78374_a(8.0D, -2.0D, 0.0D, var13, var14);
/* 71 */       var10.func_78374_a(8.0D, 2.0D, 0.0D, var13, var15);
/* 72 */       var10.func_78374_a(-8.0D, 2.0D, 0.0D, var12, var15);
/* 73 */       var10.func_78381_a();
/*    */     }
/*    */     
/* 76 */     GL11.glDisable(32826);
/* 77 */     GL11.glPopMatrix();
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
/* 88 */     renderArrow((EntityArrow)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/* 91 */   private static final ResourceLocation rl = new ResourceLocation("textures/entity/arrow.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 95 */     return rl;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderDart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */