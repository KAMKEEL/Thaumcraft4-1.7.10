/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.EntityMoveHelper;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.entities.ai.misc.AIWander;
/*     */ 
/*     */ public class EntityWatcher extends EntityMob
/*     */ {
/*     */   private float field_175482_b;
/*     */   private float field_175484_c;
/*     */   private float field_175483_bk;
/*     */   private float field_175485_bl;
/*     */   private float field_175486_bm;
/*     */   private EntityLivingBase field_175478_bn;
/*     */   private int field_175479_bo;
/*     */   private boolean field_175480_bp;
/*     */   private AIWander wander;
/*     */   private EntityMoveHelper moveHelper;
/*     */   private GuardianLookHelper lookHelper;
/*     */   
/*     */   public EntityWatcher(World worldIn)
/*     */   {
/*  45 */     super(worldIn);
/*  46 */     this.field_70728_aV = 10;
/*  47 */     func_70105_a(0.85F, 0.85F);
/*  48 */     this.field_70714_bg.func_75776_a(4, new AIGuardianAttack());
/*     */     EntityAIMoveTowardsRestriction entityaimovetowardsrestriction;
/*  50 */     this.field_70714_bg.func_75776_a(5, entityaimovetowardsrestriction = new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  51 */     this.field_70714_bg.func_75776_a(7, this.wander = new AIWander(this, 1.0D));
/*  52 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, net.minecraft.entity.player.EntityPlayer.class, 8.0F));
/*  53 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityWatcher.class, 12.0F, 0.01F));
/*  54 */     this.field_70714_bg.func_75776_a(9, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  55 */     this.wander.func_75248_a(3);
/*  56 */     entityaimovetowardsrestriction.func_75248_a(3);
/*  57 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityLivingBase.class, 10, true, false));
/*  58 */     this.lookHelper = new GuardianLookHelper(this);
/*  59 */     this.moveHelper = new GuardianMoveHelper();
/*  60 */     this.field_175484_c = (this.field_175482_b = this.field_70146_Z.nextFloat());
/*  61 */     this.field_70178_ae = true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  67 */     super.func_110147_ax();
/*  68 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
/*  69 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D);
/*  70 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0D);
/*  71 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound tagCompund)
/*     */   {
/*  77 */     super.func_70037_a(tagCompund);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound tagCompound)
/*     */   {
/*  83 */     super.func_70014_b(tagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GuardianLookHelper getLookHelper()
/*     */   {
/*  95 */     return this.lookHelper;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/* 101 */     super.func_70088_a();
/* 102 */     this.field_70180_af.func_75682_a(16, Integer.valueOf(0));
/* 103 */     this.field_70180_af.func_75682_a(17, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   private boolean getFlags(int p_175468_1_)
/*     */   {
/* 108 */     return (this.field_70180_af.func_75679_c(16) & p_175468_1_) != 0;
/*     */   }
/*     */   
/*     */   private void setFlags(int p_175473_1_, boolean p_175473_2_)
/*     */   {
/* 113 */     int j = this.field_70180_af.func_75679_c(16);
/*     */     
/* 115 */     if (p_175473_2_)
/*     */     {
/* 117 */       this.field_70180_af.func_75692_b(16, Integer.valueOf(j | p_175473_1_));
/*     */     }
/*     */     else
/*     */     {
/* 121 */       this.field_70180_af.func_75692_b(16, Integer.valueOf(j & (p_175473_1_ ^ 0xFFFFFFFF)));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isGazing()
/*     */   {
/* 127 */     return getFlags(2);
/*     */   }
/*     */   
/*     */   private void setGazing(boolean p_175476_1_)
/*     */   {
/* 132 */     setFlags(2, p_175476_1_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_175464_ck()
/*     */   {
/* 139 */     return func_175461_cl() ? 60 : 80;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_175461_cl()
/*     */   {
/* 145 */     return getFlags(4);
/*     */   }
/*     */   
/*     */ 
/*     */   private void func_175463_b(int p_175463_1_)
/*     */   {
/* 151 */     this.field_70180_af.func_75692_b(17, Integer.valueOf(p_175463_1_));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_175474_cn()
/*     */   {
/* 157 */     return this.field_70180_af.func_75679_c(17) != 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public EntityLivingBase getTargetedEntity()
/*     */   {
/* 163 */     if (!func_175474_cn())
/*     */     {
/* 165 */       return null;
/*     */     }
/* 167 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/* 169 */       if (this.field_175478_bn != null)
/*     */       {
/* 171 */         return this.field_175478_bn;
/*     */       }
/*     */       
/*     */ 
/* 175 */       Entity entity = this.field_70170_p.func_73045_a(this.field_70180_af.func_75679_c(17));
/*     */       
/* 177 */       if ((entity instanceof EntityLivingBase))
/*     */       {
/* 179 */         this.field_175478_bn = ((EntityLivingBase)entity);
/* 180 */         return this.field_175478_bn;
/*     */       }
/*     */       
/*     */ 
/* 184 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 190 */     return func_70638_az();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145781_i(int p_145781_1_)
/*     */   {
/* 196 */     super.func_145781_i(p_145781_1_);
/*     */     
/* 198 */     if (p_145781_1_ == 16)
/*     */     {
/* 200 */       if ((func_175461_cl()) && (this.field_70130_N < 1.0F))
/*     */       {
/* 202 */         func_70105_a(1.9975F, 1.9975F);
/*     */       }
/*     */     }
/* 205 */     else if (p_145781_1_ == 17)
/*     */     {
/* 207 */       this.field_175479_bo = 0;
/* 208 */       this.field_175478_bn = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 215 */     return 160;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 221 */     return func_175461_cl() ? "mob.guardian.elder.idle" : !func_70090_H() ? "mob.guardian.land.idle" : "mob.guardian.idle";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 227 */     return func_175461_cl() ? "mob.guardian.elder.hit" : !func_70090_H() ? "mob.guardian.land.hit" : "mob.guardian.hit";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 233 */     return func_175461_cl() ? "mob.guardian.elder.death" : !func_70090_H() ? "mob.guardian.land.death" : "mob.guardian.death";
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 239 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 245 */     return this.field_70131_O * 0.5F;
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70783_a(int x, int y, int z)
/*     */   {
/* 251 */     return this.field_70170_p.func_147437_c(x, y, z) ? 10.0F : super.func_70783_a(x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 257 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/* 259 */       this.field_175484_c = this.field_175482_b;
/* 260 */       if (isGazing())
/*     */       {
/* 262 */         if (this.field_175483_bk < 0.5F)
/*     */         {
/* 264 */           this.field_175483_bk = 4.0F;
/*     */         }
/*     */         else
/*     */         {
/* 268 */           this.field_175483_bk += (0.5F - this.field_175483_bk) * 0.1F;
/*     */         }
/*     */         
/*     */       }
/*     */       else {
/* 273 */         this.field_175483_bk += (0.125F - this.field_175483_bk) * 0.2F;
/*     */       }
/*     */       
/* 276 */       this.field_175482_b += this.field_175483_bk;
/* 277 */       this.field_175486_bm = this.field_175485_bl;
/*     */       
/*     */ 
/* 280 */       if (isGazing())
/*     */       {
/* 282 */         this.field_175485_bl += (0.0F - this.field_175485_bl) * 0.25F;
/*     */       }
/*     */       else
/*     */       {
/* 286 */         this.field_175485_bl += (1.0F - this.field_175485_bl) * 0.06F;
/*     */       }
/*     */       
/* 289 */       if (isGazing())
/*     */       {
/* 291 */         Vec3 vec3 = func_70676_i(0.0F);
/*     */         
/* 293 */         for (int i = 0; i < 2; i++)
/*     */         {
/* 295 */           this.field_70170_p.func_72869_a("bubble", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N - vec3.field_72450_a * 1.5D, this.field_70163_u + this.field_70146_Z.nextDouble() * this.field_70131_O - vec3.field_72448_b * 1.5D, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N - vec3.field_72449_c * 1.5D, 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 303 */       if (func_175474_cn())
/*     */       {
/* 305 */         if (this.field_175479_bo < func_175464_ck())
/*     */         {
/* 307 */           this.field_175479_bo += 1;
/*     */         }
/*     */         
/* 310 */         EntityLivingBase entitylivingbase = getTargetedEntity();
/*     */         
/* 312 */         if (entitylivingbase != null)
/*     */         {
/* 314 */           getLookHelper().func_75651_a(entitylivingbase, 90.0F, 90.0F);
/* 315 */           getLookHelper().func_75649_a();
/* 316 */           double d5 = func_175477_p(0.0F);
/* 317 */           double d0 = entitylivingbase.field_70165_t - this.field_70165_t;
/* 318 */           double d1 = entitylivingbase.field_70163_u + entitylivingbase.field_70131_O * 0.5F - (this.field_70163_u + func_70047_e());
/* 319 */           double d2 = entitylivingbase.field_70161_v - this.field_70161_v;
/* 320 */           double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/* 321 */           d0 /= d3;
/* 322 */           d1 /= d3;
/* 323 */           d2 /= d3;
/* 324 */           double d4 = this.field_70146_Z.nextDouble();
/*     */           
/* 326 */           while (d4 < d3)
/*     */           {
/* 328 */             d4 += 1.8D - d5 + this.field_70146_Z.nextDouble() * (1.7D - d5);
/* 329 */             this.field_70170_p.func_72869_a("bubble", this.field_70165_t + d0 * d4, this.field_70163_u + d1 * d4 + func_70047_e(), this.field_70161_v + d2 * d4, 0.0D, 0.0D, 0.0D);
/*     */           }
/*     */         }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 353 */     if (func_175474_cn())
/*     */     {
/* 355 */       this.field_70177_z = this.field_70759_as;
/*     */     }
/*     */     
/* 358 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_175471_a(float p_175471_1_)
/*     */   {
/* 364 */     return this.field_175484_c + (this.field_175482_b - this.field_175484_c) * p_175471_1_;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_175469_o(float p_175469_1_)
/*     */   {
/* 370 */     return this.field_175486_bm + (this.field_175485_bl - this.field_175486_bm) * p_175469_1_;
/*     */   }
/*     */   
/*     */   public float func_175477_p(float p_175477_1_)
/*     */   {
/* 375 */     return (this.field_175479_bo + p_175477_1_) / func_175464_ck();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70619_bc()
/*     */   {
/* 381 */     super.func_70619_bc();
/*     */     
/* 383 */     if (func_175461_cl())
/*     */     {
/* 385 */       if (!func_110175_bO())
/*     */       {
/* 387 */         func_110171_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 16);
/*     */       }
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70814_o()
/*     */   {
/* 421 */     return true;
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
/*     */   public boolean func_70097_a(DamageSource source, float amount)
/*     */   {
/* 435 */     if ((!isGazing()) && (!source.func_82725_o()) && ((source.func_76364_f() instanceof EntityLivingBase)))
/*     */     {
/* 437 */       EntityLivingBase entitylivingbase = (EntityLivingBase)source.func_76364_f();
/*     */       
/* 439 */       if (!source.func_94541_c())
/*     */       {
/* 441 */         entitylivingbase.func_70097_a(DamageSource.func_92087_a(this), 2.0F);
/* 442 */         entitylivingbase.func_85030_a("damage.thorns", 0.5F, 1.0F);
/*     */       }
/*     */     }
/*     */     
/* 446 */     this.wander.setWander();
/* 447 */     return super.func_70097_a(source, amount);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70646_bf()
/*     */   {
/* 453 */     return 180;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70612_e(float p_70612_1_, float p_70612_2_)
/*     */   {
/* 460 */     func_70060_a(p_70612_1_, p_70612_2_, 0.1F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 467 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 468 */     this.field_70159_w *= 0.8999999761581421D;
/* 469 */     this.field_70181_x *= 0.8999999761581421D;
/* 470 */     this.field_70179_y *= 0.8999999761581421D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   class AIGuardianAttack
/*     */     extends EntityAIBase
/*     */   {
/* 491 */     private EntityWatcher field_179456_a = EntityWatcher.this;
/*     */     private int field_179455_b;
/*     */     private static final String __OBFID = "CL_00002211";
/*     */     
/*     */     public AIGuardianAttack()
/*     */     {
/* 497 */       func_75248_a(3);
/*     */     }
/*     */     
/*     */     public boolean func_75250_a()
/*     */     {
/* 502 */       EntityLivingBase entitylivingbase = this.field_179456_a.func_70638_az();
/* 503 */       return (entitylivingbase != null) && (entitylivingbase.func_70089_S());
/*     */     }
/*     */     
/*     */     public boolean func_75253_b()
/*     */     {
/* 508 */       return (super.func_75253_b()) && ((this.field_179456_a.func_175461_cl()) || (this.field_179456_a.func_70068_e(this.field_179456_a.func_70638_az()) > 9.0D));
/*     */     }
/*     */     
/*     */     public void func_75249_e()
/*     */     {
/* 513 */       this.field_179455_b = -10;
/* 514 */       this.field_179456_a.func_70661_as().func_75499_g();
/* 515 */       this.field_179456_a.getLookHelper().func_75651_a(this.field_179456_a.func_70638_az(), 90.0F, 90.0F);
/* 516 */       this.field_179456_a.field_70160_al = true;
/*     */     }
/*     */     
/*     */     public void func_75251_c()
/*     */     {
/* 521 */       this.field_179456_a.func_175463_b(0);
/* 522 */       this.field_179456_a.func_70624_b((EntityLivingBase)null);
/* 523 */       this.field_179456_a.wander.setWander();
/*     */     }
/*     */     
/*     */     public void func_75246_d()
/*     */     {
/* 528 */       EntityLivingBase entitylivingbase = this.field_179456_a.func_70638_az();
/* 529 */       this.field_179456_a.func_70661_as().func_75499_g();
/* 530 */       this.field_179456_a.getLookHelper().func_75651_a(entitylivingbase, 90.0F, 90.0F);
/*     */       
/* 532 */       if (!this.field_179456_a.func_70685_l(entitylivingbase))
/*     */       {
/* 534 */         this.field_179456_a.func_70624_b((EntityLivingBase)null);
/*     */       }
/*     */       else
/*     */       {
/* 538 */         this.field_179455_b += 1;
/*     */         
/* 540 */         if (this.field_179455_b == 0)
/*     */         {
/* 542 */           this.field_179456_a.func_175463_b(this.field_179456_a.func_70638_az().func_145782_y());
/* 543 */           this.field_179456_a.field_70170_p.func_72960_a(this.field_179456_a, (byte)21);
/*     */         }
/* 545 */         else if (this.field_179455_b >= this.field_179456_a.func_175464_ck())
/*     */         {
/* 547 */           float f = 1.0F;
/*     */           
/* 549 */           if (this.field_179456_a.field_70170_p.field_73013_u == net.minecraft.world.EnumDifficulty.HARD)
/*     */           {
/* 551 */             f += 2.0F;
/*     */           }
/*     */           
/* 554 */           if (this.field_179456_a.func_175461_cl())
/*     */           {
/* 556 */             f += 2.0F;
/*     */           }
/*     */           
/* 559 */           entitylivingbase.func_70097_a(DamageSource.func_76354_b(this.field_179456_a, this.field_179456_a), f);
/* 560 */           entitylivingbase.func_70097_a(DamageSource.func_76358_a(this.field_179456_a), (float)this.field_179456_a.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
/* 561 */           this.field_179456_a.func_70624_b((EntityLivingBase)null);
/*     */         }
/* 563 */         else if ((this.field_179455_b < 60) || (this.field_179455_b % 20 != 0)) {}
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 568 */         super.func_75246_d();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   class GuardianLookHelper extends EntityLookHelper
/*     */   {
/*     */     public GuardianLookHelper(EntityLiving p_i1613_1_)
/*     */     {
/* 577 */       super();
/*     */     }
/*     */     
/*     */     double getX() {
/*     */       try {
/* 582 */         return ((Double)ReflectionHelper.getPrivateValue(GuardianLookHelper.class, this, new String[] { "posX", "field_75656_e" })).doubleValue();
/*     */       } catch (Exception e) {}
/* 584 */       return 0.0D;
/*     */     }
/*     */     
/*     */     double getY()
/*     */     {
/*     */       try {
/* 590 */         return ((Double)ReflectionHelper.getPrivateValue(GuardianLookHelper.class, this, new String[] { "posY", "field_75653_f" })).doubleValue();
/*     */       } catch (Exception e) {}
/* 592 */       return 0.0D;
/*     */     }
/*     */     
/*     */     double getZ()
/*     */     {
/*     */       try {
/* 598 */         return ((Double)ReflectionHelper.getPrivateValue(GuardianLookHelper.class, this, new String[] { "posZ", "field_75654_g" })).doubleValue();
/*     */       } catch (Exception e) {}
/* 600 */       return 0.0D;
/*     */     }
/*     */     
/*     */     boolean getLooking()
/*     */     {
/*     */       try {
/* 606 */         return ((Boolean)ReflectionHelper.getPrivateValue(GuardianLookHelper.class, this, new String[] { "isLooking", "field_75655_d" })).booleanValue();
/*     */       } catch (Exception e) {}
/* 608 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   class GuardianMoveHelper
/*     */     extends EntityMoveHelper
/*     */   {
/* 615 */     private EntityWatcher field_179930_g = EntityWatcher.this;
/*     */     
/*     */     public GuardianMoveHelper()
/*     */     {
/* 619 */       super();
/*     */     }
/*     */     
/*     */     double getX() {
/*     */       try {
/* 624 */         return ((Double)ReflectionHelper.getPrivateValue(GuardianMoveHelper.class, this, new String[] { "posX", "field_75646_b" })).doubleValue();
/*     */       } catch (Exception e) {}
/* 626 */       return 0.0D;
/*     */     }
/*     */     
/*     */     double getY()
/*     */     {
/*     */       try {
/* 632 */         return ((Double)ReflectionHelper.getPrivateValue(GuardianMoveHelper.class, this, new String[] { "posY", "field_75647_c" })).doubleValue();
/*     */       } catch (Exception e) {}
/* 634 */       return 0.0D;
/*     */     }
/*     */     
/*     */     double getZ()
/*     */     {
/*     */       try {
/* 640 */         return ((Double)ReflectionHelper.getPrivateValue(GuardianMoveHelper.class, this, new String[] { "posZ", "field_75644_d" })).doubleValue();
/*     */       } catch (Exception e) {}
/* 642 */       return 0.0D;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_75641_c()
/*     */     {
/* 648 */       if ((func_75640_a()) && (!this.field_179930_g.func_70661_as().func_75500_f()))
/*     */       {
/* 650 */         double d0 = getX() - this.field_179930_g.field_70165_t;
/* 651 */         double d1 = getY() - this.field_179930_g.field_70163_u;
/* 652 */         double d2 = getZ() - this.field_179930_g.field_70161_v;
/* 653 */         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/* 654 */         d3 = MathHelper.func_76133_a(d3);
/* 655 */         d1 /= d3;
/* 656 */         float f = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 657 */         this.field_179930_g.field_70177_z = limitAngle(this.field_179930_g.field_70177_z, f, 30.0F);
/* 658 */         this.field_179930_g.field_70761_aq = this.field_179930_g.field_70177_z;
/* 659 */         float f1 = (float)(func_75638_b() * this.field_179930_g.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
/* 660 */         this.field_179930_g.func_70659_e(this.field_179930_g.func_70689_ay() + (f1 - this.field_179930_g.func_70689_ay()) * 0.125F);
/* 661 */         double d4 = Math.sin((this.field_179930_g.field_70173_aa + this.field_179930_g.func_145782_y()) * 0.5D) * 0.05D;
/* 662 */         double d5 = Math.cos(this.field_179930_g.field_70177_z * 3.1415927F / 180.0F);
/* 663 */         double d6 = Math.sin(this.field_179930_g.field_70177_z * 3.1415927F / 180.0F);
/* 664 */         this.field_179930_g.field_70159_w += d4 * d5;
/* 665 */         this.field_179930_g.field_70179_y += d4 * d6;
/* 666 */         d4 = Math.sin((this.field_179930_g.field_70173_aa + this.field_179930_g.func_145782_y()) * 0.75D) * 0.05D;
/* 667 */         this.field_179930_g.field_70181_x += d4 * (d6 + d5) * 0.25D;
/* 668 */         this.field_179930_g.field_70181_x += this.field_179930_g.func_70689_ay() * d1 * 0.1D;
/* 669 */         EntityWatcher.GuardianLookHelper entitylookhelper = this.field_179930_g.getLookHelper();
/* 670 */         double d7 = this.field_179930_g.field_70165_t + d0 / d3 * 2.0D;
/* 671 */         double d8 = this.field_179930_g.func_70047_e() + this.field_179930_g.field_70163_u + d1 / d3 * 1.0D;
/* 672 */         double d9 = this.field_179930_g.field_70161_v + d2 / d3 * 2.0D;
/* 673 */         double d10 = entitylookhelper.getX();
/* 674 */         double d11 = entitylookhelper.getY();
/* 675 */         double d12 = entitylookhelper.getZ();
/*     */         
/* 677 */         if (!entitylookhelper.getLooking())
/*     */         {
/* 679 */           d10 = d7;
/* 680 */           d11 = d8;
/* 681 */           d12 = d9;
/*     */         }
/*     */         
/* 684 */         this.field_179930_g.getLookHelper().func_75650_a(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
/* 685 */         this.field_179930_g.setGazing(true);
/*     */       }
/*     */       else
/*     */       {
/* 689 */         this.field_179930_g.func_70659_e(0.0F);
/* 690 */         this.field_179930_g.setGazing(false);
/*     */       }
/*     */     }
/*     */     
/*     */     private float limitAngle(float p_75639_1_, float p_75639_2_, float p_75639_3_)
/*     */     {
/* 696 */       float f3 = MathHelper.func_76142_g(p_75639_2_ - p_75639_1_);
/*     */       
/* 698 */       if (f3 > p_75639_3_)
/*     */       {
/* 700 */         f3 = p_75639_3_;
/*     */       }
/*     */       
/* 703 */       if (f3 < -p_75639_3_)
/*     */       {
/* 705 */         f3 = -p_75639_3_;
/*     */       }
/*     */       
/* 708 */       return p_75639_1_ + f3;
/*     */     }
/*     */   }
/*     */   
/* 712 */   IEntitySelector field_82192_a = new IEntitySelector()
/*     */   {
/*     */ 
/*     */     public boolean func_82704_a(Entity ent)
/*     */     {
/* 717 */       return true;
/*     */     }
/*     */   };
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityWatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */