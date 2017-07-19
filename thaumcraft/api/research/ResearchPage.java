/*     */ package thaumcraft.api.research;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.api.crafting.IArcaneRecipe;
/*     */ import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ 
/*     */ public class ResearchPage
/*     */ {
/*     */   public static enum PageType
/*     */   {
/*  19 */     TEXT, 
/*  20 */     TEXT_CONCEALED, 
/*  21 */     IMAGE, 
/*  22 */     CRUCIBLE_CRAFTING, 
/*  23 */     ARCANE_CRAFTING, 
/*  24 */     ASPECTS, 
/*  25 */     NORMAL_CRAFTING, 
/*  26 */     INFUSION_CRAFTING, 
/*  27 */     COMPOUND_CRAFTING, 
/*  28 */     INFUSION_ENCHANTMENT, 
/*  29 */     SMELTING;
/*     */     
/*     */     private PageType() {} }
/*  32 */   public PageType type = PageType.TEXT;
/*     */   
/*  34 */   public String text = null;
/*  35 */   public String research = null;
/*  36 */   public ResourceLocation image = null;
/*  37 */   public AspectList aspects = null;
/*  38 */   public Object recipe = null;
/*  39 */   public ItemStack recipeOutput = null;
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(String text)
/*     */   {
/*  45 */     this.type = PageType.TEXT;
/*  46 */     this.text = text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ResearchPage(String research, String text)
/*     */   {
/*  54 */     this.type = PageType.TEXT_CONCEALED;
/*  55 */     this.research = research;
/*  56 */     this.text = text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(IRecipe recipe)
/*     */   {
/*  63 */     this.type = PageType.NORMAL_CRAFTING;
/*  64 */     this.recipe = recipe;
/*  65 */     this.recipeOutput = recipe.func_77571_b();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(IRecipe[] recipe)
/*     */   {
/*  72 */     this.type = PageType.NORMAL_CRAFTING;
/*  73 */     this.recipe = recipe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(IArcaneRecipe[] recipe)
/*     */   {
/*  80 */     this.type = PageType.ARCANE_CRAFTING;
/*  81 */     this.recipe = recipe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(CrucibleRecipe[] recipe)
/*     */   {
/*  88 */     this.type = PageType.CRUCIBLE_CRAFTING;
/*  89 */     this.recipe = recipe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(InfusionRecipe[] recipe)
/*     */   {
/*  96 */     this.type = PageType.INFUSION_CRAFTING;
/*  97 */     this.recipe = recipe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(List recipe)
/*     */   {
/* 104 */     this.type = PageType.COMPOUND_CRAFTING;
/* 105 */     this.recipe = recipe;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(IArcaneRecipe recipe)
/*     */   {
/* 112 */     this.type = PageType.ARCANE_CRAFTING;
/* 113 */     this.recipe = recipe;
/* 114 */     this.recipeOutput = recipe.getRecipeOutput();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(CrucibleRecipe recipe)
/*     */   {
/* 121 */     this.type = PageType.CRUCIBLE_CRAFTING;
/* 122 */     this.recipe = recipe;
/* 123 */     this.recipeOutput = recipe.getRecipeOutput();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(ItemStack input)
/*     */   {
/* 130 */     this.type = PageType.SMELTING;
/* 131 */     this.recipe = input;
/* 132 */     this.recipeOutput = FurnaceRecipes.func_77602_a().func_151395_a(input);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(InfusionRecipe recipe)
/*     */   {
/* 139 */     this.type = PageType.INFUSION_CRAFTING;
/* 140 */     this.recipe = recipe;
/* 141 */     if ((recipe.getRecipeOutput() instanceof ItemStack)) {
/* 142 */       this.recipeOutput = ((ItemStack)recipe.getRecipeOutput());
/*     */     } else {
/* 144 */       this.recipeOutput = recipe.getRecipeInput();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(InfusionEnchantmentRecipe recipe)
/*     */   {
/* 152 */     this.type = PageType.INFUSION_ENCHANTMENT;
/* 153 */     this.recipe = recipe;
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
/*     */   public ResearchPage(ResourceLocation image, String caption)
/*     */   {
/* 166 */     this.type = PageType.IMAGE;
/* 167 */     this.image = image;
/* 168 */     this.text = caption;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchPage(AspectList as)
/*     */   {
/* 175 */     this.type = PageType.ASPECTS;
/* 176 */     this.aspects = as;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTranslatedText()
/*     */   {
/* 184 */     String ret = "";
/* 185 */     if (this.text != null) {
/* 186 */       ret = StatCollector.func_74838_a(this.text);
/* 187 */       if (ret.isEmpty()) ret = this.text;
/*     */     }
/* 189 */     return ret;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/research/ResearchPage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */