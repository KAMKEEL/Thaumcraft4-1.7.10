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
/*    */ import thaumcraft.api.ItemRunic;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemGirdleRunic
/*    */   extends ItemRunic implements IBauble
/*    */ {
/*    */   public ItemGirdleRunic()
/*    */   {
/* 23 */     super(10);
/* 24 */     this.field_77777_bU = 1;
/* 25 */     this.canRepair = false;
/* 26 */     func_77656_e(0);
/* 27 */     func_77637_a(Thaumcraft.tabTC);
/* 28 */     func_77627_a(true);
/*    */   }
/*    */   
/* 31 */   public IIcon[] icon = new IIcon[2];
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir) {
/* 35 */     this.icon[0] = ir.func_94245_a("thaumcraft:runic_girdle");
/* 36 */     this.icon[1] = ir.func_94245_a("thaumcraft:runic_girdle_kinetic");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1)
/*    */   {
/* 42 */     return this.icon[par1];
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 48 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 54 */     par3List.add(new ItemStack(this, 1, 0));
/* 55 */     par3List.add(new ItemStack(this, 1, 1));
/*    */   }
/*    */   
/*    */   public EnumRarity func_77613_e(ItemStack par1ItemStack)
/*    */   {
/* 60 */     return EnumRarity.rare;
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 65 */     return itemstack.func_77960_j() == 0 ? 10 : 9;
/*    */   }
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack itemstack)
/*    */   {
/* 70 */     return BaubleType.BELT;
/*    */   }
/*    */   
/*    */ 
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}
/*    */   
/*    */   public void onEquipped(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 78 */     Thaumcraft.instance.runicEventHandler.isDirty = true;
/*    */   }
/*    */   
/*    */   public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 83 */     Thaumcraft.instance.runicEventHandler.isDirty = true;
/*    */   }
/*    */   
/*    */   public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 88 */     return true;
/*    */   }
/*    */   
/*    */   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 93 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/baubles/ItemGirdleRunic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */