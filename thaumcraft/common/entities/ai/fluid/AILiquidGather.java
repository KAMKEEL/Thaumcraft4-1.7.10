/*     */ package thaumcraft.common.entities.ai.fluid;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.BlockFluidBase;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidBlock;
/*     */ import net.minecraftforge.fluids.IFluidHandler;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.GolemHelper;
/*     */ import thaumcraft.common.entities.golems.Marker;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AILiquidGather
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private int waterX;
/*     */   private int waterY;
/*     */   private int waterZ;
/*     */   private ForgeDirection markerOrientation;
/*     */   private World theWorld;
/*  38 */   private float pumpDist = 0.0F;
/*     */   
/*     */   public AILiquidGather(EntityGolemBase par1EntityCreature)
/*     */   {
/*  42 */     this.theGolem = par1EntityCreature;
/*  43 */     this.theWorld = par1EntityCreature.field_70170_p;
/*  44 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  55 */     ArrayList<FluidStack> fluids = GolemHelper.getMissingLiquids(this.theGolem);
/*  56 */     if (fluids == null) return false;
/*  57 */     if ((this.theGolem.itemWatched == null) || (fluids.size() == 0) || (!this.theGolem.func_70661_as().func_75500_f()))
/*     */     {
/*     */ 
/*     */ 
/*  61 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  66 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  67 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  68 */     int cX = home.field_71574_a - facing.offsetX;
/*  69 */     int cY = home.field_71572_b - facing.offsetY;
/*  70 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/*  72 */     int camt = 0;
/*  73 */     if (this.theGolem.fluidCarried != null) {
/*  74 */       camt = this.theGolem.fluidCarried.amount;
/*     */     }
/*  76 */     int max = this.theGolem.getFluidCarryLimit();
/*     */     
/*  78 */     for (Iterator i$ = fluids.iterator(); i$.hasNext();) { fluid = (FluidStack)i$.next();
/*     */       
/*  80 */       ArrayList<Marker> markers = GolemHelper.getMarkedFluidHandlersAdjacentToGolem(fluid, this.theWorld, this.theGolem);
/*  81 */       for (Marker marker : markers) {
/*  82 */         TileEntity te = this.theWorld.func_147438_o(marker.x, marker.y, marker.z);
/*  83 */         if ((te != null) && ((te instanceof IFluidHandler)))
/*     */         {
/*  85 */           FluidStack fs = ((IFluidHandler)te).drain(ForgeDirection.getOrientation(marker.side), new FluidStack(fluid.getFluid(), max - camt), false);
/*     */           
/*  87 */           if ((fs != null) && (fs.amount > 0)) {
/*  88 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  94 */       ArrayList<ChunkCoordinates> coords = GolemHelper.getMarkedBlocksAdjacentToGolem(this.theWorld, this.theGolem, (byte)-1);
/*  95 */       for (ChunkCoordinates loc : coords) {
/*  96 */         Block bi = this.theWorld.func_147439_a(loc.field_71574_a, loc.field_71572_b, loc.field_71573_c);
/*  97 */         if (FluidRegistry.getFluid(fluid.fluidID).getBlock() == bi)
/*     */         {
/*  99 */           if (((bi instanceof IFluidBlock)) && (((IFluidBlock)bi).canDrain(this.theWorld, loc.field_71574_a, loc.field_71572_b, loc.field_71573_c))) {
/* 100 */             FluidStack fs = ((IFluidBlock)bi).drain(this.theWorld, loc.field_71574_a, loc.field_71572_b, loc.field_71573_c, false);
/* 101 */             return (fs != null) && (fs.amount <= max - camt);
/*     */           }
/*     */           
/* 104 */           if ((fluid.fluidID == FluidRegistry.WATER.getID()) || (fluid.fluidID == FluidRegistry.LAVA.getID())) {
/* 105 */             return this.theWorld.func_72805_g(loc.field_71574_a, loc.field_71572_b, loc.field_71573_c) == 0;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     FluidStack fluid;
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_75253_b()
/*     */   {
/* 116 */     return (this.count < 20) && (this.theGolem.itemWatched != null);
/*     */   }
/*     */   
/*     */   public boolean func_75252_g()
/*     */   {
/* 121 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 127 */     this.count = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 134 */     this.count = 0;
/* 135 */     this.theGolem.itemWatched = null;
/* 136 */     super.func_75251_c();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 141 */   int count = 0;
/*     */   
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/* 146 */     this.count += 1;
/* 147 */     if (this.count < 10) return;
/* 148 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/* 149 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/* 150 */     int cX = home.field_71574_a - facing.offsetX;
/* 151 */     int cY = home.field_71572_b - facing.offsetY;
/* 152 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/* 154 */     int camt = 0;
/* 155 */     if (this.theGolem.fluidCarried != null) {
/* 156 */       camt = this.theGolem.fluidCarried.amount;
/*     */     }
/* 158 */     int max = this.theGolem.getFluidCarryLimit();
/*     */     
/* 160 */     ArrayList<FluidStack> fluids = GolemHelper.getMissingLiquids(this.theGolem);
/* 161 */     if (fluids == null) {
/* 162 */       return;
/*     */     }
/* 164 */     for (Iterator i$ = fluids.iterator(); i$.hasNext();) { fluidstack = (FluidStack)i$.next();
/* 165 */       ArrayList<Marker> markers = GolemHelper.getMarkedFluidHandlersAdjacentToGolem(fluidstack, this.theWorld, this.theGolem);
/* 166 */       for (Marker marker : markers) {
/* 167 */         TileEntity te = this.theWorld.func_147438_o(marker.x, marker.y, marker.z);
/* 168 */         if ((te != null) && ((te instanceof IFluidHandler)))
/*     */         {
/* 170 */           FluidStack fs = ((IFluidHandler)te).drain(ForgeDirection.getOrientation(marker.side), new FluidStack(fluidstack.getFluid(), max - camt), true);
/*     */           
/* 172 */           if ((fs != null) && (fs.amount > 0)) {
/* 173 */             if (this.theGolem.fluidCarried != null) {
/* 174 */               this.theGolem.fluidCarried.amount += fs.amount;
/*     */             } else {
/* 176 */               this.theGolem.fluidCarried = fs.copy();
/*     */             }
/* 178 */             if (fs.amount > 200)
/* 179 */               this.theWorld.func_72956_a(this.theGolem, "game.neutral.swim", 0.2F * (fs.amount / max), 1.0F + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3F);
/* 180 */             this.theGolem.updateCarried();
/* 181 */             if (this.theGolem.fluidCarried.amount >= this.theGolem.getFluidCarryLimit())
/* 182 */               this.theGolem.itemWatched = null;
/* 183 */             this.count = 0;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 189 */       ArrayList<ChunkCoordinates> coords = GolemHelper.getMarkedBlocksAdjacentToGolem(this.theWorld, this.theGolem, (byte)-1);
/* 190 */       for (ChunkCoordinates loc : coords) {
/* 191 */         Block bi = this.theWorld.func_147439_a(loc.field_71574_a, loc.field_71572_b, loc.field_71573_c);
/* 192 */         int md = this.theWorld.func_72805_g(loc.field_71574_a, loc.field_71572_b, loc.field_71573_c);
/*     */         
/* 194 */         int i = loc.field_71574_a;
/* 195 */         int j = loc.field_71572_b;
/* 196 */         int k = loc.field_71573_c;
/*     */         
/* 198 */         if (this.theGolem.getUpgradeAmount(5) > 0)
/*     */         {
/* 200 */           if ((!this.queue.containsKey(loc)) || (((ArrayList)this.queue.get(loc)).size() == 0)) {
/* 201 */             rebuildQueue(loc, fluidstack.getFluid());
/*     */           }
/* 203 */           if ((this.queue.containsKey(loc)) && (((ArrayList)this.queue.get(loc)).size() > 0)) {
/* 204 */             ArrayList<SourceBlock> t = (ArrayList)this.queue.get(loc);
/*     */             do {
/* 206 */               ChunkCoordinates current = ((SourceBlock)t.get(0)).loc;
/* 207 */               i = current.field_71574_a;
/* 208 */               j = current.field_71572_b;
/* 209 */               k = current.field_71573_c;
/* 210 */               t.remove(0);
/* 211 */             } while ((t.size() > 0) && (!validFluidBlock(fluidstack.getFluid(), i, j, k)));
/* 212 */             this.queue.put(loc, t);
/*     */           }
/*     */         }
/* 215 */         if (FluidRegistry.getFluid(fluidstack.fluidID).getBlock() == bi)
/* 216 */           if (((bi instanceof BlockFluidBase)) && (((IFluidBlock)bi).canDrain(this.theWorld, i, j, k)))
/*     */           {
/* 218 */             FluidStack fs = ((IFluidBlock)bi).drain(this.theWorld, i, j, k, false);
/*     */             
/* 220 */             if ((fs != null) && (fs.amount <= max - camt)) {
/* 221 */               ((IFluidBlock)bi).drain(this.theWorld, i, j, k, true);
/*     */               
/* 223 */               if (this.theGolem.fluidCarried != null) {
/* 224 */                 this.theGolem.fluidCarried.amount += fs.amount;
/*     */               } else {
/* 226 */                 this.theGolem.fluidCarried = fs.copy();
/*     */               }
/* 228 */               this.theWorld.func_147468_f(i, j, k);
/* 229 */               this.theWorld.func_72956_a(this.theGolem, "game.neutral.swim", 0.2F, 1.0F + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3F);
/* 230 */               this.theGolem.updateCarried();
/* 231 */               if (this.theGolem.fluidCarried.amount > this.theGolem.getFluidCarryLimit() - 1000)
/* 232 */                 this.theGolem.itemWatched = null;
/* 233 */               this.count = 0;
/*     */             }
/*     */             
/*     */           }
/* 237 */           else if ((fluidstack.fluidID == FluidRegistry.WATER.getID()) || (fluidstack.fluidID == FluidRegistry.LAVA.getID())) {
/* 238 */             int wmd = this.theWorld.func_72805_g(i, j, k);
/* 239 */             if (((FluidRegistry.lookupFluidForBlock(bi) == FluidRegistry.WATER) && (fluidstack.fluidID == FluidRegistry.WATER.getID())) || ((FluidRegistry.lookupFluidForBlock(bi) == FluidRegistry.LAVA) && (fluidstack.fluidID == FluidRegistry.LAVA.getID()) && (wmd == 0)))
/*     */             {
/*     */ 
/*     */ 
/* 243 */               FluidStack fs = new FluidStack(fluidstack.fluidID, 1000);
/* 244 */               if (this.theGolem.fluidCarried != null) {
/* 245 */                 this.theGolem.fluidCarried.amount += fs.amount;
/*     */               } else {
/* 247 */                 this.theGolem.fluidCarried = fs.copy();
/*     */               }
/* 249 */               this.theWorld.func_147468_f(i, j, k);
/* 250 */               this.theWorld.func_72956_a(this.theGolem, "game.neutral.swim", 0.2F, 1.0F + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3F);
/* 251 */               this.theGolem.updateCarried();
/* 252 */               if (this.theGolem.fluidCarried.amount > this.theGolem.getFluidCarryLimit() - 1000)
/* 253 */                 this.theGolem.itemWatched = null;
/* 254 */               this.count = 0;
/*     */             }
/*     */           }
/*     */       }
/*     */     }
/*     */     FluidStack fluidstack;
/*     */   }
/*     */   
/*     */   private boolean validFluidBlock(Fluid fluid, int i, int j, int k) {
/* 263 */     Block bi = this.theWorld.func_147439_a(i, j, k);
/* 264 */     if (FluidRegistry.lookupFluidForBlock(bi) != fluid) return false;
/* 265 */     if (((bi instanceof BlockFluidBase)) && (((IFluidBlock)bi).canDrain(this.theWorld, i, j, k)))
/*     */     {
/* 267 */       FluidStack fs = ((IFluidBlock)bi).drain(this.theWorld, i, j, k, false);
/* 268 */       if (fs != null) return true;
/*     */     }
/* 270 */     if (((FluidRegistry.lookupFluidForBlock(bi) == FluidRegistry.WATER) && (fluid == FluidRegistry.WATER)) || ((FluidRegistry.lookupFluidForBlock(bi) == FluidRegistry.LAVA) && (fluid == FluidRegistry.LAVA) && (this.theWorld.func_72805_g(i, j, k) == 0)))
/*     */     {
/* 272 */       return true;
/*     */     }
/* 274 */     return false;
/*     */   }
/*     */   
/* 277 */   HashMap<ChunkCoordinates, ArrayList<SourceBlock>> queue = new HashMap();
/* 278 */   ArrayList<ChunkCoordinates> cache = new ArrayList();
/* 279 */   ChunkCoordinates origin = null;
/*     */   
/*     */   private void rebuildQueue(ChunkCoordinates loc, Fluid fluid) {
/* 282 */     this.pumpDist = (this.theGolem.getRange() * this.theGolem.getRange());
/* 283 */     this.cache.clear();
/* 284 */     this.origin = loc;
/* 285 */     ArrayList<SourceBlock> sources = new ArrayList();
/* 286 */     getConnectedFluidBlocks(this.theWorld, loc.field_71574_a, loc.field_71572_b, loc.field_71573_c, fluid, sources);
/* 287 */     Collections.sort(sources, Collections.reverseOrder());
/* 288 */     this.queue.put(loc, sources);
/*     */   }
/*     */   
/*     */   private void getConnectedFluidBlocks(World world, int x, int y, int z, Fluid fluid, ArrayList<SourceBlock> sources) {
/*     */     try {
/* 293 */       if (this.cache.contains(new ChunkCoordinates(x, y, z))) return;
/* 294 */       this.cache.add(new ChunkCoordinates(x, y, z));
/* 295 */       for (int a = -1; a <= 1; a++) for (int b = -1; b <= 1; b++) for (int c = -1; c <= 1; c++)
/* 296 */             if ((a != 0) || (b != 0) || (c != 0)) {
/* 297 */               int xx = x + a;
/* 298 */               int yy = y + b;
/* 299 */               int zz = z + c;
/* 300 */               ChunkCoordinates cc = new ChunkCoordinates(xx, yy, zz);
/* 301 */               float dist = cc.func_82371_e(this.origin);
/* 302 */               if (dist <= this.pumpDist)
/*     */               {
/* 304 */                 Block bi = world.func_147439_a(xx, yy, zz);
/* 305 */                 if (bi == Blocks.field_150356_k) bi = Blocks.field_150353_l;
/* 306 */                 if (bi == Blocks.field_150358_i) { bi = Blocks.field_150355_j;
/*     */                 }
/* 308 */                 Fluid fi = FluidRegistry.lookupFluidForBlock(bi);
/*     */                 
/* 310 */                 if ((fi != null) && (fi == fluid))
/*     */                 {
/* 312 */                   if (validFluidBlock(fluid, xx, yy, zz)) {
/* 313 */                     sources.add(new SourceBlock(cc, dist));
/*     */                   }
/*     */                   
/* 316 */                   getConnectedFluidBlocks(world, xx, yy, zz, fluid, sources);
/*     */                 }
/*     */               }
/*     */             }
/*     */     } catch (Exception e) {}
/*     */   }
/*     */   
/*     */   private class SourceBlock implements Comparable { ChunkCoordinates loc;
/*     */     float dist;
/*     */     
/* 326 */     public SourceBlock(ChunkCoordinates loc, float dist) { this.loc = loc;
/* 327 */       this.dist = dist;
/*     */     }
/*     */     
/*     */     public int compareTo(SourceBlock target)
/*     */     {
/* 332 */       return target.dist > this.dist ? -1 : target.dist < this.dist ? 1 : 0;
/*     */     }
/*     */     
/*     */     public int compareTo(Object target)
/*     */     {
/* 337 */       return compareTo((SourceBlock)target);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/fluid/AILiquidGather.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */