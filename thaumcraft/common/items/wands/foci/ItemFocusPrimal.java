/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalOrb;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ 
/*     */ public class ItemFocusPrimal
/*     */   extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusPrimal()
/*     */   {
/*  26 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public String getSortingHelper(ItemStack itemstack)
/*     */   {
/*  31 */     return "FP" + super.getSortingHelper(itemstack);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  37 */     this.icon = ir.func_94245_a("thaumcraft:focus_primal");
/*  38 */     this.depthIcon = ir.func_94245_a("thaumcraft:focus_primal_depth");
/*     */   }
/*     */   
/*  41 */   IIcon depthIcon = null;
/*     */   
/*     */   public IIcon getFocusDepthLayerIcon(ItemStack itemstack)
/*     */   {
/*  45 */     return this.depthIcon;
/*     */   }
/*     */   
/*     */   public int getActivationCooldown(ItemStack focusstack)
/*     */   {
/*  50 */     return 500;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mob)
/*     */   {
/*  56 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/*  57 */     EntityPrimalOrb shard = new EntityPrimalOrb(world, p, isUpgradedWith(wand.getFocusItem(itemstack), seeker));
/*  58 */     if (!world.field_72995_K)
/*     */     {
/*  60 */       if (wand.consumeAllVis(itemstack, p, getVisCost(itemstack), true, false)) {
/*  61 */         world.func_72838_d(shard);
/*  62 */         world.func_72956_a(shard, "thaumcraft:ice", 0.3F, 0.8F + world.field_73012_v.nextFloat() * 0.1F);
/*     */       }
/*     */     }
/*     */     
/*  66 */     p.func_71038_i();
/*     */     
/*     */ 
/*  69 */     return itemstack;
/*     */   }
/*     */   
/*     */   public int getFocusColor(ItemStack itemstack)
/*     */   {
/*  74 */     return 10854849;
/*     */   }
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  79 */     Random rand = new Random(System.currentTimeMillis() / 200L);
/*  80 */     AspectList cost = new AspectList().add(Aspect.WATER, 50 + rand.nextInt(5) * 50).add(Aspect.AIR, 50 + rand.nextInt(5) * 50).add(Aspect.EARTH, 50 + rand.nextInt(5) * 50).add(Aspect.FIRE, 50 + rand.nextInt(5) * 50).add(Aspect.ORDER, 50 + rand.nextInt(5) * 50).add(Aspect.ENTROPY, 50 + rand.nextInt(5) * 50);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  87 */     return cost;
/*     */   }
/*     */   
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/*  92 */     switch (rank) {
/*  93 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal };
/*  94 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal };
/*  95 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, seeker };
/*  96 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal };
/*  97 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal };
/*     */     }
/*  99 */     return null;
/*     */   }
/*     */   
/* 102 */   public static FocusUpgradeType seeker = new FocusUpgradeType(16, new ResourceLocation("thaumcraft", "textures/foci/seeker.png"), "focus.upgrade.seeker.name", "focus.upgrade.seeker.text", new AspectList().add(Aspect.SENSES, 1).add(Aspect.MIND, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/foci/ItemFocusPrimal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */