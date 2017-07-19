/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ public class TileTube extends TileThaumcraft implements IEssentiaTransport, IWandable
/*     */ {
/*  30 */   public ForgeDirection facing = ForgeDirection.NORTH;
/*  31 */   public boolean[] openSides = { true, true, true, true, true, true };
/*  32 */   Aspect essentiaType = null;
/*  33 */   int essentiaAmount = 0;
/*  34 */   Aspect suctionType = null;
/*  35 */   int suction = 0;
/*  36 */   int venting = 0;
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  41 */     this.essentiaType = Aspect.getAspect(nbttagcompound.func_74779_i("type"));
/*  42 */     this.essentiaAmount = nbttagcompound.func_74762_e("amount");
/*     */     
/*  44 */     this.facing = ForgeDirection.getOrientation(nbttagcompound.func_74762_e("side"));
/*  45 */     byte[] sides = nbttagcompound.func_74770_j("open");
/*  46 */     if ((sides != null) && (sides.length == 6)) {
/*  47 */       for (int a = 0; a < 6; a++) {
/*  48 */         this.openSides[a] = (sides[a] == 1 ? 1 : false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  55 */     if (this.essentiaType != null) nbttagcompound.func_74778_a("type", this.essentiaType.getTag());
/*  56 */     nbttagcompound.func_74768_a("amount", this.essentiaAmount);
/*     */     
/*  58 */     byte[] sides = new byte[6];
/*  59 */     for (int a = 0; a < 6; a++) {
/*  60 */       sides[a] = (this.openSides[a] != 0 ? 1 : 0);
/*     */     }
/*  62 */     nbttagcompound.func_74768_a("side", this.facing.ordinal());
/*  63 */     nbttagcompound.func_74773_a("open", sides);
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/*  68 */     super.func_145839_a(nbttagcompound);
/*  69 */     this.suctionType = Aspect.getAspect(nbttagcompound.func_74779_i("stype"));
/*  70 */     this.suction = nbttagcompound.func_74762_e("samount");
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/*  75 */     super.func_145841_b(nbttagcompound);
/*  76 */     if (this.suctionType != null) nbttagcompound.func_74778_a("stype", this.suctionType.getTag());
/*  77 */     nbttagcompound.func_74768_a("samount", this.suction);
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  82 */     return true;
/*     */   }
/*     */   
/*  85 */   int count = 0;
/*     */   static final int freq = 5;
/*  87 */   int ventColor = 0;
/*     */   
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/*  92 */     if (this.venting > 0) this.venting -= 1;
/*  93 */     if (this.count == 0) this.count = this.field_145850_b.field_73012_v.nextInt(10);
/*  94 */     if (!this.field_145850_b.field_72995_K) {
/*  95 */       if (this.venting <= 0) {
/*  96 */         if (++this.count % 2 == 0) {
/*  97 */           calculateSuction(null, false, false);
/*  98 */           checkVenting();
/*  99 */           if ((this.essentiaType != null) && (this.essentiaAmount == 0)) this.essentiaType = null;
/*     */         }
/* 101 */         if ((this.count % 5 == 0) && (this.suction > 0)) {
/* 102 */           equalizeWithNeighbours(false);
/*     */         }
/*     */       }
/*     */     }
/* 106 */     else if (this.venting > 0) {
/* 107 */       Random r = new Random(hashCode() * 4);
/* 108 */       float rp = r.nextFloat() * 360.0F;
/* 109 */       float ry = r.nextFloat() * 360.0F;
/* 110 */       double fx = -MathHelper.func_76126_a(ry / 180.0F * 3.1415927F) * MathHelper.func_76134_b(rp / 180.0F * 3.1415927F);
/* 111 */       double fz = MathHelper.func_76134_b(ry / 180.0F * 3.1415927F) * MathHelper.func_76134_b(rp / 180.0F * 3.1415927F);
/* 112 */       double fy = -MathHelper.func_76126_a(rp / 180.0F * 3.1415927F);
/*     */       
/* 114 */       Thaumcraft.proxy.drawVentParticles(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, fx / 5.0D, fy / 5.0D, fx / 5.0D, this.ventColor);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void calculateSuction(Aspect filter, boolean restrict, boolean directional)
/*     */   {
/* 122 */     this.suction = 0;
/* 123 */     this.suctionType = null;
/* 124 */     ForgeDirection loc = null;
/* 125 */     for (int dir = 0; dir < 6; dir++)
/*     */       try {
/* 127 */         loc = ForgeDirection.getOrientation(dir);
/* 128 */         if ((!directional) || (this.facing == loc.getOpposite()))
/*     */         {
/* 130 */           if (isConnectable(loc)) {
/* 131 */             TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, loc);
/* 132 */             if (te != null) {
/* 133 */               IEssentiaTransport ic = (IEssentiaTransport)te;
/* 134 */               if ((filter != null) && (ic.getSuctionType(loc.getOpposite()) != null) && (ic.getSuctionType(loc.getOpposite()) != filter))
/*     */                 continue;
/* 136 */               if ((filter == null) && (getEssentiaAmount(loc) > 0) && (ic.getSuctionType(loc.getOpposite()) != null) && (getEssentiaType(loc) != ic.getSuctionType(loc.getOpposite()))) {
/*     */                 continue;
/*     */               }
/*     */               
/* 140 */               if ((filter != null) && (getEssentiaAmount(loc) > 0) && (getEssentiaType(loc) != null) && (ic.getSuctionType(loc.getOpposite()) != null) && (getEssentiaType(loc) != ic.getSuctionType(loc.getOpposite()))) {
/*     */                 continue;
/*     */               }
/*     */               
/* 144 */               int suck = ic.getSuctionAmount(loc.getOpposite());
/* 145 */               if ((suck > 0) && (suck > this.suction + 1)) {
/* 146 */                 Aspect st = ic.getSuctionType(loc.getOpposite());
/* 147 */                 if (st == null) st = filter;
/* 148 */                 setSuction(st, restrict ? suck / 2 : suck - 1);
/*     */               }
/*     */             }
/*     */           } }
/*     */       } catch (Exception e) {}
/*     */   }
/*     */   
/*     */   void checkVenting() {
/* 156 */     ForgeDirection loc = null;
/* 157 */     for (int dir = 0; dir < 6; dir++)
/*     */       try {
/* 159 */         loc = ForgeDirection.getOrientation(dir);
/* 160 */         if (isConnectable(loc)) {
/* 161 */           TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, loc);
/* 162 */           if (te != null) {
/* 163 */             IEssentiaTransport ic = (IEssentiaTransport)te;
/* 164 */             int suck = ic.getSuctionAmount(loc.getOpposite());
/* 165 */             if ((this.suction > 0) && ((suck == this.suction) || (suck == this.suction - 1)) && (this.suctionType != ic.getSuctionType(loc.getOpposite()))) {
/* 166 */               int c = -1;
/* 167 */               if (this.suctionType != null) {
/* 168 */                 c = Config.aspectOrder.indexOf(this.suctionType);
/*     */               }
/* 170 */               this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockTube, 1, c);
/* 171 */               this.venting = 40;
/*     */             }
/*     */           }
/*     */         }
/*     */       } catch (Exception e) {}
/*     */   }
/*     */   
/*     */   void equalizeWithNeighbours(boolean directional) {
/* 179 */     ForgeDirection loc = null;
/*     */     
/* 181 */     if (this.essentiaAmount > 0) { return;
/*     */     }
/* 183 */     for (int dir = 0; dir < 6; dir++) {
/*     */       try {
/* 185 */         loc = ForgeDirection.getOrientation(dir);
/* 186 */         if (((!directional) || (this.facing != loc.getOpposite())) && 
/* 187 */           (isConnectable(loc)))
/*     */         {
/* 189 */           TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, loc);
/* 190 */           if (te != null) {
/* 191 */             IEssentiaTransport ic = (IEssentiaTransport)te;
/* 192 */             if (!ic.canOutputTo(loc.getOpposite()))
/*     */               continue;
/* 194 */             if (((getSuctionType(null) == null) || (getSuctionType(null) == ic.getEssentiaType(loc.getOpposite())) || (ic.getEssentiaType(loc.getOpposite()) == null)) && (getSuctionAmount(null) > ic.getSuctionAmount(loc.getOpposite())) && (getSuctionAmount(null) >= ic.getMinimumSuction()))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/* 199 */               Aspect a = getSuctionType(null);
/* 200 */               if (a == null) {
/* 201 */                 a = ic.getEssentiaType(loc.getOpposite());
/* 202 */                 if (a == null) { a = ic.getEssentiaType(ForgeDirection.UNKNOWN);
/*     */                 }
/*     */               }
/* 205 */               int am = addEssentia(a, ic.takeEssentia(a, 1, loc.getOpposite()), loc);
/*     */               
/* 207 */               if (am > 0) {
/* 208 */                 if (this.field_145850_b.field_73012_v.nextInt(100) == 0)
/* 209 */                   this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockTube, 0, 0);
/* 210 */                 return;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       } catch (Exception e) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isConnectable(ForgeDirection face) {
/* 220 */     if (face == ForgeDirection.UNKNOWN) return false;
/* 221 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 226 */     if (face == ForgeDirection.UNKNOWN) return false;
/* 227 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 232 */     if (face == ForgeDirection.UNKNOWN) return false;
/* 233 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount)
/*     */   {
/* 238 */     this.suctionType = aspect;
/* 239 */     this.suction = amount;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection loc)
/*     */   {
/* 244 */     return this.suctionType;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection loc)
/*     */   {
/* 249 */     return this.suction;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 254 */     return this.essentiaType;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 259 */     return this.essentiaAmount;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 264 */     if ((canOutputTo(face)) && (this.essentiaType == aspect) && (this.essentiaAmount > 0) && (amount > 0)) {
/* 265 */       this.essentiaAmount -= 1;
/* 266 */       if (this.essentiaAmount <= 0) this.essentiaType = null;
/* 267 */       func_70296_d();
/* 268 */       return 1;
/*     */     }
/* 270 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 275 */     if ((canInputFrom(face)) && (this.essentiaAmount == 0) && (amount > 0)) {
/* 276 */       this.essentiaType = aspect;
/* 277 */       this.essentiaAmount += 1;
/* 278 */       func_70296_d();
/* 279 */       return 1;
/*     */     }
/* 281 */     return 0;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 286 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 291 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 297 */     if (i == 0)
/*     */     {
/* 299 */       if (this.field_145850_b.field_72995_K) {
/* 300 */         this.field_145850_b.func_72980_b(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "thaumcraft:creak", 1.0F, 1.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, false);
/*     */       }
/* 302 */       return true;
/*     */     }
/*     */     
/* 305 */     if (i == 1)
/*     */     {
/* 307 */       if (this.field_145850_b.field_72995_K) {
/* 308 */         if (this.venting <= 0)
/* 309 */           this.field_145850_b.func_72980_b(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "random.fizz", 0.1F, 1.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.1F, false);
/* 310 */         this.venting = 50;
/* 311 */         if ((j == -1) || (j >= Config.aspectOrder.size())) {
/* 312 */           this.ventColor = 11184810;
/*     */         } else {
/* 314 */           this.ventColor = ((Aspect)Config.aspectOrder.get(j)).getColor();
/*     */         }
/*     */       }
/* 317 */       return true;
/*     */     }
/*     */     
/* 320 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/* 329 */     MovingObjectPosition hit = RayTracer.retraceBlock(world, player, x, y, z);
/* 330 */     if (hit == null) { return 0;
/*     */     }
/* 332 */     if ((hit.subHit >= 0) && (hit.subHit < 6))
/*     */     {
/* 334 */       player.field_70170_p.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/* 335 */       player.func_71038_i();
/* 336 */       func_70296_d();
/* 337 */       world.func_147471_g(x, y, z);
/* 338 */       this.openSides[hit.subHit] = (this.openSides[hit.subHit] == 0 ? 1 : false);
/* 339 */       ForgeDirection dir = ForgeDirection.getOrientation(hit.subHit);
/* 340 */       TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
/* 341 */       if ((tile != null) && ((tile instanceof TileTube))) {
/* 342 */         ((TileTube)tile).openSides[dir.getOpposite().ordinal()] = this.openSides[hit.subHit];
/* 343 */         world.func_147471_g(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
/* 344 */         tile.func_70296_d();
/*     */       }
/*     */     }
/*     */     
/* 348 */     if (hit.subHit == 6)
/*     */     {
/* 350 */       player.field_70170_p.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/* 351 */       player.func_71038_i();
/* 352 */       int a = this.facing.ordinal();
/* 353 */       func_70296_d();
/* 354 */       do { a++; if (a >= 20) break;
/* 355 */       } while ((!canConnectSide(ForgeDirection.getOrientation(a % 6).getOpposite().ordinal())) || (!isConnectable(ForgeDirection.getOrientation(a % 6).getOpposite())));
/*     */       
/* 357 */       a %= 6;
/* 358 */       this.facing = ForgeDirection.getOrientation(a);
/* 359 */       world.func_147471_g(x, y, z);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 367 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*     */   {
/* 374 */     return null;
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
/* 393 */     return fullblock;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean canConnectSide(int side)
/*     */   {
/* 404 */     ForgeDirection dir = ForgeDirection.getOrientation(side);
/* 405 */     TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
/* 406 */     return (tile != null) && ((tile instanceof IEssentiaTransport));
/*     */   }
/*     */   
/*     */   public void addTraceableCuboids(List<IndexedCuboid6> cuboids)
/*     */   {
/* 411 */     float min = 0.42F;
/* 412 */     float max = 0.58F;
/* 413 */     if (canConnectSide(0)) cuboids.add(new IndexedCuboid6(Integer.valueOf(0), new Cuboid6(this.field_145851_c + min, this.field_145848_d, this.field_145849_e + min, this.field_145851_c + max, this.field_145848_d + 0.5D, this.field_145849_e + max)));
/* 414 */     if (canConnectSide(1)) cuboids.add(new IndexedCuboid6(Integer.valueOf(1), new Cuboid6(this.field_145851_c + min, this.field_145848_d + 0.5D, this.field_145849_e + min, this.field_145851_c + max, this.field_145848_d + 1, this.field_145849_e + max)));
/* 415 */     if (canConnectSide(2)) cuboids.add(new IndexedCuboid6(Integer.valueOf(2), new Cuboid6(this.field_145851_c + min, this.field_145848_d + min, this.field_145849_e, this.field_145851_c + max, this.field_145848_d + max, this.field_145849_e + 0.5D)));
/* 416 */     if (canConnectSide(3)) cuboids.add(new IndexedCuboid6(Integer.valueOf(3), new Cuboid6(this.field_145851_c + min, this.field_145848_d + min, this.field_145849_e + 0.5D, this.field_145851_c + max, this.field_145848_d + max, this.field_145849_e + 1)));
/* 417 */     if (canConnectSide(4)) cuboids.add(new IndexedCuboid6(Integer.valueOf(4), new Cuboid6(this.field_145851_c, this.field_145848_d + min, this.field_145849_e + min, this.field_145851_c + 0.5D, this.field_145848_d + max, this.field_145849_e + max)));
/* 418 */     if (canConnectSide(5)) { cuboids.add(new IndexedCuboid6(Integer.valueOf(5), new Cuboid6(this.field_145851_c + 0.5D, this.field_145848_d + min, this.field_145849_e + min, this.field_145851_c + 1, this.field_145848_d + max, this.field_145849_e + max)));
/*     */     }
/* 420 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(6), new Cuboid6(this.field_145851_c + 0.34375D, this.field_145848_d + 0.34375D, this.field_145849_e + 0.34375D, this.field_145851_c + 0.65625D, this.field_145848_d + 0.65625D, this.field_145849_e + 0.65625D)));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileTube.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */