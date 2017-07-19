/*    */ package thaumcraft.common.entities;
/*    */ 
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class EntityItemGrate
/*    */   extends EntityItem
/*    */ {
/*    */   public EntityItemGrate(World par1World)
/*    */   {
/* 14 */     super(par1World);
/*    */   }
/*    */   
/*    */   public EntityItemGrate(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack)
/*    */   {
/* 19 */     super(par1World, par2, par4, par6, par8ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */   protected boolean func_145771_j(double x, double y, double z)
/*    */   {
/* 25 */     int i = MathHelper.func_76128_c(x);
/* 26 */     int j = MathHelper.func_76128_c(y);
/* 27 */     int k = MathHelper.func_76128_c(z);
/*    */     
/* 29 */     if ((this.field_70170_p.func_147439_a(i, j, k) == ConfigBlocks.blockMetalDevice) && ((this.field_70170_p.func_72805_g(i, j, k) == 5) || (this.field_70170_p.func_72805_g(i, j, k) == 6)))
/*    */     {
/* 31 */       return true;
/*    */     }
/* 33 */     return super.func_145771_j(x, y, z);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/EntityItemGrate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */