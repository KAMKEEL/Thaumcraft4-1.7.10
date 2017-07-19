/*    */ package thaumcraft.client.fx;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WRMat4
/*    */ {
/*    */   float[] mat;
/*    */   
/*    */ 
/*    */   public WRMat4()
/*    */   {
/* 12 */     loadIdentity();
/*    */   }
/*    */   
/*    */   public WRMat4 loadIdentity()
/*    */   {
/* 17 */     this.mat = new float[16];
/* 18 */     this.mat[0] = (this.mat[5] = this.mat[10] = this.mat[15] = 1.0F);
/* 19 */     return this;
/*    */   }
/*    */   
/*    */   public WRVector3 translate(WRVector3 vec)
/*    */   {
/* 24 */     float x = vec.x * this.mat[0] + vec.y * this.mat[1] + vec.z * this.mat[2] + this.mat[3];
/* 25 */     float y = vec.x * this.mat[4] + vec.y * this.mat[5] + vec.z * this.mat[6] + this.mat[7];
/* 26 */     float z = vec.x * this.mat[8] + vec.y * this.mat[9] + vec.z * this.mat[10] + this.mat[11];
/* 27 */     vec.x = x;
/* 28 */     vec.y = y;
/* 29 */     vec.z = z;
/* 30 */     return vec;
/*    */   }
/*    */   
/*    */   public static WRMat4 rotationMat(double angle, WRVector3 axis)
/*    */   {
/* 35 */     axis = axis.copy().normalize();
/* 36 */     float x = axis.x;
/* 37 */     float y = axis.y;
/* 38 */     float z = axis.z;
/* 39 */     angle *= 0.0174532925D;
/* 40 */     float cos = (float)Math.cos(angle);
/* 41 */     float ocos = 1.0F - cos;
/* 42 */     float sin = (float)Math.sin(angle);
/* 43 */     WRMat4 rotmat = new WRMat4();
/* 44 */     rotmat.mat[0] = (x * x * ocos + cos);
/* 45 */     rotmat.mat[1] = (y * x * ocos + z * sin);
/* 46 */     rotmat.mat[2] = (x * z * ocos - y * sin);
/* 47 */     rotmat.mat[4] = (x * y * ocos - z * sin);
/* 48 */     rotmat.mat[5] = (y * y * ocos + cos);
/* 49 */     rotmat.mat[6] = (y * z * ocos + x * sin);
/* 50 */     rotmat.mat[8] = (x * z * ocos + y * sin);
/* 51 */     rotmat.mat[9] = (y * z * ocos - x * sin);
/* 52 */     rotmat.mat[10] = (z * z * ocos + cos);
/* 53 */     rotmat.mat[15] = 1.0F;
/* 54 */     return rotmat;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/WRMat4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */