/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ public class SwapYZ extends VariableTransformation
/*    */ {
/*    */   public SwapYZ()
/*    */   {
/*  7 */     super(new Matrix4(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void apply(Vector3 vec)
/*    */   {
/* 17 */     double vz = vec.z;
/* 18 */     vec.z = vec.y;
/* 19 */     vec.y = vz;
/*    */   }
/*    */   
/*    */ 
/*    */   public Transformation inverse()
/*    */   {
/* 25 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/SwapYZ.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */