/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ 
/*     */ public class TileArcaneLampFertility
/*     */   extends TileThaumcraft
/*     */   implements IEssentiaTransport
/*     */ {
/*  25 */   public ForgeDirection facing = ForgeDirection.getOrientation(0);
/*  26 */   public int charges = 0;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  30 */     return true;
/*     */   }
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*     */   {
/*  35 */     super.onDataPacket(net, pkt);
/*  36 */     if ((this.field_145850_b != null) && 
/*  37 */       (this.field_145850_b.field_72995_K)) {
/*  38 */       this.field_145850_b.func_147463_c(EnumSkyBlock.Block, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*  43 */   int count = 0;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/*  47 */     if (!this.field_145850_b.field_72995_K) {
/*  48 */       if ((this.charges < 4) && 
/*  49 */         (drawEssentia())) {
/*  50 */         this.charges += 1;
/*  51 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */       
/*     */ 
/*  55 */       if ((this.charges > 1) && (this.count++ % 300 == 0)) {
/*  56 */         updateAnimals();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void updateAnimals()
/*     */   {
/*  65 */     int distance = 7;
/*     */     
/*  67 */     List<EntityAnimal> var5 = this.field_145850_b.func_72872_a(EntityAnimal.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(distance, distance, distance));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  72 */     Iterator var2 = var5.iterator();
/*     */     
/*  74 */     while (var2.hasNext())
/*     */     {
/*  76 */       EntityAnimal var3 = (EntityAnimal)var2.next();
/*     */       
/*     */ 
/*  79 */       EntityLivingBase var4 = var3;
/*     */       
/*  81 */       if ((var3.func_70874_b() == 0) && (!var3.func_70880_s()))
/*     */       {
/*  83 */         ArrayList<EntityAnimal> sa = new ArrayList();
/*  84 */         Iterator varq = var5.iterator();
/*  85 */         while (varq.hasNext())
/*     */         {
/*  87 */           EntityLivingBase var7 = (EntityLivingBase)varq.next();
/*  88 */           if (var7.getClass().equals(var4.getClass())) {
/*  89 */             sa.add((EntityAnimal)var7);
/*     */           }
/*     */         }
/*     */         
/*  93 */         if ((sa == null) || (sa.size() <= 7)) {
/*  94 */           Iterator var22 = sa.iterator();
/*  95 */           EntityAnimal partner = null;
/*  96 */           while (var22.hasNext())
/*     */           {
/*     */ 
/*  99 */             EntityAnimal var33 = (EntityAnimal)var22.next();
/* 100 */             if ((var33.func_70874_b() == 0) && (!var33.func_70880_s())) {
/* 101 */               if (partner == null) {
/* 102 */                 partner = var33;
/*     */               } else {
/* 104 */                 this.charges -= 2;
/* 105 */                 var33.func_146082_f(null);
/* 106 */                 partner.func_146082_f(null);
/* 107 */                 return;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound) {
/* 117 */     this.facing = ForgeDirection.getOrientation(nbttagcompound.func_74762_e("orientation"));
/* 118 */     this.charges = nbttagcompound.func_74762_e("charges");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 124 */     nbttagcompound.func_74768_a("orientation", this.facing.ordinal());
/* 125 */     nbttagcompound.func_74768_a("charges", this.charges);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 130 */   int drawDelay = 0;
/*     */   
/*     */   boolean drawEssentia() {
/* 133 */     if (++this.drawDelay % 5 != 0) return false;
/* 134 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.facing);
/* 135 */     if (te != null) {
/* 136 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 137 */       if (!ic.canOutputTo(this.facing.getOpposite())) return false;
/* 138 */       if ((ic.getSuctionAmount(this.facing.getOpposite()) < getSuctionAmount(this.facing)) && (ic.takeEssentia(Aspect.LIFE, 1, this.facing.getOpposite()) == 1))
/*     */       {
/* 140 */         return true;
/*     */       }
/*     */     }
/* 143 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 148 */     return face == this.facing;
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 153 */     return face == this.facing;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 158 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */ 
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 167 */     return false;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 172 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection face)
/*     */   {
/* 177 */     return Aspect.LIFE;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection face)
/*     */   {
/* 182 */     return face == this.facing ? 128 - this.charges * 10 : 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 187 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 192 */     return 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection facing)
/*     */   {
/* 197 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection facing)
/*     */   {
/* 202 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileArcaneLampFertility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */