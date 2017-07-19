/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileTubeOneway
/*    */   extends TileTube
/*    */ {
/*    */   void calculateSuction(Aspect filter, boolean restrict, boolean directional)
/*    */   {
/* 12 */     super.calculateSuction(filter, restrict, true);
/*    */   }
/*    */   
/*    */   void equalizeWithNeighbours(boolean directional)
/*    */   {
/* 17 */     super.equalizeWithNeighbours(true);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileTubeOneway.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */