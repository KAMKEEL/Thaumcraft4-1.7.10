/*    */ package thaumcraft.codechicken.lib.render.uv;
/*    */ 
/*    */ import thaumcraft.codechicken.lib.render.CCRenderState;
/*    */ import thaumcraft.codechicken.lib.render.CCRenderState.IVertexOperation;
/*    */ import thaumcraft.codechicken.lib.render.Vertex5;
/*    */ import thaumcraft.codechicken.lib.vec.ITransformation;
/*    */ 
/*    */ public abstract class UVTransformation
/*    */   extends ITransformation<UV, UVTransformation> implements CCRenderState.IVertexOperation
/*    */ {
/* 11 */   public static final int operationIndex = ;
/*    */   
/*    */   public UVTransformation at(UV point) {
/* 14 */     return new UVTransformationList(new UVTransformation[] { new UVTranslation(-point.u, -point.v), this, new UVTranslation(point.u, point.v) });
/*    */   }
/*    */   
/*    */   public UVTransformationList with(UVTransformation t) {
/* 18 */     return new UVTransformationList(new UVTransformation[] { this, t });
/*    */   }
/*    */   
/*    */   public boolean load()
/*    */   {
/* 23 */     return !isRedundant();
/*    */   }
/*    */   
/*    */   public void operate()
/*    */   {
/* 28 */     apply(CCRenderState.vert.uv);
/*    */   }
/*    */   
/*    */   public int operationID()
/*    */   {
/* 33 */     return operationIndex;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/render/uv/UVTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */