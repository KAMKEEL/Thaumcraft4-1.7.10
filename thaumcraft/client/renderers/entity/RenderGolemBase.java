/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemFishingRod;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.entities.ModelGolem;
/*     */ import thaumcraft.client.renderers.models.entities.ModelGolemAccessories;
/*     */ import thaumcraft.common.blocks.ItemJarFilled;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ 
/*     */ public class RenderGolemBase
/*     */   extends RenderLiving
/*     */ {
/*     */   ModelBase damage;
/*     */   ModelBase accessories;
/*  47 */   private static final ResourceLocation BUCKET = new ResourceLocation("thaumcraft", "textures/models/bucket.obj");
/*     */   
/*     */   public RenderGolemBase(ModelBase par1ModelBase) {
/*  50 */     super(par1ModelBase, 0.25F);
/*  51 */     if ((par1ModelBase instanceof ModelGolem)) {
/*  52 */       ModelGolem mg = new ModelGolem(false);
/*  53 */       mg.pass = 2;
/*  54 */       this.damage = mg;
/*     */     }
/*  56 */     this.accessories = new ModelGolemAccessories(0.0F, 30.0F);
/*  57 */     this.model = AdvancedModelLoader.loadModel(BUCKET);
/*     */   }
/*     */   
/*     */ 
/*     */   public void render(EntityGolemBase e, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/*  63 */     super.func_76986_a(e, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected int func_77032_a(EntityLivingBase entity, int pass, float par3)
/*     */   {
/*  68 */     if (pass == 0) {
/*  69 */       String deco = ((EntityGolemBase)entity).getGolemDecoration();
/*  70 */       if (((EntityGolemBase)entity).getCore() > -1) {
/*  71 */         GL11.glPushMatrix();
/*     */         
/*  73 */         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*  74 */         GL11.glTranslatef(0.0875F, -0.96F, 0.15F + (deco.contains("P") ? 0.03F : 0.0F));
/*  75 */         GL11.glScaled(0.175D, 0.175D, 0.175D);
/*  76 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  77 */         Tessellator tessellator = Tessellator.field_78398_a;
/*  78 */         IIcon icon = ConfigItems.itemGolemCore.func_77617_a(((EntityGolemBase)entity).getCore());
/*  79 */         float f1 = icon.func_94212_f();
/*  80 */         float f2 = icon.func_94206_g();
/*  81 */         float f3 = icon.func_94209_e();
/*  82 */         float f4 = icon.func_94210_h();
/*  83 */         this.field_76990_c.field_78724_e.func_110577_a(TextureMap.field_110576_c);
/*  84 */         ItemRenderer.func_78439_a(tessellator, f1, f2, f3, f4, icon.func_94211_a(), icon.func_94216_b(), 0.2F);
/*     */         
/*  86 */         GL11.glPopMatrix();
/*     */       }
/*  88 */       int upgrades = ((EntityGolemBase)entity).upgrades.length;
/*  89 */       float shift = 0.08F;
/*  90 */       GL11.glPushMatrix();
/*  91 */       GL11.glEnable(3042);
/*  92 */       GL11.glBlendFunc(770, 771);
/*  93 */       for (int a = 0; a < upgrades; a++) {
/*  94 */         GL11.glPushMatrix();
/*  95 */         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*  96 */         GL11.glTranslatef(-0.05F - shift * (upgrades - 1) / 2.0F + shift * a, -1.106F, 0.099F);
/*  97 */         GL11.glScaled(0.1D, 0.1D, 0.1D);
/*  98 */         Tessellator tessellator = Tessellator.field_78398_a;
/*  99 */         IIcon icon = ConfigItems.itemGolemUpgrade.func_77617_a(((EntityGolemBase)entity).getUpgrade(a));
/*     */         
/* 101 */         float f1 = icon.func_94212_f();
/* 102 */         float f2 = icon.func_94206_g();
/* 103 */         float f3 = icon.func_94209_e();
/* 104 */         float f4 = icon.func_94210_h();
/* 105 */         this.field_76990_c.field_78724_e.func_110577_a(TextureMap.field_110576_c);
/* 106 */         tessellator.func_78382_b();
/* 107 */         tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/* 108 */         tessellator.func_78374_a(0.0D, 0.0D, 0.0D, f1, f4);
/* 109 */         tessellator.func_78374_a(1.0D, 0.0D, 0.0D, f3, f4);
/* 110 */         tessellator.func_78374_a(1.0D, 1.0D, 0.0D, f3, f2);
/* 111 */         tessellator.func_78374_a(0.0D, 1.0D, 0.0D, f1, f2);
/* 112 */         tessellator.func_78381_a();
/*     */         
/* 114 */         GL11.glPopMatrix();
/*     */       }
/* 116 */       GL11.glDisable(3042);
/* 117 */       GL11.glPopMatrix();
/*     */     } else {
/* 119 */       if ((pass == 1) && ((((EntityGolemBase)entity).getGolemDecoration().length() > 0) || (((EntityGolemBase)entity).advanced)))
/*     */       {
/*     */ 
/* 122 */         UtilsFX.bindTexture("textures/models/golem_decoration.png");
/* 123 */         func_77042_a(this.accessories);
/* 124 */         return 1;
/*     */       }
/* 126 */       if ((pass == 2) && (((EntityGolemBase)entity).getHealthPercentage() < 1.0F)) {
/* 127 */         UtilsFX.bindTexture("textures/models/golem_damage.png");
/* 128 */         func_77042_a(this.damage);
/* 129 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F - ((EntityGolemBase)entity).getHealthPercentage());
/* 130 */         return 2;
/*     */       }
/*     */     }
/* 133 */     return -1;
/*     */   }
/*     */   
/*     */   protected void renderWithSway(EntityGolemBase e, float par2, float par3, float par4)
/*     */   {
/* 138 */     super.func_77043_a(e, par2, par3, par4);
/*     */     
/* 140 */     if (e.field_70721_aZ >= 0.01D)
/*     */     {
/* 142 */       float var5 = 13.0F;
/* 143 */       float var6 = e.field_70754_ba - e.field_70721_aZ * (1.0F - par4) + 6.0F;
/* 144 */       float var7 = (Math.abs(var6 % var5 - var5 * 0.5F) - var5 * 0.25F) / (var5 * 0.25F);
/* 145 */       GL11.glRotatef(6.5F * var7, 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void renderCarriedItems(EntityGolemBase e, float par2)
/*     */   {
/* 151 */     ItemStack var3 = e.getCarriedForDisplay();
/*     */     
/* 153 */     if (e.getCore() == 11) {
/* 154 */       GL11.glPushMatrix();
/* 155 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 156 */       float fs = 0.66F;
/* 157 */       this.field_76990_c.field_78724_e.func_110577_a(TextureMap.field_110576_c);
/* 158 */       GL11.glRotatef(5.0F + 90.0F * ((ModelGolem)this.field_77045_g).golemRightArm.field_78795_f / 3.1415927F, 1.0F, 0.0F, 0.0F);
/* 159 */       GL11.glTranslatef(-0.26875F, 1.6F, -0.53F);
/* 160 */       GL11.glRotatef(90.0F, 0.0F, -1.0F, 0.0F);
/* 161 */       GL11.glRotatef(30.0F, 0.0F, 0.0F, -1.0F);
/* 162 */       GL11.glScalef(fs, -fs, fs);
/* 163 */       IIcon ic = Items.field_151112_aM.func_94597_g();
/* 164 */       float f = ic.func_94209_e();
/* 165 */       float f1 = ic.func_94212_f();
/* 166 */       float f2 = ic.func_94206_g();
/* 167 */       float f3 = ic.func_94210_h();
/* 168 */       Tessellator.field_78398_a.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F);
/* 169 */       ItemRenderer.func_78439_a(Tessellator.field_78398_a, f1, f2, f, f3, ic.func_94211_a(), ic.func_94216_b(), 0.0625F);
/*     */       
/*     */ 
/* 172 */       GL11.glScaled(1.0D, 1.0D, 1.0D);
/* 173 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 176 */     if ((var3 != null) && (e.field_70725_aQ == 0) && (e.getCore() != 5))
/*     */     {
/* 178 */       GL11.glPushMatrix();
/* 179 */       GL11.glScaled(0.4D, 0.4D, 0.4D);
/* 180 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(var3, IItemRenderer.ItemRenderType.EQUIPPED);
/* 181 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, var3, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 183 */       if (((var3.func_77973_b() instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(var3.func_77973_b()).func_149645_b()))))
/*     */       {
/* 185 */         GL11.glTranslatef(0.0F, 2.5F, -1.25F);
/* 186 */         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */       }
/* 188 */       else if ((var3.func_77973_b() instanceof ItemJarFilled))
/*     */       {
/*     */ 
/* 191 */         GL11.glTranslatef(0.0F, 2.5F, -1.0F);
/* 192 */         if (e.getCore() == 6) {
/* 193 */           double s = 0.5D + Math.min(64, e.getCarryLimit()) / 128.0D;
/* 194 */           GL11.glScaled(s, s, s);
/*     */         }
/* 196 */         GL11.glTranslatef(-0.5F, 0.0F, -0.8F);
/* 197 */         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 198 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 199 */         GL11.glRotatef(335.0F, 0.0F, 0.0F, -1.0F);
/* 200 */         GL11.glRotatef(50.0F, 0.0F, -1.0F, 0.0F);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 205 */         GL11.glTranslatef(-0.5F, 2.5F, -1.25F);
/* 206 */         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 207 */         if (!var3.func_77973_b().func_77623_v()) {
/* 208 */           GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 209 */           GL11.glRotatef(335.0F, 0.0F, 0.0F, -1.0F);
/* 210 */           GL11.glRotatef(50.0F, 0.0F, -1.0F, 0.0F);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 219 */       if (var3.func_77973_b().func_77623_v())
/*     */       {
/* 221 */         int renderPass = 0;
/*     */         do {
/* 223 */           IIcon icon = var3.func_77973_b().getIcon(var3, renderPass);
/* 224 */           if (icon != null) {
/* 225 */             Color color = new Color(var3.func_77973_b().func_82790_a(var3, renderPass));
/*     */             
/* 227 */             GL11.glColor3ub((byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue());
/*     */             
/* 229 */             float f = icon.func_94209_e();
/* 230 */             float f1 = icon.func_94212_f();
/* 231 */             float f2 = icon.func_94206_g();
/* 232 */             float f3 = icon.func_94210_h();
/* 233 */             ItemRenderer.func_78439_a(Tessellator.field_78398_a, f1, f2, f, f3, icon.func_94211_a(), icon.func_94216_b(), 0.0625F);
/*     */             
/*     */ 
/* 236 */             GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */           }
/* 238 */           renderPass++;
/* 239 */         } while (renderPass < var3.func_77973_b().getRenderPasses(var3.func_77960_j()));
/*     */       } else {
/* 241 */         int i = var3.func_77973_b().func_82790_a(var3, 0);
/* 242 */         float f7 = (i >> 16 & 0xFF) / 255.0F;
/* 243 */         float f8 = (i >> 8 & 0xFF) / 255.0F;
/* 244 */         float f6 = (i & 0xFF) / 255.0F;
/* 245 */         GL11.glColor4f(f7, f8, f6, 1.0F);
/* 246 */         this.field_76990_c.field_78721_f.func_78443_a(e, var3, 0);
/*     */       }
/*     */       
/* 249 */       GL11.glScaled(1.0D, 1.0D, 1.0D);
/* 250 */       GL11.glPopMatrix();
/*     */     }
/* 252 */     else if (e.getCore() == 5)
/*     */     {
/* 254 */       GL11.glPushMatrix();
/* 255 */       GL11.glScaled(0.4D, 0.4D, 0.4D);
/*     */       
/* 257 */       GL11.glTranslatef(0.0F, 3.0F, -1.1F);
/* 258 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*     */       
/* 260 */       UtilsFX.bindTexture("textures/models/bucket.png");
/* 261 */       this.model.renderPart("Bucket");
/*     */       
/* 263 */       if (e.getCarriedForDisplay() != null) {
/* 264 */         Fluid fluid = FluidRegistry.getFluid(Item.func_150891_b(e.getCarriedForDisplay().func_77973_b()));
/* 265 */         float max = Math.max(e.getCarriedForDisplay().func_77960_j(), e.getFluidCarryLimit());
/* 266 */         float fill = e.getCarriedForDisplay().func_77960_j() / max;
/* 267 */         if (fluid != null) {
/* 268 */           GL11.glTranslatef(0.0F, 0.0F, 0.2F + 0.8F * fill);
/* 269 */           GL11.glScaled(0.8D, 0.8D, 0.8D);
/* 270 */           this.icon = fluid.getIcon();
/* 271 */           int q = 0xF00000 | fluid.getLuminosity() << 4;
/* 272 */           int b = Math.max(e.func_70070_b(par2), q);
/* 273 */           UtilsFX.renderQuadCenteredFromIcon(true, this.icon, 1.0F, 1.0F, 1.0F, 1.0F, b, 771, 1.0F);
/*     */         }
/*     */       }
/*     */       
/* 277 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 282 */   IIcon icon = null;
/*     */   
/*     */   private IModelCustom model;
/*     */   
/*     */ 
/*     */   protected void func_77029_c(EntityLivingBase par1EntityLiving, float par2)
/*     */   {
/* 289 */     renderCarriedItems((EntityGolemBase)par1EntityLiving, par2);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_77043_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
/*     */   {
/* 295 */     renderWithSway((EntityGolemBase)par1EntityLiving, par2, par3, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 301 */     render((EntityGolemBase)par1EntityLiving, par2, par4, par6, par8, par9);
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
/* 313 */     render((EntityGolemBase)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/* 316 */   private static final ResourceLocation clay = new ResourceLocation("thaumcraft", "textures/models/golem_clay.png");
/* 317 */   private static final ResourceLocation stone = new ResourceLocation("thaumcraft", "textures/models/golem_stone.png");
/* 318 */   private static final ResourceLocation wood = new ResourceLocation("thaumcraft", "textures/models/golem_wood.png");
/* 319 */   private static final ResourceLocation tallow = new ResourceLocation("thaumcraft", "textures/models/golem_tallow.png");
/* 320 */   private static final ResourceLocation iron = new ResourceLocation("thaumcraft", "textures/models/golem_iron.png");
/* 321 */   private static final ResourceLocation straw = new ResourceLocation("thaumcraft", "textures/models/golem_straw.png");
/* 322 */   private static final ResourceLocation flesh = new ResourceLocation("thaumcraft", "textures/models/golem_flesh.png");
/* 323 */   private static final ResourceLocation thaumium = new ResourceLocation("thaumcraft", "textures/models/golem_thaumium.png");
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 327 */     switch (((EntityGolemBase)entity).getGolemType()) {
/* 328 */     case STRAW:  return straw;
/* 329 */     case WOOD:  return wood;
/* 330 */     case CLAY:  return clay;
/* 331 */     case STONE:  return stone;
/* 332 */     case IRON:  return iron;
/* 333 */     case TALLOW:  return tallow;
/* 334 */     case FLESH:  return flesh;
/* 335 */     case THAUMIUM:  return thaumium;
/*     */     }
/* 337 */     return AbstractClientPlayer.field_110314_b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderGolemBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */