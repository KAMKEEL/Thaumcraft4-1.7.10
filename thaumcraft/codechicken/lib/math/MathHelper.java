/*     */ package thaumcraft.codechicken.lib.math;
/*     */ 
/*     */ 
/*     */ public class MathHelper
/*     */ {
/*     */   public static final double phi = 1.618033988749894D;
/*     */   public static final double pi = 3.141592653589793D;
/*     */   public static final double todeg = 57.29577951308232D;
/*     */   public static final double torad = 0.017453292519943D;
/*     */   public static final double sqrt2 = 1.414213562373095D;
/*  11 */   public static double[] SIN_TABLE = new double[65536];
/*     */   
/*     */   static {
/*  14 */     for (int i = 0; i < 65536; i++) {
/*  15 */       SIN_TABLE[i] = Math.sin(i / 65536.0D * 2.0D * 3.141592653589793D);
/*     */     }
/*  17 */     SIN_TABLE[0] = 0.0D;
/*  18 */     SIN_TABLE['䀀'] = 1.0D;
/*  19 */     SIN_TABLE[32768] = 0.0D;
/*  20 */     SIN_TABLE[49152] = 1.0D;
/*     */   }
/*     */   
/*     */   public static double sin(double d)
/*     */   {
/*  25 */     return SIN_TABLE[((int)((float)d * 10430.378F) & 0xFFFF)];
/*     */   }
/*     */   
/*     */   public static double cos(double d)
/*     */   {
/*  30 */     return SIN_TABLE[((int)((float)d * 10430.378F + 16384.0F) & 0xFFFF)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static float approachLinear(float a, float b, float max)
/*     */   {
/*  41 */     return b - a < max ? b : a > b ? a - max : a - b < max ? b : a + max;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double approachLinear(double a, double b, double max)
/*     */   {
/*  54 */     return b - a < max ? b : a > b ? a - max : a - b < max ? b : a + max;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static float interpolate(float a, float b, float d)
/*     */   {
/*  67 */     return a + (b - a) * d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double interpolate(double a, double b, double d)
/*     */   {
/*  78 */     return a + (b - a) * d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double approachExp(double a, double b, double ratio)
/*     */   {
/*  89 */     return a + (b - a) * ratio;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double approachExp(double a, double b, double ratio, double cap)
/*     */   {
/* 101 */     double d = (b - a) * ratio;
/* 102 */     if (Math.abs(d) > cap)
/* 103 */       d = Math.signum(d) * cap;
/* 104 */     return a + d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double retreatExp(double a, double b, double c, double ratio, double kick)
/*     */   {
/* 117 */     double d = (Math.abs(c - a) + kick) * ratio;
/* 118 */     if (d > Math.abs(b - a))
/* 119 */       return b;
/* 120 */     return a + Math.signum(b - a) * d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double clip(double value, double min, double max)
/*     */   {
/* 132 */     if (value > max)
/* 133 */       value = max;
/* 134 */     if (value < min)
/* 135 */       value = min;
/* 136 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean between(double a, double x, double b)
/*     */   {
/* 144 */     return (a <= x) && (x <= b);
/*     */   }
/*     */   
/*     */   public static int approachExpI(int a, int b, double ratio)
/*     */   {
/* 149 */     int r = (int)Math.round(approachExp(a, b, ratio));
/* 150 */     return r == a ? b : r;
/*     */   }
/*     */   
/*     */   public static int retreatExpI(int a, int b, int c, double ratio, int kick)
/*     */   {
/* 155 */     int r = (int)Math.round(retreatExp(a, b, c, ratio, kick));
/* 156 */     return r == a ? b : r;
/*     */   }
/*     */   
/*     */   public static int floor_double(double d)
/*     */   {
/* 161 */     return net.minecraft.util.MathHelper.func_76128_c(d);
/*     */   }
/*     */   
/*     */   public static int roundAway(double d)
/*     */   {
/* 166 */     return (int)(d < 0.0D ? Math.floor(d) : Math.ceil(d));
/*     */   }
/*     */   
/*     */   public static int compare(int a, int b)
/*     */   {
/* 171 */     return a < b ? -1 : a == b ? 0 : 1;
/*     */   }
/*     */   
/*     */   public static int compare(double a, double b)
/*     */   {
/* 176 */     return a < b ? -1 : a == b ? 0 : 1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/math/MathHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */