/*    */ package thaumcraft.common.entities.ai.combat;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.entity.ai.EntitySenses;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import thaumcraft.common.entities.monster.EntityTaintCreeper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AICreeperSwell
/*    */   extends EntityAIBase
/*    */ {
/*    */   EntityTaintCreeper swellingCreeper;
/*    */   EntityLivingBase creeperAttackTarget;
/*    */   
/*    */   public AICreeperSwell(EntityTaintCreeper par1EntityCreeper)
/*    */   {
/* 20 */     this.swellingCreeper = par1EntityCreeper;
/* 21 */     func_75248_a(1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 29 */     EntityLivingBase entitylivingbase = this.swellingCreeper.func_70638_az();
/* 30 */     return (this.swellingCreeper.getCreeperState() > 0) || ((entitylivingbase != null) && (this.swellingCreeper.func_70068_e(entitylivingbase) < 9.0D));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 38 */     this.swellingCreeper.func_70661_as().func_75499_g();
/* 39 */     this.creeperAttackTarget = this.swellingCreeper.func_70638_az();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75251_c()
/*    */   {
/* 47 */     this.creeperAttackTarget = null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75246_d()
/*    */   {
/* 55 */     if (this.creeperAttackTarget == null)
/*    */     {
/* 57 */       this.swellingCreeper.setCreeperState(-1);
/*    */     }
/* 59 */     else if (this.swellingCreeper.func_70068_e(this.creeperAttackTarget) > 49.0D)
/*    */     {
/* 61 */       this.swellingCreeper.setCreeperState(-1);
/*    */     }
/* 63 */     else if (!this.swellingCreeper.func_70635_at().func_75522_a(this.creeperAttackTarget))
/*    */     {
/* 65 */       this.swellingCreeper.setCreeperState(-1);
/*    */     }
/*    */     else
/*    */     {
/* 69 */       this.swellingCreeper.setCreeperState(1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AICreeperSwell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */