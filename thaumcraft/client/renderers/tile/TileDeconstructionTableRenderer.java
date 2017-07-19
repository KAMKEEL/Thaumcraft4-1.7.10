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
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelArcaneWorkbench;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.tiles.TileDeconstructionTable;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileDeconstructionTableRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 25 */   private ModelArcaneWorkbench tableModel = new ModelArcaneWorkbench();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 31 */   ItemStack tm = new ItemStack(ConfigItems.itemThaumometer);
/*    */   
/*    */   public void renderTileEntityAt(TileDeconstructionTable table, double par2, double par4, double par6, float par8)
/*    */   {
/* 35 */     GL11.glPushMatrix();
/* 36 */     UtilsFX.bindTexture("textures/models/decontable.png");
/* 37 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.0F, (float)par6 + 0.5F);
/* 38 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 39 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 40 */     this.tableModel.renderAll();
/* 41 */     GL11.glPopMatrix();
/*    */     
/* 43 */     GL11.glPushMatrix();
/* 44 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.92F, (float)par6 + 0.5F);
/* 45 */     GL11.glScaled(0.8D, 0.8D, 0.8D);
/* 46 */     EntityItem entityitem = new EntityItem(table.func_145831_w(), 0.0D, 0.0D, 0.0D, this.tm);
/* 47 */     entityitem.field_70290_d = 0.0F;
/* 48 */     net.minecraft.client.renderer.entity.RenderItem.field_82407_g = true;
/* 49 */     RenderManager.field_78727_a.func_147940_a(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 50 */     net.minecraft.client.renderer.entity.RenderItem.field_82407_g = false;
/* 51 */     GL11.glPopMatrix();
/* 52 */     float ticks = Minecraft.func_71410_x().field_71451_h.field_70173_aa + par8;
/* 53 */     if ((table != null) && (table.func_145831_w() != null) && (table.func_70301_a(0) != null)) {
/* 54 */       GL11.glPushMatrix();
/* 55 */       GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.15F, (float)par6 + 0.5F);
/* 56 */       GL11.glRotatef(ticks % 360.0F, 0.0F, 1.0F, 0.0F);
/* 57 */       GL11.glEnable(3042);
/* 58 */       GL11.glBlendFunc(770, 1);
/* 59 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/* 60 */       ItemStack is = table.func_70301_a(0).func_77946_l();
/* 61 */       is.field_77994_a = 1;
/* 62 */       entityitem = new EntityItem(table.func_145831_w(), 0.0D, 0.0D, 0.0D, is);
/* 63 */       entityitem.field_70290_d = (MathHelper.func_76126_a(ticks / 14.0F) * 0.2F + 0.2F);
/*    */       
/* 65 */       net.minecraft.client.renderer.entity.RenderItem.field_82407_g = true;
/* 66 */       RenderManager.field_78727_a.func_147940_a(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 67 */       net.minecraft.client.renderer.entity.RenderItem.field_82407_g = false;
/* 68 */       GL11.glDisable(3042);
/* 69 */       GL11.glPopMatrix();
/*    */     }
/*    */     
/* 72 */     if ((table != null) && (table.func_145831_w() != null) && (table.aspect != null)) {
/* 73 */       GL11.glPushMatrix();
/* 74 */       GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.081F, (float)par6 + 0.5F);
/* 75 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 76 */       GL11.glRotatef(ticks % 360.0F, 0.0F, 0.0F, 1.0F);
/* 77 */       GL11.glScaled(0.024D, 0.024D, 0.024D);
/* 78 */       UtilsFX.drawTag(-8, -8, table.aspect, 0.0F, 0, 0.0D, 1, 0.8F, false);
/* 79 */       GL11.glPopMatrix();
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 85 */     renderTileEntityAt((TileDeconstructionTable)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileDeconstructionTableRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */