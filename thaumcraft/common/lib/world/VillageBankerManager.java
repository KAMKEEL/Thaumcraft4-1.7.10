/*    */ package thaumcraft.common.lib.world;
/*    */ 
/*    */ import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
/*    */ import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
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
/*    */ public class VillageBankerManager
/*    */   implements VillagerRegistry.IVillageCreationHandler, VillagerRegistry.IVillageTradeHandler
/*    */ {
/*    */   public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
/*    */   {
/* 28 */     if (villager.func_70946_n() == ConfigEntities.entBankerId) {
/* 29 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 20 + random.nextInt(3), 18), new ItemStack(Items.field_151166_bC)));
/* 30 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 2 + random.nextInt(2), 18), Items.field_151032_g));
/* 31 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 6 + random.nextInt(3), 18), Item.func_150898_a(Blocks.field_150325_L)));
/* 32 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 3 + random.nextInt(2), 18), Items.field_151121_aF));
/* 33 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 7 + random.nextInt(3), 18), Items.field_151122_aG));
/* 34 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 16 + random.nextInt(5), 18), Items.field_151062_by));
/* 35 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 9 + random.nextInt(4), 18), Item.func_150898_a(Blocks.field_150426_aN)));
/* 36 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 2 + random.nextInt(2), 18), Items.field_151044_h));
/* 37 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 22 + random.nextInt(3), 18), Items.field_151045_i));
/* 38 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 6 + random.nextInt(3), 18), Items.field_151042_j));
/* 39 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 10 + random.nextInt(3), 18), new ItemStack(ConfigItems.itemResource, 1, 2)));
/* 40 */       recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 25 + random.nextInt(8), 18), Items.field_151141_av));
/*    */     }
/*    */   }
/*    */   
/*    */   public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i)
/*    */   {
/* 46 */     return new StructureVillagePieces.PieceWeight(ComponentBankerHome.class, 25, MathHelper.func_76136_a(random, 0 + i, 1 + i));
/*    */   }
/*    */   
/*    */ 
/*    */   public Class<?> getComponentClass()
/*    */   {
/* 52 */     return ComponentBankerHome.class;
/*    */   }
/*    */   
/*    */ 
/*    */   public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5)
/*    */   {
/* 58 */     return ComponentBankerHome.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/VillageBankerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */