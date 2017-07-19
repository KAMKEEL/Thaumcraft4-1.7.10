/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class BlockMagicalLeavesItem
/*    */   extends ItemBlock
/*    */ {
/*    */   public BlockMagicalLeavesItem(Block par1)
/*    */   {
/* 12 */     super(par1);
/* 13 */     func_77656_e(0);
/* 14 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77647_b(int par1)
/*    */   {
/* 23 */     return par1 | 0x4;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 39 */     int var2 = par1ItemStack.func_77960_j();
/*    */     
/* 41 */     return super.func_77658_a() + "." + BlockMagicalLeaves.leafType[(var2 & 0x1)];
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockMagicalLeavesItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */