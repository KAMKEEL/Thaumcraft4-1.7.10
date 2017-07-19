/*    */ package thaumcraft.common.items.equipment;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemHoe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class ItemThaumiumHoe extends ItemHoe implements thaumcraft.api.IRepairable
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemThaumiumHoe(Item.ToolMaterial enumtoolmaterial)
/*    */   {
/* 17 */     super(enumtoolmaterial);
/* 18 */     func_77637_a(thaumcraft.common.Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 25 */     this.icon = ir.func_94245_a("thaumcraft:thaumiumhoe");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 30 */     return this.icon;
/*    */   }
/*    */   
/*    */   public int func_77619_b()
/*    */   {
/* 35 */     return 5;
/*    */   }
/*    */   
/*    */ 
/*    */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 41 */     return net.minecraft.item.EnumRarity.uncommon;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 47 */     return par2ItemStack.func_77969_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemThaumiumHoe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */