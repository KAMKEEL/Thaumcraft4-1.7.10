/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ 
/*     */ public class EntityTaintSporeSwarmer extends EntityTaintSpore
/*     */ {
/*     */   public EntityTaintSporeSwarmer(World par1World)
/*     */   {
/*  21 */     super(par1World);
/*  22 */     setSporeSize(10);
/*     */   }
/*     */   
/*     */   public void setSporeSize(int par1)
/*     */   {
/*  27 */     this.field_70180_af.func_75692_b(16, new Byte((byte)par1));
/*  28 */     func_70105_a(1.0F, 1.0F);
/*  29 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*  30 */     this.field_70728_aV = par1;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  36 */     super.func_110147_ax();
/*  37 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(75.0D);
/*  38 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
/*     */   }
/*     */   
/*  41 */   int spawnCounter = 500;
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/*  47 */     if (this.field_70170_p.field_72995_K) sploosh(10);
/*  48 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */   protected void sporeOnUpdate()
/*     */   {
/*  53 */     func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */     
/*     */ 
/*  56 */     if (this.spawnCounter > 0) this.spawnCounter -= 1;
/*  57 */     if ((this.spawnCounter <= 0) && (this.field_70170_p.func_72856_b(this, 16.0D) != null)) {
/*  58 */       this.spawnCounter = 500;
/*  59 */       swarmBurst(1);
/*     */     }
/*     */     
/*  62 */     if (this.field_70170_p.field_72995_K) {
/*  63 */       for (int a = 0; a < this.swarm.size(); a++) {
/*  64 */         if ((this.swarm.get(a) == null) || (((Entity)this.swarm.get(a)).field_70128_L)) {
/*  65 */           this.swarm.remove(a);
/*  66 */           break;
/*     */         }
/*     */       }
/*     */       
/*  70 */       if (this.swarm.size() < (500 - this.spawnCounter) / 25) {
/*  71 */         this.swarm.add(thaumcraft.common.Thaumcraft.proxy.swarmParticleFX(this.field_70170_p, this, 0.1F, 10.0F, 0.0F));
/*     */       }
/*     */     }
/*     */     
/*  75 */     if (this.field_70725_aQ == 1) {
/*  76 */       swarmBurst(1);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70100_b_(EntityPlayer par1EntityPlayer) {}
/*     */   
/*     */   protected void swarmBurst(int amt)
/*     */   {
/*  84 */     if (!this.field_70170_p.field_72995_K) {
/*  85 */       this.field_70170_p.func_72956_a(this, "thaumcraft:gore", 1.0F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.1F);
/*     */       
/*  87 */       for (int a = 0; a < amt; a++) {
/*  88 */         EntityTaintSwarm swarm = new EntityTaintSwarm(this.field_70170_p);
/*  89 */         swarm.func_70012_b(this.field_70165_t, this.field_70163_u + 0.5D, this.field_70161_v, this.field_70170_p.field_73012_v.nextFloat() * 360.0F, 0.0F);
/*  90 */         this.field_70170_p.func_72838_d(swarm);
/*     */       }
/*     */       
/*  93 */       this.field_70170_p.func_72960_a(this, (byte)6);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 100 */     if (par1 == 6)
/*     */     {
/* 102 */       this.spawnCounter = 500;
/* 103 */       sploosh(25);
/* 104 */       for (int a = 0; a < this.swarm.size(); a++) {
/* 105 */         ((Entity)this.swarm.get(a)).func_70106_y();
/*     */       }
/* 107 */       this.swarm.clear();
/*     */     }
/*     */     else
/*     */     {
/* 111 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 118 */     return 200;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 123 */     return "thaumcraft:roots";
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b(float par1)
/*     */   {
/* 129 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 130 */     int j = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 132 */     if (this.field_70170_p.func_72899_e(i, 0, j))
/*     */     {
/* 134 */       double d0 = (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * 0.66D;
/* 135 */       int k = MathHelper.func_76128_c(this.field_70163_u - this.field_70129_M + d0);
/* 136 */       return this.field_70170_p.func_72802_i(i, k, j, 0);
/*     */     }
/*     */     
/*     */ 
/* 140 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 149 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 150 */     int j = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 152 */     if (this.field_70170_p.func_72899_e(i, 0, j))
/*     */     {
/* 154 */       double d0 = (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * 0.66D;
/* 155 */       int k = MathHelper.func_76128_c(this.field_70163_u - this.field_70129_M + d0);
/* 156 */       return this.field_70170_p.func_72801_o(i, k, j);
/*     */     }
/*     */     
/*     */ 
/* 160 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected net.minecraft.item.Item func_146068_u()
/*     */   {
/* 167 */     return ConfigItems.itemResource;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 172 */     for (int a = 0; a <= 1; a++) {
/* 173 */       if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 174 */         func_70099_a(new net.minecraft.item.ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*     */       } else {
/* 176 */         func_70099_a(new net.minecraft.item.ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintSporeSwarmer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */