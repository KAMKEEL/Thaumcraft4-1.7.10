/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.IFuelHandler;
/*     */ import cpw.mods.fml.common.eventhandler.Event.Result;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import java.util.WeakHashMap;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraftforge.event.entity.player.FillBucketEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent.MultiPlaceEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
/*     */ import net.minecraftforge.event.world.ChunkDataEvent.Load;
/*     */ import net.minecraftforge.event.world.ChunkDataEvent.Save;
/*     */ import net.minecraftforge.event.world.NoteBlockEvent.Play;
/*     */ import net.minecraftforge.event.world.WorldEvent.Load;
/*     */ import net.minecraftforge.event.world.WorldEvent.Save;
/*     */ import net.minecraftforge.event.world.WorldEvent.Unload;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.equipment.ItemPrimalCrusher;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ChunkLoc;
/*     */ import thaumcraft.common.lib.world.dim.Cell;
/*     */ import thaumcraft.common.lib.world.dim.MazeHandler;
/*     */ import thaumcraft.common.tiles.TileSensor;
/*     */ 
/*     */ public class EventHandlerWorld implements IFuelHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void worldLoad(WorldEvent.Load event)
/*     */   {
/*  54 */     if ((!event.world.field_72995_K) && (event.world.field_73011_w.field_76574_g == 0)) {
/*  55 */       MazeHandler.loadMaze(event.world);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void worldSave(WorldEvent.Save event) {
/*  61 */     if ((!event.world.field_72995_K) && (event.world.field_73011_w.field_76574_g == 0)) {
/*  62 */       MazeHandler.saveMaze(event.world);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void worldUnload(WorldEvent.Unload event) {
/*  68 */     if (event.world.field_72995_K) return;
/*  69 */     thaumcraft.api.visnet.VisNetHandler.sources.remove(Integer.valueOf(event.world.field_73011_w.field_76574_g));
/*     */     try {
/*  71 */       TileSensor.noteBlockEvents.remove((WorldServer)event.world);
/*     */     } catch (Exception e) {
/*  73 */       FMLCommonHandler.instance().getFMLLogger().log(Level.WARN, "[Thaumcraft] Error unloading noteblock even handlers.", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void chunkSave(ChunkDataEvent.Save event)
/*     */   {
/*  82 */     NBTTagCompound var4 = new NBTTagCompound();
/*  83 */     event.getData().func_74782_a("Thaumcraft", var4);
/*  84 */     var4.func_74757_a(Config.regenKey, true);
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
/*     */   @SubscribeEvent
/*     */   public void chunkLoad(ChunkDataEvent.Load event)
/*     */   {
/* 106 */     int dim = event.world.field_73011_w.field_76574_g;
/* 107 */     ChunkCoordIntPair loc = event.getChunk().func_76632_l();
/*     */     
/*     */ 
/* 110 */     if ((!event.getData().func_74775_l("Thaumcraft").func_74764_b(Config.regenKey)) && ((Config.regenAmber) || (Config.regenAura) || (Config.regenCinnibar) || (Config.regenInfusedStone) || (Config.regenStructure) || (Config.regenTrees)))
/*     */     {
/* 112 */       FMLCommonHandler.instance().getFMLLogger().log(Level.WARN, "[Thaumcraft] World gen was never run for chunk at " + event.getChunk().func_76632_l() + ". Adding to queue for regeneration.");
/* 113 */       ArrayList<ChunkLoc> chunks = (ArrayList)ServerTickEventsFML.chunksToGenerate.get(Integer.valueOf(dim));
/* 114 */       if (chunks == null) {
/* 115 */         ServerTickEventsFML.chunksToGenerate.put(Integer.valueOf(dim), new ArrayList());
/* 116 */         chunks = (ArrayList)ServerTickEventsFML.chunksToGenerate.get(Integer.valueOf(dim));
/*     */       }
/* 118 */       if (chunks != null) {
/* 119 */         chunks.add(new ChunkLoc(loc.field_77276_a, loc.field_77275_b));
/* 120 */         ServerTickEventsFML.chunksToGenerate.put(Integer.valueOf(dim), chunks);
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
/*     */   public int getBurnTime(ItemStack fuel)
/*     */   {
/* 134 */     if (fuel.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 0))) return 6400;
/* 135 */     if (fuel.func_77969_a(new ItemStack(ConfigBlocks.blockMagicalLog))) return 400;
/* 136 */     return 0;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onCrafting(PlayerEvent.ItemCraftedEvent event)
/*     */   {
/* 142 */     int warp = thaumcraft.api.ThaumcraftApi.getWarp(event.crafting);
/* 143 */     if ((!Config.wuss) && (warp > 0) && 
/* 144 */       (!event.player.field_70170_p.field_72995_K)) {
/* 145 */       thaumcraft.common.Thaumcraft.addStickyWarpToPlayer(event.player, warp);
/*     */     }
/*     */     
/*     */ 
/* 149 */     if ((event.crafting.func_77973_b() == ConfigItems.itemResource) && (event.crafting.func_77960_j() == 13) && (event.crafting.func_77942_o()))
/*     */     {
/*     */ 
/* 152 */       for (int var2 = 0; var2 < 9; var2++)
/*     */       {
/* 154 */         ItemStack var3 = event.craftMatrix.func_70301_a(var2);
/*     */         
/* 156 */         if ((var3 != null) && ((var3.func_77973_b() instanceof thaumcraft.common.items.ItemEssence)))
/*     */         {
/* 158 */           var3.field_77994_a += 1;
/* 159 */           event.craftMatrix.func_70299_a(var2, var3);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 164 */     if ((event.crafting.func_77973_b() == Item.func_150898_a(ConfigBlocks.blockMetalDevice)) && (event.crafting.func_77960_j() == 3)) {
/* 165 */       ItemStack var3 = event.craftMatrix.func_70301_a(4);
/* 166 */       var3.field_77994_a += 1;
/* 167 */       event.craftMatrix.func_70299_a(4, var3);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void harvestEvent(BlockEvent.HarvestDropsEvent event) {
/* 173 */     EntityPlayer player = event.harvester;
/* 174 */     if ((event.drops == null) || (event.drops.size() == 0) || (player == null)) { return;
/*     */     }
/* 176 */     ItemStack held = player.field_71071_by.func_70448_g();
/*     */     
/* 178 */     if ((held != null) && (((held.func_77973_b() instanceof thaumcraft.common.items.equipment.ItemElementalPickaxe)) || ((held.func_77973_b() instanceof ItemPrimalCrusher)) || (((held.func_77973_b() instanceof ItemWandCasting)) && (((ItemWandCasting)held.func_77973_b()).getFocus(held) != null) && (((ItemWandCasting)held.func_77973_b()).getFocus(held).isUpgradedWith(((ItemWandCasting)held.func_77973_b()).getFocusItem(held), thaumcraft.common.items.wands.foci.ItemFocusExcavation.dowsing)))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 186 */       int fortune = EnchantmentHelper.func_77517_e(player);
/* 187 */       if ((held.func_77973_b() instanceof ItemWandCasting)) {
/* 188 */         fortune = ((ItemWandCasting)held.func_77973_b()).getFocus(held).getUpgradeLevel(((ItemWandCasting)held.func_77973_b()).getFocusItem(held), thaumcraft.api.wands.FocusUpgradeType.treasure);
/*     */       }
/*     */       
/*     */ 
/* 192 */       float chance = 0.2F + fortune * 0.075F;
/*     */       
/* 194 */       for (int a = 0; a < event.drops.size(); a++)
/*     */       {
/* 196 */         ItemStack is = (ItemStack)event.drops.get(a);
/* 197 */         ItemStack smr = Utils.findSpecialMiningResult(is, chance, event.world.field_73012_v);
/* 198 */         if (!is.func_77969_a(smr)) {
/* 199 */           event.drops.set(a, smr);
/* 200 */           if (!event.world.field_72995_K) {
/* 201 */             event.world.func_72908_a(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, "random.orb", 0.2F, 0.7F + event.world.field_73012_v.nextFloat() * 0.2F);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void noteEvent(NoteBlockEvent.Play event)
/*     */   {
/* 213 */     if (event.world.field_72995_K) { return;
/*     */     }
/* 215 */     if (!TileSensor.noteBlockEvents.containsKey(event.world)) {
/* 216 */       TileSensor.noteBlockEvents.put((WorldServer)event.world, new ArrayList());
/*     */     }
/*     */     
/* 219 */     ArrayList<Integer[]> list = (ArrayList)TileSensor.noteBlockEvents.get(event.world);
/*     */     
/* 221 */     list.add(new Integer[] { Integer.valueOf(event.x), Integer.valueOf(event.y), Integer.valueOf(event.z), Integer.valueOf(event.instrument.ordinal()), Integer.valueOf(event.getVanillaNoteId()) });
/*     */     
/* 223 */     TileSensor.noteBlockEvents.put((WorldServer)event.world, list);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void fillBucket(FillBucketEvent event) {
/* 228 */     if (event.target.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) {
/* 229 */       if ((event.world.func_147439_a(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) == ConfigBlocks.blockFluidPure) && (event.world.func_72805_g(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) == 0))
/*     */       {
/* 231 */         event.world.func_147468_f(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d);
/* 232 */         event.result = new ItemStack(ConfigItems.itemBucketPure);
/* 233 */         event.setResult(Event.Result.ALLOW);
/* 234 */         return;
/*     */       }
/* 236 */       if ((event.world.func_147439_a(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) == ConfigBlocks.blockFluidDeath) && (event.world.func_72805_g(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) == 3))
/*     */       {
/* 238 */         event.world.func_147468_f(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d);
/* 239 */         event.result = new ItemStack(ConfigItems.itemBucketDeath);
/* 240 */         event.setResult(Event.Result.ALLOW);
/* 241 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void placeBlockEvent(BlockEvent.PlaceEvent event) {
/* 248 */     if (isNearActiveBoss(event.world, event.player, event.x, event.y, event.z)) event.setCanceled(true);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void placeBlockEvent(BlockEvent.MultiPlaceEvent event) {
/* 253 */     if (isNearActiveBoss(event.world, event.player, event.x, event.y, event.z)) event.setCanceled(true);
/*     */   }
/*     */   
/*     */   private boolean isNearActiveBoss(World world, EntityPlayer player, int x, int y, int z) {
/* 257 */     if ((world.field_73011_w.field_76574_g == Config.dimensionOuterId) && (player != null) && (!player.field_71075_bZ.field_75098_d)) {
/* 258 */       int xx = x >> 4;
/* 259 */       int zz = z >> 4;
/* 260 */       Cell c = MazeHandler.getFromHashMap(new thaumcraft.common.lib.world.dim.CellLoc(xx, zz));
/* 261 */       if ((c != null) && (c.feature >= 2) && (c.feature <= 5)) {
/* 262 */         ArrayList<Entity> list = thaumcraft.common.lib.utils.EntityUtils.getEntitiesInRange(world, x, y, z, null, thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss.class, 32.0D);
/* 263 */         if ((list != null) && (list.size() > 0)) return true;
/*     */       }
/*     */     }
/* 266 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/events/EventHandlerWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */