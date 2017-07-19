/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.common.blocks.BlockAiry;
/*     */ import thaumcraft.common.blocks.BlockAiryItem;
/*     */ import thaumcraft.common.blocks.BlockAlchemyFurnace;
/*     */ import thaumcraft.common.blocks.BlockArcaneDoor;
/*     */ import thaumcraft.common.blocks.BlockArcaneFurnace;
/*     */ import thaumcraft.common.blocks.BlockCandle;
/*     */ import thaumcraft.common.blocks.BlockCandleItem;
/*     */ import thaumcraft.common.blocks.BlockChestHungry;
/*     */ import thaumcraft.common.blocks.BlockCosmeticOpaque;
/*     */ import thaumcraft.common.blocks.BlockCosmeticOpaqueItem;
/*     */ import thaumcraft.common.blocks.BlockCosmeticSolid;
/*     */ import thaumcraft.common.blocks.BlockCosmeticSolidItem;
/*     */ import thaumcraft.common.blocks.BlockCosmeticStairs;
/*     */ import thaumcraft.common.blocks.BlockCosmeticStoneSlab;
/*     */ import thaumcraft.common.blocks.BlockCosmeticStoneSlabItem;
/*     */ import thaumcraft.common.blocks.BlockCosmeticWoodSlab;
/*     */ import thaumcraft.common.blocks.BlockCosmeticWoodSlabItem;
/*     */ import thaumcraft.common.blocks.BlockCrystal;
/*     */ import thaumcraft.common.blocks.BlockCrystalItem;
/*     */ import thaumcraft.common.blocks.BlockCustomOre;
/*     */ import thaumcraft.common.blocks.BlockCustomOreItem;
/*     */ import thaumcraft.common.blocks.BlockCustomPlant;
/*     */ import thaumcraft.common.blocks.BlockCustomPlantItem;
/*     */ import thaumcraft.common.blocks.BlockEldritch;
/*     */ import thaumcraft.common.blocks.BlockEldritchItem;
/*     */ import thaumcraft.common.blocks.BlockEldritchNothing;
/*     */ import thaumcraft.common.blocks.BlockEldritchPortal;
/*     */ import thaumcraft.common.blocks.BlockEssentiaReservoir;
/*     */ import thaumcraft.common.blocks.BlockEssentiaReservoirItem;
/*     */ import thaumcraft.common.blocks.BlockFluidDeath;
/*     */ import thaumcraft.common.blocks.BlockFluidPure;
/*     */ import thaumcraft.common.blocks.BlockFluxGas;
/*     */ import thaumcraft.common.blocks.BlockFluxGasItem;
/*     */ import thaumcraft.common.blocks.BlockFluxGoo;
/*     */ import thaumcraft.common.blocks.BlockFluxGooItem;
/*     */ import thaumcraft.common.blocks.BlockHole;
/*     */ import thaumcraft.common.blocks.BlockJar;
/*     */ import thaumcraft.common.blocks.BlockJarItem;
/*     */ import thaumcraft.common.blocks.BlockLifter;
/*     */ import thaumcraft.common.blocks.BlockLoot;
/*     */ import thaumcraft.common.blocks.BlockLootItem;
/*     */ import thaumcraft.common.blocks.BlockMagicBox;
/*     */ import thaumcraft.common.blocks.BlockMagicalLeaves;
/*     */ import thaumcraft.common.blocks.BlockMagicalLeavesItem;
/*     */ import thaumcraft.common.blocks.BlockMagicalLog;
/*     */ import thaumcraft.common.blocks.BlockMagicalLogItem;
/*     */ import thaumcraft.common.blocks.BlockManaPod;
/*     */ import thaumcraft.common.blocks.BlockMetalDevice;
/*     */ import thaumcraft.common.blocks.BlockMetalDeviceItem;
/*     */ import thaumcraft.common.blocks.BlockMirror;
/*     */ import thaumcraft.common.blocks.BlockMirrorItem;
/*     */ import thaumcraft.common.blocks.BlockStoneDevice;
/*     */ import thaumcraft.common.blocks.BlockStoneDeviceItem;
/*     */ import thaumcraft.common.blocks.BlockTable;
/*     */ import thaumcraft.common.blocks.BlockTableItem;
/*     */ import thaumcraft.common.blocks.BlockTaint;
/*     */ import thaumcraft.common.blocks.BlockTaintFibres;
/*     */ import thaumcraft.common.blocks.BlockTaintFibresItem;
/*     */ import thaumcraft.common.blocks.BlockTaintItem;
/*     */ import thaumcraft.common.blocks.BlockTube;
/*     */ import thaumcraft.common.blocks.BlockWarded;
/*     */ import thaumcraft.common.blocks.BlockWoodenDevice;
/*     */ import thaumcraft.common.blocks.BlockWoodenDeviceItem;
/*     */ import thaumcraft.common.blocks.CustomStepSound;
/*     */ import thaumcraft.common.tiles.TileAlchemyFurnace;
/*     */ import thaumcraft.common.tiles.TileAlchemyFurnaceAdvanced;
/*     */ import thaumcraft.common.tiles.TileAlchemyFurnaceAdvancedNozzle;
/*     */ import thaumcraft.common.tiles.TileAlembic;
/*     */ import thaumcraft.common.tiles.TileArcaneBore;
/*     */ import thaumcraft.common.tiles.TileArcaneBoreBase;
/*     */ import thaumcraft.common.tiles.TileArcaneFurnace;
/*     */ import thaumcraft.common.tiles.TileArcaneFurnaceNozzle;
/*     */ import thaumcraft.common.tiles.TileArcaneLamp;
/*     */ import thaumcraft.common.tiles.TileArcaneLampFertility;
/*     */ import thaumcraft.common.tiles.TileArcaneLampGrowth;
/*     */ import thaumcraft.common.tiles.TileArcanePressurePlate;
/*     */ import thaumcraft.common.tiles.TileArcaneWorkbench;
/*     */ import thaumcraft.common.tiles.TileBanner;
/*     */ import thaumcraft.common.tiles.TileBellows;
/*     */ import thaumcraft.common.tiles.TileBrainbox;
/*     */ import thaumcraft.common.tiles.TileCentrifuge;
/*     */ import thaumcraft.common.tiles.TileChestHungry;
/*     */ import thaumcraft.common.tiles.TileCrucible;
/*     */ import thaumcraft.common.tiles.TileCrystal;
/*     */ import thaumcraft.common.tiles.TileDeconstructionTable;
/*     */ import thaumcraft.common.tiles.TileEldritchAltar;
/*     */ import thaumcraft.common.tiles.TileEldritchCap;
/*     */ import thaumcraft.common.tiles.TileEldritchCrabSpawner;
/*     */ import thaumcraft.common.tiles.TileEldritchCrystal;
/*     */ import thaumcraft.common.tiles.TileEldritchLock;
/*     */ import thaumcraft.common.tiles.TileEldritchNothing;
/*     */ import thaumcraft.common.tiles.TileEldritchObelisk;
/*     */ import thaumcraft.common.tiles.TileEldritchPortal;
/*     */ import thaumcraft.common.tiles.TileEldritchTrap;
/*     */ import thaumcraft.common.tiles.TileEssentiaCrystalizer;
/*     */ import thaumcraft.common.tiles.TileEssentiaReservoir;
/*     */ import thaumcraft.common.tiles.TileEtherealBloom;
/*     */ import thaumcraft.common.tiles.TileFluxScrubber;
/*     */ import thaumcraft.common.tiles.TileFocalManipulator;
/*     */ import thaumcraft.common.tiles.TileGrate;
/*     */ import thaumcraft.common.tiles.TileHole;
/*     */ import thaumcraft.common.tiles.TileInfusionMatrix;
/*     */ import thaumcraft.common.tiles.TileInfusionPillar;
/*     */ import thaumcraft.common.tiles.TileJarBrain;
/*     */ import thaumcraft.common.tiles.TileJarFillable;
/*     */ import thaumcraft.common.tiles.TileJarFillableVoid;
/*     */ import thaumcraft.common.tiles.TileJarNode;
/*     */ import thaumcraft.common.tiles.TileLifter;
/*     */ import thaumcraft.common.tiles.TileMagicBox;
/*     */ import thaumcraft.common.tiles.TileMagicWorkbenchCharger;
/*     */ import thaumcraft.common.tiles.TileMirror;
/*     */ import thaumcraft.common.tiles.TileMirrorEssentia;
/*     */ import thaumcraft.common.tiles.TileNitor;
/*     */ import thaumcraft.common.tiles.TileNode;
/*     */ import thaumcraft.common.tiles.TileNodeConverter;
/*     */ import thaumcraft.common.tiles.TileNodeEnergized;
/*     */ import thaumcraft.common.tiles.TileNodeStabilizer;
/*     */ import thaumcraft.common.tiles.TileOwned;
/*     */ import thaumcraft.common.tiles.TilePedestal;
/*     */ import thaumcraft.common.tiles.TileResearchTable;
/*     */ import thaumcraft.common.tiles.TileSensor;
/*     */ import thaumcraft.common.tiles.TileSpa;
/*     */ import thaumcraft.common.tiles.TileTable;
/*     */ import thaumcraft.common.tiles.TileThaumatorium;
/*     */ import thaumcraft.common.tiles.TileThaumatoriumTop;
/*     */ import thaumcraft.common.tiles.TileTube;
/*     */ import thaumcraft.common.tiles.TileTubeBuffer;
/*     */ import thaumcraft.common.tiles.TileTubeOneway;
/*     */ import thaumcraft.common.tiles.TileTubeRestrict;
/*     */ import thaumcraft.common.tiles.TileTubeValve;
/*     */ import thaumcraft.common.tiles.TileVisRelay;
/*     */ import thaumcraft.common.tiles.TileWandPedestal;
/*     */ import thaumcraft.common.tiles.TileWarded;
/*     */ import thaumcraft.common.tiles.TileWardingStone;
/*     */ import thaumcraft.common.tiles.TileWardingStoneFence;
/*     */ 
/*     */ public class ConfigBlocks
/*     */ {
/*     */   public static void init()
/*     */   {
/* 152 */     initializeBlocks();
/* 153 */     registerBlocks();
/* 154 */     registerTileEntities();
/*     */     
/*     */ 
/* 157 */     blockCustomOre.setHarvestLevel("pickaxe", 2, 0);
/* 158 */     blockCustomOre.setHarvestLevel("pickaxe", 2, 7);
/*     */     
/*     */ 
/* 161 */     ThaumcraftApi.portableHoleBlackList.add(Blocks.field_150324_C);
/* 162 */     ThaumcraftApi.portableHoleBlackList.add(blockEldritch);
/* 163 */     ThaumcraftApi.portableHoleBlackList.add(blockEldritchPortal);
/* 164 */     ThaumcraftApi.portableHoleBlackList.add(Blocks.field_150331_J);
/* 165 */     ThaumcraftApi.portableHoleBlackList.add(Blocks.field_150332_K);
/* 166 */     ThaumcraftApi.portableHoleBlackList.add(Blocks.field_150320_F);
/* 167 */     ThaumcraftApi.portableHoleBlackList.add(Blocks.field_150326_M);
/* 168 */     ThaumcraftApi.portableHoleBlackList.add(Blocks.field_150466_ao);
/* 169 */     ThaumcraftApi.portableHoleBlackList.add(Blocks.field_150454_av);
/* 170 */     ThaumcraftApi.portableHoleBlackList.add(blockFluxGoo);
/* 171 */     ThaumcraftApi.portableHoleBlackList.add(blockFluxGas);
/* 172 */     ThaumcraftApi.portableHoleBlackList.add(blockArcaneFurnace);
/*     */   }
/*     */   
/* 175 */   public static Fluid FLUXGOO = new Fluid("fluxGoo").setGaseous(false).setLuminosity(7).setDensity(8).setViscosity(6000);
/* 176 */   public static Fluid FLUXGAS = new Fluid("fluxGas").setGaseous(true).setLuminosity(7).setDensity(-4).setViscosity(2500);
/* 177 */   public static Fluid FLUIDPURE = new Fluid("fluidPure").setGaseous(false).setLuminosity(10).setViscosity(1000).setRarity(EnumRarity.rare);
/* 178 */   public static Fluid FLUIDDEATH = new Fluid("fluidDeath").setGaseous(false).setLuminosity(8).setViscosity(1500).setRarity(EnumRarity.rare);
/*     */   public static Block blockFluxGoo;
/*     */   public static Block blockFluxGas;
/*     */   
/* 182 */   private static void initializeBlocks() { FluidRegistry.registerFluid(FLUXGOO);
/* 183 */     FluidRegistry.registerFluid(FLUIDPURE);
/* 184 */     FluidRegistry.registerFluid(FLUIDDEATH);
/*     */     
/* 186 */     blockFluxGoo = new BlockFluxGoo().func_149663_c("blockFluxGoo");
/* 187 */     blockFluxGas = new BlockFluxGas().func_149663_c("blockFluxGas");
/* 188 */     blockHole = new BlockHole().func_149663_c("blockHole");
/* 189 */     blockCustomOre = new BlockCustomOre().func_149663_c("blockCustomOre");
/* 190 */     blockCustomPlant = new BlockCustomPlant().func_149663_c("blockCustomPlant");
/* 191 */     blockMagicalLog = new BlockMagicalLog().func_149663_c("blockMagicalLog");
/* 192 */     blockMagicalLeaves = new BlockMagicalLeaves().func_149663_c("blockMagicalLeaves");
/* 193 */     blockArcaneFurnace = new BlockArcaneFurnace().func_149663_c("blockArcaneFurnace");
/* 194 */     blockMetalDevice = new BlockMetalDevice().func_149663_c("blockMetalDevice");
/* 195 */     blockAlchemyFurnace = new BlockAlchemyFurnace().func_149663_c("blockAlchemyFurnace");
/* 196 */     blockTable = new BlockTable().func_149663_c("blockTable");
/* 197 */     blockChestHungry = new BlockChestHungry().func_149663_c("blockChestHungry");
/* 198 */     blockCandle = new BlockCandle().func_149663_c("blockCandle");
/* 199 */     blockJar = new BlockJar().func_149663_c("blockJar");
/* 200 */     blockArcaneDoor = new BlockArcaneDoor().func_149663_c("blockArcaneDoor");
/* 201 */     blockWoodenDevice = new BlockWoodenDevice().func_149663_c("blockWoodenDevice");
/* 202 */     blockLifter = new BlockLifter().func_149663_c("blockLifter");
/* 203 */     blockAiry = new BlockAiry().func_149663_c("blockAiry");
/* 204 */     blockCrystal = new BlockCrystal().func_149663_c("blockCrystal");
/* 205 */     blockCosmeticOpaque = new BlockCosmeticOpaque().func_149663_c("blockCosmeticOpaque");
/* 206 */     blockCosmeticSolid = new BlockCosmeticSolid().func_149663_c("blockCosmeticSolid");
/* 207 */     blockMirror = new BlockMirror().func_149663_c("blockMirror");
/* 208 */     blockTaint = new BlockTaint().func_149663_c("blockTaint");
/* 209 */     blockTaintFibres = new BlockTaintFibres().func_149663_c("blockTaintFibres");
/* 210 */     blockStoneDevice = new BlockStoneDevice().func_149663_c("blockStoneDevice");
/* 211 */     blockManaPod = new BlockManaPod().func_149663_c("blockManaPod");
/* 212 */     blockTube = new BlockTube().func_149663_c("blockTube");
/* 213 */     blockWarded = new BlockWarded().func_149663_c("blockWarded");
/* 214 */     blockMagicBox = new BlockMagicBox().func_149663_c("blockMagicBox");
/* 215 */     blockFluidPure = new BlockFluidPure().func_149663_c("blockFluidPure");
/* 216 */     blockFluidDeath = new BlockFluidDeath().func_149663_c("blockFluidDeath");
/* 217 */     blockEldritch = new BlockEldritch().func_149663_c("blockEldritch");
/* 218 */     blockEldritchPortal = new BlockEldritchPortal().func_149663_c("blockPortalEldritch");
/* 219 */     blockEssentiaReservoir = new BlockEssentiaReservoir().func_149663_c("blockEssentiaReservoir");
/* 220 */     blockEldritchNothing = new BlockEldritchNothing().func_149663_c("blockPortalNothing");
/*     */     
/* 222 */     blockStairsArcaneStone = new BlockCosmeticStairs(blockCosmeticSolid, 7).func_149663_c("blockStairsArcaneStone");
/* 223 */     blockStairsGreatwood = new BlockCosmeticStairs(blockWoodenDevice, 6).func_149663_c("blockStairsGreatwood");
/* 224 */     blockStairsSilverwood = new BlockCosmeticStairs(blockWoodenDevice, 7).func_149663_c("blockStairsSilverwood");
/* 225 */     blockStairsEldritch = new BlockCosmeticStairs(blockCosmeticSolid, 11).func_149663_c("blockStairsEldritch");
/* 226 */     blockSlabWood = new BlockCosmeticWoodSlab(false).func_149711_c(2.0F).func_149752_b(5.0F).func_149672_a(Block.field_149766_f).func_149663_c("blockCosmeticSlabWood");
/* 227 */     blockSlabStone = new BlockCosmeticStoneSlab(false).func_149711_c(2.0F).func_149752_b(10.0F).func_149672_a(Block.field_149780_i).func_149663_c("blockCosmeticSlabStone");
/* 228 */     blockDoubleSlabWood = new BlockCosmeticWoodSlab(true).func_149711_c(2.0F).func_149752_b(5.0F).func_149672_a(Block.field_149766_f).func_149663_c("blockCosmeticDoubleSlabWood");
/* 229 */     blockDoubleSlabStone = new BlockCosmeticStoneSlab(true).func_149711_c(2.0F).func_149752_b(10.0F).func_149672_a(Block.field_149780_i).func_149663_c("blockCosmeticDoubleSlabStone");
/*     */     
/* 231 */     blockLootUrn = new BlockLoot(Material.field_151571_B, "urn", 1).func_149672_a(new CustomStepSound("urnbreak", 1.0F, 0.7F)).func_149663_c("blockLootUrn");
/* 232 */     blockLootCrate = new BlockLoot(Material.field_151575_d, "crate", 2).func_149672_a(Block.field_149766_f).func_149663_c("blockLootCrate");
/*     */   }
/*     */   
/*     */   private static void registerBlocks()
/*     */   {
/* 237 */     GameRegistry.registerBlock(blockFluxGoo, BlockFluxGooItem.class, "blockFluxGoo");
/* 238 */     GameRegistry.registerBlock(blockFluxGas, BlockFluxGasItem.class, "blockFluxGas");
/* 239 */     GameRegistry.registerBlock(blockFluidPure, "blockFluidPure");
/* 240 */     GameRegistry.registerBlock(blockFluidDeath, "blockFluidDeath");
/*     */     
/* 242 */     GameRegistry.registerBlock(blockCustomOre, BlockCustomOreItem.class, "blockCustomOre");
/* 243 */     GameRegistry.registerBlock(blockMagicalLog, BlockMagicalLogItem.class, "blockMagicalLog");
/* 244 */     GameRegistry.registerBlock(blockMagicalLeaves, BlockMagicalLeavesItem.class, "blockMagicalLeaves");
/* 245 */     GameRegistry.registerBlock(blockCustomPlant, BlockCustomPlantItem.class, "blockCustomPlant");
/* 246 */     GameRegistry.registerBlock(blockTaint, BlockTaintItem.class, "blockTaint");
/* 247 */     GameRegistry.registerBlock(blockTaintFibres, BlockTaintFibresItem.class, "blockTaintFibres");
/*     */     
/* 249 */     GameRegistry.registerBlock(blockCosmeticOpaque, BlockCosmeticOpaqueItem.class, "blockCosmeticOpaque");
/* 250 */     GameRegistry.registerBlock(blockCosmeticSolid, BlockCosmeticSolidItem.class, "blockCosmeticSolid");
/* 251 */     GameRegistry.registerBlock(blockCrystal, BlockCrystalItem.class, "blockCrystal");
/*     */     
/* 253 */     GameRegistry.registerBlock(blockTube, thaumcraft.common.blocks.BlockTubeItem.class, "blockTube");
/* 254 */     GameRegistry.registerBlock(blockMetalDevice, BlockMetalDeviceItem.class, "blockMetalDevice");
/* 255 */     GameRegistry.registerBlock(blockWoodenDevice, BlockWoodenDeviceItem.class, "blockWoodenDevice");
/* 256 */     GameRegistry.registerBlock(blockStoneDevice, BlockStoneDeviceItem.class, "blockStoneDevice");
/* 257 */     GameRegistry.registerBlock(blockMirror, BlockMirrorItem.class, "blockMirror");
/* 258 */     GameRegistry.registerBlock(blockTable, BlockTableItem.class, "blockTable");
/* 259 */     GameRegistry.registerBlock(blockChestHungry, "blockChestHungry");
/* 260 */     GameRegistry.registerBlock(blockArcaneDoor, "blockArcaneDoor");
/* 261 */     GameRegistry.registerBlock(blockLifter, "blockLifter");
/* 262 */     GameRegistry.registerBlock(blockMagicBox, "blockMagicBox");
/* 263 */     GameRegistry.registerBlock(blockAlchemyFurnace, "blockAlchemyFurnace");
/*     */     
/* 265 */     GameRegistry.registerBlock(blockJar, BlockJarItem.class, "blockJar");
/* 266 */     GameRegistry.registerBlock(blockCandle, BlockCandleItem.class, "blockCandle");
/* 267 */     GameRegistry.registerBlock(blockEldritch, BlockEldritchItem.class, "blockEldritch");
/*     */     
/* 269 */     GameRegistry.registerBlock(blockAiry, BlockAiryItem.class, "blockAiry");
/* 270 */     GameRegistry.registerBlock(blockManaPod, "blockManaPod");
/* 271 */     GameRegistry.registerBlock(blockArcaneFurnace, "blockArcaneFurnace");
/* 272 */     GameRegistry.registerBlock(blockWarded, "blockWarded");
/* 273 */     GameRegistry.registerBlock(blockHole, "blockHole");
/* 274 */     GameRegistry.registerBlock(blockEldritchPortal, "blockPortalEldritch");
/* 275 */     GameRegistry.registerBlock(blockEssentiaReservoir, BlockEssentiaReservoirItem.class, "blockEssentiaReservoir");
/* 276 */     GameRegistry.registerBlock(blockEldritchNothing, "blockEldritchNothing");
/*     */     
/* 278 */     GameRegistry.registerBlock(blockStairsArcaneStone, "blockStairsArcaneStone");
/* 279 */     GameRegistry.registerBlock(blockStairsGreatwood, "blockStairsGreatwood");
/* 280 */     GameRegistry.registerBlock(blockStairsSilverwood, "blockStairsSilverwood");
/* 281 */     GameRegistry.registerBlock(blockStairsEldritch, "blockStairsEldritch");
/*     */     
/* 283 */     GameRegistry.registerBlock(blockSlabWood, BlockCosmeticWoodSlabItem.class, "blockCosmeticSlabWood");
/* 284 */     GameRegistry.registerBlock(blockSlabStone, BlockCosmeticStoneSlabItem.class, "blockCosmeticSlabStone");
/* 285 */     GameRegistry.registerBlock(blockDoubleSlabWood, "blockCosmeticDoubleSlabWood");
/* 286 */     GameRegistry.registerBlock(blockDoubleSlabStone, "blockCosmeticDoubleSlabStone");
/*     */     
/* 288 */     GameRegistry.registerBlock(blockLootUrn, BlockLootItem.class, "blockLootUrn");
/* 289 */     GameRegistry.registerBlock(blockLootCrate, BlockLootItem.class, "blockLootCrate");
/*     */   }
/*     */   
/*     */   private static void registerTileEntities() {
/* 293 */     GameRegistry.registerTileEntity(TileHole.class, "TileHole");
/* 294 */     GameRegistry.registerTileEntity(TileArcaneFurnace.class, "TileArcaneFurnace");
/* 295 */     GameRegistry.registerTileEntity(TileArcaneFurnaceNozzle.class, "TileArcaneFurnaceNozzle");
/* 296 */     GameRegistry.registerTileEntity(TileCrucible.class, "TileCrucible");
/* 297 */     GameRegistry.registerTileEntity(TileAlembic.class, "TileSiphon");
/* 298 */     GameRegistry.registerTileEntity(TileJarFillable.class, "TileJar");
/* 299 */     GameRegistry.registerTileEntity(TileJarFillableVoid.class, "TileJarVoid");
/* 300 */     GameRegistry.registerTileEntity(TileJarNode.class, "TileJarNode");
/* 301 */     GameRegistry.registerTileEntity(TileJarBrain.class, "TileJarBrain");
/* 302 */     GameRegistry.registerTileEntity(TileResearchTable.class, "TileResearchTable");
/* 303 */     GameRegistry.registerTileEntity(TileTable.class, "TileTable");
/* 304 */     GameRegistry.registerTileEntity(TileArcaneWorkbench.class, "TileArcaneWorkbench");
/* 305 */     GameRegistry.registerTileEntity(TileChestHungry.class, "TileChestHungry");
/* 306 */     GameRegistry.registerTileEntity(TileBellows.class, "TileBellows");
/* 307 */     GameRegistry.registerTileEntity(TileSensor.class, "TileSensor");
/* 308 */     GameRegistry.registerTileEntity(TileArcanePressurePlate.class, "TileArcanePressurePlate");
/* 309 */     GameRegistry.registerTileEntity(TileArcaneBore.class, "TileArcaneBore");
/* 310 */     GameRegistry.registerTileEntity(TileArcaneBoreBase.class, "TileArcaneBoreBase");
/* 311 */     GameRegistry.registerTileEntity(TileArcaneLamp.class, "TileArcaneLamp");
/* 312 */     GameRegistry.registerTileEntity(TileArcaneLampGrowth.class, "TileArcaneLampGrowth");
/* 313 */     GameRegistry.registerTileEntity(TileArcaneLampFertility.class, "TileArcaneLampFertility");
/* 314 */     GameRegistry.registerTileEntity(TileOwned.class, "TileOwned");
/* 315 */     GameRegistry.registerTileEntity(TileLifter.class, "TileLifter");
/* 316 */     GameRegistry.registerTileEntity(TileNitor.class, "TileNitor");
/* 317 */     GameRegistry.registerTileEntity(TileNode.class, "TileNode");
/* 318 */     GameRegistry.registerTileEntity(TileCrystal.class, "TileCrystal");
/* 319 */     GameRegistry.registerTileEntity(TileEldritchCrystal.class, "TileEldritchCrystal");
/* 320 */     GameRegistry.registerTileEntity(TileMirror.class, "TileMirror");
/* 321 */     GameRegistry.registerTileEntity(TileMirrorEssentia.class, "TileMirrorEssentia");
/* 322 */     GameRegistry.registerTileEntity(TilePedestal.class, "TilePedestal");
/* 323 */     GameRegistry.registerTileEntity(TileWandPedestal.class, "TileWandPedestal");
/* 324 */     GameRegistry.registerTileEntity(TileInfusionMatrix.class, "TileInfusionStone");
/* 325 */     GameRegistry.registerTileEntity(TileInfusionPillar.class, "TileInfusionPillar");
/* 326 */     GameRegistry.registerTileEntity(TileAlchemyFurnace.class, "TileAlchemyFurnace");
/* 327 */     GameRegistry.registerTileEntity(TileAlchemyFurnaceAdvanced.class, "TileAlchemyFurnaceAdvanced");
/* 328 */     GameRegistry.registerTileEntity(TileAlchemyFurnaceAdvancedNozzle.class, "TileAlchemyFurnaceAdvancedNozzle");
/* 329 */     GameRegistry.registerTileEntity(TileDeconstructionTable.class, "TileDeconstructionTable");
/* 330 */     GameRegistry.registerTileEntity(TileEtherealBloom.class, "TilePurifyTotem");
/* 331 */     GameRegistry.registerTileEntity(TileTube.class, "TileTube");
/* 332 */     GameRegistry.registerTileEntity(TileTubeValve.class, "TileTubeValve");
/* 333 */     GameRegistry.registerTileEntity(TileCentrifuge.class, "TileCentrifuge");
/* 334 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.TileTubeFilter.class, "TileTubeFilter");
/* 335 */     GameRegistry.registerTileEntity(TileTubeBuffer.class, "TileTubeBuffer");
/* 336 */     GameRegistry.registerTileEntity(TileTubeRestrict.class, "TileTubeRestrict");
/* 337 */     GameRegistry.registerTileEntity(TileTubeOneway.class, "TileTubeOneway");
/* 338 */     GameRegistry.registerTileEntity(TileThaumatorium.class, "TileThaumatorium");
/* 339 */     GameRegistry.registerTileEntity(TileThaumatoriumTop.class, "TileThaumatoriumTop");
/* 340 */     GameRegistry.registerTileEntity(TileBrainbox.class, "TileBrainbox");
/* 341 */     GameRegistry.registerTileEntity(TileWarded.class, "TileWarded");
/* 342 */     GameRegistry.registerTileEntity(TileGrate.class, "TileGrate");
/* 343 */     GameRegistry.registerTileEntity(TileWardingStone.class, "TileWardingStone");
/* 344 */     GameRegistry.registerTileEntity(TileWardingStoneFence.class, "TileWardingStoneFence");
/* 345 */     GameRegistry.registerTileEntity(TileMagicBox.class, "TileMagicBox");
/* 346 */     GameRegistry.registerTileEntity(TileNodeStabilizer.class, "TileNodeStabilizer");
/* 347 */     GameRegistry.registerTileEntity(TileVisRelay.class, "TileVisRelay");
/* 348 */     GameRegistry.registerTileEntity(TileNodeEnergized.class, "TileNodeEnergized");
/* 349 */     GameRegistry.registerTileEntity(TileNodeConverter.class, "TileNodeConverter");
/* 350 */     GameRegistry.registerTileEntity(TileMagicWorkbenchCharger.class, "TileMagicWorkbenchCharger");
/* 351 */     GameRegistry.registerTileEntity(thaumcraft.common.tiles.TileManaPod.class, "TileManaPod");
/* 352 */     GameRegistry.registerTileEntity(TileSpa.class, "TileSpa");
/* 353 */     GameRegistry.registerTileEntity(TileEldritchAltar.class, "TileEldritchAltar");
/* 354 */     GameRegistry.registerTileEntity(TileEldritchObelisk.class, "TileEldritchObelisk");
/* 355 */     GameRegistry.registerTileEntity(TileEldritchCap.class, "TileEldritchCap");
/* 356 */     GameRegistry.registerTileEntity(TileBanner.class, "TileBanner");
/* 357 */     GameRegistry.registerTileEntity(TileEldritchPortal.class, "TileEldritchPortal");
/* 358 */     GameRegistry.registerTileEntity(TileEssentiaReservoir.class, "TileEssentiaReservoir");
/* 359 */     GameRegistry.registerTileEntity(TileEssentiaCrystalizer.class, "TileEssentiaCrystalizer");
/* 360 */     GameRegistry.registerTileEntity(TileEldritchNothing.class, "TileEldritchNothing");
/* 361 */     GameRegistry.registerTileEntity(TileEldritchLock.class, "TileEldritchLock");
/* 362 */     GameRegistry.registerTileEntity(TileEldritchCrabSpawner.class, "TileEldritchCrabSpawner");
/* 363 */     GameRegistry.registerTileEntity(TileFocalManipulator.class, "TileFocalManipulator");
/* 364 */     GameRegistry.registerTileEntity(TileFluxScrubber.class, "TileFluxScrubber");
/* 365 */     GameRegistry.registerTileEntity(TileEldritchTrap.class, "TileEldritchTrap");
/*     */   }
/*     */   
/*     */ 
/*     */   public static Block blockFluidPure;
/*     */   
/*     */   public static Block blockFluidDeath;
/*     */   
/*     */   public static Block blockHole;
/*     */   
/*     */   public static Block blockArcaneFurnace;
/*     */   
/*     */   public static Block blockMetalDevice;
/*     */   public static Block blockMagicalLog;
/*     */   public static Block blockStoneDevice;
/*     */   public static Block blockWoodenDevice;
/*     */   public static Block blockMagicalLeaves;
/*     */   public static Block blockTable;
/*     */   public static Block blockChestHungry;
/*     */   public static Block blockCustomOre;
/*     */   public static Block blockCustomPlant;
/*     */   public static Block blockCandle;
/*     */   public static Block blockJar;
/*     */   public static Block blockArcaneDoor;
/*     */   public static Block blockWarded;
/*     */   public static Block blockLifter;
/*     */   public static Block blockAiry;
/*     */   public static Block blockCrystal;
/*     */   public static Block blockCosmeticOpaque;
/*     */   public static Block blockCosmeticSolid;
/*     */   public static Block blockMirror;
/*     */   public static Block blockTaint;
/*     */   public static Block blockTaintFibres;
/*     */   public static Block blockManaPod;
/*     */   public static Block blockTube;
/*     */   public static Block blockMagicBox;
/*     */   public static Block blockEldritch;
/*     */   public static Block blockEldritchPortal;
/*     */   public static Block blockEssentiaReservoir;
/*     */   public static Block blockStairsArcaneStone;
/*     */   public static Block blockStairsSilverwood;
/*     */   public static Block blockStairsGreatwood;
/*     */   public static Block blockStairsEldritch;
/*     */   public static Block blockSlabWood;
/*     */   public static Block blockSlabStone;
/*     */   public static Block blockDoubleSlabWood;
/*     */   public static Block blockDoubleSlabStone;
/*     */   public static Block blockEldritchNothing;
/*     */   public static Block blockAlchemyFurnace;
/*     */   public static Block blockLootUrn;
/*     */   public static Block blockLootCrate;
/* 416 */   public static int blockCosmeticOpaqueRI = -1;
/* 417 */   public static int blockWoodenDeviceRI = -1;
/* 418 */   public static int blockLifterRI = -1;
/* 419 */   public static int blockCrystalRI = -1;
/* 420 */   public static int blockFluxGasRI = -1;
/* 421 */   public static int blockCustomOreRI = -1;
/* 422 */   public static int blockTaintRI = -1;
/* 423 */   public static int blockTaintFibreRI = -1;
/* 424 */   public static int blockMirrorRI = -1;
/* 425 */   public static int blockCandleRI = -1;
/* 426 */   public static int blockWardedRI = -1;
/* 427 */   public static int blockArcaneFurnaceRI = -1;
/* 428 */   public static int blockMetalDeviceRI = -1;
/* 429 */   public static int blockStoneDeviceRI = -1;
/* 430 */   public static int blockChestHungryRI = -1;
/* 431 */   public static int blockTableRI = -1;
/* 432 */   public static int blockJarRI = -1;
/* 433 */   public static int blockLootUrnRI = -1;
/* 434 */   public static int blockLootCrateRI = -1;
/* 435 */   public static int blockTubeRI = -1;
/* 436 */   public static int blockEssentiaReservoirRI = -1;
/* 437 */   public static int blockEldritchRI = -1;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/config/ConfigBlocks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */