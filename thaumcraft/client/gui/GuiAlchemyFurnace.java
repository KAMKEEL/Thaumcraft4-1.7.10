/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.container.ContainerAlchemyFurnace;
/*    */ import thaumcraft.common.tiles.TileAlchemyFurnace;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiAlchemyFurnace
/*    */   extends GuiContainer
/*    */ {
/*    */   private TileAlchemyFurnace furnaceInventory;
/*    */   
/*    */   public GuiAlchemyFurnace(InventoryPlayer par1InventoryPlayer, TileAlchemyFurnace par2TileEntityFurnace)
/*    */   {
/* 22 */     super(new ContainerAlchemyFurnace(par1InventoryPlayer, par2TileEntityFurnace));
/* 23 */     this.furnaceInventory = par2TileEntityFurnace;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_146979_b(int par1, int par2) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 41 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 42 */     UtilsFX.bindTexture("textures/gui/gui_alchemyfurnace.png");
/* 43 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 44 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*    */     
/* 46 */     GL11.glEnable(3042);
/* 47 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/*    */ 
/* 50 */     if (this.furnaceInventory.isBurning())
/*    */     {
/* 52 */       int i1 = this.furnaceInventory.getBurnTimeRemainingScaled(20);
/* 53 */       func_73729_b(k + 80, l + 26 + 20 - i1, 176, 20 - i1, 16, i1);
/*    */     }
/*    */     
/* 56 */     int i1 = this.furnaceInventory.getCookProgressScaled(46);
/* 57 */     func_73729_b(k + 106, l + 13 + 46 - i1, 216, 46 - i1, 9, i1);
/*    */     
/* 59 */     i1 = this.furnaceInventory.getContentsScaled(48);
/* 60 */     func_73729_b(k + 61, l + 12 + 48 - i1, 200, 48 - i1, 8, i1);
/*    */     
/*    */ 
/* 63 */     func_73729_b(k + 60, l + 8, 232, 0, 10, 55);
/* 64 */     GL11.glDisable(3042);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiAlchemyFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */