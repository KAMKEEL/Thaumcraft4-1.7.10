/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelVillager;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.monster.EntityTaintVillager;
/*     */ 
/*     */ 
/*     */ public class RenderTaintVillager
/*     */   extends RenderLiving
/*     */ {
/*     */   protected ModelVillager field_40295_c;
/*     */   
/*     */   public RenderTaintVillager()
/*     */   {
/*  20 */     super(new ModelVillager(0.0F), 0.5F);
/*  21 */     this.field_40295_c = ((ModelVillager)this.field_77045_g);
/*     */   }
/*     */   
/*  24 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/villager.png");
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/*  28 */     return rl;
/*     */   }
/*     */   
/*     */   protected int func_40293_a(EntityTaintVillager par1EntityVillager, int par2, float par3)
/*     */   {
/*  33 */     return -1;
/*     */   }
/*     */   
/*     */   public void renderVillager(EntityTaintVillager par1EntityVillager, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/*  38 */     super.func_76986_a(par1EntityVillager, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected void func_40290_a(EntityTaintVillager par1EntityVillager, double par2, double par4, double par6) {}
/*     */   
/*     */   protected void func_40291_a(EntityTaintVillager par1EntityVillager, float par2)
/*     */   {
/*  45 */     super.func_77029_c(par1EntityVillager, par2);
/*     */   }
/*     */   
/*     */   protected void func_40292_b(EntityTaintVillager par1EntityVillager, float par2)
/*     */   {
/*  50 */     float var3 = 0.9375F;
/*     */     
/*  52 */     this.field_76989_e = 0.5F;
/*     */     
/*  54 */     GL11.glScalef(var3, var3, var3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_77033_b(EntityLivingBase par1EntityLiving, double par2, double par4, double par6)
/*     */   {
/*  63 */     func_40290_a((EntityTaintVillager)par1EntityLiving, par2, par4, par6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
/*     */   {
/*  72 */     func_40292_b((EntityTaintVillager)par1EntityLiving, par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3)
/*     */   {
/*  81 */     return func_40293_a((EntityTaintVillager)par1EntityLiving, par2, par3);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_77029_c(EntityLivingBase par1EntityLiving, float par2)
/*     */   {
/*  87 */     func_40291_a((EntityTaintVillager)par1EntityLiving, par2);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/*  93 */     renderVillager((EntityTaintVillager)par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
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
/* 105 */     renderVillager((EntityTaintVillager)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderTaintVillager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */