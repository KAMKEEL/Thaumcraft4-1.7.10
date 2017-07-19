/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*     */ 
/*     */ public class EntityTaintPig extends EntityMob implements thaumcraft.api.entities.ITaintedMob
/*     */ {
/*     */   public EntityTaintPig(World par1World)
/*     */   {
/*  27 */     super(par1World);
/*  28 */     func_70105_a(0.9F, 0.9F);
/*  29 */     func_70661_as().func_75491_a(true);
/*  30 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  31 */     this.field_70714_bg.func_75776_a(2, new AIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  32 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  33 */     this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
/*  34 */     this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  35 */     this.field_70714_bg.func_75776_a(7, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  36 */     this.field_70714_bg.func_75776_a(8, new AIAttackOnCollide(this, EntityAnimal.class, 1.0D, false));
/*  37 */     this.field_70715_bh.func_75776_a(0, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  38 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  39 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
/*  40 */     this.field_70715_bh.func_75776_a(8, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, false));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  46 */     super.func_110147_ax();
/*  47 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/*  48 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*  49 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.275D);
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/*  54 */     super.func_70636_d();
/*  55 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/*  56 */       for (int a = 0; a < Thaumcraft.proxy.particleCount(10); a++)
/*  57 */         Thaumcraft.proxy.splooshFX(this);
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba() {
/*  62 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/*  70 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  78 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  84 */     super.func_70088_a();
/*  85 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  93 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 101 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 106 */     return "mob.pig.say";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 114 */     return "mob.pig.say";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 122 */     return "mob.pig.death";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4)
/*     */   {
/* 130 */     func_85030_a("mob.pig.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 135 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70085_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 143 */     if (super.func_70085_c(par1EntityPlayer))
/*     */     {
/* 145 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 149 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected net.minecraft.item.Item func_146068_u()
/*     */   {
/* 156 */     return ConfigItems.itemResource;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 161 */     if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 162 */       if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 163 */         func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*     */       } else {
/* 165 */         func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintPig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */