/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.container.ContainerHandMirror;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiHandMirror
/*    */   extends GuiContainer
/*    */ {
/*    */   public GuiHandMirror(InventoryPlayer par1InventoryPlayer, World world, int x, int y, int z)
/*    */   {
/* 20 */     super(new ContainerHandMirror(par1InventoryPlayer, world, x, y, z));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void drawGuiContainerForegroundLayer() {}
/*    */   
/*    */ 
/*    */ 
/*    */   protected boolean func_146983_a(int par1)
/*    */   {
/* 32 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 40 */     UtilsFX.bindTexture("textures/gui/guihandmirror.png");
/* 41 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 42 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/* 43 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/* 44 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiHandMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */