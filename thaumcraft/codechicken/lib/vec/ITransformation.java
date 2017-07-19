/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ITransformation<Vector, Transformation extends ITransformation>
/*    */ {
/*    */   public abstract void apply(Vector paramVector);
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract Transformation at(Vector paramVector);
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract Transformation with(Transformation paramTransformation);
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Transformation merge(Transformation next)
/*    */   {
/* 31 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean isRedundant()
/*    */   {
/* 38 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public abstract Transformation inverse();
/*    */   
/*    */ 
/*    */   public Transformation $plus$plus(Transformation t)
/*    */   {
/* 47 */     return with(t);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/ITransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */