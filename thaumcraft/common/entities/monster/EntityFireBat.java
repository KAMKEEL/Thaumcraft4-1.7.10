/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class EntityFireBat extends EntityMob
/*     */ {
/*     */   private ChunkCoordinates currentFlightTarget;
/*  31 */   public EntityPlayer owner = null;
/*     */   
/*     */   public EntityFireBat(World par1World)
/*     */   {
/*  35 */     super(par1World);
/*  36 */     func_70105_a(0.5F, 0.9F);
/*  37 */     setIsBatHanging(true);
/*  38 */     this.field_70178_ae = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70088_a()
/*     */   {
/*  44 */     super.func_70088_a();
/*  45 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b(float par1)
/*     */   {
/*  52 */     return 15728880;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70013_c(float par1)
/*     */   {
/*  61 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/*  70 */     return 0.1F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected float func_70647_i()
/*     */   {
/*  79 */     return super.func_70647_i() * 0.95F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/*  88 */     return (getIsBatHanging()) && (this.field_70146_Z.nextInt(4) != 0) ? null : "mob.bat.idle";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/*  97 */     return "mob.bat.hurt";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 106 */     return "mob.bat.death";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70104_M()
/*     */   {
/* 115 */     return false;
/*     */   }
/*     */   
/* 118 */   public int damBonus = 0;
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 123 */     super.func_110147_ax();
/* 124 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(getIsDevil() ? 15.0D : 5.0D);
/* 125 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(getIsSummoned() ? (getIsDevil() ? 3 : 2) + this.damBonus : 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean getIsBatHanging()
/*     */   {
/* 131 */     return Utils.getBit(this.field_70180_af.func_75683_a(16), 0);
/*     */   }
/*     */   
/*     */   public void setIsBatHanging(boolean par1)
/*     */   {
/* 136 */     byte var2 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 138 */     if (par1)
/*     */     {
/* 140 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.setBit(var2, 0)));
/*     */     }
/*     */     else
/*     */     {
/* 144 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.clearBit(var2, 0)));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getIsSummoned()
/*     */   {
/* 150 */     return Utils.getBit(this.field_70180_af.func_75683_a(16), 1);
/*     */   }
/*     */   
/*     */   public void setIsSummoned(boolean par1)
/*     */   {
/* 155 */     byte var2 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 157 */     if (par1)
/*     */     {
/* 159 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.setBit(var2, 1)));
/*     */     }
/*     */     else
/*     */     {
/* 163 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.clearBit(var2, 1)));
/*     */     }
/*     */     
/* 166 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(par1 ? (getIsDevil() ? 3 : 2) + this.damBonus : 1.0D);
/*     */   }
/*     */   
/*     */   public boolean getIsExplosive()
/*     */   {
/* 171 */     return Utils.getBit(this.field_70180_af.func_75683_a(16), 2);
/*     */   }
/*     */   
/*     */   public void setIsExplosive(boolean par1)
/*     */   {
/* 176 */     byte var2 = this.field_70180_af.func_75683_a(16);
/* 177 */     if (par1)
/*     */     {
/* 179 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.setBit(var2, 2)));
/*     */     }
/*     */     else
/*     */     {
/* 183 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.clearBit(var2, 2)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean getIsDevil()
/*     */   {
/* 190 */     return Utils.getBit(this.field_70180_af.func_75683_a(16), 3);
/*     */   }
/*     */   
/*     */   public void setIsDevil(boolean par1)
/*     */   {
/* 195 */     byte var2 = this.field_70180_af.func_75683_a(16);
/* 196 */     if (par1)
/*     */     {
/* 198 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.setBit(var2, 3)));
/*     */     }
/*     */     else
/*     */     {
/* 202 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.clearBit(var2, 3)));
/*     */     }
/*     */     
/* 205 */     if (par1) {
/* 206 */       func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(getIsSummoned() ? (par1 ? 3 : 2) + this.damBonus : 1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getIsVampire()
/*     */   {
/* 212 */     return Utils.getBit(this.field_70180_af.func_75683_a(16), 4);
/*     */   }
/*     */   
/*     */   public void setIsVampire(boolean par1)
/*     */   {
/* 217 */     byte var2 = this.field_70180_af.func_75683_a(16);
/* 218 */     if (par1)
/*     */     {
/* 220 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.setBit(var2, 4)));
/*     */     }
/*     */     else
/*     */     {
/* 224 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)Utils.clearBit(var2, 4)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70650_aV()
/*     */   {
/* 234 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 239 */     if (func_70026_G())
/*     */     {
/* 241 */       func_70097_a(DamageSource.field_76369_e, 1.0F);
/*     */     }
/*     */     
/* 244 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 253 */     super.func_70071_h_();
/*     */     
/* 255 */     if ((this.field_70170_p.field_72995_K) && (getIsExplosive())) {
/* 256 */       Thaumcraft.proxy.drawGenericParticles(this.field_70170_p, this.field_70169_q + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F, this.field_70167_r + this.field_70131_O / 2.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F, this.field_70166_s + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F, 0.0D, 0.0D, 0.0D, 1.0F, 1.0F, 1.0F, 0.8F, false, 151, 9, 1, 7 + this.field_70146_Z.nextInt(5), 0, 1.0F + this.field_70146_Z.nextFloat() * 0.5F);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 264 */     if (getIsBatHanging())
/*     */     {
/* 266 */       this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/* 267 */       this.field_70163_u = (MathHelper.func_76128_c(this.field_70163_u) + 1.0D - this.field_70131_O);
/*     */     }
/*     */     else
/*     */     {
/* 271 */       this.field_70181_x *= 0.6000000238418579D;
/*     */     }
/*     */     
/* 274 */     if ((this.field_70170_p.field_72995_K) && (!getIsVampire())) {
/* 275 */       this.field_70170_p.func_72869_a("smoke", this.field_70169_q + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70167_r + this.field_70131_O / 2.0F + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70166_s + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, 0.0D, 0.0D, 0.0D);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 280 */       this.field_70170_p.func_72869_a("flame", this.field_70169_q + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70167_r + this.field_70131_O / 2.0F + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, this.field_70166_s + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2F, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70626_be()
/*     */   {
/* 290 */     super.func_70626_be();
/* 291 */     if (getIsBatHanging())
/*     */     {
/* 293 */       if (!this.field_70170_p.func_147445_c(MathHelper.func_76128_c(this.field_70165_t), (int)this.field_70163_u + 1, MathHelper.func_76128_c(this.field_70161_v), false))
/*     */       {
/* 295 */         setIsBatHanging(false);
/* 296 */         this.field_70170_p.func_72889_a((EntityPlayer)null, 1015, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */       }
/*     */       else
/*     */       {
/* 300 */         if (this.field_70146_Z.nextInt(200) == 0)
/*     */         {
/* 302 */           this.field_70759_as = this.field_70146_Z.nextInt(360);
/*     */         }
/*     */         
/* 305 */         if (this.field_70170_p.func_72890_a(this, 4.0D) != null)
/*     */         {
/* 307 */           setIsBatHanging(false);
/* 308 */           this.field_70170_p.func_72889_a((EntityPlayer)null, 1015, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 314 */       if (this.field_70789_a == null)
/*     */       {
/* 316 */         if (getIsSummoned()) {
/* 317 */           func_70097_a(DamageSource.field_76377_j, 2.0F);
/*     */         }
/* 319 */         if ((this.currentFlightTarget != null) && ((!this.field_70170_p.func_147437_c(this.currentFlightTarget.field_71574_a, this.currentFlightTarget.field_71572_b, this.currentFlightTarget.field_71573_c)) || (this.currentFlightTarget.field_71572_b < 1)))
/*     */         {
/* 321 */           this.currentFlightTarget = null;
/*     */         }
/*     */         
/* 324 */         if ((this.currentFlightTarget == null) || (this.field_70146_Z.nextInt(30) == 0) || (this.currentFlightTarget.func_71569_e((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v) < 4.0F))
/*     */         {
/* 326 */           this.currentFlightTarget = new ChunkCoordinates((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
/*     */         }
/*     */         
/* 329 */         double var1 = this.currentFlightTarget.field_71574_a + 0.5D - this.field_70165_t;
/* 330 */         double var3 = this.currentFlightTarget.field_71572_b + 0.1D - this.field_70163_u;
/* 331 */         double var5 = this.currentFlightTarget.field_71573_c + 0.5D - this.field_70161_v;
/* 332 */         this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 333 */         this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 334 */         this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 335 */         float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
/* 336 */         float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 337 */         this.field_70701_bs = 0.5F;
/* 338 */         this.field_70177_z += var8;
/*     */         
/* 340 */         if ((this.field_70146_Z.nextInt(100) == 0) && (this.field_70170_p.func_147445_c(MathHelper.func_76128_c(this.field_70165_t), (int)this.field_70163_u + 1, MathHelper.func_76128_c(this.field_70161_v), false)))
/*     */         {
/* 342 */           setIsBatHanging(true);
/*     */         }
/*     */       }
/* 345 */       else if (this.field_70789_a != null) {
/* 346 */         double var1 = this.field_70789_a.field_70165_t - this.field_70165_t;
/* 347 */         double var3 = this.field_70789_a.field_70163_u + this.field_70789_a.func_70047_e() * 0.66F - this.field_70163_u;
/* 348 */         double var5 = this.field_70789_a.field_70161_v - this.field_70161_v;
/* 349 */         this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 350 */         this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 351 */         this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 352 */         float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
/* 353 */         float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 354 */         this.field_70701_bs = 0.5F;
/* 355 */         this.field_70177_z += var8;
/*     */       }
/*     */       
/* 358 */       if (((this.field_70789_a instanceof EntityPlayer)) && (((EntityPlayer)this.field_70789_a).field_71075_bZ.field_75102_a))
/*     */       {
/* 360 */         this.field_70789_a = null;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70619_bc()
/*     */   {
/* 371 */     super.func_70619_bc();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 381 */     return false;
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
/* 403 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 412 */     if ((func_85032_ar()) || (par1DamageSource.func_76347_k()) || (par1DamageSource.func_94541_c()))
/*     */     {
/* 414 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 418 */     if ((!this.field_70170_p.field_72995_K) && (getIsBatHanging()))
/*     */     {
/* 420 */       setIsBatHanging(false);
/*     */     }
/*     */     
/* 423 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70785_a(Entity par1Entity, float par2)
/*     */   {
/* 430 */     if ((this.field_70724_aR <= 0) && (par2 < Math.max(2.5F, par1Entity.field_70130_N * 1.1F)) && (par1Entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b) && (par1Entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e))
/*     */     {
/* 432 */       if (getIsSummoned()) {
/* 433 */         EntityUtils.setRecentlyHit((EntityLivingBase)par1Entity, 100);
/*     */       }
/*     */       
/* 436 */       if (getIsVampire()) {
/* 437 */         if ((this.owner != null) && (!this.owner.func_82165_m(Potion.field_76428_l.field_76415_H))) {
/* 438 */           this.owner.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 26, 1));
/*     */         }
/* 440 */         func_70691_i(1.0F);
/*     */       }
/*     */       
/* 443 */       this.field_70724_aR = 20;
/* 444 */       if (((getIsExplosive()) || (this.field_70170_p.field_73012_v.nextInt(10) == 0)) && (!this.field_70170_p.field_72995_K) && (!getIsDevil())) {
/* 445 */         par1Entity.field_70172_ad = 0;
/* 446 */         this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.5F + (getIsExplosive() ? this.damBonus * 0.33F : 0.0F), false, false);
/* 447 */         func_70106_y();
/*     */       }
/* 449 */       else if ((getIsVampire()) || (this.field_70170_p.field_73012_v.nextBoolean())) {
/* 450 */         double mx = par1Entity.field_70159_w;
/* 451 */         double my = par1Entity.field_70181_x;
/* 452 */         double mz = par1Entity.field_70179_y;
/* 453 */         func_70652_k(par1Entity);
/* 454 */         par1Entity.field_70160_al = false;
/* 455 */         par1Entity.field_70159_w = mx;
/* 456 */         par1Entity.field_70181_x = my;
/* 457 */         par1Entity.field_70179_y = mz;
/*     */       } else {
/* 459 */         par1Entity.func_70015_d(getIsSummoned() ? 4 : 2);
/*     */       }
/*     */       
/* 462 */       this.field_70170_p.func_72956_a(this, "mob.bat.hurt", 0.5F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Entity func_70782_k()
/*     */   {
/* 471 */     double var1 = 12.0D;
/* 472 */     return getIsSummoned() ? null : this.field_70170_p.func_72856_b(this, var1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 481 */     super.func_70037_a(par1NBTTagCompound);
/* 482 */     this.field_70180_af.func_75692_b(16, Byte.valueOf(par1NBTTagCompound.func_74771_c("BatFlags")));
/* 483 */     this.damBonus = par1NBTTagCompound.func_74771_c("damBonus");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 492 */     super.func_70014_b(par1NBTTagCompound);
/* 493 */     par1NBTTagCompound.func_74774_a("BatFlags", this.field_70180_af.func_75683_a(16));
/* 494 */     par1NBTTagCompound.func_74774_a("damBonus", (byte)this.damBonus);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 503 */     int var1 = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/*     */     
/* 505 */     int var2 = MathHelper.func_76128_c(this.field_70165_t);
/* 506 */     int var3 = MathHelper.func_76128_c(this.field_70161_v);
/* 507 */     int var4 = this.field_70170_p.func_72957_l(var2, var1, var3);
/* 508 */     byte var5 = 7;
/*     */     
/* 510 */     return var4 > this.field_70146_Z.nextInt(var5) ? false : super.func_70601_bi();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 519 */     if (!getIsSummoned()) return Items.field_151016_H; return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   protected boolean func_70814_o()
/*     */   {
/* 524 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityFireBat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */