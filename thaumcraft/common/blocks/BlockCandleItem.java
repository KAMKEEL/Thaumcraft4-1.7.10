/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class BlockCandleItem
/*    */   extends ItemBlock
/*    */ {
/*    */   public BlockCandleItem(Block par1)
/*    */   {
/* 13 */     super(par1);
/* 14 */     func_77656_e(0);
/* 15 */     func_77627_a(true);
/* 16 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_77647_b(int par1)
/*    */   {
/* 22 */     return par1;
/*    */   }
/*    */   
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 27 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCandleItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */