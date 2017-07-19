/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.BlockFluidBase;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import net.minecraftforge.fluids.FluidTankInfo;
/*     */ import net.minecraftforge.fluids.IFluidHandler;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.common.items.ItemBathSalts;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ public class TileSpa extends TileThaumcraft implements ISidedInventory, IFluidHandler
/*     */ {
/*  26 */   private ItemStack[] itemStacks = new ItemStack[1];
/*  27 */   private boolean mix = true;
/*     */   private String customName;
/*     */   
/*  30 */   public void toggleMix() { this.mix = (!this.mix);
/*  31 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  32 */     func_70296_d();
/*     */   }
/*     */   
/*     */   public boolean getMix() {
/*  36 */     return this.mix;
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  41 */     this.mix = nbttagcompound.func_74767_n("mix");
/*  42 */     this.tank.setFluid(FluidStack.loadFluidStackFromNBT(nbttagcompound));
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  47 */     nbttagcompound.func_74757_a("mix", this.mix);
/*  48 */     if (this.tank.getFluid() != null) {
/*  49 */       this.tank.getFluid().writeToNBT(nbttagcompound);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/*  54 */     super.func_145839_a(nbttagcompound);
/*  55 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/*  56 */     this.itemStacks = new ItemStack[func_70302_i_()];
/*  57 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/*  59 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  60 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/*  62 */       if ((b0 >= 0) && (b0 < this.itemStacks.length))
/*     */       {
/*  64 */         this.itemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/*  71 */     super.func_145841_b(nbttagcompound);
/*  72 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/*  74 */     for (int i = 0; i < this.itemStacks.length; i++)
/*     */     {
/*  76 */       if (this.itemStacks[i] != null)
/*     */       {
/*  78 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  79 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/*  80 */         this.itemStacks[i].func_77955_b(nbttagcompound1);
/*  81 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/*  85 */     nbttagcompound.func_74782_a("Items", nbttaglist);
/*     */   }
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
/*     */   public int func_70302_i_()
/*     */   {
/*  99 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/* 108 */     return this.itemStacks[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/* 118 */     if (this.itemStacks[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/* 122 */       if (this.itemStacks[par1].field_77994_a <= par2)
/*     */       {
/* 124 */         ItemStack itemstack = this.itemStacks[par1];
/* 125 */         this.itemStacks[par1] = null;
/* 126 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/* 130 */       ItemStack itemstack = this.itemStacks[par1].func_77979_a(par2);
/*     */       
/* 132 */       if (this.itemStacks[par1].field_77994_a == 0)
/*     */       {
/* 134 */         this.itemStacks[par1] = null;
/*     */       }
/*     */       
/* 137 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 142 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/* 153 */     if (this.itemStacks[par1] != null)
/*     */     {
/* 155 */       ItemStack itemstack = this.itemStacks[par1];
/* 156 */       this.itemStacks[par1] = null;
/* 157 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/* 161 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 171 */     this.itemStacks[par1] = par2ItemStack;
/*     */     
/* 173 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 175 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 187 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 196 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70295_k_() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70305_f() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 211 */     return (par2ItemStack != null) && ((par2ItemStack.func_77973_b() instanceof ItemBathSalts));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] func_94128_d(int par1)
/*     */   {
/* 221 */     return par1 != 1 ? new int[] { 0 } : new int[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 231 */     return par3 != 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 241 */     return par3 != 1;
/*     */   }
/*     */   
/*     */   public String func_145825_b()
/*     */   {
/* 246 */     return "thaumcraft.spa";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 251 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 258 */   public boolean canUpdate() { return true; }
/*     */   
/* 260 */   private int counter = 0;
/*     */   
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/* 265 */     if ((!this.field_145850_b.field_72995_K) && (this.counter++ % 40 == 0) && (!this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e)) && (hasIngredients()))
/*     */     {
/*     */ 
/*     */ 
/* 269 */       Block b = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 270 */       int m = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 271 */       Block tb = null;
/* 272 */       if (this.mix) {
/* 273 */         tb = thaumcraft.common.config.ConfigBlocks.blockFluidPure;
/*     */       } else {
/* 275 */         tb = this.tank.getFluid().getFluid().getBlock();
/*     */       }
/* 277 */       if ((b == tb) && (m == 0))
/*     */       {
/* 279 */         for (int xx = -2; xx <= 2; xx++) {
/* 280 */           for (int zz = -2; zz <= 2; zz++) {
/* 281 */             if (isValidLocation(this.field_145851_c + xx, this.field_145848_d + 1, this.field_145849_e + zz, true, tb)) {
/* 282 */               consumeIngredients();
/* 283 */               this.field_145850_b.func_147449_b(this.field_145851_c + xx, this.field_145848_d + 1, this.field_145849_e + zz, tb);
/* 284 */               checkQuanta(this.field_145851_c + xx, this.field_145848_d + 1, this.field_145849_e + zz);
/* 285 */               return;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 290 */       else if (isValidLocation(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, false, tb)) {
/* 291 */         consumeIngredients();
/* 292 */         this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, tb);
/* 293 */         checkQuanta(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkQuanta(int i, int j, int k)
/*     */   {
/* 300 */     Block b = this.field_145850_b.func_147439_a(i, j, k);
/*     */     
/* 302 */     if ((b instanceof BlockFluidBase)) {
/* 303 */       float p = ((BlockFluidBase)b).getQuantaPercentage(this.field_145850_b, i, j, k);
/* 304 */       if (p < 1.0F) {
/* 305 */         int md = (int)(1.0F / p) - 1;
/* 306 */         if ((md >= 0) && (md < 16)) this.field_145850_b.func_72921_c(i, j, k, md, 3);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean hasIngredients()
/*     */   {
/* 313 */     if (this.mix) {
/* 314 */       if ((this.tank.getInfo().fluid == null) || (!this.tank.getInfo().fluid.containsFluid(new FluidStack(FluidRegistry.WATER, 1000))))
/*     */       {
/*     */ 
/* 317 */         return false;
/*     */       }
/* 319 */       if ((this.itemStacks[0] == null) || (!(this.itemStacks[0].func_77973_b() instanceof ItemBathSalts))) {
/* 320 */         return false;
/*     */       }
/* 322 */     } else if ((this.tank.getInfo().fluid == null) || (!this.tank.getFluid().getFluid().canBePlacedInWorld()) || (this.tank.getFluidAmount() < 1000))
/*     */     {
/*     */ 
/* 325 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 329 */     return true;
/*     */   }
/*     */   
/*     */   private void consumeIngredients() {
/* 333 */     if (this.mix) func_70298_a(0, 1);
/* 334 */     drain(ForgeDirection.UNKNOWN, 1000, true);
/*     */   }
/*     */   
/*     */   private boolean isValidLocation(int x, int y, int z, boolean mustBeAdjacent, Block target) {
/* 338 */     if (((target == Blocks.field_150355_j) || (target == Blocks.field_150358_i)) && (this.field_145850_b.field_73011_w.field_76575_d)) return false;
/* 339 */     Block b = this.field_145850_b.func_147439_a(x, y, z);
/* 340 */     Block bb = this.field_145850_b.func_147439_a(x, y - 1, z);
/* 341 */     int m = this.field_145850_b.func_72805_g(x, y, z);
/* 342 */     if ((bb.isSideSolid(this.field_145850_b, x, y - 1, z, ForgeDirection.UP)) && (b.isReplaceable(this.field_145850_b, x, y, z)) && ((b != target) || (m != 0)))
/*     */     {
/* 344 */       if (!mustBeAdjacent) {
/* 345 */         return true;
/*     */       }
/* 347 */       return BlockUtils.isBlockTouching(this.field_145850_b, x, y, z, target, 0);
/*     */     }
/*     */     
/* 350 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 355 */   public FluidTank tank = new FluidTank(5000);
/*     */   
/*     */ 
/*     */   public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
/*     */   {
/* 360 */     int df = this.tank.fill(resource, doFill);
/* 361 */     if ((df > 0) && (doFill)) {
/* 362 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 363 */       func_70296_d();
/*     */     }
/* 365 */     return df;
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
/*     */   {
/* 371 */     if ((resource == null) || (!resource.isFluidEqual(this.tank.getFluid())))
/*     */     {
/* 373 */       return null;
/*     */     }
/* 375 */     return this.tank.drain(resource.amount, doDrain);
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
/*     */   {
/* 381 */     FluidStack fs = this.tank.drain(maxDrain, doDrain);
/* 382 */     if ((fs != null) && (doDrain)) {
/* 383 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 384 */       func_70296_d();
/*     */     }
/* 386 */     return fs;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canFill(ForgeDirection from, Fluid fluid)
/*     */   {
/* 392 */     return from != ForgeDirection.UP;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canDrain(ForgeDirection from, Fluid fluid)
/*     */   {
/* 398 */     return from != ForgeDirection.UP;
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidTankInfo[] getTankInfo(ForgeDirection from)
/*     */   {
/* 404 */     return new FluidTankInfo[] { this.tank.getInfo() };
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileSpa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */