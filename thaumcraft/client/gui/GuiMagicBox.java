/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.inventory.ContainerChest;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiMagicBox extends GuiContainer
/*    */ {
/* 17 */   private static final ResourceLocation field_147017_u = new ResourceLocation("textures/gui/container/generic_54.png");
/*    */   
/*    */   private IInventory playerInventory;
/*    */   
/*    */   private IInventory lowerChestInventory;
/*    */   
/*    */   private int inventoryRows;
/*    */   
/*    */   public GuiMagicBox(IInventory par1IInventory, IInventory par2IInventory)
/*    */   {
/* 27 */     super(new ContainerChest(par1IInventory, par2IInventory));
/* 28 */     this.playerInventory = par1IInventory;
/* 29 */     this.lowerChestInventory = par2IInventory;
/* 30 */     this.field_146291_p = false;
/* 31 */     short short1 = 222;
/* 32 */     int i = short1 - 108;
/* 33 */     this.inventoryRows = (par2IInventory.func_70302_i_() / 9);
/* 34 */     this.field_147000_g = (i + 54);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_146979_b(int p_146979_1_, int p_146979_2_)
/*    */   {
/* 42 */     this.field_146289_q.func_78276_b(this.lowerChestInventory.func_145818_k_() ? this.lowerChestInventory.func_145825_b() : I18n.func_135052_a(this.lowerChestInventory.func_145825_b(), new Object[0]), 8, 6, 4210752);
/* 43 */     this.field_146289_q.func_78276_b(this.playerInventory.func_145818_k_() ? this.playerInventory.func_145825_b() : I18n.func_135052_a(this.playerInventory.func_145825_b(), new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
/*    */   }
/*    */   
/*    */   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_)
/*    */   {
/* 48 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 49 */     this.field_146297_k.func_110434_K().func_110577_a(field_147017_u);
/* 50 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 51 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/* 52 */     func_73729_b(k, l, 0, 0, this.field_146999_f, 71);
/* 53 */     func_73729_b(k, l + 54 + 17, 0, 126, this.field_146999_f, 96);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiMagicBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */