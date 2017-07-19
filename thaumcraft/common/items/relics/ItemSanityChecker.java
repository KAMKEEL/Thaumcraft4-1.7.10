/*    */ package thaumcraft.common.items.relics;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSanityChecker
/*    */   extends Item
/*    */ {
/*    */   private IIcon icon;
/*    */   
/*    */   public ItemSanityChecker()
/*    */   {
/* 30 */     func_77625_d(1);
/* 31 */     func_77627_a(false);
/* 32 */     func_77656_e(0);
/* 33 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister par1IconRegister)
/*    */   {
/* 40 */     this.icon = par1IconRegister.func_94245_a("thaumcraft:sanitychecker");
/*    */   }
/*    */   
/*    */ 
/*    */   public IIcon func_77617_a(int par1)
/*    */   {
/* 46 */     return this.icon;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 52 */     par3List.add(new ItemStack(this));
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 58 */     return EnumRarity.uncommon;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/relics/ItemSanityChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */