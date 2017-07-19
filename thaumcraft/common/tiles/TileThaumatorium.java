/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.container.InventoryFake;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class TileThaumatorium extends TileThaumcraft implements IAspectContainer, IEssentiaTransport, ISidedInventory
/*     */ {
/*  38 */   public ItemStack inputStack = null;
/*  39 */   public AspectList essentia = new AspectList();
/*     */   
/*  41 */   public ArrayList<Integer> recipeHash = new ArrayList();
/*  42 */   public ArrayList<AspectList> recipeEssentia = new ArrayList();
/*     */   
/*  44 */   public ArrayList<String> recipePlayer = new ArrayList();
/*  45 */   public int currentCraft = -1;
/*  46 */   public int maxRecipes = 1;
/*     */   
/*  48 */   public ForgeDirection facing = ForgeDirection.NORTH;
/*  49 */   public Aspect currentSuction = null;
/*     */   
/*     */ 
/*  52 */   int venting = 0;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  57 */     return AxisAlignedBB.func_72330_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1, this.field_145851_c + 2, this.field_145848_d + 2, this.field_145849_e + 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  65 */     this.facing = ForgeDirection.getOrientation(nbttagcompound.func_74771_c("facing"));
/*  66 */     this.essentia.readFromNBT(nbttagcompound);
/*  67 */     this.maxRecipes = nbttagcompound.func_74771_c("maxrec");
/*     */     
/*  69 */     this.recipeEssentia = new ArrayList();
/*  70 */     this.recipeHash = new ArrayList();
/*  71 */     this.recipePlayer = new ArrayList();
/*  72 */     int[] hashes = nbttagcompound.func_74759_k("recipes");
/*  73 */     if (hashes != null) {
/*  74 */       for (int hash : hashes) {
/*  75 */         CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(hash);
/*  76 */         if (recipe != null) {
/*  77 */           this.recipeEssentia.add(recipe.aspects.copy());
/*  78 */           this.recipePlayer.add("");
/*  79 */           this.recipeHash.add(Integer.valueOf(hash));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  89 */     nbttagcompound.func_74774_a("facing", (byte)this.facing.ordinal());
/*  90 */     nbttagcompound.func_74774_a("maxrec", (byte)this.maxRecipes);
/*  91 */     this.essentia.writeToNBT(nbttagcompound);
/*  92 */     int[] hashes = new int[this.recipeHash.size()];
/*  93 */     int a = 0;
/*  94 */     for (Integer i : this.recipeHash) {
/*  95 */       hashes[a] = i.intValue();
/*  96 */       a++;
/*     */     }
/*  98 */     nbttagcompound.func_74783_a("recipes", hashes);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 104 */     super.func_145839_a(nbtCompound);
/* 105 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
/* 106 */     if (nbttaglist.func_74745_c() > 0) {
/* 107 */       this.inputStack = ItemStack.func_77949_a(nbttaglist.func_150305_b(0));
/*     */     }
/*     */     
/* 110 */     NBTTagList nbttaglist2 = nbtCompound.func_150295_c("OutputPlayer", 8);
/* 111 */     for (int a = 0; a < nbttaglist2.func_74745_c(); a++) {
/* 112 */       if (this.recipePlayer.size() > a) {
/* 113 */         this.recipePlayer.set(a, nbttaglist2.func_150307_f(a));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 120 */     super.func_145841_b(nbtCompound);
/* 121 */     NBTTagList nbttaglist = new NBTTagList();
/* 122 */     if (this.inputStack != null)
/*     */     {
/* 124 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 125 */       nbttagcompound1.func_74774_a("Slot", (byte)0);
/* 126 */       this.inputStack.func_77955_b(nbttagcompound1);
/* 127 */       nbttaglist.func_74742_a(nbttagcompound1);
/*     */     }
/* 129 */     nbtCompound.func_74782_a("Items", nbttaglist);
/*     */     
/* 131 */     NBTTagList nbttaglist2 = new NBTTagList();
/* 132 */     if (this.recipePlayer.size() > 0)
/*     */     {
/* 134 */       for (int a = 0; a < this.recipePlayer.size(); a++) {
/* 135 */         if (this.recipePlayer.get(a) != null)
/*     */         {
/* 137 */           NBTTagString nbttagcompound1 = new NBTTagString((String)this.recipePlayer.get(a));
/* 138 */           nbttaglist2.func_74742_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */     }
/* 142 */     nbtCompound.func_74782_a("OutputPlayer", nbttaglist2);
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/* 147 */     return true;
/*     */   }
/*     */   
/*     */   boolean checkHeat()
/*     */   {
/* 152 */     Material mat = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e).func_149688_o();
/* 153 */     Block bi = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
/* 154 */     int md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
/* 155 */     return (mat == Material.field_151587_i) || (mat == Material.field_151581_o) || ((bi == ConfigBlocks.blockAiry) && (md == 1));
/*     */   }
/*     */   
/* 158 */   int counter = 0;
/* 159 */   boolean heated = false;
/* 160 */   CrucibleRecipe currentRecipe = null;
/*     */   public Container eventHandler;
/*     */   
/* 163 */   public ItemStack getCurrentOutputRecipe() { ItemStack out = null;
/* 164 */     if ((this.currentCraft >= 0) && (this.recipeHash != null) && (this.recipeHash.size() > 0)) {
/* 165 */       CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(((Integer)this.recipeHash.get(this.currentCraft)).intValue());
/* 166 */       if (recipe != null) {
/* 167 */         out = recipe.getRecipeOutput().func_77946_l();
/*     */       }
/*     */     }
/* 170 */     return out;
/*     */   }
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 175 */     if (!this.field_145850_b.field_72995_K) {
/* 176 */       if ((this.counter == 0) || (this.counter % 40 == 0)) {
/* 177 */         this.heated = checkHeat();
/* 178 */         getUpgrades();
/*     */       }
/* 180 */       this.counter += 1;
/* 181 */       if ((this.heated) && (!gettingPower()) && (this.counter % 5 == 0) && (this.recipeHash != null) && (this.recipeHash.size() > 0)) {
/* 182 */         if (this.inputStack == null) {
/* 183 */           this.currentSuction = null;
/* 184 */           return;
/*     */         }
/*     */         
/* 187 */         if ((this.currentCraft < 0) || (this.currentCraft >= this.recipeHash.size()) || (this.currentRecipe == null) || (!this.currentRecipe.catalystMatches(this.inputStack))) {
/* 188 */           for (int a = 0; a < this.recipeHash.size(); a++) {
/* 189 */             CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(((Integer)this.recipeHash.get(a)).intValue());
/* 190 */             if (recipe.catalystMatches(this.inputStack)) {
/* 191 */               this.currentCraft = a;
/* 192 */               this.currentRecipe = recipe;
/* 193 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 198 */         if ((this.currentCraft < 0) || (this.currentCraft >= this.recipeHash.size())) { return;
/*     */         }
/* 200 */         TileEntity inventory = this.field_145850_b.func_147438_o(this.field_145851_c + this.facing.offsetX, this.field_145848_d, this.field_145849_e + this.facing.offsetZ);
/* 201 */         if ((inventory != null) && ((inventory instanceof IInventory))) {
/* 202 */           ItemStack dropped = getCurrentOutputRecipe();
/* 203 */           dropped = InventoryUtils.placeItemStackIntoInventory(dropped, (IInventory)inventory, this.facing.getOpposite().ordinal(), false);
/* 204 */           if (dropped != null) {
/* 205 */             return;
/*     */           }
/*     */         }
/*     */         
/* 209 */         boolean done = true;
/* 210 */         this.currentSuction = null;
/* 211 */         for (Aspect aspect : ((AspectList)this.recipeEssentia.get(this.currentCraft)).getAspectsSorted()) {
/* 212 */           if (this.essentia.getAmount(aspect) < ((AspectList)this.recipeEssentia.get(this.currentCraft)).getAmount(aspect)) {
/* 213 */             this.currentSuction = aspect;
/* 214 */             done = false;
/* 215 */             break;
/*     */           }
/*     */         }
/*     */         
/* 219 */         if (done) {
/* 220 */           completeRecipe();
/*     */ 
/*     */         }
/* 223 */         else if (this.currentSuction != null) fill();
/*     */       }
/*     */     }
/* 226 */     else if (this.venting > 0) {
/* 227 */       this.venting -= 1;
/* 228 */       float fx = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 229 */       float fz = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 230 */       float fy = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 231 */       float fx2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 232 */       float fz2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 233 */       float fy2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 234 */       int color = 16777215;
/* 235 */       Thaumcraft.proxy.drawVentParticles(this.field_145850_b, this.field_145851_c + 0.5F + fx + this.facing.offsetX / 2.0F, this.field_145848_d + 0.5F + fy, this.field_145849_e + 0.5F + fz + this.facing.offsetZ / 2.0F, this.facing.offsetX / 4.0F + fx2, fy2, this.facing.offsetZ / 4.0F + fz2, color);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void completeRecipe()
/*     */   {
/* 245 */     if ((this.currentRecipe != null) && (this.currentCraft < this.recipeHash.size()) && 
/* 246 */       (this.currentRecipe.matches(this.essentia, this.inputStack)) && 
/* 247 */       (func_70298_a(0, 1) != null)) {
/* 248 */       this.essentia = new AspectList();
/* 249 */       ItemStack dropped = getCurrentOutputRecipe();
/*     */       
/* 251 */       EntityPlayer p = this.field_145850_b.func_72924_a((String)this.recipePlayer.get(this.currentCraft));
/* 252 */       if (p != null) {
/* 253 */         FMLCommonHandler.instance().firePlayerCraftingEvent(p, dropped, new InventoryFake(new ItemStack[] { this.inputStack }));
/*     */       }
/*     */       
/* 256 */       TileEntity inventory = this.field_145850_b.func_147438_o(this.field_145851_c + this.facing.offsetX, this.field_145848_d, this.field_145849_e + this.facing.offsetZ);
/* 257 */       if ((inventory != null) && ((inventory instanceof IInventory))) {
/* 258 */         dropped = InventoryUtils.placeItemStackIntoInventory(dropped, (IInventory)inventory, this.facing.getOpposite().ordinal(), true);
/*     */       }
/*     */       
/* 261 */       if (dropped != null) {
/* 262 */         EntityItem ei = new EntityItem(this.field_145850_b, this.field_145851_c + 0.5D + this.facing.offsetX * 0.66D, this.field_145848_d + 0.33D + this.facing.getOpposite().offsetY, this.field_145849_e + 0.5D + this.facing.offsetZ * 0.66D, dropped.func_77946_l());
/*     */         
/*     */ 
/*     */ 
/* 266 */         ei.field_70159_w = (0.075F * this.facing.offsetX);
/* 267 */         ei.field_70181_x = 0.02500000037252903D;
/* 268 */         ei.field_70179_y = (0.075F * this.facing.offsetZ);
/* 269 */         this.field_145850_b.func_72838_d(ei);
/* 270 */         this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q(), 0, 0);
/*     */       }
/* 272 */       this.field_145850_b.func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "random.fizz", 0.25F, 2.6F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F);
/*     */       
/* 274 */       this.currentCraft = -1;
/* 275 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 276 */       func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   void fill()
/*     */   {
/* 283 */     TileEntity te = null;
/* 284 */     IEssentiaTransport ic = null;
/* 285 */     for (int y = 0; y <= 1; y++) {
/* 286 */       for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
/*     */       {
/* 288 */         if ((dir != this.facing) && (dir != ForgeDirection.DOWN) && ((y != 0) || (dir != ForgeDirection.UP)))
/*     */         {
/* 290 */           te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d + y, this.field_145849_e, dir);
/* 291 */           if (te != null) {
/* 292 */             ic = (IEssentiaTransport)te;
/* 293 */             if ((ic.getEssentiaAmount(dir.getOpposite()) > 0) && (ic.getSuctionAmount(dir.getOpposite()) < getSuctionAmount(null)) && (getSuctionAmount(null) >= ic.getMinimumSuction()))
/*     */             {
/*     */ 
/* 296 */               int ess = ic.takeEssentia(this.currentSuction, 1, dir.getOpposite());
/* 297 */               if (ess > 0) {
/* 298 */                 addToContainer(this.currentSuction, ess);
/* 299 */                 return;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/* 312 */     int ce = this.currentRecipe.aspects.getAmount(tt) - this.essentia.getAmount(tt);
/* 313 */     if ((this.currentRecipe == null) || (ce <= 0)) return am;
/* 314 */     int add = Math.min(ce, am);
/* 315 */     this.essentia.add(tt, add);
/* 316 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 317 */     func_70296_d();
/* 318 */     return am - add;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/* 323 */     if (this.essentia.getAmount(tt) >= am) {
/* 324 */       this.essentia.remove(tt, am);
/* 325 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 326 */       func_70296_d();
/* 327 */       return true;
/*     */     }
/* 329 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 335 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 340 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tt, int am)
/*     */   {
/* 345 */     return this.essentia.getAmount(tt) >= am;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tt)
/*     */   {
/* 350 */     return this.essentia.getAmount(tt);
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 355 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 362 */     if (i >= 0)
/*     */     {
/* 364 */       if (this.field_145850_b.field_72995_K) {
/* 365 */         this.venting = 7;
/*     */       }
/* 367 */       return true;
/*     */     }
/*     */     
/* 370 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 378 */     return face != this.facing;
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 383 */     return face != this.facing;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 388 */     return false;
/*     */   }
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount)
/*     */   {
/* 393 */     this.currentSuction = aspect;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection loc)
/*     */   {
/* 398 */     return this.currentSuction;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection loc)
/*     */   {
/* 403 */     return this.currentSuction != null ? 128 : 0;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 408 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 413 */     return 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 418 */     return (canOutputTo(face)) && (takeFromContainer(aspect, amount)) ? amount : 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 423 */     return canInputFrom(face) ? amount - addToContainer(aspect, amount) : 0;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 428 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 433 */     return false;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 438 */     return this.essentia;
/*     */   }
/*     */   
/*     */   public void setAspects(AspectList aspects)
/*     */   {
/* 443 */     this.essentia = aspects;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/* 454 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/* 460 */     return this.inputStack;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/* 466 */     if (this.inputStack != null)
/*     */     {
/*     */ 
/*     */ 
/* 470 */       if (this.inputStack.field_77994_a <= par2)
/*     */       {
/* 472 */         ItemStack itemstack = this.inputStack;
/* 473 */         this.inputStack = null;
/* 474 */         if (this.eventHandler != null) this.eventHandler.func_75130_a(this);
/* 475 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/* 479 */       ItemStack itemstack = this.inputStack.func_77979_a(par2);
/*     */       
/* 481 */       if (this.inputStack.field_77994_a == 0)
/*     */       {
/* 483 */         this.inputStack = null;
/*     */       }
/* 485 */       if (this.eventHandler != null) this.eventHandler.func_75130_a(this);
/* 486 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 491 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/* 498 */     if (this.inputStack != null)
/*     */     {
/* 500 */       ItemStack itemstack = this.inputStack;
/* 501 */       this.inputStack = null;
/* 502 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/* 506 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 515 */     this.inputStack = par2ItemStack;
/*     */     
/* 517 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 519 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/*     */     
/* 522 */     if (this.eventHandler != null) { this.eventHandler.func_75130_a(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public String func_145825_b()
/*     */   {
/* 528 */     return "container.alchemyfurnace";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 534 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 540 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 546 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
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
/* 561 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] func_94128_d(int par1)
/*     */   {
/* 571 */     return new int[] { 0 };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 581 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 591 */     return true;
/*     */   }
/*     */   
/*     */   public boolean gettingPower()
/*     */   {
/* 596 */     return (this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e)) || (this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)) || (this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void getUpgrades()
/*     */   {
/* 603 */     int mr = 1;
/* 604 */     for (int yy = 0; yy <= 1; yy++) {
/* 605 */       for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/* 606 */         if ((dir != ForgeDirection.DOWN) && (dir != this.facing)) {
/* 607 */           int xx = this.field_145851_c + dir.offsetX;
/* 608 */           int zz = this.field_145849_e + dir.offsetZ;
/* 609 */           Block bi = this.field_145850_b.func_147439_a(xx, this.field_145848_d + yy + dir.offsetY, zz);
/* 610 */           int md = this.field_145850_b.func_72805_g(xx, this.field_145848_d + yy + dir.offsetY, zz);
/* 611 */           if ((bi == ConfigBlocks.blockMetalDevice) && (md == 12)) {
/* 612 */             TileEntity te = this.field_145850_b.func_147438_o(xx, this.field_145848_d + yy + dir.offsetY, zz);
/* 613 */             if ((te != null) && ((te instanceof TileBrainbox)) && (((TileBrainbox)te).facing == dir.getOpposite()))
/* 614 */               mr += 2;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 619 */     if (mr != this.maxRecipes) {
/* 620 */       this.maxRecipes = mr;
/* 621 */       while (this.recipeHash.size() > this.maxRecipes) {
/* 622 */         this.recipeHash.remove(this.recipeHash.size() - 1);
/*     */       }
/* 624 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 625 */       func_70296_d();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileThaumatorium.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */