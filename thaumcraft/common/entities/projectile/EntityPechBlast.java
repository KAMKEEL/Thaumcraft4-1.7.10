/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class EntityPechBlast extends EntityThrowable
/*     */ {
/*     */   public EntityPechBlast(World par1World)
/*     */   {
/*  18 */     super(par1World);
/*     */   }
/*     */   
/*  21 */   int strength = 0;
/*  22 */   int duration = 0;
/*  23 */   boolean nightshade = false;
/*     */   
/*     */   public EntityPechBlast(World par1World, EntityLivingBase par2EntityLiving, int strength, int duration, boolean nightshade)
/*     */   {
/*  27 */     super(par1World, par2EntityLiving);
/*  28 */     this.strength = strength;
/*  29 */     this.nightshade = nightshade;
/*  30 */     this.duration = duration;
/*     */   }
/*     */   
/*     */   public EntityPechBlast(World par1World, double par2, double par4, double par6, int strength, int duration, boolean nightshade)
/*     */   {
/*  35 */     super(par1World, par2, par4, par6);
/*  36 */     this.strength = strength;
/*  37 */     this.nightshade = nightshade;
/*  38 */     this.duration = duration;
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  43 */     return 0.025F;
/*     */   }
/*     */   
/*     */   protected float func_70182_d()
/*     */   {
/*  48 */     return 1.5F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  54 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/*  56 */       for (int a = 0; a < 3; a++) {
/*  57 */         Thaumcraft.proxy.wispFX2(this.field_70170_p, this.field_70165_t + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70163_u + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70161_v + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, 0.3F, 3, true, true, 0.02F);
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
/*  69 */         double x2 = (this.field_70165_t + this.field_70169_q) / 2.0D + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F;
/*     */         
/*     */ 
/*     */ 
/*  73 */         double y2 = (this.field_70163_u + this.field_70167_r) / 2.0D + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F;
/*     */         
/*     */ 
/*     */ 
/*  77 */         double z2 = (this.field_70161_v + this.field_70166_s) / 2.0D + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F;
/*     */         
/*     */ 
/*     */ 
/*  81 */         Thaumcraft.proxy.wispFX2(this.field_70170_p, x2, y2, z2, 0.3F, 2, true, true, 0.02F);
/*     */         
/*  83 */         Thaumcraft.proxy.sparkle((float)this.field_70165_t + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, (float)this.field_70163_u + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, (float)this.field_70161_v + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, 5);
/*     */       }
/*     */     }
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
/*  97 */     super.func_70071_h_();
/*     */     
/*  99 */     if (this.field_70173_aa > 500) { func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(net.minecraft.util.MovingObjectPosition par1MovingObjectPosition)
/*     */   {
/* 107 */     if (this.field_70170_p.field_72995_K) {
/* 108 */       for (int a = 0; a < 9; a++) {
/* 109 */         float fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/*     */         
/* 111 */         float fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/*     */         
/* 113 */         float fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/*     */         
/* 115 */         Thaumcraft.proxy.wispFX3(this.field_70170_p, this.field_70165_t + fx, this.field_70163_u + fy, this.field_70161_v + fz, this.field_70165_t + fx * 8.0F, this.field_70163_u + fy * 8.0F, this.field_70161_v + fz * 8.0F, 0.3F, 3, true, 0.02F);
/*     */         
/*     */ 
/* 118 */         fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 119 */         fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 120 */         fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 121 */         Thaumcraft.proxy.wispFX3(this.field_70170_p, this.field_70165_t + fx, this.field_70163_u + fy, this.field_70161_v + fz, this.field_70165_t + fx * 8.0F, this.field_70163_u + fy * 8.0F, this.field_70161_v + fz * 8.0F, 0.3F, 2, true, 0.02F);
/*     */         
/*     */ 
/* 124 */         fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 125 */         fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 126 */         fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 127 */         Thaumcraft.proxy.wispFX3(this.field_70170_p, this.field_70165_t + fx, this.field_70163_u + fy, this.field_70161_v + fz, this.field_70165_t + fx * 8.0F, this.field_70163_u + fy * 8.0F, this.field_70161_v + fz * 8.0F, 0.3F, 0, true, 0.02F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 133 */     if (!this.field_70170_p.field_72995_K) {
/* 134 */       List list = this.field_70170_p.func_72839_b(func_85052_h(), this.field_70121_D.func_72314_b(2.0D, 2.0D, 2.0D));
/*     */       
/* 136 */       for (int i = 0; i < list.size(); i++) {
/* 137 */         Entity entity1 = (Entity)list.get(i);
/*     */         
/* 139 */         if ((!(entity1 instanceof thaumcraft.common.entities.monster.EntityPech)) && ((entity1 instanceof EntityLivingBase)))
/*     */         {
/* 141 */           ((EntityLivingBase)entity1).func_70097_a(net.minecraft.util.DamageSource.func_76356_a(this, func_85052_h()), this.strength + 2);
/*     */           try {
/* 143 */             if (this.nightshade) {
/* 144 */               ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 100 + this.duration * 40, this.strength));
/* 145 */               ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100 + this.duration * 40, this.strength + 1));
/* 146 */               ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 100 + this.duration * 40, this.strength));
/*     */             } else {
/* 148 */               switch (this.field_70146_Z.nextInt(3)) {
/*     */               case 0: 
/* 150 */                 ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 100 + this.duration * 40, this.strength));
/* 151 */                 break;
/*     */               case 1: 
/* 153 */                 ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100 + this.duration * 40, this.strength + 1));
/* 154 */                 break;
/*     */               case 2: 
/* 156 */                 ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 100 + this.duration * 40, this.strength));
/*     */               }
/*     */               
/*     */             }
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/*     */       
/* 165 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70053_R()
/*     */   {
/* 174 */     return 0.1F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityPechBlast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */