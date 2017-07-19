/*     */ package thaumcraft.common.items.wands;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.IArchitect;
/*     */ import thaumcraft.api.IArchitect.EnumAxis;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.ItemFocusBasic.WandFocusAnimation;
/*     */ import thaumcraft.api.wands.StaffRod;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.api.wands.WandTriggerRegistry;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.tiles.TileOwned;
/*     */ 
/*     */ public class ItemWandCasting extends Item implements IArchitect
/*     */ {
/*     */   private IIcon icon;
/*     */   
/*     */   public ItemWandCasting()
/*     */   {
/*  58 */     this.field_77777_bU = 1;
/*  59 */     func_77656_e(0);
/*  60 */     func_77627_a(true);
/*  61 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public boolean func_77645_m()
/*     */   {
/*  66 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister)
/*     */   {
/*  75 */     this.icon = par1IconRegister.func_94245_a("thaumcraft:blank");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getIcon(ItemStack stack, int pass)
/*     */   {
/*  81 */     return this.icon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77662_d()
/*     */   {
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public int getMaxVis(ItemStack stack) {
/*  91 */     return getRod(stack).getCapacity() * (isSceptre(stack) ? 150 : 100);
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  97 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 103 */     ItemStack w1 = new ItemStack(this, 1, 0);
/* 104 */     ItemStack w2 = new ItemStack(this, 1, 9);
/* 105 */     ItemStack w3 = new ItemStack(this, 1, 54);
/* 106 */     ((ItemWandCasting)w2.func_77973_b()).setCap(w2, ConfigItems.WAND_CAP_GOLD);
/* 107 */     ((ItemWandCasting)w3.func_77973_b()).setCap(w3, ConfigItems.WAND_CAP_THAUMIUM);
/* 108 */     ((ItemWandCasting)w2.func_77973_b()).setRod(w2, ConfigItems.WAND_ROD_GREATWOOD);
/* 109 */     ((ItemWandCasting)w3.func_77973_b()).setRod(w3, ConfigItems.WAND_ROD_SILVERWOOD);
/*     */     
/* 111 */     ItemStack sceptre = new ItemStack(ConfigItems.itemWandCasting, 1, 128);
/* 112 */     ((ItemWandCasting)sceptre.func_77973_b()).setCap(sceptre, ConfigItems.WAND_CAP_THAUMIUM);
/* 113 */     ((ItemWandCasting)sceptre.func_77973_b()).setRod(sceptre, ConfigItems.WAND_ROD_SILVERWOOD);
/* 114 */     sceptre.func_77983_a("sceptre", new net.minecraft.nbt.NBTTagByte((byte)1));
/*     */     
/* 116 */     for (Aspect aspect : Aspect.getPrimalAspects()) {
/* 117 */       ((ItemWandCasting)w1.func_77973_b()).addVis(w1, aspect, ((ItemWandCasting)w1.func_77973_b()).getMaxVis(w1), true);
/* 118 */       ((ItemWandCasting)w2.func_77973_b()).addVis(w2, aspect, ((ItemWandCasting)w2.func_77973_b()).getMaxVis(w2), true);
/* 119 */       ((ItemWandCasting)w3.func_77973_b()).addVis(w3, aspect, ((ItemWandCasting)w3.func_77973_b()).getMaxVis(w3), true);
/* 120 */       ((ItemWandCasting)sceptre.func_77973_b()).addVis(sceptre, aspect, ((ItemWandCasting)sceptre.func_77973_b()).getMaxVis(sceptre), true);
/*     */     }
/* 122 */     par3List.add(w1);
/* 123 */     par3List.add(w2);
/* 124 */     par3List.add(w3);
/*     */     
/*     */ 
/* 127 */     par3List.add(sceptre);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String func_77653_i(ItemStack is)
/*     */   {
/* 134 */     String name = StatCollector.func_74838_a("item.Wand.name");
/* 135 */     name = name.replace("%CAP", StatCollector.func_74838_a("item.Wand." + getCap(is).getTag() + ".cap"));
/* 136 */     String rod = getRod(is).getTag();
/* 137 */     if (rod.indexOf("_staff") >= 0) rod = rod.substring(0, getRod(is).getTag().indexOf("_staff"));
/* 138 */     name = name.replace("%ROD", StatCollector.func_74838_a("item.Wand." + rod + ".rod"));
/* 139 */     name = name.replace("%OBJ", isSceptre(is) ? StatCollector.func_74838_a("item.Wand.sceptre.obj") : isStaff(is) ? StatCollector.func_74838_a("item.Wand.staff.obj") : StatCollector.func_74838_a("item.Wand.wand.obj"));
/*     */     
/*     */ 
/*     */ 
/* 143 */     return name;
/*     */   }
/*     */   
/* 146 */   DecimalFormat myFormatter = new DecimalFormat("#######.##");
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 150 */     int pos = list.size();
/* 151 */     String tt2 = "";
/* 152 */     if (stack.func_77942_o()) {
/* 153 */       String tt = "";
/* 154 */       int tot = 0;
/* 155 */       int num = 0;
/* 156 */       for (Aspect aspect : Aspect.getPrimalAspects()) {
/* 157 */         if (stack.field_77990_d.func_74764_b(aspect.getTag()))
/*     */         {
/* 159 */           String amount = this.myFormatter.format(stack.field_77990_d.func_74762_e(aspect.getTag()) / 100.0F);
/* 160 */           float mod = getConsumptionModifier(stack, player, aspect, false);
/* 161 */           String consumption = this.myFormatter.format(mod * 100.0F);
/* 162 */           num++;
/* 163 */           tot = (int)(tot + mod * 100.0F);
/* 164 */           String text = "";
/* 165 */           ItemStack focus = getFocusItem(stack);
/* 166 */           if (focus != null) {
/* 167 */             int amt = ((ItemFocusBasic)focus.func_77973_b()).getVisCost(focus).getAmount(aspect);
/* 168 */             if (amt > 0) {
/* 169 */               text = "§r, " + this.myFormatter.format(amt * mod / 100.0F) + " " + StatCollector.func_74838_a(((ItemFocusBasic)focus.func_77973_b()).isVisCostPerTick(focus) ? "item.Focus.cost2" : "item.Focus.cost1");
/*     */             }
/*     */           }
/*     */           
/* 173 */           if (Thaumcraft.proxy.isShiftKeyDown()) {
/* 174 */             list.add(" §" + aspect.getChatcolor() + aspect.getName() + "§r x " + amount + ", §o(" + consumption + "% " + StatCollector.func_74838_a("tc.vis.cost") + ")" + text);
/*     */           }
/*     */           else {
/* 177 */             if (tt.length() > 0) tt = tt + " | ";
/* 178 */             tt = tt + "§" + aspect.getChatcolor() + amount + "§r";
/*     */           }
/*     */         }
/*     */       }
/* 182 */       if ((!Thaumcraft.proxy.isShiftKeyDown()) && (num > 0)) {
/* 183 */         list.add(tt);
/* 184 */         tot /= num;
/* 185 */         tt2 = " (" + tot + "% " + StatCollector.func_74838_a("tc.vis.costavg") + ")";
/*     */       }
/*     */     }
/*     */     
/* 189 */     list.add(pos, EnumChatFormatting.GOLD + StatCollector.func_74838_a("item.capacity.text") + " " + getMaxVis(stack) / 100 + "§r" + tt2);
/*     */     
/* 191 */     if (getFocus(stack) != null) {
/* 192 */       list.add(EnumChatFormatting.BOLD + "" + EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GREEN + getFocus(stack).func_77653_i(getFocusItem(stack)));
/*     */       
/* 194 */       if (Thaumcraft.proxy.isShiftKeyDown())
/* 195 */         getFocus(stack).addFocusInformation(getFocusItem(stack), player, list, par4);
/*     */     }
/*     */   }
/*     */   
/*     */   public AspectList getAllVis(ItemStack is) {
/* 200 */     AspectList out = new AspectList();
/* 201 */     for (Aspect aspect : Aspect.getPrimalAspects()) {
/* 202 */       if ((is.func_77942_o()) && (is.field_77990_d.func_74764_b(aspect.getTag()))) {
/* 203 */         out.merge(aspect, is.field_77990_d.func_74762_e(aspect.getTag()));
/*     */       } else {
/* 205 */         out.merge(aspect, 0);
/*     */       }
/*     */     }
/* 208 */     return out;
/*     */   }
/*     */   
/*     */   public AspectList getAspectsWithRoom(ItemStack wandstack) {
/* 212 */     AspectList out = new AspectList();
/* 213 */     AspectList cur = getAllVis(wandstack);
/* 214 */     for (Aspect aspect : cur.getAspects()) {
/* 215 */       if (cur.getAmount(aspect) < getMaxVis(wandstack)) {
/* 216 */         out.add(aspect, 1);
/*     */       }
/*     */     }
/* 219 */     return out;
/*     */   }
/*     */   
/*     */   public void storeAllVis(ItemStack is, AspectList in) {
/* 223 */     for (Aspect aspect : in.getAspects()) {
/* 224 */       is.func_77983_a(aspect.getTag(), new NBTTagInt(in.getAmount(aspect)));
/*     */     }
/*     */   }
/*     */   
/*     */   public int getVis(ItemStack is, Aspect aspect) {
/* 229 */     int out = 0;
/* 230 */     if ((is != null) && (aspect != null) && (is.func_77942_o()) && (is.field_77990_d.func_74764_b(aspect.getTag()))) {
/* 231 */       out = is.field_77990_d.func_74762_e(aspect.getTag());
/*     */     }
/* 233 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */   public void storeVis(ItemStack is, Aspect aspect, int amount)
/*     */   {
/* 239 */     is.func_77983_a(aspect.getTag(), new NBTTagInt(amount));
/*     */   }
/*     */   
/*     */   public float getConsumptionModifier(ItemStack is, EntityPlayer player, Aspect aspect, boolean crafting) {
/* 243 */     float consumptionModifier = 1.0F;
/* 244 */     if ((getCap(is).getSpecialCostModifierAspects() != null) && (getCap(is).getSpecialCostModifierAspects().contains(aspect)))
/*     */     {
/* 246 */       consumptionModifier = getCap(is).getSpecialCostModifier();
/*     */     } else {
/* 248 */       consumptionModifier = getCap(is).getBaseCostModifier();
/*     */     }
/*     */     
/* 251 */     if (player != null) { consumptionModifier -= WandManager.getTotalVisDiscount(player, aspect);
/*     */     }
/* 253 */     if ((getFocus(is) != null) && (!crafting)) {
/* 254 */       consumptionModifier -= getFocusFrugal(is) / 10.0F;
/*     */     }
/*     */     
/* 257 */     if (isSceptre(is)) {
/* 258 */       consumptionModifier -= 0.1F;
/*     */     }
/*     */     
/* 261 */     return Math.max(consumptionModifier, 0.1F);
/*     */   }
/*     */   
/*     */   public int getFocusPotency(ItemStack itemstack) {
/* 265 */     if (getFocus(itemstack) == null) return 0;
/* 266 */     return getFocus(itemstack).getUpgradeLevel(getFocusItem(itemstack), FocusUpgradeType.potency) + (hasRunes(itemstack) ? 1 : 0);
/*     */   }
/*     */   
/*     */   public int getFocusTreasure(ItemStack itemstack) {
/* 270 */     if (getFocus(itemstack) == null) return 0;
/* 271 */     return getFocus(itemstack).getUpgradeLevel(getFocusItem(itemstack), FocusUpgradeType.treasure);
/*     */   }
/*     */   
/*     */   public int getFocusFrugal(ItemStack itemstack) {
/* 275 */     if (getFocus(itemstack) == null) return 0;
/* 276 */     return getFocus(itemstack).getUpgradeLevel(getFocusItem(itemstack), FocusUpgradeType.frugal);
/*     */   }
/*     */   
/*     */   public int getFocusEnlarge(ItemStack itemstack) {
/* 280 */     if (getFocus(itemstack) == null) return 0;
/* 281 */     return getFocus(itemstack).getUpgradeLevel(getFocusItem(itemstack), FocusUpgradeType.enlarge);
/*     */   }
/*     */   
/*     */   public int getFocusExtend(ItemStack itemstack) {
/* 285 */     if (getFocus(itemstack) == null) return 0;
/* 286 */     return getFocus(itemstack).getUpgradeLevel(getFocusItem(itemstack), FocusUpgradeType.extend);
/*     */   }
/*     */   
/*     */   public boolean consumeVis(ItemStack is, EntityPlayer player, Aspect aspect, int amount, boolean crafting) {
/* 290 */     amount = (int)(amount * getConsumptionModifier(is, player, aspect, crafting));
/* 291 */     if (getVis(is, aspect) >= amount) {
/* 292 */       storeVis(is, aspect, getVis(is, aspect) - amount);
/* 293 */       return true;
/*     */     }
/* 295 */     return false;
/*     */   }
/*     */   
/*     */   public boolean consumeAllVisCrafting(ItemStack is, EntityPlayer player, AspectList aspects, boolean doit) {
/* 299 */     if ((aspects == null) || (aspects.size() == 0)) return false;
/* 300 */     AspectList nl = new AspectList();
/* 301 */     for (Aspect aspect : aspects.getAspects()) {
/* 302 */       int cost = aspects.getAmount(aspect) * 100;
/* 303 */       nl.add(aspect, cost);
/*     */     }
/* 305 */     return consumeAllVis(is, player, nl, doit, true);
/*     */   }
/*     */   
/*     */   public boolean consumeAllVis(ItemStack is, EntityPlayer player, AspectList aspects, boolean doit, boolean crafting) {
/* 309 */     if ((aspects == null) || (aspects.size() == 0)) { return false;
/*     */     }
/* 311 */     AspectList nl = new AspectList();
/* 312 */     for (Aspect aspect : aspects.getAspects()) {
/* 313 */       int cost = aspects.getAmount(aspect);
/* 314 */       cost = (int)(cost * getConsumptionModifier(is, player, aspect, crafting));
/* 315 */       nl.add(aspect, cost);
/*     */     }
/*     */     
/* 318 */     for (Aspect aspect : nl.getAspects()) {
/* 319 */       if (getVis(is, aspect) < nl.getAmount(aspect)) { return false;
/*     */       }
/*     */     }
/* 322 */     if ((doit) && (!player.field_70170_p.field_72995_K)) {
/* 323 */       for (Aspect aspect : nl.getAspects())
/* 324 */         storeVis(is, aspect, getVis(is, aspect) - nl.getAmount(aspect));
/*     */     }
/* 326 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int addVis(ItemStack is, Aspect aspect, int amount, boolean doit)
/*     */   {
/* 332 */     if (!aspect.isPrimal()) return 0;
/* 333 */     int storeAmount = getVis(is, aspect) + amount * 100;
/* 334 */     int leftover = Math.max(storeAmount - getMaxVis(is), 0);
/* 335 */     if (doit) storeVis(is, aspect, Math.min(storeAmount, getMaxVis(is)));
/* 336 */     return leftover / 100;
/*     */   }
/*     */   
/*     */   public int addRealVis(ItemStack is, Aspect aspect, int amount, boolean doit) {
/* 340 */     if (!aspect.isPrimal()) return 0;
/* 341 */     int storeAmount = getVis(is, aspect) + amount;
/* 342 */     int leftover = Math.max(storeAmount - getMaxVis(is), 0);
/* 343 */     if (doit) storeVis(is, aspect, Math.min(storeAmount, getMaxVis(is)));
/* 344 */     return leftover;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77663_a(ItemStack is, World w, Entity e, int slot, boolean currentItem)
/*     */   {
/* 350 */     if (!w.field_72995_K)
/*     */     {
/* 352 */       EntityPlayer player = (EntityPlayer)e;
/*     */       
/* 354 */       if (getRod(is).getOnUpdate() != null) {
/* 355 */         getRod(is).getOnUpdate().onUpdate(is, player);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*     */   {
/* 363 */     Block bi = world.func_147439_a(x, y, z);
/* 364 */     int md = world.func_72805_g(x, y, z);
/*     */     
/* 366 */     boolean result = false;
/*     */     
/* 368 */     ForgeDirection direction = ForgeDirection.getOrientation(side);
/*     */     
/* 370 */     if ((bi instanceof IWandable)) {
/* 371 */       int ret = ((IWandable)bi).onWandRightClick(world, itemstack, player, x, y, z, side, md);
/* 372 */       if (ret >= 0) { return ret == 1;
/*     */       }
/*     */     }
/* 375 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 376 */     if ((tile != null) && ((tile instanceof IWandable))) {
/* 377 */       int ret = ((IWandable)tile).onWandRightClick(world, itemstack, player, x, y, z, side, md);
/* 378 */       if (ret >= 0) { return ret == 1;
/*     */       }
/*     */     }
/* 381 */     if (WandTriggerRegistry.hasTrigger(bi, md)) {
/* 382 */       return WandTriggerRegistry.performTrigger(world, itemstack, player, x, y, z, side, bi, md);
/*     */     }
/*     */     
/*     */ 
/* 386 */     if (((bi == ConfigBlocks.blockWoodenDevice) && (md == 2)) || ((bi == ConfigBlocks.blockCosmeticOpaque) && (md == 2)))
/*     */     {
/* 388 */       if ((!Config.wardedStone) || ((tile != null) && ((tile instanceof TileOwned)) && (player.func_70005_c_().equals(((TileOwned)tile).owner)))) {
/* 389 */         if (!world.field_72995_K) {
/* 390 */           ((TileOwned)tile).safeToRemove = true;
/* 391 */           world.func_72838_d(new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, new ItemStack(bi, 1, md)));
/* 392 */           world.func_72926_e(2001, x, y, z, Block.func_149682_b(bi) + (md << 12));
/* 393 */           world.func_147468_f(x, y, z);
/*     */         } else {
/* 395 */           player.func_71038_i();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 401 */     if ((bi == ConfigBlocks.blockArcaneDoor) && (
/* 402 */       (!Config.wardedStone) || ((tile != null) && ((tile instanceof TileOwned)) && (player.func_70005_c_().equals(((TileOwned)tile).owner))))) {
/* 403 */       if (!world.field_72995_K) {
/* 404 */         ((TileOwned)tile).safeToRemove = true;
/* 405 */         if ((md & 0x8) == 0) {
/* 406 */           tile = world.func_147438_o(x, y + 1, z);
/*     */         } else {
/* 408 */           tile = world.func_147438_o(x, y - 1, z);
/*     */         }
/* 410 */         if ((tile != null) && ((tile instanceof TileOwned))) {
/* 411 */           ((TileOwned)tile).safeToRemove = true;
/*     */         }
/* 413 */         if ((Config.wardedStone) || ((!Config.wardedStone) && ((md & 0x8) == 0)))
/* 414 */           world.func_72838_d(new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, new ItemStack(ConfigItems.itemArcaneDoor)));
/* 415 */         world.func_72926_e(2001, x, y, z, Block.func_149682_b(bi) + (md << 12));
/* 416 */         world.func_147468_f(x, y, z);
/*     */       } else {
/* 418 */         player.func_71038_i();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 424 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemFocusBasic getFocus(ItemStack stack)
/*     */   {
/* 431 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("focus"))) {
/* 432 */       NBTTagCompound nbt = stack.field_77990_d.func_74775_l("focus");
/* 433 */       return (ItemFocusBasic)ItemStack.func_77949_a(nbt).func_77973_b();
/*     */     }
/* 435 */     return null;
/*     */   }
/*     */   
/*     */   public ItemStack getFocusItem(ItemStack stack) {
/* 439 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("focus"))) {
/* 440 */       NBTTagCompound nbt = stack.field_77990_d.func_74775_l("focus");
/* 441 */       return ItemStack.func_77949_a(nbt);
/*     */     }
/* 443 */     return null;
/*     */   }
/*     */   
/*     */   public void setFocus(ItemStack stack, ItemStack focus) {
/* 447 */     if (focus == null) {
/* 448 */       stack.field_77990_d.func_82580_o("focus");
/*     */     } else {
/* 450 */       stack.func_77983_a("focus", focus.func_77955_b(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */   
/*     */   public WandRod getRod(ItemStack stack) {
/* 455 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("rod"))) {
/* 456 */       return (WandRod)WandRod.rods.get(stack.field_77990_d.func_74779_i("rod"));
/*     */     }
/* 458 */     return ConfigItems.WAND_ROD_WOOD;
/*     */   }
/*     */   
/*     */   public boolean isStaff(ItemStack stack) {
/* 462 */     WandRod rod = getRod(stack);
/* 463 */     if ((rod != null) && ((rod instanceof StaffRod))) {
/* 464 */       return true;
/*     */     }
/* 466 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSceptre(ItemStack stack) {
/* 470 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("sceptre"))) {
/* 471 */       return true;
/*     */     }
/* 473 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasRunes(ItemStack stack) {
/* 477 */     WandRod rod = getRod(stack);
/* 478 */     if ((rod != null) && ((rod instanceof StaffRod)) && (((StaffRod)rod).hasRunes())) {
/* 479 */       return true;
/*     */     }
/* 481 */     return false;
/*     */   }
/*     */   
/*     */   public void setRod(ItemStack stack, WandRod rod) {
/* 485 */     stack.func_77983_a("rod", new NBTTagString(rod.getTag()));
/*     */     
/* 487 */     if ((rod instanceof StaffRod))
/*     */     {
/* 489 */       NBTTagList tags = new NBTTagList();
/* 490 */       NBTTagCompound tag = new NBTTagCompound();
/* 491 */       tag.func_74778_a("AttributeName", net.minecraft.entity.SharedMonsterAttributes.field_111264_e.func_111108_a());
/*     */       
/* 493 */       AttributeModifier am = new AttributeModifier(field_111210_e, "Weapon modifier", 6.0D, 0);
/*     */       
/* 495 */       tag.func_74778_a("Name", am.func_111166_b());
/* 496 */       tag.func_74780_a("Amount", am.func_111164_d());
/* 497 */       tag.func_74768_a("Operation", am.func_111169_c());
/* 498 */       tag.func_74772_a("UUIDMost", am.func_111167_a().getMostSignificantBits());
/* 499 */       tag.func_74772_a("UUIDLeast", am.func_111167_a().getLeastSignificantBits());
/*     */       
/* 501 */       tags.func_74742_a(tag);
/* 502 */       stack.field_77990_d.func_74782_a("AttributeModifiers", tags);
/*     */     }
/*     */   }
/*     */   
/*     */   public WandCap getCap(ItemStack stack)
/*     */   {
/* 508 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("cap"))) {
/* 509 */       return (WandCap)WandCap.caps.get(stack.field_77990_d.func_74779_i("cap"));
/*     */     }
/* 511 */     return ConfigItems.WAND_CAP_IRON;
/*     */   }
/*     */   
/*     */   public void setCap(ItemStack stack, WandCap cap) {
/* 515 */     stack.func_77983_a("cap", new NBTTagString(cap.getTag()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
/*     */   {
/* 524 */     MovingObjectPosition movingobjectposition = func_77621_a(world, player, true);
/*     */     
/* 526 */     if (movingobjectposition != null)
/*     */     {
/* 528 */       if (movingobjectposition.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK)
/*     */       {
/* 530 */         int i = movingobjectposition.field_72311_b;
/* 531 */         int j = movingobjectposition.field_72312_c;
/* 532 */         int k = movingobjectposition.field_72309_d;
/* 533 */         Block bi = world.func_147439_a(i, j, k);
/* 534 */         int md = world.func_72805_g(i, j, k);
/*     */         
/* 536 */         if ((bi instanceof IWandable)) {
/* 537 */           ItemStack is = ((IWandable)bi).onWandRightClick(world, itemstack, player);
/* 538 */           if (is != null) { return is;
/*     */           }
/*     */         }
/* 541 */         TileEntity tile = world.func_147438_o(i, j, k);
/* 542 */         if ((tile != null) && ((tile instanceof IWandable))) {
/* 543 */           ItemStack is = ((IWandable)tile).onWandRightClick(world, itemstack, player);
/* 544 */           if (is != null) {
/* 545 */             return is;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 554 */     ItemFocusBasic focus = getFocus(itemstack);
/* 555 */     if ((focus != null) && (!WandManager.isOnCooldown(player))) {
/* 556 */       WandManager.setCooldown(player, focus.getActivationCooldown(getFocusItem(itemstack)));
/* 557 */       ItemStack ret = focus.onFocusRightClick(itemstack, world, player, movingobjectposition);
/* 558 */       if (ret != null) { return ret;
/*     */       }
/*     */     }
/*     */     
/* 562 */     return super.func_77659_a(itemstack, world, player);
/*     */   }
/*     */   
/*     */   public void setObjectInUse(ItemStack stack, int x, int y, int z) {
/* 566 */     if (stack.field_77990_d == null) stack.field_77990_d = new NBTTagCompound();
/* 567 */     stack.field_77990_d.func_74768_a("IIUX", x);
/* 568 */     stack.field_77990_d.func_74768_a("IIUY", y);
/* 569 */     stack.field_77990_d.func_74768_a("IIUZ", z);
/*     */   }
/*     */   
/*     */   public void clearObjectInUse(ItemStack stack) {
/* 573 */     if (stack.field_77990_d == null) stack.field_77990_d = new NBTTagCompound();
/* 574 */     stack.field_77990_d.func_82580_o("IIUX");
/* 575 */     stack.field_77990_d.func_82580_o("IIUY");
/* 576 */     stack.field_77990_d.func_82580_o("IIUZ");
/*     */   }
/*     */   
/*     */   public IWandable getObjectInUse(ItemStack stack, World world) {
/* 580 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("IIUX"))) {
/* 581 */       TileEntity te = world.func_147438_o(stack.field_77990_d.func_74762_e("IIUX"), stack.field_77990_d.func_74762_e("IIUY"), stack.field_77990_d.func_74762_e("IIUZ"));
/*     */       
/*     */ 
/*     */ 
/* 585 */       if ((te != null) && ((te instanceof IWandable))) {
/* 586 */         return (IWandable)te;
/*     */       }
/*     */     }
/* 589 */     return null;
/*     */   }
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*     */   {
/* 594 */     IWandable tv = getObjectInUse(stack, player.field_70170_p);
/* 595 */     if (tv != null) {
/* 596 */       this.animation = ItemFocusBasic.WandFocusAnimation.WAVE;
/* 597 */       tv.onUsingWandTick(stack, player, count);
/*     */     }
/*     */     else {
/* 600 */       ItemFocusBasic focus = getFocus(stack);
/* 601 */       if ((focus != null) && (!WandManager.isOnCooldown(player))) {
/* 602 */         WandManager.setCooldown(player, focus.getActivationCooldown(getFocusItem(stack)));
/* 603 */         focus.onUsingFocusTick(stack, player, count);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 608 */   public ItemFocusBasic.WandFocusAnimation animation = null;
/*     */   
/*     */   public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int count)
/*     */   {
/* 612 */     IWandable tv = getObjectInUse(stack, player.field_70170_p);
/* 613 */     if (tv != null) {
/* 614 */       tv.onWandStoppedUsing(stack, world, player, count);
/* 615 */       this.animation = null;
/*     */     }
/*     */     else {
/* 618 */       ItemFocusBasic focus = getFocus(stack);
/* 619 */       if (focus != null) {
/* 620 */         focus.onPlayerStoppedUsingFocus(stack, world, player, count);
/*     */       }
/*     */     }
/*     */     
/* 624 */     clearObjectInUse(stack);
/*     */   }
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack par1ItemStack)
/*     */   {
/* 629 */     return EnumAction.bow;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_77626_a(ItemStack itemstack)
/*     */   {
/* 635 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
/*     */   {
/* 640 */     ItemStack focus = getFocusItem(stack);
/* 641 */     if ((focus != null) && (!WandManager.isOnCooldown(entityLiving))) {
/* 642 */       WandManager.setCooldown(entityLiving, getFocus(stack).getActivationCooldown(focus));
/* 643 */       return focus.func_77973_b().onEntitySwing(entityLiving, stack);
/*     */     }
/* 645 */     return super.onEntitySwing(entityLiving, stack);
/*     */   }
/*     */   
/*     */   public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player)
/*     */   {
/* 650 */     ItemFocusBasic focus = getFocus(itemstack);
/* 651 */     if ((focus != null) && (!WandManager.isOnCooldown(player))) {
/* 652 */       WandManager.setCooldown(player, focus.getActivationCooldown(getFocusItem(itemstack)));
/* 653 */       return focus.onFocusBlockStartBreak(itemstack, x, y, z, player);
/*     */     }
/*     */     
/* 656 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canHarvestBlock(Block par1Block, ItemStack itemstack)
/*     */   {
/* 662 */     ItemFocusBasic focus = getFocus(itemstack);
/* 663 */     if (focus != null) {
/* 664 */       return getFocusItem(itemstack).func_77973_b().canHarvestBlock(par1Block, itemstack);
/*     */     }
/* 666 */     return false;
/*     */   }
/*     */   
/*     */   public float func_150893_a(ItemStack itemstack, Block block)
/*     */   {
/* 671 */     ItemFocusBasic focus = getFocus(itemstack);
/* 672 */     if (focus != null) {
/* 673 */       return getFocusItem(itemstack).func_77973_b().func_150893_a(itemstack, null);
/*     */     }
/* 675 */     return super.func_150893_a(itemstack, block);
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList<thaumcraft.api.BlockCoordinates> getArchitectBlocks(ItemStack stack, World world, int x, int y, int z, int side, EntityPlayer player)
/*     */   {
/* 681 */     ItemFocusBasic focus = getFocus(stack);
/* 682 */     if ((focus != null) && ((focus instanceof IArchitect)) && (focus.isUpgradedWith(getFocusItem(stack), FocusUpgradeType.architect)))
/*     */     {
/* 684 */       return ((IArchitect)focus).getArchitectBlocks(stack, world, x, y, z, side, player);
/*     */     }
/* 686 */     return null;
/*     */   }
/*     */   
/*     */   public boolean showAxis(ItemStack stack, World world, EntityPlayer player, int side, IArchitect.EnumAxis axis)
/*     */   {
/* 691 */     ItemFocusBasic focus = getFocus(stack);
/* 692 */     if ((focus != null) && ((focus instanceof IArchitect)) && (focus.isUpgradedWith(getFocusItem(stack), FocusUpgradeType.architect)))
/*     */     {
/* 694 */       return ((IArchitect)focus).showAxis(stack, world, player, side, axis);
/*     */     }
/* 696 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/ItemWandCasting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */