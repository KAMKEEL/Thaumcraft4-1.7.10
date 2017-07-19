/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.ItemFocusBasic.WandFocusAnimation;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.projectile.EntityEmber;
/*     */ import thaumcraft.common.entities.projectile.EntityExplosiveOrb;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemFocusFire
/*     */   extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusFire()
/*     */   {
/*  31 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  38 */     this.icon = ir.func_94245_a("thaumcraft:focus_fire");
/*     */   }
/*     */   
/*     */   public String getSortingHelper(ItemStack itemstack)
/*     */   {
/*  43 */     return "AF" + super.getSortingHelper(itemstack);
/*     */   }
/*     */   
/*     */   public int getFocusColor(ItemStack itemstack)
/*     */   {
/*  48 */     return 15028484;
/*     */   }
/*     */   
/*  51 */   private static final AspectList costBase = new AspectList().add(Aspect.FIRE, 10);
/*  52 */   private static final AspectList costBeam = new AspectList().add(Aspect.FIRE, 10).add(Aspect.ORDER, 3);
/*  53 */   private static final AspectList costBall = new AspectList().add(Aspect.FIRE, 66).add(Aspect.ENTROPY, 33);
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  57 */     return isUpgradedWith(itemstack, fireball) ? costBall : isUpgradedWith(itemstack, firebeam) ? costBeam : costBase;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getActivationCooldown(ItemStack focusstack)
/*     */   {
/*  64 */     return isUpgradedWith(focusstack, fireball) ? 1000 : 0;
/*     */   }
/*     */   
/*     */   public boolean isVisCostPerTick(ItemStack itemstack)
/*     */   {
/*  69 */     return true;
/*     */   }
/*     */   
/*     */   public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack itemstack)
/*     */   {
/*  74 */     return isUpgradedWith(itemstack, fireball) ? ItemFocusBasic.WandFocusAnimation.WAVE : ItemFocusBasic.WandFocusAnimation.CHARGE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition movingobjectposition)
/*     */   {
/*  82 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/*  83 */     if (isUpgradedWith(wand.getFocusItem(itemstack), fireball)) {
/*  84 */       if (wand.consumeAllVis(itemstack, p, getVisCost(itemstack), !p.field_70170_p.field_72995_K, false)) {
/*  85 */         if (!world.field_72995_K) {
/*  86 */           EntityExplosiveOrb orb = new EntityExplosiveOrb(world, p);
/*  87 */           orb.strength += wand.getFocusPotency(itemstack) * 0.4F;
/*  88 */           orb.onFire = isUpgradedWith(wand.getFocusItem(itemstack), FocusUpgradeType.alchemistsfire);
/*  89 */           world.func_72838_d(orb);
/*  90 */           world.func_72889_a((EntityPlayer)null, 1009, (int)p.field_70165_t, (int)p.field_70163_u, (int)p.field_70161_v, 0);
/*     */         }
/*  92 */         p.func_71038_i();
/*     */       }
/*     */     } else {
/*  95 */       p.func_71008_a(itemstack, Integer.MAX_VALUE);
/*  96 */       WandManager.setCooldown(p, -1);
/*     */     }
/*     */     
/*  99 */     return itemstack;
/*     */   }
/*     */   
/* 102 */   long soundDelay = 0L;
/*     */   
/*     */   public void onUsingFocusTick(ItemStack itemstack, EntityPlayer p, int count)
/*     */   {
/* 106 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 107 */     if (!wand.consumeAllVis(itemstack, p, getVisCost(itemstack), false, false)) {
/* 108 */       p.func_71034_by();
/* 109 */       return;
/*     */     }
/* 111 */     int range = 17;
/* 112 */     Vec3 vec3d = p.func_70676_i(range);
/* 113 */     if ((!p.field_70170_p.field_72995_K) && (this.soundDelay < System.currentTimeMillis()))
/*     */     {
/* 115 */       p.field_70170_p.func_72956_a(p, "thaumcraft:fireloop", 0.33F, 2.0F);
/* 116 */       this.soundDelay = (System.currentTimeMillis() + 500L);
/*     */     }
/*     */     
/* 119 */     if ((!p.field_70170_p.field_72995_K) && (wand.consumeAllVis(itemstack, p, getVisCost(itemstack), true, false))) {
/* 120 */       float scatter = isUpgradedWith(wand.getFocusItem(itemstack), firebeam) ? 0.25F : 15.0F;
/* 121 */       for (int a = 0; a < 2 + wand.getFocusPotency(itemstack); a++) {
/* 122 */         EntityEmber orb = new EntityEmber(p.field_70170_p, p, scatter);
/* 123 */         orb.damage = (2 + wand.getFocusPotency(itemstack));
/* 124 */         if (isUpgradedWith(wand.getFocusItem(itemstack), firebeam)) {
/* 125 */           orb.damage += 0.5F;
/* 126 */           orb.damage *= 1.5F;
/* 127 */           orb.duration = 30;
/*     */         }
/* 129 */         orb.firey = getUpgradeLevel(wand.getFocusItem(itemstack), FocusUpgradeType.alchemistsfire);
/* 130 */         orb.field_70165_t += orb.field_70159_w;
/* 131 */         orb.field_70163_u += orb.field_70181_x;
/* 132 */         orb.field_70161_v += orb.field_70179_y;
/* 133 */         p.field_70170_p.func_72838_d(orb);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 140 */     switch (rank) {
/* 141 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/*     */     case 2: 
/* 143 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfire };
/*     */     case 3: 
/* 145 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, fireball, firebeam };
/*     */     case 4: 
/* 147 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfire };
/*     */     case 5: 
/* 149 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/*     */     }
/*     */     
/* 152 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
/*     */   {
/* 157 */     if ((type.equals(FocusUpgradeType.alchemistsfire)) && (isUpgradedWith(focusstack, fireball)) && (isUpgradedWith(focusstack, FocusUpgradeType.alchemistsfire)))
/*     */     {
/* 159 */       return false; }
/* 160 */     return true;
/*     */   }
/*     */   
/* 163 */   public static FocusUpgradeType fireball = new FocusUpgradeType(9, new ResourceLocation("thaumcraft", "textures/foci/fireball.png"), "focus.upgrade.fireball.name", "focus.upgrade.fireball.text", new AspectList().add(Aspect.DARKNESS, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 168 */   public static FocusUpgradeType firebeam = new FocusUpgradeType(10, new ResourceLocation("thaumcraft", "textures/foci/firebeam.png"), "focus.upgrade.firebeam.name", "focus.upgrade.firebeam.text", new AspectList().add(Aspect.ENERGY, 1).add(Aspect.AIR, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/foci/ItemFocusFire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */