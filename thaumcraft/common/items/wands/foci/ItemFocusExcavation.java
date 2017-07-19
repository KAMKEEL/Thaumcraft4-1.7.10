/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldSettings.GameType;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.event.world.BlockEvent.BreakEvent;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.ItemFocusBasic.WandFocusAnimation;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ public class ItemFocusExcavation extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusExcavation()
/*     */   {
/*  40 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  47 */     this.icon = ir.func_94245_a("thaumcraft:focus_excavation");
/*     */   }
/*     */   
/*     */   public String getSortingHelper(ItemStack itemstack)
/*     */   {
/*  52 */     return "BE" + super.getSortingHelper(itemstack);
/*     */   }
/*     */   
/*     */   public int getFocusColor(ItemStack itemstack)
/*     */   {
/*  57 */     return 409606;
/*     */   }
/*     */   
/*  60 */   private static final AspectList cost = new AspectList().add(Aspect.EARTH, 15);
/*  61 */   private static AspectList cost2 = null;
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack) {
/*  64 */     if (isUpgradedWith(itemstack, FocusUpgradeType.silktouch)) {
/*  65 */       if (cost2 == null) {
/*  66 */         cost2 = new AspectList().add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1);
/*  67 */         cost2.add(cost);
/*     */       }
/*  69 */       return cost2;
/*     */     }
/*  71 */     if (isUpgradedWith(itemstack, dowsing)) {
/*  72 */       if (cost2 == null) {
/*  73 */         cost2 = new AspectList().add(Aspect.FIRE, 2).add(Aspect.ORDER, 2);
/*  74 */         cost2.add(cost);
/*     */       }
/*  76 */       return cost2;
/*     */     }
/*  78 */     return cost;
/*     */   }
/*     */   
/*     */   public boolean isVisCostPerTick(ItemStack itemstack)
/*     */   {
/*  83 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mop)
/*     */   {
/*  90 */     p.func_71008_a(itemstack, Integer.MAX_VALUE);
/*  91 */     return itemstack;
/*     */   }
/*     */   
/*     */ 
/*  95 */   static HashMap<String, Long> soundDelay = new HashMap();
/*  96 */   static HashMap<String, Object> beam = new HashMap();
/*  97 */   static HashMap<String, Float> breakcount = new HashMap();
/*  98 */   static HashMap<String, Integer> lastX = new HashMap();
/*  99 */   static HashMap<String, Integer> lastY = new HashMap();
/* 100 */   static HashMap<String, Integer> lastZ = new HashMap();
/*     */   
/*     */   public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count)
/*     */   {
/* 104 */     ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
/* 105 */     if (!wand.consumeAllVis(stack, p, getVisCost(stack), false, false)) {
/* 106 */       p.func_71034_by();
/* 107 */       return;
/*     */     }
/* 109 */     String pp = "R" + p.func_70005_c_();
/* 110 */     if (!p.field_70170_p.field_72995_K) { pp = "S" + p.func_70005_c_();
/*     */     }
/* 112 */     if (soundDelay.get(pp) == null) soundDelay.put(pp, Long.valueOf(0L));
/* 113 */     if (breakcount.get(pp) == null) breakcount.put(pp, Float.valueOf(0.0F));
/* 114 */     if (lastX.get(pp) == null) lastX.put(pp, Integer.valueOf(0));
/* 115 */     if (lastY.get(pp) == null) lastY.put(pp, Integer.valueOf(0));
/* 116 */     if (lastZ.get(pp) == null) { lastZ.put(pp, Integer.valueOf(0));
/*     */     }
/* 118 */     MovingObjectPosition mop = BlockUtils.getTargetBlock(p.field_70170_p, p, false);
/* 119 */     Vec3 v = p.func_70040_Z();
/* 120 */     double tx = p.field_70165_t + v.field_72450_a * 10.0D;
/* 121 */     double ty = p.field_70163_u + v.field_72448_b * 10.0D;
/* 122 */     double tz = p.field_70161_v + v.field_72449_c * 10.0D;
/* 123 */     int impact = 0;
/* 124 */     if (mop != null) {
/* 125 */       tx = mop.field_72307_f.field_72450_a;
/* 126 */       ty = mop.field_72307_f.field_72448_b;
/* 127 */       tz = mop.field_72307_f.field_72449_c;
/* 128 */       impact = 5;
/* 129 */       if ((!p.field_70170_p.field_72995_K) && (((Long)soundDelay.get(pp)).longValue() < System.currentTimeMillis()))
/*     */       {
/* 131 */         p.field_70170_p.func_72908_a(tx, ty, tz, "thaumcraft:rumble", 0.3F, 1.0F);
/* 132 */         soundDelay.put(pp, Long.valueOf(System.currentTimeMillis() + 1200L));
/*     */       }
/*     */     } else {
/* 135 */       soundDelay.put(pp, Long.valueOf(0L));
/*     */     }
/*     */     
/* 138 */     if (p.field_70170_p.field_72995_K) {
/* 139 */       beam.put(pp, Thaumcraft.proxy.beamCont(p.field_70170_p, p, tx, ty, tz, 2, 65382, false, impact > 0 ? 2.0F : 0.0F, beam.get(pp), impact));
/*     */     }
/*     */     
/* 142 */     if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (p.field_70170_p.func_72962_a(p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)))
/*     */     {
/*     */ 
/* 145 */       Block bi = p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/* 146 */       int md = p.field_70170_p.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*     */       
/* 148 */       float hardness = bi.func_149712_f(p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*     */       
/* 150 */       if (hardness >= 0.0F) {
/* 151 */         int pot = wand.getFocusPotency(stack);
/* 152 */         float speed = 0.05F + pot * 0.1F;
/* 153 */         if ((bi.func_149688_o() == Material.field_151576_e) || (bi.func_149688_o() == Material.field_151577_b) || (bi.func_149688_o() == Material.field_151578_c) || (bi.func_149688_o() == Material.field_151595_p))
/*     */         {
/* 155 */           speed = 0.25F + pot * 0.25F;
/*     */         }
/* 157 */         if (bi == Blocks.field_150343_Z) { speed *= 3.0F;
/*     */         }
/* 159 */         if ((((Integer)lastX.get(pp)).intValue() == mop.field_72311_b) && (((Integer)lastY.get(pp)).intValue() == mop.field_72312_c) && (((Integer)lastZ.get(pp)).intValue() == mop.field_72309_d))
/*     */         {
/* 161 */           float bc = ((Float)breakcount.get(pp)).floatValue();
/*     */           
/* 163 */           if ((p.field_70170_p.field_72995_K) && (bc > 0.0F) && (bi != Blocks.field_150350_a)) {
/* 164 */             int progress = (int)(bc / hardness * 9.0F);
/* 165 */             Thaumcraft.proxy.excavateFX(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, p, Block.func_149682_b(bi), md, progress);
/*     */           }
/*     */           
/* 168 */           if (p.field_70170_p.field_72995_K) {
/* 169 */             if (bc >= hardness) {
/* 170 */               breakcount.put(pp, Float.valueOf(0.0F));
/*     */             } else {
/* 172 */               breakcount.put(pp, Float.valueOf(bc + speed));
/*     */             }
/*     */           }
/* 175 */           else if ((bc >= hardness) && (wand.consumeAllVis(stack, p, getVisCost(stack), true, false))) {
/* 176 */             if (excavate(p.field_70170_p, stack, p, bi, md, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d))
/*     */             {
/* 178 */               for (int a = 0; a < wand.getFocusEnlarge(stack); a++) {
/* 179 */                 if ((wand.consumeAllVis(stack, p, getVisCost(stack), false, false)) && 
/* 180 */                   (breakNeighbour(p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, bi, md, stack))) {
/* 181 */                   wand.consumeAllVis(stack, p, getVisCost(stack), true, false);
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/*     */ 
/* 187 */             lastX.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 188 */             lastY.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 189 */             lastZ.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 190 */             breakcount.put(pp, Float.valueOf(0.0F));
/*     */           } else {
/* 192 */             breakcount.put(pp, Float.valueOf(bc + speed));
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 197 */           lastX.put(pp, Integer.valueOf(mop.field_72311_b));
/* 198 */           lastY.put(pp, Integer.valueOf(mop.field_72312_c));
/* 199 */           lastZ.put(pp, Integer.valueOf(mop.field_72309_d));
/* 200 */           breakcount.put(pp, Float.valueOf(0.0F));
/*     */         }
/*     */       }
/*     */     } else {
/* 204 */       lastX.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 205 */       lastY.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 206 */       lastZ.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 207 */       breakcount.put(pp, Float.valueOf(0.0F));
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean excavate(World world, ItemStack stack, EntityPlayer player, Block block, int md, int x, int y, int z)
/*     */   {
/* 213 */     WorldSettings.GameType gt = WorldSettings.GameType.SURVIVAL;
/* 214 */     if (player.field_71075_bZ.field_75099_e) {
/* 215 */       if (player.field_71075_bZ.field_75098_d) {
/* 216 */         gt = WorldSettings.GameType.CREATIVE;
/*     */       }
/*     */     } else {
/* 219 */       gt = WorldSettings.GameType.ADVENTURE;
/*     */     }
/*     */     
/* 222 */     BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent(world, gt, (net.minecraft.entity.player.EntityPlayerMP)player, x, y, z);
/* 223 */     if (!event.isCanceled())
/*     */     {
/* 225 */       ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
/* 226 */       int fortune = wand.getFocusTreasure(stack);
/* 227 */       boolean silk = isUpgradedWith(wand.getFocusItem(stack), FocusUpgradeType.silktouch);
/*     */       
/* 229 */       if ((silk) && (block.canSilkHarvest(player.field_70170_p, player, x, y, z, md)))
/*     */       {
/* 231 */         ArrayList<ItemStack> items = new ArrayList();
/*     */         
/* 233 */         ItemStack itemstack = BlockUtils.createStackedBlock(block, md);
/*     */         
/* 235 */         if (itemstack != null)
/*     */         {
/* 237 */           items.add(itemstack);
/*     */         }
/*     */         
/* 240 */         ForgeEventFactory.fireBlockHarvesting(items, world, block, x, y, z, md, 0, 1.0F, true, player);
/* 241 */         for (ItemStack is : items)
/*     */         {
/* 243 */           BlockUtils.dropBlockAsItem(world, x, y, z, is, block);
/*     */         }
/*     */       }
/*     */       else {
/* 247 */         BlockUtils.dropBlockAsItemWithChance(world, block, x, y, z, md, 1.0F, fortune, player);
/* 248 */         block.func_149657_c(world, x, y, z, block.getExpDrop(world, md, fortune));
/*     */       }
/*     */       
/* 251 */       world.func_147468_f(x, y, z);
/* 252 */       world.func_72926_e(2001, x, y, z, Block.func_149682_b(block) + (md << 12));
/* 253 */       return true;
/*     */     }
/* 255 */     return false;
/*     */   }
/*     */   
/*     */   boolean breakNeighbour(EntityPlayer p, int x, int y, int z, Block block, int md, ItemStack stack) {
/* 259 */     java.util.List<ForgeDirection> directions = Arrays.asList(new ForgeDirection[] { ForgeDirection.DOWN, ForgeDirection.UP, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST });
/*     */     
/*     */ 
/* 262 */     Collections.shuffle(directions, p.field_70170_p.field_73012_v);
/* 263 */     for (ForgeDirection dir : directions) {
/* 264 */       if ((p.field_70170_p.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) == block) && (p.field_70170_p.func_72805_g(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) == md))
/*     */       {
/*     */ 
/* 267 */         if (excavate(p.field_70170_p, stack, p, block, md, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ))
/*     */         {
/* 269 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 274 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_77615_a(ItemStack stack, World world, EntityPlayer p, int count)
/*     */   {
/* 282 */     String pp = "R" + p.func_70005_c_();
/* 283 */     if (!p.field_70170_p.field_72995_K) { pp = "S" + p.func_70005_c_();
/*     */     }
/* 285 */     if (soundDelay.get(pp) == null) soundDelay.put(pp, Long.valueOf(0L));
/* 286 */     if (breakcount.get(pp) == null) breakcount.put(pp, Float.valueOf(0.0F));
/* 287 */     if (lastX.get(pp) == null) lastX.put(pp, Integer.valueOf(0));
/* 288 */     if (lastY.get(pp) == null) lastY.put(pp, Integer.valueOf(0));
/* 289 */     if (lastZ.get(pp) == null) lastZ.put(pp, Integer.valueOf(0));
/* 290 */     beam.put(pp, null);
/* 291 */     lastX.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 292 */     lastY.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 293 */     lastZ.put(pp, Integer.valueOf(Integer.MAX_VALUE));
/* 294 */     breakcount.put(pp, Float.valueOf(0.0F));
/*     */   }
/*     */   
/*     */   public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack itemstack)
/*     */   {
/* 299 */     return ItemFocusBasic.WandFocusAnimation.CHARGE;
/*     */   }
/*     */   
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 304 */     switch (rank) {
/* 305 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure };
/* 306 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge };
/* 307 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure, dowsing };
/* 308 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge };
/* 309 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure, FocusUpgradeType.silktouch };
/*     */     }
/* 311 */     return null;
/*     */   }
/*     */   
/* 314 */   public static FocusUpgradeType dowsing = new FocusUpgradeType(20, new ResourceLocation("thaumcraft", "textures/foci/dowsing.png"), "focus.upgrade.dowsing.name", "focus.upgrade.dowsing.text", new AspectList().add(Aspect.MINE, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/foci/ItemFocusExcavation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */