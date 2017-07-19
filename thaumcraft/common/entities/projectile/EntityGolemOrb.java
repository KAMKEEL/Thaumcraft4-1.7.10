/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class EntityGolemOrb extends EntityThrowable implements IEntityAdditionalSpawnData
/*     */ {
/*     */   public EntityGolemOrb(World par1World)
/*     */   {
/*  25 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityGolemOrb(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase t, boolean r) {
/*  29 */     super(par1World, par2EntityLiving);
/*  30 */     this.target = t;
/*  31 */     this.red = r;
/*     */   }
/*     */   
/*  34 */   int targetID = 0;
/*     */   EntityLivingBase target;
/*  36 */   public boolean red = false;
/*     */   
/*     */ 
/*     */   protected float func_70185_h()
/*     */   {
/*  41 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  47 */     int id = -1;
/*  48 */     if (this.target != null) id = this.target.func_145782_y();
/*  49 */     data.writeInt(id);
/*  50 */     data.writeBoolean(this.red);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  55 */     int id = data.readInt();
/*     */     try {
/*  57 */       if (id >= 0) this.target = ((EntityLivingBase)this.field_70170_p.func_73045_a(id));
/*     */     } catch (Exception e) {}
/*  59 */     this.red = data.readBoolean();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  67 */     if ((!this.field_70170_p.field_72995_K) && (func_85052_h() != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY)) {
/*  68 */       mop.field_72308_g.func_70097_a(DamageSource.func_76354_b(this, func_85052_h()), (float)func_85052_h().func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * (this.red ? 1.0F : 0.6F));
/*     */     }
/*     */     
/*  71 */     this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:shock", 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*  72 */     Thaumcraft.proxy.burst(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F);
/*  73 */     func_70106_y();
/*     */   }
/*     */   
/*     */   public float func_70053_R()
/*     */   {
/*  78 */     return 0.1F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  85 */     super.func_70071_h_();
/*  86 */     if (this.field_70173_aa > (this.red ? 240 : 160)) { func_70106_y();
/*     */     }
/*  88 */     if (this.target != null) {
/*  89 */       double d = func_70068_e(this.target);
/*  90 */       double dx = this.target.field_70165_t - this.field_70165_t;
/*  91 */       double dy = this.target.field_70121_D.field_72338_b + this.target.field_70131_O * 0.6D - this.field_70163_u;
/*  92 */       double dz = this.target.field_70161_v - this.field_70161_v;
/*  93 */       double d13 = 0.2D;
/*  94 */       dx /= d;
/*  95 */       dy /= d;
/*  96 */       dz /= d;
/*     */       
/*  98 */       this.field_70159_w += dx * d13;
/*  99 */       this.field_70181_x += dy * d13;
/* 100 */       this.field_70179_y += dz * d13;
/*     */       
/* 102 */       this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.25F, 0.25F);
/* 103 */       this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.25F, 0.25F);
/* 104 */       this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.25F, 0.25F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*     */   {
/* 111 */     if (func_85032_ar())
/*     */     {
/* 113 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 117 */     func_70018_K();
/*     */     
/* 119 */     if (p_70097_1_.func_76346_g() != null)
/*     */     {
/* 121 */       Vec3 vec3 = p_70097_1_.func_76346_g().func_70040_Z();
/*     */       
/* 123 */       if (vec3 != null)
/*     */       {
/* 125 */         this.field_70159_w = vec3.field_72450_a;
/* 126 */         this.field_70181_x = vec3.field_72448_b;
/* 127 */         this.field_70179_y = vec3.field_72449_c;
/* 128 */         this.field_70159_w *= 0.9D;
/* 129 */         this.field_70181_x *= 0.9D;
/* 130 */         this.field_70179_y *= 0.9D;
/* 131 */         this.field_70170_p.func_72956_a(this, "thaumcraft:zap", 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/*     */       }
/* 133 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 137 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityGolemOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */