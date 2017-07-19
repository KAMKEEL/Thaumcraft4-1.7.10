/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.WorldCoordinates;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IAspectSource;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;
/*     */ import thaumcraft.common.tiles.TileMirrorEssentia;
/*     */ 
/*     */ public class EssentiaHandler
/*     */ {
/*     */   static final int DELAY = 5000;
/*  22 */   private static HashMap<WorldCoordinates, ArrayList<WorldCoordinates>> sources = new HashMap();
/*  23 */   private static HashMap<WorldCoordinates, Long> sourcesDelay = new HashMap();
/*     */   
/*     */   public static boolean drainEssentia(TileEntity tile, Aspect aspect, ForgeDirection direction, int range) {
/*  26 */     return drainEssentia(tile, aspect, direction, range, false);
/*     */   }
/*     */   
/*     */   public static boolean drainEssentia(TileEntity tile, Aspect aspect, ForgeDirection direction, int range, boolean ignoreMirror) {
/*  30 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, tile.func_145831_w().field_73011_w.field_76574_g);
/*  31 */     if (!sources.containsKey(tileLoc)) {
/*  32 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/*  33 */       if (sources.containsKey(tileLoc))
/*  34 */         return drainEssentia(tile, aspect, direction, range);
/*  35 */       return false;
/*     */     }
/*  37 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/*  38 */     for (WorldCoordinates source : es) {
/*  39 */       TileEntity sourceTile = tile.func_145831_w().func_147438_o(source.x, source.y, source.z);
/*  40 */       if ((sourceTile == null) || (!(sourceTile instanceof IAspectSource))) break;
/*  41 */       if ((!ignoreMirror) || (!(sourceTile instanceof TileMirrorEssentia)))
/*     */       {
/*  43 */         IAspectSource as = (IAspectSource)sourceTile;
/*  44 */         if (as.takeFromContainer(aspect, 1))
/*     */         {
/*  46 */           PacketHandler.INSTANCE.sendToAllAround(new PacketFXEssentiaSource(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, (byte)(tile.field_145851_c - source.x), (byte)(tile.field_145848_d - source.y), (byte)(tile.field_145849_e - source.z), aspect.getColor()), new NetworkRegistry.TargetPoint(tile.func_145831_w().field_73011_w.field_76574_g, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 32.0D));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  52 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  58 */     sources.remove(tileLoc);
/*  59 */     sourcesDelay.put(tileLoc, Long.valueOf(System.currentTimeMillis() + 5000L));
/*     */     
/*  61 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean findEssentia(TileEntity tile, Aspect aspect, ForgeDirection direction, int range) {
/*  65 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, tile.func_145831_w().field_73011_w.field_76574_g);
/*  66 */     if (!sources.containsKey(tileLoc)) {
/*  67 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/*  68 */       if (sources.containsKey(tileLoc))
/*  69 */         return findEssentia(tile, aspect, direction, range);
/*  70 */       return false;
/*     */     }
/*  72 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/*  73 */     for (WorldCoordinates source : es) {
/*  74 */       TileEntity sourceTile = tile.func_145831_w().func_147438_o(source.x, source.y, source.z);
/*  75 */       if ((sourceTile == null) || (!(sourceTile instanceof IAspectSource)))
/*     */         break;
/*  77 */       IAspectSource as = (IAspectSource)sourceTile;
/*  78 */       if (as.doesContainerContainAmount(aspect, 1)) {
/*  79 */         return true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  85 */     sources.remove(tileLoc);
/*  86 */     sourcesDelay.put(tileLoc, Long.valueOf(System.currentTimeMillis() + 5000L));
/*     */     
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   private static void getSources(World world, WorldCoordinates tileLoc, ForgeDirection direction, int range)
/*     */   {
/*  93 */     if (sourcesDelay.containsKey(tileLoc)) {
/*  94 */       long d = ((Long)sourcesDelay.get(tileLoc)).longValue();
/*  95 */       if (d <= System.currentTimeMillis()) {
/*  96 */         sourcesDelay.remove(tileLoc);
/*     */       } else {
/*  98 */         return;
/*     */       }
/*     */     }
/*     */     
/* 102 */     TileEntity sourceTile = world.func_147438_o(tileLoc.x, tileLoc.y, tileLoc.z);
/*     */     
/* 104 */     ArrayList<WorldCoordinates> sourceList = new ArrayList();
/* 105 */     int start = 0;
/* 106 */     if (direction == ForgeDirection.UNKNOWN) {
/* 107 */       start = -range;
/* 108 */       direction = ForgeDirection.UP;
/*     */     }
/*     */     
/*     */ 
/* 112 */     int xx = 0;
/* 113 */     int yy = 0;
/* 114 */     int zz = 0;
/* 115 */     for (int aa = -range; aa <= range; aa++) {
/* 116 */       for (int bb = -range; bb <= range; bb++) {
/* 117 */         for (int cc = start; cc < range; cc++)
/*     */         {
/* 119 */           if ((aa != 0) || (bb != 0) || (cc != 0))
/*     */           {
/* 121 */             xx = tileLoc.x;
/* 122 */             yy = tileLoc.y;
/* 123 */             zz = tileLoc.z;
/*     */             
/* 125 */             if (direction.offsetY != 0) {
/* 126 */               xx += aa;
/* 127 */               yy += cc * direction.offsetY;
/* 128 */               zz += bb;
/*     */             }
/* 130 */             else if (direction.offsetX == 0) {
/* 131 */               xx += aa;
/* 132 */               yy += bb;
/* 133 */               zz += cc * direction.offsetZ;
/*     */             } else {
/* 135 */               xx += cc * direction.offsetX;
/* 136 */               yy += aa;
/* 137 */               zz += bb;
/*     */             }
/*     */             
/* 140 */             TileEntity te = world.func_147438_o(xx, yy, zz);
/* 141 */             if ((te != null) && ((te instanceof IAspectSource)))
/*     */             {
/* 143 */               if ((!(sourceTile instanceof TileMirrorEssentia)) || (!(te instanceof TileMirrorEssentia)) || 
/* 144 */                 (sourceTile.field_145851_c != ((TileMirrorEssentia)te).linkX) || (sourceTile.field_145848_d != ((TileMirrorEssentia)te).linkY) || (sourceTile.field_145849_e != ((TileMirrorEssentia)te).linkZ) || (sourceTile.func_145831_w().field_73011_w.field_76574_g != ((TileMirrorEssentia)te).linkDim))
/*     */               {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 151 */                 sourceList.add(new WorldCoordinates(xx, yy, zz, world.field_73011_w.field_76574_g)); } }
/*     */           } }
/*     */       }
/*     */     }
/* 155 */     if (sourceList.size() > 0) {
/* 156 */       sources.put(tileLoc, sourceList);
/*     */     } else {
/* 158 */       sourcesDelay.put(tileLoc, Long.valueOf(System.currentTimeMillis() + 5000L));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 164 */   public static void refreshSources(TileEntity tile) { sources.remove(new WorldCoordinates(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, tile.func_145831_w().field_73011_w.field_76574_g)); }
/*     */   
/*     */   public static class EssentiaSourceFX {
/*     */     public ChunkCoordinates start;
/*     */     public ChunkCoordinates end;
/*     */     public int ticks;
/*     */     public int color;
/*     */     
/*     */     public EssentiaSourceFX(ChunkCoordinates start, ChunkCoordinates end, int ticks, int color) {
/* 173 */       this.start = start;
/* 174 */       this.end = end;
/* 175 */       this.ticks = ticks;
/* 176 */       this.color = color;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 184 */   public static HashMap<String, EssentiaSourceFX> sourceFX = new HashMap();
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/events/EssentiaHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */