/*    */ package thaumcraft.common.entities.monster;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*    */ import net.minecraft.entity.ai.EntityAITasks;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityBrainyZombie extends EntityZombie
/*    */ {
/*    */   protected void func_110147_ax()
/*    */   {
/* 19 */     super.func_110147_ax();
/* 20 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(25.0D);
/* 21 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/* 22 */     func_110148_a(field_110186_bp).func_111128_a(0.0D);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public EntityBrainyZombie(World world)
/*    */   {
/* 30 */     super(world);
/* 31 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*    */   {
/* 39 */     return super.func_70097_a(par1DamageSource, par2);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_70074_a(EntityLivingBase par1EntityLivingBase)
/*    */   {
/* 45 */     super.func_70074_a(par1EntityLivingBase);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_70658_aO()
/*    */   {
/* 55 */     int var1 = super.func_70658_aO() + 3;
/*    */     
/* 57 */     if (var1 > 20)
/*    */     {
/* 59 */       var1 = 20;
/*    */     }
/*    */     
/* 62 */     return var1;
/*    */   }
/*    */   
/*    */   protected void func_70628_a(boolean flag, int i)
/*    */   {
/* 67 */     for (int a = 0; a < 3; a++) if (this.field_70170_p.field_73012_v.nextBoolean()) func_145779_a(Items.field_151078_bh, 1);
/* 68 */     if (this.field_70170_p.field_73012_v.nextInt(10) - i <= 4) func_70099_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemZombieBrain), 1.5F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityBrainyZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */