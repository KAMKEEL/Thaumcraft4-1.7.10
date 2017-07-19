/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectSource;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ 
/*     */ 
/*     */ public class TileEssentiaReservoir
/*     */   extends TileThaumcraft
/*     */   implements IAspectSource, IWandable, IEssentiaTransport
/*     */ {
/*  24 */   public AspectList essentia = new AspectList();
/*  25 */   public int maxAmount = 256;
/*  26 */   public ForgeDirection facing = ForgeDirection.DOWN;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  30 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  36 */     this.essentia.readFromNBT(nbttagcompound);
/*  37 */     if (this.essentia.visSize() > this.maxAmount) this.essentia = new AspectList();
/*  38 */     this.facing = ForgeDirection.getOrientation(nbttagcompound.func_74771_c("face"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  44 */     this.essentia.writeToNBT(nbttagcompound);
/*  45 */     nbttagcompound.func_74774_a("face", (byte)this.facing.ordinal());
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/*  51 */     return this.essentia;
/*     */   }
/*     */   
/*     */   public void setAspects(AspectList aspects)
/*     */   {
/*  56 */     this.essentia = aspects.copy();
/*     */   }
/*     */   
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  61 */     if (am == 0) return am;
/*  62 */     int space = this.maxAmount - this.essentia.visSize();
/*  63 */     if (space >= am) {
/*  64 */       this.essentia.add(tt, am);
/*  65 */       am = 0;
/*     */     } else {
/*  67 */       this.essentia.add(tt, space);
/*  68 */       am -= space;
/*     */     }
/*  70 */     if (space > 0) {
/*  71 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  72 */       func_70296_d();
/*     */     }
/*  74 */     return am;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/*  79 */     if (this.essentia.getAmount(tt) >= am) {
/*  80 */       this.essentia.remove(tt, am);
/*  81 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  82 */       func_70296_d();
/*  83 */       return true;
/*     */     }
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/*  90 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amt)
/*     */   {
/*  96 */     if (this.essentia.getAmount(tag) >= amt) return true;
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 102 */     for (Aspect tt : ot.getAspects()) {
/* 103 */       if (this.essentia.getAmount(tt) < ot.getAmount(tt)) return false;
/*     */     }
/* 105 */     return true;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 110 */     return this.essentia.getAmount(tag);
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 115 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 123 */     return face == this.facing;
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 128 */     return face == this.facing;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 133 */     return face == this.facing;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 141 */     return false;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 146 */     return 24;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection loc)
/*     */   {
/* 151 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection loc)
/*     */   {
/* 156 */     return this.essentia.visSize() < this.maxAmount ? 24 : 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 161 */     return (this.essentia.visSize() > 0) && (loc == ForgeDirection.UNKNOWN) ? this.essentia.getAspects()[0] : null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 166 */     return this.essentia.visSize();
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 171 */     return (canOutputTo(face)) && (takeFromContainer(aspect, amount)) ? amount : 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 176 */     return canInputFrom(face) ? amount - addToContainer(aspect, amount) : 0;
/*     */   }
/*     */   
/* 179 */   int count = 0;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 183 */     super.func_145845_h();
/* 184 */     this.count += 1;
/* 185 */     if ((!this.field_145850_b.field_72995_K) && (this.count % 5 == 0) && (this.essentia.visSize() < this.maxAmount)) {
/* 186 */       fillReservoir();
/*     */     }
/* 188 */     if (this.field_145850_b.field_72995_K) {
/* 189 */       int vs = this.essentia.visSize();
/* 190 */       if (vs > 0) {
/* 191 */         if (this.field_145850_b.field_73012_v.nextInt(500 - vs) == 0) {
/* 192 */           this.field_145850_b.func_72980_b(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "thaumcraft:creak", 1.0F, 1.4F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, false);
/*     */         }
/* 194 */         if ((this.count % 20 == 0) && (this.essentia.size() > 0)) {
/* 195 */           this.displayAspect = this.essentia.getAspects()[(this.count / 20 % this.essentia.size())];
/* 196 */           Color c = new Color(this.displayAspect.getColor());
/* 197 */           this.tr = (c.getRed() / 255.0F);
/* 198 */           this.tg = (c.getGreen() / 255.0F);
/* 199 */           this.tb = (c.getBlue() / 255.0F);
/* 200 */           this.tri = ((this.cr - this.tr) / 20.0F);
/* 201 */           this.tgi = ((this.cg - this.tg) / 20.0F);
/* 202 */           this.tbi = ((this.cb - this.tb) / 20.0F);
/*     */         }
/*     */         
/* 205 */         if (this.displayAspect == null) {
/* 206 */           this.tr = (this.tg = this.tb = 1.0F);
/* 207 */           this.tri = (this.tgi = this.tbi = 0.0F);
/*     */         } else {
/* 209 */           this.cr -= this.tri;
/* 210 */           this.cg -= this.tgi;
/* 211 */           this.cb -= this.tbi;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 219 */   float tr = 1.0F; float tri = 0.0F;
/* 220 */   float tg = 1.0F; float tgi = 0.0F;
/* 221 */   float tb = 1.0F; float tbi = 0.0F;
/* 222 */   public float cr = 1.0F;
/* 223 */   public float cg = 1.0F;
/* 224 */   public float cb = 1.0F;
/* 225 */   public Aspect displayAspect = null;
/*     */   
/*     */   void fillReservoir() {
/* 228 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.facing);
/* 229 */     if (te != null) {
/* 230 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 231 */       if (!ic.canOutputTo(this.facing.getOpposite())) { return;
/*     */       }
/* 233 */       Aspect ta = null;
/* 234 */       if ((ic.getEssentiaAmount(this.facing.getOpposite()) > 0) && 
/* 235 */         (ic.getSuctionAmount(this.facing.getOpposite()) < getSuctionAmount(this.facing)) && (getSuctionAmount(this.facing) >= ic.getMinimumSuction()))
/*     */       {
/* 237 */         ta = ic.getEssentiaType(this.facing.getOpposite());
/*     */       }
/*     */       
/* 240 */       if ((ta != null) && (ic.getSuctionAmount(this.facing.getOpposite()) < getSuctionAmount(this.facing))) {
/* 241 */         addToContainer(ta, ic.takeEssentia(ta, 1, this.facing.getOpposite()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/* 252 */     if (player.func_70093_af()) {
/* 253 */       this.facing = ForgeDirection.getOrientation(side);
/*     */     } else {
/* 255 */       this.facing = ForgeDirection.getOrientation(side).getOpposite();
/*     */     }
/* 257 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 258 */     player.func_71038_i();
/* 259 */     func_70296_d();
/* 260 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*     */   {
/* 267 */     return null;
/*     */   }
/*     */   
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileEssentiaReservoir.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */