/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelSpider;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.monster.EntityTaintSpider;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderTaintSpider
/*     */   extends RenderLiving
/*     */ {
/*     */   public RenderTaintSpider()
/*     */   {
/*  23 */     super(new ModelSpider(), 0.5F);
/*  24 */     func_77042_a(new ModelSpider());
/*     */   }
/*     */   
/*     */   protected float setSpiderDeathMaxRotation(EntitySpider par1EntitySpider)
/*     */   {
/*  29 */     return 180.0F;
/*     */   }
/*     */   
/*  32 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/taint_spider.png");
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity) {
/*  35 */     return rl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int setSpiderEyeBrightness(EntitySpider par1EntitySpider, int par2, float par3)
/*     */   {
/*  43 */     if (par2 != 0)
/*     */     {
/*  45 */       return -1;
/*     */     }
/*     */     
/*     */ 
/*  49 */     UtilsFX.bindTexture("textures/models/taint_spider_eyes.png");
/*  50 */     float f1 = 1.0F;
/*  51 */     GL11.glEnable(3042);
/*  52 */     GL11.glDisable(3008);
/*  53 */     GL11.glBlendFunc(1, 1);
/*     */     
/*  55 */     if (par1EntitySpider.func_82150_aj())
/*     */     {
/*  57 */       GL11.glDepthMask(false);
/*     */     }
/*     */     else
/*     */     {
/*  61 */       GL11.glDepthMask(true);
/*     */     }
/*     */     
/*  64 */     char c0 = 61680;
/*  65 */     int j = c0 % 65536;
/*  66 */     int k = c0 / 65536;
/*  67 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/*  68 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  69 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
/*  70 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void scaleSpider(EntityTaintSpider par1EntitySpider, float par2)
/*     */   {
/*  76 */     float f1 = par1EntitySpider.spiderScaleAmount();
/*  77 */     GL11.glScalef(f1, f1 * 1.25F, f1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*     */   {
/*  87 */     scaleSpider((EntityTaintSpider)par1EntityLiving, par2);
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_77037_a(EntityLivingBase par1EntityLiving)
/*     */   {
/*  93 */     return setSpiderDeathMaxRotation((EntitySpider)par1EntityLiving);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3)
/*     */   {
/* 102 */     return setSpiderEyeBrightness((EntitySpider)par1EntityLiving, par2, par3);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderTaintSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */