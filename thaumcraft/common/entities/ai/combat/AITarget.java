/*     */ package thaumcraft.common.entities.ai.combat;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ public abstract class AITarget
/*     */   extends EntityAIBase
/*     */ {
/*     */   protected EntityCreature taskOwner;
/*     */   protected float targetDistance;
/*     */   protected boolean shouldCheckSight;
/*     */   private boolean field_75303_a;
/*     */   private int field_75301_b;
/*     */   private int field_75302_c;
/*     */   private int field_75298_g;
/*     */   
/*     */   public AITarget(EntityCreature par1EntityLiving, float par2, boolean par3)
/*     */   {
/*  31 */     this(par1EntityLiving, par2, par3, false);
/*     */   }
/*     */   
/*     */   public AITarget(EntityCreature par1EntityLiving, float par2, boolean par3, boolean par4)
/*     */   {
/*  36 */     this.field_75301_b = 0;
/*  37 */     this.field_75302_c = 0;
/*  38 */     this.field_75298_g = 0;
/*  39 */     this.taskOwner = par1EntityLiving;
/*  40 */     this.targetDistance = par2;
/*  41 */     this.shouldCheckSight = par3;
/*  42 */     this.field_75303_a = par4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  50 */     EntityLivingBase var1 = this.taskOwner.func_70638_az();
/*     */     
/*  52 */     if (var1 == null)
/*     */     {
/*  54 */       return false;
/*     */     }
/*  56 */     if (!var1.func_70089_S())
/*     */     {
/*  58 */       return false;
/*     */     }
/*  60 */     if (this.taskOwner.func_70068_e(var1) > this.targetDistance * this.targetDistance)
/*     */     {
/*  62 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  66 */     if (this.shouldCheckSight)
/*     */     {
/*  68 */       if (this.taskOwner.func_70635_at().func_75522_a(var1))
/*     */       {
/*  70 */         this.field_75298_g = 0;
/*     */       }
/*  72 */       else if (++this.field_75298_g > 60)
/*     */       {
/*  74 */         return false;
/*     */       }
/*     */     }
/*     */     
/*  78 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  87 */     this.field_75301_b = 0;
/*  88 */     this.field_75302_c = 0;
/*  89 */     this.field_75298_g = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*  97 */     this.taskOwner.func_70624_b((EntityLiving)null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2)
/*     */   {
/* 105 */     if (par1EntityLiving == null)
/*     */     {
/* 107 */       return false;
/*     */     }
/* 109 */     if (par1EntityLiving == this.taskOwner)
/*     */     {
/* 111 */       return false;
/*     */     }
/* 113 */     if (!par1EntityLiving.func_70089_S())
/*     */     {
/* 115 */       return false;
/*     */     }
/* 117 */     if (!this.taskOwner.func_70686_a(par1EntityLiving.getClass()))
/*     */     {
/* 119 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 123 */     if (((this.taskOwner instanceof EntityTameable)) && (((EntityTameable)this.taskOwner).func_70909_n()))
/*     */     {
/* 125 */       if (((par1EntityLiving instanceof EntityTameable)) && (((EntityTameable)par1EntityLiving).func_70909_n()))
/*     */       {
/* 127 */         return false;
/*     */       }
/*     */       
/* 130 */       if (par1EntityLiving == ((EntityTameable)this.taskOwner).func_70902_q())
/*     */       {
/* 132 */         return false;
/*     */       }
/*     */     } else {
/* 135 */       if (((par1EntityLiving instanceof EntityPlayer)) && (!par2) && (((EntityPlayer)par1EntityLiving).field_71075_bZ.field_75102_a))
/*     */       {
/* 137 */         return false;
/*     */       }
/* 139 */       if (((par1EntityLiving instanceof EntityPlayer)) && (((EntityPlayer)par1EntityLiving).func_70005_c_().equals(((EntityGolemBase)this.taskOwner).getOwnerName())))
/*     */       {
/*     */ 
/* 142 */         return false;
/*     */       }
/*     */     }
/* 145 */     if (!this.taskOwner.func_110176_b(MathHelper.func_76128_c(par1EntityLiving.field_70165_t), MathHelper.func_76128_c(par1EntityLiving.field_70163_u), MathHelper.func_76128_c(par1EntityLiving.field_70161_v)))
/*     */     {
/* 147 */       return false;
/*     */     }
/* 149 */     if ((this.shouldCheckSight) && (!this.taskOwner.func_70635_at().func_75522_a(par1EntityLiving)))
/*     */     {
/* 151 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 155 */     if (this.field_75303_a)
/*     */     {
/* 157 */       if (--this.field_75302_c <= 0)
/*     */       {
/* 159 */         this.field_75301_b = 0;
/*     */       }
/*     */       
/* 162 */       if (this.field_75301_b == 0)
/*     */       {
/* 164 */         this.field_75301_b = (func_75295_a(par1EntityLiving) ? 1 : 2);
/*     */       }
/*     */       
/* 167 */       if (this.field_75301_b == 2)
/*     */       {
/* 169 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 173 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean func_75295_a(EntityLivingBase par1EntityLiving)
/*     */   {
/* 180 */     this.field_75302_c = (10 + this.taskOwner.func_70681_au().nextInt(5));
/* 181 */     PathEntity var2 = this.taskOwner.func_70661_as().func_75494_a(par1EntityLiving);
/*     */     
/* 183 */     if (var2 == null)
/*     */     {
/* 185 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 189 */     PathPoint var3 = var2.func_75870_c();
/*     */     
/* 191 */     if (var3 == null)
/*     */     {
/* 193 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 197 */     int var4 = var3.field_75839_a - MathHelper.func_76128_c(par1EntityLiving.field_70165_t);
/* 198 */     int var5 = var3.field_75838_c - MathHelper.func_76128_c(par1EntityLiving.field_70161_v);
/* 199 */     return var4 * var4 + var5 * var5 <= 2.25D;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AITarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */