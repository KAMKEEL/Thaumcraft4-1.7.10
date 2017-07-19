/*     */ package thaumcraft.common.items.baubles;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IRunicArmor;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.tiles.TileVisRelay;
/*     */ 
/*     */ public class ItemAmuletVis extends Item implements baubles.api.IBauble, IRunicArmor
/*     */ {
/*     */   public ItemAmuletVis()
/*     */   {
/*  34 */     this.field_77777_bU = 1;
/*  35 */     this.canRepair = false;
/*  36 */     func_77656_e(0);
/*  37 */     func_77637_a(Thaumcraft.tabTC);
/*  38 */     func_77627_a(true);
/*     */   }
/*     */   
/*  41 */   public IIcon[] icon = new IIcon[2];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir) {
/*  45 */     this.icon[0] = ir.func_94245_a("thaumcraft:vis_amulet_lesser");
/*  46 */     this.icon[1] = ir.func_94245_a("thaumcraft:vis_amulet");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  51 */     return par1 >= this.icon.length ? this.icon[0] : this.icon[par1];
/*     */   }
/*     */   
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  56 */     return itemstack.func_77960_j() == 0 ? EnumRarity.uncommon : EnumRarity.rare;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack stack)
/*     */   {
/*  62 */     return super.func_77658_a() + "." + stack.func_77960_j();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  68 */     par3List.add(new ItemStack(this, 1, 0));
/*  69 */     par3List.add(new ItemStack(this, 1, 1));
/*     */   }
/*     */   
/*     */   public BaubleType getBaubleType(ItemStack itemstack)
/*     */   {
/*  74 */     return BaubleType.AMULET;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onWornTick(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/*  80 */     if ((!player.field_70170_p.field_72995_K) && 
/*  81 */       (player.field_70173_aa % 5 == 0)) {
/*  82 */       if ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof ItemWandCasting))) {
/*  83 */         ItemWandCasting wand = (ItemWandCasting)player.func_70694_bm().func_77973_b();
/*  84 */         AspectList al = wand.getAspectsWithRoom(player.func_70694_bm());
/*  85 */         for (Aspect aspect : al.getAspects()) {
/*  86 */           if ((aspect != null) && (getVis(itemstack, aspect) > 0)) {
/*  87 */             int amt = Math.min(5, wand.getMaxVis(player.func_70694_bm()) - wand.getVis(player.func_70694_bm(), aspect));
/*  88 */             amt = Math.min(amt, getVis(itemstack, aspect));
/*  89 */             storeVis(itemstack, aspect, getVis(itemstack, aspect) - amt);
/*  90 */             wand.storeVis(player.func_70694_bm(), aspect, getVis(player.func_70694_bm(), aspect) + amt);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  96 */       if (TileVisRelay.nearbyPlayers.containsKey(Integer.valueOf(player.func_145782_y()))) {
/*  97 */         if ((((WeakReference)TileVisRelay.nearbyPlayers.get(Integer.valueOf(player.func_145782_y()))).get() != null) && (((TileVisRelay)((WeakReference)TileVisRelay.nearbyPlayers.get(Integer.valueOf(player.func_145782_y()))).get()).func_145835_a(player.field_70165_t, player.field_70163_u, player.field_70161_v) < 26.0D))
/*     */         {
/*  99 */           AspectList al = getAspectsWithRoom(itemstack);
/* 100 */           for (Aspect aspect : al.getAspects())
/* 101 */             if (aspect != null) {
/* 102 */               int amt = ((TileVisRelay)((WeakReference)TileVisRelay.nearbyPlayers.get(Integer.valueOf(player.func_145782_y()))).get()).consumeVis(aspect, Math.min(5, getMaxVis(itemstack) - getVis(itemstack, aspect)));
/*     */               
/*     */ 
/* 105 */               if (amt > 0) {
/* 106 */                 addRealVis(itemstack, aspect, amt, true);
/* 107 */                 ((TileVisRelay)((WeakReference)TileVisRelay.nearbyPlayers.get(Integer.valueOf(player.func_145782_y()))).get()).triggerConsumeEffect(aspect);
/*     */               }
/*     */             }
/*     */         } else {
/* 111 */           TileVisRelay.nearbyPlayers.remove(Integer.valueOf(player.func_145782_y()));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 127 */   DecimalFormat myFormatter = new DecimalFormat("#######.##");
/*     */   
/*     */   public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}
/*     */   
/* 131 */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) { if (stack.func_77960_j() == 0) {
/* 132 */       list.add(EnumChatFormatting.AQUA + StatCollector.func_74838_a("item.ItemAmuletVis.text"));
/*     */     }
/* 134 */     list.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a("item.capacity.text") + " " + getMaxVis(stack) / 100);
/* 135 */     if (stack.func_77942_o()) {
/* 136 */       for (Aspect aspect : Aspect.getPrimalAspects()) {
/* 137 */         if (stack.field_77990_d.func_74764_b(aspect.getTag())) {
/* 138 */           String amount = this.myFormatter.format(stack.field_77990_d.func_74762_e(aspect.getTag()) / 100.0F);
/* 139 */           list.add(" §" + aspect.getChatcolor() + aspect.getName() + "§r x " + amount);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int getMaxVis(ItemStack stack) {
/* 146 */     return stack.func_77960_j() == 1 ? 25000 : 2500;
/*     */   }
/*     */   
/*     */   public int getVis(ItemStack is, Aspect aspect) {
/* 150 */     int out = 0;
/* 151 */     if ((is.func_77942_o()) && (is.field_77990_d.func_74764_b(aspect.getTag()))) {
/* 152 */       out = is.field_77990_d.func_74762_e(aspect.getTag());
/*     */     }
/* 154 */     return out;
/*     */   }
/*     */   
/*     */   public void storeVis(ItemStack is, Aspect aspect, int amount) {
/* 158 */     is.func_77983_a(aspect.getTag(), new NBTTagInt(amount));
/*     */   }
/*     */   
/*     */   public AspectList getAspectsWithRoom(ItemStack wandstack) {
/* 162 */     AspectList out = new AspectList();
/* 163 */     AspectList cur = getAllVis(wandstack);
/* 164 */     for (Aspect aspect : cur.getAspects()) {
/* 165 */       if (cur.getAmount(aspect) < getMaxVis(wandstack)) {
/* 166 */         out.add(aspect, 1);
/*     */       }
/*     */     }
/* 169 */     return out;
/*     */   }
/*     */   
/*     */   public AspectList getAllVis(ItemStack is) {
/* 173 */     AspectList out = new AspectList();
/* 174 */     for (Aspect aspect : Aspect.getPrimalAspects()) {
/* 175 */       if ((is.func_77942_o()) && (is.field_77990_d.func_74764_b(aspect.getTag()))) {
/* 176 */         out.merge(aspect, is.field_77990_d.func_74762_e(aspect.getTag()));
/*     */       } else {
/* 178 */         out.merge(aspect, 0);
/*     */       }
/*     */     }
/* 181 */     return out;
/*     */   }
/*     */   
/*     */   public boolean consumeAllVis(ItemStack is, EntityPlayer player, AspectList aspects, boolean doit, boolean crafting) {
/* 185 */     if ((aspects == null) || (aspects.size() == 0)) return false;
/* 186 */     for (Aspect aspect : aspects.getAspects()) {
/* 187 */       if (getVis(is, aspect) < aspects.getAmount(aspect)) return false;
/*     */     }
/* 189 */     if (doit) {
/* 190 */       for (Aspect aspect : aspects.getAspects())
/* 191 */         storeVis(is, aspect, getVis(is, aspect) - aspects.getAmount(aspect));
/*     */     }
/* 193 */     return true;
/*     */   }
/*     */   
/*     */   public int addVis(ItemStack is, Aspect aspect, int amount, boolean doit) {
/* 197 */     if (!aspect.isPrimal()) return 0;
/* 198 */     int storeAmount = getVis(is, aspect) + amount * 100;
/* 199 */     int leftover = Math.max(storeAmount - getMaxVis(is), 0);
/* 200 */     if (doit) storeVis(is, aspect, Math.min(storeAmount, getMaxVis(is)));
/* 201 */     return leftover / 100;
/*     */   }
/*     */   
/*     */   public int addRealVis(ItemStack is, Aspect aspect, int amount, boolean doit) {
/* 205 */     if (!aspect.isPrimal()) return 0;
/* 206 */     int storeAmount = getVis(is, aspect) + amount;
/* 207 */     int leftover = Math.max(storeAmount - getMaxVis(is), 0);
/* 208 */     if (doit) storeVis(is, aspect, Math.min(storeAmount, getMaxVis(is)));
/* 209 */     return leftover;
/*     */   }
/*     */   
/*     */   public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 214 */     return itemstack.func_77960_j() != 1 ? true : thaumcraft.common.lib.research.ResearchManager.isResearchComplete(player.func_70005_c_(), "VISAMULET");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
/*     */   {
/* 220 */     return true;
/*     */   }
/*     */   
/*     */   public int getRunicCharge(ItemStack itemstack) {
/* 224 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/baubles/ItemAmuletVis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */