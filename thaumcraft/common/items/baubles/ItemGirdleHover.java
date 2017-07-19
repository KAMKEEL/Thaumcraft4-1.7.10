/*    */ package thaumcraft.common.items.baubles;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import thaumcraft.api.IRunicArmor;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemGirdleHover extends Item implements IBauble, IRunicArmor
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemGirdleHover()
/*    */   {
/* 24 */     this.field_77777_bU = 1;
/* 25 */     this.canRepair = false;
/* 26 */     func_77656_e(0);
/* 27 */     func_77637_a(Thaumcraft.tabTC);
/* 28 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 35 */     this.icon = ir.func_94245_a("thaumcraft:hovergirdle");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1)
/*    */   {
/* 41 */     return this.icon;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 47 */     par3List.add(new ItemStack(this, 1, 0));
/*    */   }
/*    */   
/*    */   public EnumRarity func_77613_e(ItemStack par1ItemStack)
/*    */   {
/* 52 */     return EnumRarity.rare;
/*    */   }
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack itemstack)
/*    */   {
/* 57 */     return BaubleType.BELT;
/*    */   }
/*    */   
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 62 */     if (player.field_70143_R > 0.0F) {
/* 63 */       player.field_70143_R -= 0.33F;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}
/*    */   
/*    */ 
/*    */   public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}
/*    */   
/*    */   public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 75 */     return true;
/*    */   }
/*    */   
/*    */   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 80 */     return true;
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 85 */     return 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/baubles/ItemGirdleHover.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */