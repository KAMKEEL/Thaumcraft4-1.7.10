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
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*     */ 
/*     */ public class EntityTaintChicken extends EntityMob implements ITaintedMob
/*     */ {
/*  26 */   public boolean field_753_a = false;
/*  27 */   public float field_752_b = 0.0F;
/*  28 */   public float destPos = 0.0F;
/*     */   public float field_757_d;
/*     */   public float field_756_e;
/*  31 */   public float field_755_h = 1.0F;
/*     */   
/*     */   public EntityTaintChicken(World par1World)
/*     */   {
/*  35 */     super(par1World);
/*  36 */     func_70105_a(0.5F, 0.8F);
/*  37 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  38 */     this.field_70714_bg.func_75776_a(2, new AIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  39 */     this.field_70714_bg.func_75776_a(2, new net.minecraft.entity.ai.EntityAILeapAtTarget(this, 0.3F));
/*  40 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  41 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityAnimal.class, 1.0D, true));
/*  42 */     this.field_70714_bg.func_75776_a(3, new EntityAIWander(this, 1.0D));
/*  43 */     this.field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  44 */     this.field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  45 */     this.field_70715_bh.func_75776_a(0, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  46 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  47 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
/*  48 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, false));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  54 */     super.func_110147_ax();
/*  55 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
/*  56 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
/*  57 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
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
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  79 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/*  88 */     super.func_70636_d();
/*  89 */     this.field_756_e = this.field_752_b;
/*  90 */     this.field_757_d = this.destPos;
/*  91 */     this.destPos = ((float)(this.destPos + (this.field_70122_E ? -1 : 4) * 0.3D));
/*     */     
/*  93 */     if (this.destPos < 0.0F)
/*     */     {
/*  95 */       this.destPos = 0.0F;
/*     */     }
/*     */     
/*  98 */     if (this.destPos > 1.0F)
/*     */     {
/* 100 */       this.destPos = 1.0F;
/*     */     }
/*     */     
/* 103 */     if ((!this.field_70122_E) && (this.field_755_h < 1.0F))
/*     */     {
/* 105 */       this.field_755_h = 1.0F;
/*     */     }
/*     */     
/* 108 */     this.field_755_h = ((float)(this.field_755_h * 0.9D));
/*     */     
/* 110 */     if ((!this.field_70122_E) && (this.field_70181_x < 0.0D))
/*     */     {
/* 112 */       this.field_70181_x *= 0.9D;
/*     */     }
/*     */     
/* 115 */     this.field_752_b += this.field_755_h * 2.0F;
/*     */     
/* 117 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/* 118 */       for (int a = 0; a < Thaumcraft.proxy.particleCount(10); a++) {
/* 119 */         Thaumcraft.proxy.splooshFX(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70069_a(float par1) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 132 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 140 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 148 */     return "mob.chicken.say";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 156 */     return "mob.chicken.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 164 */     return "mob.chicken.hurt";
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 169 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 180 */     return ConfigItems.itemResource;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 185 */     if (this.field_70170_p.field_73012_v.nextInt(4) == 0) {
/* 186 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*     */     } else {
/* 188 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintChicken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */