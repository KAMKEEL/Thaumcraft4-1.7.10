/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.BlockCoordinates;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
/*     */ import thaumcraft.common.lib.utils.CropUtils;
/*     */ 
/*     */ public class TileArcaneLampGrowth extends TileThaumcraft implements IEssentiaTransport
/*     */ {
/*  31 */   public ForgeDirection facing = ForgeDirection.getOrientation(0);
/*  32 */   private boolean reserve = false;
/*  33 */   public int charges = -1;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  37 */     return true;
/*     */   }
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*     */   {
/*  42 */     super.onDataPacket(net, pkt);
/*  43 */     if ((this.field_145850_b != null) && 
/*  44 */       (this.field_145850_b.field_72995_K)) {
/*  45 */       this.field_145850_b.func_147463_c(EnumSkyBlock.Block, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/*  52 */     if (!this.field_145850_b.field_72995_K) {
/*  53 */       if (this.charges <= 0) {
/*  54 */         if (this.reserve) {
/*  55 */           this.charges = 100;
/*  56 */           this.reserve = false;
/*  57 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*  59 */         else if (drawEssentia()) {
/*  60 */           this.charges = 100;
/*  61 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */       }
/*     */       
/*  65 */       if ((!this.reserve) && 
/*  66 */         (drawEssentia())) {
/*  67 */         this.reserve = true;
/*     */       }
/*     */       
/*     */ 
/*  71 */       if (this.charges == 0) {
/*  72 */         this.charges = -1;
/*  73 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */       
/*  76 */       if (this.charges > 0) {
/*  77 */         updatePlant();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   boolean isPlant(int x, int y, int z)
/*     */   {
/*  85 */     boolean flag = this.field_145850_b.func_147439_a(x, y, z) instanceof IGrowable;
/*  86 */     Material mat = this.field_145850_b.func_147439_a(x, y, z).func_149688_o();
/*  87 */     return ((flag) || (mat == Material.field_151570_A) || (mat == Material.field_151585_k)) && (mat != Material.field_151577_b);
/*     */   }
/*     */   
/*  90 */   int lx = 0;
/*  91 */   int ly = 0;
/*  92 */   int lz = 0;
/*  93 */   Block lid = Blocks.field_150350_a;
/*  94 */   int lmd = 0;
/*     */   
/*  96 */   ArrayList<BlockCoordinates> checklist = new ArrayList();
/*     */   
/*     */   private void updatePlant()
/*     */   {
/* 100 */     if ((this.lid != this.field_145850_b.func_147439_a(this.lx, this.ly, this.lz)) || (this.lmd != this.field_145850_b.func_72805_g(this.lx, this.ly, this.lz))) {
/* 101 */       EntityPlayer p = this.field_145850_b.func_72977_a(this.lx, this.ly, this.lz, 32.0D);
/* 102 */       if (p != null) {
/* 103 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(this.lx, this.ly, this.lz, 4259648), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, this.lx, this.ly, this.lz, 32.0D));
/*     */       }
/*     */       
/*     */ 
/* 107 */       this.lid = this.field_145850_b.func_147439_a(this.lx, this.ly, this.lz);
/* 108 */       this.lmd = this.field_145850_b.func_72805_g(this.lx, this.ly, this.lz);
/*     */     }
/*     */     
/* 111 */     int distance = 6;
/*     */     
/* 113 */     if (this.checklist.size() == 0) {
/* 114 */       for (int a = -distance; a <= distance; a++) {
/* 115 */         for (int b = -distance; b <= distance; b++)
/* 116 */           this.checklist.add(new BlockCoordinates(this.field_145851_c + a, this.field_145848_d + distance, this.field_145849_e + b));
/*     */       }
/* 118 */       Collections.shuffle(this.checklist, this.field_145850_b.field_73012_v);
/*     */     }
/*     */     
/* 121 */     int x = ((BlockCoordinates)this.checklist.get(0)).x;
/* 122 */     int y = ((BlockCoordinates)this.checklist.get(0)).y;
/* 123 */     int z = ((BlockCoordinates)this.checklist.get(0)).z;
/* 124 */     this.checklist.remove(0);
/* 126 */     for (; 
/* 126 */         y >= this.field_145848_d - distance; y--) {
/* 127 */       if ((!this.field_145850_b.func_147437_c(x, y, z)) && (isPlant(x, y, z)) && (func_145835_a(x + 0.5D, y + 0.5D, z + 0.5D) < distance * distance) && (!CropUtils.isGrownCrop(this.field_145850_b, x, y, z)) && (CropUtils.doesLampGrow(this.field_145850_b, x, y, z)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 132 */         this.charges -= 1;
/*     */         
/* 134 */         this.lx = x;
/* 135 */         this.ly = y;
/* 136 */         this.lz = z;
/* 137 */         this.lid = this.field_145850_b.func_147439_a(x, y, z);
/* 138 */         this.lmd = this.field_145850_b.func_72805_g(x, y, z);
/*     */         
/* 140 */         this.field_145850_b.func_147464_a(x, y, z, this.field_145850_b.func_147439_a(x, y, z), 1);
/* 141 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 150 */     this.facing = ForgeDirection.getOrientation(nbttagcompound.func_74762_e("orientation"));
/* 151 */     this.reserve = nbttagcompound.func_74767_n("reserve");
/* 152 */     this.charges = nbttagcompound.func_74762_e("charges");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 158 */     nbttagcompound.func_74768_a("orientation", this.facing.ordinal());
/* 159 */     nbttagcompound.func_74757_a("reserve", this.reserve);
/* 160 */     nbttagcompound.func_74768_a("charges", this.charges);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 165 */   int drawDelay = 0;
/*     */   
/*     */   boolean drawEssentia() {
/* 168 */     if (++this.drawDelay % 5 != 0) return false;
/* 169 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.facing);
/* 170 */     if (te != null) {
/* 171 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 172 */       if (!ic.canOutputTo(this.facing.getOpposite())) return false;
/* 173 */       if ((ic.getSuctionAmount(this.facing.getOpposite()) < getSuctionAmount(this.facing)) && (ic.takeEssentia(Aspect.PLANT, 1, this.facing.getOpposite()) == 1))
/*     */       {
/* 175 */         return true;
/*     */       }
/*     */     }
/* 178 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 183 */     return face == this.facing;
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 188 */     return face == this.facing;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 193 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 202 */     return false;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 207 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection face)
/*     */   {
/* 212 */     return Aspect.PLANT;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection face)
/*     */   {
/* 217 */     return (face == this.facing) && ((!this.reserve) || (this.charges <= 0)) ? 128 : 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 222 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 227 */     return 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection loc)
/*     */   {
/* 232 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection loc)
/*     */   {
/* 237 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileArcaneLampGrowth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */