/*    */ package thaumcraft.common.lib.world;
/*    */ 
/*    */ import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
/*    */ import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.village.MerchantRecipe;
/*    */ import net.minecraft.village.MerchantRecipeList;
/*    */ import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
/*    */ import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
/*    */ import thaumcraft.common.config.ConfigEntities;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VillageWizardManager
/*    */   implements VillagerRegistry.IVillageCreationHandler, VillagerRegistry.IVillageTradeHandler
/*    */ {
/*    */   public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
/*    */   {
/* 28 */     if (villager.func_70946_n() == ConfigEntities.entWizardId) {
/* 29 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 20 + random.nextInt(3), 18), new ItemStack(Items.field_151166_bC)));
/* 30 */       recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151166_bC), new ItemStack(ConfigItems.itemResource, 1, 9)));
/* 31 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 4 + random.nextInt(3), 3), new ItemStack(Items.field_151166_bC)));
/* 32 */       recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151166_bC), new ItemStack(ConfigItems.itemResource, 1, 0)));
/* 33 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 4 + random.nextInt(3), 6), new ItemStack(Items.field_151166_bC)));
/* 34 */       recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151166_bC), new ItemStack(ConfigItems.itemResource, 1, 1)));
/* 35 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemNuggetChicken, 24 + random.nextInt(8), 0), new ItemStack(Items.field_151166_bC)));
/* 36 */       recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151122_aG, 4 + random.nextInt(3), 0), new ItemStack(ConfigItems.itemResource, 1, 9)));
/* 37 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemNuggetBeef, 24 + random.nextInt(8), 0), new ItemStack(Items.field_151166_bC)));
/* 38 */       recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151166_bC), new ItemStack(ConfigItems.itemShard, 2 + random.nextInt(2), random.nextInt(6))));
/* 39 */       recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151166_bC), new ItemStack(ConfigItems.itemManaBean, 1 + random.nextInt(2), 0)));
/* 40 */       recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemBathSalts, 1, 0)));
/* 41 */       recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemRingRunic, 1, 0)));
/* 42 */       recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemAmuletVis, 1, 0)));
/* 43 */       recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 3 + random.nextInt(6))));
/*    */     }
/*    */   }
/*    */   
/*    */   public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i)
/*    */   {
/* 49 */     return new StructureVillagePieces.PieceWeight(ComponentWizardTower.class, 15, MathHelper.func_76136_a(random, 0 + i, 1 + i));
/*    */   }
/*    */   
/*    */ 
/*    */   public Class<?> getComponentClass()
/*    */   {
/* 55 */     return ComponentWizardTower.class;
/*    */   }
/*    */   
/*    */ 
/*    */   public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5)
/*    */   {
/* 61 */     return ComponentWizardTower.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/VillageWizardManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */