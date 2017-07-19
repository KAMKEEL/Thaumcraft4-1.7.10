/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.tiles.TileAlchemyFurnace;
/*     */ 
/*     */ public class ContainerAlchemyFurnace extends Container
/*     */ {
/*     */   private TileAlchemyFurnace furnace;
/*     */   private int lastCookTime;
/*     */   private int lastBurnTime;
/*     */   private int lastItemBurnTime;
/*     */   private int lastVis;
/*     */   private int lastSmelt;
/*     */   
/*     */   public ContainerAlchemyFurnace(InventoryPlayer par1InventoryPlayer, TileAlchemyFurnace tileEntity)
/*     */   {
/*  27 */     this.furnace = tileEntity;
/*  28 */     func_75146_a(new SlotLimitedHasAspects(tileEntity, 0, 80, 8));
/*  29 */     func_75146_a(new Slot(tileEntity, 1, 80, 48));
/*     */     
/*     */ 
/*  32 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  34 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  36 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*  40 */     for (i = 0; i < 9; i++)
/*     */     {
/*  42 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_75132_a(ICrafting par1ICrafting)
/*     */   {
/*  48 */     super.func_75132_a(par1ICrafting);
/*  49 */     par1ICrafting.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/*  50 */     par1ICrafting.func_71112_a(this, 1, this.furnace.furnaceBurnTime);
/*  51 */     par1ICrafting.func_71112_a(this, 2, this.furnace.currentItemBurnTime);
/*  52 */     par1ICrafting.func_71112_a(this, 3, this.furnace.vis);
/*  53 */     par1ICrafting.func_71112_a(this, 4, this.furnace.smeltTime);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75142_b()
/*     */   {
/*  61 */     super.func_75142_b();
/*     */     
/*  63 */     for (int i = 0; i < this.field_75149_d.size(); i++)
/*     */     {
/*  65 */       ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
/*     */       
/*  67 */       if (this.lastCookTime != this.furnace.furnaceCookTime)
/*     */       {
/*  69 */         icrafting.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/*     */       }
/*     */       
/*  72 */       if (this.lastBurnTime != this.furnace.furnaceBurnTime)
/*     */       {
/*  74 */         icrafting.func_71112_a(this, 1, this.furnace.furnaceBurnTime);
/*     */       }
/*     */       
/*  77 */       if (this.lastItemBurnTime != this.furnace.currentItemBurnTime)
/*     */       {
/*  79 */         icrafting.func_71112_a(this, 2, this.furnace.currentItemBurnTime);
/*     */       }
/*     */       
/*  82 */       if (this.lastVis != this.furnace.vis)
/*     */       {
/*  84 */         icrafting.func_71112_a(this, 3, this.furnace.vis);
/*     */       }
/*     */       
/*  87 */       if (this.lastSmelt != this.furnace.smeltTime)
/*     */       {
/*  89 */         icrafting.func_71112_a(this, 4, this.furnace.smeltTime);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  94 */     this.lastCookTime = this.furnace.furnaceCookTime;
/*  95 */     this.lastBurnTime = this.furnace.furnaceBurnTime;
/*  96 */     this.lastItemBurnTime = this.furnace.currentItemBurnTime;
/*  97 */     this.lastVis = this.furnace.vis;
/*  98 */     this.lastSmelt = this.furnace.smeltTime;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2)
/*     */   {
/* 104 */     if (par1 == 0)
/*     */     {
/* 106 */       this.furnace.furnaceCookTime = par2;
/*     */     }
/*     */     
/* 109 */     if (par1 == 1)
/*     */     {
/* 111 */       this.furnace.furnaceBurnTime = par2;
/*     */     }
/*     */     
/* 114 */     if (par1 == 2)
/*     */     {
/* 116 */       this.furnace.currentItemBurnTime = par2;
/*     */     }
/*     */     
/* 119 */     if (par1 == 3)
/*     */     {
/* 121 */       this.furnace.vis = par2;
/*     */     }
/*     */     
/* 124 */     if (par1 == 4)
/*     */     {
/* 126 */       this.furnace.smeltTime = par2;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 132 */     return this.furnace.func_70300_a(par1EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/* 140 */     ItemStack itemstack = null;
/* 141 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/* 143 */     if ((slot != null) && (slot.func_75216_d()))
/*     */     {
/* 145 */       ItemStack itemstack1 = slot.func_75211_c();
/* 146 */       itemstack = itemstack1.func_77946_l();
/*     */       
/* 148 */       if ((par2 != 1) && (par2 != 0))
/*     */       {
/* 150 */         AspectList al = ThaumcraftCraftingManager.getObjectTags(itemstack1);
/* 151 */         al = ThaumcraftCraftingManager.getBonusTags(itemstack1, al);
/*     */         
/* 153 */         if (TileAlchemyFurnace.isItemFuel(itemstack1))
/*     */         {
/* 155 */           if (!func_75135_a(itemstack1, 1, 2, false))
/*     */           {
/*     */ 
/* 158 */             if (!func_75135_a(itemstack1, 0, 1, false))
/*     */             {
/* 160 */               return null;
/*     */             }
/*     */           }
/*     */         }
/* 164 */         else if ((al != null) && (al.size() > 0))
/*     */         {
/* 166 */           if (!func_75135_a(itemstack1, 0, 1, false))
/*     */           {
/* 168 */             return null;
/*     */           }
/*     */         }
/* 171 */         else if ((par2 >= 2) && (par2 < 29))
/*     */         {
/* 173 */           if (!func_75135_a(itemstack1, 29, 38, false))
/*     */           {
/* 175 */             return null;
/*     */           }
/*     */         }
/* 178 */         else if ((par2 >= 29) && (par2 < 38) && (!func_75135_a(itemstack1, 2, 29, false)))
/*     */         {
/* 180 */           return null;
/*     */         }
/*     */       }
/* 183 */       else if (!func_75135_a(itemstack1, 2, 38, false))
/*     */       {
/* 185 */         return null;
/*     */       }
/*     */       
/* 188 */       if (itemstack1.field_77994_a == 0)
/*     */       {
/* 190 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 194 */         slot.func_75218_e();
/*     */       }
/*     */       
/* 197 */       if (itemstack1.field_77994_a == itemstack.field_77994_a)
/*     */       {
/* 199 */         return null;
/*     */       }
/*     */       
/* 202 */       slot.func_82870_a(par1EntityPlayer, itemstack1);
/*     */     }
/*     */     
/* 205 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerAlchemyFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */