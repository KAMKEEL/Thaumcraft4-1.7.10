/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.DoubleBuffer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ public class Matrix4
/*     */   extends Transformation
/*     */   implements Copyable<Matrix4>
/*     */ {
/*  18 */   private static DoubleBuffer glBuf = ByteBuffer.allocateDirect(128).order(ByteOrder.nativeOrder()).asDoubleBuffer();
/*     */   public double m00;
/*     */   public double m01;
/*     */   public double m02;
/*     */   public double m03;
/*     */   
/*     */   public Matrix4() {
/*  25 */     this.m00 = (this.m11 = this.m22 = this.m33 = 1.0D);
/*     */   }
/*     */   
/*     */   public double m10;
/*     */   public double m11;
/*     */   public double m12;
/*     */   
/*     */   public Matrix4(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13, double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33) {
/*  33 */     this.m00 = d00;
/*  34 */     this.m01 = d01;
/*  35 */     this.m02 = d02;
/*  36 */     this.m03 = d03;
/*  37 */     this.m10 = d10;
/*  38 */     this.m11 = d11;
/*  39 */     this.m12 = d12;
/*  40 */     this.m13 = d13;
/*  41 */     this.m20 = d20;
/*  42 */     this.m21 = d21;
/*  43 */     this.m22 = d22;
/*  44 */     this.m23 = d23;
/*  45 */     this.m30 = d30;
/*  46 */     this.m31 = d31;
/*  47 */     this.m32 = d32;
/*  48 */     this.m33 = d33;
/*     */   }
/*     */   
/*     */   public Matrix4(Matrix4 mat)
/*     */   {
/*  53 */     set(mat);
/*     */   }
/*     */   
/*     */   public Matrix4 setIdentity()
/*     */   {
/*  58 */     this.m00 = (this.m11 = this.m22 = this.m33 = 1.0D);
/*  59 */     this.m01 = (this.m02 = this.m03 = this.m10 = this.m12 = this.m13 = this.m20 = this.m21 = this.m23 = this.m30 = this.m31 = this.m32 = 0.0D);
/*     */     
/*  61 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 translate(Vector3 vec)
/*     */   {
/*  66 */     this.m03 += this.m00 * vec.x + this.m01 * vec.y + this.m02 * vec.z;
/*  67 */     this.m13 += this.m10 * vec.x + this.m11 * vec.y + this.m12 * vec.z;
/*  68 */     this.m23 += this.m20 * vec.x + this.m21 * vec.y + this.m22 * vec.z;
/*  69 */     this.m33 += this.m30 * vec.x + this.m31 * vec.y + this.m32 * vec.z;
/*     */     
/*  71 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 scale(Vector3 vec)
/*     */   {
/*  76 */     this.m00 *= vec.x;
/*  77 */     this.m10 *= vec.x;
/*  78 */     this.m20 *= vec.x;
/*  79 */     this.m30 *= vec.x;
/*  80 */     this.m01 *= vec.y;
/*  81 */     this.m11 *= vec.y;
/*  82 */     this.m21 *= vec.y;
/*  83 */     this.m31 *= vec.y;
/*  84 */     this.m02 *= vec.z;
/*  85 */     this.m12 *= vec.z;
/*  86 */     this.m22 *= vec.z;
/*  87 */     this.m32 *= vec.z;
/*     */     
/*  89 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 rotate(double angle, Vector3 axis)
/*     */   {
/*  94 */     double c = Math.cos(angle);
/*  95 */     double s = Math.sin(angle);
/*  96 */     double mc = 1.0D - c;
/*  97 */     double xy = axis.x * axis.y;
/*  98 */     double yz = axis.y * axis.z;
/*  99 */     double xz = axis.x * axis.z;
/* 100 */     double xs = axis.x * s;
/* 101 */     double ys = axis.y * s;
/* 102 */     double zs = axis.z * s;
/*     */     
/* 104 */     double f00 = axis.x * axis.x * mc + c;
/* 105 */     double f10 = xy * mc + zs;
/* 106 */     double f20 = xz * mc - ys;
/*     */     
/* 108 */     double f01 = xy * mc - zs;
/* 109 */     double f11 = axis.y * axis.y * mc + c;
/* 110 */     double f21 = yz * mc + xs;
/*     */     
/* 112 */     double f02 = xz * mc + ys;
/* 113 */     double f12 = yz * mc - xs;
/* 114 */     double f22 = axis.z * axis.z * mc + c;
/*     */     
/* 116 */     double t00 = this.m00 * f00 + this.m01 * f10 + this.m02 * f20;
/* 117 */     double t10 = this.m10 * f00 + this.m11 * f10 + this.m12 * f20;
/* 118 */     double t20 = this.m20 * f00 + this.m21 * f10 + this.m22 * f20;
/* 119 */     double t30 = this.m30 * f00 + this.m31 * f10 + this.m32 * f20;
/* 120 */     double t01 = this.m00 * f01 + this.m01 * f11 + this.m02 * f21;
/* 121 */     double t11 = this.m10 * f01 + this.m11 * f11 + this.m12 * f21;
/* 122 */     double t21 = this.m20 * f01 + this.m21 * f11 + this.m22 * f21;
/* 123 */     double t31 = this.m30 * f01 + this.m31 * f11 + this.m32 * f21;
/* 124 */     this.m02 = (this.m00 * f02 + this.m01 * f12 + this.m02 * f22);
/* 125 */     this.m12 = (this.m10 * f02 + this.m11 * f12 + this.m12 * f22);
/* 126 */     this.m22 = (this.m20 * f02 + this.m21 * f12 + this.m22 * f22);
/* 127 */     this.m32 = (this.m30 * f02 + this.m31 * f12 + this.m32 * f22);
/* 128 */     this.m00 = t00;
/* 129 */     this.m10 = t10;
/* 130 */     this.m20 = t20;
/* 131 */     this.m30 = t30;
/* 132 */     this.m01 = t01;
/* 133 */     this.m11 = t11;
/* 134 */     this.m21 = t21;
/* 135 */     this.m31 = t31;
/*     */     
/* 137 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 rotate(Rotation rotation)
/*     */   {
/* 142 */     rotation.apply(this);
/* 143 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 leftMultiply(Matrix4 mat)
/*     */   {
/* 148 */     double n00 = this.m00 * mat.m00 + this.m10 * mat.m01 + this.m20 * mat.m02 + this.m30 * mat.m03;
/* 149 */     double n01 = this.m01 * mat.m00 + this.m11 * mat.m01 + this.m21 * mat.m02 + this.m31 * mat.m03;
/* 150 */     double n02 = this.m02 * mat.m00 + this.m12 * mat.m01 + this.m22 * mat.m02 + this.m32 * mat.m03;
/* 151 */     double n03 = this.m03 * mat.m00 + this.m13 * mat.m01 + this.m23 * mat.m02 + this.m33 * mat.m03;
/* 152 */     double n10 = this.m00 * mat.m10 + this.m10 * mat.m11 + this.m20 * mat.m12 + this.m30 * mat.m13;
/* 153 */     double n11 = this.m01 * mat.m10 + this.m11 * mat.m11 + this.m21 * mat.m12 + this.m31 * mat.m13;
/* 154 */     double n12 = this.m02 * mat.m10 + this.m12 * mat.m11 + this.m22 * mat.m12 + this.m32 * mat.m13;
/* 155 */     double n13 = this.m03 * mat.m10 + this.m13 * mat.m11 + this.m23 * mat.m12 + this.m33 * mat.m13;
/* 156 */     double n20 = this.m00 * mat.m20 + this.m10 * mat.m21 + this.m20 * mat.m22 + this.m30 * mat.m23;
/* 157 */     double n21 = this.m01 * mat.m20 + this.m11 * mat.m21 + this.m21 * mat.m22 + this.m31 * mat.m23;
/* 158 */     double n22 = this.m02 * mat.m20 + this.m12 * mat.m21 + this.m22 * mat.m22 + this.m32 * mat.m23;
/* 159 */     double n23 = this.m03 * mat.m20 + this.m13 * mat.m21 + this.m23 * mat.m22 + this.m33 * mat.m23;
/* 160 */     double n30 = this.m00 * mat.m30 + this.m10 * mat.m31 + this.m20 * mat.m32 + this.m30 * mat.m33;
/* 161 */     double n31 = this.m01 * mat.m30 + this.m11 * mat.m31 + this.m21 * mat.m32 + this.m31 * mat.m33;
/* 162 */     double n32 = this.m02 * mat.m30 + this.m12 * mat.m31 + this.m22 * mat.m32 + this.m32 * mat.m33;
/* 163 */     double n33 = this.m03 * mat.m30 + this.m13 * mat.m31 + this.m23 * mat.m32 + this.m33 * mat.m33;
/*     */     
/* 165 */     this.m00 = n00;
/* 166 */     this.m01 = n01;
/* 167 */     this.m02 = n02;
/* 168 */     this.m03 = n03;
/* 169 */     this.m10 = n10;
/* 170 */     this.m11 = n11;
/* 171 */     this.m12 = n12;
/* 172 */     this.m13 = n13;
/* 173 */     this.m20 = n20;
/* 174 */     this.m21 = n21;
/* 175 */     this.m22 = n22;
/* 176 */     this.m23 = n23;
/* 177 */     this.m30 = n30;
/* 178 */     this.m31 = n31;
/* 179 */     this.m32 = n32;
/* 180 */     this.m33 = n33;
/*     */     
/* 182 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 multiply(Matrix4 mat)
/*     */   {
/* 187 */     double n00 = this.m00 * mat.m00 + this.m01 * mat.m10 + this.m02 * mat.m20 + this.m03 * mat.m30;
/* 188 */     double n01 = this.m00 * mat.m01 + this.m01 * mat.m11 + this.m02 * mat.m21 + this.m03 * mat.m31;
/* 189 */     double n02 = this.m00 * mat.m02 + this.m01 * mat.m12 + this.m02 * mat.m22 + this.m03 * mat.m32;
/* 190 */     double n03 = this.m00 * mat.m03 + this.m01 * mat.m13 + this.m02 * mat.m23 + this.m03 * mat.m33;
/* 191 */     double n10 = this.m10 * mat.m00 + this.m11 * mat.m10 + this.m12 * mat.m20 + this.m13 * mat.m30;
/* 192 */     double n11 = this.m10 * mat.m01 + this.m11 * mat.m11 + this.m12 * mat.m21 + this.m13 * mat.m31;
/* 193 */     double n12 = this.m10 * mat.m02 + this.m11 * mat.m12 + this.m12 * mat.m22 + this.m13 * mat.m32;
/* 194 */     double n13 = this.m10 * mat.m03 + this.m11 * mat.m13 + this.m12 * mat.m23 + this.m13 * mat.m33;
/* 195 */     double n20 = this.m20 * mat.m00 + this.m21 * mat.m10 + this.m22 * mat.m20 + this.m23 * mat.m30;
/* 196 */     double n21 = this.m20 * mat.m01 + this.m21 * mat.m11 + this.m22 * mat.m21 + this.m23 * mat.m31;
/* 197 */     double n22 = this.m20 * mat.m02 + this.m21 * mat.m12 + this.m22 * mat.m22 + this.m23 * mat.m32;
/* 198 */     double n23 = this.m20 * mat.m03 + this.m21 * mat.m13 + this.m22 * mat.m23 + this.m23 * mat.m33;
/* 199 */     double n30 = this.m30 * mat.m00 + this.m31 * mat.m10 + this.m32 * mat.m20 + this.m33 * mat.m30;
/* 200 */     double n31 = this.m30 * mat.m01 + this.m31 * mat.m11 + this.m32 * mat.m21 + this.m33 * mat.m31;
/* 201 */     double n32 = this.m30 * mat.m02 + this.m31 * mat.m12 + this.m32 * mat.m22 + this.m33 * mat.m32;
/* 202 */     double n33 = this.m30 * mat.m03 + this.m31 * mat.m13 + this.m32 * mat.m23 + this.m33 * mat.m33;
/*     */     
/* 204 */     this.m00 = n00;
/* 205 */     this.m01 = n01;
/* 206 */     this.m02 = n02;
/* 207 */     this.m03 = n03;
/* 208 */     this.m10 = n10;
/* 209 */     this.m11 = n11;
/* 210 */     this.m12 = n12;
/* 211 */     this.m13 = n13;
/* 212 */     this.m20 = n20;
/* 213 */     this.m21 = n21;
/* 214 */     this.m22 = n22;
/* 215 */     this.m23 = n23;
/* 216 */     this.m30 = n30;
/* 217 */     this.m31 = n31;
/* 218 */     this.m32 = n32;
/* 219 */     this.m33 = n33;
/*     */     
/* 221 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 transpose()
/*     */   {
/* 226 */     double n00 = this.m00;
/* 227 */     double n10 = this.m01;
/* 228 */     double n20 = this.m02;
/* 229 */     double n30 = this.m03;
/* 230 */     double n01 = this.m10;
/* 231 */     double n11 = this.m11;
/* 232 */     double n21 = this.m12;
/* 233 */     double n31 = this.m13;
/* 234 */     double n02 = this.m20;
/* 235 */     double n12 = this.m21;
/* 236 */     double n22 = this.m22;
/* 237 */     double n32 = this.m23;
/* 238 */     double n03 = this.m30;
/* 239 */     double n13 = this.m31;
/* 240 */     double n23 = this.m32;
/* 241 */     double n33 = this.m33;
/*     */     
/* 243 */     this.m00 = n00;
/* 244 */     this.m01 = n01;
/* 245 */     this.m02 = n02;
/* 246 */     this.m03 = n03;
/* 247 */     this.m10 = n10;
/* 248 */     this.m11 = n11;
/* 249 */     this.m12 = n12;
/* 250 */     this.m13 = n13;
/* 251 */     this.m20 = n20;
/* 252 */     this.m21 = n21;
/* 253 */     this.m22 = n22;
/* 254 */     this.m23 = n23;
/* 255 */     this.m30 = n30;
/* 256 */     this.m31 = n31;
/* 257 */     this.m32 = n32;
/* 258 */     this.m33 = n33;
/*     */     
/* 260 */     return this;
/*     */   }
/*     */   
/*     */   public Matrix4 copy()
/*     */   {
/* 265 */     return new Matrix4(this);
/*     */   }
/*     */   
/*     */   public Matrix4 set(Matrix4 mat)
/*     */   {
/* 270 */     this.m00 = mat.m00;
/* 271 */     this.m01 = mat.m01;
/* 272 */     this.m02 = mat.m02;
/* 273 */     this.m03 = mat.m03;
/* 274 */     this.m10 = mat.m10;
/* 275 */     this.m11 = mat.m11;
/* 276 */     this.m12 = mat.m12;
/* 277 */     this.m13 = mat.m13;
/* 278 */     this.m20 = mat.m20;
/* 279 */     this.m21 = mat.m21;
/* 280 */     this.m22 = mat.m22;
/* 281 */     this.m23 = mat.m23;
/* 282 */     this.m30 = mat.m30;
/* 283 */     this.m31 = mat.m31;
/* 284 */     this.m32 = mat.m32;
/* 285 */     this.m33 = mat.m33;
/*     */     
/* 287 */     return this;
/*     */   }
/*     */   
/*     */   public double m13;
/*     */   public double m20;
/*     */   public double m21;
/* 293 */   public void apply(Matrix4 mat) { mat.multiply(this); }
/*     */   
/*     */ 
/*     */   private void mult3x3(Vector3 vec)
/*     */   {
/* 298 */     double x = this.m00 * vec.x + this.m01 * vec.y + this.m02 * vec.z;
/* 299 */     double y = this.m10 * vec.x + this.m11 * vec.y + this.m12 * vec.z;
/* 300 */     double z = this.m20 * vec.x + this.m21 * vec.y + this.m22 * vec.z;
/*     */     
/* 302 */     vec.x = x;
/* 303 */     vec.y = y;
/* 304 */     vec.z = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void apply(Vector3 vec)
/*     */   {
/* 310 */     mult3x3(vec);
/* 311 */     vec.add(this.m03, this.m13, this.m23);
/*     */   }
/*     */   
/*     */ 
/*     */   public void applyN(Vector3 vec)
/*     */   {
/* 317 */     mult3x3(vec);
/* 318 */     vec.normalize();
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 324 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 325 */     return "[" + new BigDecimal(this.m00, cont) + "," + new BigDecimal(this.m01, cont) + "," + new BigDecimal(this.m02, cont) + "," + new BigDecimal(this.m03, cont) + "]\n" + "[" + new BigDecimal(this.m10, cont) + "," + new BigDecimal(this.m11, cont) + "," + new BigDecimal(this.m12, cont) + "," + new BigDecimal(this.m13, cont) + "]\n" + "[" + new BigDecimal(this.m20, cont) + "," + new BigDecimal(this.m21, cont) + "," + new BigDecimal(this.m22, cont) + "," + new BigDecimal(this.m23, cont) + "]\n" + "[" + new BigDecimal(this.m30, cont) + "," + new BigDecimal(this.m31, cont) + "," + new BigDecimal(this.m32, cont) + "," + new BigDecimal(this.m33, cont) + "]"; }
/*     */   
/*     */   public double m22;
/*     */   public double m23;
/*     */   public double m30;
/*     */   public double m31;
/*     */   public double m32;
/*     */   public double m33;
/* 333 */   public Matrix4 apply(Transformation t) { t.apply(this);
/* 334 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void glApply()
/*     */   {
/* 341 */     glBuf.put(this.m00).put(this.m10).put(this.m20).put(this.m30).put(this.m01).put(this.m11).put(this.m21).put(this.m31).put(this.m02).put(this.m12).put(this.m22).put(this.m32).put(this.m03).put(this.m13).put(this.m23).put(this.m33);
/*     */     
/*     */ 
/*     */ 
/* 345 */     glBuf.flip();
/* 346 */     GL11.glMultMatrix(glBuf);
/*     */   }
/*     */   
/*     */ 
/*     */   public Transformation inverse()
/*     */   {
/* 352 */     throw new IrreversibleTransformationException(this);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/Matrix4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */