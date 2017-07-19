/*    */ package thaumcraft.common.entities;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class EntityPermanentItem
/*    */   extends EntitySpecialItem
/*    */ {
/*    */   public EntityPermanentItem(World par1World)
/*    */   {
/* 12 */     super(par1World);
/*    */   }
/*    */   
/*    */   public EntityPermanentItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack)
/*    */   {
/* 17 */     super(par1World);
/* 18 */     func_70105_a(0.25F, 0.25F);
/* 19 */     this.field_70129_M = (this.field_70131_O / 2.0F);
/* 20 */     func_70107_b(par2, par4, par6);
/* 21 */     func_92058_a(par8ItemStack);
/* 22 */     this.field_70177_z = ((float)(Math.random() * 360.0D));
/* 23 */     this.field_70159_w = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
/* 24 */     this.field_70181_x = 0.20000000298023224D;
/* 25 */     this.field_70179_y = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 32 */     super.func_70071_h_();
/* 33 */     if (this.field_70292_b + 5 >= this.lifespan) this.field_70292_b = 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/EntityPermanentItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */