/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*    */ 
/*    */ 
/*    */ public class SlotGhostFluid
/*    */   extends SlotGhost
/*    */ {
/*    */   public SlotGhostFluid(IInventory par1iInventory, int par2, int par3, int par4)
/*    */   {
/* 14 */     super(par1iInventory, par2, par3, par4);
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_75219_a()
/*    */   {
/* 20 */     return 1;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75214_a(ItemStack par1ItemStack)
/*    */   {
/* 28 */     return FluidContainerRegistry.isContainer(par1ItemStack);
/*    */   }
/*    */   
/*    */   public boolean func_82869_a(EntityPlayer par1EntityPlayer)
/*    */   {
/* 33 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/SlotGhostFluid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */