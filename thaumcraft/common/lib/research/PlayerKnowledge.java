/*     */ package thaumcraft.common.lib.research;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerKnowledge
/*     */ {
/*  16 */   public Map<String, ArrayList<String>> researchCompleted = new HashMap();
/*  17 */   public Map<String, AspectList> aspectsDiscovered = new HashMap();
/*  18 */   public Map<String, ArrayList<String>> objectsScanned = new HashMap();
/*  19 */   public Map<String, ArrayList<String>> entitiesScanned = new HashMap();
/*  20 */   public Map<String, ArrayList<String>> phenomenaScanned = new HashMap();
/*  21 */   public Map<String, Integer> warpCount = new HashMap();
/*  22 */   public Map<String, Integer> warp = new HashMap();
/*  23 */   public Map<String, Integer> warpSticky = new HashMap();
/*  24 */   public Map<String, Integer> warpTemp = new HashMap();
/*     */   
/*     */   public void wipePlayerKnowledge(String player) {
/*  27 */     this.researchCompleted.remove(player);
/*  28 */     this.aspectsDiscovered.remove(player);
/*  29 */     this.objectsScanned.remove(player);
/*  30 */     this.entitiesScanned.remove(player);
/*  31 */     this.phenomenaScanned.remove(player);
/*     */     
/*  33 */     this.warp.remove(player);
/*  34 */     this.warpTemp.remove(player);
/*  35 */     this.warpSticky.remove(player);
/*     */   }
/*     */   
/*     */   public AspectList getAspectsDiscovered(String player)
/*     */   {
/*  40 */     AspectList known = (AspectList)this.aspectsDiscovered.get(player);
/*  41 */     if ((known == null) || (known.size() <= 6)) {
/*  42 */       addDiscoveredPrimalAspects(player);
/*  43 */       known = (AspectList)this.aspectsDiscovered.get(player);
/*     */     }
/*  45 */     return known;
/*     */   }
/*     */   
/*     */   public boolean hasDiscoveredAspect(String player, Aspect aspect) {
/*  49 */     return getAspectsDiscovered(player).aspects.containsKey(aspect);
/*     */   }
/*     */   
/*     */   public boolean hasDiscoveredParentAspects(String player, Aspect aspect) {
/*  53 */     if (aspect == null) return false;
/*  54 */     Aspect[] components = aspect.getComponents();
/*  55 */     if (components == null) { return true;
/*     */     }
/*  57 */     if (Arrays.asList(getAspectsDiscovered(player).getAspects()).containsAll(Arrays.asList(components))) { return true;
/*     */     }
/*  59 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addDiscoveredPrimalAspects(String player)
/*     */   {
/*  65 */     AspectList known = (AspectList)this.aspectsDiscovered.get(player);
/*  66 */     if (known == null) known = new AspectList();
/*  67 */     if (!known.aspects.containsKey(Aspect.AIR)) known.add(Aspect.AIR, 0);
/*  68 */     if (!known.aspects.containsKey(Aspect.FIRE)) known.add(Aspect.FIRE, 0);
/*  69 */     if (!known.aspects.containsKey(Aspect.EARTH)) known.add(Aspect.EARTH, 0);
/*  70 */     if (!known.aspects.containsKey(Aspect.WATER)) known.add(Aspect.WATER, 0);
/*  71 */     if (!known.aspects.containsKey(Aspect.ORDER)) known.add(Aspect.ORDER, 0);
/*  72 */     if (!known.aspects.containsKey(Aspect.ENTROPY)) known.add(Aspect.ENTROPY, 0);
/*  73 */     this.aspectsDiscovered.put(player, known);
/*     */   }
/*     */   
/*     */   public boolean addDiscoveredAspect(String player, Aspect aspect)
/*     */   {
/*  78 */     AspectList known = getAspectsDiscovered(player);
/*  79 */     if (!known.aspects.containsKey(aspect)) {
/*  80 */       known.add(aspect, 0);
/*  81 */       this.aspectsDiscovered.put(player, known);
/*     */       
/*  83 */       return true;
/*     */     }
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public short getAspectPoolFor(String username, Aspect aspect) {
/*  89 */     AspectList known = getAspectsDiscovered(username);
/*  90 */     if (known != null) return (short)known.getAmount(aspect);
/*  91 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean addAspectPool(String username, Aspect aspect, short amount) {
/*  95 */     AspectList al = getAspectsDiscovered(username);
/*  96 */     if (al == null) al = new AspectList();
/*  97 */     if ((aspect != null) && (amount != 0)) {
/*  98 */       boolean ret = false;
/*  99 */       if (amount > 0) {
/* 100 */         al.add(aspect, amount);
/* 101 */         ret = true;
/*     */       }
/* 103 */       else if (al.getAmount(aspect) > 0) {
/* 104 */         al.reduce(aspect, -amount);
/* 105 */         ret = true;
/*     */       }
/* 107 */       if (ret) this.aspectsDiscovered.put(username, al);
/* 108 */       return ret;
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setAspectPool(String username, Aspect aspect, short amount) {
/* 114 */     AspectList al = getAspectsDiscovered(username);
/* 115 */     if (al == null) al = new AspectList();
/* 116 */     if (aspect != null) {
/* 117 */       al.aspects.put(aspect, Integer.valueOf(amount));
/* 118 */       this.aspectsDiscovered.put(username, al);
/* 119 */       return true;
/*     */     }
/* 121 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getWarpCounter(String player)
/*     */   {
/* 128 */     int known = 0;
/* 129 */     if (!this.warpCount.containsKey(player)) {
/* 130 */       this.warpCount.put(player, Integer.valueOf(0));
/*     */     } else {
/* 132 */       known = ((Integer)this.warpCount.get(player)).intValue();
/*     */     }
/* 134 */     return known;
/*     */   }
/*     */   
/*     */   public void setWarpCounter(String player, int amount) {
/* 138 */     this.warpCount.put(player, Integer.valueOf(amount));
/*     */   }
/*     */   
/*     */   public int getWarpTotal(String player) {
/* 142 */     return getWarpPerm(player) + getWarpTemp(player) + getWarpSticky(player);
/*     */   }
/*     */   
/*     */   public int getWarpPerm(String player) {
/* 146 */     int known = 0;
/* 147 */     if (!this.warp.containsKey(player)) {
/* 148 */       this.warp.put(player, Integer.valueOf(0));
/*     */     } else {
/* 150 */       known = ((Integer)this.warp.get(player)).intValue();
/*     */     }
/* 152 */     return known;
/*     */   }
/*     */   
/*     */   public int getWarpTemp(String player) {
/* 156 */     int known = 0;
/* 157 */     if (!this.warpTemp.containsKey(player)) {
/* 158 */       this.warpTemp.put(player, Integer.valueOf(0));
/*     */     } else {
/* 160 */       known = ((Integer)this.warpTemp.get(player)).intValue();
/*     */     }
/* 162 */     return known;
/*     */   }
/*     */   
/*     */   public int getWarpSticky(String player) {
/* 166 */     int known = 0;
/* 167 */     if (!this.warpSticky.containsKey(player)) {
/* 168 */       this.warpSticky.put(player, Integer.valueOf(0));
/*     */     } else {
/* 170 */       known = ((Integer)this.warpSticky.get(player)).intValue();
/*     */     }
/* 172 */     return known;
/*     */   }
/*     */   
/*     */   public void addWarpTemp(String player, int amount) {
/* 176 */     int er = getWarpTemp(player) + amount;
/* 177 */     this.warpTemp.put(player, Integer.valueOf(Math.max(0, er)));
/*     */   }
/*     */   
/*     */   public void addWarpPerm(String player, int amount) {
/* 181 */     int er = getWarpPerm(player) + amount;
/* 182 */     this.warp.put(player, Integer.valueOf(Math.max(0, er)));
/*     */   }
/*     */   
/*     */   public void addWarpSticky(String player, int amount) {
/* 186 */     int er = getWarpSticky(player) + amount;
/* 187 */     this.warpSticky.put(player, Integer.valueOf(Math.max(0, er)));
/*     */   }
/*     */   
/*     */   public void setWarpSticky(String player, int amount) {
/* 191 */     this.warpSticky.put(player, Integer.valueOf(Math.max(0, amount)));
/*     */   }
/*     */   
/*     */   public void setWarpPerm(String player, int amount) {
/* 195 */     this.warp.put(player, Integer.valueOf(Math.max(0, amount)));
/*     */   }
/*     */   
/*     */   public void setWarpTemp(String player, int amount) {
/* 199 */     this.warpTemp.put(player, Integer.valueOf(Math.max(0, amount)));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/research/PlayerKnowledge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */