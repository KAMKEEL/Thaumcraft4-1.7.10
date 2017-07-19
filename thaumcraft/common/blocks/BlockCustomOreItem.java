/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class BlockCustomOreItem
/*    */   extends ItemBlock
/*    */ {
/* 10 */   public static final int[] colors = { 16777215, 16777086, 16727041, 37119, 40960, 15650047, 5592439 };
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
/*    */   public BlockCustomOreItem(Block par1)
/*    */   {
/* 24 */     super(par1);
/* 25 */     func_77656_e(0);
/* 26 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int func_77647_b(int par1)
/*    */   {
/* 33 */     return par1;
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 39 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCustomOreItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */