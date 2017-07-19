/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityShockOrb extends EntityThrowable
/*     */ {
/*     */   public EntityShockOrb(World par1World)
/*     */   {
/*  19 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityShockOrb(World par1World, EntityLivingBase par2EntityLiving) {
/*  23 */     super(par1World, par2EntityLiving);
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  28 */     return 0.05F;
/*     */   }
/*     */   
/*  31 */   public int area = 4;
/*  32 */   public int damage = 5;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  39 */     if (!this.field_70170_p.field_72995_K) {
/*  40 */       java.util.ArrayList<Entity> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, Entity.class, this.area);
/*  41 */       for (Entity e : list) {
/*  42 */         if (EntityUtils.canEntityBeSeen(this, e))
/*  43 */           e.func_70097_a(DamageSource.func_76354_b(this, func_85052_h()), this.damage);
/*     */       }
/*  45 */       for (int a = 0; a < 20; a++) {
/*  46 */         int xx = MathHelper.floor_double(this.field_70165_t) + this.field_70146_Z.nextInt(this.area) - this.field_70146_Z.nextInt(this.area);
/*  47 */         int yy = MathHelper.floor_double(this.field_70163_u) + this.area;
/*  48 */         int zz = MathHelper.floor_double(this.field_70161_v) + this.field_70146_Z.nextInt(this.area) - this.field_70146_Z.nextInt(this.area);
/*  49 */         while ((this.field_70170_p.func_147437_c(xx, yy, zz)) && (yy > MathHelper.floor_double(this.field_70163_u) - this.area)) yy--;
/*  50 */         if ((this.field_70170_p.func_147437_c(xx, yy + 1, zz)) && (!this.field_70170_p.func_147437_c(xx, yy, zz)) && (this.field_70170_p.func_147439_a(xx, yy + 1, zz) != ConfigBlocks.blockAiry) && (EntityUtils.canEntityBeSeen(this, xx + 0.5D, yy + 1.5D, zz + 0.5D)))
/*     */         {
/*     */ 
/*  53 */           this.field_70170_p.func_147465_d(xx, yy + 1, zz, ConfigBlocks.blockAiry, 10, 3);
/*     */         }
/*     */       }
/*     */     }
/*  57 */     thaumcraft.common.Thaumcraft.proxy.burst(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, 3.0F);
/*  58 */     this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:shock", 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*  59 */     func_70106_y();
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  64 */     super.func_70071_h_();
/*  65 */     if (this.field_70173_aa > 500) func_70106_y();
/*     */   }
/*     */   
/*     */   public float func_70053_R()
/*     */   {
/*  70 */     return 0.1F;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*     */   {
/*  76 */     if (func_85032_ar())
/*     */     {
/*  78 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  82 */     func_70018_K();
/*     */     
/*  84 */     if (p_70097_1_.func_76346_g() != null)
/*     */     {
/*  86 */       Vec3 vec3 = p_70097_1_.func_76346_g().func_70040_Z();
/*     */       
/*  88 */       if (vec3 != null)
/*     */       {
/*  90 */         this.field_70159_w = vec3.field_72450_a;
/*  91 */         this.field_70181_x = vec3.field_72448_b;
/*  92 */         this.field_70179_y = vec3.field_72449_c;
/*  93 */         this.field_70159_w *= 0.9D;
/*  94 */         this.field_70181_x *= 0.9D;
/*  95 */         this.field_70179_y *= 0.9D;
/*  96 */         this.field_70170_p.func_72956_a(this, "thaumcraft:zap", 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*     */       }
/*  98 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 102 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityShockOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */