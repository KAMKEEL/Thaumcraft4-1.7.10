/*      */ package thaumcraft.common.config;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.MapColor;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.enchantment.Enchantment;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.CraftingManager;
/*      */ import net.minecraft.item.crafting.FurnaceRecipes;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionHelper;
/*      */ import net.minecraft.util.WeightedRandomChestContent;
/*      */ import net.minecraftforge.common.BiomeDictionary;
/*      */ import net.minecraftforge.common.BiomeDictionary.Type;
/*      */ import net.minecraftforge.common.ChestGenHooks;
/*      */ import net.minecraftforge.common.config.Configuration;
/*      */ import net.minecraftforge.common.config.Property;
/*      */ import net.minecraftforge.oredict.OreDictionary;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import thaumcraft.api.ThaumcraftApi;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.potions.PotionFluxTaint;
/*      */ import thaumcraft.api.potions.PotionVisExhaust;
/*      */ import thaumcraft.common.Thaumcraft;
/*      */ import thaumcraft.common.entities.EntityAspectOrb;
/*      */ import thaumcraft.common.entities.EntityFallingTaint;
/*      */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*      */ import thaumcraft.common.entities.golems.EntityTravelingTrunk;
/*      */ import thaumcraft.common.entities.monster.EntityCultistCleric;
/*      */ import thaumcraft.common.entities.monster.EntityCultistKnight;
/*      */ import thaumcraft.common.entities.monster.EntityEldritchCrab;
/*      */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*      */ import thaumcraft.common.entities.monster.EntityInhabitedZombie;
/*      */ import thaumcraft.common.entities.monster.EntityPech;
/*      */ import thaumcraft.common.entities.monster.EntityTaintSpore;
/*      */ import thaumcraft.common.entities.monster.EntityWisp;
/*      */ import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
/*      */ import thaumcraft.common.entities.monster.boss.EntityCultistPortal;
/*      */ import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
/*      */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*      */ import thaumcraft.common.items.baubles.ItemAmuletVis;
/*      */ import thaumcraft.common.items.equipment.ItemElementalAxe;
/*      */ import thaumcraft.common.lib.enchantment.EnchantmentHaste;
/*      */ import thaumcraft.common.lib.enchantment.EnchantmentRepair;
/*      */ import thaumcraft.common.lib.potions.PotionBlurredVision;
/*      */ import thaumcraft.common.lib.potions.PotionDeathGaze;
/*      */ import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
/*      */ import thaumcraft.common.lib.potions.PotionSunScorned;
/*      */ import thaumcraft.common.lib.potions.PotionThaumarhia;
/*      */ import thaumcraft.common.lib.potions.PotionUnnaturalHunger;
/*      */ import thaumcraft.common.lib.potions.PotionWarpWard;
/*      */ import thaumcraft.common.lib.utils.CropUtils;
/*      */ import thaumcraft.common.lib.utils.Utils;
/*      */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*      */ import thaumcraft.common.lib.world.biomes.BiomeGenEerie;
/*      */ import thaumcraft.common.lib.world.biomes.BiomeGenEldritch;
/*      */ import thaumcraft.common.lib.world.biomes.BiomeGenMagicalForest;
/*      */ import thaumcraft.common.lib.world.biomes.BiomeGenTaint;
/*      */ import thaumcraft.common.lib.world.biomes.BiomeHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Config
/*      */ {
/*      */   public static Configuration config;
/*      */   public static final String CATEGORY_ENCH = "Enchantments";
/*      */   public static final String CATEGORY_ENTITIES = "Entities";
/*      */   public static final String CATEGORY_BIOMES = "Biomes";
/*      */   public static final String CATEGORY_RESEARCH = "Research";
/*      */   public static final String CATEGORY_GEN = "World_Generation";
/*      */   public static final String CATEGORY_REGEN = "World_Regeneration";
/*      */   public static final String CATEGORY_SPAWN = "Monster_Spawning";
/*      */   public static final String CATEGORY_RUNIC = "Runic_Shielding";
/*   87 */   public static int biomeTaintID = 192;
/*   88 */   public static int biomeMagicalForestID = 193;
/*   89 */   public static int biomeEerieID = 194;
/*   90 */   public static int biomeEldritchID = 195;
/*   91 */   public static int biomeTaintWeight = 2;
/*   92 */   public static int biomeMagicalForestWeight = 5;
/*   93 */   public static int taintSpreadRate = 200;
/*   94 */   public static boolean taintFromFlux = true;
/*   95 */   public static boolean hardNode = true;
/*   96 */   public static boolean wuss = false;
/*   97 */   public static int dimensionOuterId = -42;
/*   98 */   public static boolean championMobs = true;
/*      */   
/*      */ 
/*  101 */   public static int shieldRecharge = 2000;
/*  102 */   public static int shieldWait = 80;
/*  103 */   public static int shieldCost = 50;
/*      */   
/*      */ 
/*  106 */   public static boolean colorBlind = false;
/*  107 */   public static boolean shaders = true;
/*  108 */   public static boolean crooked = true;
/*  109 */   public static boolean showTags = false;
/*  110 */   public static boolean blueBiome = false;
/*  111 */   public static boolean allowMirrors = true;
/*  112 */   public static boolean dialBottom = false;
/*  113 */   public static int nodeRefresh = 10;
/*      */   
/*      */   public static final float auraSize = 4.0F;
/*  116 */   public static boolean genAura = true;
/*  117 */   public static boolean genStructure = true;
/*  118 */   public static boolean genCinnibar = true;
/*  119 */   public static boolean genAmber = true;
/*  120 */   public static boolean genInfusedStone = true;
/*  121 */   public static boolean genTrees = true;
/*  122 */   public static boolean genTaint = true;
/*      */   
/*  124 */   public static boolean regenAura = false;
/*  125 */   public static boolean regenStructure = false;
/*  126 */   public static boolean regenCinnibar = false;
/*  127 */   public static boolean regenAmber = false;
/*  128 */   public static boolean regenInfusedStone = false;
/*  129 */   public static boolean regenTrees = false;
/*  130 */   public static boolean regenTaint = false;
/*  131 */   public static String regenKey = "DEFAULT";
/*  132 */   public static boolean wardedStone = true;
/*  133 */   public static boolean allowCheatSheet = true;
/*  134 */   public static boolean golemChestInteract = true;
/*  135 */   public static int nodeRarity = 36;
/*  136 */   public static int specialNodeRarity = 18;
/*  137 */   public static int notificationDelay = 5000;
/*  138 */   public static int notificationMax = 15;
/*  139 */   public static boolean glowyTaint = true;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  144 */   public static int researchDifficulty = 0;
/*  145 */   public static int aspectTotalCap = 100;
/*      */   
/*  147 */   public static int golemDelay = 5;
/*  148 */   public static int golemIgnoreDelay = 10000;
/*  149 */   public static int golemLinkQuality = 16;
/*      */   
/*  151 */   public static boolean CwardedStone = true;
/*  152 */   public static boolean CallowCheatSheet = true;
/*  153 */   public static boolean CallowMirrors = true;
/*  154 */   public static boolean ChardNode = true;
/*  155 */   public static boolean Cwuss = false;
/*  156 */   public static int CresearchDifficulty = 0;
/*  157 */   public static int CaspectTotalCap = 100;
/*      */   
/*  159 */   public static boolean spawnAngryZombie = true;
/*  160 */   public static boolean spawnFireBat = true;
/*  161 */   public static boolean spawnTaintacle = true;
/*  162 */   public static boolean spawnWisp = true;
/*  163 */   public static boolean spawnTaintSpore = true;
/*  164 */   public static boolean spawnPech = true;
/*  165 */   public static boolean spawnElder = true;
/*      */   
/*      */   public static void initialize(File file) {
/*  168 */     config = new Configuration(file);
/*  169 */     config.addCustomCategoryComment("Enchantments", "Custom enchantments");
/*  170 */     config.addCustomCategoryComment("Monster_Spawning", "Will these mobs spawn");
/*      */     
/*      */ 
/*  173 */     config.addCustomCategoryComment("Research", "Various research related things.");
/*  174 */     config.addCustomCategoryComment("World_Generation", "Settings to turn certain world-gen on or off.");
/*  175 */     config.addCustomCategoryComment("World_Regeneration", "If a chunk is encountered that skipped TC worldgen, then the game will attempt to regenerate certain world features if they are set to true. CAUTION: Best used for worlds created before you added this mod, and only if you know what you are doing. Backups are advised.");
/*      */     
/*  177 */     config.addCustomCategoryComment("Biomes", "Biomes and effects");
/*  178 */     config.addCustomCategoryComment("Runic_Shielding", "Runic Shielding");
/*      */     
/*      */ 
/*  181 */     config.load();
/*      */     
/*  183 */     syncConfigurable();
/*      */     
/*      */ 
/*      */ 
/*  187 */     Property btcp = config.get("Biomes", "taint_biome_weight", 2);
/*  188 */     btcp.comment = "higher values increases number of taint biomes. If you are using biome addon mods you probably want to increase this weight quite a bit";
/*  189 */     biomeTaintWeight = btcp.getInt();
/*      */     
/*  191 */     Property biomeTaintProp = config.get("Biomes", "biome_taint", biomeTaintID);
/*  192 */     biomeTaintProp.comment = "Taint biome id";
/*  193 */     biomeTaintID = biomeTaintProp.getInt();
/*  194 */     if (net.minecraft.world.biome.BiomeGenBase.func_150565_n()[biomeTaintID] != null) {
/*  195 */       biomeTaintID = ThaumcraftWorldGenerator.getFirstFreeBiomeSlot(biomeTaintID);
/*  196 */       biomeTaintProp.set(biomeTaintID);
/*      */     }
/*      */     try {
/*  199 */       ThaumcraftWorldGenerator.biomeTaint = new BiomeGenTaint(biomeTaintID);
/*      */     } catch (Exception e) {
/*  201 */       Thaumcraft.log.fatal("Could not register Taint Biome");
/*      */     }
/*      */     
/*  204 */     Property mfcp = config.get("Biomes", "magical_forest_biome_weight", 5);
/*  205 */     mfcp.comment = "higher values increases number of magical forest biomes. If you are using biome addon mods you probably want to increase this weight quite a bit";
/*  206 */     biomeMagicalForestWeight = mfcp.getInt();
/*      */     
/*  208 */     Property biomeMFProp = config.get("Biomes", "biome_magical_forest", biomeMagicalForestID);
/*  209 */     biomeMFProp.comment = "Magical Forest biome id";
/*  210 */     biomeMagicalForestID = biomeMFProp.getInt();
/*  211 */     if (net.minecraft.world.biome.BiomeGenBase.func_150565_n()[biomeMagicalForestID] != null) {
/*  212 */       biomeMagicalForestID = ThaumcraftWorldGenerator.getFirstFreeBiomeSlot(biomeMagicalForestID);
/*  213 */       biomeMFProp.set(biomeMagicalForestID);
/*      */     }
/*      */     try {
/*  216 */       ThaumcraftWorldGenerator.biomeMagicalForest = new BiomeGenMagicalForest(biomeMagicalForestID);
/*      */     } catch (Exception e) {
/*  218 */       Thaumcraft.log.fatal("Could not register Magical Forest Biome");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  223 */     Property biomeEerieProp = config.get("Biomes", "biome_eerie", biomeEerieID);
/*  224 */     biomeEerieProp.comment = "Eerie biome id";
/*  225 */     biomeEerieID = biomeEerieProp.getInt();
/*  226 */     if (net.minecraft.world.biome.BiomeGenBase.func_150565_n()[biomeEerieID] != null) {
/*  227 */       biomeEerieID = ThaumcraftWorldGenerator.getFirstFreeBiomeSlot(biomeEerieID);
/*  228 */       biomeEerieProp.set(biomeEerieID);
/*      */     }
/*      */     try {
/*  231 */       ThaumcraftWorldGenerator.biomeEerie = new BiomeGenEerie(biomeEerieID);
/*      */     } catch (Exception e) {
/*  233 */       Thaumcraft.log.fatal("Could not register Eerie Biome");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  238 */     Property biomeEldritchProp = config.get("Biomes", "biome_eldritch", biomeEldritchID);
/*  239 */     biomeEldritchProp.comment = "Eldritch Lands biome id";
/*  240 */     biomeEldritchID = biomeEldritchProp.getInt();
/*  241 */     if (net.minecraft.world.biome.BiomeGenBase.func_150565_n()[biomeEldritchID] != null) {
/*  242 */       biomeEldritchID = ThaumcraftWorldGenerator.getFirstFreeBiomeSlot(biomeEldritchID);
/*  243 */       biomeEldritchProp.set(biomeEldritchID);
/*      */     }
/*      */     try {
/*  246 */       ThaumcraftWorldGenerator.biomeEldritchLands = new BiomeGenEldritch(biomeEldritchID);
/*      */     } catch (Exception e) {
/*  248 */       Thaumcraft.log.fatal("Could not register Eldritch Lands Biome");
/*      */     }
/*      */     
/*      */ 
/*  252 */     Property dimEldritch = config.get("Biomes", "outer_lands_dim", dimensionOuterId);
/*  253 */     dimensionOuterId = dimEldritch.getInt();
/*      */     
/*      */ 
/*  256 */     int encIdx = 150;
/*      */     
/*  258 */     Property enchHas = config.get("Enchantments", "ench_haste", encIdx++);
/*  259 */     enchHaste = new EnchantmentHaste(enchHas.getInt(), 3);
/*  260 */     ThaumcraftApi.enchantHaste = enchHas.getInt();
/*  261 */     Enchantment.addToBookList(enchHaste);
/*      */     
/*  263 */     Property enchRep = config.get("Enchantments", "ench_repair", encIdx++);
/*  264 */     enchRepair = new EnchantmentRepair(enchRep.getInt(), 2);
/*  265 */     ThaumcraftApi.enchantRepair = enchRep.getInt();
/*  266 */     Enchantment.addToBookList(enchRepair);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  272 */     config.save();
/*      */   }
/*      */   
/*      */   public static void save() {
/*  276 */     config.save();
/*      */   }
/*      */   
/*  279 */   public static int potionVisExhaustID = 18;
/*  280 */   public static int potionInfVisExhaustID = 18;
/*  281 */   public static int potionBlurredID = 18;
/*  282 */   public static int potionThaumarhiaID = 18;
/*  283 */   public static int potionTaintPoisonID = 19;
/*  284 */   public static int potionUnHungerID = 17;
/*  285 */   public static int potionSunScornedID = 17;
/*  286 */   public static int potionWarpWardID = 23;
/*  287 */   public static int potionDeathGazeID = 17;
/*      */   
/*      */   public static void initPotions()
/*      */   {
/*  291 */     int customPotions = 8;
/*  292 */     int potionOffset = Potion.field_76425_a.length;
/*  293 */     int start = 0;
/*  294 */     Thaumcraft.log.info("Found potion array with a size of " + potionOffset);
/*  295 */     if (potionOffset < 128 - customPotions) {
/*  296 */       Thaumcraft.log.info("Extending Potion.potionTypes array to " + (potionOffset + customPotions));
/*  297 */       Potion[] potionTypes = new Potion[potionOffset + customPotions];
/*  298 */       System.arraycopy(Potion.field_76425_a, 0, potionTypes, 0, potionOffset);
/*  299 */       Utils.setPrivateFinalValue(Potion.class, null, potionTypes, new String[] { "potionTypes", "field_76425_a", "a" });
/*  300 */       start = potionOffset++ - 1;
/*      */     } else {
/*  302 */       start = -1;
/*      */     }
/*      */     
/*  305 */     start = getNextPotionId(start);
/*  306 */     if (start >= 0) {
/*  307 */       potionTaintPoisonID = start;
/*  308 */       PotionFluxTaint.instance = new PotionFluxTaint(potionTaintPoisonID, true, 6697847);
/*  309 */       PotionFluxTaint.init();
/*  310 */       Thaumcraft.log.info("Initializing PotionFluxTaint with id " + start);
/*      */     }
/*      */     
/*  313 */     start = getNextPotionId(start);
/*  314 */     if (start >= 0) {
/*  315 */       potionVisExhaustID = start;
/*  316 */       PotionVisExhaust.instance = new PotionVisExhaust(potionVisExhaustID, true, 6702199);
/*  317 */       PotionVisExhaust.init();
/*  318 */       Thaumcraft.log.info("Initializing PotionVisExhaust with id " + start);
/*      */     }
/*      */     
/*  321 */     start = getNextPotionId(start);
/*  322 */     if (start >= 0) {
/*  323 */       potionInfVisExhaustID = start;
/*  324 */       PotionInfectiousVisExhaust.instance = new PotionInfectiousVisExhaust(potionInfVisExhaustID, true, 6706551);
/*  325 */       PotionInfectiousVisExhaust.init();
/*  326 */       Thaumcraft.log.info("Initializing PotionInfectiousVisExhaust with id " + start);
/*      */     }
/*      */     
/*  329 */     start = getNextPotionId(start);
/*  330 */     if (start >= 0) {
/*  331 */       potionUnHungerID = start;
/*  332 */       PotionUnnaturalHunger.instance = new PotionUnnaturalHunger(potionUnHungerID, true, 4482611);
/*  333 */       PotionUnnaturalHunger.init();
/*  334 */       Thaumcraft.log.info("Initializing PotionUnnaturalHunger with id " + start);
/*      */     }
/*      */     
/*  337 */     start = getNextPotionId(start);
/*  338 */     if (start >= 0) {
/*  339 */       potionWarpWardID = start;
/*  340 */       PotionWarpWard.instance = new PotionWarpWard(potionWarpWardID, false, 14742263);
/*  341 */       PotionWarpWard.init();
/*  342 */       Thaumcraft.log.info("Initializing PotionWarpWard with id " + start);
/*      */     }
/*      */     
/*  345 */     start = getNextPotionId(start);
/*  346 */     if (start >= 0) {
/*  347 */       potionDeathGazeID = start;
/*  348 */       PotionDeathGaze.instance = new PotionDeathGaze(potionDeathGazeID, true, 6702131);
/*  349 */       PotionDeathGaze.init();
/*  350 */       Thaumcraft.log.info("Initializing PotionDeathGaze with id " + start);
/*      */     }
/*      */     
/*  353 */     start = getNextPotionId(start);
/*  354 */     if (start >= 0) {
/*  355 */       potionBlurredID = start;
/*  356 */       PotionBlurredVision.instance = new PotionBlurredVision(potionBlurredID, true, 8421504);
/*  357 */       PotionBlurredVision.init();
/*  358 */       Thaumcraft.log.info("Initializing PotionBlurredVision with id " + start);
/*      */     }
/*      */     
/*  361 */     start = getNextPotionId(start);
/*  362 */     if (start >= 0) {
/*  363 */       potionSunScornedID = start;
/*  364 */       PotionSunScorned.instance = new PotionSunScorned(potionSunScornedID, true, 16308330);
/*  365 */       PotionSunScorned.init();
/*  366 */       Thaumcraft.log.info("Initializing PotionSunScorned with id " + start);
/*      */     }
/*      */     
/*  369 */     start = getNextPotionId(start);
/*  370 */     if (start >= 0) {
/*  371 */       potionThaumarhiaID = start;
/*  372 */       PotionThaumarhia.instance = new PotionThaumarhia(potionThaumarhiaID, true, 6702199);
/*  373 */       PotionThaumarhia.init();
/*  374 */       Thaumcraft.log.info("Initializing PotionThaumarhia with id " + start);
/*      */     }
/*      */   }
/*      */   
/*      */   static int getNextPotionId(int start) {
/*  379 */     if ((Potion.field_76425_a != null) && (start > 0) && (start < Potion.field_76425_a.length) && (Potion.field_76425_a[start] == null)) {
/*  380 */       return start;
/*      */     }
/*  382 */     start++;
/*  383 */     if (start < 128) start = getNextPotionId(start); else start = -1;
/*  384 */     return start;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void syncConfigurable()
/*      */   {
/*  392 */     genAura = config.get("World_Generation", "generate_aura_nodes", true).getBoolean(true);
/*  393 */     genStructure = config.get("World_Generation", "generate_structures", true).getBoolean(true);
/*  394 */     genCinnibar = config.get("World_Generation", "generate_cinnibar_ore", true).getBoolean(true);
/*  395 */     genAmber = config.get("World_Generation", "generate_amber_ore", true).getBoolean(true);
/*  396 */     genInfusedStone = config.get("World_Generation", "generate_infused_stone", true).getBoolean(true);
/*  397 */     genTrees = config.get("World_Generation", "generate_trees", true).getBoolean(true);
/*  398 */     Property gt = config.get("World_Generation", "generate_taint", genTaint);
/*  399 */     gt.comment = "Can taint biomes generate at worldgen";
/*  400 */     genTaint = gt.getBoolean(true);
/*      */     
/*      */ 
/*  403 */     Property regKey = config.get("World_Regeneration", "regen_key", "DEFAULT");
/*  404 */     regKey.comment = "This key is used to keep track of which chunk have been generated/regenerated. Changing it will cause the regeneration code to run again, so only change it if you want it to happen. Useful to regen only one world feature at a time.";
/*  405 */     regenKey = regKey.getString();
/*  406 */     regenAura = config.get("World_Regeneration", "aura_nodes", false).getBoolean(false);
/*      */     
/*  408 */     regenStructure = config.get("World_Regeneration", "structures", false).getBoolean(false);
/*      */     
/*  410 */     regenCinnibar = config.get("World_Regeneration", "cinnibar_ore", false).getBoolean(false);
/*      */     
/*  412 */     regenAmber = config.get("World_Regeneration", "amber_ore", false).getBoolean(false);
/*      */     
/*  414 */     regenInfusedStone = config.get("World_Regeneration", "infused_stone", false).getBoolean(false);
/*      */     
/*  416 */     regenTrees = config.get("World_Regeneration", "trees", false).getBoolean(false);
/*      */     
/*  418 */     regenTaint = config.get("World_Regeneration", "taint", false).getBoolean(false);
/*      */     
/*      */ 
/*      */ 
/*  422 */     Property resDif = config.get("Research", "research_difficulty", 0);
/*  423 */     resDif.comment = "0 = normal, -1 = easy (all research items are directly purchased with RP), 1 = Hard (all research items need to be solved via the research table)";
/*  424 */     CresearchDifficulty = researchDifficulty = resDif.getInt(8);
/*      */     
/*  426 */     Property aspTotCap = config.get("Research", "aspect_total_cap", 100);
/*  427 */     aspTotCap.comment = "The total amount of RP you can have in your pool per aspect before the scanning soft cap kicks in.";
/*  428 */     CaspectTotalCap = aspectTotalCap = aspTotCap.getInt(100);
/*      */     
/*      */ 
/*  431 */     spawnAngryZombie = config.get("Monster_Spawning", "spawn_angry_zombies", true).getBoolean(true);
/*  432 */     spawnFireBat = config.get("Monster_Spawning", "spawn_fire_bats", true).getBoolean(true);
/*  433 */     spawnWisp = config.get("Monster_Spawning", "spawn_wisps", true).getBoolean(true);
/*  434 */     spawnTaintacle = config.get("Monster_Spawning", "spawn_taintacles", true).getBoolean(true);
/*  435 */     spawnTaintSpore = config.get("Monster_Spawning", "spawn_taint_spores", true).getBoolean(true);
/*  436 */     spawnPech = config.get("Monster_Spawning", "spawn_pechs", true).getBoolean(true);
/*  437 */     spawnElder = config.get("Monster_Spawning", "spawn_eldercreatures", true).getBoolean(true);
/*      */     
/*  439 */     Property cm = config.get("Monster_Spawning", "champion_mobs", championMobs);
/*  440 */     cm.comment = "Setting this to false will disable spawning champion mobs. Even when false they will still have a greatly reduced chance of spawning in certain dangerous places.";
/*  441 */     championMobs = cm.getBoolean(true);
/*      */     
/*  443 */     Property am = config.get("general", "allow_mirrors", allowMirrors);
/*  444 */     am.comment = "Setting this to false will disable arcane mirror research and crafting recipes.";
/*  445 */     CallowMirrors = allowMirrors = am.getBoolean(true);
/*      */     
/*  447 */     Property cb = config.get("general", "color_blind", colorBlind);
/*  448 */     cb.comment = "Setting this to true will make certain colors higher contrast or darker to prevent them from being 'invisible' to color blind people.";
/*  449 */     colorBlind = cb.getBoolean(false);
/*      */     
/*  451 */     Property shad = config.get("general", "shaders", shaders);
/*  452 */     shad.comment = "This setting will disable certain thaumcraft shaders for those who experience FPS drops.";
/*  453 */     shaders = shad.getBoolean(false);
/*      */     
/*  455 */     Property ocd = config.get("general", "crooked", crooked);
/*  456 */     ocd.comment = "Hate crooked labels, kittens, puppies and all things awesome? If yes, set this to false.";
/*  457 */     crooked = ocd.getBoolean(true);
/*      */     
/*  459 */     Property hn = config.get("general", "hard_mode_nodes", hardNode);
/*  460 */     hn.comment = "Negative nodes like hungry, tainted or dark nodes will have additional, much nastier, effects.";
/*  461 */     ChardNode = hardNode = hn.getBoolean(true);
/*      */     
/*  463 */     Property wm = config.get("general", "wuss_mode", wuss);
/*  464 */     wm.comment = "Setting this to true disables Warp and similar mechanics. You wuss.";
/*  465 */     Cwuss = wuss = wm.getBoolean(false);
/*      */     
/*  467 */     Property dbp = config.get("general", "wand_dial_bottom", dialBottom);
/*  468 */     dbp.comment = "Set to true to have the wand dial display in the bottom left instead of the top left.";
/*  469 */     dialBottom = dbp.getBoolean(false);
/*      */     
/*  471 */     Property golDel = config.get("general", "golem_delay", golemDelay);
/*      */     
/*  473 */     golDel.comment = "How many ticks a golem waits between checking for tasks. Setting it higher will save server ticks, but will make the golems slower to react.";
/*  474 */     golemDelay = golDel.getInt();
/*  475 */     if (golemDelay < 1) { golemDelay = 1;
/*      */     }
/*  477 */     Property golIgDel = config.get("general", "golem_ignore_delay", golemIgnoreDelay);
/*      */     
/*  479 */     golIgDel.comment = "How many milliseconds a golem will ignore an item after it has failed to find a destination or use for it. Min value 1000";
/*  480 */     golemIgnoreDelay = golIgDel.getInt();
/*  481 */     if (golemIgnoreDelay < 1000) { golemIgnoreDelay = 1000;
/*      */     }
/*  483 */     Property golLinkQual = config.get("general", "golem_link_quality", golemLinkQuality);
/*      */     
/*  485 */     golLinkQual.comment = "The fx quality of the line connecting golems to marked blocks. Setting this below 4 deactives the effect entirely.";
/*  486 */     golemLinkQuality = golLinkQual.getInt();
/*  487 */     if (golemLinkQuality < 4) { golemLinkQuality = 0;
/*      */     }
/*  489 */     Property notDel = config.get("general", "notification_delay", notificationDelay);
/*      */     
/*  491 */     notDel.comment = "Determines how fast notifications scroll downwards.";
/*  492 */     notificationDelay = notDel.getInt();
/*      */     
/*  494 */     Property notMax = config.get("general", "notification_max", notificationMax);
/*  495 */     notMax.comment = "The maximum amount of notifications that are displayed onscreen.";
/*  496 */     notificationMax = notMax.getInt();
/*      */     
/*  498 */     Property nodRare = config.get("general", "node_rarity", nodeRarity);
/*  499 */     nodRare.comment = "How rare nodes are in the world. The number means there will be (on average) one node per N chunks.";
/*  500 */     nodeRarity = nodRare.getInt();
/*      */     
/*  502 */     Property nodSpec = config.get("general", "special_node_rarity", specialNodeRarity);
/*      */     
/*  504 */     nodSpec.comment = "The chance of a node being special (pure, dark, unstable, etc.). The number means roughly 1 in N nodes will be special, so setting the number to 5 will mean 1 in 5 nodes may be special.";
/*  505 */     specialNodeRarity = nodSpec.getInt();
/*  506 */     if (specialNodeRarity < 3) {
/*  507 */       specialNodeRarity = 3;
/*      */     }
/*  509 */     Property showtags = config.get("general", "display_aspects", false);
/*      */     
/*  511 */     showtags.comment = "Item aspects are hidden by default and pressing shift reveals them. Changing this setting to 'true' will reverse this behaviour and always display aspects unless shift is pressed.";
/*  512 */     showTags = showtags.getBoolean(false);
/*      */     
/*  514 */     Property cheatsheet = config.get("general", "allow_cheat_sheet", false);
/*  515 */     cheatsheet.comment = "Enables a version of the Thauminomicon in creative mode that grants you all the research when you first use it.";
/*  516 */     CallowCheatSheet = allowCheatSheet = cheatsheet.getBoolean(false);
/*      */     
/*  518 */     Property wardstone = config.get("general", "allow_warded_stone", true);
/*  519 */     wardstone.comment = "If set to false, warded stone, doors and glass will just be cosmetic in nature and not have its hardened properties (everyone will be able to break it with equal ease).";
/*  520 */     CwardedStone = wardedStone = wardstone.getBoolean(false);
/*      */     
/*      */ 
/*      */ 
/*  524 */     Property wiz_vil = config.get("general", "thaumcraft_villager_id", ConfigEntities.entWizardId);
/*      */     
/*  526 */     wiz_vil.comment = "Thaumcraft wizard villager id";
/*  527 */     ConfigEntities.entWizardId = wiz_vil.getInt();
/*      */     
/*  529 */     Property bank_vil = config.get("general", "thaumcraft_banker_id", ConfigEntities.entBankerId);
/*      */     
/*  531 */     bank_vil.comment = "Thaumcraft banker villager id";
/*  532 */     ConfigEntities.entBankerId = bank_vil.getInt();
/*      */     
/*  534 */     Property gci = config.get("general", "golem_chest_interact", true);
/*      */     
/*  536 */     gci.comment = "If set to true golems will attempt to play the chest opening animations and sounds whenever they interact with them.";
/*  537 */     golemChestInteract = gci.getBoolean(false);
/*      */     
/*  539 */     Property phblacklist = config.get("general", "portablehole_blacklist", "iron_door");
/*      */     
/*  541 */     phblacklist.comment = "This is a comma-delimited list of any block names the portable hole is not allowed to pass through.";
/*  542 */     String[] phbl = phblacklist.getString().split(",");
/*  543 */     if ((phbl != null) && (phbl.length > 0)) {
/*  544 */       for (String s : phbl) {
/*      */         try {
/*  546 */           Block b = Block.func_149684_b(s);
/*  547 */           if ((b != null) && (b != Blocks.field_150350_a)) {
/*  548 */             ThaumcraftApi.portableHoleBlackList.add(b);
/*      */           }
/*      */         }
/*      */         catch (Exception e) {}
/*      */       }
/*      */     }
/*  554 */     Property blueb = config.get("general", "blue_magical_forest", blueBiome);
/*      */     
/*  556 */     blueb.comment = "Set this to true to get the old blue magical forest back.";
/*  557 */     blueBiome = blueb.getBoolean(false);
/*      */     
/*  559 */     Property bff = config.get("general", "biome_taint_from_flux", taintFromFlux);
/*      */     
/*  561 */     bff.comment = "Can Taint be caused by flux effects.";
/*  562 */     taintFromFlux = bff.getBoolean(true);
/*      */     
/*  564 */     Property ts = config.get("general", "biome_taint_spread", taintSpreadRate);
/*      */     
/*  566 */     ts.comment = "The chance per block update (1 in n) of the Taint biome spreading. Setting it to 0 prevents the spread of Taint biomes.";
/*  567 */     taintSpreadRate = ts.getInt();
/*      */     
/*  569 */     Property glowT = config.get("general", "glowing_taint", glowyTaint);
/*      */     
/*  571 */     glowT.comment = "Setting this to false will remove the glowing purple nodules from taint fibres. This might prevent crashes some people experience and improve performance.";
/*  572 */     glowyTaint = glowT.getBoolean(true);
/*      */     
/*      */ 
/*  575 */     Property rss = config.get("Runic_Shielding", "runic_recharge_speed", shieldRecharge);
/*  576 */     rss.comment = "How many milliseconds pass between runic shielding recharge ticks. Lower values equals faster recharge. Minimum of 500.";
/*  577 */     shieldRecharge = Math.max(500, rss.getInt());
/*  578 */     Property rsd = config.get("Runic_Shielding", "runic_recharge_delay", shieldWait);
/*  579 */     rsd.comment = "How many game ticks pass after a shield has been reduced to zero before it can start recharging again. Minimum of 0.";
/*  580 */     shieldWait = Math.max(0, rsd.getInt());
/*  581 */     Property rsc = config.get("Runic_Shielding", "runic_cost", shieldCost);
/*  582 */     rsc.comment = "How much aer and terra centi-vis (0.01 vis) it costs to reacharge a single unit of shielding. Minimum of 0.";
/*  583 */     shieldCost = Math.max(0, rsc.getInt());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*  588 */   public static Enchantment enchHaste = null;
/*  589 */   public static Enchantment enchRepair = null;
/*      */   
/*      */   public static void initLoot()
/*      */   {
/*  593 */     Random rand = new Random(System.currentTimeMillis());
/*  594 */     ItemStack amulet = new ItemStack(ConfigItems.itemAmuletVis, 1, 0);
/*  595 */     ItemAmuletVis ai = (ItemAmuletVis)amulet.func_77973_b();
/*  596 */     for (Aspect a : Aspect.getPrimalAspects()) {
/*  597 */       ai.addVis(amulet, a, rand.nextInt(5), true);
/*      */     }
/*      */     
/*  600 */     ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemResource, 1, 18), 2500, new int[] { 0 });
/*  601 */     ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemResource, 2, 18), 2250, new int[] { 1 });
/*  602 */     ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemResource, 3, 18), 2000, new int[] { 2 });
/*  603 */     ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemEldritchObject, 1, 3), 1, new int[] { 2 });
/*  604 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151156_bN), 1, new int[] { 2 });
/*  605 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151045_i), 10, new int[] { 0 });
/*  606 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151045_i), 50, new int[] { 1, 2 });
/*  607 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151166_bC), 15, new int[] { 0 });
/*  608 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151166_bC), 75, new int[] { 1, 2 });
/*  609 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151043_k), 100, new int[] { 0, 1, 2 });
/*  610 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151079_bi), 100, new int[] { 0, 1, 2 });
/*  611 */     ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemResource, 1, 9), 25, new int[] { 0, 1, 2 });
/*  612 */     ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemBaubleBlanks, 1, 0), 10, new int[] { 0 });
/*  613 */     ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1), 10, new int[] { 0 });
/*  614 */     ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemBaubleBlanks, 1, 2), 10, new int[] { 0 });
/*  615 */     for (int a = 3; a <= 8; a++) {
/*  616 */       ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemBaubleBlanks, 1, a), 5, new int[] { 1 });
/*  617 */       ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemBaubleBlanks, 1, a), 7, new int[] { 2 });
/*      */     }
/*  619 */     ThaumcraftApi.addLootBagItem(amulet.func_77946_l(), 6, new int[] { 1, 2 });
/*  620 */     ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemRingRunic, 1, 0), 5, new int[] { 1, 2 });
/*  621 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 5, new int[] { 0 });
/*  622 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 10, new int[] { 1 });
/*  623 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 20, new int[] { 2 });
/*  624 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 1, new int[] { 0 });
/*  625 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 2, new int[] { 1 });
/*  626 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 3, new int[] { 2 });
/*  627 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 3, new int[] { 0 });
/*  628 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 6, new int[] { 1 });
/*  629 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 9, new int[] { 2 });
/*  630 */     ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151122_aG), 10, new int[] { 0, 1, 2 });
/*      */     
/*  632 */     for (int i = 0; i <= 15; i++) {
/*  633 */       for (int j = 0; j <= 1; j++) {
/*      */         int k;
/*      */         int k;
/*  636 */         if (j == 0) k = i | 0x2000; else k = i | 0x4000;
/*  637 */         for (int l = 0; l <= 2; l++)
/*      */         {
/*  639 */           int i1 = k;
/*  640 */           if (l != 0) if (l == 1) i1 = k | 0x20; else if (l == 2) i1 = k | 0x40;
/*  641 */           List list1 = PotionHelper.func_77917_b(i1, false);
/*  642 */           if ((list1 != null) && (!list1.isEmpty()))
/*      */           {
/*  644 */             ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151068_bn, 1, i1), l + 1, new int[] { 0, 1, 2 });
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  650 */     ItemStack[] commonLoot = { new ItemStack(ConfigItems.itemLootbag, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 6) };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  655 */     ItemStack[] uncommonLoot = { new ItemStack(ConfigItems.itemLootbag, 1, 1), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 0), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 9) };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  662 */     ItemStack[] rareLoot = { new ItemStack(ConfigItems.itemLootbag, 1, 2), new ItemStack(ConfigItems.itemThaumonomicon), new ItemStack(ConfigItems.itemSwordThaumium, 1, 0), new ItemStack(ConfigItems.itemPickThaumium, 1, 0), new ItemStack(ConfigItems.itemAxeThaumium, 1, 0), new ItemStack(ConfigItems.itemHoeThaumium, 1, 0), new ItemStack(ConfigItems.itemRingRunic, 1, 0), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 3), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 4), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 5), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 6), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 7), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 8), amulet };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  677 */     for (ItemStack is : commonLoot) {
/*  678 */       ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(is, 1, 3, 5));
/*      */       
/*  680 */       ChestGenHooks.addItem("pyramidJungleChest", new WeightedRandomChestContent(is, 1, 3, 5));
/*      */       
/*  682 */       ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(is, 1, 3, 5));
/*      */       
/*  684 */       ChestGenHooks.addItem("mineshaftCorridor", new WeightedRandomChestContent(is, 1, 3, 4));
/*      */       
/*  686 */       ChestGenHooks.addItem("strongholdCorridor", new WeightedRandomChestContent(is, 1, 3, 4));
/*      */       
/*  688 */       ChestGenHooks.addItem("strongholdCrossing", new WeightedRandomChestContent(is, 1, 3, 4));
/*      */       
/*  690 */       ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(is, 1, 3, 4));
/*      */     }
/*      */     
/*      */ 
/*  694 */     for (ItemStack is : uncommonLoot) {
/*  695 */       ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(is, 1, 2, 4));
/*      */       
/*  697 */       ChestGenHooks.addItem("pyramidJungleChest", new WeightedRandomChestContent(is, 1, 2, 4));
/*      */       
/*  699 */       ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(is, 1, 2, 4));
/*      */       
/*  701 */       ChestGenHooks.addItem("mineshaftCorridor", new WeightedRandomChestContent(is, 1, 2, 3));
/*      */       
/*  703 */       ChestGenHooks.addItem("strongholdCorridor", new WeightedRandomChestContent(is, 1, 2, 3));
/*      */       
/*  705 */       ChestGenHooks.addItem("strongholdCrossing", new WeightedRandomChestContent(is, 1, 2, 3));
/*      */       
/*  707 */       ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(is, 1, 2, 3));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  712 */     ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(new ItemStack(ConfigItems.itemResource, 1, 9), 3, 6, 20));
/*      */     
/*      */ 
/*      */ 
/*  716 */     for (ItemStack is : rareLoot) {
/*  717 */       ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(is, 1, 1, 1));
/*      */       
/*  719 */       ChestGenHooks.addItem("pyramidJungleChest", new WeightedRandomChestContent(is, 1, 1, 1));
/*      */       
/*  721 */       ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(is, 1, 1, 1));
/*      */       
/*  723 */       ChestGenHooks.addItem("mineshaftCorridor", new WeightedRandomChestContent(is, 1, 1, 1));
/*      */       
/*  725 */       ChestGenHooks.addItem("strongholdCorridor", new WeightedRandomChestContent(is, 1, 1, 1));
/*      */       
/*  727 */       ChestGenHooks.addItem("strongholdCrossing", new WeightedRandomChestContent(is, 1, 1, 1));
/*      */       
/*  729 */       ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(is, 1, 1, 1));
/*      */     }
/*      */     
/*      */ 
/*  733 */     ChestGenHooks.addItem("villageBlacksmith", new WeightedRandomChestContent(new ItemStack(ConfigItems.itemResource, 1, 2), 1, 3, 10));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void initModCompatibility()
/*      */   {
/*  740 */     String[] ores = OreDictionary.getOreNames();
/*      */     boolean first;
/*  742 */     for (String ore : ores) {
/*  743 */       if (ore != null)
/*      */       {
/*  745 */         if ((ore.equals("oreCopper")) && (OreDictionary.getOres(ore).size() > 0))
/*      */         {
/*  747 */           foundCopperOre = true;
/*  748 */           for (ItemStack is : OreDictionary.getOres(ore))
/*  749 */             Utils.addSpecialMiningResult(is, new ItemStack(ConfigItems.itemNugget, 1, 17), 1.0F);
/*      */         }
/*  751 */         if ((ore.equals("oreTin")) && (OreDictionary.getOres(ore).size() > 0))
/*      */         {
/*  753 */           foundTinOre = true;
/*  754 */           for (ItemStack is : OreDictionary.getOres(ore))
/*  755 */             Utils.addSpecialMiningResult(is, new ItemStack(ConfigItems.itemNugget, 1, 18), 1.0F);
/*      */         }
/*  757 */         if ((ore.equals("oreSilver")) && (OreDictionary.getOres(ore).size() > 0))
/*      */         {
/*  759 */           foundSilverOre = true;
/*  760 */           for (ItemStack is : OreDictionary.getOres(ore))
/*  761 */             Utils.addSpecialMiningResult(is, new ItemStack(ConfigItems.itemNugget, 1, 19), 1.0F);
/*      */         }
/*  763 */         if ((ore.equals("oreLead")) && (OreDictionary.getOres(ore).size() > 0))
/*      */         {
/*  765 */           foundLeadOre = true;
/*  766 */           for (ItemStack is : OreDictionary.getOres(ore))
/*  767 */             Utils.addSpecialMiningResult(is, new ItemStack(ConfigItems.itemNugget, 1, 20), 1.0F);
/*      */         }
/*      */         boolean first;
/*  770 */         if (ore.equals("ingotCopper")) {
/*  771 */           first = true;
/*  772 */           for (ItemStack is : OreDictionary.getOres(ore)) {
/*  773 */             if (is.field_77994_a > 1)
/*  774 */               is.field_77994_a = 1;
/*  775 */             foundCopperIngot = true;
/*  776 */             CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 1), new Object[] { "#", Character.valueOf('#'), is });
/*      */             
/*      */ 
/*      */ 
/*  780 */             if (first) {
/*  781 */               first = false;
/*  782 */               FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemNugget, 1, 17), new ItemStack(is.func_77973_b(), 2, is.func_77960_j()), 1.0F);
/*      */               
/*      */ 
/*      */ 
/*  786 */               ConfigRecipes.oreDictRecipe(is, new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 1) });
/*      */             }
/*      */           }
/*      */         } else {
/*      */           boolean first;
/*  791 */           if (ore.equals("ingotTin")) {
/*  792 */             first = true;
/*  793 */             for (ItemStack is : OreDictionary.getOres(ore)) {
/*  794 */               if (is.field_77994_a > 1)
/*  795 */                 is.field_77994_a = 1;
/*  796 */               foundTinIngot = true;
/*  797 */               CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 2), new Object[] { "#", Character.valueOf('#'), is });
/*      */               
/*      */ 
/*  800 */               if (first) {
/*  801 */                 first = false;
/*  802 */                 FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemNugget, 1, 18), new ItemStack(is.func_77973_b(), 2, is.func_77960_j()), 1.0F);
/*      */                 
/*      */ 
/*      */ 
/*  806 */                 ConfigRecipes.oreDictRecipe(is, new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 2) });
/*      */               }
/*      */             }
/*      */           } else {
/*      */             boolean first;
/*  811 */             if (ore.equals("ingotSilver")) {
/*  812 */               first = true;
/*  813 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/*  814 */                 if (is.field_77994_a > 1)
/*  815 */                   is.field_77994_a = 1;
/*  816 */                 foundSilverIngot = true;
/*  817 */                 CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 3), new Object[] { "#", Character.valueOf('#'), is });
/*      */                 
/*      */ 
/*  820 */                 if (first) {
/*  821 */                   first = false;
/*  822 */                   FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemNugget, 1, 19), new ItemStack(is.func_77973_b(), 2, is.func_77960_j()), 1.0F);
/*      */                   
/*      */ 
/*      */ 
/*  826 */                   ConfigRecipes.oreDictRecipe(is, new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 3) });
/*      */                 }
/*      */                 
/*      */               }
/*      */             }
/*  831 */             else if ((ore.equals("oreUranium")) || (ore.equals("itemDropUranium")) || (ore.equals("ingotUranium")))
/*      */             {
/*      */ 
/*  834 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/*  835 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 2).add(Aspect.POISON, 2).add(Aspect.ENERGY, 2));
/*      */               }
/*      */               
/*      */ 
/*      */             }
/*  840 */             else if ((ore.equals("ingotBrass")) || (ore.equals("ingotBronze"))) {
/*  841 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/*  842 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 3).add(Aspect.TOOL, 1));
/*      */               }
/*      */               
/*      */             }
/*  846 */             else if ((ore.equals("dustBrass")) || (ore.equals("dustBronze"))) {
/*  847 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/*  848 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.TOOL, 1));
/*      */ 
/*      */               }
/*      */               
/*      */ 
/*      */             }
/*  854 */             else if ((ore.equals("gemRuby")) || (ore.equals("gemGreenSapphire")) || (ore.equals("gemSapphire")))
/*      */             {
/*  856 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/*  857 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.GREED, 2));
/*      */               }
/*      */               
/*      */             }
/*  861 */             else if (ore.equals("woodRubber")) {
/*  862 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/*  863 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.TREE, 3).add(Aspect.TOOL, 1));
/*      */               }
/*      */               
/*      */             }
/*  867 */             else if (ore.equals("itemRubber")) {
/*  868 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/*  869 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.MOTION, 2).add(Aspect.TOOL, 2));
/*      */               }
/*      */               
/*      */             }
/*  873 */             else if (ore.equals("ingotSteel")) {
/*  874 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/*  875 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 1));
/*      */               }
/*      */               
/*      */             }
/*  879 */             else if (ore.equals("crystalQuartz")) {
/*  880 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/*  881 */                 ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.ENERGY, 1));
/*      */               }
/*      */               
/*      */             }
/*  885 */             else if (ore.equals("woodLog")) {
/*  886 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/*  887 */                 ItemElementalAxe.oreDictLogs.add(Arrays.asList(new Integer[] { Integer.valueOf(Item.func_150891_b(is.func_77973_b())), Integer.valueOf(is.func_77960_j()) }));
/*      */               }
/*      */               
/*      */             }
/*  891 */             else if (ore.equals("ingotLead")) {
/*  892 */               first = true;
/*  893 */               for (ItemStack is : OreDictionary.getOres(ore)) {
/*  894 */                 if (is.field_77994_a > 1)
/*  895 */                   is.field_77994_a = 1;
/*  896 */                 foundLeadIngot = true;
/*  897 */                 CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 4), new Object[] { "#", Character.valueOf('#'), is });
/*      */                 
/*      */ 
/*  900 */                 if (first) {
/*  901 */                   first = false;
/*  902 */                   FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemNugget, 1, 20), new ItemStack(is.func_77973_b(), 2, is.func_77960_j()), 1.0F);
/*      */                   
/*      */ 
/*      */ 
/*  906 */                   ConfigRecipes.oreDictRecipe(is, new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 4) });
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  914 */     Thaumcraft.log.info("Adding entities to MFR safari net blacklist.");
/*  915 */     registerSafariNetBlacklist(EntityGolemBase.class);
/*  916 */     registerSafariNetBlacklist(EntityTravelingTrunk.class);
/*  917 */     registerSafariNetBlacklist(EntityAspectOrb.class);
/*  918 */     registerSafariNetBlacklist(EntityFallingTaint.class);
/*  919 */     registerSafariNetBlacklist(EntityWisp.class);
/*  920 */     registerSafariNetBlacklist(EntityPech.class);
/*  921 */     registerSafariNetBlacklist(EntityTaintSpore.class);
/*  922 */     registerSafariNetBlacklist(EntityEldritchGuardian.class);
/*  923 */     registerSafariNetBlacklist(EntityEldritchWarden.class);
/*  924 */     registerSafariNetBlacklist(EntityEldritchGolem.class);
/*  925 */     registerSafariNetBlacklist(EntityCultistCleric.class);
/*  926 */     registerSafariNetBlacklist(EntityCultistKnight.class);
/*  927 */     registerSafariNetBlacklist(EntityCultistLeader.class);
/*  928 */     registerSafariNetBlacklist(EntityCultistPortal.class);
/*  929 */     registerSafariNetBlacklist(EntityEldritchCrab.class);
/*  930 */     registerSafariNetBlacklist(EntityInhabitedZombie.class);
/*      */   }
/*      */   
/*      */   public static void registerSafariNetBlacklist(Class<?> blacklistedEntity)
/*      */   {
/*      */     try {
/*  936 */       Class<?> registry = Class.forName("powercrystals.minefactoryreloaded.MFRRegistry");
/*  937 */       if (registry != null) {
/*  938 */         Method reg = registry.getMethod("registerSafariNetBlacklist", new Class[] { Class.class });
/*  939 */         reg.invoke(registry, new Object[] { blacklistedEntity });
/*      */       }
/*      */     }
/*      */     catch (Exception e) {}
/*      */   }
/*      */   
/*      */   public static void registerBiomes()
/*      */   {
/*  947 */     BiomeDictionary.registerBiomeType(ThaumcraftWorldGenerator.biomeTaint, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WASTELAND });
/*      */     
/*  949 */     BiomeDictionary.registerBiomeType(ThaumcraftWorldGenerator.biomeEerie, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY });
/*      */     
/*  951 */     BiomeDictionary.registerBiomeType(ThaumcraftWorldGenerator.biomeEldritchLands, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.END });
/*      */     
/*  953 */     BiomeDictionary.registerBiomeType(ThaumcraftWorldGenerator.biomeMagicalForest, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.FOREST });
/*      */     
/*      */ 
/*      */ 
/*  957 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.WATER, 100, Aspect.WATER, false, 0.0F);
/*  958 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.OCEAN, 120, Aspect.WATER, false, 0.0F);
/*  959 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.RIVER, 100, Aspect.WATER, false, 0.0F);
/*  960 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.WET, 80, Aspect.WATER, false, 0.0F);
/*      */     
/*  962 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.HOT, 100, Aspect.FIRE, false, 0.0F);
/*  963 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.DESERT, 100, Aspect.FIRE, false, 0.0F);
/*  964 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.NETHER, 120, Aspect.FIRE, false, 0.0F);
/*  965 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.MESA, 80, Aspect.FIRE, false, 0.0F);
/*      */     
/*  967 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.DENSE, 100, Aspect.ORDER, false, 0.0F);
/*  968 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SNOWY, 80, Aspect.ORDER, false, 0.0F);
/*  969 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.COLD, 80, Aspect.ORDER, false, 0.0F);
/*  970 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.FROZEN, 100, Aspect.ORDER, false, 0.0F);
/*  971 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.MUSHROOM, 140, Aspect.ORDER, false, 0.0F);
/*      */     
/*  973 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.CONIFEROUS, 100, Aspect.EARTH, true, 0.2F);
/*  974 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.FOREST, 120, Aspect.EARTH, true, 1.0F);
/*  975 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SANDY, 80, Aspect.EARTH, false, 0.0F);
/*  976 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.BEACH, 80, Aspect.EARTH, false, 0.0F);
/*      */     
/*  978 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SAVANNA, 80, Aspect.AIR, true, 0.2F);
/*  979 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.MOUNTAIN, 100, Aspect.AIR, false, 0.0F);
/*  980 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.HILLS, 120, Aspect.AIR, false, 0.0F);
/*  981 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.PLAINS, 80, Aspect.AIR, true, 0.2F);
/*      */     
/*  983 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.DRY, 80, Aspect.ENTROPY, false, 0.0F);
/*  984 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SPARSE, 80, Aspect.ENTROPY, false, 0.0F);
/*  985 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SWAMP, 120, Aspect.ENTROPY, true, 0.2F);
/*  986 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.WASTELAND, 80, Aspect.ENTROPY, false, 0.0F);
/*      */     
/*  988 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.JUNGLE, 100, Aspect.PLANT, false, 0.0F);
/*  989 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.LUSH, 100, Aspect.PLANT, true, 0.5F);
/*  990 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.MAGICAL, 100, null, true, 1.0F);
/*  991 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.END, 80, Aspect.VOID, false, 0.0F);
/*  992 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SPOOKY, 80, Aspect.SOUL, false, 0.0F);
/*  993 */     BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.DEAD, 50, Aspect.DEATH, false, 0.0F);
/*      */   }
/*      */   
/*      */   public static void initMisc()
/*      */   {
/*  998 */     CropUtils.addStandardCrop(new ItemStack(Blocks.field_150440_ba), 32767);
/*  999 */     CropUtils.addStandardCrop(new ItemStack(Blocks.field_150423_aK), 32767);
/* 1000 */     CropUtils.addStandardCrop(new ItemStack(ConfigBlocks.blockManaPod), 7);
/* 1001 */     CropUtils.addStackedCrop(Blocks.field_150436_aH, 32767);
/* 1002 */     CropUtils.addStackedCrop(Blocks.field_150434_aF, 32767);
/*      */     
/* 1004 */     Utils.addSpecialMiningResult(new ItemStack(Blocks.field_150366_p), new ItemStack(ConfigItems.itemNugget, 1, 16), 1.0F);
/* 1005 */     Utils.addSpecialMiningResult(new ItemStack(Blocks.field_150352_o), new ItemStack(ConfigItems.itemNugget, 1, 31), 0.9F);
/* 1006 */     Utils.addSpecialMiningResult(new ItemStack(ConfigBlocks.blockCustomOre, 1, 0), new ItemStack(ConfigItems.itemNugget, 1, 21), 0.9F);
/*      */     
/*      */ 
/* 1009 */     Collection<Aspect> pa = Aspect.aspects.values();
/* 1010 */     for (Aspect aspect : pa) {
/* 1011 */       aspectOrder.add(aspect);
/*      */     }
/*      */   }
/*      */   
/* 1015 */   public static ArrayList<Aspect> aspectOrder = new ArrayList();
/*      */   
/*      */ 
/* 1018 */   public static boolean foundCopperIngot = false;
/* 1019 */   public static boolean foundTinIngot = false;
/* 1020 */   public static boolean foundSilverIngot = false;
/* 1021 */   public static boolean foundLeadIngot = false;
/* 1022 */   public static boolean foundCopperOre = false;
/* 1023 */   public static boolean foundTinOre = false;
/* 1024 */   public static boolean foundSilverOre = false;
/* 1025 */   public static boolean foundLeadOre = false;
/*      */   
/* 1027 */   public static final Material airyMaterial = new MaterialAiry(MapColor.field_151660_b);
/*      */   
/* 1029 */   public static final Material fluxGoomaterial = new MaterialTaint(MapColor.field_151661_c).func_76219_n();
/*      */   
/* 1031 */   public static final Material taintMaterial = new MaterialTaint(MapColor.field_151661_c);
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/config/Config.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */