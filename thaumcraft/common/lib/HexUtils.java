/*     */ package thaumcraft.common.lib;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class HexUtils
/*     */ {
/*   9 */   static final int[][] NEIGHBOURS = { { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 } };
/*     */   
/*     */ 
/*     */ 
/*     */   public static class Hex
/*     */   {
/*  15 */     public int q = 0;
/*  16 */     public int r = 0;
/*     */     
/*     */     public Hex(int q, int r)
/*     */     {
/*  20 */       this.q = q;
/*  21 */       this.r = r;
/*     */     }
/*     */     
/*     */     public HexUtils.CubicHex toCubicHex() {
/*  25 */       return new HexUtils.CubicHex(this.q, this.r, -this.q - this.r);
/*     */     }
/*     */     
/*     */     public HexUtils.Pixel toPixel(int size) {
/*  29 */       return new HexUtils.Pixel(size * 1.5D * this.q, size * Math.sqrt(3.0D) * (this.r + this.q / 2.0D));
/*     */     }
/*     */     
/*     */     public Hex getNeighbour(int direction) {
/*  33 */       int[] d = HexUtils.NEIGHBOURS[direction];
/*  34 */       return new Hex(this.q + d[0], this.r + d[1]);
/*     */     }
/*     */     
/*     */     public boolean equals(Hex h) {
/*  38 */       return (h.q == this.q) && (h.r == this.r);
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/*  43 */       return this.q + ":" + this.r;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class CubicHex {
/*  48 */     public int x = 0;
/*  49 */     public int y = 0;
/*  50 */     public int z = 0;
/*     */     
/*     */     public CubicHex(int x, int y, int z)
/*     */     {
/*  54 */       this.x = x;
/*  55 */       this.y = y;
/*  56 */       this.z = z;
/*     */     }
/*     */     
/*     */     public HexUtils.Hex toHex() {
/*  60 */       return new HexUtils.Hex(this.x, this.z);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Pixel {
/*  65 */     public double x = 0.0D;
/*  66 */     public double y = 0.0D;
/*     */     
/*     */     public Pixel(double x, double y)
/*     */     {
/*  70 */       this.x = x;
/*  71 */       this.y = y;
/*     */     }
/*     */     
/*     */     public HexUtils.Hex toHex(int size) {
/*  75 */       double qq = 0.6666666666666666D * this.x / size;
/*  76 */       double rr = (0.3333333333333333D * Math.sqrt(3.0D) * -this.y - 0.3333333333333333D * this.x) / size;
/*  77 */       return HexUtils.getRoundedHex(qq, rr);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static int getDistance(Hex a1, Hex a2)
/*     */   {
/*  84 */     return (Math.abs(a1.q - a2.q) + Math.abs(a1.r - a2.r) + Math.abs(a1.q + a1.r - a2.q - a2.r)) / 2;
/*     */   }
/*     */   
/*     */   public static Hex getRoundedHex(double qq, double rr) {
/*  88 */     return getRoundedCubicHex(qq, rr, -qq - rr).toHex();
/*     */   }
/*     */   
/*     */   public static CubicHex getRoundedCubicHex(double xx, double yy, double zz) {
/*  92 */     int rx = (int)Math.round(xx);
/*  93 */     int ry = (int)Math.round(yy);
/*  94 */     int rz = (int)Math.round(zz);
/*     */     
/*  96 */     double x_diff = Math.abs(rx - xx);
/*  97 */     double y_diff = Math.abs(ry - yy);
/*  98 */     double z_diff = Math.abs(rz - zz);
/*     */     
/* 100 */     if ((x_diff > y_diff) && (x_diff > z_diff)) {
/* 101 */       rx = -ry - rz;
/*     */     }
/* 103 */     else if (y_diff > z_diff) {
/* 104 */       ry = -rx - rz;
/*     */     } else {
/* 106 */       rz = -rx - ry;
/*     */     }
/* 108 */     return new CubicHex(rx, ry, rz);
/*     */   }
/*     */   
/*     */   public static ArrayList<Hex> getRing(int radius) {
/* 112 */     Hex h = new Hex(0, 0);
/* 113 */     for (int k = 0; k < radius; k++) {
/* 114 */       h = h.getNeighbour(4);
/*     */     }
/*     */     
/* 117 */     ArrayList<Hex> ring = new ArrayList();
/* 118 */     for (int i = 0; i < 6; i++) {
/* 119 */       for (int j = 0; j < radius; j++) {
/* 120 */         ring.add(h);
/* 121 */         h = h.getNeighbour(i);
/*     */       }
/*     */     }
/*     */     
/* 125 */     return ring;
/*     */   }
/*     */   
/*     */   public static ArrayList<Hex> distributeRingRandomly(int radius, int entries, Random random) {
/* 129 */     ArrayList<Hex> ring = getRing(radius);
/* 130 */     ArrayList<Hex> results = new ArrayList();
/* 131 */     float spacing = ring.size() / entries;
/* 132 */     int start = random.nextInt(ring.size());
/* 133 */     float pos = 0.0F;
/* 134 */     for (int i = 0; i < entries; i++) {
/* 135 */       results.add(ring.get(Math.round(pos)));
/* 136 */       pos += spacing;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 146 */     return results;
/*     */   }
/*     */   
/*     */   public static HashMap<String, Hex> generateHexes(int radius) {
/* 150 */     HashMap<String, Hex> results = new HashMap();
/* 151 */     Hex h = new Hex(0, 0);
/* 152 */     results.put(h.toString(), h);
/* 153 */     for (int k = 0; k < radius; k++) {
/* 154 */       h = h.getNeighbour(4);
/* 155 */       Hex hd = new Hex(h.q, h.r);
/* 156 */       for (int i = 0; i < 6; i++) {
/* 157 */         for (int j = 0; j <= k; j++) {
/* 158 */           results.put(hd.toString(), hd);
/* 159 */           hd = hd.getNeighbour(i);
/*     */         }
/*     */       }
/*     */     }
/* 163 */     return results;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/HexUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */