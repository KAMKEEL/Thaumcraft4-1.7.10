/*    */ package thaumcraft.common.lib.world.dim;
/*    */ 
/*    */ public class CellLoc
/*    */   implements Comparable
/*    */ {
/*    */   public int x;
/*    */   public int z;
/*    */   
/*    */   public CellLoc() {}
/*    */   
/*    */   public CellLoc(int x, int z)
/*    */   {
/* 13 */     this.x = x;
/* 14 */     this.z = z;
/*    */   }
/*    */   
/*    */   public CellLoc(CellLoc c)
/*    */   {
/* 19 */     this.x = c.x;
/* 20 */     this.z = c.z;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o)
/*    */   {
/* 25 */     if (!(o instanceof CellLoc))
/*    */     {
/* 27 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 31 */     CellLoc chunkcoordinates = (CellLoc)o;
/* 32 */     return (this.x == chunkcoordinates.x) && (this.z == chunkcoordinates.z);
/*    */   }
/*    */   
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 38 */     return this.x + this.z << 8;
/*    */   }
/*    */   
/*    */   public int compareTo(CellLoc c)
/*    */   {
/* 43 */     return this.z == c.z ? this.x - c.x : this.z - c.z;
/*    */   }
/*    */   
/*    */   public void set(int x, int z)
/*    */   {
/* 48 */     this.x = x;
/* 49 */     this.z = z;
/*    */   }
/*    */   
/*    */   public float getDistanceSquared(int x, int z)
/*    */   {
/* 54 */     float f = this.x - x;
/* 55 */     float f2 = this.z - z;
/* 56 */     return f * f + f2 * f2;
/*    */   }
/*    */   
/*    */   public float getDistanceSquaredToChunkCoordinates(CellLoc c)
/*    */   {
/* 61 */     return getDistanceSquared(c.x, c.z);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 66 */     return "Pos{x=" + this.x + ", z=" + this.z + '}';
/*    */   }
/*    */   
/*    */   public int compareTo(Object o)
/*    */   {
/* 71 */     return compareTo((CellLoc)o);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/CellLoc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */