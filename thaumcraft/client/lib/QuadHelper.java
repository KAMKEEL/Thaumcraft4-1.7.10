/*    */ package thaumcraft.client.lib;
/*    */ 
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ public class QuadHelper
/*    */ {
/*    */   public double x;
/*    */   public double y;
/*    */   public double z;
/*    */   public double angle;
/*    */   
/*    */   public QuadHelper(double ang, double xx, double yy, double zz)
/*    */   {
/* 15 */     this.x = xx;
/* 16 */     this.y = yy;
/* 17 */     this.z = zz;
/* 18 */     this.angle = ang;
/*    */   }
/*    */   
/*    */ 
/*    */   public static QuadHelper setAxis(Vec3 vec, double angle)
/*    */   {
/* 24 */     angle *= 0.5D;
/* 25 */     double d4 = MathHelper.func_76126_a((float)angle);
/* 26 */     return new QuadHelper(MathHelper.func_76134_b((float)angle), vec.field_72450_a * d4, vec.field_72448_b * d4, vec.field_72449_c * d4);
/*    */   }
/*    */   
/*    */   public void rotate(Vec3 vec)
/*    */   {
/* 31 */     double d = -this.x * vec.field_72450_a - this.y * vec.field_72448_b - this.z * vec.field_72449_c;
/* 32 */     double d1 = this.angle * vec.field_72450_a + this.y * vec.field_72449_c - this.z * vec.field_72448_b;
/* 33 */     double d2 = this.angle * vec.field_72448_b - this.x * vec.field_72449_c + this.z * vec.field_72450_a;
/* 34 */     double d3 = this.angle * vec.field_72449_c + this.x * vec.field_72448_b - this.y * vec.field_72450_a;
/* 35 */     vec.field_72450_a = (d1 * this.angle - d * this.x - d2 * this.z + d3 * this.y);
/* 36 */     vec.field_72448_b = (d2 * this.angle - d * this.y + d1 * this.z - d3 * this.x);
/* 37 */     vec.field_72449_c = (d3 * this.angle - d * this.z - d1 * this.y + d2 * this.x);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/lib/QuadHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */