/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.monster.EntitySpider.GroupData;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityEldritchCrab extends EntityMob
/*     */ {
/*     */   public EntityEldritchCrab(World par1World)
/*     */   {
/*  34 */     super(par1World);
/*  35 */     func_70105_a(0.8F, 0.6F);
/*  36 */     this.field_70728_aV = 6;
/*  37 */     func_70661_as().func_75498_b(true);
/*  38 */     func_70661_as().func_75491_a(true);
/*  39 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  40 */     this.field_70714_bg.func_75776_a(2, new EntityAILeapAtTarget(this, 0.63F));
/*  41 */     this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
/*  42 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*  43 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  44 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
/*  45 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, net.minecraft.entity.player.EntityPlayer.class, 0, true));
/*  46 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityCultist.class, 0, true));
/*     */   }
/*     */   
/*     */   public double func_70033_W()
/*     */   {
/*  51 */     return func_70115_ae() ? 0.5D : 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  57 */     super.func_110147_ax();
/*  58 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/*  59 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*  60 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(hasHelm() ? 0.275D : 0.3D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  66 */     super.func_70088_a();
/*  67 */     this.field_70180_af.func_75682_a(22, new Byte((byte)0));
/*     */   }
/*     */   
/*     */   public boolean func_98052_bS()
/*     */   {
/*  72 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  78 */     return hasHelm() ? 5 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData livingData)
/*     */   {
/*  84 */     if (this.field_70170_p.field_73013_u == EnumDifficulty.HARD) {
/*  85 */       setHelm(true);
/*     */     } else {
/*  87 */       setHelm(this.field_70146_Z.nextFloat() < 0.33F);
/*     */     }
/*     */     
/*  90 */     if (livingData == null)
/*     */     {
/*  92 */       livingData = new EntitySpider.GroupData();
/*     */       
/*  94 */       if ((this.field_70170_p.field_73013_u == EnumDifficulty.HARD) && (this.field_70170_p.field_73012_v.nextFloat() < 0.1F * this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v)))
/*     */       {
/*  96 */         ((EntitySpider.GroupData)livingData).func_111104_a(this.field_70170_p.field_73012_v);
/*     */       }
/*     */     }
/*     */     
/* 100 */     if ((livingData instanceof EntitySpider.GroupData))
/*     */     {
/* 102 */       int i = ((EntitySpider.GroupData)livingData).field_111105_a;
/*     */       
/* 104 */       if ((i > 0) && (Potion.field_76425_a[i] != null))
/*     */       {
/* 106 */         func_70690_d(new PotionEffect(i, Integer.MAX_VALUE));
/*     */       }
/*     */     }
/*     */     
/* 110 */     return super.func_110161_a(livingData);
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70650_aV()
/*     */   {
/* 116 */     return true;
/*     */   }
/*     */   
/*     */   public boolean hasHelm()
/*     */   {
/* 121 */     return (this.field_70180_af.func_75683_a(22) & 0x1) != 0;
/*     */   }
/*     */   
/*     */   public void setHelm(boolean par1)
/*     */   {
/* 126 */     byte var2 = this.field_70180_af.func_75683_a(22);
/*     */     
/* 128 */     if (par1)
/*     */     {
/* 130 */       this.field_70180_af.func_75692_b(22, Byte.valueOf((byte)(var2 | 0x1)));
/*     */     }
/*     */     else
/*     */     {
/* 134 */       this.field_70180_af.func_75692_b(22, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 142 */     super.func_70071_h_();
/*     */     
/* 144 */     if (this.field_70173_aa < 20) { this.field_70143_R = 0.0F;
/*     */     }
/* 146 */     if ((this.field_70154_o == null) && (func_70643_av() != null) && (func_70643_av().field_70153_n == null) && (!this.field_70122_E) && (!hasHelm()) && (!func_70643_av().field_70128_L) && (this.field_70163_u - func_70643_av().field_70163_u >= func_70643_av().field_70131_O / 2.0F) && (func_70068_e(func_70643_av()) < 4.0D))
/*     */     {
/*     */ 
/*     */ 
/* 150 */       func_70078_a(func_70643_av());
/*     */     }
/*     */     
/* 153 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70154_o != null) && 
/* 154 */       (this.field_70724_aR <= 0)) {
/* 155 */       this.field_70724_aR = (10 + this.field_70146_Z.nextInt(10));
/* 156 */       func_70652_k(this.field_70154_o);
/* 157 */       if ((this.field_70154_o != null) && (this.field_70146_Z.nextFloat() < 0.2D)) { func_110145_l(this.field_70154_o);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 165 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
/*     */   {
/* 171 */     super.func_70628_a(p_70628_1_, p_70628_2_);
/*     */     
/* 173 */     if ((p_70628_1_) && ((this.field_70146_Z.nextInt(3) == 0) || (this.field_70146_Z.nextInt(1 + p_70628_2_) > 0)))
/*     */     {
/* 175 */       func_145779_a(Items.field_151079_bi, 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity p_70652_1_)
/*     */   {
/* 181 */     if (super.func_70652_k(p_70652_1_)) {
/* 182 */       func_85030_a("thaumcraft:crabclaw", 1.0F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/* 183 */       return true; }
/* 184 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 190 */     boolean b = super.func_70097_a(source, damage);
/*     */     
/* 192 */     if ((hasHelm()) && (func_110143_aJ() / func_110138_aP() <= 0.5F)) {
/* 193 */       setHelm(false);
/* 194 */       func_70669_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemChestCultistPlate));
/* 195 */       func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */     }
/*     */     
/* 198 */     return b;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 205 */     super.func_70037_a(par1NBTTagCompound);
/* 206 */     this.field_70180_af.func_75692_b(22, Byte.valueOf(par1NBTTagCompound.func_74771_c("Flags")));
/* 207 */     if (!hasHelm()) {
/* 208 */       func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 218 */     super.func_70014_b(par1NBTTagCompound);
/* 219 */     par1NBTTagCompound.func_74774_a("Flags", this.field_70180_af.func_75683_a(22));
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 225 */     return 160;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 231 */     return "thaumcraft:crabtalk";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 237 */     return "game.hostile.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 243 */     return "thaumcraft:crabdeath";
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
/*     */   {
/* 249 */     func_85030_a("mob.spider.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumCreatureAttribute func_70668_bt()
/*     */   {
/* 255 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70687_e(PotionEffect p_70687_1_)
/*     */   {
/* 261 */     return p_70687_1_.func_76456_a() == Potion.field_76436_u.field_76415_H ? false : super.func_70687_e(p_70687_1_);
/*     */   }
/*     */   
/*     */   public boolean func_142014_c(EntityLivingBase el)
/*     */   {
/* 266 */     return el instanceof EntityEldritchCrab;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityEldritchCrab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */