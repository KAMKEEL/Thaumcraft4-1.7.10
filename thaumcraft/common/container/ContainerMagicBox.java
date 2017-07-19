/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import thaumcraft.common.tiles.TileMagicBox;
/*     */ 
/*     */ public class ContainerMagicBox extends net.minecraft.inventory.Container
/*     */ {
/*     */   private TileMagicBox box;
/*     */   public net.minecraft.inventory.IInventory playerInv;
/*     */   private int numRows;
/*     */   
/*     */   public ContainerMagicBox(net.minecraft.inventory.IInventory par1IInventory, TileMagicBox par2IInventory)
/*     */   {
/*  17 */     this.box = par2IInventory;
/*  18 */     this.numRows = (par2IInventory.func_70302_i_() / 9);
/*  19 */     this.playerInv = par1IInventory;
/*  20 */     par2IInventory.func_70295_k_();
/*     */     
/*  22 */     bindBoxInventory(0);
/*  23 */     bindPlayerInventory();
/*     */     
/*  25 */     if ((this.box.func_145831_w() != null) && (this.box.func_145831_w().field_72995_K)) {
/*  26 */       TileMagicBox.tc = this;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void bindBoxInventory(int row)
/*     */   {
/*  34 */     for (int j = 0; j < 3; j++)
/*     */     {
/*  36 */       for (int k = 0; k < 9; k++)
/*     */       {
/*  38 */         func_75146_a(new Slot(this.box, k + (j + row) * 9, 8 + k * 18, 18 + j * 18));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   void bindPlayerInventory()
/*     */   {
/*  46 */     for (int j = 0; j < 3; j++)
/*     */     {
/*  48 */       for (int k = 0; k < 9; k++)
/*     */       {
/*  50 */         func_75146_a(new Slot(this.playerInv, k + j * 9 + 9, 8 + k * 18, 103 + j * 18));
/*     */       }
/*     */     }
/*     */     
/*  54 */     for (j = 0; j < 9; j++)
/*     */     {
/*  56 */       func_75146_a(new Slot(this.playerInv, j, 8 + j * 18, 161));
/*     */     }
/*     */   }
/*     */   
/*     */   public void refreshInventory() {
/*  61 */     this.field_75153_a.clear();
/*  62 */     this.field_75151_b.clear();
/*  63 */     bindBoxInventory(0);
/*  64 */     bindPlayerInventory();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/*  70 */     return this.box.func_70300_a(par1EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/*  79 */     ItemStack itemstack = null;
/*  80 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/*  82 */     if ((slot != null) && (slot.func_75216_d()))
/*     */     {
/*  84 */       ItemStack itemstack1 = slot.func_75211_c();
/*  85 */       itemstack = itemstack1.func_77946_l();
/*     */       
/*  87 */       if (par2 < this.numRows * 9)
/*     */       {
/*  89 */         if (!func_75135_a(itemstack1, this.numRows * 9, this.field_75151_b.size(), true))
/*     */         {
/*  91 */           return null;
/*     */         }
/*     */       }
/*  94 */       else if (!func_75135_a(itemstack1, 0, this.numRows * 9, false))
/*     */       {
/*  96 */         return null;
/*     */       }
/*     */       
/*  99 */       if (itemstack1.field_77994_a == 0)
/*     */       {
/* 101 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 105 */         slot.func_75218_e();
/*     */       }
/*     */     }
/*     */     
/* 109 */     return itemstack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 118 */     super.func_75134_a(par1EntityPlayer);
/* 119 */     this.box.func_70305_f();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerMagicBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */