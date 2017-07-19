/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSourceIndirect;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityEmber
/*     */   extends EntityThrowable
/*     */   implements IEntityAdditionalSpawnData
/*     */ {
/*     */   public EntityEmber(World par1World)
/*     */   {
/*  27 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityEmber(World par1World, EntityLivingBase par2EntityLiving, float scatter) {
/*  31 */     super(par1World, par2EntityLiving);
/*  32 */     func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, func_70182_d(), scatter);
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  37 */     return 0.0F;
/*     */   }
/*     */   
/*     */   protected float func_70182_d()
/*     */   {
/*  42 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  48 */     if (this.field_70173_aa > this.duration) { func_70106_y();
/*     */     }
/*  50 */     if (this.duration <= 20) {
/*  51 */       this.field_70159_w *= 0.95D;
/*  52 */       this.field_70181_x *= 0.95D;
/*  53 */       this.field_70179_y *= 0.95D;
/*     */     } else {
/*  55 */       this.field_70159_w *= 0.975D;
/*  56 */       this.field_70181_x *= 0.975D;
/*  57 */       this.field_70179_y *= 0.975D;
/*     */     }
/*     */     
/*  60 */     if (this.field_70122_E) {
/*  61 */       this.field_70159_w *= 0.66D;
/*  62 */       this.field_70181_x *= 0.66D;
/*  63 */       this.field_70179_y *= 0.66D;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  68 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  73 */   public int duration = 20;
/*  74 */   public int firey = 0;
/*  75 */   public float damage = 1.0F;
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  79 */     data.writeByte(this.duration);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  84 */     this.duration = data.readByte();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  93 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/*  95 */       if (mop.field_72308_g != null)
/*     */       {
/*  97 */         if ((!mop.field_72308_g.func_70045_F()) && (mop.field_72308_g.func_70097_a(new EntityDamageSourceIndirect("fireball", this, func_85052_h()).func_76361_j(), this.damage)))
/*     */         {
/*     */ 
/* 100 */           mop.field_72308_g.func_70015_d(3 + this.firey);
/*     */         }
/*     */       }
/* 103 */       else if (this.field_70146_Z.nextFloat() < 0.025F * this.firey)
/*     */       {
/* 105 */         int i = mop.field_72311_b;
/* 106 */         int j = mop.field_72312_c;
/* 107 */         int k = mop.field_72309_d;
/*     */         
/* 109 */         switch (mop.field_72310_e)
/*     */         {
/*     */         case 0: 
/* 112 */           j--;
/* 113 */           break;
/*     */         case 1: 
/* 115 */           j++;
/* 116 */           break;
/*     */         case 2: 
/* 118 */           k--;
/* 119 */           break;
/*     */         case 3: 
/* 121 */           k++;
/* 122 */           break;
/*     */         case 4: 
/* 124 */           i--;
/* 125 */           break;
/*     */         case 5: 
/* 127 */           i++;
/*     */         }
/*     */         
/* 130 */         if (this.field_70170_p.func_147437_c(i, j, k))
/*     */         {
/* 132 */           this.field_70170_p.func_147449_b(i, j, k, Blocks.field_150480_ab);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 137 */     func_70106_y();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 145 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R()
/*     */   {
/* 152 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 158 */     super.func_70014_b(par1NBTTagCompound);
/* 159 */     par1NBTTagCompound.func_74776_a("damage", this.damage);
/* 160 */     par1NBTTagCompound.func_74768_a("firey", this.firey);
/* 161 */     par1NBTTagCompound.func_74768_a("duration", this.duration);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 167 */     super.func_70037_a(par1NBTTagCompound);
/* 168 */     this.damage = par1NBTTagCompound.func_74760_g("damage");
/* 169 */     this.firey = par1NBTTagCompound.func_74762_e("firey");
/* 170 */     this.duration = par1NBTTagCompound.func_74762_e("duration");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70067_L()
/*     */   {
/* 177 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*     */   {
/* 183 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityEmber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */