/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.boss.IBossDisplayData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.monster.EntityTaintacle;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityTaintacleGiant
/*     */   extends EntityTaintacle
/*     */   implements ITaintedMob, IBossDisplayData
/*     */ {
/*     */   public EntityTaintacleGiant(World par1World)
/*     */   {
/*  35 */     super(par1World);
/*  36 */     func_70105_a(1.1F, 6.0F);
/*  37 */     this.field_70728_aV = 20;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  42 */     super.func_110147_ax();
/*  43 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(125.0D);
/*  44 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(9.0D);
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData data)
/*     */   {
/*  49 */     EntityUtils.makeChampion(this, true);
/*  50 */     return data;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  56 */     super.func_70071_h_();
/*     */     
/*  58 */     if (getAnger() > 0) setAnger(getAnger() - 1);
/*  59 */     if ((this.field_70170_p.field_72995_K) && (this.field_70146_Z.nextInt(15) == 0) && (getAnger() > 0)) {
/*  60 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  61 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  62 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  63 */       this.field_70170_p.func_72869_a("angryVillager", this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N - this.field_70130_N / 2.0D, this.field_70121_D.field_72338_b + this.field_70131_O + this.field_70146_Z.nextFloat() * 0.5D, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N - this.field_70130_N / 2.0D, d0, d1, d2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  70 */     if ((!this.field_70170_p.field_72995_K) && 
/*  71 */       (this.field_70173_aa % 30 == 0))
/*     */     {
/*  73 */       func_70691_i(1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  82 */     super.func_70088_a();
/*  83 */     func_70096_w().func_75682_a(14, new Short((short)0));
/*     */   }
/*     */   
/*     */   public int getAnger()
/*     */   {
/*  88 */     return this.field_70180_af.func_75693_b(14);
/*     */   }
/*     */   
/*     */   public void setAnger(int par1)
/*     */   {
/*  93 */     this.field_70180_af.func_75692_b(14, Short.valueOf((short)par1));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 104 */     ArrayList<Entity> ents = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityTaintacleGiant.class, 48.0D);
/* 105 */     if ((ents == null) || (ents.size() <= 0)) {
/* 106 */       EntityUtils.entityDropSpecialItem(this, new ItemStack(ConfigItems.itemEldritchObject, 1, 3), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 112 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70648_aU()
/*     */   {
/* 118 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected int func_70682_h(int air)
/*     */   {
/* 124 */     return air;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 130 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 132 */       if (damage > 35.0F) {
/* 133 */         if (getAnger() == 0) {
/*     */           try {
/* 135 */             func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 200, (int)(damage / 15.0F)));
/* 136 */             func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 200, (int)(damage / 40.0F)));
/* 137 */             func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 200, (int)(damage / 40.0F)));
/* 138 */             setAnger(200);
/*     */           } catch (Exception e) {}
/* 140 */           if ((source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityPlayer))) {
/* 141 */             ((EntityPlayer)source.func_76346_g()).func_145747_a(new ChatComponentText(func_70005_c_() + " " + StatCollector.func_74838_a("tc.boss.enrage")));
/*     */           }
/*     */         }
/* 144 */         damage = 35.0F;
/*     */       }
/*     */     }
/*     */     
/* 148 */     return super.func_70097_a(source, damage);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/boss/EntityTaintacleGiant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */