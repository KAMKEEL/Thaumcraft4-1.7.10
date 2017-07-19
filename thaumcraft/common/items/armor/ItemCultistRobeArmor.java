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
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import thaumcraft.api.IRepairable;
/*     */ import thaumcraft.api.IRunicArmor;
/*     */ import thaumcraft.api.IVisDiscountGear;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.renderers.models.gear.ModelRobe;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemCultistRobeArmor
/*     */   extends ItemArmor
/*     */   implements IRepairable, IRunicArmor, IVisDiscountGear, IWarpingGear
/*     */ {
/*     */   public IIcon iconHelm;
/*     */   public IIcon iconChest;
/*     */   public IIcon iconLegs;
/*     */   public IIcon iconChestOver;
/*     */   public IIcon iconLegsOver;
/*     */   public IIcon iconBlank;
/*     */   
/*     */   public ItemCultistRobeArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*     */   {
/*  41 */     super(enumarmormaterial, j, k);
/*  42 */     func_77637_a(Thaumcraft.tabTC);
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
/*  55 */     this.iconHelm = ir.func_94245_a("thaumcraft:cultistrobehelm");
/*  56 */     this.iconChest = ir.func_94245_a("thaumcraft:cultistrobechest");
/*  57 */     this.iconLegs = ir.func_94245_a("thaumcraft:cultistrobelegs");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  63 */     return this.field_77881_a == 1 ? this.iconChest : this.field_77881_a == 0 ? this.iconHelm : this.iconLegs;
/*     */   }
/*     */   
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  68 */     return "thaumcraft:textures/models/cultist_robe_armor.png";
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  74 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/*  79 */     list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("tc.visdiscount") + ": " + getVisDiscount(stack, player, null) + "%");
/*  80 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  85 */     return par2ItemStack.func_77969_a(new ItemStack(Items.field_151042_j)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/*  94 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
/*     */   {
/* 100 */     return 1;
/*     */   }
/*     */   
/*     */ 
/* 104 */   ModelBiped model1 = null;
/* 105 */   ModelBiped model2 = null;
/* 106 */   ModelBiped model = null;
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/*     */   {
/* 113 */     int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
/*     */     
/* 115 */     if (this.model1 == null) {
/* 116 */       this.model1 = new ModelRobe(1.0F);
/*     */     }
/* 118 */     if (this.model2 == null) {
/* 119 */       this.model2 = new ModelRobe(0.5F);
/*     */     }
/* 121 */     if ((type == 1) || (type == 3)) {
/* 122 */       this.model = this.model1;
/*     */     } else {
/* 124 */       this.model = this.model2;
/*     */     }
/*     */     
/* 127 */     if (this.model != null) {
/* 128 */       this.model.field_78116_c.field_78806_j = (armorSlot == 0);
/* 129 */       this.model.field_78114_d.field_78806_j = (armorSlot == 0);
/* 130 */       this.model.field_78115_e.field_78806_j = ((armorSlot == 1) || (armorSlot == 2));
/* 131 */       this.model.field_78112_f.field_78806_j = (armorSlot == 1);
/* 132 */       this.model.field_78113_g.field_78806_j = (armorSlot == 1);
/* 133 */       this.model.field_78123_h.field_78806_j = (armorSlot == 2);
/* 134 */       this.model.field_78124_i.field_78806_j = (armorSlot == 2);
/* 135 */       this.model.field_78117_n = entityLiving.func_70093_af();
/*     */       
/* 137 */       this.model.field_78093_q = entityLiving.func_70115_ae();
/* 138 */       this.model.field_78091_s = entityLiving.func_70631_g_();
/* 139 */       this.model.field_78118_o = false;
/* 140 */       this.model.field_78120_m = (entityLiving.func_70694_bm() != null ? 1 : 0);
/* 141 */       if ((entityLiving instanceof EntityPlayer))
/*     */       {
/* 143 */         if (((EntityPlayer)entityLiving).func_71057_bx() > 0)
/*     */         {
/* 145 */           EnumAction enumaction = ((EntityPlayer)entityLiving).func_71011_bu().func_77975_n();
/*     */           
/* 147 */           if (enumaction == EnumAction.block)
/*     */           {
/* 149 */             this.model.field_78120_m = 3;
/*     */           }
/* 151 */           else if (enumaction == EnumAction.bow)
/*     */           {
/* 153 */             this.model.field_78118_o = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 160 */     return this.model;
/*     */   }
/*     */   
/*     */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*     */   {
/* 165 */     return 1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemCultistRobeArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */