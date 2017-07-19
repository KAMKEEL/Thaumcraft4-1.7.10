/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class InventoryFake implements IInventory
/*     */ {
/*     */   private ItemStack[] stackList;
/*     */   
/*     */   public InventoryFake(ItemStack[] stackList)
/*     */   {
/*  14 */     this.stackList = stackList;
/*     */   }
/*     */   
/*     */   public InventoryFake(ArrayList<ItemStack> stackList) {
/*  18 */     this.stackList = ((ItemStack[])stackList.toArray(new ItemStack[0]));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  26 */     return this.stackList.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  34 */     return par1 >= func_70302_i_() ? null : this.stackList[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/*  44 */     if (this.stackList[par1] != null) {
/*  45 */       ItemStack var2 = this.stackList[par1];
/*  46 */       this.stackList[par1] = null;
/*  47 */       return var2;
/*     */     }
/*  49 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  59 */     if (this.stackList[par1] != null)
/*     */     {
/*     */ 
/*  62 */       if (this.stackList[par1].field_77994_a <= par2) {
/*  63 */         ItemStack var3 = this.stackList[par1];
/*  64 */         this.stackList[par1] = null;
/*  65 */         return var3;
/*     */       }
/*  67 */       ItemStack var3 = this.stackList[par1].func_77979_a(par2);
/*     */       
/*  69 */       if (this.stackList[par1].field_77994_a == 0) {
/*  70 */         this.stackList[par1] = null;
/*     */       }
/*     */       
/*  73 */       return var3;
/*     */     }
/*     */     
/*  76 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/*  86 */     this.stackList[par1] = par2ItemStack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/*  95 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 104 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   public String func_145825_b()
/*     */   {
/* 114 */     return "container.fake";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 119 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70296_d() {}
/*     */   
/*     */   public void func_70295_k_() {}
/*     */   
/*     */   public void func_70305_f() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/InventoryFake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */