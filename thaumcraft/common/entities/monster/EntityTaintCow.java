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
/*     */ public class EntityTaintCow extends EntityMob implements thaumcraft.api.entities.ITaintedMob
/*     */ {
/*     */   public EntityTaintCow(World par1World)
/*     */   {
/*  27 */     super(par1World);
/*  28 */     func_70105_a(0.9F, 1.3F);
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
/*  47 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0D);
/*  48 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
/*  49 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/*  57 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  66 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  74 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/*  79 */     return "mob.cow.say";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/*  87 */     return "mob.cow.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/*  95 */     return "mob.cow.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4)
/*     */   {
/* 103 */     func_85030_a("mob.cow.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 108 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 117 */     return 0.4F;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 122 */     super.func_70636_d();
/* 123 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/* 124 */       for (int a = 0; a < Thaumcraft.proxy.particleCount(10); a++) {
/* 125 */         Thaumcraft.proxy.splooshFX(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected net.minecraft.item.Item func_146068_u() {
/* 131 */     return ConfigItems.itemResource;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 136 */     if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 137 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*     */     } else {
/* 139 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintCow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */