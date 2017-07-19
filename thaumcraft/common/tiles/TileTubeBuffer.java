/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileTubeBuffer
/*     */   extends TileThaumcraft
/*     */   implements IAspectContainer, IEssentiaTransport, IWandable
/*     */ {
/*  36 */   public AspectList aspects = new AspectList();
/*  37 */   public final int MAXAMOUNT = 8;
/*  38 */   public boolean[] openSides = { true, true, true, true, true, true };
/*  39 */   public byte[] chokedSides = { 0, 0, 0, 0, 0, 0 };
/*     */   
/*     */ 
/*     */   public boolean canUpdate()
/*     */   {
/*  44 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  50 */     this.aspects.readFromNBT(nbttagcompound);
/*  51 */     byte[] sides = nbttagcompound.func_74770_j("open");
/*  52 */     if ((sides != null) && (sides.length == 6)) {
/*  53 */       for (int a = 0; a < 6; a++)
/*  54 */         this.openSides[a] = (sides[a] == 1 ? 1 : false);
/*     */     }
/*  56 */     this.chokedSides = nbttagcompound.func_74770_j("choke");
/*  57 */     if ((this.chokedSides == null) || (this.chokedSides.length < 6)) {
/*  58 */       this.chokedSides = new byte[] { 0, 0, 0, 0, 0, 0 };
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  65 */     this.aspects.writeToNBT(nbttagcompound);
/*  66 */     byte[] sides = new byte[6];
/*  67 */     for (int a = 0; a < 6; a++) {
/*  68 */       sides[a] = (this.openSides[a] != 0 ? 1 : 0);
/*     */     }
/*  70 */     nbttagcompound.func_74773_a("open", sides);
/*  71 */     nbttagcompound.func_74773_a("choke", this.chokedSides);
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/*  77 */     return this.aspects;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  85 */     if (am != 1) return am;
/*  86 */     if (this.aspects.visSize() < 8) {
/*  87 */       this.aspects.add(tt, am);
/*  88 */       func_70296_d();
/*  89 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  90 */       return 0;
/*     */     }
/*  92 */     return am;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/*  97 */     if (this.aspects.getAmount(tt) >= am) {
/*  98 */       this.aspects.remove(tt, am);
/*  99 */       func_70296_d();
/* 100 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 101 */       return true;
/*     */     }
/* 103 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 108 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amt)
/*     */   {
/* 114 */     return this.aspects.getAmount(tag) >= amt;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot) {
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag) {
/* 122 */     return this.aspects.getAmount(tag);
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 127 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 135 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 140 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 145 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 153 */     return false;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 158 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection loc)
/*     */   {
/* 163 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection loc)
/*     */   {
/* 168 */     return (this.bellows <= 0) || (this.chokedSides[loc.ordinal()] == 1) ? 1 : this.chokedSides[loc.ordinal()] == 2 ? 0 : this.bellows * 32;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 173 */     return this.aspects.size() > 0 ? this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.getAspects().length)] : null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 178 */     return this.aspects.visSize();
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 183 */     if (!canOutputTo(face)) return 0;
/* 184 */     TileEntity te = null;
/* 185 */     IEssentiaTransport ic = null;
/* 186 */     int suction = 0;
/* 187 */     te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, face);
/* 188 */     if (te != null) {
/* 189 */       ic = (IEssentiaTransport)te;
/* 190 */       suction = ic.getSuctionAmount(face.getOpposite());
/*     */     }
/* 192 */     for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
/* 193 */       if ((canOutputTo(dir)) && (dir != face)) {
/* 194 */         te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, dir);
/* 195 */         if (te != null) {
/* 196 */           ic = (IEssentiaTransport)te;
/* 197 */           int sa = ic.getSuctionAmount(dir.getOpposite());
/* 198 */           Aspect su = ic.getSuctionType(dir.getOpposite());
/* 199 */           if (((su == aspect) || (su == null)) && (suction < sa) && (getSuctionAmount(dir) < sa)) return 0;
/*     */         }
/*     */       }
/* 202 */     if (amount > this.aspects.getAmount(aspect)) amount = this.aspects.getAmount(aspect);
/* 203 */     return takeFromContainer(aspect, amount) ? amount : 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 208 */     return canInputFrom(face) ? amount - addToContainer(aspect, amount) : 0;
/*     */   }
/*     */   
/* 211 */   int count = 0;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 215 */     this.count += 1;
/* 216 */     if ((this.bellows < 0) || (this.count % 20 == 0)) {
/* 217 */       getBellows();
/*     */     }
/* 219 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/* 221 */       if (this.count % 5 == 0) { getClass(); if (this.aspects.visSize() < 8) {
/* 222 */           fillBuffer();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   void fillBuffer() {
/* 229 */     TileEntity te = null;
/* 230 */     IEssentiaTransport ic = null;
/* 231 */     for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/* 232 */       te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, dir);
/* 233 */       if (te != null) {
/* 234 */         ic = (IEssentiaTransport)te;
/*     */         
/* 236 */         if ((ic.getEssentiaAmount(dir.getOpposite()) > 0) && (ic.getSuctionAmount(dir.getOpposite()) < getSuctionAmount(dir)) && (getSuctionAmount(dir) >= ic.getMinimumSuction()))
/*     */         {
/*     */ 
/*     */ 
/* 240 */           Aspect ta = ic.getEssentiaType(dir.getOpposite());
/* 241 */           addToContainer(ta, ic.takeEssentia(ta, 1, dir.getOpposite()));
/*     */           
/* 243 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 251 */   int bellows = -1;
/*     */   
/*     */   public void getBellows() {
/* 254 */     this.bellows = TileBellows.getBellows(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, ForgeDirection.VALID_DIRECTIONS);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/* 262 */     MovingObjectPosition hit = RayTracer.retraceBlock(world, player, x, y, z);
/* 263 */     if (hit == null) { return 0;
/*     */     }
/* 265 */     if ((hit.subHit >= 0) && (hit.subHit < 6))
/*     */     {
/* 267 */       player.func_71038_i();
/* 268 */       if (player.func_70093_af()) {
/* 269 */         player.field_70170_p.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:squeek", 0.6F, 1.1F + world.field_73012_v.nextFloat() * 0.2F, false);
/* 270 */         if (!this.field_145850_b.field_72995_K) {
/* 271 */           int tmp118_115 = hit.subHit; byte[] tmp118_110 = this.chokedSides;tmp118_110[tmp118_115] = ((byte)(tmp118_110[tmp118_115] + 1));
/* 272 */           if (this.chokedSides[hit.subHit] > 2) this.chokedSides[hit.subHit] = 0;
/* 273 */           func_70296_d();
/* 274 */           world.func_147471_g(x, y, z);
/*     */         }
/*     */       } else {
/* 277 */         player.field_70170_p.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*     */         
/* 279 */         this.openSides[hit.subHit] = (this.openSides[hit.subHit] == 0 ? 1 : false);
/* 280 */         ForgeDirection dir = ForgeDirection.getOrientation(hit.subHit);
/* 281 */         TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
/* 282 */         if ((tile != null) && ((tile instanceof TileTube))) {
/* 283 */           ((TileTube)tile).openSides[dir.getOpposite().ordinal()] = this.openSides[hit.subHit];
/* 284 */           world.func_147471_g(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
/* 285 */           tile.func_70296_d();
/*     */         }
/* 287 */         if ((tile != null) && ((tile instanceof TileTubeBuffer))) {
/* 288 */           ((TileTubeBuffer)tile).openSides[dir.getOpposite().ordinal()] = this.openSides[hit.subHit];
/* 289 */           world.func_147471_g(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
/* 290 */           tile.func_70296_d();
/*     */         }
/* 292 */         func_70296_d();
/* 293 */         world.func_147471_g(x, y, z);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 298 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*     */   {
/* 305 */     return null;
/*     */   }
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
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public MovingObjectPosition rayTrace(World world, Vec3 vec3d, Vec3 vec3d1, MovingObjectPosition fullblock)
/*     */   {
/* 324 */     return fullblock;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean canConnectSide(int side)
/*     */   {
/* 335 */     ForgeDirection dir = ForgeDirection.getOrientation(side);
/* 336 */     TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
/* 337 */     return (tile != null) && ((tile instanceof IEssentiaTransport));
/*     */   }
/*     */   
/*     */   public void addTraceableCuboids(List<IndexedCuboid6> cuboids)
/*     */   {
/* 342 */     float min = 0.42F;
/* 343 */     float max = 0.58F;
/* 344 */     if (canConnectSide(0)) cuboids.add(new IndexedCuboid6(Integer.valueOf(0), new Cuboid6(this.field_145851_c + min, this.field_145848_d, this.field_145849_e + min, this.field_145851_c + max, this.field_145848_d + 0.5D, this.field_145849_e + max)));
/* 345 */     if (canConnectSide(1)) cuboids.add(new IndexedCuboid6(Integer.valueOf(1), new Cuboid6(this.field_145851_c + min, this.field_145848_d + 0.5D, this.field_145849_e + min, this.field_145851_c + max, this.field_145848_d + 1, this.field_145849_e + max)));
/* 346 */     if (canConnectSide(2)) cuboids.add(new IndexedCuboid6(Integer.valueOf(2), new Cuboid6(this.field_145851_c + min, this.field_145848_d + min, this.field_145849_e, this.field_145851_c + max, this.field_145848_d + max, this.field_145849_e + 0.5D)));
/* 347 */     if (canConnectSide(3)) cuboids.add(new IndexedCuboid6(Integer.valueOf(3), new Cuboid6(this.field_145851_c + min, this.field_145848_d + min, this.field_145849_e + 0.5D, this.field_145851_c + max, this.field_145848_d + max, this.field_145849_e + 1)));
/* 348 */     if (canConnectSide(4)) cuboids.add(new IndexedCuboid6(Integer.valueOf(4), new Cuboid6(this.field_145851_c, this.field_145848_d + min, this.field_145849_e + min, this.field_145851_c + 0.5D, this.field_145848_d + max, this.field_145849_e + max)));
/* 349 */     if (canConnectSide(5)) { cuboids.add(new IndexedCuboid6(Integer.valueOf(5), new Cuboid6(this.field_145851_c + 0.5D, this.field_145848_d + min, this.field_145849_e + min, this.field_145851_c + 1, this.field_145848_d + max, this.field_145849_e + max)));
/*     */     }
/* 351 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(6), new Cuboid6(this.field_145851_c + 0.25F, this.field_145848_d + 0.25F, this.field_145849_e + 0.25F, this.field_145851_c + 0.75F, this.field_145848_d + 0.75F, this.field_145849_e + 0.75F)));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileTubeBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */