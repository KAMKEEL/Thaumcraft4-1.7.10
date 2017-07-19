/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityEldritchGolem extends EntityThaumcraftBoss implements IEldritchMob, IRangedAttackMob
/*     */ {
/*     */   public EntityEldritchGolem(World p_i1745_1_)
/*     */   {
/*  39 */     super(p_i1745_1_);
/*  40 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  41 */     this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.1D, false));
/*  42 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*  43 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*  44 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  45 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  46 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
/*  47 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */     
/*  49 */     func_70105_a(1.75F, 3.5F);
/*  50 */     this.field_70178_ae = true;
/*     */   }
/*     */   
/*     */   public void generateName()
/*     */   {
/*  55 */     int t = (int)func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
/*  56 */     if (t >= 0) {
/*  57 */       func_94058_c(String.format(net.minecraft.util.StatCollector.func_74838_a("entity.Thaumcraft.EldritchGolem.name"), new Object[] { thaumcraft.common.entities.monster.mods.ChampionModifier.mods[t].getModNameLocalized() }));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  66 */     super.func_70088_a();
/*  67 */     func_70096_w().func_75682_a(12, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public boolean isHeadless() {
/*  71 */     return func_70096_w().func_75683_a(12) == 1;
/*     */   }
/*     */   
/*     */   public void setHeadless(boolean par1)
/*     */   {
/*  76 */     this.field_70180_af.func_75692_b(12, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/*  82 */     super.func_70014_b(nbt);
/*  83 */     nbt.func_74757_a("headless", isHeadless());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/*  92 */     super.func_70037_a(nbt);
/*  93 */     setHeadless(nbt.func_74767_n("headless"));
/*  94 */     if (isHeadless()) {
/*  95 */       makeHeadless();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 104 */     return isHeadless() ? 3.33F : 3.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/* 110 */     return super.func_70658_aO() + 6;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 116 */     super.func_110147_ax();
/* 117 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/* 118 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(10.0D);
/* 119 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(250.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 125 */     return "mob.irongolem.hit";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 131 */     return "mob.irongolem.death";
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
/*     */   {
/* 137 */     func_85030_a("mob.irongolem.walk", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
/*     */   {
/* 143 */     this.spawnTimer = 100;
/* 144 */     return super.func_110161_a(p_110161_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 150 */     super.func_70636_d();
/*     */     
/* 152 */     if (this.attackTimer > 0)
/*     */     {
/* 154 */       this.attackTimer -= 1;
/*     */     }
/*     */     
/* 157 */     if ((this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 2.500000277905201E-7D) && (this.field_70146_Z.nextInt(5) == 0))
/*     */     {
/* 159 */       int i = MathHelper.func_76128_c(this.field_70165_t);
/* 160 */       int j = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D - this.field_70129_M);
/* 161 */       int k = MathHelper.func_76128_c(this.field_70161_v);
/* 162 */       Block block = this.field_70170_p.func_147439_a(i, j, k);
/*     */       
/* 164 */       if (block.func_149688_o() != Material.field_151579_a)
/*     */       {
/* 166 */         this.field_70170_p.func_72869_a("blockcrack_" + Block.func_149682_b(block) + "_" + this.field_70170_p.func_72805_g(i, j, k), this.field_70165_t + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, this.field_70121_D.field_72338_b + 0.1D, this.field_70161_v + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, 4.0D * (this.field_70146_Z.nextFloat() - 0.5D), 0.5D, (this.field_70146_Z.nextFloat() - 0.5D) * 4.0D);
/*     */       }
/*     */       
/* 169 */       if ((!this.field_70170_p.field_72995_K) && 
/* 170 */         ((block instanceof thaumcraft.common.blocks.BlockLoot))) {
/* 171 */         this.field_70170_p.func_147480_a(i, j, k, true);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 176 */     if (!this.field_70170_p.field_72995_K) {
/* 177 */       int i = MathHelper.func_76128_c(this.field_70165_t + this.field_70159_w);
/* 178 */       int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 179 */       int k = MathHelper.func_76128_c(this.field_70161_v + this.field_70179_y);
/* 180 */       Block block = this.field_70170_p.func_147439_a(i, j, k);
/* 181 */       float h = block.func_149712_f(this.field_70170_p, i, j, k);
/* 182 */       if ((h >= 0.0F) && (h <= 0.15F)) {
/* 183 */         this.field_70170_p.func_147480_a(i, j, k, true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 191 */     if ((!this.field_70170_p.field_72995_K) && (damage > func_110143_aJ()) && (!isHeadless())) {
/* 192 */       setHeadless(true);
/* 193 */       this.spawnTimer = 100;
/* 194 */       double xx = MathHelper.func_76134_b(this.field_70177_z % 360.0F / 180.0F * 3.1415927F) * 0.75F;
/* 195 */       double zz = MathHelper.func_76126_a(this.field_70177_z % 360.0F / 180.0F * 3.1415927F) * 0.75F;
/* 196 */       this.field_70170_p.func_72876_a(this, this.field_70165_t + xx, this.field_70163_u + func_70047_e(), this.field_70161_v + zz, 2.0F, false);
/* 197 */       makeHeadless();
/* 198 */       return false;
/*     */     }
/* 200 */     return super.func_70097_a(source, damage);
/*     */   }
/*     */   
/*     */   void makeHeadless() {
/* 204 */     this.field_70714_bg.func_75776_a(2, new AILongRangeAttack(this, 3.0D, 1.0D, 5, 5, 24.0F));
/*     */   }
/*     */   
/* 207 */   int beamCharge = 0;
/* 208 */   boolean chargingBeam = false;
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity target)
/*     */   {
/* 213 */     if (this.attackTimer > 0) { return false;
/*     */     }
/* 215 */     this.attackTimer = 10;
/* 216 */     this.field_70170_p.func_72960_a(this, (byte)4);
/* 217 */     boolean flag = target.func_70097_a(DamageSource.func_76358_a(this), (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.75F);
/*     */     
/*     */ 
/* 220 */     if (flag)
/*     */     {
/* 222 */       target.field_70181_x += 0.2000000059604645D;
/* 223 */       if (isHeadless()) {
/* 224 */         target.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * 1.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * 1.5F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 230 */     return flag;
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f)
/*     */   {
/* 235 */     if ((func_70685_l(entitylivingbase)) && (!this.chargingBeam) && (this.beamCharge > 0)) {
/* 236 */       this.beamCharge -= 15 + this.field_70146_Z.nextInt(5);
/* 237 */       func_70671_ap().func_75650_a(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b + entitylivingbase.field_70131_O / 2.0F, entitylivingbase.field_70161_v, 30.0F, 30.0F);
/*     */       
/*     */ 
/*     */ 
/* 241 */       Vec3 v = func_70676_i(1.0F);
/* 242 */       EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, this, entitylivingbase, false);
/* 243 */       blast.field_70165_t += v.field_72450_a;
/* 244 */       blast.field_70161_v += v.field_72449_c;
/* 245 */       blast.func_70107_b(blast.field_70165_t, blast.field_70163_u, blast.field_70161_v);
/*     */       
/* 247 */       double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
/* 248 */       double d1 = entitylivingbase.field_70163_u - this.field_70163_u - entitylivingbase.field_70131_O / 2.0F;
/* 249 */       double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
/*     */       
/* 251 */       blast.func_70186_c(d0, d1, d2, 0.66F, 5.0F);
/*     */       
/* 253 */       func_85030_a("thaumcraft:egattack", 1.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 254 */       this.field_70170_p.func_72838_d(blast);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte p_70103_1_)
/*     */   {
/* 263 */     if (p_70103_1_ == 4)
/*     */     {
/* 265 */       this.attackTimer = 10;
/* 266 */       func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/*     */ 
/*     */     }
/* 269 */     else if (p_70103_1_ == 18)
/*     */     {
/* 271 */       this.spawnTimer = 150;
/*     */ 
/*     */     }
/* 274 */     else if (p_70103_1_ == 19)
/*     */     {
/* 276 */       if (this.arcing == 0) {
/* 277 */         float radius = 2.0F + this.field_70146_Z.nextFloat() * 2.0F;
/* 278 */         double radians = Math.toRadians(this.field_70146_Z.nextInt(360));
/* 279 */         double deltaX = radius * Math.cos(radians);
/* 280 */         double deltaZ = radius * Math.sin(radians);
/*     */         
/* 282 */         int bx = MathHelper.func_76128_c(this.field_70165_t + deltaX);
/* 283 */         int by = MathHelper.func_76128_c(this.field_70163_u);
/* 284 */         int bz = MathHelper.func_76128_c(this.field_70161_v + deltaZ);
/* 285 */         int c = 0;
/* 286 */         while ((c < 5) && (this.field_70170_p.func_147437_c(bx, by, bz))) {
/* 287 */           c++;
/* 288 */           by--;
/*     */         }
/* 290 */         if ((this.field_70170_p.func_147437_c(bx, by + 1, bz)) && (!this.field_70170_p.func_147437_c(bx, by, bz))) {
/* 291 */           this.ax = bx;
/* 292 */           this.ay = by;
/* 293 */           this.az = bz;
/* 294 */           this.arcing = (8 + this.field_70146_Z.nextInt(5));
/* 295 */           this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:jacobs", 0.8F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.05F, false);
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 301 */       super.func_70103_a(p_70103_1_);
/*     */     }
/*     */   }
/*     */   
/* 305 */   int arcing = 0;
/* 306 */   int ax = 0;
/* 307 */   int ay = 0;
/* 308 */   int az = 0;
/*     */   private int attackTimer;
/*     */   
/*     */   public void func_70071_h_() {
/* 312 */     if (getSpawnTimer() == 150) this.field_70170_p.func_72960_a(this, (byte)18);
/* 313 */     if (getSpawnTimer() > 0) {
/* 314 */       func_70691_i(2.0F);
/*     */     }
/* 316 */     super.func_70071_h_();
/* 317 */     if (this.field_70170_p.field_72995_K) {
/* 318 */       if (isHeadless()) {
/* 319 */         this.field_70125_A = 0.0F;
/* 320 */         float f1 = MathHelper.func_76134_b(-this.field_70761_aq * 0.017453292F - 3.1415927F);
/* 321 */         float f2 = MathHelper.func_76126_a(-this.field_70761_aq * 0.017453292F - 3.1415927F);
/* 322 */         float f3 = -MathHelper.func_76134_b(-this.field_70125_A * 0.017453292F);
/* 323 */         float f4 = MathHelper.func_76126_a(-this.field_70125_A * 0.017453292F);
/* 324 */         Vec3 v = Vec3.func_72443_a(f2 * f3, f4, f1 * f3);
/*     */         
/* 326 */         if (this.field_70146_Z.nextInt(20) == 0) {
/* 327 */           float a = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) / 2.0F;
/* 328 */           float b = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) / 2.0F;
/*     */           
/* 330 */           Thaumcraft.proxy.spark((float)(this.field_70165_t + v.field_72450_a + a), (float)this.field_70163_u + func_70047_e() - 0.25F, (float)(this.field_70161_v + v.field_72449_c + b), 0.3F, 0.65F + this.field_70146_Z.nextFloat() * 0.1F, 1.0F, 1.0F, 0.8F);
/*     */         }
/*     */         
/*     */ 
/* 334 */         Thaumcraft.proxy.drawVentParticles(this.field_70170_p, (float)this.field_70165_t + v.field_72450_a * 0.66D, (float)this.field_70163_u + func_70047_e() - 0.75F, (float)this.field_70161_v + v.field_72449_c * 0.66D, 0.0D, 0.001D, 0.0D, 5592405, 4.0F);
/*     */         
/*     */ 
/*     */ 
/* 338 */         if (this.arcing > 0) {
/* 339 */           Thaumcraft.proxy.arcLightning(this.field_70170_p, this.field_70165_t, this.field_70163_u + this.field_70131_O / 2.0F, this.field_70161_v, this.ax + 0.5D, this.ay + 1, this.az + 0.5D, 0.65F + this.field_70146_Z.nextFloat() * 0.1F, 1.0F, 1.0F, 1.0F - this.arcing / 10.0F);
/*     */           
/* 341 */           this.arcing -= 1;
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 346 */       if ((isHeadless()) && (this.beamCharge <= 0)) {
/* 347 */         this.chargingBeam = true;
/*     */       }
/* 349 */       if ((isHeadless()) && (this.chargingBeam)) {
/* 350 */         this.beamCharge += 1;
/* 351 */         this.field_70170_p.func_72960_a(this, (byte)19);
/* 352 */         if (this.beamCharge == 150) this.chargingBeam = false;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer()
/*     */   {
/* 360 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70600_l(int fortune)
/*     */   {
/* 368 */     super.func_70600_l(fortune);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/boss/EntityEldritchGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */