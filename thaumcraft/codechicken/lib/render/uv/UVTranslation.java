/*    */ package thaumcraft.codechicken.lib.render.uv;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import thaumcraft.codechicken.lib.math.MathHelper;
/*    */ 
/*    */ public class UVTranslation extends UVTransformation
/*    */ {
/*    */   public double du;
/*    */   public double dv;
/*    */   
/*    */   public UVTranslation(double u, double v)
/*    */   {
/* 15 */     this.du = u;
/* 16 */     this.dv = v;
/*    */   }
/*    */   
/*    */   public void apply(UV uv)
/*    */   {
/* 21 */     uv.u += this.du;
/* 22 */     uv.v += this.dv;
/*    */   }
/*    */   
/*    */   public UVTransformation at(UV point)
/*    */   {
/* 27 */     return this;
/*    */   }
/*    */   
/*    */   public UVTransformation inverse()
/*    */   {
/* 32 */     return new UVTranslation(-this.du, -this.dv);
/*    */   }
/*    */   
/*    */   public UVTransformation merge(UVTransformation next)
/*    */   {
/* 37 */     if ((next instanceof UVTranslation)) {
/* 38 */       UVTranslation t = (UVTranslation)next;
/* 39 */       return new UVTranslation(this.du + t.du, this.dv + t.dv);
/*    */     }
/*    */     
/* 42 */     return null;
/*    */   }
/*    */   
/*    */   public boolean isRedundant()
/*    */   {
/* 47 */     return (MathHelper.between(-1.0E-5D, this.du, 1.0E-5D)) && (MathHelper.between(-1.0E-5D, this.dv, 1.0E-5D));
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 52 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 53 */     return "UVTranslation(" + new BigDecimal(this.du, cont) + ", " + new BigDecimal(this.dv, cont) + ")";
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/render/uv/UVTranslation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */