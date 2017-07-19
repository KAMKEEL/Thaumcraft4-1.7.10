/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*     */ import thaumcraft.common.entities.ai.combat.AICultistHurtByTarget;
/*     */ import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
/*     */ import thaumcraft.common.entities.monster.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.EntityCultistCleric;
/*     */ import thaumcraft.common.entities.monster.EntityCultistKnight;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityCultistLeader extends EntityThaumcraftBoss implements IRangedAttackMob
/*     */ {
/*     */   public EntityCultistLeader(World p_i1745_1_)
/*     */   {
/*  43 */     super(p_i1745_1_);
/*  44 */     func_70105_a(0.75F, 2.25F);
/*  45 */     this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
/*  46 */     this.field_70714_bg.func_75776_a(2, new AILongRangeAttack(this, 16.0D, 1.0D, 30, 40, 24.0F));
/*  47 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityLivingBase.class, 1.1D, false));
/*  48 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*  49 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*  50 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  51 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  52 */     this.field_70715_bh.func_75776_a(1, new AICultistHurtByTarget(this, true));
/*  53 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  54 */     this.field_70728_aV = 40;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  60 */     super.func_110147_ax();
/*  61 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32D);
/*  62 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(125.0D);
/*  63 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  70 */     super.func_70088_a();
/*  71 */     func_70096_w().func_75682_a(16, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public void generateName() {
/*  75 */     int t = (int)func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
/*  76 */     if (t >= 0) {
/*  77 */       func_94058_c(String.format(StatCollector.func_74838_a("entity.Thaumcraft.CultistLeader.name"), new Object[] { getTitle(), thaumcraft.common.entities.monster.mods.ChampionModifier.mods[t].getModNameLocalized() }));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private String getTitle()
/*     */   {
/*  85 */     return this.titles[func_70096_w().func_75683_a(16)];
/*     */   }
/*     */   
/*     */   private void setTitle(int title) {
/*  89 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)title));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/*  95 */     super.func_70014_b(nbt);
/*  96 */     nbt.func_74774_a("title", func_70096_w().func_75683_a(16));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 102 */     super.func_70037_a(nbt);
/* 103 */     setTitle(nbt.func_74771_c("title"));
/*     */   }
/*     */   
/* 106 */   String[] titles = { "Alberic", "Anselm", "Bastian", "Beturian", "Chabier", "Chorache", "Chuse", "Dodorol", "Ebardo", "Ferrando", "Fertus", "Guillen", "Larpe", "Obano", "Zelipe" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_82164_bB()
/*     */   {
/* 114 */     func_70062_b(4, new ItemStack(ConfigItems.itemHelmetCultistLeaderPlate));
/* 115 */     func_70062_b(3, new ItemStack(ConfigItems.itemChestCultistLeaderPlate));
/* 116 */     func_70062_b(2, new ItemStack(ConfigItems.itemLegsCultistLeaderPlate));
/* 117 */     func_70062_b(1, new ItemStack(ConfigItems.itemBootsCultist));
/* 118 */     if (this.field_70170_p.field_73013_u == EnumDifficulty.EASY) {
/* 119 */       func_70062_b(0, new ItemStack(ConfigItems.itemSwordVoid));
/*     */     } else {
/* 121 */       func_70062_b(0, new ItemStack(ConfigItems.itemSwordCrimson));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_82162_bC()
/*     */   {
/* 127 */     float f = this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 128 */     if ((func_70694_bm() != null) && (this.field_70146_Z.nextFloat() < 0.5F * f))
/*     */     {
/* 130 */       EnchantmentHelper.func_77504_a(this.field_70146_Z, func_70694_bm(), (int)(7.0F + f * this.field_70146_Z.nextInt(22)));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_142014_c(EntityLivingBase el)
/*     */   {
/* 136 */     return ((el instanceof EntityCultist)) || ((el instanceof EntityCultistLeader));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/* 142 */     if ((clazz == EntityCultistCleric.class) || (clazz == EntityCultistLeader.class) || (clazz == EntityCultistKnight.class))
/*     */     {
/*     */ 
/* 145 */       return false; }
/* 146 */     return super.func_70686_a(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 152 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 157 */     func_70099_a(new ItemStack(ConfigItems.itemLootbag, 1, 2), 1.5F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70600_l(int p_70600_1_) {}
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
/*     */   {
/* 166 */     func_82164_bB();
/* 167 */     func_82162_bC();
/* 168 */     setTitle(this.field_70146_Z.nextInt(this.titles.length));
/* 169 */     return super.func_110161_a(p_110161_1_);
/*     */   }
/*     */   
/*     */   protected void func_70619_bc()
/*     */   {
/* 174 */     super.func_70619_bc();
/* 175 */     java.util.List<Entity> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityCultist.class, 8.0D);
/* 176 */     for (Entity e : list) {
/*     */       try {
/* 178 */         if (((e instanceof EntityCultist)) && (!((EntityCultist)e).func_82165_m(Potion.field_76428_l.field_76415_H))) {
/* 179 */           ((EntityCultist)e).func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 60, 1));
/*     */         }
/*     */       }
/*     */       catch (Exception e1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f) {
/* 187 */     if (func_70685_l(entitylivingbase)) {
/* 188 */       func_71038_i();
/* 189 */       func_70671_ap().func_75650_a(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b + entitylivingbase.field_70131_O / 2.0F, entitylivingbase.field_70161_v, 30.0F, 30.0F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 194 */       EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, this, entitylivingbase, true);
/* 195 */       blast.field_70165_t += blast.field_70159_w / 2.0D;
/* 196 */       blast.field_70161_v += blast.field_70179_y / 2.0D;
/* 197 */       blast.func_70107_b(blast.field_70165_t, blast.field_70163_u, blast.field_70161_v);
/*     */       
/* 199 */       double d0 = entitylivingbase.field_70165_t - this.field_70165_t;
/* 200 */       double d1 = entitylivingbase.field_70121_D.field_72338_b + entitylivingbase.field_70131_O / 2.0F - (this.field_70163_u + this.field_70131_O / 2.0F);
/* 201 */       double d2 = entitylivingbase.field_70161_v - this.field_70161_v;
/*     */       
/* 203 */       blast.func_70186_c(d0, d1 + 2.0D, d2, 0.66F, 3.0F);
/*     */       
/* 205 */       func_85030_a("thaumcraft:egattack", 1.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 206 */       this.field_70170_p.func_72838_d(blast);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/boss/EntityCultistLeader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */