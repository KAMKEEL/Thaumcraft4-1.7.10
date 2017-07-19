/*    */ package thaumcraft.common.items.equipment;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemSpade;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import thaumcraft.api.IRepairable;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ public class ItemThaumiumShovel
/*    */   extends ItemSpade
/*    */   implements IRepairable
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemThaumiumShovel(Item.ToolMaterial enumtoolmaterial)
/*    */   {
/* 25 */     super(enumtoolmaterial);
/* 26 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public Set<String> getToolClasses(ItemStack stack)
/*    */   {
/* 31 */     return ImmutableSet.of("shovel");
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 38 */     this.icon = ir.func_94245_a("thaumcraft:thaumiumshovel");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 43 */     return this.icon;
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 49 */     return EnumRarity.uncommon;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 55 */     return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemThaumiumShovel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */