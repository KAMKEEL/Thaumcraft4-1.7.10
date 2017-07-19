/*     */ package thaumcraft.common.entities.ai.fluid;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.tiles.TileAlembic;
/*     */ import thaumcraft.common.tiles.TileEssentiaReservoir;
/*     */ import thaumcraft.common.tiles.TileJarFillable;
/*     */ 
/*     */ public class AIEssentiaGather extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private double crucX;
/*     */   private double crucY;
/*     */   private double crucZ;
/*     */   private World theWorld;
/*  24 */   private long delay = 0L;
/*     */   
/*     */   public AIEssentiaGather(EntityGolemBase par1EntityCreature)
/*     */   {
/*  28 */     this.theGolem = par1EntityCreature;
/*  29 */     this.theWorld = par1EntityCreature.field_70170_p;
/*  30 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  38 */     if ((!this.theGolem.func_70661_as().func_75500_f()) || (this.delay > System.currentTimeMillis()))
/*     */     {
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  44 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  45 */     int cX = home.field_71574_a - facing.offsetX;
/*  46 */     int cY = home.field_71572_b - facing.offsetY;
/*  47 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/*  49 */     if (this.theGolem.func_70092_e(cX + 0.5F, cY + 0.5F, cZ + 0.5F) > 6.0D) {
/*  50 */       return false;
/*     */     }
/*  52 */     this.start = 0;
/*  53 */     TileEntity te = this.theWorld.func_147438_o(cX, cY, cZ);
/*     */     
/*  55 */     if (te != null) {
/*  56 */       if ((te instanceof IEssentiaTransport)) {
/*  57 */         IEssentiaTransport etrans = (IEssentiaTransport)te;
/*  58 */         if ((((te instanceof TileJarFillable)) || ((te instanceof TileEssentiaReservoir)) || (etrans.canOutputTo(facing))) && (etrans.getEssentiaAmount(facing) > 0))
/*     */         {
/*  60 */           if ((this.theGolem.essentiaAmount == 0) || (((this.theGolem.essentia == null) || (this.theGolem.essentia.equals(etrans.getEssentiaType(facing))) || (this.theGolem.essentia.equals(etrans.getEssentiaType(ForgeDirection.UNKNOWN)))) && (this.theGolem.essentiaAmount < this.theGolem.getCarryLimit())))
/*     */           {
/*     */ 
/*     */ 
/*  64 */             this.delay = (System.currentTimeMillis() + 1000L);
/*  65 */             this.start = 0;
/*     */             
/*  67 */             return true;
/*     */           }
/*     */         }
/*     */       } else {
/*  71 */         int a = 5;
/*  72 */         this.start = -1;
/*  73 */         int prevTot = -1;
/*  74 */         for (; a >= 0; a--) {
/*  75 */           te = this.theWorld.func_147438_o(cX, cY + a, cZ);
/*  76 */           if ((te != null) && ((te instanceof TileAlembic))) {
/*  77 */             TileAlembic ta = (TileAlembic)te;
/*  78 */             if ((this.theGolem.essentiaAmount == 0) || (((this.theGolem.essentia == null) || (this.theGolem.essentia.equals(ta.aspect))) && (this.theGolem.essentiaAmount < this.theGolem.getCarryLimit())))
/*     */             {
/*     */ 
/*  81 */               if (ta.amount > prevTot) {
/*  82 */                 this.delay = (System.currentTimeMillis() + 1000L);
/*  83 */                 this.start = a;
/*  84 */                 prevTot = ta.amount;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*  90 */         if (this.start >= 0) {
/*  91 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*  95 */     return false;
/*     */   }
/*     */   
/*  98 */   int start = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 105 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/* 106 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/* 107 */     int cX = home.field_71574_a - facing.offsetX;
/* 108 */     int cY = home.field_71572_b - facing.offsetY;
/* 109 */     int cZ = home.field_71573_c - facing.offsetZ;
/* 110 */     TileEntity te = this.theWorld.func_147438_o(cX, cY + this.start, cZ);
/* 111 */     if ((te != null) && ((te instanceof IEssentiaTransport))) {
/* 112 */       if (((te instanceof TileAlembic)) || ((te instanceof TileJarFillable))) {
/* 113 */         facing = ForgeDirection.UP;
/*     */       }
/* 115 */       if ((te instanceof TileEssentiaReservoir)) {
/* 116 */         facing = ((TileEssentiaReservoir)te).facing;
/*     */       }
/* 118 */       IEssentiaTransport ta = (IEssentiaTransport)te;
/* 119 */       if (ta.getEssentiaAmount(facing) == 0) return;
/* 120 */       if ((ta.canOutputTo(facing)) && (ta.getEssentiaAmount(facing) > 0) && (
/* 121 */         (this.theGolem.essentiaAmount == 0) || (((this.theGolem.essentia == null) || (this.theGolem.essentia.equals(ta.getEssentiaType(facing))) || (this.theGolem.essentia.equals(ta.getEssentiaType(ForgeDirection.UNKNOWN)))) && (this.theGolem.essentiaAmount < this.theGolem.getCarryLimit()))))
/*     */       {
/*     */ 
/*     */ 
/* 125 */         Aspect a = ta.getEssentiaType(facing);
/* 126 */         if (a == null) a = ta.getEssentiaType(ForgeDirection.UNKNOWN);
/* 127 */         int qq = ta.getEssentiaAmount(facing);
/* 128 */         if ((te instanceof TileEssentiaReservoir)) qq = ((TileEssentiaReservoir)te).containerContains(a);
/* 129 */         int am = Math.min(qq, this.theGolem.getCarryLimit() - this.theGolem.essentiaAmount);
/* 130 */         this.theGolem.essentia = a;
/* 131 */         int taken = ta.takeEssentia(a, am, facing);
/* 132 */         if (taken > 0) {
/* 133 */           this.theGolem.essentiaAmount += taken;
/* 134 */           this.theWorld.func_72956_a(this.theGolem, "game.neutral.swim", 0.05F, 1.0F + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3F);
/* 135 */           this.theGolem.updateCarried();
/* 136 */         } else { this.theGolem.essentia = null; }
/* 137 */         this.delay = (System.currentTimeMillis() + 100L);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/fluid/AIEssentiaGather.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */