/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ 
/*    */ 
/*    */ public class SlotGhost
/*    */   extends Slot
/*    */ {
/* 11 */   int limit = 256;
/*    */   
/*    */   public SlotGhost(IInventory par1iInventory, int par2, int par3, int par4, int par5) {
/* 14 */     super(par1iInventory, par2, par3, par4);
/* 15 */     this.limit = par5;
/*    */   }
/*    */   
/*    */   public SlotGhost(IInventory par1iInventory, int par2, int par3, int par4) {
/* 19 */     super(par1iInventory, par2, par3, par4);
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_75219_a()
/*    */   {
/* 25 */     return this.limit;
/*    */   }
/*    */   
/*    */   public boolean func_82869_a(EntityPlayer par1EntityPlayer)
/*    */   {
/* 30 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/SlotGhost.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */