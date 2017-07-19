/*    */ package thaumcraft.common.entities.ai.combat;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*    */ 
/*    */ public class AILongRangeAttack extends EntityAIArrowAttack
/*    */ {
/*    */   private final EntityLiving wielder;
/* 10 */   double minDistance = 0.0D;
/*    */   
/*    */   public AILongRangeAttack(net.minecraft.entity.IRangedAttackMob par1IRangedAttackMob, double min, double p_i1650_2_, int p_i1650_4_, int p_i1650_5_, float p_i1650_6_)
/*    */   {
/* 14 */     super(par1IRangedAttackMob, p_i1650_2_, p_i1650_4_, p_i1650_5_, p_i1650_6_);
/*    */     
/* 16 */     this.minDistance = min;
/* 17 */     this.wielder = ((EntityLiving)par1IRangedAttackMob);
/*    */   }
/*    */   
/*    */   public boolean func_75250_a()
/*    */   {
/* 22 */     boolean ex = super.func_75250_a();
/* 23 */     if (ex) {
/* 24 */       EntityLivingBase var1 = this.wielder.func_70638_az();
/* 25 */       if (var1 == null) {
/* 26 */         return false;
/*    */       }
/* 28 */       if (var1.field_70128_L) {
/* 29 */         this.wielder.func_70624_b(null);
/* 30 */         return false;
/*    */       }
/* 32 */       double ra = this.wielder.func_70092_e(var1.field_70165_t, var1.field_70121_D.field_72338_b, var1.field_70161_v);
/* 33 */       if (ra < this.minDistance * this.minDistance) { return false;
/*    */       }
/*    */     }
/* 36 */     return ex;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AILongRangeAttack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */