/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryHandMirror
/*     */   implements IInventory
/*     */ {
/*     */   private ItemStack[] stackList;
/*     */   private Container eventHandler;
/*     */   
/*     */   public InventoryHandMirror(Container par1Container)
/*     */   {
/*  20 */     this.stackList = new ItemStack[1];
/*  21 */     this.eventHandler = par1Container;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  30 */     return this.stackList.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  39 */     return par1 >= func_70302_i_() ? null : this.stackList[par1];
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
/*  50 */     if (this.stackList[par1] != null)
/*     */     {
/*  52 */       ItemStack var2 = this.stackList[par1];
/*  53 */       this.stackList[par1] = null;
/*  54 */       return var2;
/*     */     }
/*     */     
/*     */ 
/*  58 */     return null;
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
/*  69 */     if (this.stackList[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/*  73 */       if (this.stackList[par1].field_77994_a <= par2)
/*     */       {
/*  75 */         ItemStack var3 = this.stackList[par1];
/*  76 */         this.stackList[par1] = null;
/*  77 */         this.eventHandler.func_75130_a(this);
/*  78 */         return var3;
/*     */       }
/*     */       
/*     */ 
/*  82 */       ItemStack var3 = this.stackList[par1].func_77979_a(par2);
/*     */       
/*  84 */       if (this.stackList[par1].field_77994_a == 0)
/*     */       {
/*  86 */         this.stackList[par1] = null;
/*     */       }
/*     */       
/*  89 */       this.eventHandler.func_75130_a(this);
/*  90 */       return var3;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  95 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 105 */     this.stackList[par1] = par2ItemStack;
/* 106 */     this.eventHandler.func_75130_a(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 116 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 125 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 130 */     return true;
/*     */   }
/*     */   
/*     */   public String func_145825_b()
/*     */   {
/* 135 */     return "container.handmirror";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 140 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70296_d() {}
/*     */   
/*     */   public void func_70295_k_() {}
/*     */   
/*     */   public void func_70305_f() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/InventoryHandMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */