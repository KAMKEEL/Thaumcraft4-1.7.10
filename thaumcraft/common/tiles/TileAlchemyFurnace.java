/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityFurnace;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ 
/*     */ public class TileAlchemyFurnace extends TileThaumcraft implements net.minecraft.inventory.ISidedInventory
/*     */ {
/*  25 */   private static final int[] slots_bottom = { 1 };
/*  26 */   private static final int[] slots_top = new int[0];
/*  27 */   private static final int[] slots_sides = { 0 };
/*     */   
/*  29 */   public AspectList aspects = new AspectList();
/*     */   public int vis;
/*  31 */   private int maxVis = 50;
/*  32 */   public int smeltTime = 100;
/*  33 */   int bellows = -1;
/*  34 */   boolean speedBoost = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  39 */   private ItemStack[] furnaceItemStacks = new ItemStack[2];
/*     */   
/*     */ 
/*     */ 
/*     */   public int furnaceBurnTime;
/*     */   
/*     */ 
/*     */ 
/*     */   public int currentItemBurnTime;
/*     */   
/*     */ 
/*     */   public int furnaceCookTime;
/*     */   
/*     */ 
/*     */   private String customName;
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  59 */     return this.furnaceItemStacks.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  68 */     return this.furnaceItemStacks[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  78 */     if (this.furnaceItemStacks[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/*  82 */       if (this.furnaceItemStacks[par1].field_77994_a <= par2)
/*     */       {
/*  84 */         ItemStack itemstack = this.furnaceItemStacks[par1];
/*  85 */         this.furnaceItemStacks[par1] = null;
/*  86 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/*  90 */       ItemStack itemstack = this.furnaceItemStacks[par1].func_77979_a(par2);
/*     */       
/*  92 */       if (this.furnaceItemStacks[par1].field_77994_a == 0)
/*     */       {
/*  94 */         this.furnaceItemStacks[par1] = null;
/*     */       }
/*     */       
/*  97 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 102 */     return null;
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
/* 113 */     if (this.furnaceItemStacks[par1] != null)
/*     */     {
/* 115 */       ItemStack itemstack = this.furnaceItemStacks[par1];
/* 116 */       this.furnaceItemStacks[par1] = null;
/* 117 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/* 121 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 131 */     this.furnaceItemStacks[par1] = par2ItemStack;
/*     */     
/* 133 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 135 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String func_145825_b()
/*     */   {
/* 145 */     return func_145818_k_() ? this.customName : "container.alchemyfurnace";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 155 */     return (this.customName != null) && (this.customName.length() > 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGuiDisplayName(String par1Str)
/*     */   {
/* 163 */     this.customName = par1Str;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 170 */     this.furnaceBurnTime = nbttagcompound.func_74765_d("BurnTime");
/* 171 */     this.vis = nbttagcompound.func_74765_d("Vis");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 176 */     nbttagcompound.func_74777_a("BurnTime", (short)this.furnaceBurnTime);
/* 177 */     nbttagcompound.func_74777_a("Vis", (short)this.vis);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 186 */     super.func_145839_a(nbtCompound);
/* 187 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
/* 188 */     this.furnaceItemStacks = new ItemStack[func_70302_i_()];
/*     */     
/* 190 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 192 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 193 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/* 195 */       if ((b0 >= 0) && (b0 < this.furnaceItemStacks.length))
/*     */       {
/* 197 */         this.furnaceItemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/* 201 */     this.speedBoost = nbtCompound.func_74767_n("speedBoost");
/* 202 */     this.furnaceCookTime = nbtCompound.func_74765_d("CookTime");
/* 203 */     this.currentItemBurnTime = TileEntityFurnace.func_145952_a(this.furnaceItemStacks[1]);
/*     */     
/* 205 */     if (nbtCompound.func_74764_b("CustomName"))
/*     */     {
/* 207 */       this.customName = nbtCompound.func_74779_i("CustomName");
/*     */     }
/*     */     
/* 210 */     this.aspects.readFromNBT(nbtCompound);
/* 211 */     this.vis = this.aspects.visSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 220 */     super.func_145841_b(nbtCompound);
/* 221 */     nbtCompound.func_74757_a("speedBoost", this.speedBoost);
/* 222 */     nbtCompound.func_74777_a("CookTime", (short)this.furnaceCookTime);
/* 223 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 225 */     for (int i = 0; i < this.furnaceItemStacks.length; i++)
/*     */     {
/* 227 */       if (this.furnaceItemStacks[i] != null)
/*     */       {
/* 229 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 230 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 231 */         this.furnaceItemStacks[i].func_77955_b(nbttagcompound1);
/* 232 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/* 236 */     nbtCompound.func_74782_a("Items", nbttaglist);
/*     */     
/* 238 */     if (func_145818_k_())
/*     */     {
/* 240 */       nbtCompound.func_74778_a("CustomName", this.customName);
/*     */     }
/*     */     
/* 243 */     this.aspects.writeToNBT(nbtCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 253 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getCookProgressScaled(int par1)
/*     */   {
/* 264 */     if (this.smeltTime <= 0) this.smeltTime = 1;
/* 265 */     return this.furnaceCookTime * par1 / this.smeltTime;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getContentsScaled(int par1)
/*     */   {
/* 271 */     return this.vis * par1 / this.maxVis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getBurnTimeRemainingScaled(int par1)
/*     */   {
/* 282 */     if (this.currentItemBurnTime == 0)
/*     */     {
/* 284 */       this.currentItemBurnTime = 200;
/*     */     }
/*     */     
/* 287 */     return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBurning()
/*     */   {
/* 295 */     return this.furnaceBurnTime > 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*     */   {
/* 302 */     super.onDataPacket(net, pkt);
/* 303 */     if (this.field_145850_b != null) {
/* 304 */       this.field_145850_b.func_147463_c(EnumSkyBlock.Block, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/* 310 */     return true;
/*     */   }
/*     */   
/* 313 */   int count = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/* 322 */     boolean flag = this.furnaceBurnTime > 0;
/* 323 */     boolean flag1 = false;
/* 324 */     this.count += 1;
/* 325 */     if (this.furnaceBurnTime > 0)
/*     */     {
/* 327 */       this.furnaceBurnTime -= 1;
/*     */     }
/*     */     
/* 330 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/* 332 */       if (this.bellows < 0) { getBellows();
/*     */       }
/* 334 */       if ((this.count % (this.speedBoost ? 20 : 40) == 0) && (this.aspects.size() > 0)) {
/* 335 */         AspectList exlude = new AspectList();
/* 336 */         int deep = 0;
/* 337 */         TileEntity tile = null;
/* 338 */         while (deep < 5) {
/* 339 */           deep++;
/* 340 */           tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + deep, this.field_145849_e);
/* 341 */           if (!(tile instanceof TileAlembic)) break;
/* 342 */           TileAlembic alembic = (TileAlembic)tile;
/* 343 */           if ((alembic.aspect != null) && (alembic.amount < alembic.maxAmount) && (this.aspects.getAmount(alembic.aspect) > 0)) {
/* 344 */             takeFromContainer(alembic.aspect, 1);
/* 345 */             alembic.addToContainer(alembic.aspect, 1);
/* 346 */             exlude.merge(alembic.aspect, 1);
/* 347 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 348 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d + deep, this.field_145849_e);
/*     */           }
/* 350 */           tile = null;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 356 */         deep = 0;
/* 357 */         while (deep < 5) {
/* 358 */           deep++;
/* 359 */           tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + deep, this.field_145849_e);
/* 360 */           if (!(tile instanceof TileAlembic)) break;
/* 361 */           TileAlembic alembic = (TileAlembic)tile;
/* 362 */           if ((alembic.aspect == null) || (alembic.amount == 0)) {
/* 363 */             Aspect as = null;
/* 364 */             if (alembic.aspectFilter == null) {
/* 365 */               as = takeRandomAspect(exlude);
/*     */             }
/* 367 */             else if (takeFromContainer(alembic.aspectFilter, 1)) {
/* 368 */               as = alembic.aspectFilter;
/*     */             }
/*     */             
/* 371 */             if (as != null) {
/* 372 */               alembic.addToContainer(as, 1);
/* 373 */               this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 374 */               this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d + deep, this.field_145849_e);
/* 375 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 385 */       if ((this.furnaceBurnTime == 0) && (canSmelt()))
/*     */       {
/* 387 */         this.currentItemBurnTime = (this.furnaceBurnTime = TileEntityFurnace.func_145952_a(this.furnaceItemStacks[1]));
/*     */         
/* 389 */         if (this.furnaceBurnTime > 0)
/*     */         {
/* 391 */           flag1 = true;
/* 392 */           this.speedBoost = false;
/*     */           
/* 394 */           if (this.furnaceItemStacks[1] != null)
/*     */           {
/* 396 */             if (this.furnaceItemStacks[1].func_77969_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 1, 0))) {
/* 397 */               this.speedBoost = true;
/*     */             }
/* 399 */             this.furnaceItemStacks[1].field_77994_a -= 1;
/*     */             
/* 401 */             if (this.furnaceItemStacks[1].field_77994_a == 0)
/*     */             {
/* 403 */               this.furnaceItemStacks[1] = this.furnaceItemStacks[1].func_77973_b().getContainerItem(this.furnaceItemStacks[1]);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 409 */       if ((isBurning()) && (canSmelt()))
/*     */       {
/* 411 */         this.furnaceCookTime += 1;
/*     */         
/* 413 */         if (this.furnaceCookTime >= this.smeltTime)
/*     */         {
/* 415 */           this.furnaceCookTime = 0;
/* 416 */           smeltItem();
/* 417 */           flag1 = true;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 422 */         this.furnaceCookTime = 0;
/*     */       }
/*     */       
/* 425 */       if (flag != this.furnaceBurnTime > 0)
/*     */       {
/* 427 */         flag1 = true;
/* 428 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/* 432 */     if (flag1)
/*     */     {
/* 434 */       func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean canSmelt()
/*     */   {
/* 443 */     if (this.furnaceItemStacks[0] == null)
/*     */     {
/* 445 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 449 */     AspectList al = ThaumcraftCraftingManager.getObjectTags(this.furnaceItemStacks[0]);
/* 450 */     al = ThaumcraftCraftingManager.getBonusTags(this.furnaceItemStacks[0], al);
/*     */     
/* 452 */     if ((al == null) || (al.size() == 0)) return false;
/* 453 */     int vs = al.visSize();
/* 454 */     if (vs > this.maxVis - this.vis) return false;
/* 455 */     this.smeltTime = ((int)(vs * 10 * (1.0F - 0.125F * this.bellows)));
/* 456 */     return true;
/*     */   }
/*     */   
/*     */   public void getBellows()
/*     */   {
/* 461 */     this.bellows = TileBellows.getBellows(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, ForgeDirection.VALID_DIRECTIONS);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void smeltItem()
/*     */   {
/* 469 */     if (canSmelt())
/*     */     {
/*     */ 
/* 472 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(this.furnaceItemStacks[0]);
/* 473 */       al = ThaumcraftCraftingManager.getBonusTags(this.furnaceItemStacks[0], al);
/*     */       
/* 475 */       for (Aspect a : al.getAspects()) {
/* 476 */         this.aspects.add(a, al.getAmount(a));
/*     */       }
/*     */       
/* 479 */       this.vis = this.aspects.visSize();
/*     */       
/* 481 */       this.furnaceItemStacks[0].field_77994_a -= 1;
/*     */       
/* 483 */       if (this.furnaceItemStacks[0].field_77994_a <= 0)
/*     */       {
/* 485 */         this.furnaceItemStacks[0] = null;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isItemFuel(ItemStack par0ItemStack)
/*     */   {
/* 496 */     return TileEntityFurnace.func_145952_a(par0ItemStack) > 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 505 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
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
/* 520 */     if (par1 == 0) {
/* 521 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(par2ItemStack);
/* 522 */       al = ThaumcraftCraftingManager.getBonusTags(par2ItemStack, al);
/* 523 */       if ((al != null) && (al.size() > 0))
/* 524 */         return true;
/*     */     }
/* 526 */     return par1 == 1 ? isItemFuel(par2ItemStack) : false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] func_94128_d(int par1)
/*     */   {
/* 536 */     return par1 == 1 ? slots_top : par1 == 0 ? slots_bottom : slots_sides;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 546 */     return par3 == 1 ? false : func_94041_b(par1, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 556 */     return (par3 != 0) || (par1 != 1) || (par2ItemStack.func_77973_b() == Items.field_151133_ar);
/*     */   }
/*     */   
/*     */   public Aspect takeRandomAspect(AspectList exlude)
/*     */   {
/* 561 */     if (this.aspects.size() > 0) {
/* 562 */       AspectList temp = this.aspects.copy();
/* 563 */       if (exlude.size() > 0)
/* 564 */         for (Aspect a : exlude.getAspects()) temp.remove(a);
/* 565 */       if (temp.size() > 0) {
/* 566 */         Aspect tag = temp.getAspects()[this.field_145850_b.field_73012_v.nextInt(temp.getAspects().length)];
/* 567 */         this.aspects.remove(tag, 1);
/* 568 */         this.vis -= 1;
/* 569 */         return tag;
/*     */       }
/*     */     }
/* 572 */     return null;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tag, int amount) {
/* 576 */     if ((this.aspects != null) && (this.aspects.getAmount(tag) >= amount)) {
/* 577 */       this.aspects.remove(tag, amount);
/* 578 */       this.vis -= amount;
/* 579 */       return true;
/*     */     }
/* 581 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileAlchemyFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */