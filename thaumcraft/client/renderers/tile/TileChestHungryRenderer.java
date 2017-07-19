/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.model.ModelChest;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.tiles.TileChestHungry;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileChestHungryRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 20 */   private ModelChest chestModel = new ModelChest();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityChestAt(TileChestHungry chest, double par2, double par4, double par6, float par8)
/*    */   {
/* 28 */     int var9 = 0;
/*    */     
/* 30 */     if (!chest.func_145830_o())
/*    */     {
/* 32 */       var9 = 0;
/*    */     }
/*    */     else
/*    */     {
/* 36 */       Block var10 = chest.func_145838_q();
/* 37 */       var9 = chest.func_145832_p();
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 42 */     ModelChest var14 = this.chestModel;
/* 43 */     UtilsFX.bindTexture("textures/models/chesthungry.png");
/*    */     
/* 45 */     GL11.glPushMatrix();
/* 46 */     GL11.glEnable(32826);
/* 47 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 48 */     GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
/* 49 */     GL11.glScalef(1.0F, -1.0F, -1.0F);
/* 50 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 51 */     short var11 = 0;
/*    */     
/* 53 */     if (var9 == 2)
/*    */     {
/* 55 */       var11 = 180;
/*    */     }
/*    */     
/* 58 */     if (var9 == 3)
/*    */     {
/* 60 */       var11 = 0;
/*    */     }
/*    */     
/* 63 */     if (var9 == 4)
/*    */     {
/* 65 */       var11 = 90;
/*    */     }
/*    */     
/* 68 */     if (var9 == 5)
/*    */     {
/* 70 */       var11 = -90;
/*    */     }
/*    */     
/* 73 */     GL11.glRotatef(var11, 0.0F, 1.0F, 0.0F);
/* 74 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 75 */     float var12 = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * par8;
/*    */     
/*    */ 
/* 78 */     var12 = 1.0F - var12;
/* 79 */     var12 = 1.0F - var12 * var12 * var12;
/* 80 */     var14.field_78234_a.field_78795_f = (-(var12 * 3.1415927F / 2.0F));
/* 81 */     var14.func_78231_a();
/* 82 */     GL11.glDisable(32826);
/* 83 */     GL11.glPopMatrix();
/* 84 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 90 */     renderTileEntityChestAt((TileChestHungry)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileChestHungryRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */