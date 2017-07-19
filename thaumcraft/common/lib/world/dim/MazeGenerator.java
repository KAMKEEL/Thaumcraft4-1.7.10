/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class MazeGenerator
/*     */ {
/*  13 */   int width = 0;
/*  14 */   int height = 0;
/*  15 */   long seed = 0L;
/*  16 */   Random rand = null;
/*     */   public int[][] grid;
/*     */   public static final int N = 1;
/*     */   public static final int S = 2;
/*     */   public static final int E = 4;
/*     */   public static final int W = 8;
/*     */   public static final int A = 16;
/*     */   public static final int B = 32;
/*     */   
/*     */   public static int getOPP(int in)
/*     */   {
/*  27 */     switch (in) {
/*  28 */     case 1:  return 2;
/*  29 */     case 2:  return 1;
/*  30 */     case 4:  return 8;
/*  31 */     case 8:  return 4;
/*     */     }
/*  33 */     return -99;
/*     */   }
/*     */   
/*     */   public static int getDX(int in) {
/*  37 */     switch (in) {
/*  38 */     case 1:  return 0;
/*  39 */     case 2:  return 0;
/*  40 */     case 4:  return 1;
/*  41 */     case 8:  return -1;
/*     */     }
/*  43 */     return -99;
/*     */   }
/*     */   
/*     */   public static int getDY(int in) {
/*  47 */     switch (in) {
/*  48 */     case 1:  return -1;
/*  49 */     case 2:  return 1;
/*  50 */     case 4:  return 0;
/*  51 */     case 8:  return 0;
/*     */     }
/*  53 */     return -99;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MazeGenerator(int width, int height, long seed)
/*     */   {
/*  65 */     this.width = width;
/*  66 */     this.height = height;
/*  67 */     this.seed = seed;
/*  68 */     this.rand = new Random(seed);
/*  69 */     this.grid = new int[height][width];
/*  70 */     for (int y = 0; y < height; y++) for (int x = 0; x < width; x++)
/*  71 */         this.grid[y][x] = 0;
/*     */   }
/*     */   
/*     */   private class Loc {
/*     */     int x;
/*     */     int y;
/*     */     
/*  78 */     public Loc(int x, int y) { this.x = x;
/*  79 */       this.y = y;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean generate()
/*     */   {
/*  86 */     int bx = 0;int by = 0;
/*  87 */     switch (this.rand.nextInt(4)) {
/*  88 */     case 0:  bx = 0;by = 0; break;
/*  89 */     case 1:  bx = this.width - 2;by = this.height - 2; break;
/*  90 */     case 2:  bx = this.width - 2;by = 0; break;
/*  91 */     case 3:  bx = 0;by = this.height - 2;
/*     */     }
/*  93 */     this.grid[by][bx] = 'Ȁ';
/*  94 */     this.grid[by][(bx + 1)] = '̀';
/*  95 */     this.grid[(by + 1)][bx] = 'Ѐ';
/*  96 */     this.grid[(by + 1)][(bx + 1)] = 'Ԁ';
/*     */     
/*     */ 
/*  99 */     int px = 1 + this.width / 2;
/* 100 */     int py = 1 + this.height / 2;
/* 101 */     this.grid[py][px] = 'Ā';
/*     */     
/*     */ 
/* 104 */     ArrayList<Loc> cells = new ArrayList();
/* 105 */     int l = (this.width + this.height) / 4;
/* 106 */     for (int z = 0; z < l; z++) {
/* 107 */       int w = 1 + this.rand.nextInt(3);
/* 108 */       if (w > 2) l--;
/* 109 */       int qq = this.rand.nextInt(this.width - w);
/* 110 */       int ww = this.rand.nextInt(this.height - w);
/* 111 */       for (int a = qq; a < qq + w; a++) {
/* 112 */         for (int b = ww; b < ww + w; b++) {
/* 113 */           if (this.grid[b][a] == 0) {
/* 114 */             this.grid[b][a] = -1;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 119 */     List<Integer> directions = java.util.Arrays.asList(new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(8) });
/* 120 */     Collections.shuffle(directions, this.rand);
/*     */     
/* 122 */     int xx = px + getDX(((Integer)directions.get(0)).intValue());
/* 123 */     int yy = py + getDY(((Integer)directions.get(0)).intValue());
/* 124 */     this.grid[py][px] |= ((Integer)directions.get(0)).intValue();
/* 125 */     if (this.grid[yy][xx] < 0) this.grid[yy][xx] = 0;
/* 126 */     this.grid[yy][xx] |= getOPP(((Integer)directions.get(0)).intValue());
/* 127 */     cells.add(new Loc(xx, yy));
/* 128 */     boolean success = false;
/* 129 */     while (!cells.isEmpty()) {
/* 130 */       int index = getNextIndex(cells.size());
/* 131 */       int x = ((Loc)cells.get(index)).x;
/* 132 */       int y = ((Loc)cells.get(index)).y;
/*     */       
/* 134 */       Collections.shuffle(directions, this.rand);
/* 135 */       boolean carved = false;
/*     */       
/* 137 */       for (Iterator i$ = directions.iterator(); i$.hasNext();) { int dir = ((Integer)i$.next()).intValue();
/* 138 */         int nx = x + getDX(dir);
/* 139 */         int ny = y + getDY(dir);
/* 140 */         if ((0 < nx) && (nx < this.width - 1) && (0 < ny) && (ny < this.height - 1)) {
/* 141 */           if (this.grid[ny][nx] == 0) {
/* 142 */             this.grid[y][x] |= dir;
/* 143 */             this.grid[ny][nx] |= getOPP(dir);
/* 144 */             cells.add(new Loc(nx, ny));
/* 145 */             carved = true;
/*     */           }
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
/* 165 */           if (carved) {
/* 166 */             success = true;
/* 167 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 172 */       if (!carved) { cells.remove(index);
/*     */       }
/*     */     }
/* 175 */     if (!success) { return false;
/*     */     }
/*     */     
/* 178 */     for (int aa = 0; aa < this.height; aa++) {
/* 179 */       for (int bb = 0; bb < this.width; bb++) {
/* 180 */         if (this.grid[aa][bb] < 0) { this.grid[aa][bb] = 0;
/*     */         }
/*     */       }
/*     */     }
/* 184 */     Collections.shuffle(directions, this.rand);
/* 185 */     for (Iterator i$ = directions.iterator(); i$.hasNext();) { int dir = ((Integer)i$.next()).intValue();
/* 186 */       int nx = px + getDX(dir);
/* 187 */       int ny = py + getDY(dir);
/* 188 */       if ((0 < nx) && (nx < this.width - 1) && (0 < ny) && (ny < this.height - 1) && (this.grid[ny][nx] > 0) && 
/* 189 */         (this.rand.nextBoolean())) {
/* 190 */         this.grid[ny][nx] |= getOPP(dir);
/* 191 */         this.grid[py][px] |= dir;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 197 */     Collections.shuffle(directions, this.rand);
/* 198 */     boolean connected = false;
/*     */     
/* 200 */     for (int ax = 0; ax < 2; ax++) { Iterator i$;
/* 201 */       for (int ay = 0; ay < 2; ay++)
/* 202 */         for (i$ = directions.iterator(); i$.hasNext();) { int dir = ((Integer)i$.next()).intValue();
/* 203 */           int nx = bx + ax + getDX(dir);
/* 204 */           int ny = by + ay + getDY(dir);
/* 205 */           if ((0 < nx) && (nx < this.width - 1) && (0 < ny) && (ny < this.height - 1) && (this.grid[ny][nx] > 0) && (new Cell((short)this.grid[ny][nx]).feature == 0)) {
/* 206 */             this.grid[ny][nx] |= getOPP(dir);
/* 207 */             this.grid[(by + ay)][(bx + ax)] |= dir;
/* 208 */             connected = true;
/*     */             break label1226;
/*     */           } } }
/*     */     label1226:
/* 212 */     if (!connected) {
/* 213 */       List<Integer> directions2 = java.util.Arrays.asList(new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(8) });
/* 214 */       Collections.shuffle(directions2, this.rand);
/* 215 */       success = false;
/*     */       
/* 217 */       for (int ax = 0; ax < 2; ax++) { Iterator i$;
/* 218 */         for (int ay = 0; ay < 2; ay++)
/* 219 */           for (i$ = directions2.iterator(); i$.hasNext();) { int dir2 = ((Integer)i$.next()).intValue();
/* 220 */             int qx = bx + ax + getDX(dir2);
/* 221 */             int qy = by + ay + getDY(dir2);
/* 222 */             if ((0 < qx) && (qx < this.width - 1) && (0 < qy) && (qy < this.height - 1) && (this.grid[qy][qx] == 0)) {
/* 223 */               cells.add(new Loc(qx, qy));
/* 224 */               while (!cells.isEmpty()) {
/* 225 */                 int index = getNextIndex(cells.size());
/* 226 */                 int x = ((Loc)cells.get(index)).x;
/* 227 */                 int y = ((Loc)cells.get(index)).y;
/* 228 */                 Collections.shuffle(directions, this.rand);
/* 229 */                 boolean carved = false;
/* 230 */                 for (Iterator i$ = directions.iterator(); i$.hasNext();) { int dir = ((Integer)i$.next()).intValue();
/* 231 */                   int nx = x + getDX(dir);
/* 232 */                   int ny = y + getDY(dir);
/* 233 */                   if ((0 < nx) && (nx < this.width - 1) && (0 < ny) && (ny < this.height - 1)) {
/* 234 */                     if (this.grid[ny][nx] == 0) {
/* 235 */                       this.grid[y][x] |= dir;
/* 236 */                       this.grid[y][x] |= 0x6300;
/* 237 */                       this.grid[ny][nx] |= getOPP(dir);
/* 238 */                       this.grid[ny][nx] |= 0x6300;
/* 239 */                       cells.add(new Loc(nx, ny));
/* 240 */                       carved = true;
/* 241 */                     } else if (new Cell((short)this.grid[ny][nx]).feature == 0) {
/* 242 */                       this.grid[y][x] |= dir;
/* 243 */                       this.grid[ny][nx] |= getOPP(dir);
/* 244 */                       this.grid[qy][qx] |= getOPP(dir2);
/* 245 */                       this.grid[(by + ay)][(bx + ax)] |= dir2;
/* 246 */                       success = true;
/*     */                       break label1829;
/*     */                     }
/* 249 */                     if (carved) break;
/*     */                   }
/*     */                 }
/* 252 */                 if (!carved) cells.remove(index);
/*     */               }
/*     */             } } }
/*     */       label1829:
/* 256 */       if (!success) { return false;
/*     */       }
/*     */     }
/*     */     
/* 260 */     for (int aa = 0; aa < this.height; aa++) {
/* 261 */       for (int bb = 0; bb < this.width; bb++) {
/* 262 */         Cell c = new Cell((short)this.grid[aa][bb]);
/* 263 */         if (c.feature == 99) {
/* 264 */           c.feature = 0;
/* 265 */           this.grid[aa][bb] = c.pack();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 270 */     ArrayList<CellLoc> deadEndsloc = new ArrayList();
/* 271 */     for (int aa = 0; aa < this.height; aa++) {
/* 272 */       for (int bb = 0; bb < this.width; bb++) {
/* 273 */         Cell c = new Cell((short)this.grid[aa][bb]);
/* 274 */         int exits = (c.north ? 1 : 0) + (c.south ? 1 : 0) + (c.east ? 1 : 0) + (c.west ? 1 : 0);
/* 275 */         if ((exits == 1) && (c.feature == 0)) {
/* 276 */           deadEndsloc.add(new CellLoc(aa, bb));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 281 */     if (deadEndsloc.size() == 0) {
/* 282 */       return false;
/*     */     }
/* 284 */     int r = this.rand.nextInt(deadEndsloc.size());
/*     */     
/* 286 */     CellLoc ll = (CellLoc)deadEndsloc.get(r);
/* 287 */     Cell c = new Cell((short)this.grid[ll.x][ll.z]);
/* 288 */     c.feature = 6;
/* 289 */     this.grid[ll.x][ll.z] = c.pack();
/* 290 */     deadEndsloc.remove(r);
/*     */     
/*     */ 
/*     */ 
/* 294 */     if (deadEndsloc.size() > 0) {
/* 295 */       int count = 0;
/* 296 */       while (count < deadEndsloc.size() / 2) {
/* 297 */         int r = this.rand.nextInt(deadEndsloc.size());
/* 298 */         CellLoc ll = (CellLoc)deadEndsloc.get(r);
/* 299 */         Cell c = new Cell((short)this.grid[ll.x][ll.z]);
/* 300 */         if (c.feature == 0) {
/* 301 */           c.feature = ((byte)(7 + this.rand.nextInt(3)));
/* 302 */           this.grid[ll.x][ll.z] = c.pack();
/* 303 */           deadEndsloc.remove(r);
/* 304 */           count++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 310 */     for (int aa = 0; aa < this.height; aa++) {
/* 311 */       for (int bb = 0; bb < this.width; bb++) {
/* 312 */         Cell c = new Cell((short)this.grid[aa][bb]);
/* 313 */         if ((c.feature == 0) && ((c.north) || (c.south) || (c.west) || (c.east)) && (this.rand.nextInt(25) == 0)) {
/* 314 */           switch (this.rand.nextInt(8)) {
/* 315 */           case 0:  c.feature = 8; break;
/* 316 */           case 1:  c.feature = 10; break;
/* 317 */           case 2: case 3:  c.feature = 11; break;
/* 318 */           case 4: case 5:  c.feature = 12; break;
/* 319 */           case 6:  c.feature = 13; break;
/* 320 */           case 7:  c.feature = 14;
/*     */           }
/* 322 */           this.grid[aa][bb] = c.pack();
/*     */         }
/*     */       }
/*     */     }
/* 326 */     return true;
/*     */   }
/*     */   
/*     */   private int getNextIndex(int ceil) {
/* 330 */     float r = this.rand.nextFloat();
/* 331 */     if (r <= 0.45F)
/* 332 */       return ceil - 1;
/* 333 */     if (r <= 0.9F) {
/* 334 */       return this.rand.nextInt(ceil);
/*     */     }
/* 336 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void print()
/*     */   {
/* 342 */     HashMap<Integer, String[]> tiles = new HashMap();
/* 343 */     tiles.put(Integer.valueOf(0), new String[] { "...", "...", "..." });
/* 344 */     tiles.put(Integer.valueOf(1), new String[] { "# #", "# #", "###" });
/* 345 */     tiles.put(Integer.valueOf(2), new String[] { "###", "# #", "# #" });
/* 346 */     tiles.put(Integer.valueOf(4), new String[] { "###", "#  ", "###" });
/* 347 */     tiles.put(Integer.valueOf(8), new String[] { "###", "  #", "###" });
/* 348 */     tiles.put(Integer.valueOf(3), new String[] { "# #", "# #", "# #" });
/* 349 */     tiles.put(Integer.valueOf(9), new String[] { "# #", "  #", "###" });
/* 350 */     tiles.put(Integer.valueOf(5), new String[] { "# #", "#  ", "###" });
/* 351 */     tiles.put(Integer.valueOf(10), new String[] { "###", "  #", "# #" });
/* 352 */     tiles.put(Integer.valueOf(6), new String[] { "###", "#  ", "# #" });
/* 353 */     tiles.put(Integer.valueOf(12), new String[] { "###", "   ", "###" });
/* 354 */     tiles.put(Integer.valueOf(7), new String[] { "# #", "#  ", "# #" });
/* 355 */     tiles.put(Integer.valueOf(11), new String[] { "# #", "  #", "# #" });
/* 356 */     tiles.put(Integer.valueOf(13), new String[] { "# #", "   ", "###" });
/* 357 */     tiles.put(Integer.valueOf(14), new String[] { "###", "   ", "# #" });
/* 358 */     tiles.put(Integer.valueOf(15), new String[] { "# #", "   ", "# #" });
/* 359 */     tiles.put(Integer.valueOf(19), new String[] { "#-#", " A ", "#-#" });
/* 360 */     tiles.put(Integer.valueOf(28), new String[] { "# #", "|A|", "# #" });
/* 361 */     tiles.put(Integer.valueOf(35), new String[] { "#-#", " B ", "#-#" });
/* 362 */     tiles.put(Integer.valueOf(44), new String[] { "# #", "|B|", "# #" });
/*     */     
/* 364 */     for (int y = 0; y < this.height; y++) {
/* 365 */       for (int q = 0; q < 3; q++) {
/* 366 */         for (int x = 0; x < this.width; x++) {
/* 367 */           Cell c = new Cell((short)this.grid[y][x]);
/* 368 */           if (tiles.containsKey(Integer.valueOf(this.grid[y][x]))) {
/* 369 */             System.out.print(((String[])tiles.get(Integer.valueOf(this.grid[y][x])))[q]);
/*     */           }
/* 371 */           else if (c.feature == 1) {
/* 372 */             if (((q == 0) && (c.north)) || ((q == 2) && (c.south))) { System.out.print("P|P");
/* 373 */             } else if ((q == 1) && (c.west) && (!c.east)) { System.out.print("-PP");
/* 374 */             } else if ((q == 1) && (c.east) && (!c.west)) { System.out.print("PP-");
/* 375 */             } else if ((q == 1) && (c.east) && (c.west)) System.out.print("-P-"); else {
/* 376 */               System.out.print("PPP");
/*     */             }
/*     */           }
/* 379 */           else if (c.feature > 1) {
/* 380 */             System.out.print("FFF");
/*     */           }
/*     */         }
/* 383 */         System.out.println();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/MazeGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */