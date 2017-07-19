/*     */ package thaumcraft.common.lib.world.biomes;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenBlockBlob;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.entities.monster.EntityTaintacle;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ import thaumcraft.common.lib.world.WorldGenBigMagicTree;
/*     */ 
/*     */ public class BiomeGenTaint
/*     */   extends BiomeGenBase
/*     */ {
/*  25 */   public static WorldGenBlockBlob blobs = null;
/*     */   protected WorldGenBigMagicTree bigTree;
/*     */   
/*     */   public BiomeGenTaint(int par1)
/*     */   {
/*  30 */     super(par1);
/*  31 */     this.bigTree = new WorldGenBigMagicTree(false);
/*  32 */     this.field_76760_I.field_76832_z = 2;
/*  33 */     this.field_76760_I.field_76802_A = 64537;
/*  34 */     this.field_76760_I.field_76803_B = 2;
/*  35 */     this.field_76760_I.field_76799_E = 64537;
/*  36 */     func_76735_a("Tainted Land");
/*  37 */     func_76739_b(7160201);
/*  38 */     this.field_76762_K.clear();
/*  39 */     this.field_76761_J.clear();
/*  40 */     this.field_76755_L.clear();
/*  41 */     if (Config.spawnTaintacle) {
/*  42 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityTaintacle.class, 1, 1, 1));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76728_a(World world, Random random, int x, int z)
/*     */   {
/*  49 */     super.func_76728_a(world, random, x, z);
/*     */     
/*  51 */     decorateSpecial(world, random, x, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public WorldGenAbstractTree func_150567_a(Random par1Random)
/*     */   {
/*  60 */     return par1Random.nextInt(8) == 0 ? this.bigTree : super.func_150567_a(par1Random);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void decorateSpecial(World world, Random random, int x, int z)
/*     */   {
/*  71 */     int k = random.nextInt(3);
/*     */     
/*  73 */     for (int l = 0; l < k; l++)
/*     */     {
/*  75 */       int i1 = x + random.nextInt(16) + 8;
/*  76 */       int j1 = z + random.nextInt(16) + 8;
/*  77 */       int k1 = world.func_72976_f(i1, j1);
/*  78 */       blobs.func_76484_a(world, random, i1, k1, j1);
/*     */     }
/*     */     
/*  81 */     for (int a = 0; a < 10; a++) {
/*  82 */       int xx = x + random.nextInt(16);
/*  83 */       int zz = z + random.nextInt(16);
/*  84 */       int yy = world.func_72976_f(xx, zz) - 1;
/*  85 */       Block l1 = world.func_147439_a(xx, yy, zz);
/*  86 */       if (l1 != Blocks.field_150350_a)
/*     */       {
/*  88 */         if (l1 == Blocks.field_150349_c)
/*     */         {
/*  90 */           world.func_147465_d(xx, yy + 1, zz, ConfigBlocks.blockTaintFibres, 0, 2);
/*     */         }
/*  92 */         else if ((l1.isReplaceable(world, xx, yy, zz)) && (world.func_147439_a(xx, yy - 1, zz) == Blocks.field_150349_c))
/*     */         {
/*     */ 
/*  95 */           world.func_147465_d(xx, yy, zz, ConfigBlocks.blockTaintFibres, 0, 2);
/*     */         }
/*     */       }
/*     */     }
/*  99 */     for (int a = 0; a < 8; a++) {
/* 100 */       int xx = x + random.nextInt(16);
/* 101 */       int zz = z + random.nextInt(16);
/* 102 */       int yy = Utils.getFirstUncoveredBlockHeight(world, xx, zz) + 1;
/*     */       
/* 104 */       if (world.func_72807_a(xx, zz) != this) { Utils.setBiomeAt(world, xx, zz, ThaumcraftWorldGenerator.biomeTaint);
/*     */       }
/* 106 */       if ((world.func_147437_c(xx, yy, zz)) && (BlockUtils.isAdjacentToSolidBlock(world, xx, yy, zz)))
/*     */       {
/* 108 */         world.func_147465_d(xx, yy, zz, ConfigBlocks.blockTaintFibres, 0, 2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_150558_b(int x, int y, int z)
/*     */   {
/* 120 */     return 7160201;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_150571_c(int x, int y, int z)
/*     */   {
/* 127 */     return 8154503;
/*     */   }
/*     */   
/*     */   public int func_76731_a(float par1)
/*     */   {
/* 132 */     return 8144127;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getWaterColorMultiplier()
/*     */   {
/* 138 */     return 13373832;
/*     */   }
/*     */   
/*     */   public BiomeGenBase func_150566_k()
/*     */   {
/* 143 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/biomes/BiomeGenTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */