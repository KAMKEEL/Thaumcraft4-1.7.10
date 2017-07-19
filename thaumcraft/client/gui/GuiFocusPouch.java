/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiFocusPouch extends GuiContainer
/*    */ {
/*    */   private int blockSlot;
/*    */   
/*    */   public GuiFocusPouch(InventoryPlayer par1InventoryPlayer, World world, int x, int y, int z)
/*    */   {
/* 20 */     super(new thaumcraft.common.container.ContainerFocusPouch(par1InventoryPlayer, world, x, y, z));
/* 21 */     this.blockSlot = par1InventoryPlayer.field_70461_c;
/* 22 */     this.field_146999_f = 175;
/* 23 */     this.field_147000_g = 232;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void func_146979_b(int par1, int par2)
/*    */   {
/* 30 */     UtilsFX.bindTexture("textures/gui/gui_focuspouch.png");
/* 31 */     float t = this.field_73735_i;
/* 32 */     this.field_73735_i = 200.0F;
/* 33 */     GL11.glEnable(3042);
/* 34 */     func_73729_b(8 + this.blockSlot * 18, 209, 240, 0, 16, 16);
/* 35 */     GL11.glDisable(3042);
/* 36 */     this.field_73735_i = t;
/*    */   }
/*    */   
/*    */   protected boolean func_146983_a(int par1)
/*    */   {
/* 41 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 50 */     if (this.field_146297_k.field_71439_g.field_71071_by.field_70462_a[this.blockSlot] == null) this.field_146297_k.field_71439_g.func_71053_j();
/* 51 */     UtilsFX.bindTexture("textures/gui/gui_focuspouch.png");
/* 52 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 53 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/* 54 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/* 55 */     GL11.glEnable(3042);
/* 56 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/* 57 */     GL11.glDisable(3042);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiFocusPouch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */