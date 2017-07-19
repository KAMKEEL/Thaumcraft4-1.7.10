/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ public class TileAlembic extends TileThaumcraft implements IAspectContainer, IWandable, IEssentiaTransport
/*     */ {
/*     */   public Aspect aspect;
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/*  27 */     return this.aspect != null ? new AspectList().add(this.aspect, this.amount) : new AspectList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  34 */   public Aspect aspectFilter = null;
/*  35 */   public int amount = 0;
/*  36 */   public int maxAmount = 32;
/*  37 */   public int facing = 2;
/*     */   
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  42 */   public AxisAlignedBB getRenderBoundingBox() { return AxisAlignedBB.func_72330_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1, this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e + 2); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  50 */     this.facing = nbttagcompound.func_74771_c("facing");
/*  51 */     this.aspectFilter = Aspect.getAspect(nbttagcompound.func_74779_i("AspectFilter"));
/*  52 */     String tag = nbttagcompound.func_74779_i("aspect");
/*  53 */     if (tag != null) this.aspect = Aspect.getAspect(tag);
/*  54 */     this.amount = nbttagcompound.func_74765_d("amount");
/*     */     
/*  56 */     this.fd = ForgeDirection.getOrientation(this.facing);
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  62 */     if (this.aspect != null) nbttagcompound.func_74778_a("aspect", this.aspect.getTag());
/*  63 */     if (this.aspectFilter != null) nbttagcompound.func_74778_a("AspectFilter", this.aspectFilter.getTag());
/*  64 */     nbttagcompound.func_74777_a("amount", (short)this.amount);
/*  65 */     nbttagcompound.func_74774_a("facing", (byte)this.facing);
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  70 */     return false;
/*     */   }
/*     */   
/*     */ 
/*  74 */   public boolean aboveAlembic = false;
/*  75 */   public boolean aboveFurnace = false;
/*  76 */   ForgeDirection fd = null;
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
/*     */ 
/*     */ 
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  94 */     if (((this.amount < this.maxAmount) && (tt == this.aspect)) || (this.amount == 0))
/*     */     {
/*  96 */       this.aspect = tt;
/*  97 */       int added = Math.min(am, this.maxAmount - this.amount);
/*  98 */       this.amount += added;
/*  99 */       am -= added;
/*     */     }
/* 101 */     func_70296_d();
/* 102 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 103 */     return am;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/* 108 */     if ((this.amount == 0) || (this.aspect == null)) {
/* 109 */       this.aspect = null;
/* 110 */       this.amount = 0;
/*     */     }
/* 112 */     if ((this.aspect != null) && (this.amount >= am) && (tt == this.aspect)) {
/* 113 */       this.amount -= am;
/* 114 */       if (this.amount <= 0) {
/* 115 */         this.aspect = null;
/* 116 */         this.amount = 0;
/*     */       }
/* 118 */       func_70296_d();
/* 119 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 120 */       return true;
/*     */     }
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 127 */     if ((this.amount > 0) && (this.aspect != null) && 
/* 128 */       (ot.getAmount(this.aspect) > 0)) { return true;
/*     */     }
/* 130 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tt, int am)
/*     */   {
/* 135 */     if ((this.amount >= am) && (tt == this.aspect)) return true;
/* 136 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tt)
/*     */   {
/* 141 */     return tt == this.aspect ? this.amount : 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 146 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 152 */     return false;
/*     */   }
/*     */   
/*     */   public void getAppearance() {
/* 156 */     this.aboveAlembic = false;
/* 157 */     this.aboveFurnace = false;
/* 158 */     if ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockStoneDevice) && (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 0))
/*     */     {
/* 160 */       this.aboveFurnace = true;
/*     */     }
/* 162 */     if ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockMetalDevice) && (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == func_145832_p()))
/*     */     {
/* 164 */       this.aboveAlembic = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*     */   {
/* 170 */     super.onDataPacket(net, pkt);
/* 171 */     getAppearance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/* 178 */     if (side <= 1) return 0;
/* 179 */     this.facing = side;
/* 180 */     this.fd = ForgeDirection.getOrientation(this.facing);
/* 181 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 182 */     player.func_71038_i();
/* 183 */     func_70296_d();
/* 184 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*     */   {
/* 191 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 213 */     return (face != ForgeDirection.getOrientation(this.facing)) && (face != ForgeDirection.DOWN);
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 218 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 223 */     return (face != ForgeDirection.getOrientation(this.facing)) && (face != ForgeDirection.DOWN);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public Aspect getSuctionType(ForgeDirection loc)
/*     */   {
/* 233 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection loc)
/*     */   {
/* 238 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 243 */     return this.aspect;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 248 */     return this.amount;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 253 */     return (canOutputTo(face)) && (takeFromContainer(aspect, amount)) ? amount : 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection loc)
/*     */   {
/* 258 */     return 0;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 263 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 268 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileAlembic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */