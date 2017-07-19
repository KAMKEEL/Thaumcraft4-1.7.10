/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class EntityEldritchOrb extends EntityThrowable
/*     */ {
/*     */   public EntityEldritchOrb(World par1World)
/*     */   {
/*  18 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityEldritchOrb(World par1World, EntityLivingBase par2EntityLiving) {
/*  22 */     super(par1World, par2EntityLiving);
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  27 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  42 */     super.func_70071_h_();
/*  43 */     if (this.field_70173_aa > 100) {
/*  44 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70103_a(byte b)
/*     */   {
/*  52 */     if (b == 16) {
/*  53 */       if (this.field_70170_p.field_72995_K) {
/*  54 */         for (int a = 0; a < 30; a++) {
/*  55 */           float fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/*  56 */           float fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/*  57 */           float fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/*  58 */           Thaumcraft.proxy.wispFX3(this.field_70170_p, this.field_70165_t + fx, this.field_70163_u + fy, this.field_70161_v + fz, this.field_70165_t + fx * 8.0F, this.field_70163_u + fy * 8.0F, this.field_70161_v + fz * 8.0F, 0.3F, 5, true, 0.02F);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     }
/*     */     else {
/*  65 */       super.func_70103_a(b);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  75 */     if ((!this.field_70170_p.field_72995_K) && (func_85052_h() != null)) {
/*  76 */       List list = this.field_70170_p.func_72839_b(func_85052_h(), this.field_70121_D.func_72314_b(2.0D, 2.0D, 2.0D));
/*     */       
/*  78 */       for (int i = 0; i < list.size(); i++) {
/*  79 */         Entity entity1 = (Entity)list.get(i);
/*  80 */         if (((entity1 instanceof EntityLivingBase)) && (!((EntityLivingBase)entity1).func_70662_br())) {
/*  81 */           ((EntityLivingBase)entity1).func_70097_a(net.minecraft.util.DamageSource.func_76354_b(this, func_85052_h()), (float)func_85052_h().func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.666F);
/*     */           
/*     */           try
/*     */           {
/*  85 */             ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 160, 0));
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  93 */       this.field_70170_p.func_72956_a(this, "random.fizz", 0.5F, 2.6F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.8F);
/*  94 */       this.field_70173_aa = 100;
/*  95 */       this.field_70170_p.func_72960_a(this, (byte)16);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70053_R()
/*     */   {
/* 102 */     return 0.1F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityEldritchOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */