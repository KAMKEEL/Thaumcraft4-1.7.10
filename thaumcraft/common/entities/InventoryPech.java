/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ 
/*     */ public class InventoryPech implements IInventory
/*     */ {
/*     */   private final EntityPech theMerchant;
/*  12 */   private ItemStack[] theInventory = new ItemStack[5];
/*     */   private final EntityPlayer thePlayer;
/*     */   private Container eventHandler;
/*     */   
/*     */   public InventoryPech(EntityPlayer par1EntityPlayer, EntityPech par2IMerchant, Container par1Container)
/*     */   {
/*  18 */     this.thePlayer = par1EntityPlayer;
/*  19 */     this.theMerchant = par2IMerchant;
/*  20 */     this.eventHandler = par1Container;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  29 */     return this.theInventory.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  38 */     return this.theInventory[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  48 */     if (this.theInventory[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/*  52 */       if (this.theInventory[par1].field_77994_a <= par2)
/*     */       {
/*  54 */         ItemStack var3 = this.theInventory[par1];
/*  55 */         this.theInventory[par1] = null;
/*  56 */         this.eventHandler.func_75130_a(this);
/*  57 */         return var3;
/*     */       }
/*     */       
/*     */ 
/*  61 */       ItemStack var3 = this.theInventory[par1].func_77979_a(par2);
/*     */       
/*  63 */       if (this.theInventory[par1].field_77994_a == 0)
/*     */       {
/*  65 */         this.theInventory[par1] = null;
/*     */       }
/*     */       
/*  68 */       this.eventHandler.func_75130_a(this);
/*  69 */       return var3;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  74 */     return null;
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
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/*  87 */     if (this.theInventory[par1] != null)
/*     */     {
/*  89 */       ItemStack itemstack = this.theInventory[par1];
/*  90 */       this.theInventory[par1] = null;
/*  91 */       return itemstack;
/*     */     }
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
/* 105 */     this.theInventory[par1] = par2ItemStack;
/*     */     
/* 107 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 109 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/* 111 */     this.eventHandler.func_75130_a(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 121 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 130 */     return this.theMerchant.isTamed();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 139 */     return par1 == 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_145825_b()
/*     */   {
/* 145 */     return "entity.Pech.name";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 150 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70296_d()
/*     */   {
/* 155 */     this.eventHandler.func_75130_a(this);
/*     */   }
/*     */   
/*     */   public void func_70295_k_() {}
/*     */   
/*     */   public void func_70305_f() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/InventoryPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */