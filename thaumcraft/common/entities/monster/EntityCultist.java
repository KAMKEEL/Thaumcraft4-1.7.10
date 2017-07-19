/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
/*     */ 
/*     */ public class EntityCultist extends EntityMob
/*     */ {
/*     */   public EntityCultist(World p_i1745_1_)
/*     */   {
/*  21 */     super(p_i1745_1_);
/*  22 */     func_70105_a(0.6F, 1.8F);
/*  23 */     this.field_70728_aV = 10;
/*  24 */     func_70661_as().func_75498_b(true);
/*  25 */     func_70661_as().func_75491_a(true);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  31 */     super.func_110147_ax();
/*  32 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
/*  33 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*  34 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  40 */     super.func_70088_a();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_98052_bS()
/*     */   {
/*  48 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70650_aV()
/*     */   {
/*  57 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/*  65 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/*  70 */     int r = this.field_70146_Z.nextInt(10);
/*  71 */     if (r == 0) {
/*  72 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 9), 1.5F);
/*     */     }
/*  74 */     else if (r <= 1) {
/*  75 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 17), 1.5F);
/*     */     }
/*  77 */     else if (r <= 3 + i) {
/*  78 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 18), 1.5F);
/*     */     }
/*  80 */     super.func_70628_a(flag, i);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70600_l(int p_70600_1_)
/*     */   {
/*  86 */     func_70099_a(new ItemStack(ConfigItems.itemEldritchObject, 1, 1), 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_82164_bB() {}
/*     */   
/*     */ 
/*     */   protected void func_82162_bC() {}
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
/*     */   {
/*  98 */     func_82164_bB();
/*  99 */     func_82162_bC();
/*     */     
/* 101 */     return super.func_110161_a(p_110161_1_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 110 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70652_k(Entity p_70652_1_)
/*     */   {
/* 117 */     return super.func_70652_k(p_70652_1_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/* 126 */     super.func_70037_a(nbt);
/* 127 */     if (nbt.func_74764_b("HomeD")) {
/* 128 */       func_110171_b(nbt.func_74762_e("HomeX"), nbt.func_74762_e("HomeY"), nbt.func_74762_e("HomeZ"), nbt.func_74762_e("HomeD"));
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
/* 139 */     super.func_70014_b(nbt);
/* 140 */     if ((func_110172_bL() != null) && (func_110174_bM() > 0.0F)) {
/* 141 */       nbt.func_74768_a("HomeD", (int)func_110174_bM());
/* 142 */       nbt.func_74768_a("HomeX", func_110172_bL().field_71574_a);
/* 143 */       nbt.func_74768_a("HomeY", func_110172_bL().field_71572_b);
/* 144 */       nbt.func_74768_a("HomeZ", func_110172_bL().field_71573_c);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_142014_c(net.minecraft.entity.EntityLivingBase el)
/*     */   {
/* 151 */     return ((el instanceof EntityCultist)) || ((el instanceof EntityCultistLeader));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70686_a(Class clazz)
/*     */   {
/* 157 */     if ((clazz == EntityCultistCleric.class) || (clazz == EntityCultistLeader.class) || (clazz == EntityCultistKnight.class))
/*     */     {
/*     */ 
/* 160 */       return false; }
/* 161 */     return super.func_70686_a(clazz);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityCultist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */