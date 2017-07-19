/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
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
/*     */ public class TileArcaneFurnaceNozzle
/*     */   extends TileThaumcraft
/*     */   implements IEssentiaTransport
/*     */ {
/*  26 */   ForgeDirection facing = ForgeDirection.UNKNOWN;
/*  27 */   TileArcaneFurnace furnace = null;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  31 */     return this.facing != null;
/*     */   }
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/*  36 */     if ((this.facing == ForgeDirection.UNKNOWN) && (this.furnace == null)) {
/*  37 */       this.facing = null;
/*  38 */       for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/*  39 */         TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
/*  40 */         if ((tile != null) && ((tile instanceof TileArcaneFurnace))) {
/*  41 */           this.facing = dir.getOpposite();
/*  42 */           this.furnace = ((TileArcaneFurnace)tile);
/*  43 */           break;
/*     */         }
/*     */       }
/*     */     }
/*  47 */     if (!this.field_145850_b.field_72995_K) {
/*     */       try {
/*  49 */         if ((this.furnace != null) && (this.furnace.speedyTime < 60) && 
/*  50 */           (drawEssentia())) { this.furnace.speedyTime += 600;
/*     */         }
/*     */       }
/*     */       catch (Exception e) {}
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  60 */   int drawDelay = 0;
/*     */   
/*     */   boolean drawEssentia() {
/*  63 */     if (++this.drawDelay % 5 != 0) return false;
/*  64 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.facing);
/*  65 */     if (te != null) {
/*  66 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/*  67 */       if (!ic.canOutputTo(this.facing.getOpposite())) return false;
/*  68 */       if ((ic.getSuctionAmount(this.facing.getOpposite()) < getSuctionAmount(this.facing)) && (ic.takeEssentia(Aspect.FIRE, 1, this.facing.getOpposite()) == 1))
/*     */       {
/*  70 */         return true;
/*     */       }
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/*  78 */     return this.facing != null;
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/*  83 */     return this.facing != null;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/*  88 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public boolean renderExtendedTube()
/*     */   {
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 102 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection face)
/*     */   {
/* 107 */     return Aspect.FIRE;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection face)
/*     */   {
/*     */     try {
/* 113 */       if ((this.furnace != null) && (this.furnace.speedyTime < 40)) {
/* 114 */         return 128;
/*     */       }
/*     */     } catch (Exception e) {}
/* 117 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 122 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 127 */     return 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection facing)
/*     */   {
/* 132 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection facing)
/*     */   {
/* 137 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileArcaneFurnaceNozzle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */