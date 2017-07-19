/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityFlying;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.items.ItemWispEssence;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXWispZap;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class EntityWisp extends EntityFlying implements IMob
/*     */ {
/*     */   public int courseChangeCooldown;
/*     */   public double waypointX;
/*     */   public double waypointY;
/*     */   public double waypointZ;
/*     */   private Entity targetedEntity;
/*     */   private int aggroCooldown;
/*     */   public int prevAttackCounter;
/*     */   public int attackCounter;
/*     */   
/*     */   public EntityWisp(World world)
/*     */   {
/*  48 */     super(world);
/*  49 */     this.courseChangeCooldown = 0;
/*  50 */     this.targetedEntity = null;
/*  51 */     this.aggroCooldown = 0;
/*  52 */     this.prevAttackCounter = 0;
/*  53 */     this.attackCounter = 0;
/*  54 */     func_70105_a(0.9F, 0.9F);
/*  55 */     this.field_70728_aV = 5;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  61 */     super.func_110147_ax();
/*  62 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(22.0D);
/*  63 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/*  64 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
/*     */   }
/*     */   
/*     */   protected boolean func_70041_e_()
/*     */   {
/*  69 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70682_h(int par1)
/*     */   {
/*  75 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource damagesource, float i)
/*     */   {
/*  81 */     if ((damagesource.func_76364_f() instanceof EntityLivingBase)) {
/*  82 */       this.targetedEntity = ((EntityLivingBase)damagesource.func_76364_f());
/*  83 */       this.aggroCooldown = 200;
/*     */     }
/*  85 */     if ((damagesource.func_76346_g() instanceof EntityLivingBase)) {
/*  86 */       this.targetedEntity = ((EntityLivingBase)damagesource.func_76346_g());
/*  87 */       this.aggroCooldown = 200;
/*     */     }
/*  89 */     return super.func_70097_a(damagesource, i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  96 */     super.func_70088_a();
/*  97 */     this.field_70180_af.func_75682_a(22, String.valueOf(""));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource par1DamageSource)
/*     */   {
/* 103 */     super.func_70645_a(par1DamageSource);
/* 104 */     if (this.field_70170_p.field_72995_K) {
/* 105 */       Thaumcraft.proxy.burst(this.field_70170_p, this.field_70165_t, this.field_70163_u + 0.44999998807907104D, this.field_70161_v, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 112 */     super.func_70071_h_();
/* 113 */     if ((this.field_70170_p.field_72995_K) && 
/* 114 */       (this.field_70173_aa <= 1)) {
/* 115 */       Thaumcraft.proxy.burst(this.field_70170_p, this.field_70165_t, this.field_70163_u + 0.44999998807907104D, this.field_70161_v, 1.0F);
/*     */     }
/*     */     
/* 118 */     if ((this.field_70170_p.field_72995_K) && (this.field_70170_p.field_73012_v.nextBoolean()) && (Aspect.getAspect(getType()) != null)) {
/* 119 */       Color color = new Color(Aspect.getAspect(getType()).getColor());
/* 120 */       Thaumcraft.proxy.wispFX(this.field_70170_p, this.field_70165_t + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7F, this.field_70163_u + 0.44999998807907104D + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7F, this.field_70161_v + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7F, 0.1F, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getType()
/*     */   {
/* 129 */     return this.field_70180_af.func_75681_e(22);
/*     */   }
/*     */   
/*     */   public void setType(String t) {
/* 133 */     this.field_70180_af.func_75692_b(22, String.valueOf(t));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70626_be()
/*     */   {
/* 139 */     if ((!this.field_70170_p.field_72995_K) && (Aspect.getAspect(getType()) == null)) {
/* 140 */       BiomeGenBase bg = this.field_70170_p.func_72807_a(MathHelper.func_76143_f(this.field_70165_t), MathHelper.func_76143_f(this.field_70161_v));
/* 141 */       if (bg.field_76756_M == ThaumcraftWorldGenerator.biomeEerie.field_76756_M) {
/* 142 */         switch (this.field_70146_Z.nextInt(6)) {
/* 143 */         case 0:  setType(Aspect.DARKNESS.getTag()); break;
/* 144 */         case 1:  setType(Aspect.UNDEAD.getTag()); break;
/* 145 */         case 2:  setType(Aspect.ENTROPY.getTag()); break;
/* 146 */         case 3:  setType(Aspect.ELDRITCH.getTag()); break;
/* 147 */         case 4:  setType(Aspect.POISON.getTag()); break;
/* 148 */         case 5:  setType(Aspect.DEATH.getTag());
/*     */         }
/*     */       }
/* 151 */       else if (this.field_70170_p.field_73012_v.nextInt(10) != 0) {
/* 152 */         ArrayList<Aspect> as = Aspect.getPrimalAspects();
/* 153 */         setType(((Aspect)as.get(this.field_70170_p.field_73012_v.nextInt(as.size()))).getTag());
/*     */       } else {
/* 155 */         ArrayList<Aspect> as = Aspect.getCompoundAspects();
/* 156 */         setType(((Aspect)as.get(this.field_70170_p.field_73012_v.nextInt(as.size()))).getTag());
/*     */       }
/*     */     }
/*     */     
/* 160 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70170_p.field_73013_u.func_151525_a() == 0))
/*     */     {
/* 162 */       func_70106_y();
/*     */     }
/*     */     
/*     */ 
/* 166 */     func_70623_bb();
/*     */     
/*     */ 
/*     */ 
/* 170 */     this.prevAttackCounter = this.attackCounter;
/*     */     
/* 172 */     double attackrange = 16.0D;
/*     */     
/* 174 */     double d = this.waypointX - this.field_70165_t;
/* 175 */     double d1 = this.waypointY - this.field_70163_u;
/* 176 */     double d2 = this.waypointZ - this.field_70161_v;
/* 177 */     double d3 = d * d + d1 * d1 + d2 * d2;
/*     */     
/* 179 */     if ((d3 < 1.0D) || (d3 > 3600.0D))
/*     */     {
/* 181 */       this.waypointX = (this.field_70165_t + (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0D);
/* 182 */       this.waypointY = (this.field_70163_u + (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0D);
/* 183 */       this.waypointZ = (this.field_70161_v + (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0D);
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
/* 200 */     if (this.courseChangeCooldown-- <= 0)
/*     */     {
/* 202 */       this.courseChangeCooldown += this.field_70146_Z.nextInt(5) + 2;
/* 203 */       d3 = MathHelper.func_76133_a(d3);
/* 204 */       if (isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
/*     */       {
/* 206 */         this.field_70159_w += d / d3 * 0.1D;
/* 207 */         this.field_70181_x += d1 / d3 * 0.1D;
/* 208 */         this.field_70179_y += d2 / d3 * 0.1D;
/*     */       }
/*     */       else {
/* 211 */         this.waypointX = this.field_70165_t;
/* 212 */         this.waypointY = this.field_70163_u;
/* 213 */         this.waypointZ = this.field_70161_v;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 218 */     if ((this.targetedEntity != null) && (this.targetedEntity.field_70128_L))
/*     */     {
/* 220 */       this.targetedEntity = null;
/*     */     }
/*     */     
/* 223 */     this.aggroCooldown -= 1;
/*     */     
/*     */ 
/* 226 */     if ((this.field_70170_p.field_73012_v.nextInt(1000) == 0) && ((this.targetedEntity == null) || (this.aggroCooldown-- <= 0)))
/*     */     {
/*     */ 
/* 229 */       this.targetedEntity = this.field_70170_p.func_72856_b(this, 16.0D);
/* 230 */       if (this.targetedEntity != null)
/*     */       {
/* 232 */         this.aggroCooldown = 50;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 237 */     if ((this.targetedEntity != null) && (this.targetedEntity.func_70068_e(this) < attackrange * attackrange))
/*     */     {
/* 239 */       double d5 = this.targetedEntity.field_70165_t - this.field_70165_t;
/* 240 */       double d6 = this.targetedEntity.field_70121_D.field_72338_b + this.targetedEntity.field_70131_O / 2.0F - (this.field_70163_u + this.field_70131_O / 2.0F);
/* 241 */       double d7 = this.targetedEntity.field_70161_v - this.field_70161_v;
/* 242 */       this.field_70761_aq = (this.field_70177_z = -(float)Math.atan2(d5, d7) * 180.0F / 3.141593F);
/* 243 */       if (func_70685_l(this.targetedEntity))
/*     */       {
/* 245 */         this.attackCounter += 1;
/* 246 */         if (this.attackCounter == 20)
/*     */         {
/* 248 */           this.field_70170_p.func_72956_a(this, "thaumcraft:zap", 1.0F, 1.1F);
/*     */           
/* 250 */           thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new PacketFXWispZap(func_145782_y(), this.targetedEntity.func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */           
/*     */ 
/*     */ 
/* 254 */           float damage = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/*     */           
/* 256 */           if ((Math.abs(this.targetedEntity.field_70159_w) > 0.10000000149011612D) || (Math.abs(this.targetedEntity.field_70181_x) > 0.10000000149011612D) || (Math.abs(this.targetedEntity.field_70179_y) > 0.10000000149011612D))
/*     */           {
/*     */ 
/* 259 */             if (this.field_70170_p.field_73012_v.nextFloat() < 0.4F) {
/* 260 */               this.targetedEntity.func_70097_a(DamageSource.func_76358_a(this), damage);
/*     */             }
/*     */           }
/* 263 */           else if (this.field_70170_p.field_73012_v.nextFloat() < 0.66F) {
/* 264 */             this.targetedEntity.func_70097_a(DamageSource.func_76358_a(this), damage + 1.0F);
/*     */           }
/* 266 */           this.attackCounter = (-20 + this.field_70170_p.field_73012_v.nextInt(20));
/*     */         }
/*     */       }
/* 269 */       else if (this.attackCounter > 0)
/*     */       {
/* 271 */         this.attackCounter -= 1;
/*     */       }
/*     */     }
/*     */     else {
/* 275 */       this.field_70761_aq = (this.field_70177_z = -(float)Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0F / 3.141593F);
/* 276 */       if (this.attackCounter > 0)
/*     */       {
/* 278 */         this.attackCounter -= 1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean isCourseTraversable(double d, double d1, double d2, double d3)
/*     */   {
/* 286 */     double d4 = (this.waypointX - this.field_70165_t) / d3;
/* 287 */     double d5 = (this.waypointY - this.field_70163_u) / d3;
/* 288 */     double d6 = (this.waypointZ - this.field_70161_v) / d3;
/* 289 */     AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
/* 290 */     for (int i = 1; i < d3; i++)
/*     */     {
/* 292 */       axisalignedbb.func_72317_d(d4, d5, d6);
/* 293 */       if (!this.field_70170_p.func_72945_a(this, axisalignedbb).isEmpty())
/*     */       {
/* 295 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 299 */     int x = (int)this.waypointX;
/* 300 */     int y = (int)this.waypointY;
/* 301 */     int z = (int)this.waypointZ;
/*     */     
/* 303 */     if (this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76224_d()) { return false;
/*     */     }
/* 305 */     for (int a = 0; a < 11; a++) if (!this.field_70170_p.func_147437_c(x, y - a, z)) { return true;
/*     */       }
/* 307 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 313 */     return "thaumcraft:wisplive";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 319 */     return "random.fizz";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 325 */     return "thaumcraft:wispdead";
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 331 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 337 */     if (Aspect.getAspect(getType()) != null) {
/* 338 */       ItemStack ess = new ItemStack(thaumcraft.common.config.ConfigItems.itemWispEssence);
/* 339 */       AspectList al = new AspectList();
/* 340 */       ((ItemWispEssence)ess.func_77973_b()).setAspects(ess, new AspectList().add(Aspect.getAspect(getType()), 2));
/* 341 */       func_70099_a(ess, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 349 */     return 0.25F;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 354 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 360 */     int count = 0;
/*     */     try {
/* 362 */       List l = this.field_70170_p.func_72872_a(EntityWisp.class, this.field_70121_D.func_72314_b(16.0D, 16.0D, 16.0D));
/* 363 */       if (l != null) count = l.size();
/*     */     }
/*     */     catch (Exception e) {}
/* 366 */     return (count < 8) && (this.field_70170_p.field_73013_u.func_151525_a() > 0) && (isValidLightLevel()) && (super.func_70601_bi());
/*     */   }
/*     */   
/*     */   protected boolean isValidLightLevel()
/*     */   {
/* 371 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 372 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 373 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 375 */     if (this.field_70170_p.func_72972_b(net.minecraft.world.EnumSkyBlock.Sky, i, j, k) > this.field_70146_Z.nextInt(32))
/*     */     {
/* 377 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 381 */     int l = this.field_70170_p.func_72957_l(i, j, k);
/*     */     
/* 383 */     if (this.field_70170_p.func_72911_I())
/*     */     {
/* 385 */       int i1 = this.field_70170_p.field_73008_k;
/* 386 */       this.field_70170_p.field_73008_k = 10;
/* 387 */       l = this.field_70170_p.func_72957_l(i, j, k);
/* 388 */       this.field_70170_p.field_73008_k = i1;
/*     */     }
/*     */     
/* 391 */     return l <= this.field_70146_Z.nextInt(8);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbttagcompound)
/*     */   {
/* 398 */     super.func_70014_b(nbttagcompound);
/* 399 */     nbttagcompound.func_74778_a("Type", getType());
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbttagcompound)
/*     */   {
/* 405 */     super.func_70037_a(nbttagcompound);
/* 406 */     setType(nbttagcompound.func_74779_i("Type"));
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70641_bl()
/*     */   {
/* 412 */     return 2;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityWisp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */