/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelCreeper;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.monster.EntityTaintCreeper;
/*     */ 
/*     */ 
/*     */ public class RenderTaintCreeper
/*     */   extends RenderLiving
/*     */ {
/*  17 */   private ModelBase field_27008_a = new ModelCreeper(2.0F);
/*     */   
/*     */   public RenderTaintCreeper()
/*     */   {
/*  21 */     super(new ModelCreeper(), 0.5F);
/*     */   }
/*     */   
/*  24 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/creeper.png");
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/*  28 */     return rl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void updateCreeperScale(EntityTaintCreeper par1EntityCreeper, float par2)
/*     */   {
/*  36 */     float var4 = par1EntityCreeper.getCreeperFlashIntensity(par2);
/*  37 */     float var5 = 1.0F + MathHelper.func_76126_a(var4 * 100.0F) * var4 * 0.01F;
/*     */     
/*  39 */     if (var4 < 0.0F)
/*     */     {
/*  41 */       var4 = 0.0F;
/*     */     }
/*     */     
/*  44 */     if (var4 > 1.0F)
/*     */     {
/*  46 */       var4 = 1.0F;
/*     */     }
/*     */     
/*  49 */     var4 *= var4;
/*  50 */     var4 *= var4;
/*  51 */     float var6 = (1.0F + var4 * 0.4F) * var5;
/*  52 */     float var7 = (1.0F + var4 * 0.1F) / var5;
/*  53 */     GL11.glScalef(var6, var7, var6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int updateCreeperColorMultiplier(EntityTaintCreeper par1EntityCreeper, float par2, float par3)
/*     */   {
/*  61 */     float var5 = par1EntityCreeper.getCreeperFlashIntensity(par3);
/*     */     
/*  63 */     if ((int)(var5 * 10.0F) % 2 == 0)
/*     */     {
/*  65 */       return 0;
/*     */     }
/*     */     
/*     */ 
/*  69 */     int var6 = (int)(var5 * 0.2F * 255.0F);
/*     */     
/*  71 */     if (var6 < 0)
/*     */     {
/*  73 */       var6 = 0;
/*     */     }
/*     */     
/*  76 */     if (var6 > 255)
/*     */     {
/*  78 */       var6 = 255;
/*     */     }
/*     */     
/*  81 */     short var7 = 255;
/*  82 */     short var8 = 255;
/*  83 */     short var9 = 255;
/*  84 */     return var6 << 24 | var7 << 16 | var8 << 8 | var9;
/*     */   }
/*     */   
/*     */ 
/*  88 */   private static final ResourceLocation field_110831_a = new ResourceLocation("thaumcraft", "textures/entity/creeper/creeper_armor.png");
/*     */   
/*     */ 
/*     */ 
/*     */   protected int func_27007_b(EntityTaintCreeper par1EntityCreeper, int par2, float par3)
/*     */   {
/*  94 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*     */   {
/* 104 */     updateCreeperScale((EntityTaintCreeper)par1EntityLiving, par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int func_77030_a(EntityLivingBase par1EntityLiving, float par2, float par3)
/*     */   {
/* 113 */     return updateCreeperColorMultiplier((EntityTaintCreeper)par1EntityLiving, par2, par3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int func_77035_b(EntityLivingBase par1EntityLiving, int par2, float par3)
/*     */   {
/* 120 */     return func_27007_b((EntityTaintCreeper)par1EntityLiving, par2, par3);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderTaintCreeper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */