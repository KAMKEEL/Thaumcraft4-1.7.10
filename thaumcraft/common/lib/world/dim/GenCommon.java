/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.tiles.TileCrystal;
/*     */ import thaumcraft.common.tiles.TileEldritchCrabSpawner;
/*     */ 
/*     */ 
/*     */ public class GenCommon
/*     */ {
/*     */   static void placeBlock(World world, int i, int j, int k, int l, Cell cell)
/*     */   {
/*  21 */     placeBlock(world, i, j, k, l, ForgeDirection.UNKNOWN, cell);
/*     */   }
/*     */   
/*     */   static void placeBlock(World world, int x, int y, int z, int b, ForgeDirection dir, Cell cell) {
/*  25 */     Block block = null;
/*  26 */     int meta = 0;
/*  27 */     switch (b) {
/*  28 */     case 1:  if (world.func_147437_c(x, y, z)) block = Blocks.field_150357_h;
/*     */       break;
/*  30 */     case 15:  block = ConfigBlocks.blockEldritch;meta = 7;
/*  31 */       decoCommon.remove(new ChunkCoordinates(x, y, z));
/*  32 */       crabSpawner.remove(new ChunkCoordinates(x, y, z));
/*  33 */       decoUrn.remove(new ChunkCoordinates(x, y, z));
/*  34 */       break;
/*     */     case 16: 
/*  36 */       block = ConfigBlocks.blockEldritch;meta = 8;
/*  37 */       decoCommon.remove(new ChunkCoordinates(x, y, z));
/*  38 */       crabSpawner.remove(new ChunkCoordinates(x, y, z));
/*  39 */       decoUrn.remove(new ChunkCoordinates(x, y, z));
/*  40 */       break;
/*  41 */     case 99:  block = Blocks.field_150357_h; break;
/*     */     case 8: 
/*  43 */       block = ConfigBlocks.blockEldritchNothing; break;
/*     */     case 17: 
/*  45 */       block = ConfigBlocks.blockAiry;meta = 12; break;
/*     */     case 9: 
/*  47 */       block = Blocks.field_150350_a;
/*  48 */       decoCommon.remove(new ChunkCoordinates(x, y, z));
/*  49 */       crabSpawner.remove(new ChunkCoordinates(x, y, z));
/*  50 */       decoUrn.remove(new ChunkCoordinates(x, y, z));
/*  51 */       break;
/*     */     case 2: 
/*  53 */       if ((cell.feature != 7) || (world.field_73012_v.nextInt(3) != 0))
/*     */       {
/*     */ 
/*  56 */         if (world.func_147439_a(x, y, z) == ConfigBlocks.blockEldritchNothing) break label1241;
/*  57 */         if (world.field_73012_v.nextInt(25) == 0) {
/*  58 */           boolean crab = cell.feature == 7;
/*  59 */           if (((crab) && (cell.feature == 0)) || ((crab) && (cell.feature == 7))) {
/*  60 */             crabSpawner.add(new ChunkCoordinates(x, y, z));
/*     */           } else
/*  62 */             decoCommon.add(new ChunkCoordinates(x, y, z));
/*     */         }
/*  64 */         block = ConfigBlocks.blockCosmeticSolid;meta = 11;
/*     */       }
/*     */       
/*     */       break;
/*     */     case 21: 
/*  69 */       if (world.func_147439_a(x, y, z) != ConfigBlocks.blockEldritchNothing) {
/*  70 */         block = ConfigBlocks.blockCosmeticSolid;meta = 14;
/*  71 */         if (world.field_73012_v.nextInt(25) == 0) {
/*  72 */           block = ConfigBlocks.blockEldritch;meta = 4;
/*     */         }
/*  74 */         else if (world.field_73012_v.nextInt(25) == 0) {
/*  75 */           boolean crab = cell.feature == 7;
/*  76 */           if (((crab) && (cell.feature == 0)) || ((crab) && (cell.feature == 7)) || ((crab) && (cell.feature == 12)))
/*  77 */             crabSpawner.add(new ChunkCoordinates(x, y, z)); } }
/*  78 */       break;
/*     */     
/*     */ 
/*     */     case 18: 
/*  82 */       if (world.func_147439_a(x, y, z) != ConfigBlocks.blockEldritchNothing) {
/*  83 */         block = ConfigBlocks.blockCosmeticSolid;meta = 12;
/*     */       }
/*     */       break;
/*     */     case 19: 
/*  87 */       if (world.func_147439_a(x, y, z) != ConfigBlocks.blockEldritchNothing) {
/*  88 */         block = ConfigBlocks.blockCosmeticSolid;meta = 13;
/*     */       }
/*     */       break;
/*     */     case 20: 
/*  92 */       if (world.func_147439_a(x, y, z) != ConfigBlocks.blockEldritchNothing) {
/*  93 */         block = ConfigBlocks.blockEldritch;meta = 10;
/*     */       }
/*     */       
/*     */       break;
/*     */     case 10: 
/*  98 */       block = ConfigBlocks.blockStairsEldritch;
/*  99 */       switch (dir) {
/* 100 */       case NORTH:  meta = 3; break;
/* 101 */       case SOUTH:  meta = 2; break;
/* 102 */       case EAST:  meta = 0; break;
/* 103 */       case WEST:  meta = 1; }
/* 104 */       break;
/*     */     
/*     */ 
/*     */     case 11: 
/* 108 */       block = ConfigBlocks.blockStairsEldritch;
/* 109 */       switch (dir) {
/* 110 */       case NORTH:  meta = 7; break;
/* 111 */       case SOUTH:  meta = 6; break;
/* 112 */       case EAST:  meta = 4; break;
/* 113 */       case WEST:  meta = 5; }
/* 114 */       break;
/*     */     
/*     */     case 3: 
/* 117 */       if (world.field_73012_v.nextFloat() < 0.005D) decoUrn.add(new ChunkCoordinates(x, y, z));
/* 118 */       block = ConfigBlocks.blockStairsEldritch;
/* 119 */       switch (dir.ordinal()) {
/* 120 */       case 2: case 3:  meta = 1; break;
/* 121 */       case 4: case 5:  meta = 3;
/*     */       }
/* 123 */       break;
/* 124 */     case 4:  if (world.field_73012_v.nextFloat() < 0.005D) decoUrn.add(new ChunkCoordinates(x, y, z));
/* 125 */       block = ConfigBlocks.blockStairsEldritch;
/* 126 */       switch (dir.ordinal()) {
/* 127 */       case 2: case 3:  meta = 0; break;
/* 128 */       case 4: case 5:  meta = 2;
/*     */       }
/* 130 */       break;
/* 131 */     case 5:  block = ConfigBlocks.blockStairsEldritch;
/* 132 */       switch (dir.ordinal()) {
/* 133 */       case 2: case 3:  meta = 5; break;
/* 134 */       case 4: case 5:  meta = 7;
/*     */       }
/* 136 */       break;
/* 137 */     case 6:  block = ConfigBlocks.blockStairsEldritch;
/* 138 */       switch (dir.ordinal()) {
/* 139 */       case 2: case 3:  meta = 4; break;
/* 140 */       case 4: case 5:  meta = 6;
/*     */       }
/* 142 */       break;
/* 143 */     case 7:  block = ConfigBlocks.blockEldritch;meta = 4;
/*     */     }
/*     */     label1241:
/* 146 */     if (block != null) {
/* 147 */       world.func_147465_d(x, y, z, block, meta, (block == ConfigBlocks.blockEldritchNothing) || (block == Blocks.field_150357_h) || (block == Blocks.field_150350_a) ? 0 : 3);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void genObelisk(World world, int x, int y, int z)
/*     */   {
/* 156 */     world.func_147465_d(x, y, z, ConfigBlocks.blockEldritch, 1, 3);
/* 157 */     world.func_147465_d(x, y + 1, z, ConfigBlocks.blockEldritch, 2, 3);
/* 158 */     world.func_147465_d(x, y + 2, z, ConfigBlocks.blockEldritch, 2, 3);
/* 159 */     world.func_147465_d(x, y + 3, z, ConfigBlocks.blockEldritch, 2, 3);
/* 160 */     world.func_147465_d(x, y + 4, z, ConfigBlocks.blockEldritch, 2, 3);
/*     */   }
/*     */   
/* 163 */   static ArrayList<ChunkCoordinates> decoCommon = new ArrayList();
/* 164 */   static ArrayList<ChunkCoordinates> crabSpawner = new ArrayList();
/* 165 */   static ArrayList<ChunkCoordinates> decoUrn = new ArrayList();
/*     */   
/* 167 */   static void processDecorations(World world) { for (ChunkCoordinates cc : decoUrn) {
/* 168 */       if (world.func_147437_c(cc.field_71574_a, cc.field_71572_b + 1, cc.field_71573_c)) {
/* 169 */         world.func_147465_d(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockCosmeticSolid, 15, 3);
/* 170 */         float rr = world.field_73012_v.nextFloat();
/* 171 */         int meta = rr < 0.1F ? 1 : rr < 0.025F ? 2 : 0;
/* 172 */         world.func_147465_d(cc.field_71574_a, cc.field_71572_b + 1, cc.field_71573_c, ConfigBlocks.blockLootUrn, meta, 3);
/*     */       }
/*     */     }
/* 175 */     for (ChunkCoordinates cc : decoCommon) {
/* 176 */       int exp = BlockUtils.countExposedSides(world, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
/* 177 */       if ((exp > 0) && ((exp == 1) || (!isBedrockShowing(world, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c))) && (!BlockUtils.isBlockAdjacentToAtleast(world, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockEldritch, 32767, 1)))
/*     */       {
/* 179 */         int meta = world.field_73012_v.nextInt(8) != 0 ? 5 : world.field_73012_v.nextInt(3) != 0 ? 4 : 10;
/*     */         
/* 181 */         world.func_147465_d(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockEldritch, meta, 3);
/* 182 */         if ((meta == 4) && (world.field_73012_v.nextInt(12) == 0)) {
/* 183 */           for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/* 184 */             if (world.func_147437_c(cc.field_71574_a + dir.offsetX, cc.field_71572_b + dir.offsetY, cc.field_71573_c + dir.offsetZ)) {
/* 185 */               world.func_147465_d(cc.field_71574_a + dir.offsetX, cc.field_71572_b + dir.offsetY, cc.field_71573_c + dir.offsetZ, ConfigBlocks.blockCrystal, 7, 3);
/*     */               
/* 187 */               TileCrystal te = (TileCrystal)world.func_147438_o(cc.field_71574_a + dir.offsetX, cc.field_71572_b + dir.offsetY, cc.field_71573_c + dir.offsetZ);
/* 188 */               te.orientation = ((short)dir.ordinal());
/* 189 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 195 */     for (ChunkCoordinates cc : crabSpawner) {
/* 196 */       int exp = BlockUtils.countExposedSides(world, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
/* 197 */       if ((exp == 1) && (!BlockUtils.isBlockAdjacentToAtleast(world, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockEldritch, 32767, 1)))
/*     */       {
/* 199 */         world.func_147465_d(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockEldritch, 9, 3);
/* 200 */         TileEntity te = world.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
/* 201 */         if ((te != null) && ((te instanceof TileEldritchCrabSpawner))) {
/* 202 */           for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/* 203 */             if (world.func_147437_c(cc.field_71574_a + dir.offsetX, cc.field_71572_b + dir.offsetY, cc.field_71573_c + dir.offsetZ)) {
/* 204 */               ((TileEldritchCrabSpawner)te).setFacing((byte)dir.ordinal());
/* 205 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 212 */     decoCommon.clear();
/* 213 */     crabSpawner.clear();
/* 214 */     decoUrn.clear();
/*     */   }
/*     */   
/*     */   static boolean isBedrockShowing(World world, int x, int y, int z) {
/* 218 */     for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/* 219 */       if ((!world.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ).func_149662_c()) && ((world.func_147439_a(x + dir.getOpposite().offsetX, y + dir.getOpposite().offsetY, z + dir.getOpposite().offsetZ) == Blocks.field_150357_h) || (world.func_147439_a(x + dir.getOpposite().offsetX, y + dir.getOpposite().offsetY, z + dir.getOpposite().offsetZ) == ConfigBlocks.blockEldritchNothing)))
/*     */       {
/*     */ 
/* 222 */         return true; }
/*     */     }
/* 224 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   static final int BEDROCK = 1;
/*     */   static final int BEDROCK_REPL = 99;
/*     */   static final int STONE = 2;
/*     */   static final int VOID = 8;
/*     */   static final int AIR_REPL = 9;
/*     */   static final int STAIR_DIRECTIONAL = 10;
/*     */   static final int STAIR_DIRECTIONAL_INV = 11;
/*     */   static final int SLAB = 12;
/*     */   static final int DOOR_BLOCK = 15;
/*     */   static final int DOOR_LOCK = 16;
/*     */   static final int VOID_DOOR = 17;
/*     */   static final int ROCK = 18;
/*     */   static final int STONE_NOSPAWN = 19;
/*     */   static final int STONE_TRAPPED = 20;
/*     */   static final int CRUST = 21;
/*     */   static void generateConnections(World world, Random random, int cx, int cz, int y, Cell cell, int depth, boolean justthetip)
/*     */   {
/* 245 */     int x = cx * 16;
/* 246 */     int z = cz * 16;
/*     */     
/* 248 */     if (cell.north) {
/* 249 */       for (int d = 0; d <= depth; d++) {
/* 250 */         for (int w = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 251 */             w < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); w++) {
/* 252 */           for (int h = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 253 */               h < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); h++)
/* 254 */             if ((d != depth) || (!justthetip) || (PAT_CONNECT[h][w] != 8))
/* 255 */               placeBlock(world, x + 3 + w, y + 10 - h, z + d, PAT_CONNECT[h][w], ForgeDirection.NORTH, cell);
/*     */         }
/*     */       }
/*     */     }
/* 259 */     if (cell.south) {
/* 260 */       for (int d = 0; d <= depth; d++) {
/* 261 */         for (int w = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 262 */             w < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); w++) {
/* 263 */           for (int h = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 264 */               h < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); h++)
/* 265 */             if ((d != depth) || (!justthetip) || (PAT_CONNECT[h][w] != 8))
/* 266 */               placeBlock(world, x + 3 + w, y + 10 - h, z + 16 - d, PAT_CONNECT[h][w], ForgeDirection.SOUTH, cell);
/*     */         }
/*     */       }
/*     */     }
/* 270 */     if (cell.east) {
/* 271 */       for (int d = 0; d <= depth; d++) {
/* 272 */         for (int w = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 273 */             w < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); w++) {
/* 274 */           for (int h = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 275 */               h < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); h++)
/* 276 */             if ((d != depth) || (!justthetip) || (PAT_CONNECT[h][w] != 8))
/* 277 */               placeBlock(world, x + 16 - d, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], ForgeDirection.EAST, cell);
/*     */         }
/*     */       }
/*     */     }
/* 281 */     if (cell.west) {
/* 282 */       for (int d = 0; d <= depth; d++) {
/* 283 */         for (int w = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 284 */             w < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); w++) {
/* 285 */           for (int h = (d == depth - 1) && (justthetip) ? 1 : (d == depth) && (justthetip) ? 2 : 0; 
/* 286 */               h < ((d == depth - 1) && (justthetip) ? 10 : (d == depth) && (justthetip) ? 9 : 11); h++) {
/* 287 */             if ((d != depth) || (!justthetip) || (PAT_CONNECT[h][w] != 8))
/* 288 */               placeBlock(world, x + d, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], ForgeDirection.WEST, cell);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 295 */   static final int[][] PAT_CONNECT = { { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 }, { 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1 }, { 1, 8, 8, 2, 2, 2, 2, 2, 8, 8, 1 }, { 1, 8, 2, 5, 9, 9, 9, 6, 2, 8, 1 }, { 1, 8, 2, 9, 9, 9, 9, 9, 2, 8, 1 }, { 1, 8, 2, 9, 9, 9, 9, 9, 2, 8, 1 }, { 1, 8, 2, 9, 9, 9, 9, 9, 2, 8, 1 }, { 1, 8, 2, 3, 9, 9, 9, 4, 2, 8, 1 }, { 1, 8, 8, 2, 2, 2, 2, 2, 8, 8, 1 }, { 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1 }, { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 } };
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/GenCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */