/*     */ package thaumcraft.common.entities.golems;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ 
/*     */ public class InventoryTrunk implements net.minecraft.inventory.IInventory
/*     */ {
/*     */   public ItemStack[] inventory;
/*     */   public EntityTravelingTrunk ent;
/*     */   public boolean inventoryChanged;
/*     */   public int slotCount;
/*  15 */   public int stacklimit = 64;
/*     */   
/*     */   public InventoryTrunk(EntityTravelingTrunk entity, int slots) {
/*  18 */     this.slotCount = slots;
/*  19 */     this.inventory = new ItemStack[36];
/*  20 */     this.inventoryChanged = false;
/*  21 */     this.ent = entity;
/*     */   }
/*     */   
/*     */   public InventoryTrunk(EntityTravelingTrunk entity, int slots, int lim) {
/*  25 */     this.slotCount = slots;
/*  26 */     this.inventory = new ItemStack[36];
/*  27 */     this.inventoryChanged = false;
/*  28 */     this.stacklimit = lim;
/*  29 */     this.ent = entity;
/*     */   }
/*     */   
/*     */   public int getInventorySlotContainItem(Item i)
/*     */   {
/*  34 */     for (int j = 0; j < this.inventory.length; j++) {
/*  35 */       if ((this.inventory[j] != null) && (this.inventory[j].func_77973_b() == i)) {
/*  36 */         return j;
/*     */       }
/*     */     }
/*  39 */     return -1;
/*     */   }
/*     */   
/*     */   public int getFirstEmptyStack()
/*     */   {
/*  44 */     for (int i = 0; i < this.inventory.length; i++) {
/*  45 */       if (this.inventory[i] == null) {
/*  46 */         return i;
/*     */       }
/*     */     }
/*  49 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int i, int j)
/*     */   {
/*  56 */     ItemStack[] aitemstack = this.inventory;
/*  57 */     if (aitemstack[i] != null) {
/*  58 */       if (aitemstack[i].field_77994_a <= j) {
/*  59 */         ItemStack itemstack = aitemstack[i];
/*  60 */         aitemstack[i] = null;
/*  61 */         return itemstack;
/*     */       }
/*     */       
/*  64 */       ItemStack itemstack1 = aitemstack[i].func_77979_a(j);
/*  65 */       if (aitemstack[i].field_77994_a == 0) {
/*  66 */         aitemstack[i] = null;
/*     */       }
/*  68 */       return itemstack1;
/*     */     }
/*     */     
/*  71 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70299_a(int i, ItemStack itemstack)
/*     */   {
/*  77 */     ItemStack[] aitemstack = this.inventory;
/*  78 */     aitemstack[i] = itemstack;
/*     */   }
/*     */   
/*     */   public NBTTagList writeToNBT(NBTTagList nbttaglist) {
/*  82 */     for (int i = 0; i < this.inventory.length; i++) {
/*  83 */       if (this.inventory[i] != null) {
/*  84 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/*  85 */         nbttagcompound.func_74774_a("Slot", (byte)i);
/*  86 */         this.inventory[i].func_77955_b(nbttagcompound);
/*  87 */         nbttaglist.func_74742_a(nbttagcompound);
/*     */       }
/*     */     }
/*  90 */     return nbttaglist;
/*     */   }
/*     */   
/*     */   public void readFromNBT(NBTTagList nbttaglist) {
/*  94 */     this.inventory = new ItemStack[this.inventory.length];
/*  95 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*  96 */       NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
/*     */       
/*  98 */       int j = nbttagcompound.func_74771_c("Slot") & 0xFF;
/*  99 */       ItemStack itemstack = ItemStack.func_77949_a(nbttagcompound);
/*     */       
/* 101 */       if (itemstack.func_77973_b() != null)
/*     */       {
/*     */ 
/* 104 */         if ((j >= 0) && (j < this.inventory.length)) {
/* 105 */           this.inventory[j] = itemstack;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_70302_i_() {
/* 112 */     return this.slotCount;
/*     */   }
/*     */   
/*     */   public ItemStack func_70301_a(int i)
/*     */   {
/* 117 */     ItemStack[] aitemstack = this.inventory;
/* 118 */     return aitemstack[i];
/*     */   }
/*     */   
/*     */   public int func_70297_j_()
/*     */   {
/* 123 */     return this.stacklimit;
/*     */   }
/*     */   
/*     */   public void dropAllItems()
/*     */   {
/* 128 */     for (int i = 0; i < this.inventory.length; i++) {
/* 129 */       if (this.inventory[i] != null) {
/* 130 */         this.ent.func_70099_a(this.inventory[i], 0.0F);
/* 131 */         this.inventory[i] = null;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer entityplayer)
/*     */   {
/* 139 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70304_b(int var1)
/*     */   {
/* 145 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 151 */     return true;
/*     */   }
/*     */   
/*     */   public String func_145825_b()
/*     */   {
/* 156 */     return "Inventory";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 162 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70296_d()
/*     */   {
/* 167 */     this.inventoryChanged = true;
/*     */   }
/*     */   
/*     */   public void func_70295_k_()
/*     */   {
/* 172 */     if ((this.ent instanceof EntityTravelingTrunk)) {
/* 173 */       this.ent.setOpen(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70305_f()
/*     */   {
/* 179 */     if ((this.ent instanceof EntityTravelingTrunk)) {
/* 180 */       this.ent.setOpen(false);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/InventoryTrunk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */