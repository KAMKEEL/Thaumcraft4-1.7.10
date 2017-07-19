/*     */ package thaumcraft.client.renderers.item;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ItemBowBoneRenderer implements IItemRenderer
/*     */ {
/*  19 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */   private RenderManager renderManager;
/*     */   
/*     */   public ItemBowBoneRenderer() {
/*  23 */     this.renderManager = RenderManager.field_78727_a;
/*  24 */     this.mc = Minecraft.func_71410_x();
/*  25 */     this.texturemanager = this.mc.func_110434_K();
/*     */   }
/*     */   
/*     */ 
/*     */   private Minecraft mc;
/*     */   
/*     */   private TextureManager texturemanager;
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*     */   {
/*  34 */     return type == IItemRenderer.ItemRenderType.EQUIPPED;
/*     */   }
/*     */   
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*     */   {
/*  39 */     return false;
/*     */   }
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*     */   {
/*  44 */     EntityLivingBase entity = (EntityLivingBase)data[1];
/*  45 */     ItemRenderer irInstance = this.mc.field_71460_t.field_78516_c;
/*  46 */     GL11.glPopMatrix();
/*  47 */     GL11.glPushMatrix();
/*  48 */     float f2 = 2.6666667F;
/*  49 */     GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
/*  50 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  51 */     GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
/*  52 */     GL11.glScalef(f2, f2, f2);
/*  53 */     GL11.glTranslatef(-0.25F, -0.1875F, 0.1875F);
/*  54 */     float f3 = 0.625F;
/*  55 */     GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/*  56 */     GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/*  57 */     GL11.glScalef(f3, -f3, f3);
/*  58 */     GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/*  59 */     GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  60 */     renderItem(entity, item, 0);
/*  61 */     GL11.glPopMatrix();
/*  62 */     GL11.glPushMatrix();
/*     */   }
/*     */   
/*     */   private void renderItem(EntityLivingBase par1EntityLiving, ItemStack par2ItemStack, int par3) {
/*  66 */     IIcon icon = par1EntityLiving.func_70620_b(par2ItemStack, par3);
/*  67 */     if (icon == null)
/*     */     {
/*  69 */       return;
/*     */     }
/*  71 */     this.texturemanager.func_110577_a(this.texturemanager.func_130087_a(par2ItemStack.func_94608_d()));
/*  72 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  73 */     float f = icon.func_94209_e();
/*  74 */     float f1 = icon.func_94212_f();
/*  75 */     float f2 = icon.func_94206_g();
/*  76 */     float f3 = icon.func_94210_h();
/*  77 */     float f4 = 0.0F;
/*  78 */     float f5 = 0.3F;
/*  79 */     GL11.glEnable(32826);
/*  80 */     GL11.glTranslatef(-f4, -f5, 0.0F);
/*  81 */     float f6 = 1.5F;
/*  82 */     GL11.glScalef(f6, f6, f6);
/*  83 */     GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
/*  84 */     GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
/*  85 */     GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
/*  86 */     ItemRenderer.func_78439_a(tessellator, f1, f2, f, f3, icon.func_94211_a(), icon.func_94216_b(), 0.0625F);
/*     */     
/*  88 */     if (par2ItemStack.hasEffect(par3)) {
/*  89 */       GL11.glDepthFunc(514);
/*  90 */       GL11.glDisable(2896);
/*  91 */       this.texturemanager.func_110577_a(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
/*     */       
/*  93 */       GL11.glEnable(3042);
/*  94 */       GL11.glBlendFunc(768, 1);
/*  95 */       float f7 = 0.76F;
/*  96 */       GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
/*  97 */       GL11.glMatrixMode(5890);
/*  98 */       GL11.glPushMatrix();
/*  99 */       float f8 = 0.125F;
/* 100 */       GL11.glScalef(f8, f8, f8);
/* 101 */       float f9 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0F * 8.0F;
/* 102 */       GL11.glTranslatef(f9, 0.0F, 0.0F);
/* 103 */       GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
/* 104 */       ItemRenderer.func_78439_a(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
/*     */       
/* 106 */       GL11.glPopMatrix();
/* 107 */       GL11.glPushMatrix();
/* 108 */       GL11.glScalef(f8, f8, f8);
/* 109 */       f9 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0F * 8.0F;
/* 110 */       GL11.glTranslatef(-f9, 0.0F, 0.0F);
/* 111 */       GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/* 112 */       ItemRenderer.func_78439_a(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
/*     */       
/* 114 */       GL11.glPopMatrix();
/* 115 */       GL11.glMatrixMode(5888);
/* 116 */       GL11.glDisable(3042);
/* 117 */       GL11.glEnable(2896);
/* 118 */       GL11.glDepthFunc(515);
/*     */     }
/* 120 */     GL11.glDisable(32826);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/item/ItemBowBoneRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */