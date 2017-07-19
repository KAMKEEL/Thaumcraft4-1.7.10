/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ public class ItemVoidArmor extends ItemArmor implements thaumcraft.api.IRepairable, thaumcraft.api.IRunicArmor, thaumcraft.api.IWarpingGear
/*    */ {
/*    */   public IIcon iconHelm;
/*    */   public IIcon iconChest;
/*    */   public IIcon iconLegs;
/*    */   public IIcon iconBoots;
/*    */   
/*    */   public ItemVoidArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*    */   {
/* 24 */     super(enumarmormaterial, j, k);
/* 25 */     func_77637_a(thaumcraft.common.Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 30 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 40 */     this.iconHelm = ir.func_94245_a("thaumcraft:voidhelm");
/* 41 */     this.iconChest = ir.func_94245_a("thaumcraft:voidchest");
/* 42 */     this.iconLegs = ir.func_94245_a("thaumcraft:voidlegs");
/* 43 */     this.iconBoots = ir.func_94245_a("thaumcraft:voidboots");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 48 */     return this.field_77881_a == 2 ? this.iconLegs : this.field_77881_a == 1 ? this.iconChest : this.field_77881_a == 0 ? this.iconHelm : this.iconBoots;
/*    */   }
/*    */   
/*    */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*    */   {
/* 53 */     if ((stack.func_77973_b() == ConfigItems.itemHelmetVoid) || (stack.func_77973_b() == ConfigItems.itemChestVoid) || (stack.func_77973_b() == ConfigItems.itemBootsVoid))
/*    */     {
/*    */ 
/* 56 */       return "thaumcraft:textures/models/void_1.png";
/*    */     }
/* 58 */     if (stack.func_77973_b() == ConfigItems.itemLegsVoid) {
/* 59 */       return "thaumcraft:textures/models/void_2.png";
/*    */     }
/* 61 */     return "thaumcraft:textures/models/void_1.png";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 68 */     return net.minecraft.item.EnumRarity.uncommon;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 74 */     return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 16)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*    */   {
/* 79 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*    */     
/* 81 */     if ((!world.field_72995_K) && (stack.func_77951_h()) && (entity.field_70173_aa % 20 == 0) && ((entity instanceof net.minecraft.entity.EntityLivingBase))) {
/* 82 */       stack.func_77972_a(-1, (net.minecraft.entity.EntityLivingBase)entity);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onArmorTick(World world, EntityPlayer player, ItemStack armor)
/*    */   {
/* 88 */     super.onArmorTick(world, player, armor);
/* 89 */     if ((!world.field_72995_K) && (armor.func_77960_j() > 0) && (player.field_70173_aa % 20 == 0)) {
/* 90 */       armor.func_77972_a(-1, player);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*    */   {
/* 96 */     return 1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemVoidArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */