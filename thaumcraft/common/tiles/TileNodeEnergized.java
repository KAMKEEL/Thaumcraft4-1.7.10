/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.nodes.NodeModifier;
/*     */ import thaumcraft.api.nodes.NodeType;
/*     */ import thaumcraft.api.visnet.TileVisNode;
/*     */ 
/*     */ public class TileNodeEnergized extends TileVisNode implements thaumcraft.api.aspects.IAspectContainer
/*     */ {
/*  16 */   private AspectList auraBase = new AspectList().add(Aspect.AIR, 20).add(Aspect.FIRE, 20).add(Aspect.EARTH, 20).add(Aspect.WATER, 20).add(Aspect.ORDER, 20).add(Aspect.ENTROPY, 20);
/*  17 */   AspectList visBase = new AspectList();
/*  18 */   AspectList vis = new AspectList();
/*     */   
/*  20 */   private NodeType nodeType = NodeType.NORMAL;
/*  21 */   private NodeModifier nodeModifier = null;
/*  22 */   String id = "blank";
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/*  26 */     super.func_145845_h();
/*     */     
/*  28 */     if (!this.field_145850_b.field_72995_K) {
/*  29 */       if ((getNodeType() == NodeType.UNSTABLE) && (this.field_145850_b.field_73012_v.nextInt(500) == 1)) {
/*  30 */         this.visBase = new AspectList();
/*     */       }
/*  32 */       if ((this.visBase.size() == 0) && (getAuraBase().size() > 0)) {
/*  33 */         setupNode();
/*     */       }
/*     */       
/*     */ 
/*  37 */       this.vis = this.visBase.copy();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  43 */     return true;
/*     */   }
/*     */   
/*     */   public void setupNode() {
/*  47 */     this.visBase = new AspectList();
/*  48 */     AspectList temp = thaumcraft.common.lib.research.ResearchManager.reduceToPrimals(getAuraBase(), true);
/*  49 */     for (Aspect aspect : temp.getAspects()) {
/*  50 */       int amt = temp.getAmount(aspect);
/*  51 */       if (getNodeModifier() == NodeModifier.BRIGHT) amt = (int)(amt * 1.2F);
/*  52 */       if (getNodeModifier() == NodeModifier.PALE) amt = (int)(amt * 0.8F);
/*  53 */       if (getNodeModifier() == NodeModifier.FADING) amt = (int)(amt * 0.5F);
/*  54 */       amt = MathHelper.func_76128_c(MathHelper.func_76133_a(amt));
/*  55 */       if (getNodeType() == NodeType.UNSTABLE) {
/*  56 */         amt += this.field_145850_b.field_73012_v.nextInt(5) - 2;
/*     */       }
/*  58 */       if (amt >= 1) {
/*  59 */         this.visBase.merge(aspect, amt);
/*     */       }
/*     */     }
/*  62 */     func_70296_d();
/*  63 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/*  68 */     super.func_145839_a(nbttagcompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/*  74 */     super.func_145841_b(nbttagcompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  82 */     this.id = nbttagcompound.func_74779_i("nodeId");
/*     */     
/*  84 */     setNodeType(NodeType.values()[nbttagcompound.func_74771_c("type")]);
/*  85 */     byte mod = nbttagcompound.func_74771_c("modifier");
/*  86 */     if (mod >= 0) {
/*  87 */       setNodeModifier(NodeModifier.values()[mod]);
/*     */     } else {
/*  89 */       setNodeModifier(null);
/*     */     }
/*  91 */     this.visBase.aspects.clear();
/*  92 */     NBTTagList tlist = nbttagcompound.func_150295_c("AEB", 10);
/*  93 */     for (int j = 0; j < tlist.func_74745_c(); j++) {
/*  94 */       NBTTagCompound rs = tlist.func_150305_b(j);
/*  95 */       if (rs.func_74764_b("key")) {
/*  96 */         this.visBase.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 101 */     getAuraBase().readFromNBT(nbttagcompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 107 */     nbttagcompound.func_74778_a("nodeId", this.id);
/*     */     
/* 109 */     nbttagcompound.func_74774_a("type", (byte)getNodeType().ordinal());
/* 110 */     nbttagcompound.func_74774_a("modifier", getNodeModifier() == null ? -1 : (byte)getNodeModifier().ordinal());
/*     */     
/* 112 */     NBTTagList tlist = new NBTTagList();
/* 113 */     nbttagcompound.func_74782_a("AEB", tlist);
/* 114 */     for (Aspect aspect : this.visBase.getAspects()) {
/* 115 */       if (aspect != null) {
/* 116 */         NBTTagCompound f = new NBTTagCompound();
/* 117 */         f.func_74778_a("key", aspect.getTag());
/* 118 */         f.func_74768_a("amount", this.visBase.getAmount(aspect));
/* 119 */         tlist.func_74742_a(f);
/*     */       }
/*     */     }
/* 122 */     getAuraBase().writeToNBT(nbttagcompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 129 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */   public NodeType getNodeType() {
/* 133 */     return this.nodeType;
/*     */   }
/*     */   
/*     */   public void setNodeType(NodeType nodeType) {
/* 137 */     this.nodeType = nodeType;
/*     */   }
/*     */   
/*     */   public void setNodeModifier(NodeModifier nodeModifier) {
/* 141 */     this.nodeModifier = nodeModifier;
/*     */   }
/*     */   
/*     */   public NodeModifier getNodeModifier() {
/* 145 */     return this.nodeModifier;
/*     */   }
/*     */   
/*     */   public int getRange()
/*     */   {
/* 150 */     return 8;
/*     */   }
/*     */   
/*     */   public boolean isSource()
/*     */   {
/* 155 */     return true;
/*     */   }
/*     */   
/*     */   public int consumeVis(Aspect aspect, int amount)
/*     */   {
/* 160 */     int drain = Math.min(this.vis.getAmount(aspect), amount);
/* 161 */     if (drain > 0) this.vis.reduce(aspect, drain);
/* 162 */     return drain;
/*     */   }
/*     */   
/*     */   public AspectList getAuraBase() {
/* 166 */     return this.auraBase;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 171 */     return this.visBase;
/*     */   }
/*     */   
/*     */   public void setAspects(AspectList aspects)
/*     */   {
/* 176 */     this.auraBase = aspects;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 181 */     return false;
/*     */   }
/*     */   
/*     */   public int addToContainer(Aspect tag, int amount)
/*     */   {
/* 186 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tag, int amount)
/*     */   {
/* 191 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 196 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amount)
/*     */   {
/* 201 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 206 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 211 */     return 0;
/*     */   }
/*     */   
/*     */   public byte getAttunement()
/*     */   {
/* 216 */     return -1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileNodeEnergized.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */