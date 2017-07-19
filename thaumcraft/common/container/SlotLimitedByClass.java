/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class SlotLimitedByClass
/*    */   extends Slot
/*    */ {
/* 10 */   Class clazz = Object.class;
/* 11 */   int limit = 64;
/*    */   
/*    */   public SlotLimitedByClass(Class clazz, IInventory par2IInventory, int par3, int par4, int par5)
/*    */   {
/* 15 */     super(par2IInventory, par3, par4, par5);
/* 16 */     this.clazz = clazz;
/*    */   }
/*    */   
/*    */   public SlotLimitedByClass(Class clazz, int limit, IInventory par2IInventory, int par3, int par4, int par5)
/*    */   {
/* 21 */     super(par2IInventory, par3, par4, par5);
/* 22 */     this.clazz = clazz;
/* 23 */     this.limit = limit;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75214_a(ItemStack par1ItemStack)
/*    */   {
/* 31 */     return (par1ItemStack != null) && (par1ItemStack.func_77973_b() != null) && (this.clazz.isAssignableFrom(par1ItemStack.func_77973_b().getClass()));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int func_75219_a()
/*    */   {
/* 38 */     return this.limit;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/SlotLimitedByClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */