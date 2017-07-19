/*     */ package thaumcraft.client.fx.bolt;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.client.fx.WRVector3;
/*     */ 
/*     */ public class FXLightningBoltCommon
/*     */ {
/*     */   ArrayList segments;
/*     */   WRVector3 start;
/*     */   WRVector3 end;
/*     */   HashMap splitparents;
/*     */   public float multiplier;
/*     */   public float length;
/*     */   public int numsegments0;
/*     */   public int increment;
/*     */   
/*     */   public class BoltPoint
/*     */   {
/*     */     WRVector3 point;
/*     */     WRVector3 basepoint;
/*     */     WRVector3 offsetvec;
/*     */     final FXLightningBoltCommon this$0;
/*     */     
/*     */     public BoltPoint(WRVector3 basepoint, WRVector3 offsetvec)
/*     */     {
/*  31 */       this.this$0 = FXLightningBoltCommon.this;
/*  32 */       this.point = basepoint.copy().add(offsetvec);
/*  33 */       this.basepoint = basepoint;
/*  34 */       this.offsetvec = offsetvec; } }
/*     */   
/*     */   public class Segment { public FXLightningBoltCommon.BoltPoint startpoint;
/*     */     public FXLightningBoltCommon.BoltPoint endpoint;
/*     */     public WRVector3 diff;
/*     */     public Segment prev;
/*     */     public Segment next;
/*     */     public WRVector3 nextdiff;
/*     */     
/*  43 */     public void calcDiff() { this.diff = this.endpoint.point.copy().sub(this.startpoint.point); }
/*     */     
/*     */ 
/*     */     public void calcEndDiffs()
/*     */     {
/*  48 */       if (this.prev != null)
/*     */       {
/*  50 */         WRVector3 prevdiffnorm = this.prev.diff.copy().normalize();
/*  51 */         WRVector3 thisdiffnorm = this.diff.copy().normalize();
/*  52 */         this.prevdiff = thisdiffnorm.add(prevdiffnorm).normalize();
/*  53 */         this.sinprev = ((float)Math.sin(WRVector3.anglePreNorm(thisdiffnorm, prevdiffnorm.scale(-1.0F)) / 2.0F));
/*     */       }
/*     */       else {
/*  56 */         this.prevdiff = this.diff.copy().normalize();
/*  57 */         this.sinprev = 1.0F;
/*     */       }
/*  59 */       if (this.next != null)
/*     */       {
/*  61 */         WRVector3 nextdiffnorm = this.next.diff.copy().normalize();
/*  62 */         WRVector3 thisdiffnorm = this.diff.copy().normalize();
/*  63 */         this.nextdiff = thisdiffnorm.add(nextdiffnorm).normalize();
/*  64 */         this.sinnext = ((float)Math.sin(WRVector3.anglePreNorm(thisdiffnorm, nextdiffnorm.scale(-1.0F)) / 2.0F));
/*     */       }
/*     */       else {
/*  67 */         this.nextdiff = this.diff.copy().normalize();
/*  68 */         this.sinnext = 1.0F;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/*  75 */       return this.startpoint.point.toString() + " " + this.endpoint.point.toString();
/*     */     }
/*     */     
/*     */ 
/*     */     public WRVector3 prevdiff;
/*     */     
/*     */     public float sinprev;
/*     */     
/*     */     public float sinnext;
/*     */     
/*     */     public float light;
/*     */     
/*     */     public int segmentno;
/*     */     
/*     */     public int splitno;
/*     */     
/*     */     final FXLightningBoltCommon this$0;
/*     */     
/*     */     public Segment(FXLightningBoltCommon.BoltPoint start, FXLightningBoltCommon.BoltPoint end, float light, int segmentnumber, int splitnumber)
/*     */     {
/*  95 */       this.this$0 = FXLightningBoltCommon.this;
/*  96 */       this.startpoint = start;
/*  97 */       this.endpoint = end;
/*  98 */       this.light = light;
/*  99 */       this.segmentno = segmentnumber;
/* 100 */       this.splitno = splitnumber;
/* 101 */       calcDiff();
/*     */     }
/*     */     
/*     */     public Segment(WRVector3 start, WRVector3 end)
/*     */     {
/* 106 */       this(new FXLightningBoltCommon.BoltPoint(FXLightningBoltCommon.this, start, new WRVector3(0.0D, 0.0D, 0.0D)), new FXLightningBoltCommon.BoltPoint(FXLightningBoltCommon.this, end, new WRVector3(0.0D, 0.0D, 0.0D)), 1.0F, 0, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SegmentLightSorter implements java.util.Comparator {
/*     */     final FXLightningBoltCommon this$0;
/*     */     
/*     */     public int compare(FXLightningBoltCommon.Segment o1, FXLightningBoltCommon.Segment o2) {
/* 114 */       return Float.compare(o2.light, o1.light);
/*     */     }
/*     */     
/*     */ 
/*     */     public int compare(Object obj, Object obj1)
/*     */     {
/* 120 */       return compare((FXLightningBoltCommon.Segment)obj, (FXLightningBoltCommon.Segment)obj1);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public SegmentLightSorter()
/*     */     {
/* 128 */       this.this$0 = FXLightningBoltCommon.this;
/*     */     }
/*     */   }
/*     */   
/*     */   public class SegmentSorter implements java.util.Comparator
/*     */   {
/*     */     final FXLightningBoltCommon this$0;
/*     */     
/*     */     public int compare(FXLightningBoltCommon.Segment o1, FXLightningBoltCommon.Segment o2)
/*     */     {
/* 138 */       int comp = Integer.valueOf(o1.splitno).compareTo(Integer.valueOf(o2.splitno));
/* 139 */       if (comp == 0) {
/* 140 */         return Integer.valueOf(o1.segmentno).compareTo(Integer.valueOf(o2.segmentno));
/*     */       }
/* 142 */       return comp;
/*     */     }
/*     */     
/*     */ 
/*     */     public int compare(Object obj, Object obj1)
/*     */     {
/* 148 */       return compare((FXLightningBoltCommon.Segment)obj, (FXLightningBoltCommon.Segment)obj1);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public SegmentSorter()
/*     */     {
/* 156 */       this.this$0 = FXLightningBoltCommon.this;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public FXLightningBoltCommon(World world, WRVector3 jammervec, WRVector3 targetvec, long seed)
/*     */   {
/* 163 */     this.segments = new ArrayList();
/* 164 */     this.splitparents = new HashMap();
/* 165 */     this.world = world;
/* 166 */     this.start = jammervec;
/* 167 */     this.end = targetvec;
/* 168 */     this.seed = seed;
/* 169 */     this.rand = new Random(seed);
/* 170 */     this.numsegments0 = 1;
/* 171 */     this.increment = 1;
/* 172 */     this.length = this.end.copy().sub(this.start).length();
/* 173 */     this.particleMaxAge = (3 + this.rand.nextInt(3) - 1);
/* 174 */     this.multiplier = 1.0F;
/* 175 */     this.particleAge = (-(int)(this.length * 3.0F));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 181 */     this.segments.add(new Segment(this.start, this.end));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FXLightningBoltCommon(World world, Entity detonator, Entity target, long seed)
/*     */   {
/* 188 */     this(world, new WRVector3(detonator), new WRVector3(target), seed);
/*     */   }
/*     */   
/*     */ 
/*     */   public FXLightningBoltCommon(World world, Entity detonator, Entity target, long seed, int speed)
/*     */   {
/* 194 */     this(world, new WRVector3(detonator), new WRVector3(target.field_70165_t, target.field_70163_u + target.func_70047_e() - 0.699999988079071D, target.field_70161_v), seed);
/* 195 */     this.increment = speed;
/* 196 */     this.multiplier = 0.4F;
/*     */   }
/*     */   
/*     */   public FXLightningBoltCommon(World world, net.minecraft.tileentity.TileEntity detonator, Entity target, long seed)
/*     */   {
/* 201 */     this(world, new WRVector3(detonator), new WRVector3(target), seed);
/*     */   }
/*     */   
/*     */   public FXLightningBoltCommon(World world, net.minecraft.tileentity.TileEntity detonator, double x, double y, double z, long seed)
/*     */   {
/* 206 */     this(world, new WRVector3(detonator), new WRVector3(x, y, z), seed);
/*     */   }
/*     */   
/*     */   public FXLightningBoltCommon(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration, float multi)
/*     */   {
/* 211 */     this(world, new WRVector3(x1, y1, z1), new WRVector3(x, y, z), seed);
/* 212 */     this.particleMaxAge = (duration + this.rand.nextInt(duration) - duration / 2);
/* 213 */     this.multiplier = multi;
/*     */   }
/*     */   
/*     */   public FXLightningBoltCommon(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration, float multi, int speed)
/*     */   {
/* 218 */     this(world, new WRVector3(x1, y1, z1), new WRVector3(x, y, z), seed);
/* 219 */     this.particleMaxAge = (duration + this.rand.nextInt(duration) - duration / 2);
/* 220 */     this.multiplier = multi;
/* 221 */     this.increment = speed;
/*     */   }
/*     */   
/*     */   public void setMultiplier(float m) {
/* 225 */     this.multiplier = m;
/*     */   }
/*     */   
/*     */   public void fractal(int splits, float amount, float splitchance, float splitlength, float splitangle)
/*     */   {
/* 230 */     if (this.finalized)
/* 231 */       return;
/* 232 */     ArrayList oldsegments = this.segments;
/* 233 */     this.segments = new ArrayList();
/* 234 */     Segment prev = null;
/* 235 */     for (Iterator iterator = oldsegments.iterator(); iterator.hasNext();)
/*     */     {
/* 237 */       Segment segment = (Segment)iterator.next();
/* 238 */       prev = segment.prev;
/* 239 */       WRVector3 subsegment = segment.diff.copy().scale(1.0F / splits);
/* 240 */       BoltPoint[] newpoints = new BoltPoint[splits + 1];
/* 241 */       WRVector3 startpoint = segment.startpoint.point;
/* 242 */       newpoints[0] = segment.startpoint;
/* 243 */       newpoints[splits] = segment.endpoint;
/* 244 */       for (int i = 1; i < splits; i++)
/*     */       {
/* 246 */         WRVector3 randoff = WRVector3.getPerpendicular(segment.diff).rotate(this.rand.nextFloat() * 360.0F, segment.diff);
/* 247 */         randoff.scale((this.rand.nextFloat() - 0.5F) * amount);
/* 248 */         WRVector3 basepoint = startpoint.copy().add(subsegment.copy().scale(i));
/* 249 */         newpoints[i] = new BoltPoint(basepoint, randoff);
/*     */       }
/*     */       
/* 252 */       for (int i = 0; i < splits; i++)
/*     */       {
/* 254 */         Segment next = new Segment(newpoints[i], newpoints[(i + 1)], segment.light, segment.segmentno * splits + i, segment.splitno);
/* 255 */         next.prev = prev;
/* 256 */         if (prev != null)
/* 257 */           prev.next = next;
/* 258 */         if ((i != 0) && (this.rand.nextFloat() < splitchance))
/*     */         {
/* 260 */           WRVector3 splitrot = WRVector3.xCrossProduct(next.diff).rotate(this.rand.nextFloat() * 360.0F, next.diff);
/* 261 */           WRVector3 diff = next.diff.copy().rotate((this.rand.nextFloat() * 0.66F + 0.33F) * splitangle, splitrot).scale(splitlength);
/* 262 */           this.numsplits += 1;
/* 263 */           this.splitparents.put(Integer.valueOf(this.numsplits), Integer.valueOf(next.splitno));
/* 264 */           Segment split = new Segment(newpoints[i], new BoltPoint(newpoints[(i + 1)].basepoint, newpoints[(i + 1)].offsetvec.copy().add(diff)), segment.light / 2.0F, next.segmentno, this.numsplits);
/* 265 */           split.prev = prev;
/* 266 */           this.segments.add(split);
/*     */         }
/* 268 */         prev = next;
/* 269 */         this.segments.add(next);
/*     */       }
/*     */       
/* 272 */       if (segment.next != null) {
/* 273 */         segment.next.prev = prev;
/*     */       }
/*     */     }
/* 276 */     this.numsegments0 *= splits;
/*     */   }
/*     */   
/*     */   public void defaultFractal()
/*     */   {
/* 281 */     fractal(2, this.length * this.multiplier / 8.0F, 0.7F, 0.1F, 45.0F);
/* 282 */     fractal(2, this.length * this.multiplier / 12.0F, 0.5F, 0.1F, 50.0F);
/* 283 */     fractal(2, this.length * this.multiplier / 17.0F, 0.5F, 0.1F, 55.0F);
/* 284 */     fractal(2, this.length * this.multiplier / 23.0F, 0.5F, 0.1F, 60.0F);
/* 285 */     fractal(2, this.length * this.multiplier / 30.0F, 0.0F, 0.0F, 0.0F);
/* 286 */     fractal(2, this.length * this.multiplier / 34.0F, 0.0F, 0.0F, 0.0F);
/* 287 */     fractal(2, this.length * this.multiplier / 40.0F, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   private void calculateCollisionAndDiffs()
/*     */   {
/* 292 */     HashMap lastactivesegment = new HashMap();
/* 293 */     java.util.Collections.sort(this.segments, new SegmentSorter());
/* 294 */     int lastsplitcalc = 0;
/* 295 */     int lastactiveseg = 0;
/* 296 */     for (Iterator iterator = this.segments.iterator(); iterator.hasNext();)
/*     */     {
/* 298 */       Segment segment = (Segment)iterator.next();
/* 299 */       if (segment.splitno > lastsplitcalc)
/*     */       {
/* 301 */         lastactivesegment.put(Integer.valueOf(lastsplitcalc), Integer.valueOf(lastactiveseg));
/* 302 */         lastsplitcalc = segment.splitno;
/* 303 */         lastactiveseg = ((Integer)lastactivesegment.get(this.splitparents.get(Integer.valueOf(segment.splitno)))).intValue();
/*     */       }
/*     */       
/* 306 */       lastactiveseg = segment.segmentno;
/*     */     }
/*     */     
/* 309 */     lastactivesegment.put(Integer.valueOf(lastsplitcalc), Integer.valueOf(lastactiveseg));
/* 310 */     lastsplitcalc = 0;
/* 311 */     lastactiveseg = ((Integer)lastactivesegment.get(Integer.valueOf(0))).intValue();
/*     */     Segment segment;
/* 313 */     for (Iterator iterator = this.segments.iterator(); iterator.hasNext(); segment.calcEndDiffs())
/*     */     {
/* 315 */       segment = (Segment)iterator.next();
/* 316 */       if (lastsplitcalc != segment.splitno)
/*     */       {
/* 318 */         lastsplitcalc = segment.splitno;
/* 319 */         lastactiveseg = ((Integer)lastactivesegment.get(Integer.valueOf(segment.splitno))).intValue();
/*     */       }
/* 321 */       if (segment.segmentno > lastactiveseg) {
/* 322 */         iterator.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void finalizeBolt()
/*     */   {
/* 329 */     if (this.finalized)
/*     */     {
/* 331 */       return;
/*     */     }
/*     */     
/* 334 */     this.finalized = true;
/* 335 */     calculateCollisionAndDiffs();
/* 336 */     java.util.Collections.sort(this.segments, new SegmentLightSorter());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onUpdate()
/*     */   {
/* 343 */     this.particleAge += this.increment;
/* 344 */     if (this.particleAge > this.particleMaxAge) { this.particleAge = this.particleMaxAge;
/*     */     }
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
/*     */ 
/* 357 */   public int type = 0;
/* 358 */   public boolean nonLethal = false;
/*     */   private int numsplits;
/*     */   private boolean finalized;
/*     */   private Random rand;
/*     */   public long seed;
/*     */   public int particleAge;
/*     */   public int particleMaxAge;
/*     */   private World world;
/*     */   public static final float speed = 3.0F;
/*     */   public static final int fadetime = 20;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/bolt/FXLightningBoltCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */