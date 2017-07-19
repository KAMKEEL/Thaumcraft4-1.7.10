/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.client.IItemRenderer;
/*    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*    */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.blocks.ItemJarNode;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.tiles.TileJar;
/*    */ import thaumcraft.common.tiles.TileJarNode;
/*    */ 
/*    */ public class ItemJarNodeRenderer implements IItemRenderer
/*    */ {
/*    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*    */   {
/* 25 */     return (item != null) && (item.func_77973_b() == ConfigItems.itemJarNode);
/*    */   }
/*    */   
/*    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*    */   {
/* 30 */     if (helper == IItemRenderer.ItemRendererHelper.EQUIPPED_BLOCK) return false;
/* 31 */     return true;
/*    */   }
/*    */   
/* 34 */   RenderBlocks rb = new RenderBlocks();
/*    */   
/*    */ 
/*    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*    */   {
/* 39 */     if (item.func_77973_b() == ConfigItems.itemJarNode) {
/* 40 */       if (type == IItemRenderer.ItemRenderType.ENTITY) { GL11.glTranslatef(-0.5F, -0.25F, -0.5F);
/*    */       }
/* 42 */       else if ((type == IItemRenderer.ItemRenderType.EQUIPPED) && ((data[1] instanceof EntityPlayer))) { GL11.glTranslatef(0.0F, 0.0F, -0.5F);
/*    */       }
/* 44 */       TileJarNode tjf = new TileJarNode();
/* 45 */       if (item.func_77942_o()) {
/* 46 */         AspectList aspects = ((ItemJarNode)item.func_77973_b()).getAspects(item);
/* 47 */         if (aspects != null) {
/* 48 */           tjf.setAspects(aspects);
/* 49 */           tjf.setNodeType(((ItemJarNode)item.func_77973_b()).getNodeType(item));
/* 50 */           tjf.setNodeModifier(((ItemJarNode)item.func_77973_b()).getNodeModifier(item));
/*    */         }
/*    */       }
/* 53 */       tjf.field_145854_h = ConfigBlocks.blockJar;
/* 54 */       tjf.field_145847_g = 2;
/* 55 */       GL11.glPushMatrix();
/* 56 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileJar(), 0.0D, 0.0D, 0.0D, 0.0F);
/* 57 */       GL11.glPopMatrix();
/* 58 */       GL11.glPushMatrix();
/* 59 */       GL11.glTranslated(0.5D, 0.4D, 0.5D);
/* 60 */       ItemNodeRenderer.renderItemNode(tjf);
/* 61 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 62 */       ItemNodeRenderer.renderItemNode(tjf);
/* 63 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 64 */       ItemNodeRenderer.renderItemNode(tjf);
/* 65 */       GL11.glPopMatrix();
/*    */       
/*    */ 
/* 68 */       GL11.glPushMatrix();
/* 69 */       GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 70 */       GL11.glEnable(3042);
/* 71 */       GL11.glBlendFunc(770, 771);
/* 72 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 73 */       Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
/* 74 */       this.rb.field_147844_c = true;
/* 75 */       this.rb.func_147800_a(ConfigBlocks.blockJar, item.func_77960_j(), 1.0F);
/* 76 */       GL11.glPopMatrix();
/*    */       
/*    */ 
/* 79 */       GL11.glEnable(32826);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/ItemJarNodeRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */