/*    */ package thaumcraft.common.entities;
/*    */ 
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySpecialItem
/*    */   extends EntityItem
/*    */ {
/*    */   public EntitySpecialItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack)
/*    */   {
/* 15 */     super(par1World);
/* 16 */     func_70105_a(0.25F, 0.25F);
/* 17 */     this.field_70129_M = (this.field_70131_O / 2.0F);
/* 18 */     func_70107_b(par2, par4, par6);
/* 19 */     func_92058_a(par8ItemStack);
/* 20 */     this.field_70177_z = ((float)(Math.random() * 360.0D));
/* 21 */     this.field_70159_w = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
/* 22 */     this.field_70181_x = 0.20000000298023224D;
/* 23 */     this.field_70179_y = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
/*    */   }
/*    */   
/*    */   public EntitySpecialItem(World par1World) {
/* 27 */     super(par1World);
/* 28 */     func_70105_a(0.25F, 0.25F);
/* 29 */     this.field_70129_M = (this.field_70131_O / 2.0F);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 35 */     if (this.field_70181_x > 0.0D) this.field_70181_x *= 0.8999999761581421D;
/* 36 */     this.field_70181_x += 0.03999999910593033D;
/* 37 */     super.func_70071_h_();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*    */   {
/* 45 */     if (p_70097_1_.func_94541_c())
/*    */     {
/* 47 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 51 */     return super.func_70097_a(p_70097_1_, p_70097_2_);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/EntitySpecialItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */