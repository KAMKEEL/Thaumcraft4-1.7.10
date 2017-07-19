/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.ItemFocusBasic.WandFocusAnimation;
/*     */ import thaumcraft.client.fx.bolt.FXLightningBolt;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.projectile.EntityShockOrb;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class ItemFocusShock extends ItemFocusBasic
/*     */ {
/*     */   public ItemFocusShock()
/*     */   {
/*  39 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  45 */     this.icon = ir.func_94245_a("thaumcraft:focus_shock");
/*     */   }
/*     */   
/*     */   public String getSortingHelper(ItemStack itemstack) {
/*  49 */     return "BL" + super.getSortingHelper(itemstack);
/*     */   }
/*     */   
/*     */   public int getFocusColor(ItemStack itemstack)
/*     */   {
/*  54 */     return 10466239;
/*     */   }
/*     */   
/*  57 */   private static final AspectList costBase = new AspectList().add(Aspect.AIR, 25);
/*  58 */   private static final AspectList costChain = new AspectList().add(Aspect.AIR, 40).add(Aspect.WATER, 10);
/*  59 */   private static final AspectList costGround = new AspectList().add(Aspect.AIR, 75).add(Aspect.EARTH, 25);
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  63 */     return isUpgradedWith(itemstack, earthshock) ? costGround : isUpgradedWith(itemstack, chainlightning) ? costChain : costBase;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getActivationCooldown(ItemStack focusstack)
/*     */   {
/*  69 */     return isUpgradedWith(focusstack, earthshock) ? 1000 : isUpgradedWith(focusstack, chainlightning) ? 500 : 250;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack itemstack)
/*     */   {
/*  76 */     return isUpgradedWith(itemstack, earthshock) ? ItemFocusBasic.WandFocusAnimation.WAVE : ItemFocusBasic.WandFocusAnimation.CHARGE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void shootLightning(World world, EntityLivingBase entityplayer, double xx, double yy, double zz, boolean offset)
/*     */   {
/*  83 */     double px = entityplayer.field_70165_t;
/*  84 */     double py = entityplayer.field_70163_u;
/*  85 */     double pz = entityplayer.field_70161_v;
/*  86 */     if (entityplayer.func_145782_y() != FMLClientHandler.instance().getClient().field_71439_g.func_145782_y()) {
/*  87 */       py = entityplayer.field_70121_D.field_72338_b + entityplayer.field_70131_O / 2.0F + 0.25D;
/*     */     }
/*  89 */     px += -MathHelper.func_76134_b(entityplayer.field_70177_z / 180.0F * 3.141593F) * 0.06F;
/*  90 */     py += -0.05999999865889549D;
/*  91 */     pz += -MathHelper.func_76126_a(entityplayer.field_70177_z / 180.0F * 3.141593F) * 0.06F;
/*  92 */     if (entityplayer.func_145782_y() != FMLClientHandler.instance().getClient().field_71439_g.func_145782_y()) {
/*  93 */       py = entityplayer.field_70121_D.field_72338_b + entityplayer.field_70131_O / 2.0F + 0.25D;
/*     */     }
/*  95 */     Vec3 vec3d = entityplayer.func_70676_i(1.0F);
/*  96 */     px += vec3d.field_72450_a * 0.3D;
/*  97 */     py += vec3d.field_72448_b * 0.3D;
/*  98 */     pz += vec3d.field_72449_c * 0.3D;
/*     */     
/* 100 */     FXLightningBolt bolt = new FXLightningBolt(world, px, py, pz, xx, yy, zz, world.field_73012_v.nextLong(), 6, 0.5F, 8);
/*     */     
/*     */ 
/*     */ 
/* 104 */     bolt.defaultFractal();
/* 105 */     bolt.setType(2);
/* 106 */     bolt.setWidth(0.125F);
/* 107 */     bolt.finalizeBolt();
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition movingobjectposition)
/*     */   {
/* 113 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 114 */     if (isUpgradedWith(wand.getFocusItem(itemstack), earthshock)) {
/* 115 */       if (wand.consumeAllVis(itemstack, p, getVisCost(itemstack), !p.field_70170_p.field_72995_K, false)) {
/* 116 */         if (!world.field_72995_K) {
/* 117 */           EntityShockOrb orb = new EntityShockOrb(world, p);
/* 118 */           orb.area += getUpgradeLevel(wand.getFocusItem(itemstack), FocusUpgradeType.enlarge) * 2; EntityShockOrb 
/* 119 */             tmp101_99 = orb;tmp101_99.damage = ((int)(tmp101_99.damage + wand.getFocusPotency(itemstack) * 1.33D));
/* 120 */           world.func_72838_d(orb);
/* 121 */           world.func_72956_a(orb, "thaumcraft:zap", 1.0F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.2F);
/*     */         }
/* 123 */         p.func_71038_i();
/*     */       }
/*     */     } else {
/* 126 */       p.func_71008_a(itemstack, Integer.MAX_VALUE);
/* 127 */       thaumcraft.common.items.wands.WandManager.setCooldown(p, -1);
/*     */     }
/* 129 */     return itemstack;
/*     */   }
/*     */   
/*     */   public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count)
/*     */   {
/* 134 */     doLightningBolt(stack, p, count);
/*     */   }
/*     */   
/*     */   public void doLightningBolt(ItemStack stack, EntityPlayer p, int count)
/*     */   {
/* 139 */     ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
/* 140 */     if (!wand.consumeAllVis(stack, p, getVisCost(stack), !p.field_70170_p.field_72995_K, false)) {
/* 141 */       p.func_71034_by();
/* 142 */       return;
/*     */     }
/*     */     
/* 145 */     int potency = wand.getFocusPotency(stack);
/*     */     
/* 147 */     Entity pointedEntity = EntityUtils.getPointedEntity(p.field_70170_p, p, 0.0D, 20.0D, 1.1F);
/*     */     
/* 149 */     if (p.field_70170_p.field_72995_K) {
/* 150 */       MovingObjectPosition mop = thaumcraft.common.lib.utils.BlockUtils.getTargetBlock(p.field_70170_p, p, false);
/* 151 */       Vec3 v = p.func_70676_i(2.0F);
/* 152 */       double px = p.field_70165_t + v.field_72450_a * 10.0D;
/* 153 */       double py = p.field_70163_u + v.field_72448_b * 10.0D;
/* 154 */       double pz = p.field_70161_v + v.field_72449_c * 10.0D;
/* 155 */       if (mop != null) {
/* 156 */         px = mop.field_72307_f.field_72450_a;
/* 157 */         py = mop.field_72307_f.field_72448_b;
/* 158 */         pz = mop.field_72307_f.field_72449_c;
/* 159 */         for (int a = 0; a < 5; a++) {
/* 160 */           Thaumcraft.proxy.sparkle((float)px + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.3F, (float)py + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.3F, (float)pz + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.3F, 2.0F + p.field_70170_p.field_73012_v.nextFloat(), 2, 0.05F + p.field_70170_p.field_73012_v.nextFloat() * 0.05F);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 165 */       if (pointedEntity != null) {
/* 166 */         px = pointedEntity.field_70165_t;
/* 167 */         py = pointedEntity.field_70121_D.field_72338_b + pointedEntity.field_70131_O / 2.0F;
/* 168 */         pz = pointedEntity.field_70161_v;
/* 169 */         for (int a = 0; a < 5; a++) {
/* 170 */           Thaumcraft.proxy.sparkle((float)px + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.6F, (float)py + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.6F, (float)pz + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.6F, 2.0F + p.field_70170_p.field_73012_v.nextFloat(), 2, 0.05F + p.field_70170_p.field_73012_v.nextFloat() * 0.05F);
/*     */         }
/*     */       }
/*     */       
/* 174 */       shootLightning(p.field_70170_p, p, px, py, pz, true);
/*     */     } else {
/* 176 */       p.field_70170_p.func_72908_a(p.field_70165_t, p.field_70163_u, p.field_70161_v, "thaumcraft:shock", 0.25F, 1.0F);
/*     */       
/* 178 */       if ((pointedEntity != null) && ((pointedEntity instanceof EntityLivingBase)) && (
/* 179 */         (!(pointedEntity instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W())))
/*     */       {
/* 181 */         int cl = getUpgradeLevel(wand.getFocusItem(stack), chainlightning) * 2;
/* 182 */         pointedEntity.func_70097_a(DamageSource.func_76365_a(p), (cl > 0 ? 6 : 4) + potency);
/*     */         
/* 184 */         if (cl > 0) {
/* 185 */           cl += getUpgradeLevel(wand.getFocusItem(stack), FocusUpgradeType.enlarge) * 2;
/* 186 */           EntityLivingBase center = (EntityLivingBase)pointedEntity;
/* 187 */           ArrayList<Integer> targets = new ArrayList();
/* 188 */           targets.add(Integer.valueOf(pointedEntity.func_145782_y()));
/* 189 */           while (cl > 0) {
/* 190 */             cl--;
/* 191 */             ArrayList<Entity> list = EntityUtils.getEntitiesInRange(p.field_70170_p, center.field_70165_t, center.field_70163_u, center.field_70161_v, p, EntityLivingBase.class, 8.0D);
/*     */             
/*     */ 
/*     */ 
/* 195 */             double d = Double.MAX_VALUE;
/* 196 */             Entity closest = null;
/* 197 */             for (Entity e : list)
/* 198 */               if ((!targets.contains(Integer.valueOf(e.func_145782_y()))) && (
/* 199 */                 (!(e instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W()))) {
/* 200 */                 double dd = e.func_70068_e(center);
/* 201 */                 if (dd < d) {
/* 202 */                   closest = e;
/* 203 */                   d = dd;
/*     */                 }
/*     */               }
/* 206 */             if (closest != null) {
/* 207 */               thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new thaumcraft.common.lib.network.fx.PacketFXZap(center.func_145782_y(), closest.func_145782_y()), new NetworkRegistry.TargetPoint(p.field_70170_p.field_73011_w.field_76574_g, center.field_70165_t, center.field_70163_u, center.field_70161_v, 64.0D));
/*     */               
/*     */ 
/*     */ 
/* 211 */               targets.add(Integer.valueOf(closest.func_145782_y()));
/* 212 */               closest.func_70097_a(DamageSource.func_76365_a(p), 4 + potency);
/*     */               
/* 214 */               center = (EntityLivingBase)closest;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
/*     */   {
/* 226 */     if ((type.equals(FocusUpgradeType.enlarge)) && 
/* 227 */       (!isUpgradedWith(focusstack, chainlightning)) && (!isUpgradedWith(focusstack, earthshock)))
/*     */     {
/* 229 */       return false;
/*     */     }
/* 231 */     return true;
/*     */   }
/*     */   
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 236 */     switch (rank) {
/* 237 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/*     */     case 2: 
/* 239 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
/*     */     case 3: 
/* 241 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, chainlightning, earthshock };
/*     */     case 4: 
/* 243 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge };
/*     */     case 5: 
/* 245 */       return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge };
/*     */     }
/*     */     
/* 248 */     return null;
/*     */   }
/*     */   
/* 251 */   public static FocusUpgradeType chainlightning = new FocusUpgradeType(17, new ResourceLocation("thaumcraft", "textures/foci/chainlightning.png"), "focus.upgrade.chainlightning.name", "focus.upgrade.chainlightning.text", new AspectList().add(Aspect.WEATHER, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 256 */   public static FocusUpgradeType earthshock = new FocusUpgradeType(18, new ResourceLocation("thaumcraft", "textures/foci/earthshock.png"), "focus.upgrade.earthshock.name", "focus.upgrade.earthshock.text", new AspectList().add(Aspect.WEATHER, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/foci/ItemFocusShock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */