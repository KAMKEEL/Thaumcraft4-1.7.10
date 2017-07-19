/*     */ package thaumcraft.common.lib.world;
/*     */ 
/*     */ import cpw.mods.fml.common.IWorldGenerator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.ChunkPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.feature.WorldGenBlockBlob;
/*     */ import net.minecraft.world.gen.feature.WorldGenMinable;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraft.world.gen.structure.MapGenScatteredFeature;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeManager;
/*     */ import net.minecraftforge.common.BiomeManager.BiomeEntry;
/*     */ import net.minecraftforge.common.BiomeManager.BiomeType;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.nodes.NodeModifier;
/*     */ import thaumcraft.api.nodes.NodeType;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.biomes.BiomeHandler;
/*     */ import thaumcraft.common.tiles.TileNode;
/*     */ 
/*     */ public class ThaumcraftWorldGenerator implements IWorldGenerator
/*     */ {
/*     */   public static BiomeGenBase biomeTaint;
/*     */   public static BiomeGenBase biomeEerie;
/*     */   public static BiomeGenBase biomeMagicalForest;
/*     */   public static BiomeGenBase biomeEldritchLands;
/*  47 */   static Collection<Aspect> c = Aspect.aspects.values();
/*  48 */   static ArrayList<Aspect> basicAspects = new ArrayList();
/*  49 */   static ArrayList<Aspect> complexAspects = new ArrayList();
/*     */   
/*  51 */   public static HashMap<Integer, Integer> dimensionBlacklist = new HashMap();
/*  52 */   public static HashMap<Integer, Integer> biomeBlacklist = new HashMap();
/*     */   
/*     */ 
/*     */   public static int getFirstFreeBiomeSlot(int old)
/*     */   {
/*  57 */     for (int a = 0; a < BiomeGenBase.func_150565_n().length; a++) {
/*  58 */       if (BiomeGenBase.func_150565_n()[a] == null) {
/*  59 */         thaumcraft.common.Thaumcraft.log.warn("Biome slot " + old + " already occupied. Using first free biome slot at " + a);
/*  60 */         return a;
/*     */       }
/*     */     }
/*  63 */     return -1;
/*     */   }
/*     */   
/*     */   public void initialize()
/*     */   {
/*  68 */     thaumcraft.common.lib.world.biomes.BiomeGenTaint.blobs = new WorldGenBlockBlob(ConfigBlocks.blockTaint, 0);
/*     */     
/*  70 */     BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeMagicalForest, Config.biomeMagicalForestWeight));
/*  71 */     BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biomeMagicalForest, Config.biomeMagicalForestWeight));
/*  72 */     BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeTaint, Config.biomeTaintWeight));
/*  73 */     BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biomeTaint, Config.biomeTaintWeight));
/*     */   }
/*     */   
/*     */   public static void addDimBlacklist(int dim, int level) {
/*  77 */     dimensionBlacklist.put(Integer.valueOf(dim), Integer.valueOf(level));
/*     */   }
/*     */   
/*     */   public static int getDimBlacklist(int dim) {
/*  81 */     if (!dimensionBlacklist.containsKey(Integer.valueOf(dim))) return -1; return ((Integer)dimensionBlacklist.get(Integer.valueOf(dim))).intValue();
/*     */   }
/*     */   
/*     */   public static void addBiomeBlacklist(int biome, int level) {
/*  85 */     biomeBlacklist.put(Integer.valueOf(biome), Integer.valueOf(level));
/*     */   }
/*     */   
/*     */   public static int getBiomeBlacklist(int biome) {
/*  89 */     if (!biomeBlacklist.containsKey(Integer.valueOf(biome))) return -1; return ((Integer)biomeBlacklist.get(Integer.valueOf(biome))).intValue();
/*     */   }
/*     */   
/*     */ 
/*     */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
/*     */   {
/*  95 */     worldGeneration(random, chunkX, chunkZ, world, true);
/*     */   }
/*     */   
/*     */ 
/*     */   public void worldGeneration(Random random, int chunkX, int chunkZ, World world, boolean newGen)
/*     */   {
/* 101 */     if (world.field_73011_w.field_76574_g == Config.dimensionOuterId) {
/* 102 */       thaumcraft.common.lib.world.dim.MazeHandler.generateEldritch(world, random, chunkX, chunkZ);
/* 103 */       world.func_72964_e(chunkX, chunkZ).func_76630_e();
/*     */     } else {
/* 105 */       switch (world.field_73011_w.field_76574_g) {
/*     */       case -1: 
/* 107 */         generateNether(world, random, chunkX, chunkZ, newGen); break;
/*     */       case 1: 
/*     */         break; default:  generateSurface(world, random, chunkX, chunkZ, newGen);
/*     */       }
/*     */       
/* 112 */       if (!newGen) {
/* 113 */         world.func_72964_e(chunkX, chunkZ).func_76630_e();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean generateTotem(World world, Random random, int chunkX, int chunkZ, boolean auraGen, boolean newGen)
/*     */   {
/* 123 */     if ((Config.genStructure) && ((world.field_73011_w.field_76574_g == 0) || (world.field_73011_w.field_76574_g == 1)) && (newGen) && (!auraGen) && (random.nextInt(Config.nodeRarity * 10) == 0)) {
/* 124 */       int x = chunkX * 16 + random.nextInt(16);
/* 125 */       int z = chunkZ * 16 + random.nextInt(16);
/*     */       
/* 127 */       int topy = world.field_73011_w.field_76574_g == -1 ? Utils.getFirstUncoveredY(world, x, z) - 1 : world.func_72976_f(x, z) - 1;
/*     */       
/*     */ 
/* 130 */       if (topy > world.func_72940_L()) { return false;
/*     */       }
/* 132 */       while ((world.func_147439_a(x, topy, z) != null) && (world.func_147439_a(x, topy, z).isLeaves(world, x, topy, z)) && 
/*     */       
/* 134 */         (world.func_147439_a(x, --topy, z) != Blocks.field_150349_c) && (topy > 40)) {}
/*     */       
/* 136 */       if ((world.func_147439_a(x, topy, z) == Blocks.field_150431_aC) || (world.func_147439_a(x, topy, z) == Blocks.field_150329_H))
/*     */       {
/* 138 */         topy--; }
/* 139 */       if ((world.func_147439_a(x, topy, z) == Blocks.field_150349_c) || (world.func_147439_a(x, topy, z) == Blocks.field_150354_m) || (world.func_147439_a(x, topy, z) == Blocks.field_150346_d) || (world.func_147439_a(x, topy, z) == Blocks.field_150348_b) || (world.func_147439_a(x, topy, z) == Blocks.field_150424_aL))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 144 */         int count = 1;
/*     */         
/*     */ 
/*     */ 
/* 148 */         while (((world.func_147437_c(x, topy + count, z)) || (world.func_147439_a(x, topy + count, z) == Blocks.field_150431_aC) || (world.func_147439_a(x, topy + count, z) == Blocks.field_150329_H)) && (count < 3)) count++;
/* 149 */         if (count >= 2) {
/* 150 */           world.func_147465_d(x, topy, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
/* 151 */           count = 1;
/*     */           
/*     */ 
/* 154 */           while (((world.func_147437_c(x, topy + count, z)) || (world.func_147439_a(x, topy + count, z) == Blocks.field_150431_aC) || (world.func_147439_a(x, topy + count, z) == Blocks.field_150329_H)) && (count < 5)) {
/* 155 */             world.func_147465_d(x, topy + count, z, ConfigBlocks.blockCosmeticSolid, 0, 3);
/* 156 */             if ((count > 1) && (random.nextInt(4) == 0)) {
/* 157 */               world.func_147465_d(x, topy + count, z, ConfigBlocks.blockCosmeticSolid, 8, 3);
/* 158 */               createRandomNodeAt(world, x, topy + count, z, random, false, true, false);
/* 159 */               count = 5;
/* 160 */               auraGen = true;
/*     */             }
/* 162 */             count++;
/* 163 */             if ((count >= 5) && (!auraGen)) {
/* 164 */               world.func_147465_d(x, topy + 5, z, ConfigBlocks.blockCosmeticSolid, 8, 3);
/* 165 */               createRandomNodeAt(world, x, topy + 5, z, random, false, true, false);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 172 */     return false;
/*     */   }
/*     */   
/*     */   private boolean generateWildNodes(World world, Random random, int chunkX, int chunkZ, boolean auraGen, boolean newGen) {
/* 176 */     if ((Config.genAura) && (random.nextInt(Config.nodeRarity) == 0) && (!auraGen)) {
/* 177 */       int x = chunkX * 16 + random.nextInt(16);
/* 178 */       int z = chunkZ * 16 + random.nextInt(16);
/*     */       
/* 180 */       int q = Utils.getFirstUncoveredY(world, x, z);
/* 181 */       if (q < 2) q = world.field_73011_w.func_76557_i() + random.nextInt(64) - 32 + Utils.getFirstUncoveredY(world, x, z);
/* 182 */       if (q < 2) { q = 32 + random.nextInt(64);
/*     */       }
/* 184 */       if (world.func_147437_c(x, q + 1, z)) { q++;
/*     */       }
/* 186 */       int p = random.nextInt(4);
/* 187 */       Block b = world.func_147439_a(x, q + p, z);
/* 188 */       if ((world.func_147437_c(x, q + p, z)) || (b.isReplaceable(world, x, q + p, z))) {
/* 189 */         q += p;
/*     */       }
/* 191 */       int y = q;
/* 192 */       if (y > world.func_72940_L()) return false;
/* 193 */       createRandomNodeAt(world, x, y, z, random, false, false, false);
/*     */       
/* 195 */       return true;
/*     */     }
/* 197 */     return false;
/*     */   }
/*     */   
/*     */   public static void createRandomNodeAt(World world, int x, int y, int z, Random random, boolean silverwood, boolean eerie, boolean small)
/*     */   {
/* 202 */     if (basicAspects.size() == 0) {
/* 203 */       for (Aspect as : c) {
/* 204 */         if (as.getComponents() != null) {
/* 205 */           complexAspects.add(as);
/*     */         } else {
/* 207 */           basicAspects.add(as);
/*     */         }
/*     */       }
/*     */     }
/* 211 */     NodeType type = NodeType.NORMAL;
/* 212 */     if (silverwood) {
/* 213 */       type = NodeType.PURE;
/*     */     }
/* 215 */     else if (eerie) {
/* 216 */       type = NodeType.DARK;
/*     */     }
/* 218 */     else if (random.nextInt(Config.specialNodeRarity) == 0) {
/* 219 */       switch (random.nextInt(10)) {
/* 220 */       case 0: case 1: case 2:  type = NodeType.DARK; break;
/* 221 */       case 3: case 4: case 5:  type = NodeType.UNSTABLE; break;
/* 222 */       case 6: case 7: case 8:  type = NodeType.PURE; break;
/* 223 */       case 9:  type = NodeType.HUNGRY;
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 228 */     NodeModifier modifier = null;
/* 229 */     if (random.nextInt(Config.specialNodeRarity / 2) == 0) {
/* 230 */       switch (random.nextInt(3)) {
/* 231 */       case 0:  modifier = NodeModifier.BRIGHT; break;
/* 232 */       case 1:  modifier = NodeModifier.PALE; break;
/* 233 */       case 2:  modifier = NodeModifier.FADING;
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 238 */     BiomeGenBase bg = world.func_72807_a(x, z);
/* 239 */     int baura = BiomeHandler.getBiomeAura(bg);
/*     */     
/* 241 */     if ((type != NodeType.PURE) && (bg.field_76756_M == biomeTaint.field_76756_M)) {
/* 242 */       baura = (int)(baura * 1.5F);
/* 243 */       if (random.nextBoolean()) {
/* 244 */         type = NodeType.TAINTED;
/* 245 */         baura = (int)(baura * 1.5F);
/*     */       }
/*     */     }
/*     */     
/* 249 */     if ((silverwood) || (small)) { baura /= 4;
/*     */     }
/* 251 */     int value = random.nextInt(baura / 2) + baura / 2;
/*     */     
/* 253 */     Aspect ra = BiomeHandler.getRandomBiomeTag(bg.field_76756_M, random);
/* 254 */     AspectList al = new AspectList();
/*     */     
/* 256 */     if (ra != null) {
/* 257 */       al.add(ra, 2);
/*     */     } else {
/* 259 */       Aspect aa = (Aspect)complexAspects.get(random.nextInt(complexAspects.size()));
/* 260 */       al.add(aa, 1);
/* 261 */       aa = (Aspect)basicAspects.get(random.nextInt(basicAspects.size()));
/* 262 */       al.add(aa, 1);
/*     */     }
/*     */     
/*     */ 
/* 266 */     for (int a = 0; a < 3; a++) {
/* 267 */       if (random.nextBoolean()) {
/* 268 */         if (random.nextInt(Config.specialNodeRarity) == 0) {
/* 269 */           Aspect aa = (Aspect)complexAspects.get(random.nextInt(complexAspects.size()));
/* 270 */           al.merge(aa, 1);
/*     */         } else {
/* 272 */           Aspect aa = (Aspect)basicAspects.get(random.nextInt(basicAspects.size()));
/* 273 */           al.merge(aa, 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 278 */     if (type == NodeType.HUNGRY) {
/* 279 */       al.merge(Aspect.HUNGER, 2);
/* 280 */       if (random.nextBoolean()) al.merge(Aspect.GREED, 1);
/*     */     }
/* 282 */     else if (type == NodeType.PURE) {
/* 283 */       if (random.nextBoolean()) {
/* 284 */         al.merge(Aspect.LIFE, 2);
/*     */       } else {
/* 286 */         al.merge(Aspect.ORDER, 2);
/*     */       }
/* 288 */     } else if (type == NodeType.DARK) {
/* 289 */       if (random.nextBoolean()) al.merge(Aspect.DEATH, 1);
/* 290 */       if (random.nextBoolean()) al.merge(Aspect.UNDEAD, 1);
/* 291 */       if (random.nextBoolean()) al.merge(Aspect.ENTROPY, 1);
/* 292 */       if (random.nextBoolean()) { al.merge(Aspect.DARKNESS, 1);
/*     */       }
/*     */     }
/*     */     
/* 296 */     int water = 0;
/* 297 */     int lava = 0;
/* 298 */     int stone = 0;
/* 299 */     int foliage = 0;
/*     */     try
/*     */     {
/* 302 */       for (int xx = -5; xx <= 5; xx++) for (int yy = -5; yy <= 5; yy++) for (int zz = -5; zz <= 5; zz++) {
/*     */             try {
/* 304 */               Block bi = world.func_147439_a(x + xx, y + yy, z + zz);
/* 305 */               if (bi.func_149688_o() == Material.field_151586_h) { water++;
/* 306 */               } else if (bi.func_149688_o() == Material.field_151587_i) { lava++;
/* 307 */               } else if (bi == Blocks.field_150348_b) stone++;
/* 308 */               if (bi.isFoliage(world, x + xx, y + yy, z + zz)) foliage++;
/*     */             }
/*     */             catch (Exception e) {}
/*     */           }
/*     */     } catch (Exception e) {}
/* 313 */     if (water > 100) al.merge(Aspect.WATER, 1);
/* 314 */     if (lava > 100) { al.merge(Aspect.FIRE, 1);al.merge(Aspect.EARTH, 1); }
/* 315 */     if (stone > 500) al.merge(Aspect.EARTH, 1);
/* 316 */     if (foliage > 100) { al.merge(Aspect.PLANT, 1);
/*     */     }
/*     */     
/* 319 */     int[] spread = new int[al.size()];
/* 320 */     float total = 0.0F;
/* 321 */     for (int a = 0; a < spread.length; a++) {
/* 322 */       if (al.getAmount(al.getAspectsSorted()[a]) == 2) {
/* 323 */         spread[a] = (50 + random.nextInt(25));
/*     */       } else
/* 325 */         spread[a] = (25 + random.nextInt(50));
/* 326 */       total += spread[a];
/*     */     }
/*     */     
/* 329 */     for (int a = 0; a < spread.length; a++) {
/* 330 */       al.merge(al.getAspectsSorted()[a], (int)(spread[a] / total * value));
/*     */     }
/*     */     
/* 333 */     createNodeAt(world, x, y, z, type, modifier, al);
/*     */   }
/*     */   
/*     */   public static void createNodeAt(World world, int x, int y, int z, NodeType nt, NodeModifier nm, AspectList al) {
/* 337 */     if (world.func_147437_c(x, y, z))
/* 338 */       world.func_147465_d(x, y, z, ConfigBlocks.blockAiry, 0, 0);
/* 339 */     TileEntity te = world.func_147438_o(x, y, z);
/* 340 */     if ((te != null) && ((te instanceof TileNode))) {
/* 341 */       ((TileNode)te).setNodeType(nt);
/* 342 */       ((TileNode)te).setNodeModifier(nm);
/* 343 */       ((TileNode)te).setAspects(al);
/*     */     }
/* 345 */     world.func_147471_g(x, y, z);
/*     */   }
/*     */   
/* 348 */   HashMap<Integer, Boolean> structureNode = new HashMap();
/*     */   
/*     */   private void generateSurface(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
/* 351 */     boolean auraGen = false;
/* 352 */     int blacklist = getDimBlacklist(world.field_73011_w.field_76574_g);
/*     */     
/* 354 */     if ((blacklist == -1) && (Config.genTrees) && (!world.func_72912_H().func_76067_t().func_77127_a().startsWith("flat")) && ((newGen) || (Config.regenTrees))) {
/* 355 */       generateVegetation(world, random, chunkX, chunkZ, newGen);
/*     */     }
/*     */     
/* 358 */     if ((blacklist != 0) && (blacklist != 2)) { generateOres(world, random, chunkX, chunkZ, newGen);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 366 */     if ((blacklist != 0) && (blacklist != 2) && (Config.genAura) && ((newGen) || (Config.regenAura)))
/*     */     {
/* 368 */       ChunkPosition var7 = new MapGenScatteredFeature().func_151545_a(world, chunkX * 16 + 8, world.func_72976_f(chunkX * 16 + 8, chunkZ * 16 + 8), chunkZ * 16 + 8);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 373 */       if ((var7 != null) && (!this.structureNode.containsKey(Integer.valueOf(var7.hashCode())))) {
/* 374 */         auraGen = true;
/* 375 */         this.structureNode.put(Integer.valueOf(var7.hashCode()), Boolean.valueOf(true));
/* 376 */         createRandomNodeAt(world, var7.field_151329_a, world.func_72976_f(var7.field_151329_a, var7.field_151328_c) + 3, var7.field_151328_c, random, false, false, false);
/*     */       }
/*     */       
/*     */ 
/* 380 */       auraGen = generateWildNodes(world, random, chunkX, chunkZ, auraGen, newGen);
/*     */     }
/*     */     
/* 383 */     if ((blacklist == -1) && (Config.genStructure) && (world.field_73011_w.field_76574_g == 0) && (!world.func_72912_H().func_76067_t().func_77127_a().startsWith("flat")) && ((newGen) || (Config.regenStructure)))
/*     */     {
/*     */ 
/* 386 */       int randPosX = chunkX * 16 + random.nextInt(16);
/* 387 */       int randPosZ = chunkZ * 16 + random.nextInt(16);
/* 388 */       int randPosY = world.func_72976_f(randPosX, randPosZ) - 9;
/*     */       
/* 390 */       if (randPosY < world.func_72940_L())
/*     */       {
/* 392 */         Chunk var1 = world.func_72938_d(MathHelper.func_76128_c(randPosX), MathHelper.func_76128_c(randPosZ));
/* 393 */         WorldGenerator mound = new WorldGenMound();
/* 394 */         if (random.nextInt(150) == 0) {
/* 395 */           if (mound.func_76484_a(world, random, randPosX, randPosY, randPosZ)) {
/* 396 */             auraGen = true;
/* 397 */             int value = random.nextInt(200) + 400;
/* 398 */             createRandomNodeAt(world, randPosX + 9, randPosY + 8, randPosZ + 9, random, false, true, false);
/*     */           }
/*     */         }
/* 401 */         else if (random.nextInt(66) == 0) {
/* 402 */           WorldGenEldritchRing stonering = new WorldGenEldritchRing();
/* 403 */           randPosY += 8;
/* 404 */           int w = 11 + random.nextInt(6) * 2;
/* 405 */           int h = 11 + random.nextInt(6) * 2;
/* 406 */           stonering.chunkX = chunkX;
/* 407 */           stonering.chunkZ = chunkZ;
/* 408 */           stonering.width = w;
/* 409 */           stonering.height = h;
/* 410 */           if (stonering.func_76484_a(world, random, randPosX, randPosY, randPosZ))
/*     */           {
/* 412 */             auraGen = true;
/* 413 */             createRandomNodeAt(world, randPosX, randPosY + 2, randPosZ, random, false, true, false);
/*     */             
/* 415 */             Thread t = new Thread(new thaumcraft.common.lib.world.dim.MazeThread(chunkX, chunkZ, w, h, random.nextLong()));
/* 416 */             t.start();
/*     */           }
/*     */         }
/* 419 */         else if (random.nextInt(40) == 0) {
/* 420 */           randPosY += 9;
/* 421 */           WorldGenerator hilltopStones = new WorldGenHilltopStones();
/* 422 */           if (hilltopStones.func_76484_a(world, random, randPosX, randPosY, randPosZ)) {
/* 423 */             auraGen = true;
/* 424 */             createRandomNodeAt(world, randPosX, randPosY + 5, randPosZ, random, false, true, false);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 431 */       generateTotem(world, random, chunkX, chunkZ, auraGen, newGen);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void generateVegetation(World world, Random random, int chunkX, int chunkZ, boolean newGen)
/*     */   {
/* 442 */     BiomeGenBase bgb = world.func_72807_a(chunkX * 16 + 8, chunkZ * 16 + 8);
/* 443 */     if (getBiomeBlacklist(bgb.field_76756_M) != -1) { return;
/*     */     }
/*     */     
/* 446 */     if (random.nextInt(60) == 3) {
/* 447 */       generateSilverwood(world, random, chunkX, chunkZ);
/*     */     }
/*     */     
/*     */ 
/* 451 */     if (random.nextInt(25) == 7) {
/* 452 */       generateGreatwood(world, random, chunkX, chunkZ);
/*     */     }
/*     */     
/* 455 */     int randPosX = chunkX * 16 + random.nextInt(16);
/* 456 */     int randPosZ = chunkZ * 16 + random.nextInt(16);
/* 457 */     int randPosY = world.func_72976_f(randPosX, randPosZ);
/* 458 */     if (randPosY > world.func_72940_L()) return;
/* 459 */     if ((world.func_72807_a(randPosX, randPosZ).field_76752_A == Blocks.field_150354_m) && (world.func_72807_a(randPosX, randPosZ).field_76750_F > 1.0F) && (random.nextInt(30) == 0))
/*     */     {
/*     */ 
/* 462 */       generateFlowers(world, random, randPosX, randPosY, randPosZ, 3);
/*     */     }
/*     */   }
/*     */   
/*     */   private void generateOres(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
/* 467 */     BiomeGenBase bgb = world.func_72807_a(chunkX * 16 + 8, chunkZ * 16 + 8);
/* 468 */     if ((getBiomeBlacklist(bgb.field_76756_M) == 0) || (getBiomeBlacklist(bgb.field_76756_M) == 2)) {
/* 469 */       return;
/*     */     }
/* 471 */     if ((Config.genCinnibar) && ((newGen) || (Config.regenCinnibar)))
/*     */     {
/* 473 */       for (int i = 0; i < 18; i++)
/*     */       {
/* 475 */         int randPosX = chunkX * 16 + random.nextInt(16);
/* 476 */         int randPosY = random.nextInt(world.func_72800_K() / 5);
/* 477 */         int randPosZ = chunkZ * 16 + random.nextInt(16);
/* 478 */         Block block = world.func_147439_a(randPosX, randPosY, randPosZ);
/* 479 */         if ((block != null) && (block.isReplaceableOreGen(world, randPosX, randPosY, randPosZ, Blocks.field_150348_b)))
/*     */         {
/* 481 */           world.func_147465_d(randPosX, randPosY, randPosZ, ConfigBlocks.blockCustomOre, 0, 0);
/*     */         }
/*     */       }
/*     */     }
/* 485 */     if ((Config.genAmber) && ((newGen) || (Config.regenAmber)))
/*     */     {
/* 487 */       for (int i = 0; i < 20; i++)
/*     */       {
/* 489 */         int randPosX = chunkX * 16 + random.nextInt(16);
/* 490 */         int randPosZ = chunkZ * 16 + random.nextInt(16);
/*     */         
/* 492 */         int randPosY = world.func_72976_f(randPosX, randPosZ) - random.nextInt(25);
/*     */         
/* 494 */         Block block = world.func_147439_a(randPosX, randPosY, randPosZ);
/* 495 */         if ((block != null) && (block.isReplaceableOreGen(world, randPosX, randPosY, randPosZ, Blocks.field_150348_b)))
/*     */         {
/* 497 */           world.func_147465_d(randPosX, randPosY, randPosZ, ConfigBlocks.blockCustomOre, 7, 2);
/*     */         }
/*     */       }
/*     */     }
/* 501 */     if ((Config.genInfusedStone) && ((newGen) || (Config.regenInfusedStone)))
/*     */     {
/* 503 */       for (int i = 0; i < 8; i++)
/*     */       {
/* 505 */         int randPosX = chunkX * 16 + random.nextInt(16);
/* 506 */         int randPosZ = chunkZ * 16 + random.nextInt(16);
/* 507 */         int randPosY = random.nextInt(Math.max(5, world.func_72976_f(randPosX, randPosZ) - 5));
/* 508 */         int md = random.nextInt(6) + 1;
/* 509 */         if (random.nextInt(3) == 0) {
/* 510 */           Aspect tag = BiomeHandler.getRandomBiomeTag(world.func_72807_a(randPosX, randPosZ).field_76756_M, random);
/* 511 */           if (tag == null) {
/* 512 */             md = 1 + random.nextInt(6);
/*     */ 
/*     */           }
/* 515 */           else if (tag == Aspect.AIR) { md = 1;
/* 516 */           } else if (tag == Aspect.FIRE) { md = 2;
/* 517 */           } else if (tag == Aspect.WATER) { md = 3;
/* 518 */           } else if (tag == Aspect.EARTH) { md = 4;
/* 519 */           } else if (tag == Aspect.ORDER) { md = 5;
/* 520 */           } else if (tag == Aspect.ENTROPY) { md = 6;
/*     */           }
/*     */         }
/*     */         try
/*     */         {
/* 525 */           new WorldGenMinable(ConfigBlocks.blockCustomOre, md, 6, Blocks.field_150348_b).func_76484_a(world, random, randPosX, randPosY, randPosZ);
/*     */         } catch (Exception e) {
/* 527 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void generateNether(World world, Random random, int chunkX, int chunkZ, boolean newGen)
/*     */   {
/* 571 */     boolean auraGen = false;
/*     */     
/* 573 */     if ((!world.func_72912_H().func_76067_t().func_77127_a().startsWith("flat")) && ((newGen) || (Config.regenStructure)))
/*     */     {
/* 575 */       generateTotem(world, random, chunkX, chunkZ, auraGen, newGen);
/*     */     }
/*     */     
/* 578 */     if ((newGen) || (Config.regenAura))
/*     */     {
/* 580 */       generateWildNodes(world, random, chunkX, chunkZ, auraGen, newGen);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean generateFlowers(World world, Random random, int x, int y, int z, int flower)
/*     */   {
/* 588 */     WorldGenerator flowers = new WorldGenCustomFlowers(ConfigBlocks.blockCustomPlant, flower);
/* 589 */     return flowers.func_76484_a(world, random, x, y, z);
/*     */   }
/*     */   
/*     */   public static boolean generateGreatwood(World world, Random random, int chunkX, int chunkZ)
/*     */   {
/* 594 */     int x = chunkX * 16 + random.nextInt(16);
/* 595 */     int z = chunkZ * 16 + random.nextInt(16);
/* 596 */     int y = world.func_72976_f(x, z);
/* 597 */     int bio = world.func_72807_a(x, z).field_76756_M;
/* 598 */     if (BiomeHandler.getBiomeSupportsGreatwood(bio) > random.nextFloat()) {
/* 599 */       boolean t = new WorldGenGreatwoodTrees(false).generate(world, random, x, y, z, random.nextInt(8) == 0);
/* 600 */       return t;
/*     */     }
/* 602 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean generateSilverwood(World world, Random random, int chunkX, int chunkZ)
/*     */   {
/* 607 */     int x = chunkX * 16 + random.nextInt(16);
/* 608 */     int z = chunkZ * 16 + random.nextInt(16);
/* 609 */     int y = world.func_72976_f(x, z);
/* 610 */     BiomeGenBase bio = world.func_72807_a(x, z);
/* 611 */     if ((!bio.equals(biomeMagicalForest)) && (!bio.equals(biomeTaint)) && ((BiomeDictionary.isBiomeOfType(bio, net.minecraftforge.common.BiomeDictionary.Type.MAGICAL)) || (bio.field_76756_M == BiomeGenBase.field_76785_t.field_76756_M) || (bio.field_76756_M == BiomeGenBase.field_150582_Q.field_76756_M)))
/*     */     {
/*     */ 
/*     */ 
/* 615 */       boolean t = new WorldGenSilverwoodTrees(false, 7, 4).func_76484_a(world, random, x, y, z);
/* 616 */       return t;
/*     */     }
/* 618 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/ThaumcraftWorldGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */