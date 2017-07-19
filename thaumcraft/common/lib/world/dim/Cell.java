/*    */ package thaumcraft.common.lib.world.dim;
/*    */ 
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class Cell { public boolean north;
/*  6 */   public boolean south; public boolean east; public boolean west; public boolean above; public boolean below = false;
/*  7 */   public byte feature = 0;
/*    */   
/*    */   public Cell() {}
/*    */   
/*    */   public Cell(short data) {
/* 12 */     unpack(data);
/*    */   }
/*    */   
/*    */   private void unpack(short data) {
/* 16 */     this.north = Utils.getBit(data, 0);
/* 17 */     this.south = Utils.getBit(data, 1);
/* 18 */     this.east = Utils.getBit(data, 2);
/* 19 */     this.west = Utils.getBit(data, 3);
/* 20 */     this.above = Utils.getBit(data, 4);
/* 21 */     this.below = Utils.getBit(data, 5);
/* 22 */     this.feature = ((byte)(data >> 8));
/*    */   }
/*    */   
/*    */   public short pack() {
/* 26 */     int out = 0;
/* 27 */     if (this.north) out = Utils.setBit(out, 0);
/* 28 */     if (this.south) out = Utils.setBit(out, 1);
/* 29 */     if (this.east) out = Utils.setBit(out, 2);
/* 30 */     if (this.west) out = Utils.setBit(out, 3);
/* 31 */     if (this.above) out = Utils.setBit(out, 4);
/* 32 */     if (this.below) out = Utils.setBit(out, 5);
/* 33 */     out |= this.feature << 8;
/* 34 */     return (short)out;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/Cell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */