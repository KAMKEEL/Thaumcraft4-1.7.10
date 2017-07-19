/*    */ package thaumcraft.client.renderers.item;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.ModelChest;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.client.IItemRenderer;
/*    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*    */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ 
/*    */ public class ItemTrunkSpawnerRenderer implements IItemRenderer
/*    */ {
/* 14 */   private ModelChest chest = new ModelChest();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*    */   {
/* 21 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*    */   {
/* 26 */     return true;
/*    */   }
/*    */   
/*    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*    */   {
/* 31 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 33 */     GL11.glPushMatrix();
/* 34 */     UtilsFX.bindTexture("textures/models/trunk.png");
/*    */     
/* 36 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 37 */     GL11.glScalef(1.0F, -1.0F, -1.0F);
/* 38 */     short var11 = 0;
/*    */     
/* 40 */     if ((type == IItemRenderer.ItemRenderType.EQUIPPED) || (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
/* 41 */       GL11.glTranslatef(-0.25F, -0.5F, -0.25F);
/* 42 */       if ((type == IItemRenderer.ItemRenderType.EQUIPPED) && (type != IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) {
/* 43 */         GL11.glTranslatef(1.0F, 0.0F, 0.0F);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 48 */     GL11.glRotatef(var11, 0.0F, 1.0F, 0.0F);
/*    */     
/* 50 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*    */     
/* 52 */     this.chest.func_78231_a();
/*    */     
/* 54 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/item/ItemTrunkSpawnerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */