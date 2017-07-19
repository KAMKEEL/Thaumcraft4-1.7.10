/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.wands.FocusUpgradeType;
/*    */ import thaumcraft.api.wands.ItemFocusBasic;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.container.ContainerArcaneBore;
/*    */ import thaumcraft.common.items.equipment.ItemElementalPickaxe;
/*    */ import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
/*    */ import thaumcraft.common.tiles.TileArcaneBore;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiArcaneBore extends GuiContainer
/*    */ {
/*    */   private TileArcaneBore bore;
/*    */   
/*    */   public GuiArcaneBore(InventoryPlayer par1InventoryPlayer, TileArcaneBore e)
/*    */   {
/* 27 */     super(new ContainerArcaneBore(par1InventoryPlayer, e));
/* 28 */     this.bore = e;
/* 29 */     this.field_146999_f = 176;
/* 30 */     this.field_147000_g = 141;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void drawGuiContainerForegroundLayer() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 45 */     UtilsFX.bindTexture("textures/gui/gui_arcanebore.png");
/* 46 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 47 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/* 48 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/* 49 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/* 51 */     if ((this.bore.func_70301_a(1) != null) && (this.bore.func_70301_a(1).func_77960_j() + 1 >= this.bore.func_70301_a(1).func_77958_k())) {
/* 52 */       func_73729_b(var5 + 74, var6 + 18, 184, 0, 16, 16);
/*    */     }
/*    */     
/* 55 */     GL11.glPushMatrix();
/* 56 */     GL11.glTranslatef(var5 + 112, var6 + 8, 505.0F);
/* 57 */     GL11.glScalef(0.5F, 0.5F, 0.0F);
/* 58 */     String text = "Width: " + (1 + (this.bore.area + this.bore.maxRadius) * 2);
/* 59 */     this.field_146289_q.func_78261_a(text, 0, 0, 16777215);
/*    */     
/* 61 */     text = "Speed: +" + this.bore.speed;
/* 62 */     this.field_146289_q.func_78261_a(text, 0, 10, 16777215);
/* 63 */     text = "Other properties:";
/* 64 */     this.field_146289_q.func_78261_a(text, 0, 24, 16777215);
/* 65 */     int base = 0;
/* 66 */     if (((this.bore.func_70301_a(1) != null) && ((this.bore.func_70301_a(1).func_77973_b() instanceof ItemElementalPickaxe))) || ((this.bore.func_70301_a(0) != null) && ((this.bore.func_70301_a(0).func_77973_b() instanceof ItemFocusBasic)) && (((ItemFocusBasic)this.bore.func_70301_a(0).func_77973_b()).isUpgradedWith(this.bore.func_70301_a(0), ItemFocusExcavation.dowsing))))
/*    */     {
/*    */ 
/* 69 */       text = "Native Clusters";
/* 70 */       this.field_146289_q.func_78261_a(text, 4, 34 + base, 12632256);
/* 71 */       base += 9;
/*    */     }
/* 73 */     if (this.bore.fortune > 0) {
/* 74 */       text = "Fortune " + this.bore.fortune;
/* 75 */       this.field_146289_q.func_78261_a(text, 4, 34 + base, 15648330);
/* 76 */       base += 9;
/*    */     }
/*    */     
/* 79 */     if (((this.bore.func_70301_a(1) != null) && (EnchantmentHelper.func_77506_a(Enchantment.field_77348_q.field_77352_x, this.bore.func_70301_a(1)) > 0)) || ((this.bore.func_70301_a(0) != null) && ((this.bore.func_70301_a(0).func_77973_b() instanceof ItemFocusBasic)) && (((ItemFocusBasic)this.bore.func_70301_a(0).func_77973_b()).isUpgradedWith(this.bore.func_70301_a(0), FocusUpgradeType.silktouch))))
/*    */     {
/*    */ 
/* 82 */       text = "Silk Touch";
/* 83 */       this.field_146289_q.func_78261_a(text, 4, 34 + base, 8421631);
/* 84 */       base += 9;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 89 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 90 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiArcaneBore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */