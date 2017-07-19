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
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IRepairable;
/*     */ import thaumcraft.api.IVisDiscountGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.ItemJarFilled;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ 
/*     */ public class ItemHoverHarness extends ItemArmor implements IRepairable, IVisDiscountGear, thaumcraft.api.IRunicArmor
/*     */ {
/*     */   public ItemHoverHarness(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*     */   {
/*  35 */     super(enumarmormaterial, j, k);
/*  36 */     func_77656_e(400);
/*  37 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*  41 */   ModelBiped model = null;
/*     */   public IIcon icon;
/*     */   public IIcon iconLightningRing;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
/*  47 */     if (this.model == null) this.model = new thaumcraft.client.renderers.models.gear.ModelHoverHarness();
/*  48 */     return this.model;
/*     */   }
/*     */   
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/*  53 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  62 */     this.icon = ir.func_94245_a("thaumcraft:hoverharness");
/*  63 */     this.iconLightningRing = ir.func_94245_a("thaumcraft:lightningring");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  68 */     return this.icon;
/*     */   }
/*     */   
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  73 */     return "thaumcraft:textures/models/hoverharness.png";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  80 */     return EnumRarity.epic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  87 */     return par2ItemStack.func_77969_a(new ItemStack(Items.field_151043_k)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */   public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
/*     */   {
/*  92 */     return aspect == Aspect.AIR ? 5 : 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/* 100 */     if (!par2World.field_72995_K)
/*     */     {
/* 102 */       par3EntityPlayer.openGui(Thaumcraft.instance, 17, par2World, MathHelper.func_76128_c(par3EntityPlayer.field_70165_t), MathHelper.func_76128_c(par3EntityPlayer.field_70163_u), MathHelper.func_76128_c(par3EntityPlayer.field_70161_v));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 108 */     return par1ItemStack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
/*     */   {
/* 116 */     if (!player.field_71075_bZ.field_75098_d) {
/* 117 */       Hover.handleHoverArmor(player, player.field_71071_by.func_70440_f(2));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 124 */     super.func_77624_a(is, player, list, par4);
/*     */     
/* 126 */     if ((is.func_77942_o()) && (is.field_77990_d.func_74764_b("jar"))) {
/* 127 */       ItemStack jar = ItemStack.func_77949_a(is.field_77990_d.func_74775_l("jar"));
/*     */       try {
/* 129 */         AspectList aspects = ((ItemJarFilled)jar.func_77973_b()).getAspects(jar);
/* 130 */         if ((aspects != null) && (aspects.size() > 0)) {
/* 131 */           for (Aspect tag : aspects.getAspectsSorted()) {
/* 132 */             if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
/* 133 */               list.add(tag.getName() + " x " + aspects.getAmount(tag));
/*     */             } else {
/* 135 */               list.add(StatCollector.func_74838_a("tc.aspect.unknown"));
/*     */             }
/*     */           }
/*     */         }
/*     */       } catch (Exception e) {}
/*     */     }
/* 141 */     list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("tc.visdiscount") + ": " + getVisDiscount(is, player, null) + "%");
/* 142 */     list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("tc.visdiscount") + " (Aer): " + getVisDiscount(is, player, Aspect.AIR) + "%");
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemHoverHarness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */