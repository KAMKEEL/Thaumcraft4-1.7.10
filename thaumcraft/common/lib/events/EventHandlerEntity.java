/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import com.google.common.io.Files;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityEnderPearl;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityChicken;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.passive.EntityPig;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemExpireEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDropsEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowLooseEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowNockEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityInteractEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent.LoadFromFile;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;
/*     */ import net.minecraftforge.event.entity.player.PlayerUseItemEvent.Finish;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.IRepairable;
/*     */ import thaumcraft.api.IRepairableExtended;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.damagesource.DamageSourceThaumcraft;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.research.ResearchCategoryList;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigEntities;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.EntityAspectOrb;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.monster.EntityTaintCow;
/*     */ import thaumcraft.common.entities.monster.EntityTaintSheep;
/*     */ import thaumcraft.common.entities.monster.EntityThaumicSlime;
/*     */ import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ import thaumcraft.common.entities.monster.mods.IChampionModifierEffect;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalArrow;
/*     */ import thaumcraft.common.items.ItemBathSalts;
/*     */ import thaumcraft.common.items.ItemCrystalEssence;
/*     */ import thaumcraft.common.items.armor.Hover;
/*     */ import thaumcraft.common.items.armor.ItemHoverHarness;
/*     */ import thaumcraft.common.items.equipment.ItemBowBone;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.WarpEvents;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.research.ScanManager;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.lib.world.dim.Cell;
/*     */ import thaumcraft.common.lib.world.dim.CellLoc;
/*     */ 
/*     */ public class EventHandlerEntity
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void droppedItem(ItemTossEvent event)
/*     */   {
/* 111 */     NBTTagCompound itemData = event.entityItem.getEntityData();
/* 112 */     itemData.func_74778_a("thrower", event.player.func_70005_c_());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void playerLoad(PlayerEvent.LoadFromFile event)
/*     */   {
/* 120 */     EntityPlayer p = event.entityPlayer;
/* 121 */     Thaumcraft.proxy.getPlayerKnowledge().wipePlayerKnowledge(p.func_70005_c_());
/*     */     
/* 123 */     File file1 = getPlayerFile("thaum", event.playerDirectory, p.func_70005_c_());
/* 124 */     boolean legacy = false;
/* 125 */     if (!file1.exists()) {
/* 126 */       File filep = event.getPlayerFile("thaum");
/* 127 */       if (filep.exists()) {
/*     */         try {
/* 129 */           Files.copy(filep, file1);
/* 130 */           Thaumcraft.log.info("Using and converting UUID Thaumcraft savefile for " + p.func_70005_c_());
/* 131 */           legacy = true;
/* 132 */           filep.delete();
/* 133 */           File fb = event.getPlayerFile("thaumback");
/* 134 */           if (fb.exists()) fb.delete();
/*     */         } catch (IOException e) {}
/*     */       } else {
/* 137 */         File filet = getLegacyPlayerFile(p);
/* 138 */         if (filet.exists()) {
/*     */           try {
/* 140 */             Files.copy(filet, file1);
/* 141 */             Thaumcraft.log.info("Using pre MC 1.7.10 Thaumcraft savefile for " + p.func_70005_c_());
/* 142 */             legacy = true;
/*     */           }
/*     */           catch (IOException e) {}
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 149 */     ResearchManager.loadPlayerData(p, file1, getPlayerFile("thaumback", event.playerDirectory, p.func_70005_c_()), legacy);
/*     */     
/*     */ 
/*     */ 
/* 153 */     Collection<ResearchCategoryList> rc = thaumcraft.api.research.ResearchCategories.researchCategories.values();
/* 154 */     for (ResearchCategoryList cat : rc) {
/* 155 */       Collection<ResearchItem> res = cat.research.values();
/* 156 */       for (ResearchItem ri : res) {
/* 157 */         if (ri.isAutoUnlock()) {
/* 158 */           Thaumcraft.proxy.getResearchManager().completeResearch(p, ri.key);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public File getLegacyPlayerFile(EntityPlayer player)
/*     */   {
/*     */     try
/*     */     {
/* 168 */       File playersDirectory = new File(player.field_70170_p.func_72860_G().func_75765_b(), "players");
/* 169 */       return new File(playersDirectory, player.func_70005_c_() + ".thaum");
/* 170 */     } catch (Exception e) { e.printStackTrace(); }
/* 171 */     return null;
/*     */   }
/*     */   
/*     */   public File getPlayerFile(String suffix, File playerDirectory, String playername)
/*     */   {
/* 176 */     if ("dat".equals(suffix)) throw new IllegalArgumentException("The suffix 'dat' is reserved");
/* 177 */     return new File(playerDirectory, playername + "." + suffix);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void playerSave(PlayerEvent.SaveToFile event)
/*     */   {
/* 183 */     EntityPlayer p = event.entityPlayer;
/*     */     
/* 185 */     ResearchManager.savePlayerData(p, getPlayerFile("thaum", event.playerDirectory, p.func_70005_c_()), getPlayerFile("thaumback", event.playerDirectory, p.func_70005_c_()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void doRepair(ItemStack is, EntityPlayer player)
/*     */   {
/* 192 */     int level = EnchantmentHelper.func_77506_a(Config.enchRepair.field_77352_x, is);
/* 193 */     if (level <= 0) return;
/* 194 */     if (level > 2) {
/* 195 */       level = 2;
/*     */     }
/*     */     
/* 198 */     AspectList cost = thaumcraft.common.lib.crafting.ThaumcraftCraftingManager.getObjectTags(is);
/* 199 */     if ((cost == null) || (cost.size() == 0)) return;
/* 200 */     cost = ResearchManager.reduceToPrimals(cost);
/* 201 */     AspectList finalCost = new AspectList();
/* 202 */     for (Aspect a : cost.getAspects()) {
/* 203 */       if (a != null) {
/* 204 */         finalCost.merge(a, (int)Math.sqrt(cost.getAmount(a) * 2) * level);
/*     */       }
/*     */     }
/* 207 */     if ((is.func_77973_b() instanceof IRepairableExtended)) {
/* 208 */       if ((((IRepairableExtended)is.func_77973_b()).doRepair(is, player, level)) && 
/* 209 */         (WandManager.consumeVisFromInventory(player, finalCost))) {
/* 210 */         is.func_77972_a(-level, player);
/*     */       }
/*     */       
/*     */     }
/* 214 */     else if (WandManager.consumeVisFromInventory(player, finalCost)) {
/* 215 */       is.func_77972_a(-level, player);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void livingTick(LivingEvent.LivingUpdateEvent event)
/*     */   {
/* 224 */     if ((event.entity instanceof EntityPlayer))
/*     */     {
/* 226 */       EntityPlayer player = (EntityPlayer)event.entity;
/*     */       
/* 228 */       if ((event.entity.field_70170_p.field_73011_w.field_76574_g == Config.dimensionOuterId) && (!player.field_71075_bZ.field_75098_d) && (player.field_70173_aa % 20 == 0) && ((player.field_71075_bZ.field_75100_b) || (Hover.getHover(player.func_145782_y()))))
/*     */       {
/*     */ 
/* 231 */         player.field_71075_bZ.field_75100_b = false;
/* 232 */         Hover.setHover(player.func_145782_y(), false);
/* 233 */         player.func_145747_a(new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + StatCollector.func_74838_a("tc.break.fly")));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 238 */       if ((Hover.getHover(player.func_145782_y())) && ((player.field_71071_by.func_70440_f(2) == null) || (!(player.field_71071_by.func_70440_f(2).func_77973_b() instanceof ItemHoverHarness))))
/*     */       {
/* 240 */         Hover.setHover(player.func_145782_y(), false);
/* 241 */         player.field_71075_bZ.field_75100_b = false;
/*     */       }
/*     */       
/* 244 */       if (!event.entity.field_70170_p.field_72995_K)
/*     */       {
/*     */ 
/* 247 */         if ((!Config.wuss) && (player.field_70173_aa > 0) && (player.field_70173_aa % 2000 == 0) && (!player.func_82165_m(Config.potionWarpWardID)))
/*     */         {
/* 249 */           WarpEvents.checkWarpEvent(player);
/*     */         }
/*     */         
/*     */ 
/* 253 */         if ((player.field_70173_aa % 10 == 0) && (player.func_82165_m(Config.potionDeathGazeID))) {
/* 254 */           WarpEvents.checkDeathGaze(player);
/*     */         }
/*     */         
/*     */ 
/* 258 */         if (player.field_70173_aa % 40 == 0)
/*     */         {
/*     */ 
/* 261 */           for (int a = 0; a < InventoryPlayer.func_70451_h(); a++) {
/* 262 */             if (player.field_71071_by.field_70462_a[a] != null) {
/* 263 */               ItemStack is = player.field_71071_by.field_70462_a[a];
/*     */               
/*     */ 
/* 266 */               if ((is.func_77960_j() > 0) && ((is.func_77973_b() instanceof IRepairable)) && (!player.field_71075_bZ.field_75098_d) && (!(is.func_77973_b() instanceof ItemHoverHarness)))
/*     */               {
/*     */ 
/* 269 */                 doRepair(is, player);
/*     */               }
/*     */             }
/*     */           }
/* 273 */           for (int a = 0; a < 4; a++) {
/* 274 */             if (player.field_71071_by.func_70440_f(a) != null) {
/* 275 */               ItemStack is = player.field_71071_by.func_70440_f(a);
/*     */               
/*     */ 
/* 278 */               if ((is.func_77960_j() > 0) && ((is.func_77973_b() instanceof IRepairable)) && (!player.field_71075_bZ.field_75098_d))
/*     */               {
/* 280 */                 doRepair(is, player);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 286 */       updateSpeed(player);
/*     */       
/* 288 */       if ((player.field_70170_p.field_72995_K) && ((player.func_70093_af()) || (player.field_71071_by.func_70440_f(0) == null) || (player.field_71071_by.func_70440_f(0).func_77973_b() != ConfigItems.itemBootsTraveller)) && (this.prevStep.containsKey(Integer.valueOf(player.func_145782_y()))))
/*     */       {
/*     */ 
/*     */ 
/* 292 */         player.field_70138_W = ((Float)this.prevStep.get(Integer.valueOf(player.func_145782_y()))).floatValue();
/* 293 */         this.prevStep.remove(Integer.valueOf(player.func_145782_y()));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 298 */     if (((event.entity instanceof EntityMob)) && (!event.entity.field_70128_L)) {
/* 299 */       EntityMob mob = (EntityMob)event.entity;
/* 300 */       int t = (int)mob.func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
/* 301 */       if ((t >= 0) && (ChampionModifier.mods[t].type == 0)) {
/* 302 */         ChampionModifier.mods[t].effect.performEffect(mob, null, null, 0.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 308 */   public HashMap<Integer, Float> prevStep = new HashMap();
/*     */   
/*     */   private void updateSpeed(EntityPlayer player)
/*     */   {
/*     */     try {
/* 313 */       if ((!player.field_71075_bZ.field_75100_b) && (player.field_71071_by.func_70440_f(0) != null) && (player.field_70701_bs > 0.0F))
/*     */       {
/*     */ 
/*     */ 
/* 317 */         int haste = EnchantmentHelper.func_77506_a(Config.enchHaste.field_77352_x, player.field_71071_by.func_70440_f(0));
/* 318 */         if (haste > 0) {
/* 319 */           float bonus = haste * 0.015F;
/* 320 */           if (player.field_70160_al) bonus /= 2.0F;
/* 321 */           if (player.func_70090_H()) bonus /= 2.0F;
/* 322 */           player.func_70060_a(0.0F, 1.0F, bonus);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void playerJumps(LivingEvent.LivingJumpEvent event)
/*     */   {
/* 332 */     if (((event.entity instanceof EntityPlayer)) && (((EntityPlayer)event.entity).field_71071_by.func_70440_f(0) != null) && (((EntityPlayer)event.entity).field_71071_by.func_70440_f(0).func_77973_b() == ConfigItems.itemBootsTraveller))
/*     */     {
/*     */ 
/* 335 */       event.entityLiving.field_70181_x += 0.2750000059604645D;
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void playerInteract(EntityInteractEvent event)
/*     */   {
/* 342 */     if (((event.target instanceof EntityGolemBase)) && (((EntityGolemBase)event.target).getOwnerName().length() > 0) && (!((EntityGolemBase)event.target).getOwnerName().equals(event.entityPlayer.func_70005_c_())))
/*     */     {
/* 344 */       if (!event.entityPlayer.field_70170_p.field_72995_K) event.entityPlayer.func_145747_a(new ChatComponentTranslation("You are not my Master!", new Object[0]));
/* 345 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 350 */   public static HashMap<String, ArrayList<WeakReference<Entity>>> linkedEntities = new HashMap();
/*     */   
/*     */   @SubscribeEvent
/*     */   public void entitySpawns(EntityJoinWorldEvent event) {
/* 354 */     if (!event.world.field_72995_K) {
/* 355 */       if ((event.entity instanceof EntityEnderPearl)) {
/* 356 */         int x = MathHelper.func_76128_c(event.entity.field_70165_t);
/* 357 */         int y = MathHelper.func_76128_c(event.entity.field_70163_u);
/* 358 */         int z = MathHelper.func_76128_c(event.entity.field_70161_v);
/*     */         
/* 360 */         for (int xx = -5; xx <= 5; xx++) for (int yy = -5; yy <= 5; yy++) for (int zz = -5; zz <= 5; zz++) {
/* 361 */               TileEntity tile = event.world.func_147438_o(x + xx, y + yy, z + zz);
/* 362 */               if ((tile != null) && ((tile instanceof thaumcraft.common.tiles.TileOwned))) {
/* 363 */                 if ((((EntityEnderPearl)event.entity).func_85052_h() instanceof EntityPlayer)) {
/* 364 */                   ((EntityPlayer)((EntityEnderPearl)event.entity).func_85052_h()).func_145747_a(new ChatComponentText("§5§oThe magic of a nearby warded object destroys the ender pearl."));
/*     */                 }
/* 366 */                 event.entity.func_70106_y();
/*     */                 break label189;
/*     */               }
/*     */             }
/*     */       }
/*     */       label189:
/* 372 */       if ((event.entity instanceof EntityPlayer)) {
/* 373 */         ArrayList<WeakReference<Entity>> dudes = (ArrayList)linkedEntities.get(event.entity.func_70005_c_());
/* 374 */         if (dudes != null) {
/* 375 */           for (WeakReference<Entity> dude : dudes) {
/* 376 */             if ((dude.get() != null) && (((Entity)dude.get()).field_71088_bW == 0)) {
/* 377 */               ((Entity)dude.get()).field_71088_bW = ((Entity)dude.get()).func_82147_ab();
/* 378 */               ((Entity)dude.get()).func_71027_c(event.world.field_73011_w.field_76574_g);
/*     */             }
/*     */             
/*     */           }
/*     */         }
/*     */       }
/* 384 */       else if ((event.entity instanceof EntityMob)) {
/* 385 */         EntityMob mob = (EntityMob)event.entity;
/*     */         
/* 387 */         if (mob.func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e() < -1.0D)
/*     */         {
/* 389 */           int c = event.world.field_73012_v.nextInt(100);
/*     */           
/* 391 */           if ((event.world.field_73013_u == EnumDifficulty.EASY) || (!Config.championMobs)) c += 2;
/* 392 */           if (event.world.field_73013_u == EnumDifficulty.HARD) c -= (Config.championMobs ? 2 : 0);
/* 393 */           if (event.world.field_73011_w.field_76574_g == Config.dimensionOuterId) c -= 3;
/* 394 */           BiomeGenBase bg = mob.field_70170_p.func_72807_a(MathHelper.func_76143_f(mob.field_70165_t), MathHelper.func_76143_f(mob.field_70161_v));
/* 395 */           if ((BiomeDictionary.isBiomeOfType(bg, BiomeDictionary.Type.SPOOKY)) || (BiomeDictionary.isBiomeOfType(bg, BiomeDictionary.Type.NETHER)) || (BiomeDictionary.isBiomeOfType(bg, BiomeDictionary.Type.END)))
/*     */           {
/* 397 */             c -= (Config.championMobs ? 2 : 1);
/*     */           }
/* 399 */           if (isDangerousLocation(mob.field_70170_p, MathHelper.func_76143_f(mob.field_70165_t), MathHelper.func_76143_f(mob.field_70163_u), MathHelper.func_76143_f(mob.field_70161_v)))
/*     */           {
/*     */ 
/*     */ 
/* 403 */             c -= (Config.championMobs ? 10 : 3);
/*     */           }
/*     */           
/* 406 */           int cc = 0;
/* 407 */           boolean whitelisted = false;
/* 408 */           for (Class clazz : ConfigEntities.championModWhitelist.keySet()) {
/* 409 */             if (clazz.isAssignableFrom(event.entity.getClass())) {
/* 410 */               whitelisted = true;
/* 411 */               if ((Config.championMobs) || ((event.entity instanceof EntityThaumcraftBoss))) {
/* 412 */                 cc = Math.max(cc, ((Integer)ConfigEntities.championModWhitelist.get(clazz)).intValue() - 1);
/*     */               }
/*     */             }
/*     */           }
/* 416 */           c -= cc;
/*     */           
/* 418 */           if ((whitelisted) && (c <= 0) && (mob.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() >= 10.0D)) {
/* 419 */             EntityUtils.makeChampion(mob, false);
/*     */           } else {
/* 421 */             IAttributeInstance modai = mob.func_110148_a(EntityUtils.CHAMPION_MOD);
/* 422 */             modai.func_111124_b(ChampionModifier.ATTRIBUTE_MOD_NONE);
/* 423 */             modai.func_111121_a(ChampionModifier.ATTRIBUTE_MOD_NONE);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isDangerousLocation(World world, int x, int y, int z) {
/* 431 */     if (world.field_73011_w.field_76574_g == Config.dimensionOuterId) {
/* 432 */       int xx = x >> 4;
/* 433 */       int zz = z >> 4;
/* 434 */       Cell c = thaumcraft.common.lib.world.dim.MazeHandler.getFromHashMap(new CellLoc(xx, zz));
/* 435 */       if ((c != null) && ((c.feature == 6) || (c.feature == 8))) {
/* 436 */         return true;
/*     */       }
/*     */     }
/* 439 */     return false;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void entityConstuct(EntityEvent.EntityConstructing event) {
/* 444 */     if ((event.entity instanceof EntityMob)) {
/* 445 */       EntityMob mob = (EntityMob)event.entity;
/* 446 */       mob.func_110140_aT().func_111150_b(EntityUtils.CHAMPION_MOD).func_111128_a(-2.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void itemPickup(EntityItemPickupEvent event) {
/* 452 */     if (event.entityPlayer.func_70005_c_().startsWith("FakeThaumcraft")) {
/* 453 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void livingDrops(LivingDropsEvent event) {
/* 459 */     boolean fakeplayer = (event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof net.minecraftforge.common.util.FakePlayer));
/*     */     
/*     */ 
/* 462 */     if ((!event.entity.field_70170_p.field_72995_K) && (event.recentlyHit) && (!fakeplayer) && ((event.entity instanceof EntityMob)) && (!(event.entity instanceof EntityThaumcraftBoss)) && (((EntityMob)event.entity).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e() >= 0.0D))
/*     */     {
/*     */ 
/*     */ 
/* 466 */       int i = 5 + event.entity.field_70170_p.field_73012_v.nextInt(3);
/* 467 */       while (i > 0)
/*     */       {
/* 469 */         int j = EntityXPOrb.func_70527_a(i);
/* 470 */         i -= j;
/* 471 */         event.entity.field_70170_p.func_72838_d(new EntityXPOrb(event.entity.field_70170_p, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, j));
/*     */       }
/*     */       
/* 474 */       int lb = Math.min(2, MathHelper.func_76141_d((event.entity.field_70170_p.field_73012_v.nextInt(9) + event.lootingLevel) / 5.0F));
/* 475 */       event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, new ItemStack(ConfigItems.itemLootbag, 1, lb)));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 484 */     if (((event.entityLiving instanceof net.minecraft.entity.monster.EntityZombie)) && (!(event.entityLiving instanceof thaumcraft.common.entities.monster.EntityBrainyZombie)))
/*     */     {
/* 486 */       if ((event.recentlyHit) && (event.entity.field_70170_p.field_73012_v.nextInt(10) - event.lootingLevel < 1)) {
/* 487 */         event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, new ItemStack(ConfigItems.itemZombieBrain)));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 495 */     if (((event.entityLiving instanceof EntityVillager)) && (event.entity.field_70170_p.field_73012_v.nextInt(10) - event.lootingLevel < 1))
/*     */     {
/* 497 */       event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, new ItemStack(ConfigItems.itemResource, 1, 18)));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 505 */     if (event.source == DamageSourceThaumcraft.dissolve) {
/* 506 */       AspectList aspects = ScanManager.generateEntityAspects(event.entityLiving);
/* 507 */       if ((aspects != null) && (aspects.size() > 0)) {
/* 508 */         for (Aspect aspect : aspects.getAspects()) {
/* 509 */           if (!event.entity.field_70170_p.field_73012_v.nextBoolean()) {
/* 510 */             int size = 1 + event.entity.field_70170_p.field_73012_v.nextInt(aspects.getAmount(aspect));
/* 511 */             size = Math.max(1, size / 2);
/* 512 */             ItemStack stack = new ItemStack(ConfigItems.itemCrystalEssence, size, 0);
/* 513 */             ((ItemCrystalEssence)stack.func_77973_b()).setAspects(stack, new AspectList().add(aspect, 1));
/* 514 */             event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, stack));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void livingTick(LivingDeathEvent event)
/*     */   {
/* 528 */     if ((!event.entityLiving.field_70170_p.field_72995_K) && (!(event.entityLiving instanceof ITaintedMob)) && (event.entityLiving.func_82165_m(Config.potionTaintPoisonID)))
/*     */     {
/*     */ 
/*     */ 
/* 532 */       Entity entity = null;
/*     */       
/* 534 */       if ((event.entityLiving instanceof EntityCreeper)) {
/* 535 */         entity = new thaumcraft.common.entities.monster.EntityTaintCreeper(event.entityLiving.field_70170_p);
/*     */       }
/* 537 */       else if ((event.entityLiving instanceof EntitySheep)) {
/* 538 */         entity = new EntityTaintSheep(event.entityLiving.field_70170_p);
/*     */       }
/* 540 */       else if ((event.entityLiving instanceof EntityCow)) {
/* 541 */         entity = new EntityTaintCow(event.entityLiving.field_70170_p);
/*     */       }
/* 543 */       else if ((event.entityLiving instanceof EntityPig)) {
/* 544 */         entity = new thaumcraft.common.entities.monster.EntityTaintPig(event.entityLiving.field_70170_p);
/*     */       }
/* 546 */       else if ((event.entityLiving instanceof EntityChicken)) {
/* 547 */         entity = new thaumcraft.common.entities.monster.EntityTaintChicken(event.entityLiving.field_70170_p);
/*     */       }
/* 549 */       else if ((event.entityLiving instanceof EntityVillager)) {
/* 550 */         entity = new thaumcraft.common.entities.monster.EntityTaintVillager(event.entityLiving.field_70170_p);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 556 */         entity = new EntityThaumicSlime(event.entityLiving.field_70170_p);
/* 557 */         if (entity != null) { ((EntityThaumicSlime)entity).setSlimeSize((int)(1.0F + Math.min(event.entityLiving.func_110138_aP() / 10.0F, 6.0F)));
/*     */         }
/*     */       }
/* 560 */       if (entity != null)
/*     */       {
/* 562 */         entity.func_70012_b(event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, event.entityLiving.field_70177_z, 0.0F);
/* 563 */         event.entityLiving.field_70170_p.func_72838_d(entity);
/* 564 */         event.entityLiving.func_70106_y();
/*     */       }
/*     */     }
/* 567 */     else if ((!event.entityLiving.field_70170_p.field_72995_K) && (EntityUtils.getRecentlyHit(event.entityLiving) > 0))
/*     */     {
/* 569 */       AspectList aspectsCompound = ScanManager.generateEntityAspects(event.entityLiving);
/* 570 */       if ((aspectsCompound != null) && (aspectsCompound.size() > 0)) {
/* 571 */         AspectList aspects = ResearchManager.reduceToPrimals(aspectsCompound);
/* 572 */         for (Aspect aspect : aspects.getAspects()) {
/* 573 */           if (event.entityLiving.field_70170_p.field_73012_v.nextBoolean()) {
/* 574 */             EntityAspectOrb orb = new EntityAspectOrb(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, aspect, 1 + event.entityLiving.field_70170_p.field_73012_v.nextInt(aspects.getAmount(aspect)));
/*     */             
/*     */ 
/* 577 */             event.entityLiving.field_70170_p.func_72838_d(orb);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void bowNocked(ArrowNockEvent event)
/*     */   {
/* 589 */     if (event.entityPlayer.field_71071_by.func_146028_b(ConfigItems.itemPrimalArrow))
/*     */     {
/* 591 */       event.entityPlayer.func_71008_a(event.result, event.result.func_77973_b().func_77626_a(event.result));
/*     */       
/* 593 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void bowShot(ArrowLooseEvent event) {
/* 599 */     if (event.entityPlayer.field_71071_by.func_146028_b(ConfigItems.itemPrimalArrow))
/*     */     {
/* 601 */       float f = 0.0F;
/* 602 */       float dam = 2.0F;
/*     */       
/* 604 */       if ((event.bow.func_77973_b() instanceof ItemBowBone)) {
/* 605 */         f = event.charge / 10.0F;
/* 606 */         f = (f * f + f * 2.0F) / 3.0F;
/*     */         
/* 608 */         if (f < 0.1D)
/*     */         {
/* 610 */           return;
/*     */         }
/* 612 */         dam = 2.5F;
/*     */       } else {
/* 614 */         f = event.charge / 20.0F;
/* 615 */         f = (f * f + f * 2.0F) / 3.0F;
/*     */         
/* 617 */         if (f < 0.1D)
/*     */         {
/* 619 */           return;
/*     */         }
/*     */       }
/*     */       
/* 623 */       if (f > 1.0F)
/*     */       {
/* 625 */         f = 1.0F;
/*     */       }
/*     */       
/* 628 */       int type = 0;
/*     */       
/* 630 */       for (int j = 0; j < event.entityPlayer.field_71071_by.field_70462_a.length; j++)
/*     */       {
/* 632 */         if ((event.entityPlayer.field_71071_by.field_70462_a[j] != null) && (event.entityPlayer.field_71071_by.field_70462_a[j].func_77973_b() == ConfigItems.itemPrimalArrow))
/*     */         {
/* 634 */           type = event.entityPlayer.field_71071_by.field_70462_a[j].func_77960_j();
/* 635 */           break;
/*     */         }
/*     */       }
/*     */       
/* 639 */       EntityPrimalArrow entityarrow = new EntityPrimalArrow(event.entityPlayer.field_70170_p, event.entityPlayer, f * dam, type);
/*     */       
/* 641 */       if ((event.bow.func_77973_b() instanceof ItemBowBone)) {
/* 642 */         entityarrow.func_70239_b(entityarrow.func_70242_d() + 0.5D);
/*     */       }
/* 644 */       else if (f == 1.0F)
/*     */       {
/* 646 */         entityarrow.func_70243_d(true);
/*     */       }
/*     */       
/*     */ 
/* 650 */       int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, event.bow);
/*     */       
/* 652 */       if (k > 0)
/*     */       {
/* 654 */         entityarrow.func_70239_b(entityarrow.func_70242_d() + k * 0.5D + 0.5D);
/*     */       }
/*     */       
/* 657 */       int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, event.bow);
/* 658 */       if (type == 3) l++;
/* 659 */       if (l > 0)
/*     */       {
/* 661 */         entityarrow.func_70240_a(l);
/*     */       }
/*     */       
/* 664 */       if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, event.bow) > 0)
/*     */       {
/* 666 */         entityarrow.func_70015_d(100);
/*     */       }
/*     */       
/* 669 */       event.bow.func_77972_a(1, event.entityPlayer);
/* 670 */       event.entityPlayer.field_70170_p.func_72956_a(event.entityPlayer, "random.bow", 1.0F, 1.0F / (event.entityPlayer.field_70170_p.field_73012_v.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/*     */       
/* 672 */       boolean flag = false;
/* 673 */       if ((EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, event.bow) > 0) && (event.entityPlayer.field_70170_p.field_73012_v.nextFloat() < 0.33F))
/*     */       {
/* 675 */         flag = true;
/*     */       }
/* 677 */       if ((!event.entityPlayer.field_71075_bZ.field_75098_d) || (!flag)) {
/* 678 */         InventoryUtils.consumeInventoryItem(event.entityPlayer, ConfigItems.itemPrimalArrow, type);
/*     */       }
/*     */       
/* 681 */       if (!event.entityPlayer.field_70170_p.field_72995_K)
/*     */       {
/* 683 */         event.entityPlayer.field_70170_p.func_72838_d(entityarrow);
/*     */       }
/*     */       
/* 686 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void finishedUsingItem(PlayerUseItemEvent.Finish event)
/*     */   {
/* 694 */     if ((!event.entity.field_70170_p.field_72995_K) && 
/* 695 */       (event.entityPlayer.func_82165_m(Config.potionUnHungerID))) {
/* 696 */       if ((event.item.func_77969_a(new ItemStack(Items.field_151078_bh))) || (event.item.func_77969_a(new ItemStack(ConfigItems.itemZombieBrain))))
/*     */       {
/* 698 */         PotionEffect pe = event.entityPlayer.func_70660_b(net.minecraft.potion.Potion.field_76425_a[Config.potionUnHungerID]);
/*     */         
/* 700 */         int amp = pe.func_76458_c() - 1;
/* 701 */         int duration = pe.func_76459_b() - 600;
/*     */         
/* 703 */         event.entityPlayer.func_82170_o(Config.potionUnHungerID);
/*     */         
/* 705 */         if ((duration > 0) && (amp >= 0)) {
/* 706 */           pe = new PotionEffect(Config.potionUnHungerID, duration, amp, true);
/* 707 */           pe.getCurativeItems().clear();
/* 708 */           pe.addCurativeItem(new ItemStack(Items.field_151078_bh));
/* 709 */           event.entityPlayer.func_70690_d(pe);
/*     */         }
/*     */         
/* 712 */         event.entityPlayer.func_145747_a(new ChatComponentText("§2§o" + StatCollector.func_74838_a("warp.text.hunger.2")));
/*     */ 
/*     */ 
/*     */       }
/* 716 */       else if ((event.item.func_77973_b() instanceof ItemFood)) {
/* 717 */         event.entityPlayer.func_145747_a(new ChatComponentText("§4§o" + StatCollector.func_74838_a("warp.text.hunger.1")));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void itemExpire(ItemExpireEvent event)
/*     */   {
/* 728 */     if ((event.entityItem.func_92059_d() != null) && (event.entityItem.func_92059_d().func_77973_b() != null) && ((event.entityItem.func_92059_d().func_77973_b() instanceof ItemBathSalts)))
/*     */     {
/*     */ 
/* 731 */       int x = MathHelper.func_76128_c(event.entityItem.field_70165_t);
/* 732 */       int y = MathHelper.func_76128_c(event.entityItem.field_70163_u);
/* 733 */       int z = MathHelper.func_76128_c(event.entityItem.field_70161_v);
/* 734 */       if ((event.entityItem.field_70170_p.func_147439_a(x, y, z) == net.minecraft.init.Blocks.field_150355_j) && (event.entityItem.field_70170_p.func_72805_g(x, y, z) == 0))
/*     */       {
/* 736 */         event.entityItem.field_70170_p.func_147449_b(x, y, z, thaumcraft.common.config.ConfigBlocks.blockFluidPure);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void breakSpeedEvent(PlayerEvent.BreakSpeed event) {
/* 743 */     if ((!event.entityPlayer.field_70122_E) && (Hover.getHover(event.entityPlayer.func_145782_y()))) {
/* 744 */       event.newSpeed = (event.originalSpeed * 5.0F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/events/EventHandlerEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */