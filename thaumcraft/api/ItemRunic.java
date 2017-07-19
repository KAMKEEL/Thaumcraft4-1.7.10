/*    */ package thaumcraft.api;
/*    */ 
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ItemRunic
/*    */   extends Item implements IRunicArmor
/*    */ {
/*    */   int charge;
/*    */   
/*    */   public ItemRunic(int charge)
/*    */   {
/* 13 */     this.charge = charge;
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 18 */     return this.charge;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/ItemRunic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */