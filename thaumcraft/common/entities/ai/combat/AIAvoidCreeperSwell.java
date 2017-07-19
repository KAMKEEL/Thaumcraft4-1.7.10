/*     */ package thaumcraft.common.entities.ai.combat;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AIAvoidCreeperSwell
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private float farSpeed;
/*     */   private float nearSpeed;
/*     */   private Entity closestLivingEntity;
/*     */   private float distanceFromEntity;
/*     */   private PathEntity entityPathEntity;
/*     */   private PathNavigate entityPathNavigate;
/*     */   Vec3 targetBlock;
/*     */   
/*     */   public AIAvoidCreeperSwell(EntityGolemBase par1EntityCreature)
/*     */   {
/*  33 */     this.theGolem = par1EntityCreature;
/*  34 */     this.distanceFromEntity = 5.0F;
/*     */     
/*  36 */     this.entityPathNavigate = par1EntityCreature.func_70661_as();
/*  37 */     func_75248_a(1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  45 */     if (this.farSpeed == 0.0F) {
/*  46 */       this.farSpeed = (this.theGolem.func_70689_ay() * 1.125F);
/*  47 */       this.nearSpeed = (this.theGolem.func_70689_ay() * 1.25F);
/*     */     }
/*     */     
/*  50 */     List var1 = this.theGolem.field_70170_p.func_72872_a(EntityCreeper.class, this.theGolem.field_70121_D.func_72314_b(this.distanceFromEntity, 3.0D, this.distanceFromEntity));
/*     */     
/*  52 */     if (var1.isEmpty())
/*     */     {
/*  54 */       return false;
/*     */     }
/*     */     
/*  57 */     if (((EntityCreeper)var1.get(0)).func_70832_p() != 1) return false;
/*  58 */     this.closestLivingEntity = ((Entity)var1.get(0));
/*     */     
/*     */ 
/*  61 */     if (!this.theGolem.func_70635_at().func_75522_a(this.closestLivingEntity))
/*     */     {
/*  63 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  67 */     Vec3 var2 = RandomPositionGenerator.func_75461_b(this.theGolem, 16, 7, Vec3.func_72443_a(this.closestLivingEntity.field_70165_t, this.closestLivingEntity.field_70163_u, this.closestLivingEntity.field_70161_v));
/*     */     
/*  69 */     if (var2 == null)
/*     */     {
/*  71 */       return false;
/*     */     }
/*  73 */     if (this.closestLivingEntity.func_70092_e(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c) < this.closestLivingEntity.func_70068_e(this.theGolem))
/*     */     {
/*  75 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  79 */     this.entityPathEntity = this.entityPathNavigate.func_75488_a(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c);
/*  80 */     this.targetBlock = var2;
/*  81 */     return this.entityPathEntity == null ? false : this.entityPathEntity.func_75880_b(var2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  93 */     return !this.entityPathNavigate.func_75500_f();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 101 */     double var1 = this.targetBlock.field_72450_a + 0.5D - this.theGolem.field_70165_t;
/* 102 */     double var3 = this.targetBlock.field_72449_c + 0.5D - this.theGolem.field_70161_v;
/* 103 */     float var5 = MathHelper.func_76133_a(var1 * var1 + var3 * var3);
/* 104 */     this.theGolem.field_70159_w += var1 / var5 * 1.0D * 0.800000011920929D + this.theGolem.field_70159_w * 0.20000000298023224D;
/* 105 */     this.theGolem.field_70179_y += var3 / var5 * 1.0D * 0.800000011920929D + this.theGolem.field_70179_y * 0.20000000298023224D;
/* 106 */     this.theGolem.field_70181_x = 0.3D;
/* 107 */     this.entityPathNavigate.func_75484_a(this.entityPathEntity, this.nearSpeed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 115 */     this.closestLivingEntity = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/* 123 */     if (this.theGolem.func_70068_e(this.closestLivingEntity) < 49.0D)
/*     */     {
/* 125 */       this.theGolem.func_70661_as().func_75489_a(this.nearSpeed);
/*     */     }
/*     */     else
/*     */     {
/* 129 */       this.theGolem.func_70661_as().func_75489_a(this.farSpeed);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AIAvoidCreeperSwell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */