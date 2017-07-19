/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusPortableHole;
/*     */ 
/*     */ public class TileHole extends TileMemory
/*     */ {
/*  17 */   public short countdown = 0;
/*  18 */   public short countdownmax = 120;
/*  19 */   public byte count = 0;
/*  20 */   public byte direction = 0;
/*     */   
/*     */ 
/*     */   public TileHole() {}
/*     */   
/*     */ 
/*     */   public TileHole(Block bi, int md, short max, byte count, byte direction, TileEntity te)
/*     */   {
/*  28 */     super(bi, md, te);
/*  29 */     this.count = count;
/*  30 */     this.countdownmax = max;
/*  31 */     this.direction = direction;
/*     */   }
/*     */   
/*     */   public TileHole(byte count)
/*     */   {
/*  36 */     this.count = count;
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  41 */     return true;
/*     */   }
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/*  46 */     super.func_145845_h();
/*     */     
/*  48 */     if (this.field_145850_b.field_72995_K)
/*     */     {
/*  50 */       surroundwithsparkles();
/*     */     }
/*     */     
/*  53 */     if ((this.countdown == 0) && (this.count > 1) && (this.direction != -1)) {
/*  54 */       int ii = this.field_145851_c;
/*  55 */       int jj = this.field_145848_d;
/*  56 */       int kk = this.field_145849_e;
/*     */       
/*  58 */       switch (this.direction) {
/*     */       case 0: case 1: 
/*  60 */         for (int a = 0; a < 9; a++) if ((a / 3 != 1) || (a % 3 != 1))
/*  61 */             ItemFocusPortableHole.createHole(this.field_145850_b, ii - 1 + a / 3, jj, kk - 1 + a % 3, -1, (byte)1, this.countdownmax);
/*  62 */         break;
/*     */       case 2: case 3: 
/*  64 */         for (int a = 0; a < 9; a++) if ((a / 3 != 1) || (a % 3 != 1))
/*  65 */             ItemFocusPortableHole.createHole(this.field_145850_b, ii - 1 + a / 3, jj - 1 + a % 3, kk, -1, (byte)1, this.countdownmax);
/*  66 */         break;
/*     */       case 4: case 5: 
/*  68 */         for (int a = 0; a < 9; a++) { if ((a / 3 != 1) || (a % 3 != 1)) {
/*  69 */             ItemFocusPortableHole.createHole(this.field_145850_b, ii, jj - 1 + a / 3, kk - 1 + a % 3, -1, (byte)1, this.countdownmax);
/*     */           }
/*     */         }
/*     */       }
/*  73 */       switch (this.direction) {
/*  74 */       case 0:  jj++; break;
/*  75 */       case 1:  jj--; break;
/*  76 */       case 2:  kk++; break;
/*  77 */       case 3:  kk--; break;
/*  78 */       case 4:  ii++; break;
/*  79 */       case 5:  ii--;
/*     */       }
/*     */       
/*  82 */       if (!ItemFocusPortableHole.createHole(this.field_145850_b, ii, jj, kk, this.direction, (byte)(this.count - 1), this.countdownmax))
/*     */       {
/*  84 */         this.count = 0;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  89 */     this.countdown = ((short)(this.countdown + 1));
/*     */     
/*  91 */     if (this.countdown >= this.countdownmax) {
/*  92 */       if (this.field_145850_b.field_72995_K) {
/*  93 */         Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, 4194368, 1);
/*     */       } else {
/*  95 */         this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.oldblock, this.oldmeta, 0);
/*  96 */         recreateTileEntity();
/*     */       }
/*  98 */       this.field_145850_b.func_147464_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.oldblock, 2);
/*     */     }
/*     */   }
/*     */   
/*     */   private void surroundwithsparkles()
/*     */   {
/* 104 */     boolean yp = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).func_149662_c();
/* 105 */     boolean xp = this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e).func_149662_c();
/* 106 */     boolean zp = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1).func_149662_c();
/* 107 */     boolean yn = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e).func_149662_c();
/* 108 */     boolean xn = this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e).func_149662_c();
/* 109 */     boolean zn = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1).func_149662_c();
/* 110 */     boolean b1 = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) != ConfigBlocks.blockHole;
/* 111 */     boolean b2 = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) != ConfigBlocks.blockHole;
/* 112 */     boolean b3 = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) != ConfigBlocks.blockHole;
/* 113 */     boolean b4 = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) != ConfigBlocks.blockHole;
/* 114 */     boolean b5 = this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) != ConfigBlocks.blockHole;
/* 115 */     boolean b6 = this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) != ConfigBlocks.blockHole;
/*     */     
/* 117 */     if ((!xp) && (yp) && (b6)) Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
/* 118 */     if ((!xn) && (yp) && (b5)) Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
/* 119 */     if ((!zp) && (yp) && (b4)) Thaumcraft.proxy.sparkle(this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e + 1, 2);
/* 120 */     if ((!zn) && (yp) && (b3)) { Thaumcraft.proxy.sparkle(this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e, 2);
/*     */     }
/* 122 */     if ((!xp) && (yn) && (b6)) Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
/* 123 */     if ((!xn) && (yn) && (b5)) Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d, this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
/* 124 */     if ((!zp) && (yn) && (b4)) Thaumcraft.proxy.sparkle(this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d, this.field_145849_e + 1, 2);
/* 125 */     if ((!zn) && (yn) && (b3)) { Thaumcraft.proxy.sparkle(this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d, this.field_145849_e, 2);
/*     */     }
/*     */     
/* 128 */     if ((!yp) && (xp) && (b1)) Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
/* 129 */     if ((!yn) && (xp) && (b2)) Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
/* 130 */     if ((!zp) && (xp) && (b4)) Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e + 1, 2);
/* 131 */     if ((!zn) && (xp) && (b3)) { Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e, 2);
/*     */     }
/* 133 */     if ((!yp) && (xn) && (b1)) Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
/* 134 */     if ((!yn) && (xn) && (b2)) Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d, this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
/* 135 */     if ((!zp) && (xn) && (b4)) Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e + 1, 2);
/* 136 */     if ((!zn) && (xn) && (b3)) { Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e, 2);
/*     */     }
/*     */     
/* 139 */     if ((!xp) && (zp) && (b6)) Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e + 1, 2);
/* 140 */     if ((!xn) && (zp) && (b5)) Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e + 1, 2);
/* 141 */     if ((!yp) && (zp) && (b1)) Thaumcraft.proxy.sparkle(this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e + 1, 2);
/* 142 */     if ((!yn) && (zp) && (b2)) { Thaumcraft.proxy.sparkle(this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d, this.field_145849_e + 1, 2);
/*     */     }
/* 144 */     if ((!xp) && (zn) && (b6)) Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e, 2);
/* 145 */     if ((!xn) && (zn) && (b5)) Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e, 2);
/* 146 */     if ((!yp) && (zn) && (b1)) Thaumcraft.proxy.sparkle(this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e, 2);
/* 147 */     if ((!yn) && (zn) && (b2)) { Thaumcraft.proxy.sparkle(this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d, this.field_145849_e, 2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/* 154 */     super.func_145839_a(nbttagcompound);
/* 155 */     readCustomNBT(nbttagcompound);
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 160 */     this.countdown = nbttagcompound.func_74765_d("countdown");
/* 161 */     this.countdownmax = nbttagcompound.func_74765_d("countdownmax");
/* 162 */     this.count = nbttagcompound.func_74771_c("count");
/* 163 */     this.direction = nbttagcompound.func_74771_c("direction");
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/* 169 */     super.func_145841_b(nbttagcompound);
/* 170 */     writeCustomNBT(nbttagcompound);
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 175 */     nbttagcompound.func_74777_a("countdown", this.countdown);
/* 176 */     nbttagcompound.func_74777_a("countdownmax", this.countdownmax);
/* 177 */     nbttagcompound.func_74774_a("count", this.count);
/* 178 */     nbttagcompound.func_74774_a("direction", this.direction);
/*     */   }
/*     */   
/*     */ 
/*     */   public net.minecraft.network.Packet func_145844_m()
/*     */   {
/* 184 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 185 */     writeCustomNBT(nbttagcompound);
/* 186 */     return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 64537, nbttagcompound);
/*     */   }
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*     */   {
/* 191 */     super.onDataPacket(net, pkt);
/* 192 */     readCustomNBT(pkt.func_148857_g());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileHole.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */