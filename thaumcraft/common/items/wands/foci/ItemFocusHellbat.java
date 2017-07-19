/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.monster.EntityFireBat;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class ItemFocusHellbat extends ItemFocusBasic
/*     */ {
/*     */   public IIcon iconOrnament;
/*     */   
/*     */   public ItemFocusHellbat()
/*     */   {
/*  32 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public String getSortingHelper(ItemStack itemstack)
/*     */   {
/*  37 */     return "HH" + super.getSortingHelper(itemstack);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  44 */     this.icon = ir.func_94245_a("thaumcraft:focus_hellbat");
/*  45 */     this.iconOrnament = ir.func_94245_a("thaumcraft:focus_hellbat_orn");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77618_c(int par1, int renderPass)
/*     */   {
/*  51 */     return renderPass == 1 ? this.icon : this.iconOrnament;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */   
/*     */   public IIcon getOrnament(ItemStack itemstack)
/*     */   {
/*  63 */     return this.iconOrnament;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition)
/*     */   {
/*  69 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/*     */     
/*     */ 
/*  72 */     net.minecraft.entity.Entity pointedEntity = EntityUtils.getPointedEntity(player.field_70170_p, player, 32.0D, EntityFireBat.class);
/*     */     
/*  74 */     double px = player.field_70165_t;
/*  75 */     double py = player.field_70163_u;
/*  76 */     double pz = player.field_70161_v;
/*  77 */     py = player.field_70121_D.field_72338_b + player.field_70131_O / 2.0F + 0.25D;
/*  78 */     px -= MathHelper.func_76134_b(player.field_70177_z / 180.0F * 3.141593F) * 0.16F;
/*  79 */     py -= 0.05000000014901161D;
/*  80 */     pz -= MathHelper.func_76126_a(player.field_70177_z / 180.0F * 3.141593F) * 0.16F;
/*  81 */     Vec3 vec3d = player.func_70676_i(1.0F);
/*  82 */     px += vec3d.field_72450_a * 0.5D;
/*  83 */     py += vec3d.field_72448_b * 0.5D;
/*  84 */     pz += vec3d.field_72449_c * 0.5D;
/*     */     
/*  86 */     if ((pointedEntity != null) && ((pointedEntity instanceof net.minecraft.entity.EntityLivingBase))) {
/*  87 */       if (!world.field_72995_K) {
/*  88 */         if (((pointedEntity instanceof EntityPlayer)) && (!MinecraftServer.func_71276_C().func_71219_W())) return itemstack;
/*  89 */         EntityFireBat firebat = new EntityFireBat(world);
/*  90 */         firebat.func_70012_b(px, py + firebat.field_70131_O, pz, player.field_70177_z, 0.0F);
/*  91 */         firebat.func_70784_b(pointedEntity);
/*  92 */         firebat.damBonus = wand.getFocusPotency(itemstack);
/*  93 */         firebat.setIsSummoned(true);
/*  94 */         firebat.setIsBatHanging(false);
/*  95 */         if (isUpgradedWith(wand.getFocusItem(itemstack), devilbats)) {
/*  96 */           firebat.setIsDevil(true);
/*     */         }
/*  98 */         if (isUpgradedWith(wand.getFocusItem(itemstack), batbombs)) {
/*  99 */           firebat.setIsExplosive(true);
/*     */         }
/* 101 */         if (isUpgradedWith(wand.getFocusItem(itemstack), vampirebats)) {
/* 102 */           firebat.owner = player;
/* 103 */           firebat.setIsVampire(true);
/*     */         }
/* 105 */         if ((wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, false)) && (world.func_72838_d(firebat)))
/*     */         {
/* 107 */           world.func_72926_e(2004, (int)px, (int)py, (int)pz, 0);
/* 108 */           world.func_72956_a(firebat, "thaumcraft:ice", 0.2F, 0.95F + world.field_73012_v.nextFloat() * 0.1F);
/*     */         } else {
/* 110 */           world.func_72956_a(player, "thaumcraft:wandfail", 0.1F, 0.8F + world.field_73012_v.nextFloat() * 0.1F);
/*     */         }
/*     */       }
/*     */       
/* 114 */       player.func_71038_i();
/*     */     }
/*     */     
/* 117 */     return itemstack;
/*     */   }
/*     */   
/*     */   public int getFocusColor(ItemStack itemstack)
/*     */   {
/* 122 */     return 14431746;
/*     */   }
/*     */   
/* 125 */   private static final AspectList costBase = new AspectList().add(Aspect.FIRE, 200).add(Aspect.ENTROPY, 100).add(Aspect.AIR, 100);
/* 126 */   private static final AspectList costBomb = new AspectList().add(Aspect.FIRE, 100).add(Aspect.ENTROPY, 200).add(Aspect.AIR, 100);
/* 127 */   private static final AspectList costDevil = new AspectList().add(Aspect.FIRE, 100).add(Aspect.ENTROPY, 100).add(Aspect.AIR, 100).add(Aspect.EARTH, 100);
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/* 131 */     return isUpgradedWith(itemstack, devilbats) ? costDevil : isUpgradedWith(itemstack, batbombs) ? costBomb : costBase;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getActivationCooldown(ItemStack focusstack)
/*     */   {
/* 138 */     return 1000;
/*     */   }
/*     */   
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 143 */     switch (rank) {
/* 144 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 145 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 146 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, batbombs, devilbats };
/* 147 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/* 148 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, vampirebats };
/*     */     }
/* 150 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
/*     */   {
/* 155 */     if ((type.equals(vampirebats)) && (!thaumcraft.api.ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "VAMPBAT")))
/*     */     {
/* 157 */       return false; }
/* 158 */     return true;
/*     */   }
/*     */   
/* 161 */   public static FocusUpgradeType batbombs = new FocusUpgradeType(13, new ResourceLocation("thaumcraft", "textures/foci/batbombs.png"), "focus.upgrade.batbombs.name", "focus.upgrade.batbombs.text", new AspectList().add(Aspect.ENERGY, 1).add(Aspect.TRAP, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 166 */   public static FocusUpgradeType devilbats = new FocusUpgradeType(14, new ResourceLocation("thaumcraft", "textures/foci/devilbats.png"), "focus.upgrade.devilbats.name", "focus.upgrade.devilbats.text", new AspectList().add(Aspect.ARMOR, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 171 */   public static FocusUpgradeType vampirebats = new FocusUpgradeType(19, new ResourceLocation("thaumcraft", "textures/foci/vampirebats.png"), "focus.upgrade.vampirebats.name", "focus.upgrade.vampirebats.text", new AspectList().add(Aspect.HUNGER, 1).add(Aspect.LIFE, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/foci/ItemFocusHellbat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */