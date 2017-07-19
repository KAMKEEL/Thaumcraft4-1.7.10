/*    */ package thaumcraft.common.lib.enchantment;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnumEnchantmentType;
/*    */ import net.minecraft.item.ItemBook;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.wands.IWandFocus;
/*    */ import thaumcraft.api.wands.ItemFocusBasic;
/*    */ 
/*    */ public class EnchantmentFrugal
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentFrugal(int par1, int par2)
/*    */   {
/* 16 */     super(par1, par2, EnumEnchantmentType.all);
/* 17 */     func_77322_b("frugal");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77321_a(int par1)
/*    */   {
/* 26 */     return 5 + 11 * (par1 - 1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77317_b(int par1)
/*    */   {
/* 35 */     return super.func_77321_a(par1) + 50;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77325_b()
/*    */   {
/* 44 */     return 3;
/*    */   }
/*    */   
/*    */   public boolean func_92089_a(ItemStack stack)
/*    */   {
/* 49 */     if ((((stack.func_77973_b() instanceof IWandFocus)) && (((IWandFocus)stack.func_77973_b()).acceptsEnchant(this.field_77352_x))) || ((stack.func_77973_b() instanceof ItemBook)))
/* 50 */       return true;
/* 51 */     return false;
/*    */   }
/*    */   
/*    */   public boolean canApplyAtEnchantingTable(ItemStack stack)
/*    */   {
/* 56 */     return func_92089_a(stack);
/*    */   }
/*    */   
/*    */   public static boolean doDamage(ItemStack par0ItemStack, int par1, Random par2Random)
/*    */   {
/* 61 */     float chance = 1.0F - par1 / 5.0F;
/* 62 */     return ((par0ItemStack.func_77973_b() instanceof ItemFocusBasic)) && (par2Random.nextFloat() < chance);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_77326_a(Enchantment par1Enchantment)
/*    */   {
/* 68 */     return super.func_77326_a(par1Enchantment);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/enchantment/EnchantmentFrugal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */