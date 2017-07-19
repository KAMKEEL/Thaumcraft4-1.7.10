/*    */ package thaumcraft.common.entities.monster;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.DataWatcher;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ public class EntityGiantBrainyZombie extends EntityBrainyZombie
/*    */ {
/*    */   public EntityGiantBrainyZombie(World world)
/*    */   {
/* 17 */     super(world);
/* 18 */     this.field_70728_aV = 15;
/* 19 */     this.field_70129_M *= (1.2F + getAnger());
/* 20 */     func_70105_a(this.field_70130_N * (1.2F + getAnger()), this.field_70131_O * (1.2F + getAnger()));
/*    */     
/* 22 */     this.field_70714_bg.func_75776_a(2, new net.minecraft.entity.ai.EntityAILeapAtTarget(this, 0.4F));
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_70636_d()
/*    */   {
/* 28 */     super.func_70636_d();
/* 29 */     if (getAnger() > 1.0F) {
/* 30 */       setAnger(getAnger() - 0.002F);
/* 31 */       func_70105_a(0.6F * (1.2F + getAnger()), 1.8F * (1.2F + getAnger()));
/*    */     }
/*    */     
/* 34 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0F + (getAnger() - 1.0F) * 5.0F);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_70088_a()
/*    */   {
/* 40 */     super.func_70088_a();
/* 41 */     this.field_70180_af.func_75682_a(20, new Float(1.0F));
/*    */   }
/*    */   
/*    */   public float getAnger()
/*    */   {
/* 46 */     return this.field_70180_af.func_111145_d(20);
/*    */   }
/*    */   
/*    */   public void setAnger(float par1)
/*    */   {
/* 51 */     this.field_70180_af.func_75692_b(20, Float.valueOf(par1));
/*    */   }
/*    */   
/*    */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*    */   {
/* 56 */     setAnger(Math.min(2.0F, getAnger() + 0.1F));
/* 57 */     return super.func_70097_a(par1DamageSource, par2);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_110147_ax()
/*    */   {
/* 65 */     super.func_110147_ax();
/* 66 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(60.0D);
/* 67 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_70628_a(boolean flag, int i)
/*    */   {
/* 73 */     for (int a = 0; a < 6; a++) if (this.field_70170_p.field_73012_v.nextBoolean()) func_145779_a(Items.field_151078_bh, 2);
/* 74 */     for (int a = 0; a < 6; a++) if (this.field_70170_p.field_73012_v.nextBoolean()) func_145779_a(Items.field_151078_bh, 2);
/* 75 */     if (this.field_70170_p.field_73012_v.nextInt(10) - i <= 4) { func_70099_a(new ItemStack(ConfigItems.itemZombieBrain), 2.0F);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void func_70600_l(int par1)
/*    */   {
/* 81 */     switch (this.field_70146_Z.nextInt(4))
/*    */     {
/*    */     case 0: 
/* 84 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 2), 2.0F);
/* 85 */       break;
/*    */     case 1: 
/* 87 */       func_145779_a(Items.field_151172_bF, 1);
/* 88 */       break;
/*    */     case 2: 
/* 90 */       func_145779_a(Items.field_151174_bG, 1);
/* 91 */       break;
/*    */     case 3: 
/* 93 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 6), 2.0F);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityGiantBrainyZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */