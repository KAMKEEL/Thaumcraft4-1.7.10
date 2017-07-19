/*    */ package thaumcraft.common.lib.enchantment;
/*    */ 
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnumEnchantmentType;
/*    */ import net.minecraft.item.ItemBook;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.wands.IWandFocus;
/*    */ 
/*    */ public class EnchantmentWandFortune extends Enchantment
/*    */ {
/*    */   public EnchantmentWandFortune(int par1, int par2)
/*    */   {
/* 13 */     super(par1, par2, EnumEnchantmentType.all);
/* 14 */     func_77322_b("wandfortune");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77321_a(int par1)
/*    */   {
/* 23 */     return 15 + (par1 - 1) * 9;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77317_b(int par1)
/*    */   {
/* 32 */     return super.func_77321_a(par1) + 50;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77325_b()
/*    */   {
/* 41 */     return 3;
/*    */   }
/*    */   
/*    */   public boolean func_92089_a(ItemStack stack)
/*    */   {
/* 46 */     if ((((stack.func_77973_b() instanceof IWandFocus)) && (((IWandFocus)stack.func_77973_b()).acceptsEnchant(this.field_77352_x))) || ((stack.func_77973_b() instanceof ItemBook)))
/* 47 */       return true;
/* 48 */     return false;
/*    */   }
/*    */   
/*    */   public boolean canApplyAtEnchantingTable(ItemStack stack)
/*    */   {
/* 53 */     return func_92089_a(stack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/enchantment/EnchantmentWandFortune.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */