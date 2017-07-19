/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class Scale extends Transformation
/*    */ {
/*    */   public Vector3 factor;
/*    */   
/*    */   public Scale(Vector3 factor)
/*    */   {
/* 16 */     this.factor = factor;
/*    */   }
/*    */   
/*    */   public Scale(double factor) {
/* 20 */     this(new Vector3(factor, factor, factor));
/*    */   }
/*    */   
/*    */   public Scale(double x, double y, double z) {
/* 24 */     this(new Vector3(x, y, z));
/*    */   }
/*    */   
/*    */   public void apply(Vector3 vec)
/*    */   {
/* 29 */     vec.multiply(this.factor);
/*    */   }
/*    */   
/*    */ 
/*    */   public void applyN(Vector3 normal) {}
/*    */   
/*    */ 
/*    */   public void apply(Matrix4 mat)
/*    */   {
/* 38 */     mat.scale(this.factor);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void glApply()
/*    */   {
/* 44 */     GL11.glScaled(this.factor.x, this.factor.y, this.factor.z);
/*    */   }
/*    */   
/*    */   public Transformation inverse()
/*    */   {
/* 49 */     return new Scale(1.0D / this.factor.x, 1.0D / this.factor.y, 1.0D / this.factor.z);
/*    */   }
/*    */   
/*    */   public Transformation merge(Transformation next)
/*    */   {
/* 54 */     if ((next instanceof Scale)) {
/* 55 */       return new Scale(this.factor.copy().multiply(((Scale)next).factor));
/*    */     }
/* 57 */     return null;
/*    */   }
/*    */   
/*    */   public boolean isRedundant()
/*    */   {
/* 62 */     return this.factor.equalsT(Vector3.one);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 67 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 68 */     return "Scale(" + new BigDecimal(this.factor.x, cont) + ", " + new BigDecimal(this.factor.y, cont) + ", " + new BigDecimal(this.factor.z, cont) + ")";
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/Scale.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */