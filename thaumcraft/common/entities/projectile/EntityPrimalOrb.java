/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class EntityPrimalOrb extends EntityThrowable implements IEntityAdditionalSpawnData
/*     */ {
/*     */   public EntityPrimalOrb(World par1World)
/*     */   {
/*  25 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityPrimalOrb(World par1World, EntityLivingBase par2EntityLiving, boolean seeker) {
/*  29 */     super(par1World, par2EntityLiving);
/*  30 */     this.seeker = seeker;
/*  31 */     this.oi = par2EntityLiving.func_145782_y();
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  36 */     data.writeBoolean(this.seeker);
/*  37 */     data.writeInt(this.oi);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  42 */     this.seeker = data.readBoolean();
/*  43 */     this.oi = data.readInt();
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  48 */     return 0.001F;
/*     */   }
/*     */   
/*     */   protected float func_70182_d()
/*     */   {
/*  53 */     return 0.5F;
/*     */   }
/*     */   
/*  56 */   int count = 0;
/*  57 */   boolean seeker = false;
/*  58 */   int oi = 0;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  62 */     this.count += 1;
/*  63 */     if (func_70055_a(Material.field_151567_E)) {
/*  64 */       func_70184_a(new MovingObjectPosition(this));
/*     */     }
/*     */     
/*  67 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/*  69 */       for (int a = 0; a < 6; a++) {
/*  70 */         Thaumcraft.proxy.wispFX4(this.field_70170_p, (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this, a, true, 0.0F);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  80 */       Thaumcraft.proxy.wispFX2(this.field_70170_p, this.field_70165_t + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70163_u + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70161_v + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, 0.1F, this.field_70146_Z.nextInt(6), true, true, 0.0F);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  88 */     Random rr = new Random(func_145782_y() + this.count);
/*     */     
/*  90 */     if (this.field_70173_aa > 20) {
/*  91 */       if (!this.seeker) {
/*  92 */         this.field_70159_w += (rr.nextFloat() - rr.nextFloat()) * 0.01F;
/*  93 */         this.field_70181_x += (rr.nextFloat() - rr.nextFloat()) * 0.01F;
/*  94 */         this.field_70179_y += (rr.nextFloat() - rr.nextFloat()) * 0.01F;
/*     */       } else {
/*  96 */         java.util.List<Entity> l = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityLivingBase.class, 16.0D);
/*  97 */         double d = Double.MAX_VALUE;
/*  98 */         Entity t = null;
/*  99 */         for (Entity e : l) {
/* 100 */           if ((e.func_145782_y() != this.oi) && (!e.field_70128_L)) {
/* 101 */             double dd = func_70068_e(e);
/* 102 */             if (dd < d) {
/* 103 */               d = dd;
/* 104 */               t = e;
/*     */             }
/*     */           }
/*     */         }
/* 108 */         if (t != null) {
/* 109 */           double dx = t.field_70165_t - this.field_70165_t;
/* 110 */           double dy = t.field_70121_D.field_72338_b + t.field_70131_O * 0.9D - this.field_70163_u;
/* 111 */           double dz = t.field_70161_v - this.field_70161_v;
/* 112 */           double d13 = 0.2D;
/* 113 */           dx /= d;
/* 114 */           dy /= d;
/* 115 */           dz /= d;
/*     */           
/* 117 */           this.field_70159_w += dx * d13;
/* 118 */           this.field_70181_x += dy * d13;
/* 119 */           this.field_70179_y += dz * d13;
/*     */           
/* 121 */           this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.2F, 0.2F);
/* 122 */           this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.2F, 0.2F);
/* 123 */           this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.2F, 0.2F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 128 */     super.func_70071_h_();
/* 129 */     if (this.field_70173_aa > 5000) { func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/* 137 */     if (this.field_70170_p.field_72995_K) {
/* 138 */       for (int a = 0; a < 6; a++) {
/* 139 */         for (int b = 0; b < 6; b++) {
/* 140 */           float fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.5F;
/*     */           
/* 142 */           float fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.5F;
/*     */           
/* 144 */           float fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.5F;
/*     */           
/* 146 */           Thaumcraft.proxy.wispFX3(this.field_70170_p, this.field_70165_t + fx, this.field_70163_u + fy, this.field_70161_v + fz, this.field_70165_t + fx * 10.0F, this.field_70163_u + fy * 10.0F, this.field_70161_v + fz * 10.0F, 0.4F, b, true, 0.05F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 152 */     if (!this.field_70170_p.field_72995_K) {
/* 153 */       float specialchance = 1.0F;
/* 154 */       float expl = 2.0F;
/* 155 */       if ((mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && 
/* 156 */         (func_70055_a(Material.field_151567_E))) {
/* 157 */         expl = 4.0F;
/* 158 */         specialchance = 10.0F;
/*     */       }
/*     */       
/* 161 */       this.field_70170_p.func_72876_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, expl, true);
/*     */       
/* 163 */       if ((!this.seeker) && (this.field_70146_Z.nextInt(100) <= specialchance)) {
/* 164 */         if (this.field_70146_Z.nextBoolean()) {
/* 165 */           taintSplosion();
/*     */         } else {
/* 167 */           ThaumcraftWorldGenerator.createRandomNodeAt(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, this.field_70146_Z, false, false, true);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 173 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void taintSplosion()
/*     */   {
/* 179 */     int x = (int)this.field_70165_t;
/* 180 */     int y = (int)this.field_70163_u;
/* 181 */     int z = (int)this.field_70161_v;
/* 182 */     for (int a = 0; a < 10; a++) {
/* 183 */       int xx = x + (int)(this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat() * 6.0F);
/* 184 */       int zz = z + (int)(this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat() * 6.0F);
/* 185 */       if ((this.field_70146_Z.nextBoolean()) && (this.field_70170_p.func_72807_a(xx, zz) != ThaumcraftWorldGenerator.biomeTaint))
/*     */       {
/* 187 */         Utils.setBiomeAt(this.field_70170_p, xx, zz, ThaumcraftWorldGenerator.biomeTaint);
/*     */         
/*     */ 
/* 190 */         int yy = this.field_70170_p.func_72976_f(xx, zz);
/* 191 */         if (!this.field_70170_p.func_147437_c(xx, yy - 1, zz)) {
/* 192 */           this.field_70170_p.func_147465_d(xx, yy, zz, thaumcraft.common.config.ConfigBlocks.blockTaintFibres, 0, 3);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70053_R()
/*     */   {
/* 203 */     return 0.1F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityPrimalOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */