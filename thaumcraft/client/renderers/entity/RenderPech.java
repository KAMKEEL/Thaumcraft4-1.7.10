/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.renderers.models.entities.ModelPech;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderPech extends RenderLiving
/*     */ {
/*     */   protected ModelPech modelMain;
/*     */   protected ModelPech modelOverlay;
/*  31 */   private static final ResourceLocation[] skin = { new ResourceLocation("thaumcraft", "textures/models/pech_forage.png"), new ResourceLocation("thaumcraft", "textures/models/pech_thaum.png"), new ResourceLocation("thaumcraft", "textures/models/pech_stalker.png") };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RenderPech(ModelPech par1ModelBiped, float par2)
/*     */   {
/*  39 */     super(par1ModelBiped, par2);
/*  40 */     this.modelMain = par1ModelBiped;
/*  41 */     this.modelOverlay = new ModelPech();
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/*  46 */     return skin[((thaumcraft.common.entities.monster.EntityPech)entity).getPechType()];
/*     */   }
/*     */   
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/*  51 */     float f2 = 1.0F;
/*  52 */     GL11.glColor3f(f2, f2, f2);
/*  53 */     ItemStack itemstack = par1EntityLiving.func_70694_bm();
/*  54 */     func_82420_a(par1EntityLiving, itemstack);
/*  55 */     double d3 = par4 - par1EntityLiving.field_70129_M;
/*     */     
/*  57 */     if (par1EntityLiving.func_70093_af())
/*     */     {
/*  59 */       d3 -= 0.125D;
/*     */     }
/*     */     
/*  62 */     super.func_76986_a(par1EntityLiving, par2, d3, par6, par8, par9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving)
/*     */   {
/*  70 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_82420_a(EntityLiving par1EntityLiving, ItemStack par2ItemStack) {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_130005_c(EntityLiving par1EntityLiving, float par2)
/*     */   {
/*  81 */     float f1 = 1.0F;
/*  82 */     GL11.glColor3f(f1, f1, f1);
/*  83 */     super.func_77029_c(par1EntityLiving, par2);
/*  84 */     ItemStack itemstack = par1EntityLiving.func_70694_bm();
/*     */     
/*     */ 
/*  87 */     if (itemstack != null)
/*     */     {
/*  89 */       GL11.glPushMatrix();
/*     */       
/*  91 */       if (this.field_77045_g.field_78091_s)
/*     */       {
/*  93 */         float f2 = 0.5F;
/*  94 */         GL11.glTranslatef(0.0F, 0.625F, 0.0F);
/*  95 */         GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
/*  96 */         GL11.glScalef(f2, f2, f2);
/*     */       }
/*     */       
/*  99 */       this.modelMain.RightArm.func_78794_c(0.0625F);
/* 100 */       GL11.glTranslatef(-0.0625F, 0.3375F, 0.0625F);
/*     */       
/* 102 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/* 103 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 105 */       if (((itemstack.func_77973_b() instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(itemstack.func_77973_b()).func_149645_b()))))
/*     */       {
/* 107 */         float f2 = 0.5F;
/* 108 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 109 */         f2 *= 0.75F;
/* 110 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 111 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 112 */         GL11.glScalef(-f2, -f2, f2);
/*     */       }
/* 114 */       else if (itemstack.func_77973_b() == Items.field_151031_f)
/*     */       {
/* 116 */         float f2 = 0.625F;
/* 117 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 118 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 119 */         GL11.glScalef(f2, -f2, f2);
/* 120 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 121 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/* 123 */       else if (itemstack.func_77973_b().func_77662_d())
/*     */       {
/* 125 */         float f2 = 0.625F;
/* 126 */         if (itemstack.func_77973_b() == thaumcraft.common.config.ConfigItems.itemWandCasting)
/*     */         {
/* 128 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/* 130 */         if (itemstack.func_77973_b().func_77629_n_())
/*     */         {
/* 132 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 133 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/* 136 */         func_82422_c();
/* 137 */         GL11.glScalef(f2, -f2, f2);
/* 138 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 139 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       else
/*     */       {
/* 143 */         float f2 = 0.375F;
/* 144 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 145 */         GL11.glScalef(f2, f2, f2);
/* 146 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 147 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 148 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/* 151 */       this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, 0);
/*     */       
/* 153 */       if (itemstack.func_77973_b().func_77623_v())
/*     */       {
/* 155 */         for (int x = 1; x < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); x++)
/*     */         {
/* 157 */           this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, x);
/*     */         }
/*     */       }
/*     */       
/* 161 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_82422_c()
/*     */   {
/* 167 */     GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_77029_c(EntityLivingBase par1EntityLivingBase, float par2)
/*     */   {
/* 185 */     func_130005_c((EntityLiving)par1EntityLivingBase, par2);
/*     */   }
/*     */   
/*     */   public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 190 */     doRenderLiving((EntityLiving)par1EntityLivingBase, par2, par4, par6, par8, par9);
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
/* 202 */     doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */