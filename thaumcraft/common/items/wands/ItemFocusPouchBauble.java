/*    */ package thaumcraft.common.items.wands;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemFocusPouchBauble
/*    */   extends ItemFocusPouch
/*    */   implements IBauble
/*    */ {
/*    */   public BaubleType getBaubleType(ItemStack itemstack)
/*    */   {
/* 17 */     return BaubleType.BELT;
/*    */   }
/*    */   
/*    */ 
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}
/*    */   
/*    */ 
/*    */   public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}
/*    */   
/*    */ 
/*    */   public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}
/*    */   
/*    */   public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 31 */     return true;
/*    */   }
/*    */   
/*    */   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
/*    */   {
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/ItemFocusPouchBauble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */