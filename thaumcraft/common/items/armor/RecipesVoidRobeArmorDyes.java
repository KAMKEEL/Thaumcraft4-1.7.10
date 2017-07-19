/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipesVoidRobeArmorDyes
/*     */   implements IRecipe
/*     */ {
/*     */   public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World)
/*     */   {
/*  21 */     ItemStack itemstack = null;
/*  22 */     ArrayList arraylist = new ArrayList();
/*     */     
/*  24 */     for (int i = 0; i < par1InventoryCrafting.func_70302_i_(); i++)
/*     */     {
/*  26 */       ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(i);
/*     */       
/*  28 */       if (itemstack1 != null)
/*     */       {
/*  30 */         if ((itemstack1.func_77973_b() instanceof ItemArmor))
/*     */         {
/*  32 */           ItemArmor itemarmor = (ItemArmor)itemstack1.func_77973_b();
/*     */           
/*  34 */           if ((!(itemarmor instanceof ItemVoidRobeArmor)) || (itemstack != null))
/*     */           {
/*  36 */             return false;
/*     */           }
/*     */           
/*  39 */           itemstack = itemstack1;
/*     */         }
/*     */         else
/*     */         {
/*  43 */           if (itemstack1.func_77973_b() != Items.field_151100_aR)
/*     */           {
/*  45 */             return false;
/*     */           }
/*     */           
/*  48 */           arraylist.add(itemstack1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  53 */     return (itemstack != null) && (!arraylist.isEmpty());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_77572_b(InventoryCrafting par1InventoryCrafting)
/*     */   {
/*  61 */     ItemStack itemstack = null;
/*  62 */     int[] aint = new int[3];
/*  63 */     int i = 0;
/*  64 */     int j = 0;
/*  65 */     ItemArmor itemarmor = null;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  72 */     for (int k = 0; k < par1InventoryCrafting.func_70302_i_(); k++)
/*     */     {
/*  74 */       ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(k);
/*     */       
/*  76 */       if (itemstack1 != null)
/*     */       {
/*  78 */         if ((itemstack1.func_77973_b() instanceof ItemArmor))
/*     */         {
/*  80 */           itemarmor = (ItemArmor)itemstack1.func_77973_b();
/*     */           
/*  82 */           if ((!(itemarmor instanceof ItemVoidRobeArmor)) || (itemstack != null))
/*     */           {
/*  84 */             return null;
/*     */           }
/*     */           
/*  87 */           itemstack = itemstack1.func_77946_l();
/*  88 */           itemstack.field_77994_a = 1;
/*     */           
/*  90 */           if (itemarmor.func_82816_b_(itemstack1))
/*     */           {
/*  92 */             int l = itemarmor.func_82814_b(itemstack);
/*  93 */             float f = (l >> 16 & 0xFF) / 255.0F;
/*  94 */             float f1 = (l >> 8 & 0xFF) / 255.0F;
/*  95 */             float f2 = (l & 0xFF) / 255.0F;
/*  96 */             i = (int)(i + Math.max(f, Math.max(f1, f2)) * 255.0F);
/*  97 */             aint[0] = ((int)(aint[0] + f * 255.0F));
/*  98 */             aint[1] = ((int)(aint[1] + f1 * 255.0F));
/*  99 */             aint[2] = ((int)(aint[2] + f2 * 255.0F));
/* 100 */             j++;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 105 */           if (itemstack1.func_77973_b() != Items.field_151100_aR)
/*     */           {
/* 107 */             return null;
/*     */           }
/*     */           
/* 110 */           float[] afloat = net.minecraft.entity.passive.EntitySheep.field_70898_d[net.minecraft.block.BlockColored.func_150032_b(itemstack1.func_77960_j())];
/* 111 */           int j1 = (int)(afloat[0] * 255.0F);
/* 112 */           int k1 = (int)(afloat[1] * 255.0F);
/* 113 */           int i1 = (int)(afloat[2] * 255.0F);
/* 114 */           i += Math.max(j1, Math.max(k1, i1));
/* 115 */           aint[0] += j1;
/* 116 */           aint[1] += k1;
/* 117 */           aint[2] += i1;
/* 118 */           j++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 123 */     if (itemarmor == null)
/*     */     {
/* 125 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 129 */     k = aint[0] / j;
/* 130 */     int l1 = aint[1] / j;
/* 131 */     int l = aint[2] / j;
/* 132 */     float f = i / j;
/* 133 */     float f1 = Math.max(k, Math.max(l1, l));
/* 134 */     k = (int)(k * f / f1);
/* 135 */     l1 = (int)(l1 * f / f1);
/* 136 */     l = (int)(l * f / f1);
/* 137 */     int i1 = (k << 8) + l1;
/* 138 */     i1 = (i1 << 8) + l;
/* 139 */     itemarmor.func_82813_b(itemstack, i1);
/* 140 */     return itemstack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_77570_a()
/*     */   {
/* 149 */     return 10;
/*     */   }
/*     */   
/*     */   public ItemStack func_77571_b()
/*     */   {
/* 154 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/RecipesVoidRobeArmorDyes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */