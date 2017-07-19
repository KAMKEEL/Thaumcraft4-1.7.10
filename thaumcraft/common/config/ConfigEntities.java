/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import cpw.mods.fml.common.event.FMLInterModComms;
/*     */ import cpw.mods.fml.common.registry.EntityRegistry;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.WorldChunkManager;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.EntityAspectOrb;
/*     */ import thaumcraft.common.entities.EntityFallingTaint;
/*     */ import thaumcraft.common.entities.EntityFollowingItem;
/*     */ import thaumcraft.common.entities.EntityItemGrate;
/*     */ import thaumcraft.common.entities.EntityPermanentItem;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.entities.ItemSpawnerEgg;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBobber;
/*     */ import thaumcraft.common.entities.golems.EntityTravelingTrunk;
/*     */ import thaumcraft.common.entities.monster.EntityBrainyZombie;
/*     */ import thaumcraft.common.entities.monster.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.EntityCultistCleric;
/*     */ import thaumcraft.common.entities.monster.EntityCultistKnight;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchCrab;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.entities.monster.EntityFireBat;
/*     */ import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
/*     */ import thaumcraft.common.entities.monster.EntityInhabitedZombie;
/*     */ import thaumcraft.common.entities.monster.EntityMindSpider;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ import thaumcraft.common.entities.monster.EntityTaintChicken;
/*     */ import thaumcraft.common.entities.monster.EntityTaintCow;
/*     */ import thaumcraft.common.entities.monster.EntityTaintCreeper;
/*     */ import thaumcraft.common.entities.monster.EntityTaintPig;
/*     */ import thaumcraft.common.entities.monster.EntityTaintSheep;
/*     */ import thaumcraft.common.entities.monster.EntityTaintSpider;
/*     */ import thaumcraft.common.entities.monster.EntityTaintSpore;
/*     */ import thaumcraft.common.entities.monster.EntityTaintSporeSwarmer;
/*     */ import thaumcraft.common.entities.monster.EntityTaintSwarm;
/*     */ import thaumcraft.common.entities.monster.EntityTaintVillager;
/*     */ import thaumcraft.common.entities.monster.EntityTaintacle;
/*     */ import thaumcraft.common.entities.monster.EntityTaintacleSmall;
/*     */ import thaumcraft.common.entities.monster.EntityWatcher;
/*     */ import thaumcraft.common.entities.monster.EntityWisp;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistPortal;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*     */ import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
/*     */ import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
/*     */ import thaumcraft.common.entities.projectile.EntityAlumentum;
/*     */ import thaumcraft.common.entities.projectile.EntityBottleTaint;
/*     */ import thaumcraft.common.entities.projectile.EntityDart;
/*     */ import thaumcraft.common.entities.projectile.EntityEldritchOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityEmber;
/*     */ import thaumcraft.common.entities.projectile.EntityExplosiveOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityFrostShard;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityPechBlast;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalArrow;
/*     */ import thaumcraft.common.entities.projectile.EntityPrimalOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityShockOrb;
/*     */ 
/*     */ public class ConfigEntities
/*     */ {
/*  70 */   public static int entWizardId = 190;
/*  71 */   public static int entBankerId = 191;
/*     */   
/*     */   public static void init()
/*     */   {
/*  75 */     int id = 0;
/*     */     
/*  77 */     EntityRegistry.registerModEntity(EntitySpecialItem.class, "SpecialItem", id++, Thaumcraft.instance, 64, 20, true);
/*  78 */     EntityRegistry.registerModEntity(EntityPermanentItem.class, "PermanentItem", id++, Thaumcraft.instance, 64, 20, true);
/*  79 */     EntityRegistry.registerModEntity(EntityFollowingItem.class, "FollowItem", id++, Thaumcraft.instance, 64, 20, false);
/*  80 */     EntityRegistry.registerModEntity(EntityAspectOrb.class, "AspectOrb", id++, Thaumcraft.instance, 120, 20, true);
/*  81 */     EntityRegistry.registerModEntity(EntityFallingTaint.class, "FallingTaint", id++, Thaumcraft.instance, 64, 3, true);
/*     */     
/*     */ 
/*  84 */     EntityRegistry.registerModEntity(EntityAlumentum.class, "Alumentum", id++, Thaumcraft.instance, 64, 20, true);
/*  85 */     EntityRegistry.registerModEntity(EntityPrimalOrb.class, "PrimalOrb", id++, Thaumcraft.instance, 64, 20, true);
/*  86 */     EntityRegistry.registerModEntity(EntityFrostShard.class, "FrostShard", id++, Thaumcraft.instance, 64, 20, true);
/*  87 */     EntityRegistry.registerModEntity(EntityDart.class, "Dart", id++, Thaumcraft.instance, 64, 20, false);
/*  88 */     EntityRegistry.registerModEntity(EntityPrimalArrow.class, "PrimalArrow", id++, Thaumcraft.instance, 64, 20, false);
/*  89 */     EntityRegistry.registerModEntity(EntityPechBlast.class, "PechBlast", id++, Thaumcraft.instance, 64, 20, true);
/*  90 */     EntityRegistry.registerModEntity(EntityEldritchOrb.class, "EldritchOrb", id++, Thaumcraft.instance, 64, 20, true);
/*  91 */     EntityRegistry.registerModEntity(EntityBottleTaint.class, "BottleTaint", id++, Thaumcraft.instance, 64, 20, true);
/*  92 */     EntityRegistry.registerModEntity(EntityGolemOrb.class, "GolemOrb", id++, Thaumcraft.instance, 64, 20, true);
/*  93 */     EntityRegistry.registerModEntity(EntityShockOrb.class, "ShockOrb", id++, Thaumcraft.instance, 64, 20, true);
/*  94 */     EntityRegistry.registerModEntity(EntityExplosiveOrb.class, "ExplosiveOrb", id++, Thaumcraft.instance, 64, 20, true);
/*  95 */     EntityRegistry.registerModEntity(EntityEmber.class, "Ember", id++, Thaumcraft.instance, 64, 20, true);
/*     */     
/*     */ 
/*  98 */     EntityRegistry.registerModEntity(EntityGolemBase.class, "Golem", id++, Thaumcraft.instance, 64, 3, true);
/*  99 */     EntityRegistry.registerModEntity(EntityTravelingTrunk.class, "TravelingTrunk", id++, Thaumcraft.instance, 64, 3, true);
/*     */     
/*     */ 
/* 102 */     EntityRegistry.registerModEntity(EntityBrainyZombie.class, "BrainyZombie", id++, Thaumcraft.instance, 64, 3, true);
/* 103 */     ItemSpawnerEgg.addMapping("BrainyZombie", 16761087, 32768);
/* 104 */     EntityRegistry.registerModEntity(EntityGiantBrainyZombie.class, "GiantBrainyZombie", id++, Thaumcraft.instance, 64, 3, true);
/* 105 */     ItemSpawnerEgg.addMapping("GiantBrainyZombie", 16761087, 16384);
/* 106 */     EntityRegistry.registerModEntity(EntityWisp.class, "Wisp", id++, Thaumcraft.instance, 64, 3, true);
/* 107 */     ItemSpawnerEgg.addMapping("Wisp", 16761087, 16777215);
/* 108 */     EntityRegistry.registerModEntity(EntityFireBat.class, "Firebat", id++, Thaumcraft.instance, 64, 3, true);
/* 109 */     ItemSpawnerEgg.addMapping("Firebat", 16761087, 12582912);
/* 110 */     EntityRegistry.registerModEntity(EntityPech.class, "Pech", id++, Thaumcraft.instance, 64, 3, true);
/* 111 */     ItemSpawnerEgg.addMapping("Pech", 16761087, 4194368);
/* 112 */     EntityRegistry.registerModEntity(EntityMindSpider.class, "MindSpider", id++, Thaumcraft.instance, 64, 3, true);
/* 113 */     ItemSpawnerEgg.addMapping("MindSpider", 11184810, 4210752);
/* 114 */     EntityRegistry.registerModEntity(EntityEldritchGuardian.class, "EldritchGuardian", id++, Thaumcraft.instance, 64, 3, true);
/* 115 */     ItemSpawnerEgg.addMapping("EldritchGuardian", 2236962, 4210752);
/* 116 */     EntityRegistry.registerModEntity(EntityEldritchWarden.class, "EldritchWarden", id++, Thaumcraft.instance, 64, 3, true);
/* 117 */     ItemSpawnerEgg.addMapping("EldritchWarden", 5579298, 4210752);
/* 118 */     EntityRegistry.registerModEntity(EntityCultistKnight.class, "CultistKnight", id++, Thaumcraft.instance, 64, 3, true);
/* 119 */     ItemSpawnerEgg.addMapping("CultistKnight", 16732245, 128);
/* 120 */     EntityRegistry.registerModEntity(EntityCultistCleric.class, "CultistCleric", id++, Thaumcraft.instance, 64, 3, true);
/* 121 */     ItemSpawnerEgg.addMapping("CultistCleric", 16732245, 8388608);
/* 122 */     EntityRegistry.registerModEntity(EntityCultistLeader.class, "CultistLeader", id++, Thaumcraft.instance, 64, 3, true);
/* 123 */     ItemSpawnerEgg.addMapping("CultistLeader", 16732245, 5263440);
/* 124 */     EntityRegistry.registerModEntity(EntityCultistPortal.class, "CultistPortal", id++, Thaumcraft.instance, 64, 20, false);
/* 125 */     ItemSpawnerEgg.addMapping("CultistPortal", 16732245, 16732415);
/* 126 */     EntityRegistry.registerModEntity(EntityEldritchGolem.class, "EldritchGolem", id++, Thaumcraft.instance, 64, 3, true);
/* 127 */     ItemSpawnerEgg.addMapping("EldritchGolem", 5592405, 4210752);
/* 128 */     EntityRegistry.registerModEntity(EntityEldritchCrab.class, "EldritchCrab", id++, Thaumcraft.instance, 64, 3, true);
/* 129 */     ItemSpawnerEgg.addMapping("EldritchCrab", 5592405, 5570560);
/* 130 */     EntityRegistry.registerModEntity(EntityInhabitedZombie.class, "InhabitedZombie", id++, Thaumcraft.instance, 64, 3, true);
/* 131 */     ItemSpawnerEgg.addMapping("InhabitedZombie", 5601109, 5570560);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 136 */     EntityRegistry.registerModEntity(thaumcraft.common.entities.monster.EntityThaumicSlime.class, "ThaumSlime", id++, Thaumcraft.instance, 64, 3, true);
/* 137 */     ItemSpawnerEgg.addMapping("ThaumSlime", 16761087, 16744703);
/* 138 */     EntityRegistry.registerModEntity(EntityTaintSpider.class, "TaintSpider", id++, Thaumcraft.instance, 64, 3, true);
/* 139 */     ItemSpawnerEgg.addMapping("TaintSpider", 16761087, 4210752);
/* 140 */     EntityRegistry.registerModEntity(EntityTaintacle.class, "Taintacle", id++, Thaumcraft.instance, 64, 3, false);
/* 141 */     ItemSpawnerEgg.addMapping("Taintacle", 16761087, 8388736);
/* 142 */     EntityRegistry.registerModEntity(EntityTaintacleSmall.class, "TaintacleTiny", id++, Thaumcraft.instance, 64, 3, false);
/* 143 */     ItemSpawnerEgg.addMapping("TaintacleTiny", 16761087, 8388752);
/* 144 */     EntityRegistry.registerModEntity(EntityTaintSpore.class, "TaintSpore", id++, Thaumcraft.instance, 64, 20, false);
/* 145 */     ItemSpawnerEgg.addMapping("TaintSpore", 16761087, 8388720);
/* 146 */     EntityRegistry.registerModEntity(EntityTaintSporeSwarmer.class, "TaintSwarmer", id++, Thaumcraft.instance, 64, 20, false);
/* 147 */     ItemSpawnerEgg.addMapping("TaintSwarmer", 16761087, 8388704);
/* 148 */     EntityRegistry.registerModEntity(EntityTaintSwarm.class, "TaintSwarm", id++, Thaumcraft.instance, 64, 3, true);
/* 149 */     ItemSpawnerEgg.addMapping("TaintSwarm", 16761087, 8388688);
/* 150 */     EntityRegistry.registerModEntity(EntityTaintChicken.class, "TaintedChicken", id++, Thaumcraft.instance, 64, 3, true);
/* 151 */     ItemSpawnerEgg.addMapping("TaintedChicken", 16761087, 12632256);
/* 152 */     EntityRegistry.registerModEntity(EntityTaintCow.class, "TaintedCow", id++, Thaumcraft.instance, 64, 3, true);
/* 153 */     ItemSpawnerEgg.addMapping("TaintedCow", 16761087, 8272443);
/* 154 */     EntityRegistry.registerModEntity(EntityTaintCreeper.class, "TaintedCreeper", id++, Thaumcraft.instance, 64, 3, true);
/* 155 */     ItemSpawnerEgg.addMapping("TaintedCreeper", 16761087, 65280);
/* 156 */     EntityRegistry.registerModEntity(EntityTaintPig.class, "TaintedPig", id++, Thaumcraft.instance, 64, 3, true);
/* 157 */     ItemSpawnerEgg.addMapping("TaintedPig", 16761087, 15702511);
/* 158 */     EntityRegistry.registerModEntity(EntityTaintSheep.class, "TaintedSheep", id++, Thaumcraft.instance, 64, 3, true);
/* 159 */     ItemSpawnerEgg.addMapping("TaintedSheep", 16761087, 8421504);
/* 160 */     EntityRegistry.registerModEntity(EntityTaintVillager.class, "TaintedVillager", id++, Thaumcraft.instance, 64, 3, true);
/* 161 */     ItemSpawnerEgg.addMapping("TaintedVillager", 16761087, 65535);
/* 162 */     EntityRegistry.registerModEntity(EntityTaintacleGiant.class, "TaintacleGiant", id++, Thaumcraft.instance, 64, 3, false);
/* 163 */     ItemSpawnerEgg.addMapping("TaintacleGiant", 16761087, 8421504);
/*     */     
/* 165 */     EntityRegistry.registerModEntity(EntityItemGrate.class, "SpecialItemGrate", id++, Thaumcraft.instance, 64, 20, true);
/* 166 */     EntityRegistry.registerModEntity(EntityGolemBobber.class, "GolemBobber", id++, Thaumcraft.instance, 64, 64, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void initEntitySpawns()
/*     */   {
/* 174 */     List<BiomeGenBase> biomes = WorldChunkManager.allowedBiomes;
/* 175 */     BiomeGenBase[] allBiomes = (BiomeGenBase[])biomes.toArray(new BiomeGenBase[] { null });
/*     */     
/* 177 */     if (Config.spawnAngryZombie) {
/* 178 */       for (BiomeGenBase bgb : biomes) {
/* 179 */         if (((bgb.func_76747_a(EnumCreatureType.monster) != null ? 1 : 0) & (bgb.func_76747_a(EnumCreatureType.monster).size() > 0 ? 1 : 0)) != 0)
/*     */         {
/* 181 */           EntityRegistry.addSpawn(EntityBrainyZombie.class, 10, 1, 1, EnumCreatureType.monster, new BiomeGenBase[] { bgb });
/*     */         }
/*     */       }
/*     */     }
/* 185 */     if (Config.spawnPech) {
/* 186 */       for (BiomeGenBase bgb : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.MAGICAL)) {
/* 187 */         if (((bgb.func_76747_a(EnumCreatureType.monster) != null ? 1 : 0) & (bgb.func_76747_a(EnumCreatureType.monster).size() > 0 ? 1 : 0)) != 0)
/*     */         {
/* 189 */           EntityRegistry.addSpawn(EntityPech.class, 10, 1, 1, EnumCreatureType.monster, new BiomeGenBase[] { bgb });
/*     */         }
/*     */       }
/*     */     }
/* 193 */     if (Config.spawnFireBat) {
/* 194 */       EntityRegistry.addSpawn(EntityFireBat.class, 10, 1, 2, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.NETHER));
/*     */       
/*     */ 
/* 197 */       Calendar calendar = Calendar.getInstance();
/* 198 */       calendar.setTimeInMillis(System.currentTimeMillis());
/* 199 */       if ((calendar.get(2) + 1 == 10) && (calendar.get(5) == 31)) {
/* 200 */         EntityRegistry.addSpawn(EntityFireBat.class, 5, 1, 2, EnumCreatureType.monster, (BiomeGenBase[])biomes.toArray(allBiomes));
/*     */       }
/*     */     }
/*     */     
/* 204 */     if (Config.spawnWisp) {
/* 205 */       EntityRegistry.addSpawn(EntityWisp.class, 5, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.NETHER));
/*     */     }
/*     */     
/*     */ 
/* 209 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Zombie:0");
/* 210 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Spider:0");
/* 211 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Blaze:0");
/* 212 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Enderman:0");
/* 213 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Skeleton:0");
/* 214 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Witch:1");
/*     */     
/* 216 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Thaumcraft.EldritchCrab:0");
/* 217 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Thaumcraft.Taintacle:2");
/* 218 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Thaumcraft.Wisp:1");
/* 219 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Thaumcraft.InhabitedZombie:3");
/*     */     
/* 221 */     championModWhitelist.put(EntityCultist.class, Integer.valueOf(1));
/* 222 */     championModWhitelist.put(EntityWatcher.class, Integer.valueOf(2));
/* 223 */     championModWhitelist.put(EntityPech.class, Integer.valueOf(1));
/* 224 */     championModWhitelist.put(EntityThaumcraftBoss.class, Integer.valueOf(200));
/*     */   }
/*     */   
/* 227 */   public static HashMap<Class, Integer> championModWhitelist = new HashMap();
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/config/ConfigEntities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */