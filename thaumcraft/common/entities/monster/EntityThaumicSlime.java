/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ 
/*     */ public class EntityThaumicSlime extends EntityMob implements IMob, ITaintedMob
/*     */ {
/*  24 */   private static final float[] spawnChances = { 1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F };
/*     */   
/*     */   public float field_70813_a;
/*     */   
/*     */   public float field_70811_b;
/*     */   public float field_70812_c;
/*  30 */   private int slimeJumpDelay = 0;
/*     */   
/*     */   public EntityThaumicSlime(World par1World)
/*     */   {
/*  34 */     super(par1World);
/*  35 */     int i = 1 << this.field_70146_Z.nextInt(3);
/*  36 */     this.field_70129_M = 0.0F;
/*  37 */     this.slimeJumpDelay = (this.field_70146_Z.nextInt(20) + 10);
/*  38 */     setSlimeSize(i);
/*     */   }
/*     */   
/*  41 */   int launched = 10;
/*     */   
/*     */   public EntityThaumicSlime(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving)
/*     */   {
/*  45 */     super(par1World);
/*  46 */     setSlimeSize(1);
/*  47 */     this.field_70163_u = ((par2EntityLiving.field_70121_D.field_72338_b + par2EntityLiving.field_70121_D.field_72337_e) / 2.0D);
/*  48 */     double var6 = par3EntityLiving.field_70165_t - par2EntityLiving.field_70165_t;
/*  49 */     double var8 = par3EntityLiving.field_70121_D.field_72338_b + par3EntityLiving.field_70131_O / 3.0F - this.field_70163_u;
/*  50 */     double var10 = par3EntityLiving.field_70161_v - par2EntityLiving.field_70161_v;
/*  51 */     double var12 = MathHelper.func_76133_a(var6 * var6 + var10 * var10);
/*     */     
/*  53 */     if (var12 >= 1.0E-7D)
/*     */     {
/*  55 */       float var14 = (float)(Math.atan2(var10, var6) * 180.0D / 3.141592653589793D) - 90.0F;
/*  56 */       float var15 = (float)-(Math.atan2(var8, var12) * 180.0D / 3.141592653589793D);
/*  57 */       double var16 = var6 / var12;
/*  58 */       double var18 = var10 / var12;
/*  59 */       func_70012_b(par2EntityLiving.field_70165_t + var16, this.field_70163_u, par2EntityLiving.field_70161_v + var18, var14, var15);
/*  60 */       this.field_70129_M = 0.0F;
/*  61 */       float var20 = (float)var12 * 0.2F;
/*  62 */       setThrowableHeading(var6, var8 + var20, var10, 1.5F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
/*     */   {
/*  68 */     float var9 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/*  69 */     par1 /= var9;
/*  70 */     par3 /= var9;
/*  71 */     par5 /= var9;
/*  72 */     par1 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
/*  73 */     par3 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
/*  74 */     par5 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
/*  75 */     par1 *= par7;
/*  76 */     par3 *= par7;
/*  77 */     par5 *= par7;
/*  78 */     this.field_70159_w = par1;
/*  79 */     this.field_70181_x = par3;
/*  80 */     this.field_70179_y = par5;
/*  81 */     float var10 = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
/*  82 */     this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0D / 3.141592653589793D));
/*  83 */     this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(par3, var10) * 180.0D / 3.141592653589793D));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  89 */     super.func_70088_a();
/*  90 */     this.field_70180_af.func_75682_a(16, new Byte((byte)1));
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSlimeSize(int par1)
/*     */   {
/*  96 */     this.field_70180_af.func_75692_b(16, new Byte((byte)par1));
/*  97 */     float ss = (float)Math.sqrt(par1);
/*  98 */     func_70105_a(0.25F * ss + 0.25F, 0.25F * ss + 0.25F);
/*  99 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 100 */     func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111267_a).func_111128_a(par1);
/* 101 */     func_70606_j(func_110138_aP());
/* 102 */     this.field_70728_aV = ((int)ss);
/*     */   }
/*     */   
/*     */ 
/*     */   protected int getAttackStrength()
/*     */   {
/* 108 */     return getSlimeSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSlimeSize()
/*     */   {
/* 117 */     return this.field_70180_af.func_75683_a(16);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 126 */     super.func_70014_b(par1NBTTagCompound);
/* 127 */     par1NBTTagCompound.func_74768_a("Size", getSlimeSize() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 136 */     super.func_70037_a(par1NBTTagCompound);
/* 137 */     setSlimeSize(par1NBTTagCompound.func_74762_e("Size") + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String getSlimeParticle()
/*     */   {
/* 145 */     return "slime";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String getJumpSound()
/*     */   {
/* 153 */     return "mob.slime." + (getSlimeSize() > 3 ? "big" : "small");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 164 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70170_p.field_73013_u.func_151525_a() == 0) && (getSlimeSize() > 0))
/*     */     {
/* 166 */       this.field_70128_L = true;
/*     */     }
/*     */     
/* 169 */     this.field_70811_b += (this.field_70813_a - this.field_70811_b) * 0.5F;
/* 170 */     this.field_70812_c = this.field_70811_b;
/* 171 */     boolean flag = this.field_70122_E;
/* 172 */     super.func_70071_h_();
/* 173 */     int i = (int)Math.sqrt(getSlimeSize());
/*     */     
/* 175 */     if (this.launched > 0) {
/* 176 */       this.launched -= 1;
/* 177 */       if (this.field_70170_p.field_72995_K) {
/* 178 */         for (int j = 0; j < i * (this.launched + 1); j++)
/*     */         {
/* 180 */           Thaumcraft.proxy.slimeJumpFX(this, i);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 186 */     if ((this.field_70122_E) && (!flag))
/*     */     {
/*     */ 
/* 189 */       if (this.field_70170_p.field_72995_K) {
/* 190 */         for (int j = 0; j < i * 8; j++)
/*     */         {
/* 192 */           Thaumcraft.proxy.slimeJumpFX(this, i);
/*     */         }
/*     */       }
/* 195 */       if (makesSoundOnLand())
/*     */       {
/* 197 */         func_85030_a(getJumpSound(), func_70599_aP(), ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) / 0.8F);
/*     */       }
/*     */       
/* 200 */       this.field_70813_a = -0.5F;
/*     */     }
/* 202 */     else if ((!this.field_70122_E) && (flag))
/*     */     {
/* 204 */       this.field_70813_a = 1.0F;
/*     */     }
/*     */     
/* 207 */     func_70808_l();
/*     */     
/* 209 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/* 211 */       float ff = (float)Math.sqrt(getSlimeSize());
/* 212 */       func_70105_a(0.6F * ff, 0.6F * ff);
/*     */     }
/*     */   }
/*     */   
/* 216 */   int spitCounter = 100;
/*     */   
/*     */   protected EntityThaumicSlime getClosestMergableSlime() {
/* 219 */     EntityThaumicSlime closest = null;
/* 220 */     double distance = Double.MAX_VALUE;
/* 221 */     List ents = this.field_70170_p.func_72872_a(EntityThaumicSlime.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t, this.field_70163_u, this.field_70161_v).func_72314_b(16.0D, 8.0D, 16.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 226 */     if ((ents != null) && (ents.size() > 0)) {
/* 227 */       for (Object s : ents) {
/* 228 */         EntityThaumicSlime slime = (EntityThaumicSlime)s;
/* 229 */         if ((slime.func_145782_y() != func_145782_y()) && (slime.field_70173_aa > 100) && (slime.getSlimeSize() < 100) && (func_70068_e(slime) < distance))
/*     */         {
/*     */ 
/*     */ 
/* 233 */           closest = slime; }
/* 234 */         distance = func_70068_e(slime);
/*     */       }
/*     */     }
/* 237 */     return closest;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70626_be()
/*     */   {
/* 244 */     func_70623_bb();
/* 245 */     EntityPlayer entityplayer = this.field_70170_p.func_72856_b(this, 16.0D);
/*     */     
/* 247 */     if (entityplayer != null)
/*     */     {
/* 249 */       if (this.spitCounter > 0) this.spitCounter -= 1;
/* 250 */       func_70625_a(entityplayer, 10.0F, 20.0F);
/* 251 */       if ((func_70032_d(entityplayer) > 4.0F) && (this.spitCounter <= 0) && (getSlimeSize() > 3)) {
/* 252 */         this.spitCounter = 101;
/*     */         
/* 254 */         if (!this.field_70170_p.field_72995_K) {
/* 255 */           EntityThaumicSlime flyslime = new EntityThaumicSlime(this.field_70170_p, this, entityplayer);
/* 256 */           this.field_70170_p.func_72838_d(flyslime);
/*     */         }
/* 258 */         this.field_70170_p.func_72956_a(this, "thaumcraft:gore", 1.0F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/* 259 */         setSlimeSize(getSlimeSize() - 1);
/*     */       }
/*     */     } else {
/* 262 */       EntityThaumicSlime slime = getClosestMergableSlime();
/* 263 */       if (slime != null) {
/* 264 */         func_70625_a(slime, 10.0F, 20.0F);
/* 265 */         if (func_70032_d(slime) < this.field_70130_N + slime.field_70130_N)
/*     */         {
/* 267 */           slime.setSlimeSize(Math.min(100, slime.getSlimeSize() + getSlimeSize()));
/* 268 */           func_70106_y();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 273 */     if ((this.field_70122_E) && (this.slimeJumpDelay-- <= 0))
/*     */     {
/* 275 */       this.slimeJumpDelay = getJumpDelay();
/*     */       
/* 277 */       if (entityplayer != null)
/*     */       {
/* 279 */         this.slimeJumpDelay /= 3;
/*     */       }
/*     */       
/* 282 */       this.field_70703_bu = true;
/*     */       
/* 284 */       if (makesSoundOnJump())
/*     */       {
/* 286 */         func_85030_a(getJumpSound(), func_70599_aP(), ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/*     */       }
/*     */       
/* 289 */       this.field_70702_br = (1.0F - this.field_70146_Z.nextFloat() * 2.0F);
/* 290 */       this.field_70701_bs = ((float)(1.0D * Math.sqrt(getSlimeSize())));
/*     */     }
/*     */     else
/*     */     {
/* 294 */       this.field_70703_bu = false;
/*     */       
/* 296 */       if (this.field_70122_E)
/*     */       {
/* 298 */         this.field_70702_br = (this.field_70701_bs = 0.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70808_l()
/*     */   {
/* 305 */     this.field_70813_a *= 0.6F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int getJumpDelay()
/*     */   {
/* 313 */     return this.field_70146_Z.nextInt(16) + 8;
/*     */   }
/*     */   
/*     */   protected EntityThaumicSlime createInstance()
/*     */   {
/* 318 */     return new EntityThaumicSlime(this.field_70170_p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70106_y()
/*     */   {
/* 327 */     int i = (int)Math.sqrt(getSlimeSize());
/*     */     
/* 329 */     if ((!this.field_70170_p.field_72995_K) && (i > 1) && (func_110143_aJ() <= 0.0F))
/*     */     {
/*     */ 
/* 332 */       for (int k = 0; k < i; k++)
/*     */       {
/* 334 */         float f = (k % 2 - 0.5F) * i / 4.0F;
/* 335 */         float f1 = (k / 2 - 0.5F) * i / 4.0F;
/* 336 */         EntityThaumicSlime entityslime = createInstance();
/* 337 */         entityslime.setSlimeSize(1);
/* 338 */         entityslime.func_70012_b(this.field_70165_t + f, this.field_70163_u + 0.5D, this.field_70161_v + f1, this.field_70146_Z.nextFloat() * 360.0F, 0.0F);
/* 339 */         this.field_70170_p.func_72838_d(entityslime);
/*     */       }
/*     */     }
/*     */     
/* 343 */     super.func_70106_y();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70100_b_(EntityPlayer par1EntityPlayer)
/*     */   {
/* 352 */     if (canDamagePlayer())
/*     */     {
/* 354 */       int i = (int)Math.max(1.0D, Math.sqrt(getSlimeSize()));
/* 355 */       if ((this.launched > 0) && (i == 2)) i = 3;
/* 356 */       if ((func_70685_l(par1EntityPlayer)) && (func_70068_e(par1EntityPlayer) < 0.8D * i * 0.8D * i) && (par1EntityPlayer.func_70097_a(DamageSource.func_76358_a(this), getAttackStrength())))
/*     */       {
/*     */ 
/* 359 */         func_85030_a("mob.attack", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean canDamagePlayer()
/*     */   {
/* 370 */     return getSlimeSize() > 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 379 */     return "mob.slime." + (getSlimeSize() > 3 ? "big" : "small");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 387 */     return "mob.slime." + (getSlimeSize() > 3 ? "big" : "small");
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 393 */     return getSlimeSize() < 3 ? ConfigItems.itemResource : Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 398 */     if ((getSlimeSize() < 3) && (this.field_70146_Z.nextInt(3) == 0)) {
/* 399 */       func_70099_a(new net.minecraft.item.ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 408 */     return 0.1F * (float)Math.sqrt(getSlimeSize());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70646_bf()
/*     */   {
/* 418 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean makesSoundOnJump()
/*     */   {
/* 426 */     return getSlimeSize() > 3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean makesSoundOnLand()
/*     */   {
/* 434 */     return getSlimeSize() > 5;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityThaumicSlime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */