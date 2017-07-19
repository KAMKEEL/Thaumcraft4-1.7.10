/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.vector.Vector3f;
/*     */ import org.lwjgl.util.vector.Vector4f;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Vector3
/*     */   implements Copyable<Vector3>
/*     */ {
/*  22 */   public static Vector3 zero = new Vector3();
/*  23 */   public static Vector3 one = new Vector3(1.0D, 1.0D, 1.0D);
/*  24 */   public static Vector3 center = new Vector3(0.5D, 0.5D, 0.5D);
/*     */   
/*     */   public double x;
/*     */   
/*     */   public double y;
/*     */   
/*     */   public double z;
/*     */   
/*     */   public Vector3() {}
/*     */   
/*     */   public Vector3(double d, double d1, double d2)
/*     */   {
/*  36 */     this.x = d;
/*  37 */     this.y = d1;
/*  38 */     this.z = d2;
/*     */   }
/*     */   
/*     */   public Vector3(Vector3 vec)
/*     */   {
/*  43 */     this.x = vec.x;
/*  44 */     this.y = vec.y;
/*  45 */     this.z = vec.z;
/*     */   }
/*     */   
/*     */   public Vector3(double[] da)
/*     */   {
/*  50 */     this(da[0], da[1], da[2]);
/*     */   }
/*     */   
/*     */   public Vector3(Vec3 vec)
/*     */   {
/*  55 */     this.x = vec.field_72450_a;
/*  56 */     this.y = vec.field_72448_b;
/*  57 */     this.z = vec.field_72449_c;
/*     */   }
/*     */   
/*     */   public Vector3(BlockCoord coord)
/*     */   {
/*  62 */     this.x = coord.x;
/*  63 */     this.y = coord.y;
/*  64 */     this.z = coord.z;
/*     */   }
/*     */   
/*     */   public Vector3 copy()
/*     */   {
/*  69 */     return new Vector3(this);
/*     */   }
/*     */   
/*     */   public static Vector3 fromEntity(Entity e)
/*     */   {
/*  74 */     return new Vector3(e.field_70165_t, e.field_70163_u, e.field_70161_v);
/*     */   }
/*     */   
/*     */   public static Vector3 fromEntityCenter(Entity e)
/*     */   {
/*  79 */     return new Vector3(e.field_70165_t, e.field_70163_u - e.field_70129_M + e.field_70131_O / 2.0F, e.field_70161_v);
/*     */   }
/*     */   
/*     */   public static Vector3 fromTileEntity(TileEntity e)
/*     */   {
/*  84 */     return new Vector3(e.field_145851_c, e.field_145848_d, e.field_145849_e);
/*     */   }
/*     */   
/*     */   public static Vector3 fromTileEntityCenter(TileEntity e)
/*     */   {
/*  89 */     return new Vector3(e.field_145851_c + 0.5D, e.field_145848_d + 0.5D, e.field_145849_e + 0.5D);
/*     */   }
/*     */   
/*     */   public static Vector3 fromAxes(double[] da)
/*     */   {
/*  94 */     return new Vector3(da[2], da[0], da[1]);
/*     */   }
/*     */   
/*     */   public Vector3 set(double d, double d1, double d2)
/*     */   {
/*  99 */     this.x = d;
/* 100 */     this.y = d1;
/* 101 */     this.z = d2;
/* 102 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 set(Vector3 vec)
/*     */   {
/* 107 */     this.x = vec.x;
/* 108 */     this.y = vec.y;
/* 109 */     this.z = vec.z;
/* 110 */     return this;
/*     */   }
/*     */   
/*     */   public double getSide(int side) {
/* 114 */     switch (side) {
/*     */     case 0: 
/*     */     case 1: 
/* 117 */       return this.y;
/*     */     case 2: 
/*     */     case 3: 
/* 120 */       return this.z;
/*     */     case 4: 
/*     */     case 5: 
/* 123 */       return this.x;
/*     */     }
/* 125 */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */   
/*     */   public Vector3 setSide(int s, double v)
/*     */   {
/* 130 */     switch (s) {
/*     */     case 0: 
/*     */     case 1: 
/* 133 */       this.y = v; break;
/*     */     case 2: case 3: 
/* 135 */       this.z = v; break;
/*     */     case 4: case 5: 
/* 137 */       this.x = v; break;
/* 138 */     default:  throw new IndexOutOfBoundsException("Switch Falloff");
/*     */     }
/* 140 */     return this;
/*     */   }
/*     */   
/*     */   public double dotProduct(Vector3 vec)
/*     */   {
/* 145 */     double d = vec.x * this.x + vec.y * this.y + vec.z * this.z;
/*     */     
/* 147 */     if ((d > 1.0D) && (d < 1.00001D)) {
/* 148 */       d = 1.0D;
/* 149 */     } else if ((d < -1.0D) && (d > -1.00001D))
/* 150 */       d = -1.0D;
/* 151 */     return d;
/*     */   }
/*     */   
/*     */   public double dotProduct(double d, double d1, double d2)
/*     */   {
/* 156 */     return d * this.x + d1 * this.y + d2 * this.z;
/*     */   }
/*     */   
/*     */   public Vector3 crossProduct(Vector3 vec)
/*     */   {
/* 161 */     double d = this.y * vec.z - this.z * vec.y;
/* 162 */     double d1 = this.z * vec.x - this.x * vec.z;
/* 163 */     double d2 = this.x * vec.y - this.y * vec.x;
/* 164 */     this.x = d;
/* 165 */     this.y = d1;
/* 166 */     this.z = d2;
/* 167 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 add(double d, double d1, double d2)
/*     */   {
/* 172 */     this.x += d;
/* 173 */     this.y += d1;
/* 174 */     this.z += d2;
/* 175 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 add(Vector3 vec)
/*     */   {
/* 180 */     this.x += vec.x;
/* 181 */     this.y += vec.y;
/* 182 */     this.z += vec.z;
/* 183 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 add(double d)
/*     */   {
/* 188 */     return add(d, d, d);
/*     */   }
/*     */   
/*     */   public Vector3 sub(Vector3 vec)
/*     */   {
/* 193 */     return subtract(vec);
/*     */   }
/*     */   
/*     */   public Vector3 subtract(Vector3 vec)
/*     */   {
/* 198 */     this.x -= vec.x;
/* 199 */     this.y -= vec.y;
/* 200 */     this.z -= vec.z;
/* 201 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 negate(Vector3 vec)
/*     */   {
/* 206 */     this.x = (-this.x);
/* 207 */     this.y = (-this.y);
/* 208 */     this.z = (-this.z);
/* 209 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 multiply(double d)
/*     */   {
/* 214 */     this.x *= d;
/* 215 */     this.y *= d;
/* 216 */     this.z *= d;
/* 217 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 multiply(Vector3 f)
/*     */   {
/* 222 */     this.x *= f.x;
/* 223 */     this.y *= f.y;
/* 224 */     this.z *= f.z;
/* 225 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 multiply(double fx, double fy, double fz)
/*     */   {
/* 230 */     this.x *= fx;
/* 231 */     this.y *= fy;
/* 232 */     this.z *= fz;
/* 233 */     return this;
/*     */   }
/*     */   
/*     */   public double mag()
/*     */   {
/* 238 */     return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/*     */   }
/*     */   
/*     */   public double magSquared()
/*     */   {
/* 243 */     return this.x * this.x + this.y * this.y + this.z * this.z;
/*     */   }
/*     */   
/*     */   public Vector3 normalize()
/*     */   {
/* 248 */     double d = mag();
/* 249 */     if (d != 0.0D)
/*     */     {
/* 251 */       multiply(1.0D / d);
/*     */     }
/* 253 */     return this;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 258 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 259 */     return "Vector3(" + new BigDecimal(this.x, cont) + ", " + new BigDecimal(this.y, cont) + ", " + new BigDecimal(this.z, cont) + ")";
/*     */   }
/*     */   
/*     */   public Vector3 perpendicular()
/*     */   {
/* 264 */     if (this.z == 0.0D)
/* 265 */       return zCrossProduct();
/* 266 */     return xCrossProduct();
/*     */   }
/*     */   
/*     */   public Vector3 xCrossProduct()
/*     */   {
/* 271 */     double d = this.z;
/* 272 */     double d1 = -this.y;
/* 273 */     this.x = 0.0D;
/* 274 */     this.y = d;
/* 275 */     this.z = d1;
/* 276 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 zCrossProduct()
/*     */   {
/* 281 */     double d = this.y;
/* 282 */     double d1 = -this.x;
/* 283 */     this.x = d;
/* 284 */     this.y = d1;
/* 285 */     this.z = 0.0D;
/* 286 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 yCrossProduct()
/*     */   {
/* 291 */     double d = -this.z;
/* 292 */     double d1 = this.x;
/* 293 */     this.x = d;
/* 294 */     this.y = 0.0D;
/* 295 */     this.z = d1;
/* 296 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 rotate(double angle, Vector3 axis) {
/* 300 */     Quat.aroundAxis(axis.copy().normalize(), angle).rotate(this);
/* 301 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 rotate(Quat rotator)
/*     */   {
/* 306 */     rotator.rotate(this);
/* 307 */     return this;
/*     */   }
/*     */   
/*     */   public Vec3 toVec3D()
/*     */   {
/* 312 */     return Vec3.func_72443_a(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public double angle(Vector3 vec)
/*     */   {
/* 317 */     return Math.acos(copy().normalize().dotProduct(vec.copy().normalize()));
/*     */   }
/*     */   
/*     */   public boolean isZero()
/*     */   {
/* 322 */     return (this.x == 0.0D) && (this.y == 0.0D) && (this.z == 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isAxial()
/*     */   {
/* 327 */     return (this.y == 0.0D) || (this.z == 0.0D);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Vector3f vector3f()
/*     */   {
/* 333 */     return new Vector3f((float)this.x, (float)this.y, (float)this.z);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Vector4f vector4f()
/*     */   {
/* 339 */     return new Vector4f((float)this.x, (float)this.y, (float)this.z, 1.0F);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void glVertex()
/*     */   {
/* 345 */     GL11.glVertex3d(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public Vector3 YZintercept(Vector3 end, double px)
/*     */   {
/* 350 */     double dx = end.x - this.x;
/* 351 */     double dy = end.y - this.y;
/* 352 */     double dz = end.z - this.z;
/*     */     
/* 354 */     if (dx == 0.0D) {
/* 355 */       return null;
/*     */     }
/* 357 */     double d = (px - this.x) / dx;
/* 358 */     if (MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
/* 359 */       return this;
/*     */     }
/* 361 */     if (!MathHelper.between(0.0D, d, 1.0D)) {
/* 362 */       return null;
/*     */     }
/* 364 */     this.x = px;
/* 365 */     this.y += d * dy;
/* 366 */     this.z += d * dz;
/* 367 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 XZintercept(Vector3 end, double py)
/*     */   {
/* 372 */     double dx = end.x - this.x;
/* 373 */     double dy = end.y - this.y;
/* 374 */     double dz = end.z - this.z;
/*     */     
/* 376 */     if (dy == 0.0D) {
/* 377 */       return null;
/*     */     }
/* 379 */     double d = (py - this.y) / dy;
/* 380 */     if (MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
/* 381 */       return this;
/*     */     }
/* 383 */     if (!MathHelper.between(0.0D, d, 1.0D)) {
/* 384 */       return null;
/*     */     }
/* 386 */     this.x += d * dx;
/* 387 */     this.y = py;
/* 388 */     this.z += d * dz;
/* 389 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 XYintercept(Vector3 end, double pz)
/*     */   {
/* 394 */     double dx = end.x - this.x;
/* 395 */     double dy = end.y - this.y;
/* 396 */     double dz = end.z - this.z;
/*     */     
/* 398 */     if (dz == 0.0D) {
/* 399 */       return null;
/*     */     }
/* 401 */     double d = (pz - this.z) / dz;
/* 402 */     if (MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
/* 403 */       return this;
/*     */     }
/* 405 */     if (!MathHelper.between(0.0D, d, 1.0D)) {
/* 406 */       return null;
/*     */     }
/* 408 */     this.x += d * dx;
/* 409 */     this.y += d * dy;
/* 410 */     this.z = pz;
/* 411 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 negate()
/*     */   {
/* 416 */     this.x = (-this.x);
/* 417 */     this.y = (-this.y);
/* 418 */     this.z = (-this.z);
/* 419 */     return this;
/*     */   }
/*     */   
/*     */   public Translation translation()
/*     */   {
/* 424 */     return new Translation(this);
/*     */   }
/*     */   
/*     */   public double scalarProject(Vector3 b)
/*     */   {
/* 429 */     double l = b.mag();
/* 430 */     return l == 0.0D ? 0.0D : dotProduct(b) / l;
/*     */   }
/*     */   
/*     */   public Vector3 project(Vector3 b)
/*     */   {
/* 435 */     double l = b.magSquared();
/* 436 */     if (l == 0.0D)
/*     */     {
/* 438 */       set(0.0D, 0.0D, 0.0D);
/* 439 */       return this;
/*     */     }
/* 441 */     double m = dotProduct(b) / l;
/* 442 */     set(b).multiply(m);
/* 443 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 449 */     if (!(o instanceof Vector3))
/* 450 */       return false;
/* 451 */     Vector3 v = (Vector3)o;
/* 452 */     return (this.x == v.x) && (this.y == v.y) && (this.z == v.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equalsT(Vector3 v)
/*     */   {
/* 461 */     return (MathHelper.between(this.x - 1.0E-5D, v.x, this.x + 1.0E-5D)) && (MathHelper.between(this.y - 1.0E-5D, v.y, this.y + 1.0E-5D)) && (MathHelper.between(this.z - 1.0E-5D, v.z, this.z + 1.0E-5D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Vector3 apply(Transformation t)
/*     */   {
/* 468 */     t.apply(this);
/* 469 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 $tilde()
/*     */   {
/* 474 */     return normalize();
/*     */   }
/*     */   
/*     */   public Vector3 unary_$tilde()
/*     */   {
/* 479 */     return normalize();
/*     */   }
/*     */   
/*     */   public Vector3 $plus(Vector3 v)
/*     */   {
/* 484 */     return add(v);
/*     */   }
/*     */   
/*     */   public Vector3 $minus(Vector3 v)
/*     */   {
/* 489 */     return subtract(v);
/*     */   }
/*     */   
/*     */   public Vector3 $times(double d)
/*     */   {
/* 494 */     return multiply(d);
/*     */   }
/*     */   
/*     */   public Vector3 $div(double d)
/*     */   {
/* 499 */     return multiply(1.0D / d);
/*     */   }
/*     */   
/*     */   public Vector3 $times(Vector3 v)
/*     */   {
/* 504 */     return crossProduct(v);
/*     */   }
/*     */   
/*     */   public double $dot$times(Vector3 v)
/*     */   {
/* 509 */     return dotProduct(v);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/Vector3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */