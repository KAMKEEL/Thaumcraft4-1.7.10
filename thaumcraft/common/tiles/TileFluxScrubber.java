/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.BlockCoordinates;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.visnet.VisNetHandler;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
/*     */ 
/*     */ public class TileFluxScrubber extends TileThaumcraft implements thaumcraft.api.aspects.IEssentiaTransport
/*     */ {
/*  22 */   public int essentia = 0;
/*  23 */   public int charges = 0;
/*  24 */   public int power = 0;
/*  25 */   public ForgeDirection facing = ForgeDirection.getOrientation(0);
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  29 */     return true;
/*     */   }
/*     */   
/*  32 */   public int count = 0;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/*  36 */     if (this.count == 0) this.count = this.field_145850_b.field_73012_v.nextInt(1000);
/*  37 */     if (!this.field_145850_b.field_72995_K) {
/*  38 */       if (this.charges >= 4) {
/*  39 */         this.charges -= 4;
/*  40 */         if (this.field_145850_b.field_73012_v.nextInt(4) == 0) {
/*  41 */           this.essentia += 1;
/*  42 */           if (this.essentia > 4) this.essentia = 4;
/*  43 */           func_70296_d();
/*     */         }
/*     */       }
/*     */       
/*  47 */       if (this.power < 5) {
/*  48 */         this.power += VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.AIR, 10);
/*     */       }
/*  50 */       if (this.power >= 5) {
/*  51 */         checkFlux();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   boolean isFlux(int x, int y, int z) {
/*  57 */     net.minecraft.block.material.Material mat = this.field_145850_b.func_147439_a(x, y, z).func_149688_o();
/*  58 */     return mat == Config.fluxGoomaterial;
/*     */   }
/*     */   
/*     */ 
/*  62 */   ArrayList<BlockCoordinates> checklist = new ArrayList();
/*     */   
/*     */   private void checkFlux()
/*     */   {
/*  66 */     int distance = 16;
/*     */     
/*  68 */     if (this.checklist.size() == 0) {
/*  69 */       for (int a = -distance; a <= distance; a++) {
/*  70 */         for (int c = -distance; c <= distance; c++)
/*  71 */           for (int b = -distance; b <= distance; b++)
/*  72 */             this.checklist.add(new BlockCoordinates(this.field_145851_c + a, this.field_145848_d + c, this.field_145849_e + b));
/*     */       }
/*  74 */       java.util.Collections.shuffle(this.checklist, this.field_145850_b.field_73012_v);
/*     */     }
/*  76 */     int x = 0;
/*  77 */     int y = 0;
/*  78 */     int z = 0;
/*  79 */     int cc = 0;
/*  80 */     while ((cc < 16) && (this.checklist.size() > 0)) {
/*  81 */       cc++;
/*  82 */       x = ((BlockCoordinates)this.checklist.get(0)).x;
/*  83 */       y = ((BlockCoordinates)this.checklist.get(0)).y;
/*  84 */       z = ((BlockCoordinates)this.checklist.get(0)).z;
/*  85 */       this.checklist.remove(0);
/*  86 */       if ((!this.field_145850_b.func_147437_c(x, y, z)) && (isFlux(x, y, z)) && (func_145835_a(x + 0.5D, y + 0.5D, z + 0.5D) < distance * distance))
/*     */       {
/*     */ 
/*  89 */         this.power -= 5;
/*     */         
/*  91 */         int lmd = this.field_145850_b.func_72805_g(x, y, z);
/*     */         
/*  93 */         if (lmd > 0) {
/*  94 */           this.field_145850_b.func_72921_c(x, y, z, lmd - 1, 3);
/*     */         } else {
/*  96 */           this.field_145850_b.func_147468_f(x, y, z);
/*     */         }
/*     */         
/*  99 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(x, y, z, 14483711), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, x, y, z, 32.0D));
/*     */         
/*     */ 
/*     */ 
/* 103 */         this.charges += 1;
/*     */         
/* 105 */         func_70296_d();
/*     */         
/* 107 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 116 */     this.facing = ForgeDirection.getOrientation(nbttagcompound.func_74762_e("facing"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 122 */     nbttagcompound.func_74768_a("facing", this.facing.ordinal());
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/* 128 */     super.func_145839_a(nbttagcompound);
/* 129 */     this.charges = nbttagcompound.func_74762_e("charges");
/* 130 */     this.power = nbttagcompound.func_74762_e("power");
/* 131 */     this.essentia = nbttagcompound.func_74762_e("essentia");
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/* 136 */     super.func_145841_b(nbttagcompound);
/* 137 */     nbttagcompound.func_74768_a("charges", this.charges);
/* 138 */     nbttagcompound.func_74768_a("power", this.power);
/* 139 */     nbttagcompound.func_74768_a("essentia", this.essentia);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 147 */     return face == this.facing;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 152 */     return face == this.facing;
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 157 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 166 */     return false;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 171 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection face)
/*     */   {
/* 176 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection face)
/*     */   {
/* 181 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 186 */     return Aspect.MAGIC;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 191 */     return this.essentia;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection loc)
/*     */   {
/* 196 */     int re = Math.min(this.essentia, amount);
/* 197 */     this.essentia -= re;
/* 198 */     func_70296_d();
/* 199 */     return re;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection loc)
/*     */   {
/* 204 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileFluxScrubber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */