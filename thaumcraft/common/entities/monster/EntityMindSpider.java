/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityMindSpider extends EntitySpider
/*     */ {
/*     */   public EntityMindSpider(World par1World)
/*     */   {
/*  17 */     super(par1World);
/*  18 */     func_70105_a(0.3F, 0.3F);
/*  19 */     this.field_70728_aV = 1;
/*     */   }
/*     */   
/*  22 */   private int lifeSpan = Integer.MAX_VALUE;
/*     */   
/*     */   protected int func_70693_a(EntityPlayer p_70693_1_)
/*     */   {
/*  26 */     return isHarmless() ? 0 : super.func_70693_a(p_70693_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  32 */     super.func_110147_ax();
/*  33 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D);
/*  34 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  40 */     super.func_70088_a();
/*  41 */     this.field_70180_af.func_75682_a(22, new Byte((byte)0));
/*  42 */     this.field_70180_af.func_75682_a(23, new String(""));
/*     */   }
/*     */   
/*     */   public String getViewer()
/*     */   {
/*  47 */     return this.field_70180_af.func_75681_e(23);
/*     */   }
/*     */   
/*     */   public void setViewer(String player) {
/*  51 */     this.field_70180_af.func_75692_b(23, String.valueOf(player));
/*     */   }
/*     */   
/*     */   public boolean isHarmless()
/*     */   {
/*  56 */     return this.field_70180_af.func_75683_a(22) != 0;
/*     */   }
/*     */   
/*     */   public void setHarmless(boolean h) {
/*  60 */     if (h) this.lifeSpan = 1200;
/*  61 */     this.field_70180_af.func_75692_b(22, Byte.valueOf((byte)(h ? 1 : 0)));
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/*  66 */     return 0.7F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected Entity func_70782_k()
/*     */   {
/*  72 */     double d0 = 12.0D;
/*  73 */     return this.field_70170_p.func_72856_b(this, d0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public float spiderScaleAmount()
/*     */   {
/*  83 */     return 0.3F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  89 */     super.func_70071_h_();
/*  90 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70173_aa > this.lifeSpan)) { func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public float func_70053_R()
/*     */   {
/*  96 */     return isHarmless() ? 0.0F : 0.1F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 103 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {}
/*     */   
/*     */ 
/*     */   public boolean func_145773_az()
/*     */   {
/* 113 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 119 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_70785_a(Entity p_70785_1_, float p_70785_2_)
/*     */   {
/* 124 */     if (isHarmless()) {
/* 125 */       return;
/*     */     }
/* 127 */     super.func_70785_a(p_70785_1_, p_70785_2_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 134 */     super.func_70037_a(par1NBTTagCompound);
/* 135 */     this.field_70180_af.func_75692_b(22, Byte.valueOf(par1NBTTagCompound.func_74771_c("harmless")));
/* 136 */     this.field_70180_af.func_75692_b(23, String.valueOf(par1NBTTagCompound.func_74779_i("viewer")));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 145 */     super.func_70014_b(par1NBTTagCompound);
/* 146 */     par1NBTTagCompound.func_74774_a("harmless", this.field_70180_af.func_75683_a(22));
/* 147 */     par1NBTTagCompound.func_74778_a("viewer", this.field_70180_af.func_75681_e(23));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityMindSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */