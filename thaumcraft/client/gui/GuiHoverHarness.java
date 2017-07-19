/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.container.ContainerHoverHarness;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */ public class GuiHoverHarness extends GuiContainer
/*    */ {
/*    */   private int blockSlot;
/*    */   
/*    */   public GuiHoverHarness(InventoryPlayer par1InventoryPlayer, World world, int x, int y, int z)
/*    */   {
/* 20 */     super(new ContainerHoverHarness(par1InventoryPlayer, world, x, y, z));
/* 21 */     this.blockSlot = par1InventoryPlayer.field_70461_c;
/*    */   }
/*    */   
/*    */   protected void func_146979_b(int par1, int par2)
/*    */   {
/* 26 */     UtilsFX.bindTexture("textures/gui/guihoverharness.png");
/* 27 */     float t = this.field_73735_i;
/* 28 */     this.field_73735_i = 200.0F;
/* 29 */     GL11.glEnable(3042);
/* 30 */     func_73729_b(8 + this.blockSlot * 18, 142, 240, 0, 16, 16);
/* 31 */     GL11.glDisable(3042);
/* 32 */     this.field_73735_i = t;
/*    */   }
/*    */   
/*    */   protected boolean func_146983_a(int par1)
/*    */   {
/* 37 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 45 */     if (this.field_146297_k.field_71439_g.field_71071_by.field_70462_a[this.blockSlot] == null) this.field_146297_k.field_71439_g.func_71053_j();
/* 46 */     UtilsFX.bindTexture("textures/gui/guihoverharness.png");
/* 47 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 48 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/* 49 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/* 50 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiHoverHarness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */