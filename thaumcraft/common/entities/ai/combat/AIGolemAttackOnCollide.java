/*     */ package thaumcraft.common.entities.ai.combat;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AIGolemAttackOnCollide
/*     */   extends EntityAIBase
/*     */ {
/*     */   World worldObj;
/*     */   EntityGolemBase theGolem;
/*     */   EntityLivingBase entityTarget;
/*     */   int attackTick;
/*     */   PathEntity entityPathEntity;
/*     */   private int counter;
/*     */   
/*     */   public AIGolemAttackOnCollide(EntityGolemBase par1EntityLiving)
/*     */   {
/*  28 */     this.attackTick = 0;
/*  29 */     this.theGolem = par1EntityLiving;
/*  30 */     this.worldObj = par1EntityLiving.field_70170_p;
/*  31 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  39 */     EntityLivingBase var1 = this.theGolem.func_70638_az();
/*     */     
/*  41 */     if (var1 == null)
/*     */     {
/*  43 */       return false;
/*     */     }
/*  45 */     if (!this.theGolem.isValidTarget(var1))
/*     */     {
/*  47 */       this.theGolem.func_70624_b(null);
/*  48 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  52 */     this.entityTarget = var1;
/*  53 */     this.entityPathEntity = this.theGolem.func_70661_as().func_75494_a(this.entityTarget);
/*  54 */     return this.entityPathEntity != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  63 */     return (func_75250_a()) && (!this.theGolem.func_70661_as().func_75500_f());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  71 */     this.theGolem.func_70661_as().func_75484_a(this.entityPathEntity, this.theGolem.func_70689_ay());
/*  72 */     this.counter = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*  80 */     this.entityTarget = null;
/*  81 */     this.theGolem.func_70661_as().func_75499_g();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  89 */     this.theGolem.func_70671_ap().func_75651_a(this.entityTarget, 30.0F, 30.0F);
/*     */     
/*  91 */     if ((this.theGolem.func_70635_at().func_75522_a(this.entityTarget)) && (--this.counter <= 0))
/*     */     {
/*  93 */       this.counter = (4 + this.theGolem.func_70681_au().nextInt(7));
/*  94 */       this.theGolem.func_70661_as().func_75497_a(this.entityTarget, this.theGolem.func_70689_ay());
/*     */     }
/*     */     
/*  97 */     this.attackTick = Math.max(this.attackTick - 1, 0);
/*  98 */     double attackRange = this.entityTarget.field_70130_N * 2.0F * this.entityTarget.field_70130_N * 2.0F + 1.0D;
/*     */     
/* 100 */     if (this.theGolem.func_70092_e(this.entityTarget.field_70165_t, this.entityTarget.field_70121_D.field_72338_b, this.entityTarget.field_70161_v) <= attackRange)
/*     */     {
/* 102 */       if (this.attackTick <= 0)
/*     */       {
/* 104 */         this.attackTick = this.theGolem.getAttackSpeed();
/*     */         
/* 106 */         if (this.theGolem.func_70694_bm() != null)
/*     */         {
/* 108 */           this.theGolem.func_71038_i();
/*     */         } else {
/* 110 */           this.theGolem.startActionTimer();
/*     */         }
/*     */         
/* 113 */         this.theGolem.func_70652_k(this.entityTarget);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AIGolemAttackOnCollide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */