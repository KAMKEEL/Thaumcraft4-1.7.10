/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class Translation extends Transformation
/*    */ {
/*    */   public Vector3 vec;
/*    */   
/*    */   public Translation(Vector3 vec)
/*    */   {
/* 16 */     this.vec = vec;
/*    */   }
/*    */   
/*    */   public Translation(double x, double y, double z) {
/* 20 */     this(new Vector3(x, y, z));
/*    */   }
/*    */   
/*    */   public void apply(Vector3 vec)
/*    */   {
/* 25 */     vec.add(this.vec);
/*    */   }
/*    */   
/*    */ 
/*    */   public void applyN(Vector3 normal) {}
/*    */   
/*    */ 
/*    */   public void apply(Matrix4 mat)
/*    */   {
/* 34 */     mat.translate(this.vec);
/*    */   }
/*    */   
/*    */   public Transformation at(Vector3 point)
/*    */   {
/* 39 */     return this;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void glApply()
/*    */   {
/* 45 */     GL11.glTranslated(this.vec.x, this.vec.y, this.vec.z);
/*    */   }
/*    */   
/*    */   public Transformation inverse()
/*    */   {
/* 50 */     return new Translation(-this.vec.x, -this.vec.y, -this.vec.z);
/*    */   }
/*    */   
/*    */   public Transformation merge(Transformation next)
/*    */   {
/* 55 */     if ((next instanceof Translation)) {
/* 56 */       return new Translation(this.vec.copy().add(((Translation)next).vec));
/*    */     }
/* 58 */     return null;
/*    */   }
/*    */   
/*    */   public boolean isRedundant()
/*    */   {
/* 63 */     return this.vec.equalsT(Vector3.zero);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 68 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 69 */     return "Translation(" + new BigDecimal(this.vec.x, cont) + ", " + new BigDecimal(this.vec.y, cont) + ", " + new BigDecimal(this.vec.z, cont) + ")";
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/Translation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */