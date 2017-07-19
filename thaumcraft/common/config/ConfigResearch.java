/*      */ package thaumcraft.common.config;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.IRecipe;
/*      */ import net.minecraft.nbt.NBTTagByte;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import thaumcraft.api.ThaumcraftApi;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.crafting.CrucibleRecipe;
/*      */ import thaumcraft.api.crafting.IArcaneRecipe;
/*      */ import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
/*      */ import thaumcraft.api.crafting.InfusionRecipe;
/*      */ import thaumcraft.api.crafting.ShapedArcaneRecipe;
/*      */ import thaumcraft.api.research.ResearchCategories;
/*      */ import thaumcraft.api.research.ResearchItem;
/*      */ import thaumcraft.api.research.ResearchPage;
/*      */ import thaumcraft.api.wands.WandCap;
/*      */ import thaumcraft.api.wands.WandRod;
/*      */ import thaumcraft.common.blocks.ItemJarNode;
/*      */ import thaumcraft.common.items.wands.ItemWandCasting;
/*      */ import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;
/*      */ 
/*      */ public class ConfigResearch
/*      */ {
/*   32 */   public static ItemStack wand = null;
/*      */   
/*      */   public static void init() {
/*   35 */     wand = new ItemStack(ConfigItems.itemWandCasting, 1, 0);
/*   36 */     ((ItemWandCasting)wand.func_77973_b()).setCap(wand, ConfigItems.WAND_CAP_IRON);
/*   37 */     ((ItemWandCasting)wand.func_77973_b()).setRod(wand, ConfigItems.WAND_ROD_WOOD);
/*      */     
/*   39 */     initCategories();
/*   40 */     initBasicResearch();
/*   41 */     initThaumaturgyResearch();
/*   42 */     initAlchemyResearch();
/*   43 */     initArtificeResearch();
/*   44 */     initGolemancyResearch();
/*   45 */     initEldritchResearch();
/*      */   }
/*      */   
/*      */ 
/*      */   private static void initCategories()
/*      */   {
/*   51 */     ResearchCategories.registerCategory("BASICS", new ResourceLocation("thaumcraft", "textures/items/thaumonomiconcheat.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
/*      */     
/*      */ 
/*      */ 
/*   55 */     ResearchCategories.registerCategory("THAUMATURGY", new ResourceLocation("thaumcraft", "textures/misc/r_thaumaturgy.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*   60 */     ResearchCategories.registerCategory("ALCHEMY", new ResourceLocation("thaumcraft", "textures/misc/r_crucible.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
/*      */     
/*      */ 
/*      */ 
/*   64 */     ResearchCategories.registerCategory("ARTIFICE", new ResourceLocation("thaumcraft", "textures/misc/r_artifice.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
/*      */     
/*      */ 
/*      */ 
/*   68 */     ResearchCategories.registerCategory("GOLEMANCY", new ResourceLocation("thaumcraft", "textures/misc/r_golemancy.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
/*      */     
/*      */ 
/*      */ 
/*   72 */     ResearchCategories.registerCategory("ELDRITCH", new ResourceLocation("thaumcraft", "textures/misc/r_eldritch.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchbackeldritch.png"));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initThaumaturgyResearch()
/*      */   {
/*   80 */     new ResearchItem("BASICTHAUMATURGY", "THAUMATURGY", new AspectList(), 0, 0, 0, wand).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BASICTHAUMATURGY.1"), new ResearchPage("tc.research_page.BASICTHAUMATURGY.2"), new ResearchPage((IRecipe)recipes.get("WandCapIron")), new ResearchPage((IRecipe)recipes.get("WandBasic")) }).setAutoUnlock().setStub().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   90 */     new ResearchItem("FOCUSFIRE", "THAUMATURGY", new AspectList().add(Aspect.FIRE, 3).add(Aspect.MAGIC, 3), 2, -2, 1, new ItemStack(ConfigItems.itemFocusFire)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSFIRE.1"), new ResearchPage("tc.research_page.FOCUSFIRE.2"), new ResearchPage((IArcaneRecipe)recipes.get("FocusFire")) }).setParents(new String[] { "BASICTHAUMATURGY" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  100 */     new ResearchItem("FOCUSFROST", "THAUMATURGY", new AspectList().add(Aspect.WATER, 3).add(Aspect.MAGIC, 3).add(Aspect.COLD, 6), 1, -5, 1, new ItemStack(ConfigItems.itemFocusFrost)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSFROST.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusFrost")) }).setConcealed().setSecondary().setParents(new String[] { "FOCUSFIRE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  109 */     new ResearchItem("FOCUSHELLBAT", "THAUMATURGY", new AspectList().add(Aspect.TRAVEL, 3).add(Aspect.BEAST, 6).add(Aspect.FIRE, 3).add(Aspect.MAGIC, 3), 3, -7, 2, new ItemStack(ConfigItems.itemFocusHellbat)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSHELLBAT.1"), new ResearchPage((InfusionRecipe)recipes.get("FocusHellbat")) }).setHidden().setEntityTriggers(new String[] { "Thaumcraft.Firebat" }).setAspectTriggers(new Aspect[] { Aspect.FIRE }).setParentsHidden(new String[] { "FOCUSFIRE", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  121 */     ThaumcraftApi.addWarpToResearch("FOCUSHELLBAT", 2);
/*  122 */     ThaumcraftApi.addWarpToItem(new ItemStack(ConfigItems.itemFocusHellbat), 1);
/*      */     
/*  124 */     new ResearchItem("FOCUSEXCAVATION", "THAUMATURGY", new AspectList().add(Aspect.EARTH, 3).add(Aspect.ENTROPY, 3).add(Aspect.MAGIC, 3), 0, -3, 2, new ItemStack(ConfigItems.itemFocusExcavation)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSEXCAVATION.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusExcavation")) }).setConcealed().setParents(new String[] { "FOCUSFIRE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  134 */     if (Config.wardedStone) {
/*  135 */       new ResearchItem("FOCUSWARDING", "THAUMATURGY", new AspectList().add(Aspect.EARTH, 6).add(Aspect.ARMOR, 3).add(Aspect.ORDER, 3).add(Aspect.MIND, 3), -2, -4, 3, new ItemStack(ConfigItems.itemFocusWarding)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSWARDING.1"), new ResearchPage((InfusionRecipe)recipes.get("FocusWarding")) }).setConcealed().setParents(new String[] { "FOCUSEXCAVATION", "INFUSION" }).registerResearchItem();
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
/*  147 */     new ResearchItem("FOCUSSHOCK", "THAUMATURGY", new AspectList().add(Aspect.AIR, 3).add(Aspect.ENERGY, 6).add(Aspect.MAGIC, 3), 3, -5, 1, new ItemStack(ConfigItems.itemFocusShock)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSSHOCK.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusShock")) }).setConcealed().setSecondary().setParents(new String[] { "FOCUSFIRE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  156 */     new ResearchItem("FOCUSTRADE", "THAUMATURGY", new AspectList().add(Aspect.EARTH, 3).add(Aspect.EXCHANGE, 6).add(Aspect.MAGIC, 3), 4, -3, 2, new ItemStack(ConfigItems.itemFocusTrade)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSTRADE.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusTrade")) }).setConcealed().setParents(new String[] { "FOCUSFIRE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  167 */     new ResearchItem("FOCUSPORTABLEHOLE", "THAUMATURGY", new AspectList().add(Aspect.TRAVEL, 3).add(Aspect.ENTROPY, 3).add(Aspect.ELDRITCH, 6).add(Aspect.AIR, 3), 7, -2, 2, new ItemStack(ConfigItems.itemFocusPortableHole)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSPORTABLEHOLE.1"), new ResearchPage((InfusionRecipe)recipes.get("FocusPortableHole")) }).setConcealed().setParents(new String[] { "FOCUSTRADE", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  177 */     new ResearchItem("FOCUSPOUCH", "THAUMATURGY", new AspectList().add(Aspect.VOID, 6).add(Aspect.MAGIC, 3).add(Aspect.TOOL, 3), 4, -1, 1, new ItemStack(ConfigItems.itemFocusPouch)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSPOUCH.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusPouch")) }).setParents(new String[] { "FOCUSFIRE" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  187 */     new ResearchItem("CAP_iron", "THAUMATURGY").setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*  190 */     new ResearchItem("CAP_gold", "THAUMATURGY", new AspectList().add(Aspect.METAL, 3).add(Aspect.GREED, 3).add(Aspect.TOOL, 3), 3, 2, 1, new ItemStack(ConfigItems.itemWandCap, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CAP_gold.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandCapGold")) }).setParents(new String[] { "BASICTHAUMATURGY" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  200 */     new ResearchItem("CAP_thaumium", "THAUMATURGY", new AspectList().add(Aspect.METAL, 6).add(Aspect.MAGIC, 6).add(Aspect.TOOL, 3).add(Aspect.AURA, 3), 5, 4, 2, new ItemStack(ConfigItems.itemWandCap, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CAP_thaumium.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandCapThaumiumInert")), new ResearchPage((InfusionRecipe)recipes.get("WandCapThaumium")) }).setParents(new String[] { "CAP_gold", "THAUMIUM", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  215 */     if (Config.foundCopperIngot) {
/*  216 */       new ResearchItem("CAP_copper", "THAUMATURGY", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 3).add(Aspect.TOOL, 3), 2, 0, 1, new ItemStack(ConfigItems.itemWandCap, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CAP_copper.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandCapCopper")) }).setParents(new String[] { "BASICTHAUMATURGY" }).registerResearchItem();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  226 */     if (Config.foundSilverIngot) {
/*  227 */       new ResearchItem("CAP_silver", "THAUMATURGY", new AspectList().add(Aspect.METAL, 3).add(Aspect.GREED, 3).add(Aspect.TOOL, 3).add(Aspect.AURA, 3), 5, 1, 1, new ItemStack(ConfigItems.itemWandCap, 1, 4)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CAP_silver.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandCapSilverInert")), new ResearchPage((InfusionRecipe)recipes.get("WandCapSilver")) }).setConcealed().setParents(new String[] { "CAP_gold", "INFUSION" }).registerResearchItem();
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
/*  240 */     new ResearchItem("ROD_wood", "THAUMATURGY").setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*  244 */     new ResearchItem("ROD_greatwood", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.TREE, 6).add(Aspect.MAGIC, 3), -5, 2, 1, new ItemStack(ConfigItems.itemWandRod, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_greatwood.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodGreatwood")) }).setParents(new String[] { "BASICTHAUMATURGY" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  253 */     new ResearchItem("ROD_reed", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.AIR, 6).add(Aspect.PLANT, 3).add(Aspect.MAGIC, 3), -5, -1, 2, new ItemStack(ConfigItems.itemWandRod, 1, 5)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_reed.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodReed")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_greatwood", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  264 */     new ResearchItem("ROD_blaze", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.FIRE, 6).add(Aspect.ENERGY, 3).add(Aspect.MAGIC, 3), -7, 0, 2, new ItemStack(ConfigItems.itemWandRod, 1, 6)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_blaze.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodBlaze")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_greatwood", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  275 */     new ResearchItem("ROD_obsidian", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.EARTH, 6).add(Aspect.FIRE, 3).add(Aspect.MAGIC, 3), -8, 2, 2, new ItemStack(ConfigItems.itemWandRod, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_obsidian.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodObsidian")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_greatwood", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  286 */     new ResearchItem("ROD_ice", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.COLD, 6).add(Aspect.WATER, 3).add(Aspect.MAGIC, 3), -7, 4, 2, new ItemStack(ConfigItems.itemWandRod, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_ice.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodIce")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_greatwood", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  297 */     new ResearchItem("ROD_quartz", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.ORDER, 6).add(Aspect.CRYSTAL, 3).add(Aspect.MAGIC, 3), -5, 5, 2, new ItemStack(ConfigItems.itemWandRod, 1, 4)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_quartz.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodQuartz")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_greatwood", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  308 */     new ResearchItem("ROD_bone", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.ENTROPY, 6).add(Aspect.UNDEAD, 3).add(Aspect.MAGIC, 3), -3, 0, 2, new ItemStack(ConfigItems.itemWandRod, 1, 7)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_bone.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodBone")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_greatwood", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  319 */     ThaumcraftApi.addWarpToResearch("ROD_bone", 1);
/*      */     
/*  321 */     new ResearchItem("ROD_silverwood", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 6).add(Aspect.TREE, 6).add(Aspect.MAGIC, 9), -2, 5, 3, new ItemStack(ConfigItems.itemWandRod, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_silverwood.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodSilverwood")) }).setParents(new String[] { "ROD_greatwood", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  330 */     ArrayList<IArcaneRecipe> scer = new ArrayList();
/*  331 */     AspectList al1 = new AspectList();
/*  332 */     AspectList al2 = new AspectList();
/*  333 */     AspectList al3 = new AspectList();
/*      */     
/*  335 */     int cost = (int)(ConfigItems.WAND_CAP_IRON.getCraftCost() * ConfigItems.WAND_ROD_WOOD.getCraftCost() * 1.5F);
/*      */     
/*  337 */     for (Aspect as : Aspect.getPrimalAspects()) {
/*  338 */       al1.add(as, cost);
/*      */     }
/*  340 */     ItemStack sceptre1 = new ItemStack(ConfigItems.itemWandCasting, 1, cost);
/*  341 */     ((ItemWandCasting)sceptre1.func_77973_b()).setCap(sceptre1, ConfigItems.WAND_CAP_IRON);
/*      */     
/*  343 */     ((ItemWandCasting)sceptre1.func_77973_b()).setRod(sceptre1, ConfigItems.WAND_ROD_WOOD);
/*      */     
/*  345 */     sceptre1.func_77983_a("sceptre", new NBTTagByte((byte)1));
/*  346 */     ShapedArcaneRecipe r1 = new ShapedArcaneRecipe("SCEPTRE", sceptre1, al1, new Object[] { " TF", " RT", "T  ", Character.valueOf('T'), ConfigItems.WAND_CAP_IRON.getItem(), Character.valueOf('R'), ConfigItems.WAND_ROD_WOOD.getItem(), Character.valueOf('F'), new ItemStack(ConfigItems.itemResource, 1, 15) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  354 */     scer.add(r1);
/*      */     
/*  356 */     cost = (int)(ConfigItems.WAND_CAP_GOLD.getCraftCost() * ConfigItems.WAND_ROD_GREATWOOD.getCraftCost() * 1.5F);
/*      */     
/*  358 */     for (Aspect as : Aspect.getPrimalAspects()) {
/*  359 */       al2.add(as, cost);
/*      */     }
/*  361 */     ItemStack sceptre2 = new ItemStack(ConfigItems.itemWandCasting, 1, cost);
/*  362 */     ((ItemWandCasting)sceptre2.func_77973_b()).setCap(sceptre2, ConfigItems.WAND_CAP_GOLD);
/*      */     
/*  364 */     ((ItemWandCasting)sceptre2.func_77973_b()).setRod(sceptre2, ConfigItems.WAND_ROD_GREATWOOD);
/*      */     
/*  366 */     sceptre2.func_77983_a("sceptre", new NBTTagByte((byte)1));
/*  367 */     ShapedArcaneRecipe r2 = new ShapedArcaneRecipe("SCEPTRE", sceptre2, al2, new Object[] { " TF", " RT", "T  ", Character.valueOf('T'), ConfigItems.WAND_CAP_GOLD.getItem(), Character.valueOf('R'), ConfigItems.WAND_ROD_GREATWOOD.getItem(), Character.valueOf('F'), new ItemStack(ConfigItems.itemResource, 1, 15) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  375 */     scer.add(r2);
/*      */     
/*  377 */     cost = (int)(ConfigItems.WAND_CAP_THAUMIUM.getCraftCost() * ConfigItems.WAND_ROD_SILVERWOOD.getCraftCost() * 1.5F);
/*      */     
/*  379 */     for (Aspect as : Aspect.getPrimalAspects()) {
/*  380 */       al3.add(as, cost);
/*      */     }
/*  382 */     ItemStack sceptre3 = new ItemStack(ConfigItems.itemWandCasting, 1, cost);
/*  383 */     ((ItemWandCasting)sceptre3.func_77973_b()).setCap(sceptre3, ConfigItems.WAND_CAP_THAUMIUM);
/*      */     
/*  385 */     ((ItemWandCasting)sceptre3.func_77973_b()).setRod(sceptre3, ConfigItems.WAND_ROD_SILVERWOOD);
/*      */     
/*  387 */     sceptre3.func_77983_a("sceptre", new NBTTagByte((byte)1));
/*  388 */     ShapedArcaneRecipe r3 = new ShapedArcaneRecipe("SCEPTRE", sceptre3, al3, new Object[] { " TF", " RT", "T  ", Character.valueOf('T'), ConfigItems.WAND_CAP_THAUMIUM.getItem(), Character.valueOf('R'), ConfigItems.WAND_ROD_SILVERWOOD.getItem(), Character.valueOf('F'), new ItemStack(ConfigItems.itemResource, 1, 15) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  396 */     scer.add(r3);
/*      */     
/*  398 */     new ResearchItem("SCEPTRE", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 6).add(Aspect.CRAFT, 6).add(Aspect.TREE, 6).add(Aspect.MAGIC, 9), 0, 4, 3, sceptre3).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SCEPTRE.1"), new ResearchPage((IArcaneRecipe[])scer.toArray(new IArcaneRecipe[0])) }).setConcealed().setParents(new String[] { "ROD_silverwood" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  406 */     new ResearchItem("ROD_greatwood_staff", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.TREE, 6).add(Aspect.MAGIC, 3), -1, 7, 1, new ItemStack(ConfigItems.itemWandRod, 1, 50)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_greatwood_staff.1"), new ResearchPage("tc.research_page.ROD_greatwood_staff.2"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodGreatwoodStaff")) }).setParents(new String[] { "ROD_silverwood" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  418 */     new ResearchItem("ROD_reed_staff", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.AIR, 6).add(Aspect.PLANT, 3).add(Aspect.MAGIC, 3), -5, -2, 2, new ItemStack(ConfigItems.itemWandRod, 1, 55)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_reed_staff.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodReedStaff")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_reed" }).setParentsHidden(new String[] { "ROD_greatwood_staff" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  429 */     new ResearchItem("ROD_blaze_staff", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.FIRE, 6).add(Aspect.ENERGY, 3).add(Aspect.MAGIC, 3), -8, -1, 2, new ItemStack(ConfigItems.itemWandRod, 1, 56)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_blaze_staff.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodBlazeStaff")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_blaze" }).setParentsHidden(new String[] { "ROD_greatwood_staff" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  440 */     new ResearchItem("ROD_obsidian_staff", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.EARTH, 6).add(Aspect.FIRE, 3).add(Aspect.MAGIC, 3), -9, 2, 2, new ItemStack(ConfigItems.itemWandRod, 1, 51)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_obsidian_staff.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodObsidianStaff")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_obsidian" }).setParentsHidden(new String[] { "ROD_greatwood_staff" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  452 */     new ResearchItem("ROD_ice_staff", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.COLD, 6).add(Aspect.WATER, 3).add(Aspect.MAGIC, 3), -8, 5, 2, new ItemStack(ConfigItems.itemWandRod, 1, 53)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_ice_staff.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodIceStaff")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_ice" }).setParentsHidden(new String[] { "ROD_greatwood_staff" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  463 */     new ResearchItem("ROD_quartz_staff", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.ORDER, 6).add(Aspect.CRYSTAL, 3).add(Aspect.MAGIC, 3), -4, 6, 2, new ItemStack(ConfigItems.itemWandRod, 1, 54)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_quartz_staff.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodQuartzStaff")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_quartz" }).setParentsHidden(new String[] { "ROD_greatwood_staff" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  474 */     new ResearchItem("ROD_bone_staff", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.ENTROPY, 6).add(Aspect.UNDEAD, 3).add(Aspect.MAGIC, 3), -2, -1, 2, new ItemStack(ConfigItems.itemWandRod, 1, 57)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_bone_staff.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodBoneStaff")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_bone" }).setParentsHidden(new String[] { "ROD_greatwood_staff" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  485 */     ThaumcraftApi.addWarpToResearch("ROD_bone_staff", 1);
/*      */     
/*  487 */     new ResearchItem("ROD_silverwood_staff", "THAUMATURGY", new AspectList().add(Aspect.TOOL, 6).add(Aspect.TREE, 6).add(Aspect.MAGIC, 9), -1, 5, 3, new ItemStack(ConfigItems.itemWandRod, 1, 52)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_silverwood_staff.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandRodSilverwoodStaff")) }).setSecondary().setConcealed().setParents(new String[] { "ROD_silverwood" }).setParentsHidden(new String[] { "ROD_greatwood_staff" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  502 */     new ResearchItem("WANDPED", "THAUMATURGY", new AspectList().add(Aspect.AURA, 6).add(Aspect.MAGIC, 3).add(Aspect.EXCHANGE, 3).add(Aspect.ENERGY, 3), -9, -6, 2, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 5)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.WANDPED.1"), new ResearchPage((InfusionRecipe)recipes.get("WandPed")) }).setConcealed().setParents(new String[] { "INFUSION", "NODEPRESERVE", "NODESTABILIZER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  512 */     new ResearchItem("VISAMULET", "THAUMATURGY", new AspectList().add(Aspect.AURA, 3).add(Aspect.MAGIC, 6).add(Aspect.ENERGY, 3).add(Aspect.VOID, 3), -9, -8, 2, new ItemStack(ConfigItems.itemAmuletVis, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.VISAMULET.1"), new ResearchPage((InfusionRecipe)recipes.get("VisAmulet")), new ResearchPage("tc.research_page.VISAMULET.2") }).setConcealed().setParents(new String[] { "WANDPED" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  523 */     new ResearchItem("WANDPEDFOC", "THAUMATURGY", new AspectList().add(Aspect.AURA, 6).add(Aspect.MAGIC, 6).add(Aspect.EXCHANGE, 6).add(Aspect.ENERGY, 3).add(Aspect.TOOL, 3), -10, -7, 3, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 8)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.WANDPEDFOC.1"), new ResearchPage((InfusionRecipe)recipes.get("WandPedFocus")) }).setSecondary().setConcealed().setParents(new String[] { "WANDPED" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  534 */     new ResearchItem("NODESTABILIZER", "THAUMATURGY", new AspectList().add(Aspect.AURA, 4).add(Aspect.ORDER, 4).add(Aspect.ENERGY, 4), -7, -4, 1, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 9)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODESTABILIZER.1"), new ResearchPage((IArcaneRecipe)recipes.get("NodeStabilizer")), new ResearchPage("tc.research_page.NODESTABILIZER.2") }).setParents(new String[] { "NODEPRESERVE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  546 */     new ResearchItem("NODESTABILIZERADV", "THAUMATURGY", new AspectList().add(Aspect.AURA, 9).add(Aspect.MAGIC, 6).add(Aspect.ORDER, 6).add(Aspect.ENERGY, 6), -8, -3, 2, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 9)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODESTABILIZERADV.1"), new ResearchPage((InfusionRecipe)recipes.get("NodeStabilizerAdv")) }).setSecondary().setConcealed().setParents(new String[] { "NODESTABILIZER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  557 */     new ResearchItem("VISPOWER", "THAUMATURGY", new AspectList().add(Aspect.AURA, 3).add(Aspect.MECHANISM, 3).add(Aspect.ENERGY, 6), -5, -6, 2, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 11)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.VISPOWER.1"), new ResearchPage((IArcaneRecipe)recipes.get("NodeTransducer")), new ResearchPage("tc.research_page.VISPOWER.2"), new ResearchPage("tc.research_page.VISPOWER.3"), new ResearchPage((IArcaneRecipe)recipes.get("NodeRelay")), new ResearchPage("tc.research_page.VISPOWER.4"), new ResearchPage("tc.research_page.VISPOWER.5") }).setParents(new String[] { "NODESTABILIZER" }).setSpecial().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  573 */     new ResearchItem("VISCHARGERELAY", "THAUMATURGY", new AspectList().add(Aspect.MAGIC, 3).add(Aspect.AURA, 3).add(Aspect.MECHANISM, 3).add(Aspect.ENERGY, 6), -7, -6, 2, new ItemStack(ConfigBlocks.blockMetalDevice, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.VISCHARGERELAY.1"), new ResearchPage((IArcaneRecipe)recipes.get("NodeChargeRelay")) }).setParents(new String[] { "VISPOWER", "WANDPED" }).setParentsHidden(new String[] { "ROD_greatwood" }).setSecondary().setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  585 */     new ResearchItem("FOCALMANIPULATION", "THAUMATURGY", new AspectList().add(Aspect.MAGIC, 8).add(Aspect.TOOL, 8).add(Aspect.CRAFT, 5).add(Aspect.CRYSTAL, 5).add(Aspect.ENERGY, 5), -3, -8, 2, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 13)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCALMANIPULATION.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocalManipulator")), new ResearchPage("tc.research_page.FOCALMANIPULATION.2") }).setParentsHidden(new String[] { "INFUSION", "FOCUSFIRE" }).setParents(new String[] { "VISPOWER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  598 */     new ResearchItem("VAMPBAT", "THAUMATURGY", new AspectList().add(Aspect.HUNGER, 5).add(Aspect.LIFE, 5).add(Aspect.MAGIC, 5), 4, -8, 1, new ResourceLocation("thaumcraft", "textures/foci/vampirebats.png")).setPages(new ResearchPage[] { new ResearchPage("focus.upgrade.vampirebats.text") }).setSecondary().setParents(new String[] { "FOCUSHELLBAT" }).setParentsHidden(new String[] { "FOCALMANIPULATION" }).registerResearchItem();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initArtificeResearch()
/*      */   {
/*  607 */     new ResearchItem("BASICARTIFACE", "ARTIFICE", new AspectList(), 0, 1, 0, new ItemStack(ConfigItems.itemResource, 1, 15)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BASICARTIFACE.1"), new ResearchPage((IArcaneRecipe)recipes.get("PrimalCharm")), new ResearchPage((IRecipe)recipes.get("MundaneAmulet")), new ResearchPage((IRecipe)recipes.get("MundaneRing")), new ResearchPage((IRecipe)recipes.get("MundaneBelt")), new ResearchPage((IArcaneRecipe)recipes.get("MirrorGlass")) }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  618 */     new ResearchItem("ARCANESTONE", "ARTIFICE", new AspectList(), 5, -2, 0, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCANESTONE.1"), new ResearchPage((IArcaneRecipe)recipes.get("ArcaneStone1")), new ResearchPage((IRecipe)recipes.get("ArcaneStone2")), new ResearchPage((IRecipe)recipes.get("ArcaneStone3")), new ResearchPage((IRecipe)recipes.get("ArcaneStone4")) }).setStub().setAutoUnlock().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  628 */     new ResearchItem("GRATE", "ARTIFICE", new AspectList(), 2, -1, 0, new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GRATE.1"), new ResearchPage((IRecipe)recipes.get("Grate")) }).setStub().setAutoUnlock().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  634 */     new ResearchItem("TABLE", "ARTIFICE", new AspectList(), 0, -1, 0, new ItemStack(ConfigBlocks.blockTable)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TABLE.1"), new ResearchPage((IRecipe)recipes.get("Table")) }).setStub().setAutoUnlock().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  640 */     new ResearchItem("ARCTABLE", "ARTIFICE", new AspectList(), -1, -3, 0, new ItemStack(ConfigBlocks.blockTable, 1, 15)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCTABLE.1"), new ResearchPage((List)recipes.get("ArcTable")) }).setStub().setAutoUnlock().setRound().setParents(new String[] { "TABLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  647 */     new ResearchItem("RESTABLE", "ARTIFICE", new AspectList(), 1, -3, 0, new ItemStack(ConfigBlocks.blockTable, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RESTABLE.1"), new ResearchPage((List)recipes.get("ResTable")) }).setStub().setAutoUnlock().setRound().setParents(new String[] { "TABLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  654 */     new ResearchItem("THAUMOMETER", "ARTIFICE", new AspectList(), 2, 1, 0, new ItemStack(ConfigItems.itemThaumometer)).setStub().setAutoUnlock().setRound().setPages(new ResearchPage[] { new ResearchPage("tc.research_page.THAUMOMETER.1"), new ResearchPage((IRecipe)recipes.get("Thaumometer")) }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  665 */     new ResearchItem("PAVETRAVEL", "ARTIFICE", new AspectList().add(Aspect.TRAVEL, 3).add(Aspect.EARTH, 3).add(Aspect.FLIGHT, 3), 4, -4, 1, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PAVETRAVEL.1"), new ResearchPage((IArcaneRecipe)recipes.get("PaveTravel")) }).setParents(new String[] { "ARCANESTONE" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  675 */     new ResearchItem("PAVEWARD", "ARTIFICE", new AspectList().add(Aspect.MOTION, 3).add(Aspect.TRAP, 3).add(Aspect.BEAST, 3), 6, -4, 1, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PAVEWARD.1"), new ResearchPage((IArcaneRecipe)recipes.get("PaveWard")), new ResearchPage("tc.research_page.PAVEWARD.2") }).setParents(new String[] { "ARCANESTONE" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  687 */     new ResearchItem("GOGGLES", "ARTIFICE", new AspectList().add(Aspect.SENSES, 3).add(Aspect.AURA, 3).add(Aspect.MAGIC, 3), 4, 1, 1, new ItemStack(ConfigItems.itemGoggles)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOGGLES.1"), new ResearchPage((IArcaneRecipe)recipes.get("Goggles")) }).setParents(new String[] { "THAUMOMETER" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  697 */     new ResearchItem("ARCANEEAR", "ARTIFICE", new AspectList().add(Aspect.SENSES, 3).add(Aspect.ENERGY, 3).add(Aspect.AIR, 3), 6, 0, 1, new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCANEEAR.1"), new ResearchPage((IArcaneRecipe)recipes.get("ArcaneEar")) }).setParents(new String[] { "GOGGLES" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  707 */     new ResearchItem("SINSTONE", "ARTIFICE", new AspectList().add(Aspect.SENSES, 3).add(Aspect.DARKNESS, 3).add(Aspect.ELDRITCH, 3).add(Aspect.AURA, 3), 6, 2, 1, new ItemStack(ConfigItems.itemCompassStone, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SINSTONE.1"), new ResearchPage((InfusionRecipe)recipes.get("SinStone")) }).setParents(new String[] { "GOGGLES" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  716 */     ThaumcraftApi.addWarpToResearch("SINSTONE", 2);
/*  717 */     ThaumcraftApi.addWarpToItem(new ItemStack(ConfigItems.itemCompassStone), 1);
/*      */     
/*  719 */     new ResearchItem("LEVITATOR", "ARTIFICE", new AspectList().add(Aspect.MOTION, 3).add(Aspect.FLIGHT, 3).add(Aspect.AIR, 3), -3, -3, 1, new ItemStack(ConfigBlocks.blockLifter)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.LEVITATOR.1"), new ResearchPage((IArcaneRecipe)recipes.get("Levitator")) }).setConcealed().setParents(new String[] { "NITOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  729 */     new ResearchItem("INFERNALFURNACE", "ARTIFICE", new AspectList().add(Aspect.FIRE, 6).add(Aspect.METAL, 3).add(Aspect.CRAFT, 3).add(Aspect.AURA, 3), -4, -1, 2, new ResourceLocation("thaumcraft", "textures/misc/r_infernalfurnace.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.INFERNALFURNACE.1"), new ResearchPage((List)recipes.get("InfernalFurnace")), new ResearchPage("tc.research_page.INFERNALFURNACE.2") }).setParents(new String[] { "NITOR", "ALUMENTUM" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  740 */     ThaumcraftApi.addWarpToResearch("INFERNALFURNACE", 2);
/*      */     
/*  742 */     new ResearchItem("BELLOWS", "ARTIFICE", new AspectList().add(Aspect.AIR, 6).add(Aspect.MECHANISM, 3).add(Aspect.MOTION, 3), -6, -2, 1, new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BELLOWS.1"), new ResearchPage((IArcaneRecipe)recipes.get("Bellows")), new ResearchPage("tc.research_page.BELLOWS.2") }).setParents(new String[] { "INFERNALFURNACE" }).setSecondary().setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  753 */     new ResearchItem("ENCHFABRIC", "ARTIFICE", new AspectList().add(Aspect.CLOTH, 3).add(Aspect.MAGIC, 3), 0, 3, 1, new ItemStack(ConfigItems.itemResource, 1, 7)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ENCHFABRIC.1"), new ResearchPage((IArcaneRecipe)recipes.get("EnchantedFabric")), new ResearchPage("tc.research_page.ENCHFABRIC.2"), new ResearchPage((IArcaneRecipe)recipes.get("RobeChest")), new ResearchPage((IArcaneRecipe)recipes.get("RobeLegs")), new ResearchPage((IArcaneRecipe)recipes.get("RobeBoots")) }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  769 */     new ResearchItem("INFUSION", "ARTIFICE", new AspectList().add(Aspect.MAGIC, 6).add(Aspect.MECHANISM, 3).add(Aspect.CRAFT, 6), -4, 5, 2, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.INFUSION.1"), new ResearchPage((IArcaneRecipe)recipes.get("InfusionMatrix")), new ResearchPage((IArcaneRecipe)recipes.get("ArcanePedestal")), new ResearchPage("tc.research_page.INFUSION.2"), new ResearchPage((List)recipes.get("InfusionAltar")), new ResearchPage("tc.research_page.INFUSION.3"), new ResearchPage("tc.research_page.INFUSION.4"), new ResearchPage("tc.research_page.INFUSION.5") }).setParents(new String[] { "DISTILESSENTIA" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  787 */     new ResearchItem("FLUXSCRUB", "ARTIFICE", new AspectList().add(Aspect.MAGIC, 3).add(Aspect.TRAP, 3).add(Aspect.AIR, 3).add(Aspect.WATER, 3), -8, -3, 1, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 14)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FLUXSCRUB.1"), new ResearchPage((IArcaneRecipe)recipes.get("FluxScrubber")) }).setParentsHidden(new String[] { "INFUSION" }).setParents(new String[] { "VISPOWER", "BELLOWS", "TUBES" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  796 */     new ResearchItem("RUNICARMOR", "ARTIFICE", new AspectList().add(Aspect.ARMOR, 6).add(Aspect.AIR, 3).add(Aspect.MAGIC, 3).add(Aspect.ENERGY, 3).add(Aspect.MIND, 3), 3, 4, 3, new ItemStack(ConfigItems.itemRingRunic, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RUNICARMOR.1"), new ResearchPage("tc.research_page.RUNICARMOR.2"), new ResearchPage((InfusionRecipe)recipes.get("RunicRing")), new ResearchPage((InfusionRecipe)recipes.get("RunicAmulet")), new ResearchPage((InfusionRecipe)recipes.get("RunicGirdle")) }).setParentsHidden(new String[] { "INFUSION" }).setParents(new String[] { "ENCHFABRIC" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  812 */     new ResearchItem("RUNICCHARGED", "ARTIFICE", new AspectList().add(Aspect.MAGIC, 3).add(Aspect.ARMOR, 3).add(Aspect.ENERGY, 6), 2, 3, 2, new ItemStack(ConfigItems.itemRingRunic, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RUNICCHARGED.1"), new ResearchPage((InfusionRecipe)recipes.get("RunicRingCharged")) }).setParents(new String[] { "RUNICARMOR" }).setSecondary().setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  823 */     new ResearchItem("RUNICHEALING", "ARTIFICE", new AspectList().add(Aspect.MAGIC, 3).add(Aspect.ARMOR, 3).add(Aspect.HEAL, 4).add(Aspect.WATER, 4), 4, 3, 2, new ItemStack(ConfigItems.itemRingRunic, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RUNICHEALING.1"), new ResearchPage((InfusionRecipe)recipes.get("RunicRingHealing")) }).setParents(new String[] { "RUNICARMOR" }).setSecondary().setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  834 */     new ResearchItem("RUNICKINETIC", "ARTIFICE", new AspectList().add(Aspect.MAGIC, 3).add(Aspect.ARMOR, 3).add(Aspect.AIR, 6), 2, 5, 2, new ItemStack(ConfigItems.itemGirdleRunic, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RUNICKINETIC.1"), new ResearchPage((InfusionRecipe)recipes.get("RunicGirdleKinetic")) }).setParents(new String[] { "RUNICARMOR" }).setSecondary().setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  844 */     new ResearchItem("RUNICEMERGENCY", "ARTIFICE", new AspectList().add(Aspect.MAGIC, 3).add(Aspect.ARMOR, 3).add(Aspect.EARTH, 4).add(Aspect.VOID, 4), 4, 5, 2, new ItemStack(ConfigItems.itemAmuletRunic, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RUNICEMERGENCY.1"), new ResearchPage((InfusionRecipe)recipes.get("RunicAmuletEmergency")) }).setParents(new String[] { "RUNICARMOR" }).setSecondary().setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  855 */     ArrayList<IArcaneRecipe> banners = new ArrayList();
/*  856 */     for (int a = 0; a < 16; a++) {
/*  857 */       banners.add((IArcaneRecipe)recipes.get("Banner_" + a));
/*      */     }
/*  859 */     ItemStack is = new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 8);
/*  860 */     is.func_77982_d(new NBTTagCompound());
/*  861 */     is.field_77990_d.func_74774_a("color", (byte)10);
/*  862 */     new ResearchItem("BANNERS", "ARTIFICE", new AspectList().add(Aspect.SENSES, 3).add(Aspect.CLOTH, 3).add(Aspect.MAGIC, 1), 4, 8, 1, is).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BANNERS.1"), new ResearchPage((IArcaneRecipe[])banners.toArray(new IArcaneRecipe[0])) }).setHidden().setItemTriggers(new ItemStack[] { new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 8) }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  871 */     ArrayList<InfusionRecipe> raug = new ArrayList();
/*  872 */     for (int a = 0; a <= 4; a++) {
/*  873 */       ItemStack in = new ItemStack(ConfigItems.itemChestRobe);
/*  874 */       if (a > 0) in.func_77983_a("RS.HARDEN", new NBTTagByte((byte)a));
/*  875 */       raug.add(new InfusionRunicAugmentRecipe(in));
/*      */     }
/*      */     
/*  878 */     new ResearchItem("RUNICAUGMENTATION", "ARTIFICE", new AspectList().add(Aspect.MAGIC, 3).add(Aspect.ARMOR, 3).add(Aspect.EXCHANGE, 4).add(Aspect.GREED, 4), 6, 4, 1, new ResourceLocation("thaumcraft", "textures/misc/r_runicupg.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RUNICAUGMENTATION.1"), new ResearchPage((InfusionRecipe[])raug.toArray(new InfusionRecipe[0])), new ResearchPage("tc.research_page.RUNICAUGMENTATION.2") }).setParents(new String[] { "RUNICARMOR" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  889 */     new ResearchItem("BOOTSTRAVELLER", "ARTIFICE", new AspectList().add(Aspect.TRAVEL, 3).add(Aspect.EARTH, 3).add(Aspect.FLIGHT, 3).add(Aspect.WATER, 3), -1, 5, 2, new ItemStack(ConfigItems.itemBootsTraveller)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BOOTSTRAVELLER.1"), new ResearchPage((InfusionRecipe)recipes.get("BootsTraveller")) }).setParents(new String[] { "ENCHFABRIC", "INFUSION" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  900 */     new ResearchItem("HOVERHARNESS", "ARTIFICE", new AspectList().add(Aspect.FLIGHT, 6).add(Aspect.TRAVEL, 6).add(Aspect.AIR, 6).add(Aspect.MECHANISM, 3), 1, 7, 3, new ItemStack(ConfigItems.itemHoverHarness)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.HOVERHARNESS.1"), new ResearchPage((InfusionRecipe)recipes.get("HoverHarness")), new ResearchPage("tc.research_page.HOVERHARNESS.2") }).setParents(new String[] { "BOOTSTRAVELLER" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  912 */     new ResearchItem("HOVERGIRDLE", "ARTIFICE", new AspectList().add(Aspect.FLIGHT, 6).add(Aspect.TRAVEL, 3).add(Aspect.AIR, 3).add(Aspect.MOTION, 6), 2, 7, 3, new ItemStack(ConfigItems.itemGirdleHover)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.HOVERGIRDLE.1"), new ResearchPage((InfusionRecipe)recipes.get("HoverGirdle")) }).setHidden().setAspectTriggers(new Aspect[] { Aspect.FLIGHT }).setParents(new String[] { "HOVERHARNESS" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  923 */     if (Config.allowMirrors) {
/*  924 */       new ResearchItem("MIRROR", "ARTIFICE", new AspectList().add(Aspect.TRAVEL, 6).add(Aspect.ELDRITCH, 3).add(Aspect.DARKNESS, 3).add(Aspect.CRYSTAL, 3), -1, 8, 2, new ItemStack(ConfigBlocks.blockMirror, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MIRROR.1"), new ResearchPage("tc.research_page.MIRROR.2"), new ResearchPage((InfusionRecipe)recipes.get("Mirror")), new ResearchPage("tc.research_page.MIRROR.3") }).setHidden().setEntityTriggers(new String[] { "Enderman" }).setItemTriggers(new ItemStack[] { new ItemStack(Items.field_151079_bi), new ItemStack(Blocks.field_150427_aO, 1, 32767), new ItemStack(Blocks.field_150384_bq, 1, 32767), new ItemStack(Blocks.field_150378_br, 1, 32767) }).setParents(new String[] { "INFUSION" }).registerResearchItem();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  946 */       new ResearchItem("MIRRORHAND", "ARTIFICE", new AspectList().add(Aspect.TOOL, 6).add(Aspect.ELDRITCH, 3).add(Aspect.CRYSTAL, 3).add(Aspect.TRAVEL, 3), 1, 9, 2, new ItemStack(ConfigItems.itemHandMirror)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MIRRORHAND.1"), new ResearchPage((InfusionRecipe)recipes.get("MirrorHand")) }).setConcealed().setSecondary().setParents(new String[] { "MIRROR" }).registerResearchItem();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  956 */       new ResearchItem("MIRRORESSENTIA", "ARTIFICE", new AspectList().add(Aspect.TRAVEL, 6).add(Aspect.ELDRITCH, 3).add(Aspect.WATER, 3).add(Aspect.MAGIC, 3), -1, 10, 2, new ItemStack(ConfigBlocks.blockMirror, 1, 6)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MIRRORESSENTIA.1"), new ResearchPage((InfusionRecipe)recipes.get("MirrorEssentia")), new ResearchPage("tc.research_page.MIRRORESSENTIA.2") }).setSecondary().setConcealed().setParents(new String[] { "MIRROR" }).registerResearchItem();
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
/*  971 */     new ResearchItem("ARCANEBORE", "ARTIFICE", new AspectList().add(Aspect.MINE, 6).add(Aspect.MOTION, 3).add(Aspect.MECHANISM, 3).add(Aspect.TOOL, 3), -3, 8, 2, new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 5)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCANEBORE.1"), new ResearchPage((InfusionRecipe)recipes.get("ArcaneBore")), new ResearchPage("tc.research_page.ARCANEBORE.2"), new ResearchPage((IArcaneRecipe)recipes.get("ArcaneBoreBase")), new ResearchPage("tc.research_page.ARCANEBORE.3") }).setConcealed().setParents(new String[] { "FOCUSEXCAVATION", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  986 */     new ResearchItem("ARCANELAMP", "ARTIFICE", new AspectList().add(Aspect.LIGHT, 3).add(Aspect.SENSES, 3).add(Aspect.DARKNESS, 3), -3, 1, 1, new ItemStack(ConfigBlocks.blockMetalDevice, 1, 7)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCANELAMP.1"), new ResearchPage((IArcaneRecipe)recipes.get("ArcaneLamp")), new ResearchPage("ARCANEBORE", "tc.research_page.ARCANELAMP.2") }).setSecondary().setParents(new String[] { "NITOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  998 */     new ResearchItem("LAMPGROWTH", "ARTIFICE", new AspectList().add(Aspect.LIGHT, 3).add(Aspect.PLANT, 6).add(Aspect.LIFE, 3).add(Aspect.CROP, 3), -4, 3, 2, new ItemStack(ConfigBlocks.blockMetalDevice, 1, 8)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.LAMPGROWTH.1"), new ResearchPage((InfusionRecipe)recipes.get("LampGrowth")) }).setHidden().setAspectTriggers(new Aspect[] { Aspect.LIGHT, Aspect.CROP }).setParents(new String[] { "ARCANELAMP", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1009 */     new ResearchItem("LAMPFERTILITY", "ARTIFICE", new AspectList().add(Aspect.BEAST, 6).add(Aspect.LIFE, 6).add(Aspect.LIGHT, 3), -2, 3, 2, new ItemStack(ConfigBlocks.blockMetalDevice, 1, 13)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.LAMPFERTILITY.1"), new ResearchPage((InfusionRecipe)recipes.get("LampFertility")) }).setHidden().setAspectTriggers(new Aspect[] { Aspect.LIGHT, Aspect.LIFE }).setParents(new String[] { "ARCANELAMP", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1020 */     new ResearchItem("BONEBOW", "ARTIFICE", new AspectList().add(Aspect.WEAPON, 3).add(Aspect.AIR, 3).add(Aspect.MOTION, 3), -7, 1, 1, new ItemStack(ConfigItems.itemBowBone)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BONEBOW.1"), new ResearchPage((IArcaneRecipe)recipes.get("BoneBow")) }).setHidden().setItemTriggers(new ItemStack[] { new ItemStack(Items.field_151031_f, 1, 32767), new ItemStack(Items.field_151103_aS) }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1034 */     ArrayList<IArcaneRecipe> rcbb = new ArrayList();
/* 1035 */     for (int a = 0; a < 6; a++) {
/* 1036 */       rcbb.add((IArcaneRecipe)recipes.get("PrimalArrow_" + a));
/*      */     }
/* 1038 */     new ResearchItem("PRIMALARROW", "ARTIFICE", new AspectList().add(Aspect.WEAPON, 3).add(Aspect.AIR, 3).add(Aspect.FIRE, 3).add(Aspect.WATER, 3).add(Aspect.EARTH, 3).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), -9, 0, 2, new ItemStack(ConfigItems.itemPrimalArrow, 1, 32767)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PRIMALARROW.1"), new ResearchPage((IArcaneRecipe[])rcbb.toArray(new IArcaneRecipe[0])), new ResearchPage("tc.research_page.PRIMALARROW.2"), new ResearchPage("tc.research_page.PRIMALARROW.3") }).setConcealed().setParents(new String[] { "BONEBOW" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1051 */     new ResearchItem("ELEMENTALAXE", "ARTIFICE", new AspectList().add(Aspect.TOOL, 3).add(Aspect.WATER, 3).add(Aspect.MOTION, 3), -7, 4, 2, new ItemStack(ConfigItems.itemAxeElemental)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ELEMENTALAXE.1"), new ResearchPage((InfusionRecipe)recipes.get("ElementalAxe")), new ResearchPage("tc.research_page.ELEMENTALAXE.2") }).setParents(new String[] { "THAUMIUM", "INFUSION" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1063 */     new ResearchItem("ELEMENTALPICK", "ARTIFICE", new AspectList().add(Aspect.TOOL, 3).add(Aspect.FIRE, 3).add(Aspect.SENSES, 3), -7, 3, 2, new ItemStack(ConfigItems.itemPickElemental)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ELEMENTALPICK.1"), new ResearchPage((InfusionRecipe)recipes.get("ElementalPick")), new ResearchPage("tc.research_page.ELEMENTALPICK.2") }).setParents(new String[] { "THAUMIUM", "INFUSION" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1074 */     new ResearchItem("ELEMENTALSWORD", "ARTIFICE", new AspectList().add(Aspect.WEAPON, 3).add(Aspect.AIR, 3).add(Aspect.ENERGY, 3), -7, 5, 2, new ItemStack(ConfigItems.itemSwordElemental)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ELEMENTALSWORD.1"), new ResearchPage((InfusionRecipe)recipes.get("ElementalSword")) }).setParents(new String[] { "THAUMIUM", "INFUSION" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1085 */     new ResearchItem("ELEMENTALSHOVEL", "ARTIFICE", new AspectList().add(Aspect.TOOL, 3).add(Aspect.EARTH, 3).add(Aspect.CRAFT, 3), -7, 6, 2, new ItemStack(ConfigItems.itemShovelElemental)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ELEMENTALSHOVEL.1"), new ResearchPage((InfusionRecipe)recipes.get("ElementalShovel")), new ResearchPage("tc.research_page.ELEMENTALSHOVEL.2") }).setParents(new String[] { "THAUMIUM", "INFUSION" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1096 */     new ResearchItem("ELEMENTALHOE", "ARTIFICE", new AspectList().add(Aspect.TOOL, 3).add(Aspect.LIFE, 3).add(Aspect.CROP, 3), -7, 7, 2, new ItemStack(ConfigItems.itemHoeElemental)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ELEMENTALHOE.1"), new ResearchPage((InfusionRecipe)recipes.get("ElementalHoe")) }).setParents(new String[] { "THAUMIUM", "INFUSION" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1106 */     if (Config.wardedStone) {
/* 1107 */       new ResearchItem("WARDEDARCANA", "ARTIFICE", new AspectList().add(Aspect.TOOL, 6).add(Aspect.MIND, 3).add(Aspect.MECHANISM, 3).add(Aspect.ARMOR, 3), -5, -4, 2, new ItemStack(ConfigItems.itemArcaneDoor)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.WARDEDARCANA.1"), new ResearchPage((IArcaneRecipe)recipes.get("ArcaneDoor")), new ResearchPage("tc.research_page.WARDEDARCANA.2"), new ResearchPage((IArcaneRecipe)recipes.get("IronKey")), new ResearchPage((IArcaneRecipe)recipes.get("GoldKey")), new ResearchPage("tc.research_page.WARDEDARCANA.3"), new ResearchPage((IArcaneRecipe)recipes.get("ArcanePressurePlate")), new ResearchPage("tc.research_page.WARDEDARCANA.4"), new ResearchPage((IArcaneRecipe)recipes.get("WardedGlass")) }).setParents(new String[] { "THAUMIUM" }).registerResearchItem();
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
/* 1129 */     new ResearchItem("JARBRAIN", "ARTIFICE", new AspectList().add(Aspect.HUNGER, 3).add(Aspect.MIND, 3).add(Aspect.UNDEAD, 3).add(Aspect.GREED, 3), -5, 9, 2, new ItemStack(ConfigBlocks.blockJar, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.JARBRAIN.1"), new ResearchPage((InfusionRecipe)recipes.get("JarBrain")) }).setParents(new String[] { "INFUSION" }).setHidden().setItemTriggers(new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 3) }).setEntityTriggers(new String[] { "Thaumcraft.BrainyZombie", "Thaumcraft.GiantBrainyZombie" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1143 */     ThaumcraftApi.addWarpToResearch("JARBRAIN", 3);
/* 1144 */     ThaumcraftApi.addWarpToItem(new ItemStack(ConfigBlocks.blockJar, 1, 1), 1);
/*      */     
/* 1146 */     new ResearchItem("INFUSIONENCHANTMENT", "ARTIFICE", new AspectList().add(Aspect.MAGIC, 6).add(Aspect.MIND, 3).add(Aspect.WEAPON, 3).add(Aspect.ARMOR, 3).add(Aspect.TOOL, 3), -6, 11, 3, new ResourceLocation("thaumcraft", "textures/misc/r_enchant.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.1"), new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.2"), new ResearchPage("tc.research_page.INFUSIONENCHANTMENT.3"), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnchRepair")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnchHaste")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch0")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch1")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch2")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch3")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch4")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch5")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch6")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch7")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch8")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch9")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch10")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch11")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch12")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch13")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch14")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch15")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch16")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch17")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch18")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch19")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch20")), new ResearchPage((InfusionEnchantmentRecipe)recipes.get("InfEnch21")) }).setConcealed().setParents(new String[] { "JARBRAIN" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1209 */     new ResearchItem("ARMORFORTRESS", "ARTIFICE", new AspectList().add(Aspect.METAL, 3).add(Aspect.ARMOR, 5).add(Aspect.CRAFT, 5), -8, 9, 2, new ItemStack(ConfigItems.itemHelmetFortress)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARMORFORTRESS.1"), new ResearchPage("tc.research_page.ARMORFORTRESS.2"), new ResearchPage((InfusionRecipe)recipes.get("ThaumiumFortressHelm")), new ResearchPage((InfusionRecipe)recipes.get("ThaumiumFortressChest")), new ResearchPage((InfusionRecipe)recipes.get("ThaumiumFortressLegs")) }).setParents(new String[] { "THAUMIUM", "INFUSIONENCHANTMENT" }).setHidden().setAspectTriggers(new Aspect[] { Aspect.ARMOR }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1223 */     new ResearchItem("HELMGOGGLES", "ARTIFICE", new AspectList().add(Aspect.SENSES, 5).add(Aspect.AURA, 3).add(Aspect.ARMOR, 3), -9, 7, 2, new ItemStack(ConfigItems.itemGoggles)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.HELMGOGGLES.1"), new ResearchPage((InfusionRecipe)recipes.get("HelmGoggles")) }).setParentsHidden(new String[] { "GOGGLES" }).setParents(new String[] { "ARMORFORTRESS" }).setConcealed().setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1230 */     new ResearchItem("MASKGRINNINGDEVIL", "ARTIFICE", new AspectList().add(Aspect.HEAL, 5).add(Aspect.MIND, 5).add(Aspect.ARMOR, 3), -10, 8, 2, new ResourceLocation("thaumcraft", "textures/misc/r_mask0.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MASKGRINNINGDEVIL.1"), new ResearchPage((InfusionRecipe)recipes.get("MaskGrinningDevil")) }).setParents(new String[] { "ARMORFORTRESS" }).setConcealed().setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1236 */     new ResearchItem("MASKANGRYGHOST", "ARTIFICE", new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.DEATH, 5).add(Aspect.ARMOR, 3), -10, 9, 2, new ResourceLocation("thaumcraft", "textures/misc/r_mask1.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MASKANGRYGHOST.1"), new ResearchPage((InfusionRecipe)recipes.get("MaskAngryGhost")) }).setParents(new String[] { "ARMORFORTRESS" }).setConcealed().setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1242 */     new ResearchItem("MASKSIPPINGFIEND", "ARTIFICE", new AspectList().add(Aspect.UNDEAD, 5).add(Aspect.LIFE, 5).add(Aspect.ARMOR, 3), -10, 10, 2, new ResourceLocation("thaumcraft", "textures/misc/r_mask2.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.MASKSIPPINGFIEND.1"), new ResearchPage((InfusionRecipe)recipes.get("MaskSippingFiend")) }).setParents(new String[] { "ARMORFORTRESS" }).setConcealed().setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1251 */     ThaumcraftApi.addWarpToResearch("MASKANGRYGHOST", 1);
/* 1252 */     ThaumcraftApi.addWarpToResearch("MASKSIPPINGFIEND", 1);
/*      */   }
/*      */   
/*      */   private static void initAlchemyResearch() {
/* 1256 */     new ResearchItem("PHIAL", "ALCHEMY", new AspectList(), 0, -2, 0, new ItemStack(ConfigItems.itemEssence, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PHIAL.1"), new ResearchPage((IRecipe)recipes.get("Phial")) }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1262 */     ArrayList<CrucibleRecipe> cruc = new ArrayList();
/* 1263 */     for (int a = 0; a < 6; a++) {
/* 1264 */       cruc.add((CrucibleRecipe)recipes.get("BalancedShard_" + a));
/*      */     }
/*      */     
/* 1267 */     new ResearchItem("CRUCIBLE", "ALCHEMY", new AspectList(), 0, 0, 0, new ItemStack(ConfigBlocks.blockMetalDevice, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CRUCIBLE.1"), new ResearchPage("tc.research_page.CRUCIBLE.2"), new ResearchPage("tc.research_page.CRUCIBLE.3"), new ResearchPage((List)recipes.get("Crucible")), new ResearchPage("tc.research_page.CRUCIBLE.4"), new ResearchPage((CrucibleRecipe[])cruc.toArray(new CrucibleRecipe[0])), new ResearchPage("tc.research_page.CRUCIBLE.5"), new ResearchPage(new ItemStack(ConfigItems.itemShard, 1, 6)) }).setStub().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1281 */     new ResearchItem("NITOR", "ALCHEMY", new AspectList().add(Aspect.LIGHT, 3).add(Aspect.FIRE, 1), 2, -1, 1, new ItemStack(ConfigItems.itemResource, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NITOR.1"), new ResearchPage((CrucibleRecipe)recipes.get("Nitor")) }).setParents(new String[] { "CRUCIBLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1288 */     new ResearchItem("ALUMENTUM", "ALCHEMY", new AspectList().add(Aspect.ENERGY, 3).add(Aspect.FIRE, 1), 2, 1, 1, new ItemStack(ConfigItems.itemResource, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ALUMENTUM.1"), new ResearchPage((CrucibleRecipe)recipes.get("Alumentum")) }).setParents(new String[] { "CRUCIBLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1297 */     new ResearchItem("ALCHEMICALDUPLICATION", "ALCHEMY", new AspectList().add(Aspect.MAGIC, 3).add(Aspect.GREED, 3).add(Aspect.CRAFT, 3), -4, 0, 1, new ResourceLocation("thaumcraft", "textures/misc/r_alchmult.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ALCHEMICALDUPLICATION.1"), new ResearchPage((CrucibleRecipe)recipes.get("AltGunpowder")), new ResearchPage((CrucibleRecipe)recipes.get("AltSlime")), new ResearchPage((CrucibleRecipe)recipes.get("AltClay")), new ResearchPage((CrucibleRecipe)recipes.get("AltGlowstone")), new ResearchPage((CrucibleRecipe)recipes.get("AltInk")) }).setConcealed().setSecondary().setParents(new String[] { "TALLOW" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1316 */     new ResearchItem("ALCHEMICALMANUFACTURE", "ALCHEMY", new AspectList().add(Aspect.MAGIC, 3).add(Aspect.EXCHANGE, 3).add(Aspect.CRAFT, 3), -5, -2, 1, new ResourceLocation("thaumcraft", "textures/misc/r_alchman.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ALCHEMICALMANUFACTURE.1"), new ResearchPage((CrucibleRecipe)recipes.get("AltWeb")), new ResearchPage((CrucibleRecipe)recipes.get("AltMossyCobble")), new ResearchPage((CrucibleRecipe)recipes.get("AltIce")) }).setConcealed().setSecondary().setParents(new String[] { "ALCHEMICALDUPLICATION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1330 */     new ResearchItem("ENTROPICPROCESSING", "ALCHEMY", new AspectList().add(Aspect.MAGIC, 1).add(Aspect.ENTROPY, 3).add(Aspect.CRAFT, 1), -6, 1, 1, new ResourceLocation("thaumcraft", "textures/misc/r_alchent.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ENTROPICPROCESSING.1"), new ResearchPage((CrucibleRecipe)recipes.get("AltCrackedBrick")), new ResearchPage((CrucibleRecipe)recipes.get("AltBonemeal")) }).setConcealed().setSecondary().setParents(new String[] { "ALCHEMICALDUPLICATION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1344 */     new ResearchItem("LIQUIDDEATH", "ALCHEMY", new AspectList().add(Aspect.DEATH, 3).add(Aspect.POISON, 3).add(Aspect.ENTROPY, 1).add(Aspect.WATER, 1), -7, 3, 2, new ItemStack(ConfigItems.itemBucketDeath)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.LIQUIDDEATH.1"), new ResearchPage((CrucibleRecipe)recipes.get("LiquidDeath")) }).setHidden().setAspectTriggers(new Aspect[] { Aspect.DEATH, Aspect.POISON }).setParents(new String[] { "ENTROPICPROCESSING" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1354 */     ThaumcraftApi.addWarpToResearch("LIQUIDDEATH", 3);
/* 1355 */     ThaumcraftApi.addWarpToItem(new ItemStack(ConfigItems.itemBucketDeath), 1);
/*      */     
/* 1357 */     new ResearchItem("BOTTLETAINT", "ALCHEMY", new AspectList().add(Aspect.TAINT, 5).add(Aspect.MAGIC, 3).add(Aspect.ENTROPY, 1).add(Aspect.WATER, 1), -8, 1, 2, new ItemStack(ConfigItems.itemBottleTaint)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BOTTLETAINT.1"), new ResearchPage((CrucibleRecipe)recipes.get("BottleTaint")) }).setHidden().setAspectTriggers(new Aspect[] { Aspect.TAINT }).setParents(new String[] { "ENTROPICPROCESSING" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1367 */     ThaumcraftApi.addWarpToResearch("BOTTLETAINT", 2);
/* 1368 */     ThaumcraftApi.addWarpToItem(new ItemStack(ConfigItems.itemBottleTaint), 1);
/*      */     
/* 1370 */     new ResearchItem("THAUMIUM", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.MAGIC, 3), -1, 3, 1, new ItemStack(ConfigItems.itemResource, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.THAUMIUM.1"), new ResearchPage((CrucibleRecipe)recipes.get("Thaumium")), new ResearchPage((IRecipe)recipes.get("ThaumiumAxe")), new ResearchPage((IRecipe)recipes.get("ThaumiumSword")), new ResearchPage((IRecipe)recipes.get("ThaumiumPick")), new ResearchPage((IRecipe)recipes.get("ThaumiumShovel")), new ResearchPage((IRecipe)recipes.get("ThaumiumHoe")), new ResearchPage((IRecipe)recipes.get("ThaumiumHelm")), new ResearchPage((IRecipe)recipes.get("ThaumiumChest")), new ResearchPage((IRecipe)recipes.get("ThaumiumLegs")), new ResearchPage((IRecipe)recipes.get("ThaumiumBoots")) }).setHidden().setAspectTriggers(new Aspect[] { Aspect.METAL }).setParents(new String[] { "CRUCIBLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1390 */     new ResearchItem("PUREIRON", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 3), -2, 5, 1, new ItemStack(ConfigItems.itemNugget, 1, 16)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PUREIRON.1"), new ResearchPage((CrucibleRecipe)recipes.get("PureIron")) }).setConcealed().setParents(new String[] { "THAUMIUM" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1399 */     new ResearchItem("PUREGOLD", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.GREED, 1), -4, 3, 1, new ItemStack(ConfigItems.itemNugget, 1, 31)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PUREGOLD.1"), new ResearchPage((CrucibleRecipe)recipes.get("PureGold")) }).setConcealed().setSecondary().setParents(new String[] { "PUREIRON" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1409 */     if ((Config.foundCopperOre) && (Config.foundCopperIngot)) {
/* 1410 */       new ResearchItem("PURECOPPER", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.EXCHANGE, 1), -4, 5, 1, new ItemStack(ConfigItems.itemNugget, 1, 17)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PURECOPPER.1"), new ResearchPage((CrucibleRecipe)recipes.get("PureCopper")) }).setConcealed().setSecondary().setParents(new String[] { "PUREIRON" }).registerResearchItem();
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
/* 1422 */     if ((Config.foundTinOre) && (Config.foundTinIngot)) {
/* 1423 */       new ResearchItem("PURETIN", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.CRYSTAL, 1), -4, 7, 1, new ItemStack(ConfigItems.itemNugget, 1, 18)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PURETIN.1"), new ResearchPage((CrucibleRecipe)recipes.get("PureTin")) }).setConcealed().setSecondary().setParents(new String[] { "PUREIRON" }).registerResearchItem();
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
/* 1435 */     if ((Config.foundSilverOre) && (Config.foundSilverIngot)) {
/* 1436 */       new ResearchItem("PURESILVER", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(Aspect.GREED, 1), -3, 8, 1, new ItemStack(ConfigItems.itemNugget, 1, 19)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PURESILVER.1"), new ResearchPage((CrucibleRecipe)recipes.get("PureSilver")) }).setConcealed().setSecondary().setParents(new String[] { "PUREIRON" }).registerResearchItem();
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
/* 1448 */     if ((Config.foundLeadOre) && (Config.foundLeadIngot)) {
/* 1449 */       new ResearchItem("PURELEAD", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 3), -2, 9, 1, new ItemStack(ConfigItems.itemNugget, 1, 20)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PURELEAD.1"), new ResearchPage((CrucibleRecipe)recipes.get("PureLead")) }).setConcealed().setSecondary().setParents(new String[] { "PUREIRON" }).registerResearchItem();
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
/* 1462 */     new ResearchItem("TRANSIRON", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 3), 0, 5, 1, new ItemStack(ConfigItems.itemNugget, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TRANSIRON.1"), new ResearchPage((CrucibleRecipe)recipes.get("TransIron")) }).setConcealed().setParents(new String[] { "THAUMIUM" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1471 */     new ResearchItem("TRANSGOLD", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 3), 2, 3, 1, new ItemStack(Items.field_151074_bl)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TRANSGOLD.1"), new ResearchPage((CrucibleRecipe)recipes.get("TransGold")) }).setConcealed().setSecondary().setParents(new String[] { "TRANSIRON" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1480 */     if (Config.foundCopperIngot) {
/* 1481 */       new ResearchItem("TRANSCOPPER", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 3), 2, 5, 1, new ItemStack(ConfigItems.itemNugget, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TRANSCOPPER.1"), new ResearchPage((CrucibleRecipe)recipes.get("TransCopper")) }).setConcealed().setSecondary().setParents(new String[] { "TRANSIRON" }).registerResearchItem();
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
/* 1492 */     if (Config.foundTinIngot) {
/* 1493 */       new ResearchItem("TRANSTIN", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 2).add(Aspect.CRYSTAL, 1), 2, 7, 1, new ItemStack(ConfigItems.itemNugget, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TRANSTIN.1"), new ResearchPage((CrucibleRecipe)recipes.get("TransTin")) }).setConcealed().setSecondary().setParents(new String[] { "TRANSIRON" }).registerResearchItem();
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
/* 1505 */     if (Config.foundSilverIngot) {
/* 1506 */       new ResearchItem("TRANSSILVER", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 2).add(Aspect.GREED, 1), 1, 8, 1, new ItemStack(ConfigItems.itemNugget, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TRANSSILVER.1"), new ResearchPage((CrucibleRecipe)recipes.get("TransSilver")) }).setConcealed().setSecondary().setParents(new String[] { "TRANSIRON" }).registerResearchItem();
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
/* 1518 */     if (Config.foundLeadIngot) {
/* 1519 */       new ResearchItem("TRANSLEAD", "ALCHEMY", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 2).add(Aspect.ORDER, 1), 0, 9, 1, new ItemStack(ConfigItems.itemNugget, 1, 4)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TRANSLEAD.1"), new ResearchPage((CrucibleRecipe)recipes.get("TransLead")) }).setConcealed().setSecondary().setParents(new String[] { "TRANSIRON" }).registerResearchItem();
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
/* 1531 */     new ResearchItem("TALLOW", "ALCHEMY", new AspectList().add(Aspect.FLESH, 3).add(Aspect.MAGIC, 1), -2, 0, 1, new ItemStack(ConfigItems.itemResource, 1, 4)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TALLOW.1"), new ResearchPage((CrucibleRecipe)recipes.get("Tallow")), new ResearchPage((IRecipe)recipes.get("TallowCandle")) }).setParents(new String[] { "CRUCIBLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1540 */     new ResearchItem("ETHEREALBLOOM", "ALCHEMY", new AspectList().add(Aspect.MAGIC, 1).add(Aspect.PLANT, 6).add(Aspect.HEAL, 3).add(Aspect.TAINT, 6), -2, -3, 2, new ItemStack(ConfigBlocks.blockCustomPlant, 1, 4)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ETHEREALBLOOM.1"), new ResearchPage((CrucibleRecipe)recipes.get("EtherealBloom")), new ResearchPage("tc.research_page.ETHEREALBLOOM.2") }).setHidden().setAspectTriggers(new Aspect[] { Aspect.TAINT }).setConcealed().setParents(new String[] { "CRUCIBLE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1552 */     new ResearchItem("BATHSALTS", "ALCHEMY", new AspectList().add(Aspect.MIND, 3).add(Aspect.AURA, 3).add(Aspect.ORDER, 3).add(Aspect.HEAL, 3), -4, -4, 2, new ItemStack(ConfigItems.itemBathSalts)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.BATHSALTS.1"), new ResearchPage((CrucibleRecipe)recipes.get("BathSalts")) }).setHidden().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1560 */     new ResearchItem("SANESOAP", "ALCHEMY", new AspectList().add(Aspect.MIND, 5).add(Aspect.ORDER, 5).add(Aspect.HEAL, 5).add(Aspect.ELDRITCH, 5), -3, -6, 1, new ItemStack(ConfigItems.itemSanitySoap)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SANESOAP.1"), new ResearchPage((CrucibleRecipe)recipes.get("SaneSoap")) }).setParents(new String[] { "BATHSALTS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1568 */     new ResearchItem("ARCANESPA", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.MECHANISM, 3).add(Aspect.ORDER, 3), -6, -5, 1, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 12)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARCANESPA.1"), new ResearchPage((IArcaneRecipe)recipes.get("ArcaneSpa")) }).setSecondary().setParents(new String[] { "BATHSALTS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1576 */     new ResearchItem("DISTILESSENTIA", "ALCHEMY", new AspectList().add(Aspect.MAGIC, 3).add(Aspect.WATER, 3).add(Aspect.SLIME, 3), 5, -1, 1, new ItemStack(ConfigBlocks.blockMetalDevice, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.DISTILESSENTIA.1"), new ResearchPage((IArcaneRecipe)recipes.get("AlchemyFurnace")), new ResearchPage("tc.research_page.DISTILESSENTIA.2"), new ResearchPage((IArcaneRecipe)recipes.get("Filter")), new ResearchPage((IArcaneRecipe)recipes.get("Alembic")), new ResearchPage((IArcaneRecipe)recipes.get("AlchemicalConstruct")) }).setSiblings(new String[] { "JARLABEL" }).setParents(new String[] { "NITOR", "ALUMENTUM" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1592 */     ArrayList<IRecipe> rc = new ArrayList();
/* 1593 */     for (int a = 0; a < Aspect.aspects.values().size(); a++) {
/* 1594 */       rc.add((IRecipe)recipes.get("JarLabel" + a));
/*      */     }
/*      */     
/* 1597 */     new ResearchItem("JARLABEL", "ALCHEMY", new AspectList(), 4, -3, 0, new ItemStack(ConfigBlocks.blockJar)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.JARLABEL.1"), new ResearchPage((IArcaneRecipe)recipes.get("WardedJar")), new ResearchPage("tc.research_page.JARLABEL.2"), new ResearchPage((IRecipe)recipes.get("JarLabel")), new ResearchPage("tc.research_page.JARLABEL.3"), new ResearchPage((IRecipe[])rc.toArray(new IRecipe[0])), new ResearchPage((IRecipe)recipes.get("JarLabelNull")) }).setParents(new String[] { "DISTILESSENTIA" }).setStub().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1611 */     new ResearchItem("JARVOID", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.ENTROPY, 3).add(Aspect.VOID, 6), 5, -5, 1, new ItemStack(ConfigBlocks.blockJar, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.JARVOID.1"), new ResearchPage((IArcaneRecipe)recipes.get("JarVoid")) }).setParents(new String[] { "JARLABEL" }).setSecondary().setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1621 */     new ResearchItem("TUBES", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.MAGIC, 3).add(Aspect.EXCHANGE, 3), 7, 0, 1, new ItemStack(ConfigBlocks.blockTube, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TUBES.1"), new ResearchPage((IArcaneRecipe)recipes.get("Tube")), new ResearchPage("tc.research_page.TUBES.2"), new ResearchPage((IArcaneRecipe)recipes.get("TubeValve")), new ResearchPage("tc.research_page.TUBES.3"), new ResearchPage((IArcaneRecipe)recipes.get("Resonator")), new ResearchPage("tc.research_page.TUBES.4") }).setParents(new String[] { "DISTILESSENTIA" }).setSecondary().setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1638 */     new ResearchItem("TUBEFILTER", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.MAGIC, 3).add(Aspect.EXCHANGE, 3).add(Aspect.ORDER, 3), 9, 1, 2, new ItemStack(ConfigBlocks.blockTube, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TUBEFILTER.1"), new ResearchPage((IArcaneRecipe)recipes.get("TubeFilter")), new ResearchPage("tc.research_page.TUBEFILTER.2"), new ResearchPage((IArcaneRecipe)recipes.get("TubeRestrict")), new ResearchPage((IArcaneRecipe)recipes.get("TubeOneway")) }).setParents(new String[] { "TUBES" }).setSecondary().setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1653 */     new ResearchItem("ESSENTIACRYSTAL", "ALCHEMY", new AspectList().add(Aspect.WATER, 5).add(Aspect.CRYSTAL, 5).add(Aspect.EXCHANGE, 3).add(Aspect.MAGIC, 5), 8, -2, 1, new ItemStack(ConfigBlocks.blockTube, 1, 7)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ESSENTIACRYSTAL.1"), new ResearchPage((IArcaneRecipe)recipes.get("EssentiaCrystalizer")) }).setConcealed().setParents(new String[] { "TUBES" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1661 */     new ResearchItem("CENTRIFUGE", "ALCHEMY", new AspectList().add(Aspect.ENTROPY, 3).add(Aspect.MAGIC, 3).add(Aspect.EXCHANGE, 3).add(Aspect.CRAFT, 3), 10, 0, 2, new ItemStack(ConfigBlocks.blockTube, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CENTRIFUGE.1"), new ResearchPage((IArcaneRecipe)recipes.get("Centrifuge")), new ResearchPage("tc.research_page.CENTRIFUGE.2"), new ResearchPage("tc.research_page.CENTRIFUGE.3"), new ResearchPage((IArcaneRecipe)recipes.get("TubeBuffer")) }).setParents(new String[] { "TUBEFILTER" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1676 */     new ResearchItem("THAUMATORIUM", "ALCHEMY", new AspectList().add(Aspect.WATER, 3).add(Aspect.MAGIC, 6).add(Aspect.EXCHANGE, 3).add(Aspect.CRAFT, 3), 10, -2, 3, new ResourceLocation("thaumcraft", "textures/blocks/alchemyblock.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.THAUMATORIUM.1"), new ResearchPage((List)recipes.get("Thaumatorium")), new ResearchPage("tc.research_page.THAUMATORIUM.2"), new ResearchPage("tc.research_page.THAUMATORIUM.3"), new ResearchPage((IArcaneRecipe)recipes.get("MnemonicMatrix")) }).setParents(new String[] { "CENTRIFUGE" }).setConcealed().registerResearchItem();
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
/*      */   private static void initGolemancyResearch()
/*      */   {
/* 1691 */     new ResearchItem("HUNGRYCHEST", "GOLEMANCY", new AspectList().add(Aspect.HUNGER, 3).add(Aspect.VOID, 3), -1, 0, 1, new ItemStack(ConfigBlocks.blockChestHungry)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.HUNGRYCHEST.1"), new ResearchPage((IArcaneRecipe)recipes.get("HungryChest")) }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1700 */     new ResearchItem("GOLEMFETTER", "GOLEMANCY", new AspectList().add(Aspect.TRAP, 3).add(Aspect.MECHANISM, 3), 4, 8, 1, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 9)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMFETTER.1"), new ResearchPage((IArcaneRecipe)recipes.get("GolemFetter")) }).setParents(new String[] { "GOLEMSTONE" }).setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1709 */     new ResearchItem("TRAVELTRUNK", "GOLEMANCY", new AspectList().add(Aspect.SOUL, 3).add(Aspect.TRAVEL, 3).add(Aspect.TREE, 3).add(Aspect.VOID, 3), 0, 4, 2, new ItemStack(ConfigItems.itemTrunkSpawner)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TRAVELTRUNK.1"), new ResearchPage((InfusionRecipe)recipes.get("TravelTrunk")), new ResearchPage("tc.research_page.TRAVELTRUNK.2"), new ResearchPage("UPGRADEAIR", "tc.research_page.TRAVELTRUNK.UAI"), new ResearchPage("UPGRADEEARTH", "tc.research_page.TRAVELTRUNK.UEA"), new ResearchPage("UPGRADEFIRE", "tc.research_page.TRAVELTRUNK.UFI"), new ResearchPage("UPGRADEWATER", "tc.research_page.TRAVELTRUNK.UWA"), new ResearchPage("UPGRADEORDER", "tc.research_page.TRAVELTRUNK.UOR"), new ResearchPage("UPGRADEENTROPY", "tc.research_page.TRAVELTRUNK.UEN") }).setConcealed().setParents(new String[] { "INFUSION", "GOLEMWOOD" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1733 */     new ResearchItem("GOLEMSTRAW", "GOLEMANCY", new AspectList().add(Aspect.SOUL, 3).add(Aspect.MOTION, 3).add(Aspect.CROP, 3).add(Aspect.EXCHANGE, 3), 0, 2, 2, new ItemStack(ConfigItems.itemGolemPlacer, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMSTRAW.1"), new ResearchPage("tc.research_page.GOLEMSTRAW.2"), new ResearchPage((CrucibleRecipe)recipes.get("GolemStraw")), new ResearchPage("tc.research_page.GOLEMSTRAW.3") }).setSiblings(new String[] { "COREGATHER", "GOLEMBELL" }).setParents(new String[] { "HUNGRYCHEST" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1746 */     new ResearchItem("GOLEMWOOD", "GOLEMANCY", new AspectList().add(Aspect.SOUL, 4).add(Aspect.MOTION, 4).add(Aspect.TREE, 3).add(Aspect.EXCHANGE, 3), 2, 4, 2, new ItemStack(ConfigItems.itemGolemPlacer, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMWOOD.1"), new ResearchPage((CrucibleRecipe)recipes.get("GolemWood")) }).setSecondary().setParents(new String[] { "GOLEMSTRAW" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1756 */     new ResearchItem("GOLEMTALLOW", "GOLEMANCY", new AspectList().add(Aspect.SOUL, 3).add(Aspect.MOTION, 3).add(Aspect.FLESH, 3).add(Aspect.MAGIC, 3).add(Aspect.EXCHANGE, 3), 4, 6, 2, new ItemStack(ConfigItems.itemGolemPlacer, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMTALLOW.1"), new ResearchPage((IRecipe)recipes.get("BlockTallow")), new ResearchPage((CrucibleRecipe)recipes.get("GolemTallow")) }).setConcealed().setParents(new String[] { "GOLEMCLAY", "TALLOW" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1767 */     new ResearchItem("GOLEMCLAY", "GOLEMANCY", new AspectList().add(Aspect.SOUL, 6).add(Aspect.MOTION, 6).add(Aspect.EARTH, 3).add(Aspect.EXCHANGE, 3), 2, 6, 2, new ItemStack(ConfigItems.itemGolemPlacer, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMCLAY.1"), new ResearchPage((CrucibleRecipe)recipes.get("GolemClay")) }).setSecondary().setConcealed().setParents(new String[] { "GOLEMWOOD" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1777 */     new ResearchItem("GOLEMFLESH", "GOLEMANCY", new AspectList().add(Aspect.SOUL, 7).add(Aspect.MOTION, 7).add(Aspect.FLESH, 6).add(Aspect.EXCHANGE, 3), 4, 4, 2, new ItemStack(ConfigItems.itemGolemPlacer, 1, 4)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMFLESH.1"), new ResearchPage((IRecipe)recipes.get("BlockFlesh")), new ResearchPage((CrucibleRecipe)recipes.get("GolemFlesh")) }).setConcealed().setParents(new String[] { "GOLEMWOOD" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1788 */     ThaumcraftApi.addWarpToResearch("GOLEMFLESH", 3);
/* 1789 */     ThaumcraftApi.addWarpToItem(new ItemStack(ConfigItems.itemGolemPlacer, 1, 4), 1);
/*      */     
/* 1791 */     new ResearchItem("GOLEMSTONE", "GOLEMANCY", new AspectList().add(Aspect.SOUL, 6).add(Aspect.MOTION, 6).add(Aspect.EARTH, 3).add(Aspect.EXCHANGE, 3), 2, 8, 2, new ItemStack(ConfigItems.itemGolemPlacer, 1, 5)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMSTONE.1"), new ResearchPage((CrucibleRecipe)recipes.get("GolemStone")) }).setSecondary().setConcealed().setParents(new String[] { "GOLEMCLAY" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1801 */     new ResearchItem("GOLEMIRON", "GOLEMANCY", new AspectList().add(Aspect.SOUL, 9).add(Aspect.MOTION, 9).add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 3), 0, 10, 2, new ItemStack(ConfigItems.itemGolemPlacer, 1, 6)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMIRON.1"), new ResearchPage((CrucibleRecipe)recipes.get("GolemIron")) }).setSecondary().setConcealed().setParents(new String[] { "GOLEMSTONE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1811 */     new ResearchItem("GOLEMTHAUMIUM", "GOLEMANCY", new AspectList().add(Aspect.SOUL, 10).add(Aspect.MOTION, 10).add(Aspect.METAL, 3).add(Aspect.MAGIC, 3).add(Aspect.EXCHANGE, 3), 2, 10, 2, new ItemStack(ConfigItems.itemGolemPlacer, 1, 7)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMTHAUMIUM.1"), new ResearchPage((IRecipe)recipes.get("BlockThaumium")), new ResearchPage((CrucibleRecipe)recipes.get("GolemThaumium")) }).setConcealed().setParents(new String[] { "GOLEMIRON", "THAUMIUM" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1823 */     new ResearchItem("GOLEMBELL", "GOLEMANCY", new AspectList(), 3, 0, 0, new ItemStack(ConfigItems.itemGolemBell)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.GOLEMBELL.1"), new ResearchPage("tc.research_page.GOLEMBELL.2"), new ResearchPage((IArcaneRecipe)recipes.get("GolemBell")) }).setParents(new String[] { "GOLEMSTRAW" }).setStub().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1832 */     new ResearchItem("COREGATHER", "GOLEMANCY", new AspectList(), -3, 3, 1, new ItemStack(ConfigItems.itemGolemCore, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.COREGATHER.1"), new ResearchPage((IArcaneRecipe)recipes.get("CoreBlank")), new ResearchPage("tc.research_page.COREGATHER.2"), new ResearchPage((CrucibleRecipe)recipes.get("CoreGather")) }).setConcealed().setParents(new String[] { "GOLEMSTRAW" }).setStub().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1843 */     new ResearchItem("COREFILL", "GOLEMANCY", new AspectList().add(Aspect.HUNGER, 3).add(Aspect.EXCHANGE, 3).add(Aspect.VOID, 3), -5, 3, 2, new ItemStack(ConfigItems.itemGolemCore, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.COREFILL.1"), new ResearchPage((CrucibleRecipe)recipes.get("CoreFill")) }).setConcealed().setSecondary().setParents(new String[] { "COREGATHER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1853 */     new ResearchItem("COREEMPTY", "GOLEMANCY", new AspectList().add(Aspect.VOID, 3).add(Aspect.EXCHANGE, 3).add(Aspect.GREED, 3), -5, 1, 2, new ItemStack(ConfigItems.itemGolemCore, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.COREEMPTY.1"), new ResearchPage((CrucibleRecipe)recipes.get("CoreEmpty")) }).setConcealed().setSecondary().setParents(new String[] { "COREGATHER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1863 */     new ResearchItem("CORESORTING", "GOLEMANCY", new AspectList().add(Aspect.VOID, 3).add(Aspect.EXCHANGE, 3).add(Aspect.GREED, 3).add(Aspect.HUNGER, 3), -7, 2, 2, new ItemStack(ConfigItems.itemGolemCore, 1, 10)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CORESORTING.1"), new ResearchPage((InfusionRecipe)recipes.get("CoreSorting")) }).setConcealed().setSecondary().setParents(new String[] { "COREEMPTY", "COREFILL", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1874 */     new ResearchItem("COREUSE", "GOLEMANCY", new AspectList().add(Aspect.TOOL, 3).add(Aspect.EXCHANGE, 3).add(Aspect.MECHANISM, 3).add(Aspect.MAN, 3), -7, 0, 3, new ItemStack(ConfigItems.itemGolemCore, 1, 8)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.COREUSE.1"), new ResearchPage("tc.research_page.COREUSE.2"), new ResearchPage((InfusionRecipe)recipes.get("CoreUse")), new ResearchPage("UPGRADEAIR", "tc.research_page.COREUSE.3") }).setConcealed().setParents(new String[] { "COREEMPTY", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1887 */     new ResearchItem("COREHARVEST", "GOLEMANCY", new AspectList().add(Aspect.HARVEST, 6).add(Aspect.CROP, 3).add(Aspect.TRAVEL, 3), -2, 5, 2, new ItemStack(ConfigItems.itemGolemCore, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.COREHARVEST.1"), new ResearchPage((CrucibleRecipe)recipes.get("CoreHarvest")), new ResearchPage("UPGRADEORDER", "tc.research_page.COREHARVEST.2") }).setConcealed().setParents(new String[] { "COREGATHER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1899 */     new ResearchItem("COREFISHING", "GOLEMANCY", new AspectList().add(Aspect.WATER, 3).add(Aspect.HARVEST, 3).add(Aspect.BEAST, 3).add(Aspect.HUNGER, 3), -2, 7, 2, new ItemStack(ConfigItems.itemGolemCore, 1, 11)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.COREFISHING.1"), new ResearchPage((InfusionRecipe)recipes.get("CoreFishing")), new ResearchPage("UPGRADEAIR", "tc.research_page.COREFISHING.2"), new ResearchPage("UPGRADEFIRE", "tc.research_page.COREFISHING.3"), new ResearchPage("UPGRADEORDER", "tc.research_page.COREFISHING.4"), new ResearchPage("UPGRADEENTROPY", "tc.research_page.COREFISHING.5") }).setConcealed().setSecondary().setParents(new String[] { "COREHARVEST", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1913 */     new ResearchItem("CORELUMBER", "GOLEMANCY", new AspectList().add(Aspect.TREE, 6).add(Aspect.HARVEST, 3).add(Aspect.TOOL, 3).add(Aspect.ENERGY, 3), -1, 7, 2, new ItemStack(ConfigItems.itemGolemCore, 1, 7)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CORELUMBER.1"), new ResearchPage((InfusionRecipe)recipes.get("CoreLumber")) }).setConcealed().setSecondary().setParents(new String[] { "COREHARVEST", "ELEMENTALAXE" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1923 */     new ResearchItem("COREGUARD", "GOLEMANCY", new AspectList().add(Aspect.WEAPON, 3).add(Aspect.TRAP, 3).add(Aspect.SENSES, 3), -4, 5, 2, new ItemStack(ConfigItems.itemGolemCore, 1, 4)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.COREGUARD.1"), new ResearchPage((CrucibleRecipe)recipes.get("CoreGuard")), new ResearchPage("UPGRADEORDER", "tc.research_page.COREGUARD.2") }).setConcealed().setParents(new String[] { "COREGATHER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1933 */     new ResearchItem("COREBUTCHER", "GOLEMANCY", new AspectList().add(Aspect.WEAPON, 3).add(Aspect.BEAST, 3).add(Aspect.SENSES, 3).add(Aspect.HARVEST, 3), -3, 7, 2, new ItemStack(ConfigItems.itemGolemCore, 1, 9)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.COREBUTCHER.1"), new ResearchPage((CrucibleRecipe)recipes.get("CoreButcher")) }).setConcealed().setSecondary().setParents(new String[] { "COREGUARD", "COREHARVEST" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1943 */     ThaumcraftApi.addWarpToResearch("COREBUTCHER", 1);
/*      */     
/* 1945 */     new ResearchItem("CORELIQUID", "GOLEMANCY", new AspectList().add(Aspect.WATER, 3).add(Aspect.EXCHANGE, 3).add(Aspect.TRAVEL, 3), -7, 4, 2, new ItemStack(ConfigItems.itemGolemCore, 1, 5)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CORELIQUID.1"), new ResearchPage((CrucibleRecipe)recipes.get("CoreLiquid")), new ResearchPage("UPGRADEENTROPY", "tc.research_page.CORELIQUID.2") }).setConcealed().setParents(new String[] { "COREFILL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1956 */     new ResearchItem("COREALCHEMY", "GOLEMANCY", new AspectList().add(Aspect.WATER, 3).add(Aspect.TRAVEL, 3).add(Aspect.MAGIC, 3).add(Aspect.ENERGY, 3), -9, 3, 2, new ItemStack(ConfigItems.itemGolemCore, 1, 6)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.COREALCHEMY.1"), new ResearchPage((InfusionRecipe)recipes.get("CoreAlchemy")), new ResearchPage("tc.research_page.COREALCHEMY.2") }).setConcealed().setSecondary().setParents(new String[] { "CORELIQUID", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1968 */     new ResearchItem("UPGRADEAIR", "GOLEMANCY", new AspectList().add(Aspect.AIR, 6).add(Aspect.MOTION, 3), 7, -3, 1, new ItemStack(ConfigItems.itemGolemUpgrade, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.UPGRADEAIR.1"), new ResearchPage((IArcaneRecipe)recipes.get("UpgradeAir")) }).setConcealed().setSecondary().setParents(new String[] { "GOLEMBELL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1977 */     new ResearchItem("UPGRADEEARTH", "GOLEMANCY", new AspectList().add(Aspect.EARTH, 6).add(Aspect.LIFE, 3), 6, -2, 1, new ItemStack(ConfigItems.itemGolemUpgrade, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.UPGRADEEARTH.1"), new ResearchPage((IArcaneRecipe)recipes.get("UpgradeEarth")) }).setConcealed().setSecondary().setParents(new String[] { "GOLEMBELL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1986 */     new ResearchItem("UPGRADEFIRE", "GOLEMANCY", new AspectList().add(Aspect.FIRE, 6).add(Aspect.ENERGY, 3), 5, -1, 1, new ItemStack(ConfigItems.itemGolemUpgrade, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.UPGRADEFIRE.1"), new ResearchPage((IArcaneRecipe)recipes.get("UpgradeFire")) }).setConcealed().setSecondary().setParents(new String[] { "GOLEMBELL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1995 */     new ResearchItem("UPGRADEWATER", "GOLEMANCY", new AspectList().add(Aspect.WATER, 6).add(Aspect.SENSES, 3), 5, 1, 1, new ItemStack(ConfigItems.itemGolemUpgrade, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.UPGRADEWATER.1"), new ResearchPage((IArcaneRecipe)recipes.get("UpgradeWater")) }).setConcealed().setSecondary().setParents(new String[] { "GOLEMBELL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2004 */     new ResearchItem("UPGRADEORDER", "GOLEMANCY", new AspectList().add(Aspect.ORDER, 6).add(Aspect.MIND, 3), 6, 2, 1, new ItemStack(ConfigItems.itemGolemUpgrade, 1, 4)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.UPGRADEORDER.1"), new ResearchPage((IArcaneRecipe)recipes.get("UpgradeOrder")) }).setConcealed().setSecondary().setParents(new String[] { "GOLEMBELL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2013 */     new ResearchItem("UPGRADEENTROPY", "GOLEMANCY", new AspectList().add(Aspect.ENTROPY, 6).add(Aspect.MIND, 3), 7, 3, 1, new ItemStack(ConfigItems.itemGolemUpgrade, 1, 5)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.UPGRADEENTROPY.1"), new ResearchPage((IArcaneRecipe)recipes.get("UpgradeEntropy")) }).setConcealed().setSecondary().setParents(new String[] { "GOLEMBELL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2023 */     ItemStack ag = new ItemStack(ConfigItems.itemGolemPlacer, 1, 32767);
/*      */     
/* 2025 */     ag.func_77983_a("advanced", new NBTTagByte((byte)1));
/* 2026 */     new ResearchItem("ADVANCEDGOLEM", "GOLEMANCY", new AspectList().add(Aspect.LIFE, 3).add(Aspect.ENERGY, 3).add(Aspect.MIND, 6).add(Aspect.SENSES, 3), 8, 0, 2, ag).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ADVANCEDGOLEM.1"), new ResearchPage((InfusionRecipe)recipes.get("AdvancedGolem")) }).setConcealed().setParents(new String[] { "INFUSION", "UPGRADEAIR", "UPGRADEEARTH", "UPGRADEFIRE", "UPGRADEWATER", "UPGRADEORDER", "UPGRADEENTROPY" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2038 */     ThaumcraftApi.addWarpToResearch("ADVANCEDGOLEM", 5);
/*      */     
/*      */ 
/*      */ 
/* 2042 */     new ResearchItem("TINYHAT", "GOLEMANCY", new AspectList().add(Aspect.CLOTH, 2).add(Aspect.LIFE, 1).add(Aspect.GREED, 1), 5, 10, 1, new ItemStack(ConfigItems.itemGolemDecoration, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TINYHAT.1"), new ResearchPage((IArcaneRecipe)recipes.get("TinyHat")) }).setHidden().setSecondary().setItemTriggers(new ItemStack[] { new ItemStack(Blocks.field_150325_L, 1, 32767) }).setAspectTriggers(new Aspect[] { Aspect.CLOTH }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2055 */     new ResearchItem("TINYGLASSES", "GOLEMANCY", new AspectList().add(Aspect.CLOTH, 2).add(Aspect.SENSES, 1).add(Aspect.GREED, 1), 6, 10, 1, new ItemStack(ConfigItems.itemGolemDecoration, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TINYGLASSES.1"), new ResearchPage((IArcaneRecipe)recipes.get("TinyGlasses")) }).setHidden().setSecondary().setItemTriggers(new ItemStack[] { new ItemStack(Blocks.field_150325_L, 1, 32767) }).setAspectTriggers(new Aspect[] { Aspect.CLOTH }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2070 */     new ResearchItem("TINYBOWTIE", "GOLEMANCY", new AspectList().add(Aspect.CLOTH, 2).add(Aspect.TRAVEL, 1).add(Aspect.GREED, 1), 7, 10, 1, new ItemStack(ConfigItems.itemGolemDecoration, 1, 2)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TINYBOWTIE.1"), new ResearchPage((IArcaneRecipe)recipes.get("TinyBowtie")) }).setHidden().setSecondary().setItemTriggers(new ItemStack[] { new ItemStack(Blocks.field_150325_L, 1, 32767) }).setAspectTriggers(new Aspect[] { Aspect.CLOTH }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2085 */     new ResearchItem("TINYFEZ", "GOLEMANCY", new AspectList().add(Aspect.CLOTH, 2).add(Aspect.ENERGY, 1).add(Aspect.GREED, 1), 8, 10, 1, new ItemStack(ConfigItems.itemGolemDecoration, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TINYFEZ.1"), new ResearchPage((IArcaneRecipe)recipes.get("TinyFez")) }).setHidden().setSecondary().setItemTriggers(new ItemStack[] { new ItemStack(Blocks.field_150325_L, 1, 32767) }).setAspectTriggers(new Aspect[] { Aspect.CLOTH }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2099 */     new ResearchItem("TINYDART", "GOLEMANCY", new AspectList().add(Aspect.FLIGHT, 1).add(Aspect.WEAPON, 2).add(Aspect.GREED, 1), 5, 11, 1, new ItemStack(ConfigItems.itemGolemDecoration, 1, 4)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TINYDART.1"), new ResearchPage((IArcaneRecipe)recipes.get("TinyDart")) }).setHidden().setSecondary().setAspectTriggers(new Aspect[] { Aspect.WEAPON }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2109 */     new ResearchItem("TINYVISOR", "GOLEMANCY", new AspectList().add(Aspect.SENSES, 1).add(Aspect.ARMOR, 2).add(Aspect.GREED, 1), 6, 11, 1, new ItemStack(ConfigItems.itemGolemDecoration, 1, 5)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TINYVISOR.1"), new ResearchPage((IArcaneRecipe)recipes.get("TinyVisor")) }).setHidden().setSecondary().setAspectTriggers(new Aspect[] { Aspect.ARMOR }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2119 */     new ResearchItem("TINYARMOR", "GOLEMANCY", new AspectList().add(Aspect.METAL, 1).add(Aspect.ARMOR, 2).add(Aspect.GREED, 1), 7, 11, 1, new ItemStack(ConfigItems.itemGolemDecoration, 1, 6)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TINYARMOR.1"), new ResearchPage((IArcaneRecipe)recipes.get("TinyArmor")) }).setHidden().setSecondary().setAspectTriggers(new Aspect[] { Aspect.ARMOR }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2129 */     new ResearchItem("TINYHAMMER", "GOLEMANCY", new AspectList().add(Aspect.METAL, 1).add(Aspect.WEAPON, 2).add(Aspect.GREED, 1), 8, 11, 1, new ItemStack(ConfigItems.itemGolemDecoration, 1, 7)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.TINYHAMMER.1"), new ResearchPage((IArcaneRecipe)recipes.get("TinyHammer")) }).setHidden().setSecondary().setAspectTriggers(new Aspect[] { Aspect.WEAPON }).registerResearchItem();
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
/*      */   private static void initBasicResearch()
/*      */   {
/* 2142 */     new ResearchItem("ASPECTS", "BASICS", new AspectList(), 0, 0, 0, new ResourceLocation("thaumcraft", "textures/misc/r_aspects.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ASPECTS.1"), new ResearchPage("tc.research_page.ASPECTS.2"), new ResearchPage("tc.research_page.ASPECTS.3") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2150 */     new ResearchItem("PECH", "BASICS", new AspectList(), -4, -4, 0, new ResourceLocation("thaumcraft", "textures/misc/r_pech.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PECH.1"), new ResearchPage("tc.research_page.PECH.2") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2156 */     new ResearchItem("NODES", "BASICS", new AspectList(), -2, 0, 0, new ResourceLocation("thaumcraft", "textures/misc/r_nodes.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODES.1"), new ResearchPage("tc.research_page.NODES.2"), new ResearchPage("tc.research_page.NODES.3") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2163 */     new ResearchItem("WARP", "BASICS", new AspectList(), 0, 2, 0, new ResourceLocation("thaumcraft", "textures/misc/r_warp.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.WARP.1"), new ResearchPage("tc.research_page.WARP.2"), new ResearchPage("tc.research_page.WARP.3") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2170 */     new ResearchItem("RESEARCH", "BASICS", new AspectList(), 2, 0, 0, new ItemStack(ConfigItems.itemInkwell)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RESEARCH.1"), new ResearchPage("tc.research_page.RESEARCH.2"), new ResearchPage((IRecipe)recipes.get("Thaumometer")), new ResearchPage("tc.research_page.RESEARCH.3"), new ResearchPage("tc.research_page.RESEARCH.4"), new ResearchPage((IRecipe)recipes.get("Scribe1")), new ResearchPage((IRecipe)recipes.get("Scribe2")), new ResearchPage((IRecipe)recipes.get("Scribe3")), new ResearchPage("tc.research_page.RESEARCH.5"), new ResearchPage("tc.research_page.RESEARCH.6"), new ResearchPage("tc.research_page.RESEARCH.7"), new ResearchPage("tc.research_page.RESEARCH.8"), new ResearchPage("tc.research_page.RESEARCH.9"), new ResearchPage("tc.research_page.RESEARCH.10"), new ResearchPage("tc.research_page.RESEARCH.11"), new ResearchPage("tc.research_page.RESEARCH.12") }).setAutoUnlock().setStub().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2190 */     new ResearchItem("KNOWFRAG", "BASICS", new AspectList(), 3, -2, 0, new ItemStack(ConfigItems.itemResource, 1, 9)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.KNOWFRAG.1"), new ResearchPage((IRecipe)recipes.get("KnowFrag")) }).setStub().setRound().setAutoUnlock().setParents(new String[] { "RESEARCH" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2197 */     new ResearchItem("THAUMONOMICON", "BASICS", new AspectList(), 1, -2, 0, new ItemStack(ConfigItems.itemThaumonomicon)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.THAUMONOMICON.1"), new ResearchPage((List)recipes.get("Thaumonomicon")) }).setAutoUnlock().setStub().setRound().setParents(new String[] { "RESEARCH" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2204 */     ArrayList<IRecipe> rc = new ArrayList();
/* 2205 */     for (int a = 0; a <= 6; a++) {
/* 2206 */       rc.add((IRecipe)recipes.get("Clusters" + a));
/*      */     }
/*      */     
/* 2209 */     new ResearchItem("ORE", "BASICS", new AspectList(), -2, -2, 0, new ItemStack(ConfigBlocks.blockCustomOre, 1, 32767)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ORE.1"), new ResearchPage("tc.research_page.ORE.2"), new ResearchPage((IRecipe[])rc.toArray(new IRecipe[0])), new ResearchPage("tc.research_page.ORE.3"), new ResearchPage("tc.research_page.ORE.4") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2219 */     new ResearchItem("PLANTS", "BASICS", new AspectList(), -2, -4, 0, new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PLANTS.1"), new ResearchPage((IRecipe)recipes.get("PlankGreatwood")), new ResearchPage("tc.research_page.PLANTS.2"), new ResearchPage((IRecipe)recipes.get("PlankSilverwood")), new ResearchPage("tc.research_page.PLANTS.3"), new ResearchPage("tc.research_page.PLANTS.4"), new ResearchPage("tc.research_page.PLANTS.5"), new ResearchPage("tc.research_page.PLANTS.6") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2234 */     new ResearchItem("ENCHANT", "BASICS", new AspectList(), -4, -2, 0, new ResourceLocation("thaumcraft", "textures/misc/r_enchant.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ENCHANT.1"), new ResearchPage("tc.research_page.ENCHANT.2") }).setStub().setRound().setAutoUnlock().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2243 */     new ResearchItem("NODETAPPER1", "BASICS", new AspectList().add(Aspect.AURA, 3).add(Aspect.MAGIC, 3).add(Aspect.MOTION, 3).add(Aspect.EXCHANGE, 3), -4, 1, 2, new ResourceLocation("thaumcraft", "textures/misc/r_nodetap1.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODETAPPER1.1") }).setParents(new String[] { "NODES" }).setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2250 */     new ResearchItem("NODEPRESERVE", "BASICS", new AspectList().add(Aspect.AURA, 3).add(Aspect.GREED, 3).add(Aspect.SENSES, 3), -6, 2, 2, new ResourceLocation("thaumcraft", "textures/misc/r_nodepreserve.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODEPRESERVE") }).setParents(new String[] { "NODETAPPER1" }).setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2257 */     ItemStack jarIs = new ItemStack(ConfigItems.itemJarNode);
/* 2258 */     ((ItemJarNode)jarIs.func_77973_b()).setAspects(jarIs, new AspectList().add(Aspect.AIR, 40).add(Aspect.FIRE, 40).add(Aspect.WATER, 40).add(Aspect.EARTH, 40));
/*      */     
/*      */ 
/* 2261 */     ((ItemJarNode)jarIs.func_77973_b()).setNodeAttributes(jarIs, thaumcraft.api.nodes.NodeType.NORMAL, null, "");
/*      */     
/* 2263 */     new ResearchItem("NODEJAR", "BASICS", new AspectList().add(Aspect.AURA, 6).add(Aspect.GREED, 3).add(Aspect.EXCHANGE, 3).add(Aspect.MOTION, 3), -7, 4, 3, jarIs).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODEJAR.1"), new ResearchPage((List)recipes.get("NodeJar")), new ResearchPage("tc.research_page.NODEJAR.2") }).setParents(new String[] { "NODEPRESERVE" }).setConcealed().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2272 */     new ResearchItem("NODETAPPER2", "BASICS", new AspectList().add(Aspect.AURA, 6).add(Aspect.MAGIC, 3).add(Aspect.MOTION, 3).add(Aspect.EXCHANGE, 3), -3, 3, 2, new ResourceLocation("thaumcraft", "textures/misc/r_nodetap2.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.NODETAPPER2.1") }).setParents(new String[] { "NODETAPPER1" }).setSpecial().setRound().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2280 */     new ResearchItem("RESEARCHER1", "BASICS", new AspectList().add(Aspect.MIND, 3).add(Aspect.SENSES, 3).add(Aspect.ORDER, 3), 4, 1, 1, new ResourceLocation("thaumcraft", "textures/misc/r_researcher1.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RESEARCHER1.1") }).setRound().setParents(new String[] { "RESEARCH" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2287 */     new ResearchItem("DECONSTRUCTOR", "BASICS", new AspectList().add(Aspect.MIND, 3).add(Aspect.CRAFT, 3).add(Aspect.ENTROPY, 3), 6, 2, 1, new ItemStack(ConfigBlocks.blockTable, 1, 14)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.DECONSTRUCTOR.1"), new ResearchPage((IArcaneRecipe)recipes.get("Deconstructor")), new ResearchPage("tc.research_page.DECONSTRUCTOR.2") }).setRound().setParents(new String[] { "RESEARCHER1" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2298 */     new ResearchItem("RESEARCHER2", "BASICS", new AspectList().add(Aspect.MIND, 6).add(Aspect.ORDER, 3).add(Aspect.SENSES, 3).add(Aspect.MAGIC, 3), 3, 3, 2, new ResourceLocation("thaumcraft", "textures/misc/r_researcher2.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RESEARCHER2.1") }).setRound().setSpecial().setParents(new String[] { "RESEARCHER1" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2306 */     ThaumcraftApi.addWarpToResearch("RESEARCHER2", 1);
/*      */     
/* 2308 */     new ResearchItem("RESEARCHDUPE", "BASICS", new AspectList().add(Aspect.MIND, 6).add(Aspect.EXCHANGE, 3).add(Aspect.SENSES, 3).add(Aspect.GREED, 3).add(Aspect.CRAFT, 3), 4, 5, 3, new ResourceLocation("thaumcraft", "textures/misc/r_resdupe.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.RESEARCHDUPE.1") }).setRound().setParents(new String[] { "RESEARCHER2" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2316 */     new ResearchItem("CRIMSON", "BASICS", new AspectList(), 0, 4, 0, new ItemStack(ConfigItems.itemEldritchObject, 1, 1)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CRIMSON.1") }).setStub().setHidden().setRound().setSpecial().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2321 */     ThaumcraftApi.addWarpToResearch("CRIMSON", 3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void initEldritchResearch()
/*      */   {
/* 2329 */     new ResearchItem("ELDRITCHMINOR", "ELDRITCH", new AspectList(), 1, 0, 0, new ResourceLocation("thaumcraft", "textures/misc/r_eldritchminor.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ELDRITCHMINOR.1"), new ResearchPage((CrucibleRecipe)recipes.get("VoidSeed")) }).setHidden().setRound().setSpecial().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2336 */     new ResearchItem("ELDRITCHMAJOR", "ELDRITCH", new AspectList(), -1, 0, 0, new ResourceLocation("thaumcraft", "textures/misc/r_eldritchmajor.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ELDRITCHMAJOR.1"), new ResearchPage("tc.research_page.ELDRITCHMAJOR.2") }).setStub().setHidden().setRound().setSpecial().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2343 */     new ResearchItem("OCULUS", "ELDRITCH", new AspectList().add(Aspect.MIND, 3).add(Aspect.DARKNESS, 3).add(Aspect.EXCHANGE, 3).add(Aspect.TRAVEL, 6).add(Aspect.ELDRITCH, 6), -2, 2, 1, new ItemStack(ConfigItems.itemEldritchObject, 1, 0)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.OCULUS.1"), new ResearchPage((InfusionRecipe)recipes.get("EldritchEye")), new ResearchPage("tc.research_page.OCULUS.2") }).setRound().setConcealed().setParents(new String[] { "CRIMSON", "ELDRITCHMAJOR" }).setSpecial().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2351 */     ThaumcraftApi.addWarpToResearch("OCULUS", 6);
/*      */     
/* 2353 */     new ResearchItem("ENTEROUTER", "ELDRITCH", new AspectList(), -3, 4, 1, new ResourceLocation("thaumcraft", "textures/misc/r_outer.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ENTEROUTER.1") }).setStub().setHidden().setRound().setParents(new String[] { "OCULUS" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2358 */     new ResearchItem("OUTERREV", "ELDRITCH", new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.MIND, 4), -5, 3, 1, new ResourceLocation("thaumcraft", "textures/misc/r_outerrev.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.OUTERREV.1") }).setItemTriggers(new ItemStack[] { new ItemStack(ConfigBlocks.blockEldritch, 1, 5), new ItemStack(ConfigBlocks.blockEldritch, 1, 10) }).setLost().setSecondary().setSpecial().setParents(new String[] { "ENTEROUTER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2366 */     new ResearchItem("PRIMPEARL", "ELDRITCH", new AspectList().add(Aspect.AIR, 8).add(Aspect.EARTH, 8).add(Aspect.FIRE, 8).add(Aspect.WATER, 8).add(Aspect.ORDER, 8).add(Aspect.ENTROPY, 8), 0, 4, 1, new ItemStack(ConfigItems.itemEldritchObject, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PRIMPEARL.1"), new ResearchPage("tc.research_page.PRIMPEARL.2") }).setItemTriggers(new ItemStack[] { new ItemStack(ConfigItems.itemEldritchObject, 1, 3) }).setLost().setSecondary().setSpecial().setParents(new String[] { "ELDRITCHMINOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2375 */     new ResearchItem("PRIMNODE", "ELDRITCH", new AspectList().add(Aspect.AURA, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), 0, 6, 1, new ResourceLocation("thaumcraft", "textures/misc/r_nodes_2.png")).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PRIMNODE.1") }).setSecondary().setParents(new String[] { "PRIMPEARL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2381 */     ThaumcraftApi.addWarpToResearch("PRIMNODE", 1);
/*      */     
/* 2383 */     new ResearchItem("ADVALCHEMYFURNACE", "ELDRITCH", new AspectList().add(Aspect.AURA, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), -2, 6, 1, new ItemStack(ConfigBlocks.blockMetalDevice, 1, 3)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ADVALCHEMYFURNACE.1"), new ResearchPage((IArcaneRecipe)recipes.get("AdvAlchemyConstruct")), new ResearchPage("tc.research_page.ADVALCHEMYFURNACE.2"), new ResearchPage((List)recipes.get("AdvAlchemyFurnace")) }).setSecondary().setParents(new String[] { "PRIMPEARL", "DISTILESSENTIA", "VISPOWER" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2394 */     new ResearchItem("PRIMALCRUSHER", "ELDRITCH", new AspectList().add(Aspect.MINE, 6).add(Aspect.TOOL, 6).add(Aspect.ENTROPY, 6).add(Aspect.VOID, 6).add(Aspect.WEAPON, 6).add(Aspect.ELDRITCH, 6).add(Aspect.GREED, 6), 2, 5, 2, new ItemStack(ConfigItems.itemPrimalCrusher)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.PRIMALCRUSHER.1"), new ResearchPage((InfusionRecipe)recipes.get("PrimalCrusher")), new ResearchPage("tc.research_page.PRIMALCRUSHER.2") }).setConcealed().setParents(new String[] { "PRIMPEARL" }).setParentsHidden(new String[] { "VOIDMETAL", "ELEMENTALPICK", "ELEMENTALSHOVEL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2406 */     new ResearchItem("SANITYCHECK", "ELDRITCH", new AspectList().add(Aspect.MIND, 5).add(Aspect.ELDRITCH, 3).add(Aspect.SENSES, 5), 2, 2, 1, new ItemStack(ConfigItems.itemSanityChecker)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.SANITYCHECK.1"), new ResearchPage((InfusionRecipe)recipes.get("SanityCheck")) }).setParents(new String[] { "ELDRITCHMINOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2416 */     new ResearchItem("VOIDMETAL", "ELDRITCH", new AspectList().add(Aspect.METAL, 3).add(Aspect.ELDRITCH, 3).add(Aspect.DARKNESS, 3).add(Aspect.VOID, 5), 2, -2, 2, new ItemStack(ConfigItems.itemResource, 1, 16)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.VOIDMETAL.1"), new ResearchPage((CrucibleRecipe)recipes.get("VoidMetal")), new ResearchPage("tc.research_page.VOIDMETAL.2"), new ResearchPage((IRecipe)recipes.get("VoidAxe")), new ResearchPage((IRecipe)recipes.get("VoidSword")), new ResearchPage((IRecipe)recipes.get("VoidPick")), new ResearchPage((IRecipe)recipes.get("VoidShovel")), new ResearchPage((IRecipe)recipes.get("VoidHoe")), new ResearchPage((IRecipe)recipes.get("VoidHelm")), new ResearchPage((IRecipe)recipes.get("VoidChest")), new ResearchPage((IRecipe)recipes.get("VoidLegs")), new ResearchPage((IRecipe)recipes.get("VoidBoots")) }).setParents(new String[] { "THAUMIUM", "ELDRITCHMINOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2436 */     new ResearchItem("ESSENTIARESERVOIR", "ELDRITCH", new AspectList().add(Aspect.WATER, 5).add(Aspect.VOID, 3).add(Aspect.EXCHANGE, 3).add(Aspect.MAGIC, 5).add(Aspect.VOID, 5), 4, -3, 2, new ItemStack(ConfigBlocks.blockEssentiaReservoir)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ESSENTIARESERVOIR.1"), new ResearchPage((InfusionRecipe)recipes.get("EssentiaReservoir")), new ResearchPage("tc.research_page.ESSENTIARESERVOIR.2") }).setParents(new String[] { "VOIDMETAL", "CENTRIFUGE", "INFUSION" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2447 */     new ResearchItem("CAP_void", "ELDRITCH", new AspectList().add(Aspect.VOID, 5).add(Aspect.ELDRITCH, 5).add(Aspect.TOOL, 3).add(Aspect.MAGIC, 3).add(Aspect.AURA, 3), 5, -1, 3, new ItemStack(ConfigItems.itemWandCap, 1, 7)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.CAP_void.1"), new ResearchPage((IArcaneRecipe)recipes.get("WandCapVoidInert")), new ResearchPage((InfusionRecipe)recipes.get("WandCapVoid")) }).setConcealed().setParents(new String[] { "CAP_thaumium", "VOIDMETAL" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2457 */     ThaumcraftApi.addWarpToResearch("CAP_void", 1);
/*      */     
/*      */ 
/* 2460 */     new ResearchItem("ARMORVOIDFORTRESS", "ELDRITCH", new AspectList().add(Aspect.ARMOR, 5).add(Aspect.ELDRITCH, 3).add(Aspect.CLOTH, 3).add(Aspect.DARKNESS, 3).add(Aspect.VOID, 5), 0, -3, 3, new ItemStack(ConfigItems.itemHelmetVoidRobe)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ARMORVOIDFORTRESS.1"), new ResearchPage((InfusionRecipe)recipes.get("VoidRobeHelm")), new ResearchPage((InfusionRecipe)recipes.get("VoidRobeChest")), new ResearchPage((InfusionRecipe)recipes.get("VoidRobeLegs")) }).setParents(new String[] { "VOIDMETAL", "ENCHFABRIC", "ELDRITCHMAJOR" }).setConcealed().setSecondary().registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2476 */     new ResearchItem("FOCUSPRIMAL", "ELDRITCH", new AspectList().add(Aspect.AIR, 6).add(Aspect.WATER, 6).add(Aspect.FIRE, 6).add(Aspect.EARTH, 6).add(Aspect.ORDER, 6).add(Aspect.ENTROPY, 6).add(Aspect.MAGIC, 6), 4, 1, 2, new ItemStack(ConfigItems.itemFocusPrimal)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.FOCUSPRIMAL.1"), new ResearchPage((IArcaneRecipe)recipes.get("FocusPrimal")) }).setConcealed().setParents(new String[] { "ELDRITCHMINOR" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2487 */     ThaumcraftApi.addWarpToResearch("FOCUSPRIMAL", 2);
/* 2488 */     ThaumcraftApi.addWarpToItem(new ItemStack(ConfigItems.itemFocusPrimal), 1);
/*      */     
/*      */ 
/*      */ 
/* 2492 */     new ResearchItem("ROD_primal_staff", "ELDRITCH", new AspectList().add(Aspect.AIR, 9).add(Aspect.EARTH, 9).add(Aspect.FIRE, 9).add(Aspect.WATER, 9).add(Aspect.ORDER, 9).add(Aspect.ENTROPY, 9).add(Aspect.TOOL, 9).add(Aspect.MAGIC, 12), 6, 2, 3, new ItemStack(ConfigItems.itemWandRod, 1, 100)).setPages(new ResearchPage[] { new ResearchPage("tc.research_page.ROD_primal_staff.1"), new ResearchPage((InfusionRecipe)recipes.get("WandRodPrimalStaff")) }).setHidden().setEntityTriggers(new String[] { "Thaumcraft.PrimalOrb" }).setItemTriggers(new ItemStack[] { new ItemStack(ConfigItems.itemFocusPrimal) }).setParents(new String[] { "FOCUSPRIMAL" }).setParentsHidden(new String[] { "ROD_silverwood_staff", "ROD_bone_staff", "ROD_greatwood_staff", "ROD_blaze_staff", "ROD_reed_staff", "ROD_obsidian_staff", "ROD_quartz_staff", "ROD_ice_staff" }).registerResearchItem();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2509 */     ThaumcraftApi.addWarpToResearch("ROD_primal_staff", 3);
/* 2510 */     ThaumcraftApi.addWarpToItem(new ItemStack(ConfigItems.itemWandRod, 1, 100), 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2520 */   public static HashMap<String, Object> recipes = new HashMap();
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/config/ConfigResearch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */