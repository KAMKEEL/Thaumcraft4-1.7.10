/*      */ package thaumcraft.client;
/*      */ 
/*      */ import cpw.mods.fml.client.FMLClientHandler;
/*      */ import cpw.mods.fml.client.registry.ClientRegistry;
/*      */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*      */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*      */ import cpw.mods.fml.common.FMLCommonHandler;
/*      */ import cpw.mods.fml.common.Loader;
/*      */ import cpw.mods.fml.common.eventhandler.EventBus;
/*      */ import cpw.mods.fml.common.registry.VillagerRegistry;
/*      */ import java.awt.Color;
/*      */ import java.util.HashMap;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.model.ModelChicken;
/*      */ import net.minecraft.client.model.ModelCow;
/*      */ import net.minecraft.client.model.ModelPig;
/*      */ import net.minecraft.client.model.ModelSlime;
/*      */ import net.minecraft.client.multiplayer.WorldClient;
/*      */ import net.minecraft.client.particle.EffectRenderer;
/*      */ import net.minecraft.client.particle.EntityDiggingFX;
/*      */ import net.minecraft.client.particle.EntityLavaFX;
/*      */ import net.minecraft.client.renderer.RenderGlobal;
/*      */ import net.minecraft.client.renderer.entity.RenderItem;
/*      */ import net.minecraft.client.renderer.entity.RenderSnowball;
/*      */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.IIcon;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.client.MinecraftForgeClient;
/*      */ import net.minecraftforge.common.MinecraftForge;
/*      */ import net.minecraftforge.common.util.ForgeDirection;
/*      */ import thaumcraft.client.fx.ParticleEngine;
/*      */ import thaumcraft.client.fx.beams.FXArc;
/*      */ import thaumcraft.client.fx.beams.FXBeam;
/*      */ import thaumcraft.client.fx.beams.FXBeamBore;
/*      */ import thaumcraft.client.fx.beams.FXBeamPower;
/*      */ import thaumcraft.client.fx.beams.FXBeamWand;
/*      */ import thaumcraft.client.fx.bolt.FXLightningBolt;
/*      */ import thaumcraft.client.fx.other.FXBlockWard;
/*      */ import thaumcraft.client.fx.particles.FXBlockRunes;
/*      */ import thaumcraft.client.fx.particles.FXBoreParticles;
/*      */ import thaumcraft.client.fx.particles.FXBoreSparkle;
/*      */ import thaumcraft.client.fx.particles.FXBreaking;
/*      */ import thaumcraft.client.fx.particles.FXBubble;
/*      */ import thaumcraft.client.fx.particles.FXBurst;
/*      */ import thaumcraft.client.fx.particles.FXDrop;
/*      */ import thaumcraft.client.fx.particles.FXEssentiaTrail;
/*      */ import thaumcraft.client.fx.particles.FXGeneric;
/*      */ import thaumcraft.client.fx.particles.FXSmokeSpiral;
/*      */ import thaumcraft.client.fx.particles.FXSmokeTrail;
/*      */ import thaumcraft.client.fx.particles.FXSpark;
/*      */ import thaumcraft.client.fx.particles.FXSparkle;
/*      */ import thaumcraft.client.fx.particles.FXSparkleTrail;
/*      */ import thaumcraft.client.fx.particles.FXSwarm;
/*      */ import thaumcraft.client.fx.particles.FXVent;
/*      */ import thaumcraft.client.fx.particles.FXWisp;
/*      */ import thaumcraft.client.fx.particles.FXWispArcing;
/*      */ import thaumcraft.client.fx.particles.FXWispEG;
/*      */ import thaumcraft.client.gui.GuiAlchemyFurnace;
/*      */ import thaumcraft.client.gui.GuiArcaneBore;
/*      */ import thaumcraft.client.gui.GuiArcaneWorkbench;
/*      */ import thaumcraft.client.gui.GuiDeconstructionTable;
/*      */ import thaumcraft.client.gui.GuiFocalManipulator;
/*      */ import thaumcraft.client.gui.GuiFocusPouch;
/*      */ import thaumcraft.client.gui.GuiGolem;
/*      */ import thaumcraft.client.gui.GuiHandMirror;
/*      */ import thaumcraft.client.gui.GuiHoverHarness;
/*      */ import thaumcraft.client.gui.GuiMagicBox;
/*      */ import thaumcraft.client.gui.GuiPech;
/*      */ import thaumcraft.client.gui.GuiResearchBrowser;
/*      */ import thaumcraft.client.gui.GuiResearchTable;
/*      */ import thaumcraft.client.gui.GuiSpa;
/*      */ import thaumcraft.client.gui.GuiThaumatorium;
/*      */ import thaumcraft.client.gui.GuiTravelingTrunk;
/*      */ import thaumcraft.client.lib.ClientTickEventsFML;
/*      */ import thaumcraft.client.renderers.block.BlockArcaneFurnaceRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockCandleRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockChestHungryRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockCosmeticOpaqueRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockCrystalRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockCustomOreRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockEldritchRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockEssentiaReservoirRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockGasRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockJarRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockLifterRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockLootCrateRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockLootUrnRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockMetalDeviceRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockStoneDeviceRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockTableRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockTaintFibreRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockTaintRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockTubeRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockWardedRenderer;
/*      */ import thaumcraft.client.renderers.block.BlockWoodenDeviceRenderer;
/*      */ import thaumcraft.client.renderers.entity.RenderAlumentum;
/*      */ import thaumcraft.client.renderers.entity.RenderAspectOrb;
/*      */ import thaumcraft.client.renderers.entity.RenderBrainyZombie;
/*      */ import thaumcraft.client.renderers.entity.RenderCultist;
/*      */ import thaumcraft.client.renderers.entity.RenderCultistPortal;
/*      */ import thaumcraft.client.renderers.entity.RenderDart;
/*      */ import thaumcraft.client.renderers.entity.RenderEldritchCrab;
/*      */ import thaumcraft.client.renderers.entity.RenderEldritchGolem;
/*      */ import thaumcraft.client.renderers.entity.RenderEldritchGuardian;
/*      */ import thaumcraft.client.renderers.entity.RenderEldritchOrb;
/*      */ import thaumcraft.client.renderers.entity.RenderElectricOrb;
/*      */ import thaumcraft.client.renderers.entity.RenderEmber;
/*      */ import thaumcraft.client.renderers.entity.RenderExplosiveOrb;
/*      */ import thaumcraft.client.renderers.entity.RenderFallingTaint;
/*      */ import thaumcraft.client.renderers.entity.RenderFireBat;
/*      */ import thaumcraft.client.renderers.entity.RenderFollowingItem;
/*      */ import thaumcraft.client.renderers.entity.RenderFrostShard;
/*      */ import thaumcraft.client.renderers.entity.RenderGolemBase;
/*      */ import thaumcraft.client.renderers.entity.RenderGolemBobber;
/*      */ import thaumcraft.client.renderers.entity.RenderInhabitedZombie;
/*      */ import thaumcraft.client.renderers.entity.RenderMindSpider;
/*      */ import thaumcraft.client.renderers.entity.RenderPech;
/*      */ import thaumcraft.client.renderers.entity.RenderPrimalArrow;
/*      */ import thaumcraft.client.renderers.entity.RenderPrimalOrb;
/*      */ import thaumcraft.client.renderers.entity.RenderSpecialItem;
/*      */ import thaumcraft.client.renderers.entity.RenderTaintChicken;
/*      */ import thaumcraft.client.renderers.entity.RenderTaintCow;
/*      */ import thaumcraft.client.renderers.entity.RenderTaintCreeper;
/*      */ import thaumcraft.client.renderers.entity.RenderTaintPig;
/*      */ import thaumcraft.client.renderers.entity.RenderTaintSheep;
/*      */ import thaumcraft.client.renderers.entity.RenderTaintSpider;
/*      */ import thaumcraft.client.renderers.entity.RenderTaintSpore;
/*      */ import thaumcraft.client.renderers.entity.RenderTaintSporeSwarmer;
/*      */ import thaumcraft.client.renderers.entity.RenderTaintSwarm;
/*      */ import thaumcraft.client.renderers.entity.RenderTaintVillager;
/*      */ import thaumcraft.client.renderers.entity.RenderTaintacle;
/*      */ import thaumcraft.client.renderers.entity.RenderThaumicSlime;
/*      */ import thaumcraft.client.renderers.entity.RenderTravelingTrunk;
/*      */ import thaumcraft.client.renderers.entity.RenderWisp;
/*      */ import thaumcraft.client.renderers.item.ItemBannerRenderer;
/*      */ import thaumcraft.client.renderers.item.ItemBowBoneRenderer;
/*      */ import thaumcraft.client.renderers.item.ItemThaumometerRenderer;
/*      */ import thaumcraft.client.renderers.item.ItemTrunkSpawnerRenderer;
/*      */ import thaumcraft.client.renderers.item.ItemWandRenderer;
/*      */ import thaumcraft.client.renderers.models.entities.ModelEldritchGolem;
/*      */ import thaumcraft.client.renderers.models.entities.ModelEldritchGuardian;
/*      */ import thaumcraft.client.renderers.models.entities.ModelGolem;
/*      */ import thaumcraft.client.renderers.models.entities.ModelPech;
/*      */ import thaumcraft.client.renderers.models.entities.ModelTaintSheep1;
/*      */ import thaumcraft.client.renderers.models.entities.ModelTaintSheep2;
/*      */ import thaumcraft.client.renderers.models.entities.ModelTrunk;
/*      */ import thaumcraft.client.renderers.tile.ItemJarFilledRenderer;
/*      */ import thaumcraft.client.renderers.tile.ItemJarNodeRenderer;
/*      */ import thaumcraft.client.renderers.tile.ItemNodeRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileAlchemyFurnaceAdvancedRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileAlembicRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileArcaneBoreBaseRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileArcaneBoreRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileArcaneLampRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileArcaneWorkbenchRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileBannerRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileBellowsRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileCentrifugeRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileChestHungryRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileCrucibleRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileCrystalRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileDeconstructionTableRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileEldritchCapRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileEldritchCrabSpawnerRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileEldritchCrystalRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileEldritchLockRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileEldritchNothingRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileEldritchObeliskRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileEldritchPortalRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileEssentiaCrystalizerRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileEssentiaReservoirRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileEtherealBloomRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileFluxScrubberRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileFocalManipulatorRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileHoleRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileInfusionPillarRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileJarRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileMagicWorkbenchChargerRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileManaPodRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileMirrorRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileNodeConverterRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileNodeEnergizedRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileNodeRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileNodeStabilizerRenderer;
/*      */ import thaumcraft.client.renderers.tile.TilePedestalRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileResearchTableRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileRunicMatrixRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileTableRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileTubeBufferRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileTubeOnewayRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileTubeValveRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileVisRelayRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileWandPedestalRenderer;
/*      */ import thaumcraft.client.renderers.tile.TileWardedRenderer;
/*      */ import thaumcraft.common.CommonProxy;
/*      */ import thaumcraft.common.Thaumcraft;
/*      */ import thaumcraft.common.config.ConfigBlocks;
/*      */ import thaumcraft.common.config.ConfigEntities;
/*      */ import thaumcraft.common.config.ConfigItems;
/*      */ import thaumcraft.common.entities.EntityAspectOrb;
/*      */ import thaumcraft.common.entities.EntityFallingTaint;
/*      */ import thaumcraft.common.entities.EntityFollowingItem;
/*      */ import thaumcraft.common.entities.EntityItemGrate;
/*      */ import thaumcraft.common.entities.EntityPermanentItem;
/*      */ import thaumcraft.common.entities.EntitySpecialItem;
/*      */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*      */ import thaumcraft.common.entities.golems.EntityGolemBobber;
/*      */ import thaumcraft.common.entities.golems.EntityTravelingTrunk;
/*      */ import thaumcraft.common.entities.monster.EntityBrainyZombie;
/*      */ import thaumcraft.common.entities.monster.EntityCultistCleric;
/*      */ import thaumcraft.common.entities.monster.EntityCultistKnight;
/*      */ import thaumcraft.common.entities.monster.EntityEldritchCrab;
/*      */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*      */ import thaumcraft.common.entities.monster.EntityFireBat;
/*      */ import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
/*      */ import thaumcraft.common.entities.monster.EntityInhabitedZombie;
/*      */ import thaumcraft.common.entities.monster.EntityMindSpider;
/*      */ import thaumcraft.common.entities.monster.EntityPech;
/*      */ import thaumcraft.common.entities.monster.EntityTaintChicken;
/*      */ import thaumcraft.common.entities.monster.EntityTaintCow;
/*      */ import thaumcraft.common.entities.monster.EntityTaintCreeper;
/*      */ import thaumcraft.common.entities.monster.EntityTaintPig;
/*      */ import thaumcraft.common.entities.monster.EntityTaintSheep;
/*      */ import thaumcraft.common.entities.monster.EntityTaintSpider;
/*      */ import thaumcraft.common.entities.monster.EntityTaintSpore;
/*      */ import thaumcraft.common.entities.monster.EntityTaintSporeSwarmer;
/*      */ import thaumcraft.common.entities.monster.EntityTaintSwarm;
/*      */ import thaumcraft.common.entities.monster.EntityTaintVillager;
/*      */ import thaumcraft.common.entities.monster.EntityTaintacle;
/*      */ import thaumcraft.common.entities.monster.EntityTaintacleSmall;
/*      */ import thaumcraft.common.entities.monster.EntityThaumicSlime;
/*      */ import thaumcraft.common.entities.monster.EntityWisp;
/*      */ import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
/*      */ import thaumcraft.common.entities.monster.boss.EntityCultistPortal;
/*      */ import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
/*      */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*      */ import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
/*      */ import thaumcraft.common.entities.projectile.EntityAlumentum;
/*      */ import thaumcraft.common.entities.projectile.EntityBottleTaint;
/*      */ import thaumcraft.common.entities.projectile.EntityDart;
/*      */ import thaumcraft.common.entities.projectile.EntityEldritchOrb;
/*      */ import thaumcraft.common.entities.projectile.EntityEmber;
/*      */ import thaumcraft.common.entities.projectile.EntityExplosiveOrb;
/*      */ import thaumcraft.common.entities.projectile.EntityFrostShard;
/*      */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*      */ import thaumcraft.common.entities.projectile.EntityPechBlast;
/*      */ import thaumcraft.common.entities.projectile.EntityPrimalArrow;
/*      */ import thaumcraft.common.entities.projectile.EntityPrimalOrb;
/*      */ import thaumcraft.common.entities.projectile.EntityShockOrb;
/*      */ import thaumcraft.common.items.wands.WandManager;
/*      */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*      */ import thaumcraft.common.lib.research.ResearchManager;
/*      */ import thaumcraft.common.tiles.TileAlchemyFurnace;
/*      */ import thaumcraft.common.tiles.TileAlchemyFurnaceAdvanced;
/*      */ import thaumcraft.common.tiles.TileAlembic;
/*      */ import thaumcraft.common.tiles.TileArcaneBore;
/*      */ import thaumcraft.common.tiles.TileArcaneBoreBase;
/*      */ import thaumcraft.common.tiles.TileArcaneLamp;
/*      */ import thaumcraft.common.tiles.TileArcaneLampFertility;
/*      */ import thaumcraft.common.tiles.TileArcaneLampGrowth;
/*      */ import thaumcraft.common.tiles.TileArcaneWorkbench;
/*      */ import thaumcraft.common.tiles.TileBanner;
/*      */ import thaumcraft.common.tiles.TileBellows;
/*      */ import thaumcraft.common.tiles.TileCentrifuge;
/*      */ import thaumcraft.common.tiles.TileChestHungry;
/*      */ import thaumcraft.common.tiles.TileCrucible;
/*      */ import thaumcraft.common.tiles.TileCrystal;
/*      */ import thaumcraft.common.tiles.TileDeconstructionTable;
/*      */ import thaumcraft.common.tiles.TileEldritchAltar;
/*      */ import thaumcraft.common.tiles.TileEldritchCap;
/*      */ import thaumcraft.common.tiles.TileEldritchCrabSpawner;
/*      */ import thaumcraft.common.tiles.TileEldritchCrystal;
/*      */ import thaumcraft.common.tiles.TileEldritchLock;
/*      */ import thaumcraft.common.tiles.TileEldritchNothing;
/*      */ import thaumcraft.common.tiles.TileEldritchObelisk;
/*      */ import thaumcraft.common.tiles.TileEldritchPortal;
/*      */ import thaumcraft.common.tiles.TileEssentiaCrystalizer;
/*      */ import thaumcraft.common.tiles.TileEssentiaReservoir;
/*      */ import thaumcraft.common.tiles.TileEtherealBloom;
/*      */ import thaumcraft.common.tiles.TileFluxScrubber;
/*      */ import thaumcraft.common.tiles.TileFocalManipulator;
/*      */ import thaumcraft.common.tiles.TileHole;
/*      */ import thaumcraft.common.tiles.TileInfusionMatrix;
/*      */ import thaumcraft.common.tiles.TileInfusionPillar;
/*      */ import thaumcraft.common.tiles.TileJar;
/*      */ import thaumcraft.common.tiles.TileMagicBox;
/*      */ import thaumcraft.common.tiles.TileMagicWorkbenchCharger;
/*      */ import thaumcraft.common.tiles.TileManaPod;
/*      */ import thaumcraft.common.tiles.TileMirror;
/*      */ import thaumcraft.common.tiles.TileNode;
/*      */ import thaumcraft.common.tiles.TileNodeConverter;
/*      */ import thaumcraft.common.tiles.TileNodeEnergized;
/*      */ import thaumcraft.common.tiles.TileNodeStabilizer;
/*      */ import thaumcraft.common.tiles.TilePedestal;
/*      */ import thaumcraft.common.tiles.TileResearchTable;
/*      */ import thaumcraft.common.tiles.TileSpa;
/*      */ import thaumcraft.common.tiles.TileTable;
/*      */ import thaumcraft.common.tiles.TileThaumatorium;
/*      */ import thaumcraft.common.tiles.TileTubeBuffer;
/*      */ import thaumcraft.common.tiles.TileTubeOneway;
/*      */ import thaumcraft.common.tiles.TileTubeValve;
/*      */ import thaumcraft.common.tiles.TileVisRelay;
/*      */ import thaumcraft.common.tiles.TileWandPedestal;
/*      */ import thaumcraft.common.tiles.TileWarded;
/*      */ 
/*      */ public class ClientProxy extends CommonProxy
/*      */ {
/*  319 */   protected PlayerKnowledge playerResearch = new PlayerKnowledge();
/*  320 */   protected ResearchManager researchManager = new ResearchManager();
/*  321 */   public WandManager wandManager = new WandManager();
/*      */   
/*      */   public void registerHandlers()
/*      */   {
/*  325 */     FMLCommonHandler.instance().bus().register(new ClientTickEventsFML());
/*  326 */     MinecraftForge.EVENT_BUS.register(Thaumcraft.instance.renderEventHandler);
/*      */     
/*  328 */     MinecraftForge.EVENT_BUS.register(ConfigBlocks.blockTube);
/*  329 */     MinecraftForge.EVENT_BUS.register(ParticleEngine.instance);
/*  330 */     FMLCommonHandler.instance().bus().register(ParticleEngine.instance);
/*      */   }
/*      */   
/*      */ 
/*      */   public void registerKeyBindings()
/*      */   {
/*  336 */     FMLCommonHandler.instance().bus().register(new thaumcraft.common.lib.events.KeyHandler());
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*      */   {
/*  342 */     if ((world instanceof WorldClient)) {
/*  343 */       switch (ID) {
/*      */       case 0: 
/*  345 */         return new GuiGolem(player, (EntityGolemBase)((WorldClient)world).func_73045_a(x));
/*      */       case 1: 
/*  347 */         return new GuiPech(player.field_71071_by, world, (EntityPech)((WorldClient)world).func_73045_a(x));
/*      */       case 2: 
/*  349 */         return new GuiTravelingTrunk(player, (EntityTravelingTrunk)((WorldClient)world).func_73045_a(x));
/*      */       case 9: 
/*  351 */         return new GuiAlchemyFurnace(player.field_71071_by, (TileAlchemyFurnace)world.func_147438_o(x, y, z));
/*      */       
/*      */       case 3: 
/*  354 */         return new GuiThaumatorium(player.field_71071_by, (TileThaumatorium)world.func_147438_o(x, y, z));
/*      */       
/*      */       case 10: 
/*  357 */         return new GuiResearchTable(player, (TileResearchTable)world.func_147438_o(x, y, z));
/*      */       
/*      */       case 12: 
/*  360 */         return new GuiResearchBrowser();
/*      */       case 13: 
/*  362 */         return new GuiArcaneWorkbench(player.field_71071_by, (TileArcaneWorkbench)world.func_147438_o(x, y, z));
/*      */       
/*      */       case 8: 
/*  365 */         return new GuiDeconstructionTable(player.field_71071_by, (TileDeconstructionTable)world.func_147438_o(x, y, z));
/*      */       
/*      */       case 15: 
/*  368 */         return new GuiArcaneBore(player.field_71071_by, (TileArcaneBore)world.func_147438_o(x, y, z));
/*      */       
/*      */       case 16: 
/*  371 */         return new GuiHandMirror(player.field_71071_by, world, x, y, z);
/*      */       case 17: 
/*  373 */         return new GuiHoverHarness(player.field_71071_by, world, x, y, z);
/*      */       case 5: 
/*  375 */         return new GuiFocusPouch(player.field_71071_by, world, x, y, z);
/*      */       case 18: 
/*  377 */         return new GuiMagicBox(player.field_71071_by, (TileMagicBox)world.func_147438_o(x, y, z));
/*      */       
/*      */       case 19: 
/*  380 */         return new GuiSpa(player.field_71071_by, (TileSpa)world.func_147438_o(x, y, z));
/*      */       
/*      */       case 20: 
/*  383 */         return new GuiFocalManipulator(player.field_71071_by, (TileFocalManipulator)world.func_147438_o(x, y, z));
/*      */       }
/*      */       
/*      */     }
/*  387 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerDisplayInformation()
/*      */   {
/*  394 */     Thaumcraft.instance.aspectShift = FMLClientHandler.instance().hasOptifine();
/*  395 */     if (Loader.isModLoaded("NotEnoughItems")) {
/*  396 */       Thaumcraft.instance.aspectShift = true;
/*      */     }
/*      */     
/*      */ 
/*  400 */     setupItemRenderers();
/*      */     
/*      */ 
/*  403 */     setupEntityRenderers();
/*      */     
/*      */ 
/*  406 */     setupBlockRenderers();
/*      */     
/*      */ 
/*  409 */     setupTileRenderers();
/*      */   }
/*      */   
/*      */   private void setupItemRenderers() {
/*  413 */     MinecraftForgeClient.registerItemRenderer(ConfigItems.itemJarFilled, new ItemJarFilledRenderer());
/*  414 */     MinecraftForgeClient.registerItemRenderer(ConfigItems.itemJarNode, new ItemJarNodeRenderer());
/*  415 */     MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(ConfigBlocks.blockAiry), new ItemNodeRenderer());
/*  416 */     MinecraftForgeClient.registerItemRenderer(ConfigItems.itemThaumometer, new ItemThaumometerRenderer());
/*  417 */     MinecraftForgeClient.registerItemRenderer(ConfigItems.itemWandCasting, new ItemWandRenderer());
/*  418 */     MinecraftForgeClient.registerItemRenderer(ConfigItems.itemTrunkSpawner, new ItemTrunkSpawnerRenderer());
/*  419 */     MinecraftForgeClient.registerItemRenderer(ConfigItems.itemBowBone, new ItemBowBoneRenderer());
/*  420 */     MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(ConfigBlocks.blockWoodenDevice), new ItemBannerRenderer());
/*      */   }
/*      */   
/*      */   private void setupEntityRenderers()
/*      */   {
/*  425 */     RenderingRegistry.registerEntityRenderingHandler(EntityItemGrate.class, new RenderItem());
/*  426 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpecialItem.class, new RenderSpecialItem());
/*  427 */     RenderingRegistry.registerEntityRenderingHandler(EntityFollowingItem.class, new RenderFollowingItem());
/*  428 */     RenderingRegistry.registerEntityRenderingHandler(EntityPermanentItem.class, new RenderSpecialItem());
/*  429 */     RenderingRegistry.registerEntityRenderingHandler(EntityAspectOrb.class, new RenderAspectOrb());
/*  430 */     RenderingRegistry.registerEntityRenderingHandler(EntityGolemBobber.class, new RenderGolemBobber());
/*  431 */     RenderingRegistry.registerEntityRenderingHandler(EntityGolemBase.class, new RenderGolemBase(new ModelGolem(false)));
/*  432 */     RenderingRegistry.registerEntityRenderingHandler(EntityWisp.class, new RenderWisp());
/*  433 */     RenderingRegistry.registerEntityRenderingHandler(EntityAlumentum.class, new RenderAlumentum());
/*  434 */     RenderingRegistry.registerEntityRenderingHandler(EntityPrimalOrb.class, new RenderPrimalOrb());
/*  435 */     RenderingRegistry.registerEntityRenderingHandler(EntityEldritchOrb.class, new RenderEldritchOrb());
/*  436 */     RenderingRegistry.registerEntityRenderingHandler(EntityGolemOrb.class, new RenderElectricOrb());
/*  437 */     RenderingRegistry.registerEntityRenderingHandler(EntityEmber.class, new RenderEmber());
/*  438 */     RenderingRegistry.registerEntityRenderingHandler(EntityShockOrb.class, new RenderElectricOrb());
/*  439 */     RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveOrb.class, new RenderExplosiveOrb());
/*  440 */     RenderingRegistry.registerEntityRenderingHandler(EntityPechBlast.class, new thaumcraft.client.renderers.entity.RenderPechBlast());
/*  441 */     RenderingRegistry.registerEntityRenderingHandler(EntityBrainyZombie.class, new RenderBrainyZombie());
/*  442 */     RenderingRegistry.registerEntityRenderingHandler(EntityInhabitedZombie.class, new RenderInhabitedZombie());
/*  443 */     RenderingRegistry.registerEntityRenderingHandler(EntityGiantBrainyZombie.class, new RenderBrainyZombie());
/*  444 */     RenderingRegistry.registerEntityRenderingHandler(EntityPech.class, new RenderPech(new ModelPech(), 0.25F));
/*  445 */     RenderingRegistry.registerEntityRenderingHandler(EntityFireBat.class, new RenderFireBat());
/*  446 */     RenderingRegistry.registerEntityRenderingHandler(EntityFrostShard.class, new RenderFrostShard());
/*  447 */     RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart());
/*  448 */     RenderingRegistry.registerEntityRenderingHandler(EntityPrimalArrow.class, new RenderPrimalArrow());
/*  449 */     RenderingRegistry.registerEntityRenderingHandler(EntityFallingTaint.class, new RenderFallingTaint());
/*  450 */     RenderingRegistry.registerEntityRenderingHandler(EntityThaumicSlime.class, new RenderThaumicSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
/*  451 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintSpider.class, new RenderTaintSpider());
/*  452 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintacle.class, new RenderTaintacle(0.6F, 10));
/*  453 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintacleSmall.class, new RenderTaintacle(0.2F, 6));
/*  454 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintacleGiant.class, new RenderTaintacle(1.0F, 14));
/*  455 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintSpore.class, new RenderTaintSpore());
/*  456 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintSporeSwarmer.class, new RenderTaintSporeSwarmer());
/*  457 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintSwarm.class, new RenderTaintSwarm());
/*  458 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintChicken.class, new RenderTaintChicken(new ModelChicken(), 0.3F));
/*  459 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintCow.class, new RenderTaintCow(new ModelCow(), 0.7F));
/*  460 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintCreeper.class, new RenderTaintCreeper());
/*  461 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintPig.class, new RenderTaintPig(new ModelPig(), 0.7F));
/*  462 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintSheep.class, new RenderTaintSheep(new ModelTaintSheep2(), new ModelTaintSheep1(), 0.7F));
/*  463 */     RenderingRegistry.registerEntityRenderingHandler(EntityTaintVillager.class, new RenderTaintVillager());
/*  464 */     RenderingRegistry.registerEntityRenderingHandler(EntityTravelingTrunk.class, new RenderTravelingTrunk(new ModelTrunk(), 0.5F));
/*  465 */     RenderingRegistry.registerEntityRenderingHandler(EntityMindSpider.class, new RenderMindSpider());
/*  466 */     RenderingRegistry.registerEntityRenderingHandler(EntityEldritchGuardian.class, new RenderEldritchGuardian(new ModelEldritchGuardian(), 0.5F));
/*  467 */     RenderingRegistry.registerEntityRenderingHandler(EntityEldritchWarden.class, new RenderEldritchGuardian(new ModelEldritchGuardian(), 0.6F));
/*  468 */     RenderingRegistry.registerEntityRenderingHandler(EntityCultistPortal.class, new RenderCultistPortal());
/*  469 */     RenderingRegistry.registerEntityRenderingHandler(EntityCultistKnight.class, new RenderCultist());
/*  470 */     RenderingRegistry.registerEntityRenderingHandler(EntityCultistLeader.class, new RenderCultist());
/*  471 */     RenderingRegistry.registerEntityRenderingHandler(EntityCultistCleric.class, new RenderCultist());
/*  472 */     RenderingRegistry.registerEntityRenderingHandler(EntityEldritchGolem.class, new RenderEldritchGolem(new ModelEldritchGolem(), 0.5F));
/*  473 */     RenderingRegistry.registerEntityRenderingHandler(EntityBottleTaint.class, new RenderSnowball(ConfigItems.itemBottleTaint, 0));
/*  474 */     RenderingRegistry.registerEntityRenderingHandler(EntityEldritchCrab.class, new RenderEldritchCrab());
/*      */     
/*      */ 
/*  477 */     VillagerRegistry.instance().registerVillagerSkin(ConfigEntities.entWizardId, new ResourceLocation("thaumcraft", "textures/models/wizard.png"));
/*  478 */     VillagerRegistry.instance().registerVillagerSkin(ConfigEntities.entBankerId, new ResourceLocation("thaumcraft", "textures/models/moneychanger.png"));
/*      */   }
/*      */   
/*      */   void setupTileRenderers() {
/*  482 */     registerTileEntitySpecialRenderer(TileAlembic.class, new TileAlembicRenderer());
/*  483 */     registerTileEntitySpecialRenderer(TileArcaneBore.class, new TileArcaneBoreRenderer());
/*  484 */     registerTileEntitySpecialRenderer(TileArcaneBoreBase.class, new TileArcaneBoreBaseRenderer());
/*  485 */     registerTileEntitySpecialRenderer(TileArcaneLamp.class, new TileArcaneLampRenderer());
/*  486 */     registerTileEntitySpecialRenderer(TileArcaneLampGrowth.class, new TileArcaneLampRenderer());
/*  487 */     registerTileEntitySpecialRenderer(TileArcaneLampFertility.class, new TileArcaneLampRenderer());
/*  488 */     registerTileEntitySpecialRenderer(TileArcaneWorkbench.class, new TileArcaneWorkbenchRenderer());
/*  489 */     registerTileEntitySpecialRenderer(TileBanner.class, new TileBannerRenderer());
/*  490 */     registerTileEntitySpecialRenderer(TileBellows.class, new TileBellowsRenderer());
/*  491 */     registerTileEntitySpecialRenderer(TileCentrifuge.class, new TileCentrifugeRenderer());
/*  492 */     registerTileEntitySpecialRenderer(TileChestHungry.class, new TileChestHungryRenderer());
/*  493 */     registerTileEntitySpecialRenderer(TileCrucible.class, new TileCrucibleRenderer());
/*  494 */     registerTileEntitySpecialRenderer(TileCrystal.class, new TileCrystalRenderer());
/*  495 */     registerTileEntitySpecialRenderer(TileEldritchCrystal.class, new TileEldritchCrystalRenderer());
/*  496 */     registerTileEntitySpecialRenderer(TileDeconstructionTable.class, new TileDeconstructionTableRenderer());
/*  497 */     registerTileEntitySpecialRenderer(TileEldritchAltar.class, new TileEldritchCapRenderer("textures/models/obelisk_cap_altar.png"));
/*  498 */     registerTileEntitySpecialRenderer(TileEldritchCap.class, new TileEldritchCapRenderer("textures/models/obelisk_cap.png"));
/*  499 */     registerTileEntitySpecialRenderer(TileEldritchCrabSpawner.class, new TileEldritchCrabSpawnerRenderer());
/*  500 */     registerTileEntitySpecialRenderer(TileEldritchNothing.class, new TileEldritchNothingRenderer());
/*  501 */     registerTileEntitySpecialRenderer(TileEldritchObelisk.class, new TileEldritchObeliskRenderer());
/*  502 */     registerTileEntitySpecialRenderer(TileEldritchPortal.class, new TileEldritchPortalRenderer());
/*  503 */     registerTileEntitySpecialRenderer(TileEldritchLock.class, new TileEldritchLockRenderer());
/*  504 */     registerTileEntitySpecialRenderer(TileEssentiaCrystalizer.class, new TileEssentiaCrystalizerRenderer());
/*  505 */     registerTileEntitySpecialRenderer(TileEssentiaReservoir.class, new TileEssentiaReservoirRenderer());
/*  506 */     registerTileEntitySpecialRenderer(TileEtherealBloom.class, new TileEtherealBloomRenderer());
/*  507 */     registerTileEntitySpecialRenderer(TileHole.class, new TileHoleRenderer());
/*  508 */     registerTileEntitySpecialRenderer(TileInfusionMatrix.class, new TileRunicMatrixRenderer(0));
/*  509 */     registerTileEntitySpecialRenderer(TileInfusionPillar.class, new TileInfusionPillarRenderer());
/*  510 */     registerTileEntitySpecialRenderer(TileJar.class, new TileJarRenderer());
/*  511 */     registerTileEntitySpecialRenderer(TileMagicWorkbenchCharger.class, new TileMagicWorkbenchChargerRenderer());
/*  512 */     registerTileEntitySpecialRenderer(TileManaPod.class, new TileManaPodRenderer());
/*  513 */     TileMirrorRenderer tmr = new TileMirrorRenderer();
/*  514 */     registerTileEntitySpecialRenderer(TileMirror.class, tmr);
/*  515 */     registerTileEntitySpecialRenderer(thaumcraft.common.tiles.TileMirrorEssentia.class, tmr);
/*  516 */     registerTileEntitySpecialRenderer(TileNode.class, new TileNodeRenderer());
/*  517 */     registerTileEntitySpecialRenderer(TileNodeEnergized.class, new TileNodeEnergizedRenderer());
/*  518 */     registerTileEntitySpecialRenderer(TileNodeConverter.class, new TileNodeConverterRenderer());
/*  519 */     registerTileEntitySpecialRenderer(TileNodeStabilizer.class, new TileNodeStabilizerRenderer());
/*  520 */     registerTileEntitySpecialRenderer(TilePedestal.class, new TilePedestalRenderer());
/*  521 */     registerTileEntitySpecialRenderer(TileResearchTable.class, new TileResearchTableRenderer());
/*  522 */     registerTileEntitySpecialRenderer(TileTable.class, new TileTableRenderer());
/*  523 */     registerTileEntitySpecialRenderer(TileThaumatorium.class, new thaumcraft.client.renderers.tile.TileThaumatoriumRenderer());
/*  524 */     registerTileEntitySpecialRenderer(TileTubeBuffer.class, new TileTubeBufferRenderer());
/*  525 */     registerTileEntitySpecialRenderer(TileTubeOneway.class, new TileTubeOnewayRenderer());
/*  526 */     registerTileEntitySpecialRenderer(TileTubeValve.class, new TileTubeValveRenderer());
/*  527 */     registerTileEntitySpecialRenderer(TileVisRelay.class, new TileVisRelayRenderer());
/*  528 */     registerTileEntitySpecialRenderer(TileWandPedestal.class, new TileWandPedestalRenderer());
/*  529 */     registerTileEntitySpecialRenderer(TileWarded.class, new TileWardedRenderer());
/*  530 */     registerTileEntitySpecialRenderer(TileFocalManipulator.class, new TileFocalManipulatorRenderer());
/*  531 */     registerTileEntitySpecialRenderer(TileAlchemyFurnaceAdvanced.class, new TileAlchemyFurnaceAdvancedRenderer());
/*  532 */     registerTileEntitySpecialRenderer(TileFluxScrubber.class, new TileFluxScrubberRenderer());
/*      */   }
/*      */   
/*      */   void setupBlockRenderers() {
/*  536 */     ConfigBlocks.blockFluxGasRI = RenderingRegistry.getNextAvailableRenderId();
/*  537 */     registerBlockRenderer(new BlockGasRenderer());
/*  538 */     ConfigBlocks.blockArcaneFurnaceRI = RenderingRegistry.getNextAvailableRenderId();
/*  539 */     registerBlockRenderer(new BlockArcaneFurnaceRenderer());
/*  540 */     ConfigBlocks.blockMetalDeviceRI = RenderingRegistry.getNextAvailableRenderId();
/*  541 */     registerBlockRenderer(new BlockMetalDeviceRenderer());
/*  542 */     ConfigBlocks.blockStoneDeviceRI = RenderingRegistry.getNextAvailableRenderId();
/*  543 */     registerBlockRenderer(new BlockStoneDeviceRenderer());
/*  544 */     ConfigBlocks.blockTaintRI = RenderingRegistry.getNextAvailableRenderId();
/*  545 */     registerBlockRenderer(new BlockTaintRenderer());
/*  546 */     ConfigBlocks.blockCosmeticOpaqueRI = RenderingRegistry.getNextAvailableRenderId();
/*  547 */     registerBlockRenderer(new BlockCosmeticOpaqueRenderer());
/*  548 */     ConfigBlocks.blockTubeRI = RenderingRegistry.getNextAvailableRenderId();
/*  549 */     registerBlockRenderer(new BlockTubeRenderer());
/*  550 */     ConfigBlocks.blockTaintFibreRI = RenderingRegistry.getNextAvailableRenderId();
/*  551 */     registerBlockRenderer(new BlockTaintFibreRenderer());
/*  552 */     ConfigBlocks.blockJarRI = RenderingRegistry.getNextAvailableRenderId();
/*  553 */     registerBlockRenderer(new BlockJarRenderer());
/*  554 */     ConfigBlocks.blockCustomOreRI = RenderingRegistry.getNextAvailableRenderId();
/*  555 */     registerBlockRenderer(new BlockCustomOreRenderer());
/*  556 */     ConfigBlocks.blockChestHungryRI = RenderingRegistry.getNextAvailableRenderId();
/*  557 */     registerBlockRenderer(new BlockChestHungryRenderer());
/*  558 */     ConfigBlocks.blockTableRI = RenderingRegistry.getNextAvailableRenderId();
/*  559 */     registerBlockRenderer(new BlockTableRenderer());
/*  560 */     ConfigBlocks.blockCandleRI = RenderingRegistry.getNextAvailableRenderId();
/*  561 */     registerBlockRenderer(new BlockCandleRenderer());
/*  562 */     ConfigBlocks.blockWoodenDeviceRI = RenderingRegistry.getNextAvailableRenderId();
/*  563 */     registerBlockRenderer(new BlockWoodenDeviceRenderer());
/*  564 */     ConfigBlocks.blockLifterRI = RenderingRegistry.getNextAvailableRenderId();
/*  565 */     registerBlockRenderer(new BlockLifterRenderer());
/*  566 */     ConfigBlocks.blockCrystalRI = RenderingRegistry.getNextAvailableRenderId();
/*  567 */     registerBlockRenderer(new BlockCrystalRenderer());
/*  568 */     ConfigBlocks.blockWardedRI = RenderingRegistry.getNextAvailableRenderId();
/*  569 */     registerBlockRenderer(new BlockWardedRenderer());
/*  570 */     ConfigBlocks.blockEldritchRI = RenderingRegistry.getNextAvailableRenderId();
/*  571 */     registerBlockRenderer(new BlockEldritchRenderer());
/*  572 */     ConfigBlocks.blockEssentiaReservoirRI = RenderingRegistry.getNextAvailableRenderId();
/*  573 */     registerBlockRenderer(new BlockEssentiaReservoirRenderer());
/*  574 */     ConfigBlocks.blockLootUrnRI = RenderingRegistry.getNextAvailableRenderId();
/*  575 */     registerBlockRenderer(new BlockLootUrnRenderer());
/*  576 */     ConfigBlocks.blockLootCrateRI = RenderingRegistry.getNextAvailableRenderId();
/*  577 */     registerBlockRenderer(new BlockLootCrateRenderer());
/*      */   }
/*      */   
/*      */   public void registerTileEntitySpecialRenderer(Class tile, TileEntitySpecialRenderer renderer) {
/*  581 */     ClientRegistry.bindTileEntitySpecialRenderer(tile, renderer);
/*      */   }
/*      */   
/*      */   public void registerBlockRenderer(ISimpleBlockRenderingHandler renderer) {
/*  585 */     RenderingRegistry.registerBlockHandler(renderer);
/*      */   }
/*      */   
/*      */   public World getClientWorld()
/*      */   {
/*  590 */     return FMLClientHandler.instance().getClient().field_71441_e;
/*      */   }
/*      */   
/*      */   public void blockSparkle(World world, int x, int y, int z, int c, int count)
/*      */   {
/*  595 */     Color color = new Color(c);
/*  596 */     float r = color.getRed() / 255.0F;
/*  597 */     float g = color.getGreen() / 255.0F;
/*  598 */     float b = color.getBlue() / 255.0F;
/*  599 */     for (int a = 0; a < Thaumcraft.proxy.particleCount(count); a++)
/*      */     {
/*  601 */       if (c == 55537) {
/*  602 */         r = 0.33F + world.field_73012_v.nextFloat() * 0.67F;
/*  603 */         g = 0.33F + world.field_73012_v.nextFloat() * 0.67F;
/*  604 */         b = 0.33F + world.field_73012_v.nextFloat() * 0.67F;
/*      */       }
/*      */       
/*  607 */       Thaumcraft.proxy.drawGenericParticles(world, x - 0.1F + world.field_73012_v.nextFloat() * 1.2F, y - 0.1F + world.field_73012_v.nextFloat() * 1.2F, z - 0.1F + world.field_73012_v.nextFloat() * 1.2F, 0.0D, world.field_73012_v.nextFloat() * 0.02D, 0.0D, r - 0.2F + world.field_73012_v.nextFloat() * 0.4F, g - 0.2F + world.field_73012_v.nextFloat() * 0.4F, b - 0.2F + world.field_73012_v.nextFloat() * 0.4F, 0.9F, false, 112, 9, 1, 5 + world.field_73012_v.nextInt(8), world.field_73012_v.nextInt(10), 0.7F + world.field_73012_v.nextFloat() * 0.4F);
/*      */     }
/*      */   }
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
/*      */   public void sparkle(float x, float y, float z, float size, int color, float gravity)
/*      */   {
/*  623 */     if ((getClientWorld() != null) && (getClientWorld().field_73012_v.nextInt(6) < particleCount(2)))
/*      */     {
/*  625 */       FXSparkle fx = new FXSparkle(getClientWorld(), x, y, z, size, color, 6);
/*      */       
/*  627 */       fx.field_70145_X = true;
/*  628 */       fx.setGravity(gravity);
/*  629 */       ParticleEngine.instance.addEffect(getClientWorld(), fx);
/*      */     }
/*      */   }
/*      */   
/*      */   public void sparkle(float x, float y, float z, int color)
/*      */   {
/*  635 */     if ((getClientWorld() != null) && (getClientWorld().field_73012_v.nextInt(6) < particleCount(2)))
/*      */     {
/*  637 */       FXSparkle fx = new FXSparkle(getClientWorld(), x, y, z, 1.5F, color, 6);
/*      */       
/*  639 */       fx.field_70145_X = true;
/*  640 */       ParticleEngine.instance.addEffect(getClientWorld(), fx);
/*      */     }
/*      */   }
/*      */   
/*      */   public void spark(float x, float y, float z, float size, float r, float g, float b, float a)
/*      */   {
/*  646 */     if (getClientWorld() != null) {
/*  647 */       FXSpark fx = new FXSpark(getClientWorld(), x, y, z, size);
/*  648 */       fx.func_70538_b(r, g, b);
/*  649 */       fx.func_82338_g(a);
/*  650 */       ParticleEngine.instance.addEffect(getClientWorld(), fx);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void smokeSpiral(World world, double x, double y, double z, float rad, int start, int miny, int color)
/*      */   {
/*  657 */     FXSmokeSpiral fx = new FXSmokeSpiral(getClientWorld(), x, y, z, rad, start, miny);
/*      */     
/*  659 */     Color c = new Color(color);
/*  660 */     fx.func_70538_b(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/*  661 */     ParticleEngine.instance.addEffect(world, fx);
/*      */   }
/*      */   
/*      */ 
/*      */   public void crucibleBoilSound(World world, int xCoord, int yCoord, int zCoord)
/*      */   {
/*  667 */     world.func_72980_b(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, "thaumcraft:spill", 0.2F, 1.0F, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void crucibleBoil(World world, int xCoord, int yCoord, int zCoord, TileCrucible tile, int j)
/*      */   {
/*  674 */     for (int a = 0; a < particleCount(1); a++) {
/*  675 */       FXBubble fb = new FXBubble(world, xCoord + 0.2F + world.field_73012_v.nextFloat() * 0.6F, yCoord + 0.1F + tile.getFluidHeight(), zCoord + 0.2F + world.field_73012_v.nextFloat() * 0.6F, 0.0D, 0.0D, 0.0D, 3);
/*      */       
/*      */ 
/*      */ 
/*  679 */       if (tile.aspects.size() == 0) {
/*  680 */         fb.func_70538_b(1.0F, 1.0F, 1.0F);
/*      */       } else {
/*  682 */         Color color = new Color(tile.aspects.getAspects()[world.field_73012_v.nextInt(tile.aspects.getAspects().length)].getColor());
/*      */         
/*      */ 
/*      */ 
/*  686 */         fb.func_70538_b(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
/*      */       }
/*      */       
/*  689 */       fb.bubblespeed = (0.003D * j);
/*      */       
/*      */ 
/*  692 */       ParticleEngine.instance.addEffect(world, fb);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void crucibleBubble(World world, float x, float y, float z, float cr, float cg, float cb)
/*      */   {
/*  700 */     FXBubble fb = new FXBubble(world, x, y, z, 0.0D, 0.0D, 0.0D, 1);
/*  701 */     fb.func_70538_b(cr, cg, cb);
/*      */     
/*  703 */     ParticleEngine.instance.addEffect(world, fb);
/*      */   }
/*      */   
/*      */   public void crucibleFroth(World world, float x, float y, float z)
/*      */   {
/*  708 */     FXBubble fb = new FXBubble(world, x, y, z, 0.0D, 0.0D, 0.0D, -4);
/*  709 */     fb.func_70538_b(0.5F, 0.5F, 0.7F);
/*  710 */     fb.setFroth();
/*      */     
/*  712 */     ParticleEngine.instance.addEffect(world, fb);
/*      */   }
/*      */   
/*      */   public void crucibleFrothDown(World world, float x, float y, float z)
/*      */   {
/*  717 */     FXBubble fb = new FXBubble(world, x, y, z, 0.0D, 0.0D, 0.0D, -4);
/*  718 */     fb.func_70538_b(0.5F, 0.5F, 0.7F);
/*  719 */     fb.setFroth2();
/*      */     
/*  721 */     ParticleEngine.instance.addEffect(world, fb);
/*      */   }
/*      */   
/*      */ 
/*      */   public void wispFX(World worldObj, double posX, double posY, double posZ, float f, float g, float h, float i)
/*      */   {
/*  727 */     FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, f, g, h, i);
/*  728 */     ef.setGravity(0.02F);
/*      */     
/*  730 */     ParticleEngine.instance.addEffect(worldObj, ef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void wispFX2(World worldObj, double posX, double posY, double posZ, float size, int type, boolean shrink, boolean clip, float gravity)
/*      */   {
/*  737 */     FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, size, type);
/*  738 */     ef.setGravity(gravity);
/*  739 */     ef.shrink = shrink;
/*  740 */     ef.field_70145_X = clip;
/*      */     
/*  742 */     ParticleEngine.instance.addEffect(worldObj, ef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void wispFXEG(World worldObj, double posX, double posY, double posZ, Entity target)
/*      */   {
/*  749 */     for (int a = 0; a < particleCount(1); a++) {
/*  750 */       FXWispEG ef = new FXWispEG(worldObj, posX, posY, posZ, target);
/*  751 */       ParticleEngine.instance.addEffect(worldObj, ef);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void wispFX3(World worldObj, double posX, double posY, double posZ, double posX2, double posY2, double posZ2, float size, int type, boolean shrink, float gravity)
/*      */   {
/*  760 */     FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, posX2, posY2, posZ2, size, type);
/*      */     
/*  762 */     ef.setGravity(gravity);
/*  763 */     ef.shrink = shrink;
/*      */     
/*      */ 
/*  766 */     ParticleEngine.instance.addEffect(worldObj, ef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void wispFX4(World worldObj, double posX, double posY, double posZ, Entity target, int type, boolean shrink, float gravity)
/*      */   {
/*  773 */     FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, target, type);
/*  774 */     ef.setGravity(gravity);
/*  775 */     ef.shrink = shrink;
/*      */     
/*  777 */     ParticleEngine.instance.addEffect(worldObj, ef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void burst(World worldObj, double sx, double sy, double sz, float size)
/*      */   {
/*  784 */     FXBurst ef = new FXBurst(worldObj, sx, sy, sz, size);
/*  785 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(ef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void sourceStreamFX(World worldObj, double sx, double sy, double sz, float tx, float ty, float tz, int tagColor)
/*      */   {
/*  792 */     Color c = new Color(tagColor);
/*  793 */     FXWispArcing ef = new FXWispArcing(worldObj, tx, ty, tz, sx, sy, sz, 0.1F, c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/*      */     
/*  795 */     ef.setGravity(0.0F);
/*      */     
/*  797 */     ParticleEngine.instance.addEffect(worldObj, ef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void bolt(World worldObj, Entity sourceEntity, Entity targetedEntity)
/*      */   {
/*  804 */     FXLightningBolt bolt = new FXLightningBolt(worldObj, sourceEntity, targetedEntity, worldObj.field_73012_v.nextLong(), 4);
/*      */     
/*  806 */     bolt.defaultFractal();
/*  807 */     bolt.setType(0);
/*  808 */     bolt.finalizeBolt();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void nodeBolt(World worldObj, float x, float y, float z, Entity targetedEntity)
/*      */   {
/*  816 */     FXLightningBolt bolt = new FXLightningBolt(worldObj, x, y, z, targetedEntity.field_70165_t, targetedEntity.field_70163_u, targetedEntity.field_70161_v, worldObj.field_73012_v.nextLong(), 10, 4.0F, 5);
/*      */     
/*      */ 
/*  819 */     bolt.defaultFractal();
/*  820 */     bolt.setType(3);
/*  821 */     bolt.finalizeBolt();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void nodeBolt(World worldObj, float x, float y, float z, float x2, float y2, float z2)
/*      */   {
/*  828 */     FXLightningBolt bolt = new FXLightningBolt(worldObj, x, y, z, x2, y2, z2, worldObj.field_73012_v.nextLong(), 10, 4.0F, 5);
/*      */     
/*  830 */     bolt.defaultFractal();
/*  831 */     bolt.setType(0);
/*  832 */     bolt.finalizeBolt();
/*      */   }
/*      */   
/*      */ 
/*      */   public void excavateFX(int x, int y, int z, EntityPlayer p, int bi, int md, int progress)
/*      */   {
/*  838 */     RenderGlobal rg = Minecraft.func_71410_x().field_71438_f;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  844 */     rg.func_147587_b(p.func_145782_y(), x, y, z, progress);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void beam(World worldObj, double sx, double sy, double sz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, int age)
/*      */   {
/*  851 */     FXBeam beamcon = null;
/*  852 */     Color c = new Color(color);
/*  853 */     beamcon = new FXBeam(worldObj, sx, sy, sz, tx, ty, tz, c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, age);
/*      */     
/*  855 */     beamcon.setType(type);
/*  856 */     beamcon.setEndMod(endmod);
/*  857 */     beamcon.setReverse(reverse);
/*  858 */     beamcon.setPulse(false);
/*  859 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(beamcon);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object beamCont(World worldObj, EntityPlayer p, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact)
/*      */   {
/*  867 */     FXBeamWand beamcon = null;
/*  868 */     Color c = new Color(color);
/*  869 */     if ((input instanceof FXBeamWand))
/*  870 */       beamcon = (FXBeamWand)input;
/*  871 */     if ((beamcon == null) || (beamcon.field_70128_L)) {
/*  872 */       beamcon = new FXBeamWand(worldObj, p, tx, ty, tz, c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 8);
/*      */       
/*      */ 
/*  875 */       beamcon.setType(type);
/*  876 */       beamcon.setEndMod(endmod);
/*  877 */       beamcon.setReverse(reverse);
/*  878 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(beamcon);
/*      */     }
/*      */     else {
/*  881 */       beamcon.updateBeam(tx, ty, tz);
/*  882 */       beamcon.setEndMod(endmod);
/*  883 */       beamcon.impact = impact;
/*      */     }
/*  885 */     return beamcon;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object beamBore(World worldObj, double px, double py, double pz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact)
/*      */   {
/*  892 */     FXBeamBore beamcon = null;
/*  893 */     Color c = new Color(color);
/*  894 */     if ((input instanceof FXBeamBore))
/*  895 */       beamcon = (FXBeamBore)input;
/*  896 */     if ((beamcon == null) || (beamcon.field_70128_L)) {
/*  897 */       beamcon = new FXBeamBore(worldObj, px, py, pz, tx, ty, tz, c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 8);
/*      */       
/*      */ 
/*  900 */       beamcon.setType(type);
/*  901 */       beamcon.setEndMod(endmod);
/*  902 */       beamcon.setReverse(reverse);
/*  903 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(beamcon);
/*      */     }
/*      */     else {
/*  906 */       beamcon.updateBeam(tx, ty, tz);
/*  907 */       beamcon.setEndMod(endmod);
/*  908 */       beamcon.impact = impact;
/*      */     }
/*  910 */     return beamcon;
/*      */   }
/*      */   
/*      */ 
/*      */   public void boreDigFx(World worldObj, int x, int y, int z, int x2, int y2, int z2, Block bi, int md)
/*      */   {
/*  916 */     if (worldObj.field_73012_v.nextInt(10) == 0) {
/*  917 */       FXBoreSparkle fb = new FXBoreSparkle(worldObj, x + worldObj.field_73012_v.nextFloat(), y + worldObj.field_73012_v.nextFloat(), z + worldObj.field_73012_v.nextFloat(), x2 + 0.5D, y2 + 0.5D, z2 + 0.5D);
/*      */       
/*      */ 
/*  920 */       ParticleEngine.instance.addEffect(worldObj, fb);
/*      */     } else {
/*  922 */       FXBoreParticles fb = new FXBoreParticles(worldObj, x + worldObj.field_73012_v.nextFloat(), y + worldObj.field_73012_v.nextFloat(), z + worldObj.field_73012_v.nextFloat(), x2 + 0.5D, y2 + 0.5D, z2 + 0.5D, bi, worldObj.field_73012_v.nextInt(6), md).func_70596_a(x, y, z);
/*      */       
/*      */ 
/*      */ 
/*  926 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void essentiaTrailFx(World worldObj, int x, int y, int z, int x2, int y2, int z2, int count, int color, float scale)
/*      */   {
/*  934 */     FXEssentiaTrail fb = new FXEssentiaTrail(worldObj, x + 0.5D, y + 0.5D, z + 0.5D, x2 + 0.5D, y2 + 0.5D, z2 + 0.5D, count, color, scale);
/*      */     
/*  936 */     ParticleEngine.instance.addEffect(worldObj, fb);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void soulTrail(World world, Entity source, Entity target, float r, float g, float b)
/*      */   {
/*  943 */     for (int a = 0; a < particleCount(2); a++) {
/*  944 */       if (world.field_73012_v.nextInt(10) == 0) {
/*  945 */         FXSparkleTrail st = new FXSparkleTrail(world, source.field_70165_t - source.field_70130_N / 2.0F + world.field_73012_v.nextFloat() * source.field_70130_N, source.field_70163_u + world.field_73012_v.nextFloat() * source.field_70131_O, source.field_70161_v - source.field_70130_N / 2.0F + world.field_73012_v.nextFloat() * source.field_70130_N, target, r, g, b);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  951 */         st.field_70145_X = true;
/*  952 */         ParticleEngine.instance.addEffect(world, st);
/*      */       } else {
/*  954 */         FXSmokeTrail st = new FXSmokeTrail(world, source.field_70165_t - source.field_70130_N / 2.0F + world.field_73012_v.nextFloat() * source.field_70130_N, source.field_70163_u + world.field_73012_v.nextFloat() * source.field_70131_O, source.field_70161_v - source.field_70130_N / 2.0F + world.field_73012_v.nextFloat() * source.field_70130_N, target, r, g, b);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  960 */         st.field_70145_X = true;
/*  961 */         ParticleEngine.instance.addEffect(world, st);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public int particleCount(int base)
/*      */   {
/*  969 */     if (FMLClientHandler.instance().getClient().field_71474_y.field_74362_aa == 2)
/*  970 */       return 0;
/*  971 */     return FMLClientHandler.instance().getClient().field_71474_y.field_74362_aa == 1 ? base * 1 : base * 2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void furnaceLavaFx(World worldObj, int x, int y, int z, int facingX, int facingZ)
/*      */   {
/*  978 */     EntityLavaFX fb = new EntityLavaFX(worldObj, x + 0.5F + (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.3F + facingX * 1.0F, y + 0.3F, z + 0.5F + (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.3F + facingZ * 1.0F);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  983 */     fb.field_70181_x = (0.2F * worldObj.field_73012_v.nextFloat());
/*  984 */     float qx = facingX == 0 ? (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.5F : facingX * worldObj.field_73012_v.nextFloat();
/*      */     
/*  986 */     float qz = facingZ == 0 ? (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.5F : facingZ * worldObj.field_73012_v.nextFloat();
/*      */     
/*  988 */     fb.field_70159_w = (0.15F * qx);
/*  989 */     fb.field_70179_y = (0.15F * qz);
/*  990 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*      */   }
/*      */   
/*      */ 
/*      */   public void blockRunes(World world, double x, double y, double z, float r, float g, float b, int dur, float grav)
/*      */   {
/*  996 */     FXBlockRunes fb = new FXBlockRunes(world, x + 0.5D, y + 0.5D, z + 0.5D, r, g, b, dur);
/*      */     
/*  998 */     fb.setGravity(grav);
/*  999 */     ParticleEngine.instance.addEffect(world, fb);
/*      */   }
/*      */   
/*      */ 
/*      */   public void blockWard(World world, double x, double y, double z, ForgeDirection side, float f, float f1, float f2)
/*      */   {
/* 1005 */     FXBlockWard fb = new FXBlockWard(world, x + 0.5D, y + 0.5D, z + 0.5D, side, f, f1, f2);
/*      */     
/* 1007 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*      */   }
/*      */   
/*      */ 
/*      */   public Object swarmParticleFX(World worldObj, Entity targetedEntity, float f1, float f2, float pg)
/*      */   {
/* 1013 */     FXSwarm fx = new FXSwarm(worldObj, targetedEntity.field_70165_t + (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 2.0F, targetedEntity.field_70163_u + (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 2.0F, targetedEntity.field_70161_v + (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 2.0F, targetedEntity, 0.8F + worldObj.field_73012_v.nextFloat() * 0.2F, worldObj.field_73012_v.nextFloat() * 0.4F, 1.0F - worldObj.field_73012_v.nextFloat() * 0.2F, f1, f2, pg);
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
/* 1024 */     ParticleEngine.instance.addEffect(worldObj, fx);
/* 1025 */     return fx;
/*      */   }
/*      */   
/*      */   public void splooshFX(Entity e)
/*      */   {
/* 1030 */     float f = e.field_70170_p.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
/* 1031 */     float f1 = e.field_70170_p.field_73012_v.nextFloat() * 0.5F + 0.5F;
/* 1032 */     float f2 = MathHelper.func_76126_a(f) * 2.0F * 0.5F * f1;
/* 1033 */     float f3 = MathHelper.func_76134_b(f) * 2.0F * 0.5F * f1;
/* 1034 */     FXBreaking fx = new FXBreaking(e.field_70170_p, e.field_70165_t + f2, e.field_70163_u + e.field_70170_p.field_73012_v.nextFloat() * e.field_70131_O, e.field_70161_v + f3, Items.field_151123_aH);
/*      */     
/*      */ 
/* 1037 */     if (e.field_70170_p.field_73012_v.nextBoolean()) {
/* 1038 */       fx.func_70538_b(0.6F, 0.0F, 0.3F);
/* 1039 */       fx.func_82338_g(0.4F);
/*      */     } else {
/* 1041 */       fx.func_70538_b(0.3F, 0.0F, 0.3F);
/* 1042 */       fx.func_82338_g(0.6F);
/*      */     }
/*      */     
/* 1045 */     fx.setParticleMaxAge((int)(66.0F / (e.field_70170_p.field_73012_v.nextFloat() * 0.9F + 0.1F)));
/* 1046 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fx);
/*      */   }
/*      */   
/*      */   public void splooshFX(World worldObj, int x, int y, int z)
/*      */   {
/* 1051 */     float f = worldObj.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
/* 1052 */     float f1 = worldObj.field_73012_v.nextFloat() * 0.5F + 0.5F;
/* 1053 */     float f2 = MathHelper.func_76126_a(f) * 2.0F * 0.5F * f1;
/* 1054 */     float f3 = MathHelper.func_76134_b(f) * 2.0F * 0.5F * f1;
/* 1055 */     FXBreaking fx = new FXBreaking(worldObj, x + f2 + 0.5D, y + worldObj.field_73012_v.nextFloat(), z + f3 + 0.5D, Items.field_151123_aH);
/*      */     
/*      */ 
/* 1058 */     if (worldObj.field_73012_v.nextBoolean()) {
/* 1059 */       fx.func_70538_b(0.6F, 0.0F, 0.3F);
/* 1060 */       fx.func_82338_g(0.4F);
/*      */     } else {
/* 1062 */       fx.func_70538_b(0.3F, 0.0F, 0.3F);
/* 1063 */       fx.func_82338_g(0.6F);
/*      */     }
/*      */     
/* 1066 */     fx.setParticleMaxAge((int)(66.0F / (worldObj.field_73012_v.nextFloat() * 0.9F + 0.1F)));
/* 1067 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fx);
/*      */   }
/*      */   
/*      */   public void taintsplosionFX(Entity e)
/*      */   {
/* 1072 */     FXBreaking fx = new FXBreaking(e.field_70170_p, e.field_70165_t, e.field_70163_u + e.field_70170_p.field_73012_v.nextFloat() * e.field_70131_O, e.field_70161_v, Items.field_151123_aH);
/*      */     
/*      */ 
/* 1075 */     if (e.field_70170_p.field_73012_v.nextBoolean()) {
/* 1076 */       fx.func_70538_b(0.6F, 0.0F, 0.3F);
/* 1077 */       fx.func_82338_g(0.4F);
/*      */     } else {
/* 1079 */       fx.func_70538_b(0.3F, 0.0F, 0.3F);
/* 1080 */       fx.func_82338_g(0.6F);
/*      */     }
/* 1082 */     fx.field_70159_w = ((float)(Math.random() * 2.0D - 1.0D));
/* 1083 */     fx.field_70181_x = ((float)(Math.random() * 2.0D - 1.0D));
/* 1084 */     fx.field_70179_y = ((float)(Math.random() * 2.0D - 1.0D));
/* 1085 */     float f = (float)(Math.random() + Math.random() + 1.0D) * 0.15F;
/* 1086 */     float f1 = MathHelper.func_76133_a(fx.field_70159_w * fx.field_70159_w + fx.field_70181_x * fx.field_70181_x + fx.field_70179_y * fx.field_70179_y);
/*      */     
/* 1088 */     fx.field_70159_w = (fx.field_70159_w / f1 * f * 0.9640000000596046D);
/*      */     
/* 1090 */     fx.field_70181_x = (fx.field_70181_x / f1 * f * 0.9640000000596046D + 0.10000000149011612D);
/*      */     
/* 1092 */     fx.field_70179_y = (fx.field_70179_y / f1 * f * 0.9640000000596046D);
/*      */     
/* 1094 */     fx.setParticleMaxAge((int)(66.0F / (e.field_70170_p.field_73012_v.nextFloat() * 0.9F + 0.1F)));
/* 1095 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fx);
/*      */   }
/*      */   
/*      */   public void tentacleAriseFX(Entity e)
/*      */   {
/* 1100 */     int xx = MathHelper.func_76128_c(e.field_70165_t);
/* 1101 */     int yy = MathHelper.func_76128_c(e.field_70163_u) - 1;
/* 1102 */     int zz = MathHelper.func_76128_c(e.field_70161_v);
/*      */     
/* 1104 */     for (int j = 0; j < 2.0F * e.field_70131_O; j++) {
/* 1105 */       float f = e.field_70170_p.field_73012_v.nextFloat() * 3.1415927F * e.field_70131_O;
/* 1106 */       float f1 = e.field_70170_p.field_73012_v.nextFloat() * 0.5F + 0.5F;
/* 1107 */       float f2 = MathHelper.func_76126_a(f) * e.field_70131_O * 0.25F * f1;
/* 1108 */       float f3 = MathHelper.func_76134_b(f) * e.field_70131_O * 0.25F * f1;
/* 1109 */       FXBreaking fx = new FXBreaking(e.field_70170_p, e.field_70165_t + f2, e.field_70163_u, e.field_70161_v + f3, Items.field_151123_aH);
/*      */       
/* 1111 */       fx.func_70538_b(0.4F, 0.0F, 0.4F);
/* 1112 */       fx.func_82338_g(0.5F);
/* 1113 */       fx.setParticleMaxAge((int)(66.0F / (e.field_70170_p.field_73012_v.nextFloat() * 0.9F + 0.1F)));
/* 1114 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fx);
/*      */       
/*      */ 
/* 1117 */       if (!e.field_70170_p.func_147437_c(xx, yy, zz)) {
/* 1118 */         f = e.field_70170_p.field_73012_v.nextFloat() * 3.1415927F * e.field_70131_O;
/* 1119 */         f1 = e.field_70170_p.field_73012_v.nextFloat() * 0.5F + 0.5F;
/* 1120 */         f2 = MathHelper.func_76126_a(f) * e.field_70131_O * 0.25F * f1;
/* 1121 */         f3 = MathHelper.func_76134_b(f) * e.field_70131_O * 0.25F * f1;
/* 1122 */         EntityDiggingFX fx2 = new EntityDiggingFX(e.field_70170_p, e.field_70165_t + f2, e.field_70163_u, e.field_70161_v + f3, 0.0D, 0.0D, 0.0D, e.field_70170_p.func_147439_a(xx, yy, zz), e.field_70170_p.func_72805_g(xx, yy, zz), 1).func_70596_a(xx, yy, zz);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1128 */         FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fx2);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void slimeJumpFX(Entity e, int i)
/*      */   {
/* 1136 */     float f = e.field_70170_p.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
/* 1137 */     float f1 = e.field_70170_p.field_73012_v.nextFloat() * 0.5F + 0.5F;
/* 1138 */     float f2 = MathHelper.func_76126_a(f) * i * 0.5F * f1;
/* 1139 */     float f3 = MathHelper.func_76134_b(f) * i * 0.5F * f1;
/* 1140 */     FXBreaking fx = new FXBreaking(e.field_70170_p, e.field_70165_t + f2, (e.field_70121_D.field_72338_b + e.field_70121_D.field_72337_e) / 2.0D, e.field_70161_v + f3, Items.field_151123_aH);
/*      */     
/*      */ 
/* 1143 */     fx.func_70538_b(0.7F, 0.0F, 1.0F);
/* 1144 */     fx.func_82338_g(0.4F);
/* 1145 */     fx.setParticleMaxAge((int)(66.0F / (e.field_70170_p.field_73012_v.nextFloat() * 0.9F + 0.1F)));
/* 1146 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fx);
/*      */   }
/*      */   
/*      */ 
/*      */   public void dropletFX(World world, float i, float j, float k, float r, float g, float b)
/*      */   {
/* 1152 */     FXDrop obj = new FXDrop(world, i, j, k, r, g, b);
/*      */     
/* 1154 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(obj);
/*      */   }
/*      */   
/*      */   public void taintLandFX(Entity e)
/*      */   {
/* 1159 */     float f = e.field_70170_p.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
/* 1160 */     float f1 = e.field_70170_p.field_73012_v.nextFloat() * 0.5F + 0.5F;
/* 1161 */     float f2 = MathHelper.func_76126_a(f) * 2.0F * 0.5F * f1;
/* 1162 */     float f3 = MathHelper.func_76134_b(f) * 2.0F * 0.5F * f1;
/* 1163 */     if (e.field_70170_p.field_72995_K) {
/* 1164 */       FXBreaking fx = new FXBreaking(e.field_70170_p, e.field_70165_t + f2, (e.field_70121_D.field_72338_b + e.field_70121_D.field_72337_e) / 2.0D, e.field_70161_v + f3, Items.field_151123_aH);
/*      */       
/*      */ 
/* 1167 */       fx.func_70538_b(0.1F, 0.0F, 0.1F);
/* 1168 */       fx.func_82338_g(0.4F);
/* 1169 */       fx.setParticleMaxAge((int)(66.0F / (e.field_70170_p.field_73012_v.nextFloat() * 0.9F + 0.1F)));
/* 1170 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fx);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/* 1175 */   private HashMap<String, IIcon> customIcons = new HashMap();
/*      */   
/*      */ 
/*      */   public void hungryNodeFX(World worldObj, int sourceX, int sourceY, int sourceZ, int targetX, int targetY, int targetZ, Block block, int md)
/*      */   {
/* 1180 */     FXBoreParticles fb = new FXBoreParticles(worldObj, sourceX + worldObj.field_73012_v.nextFloat(), sourceY + worldObj.field_73012_v.nextFloat(), sourceZ + worldObj.field_73012_v.nextFloat(), targetX + 0.5D, targetY + 0.5D, targetZ + 0.5D, block, worldObj.field_73012_v.nextInt(6), md).func_70596_a(sourceX, sourceY, sourceZ);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1185 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*      */   }
/*      */   
/*      */ 
/*      */   public void drawInfusionParticles1(World worldObj, double x, double y, double z, int x2, int y2, int z2, Item id, int md)
/*      */   {
/* 1191 */     FXBoreParticles fb = new FXBoreParticles(worldObj, x, y, z, x2 + 0.5D, y2 - 0.5D, z2 + 0.5D, id, worldObj.field_73012_v.nextInt(6), md).func_70596_a(x2, y2, z2);
/*      */     
/*      */ 
/* 1194 */     fb.func_82338_g(0.3F);
/* 1195 */     fb.field_70159_w = ((float)worldObj.field_73012_v.nextGaussian() * 0.03F);
/* 1196 */     fb.field_70181_x = ((float)worldObj.field_73012_v.nextGaussian() * 0.03F);
/* 1197 */     fb.field_70179_y = ((float)worldObj.field_73012_v.nextGaussian() * 0.03F);
/* 1198 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*      */   }
/*      */   
/*      */ 
/*      */   public void drawInfusionParticles2(World worldObj, double x, double y, double z, int x2, int y2, int z2, Block id, int md)
/*      */   {
/* 1204 */     FXBoreParticles fb = new FXBoreParticles(worldObj, x, y, z, x2 + 0.5D, y2 - 0.5D, z2 + 0.5D, id, worldObj.field_73012_v.nextInt(6), md).func_70596_a(x2, y2, z2);
/*      */     
/*      */ 
/* 1207 */     fb.func_82338_g(0.3F);
/* 1208 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*      */   }
/*      */   
/*      */ 
/*      */   public void drawInfusionParticles3(World worldObj, double x, double y, double z, int x2, int y2, int z2)
/*      */   {
/* 1214 */     FXBoreSparkle fb = new FXBoreSparkle(worldObj, x, y, z, x2 + 0.5D, y2 - 0.5D, z2 + 0.5D);
/*      */     
/* 1216 */     fb.func_70538_b(0.4F + worldObj.field_73012_v.nextFloat() * 0.2F, 0.2F, 0.6F + worldObj.field_73012_v.nextFloat() * 0.3F);
/*      */     
/* 1218 */     ParticleEngine.instance.addEffect(worldObj, fb);
/*      */   }
/*      */   
/*      */ 
/*      */   public void drawInfusionParticles4(World worldObj, double x, double y, double z, int x2, int y2, int z2)
/*      */   {
/* 1224 */     FXBoreSparkle fb = new FXBoreSparkle(worldObj, x, y, z, x2 + 0.5D, y2 - 0.5D, z2 + 0.5D);
/*      */     
/* 1226 */     fb.func_70538_b(0.2F, 0.6F + worldObj.field_73012_v.nextFloat() * 0.3F, 0.3F);
/* 1227 */     ParticleEngine.instance.addEffect(worldObj, fb);
/*      */   }
/*      */   
/*      */ 
/*      */   public void drawVentParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, int color)
/*      */   {
/* 1233 */     FXVent fb = new FXVent(worldObj, x, y, z, x2, y2, z2, color);
/* 1234 */     fb.func_82338_g(0.4F);
/* 1235 */     ParticleEngine.instance.addEffect(worldObj, fb);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void drawGenericParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, float r, float g, float b, float alpha, boolean loop, int start, int num, int inc, int age, int delay, float scale)
/*      */   {
/* 1245 */     FXGeneric fb = new FXGeneric(worldObj, x, y, z, x2, y2, z2);
/* 1246 */     fb.setMaxAge(age, delay);
/* 1247 */     fb.func_70538_b(r, g, b);
/* 1248 */     fb.func_82338_g(alpha);
/* 1249 */     fb.setLoop(loop);
/* 1250 */     fb.setParticles(start, num, inc);
/* 1251 */     fb.setScale(scale);
/*      */     
/* 1253 */     ParticleEngine.instance.addEffect(worldObj, fb);
/*      */   }
/*      */   
/*      */ 
/*      */   public void drawVentParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, int color, float scale)
/*      */   {
/* 1259 */     FXVent fb = new FXVent(worldObj, x, y, z, x2, y2, z2, color);
/* 1260 */     fb.func_82338_g(0.4F);
/* 1261 */     fb.setScale(scale);
/* 1262 */     ParticleEngine.instance.addEffect(worldObj, fb);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object beamPower(World worldObj, double px, double py, double pz, double tx, double ty, double tz, float r, float g, float b, boolean pulse, Object input)
/*      */   {
/* 1269 */     FXBeamPower beamcon = null;
/* 1270 */     if ((input instanceof FXBeamPower))
/* 1271 */       beamcon = (FXBeamPower)input;
/* 1272 */     if ((beamcon == null) || (beamcon.field_70128_L)) {
/* 1273 */       beamcon = new FXBeamPower(worldObj, px, py, pz, tx, ty, tz, r, g, b, 8);
/*      */       
/* 1275 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(beamcon);
/*      */     }
/*      */     else {
/* 1278 */       beamcon.updateBeam(px, py, pz, tx, ty, tz);
/* 1279 */       beamcon.setPulse(pulse, r, g, b);
/*      */     }
/* 1281 */     return beamcon;
/*      */   }
/*      */   
/*      */   public boolean isShiftKeyDown()
/*      */   {
/* 1286 */     return GuiScreen.func_146272_n();
/*      */   }
/*      */   
/*      */   public void bottleTaintBreak(World world, double x, double y, double z)
/*      */   {
/* 1291 */     String s = "iconcrack_" + Item.func_150891_b(ConfigItems.itemBottleTaint) + "_" + 0;
/* 1292 */     for (int k1 = 0; k1 < 8; k1++)
/*      */     {
/* 1294 */       Minecraft.func_71410_x().field_71438_f.func_72708_a(s, x, y, z, world.field_73012_v.nextGaussian() * 0.15D, world.field_73012_v.nextDouble() * 0.2D, world.field_73012_v.nextGaussian() * 0.15D);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1299 */     world.func_72980_b(x, y, z, "game.potion.smash", 1.0F, world.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
/*      */   }
/*      */   
/*      */ 
/*      */   public void arcLightning(World world, double x, double y, double z, double tx, double ty, double tz, float r, float g, float b, float h)
/*      */   {
/* 1305 */     FXSparkle ef2 = new FXSparkle(world, tx, ty, tz, tx, ty, tz, 3.0F, 6, 2);
/* 1306 */     ef2.setGravity(0.0F);
/* 1307 */     ef2.field_70145_X = true;
/* 1308 */     ef2.func_70538_b(r, g, b);
/* 1309 */     ParticleEngine.instance.addEffect(world, ef2);
/* 1310 */     FXArc efa = new FXArc(world, x, y, z, tx, ty, tz, r, g, b, h);
/* 1311 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(efa);
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/ClientProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */