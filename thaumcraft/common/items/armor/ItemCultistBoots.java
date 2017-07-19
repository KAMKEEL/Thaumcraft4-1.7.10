/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.util.IIcon;
/*    */ import thaumcraft.api.IRepairable;
/*    */ import thaumcraft.api.IVisDiscountGear;
/*    */ import thaumcraft.api.IWarpingGear;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemCultistBoots extends ItemArmor implements IRepairable, thaumcraft.api.IRunicArmor, IWarpingGear, IVisDiscountGear
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemCultistBoots(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*    */   {
/* 28 */     super(enumarmormaterial, j, k);
/* 29 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 36 */     this.icon = ir.func_94245_a("thaumcraft:cultistboots");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 41 */     return this.icon;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*    */   {
/* 47 */     return "thaumcraft:textures/models/cultistboots.png";
/*    */   }
/*    */   
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 52 */     return par2ItemStack.func_77969_a(new ItemStack(Items.field_151042_j)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 59 */     return EnumRarity.uncommon;
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 64 */     return 0;
/*    */   }
/*    */   
/*    */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*    */   {
/* 69 */     return 1;
/*    */   }
/*    */   
/*    */ 
/*    */   public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
/*    */   {
/* 75 */     return 1;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 80 */     list.add(EnumChatFormatting.DARK_PURPLE + net.minecraft.util.StatCollector.func_74838_a("tc.visdiscount") + ": " + getVisDiscount(stack, player, null) + "%");
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemCultistBoots.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */