/*     */ package thaumcraft.client.renderers.item;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.ItemFocusBasic.WandFocusAnimation;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.gear.ModelWand;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ 
/*     */ public class ItemWandRenderer implements net.minecraftforge.client.IItemRenderer
/*     */ {
/*  21 */   private ModelWand model = new ModelWand();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*     */   {
/*  29 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*     */   {
/*  34 */     if (helper == IItemRenderer.ItemRendererHelper.BLOCK_3D) return false;
/*  35 */     return true;
/*     */   }
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*     */   {
/*  40 */     Minecraft mc = Minecraft.func_71410_x();
/*  41 */     if ((item == null) || (!(item.func_77973_b() instanceof ItemWandCasting))) return;
/*  42 */     ItemWandCasting wand = (ItemWandCasting)item.func_77973_b();
/*  43 */     ItemStack focusStack = wand.getFocusItem(item);
/*  44 */     boolean staff = wand.isStaff(item);
/*  45 */     float pt = UtilsFX.getTimer(mc).field_74281_c;
/*     */     
/*  47 */     ItemRenderer ir = RenderManager.field_78727_a.field_78721_f;
/*     */     
/*  49 */     EntityLivingBase wielder = null;
/*  50 */     if ((type == IItemRenderer.ItemRenderType.EQUIPPED) || (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
/*  51 */       wielder = (EntityLivingBase)data[1];
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  75 */     GL11.glPushMatrix();
/*     */     
/*  77 */     if (staff) { GL11.glTranslated(0.0D, 0.5D, 0.0D);
/*     */     }
/*  79 */     if (type != IItemRenderer.ItemRenderType.INVENTORY) {
/*  80 */       if (type == IItemRenderer.ItemRenderType.ENTITY) {
/*  81 */         if (staff) {
/*  82 */           GL11.glTranslated(0.0D, 1.5D, 0.0D);
/*  83 */           GL11.glScaled(0.9D, 0.9D, 0.9D);
/*     */         }
/*     */         else {
/*  86 */           GL11.glTranslated(0.0D, 1.0D, 0.0D);
/*     */         }
/*     */       }
/*     */       else {
/*  90 */         GL11.glTranslated(0.5D, 1.5D, 0.5D);
/*  91 */         if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
/*  92 */           GL11.glScaled(1.0D, 1.1D, 1.0D);
/*     */         }
/*     */       }
/*     */     } else {
/*  96 */       if (staff) GL11.glScaled(0.8D, 0.8D, 0.8D);
/*  97 */       GL11.glRotatef(66.0F, 0.0F, 0.0F, 1.0F);
/*  98 */       GL11.glTranslated(0.0D, 0.6D, 0.0D);
/*  99 */       if (staff) { GL11.glTranslated(-0.7D, 0.6D, 0.0D);
/*     */       }
/*     */     }
/* 102 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */     
/* 104 */     if ((wielder != null) && ((wielder instanceof EntityPlayer)) && (((EntityPlayer)wielder).func_71011_bu() != null))
/*     */     {
/* 106 */       float t = ((EntityPlayer)wielder).func_71057_bx() + pt;
/* 107 */       if (t > 3.0F) t = 3.0F;
/* 108 */       GL11.glTranslated(0.0D, 1.0D, 0.0D);
/* 109 */       if (type != IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
/* 110 */         GL11.glRotatef(33.0F, 0.0F, 0.0F, 1.0F);
/*     */       } else {
/* 112 */         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
/* 113 */         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/* 115 */       GL11.glRotatef(60.0F * (t / 3.0F), -1.0F, 0.0F, 0.0F);
/* 116 */       if ((wand.animation == ItemFocusBasic.WandFocusAnimation.WAVE) || ((wand.getFocus(item) != null) && (wand.getFocus(item).getAnimation(focusStack) == ItemFocusBasic.WandFocusAnimation.WAVE)))
/*     */       {
/* 118 */         float wave = MathHelper.func_76126_a((((EntityPlayer)wielder).func_71057_bx() + pt) / 10.0F) * 10.0F;
/* 119 */         GL11.glRotatef(wave, 0.0F, 0.0F, 1.0F);
/* 120 */         wave = MathHelper.func_76126_a((((EntityPlayer)wielder).func_71057_bx() + pt) / 15.0F) * 10.0F;
/* 121 */         GL11.glRotatef(wave, 1.0F, 0.0F, 0.0F);
/*     */       }
/* 123 */       else if ((wand.getFocus(item) != null) && (wand.getFocus(item).getAnimation(focusStack) == ItemFocusBasic.WandFocusAnimation.CHARGE)) {
/* 124 */         float wave = MathHelper.func_76126_a((((EntityPlayer)wielder).func_71057_bx() + pt) / 0.8F) * 1.0F;
/* 125 */         GL11.glRotatef(wave, 0.0F, 0.0F, 1.0F);
/* 126 */         wave = MathHelper.func_76126_a((((EntityPlayer)wielder).func_71057_bx() + pt) / 0.7F) * 1.0F;
/* 127 */         GL11.glRotatef(wave, 1.0F, 0.0F, 0.0F);
/*     */       }
/* 129 */       GL11.glTranslated(0.0D, -1.0D, 0.0D);
/*     */     }
/* 131 */     GL11.glEnable(3042);
/* 132 */     GL11.glBlendFunc(770, 771);
/* 133 */     this.model.render(item);
/* 134 */     GL11.glDisable(3042);
/*     */     
/* 136 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/item/ItemWandRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */