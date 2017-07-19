/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectSource;
/*     */ import thaumcraft.common.lib.events.EssentiaHandler;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class TileMirrorEssentia
/*     */   extends TileThaumcraft implements IAspectSource
/*     */ {
/*  20 */   public boolean linked = false;
/*     */   public int linkX;
/*     */   public int linkY;
/*     */   public int linkZ;
/*     */   public int linkDim;
/*  25 */   public ForgeDirection linkedFacing = ForgeDirection.UNKNOWN;
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  30 */     this.linked = nbttagcompound.func_74767_n("linked");
/*  31 */     this.linkX = nbttagcompound.func_74762_e("linkX");
/*  32 */     this.linkY = nbttagcompound.func_74762_e("linkY");
/*  33 */     this.linkZ = nbttagcompound.func_74762_e("linkZ");
/*  34 */     this.linkDim = nbttagcompound.func_74762_e("linkDim");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  40 */     nbttagcompound.func_74757_a("linked", this.linked);
/*  41 */     nbttagcompound.func_74768_a("linkX", this.linkX);
/*  42 */     nbttagcompound.func_74768_a("linkY", this.linkY);
/*  43 */     nbttagcompound.func_74768_a("linkZ", this.linkZ);
/*  44 */     nbttagcompound.func_74768_a("linkDim", this.linkDim);
/*     */   }
/*     */   
/*     */   public void restoreLink()
/*     */   {
/*  49 */     if (isDestinationValid()) {
/*  50 */       World targetWorld = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
/*  51 */       if (targetWorld == null) return;
/*  52 */       TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/*  53 */       if ((te != null) && ((te instanceof TileMirrorEssentia))) {
/*  54 */         TileMirrorEssentia tm = (TileMirrorEssentia)te;
/*  55 */         tm.linked = true;
/*  56 */         tm.linkX = this.field_145851_c;
/*  57 */         tm.linkY = this.field_145848_d;
/*  58 */         tm.linkZ = this.field_145849_e;
/*  59 */         tm.linkDim = this.field_145850_b.field_73011_w.field_76574_g;
/*  60 */         targetWorld.func_147471_g(tm.field_145851_c, tm.field_145848_d, tm.field_145849_e);
/*  61 */         this.linkedFacing = ForgeDirection.getOrientation(targetWorld.func_72805_g(this.linkX, this.linkY, this.linkZ));
/*  62 */         this.linked = true;
/*  63 */         func_70296_d();
/*  64 */         tm.func_70296_d();
/*  65 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void invalidateLink() {
/*  71 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/*  72 */     if (targetWorld == null) return;
/*  73 */     if (!Utils.isChunkLoaded(targetWorld, this.linkX, this.linkZ)) return;
/*  74 */     TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/*  75 */     if ((te != null) && ((te instanceof TileMirrorEssentia))) {
/*  76 */       TileMirrorEssentia tm = (TileMirrorEssentia)te;
/*  77 */       tm.linked = false;
/*  78 */       tm.linkedFacing = ForgeDirection.UNKNOWN;
/*  79 */       func_70296_d();
/*  80 */       tm.func_70296_d();
/*  81 */       targetWorld.func_147471_g(this.linkX, this.linkY, this.linkZ);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isLinkValid()
/*     */   {
/*  87 */     if (!this.linked) return false;
/*  88 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/*  89 */     if (targetWorld == null) {
/*  90 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  95 */     TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/*  96 */     if ((te == null) || (!(te instanceof TileMirrorEssentia))) {
/*  97 */       this.linked = false;
/*  98 */       func_70296_d();
/*  99 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 100 */       return false;
/*     */     }
/* 102 */     TileMirrorEssentia tm = (TileMirrorEssentia)te;
/* 103 */     if (!tm.linked) {
/* 104 */       this.linked = false;
/* 105 */       func_70296_d();
/* 106 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 107 */       return false;
/*     */     }
/* 109 */     if ((tm.linkX != this.field_145851_c) || (tm.linkY != this.field_145848_d) || (tm.linkZ != this.field_145849_e) || (tm.linkDim != this.field_145850_b.field_73011_w.field_76574_g))
/*     */     {
/*     */ 
/* 112 */       this.linked = false;
/* 113 */       func_70296_d();
/* 114 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 115 */       return false;
/*     */     }
/* 117 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isLinkValidSimple() {
/* 121 */     if (!this.linked) return false;
/* 122 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/* 123 */     if (targetWorld == null) {
/* 124 */       return false;
/*     */     }
/*     */     
/* 127 */     TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/* 128 */     if ((te == null) || (!(te instanceof TileMirrorEssentia))) {
/* 129 */       return false;
/*     */     }
/* 131 */     TileMirrorEssentia tm = (TileMirrorEssentia)te;
/* 132 */     if (!tm.linked) {
/* 133 */       return false;
/*     */     }
/* 135 */     if ((tm.linkX != this.field_145851_c) || (tm.linkY != this.field_145848_d) || (tm.linkZ != this.field_145849_e) || (tm.linkDim != this.field_145850_b.field_73011_w.field_76574_g))
/*     */     {
/*     */ 
/* 138 */       return false;
/*     */     }
/* 140 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isDestinationValid() {
/* 144 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/* 145 */     if (targetWorld == null) {
/* 146 */       return false;
/*     */     }
/*     */     
/* 149 */     TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/* 150 */     if ((te == null) || (!(te instanceof TileMirrorEssentia))) {
/* 151 */       this.linked = false;
/* 152 */       func_70296_d();
/* 153 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 154 */       return false;
/*     */     }
/*     */     
/* 157 */     TileMirrorEssentia tm = (TileMirrorEssentia)te;
/* 158 */     if (tm.isLinkValid()) {
/* 159 */       return false;
/*     */     }
/* 161 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/* 170 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 178 */     return false;
/*     */   }
/*     */   
/*     */   public int addToContainer(Aspect tag, int amount)
/*     */   {
/* 183 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tag, int amount)
/*     */   {
/* 188 */     if ((!isLinkValid()) || (amount > 1)) { return false;
/*     */     }
/* 190 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/* 191 */     if ((this.linkedFacing == ForgeDirection.UNKNOWN) && 
/* 192 */       (targetWorld != null)) {
/* 193 */       this.linkedFacing = ForgeDirection.getOrientation(targetWorld.func_72805_g(this.linkX, this.linkY, this.linkZ) % 6);
/*     */     }
/*     */     
/*     */ 
/* 197 */     TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/* 198 */     if ((te != null) && ((te instanceof TileMirrorEssentia))) {
/* 199 */       return EssentiaHandler.drainEssentia(te, tag, this.linkedFacing, 8, true);
/*     */     }
/*     */     
/* 202 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 207 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amount)
/*     */   {
/* 213 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 218 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 223 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/* 228 */     return true;
/*     */   }
/*     */   
/* 231 */   int count = 0;
/* 232 */   int inc = 40;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 236 */     if ((!this.field_145850_b.field_72995_K) && (this.count++ % this.inc == 0)) {
/* 237 */       if (!isLinkValidSimple()) {
/* 238 */         if (this.inc < 600) this.inc += 20;
/* 239 */         restoreLink();
/*     */       } else {
/* 241 */         this.inc = 40;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileMirrorEssentia.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */