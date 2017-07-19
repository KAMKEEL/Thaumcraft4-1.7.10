/*     */ package thaumcraft.api.visnet;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.WorldCoordinates;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TileVisNode
/*     */   extends TileThaumcraft
/*     */ {
/*  20 */   WeakReference<TileVisNode> parent = null;
/*  21 */   ArrayList<WeakReference<TileVisNode>> children = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */   public WorldCoordinates getLocation()
/*     */   {
/*  27 */     return new WorldCoordinates(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract int getRange();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean isSource();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int consumeVis(Aspect aspect, int vis)
/*     */   {
/*  47 */     if (VisNetHandler.isNodeValid(getParent())) {
/*  48 */       int out = ((TileVisNode)getParent().get()).consumeVis(aspect, vis);
/*  49 */       if (out > 0) {
/*  50 */         triggerConsumeEffect(aspect);
/*     */       }
/*  52 */       return out;
/*     */     }
/*  54 */     return 0;
/*     */   }
/*     */   
/*     */   public void removeThisNode() {
/*  58 */     for (WeakReference<TileVisNode> n : getChildren()) {
/*  59 */       if ((n != null) && (n.get() != null)) {
/*  60 */         ((TileVisNode)n.get()).removeThisNode();
/*     */       }
/*     */     }
/*     */     
/*  64 */     this.children = new ArrayList();
/*  65 */     if (VisNetHandler.isNodeValid(getParent())) {
/*  66 */       ((TileVisNode)getParent().get()).nodeRefresh = true;
/*     */     }
/*  68 */     setParent(null);
/*  69 */     parentChanged();
/*     */     
/*  71 */     if (isSource()) {
/*  72 */       HashMap<WorldCoordinates, WeakReference<TileVisNode>> sourcelist = (HashMap)VisNetHandler.sources.get(Integer.valueOf(this.field_145850_b.field_73011_w.field_76574_g));
/*  73 */       if (sourcelist == null) {
/*  74 */         sourcelist = new HashMap();
/*     */       }
/*  76 */       sourcelist.remove(getLocation());
/*  77 */       VisNetHandler.sources.put(Integer.valueOf(this.field_145850_b.field_73011_w.field_76574_g), sourcelist);
/*     */     }
/*     */     
/*  80 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_145843_s()
/*     */   {
/*  87 */     removeThisNode();
/*  88 */     super.func_145843_s();
/*     */   }
/*     */   
/*     */ 
/*     */   public void triggerConsumeEffect(Aspect aspect) {}
/*     */   
/*     */ 
/*     */   public WeakReference<TileVisNode> getParent()
/*     */   {
/*  97 */     return this.parent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public WeakReference<TileVisNode> getRootSource()
/*     */   {
/* 104 */     return isSource() ? new WeakReference(this) : VisNetHandler.isNodeValid(getParent()) ? ((TileVisNode)getParent().get()).getRootSource() : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setParent(WeakReference<TileVisNode> parent)
/*     */   {
/* 113 */     this.parent = parent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ArrayList<WeakReference<TileVisNode>> getChildren()
/*     */   {
/* 120 */     return this.children;
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/* 125 */     return true;
/*     */   }
/*     */   
/* 128 */   protected int nodeCounter = 0;
/* 129 */   private boolean nodeRegged = false;
/* 130 */   public boolean nodeRefresh = false;
/*     */   
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/* 135 */     if ((!this.field_145850_b.field_72995_K) && ((this.nodeCounter++ % 40 == 0) || (this.nodeRefresh)))
/*     */     {
/* 137 */       if ((!this.nodeRefresh) && (this.children.size() > 0)) {
/* 138 */         for (WeakReference<TileVisNode> n : this.children) {
/* 139 */           if ((n == null) || (n.get() == null) || (!VisNetHandler.canNodeBeSeen(this, (TileVisNode)n.get()))) {
/* 140 */             this.nodeRefresh = true;
/* 141 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 147 */       if (this.nodeRefresh) {
/* 148 */         for (WeakReference<TileVisNode> n : this.children) {
/* 149 */           if (n.get() != null) {
/* 150 */             ((TileVisNode)n.get()).nodeRefresh = true;
/*     */           }
/*     */         }
/* 153 */         this.children.clear();
/* 154 */         this.parent = null;
/*     */       }
/*     */       
/*     */ 
/* 158 */       if ((isSource()) && (!this.nodeRegged)) {
/* 159 */         VisNetHandler.addSource(func_145831_w(), this);
/* 160 */         this.nodeRegged = true;
/*     */       }
/* 162 */       else if ((!isSource()) && (!VisNetHandler.isNodeValid(getParent()))) {
/* 163 */         setParent(VisNetHandler.addNode(func_145831_w(), this));
/* 164 */         this.nodeRefresh = true;
/*     */       }
/*     */       
/* 167 */       if (this.nodeRefresh) {
/* 168 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 169 */         parentChanged();
/*     */       }
/* 171 */       this.nodeRefresh = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void parentChanged() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public byte getAttunement()
/*     */   {
/* 184 */     return -1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/visnet/TileVisNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */