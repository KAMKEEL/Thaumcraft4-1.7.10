/*    */ package thaumcraft.common.lib;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.items.wands.ItemWandCasting;
/*    */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*    */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ import thaumcraft.common.tiles.TileMagicWorkbench;
/*    */ 
/*    */ public class InternalMethodHandler implements thaumcraft.api.internal.IInternalMethodHandler
/*    */ {
/*    */   public void generateVisEffect(int dim, int x, int y, int z, int x2, int y2, int z2, int color)
/*    */   {
/* 21 */     Utils.generateVisEffect(dim, x, y, z, x2, y2, z2, color);
/*    */   }
/*    */   
/*    */   public boolean isResearchComplete(String username, String researchkey)
/*    */   {
/* 26 */     return ResearchManager.isResearchComplete(username, researchkey);
/*    */   }
/*    */   
/*    */   public boolean hasDiscoveredAspect(String username, Aspect aspect)
/*    */   {
/* 31 */     return Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(username, aspect);
/*    */   }
/*    */   
/*    */   public AspectList getDiscoveredAspects(String username)
/*    */   {
/* 36 */     return Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(username);
/*    */   }
/*    */   
/*    */   public ItemStack getStackInRowAndColumn(Object instance, int row, int column)
/*    */   {
/* 41 */     return ((TileMagicWorkbench)instance).getStackInRowAndColumn(row, column);
/*    */   }
/*    */   
/*    */   public AspectList getObjectAspects(ItemStack is)
/*    */   {
/* 46 */     return ThaumcraftCraftingManager.getObjectTags(is);
/*    */   }
/*    */   
/*    */   public AspectList getBonusObjectTags(ItemStack is, AspectList ot)
/*    */   {
/* 51 */     return ThaumcraftCraftingManager.getBonusTags(is, ot);
/*    */   }
/*    */   
/*    */   public AspectList generateTags(Item item, int meta)
/*    */   {
/* 56 */     return ThaumcraftCraftingManager.generateTags(item, meta);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean consumeVisFromWand(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit, boolean crafting)
/*    */   {
/* 62 */     if ((wand.func_77973_b() instanceof ItemWandCasting)) {
/* 63 */       return ((ItemWandCasting)wand.func_77973_b()).consumeAllVis(wand, player, cost, doit, crafting);
/*    */     }
/* 65 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean consumeVisFromWandCrafting(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit)
/*    */   {
/* 71 */     if ((wand.func_77973_b() instanceof ItemWandCasting)) {
/* 72 */       return ((ItemWandCasting)wand.func_77973_b()).consumeAllVisCrafting(wand, player, cost, doit);
/*    */     }
/* 74 */     return false;
/*    */   }
/*    */   
/*    */   public boolean consumeVisFromInventory(EntityPlayer player, AspectList cost)
/*    */   {
/* 79 */     return thaumcraft.common.items.wands.WandManager.consumeVisFromInventory(player, cost);
/*    */   }
/*    */   
/*    */   public void addWarpToPlayer(EntityPlayer player, int amount, boolean temporary)
/*    */   {
/* 84 */     Thaumcraft.addWarpToPlayer(player, amount, temporary);
/*    */   }
/*    */   
/*    */   public void addStickyWarpToPlayer(EntityPlayer player, int amount)
/*    */   {
/* 89 */     Thaumcraft.addStickyWarpToPlayer(player, amount);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/InternalMethodHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */