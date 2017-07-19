/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.wands.ItemFocusBasic;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelArcaneWorkbench;
/*    */ import thaumcraft.common.tiles.TileFocalManipulator;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileFocalManipulatorRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 25 */   private ModelArcaneWorkbench tableModel = new ModelArcaneWorkbench();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 32 */   EntityItem entityitem = null;
/*    */   
/*    */   public void renderTileEntityAt(TileFocalManipulator table, double par2, double par4, double par6, float par8)
/*    */   {
/* 36 */     GL11.glPushMatrix();
/* 37 */     UtilsFX.bindTexture("textures/models/wandtable.png");
/* 38 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.0F, (float)par6 + 0.5F);
/* 39 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 40 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 41 */     this.tableModel.renderAll();
/* 42 */     GL11.glPopMatrix();
/* 43 */     if ((table.func_145831_w() != null) && (table.func_70301_a(0) != null) && ((table.func_70301_a(0).func_77973_b() instanceof ItemFocusBasic)))
/*    */     {
/* 45 */       float ticks = Minecraft.func_71410_x().field_71451_h.field_70173_aa + par8;
/* 46 */       GL11.glPushMatrix();
/* 47 */       GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.0F, (float)par6 + 0.5F);
/* 48 */       GL11.glRotatef(ticks % 360.0F, 0.0F, 1.0F, 0.0F);
/* 49 */       ItemStack is = table.func_70301_a(0).func_77946_l();
/* 50 */       this.entityitem = new EntityItem(table.func_145831_w(), 0.0D, 0.0D, 0.0D, is);
/* 51 */       this.entityitem.field_70290_d = (MathHelper.func_76126_a(ticks / 14.0F) * 0.2F + 0.2F);
/* 52 */       net.minecraft.client.renderer.entity.RenderItem.field_82407_g = true;
/* 53 */       RenderManager.field_78727_a.func_147940_a(this.entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 54 */       net.minecraft.client.renderer.entity.RenderItem.field_82407_g = false;
/* 55 */       GL11.glPopMatrix();
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 61 */     renderTileEntityAt((TileFocalManipulator)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileFocalManipulatorRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */