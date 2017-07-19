/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBobber;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderGolemBobber
/*     */   extends Render
/*     */ {
/*  23 */   private static final ResourceLocation tex = new ResourceLocation("textures/particle/particles.png");
/*     */   
/*     */   public void doRender(EntityGolemBobber bobber, double xx, double yy, double zz, float p_147922_8_, float p_147922_9_)
/*     */   {
/*  27 */     GL11.glPushMatrix();
/*  28 */     GL11.glTranslatef((float)xx, (float)yy, (float)zz);
/*  29 */     GL11.glEnable(32826);
/*  30 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  31 */     func_110777_b(bobber);
/*  32 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  33 */     byte b0 = 1;
/*  34 */     byte b1 = 2;
/*  35 */     float f2 = (b0 * 8 + 0) / 128.0F;
/*  36 */     float f3 = (b0 * 8 + 8) / 128.0F;
/*  37 */     float f4 = (b1 * 8 + 0) / 128.0F;
/*  38 */     float f5 = (b1 * 8 + 8) / 128.0F;
/*  39 */     float f6 = 1.0F;
/*  40 */     float f7 = 0.5F;
/*  41 */     float f8 = 0.5F;
/*  42 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  43 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/*  44 */     tessellator.func_78382_b();
/*  45 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/*  46 */     tessellator.func_78374_a(0.0F - f7, 0.0F - f8, 0.0D, f2, f5);
/*  47 */     tessellator.func_78374_a(f6 - f7, 0.0F - f8, 0.0D, f3, f5);
/*  48 */     tessellator.func_78374_a(f6 - f7, 1.0F - f8, 0.0D, f3, f4);
/*  49 */     tessellator.func_78374_a(0.0F - f7, 1.0F - f8, 0.0D, f2, f4);
/*  50 */     tessellator.func_78381_a();
/*  51 */     GL11.glDisable(32826);
/*  52 */     GL11.glPopMatrix();
/*     */     
/*  54 */     if (bobber.fisher != null)
/*     */     {
/*  56 */       float f9 = bobber.fisher.rightArm / 3.0F;
/*  57 */       float f10 = MathHelper.func_76126_a(MathHelper.func_76129_c(f9) * 3.1415927F);
/*  58 */       Vec3 vec3 = Vec3.func_72443_a(-0.5D, 0.03D, 0.8D);
/*  59 */       vec3.func_72440_a(-(bobber.fisher.field_70127_C + (bobber.fisher.field_70125_A - bobber.fisher.field_70127_C) * p_147922_9_) * 3.1415927F / 180.0F);
/*  60 */       vec3.func_72442_b(-(bobber.fisher.field_70126_B + (bobber.fisher.field_70177_z - bobber.fisher.field_70126_B) * p_147922_9_) * 3.1415927F / 180.0F);
/*  61 */       vec3.func_72442_b(f10 * 0.5F);
/*  62 */       vec3.func_72440_a(-f10 * 0.7F);
/*  63 */       double d3 = bobber.fisher.field_70169_q + (bobber.fisher.field_70165_t - bobber.fisher.field_70169_q) * p_147922_9_ + vec3.field_72450_a;
/*  64 */       double d4 = bobber.fisher.field_70167_r + (bobber.fisher.field_70163_u - bobber.fisher.field_70167_r) * p_147922_9_ + vec3.field_72448_b;
/*  65 */       double d5 = bobber.fisher.field_70166_s + (bobber.fisher.field_70161_v - bobber.fisher.field_70166_s) * p_147922_9_ + vec3.field_72449_c;
/*  66 */       double d6 = bobber.fisher.func_70047_e();
/*     */       
/*  68 */       float f11 = (bobber.fisher.field_70760_ar + (bobber.fisher.field_70761_aq - bobber.fisher.field_70760_ar) * p_147922_9_) * 3.1415927F / 180.0F;
/*  69 */       double d7 = MathHelper.func_76126_a(f11);
/*  70 */       double d9 = MathHelper.func_76134_b(f11);
/*  71 */       d3 = bobber.fisher.field_70169_q + (bobber.fisher.field_70165_t - bobber.fisher.field_70169_q) * p_147922_9_ - d9 * 0.25D - d7 * 0.7D;
/*  72 */       d4 = bobber.fisher.field_70167_r + d6 + (bobber.fisher.field_70163_u - bobber.fisher.field_70167_r) * p_147922_9_ - 0.4D;
/*  73 */       d5 = bobber.fisher.field_70166_s + (bobber.fisher.field_70161_v - bobber.fisher.field_70166_s) * p_147922_9_ - d7 * 0.25D + d9 * 0.7D;
/*     */       
/*  75 */       double d14 = bobber.field_70169_q + (bobber.field_70165_t - bobber.field_70169_q) * p_147922_9_;
/*  76 */       double d8 = bobber.field_70167_r + (bobber.field_70163_u - bobber.field_70167_r) * p_147922_9_ + 0.25D;
/*  77 */       double d10 = bobber.field_70166_s + (bobber.field_70161_v - bobber.field_70166_s) * p_147922_9_;
/*  78 */       double d11 = (float)(d3 - d14);
/*  79 */       double d12 = (float)(d4 - d8);
/*  80 */       double d13 = (float)(d5 - d10);
/*  81 */       GL11.glDisable(3553);
/*  82 */       GL11.glDisable(2896);
/*  83 */       tessellator.func_78371_b(3);
/*  84 */       tessellator.func_78378_d(0);
/*  85 */       byte b2 = 16;
/*     */       
/*  87 */       for (int i = 0; i <= b2; i++)
/*     */       {
/*  89 */         float f12 = i / b2;
/*  90 */         tessellator.func_78377_a(xx + d11 * f12, yy + d12 * (f12 * f12 + f12) * 0.5D + 0.25D, zz + d13 * f12);
/*     */       }
/*     */       
/*  93 */       tessellator.func_78381_a();
/*  94 */       GL11.glEnable(2896);
/*  95 */       GL11.glEnable(3553);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/* 102 */     return tex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 115 */     doRender((EntityGolemBobber)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderGolemBobber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */