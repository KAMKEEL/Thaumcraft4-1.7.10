/*    */ package thaumcraft.common.lib.enchantment;
/*    */ 
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnumEnchantmentType;
/*    */ import net.minecraft.item.ItemBook;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.IRepairable;
/*    */ 
/*    */ public class EnchantmentRepair extends Enchantment
/*    */ {
/*    */   public EnchantmentRepair(int par1, int par2)
/*    */   {
/* 13 */     super(par1, par2, EnumEnchantmentType.all);
/* 14 */     func_77322_b("repair");
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_77321_a(int par1)
/*    */   {
/* 20 */     return 20 + (par1 - 1) * 10;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77317_b(int par1)
/*    */   {
/* 29 */     return super.func_77321_a(par1) + 50;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77325_b()
/*    */   {
/* 38 */     return 2;
/*    */   }
/*    */   
/*    */   public boolean func_92089_a(ItemStack stack)
/*    */   {
/* 43 */     if ((stack.func_77984_f()) && (((stack.func_77973_b() instanceof IRepairable)) || ((stack.func_77973_b() instanceof ItemBook))))
/* 44 */       return true;
/* 45 */     return false;
/*    */   }
/*    */   
/*    */   public boolean canApplyAtEnchantingTable(ItemStack stack)
/*    */   {
/* 50 */     return func_92089_a(stack);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_77326_a(Enchantment par1Enchantment)
/*    */   {
/* 56 */     return (super.func_77326_a(par1Enchantment)) && (par1Enchantment.field_77352_x != Enchantment.field_77347_r.field_77352_x);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/enchantment/EnchantmentRepair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */