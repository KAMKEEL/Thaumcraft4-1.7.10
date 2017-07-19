/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest2;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.village.VillageCollection;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ 
/*     */ public class EntityTaintVillager extends EntityMob implements ITaintedMob
/*     */ {
/*     */   private int randomTickDivider;
/*     */   private boolean isMatingFlag;
/*     */   private boolean isPlayingFlag;
/*     */   Village villageObj;
/*     */   
/*     */   public EntityTaintVillager(World par1World)
/*     */   {
/*  40 */     this(par1World, 0);
/*     */   }
/*     */   
/*     */   public EntityTaintVillager(World par1World, int par2)
/*     */   {
/*  45 */     super(par1World);
/*  46 */     this.randomTickDivider = 0;
/*  47 */     this.isMatingFlag = false;
/*  48 */     this.isPlayingFlag = false;
/*  49 */     this.villageObj = null;
/*  50 */     func_70661_as().func_75498_b(true);
/*  51 */     func_70661_as().func_75491_a(true);
/*  52 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  53 */     this.field_70714_bg.func_75776_a(2, new net.minecraft.entity.ai.EntityAIMoveIndoors(this));
/*  54 */     this.field_70714_bg.func_75776_a(2, new thaumcraft.common.entities.ai.combat.AIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  55 */     this.field_70714_bg.func_75776_a(3, new EntityAIRestrictOpenDoor(this));
/*  56 */     this.field_70714_bg.func_75776_a(4, new EntityAIOpenDoor(this, true));
/*  57 */     this.field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAIMoveTowardsRestriction(this, 1.0D));
/*  58 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
/*  59 */     this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
/*  60 */     this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityVillager.class, 5.0F, 0.02F));
/*  61 */     this.field_70714_bg.func_75776_a(9, new EntityAIWander(this, 1.0D));
/*  62 */     this.field_70714_bg.func_75776_a(10, new EntityAIWatchClosest(this, EntityLivingBase.class, 8.0F));
/*  63 */     this.field_70715_bh.func_75776_a(0, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  64 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  70 */     super.func_110147_ax();
/*  71 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*  72 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*  73 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/*  78 */     super.func_70636_d();
/*  79 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/*  80 */       for (int a = 0; a < Thaumcraft.proxy.particleCount(10); a++) {
/*  81 */         Thaumcraft.proxy.splooshFX(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/*  89 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70629_bd()
/*     */   {
/*  98 */     if (--this.randomTickDivider <= 0)
/*     */     {
/* 100 */       this.field_70170_p.field_72982_D.func_75551_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
/* 101 */       this.randomTickDivider = (70 + this.field_70146_Z.nextInt(50));
/* 102 */       this.villageObj = this.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v), 32);
/*     */       
/* 104 */       if (this.villageObj == null)
/*     */       {
/* 106 */         func_110177_bN();
/*     */       }
/*     */       else
/*     */       {
/* 110 */         ChunkCoordinates chunkcoordinates = this.villageObj.func_75577_a();
/* 111 */         func_110171_b(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c, (int)(this.villageObj.func_75568_b() * 0.6F));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 116 */     super.func_70629_bd();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/* 122 */     super.func_70088_a();
/* 123 */     this.field_70180_af.func_75682_a(16, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 134 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 143 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 153 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 159 */     return ConfigItems.itemResource;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 164 */     if (this.field_70170_p.field_73012_v.nextInt(2) == 0) {
/* 165 */       if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 166 */         func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*     */       } else
/* 168 */         func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0F);
/*     */     }
/* 170 */     if (this.field_70170_p.field_73012_v.nextInt(13) < 1 + i) { func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 18), 1.5F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 180 */     return "mob.villager.idle";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 189 */     return "mob.villager.hit";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 198 */     return "mob.villager.death";
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 203 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70604_c(EntityLivingBase par1EntityLiving)
/*     */   {
/* 209 */     super.func_70604_c(par1EntityLiving);
/*     */     
/* 211 */     if ((this.villageObj != null) && (par1EntityLiving != null))
/*     */     {
/* 213 */       this.villageObj.func_75575_a(par1EntityLiving);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintVillager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */