/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.item.ItemFood;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemNuggetEdible extends ItemFood
/*    */ {
/*    */   public final int field_77855_a;
/*    */   public final String iconName;
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemNuggetEdible(String iconName)
/*    */   {
/* 19 */     super(1, 0.3F, false);
/* 20 */     this.field_77855_a = 10;
/* 21 */     this.iconName = iconName;
/* 22 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77626_a(ItemStack par1ItemStack)
/*    */   {
/* 31 */     return this.field_77855_a;
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 38 */     this.icon = ir.func_94245_a("thaumcraft:" + this.iconName);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int meta) {
/* 43 */     return this.icon;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemNuggetEdible.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */