/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.api.nodes.NodeType;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileNode;
/*     */ 
/*     */ public class ItemNodeRenderer
/*     */   implements IItemRenderer
/*     */ {
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*     */   {
/*  26 */     return (item != null) && (item.func_77973_b() == Item.func_150898_a(ConfigBlocks.blockAiry)) && ((item.func_77960_j() == 0) || (item.func_77960_j() == 5));
/*     */   }
/*     */   
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*     */   {
/*  31 */     if (helper == IItemRenderer.ItemRendererHelper.EQUIPPED_BLOCK) return false;
/*  32 */     return true;
/*     */   }
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*     */   {
/*  37 */     if (type == IItemRenderer.ItemRenderType.ENTITY) { GL11.glTranslatef(-0.5F, -0.25F, -0.5F);
/*     */     }
/*  39 */     else if ((type == IItemRenderer.ItemRenderType.EQUIPPED) && ((data[1] instanceof EntityPlayer))) { GL11.glTranslatef(0.0F, 0.0F, -0.5F);
/*     */     }
/*  41 */     TileNode tjf = new TileNode();
/*  42 */     tjf.setAspects(this.aspects);
/*  43 */     tjf.setNodeType(NodeType.NORMAL);
/*  44 */     tjf.field_145854_h = ConfigBlocks.blockAiry;
/*  45 */     tjf.field_145847_g = 0;
/*  46 */     GL11.glPushMatrix();
/*  47 */     GL11.glTranslated(0.5D, 0.5D, 0.5D);
/*  48 */     GL11.glScaled(2.0D, 2.0D, 2.0D);
/*  49 */     renderItemNode(tjf);
/*  50 */     GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  51 */     renderItemNode(tjf);
/*  52 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  53 */     renderItemNode(tjf);
/*  54 */     GL11.glPopMatrix();
/*  55 */     GL11.glEnable(32826);
/*     */   }
/*     */   
/*     */ 
/*  59 */   AspectList aspects = new AspectList().add(Aspect.AIR, 40).add(Aspect.FIRE, 40).add(Aspect.EARTH, 40).add(Aspect.WATER, 40);
/*     */   
/*     */ 
/*     */   public static void renderItemNode(INode node)
/*     */   {
/*  64 */     if (node.getAspects().size() > 0) {
/*  65 */       EntityLivingBase viewer = Minecraft.func_71410_x().field_71451_h;
/*  66 */       float alpha = 0.5F;
/*  67 */       if (node.getNodeModifier() != null) {
/*  68 */         switch (node.getNodeModifier()) {
/*  69 */         case BRIGHT:  alpha *= 1.5F; break;
/*  70 */         case PALE:  alpha *= 0.66F; break;
/*     */         case FADING: 
/*  72 */           alpha *= (MathHelper.func_76126_a(viewer.field_70173_aa / 3.0F) * 0.25F + 0.33F);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */ 
/*  78 */       GL11.glPushMatrix();
/*  79 */       GL11.glAlphaFunc(516, 0.003921569F);
/*  80 */       GL11.glDepthMask(false);
/*  81 */       GL11.glDisable(2884);
/*  82 */       long nt = System.nanoTime();
/*  83 */       long time = nt / 5000000L;
/*  84 */       float bscale = 0.25F;
/*     */       
/*  86 */       GL11.glPushMatrix();
/*  87 */       float rad = 6.2831855F;
/*  88 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/*     */       
/*  90 */       UtilsFX.bindTexture(TileNodeRenderer.nodetex);
/*  91 */       int frames = 32;
/*  92 */       int i = (int)((nt / 40000000L + 1L) % frames);
/*     */       
/*  94 */       int count = 0;
/*  95 */       float scale = 0.0F;
/*  96 */       float average = 0.0F;
/*     */       
/*     */ 
/*  99 */       for (Aspect aspect : node.getAspects().getAspects()) {
/* 100 */         if (aspect.getBlend() == 771) alpha = (float)(alpha * 1.5D);
/* 101 */         average += node.getAspects().getAmount(aspect);
/* 102 */         GL11.glPushMatrix();
/* 103 */         GL11.glEnable(3042);
/* 104 */         GL11.glBlendFunc(770, aspect.getBlend());
/* 105 */         scale = MathHelper.func_76126_a(viewer.field_70173_aa / (14.0F - count)) * bscale + bscale * 2.0F;
/* 106 */         scale = 0.2F + scale * (node.getAspects().getAmount(aspect) / 50.0F);
/* 107 */         UtilsFX.renderAnimatedQuadStrip(scale, alpha / node.getAspects().size(), frames, 0, i, 0.0F, aspect.getColor());
/* 108 */         GL11.glDisable(3042);
/* 109 */         GL11.glPopMatrix();
/* 110 */         count++;
/* 111 */         if (aspect.getBlend() == 771) { alpha = (float)(alpha / 1.5D);
/*     */         }
/*     */       }
/*     */       
/* 115 */       average /= node.getAspects().size();
/* 116 */       GL11.glPushMatrix();
/* 117 */       GL11.glEnable(3042);
/* 118 */       i = (int)((nt / 40000000L + 1L) % frames);
/* 119 */       scale = 0.1F + average / 150.0F;
/* 120 */       int strip = 1;
/* 121 */       switch (node.getNodeType()) {
/*     */       case NORMAL: 
/* 123 */         GL11.glBlendFunc(770, 1);
/* 124 */         break;
/*     */       case UNSTABLE: 
/* 126 */         GL11.glBlendFunc(770, 1);
/* 127 */         strip = 6;
/* 128 */         break;
/*     */       case DARK: 
/* 130 */         GL11.glBlendFunc(770, 771);
/* 131 */         strip = 2;
/* 132 */         break;
/*     */       case TAINTED: 
/* 134 */         GL11.glBlendFunc(770, 771);
/* 135 */         strip = 5;
/* 136 */         break;
/*     */       case PURE: 
/* 138 */         GL11.glBlendFunc(770, 1);
/* 139 */         strip = 4;
/* 140 */         break;
/*     */       case HUNGRY: 
/* 142 */         scale *= 0.75F;
/* 143 */         GL11.glBlendFunc(770, 1);
/* 144 */         strip = 3;
/*     */       }
/*     */       
/*     */       
/* 148 */       GL11.glColor4f(1.0F, 0.0F, 1.0F, alpha);
/* 149 */       UtilsFX.renderAnimatedQuadStrip(scale, alpha, frames, strip, i, 0.0F, 16777215);
/*     */       
/* 151 */       GL11.glDisable(3042);
/* 152 */       GL11.glPopMatrix();
/*     */       
/* 154 */       GL11.glPopMatrix();
/*     */       
/* 156 */       GL11.glEnable(2884);
/* 157 */       GL11.glDepthMask(true);
/* 158 */       GL11.glAlphaFunc(516, 0.1F);
/* 159 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/ItemNodeRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */