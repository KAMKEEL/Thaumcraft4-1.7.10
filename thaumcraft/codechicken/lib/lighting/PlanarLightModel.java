/*    */ package thaumcraft.codechicken.lib.lighting;
/*    */ 
/*    */ import thaumcraft.codechicken.lib.colour.ColourRGBA;
/*    */ import thaumcraft.codechicken.lib.render.CCRenderPipeline;
/*    */ import thaumcraft.codechicken.lib.render.CCRenderState;
/*    */ import thaumcraft.codechicken.lib.render.CCRenderState.IVertexOperation;
/*    */ 
/*    */ public class PlanarLightModel
/*    */   implements CCRenderState.IVertexOperation
/*    */ {
/* 11 */   public static PlanarLightModel standardLightModel = LightModel.standardLightModel.reducePlanar();
/*    */   public int[] colours;
/*    */   
/*    */   public PlanarLightModel(int[] colours)
/*    */   {
/* 16 */     this.colours = colours;
/*    */   }
/*    */   
/*    */   public boolean load()
/*    */   {
/* 21 */     CCRenderState.pipeline.addDependency(CCRenderState.sideAttrib);
/* 22 */     CCRenderState.pipeline.addDependency(CCRenderState.colourAttrib);
/* 23 */     return true;
/*    */   }
/*    */   
/*    */   public void operate()
/*    */   {
/* 28 */     CCRenderState.setColour(ColourRGBA.multiply(CCRenderState.colour, this.colours[CCRenderState.side]));
/*    */   }
/*    */   
/*    */   public int operationID()
/*    */   {
/* 33 */     return LightModel.operationIndex;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/lighting/PlanarLightModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */