/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*     */ import thaumcraft.common.entities.ai.misc.AIConvertGrass;
/*     */ 
/*     */ public class EntityTaintSheep extends EntityMob implements IShearable, ITaintedMob
/*     */ {
/*     */   private int sheepTimer;
/*  36 */   private AIConvertGrass field_48137_c = new AIConvertGrass(this);
/*     */   
/*     */   public EntityTaintSheep(World par1World)
/*     */   {
/*  40 */     super(par1World);
/*  41 */     func_70105_a(0.9F, 1.3F);
/*  42 */     func_70661_as().func_75491_a(true);
/*  43 */     this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
/*  44 */     this.field_70714_bg.func_75776_a(2, this.field_48137_c);
/*  45 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  46 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  47 */     this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
/*  48 */     this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  49 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  50 */     this.field_70715_bh.func_75776_a(0, new EntityAIHurtByTarget(this, false));
/*  51 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  52 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  58 */     super.func_110147_ax();
/*  59 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/*  60 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
/*  61 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70650_aV()
/*     */   {
/*  70 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/*  75 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  84 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70619_bc()
/*     */   {
/*  90 */     this.sheepTimer = this.field_48137_c.func_48396_h();
/*  91 */     super.func_70619_bc();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 100 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/* 102 */       this.sheepTimer = Math.max(0, this.sheepTimer - 1);
/*     */     }
/*     */     
/* 105 */     super.func_70636_d();
/* 106 */     if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa < 5)) {
/* 107 */       for (int a = 0; a < Thaumcraft.proxy.particleCount(10); a++) {
/* 108 */         Thaumcraft.proxy.splooshFX(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70088_a() {
/* 114 */     super.func_70088_a();
/* 115 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 121 */     return ConfigItems.itemResource;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 126 */     if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 127 */       if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 128 */         func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*     */       } else
/* 130 */         func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70103_a(byte par1) {
/* 135 */     if (par1 == 10)
/*     */     {
/* 137 */       this.sheepTimer = 40;
/*     */     }
/*     */     else
/*     */     {
/* 141 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public float func_44003_c(float par1)
/*     */   {
/* 147 */     return this.sheepTimer < 4 ? (this.sheepTimer - par1) / 4.0F : (this.sheepTimer >= 4) && (this.sheepTimer <= 36) ? 1.0F : this.sheepTimer <= 0 ? 0.0F : -(this.sheepTimer - 40 - par1) / 4.0F;
/*     */   }
/*     */   
/*     */   public float func_44002_d(float par1)
/*     */   {
/* 152 */     if ((this.sheepTimer > 4) && (this.sheepTimer <= 36))
/*     */     {
/* 154 */       float var2 = (this.sheepTimer - 4 - par1) / 32.0F;
/* 155 */       return 0.62831855F + 0.2199115F * MathHelper.func_76126_a(var2 * 28.7F);
/*     */     }
/*     */     
/*     */ 
/* 159 */     return this.sheepTimer > 0 ? 0.62831855F : this.field_70125_A / 57.295776F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70085_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 168 */     return super.func_70085_c(par1EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 176 */     super.func_70014_b(par1NBTTagCompound);
/* 177 */     par1NBTTagCompound.func_74757_a("Sheared", getSheared());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 185 */     super.func_70037_a(par1NBTTagCompound);
/* 186 */     setSheared(par1NBTTagCompound.func_74767_n("Sheared"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 194 */     return "mob.sheep.say";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 202 */     return "mob.sheep.say";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 210 */     return "mob.sheep.say";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4)
/*     */   {
/* 218 */     func_85030_a("mob.sheep.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 223 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getSheared()
/*     */   {
/* 231 */     return (this.field_70180_af.func_75683_a(16) & 0x10) != 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSheared(boolean par1)
/*     */   {
/* 239 */     byte var2 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 241 */     if (par1)
/*     */     {
/* 243 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 | 0x10)));
/*     */     }
/*     */     else
/*     */     {
/* 247 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 & 0xFFFFFFEF)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isShearable(ItemStack item, IBlockAccess world, int X, int Y, int Z)
/*     */   {
/* 256 */     return !getSheared();
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int X, int Y, int Z, int fortune)
/*     */   {
/* 262 */     ArrayList<ItemStack> ret = new ArrayList();
/* 263 */     setSheared(true);
/* 264 */     int i = 1 + this.field_70146_Z.nextInt(3);
/* 265 */     for (int j = 0; j < i; j++)
/*     */     {
/* 267 */       ret.add(new ItemStack(net.minecraft.init.Blocks.field_150325_L, 1, 10));
/*     */     }
/* 269 */     return ret;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintSheep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */