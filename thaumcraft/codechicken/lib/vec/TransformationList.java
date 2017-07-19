/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class TransformationList
/*     */   extends Transformation
/*     */ {
/*  11 */   private ArrayList<Transformation> transformations = new ArrayList();
/*     */   private Matrix4 mat;
/*     */   
/*     */   public TransformationList(Transformation... transforms)
/*     */   {
/*  16 */     for (Transformation t : transforms) {
/*  17 */       if ((t instanceof TransformationList)) {
/*  18 */         this.transformations.addAll(((TransformationList)t).transformations);
/*     */       } else
/*  20 */         this.transformations.add(t);
/*     */     }
/*  22 */     compact();
/*     */   }
/*     */   
/*     */   public Matrix4 compile()
/*     */   {
/*  27 */     if (this.mat == null)
/*     */     {
/*  29 */       this.mat = new Matrix4();
/*  30 */       for (int i = this.transformations.size() - 1; i >= 0; i--)
/*  31 */         ((Transformation)this.transformations.get(i)).apply(this.mat);
/*     */     }
/*  33 */     return this.mat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Matrix4 reverseCompile()
/*     */   {
/*  42 */     Matrix4 mat = new Matrix4();
/*  43 */     for (Transformation t : this.transformations)
/*  44 */       t.apply(mat);
/*  45 */     return mat;
/*     */   }
/*     */   
/*     */ 
/*     */   public void apply(Vector3 vec)
/*     */   {
/*  51 */     if (this.mat != null) {
/*  52 */       this.mat.apply(vec);
/*     */     } else {
/*  54 */       for (int i = 0; i < this.transformations.size(); i++) {
/*  55 */         ((Transformation)this.transformations.get(i)).apply(vec);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void applyN(Vector3 normal) {
/*  61 */     if (this.mat != null) {
/*  62 */       this.mat.applyN(normal);
/*     */     } else {
/*  64 */       for (int i = 0; i < this.transformations.size(); i++) {
/*  65 */         ((Transformation)this.transformations.get(i)).applyN(normal);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void apply(Matrix4 mat) {
/*  71 */     mat.multiply(compile());
/*     */   }
/*     */   
/*     */ 
/*     */   public TransformationList with(Transformation t)
/*     */   {
/*  77 */     if (t.isRedundant()) {
/*  78 */       return this;
/*     */     }
/*  80 */     this.mat = null;
/*  81 */     if ((t instanceof TransformationList)) {
/*  82 */       this.transformations.addAll(((TransformationList)t).transformations);
/*     */     } else {
/*  84 */       this.transformations.add(t);
/*     */     }
/*  86 */     compact();
/*  87 */     return this;
/*     */   }
/*     */   
/*     */   public TransformationList prepend(Transformation t)
/*     */   {
/*  92 */     if (t.isRedundant()) {
/*  93 */       return this;
/*     */     }
/*  95 */     this.mat = null;
/*  96 */     if ((t instanceof TransformationList)) {
/*  97 */       this.transformations.addAll(0, ((TransformationList)t).transformations);
/*     */     } else {
/*  99 */       this.transformations.add(0, t);
/*     */     }
/* 101 */     compact();
/* 102 */     return this;
/*     */   }
/*     */   
/*     */   private void compact() {
/* 106 */     ArrayList<Transformation> newList = new ArrayList(this.transformations.size());
/* 107 */     Iterator<Transformation> iterator = this.transformations.iterator();
/* 108 */     Transformation prev = null;
/* 109 */     while (iterator.hasNext()) {
/* 110 */       Transformation t = (Transformation)iterator.next();
/* 111 */       if (!t.isRedundant())
/*     */       {
/*     */ 
/* 114 */         if (prev != null) {
/* 115 */           Transformation m = (Transformation)prev.merge(t);
/* 116 */           if (m == null) {
/* 117 */             newList.add(prev);
/* 118 */           } else if (m.isRedundant()) {
/* 119 */             t = null;
/*     */           } else
/* 121 */             t = m;
/*     */         }
/* 123 */         prev = t;
/*     */       } }
/* 125 */     if (prev != null) {
/* 126 */       newList.add(prev);
/*     */     }
/* 128 */     if (newList.size() < this.transformations.size()) {
/* 129 */       this.transformations = newList;
/* 130 */       this.mat = null;
/*     */     }
/*     */     
/* 133 */     if ((this.transformations.size() > 3) && (this.mat == null)) {
/* 134 */       compile();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isRedundant() {
/* 139 */     return this.transformations.size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void glApply()
/*     */   {
/* 146 */     for (int i = this.transformations.size() - 1; i >= 0; i--) {
/* 147 */       ((Transformation)this.transformations.get(i)).glApply();
/*     */     }
/*     */   }
/*     */   
/*     */   public Transformation inverse()
/*     */   {
/* 153 */     TransformationList rev = new TransformationList(new Transformation[0]);
/* 154 */     for (int i = this.transformations.size() - 1; i >= 0; i--)
/* 155 */       rev.with((Transformation)((Transformation)this.transformations.get(i)).inverse());
/* 156 */     return rev;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 162 */     String s = "";
/* 163 */     for (Transformation t : this.transformations)
/* 164 */       s = s + "\n" + t.toString();
/* 165 */     return s.trim();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/TransformationList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */