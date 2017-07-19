/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import thaumcraft.codechicken.lib.render.CCRenderPipeline;
/*    */ import thaumcraft.codechicken.lib.render.CCRenderState;
/*    */ import thaumcraft.codechicken.lib.render.CCRenderState.IVertexOperation;
/*    */ import thaumcraft.codechicken.lib.render.CCRenderState.VertexAttribute;
/*    */ 
/*    */ public abstract class Transformation extends ITransformation<Vector3, Transformation> implements CCRenderState.IVertexOperation
/*    */ {
/* 12 */   public static final int operationIndex = ;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract void applyN(Vector3 paramVector3);
/*    */   
/*    */ 
/*    */ 
/*    */   public abstract void apply(Matrix4 paramMatrix4);
/*    */   
/*    */ 
/*    */ 
/*    */   public Transformation at(Vector3 point)
/*    */   {
/* 27 */     return new TransformationList(new Transformation[] { new Translation(-point.x, -point.y, -point.z), this, point.translation() });
/*    */   }
/*    */   
/*    */   public TransformationList with(Transformation t) {
/* 31 */     return new TransformationList(new Transformation[] { this, t });
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public abstract void glApply();
/*    */   
/*    */   public boolean load()
/*    */   {
/* 39 */     CCRenderState.pipeline.addRequirement(CCRenderState.normalAttrib.operationID());
/* 40 */     return !isRedundant();
/*    */   }
/*    */   
/*    */   public void operate()
/*    */   {
/* 45 */     apply(CCRenderState.vert.vec);
/* 46 */     if (CCRenderState.normalAttrib.active) {
/* 47 */       applyN(CCRenderState.normal);
/*    */     }
/*    */   }
/*    */   
/*    */   public int operationID() {
/* 52 */     return operationIndex;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/Transformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */