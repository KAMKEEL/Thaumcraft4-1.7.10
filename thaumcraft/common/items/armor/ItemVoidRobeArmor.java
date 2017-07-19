/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
/*     */ import thaumcraft.api.IGoggles;
/*     */ import thaumcraft.api.IVisDiscountGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.renderers.models.gear.ModelRobe;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemVoidRobeArmor extends ItemArmor implements thaumcraft.api.IRepairable, thaumcraft.api.IRunicArmor, IVisDiscountGear, IGoggles, thaumcraft.api.nodes.IRevealer, net.minecraftforge.common.ISpecialArmor, thaumcraft.api.IWarpingGear
/*     */ {
/*     */   public IIcon iconHelm;
/*     */   public IIcon iconChest;
/*     */   public IIcon iconLegs;
/*     */   public IIcon iconChestOver;
/*     */   public IIcon iconLegsOver;
/*     */   public IIcon iconBlank;
/*     */   
/*     */   public ItemVoidRobeArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*     */   {
/*  40 */     super(enumarmormaterial, j, k);
/*  41 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
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
/*  54 */     this.iconHelm = ir.func_94245_a("thaumcraft:voidrobehelm");
/*  55 */     this.iconBlank = ir.func_94245_a("thaumcraft:blank");
/*  56 */     this.iconChest = ir.func_94245_a("thaumcraft:voidrobechestover");
/*  57 */     this.iconLegs = ir.func_94245_a("thaumcraft:voidrobelegsover");
/*  58 */     this.iconChestOver = ir.func_94245_a("thaumcraft:voidrobechest");
/*  59 */     this.iconLegsOver = ir.func_94245_a("thaumcraft:voidrobelegs");
/*     */   }
/*     */   
/*     */ 
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  65 */     return type == null ? "thaumcraft:textures/models/void_robe_armor_overlay.png" : "thaumcraft:textures/models/void_robe_armor.png";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  72 */     return EnumRarity.epic;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/*  77 */     list.add(EnumChatFormatting.DARK_PURPLE + net.minecraft.util.StatCollector.func_74838_a("tc.visdiscount") + ": " + getVisDiscount(stack, player, null) + "%");
/*  78 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  84 */     return par2ItemStack.func_77969_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 1, 16)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*     */   {
/*  92 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*     */     
/*  94 */     if ((!world.field_72995_K) && (stack.func_77951_h()) && (entity.field_70173_aa % 20 == 0) && ((entity instanceof EntityLivingBase))) {
/*  95 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onArmorTick(World world, EntityPlayer player, ItemStack armor)
/*     */   {
/* 101 */     super.onArmorTick(world, player, armor);
/* 102 */     if ((!world.field_72995_K) && (armor.func_77960_j() > 0) && (player.field_70173_aa % 20 == 0)) {
/* 103 */       armor.func_77972_a(-1, player);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/* 111 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean showNodes(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 116 */     int type = ((ItemArmor)itemstack.func_77973_b()).field_77881_a;
/* 117 */     return type == 0;
/*     */   }
/*     */   
/*     */   public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 122 */     int type = ((ItemArmor)itemstack.func_77973_b()).field_77881_a;
/* 123 */     return type == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
/*     */   {
/* 130 */     return 5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 135 */   ModelBiped model1 = null;
/* 136 */   ModelBiped model2 = null;
/* 137 */   ModelBiped model = null;
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/*     */   {
/* 144 */     int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
/*     */     
/* 146 */     if (this.model1 == null) {
/* 147 */       this.model1 = new ModelRobe(1.0F);
/*     */     }
/* 149 */     if (this.model2 == null) {
/* 150 */       this.model2 = new ModelRobe(0.5F);
/*     */     }
/* 152 */     if ((type == 1) || (type == 3)) {
/* 153 */       this.model = this.model1;
/*     */     } else {
/* 155 */       this.model = this.model2;
/*     */     }
/*     */     
/* 158 */     if (this.model != null) {
/* 159 */       this.model.field_78116_c.field_78806_j = (armorSlot == 0);
/* 160 */       this.model.field_78114_d.field_78806_j = (armorSlot == 0);
/* 161 */       this.model.field_78115_e.field_78806_j = ((armorSlot == 1) || (armorSlot == 2));
/* 162 */       this.model.field_78112_f.field_78806_j = (armorSlot == 1);
/* 163 */       this.model.field_78113_g.field_78806_j = (armorSlot == 1);
/* 164 */       this.model.field_78123_h.field_78806_j = (armorSlot == 2);
/* 165 */       this.model.field_78124_i.field_78806_j = (armorSlot == 2);
/* 166 */       this.model.field_78117_n = entityLiving.func_70093_af();
/*     */       
/* 168 */       this.model.field_78093_q = entityLiving.func_70115_ae();
/* 169 */       this.model.field_78091_s = entityLiving.func_70631_g_();
/* 170 */       this.model.field_78118_o = false;
/* 171 */       this.model.field_78120_m = (entityLiving.func_70694_bm() != null ? 1 : 0);
/* 172 */       if ((entityLiving instanceof EntityPlayer))
/*     */       {
/* 174 */         if (((EntityPlayer)entityLiving).func_71057_bx() > 0)
/*     */         {
/* 176 */           EnumAction enumaction = ((EntityPlayer)entityLiving).func_71011_bu().func_77975_n();
/*     */           
/* 178 */           if (enumaction == EnumAction.block)
/*     */           {
/* 180 */             this.model.field_78120_m = 3;
/*     */           }
/* 182 */           else if (enumaction == EnumAction.bow)
/*     */           {
/* 184 */             this.model.field_78118_o = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 191 */     return this.model;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/* 200 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82816_b_(ItemStack par1ItemStack)
/*     */   {
/* 206 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon func_77618_c(int par1, int par2)
/*     */   {
/* 212 */     return this.field_77881_a == 2 ? this.iconLegsOver : this.field_77881_a == 1 ? this.iconChestOver : par2 == 0 ? this.iconHelm : this.field_77881_a == 2 ? this.iconLegs : this.field_77881_a == 1 ? this.iconChest : this.iconBlank;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_82814_b(ItemStack par1ItemStack)
/*     */   {
/* 219 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/* 221 */     if (nbttagcompound == null)
/*     */     {
/* 223 */       return 6961280;
/*     */     }
/*     */     
/*     */ 
/* 227 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/* 228 */     return nbttagcompound1.func_74764_b("color") ? nbttagcompound1.func_74762_e("color") : nbttagcompound1 == null ? 6961280 : 6961280;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_82815_c(ItemStack par1ItemStack)
/*     */   {
/* 235 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/* 237 */     if (nbttagcompound != null)
/*     */     {
/* 239 */       NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */       
/* 241 */       if (nbttagcompound1.func_74764_b("color"))
/*     */       {
/* 243 */         nbttagcompound1.func_82580_o("color");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_82813_b(ItemStack par1ItemStack, int par2)
/*     */   {
/* 252 */     NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
/*     */     
/* 254 */     if (nbttagcompound == null)
/*     */     {
/* 256 */       nbttagcompound = new NBTTagCompound();
/* 257 */       par1ItemStack.func_77982_d(nbttagcompound);
/*     */     }
/*     */     
/* 260 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */     
/* 262 */     if (!nbttagcompound.func_74764_b("display"))
/*     */     {
/* 264 */       nbttagcompound.func_74782_a("display", nbttagcompound1);
/*     */     }
/*     */     
/* 267 */     nbttagcompound1.func_74768_a("color", par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
/*     */   {
/* 276 */     int priority = 0;
/* 277 */     double ratio = this.field_77879_b / 25.0D;
/*     */     
/* 279 */     if (source.func_82725_o()) {
/* 280 */       priority = 1;
/* 281 */       ratio = this.field_77879_b / 35.0D;
/* 282 */     } else if (source.func_76363_c()) {
/* 283 */       priority = 0;
/* 284 */       ratio = 0.0D;
/*     */     }
/* 286 */     return new ISpecialArmor.ArmorProperties(priority, ratio, armor.func_77958_k() + 1 - armor.func_77960_j());
/*     */   }
/*     */   
/*     */   public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
/*     */   {
/* 291 */     return this.field_77879_b;
/*     */   }
/*     */   
/*     */   public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
/*     */   {
/* 296 */     if (source != DamageSource.field_76379_h) { stack.func_77972_a(damage, entity);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*     */   {
/* 304 */     if ((!world.field_72995_K) && (world.func_147439_a(x, y, z) == Blocks.field_150383_bp) && (world.func_72805_g(x, y, z) > 0))
/*     */     {
/* 306 */       func_82815_c(stack);
/* 307 */       world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) - 1, 2);
/* 308 */       world.func_147453_f(x, y, z, Blocks.field_150383_bp);
/* 309 */       return true;
/*     */     }
/* 311 */     return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*     */   {
/* 318 */     return 2;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemVoidRobeArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */