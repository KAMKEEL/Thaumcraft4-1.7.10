/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import net.minecraft.block.BlockDispenser;
/*    */ import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
/*    */ import net.minecraft.dispenser.BehaviorProjectileDispense;
/*    */ import net.minecraft.dispenser.IBlockSource;
/*    */ import net.minecraft.dispenser.IPosition;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.IProjectile;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.entities.projectile.EntityAlumentum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorDispenseAlumetum
/*    */   extends BehaviorProjectileDispense
/*    */ {
/*    */   public ItemStack func_82487_b(IBlockSource par1IBlockSource, ItemStack par2ItemStack)
/*    */   {
/* 25 */     if (par2ItemStack.func_77960_j() != 0) {
/* 26 */       BehaviorDefaultDispenseItem def = new BehaviorDefaultDispenseItem();
/* 27 */       return def.func_82482_a(par1IBlockSource, par2ItemStack);
/*    */     }
/* 29 */     World var3 = par1IBlockSource.func_82618_k();
/* 30 */     IPosition var4 = BlockDispenser.func_149939_a(par1IBlockSource);
/* 31 */     EnumFacing var5 = BlockDispenser.func_149937_b(par1IBlockSource.func_82620_h());
/* 32 */     IProjectile var6 = func_82499_a(var3, var4);
/* 33 */     var6.func_70186_c(var5.func_82601_c(), var5.func_96559_d() + 0.1F, var5.func_82599_e(), func_82500_b(), func_82498_a());
/* 34 */     var3.func_72838_d((Entity)var6);
/* 35 */     par2ItemStack.func_77979_a(1);
/* 36 */     return par2ItemStack;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected IProjectile func_82499_a(World par1World, IPosition par2IPosition)
/*    */   {
/* 43 */     return new EntityAlumentum(par1World, par2IPosition.func_82615_a(), par2IPosition.func_82617_b(), par2IPosition.func_82616_c());
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_82485_a(IBlockSource par1IBlockSource)
/*    */   {
/* 49 */     par1IBlockSource.func_82618_k().func_72926_e(1009, par1IBlockSource.func_82623_d(), par1IBlockSource.func_82622_e(), par1IBlockSource.func_82621_f(), 0);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/BehaviorDispenseAlumetum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */