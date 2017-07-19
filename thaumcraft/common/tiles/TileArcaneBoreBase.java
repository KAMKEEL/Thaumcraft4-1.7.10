/*     */ package thaumcraft.common.tiles;
/*     */ 
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
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ 
/*     */ public class TileArcaneBoreBase
/*     */   extends TileThaumcraft implements IWandable, IEssentiaTransport
/*     */ {
/*  19 */   public ForgeDirection orientation = ForgeDirection.getOrientation(2);
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  23 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  31 */     this.orientation = ForgeDirection.getOrientation(nbttagcompound.func_74762_e("orientation"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  37 */     nbttagcompound.func_74768_a("orientation", this.orientation.ordinal());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/*  44 */     this.orientation = ForgeDirection.getOrientation(side);
/*  45 */     player.field_70170_p.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:tool", 0.3F, 1.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*  46 */     player.func_71038_i();
/*  47 */     func_70296_d();
/*  48 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*     */   {
/*  55 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */   boolean drawEssentia()
/*     */   {
/*  71 */     for (ForgeDirection facing : ForgeDirection.VALID_DIRECTIONS) {
/*  72 */       TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, facing);
/*  73 */       if (te != null) {
/*  74 */         IEssentiaTransport ic = (IEssentiaTransport)te;
/*  75 */         if (!ic.canOutputTo(facing.getOpposite())) return false;
/*  76 */         if ((ic.getSuctionAmount(facing.getOpposite()) < getSuctionAmount(facing)) && (ic.takeEssentia(Aspect.ENTROPY, 1, facing.getOpposite()) == 1))
/*     */         {
/*  78 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*  82 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/*  93 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/*  98 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public Aspect getSuctionType(ForgeDirection face)
/*     */   {
/* 107 */     return Aspect.ENTROPY;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection face)
/*     */   {
/* 112 */     return face != this.orientation ? 128 : 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 117 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 122 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection face)
/*     */   {
/* 127 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection face)
/*     */   {
/* 132 */     return 0;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 137 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 142 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileArcaneBoreBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */