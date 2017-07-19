/*     */ package thaumcraft.common.lib.world.biomes;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.entity.monster.EntityEnderman;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.BiomeGenBase.Height;
/*     */ import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenBigMushroom;
/*     */ import net.minecraft.world.gen.feature.WorldGenBlockBlob;
/*     */ import net.minecraft.world.gen.feature.WorldGenTallGrass;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.WorldGenBigMagicTree;
/*     */ import thaumcraft.common.lib.world.WorldGenGreatwoodTrees;
/*     */ import thaumcraft.common.lib.world.WorldGenManaPods;
/*     */ import thaumcraft.common.lib.world.WorldGenSilverwoodTrees;
/*     */ 
/*     */ public class BiomeGenMagicalForest extends BiomeGenBase
/*     */ {
/*     */   protected WorldGenBigMagicTree bigTree;
/*  35 */   private static final WorldGenBlockBlob blobs = new WorldGenBlockBlob(Blocks.field_150341_Y, 0);
/*     */   
/*     */   public BiomeGenMagicalForest(int par1)
/*     */   {
/*  39 */     super(par1);
/*  40 */     this.bigTree = new WorldGenBigMagicTree(false);
/*     */     
/*  42 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 2, 1, 3));
/*  43 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 2, 1, 3));
/*     */     
/*  45 */     this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 3, 1, 1));
/*  46 */     this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 3, 1, 1));
/*     */     
/*  48 */     if (Config.spawnPech)
/*  49 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityPech.class, 10, 1, 2));
/*  50 */     if (Config.spawnWisp) {
/*  51 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(thaumcraft.common.entities.monster.EntityWisp.class, 10, 1, 2));
/*     */     }
/*  53 */     this.field_76760_I.field_76832_z = 2;
/*  54 */     this.field_76760_I.field_76802_A = 10;
/*  55 */     this.field_76760_I.field_76803_B = 12;
/*  56 */     this.field_76760_I.field_76833_y = 6;
/*  57 */     this.field_76760_I.field_76798_D = 6;
/*  58 */     func_76732_a(0.7F, 0.6F);
/*  59 */     func_150570_a(new BiomeGenBase.Height(0.2F, 0.2F));
/*  60 */     func_76735_a("Magical Forest");
/*  61 */     func_76739_b(Config.blueBiome ? 6728396 : 6747307);
/*     */     
/*  63 */     this.flowers.clear();
/*  64 */     for (int x = 0; x < BlockFlower.field_149859_a.length; x++)
/*     */     {
/*  66 */       addFlower(Blocks.field_150328_O, x, 10);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WorldGenAbstractTree func_150567_a(Random par1Random)
/*     */   {
/*  76 */     return par1Random.nextInt(10) == 0 ? new WorldGenGreatwoodTrees(false) : par1Random.nextInt(14) == 0 ? new WorldGenSilverwoodTrees(false, 8, 5) : this.bigTree;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WorldGenerator func_76730_b(Random par1Random)
/*     */   {
/*  85 */     return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.field_150329_H, 2) : new WorldGenTallGrass(Blocks.field_150329_H, 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_150558_b(int x, int y, int z)
/*     */   {
/*  93 */     return Config.blueBiome ? 6728396 : 5635969;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_150571_c(int x, int y, int z)
/*     */   {
/* 100 */     return Config.blueBiome ? 7851246 : 6750149;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getWaterColorMultiplier()
/*     */   {
/* 111 */     return 30702;
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
/*     */   public void func_76728_a(World world, Random random, int x, int z)
/*     */   {
/* 125 */     int k = random.nextInt(3);
/*     */     
/* 127 */     for (int l = 0; l < k; l++)
/*     */     {
/* 129 */       int i1 = x + random.nextInt(16) + 8;
/* 130 */       int j1 = z + random.nextInt(16) + 8;
/* 131 */       int k1 = world.func_72976_f(i1, j1);
/* 132 */       blobs.func_76484_a(world, random, i1, k1, j1);
/*     */     }
/*     */     
/* 135 */     for (k = 0; k < 4; k++)
/*     */     {
/* 137 */       for (l = 0; l < 4; l++)
/*     */       {
/* 139 */         int i1 = x + k * 4 + 1 + 8 + random.nextInt(3);
/* 140 */         int j1 = z + l * 4 + 1 + 8 + random.nextInt(3);
/* 141 */         int k1 = world.func_72976_f(i1, j1);
/*     */         
/* 143 */         if (random.nextInt(40) == 0)
/*     */         {
/* 145 */           WorldGenBigMushroom worldgenbigmushroom = new WorldGenBigMushroom();
/* 146 */           worldgenbigmushroom.func_76484_a(world, random, i1, k1, j1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 152 */     super.func_76728_a(world, random, x, z);
/*     */     
/*     */ 
/* 155 */     WorldGenManaPods worldgenpods = new WorldGenManaPods();
/* 156 */     for (k = 0; k < 10; k++)
/*     */     {
/* 158 */       l = x + random.nextInt(16) + 8;
/* 159 */       byte b0 = 64;
/* 160 */       int i1 = z + random.nextInt(16) + 8;
/* 161 */       worldgenpods.func_76484_a(world, random, l, b0, i1);
/*     */     }
/*     */     
/* 164 */     for (int a = 0; a < 8; a++) {
/* 165 */       int xx = x + random.nextInt(16);
/* 166 */       int zz = z + random.nextInt(16);
/* 167 */       int yy = world.func_72976_f(xx, zz);
/* 168 */       while ((yy > 50) && (world.func_147439_a(xx, yy, zz) != Blocks.field_150349_c)) {
/* 169 */         yy--;
/*     */       }
/* 171 */       Block l1 = world.func_147439_a(xx, yy, zz);
/* 172 */       if ((l1 == Blocks.field_150349_c) && (world.func_147439_a(xx, yy + 1, zz).isReplaceable(world, xx, yy + 1, zz)) && (isBlockAdjacentToWood(world, xx, yy + 1, zz)))
/*     */       {
/*     */ 
/* 175 */         world.func_147465_d(xx, yy + 1, zz, thaumcraft.common.config.ConfigBlocks.blockCustomPlant, 5, 2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isBlockAdjacentToWood(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 182 */     int count = 0;
/* 183 */     for (int xx = -1; xx <= 1; xx++) {
/* 184 */       for (int yy = -1; yy <= 1; yy++) {
/* 185 */         for (int zz = -1; zz <= 1; zz++)
/* 186 */           if (((xx != 0) || (yy != 0) || (zz != 0)) && 
/* 187 */             (Utils.isWoodLog(world, xx + x, yy + y, zz + z))) return true;
/*     */       }
/*     */     }
/* 190 */     return false;
/*     */   }
/*     */   
/*     */   public BiomeGenBase func_150566_k()
/*     */   {
/* 195 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/biomes/BiomeGenMagicalForest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */