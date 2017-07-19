/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemBow;
/*     */ import net.minecraft.item.ItemEnchantedBook;
/*     */ import net.minecraft.item.ItemHoe;
/*     */ import net.minecraft.item.ItemPickaxe;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemShears;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.item.crafting.ShapedRecipes;
/*     */ import net.minecraft.item.crafting.ShapelessRecipes;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraftforge.oredict.ShapedOreRecipe;
/*     */ import net.minecraftforge.oredict.ShapelessOreRecipe;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.api.crafting.IArcaneRecipe;
/*     */ import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.api.crafting.ShapedArcaneRecipe;
/*     */ import thaumcraft.api.crafting.ShapelessArcaneRecipe;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class ThaumcraftCraftingManager
/*     */ {
/*     */   public static ShapedRecipes createFakeRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj)
/*     */   {
/*  58 */     String var3 = "";
/*  59 */     int var4 = 0;
/*  60 */     int var5 = 0;
/*  61 */     int var6 = 0;
/*     */     
/*     */ 
/*  64 */     if ((par2ArrayOfObj[var4] instanceof String[]))
/*     */     {
/*  66 */       String[] var7 = (String[])(String[])par2ArrayOfObj[(var4++)];
/*  67 */       String[] var8 = var7;
/*  68 */       int var9 = var7.length;
/*     */       
/*  70 */       for (int var10 = 0; var10 < var9; var10++)
/*     */       {
/*  72 */         String var11 = var8[var10];
/*  73 */         var6++;
/*  74 */         var5 = var11.length();
/*  75 */         var3 = var3 + var11;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  80 */       while ((par2ArrayOfObj[var4] instanceof String))
/*     */       {
/*  82 */         String var13 = (String)par2ArrayOfObj[(var4++)];
/*  83 */         var6++;
/*  84 */         var5 = var13.length();
/*  85 */         var3 = var3 + var13;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  91 */     for (HashMap var14 = new HashMap(); var4 < par2ArrayOfObj.length; var4 += 2)
/*     */     {
/*  93 */       Character var16 = (Character)par2ArrayOfObj[var4];
/*  94 */       ItemStack var17 = null;
/*     */       
/*  96 */       if ((par2ArrayOfObj[(var4 + 1)] instanceof Item))
/*     */       {
/*  98 */         var17 = new ItemStack((Item)par2ArrayOfObj[(var4 + 1)]);
/*     */       }
/* 100 */       else if ((par2ArrayOfObj[(var4 + 1)] instanceof Block))
/*     */       {
/* 102 */         var17 = new ItemStack((Block)par2ArrayOfObj[(var4 + 1)]);
/*     */       }
/* 104 */       else if ((par2ArrayOfObj[(var4 + 1)] instanceof ItemStack))
/*     */       {
/* 106 */         var17 = (ItemStack)par2ArrayOfObj[(var4 + 1)];
/*     */       }
/*     */       
/* 109 */       var14.put(var16, var17);
/*     */     }
/*     */     
/* 112 */     ItemStack[] var15 = new ItemStack[var5 * var6];
/*     */     
/* 114 */     for (int var9 = 0; var9 < var5 * var6; var9++)
/*     */     {
/* 116 */       char var18 = var3.charAt(var9);
/*     */       
/* 118 */       if (var14.containsKey(Character.valueOf(var18)))
/*     */       {
/* 120 */         var15[var9] = ((ItemStack)var14.get(Character.valueOf(var18))).func_77946_l();
/*     */       }
/*     */       else
/*     */       {
/* 124 */         var15[var9] = null;
/*     */       }
/*     */     }
/*     */     
/* 128 */     return new ShapedRecipes(var5, var6, var15, par1ItemStack);
/*     */   }
/*     */   
/*     */   public static CrucibleRecipe findMatchingCrucibleRecipe(String username, AspectList aspects, ItemStack lastDrop) {
/* 132 */     int highest = 0;
/* 133 */     int index = -1;
/* 134 */     for (int a = 0; a < ThaumcraftApi.getCraftingRecipes().size(); a++) {
/* 135 */       if ((ThaumcraftApi.getCraftingRecipes().get(a) instanceof CrucibleRecipe)) {
/* 136 */         CrucibleRecipe recipe = (CrucibleRecipe)ThaumcraftApi.getCraftingRecipes().get(a);
/* 137 */         ItemStack temp = lastDrop.func_77946_l();
/* 138 */         temp.field_77994_a = 1;
/* 139 */         if ((thaumcraft.common.lib.research.ResearchManager.isResearchComplete(username, recipe.key)) && (recipe.matches(aspects, temp)))
/*     */         {
/* 141 */           int result = recipe.aspects.size();
/* 142 */           if (result > highest) {
/* 143 */             highest = result;
/* 144 */             index = a;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 150 */     if (index < 0) return null;
/* 151 */     AspectList output = new AspectList();
/*     */     
/* 153 */     return (CrucibleRecipe)ThaumcraftApi.getCraftingRecipes().get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ItemStack findMatchingArcaneRecipe(IInventory awb, EntityPlayer player)
/*     */   {
/* 161 */     int var2 = 0;
/* 162 */     ItemStack var3 = null;
/* 163 */     ItemStack var4 = null;
/*     */     
/* 165 */     for (int var5 = 0; var5 < 9; var5++)
/*     */     {
/* 167 */       ItemStack var6 = awb.func_70301_a(var5);
/*     */       
/* 169 */       if (var6 != null)
/*     */       {
/* 171 */         if (var2 == 0)
/*     */         {
/* 173 */           var3 = var6;
/*     */         }
/*     */         
/* 176 */         if (var2 == 1)
/*     */         {
/* 178 */           var4 = var6;
/*     */         }
/*     */         
/* 181 */         var2++;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 186 */     IArcaneRecipe var13 = null;
/* 187 */     for (Object var11 : ThaumcraftApi.getCraftingRecipes()) {
/* 188 */       if (((var11 instanceof IArcaneRecipe)) && (((IArcaneRecipe)var11).matches(awb, player.field_70170_p, player)))
/*     */       {
/* 190 */         var13 = (IArcaneRecipe)var11;
/* 191 */         break;
/*     */       }
/*     */     }
/*     */     
/* 195 */     return var13 == null ? null : var13.getCraftingResult(awb);
/*     */   }
/*     */   
/*     */ 
/*     */   public static AspectList findMatchingArcaneRecipeAspects(IInventory awb, EntityPlayer player)
/*     */   {
/* 201 */     int var2 = 0;
/* 202 */     ItemStack var3 = null;
/* 203 */     ItemStack var4 = null;
/*     */     
/* 205 */     for (int var5 = 0; var5 < 9; var5++)
/*     */     {
/* 207 */       ItemStack var6 = awb.func_70301_a(var5);
/*     */       
/* 209 */       if (var6 != null)
/*     */       {
/* 211 */         if (var2 == 0)
/*     */         {
/* 213 */           var3 = var6;
/*     */         }
/*     */         
/* 216 */         if (var2 == 1)
/*     */         {
/* 218 */           var4 = var6;
/*     */         }
/*     */         
/* 221 */         var2++;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 226 */     IArcaneRecipe var13 = null;
/* 227 */     for (Object var11 : ThaumcraftApi.getCraftingRecipes()) {
/* 228 */       if (((var11 instanceof IArcaneRecipe)) && (((IArcaneRecipe)var11).matches(awb, player.field_70170_p, player)))
/*     */       {
/* 230 */         var13 = (IArcaneRecipe)var11;
/*     */         
/* 232 */         break;
/*     */       }
/*     */     }
/*     */     
/* 236 */     return var13.getAspects() != null ? var13.getAspects() : var13 == null ? new AspectList() : var13.getAspects(awb);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static InfusionRecipe findMatchingInfusionRecipe(ArrayList<ItemStack> items, ItemStack input, EntityPlayer player)
/*     */   {
/* 243 */     InfusionRecipe var13 = null;
/* 244 */     for (Object var11 : ThaumcraftApi.getCraftingRecipes()) {
/* 245 */       if (((var11 instanceof InfusionRecipe)) && (((InfusionRecipe)var11).matches(items, input, player.field_70170_p, player)))
/*     */       {
/* 247 */         var13 = (InfusionRecipe)var11;
/* 248 */         break;
/*     */       }
/*     */     }
/*     */     
/* 252 */     return var13;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static InfusionEnchantmentRecipe findMatchingInfusionEnchantmentRecipe(ArrayList<ItemStack> items, ItemStack input, EntityPlayer player)
/*     */   {
/* 259 */     InfusionEnchantmentRecipe var13 = null;
/* 260 */     for (Object var11 : ThaumcraftApi.getCraftingRecipes()) {
/* 261 */       if (((var11 instanceof InfusionEnchantmentRecipe)) && (((InfusionEnchantmentRecipe)var11).matches(items, input, player.field_70170_p, player)))
/*     */       {
/* 263 */         var13 = (InfusionEnchantmentRecipe)var11;
/* 264 */         break;
/*     */       }
/*     */     }
/*     */     
/* 268 */     return var13;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static AspectList getObjectTags(ItemStack itemstack)
/*     */   {
/*     */     Item item;
/*     */     
/*     */ 
/*     */     int meta;
/*     */     
/*     */     try
/*     */     {
/* 282 */       item = itemstack.func_77973_b();
/* 283 */       meta = itemstack.func_77960_j();
/*     */     } catch (Exception e) {
/* 285 */       return null;
/*     */     }
/*     */     
/* 288 */     AspectList tmp = (AspectList)ThaumcraftApi.objectTags.get(Arrays.asList(new Object[] { item, Integer.valueOf(meta) }));
/*     */     
/* 290 */     if (tmp == null) {
/* 291 */       Collection<List> col = ThaumcraftApi.objectTags.keySet();
/* 292 */       for (List l : col) {
/* 293 */         if (((Item)l.get(0) == item) && ((l.get(1) instanceof int[]))) {
/* 294 */           int[] range = (int[])l.get(1);
/* 295 */           Arrays.sort(range);
/* 296 */           if (Arrays.binarySearch(range, meta) >= 0) {
/* 297 */             tmp = (AspectList)ThaumcraftApi.objectTags.get(Arrays.asList(new Object[] { item, range }));
/* 298 */             return tmp;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 303 */       tmp = (AspectList)ThaumcraftApi.objectTags.get(Arrays.asList(new Object[] { item, Integer.valueOf(32767) }));
/* 304 */       if ((tmp == null) && 
/* 305 */         (tmp == null)) {
/* 306 */         if ((meta == 32767) && (tmp == null)) {
/* 307 */           int index = 0;
/*     */           do {
/* 309 */             tmp = (AspectList)ThaumcraftApi.objectTags.get(Arrays.asList(new Object[] { item, Integer.valueOf(index) }));
/* 310 */             index++;
/* 311 */           } while ((index < 16) && (tmp == null));
/*     */         }
/* 313 */         if (tmp == null) { tmp = generateTags(item, meta);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 320 */     if ((itemstack.func_77973_b() instanceof ItemWandCasting)) {
/* 321 */       ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 322 */       if (tmp == null) tmp = new AspectList();
/* 323 */       tmp.merge(Aspect.MAGIC, (wand.getRod(itemstack).getCraftCost() + wand.getCap(itemstack).getCraftCost()) / 2);
/*     */       
/* 325 */       tmp.merge(Aspect.TOOL, (wand.getRod(itemstack).getCraftCost() + wand.getCap(itemstack).getCraftCost()) / 3);
/*     */     }
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
/*     */ 
/*     */ 
/* 341 */     if ((item != null) && (item == Items.field_151068_bn)) {
/* 342 */       if (tmp == null) tmp = new AspectList();
/* 343 */       tmp.merge(Aspect.WATER, 1);
/*     */       
/* 345 */       ItemPotion ip = (ItemPotion)item;
/* 346 */       List effects = ip.func_77834_f(itemstack.func_77960_j());
/* 347 */       if (effects != null)
/*     */       {
/* 349 */         if (ItemPotion.func_77831_g(itemstack.func_77960_j())) tmp.merge(Aspect.ENTROPY, 2);
/* 350 */         Iterator var5 = effects.iterator();
/* 351 */         while (var5.hasNext())
/*     */         {
/* 353 */           PotionEffect var6 = (PotionEffect)var5.next();
/* 354 */           tmp.merge(Aspect.MAGIC, (var6.func_76458_c() + 1) * 2);
/* 355 */           if (var6.func_76456_a() == Potion.field_76440_q.field_76415_H) { tmp.merge(Aspect.DARKNESS, (var6.func_76458_c() + 1) * 3);
/* 356 */           } else if (var6.func_76456_a() == Potion.field_76431_k.field_76415_H) { tmp.merge(Aspect.ELDRITCH, (var6.func_76458_c() + 1) * 3);
/* 357 */           } else if (var6.func_76456_a() == Potion.field_76420_g.field_76415_H) { tmp.merge(Aspect.WEAPON, (var6.func_76458_c() + 1) * 3);
/* 358 */           } else if (var6.func_76456_a() == Potion.field_76419_f.field_76415_H) { tmp.merge(Aspect.TRAP, (var6.func_76458_c() + 1) * 3);
/* 359 */           } else if (var6.func_76456_a() == Potion.field_76422_e.field_76415_H) { tmp.merge(Aspect.TOOL, (var6.func_76458_c() + 1) * 3);
/* 360 */           } else if (var6.func_76456_a() == Potion.field_76426_n.field_76415_H) {
/* 361 */             tmp.merge(Aspect.ARMOR, var6.func_76458_c() + 1);
/* 362 */             tmp.merge(Aspect.FIRE, (var6.func_76458_c() + 1) * 2);
/*     */           }
/* 364 */           else if (var6.func_76456_a() == Potion.field_76433_i.field_76415_H) { tmp.merge(Aspect.DEATH, (var6.func_76458_c() + 1) * 3);
/* 365 */           } else if (var6.func_76456_a() == Potion.field_76432_h.field_76415_H) { tmp.merge(Aspect.HEAL, (var6.func_76458_c() + 1) * 3);
/* 366 */           } else if (var6.func_76456_a() == Potion.field_76438_s.field_76415_H) { tmp.merge(Aspect.DEATH, (var6.func_76458_c() + 1) * 3);
/* 367 */           } else if (var6.func_76456_a() == Potion.field_76441_p.field_76415_H) { tmp.merge(Aspect.SENSES, (var6.func_76458_c() + 1) * 3);
/* 368 */           } else if (var6.func_76456_a() == Potion.field_76430_j.field_76415_H) { tmp.merge(Aspect.FLIGHT, (var6.func_76458_c() + 1) * 3);
/* 369 */           } else if (var6.func_76456_a() == Potion.field_76421_d.field_76415_H) { tmp.merge(Aspect.TRAP, (var6.func_76458_c() + 1) * 3);
/* 370 */           } else if (var6.func_76456_a() == Potion.field_76424_c.field_76415_H) { tmp.merge(Aspect.MOTION, (var6.func_76458_c() + 1) * 3);
/* 371 */           } else if (var6.func_76456_a() == Potion.field_76439_r.field_76415_H) { tmp.merge(Aspect.SENSES, (var6.func_76458_c() + 1) * 3);
/* 372 */           } else if (var6.func_76456_a() == Potion.field_76436_u.field_76415_H) { tmp.merge(Aspect.POISON, (var6.func_76458_c() + 1) * 3);
/* 373 */           } else if (var6.func_76456_a() == Potion.field_76428_l.field_76415_H) { tmp.merge(Aspect.HEAL, (var6.func_76458_c() + 1) * 3);
/* 374 */           } else if (var6.func_76456_a() == Potion.field_76429_m.field_76415_H) { tmp.merge(Aspect.ARMOR, (var6.func_76458_c() + 1) * 3);
/* 375 */           } else if (var6.func_76456_a() == Potion.field_76427_o.field_76415_H) { tmp.merge(Aspect.AIR, (var6.func_76458_c() + 1) * 3);
/* 376 */           } else if (var6.func_76456_a() == Potion.field_76437_t.field_76415_H) { tmp.merge(Aspect.DEATH, (var6.func_76458_c() + 1) * 3);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 382 */     return capAspects(tmp, 64);
/*     */   }
/*     */   
/*     */   private static AspectList capAspects(AspectList sourcetags, int amount)
/*     */   {
/* 387 */     if (sourcetags == null) return sourcetags;
/* 388 */     AspectList out = new AspectList();
/* 389 */     for (Aspect aspect : sourcetags.getAspects()) {
/* 390 */       out.merge(aspect, Math.min(amount, sourcetags.getAmount(aspect)));
/*     */     }
/* 392 */     return out;
/*     */   }
/*     */   
/*     */   public static AspectList getBonusTags(ItemStack itemstack, AspectList sourcetags) {
/* 396 */     AspectList tmp = new AspectList();
/*     */     
/* 398 */     Item item = itemstack.func_77973_b();
/* 399 */     if ((item != null) && ((item instanceof IEssentiaContainerItem))) {
/* 400 */       tmp = ((IEssentiaContainerItem)item).getAspects(itemstack);
/* 401 */       if ((tmp != null) && (tmp.size() > 0)) {
/* 402 */         for (Aspect tag : tmp.copy().getAspects()) {
/* 403 */           if (tmp.getAmount(tag) <= 0) tmp.remove(tag);
/*     */         }
/*     */       }
/*     */     }
/* 407 */     if (tmp == null) { tmp = new AspectList();
/*     */     }
/* 409 */     if (sourcetags != null) {
/* 410 */       for (Aspect tag : sourcetags.getAspects()) {
/* 411 */         if (tag != null) {
/* 412 */           tmp.add(tag, sourcetags.getAmount(tag));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 417 */     int id = Item.func_150891_b(itemstack.func_77973_b());
/* 418 */     int meta = itemstack.func_77960_j();
/*     */     
/* 420 */     if ((item != null) && (
/* 421 */       (tmp != null) || (item == Items.field_151068_bn)))
/*     */     {
/* 423 */       if ((item instanceof ItemArmor)) {
/* 424 */         tmp.merge(Aspect.ARMOR, ((ItemArmor)item).field_77879_b);
/*     */       }
/* 426 */       else if (((item instanceof ItemSword)) && (((ItemSword)item).func_150931_i() + 1.0F > 0.0F))
/*     */       {
/* 428 */         tmp.merge(Aspect.WEAPON, (int)(((ItemSword)item).func_150931_i() + 1.0F));
/*     */ 
/*     */       }
/* 431 */       else if ((item instanceof ItemBow)) {
/* 432 */         tmp.merge(Aspect.WEAPON, 3).merge(Aspect.FLIGHT, 1);
/*     */ 
/*     */       }
/* 435 */       else if ((item instanceof ItemPickaxe)) {
/* 436 */         String mat = ((ItemTool)item).func_77861_e();
/* 437 */         for (Item.ToolMaterial tm : Item.ToolMaterial.values()) {
/* 438 */           if (tm.toString().equals(mat)) {
/* 439 */             tmp.merge(Aspect.MINE, tm.func_77996_d() + 1);
/*     */           }
/*     */           
/*     */         }
/*     */       }
/* 444 */       else if ((item instanceof ItemTool)) {
/* 445 */         String mat = ((ItemTool)item).func_77861_e();
/* 446 */         for (Item.ToolMaterial tm : Item.ToolMaterial.values()) {
/* 447 */           if (tm.toString().equals(mat)) {
/* 448 */             tmp.merge(Aspect.TOOL, tm.func_77996_d() + 1);
/*     */           }
/*     */           
/*     */         }
/*     */       }
/* 453 */       else if (((item instanceof ItemShears)) || ((item instanceof ItemHoe))) {
/* 454 */         if (item.func_77612_l() <= Item.ToolMaterial.WOOD.func_77997_a()) {
/* 455 */           tmp.merge(Aspect.HARVEST, 1);
/*     */         }
/* 457 */         else if ((item.func_77612_l() <= Item.ToolMaterial.STONE.func_77997_a()) || (item.func_77612_l() <= Item.ToolMaterial.GOLD.func_77997_a()))
/*     */         {
/* 459 */           tmp.merge(Aspect.HARVEST, 2);
/*     */         }
/* 461 */         else if (item.func_77612_l() <= Item.ToolMaterial.IRON.func_77997_a()) {
/* 462 */           tmp.merge(Aspect.HARVEST, 3);
/*     */         } else {
/* 464 */           tmp.merge(Aspect.HARVEST, 4);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 469 */       NBTTagList ench = itemstack.func_77986_q();
/* 470 */       if ((item instanceof ItemEnchantedBook)) {
/* 471 */         ench = ((ItemEnchantedBook)item).func_92110_g(itemstack);
/*     */       }
/* 473 */       if (ench != null)
/*     */       {
/* 475 */         int var5 = 0;
/* 476 */         for (int var3 = 0; var3 < ench.func_74745_c(); var3++)
/*     */         {
/* 478 */           short eid = ench.func_150305_b(var3).func_74765_d("id");
/* 479 */           short lvl = ench.func_150305_b(var3).func_74765_d("lvl");
/* 480 */           if (eid == Enchantment.field_77341_i.field_77352_x) { tmp.merge(Aspect.WATER, lvl);
/* 481 */           } else if (eid == Enchantment.field_77336_l.field_77352_x) { tmp.merge(Aspect.BEAST, lvl);
/* 482 */           } else if (eid == Enchantment.field_77327_f.field_77352_x) { tmp.merge(Aspect.ARMOR, lvl);
/* 483 */           } else if (eid == Enchantment.field_77349_p.field_77352_x) { tmp.merge(Aspect.TOOL, lvl);
/* 484 */           } else if (eid == Enchantment.field_77330_e.field_77352_x) { tmp.merge(Aspect.FLIGHT, lvl);
/* 485 */           } else if (eid == Enchantment.field_77334_n.field_77352_x) { tmp.merge(Aspect.FIRE, lvl);
/* 486 */           } else if (eid == Enchantment.field_77329_d.field_77352_x) { tmp.merge(Aspect.ARMOR, lvl);
/* 487 */           } else if (eid == Enchantment.field_77343_v.field_77352_x) { tmp.merge(Aspect.FIRE, lvl);
/* 488 */           } else if (eid == Enchantment.field_77346_s.field_77352_x) { tmp.merge(Aspect.GREED, lvl);
/* 489 */           } else if (eid == Enchantment.field_77342_w.field_77352_x) { tmp.merge(Aspect.CRAFT, lvl);
/* 490 */           } else if (eid == Enchantment.field_77337_m.field_77352_x) { tmp.merge(Aspect.AIR, lvl);
/* 491 */           } else if (eid == Enchantment.field_77335_o.field_77352_x) { tmp.merge(Aspect.GREED, lvl);
/* 492 */           } else if (eid == Enchantment.field_77345_t.field_77352_x) { tmp.merge(Aspect.WEAPON, lvl);
/* 493 */           } else if (eid == Enchantment.field_77328_g.field_77352_x) { tmp.merge(Aspect.ARMOR, lvl);
/* 494 */           } else if (eid == Enchantment.field_77332_c.field_77352_x) { tmp.merge(Aspect.ARMOR, lvl);
/* 495 */           } else if (eid == Enchantment.field_77344_u.field_77352_x) { tmp.merge(Aspect.AIR, lvl);
/* 496 */           } else if (eid == Enchantment.field_77340_h.field_77352_x) { tmp.merge(Aspect.AIR, lvl);
/* 497 */           } else if (eid == Enchantment.field_77338_j.field_77352_x) { tmp.merge(Aspect.WEAPON, lvl);
/* 498 */           } else if (eid == Enchantment.field_77348_q.field_77352_x) { tmp.merge(Aspect.EXCHANGE, lvl);
/* 499 */           } else if (eid == Enchantment.field_92091_k.field_77352_x) { tmp.merge(Aspect.WEAPON, lvl);
/* 500 */           } else if (eid == Enchantment.field_77339_k.field_77352_x) { tmp.merge(Aspect.ENTROPY, lvl);
/* 501 */           } else if (eid == Enchantment.field_77347_r.field_77352_x) { tmp.merge(Aspect.EARTH, lvl);
/* 502 */           } else if (eid == Enchantment.field_151370_z.field_77352_x) { tmp.merge(Aspect.GREED, lvl);
/* 503 */           } else if (eid == Enchantment.field_151369_A.field_77352_x) { tmp.merge(Aspect.BEAST, lvl);
/*     */ 
/*     */           }
/* 506 */           else if (eid == Config.enchHaste.field_77352_x) { tmp.merge(Aspect.MOTION, lvl);
/* 507 */           } else if (eid == Config.enchRepair.field_77352_x) {
/* 508 */             tmp.merge(Aspect.TOOL, lvl);
/*     */           }
/*     */           
/* 511 */           var5 += lvl;
/*     */         }
/* 513 */         if (var5 > 0) { tmp.merge(Aspect.MAGIC, var5);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 518 */     return ThaumcraftApiHelper.cullTags(tmp);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static AspectList generateTags(Item item, int meta)
/*     */   {
/* 525 */     AspectList temp = generateTags(item, meta, new ArrayList());
/* 526 */     return temp;
/*     */   }
/*     */   
/*     */   public static AspectList generateTags(Item item, int meta, ArrayList<List> history)
/*     */   {
/* 531 */     int tmeta = meta;
/*     */     try {
/* 533 */       tmeta = (new ItemStack(item, 1, meta).func_77973_b().func_77645_m()) || (!new ItemStack(item, 1, meta).func_77973_b().func_77614_k()) ? 32767 : meta;
/*     */     }
/*     */     catch (Exception e) {}
/*     */     
/*     */ 
/* 538 */     if (ThaumcraftApi.exists(item, tmeta)) {
/* 539 */       return getObjectTags(new ItemStack(item, 1, tmeta));
/*     */     }
/*     */     
/* 542 */     if (history.contains(Arrays.asList(new Object[] { item, Integer.valueOf(tmeta) }))) {
/* 543 */       return null;
/*     */     }
/*     */     
/* 546 */     history.add(Arrays.asList(new Object[] { item, Integer.valueOf(tmeta) }));
/*     */     
/*     */     AspectList ret;
/*     */     
/* 550 */     if (history.size() < 100)
/* 551 */       ret = generateTagsFromRecipes(item, tmeta == 32767 ? 0 : meta, history); else {
/* 552 */       return null;
/*     */     }
/* 554 */     AspectList ret = capAspects(ret, 64);
/*     */     
/*     */ 
/* 557 */     ThaumcraftApi.registerObjectTag(new ItemStack(item, 1, tmeta), ret);
/*     */     
/* 559 */     return ret;
/*     */   }
/*     */   
/*     */   private static AspectList generateTagsFromCrucibleRecipes(Item item, int meta, ArrayList<List> history)
/*     */   {
/* 564 */     CrucibleRecipe cr = ThaumcraftApi.getCrucibleRecipe(new ItemStack(item, 1, meta));
/*     */     
/* 566 */     if (cr != null) {
/* 567 */       AspectList ot = cr.aspects.copy();
/* 568 */       int ss = cr.getRecipeOutput().field_77994_a;
/* 569 */       ItemStack cat = null;
/* 570 */       if ((cr.catalyst instanceof ItemStack)) {
/* 571 */         cat = (ItemStack)cr.catalyst;
/* 572 */       } else if (((cr.catalyst instanceof ArrayList)) && (((ArrayList)cr.catalyst).size() > 0)) {
/* 573 */         cat = (ItemStack)((ArrayList)cr.catalyst).get(0);
/*     */       }
/*     */       
/* 576 */       AspectList ot2 = generateTags(cat.func_77973_b(), cat.func_77960_j());
/*     */       
/* 578 */       AspectList out = new AspectList();
/*     */       
/* 580 */       if ((ot2 != null) && (ot2.size() > 0)) {
/* 581 */         for (Aspect tt : ot2.getAspects()) {
/* 582 */           out.add(tt, ot2.getAmount(tt));
/*     */         }
/*     */       }
/* 585 */       for (Aspect tt : ot.getAspects()) {
/* 586 */         int amt = (int)(Math.sqrt(ot.getAmount(tt)) / ss);
/* 587 */         out.add(tt, amt);
/*     */       }
/*     */       
/* 590 */       for (Aspect as : out.getAspects()) {
/* 591 */         if (out.getAmount(as) <= 0) out.remove(as);
/*     */       }
/* 593 */       return out;
/*     */     }
/* 595 */     return null;
/*     */   }
/*     */   
/*     */   private static AspectList generateTagsFromArcaneRecipes(Item item, int meta, ArrayList<List> history) {
/* 599 */     AspectList ret = null;
/* 600 */     int value = 0;
/*     */     
/*     */ 
/* 603 */     List recipeList = ThaumcraftApi.getCraftingRecipes();
/*     */     label882:
/* 605 */     for (int q = 0; q < recipeList.size(); q++)
/* 606 */       if ((recipeList.get(q) instanceof IArcaneRecipe)) {
/* 607 */         IArcaneRecipe recipe = (IArcaneRecipe)recipeList.get(q);
/* 608 */         if (recipe.getRecipeOutput() != null) {
/* 609 */           int idR = recipe.getRecipeOutput().func_77960_j() == 32767 ? 0 : recipe.getRecipeOutput().func_77960_j();
/* 610 */           int idS = meta < 0 ? 0 : meta;
/* 611 */           if ((recipe.getRecipeOutput().func_77973_b() == item) && (idR == idS)) {
/* 612 */             ArrayList<ItemStack> ingredients = new ArrayList();
/* 613 */             AspectList ph = new AspectList();
/* 614 */             int cval = 0;
/*     */             try {
/* 616 */               if ((recipeList.get(q) instanceof ShapedArcaneRecipe)) {
/* 617 */                 int width = ((ShapedArcaneRecipe)recipeList.get(q)).width;
/* 618 */                 int height = ((ShapedArcaneRecipe)recipeList.get(q)).height;
/* 619 */                 Object[] items = ((ShapedArcaneRecipe)recipeList.get(q)).getInput();
/*     */                 
/* 621 */                 for (int i = 0; (i < width) && (i < 3); i++) {
/* 622 */                   for (int j = 0; (j < height) && (j < 3); j++) {
/* 623 */                     if (items[(i + j * width)] != null)
/*     */                     {
/* 625 */                       if ((items[(i + j * width)] instanceof ArrayList)) {
/* 626 */                         for (ItemStack it : (ArrayList)items[(i + j * width)]) {
/* 627 */                           if (Utils.isEETransmutionItem(it.func_77973_b())) break label882;
/* 628 */                           AspectList obj = generateTags(it.func_77973_b(), it.func_77960_j(), history);
/* 629 */                           if ((obj != null) && (obj.size() > 0)) {
/* 630 */                             ItemStack is = it.func_77946_l();
/* 631 */                             is.field_77994_a = 1;
/* 632 */                             ingredients.add(is);
/* 633 */                             break;
/*     */                           }
/*     */                         }
/*     */                       } else {
/* 637 */                         ItemStack it = (ItemStack)items[(i + j * width)];
/* 638 */                         if (Utils.isEETransmutionItem(it.func_77973_b())) break label882;
/* 639 */                         ItemStack is = it.func_77946_l();
/* 640 */                         is.field_77994_a = 1;
/* 641 */                         ingredients.add(is);
/*     */                       } }
/*     */                   }
/*     */                 }
/* 645 */               } else if ((recipeList.get(q) instanceof ShapelessArcaneRecipe)) {
/* 646 */                 ArrayList items = ((ShapelessArcaneRecipe)recipeList.get(q)).getInput();
/*     */                 
/* 648 */                 for (int i = 0; (i < items.size()) && (i < 9); i++) {
/* 649 */                   if (items.get(i) != null)
/*     */                   {
/* 651 */                     if ((items.get(i) instanceof ArrayList)) {
/* 652 */                       for (ItemStack it : (ArrayList)items.get(i)) {
/* 653 */                         if (Utils.isEETransmutionItem(it.func_77973_b())) break label882;
/* 654 */                         AspectList obj = generateTags(it.func_77973_b(), it.func_77960_j(), history);
/*     */                         
/* 656 */                         if ((obj != null) && (obj.size() > 0)) {
/* 657 */                           ItemStack is = it.func_77946_l();
/* 658 */                           is.field_77994_a = 1;
/* 659 */                           ingredients.add(is);
/* 660 */                           break;
/*     */                         }
/*     */                       }
/*     */                     } else {
/* 664 */                       ItemStack it = (ItemStack)items.get(i);
/* 665 */                       if (Utils.isEETransmutionItem(it.func_77973_b())) break label882;
/* 666 */                       ItemStack is = it.func_77946_l();
/* 667 */                       is.field_77994_a = 1;
/* 668 */                       ingredients.add(is);
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/* 673 */               ph = getAspectsFromIngredients(ingredients, recipe.getRecipeOutput(), history);
/* 674 */               if (recipe.getAspects() != null) {
/* 675 */                 for (Aspect a : recipe.getAspects().getAspects()) {
/* 676 */                   ph.add(a, (int)(Math.sqrt(recipe.getAspects().getAmount(a)) / recipe.getRecipeOutput().field_77994_a));
/*     */                 }
/*     */               }
/* 679 */               for (Aspect as : ph.copy().getAspects()) {
/* 680 */                 if (ph.getAmount(as) <= 0) ph.remove(as);
/*     */               }
/* 682 */               if (cval >= value) {
/* 683 */                 ret = ph;
/* 684 */                 value = cval;
/*     */               }
/*     */             } catch (Exception e) {
/* 687 */               e.printStackTrace();
/*     */             }
/*     */           } } }
/* 690 */     return ret;
/*     */   }
/*     */   
/*     */   private static AspectList generateTagsFromInfusionRecipes(Item item, int meta, ArrayList<List> history)
/*     */   {
/* 695 */     InfusionRecipe cr = ThaumcraftApi.getInfusionRecipe(new ItemStack(item, 1, meta));
/*     */     
/* 697 */     if (cr != null) {
/* 698 */       AspectList ot = cr.getAspects().copy();
/* 699 */       ArrayList<ItemStack> ingredients = new ArrayList();
/* 700 */       ItemStack is = cr.getRecipeInput().func_77946_l();
/* 701 */       is.field_77994_a = 1;
/* 702 */       ingredients.add(is);
/* 703 */       for (ItemStack cat : cr.getComponents()) {
/* 704 */         ItemStack is2 = cat.func_77946_l();
/* 705 */         is2.field_77994_a = 1;
/* 706 */         ingredients.add(is2);
/*     */       }
/*     */       
/* 709 */       AspectList out = new AspectList();
/*     */       
/* 711 */       AspectList ot2 = getAspectsFromIngredients(ingredients, (ItemStack)cr.getRecipeOutput(), history);
/*     */       
/* 713 */       for (Aspect tt : ot2.getAspects()) {
/* 714 */         out.add(tt, ot2.getAmount(tt));
/*     */       }
/*     */       
/* 717 */       for (Aspect tt : ot.getAspects()) {
/* 718 */         int amt = (int)(Math.sqrt(ot.getAmount(tt)) / ((ItemStack)cr.getRecipeOutput()).field_77994_a);
/* 719 */         out.add(tt, amt);
/*     */       }
/*     */       
/* 722 */       for (Aspect as : out.getAspects()) {
/* 723 */         if (out.getAmount(as) <= 0) { out.remove(as);
/*     */         }
/*     */       }
/* 726 */       return out;
/*     */     }
/* 728 */     return null;
/*     */   }
/*     */   
/*     */   private static AspectList generateTagsFromCraftingRecipes(Item item, int meta, ArrayList<List> history) {
/* 732 */     AspectList ret = null;
/* 733 */     int value = Integer.MAX_VALUE;
/*     */     
/*     */ 
/* 736 */     List recipeList = CraftingManager.func_77594_a().func_77592_b();
/*     */     label1083:
/* 738 */     for (int q = 0; q < recipeList.size(); q++) {
/* 739 */       IRecipe recipe = (IRecipe)recipeList.get(q);
/* 740 */       if ((recipe != null) && (recipe.func_77571_b() != null) && (Item.func_150891_b(recipe.func_77571_b().func_77973_b()) > 0) && (recipe.func_77571_b().func_77973_b() != null))
/*     */       {
/* 742 */         int idR = recipe.func_77571_b().func_77960_j() == 32767 ? 0 : recipe.func_77571_b().func_77960_j();
/* 743 */         int idS = meta == 32767 ? 0 : meta;
/*     */         
/* 745 */         if ((recipe.func_77571_b().func_77973_b() == item) && (idR == idS)) {
/* 746 */           ArrayList<ItemStack> ingredients = new ArrayList();
/* 747 */           AspectList ph = new AspectList();
/* 748 */           int cval = 0;
/*     */           try {
/* 750 */             if ((recipeList.get(q) instanceof ShapedRecipes))
/*     */             {
/* 752 */               int width = ((ShapedRecipes)recipeList.get(q)).field_77576_b;
/* 753 */               int height = ((ShapedRecipes)recipeList.get(q)).field_77577_c;
/* 754 */               ItemStack[] items = ((ShapedRecipes)recipeList.get(q)).field_77574_d;
/*     */               
/* 756 */               for (int i = 0; (i < width) && (i < 3); i++) {
/* 757 */                 for (int j = 0; (j < height) && (j < 3); j++)
/* 758 */                   if (items[(i + j * width)] != null)
/*     */                   {
/* 760 */                     if (Utils.isEETransmutionItem(items[(i + j * width)].func_77973_b())) break label1083;
/* 761 */                     ItemStack is = items[(i + j * width)].func_77946_l();
/* 762 */                     is.field_77994_a = 1;
/* 763 */                     ingredients.add(is);
/*     */                   }
/*     */               }
/* 766 */             } else if ((recipeList.get(q) instanceof ShapelessRecipes)) {
/* 767 */               List<ItemStack> items = ((ShapelessRecipes)recipeList.get(q)).field_77579_b;
/* 768 */               for (int i = 0; (i < items.size()) && (i < 9); i++)
/* 769 */                 if (items.get(i) != null)
/*     */                 {
/* 771 */                   if (Utils.isEETransmutionItem(((ItemStack)items.get(i)).func_77973_b())) break label1083;
/* 772 */                   ItemStack is = ((ItemStack)items.get(i)).func_77946_l();
/* 773 */                   is.field_77994_a = 1;
/* 774 */                   ingredients.add(is);
/*     */                 }
/* 776 */             } else if ((recipeList.get(q) instanceof ShapedOreRecipe))
/*     */             {
/* 778 */               int size = ((ShapedOreRecipe)recipeList.get(q)).func_77570_a();
/*     */               
/* 780 */               Object[] items = ((ShapedOreRecipe)recipeList.get(q)).getInput();
/*     */               
/* 782 */               for (int i = 0; (i < size) && (i < 9); i++) {
/* 783 */                 if (items[i] != null)
/*     */                 {
/* 785 */                   if ((items[i] instanceof ArrayList)) {
/* 786 */                     for (ItemStack it : (ArrayList)items[i]) {
/* 787 */                       if (Utils.isEETransmutionItem(it.func_77973_b())) break label1083;
/* 788 */                       AspectList obj = generateTags(it.func_77973_b(), it.func_77960_j(), history);
/* 789 */                       if ((obj != null) && (obj.size() > 0)) {
/* 790 */                         ItemStack is = it.func_77946_l();
/* 791 */                         is.field_77994_a = 1;
/* 792 */                         ingredients.add(is);
/* 793 */                         break;
/*     */                       }
/*     */                     }
/*     */                   } else {
/* 797 */                     ItemStack it = (ItemStack)items[i];
/* 798 */                     if (Utils.isEETransmutionItem(it.func_77973_b())) break label1083;
/* 799 */                     ItemStack is = it.func_77946_l();
/* 800 */                     is.field_77994_a = 1;
/* 801 */                     ingredients.add(is);
/*     */                   }
/*     */                 }
/*     */               }
/* 805 */             } else if ((recipeList.get(q) instanceof ShapelessOreRecipe)) {
/* 806 */               ArrayList items = ((ShapelessOreRecipe)recipeList.get(q)).getInput();
/*     */               
/* 808 */               for (int i = 0; (i < items.size()) && (i < 9); i++) {
/* 809 */                 if (items.get(i) != null)
/*     */                 {
/* 811 */                   if ((items.get(i) instanceof ArrayList)) {
/* 812 */                     for (ItemStack it : (ArrayList)items.get(i)) {
/* 813 */                       if (Utils.isEETransmutionItem(it.func_77973_b())) break label1083;
/* 814 */                       AspectList obj = generateTags(it.func_77973_b(), it.func_77960_j(), history);
/*     */                       
/* 816 */                       if ((obj != null) && (obj.size() > 0)) {
/* 817 */                         ItemStack is = it.func_77946_l();
/* 818 */                         is.field_77994_a = 1;
/* 819 */                         ingredients.add(is);
/* 820 */                         break;
/*     */                       }
/*     */                     }
/*     */                   } else {
/* 824 */                     ItemStack it = (ItemStack)items.get(i);
/* 825 */                     if (Utils.isEETransmutionItem(it.func_77973_b())) break label1083;
/* 826 */                     ItemStack is = it.func_77946_l();
/* 827 */                     is.field_77994_a = 1;
/* 828 */                     ingredients.add(is);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/* 833 */             ph = getAspectsFromIngredients(ingredients, recipe.func_77571_b(), history);
/* 834 */             for (Aspect as : ph.copy().getAspects()) {
/* 835 */               if (ph.getAmount(as) <= 0) ph.remove(as);
/*     */             }
/* 837 */             if ((ph.visSize() < value) && (ph.visSize() > 0)) {
/* 838 */               ret = ph;
/* 839 */               value = ph.visSize();
/*     */             }
/*     */           } catch (Exception e) {
/* 842 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */       } }
/* 846 */     return ret;
/*     */   }
/*     */   
/*     */   private static AspectList getAspectsFromIngredients(ArrayList<ItemStack> ingredients, ItemStack recipeOut, ArrayList<List> history)
/*     */   {
/* 851 */     AspectList out = new AspectList();
/* 852 */     AspectList mid = new AspectList();
/* 853 */     for (ItemStack is : ingredients) {
/* 854 */       AspectList obj = generateTags(is.func_77973_b(), is.func_77960_j(), history);
/*     */       
/* 856 */       if (is.func_77973_b().func_77668_q() != null) {
/* 857 */         if (is.func_77973_b().func_77668_q() != is.func_77973_b())
/*     */         {
/*     */ 
/* 860 */           AspectList objC = generateTags(is.func_77973_b().func_77668_q(), 32767, history);
/* 861 */           for (Aspect as : objC.getAspects()) {
/* 862 */             out.reduce(as, objC.getAmount(as));
/*     */           }
/*     */         }
/*     */       }
/* 866 */       else if (obj != null) {
/* 867 */         for (Aspect as : obj.getAspects()) {
/* 868 */           if (as != null) {
/* 869 */             mid.add(as, obj.getAmount(as));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 874 */     for (Aspect as : mid.getAspects()) {
/* 875 */       if (as != null) {
/* 876 */         out.add(as, (int)(mid.getAmount(as) * 0.75F / recipeOut.field_77994_a));
/*     */       }
/*     */     }
/* 879 */     for (Aspect as : out.getAspects()) {
/* 880 */       if (out.getAmount(as) <= 0) out.remove(as);
/*     */     }
/* 882 */     return out;
/*     */   }
/*     */   
/*     */   private static AspectList generateTagsFromRecipes(Item item, int meta, ArrayList<List> history)
/*     */   {
/* 887 */     AspectList ret = null;
/* 888 */     int value = 0;
/*     */     
/*     */ 
/* 891 */     ret = generateTagsFromCrucibleRecipes(item, meta, history);
/* 892 */     if (ret != null) { return ret;
/*     */     }
/*     */     
/* 895 */     ret = generateTagsFromArcaneRecipes(item, meta, history);
/* 896 */     if (ret != null) { return ret;
/*     */     }
/*     */     
/* 899 */     ret = generateTagsFromInfusionRecipes(item, meta, history);
/* 900 */     if (ret != null) { return ret;
/*     */     }
/*     */     
/* 903 */     ret = generateTagsFromCraftingRecipes(item, meta, history);
/* 904 */     return ret;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/crafting/ThaumcraftCraftingManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */