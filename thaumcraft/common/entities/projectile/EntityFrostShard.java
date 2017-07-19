/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ public class EntityFrostShard extends EntityThrowable implements IEntityAdditionalSpawnData
/*     */ {
/*  30 */   public double bounce = 0.5D;
/*     */   
/*  32 */   public int bounceLimit = 3;
/*     */   
/*     */   public EntityFrostShard(World par1World) {
/*  35 */     super(par1World);
/*     */   }
/*     */   
/*     */   public EntityFrostShard(World par1World, EntityLivingBase par2EntityLiving, float scatter) {
/*  39 */     super(par1World, par2EntityLiving);
/*  40 */     func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, func_70182_d(), scatter);
/*     */   }
/*     */   
/*     */   protected float func_70185_h()
/*     */   {
/*  45 */     return this.fragile ? 0.015F : 0.05F;
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  50 */     data.writeDouble(this.bounce);
/*  51 */     data.writeInt(this.bounceLimit);
/*  52 */     data.writeBoolean(this.fragile);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  57 */     this.bounce = data.readDouble();
/*  58 */     this.bounceLimit = data.readInt();
/*  59 */     this.fragile = data.readBoolean();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70184_a(MovingObjectPosition mop)
/*     */   {
/*  65 */     if (mop.field_72308_g != null)
/*     */     {
/*  67 */       int ox = MathHelper.func_76128_c(this.field_70165_t) - MathHelper.func_76128_c(mop.field_72308_g.field_70165_t);
/*  68 */       int oy = MathHelper.func_76128_c(this.field_70163_u) - MathHelper.func_76128_c(mop.field_72308_g.field_70163_u);
/*  69 */       int oz = MathHelper.func_76128_c(this.field_70161_v) - MathHelper.func_76128_c(mop.field_72308_g.field_70161_v);
/*  70 */       if (oz != 0) this.field_70179_y *= -1.0D;
/*  71 */       if (ox != 0) this.field_70159_w *= -1.0D;
/*  72 */       if (oy != 0) this.field_70181_x *= -0.9D;
/*  73 */       this.field_70159_w *= 0.66D;
/*  74 */       this.field_70181_x *= 0.66D;
/*  75 */       this.field_70179_y *= 0.66D;
/*  76 */       for (int a = 0; a < getDamage(); a++) {
/*  77 */         this.field_70170_p.func_72869_a("blockcrack_" + Block.func_149682_b(ConfigBlocks.blockCustomOre) + "_15", this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0D * (this.field_70146_Z.nextFloat() - 0.5D), 0.5D, (this.field_70146_Z.nextFloat() - 0.5D) * 4.0D);
/*     */       }
/*     */     }
/*  80 */     else if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  81 */       ForgeDirection dir = ForgeDirection.getOrientation(mop.field_72310_e);
/*  82 */       if (dir.offsetZ != 0) this.field_70179_y *= -1.0D;
/*  83 */       if (dir.offsetX != 0) this.field_70159_w *= -1.0D;
/*  84 */       if (dir.offsetY != 0) this.field_70181_x *= -0.9D;
/*  85 */       Block bhit = this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*  86 */       try { func_85030_a(bhit.field_149762_H.func_150495_a(), 0.3F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/*     */       } catch (Exception e) {}
/*  88 */       for (int a = 0; a < getDamage(); a++) {
/*  89 */         this.field_70170_p.func_72869_a("blockcrack_" + Block.func_149682_b(bhit) + "_" + this.field_70170_p.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d), this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0D * (this.field_70146_Z.nextFloat() - 0.5D), 0.5D, (this.field_70146_Z.nextFloat() - 0.5D) * 4.0D);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  94 */     this.field_70159_w *= this.bounce;
/*  95 */     this.field_70181_x *= this.bounce;
/*  96 */     this.field_70179_y *= this.bounce;
/*  97 */     float var20 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/*  98 */     this.field_70165_t -= this.field_70159_w / var20 * 0.05000000074505806D;
/*  99 */     this.field_70163_u -= this.field_70181_x / var20 * 0.05000000074505806D;
/* 100 */     this.field_70161_v -= this.field_70179_y / var20 * 0.05000000074505806D;
/* 101 */     func_70018_K();
/*     */     
/* 103 */     if ((!this.field_70170_p.field_72995_K) && (mop.field_72308_g != null)) {
/* 104 */       double mx = mop.field_72308_g.field_70159_w;
/* 105 */       double my = mop.field_72308_g.field_70181_x;
/* 106 */       double mz = mop.field_72308_g.field_70179_y;
/* 107 */       mop.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, func_85052_h()), getDamage());
/*     */       
/* 109 */       if (((mop.field_72308_g instanceof EntityLivingBase)) && (getFrosty() > 0)) {
/* 110 */         ((EntityLivingBase)mop.field_72308_g).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 200, getFrosty() - 1));
/*     */       }
/*     */       
/* 113 */       if (this.fragile) {
/* 114 */         mop.field_72308_g.field_70172_ad = 0;
/* 115 */         func_70106_y();
/* 116 */         func_85030_a(Blocks.field_150432_aD.field_149762_H.func_150495_a(), 0.3F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/* 117 */         mop.field_72308_g.field_70159_w = (mx + (mop.field_72308_g.field_70159_w - mx) / 10.0D);
/* 118 */         mop.field_72308_g.field_70181_x = (my + (mop.field_72308_g.field_70181_x - my) / 10.0D);
/* 119 */         mop.field_72308_g.field_70179_y = (mz + (mop.field_72308_g.field_70179_y - mz) / 10.0D);
/*     */       }
/*     */     }
/*     */     
/* 123 */     if (this.bounceLimit-- <= 0) {
/* 124 */       func_70106_y();
/* 125 */       func_85030_a(Blocks.field_150432_aD.field_149762_H.func_150495_a(), 0.3F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/* 126 */       for (int a = 0; a < 8.0F * getDamage(); a++) {
/* 127 */         this.field_70170_p.func_72869_a("blockcrack_" + Block.func_149682_b(ConfigBlocks.blockCustomOre) + "_15", this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0D * (this.field_70146_Z.nextFloat() - 0.5D), 0.5D, (this.field_70146_Z.nextFloat() - 0.5D) * 4.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 136 */     super.func_70071_h_();
/*     */     
/* 138 */     if ((this.field_70170_p.field_72995_K) && (getFrosty() > 0)) {
/* 139 */       float s = getDamage() / 10.0F;
/* 140 */       for (int a = 0; a < getFrosty(); a++) {
/* 141 */         Thaumcraft.proxy.sparkle((float)this.field_70165_t - s + this.field_70146_Z.nextFloat() * (s * 2.0F), (float)this.field_70163_u - s + this.field_70146_Z.nextFloat() * (s * 2.0F), (float)this.field_70161_v - s + this.field_70146_Z.nextFloat() * (s * 2.0F), 0.4F, 6, 0.005F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 148 */     float var20 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 149 */     this.field_70177_z = ((float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/*     */     
/* 151 */     for (this.field_70125_A = ((float)(Math.atan2(this.field_70181_x, var20) * 180.0D / 3.141592653589793D)); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 156 */     while (this.field_70125_A - this.field_70127_C >= 180.0F)
/*     */     {
/* 158 */       this.field_70127_C += 360.0F;
/*     */     }
/*     */     
/* 161 */     while (this.field_70177_z - this.field_70126_B < -180.0F)
/*     */     {
/* 163 */       this.field_70126_B -= 360.0F;
/*     */     }
/*     */     
/* 166 */     while (this.field_70177_z - this.field_70126_B >= 180.0F)
/*     */     {
/* 168 */       this.field_70126_B += 360.0F;
/*     */     }
/*     */     
/* 171 */     this.field_70125_A = (this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F);
/* 172 */     this.field_70177_z = (this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 178 */     super.func_70014_b(par1NBTTagCompound);
/* 179 */     par1NBTTagCompound.func_74776_a("damage", getDamage());
/* 180 */     par1NBTTagCompound.func_74757_a("fragile", this.fragile);
/* 181 */     par1NBTTagCompound.func_74768_a("frost", getFrosty());
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 187 */     super.func_70037_a(par1NBTTagCompound);
/* 188 */     setDamage(par1NBTTagCompound.func_74760_g("damage"));
/* 189 */     this.fragile = par1NBTTagCompound.func_74767_n("fragile");
/* 190 */     setFrosty(par1NBTTagCompound.func_74762_e("frost"));
/*     */   }
/*     */   
/*     */ 
/* 194 */   public boolean fragile = false;
/*     */   
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/* 199 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R()
/*     */   {
/* 206 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70088_a()
/*     */   {
/* 212 */     super.func_70088_a();
/* 213 */     this.field_70180_af.func_75682_a(16, new Float(0.0F));
/* 214 */     this.field_70180_af.func_75682_a(17, new Byte((byte)0));
/*     */   }
/*     */   
/*     */   public void setDamage(float par1)
/*     */   {
/* 219 */     this.field_70180_af.func_75692_b(16, Float.valueOf(par1));
/*     */     
/* 221 */     func_70105_a(0.15F + par1 * 0.15F, 0.15F + par1 * 0.15F);
/*     */   }
/*     */   
/*     */   public float getDamage()
/*     */   {
/* 226 */     return this.field_70180_af.func_111145_d(16);
/*     */   }
/*     */   
/*     */   public void setFrosty(int frosty)
/*     */   {
/* 231 */     this.field_70180_af.func_75692_b(17, Byte.valueOf((byte)frosty));
/*     */   }
/*     */   
/*     */   public int getFrosty()
/*     */   {
/* 236 */     return this.field_70180_af.func_75683_a(17);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityFrostShard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */