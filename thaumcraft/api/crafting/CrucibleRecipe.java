/*    */ package thaumcraft.api.crafting;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.LinkedHashMap;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.oredict.OreDictionary;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ 
/*    */ 
/*    */ public class CrucibleRecipe
/*    */ {
/*    */   private ItemStack recipeOutput;
/*    */   public Object catalyst;
/*    */   public AspectList aspects;
/*    */   public String key;
/*    */   public int hash;
/*    */   
/*    */   public CrucibleRecipe(String researchKey, ItemStack result, Object cat, AspectList tags)
/*    */   {
/* 22 */     this.recipeOutput = result;
/* 23 */     this.aspects = tags;
/* 24 */     this.key = researchKey;
/* 25 */     this.catalyst = cat;
/* 26 */     if ((cat instanceof String)) {
/* 27 */       this.catalyst = OreDictionary.getOres((String)cat);
/*    */     }
/* 29 */     String hc = researchKey + result.toString();
/* 30 */     for (Aspect tag : tags.getAspects()) {
/* 31 */       hc = hc + tag.getTag() + tags.getAmount(tag);
/*    */     }
/* 33 */     if ((cat instanceof ItemStack)) {
/* 34 */       hc = hc + ((ItemStack)cat).toString();
/*    */     }
/* 36 */     else if (((cat instanceof ArrayList)) && (((ArrayList)this.catalyst).size() > 0)) {
/* 37 */       for (ItemStack is : (ArrayList)this.catalyst) {
/* 38 */         hc = hc + is.toString();
/*    */       }
/*    */     }
/*    */     
/* 42 */     this.hash = hc.hashCode();
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean matches(AspectList itags, ItemStack cat)
/*    */   {
/* 48 */     if (((this.catalyst instanceof ItemStack)) && (!ThaumcraftApiHelper.itemMatches((ItemStack)this.catalyst, cat, false)))
/*    */     {
/* 50 */       return false;
/*    */     }
/* 52 */     if (((this.catalyst instanceof ArrayList)) && (((ArrayList)this.catalyst).size() > 0)) {
/* 53 */       ItemStack[] ores = (ItemStack[])((ArrayList)this.catalyst).toArray(new ItemStack[0]);
/* 54 */       if (!ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { cat }, ores)) return false;
/*    */     }
/* 56 */     if (itags == null) return false;
/* 57 */     for (Aspect tag : this.aspects.getAspects()) {
/* 58 */       if (itags.getAmount(tag) < this.aspects.getAmount(tag)) return false;
/*    */     }
/* 60 */     return true;
/*    */   }
/*    */   
/*    */   public boolean catalystMatches(ItemStack cat) {
/* 64 */     if (((this.catalyst instanceof ItemStack)) && (ThaumcraftApiHelper.itemMatches((ItemStack)this.catalyst, cat, false))) {
/* 65 */       return true;
/*    */     }
/* 67 */     if (((this.catalyst instanceof ArrayList)) && (((ArrayList)this.catalyst).size() > 0)) {
/* 68 */       ItemStack[] ores = (ItemStack[])((ArrayList)this.catalyst).toArray(new ItemStack[0]);
/* 69 */       if (ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { cat }, ores)) return true;
/*    */     }
/* 71 */     return false;
/*    */   }
/*    */   
/*    */   public AspectList removeMatching(AspectList itags) {
/* 75 */     AspectList temptags = new AspectList();
/* 76 */     temptags.aspects.putAll(itags.aspects);
/*    */     
/* 78 */     for (Aspect tag : this.aspects.getAspects()) {
/* 79 */       temptags.remove(tag, this.aspects.getAmount(tag));
/*    */     }
/*    */     
/* 82 */     itags = temptags;
/* 83 */     return itags;
/*    */   }
/*    */   
/*    */   public ItemStack getRecipeOutput() {
/* 87 */     return this.recipeOutput;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/crafting/CrucibleRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */