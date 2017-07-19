/*     */ package thaumcraft.api.wands;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ 
/*     */ public class ItemFocusBasic extends Item
/*     */ {
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemFocusBasic()
/*     */   {
/*  29 */     this.field_77777_bU = 1;
/*  30 */     this.canRepair = false;
/*  31 */     func_77656_e(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  39 */     return this.icon;
/*     */   }
/*     */   
/*     */   public boolean func_77645_m()
/*     */   {
/*  44 */     return false;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/*  49 */     AspectList al = getVisCost(stack);
/*  50 */     if ((al != null) && (al.size() > 0)) {
/*  51 */       list.add(StatCollector.func_74838_a(isVisCostPerTick(stack) ? "item.Focus.cost2" : "item.Focus.cost1"));
/*  52 */       for (Aspect aspect : al.getAspectsSorted()) {
/*  53 */         DecimalFormat myFormatter = new DecimalFormat("#####.##");
/*  54 */         String amount = myFormatter.format(al.getAmount(aspect) / 100.0F);
/*  55 */         list.add(" §" + aspect.getChatcolor() + aspect.getName() + "§r x " + amount);
/*     */       }
/*     */     }
/*  58 */     addFocusInformation(stack, player, list, par4);
/*     */   }
/*     */   
/*     */   public void addFocusInformation(ItemStack focusstack, EntityPlayer player, List list, boolean par4) {
/*  62 */     LinkedHashMap<Short, Integer> map = new LinkedHashMap();
/*  63 */     for (short id : getAppliedUpgrades(focusstack)) {
/*  64 */       if (id >= 0) {
/*  65 */         int amt = 1;
/*  66 */         if (map.containsKey(Short.valueOf(id))) {
/*  67 */           amt = ((Integer)map.get(Short.valueOf(id))).intValue() + 1;
/*     */         }
/*  69 */         map.put(Short.valueOf(id), Integer.valueOf(amt));
/*     */       }
/*     */     }
/*  72 */     for (Short id : map.keySet()) {
/*  73 */       list.add(EnumChatFormatting.DARK_PURPLE + FocusUpgradeType.types[id.shortValue()].getLocalizedName() + (((Integer)map.get(id)).intValue() > 1 ? " " + StatCollector.func_74838_a(new StringBuilder().append("enchantment.level.").append(map.get(id)).toString()) : ""));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isVisCostPerTick(ItemStack focusstack)
/*     */   {
/*  82 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack focusstack)
/*     */   {
/*  88 */     return EnumRarity.rare;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getFocusColor(ItemStack focusstack)
/*     */   {
/*  95 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IIcon getOrnament(ItemStack focusstack)
/*     */   {
/* 104 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IIcon getFocusDepthLayerIcon(ItemStack focusstack)
/*     */   {
/* 112 */     return null;
/*     */   }
/*     */   
/*     */   public static enum WandFocusAnimation {
/* 116 */     WAVE,  CHARGE;
/*     */     
/*     */     private WandFocusAnimation() {} }
/*     */   
/* 120 */   public WandFocusAnimation getAnimation(ItemStack focusstack) { return WandFocusAnimation.WAVE; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSortingHelper(ItemStack focusstack)
/*     */   {
/* 127 */     String out = "";
/* 128 */     for (short id : getAppliedUpgrades(focusstack)) {
/* 129 */       out = out + id;
/*     */     }
/* 131 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList getVisCost(ItemStack focusstack)
/*     */   {
/* 139 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getActivationCooldown(ItemStack focusstack)
/*     */   {
/* 146 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getMaxAreaSize(ItemStack focusstack)
/*     */   {
/* 153 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack, int rank)
/*     */   {
/* 160 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public short[] getAppliedUpgrades(ItemStack focusstack)
/*     */   {
/* 167 */     short[] l = { -1, -1, -1, -1, -1 };
/* 168 */     NBTTagList nbttaglist = getFocusUpgradeTagList(focusstack);
/* 169 */     if (nbttaglist == null)
/*     */     {
/* 171 */       return l;
/*     */     }
/*     */     
/*     */ 
/* 175 */     for (int j = 0; j < nbttaglist.func_74745_c(); j++)
/*     */     {
/* 177 */       if (j >= 5) break;
/* 178 */       l[j] = nbttaglist.func_150305_b(j).func_74765_d("id");
/*     */     }
/*     */     
/* 181 */     return l;
/*     */   }
/*     */   
/*     */   public boolean applyUpgrade(ItemStack focusstack, FocusUpgradeType type, int rank)
/*     */   {
/* 186 */     short[] upgrades = getAppliedUpgrades(focusstack);
/* 187 */     if ((upgrades[(rank - 1)] != -1) || (rank < 1) || (rank > 5)) {
/* 188 */       return false;
/*     */     }
/* 190 */     upgrades[(rank - 1)] = type.id;
/* 191 */     setFocusUpgradeTagList(focusstack, upgrades);
/* 192 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
/*     */   {
/* 201 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isUpgradedWith(ItemStack focusstack, FocusUpgradeType focusUpgradetype)
/*     */   {
/* 208 */     return getUpgradeLevel(focusstack, focusUpgradetype) > 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getUpgradeLevel(ItemStack focusstack, FocusUpgradeType focusUpgradetype)
/*     */   {
/* 215 */     short[] list = getAppliedUpgrades(focusstack);
/* 216 */     int level = 0;
/* 217 */     for (short id : list) {
/* 218 */       if (id == focusUpgradetype.id)
/*     */       {
/* 220 */         level++;
/*     */       }
/*     */     }
/* 223 */     return level;
/*     */   }
/*     */   
/*     */   public ItemStack onFocusRightClick(ItemStack wandstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition)
/*     */   {
/* 228 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onUsingFocusTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void onPlayerStoppedUsingFocus(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */   public boolean onFocusBlockStartBreak(ItemStack wandstack, int x, int y, int z, EntityPlayer player)
/*     */   {
/* 242 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private NBTTagList getFocusUpgradeTagList(ItemStack focusstack)
/*     */   {
/* 250 */     return focusstack.field_77990_d == null ? null : focusstack.field_77990_d.func_150295_c("upgrade", 10);
/*     */   }
/*     */   
/*     */   private void setFocusUpgradeTagList(ItemStack focusstack, short[] upgrades) {
/* 254 */     if (!focusstack.func_77942_o())
/* 255 */       focusstack.func_77982_d(new NBTTagCompound());
/* 256 */     NBTTagCompound nbttagcompound = focusstack.func_77978_p();
/* 257 */     NBTTagList tlist = new NBTTagList();
/* 258 */     nbttagcompound.func_74782_a("upgrade", tlist);
/* 259 */     for (short id : upgrades) {
/* 260 */       NBTTagCompound f = new NBTTagCompound();
/* 261 */       f.func_74777_a("id", id);
/* 262 */       tlist.func_74742_a(f);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*     */   {
/* 268 */     if ((stack.field_77990_d != null) && (stack.field_77990_d.func_74764_b("ench"))) {
/* 269 */       stack.field_77990_d.func_82580_o("ench");
/*     */     }
/* 271 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/wands/ItemFocusBasic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */