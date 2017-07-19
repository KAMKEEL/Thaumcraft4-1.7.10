/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.common.entities.golems.EntityTravelingTrunk;
/*     */ 
/*     */ public class InventoryMob implements IInventory
/*     */ {
/*     */   public ItemStack[] inventory;
/*     */   public Entity ent;
/*     */   public boolean inventoryChanged;
/*     */   public int slotCount;
/*  21 */   public int stacklimit = 64;
/*     */   
/*     */   public InventoryMob(Entity entity, int slots) {
/*  24 */     this.slotCount = slots;
/*  25 */     this.inventory = new ItemStack[this.slotCount];
/*  26 */     this.inventoryChanged = false;
/*  27 */     this.ent = entity;
/*     */   }
/*     */   
/*     */   public InventoryMob(Entity entity, int slots, int lim) {
/*  31 */     this.slotCount = slots;
/*  32 */     this.inventory = new ItemStack[this.slotCount];
/*  33 */     this.inventoryChanged = false;
/*  34 */     this.stacklimit = lim;
/*  35 */     this.ent = entity;
/*     */   }
/*     */   
/*     */   public int getInventorySlotContainItem(Item i)
/*     */   {
/*  40 */     for (int j = 0; j < this.inventory.length; j++) {
/*  41 */       if ((this.inventory[j] != null) && (this.inventory[j].func_77973_b() == i)) {
/*  42 */         return j;
/*     */       }
/*     */     }
/*  45 */     return -1;
/*     */   }
/*     */   
/*     */   public int storeItemStack(ItemStack itemstack) {
/*  49 */     for (int i = 0; i < this.inventory.length; i++) {
/*  50 */       if ((this.inventory[i] != null) && (this.inventory[i].func_77973_b() == itemstack.func_77973_b()) && (this.inventory[i].func_77985_e()) && (this.inventory[i].field_77994_a < this.inventory[i].func_77976_d()) && (this.inventory[i].field_77994_a < func_70297_j_()) && ((!this.inventory[i].func_77981_g()) || (this.inventory[i].func_77960_j() == itemstack.func_77960_j())))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  57 */         return i;
/*     */       }
/*     */     }
/*  60 */     return -1;
/*     */   }
/*     */   
/*     */   public int getFirstEmptyStack() {
/*  64 */     for (int i = 0; i < this.inventory.length; i++) {
/*  65 */       if (this.inventory[i] == null) {
/*  66 */         return i;
/*     */       }
/*     */     }
/*  69 */     return -1;
/*     */   }
/*     */   
/*     */   public int storePartialItemStack(ItemStack itemstack)
/*     */   {
/*  74 */     Item i = itemstack.func_77973_b();
/*  75 */     int j = itemstack.field_77994_a;
/*  76 */     int k = storeItemStack(itemstack);
/*  77 */     if (k < 0) {
/*  78 */       k = getFirstEmptyStack();
/*     */     }
/*  80 */     if (k < 0) {
/*  81 */       return j;
/*     */     }
/*  83 */     if (this.inventory[k] == null) {
/*  84 */       this.inventory[k] = new ItemStack(i, 0, itemstack.func_77960_j());
/*     */     }
/*  86 */     int l = j;
/*  87 */     if (l > this.inventory[k].func_77976_d() - this.inventory[k].field_77994_a) {
/*  88 */       l = this.inventory[k].func_77976_d() - this.inventory[k].field_77994_a;
/*     */     }
/*  90 */     if (l > func_70297_j_() - this.inventory[k].field_77994_a) {
/*  91 */       l = func_70297_j_() - this.inventory[k].field_77994_a;
/*     */     }
/*  93 */     if (l == 0) {
/*  94 */       return j;
/*     */     }
/*  96 */     j -= l;
/*  97 */     this.inventory[k].field_77994_a += l;
/*  98 */     this.inventory[k].field_77992_b = 5;
/*  99 */     return j;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addItemStackToInventory(ItemStack itemstack)
/*     */   {
/* 105 */     if (!itemstack.func_77951_h()) {
/*     */       int i;
/*     */       do {
/* 108 */         i = itemstack.field_77994_a;
/* 109 */         itemstack.field_77994_a = storePartialItemStack(itemstack);
/* 110 */       } while ((itemstack.field_77994_a > 0) && (itemstack.field_77994_a < i));
/* 111 */       return itemstack.field_77994_a < i;
/*     */     }
/* 113 */     int j = getFirstEmptyStack();
/* 114 */     if (j >= 0) {
/* 115 */       this.inventory[j] = ItemStack.func_77944_b(itemstack);
/* 116 */       itemstack.field_77994_a = 0;
/* 117 */       return true;
/*     */     }
/* 119 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70298_a(int i, int j)
/*     */   {
/* 125 */     ItemStack[] aitemstack = this.inventory;
/* 126 */     if (aitemstack[i] != null) {
/* 127 */       if (aitemstack[i].field_77994_a <= j) {
/* 128 */         ItemStack itemstack = aitemstack[i];
/* 129 */         aitemstack[i] = null;
/* 130 */         return itemstack;
/*     */       }
/*     */       
/* 133 */       ItemStack itemstack1 = aitemstack[i].func_77979_a(j);
/* 134 */       if (aitemstack[i].field_77994_a == 0) {
/* 135 */         aitemstack[i] = null;
/*     */       }
/* 137 */       return itemstack1;
/*     */     }
/*     */     
/* 140 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70299_a(int i, ItemStack itemstack)
/*     */   {
/* 146 */     ItemStack[] aitemstack = this.inventory;
/* 147 */     aitemstack[i] = itemstack;
/*     */   }
/*     */   
/*     */   public NBTTagList writeToNBT(NBTTagList nbttaglist) {
/* 151 */     for (int i = 0; i < this.inventory.length; i++) {
/* 152 */       if (this.inventory[i] != null) {
/* 153 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 154 */         nbttagcompound.func_74774_a("Slot", (byte)i);
/* 155 */         this.inventory[i].func_77955_b(nbttagcompound);
/* 156 */         nbttaglist.func_74742_a(nbttagcompound);
/*     */       }
/*     */     }
/* 159 */     return nbttaglist;
/*     */   }
/*     */   
/*     */   public void readFromNBT(NBTTagList nbttaglist) {
/* 163 */     this.inventory = new ItemStack[this.slotCount];
/* 164 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/* 165 */       NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
/* 166 */       int j = nbttagcompound.func_74771_c("Slot") & 0xFF;
/* 167 */       ItemStack itemstack = ItemStack.func_77949_a(nbttagcompound);
/*     */       
/* 169 */       if (itemstack.func_77973_b() != null)
/*     */       {
/*     */ 
/* 172 */         if ((j >= 0) && (j < this.inventory.length)) {
/* 173 */           this.inventory[j] = itemstack;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_70302_i_() {
/* 180 */     return this.inventory.length + 1;
/*     */   }
/*     */   
/*     */   public ItemStack func_70301_a(int i)
/*     */   {
/* 185 */     ItemStack[] aitemstack = this.inventory;
/* 186 */     return aitemstack[i];
/*     */   }
/*     */   
/*     */   public int func_70297_j_()
/*     */   {
/* 191 */     return this.stacklimit;
/*     */   }
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer entityplayer) {
/* 195 */     if (this.ent.field_70128_L) {
/* 196 */       return false;
/*     */     }
/* 198 */     return entityplayer.func_70068_e(this.ent) <= 64.0D;
/*     */   }
/*     */   
/*     */   public boolean func_28018_c(ItemStack itemstack)
/*     */   {
/* 203 */     for (int j = 0; j < this.inventory.length; j++) {
/* 204 */       if ((this.inventory[j] != null) && (ItemStack.func_77989_b(this.inventory[j], itemstack))) {
/* 205 */         return true;
/*     */       }
/*     */     }
/* 208 */     return false;
/*     */   }
/*     */   
/*     */   public void dropAllItems() {
/* 212 */     for (int i = 0; i < this.inventory.length; i++) {
/* 213 */       if (this.inventory[i] != null) {
/* 214 */         this.ent.func_70099_a(this.inventory[i], 0.0F);
/* 215 */         this.inventory[i] = null;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer)
/*     */   {
/* 222 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack func_70304_b(int var1)
/*     */   {
/* 227 */     return null;
/*     */   }
/*     */   
/*     */   public boolean hasSomething() {
/* 231 */     for (int a = 0; a < this.slotCount; a++) {
/* 232 */       if (this.inventory[a] != null) {
/* 233 */         return true;
/*     */       }
/*     */     }
/* 236 */     return false;
/*     */   }
/*     */   
/*     */   public boolean allEmpty() {
/* 240 */     for (int a = 0; a < this.slotCount; a++) {
/* 241 */       if (this.inventory[a] != null) return false;
/*     */     }
/* 243 */     return true;
/*     */   }
/*     */   
/*     */   public int getAmountNeeded(ItemStack stackInSlot) {
/* 247 */     int amt = 0;
/* 248 */     for (int a = 0; a < this.slotCount; a++) {
/* 249 */       if ((this.inventory[a] != null) && (this.inventory[a].func_77969_a(stackInSlot))) amt += this.inventory[a].field_77994_a;
/*     */     }
/* 251 */     return amt;
/*     */   }
/*     */   
/*     */   public int getAmountNeededSmart(ItemStack stackInSlot, boolean fuzzy) {
/* 255 */     int amt = 0;
/* 256 */     for (int a = 0; a < this.slotCount; a++) {
/* 257 */       if (this.inventory[a] != null) {
/* 258 */         if (fuzzy) {
/* 259 */           if (this.inventory[a].func_77969_a(stackInSlot)) {
/* 260 */             amt += this.inventory[a].field_77994_a;
/*     */           } else {
/* 262 */             int od = OreDictionary.getOreID(this.inventory[a]);
/* 263 */             if (od != -1) {
/* 264 */               ItemStack[] ores = (ItemStack[])OreDictionary.getOres(Integer.valueOf(od)).toArray(new ItemStack[0]);
/* 265 */               if (ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { stackInSlot }, ores)) {
/* 266 */                 amt += this.inventory[a].field_77994_a;
/*     */               }
/*     */             }
/*     */           }
/* 270 */         } else if ((this.inventory[a].func_77969_a(stackInSlot)) && (ItemStack.func_77970_a(this.inventory[a], stackInSlot))) {
/* 271 */           amt += this.inventory[a].field_77994_a;
/*     */         }
/*     */       }
/*     */     }
/* 275 */     return amt;
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getItemsNeeded(boolean fuzzy) {
/* 279 */     int amt = 0;
/* 280 */     ArrayList<ItemStack> needed = new ArrayList();
/* 281 */     for (int a = 0; a < this.slotCount; a++) {
/* 282 */       if (this.inventory[a] != null)
/* 283 */         if (fuzzy) {
/* 284 */           int od = OreDictionary.getOreID(this.inventory[a]);
/* 285 */           if (od != -1) {
/* 286 */             ItemStack[] ores = (ItemStack[])OreDictionary.getOres(Integer.valueOf(od)).toArray(new ItemStack[0]);
/* 287 */             for (ItemStack ore : ores) {
/* 288 */               needed.add(ore.func_77946_l());
/*     */             }
/*     */           } else {
/* 291 */             needed.add(this.inventory[a].func_77946_l());
/*     */           }
/*     */         } else {
/* 294 */           needed.add(this.inventory[a].func_77946_l());
/*     */         }
/*     */     }
/* 297 */     return needed;
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 302 */     return true;
/*     */   }
/*     */   
/*     */   public String func_145825_b()
/*     */   {
/* 307 */     return "Inventory";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 312 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70296_d()
/*     */   {
/* 317 */     this.inventoryChanged = true;
/*     */   }
/*     */   
/*     */   public void func_70295_k_()
/*     */   {
/* 322 */     if ((this.ent instanceof EntityTravelingTrunk)) {
/* 323 */       ((EntityTravelingTrunk)this.ent).setOpen(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70305_f()
/*     */   {
/* 329 */     if ((this.ent instanceof EntityTravelingTrunk)) {
/* 330 */       ((EntityTravelingTrunk)this.ent).setOpen(false);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/InventoryMob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */