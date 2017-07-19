/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ 
/*     */ public class Quat
/*     */   implements Copyable<Quat>
/*     */ {
/*     */   public double x;
/*     */   public double y;
/*     */   public double z;
/*     */   public double s;
/*     */   
/*     */   public Quat()
/*     */   {
/*  20 */     this.s = 1.0D;
/*  21 */     this.x = 0.0D;
/*  22 */     this.y = 0.0D;
/*  23 */     this.z = 0.0D;
/*     */   }
/*     */   
/*     */   public Quat(Quat quat)
/*     */   {
/*  28 */     this.x = quat.x;
/*  29 */     this.y = quat.y;
/*  30 */     this.z = quat.z;
/*  31 */     this.s = quat.s;
/*     */   }
/*     */   
/*     */   public Quat(double d, double d1, double d2, double d3)
/*     */   {
/*  36 */     this.x = d1;
/*  37 */     this.y = d2;
/*  38 */     this.z = d3;
/*  39 */     this.s = d;
/*     */   }
/*     */   
/*     */   public Quat set(Quat quat)
/*     */   {
/*  44 */     this.x = quat.x;
/*  45 */     this.y = quat.y;
/*  46 */     this.z = quat.z;
/*  47 */     this.s = quat.s;
/*     */     
/*  49 */     return this;
/*     */   }
/*     */   
/*     */   public Quat set(double d, double d1, double d2, double d3)
/*     */   {
/*  54 */     this.x = d1;
/*  55 */     this.y = d2;
/*  56 */     this.z = d3;
/*  57 */     this.s = d;
/*     */     
/*  59 */     return this;
/*     */   }
/*     */   
/*     */   public static Quat aroundAxis(double ax, double ay, double az, double angle)
/*     */   {
/*  64 */     return new Quat().setAroundAxis(ax, ay, az, angle);
/*     */   }
/*     */   
/*     */   public static Quat aroundAxis(Vector3 axis, double angle)
/*     */   {
/*  69 */     return aroundAxis(axis.x, axis.y, axis.z, angle);
/*     */   }
/*     */   
/*     */   public Quat setAroundAxis(double ax, double ay, double az, double angle)
/*     */   {
/*  74 */     angle *= 0.5D;
/*  75 */     double d4 = MathHelper.sin(angle);
/*  76 */     return set(MathHelper.cos(angle), ax * d4, ay * d4, az * d4);
/*     */   }
/*     */   
/*     */   public Quat setAroundAxis(Vector3 axis, double angle)
/*     */   {
/*  81 */     return setAroundAxis(axis.x, axis.y, axis.z, angle);
/*     */   }
/*     */   
/*     */   public Quat multiply(Quat quat)
/*     */   {
/*  86 */     double d = this.s * quat.s - this.x * quat.x - this.y * quat.y - this.z * quat.z;
/*  87 */     double d1 = this.s * quat.x + this.x * quat.s - this.y * quat.z + this.z * quat.y;
/*  88 */     double d2 = this.s * quat.y + this.x * quat.z + this.y * quat.s - this.z * quat.x;
/*  89 */     double d3 = this.s * quat.z - this.x * quat.y + this.y * quat.x + this.z * quat.s;
/*  90 */     this.s = d;
/*  91 */     this.x = d1;
/*  92 */     this.y = d2;
/*  93 */     this.z = d3;
/*     */     
/*  95 */     return this;
/*     */   }
/*     */   
/*     */   public Quat rightMultiply(Quat quat)
/*     */   {
/* 100 */     double d = this.s * quat.s - this.x * quat.x - this.y * quat.y - this.z * quat.z;
/* 101 */     double d1 = this.s * quat.x + this.x * quat.s + this.y * quat.z - this.z * quat.y;
/* 102 */     double d2 = this.s * quat.y - this.x * quat.z + this.y * quat.s + this.z * quat.x;
/* 103 */     double d3 = this.s * quat.z + this.x * quat.y - this.y * quat.x + this.z * quat.s;
/* 104 */     this.s = d;
/* 105 */     this.x = d1;
/* 106 */     this.y = d2;
/* 107 */     this.z = d3;
/*     */     
/* 109 */     return this;
/*     */   }
/*     */   
/*     */   public double mag()
/*     */   {
/* 114 */     return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.s * this.s);
/*     */   }
/*     */   
/*     */   public Quat normalize()
/*     */   {
/* 119 */     double d = mag();
/* 120 */     if (d != 0.0D)
/*     */     {
/* 122 */       d = 1.0D / d;
/* 123 */       this.x *= d;
/* 124 */       this.y *= d;
/* 125 */       this.z *= d;
/* 126 */       this.s *= d;
/*     */     }
/*     */     
/* 129 */     return this;
/*     */   }
/*     */   
/*     */   public Quat copy()
/*     */   {
/* 134 */     return new Quat(this);
/*     */   }
/*     */   
/*     */   public void rotate(Vector3 vec)
/*     */   {
/* 139 */     double d = -this.x * vec.x - this.y * vec.y - this.z * vec.z;
/* 140 */     double d1 = this.s * vec.x + this.y * vec.z - this.z * vec.y;
/* 141 */     double d2 = this.s * vec.y - this.x * vec.z + this.z * vec.x;
/* 142 */     double d3 = this.s * vec.z + this.x * vec.y - this.y * vec.x;
/* 143 */     vec.x = (d1 * this.s - d * this.x - d2 * this.z + d3 * this.y);
/* 144 */     vec.y = (d2 * this.s - d * this.y + d1 * this.z - d3 * this.x);
/* 145 */     vec.z = (d3 * this.s - d * this.z - d1 * this.y + d2 * this.x);
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 150 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 151 */     return "Quat(" + new BigDecimal(this.s, cont) + ", " + new BigDecimal(this.x, cont) + ", " + new BigDecimal(this.y, cont) + ", " + new BigDecimal(this.z, cont) + ")";
/*     */   }
/*     */   
/*     */   public Rotation rotation()
/*     */   {
/* 156 */     return new Rotation(this);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/Quat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */