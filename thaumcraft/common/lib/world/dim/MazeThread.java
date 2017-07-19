/*    */ package thaumcraft.common.lib.world.dim;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class MazeThread implements Runnable
/*    */ {
/*    */   int x;
/*    */   int z;
/*    */   int w;
/* 10 */   int h = 0;
/* 11 */   long seed = 0L;
/*    */   
/*    */   public MazeThread(int x, int z, int w, int h, long seed) {
/* 14 */     this.x = x;
/* 15 */     this.z = z;
/* 16 */     this.w = w;
/* 17 */     this.h = h;
/* 18 */     this.seed = seed;
/*    */   }
/*    */   
/*    */ 
/*    */   public void run()
/*    */   {
/* 24 */     MazeHandler.putToHashMapRaw(new CellLoc(this.x, this.z), (short)0);
/* 25 */     MazeHandler.putToHashMapRaw(new CellLoc(this.x - this.w, this.z - this.h), (short)0);
/* 26 */     MazeHandler.putToHashMapRaw(new CellLoc(this.x + this.w, this.z + this.h), (short)0);
/* 27 */     MazeHandler.putToHashMapRaw(new CellLoc(this.x - this.w, this.z + this.h), (short)0);
/* 28 */     MazeHandler.putToHashMapRaw(new CellLoc(this.x + this.w, this.z - this.h), (short)0);
/*    */     
/* 30 */     MazeGenerator gen = new MazeGenerator(this.w, this.h, this.seed++);
/* 31 */     while (!gen.generate()) {
/* 32 */       gen = new MazeGenerator(this.w, this.h, this.seed++);
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 37 */     int col = this.x - (1 + this.w / 2);
/* 38 */     int row = this.z - (1 + this.h / 2);
/* 39 */     java.util.List<Integer> directions = Arrays.asList(new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(8) });
/*    */     
/* 41 */     for (int a = 0; a < this.w; a++) {
/* 42 */       for (int b = 0; b < this.h; b++) {
/* 43 */         if (gen.grid[b][a] > 0) {
/* 44 */           CellLoc loc = new CellLoc(a + col, b + row);
/* 45 */           MazeHandler.putToHashMapRaw(loc, (short)gen.grid[b][a]);
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 50 */     if (MazeHandler.getFromHashMapRaw(new CellLoc(this.x, this.z)) == 0) MazeHandler.removeFromHashMap(new CellLoc(this.x, this.z));
/* 51 */     if (MazeHandler.getFromHashMapRaw(new CellLoc(this.x - this.w, this.z - this.h)) == 0) MazeHandler.removeFromHashMap(new CellLoc(this.x - this.w, this.z - this.h));
/* 52 */     if (MazeHandler.getFromHashMapRaw(new CellLoc(this.x + this.w, this.z + this.h)) == 0) MazeHandler.removeFromHashMap(new CellLoc(this.x + this.w, this.z + this.h));
/* 53 */     if (MazeHandler.getFromHashMapRaw(new CellLoc(this.x - this.w, this.z + this.h)) == 0) MazeHandler.removeFromHashMap(new CellLoc(this.x - this.w, this.z + this.h));
/* 54 */     if (MazeHandler.getFromHashMapRaw(new CellLoc(this.x + this.w, this.z - this.h)) == 0) MazeHandler.removeFromHashMap(new CellLoc(this.x + this.w, this.z - this.h));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/MazeThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */