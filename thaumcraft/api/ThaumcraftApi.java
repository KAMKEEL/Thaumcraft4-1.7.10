/*     */ package thaumcraft.api;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.common.util.EnumHelper;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.api.crafting.ShapedArcaneRecipe;
/*     */ import thaumcraft.api.crafting.ShapelessArcaneRecipe;
/*     */ import thaumcraft.api.internal.DummyInternalMethodHandler;
/*     */ import thaumcraft.api.internal.IInternalMethodHandler;
/*     */ import thaumcraft.api.internal.WeightedRandomLoot;
/*     */ import thaumcraft.api.research.IScanEventHandler;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategoryList;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.api.research.ResearchPage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThaumcraftApi
/*     */ {
/*  45 */   public static Item.ToolMaterial toolMatThaumium = EnumHelper.addToolMaterial("THAUMIUM", 3, 400, 7.0F, 2.0F, 22);
/*  46 */   public static Item.ToolMaterial toolMatVoid = EnumHelper.addToolMaterial("VOID", 4, 150, 8.0F, 3.0F, 10);
/*  47 */   public static Item.ToolMaterial toolMatElemental = EnumHelper.addToolMaterial("THAUMIUM_ELEMENTAL", 3, 1500, 10.0F, 3.0F, 18);
/*  48 */   public static ItemArmor.ArmorMaterial armorMatThaumium = EnumHelper.addArmorMaterial("THAUMIUM", 25, new int[] { 2, 6, 5, 2 }, 25);
/*  49 */   public static ItemArmor.ArmorMaterial armorMatSpecial = EnumHelper.addArmorMaterial("SPECIAL", 25, new int[] { 1, 3, 2, 1 }, 25);
/*  50 */   public static ItemArmor.ArmorMaterial armorMatThaumiumFortress = EnumHelper.addArmorMaterial("FORTRESS", 40, new int[] { 3, 7, 6, 3 }, 25);
/*  51 */   public static ItemArmor.ArmorMaterial armorMatVoid = EnumHelper.addArmorMaterial("VOID", 10, new int[] { 3, 7, 6, 3 }, 10);
/*  52 */   public static ItemArmor.ArmorMaterial armorMatVoidFortress = EnumHelper.addArmorMaterial("VOIDFORTRESS", 18, new int[] { 4, 8, 7, 4 }, 10);
/*     */   
/*     */ 
/*     */   public static int enchantFrugal;
/*     */   
/*     */ 
/*     */   public static int enchantPotency;
/*     */   
/*     */   public static int enchantWandFortune;
/*     */   
/*     */   public static int enchantHaste;
/*     */   
/*     */   public static int enchantRepair;
/*     */   
/*  66 */   public static ArrayList<Block> portableHoleBlackList = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*  70 */   public static IInternalMethodHandler internalMethods = new DummyInternalMethodHandler();
/*     */   
/*     */ 
/*  73 */   public static ArrayList<IScanEventHandler> scanEventhandlers = new ArrayList();
/*  74 */   public static ArrayList<EntityTags> scanEntities = new ArrayList();
/*     */   
/*     */   public static class EntityTagsNBT {
/*  77 */     public EntityTagsNBT(String name, Object value) { this.name = name;
/*  78 */       this.value = value; }
/*     */     
/*     */     public String name;
/*     */     public Object value; }
/*     */   
/*     */   public static class EntityTags { public String entityName;
/*     */     
/*  85 */     public EntityTags(String entityName, AspectList aspects, ThaumcraftApi.EntityTagsNBT... nbts) { this.entityName = entityName;
/*  86 */       this.nbts = nbts;
/*  87 */       this.aspects = aspects;
/*     */     }
/*     */     
/*     */ 
/*     */     public ThaumcraftApi.EntityTagsNBT[] nbts;
/*     */     
/*     */     public AspectList aspects;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void registerScanEventhandler(IScanEventHandler scanEventHandler)
/*     */   {
/*  99 */     scanEventhandlers.add(scanEventHandler);
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
/*     */ 
/*     */   public static void registerEntityTag(String entityName, AspectList aspects, EntityTagsNBT... nbt)
/*     */   {
/* 113 */     scanEntities.add(new EntityTags(entityName, aspects, nbt));
/*     */   }
/*     */   
/*     */ 
/* 117 */   private static ArrayList craftingRecipes = new ArrayList();
/* 118 */   private static HashMap<Object, ItemStack> smeltingBonus = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addSmeltingBonus(ItemStack in, ItemStack out)
/*     */   {
/* 127 */     smeltingBonus.put(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(in.func_77960_j()) }), new ItemStack(out.func_77973_b(), 0, out.func_77960_j()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addSmeltingBonus(String in, ItemStack out)
/*     */   {
/* 139 */     smeltingBonus.put(in, new ItemStack(out.func_77973_b(), 0, out.func_77960_j()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ItemStack getSmeltingBonus(ItemStack in)
/*     */   {
/* 148 */     ItemStack out = (ItemStack)smeltingBonus.get(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(in.func_77960_j()) }));
/* 149 */     if (out == null) {
/* 150 */       out = (ItemStack)smeltingBonus.get(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(32767) }));
/*     */     }
/* 152 */     if (out == null) {
/* 153 */       String od = OreDictionary.getOreName(OreDictionary.getOreID(in));
/* 154 */       out = (ItemStack)smeltingBonus.get(od);
/*     */     }
/* 156 */     return out;
/*     */   }
/*     */   
/*     */   public static List getCraftingRecipes() {
/* 160 */     return craftingRecipes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShapedArcaneRecipe addArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object... recipe)
/*     */   {
/* 171 */     ShapedArcaneRecipe r = new ShapedArcaneRecipe(research, result, aspects, recipe);
/* 172 */     craftingRecipes.add(r);
/* 173 */     return r;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShapelessArcaneRecipe addShapelessArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object... recipe)
/*     */   {
/* 184 */     ShapelessArcaneRecipe r = new ShapelessArcaneRecipe(research, result, aspects, recipe);
/* 185 */     craftingRecipes.add(r);
/* 186 */     return r;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static InfusionRecipe addInfusionCraftingRecipe(String research, Object result, int instability, AspectList aspects, ItemStack input, ItemStack[] recipe)
/*     */   {
/* 202 */     if ((!(result instanceof ItemStack)) && (!(result instanceof Object[]))) return null;
/* 203 */     InfusionRecipe r = new InfusionRecipe(research, result, instability, aspects, input, recipe);
/* 204 */     craftingRecipes.add(r);
/* 205 */     return r;
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
/*     */ 
/*     */ 
/*     */   public static InfusionEnchantmentRecipe addInfusionEnchantmentRecipe(String research, Enchantment enchantment, int instability, AspectList aspects, ItemStack[] recipe)
/*     */   {
/* 220 */     InfusionEnchantmentRecipe r = new InfusionEnchantmentRecipe(research, enchantment, instability, aspects, recipe);
/* 221 */     craftingRecipes.add(r);
/* 222 */     return r;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static InfusionRecipe getInfusionRecipe(ItemStack res)
/*     */   {
/* 230 */     for (Object r : ) {
/* 231 */       if (((r instanceof InfusionRecipe)) && 
/* 232 */         ((((InfusionRecipe)r).getRecipeOutput() instanceof ItemStack)) && 
/* 233 */         (((ItemStack)((InfusionRecipe)r).getRecipeOutput()).func_77969_a(res))) {
/* 234 */         return (InfusionRecipe)r;
/*     */       }
/*     */     }
/*     */     
/* 238 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static CrucibleRecipe addCrucibleRecipe(String key, ItemStack result, Object catalyst, AspectList tags)
/*     */   {
/* 250 */     CrucibleRecipe rc = new CrucibleRecipe(key, result, catalyst, tags);
/* 251 */     getCraftingRecipes().add(rc);
/* 252 */     return rc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static CrucibleRecipe getCrucibleRecipe(ItemStack stack)
/*     */   {
/* 261 */     for (Object r : ) {
/* 262 */       if (((r instanceof CrucibleRecipe)) && 
/* 263 */         (((CrucibleRecipe)r).getRecipeOutput().func_77969_a(stack))) {
/* 264 */         return (CrucibleRecipe)r;
/*     */       }
/*     */     }
/* 267 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static CrucibleRecipe getCrucibleRecipeFromHash(int hash)
/*     */   {
/* 275 */     for (Object r : ) {
/* 276 */       if (((r instanceof CrucibleRecipe)) && 
/* 277 */         (((CrucibleRecipe)r).hash == hash)) {
/* 278 */         return (CrucibleRecipe)r;
/*     */       }
/*     */     }
/* 281 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 289 */   private static HashMap<int[], Object[]> keyCache = new HashMap();
/*     */   
/*     */   public static Object[] getCraftingRecipeKey(EntityPlayer player, ItemStack stack) {
/* 292 */     int[] key = { Item.func_150891_b(stack.func_77973_b()), stack.func_77960_j() };
/* 293 */     if (keyCache.containsKey(key)) {
/* 294 */       if (keyCache.get(key) == null) return null;
/* 295 */       if (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), (String)((Object[])keyCache.get(key))[0])) {
/* 296 */         return (Object[])keyCache.get(key);
/*     */       }
/* 298 */       return null;
/*     */     }
/* 300 */     for (ResearchCategoryList rcl : ResearchCategories.researchCategories.values()) {
/* 301 */       for (ResearchItem ri : rcl.research.values()) {
/* 302 */         if (ri.getPages() != null)
/* 303 */           for (int a = 0; a < ri.getPages().length; a++) {
/* 304 */             ResearchPage page = ri.getPages()[a];
/* 305 */             if ((page.recipe != null) && ((page.recipe instanceof CrucibleRecipe[]))) {
/* 306 */               CrucibleRecipe[] crs = (CrucibleRecipe[])page.recipe;
/* 307 */               for (CrucibleRecipe cr : crs) {
/* 308 */                 if (cr.getRecipeOutput().func_77969_a(stack)) {
/* 309 */                   keyCache.put(key, new Object[] { ri.key, Integer.valueOf(a) });
/* 310 */                   if (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), ri.key)) {
/* 311 */                     return new Object[] { ri.key, Integer.valueOf(a) };
/*     */                   }
/*     */                 }
/*     */               }
/* 315 */             } else if ((page.recipeOutput != null) && (stack != null) && (page.recipeOutput.func_77969_a(stack))) {
/* 316 */               keyCache.put(key, new Object[] { ri.key, Integer.valueOf(a) });
/* 317 */               if (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), ri.key)) {
/* 318 */                 return new Object[] { ri.key, Integer.valueOf(a) };
/*     */               }
/* 320 */               return null;
/*     */             }
/*     */           }
/*     */       }
/*     */     }
/* 325 */     keyCache.put(key, null);
/* 326 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 331 */   public static ConcurrentHashMap<List, AspectList> objectTags = new ConcurrentHashMap();
/* 332 */   public static ConcurrentHashMap<List, int[]> groupedObjectTags = new ConcurrentHashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean exists(Item item, int meta)
/*     */   {
/* 341 */     AspectList tmp = (AspectList)objectTags.get(Arrays.asList(new Object[] { item, Integer.valueOf(meta) }));
/* 342 */     if (tmp == null) {
/* 343 */       tmp = (AspectList)objectTags.get(Arrays.asList(new Object[] { item, Integer.valueOf(32767) }));
/* 344 */       if ((meta == 32767) && (tmp == null)) {
/* 345 */         int index = 0;
/*     */         do {
/* 347 */           tmp = (AspectList)objectTags.get(Arrays.asList(new Object[] { item, Integer.valueOf(index) }));
/* 348 */           index++;
/* 349 */         } while ((index < 16) && (tmp == null));
/*     */       }
/* 351 */       if (tmp == null) { return false;
/*     */       }
/*     */     }
/* 354 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerObjectTag(ItemStack item, AspectList aspects)
/*     */   {
/* 364 */     if (aspects == null) aspects = new AspectList();
/*     */     try {
/* 366 */       objectTags.put(Arrays.asList(new Object[] { item.func_77973_b(), Integer.valueOf(item.func_77960_j()) }), aspects);
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerObjectTag(ItemStack item, int[] meta, AspectList aspects)
/*     */   {
/* 379 */     if (aspects == null) aspects = new AspectList();
/*     */     try {
/* 381 */       objectTags.put(Arrays.asList(new Object[] { item.func_77973_b(), Integer.valueOf(meta[0]) }), aspects);
/* 382 */       for (int m : meta) {
/* 383 */         groupedObjectTags.put(Arrays.asList(new Object[] { item.func_77973_b(), Integer.valueOf(m) }), meta);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerObjectTag(String oreDict, AspectList aspects)
/*     */   {
/* 395 */     if (aspects == null) aspects = new AspectList();
/* 396 */     ArrayList<ItemStack> ores = OreDictionary.getOres(oreDict);
/* 397 */     if ((ores != null) && (ores.size() > 0)) {
/* 398 */       for (ItemStack ore : ores) {
/*     */         try {
/* 400 */           objectTags.put(Arrays.asList(new Object[] { ore.func_77973_b(), Integer.valueOf(ore.func_77960_j()) }), aspects);
/*     */         }
/*     */         catch (Exception e) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerComplexObjectTag(ItemStack item, AspectList aspects)
/*     */   {
/* 416 */     if (!exists(item.func_77973_b(), item.func_77960_j())) {
/* 417 */       AspectList tmp = ThaumcraftApiHelper.generateTags(item.func_77973_b(), item.func_77960_j());
/* 418 */       if ((tmp != null) && (tmp.size() > 0)) {
/* 419 */         for (Aspect tag : tmp.getAspects()) {
/* 420 */           aspects.add(tag, tmp.getAmount(tag));
/*     */         }
/*     */       }
/* 423 */       registerObjectTag(item, aspects);
/*     */     } else {
/* 425 */       AspectList tmp = ThaumcraftApiHelper.getObjectAspects(item);
/* 426 */       for (Aspect tag : aspects.getAspects()) {
/* 427 */         tmp.merge(tag, tmp.getAmount(tag));
/*     */       }
/* 429 */       registerObjectTag(item, tmp);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 434 */   private static HashMap<Object, Integer> warpMap = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addWarpToItem(ItemStack craftresult, int amount)
/*     */   {
/* 443 */     warpMap.put(Arrays.asList(new Object[] { craftresult.func_77973_b(), Integer.valueOf(craftresult.func_77960_j()) }), Integer.valueOf(amount));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addWarpToResearch(String research, int amount)
/*     */   {
/* 452 */     warpMap.put(research, Integer.valueOf(amount));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int getWarp(Object in)
/*     */   {
/* 461 */     if (in == null) return 0;
/* 462 */     if ((in instanceof ItemStack)) if (warpMap.containsKey(Arrays.asList(new Object[] { ((ItemStack)in).func_77973_b(), Integer.valueOf(((ItemStack)in).func_77960_j()) }))) {
/* 463 */         return ((Integer)warpMap.get(Arrays.asList(new Object[] { ((ItemStack)in).func_77973_b(), Integer.valueOf(((ItemStack)in).func_77960_j()) }))).intValue();
/*     */       }
/* 465 */     if (((in instanceof String)) && (warpMap.containsKey((String)in))) {
/* 466 */       return ((Integer)warpMap.get((String)in)).intValue();
/*     */     }
/* 468 */     return 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addLootBagItem(ItemStack item, int weight, int... bagTypes)
/*     */   {
/* 484 */     if ((bagTypes == null) || (bagTypes.length == 0)) {
/* 485 */       WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight));
/*     */     } else {
/* 487 */       for (int rarity : bagTypes) {
/* 488 */         switch (rarity) {
/* 489 */         case 0:  WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight)); break;
/* 490 */         case 1:  WeightedRandomLoot.lootBagUncommon.add(new WeightedRandomLoot(item, weight)); break;
/* 491 */         case 2:  WeightedRandomLoot.lootBagRare.add(new WeightedRandomLoot(item, weight));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/ThaumcraftApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */