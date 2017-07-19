/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionHelper;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.ThaumcraftApi.EntityTagsNBT;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ 
/*     */ public class ConfigAspects
/*     */ {
/*     */   public static void init()
/*     */   {
/*  21 */     registerItemAspects();
/*  22 */     registerEntityAspects();
/*     */   }
/*     */   
/*  25 */   private static final int[] ALLMETA = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
/*     */   
/*     */   private static void registerEntityAspects() {
/*  28 */     ThaumcraftApi.registerEntityTag("Zombie", new AspectList().add(Aspect.UNDEAD, 2).add(Aspect.MAN, 1).add(Aspect.EARTH, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  29 */     ThaumcraftApi.registerEntityTag("Giant", new AspectList().add(Aspect.UNDEAD, 4).add(Aspect.MAN, 3).add(Aspect.EARTH, 3), new ThaumcraftApi.EntityTagsNBT[0]);
/*  30 */     ThaumcraftApi.registerEntityTag("Skeleton", new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.MAN, 1).add(Aspect.EARTH, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  31 */     ThaumcraftApi.registerEntityTag("Skeleton", new AspectList().add(Aspect.UNDEAD, 4).add(Aspect.MAN, 1).add(Aspect.FIRE, 2), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("SkeletonType", Byte.valueOf(1)) });
/*  32 */     ThaumcraftApi.registerEntityTag("Creeper", new AspectList().add(Aspect.PLANT, 2).add(Aspect.FIRE, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  33 */     ThaumcraftApi.registerEntityTag("Creeper", new AspectList().add(Aspect.PLANT, 3).add(Aspect.FIRE, 3).add(Aspect.ENERGY, 3), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("powered", Byte.valueOf(1)) });
/*  34 */     ThaumcraftApi.registerEntityTag("EntityHorse", new AspectList().add(Aspect.BEAST, 4).add(Aspect.EARTH, 1).add(Aspect.AIR, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  35 */     ThaumcraftApi.registerEntityTag("Pig", new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  36 */     ThaumcraftApi.registerEntityTag("XPOrb", new AspectList().add(Aspect.MIND, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  37 */     ThaumcraftApi.registerEntityTag("Sheep", new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  38 */     ThaumcraftApi.registerEntityTag("Cow", new AspectList().add(Aspect.BEAST, 3).add(Aspect.EARTH, 3), new ThaumcraftApi.EntityTagsNBT[0]);
/*  39 */     ThaumcraftApi.registerEntityTag("MushroomCow", new AspectList().add(Aspect.BEAST, 3).add(Aspect.PLANT, 1).add(Aspect.EARTH, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  40 */     ThaumcraftApi.registerEntityTag("SnowMan", new AspectList().add(Aspect.COLD, 3).add(Aspect.WATER, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  41 */     ThaumcraftApi.registerEntityTag("Ozelot", new AspectList().add(Aspect.BEAST, 3).add(Aspect.ENTROPY, 3), new ThaumcraftApi.EntityTagsNBT[0]);
/*  42 */     ThaumcraftApi.registerEntityTag("Chicken", new AspectList().add(Aspect.BEAST, 2).add(Aspect.FLIGHT, 2).add(Aspect.AIR, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  43 */     ThaumcraftApi.registerEntityTag("Squid", new AspectList().add(Aspect.BEAST, 2).add(Aspect.WATER, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  44 */     ThaumcraftApi.registerEntityTag("Wolf", new AspectList().add(Aspect.BEAST, 3).add(Aspect.EARTH, 3), new ThaumcraftApi.EntityTagsNBT[0]);
/*  45 */     ThaumcraftApi.registerEntityTag("Bat", new AspectList().add(Aspect.BEAST, 1).add(Aspect.FLIGHT, 1).add(Aspect.AIR, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  46 */     ThaumcraftApi.registerEntityTag("Boat", new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.WATER, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  47 */     ThaumcraftApi.registerEntityTag("Spider", new AspectList().add(Aspect.BEAST, 3).add(Aspect.ENTROPY, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  48 */     ThaumcraftApi.registerEntityTag("Slime", new AspectList().add(Aspect.SLIME, 2).add(Aspect.WATER, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  49 */     ThaumcraftApi.registerEntityTag("Ghast", new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.FIRE, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  50 */     ThaumcraftApi.registerEntityTag("PigZombie", new AspectList().add(Aspect.UNDEAD, 4).add(Aspect.FIRE, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  51 */     ThaumcraftApi.registerEntityTag("Enderman", new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.TRAVEL, 2).add(Aspect.AIR, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  52 */     ThaumcraftApi.registerEntityTag("CaveSpider", new AspectList().add(Aspect.BEAST, 2).add(Aspect.POISON, 2).add(Aspect.EARTH, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  53 */     ThaumcraftApi.registerEntityTag("Silverfish", new AspectList().add(Aspect.BEAST, 1).add(Aspect.EARTH, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  54 */     ThaumcraftApi.registerEntityTag("Blaze", new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.FIRE, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  55 */     ThaumcraftApi.registerEntityTag("LavaSlime", new AspectList().add(Aspect.SLIME, 3).add(Aspect.FIRE, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  56 */     ThaumcraftApi.registerEntityTag("EnderDragon", new AspectList().add(Aspect.ELDRITCH, 20).add(Aspect.BEAST, 20).add(Aspect.ENTROPY, 20), new ThaumcraftApi.EntityTagsNBT[0]);
/*  57 */     ThaumcraftApi.registerEntityTag("WitherBoss", new AspectList().add(Aspect.UNDEAD, 20).add(Aspect.ENTROPY, 20).add(Aspect.FIRE, 15), new ThaumcraftApi.EntityTagsNBT[0]);
/*  58 */     ThaumcraftApi.registerEntityTag("Witch", new AspectList().add(Aspect.MAN, 3).add(Aspect.MAGIC, 2).add(Aspect.FIRE, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  59 */     ThaumcraftApi.registerEntityTag("Villager", new AspectList().add(Aspect.MAN, 3).add(Aspect.AIR, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  60 */     ThaumcraftApi.registerEntityTag("VillagerGolem", new AspectList().add(Aspect.METAL, 4).add(Aspect.EARTH, 3), new ThaumcraftApi.EntityTagsNBT[0]);
/*  61 */     ThaumcraftApi.registerEntityTag("MinecartRideable", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.AIR, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  62 */     ThaumcraftApi.registerEntityTag("MinecartChest", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.AIR, 1).add(Aspect.VOID, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  63 */     ThaumcraftApi.registerEntityTag("MinecartFurnace", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.AIR, 1).add(Aspect.FIRE, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  64 */     ThaumcraftApi.registerEntityTag("MinecartTNT", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.AIR, 1).add(Aspect.FIRE, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  65 */     ThaumcraftApi.registerEntityTag("MinecartHopper", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.AIR, 1).add(Aspect.EXCHANGE, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  66 */     ThaumcraftApi.registerEntityTag("MinecartSpawner", new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.AIR, 1).add(Aspect.MAGIC, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  67 */     ThaumcraftApi.registerEntityTag("EnderCrystal", new AspectList().add(Aspect.ELDRITCH, 3).add(Aspect.MAGIC, 3).add(Aspect.HEAL, 3), new ThaumcraftApi.EntityTagsNBT[0]);
/*  68 */     ThaumcraftApi.registerEntityTag("ItemFrame", new AspectList().add(Aspect.SENSES, 3).add(Aspect.CLOTH, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  69 */     ThaumcraftApi.registerEntityTag("Painting", new AspectList().add(Aspect.SENSES, 5).add(Aspect.CLOTH, 3), new ThaumcraftApi.EntityTagsNBT[0]);
/*     */     
/*  71 */     ThaumcraftApi.registerEntityTag("Thaumcraft.PrimalOrb", new AspectList().add(Aspect.AIR, 5).add(Aspect.ENTROPY, 10).add(Aspect.MAGIC, 10).add(Aspect.ENERGY, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  72 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Firebat", new AspectList().add(Aspect.BEAST, 2).add(Aspect.FLIGHT, 1).add(Aspect.FIRE, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  73 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Pech", new AspectList().add(Aspect.MAN, 2).add(Aspect.MAGIC, 2).add(Aspect.EXCHANGE, 2).add(Aspect.GREED, 2), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("PechType", Byte.valueOf(0)) });
/*  74 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Pech", new AspectList().add(Aspect.MAN, 2).add(Aspect.MAGIC, 2).add(Aspect.EXCHANGE, 2).add(Aspect.WEAPON, 2), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("PechType", Byte.valueOf(1)) });
/*  75 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Pech", new AspectList().add(Aspect.MAN, 2).add(Aspect.MAGIC, 4).add(Aspect.EXCHANGE, 2), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("PechType", Byte.valueOf(2)) });
/*  76 */     ThaumcraftApi.registerEntityTag("Thaumcraft.ThaumSlime", new AspectList().add(Aspect.SLIME, 2).add(Aspect.MAGIC, 1).add(Aspect.WATER, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  77 */     ThaumcraftApi.registerEntityTag("Thaumcraft.BrainyZombie", new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.MAN, 1).add(Aspect.MIND, 1).add(Aspect.EARTH, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  78 */     ThaumcraftApi.registerEntityTag("Thaumcraft.GiantBrainyZombie", new AspectList().add(Aspect.UNDEAD, 4).add(Aspect.MAN, 2).add(Aspect.MIND, 1).add(Aspect.EARTH, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  79 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Taintacle", new AspectList().add(Aspect.TAINT, 3).add(Aspect.WATER, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  80 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintacleTiny", new AspectList().add(Aspect.TAINT, 1).add(Aspect.WATER, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  81 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintSpider", new AspectList().add(Aspect.TAINT, 1).add(Aspect.EARTH, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  82 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintSpore", new AspectList().add(Aspect.TAINT, 2).add(Aspect.AIR, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  83 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintSwarmer", new AspectList().add(Aspect.TAINT, 2).add(Aspect.AIR, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  84 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintSwarm", new AspectList().add(Aspect.TAINT, 3).add(Aspect.AIR, 3), new ThaumcraftApi.EntityTagsNBT[0]);
/*  85 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintedPig", new AspectList().add(Aspect.TAINT, 2).add(Aspect.EARTH, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  86 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintedSheep", new AspectList().add(Aspect.TAINT, 2).add(Aspect.EARTH, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  87 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintedCow", new AspectList().add(Aspect.TAINT, 3).add(Aspect.EARTH, 3), new ThaumcraftApi.EntityTagsNBT[0]);
/*  88 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintedChicken", new AspectList().add(Aspect.TAINT, 2).add(Aspect.FLIGHT, 2).add(Aspect.AIR, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  89 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintedVillager", new AspectList().add(Aspect.TAINT, 3).add(Aspect.AIR, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  90 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintedCreeper", new AspectList().add(Aspect.TAINT, 2).add(Aspect.FIRE, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  91 */     ThaumcraftApi.registerEntityTag("Thaumcraft.MindSpider", new AspectList().add(Aspect.TAINT, 2).add(Aspect.FIRE, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*     */     
/*  93 */     ThaumcraftApi.registerEntityTag("Thaumcraft.EldritchGuardian", new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.DEATH, 2).add(Aspect.UNDEAD, 4), new ThaumcraftApi.EntityTagsNBT[0]);
/*  94 */     ThaumcraftApi.registerEntityTag("Thaumcraft.EldritchOrb", new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.DEATH, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*  95 */     ThaumcraftApi.registerEntityTag("Thaumcraft.CultistKnight", new AspectList().add(Aspect.ELDRITCH, 1).add(Aspect.MAN, 2).add(Aspect.ENTROPY, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*  96 */     ThaumcraftApi.registerEntityTag("Thaumcraft.CultistCleric", new AspectList().add(Aspect.ELDRITCH, 1).add(Aspect.MAN, 2).add(Aspect.ENTROPY, 1), new ThaumcraftApi.EntityTagsNBT[0]);
/*     */     
/*  98 */     for (Aspect tag : Aspect.aspects.values()) {
/*  99 */       ThaumcraftApi.registerEntityTag("Thaumcraft.Wisp", new AspectList().add(tag, 2).add(Aspect.MAGIC, 1).add(Aspect.AIR, 1), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("Type", tag.getTag()) });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 104 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Golem", new AspectList().add(Aspect.AIR, 2).add(Aspect.EARTH, 2).add(Aspect.MAGIC, 2), new ThaumcraftApi.EntityTagsNBT[0]);
/*     */   }
/*     */   
/* 107 */   public static String[] dyes = { "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void registerItemAspects()
/*     */   {
/* 118 */     ThaumcraftApi.registerObjectTag("stone", new AspectList().add(Aspect.EARTH, 2));
/* 119 */     ThaumcraftApi.registerObjectTag("cobblestone", new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1));
/* 120 */     ThaumcraftApi.registerObjectTag("logWood", new AspectList().add(Aspect.TREE, 4));
/* 121 */     ThaumcraftApi.registerObjectTag("plankWood", new AspectList().add(Aspect.TREE, 1));
/* 122 */     ThaumcraftApi.registerObjectTag("slabWood", new AspectList().add(Aspect.TREE, 1));
/* 123 */     ThaumcraftApi.registerObjectTag("stairWood", new AspectList().add(Aspect.TREE, 1));
/* 124 */     ThaumcraftApi.registerObjectTag("stickWood", new AspectList().add(Aspect.TREE, 1));
/* 125 */     ThaumcraftApi.registerObjectTag("treeSapling", new AspectList().add(Aspect.TREE, 1).add(Aspect.PLANT, 2));
/* 126 */     ThaumcraftApi.registerObjectTag("treeLeaves", new AspectList().add(Aspect.PLANT, 1));
/*     */     
/* 128 */     for (int i = 0; i < 16; i++)
/*     */     {
/* 130 */       ThaumcraftApi.registerObjectTag(dyes[i], new AspectList().add(Aspect.SENSES, 1));
/*     */     }
/*     */     
/*     */ 
/* 134 */     ThaumcraftApi.registerObjectTag("oreLapis", new AspectList().add(Aspect.EARTH, 1).add(Aspect.SENSES, 3));
/* 135 */     ThaumcraftApi.registerObjectTag("oreDiamond", new AspectList().add(Aspect.EARTH, 1).add(Aspect.GREED, 3).add(Aspect.CRYSTAL, 3));
/* 136 */     ThaumcraftApi.registerObjectTag("gemDiamond", new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4));
/* 137 */     ThaumcraftApi.registerObjectTag("oreRedstone", new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENERGY, 2).add(Aspect.MECHANISM, 2));
/* 138 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150439_ay), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENERGY, 3).add(Aspect.MECHANISM, 2));
/* 139 */     ThaumcraftApi.registerObjectTag("oreEmerald", new AspectList().add(Aspect.EARTH, 1).add(Aspect.GREED, 4).add(Aspect.CRYSTAL, 3));
/* 140 */     ThaumcraftApi.registerObjectTag("gemEmerald", new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 5));
/* 141 */     ThaumcraftApi.registerObjectTag("oreQuartz", new AspectList().add(Aspect.EARTH, 1).add(Aspect.CRYSTAL, 3));
/* 142 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151128_bU), new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.ENERGY, 1));
/* 143 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151074_bl), new AspectList().add(Aspect.METAL, 1));
/* 144 */     ThaumcraftApi.registerObjectTag("nuggetIron", new AspectList().add(Aspect.METAL, 1));
/* 145 */     ThaumcraftApi.registerObjectTag("oreIron", new AspectList().add(Aspect.EARTH, 1).add(Aspect.METAL, 3));
/* 146 */     ThaumcraftApi.registerObjectTag("dustIron", new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1));
/* 147 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151042_j), new AspectList().add(Aspect.METAL, 4));
/* 148 */     ThaumcraftApi.registerObjectTag("oreGold", new AspectList().add(Aspect.EARTH, 1).add(Aspect.METAL, 2).add(Aspect.GREED, 1));
/* 149 */     ThaumcraftApi.registerObjectTag("dustGold", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1));
/* 150 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151043_k), new AspectList().add(Aspect.METAL, 3).add(Aspect.GREED, 2));
/* 151 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150365_q), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENERGY, 2).add(Aspect.FIRE, 1));
/* 152 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151044_h, 1, 32767), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.FIRE, 2));
/* 153 */     ThaumcraftApi.registerObjectTag("dustRedstone", new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MECHANISM, 1));
/* 154 */     ThaumcraftApi.registerObjectTag("dustGlowstone", new AspectList().add(Aspect.SENSES, 1).add(Aspect.LIGHT, 2));
/* 155 */     ThaumcraftApi.registerObjectTag("glowstone", new AspectList().add(Aspect.SENSES, 3).add(Aspect.LIGHT, 10));
/*     */     
/* 157 */     if (Config.foundCopperIngot) {
/* 158 */       ThaumcraftApi.registerObjectTag("nuggetCopper", new AspectList().add(Aspect.METAL, 1));
/* 159 */       ThaumcraftApi.registerObjectTag("ingotCopper", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 1));
/* 160 */       ThaumcraftApi.registerObjectTag("dustCopper", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1));
/* 161 */       ThaumcraftApi.registerObjectTag("oreCopper", new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 1));
/* 162 */       ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNugget, 1, 17), new AspectList().add(Aspect.ORDER, 1).add(Aspect.METAL, 5).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 2));
/*     */     }
/* 164 */     if (Config.foundTinIngot) {
/* 165 */       ThaumcraftApi.registerObjectTag("nuggetTin", new AspectList().add(Aspect.METAL, 1));
/* 166 */       ThaumcraftApi.registerObjectTag("ingotTin", new AspectList().add(Aspect.METAL, 3).add(Aspect.CRYSTAL, 1));
/* 167 */       ThaumcraftApi.registerObjectTag("dustTin", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1));
/* 168 */       ThaumcraftApi.registerObjectTag("oreTin", new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 1));
/* 169 */       ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNugget, 1, 18), new AspectList().add(Aspect.ORDER, 1).add(Aspect.METAL, 5).add(Aspect.EARTH, 1).add(Aspect.CRYSTAL, 2));
/*     */     }
/* 171 */     if (Config.foundSilverIngot) {
/* 172 */       ThaumcraftApi.registerObjectTag("nuggetSilver", new AspectList().add(Aspect.METAL, 1));
/* 173 */       ThaumcraftApi.registerObjectTag("ingotSilver", new AspectList().add(Aspect.METAL, 3).add(Aspect.GREED, 1));
/* 174 */       ThaumcraftApi.registerObjectTag("dustSilver", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1));
/* 175 */       ThaumcraftApi.registerObjectTag("oreSilver", new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.GREED, 1));
/* 176 */       ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNugget, 1, 19), new AspectList().add(Aspect.ORDER, 1).add(Aspect.METAL, 5).add(Aspect.EARTH, 1).add(Aspect.GREED, 2));
/*     */     }
/* 178 */     if (Config.foundLeadIngot) {
/* 179 */       ThaumcraftApi.registerObjectTag("nuggetLead", new AspectList().add(Aspect.METAL, 1));
/* 180 */       ThaumcraftApi.registerObjectTag("ingotLead", new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 1));
/* 181 */       ThaumcraftApi.registerObjectTag("dustLead", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.ORDER, 1));
/* 182 */       ThaumcraftApi.registerObjectTag("oreLead", new AspectList().add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1).add(Aspect.ORDER, 1));
/* 183 */       ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNugget, 1, 20), new AspectList().add(Aspect.ORDER, 1).add(Aspect.METAL, 5).add(Aspect.EARTH, 1).add(Aspect.ORDER, 2));
/*     */     }
/*     */     
/*     */ 
/* 187 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150357_h), new AspectList().add(Aspect.VOID, 16).add(Aspect.ENTROPY, 16).add(Aspect.EARTH, 16).add(Aspect.DARKNESS, 16));
/* 188 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150346_d, 1, 32767), new AspectList().add(Aspect.EARTH, 2));
/* 189 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150346_d, 1, 2), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1));
/* 190 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150458_ak, 1, 32767), new AspectList().add(Aspect.EARTH, 1).add(Aspect.HARVEST, 2));
/* 191 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150354_m, 1, 32767), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1));
/* 192 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150349_c), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1));
/* 193 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151119_aD, 1, 32767), new AspectList().add(Aspect.WATER, 1).add(Aspect.EARTH, 1));
/* 194 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150377_bs), new AspectList().add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1));
/* 195 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150351_n), new AspectList().add(Aspect.EARTH, 1).add(Aspect.EARTH, 1));
/* 196 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150391_bh), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1));
/* 197 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150435_aG, 1, 32767), new AspectList().add(Aspect.EARTH, 3).add(Aspect.WATER, 3));
/* 198 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150405_ch, 1, 32767), new AspectList().add(Aspect.EARTH, 4).add(Aspect.FIRE, 1));
/* 199 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150406_ce, 1, 32767), new AspectList().add(Aspect.EARTH, 3).add(Aspect.FIRE, 1).add(Aspect.SENSES, 1));
/* 200 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151118_aC, 1, 32767), new AspectList().add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));
/* 201 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151130_bT, 1, 32767), new AspectList().add(Aspect.FIRE, 1));
/* 202 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150425_aM, 1, 32767), new AspectList().add(Aspect.EARTH, 1).add(Aspect.TRAP, 1).add(Aspect.SOUL, 1));
/* 203 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150424_aL, 1, 32767), new AspectList().add(Aspect.EARTH, 1).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));
/* 204 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150385_bj), new AspectList().add(Aspect.EARTH, 2).add(Aspect.FIRE, 1));
/* 205 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150359_w, 1, 32767), new AspectList().add(Aspect.CRYSTAL, 1));
/* 206 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150399_cn, 1, 32767), new AspectList().add(Aspect.CRYSTAL, 1));
/* 207 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150341_Y, 1, 32767), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1).add(Aspect.MAGIC, 1));
/* 208 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150343_Z, 1, 32767), new AspectList().add(Aspect.EARTH, 2).add(Aspect.FIRE, 2).add(Aspect.DARKNESS, 1));
/* 209 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150418_aU, 1, 32767), new AspectList().add(Aspect.EARTH, 2).add(Aspect.BEAST, 1).add(Aspect.TRAP, 1));
/* 210 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150417_aV, 1, 32767), new AspectList().add(Aspect.EARTH, 2));
/* 211 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150417_aV, 1, 1), new AspectList(new ItemStack(Blocks.field_150417_aV)).remove(Aspect.EARTH, 1).add(Aspect.PLANT, 1));
/* 212 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150417_aV, 1, 2), new AspectList(new ItemStack(Blocks.field_150417_aV)).remove(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1));
/* 213 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150417_aV, 1, 3), new AspectList(new ItemStack(Blocks.field_150417_aV)).remove(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
/* 214 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150322_A, 1, 32767), new AspectList().add(Aspect.EARTH, 1).remove(Aspect.EARTH, 1));
/* 215 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150322_A, 1, 1), new AspectList(new ItemStack(Blocks.field_150322_A)).remove(Aspect.EARTH, 1).add(Aspect.MAGIC, 1));
/* 216 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150322_A, 1, 2), new AspectList(new ItemStack(Blocks.field_150322_A)).remove(Aspect.EARTH, 1).add(Aspect.ORDER, 1));
/*     */     
/*     */ 
/* 219 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150329_H, 1, 32767), new AspectList().add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
/* 220 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150398_cm, 1, 32767), new AspectList().add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
/* 221 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150392_bi, 1, 32767), new AspectList().add(Aspect.PLANT, 2).add(Aspect.WATER, 1));
/* 222 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150330_I, 1, 32767), new AspectList().add(Aspect.PLANT, 1).add(Aspect.ENTROPY, 1));
/* 223 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150395_bd, 1, 32767), new AspectList().add(Aspect.PLANT, 1));
/* 224 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151014_N, 1, 32767), new AspectList().add(Aspect.PLANT, 1));
/* 225 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151081_bc, 1, 32767), new AspectList().add(Aspect.PLANT, 1));
/* 226 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151080_bb, 1, 32767), new AspectList().add(Aspect.PLANT, 1));
/* 227 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151127_ba, 1, 32767), new AspectList().add(Aspect.HUNGER, 1));
/* 228 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151075_bm), new AspectList().add(Aspect.PLANT, 1).add(Aspect.MAGIC, 1));
/*     */     
/* 230 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150328_O, 1, 32767), new AspectList().add(Aspect.PLANT, 1).add(Aspect.LIFE, 1).add(Aspect.SENSES, 1));
/* 231 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150327_N, 1, 32767), new AspectList().add(Aspect.PLANT, 1).add(Aspect.LIFE, 1).add(Aspect.SENSES, 1));
/* 232 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150434_aF), new AspectList().add(Aspect.PLANT, 3).add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1));
/* 233 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150338_P), new AspectList().add(Aspect.PLANT, 1).add(Aspect.DARKNESS, 1).add(Aspect.EARTH, 1));
/* 234 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150337_Q), new AspectList().add(Aspect.PLANT, 1).add(Aspect.DARKNESS, 1).add(Aspect.FIRE, 1));
/* 235 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150420_aW, 1, 32767), new AspectList().add(Aspect.PLANT, 2).add(Aspect.DARKNESS, 1).add(Aspect.EARTH, 1));
/* 236 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150419_aX, 1, 32767), new AspectList().add(Aspect.PLANT, 2).add(Aspect.DARKNESS, 1).add(Aspect.FIRE, 1));
/* 237 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151120_aE), new AspectList().add(Aspect.PLANT, 1).add(Aspect.WATER, 1).add(Aspect.AIR, 1));
/*     */     
/* 239 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151015_O), new AspectList().add(Aspect.CROP, 2).add(Aspect.HUNGER, 1));
/*     */     
/*     */ 
/*     */ 
/* 243 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151034_e), new AspectList().add(Aspect.CROP, 2).add(Aspect.HUNGER, 1));
/* 244 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151172_bF), new AspectList().add(Aspect.CROP, 1).add(Aspect.HUNGER, 1).add(Aspect.SENSES, 1));
/* 245 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151174_bG), new AspectList().add(Aspect.CROP, 1).add(Aspect.HUNGER, 1).add(Aspect.EARTH, 1));
/* 246 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151168_bH), new AspectList().add(Aspect.CROP, 1).add(Aspect.HUNGER, 2));
/* 247 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151170_bI), new AspectList().add(Aspect.CROP, 1).add(Aspect.POISON, 2));
/* 248 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150423_aK, 1, 32767), new AspectList().add(Aspect.CROP, 2));
/* 249 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150440_ba, 1, 32767), new AspectList().add(Aspect.CROP, 2).remove(Aspect.HUNGER, 4));
/*     */     
/*     */ 
/* 252 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151057_cb, 1, 32767), new AspectList().add(Aspect.MIND, 2).add(Aspect.BEAST, 2));
/* 253 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151138_bX, 1, 32767), new AspectList().add(Aspect.ARMOR, 2).add(Aspect.BEAST, 2));
/* 254 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151136_bY, 1, 32767), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.BEAST, 2));
/* 255 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151125_bZ, 1, 32767), new AspectList().add(Aspect.ARMOR, 6).add(Aspect.BEAST, 2));
/* 256 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150480_ab, 1, 32767), new AspectList().add(Aspect.FIRE, 4));
/*     */     
/* 258 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150355_j, 1, 32767), new AspectList().add(Aspect.WATER, 4));
/* 259 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150353_l, 1, 32767), new AspectList().add(Aspect.FIRE, 3).add(Aspect.EARTH, 1));
/* 260 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150432_aD, 1, 32767), new AspectList().add(Aspect.COLD, 4));
/* 261 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150403_cj, 1, 32767), new AspectList().add(Aspect.COLD, 3).add(Aspect.EARTH, 1));
/* 262 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151126_ay, 1, 32767), new AspectList().add(Aspect.COLD, 1));
/* 263 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151106_aX, 1, 32767), new AspectList().add(Aspect.HUNGER, 1));
/* 264 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151068_bn), new AspectList().add(Aspect.WATER, 1).add(Aspect.CRYSTAL, 1));
/* 265 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150478_aa, 1, 32767), new AspectList().add(Aspect.LIGHT, 1));
/* 266 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150321_G, 1, 32767), new AspectList().add(Aspect.TRAP, 2).add(Aspect.CLOTH, 1));
/* 267 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151145_ak, 1, 32767), new AspectList().add(Aspect.EARTH, 1).add(Aspect.TOOL, 1));
/* 268 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151007_F, 1, 32767), new AspectList().add(Aspect.BEAST, 1).add(Aspect.CLOTH, 1));
/* 269 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151123_aH), new AspectList().add(Aspect.SLIME, 2));
/* 270 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151116_aA, 1, 32767), new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 1).add(Aspect.ARMOR, 1));
/* 271 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151078_bh, 1, 32767), new AspectList().add(Aspect.MAN, 1).add(Aspect.FLESH, 2));
/* 272 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151008_G, 1, 32767), new AspectList().add(Aspect.FLIGHT, 2).add(Aspect.AIR, 1));
/* 273 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151103_aS, 1, 32767), new AspectList().add(Aspect.DEATH, 2).add(Aspect.FLESH, 1));
/* 274 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151110_aK, 1, 32767), new AspectList().add(Aspect.SLIME, 1).add(Aspect.LIFE, 1).add(Aspect.BEAST, 1));
/* 275 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151070_bp, 1, 32767), new AspectList().add(Aspect.SENSES, 2).add(Aspect.BEAST, 2).add(Aspect.POISON, 2));
/* 276 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151016_H, 1, 32767), new AspectList().add(Aspect.FIRE, 4).add(Aspect.ENTROPY, 4));
/* 277 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150325_L, 1, 32767), new AspectList().add(Aspect.CLOTH, 4).add(Aspect.CRAFT, 1));
/* 278 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151115_aP, 1, 32767), new AspectList().add(Aspect.FLESH, 3).add(Aspect.LIFE, 1).add(Aspect.WATER, 1));
/* 279 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151101_aQ, 1, 32767), new AspectList().add(Aspect.CRAFT, 1).add(Aspect.FLESH, 4).add(Aspect.HUNGER, 3));
/* 280 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151076_bf, 1, 32767), new AspectList().add(Aspect.FLESH, 3).add(Aspect.LIFE, 2).add(Aspect.BEAST, 1));
/* 281 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151077_bg, 1, 32767), new AspectList().add(Aspect.CRAFT, 1).add(Aspect.FLESH, 4).add(Aspect.HUNGER, 3));
/* 282 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151147_al, 1, 32767), new AspectList().add(Aspect.FLESH, 3).add(Aspect.LIFE, 1).add(Aspect.BEAST, 1));
/* 283 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151157_am, 1, 32767), new AspectList().add(Aspect.CRAFT, 1).add(Aspect.FLESH, 3).add(Aspect.HUNGER, 3));
/* 284 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151082_bd, 1, 32767), new AspectList().add(Aspect.FLESH, 4).add(Aspect.LIFE, 2).add(Aspect.BEAST, 1));
/* 285 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151083_be, 1, 32767), new AspectList().add(Aspect.CRAFT, 1).add(Aspect.FLESH, 4).add(Aspect.HUNGER, 4));
/* 286 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151072_bj, 1, 32767), new AspectList().add(Aspect.FIRE, 4).add(Aspect.MAGIC, 2));
/* 287 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151141_av, 1, 32767), new AspectList().add(Aspect.BEAST, 2).add(Aspect.CLOTH, 3).add(Aspect.TRAVEL, 3));
/* 288 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151079_bi, 1, 32767), new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.MAGIC, 2).add(Aspect.TRAVEL, 4));
/* 289 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151073_bk, 1, 32767), new AspectList().add(Aspect.WATER, 1).add(Aspect.UNDEAD, 4).add(Aspect.SOUL, 4));
/* 290 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151144_bL, 1, 0), new AspectList().add(Aspect.DEATH, 4).add(Aspect.SOUL, 4).add(Aspect.UNDEAD, 4));
/* 291 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151144_bL, 1, 1), new AspectList().add(Aspect.DEATH, 4).add(Aspect.SOUL, 4).add(Aspect.UNDEAD, 4).add(Aspect.POISON, 4));
/* 292 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151144_bL, 1, 2), new AspectList().add(Aspect.DEATH, 4).add(Aspect.SOUL, 4).add(Aspect.FLESH, 4));
/* 293 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151144_bL, 1, 3), new AspectList().add(Aspect.DEATH, 4).add(Aspect.SOUL, 4).add(Aspect.MAN, 4));
/* 294 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151144_bL, 1, 4), new AspectList().add(Aspect.DEATH, 4).add(Aspect.SOUL, 4).add(Aspect.ENTROPY, 2).add(Aspect.FIRE, 2));
/*     */     
/* 296 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151086_cn), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.GREED, 4));
/* 297 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151096_cd), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.WATER, 4).add(Aspect.GREED, 4));
/* 298 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151093_ce), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.BEAST, 4).add(Aspect.GREED, 4));
/* 299 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151091_cg), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.EARTH, 4).add(Aspect.GREED, 4));
/* 300 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151092_ch), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.ELDRITCH, 4).add(Aspect.GREED, 4));
/* 301 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151089_ci), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.MAN, 4).add(Aspect.GREED, 4));
/* 302 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151090_cj), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.CRAFT, 4).add(Aspect.GREED, 4));
/* 303 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151087_ck), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.DARKNESS, 4).add(Aspect.GREED, 4));
/* 304 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151088_cl), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.MECHANISM, 4).add(Aspect.GREED, 4));
/* 305 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151085_cm), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.MAGIC, 4).add(Aspect.GREED, 4));
/* 306 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151094_cf), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.TOOL, 4).add(Aspect.GREED, 4));
/* 307 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151084_co), new AspectList().add(Aspect.SENSES, 4).add(Aspect.AIR, 4).add(Aspect.TRAP, 4).add(Aspect.GREED, 4));
/*     */     
/* 309 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151156_bN), new AspectList().add(Aspect.ELDRITCH, 8).add(Aspect.MAGIC, 8).add(Aspect.ORDER, 8).add(Aspect.LIGHT, 8));
/*     */     
/* 311 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151020_U, 1, 32767), new AspectList().add(Aspect.METAL, 8));
/* 312 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151023_V, 1, 32767), new AspectList().add(Aspect.METAL, 12));
/* 313 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151022_W, 1, 32767), new AspectList().add(Aspect.METAL, 11));
/* 314 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151029_X, 1, 32767), new AspectList().add(Aspect.METAL, 7));
/*     */     
/* 316 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151121_aF), new AspectList().add(Aspect.MIND, 1));
/* 317 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151122_aG), new AspectList().add(Aspect.MIND, 3));
/* 318 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151134_bR), new AspectList(new ItemStack(Items.field_151122_aG)));
/* 319 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150342_X), new AspectList().add(Aspect.MIND, 8));
/* 320 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150380_bt), new AspectList().add(Aspect.ELDRITCH, 8).add(Aspect.BEAST, 8).add(Aspect.MAGIC, 8));
/* 321 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150427_aO, 1, 32767), new AspectList().add(Aspect.FIRE, 4).add(Aspect.TRAVEL, 4));
/* 322 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150384_bq, 1, 32767), new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.TRAVEL, 4));
/* 323 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150378_br, 1, 32767), new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.MECHANISM, 4).add(Aspect.TRAVEL, 4));
/* 324 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150474_ac, 1, 32767), new AspectList().add(Aspect.BEAST, 4).add(Aspect.TRAVEL, 4).add(Aspect.UNDEAD, 4).add(Aspect.MAGIC, 4));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 330 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151061_bv), new AspectList().add(Aspect.SENSES, 4));
/* 331 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151032_g), new AspectList().add(Aspect.WEAPON, 1));
/* 332 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151069_bo), new AspectList().add(Aspect.VOID, 1));
/*     */     
/* 334 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150486_ae, 1, 32767), new AspectList().add(Aspect.VOID, 4));
/* 335 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151153_ao, 1, 0), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.HEAL, 4));
/* 336 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151153_ao, 1, 1), new AspectList().add(Aspect.MAGIC, 4).add(Aspect.HEAL, 8));
/* 337 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151054_z), new AspectList().add(Aspect.VOID, 1));
/* 338 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151009_A), new AspectList().add(Aspect.HUNGER, 4));
/* 339 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151143_au), new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.TRAVEL, 4));
/* 340 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151139_aw), new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.MOTION, 1));
/* 341 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151135_aq), new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MOTION, 1));
/* 342 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151124_az), new AspectList().add(Aspect.WATER, 4).add(Aspect.TRAVEL, 4));
/* 343 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151033_d, 1, 32767), new AspectList().add(Aspect.FIRE, 4));
/* 344 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151112_aM, 1, 32767), new AspectList().add(Aspect.WATER, 1).add(Aspect.TOOL, 1));
/*     */     
/* 346 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151133_ar), new AspectList().add(Aspect.METAL, 8).add(Aspect.VOID, 1));
/* 347 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151131_as), new AspectList(new ItemStack(Items.field_151133_ar)).add(Aspect.WATER, 4));
/* 348 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151129_at), new AspectList(new ItemStack(Items.field_151133_ar)).add(Aspect.FIRE, 4).add(Aspect.EARTH, 1));
/* 349 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151117_aB), new AspectList(new ItemStack(Items.field_151133_ar)).add(Aspect.HUNGER, 2).add(Aspect.HEAL, 2).add(Aspect.WATER, 2));
/*     */     
/* 351 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151105_aU, 32767), new AspectList().add(Aspect.WATER, 4).add(Aspect.HUNGER, 4).add(Aspect.LIFE, 4));
/*     */     
/* 353 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151067_bt), new AspectList().add(Aspect.CRAFT, 2).add(Aspect.WATER, 2));
/* 354 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150430_aB), new AspectList().add(Aspect.MECHANISM, 1));
/* 355 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150448_aq, 1, 32767), new AspectList().add(Aspect.METAL, 1).add(Aspect.TRAVEL, 1));
/* 356 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150319_E, 1, 32767), new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/* 357 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150318_D, 1, 32767), new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.ENERGY, 1));
/* 358 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150408_cc, 1, 32767), new AspectList().add(Aspect.MECHANISM, 2));
/* 359 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150396_be, 1, 32767), new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.TRAVEL, 1));
/* 360 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150452_aw, 1, 32767), new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/* 361 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150456_au, 1, 32767), new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/* 362 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150445_bS, 1, 32767), new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/* 363 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150443_bT, 1, 32767), new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
/* 364 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150442_at, 1, 32767), new AspectList().add(Aspect.MECHANISM, 1));
/* 365 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150429_aA, 1, 32767), new AspectList().add(Aspect.MECHANISM, 2));
/* 366 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150331_J, 1, 32767), new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.MOTION, 4));
/* 367 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150320_F, 1, 32767), new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.MOTION, 4));
/* 368 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150421_aI), new AspectList().add(Aspect.SENSES, 4).add(Aspect.MECHANISM, 2).add(Aspect.AIR, 4));
/* 369 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150323_B), new AspectList().add(Aspect.SENSES, 4).add(Aspect.MECHANISM, 1).add(Aspect.AIR, 4));
/* 370 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150415_aT, 1, 32767), new AspectList().add(Aspect.MOTION, 1));
/* 371 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150470_am, 1, 32767), new AspectList().add(Aspect.FIRE, 4));
/* 372 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150460_al, 1, 32767), new AspectList().add(Aspect.FIRE, 2));
/* 373 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150381_bn), new AspectList().add(Aspect.MAGIC, 8).add(Aspect.CRAFT, 4));
/* 374 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150462_ai), new AspectList().add(Aspect.CRAFT, 4));
/* 375 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151113_aN), new AspectList().add(Aspect.MECHANISM, 2));
/*     */     
/* 377 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150467_bQ), new int[] { 0, 1, 2 }, new AspectList().add(Aspect.METAL, 64).add(Aspect.CRAFT, 2).add(Aspect.TOOL, 2));
/*     */     
/*     */ 
/* 380 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150461_bJ), new AspectList().add(Aspect.AURA, 2).add(Aspect.MAGIC, 2).add(Aspect.EXCHANGE, 2));
/* 381 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150471_bO, 1, 32767), new AspectList().add(Aspect.MECHANISM, 1));
/* 382 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151146_bM, 1, 32767), new AspectList().add(Aspect.TRAVEL, 4));
/*     */     
/* 384 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151162_bE), new AspectList().add(Aspect.VOID, 1).add(Aspect.PLANT, 1));
/* 385 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151150_bK), new AspectList().add(Aspect.SENSES, 2));
/* 386 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150477_bB, 1, 32767), new AspectList().merge(Aspect.EXCHANGE, 2).merge(Aspect.TRAVEL, 2).merge(Aspect.VOID, 4));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 391 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150438_bZ, 1, 32767), new AspectList().merge(Aspect.MECHANISM, 1).merge(Aspect.EXCHANGE, 1).merge(Aspect.VOID, 1));
/* 392 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150409_cd, 1, 32767), new AspectList().merge(Aspect.MECHANISM, 1).merge(Aspect.EXCHANGE, 1).merge(Aspect.VOID, 1));
/* 393 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150367_z, 1, 32767), new AspectList().merge(Aspect.MECHANISM, 1).merge(Aspect.EXCHANGE, 1).merge(Aspect.VOID, 1));
/* 394 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150479_bC, 1, 32767), new AspectList().add(Aspect.SENSES, 1).add(Aspect.MECHANISM, 1).add(Aspect.TRAP, 1));
/*     */     
/* 396 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150453_bW, 1, 32767), new AspectList().merge(Aspect.SENSES, 2).merge(Aspect.LIGHT, 3).merge(Aspect.MECHANISM, 3));
/*     */     
/*     */ 
/* 399 */     Map lhm = new LinkedHashMap();
/* 400 */     for (int var4 = 1; var4 <= 32767; var4++)
/*     */     {
/* 402 */       List var5 = PotionHelper.func_77917_b(var4, false);
/* 403 */       if ((var5 != null) && (!var5.isEmpty()))
/*     */       {
/* 405 */         lhm.put(var5, Integer.valueOf(var4));
/*     */       }
/*     */     }
/* 408 */     Iterator var6 = lhm.values().iterator();
/* 409 */     while (var6.hasNext())
/*     */     {
/* 411 */       int var7 = ((Integer)var6.next()).intValue();
/* 412 */       ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151068_bn, 1, var7), new AspectList(new ItemStack(Items.field_151068_bn)));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 417 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(ConfigBlocks.blockTable), new AspectList().add(Aspect.TOOL, 1));
/* 418 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockTable, 1, 15), new AspectList(new ItemStack(ConfigBlocks.blockTable)).add(Aspect.CRAFT, 4));
/* 419 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockTable, 1, 2), new AspectList(new ItemStack(ConfigBlocks.blockTable)).add(Aspect.MIND, 4));
/*     */     
/* 421 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNugget, 1, 16), new AspectList().add(Aspect.ORDER, 1).add(Aspect.METAL, 6).add(Aspect.EARTH, 1));
/* 422 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNugget, 1, 31), new AspectList().add(Aspect.ORDER, 1).add(Aspect.METAL, 4).add(Aspect.EARTH, 1).add(Aspect.GREED, 2));
/* 423 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNugget, 1, 21), new AspectList().add(Aspect.ORDER, 1).add(Aspect.METAL, 4).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 4).add(Aspect.POISON, 2));
/* 424 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNugget, 1, 5), new AspectList().add(Aspect.METAL, 1));
/* 425 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNugget, 1, 6), new AspectList().add(Aspect.METAL, 1));
/* 426 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomOre, 1, 0), new AspectList().add(Aspect.EARTH, 1).add(Aspect.METAL, 2).add(Aspect.EXCHANGE, 2).add(Aspect.POISON, 1));
/* 427 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomOre, 1, 7), new AspectList().add(Aspect.EARTH, 1).add(Aspect.TRAP, 3).add(Aspect.CRYSTAL, 2));
/*     */     
/* 429 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomOre, 1, 1), new AspectList().add(Aspect.EARTH, 1).add(Aspect.AIR, 3).add(Aspect.CRYSTAL, 2));
/* 430 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomOre, 1, 2), new AspectList().add(Aspect.EARTH, 1).add(Aspect.FIRE, 3).add(Aspect.CRYSTAL, 2));
/* 431 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomOre, 1, 3), new AspectList().add(Aspect.EARTH, 1).add(Aspect.WATER, 3).add(Aspect.CRYSTAL, 2));
/* 432 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomOre, 1, 4), new AspectList().add(Aspect.EARTH, 1).add(Aspect.EARTH, 3).add(Aspect.CRYSTAL, 2));
/* 433 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomOre, 1, 5), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ORDER, 3).add(Aspect.CRYSTAL, 2));
/* 434 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomOre, 1, 6), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 3).add(Aspect.CRYSTAL, 2));
/*     */     
/* 436 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockTaint, 1, 0), new AspectList().add(Aspect.TREE, 1).add(Aspect.TAINT, 3));
/* 437 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockTaint, 1, 1), new AspectList().add(Aspect.EARTH, 1).add(Aspect.TAINT, 3));
/* 438 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockTaintFibres, 1, 0), new AspectList().add(Aspect.LIFE, 1).add(Aspect.TAINT, 2));
/* 439 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockTaintFibres, 1, 1), new AspectList().add(Aspect.PLANT, 1).add(Aspect.TAINT, 1));
/* 440 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockTaintFibres, 1, 2), new AspectList().add(Aspect.PLANT, 1).add(Aspect.TAINT, 1));
/* 441 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockTaintFibres, 1, 3), new AspectList().add(Aspect.BEAST, 1).add(Aspect.PLANT, 1).add(Aspect.TAINT, 2));
/* 442 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockTaintFibres, 1, 4), new AspectList().add(Aspect.BEAST, 1).add(Aspect.PLANT, 1).add(Aspect.TAINT, 2));
/*     */     
/* 444 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCosmeticSolid), new AspectList().add(Aspect.EARTH, 4).add(Aspect.DARKNESS, 2).add(Aspect.ELDRITCH, 2));
/* 445 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0), new AspectList().add(Aspect.TREE, 3).add(Aspect.MAGIC, 1));
/* 446 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1), new AspectList().add(Aspect.TREE, 3).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1));
/* 447 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockMagicalLeaves, 1, 0), new AspectList().add(Aspect.PLANT, 1));
/* 448 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockMagicalLeaves, 1, 1), new AspectList().add(Aspect.PLANT, 1));
/* 449 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0), new AspectList().add(Aspect.PLANT, 2).add(Aspect.TREE, 1).add(Aspect.MAGIC, 1));
/* 450 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1), new AspectList().add(Aspect.PLANT, 2).add(Aspect.TREE, 1).add(Aspect.MAGIC, 1));
/* 451 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2), new AspectList().add(Aspect.PLANT, 2).add(Aspect.EXCHANGE, 2).add(Aspect.MAGIC, 2));
/* 452 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 3), new AspectList().add(Aspect.PLANT, 2).add(Aspect.FIRE, 2).add(Aspect.MAGIC, 2));
/* 453 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCustomPlant, 1, 5), new AspectList().add(Aspect.PLANT, 2).add(Aspect.POISON, 1).add(Aspect.MAGIC, 2));
/*     */     
/* 455 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), new AspectList().add(Aspect.EARTH, 1).add(Aspect.MAGIC, 1));
/* 456 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7), new AspectList().add(Aspect.EARTH, 1).add(Aspect.MAGIC, 1));
/*     */     
/* 458 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemResource, 1, 3), new AspectList().add(Aspect.METAL, 3).add(Aspect.POISON, 1).add(Aspect.EXCHANGE, 2));
/* 459 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemZombieBrain), new AspectList().add(Aspect.FLESH, 2).add(Aspect.MIND, 4).add(Aspect.UNDEAD, 2));
/* 460 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemResource, 1, 6), new AspectList().add(Aspect.TRAP, 2).add(Aspect.CRYSTAL, 2));
/* 461 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemResource, 1, 9), new AspectList().add(Aspect.MIND, 8));
/* 462 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemResource, 1, 11), new AspectList().add(Aspect.TAINT, 3).add(Aspect.SLIME, 1));
/* 463 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemResource, 1, 12), new AspectList().add(Aspect.TAINT, 2).add(Aspect.GREED, 1).add(Aspect.HUNGER, 1));
/*     */     
/* 465 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemResource, 1, 18), new AspectList().add(Aspect.GREED, 1));
/* 466 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemLootbag, 1, 0), new AspectList().add(Aspect.GREED, 8));
/* 467 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemLootbag, 1, 1), new AspectList().add(Aspect.GREED, 16));
/* 468 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemLootbag, 1, 2), new AspectList().add(Aspect.GREED, 32));
/*     */     
/* 470 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNuggetBeef, 1, 32767), new AspectList().add(Aspect.HUNGER, 1));
/* 471 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNuggetChicken, 1, 32767), new AspectList().add(Aspect.HUNGER, 1));
/* 472 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNuggetPork, 1, 32767), new AspectList().add(Aspect.HUNGER, 1));
/* 473 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemNuggetFish, 1, 32767), new AspectList().add(Aspect.HUNGER, 1));
/* 474 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(ConfigItems.itemTripleMeatTreat, 1, 32767), new AspectList().add(Aspect.HEAL, 1).remove(Aspect.HUNGER, 1));
/*     */     
/* 476 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemShard, 1, 0), new AspectList().add(Aspect.MAGIC, 1).add(Aspect.AIR, 2).add(Aspect.CRYSTAL, 1));
/* 477 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemShard, 1, 1), new AspectList().add(Aspect.MAGIC, 1).add(Aspect.FIRE, 2).add(Aspect.CRYSTAL, 1));
/* 478 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemShard, 1, 2), new AspectList().add(Aspect.MAGIC, 1).add(Aspect.WATER, 2).add(Aspect.CRYSTAL, 1));
/* 479 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemShard, 1, 3), new AspectList().add(Aspect.MAGIC, 1).add(Aspect.EARTH, 2).add(Aspect.CRYSTAL, 1));
/* 480 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemShard, 1, 4), new AspectList().add(Aspect.MAGIC, 1).add(Aspect.ORDER, 2).add(Aspect.CRYSTAL, 1));
/* 481 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemShard, 1, 5), new AspectList().add(Aspect.MAGIC, 1).add(Aspect.ENTROPY, 2).add(Aspect.CRYSTAL, 1));
/* 482 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemResource, 1, 14), new AspectList(new ItemStack(ConfigItems.itemShard, 1, 6)).add(Aspect.MAGIC, 2).remove(Aspect.CRYSTAL));
/*     */     
/* 484 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockMetalDevice), new AspectList(new ItemStack(Items.field_151066_bu, 1, 32767)).add(Aspect.CRAFT, 4).add(Aspect.MAGIC, 4));
/* 485 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCandle), new AspectList().add(Aspect.LIGHT, 2).add(Aspect.FLESH, 1).add(Aspect.MAGIC, 1));
/*     */     
/* 487 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockAiry, 1, 2), new AspectList().add(Aspect.LIGHT, 1));
/* 488 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockAiry, 1, 3), new AspectList().add(Aspect.LIGHT, 1));
/*     */     
/* 490 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemThaumonomicon, 1, 32767), new AspectList(new ItemStack(Blocks.field_150342_X)).add(Aspect.MAGIC, 2).merge(Aspect.MIND, 2));
/*     */     
/* 492 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockAlchemyFurnace, 1, 32767), new AspectList().add(Aspect.MAGIC, 8).add(Aspect.WATER, 8).add(Aspect.CRAFT, 8));
/*     */     
/*     */ 
/* 495 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemEssence, 1, 0), new AspectList().add(Aspect.VOID, 1));
/* 496 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemEssence, 1, 1), new AspectList());
/* 497 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemWispEssence, 1, 0), new AspectList().add(Aspect.AURA, 2));
/* 498 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemCrystalEssence, 1, 0), new AspectList());
/*     */     
/*     */ 
/* 501 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(ConfigItems.itemPrimalArrow), new AspectList().add(Aspect.WEAPON, 1));
/* 502 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(ConfigItems.itemGoggles, 1, 32767), new AspectList().add(Aspect.SENSES, 4));
/* 503 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 1), new AspectList().add(Aspect.SENSES, 4));
/*     */     
/*     */ 
/* 506 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemBaubleBlanks, 1, 3), new AspectList().add(Aspect.MAGIC, 5));
/* 507 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemFocusPech), new AspectList().add(Aspect.MAGIC, 5).add(Aspect.POISON, 5).add(Aspect.ENTROPY, 5).add(Aspect.ELDRITCH, 5).add(Aspect.WEAPON, 5));
/* 508 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemChestCultistPlate, 1, 32767), new AspectList().add(Aspect.METAL, 5).add(Aspect.ELDRITCH, 1));
/* 509 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemChestCultistRobe, 1, 32767), new AspectList().add(Aspect.METAL, 3).add(Aspect.CLOTH, 2).add(Aspect.ELDRITCH, 1));
/* 510 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemChestCultistLeaderPlate, 1, 32767), new AspectList().add(Aspect.METAL, 5).add(Aspect.ELDRITCH, 2));
/* 511 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemLegsCultistPlate, 1, 32767), new AspectList().add(Aspect.METAL, 5).add(Aspect.ELDRITCH, 1));
/* 512 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemLegsCultistLeaderPlate, 1, 32767), new AspectList().add(Aspect.METAL, 5).add(Aspect.ELDRITCH, 2));
/* 513 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemLegsCultistRobe, 1, 32767), new AspectList().add(Aspect.METAL, 3).add(Aspect.CLOTH, 2).add(Aspect.ELDRITCH, 1));
/* 514 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemHelmetCultistPlate, 1, 32767), new AspectList().add(Aspect.METAL, 5).add(Aspect.ELDRITCH, 1));
/* 515 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemHelmetCultistLeaderPlate, 1, 32767), new AspectList().add(Aspect.METAL, 5).add(Aspect.ELDRITCH, 2));
/* 516 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemHelmetCultistRobe, 1, 32767), new AspectList().add(Aspect.METAL, 3).add(Aspect.CLOTH, 2).add(Aspect.ELDRITCH, 1));
/* 517 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemBootsCultist, 1, 32767), new AspectList().add(Aspect.METAL, 4).add(Aspect.ELDRITCH, 1));
/* 518 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 8), new AspectList().add(Aspect.ELDRITCH, 1).add(Aspect.TREE, 2).add(Aspect.CLOTH, 3));
/*     */     
/*     */ 
/* 521 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemEldritchObject, 1, 0), new AspectList().add(Aspect.ELDRITCH, 5).add(Aspect.AURA, 3).add(Aspect.MAGIC, 3).add(Aspect.SENSES, 3).add(Aspect.SOUL, 3));
/* 522 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemEldritchObject, 1, 1), new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 3).add(Aspect.ELDRITCH, 3).add(Aspect.SOUL, 3));
/* 523 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemEldritchObject, 1, 2), new AspectList().add(Aspect.TRAP, 4).add(Aspect.MIND, 4).add(Aspect.MECHANISM, 4));
/* 524 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new AspectList().add(Aspect.AIR, 16).add(Aspect.EARTH, 16).add(Aspect.FIRE, 16).add(Aspect.WATER, 16).add(Aspect.ORDER, 16).add(Aspect.ENTROPY, 16));
/* 525 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockEldritch, 1, 32767), new AspectList().add(Aspect.VOID, 8).add(Aspect.ELDRITCH, 8).add(Aspect.SENSES, 4));
/* 526 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockEldritchPortal), new AspectList().add(Aspect.VOID, 8).add(Aspect.ELDRITCH, 8).add(Aspect.TRAVEL, 8));
/*     */     
/* 528 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockEldritch, 1, 3), new AspectList().add(Aspect.VOID, 4).add(Aspect.ELDRITCH, 4));
/* 529 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockEldritch, 1, 4), new AspectList().add(Aspect.LIGHT, 1).add(Aspect.EARTH, 1).add(Aspect.ELDRITCH, 1));
/* 530 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockEldritch, 1, 5), new AspectList().add(Aspect.MIND, 2).add(Aspect.EARTH, 1).add(Aspect.ELDRITCH, 1));
/* 531 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockEldritch, 1, 6), new AspectList().add(Aspect.METAL, 2).add(Aspect.MECHANISM, 2).add(Aspect.ELDRITCH, 1));
/*     */     
/* 533 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 11), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ELDRITCH, 1));
/* 534 */     ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 12), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ELDRITCH, 1));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/config/ConfigAspects.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */