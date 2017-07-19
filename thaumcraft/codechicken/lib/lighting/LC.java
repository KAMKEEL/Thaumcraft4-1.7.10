/*    */ package thaumcraft.codechicken.lib.lighting;
/*    */ 
/*    */ import thaumcraft.codechicken.lib.render.CCModel;
/*    */ import thaumcraft.codechicken.lib.util.Copyable;
/*    */ import thaumcraft.codechicken.lib.vec.Vector3;
/*    */ 
/*    */ public class LC implements Copyable<LC>
/*    */ {
/*    */   public int side;
/*    */   public float fa;
/*    */   public float fb;
/*    */   public float fc;
/*    */   public float fd;
/*    */   
/*    */   public LC()
/*    */   {
/* 17 */     this(0, 0.0F, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public LC(int s, float a, float b, float c, float d) {
/* 21 */     this.side = s;
/* 22 */     this.fa = a;
/* 23 */     this.fb = b;
/* 24 */     this.fc = c;
/* 25 */     this.fd = d;
/*    */   }
/*    */   
/*    */   public LC set(int s, float a, float b, float c, float d) {
/* 29 */     this.side = s;
/* 30 */     this.fa = a;
/* 31 */     this.fb = b;
/* 32 */     this.fc = c;
/* 33 */     this.fd = d;
/* 34 */     return this;
/*    */   }
/*    */   
/*    */   public LC set(LC lc) {
/* 38 */     return set(lc.side, lc.fa, lc.fb, lc.fc, lc.fd);
/*    */   }
/*    */   
/*    */   public LC compute(Vector3 vec, Vector3 normal) {
/* 42 */     int side = CCModel.findSide(normal);
/* 43 */     if (side < 0)
/* 44 */       return set(12, 1.0F, 0.0F, 0.0F, 0.0F);
/* 45 */     return compute(vec, side);
/*    */   }
/*    */   
/*    */   public LC compute(Vector3 vec, int side) {
/* 49 */     boolean offset = false;
/* 50 */     switch (side) {
/*    */     case 0: 
/* 52 */       offset = vec.y <= 0.0D;
/* 53 */       break;
/*    */     case 1: 
/* 55 */       offset = vec.y >= 1.0D;
/* 56 */       break;
/*    */     case 2: 
/* 58 */       offset = vec.z <= 0.0D;
/* 59 */       break;
/*    */     case 3: 
/* 61 */       offset = vec.z >= 1.0D;
/* 62 */       break;
/*    */     case 4: 
/* 64 */       offset = vec.x <= 0.0D;
/* 65 */       break;
/*    */     case 5: 
/* 67 */       offset = vec.x >= 1.0D;
/*    */     }
/*    */     
/* 70 */     if (!offset)
/* 71 */       side += 6;
/* 72 */     return computeO(vec, side);
/*    */   }
/*    */   
/*    */   public LC computeO(Vector3 vec, int side) {
/* 76 */     Vector3 v1 = thaumcraft.codechicken.lib.vec.Rotation.axes[(((side & 0xE) + 3) % 6)];
/* 77 */     Vector3 v2 = thaumcraft.codechicken.lib.vec.Rotation.axes[(((side & 0xE) + 5) % 6)];
/* 78 */     float d1 = (float)vec.scalarProject(v1);
/* 79 */     float d2 = 1.0F - d1;
/* 80 */     float d3 = (float)vec.scalarProject(v2);
/* 81 */     float d4 = 1.0F - d3;
/* 82 */     return set(side, d2 * d4, d2 * d3, d1 * d4, d1 * d3);
/*    */   }
/*    */   
/*    */   public LC copy()
/*    */   {
/* 87 */     return new LC(this.side, this.fa, this.fb, this.fc, this.fd);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/lighting/LC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */