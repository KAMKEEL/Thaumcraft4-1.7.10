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
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
/*     */ import thaumcraft.api.IGoggles;
/*     */ import thaumcraft.api.IRepairable;
/*     */ import thaumcraft.client.renderers.models.gear.ModelFortressArmor;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemFortressArmor extends ItemArmor implements IRepairable, thaumcraft.api.IRunicArmor, net.minecraftforge.common.ISpecialArmor, IGoggles, thaumcraft.api.nodes.IRevealer
/*     */ {
/*     */   public IIcon iconHelm;
/*     */   public IIcon iconChest;
/*     */   public IIcon iconLegs;
/*     */   
/*     */   public ItemFortressArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*     */   {
/*  35 */     super(enumarmormaterial, j, k);
/*  36 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  46 */     this.iconHelm = ir.func_94245_a("thaumcraft:thaumiumfortresshelm");
/*  47 */     this.iconChest = ir.func_94245_a("thaumcraft:thaumiumfortresschest");
/*  48 */     this.iconLegs = ir.func_94245_a("thaumcraft:thaumiumfortresslegs");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  54 */     return this.field_77881_a == 1 ? this.iconChest : this.field_77881_a == 0 ? this.iconHelm : this.iconLegs;
/*     */   }
/*     */   
/*     */ 
/*  58 */   ModelBiped model1 = null;
/*  59 */   ModelBiped model2 = null;
/*  60 */   ModelBiped model = null;
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/*     */   {
/*  66 */     int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
/*  67 */     if (this.model1 == null) {
/*  68 */       this.model1 = new ModelFortressArmor(1.0F);
/*     */     }
/*  70 */     if (this.model2 == null) {
/*  71 */       this.model2 = new ModelFortressArmor(0.5F);
/*     */     }
/*  73 */     if ((type == 1) || (type == 3)) {
/*  74 */       this.model = this.model1;
/*     */     } else {
/*  76 */       this.model = this.model2;
/*     */     }
/*     */     
/*  79 */     if (this.model != null) {
/*  80 */       this.model.field_78116_c.field_78806_j = (armorSlot == 0);
/*  81 */       this.model.field_78114_d.field_78806_j = (armorSlot == 0);
/*  82 */       this.model.field_78115_e.field_78806_j = ((armorSlot == 1) || (armorSlot == 2));
/*  83 */       this.model.field_78112_f.field_78806_j = (armorSlot == 1);
/*  84 */       this.model.field_78113_g.field_78806_j = (armorSlot == 1);
/*  85 */       this.model.field_78123_h.field_78806_j = (armorSlot == 2);
/*  86 */       this.model.field_78124_i.field_78806_j = (armorSlot == 2);
/*  87 */       this.model.field_78117_n = entityLiving.func_70093_af();
/*     */       
/*  89 */       this.model.field_78093_q = entityLiving.func_70115_ae();
/*  90 */       this.model.field_78091_s = entityLiving.func_70631_g_();
/*  91 */       this.model.field_78118_o = false;
/*  92 */       this.model.field_78120_m = (entityLiving.func_70694_bm() != null ? 1 : 0);
/*  93 */       if ((entityLiving instanceof EntityPlayer))
/*     */       {
/*  95 */         if (((EntityPlayer)entityLiving).func_71057_bx() > 0)
/*     */         {
/*  97 */           EnumAction enumaction = ((EntityPlayer)entityLiving).func_71011_bu().func_77975_n();
/*     */           
/*  99 */           if (enumaction == EnumAction.block)
/*     */           {
/* 101 */             this.model.field_78120_m = 3;
/*     */           }
/* 103 */           else if (enumaction == EnumAction.bow)
/*     */           {
/* 105 */             this.model.field_78118_o = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 112 */     return this.model;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/* 118 */     return "thaumcraft:textures/models/fortress_armor.png";
/*     */   }
/*     */   
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/* 123 */     return EnumRarity.rare;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 128 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("goggles"))) {
/* 129 */       list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("item.ItemGoggles.name"));
/*     */     }
/*     */     
/* 132 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("mask"))) {
/* 133 */       list.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a(new StringBuilder().append("item.HelmetFortress.mask.").append(stack.field_77990_d.func_74762_e("mask")).toString()));
/*     */     }
/*     */     
/*     */ 
/* 137 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/* 145 */     return par2ItemStack.func_77969_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/* 155 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
/*     */   {
/* 164 */     int priority = 0;
/* 165 */     double ratio = this.field_77879_b / 25.0D;
/*     */     
/* 167 */     if (source.func_82725_o() == true) {
/* 168 */       priority = 1;
/* 169 */       ratio = this.field_77879_b / 35.0D;
/*     */     }
/* 171 */     else if ((source.func_76347_k() == true) || (source.func_94541_c())) {
/* 172 */       priority = 1;
/* 173 */       ratio = this.field_77879_b / 20.0D;
/* 174 */     } else if (source.func_76363_c()) {
/* 175 */       priority = 0;
/* 176 */       ratio = 0.0D;
/*     */     }
/*     */     
/* 179 */     if ((player instanceof EntityPlayer)) {
/* 180 */       double set = 0.875D;
/* 181 */       for (int a = 1; a < 4; a++) {
/* 182 */         ItemStack piece = ((EntityPlayer)player).field_71071_by.field_70460_b[a];
/* 183 */         if ((piece != null) && ((piece.func_77973_b() instanceof ItemFortressArmor))) {
/* 184 */           set += 0.125D;
/* 185 */           if ((piece.func_77942_o()) && (piece.field_77990_d.func_74764_b("mask"))) {
/* 186 */             set += 0.05D;
/*     */           }
/*     */         }
/*     */       }
/* 190 */       ratio *= set;
/*     */     }
/*     */     
/* 193 */     return new ISpecialArmor.ArmorProperties(priority, ratio, armor.func_77958_k() + 1 - armor.func_77960_j());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
/*     */   {
/* 200 */     return this.field_77879_b;
/*     */   }
/*     */   
/*     */   public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
/*     */   {
/* 205 */     if (source != DamageSource.field_76379_h) {
/* 206 */       stack.func_77972_a(damage, entity);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean showNodes(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 213 */     return (itemstack.func_77942_o()) && (itemstack.field_77990_d.func_74764_b("goggles"));
/*     */   }
/*     */   
/*     */   public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 218 */     return (itemstack.func_77942_o()) && (itemstack.field_77990_d.func_74764_b("goggles"));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemFortressArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */