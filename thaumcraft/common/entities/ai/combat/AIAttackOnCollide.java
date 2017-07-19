/*     */ package thaumcraft.common.entities.ai.combat;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class AIAttackOnCollide extends EntityAIBase
/*     */ {
/*     */   World worldObj;
/*     */   EntityCreature attacker;
/*     */   int attackTick;
/*     */   double speedTowardsTarget;
/*     */   boolean longMemory;
/*     */   PathEntity entityPathEntity;
/*     */   Class classTarget;
/*     */   private int field_75445_i;
/*     */   private double field_151497_i;
/*     */   private double field_151495_j;
/*     */   private double field_151496_k;
/*     */   private static final String __OBFID = "CL_00001595";
/*     */   private int failedPathFindingPenalty;
/*     */   
/*     */   public AIAttackOnCollide(EntityCreature p_i1635_1_, Class p_i1635_2_, double p_i1635_3_, boolean p_i1635_5_)
/*     */   {
/*  34 */     this(p_i1635_1_, p_i1635_3_, p_i1635_5_);
/*  35 */     this.classTarget = p_i1635_2_;
/*     */   }
/*     */   
/*     */   public AIAttackOnCollide(EntityCreature p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_)
/*     */   {
/*  40 */     this.attacker = p_i1636_1_;
/*  41 */     this.worldObj = p_i1636_1_.field_70170_p;
/*  42 */     this.speedTowardsTarget = p_i1636_2_;
/*  43 */     this.longMemory = p_i1636_4_;
/*  44 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  52 */     EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
/*     */     
/*  54 */     if (entitylivingbase == null)
/*     */     {
/*  56 */       return false;
/*     */     }
/*  58 */     if (!entitylivingbase.func_70089_S())
/*     */     {
/*  60 */       return false;
/*     */     }
/*  62 */     if ((this.classTarget != null) && (!this.classTarget.isAssignableFrom(entitylivingbase.getClass())))
/*     */     {
/*  64 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  68 */     if (--this.field_75445_i <= 0)
/*     */     {
/*  70 */       this.entityPathEntity = this.attacker.func_70661_as().func_75494_a(entitylivingbase);
/*  71 */       this.field_75445_i = (4 + this.attacker.func_70681_au().nextInt(7));
/*  72 */       return this.entityPathEntity != null;
/*     */     }
/*     */     
/*     */ 
/*  76 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  86 */     EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
/*  87 */     return !this.longMemory ? false : !this.attacker.func_70661_as().func_75500_f() ? true : !entitylivingbase.func_70089_S() ? false : entitylivingbase == null ? false : this.attacker.func_110176_b(MathHelper.func_76128_c(entitylivingbase.field_70165_t), MathHelper.func_76128_c(entitylivingbase.field_70163_u), MathHelper.func_76128_c(entitylivingbase.field_70161_v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  95 */     this.attacker.func_70661_as().func_75484_a(this.entityPathEntity, this.speedTowardsTarget);
/*  96 */     this.field_75445_i = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 104 */     this.attacker.func_70661_as().func_75499_g();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/* 112 */     EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
/* 113 */     this.attacker.func_70671_ap().func_75651_a(entitylivingbase, 30.0F, 30.0F);
/* 114 */     double d0 = this.attacker.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b, entitylivingbase.field_70161_v);
/* 115 */     double d1 = this.attacker.field_70130_N * 2.0F * this.attacker.field_70130_N * 2.0F + entitylivingbase.field_70130_N;
/* 116 */     this.field_75445_i -= 1;
/* 117 */     if (this.attackTick > 0) { this.attackTick -= 1;
/*     */     }
/* 119 */     if (((this.longMemory) || (this.attacker.func_70635_at().func_75522_a(entitylivingbase))) && (this.field_75445_i <= 0) && (((this.field_151497_i == 0.0D) && (this.field_151495_j == 0.0D) && (this.field_151496_k == 0.0D)) || (entitylivingbase.func_70092_e(this.field_151497_i, this.field_151495_j, this.field_151496_k) >= 1.0D) || (this.attacker.func_70681_au().nextFloat() < 0.05F)))
/*     */     {
/* 121 */       this.field_151497_i = entitylivingbase.field_70165_t;
/* 122 */       this.field_151495_j = entitylivingbase.field_70121_D.field_72338_b;
/* 123 */       this.field_151496_k = entitylivingbase.field_70161_v;
/* 124 */       this.field_75445_i = (this.failedPathFindingPenalty + 4 + this.attacker.func_70681_au().nextInt(7));
/*     */       
/* 126 */       if (this.attacker.func_70661_as().func_75505_d() != null)
/*     */       {
/* 128 */         PathPoint finalPathPoint = this.attacker.func_70661_as().func_75505_d().func_75870_c();
/* 129 */         if ((finalPathPoint != null) && (entitylivingbase.func_70092_e(finalPathPoint.field_75839_a, finalPathPoint.field_75837_b, finalPathPoint.field_75838_c) < 1.0D))
/*     */         {
/* 131 */           this.failedPathFindingPenalty = 0;
/*     */         }
/*     */         else
/*     */         {
/* 135 */           this.failedPathFindingPenalty += 10;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 140 */         this.failedPathFindingPenalty += 10;
/*     */       }
/*     */       
/* 143 */       if (d0 > 1024.0D)
/*     */       {
/* 145 */         this.field_75445_i += 10;
/*     */       }
/* 147 */       else if (d0 > 256.0D)
/*     */       {
/* 149 */         this.field_75445_i += 5;
/*     */       }
/*     */       
/* 152 */       if (!this.attacker.func_70661_as().func_75497_a(entitylivingbase, this.speedTowardsTarget))
/*     */       {
/* 154 */         this.field_75445_i += 15;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 160 */     if ((d0 <= d1) && (this.attackTick <= 0))
/*     */     {
/* 162 */       this.attackTick = 10;
/*     */       
/* 164 */       if (this.attacker.func_70694_bm() != null)
/*     */       {
/* 166 */         this.attacker.func_71038_i();
/*     */       }
/*     */       
/* 169 */       this.attacker.func_70652_k(entitylivingbase);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AIAttackOnCollide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */