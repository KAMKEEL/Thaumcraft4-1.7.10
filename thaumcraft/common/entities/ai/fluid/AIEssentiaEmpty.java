/*     */ package thaumcraft.common.entities.ai.fluid;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.GolemHelper;
/*     */ import thaumcraft.common.tiles.TileEssentiaReservoir;
/*     */ import thaumcraft.common.tiles.TileJarFillable;
/*     */ 
/*     */ public class AIEssentiaEmpty extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private int jarX;
/*     */   private int jarY;
/*     */   private int jarZ;
/*     */   private ForgeDirection markerOrientation;
/*     */   private World theWorld;
/*     */   
/*     */   public AIEssentiaEmpty(EntityGolemBase par1EntityCreature)
/*     */   {
/*  25 */     this.theGolem = par1EntityCreature;
/*  26 */     this.theWorld = par1EntityCreature.field_70170_p;
/*  27 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  36 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  37 */     if ((!this.theGolem.func_70661_as().func_75500_f()) || (this.theGolem.essentia == null) || (this.theGolem.essentiaAmount == 0))
/*     */     {
/*     */ 
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     ChunkCoordinates jarloc = GolemHelper.findJarWithRoom(this.theGolem);
/*  44 */     if (jarloc == null) {
/*  45 */       return false;
/*     */     }
/*  47 */     if (this.theGolem.func_70092_e(jarloc.field_71574_a + 0.5D, jarloc.field_71572_b + 0.5D, jarloc.field_71573_c + 0.5D) > 4.0D) {
/*  48 */       return false;
/*     */     }
/*  50 */     this.jarX = jarloc.field_71574_a;
/*  51 */     this.jarY = jarloc.field_71572_b;
/*  52 */     this.jarZ = jarloc.field_71573_c;
/*  53 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_75253_b()
/*     */   {
/*  58 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  68 */     net.minecraft.tileentity.TileEntity tile = this.theWorld.func_147438_o(this.jarX, this.jarY, this.jarZ);
/*  69 */     if ((tile != null) && ((tile instanceof TileJarFillable))) {
/*  70 */       TileJarFillable jar = (TileJarFillable)tile;
/*  71 */       this.theGolem.essentiaAmount = jar.addToContainer(this.theGolem.essentia, this.theGolem.essentiaAmount);
/*  72 */       if (this.theGolem.essentiaAmount == 0) {
/*  73 */         this.theGolem.essentia = null;
/*     */       }
/*  75 */       this.theWorld.func_72956_a(this.theGolem, "game.neutral.swim", 0.2F, 1.0F + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3F);
/*  76 */       this.theGolem.updateCarried();
/*  77 */       this.theWorld.func_147471_g(this.jarX, this.jarY, this.jarZ);
/*     */     }
/*  79 */     else if ((tile != null) && ((tile instanceof TileEssentiaReservoir))) {
/*  80 */       TileEssentiaReservoir trans = (TileEssentiaReservoir)tile;
/*  81 */       if ((trans.getSuctionAmount(trans.facing) > 0) && ((trans.getSuctionType(trans.facing) == null) || (trans.getSuctionType(trans.facing) == this.theGolem.essentia)))
/*     */       {
/*  83 */         int added = trans.addEssentia(this.theGolem.essentia, this.theGolem.essentiaAmount, trans.facing);
/*  84 */         if (added > 0) {
/*  85 */           this.theGolem.essentiaAmount -= added;
/*  86 */           if (this.theGolem.essentiaAmount == 0) {
/*  87 */             this.theGolem.essentia = null;
/*     */           }
/*  89 */           this.theWorld.func_72956_a(this.theGolem, "game.neutral.swim", 0.2F, 1.0F + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3F);
/*  90 */           this.theGolem.updateCarried();
/*  91 */           this.theWorld.func_147471_g(this.jarX, this.jarY, this.jarZ);
/*     */         }
/*     */       }
/*     */     }
/*  95 */     else if ((tile != null) && ((tile instanceof IEssentiaTransport))) {
/*  96 */       for (Integer side : GolemHelper.getMarkedSides(this.theGolem, tile, (byte)-1)) {
/*  97 */         IEssentiaTransport trans = (IEssentiaTransport)tile;
/*  98 */         if ((trans.canInputFrom(ForgeDirection.getOrientation(side.intValue()))) && (trans.getSuctionAmount(ForgeDirection.getOrientation(side.intValue())) > 0) && ((trans.getSuctionType(ForgeDirection.getOrientation(side.intValue())) == null) || (trans.getSuctionType(ForgeDirection.getOrientation(side.intValue())) == this.theGolem.essentia)))
/*     */         {
/*     */ 
/*     */ 
/* 102 */           int added = trans.addEssentia(this.theGolem.essentia, this.theGolem.essentiaAmount, ForgeDirection.getOrientation(side.intValue()));
/* 103 */           if (added > 0) {
/* 104 */             this.theGolem.essentiaAmount -= added;
/* 105 */             if (this.theGolem.essentiaAmount == 0) {
/* 106 */               this.theGolem.essentia = null;
/*     */             }
/* 108 */             this.theWorld.func_72956_a(this.theGolem, "game.neutral.swim", 0.2F, 1.0F + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3F);
/* 109 */             this.theGolem.updateCarried();
/* 110 */             this.theWorld.func_147471_g(this.jarX, this.jarY, this.jarZ);
/* 111 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/fluid/AIEssentiaEmpty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */