/*    */ package thaumcraft.codechicken.lib.render.uv;
/*    */ 
/*    */ import java.math.MathContext;
/*    */ 
/*    */ public class UVScale extends UVTransformation
/*    */ {
/*    */   double su;
/*    */   double sv;
/*    */   
/*    */   public UVScale(double scaleu, double scalev)
/*    */   {
/* 12 */     this.su = scaleu;
/* 13 */     this.sv = scalev;
/*    */   }
/*    */   
/*    */   public UVScale(double d) {
/* 17 */     this(d, d);
/*    */   }
/*    */   
/*    */   public void apply(UV uv)
/*    */   {
/* 22 */     uv.u *= this.su;
/* 23 */     uv.v *= this.sv;
/*    */   }
/*    */   
/*    */   public UVTransformation inverse()
/*    */   {
/* 28 */     return new UVScale(1.0D / this.su, 1.0D / this.sv);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 33 */     MathContext cont = new MathContext(4, java.math.RoundingMode.HALF_UP);
/* 34 */     return "UVScale(" + new java.math.BigDecimal(this.su, cont) + ", " + new java.math.BigDecimal(this.sv, cont) + ")";
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/render/uv/UVScale.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */