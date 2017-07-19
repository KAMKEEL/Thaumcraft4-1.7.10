/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.living.EnderTeleportEvent;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.entities.monster.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ import thaumcraft.common.entities.projectile.EntityEldritchOrb;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockArc;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
/*     */ 
/*     */ public class EntityEldritchWarden extends EntityThaumcraftBoss implements IRangedAttackMob, IEldritchMob
/*     */ {
/*     */   public EntityEldritchWarden(World p_i1745_1_)
/*     */   {
/*  47 */     super(p_i1745_1_);
/*  48 */     func_70661_as().func_75498_b(true);
/*  49 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  50 */     this.field_70714_bg.func_75776_a(2, new thaumcraft.common.entities.ai.combat.AILongRangeAttack(this, 3.0D, 1.0D, 20, 40, 24.0F));
/*  51 */     this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.1D, false));
/*  52 */     this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*  53 */     this.field_70714_bg.func_75776_a(7, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
/*  54 */     this.field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  55 */     this.field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  56 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  57 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  58 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityCultist.class, 0, true));
/*  59 */     func_70105_a(1.5F, 3.5F);
/*     */   }
/*     */   
/*     */   public void generateName()
/*     */   {
/*  64 */     int t = (int)func_110148_a(thaumcraft.common.lib.utils.EntityUtils.CHAMPION_MOD).func_111126_e();
/*  65 */     if (t >= 0) {
/*  66 */       func_94058_c(String.format(net.minecraft.util.StatCollector.func_74838_a("entity.Thaumcraft.EldritchWarden.name"), new Object[] { getTitle(), ChampionModifier.mods[t].getModNameLocalized() }));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private String getTitle()
/*     */   {
/*  73 */     return this.titles[func_70096_w().func_75683_a(16)];
/*     */   }
/*     */   
/*     */   private void setTitle(int title) {
/*  77 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)title));
/*     */   }
/*     */   
/*  80 */   String[] titles = { "Aphoom-Zhah", "Basatan", "Chaugnar Faugn", "Mnomquah", "Nyogtha", "Oorn", "Shaikorth", "Rhan-Tegoth", "Rhogog", "Shudde M'ell", "Vulthoom", "Yag-Kosha", "Yibb-Tstll", "Zathog", "Zushakon" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  88 */     super.func_110147_ax();
/*  89 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
/*  90 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.33D);
/*  91 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(10.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  97 */     super.func_70088_a();
/*  98 */     func_70096_w().func_75682_a(16, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/* 104 */     super.func_70014_b(nbt);
/* 105 */     nbt.func_74774_a("title", func_70096_w().func_75683_a(16));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 111 */     super.func_70037_a(nbt);
/* 112 */     setTitle(nbt.func_74771_c("title"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/* 121 */     return super.func_70658_aO() + 4;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70619_bc()
/*     */   {
/* 127 */     if (this.fieldFrenzyCounter == 0) {
/* 128 */       super.func_70619_bc();
/*     */     }
/*     */     
/* 131 */     if ((this.field_70172_ad <= 0) && (this.field_70173_aa % 25 == 0)) {
/* 132 */       int bh = (int)(func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() * 0.66D);
/* 133 */       if (func_110139_bj() < bh) {
/* 134 */         func_110149_m(func_110139_bj() + 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/* 140 */     if (getSpawnTimer() == 150) this.field_70170_p.func_72960_a(this, (byte)18);
/* 141 */     super.func_70071_h_();
/* 142 */     if (this.field_70170_p.field_72995_K) {
/* 143 */       if (this.armLiftL > 0.0F) this.armLiftL -= 0.05F;
/* 144 */       if (this.armLiftR > 0.0F) this.armLiftR -= 0.05F;
/* 145 */       float x = (float)(this.field_70165_t + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/* 146 */       float z = (float)(this.field_70161_v + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/* 147 */       Thaumcraft.proxy.wispFXEG(this.field_70170_p, x, (float)(this.field_70163_u + 0.25D * this.field_70131_O), z, this);
/*     */       
/* 149 */       if (this.spawnTimer > 0) {
/* 150 */         float he = Math.max(1.0F, this.field_70131_O * ((150 - this.spawnTimer) / 150.0F));
/* 151 */         for (int a = 0; a < 33; a++) {
/* 152 */           Thaumcraft.proxy.smokeSpiral(this.field_70170_p, this.field_70165_t, this.field_70121_D.field_72338_b + he / 2.0F, this.field_70161_v, he, this.field_70146_Z.nextInt(360), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, 2232623);
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
/*     */ 
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 169 */     super.func_70636_d();
/* 170 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 171 */     int j = MathHelper.func_76128_c(this.field_70163_u);
/* 172 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 174 */     for (int l = 0; l < 4; l++)
/*     */     {
/* 176 */       i = MathHelper.func_76128_c(this.field_70165_t + (l % 2 * 2 - 1) * 0.25F);
/* 177 */       j = MathHelper.func_76128_c(this.field_70163_u);
/* 178 */       k = MathHelper.func_76128_c(this.field_70161_v + (l / 2 % 2 * 2 - 1) * 0.25F);
/*     */       
/* 180 */       if (this.field_70170_p.func_147437_c(i, j, k))
/*     */       {
/* 182 */         this.field_70170_p.func_147465_d(i, j, k, ConfigBlocks.blockAiry, 11, 3);
/*     */       }
/*     */     }
/*     */     
/* 186 */     if ((!this.field_70170_p.field_72995_K) && (this.fieldFrenzyCounter > 0)) {
/* 187 */       if (this.fieldFrenzyCounter == 150) {
/* 188 */         teleportHome();
/*     */       }
/* 190 */       performFieldFrenzy();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 195 */   boolean fieldFrenzy = false;
/* 196 */   int fieldFrenzyCounter = 0;
/*     */   
/*     */   private void performFieldFrenzy()
/*     */   {
/* 200 */     if ((this.fieldFrenzyCounter < 121) && (this.fieldFrenzyCounter % 10 == 0)) {
/* 201 */       this.field_70170_p.func_72960_a(this, (byte)17);
/* 202 */       double radius = (150 - this.fieldFrenzyCounter) / 8.0D;
/* 203 */       int d = 1 + this.fieldFrenzyCounter / 8;
/* 204 */       int i = MathHelper.func_76128_c(this.field_70165_t);
/* 205 */       int j = MathHelper.func_76128_c(this.field_70163_u);
/* 206 */       int k = MathHelper.func_76128_c(this.field_70161_v);
/* 207 */       for (int q = 0; q < 180 / d; q++)
/*     */       {
/* 209 */         double radians = Math.toRadians(q * 2 * d);
/* 210 */         int deltaX = (int)(radius * Math.cos(radians));
/* 211 */         int deltaZ = (int)(radius * Math.sin(radians));
/*     */         
/* 213 */         if ((this.field_70170_p.func_147437_c(i + deltaX, j, k + deltaZ)) && (this.field_70170_p.func_147445_c(i + deltaX, j - 1, k + deltaZ, false)))
/*     */         {
/* 215 */           this.field_70170_p.func_147465_d(i + deltaX, j, k + deltaZ, ConfigBlocks.blockAiry, 11, 3);
/* 216 */           this.field_70170_p.func_147464_a(i + deltaX, j, k + deltaZ, ConfigBlocks.blockAiry, 250 + this.field_70146_Z.nextInt(150));
/*     */           
/* 218 */           if (this.field_70146_Z.nextFloat() < 0.3F) {
/* 219 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(i + deltaX, j, k + deltaZ, func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, i + deltaX, j, k + deltaZ, 32.0D));
/*     */           }
/*     */           else
/*     */           {
/* 223 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(i + deltaX, j, k + deltaZ, 8388736), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, i + deltaX, j, k + deltaZ, 32.0D));
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 228 */       this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:zap", 1.0F, 0.9F + this.field_70146_Z.nextFloat() * 0.1F);
/*     */     }
/*     */     
/* 231 */     this.fieldFrenzyCounter -= 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void teleportHome()
/*     */   {
/* 238 */     EnderTeleportEvent event = new EnderTeleportEvent(this, func_110172_bL().field_71574_a, func_110172_bL().field_71572_b, func_110172_bL().field_71573_c, 0.0F);
/* 239 */     if (MinecraftForge.EVENT_BUS.post(event)) {
/* 240 */       return;
/*     */     }
/* 242 */     double d3 = this.field_70165_t;
/* 243 */     double d4 = this.field_70163_u;
/* 244 */     double d5 = this.field_70161_v;
/* 245 */     this.field_70165_t = event.targetX;
/* 246 */     this.field_70163_u = event.targetY;
/* 247 */     this.field_70161_v = event.targetZ;
/* 248 */     boolean flag = false;
/* 249 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 250 */     int j = MathHelper.func_76128_c(this.field_70163_u);
/* 251 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 253 */     if (this.field_70170_p.func_72899_e(i, j, k))
/*     */     {
/* 255 */       boolean flag1 = false;
/* 256 */       int tries = 20;
/* 257 */       while ((!flag1) && (tries > 0))
/*     */       {
/* 259 */         Block block = this.field_70170_p.func_147439_a(i, j - 1, k);
/* 260 */         Block block2 = this.field_70170_p.func_147439_a(i, j, k);
/* 261 */         if ((block.func_149688_o().func_76230_c()) && (!block2.func_149688_o().func_76230_c()))
/*     */         {
/* 263 */           flag1 = true;
/*     */         }
/*     */         else
/*     */         {
/* 267 */           i = MathHelper.func_76128_c(this.field_70165_t) + this.field_70146_Z.nextInt(8) - this.field_70146_Z.nextInt(8);
/* 268 */           k = MathHelper.func_76128_c(this.field_70161_v) + this.field_70146_Z.nextInt(8) - this.field_70146_Z.nextInt(8);
/* 269 */           tries--;
/*     */         }
/*     */       }
/*     */       
/* 273 */       if (flag1)
/*     */       {
/* 275 */         func_70107_b(i + 0.5D, j + 0.1D, k + 0.5D);
/*     */         
/* 277 */         if (this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty())
/*     */         {
/* 279 */           flag = true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 284 */     if (!flag)
/*     */     {
/* 286 */       func_70107_b(d3, d4, d5);
/* 287 */       return;
/*     */     }
/*     */     
/*     */ 
/* 291 */     short short1 = 128;
/*     */     
/* 293 */     for (int l = 0; l < short1; l++)
/*     */     {
/* 295 */       double d6 = l / (short1 - 1.0D);
/* 296 */       float f = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 297 */       float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 298 */       float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 299 */       double d7 = d3 + (this.field_70165_t - d3) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 300 */       double d8 = d4 + (this.field_70163_u - d4) * d6 + this.field_70146_Z.nextDouble() * this.field_70131_O;
/* 301 */       double d9 = d5 + (this.field_70161_v - d5) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 302 */       this.field_70170_p.func_72869_a("portal", d7, d8, d9, f, f1, f2);
/*     */     }
/*     */     
/* 305 */     this.field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
/* 306 */     func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_85032_ar()
/*     */   {
/* 314 */     return (this.fieldFrenzyCounter > 0) || (super.func_85032_ar());
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 320 */     if ((func_85032_ar()) || (source == DamageSource.field_76369_e) || (source == DamageSource.field_82727_n))
/*     */     {
/* 322 */       return false;
/*     */     }
/*     */     
/* 325 */     boolean aef = super.func_70097_a(source, damage);
/*     */     
/* 327 */     if ((!this.field_70170_p.field_72995_K) && (aef) && (!this.fieldFrenzy) && (func_110139_bj() <= 0.0F)) {
/* 328 */       this.fieldFrenzy = true;
/* 329 */       this.fieldFrenzyCounter = 150;
/*     */     }
/*     */     
/* 332 */     return aef;
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
/*     */   {
/* 338 */     this.spawnTimer = 150;
/* 339 */     setTitle(this.field_70146_Z.nextInt(this.titles.length));
/* 340 */     func_110149_m((float)(func_110139_bj() + func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() * 0.66D));
/* 341 */     return super.func_110161_a(p_110161_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 347 */     return 3.1F;
/*     */   }
/*     */   
/* 350 */   boolean lastBlast = false;
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f)
/*     */   {
/* 354 */     if (this.field_70146_Z.nextFloat() > 0.2F) {
/* 355 */       EntityEldritchOrb blast = new EntityEldritchOrb(this.field_70170_p, this);
/* 356 */       this.lastBlast = (!this.lastBlast);
/*     */       
/* 358 */       this.field_70170_p.func_72960_a(this, (byte)(this.lastBlast ? 16 : 15));
/*     */       
/* 360 */       int rr = this.lastBlast ? 90 : 180;
/* 361 */       double xx = MathHelper.func_76134_b((this.field_70177_z + rr) % 360.0F / 180.0F * 3.1415927F) * 0.5F;
/* 362 */       double yy = 0.13D;
/* 363 */       double zz = MathHelper.func_76126_a((this.field_70177_z + rr) % 360.0F / 180.0F * 3.1415927F) * 0.5F;
/* 364 */       blast.func_70107_b(blast.field_70165_t - xx, blast.field_70163_u - yy, blast.field_70161_v - zz);
/*     */       
/* 366 */       double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
/* 367 */       double d1 = entitylivingbase.field_70163_u - this.field_70163_u - entitylivingbase.field_70131_O / 2.0F;
/* 368 */       double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
/*     */       
/* 370 */       blast.func_70186_c(d0, d1, d2, 1.0F, 2.0F);
/*     */       
/* 372 */       func_85030_a("thaumcraft:egattack", 2.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 373 */       this.field_70170_p.func_72838_d(blast);
/*     */     }
/* 375 */     else if (func_70685_l(entitylivingbase)) {
/* 376 */       PacketHandler.INSTANCE.sendToAllAround(new thaumcraft.common.lib.network.fx.PacketFXSonic(func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */       
/*     */ 
/* 379 */       entitylivingbase.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * 1.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * 1.5F);
/*     */       
/*     */       try
/*     */       {
/* 383 */         entitylivingbase.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 400, 0));
/* 384 */         entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 400, 0));
/*     */       }
/*     */       catch (Exception e) {}
/* 387 */       if ((entitylivingbase instanceof EntityPlayer)) {
/* 388 */         Thaumcraft.addWarpToPlayer((EntityPlayer)entitylivingbase, 3 + this.field_70170_p.field_73012_v.nextInt(3), true);
/*     */       }
/*     */       
/* 391 */       func_85030_a("thaumcraft:egscreech", 4.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 396 */   public float armLiftL = 0.0F;
/* 397 */   public float armLiftR = 0.0F;
/*     */   
/*     */ 
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_70103_a(byte p_70103_1_)
/*     */   {
/* 403 */     if (p_70103_1_ == 15)
/*     */     {
/* 405 */       this.armLiftL = 0.5F;
/*     */ 
/*     */     }
/* 408 */     else if (p_70103_1_ == 16)
/*     */     {
/* 410 */       this.armLiftR = 0.5F;
/*     */ 
/*     */     }
/* 413 */     else if (p_70103_1_ == 17)
/*     */     {
/* 415 */       this.armLiftL = 0.9F;
/* 416 */       this.armLiftR = 0.9F;
/*     */ 
/*     */     }
/* 419 */     else if (p_70103_1_ == 18)
/*     */     {
/* 421 */       this.spawnTimer = 150;
/*     */     }
/*     */     else
/*     */     {
/* 425 */       super.func_70103_a(p_70103_1_);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70600_l(int fortune)
/*     */   {
/* 432 */     super.func_70600_l(fortune);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/* 438 */     if (clazz == thaumcraft.common.entities.monster.EntityEldritchGuardian.class)
/* 439 */       return false;
/* 440 */     return super.func_70686_a(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 449 */     return "thaumcraft:egidle";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 459 */     return "thaumcraft:egdeath";
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 465 */     return 500;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/boss/EntityEldritchWarden.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */