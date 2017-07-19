/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
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
/*     */ 
/*     */ public class TileAlchemyFurnaceAdvancedNozzle
/*     */   extends TileThaumcraft
/*     */   implements IAspectContainer, IEssentiaTransport
/*     */ {
/*  28 */   ForgeDirection facing = ForgeDirection.UNKNOWN;
/*  29 */   public TileAlchemyFurnaceAdvanced furnace = null;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  33 */     return this.facing != null;
/*     */   }
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/*  38 */     if ((this.facing == ForgeDirection.UNKNOWN) && (this.furnace == null)) {
/*  39 */       this.facing = null;
/*  40 */       for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/*  41 */         TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
/*  42 */         if ((tile != null) && ((tile instanceof TileAlchemyFurnaceAdvanced))) {
/*  43 */           this.facing = dir.getOpposite();
/*  44 */           this.furnace = ((TileAlchemyFurnaceAdvanced)tile);
/*  45 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/*  53 */     return this.furnace != null ? this.furnace.aspects : null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  62 */     return am;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/*  67 */     if (this.furnace == null) return false;
/*  68 */     if (this.furnace.aspects.getAmount(tt) >= am) {
/*  69 */       this.furnace.aspects.remove(tt, am);
/*  70 */       this.furnace.func_70296_d();
/*  71 */       this.furnace.vis = this.furnace.aspects.visSize();
/*  72 */       this.field_145850_b.func_147471_g(this.furnace.field_145851_c, this.furnace.field_145848_d, this.furnace.field_145849_e);
/*  73 */       return true;
/*     */     }
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tt, int am)
/*     */   {
/*  85 */     if (this.furnace == null) return false;
/*  86 */     if (this.furnace.aspects.getAmount(tt) >= am) return true;
/*  87 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tt)
/*     */   {
/*  92 */     if (this.furnace == null) return 0;
/*  93 */     return this.furnace.aspects.getAmount(tt);
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/*  98 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 103 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 111 */     return face == this.facing;
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 121 */     return face == this.facing;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 130 */     return false;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 135 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection face)
/*     */   {
/* 140 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection face)
/*     */   {
/* 145 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 150 */     return this.furnace != null ? this.furnace.aspects.getAspects()[0] : null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 155 */     return (this.furnace != null ? Integer.valueOf(this.furnace.aspects.getAmount(this.furnace.aspects.getAspects()[0])) : null).intValue();
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection facing)
/*     */   {
/* 160 */     return (canOutputTo(facing)) && (takeFromContainer(aspect, amount)) ? amount : 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection facing)
/*     */   {
/* 165 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileAlchemyFurnaceAdvancedNozzle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */