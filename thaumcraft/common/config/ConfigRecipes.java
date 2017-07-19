/*      */ package thaumcraft.common.config;
/*      */ 
/*      */ import cpw.mods.fml.common.registry.GameRegistry;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import net.minecraft.enchantment.Enchantment;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.CraftingManager;
/*      */ import net.minecraft.item.crafting.FurnaceRecipes;
/*      */ import net.minecraft.item.crafting.IRecipe;
/*      */ import net.minecraft.nbt.NBTTagByte;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagInt;
/*      */ import net.minecraftforge.oredict.RecipeSorter;
/*      */ import net.minecraftforge.oredict.RecipeSorter.Category;
/*      */ import net.minecraftforge.oredict.ShapedOreRecipe;
/*      */ import net.minecraftforge.oredict.ShapelessOreRecipe;
/*      */ import thaumcraft.api.ThaumcraftApi;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*      */ import thaumcraft.api.wands.WandCap;
/*      */ import thaumcraft.api.wands.WandRod;
/*      */ import thaumcraft.api.wands.WandTriggerRegistry;
/*      */ import thaumcraft.common.CommonProxy;
/*      */ import thaumcraft.common.Thaumcraft;
/*      */ import thaumcraft.common.items.armor.RecipesRobeArmorDyes;
/*      */ import thaumcraft.common.items.armor.RecipesVoidRobeArmorDyes;
/*      */ import thaumcraft.common.items.wands.ItemWandCasting;
/*      */ import thaumcraft.common.lib.crafting.ArcaneSceptreRecipe;
/*      */ import thaumcraft.common.lib.crafting.ArcaneWandRecipe;
/*      */ import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;
/*      */ import thaumcraft.common.lib.crafting.ShapelessNBTOreRecipe;
/*      */ 
/*      */ public class ConfigRecipes
/*      */ {
/*   41 */   static ItemStack basicWand = new ItemStack(ConfigItems.itemWandCasting);
/*      */   
/*      */   public static void init()
/*      */   {
/*   45 */     ((ItemWandCasting)basicWand.func_77973_b()).setCap(basicWand, ConfigItems.WAND_CAP_IRON);
/*   46 */     ((ItemWandCasting)basicWand.func_77973_b()).setRod(basicWand, ConfigItems.WAND_ROD_WOOD);
/*      */     
/*   48 */     initializeSmelting();
/*   49 */     initializeNormalRecipes();
/*   50 */     initializeArcaneRecipes();
/*   51 */     initializeInfusionRecipes();
/*   52 */     initializeInfusionEnchantmentRecipes();
/*   53 */     initializeAlchemyRecipes();
/*   54 */     initializeCompoundRecipes();
/*      */     
/*   56 */     RecipeSorter.register("forge:shapelessorenbt", ShapelessNBTOreRecipe.class, RecipeSorter.Category.SHAPELESS, "after:forge:shapelessore");
/*   57 */     RecipeSorter.register("forge:robearmordye", RecipesRobeArmorDyes.class, RecipeSorter.Category.SHAPELESS, "after:forge:shapelessorenbt");
/*   58 */     RecipeSorter.register("forge:voidrobearmordye", RecipesVoidRobeArmorDyes.class, RecipeSorter.Category.SHAPELESS, "after:forge:robearmordye");
/*      */   }
/*      */   
/*      */ 
/*      */   private static void initializeCompoundRecipes()
/*      */   {
/*   64 */     ItemStack empty = new ItemStack(ConfigBlocks.blockHole, 1, 15);
/*      */     
/*   66 */     ConfigResearch.recipes.put("Thaumonomicon", Arrays.asList(new Object[] { new AspectList(), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1), Arrays.asList(new ItemStack[] { basicWand, new ItemStack(Blocks.field_150342_X) }) }));
/*      */     
/*      */ 
/*   69 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager, 0, Blocks.field_150342_X, 0, "Thaumcraft");
/*      */     
/*      */ 
/*   72 */     ConfigResearch.recipes.put("ArcTable", Arrays.asList(new Object[] { new AspectList(), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1), Arrays.asList(new ItemStack[] { basicWand, new ItemStack(ConfigBlocks.blockTable) }) }));
/*      */     
/*      */ 
/*      */ 
/*   76 */     ConfigResearch.recipes.put("ResTable", Arrays.asList(new Object[] { new AspectList(), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(2), Arrays.asList(new ItemStack[] { null, new ItemStack(ConfigItems.itemInkwell), new ItemStack(ConfigBlocks.blockTable), new ItemStack(ConfigBlocks.blockTable) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   82 */     ConfigResearch.recipes.put("Crucible", Arrays.asList(new Object[] { new AspectList(), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1), Arrays.asList(new ItemStack[] { basicWand, new ItemStack(Items.field_151066_bu) }) }));
/*      */     
/*      */ 
/*   85 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager, 1, Blocks.field_150383_bp, -1, "Thaumcraft");
/*      */     
/*      */ 
/*      */ 
/*   89 */     ConfigResearch.recipes.put("InfernalFurnace", Arrays.asList(new Object[] { new AspectList().add(Aspect.FIRE, 50).add(Aspect.EARTH, 50), Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(3), Arrays.asList(new ItemStack[] { new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), empty, new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150353_l), new ItemStack(Blocks.field_150411_aY), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj), new ItemStack(Blocks.field_150343_Z), new ItemStack(Blocks.field_150385_bj) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  118 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager, 2, Blocks.field_150343_Z, -1, "Thaumcraft");
/*      */     
/*  120 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager, 2, Blocks.field_150385_bj, -1, "Thaumcraft");
/*      */     
/*  122 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager, 2, Blocks.field_150411_aY, -1, "Thaumcraft");
/*      */     
/*      */ 
/*  125 */     ConfigResearch.recipes.put("InfusionAltar", Arrays.asList(new Object[] { new AspectList().add(Aspect.FIRE, 25).add(Aspect.EARTH, 25).add(Aspect.ORDER, 25).add(Aspect.AIR, 25).add(Aspect.ENTROPY, 25).add(Aspect.WATER, 25), Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(3), Arrays.asList(new ItemStack[] { empty, null, empty, null, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 2), null, empty, null, empty, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), null, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), null, null, null, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), null, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7), null, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7), null, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 1), null, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7), null, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  151 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager, 3, ConfigBlocks.blockStoneDevice, 2, "Thaumcraft");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  156 */     ConfigResearch.recipes.put("NodeJar", Arrays.asList(new Object[] { new AspectList().add(Aspect.FIRE, 70).add(Aspect.EARTH, 70).add(Aspect.AIR, 70).add(Aspect.WATER, 70).add(Aspect.ORDER, 70).add(Aspect.ENTROPY, 70), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(3), Arrays.asList(new Object[] { "slabWood", "slabWood", "slabWood", "slabWood", "slabWood", "slabWood", "slabWood", "slabWood", "slabWood", new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(ConfigBlocks.blockAiry, 1, 5), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w), new ItemStack(Blocks.field_150359_w) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  193 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager, 4, Blocks.field_150359_w, -1, "Thaumcraft");
/*      */     
/*      */ 
/*  196 */     ConfigResearch.recipes.put("Thaumatorium", Arrays.asList(new Object[] { new AspectList().add(Aspect.FIRE, 15).add(Aspect.ORDER, 30).add(Aspect.WATER, 30), Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(1), Arrays.asList(new ItemStack[] { new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 0) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  202 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager, 5, ConfigBlocks.blockMetalDevice, 9, "Thaumcraft");
/*      */     
/*      */ 
/*  205 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager, 6, ConfigBlocks.blockEldritch, 0, "Thaumcraft");
/*      */     
/*      */ 
/*      */ 
/*  209 */     ConfigResearch.recipes.put("AdvAlchemyFurnace", Arrays.asList(new Object[] { new AspectList().add(Aspect.FIRE, 50).add(Aspect.WATER, 50).add(Aspect.ORDER, 50), Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(3), Arrays.asList(new ItemStack[] { new ItemStack(ConfigBlocks.blockMetalDevice, 1, 1), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 1), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9), empty, new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 1), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 1), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 3), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 3), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 3), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 3), new ItemStack(ConfigBlocks.blockStoneDevice, 1, 0), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 3), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 3), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 3), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 3) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  231 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager, 7, ConfigBlocks.blockMetalDevice, 3, "Thaumcraft");
/*      */     
/*  233 */     WandTriggerRegistry.registerWandBlockTrigger(Thaumcraft.proxy.wandManager, 7, ConfigBlocks.blockMetalDevice, 9, "Thaumcraft_2");
/*      */   }
/*      */   
/*      */ 
/*      */   private static void initializeAlchemyRecipes()
/*      */   {
/*  239 */     Aspect[] aspect = { Aspect.AIR, Aspect.FIRE, Aspect.WATER, Aspect.EARTH, Aspect.ORDER, Aspect.ENTROPY };
/*      */     
/*  241 */     for (int a = 0; a < 6; a++) {
/*  242 */       AspectList al = new AspectList();
/*  243 */       for (int b = 0; b < 6; b++) {
/*  244 */         if (b != a)
/*      */         {
/*  246 */           al.add(aspect[b], 2); }
/*      */       }
/*  248 */       ConfigResearch.recipes.put("BalancedShard_" + a, ThaumcraftApi.addCrucibleRecipe("CRUCIBLE", new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, a), al));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  254 */     ConfigResearch.recipes.put("Alumentum", ThaumcraftApi.addCrucibleRecipe("ALUMENTUM", new ItemStack(ConfigItems.itemResource, 1, 0), new ItemStack(Items.field_151044_h, 1, 32767), new AspectList().merge(Aspect.ENERGY, 3).merge(Aspect.FIRE, 3).merge(Aspect.ENTROPY, 3)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  265 */     ConfigResearch.recipes.put("Nitor", ThaumcraftApi.addCrucibleRecipe("NITOR", new ItemStack(ConfigItems.itemResource, 1, 1), "dustGlowstone", new AspectList().merge(Aspect.ENERGY, 3).merge(Aspect.FIRE, 3).merge(Aspect.LIGHT, 3)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  270 */     ConfigResearch.recipes.put("Thaumium", ThaumcraftApi.addCrucibleRecipe("THAUMIUM", new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(Items.field_151042_j), new AspectList().merge(Aspect.MAGIC, 4)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  275 */     ConfigResearch.recipes.put("VoidMetal", ThaumcraftApi.addCrucibleRecipe("VOIDMETAL", new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemResource, 1, 17), new AspectList().merge(Aspect.METAL, 8)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  280 */     ConfigResearch.recipes.put("VoidSeed", ThaumcraftApi.addCrucibleRecipe("VOIDMETAL", new ItemStack(ConfigItems.itemResource, 1, 17), new ItemStack(Items.field_151014_N), new AspectList().merge(Aspect.DARKNESS, 8).merge(Aspect.VOID, 8).merge(Aspect.ELDRITCH, 2)));
/*      */     
/*      */ 
/*      */ 
/*  284 */     ConfigResearch.recipes.put("Tallow", ThaumcraftApi.addCrucibleRecipe("TALLOW", new ItemStack(ConfigItems.itemResource, 1, 4), new ItemStack(Items.field_151078_bh), new AspectList().merge(Aspect.MAGIC, 2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  291 */     ConfigResearch.recipes.put("AltGunpowder", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALDUPLICATION", new ItemStack(Items.field_151016_H, 2, 0), new ItemStack(Items.field_151016_H), new AspectList().merge(Aspect.FIRE, 4).merge(Aspect.ENTROPY, 4)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  299 */     ConfigResearch.recipes.put("AltSlime", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALDUPLICATION", new ItemStack(Items.field_151123_aH, 2, 0), new ItemStack(Items.field_151123_aH), new AspectList().merge(Aspect.WATER, 2).merge(Aspect.LIFE, 2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  305 */     ConfigResearch.recipes.put("AltClay", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALDUPLICATION", new ItemStack(Items.field_151119_aD, 2, 0), new ItemStack(Items.field_151119_aD), new AspectList().merge(Aspect.WATER, 1).merge(Aspect.EARTH, 2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  311 */     ConfigResearch.recipes.put("AltGlowstone", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALDUPLICATION", new ItemStack(Items.field_151114_aO, 2, 0), "dustGlowstone", new AspectList().merge(Aspect.LIGHT, 3).merge(Aspect.SENSES, 1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  319 */     ConfigResearch.recipes.put("AltInk", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALDUPLICATION", new ItemStack(Items.field_151100_aR, 2, 0), new ItemStack(Items.field_151100_aR, 1, 0), new AspectList().merge(Aspect.WATER, 2).merge(Aspect.SENSES, 2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  326 */     ConfigResearch.recipes.put("AltWeb", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALMANUFACTURE", new ItemStack(Blocks.field_150321_G), new ItemStack(Items.field_151007_F), new AspectList().merge(Aspect.TRAP, 2).merge(Aspect.CLOTH, 2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  334 */     ConfigResearch.recipes.put("AltMossyCobble", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALMANUFACTURE", new ItemStack(Blocks.field_150341_Y), new ItemStack(Blocks.field_150347_e), new AspectList().merge(Aspect.PLANT, 2).merge(Aspect.MAGIC, 1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  342 */     ConfigResearch.recipes.put("AltIce", ThaumcraftApi.addCrucibleRecipe("ALCHEMICALMANUFACTURE", new ItemStack(Blocks.field_150432_aD), new ItemStack(Blocks.field_150433_aE), new AspectList().merge(Aspect.ORDER, 1).merge(Aspect.COLD, 1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  350 */     ConfigResearch.recipes.put("AltCrackedBrick", ThaumcraftApi.addCrucibleRecipe("ENTROPICPROCESSING", new ItemStack(Blocks.field_150417_aV, 1, 2), new ItemStack(Blocks.field_150417_aV), new AspectList().merge(Aspect.ENTROPY, 2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  356 */     ConfigResearch.recipes.put("AltBonemeal", ThaumcraftApi.addCrucibleRecipe("ENTROPICPROCESSING", new ItemStack(Items.field_151100_aR, 4, 15), new ItemStack(Items.field_151103_aS), new AspectList().merge(Aspect.ENTROPY, 1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  365 */     ConfigResearch.recipes.put("PureIron", ThaumcraftApi.addCrucibleRecipe("PUREIRON", new ItemStack(ConfigItems.itemNugget, 1, 16), "oreIron", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  371 */     ConfigResearch.recipes.put("PureGold", ThaumcraftApi.addCrucibleRecipe("PUREGOLD", new ItemStack(ConfigItems.itemNugget, 1, 31), "oreGold", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  377 */     if (Config.foundCopperIngot) {
/*  378 */       ConfigResearch.recipes.put("PureCopper", ThaumcraftApi.addCrucibleRecipe("PURECOPPER", new ItemStack(ConfigItems.itemNugget, 1, 17), "oreCopper", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  386 */     if (Config.foundTinIngot) {
/*  387 */       ConfigResearch.recipes.put("PureTin", ThaumcraftApi.addCrucibleRecipe("PURETIN", new ItemStack(ConfigItems.itemNugget, 1, 18), "oreTin", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  395 */     if (Config.foundSilverIngot) {
/*  396 */       ConfigResearch.recipes.put("PureSilver", ThaumcraftApi.addCrucibleRecipe("PURESILVER", new ItemStack(ConfigItems.itemNugget, 1, 19), "oreSilver", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  404 */     if (Config.foundLeadIngot) {
/*  405 */       ConfigResearch.recipes.put("PureLead", ThaumcraftApi.addCrucibleRecipe("PURELEAD", new ItemStack(ConfigItems.itemNugget, 1, 20), "oreLead", new AspectList().merge(Aspect.METAL, 1).merge(Aspect.ORDER, 1)));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  415 */     ConfigResearch.recipes.put("TransIron", ThaumcraftApi.addCrucibleRecipe("TRANSIRON", new ItemStack(ConfigItems.itemNugget, 3, 0), "nuggetIron", new AspectList().merge(Aspect.METAL, 2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  420 */     ConfigResearch.recipes.put("TransGold", ThaumcraftApi.addCrucibleRecipe("TRANSGOLD", new ItemStack(Items.field_151074_bl, 3, 0), new ItemStack(Items.field_151074_bl), new AspectList().merge(Aspect.METAL, 2).merge(Aspect.GREED, 1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  428 */     if (Config.foundCopperIngot) {
/*  429 */       ConfigResearch.recipes.put("TransCopper", ThaumcraftApi.addCrucibleRecipe("TRANSCOPPER", new ItemStack(ConfigItems.itemNugget, 3, 1), "nuggetCopper", new AspectList().merge(Aspect.METAL, 2).merge(Aspect.EXCHANGE, 1)));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  437 */     if (Config.foundTinIngot) {
/*  438 */       ConfigResearch.recipes.put("TransTin", ThaumcraftApi.addCrucibleRecipe("TRANSTIN", new ItemStack(ConfigItems.itemNugget, 3, 2), "nuggetTin", new AspectList().merge(Aspect.METAL, 2).merge(Aspect.CRYSTAL, 1)));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  446 */     if (Config.foundSilverIngot) {
/*  447 */       ConfigResearch.recipes.put("TransSilver", ThaumcraftApi.addCrucibleRecipe("TRANSSILVER", new ItemStack(ConfigItems.itemNugget, 3, 3), "nuggetSilver", new AspectList().merge(Aspect.METAL, 2).merge(Aspect.GREED, 1)));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  455 */     if (Config.foundLeadIngot) {
/*  456 */       ConfigResearch.recipes.put("TransLead", ThaumcraftApi.addCrucibleRecipe("TRANSLEAD", new ItemStack(ConfigItems.itemNugget, 3, 4), "nuggetLead", new AspectList().merge(Aspect.METAL, 2).merge(Aspect.ORDER, 1)));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  464 */     ConfigResearch.recipes.put("EtherealBloom", ThaumcraftApi.addCrucibleRecipe("ETHEREALBLOOM", new ItemStack(ConfigBlocks.blockCustomPlant, 1, 4), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2), new AspectList().add(Aspect.MAGIC, 16).add(Aspect.PLANT, 16).add(Aspect.HEAL, 16).add(Aspect.TAINT, 8)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  474 */     ConfigResearch.recipes.put("LiquidDeath", ThaumcraftApi.addCrucibleRecipe("LIQUIDDEATH", new ItemStack(ConfigItems.itemBucketDeath), new ItemStack(Items.field_151133_ar), new AspectList().add(Aspect.DEATH, 32).add(Aspect.POISON, 32).add(Aspect.ENTROPY, 32)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  481 */     ItemStack bt = new ItemStack(ConfigItems.itemEssence, 1, 1);
/*  482 */     ((IEssentiaContainerItem)bt.func_77973_b()).setAspects(bt, new AspectList().add(Aspect.TAINT, 8));
/*  483 */     ConfigResearch.recipes.put("BottleTaint", ThaumcraftApi.addCrucibleRecipe("BOTTLETAINT", new ItemStack(ConfigItems.itemBottleTaint), bt, new AspectList().add(Aspect.TAINT, 8).add(Aspect.MAGIC, 8)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  488 */     ConfigResearch.recipes.put("GolemStraw", ThaumcraftApi.addCrucibleRecipe("GOLEMSTRAW", new ItemStack(ConfigItems.itemGolemPlacer, 1, 0), new ItemStack(Blocks.field_150407_cf), new AspectList().add(Aspect.MAN, 4).add(Aspect.MOTION, 4).add(Aspect.SOUL, 4)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  497 */     ConfigResearch.recipes.put("GolemWood", ThaumcraftApi.addCrucibleRecipe("GOLEMWOOD", new ItemStack(ConfigItems.itemGolemPlacer, 1, 1), new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0), new AspectList().add(Aspect.MAN, 4).add(Aspect.MOTION, 4).add(Aspect.SOUL, 4)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  506 */     ConfigResearch.recipes.put("GolemTallow", ThaumcraftApi.addCrucibleRecipe("GOLEMTALLOW", new ItemStack(ConfigItems.itemGolemPlacer, 1, 2), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 5), new AspectList().add(Aspect.MAN, 8).add(Aspect.MOTION, 8).add(Aspect.SOUL, 8)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  515 */     ConfigResearch.recipes.put("GolemClay", ThaumcraftApi.addCrucibleRecipe("GOLEMCLAY", new ItemStack(ConfigItems.itemGolemPlacer, 1, 3), new ItemStack(Blocks.field_150336_V), new AspectList().add(Aspect.MAN, 4).add(Aspect.MOTION, 4).add(Aspect.SOUL, 4)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  524 */     ConfigResearch.recipes.put("GolemFlesh", ThaumcraftApi.addCrucibleRecipe("GOLEMFLESH", new ItemStack(ConfigItems.itemGolemPlacer, 1, 4), new ItemStack(ConfigBlocks.blockTaint, 1, 2), new AspectList().add(Aspect.MAN, 8).add(Aspect.MOTION, 8).add(Aspect.SOUL, 8)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  533 */     ConfigResearch.recipes.put("GolemStone", ThaumcraftApi.addCrucibleRecipe("GOLEMSTONE", new ItemStack(ConfigItems.itemGolemPlacer, 1, 5), new ItemStack(Blocks.field_150417_aV), new AspectList().add(Aspect.MAN, 4).add(Aspect.MOTION, 4).add(Aspect.SOUL, 4)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  542 */     ConfigResearch.recipes.put("GolemIron", ThaumcraftApi.addCrucibleRecipe("GOLEMIRON", new ItemStack(ConfigItems.itemGolemPlacer, 1, 6), new ItemStack(Blocks.field_150339_S), new AspectList().add(Aspect.MAN, 4).add(Aspect.MOTION, 4).add(Aspect.SOUL, 4)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  551 */     ConfigResearch.recipes.put("GolemThaumium", ThaumcraftApi.addCrucibleRecipe("GOLEMTHAUMIUM", new ItemStack(ConfigItems.itemGolemPlacer, 1, 7), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 4), new AspectList().add(Aspect.MAN, 8).add(Aspect.MOTION, 8).add(Aspect.SOUL, 8)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  560 */     ConfigResearch.recipes.put("CoreGather", ThaumcraftApi.addCrucibleRecipe("COREGATHER", new ItemStack(ConfigItems.itemGolemCore, 1, 2), new ItemStack(ConfigItems.itemGolemCore, 1, 100), new AspectList().add(Aspect.GREED, 5).add(Aspect.EARTH, 5)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  566 */     ConfigResearch.recipes.put("CoreFill", ThaumcraftApi.addCrucibleRecipe("COREFILL", new ItemStack(ConfigItems.itemGolemCore, 1, 0), new ItemStack(ConfigItems.itemGolemCore, 1, 100), new AspectList().add(Aspect.HUNGER, 5).add(Aspect.VOID, 5)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  571 */     ConfigResearch.recipes.put("CoreEmpty", ThaumcraftApi.addCrucibleRecipe("COREEMPTY", new ItemStack(ConfigItems.itemGolemCore, 1, 1), new ItemStack(ConfigItems.itemGolemCore, 1, 100), new AspectList().add(Aspect.GREED, 5).add(Aspect.VOID, 5)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  577 */     ConfigResearch.recipes.put("CoreHarvest", ThaumcraftApi.addCrucibleRecipe("COREHARVEST", new ItemStack(ConfigItems.itemGolemCore, 1, 3), new ItemStack(ConfigItems.itemGolemCore, 1, 100), new AspectList().add(Aspect.HARVEST, 5).add(Aspect.CROP, 5)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  583 */     ConfigResearch.recipes.put("CoreGuard", ThaumcraftApi.addCrucibleRecipe("COREGUARD", new ItemStack(ConfigItems.itemGolemCore, 1, 4), new ItemStack(ConfigItems.itemGolemCore, 1, 100), new AspectList().add(Aspect.WEAPON, 5).add(Aspect.TRAP, 5)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  589 */     ConfigResearch.recipes.put("CoreButcher", ThaumcraftApi.addCrucibleRecipe("COREBUTCHER", new ItemStack(ConfigItems.itemGolemCore, 1, 9), new ItemStack(ConfigItems.itemGolemCore, 1, 4), new AspectList().add(Aspect.FLESH, 5).add(Aspect.BEAST, 5)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  596 */     ConfigResearch.recipes.put("CoreLiquid", ThaumcraftApi.addCrucibleRecipe("CORELIQUID", new ItemStack(ConfigItems.itemGolemCore, 1, 5), new ItemStack(ConfigItems.itemGolemCore, 1, 100), new AspectList().add(Aspect.WATER, 5).add(Aspect.VOID, 5)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  604 */     ConfigResearch.recipes.put("BathSalts", ThaumcraftApi.addCrucibleRecipe("BATHSALTS", new ItemStack(ConfigItems.itemBathSalts), new ItemStack(ConfigItems.itemResource, 1, 14), new AspectList().add(Aspect.MIND, 6).add(Aspect.AURA, 6).add(Aspect.ORDER, 6).add(Aspect.HEAL, 6)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  612 */     ConfigResearch.recipes.put("SaneSoap", ThaumcraftApi.addCrucibleRecipe("SANESOAP", new ItemStack(ConfigItems.itemSanitySoap), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 5), new AspectList().add(Aspect.MIND, 16).add(Aspect.ELDRITCH, 16).add(Aspect.ORDER, 16).add(Aspect.HEAL, 16)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initializeArcaneRecipes()
/*      */   {
/*  624 */     for (int a = 0; a < 16; a++) {
/*  625 */       ItemStack banner = new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 8);
/*  626 */       banner.func_77982_d(new NBTTagCompound());
/*  627 */       banner.field_77990_d.func_74774_a("color", (byte)a);
/*  628 */       ConfigResearch.recipes.put("Banner_" + a, ThaumcraftApi.addArcaneCraftingRecipe("BANNERS", banner, new AspectList().add(Aspect.WATER, 5).add(Aspect.EARTH, 5), new Object[] { "WS", "WS", "WB", Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, a), Character.valueOf('S'), "stickWood", Character.valueOf('B'), "slabWood" }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  637 */     ConfigResearch.recipes.put("PrimalCharm", ThaumcraftApi.addArcaneCraftingRecipe("BASICARTIFACE", new ItemStack(ConfigItems.itemResource, 1, 15), new AspectList().add(Aspect.EARTH, 25).add(Aspect.FIRE, 25).add(Aspect.AIR, 25).add(Aspect.WATER, 25).add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25), new Object[] { "123", "ISI", "456", Character.valueOf('S'), new ItemStack(ConfigItems.itemShard, 1, 6), Character.valueOf('I'), Items.field_151043_k, Character.valueOf('1'), new ItemStack(ConfigItems.itemShard, 1, 0), Character.valueOf('2'), new ItemStack(ConfigItems.itemShard, 1, 1), Character.valueOf('3'), new ItemStack(ConfigItems.itemShard, 1, 2), Character.valueOf('4'), new ItemStack(ConfigItems.itemShard, 1, 3), Character.valueOf('5'), new ItemStack(ConfigItems.itemShard, 1, 4), Character.valueOf('6'), new ItemStack(ConfigItems.itemShard, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  654 */     ConfigResearch.recipes.put("ArcaneDoor", ThaumcraftApi.addArcaneCraftingRecipe("WARDEDARCANA", new ItemStack(ConfigItems.itemArcaneDoor), new AspectList().add(Aspect.WATER, 20).add(Aspect.ORDER, 10).add(Aspect.EARTH, 10).add(Aspect.FIRE, 5), new Object[] { "TDT", "DBD", "TDT", Character.valueOf('T'), "ingotThaumium", Character.valueOf('B'), new ItemStack(ConfigItems.itemZombieBrain), Character.valueOf('D'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  668 */     ConfigResearch.recipes.put("WardedGlass", ThaumcraftApi.addArcaneCraftingRecipe("WARDEDARCANA", new ItemStack(ConfigBlocks.blockCosmeticOpaque, 8, 2), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 10).add(Aspect.EARTH, 5).add(Aspect.FIRE, 5), new Object[] { "GGG", "WBW", "GGG", Character.valueOf('B'), new ItemStack(ConfigItems.itemZombieBrain), Character.valueOf('G'), new ItemStack(Blocks.field_150359_w), Character.valueOf('W'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  680 */     ConfigResearch.recipes.put("IronKey", ThaumcraftApi.addArcaneCraftingRecipe("WARDEDARCANA", new ItemStack(ConfigItems.itemKey, 2, 0), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 5), new Object[] { "NNI", "N  ", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('N'), "nuggetIron" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  688 */     ConfigResearch.recipes.put("FluxScrubber", ThaumcraftApi.addArcaneCraftingRecipe("FLUXSCRUB", new ItemStack(ConfigBlocks.blockStoneDevice, 1, 14), new AspectList().add(Aspect.WATER, 16).add(Aspect.ORDER, 16).add(Aspect.AIR, 8), new Object[] { " B ", "GOG", "STS", Character.valueOf('B'), new ItemStack(ConfigBlocks.blockWoodenDevice), Character.valueOf('G'), new ItemStack(Blocks.field_150411_aY), Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube), Character.valueOf('O'), new ItemStack(ConfigItems.itemResource, 1, 8), Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  698 */     if (Config.wardedStone) {
/*  699 */       ConfigResearch.recipes.put("GoldKey", ThaumcraftApi.addArcaneCraftingRecipe("WARDEDARCANA", new ItemStack(ConfigItems.itemKey, 2, 1), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 5), new Object[] { "NNI", "N  ", Character.valueOf('I'), Items.field_151043_k, Character.valueOf('N'), Items.field_151074_bl }));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  710 */       ConfigResearch.recipes.put("ArcanePressurePlate", ThaumcraftApi.addArcaneCraftingRecipe("WARDEDARCANA", new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 2), new AspectList().add(Aspect.WATER, 20).add(Aspect.ORDER, 10).add(Aspect.FIRE, 5).add(Aspect.EARTH, 10), new Object[] { " B ", "TDT", Character.valueOf('T'), new ItemStack(ConfigItems.itemResource, 1, 2), Character.valueOf('B'), new ItemStack(ConfigItems.itemZombieBrain), Character.valueOf('D'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6) }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  740 */     ConfigResearch.recipes.put("NodeStabilizer", ThaumcraftApi.addArcaneCraftingRecipe("NODESTABILIZER", new ItemStack(ConfigBlocks.blockStoneDevice, 1, 9), new AspectList().add(Aspect.WATER, 32).add(Aspect.EARTH, 32).add(Aspect.ORDER, 32), new Object[] { " G ", "QPQ", "SNS", Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7), Character.valueOf('G'), new ItemStack(Items.field_151043_k), Character.valueOf('P'), new ItemStack(Blocks.field_150331_J), Character.valueOf('Q'), new ItemStack(Blocks.field_150371_ca), Character.valueOf('N'), new ItemStack(ConfigItems.itemResource, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  755 */     ConfigResearch.recipes.put("NodeTransducer", ThaumcraftApi.addArcaneCraftingRecipe("VISPOWER", new ItemStack(ConfigBlocks.blockStoneDevice, 1, 11), new AspectList().add(Aspect.FIRE, 32).add(Aspect.AIR, 32).add(Aspect.ENTROPY, 32), new Object[] { "RCR", "ISI", "RAR", Character.valueOf('S'), new ItemStack(ConfigBlocks.blockStoneDevice, 1, 9), Character.valueOf('C'), new ItemStack(Items.field_151132_bS), Character.valueOf('I'), new ItemStack(Items.field_151042_j), Character.valueOf('R'), new ItemStack(Blocks.field_150451_bX), Character.valueOf('A'), new ItemStack(ConfigItems.itemResource, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  770 */     ConfigResearch.recipes.put("NodeRelay", ThaumcraftApi.addArcaneCraftingRecipe("VISPOWER", new ItemStack(ConfigBlocks.blockMetalDevice, 2, 14), new AspectList().add(Aspect.FIRE, 8).add(Aspect.ORDER, 8), new Object[] { " I ", "ISI", " I ", Character.valueOf('I'), new ItemStack(Items.field_151042_j), Character.valueOf('S'), new ItemStack(ConfigItems.itemShard, 1, 6) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  778 */     ConfigResearch.recipes.put("NodeChargeRelay", ThaumcraftApi.addArcaneCraftingRecipe("VISCHARGERELAY", new ItemStack(ConfigBlocks.blockMetalDevice, 1, 2), new AspectList().add(Aspect.FIRE, 16).add(Aspect.ORDER, 16).add(Aspect.AIR, 16), new Object[] { " R ", "W W", "I I", Character.valueOf('I'), new ItemStack(Items.field_151042_j), Character.valueOf('R'), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 14), Character.valueOf('W'), new ItemStack(ConfigItems.itemWandRod, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  788 */     ConfigResearch.recipes.put("FocalManipulator", ThaumcraftApi.addArcaneCraftingRecipe("FOCALMANIPULATION", new ItemStack(ConfigBlocks.blockStoneDevice, 1, 13), new AspectList().add(Aspect.FIRE, 32).add(Aspect.AIR, 32).add(Aspect.ENTROPY, 32).add(Aspect.EARTH, 32).add(Aspect.WATER, 32).add(Aspect.ORDER, 32), new Object[] { "IQI", "SPS", "GTG", Character.valueOf('Q'), new ItemStack(ConfigBlocks.blockSlabStone, 1, 0), Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTable), Character.valueOf('I'), new ItemStack(Items.field_151042_j), Character.valueOf('G'), new ItemStack(Items.field_151043_k), Character.valueOf('P'), new ItemStack(ConfigItems.itemResource, 1, 15) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  801 */     ConfigResearch.recipes.put("GolemFetter", ThaumcraftApi.addArcaneCraftingRecipe("GOLEMFETTER", new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 9), new AspectList().add(Aspect.EARTH, 5).add(Aspect.ORDER, 5), new Object[] { "SSS", "IRI", "BBB", Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), Character.valueOf('I'), new ItemStack(Items.field_151042_j), Character.valueOf('B'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7), Character.valueOf('R'), new ItemStack(Blocks.field_150451_bX) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  822 */     ConfigResearch.recipes.put("ArcaneStone1", ThaumcraftApi.addArcaneCraftingRecipe("ARCANESTONE", new ItemStack(ConfigBlocks.blockCosmeticSolid, 9, 6), new AspectList().add(Aspect.EARTH, 1).add(Aspect.FIRE, 1), new Object[] { "SSS", "SCS", "SSS", Character.valueOf('S'), "stone", Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  830 */     ConfigResearch.recipes.put("ArcaneStone2", GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockCosmeticSolid, 4, 7), new Object[] { "SS", "SS", Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  835 */     ConfigResearch.recipes.put("ArcaneStone3", GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockStairsArcaneStone, 4, 0), new Object[] { "K  ", "KK ", "KKK", Character.valueOf('K'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7) }));
/*      */     
/*      */ 
/*  838 */     ConfigResearch.recipes.put("ArcaneStone4", GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockSlabStone, 6, 0), new Object[] { "KKK", Character.valueOf('K'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  843 */     ConfigResearch.recipes.put("PaveTravel", ThaumcraftApi.addArcaneCraftingRecipe("PAVETRAVEL", new ItemStack(ConfigBlocks.blockCosmeticSolid, 4, 2), new AspectList().add(Aspect.EARTH, 10).add(Aspect.AIR, 10), new Object[] { "SAS", "SBS", Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7), Character.valueOf('A'), new ItemStack(ConfigItems.itemShard, 1, 0), Character.valueOf('B'), new ItemStack(ConfigItems.itemShard, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  859 */     ConfigResearch.recipes.put("ArcaneLamp", ThaumcraftApi.addArcaneCraftingRecipe("ARCANELAMP", new ItemStack(ConfigBlocks.blockMetalDevice, 1, 7), new AspectList().add(Aspect.FIRE, 8).add(Aspect.AIR, 8).add(Aspect.WATER, 4).add(Aspect.ENTROPY, 4), new Object[] { " S ", "IAI", " N ", Character.valueOf('A'), new ItemStack(ConfigBlocks.blockCosmeticOpaque, 1, 0), Character.valueOf('S'), new ItemStack(Blocks.field_150453_bW), Character.valueOf('N'), new ItemStack(ConfigItems.itemResource, 1, 1), Character.valueOf('I'), new ItemStack(Items.field_151042_j) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  879 */     ConfigResearch.recipes.put("ArcaneSpa", ThaumcraftApi.addArcaneCraftingRecipe("ARCANESPA", new ItemStack(ConfigBlocks.blockStoneDevice, 1, 12), new AspectList().add(Aspect.WATER, 16).add(Aspect.ORDER, 8).add(Aspect.EARTH, 4), new Object[] { "QIQ", "SJS", "SPS", Character.valueOf('P'), new ItemStack(Blocks.field_150331_J), Character.valueOf('J'), new ItemStack(ConfigBlocks.blockJar), Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), Character.valueOf('Q'), new ItemStack(Blocks.field_150371_ca), Character.valueOf('I'), new ItemStack(Blocks.field_150411_aY) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  890 */     ConfigResearch.recipes.put("PaveWard", ThaumcraftApi.addArcaneCraftingRecipe("PAVEWARD", new ItemStack(ConfigBlocks.blockCosmeticSolid, 4, 3), new AspectList().add(Aspect.FIRE, 10).add(Aspect.ORDER, 10), new Object[] { "SAS", "SBS", Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7), Character.valueOf('A'), new ItemStack(ConfigItems.itemShard, 1, 1), Character.valueOf('B'), new ItemStack(ConfigItems.itemShard, 1, 4) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  907 */     ConfigResearch.recipes.put("Levitator", ThaumcraftApi.addArcaneCraftingRecipe("LEVITATOR", new ItemStack(ConfigBlocks.blockLifter), new AspectList().add(Aspect.AIR, 10).add(Aspect.EARTH, 5), new Object[] { "WEW", "BNB", "WAW", Character.valueOf('W'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), Character.valueOf('E'), new ItemStack(ConfigItems.itemShard, 1, 3), Character.valueOf('A'), new ItemStack(ConfigItems.itemShard, 1, 0), Character.valueOf('N'), new ItemStack(ConfigItems.itemResource, 1, 1), Character.valueOf('B'), Items.field_151042_j }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  927 */     ConfigResearch.recipes.put("ArcaneEar", ThaumcraftApi.addArcaneCraftingRecipe("ARCANEEAR", new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 1), new AspectList().add(Aspect.AIR, 10).add(Aspect.ORDER, 10), new Object[] { "GIG", "GBG", "WRW", Character.valueOf('W'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), Character.valueOf('R'), Items.field_151137_ax, Character.valueOf('I'), Items.field_151042_j, Character.valueOf('G'), Items.field_151043_k, Character.valueOf('B'), new ItemStack(ConfigItems.itemZombieBrain) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  953 */     ConfigResearch.recipes.put("MirrorGlass", ThaumcraftApi.addShapelessArcaneCraftingRecipe("BASICARTIFACE", new ItemStack(ConfigItems.itemResource, 1, 10), new AspectList().add(Aspect.FIRE, 10).add(Aspect.EARTH, 10), new Object[] { new ItemStack(ConfigItems.itemResource, 1, 3), Blocks.field_150410_aZ }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  960 */     ConfigResearch.recipes.put("BoneBow", ThaumcraftApi.addArcaneCraftingRecipe("BONEBOW", new ItemStack(ConfigItems.itemBowBone), new AspectList().add(Aspect.AIR, 16).add(Aspect.ENTROPY, 32), new Object[] { "SB ", "SEB", "SB ", Character.valueOf('E'), new ItemStack(ConfigItems.itemShard, 1, 5), Character.valueOf('B'), Items.field_151103_aS, Character.valueOf('S'), Items.field_151007_F }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  972 */     Aspect[] pa = { Aspect.AIR, Aspect.FIRE, Aspect.WATER, Aspect.EARTH, Aspect.ORDER, Aspect.ENTROPY };
/*      */     
/*  974 */     for (int a = 0; a < 6; a++) {
/*  975 */       ConfigResearch.recipes.put("PrimalArrow_" + a, ThaumcraftApi.addArcaneCraftingRecipe("PRIMALARROW", new ItemStack(ConfigItems.itemPrimalArrow, 8, a), new AspectList().add(pa[a], 8), new Object[] { "AAA", "ASA", "AAA", Character.valueOf('A'), Items.field_151032_g, Character.valueOf('S'), new ItemStack(ConfigItems.itemShard, 1, a) }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  997 */     ConfigResearch.recipes.put("InfusionMatrix", ThaumcraftApi.addArcaneCraftingRecipe("INFUSION", new ItemStack(ConfigBlocks.blockStoneDevice, 1, 2), new AspectList().add(Aspect.ORDER, 40), new Object[] { "SBS", "BEB", "SBS", Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), Character.valueOf('E'), Items.field_151079_bi, Character.valueOf('B'), new ItemStack(ConfigItems.itemShard, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1016 */     ConfigResearch.recipes.put("ArcanePedestal", ThaumcraftApi.addArcaneCraftingRecipe("INFUSION", new ItemStack(ConfigBlocks.blockStoneDevice, 2, 1), new AspectList().add(Aspect.AIR, 5), new Object[] { "SSS", " S ", "SSS", Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1029 */     ConfigResearch.recipes.put("WardedJar", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA", new ItemStack(ConfigBlocks.blockJar, 1, 0), new AspectList().add(Aspect.WATER, 1), new Object[] { "GWG", "G G", "GGG", Character.valueOf('W'), "slabWood", Character.valueOf('G'), Blocks.field_150410_aZ }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1039 */     ConfigResearch.recipes.put("JarVoid", ThaumcraftApi.addArcaneCraftingRecipe("JARVOID", new ItemStack(ConfigBlocks.blockJar, 1, 3), new AspectList().add(Aspect.WATER, 5).add(Aspect.ENTROPY, 15), new Object[] { "O", "J", "P", Character.valueOf('O'), Blocks.field_150343_Z, Character.valueOf('P'), Items.field_151065_br, Character.valueOf('J'), new ItemStack(ConfigBlocks.blockJar, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1051 */     ArcaneWandRecipe wr = new ArcaneWandRecipe();
/* 1052 */     ThaumcraftApi.getCraftingRecipes().add(wr);
/*      */     
/* 1054 */     ArcaneSceptreRecipe sr = new ArcaneSceptreRecipe();
/* 1055 */     ThaumcraftApi.getCraftingRecipes().add(sr);
/*      */     
/*      */ 
/* 1058 */     ConfigResearch.recipes.put("WandCapGold", ThaumcraftApi.addArcaneCraftingRecipe("CAP_gold", new ItemStack(ConfigItems.itemWandCap, 1, 1), new AspectList().add(Aspect.ORDER, ((WandCap)WandCap.caps.get("gold")).getCraftCost()).add(Aspect.FIRE, ((WandCap)WandCap.caps.get("gold")).getCraftCost()).add(Aspect.AIR, ((WandCap)WandCap.caps.get("gold")).getCraftCost()), new Object[] { "NNN", "N N", Character.valueOf('N'), Items.field_151074_bl }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1077 */     if (Config.foundCopperIngot) {
/* 1078 */       ConfigResearch.recipes.put("WandCapCopper", ThaumcraftApi.addArcaneCraftingRecipe("CAP_copper", new ItemStack(ConfigItems.itemWandCap, 1, 3), new AspectList().add(Aspect.ORDER, ((WandCap)WandCap.caps.get("copper")).getCraftCost()).add(Aspect.FIRE, ((WandCap)WandCap.caps.get("copper")).getCraftCost()).add(Aspect.AIR, ((WandCap)WandCap.caps.get("copper")).getCraftCost()), new Object[] { "NNN", "N N", Character.valueOf('N'), "nuggetCopper" }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1096 */     if (Config.foundSilverIngot) {
/* 1097 */       ConfigResearch.recipes.put("WandCapSilverInert", ThaumcraftApi.addArcaneCraftingRecipe("CAP_silver", new ItemStack(ConfigItems.itemWandCap, 1, 5), new AspectList().add(Aspect.ORDER, ((WandCap)WandCap.caps.get("silver")).getCraftCost()).add(Aspect.FIRE, ((WandCap)WandCap.caps.get("silver")).getCraftCost()).add(Aspect.AIR, ((WandCap)WandCap.caps.get("silver")).getCraftCost()), new Object[] { "NNN", "N N", Character.valueOf('N'), "nuggetSilver" }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1115 */     ConfigResearch.recipes.put("WandCapThaumiumInert", ThaumcraftApi.addArcaneCraftingRecipe("CAP_thaumium", new ItemStack(ConfigItems.itemWandCap, 1, 6), new AspectList().add(Aspect.ORDER, ((WandCap)WandCap.caps.get("thaumium")).getCraftCost()).add(Aspect.FIRE, ((WandCap)WandCap.caps.get("thaumium")).getCraftCost()).add(Aspect.AIR, ((WandCap)WandCap.caps.get("thaumium")).getCraftCost()), new Object[] { "NNN", "N N", Character.valueOf('N'), "nuggetThaumium" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1132 */     ConfigResearch.recipes.put("WandCapVoidInert", ThaumcraftApi.addArcaneCraftingRecipe("CAP_void", new ItemStack(ConfigItems.itemWandCap, 1, 8), new AspectList().add(Aspect.ENTROPY, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 3).add(Aspect.ORDER, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 3).add(Aspect.FIRE, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 2).add(Aspect.AIR, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 2), new Object[] { "NNN", "N N", Character.valueOf('N'), "nuggetVoid" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1144 */     ConfigResearch.recipes.put("WandRodGreatwood", ThaumcraftApi.addArcaneCraftingRecipe("ROD_greatwood", new ItemStack(ConfigItems.itemWandRod, 1, 0), new AspectList().add(Aspect.ENTROPY, ((WandRod)WandRod.rods.get("greatwood")).getCraftCost()), new Object[] { " G", "G ", Character.valueOf('G'), new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1152 */     ConfigResearch.recipes.put("WandRodGreatwoodStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_greatwood_staff", new ItemStack(ConfigItems.itemWandRod, 1, 50), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("greatwood_staff")).getCraftCost()), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 15), Character.valueOf('G'), new ItemStack(ConfigItems.itemWandRod, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1161 */     ConfigResearch.recipes.put("WandRodObsidianStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_obsidian_staff", new ItemStack(ConfigItems.itemWandRod, 1, 51), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("obsidian_staff")).getCraftCost()), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 15), Character.valueOf('G'), new ItemStack(ConfigItems.itemWandRod, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1170 */     ConfigResearch.recipes.put("WandRodSilverwoodStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_silverwood_staff", new ItemStack(ConfigItems.itemWandRod, 1, 52), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("silverwood_staff")).getCraftCost()), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 15), Character.valueOf('G'), new ItemStack(ConfigItems.itemWandRod, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1179 */     ConfigResearch.recipes.put("WandRodIceStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_ice_staff", new ItemStack(ConfigItems.itemWandRod, 1, 53), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("ice_staff")).getCraftCost()), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 15), Character.valueOf('G'), new ItemStack(ConfigItems.itemWandRod, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1188 */     ConfigResearch.recipes.put("WandRodQuartzStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_quartz_staff", new ItemStack(ConfigItems.itemWandRod, 1, 54), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("quartz_staff")).getCraftCost()), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 15), Character.valueOf('G'), new ItemStack(ConfigItems.itemWandRod, 1, 4) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1197 */     ConfigResearch.recipes.put("WandRodReedStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_reed_staff", new ItemStack(ConfigItems.itemWandRod, 1, 55), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("reed_staff")).getCraftCost()), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 15), Character.valueOf('G'), new ItemStack(ConfigItems.itemWandRod, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1206 */     ConfigResearch.recipes.put("WandRodBlazeStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_blaze_staff", new ItemStack(ConfigItems.itemWandRod, 1, 56), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("blaze_staff")).getCraftCost()), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 15), Character.valueOf('G'), new ItemStack(ConfigItems.itemWandRod, 1, 6) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1215 */     ConfigResearch.recipes.put("WandRodBoneStaff", ThaumcraftApi.addArcaneCraftingRecipe("ROD_bone_staff", new ItemStack(ConfigItems.itemWandRod, 1, 57), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("bone_staff")).getCraftCost()), new Object[] { "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 15), Character.valueOf('G'), new ItemStack(ConfigItems.itemWandRod, 1, 7) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1226 */     ConfigResearch.recipes.put("FocusFire", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSFIRE", new ItemStack(ConfigItems.itemFocusFire), new AspectList().add(Aspect.FIRE, 20).add(Aspect.ENTROPY, 10), new Object[] { "CQC", "Q#Q", "CQC", Character.valueOf('#'), Items.field_151059_bz, Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1237 */     ConfigResearch.recipes.put("FocusFrost", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSFROST", new ItemStack(ConfigItems.itemFocusFrost), new AspectList().add(Aspect.WATER, 10).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10), new Object[] { "CQC", "Q#Q", "CQC", Character.valueOf('#'), Items.field_151045_i, Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1249 */     ConfigResearch.recipes.put("FocusShock", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSSHOCK", new ItemStack(ConfigItems.itemFocusShock), new AspectList().add(Aspect.AIR, 10).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10), new Object[] { "CQC", "Q#Q", "CQC", Character.valueOf('#'), Items.field_151174_bG, Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1261 */     ConfigResearch.recipes.put("FocusTrade", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSTRADE", new ItemStack(ConfigItems.itemFocusTrade), new AspectList().add(Aspect.ORDER, 15).add(Aspect.ENTROPY, 15).add(Aspect.EARTH, 10), new Object[] { "CQE", "Q#Q", "CQE", Character.valueOf('#'), new ItemStack(ConfigItems.itemResource, 1, 3), Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 6), Character.valueOf('E'), new ItemStack(ConfigItems.itemShard, 1, 6) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1276 */     ConfigResearch.recipes.put("FocusExcavation", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSEXCAVATION", new ItemStack(ConfigItems.itemFocusExcavation), new AspectList().add(Aspect.EARTH, 20).add(Aspect.ENTROPY, 5).add(Aspect.ORDER, 5), new Object[] { "CQC", "Q#Q", "CQC", Character.valueOf('#'), "gemEmerald", Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1288 */     ConfigResearch.recipes.put("FocusPrimal", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSPRIMAL", new ItemStack(ConfigItems.itemFocusPrimal), new AspectList().add(Aspect.EARTH, 25).add(Aspect.ENTROPY, 25).add(Aspect.ORDER, 25).add(Aspect.AIR, 25).add(Aspect.FIRE, 25).add(Aspect.WATER, 25), new Object[] { "CQC", "Q#Q", "CQC", Character.valueOf('#'), new ItemStack(ConfigItems.itemResource, 1, 15), Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), Items.field_151045_i }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1303 */     ConfigResearch.recipes.put("FocusPouch", ThaumcraftApi.addArcaneCraftingRecipe("FOCUSPOUCH", new ItemStack(ConfigItems.itemFocusPouch), new AspectList().add(Aspect.EARTH, 10).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10), new Object[] { "LGL", "LBL", "LLL", Character.valueOf('B'), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 2), Character.valueOf('L'), Items.field_151116_aA, Character.valueOf('G'), Items.field_151043_k }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1320 */     ConfigResearch.recipes.put("Deconstructor", ThaumcraftApi.addArcaneCraftingRecipe("DECONSTRUCTOR", new ItemStack(ConfigBlocks.blockTable, 1, 14), new AspectList().add(Aspect.ENTROPY, 20), new Object[] { " S ", "ATP", Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTable, 1, 0), Character.valueOf('S'), new ItemStack(ConfigItems.itemThaumometer), Character.valueOf('P'), new ItemStack(Items.field_151005_D), Character.valueOf('A'), new ItemStack(Items.field_151006_E) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1335 */     ConfigResearch.recipes.put("ArcaneBoreBase", ThaumcraftApi.addArcaneCraftingRecipe("ARCANEBORE", new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 4), new AspectList().add(Aspect.AIR, 10).add(Aspect.ORDER, 10), new Object[] { "WIW", "IDI", "WIW", Character.valueOf('W'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), Character.valueOf('I'), Items.field_151042_j, Character.valueOf('D'), Blocks.field_150367_z }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1351 */     ConfigResearch.recipes.put("EnchantedFabric", ThaumcraftApi.addArcaneCraftingRecipe("ENCHFABRIC", new ItemStack(ConfigItems.itemResource, 1, 7), new AspectList().add(Aspect.AIR, 1).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), new Object[] { " S ", "SCS", " S ", Character.valueOf('S'), new ItemStack(Items.field_151007_F, 1, 32767), Character.valueOf('C'), new ItemStack(Blocks.field_150325_L, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1370 */     ConfigResearch.recipes.put("RobeChest", ThaumcraftApi.addArcaneCraftingRecipe("ENCHFABRIC", new ItemStack(ConfigItems.itemChestRobe, 1), new AspectList().add(Aspect.AIR, 5), new Object[] { "I I", "III", "III", Character.valueOf('I'), new ItemStack(ConfigItems.itemResource, 1, 7) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1376 */     ConfigResearch.recipes.put("RobeLegs", ThaumcraftApi.addArcaneCraftingRecipe("ENCHFABRIC", new ItemStack(ConfigItems.itemLegsRobe, 1), new AspectList().add(Aspect.WATER, 5), new Object[] { "III", "I I", "I I", Character.valueOf('I'), new ItemStack(ConfigItems.itemResource, 1, 7) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1382 */     ConfigResearch.recipes.put("RobeBoots", ThaumcraftApi.addArcaneCraftingRecipe("ENCHFABRIC", new ItemStack(ConfigItems.itemBootsRobe, 1), new AspectList().add(Aspect.EARTH, 3), new Object[] { "I I", "I I", Character.valueOf('I'), new ItemStack(ConfigItems.itemResource, 1, 7) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1389 */     GameRegistry.addRecipe(new RecipesRobeArmorDyes());
/*      */     
/*      */ 
/*      */ 
/* 1393 */     ConfigResearch.recipes.put("Goggles", ThaumcraftApi.addArcaneCraftingRecipe("GOGGLES", new ItemStack(ConfigItems.itemGoggles), new AspectList().add(Aspect.AIR, 5).add(Aspect.FIRE, 5).add(Aspect.WATER, 5).add(Aspect.EARTH, 5).add(Aspect.ENTROPY, 3).add(Aspect.ORDER, 3), new Object[] { "LGL", "L L", "TGT", Character.valueOf('T'), ConfigItems.itemThaumometer, Character.valueOf('G'), Items.field_151043_k, Character.valueOf('L'), Items.field_151116_aA }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1408 */     ConfigResearch.recipes.put("HungryChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHEST", new ItemStack(ConfigBlocks.blockChestHungry), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), new Object[] { "WTW", "W W", "WWW", Character.valueOf('W'), "plankWood", Character.valueOf('T'), Blocks.field_150415_aT }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1419 */     ConfigResearch.recipes.put("GolemBell", ThaumcraftApi.addArcaneCraftingRecipe("GOLEMBELL", new ItemStack(ConfigItems.itemGolemBell), new AspectList().add(Aspect.ORDER, 5), new Object[] { " QQ", " QQ", "S  ", Character.valueOf('S'), "stickWood", Character.valueOf('Q'), Items.field_151128_bU }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1429 */     ConfigResearch.recipes.put("CoreBlank", ThaumcraftApi.addArcaneCraftingRecipe("COREGATHER", new ItemStack(ConfigItems.itemGolemCore, 1, 100), new AspectList().add(Aspect.ORDER, 5).add(Aspect.FIRE, 5), new Object[] { " C ", "CNC", " C ", Character.valueOf('C'), Items.field_151118_aC, Character.valueOf('N'), new ItemStack(ConfigItems.itemResource, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1451 */     ConfigResearch.recipes.put("UpgradeAir", ThaumcraftApi.addArcaneCraftingRecipe("UPGRADEAIR", new ItemStack(ConfigItems.itemGolemUpgrade, 1, 0), new AspectList().add(Aspect.AIR, 10), new Object[] { "NNN", "NCN", "NNN", Character.valueOf('N'), Items.field_151074_bl, Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1459 */     ConfigResearch.recipes.put("UpgradeEarth", ThaumcraftApi.addArcaneCraftingRecipe("UPGRADEEARTH", new ItemStack(ConfigItems.itemGolemUpgrade, 1, 1), new AspectList().add(Aspect.EARTH, 10), new Object[] { "NNN", "NCN", "NNN", Character.valueOf('N'), Items.field_151074_bl, Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1467 */     ConfigResearch.recipes.put("UpgradeFire", ThaumcraftApi.addArcaneCraftingRecipe("UPGRADEFIRE", new ItemStack(ConfigItems.itemGolemUpgrade, 1, 2), new AspectList().add(Aspect.FIRE, 10), new Object[] { "NNN", "NCN", "NNN", Character.valueOf('N'), Items.field_151074_bl, Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1475 */     ConfigResearch.recipes.put("UpgradeWater", ThaumcraftApi.addArcaneCraftingRecipe("UPGRADEWATER", new ItemStack(ConfigItems.itemGolemUpgrade, 1, 3), new AspectList().add(Aspect.WATER, 10), new Object[] { "NNN", "NCN", "NNN", Character.valueOf('N'), Items.field_151074_bl, Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1483 */     ConfigResearch.recipes.put("UpgradeOrder", ThaumcraftApi.addArcaneCraftingRecipe("UPGRADEORDER", new ItemStack(ConfigItems.itemGolemUpgrade, 1, 4), new AspectList().add(Aspect.ORDER, 10), new Object[] { "NNN", "NCN", "NNN", Character.valueOf('N'), Items.field_151074_bl, Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 4) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1491 */     ConfigResearch.recipes.put("UpgradeEntropy", ThaumcraftApi.addArcaneCraftingRecipe("UPGRADEENTROPY", new ItemStack(ConfigItems.itemGolemUpgrade, 1, 5), new AspectList().add(Aspect.ENTROPY, 10), new Object[] { "NNN", "NCN", "NNN", Character.valueOf('N'), Items.field_151074_bl, Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1500 */     ConfigResearch.recipes.put("TinyHat", ThaumcraftApi.addArcaneCraftingRecipe("TINYHAT", new ItemStack(ConfigItems.itemGolemDecoration, 1, 0), new AspectList().add(Aspect.ORDER, 8).add(Aspect.FIRE, 8), new Object[] { " C ", " G ", "CCC", Character.valueOf('C'), new ItemStack(Blocks.field_150325_L, 1, 15), Character.valueOf('G'), Items.field_151043_k }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1511 */     ConfigResearch.recipes.put("TinyFez", ThaumcraftApi.addArcaneCraftingRecipe("TINYFEZ", new ItemStack(ConfigItems.itemGolemDecoration, 1, 3), new AspectList().add(Aspect.WATER, 4).add(Aspect.EARTH, 4), new Object[] { "CCS", "CCS", "  S", Character.valueOf('C'), new ItemStack(Blocks.field_150325_L, 1, 14), Character.valueOf('S'), Items.field_151007_F }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1522 */     ConfigResearch.recipes.put("TinyBowtie", ThaumcraftApi.addArcaneCraftingRecipe("TINYBOWTIE", new ItemStack(ConfigItems.itemGolemDecoration, 1, 2), new AspectList().add(Aspect.AIR, 4).add(Aspect.ORDER, 4), new Object[] { "CSC", "C C", Character.valueOf('C'), new ItemStack(Blocks.field_150325_L, 1, 15), Character.valueOf('S'), Items.field_151007_F }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1532 */     ConfigResearch.recipes.put("TinyGlasses", ThaumcraftApi.addArcaneCraftingRecipe("TINYGLASSES", new ItemStack(ConfigItems.itemGolemDecoration, 1, 1), new AspectList().add(Aspect.AIR, 4).add(Aspect.WATER, 4), new Object[] { "GIG", Character.valueOf('G'), Blocks.field_150359_w, Character.valueOf('I'), Items.field_151042_j }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1542 */     ConfigResearch.recipes.put("TinyDart", ThaumcraftApi.addArcaneCraftingRecipe("TINYDART", new ItemStack(ConfigItems.itemGolemDecoration, 1, 4), new AspectList().add(Aspect.AIR, 4).add(Aspect.FIRE, 4), new Object[] { "AIA", "ADA", "AIA", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('D'), Blocks.field_150367_z, Character.valueOf('A'), Items.field_151032_g }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1551 */     ConfigResearch.recipes.put("TinyVisor", ThaumcraftApi.addArcaneCraftingRecipe("TINYVISOR", new ItemStack(ConfigItems.itemGolemDecoration, 1, 5), new AspectList().add(Aspect.EARTH, 4).add(Aspect.WATER, 4), new Object[] { "IHI", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('H'), new ItemStack(Items.field_151028_Y, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1558 */     ConfigResearch.recipes.put("TinyArmor", ThaumcraftApi.addArcaneCraftingRecipe("TINYARMOR", new ItemStack(ConfigItems.itemGolemDecoration, 1, 6), new AspectList().add(Aspect.EARTH, 8), new Object[] { "I I", "IAI", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('A'), new ItemStack(Items.field_151030_Z, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1565 */     ConfigResearch.recipes.put("TinyHammer", ThaumcraftApi.addArcaneCraftingRecipe("TINYHAMMER", new ItemStack(ConfigItems.itemGolemDecoration, 1, 7), new AspectList().add(Aspect.EARTH, 4).add(Aspect.FIRE, 4), new Object[] { "III", "III", " I ", Character.valueOf('I'), Items.field_151042_j }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1571 */     ConfigResearch.recipes.put("Filter", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA", new ItemStack(ConfigItems.itemResource, 2, 8), new AspectList().add(Aspect.ORDER, 5).add(Aspect.WATER, 5), new Object[] { "GWG", Character.valueOf('G'), Items.field_151043_k, Character.valueOf('W'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 7) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1580 */     ConfigResearch.recipes.put("AlchemyFurnace", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA", new ItemStack(ConfigBlocks.blockStoneDevice, 1, 0), new AspectList().add(Aspect.FIRE, 5).add(Aspect.WATER, 5), new Object[] { "SCS", "SFS", "SSS", Character.valueOf('C'), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 0), Character.valueOf('F'), Blocks.field_150460_al, Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1591 */     ConfigResearch.recipes.put("Alembic", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA", new ItemStack(ConfigBlocks.blockMetalDevice, 1, 1), new AspectList().add(Aspect.AIR, 5).add(Aspect.WATER, 5), new Object[] { "FIG", "IBI", "I I", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('B'), Items.field_151133_ar, Character.valueOf('G'), Items.field_151043_k, Character.valueOf('F'), new ItemStack(ConfigItems.itemResource, 1, 8), Character.valueOf('L'), new ItemStack(ConfigBlocks.blockMagicalLeaves, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1614 */     ConfigResearch.recipes.put("Bellows", ThaumcraftApi.addArcaneCraftingRecipe("BELLOWS", new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 0), new AspectList().add(Aspect.AIR, 10).add(Aspect.ORDER, 5), new Object[] { "WW ", "LCI", "WW ", Character.valueOf('W'), "plankWood", Character.valueOf('C'), new ItemStack(ConfigItems.itemShard, 1, 0), Character.valueOf('I'), Items.field_151042_j, Character.valueOf('L'), Items.field_151116_aA }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1628 */     ConfigResearch.recipes.put("Tube", ThaumcraftApi.addArcaneCraftingRecipe("TUBES", new ItemStack(ConfigBlocks.blockTube, 8, 0), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 5), new Object[] { " Q ", "IGI", " B ", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('B'), Items.field_151074_bl, Character.valueOf('G'), Blocks.field_150359_w, Character.valueOf('Q'), new ItemStack(ConfigItems.itemNugget, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1639 */     ConfigResearch.recipes.put("Resonator", ThaumcraftApi.addArcaneCraftingRecipe("TUBES", new ItemStack(ConfigItems.itemResonator), new AspectList().add(Aspect.WATER, 5).add(Aspect.AIR, 5), new Object[] { "I I", "INI", " S ", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('N'), Items.field_151128_bU, Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1648 */     ConfigResearch.recipes.put("TubeValve", ThaumcraftApi.addShapelessArcaneCraftingRecipe("TUBES", new ItemStack(ConfigBlocks.blockTube, 1, 1), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 5), new Object[] { new ItemStack(ConfigBlocks.blockTube, 1, 0), new ItemStack(Blocks.field_150442_at) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1656 */     ConfigResearch.recipes.put("TubeFilter", ThaumcraftApi.addShapelessArcaneCraftingRecipe("TUBEFILTER", new ItemStack(ConfigBlocks.blockTube, 1, 3), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 16), new Object[] { new ItemStack(ConfigBlocks.blockTube, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 8) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1667 */     ConfigResearch.recipes.put("TubeRestrict", ThaumcraftApi.addShapelessArcaneCraftingRecipe("TUBEFILTER", new ItemStack(ConfigBlocks.blockTube, 1, 5), new AspectList().add(Aspect.WATER, 5).add(Aspect.EARTH, 16), new Object[] { new ItemStack(ConfigBlocks.blockTube, 1, 0), "stone" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1675 */     ConfigResearch.recipes.put("TubeOneway", ThaumcraftApi.addShapelessArcaneCraftingRecipe("TUBEFILTER", new ItemStack(ConfigBlocks.blockTube, 1, 6), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 8).add(Aspect.ENTROPY, 8), new Object[] { new ItemStack(ConfigBlocks.blockTube, 1, 0), "dyeBlue" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1685 */     ConfigResearch.recipes.put("TubeBuffer", ThaumcraftApi.addArcaneCraftingRecipe("CENTRIFUGE", new ItemStack(ConfigBlocks.blockTube, 1, 4), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 5), new Object[] { "PVP", "T T", "PRP", Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube, 1, 0), Character.valueOf('V'), new ItemStack(ConfigBlocks.blockTube, 1, 1), Character.valueOf('R'), new ItemStack(ConfigBlocks.blockTube, 1, 5), Character.valueOf('P'), new ItemStack(ConfigItems.itemEssence, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1697 */     ConfigResearch.recipes.put("AlchemicalConstruct", ThaumcraftApi.addArcaneCraftingRecipe("DISTILESSENTIA", new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 5), new Object[] { "VTF", "TWT", "FTV", Character.valueOf('W'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), Character.valueOf('V'), new ItemStack(ConfigBlocks.blockTube, 1, 1), Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube, 1, 0), Character.valueOf('F'), new ItemStack(ConfigItems.itemResource, 1, 8) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1709 */     ConfigResearch.recipes.put("AdvAlchemyConstruct", ThaumcraftApi.addArcaneCraftingRecipe("ADVALCHEMYFURNACE", new ItemStack(ConfigBlocks.blockMetalDevice, 4, 3), new AspectList().add(Aspect.WATER, 10).add(Aspect.ORDER, 30).add(Aspect.EARTH, 10), new Object[] { "VAV", "APA", "VAV", Character.valueOf('A'), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9), Character.valueOf('V'), new ItemStack(ConfigItems.itemResource, 1, 16), Character.valueOf('P'), new ItemStack(ConfigItems.itemEldritchObject, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1719 */     ConfigResearch.recipes.put("Centrifuge", ThaumcraftApi.addArcaneCraftingRecipe("CENTRIFUGE", new ItemStack(ConfigBlocks.blockTube, 1, 2), new AspectList().add(Aspect.WATER, 5).add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 5), new Object[] { " T ", "ACP", " T ", Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube, 1, 0), Character.valueOf('P'), new ItemStack(Blocks.field_150331_J), Character.valueOf('A'), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 1), Character.valueOf('C'), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1729 */     ConfigResearch.recipes.put("EssentiaCrystalizer", ThaumcraftApi.addArcaneCraftingRecipe("ESSENTIACRYSTAL", new ItemStack(ConfigBlocks.blockTube, 1, 7), new AspectList().add(Aspect.WATER, 5).add(Aspect.EARTH, 15).add(Aspect.ORDER, 5), new Object[] { "IDI", "QCQ", "WTW", Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube, 1, 0), Character.valueOf('D'), new ItemStack(Blocks.field_150367_z), Character.valueOf('Q'), new ItemStack(ConfigItems.itemShard, 1, 6), Character.valueOf('I'), "ingotIron", Character.valueOf('W'), "plankWood", Character.valueOf('C'), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1740 */     ConfigResearch.recipes.put("MnemonicMatrix", ThaumcraftApi.addArcaneCraftingRecipe("THAUMATORIUM", new ItemStack(ConfigBlocks.blockMetalDevice, 1, 12), new AspectList().add(Aspect.FIRE, 5).add(Aspect.WATER, 5).add(Aspect.ORDER, 5), new Object[] { "IAI", "ABA", "IAI", Character.valueOf('B'), new ItemStack(ConfigItems.itemZombieBrain), Character.valueOf('A'), new ItemStack(ConfigItems.itemResource, 1, 6), Character.valueOf('I'), new ItemStack(Items.field_151042_j) }));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initializeInfusionEnchantmentRecipes()
/*      */   {
/* 1759 */     ConfigResearch.recipes.put("InfEnchRepair", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77331_b[ThaumcraftApi.enchantRepair], 4, new AspectList().add(Aspect.MAGIC, 8).add(Aspect.CRAFT, 10).add(Aspect.ORDER, 10), new ItemStack[] { new ItemStack(Blocks.field_150467_bQ), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1775 */     ConfigResearch.recipes.put("InfEnchHaste", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77331_b[ThaumcraftApi.enchantHaste], 3, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.TRAVEL, 8).add(Aspect.FLIGHT, 8), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1794 */     ConfigResearch.recipes.put("InfEnch0", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77332_c, 1, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.ARMOR, 8), new ItemStack[] { new ItemStack(Items.field_151042_j), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1806 */     ConfigResearch.recipes.put("InfEnch1", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77329_d, 1, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.ARMOR, 4).add(Aspect.FIRE, 4), new ItemStack[] { new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151064_bs), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1820 */     ConfigResearch.recipes.put("InfEnch2", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77327_f, 1, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.ARMOR, 4).add(Aspect.ENTROPY, 4), new ItemStack[] { new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151016_H), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1835 */     ConfigResearch.recipes.put("InfEnch3", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77328_g, 1, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.ARMOR, 4).add(Aspect.FLIGHT, 4), new ItemStack[] { new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151032_g), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1850 */     ConfigResearch.recipes.put("InfEnch4", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77330_e, 1, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.AIR, 4).add(Aspect.FLIGHT, 4), new ItemStack[] { new ItemStack(Items.field_151008_G), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1864 */     ConfigResearch.recipes.put("InfEnch5", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77340_h, 2, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.AIR, 8).add(Aspect.WATER, 8), new ItemStack[] { new ItemStack(Items.field_151120_aE), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1875 */     ConfigResearch.recipes.put("InfEnch6", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77341_i, 2, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.MOTION, 8).add(Aspect.WATER, 8), new ItemStack[] { new ItemStack(Items.field_151120_aE), new ItemStack(Items.field_151123_aH), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1889 */     ConfigResearch.recipes.put("InfEnch7", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_92091_k, 2, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 8).add(Aspect.PLANT, 8), new ItemStack[] { new ItemStack(Blocks.field_150330_I), new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1903 */     ConfigResearch.recipes.put("InfEnch8", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77338_j, 2, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 8), new ItemStack[] { new ItemStack(Items.field_151040_l), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1915 */     ConfigResearch.recipes.put("InfEnch9", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77339_k, 2, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 4).add(Aspect.UNDEAD, 4), new ItemStack[] { new ItemStack(Items.field_151040_l), new ItemStack(Items.field_151114_aO), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1930 */     ConfigResearch.recipes.put("InfEnch10", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77336_l, 2, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 4).add(Aspect.BEAST, 4), new ItemStack[] { new ItemStack(Items.field_151040_l), new ItemStack(ConfigItems.itemResource, 1, 6), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1945 */     ConfigResearch.recipes.put("InfEnch11", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77337_m, 1, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 3).add(Aspect.MOTION, 3), new ItemStack[] { new ItemStack(Blocks.field_150331_J), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1959 */     ConfigResearch.recipes.put("InfEnch12", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77334_n, 3, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 4).add(Aspect.FIRE, 8), new ItemStack[] { new ItemStack(Items.field_151040_l), new ItemStack(Items.field_151065_br), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1973 */     ConfigResearch.recipes.put("InfEnch13", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77335_o, 3, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 4).add(Aspect.GREED, 8), new ItemStack[] { new ItemStack(Items.field_151040_l), new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1987 */     ConfigResearch.recipes.put("InfEnch14", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77349_p, 2, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.TOOL, 4).add(Aspect.ORDER, 4), new ItemStack[] { new ItemStack(Items.field_151035_b), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2000 */     ConfigResearch.recipes.put("InfEnch15", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77348_q, 5, new AspectList().add(Aspect.MAGIC, 16).add(Aspect.TOOL, 16).add(Aspect.ORDER, 16).add(Aspect.HARVEST, 16).add(Aspect.MINE, 16), new ItemStack[] { new ItemStack(Items.field_151035_b), new ItemStack(Blocks.field_150321_G), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2016 */     ConfigResearch.recipes.put("InfEnch16", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77347_r, 2, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.TOOL, 4).add(Aspect.ORDER, 8), new ItemStack[] { new ItemStack(Items.field_151035_b), new ItemStack(Blocks.field_150343_Z), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2030 */     ConfigResearch.recipes.put("InfEnch17", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77346_s, 3, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.TOOL, 4).add(Aspect.GREED, 8), new ItemStack[] { new ItemStack(Items.field_151035_b), new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2044 */     ConfigResearch.recipes.put("InfEnch18", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77345_t, 2, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 8), new ItemStack[] { new ItemStack(Items.field_151031_f), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2056 */     ConfigResearch.recipes.put("InfEnch19", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77344_u, 2, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 3).add(Aspect.MOTION, 3), new ItemStack[] { new ItemStack(Blocks.field_150331_J), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2070 */     ConfigResearch.recipes.put("InfEnch20", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77343_v, 3, new AspectList().add(Aspect.MAGIC, 4).add(Aspect.WEAPON, 4).add(Aspect.FIRE, 8), new ItemStack[] { new ItemStack(Items.field_151031_f), new ItemStack(Items.field_151065_br), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2084 */     ConfigResearch.recipes.put("InfEnch21", ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", Enchantment.field_77342_w, 5, new AspectList().add(Aspect.MAGIC, 8).add(Aspect.WEAPON, 16).add(Aspect.VOID, 16).add(Aspect.EXCHANGE, 16), new ItemStack[] { new ItemStack(Items.field_151031_f), new ItemStack(Items.field_151032_g), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initializeInfusionRecipes()
/*      */   {
/* 2117 */     if (Config.foundSilverIngot) {
/* 2118 */       ConfigResearch.recipes.put("WandCapSilver", ThaumcraftApi.addInfusionCraftingRecipe("CAP_silver", new ItemStack(ConfigItems.itemWandCap, 1, 4), 4, new AspectList().add(Aspect.ENERGY, ((WandCap)WandCap.caps.get("silver")).getCraftCost() * 2).add(Aspect.AURA, ((WandCap)WandCap.caps.get("silver")).getCraftCost()), new ItemStack(ConfigItems.itemWandCap, 1, 5), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2138 */     ConfigResearch.recipes.put("WandCapThaumium", ThaumcraftApi.addInfusionCraftingRecipe("CAP_thaumium", new ItemStack(ConfigItems.itemWandCap, 1, 2), 5, new AspectList().add(Aspect.ENERGY, ((WandCap)WandCap.caps.get("thaumium")).getCraftCost() * 2).add(Aspect.AURA, ((WandCap)WandCap.caps.get("thaumium")).getCraftCost()), new ItemStack(ConfigItems.itemWandCap, 1, 6), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2160 */     ConfigResearch.recipes.put("WandCapVoid", ThaumcraftApi.addInfusionCraftingRecipe("CAP_void", new ItemStack(ConfigItems.itemWandCap, 1, 7), 8, new AspectList().add(Aspect.ENERGY, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 2).add(Aspect.VOID, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 2).add(Aspect.ELDRITCH, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 2).add(Aspect.AURA, ((WandCap)WandCap.caps.get("void")).getCraftCost() * 2), new ItemStack(ConfigItems.itemWandCap, 1, 8), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 14) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2177 */     ConfigResearch.recipes.put("WandRodObsidian", ThaumcraftApi.addInfusionCraftingRecipe("ROD_obsidian", new ItemStack(ConfigItems.itemWandRod, 1, 1), 3, new AspectList().add(Aspect.EARTH, ((WandRod)WandRod.rods.get("obsidian")).getCraftCost() * 2).add(Aspect.MAGIC, ((WandRod)WandRod.rods.get("obsidian")).getCraftCost()).add(Aspect.DARKNESS, ((WandRod)WandRod.rods.get("blaze")).getCraftCost()), new ItemStack(Blocks.field_150343_Z), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2196 */     ConfigResearch.recipes.put("WandRodIce", ThaumcraftApi.addInfusionCraftingRecipe("ROD_ice", new ItemStack(ConfigItems.itemWandRod, 1, 3), 3, new AspectList().add(Aspect.WATER, ((WandRod)WandRod.rods.get("ice")).getCraftCost() * 2).add(Aspect.MAGIC, ((WandRod)WandRod.rods.get("ice")).getCraftCost()).add(Aspect.COLD, ((WandRod)WandRod.rods.get("blaze")).getCraftCost()), new ItemStack(Blocks.field_150432_aD), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2217 */     ConfigResearch.recipes.put("WandRodQuartz", ThaumcraftApi.addInfusionCraftingRecipe("ROD_quartz", new ItemStack(ConfigItems.itemWandRod, 1, 4), 3, new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("quartz")).getCraftCost() * 2).add(Aspect.MAGIC, ((WandRod)WandRod.rods.get("quartz")).getCraftCost()).add(Aspect.CRYSTAL, ((WandRod)WandRod.rods.get("blaze")).getCraftCost()), new ItemStack(Blocks.field_150371_ca), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 4) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2236 */     ConfigResearch.recipes.put("WandRodReed", ThaumcraftApi.addInfusionCraftingRecipe("ROD_reed", new ItemStack(ConfigItems.itemWandRod, 1, 5), 3, new AspectList().add(Aspect.AIR, ((WandRod)WandRod.rods.get("reed")).getCraftCost() * 2).add(Aspect.MAGIC, ((WandRod)WandRod.rods.get("reed")).getCraftCost()).add(Aspect.MOTION, ((WandRod)WandRod.rods.get("blaze")).getCraftCost()), new ItemStack(Items.field_151120_aE), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2257 */     ConfigResearch.recipes.put("WandRodBlaze", ThaumcraftApi.addInfusionCraftingRecipe("ROD_blaze", new ItemStack(ConfigItems.itemWandRod, 1, 6), 3, new AspectList().add(Aspect.FIRE, ((WandRod)WandRod.rods.get("blaze")).getCraftCost() * 2).add(Aspect.MAGIC, ((WandRod)WandRod.rods.get("blaze")).getCraftCost()).add(Aspect.BEAST, ((WandRod)WandRod.rods.get("blaze")).getCraftCost()), new ItemStack(Items.field_151072_bj), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2276 */     ConfigResearch.recipes.put("WandRodBone", ThaumcraftApi.addInfusionCraftingRecipe("ROD_bone", new ItemStack(ConfigItems.itemWandRod, 1, 7), 3, new AspectList().add(Aspect.ENTROPY, ((WandRod)WandRod.rods.get("bone")).getCraftCost() * 2).add(Aspect.MAGIC, ((WandRod)WandRod.rods.get("bone")).getCraftCost()).add(Aspect.UNDEAD, ((WandRod)WandRod.rods.get("blaze")).getCraftCost()), new ItemStack(Items.field_151103_aS), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2297 */     ConfigResearch.recipes.put("WandRodSilverwood", ThaumcraftApi.addInfusionCraftingRecipe("ROD_silverwood", new ItemStack(ConfigItems.itemWandRod, 1, 2), 5, new AspectList().add(Aspect.AIR, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.FIRE, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.WATER, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.EARTH, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.ORDER, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.ENTROPY, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()).add(Aspect.MAGIC, ((WandRod)WandRod.rods.get("silverwood")).getCraftCost()), new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(ConfigItems.itemShard, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2334 */     ConfigResearch.recipes.put("WandRodPrimalStaff", ThaumcraftApi.addInfusionCraftingRecipe("ROD_primal_staff", new ItemStack(ConfigItems.itemWandRod, 1, 100), 8, new AspectList().add(Aspect.AIR, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.FIRE, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.WATER, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.EARTH, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.ORDER, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.ENTROPY, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost()).add(Aspect.MAGIC, ((WandRod)WandRod.rods.get("primal_staff")).getCraftCost() * 2), new ItemStack(ConfigItems.itemWandRod, 1, 2), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWandRod, 1, 1), new ItemStack(ConfigItems.itemWandRod, 1, 3), new ItemStack(ConfigItems.itemWandRod, 1, 4), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWandRod, 1, 5), new ItemStack(ConfigItems.itemWandRod, 1, 6), new ItemStack(ConfigItems.itemWandRod, 1, 7) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2389 */     ConfigResearch.recipes.put("FocusHellbat", ThaumcraftApi.addInfusionCraftingRecipe("FOCUSHELLBAT", new ItemStack(ConfigItems.itemFocusHellbat), 3, new AspectList().add(Aspect.FIRE, 25).add(Aspect.AIR, 15).add(Aspect.BEAST, 15).add(Aspect.ENTROPY, 25), new ItemStack(Items.field_151064_bs), new ItemStack[] { new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemShard, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2405 */     ConfigResearch.recipes.put("FocusPortableHole", ThaumcraftApi.addInfusionCraftingRecipe("FOCUSPORTABLEHOLE", new ItemStack(ConfigItems.itemFocusPortableHole), 3, new AspectList().add(Aspect.TRAVEL, 25).add(Aspect.ELDRITCH, 10).add(Aspect.EXCHANGE, 10).add(Aspect.ENTROPY, 25), new ItemStack(Items.field_151079_bi), new ItemStack[] { new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemShard, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2422 */     ConfigResearch.recipes.put("FocusWarding", ThaumcraftApi.addInfusionCraftingRecipe("FOCUSWARDING", new ItemStack(ConfigItems.itemFocusWarding), 4, new AspectList().add(Aspect.EARTH, 25).add(Aspect.ARMOR, 25).add(Aspect.ORDER, 25).add(Aspect.MIND, 10), new ItemStack(Items.field_151156_bN), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(ConfigItems.itemResource, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemShard, 1, 4) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2440 */     ConfigResearch.recipes.put("WandPed", ThaumcraftApi.addInfusionCraftingRecipe("WANDPED", new ItemStack(ConfigBlocks.blockStoneDevice, 1, 5), 3, new AspectList().add(Aspect.AURA, 10).add(Aspect.MAGIC, 15).add(Aspect.EXCHANGE, 15), new ItemStack(ConfigBlocks.blockStoneDevice, 1, 1), new ItemStack[] { new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(Items.field_151045_i) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2458 */     ConfigResearch.recipes.put("WandPedFocus", ThaumcraftApi.addInfusionCraftingRecipe("WANDPEDFOC", new ItemStack(ConfigBlocks.blockStoneDevice, 1, 8), 4, new AspectList().add(Aspect.ORDER, 10).add(Aspect.MAGIC, 15).add(Aspect.EXCHANGE, 10), new ItemStack(Items.field_151132_bS), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 8), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 8), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 8), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 8) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2488 */     ConfigResearch.recipes.put("NodeStabilizerAdv", ThaumcraftApi.addInfusionCraftingRecipe("NODESTABILIZERADV", new ItemStack(ConfigBlocks.blockStoneDevice, 1, 10), 10, new AspectList().add(Aspect.AURA, 32).add(Aspect.MAGIC, 16).add(Aspect.ORDER, 16).add(Aspect.ENERGY, 16), new ItemStack(ConfigBlocks.blockStoneDevice, 1, 9), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(Blocks.field_150451_bX), new ItemStack(ConfigItems.itemResource, 1, 0), new ItemStack(Blocks.field_150451_bX), new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(Blocks.field_150451_bX), new ItemStack(ConfigItems.itemResource, 1, 0), new ItemStack(Blocks.field_150451_bX) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2508 */     ConfigResearch.recipes.put("JarBrain", ThaumcraftApi.addInfusionCraftingRecipe("JARBRAIN", new ItemStack(ConfigBlocks.blockJar, 1, 1), 4, new AspectList().add(Aspect.MIND, 10).add(Aspect.SENSES, 10).add(Aspect.UNDEAD, 20), new ItemStack(ConfigBlocks.blockJar, 1, 0), new ItemStack[] { new ItemStack(ConfigItems.itemZombieBrain), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151131_as), new ItemStack(Items.field_151070_bp) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2522 */     ConfigResearch.recipes.put("AdvancedGolem", ThaumcraftApi.addInfusionCraftingRecipe("ADVANCEDGOLEM", new Object[] { "advanced", new NBTTagByte(1) }, 3, new AspectList().add(Aspect.MIND, 8).add(Aspect.SENSES, 8).add(Aspect.LIFE, 8), new ItemStack(ConfigItems.itemGolemPlacer, 1, 32767), new ItemStack[] { new ItemStack(Items.field_151137_ax), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151016_H), new ItemStack(ConfigBlocks.blockJar, 1, 0), new ItemStack(ConfigItems.itemZombieBrain) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2543 */     ConfigResearch.recipes.put("HoverHarness", ThaumcraftApi.addInfusionCraftingRecipe("HOVERHARNESS", new ItemStack(ConfigItems.itemHoverHarness), 6, new AspectList().add(Aspect.FLIGHT, 32).add(Aspect.ENERGY, 32).add(Aspect.MECHANISM, 32).add(Aspect.TRAVEL, 16), new ItemStack(Items.field_151027_R), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), new ItemStack(Items.field_151132_bS), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151042_j) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2565 */     ConfigResearch.recipes.put("HoverGirdle", ThaumcraftApi.addInfusionCraftingRecipe("HOVERGIRDLE", new ItemStack(ConfigItems.itemGirdleHover), 8, new AspectList().add(Aspect.FLIGHT, 16).add(Aspect.ENERGY, 32).add(Aspect.AIR, 32).add(Aspect.TRAVEL, 16), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 2), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151043_k), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151043_k) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2583 */     ConfigResearch.recipes.put("VisAmulet", ThaumcraftApi.addInfusionCraftingRecipe("VISAMULET", new ItemStack(ConfigItems.itemAmuletVis, 1, 1), 6, new AspectList().add(Aspect.AURA, 24).add(Aspect.ENERGY, 64).add(Aspect.MAGIC, 64).add(Aspect.VOID, 24), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 0), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigBlocks.blockCrystal, 1, 6), new ItemStack(ConfigBlocks.blockCrystal, 1, 6), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigBlocks.blockCrystal, 1, 6), new ItemStack(ConfigBlocks.blockCrystal, 1, 6) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2619 */     ConfigResearch.recipes.put("RunicAmulet", ThaumcraftApi.addInfusionCraftingRecipe("RUNICARMOR", new ItemStack(ConfigItems.itemAmuletRunic, 1, 0), 4, new AspectList().add(Aspect.ARMOR, 20).add(Aspect.MAGIC, 35).add(Aspect.ENERGY, 35), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 0), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemResource, 1, 6), new ItemStack(ConfigItems.itemResource, 1, 7), new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(ConfigItems.itemInkwell) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2634 */     ConfigResearch.recipes.put("RunicAmuletEmergency", ThaumcraftApi.addInfusionCraftingRecipe("RUNICEMERGENCY", new ItemStack(ConfigItems.itemAmuletRunic, 1, 1), 7, new AspectList().add(Aspect.ARMOR, 20).add(Aspect.MAGIC, 35).add(Aspect.EARTH, 32).add(Aspect.VOID, 32), new ItemStack(ConfigItems.itemAmuletRunic, 1, 0), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(Items.field_151068_bn, 1, 8233), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2650 */     ConfigResearch.recipes.put("RunicRing", ThaumcraftApi.addInfusionCraftingRecipe("RUNICARMOR", new ItemStack(ConfigItems.itemRingRunic, 1, 1), 3, new AspectList().add(Aspect.ARMOR, 10).add(Aspect.MAGIC, 25).add(Aspect.ENERGY, 25), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemResource, 1, 6), new ItemStack(ConfigItems.itemResource, 1, 7), new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(ConfigItems.itemInkwell) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2664 */     ConfigResearch.recipes.put("RunicRingCharged", ThaumcraftApi.addInfusionCraftingRecipe("RUNICCHARGED", new ItemStack(ConfigItems.itemRingRunic, 1, 2), 6, new AspectList().add(Aspect.ARMOR, 16).add(Aspect.MAGIC, 16).add(Aspect.ENERGY, 64), new ItemStack(ConfigItems.itemRingRunic, 1, 1), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(Items.field_151068_bn, 1, 8226), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2679 */     ConfigResearch.recipes.put("RunicRingHealing", ThaumcraftApi.addInfusionCraftingRecipe("RUNICHEALING", new ItemStack(ConfigItems.itemRingRunic, 1, 3), 6, new AspectList().add(Aspect.ARMOR, 16).add(Aspect.MAGIC, 16).add(Aspect.WATER, 32).add(Aspect.HEAL, 32), new ItemStack(ConfigItems.itemRingRunic, 1, 1), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(Items.field_151068_bn, 1, 8257), new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(ConfigItems.itemShard, 1, 2) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2695 */     ConfigResearch.recipes.put("RunicGirdle", ThaumcraftApi.addInfusionCraftingRecipe("RUNICARMOR", new ItemStack(ConfigItems.itemGirdleRunic, 1, 0), 4, new AspectList().add(Aspect.ARMOR, 30).add(Aspect.MAGIC, 50).add(Aspect.ENERGY, 50), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 2), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemResource, 1, 6), new ItemStack(ConfigItems.itemResource, 1, 7), new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(ConfigItems.itemInkwell) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2711 */     ConfigResearch.recipes.put("RunicGirdleKinetic", ThaumcraftApi.addInfusionCraftingRecipe("RUNICKINETIC", new ItemStack(ConfigItems.itemGirdleRunic, 1, 1), 7, new AspectList().add(Aspect.ARMOR, 33).add(Aspect.MAGIC, 55).add(Aspect.AIR, 64), new ItemStack(ConfigItems.itemGirdleRunic, 1, 0), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(Items.field_151068_bn, 1, 16428), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2725 */     ConfigResearch.recipes.put("RunicGirdleKinetic_2", ThaumcraftApi.addInfusionCraftingRecipe("RUNICKINETIC", new ItemStack(ConfigItems.itemGirdleRunic, 1, 1), 7, new AspectList().add(Aspect.ARMOR, 33).add(Aspect.MAGIC, 55).add(Aspect.AIR, 64), new ItemStack(ConfigItems.itemGirdleRunic, 1, 0), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(Items.field_151068_bn, 1, 24620), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2741 */     InfusionRunicAugmentRecipe ra = new InfusionRunicAugmentRecipe();
/* 2742 */     ThaumcraftApi.getCraftingRecipes().add(ra);
/*      */     
/*      */ 
/*      */ 
/* 2746 */     if (Config.allowMirrors) {
/* 2747 */       ConfigResearch.recipes.put("Mirror", ThaumcraftApi.addInfusionCraftingRecipe("MIRROR", new ItemStack(ConfigBlocks.blockMirror, 1, 0), 1, new AspectList().add(Aspect.TRAVEL, 8).add(Aspect.DARKNESS, 8).add(Aspect.EXCHANGE, 8), new ItemStack(ConfigItems.itemResource, 1, 10), new ItemStack[] { new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151079_bi) }));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2761 */       ConfigResearch.recipes.put("MirrorHand", ThaumcraftApi.addInfusionCraftingRecipe("MIRRORHAND", new ItemStack(ConfigItems.itemHandMirror), 5, new AspectList().add(Aspect.TOOL, 16).add(Aspect.TRAVEL, 16), new ItemStack(ConfigBlocks.blockMirror, 1, 0), new ItemStack[] { new ItemStack(Items.field_151055_y), new ItemStack(Items.field_151111_aL), new ItemStack(Items.field_151148_bJ) }));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2770 */       ConfigResearch.recipes.put("MirrorEssentia", ThaumcraftApi.addInfusionCraftingRecipe("MIRRORESSENTIA", new ItemStack(ConfigBlocks.blockMirror, 1, 6), 2, new AspectList().add(Aspect.TRAVEL, 8).add(Aspect.WATER, 8).add(Aspect.EXCHANGE, 8), new ItemStack(ConfigItems.itemResource, 1, 10), new ItemStack[] { new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151079_bi) }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2786 */     ConfigResearch.recipes.put("ElementalAxe", ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTALAXE", new ItemStack(ConfigItems.itemAxeElemental), 1, new AspectList().add(Aspect.WATER, 16).add(Aspect.TREE, 8), new ItemStack(ConfigItems.itemAxeThaumium), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(Items.field_151045_i), new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2800 */     ConfigResearch.recipes.put("ElementalPick", ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTALPICK", new ItemStack(ConfigItems.itemPickElemental), 1, new AspectList().add(Aspect.FIRE, 8).add(Aspect.MINE, 8).add(Aspect.SENSES, 8), new ItemStack(ConfigItems.itemPickThaumium), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(Items.field_151045_i), new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2813 */     ConfigResearch.recipes.put("ElementalSword", ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTALSWORD", new ItemStack(ConfigItems.itemSwordElemental), 1, new AspectList().add(Aspect.AIR, 8).add(Aspect.MOTION, 8).add(Aspect.ENERGY, 8), new ItemStack(ConfigItems.itemSwordThaumium), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(Items.field_151045_i), new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2828 */     ConfigResearch.recipes.put("ElementalShovel", ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTALSHOVEL", new ItemStack(ConfigItems.itemShovelElemental), 1, new AspectList().add(Aspect.EARTH, 16).add(Aspect.CRAFT, 8), new ItemStack(ConfigItems.itemShovelThaumium), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(Items.field_151045_i), new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2840 */     ConfigResearch.recipes.put("ElementalHoe", ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTALHOE", new ItemStack(ConfigItems.itemHoeElemental), 1, new AspectList().add(Aspect.HARVEST, 8).add(Aspect.PLANT, 8).add(Aspect.EARTH, 8), new ItemStack(ConfigItems.itemHoeThaumium), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(Items.field_151045_i), new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2856 */     ConfigResearch.recipes.put("BootsTraveller", ThaumcraftApi.addInfusionCraftingRecipe("BOOTSTRAVELLER", new ItemStack(ConfigItems.itemBootsTraveller), 1, new AspectList().add(Aspect.FLIGHT, 25).add(Aspect.TRAVEL, 25), new ItemStack(Items.field_151021_T), new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 7), new ItemStack(ConfigItems.itemResource, 1, 7), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151115_aP, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2870 */     ConfigResearch.recipes.put("CoreAlchemy", ThaumcraftApi.addInfusionCraftingRecipe("COREALCHEMY", new ItemStack(ConfigItems.itemGolemCore, 1, 6), 2, new AspectList().add(Aspect.MAGIC, 15).add(Aspect.WATER, 15).add(Aspect.MOTION, 15), new ItemStack(ConfigItems.itemGolemCore, 1, 5), new ItemStack[] { new ItemStack(ConfigBlocks.blockJar, 1, 0), new ItemStack(Items.field_151068_bn), new ItemStack(Items.field_151068_bn), new ItemStack(Items.field_151068_bn) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2885 */     ConfigResearch.recipes.put("CoreSorting", ThaumcraftApi.addInfusionCraftingRecipe("CORESORTING", new ItemStack(ConfigItems.itemGolemCore, 1, 10), 3, new AspectList().add(Aspect.VOID, 16).add(Aspect.EXCHANGE, 16).add(Aspect.HUNGER, 16).add(Aspect.GREED, 16), new ItemStack(ConfigItems.itemZombieBrain), new ItemStack[] { new ItemStack(ConfigItems.itemGolemCore, 1, 0), new ItemStack(Items.field_151132_bS), new ItemStack(ConfigItems.itemGolemCore, 1, 1), new ItemStack(Items.field_151121_aF) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2901 */     ConfigResearch.recipes.put("CoreLumber", ThaumcraftApi.addInfusionCraftingRecipe("CORELUMBER", new ItemStack(ConfigItems.itemGolemCore, 1, 7), 2, new AspectList().add(Aspect.TOOL, 16).add(Aspect.TREE, 16).add(Aspect.HARVEST, 16), new ItemStack(ConfigItems.itemGolemCore, 1, 3), new ItemStack[] { new ItemStack(ConfigItems.itemAxeElemental), new ItemStack(Items.field_151036_c), new ItemStack(Items.field_151036_c), new ItemStack(Items.field_151036_c) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2916 */     ConfigResearch.recipes.put("CoreFishing", ThaumcraftApi.addInfusionCraftingRecipe("COREFISHING", new ItemStack(ConfigItems.itemGolemCore, 1, 11), 3, new AspectList().add(Aspect.WATER, 16).add(Aspect.HARVEST, 16).add(Aspect.BEAST, 16), new ItemStack(ConfigItems.itemGolemCore, 1, 3), new ItemStack[] { new ItemStack(Items.field_151112_aM), new ItemStack(Items.field_151115_aP, 1, 0), new ItemStack(Items.field_151115_aP, 1, 3), new ItemStack(Items.field_151115_aP, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2930 */     ConfigResearch.recipes.put("CoreUse", ThaumcraftApi.addInfusionCraftingRecipe("COREUSE", new ItemStack(ConfigItems.itemGolemCore, 1, 8), 3, new AspectList().add(Aspect.TOOL, 20).add(Aspect.MECHANISM, 20).add(Aspect.MAN, 20), new ItemStack(ConfigItems.itemGolemCore, 1, 1), new ItemStack[] { new ItemStack(Items.field_151132_bS), new ItemStack(Items.field_151033_d), new ItemStack(Items.field_151097_aZ), new ItemStack(Blocks.field_150442_at) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2944 */     ConfigResearch.recipes.put("ArcaneBore", ThaumcraftApi.addInfusionCraftingRecipe("ARCANEBORE", new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 5), 4, new AspectList().add(Aspect.ENERGY, 16).add(Aspect.MINE, 32).add(Aspect.MECHANISM, 32).add(Aspect.VOID, 16).add(Aspect.MOTION, 16), new ItemStack(Blocks.field_150331_J), new ItemStack[] { new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151046_w), new ItemStack(Items.field_151047_v), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2964 */     ConfigResearch.recipes.put("TravelTrunk", ThaumcraftApi.addInfusionCraftingRecipe("TRAVELTRUNK", new ItemStack(ConfigItems.itemTrunkSpawner), 3, new AspectList().add(Aspect.MOTION, 4).add(Aspect.SOUL, 4).add(Aspect.TRAVEL, 4).add(Aspect.VOID, 16), new ItemStack(ConfigBlocks.blockChestHungry), new ItemStack[] { new ItemStack(Items.field_151042_j), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), new ItemStack(ConfigItems.itemGolemPlacer, 1, 1), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2988 */     ConfigResearch.recipes.put("LampGrowth", ThaumcraftApi.addInfusionCraftingRecipe("LAMPGROWTH", new ItemStack(ConfigBlocks.blockMetalDevice, 1, 8), 4, new AspectList().add(Aspect.PLANT, 16).add(Aspect.LIGHT, 8).add(Aspect.LIFE, 16), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 7), new ItemStack[] { new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151100_aR, 1, 15), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151100_aR, 1, 15), new ItemStack(ConfigItems.itemShard, 1, 3) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3003 */     ConfigResearch.recipes.put("LampFertility", ThaumcraftApi.addInfusionCraftingRecipe("LAMPFERTILITY", new ItemStack(ConfigBlocks.blockMetalDevice, 1, 13), 4, new AspectList().add(Aspect.BEAST, 16).add(Aspect.LIFE, 16).add(Aspect.LIGHT, 8), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 7), new ItemStack[] { new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151015_O), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151172_bF), new ItemStack(ConfigItems.itemShard, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3020 */     ConfigResearch.recipes.put("ThaumiumFortressHelm", ThaumcraftApi.addInfusionCraftingRecipe("ARMORFORTRESS", new ItemStack(ConfigItems.itemHelmetFortress), 3, new AspectList().add(Aspect.METAL, 24).add(Aspect.ARMOR, 16).add(Aspect.MAGIC, 16), new ItemStack(ConfigItems.itemHelmetThaumium), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151166_bC) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3031 */     ConfigResearch.recipes.put("ThaumiumFortressChest", ThaumcraftApi.addInfusionCraftingRecipe("ARMORFORTRESS", new ItemStack(ConfigItems.itemChestFortress), 3, new AspectList().add(Aspect.METAL, 24).add(Aspect.ARMOR, 24).add(Aspect.MAGIC, 16), new ItemStack(ConfigItems.itemChestThaumium), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151116_aA) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3043 */     ConfigResearch.recipes.put("ThaumiumFortressLegs", ThaumcraftApi.addInfusionCraftingRecipe("ARMORFORTRESS", new ItemStack(ConfigItems.itemLegsFortress), 3, new AspectList().add(Aspect.METAL, 24).add(Aspect.ARMOR, 20).add(Aspect.MAGIC, 16), new ItemStack(ConfigItems.itemLegsThaumium), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151116_aA) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3056 */     ConfigResearch.recipes.put("VoidRobeHelm", ThaumcraftApi.addInfusionCraftingRecipe("ARMORVOIDFORTRESS", new ItemStack(ConfigItems.itemHelmetVoidRobe), 6, new AspectList().add(Aspect.METAL, 16).add(Aspect.SENSES, 16).add(Aspect.ARMOR, 16).add(Aspect.CLOTH, 16).add(Aspect.MAGIC, 16).add(Aspect.ELDRITCH, 16).add(Aspect.VOID, 16), new ItemStack(ConfigItems.itemHelmetVoid), new ItemStack[] { new ItemStack(ConfigItems.itemGoggles), new ItemStack(ConfigItems.itemResource, 1, 7), new ItemStack(ConfigItems.itemResource, 1, 7), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 7), new ItemStack(ConfigItems.itemResource, 1, 7) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3069 */     ConfigResearch.recipes.put("VoidRobeChest", ThaumcraftApi.addInfusionCraftingRecipe("ARMORVOIDFORTRESS", new ItemStack(ConfigItems.itemChestVoidRobe), 6, new AspectList().add(Aspect.METAL, 24).add(Aspect.ARMOR, 24).add(Aspect.CLOTH, 24).add(Aspect.MAGIC, 16).add(Aspect.ELDRITCH, 16).add(Aspect.VOID, 24), new ItemStack(ConfigItems.itemChestVoid), new ItemStack[] { new ItemStack(ConfigItems.itemChestRobe), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 7), new ItemStack(Items.field_151116_aA) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3082 */     ConfigResearch.recipes.put("VoidRobeLegs", ThaumcraftApi.addInfusionCraftingRecipe("ARMORVOIDFORTRESS", new ItemStack(ConfigItems.itemLegsVoidRobe), 6, new AspectList().add(Aspect.METAL, 20).add(Aspect.ARMOR, 20).add(Aspect.CLOTH, 20).add(Aspect.MAGIC, 16).add(Aspect.ELDRITCH, 16).add(Aspect.VOID, 20), new ItemStack(ConfigItems.itemLegsVoid), new ItemStack[] { new ItemStack(ConfigItems.itemLegsRobe), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 7), new ItemStack(Items.field_151116_aA) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3096 */     GameRegistry.addRecipe(new RecipesVoidRobeArmorDyes());
/*      */     
/*      */ 
/* 3099 */     ConfigResearch.recipes.put("HelmGoggles", ThaumcraftApi.addInfusionCraftingRecipe("HELMGOGGLES", new Object[] { "goggles", new NBTTagByte(1) }, 5, new AspectList().add(Aspect.SENSES, 32).add(Aspect.AURA, 16).add(Aspect.ARMOR, 16), new ItemStack(ConfigItems.itemHelmetFortress, 1, 32767), new ItemStack[] { new ItemStack(Items.field_151123_aH), new ItemStack(ConfigItems.itemGoggles, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3106 */     ConfigResearch.recipes.put("MaskGrinningDevil", ThaumcraftApi.addInfusionCraftingRecipe("MASKGRINNINGDEVIL", new Object[] { "mask", new NBTTagInt(0) }, 8, new AspectList().add(Aspect.MIND, 64).add(Aspect.HEAL, 64).add(Aspect.ARMOR, 16), new ItemStack(ConfigItems.itemHelmetFortress, 1, 32767), new ItemStack[] { new ItemStack(Items.field_151100_aR, 1, 0), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151116_aA), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2), new ItemStack(ConfigItems.itemZombieBrain), new ItemStack(Items.field_151042_j) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3117 */     ConfigResearch.recipes.put("MaskAngryGhost", ThaumcraftApi.addInfusionCraftingRecipe("MASKANGRYGHOST", new Object[] { "mask", new NBTTagInt(1) }, 8, new AspectList().add(Aspect.ENTROPY, 64).add(Aspect.DEATH, 64).add(Aspect.ARMOR, 16), new ItemStack(ConfigItems.itemHelmetFortress, 1, 32767), new ItemStack[] { new ItemStack(Items.field_151100_aR, 1, 15), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151116_aA), new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151144_bL, 1, 1), new ItemStack(Items.field_151042_j) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3128 */     ConfigResearch.recipes.put("MaskSippingFiend", ThaumcraftApi.addInfusionCraftingRecipe("MASKSIPPINGFIEND", new Object[] { "mask", new NBTTagInt(2) }, 8, new AspectList().add(Aspect.UNDEAD, 64).add(Aspect.LIFE, 64).add(Aspect.ARMOR, 16), new ItemStack(ConfigItems.itemHelmetFortress, 1, 32767), new ItemStack[] { new ItemStack(Items.field_151100_aR, 1, 1), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151116_aA), new ItemStack(Items.field_151073_bk), new ItemStack(Items.field_151117_aB), new ItemStack(Items.field_151042_j) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3141 */     ConfigResearch.recipes.put("SanityCheck", ThaumcraftApi.addInfusionCraftingRecipe("SANITYCHECK", new ItemStack(ConfigItems.itemSanityChecker), 4, new AspectList().add(Aspect.MIND, 24).add(Aspect.SENSES, 24).add(Aspect.ELDRITCH, 8), new ItemStack(ConfigItems.itemThaumometer), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 10), new ItemStack(ConfigItems.itemZombieBrain), new ItemStack(Items.field_151045_i) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3153 */     ConfigResearch.recipes.put("EssentiaReservoir", ThaumcraftApi.addInfusionCraftingRecipe("ESSENTIARESERVOIR", new ItemStack(ConfigBlocks.blockEssentiaReservoir), 6, new AspectList().add(Aspect.WATER, 8).add(Aspect.VOID, 8).add(Aspect.MAGIC, 8).add(Aspect.EXCHANGE, 8), new ItemStack(ConfigBlocks.blockTube, 1, 4), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigBlocks.blockJar, 1, 0), new ItemStack(ConfigBlocks.blockJar, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigBlocks.blockJar, 1, 0), new ItemStack(ConfigBlocks.blockJar, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3168 */     ConfigResearch.recipes.put("SinStone", ThaumcraftApi.addInfusionCraftingRecipe("SINSTONE", new ItemStack(ConfigItems.itemCompassStone), 5, new AspectList().add(Aspect.SENSES, 8).add(Aspect.DARKNESS, 8).add(Aspect.ELDRITCH, 8).add(Aspect.AURA, 8), new ItemStack(Items.field_151145_ak), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(ConfigItems.itemResource, 1, 9), new ItemStack(ConfigItems.itemShard, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3180 */     ConfigResearch.recipes.put("PrimalCrusher", ThaumcraftApi.addInfusionCraftingRecipe("PRIMALCRUSHER", new ItemStack(ConfigItems.itemPrimalCrusher), 6, new AspectList().add(Aspect.MINE, 24).add(Aspect.TOOL, 24).add(Aspect.ENTROPY, 16).add(Aspect.VOID, 16).add(Aspect.WEAPON, 16).add(Aspect.ELDRITCH, 16).add(Aspect.GREED, 16), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemPickVoid, 1, 32767), new ItemStack(ConfigItems.itemShovelVoid, 1, 32767), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemPickElemental, 1, 32767), new ItemStack(ConfigItems.itemShovelElemental, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3195 */     ConfigResearch.recipes.put("EldritchEye", ThaumcraftApi.addInfusionCraftingRecipe("OCULUS", new ItemStack(ConfigItems.itemEldritchObject), 5, new AspectList().add(Aspect.ELDRITCH, 64).add(Aspect.VOID, 16).add(Aspect.DARKNESS, 16).add(Aspect.TRAVEL, 16), new ItemStack(Items.field_151061_bv), new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 17), new ItemStack(Items.field_151043_k) }));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initializeNormalRecipes()
/*      */   {
/* 3207 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 0), new Object[] { "#", Character.valueOf('#'), Items.field_151042_j });
/*      */     
/*      */ 
/* 3210 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 6), new Object[] { "#", Character.valueOf('#'), new ItemStack(ConfigItems.itemResource, 1, 2) });
/*      */     
/*      */ 
/* 3213 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 7), new Object[] { "#", Character.valueOf('#'), new ItemStack(ConfigItems.itemResource, 1, 16) });
/*      */     
/*      */ 
/* 3216 */     oreDictRecipe(new ItemStack(Items.field_151042_j), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 0) });
/*      */     
/*      */ 
/* 3219 */     oreDictRecipe(new ItemStack(ConfigItems.itemResource, 1, 2), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 6) });
/*      */     
/*      */ 
/* 3222 */     oreDictRecipe(new ItemStack(ConfigItems.itemResource, 1, 3), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 5) });
/*      */     
/*      */ 
/* 3225 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 5), new Object[] { "#", Character.valueOf('#'), new ItemStack(ConfigItems.itemResource, 1, 3) });
/*      */     
/*      */ 
/* 3228 */     oreDictRecipe(new ItemStack(ConfigItems.itemResource, 1, 16), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 7) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3234 */     ConfigResearch.recipes.put("MundaneAmulet", oreDictRecipe(new ItemStack(ConfigItems.itemBaubleBlanks, 1, 0), new Object[] { " S ", "S S", " I ", Character.valueOf('S'), new ItemStack(Items.field_151007_F), Character.valueOf('I'), new ItemStack(Items.field_151043_k) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3243 */     ConfigResearch.recipes.put("MundaneRing", oreDictRecipe(new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1), new Object[] { " N ", "N N", " N ", Character.valueOf('N'), new ItemStack(Items.field_151074_bl) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3250 */     ConfigResearch.recipes.put("MundaneBelt", oreDictRecipe(new ItemStack(ConfigItems.itemBaubleBlanks, 1, 2), new Object[] { " L ", "L L", " I ", Character.valueOf('L'), new ItemStack(Items.field_151116_aA), Character.valueOf('I'), new ItemStack(Items.field_151043_k) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3261 */     shapelessOreDictRecipe(new ItemStack(ConfigItems.itemTripleMeatTreat), new Object[] { Items.field_151102_aT, ConfigItems.itemNuggetBeef, ConfigItems.itemNuggetChicken, ConfigItems.itemNuggetPork });
/*      */     
/* 3263 */     shapelessOreDictRecipe(new ItemStack(ConfigItems.itemTripleMeatTreat), new Object[] { Items.field_151102_aT, ConfigItems.itemNuggetBeef, ConfigItems.itemNuggetChicken, ConfigItems.itemNuggetFish });
/*      */     
/* 3265 */     shapelessOreDictRecipe(new ItemStack(ConfigItems.itemTripleMeatTreat), new Object[] { Items.field_151102_aT, ConfigItems.itemNuggetBeef, ConfigItems.itemNuggetFish, ConfigItems.itemNuggetPork });
/*      */     
/* 3267 */     shapelessOreDictRecipe(new ItemStack(ConfigItems.itemTripleMeatTreat), new Object[] { Items.field_151102_aT, ConfigItems.itemNuggetFish, ConfigItems.itemNuggetChicken, ConfigItems.itemNuggetPork });
/*      */     
/*      */ 
/*      */ 
/* 3271 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemResource, 1, 3), new Object[] { "#", Character.valueOf('#'), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2) });
/*      */     
/*      */ 
/*      */ 
/* 3275 */     CraftingManager.func_77594_a().func_92103_a(new ItemStack(Items.field_151065_br), new Object[] { "#", Character.valueOf('#'), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 3) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3281 */     ConfigResearch.recipes.put("JarLabel", shapelessOreDictRecipe(new ItemStack(ConfigItems.itemResource, 4, 13), new Object[] { "dyeBlack", Items.field_151123_aH, Items.field_151121_aF, Items.field_151121_aF, Items.field_151121_aF, Items.field_151121_aF }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3286 */     int count = 0;
/* 3287 */     for (Aspect aspect : Aspect.aspects.values()) {
/* 3288 */       ItemStack essence = new ItemStack(ConfigItems.itemEssence, 1, 1);
/* 3289 */       ((IEssentiaContainerItem)essence.func_77973_b()).setAspects(essence, new AspectList().add(aspect, 8));
/*      */       
/* 3291 */       ItemStack output = new ItemStack(ConfigItems.itemResource, 1, 13);
/* 3292 */       ((IEssentiaContainerItem)output.func_77973_b()).setAspects(output, new AspectList().add(aspect, 0));
/*      */       
/*      */ 
/* 3295 */       ConfigResearch.recipes.put("JarLabel" + count, shapelessNBTOreRecipe(output, new Object[] { new ItemStack(ConfigItems.itemResource, 1, 13), essence }));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 3300 */       count++;
/*      */     }
/*      */     
/* 3303 */     ItemStack input = new ItemStack(ConfigItems.itemResource, 1, 13);
/* 3304 */     ((IEssentiaContainerItem)input.func_77973_b()).setAspects(input, new AspectList().add(Aspect.WATER, 1));
/*      */     
/* 3306 */     ConfigResearch.recipes.put("JarLabelNull", shapelessOreDictRecipe(new ItemStack(ConfigItems.itemResource, 1, 13), new Object[] { input }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3312 */     ConfigResearch.recipes.put("WandBasic", oreDictRecipe(basicWand, new Object[] { "  I", " S ", "I  ", Character.valueOf('I'), new ItemStack(ConfigItems.itemWandCap, 1, 0), Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3320 */     ConfigResearch.recipes.put("WandCapIron", oreDictRecipe(new ItemStack(ConfigItems.itemWandCap, 1, 0), new Object[] { "NNN", "N N", Character.valueOf('N'), "nuggetIron" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3327 */     ConfigResearch.recipes.put("KnowFrag", GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemResearchNotes, 1, 42), new Object[] { "KKK", "KKK", "KKK", Character.valueOf('K'), new ItemStack(ConfigItems.itemResource, 1, 9) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3335 */     ConfigResearch.recipes.put("PlankGreatwood", GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockWoodenDevice, 4, 6), new Object[] { "W", Character.valueOf('W'), new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/* 3339 */     ConfigResearch.recipes.put("PlankSilverwood", GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockWoodenDevice, 4, 7), new Object[] { "W", Character.valueOf('W'), new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3345 */     GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockStairsGreatwood, 4, 0), new Object[] { "K  ", "KK ", "KKK", Character.valueOf('K'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6) });
/*      */     
/* 3347 */     GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockStairsSilverwood, 4, 0), new Object[] { "K  ", "KK ", "KKK", Character.valueOf('K'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 7) });
/*      */     
/*      */ 
/*      */ 
/* 3351 */     GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockSlabWood, 6, 0), new Object[] { "KKK", Character.valueOf('K'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6) });
/*      */     
/* 3353 */     GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockSlabWood, 6, 1), new Object[] { "KKK", Character.valueOf('K'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 7) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3358 */     ConfigResearch.recipes.put("BlockFlesh", GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockTaint, 1, 2), new Object[] { "KKK", "KKK", "KKK", Character.valueOf('K'), Items.field_151078_bh }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3363 */     ConfigResearch.recipes.put("BlockThaumium", oreDictRecipe(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 4), new Object[] { "KKK", "KKK", "KKK", Character.valueOf('K'), "ingotThaumium" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3369 */     GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemResource, 9, 2), new Object[] { "K", Character.valueOf('K'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 4) });
/*      */     
/*      */ 
/*      */ 
/* 3373 */     ConfigResearch.recipes.put("BlockTallow", GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 5), new Object[] { "KKK", "KKK", "KKK", Character.valueOf('K'), new ItemStack(ConfigItems.itemResource, 1, 4) }));
/*      */     
/*      */ 
/*      */ 
/* 3377 */     GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemResource, 9, 4), new Object[] { "K", Character.valueOf('K'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 5) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3382 */     for (int a = 0; a < 6; a++) {
/* 3383 */       ConfigResearch.recipes.put("Clusters" + a, shapelessOreDictRecipe(new ItemStack(ConfigBlocks.blockCrystal, 1, a), new Object[] { new ItemStack(ConfigItems.itemShard, 1, a), new ItemStack(ConfigItems.itemShard, 1, a), new ItemStack(ConfigItems.itemShard, 1, a), new ItemStack(ConfigItems.itemShard, 1, a), new ItemStack(ConfigItems.itemShard, 1, a), new ItemStack(ConfigItems.itemShard, 1, a) }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3394 */     ConfigResearch.recipes.put("Clusters6", shapelessOreDictRecipe(new ItemStack(ConfigBlocks.blockCrystal, 1, 6), new Object[] { new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(ConfigItems.itemShard, 1, 5) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3406 */     GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockCosmeticOpaque, 1, 0), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack(ConfigItems.itemResource, 1, 6) });
/*      */     
/*      */ 
/* 3409 */     GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockCosmeticOpaque, 4, 1), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack(ConfigBlocks.blockCosmeticOpaque, 1, 0) });
/*      */     
/*      */ 
/* 3412 */     GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockCosmeticSolid, 4, 1), new Object[] { "##", "##", Character.valueOf('#'), Blocks.field_150343_Z });
/*      */     
/*      */ 
/* 3415 */     GameRegistry.addRecipe(new ItemStack(ConfigItems.itemResource, 4, 6), new Object[] { "#", Character.valueOf('#'), new ItemStack(ConfigBlocks.blockCosmeticOpaque, 1, 0) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3422 */     GameRegistry.addRecipe(new ItemStack(ConfigItems.itemResource, 4, 6), new Object[] { "#", Character.valueOf('#'), new ItemStack(ConfigBlocks.blockCosmeticOpaque, 1, 1) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3431 */     ConfigResearch.recipes.put("Grate", GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5), new Object[] { "#", "T", Character.valueOf('#'), new ItemStack(Blocks.field_150411_aY), Character.valueOf('T'), new ItemStack(Blocks.field_150415_aT) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3440 */     ConfigResearch.recipes.put("Phial", GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemEssence, 8, 0), new Object[] { " C ", "G G", " G ", Character.valueOf('G'), Blocks.field_150359_w, Character.valueOf('C'), Items.field_151119_aD }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3447 */     ConfigResearch.recipes.put("Table", oreDictRecipe(new ItemStack(ConfigBlocks.blockTable, 1, 0), new Object[] { "SSS", "W W", Character.valueOf('S'), "slabWood", Character.valueOf('W'), "plankWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3456 */     ConfigResearch.recipes.put("Scribe1", shapelessOreDictRecipe(new ItemStack(ConfigItems.itemInkwell), new Object[] { new ItemStack(ConfigItems.itemEssence, 1, 0), Items.field_151008_G, "dyeBlack" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3462 */     ConfigResearch.recipes.put("Scribe2", shapelessOreDictRecipe(new ItemStack(ConfigItems.itemInkwell), new Object[] { Items.field_151069_bo, Items.field_151008_G, "dyeBlack" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3467 */     ConfigResearch.recipes.put("Scribe3", shapelessOreDictRecipe(new ItemStack(ConfigItems.itemInkwell), new Object[] { new ItemStack(ConfigItems.itemInkwell, 1, 32767), "dyeBlack" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3476 */     ConfigResearch.recipes.put("Thaumometer", oreDictRecipe(new ItemStack(ConfigItems.itemThaumometer), new Object[] { " 1 ", "IGI", " 1 ", Character.valueOf('I'), Items.field_151043_k, Character.valueOf('G'), Blocks.field_150359_w, Character.valueOf('1'), new ItemStack(ConfigItems.itemShard, 1, 32767) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3493 */     ConfigResearch.recipes.put("ThaumiumHelm", oreDictRecipe(new ItemStack(ConfigItems.itemHelmetThaumium, 1), new Object[] { "III", "I I", Character.valueOf('I'), "ingotThaumium" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3498 */     ConfigResearch.recipes.put("ThaumiumChest", oreDictRecipe(new ItemStack(ConfigItems.itemChestThaumium, 1), new Object[] { "I I", "III", "III", Character.valueOf('I'), "ingotThaumium" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3503 */     ConfigResearch.recipes.put("ThaumiumLegs", oreDictRecipe(new ItemStack(ConfigItems.itemLegsThaumium, 1), new Object[] { "III", "I I", "I I", Character.valueOf('I'), "ingotThaumium" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3508 */     ConfigResearch.recipes.put("ThaumiumBoots", oreDictRecipe(new ItemStack(ConfigItems.itemBootsThaumium, 1), new Object[] { "I I", "I I", Character.valueOf('I'), "ingotThaumium" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3513 */     ConfigResearch.recipes.put("ThaumiumShovel", oreDictRecipe(new ItemStack(ConfigItems.itemShovelThaumium, 1), new Object[] { "I", "S", "S", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3519 */     ConfigResearch.recipes.put("ThaumiumPick", oreDictRecipe(new ItemStack(ConfigItems.itemPickThaumium, 1), new Object[] { "III", " S ", " S ", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3527 */     ConfigResearch.recipes.put("ThaumiumAxe", oreDictRecipe(new ItemStack(ConfigItems.itemAxeThaumium, 1), new Object[] { "II", "SI", "S ", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3534 */     ConfigResearch.recipes.put("ThaumiumHoe", oreDictRecipe(new ItemStack(ConfigItems.itemHoeThaumium, 1), new Object[] { "II", "S ", "S ", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3541 */     ConfigResearch.recipes.put("ThaumiumSword", oreDictRecipe(new ItemStack(ConfigItems.itemSwordThaumium, 1), new Object[] { "I", "I", "S", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3549 */     ConfigResearch.recipes.put("VoidHelm", oreDictRecipe(new ItemStack(ConfigItems.itemHelmetVoid, 1), new Object[] { "III", "I I", Character.valueOf('I'), "ingotVoid" }));
/*      */     
/* 3551 */     ConfigResearch.recipes.put("VoidChest", oreDictRecipe(new ItemStack(ConfigItems.itemChestVoid, 1), new Object[] { "I I", "III", "III", Character.valueOf('I'), "ingotVoid" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3556 */     ConfigResearch.recipes.put("VoidLegs", oreDictRecipe(new ItemStack(ConfigItems.itemLegsVoid, 1), new Object[] { "III", "I I", "I I", Character.valueOf('I'), "ingotVoid" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3561 */     ConfigResearch.recipes.put("VoidBoots", oreDictRecipe(new ItemStack(ConfigItems.itemBootsVoid, 1), new Object[] { "I I", "I I", Character.valueOf('I'), "ingotVoid" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3566 */     ConfigResearch.recipes.put("VoidShovel", oreDictRecipe(new ItemStack(ConfigItems.itemShovelVoid, 1), new Object[] { "I", "S", "S", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3572 */     ConfigResearch.recipes.put("VoidPick", oreDictRecipe(new ItemStack(ConfigItems.itemPickVoid, 1), new Object[] { "III", " S ", " S ", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3580 */     ConfigResearch.recipes.put("VoidAxe", oreDictRecipe(new ItemStack(ConfigItems.itemAxeVoid, 1), new Object[] { "II", "SI", "S ", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3587 */     ConfigResearch.recipes.put("VoidHoe", oreDictRecipe(new ItemStack(ConfigItems.itemHoeVoid, 1), new Object[] { "II", "S ", "S ", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3594 */     ConfigResearch.recipes.put("VoidSword", oreDictRecipe(new ItemStack(ConfigItems.itemSwordVoid, 1), new Object[] { "I", "I", "S", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3602 */     ConfigResearch.recipes.put("TallowCandle", GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockCandle, 3, 0), new Object[] { " S ", " T ", " T ", Character.valueOf('S'), Items.field_151007_F, Character.valueOf('T'), new ItemStack(ConfigItems.itemResource, 1, 4) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3608 */     for (int a = 1; a < 16; a++) {
/* 3609 */       shapelessOreDictRecipe(new ItemStack(ConfigBlocks.blockCandle, 1, a), new Object[] { ConfigAspects.dyes[(15 - a)], new ItemStack(ConfigBlocks.blockCandle, 1, 0) });
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3614 */     GameRegistry.addShapelessRecipe(new ItemStack(ConfigBlocks.blockCandle, 1, 0), new Object[] { new ItemStack(Items.field_151100_aR, 1, 15), new ItemStack(ConfigBlocks.blockCandle, 1, 32767) });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initializeSmelting()
/*      */   {
/* 3623 */     FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigBlocks.blockCustomOre, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 3), 1.0F);
/*      */     
/*      */ 
/* 3626 */     FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigBlocks.blockCustomOre, 1, 7), new ItemStack(ConfigItems.itemResource, 1, 6), 1.0F);
/*      */     
/*      */ 
/* 3629 */     GameRegistry.addSmelting(ConfigBlocks.blockMagicalLog, new ItemStack(Items.field_151044_h, 1, 1), 0.5F);
/*      */     
/* 3631 */     FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemNugget, 1, 16), new ItemStack(Items.field_151042_j, 2, 0), 1.0F);
/*      */     
/*      */ 
/* 3634 */     FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemNugget, 1, 21), new ItemStack(ConfigItems.itemResource, 2, 3), 1.0F);
/*      */     
/*      */ 
/* 3637 */     FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemNugget, 1, 31), new ItemStack(Items.field_151043_k, 2, 0), 1.0F);
/*      */     
/*      */ 
/* 3640 */     FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(ConfigItems.itemResource, 1, 14), 1.0F);
/*      */     
/*      */ 
/* 3643 */     FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemResource, 1, 18), new ItemStack(Items.field_151074_bl), 0.0F);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3648 */     ThaumcraftApi.addSmeltingBonus("oreGold", new ItemStack(Items.field_151074_bl, 0, 0));
/*      */     
/* 3650 */     ThaumcraftApi.addSmeltingBonus("oreIron", new ItemStack(ConfigItems.itemNugget, 0, 0));
/*      */     
/* 3652 */     ThaumcraftApi.addSmeltingBonus("oreCinnabar", new ItemStack(ConfigItems.itemNugget, 0, 5));
/*      */     
/* 3654 */     ThaumcraftApi.addSmeltingBonus("oreCopper", new ItemStack(ConfigItems.itemNugget, 0, 1));
/*      */     
/* 3656 */     ThaumcraftApi.addSmeltingBonus("oreTin", new ItemStack(ConfigItems.itemNugget, 0, 2));
/*      */     
/* 3658 */     ThaumcraftApi.addSmeltingBonus("oreSilver", new ItemStack(ConfigItems.itemNugget, 0, 3));
/*      */     
/* 3660 */     ThaumcraftApi.addSmeltingBonus("oreLead", new ItemStack(ConfigItems.itemNugget, 0, 4));
/*      */     
/* 3662 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ConfigItems.itemNugget, 1, 31), new ItemStack(Items.field_151074_bl, 0, 0));
/*      */     
/* 3664 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ConfigItems.itemNugget, 1, 16), new ItemStack(ConfigItems.itemNugget, 0, 0));
/*      */     
/* 3666 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ConfigItems.itemNugget, 1, 21), new ItemStack(ConfigItems.itemNugget, 0, 5));
/*      */     
/* 3668 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ConfigItems.itemNugget, 1, 17), new ItemStack(ConfigItems.itemNugget, 0, 1));
/*      */     
/* 3670 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ConfigItems.itemNugget, 1, 18), new ItemStack(ConfigItems.itemNugget, 0, 2));
/*      */     
/* 3672 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ConfigItems.itemNugget, 1, 19), new ItemStack(ConfigItems.itemNugget, 0, 3));
/*      */     
/* 3674 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ConfigItems.itemNugget, 1, 20), new ItemStack(ConfigItems.itemNugget, 0, 4));
/*      */     
/* 3676 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151076_bf), new ItemStack(ConfigItems.itemNuggetChicken));
/*      */     
/* 3678 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151082_bd), new ItemStack(ConfigItems.itemNuggetBeef));
/*      */     
/* 3680 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151147_al), new ItemStack(ConfigItems.itemNuggetPork));
/*      */     
/* 3682 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151115_aP, 1, 32767), new ItemStack(ConfigItems.itemNuggetFish));
/*      */   }
/*      */   
/*      */ 
/*      */   static IRecipe oreDictRecipe(ItemStack res, Object[] params)
/*      */   {
/* 3688 */     IRecipe rec = new ShapedOreRecipe(res, params);
/* 3689 */     CraftingManager.func_77594_a().func_77592_b().add(rec);
/* 3690 */     return rec;
/*      */   }
/*      */   
/*      */   static IRecipe shapelessOreDictRecipe(ItemStack res, Object[] params) {
/* 3694 */     IRecipe rec = new ShapelessOreRecipe(res, params);
/* 3695 */     CraftingManager.func_77594_a().func_77592_b().add(rec);
/* 3696 */     return rec;
/*      */   }
/*      */   
/*      */   static IRecipe shapelessNBTOreRecipe(ItemStack res, Object[] params) {
/* 3700 */     IRecipe rec = new ShapelessNBTOreRecipe(res, params);
/* 3701 */     CraftingManager.func_77594_a().func_77592_b().add(rec);
/* 3702 */     return rec;
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/config/ConfigRecipes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */