/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectSource;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ 
/*     */ 
/*     */ public class TileJarFillable
/*     */   extends TileJar
/*     */   implements IAspectSource, IEssentiaTransport
/*     */ {
/*  18 */   public Aspect aspect = null;
/*  19 */   public Aspect aspectFilter = null;
/*  20 */   public int amount = 0;
/*  21 */   public int maxAmount = 64;
/*  22 */   public int facing = 2;
/*     */   
/*     */ 
/*  25 */   public boolean forgeLiquid = false;
/*  26 */   public int lid = 0;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  30 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  38 */     this.aspect = Aspect.getAspect(nbttagcompound.func_74779_i("Aspect"));
/*  39 */     this.aspectFilter = Aspect.getAspect(nbttagcompound.func_74779_i("AspectFilter"));
/*  40 */     this.amount = nbttagcompound.func_74765_d("Amount");
/*  41 */     this.facing = nbttagcompound.func_74771_c("facing");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  47 */     if (this.aspect != null) nbttagcompound.func_74778_a("Aspect", this.aspect.getTag());
/*  48 */     if (this.aspectFilter != null) nbttagcompound.func_74778_a("AspectFilter", this.aspectFilter.getTag());
/*  49 */     nbttagcompound.func_74777_a("Amount", (short)this.amount);
/*  50 */     nbttagcompound.func_74774_a("facing", (byte)this.facing);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/*  57 */     AspectList al = new AspectList();
/*  58 */     if ((this.aspect != null) && (this.amount > 0)) al.add(this.aspect, this.amount);
/*  59 */     return al;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  69 */     if (am == 0) return am;
/*  70 */     if (((this.amount < this.maxAmount) && (tt == this.aspect)) || (this.amount == 0))
/*     */     {
/*  72 */       this.aspect = tt;
/*  73 */       int added = Math.min(am, this.maxAmount - this.amount);
/*  74 */       this.amount += added;
/*  75 */       am -= added;
/*     */     }
/*  77 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  78 */     func_70296_d();
/*  79 */     return am;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/*  84 */     if ((this.amount >= am) && (tt == this.aspect)) {
/*  85 */       this.amount -= am;
/*  86 */       if (this.amount <= 0) {
/*  87 */         this.aspect = null;
/*  88 */         this.amount = 0;
/*     */       }
/*  90 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  91 */       func_70296_d();
/*  92 */       return true;
/*     */     }
/*  94 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/*  99 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amt)
/*     */   {
/* 105 */     if ((this.amount >= amt) && (tag == this.aspect)) return true;
/* 106 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 111 */     for (Aspect tt : ot.getAspects()) {
/* 112 */       if ((this.amount > 0) && (tt == this.aspect)) return true;
/*     */     }
/* 114 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 119 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 124 */     return this.aspectFilter != null ? tag.equals(this.aspectFilter) : true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 132 */     return face == ForgeDirection.UP;
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 137 */     return face == ForgeDirection.UP;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 142 */     return face == ForgeDirection.UP;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 150 */     return true;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 155 */     return this.aspectFilter != null ? 64 : 32;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection loc)
/*     */   {
/* 160 */     return this.aspectFilter != null ? this.aspectFilter : this.aspect;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection loc)
/*     */   {
/* 165 */     if (this.amount < this.maxAmount) {
/* 166 */       if (this.aspectFilter != null) {
/* 167 */         return 64;
/*     */       }
/* 169 */       return 32;
/*     */     }
/*     */     
/* 172 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 178 */     return this.aspect;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 183 */     return this.amount;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 188 */     return (canOutputTo(face)) && (takeFromContainer(aspect, amount)) ? amount : 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 193 */     return canInputFrom(face) ? amount - addToContainer(aspect, amount) : 0;
/*     */   }
/*     */   
/* 196 */   int count = 0;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 200 */     super.func_145845_h();
/* 201 */     if ((!this.field_145850_b.field_72995_K) && (++this.count % 5 == 0) && (this.amount < this.maxAmount)) {
/* 202 */       fillJar();
/*     */     }
/*     */   }
/*     */   
/*     */   void fillJar() {
/* 207 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, ForgeDirection.UP);
/* 208 */     if (te != null) {
/* 209 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 210 */       if (!ic.canOutputTo(ForgeDirection.DOWN)) { return;
/*     */       }
/* 212 */       Aspect ta = null;
/* 213 */       if (this.aspectFilter != null) {
/* 214 */         ta = this.aspectFilter;
/*     */       }
/* 216 */       else if ((this.aspect != null) && (this.amount > 0)) {
/* 217 */         ta = this.aspect;
/*     */       }
/* 219 */       else if ((ic.getEssentiaAmount(ForgeDirection.DOWN) > 0) && 
/* 220 */         (ic.getSuctionAmount(ForgeDirection.DOWN) < getSuctionAmount(ForgeDirection.UP)) && (getSuctionAmount(ForgeDirection.UP) >= ic.getMinimumSuction()))
/*     */       {
/* 222 */         ta = ic.getEssentiaType(ForgeDirection.DOWN);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 228 */       if ((ta != null) && (ic.getSuctionAmount(ForgeDirection.DOWN) < getSuctionAmount(ForgeDirection.UP))) {
/* 229 */         addToContainer(ta, ic.takeEssentia(ta, 1, ForgeDirection.DOWN));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileJarFillable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */