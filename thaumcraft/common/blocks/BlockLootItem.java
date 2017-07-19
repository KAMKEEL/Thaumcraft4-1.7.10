/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockLootItem
/*    */   extends ItemBlock
/*    */ {
/*    */   public BlockLootItem(Block par1)
/*    */   {
/* 19 */     super(par1);
/* 20 */     func_77656_e(0);
/* 21 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_77647_b(int par1)
/*    */   {
/* 27 */     return par1;
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack stack)
/*    */   {
/* 33 */     switch (stack.func_77960_j()) {
/* 34 */     case 1:  return EnumRarity.uncommon;
/* 35 */     case 2:  return EnumRarity.rare;
/*    */     }
/* 37 */     return EnumRarity.common;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 42 */     super.func_77624_a(stack, player, list, par4);
/* 43 */     list.add(func_77613_e(stack).field_77934_f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockLootItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */