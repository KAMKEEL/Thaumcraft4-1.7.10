/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.Phase;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.WeakHashMap;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.lib.world.ChunkLoc;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ import thaumcraft.common.tiles.TileSensor;
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
/*     */ public class ServerTickEventsFML
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void serverWorldTick(TickEvent.WorldTickEvent event)
/*     */   {
/*  66 */     if (event.side == Side.CLIENT) return;
/*  67 */     if (event.phase != TickEvent.Phase.START)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  72 */       tickChunkRegeneration(event);
/*     */       
/*  74 */       tickBlockSwap(event.world);
/*     */       
/*  76 */       if (TileSensor.noteBlockEvents.get(event.world) != null)
/*  77 */         ((ArrayList)TileSensor.noteBlockEvents.get(event.world)).clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public void tickChunkRegeneration(TickEvent.WorldTickEvent event) {
/*  82 */     int dim = event.world.field_73011_w.field_76574_g;
/*     */     
/*     */ 
/*     */ 
/*  86 */     int count = 0;
/*  87 */     ArrayList<ChunkLoc> chunks = (ArrayList)chunksToGenerate.get(Integer.valueOf(dim));
/*  88 */     if ((chunks != null) && (chunks.size() > 0)) {
/*  89 */       for (int a = 0; a < 10; a++) {
/*  90 */         chunks = (ArrayList)chunksToGenerate.get(Integer.valueOf(dim));
/*  91 */         if ((chunks == null) || (chunks.size() <= 0)) break;
/*  92 */         count++;
/*  93 */         ChunkLoc loc = (ChunkLoc)chunks.get(0);
/*  94 */         long worldSeed = event.world.func_72905_C();
/*  95 */         Random fmlRandom = new Random(worldSeed);
/*  96 */         long xSeed = fmlRandom.nextLong() >> 3;
/*  97 */         long zSeed = fmlRandom.nextLong() >> 3;
/*  98 */         fmlRandom.setSeed(xSeed * loc.chunkXPos + zSeed * loc.chunkZPos ^ worldSeed);
/*  99 */         Thaumcraft.instance.worldGen.worldGeneration(fmlRandom, loc.chunkXPos, loc.chunkZPos, event.world, false);
/* 100 */         chunks.remove(0);
/* 101 */         chunksToGenerate.put(Integer.valueOf(dim), chunks);
/*     */       }
/*     */     }
/* 104 */     if (count > 0) {
/* 105 */       FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[Thaumcraft] Regenerated " + count + " chunks. " + Math.max(0, chunks.size()) + " chunks left");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void tickBlockSwap(World world)
/*     */   {
/* 113 */     int dim = world.field_73011_w.field_76574_g;
/* 114 */     LinkedBlockingQueue<VirtualSwapper> queue = (LinkedBlockingQueue)swapList.get(Integer.valueOf(dim));
/* 115 */     if (queue != null) {
/* 116 */       boolean didSomething = false;
/* 117 */       while (!didSomething) {
/* 118 */         VirtualSwapper vs = (VirtualSwapper)queue.poll();
/* 119 */         if (vs != null) {
/* 120 */           Block bi = world.func_147439_a(vs.x, vs.y, vs.z);
/* 121 */           int md = world.func_72805_g(vs.x, vs.y, vs.z);
/*     */           
/* 123 */           ItemWandCasting wand = null;
/* 124 */           ItemFocusBasic focus = null;
/* 125 */           ItemStack focusStack = null;
/* 126 */           if ((vs.player.field_71071_by.func_70301_a(vs.wand) != null) && ((vs.player.field_71071_by.func_70301_a(vs.wand).func_77973_b() instanceof ItemWandCasting)))
/*     */           {
/* 128 */             wand = (ItemWandCasting)vs.player.field_71071_by.func_70301_a(vs.wand).func_77973_b();
/* 129 */             focusStack = wand.getFocusItem(vs.player.field_71071_by.func_70301_a(vs.wand));
/* 130 */             focus = wand.getFocus(vs.player.field_71071_by.func_70301_a(vs.wand));
/*     */           }
/* 132 */           if ((world.func_72962_a(vs.player, vs.x, vs.y, vs.z)) && (!vs.target.func_77969_a(new ItemStack(bi, 1, md))) && (wand != null) && (focus != null) && (!ForgeEventFactory.onPlayerInteract(vs.player, PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK, vs.x, vs.y, vs.z, 1, world).isCanceled()) && (wand.consumeAllVis(vs.player.field_71071_by.func_70301_a(vs.wand), vs.player, focus.getVisCost(focusStack), false, false)))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/* 137 */             int slot = InventoryUtils.isPlayerCarrying(vs.player, vs.target);
/* 138 */             if (vs.player.field_71075_bZ.field_75098_d) slot = 1;
/* 139 */             if ((vs.bSource == bi) && (vs.mSource == md) && (slot >= 0)) {
/* 140 */               didSomething = true;
/* 141 */               if (!vs.player.field_71075_bZ.field_75098_d) {
/* 142 */                 int fortune = wand.getFocusTreasure(vs.player.field_71071_by.func_70301_a(vs.wand));
/* 143 */                 boolean silk = wand.getFocus(vs.player.field_71071_by.func_70301_a(vs.wand)).isUpgradedWith(wand.getFocusItem(vs.player.field_71071_by.func_70301_a(vs.wand)), FocusUpgradeType.silktouch);
/*     */                 
/* 145 */                 vs.player.field_71071_by.func_70298_a(slot, 1);
/*     */                 
/* 147 */                 ArrayList<ItemStack> ret = new ArrayList();
/* 148 */                 if ((silk) && (bi.canSilkHarvest(world, vs.player, vs.x, vs.y, vs.z, md)))
/*     */                 {
/* 150 */                   ItemStack itemstack = BlockUtils.createStackedBlock(bi, md);
/* 151 */                   if (itemstack != null)
/*     */                   {
/* 153 */                     ret.add(itemstack);
/*     */                   }
/*     */                 } else {
/* 156 */                   ret = bi.getDrops(world, vs.x, vs.y, vs.z, md, fortune);
/*     */                 }
/*     */                 
/* 159 */                 if (ret.size() > 0) { for (ItemStack is : ret) {
/* 160 */                     if (!vs.player.field_71071_by.func_70441_a(is)) {
/* 161 */                       world.func_72838_d(new EntityItem(world, vs.x + 0.5D, vs.y + 0.5D, vs.z + 0.5D, is));
/*     */                     }
/*     */                   }
/*     */                 }
/* 165 */                 wand.consumeAllVis(vs.player.field_71071_by.func_70301_a(vs.wand), vs.player, focus.getVisCost(focusStack), true, false);
/*     */               }
/*     */               
/* 168 */               world.func_147465_d(vs.x, vs.y, vs.z, Block.func_149634_a(vs.target.func_77973_b()), vs.target.func_77960_j(), 3);
/* 169 */               Block.func_149634_a(vs.target.func_77973_b()).func_149689_a(world, vs.x, vs.y, vs.z, vs.player, vs.target);
/*     */               
/* 171 */               PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(vs.x, vs.y, vs.z, 12632319), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, vs.x, vs.y, vs.z, 32.0D));
/*     */               
/*     */ 
/* 174 */               world.func_72926_e(2001, vs.x, vs.y, vs.z, Block.func_149682_b(vs.bSource) + (vs.mSource << 12));
/*     */               
/*     */ 
/* 177 */               if (vs.lifespan > 0) {
/* 178 */                 for (int xx = -1; xx <= 1; xx++) {
/* 179 */                   for (int yy = -1; yy <= 1; yy++) {
/* 180 */                     for (int zz = -1; zz <= 1; zz++) {
/* 181 */                       if (((xx != 0) || (yy != 0) || (zz != 0)) && (world.func_147439_a(vs.x + xx, vs.y + yy, vs.z + zz) == vs.bSource) && (world.func_72805_g(vs.x + xx, vs.y + yy, vs.z + zz) == vs.mSource) && (BlockUtils.isBlockExposed(world, vs.x + xx, vs.y + yy, vs.z + zz)))
/*     */                       {
/*     */ 
/*     */ 
/* 185 */                         queue.offer(new VirtualSwapper(vs.x + xx, vs.y + yy, vs.z + zz, vs.bSource, vs.mSource, vs.target, vs.lifespan - 1, vs.player, vs.wand));
/*     */                       }
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/* 195 */           didSomething = true;
/*     */         }
/*     */       }
/* 198 */       swapList.put(Integer.valueOf(dim), queue);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void addSwapper(World world, int x, int y, int z, Block bs, int ms, ItemStack target, int life, EntityPlayer player, int wand)
/*     */   {
/* 204 */     int dim = world.field_73011_w.field_76574_g;
/* 205 */     if ((bs == Blocks.field_150350_a) || (bs.func_149712_f(world, x, y, z) < 0.0F) || (target.func_77969_a(new ItemStack(bs, 1, ms)))) {
/* 206 */       return;
/*     */     }
/* 208 */     LinkedBlockingQueue<VirtualSwapper> queue = (LinkedBlockingQueue)swapList.get(Integer.valueOf(dim));
/* 209 */     if (queue == null) {
/* 210 */       swapList.put(Integer.valueOf(dim), new LinkedBlockingQueue());
/* 211 */       queue = (LinkedBlockingQueue)swapList.get(Integer.valueOf(dim));
/*     */     }
/* 213 */     queue.offer(new VirtualSwapper(x, y, z, bs, ms, target, life, player, wand));
/* 214 */     world.func_72956_a(player, "thaumcraft:wand", 0.25F, 1.0F);
/* 215 */     swapList.put(Integer.valueOf(dim), queue);
/*     */   }
/*     */   
/*     */ 
/* 219 */   public static Map<Integer, LinkedBlockingQueue<VirtualSwapper>> swapList = new HashMap();
/* 220 */   public static HashMap<Integer, ArrayList<ChunkLoc>> chunksToGenerate = new HashMap();
/*     */   
/*     */   public static class VirtualSwapper {
/* 223 */     int lifespan = 0;
/* 224 */     int x = 0;
/* 225 */     int y = 0;
/* 226 */     int z = 0;
/*     */     Block bSource;
/* 228 */     int mSource = 0;
/*     */     ItemStack target;
/* 230 */     int wand = 0;
/* 231 */     EntityPlayer player = null;
/*     */     
/* 233 */     VirtualSwapper(int x, int y, int z, Block bs, int ms, ItemStack t, int life, EntityPlayer p, int wand) { this.x = x;
/* 234 */       this.y = y;
/* 235 */       this.z = z;
/* 236 */       this.bSource = bs;
/* 237 */       this.mSource = ms;
/* 238 */       this.target = t;
/* 239 */       this.lifespan = life;
/* 240 */       this.player = p;
/* 241 */       this.wand = wand;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class RestorableWardedBlock {
/* 246 */     int x = 0;
/* 247 */     int y = 0;
/* 248 */     int z = 0;
/*     */     Block bi;
/* 250 */     int md = 0;
/* 251 */     NBTTagCompound nbt = null;
/*     */     
/*     */     RestorableWardedBlock(World world, int x, int y, int z) {
/* 254 */       this.x = x;
/* 255 */       this.y = y;
/* 256 */       this.z = z;
/* 257 */       this.bi = world.func_147439_a(x, y, z);
/* 258 */       this.md = world.func_72805_g(x, y, z);
/* 259 */       TileEntity te = world.func_147438_o(x, y, z);
/* 260 */       if (te != null) {
/* 261 */         this.nbt = new NBTTagCompound();
/* 262 */         te.func_145841_b(this.nbt);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/events/ServerTickEventsFML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */