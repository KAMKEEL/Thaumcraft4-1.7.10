/*     */ package thaumcraft.common.lib;
/*     */ 
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.entities.monster.EntityMindSpider;
/*     */ import thaumcraft.common.lib.events.EventHandlerRunic;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketMiscEvent;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ 
/*     */ public class WarpEvents
/*     */ {
/*     */   public static void checkWarpEvent(EntityPlayer player)
/*     */   {
/*  40 */     int warp = Thaumcraft.proxy.getPlayerKnowledge().getWarpTotal(player.func_70005_c_());
/*  41 */     int actualwarp = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.func_70005_c_()) + Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_());
/*     */     
/*     */ 
/*  44 */     warp += getWarpFromGear(player);
/*     */     
/*  46 */     int warpCounter = Thaumcraft.proxy.getPlayerKnowledge().getWarpCounter(player.func_70005_c_());
/*     */     
/*  48 */     int r = player.field_70170_p.field_73012_v.nextInt(100);
/*     */     
/*  50 */     if ((warpCounter > 0) && (warp > 0) && (r <= Math.sqrt(warpCounter)))
/*     */     {
/*  52 */       warp = Math.min(100, (warp + warp + warpCounter) / 3);
/*     */       
/*  54 */       warpCounter = (int)(warpCounter - Math.max(5.0D, Math.sqrt(warpCounter) * 2.0D));
/*  55 */       Thaumcraft.proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), warpCounter);
/*     */       
/*  57 */       int eff = player.field_70170_p.field_73012_v.nextInt(warp);
/*     */       
/*     */ 
/*  60 */       ItemStack helm = player.field_71071_by.field_70460_b[3];
/*  61 */       if ((helm != null) && ((helm.func_77973_b() instanceof thaumcraft.common.items.armor.ItemFortressArmor)) && 
/*  62 */         (helm.func_77942_o()) && (helm.field_77990_d.func_74764_b("mask")) && (helm.field_77990_d.func_74762_e("mask") == 0))
/*     */       {
/*     */ 
/*  65 */         eff -= 2 + player.field_70170_p.field_73012_v.nextInt(4);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  70 */       PacketHandler.INSTANCE.sendTo(new PacketMiscEvent((short)0), (EntityPlayerMP)player);
/*     */       
/*     */ 
/*     */ 
/*  74 */       if (eff > 0)
/*     */       {
/*     */ 
/*     */ 
/*  78 */         if (eff <= 4) {
/*  79 */           grantResearch(player, 1);
/*  80 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.3")));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/*  85 */         else if (eff > 8)
/*     */         {
/*     */ 
/*     */ 
/*  89 */           if (eff <= 12) {
/*  90 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.11")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/*  95 */           else if (eff <= 16) {
/*  96 */             PotionEffect pe = new PotionEffect(Config.potionVisExhaustID, 5000, Math.min(3, warp / 15), true);
/*  97 */             pe.getCurativeItems().clear();
/*     */             try {
/*  99 */               player.func_70690_d(pe);
/*     */             } catch (Exception e) {
/* 101 */               e.printStackTrace();
/*     */             }
/*     */             
/* 104 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.1")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 109 */           else if (eff <= 20) {
/* 110 */             PotionEffect pe = new PotionEffect(Config.potionThaumarhiaID, Math.min(32000, 10 * warp), 0, true);
/* 111 */             pe.getCurativeItems().clear();
/*     */             try {
/* 113 */               player.func_70690_d(pe);
/*     */             } catch (Exception e) {
/* 115 */               e.printStackTrace();
/*     */             }
/*     */             
/* 118 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.15")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 123 */           else if (eff <= 24) {
/* 124 */             PotionEffect pe = new PotionEffect(Config.potionUnHungerID, 5000, Math.min(3, warp / 15), true);
/* 125 */             pe.getCurativeItems().clear();
/* 126 */             pe.addCurativeItem(new ItemStack(Items.field_151078_bh));
/* 127 */             pe.addCurativeItem(new ItemStack(thaumcraft.common.config.ConfigItems.itemZombieBrain));
/*     */             try {
/* 129 */               player.func_70690_d(pe);
/*     */             } catch (Exception e) {
/* 131 */               e.printStackTrace();
/*     */             }
/*     */             
/* 134 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.2")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 139 */           else if (eff <= 28) {
/* 140 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.12")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 145 */           else if (eff <= 32) {
/* 146 */             spawnMist(player, warp, 1);
/*     */ 
/*     */           }
/* 149 */           else if (eff <= 36) {
/*     */             try {
/* 151 */               player.func_70690_d(new PotionEffect(Config.potionBlurredID, Math.min(32000, 10 * warp), 0, true));
/*     */             } catch (Exception e) {
/* 153 */               e.printStackTrace();
/*     */             }
/*     */             
/*     */           }
/* 157 */           else if (eff <= 40) {
/* 158 */             PotionEffect pe = new PotionEffect(Config.potionSunScornedID, 5000, Math.min(3, warp / 15), true);
/* 159 */             pe.getCurativeItems().clear();
/*     */             try {
/* 161 */               player.func_70690_d(pe);
/*     */             } catch (Exception e) {
/* 163 */               e.printStackTrace();
/*     */             }
/*     */             
/* 166 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.5")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 171 */           else if (eff <= 44) {
/*     */             try {
/* 173 */               player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, 1200, Math.min(3, warp / 15), true));
/*     */             } catch (Exception e) {
/* 175 */               e.printStackTrace();
/*     */             }
/* 177 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.9")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 182 */           else if (eff <= 48) {
/* 183 */             PotionEffect pe = new PotionEffect(Config.potionInfVisExhaustID, 6000, Math.min(3, warp / 15), false);
/* 184 */             pe.getCurativeItems().clear();
/*     */             try {
/* 186 */               player.func_70690_d(pe);
/*     */             } catch (Exception e) {
/* 188 */               e.printStackTrace();
/*     */             }
/*     */             
/* 191 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.1")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 196 */           else if (eff <= 52) {
/* 197 */             player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, Math.min(40 * warp, 6000), 0, true));
/* 198 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.10")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 203 */           else if (eff <= 56) {
/* 204 */             PotionEffect pe = new PotionEffect(Config.potionDeathGazeID, 6000, Math.min(3, warp / 15), true);
/* 205 */             pe.getCurativeItems().clear();
/*     */             try {
/* 207 */               player.func_70690_d(pe);
/*     */             }
/*     */             catch (Exception e) {
/* 210 */               e.printStackTrace();
/*     */             }
/*     */             
/* 213 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.4")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 218 */           else if (eff <= 60) {
/* 219 */             suddenlySpiders(player, warp, false);
/*     */ 
/*     */           }
/* 222 */           else if (eff <= 64) {
/* 223 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.13")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 228 */           else if (eff <= 68) {
/* 229 */             spawnMist(player, warp, warp / 30);
/*     */ 
/*     */           }
/* 232 */           else if (eff <= 72) {
/*     */             try {
/* 234 */               player.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, Math.min(32000, 5 * warp), 0, true));
/*     */             } catch (Exception e) {
/* 236 */               e.printStackTrace();
/*     */             }
/*     */             
/*     */           }
/* 240 */           else if (eff == 76) {
/* 241 */             if (Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_()) > 0) {
/* 242 */               Thaumcraft.proxy.getPlayerKnowledge().addWarpSticky(player.func_70005_c_(), -1);
/* 243 */               PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)1), (EntityPlayerMP)player);
/* 244 */               PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketWarpMessage(player, (byte)1, -1), (EntityPlayerMP)player);
/*     */             }
/* 246 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.14")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 251 */           else if (eff <= 80) {
/* 252 */             PotionEffect pe = new PotionEffect(Config.potionUnHungerID, 6000, Math.min(3, warp / 15), true);
/* 253 */             pe.getCurativeItems().clear();
/* 254 */             pe.addCurativeItem(new ItemStack(Items.field_151078_bh));
/* 255 */             pe.addCurativeItem(new ItemStack(thaumcraft.common.config.ConfigItems.itemZombieBrain));
/*     */             try {
/* 257 */               player.func_70690_d(pe);
/*     */             } catch (Exception e) {
/* 259 */               e.printStackTrace();
/*     */             }
/*     */             
/* 262 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.2")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 267 */           else if (eff <= 84) {
/* 268 */             grantResearch(player, warp / 10);
/* 269 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.3")));
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/* 274 */           else if (eff > 88)
/*     */           {
/*     */ 
/*     */ 
/* 278 */             if (eff <= 92) {
/* 279 */               suddenlySpiders(player, warp, true);
/*     */             } else {
/* 281 */               spawnMist(player, warp, warp / 15);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 286 */       if (actualwarp > 10)
/*     */       {
/* 288 */         if ((!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "BATHSALTS")) && (!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "@BATHSALTS")))
/*     */         {
/* 290 */           player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.8")));
/* 291 */           PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("@BATHSALTS"), (EntityPlayerMP)player);
/* 292 */           Thaumcraft.proxy.getResearchManager().completeResearch(player, "@BATHSALTS");
/*     */         }
/*     */       }
/* 295 */       if (actualwarp > 25)
/*     */       {
/* 297 */         if (!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "ELDRITCHMINOR")) {
/* 298 */           grantResearch(player, 10);
/* 299 */           PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("ELDRITCHMINOR"), (EntityPlayerMP)player);
/* 300 */           Thaumcraft.proxy.getResearchManager().completeResearch(player, "ELDRITCHMINOR");
/*     */         }
/*     */       }
/*     */       
/* 304 */       if (actualwarp > 50)
/*     */       {
/* 306 */         if (!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "ELDRITCHMAJOR")) {
/* 307 */           grantResearch(player, 20);
/* 308 */           PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("ELDRITCHMAJOR"), (EntityPlayerMP)player);
/* 309 */           Thaumcraft.proxy.getResearchManager().completeResearch(player, "ELDRITCHMAJOR");
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 314 */     Thaumcraft.proxy.getPlayerKnowledge().addWarpTemp(player.func_70005_c_(), -1);
/* 315 */     PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)2), (EntityPlayerMP)player);
/*     */   }
/*     */   
/*     */   private static void spawnMist(EntityPlayer player, int warp, int guardian) {
/* 319 */     PacketHandler.INSTANCE.sendTo(new PacketMiscEvent((short)1), (EntityPlayerMP)player);
/*     */     
/*     */ 
/*     */ 
/* 323 */     if (guardian > 0) {
/* 324 */       guardian = Math.min(8, guardian);
/* 325 */       for (int a = 0; a < guardian; a++) {
/* 326 */         spawnGuardian(player);
/*     */       }
/*     */     }
/* 329 */     player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.6")));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void grantResearch(EntityPlayer player, int times)
/*     */   {
/* 336 */     int amt = 1 + player.field_70170_p.field_73012_v.nextInt(times);
/* 337 */     for (int a = 0; a < amt; a++) {
/* 338 */       Aspect aspect = (Aspect)Aspect.getPrimalAspects().get(player.field_70170_p.field_73012_v.nextInt(6));
/* 339 */       Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), aspect, (short)1);
/* 340 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketAspectPool(aspect.getTag(), Short.valueOf((short)1), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), aspect))), (EntityPlayerMP)player);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 345 */     ResearchManager.scheduleSave(player);
/*     */   }
/*     */   
/*     */   private static void spawnGuardian(EntityPlayer player)
/*     */   {
/* 350 */     EntityEldritchGuardian eg = new EntityEldritchGuardian(player.field_70170_p);
/* 351 */     int i = MathHelper.func_76128_c(player.field_70165_t);
/* 352 */     int j = MathHelper.func_76128_c(player.field_70163_u);
/* 353 */     int k = MathHelper.func_76128_c(player.field_70161_v);
/* 354 */     for (int l = 0; l < 50; l++)
/*     */     {
/* 356 */       int i1 = i + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 357 */       int j1 = j + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 358 */       int k1 = k + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/*     */       
/* 360 */       if (World.func_147466_a(player.field_70170_p, i1, j1 - 1, k1))
/*     */       {
/* 362 */         eg.func_70107_b(i1, j1, k1);
/*     */         
/* 364 */         if ((player.field_70170_p.func_72855_b(eg.field_70121_D)) && (player.field_70170_p.func_72945_a(eg, eg.field_70121_D).isEmpty()) && (!player.field_70170_p.func_72953_d(eg.field_70121_D)))
/*     */         {
/* 366 */           eg.func_70784_b(player);
/* 367 */           eg.func_70624_b(player);
/* 368 */           player.field_70170_p.func_72838_d(eg);
/* 369 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static void suddenlySpiders(EntityPlayer player, int warp, boolean real)
/*     */   {
/* 377 */     int spawns = Math.min(50, warp);
/*     */     
/* 379 */     for (int a = 0; a < spawns; a++)
/*     */     {
/* 381 */       EntityMindSpider spider = new EntityMindSpider(player.field_70170_p);
/* 382 */       int i = MathHelper.func_76128_c(player.field_70165_t);
/* 383 */       int j = MathHelper.func_76128_c(player.field_70163_u);
/* 384 */       int k = MathHelper.func_76128_c(player.field_70161_v);
/* 385 */       boolean success = false;
/* 386 */       for (int l = 0; l < 50; l++)
/*     */       {
/* 388 */         int i1 = i + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 389 */         int j1 = j + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 390 */         int k1 = k + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/*     */         
/* 392 */         if (World.func_147466_a(player.field_70170_p, i1, j1 - 1, k1))
/*     */         {
/* 394 */           spider.func_70107_b(i1, j1, k1);
/*     */           
/* 396 */           if ((player.field_70170_p.func_72855_b(spider.field_70121_D)) && (player.field_70170_p.func_72945_a(spider, spider.field_70121_D).isEmpty()) && (!player.field_70170_p.func_72953_d(spider.field_70121_D)))
/*     */           {
/* 398 */             success = true;
/* 399 */             break;
/*     */           }
/*     */         }
/*     */       }
/* 403 */       if (success)
/*     */       {
/* 405 */         spider.func_70784_b(player);
/* 406 */         spider.func_70624_b(player);
/* 407 */         if (!real) {
/* 408 */           spider.setViewer(player.func_70005_c_());
/* 409 */           spider.setHarmless(true);
/*     */         }
/*     */         
/* 412 */         player.field_70170_p.func_72838_d(spider);
/*     */       }
/*     */     }
/*     */     
/* 416 */     player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("warp.text.7")));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void checkDeathGaze(EntityPlayer player)
/*     */   {
/* 423 */     PotionEffect pe = player.func_70660_b(Potion.field_76425_a[Config.potionDeathGazeID]);
/* 424 */     if (pe == null) return;
/* 425 */     int level = pe.func_76458_c();
/* 426 */     int range = Math.min(8 + level * 3, 24);
/* 427 */     List list = player.field_70170_p.func_72839_b(player, player.field_70121_D.func_72314_b(range, range, range));
/*     */     
/*     */ 
/* 430 */     for (int i = 0; i < list.size(); i++)
/*     */     {
/* 432 */       Entity entity = (Entity)list.get(i);
/* 433 */       if ((entity.func_70067_L()) && ((entity instanceof EntityLivingBase)) && (((EntityLivingBase)entity).func_70089_S()))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 440 */         if (thaumcraft.common.lib.utils.EntityUtils.isVisibleTo(0.75F, player, entity, range))
/*     */         {
/* 442 */           if ((entity != null) && (player.func_70685_l(entity)) && 
/* 443 */             ((!(entity instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W())) && 
/* 444 */             (!((EntityLivingBase)entity).func_82165_m(Potion.field_82731_v.func_76396_c()))) {
/* 445 */             ((EntityLivingBase)entity).func_70604_c(player);
/* 446 */             ((EntityLivingBase)entity).func_130011_c(player);
/* 447 */             if ((entity instanceof EntityCreature)) {
/* 448 */               ((EntityCreature)entity).func_70784_b(player);
/*     */             }
/* 450 */             ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_82731_v.func_76396_c(), 80));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static int getWarpFromGear(EntityPlayer player)
/*     */   {
/* 460 */     int w = EventHandlerRunic.getFinalWarp(player.func_71045_bC(), player);
/*     */     
/* 462 */     for (int a = 0; a < 4; a++) {
/* 463 */       w += EventHandlerRunic.getFinalWarp(player.field_71071_by.func_70440_f(a), player);
/*     */     }
/*     */     
/* 466 */     IInventory baubles = baubles.api.BaublesApi.getBaubles(player);
/* 467 */     for (int a = 0; a < 4; a++) {
/* 468 */       w += EventHandlerRunic.getFinalWarp(baubles.func_70301_a(a), player);
/*     */     }
/*     */     
/* 471 */     return w;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/WarpEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */