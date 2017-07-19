/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.visnet.VisNetHandler;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ public class TileArcaneFurnace extends TileThaumcraft
/*     */ {
/*     */   private ItemStack[] furnaceItemStacks;
/*     */   public int furnaceCookTime;
/*     */   public int furnaceMaxCookTime;
/*     */   public int speedyTime;
/*     */   
/*     */   public TileArcaneFurnace()
/*     */   {
/*  28 */     this.furnaceItemStacks = new ItemStack[32];
/*  29 */     this.furnaceCookTime = 0;
/*  30 */     this.furnaceMaxCookTime = 0;
/*  31 */     this.speedyTime = 0;
/*     */   }
/*     */   
/*     */   public int getSizeInventory()
/*     */   {
/*  36 */     return this.furnaceItemStacks.length;
/*     */   }
/*     */   
/*     */   public ItemStack getStackInSlot(int i)
/*     */   {
/*  41 */     return this.furnaceItemStacks[i];
/*     */   }
/*     */   
/*     */   public ItemStack decrStackSize(int i, int j)
/*     */   {
/*  46 */     if (this.furnaceItemStacks[i] != null)
/*     */     {
/*  48 */       if (this.furnaceItemStacks[i].field_77994_a <= j)
/*     */       {
/*  50 */         ItemStack itemstack = this.furnaceItemStacks[i];
/*  51 */         this.furnaceItemStacks[i] = null;
/*  52 */         func_70296_d();
/*  53 */         return itemstack;
/*     */       }
/*  55 */       ItemStack itemstack1 = this.furnaceItemStacks[i].func_77979_a(j);
/*  56 */       if (this.furnaceItemStacks[i].field_77994_a == 0)
/*     */       {
/*  58 */         this.furnaceItemStacks[i] = null;
/*     */       }
/*  60 */       func_70296_d();
/*  61 */       return itemstack1;
/*     */     }
/*     */     
/*     */ 
/*  65 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setInventorySlotContents(int i, ItemStack itemstack)
/*     */   {
/*  71 */     this.furnaceItemStacks[i] = itemstack;
/*  72 */     if ((itemstack != null) && (itemstack.field_77994_a > getInventoryStackLimit()))
/*     */     {
/*  74 */       itemstack.field_77994_a = getInventoryStackLimit();
/*     */     }
/*  76 */     func_70296_d();
/*     */   }
/*     */   
/*     */   private int getInventoryStackLimit() {
/*  80 */     return 64;
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/*  85 */     super.func_145839_a(nbttagcompound);
/*  86 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/*  87 */     this.furnaceItemStacks = new ItemStack[getSizeInventory()];
/*  88 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/*  90 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  91 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/*  92 */       if ((byte0 >= 0) && (byte0 < this.furnaceItemStacks.length))
/*     */       {
/*  94 */         this.furnaceItemStacks[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/*  98 */     this.furnaceCookTime = nbttagcompound.func_74765_d("CookTime");
/*  99 */     this.speedyTime = nbttagcompound.func_74765_d("SpeedyTime");
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/* 104 */     super.func_145841_b(nbttagcompound);
/* 105 */     nbttagcompound.func_74777_a("CookTime", (short)this.furnaceCookTime);
/* 106 */     nbttagcompound.func_74777_a("SpeedyTime", (short)this.speedyTime);
/*     */     
/* 108 */     NBTTagList nbttaglist = new NBTTagList();
/* 109 */     for (int i = 0; i < this.furnaceItemStacks.length; i++)
/*     */     {
/* 111 */       if (this.furnaceItemStacks[i] != null)
/*     */       {
/* 113 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 114 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 115 */         this.furnaceItemStacks[i].func_77955_b(nbttagcompound1);
/* 116 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/* 120 */     nbttagcompound.func_74782_a("Items", nbttaglist);
/*     */   }
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 125 */     super.func_145845_h();
/* 126 */     if (this.facingX == -5) {
/* 127 */       getFacing();
/*     */     }
/*     */     
/* 130 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/* 132 */       boolean cookedflag = false;
/*     */       
/* 134 */       if (this.furnaceCookTime > 0) {
/* 135 */         this.furnaceCookTime -= 1;
/* 136 */         cookedflag = true;
/*     */       }
/*     */       
/* 139 */       if ((cookedflag) && (this.speedyTime > 0)) {
/* 140 */         this.speedyTime -= 1;
/*     */       }
/*     */       
/* 143 */       if (this.speedyTime <= 0) {
/* 144 */         this.speedyTime = VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, thaumcraft.api.aspects.Aspect.FIRE, 5);
/*     */       }
/*     */       
/* 147 */       if (this.furnaceMaxCookTime == 0) {
/* 148 */         this.furnaceMaxCookTime = calcCookTime();
/*     */       }
/*     */       
/* 151 */       if (this.furnaceCookTime > this.furnaceMaxCookTime) {
/* 152 */         this.furnaceCookTime = this.furnaceMaxCookTime;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 158 */       if ((this.furnaceCookTime == 0) && (cookedflag)) {
/* 159 */         for (int a = 0; a < getSizeInventory(); a++) {
/* 160 */           if (this.furnaceItemStacks[a] != null) {
/* 161 */             ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(this.furnaceItemStacks[a]);
/* 162 */             if (itemstack != null)
/*     */             {
/* 164 */               ejectItem(itemstack.func_77946_l(), this.furnaceItemStacks[a]);
/* 165 */               this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockArcaneFurnace, 3, 0);
/*     */               
/* 167 */               this.furnaceItemStacks[a].field_77994_a -= 1;
/* 168 */               if (this.furnaceItemStacks[a].field_77994_a > 0) break;
/* 169 */               this.furnaceItemStacks[a] = null; break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 177 */       if ((this.furnaceCookTime == 0) && (!cookedflag)) {
/* 178 */         for (int a = 0; a < getSizeInventory(); a++) {
/* 179 */           if ((this.furnaceItemStacks[a] != null) && (canSmelt(a))) {
/* 180 */             this.furnaceMaxCookTime = calcCookTime();
/* 181 */             this.furnaceCookTime = this.furnaceMaxCookTime;
/*     */             
/* 183 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private int getBellows()
/*     */   {
/* 192 */     int bellows = 0;
/* 193 */     for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/* 194 */       if (dir != ForgeDirection.UP) {
/* 195 */         int xx = this.field_145851_c + dir.offsetX * 2;
/* 196 */         int yy = this.field_145848_d + dir.offsetY * 2;
/* 197 */         int zz = this.field_145849_e + dir.offsetZ * 2;
/* 198 */         net.minecraft.tileentity.TileEntity tile = this.field_145850_b.func_147438_o(xx, yy, zz);
/* 199 */         if ((tile != null) && ((tile instanceof TileBellows)) && (((TileBellows)tile).orientation == dir.getOpposite().ordinal()) && (!this.field_145850_b.func_72864_z(xx, yy, zz)))
/*     */         {
/*     */ 
/* 202 */           bellows++; }
/*     */       }
/*     */     }
/* 205 */     return Math.min(3, bellows);
/*     */   }
/*     */   
/*     */   private int calcCookTime() {
/* 209 */     return (this.speedyTime > 0 ? 80 : 140) - 20 * getBellows();
/*     */   }
/*     */   
/*     */   public boolean addItemsToInventory(ItemStack items)
/*     */   {
/* 214 */     for (int a = 0; a < getSizeInventory(); a++) {
/* 215 */       if ((this.furnaceItemStacks[a] != null) && (this.furnaceItemStacks[a].func_77969_a(items)) && (this.furnaceItemStacks[a].field_77994_a + items.field_77994_a <= items.func_77976_d()))
/*     */       {
/*     */ 
/* 218 */         this.furnaceItemStacks[a].field_77994_a += items.field_77994_a;
/* 219 */         if (!canSmelt(a)) {
/* 220 */           destroyItem(a);
/*     */         }
/* 222 */         func_70296_d();
/* 223 */         return true;
/*     */       }
/* 225 */       if (this.furnaceItemStacks[a] == null) {
/* 226 */         setInventorySlotContents(a, items);
/* 227 */         if (!canSmelt(a)) {
/* 228 */           destroyItem(a);
/*     */         }
/* 230 */         func_70296_d();
/* 231 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 235 */     return false;
/*     */   }
/*     */   
/*     */   private void destroyItem(int slot) {
/* 239 */     this.furnaceItemStacks[slot] = null;
/*     */     
/* 241 */     this.field_145850_b.func_72980_b(this.field_145851_c + 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.5F, "random.fizz", 0.3F, 2.6F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F, false);
/* 242 */     double var21 = this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat();
/* 243 */     double var22 = this.field_145848_d + 1;
/* 244 */     double var23 = this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat();
/* 245 */     this.field_145850_b.func_72869_a("lava", var21, var22, var23, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/* 248 */   public int facingX = -5;
/* 249 */   public int facingZ = -5;
/*     */   
/*     */   private void getFacing() {
/* 252 */     this.facingX = 0;
/* 253 */     this.facingZ = 0;
/* 254 */     if ((this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == ConfigBlocks.blockArcaneFurnace) && (this.field_145850_b.func_72805_g(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == 10))
/*     */     {
/*     */ 
/* 257 */       this.facingX = -1;
/*     */     }
/* 259 */     else if ((this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == ConfigBlocks.blockArcaneFurnace) && (this.field_145850_b.func_72805_g(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == 10))
/*     */     {
/*     */ 
/* 262 */       this.facingX = 1;
/*     */     }
/* 264 */     else if ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == ConfigBlocks.blockArcaneFurnace) && (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == 10))
/*     */     {
/*     */ 
/* 267 */       this.facingZ = -1;
/*     */     }
/*     */     else {
/* 270 */       this.facingZ = 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public void ejectItem(ItemStack items, ItemStack furnaceItemStack)
/*     */   {
/* 276 */     if (items == null) return;
/* 277 */     ItemStack bit = items.func_77946_l();
/* 278 */     int bellows = getBellows();
/*     */     
/* 280 */     float lx = 0.5F;lx += this.facingX * 1.2F;
/* 281 */     float lz = 0.5F;lz += this.facingZ * 1.2F;
/* 282 */     float mx = this.facingX == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03F : this.facingX * 0.13F;
/* 283 */     float mz = this.facingZ == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03F : this.facingZ * 0.13F;
/*     */     
/* 285 */     EntityItem entityitem = new EntityItem(this.field_145850_b, this.field_145851_c + lx, this.field_145848_d + 0.4F, this.field_145849_e + lz, items);
/*     */     
/*     */ 
/*     */ 
/* 289 */     entityitem.field_70159_w = mx;
/* 290 */     entityitem.field_70179_y = mz;
/* 291 */     entityitem.field_70181_x = 0.0D;
/* 292 */     this.field_145850_b.func_72838_d(entityitem);
/*     */     
/* 294 */     if (ThaumcraftApi.getSmeltingBonus(furnaceItemStack) != null) {
/* 295 */       ItemStack bonus = ThaumcraftApi.getSmeltingBonus(furnaceItemStack).func_77946_l();
/* 296 */       if (bonus != null) {
/* 297 */         if (bellows == 0) {
/* 298 */           if (this.field_145850_b.field_73012_v.nextInt(4) == 0) bonus.field_77994_a += 1;
/*     */         } else {
/* 300 */           for (int a = 0; a < bellows; a++) if (this.field_145850_b.field_73012_v.nextFloat() < 0.44F) { bonus.field_77994_a += 1;
/*     */             }
/*     */         }
/*     */       }
/* 304 */       if ((bonus != null) && (bonus.field_77994_a > 0)) {
/* 305 */         mx = this.facingX == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03F : this.facingX * 0.13F;
/* 306 */         mz = this.facingZ == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03F : this.facingZ * 0.13F;
/* 307 */         EntityItem entityitem2 = new EntityItem(this.field_145850_b, this.field_145851_c + lx, this.field_145848_d + 0.4F, this.field_145849_e + lz, bonus);
/*     */         
/*     */ 
/*     */ 
/* 311 */         entityitem2.field_70159_w = mx;
/* 312 */         entityitem2.field_70179_y = mz;
/* 313 */         entityitem2.field_70181_x = 0.0D;
/* 314 */         this.field_145850_b.func_72838_d(entityitem2);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 319 */     int var2 = items.field_77994_a;
/* 320 */     float var3 = FurnaceRecipes.func_77602_a().func_151398_b(bit);
/*     */     
/*     */ 
/* 323 */     if (var3 == 0.0F)
/*     */     {
/* 325 */       var2 = 0;
/*     */     }
/* 327 */     else if (var3 < 1.0F)
/*     */     {
/* 329 */       int var4 = MathHelper.func_76141_d(var2 * var3);
/*     */       
/* 331 */       if ((var4 < MathHelper.func_76123_f(var2 * var3)) && ((float)Math.random() < var2 * var3 - var4))
/*     */       {
/* 333 */         var4++;
/*     */       }
/*     */       
/* 336 */       var2 = var4;
/*     */     }
/*     */     
/* 339 */     while (var2 > 0)
/*     */     {
/* 341 */       int var4 = EntityXPOrb.func_70527_a(var2);
/* 342 */       var2 -= var4;
/* 343 */       EntityXPOrb xp = new EntityXPOrb(this.field_145850_b, this.field_145851_c + lx, this.field_145848_d + 0.4F, this.field_145849_e + lz, var4);
/*     */       
/*     */ 
/*     */ 
/* 347 */       mx = this.facingX == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.025F : this.facingX * 0.13F;
/* 348 */       mz = this.facingZ == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.025F : this.facingZ * 0.13F;
/* 349 */       xp.field_70159_w = mx;
/* 350 */       xp.field_70179_y = mz;
/* 351 */       xp.field_70181_x = 0.0D;
/* 352 */       this.field_145850_b.func_72838_d(xp);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean canSmelt(int slotIn)
/*     */   {
/* 362 */     if (this.furnaceItemStacks[slotIn] == null)
/*     */     {
/* 364 */       return false;
/*     */     }
/*     */     
/* 367 */     ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(this.furnaceItemStacks[slotIn]);
/* 368 */     if (itemstack == null)
/*     */     {
/* 370 */       return false;
/*     */     }
/*     */     
/* 373 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 380 */     if (i == 3)
/*     */     {
/* 382 */       if (this.field_145850_b.field_72995_K) {
/* 383 */         for (int a = 0; a < 5; a++) {
/* 384 */           Thaumcraft.proxy.furnaceLavaFx(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.facingX, this.facingZ);
/* 385 */           this.field_145850_b.func_72980_b(this.field_145851_c + 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.5F, "liquid.lavapop", 0.1F + this.field_145850_b.field_73012_v.nextFloat() * 0.1F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.15F, false);
/*     */         }
/*     */       }
/*     */       
/* 389 */       return true;
/*     */     }
/*     */     
/* 392 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileArcaneFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */