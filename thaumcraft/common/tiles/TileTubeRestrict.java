/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ 
/*    */ 
/*    */ public class TileTubeRestrict
/*    */   extends TileTube
/*    */ {
/*    */   void calculateSuction(Aspect filter, boolean restrict, boolean dir)
/*    */   {
/* 11 */     super.calculateSuction(filter, true, dir);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileTubeRestrict.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */