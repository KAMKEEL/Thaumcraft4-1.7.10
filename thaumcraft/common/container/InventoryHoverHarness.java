/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.blocks.ItemJarFilled;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryHoverHarness
/*     */   implements IInventory
/*     */ {
/*     */   private ItemStack[] stackList;
/*     */   private Container eventHandler;
/*     */   
/*     */   public InventoryHoverHarness(Container par1Container)
/*     */   {
/*  23 */     this.stackList = new ItemStack[1];
/*  24 */     this.eventHandler = par1Container;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  33 */     return this.stackList.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  42 */     return par1 >= func_70302_i_() ? null : this.stackList[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/*  52 */     if (this.stackList[par1] != null)
/*     */     {
/*  54 */       ItemStack var2 = this.stackList[par1];
/*  55 */       this.stackList[par1] = null;
/*  56 */       return var2;
/*     */     }
/*     */     
/*     */ 
/*  60 */     return null;
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
/*  71 */     if (this.stackList[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/*  75 */       if (this.stackList[par1].field_77994_a <= par2)
/*     */       {
/*  77 */         ItemStack var3 = this.stackList[par1];
/*  78 */         this.stackList[par1] = null;
/*  79 */         this.eventHandler.func_75130_a(this);
/*  80 */         return var3;
/*     */       }
/*     */       
/*     */ 
/*  84 */       ItemStack var3 = this.stackList[par1].func_77979_a(par2);
/*     */       
/*  86 */       if (this.stackList[par1].field_77994_a == 0)
/*     */       {
/*  88 */         this.stackList[par1] = null;
/*     */       }
/*     */       
/*  91 */       this.eventHandler.func_75130_a(this);
/*  92 */       return var3;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  97 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 107 */     this.stackList[par1] = par2ItemStack;
/* 108 */     this.eventHandler.func_75130_a(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 118 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 127 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int i, ItemStack jar)
/*     */   {
/* 134 */     if ((jar != null) && ((jar.func_77973_b() instanceof ItemJarFilled)) && (jar.func_77942_o())) {
/* 135 */       AspectList aspects = ((ItemJarFilled)jar.func_77973_b()).getAspects(jar);
/* 136 */       if ((aspects != null) && (aspects.size() > 0) && (aspects.getAmount(Aspect.ENERGY) > 0)) {
/* 137 */         return true;
/*     */       }
/*     */     }
/* 140 */     return false;
/*     */   }
/*     */   
/*     */   public String func_145825_b()
/*     */   {
/* 145 */     return "container.hoverharness";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 150 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70296_d() {}
/*     */   
/*     */   public void func_70295_k_() {}
/*     */   
/*     */   public void func_70305_f() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/InventoryHoverHarness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */