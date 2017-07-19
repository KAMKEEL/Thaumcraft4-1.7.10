/*    */ package thaumcraft.common.items.wands.foci;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.wands.FocusUpgradeType;
/*    */ import thaumcraft.api.wands.ItemFocusBasic;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.entities.projectile.EntityPechBlast;
/*    */ import thaumcraft.common.items.wands.ItemWandCasting;
/*    */ 
/*    */ public class ItemFocusPech extends ItemFocusBasic
/*    */ {
/*    */   public ItemFocusPech()
/*    */   {
/* 24 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public String getSortingHelper(ItemStack itemstack)
/*    */   {
/* 29 */     return "PP" + super.getSortingHelper(itemstack);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 35 */     this.icon = ir.func_94245_a("thaumcraft:focus_pech");
/* 36 */     this.depthIcon = ir.func_94245_a("thaumcraft:focus_pech_depth");
/*    */   }
/*    */   
/* 39 */   IIcon depthIcon = null;
/*    */   
/*    */   public IIcon getFocusDepthLayerIcon(ItemStack itemstack)
/*    */   {
/* 43 */     return this.depthIcon;
/*    */   }
/*    */   
/*    */   public int getActivationCooldown(ItemStack focusstack)
/*    */   {
/* 48 */     return 250;
/*    */   }
/*    */   
/*    */ 
/*    */   public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mob)
/*    */   {
/* 54 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 55 */     EntityPechBlast blast = new EntityPechBlast(world, p, wand.getFocusPotency(itemstack), wand.getFocusExtend(itemstack), isUpgradedWith(wand.getFocusItem(itemstack), nightshade));
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 60 */     if (!world.field_72995_K)
/*    */     {
/* 62 */       if (wand.consumeAllVis(itemstack, p, getVisCost(itemstack), true, false)) {
/* 63 */         world.func_72838_d(blast);
/* 64 */         world.func_72956_a(blast, "thaumcraft:ice", 0.4F, 1.0F + world.field_73012_v.nextFloat() * 0.1F);
/*    */       }
/*    */     }
/*    */     
/* 68 */     p.func_71038_i();
/*    */     
/* 70 */     return itemstack;
/*    */   }
/*    */   
/*    */   public int getFocusColor(ItemStack itemstack)
/*    */   {
/* 75 */     return 2267460;
/*    */   }
/*    */   
/* 78 */   private static final AspectList cost = new AspectList().add(Aspect.EARTH, 10).add(Aspect.ENTROPY, 10).add(Aspect.WATER, 10);
/* 79 */   private static final AspectList costAll = new AspectList().add(Aspect.AIR, 10).add(Aspect.FIRE, 10).add(Aspect.EARTH, 10).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10).add(Aspect.WATER, 10);
/*    */   
/*    */   public AspectList getVisCost(ItemStack itemstack)
/*    */   {
/* 83 */     return isUpgradedWith(itemstack, nightshade) ? costAll : cost;
/*    */   }
/*    */   
/*    */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*    */   {
/* 88 */     switch (rank) {
/* 89 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 90 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.extend };
/* 91 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 92 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.extend };
/* 93 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, nightshade };
/*    */     }
/* 95 */     return null;
/*    */   }
/*    */   
/* 98 */   public static FocusUpgradeType nightshade = new FocusUpgradeType(15, new net.minecraft.util.ResourceLocation("thaumcraft", "textures/foci/nightshade.png"), "focus.upgrade.nightshade.name", "focus.upgrade.nightshade.text", new AspectList().add(Aspect.LIFE, 1).add(Aspect.POISON, 1).add(Aspect.MAGIC, 1));
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/foci/ItemFocusPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */