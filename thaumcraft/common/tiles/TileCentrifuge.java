/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ 
/*     */ 
/*     */ public class TileCentrifuge
/*     */   extends TileThaumcraft
/*     */   implements IAspectContainer, IEssentiaTransport
/*     */ {
/*  22 */   public Aspect aspectOut = null;
/*  23 */   public Aspect aspectIn = null;
/*  24 */   public ForgeDirection facing = ForgeDirection.NORTH;
/*     */   
/*     */ 
/*     */   public boolean canUpdate()
/*     */   {
/*  29 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  36 */     this.aspectIn = Aspect.getAspect(nbttagcompound.func_74779_i("aspectIn"));
/*  37 */     this.aspectOut = Aspect.getAspect(nbttagcompound.func_74779_i("aspectOut"));
/*  38 */     this.facing = ForgeDirection.getOrientation(nbttagcompound.func_74762_e("facing"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  44 */     if (this.aspectIn != null) nbttagcompound.func_74778_a("aspectIn", this.aspectIn.getTag());
/*  45 */     if (this.aspectOut != null) nbttagcompound.func_74778_a("aspectOut", this.aspectOut.getTag());
/*  46 */     nbttagcompound.func_74768_a("facing", this.facing.ordinal());
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/*  52 */     AspectList al = new AspectList();
/*  53 */     if (this.aspectOut != null) al.add(this.aspectOut, 1);
/*  54 */     return al;
/*     */   }
/*     */   
/*     */ 
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  60 */     if ((am > 0) && (this.aspectOut == null)) {
/*  61 */       this.aspectOut = tt;
/*  62 */       func_70296_d();
/*  63 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  64 */       am--;
/*     */     }
/*  66 */     return am;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/*  71 */     if ((this.aspectOut != null) && (tt == this.aspectOut)) {
/*  72 */       this.aspectOut = null;
/*  73 */       func_70296_d();
/*  74 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  75 */       return true;
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/*  82 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amt)
/*     */   {
/*  88 */     if ((amt == 1) && (tag == this.aspectOut)) return true;
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/*  94 */     for (Aspect tt : ot.getAspects()) {
/*  95 */       if (tt == this.aspectOut) return true;
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 102 */     return tag == this.aspectOut ? 1 : 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 107 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 114 */     return (face == ForgeDirection.UP) || (face == ForgeDirection.DOWN);
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 119 */     return face == ForgeDirection.DOWN;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 124 */     return face == ForgeDirection.UP;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 133 */     return false;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 138 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection face)
/*     */   {
/* 143 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection face)
/*     */   {
/* 148 */     return face == ForgeDirection.DOWN ? 64 : this.aspectIn == null ? 128 : gettingPower() ? 0 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 154 */     return this.aspectOut;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 159 */     return this.aspectOut != null ? 1 : 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 164 */     return (canOutputTo(face)) && (takeFromContainer(aspect, amount)) ? amount : 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 169 */     if ((this.aspectIn == null) && (!aspect.isPrimal())) {
/* 170 */       this.aspectIn = aspect;
/* 171 */       this.process = 39;
/* 172 */       func_70296_d();
/* 173 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 174 */       return 1;
/*     */     }
/* 176 */     return 0;
/*     */   }
/*     */   
/* 179 */   int count = 0;
/* 180 */   int process = 0;
/* 181 */   float rotationSpeed = 0.0F;
/* 182 */   public float rotation = 0.0F;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 186 */     super.func_145845_h();
/* 187 */     if (!this.field_145850_b.field_72995_K) {
/* 188 */       if (!gettingPower()) {
/* 189 */         if ((this.aspectOut == null) && (this.aspectIn == null) && (++this.count % 5 == 0)) {
/* 190 */           drawEssentia();
/*     */         }
/* 192 */         if (this.process > 0) this.process -= 1;
/* 193 */         if ((this.aspectOut == null) && (this.aspectIn != null) && (this.process == 0)) {
/* 194 */           processEssentia();
/*     */         }
/*     */       }
/*     */     } else {
/* 198 */       if ((this.aspectIn != null) && (!gettingPower()) && (this.rotationSpeed < 20.0F)) this.rotationSpeed += 2.0F;
/* 199 */       if (((this.aspectIn == null) || (gettingPower())) && (this.rotationSpeed > 0.0F)) this.rotationSpeed -= 0.5F;
/* 200 */       int pr = (int)this.rotation;
/* 201 */       this.rotation += this.rotationSpeed;
/* 202 */       if ((this.rotation % 180.0F <= 20.0F) && (pr % 180 >= 160) && (this.rotationSpeed > 0.0F)) {
/* 203 */         this.field_145850_b.func_72980_b(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "thaumcraft:pump", 1.0F, 1.0F, false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   void processEssentia() {
/* 209 */     Aspect[] comps = this.aspectIn.getComponents();
/* 210 */     this.aspectOut = comps[this.field_145850_b.field_73012_v.nextInt(2)];
/* 211 */     this.aspectIn = null;
/* 212 */     func_70296_d();
/* 213 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */   
/*     */   void drawEssentia() {
/* 217 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, ForgeDirection.DOWN);
/* 218 */     if (te != null) {
/* 219 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 220 */       if (!ic.canOutputTo(ForgeDirection.UP)) return;
/* 221 */       Aspect ta = null;
/* 222 */       if ((ic.getEssentiaAmount(ForgeDirection.UP) > 0) && 
/* 223 */         (ic.getSuctionAmount(ForgeDirection.UP) < getSuctionAmount(ForgeDirection.DOWN)) && (getSuctionAmount(ForgeDirection.DOWN) >= ic.getMinimumSuction()))
/*     */       {
/* 225 */         ta = ic.getEssentiaType(ForgeDirection.UP);
/*     */       }
/*     */       
/*     */ 
/* 229 */       if ((ta != null) && (!ta.isPrimal()) && (ic.getSuctionAmount(ForgeDirection.UP) < getSuctionAmount(ForgeDirection.DOWN)) && 
/* 230 */         (ic.takeEssentia(ta, 1, ForgeDirection.UP) == 1)) {
/* 231 */         this.aspectIn = ta;
/* 232 */         this.process = 39;
/* 233 */         func_70296_d();
/* 234 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/* 249 */     return AxisAlignedBB.func_72330_a(this.field_145851_c - 1, this.field_145848_d - 1, this.field_145849_e - 1, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean gettingPower()
/*     */   {
/* 256 */     return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileCentrifuge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */