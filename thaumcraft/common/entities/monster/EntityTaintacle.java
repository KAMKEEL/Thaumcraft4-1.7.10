/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class EntityTaintacle extends EntityMob implements ITaintedMob
/*     */ {
/*  29 */   public float flailIntensity = 1.0F;
/*     */   
/*     */   public EntityTaintacle(World par1World)
/*     */   {
/*  33 */     super(par1World);
/*  34 */     func_70105_a(0.66F, 3.0F);
/*  35 */     this.field_70728_aV = 10;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/*  43 */     int var1 = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/*  44 */     int var2 = MathHelper.func_76128_c(this.field_70165_t);
/*  45 */     int var3 = MathHelper.func_76128_c(this.field_70161_v);
/*  46 */     int var4 = this.field_70170_p.func_72957_l(var2, var1, var3);
/*  47 */     byte var5 = 7;
/*     */     
/*  49 */     List ents = this.field_70170_p.func_72872_a(EntityTaintacle.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t, this.field_70163_u, this.field_70161_v).func_72314_b(24.0D, 8.0D, 24.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  54 */     boolean onTaint = ((this.field_70170_p.func_147439_a(var2, var1, var3) == ConfigBlocks.blockTaintFibres) && (this.field_70170_p.func_72805_g(var2, var1, var3) == 0)) || ((this.field_70170_p.func_147439_a(var2, var1, var3) == ConfigBlocks.blockTaint) && (this.field_70170_p.func_72805_g(var2, var1, var3) == 1) && (this.field_70170_p.func_72807_a(var2, var3).field_76756_M == Config.biomeTaintID));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  59 */     return (ents.size() > 0) || (!onTaint) ? false : super.func_70601_bi();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float func_70053_R()
/*     */   {
/*  66 */     return 0.25F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  72 */     super.func_110147_ax();
/*  73 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/*  74 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
/*     */   }
/*     */   
/*     */   public boolean func_70067_L()
/*     */   {
/*  79 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70104_M()
/*     */   {
/*  84 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected Entity func_70782_k()
/*     */   {
/*  90 */     Entity entity = null;
/*     */     
/*  92 */     List ents = this.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t, this.field_70163_u, this.field_70161_v).func_72314_b(this.field_70131_O * 6.0F, this.field_70131_O * 3.0F, this.field_70131_O * 6.0F));
/*     */     
/*     */ 
/*     */     double distance;
/*     */     
/*  97 */     if (ents.size() > 0) {
/*  98 */       distance = Double.MAX_VALUE;
/*  99 */       for (Object ent : ents) {
/* 100 */         if (ent != null) {
/* 101 */           EntityLivingBase el = (EntityLivingBase)ent;
/* 102 */           double d = el.func_70068_e(this);
/* 103 */           if ((!(el instanceof ITaintedMob)) && (d < distance)) {
/* 104 */             distance = d;
/* 105 */             entity = el;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 112 */     return entity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70091_d(double par1, double par3, double par5)
/*     */   {
/* 119 */     par1 = 0.0D;
/* 120 */     par5 = 0.0D;
/* 121 */     if (par3 > 0.0D) par3 = 0.0D;
/* 122 */     super.func_70091_d(par1, par3, par5);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70626_be()
/*     */   {
/* 128 */     if (this.field_70789_a != null)
/*     */     {
/* 130 */       faceEntity(this.field_70789_a, 5.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 137 */     super.func_70071_h_();
/*     */     
/* 139 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70173_aa % 20 == 0) && (this.field_70170_p.func_72807_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70161_v)).field_76756_M != Config.biomeTaintID))
/*     */     {
/*     */ 
/*     */ 
/* 143 */       func_70665_d(DamageSource.field_76366_f, 1.0F);
/*     */     }
/*     */     
/* 146 */     if (this.field_70170_p.field_72995_K) {
/* 147 */       if ((this.field_70173_aa > this.field_70131_O * 10.0F) && ((this.field_70737_aN > 0) || (this.field_70724_aR > 0) || ((this.field_70789_a != null) && (this.field_70789_a.func_70032_d(this) < this.field_70131_O))))
/*     */       {
/*     */ 
/* 150 */         if (this.flailIntensity < 3.0F) this.flailIntensity += 0.2F;
/*     */       }
/* 152 */       else if (this.flailIntensity > 1.0F) { this.flailIntensity -= 0.2F;
/*     */       }
/*     */       
/* 155 */       if ((this.field_70173_aa < this.field_70131_O * 10.0F) && (this.field_70122_E)) {
/* 156 */         Thaumcraft.proxy.tentacleAriseFX(this);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 162 */     if (this.field_70789_a == null)
/*     */     {
/* 164 */       this.field_70789_a = func_70782_k();
/*     */     }
/* 166 */     else if ((this.field_70789_a.func_70089_S()) && (getAgitationState()))
/*     */     {
/* 168 */       float f1 = this.field_70789_a.func_70032_d(this);
/* 169 */       if ((!this.field_70170_p.field_72995_K) && 
/* 170 */         (func_70685_l(this.field_70789_a)))
/*     */       {
/* 172 */         func_70785_a(this.field_70789_a, f1);
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 178 */       this.field_70789_a = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70785_a(Entity entity, float par2)
/*     */   {
/* 188 */     if (this.field_70724_aR <= 0)
/*     */     {
/* 190 */       if ((par2 <= this.field_70131_O) && (entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b) && (entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e)) {
/* 191 */         this.field_70724_aR = 20;
/* 192 */         func_70652_k(entity);
/* 193 */         func_85030_a("thaumcraft:tentacle", func_70599_aP(), func_70647_i());
/* 194 */       } else if ((par2 > this.field_70131_O) && (entity.field_70122_E) && (!(this instanceof EntityTaintacleSmall))) {
/* 195 */         spawnTentacles(entity);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 203 */     float i = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/*     */     
/* 205 */     if (func_70644_a(Potion.field_76420_g))
/*     */     {
/* 207 */       i += (3 << func_70660_b(Potion.field_76420_g).func_76458_c());
/*     */     }
/*     */     
/* 210 */     if (func_70644_a(Potion.field_76437_t))
/*     */     {
/* 212 */       i -= (2 << func_70660_b(Potion.field_76437_t).func_76458_c());
/*     */     }
/*     */     
/* 215 */     int j = 0;
/*     */     
/* 217 */     if ((par1Entity instanceof EntityLivingBase))
/*     */     {
/* 219 */       i += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)par1Entity);
/* 220 */       j += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)par1Entity);
/*     */     }
/*     */     
/* 223 */     boolean flag = par1Entity.func_70097_a(thaumcraft.api.damagesource.DamageSourceThaumcraft.causeTentacleDamage(this), i);
/*     */     
/* 225 */     if (flag)
/*     */     {
/* 227 */       if (j > 0)
/*     */       {
/* 229 */         par1Entity.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * j * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * j * 0.5F);
/* 230 */         this.field_70159_w *= 0.6D;
/* 231 */         this.field_70179_y *= 0.6D;
/*     */       }
/*     */       
/* 234 */       int k = EnchantmentHelper.func_90036_a(this);
/*     */       
/* 236 */       if (k > 0)
/*     */       {
/* 238 */         par1Entity.func_70015_d(k * 4);
/*     */       }
/*     */       
/* 241 */       if ((par1Entity instanceof EntityLivingBase))
/*     */       {
/* 243 */         EnchantmentHelper.func_151384_a((EntityLivingBase)par1Entity, this);
/*     */       }
/*     */       
/* 246 */       EnchantmentHelper.func_151385_b(this, par1Entity);
/*     */     }
/*     */     
/*     */ 
/* 250 */     return flag;
/*     */   }
/*     */   
/*     */   protected void spawnTentacles(Entity entity) {
/* 254 */     int i = MathHelper.func_76128_c(entity.field_70165_t);
/* 255 */     int j = MathHelper.func_76128_c(entity.field_70121_D.field_72338_b);
/* 256 */     int k = MathHelper.func_76128_c(entity.field_70161_v);
/* 257 */     if ((this.field_70170_p.func_72807_a(i, k).field_76756_M == Config.biomeEldritchID) || ((this.field_70170_p.func_72807_a(i, k).field_76756_M == Config.biomeTaintID) && ((this.field_70170_p.func_147439_a(i, j, k).func_149688_o() == Config.taintMaterial) || (this.field_70170_p.func_147439_a(i, j, k).func_149688_o() == Config.taintMaterial) || (this.field_70170_p.func_147439_a(i, j - 1, k).func_149688_o() == Config.taintMaterial))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 262 */       this.field_70724_aR = (40 + this.field_70170_p.field_73012_v.nextInt(20));
/* 263 */       EntityTaintacleSmall taintlet = new EntityTaintacleSmall(this.field_70170_p);
/* 264 */       taintlet.func_70012_b(entity.field_70165_t + this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat(), entity.field_70163_u, entity.field_70161_v + this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat(), 0.0F, 0.0F);
/*     */       
/*     */ 
/*     */ 
/* 268 */       this.field_70170_p.func_72838_d(taintlet);
/* 269 */       func_85030_a("thaumcraft:tentacle", func_70599_aP(), func_70647_i());
/* 270 */       if ((this.field_70170_p.func_72807_a(i, k).field_76756_M == Config.biomeEldritchID) && (this.field_70170_p.func_147437_c(i, j, k)) && (thaumcraft.common.lib.utils.BlockUtils.isAdjacentToSolidBlock(this.field_70170_p, i, j, k)))
/*     */       {
/* 272 */         thaumcraft.common.lib.utils.Utils.setBiomeAt(this.field_70170_p, i, k, ThaumcraftWorldGenerator.biomeTaint);
/* 273 */         this.field_70170_p.func_147465_d(i, j, k, ConfigBlocks.blockTaintFibres, this.field_70170_p.field_73012_v.nextInt(4) == 0 ? 1 : 0, 3);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource ds, float par2)
/*     */   {
/* 282 */     if ((!(this instanceof EntityTaintacleSmall)) && (ds.func_76346_g() != null) && (func_70032_d(ds.func_76346_g()) > 16.0F))
/*     */     {
/* 284 */       if (!this.field_70170_p.field_72995_K) spawnTentacles(ds.func_76346_g());
/*     */     }
/* 286 */     return super.func_70097_a(ds, par2);
/*     */   }
/*     */   
/*     */   public boolean getAgitationState()
/*     */   {
/* 291 */     return (this.field_70789_a != null) && (this.field_70789_a.func_70068_e(this) < this.field_70131_O * 7.0F * (this.field_70131_O * 7.0F));
/*     */   }
/*     */   
/*     */ 
/*     */   public void faceEntity(Entity par1Entity, float par2)
/*     */   {
/* 297 */     double d0 = par1Entity.field_70165_t - this.field_70165_t;
/* 298 */     double d1 = par1Entity.field_70161_v - this.field_70161_v;
/*     */     
/*     */ 
/* 301 */     float f2 = (float)(Math.atan2(d1, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 302 */     this.field_70177_z = func_70663_b(this.field_70177_z, f2, par2);
/*     */   }
/*     */   
/*     */   protected float func_70663_b(float par1, float par2, float par3)
/*     */   {
/* 307 */     float f3 = MathHelper.func_76142_g(par2 - par1);
/*     */     
/* 309 */     if (f3 > par3)
/*     */     {
/* 311 */       f3 = par3;
/*     */     }
/*     */     
/* 314 */     if (f3 < -par3)
/*     */     {
/* 316 */       f3 = -par3;
/*     */     }
/*     */     
/* 319 */     return par1 + f3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 326 */     return 200;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 333 */     return "thaumcraft:roots";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected float func_70647_i()
/*     */   {
/* 340 */     return 1.3F - this.field_70131_O / 10.0F;
/*     */   }
/*     */   
/*     */   protected float func_70599_aP()
/*     */   {
/* 345 */     return this.field_70131_O / 8.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 352 */     return "thaumcraft:tentacle";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 359 */     return "thaumcraft:tentacle";
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 364 */     if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 365 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*     */     } else {
/* 367 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0F);
/*     */     }
/* 369 */     super.func_70628_a(flag, i);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintacle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */