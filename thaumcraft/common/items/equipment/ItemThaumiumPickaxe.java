/*    */ package thaumcraft.common.items.equipment;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemPickaxe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import thaumcraft.api.IRepairable;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemThaumiumPickaxe extends ItemPickaxe implements IRepairable
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemThaumiumPickaxe(Item.ToolMaterial enumtoolmaterial)
/*    */   {
/* 22 */     super(enumtoolmaterial);
/* 23 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public Set<String> getToolClasses(ItemStack stack)
/*    */   {
/* 28 */     return ImmutableSet.of("pickaxe");
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 35 */     this.icon = ir.func_94245_a("thaumcraft:thaumiumpick");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 40 */     return this.icon;
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 46 */     return EnumRarity.uncommon;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 52 */     return par2ItemStack.func_77969_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemThaumiumPickaxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */