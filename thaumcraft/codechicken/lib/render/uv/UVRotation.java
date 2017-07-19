/*    */ package thaumcraft.codechicken.lib.render.uv;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import thaumcraft.codechicken.lib.math.MathHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UVRotation
/*    */   extends UVTransformation
/*    */ {
/*    */   public double angle;
/*    */   
/*    */   public UVRotation(double angle)
/*    */   {
/* 18 */     this.angle = angle;
/*    */   }
/*    */   
/*    */   public void apply(UV uv)
/*    */   {
/* 23 */     double c = MathHelper.cos(this.angle);
/* 24 */     double s = MathHelper.sin(this.angle);
/* 25 */     double u2 = c * uv.u + s * uv.v;
/* 26 */     uv.v = (-s * uv.u + c * uv.v);
/* 27 */     uv.u = u2;
/*    */   }
/*    */   
/*    */   public UVTransformation inverse()
/*    */   {
/* 32 */     return new UVRotation(-this.angle);
/*    */   }
/*    */   
/*    */   public UVTransformation merge(UVTransformation next)
/*    */   {
/* 37 */     if ((next instanceof UVRotation)) {
/* 38 */       return new UVRotation(this.angle + ((UVRotation)next).angle);
/*    */     }
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   public boolean isRedundant()
/*    */   {
/* 45 */     return MathHelper.between(-1.0E-5D, this.angle, 1.0E-5D);
/*    */   }
/*    */   
/*    */ 
/*    */   public String toString()
/*    */   {
/* 51 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 52 */     return "UVRotation(" + new BigDecimal(this.angle, cont) + ")";
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/render/uv/UVRotation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */