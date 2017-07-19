/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.projectile.EntityFrostShard;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ 
/*     */ public class ItemFocusFrost extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusFrost()
/*     */   {
/*  23 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public String getSortingHelper(ItemStack itemstack)
/*     */   {
/*  28 */     return "BF" + super.getSortingHelper(itemstack);
/*     */   }
/*     */   
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  34 */     this.icon = ir.func_94245_a("thaumcraft:focus_frost");
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mob)
/*     */   {
/*  40 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/*     */     
/*  42 */     if (!world.field_72995_K)
/*     */     {
/*  44 */       if (wand.consumeAllVis(itemstack, p, getVisCost(itemstack), true, false)) {
/*  45 */         int frosty = getUpgradeLevel(wand.getFocusItem(itemstack), FocusUpgradeType.alchemistsfrost);
/*  46 */         EntityFrostShard shard = null;
/*  47 */         if (isUpgradedWith(wand.getFocusItem(itemstack), scattershot)) {
/*  48 */           for (int a = 0; a < 5 + wand.getFocusPotency(itemstack) * 2; a++) {
/*  49 */             shard = new EntityFrostShard(world, p, 8.0F);
/*  50 */             shard.setDamage(1.0F);
/*  51 */             shard.fragile = true;
/*  52 */             shard.setFrosty(frosty);
/*  53 */             world.func_72838_d(shard);
/*     */           }
/*     */         }
/*  56 */         else if (isUpgradedWith(wand.getFocusItem(itemstack), iceboulder)) {
/*  57 */           shard = new EntityFrostShard(world, p, 1.0F);
/*  58 */           shard.setDamage(4 + wand.getFocusPotency(itemstack) * 2);
/*  59 */           shard.bounce = 0.8D;
/*  60 */           shard.bounceLimit = 6;
/*  61 */           shard.setFrosty(frosty);
/*  62 */           world.func_72838_d(shard);
/*     */         }
/*     */         else {
/*  65 */           shard = new EntityFrostShard(world, p, 1.0F);
/*  66 */           shard.setDamage((float)(3.0D + wand.getFocusPotency(itemstack) * 1.5D));
/*  67 */           shard.setFrosty(frosty);
/*  68 */           world.func_72838_d(shard);
/*     */         }
/*  70 */         world.func_72956_a(shard, "thaumcraft:ice", 0.4F, 1.0F + world.field_73012_v.nextFloat() * 0.1F);
/*     */       }
/*     */     }
/*     */     
/*  74 */     p.func_71038_i();
/*     */     
/*     */ 
/*  77 */     return itemstack;
/*     */   }
/*     */   
/*     */   public int getFocusColor(ItemStack itemstack)
/*     */   {
/*  82 */     return 5204428;
/*     */   }
/*     */   
/*  85 */   private static final AspectList costBase = new AspectList().add(Aspect.WATER, 5).add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 2);
/*  86 */   private static final AspectList costScatter = new AspectList().add(Aspect.WATER, 20).add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 2).add(Aspect.AIR, 5);
/*  87 */   private static final AspectList costBoulder = new AspectList().add(Aspect.WATER, 20).add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 2).add(Aspect.EARTH, 5);
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  91 */     return isUpgradedWith(itemstack, iceboulder) ? costBoulder : isUpgradedWith(itemstack, scattershot) ? costScatter : costBase;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getActivationCooldown(ItemStack focusstack)
/*     */   {
/*  98 */     return (getUpgradeLevel(focusstack, scattershot) > 0) || (getUpgradeLevel(focusstack, iceboulder) > 0) ? 500 : 200;
/*     */   }
/*     */   
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 103 */     switch (rank) {
/* 104 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfrost };
/* 105 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 106 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, scattershot, iceboulder, FocusUpgradeType.alchemistsfrost };
/* 107 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 108 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfrost };
/*     */     }
/* 110 */     return null;
/*     */   }
/*     */   
/* 113 */   public static FocusUpgradeType scattershot = new FocusUpgradeType(11, new ResourceLocation("thaumcraft", "textures/foci/scattershot.png"), "focus.upgrade.scattershot.name", "focus.upgrade.scattershot.text", new AspectList().add(Aspect.COLD, 1).add(Aspect.WEAPON, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 118 */   public static FocusUpgradeType iceboulder = new FocusUpgradeType(12, new ResourceLocation("thaumcraft", "textures/foci/iceboulder.png"), "focus.upgrade.iceboulder.name", "focus.upgrade.iceboulder.text", new AspectList().add(Aspect.COLD, 1).add(Aspect.CRYSTAL, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/foci/ItemFocusFrost.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */