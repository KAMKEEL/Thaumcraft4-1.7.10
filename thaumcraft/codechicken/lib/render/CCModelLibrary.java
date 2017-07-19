/*    */ package thaumcraft.codechicken.lib.render;
/*    */ 
/*    */ import thaumcraft.codechicken.lib.vec.Matrix4;
/*    */ import thaumcraft.codechicken.lib.vec.Quat;
/*    */ import thaumcraft.codechicken.lib.vec.Rotation;
/*    */ import thaumcraft.codechicken.lib.vec.Scale;
/*    */ import thaumcraft.codechicken.lib.vec.Vector3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CCModelLibrary
/*    */ {
/*    */   public static CCModel icosahedron4;
/*    */   public static CCModel icosahedron7;
/*    */   private static int i;
/*    */   
/*    */   private static void generateIcosahedron()
/*    */   {
/* 24 */     Vector3[] verts = new Vector3[12];
/*    */     
/* 26 */     verts[0] = new Vector3(-1.0D, 1.618033988749894D, 0.0D);
/* 27 */     verts[1] = new Vector3(1.0D, 1.618033988749894D, 0.0D);
/* 28 */     verts[2] = new Vector3(1.0D, -1.618033988749894D, 0.0D);
/* 29 */     verts[3] = new Vector3(-1.0D, -1.618033988749894D, 0.0D);
/*    */     
/* 31 */     verts[4] = new Vector3(0.0D, -1.0D, 1.618033988749894D);
/* 32 */     verts[5] = new Vector3(0.0D, 1.0D, 1.618033988749894D);
/* 33 */     verts[6] = new Vector3(0.0D, 1.0D, -1.618033988749894D);
/* 34 */     verts[7] = new Vector3(0.0D, -1.0D, -1.618033988749894D);
/*    */     
/* 36 */     verts[8] = new Vector3(1.618033988749894D, 0.0D, -1.0D);
/* 37 */     verts[9] = new Vector3(1.618033988749894D, 0.0D, 1.0D);
/* 38 */     verts[10] = new Vector3(-1.618033988749894D, 0.0D, 1.0D);
/* 39 */     verts[11] = new Vector3(-1.618033988749894D, 0.0D, -1.0D);
/*    */     
/* 41 */     Quat quat = Quat.aroundAxis(0.0D, 0.0D, 1.0D, Math.atan(0.6180339887498951D));
/* 42 */     for (Vector3 vec : verts) {
/* 43 */       quat.rotate(vec);
/*    */     }
/* 45 */     icosahedron4 = CCModel.newModel(4, 60);
/* 46 */     icosahedron7 = CCModel.newModel(7, 80);
/*    */     
/* 48 */     i = 0;
/*    */     
/* 50 */     addIcosahedronTriangle(verts[1], 0.5D, 0.0D, verts[0], 0.0D, 0.25D, verts[5], 1.0D, 0.25D);
/* 51 */     addIcosahedronTriangle(verts[1], 0.5D, 0.0D, verts[5], 0.0D, 0.25D, verts[9], 1.0D, 0.25D);
/* 52 */     addIcosahedronTriangle(verts[1], 0.5D, 0.0D, verts[9], 0.0D, 0.25D, verts[8], 1.0D, 0.25D);
/* 53 */     addIcosahedronTriangle(verts[1], 0.5D, 0.0D, verts[8], 0.0D, 0.25D, verts[6], 1.0D, 0.25D);
/* 54 */     addIcosahedronTriangle(verts[1], 0.5D, 0.0D, verts[6], 0.0D, 0.25D, verts[0], 1.0D, 0.25D);
/*    */     
/* 56 */     addIcosahedronTriangle(verts[0], 0.5D, 0.25D, verts[11], 0.0D, 0.75D, verts[10], 1.0D, 0.75D);
/* 57 */     addIcosahedronTriangle(verts[5], 0.5D, 0.25D, verts[10], 0.0D, 0.75D, verts[4], 1.0D, 0.75D);
/* 58 */     addIcosahedronTriangle(verts[9], 0.5D, 0.25D, verts[4], 0.0D, 0.75D, verts[2], 1.0D, 0.75D);
/* 59 */     addIcosahedronTriangle(verts[8], 0.5D, 0.25D, verts[2], 0.0D, 0.75D, verts[7], 1.0D, 0.75D);
/* 60 */     addIcosahedronTriangle(verts[6], 0.5D, 0.25D, verts[7], 0.0D, 0.75D, verts[11], 1.0D, 0.75D);
/*    */     
/* 62 */     addIcosahedronTriangle(verts[2], 0.5D, 0.75D, verts[8], 0.0D, 0.25D, verts[9], 1.0D, 0.25D);
/* 63 */     addIcosahedronTriangle(verts[7], 0.5D, 0.75D, verts[6], 0.0D, 0.25D, verts[8], 1.0D, 0.25D);
/* 64 */     addIcosahedronTriangle(verts[11], 0.5D, 0.75D, verts[0], 0.0D, 0.25D, verts[6], 1.0D, 0.25D);
/* 65 */     addIcosahedronTriangle(verts[10], 0.5D, 0.75D, verts[5], 0.0D, 0.25D, verts[0], 1.0D, 0.25D);
/* 66 */     addIcosahedronTriangle(verts[4], 0.5D, 0.75D, verts[9], 0.0D, 0.25D, verts[5], 1.0D, 0.25D);
/*    */     
/* 68 */     addIcosahedronTriangle(verts[3], 0.5D, 1.0D, verts[2], 0.0D, 0.75D, verts[4], 1.0D, 0.75D);
/* 69 */     addIcosahedronTriangle(verts[3], 0.5D, 1.0D, verts[7], 0.0D, 0.75D, verts[2], 1.0D, 0.75D);
/* 70 */     addIcosahedronTriangle(verts[3], 0.5D, 1.0D, verts[11], 0.0D, 0.75D, verts[7], 1.0D, 0.75D);
/* 71 */     addIcosahedronTriangle(verts[3], 0.5D, 1.0D, verts[10], 0.0D, 0.75D, verts[11], 1.0D, 0.75D);
/* 72 */     addIcosahedronTriangle(verts[3], 0.5D, 1.0D, verts[4], 0.0D, 0.75D, verts[10], 1.0D, 0.75D);
/*    */     
/* 74 */     icosahedron4.computeNormals().smoothNormals();
/* 75 */     icosahedron7.computeNormals().smoothNormals();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private static void addIcosahedronTriangle(Vector3 vec1, double u1, double v1, Vector3 vec2, double u2, double v2, Vector3 vec3, double u3, double v3)
/*    */   {
/* 82 */     icosahedron4.verts[(i * 3)] = (icosahedron7.verts[(i * 4)] = new Vertex5(vec1, u1, v1));
/* 83 */     icosahedron4.verts[(i * 3 + 1)] = (icosahedron7.verts[(i * 4 + 1)] = new Vertex5(vec2, u2, v2));
/* 84 */     icosahedron4.verts[(i * 3 + 2)] = (icosahedron7.verts[(i * 4 + 2)] = icosahedron7.verts[(i * 4 + 3)] = new Vertex5(vec3, u3, v3));
/* 85 */     i += 1;
/*    */   }
/*    */   
/*    */   public static Matrix4 getRenderMatrix(Vector3 position, Rotation rotation, double scale)
/*    */   {
/* 90 */     return new Matrix4().translate(position).apply(new Scale(scale)).apply(rotation);
/*    */   }
/*    */   
/*    */   static {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/render/CCModelLibrary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */