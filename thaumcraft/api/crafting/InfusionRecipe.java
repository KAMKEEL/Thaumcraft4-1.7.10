/*     */ package thaumcraft.api.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ 
/*     */ 
/*     */ public class InfusionRecipe
/*     */ {
/*     */   protected AspectList aspects;
/*     */   protected String research;
/*     */   private ItemStack[] components;
/*     */   private ItemStack recipeInput;
/*     */   protected Object recipeOutput;
/*     */   protected int instability;
/*     */   
/*     */   public InfusionRecipe(String research, Object output, int inst, AspectList aspects2, ItemStack input, ItemStack[] recipe)
/*     */   {
/*  23 */     this.research = research;
/*  24 */     this.recipeOutput = output;
/*  25 */     this.recipeInput = input;
/*  26 */     this.aspects = aspects2;
/*  27 */     this.components = recipe;
/*  28 */     this.instability = inst;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player)
/*     */   {
/*  36 */     if (getRecipeInput() == null) { return false;
/*     */     }
/*  38 */     if ((this.research.length() > 0) && (!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), this.research))) {
/*  39 */       return false;
/*     */     }
/*     */     
/*  42 */     ItemStack i2 = central.func_77946_l();
/*  43 */     if (getRecipeInput().func_77960_j() == 32767) {
/*  44 */       i2.func_77964_b(32767);
/*     */     }
/*     */     
/*  47 */     if (!areItemStacksEqual(i2, getRecipeInput(), true)) { return false;
/*     */     }
/*  49 */     ArrayList<ItemStack> ii = new ArrayList();
/*  50 */     for (ItemStack is : input) {
/*  51 */       ii.add(is.func_77946_l());
/*     */     }
/*     */     
/*  54 */     for (ItemStack comp : getComponents()) {
/*  55 */       boolean b = false;
/*  56 */       for (int a = 0; a < ii.size(); a++) {
/*  57 */         i2 = ((ItemStack)ii.get(a)).func_77946_l();
/*  58 */         if (comp.func_77960_j() == 32767) {
/*  59 */           i2.func_77964_b(32767);
/*     */         }
/*  61 */         if (areItemStacksEqual(i2, comp, true)) {
/*  62 */           ii.remove(a);
/*  63 */           b = true;
/*  64 */           break;
/*     */         }
/*     */       }
/*  67 */       if (!b) return false;
/*     */     }
/*  69 */     return ii.size() == 0;
/*     */   }
/*     */   
/*     */   public static boolean areItemStacksEqual(ItemStack stack0, ItemStack stack1, boolean fuzzy)
/*     */   {
/*  74 */     if ((stack0 == null) && (stack1 != null)) return false;
/*  75 */     if ((stack0 != null) && (stack1 == null)) return false;
/*  76 */     if ((stack0 == null) && (stack1 == null)) { return true;
/*     */     }
/*     */     
/*  79 */     boolean t1 = ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(stack0, stack1);
/*  80 */     if (!t1) { return false;
/*     */     }
/*  82 */     if (fuzzy) {
/*  83 */       Integer od = Integer.valueOf(OreDictionary.getOreID(stack0));
/*  84 */       if (od.intValue() != -1) {
/*  85 */         ItemStack[] ores = (ItemStack[])OreDictionary.getOres(od).toArray(new ItemStack[0]);
/*  86 */         if (ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { stack1 }, ores)) {
/*  87 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  92 */     boolean damage = (stack0.func_77960_j() == stack1.func_77960_j()) || (stack1.func_77960_j() == 32767);
/*     */     
/*     */ 
/*  95 */     return stack0.func_77973_b() == stack1.func_77973_b();
/*     */   }
/*     */   
/*     */   public Object getRecipeOutput() {
/*  99 */     return getRecipeOutput(getRecipeInput());
/*     */   }
/*     */   
/*     */   public AspectList getAspects() {
/* 103 */     return getAspects(getRecipeInput());
/*     */   }
/*     */   
/*     */   public int getInstability() {
/* 107 */     return getInstability(getRecipeInput());
/*     */   }
/*     */   
/*     */   public String getResearch() {
/* 111 */     return this.research;
/*     */   }
/*     */   
/*     */   public ItemStack getRecipeInput() {
/* 115 */     return this.recipeInput;
/*     */   }
/*     */   
/*     */   public ItemStack[] getComponents() {
/* 119 */     return this.components;
/*     */   }
/*     */   
/*     */   public Object getRecipeOutput(ItemStack input) {
/* 123 */     return this.recipeOutput;
/*     */   }
/*     */   
/*     */   public AspectList getAspects(ItemStack input) {
/* 127 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public int getInstability(ItemStack input) {
/* 131 */     return this.instability;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/crafting/InfusionRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */