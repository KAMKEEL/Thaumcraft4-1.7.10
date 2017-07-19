/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ 
/*     */ public class TileThaumcraftInventory extends TileThaumcraft implements ISidedInventory
/*     */ {
/*  13 */   protected ItemStack[] itemStacks = new ItemStack[1];
/*     */   protected String customName;
/*  15 */   protected int[] syncedSlots = new int[0];
/*     */   
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  20 */     return this.itemStacks.length;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  26 */     return this.itemStacks[par1];
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  32 */     if (this.itemStacks[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/*  36 */       if (this.itemStacks[par1].field_77994_a <= par2)
/*     */       {
/*  38 */         ItemStack itemstack = this.itemStacks[par1];
/*  39 */         this.itemStacks[par1] = null;
/*  40 */         func_70296_d();
/*  41 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/*  45 */       ItemStack itemstack = this.itemStacks[par1].func_77979_a(par2);
/*     */       
/*  47 */       if (this.itemStacks[par1].field_77994_a == 0)
/*     */       {
/*  49 */         this.itemStacks[par1] = null;
/*     */       }
/*  51 */       func_70296_d();
/*  52 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  57 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/*  64 */     if (this.itemStacks[par1] != null)
/*     */     {
/*  66 */       ItemStack itemstack = this.itemStacks[par1];
/*  67 */       this.itemStacks[par1] = null;
/*  68 */       func_70296_d();
/*  69 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*  73 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/*  80 */     this.itemStacks[par1] = par2ItemStack;
/*     */     
/*  82 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/*  84 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/*  86 */     func_70296_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_145825_b()
/*     */   {
/*  92 */     return func_145818_k_() ? this.customName : "container.thaumcraft";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/*  98 */     return (this.customName != null) && (this.customName.length() > 0);
/*     */   }
/*     */   
/*     */   public void setGuiDisplayName(String par1Str)
/*     */   {
/* 103 */     this.customName = par1Str;
/*     */   }
/*     */   
/*     */   private boolean isSyncedSlot(int slot) {
/* 107 */     for (int s : this.syncedSlots) {
/* 108 */       if (s == slot) return true;
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbtCompound)
/*     */   {
/* 115 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("ItemsSynced", 10);
/* 116 */     this.itemStacks = new ItemStack[func_70302_i_()];
/* 117 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 119 */       if (isSyncedSlot(i)) {
/* 120 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 121 */         byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */         
/* 123 */         if ((b0 >= 0) && (b0 < this.itemStacks.length))
/*     */         {
/* 125 */           this.itemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbtCompound)
/*     */   {
/* 133 */     NBTTagList nbttaglist = new NBTTagList();
/* 134 */     for (int i = 0; i < this.itemStacks.length; i++)
/*     */     {
/* 136 */       if ((this.itemStacks[i] != null) && (isSyncedSlot(i)))
/*     */       {
/* 138 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 139 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 140 */         this.itemStacks[i].func_77955_b(nbttagcompound1);
/* 141 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/* 144 */     nbtCompound.func_74782_a("ItemsSynced", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 150 */     super.func_145839_a(nbtCompound);
/*     */     
/* 152 */     if (nbtCompound.func_74764_b("CustomName"))
/*     */     {
/* 154 */       this.customName = nbtCompound.func_74779_i("CustomName");
/*     */     }
/*     */     
/* 157 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
/* 158 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 160 */       if (!isSyncedSlot(i)) {
/* 161 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 162 */         byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */         
/* 164 */         if ((b0 >= 0) && (b0 < this.itemStacks.length))
/*     */         {
/* 166 */           this.itemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 176 */     super.func_145841_b(nbtCompound);
/*     */     
/* 178 */     if (func_145818_k_())
/*     */     {
/* 180 */       nbtCompound.func_74778_a("CustomName", this.customName);
/*     */     }
/*     */     
/* 183 */     NBTTagList nbttaglist = new NBTTagList();
/* 184 */     for (int i = 0; i < this.itemStacks.length; i++)
/*     */     {
/* 186 */       if ((this.itemStacks[i] != null) && (!isSyncedSlot(i)))
/*     */       {
/* 188 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 189 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 190 */         this.itemStacks[i].func_77955_b(nbttagcompound1);
/* 191 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/* 194 */     nbtCompound.func_74782_a("Items", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 200 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 206 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70295_k_() {}
/*     */   
/*     */ 
/*     */   public void func_70305_f() {}
/*     */   
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 218 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int[] func_94128_d(int par1)
/*     */   {
/* 224 */     return new int[] { 0 };
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 230 */     return func_94041_b(par1, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 236 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileThaumcraftInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */