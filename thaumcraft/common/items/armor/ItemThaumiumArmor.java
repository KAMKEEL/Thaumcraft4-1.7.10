/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ public class ItemThaumiumArmor extends ItemArmor implements thaumcraft.api.IRepairable, thaumcraft.api.IRunicArmor
/*    */ {
/*    */   public IIcon iconHelm;
/*    */   public IIcon iconChest;
/*    */   public IIcon iconLegs;
/*    */   public IIcon iconBoots;
/*    */   
/*    */   public ItemThaumiumArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*    */   {
/* 20 */     super(enumarmormaterial, j, k);
/* 21 */     func_77637_a(thaumcraft.common.Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 26 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 36 */     this.iconHelm = ir.func_94245_a("thaumcraft:thaumiumhelm");
/* 37 */     this.iconChest = ir.func_94245_a("thaumcraft:thaumiumchest");
/* 38 */     this.iconLegs = ir.func_94245_a("thaumcraft:thaumiumlegs");
/* 39 */     this.iconBoots = ir.func_94245_a("thaumcraft:thaumiumboots");
/*    */   }
/*    */   
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 44 */     return this.field_77881_a == 2 ? this.iconLegs : this.field_77881_a == 1 ? this.iconChest : this.field_77881_a == 0 ? this.iconHelm : this.iconBoots;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getArmorTexture(ItemStack stack, net.minecraft.entity.Entity entity, int slot, String type)
/*    */   {
/* 50 */     if ((stack.func_77973_b() == ConfigItems.itemHelmetThaumium) || (stack.func_77973_b() == ConfigItems.itemChestThaumium) || (stack.func_77973_b() == ConfigItems.itemBootsThaumium))
/*    */     {
/*    */ 
/* 53 */       return "thaumcraft:textures/models/thaumium_1.png";
/*    */     }
/* 55 */     if (stack.func_77973_b() == ConfigItems.itemLegsThaumium) {
/* 56 */       return "thaumcraft:textures/models/thaumium_2.png";
/*    */     }
/* 58 */     return "thaumcraft:textures/models/thaumium_1.png";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 65 */     return net.minecraft.item.EnumRarity.uncommon;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 71 */     return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemThaumiumArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */