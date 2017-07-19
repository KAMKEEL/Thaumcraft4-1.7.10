/*     */ package thaumcraft.common;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.Mod;
/*     */ import cpw.mods.fml.common.Mod.EventHandler;
/*     */ import cpw.mods.fml.common.Mod.Instance;
/*     */ import cpw.mods.fml.common.SidedProxy;
/*     */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLInterModComms;
/*     */ import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
/*     */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLServerStartingEvent;
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import cpw.mods.fml.common.registry.VillagerRegistry;
/*     */ import java.io.File;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.BlockDispenser;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IRegistry;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.structure.MapGenStructureIO;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.client.lib.RenderEventHandler;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigAspects;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigEntities;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.config.ConfigRecipes;
/*     */ import thaumcraft.common.config.ConfigResearch;
/*     */ import thaumcraft.common.items.BehaviorDispenseAlumetum;
/*     */ import thaumcraft.common.lib.CreativeTabThaumcraft;
/*     */ import thaumcraft.common.lib.InternalMethodHandler;
/*     */ import thaumcraft.common.lib.events.CommandThaumcraft;
/*     */ import thaumcraft.common.lib.events.EventHandlerEntity;
/*     */ import thaumcraft.common.lib.events.EventHandlerRunic;
/*     */ import thaumcraft.common.lib.events.EventHandlerWorld;
/*     */ import thaumcraft.common.lib.events.ServerTickEventsFML;
/*     */ import thaumcraft.common.lib.network.EventHandlerNetwork;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketWarpMessage;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.research.ScanManager;
/*     */ import thaumcraft.common.lib.utils.CropUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ComponentBankerHome;
/*     */ import thaumcraft.common.lib.world.ComponentWizardTower;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ import thaumcraft.common.lib.world.VillageBankerManager;
/*     */ import thaumcraft.common.lib.world.VillageWizardManager;
/*     */ import thaumcraft.common.lib.world.dim.WorldProviderOuter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mod(modid="Thaumcraft", name="Thaumcraft", version="4.2.3.5", guiFactory="thaumcraft.client.ThaumcraftGuiFactory", dependencies="required-after:Forge@[10.13.2,);required-after:Baubles@[1.0.1.10,)")
/*     */ public class Thaumcraft
/*     */ {
/*     */   public static final String MODID = "Thaumcraft";
/*     */   public static final String MODNAME = "Thaumcraft";
/*     */   public static final String VERSION = "4.2.3.5";
/*     */   @SidedProxy(clientSide="thaumcraft.client.ClientProxy", serverSide="thaumcraft.common.CommonProxy")
/*     */   public static CommonProxy proxy;
/*     */   @Mod.Instance("Thaumcraft")
/*     */   public static Thaumcraft instance;
/*     */   ResearchManager researchManager;
/*     */   public ThaumcraftWorldGenerator worldGen;
/*     */   public EventHandlerWorld worldEventHandler;
/*     */   public EventHandlerNetwork networkEventHandler;
/*     */   public ServerTickEventsFML serverTickEvents;
/*     */   public EventHandlerEntity entityEventHandler;
/*     */   public EventHandlerRunic runicEventHandler;
/*     */   public RenderEventHandler renderEventHandler;
/*     */   public File modDir;
/* 101 */   public static final Logger log = LogManager.getLogger("THAUMCRAFT");
/*     */   
/*     */   @Mod.EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event) {
/* 105 */     event.getModMetadata().version = "4.2.3.5";
/* 106 */     this.modDir = event.getModConfigurationDirectory();
/*     */     try
/*     */     {
/* 109 */       Config.initialize(event.getSuggestedConfigurationFile());
/*     */     } catch (Exception e) {
/* 111 */       log.error("Thaumcraft has a problem loading it's configuration");
/*     */     } finally {
/* 113 */       if (Config.config != null) { Config.save();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */     ThaumcraftApi.internalMethods = new InternalMethodHandler();
/*     */     
/*     */ 
/*     */ 
/* 125 */     proxy.playerKnowledge = new PlayerKnowledge();
/* 126 */     proxy.researchManager = new ResearchManager();
/* 127 */     this.worldEventHandler = new EventHandlerWorld();
/* 128 */     this.serverTickEvents = new ServerTickEventsFML();
/* 129 */     this.entityEventHandler = new EventHandlerEntity();
/* 130 */     this.runicEventHandler = new EventHandlerRunic();
/* 131 */     this.renderEventHandler = new RenderEventHandler();
/* 132 */     this.networkEventHandler = new EventHandlerNetwork();
/*     */     
/*     */ 
/*     */ 
/* 136 */     PacketHandler.init();
/*     */     
/*     */ 
/*     */ 
/* 140 */     MinecraftForge.TERRAIN_GEN_BUS.register(this.worldEventHandler);
/* 141 */     MinecraftForge.EVENT_BUS.register(this.worldEventHandler);
/* 142 */     FMLCommonHandler.instance().bus().register(this.worldEventHandler);
/* 143 */     MinecraftForge.EVENT_BUS.register(this.entityEventHandler);
/* 144 */     MinecraftForge.EVENT_BUS.register(this.runicEventHandler);
/* 145 */     FMLCommonHandler.instance().bus().register(this.networkEventHandler);
/* 146 */     GameRegistry.registerFuelHandler(this.worldEventHandler);
/* 147 */     FMLCommonHandler.instance().bus().register(this.serverTickEvents);
/* 148 */     GameRegistry.registerWorldGenerator(this.worldGen = new ThaumcraftWorldGenerator(), 0);
/*     */     
/* 150 */     ThaumcraftApi.registerScanEventhandler(new ScanManager());
/*     */     
/*     */ 
/*     */ 
/* 154 */     Config.save();
/* 155 */     ConfigBlocks.init();
/* 156 */     ConfigItems.init();
/*     */     try
/*     */     {
/* 159 */       MapGenStructureIO.func_143031_a(ComponentWizardTower.class, "TCVillageTower");
/* 160 */       MapGenStructureIO.func_143031_a(ComponentBankerHome.class, "TCVillageBanker");
/*     */     } catch (Throwable e) {
/* 162 */       log.error("[Thaumcraft] Village tower could not be registered.");
/*     */     }
/*     */     
/* 165 */     proxy.registerHandlers();
/* 166 */     this.worldGen.initialize();
/*     */     
/* 168 */     FMLCommonHandler.instance().bus().register(instance);
/*     */     
/* 170 */     Config.registerBiomes();
/*     */   }
/*     */   
/* 173 */   public static boolean isHalloween = false;
/*     */   
/*     */ 
/*     */   @Mod.EventHandler
/*     */   public void init(FMLInitializationEvent evt)
/*     */   {
/* 179 */     proxy.registerDisplayInformation();
/*     */     
/* 181 */     ConfigEntities.init();
/*     */     
/* 183 */     BlockDispenser.field_149943_a.func_82595_a(ConfigItems.itemResource, new BehaviorDispenseAlumetum());
/*     */     
/* 185 */     NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
/*     */     
/*     */ 
/* 188 */     VillageWizardManager villageManagerWizard = new VillageWizardManager();
/* 189 */     VillageBankerManager villageManagerBanker = new VillageBankerManager();
/* 190 */     VillagerRegistry.instance().registerVillagerId(ConfigEntities.entWizardId);
/* 191 */     VillagerRegistry.instance().registerVillagerId(ConfigEntities.entBankerId);
/* 192 */     VillagerRegistry.instance().registerVillageCreationHandler(villageManagerWizard);
/* 193 */     VillagerRegistry.instance().registerVillageCreationHandler(villageManagerBanker);
/* 194 */     VillagerRegistry.instance().registerVillageTradeHandler(ConfigEntities.entWizardId, villageManagerWizard);
/* 195 */     VillagerRegistry.instance().registerVillageTradeHandler(ConfigEntities.entBankerId, villageManagerBanker);
/*     */     
/* 197 */     proxy.registerKeyBindings();
/*     */     
/* 199 */     DimensionManager.registerProviderType(Config.dimensionOuterId, WorldProviderOuter.class, false);
/* 200 */     DimensionManager.registerDimension(Config.dimensionOuterId, Config.dimensionOuterId);
/*     */   }
/*     */   
/*     */ 
/*     */   @Mod.EventHandler
/*     */   public void postInit(FMLPostInitializationEvent evt)
/*     */   {
/* 207 */     Config.initPotions();
/* 208 */     ConfigEntities.initEntitySpawns();
/* 209 */     Config.initModCompatibility();
/* 210 */     ConfigItems.postInit();
/* 211 */     ConfigRecipes.init();
/* 212 */     ConfigAspects.init();
/* 213 */     ConfigResearch.init();
/* 214 */     Config.initLoot();
/* 215 */     Config.initMisc();
/*     */     
/* 217 */     ImmutableList<FMLInterModComms.IMCMessage> messages = FMLInterModComms.fetchRuntimeMessages(this);
/* 218 */     for (FMLInterModComms.IMCMessage message : messages) {
/* 219 */       if ((message.key.equals("harvestStandardCrop")) && (message.isItemStackMessage())) {
/* 220 */         ItemStack crop = message.getItemStackValue();
/* 221 */         CropUtils.addStandardCrop(crop, crop.func_77960_j());
/* 222 */         log.warn("Adding standard crop support for [" + crop.func_82833_r() + "]");
/*     */       }
/* 224 */       if ((message.key.equals("harvestClickableCrop")) && (message.isItemStackMessage())) {
/* 225 */         ItemStack crop = message.getItemStackValue();
/* 226 */         CropUtils.addClickableCrop(crop, crop.func_77960_j());
/* 227 */         log.warn("Adding clickable crop support for [" + crop.func_82833_r() + "]");
/*     */       }
/* 229 */       if ((message.key.equals("harvestStackedCrop")) && (message.isItemStackMessage())) {
/* 230 */         ItemStack crop = message.getItemStackValue();
/* 231 */         CropUtils.addStackedCrop(crop, crop.func_77960_j());
/* 232 */         log.warn("Adding stacked crop support for [" + crop.func_82833_r() + "]");
/*     */       }
/* 234 */       if ((message.key.equals("nativeCluster")) && (message.isStringMessage())) {
/* 235 */         String[] t = message.getStringValue().split(",");
/* 236 */         if ((t != null) && (t.length == 5)) {
/*     */           try {
/* 238 */             ItemStack ore = new ItemStack(Item.func_150899_d(Integer.parseInt(t[0])), 1, Integer.parseInt(t[1]));
/* 239 */             ItemStack cluster = new ItemStack(Item.func_150899_d(Integer.parseInt(t[2])), 1, Integer.parseInt(t[3]));
/* 240 */             Utils.addSpecialMiningResult(ore, cluster, Float.parseFloat(t[4]));
/* 241 */             log.warn("Adding [" + cluster.func_82833_r() + "] to special result list for [" + ore.func_82833_r() + "]");
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/* 246 */       if ((message.key.equals("lampBlacklist")) && (message.isItemStackMessage())) {
/* 247 */         ItemStack crop = message.getItemStackValue();
/* 248 */         CropUtils.blacklistLamp(crop, crop.func_77960_j());
/* 249 */         log.warn("[Thaumcraft] Blacklisting [" + crop.func_82833_r() + "] for lamp of growth");
/*     */       }
/* 251 */       if ((message.key.equals("dimensionBlacklist")) && (message.isStringMessage())) {
/* 252 */         String[] t = message.getStringValue().split(":");
/* 253 */         if ((t != null) && (t.length == 2)) {
/*     */           try {
/* 255 */             ThaumcraftWorldGenerator.addDimBlacklist(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
/* 256 */             log.warn("Blacklisting dimension [" + Integer.parseInt(t[0]) + "] to only spawn TC content at level [" + Integer.parseInt(t[1]) + "]");
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/* 261 */       if ((message.key.equals("biomeBlacklist")) && (message.isStringMessage())) {
/* 262 */         String[] t = message.getStringValue().split(":");
/* 263 */         if ((t != null) && (t.length == 2) && (BiomeGenBase.func_150568_d(Integer.parseInt(t[0])) != null)) {
/*     */           try {
/* 265 */             ThaumcraftWorldGenerator.addBiomeBlacklist(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
/* 266 */             log.warn("Blacklisting [" + BiomeGenBase.func_150568_d(Integer.parseInt(t[0])).field_76791_y + "] to only spawn TC content at level [" + Integer.parseInt(t[1]) + "]");
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/*     */       
/* 272 */       if ((message.key.equals("championWhiteList")) && (message.isStringMessage())) {
/*     */         try
/*     */         {
/* 275 */           String[] t = message.getStringValue().split(":");
/* 276 */           Class oclass = (Class)EntityList.field_75625_b.get(t[0]);
/* 277 */           if (oclass != null)
/*     */           {
/* 279 */             ConfigEntities.championModWhitelist.put(oclass, Integer.valueOf(Integer.parseInt(t[1])));
/* 280 */             log.warn("Whitelisting [" + t[0] + "] to spawn champion mobs at level [" + Integer.parseInt(t[1]) + "]");
/*     */           }
/*     */         } catch (Exception e) {
/* 283 */           log.error("Failed to Whitelist [" + message.getStringValue() + "] with [ championWhiteList ] message.");
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @Mod.EventHandler
/*     */   public void serverLoad(FMLServerStartingEvent event)
/*     */   {
/* 293 */     event.registerServerCommand(new CommandThaumcraft());
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
/* 298 */     if (eventArgs.modID.equals("Thaumcraft")) {
/* 299 */       Config.syncConfigurable();
/* 300 */       if ((Config.config != null) && (Config.config.hasChanged())) {
/* 301 */         Config.save();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void addWarpToPlayer(EntityPlayer player, int amount, boolean temporary) {
/* 307 */     if (player.field_70170_p.field_72995_K) return;
/* 308 */     if (proxy.getPlayerKnowledge() == null) return;
/* 309 */     if ((!temporary) && (amount < 0)) return;
/* 310 */     if (amount == 0) return;
/* 311 */     if (temporary) {
/* 312 */       if ((amount < 0) && (proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_()) <= 0)) return;
/* 313 */       proxy.getPlayerKnowledge().addWarpTemp(player.func_70005_c_(), amount);
/* 314 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)2), (EntityPlayerMP)player);
/* 315 */       PacketHandler.INSTANCE.sendTo(new PacketWarpMessage(player, (byte)2, amount), (EntityPlayerMP)player);
/*     */     } else {
/* 317 */       proxy.getPlayerKnowledge().addWarpPerm(player.func_70005_c_(), amount);
/* 318 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)0), (EntityPlayerMP)player);
/* 319 */       PacketHandler.INSTANCE.sendTo(new PacketWarpMessage(player, (byte)0, amount), (EntityPlayerMP)player);
/*     */     }
/*     */     
/* 322 */     proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), proxy.getPlayerKnowledge().getWarpTotal(player.func_70005_c_()));
/*     */   }
/*     */   
/*     */   public static void addStickyWarpToPlayer(EntityPlayer player, int amount) {
/* 326 */     if (player.field_70170_p.field_72995_K) return;
/* 327 */     if (proxy.getPlayerKnowledge() == null) return;
/* 328 */     if (amount == 0) return;
/* 329 */     if ((amount < 0) && (proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_()) <= 0)) return;
/* 330 */     proxy.getPlayerKnowledge().addWarpSticky(player.func_70005_c_(), amount);
/* 331 */     PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)1), (EntityPlayerMP)player);
/* 332 */     PacketHandler.INSTANCE.sendTo(new PacketWarpMessage(player, (byte)1, amount), (EntityPlayerMP)player);
/*     */     
/* 334 */     proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), proxy.getPlayerKnowledge().getWarpTotal(player.func_70005_c_()));
/*     */   }
/*     */   
/* 337 */   public static CreativeTabs tabTC = new CreativeTabThaumcraft(CreativeTabs.getNextID(), "thaumcraft");
/*     */   
/* 339 */   public boolean aspectShift = false;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/Thaumcraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */