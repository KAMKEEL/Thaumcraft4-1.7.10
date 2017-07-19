/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*     */ import thaumcraft.common.entities.ai.combat.AICreeperSwell;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ 
/*     */ public class EntityTaintCreeper
/*     */   extends EntityMob
/*     */   implements ITaintedMob
/*     */ {
/*     */   private int lastActiveTime;
/*     */   private int timeSinceIgnited;
/*  46 */   private int fuseTime = 30;
/*     */   
/*     */ 
/*  49 */   private int explosionRadius = 3;
/*     */   
/*     */   public EntityTaintCreeper(World par1World)
/*     */   {
/*  53 */     super(par1World);
/*  54 */     this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
/*  55 */     this.field_70714_bg.func_75776_a(2, new AICreeperSwell(this));
/*  56 */     this.field_70714_bg.func_75776_a(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
/*  57 */     this.field_70714_bg.func_75776_a(4, new AIAttackOnCollide(this, 1.0D, false));
/*  58 */     this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
/*  59 */     this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  60 */     this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
/*  61 */     this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  62 */     this.field_70715_bh.func_75776_a(2, new EntityAIHurtByTarget(this, false));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  68 */     super.func_110147_ax();
/*  69 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/*  70 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*  71 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
/*  72 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/*  78 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/*  83 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_82143_as()
/*     */   {
/*  89 */     return func_70638_az() == null ? 3 : 3 + (int)(func_110143_aJ() - 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70069_a(float par1)
/*     */   {
/*  95 */     super.func_70069_a(par1);
/*  96 */     this.timeSinceIgnited = ((int)(this.timeSinceIgnited + par1 * 1.5F));
/*     */     
/*  98 */     if (this.timeSinceIgnited > this.fuseTime - 5)
/*     */     {
/* 100 */       this.timeSinceIgnited = (this.fuseTime - 5);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 107 */     super.func_70014_b(par1NBTTagCompound);
/*     */     
/* 109 */     if (this.field_70180_af.func_75683_a(17) == 1)
/*     */     {
/* 111 */       par1NBTTagCompound.func_74757_a("powered", true);
/*     */     }
/*     */     
/* 114 */     par1NBTTagCompound.func_74777_a("Fuse", (short)this.fuseTime);
/* 115 */     par1NBTTagCompound.func_74774_a("ExplosionRadius", (byte)this.explosionRadius);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 122 */     super.func_70037_a(par1NBTTagCompound);
/* 123 */     this.field_70180_af.func_75692_b(17, Byte.valueOf((byte)(par1NBTTagCompound.func_74767_n("powered") ? 1 : 0)));
/*     */     
/* 125 */     if (par1NBTTagCompound.func_74764_b("Fuse"))
/*     */     {
/* 127 */       this.fuseTime = par1NBTTagCompound.func_74765_d("Fuse");
/*     */     }
/*     */     
/* 130 */     if (par1NBTTagCompound.func_74764_b("ExplosionRadius"))
/*     */     {
/* 132 */       this.explosionRadius = par1NBTTagCompound.func_74771_c("ExplosionRadius");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/* 139 */     super.func_70088_a();
/* 140 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)-1));
/* 141 */     this.field_70180_af.func_75682_a(17, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 148 */     return ConfigItems.itemResource;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 153 */     if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 154 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*     */     } else {
/* 156 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 165 */     if (func_70089_S())
/*     */     {
/* 167 */       this.lastActiveTime = this.timeSinceIgnited;
/* 168 */       int var1 = getCreeperState();
/*     */       
/* 170 */       if ((var1 > 0) && (this.timeSinceIgnited == 0))
/*     */       {
/* 172 */         this.field_70170_p.func_72956_a(this, "random.fuse", 1.0F, 0.5F);
/*     */       }
/*     */       
/* 175 */       this.timeSinceIgnited += var1;
/*     */       
/* 177 */       if (this.timeSinceIgnited < 0)
/*     */       {
/* 179 */         this.timeSinceIgnited = 0;
/*     */       }
/*     */       
/* 182 */       if (this.timeSinceIgnited >= 30)
/*     */       {
/* 184 */         this.timeSinceIgnited = 30;
/*     */         
/* 186 */         if (!this.field_70170_p.field_72995_K)
/*     */         {
/* 188 */           this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u + this.field_70131_O / 2.0F, this.field_70161_v, 1.5F, false);
/* 189 */           List ents = this.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t, this.field_70163_u, this.field_70161_v).func_72314_b(6.0D, 6.0D, 6.0D));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 194 */           if (ents.size() > 0) {
/* 195 */             for (Object ent : ents) {
/* 196 */               EntityLivingBase el = (EntityLivingBase)ent;
/* 197 */               if ((!(el instanceof ITaintedMob)) && (!el.func_70662_br())) {
/* 198 */                 el.func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 100, 0, false));
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 203 */           int x = (int)this.field_70165_t;
/* 204 */           int y = (int)this.field_70163_u;
/* 205 */           int z = (int)this.field_70161_v;
/* 206 */           for (int a = 0; a < 10; a++) {
/* 207 */             int xx = x + (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 5.0F);
/* 208 */             int zz = z + (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 5.0F);
/* 209 */             if ((this.field_70170_p.field_73012_v.nextBoolean()) && (this.field_70170_p.func_72807_a(xx, zz) != ThaumcraftWorldGenerator.biomeTaint))
/*     */             {
/* 211 */               Utils.setBiomeAt(this.field_70170_p, xx, zz, ThaumcraftWorldGenerator.biomeTaint);
/*     */               
/*     */ 
/* 214 */               if ((this.field_70170_p.func_147445_c(xx, y - 1, zz, false)) && (this.field_70170_p.func_147439_a(xx, y, zz).isReplaceable(this.field_70170_p, xx, y, zz)))
/*     */               {
/* 216 */                 this.field_70170_p.func_147465_d(xx, y, zz, ConfigBlocks.blockTaintFibres, 0, 3);
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 222 */           func_70106_y();
/*     */         } else {
/* 224 */           for (int a = 0; a < Thaumcraft.proxy.particleCount(100); a++) { Thaumcraft.proxy.taintsplosionFX(this);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 229 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 234 */     super.func_70636_d();
/* 235 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/* 236 */       for (int a = 0; a < Thaumcraft.proxy.particleCount(10); a++)
/* 237 */         Thaumcraft.proxy.splooshFX(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public float getCreeperFlashIntensity(float par1) {
/* 242 */     return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * par1) / 28.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 251 */     return "mob.creeper.say";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 260 */     return "mob.creeper.death";
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 265 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 271 */     return true;
/*     */   }
/*     */   
/*     */   public int getCreeperState()
/*     */   {
/* 276 */     return this.field_70180_af.func_75683_a(16);
/*     */   }
/*     */   
/*     */   public void setCreeperState(int par1)
/*     */   {
/* 281 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)par1));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintCreeper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */