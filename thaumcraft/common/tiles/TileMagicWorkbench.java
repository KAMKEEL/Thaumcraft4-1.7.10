/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ 
/*     */ public class TileMagicWorkbench
/*     */   extends TileThaumcraft implements IInventory, ISidedInventory
/*     */ {
/*  17 */   public ItemStack[] stackList = new ItemStack[11];
/*     */   
/*     */ 
/*     */   public Container eventHandler;
/*     */   
/*     */   protected int count;
/*     */   
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  27 */     return this.stackList.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  36 */     return par1 >= func_70302_i_() ? null : this.stackList[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack getStackInRowAndColumn(int par1, int par2)
/*     */   {
/*  46 */     if ((par1 >= 0) && (par1 < 3))
/*     */     {
/*  48 */       int var3 = par1 + par2 * 3;
/*  49 */       return func_70301_a(var3);
/*     */     }
/*     */     
/*     */ 
/*  53 */     return null;
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
/*  64 */     if (this.stackList[par1] != null)
/*     */     {
/*  66 */       ItemStack var2 = this.stackList[par1];
/*  67 */       this.stackList[par1] = null;
/*  68 */       func_70296_d();
/*  69 */       return var2;
/*     */     }
/*     */     
/*     */ 
/*  73 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  84 */     if (this.stackList[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/*  88 */       if (this.stackList[par1].field_77994_a <= par2)
/*     */       {
/*  90 */         ItemStack var3 = this.stackList[par1];
/*  91 */         this.stackList[par1] = null;
/*  92 */         if (this.eventHandler != null) this.eventHandler.func_75130_a(this);
/*  93 */         func_70296_d();
/*  94 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  95 */         return var3;
/*     */       }
/*     */       
/*     */ 
/*  99 */       ItemStack var3 = this.stackList[par1].func_77979_a(par2);
/*     */       
/* 101 */       if (this.stackList[par1].field_77994_a == 0)
/*     */       {
/* 103 */         this.stackList[par1] = null;
/*     */       }
/*     */       
/* 106 */       if (this.eventHandler != null) this.eventHandler.func_75130_a(this);
/* 107 */       func_70296_d();
/* 108 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 109 */       return var3;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 114 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 124 */     this.stackList[par1] = par2ItemStack;
/* 125 */     func_70296_d();
/* 126 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 127 */     if (this.eventHandler != null) { this.eventHandler.func_75130_a(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setInventorySlotContentsSoftly(int par1, ItemStack par2ItemStack)
/*     */   {
/* 133 */     this.stackList[par1] = par2ItemStack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 143 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 152 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 163 */     NBTTagList var2 = par1NBTTagCompound.func_150295_c("Inventory", 10);
/* 164 */     this.stackList = new ItemStack[func_70302_i_()];
/*     */     
/* 166 */     for (int var3 = 0; var3 < var2.func_74745_c(); var3++)
/*     */     {
/* 168 */       NBTTagCompound var4 = var2.func_150305_b(var3);
/* 169 */       int var5 = var4.func_74771_c("Slot") & 0xFF;
/*     */       
/* 171 */       if ((var5 >= 0) && (var5 < this.stackList.length))
/*     */       {
/* 173 */         this.stackList[var5] = ItemStack.func_77949_a(var4);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 183 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 185 */     for (int var3 = 0; var3 < this.stackList.length; var3++)
/*     */     {
/* 187 */       if (this.stackList[var3] != null)
/*     */       {
/* 189 */         NBTTagCompound var4 = new NBTTagCompound();
/* 190 */         var4.func_74774_a("Slot", (byte)var3);
/* 191 */         this.stackList[var3].func_77955_b(var4);
/* 192 */         var2.func_74742_a(var4);
/*     */       }
/*     */     }
/*     */     
/* 196 */     par1NBTTagCompound.func_74782_a("Inventory", var2);
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_145825_b()
/*     */   {
/* 202 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70295_k_() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70305_f() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 222 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 227 */     if ((i == 10) && (itemstack != null)) {
/* 228 */       if (!(itemstack.func_77973_b() instanceof ItemWandCasting)) return false;
/* 229 */       ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 230 */       return !wand.isStaff(itemstack);
/*     */     }
/* 232 */     return true;
/*     */   }
/*     */   
/*     */   public int[] func_94128_d(int var1)
/*     */   {
/* 237 */     return new int[] { 10 };
/*     */   }
/*     */   
/*     */   public boolean func_102007_a(int i, ItemStack itemstack, int j)
/*     */   {
/* 242 */     if ((i != 10) || (itemstack == null) || (!(itemstack.func_77973_b() instanceof ItemWandCasting))) return false;
/* 243 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 244 */     return !wand.isStaff(itemstack);
/*     */   }
/*     */   
/*     */   public boolean func_102008_b(int i, ItemStack itemstack, int j)
/*     */   {
/* 249 */     return i == 10;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileMagicWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */