/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSourceIndirect;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ 
/*     */ public class EntityExplosiveOrb
/*     */   extends EntityThrowable
/*     */ {
/*     */   public EntityExplosiveOrb(World par1World)
/*     */   {
/*  21 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityExplosiveOrb(World par1World, EntityLivingBase par2EntityLiving) {
/*  25 */     super(par1World, par2EntityLiving);
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  30 */     return 0.01F;
/*     */   }
/*     */   
/*  33 */   public float strength = 1.0F;
/*  34 */   public boolean onFire = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  41 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/*  43 */       if (mop.field_72308_g != null)
/*     */       {
/*  45 */         mop.field_72308_g.func_70097_a(causeFireballDamage(this, func_85052_h()), this.strength * 1.5F);
/*     */       }
/*     */       
/*  48 */       this.field_70170_p.func_72885_a((Entity)null, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.strength, this.onFire, false);
/*  49 */       func_70106_y();
/*     */     }
/*  51 */     func_70106_y();
/*     */   }
/*     */   
/*     */   public static DamageSource causeFireballDamage(EntityExplosiveOrb p_76362_0_, Entity p_76362_1_)
/*     */   {
/*  56 */     return p_76362_1_ == null ? new EntityDamageSourceIndirect("onFire", p_76362_0_, p_76362_0_).func_76361_j().func_76349_b() : new EntityDamageSourceIndirect("fireball", p_76362_0_, p_76362_1_).func_76361_j().func_76349_b();
/*     */   }
/*     */   
/*     */   public float func_70053_R()
/*     */   {
/*  61 */     return 0.1F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  68 */     super.func_70071_h_();
/*  69 */     if (this.field_70170_p.field_72995_K) {
/*  70 */       Thaumcraft.proxy.drawGenericParticles(this.field_70170_p, this.field_70169_q + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F, this.field_70167_r + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F, this.field_70166_s + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F, 0.0D, 0.0D, 0.0D, 1.0F, 1.0F, 1.0F, 0.8F, false, 151, 9, 1, 7 + this.field_70146_Z.nextInt(5), 0, 2.0F + this.field_70146_Z.nextFloat());
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  77 */     if (this.field_70173_aa > 500) { func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*     */   {
/*  83 */     if (func_85032_ar())
/*     */     {
/*  85 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  89 */     func_70018_K();
/*     */     
/*  91 */     if (p_70097_1_.func_76346_g() != null)
/*     */     {
/*  93 */       Vec3 vec3 = p_70097_1_.func_76346_g().func_70040_Z();
/*     */       
/*  95 */       if (vec3 != null)
/*     */       {
/*  97 */         this.field_70159_w = vec3.field_72450_a;
/*  98 */         this.field_70181_x = vec3.field_72448_b;
/*  99 */         this.field_70179_y = vec3.field_72449_c;
/* 100 */         this.field_70159_w *= 0.9D;
/* 101 */         this.field_70181_x *= 0.9D;
/* 102 */         this.field_70179_y *= 0.9D;
/*     */       }
/* 104 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 108 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityExplosiveOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */