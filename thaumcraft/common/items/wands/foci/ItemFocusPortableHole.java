/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.tiles.TileHole;
/*     */ 
/*     */ public class ItemFocusPortableHole extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusPortableHole()
/*     */   {
/*  29 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public String getSortingHelper(ItemStack itemstack)
/*     */   {
/*  34 */     return "BPH" + super.getSortingHelper(itemstack);
/*     */   }
/*     */   
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  40 */     this.depthIcon = ir.func_94245_a("thaumcraft:focus_portablehole_depth");
/*  41 */     this.icon = ir.func_94245_a("thaumcraft:focus_portablehole");
/*     */   }
/*     */   
/*     */ 
/*  45 */   IIcon depthIcon = null;
/*     */   
/*     */   public IIcon getFocusDepthLayerIcon(ItemStack itemstack)
/*     */   {
/*  49 */     return this.depthIcon;
/*     */   }
/*     */   
/*     */   public int getFocusColor(ItemStack itemstack)
/*     */   {
/*  54 */     return 594985;
/*     */   }
/*     */   
/*  57 */   private static final AspectList cost = new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.AIR, 10);
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  61 */     return cost.copy();
/*     */   }
/*     */   
/*     */   public static boolean createHole(World world, int ii, int jj, int kk, int side, byte count, int max) {
/*  65 */     Block bi = world.func_147439_a(ii, jj, kk);
/*  66 */     if ((world.func_147438_o(ii, jj, kk) == null) && (!ThaumcraftApi.portableHoleBlackList.contains(bi)) && (bi != Blocks.field_150357_h) && (bi != ConfigBlocks.blockHole) && (!bi.isAir(world, ii, jj, kk)) && (!bi.func_149742_c(world, ii, jj, kk)) && (bi.func_149712_f(world, ii, jj, kk) != -1.0F))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  74 */       TileHole ts = new TileHole(bi, world.func_72805_g(ii, jj, kk), (short)max, count, (byte)side, null);
/*     */       
/*     */ 
/*     */ 
/*  78 */       world.func_147465_d(ii, jj, kk, Blocks.field_150350_a, 0, 0);
/*  79 */       if (world.func_147465_d(ii, jj, kk, ConfigBlocks.blockHole, 0, 0)) {
/*  80 */         world.func_147455_a(ii, jj, kk, ts);
/*     */       }
/*  82 */       world.func_147471_g(ii, jj, kk);
/*  83 */       Thaumcraft.proxy.blockSparkle(world, ii, jj, kk, 4194368, 1);
/*  84 */       return true; }
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop)
/*     */   {
/*  90 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/*     */     
/*  92 */     if ((mop != null) && (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK)) {
/*  93 */       if (world.field_73011_w.field_76574_g == Config.dimensionOuterId) {
/*  94 */         if (!world.field_72995_K) {
/*  95 */           world.func_72908_a(mop.field_72311_b + 0.5D, mop.field_72312_c + 0.5D, mop.field_72309_d + 0.5D, "thaumcraft:wandfail", 1.0F, 1.0F);
/*     */         }
/*  97 */         player.func_71038_i();
/*  98 */         return itemstack;
/*     */       }
/* 100 */       int ii = mop.field_72311_b;
/* 101 */       int jj = mop.field_72312_c;
/* 102 */       int kk = mop.field_72309_d;
/* 103 */       int enlarge = wand.getFocusEnlarge(itemstack);
/* 104 */       int distance = 0;
/* 105 */       int maxdis = 33 + enlarge * 8;
/* 106 */       for (distance = 0; distance < maxdis; distance++) {
/* 107 */         Block bi = world.func_147439_a(ii, jj, kk);
/* 108 */         if ((ThaumcraftApi.portableHoleBlackList.contains(bi)) || (bi == Blocks.field_150357_h) || (bi == ConfigBlocks.blockHole) || (bi.isAir(world, ii, jj, kk)) || (bi.func_149712_f(world, ii, jj, kk) == -1.0F)) {
/*     */           break;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 115 */         switch (mop.field_72310_e) {
/* 116 */         case 0:  jj++; break;
/* 117 */         case 1:  jj--; break;
/* 118 */         case 2:  kk++; break;
/* 119 */         case 3:  kk--; break;
/* 120 */         case 4:  ii++; break;
/* 121 */         case 5:  ii--;
/*     */         }
/*     */         
/*     */       }
/* 125 */       AspectList c = getVisCost(itemstack);
/* 126 */       for (Aspect a : c.getAspects()) {
/* 127 */         c.merge(a, c.getAmount(a) * distance);
/*     */       }
/* 129 */       if (wand.consumeAllVis(itemstack, player, c, true, false)) {
/* 130 */         int di = getUpgradeLevel(wand.getFocusItem(itemstack), FocusUpgradeType.extend);
/* 131 */         short dur = (short)(120 + 60 * di);
/* 132 */         createHole(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, (byte)(distance + 1), dur);
/*     */       }
/* 134 */       player.func_71038_i();
/* 135 */       if (!world.field_72995_K) { world.func_72908_a(mop.field_72311_b + 0.5D, mop.field_72312_c + 0.5D, mop.field_72309_d + 0.5D, "mob.endermen.portal", 1.0F, 1.0F);
/*     */       }
/*     */     }
/* 138 */     return itemstack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 146 */     switch (rank) {
/* 147 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
/* 148 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
/* 149 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
/* 150 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
/* 151 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend };
/*     */     }
/* 153 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/foci/ItemFocusPortableHole.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */