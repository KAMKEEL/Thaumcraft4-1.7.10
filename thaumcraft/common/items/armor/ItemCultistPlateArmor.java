/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ 
/*     */ public class ItemCultistPlateArmor extends ItemArmor implements thaumcraft.api.IRepairable, thaumcraft.api.IRunicArmor
/*     */ {
/*     */   public IIcon iconHelm;
/*     */   public IIcon iconChest;
/*     */   public IIcon iconLegs;
/*     */   public IIcon iconChestOver;
/*     */   public IIcon iconLegsOver;
/*     */   public IIcon iconBlank;
/*     */   
/*     */   public ItemCultistPlateArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*     */   {
/*  26 */     super(enumarmormaterial, j, k);
/*  27 */     func_77637_a(thaumcraft.common.Thaumcraft.tabTC);
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
/*  40 */     this.iconHelm = ir.func_94245_a("thaumcraft:cultistplatehelm");
/*  41 */     this.iconChest = ir.func_94245_a("thaumcraft:cultistplatechest");
/*  42 */     this.iconLegs = ir.func_94245_a("thaumcraft:cultistplatelegs");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  48 */     return this.field_77881_a == 1 ? this.iconChest : this.field_77881_a == 0 ? this.iconHelm : this.iconLegs;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getArmorTexture(ItemStack stack, net.minecraft.entity.Entity entity, int slot, String type)
/*     */   {
/*  54 */     return (entity instanceof thaumcraft.common.entities.monster.EntityInhabitedZombie) ? "thaumcraft:textures/models/zombie_plate_armor.png" : "thaumcraft:textures/models/cultist_plate_armor.png";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  62 */     return net.minecraft.item.EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  68 */     return par2ItemStack.func_77969_a(new ItemStack(net.minecraft.init.Items.field_151042_j)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/*  77 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*  81 */   ModelBiped model1 = null;
/*  82 */   ModelBiped model2 = null;
/*  83 */   ModelBiped model = null;
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/*     */   {
/*  90 */     int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
/*     */     
/*  92 */     if (this.model1 == null) {
/*  93 */       this.model1 = new thaumcraft.client.renderers.models.gear.ModelKnightArmor(1.0F);
/*     */     }
/*  95 */     if (this.model2 == null) {
/*  96 */       this.model2 = new thaumcraft.client.renderers.models.gear.ModelKnightArmor(0.5F);
/*     */     }
/*  98 */     if ((type == 1) || (type == 3)) {
/*  99 */       this.model = this.model1;
/*     */     } else {
/* 101 */       this.model = this.model2;
/*     */     }
/*     */     
/* 104 */     if (this.model != null) {
/* 105 */       this.model.field_78116_c.field_78806_j = (armorSlot == 0);
/* 106 */       this.model.field_78114_d.field_78806_j = (armorSlot == 0);
/* 107 */       this.model.field_78115_e.field_78806_j = ((armorSlot == 1) || (armorSlot == 2));
/* 108 */       this.model.field_78112_f.field_78806_j = (armorSlot == 1);
/* 109 */       this.model.field_78113_g.field_78806_j = (armorSlot == 1);
/* 110 */       this.model.field_78123_h.field_78806_j = (armorSlot == 2);
/* 111 */       this.model.field_78124_i.field_78806_j = (armorSlot == 2);
/* 112 */       this.model.field_78117_n = entityLiving.func_70093_af();
/*     */       
/* 114 */       this.model.field_78093_q = entityLiving.func_70115_ae();
/* 115 */       this.model.field_78091_s = entityLiving.func_70631_g_();
/* 116 */       this.model.field_78118_o = false;
/* 117 */       this.model.field_78120_m = (entityLiving.func_70694_bm() != null ? 1 : 0);
/* 118 */       if ((entityLiving instanceof EntityPlayer))
/*     */       {
/* 120 */         if (((EntityPlayer)entityLiving).func_71057_bx() > 0)
/*     */         {
/* 122 */           EnumAction enumaction = ((EntityPlayer)entityLiving).func_71011_bu().func_77975_n();
/*     */           
/* 124 */           if (enumaction == EnumAction.block)
/*     */           {
/* 126 */             this.model.field_78120_m = 3;
/*     */           }
/* 128 */           else if (enumaction == EnumAction.bow)
/*     */           {
/* 130 */             this.model.field_78118_o = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 137 */     return this.model;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemCultistPlateArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */