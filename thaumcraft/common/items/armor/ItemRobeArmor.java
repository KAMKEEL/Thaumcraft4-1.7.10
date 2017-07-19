/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ 
/*     */ public class ItemRobeArmor extends ItemArmor implements thaumcraft.api.IRepairable, thaumcraft.api.IVisDiscountGear, thaumcraft.api.IRunicArmor
/*     */ {
/*     */   public IIcon iconChest;
/*     */   public IIcon iconLegs;
/*     */   public IIcon iconBoots;
/*     */   public IIcon iconChestOver;
/*     */   public IIcon iconLegsOver;
/*     */   public IIcon iconBootsOver;
/*     */   
/*     */   public ItemRobeArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*     */   {
/*  29 */     super(enumarmormaterial, j, k);
/*  30 */     func_77637_a(thaumcraft.common.Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/*  35 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  47 */     this.iconChest = ir.func_94245_a("thaumcraft:clothchest");
/*  48 */     this.iconLegs = ir.func_94245_a("thaumcraft:clothlegs");
/*  49 */     this.iconBoots = ir.func_94245_a("thaumcraft:clothboots");
/*  50 */     this.iconChestOver = ir.func_94245_a("thaumcraft:clothchestover");
/*  51 */     this.iconLegsOver = ir.func_94245_a("thaumcraft:clothlegsover");
/*  52 */     this.iconBootsOver = ir.func_94245_a("thaumcraft:clothbootsover");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  57 */     return this.field_77881_a == 2 ? this.iconLegs : this.field_77881_a == 1 ? this.iconChest : this.iconBoots;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/*  64 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82816_b_(ItemStack par1ItemStack)
/*     */   {
/*  70 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon func_77618_c(int par1, int par2)
/*     */   {
/*  76 */     return this.field_77881_a == 2 ? this.iconLegsOver : this.field_77881_a == 1 ? this.iconChestOver : par2 == 0 ? this.iconBoots : this.field_77881_a == 2 ? this.iconLegs : this.field_77881_a == 1 ? this.iconChest : this.iconBootsOver;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_82814_b(ItemStack par1ItemStack)
/*     */   {
/*  83 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/*  85 */     if (nbttagcompound == null)
/*     */     {
/*  87 */       return 6961280;
/*     */     }
/*     */     
/*     */ 
/*  91 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*  92 */     return nbttagcompound1.func_74764_b("color") ? nbttagcompound1.func_74762_e("color") : nbttagcompound1 == null ? 6961280 : 6961280;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_82815_c(ItemStack par1ItemStack)
/*     */   {
/*  99 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/* 101 */     if (nbttagcompound != null)
/*     */     {
/* 103 */       NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */       
/* 105 */       if (nbttagcompound1.func_74764_b("color"))
/*     */       {
/* 107 */         nbttagcompound1.func_82580_o("color");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_82813_b(ItemStack par1ItemStack, int par2)
/*     */   {
/* 116 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/* 118 */     if (nbttagcompound == null)
/*     */     {
/* 120 */       nbttagcompound = new NBTTagCompound();
/* 121 */       par1ItemStack.func_77982_d(nbttagcompound);
/*     */     }
/*     */     
/* 124 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */     
/* 126 */     if (!nbttagcompound.func_74764_b("display"))
/*     */     {
/* 128 */       nbttagcompound.func_74782_a("display", nbttagcompound1);
/*     */     }
/*     */     
/* 131 */     nbttagcompound1.func_74768_a("color", par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/* 138 */     if ((stack.func_77973_b() == ConfigItems.itemChestRobe) || (stack.func_77973_b() == ConfigItems.itemBootsRobe))
/*     */     {
/* 140 */       return type == null ? "thaumcraft:textures/models/robes_1.png" : "thaumcraft:textures/models/robes_1_overlay.png";
/*     */     }
/* 142 */     if (stack.func_77973_b() == ConfigItems.itemLegsRobe) {
/* 143 */       return type == null ? "thaumcraft:textures/models/robes_2.png" : "thaumcraft:textures/models/robes_2_overlay.png";
/*     */     }
/* 145 */     return type == null ? "thaumcraft:textures/models/robes_1.png" : "thaumcraft:textures/models/robes_1_overlay.png";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/* 152 */     return net.minecraft.item.EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/* 158 */     return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 7)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */   public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
/*     */   {
/* 163 */     return this.field_77881_a == 3 ? 1 : 2;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 168 */     list.add(net.minecraft.util.EnumChatFormatting.DARK_PURPLE + net.minecraft.util.StatCollector.func_74838_a("tc.visdiscount") + ": " + getVisDiscount(stack, player, null) + "%");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*     */   {
/* 175 */     if ((!world.field_72995_K) && (world.func_147439_a(x, y, z) == net.minecraft.init.Blocks.field_150383_bp) && (world.func_72805_g(x, y, z) > 0))
/*     */     {
/* 177 */       func_82815_c(stack);
/* 178 */       world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) - 1, 2);
/* 179 */       world.func_147453_f(x, y, z, net.minecraft.init.Blocks.field_150383_bp);
/* 180 */       return true;
/*     */     }
/* 182 */     return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemRobeArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */