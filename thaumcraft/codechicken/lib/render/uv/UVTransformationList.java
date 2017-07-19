/*     */ package thaumcraft.codechicken.lib.render.uv;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class UVTransformationList extends UVTransformation
/*     */ {
/*   8 */   private ArrayList<UVTransformation> transformations = new ArrayList();
/*     */   
/*     */   public UVTransformationList(UVTransformation... transforms)
/*     */   {
/*  12 */     for (UVTransformation t : transforms) {
/*  13 */       if ((t instanceof UVTransformationList)) {
/*  14 */         this.transformations.addAll(((UVTransformationList)t).transformations);
/*     */       } else
/*  16 */         this.transformations.add(t);
/*     */     }
/*  18 */     compact();
/*     */   }
/*     */   
/*     */ 
/*     */   public void apply(UV uv)
/*     */   {
/*  24 */     for (int i = 0; i < this.transformations.size(); i++) {
/*  25 */       ((UVTransformation)this.transformations.get(i)).apply(uv);
/*     */     }
/*     */   }
/*     */   
/*     */   public UVTransformationList with(UVTransformation t)
/*     */   {
/*  31 */     if (t.isRedundant()) {
/*  32 */       return this;
/*     */     }
/*  34 */     if ((t instanceof UVTransformationList)) {
/*  35 */       this.transformations.addAll(((UVTransformationList)t).transformations);
/*     */     } else {
/*  37 */       this.transformations.add(t);
/*     */     }
/*  39 */     compact();
/*  40 */     return this;
/*     */   }
/*     */   
/*     */   public UVTransformationList prepend(UVTransformation t)
/*     */   {
/*  45 */     if (t.isRedundant()) {
/*  46 */       return this;
/*     */     }
/*  48 */     if ((t instanceof UVTransformationList)) {
/*  49 */       this.transformations.addAll(0, ((UVTransformationList)t).transformations);
/*     */     } else {
/*  51 */       this.transformations.add(0, t);
/*     */     }
/*  53 */     compact();
/*  54 */     return this;
/*     */   }
/*     */   
/*     */   private void compact() {
/*  58 */     ArrayList<UVTransformation> newList = new ArrayList(this.transformations.size());
/*  59 */     Iterator<UVTransformation> iterator = this.transformations.iterator();
/*  60 */     UVTransformation prev = null;
/*  61 */     while (iterator.hasNext()) {
/*  62 */       UVTransformation t = (UVTransformation)iterator.next();
/*  63 */       if (!t.isRedundant())
/*     */       {
/*     */ 
/*  66 */         if (prev != null) {
/*  67 */           UVTransformation m = (UVTransformation)prev.merge(t);
/*  68 */           if (m == null) {
/*  69 */             newList.add(prev);
/*  70 */           } else if (m.isRedundant()) {
/*  71 */             t = null;
/*     */           } else
/*  73 */             t = m;
/*     */         }
/*  75 */         prev = t;
/*     */       } }
/*  77 */     if (prev != null) {
/*  78 */       newList.add(prev);
/*     */     }
/*  80 */     if (newList.size() < this.transformations.size()) {
/*  81 */       this.transformations = newList;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isRedundant() {
/*  86 */     return this.transformations.size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public UVTransformation inverse()
/*     */   {
/*  92 */     UVTransformationList rev = new UVTransformationList(new UVTransformation[0]);
/*  93 */     for (int i = this.transformations.size() - 1; i >= 0; i--)
/*  94 */       rev.with((UVTransformation)((UVTransformation)this.transformations.get(i)).inverse());
/*  95 */     return rev;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 101 */     String s = "";
/* 102 */     for (UVTransformation t : this.transformations)
/* 103 */       s = s + "\n" + t.toString();
/* 104 */     return s.trim();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/render/uv/UVTransformationList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */