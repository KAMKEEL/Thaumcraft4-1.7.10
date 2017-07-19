/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.wands.StaffRod;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.common.entities.golems.ItemGolemBell;
/*     */ import thaumcraft.common.entities.golems.ItemTrunkSpawner;
/*     */ import thaumcraft.common.items.ItemBottleTaint;
/*     */ import thaumcraft.common.items.ItemKey;
/*     */ import thaumcraft.common.items.ItemNuggetEdible;
/*     */ import thaumcraft.common.items.ItemZombieBrain;
/*     */ import thaumcraft.common.items.armor.ItemCultistLeaderArmor;
/*     */ import thaumcraft.common.items.armor.ItemCultistPlateArmor;
/*     */ import thaumcraft.common.items.armor.ItemCultistRobeArmor;
/*     */ import thaumcraft.common.items.armor.ItemFortressArmor;
/*     */ import thaumcraft.common.items.armor.ItemRobeArmor;
/*     */ import thaumcraft.common.items.armor.ItemThaumiumArmor;
/*     */ import thaumcraft.common.items.armor.ItemVoidArmor;
/*     */ import thaumcraft.common.items.armor.ItemVoidRobeArmor;
/*     */ import thaumcraft.common.items.equipment.ItemPrimalArrow;
/*     */ import thaumcraft.common.items.equipment.ItemPrimalCrusher;
/*     */ import thaumcraft.common.items.equipment.ItemThaumiumShovel;
/*     */ import thaumcraft.common.items.equipment.ItemThaumiumSword;
/*     */ import thaumcraft.common.items.equipment.ItemVoidShovel;
/*     */ import thaumcraft.common.items.relics.ItemHandMirror;
/*     */ import thaumcraft.common.items.wands.WandRodPrimalOnUpdate;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusPech;
/*     */ 
/*     */ public class ConfigItems
/*     */ {
/*     */   public static WandCap WAND_CAP_IRON;
/*     */   public static WandCap WAND_CAP_COPPER;
/*     */   public static WandCap WAND_CAP_GOLD;
/*     */   public static WandCap WAND_CAP_SILVER;
/*     */   public static WandCap WAND_CAP_THAUMIUM;
/*     */   public static WandCap WAND_CAP_VOID;
/*     */   public static WandRod WAND_ROD_WOOD;
/*     */   public static WandRod WAND_ROD_GREATWOOD;
/*     */   public static WandRod WAND_ROD_OBSIDIAN;
/*     */   public static WandRod WAND_ROD_BLAZE;
/*     */   public static WandRod WAND_ROD_ICE;
/*     */   public static WandRod WAND_ROD_QUARTZ;
/*     */   public static WandRod WAND_ROD_BONE;
/*     */   public static WandRod WAND_ROD_REED;
/*     */   public static WandRod WAND_ROD_SILVERWOOD;
/*     */   public static StaffRod STAFF_ROD_GREATWOOD;
/*     */   public static StaffRod STAFF_ROD_OBSIDIAN;
/*     */   public static StaffRod STAFF_ROD_BLAZE;
/*     */   public static StaffRod STAFF_ROD_ICE;
/*     */   public static StaffRod STAFF_ROD_QUARTZ;
/*     */   public static StaffRod STAFF_ROD_BONE;
/*     */   public static StaffRod STAFF_ROD_REED;
/*     */   public static StaffRod STAFF_ROD_SILVERWOOD;
/*     */   public static StaffRod STAFF_ROD_PRIMAL;
/*     */   public static Item itemWandCasting;
/*     */   public static Item itemWandCap;
/*     */   public static Item itemWandRod;
/*     */   public static Item itemFocusPouch;
/*     */   public static Item itemFocusFire;
/*     */   public static Item itemFocusFrost;
/*     */   public static Item itemFocusShock;
/*     */   public static Item itemFocusTrade;
/*     */   public static Item itemFocusExcavation;
/*     */   public static Item itemFocusHellbat;
/*     */   public static Item itemFocusWarding;
/*     */   public static Item itemFocusPrimal;
/*     */   public static Item itemEssence;
/*     */   public static Item itemWispEssence;
/*     */   public static Item itemCrystalEssence;
/*     */   public static Item itemResource;
/*     */   public static Item itemShard;
/*     */   public static Item itemNugget;
/*     */   public static Item itemNuggetChicken;
/*     */   public static Item itemNuggetBeef;
/*     */   public static Item itemNuggetPork;
/*     */   public static Item itemNuggetFish;
/*     */   public static Item itemTripleMeatTreat;
/*     */   public static Item itemPhotoPlate;
/*     */   public static Item itemManaBean;
/*     */   public static Item itemZombieBrain;
/*     */   public static Item itemResearchNotes;
/*     */   public static Item itemInkwell;
/*     */   public static Item itemThaumonomicon;
/*     */   public static Item itemSpawnerEgg;
/*     */   public static Item itemFocusPortableHole;
/*     */   public static Item itemFocusPech;
/*     */   public static Item itemThaumometer;
/*     */   public static Item itemFlyingCarpet;
/*     */   public static Item itemGolemPlacer;
/*     */   public static Item itemGolemBell;
/*     */   public static Item itemGolemDecoration;
/*     */   public static Item itemGolemCore;
/*     */   public static Item itemGolemUpgrade;
/*     */   public static Item itemArcaneDoor;
/*     */   public static Item itemJarFilled;
/*     */   public static Item itemJarNode;
/*     */   public static Item itemKey;
/*     */   
/*     */   public static void init()
/*     */   {
/* 110 */     initializeItems();
/*     */     
/*     */ 
/* 113 */     OreDictionary.registerOre("oreCinnabar", new ItemStack(ConfigBlocks.blockCustomOre, 1, 0));
/* 114 */     OreDictionary.registerOre("oreInfusedAir", new ItemStack(ConfigBlocks.blockCustomOre, 1, 1));
/* 115 */     OreDictionary.registerOre("oreInfusedFire", new ItemStack(ConfigBlocks.blockCustomOre, 1, 2));
/* 116 */     OreDictionary.registerOre("oreInfusedWater", new ItemStack(ConfigBlocks.blockCustomOre, 1, 3));
/* 117 */     OreDictionary.registerOre("oreInfusedEarth", new ItemStack(ConfigBlocks.blockCustomOre, 1, 4));
/* 118 */     OreDictionary.registerOre("oreInfusedOrder", new ItemStack(ConfigBlocks.blockCustomOre, 1, 5));
/* 119 */     OreDictionary.registerOre("oreInfusedEntropy", new ItemStack(ConfigBlocks.blockCustomOre, 1, 6));
/* 120 */     OreDictionary.registerOre("shardAir", new ItemStack(itemShard, 1, 0));
/* 121 */     OreDictionary.registerOre("shardFire", new ItemStack(itemShard, 1, 1));
/* 122 */     OreDictionary.registerOre("shardWater", new ItemStack(itemShard, 1, 2));
/* 123 */     OreDictionary.registerOre("shardEarth", new ItemStack(itemShard, 1, 3));
/* 124 */     OreDictionary.registerOre("shardOrder", new ItemStack(itemShard, 1, 4));
/* 125 */     OreDictionary.registerOre("shardEntropy", new ItemStack(itemShard, 1, 5));
/* 126 */     OreDictionary.registerOre("oreAmber", new ItemStack(ConfigBlocks.blockCustomOre, 1, 7));
/* 127 */     OreDictionary.registerOre("quicksilver", new ItemStack(itemResource, 1, 3));
/* 128 */     OreDictionary.registerOre("gemAmber", new ItemStack(itemResource, 1, 6));
/* 129 */     OreDictionary.registerOre("nuggetIron", new ItemStack(itemNugget, 1, 0));
/* 130 */     OreDictionary.registerOre("nuggetCopper", new ItemStack(itemNugget, 1, 1));
/* 131 */     OreDictionary.registerOre("nuggetTin", new ItemStack(itemNugget, 1, 2));
/* 132 */     OreDictionary.registerOre("nuggetSilver", new ItemStack(itemNugget, 1, 3));
/* 133 */     OreDictionary.registerOre("nuggetLead", new ItemStack(itemNugget, 1, 4));
/* 134 */     OreDictionary.registerOre("nuggetThaumium", new ItemStack(itemNugget, 1, 6));
/* 135 */     OreDictionary.registerOre("nuggetVoid", new ItemStack(itemNugget, 1, 7));
/* 136 */     OreDictionary.registerOre("nuggetQuicksilver", new ItemStack(itemResource, 1, 3));
/* 137 */     OreDictionary.registerOre("nuggetGold", new ItemStack(itemResource, 1, 18));
/* 138 */     OreDictionary.registerOre("ingotThaumium", new ItemStack(itemResource, 1, 2));
/* 139 */     OreDictionary.registerOre("ingotVoid", new ItemStack(itemResource, 1, 16));
/* 140 */     OreDictionary.registerOre("clusterIron", new ItemStack(itemNugget, 1, 16));
/* 141 */     OreDictionary.registerOre("clusterCopper", new ItemStack(itemNugget, 1, 17));
/* 142 */     OreDictionary.registerOre("clusterTin", new ItemStack(itemNugget, 1, 18));
/* 143 */     OreDictionary.registerOre("clusterSilver", new ItemStack(itemNugget, 1, 19));
/* 144 */     OreDictionary.registerOre("clusterLead", new ItemStack(itemNugget, 1, 20));
/* 145 */     OreDictionary.registerOre("clusterCinnabar", new ItemStack(itemNugget, 1, 21));
/* 146 */     OreDictionary.registerOre("clusterGold", new ItemStack(itemNugget, 1, 31));
/* 147 */     OreDictionary.registerOre("logWood", new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0));
/* 148 */     OreDictionary.registerOre("logWood", new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1));
/* 149 */     OreDictionary.registerOre("plankWood", new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6));
/* 150 */     OreDictionary.registerOre("plankWood", new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 7));
/* 151 */     OreDictionary.registerOre("slabWood", new ItemStack(ConfigBlocks.blockSlabWood, 1, 0));
/* 152 */     OreDictionary.registerOre("slabWood", new ItemStack(ConfigBlocks.blockSlabWood, 1, 1));
/* 153 */     OreDictionary.registerOre("treeSapling", new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0));
/* 154 */     OreDictionary.registerOre("treeSapling", new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1));
/*     */     
/*     */ 
/* 157 */     WAND_CAP_IRON = new WandCap("iron", 1.1F, new ItemStack(itemWandCap, 1, 0), 1);
/* 158 */     WAND_CAP_GOLD = new WandCap("gold", 1.0F, new ItemStack(itemWandCap, 1, 1), 3);
/* 159 */     WAND_CAP_THAUMIUM = new WandCap("thaumium", 0.9F, new ItemStack(itemWandCap, 1, 2), 6);
/* 160 */     WAND_CAP_VOID = new WandCap("void", 0.8F, new ItemStack(itemWandCap, 1, 7), 9);
/*     */     
/* 162 */     WAND_ROD_WOOD = new WandRod("wood", 25, new ItemStack(Items.field_151055_y), 1);
/* 163 */     WAND_ROD_GREATWOOD = new WandRod("greatwood", 50, new ItemStack(itemWandRod, 1, 0), 3);
/* 164 */     WAND_ROD_OBSIDIAN = new WandRod("obsidian", 75, new ItemStack(itemWandRod, 1, 1), 6, new WandRodPrimalOnUpdate(Aspect.EARTH));
/* 165 */     WAND_ROD_BLAZE = new WandRod("blaze", 75, new ItemStack(itemWandRod, 1, 6), 6, new WandRodPrimalOnUpdate(Aspect.FIRE));
/* 166 */     WAND_ROD_ICE = new WandRod("ice", 75, new ItemStack(itemWandRod, 1, 3), 6, new WandRodPrimalOnUpdate(Aspect.WATER));
/* 167 */     WAND_ROD_QUARTZ = new WandRod("quartz", 75, new ItemStack(itemWandRod, 1, 4), 6, new WandRodPrimalOnUpdate(Aspect.ORDER));
/* 168 */     WAND_ROD_BONE = new WandRod("bone", 75, new ItemStack(itemWandRod, 1, 7), 6, new WandRodPrimalOnUpdate(Aspect.ENTROPY));
/* 169 */     WAND_ROD_REED = new WandRod("reed", 75, new ItemStack(itemWandRod, 1, 5), 6, new WandRodPrimalOnUpdate(Aspect.AIR));
/* 170 */     WAND_ROD_SILVERWOOD = new WandRod("silverwood", 100, new ItemStack(itemWandRod, 1, 2), 9);
/*     */     
/* 172 */     WAND_ROD_BLAZE.setGlowing(true);
/*     */     
/*     */ 
/* 175 */     STAFF_ROD_GREATWOOD = new StaffRod("greatwood", 125, new ItemStack(itemWandRod, 1, 50), 8);
/* 176 */     STAFF_ROD_OBSIDIAN = new StaffRod("obsidian", 175, new ItemStack(itemWandRod, 1, 51), 14, new WandRodPrimalOnUpdate(Aspect.EARTH));
/* 177 */     STAFF_ROD_BLAZE = new StaffRod("blaze", 175, new ItemStack(itemWandRod, 1, 56), 14, new WandRodPrimalOnUpdate(Aspect.FIRE));
/* 178 */     STAFF_ROD_ICE = new StaffRod("ice", 175, new ItemStack(itemWandRod, 1, 53), 14, new WandRodPrimalOnUpdate(Aspect.WATER));
/* 179 */     STAFF_ROD_QUARTZ = new StaffRod("quartz", 175, new ItemStack(itemWandRod, 1, 54), 14, new WandRodPrimalOnUpdate(Aspect.ORDER));
/* 180 */     STAFF_ROD_BONE = new StaffRod("bone", 175, new ItemStack(itemWandRod, 1, 57), 14, new WandRodPrimalOnUpdate(Aspect.ENTROPY));
/* 181 */     STAFF_ROD_REED = new StaffRod("reed", 175, new ItemStack(itemWandRod, 1, 55), 14, new WandRodPrimalOnUpdate(Aspect.AIR));
/* 182 */     STAFF_ROD_SILVERWOOD = new StaffRod("silverwood", 250, new ItemStack(itemWandRod, 1, 52), 24);
/*     */     
/* 184 */     STAFF_ROD_PRIMAL = new StaffRod("primal", 250, new ItemStack(itemWandRod, 1, 100), 32, new WandRodPrimalOnUpdate());
/*     */     
/* 186 */     STAFF_ROD_PRIMAL.setRunes(true);
/* 187 */     STAFF_ROD_BLAZE.setGlowing(true);
/*     */   }
/*     */   
/*     */   public static void postInit()
/*     */   {
/* 192 */     if (Config.foundCopperIngot) WAND_CAP_COPPER = new WandCap("copper", 1.1F, Arrays.asList(new Aspect[] { Aspect.ORDER, Aspect.ENTROPY }), 1.0F, new ItemStack(itemWandCap, 1, 3), 2);
/* 193 */     if (Config.foundSilverIngot) { WAND_CAP_SILVER = new WandCap("silver", 1.0F, Arrays.asList(new Aspect[] { Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER }), 0.95F, new ItemStack(itemWandCap, 1, 4), 4);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static Item itemHandMirror;
/*     */   
/*     */   public static Item itemTrunkSpawner;
/*     */   
/*     */   public static Item itemResonator;
/*     */   
/*     */   public static Item itemBathSalts;
/*     */   
/*     */   public static Item itemBucketDeath;
/*     */   
/*     */   public static Item itemBucketPure;
/*     */   
/*     */   public static Item itemEldritchObject;
/*     */   public static Item itemSanitySoap;
/*     */   public static Item itemSanityChecker;
/*     */   public static Item itemBottleTaint;
/*     */   public static Item itemGoggles;
/*     */   public static Item itemBootsTraveller;
/*     */   public static Item itemHelmetThaumium;
/*     */   public static Item itemChestThaumium;
/*     */   public static Item itemBootsThaumium;
/*     */   public static Item itemLegsThaumium;
/*     */   public static Item itemShovelThaumium;
/*     */   
/*     */   private static void initializeItems()
/*     */   {
/* 224 */     itemWandCasting = new thaumcraft.common.items.wands.ItemWandCasting().func_77655_b("WandCasting");
/* 225 */     GameRegistry.registerItem(itemWandCasting, "WandCasting", "Thaumcraft");
/* 226 */     itemWandCap = new thaumcraft.common.items.wands.ItemWandCap().func_77655_b("WandCap");
/* 227 */     GameRegistry.registerItem(itemWandCap, "WandCap", "Thaumcraft");
/* 228 */     itemWandRod = new thaumcraft.common.items.wands.ItemWandRod().func_77655_b("WandRod");
/* 229 */     GameRegistry.registerItem(itemWandRod, "WandRod", "Thaumcraft");
/* 230 */     itemFocusPouch = new thaumcraft.common.items.wands.ItemFocusPouchBauble().func_77655_b("FocusPouch");
/* 231 */     GameRegistry.registerItem(itemFocusPouch, "FocusPouch", "Thaumcraft");
/* 232 */     itemFocusFire = new thaumcraft.common.items.wands.foci.ItemFocusFire().func_77655_b("FocusFire");
/* 233 */     GameRegistry.registerItem(itemFocusFire, "FocusFire", "Thaumcraft");
/* 234 */     itemFocusShock = new thaumcraft.common.items.wands.foci.ItemFocusShock().func_77655_b("FocusShock");
/* 235 */     GameRegistry.registerItem(itemFocusShock, "FocusShock", "Thaumcraft");
/* 236 */     itemFocusHellbat = new thaumcraft.common.items.wands.foci.ItemFocusHellbat().func_77655_b("FocusHellbat");
/* 237 */     GameRegistry.registerItem(itemFocusHellbat, "FocusHellbat", "Thaumcraft");
/* 238 */     itemFocusFrost = new thaumcraft.common.items.wands.foci.ItemFocusFrost().func_77655_b("FocusFrost");
/* 239 */     GameRegistry.registerItem(itemFocusFrost, "FocusFrost", "Thaumcraft");
/* 240 */     itemFocusTrade = new thaumcraft.common.items.wands.foci.ItemFocusTrade().func_77655_b("FocusTrade");
/* 241 */     GameRegistry.registerItem(itemFocusTrade, "FocusTrade", "Thaumcraft");
/* 242 */     itemFocusExcavation = new thaumcraft.common.items.wands.foci.ItemFocusExcavation().func_77655_b("FocusExcavation");
/* 243 */     GameRegistry.registerItem(itemFocusExcavation, "FocusExcavation", "Thaumcraft");
/* 244 */     itemFocusPortableHole = new thaumcraft.common.items.wands.foci.ItemFocusPortableHole().func_77655_b("FocusPortableHole");
/* 245 */     GameRegistry.registerItem(itemFocusPortableHole, "FocusPortableHole", "Thaumcraft");
/* 246 */     itemFocusPech = new ItemFocusPech().func_77655_b("FocusPech");
/* 247 */     GameRegistry.registerItem(itemFocusPech, "FocusPech", "Thaumcraft");
/* 248 */     itemFocusWarding = new thaumcraft.common.items.wands.foci.ItemFocusWarding().func_77655_b("FocusWarding");
/* 249 */     GameRegistry.registerItem(itemFocusWarding, "FocusWarding", "Thaumcraft");
/* 250 */     itemFocusPrimal = new thaumcraft.common.items.wands.foci.ItemFocusPrimal().func_77655_b("FocusPrimal");
/* 251 */     GameRegistry.registerItem(itemFocusPrimal, "FocusPrimal", "Thaumcraft");
/*     */     
/* 253 */     itemEssence = new thaumcraft.common.items.ItemEssence().func_77655_b("ItemEssence");
/* 254 */     GameRegistry.registerItem(itemEssence, "ItemEssence", "Thaumcraft");
/* 255 */     itemManaBean = new thaumcraft.common.items.ItemManaBean().func_77655_b("ItemManaBean");
/* 256 */     GameRegistry.registerItem(itemManaBean, "ItemManaBean", "Thaumcraft");
/* 257 */     itemWispEssence = new thaumcraft.common.items.ItemWispEssence().func_77655_b("ItemWispEssence");
/* 258 */     GameRegistry.registerItem(itemWispEssence, "ItemWispEssence", "Thaumcraft");
/* 259 */     itemResource = new thaumcraft.common.items.ItemResource().func_77655_b("ItemResource");
/* 260 */     GameRegistry.registerItem(itemResource, "ItemResource", "Thaumcraft");
/* 261 */     itemShard = new thaumcraft.common.items.ItemShard().func_77655_b("ItemShard");
/* 262 */     GameRegistry.registerItem(itemShard, "ItemShard", "Thaumcraft");
/* 263 */     itemResearchNotes = new thaumcraft.common.items.ItemResearchNotes().func_77655_b("ItemResearchNotes");
/* 264 */     GameRegistry.registerItem(itemResearchNotes, "ItemResearchNotes", "Thaumcraft");
/* 265 */     itemInkwell = new thaumcraft.common.items.ItemInkwell().func_77655_b("ItemInkwell");
/* 266 */     GameRegistry.registerItem(itemInkwell, "ItemInkwell", "Thaumcraft");
/* 267 */     itemThaumonomicon = new thaumcraft.common.items.relics.ItemThaumonomicon().func_77655_b("ItemThaumonomicon");
/* 268 */     GameRegistry.registerItem(itemThaumonomicon, "ItemThaumonomicon", "Thaumcraft");
/*     */     
/* 270 */     itemThaumometer = new thaumcraft.common.items.relics.ItemThaumometer().func_77655_b("ItemThaumometer");
/* 271 */     GameRegistry.registerItem(itemThaumometer, "ItemThaumometer", "Thaumcraft");
/*     */     
/* 273 */     itemGoggles = new thaumcraft.common.items.armor.ItemGoggles(ThaumcraftApi.armorMatSpecial, 4, 0).func_77655_b("ItemGoggles");
/* 274 */     GameRegistry.registerItem(itemGoggles, "ItemGoggles", "Thaumcraft");
/* 275 */     itemHelmetThaumium = new ItemThaumiumArmor(ThaumcraftApi.armorMatThaumium, 2, 0).func_77655_b("ItemHelmetThaumium");
/* 276 */     GameRegistry.registerItem(itemHelmetThaumium, "ItemHelmetThaumium", "Thaumcraft");
/* 277 */     itemChestThaumium = new ItemThaumiumArmor(ThaumcraftApi.armorMatThaumium, 2, 1).func_77655_b("ItemChestplateThaumium");
/* 278 */     GameRegistry.registerItem(itemChestThaumium, "ItemChestplateThaumium", "Thaumcraft");
/* 279 */     itemLegsThaumium = new ItemThaumiumArmor(ThaumcraftApi.armorMatThaumium, 2, 2).func_77655_b("ItemLeggingsThaumium");
/* 280 */     GameRegistry.registerItem(itemLegsThaumium, "ItemLeggingsThaumium", "Thaumcraft");
/* 281 */     itemBootsThaumium = new ItemThaumiumArmor(ThaumcraftApi.armorMatThaumium, 2, 3).func_77655_b("ItemBootsThaumium");
/* 282 */     GameRegistry.registerItem(itemBootsThaumium, "ItemBootsThaumium", "Thaumcraft");
/* 283 */     itemShovelThaumium = new ItemThaumiumShovel(ThaumcraftApi.toolMatThaumium).func_77655_b("ItemShovelThaumium");
/* 284 */     GameRegistry.registerItem(itemShovelThaumium, "ItemShovelThaumium", "Thaumcraft");
/* 285 */     itemPickThaumium = new thaumcraft.common.items.equipment.ItemThaumiumPickaxe(ThaumcraftApi.toolMatThaumium).func_77655_b("ItemPickThaumium");
/* 286 */     GameRegistry.registerItem(itemPickThaumium, "ItemPickThaumium", "Thaumcraft");
/* 287 */     itemAxeThaumium = new thaumcraft.common.items.equipment.ItemThaumiumAxe(ThaumcraftApi.toolMatThaumium).func_77655_b("ItemAxeThaumium");
/* 288 */     GameRegistry.registerItem(itemAxeThaumium, "ItemAxeThaumium", "Thaumcraft");
/* 289 */     itemSwordThaumium = new ItemThaumiumSword(ThaumcraftApi.toolMatThaumium).func_77655_b("ItemSwordThaumium");
/* 290 */     GameRegistry.registerItem(itemSwordThaumium, "ItemSwordThaumium", "Thaumcraft");
/* 291 */     itemHoeThaumium = new thaumcraft.common.items.equipment.ItemThaumiumHoe(ThaumcraftApi.toolMatThaumium).func_77655_b("ItemHoeThaumium");
/* 292 */     GameRegistry.registerItem(itemHoeThaumium, "ItemHoeThaumium", "Thaumcraft");
/*     */     
/* 294 */     itemArcaneDoor = new thaumcraft.common.blocks.ItemArcaneDoor().func_77655_b("ItemArcaneDoor");
/* 295 */     GameRegistry.registerItem(itemArcaneDoor, "ItemArcaneDoor", "Thaumcraft");
/* 296 */     itemNugget = new thaumcraft.common.items.ItemNugget().func_77655_b("ItemNugget");
/* 297 */     GameRegistry.registerItem(itemNugget, "ItemNugget", "Thaumcraft");
/* 298 */     itemBootsTraveller = new thaumcraft.common.items.armor.ItemBootsTraveller(ThaumcraftApi.armorMatSpecial, 4, 3).func_77655_b("BootsTraveller");
/* 299 */     GameRegistry.registerItem(itemBootsTraveller, "BootsTraveller", "Thaumcraft");
/* 300 */     itemNuggetChicken = new ItemNuggetEdible("nuggetchicken").func_77655_b("ItemNuggetChicken");
/* 301 */     GameRegistry.registerItem(itemNuggetChicken, "ItemNuggetChicken", "Thaumcraft");
/* 302 */     itemNuggetBeef = new ItemNuggetEdible("nuggetbeef").func_77655_b("ItemNuggetBeef");
/* 303 */     GameRegistry.registerItem(itemNuggetBeef, "ItemNuggetBeef", "Thaumcraft");
/* 304 */     itemNuggetPork = new ItemNuggetEdible("nuggetpork").func_77655_b("ItemNuggetPork");
/* 305 */     GameRegistry.registerItem(itemNuggetPork, "ItemNuggetPork", "Thaumcraft");
/* 306 */     itemNuggetFish = new ItemNuggetEdible("nuggetfish").func_77655_b("ItemNuggetFish");
/* 307 */     GameRegistry.registerItem(itemNuggetFish, "ItemNuggetFish", "Thaumcraft");
/*     */     
/* 309 */     itemTripleMeatTreat = new thaumcraft.common.items.ItemTripleMeatTreat().func_77655_b("TripleMeatTreat");
/* 310 */     GameRegistry.registerItem(itemTripleMeatTreat, "TripleMeatTreat", "Thaumcraft");
/* 311 */     itemSwordElemental = new thaumcraft.common.items.equipment.ItemElementalSword(ThaumcraftApi.toolMatElemental).func_77655_b("ItemSwordElemental");
/* 312 */     GameRegistry.registerItem(itemSwordElemental, "ItemSwordElemental", "Thaumcraft");
/* 313 */     itemShovelElemental = new thaumcraft.common.items.equipment.ItemElementalShovel(ThaumcraftApi.toolMatElemental).func_77655_b("ItemShovelElemental");
/* 314 */     GameRegistry.registerItem(itemShovelElemental, "ItemShovelElemental", "Thaumcraft");
/* 315 */     itemPickElemental = new thaumcraft.common.items.equipment.ItemElementalPickaxe(ThaumcraftApi.toolMatElemental).func_77655_b("ItemPickaxeElemental");
/* 316 */     GameRegistry.registerItem(itemPickElemental, "ItemPickaxeElemental", "Thaumcraft");
/* 317 */     itemAxeElemental = new thaumcraft.common.items.equipment.ItemElementalAxe(ThaumcraftApi.toolMatElemental).func_77655_b("ItemAxeElemental");
/* 318 */     GameRegistry.registerItem(itemAxeElemental, "ItemAxeElemental", "Thaumcraft");
/* 319 */     itemHoeElemental = new thaumcraft.common.items.equipment.ItemElementalHoe(ThaumcraftApi.toolMatElemental).func_77655_b("ItemHoeElemental");
/* 320 */     GameRegistry.registerItem(itemHoeElemental, "ItemHoeElemental", "Thaumcraft");
/* 321 */     itemChestRobe = new ItemRobeArmor(ThaumcraftApi.armorMatSpecial, 1, 1).func_77655_b("ItemChestplateRobe");
/* 322 */     GameRegistry.registerItem(itemChestRobe, "ItemChestplateRobe", "Thaumcraft");
/* 323 */     itemLegsRobe = new ItemRobeArmor(ThaumcraftApi.armorMatSpecial, 2, 2).func_77655_b("ItemLeggingsRobe");
/* 324 */     GameRegistry.registerItem(itemLegsRobe, "ItemLeggingsRobe", "Thaumcraft");
/* 325 */     itemBootsRobe = new ItemRobeArmor(ThaumcraftApi.armorMatSpecial, 1, 3).func_77655_b("ItemBootsRobe");
/* 326 */     GameRegistry.registerItem(itemBootsRobe, "ItemBootsRobe", "Thaumcraft");
/* 327 */     itemKey = new ItemKey().func_77655_b("ArcaneDoorKey");
/* 328 */     GameRegistry.registerItem(itemKey, "ArcaneDoorKey", "Thaumcraft");
/* 329 */     itemHandMirror = new ItemHandMirror().func_77655_b("HandMirror");
/* 330 */     GameRegistry.registerItem(itemHandMirror, "HandMirror", "Thaumcraft");
/* 331 */     itemHoverHarness = new thaumcraft.common.items.armor.ItemHoverHarness(ThaumcraftApi.armorMatSpecial, 1, 1).func_77655_b("HoverHarness");
/* 332 */     GameRegistry.registerItem(itemHoverHarness, "HoverHarness", "Thaumcraft");
/* 333 */     itemJarFilled = new thaumcraft.common.blocks.ItemJarFilled().func_77655_b("BlockJarFilledItem");
/* 334 */     GameRegistry.registerItem(itemJarFilled, "BlockJarFilledItem", "Thaumcraft");
/* 335 */     itemJarNode = new thaumcraft.common.blocks.ItemJarNode().func_77655_b("BlockJarNodeItem");
/* 336 */     GameRegistry.registerItem(itemJarNode, "BlockJarNodeItem", "Thaumcraft");
/*     */     
/* 338 */     itemTrunkSpawner = new ItemTrunkSpawner().func_77655_b("TrunkSpawner");
/* 339 */     GameRegistry.registerItem(itemTrunkSpawner, "TrunkSpawner", "Thaumcraft");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 347 */     itemGolemPlacer = new thaumcraft.common.entities.golems.ItemGolemPlacer().func_77655_b("ItemGolemPlacer");
/* 348 */     GameRegistry.registerItem(itemGolemPlacer, "ItemGolemPlacer", "Thaumcraft");
/* 349 */     itemGolemCore = new thaumcraft.common.entities.golems.ItemGolemCore().func_77655_b("ItemGolemCore");
/* 350 */     GameRegistry.registerItem(itemGolemCore, "ItemGolemCore", "Thaumcraft");
/* 351 */     itemGolemUpgrade = new thaumcraft.common.entities.golems.ItemGolemUpgrade().func_77655_b("ItemGolemUpgrade");
/* 352 */     GameRegistry.registerItem(itemGolemUpgrade, "ItemGolemUpgrade", "Thaumcraft");
/* 353 */     itemGolemBell = new ItemGolemBell().func_77655_b("GolemBell");
/* 354 */     GameRegistry.registerItem(itemGolemBell, "GolemBell", "Thaumcraft");
/* 355 */     itemGolemDecoration = new thaumcraft.common.entities.golems.ItemGolemDecoration().func_77655_b("ItemGolemDecoration");
/* 356 */     GameRegistry.registerItem(itemGolemDecoration, "ItemGolemDecoration", "Thaumcraft");
/*     */     
/* 358 */     itemBowBone = new thaumcraft.common.items.equipment.ItemBowBone().func_77655_b("ItemBowBone");
/* 359 */     GameRegistry.registerItem(itemBowBone, "ItemBowBone", "Thaumcraft");
/*     */     
/* 361 */     itemPrimalArrow = new ItemPrimalArrow().func_77655_b("ItemPrimalArrow");
/* 362 */     GameRegistry.registerItem(itemPrimalArrow, "PrimalArrow", "Thaumcraft");
/*     */     
/* 364 */     itemResonator = new thaumcraft.common.items.relics.ItemResonator().func_77655_b("ItemResonator");
/* 365 */     GameRegistry.registerItem(itemResonator, "ItemResonator", "Thaumcraft");
/*     */     
/* 367 */     itemBaubleBlanks = new thaumcraft.common.items.baubles.ItemBaubleBlanks().func_77655_b("ItemBaubleBlanks");
/* 368 */     GameRegistry.registerItem(itemBaubleBlanks, "ItemBaubleBlanks", "Thaumcraft");
/* 369 */     itemAmuletRunic = new thaumcraft.common.items.baubles.ItemAmuletRunic().func_77655_b("ItemAmuletRunic");
/* 370 */     GameRegistry.registerItem(itemAmuletRunic, "ItemAmuletRunic", "Thaumcraft");
/* 371 */     itemRingRunic = new thaumcraft.common.items.baubles.ItemRingRunic().func_77655_b("ItemRingRunic");
/* 372 */     GameRegistry.registerItem(itemRingRunic, "ItemRingRunic", "Thaumcraft");
/* 373 */     itemGirdleRunic = new thaumcraft.common.items.baubles.ItemGirdleRunic().func_77655_b("ItemGirdleRunic");
/* 374 */     GameRegistry.registerItem(itemGirdleRunic, "ItemGirdleRunic", "Thaumcraft");
/* 375 */     itemAmuletVis = new thaumcraft.common.items.baubles.ItemAmuletVis().func_77655_b("ItemAmuletVis");
/* 376 */     GameRegistry.registerItem(itemAmuletVis, "ItemAmuletVis", "Thaumcraft");
/* 377 */     itemGirdleHover = new thaumcraft.common.items.baubles.ItemGirdleHover().func_77655_b("ItemGirdleHover");
/* 378 */     GameRegistry.registerItem(itemGirdleHover, "ItemGirdleHover", "Thaumcraft");
/*     */     
/* 380 */     itemSpawnerEgg = new thaumcraft.common.entities.ItemSpawnerEgg().func_77655_b("ItemSpawnerEgg");
/* 381 */     GameRegistry.registerItem(itemSpawnerEgg, "ItemSpawnerEgg", "Thaumcraft");
/*     */     
/* 383 */     itemZombieBrain = new ItemZombieBrain().func_77655_b("ItemZombieBrain");
/* 384 */     GameRegistry.registerItem(itemZombieBrain, "ItemZombieBrain", "Thaumcraft");
/*     */     
/* 386 */     itemBathSalts = new thaumcraft.common.items.ItemBathSalts().func_77655_b("ItemBathSalts");
/* 387 */     GameRegistry.registerItem(itemBathSalts, "ItemBathSalts", "Thaumcraft");
/*     */     
/* 389 */     itemCrystalEssence = new thaumcraft.common.items.ItemCrystalEssence().func_77655_b("ItemCrystalEssence");
/* 390 */     GameRegistry.registerItem(itemCrystalEssence, "ItemCrystalEssence", "Thaumcraft");
/*     */     
/* 392 */     itemBucketDeath = new thaumcraft.common.items.ItemBucketDeath().func_77655_b("ItemBucketDeath");
/* 393 */     GameRegistry.registerItem(itemBucketDeath, "ItemBucketDeath", "Thaumcraft");
/* 394 */     itemBucketPure = new thaumcraft.common.items.ItemBucketPure().func_77655_b("ItemBucketPure");
/* 395 */     GameRegistry.registerItem(itemBucketPure, "ItemBucketPure", "Thaumcraft");
/*     */     
/* 397 */     net.minecraftforge.fluids.FluidContainerRegistry.registerFluidContainer(new net.minecraftforge.fluids.FluidStack(ConfigBlocks.FLUIDDEATH, 1000), new ItemStack(itemBucketDeath), new ItemStack(Items.field_151133_ar));
/*     */     
/*     */ 
/* 400 */     net.minecraftforge.fluids.FluidContainerRegistry.registerFluidContainer(new net.minecraftforge.fluids.FluidStack(ConfigBlocks.FLUIDPURE, 1000), new ItemStack(itemBucketPure), new ItemStack(Items.field_151133_ar));
/*     */     
/*     */ 
/*     */ 
/* 404 */     itemHelmetFortress = new ItemFortressArmor(ThaumcraftApi.armorMatThaumiumFortress, 4, 0).func_77655_b("ItemHelmetFortress");
/* 405 */     GameRegistry.registerItem(itemHelmetFortress, "ItemHelmetFortress", "Thaumcraft");
/* 406 */     itemChestFortress = new ItemFortressArmor(ThaumcraftApi.armorMatThaumiumFortress, 4, 1).func_77655_b("ItemChestplateFortress");
/* 407 */     GameRegistry.registerItem(itemChestFortress, "ItemChestplateFortress", "Thaumcraft");
/* 408 */     itemLegsFortress = new ItemFortressArmor(ThaumcraftApi.armorMatThaumiumFortress, 4, 2).func_77655_b("ItemLeggingsFortress");
/* 409 */     GameRegistry.registerItem(itemLegsFortress, "ItemLeggingsFortress", "Thaumcraft");
/*     */     
/* 411 */     itemEldritchObject = new thaumcraft.common.items.ItemEldritchObject().func_77655_b("ItemEldritchObject");
/* 412 */     GameRegistry.registerItem(itemEldritchObject, "ItemEldritchObject", "Thaumcraft");
/*     */     
/* 414 */     itemHelmetVoid = new ItemVoidArmor(ThaumcraftApi.armorMatVoid, 2, 0).func_77655_b("ItemHelmetVoid");
/* 415 */     GameRegistry.registerItem(itemHelmetVoid, "ItemHelmetVoid", "Thaumcraft");
/* 416 */     itemChestVoid = new ItemVoidArmor(ThaumcraftApi.armorMatVoid, 2, 1).func_77655_b("ItemChestplateVoid");
/* 417 */     GameRegistry.registerItem(itemChestVoid, "ItemChestplateVoid", "Thaumcraft");
/* 418 */     itemLegsVoid = new ItemVoidArmor(ThaumcraftApi.armorMatVoid, 2, 2).func_77655_b("ItemLeggingsVoid");
/* 419 */     GameRegistry.registerItem(itemLegsVoid, "ItemLeggingsVoid", "Thaumcraft");
/* 420 */     itemBootsVoid = new ItemVoidArmor(ThaumcraftApi.armorMatVoid, 2, 3).func_77655_b("ItemBootsVoid");
/* 421 */     GameRegistry.registerItem(itemBootsVoid, "ItemBootsVoid", "Thaumcraft");
/* 422 */     itemShovelVoid = new ItemVoidShovel(ThaumcraftApi.toolMatVoid).func_77655_b("ItemShovelVoid");
/* 423 */     GameRegistry.registerItem(itemShovelVoid, "ItemShovelVoid", "Thaumcraft");
/* 424 */     itemPickVoid = new thaumcraft.common.items.equipment.ItemVoidPickaxe(ThaumcraftApi.toolMatVoid).func_77655_b("ItemPickVoid");
/* 425 */     GameRegistry.registerItem(itemPickVoid, "ItemPickVoid", "Thaumcraft");
/* 426 */     itemAxeVoid = new thaumcraft.common.items.equipment.ItemVoidAxe(ThaumcraftApi.toolMatVoid).func_77655_b("ItemAxeVoid");
/* 427 */     GameRegistry.registerItem(itemAxeVoid, "ItemAxeVoid", "Thaumcraft");
/* 428 */     itemSwordVoid = new thaumcraft.common.items.equipment.ItemVoidSword(ThaumcraftApi.toolMatVoid).func_77655_b("ItemSwordVoid");
/* 429 */     GameRegistry.registerItem(itemSwordVoid, "ItemSwordVoid", "Thaumcraft");
/* 430 */     itemHoeVoid = new thaumcraft.common.items.equipment.ItemVoidHoe(ThaumcraftApi.toolMatVoid).func_77655_b("ItemHoeVoid");
/* 431 */     GameRegistry.registerItem(itemHoeVoid, "ItemHoeVoid", "Thaumcraft");
/*     */     
/* 433 */     itemHelmetVoidRobe = new ItemVoidRobeArmor(ThaumcraftApi.armorMatVoid, 4, 0).func_77655_b("ItemHelmetVoidRobe");
/* 434 */     GameRegistry.registerItem(itemHelmetVoidRobe, "ItemHelmetVoidFortress", "Thaumcraft");
/* 435 */     itemChestVoidRobe = new ItemVoidRobeArmor(ThaumcraftApi.armorMatVoid, 4, 1).func_77655_b("ItemChestplateVoidRobe");
/* 436 */     GameRegistry.registerItem(itemChestVoidRobe, "ItemChestplateVoidFortress", "Thaumcraft");
/* 437 */     itemLegsVoidRobe = new ItemVoidRobeArmor(ThaumcraftApi.armorMatVoid, 4, 2).func_77655_b("ItemLeggingsVoidRobe");
/* 438 */     GameRegistry.registerItem(itemLegsVoidRobe, "ItemLeggingsVoidFortress", "Thaumcraft");
/*     */     
/* 440 */     itemSanitySoap = new thaumcraft.common.items.ItemSanitySoap().func_77655_b("ItemSanitySoap");
/* 441 */     GameRegistry.registerItem(itemSanitySoap, "ItemSanitySoap", "Thaumcraft");
/*     */     
/* 443 */     itemSanityChecker = new thaumcraft.common.items.relics.ItemSanityChecker().func_77655_b("ItemSanityChecker");
/* 444 */     GameRegistry.registerItem(itemSanityChecker, "ItemSanityChecker", "Thaumcraft");
/*     */     
/* 446 */     itemBottleTaint = new ItemBottleTaint().func_77655_b("ItemBottleTaint");
/* 447 */     GameRegistry.registerItem(itemBottleTaint, "ItemBottleTaint", "Thaumcraft");
/*     */     
/*     */ 
/* 450 */     itemHelmetCultistRobe = new ItemCultistRobeArmor(ItemArmor.ArmorMaterial.IRON, 4, 0).func_77655_b("ItemHelmetCultistRobe");
/* 451 */     GameRegistry.registerItem(itemHelmetCultistRobe, "ItemHelmetCultistRobe", "Thaumcraft");
/* 452 */     itemChestCultistRobe = new ItemCultistRobeArmor(ItemArmor.ArmorMaterial.IRON, 4, 1).func_77655_b("ItemChestplateCultistRobe");
/* 453 */     GameRegistry.registerItem(itemChestCultistRobe, "ItemChestplateCultistRobe", "Thaumcraft");
/* 454 */     itemLegsCultistRobe = new ItemCultistRobeArmor(ItemArmor.ArmorMaterial.IRON, 4, 2).func_77655_b("ItemLeggingsCultistRobe");
/* 455 */     GameRegistry.registerItem(itemLegsCultistRobe, "ItemLeggingsCultistRobe", "Thaumcraft");
/* 456 */     itemBootsCultist = new thaumcraft.common.items.armor.ItemCultistBoots(ItemArmor.ArmorMaterial.IRON, 4, 3).func_77655_b("ItemBootsCultist");
/* 457 */     GameRegistry.registerItem(itemBootsCultist, "ItemBootsCultist", "Thaumcraft");
/* 458 */     itemHelmetCultistPlate = new ItemCultistPlateArmor(ItemArmor.ArmorMaterial.IRON, 4, 0).func_77655_b("ItemHelmetCultistPlate");
/* 459 */     GameRegistry.registerItem(itemHelmetCultistPlate, "ItemHelmetCultistPlate", "Thaumcraft");
/* 460 */     itemChestCultistPlate = new ItemCultistPlateArmor(ItemArmor.ArmorMaterial.IRON, 4, 1).func_77655_b("ItemChestplateCultistPlate");
/* 461 */     GameRegistry.registerItem(itemChestCultistPlate, "ItemChestplateCultistPlate", "Thaumcraft");
/* 462 */     itemLegsCultistPlate = new ItemCultistPlateArmor(ItemArmor.ArmorMaterial.IRON, 4, 2).func_77655_b("ItemLeggingsCultistPlate");
/* 463 */     GameRegistry.registerItem(itemLegsCultistPlate, "ItemLeggingsCultistPlate", "Thaumcraft");
/*     */     
/* 465 */     itemHelmetCultistLeaderPlate = new ItemCultistLeaderArmor(ThaumcraftApi.armorMatThaumiumFortress, 4, 0).func_77655_b("ItemHelmetCultistLeaderPlate");
/* 466 */     GameRegistry.registerItem(itemHelmetCultistLeaderPlate, "ItemHelmetCultistLeaderPlate", "Thaumcraft");
/* 467 */     itemChestCultistLeaderPlate = new ItemCultistLeaderArmor(ThaumcraftApi.armorMatThaumiumFortress, 4, 1).func_77655_b("ItemChestplateCultistLeaderPlate");
/* 468 */     GameRegistry.registerItem(itemChestCultistLeaderPlate, "ItemChestplateCultistLeaderPlate", "Thaumcraft");
/* 469 */     itemLegsCultistLeaderPlate = new ItemCultistLeaderArmor(ThaumcraftApi.armorMatThaumiumFortress, 4, 2).func_77655_b("ItemLeggingsCultistLeaderPlate");
/* 470 */     GameRegistry.registerItem(itemLegsCultistLeaderPlate, "ItemLeggingsCultistLeaderPlate", "Thaumcraft");
/*     */     
/* 472 */     itemSwordCrimson = new thaumcraft.common.items.equipment.ItemCrimsonSword().func_77655_b("ItemSwordCrimson");
/* 473 */     GameRegistry.registerItem(itemSwordCrimson, "ItemSwordCrimson", "Thaumcraft");
/*     */     
/* 475 */     itemLootbag = new thaumcraft.common.items.ItemLootBag().func_77655_b("ItemLootBag");
/* 476 */     GameRegistry.registerItem(itemLootbag, "ItemLootBag", "Thaumcraft");
/*     */     
/* 478 */     itemCompassStone = new thaumcraft.common.items.ItemCompassStone().func_77655_b("ItemCompassStone");
/* 479 */     GameRegistry.registerItem(itemCompassStone, "ItemCompassStone", "Thaumcraft");
/*     */     
/* 481 */     itemPrimalCrusher = new ItemPrimalCrusher(ItemPrimalCrusher.material).func_77655_b("ItemPrimalCrusher");
/* 482 */     GameRegistry.registerItem(itemPrimalCrusher, "ItemPrimalCrusher", "Thaumcraft");
/*     */   }
/*     */   
/*     */   public static Item itemShovelElemental;
/*     */   public static Item itemPickThaumium;
/*     */   public static Item itemPickElemental;
/*     */   public static Item itemSwordThaumium;
/*     */   public static Item itemSwordElemental;
/*     */   public static Item itemAxeThaumium;
/*     */   public static Item itemAxeElemental;
/*     */   public static Item itemHoeThaumium;
/*     */   public static Item itemHoeElemental;
/*     */   public static Item itemHelmetFortress;
/*     */   public static Item itemChestFortress;
/*     */   public static Item itemBootsFortress;
/*     */   public static Item itemLegsFortress;
/*     */   public static Item itemHelmetVoid;
/*     */   public static Item itemChestVoid;
/*     */   public static Item itemBootsVoid;
/*     */   public static Item itemLegsVoid;
/*     */   public static Item itemShovelVoid;
/*     */   public static Item itemPickVoid;
/*     */   public static Item itemSwordVoid;
/*     */   public static Item itemSwordCrimson;
/*     */   public static Item itemAxeVoid;
/*     */   public static Item itemHoeVoid;
/*     */   public static Item itemHelmetVoidRobe;
/*     */   public static Item itemChestVoidRobe;
/*     */   public static Item itemLegsVoidRobe;
/*     */   public static Item itemHelmetCultistRobe;
/*     */   public static Item itemChestCultistRobe;
/*     */   public static Item itemLegsCultistRobe;
/*     */   public static Item itemBootsCultist;
/*     */   public static Item itemHelmetCultistPlate;
/*     */   public static Item itemChestCultistPlate;
/*     */   public static Item itemLegsCultistPlate;
/*     */   public static Item itemHelmetCultistLeaderPlate;
/*     */   public static Item itemChestCultistLeaderPlate;
/*     */   public static Item itemLegsCultistLeaderPlate;
/*     */   public static Item itemLootbag;
/*     */   public static Item itemBaubleBlanks;
/*     */   public static Item itemAmuletRunic;
/*     */   public static Item itemRingRunic;
/*     */   public static Item itemGirdleRunic;
/*     */   public static Item itemGirdleHover;
/*     */   public static Item itemAmuletVis;
/*     */   public static Item itemLegsRobe;
/*     */   public static Item itemChestRobe;
/*     */   public static Item itemBootsRobe;
/*     */   public static Item itemHoverHarness;
/*     */   public static Item itemBowBone;
/*     */   public static Item itemPrimalArrow;
/*     */   public static Item itemCompassStone;
/*     */   public static Item itemPrimalCrusher;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/config/ConfigItems.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */