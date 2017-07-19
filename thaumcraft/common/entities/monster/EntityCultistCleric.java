/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
/*     */ import thaumcraft.common.entities.ai.misc.AIAltarFocus;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ 
/*     */ public class EntityCultistCleric extends EntityCultist implements IRangedAttackMob, cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*     */   public EntityCultistCleric(World p_i1745_1_)
/*     */   {
/*  35 */     super(p_i1745_1_);
/*  36 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  37 */     this.field_70714_bg.func_75776_a(1, new AIAltarFocus(this));
/*  38 */     this.field_70714_bg.func_75776_a(2, new AILongRangeAttack(this, 2.0D, 1.0D, 20, 40, 24.0F));
/*  39 */     this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
/*  40 */     this.field_70714_bg.func_75776_a(4, new EntityAIRestrictOpenDoor(this));
/*  41 */     this.field_70714_bg.func_75776_a(5, new EntityAIOpenDoor(this, true));
/*  42 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*  43 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*  44 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  45 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  46 */     this.field_70715_bh.func_75776_a(1, new thaumcraft.common.entities.ai.combat.AICultistHurtByTarget(this, true));
/*  47 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  53 */     super.func_110147_ax();
/*  54 */     func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_82164_bB()
/*     */   {
/*  60 */     func_70062_b(4, new ItemStack(ConfigItems.itemHelmetCultistRobe));
/*  61 */     func_70062_b(3, new ItemStack(ConfigItems.itemChestCultistRobe));
/*  62 */     func_70062_b(2, new ItemStack(ConfigItems.itemLegsCultistRobe));
/*  63 */     if (this.field_70146_Z.nextFloat() < (this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 0.3F : 0.1F))
/*     */     {
/*  65 */       func_70062_b(1, new ItemStack(ConfigItems.itemBootsCultist));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f)
/*     */   {
/*  71 */     double d0 = entitylivingbase.field_70165_t - this.field_70165_t;
/*  72 */     double d1 = entitylivingbase.field_70121_D.field_72338_b + entitylivingbase.field_70131_O / 2.0F - (this.field_70163_u + this.field_70131_O / 2.0F);
/*  73 */     double d2 = entitylivingbase.field_70161_v - this.field_70161_v;
/*  74 */     func_71038_i();
/*  75 */     if (this.field_70146_Z.nextFloat() > 0.66F) {
/*  76 */       EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, this, entitylivingbase, true);
/*  77 */       blast.field_70165_t += blast.field_70159_w / 2.0D;
/*  78 */       blast.field_70161_v += blast.field_70179_y / 2.0D;
/*  79 */       blast.func_70107_b(blast.field_70165_t, blast.field_70163_u, blast.field_70161_v);
/*  80 */       blast.func_70186_c(d0, d1 + 2.0D, d2, 0.66F, 3.0F);
/*  81 */       func_85030_a("thaumcraft:egattack", 1.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/*  82 */       this.field_70170_p.func_72838_d(blast);
/*     */     } else {
/*  84 */       float f1 = MathHelper.func_76129_c(f) * 0.5F;
/*  85 */       this.field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */       
/*  87 */       for (int i = 0; i < 3; i++)
/*     */       {
/*  89 */         EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.field_70170_p, this, d0 + this.field_70146_Z.nextGaussian() * f1, d1, d2 + this.field_70146_Z.nextGaussian() * f1);
/*  90 */         entitysmallfireball.field_70163_u = (this.field_70163_u + this.field_70131_O / 2.0F + 0.5D);
/*  91 */         this.field_70170_p.func_72838_d(entitysmallfireball);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/*  99 */     return !getIsRitualist();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70088_a()
/*     */   {
/* 105 */     super.func_70088_a();
/* 106 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*     */   }
/*     */   
/*     */   public boolean getIsRitualist()
/*     */   {
/* 111 */     return (this.field_70180_af.func_75683_a(16) & 0x1) != 0;
/*     */   }
/*     */   
/*     */   public void setIsRitualist(boolean par1)
/*     */   {
/* 116 */     byte var2 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 118 */     if (par1)
/*     */     {
/* 120 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 | 0x1)));
/*     */     }
/*     */     else
/*     */     {
/* 124 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*     */   {
/* 131 */     if (func_85032_ar())
/*     */     {
/* 133 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 137 */     setIsRitualist(false);
/* 138 */     return super.func_70097_a(p_70097_1_, p_70097_2_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 148 */     super.func_70037_a(par1NBTTagCompound);
/* 149 */     this.field_70180_af.func_75692_b(16, Byte.valueOf(par1NBTTagCompound.func_74771_c("Flags")));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 155 */     super.func_70014_b(par1NBTTagCompound);
/* 156 */     par1NBTTagCompound.func_74774_a("Flags", this.field_70180_af.func_75683_a(16));
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/* 161 */     data.writeInt(func_110172_bL().field_71574_a);
/* 162 */     data.writeInt(func_110172_bL().field_71572_b);
/* 163 */     data.writeInt(func_110172_bL().field_71573_c);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/* 168 */     func_110171_b(data.readInt(), data.readInt(), data.readInt(), 8);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 175 */     super.func_70071_h_();
/*     */     
/* 177 */     if ((this.field_70170_p.field_72995_K) && (getIsRitualist())) {
/* 178 */       double d0 = func_110172_bL().field_71574_a + 0.5D - this.field_70165_t;
/* 179 */       double d1 = func_110172_bL().field_71572_b + 1.5D - (this.field_70163_u + func_70047_e());
/* 180 */       double d2 = func_110172_bL().field_71573_c + 0.5D - this.field_70161_v;
/* 181 */       double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/* 182 */       float f = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 183 */       float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
/* 184 */       this.field_70125_A = updateRotation(this.field_70125_A, f1, 10.0F);
/* 185 */       this.field_70759_as = updateRotation(this.field_70759_as, f, func_70646_bf());
/*     */     }
/*     */   }
/*     */   
/*     */   private float updateRotation(float p_75652_1_, float p_75652_2_, float p_75652_3_)
/*     */   {
/* 191 */     float f3 = MathHelper.func_76142_g(p_75652_2_ - p_75652_1_);
/*     */     
/* 193 */     if (f3 > p_75652_3_)
/*     */     {
/* 195 */       f3 = p_75652_3_;
/*     */     }
/*     */     
/* 198 */     if (f3 < -p_75652_3_)
/*     */     {
/* 200 */       f3 = -p_75652_3_;
/*     */     }
/*     */     
/* 203 */     return p_75652_1_ + f3;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 209 */     return "thaumcraft:chant";
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 215 */     return 500;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityCultistCleric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */