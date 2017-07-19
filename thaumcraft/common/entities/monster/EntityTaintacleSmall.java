/*    */ package thaumcraft.common.entities.monster;
/*    */ 
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.entities.ITaintedMob;
/*    */ 
/*    */ public class EntityTaintacleSmall extends EntityTaintacle implements ITaintedMob
/*    */ {
/* 11 */   int lifetime = 200;
/*    */   
/*    */   public EntityTaintacleSmall(World par1World)
/*    */   {
/* 15 */     super(par1World);
/* 16 */     func_70105_a(0.22F, 1.0F);
/* 17 */     this.field_70728_aV = 0;
/*    */   }
/*    */   
/*    */   protected void func_110147_ax()
/*    */   {
/* 22 */     super.func_110147_ax();
/* 23 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
/* 24 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 30 */     super.func_70071_h_();
/*    */     
/* 32 */     if (this.lifetime-- <= 0) {
/* 33 */       func_70665_d(net.minecraft.util.DamageSource.field_76376_m, 10.0F);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_70601_bi()
/*    */   {
/* 40 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   protected Item func_146068_u()
/*    */   {
/* 46 */     return Item.func_150899_d(0);
/*    */   }
/*    */   
/*    */   protected void func_70628_a(boolean flag, int i) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintacleSmall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */