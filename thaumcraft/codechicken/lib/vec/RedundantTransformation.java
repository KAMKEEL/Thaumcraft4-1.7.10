/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RedundantTransformation
/*    */   extends Transformation
/*    */ {
/*    */   public void apply(Vector3 vec) {}
/*    */   
/*    */   public void apply(Matrix4 mat) {}
/*    */   
/*    */   public void applyN(Vector3 normal) {}
/*    */   
/*    */   public Transformation at(Vector3 point)
/*    */   {
/* 20 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void glApply() {}
/*    */   
/*    */ 
/*    */   public Transformation inverse()
/*    */   {
/* 30 */     return this;
/*    */   }
/*    */   
/*    */   public Transformation merge(Transformation next)
/*    */   {
/* 35 */     return next;
/*    */   }
/*    */   
/*    */   public boolean isRedundant()
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public String toString()
/*    */   {
/* 46 */     return "Nothing()";
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/RedundantTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */