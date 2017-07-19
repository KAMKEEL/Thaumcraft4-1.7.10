/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.projectile.EntityEldritchOrb;
/*     */ import thaumcraft.common.items.ItemWispEssence;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXSonic;
/*     */ 
/*     */ public class EntityEldritchGuardian extends EntityMob implements IRangedAttackMob, IEldritchMob
/*     */ {
/*     */   public EntityEldritchGuardian(World p_i1745_1_)
/*     */   {
/*  52 */     super(p_i1745_1_);
/*  53 */     func_70661_as().func_75498_b(true);
/*  54 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  55 */     this.field_70714_bg.func_75776_a(2, new thaumcraft.common.entities.ai.combat.AILongRangeAttack(this, 8.0D, 1.0D, 20, 40, 24.0F));
/*  56 */     this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
/*  57 */     this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*  58 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
/*  59 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  60 */     this.field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  61 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  62 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  63 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityCultist.class, 0, true));
/*  64 */     func_70105_a(0.8F, 2.25F);
/*  65 */     this.field_70728_aV = 20;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  71 */     super.func_110147_ax();
/*  72 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/*  73 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
/*  74 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28D);
/*  75 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  81 */     super.func_70088_a();
/*  82 */     func_70096_w().func_75682_a(12, Byte.valueOf((byte)0));
/*  83 */     func_70096_w().func_75682_a(13, Byte.valueOf((byte)0));
/*  84 */     func_70096_w().func_75682_a(14, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  93 */     return 4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70650_aV()
/*     */   {
/* 102 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_98052_bS()
/*     */   {
/* 107 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 116 */     if (source.func_82725_o()) {
/* 117 */       damage /= 2.0F;
/*     */     }
/* 119 */     return super.func_70097_a(source, damage);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 129 */     super.func_70071_h_();
/*     */     
/* 131 */     if (this.field_70170_p.field_72995_K) {
/* 132 */       if (this.armLiftL > 0.0F) this.armLiftL -= 0.05F;
/* 133 */       if (this.armLiftR > 0.0F) this.armLiftR -= 0.05F;
/* 134 */       float x = (float)(this.field_70165_t + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/* 135 */       float z = (float)(this.field_70161_v + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/* 136 */       Thaumcraft.proxy.wispFXEG(this.field_70170_p, x, (float)(this.field_70163_u + 0.22D * this.field_70131_O), z, this);
/*     */ 
/*     */     }
/* 139 */     else if (this.field_70170_p.field_73011_w.field_76574_g != Config.dimensionOuterId)
/*     */     {
/* 141 */       if (((this.field_70173_aa == 0) || (this.field_70173_aa % 100 == 0)) && (this.field_70170_p.field_73013_u != EnumDifficulty.EASY))
/*     */       {
/* 143 */         double d6 = this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 576.0D : 256.0D;
/* 144 */         for (int i = 0; i < this.field_70170_p.field_73010_i.size(); i++)
/*     */         {
/* 146 */           EntityPlayer entityplayer1 = (EntityPlayer)this.field_70170_p.field_73010_i.get(i);
/*     */           
/* 148 */           if (entityplayer1.func_70089_S())
/*     */           {
/* 150 */             double d5 = entityplayer1.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */             
/* 152 */             if (d5 < d6)
/*     */             {
/* 154 */               PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.misc.PacketMiscEvent((short)2), (net.minecraft.entity.player.EntityPlayerMP)entityplayer1);
/*     */             }
/*     */           }
/*     */         }
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
/*     */   public boolean func_70652_k(Entity p_70652_1_)
/*     */   {
/* 171 */     boolean flag = super.func_70652_k(p_70652_1_);
/*     */     
/* 173 */     if (flag)
/*     */     {
/* 175 */       int i = this.field_70170_p.field_73013_u.func_151525_a();
/*     */       
/* 177 */       if ((func_70694_bm() == null) && (func_70027_ad()) && (this.field_70146_Z.nextFloat() < i * 0.3F))
/*     */       {
/* 179 */         p_70652_1_.func_70015_d(2 * i);
/*     */       }
/*     */     }
/*     */     
/* 183 */     return flag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 192 */     return "thaumcraft:egidle";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 202 */     return "thaumcraft:egdeath";
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 208 */     return 500;
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 214 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 220 */     if (this.field_70146_Z.nextBoolean()) {
/* 221 */       ItemStack ess = new ItemStack(ConfigItems.itemWispEssence);
/* 222 */       AspectList al = new AspectList();
/* 223 */       ((ItemWispEssence)ess.func_77973_b()).setAspects(ess, new AspectList().add(Aspect.UNDEAD, 2));
/* 224 */       func_70099_a(ess, 1.0F);
/*     */     }
/*     */     
/* 227 */     if (this.field_70146_Z.nextBoolean()) {
/* 228 */       ItemStack ess = new ItemStack(ConfigItems.itemWispEssence);
/* 229 */       AspectList al = new AspectList();
/* 230 */       ((ItemWispEssence)ess.func_77973_b()).setAspects(ess, new AspectList().add(Aspect.ELDRITCH, 2));
/* 231 */       func_70099_a(ess, 1.0F);
/*     */     }
/*     */     
/* 234 */     super.func_70628_a(flag, i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EnumCreatureAttribute func_70668_bt()
/*     */   {
/* 243 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70600_l(int p_70600_1_)
/*     */   {
/* 249 */     func_145779_a(ConfigItems.itemEldritchObject, 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound p_70014_1_)
/*     */   {
/* 259 */     super.func_70014_b(p_70014_1_);
/* 260 */     if ((func_110172_bL() != null) && (func_110174_bM() > 0.0F)) {
/* 261 */       p_70014_1_.func_74768_a("HomeD", (int)func_110174_bM());
/* 262 */       p_70014_1_.func_74768_a("HomeX", func_110172_bL().field_71574_a);
/* 263 */       p_70014_1_.func_74768_a("HomeY", func_110172_bL().field_71572_b);
/* 264 */       p_70014_1_.func_74768_a("HomeZ", func_110172_bL().field_71573_c);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound p_70037_1_)
/*     */   {
/* 275 */     super.func_70037_a(p_70037_1_);
/* 276 */     if (p_70037_1_.func_74764_b("HomeD")) {
/* 277 */       func_110171_b(p_70037_1_.func_74762_e("HomeX"), p_70037_1_.func_74762_e("HomeY"), p_70037_1_.func_74762_e("HomeZ"), p_70037_1_.func_74762_e("HomeD"));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
/*     */   {
/* 289 */     Object p_110161_1_1 = super.func_110161_a(p_110161_1_);
/* 290 */     float f = this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 291 */     if (this.field_70170_p.field_73011_w.field_76574_g == Config.dimensionOuterId) {
/* 292 */       int bh = (int)func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
/* 293 */       func_110149_m(func_110139_bj() + bh);
/*     */     }
/* 295 */     return (IEntityLivingData)p_110161_1_1;
/*     */   }
/*     */   
/*     */   protected void func_70619_bc()
/*     */   {
/* 300 */     super.func_70619_bc();
/* 301 */     if ((this.field_70170_p.field_73011_w.field_76574_g == Config.dimensionOuterId) && (this.field_70172_ad <= 0) && (this.field_70173_aa % 25 == 0)) {
/* 302 */       int bh = (int)func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
/* 303 */       if (func_110139_bj() < bh)
/* 304 */         func_110149_m(func_110139_bj() + 1.0F);
/*     */     }
/*     */   }
/*     */   
/* 308 */   public float armLiftL = 0.0F;
/* 309 */   public float armLiftR = 0.0F;
/*     */   
/*     */ 
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_70103_a(byte p_70103_1_)
/*     */   {
/* 315 */     if (p_70103_1_ == 15)
/*     */     {
/* 317 */       this.armLiftL = 0.5F;
/*     */ 
/*     */     }
/* 320 */     else if (p_70103_1_ == 16)
/*     */     {
/* 322 */       this.armLiftR = 0.5F;
/*     */     }
/* 324 */     else if (p_70103_1_ == 17)
/*     */     {
/* 326 */       this.armLiftL = 0.9F;
/* 327 */       this.armLiftR = 0.9F;
/*     */     }
/*     */     else
/*     */     {
/* 331 */       super.func_70103_a(p_70103_1_);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 341 */     return !func_110175_bO();
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 347 */     return 2.1F;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 353 */     List ents = this.field_70170_p.func_72872_a(EntityEldritchGuardian.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(32.0D, 16.0D, 32.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 359 */     return ents.size() > 0 ? false : super.func_70601_bi();
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70814_o()
/*     */   {
/* 365 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 371 */     return 1.5F;
/*     */   }
/*     */   
/* 374 */   boolean lastBlast = false;
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f)
/*     */   {
/* 378 */     if (this.field_70146_Z.nextFloat() > 0.1F) {
/* 379 */       EntityEldritchOrb blast = new EntityEldritchOrb(this.field_70170_p, this);
/* 380 */       this.lastBlast = (!this.lastBlast);
/*     */       
/* 382 */       this.field_70170_p.func_72960_a(this, (byte)(this.lastBlast ? 16 : 15));
/*     */       
/* 384 */       int rr = this.lastBlast ? 90 : 180;
/* 385 */       double xx = MathHelper.func_76134_b((this.field_70177_z + rr) % 360.0F / 180.0F * 3.1415927F) * 0.5F;
/* 386 */       double yy = 0.057777777D * this.field_70131_O;
/* 387 */       double zz = MathHelper.func_76126_a((this.field_70177_z + rr) % 360.0F / 180.0F * 3.1415927F) * 0.5F;
/* 388 */       blast.func_70107_b(blast.field_70165_t - xx, blast.field_70163_u - yy, blast.field_70161_v - zz);
/*     */       
/* 390 */       double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
/* 391 */       double d1 = entitylivingbase.field_70163_u - this.field_70163_u - entitylivingbase.field_70131_O / 2.0F;
/* 392 */       double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
/*     */       
/* 394 */       blast.func_70186_c(d0, d1, d2, 1.0F, 2.0F);
/*     */       
/* 396 */       func_85030_a("thaumcraft:egattack", 2.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 397 */       this.field_70170_p.func_72838_d(blast);
/*     */     }
/* 399 */     else if (func_70685_l(entitylivingbase)) {
/* 400 */       PacketHandler.INSTANCE.sendToAllAround(new PacketFXSonic(func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */       
/*     */       try
/*     */       {
/* 404 */         entitylivingbase.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 400, 0));
/*     */       }
/*     */       catch (Exception e) {}
/*     */       
/* 408 */       if ((entitylivingbase instanceof EntityPlayer)) {
/* 409 */         Thaumcraft.addWarpToPlayer((EntityPlayer)entitylivingbase, 1 + this.field_70170_p.field_73012_v.nextInt(3), true);
/*     */       }
/*     */       
/* 412 */       func_85030_a("thaumcraft:egscreech", 3.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(EntityLivingBase el)
/*     */   {
/* 419 */     return el instanceof IEldritchMob;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityEldritchGuardian.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */