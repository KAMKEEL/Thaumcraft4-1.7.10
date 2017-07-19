/*     */ package thaumcraft.api.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ 
/*     */ public class InfusionEnchantmentRecipe
/*     */ {
/*     */   public AspectList aspects;
/*     */   public String research;
/*     */   public ItemStack[] components;
/*     */   public Enchantment enchantment;
/*     */   public int recipeXP;
/*     */   public int instability;
/*     */   
/*     */   public InfusionEnchantmentRecipe(String research, Enchantment input, int inst, AspectList aspects2, ItemStack[] recipe)
/*     */   {
/*  28 */     this.research = research;
/*  29 */     this.enchantment = input;
/*  30 */     this.aspects = aspects2;
/*  31 */     this.components = recipe;
/*  32 */     this.instability = inst;
/*  33 */     this.recipeXP = Math.max(1, input.func_77321_a(1) / 3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player)
/*     */   {
/*  41 */     if ((this.research.length() > 0) && (!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), this.research))) {
/*  42 */       return false;
/*     */     }
/*     */     
/*  45 */     if ((!this.enchantment.func_92089_a(central)) || (!central.func_77973_b().func_77616_k(central))) {
/*  46 */       return false;
/*     */     }
/*     */     
/*  49 */     Map map1 = EnchantmentHelper.func_82781_a(central);
/*  50 */     Iterator iterator = map1.keySet().iterator();
/*  51 */     while (iterator.hasNext())
/*     */     {
/*  53 */       int j1 = ((Integer)iterator.next()).intValue();
/*  54 */       Enchantment ench = Enchantment.field_77331_b[j1];
/*  55 */       if ((j1 == this.enchantment.field_77352_x) && (EnchantmentHelper.func_77506_a(j1, central) >= ench.func_77325_b()))
/*     */       {
/*  57 */         return false; }
/*  58 */       if ((this.enchantment.field_77352_x != ench.field_77352_x) && ((!this.enchantment.func_77326_a(ench)) || (!ench.func_77326_a(this.enchantment))))
/*     */       {
/*     */ 
/*  61 */         return false;
/*     */       }
/*     */     }
/*     */     
/*  65 */     ItemStack i2 = null;
/*     */     
/*  67 */     ArrayList<ItemStack> ii = new ArrayList();
/*  68 */     for (ItemStack is : input) {
/*  69 */       ii.add(is.func_77946_l());
/*     */     }
/*     */     
/*  72 */     for (ItemStack comp : this.components) {
/*  73 */       boolean b = false;
/*  74 */       for (int a = 0; a < ii.size(); a++) {
/*  75 */         i2 = ((ItemStack)ii.get(a)).func_77946_l();
/*  76 */         if (comp.func_77960_j() == 32767) {
/*  77 */           i2.func_77964_b(32767);
/*     */         }
/*  79 */         if (areItemStacksEqual(i2, comp, true)) {
/*  80 */           ii.remove(a);
/*  81 */           b = true;
/*  82 */           break;
/*     */         }
/*     */       }
/*  85 */       if (!b) { return false;
/*     */       }
/*     */     }
/*  88 */     return ii.size() == 0;
/*     */   }
/*     */   
/*     */   protected boolean areItemStacksEqual(ItemStack stack0, ItemStack stack1, boolean fuzzy)
/*     */   {
/*  93 */     if ((stack0 == null) && (stack1 != null)) return false;
/*  94 */     if ((stack0 != null) && (stack1 == null)) return false;
/*  95 */     if ((stack0 == null) && (stack1 == null)) return true;
/*  96 */     boolean t1 = ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(stack0, stack1);
/*  97 */     if (!t1) return false;
/*  98 */     if (fuzzy) {
/*  99 */       Integer od = Integer.valueOf(OreDictionary.getOreID(stack0));
/* 100 */       if (od.intValue() != -1) {
/* 101 */         ItemStack[] ores = (ItemStack[])OreDictionary.getOres(od).toArray(new ItemStack[0]);
/* 102 */         if (ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { stack1 }, ores))
/* 103 */           return true;
/*     */       }
/*     */     }
/* 106 */     return stack0.func_77973_b() == stack1.func_77973_b();
/*     */   }
/*     */   
/*     */   public Enchantment getEnchantment()
/*     */   {
/* 111 */     return this.enchantment;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 116 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public String getResearch()
/*     */   {
/* 121 */     return this.research;
/*     */   }
/*     */   
/*     */   public int calcInstability(ItemStack recipeInput)
/*     */   {
/* 126 */     int i = 0;
/* 127 */     Map map1 = EnchantmentHelper.func_82781_a(recipeInput);
/* 128 */     Iterator iterator = map1.keySet().iterator();
/* 129 */     while (iterator.hasNext())
/*     */     {
/* 131 */       int j1 = ((Integer)iterator.next()).intValue();
/* 132 */       i += EnchantmentHelper.func_77506_a(j1, recipeInput);
/*     */     }
/* 134 */     return i / 2 + this.instability;
/*     */   }
/*     */   
/*     */   public int calcXP(ItemStack recipeInput) {
/* 138 */     return this.recipeXP * (1 + EnchantmentHelper.func_77506_a(this.enchantment.field_77352_x, recipeInput));
/*     */   }
/*     */   
/*     */   public float getEssentiaMod(ItemStack recipeInput) {
/* 142 */     float mod = EnchantmentHelper.func_77506_a(this.enchantment.field_77352_x, recipeInput);
/* 143 */     Map map1 = EnchantmentHelper.func_82781_a(recipeInput);
/* 144 */     Iterator iterator = map1.keySet().iterator();
/* 145 */     while (iterator.hasNext())
/*     */     {
/* 147 */       int j1 = ((Integer)iterator.next()).intValue();
/* 148 */       if (j1 != this.enchantment.field_77352_x)
/* 149 */         mod += EnchantmentHelper.func_77506_a(j1, recipeInput) * 0.1F;
/*     */     }
/* 151 */     return mod;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/crafting/InfusionEnchantmentRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */