/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ 
/*     */ public class EntityInhabitedZombie
/*     */   extends EntityZombie
/*     */ {
/*     */   protected void func_110147_ax()
/*     */   {
/*  28 */     super.func_110147_ax();
/*  29 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*  30 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/*  31 */     func_110148_a(field_110186_bp).func_111128_a(0.0D);
/*     */   }
/*     */   
/*     */   public EntityInhabitedZombie(World world)
/*     */   {
/*  36 */     super(world);
/*  37 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true));
/*  38 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityCultist.class, 0, true));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70074_a(EntityLivingBase par1EntityLivingBase) {}
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
/*     */   {
/*  47 */     float diff = this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 0.9F : 0.6F;
/*  48 */     func_70062_b(4, new ItemStack(ConfigItems.itemHelmetCultistPlate));
/*  49 */     if (this.field_70146_Z.nextFloat() <= diff) func_70062_b(3, new ItemStack(ConfigItems.itemChestCultistPlate));
/*  50 */     if (this.field_70146_Z.nextFloat() <= diff) func_70062_b(2, new ItemStack(ConfigItems.itemLegsCultistPlate));
/*  51 */     return p_110161_1_;
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/*  57 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {}
/*     */   
/*     */ 
/*     */   protected void func_70609_aI()
/*     */   {
/*  66 */     if (!this.field_70170_p.field_72995_K) {
/*  67 */       EntityEldritchCrab crab = new EntityEldritchCrab(this.field_70170_p);
/*  68 */       crab.func_70080_a(this.field_70165_t, this.field_70163_u + func_70047_e(), this.field_70161_v, this.field_70177_z, this.field_70125_A);
/*  69 */       crab.setHelm(true);
/*  70 */       this.field_70170_p.func_72838_d(crab);
/*     */       
/*  72 */       if (((this.field_70718_bc > 0) || (func_70684_aJ())) && (func_146066_aG()) && (this.field_70170_p.func_82736_K().func_82766_b("doMobLoot")))
/*     */       {
/*  74 */         int i = func_70693_a(this.field_70717_bb);
/*     */         
/*  76 */         while (i > 0)
/*     */         {
/*  78 */           int j = EntityXPOrb.func_70527_a(i);
/*  79 */           i -= j;
/*  80 */           this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  85 */     for (int i = 0; i < 20; i++)
/*     */     {
/*  87 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  88 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  89 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  90 */       this.field_70170_p.func_72869_a("explode", this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d2, d0, d1);
/*     */     }
/*     */     
/*  93 */     func_70106_y();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource p_70645_1_) {}
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 101 */     return "thaumcraft:crabtalk";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 107 */     return "game.hostile.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 113 */     List ents = this.field_70170_p.func_72872_a(EntityInhabitedZombie.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(32.0D, 16.0D, 32.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 119 */     return ents.size() > 0 ? false : super.func_70601_bi();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityInhabitedZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */