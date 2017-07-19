/*    */ package thaumcraft.api.internal;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DummyInternalMethodHandler
/*    */   implements IInternalMethodHandler
/*    */ {
/*    */   public void generateVisEffect(int dim, int x, int y, int z, int x2, int y2, int z2, int color) {}
/*    */   
/*    */   public boolean isResearchComplete(String username, String researchkey)
/*    */   {
/* 18 */     return false;
/*    */   }
/*    */   
/*    */   public boolean hasDiscoveredAspect(String username, Aspect aspect)
/*    */   {
/* 23 */     return false;
/*    */   }
/*    */   
/*    */   public AspectList getDiscoveredAspects(String username)
/*    */   {
/* 28 */     return null;
/*    */   }
/*    */   
/*    */   public ItemStack getStackInRowAndColumn(Object instance, int row, int column)
/*    */   {
/* 33 */     return null;
/*    */   }
/*    */   
/*    */   public AspectList getObjectAspects(ItemStack is)
/*    */   {
/* 38 */     return null;
/*    */   }
/*    */   
/*    */   public AspectList getBonusObjectTags(ItemStack is, AspectList ot)
/*    */   {
/* 43 */     return null;
/*    */   }
/*    */   
/*    */   public AspectList generateTags(Item item, int meta)
/*    */   {
/* 48 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean consumeVisFromWand(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit, boolean crafting)
/*    */   {
/* 54 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean consumeVisFromWandCrafting(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit)
/*    */   {
/* 60 */     return false;
/*    */   }
/*    */   
/*    */   public boolean consumeVisFromInventory(EntityPlayer player, AspectList cost)
/*    */   {
/* 65 */     return false;
/*    */   }
/*    */   
/*    */   public void addWarpToPlayer(EntityPlayer player, int amount, boolean temporary) {}
/*    */   
/*    */   public void addStickyWarpToPlayer(EntityPlayer player, int amount) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/internal/DummyInternalMethodHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */