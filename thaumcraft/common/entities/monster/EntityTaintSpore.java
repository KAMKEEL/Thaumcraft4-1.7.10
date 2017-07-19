/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ 
/*     */ public class EntityTaintSpore extends EntityMob implements ITaintedMob, cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*  30 */   public ArrayList swarm = new ArrayList();
/*     */   
/*     */   public EntityTaintSpore(World par1World)
/*     */   {
/*  34 */     super(par1World);
/*  35 */     setSporeSize(2);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  41 */     super.func_70088_a();
/*  42 */     this.field_70180_af.func_75682_a(16, new Byte((byte)1));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  51 */     super.func_70014_b(par1NBTTagCompound);
/*  52 */     par1NBTTagCompound.func_74768_a("Size", getSporeSize() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  61 */     super.func_70037_a(par1NBTTagCompound);
/*  62 */     setSporeSize(par1NBTTagCompound.func_74762_e("Size") + 1);
/*     */   }
/*     */   
/*     */   public void setSporeSize(int par1)
/*     */   {
/*  67 */     this.field_70180_af.func_75692_b(16, new Byte((byte)par1));
/*  68 */     float size = Math.max(0.15F * par1, 0.5F);
/*  69 */     func_70105_a(size, size);
/*  70 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*  71 */     this.field_70728_aV = par1;
/*     */   }
/*     */   
/*     */   public int getSporeSize()
/*     */   {
/*  76 */     return this.field_70180_af.func_75683_a(16);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  82 */     super.func_110147_ax();
/*  83 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D);
/*  84 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70053_R()
/*     */   {
/*  90 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70067_L()
/*     */   {
/*  96 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70104_M()
/*     */   {
/* 101 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70091_d(double par1, double par3, double par5)
/*     */   {
/* 107 */     par1 = 0.0D;
/* 108 */     par5 = 0.0D;
/* 109 */     if (par3 > 0.0D) { par3 = 0.0D;
/*     */     }
/* 111 */     int x = MathHelper.func_76128_c(this.field_70165_t);
/* 112 */     int y = MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1;
/* 113 */     int z = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 115 */     if ((this.field_70170_p.func_147439_a(x, y, z) == ConfigBlocks.blockTaintFibres) && (this.field_70170_p.func_72805_g(x, y, z) == 4)) {
/* 116 */       return;
/*     */     }
/* 118 */     super.func_70091_d(par1, par3, par5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70626_be() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70112_a(double par1)
/*     */   {
/* 129 */     return par1 < 4096.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public int func_70070_b(float par1)
/*     */   {
/* 137 */     return 15728880;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 146 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 151 */   protected int growth = 0;
/* 152 */   public float displaySize = 0.0F;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 156 */     super.func_70071_h_();
/*     */     
/* 158 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70173_aa % 20 == 0) && (this.field_70170_p.func_72807_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70161_v)).field_76756_M != Config.biomeTaintID))
/*     */     {
/*     */ 
/*     */ 
/* 162 */       func_70665_d(DamageSource.field_76366_f, 1.0F);
/*     */     }
/*     */     
/* 165 */     sporeOnUpdate();
/*     */   }
/*     */   
/*     */   protected void sporeOnUpdate() {
/* 169 */     if ((getSporeSize() < 10) && (this.growth++ == 1200)) {
/* 170 */       setSporeSize(getSporeSize() + 1);
/* 171 */       this.growth = 0;
/*     */     }
/*     */     
/* 174 */     if (this.field_70170_p.field_72995_K) {
/* 175 */       if (this.displaySize < getSporeSize()) {
/* 176 */         this.displaySize += 0.02F;
/*     */       }
/*     */       
/* 179 */       for (int a = 0; a < this.swarm.size(); a++) {
/* 180 */         if ((this.swarm.get(a) == null) || (((Entity)this.swarm.get(a)).field_70128_L)) {
/* 181 */           this.swarm.remove(a);
/* 182 */           break;
/*     */         }
/*     */       }
/*     */       
/* 186 */       if (this.swarm.size() < getSporeSize() / 3) {
/* 187 */         this.swarm.add(Thaumcraft.proxy.swarmParticleFX(this.field_70170_p, this, 0.1F, 10.0F, 0.0F));
/*     */       }
/*     */     }
/*     */     
/* 191 */     int x = MathHelper.func_76128_c(this.field_70165_t);
/* 192 */     int y = MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1;
/* 193 */     int z = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 195 */     if ((this.field_70170_p.func_147439_a(x, y, z) != ConfigBlocks.blockTaintFibres) || (this.field_70170_p.func_72805_g(x, y, z) != 4))
/*     */     {
/* 197 */       spiderBurst();
/*     */ 
/*     */     }
/* 200 */     else if (this.field_70725_aQ > 0) spiderBurst();
/*     */   }
/*     */   
/*     */   public void func_70100_b_(EntityPlayer par1EntityPlayer)
/*     */   {
/* 205 */     spiderBurst();
/*     */   }
/*     */   
/*     */   protected void spiderBurst() {
/* 209 */     if (!this.field_70170_p.field_72995_K) {
/* 210 */       this.field_70170_p.func_72956_a(this, "thaumcraft:gore", 1.0F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.1F);
/* 211 */       int q = getSporeSize() / 3 + this.field_70170_p.field_73012_v.nextInt(getSporeSize() / 2 + 1);
/* 212 */       for (int a = 0; a < q; a++) {
/* 213 */         EntityTaintSpider spiderling = new EntityTaintSpider(this.field_70170_p);
/* 214 */         spiderling.func_70012_b(this.field_70165_t + this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat(), this.field_70163_u + this.field_70170_p.field_73012_v.nextFloat(), this.field_70161_v + this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat(), this.field_70170_p.field_73012_v.nextFloat() * 360.0F, 0.0F);
/*     */         
/*     */ 
/*     */ 
/* 218 */         this.field_70170_p.func_72838_d(spiderling);
/*     */       }
/*     */       
/* 221 */       int x = MathHelper.func_76128_c(this.field_70165_t);
/* 222 */       int y = MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1;
/* 223 */       int z = MathHelper.func_76128_c(this.field_70161_v);
/*     */       
/* 225 */       if ((this.field_70170_p.func_147439_a(x, y, z) == ConfigBlocks.blockTaintFibres) && (this.field_70170_p.func_72805_g(x, y, z) == 4))
/*     */       {
/* 227 */         this.field_70170_p.func_72921_c(x, y, z, 3, 3);
/*     */       }
/*     */       
/* 230 */       func_70106_y();
/*     */     } else {
/* 232 */       sploosh(50);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void sploosh(int amt) {
/* 237 */     for (int a = 0; a < amt; a++) {
/* 238 */       Thaumcraft.proxy.splooshFX(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/* 245 */     data.writeFloat(getSporeSize());
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*     */     try {
/* 251 */       this.displaySize = data.readFloat();
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   protected float func_70599_aP()
/*     */   {
/* 258 */     return 0.1F;
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 263 */     return 200;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 268 */     return "thaumcraft:swarm";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 274 */     return "thaumcraft:gore";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 279 */     return "thaumcraft:gore";
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 285 */     return ConfigItems.itemResource;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 290 */     if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 291 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*     */     } else {
/* 293 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintSpore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */