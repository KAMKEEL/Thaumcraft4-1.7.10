/*   */ package thaumcraft.codechicken.lib.vec;
/*   */ 
/*   */ public class AxisCycle
/*   */ {
/* 5 */   public static Transformation[] cycles = { new RedundantTransformation(), new VariableTransformation(new Matrix4(0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*   */   {
/*   */     public void apply(Vector3 vec)
/*   */     {
/* 9 */       double d0 = vec.x;double d1 = vec.y;double d2 = vec.z;
/* : */       vec.x = d2;vec.y = d0;vec.z = d1; }
/*   */     
/* < */     public Transformation inverse() { return AxisCycle.cycles[2]; }
/* 5 */   }, new VariableTransformation(new Matrix4(0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))
/*   */   {
/*   */ 
/*   */ 
/*   */ 
/*   */ 
/*   */     public void apply(Vector3 vec)
/*   */     {
/*   */ 
/*   */ 
/*   */ 
/* @ */       double d0 = vec.x;double d1 = vec.y;double d2 = vec.z;
/* A */       vec.x = d1;vec.y = d2;vec.z = d0; }
/*   */     
/* C */     public Transformation inverse() { return AxisCycle.cycles[1]; }
/* 5 */   } };
/*   */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/AxisCycle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */