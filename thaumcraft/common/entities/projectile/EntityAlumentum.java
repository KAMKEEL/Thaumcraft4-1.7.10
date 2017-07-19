/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class EntityAlumentum extends EntityThrowable
/*    */ {
/*    */   public EntityAlumentum(World par1World)
/*    */   {
/* 14 */     super(par1World);
/*    */   }
/*    */   
/*    */   public EntityAlumentum(World par1World, net.minecraft.entity.EntityLivingBase par2EntityLiving)
/*    */   {
/* 19 */     super(par1World, par2EntityLiving);
/*    */   }
/*    */   
/*    */   public EntityAlumentum(World par1World, double par2, double par4, double par6)
/*    */   {
/* 24 */     super(par1World, par2, par4, par6);
/*    */   }
/*    */   
/*    */ 
/*    */   protected float func_70182_d()
/*    */   {
/* 30 */     return 0.75F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 37 */     super.func_70071_h_();
/*    */     
/* 39 */     if (this.field_70170_p.field_72995_K)
/*    */     {
/* 41 */       for (int a = 0; a < 3; a++) {
/* 42 */         Thaumcraft.proxy.wispFX2(this.field_70170_p, this.field_70165_t + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F, this.field_70163_u + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F, this.field_70161_v + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F, 0.3F, 5, true, true, 0.02F);
/*    */         
/*    */ 
/*    */ 
/* 46 */         double x2 = (this.field_70165_t + this.field_70169_q) / 2.0D + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 47 */         double y2 = (this.field_70163_u + this.field_70167_r) / 2.0D + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 48 */         double z2 = (this.field_70161_v + this.field_70166_s) / 2.0D + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3F;
/* 49 */         Thaumcraft.proxy.wispFX2(this.field_70170_p, x2, y2, z2, 0.3F, 5, true, true, 0.02F);
/* 50 */         Thaumcraft.proxy.sparkle((float)this.field_70165_t + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, (float)this.field_70163_u + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, (float)this.field_70161_v + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1F, 6);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_70184_a(MovingObjectPosition par1MovingObjectPosition)
/*    */   {
/* 65 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 67 */       boolean var2 = this.field_70170_p.func_82736_K().func_82766_b("mobGriefing");
/* 68 */       this.field_70170_p.func_72876_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.66F, var2);
/* 69 */       func_70106_y();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public float func_70053_R()
/*    */   {
/* 77 */     return 0.1F;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityAlumentum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */