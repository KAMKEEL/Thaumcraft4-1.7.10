/*    */ package thaumcraft.common.entities.golems;
/*    */ 
/*    */ public class Marker
/*    */ {
/*    */   public int x;
/*    */   public int y;
/*    */   public int z;
/*    */   public int dim;
/*    */   public byte side;
/*    */   public byte color;
/*    */   
/*    */   public Marker(int x, int y, int z, int dim, byte side, byte color) {
/* 13 */     this.x = x;
/* 14 */     this.y = y;
/* 15 */     this.z = z;
/* 16 */     this.dim = dim;
/* 17 */     this.side = side;
/* 18 */     this.color = color;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj)
/*    */   {
/* 23 */     if ((obj instanceof Marker)) {
/* 24 */       Marker marker = (Marker)obj;
/* 25 */       if ((this.x == marker.x) && (this.y == marker.y) && (this.z == marker.z) && (this.dim == marker.dim) && (this.side == marker.side) && (this.color == marker.color))
/*    */       {
/*    */ 
/* 28 */         return true;
/*    */       }
/*    */       
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     return false;
/*    */   }
/*    */   
/*    */   public boolean equalsFuzzy(Object obj) {
/* 38 */     if ((obj instanceof Marker)) {
/* 39 */       Marker marker = (Marker)obj;
/* 40 */       if ((this.x == marker.x) && (this.y == marker.y) && (this.z == marker.z) && (this.dim == marker.dim) && (this.side == marker.side) && ((this.color == marker.color) || (this.color == -1)))
/*    */       {
/*    */ 
/* 43 */         return true;
/*    */       }
/*    */       
/* 46 */       return false;
/*    */     }
/*    */     
/* 49 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/Marker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */