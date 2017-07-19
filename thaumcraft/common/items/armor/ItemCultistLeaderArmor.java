/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ 
/*     */ public class ItemCultistLeaderArmor extends ItemArmor implements thaumcraft.api.IRepairable, thaumcraft.api.IRunicArmor
/*     */ {
/*     */   public IIcon iconHelm;
/*     */   public IIcon iconChest;
/*     */   public IIcon iconLegs;
/*     */   public IIcon iconChestOver;
/*     */   public IIcon iconLegsOver;
/*     */   public IIcon iconBlank;
/*     */   
/*     */   public ItemCultistLeaderArmor(net.minecraft.item.ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*     */   {
/*  24 */     super(enumarmormaterial, j, k);
/*  25 */     func_77637_a(thaumcraft.common.Thaumcraft.tabTC);
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
/*  38 */     this.iconHelm = ir.func_94245_a("thaumcraft:cultistplateleaderhelm");
/*  39 */     this.iconChest = ir.func_94245_a("thaumcraft:cultistplateleaderchest");
/*  40 */     this.iconLegs = ir.func_94245_a("thaumcraft:cultistplateleaderlegs");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  46 */     return this.field_77881_a == 1 ? this.iconChest : this.field_77881_a == 0 ? this.iconHelm : this.iconLegs;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getArmorTexture(ItemStack stack, net.minecraft.entity.Entity entity, int slot, String type)
/*     */   {
/*  52 */     return "thaumcraft:textures/models/cultist_leader_armor.png";
/*     */   }
/*     */   
/*     */ 
/*     */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  58 */     return net.minecraft.item.EnumRarity.rare;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  64 */     return par2ItemStack.func_77969_a(new ItemStack(net.minecraft.init.Items.field_151042_j)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/*  73 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*  77 */   ModelBiped model1 = null;
/*  78 */   ModelBiped model2 = null;
/*  79 */   ModelBiped model = null;
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
/*     */   {
/*  86 */     int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
/*     */     
/*  88 */     if (this.model1 == null) {
/*  89 */       this.model1 = new thaumcraft.client.renderers.models.gear.ModelLeaderArmor(1.0F);
/*     */     }
/*  91 */     if (this.model2 == null) {
/*  92 */       this.model2 = new thaumcraft.client.renderers.models.gear.ModelLeaderArmor(0.5F);
/*     */     }
/*  94 */     if ((type == 1) || (type == 3)) {
/*  95 */       this.model = this.model1;
/*     */     } else {
/*  97 */       this.model = this.model2;
/*     */     }
/*     */     
/* 100 */     if (this.model != null) {
/* 101 */       this.model.field_78116_c.field_78806_j = (armorSlot == 0);
/* 102 */       this.model.field_78114_d.field_78806_j = (armorSlot == 0);
/* 103 */       this.model.field_78115_e.field_78806_j = ((armorSlot == 1) || (armorSlot == 2));
/* 104 */       this.model.field_78112_f.field_78806_j = (armorSlot == 1);
/* 105 */       this.model.field_78113_g.field_78806_j = (armorSlot == 1);
/* 106 */       this.model.field_78123_h.field_78806_j = (armorSlot == 2);
/* 107 */       this.model.field_78124_i.field_78806_j = (armorSlot == 2);
/* 108 */       this.model.field_78117_n = entityLiving.func_70093_af();
/*     */       
/* 110 */       this.model.field_78093_q = entityLiving.func_70115_ae();
/* 111 */       this.model.field_78091_s = entityLiving.func_70631_g_();
/* 112 */       this.model.field_78118_o = false;
/* 113 */       this.model.field_78120_m = (entityLiving.func_70694_bm() != null ? 1 : 0);
/* 114 */       if ((entityLiving instanceof EntityPlayer))
/*     */       {
/* 116 */         if (((EntityPlayer)entityLiving).func_71057_bx() > 0)
/*     */         {
/* 118 */           net.minecraft.item.EnumAction enumaction = ((EntityPlayer)entityLiving).func_71011_bu().func_77975_n();
/*     */           
/* 120 */           if (enumaction == net.minecraft.item.EnumAction.block)
/*     */           {
/* 122 */             this.model.field_78120_m = 3;
/*     */           }
/* 124 */           else if (enumaction == net.minecraft.item.EnumAction.bow)
/*     */           {
/* 126 */             this.model.field_78118_o = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 133 */     return this.model;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemCultistLeaderArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */