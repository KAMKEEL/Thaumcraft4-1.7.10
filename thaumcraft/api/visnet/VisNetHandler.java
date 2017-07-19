/*     */ package thaumcraft.api.visnet;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.WorldCoordinates;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.internal.IInternalMethodHandler;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VisNetHandler
/*     */ {
/*     */   public static int drainVis(World world, int x, int y, int z, Aspect aspect, int amount)
/*     */   {
/*  34 */     int drainedAmount = 0;
/*     */     
/*  36 */     WorldCoordinates drainer = new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g);
/*     */     
/*  38 */     if (!nearbyNodes.containsKey(drainer)) {
/*  39 */       calculateNearbyNodes(world, x, y, z);
/*     */     }
/*     */     
/*  42 */     ArrayList<WeakReference<TileVisNode>> nodes = (ArrayList)nearbyNodes.get(drainer);
/*  43 */     if ((nodes != null) && (nodes.size() > 0)) {
/*  44 */       for (WeakReference<TileVisNode> noderef : nodes)
/*     */       {
/*  46 */         TileVisNode node = (TileVisNode)noderef.get();
/*     */         
/*  48 */         if (node != null)
/*     */         {
/*  50 */           int a = node.consumeVis(aspect, amount);
/*  51 */           drainedAmount += a;
/*  52 */           amount -= a;
/*  53 */           if (a > 0) {
/*  54 */             int color = Aspect.getPrimalAspects().indexOf(aspect);
/*  55 */             generateVisEffect(world.field_73011_w.field_76574_g, x, y, z, node.field_145851_c, node.field_145848_d, node.field_145849_e, color);
/*     */           }
/*  57 */           if (amount <= 0)
/*     */             break;
/*     */         }
/*     */       }
/*     */     }
/*  62 */     return drainedAmount;
/*     */   }
/*     */   
/*     */   public static void generateVisEffect(int dim, int x, int y, int z, int x2, int y2, int z2, int color) {
/*  66 */     ThaumcraftApi.internalMethods.generateVisEffect(dim, x, y, z, x2, y2, z2, color);
/*     */   }
/*     */   
/*  69 */   public static HashMap<Integer, HashMap<WorldCoordinates, WeakReference<TileVisNode>>> sources = new HashMap();
/*     */   
/*     */   public static void addSource(World world, TileVisNode vs) {
/*  72 */     HashMap<WorldCoordinates, WeakReference<TileVisNode>> sourcelist = (HashMap)sources.get(Integer.valueOf(world.field_73011_w.field_76574_g));
/*     */     
/*  74 */     if (sourcelist == null) {
/*  75 */       sourcelist = new HashMap();
/*     */     }
/*  77 */     sourcelist.put(vs.getLocation(), new WeakReference(vs));
/*  78 */     sources.put(Integer.valueOf(world.field_73011_w.field_76574_g), sourcelist);
/*  79 */     nearbyNodes.clear();
/*     */   }
/*     */   
/*     */   public static boolean isNodeValid(WeakReference<TileVisNode> node) {
/*  83 */     if ((node == null) || (node.get() == null) || (((TileVisNode)node.get()).func_145837_r()))
/*  84 */       return false;
/*  85 */     return true;
/*     */   }
/*     */   
/*     */   public static WeakReference<TileVisNode> addNode(World world, TileVisNode vn) {
/*  89 */     WeakReference ref = new WeakReference(vn);
/*     */     
/*  91 */     HashMap<WorldCoordinates, WeakReference<TileVisNode>> sourcelist = (HashMap)sources.get(Integer.valueOf(world.field_73011_w.field_76574_g));
/*     */     
/*  93 */     if (sourcelist == null) {
/*  94 */       sourcelist = new HashMap();
/*  95 */       return null;
/*     */     }
/*     */     
/*  98 */     ArrayList<Object[]> nearby = new ArrayList();
/*     */     
/* 100 */     for (WeakReference<TileVisNode> root : sourcelist.values()) {
/* 101 */       if (isNodeValid(root))
/*     */       {
/*     */ 
/* 104 */         TileVisNode source = (TileVisNode)root.get();
/*     */         
/* 106 */         float r = inRange(world, vn.getLocation(), source.getLocation(), vn.getRange());
/*     */         
/* 108 */         if (r > 0.0F) {
/* 109 */           nearby.add(new Object[] { source, Float.valueOf(r - vn.getRange() * 2) });
/*     */         }
/*     */         
/* 112 */         nearby = findClosestNodes(vn, source, nearby);
/* 113 */         cache.clear();
/*     */       }
/*     */     }
/* 116 */     float dist = Float.MAX_VALUE;
/* 117 */     TileVisNode closest = null;
/* 118 */     if (nearby.size() > 0) {
/* 119 */       for (Object[] o : nearby) {
/* 120 */         if ((((Float)o[1]).floatValue() < dist) && ((vn.getAttunement() == -1) || (((TileVisNode)o[0]).getAttunement() == -1) || (vn.getAttunement() == ((TileVisNode)o[0]).getAttunement())) && (canNodeBeSeen(vn, (TileVisNode)o[0])))
/*     */         {
/*     */ 
/*     */ 
/* 124 */           dist = ((Float)o[1]).floatValue();
/* 125 */           closest = (TileVisNode)o[0];
/*     */         }
/*     */       }
/*     */     }
/* 129 */     if (closest != null) {
/* 130 */       closest.getChildren().add(ref);
/* 131 */       nearbyNodes.clear();
/* 132 */       return new WeakReference(closest);
/*     */     }
/*     */     
/* 135 */     return null;
/*     */   }
/*     */   
/* 138 */   static ArrayList<WorldCoordinates> cache = new ArrayList();
/*     */   
/*     */   public static ArrayList<Object[]> findClosestNodes(TileVisNode target, TileVisNode parent, ArrayList<Object[]> in)
/*     */   {
/* 142 */     if ((cache.size() > 512) || (cache.contains(new WorldCoordinates(parent)))) return in;
/* 143 */     cache.add(new WorldCoordinates(parent));
/*     */     
/* 145 */     for (WeakReference<TileVisNode> childWR : parent.getChildren()) {
/* 146 */       TileVisNode child = (TileVisNode)childWR.get();
/*     */       
/* 148 */       if ((child != null) && (!child.equals(target)) && (!child.equals(parent))) {
/* 149 */         float r2 = inRange(child.func_145831_w(), child.getLocation(), target.getLocation(), target.getRange());
/*     */         
/* 151 */         if (r2 > 0.0F) {
/* 152 */           in.add(new Object[] { child, Float.valueOf(r2) });
/*     */         }
/*     */         
/* 155 */         in = findClosestNodes(target, child, in);
/*     */       }
/*     */     }
/* 158 */     return in;
/*     */   }
/*     */   
/*     */   private static float inRange(World world, WorldCoordinates cc1, WorldCoordinates cc2, int range)
/*     */   {
/* 163 */     float distance = cc1.getDistanceSquaredToWorldCoordinates(cc2);
/* 164 */     return distance > range * range ? -1.0F : distance;
/*     */   }
/*     */   
/* 167 */   private static HashMap<WorldCoordinates, ArrayList<WeakReference<TileVisNode>>> nearbyNodes = new HashMap();
/*     */   
/*     */   private static void calculateNearbyNodes(World world, int x, int y, int z)
/*     */   {
/* 171 */     HashMap<WorldCoordinates, WeakReference<TileVisNode>> sourcelist = (HashMap)sources.get(Integer.valueOf(world.field_73011_w.field_76574_g));
/*     */     
/* 173 */     if (sourcelist == null) {
/* 174 */       sourcelist = new HashMap();
/* 175 */       return;
/*     */     }
/*     */     
/* 178 */     ArrayList<WeakReference<TileVisNode>> cn = new ArrayList();
/* 179 */     WorldCoordinates drainer = new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g);
/*     */     
/*     */ 
/* 182 */     ArrayList<Object[]> nearby = new ArrayList();
/*     */     
/* 184 */     for (WeakReference<TileVisNode> root : sourcelist.values())
/*     */     {
/* 186 */       if (isNodeValid(root))
/*     */       {
/*     */ 
/* 189 */         TileVisNode source = (TileVisNode)root.get();
/*     */         
/* 191 */         TileVisNode closest = null;
/* 192 */         float range = Float.MAX_VALUE;
/*     */         
/* 194 */         float r = inRange(world, drainer, source.getLocation(), source.getRange());
/*     */         
/* 196 */         if (r > 0.0F) {
/* 197 */           range = r;
/* 198 */           closest = source;
/*     */         }
/*     */         
/* 201 */         ArrayList<WeakReference<TileVisNode>> children = new ArrayList();
/* 202 */         children = getAllChildren(source, children);
/*     */         
/* 204 */         for (WeakReference<TileVisNode> child : children) {
/* 205 */           TileVisNode n = (TileVisNode)child.get();
/* 206 */           if ((n != null) && (!n.equals(root)))
/*     */           {
/* 208 */             float r2 = inRange(n.func_145831_w(), n.getLocation(), drainer, n.getRange());
/*     */             
/* 210 */             if ((r2 > 0.0F) && (r2 < range)) {
/* 211 */               range = r2;
/* 212 */               closest = n;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 217 */         if (closest != null)
/*     */         {
/* 219 */           cn.add(new WeakReference(closest));
/*     */         }
/*     */       }
/*     */     }
/* 223 */     nearbyNodes.put(drainer, cn);
/*     */   }
/*     */   
/*     */   private static ArrayList<WeakReference<TileVisNode>> getAllChildren(TileVisNode source, ArrayList<WeakReference<TileVisNode>> list) {
/* 227 */     for (WeakReference<TileVisNode> child : source.getChildren()) {
/* 228 */       TileVisNode n = (TileVisNode)child.get();
/*     */       
/* 230 */       if ((n != null) && (n.func_145831_w() != null) && (isChunkLoaded(n.func_145831_w(), n.field_145851_c, n.field_145849_e))) {
/* 231 */         list.add(child);
/* 232 */         list = getAllChildren(n, list);
/*     */       }
/*     */     }
/* 235 */     return list;
/*     */   }
/*     */   
/*     */   public static boolean isChunkLoaded(World world, int x, int z) {
/* 239 */     int xx = x >> 4;
/* 240 */     int zz = z >> 4;
/* 241 */     return world.func_72863_F().func_73149_a(xx, zz);
/*     */   }
/*     */   
/*     */   public static boolean canNodeBeSeen(TileVisNode source, TileVisNode target)
/*     */   {
/* 246 */     MovingObjectPosition mop = ThaumcraftApiHelper.rayTraceIgnoringSource(source.func_145831_w(), Vec3.func_72443_a(source.field_145851_c + 0.5D, source.field_145848_d + 0.5D, source.field_145849_e + 0.5D), Vec3.func_72443_a(target.field_145851_c + 0.5D, target.field_145848_d + 0.5D, target.field_145849_e + 0.5D), false, true, false);
/*     */     
/*     */ 
/*     */ 
/* 250 */     return (mop == null) || ((mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (mop.field_72311_b == target.field_145851_c) && (mop.field_72312_c == target.field_145848_d) && (mop.field_72309_d == target.field_145849_e));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/visnet/VisNetHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */