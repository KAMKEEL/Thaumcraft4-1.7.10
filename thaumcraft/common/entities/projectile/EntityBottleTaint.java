/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.entities.ITaintedMob;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*    */ 
/*    */ public class EntityBottleTaint
/*    */   extends EntityThrowable
/*    */ {
/*    */   public EntityBottleTaint(World p_i1788_1_)
/*    */   {
/* 25 */     super(p_i1788_1_);
/*    */   }
/*    */   
/*    */   public EntityBottleTaint(World p_i1790_1_, EntityLivingBase p_i1790_2)
/*    */   {
/* 30 */     super(p_i1790_1_, p_i1790_2);
/*    */   }
/*    */   
/*    */ 
/*    */   protected float func_70185_h()
/*    */   {
/* 36 */     return 0.05F;
/*    */   }
/*    */   
/*    */ 
/*    */   protected float func_70182_d()
/*    */   {
/* 42 */     return 0.5F;
/*    */   }
/*    */   
/*    */ 
/*    */   protected float func_70183_g()
/*    */   {
/* 48 */     return -20.0F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void func_70184_a(MovingObjectPosition p_70184_1_)
/*    */   {
/* 55 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 57 */       List ents = this.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t, this.field_70163_u, this.field_70161_v).func_72314_b(5.0D, 5.0D, 5.0D));
/*    */       
/*    */ 
/*    */ 
/*    */ 
/* 62 */       if (ents.size() > 0) {
/* 63 */         for (Object ent : ents) {
/* 64 */           EntityLivingBase el = (EntityLivingBase)ent;
/* 65 */           if ((!(el instanceof ITaintedMob)) && (!el.func_70662_br())) {
/* 66 */             el.func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 100, 0, false));
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 71 */       int x = (int)this.field_70165_t;
/* 72 */       int y = (int)this.field_70163_u;
/* 73 */       int z = (int)this.field_70161_v;
/* 74 */       for (int a = 0; a < 10; a++) {
/* 75 */         int xx = x + (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 5.0F);
/* 76 */         int zz = z + (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 5.0F);
/* 77 */         if ((this.field_70170_p.field_73012_v.nextBoolean()) && (this.field_70170_p.func_72807_a(xx, zz) != ThaumcraftWorldGenerator.biomeTaint))
/*    */         {
/* 79 */           Utils.setBiomeAt(this.field_70170_p, xx, zz, ThaumcraftWorldGenerator.biomeTaint);
/*    */           
/* 81 */           if ((this.field_70170_p.func_147445_c(xx, y - 1, zz, false)) && (this.field_70170_p.func_147439_a(xx, y, zz).isReplaceable(this.field_70170_p, xx, y, zz)))
/*    */           {
/* 83 */             this.field_70170_p.func_147465_d(xx, y, zz, ConfigBlocks.blockTaintFibres, 0, 3);
/*    */           }
/*    */         }
/*    */       }
/*    */       
/*    */ 
/* 89 */       func_70106_y();
/*    */     } else {
/* 91 */       for (int a = 0; a < Thaumcraft.proxy.particleCount(100); a++) Thaumcraft.proxy.taintsplosionFX(this);
/* 92 */       Thaumcraft.proxy.bottleTaintBreak(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityBottleTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */