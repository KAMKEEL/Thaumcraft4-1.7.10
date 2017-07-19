/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.util.IIcon;
/*    */ import thaumcraft.api.IGoggles;
/*    */ import thaumcraft.api.IRepairable;
/*    */ import thaumcraft.api.IVisDiscountGear;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.nodes.IRevealer;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemGoggles extends ItemArmor implements IRepairable, IVisDiscountGear, IRevealer, IGoggles, thaumcraft.api.IRunicArmor
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemGoggles(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*    */   {
/* 30 */     super(enumarmormaterial, j, k);
/* 31 */     func_77656_e(350);
/* 32 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 37 */     return 0;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 42 */     super.func_77624_a(stack, player, list, par4);
/* 43 */     list.add(EnumChatFormatting.DARK_PURPLE + net.minecraft.util.StatCollector.func_74838_a("tc.visdiscount") + ": " + getVisDiscount(stack, player, null) + "%");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 51 */     this.icon = ir.func_94245_a("thaumcraft:gogglesrevealing");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 56 */     return this.icon;
/*    */   }
/*    */   
/*    */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*    */   {
/* 61 */     return "thaumcraft:textures/models/goggles.png";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 68 */     return EnumRarity.rare;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 75 */     return par2ItemStack.func_77969_a(new ItemStack(Items.field_151043_k)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */   public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
/*    */   {
/* 80 */     return 5;
/*    */   }
/*    */   
/*    */   public boolean showNodes(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 85 */     return true;
/*    */   }
/*    */   
/*    */   public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 90 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemGoggles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */