/*     */ package thaumcraft.common.entities.ai.combat;
/*     */ 
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AIDartAttack
/*     */   extends EntityAIBase
/*     */ {
/*     */   private final EntityGolemBase theGolem;
/*     */   private EntityLivingBase attackTarget;
/*  22 */   private int rangedAttackTime = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   private int maxRangedAttackTime;
/*     */   
/*     */ 
/*     */ 
/*     */   public AIDartAttack(EntityGolemBase par1IRangedAttackMob)
/*     */   {
/*  32 */     this.theGolem = par1IRangedAttackMob;
/*  33 */     this.maxRangedAttackTime = (30 - this.theGolem.getUpgradeAmount(0) * 8);
/*     */     
/*  35 */     this.rangedAttackTime = (this.maxRangedAttackTime / 2);
/*  36 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  44 */     EntityLivingBase var1 = this.theGolem.func_70638_az();
/*     */     
/*  46 */     if (var1 == null)
/*     */     {
/*  48 */       return false;
/*     */     }
/*     */     
/*  51 */     if (!this.theGolem.isValidTarget(var1))
/*     */     {
/*  53 */       this.theGolem.func_70624_b(null);
/*  54 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  58 */     double ra = this.theGolem.func_70092_e(var1.field_70165_t, var1.field_70121_D.field_72338_b, var1.field_70161_v);
/*  59 */     if (ra < 9.0D) return false;
/*  60 */     this.attackTarget = var1;
/*  61 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  70 */     return (func_75250_a()) && (!this.theGolem.func_70661_as().func_75500_f());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*  78 */     this.attackTarget = null;
/*  79 */     this.rangedAttackTime = (this.maxRangedAttackTime / 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  87 */     double var1 = this.theGolem.func_70092_e(this.attackTarget.field_70165_t, this.attackTarget.field_70121_D.field_72338_b, this.attackTarget.field_70161_v);
/*  88 */     boolean var3 = this.theGolem.func_70635_at().func_75522_a(this.attackTarget);
/*     */     
/*     */ 
/*     */ 
/*  92 */     this.theGolem.func_70661_as().func_75497_a(this.attackTarget, this.theGolem.func_70689_ay());
/*     */     
/*  94 */     if (var3)
/*     */     {
/*  96 */       this.theGolem.func_70671_ap().func_75651_a(this.attackTarget, 30.0F, 30.0F);
/*  97 */       this.rangedAttackTime = Math.max(this.rangedAttackTime - 1, 0);
/*     */       
/*  99 */       if (this.rangedAttackTime <= 0)
/*     */       {
/* 101 */         float r = this.theGolem.getRange() * 0.8F;
/* 102 */         r *= r;
/* 103 */         if ((var1 <= r) && (var3))
/*     */         {
/* 105 */           this.theGolem.attackEntityWithRangedAttack(this.attackTarget);
/* 106 */           this.rangedAttackTime = this.maxRangedAttackTime;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AIDartAttack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */