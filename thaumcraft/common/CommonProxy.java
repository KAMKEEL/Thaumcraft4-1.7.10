/*     */ package thaumcraft.common;
/*     */ 
/*     */ import cpw.mods.fml.common.network.IGuiHandler;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.container.ContainerAlchemyFurnace;
/*     */ import thaumcraft.common.container.ContainerArcaneBore;
/*     */ import thaumcraft.common.container.ContainerArcaneWorkbench;
/*     */ import thaumcraft.common.container.ContainerDeconstructionTable;
/*     */ import thaumcraft.common.container.ContainerFocalManipulator;
/*     */ import thaumcraft.common.container.ContainerFocusPouch;
/*     */ import thaumcraft.common.container.ContainerHandMirror;
/*     */ import thaumcraft.common.container.ContainerHoverHarness;
/*     */ import thaumcraft.common.container.ContainerMagicBox;
/*     */ import thaumcraft.common.container.ContainerResearchTable;
/*     */ import thaumcraft.common.container.ContainerSpa;
/*     */ import thaumcraft.common.container.ContainerThaumatorium;
/*     */ import thaumcraft.common.entities.ContainerPech;
/*     */ import thaumcraft.common.entities.golems.ContainerGolem;
/*     */ import thaumcraft.common.entities.golems.ContainerTravelingTrunk;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.EntityTravelingTrunk;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.tiles.TileAlchemyFurnace;
/*     */ import thaumcraft.common.tiles.TileArcaneBore;
/*     */ import thaumcraft.common.tiles.TileArcaneWorkbench;
/*     */ import thaumcraft.common.tiles.TileCrucible;
/*     */ import thaumcraft.common.tiles.TileDeconstructionTable;
/*     */ import thaumcraft.common.tiles.TileFocalManipulator;
/*     */ import thaumcraft.common.tiles.TileMagicBox;
/*     */ import thaumcraft.common.tiles.TileResearchTable;
/*     */ import thaumcraft.common.tiles.TileSpa;
/*     */ import thaumcraft.common.tiles.TileThaumatorium;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommonProxy
/*     */   implements IGuiHandler
/*     */ {
/*     */   public PlayerKnowledge playerKnowledge;
/*     */   public ResearchManager researchManager;
/*  55 */   public WandManager wandManager = new WandManager();
/*     */   
/*     */   public PlayerKnowledge getPlayerKnowledge() {
/*  58 */     return this.playerKnowledge;
/*     */   }
/*     */   
/*     */   public ResearchManager getResearchManager() {
/*  62 */     return this.researchManager;
/*     */   }
/*     */   
/*     */   public Map<String, ArrayList<String>> getCompletedResearch() {
/*  66 */     return this.playerKnowledge.researchCompleted;
/*     */   }
/*     */   
/*     */   public Map<String, ArrayList<String>> getScannedObjects() {
/*  70 */     return this.playerKnowledge.objectsScanned;
/*     */   }
/*     */   
/*     */   public Map<String, ArrayList<String>> getScannedEntities() {
/*  74 */     return this.playerKnowledge.entitiesScanned;
/*     */   }
/*     */   
/*     */   public Map<String, ArrayList<String>> getScannedPhenomena() {
/*  78 */     return this.playerKnowledge.phenomenaScanned;
/*     */   }
/*     */   
/*     */   public Map<String, AspectList> getKnownAspects() {
/*  82 */     return this.playerKnowledge.aspectsDiscovered;
/*     */   }
/*     */   
/*     */ 
/*     */   public void registerDisplayInformation() {}
/*     */   
/*     */ 
/*     */   public void registerHandlers() {}
/*     */   
/*     */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*     */   {
/*  93 */     return null;
/*     */   }
/*     */   
/*     */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*     */   {
/*  98 */     switch (ID) {
/*     */     case 0: 
/* 100 */       return new ContainerGolem(player.field_71071_by, ((EntityGolemBase)((WorldServer)world).func_73045_a(x)).inventory);
/*     */     case 1: 
/* 102 */       return new ContainerPech(player.field_71071_by, world, (EntityPech)((WorldServer)world).func_73045_a(x));
/*     */     case 2: 
/* 104 */       return new ContainerTravelingTrunk(player.field_71071_by, world, (EntityTravelingTrunk)((WorldServer)world).func_73045_a(x));
/*     */     case 9: 
/* 106 */       return new ContainerAlchemyFurnace(player.field_71071_by, (TileAlchemyFurnace)world.func_147438_o(x, y, z));
/*     */     case 3: 
/* 108 */       return new ContainerThaumatorium(player.field_71071_by, (TileThaumatorium)world.func_147438_o(x, y, z));
/*     */     case 5: 
/* 110 */       return new ContainerFocusPouch(player.field_71071_by, world, x, y, z);
/*     */     case 10: 
/* 112 */       return new ContainerResearchTable(player.field_71071_by, (TileResearchTable)world.func_147438_o(x, y, z));
/*     */     case 13: 
/* 114 */       return new ContainerArcaneWorkbench(player.field_71071_by, (TileArcaneWorkbench)world.func_147438_o(x, y, z));
/*     */     case 8: 
/* 116 */       return new ContainerDeconstructionTable(player.field_71071_by, (TileDeconstructionTable)world.func_147438_o(x, y, z));
/*     */     case 15: 
/* 118 */       return new ContainerArcaneBore(player.field_71071_by, (TileArcaneBore)world.func_147438_o(x, y, z));
/*     */     case 16: 
/* 120 */       return new ContainerHandMirror(player.field_71071_by, world, x, y, z);
/*     */     case 17: 
/* 122 */       return new ContainerHoverHarness(player.field_71071_by, world, x, y, z);
/*     */     case 18: 
/* 124 */       return new ContainerMagicBox(player.field_71071_by, (TileMagicBox)world.func_147438_o(x, y, z));
/*     */     case 19: 
/* 126 */       return new ContainerSpa(player.field_71071_by, (TileSpa)world.func_147438_o(x, y, z));
/*     */     case 20: 
/* 128 */       return new ContainerFocalManipulator(player.field_71071_by, (TileFocalManipulator)world.func_147438_o(x, y, z));
/*     */     }
/* 130 */     return null;
/*     */   }
/*     */   
/*     */   public World getClientWorld() {
/* 134 */     return null;
/*     */   }
/*     */   
/*     */   public void blockSparkle(World world, int x, int y, int z, int i, int count) {}
/*     */   
/*     */   public void sparkle(float x, float y, float z, float size, int color, float gravity) {}
/*     */   
/*     */   public void sparkle(float x, float y, float z, int color) {}
/*     */   
/*     */   public void crucibleBoil(World world, int xCoord, int yCoord, int zCoord, TileCrucible tile, int j) {}
/*     */   
/*     */   public void crucibleBoilSound(World world, int xCoord, int yCoord, int zCoord) {}
/*     */   
/*     */   public void crucibleBubble(World world, float x, float y, float z, float cr, float cg, float cb) {}
/*     */   
/*     */   public int particleCount(int base) {
/* 150 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void wispFX(World worldObj, double posX, double posY, double posZ, float f, float g, float h, float i) {}
/*     */   
/*     */ 
/*     */   public void sourceStreamFX(World worldObj, double sx, double sy, double sz, float tx, float ty, float tz, int tag) {}
/*     */   
/*     */ 
/*     */   public void bolt(World worldObj, Entity sourceEntity, Entity targetedEntity) {}
/*     */   
/*     */ 
/*     */   public void furnaceLavaFx(World worldObj, int x, int y, int z, int facingX, int facingZ) {}
/*     */   
/*     */ 
/*     */   public void wispFX2(World worldObj, double posX, double posY, double posZ, float size, int type, boolean shrink, boolean clip, float gravity) {}
/*     */   
/*     */ 
/*     */   public void crucibleFroth(World world, float x, float y, float z) {}
/*     */   
/*     */   public void crucibleFrothDown(World world, float x, float y, float z) {}
/*     */   
/*     */   public Object beamCont(World worldObj, EntityPlayer pm, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact)
/*     */   {
/* 175 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void excavateFX(int x, int y, int z, EntityPlayer p, int bi, int md, int progress) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void burst(World worldObj, double sx, double sy, double sz, float size) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void wispFX3(World worldObj, double posX, double posY, double posZ, double posX2, double posY2, double posZ2, float size, int type, boolean shrink, float gravity) {}
/*     */   
/*     */ 
/*     */   public void smokeSpiral(World m, double x, double y, double z, float rad, int start, int miny, int color) {}
/*     */   
/*     */ 
/*     */   public void beam(World worldObj, double sx, double sy, double sz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, int impact) {}
/*     */   
/*     */ 
/*     */   public void blockRunes(World world, double x, double y, double z, float r, float g, float b, int dur, float grav) {}
/*     */   
/*     */ 
/*     */   public Object beamBore(World worldObj, double px, double py, double pz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact)
/*     */   {
/* 202 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void boreDigFx(World worldObj, int x, int y, int z, int x2, int y2, int z2, Block bi, int md) {}
/*     */   
/*     */ 
/*     */   public void soulTrail(World world, Entity source, Entity target, float r, float g, float b) {}
/*     */   
/*     */ 
/*     */   public void nodeBolt(World worldObj, float x, float y, float z, Entity targetedEntity) {}
/*     */   
/*     */   public void splooshFX(Entity e) {}
/*     */   
/*     */   public void tentacleAriseFX(Entity e) {}
/*     */   
/*     */   public void slimeJumpFX(Entity e, int i) {}
/*     */   
/*     */   public void dropletFX(World world, float i, float j, float k, float r, float g, float b) {}
/*     */   
/*     */   public void taintLandFX(Entity e) {}
/*     */   
/*     */   public Object swarmParticleFX(World worldObj, Entity targetedEntity, float f1, float f2, float pg)
/*     */   {
/* 226 */     return null;
/*     */   }
/*     */   
/*     */   public void taintsplosionFX(Entity e) {}
/*     */   
/*     */   public IIcon getIcon(String string) {
/* 232 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void registerCustomIcons() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void hungryNodeFX(World worldObj, int tx, int ty, int tz, int xCoord, int yCoord, int zCoord, Block block, int md) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void essentiaTrailFx(World worldObj, int x, int y, int z, int x2, int y2, int z2, int count, int color, float scale) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void splooshFX(World worldObj, int x, int y, int z) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void nodeBolt(World worldObj, float x, float y, float z, float x2, float y2, float z2) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawInfusionParticles1(World worldObj, double x, double y, double z, int x2, int y2, int z2, Item bi, int md) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawInfusionParticles2(World worldObj, double x, double y, double z, int x2, int y2, int z2, Block bi, int md) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawInfusionParticles3(World worldObj, double x, double y, double z, int x2, int y2, int z2) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawInfusionParticles4(World worldObj, double x, double y, double z, int x2, int y2, int z2) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawVentParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, int color) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void blockWard(World world, double x, double y, double z, ForgeDirection side, float f, float f1, float f2) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void wispFX4(World worldObj, double posX, double posY, double posZ, Entity target, int type, boolean shrink, float gravity) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void registerKeyBindings() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object beamPower(World worldObj, double px, double py, double pz, double tx, double ty, double tz, float r, float g, float b, boolean pulse, Object input)
/*     */   {
/* 312 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isShiftKeyDown() {
/* 316 */     return false;
/*     */   }
/*     */   
/*     */   public void wispFXEG(World worldObj, double posX, double posY, double posZ, Entity target) {}
/*     */   
/*     */   public void reservoirBubble(World worldObj, int xCoord, int yCoord, int zCoord, int color) {}
/*     */   
/*     */   public void spark(float x, float y, float z, float size, float r, float g, float b, float a) {}
/*     */   
/*     */   public void drawVentParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, int color, float scale) {}
/*     */   
/*     */   public void bottleTaintBreak(World world, double x, double y, double z) {}
/*     */   
/*     */   public void drawGenericParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, float r, float g, float b, float alpha, boolean loop, int start, int num, int inc, int age, int delay, float scale) {}
/*     */   
/*     */   public void arcLightning(World world, double x, double y, double z, double tx, double ty, double tz, float r, float g, float b, float h) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/CommonProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */