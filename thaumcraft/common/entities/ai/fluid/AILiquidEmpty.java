/*    */ package thaumcraft.common.entities.ai.fluid;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import net.minecraft.util.ChunkCoordinates;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import net.minecraftforge.fluids.IFluidHandler;
/*    */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*    */ import thaumcraft.common.entities.golems.GolemHelper;
/*    */ 
/*    */ public class AILiquidEmpty extends EntityAIBase
/*    */ {
/*    */   private EntityGolemBase theGolem;
/*    */   private int waterX;
/*    */   private int waterY;
/*    */   private int waterZ;
/*    */   private ForgeDirection markerOrientation;
/*    */   private World theWorld;
/*    */   
/*    */   public AILiquidEmpty(EntityGolemBase par1EntityCreature)
/*    */   {
/* 26 */     this.theGolem = par1EntityCreature;
/* 27 */     this.theWorld = par1EntityCreature.field_70170_p;
/* 28 */     func_75248_a(3);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 37 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/* 38 */     if ((!this.theGolem.func_70661_as().func_75500_f()) || (this.theGolem.fluidCarried == null) || (this.theGolem.fluidCarried.amount == 0) || (this.theGolem.func_70092_e(home.field_71574_a + 0.5F, home.field_71572_b + 0.5F, home.field_71573_c + 0.5F) > 5.0D))
/*    */     {
/*    */ 
/*    */ 
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     ArrayList<FluidStack> fluids = GolemHelper.getMissingLiquids(this.theGolem);
/* 46 */     if (fluids == null) return false;
/* 47 */     for (FluidStack fluid : fluids) {
/* 48 */       if (fluid.isFluidEqual(this.theGolem.fluidCarried)) {
/* 49 */         return true;
/*    */       }
/*    */     }
/* 52 */     return false;
/*    */   }
/*    */   
/*    */   public boolean func_75253_b()
/*    */   {
/* 57 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 67 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/* 68 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/* 69 */     int cX = home.field_71574_a - facing.offsetX;
/* 70 */     int cY = home.field_71572_b - facing.offsetY;
/* 71 */     int cZ = home.field_71573_c - facing.offsetZ;
/*    */     
/* 73 */     net.minecraft.tileentity.TileEntity tile = this.theWorld.func_147438_o(cX, cY, cZ);
/* 74 */     if ((tile != null) && ((tile instanceof IFluidHandler))) {
/* 75 */       IFluidHandler fh = (IFluidHandler)tile;
/* 76 */       int amt = fh.fill(ForgeDirection.getOrientation(this.theGolem.homeFacing), this.theGolem.fluidCarried, true);
/* 77 */       this.theGolem.fluidCarried.amount -= amt;
/* 78 */       if (this.theGolem.fluidCarried.amount <= 0)
/* 79 */         this.theGolem.fluidCarried = null;
/* 80 */       if (amt > 200)
/* 81 */         this.theWorld.func_72956_a(this.theGolem, "game.neutral.swim", Math.min(0.2F, 0.2F * (amt / this.theGolem.getFluidCarryLimit())), 1.0F + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3F);
/* 82 */       this.theGolem.updateCarried();
/* 83 */       this.theWorld.func_147471_g(cX, cY, cZ);
/* 84 */       this.theGolem.itemWatched = null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/fluid/AILiquidEmpty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */