/*    */ package thaumcraft.client.renderers.item;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraftforge.client.IItemRenderer;
/*    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*    */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.common.tiles.TileBanner;
/*    */ 
/*    */ 
/*    */ public class ItemBannerRenderer
/*    */   implements IItemRenderer
/*    */ {
/*    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*    */   {
/* 20 */     if ((!item.func_77942_o()) || (item.func_77960_j() != 8)) return false;
/* 21 */     if ((item.field_77990_d.func_74779_i("aspect") == null) || (item.field_77990_d.func_74771_c("color") < 0)) {
/* 22 */       return false;
/*    */     }
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*    */   {
/* 29 */     return true;
/*    */   }
/*    */   
/*    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*    */   {
/* 34 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 36 */     GL11.glPushMatrix();
/* 37 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 38 */     short var11 = 0;
/*    */     
/* 40 */     if ((type == IItemRenderer.ItemRenderType.EQUIPPED) || (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
/* 41 */       GL11.glTranslatef(1.0F, 1.0F, 1.0F);
/*    */     } else {
/* 43 */       GL11.glRotatef(var11, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 46 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*    */     
/* 48 */     GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 49 */     GL11.glTranslatef(-0.5F, -1.0F, -0.5F);
/* 50 */     TileBanner tb = new TileBanner();
/* 51 */     tb.setColor(item.field_77990_d.func_74771_c("color"));
/* 52 */     if (item.field_77990_d.func_74779_i("aspect") != null)
/* 53 */       tb.setAspect(Aspect.getAspect(item.field_77990_d.func_74779_i("aspect")));
/* 54 */     TileEntityRendererDispatcher.field_147556_a.func_147549_a(tb, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */     
/* 56 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/item/ItemBannerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */