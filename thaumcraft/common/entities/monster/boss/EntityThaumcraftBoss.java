/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ 
/*     */ public class EntityThaumcraftBoss extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData
/*     */ {
/*     */   public EntityThaumcraftBoss(World world)
/*     */   {
/*  31 */     super(world);
/*  32 */     this.field_70728_aV = 50;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/*  38 */     super.func_70037_a(nbt);
/*  39 */     if (nbt.func_74764_b("HomeD")) {
/*  40 */       func_110171_b(nbt.func_74762_e("HomeX"), nbt.func_74762_e("HomeY"), nbt.func_74762_e("HomeZ"), nbt.func_74762_e("HomeD"));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/*  51 */     super.func_70014_b(nbt);
/*  52 */     if ((func_110172_bL() != null) && (func_110174_bM() > 0.0F)) {
/*  53 */       nbt.func_74768_a("HomeD", (int)func_110174_bM());
/*  54 */       nbt.func_74768_a("HomeX", func_110172_bL().field_71574_a);
/*  55 */       nbt.func_74768_a("HomeY", func_110172_bL().field_71572_b);
/*  56 */       nbt.func_74768_a("HomeZ", func_110172_bL().field_71573_c);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  63 */     super.func_110147_ax();
/*  64 */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.95D);
/*  65 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  71 */     super.func_70088_a();
/*  72 */     func_70096_w().func_75682_a(14, new Short((short)0));
/*     */   }
/*     */   
/*  75 */   HashMap<Integer, Integer> aggro = new HashMap();
/*     */   
/*     */   protected void func_70619_bc()
/*     */   {
/*  79 */     if (getSpawnTimer() == 0) {
/*  80 */       super.func_70619_bc();
/*     */     }
/*  82 */     if ((func_70638_az() != null) && (func_70638_az().field_70128_L)) {
/*  83 */       func_70624_b(null);
/*     */     }
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData data) {
/*  88 */     func_110171_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 24);
/*  89 */     return data;
/*     */   }
/*     */   
/*     */   public int getAnger()
/*     */   {
/*  94 */     return this.field_70180_af.func_75693_b(14);
/*     */   }
/*     */   
/*     */   public void setAnger(int par1)
/*     */   {
/*  99 */     this.field_70180_af.func_75692_b(14, Short.valueOf((short)par1));
/*     */   }
/*     */   
/* 102 */   int spawnTimer = 0;
/*     */   
/*     */   public int getSpawnTimer() {
/* 105 */     return this.spawnTimer;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 110 */     super.func_70071_h_();
/* 111 */     if (getSpawnTimer() > 0) { this.spawnTimer -= 1;
/*     */     }
/* 113 */     if (getAnger() > 0) setAnger(getAnger() - 1);
/* 114 */     if ((this.field_70170_p.field_72995_K) && (this.field_70146_Z.nextInt(15) == 0) && (getAnger() > 0)) {
/* 115 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 116 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 117 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 118 */       this.field_70170_p.func_72869_a("angryVillager", this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N - this.field_70130_N / 2.0D, this.field_70121_D.field_72338_b + this.field_70131_O + this.field_70146_Z.nextFloat() * 0.5D, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N - this.field_70130_N / 2.0D, d0, d1, d2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 125 */     if (!this.field_70170_p.field_72995_K) {
/* 126 */       if (this.field_70173_aa % 30 == 0)
/*     */       {
/* 128 */         func_70691_i(1.0F);
/*     */       }
/*     */       
/* 131 */       if ((func_70638_az() != null) && (this.field_70173_aa % 20 == 0)) {
/* 132 */         ArrayList<Integer> dl = new ArrayList();
/* 133 */         int players = 0;
/* 134 */         int hei = func_70638_az().func_145782_y();
/* 135 */         int ad = this.aggro.containsKey(Integer.valueOf(hei)) ? ((Integer)this.aggro.get(Integer.valueOf(hei))).intValue() : 0;
/* 136 */         int ld = ad;
/* 137 */         Entity newTarget = null;
/* 138 */         for (Integer ei : this.aggro.keySet()) {
/* 139 */           int ca = ((Integer)this.aggro.get(ei)).intValue();
/* 140 */           if ((ca > ad + 25) && (ca > ad * 1.1D) && (ca > ld)) {
/* 141 */             newTarget = this.field_70170_p.func_73045_a(hei);
/* 142 */             if ((newTarget == null) || (newTarget.field_70128_L) || (func_70068_e(newTarget) > 16384.0D)) {
/* 143 */               dl.add(ei);
/*     */             } else {
/* 145 */               hei = ei.intValue();
/* 146 */               ld = ei.intValue();
/* 147 */               if ((newTarget instanceof EntityPlayer)) {
/* 148 */                 players++;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         Integer ei;
/* 154 */         for (Iterator i$ = dl.iterator(); i$.hasNext(); this.aggro.remove(ei)) { ei = (Integer)i$.next();
/*     */         }
/* 156 */         if ((newTarget != null) && (hei != func_70638_az().func_145782_y())) {
/* 157 */           func_70624_b((EntityLivingBase)newTarget);
/*     */         }
/*     */         
/* 160 */         float om = func_110138_aP();
/*     */         
/* 162 */         IAttributeInstance iattributeinstance = func_110148_a(SharedMonsterAttributes.field_111267_a);
/* 163 */         IAttributeInstance iattributeinstance2 = func_110148_a(SharedMonsterAttributes.field_111264_e);
/*     */         
/* 165 */         for (int a = 0; a < 5; a++) {
/* 166 */           iattributeinstance2.func_111124_b(thaumcraft.common.lib.utils.EntityUtils.DMGBUFF[a]);
/* 167 */           iattributeinstance.func_111124_b(thaumcraft.common.lib.utils.EntityUtils.HPBUFF[a]);
/*     */         }
/*     */         
/* 170 */         for (int a = 0; a < Math.min(5, players - 1); a++) {
/* 171 */           iattributeinstance.func_111121_a(thaumcraft.common.lib.utils.EntityUtils.HPBUFF[a]);
/* 172 */           iattributeinstance2.func_111121_a(thaumcraft.common.lib.utils.EntityUtils.DMGBUFF[a]);
/*     */         }
/*     */         
/* 175 */         double mm = func_110138_aP() / om;
/* 176 */         func_70606_j((float)(func_110143_aJ() * mm));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_85032_ar()
/*     */   {
/* 183 */     return (super.func_85032_ar()) || (getSpawnTimer() > 0);
/*     */   }
/*     */   
/*     */   public boolean func_70648_aU()
/*     */   {
/* 188 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70104_M()
/*     */   {
/* 193 */     return (super.func_70104_M()) && (!func_85032_ar());
/*     */   }
/*     */   
/*     */ 
/*     */   protected int func_70682_h(int air)
/*     */   {
/* 199 */     return air;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70110_aj() {}
/*     */   
/*     */   public boolean func_98052_bS()
/*     */   {
/* 207 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70650_aV()
/*     */   {
/* 213 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_82164_bB() {}
/*     */   
/*     */ 
/*     */   protected void func_82162_bC() {}
/*     */   
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 225 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_142014_c(EntityLivingBase el)
/*     */   {
/* 230 */     return el instanceof IEldritchMob;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70628_a(boolean flag, int fortune)
/*     */   {
/* 238 */     thaumcraft.common.lib.utils.EntityUtils.entityDropSpecialItem(this, new ItemStack(ConfigItems.itemEldritchObject, 1, 3), this.field_70131_O / 2.0F);
/* 239 */     func_70099_a(new ItemStack(ConfigItems.itemLootbag, 1, 2), 1.5F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70600_l(int fortune)
/*     */   {
/* 245 */     super.func_70600_l(fortune);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 251 */     if (!this.field_70170_p.field_72995_K) {
/* 252 */       if ((source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLivingBase))) {
/* 253 */         int target = source.func_76346_g().func_145782_y();
/* 254 */         int ad = (int)damage;
/* 255 */         if (this.aggro.containsKey(Integer.valueOf(target))) {
/* 256 */           ad += ((Integer)this.aggro.get(Integer.valueOf(target))).intValue();
/*     */         }
/* 258 */         this.aggro.put(Integer.valueOf(target), Integer.valueOf(ad));
/*     */       }
/*     */       
/* 261 */       if (damage > 35.0F) {
/* 262 */         if (getAnger() == 0) {
/*     */           try {
/* 264 */             func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 200, (int)(damage / 15.0F)));
/* 265 */             func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 200, (int)(damage / 40.0F)));
/* 266 */             func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 200, (int)(damage / 40.0F)));
/* 267 */             setAnger(200);
/*     */           } catch (Exception e) {}
/* 269 */           if ((source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityPlayer))) {
/* 270 */             ((EntityPlayer)source.func_76346_g()).func_145747_a(new ChatComponentText(func_70005_c_() + " " + net.minecraft.util.StatCollector.func_74838_a("tc.boss.enrage")));
/*     */           }
/*     */         }
/* 273 */         damage = 35.0F;
/*     */       }
/*     */     }
/*     */     
/* 277 */     return super.func_70097_a(source, damage);
/*     */   }
/*     */   
/*     */   public void generateName() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/boss/EntityThaumcraftBoss.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */