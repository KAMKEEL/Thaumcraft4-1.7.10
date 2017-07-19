/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityTaintSwarm extends EntityMob implements ITaintedMob
/*     */ {
/*     */   private ChunkCoordinates currentFlightTarget;
/*     */   
/*     */   public EntityTaintSwarm(World par1World)
/*     */   {
/*  37 */     super(par1World);
/*  38 */     func_70105_a(2.0F, 2.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  44 */     super.func_70088_a();
/*  45 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public int func_70070_b(float par1)
/*     */   {
/*  52 */     return 15728880;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70013_c(float par1)
/*     */   {
/*  67 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/*  76 */     return 0.1F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/*  86 */     return "";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/*  95 */     return "thaumcraft:swarmattack";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 104 */     return "thaumcraft:swarmattack";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70104_M()
/*     */   {
/* 113 */     return false;
/*     */   }
/*     */   
/* 116 */   public int damBonus = 0;
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 121 */     super.func_110147_ax();
/* 122 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/* 123 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2 + this.damBonus);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean getIsSummoned()
/*     */   {
/* 129 */     return (this.field_70180_af.func_75683_a(16) & 0x2) != 0;
/*     */   }
/*     */   
/*     */   public void setIsSummoned(boolean par1)
/*     */   {
/* 134 */     byte var2 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 136 */     if (par1)
/*     */     {
/* 138 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 | 0x2)));
/*     */     }
/*     */     else
/*     */     {
/* 142 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFC)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70650_aV()
/*     */   {
/* 152 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 157 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 163 */   public ArrayList swarm = new ArrayList();
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 168 */     super.func_70071_h_();
/*     */     
/*     */ 
/* 171 */     this.field_70181_x *= 0.6000000238418579D;
/*     */     
/* 173 */     if (this.field_70170_p.field_72995_K) {
/* 174 */       for (int a = 0; a < this.swarm.size(); a++) {
/* 175 */         if ((this.swarm.get(a) == null) || (((Entity)this.swarm.get(a)).field_70128_L)) {
/* 176 */           this.swarm.remove(a);
/* 177 */           break;
/*     */         }
/*     */       }
/*     */       
/* 181 */       if (this.swarm.size() < Math.max(Thaumcraft.proxy.particleCount(25), 10)) {
/* 182 */         this.swarm.add(Thaumcraft.proxy.swarmParticleFX(this.field_70170_p, this, 0.22F, 15.0F, 0.08F));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70626_be()
/*     */   {
/* 189 */     super.func_70626_be();
/*     */     
/* 191 */     if (this.field_70789_a == null)
/*     */     {
/* 193 */       if (getIsSummoned()) {
/* 194 */         func_70097_a(DamageSource.field_76377_j, 5.0F);
/*     */       }
/* 196 */       if ((this.currentFlightTarget != null) && ((!this.field_70170_p.func_147437_c(this.currentFlightTarget.field_71574_a, this.currentFlightTarget.field_71572_b, this.currentFlightTarget.field_71573_c)) || (this.currentFlightTarget.field_71572_b < 1) || (this.currentFlightTarget.field_71572_b > this.field_70170_p.func_72976_f(this.currentFlightTarget.field_71574_a, this.currentFlightTarget.field_71573_c) + 8) || (this.field_70170_p.func_72807_a(this.currentFlightTarget.field_71574_a, this.currentFlightTarget.field_71573_c).field_76756_M != Config.biomeTaintID)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 203 */         this.currentFlightTarget = null;
/*     */       }
/*     */       
/* 206 */       if ((this.currentFlightTarget == null) || (this.field_70146_Z.nextInt(30) == 0) || (this.currentFlightTarget.func_71569_e((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v) < 4.0F))
/*     */       {
/* 208 */         this.currentFlightTarget = new ChunkCoordinates((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
/*     */       }
/*     */       
/* 211 */       double var1 = this.currentFlightTarget.field_71574_a + 0.5D - this.field_70165_t;
/* 212 */       double var3 = this.currentFlightTarget.field_71572_b + 0.1D - this.field_70163_u;
/* 213 */       double var5 = this.currentFlightTarget.field_71573_c + 0.5D - this.field_70161_v;
/* 214 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.015000000014901161D;
/* 215 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 216 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.015000000014901161D;
/* 217 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
/* 218 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 219 */       this.field_70701_bs = 0.1F;
/* 220 */       this.field_70177_z += var8;
/*     */     }
/* 222 */     else if (this.field_70789_a != null) {
/* 223 */       double var1 = this.field_70789_a.field_70165_t - this.field_70165_t;
/* 224 */       double var3 = this.field_70789_a.field_70163_u + this.field_70789_a.func_70047_e() - this.field_70163_u;
/* 225 */       double var5 = this.field_70789_a.field_70161_v - this.field_70161_v;
/* 226 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.025000000149011613D;
/* 227 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 228 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.02500000001490116D;
/* 229 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
/* 230 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 231 */       this.field_70701_bs = 0.1F;
/* 232 */       this.field_70177_z += var8;
/*     */     }
/*     */     
/* 235 */     if (((this.field_70789_a instanceof EntityPlayer)) && (((EntityPlayer)this.field_70789_a).field_71075_bZ.field_75102_a))
/*     */     {
/* 237 */       this.field_70789_a = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70619_bc()
/*     */   {
/* 247 */     super.func_70619_bc();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 257 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70069_a(float par1) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70064_a(double par1, boolean par3) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_145773_az()
/*     */   {
/* 279 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 288 */     if (func_85032_ar())
/*     */     {
/* 290 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 294 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70785_a(Entity par1Entity, float par2)
/*     */   {
/* 301 */     if ((this.field_70724_aR <= 0) && (par2 < 3.0F) && (par1Entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b) && (par1Entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e))
/*     */     {
/* 303 */       if (getIsSummoned()) {
/* 304 */         EntityUtils.setRecentlyHit((EntityLivingBase)par1Entity, 100);
/*     */       }
/*     */       
/* 307 */       this.field_70724_aR = (10 + this.field_70146_Z.nextInt(5));
/*     */       
/* 309 */       double mx = par1Entity.field_70159_w;
/* 310 */       double my = par1Entity.field_70181_x;
/* 311 */       double mz = par1Entity.field_70179_y;
/* 312 */       if ((func_70652_k(par1Entity)) && 
/* 313 */         (!this.field_70170_p.field_72995_K) && ((par1Entity instanceof EntityLivingBase))) {
/* 314 */         ((EntityLivingBase)par1Entity).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 100, 0));
/*     */       }
/*     */       
/* 317 */       par1Entity.field_70160_al = false;
/* 318 */       par1Entity.field_70159_w = mx;
/* 319 */       par1Entity.field_70181_x = my;
/* 320 */       par1Entity.field_70179_y = mz;
/*     */       
/* 322 */       this.field_70170_p.func_72956_a(this, "thaumcraft:swarmattack", 0.3F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Entity func_70782_k()
/*     */   {
/* 332 */     double var1 = 12.0D;
/* 333 */     return getIsSummoned() ? null : this.field_70170_p.func_72856_b(this, var1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 342 */     super.func_70037_a(par1NBTTagCompound);
/* 343 */     this.field_70180_af.func_75692_b(16, Byte.valueOf(par1NBTTagCompound.func_74771_c("Flags")));
/* 344 */     this.damBonus = par1NBTTagCompound.func_74771_c("damBonus");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 353 */     super.func_70014_b(par1NBTTagCompound);
/* 354 */     par1NBTTagCompound.func_74774_a("Flags", this.field_70180_af.func_75683_a(16));
/* 355 */     par1NBTTagCompound.func_74774_a("damBonus", (byte)this.damBonus);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 364 */     int var1 = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/*     */     
/* 366 */     int var2 = MathHelper.func_76128_c(this.field_70165_t);
/* 367 */     int var3 = MathHelper.func_76128_c(this.field_70161_v);
/* 368 */     int var4 = this.field_70170_p.func_72957_l(var2, var1, var3);
/* 369 */     byte var5 = 7;
/*     */     
/* 371 */     return var4 > this.field_70146_Z.nextInt(var5) ? false : super.func_70601_bi();
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70814_o()
/*     */   {
/* 377 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 383 */     return ConfigItems.itemResource;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 388 */     if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 389 */       func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintSwarm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */