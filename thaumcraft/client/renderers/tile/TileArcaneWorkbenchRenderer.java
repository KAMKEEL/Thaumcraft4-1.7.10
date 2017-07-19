/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelArcaneWorkbench;
/*    */ import thaumcraft.common.items.wands.ItemWandCasting;
/*    */ import thaumcraft.common.tiles.TileArcaneWorkbench;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileArcaneWorkbenchRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 23 */   private ModelArcaneWorkbench tableModel = new ModelArcaneWorkbench();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileArcaneWorkbench table, double par2, double par4, double par6, float par8)
/*    */   {
/* 33 */     GL11.glPushMatrix();
/* 34 */     UtilsFX.bindTexture("textures/models/worktable.png");
/* 35 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.0F, (float)par6 + 0.5F);
/* 36 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 37 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 38 */     this.tableModel.renderAll();
/* 39 */     GL11.glPopMatrix();
/* 40 */     if ((table.func_145831_w() != null) && (table.func_70301_a(10) != null) && ((table.func_70301_a(10).func_77973_b() instanceof ItemWandCasting)))
/*    */     {
/*    */ 
/* 43 */       GL11.glPushMatrix();
/* 44 */       GL11.glTranslatef((float)par2 + 0.65F, (float)par4 + 1.0625F, (float)par6 + 0.25F);
/* 45 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 46 */       GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*    */       
/* 48 */       ItemStack is = table.func_70301_a(10).func_77946_l();
/* 49 */       is.field_77994_a = 1;
/* 50 */       EntityItem entityitem = new EntityItem(table.func_145831_w(), 0.0D, 0.0D, 0.0D, is);
/* 51 */       entityitem.field_70290_d = 0.0F;
/* 52 */       net.minecraft.client.renderer.entity.RenderItem.field_82407_g = true;
/* 53 */       RenderManager.field_78727_a.func_147940_a(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 54 */       net.minecraft.client.renderer.entity.RenderItem.field_82407_g = false;
/*    */       
/* 56 */       GL11.glPopMatrix();
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 62 */     renderTileEntityAt((TileArcaneWorkbench)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileArcaneWorkbenchRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */