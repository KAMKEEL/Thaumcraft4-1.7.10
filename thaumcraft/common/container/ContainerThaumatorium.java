/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.common.tiles.TileThaumatorium;
/*     */ 
/*     */ public class ContainerThaumatorium extends Container
/*     */ {
/*     */   private TileThaumatorium thaumatorium;
/*  19 */   private EntityPlayer player = null;
/*     */   
/*     */   public ContainerThaumatorium(InventoryPlayer par1InventoryPlayer, TileThaumatorium tileEntity)
/*     */   {
/*  23 */     this.player = par1InventoryPlayer.field_70458_d;
/*  24 */     this.thaumatorium = tileEntity;
/*  25 */     this.thaumatorium.eventHandler = this;
/*  26 */     func_75146_a(new Slot(tileEntity, 0, 48, 16));
/*     */     
/*     */ 
/*  29 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  31 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  33 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*  37 */     for (i = 0; i < 9; i++)
/*     */     {
/*  39 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */     
/*  42 */     func_75130_a(this.thaumatorium);
/*     */   }
/*     */   
/*  45 */   public ArrayList<CrucibleRecipe> recipes = new ArrayList();
/*     */   
/*     */ 
/*     */   public void func_75130_a(IInventory par1iInventory)
/*     */   {
/*  50 */     super.func_75130_a(par1iInventory);
/*  51 */     updateRecipes();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/*  58 */     super.func_75134_a(par1EntityPlayer);
/*     */     
/*  60 */     if (!this.thaumatorium.func_145831_w().field_72995_K)
/*     */     {
/*  62 */       this.thaumatorium.eventHandler = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateRecipes()
/*     */   {
/*  68 */     this.recipes.clear();
/*  69 */     Iterator i$; if ((this.thaumatorium.inputStack != null) || (this.thaumatorium.recipeHash != null)) {
/*  70 */       for (i$ = thaumcraft.api.ThaumcraftApi.getCraftingRecipes().iterator(); i$.hasNext();) { r = i$.next();
/*  71 */         if ((r instanceof CrucibleRecipe)) {
/*  72 */           if ((thaumcraft.common.lib.research.ResearchManager.isResearchComplete(this.player.func_70005_c_(), ((CrucibleRecipe)r).key)) && (((CrucibleRecipe)r).catalystMatches(this.thaumatorium.inputStack)))
/*     */           {
/*  74 */             this.recipes.add((CrucibleRecipe)r);
/*     */ 
/*     */           }
/*  77 */           else if ((this.thaumatorium.recipeHash != null) && (this.thaumatorium.recipeHash.size() > 0)) {
/*  78 */             for (Integer hash : this.thaumatorium.recipeHash) {
/*  79 */               if (((CrucibleRecipe)r).hash == hash.intValue()) {
/*  80 */                 this.recipes.add((CrucibleRecipe)r);
/*  81 */                 break;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     Object r;
/*     */   }
/*     */   
/*     */   public boolean func_75140_a(EntityPlayer par1EntityPlayer, int button)
/*     */   {
/*  94 */     if ((this.recipes.size() > 0) && 
/*  95 */       (button >= 0) && (button < this.recipes.size())) {
/*  96 */       boolean found = false;
/*  97 */       for (int a = 0; a < this.thaumatorium.recipeHash.size(); a++) {
/*  98 */         if (((CrucibleRecipe)this.recipes.get(button)).hash == ((Integer)this.thaumatorium.recipeHash.get(a)).intValue()) {
/*  99 */           found = true;
/* 100 */           this.thaumatorium.recipeEssentia.remove(a);
/* 101 */           this.thaumatorium.recipePlayer.remove(a);
/* 102 */           this.thaumatorium.recipeHash.remove(a);
/* 103 */           this.thaumatorium.currentCraft = -1;
/* 104 */           break;
/*     */         }
/*     */       }
/* 107 */       if (!found) {
/* 108 */         this.thaumatorium.recipeEssentia.add(((CrucibleRecipe)this.recipes.get(button)).aspects.copy());
/* 109 */         this.thaumatorium.recipePlayer.add(par1EntityPlayer.func_70005_c_());
/* 110 */         this.thaumatorium.recipeHash.add(Integer.valueOf(((CrucibleRecipe)this.recipes.get(button)).hash));
/*     */       }
/* 112 */       this.thaumatorium.func_70296_d();
/* 113 */       this.thaumatorium.func_145831_w().func_147471_g(this.thaumatorium.field_145851_c, this.thaumatorium.field_145848_d, this.thaumatorium.field_145849_e);
/* 114 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 118 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 124 */     return this.thaumatorium.func_70300_a(par1EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/* 133 */     ItemStack itemstack = null;
/* 134 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/* 136 */     if ((slot != null) && (slot.func_75216_d()))
/*     */     {
/* 138 */       ItemStack itemstack1 = slot.func_75211_c();
/* 139 */       itemstack = itemstack1.func_77946_l();
/*     */       
/* 141 */       if (par2 != 0)
/*     */       {
/* 143 */         if (!func_75135_a(itemstack1, 0, 1, false))
/*     */         {
/* 145 */           return null;
/*     */         }
/*     */       }
/* 148 */       else if ((par2 >= 1) && (par2 < 28))
/*     */       {
/* 150 */         if (!func_75135_a(itemstack1, 28, 37, false))
/*     */         {
/* 152 */           return null;
/*     */         }
/*     */       } else {
/* 155 */         if ((par2 >= 28) && (par2 < 37) && (!func_75135_a(itemstack1, 1, 28, false)))
/*     */         {
/* 157 */           return null;
/*     */         }
/* 159 */         if (!func_75135_a(itemstack1, 1, 37, false))
/*     */         {
/* 161 */           return null;
/*     */         }
/*     */       }
/* 164 */       if (itemstack1.field_77994_a == 0)
/*     */       {
/* 166 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 170 */         slot.func_75218_e();
/*     */       }
/*     */       
/* 173 */       if (itemstack1.field_77994_a == itemstack.field_77994_a)
/*     */       {
/* 175 */         return null;
/*     */       }
/*     */       
/* 178 */       slot.func_82870_a(par1EntityPlayer, itemstack1);
/*     */     }
/*     */     
/* 181 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerThaumatorium.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */