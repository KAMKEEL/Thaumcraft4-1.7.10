/*     */ package thaumcraft.common.items.baubles;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.api.IBauble;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import thaumcraft.api.IRunicArmor;
/*     */ import thaumcraft.api.IVisDiscountGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemBaubleBlanks
/*     */   extends Item implements IBauble, IVisDiscountGear, IRunicArmor
/*     */ {
/*     */   public ItemBaubleBlanks()
/*     */   {
/*  28 */     this.field_77777_bU = 1;
/*  29 */     this.canRepair = false;
/*  30 */     func_77656_e(0);
/*  31 */     func_77637_a(Thaumcraft.tabTC);
/*  32 */     func_77627_a(true);
/*     */   }
/*     */   
/*  35 */   public IIcon[] icon = new IIcon[4];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir) {
/*  39 */     this.icon[0] = ir.func_94245_a("thaumcraft:bauble_amulet");
/*  40 */     this.icon[1] = ir.func_94245_a("thaumcraft:bauble_ring");
/*  41 */     this.icon[2] = ir.func_94245_a("thaumcraft:bauble_belt");
/*  42 */     this.icon[3] = ir.func_94245_a("thaumcraft:bauble_ring_iron");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  48 */     return par1 <= 2 ? this.icon[par1] : this.icon[3];
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack par1ItemStack)
/*     */   {
/*  54 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  60 */     par3List.add(new ItemStack(this, 1, 0));
/*  61 */     par3List.add(new ItemStack(this, 1, 1));
/*  62 */     par3List.add(new ItemStack(this, 1, 2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaubleType getBaubleType(ItemStack itemstack)
/*     */   {
/*  74 */     switch (itemstack.func_77960_j()) {
/*  75 */     case 1: case 3: case 4: case 5: case 6: case 7: case 8:  return BaubleType.RING;
/*  76 */     case 2:  return BaubleType.BELT;
/*     */     }
/*  78 */     return BaubleType.AMULET;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}
/*     */   
/*     */ 
/*     */   public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}
/*     */   
/*     */ 
/*     */   public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}
/*     */   
/*     */   public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
/*     */   {
/*  92 */     if ((stack.func_77960_j() >= 3) && (stack.func_77960_j() <= 8) && (Aspect.getPrimalAspects().get(stack.func_77960_j() - 3) == aspect))
/*     */     {
/*  94 */       return 1;
/*     */     }
/*  96 */     return 0;
/*     */   }
/*     */   
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/* 101 */     if ((stack.func_77960_j() >= 3) && (stack.func_77960_j() <= 8)) {
/* 102 */       return ((Aspect)Aspect.getPrimalAspects().get(stack.func_77960_j() - 3)).getColor();
/*     */     }
/* 104 */     return super.func_82790_a(stack, par2);
/*     */   }
/*     */   
/*     */   public String func_77653_i(ItemStack stack)
/*     */   {
/* 109 */     if ((stack.func_77960_j() >= 3) && (stack.func_77960_j() <= 8)) {
/* 110 */       Aspect aspect = (Aspect)Aspect.getPrimalAspects().get(stack.func_77960_j() - 3);
/* 111 */       return StatCollector.func_74838_a("item.ItemBaubleBlanks.3.name").replace("%TYPE", aspect.getName());
/*     */     }
/*     */     
/* 114 */     return super.func_77653_i(stack);
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 119 */     if ((stack.func_77960_j() >= 3) && (stack.func_77960_j() <= 8)) {
/* 120 */       Aspect aspect = (Aspect)Aspect.getPrimalAspects().get(stack.func_77960_j() - 3);
/* 121 */       list.add(EnumChatFormatting.DARK_PURPLE + aspect.getName() + " " + StatCollector.func_74838_a("tc.discount") + ": 1%");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 128 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 133 */     return true;
/*     */   }
/*     */   
/*     */   public int getRunicCharge(ItemStack itemstack)
/*     */   {
/* 138 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/baubles/ItemBaubleBlanks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */