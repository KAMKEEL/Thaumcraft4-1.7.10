/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.tiles.TileArcaneWorkbench;
/*     */ 
/*     */ public class ContainerArcaneWorkbench extends Container
/*     */ {
/*     */   private TileArcaneWorkbench tileEntity;
/*     */   private InventoryPlayer ip;
/*     */   
/*     */   public ContainerArcaneWorkbench(InventoryPlayer par1InventoryPlayer, TileArcaneWorkbench e)
/*     */   {
/*  23 */     this.tileEntity = e;
/*  24 */     this.tileEntity.eventHandler = this;
/*  25 */     this.ip = par1InventoryPlayer;
/*  26 */     func_75146_a(new SlotCraftingArcaneWorkbench(par1InventoryPlayer.field_70458_d, this.tileEntity, this.tileEntity, 9, 160, 64));
/*  27 */     func_75146_a(new SlotLimitedByWand(this.tileEntity, 10, 160, 24));
/*     */     
/*     */ 
/*     */ 
/*  31 */     for (int var6 = 0; var6 < 3; var6++)
/*     */     {
/*  33 */       for (int var7 = 0; var7 < 3; var7++)
/*     */       {
/*  35 */         func_75146_a(new Slot(this.tileEntity, var7 + var6 * 3, 40 + var7 * 24, 40 + var6 * 24));
/*     */       }
/*     */     }
/*     */     
/*  39 */     for (var6 = 0; var6 < 3; var6++)
/*     */     {
/*  41 */       for (int var7 = 0; var7 < 9; var7++)
/*     */       {
/*  43 */         func_75146_a(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 16 + var7 * 18, 151 + var6 * 18));
/*     */       }
/*     */     }
/*     */     
/*  47 */     for (var6 = 0; var6 < 9; var6++)
/*     */     {
/*  49 */       func_75146_a(new Slot(par1InventoryPlayer, var6, 16 + var6 * 18, 209));
/*     */     }
/*     */     
/*  52 */     func_75130_a(this.tileEntity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75130_a(IInventory par1IInventory)
/*     */   {
/*  61 */     InventoryCrafting ic = new InventoryCrafting(new ContainerDummy(), 3, 3);
/*  62 */     for (int a = 0; a < 9; a++) {
/*  63 */       ic.func_70299_a(a, this.tileEntity.func_70301_a(a));
/*     */     }
/*  65 */     this.tileEntity.setInventorySlotContentsSoftly(9, CraftingManager.func_77594_a().func_82787_a(ic, this.tileEntity.func_145831_w()));
/*     */     
/*     */ 
/*  68 */     if ((this.tileEntity.func_70301_a(9) == null) && (this.tileEntity.func_70301_a(10) != null) && ((this.tileEntity.func_70301_a(10).func_77973_b() instanceof ItemWandCasting)))
/*     */     {
/*     */ 
/*  71 */       ItemWandCasting wand = (ItemWandCasting)this.tileEntity.func_70301_a(10).func_77973_b();
/*     */       
/*  73 */       if (wand.consumeAllVisCrafting(this.tileEntity.func_70301_a(10), this.ip.field_70458_d, ThaumcraftCraftingManager.findMatchingArcaneRecipeAspects(this.tileEntity, this.ip.field_70458_d), false))
/*     */       {
/*     */ 
/*     */ 
/*  77 */         this.tileEntity.setInventorySlotContentsSoftly(9, ThaumcraftCraftingManager.findMatchingArcaneRecipe(this.tileEntity, this.ip.field_70458_d));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/*  88 */     super.func_75134_a(par1EntityPlayer);
/*     */     
/*  90 */     if (!this.tileEntity.func_145831_w().field_72995_K)
/*     */     {
/*  92 */       this.tileEntity.eventHandler = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/*  99 */     return this.tileEntity.func_145831_w().func_147438_o(this.tileEntity.field_145851_c, this.tileEntity.field_145848_d, this.tileEntity.field_145849_e) == this.tileEntity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par1)
/*     */   {
/* 108 */     ItemStack var2 = null;
/* 109 */     Slot var3 = (Slot)this.field_75151_b.get(par1);
/*     */     
/* 111 */     if ((var3 != null) && (var3.func_75216_d()))
/*     */     {
/* 113 */       ItemStack var4 = var3.func_75211_c();
/* 114 */       var2 = var4.func_77946_l();
/*     */       
/* 116 */       if (par1 == 0)
/*     */       {
/* 118 */         if (!func_75135_a(var4, 11, 47, true))
/*     */         {
/* 120 */           return null;
/*     */         }
/*     */         
/* 123 */         var3.func_75220_a(var4, var2);
/*     */       }
/* 125 */       else if ((par1 >= 11) && (par1 < 38))
/*     */       {
/* 127 */         if (((var4.func_77973_b() instanceof ItemWandCasting)) && (!((ItemWandCasting)var4.func_77973_b()).isStaff(var4)))
/*     */         {
/* 129 */           if (!func_75135_a(var4, 1, 2, false)) {
/* 130 */             return null;
/*     */           }
/* 132 */           var3.func_75220_a(var4, var2);
/*     */         }
/* 134 */         else if (!func_75135_a(var4, 38, 47, false))
/*     */         {
/* 136 */           return null;
/*     */         }
/*     */         
/*     */ 
/*     */       }
/* 141 */       else if ((par1 >= 38) && (par1 < 47))
/*     */       {
/* 143 */         if (((var4.func_77973_b() instanceof ItemWandCasting)) && (!((ItemWandCasting)var4.func_77973_b()).isStaff(var4)))
/*     */         {
/* 145 */           if (!func_75135_a(var4, 1, 2, false)) {
/* 146 */             return null;
/*     */           }
/* 148 */           var3.func_75220_a(var4, var2);
/*     */         }
/* 150 */         else if (!func_75135_a(var4, 11, 38, false))
/*     */         {
/* 152 */           return null;
/*     */         }
/*     */         
/*     */       }
/* 156 */       else if (!func_75135_a(var4, 11, 47, false))
/*     */       {
/* 158 */         return null;
/*     */       }
/*     */       
/* 161 */       if (var4.field_77994_a == 0)
/*     */       {
/* 163 */         var3.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 167 */         var3.func_75218_e();
/*     */       }
/*     */       
/* 170 */       if (var4.field_77994_a == var2.field_77994_a)
/*     */       {
/* 172 */         return null;
/*     */       }
/*     */       
/* 175 */       var3.func_82870_a(this.ip.field_70458_d, var4);
/*     */     }
/*     */     
/* 178 */     return var2;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
/*     */   {
/* 184 */     if (par3 == 4) {
/* 185 */       par2 = 1;
/* 186 */       return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
/*     */     }
/* 188 */     if (((par1 == 0) || (par1 == 1)) && (par2 > 0)) par2 = 0;
/* 189 */     return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
/*     */   {
/* 195 */     return (par2Slot.field_75224_c != this.tileEntity) && (super.func_94530_a(par1ItemStack, par2Slot));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerArcaneWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */