/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class EntityAspectOrb
/*     */   extends Entity implements IEntityAdditionalSpawnData
/*     */ {
/*     */   public boolean func_70112_a(double par1)
/*     */   {
/*  28 */     double d1 = 0.5D;
/*  29 */     d1 *= 64.0D * this.field_70155_l;
/*  30 */     return par1 < d1 * d1;
/*     */   }
/*     */   
/*     */ 
/*  34 */   public int orbAge = 0;
/*  35 */   public int orbMaxAge = 150;
/*     */   
/*     */   public int orbCooldown;
/*     */   
/*  39 */   private int orbHealth = 5;
/*     */   
/*     */   private Aspect aspect;
/*     */   
/*     */   private int aspectValue;
/*     */   
/*     */   private EntityPlayer closestPlayer;
/*     */   
/*     */   public EntityAspectOrb(World par1World, double par2, double par4, double par6, Aspect aspect, int par8)
/*     */   {
/*  49 */     super(par1World);
/*  50 */     func_70105_a(0.125F, 0.125F);
/*  51 */     this.field_70129_M = (this.field_70131_O / 2.0F);
/*  52 */     func_70107_b(par2, par4, par6);
/*  53 */     this.field_70177_z = ((float)(Math.random() * 360.0D));
/*  54 */     this.field_70159_w = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
/*  55 */     this.field_70181_x = ((float)(Math.random() * 0.2D) * 2.0F);
/*  56 */     this.field_70179_y = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
/*  57 */     this.aspectValue = par8;
/*  58 */     setAspect(aspect);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/*  68 */     return false;
/*     */   }
/*     */   
/*     */   public EntityAspectOrb(World par1World)
/*     */   {
/*  73 */     super(par1World);
/*  74 */     func_70105_a(0.125F, 0.125F);
/*  75 */     this.field_70129_M = (this.field_70131_O / 2.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a() {}
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b(float par1)
/*     */   {
/*  85 */     float f1 = 0.5F;
/*     */     
/*  87 */     if (f1 < 0.0F)
/*     */     {
/*  89 */       f1 = 0.0F;
/*     */     }
/*     */     
/*  92 */     if (f1 > 1.0F)
/*     */     {
/*  94 */       f1 = 1.0F;
/*     */     }
/*     */     
/*  97 */     int i = super.func_70070_b(par1);
/*  98 */     int j = i & 0xFF;
/*  99 */     int k = i >> 16 & 0xFF;
/* 100 */     j += (int)(f1 * 15.0F * 16.0F);
/*     */     
/* 102 */     if (j > 240)
/*     */     {
/* 104 */       j = 240;
/*     */     }
/*     */     
/* 107 */     return j | k << 16;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 116 */     super.func_70071_h_();
/*     */     
/* 118 */     if (this.orbCooldown > 0)
/*     */     {
/* 120 */       this.orbCooldown -= 1;
/*     */     }
/*     */     
/* 123 */     this.field_70169_q = this.field_70165_t;
/* 124 */     this.field_70167_r = this.field_70163_u;
/* 125 */     this.field_70166_s = this.field_70161_v;
/* 126 */     this.field_70181_x -= 0.029999999329447746D;
/*     */     
/* 128 */     if (this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)).func_149688_o() == Material.field_151587_i)
/*     */     {
/* 130 */       this.field_70181_x = 0.20000000298023224D;
/* 131 */       this.field_70159_w = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/* 132 */       this.field_70179_y = ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
/* 133 */       func_85030_a("random.fizz", 0.4F, 2.0F + this.field_70146_Z.nextFloat() * 0.4F);
/*     */     }
/*     */     
/* 136 */     func_145771_j(this.field_70165_t, (this.field_70121_D.field_72338_b + this.field_70121_D.field_72337_e) / 2.0D, this.field_70161_v);
/* 137 */     double d0 = 8.0D;
/*     */     
/*     */     double distance;
/* 140 */     if ((this.field_70173_aa % 5 == 0) && (this.closestPlayer == null)) {
/* 141 */       List<Entity> targets = this.field_70170_p.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t, this.field_70163_u, this.field_70161_v).func_72314_b(d0, d0, d0));
/*     */       
/* 143 */       if (targets.size() > 0) {
/* 144 */         distance = Double.MAX_VALUE;
/* 145 */         for (Entity t : targets) {
/* 146 */           double d = ((EntityPlayer)t).func_70068_e(this);
/* 147 */           if ((d < distance) && (InventoryUtils.isWandInHotbarWithRoom(getAspect(), this.aspectValue, (EntityPlayer)t) >= 0)) {
/* 148 */             distance = d;
/* 149 */             this.closestPlayer = ((EntityPlayer)t);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 155 */     if (this.closestPlayer != null)
/*     */     {
/* 157 */       double d1 = (this.closestPlayer.field_70165_t - this.field_70165_t) / d0;
/* 158 */       double d2 = (this.closestPlayer.field_70163_u + this.closestPlayer.func_70047_e() - this.field_70163_u) / d0;
/* 159 */       double d3 = (this.closestPlayer.field_70161_v - this.field_70161_v) / d0;
/* 160 */       double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
/* 161 */       double d5 = 1.0D - d4;
/*     */       
/* 163 */       if (d5 > 0.0D)
/*     */       {
/* 165 */         d5 *= d5;
/* 166 */         this.field_70159_w += d1 / d4 * d5 * 0.1D;
/* 167 */         this.field_70181_x += d2 / d4 * d5 * 0.1D;
/* 168 */         this.field_70179_y += d3 / d4 * d5 * 0.1D;
/*     */       }
/*     */     }
/*     */     
/* 172 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 173 */     float f = 0.98F;
/*     */     
/* 175 */     if (this.field_70122_E)
/*     */     {
/* 177 */       f = 0.58800006F;
/* 178 */       Block i = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v));
/*     */       
/* 180 */       if (!i.isAir(this.field_70170_p, MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v)))
/*     */       {
/* 182 */         f = i.field_149765_K * 0.98F;
/*     */       }
/*     */     }
/*     */     
/* 186 */     this.field_70159_w *= f;
/* 187 */     this.field_70181_x *= 0.9800000190734863D;
/* 188 */     this.field_70179_y *= f;
/*     */     
/* 190 */     if (this.field_70122_E)
/*     */     {
/* 192 */       this.field_70181_x *= -0.8999999761581421D;
/*     */     }
/*     */     
/* 195 */     this.orbAge += 1;
/*     */     
/* 197 */     if (this.orbAge >= this.orbMaxAge)
/*     */     {
/* 199 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70072_I()
/*     */   {
/* 208 */     return this.field_70170_p.func_72918_a(this.field_70121_D, Material.field_151586_h, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70081_e(int par1)
/*     */   {
/* 217 */     func_70097_a(DamageSource.field_76372_a, par1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 226 */     if (func_85032_ar())
/*     */     {
/* 228 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 232 */     func_70018_K();
/* 233 */     this.orbHealth = ((int)(this.orbHealth - par2));
/*     */     
/* 235 */     if (this.orbHealth <= 0)
/*     */     {
/* 237 */       func_70106_y();
/*     */     }
/*     */     
/* 240 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 250 */     par1NBTTagCompound.func_74777_a("Health", (short)(byte)this.orbHealth);
/* 251 */     par1NBTTagCompound.func_74777_a("Age", (short)this.orbAge);
/* 252 */     par1NBTTagCompound.func_74777_a("Value", (short)this.aspectValue);
/* 253 */     par1NBTTagCompound.func_74778_a("Aspect", getAspect().getTag());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 262 */     this.orbHealth = (par1NBTTagCompound.func_74765_d("Health") & 0xFF);
/* 263 */     this.orbAge = par1NBTTagCompound.func_74765_d("Age");
/* 264 */     this.aspectValue = par1NBTTagCompound.func_74765_d("Value");
/* 265 */     setAspect(Aspect.getAspect(par1NBTTagCompound.func_74779_i("Aspect")));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70100_b_(EntityPlayer par1EntityPlayer)
/*     */   {
/* 274 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 276 */       int slot = InventoryUtils.isWandInHotbarWithRoom(getAspect(), this.aspectValue, par1EntityPlayer);
/* 277 */       if ((this.orbCooldown == 0) && (par1EntityPlayer.field_71090_bL == 0) && (getAspect().isPrimal()) && (slot >= 0))
/*     */       {
/*     */ 
/* 280 */         ItemWandCasting wand = (ItemWandCasting)par1EntityPlayer.field_71071_by.field_70462_a[slot].func_77973_b();
/* 281 */         wand.addVis(par1EntityPlayer.field_71071_by.field_70462_a[slot], getAspect(), this.aspectValue, true);
/*     */         
/* 283 */         par1EntityPlayer.field_71090_bL = 2;
/* 284 */         func_85030_a("random.orb", 0.1F, 0.5F * ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.8F));
/* 285 */         func_70106_y();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/* 292 */     if (getAspect() != null) {
/* 293 */       data.writeShort(getAspect().getTag().length());
/* 294 */       for (char c : getAspect().getTag().toCharArray()) {
/* 295 */         data.writeChar(c);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*     */     try {
/* 303 */       int l = data.readShort();
/* 304 */       StringBuilder s = new StringBuilder();
/* 305 */       for (int var4 = 0; var4 < l; var4++)
/*     */       {
/* 307 */         s.append(data.readChar());
/*     */       }
/* 309 */       setAspect(Aspect.getAspect(s.toString()));
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getAspectValue()
/*     */   {
/* 318 */     return this.aspectValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70075_an()
/*     */   {
/* 327 */     return false;
/*     */   }
/*     */   
/*     */   public Aspect getAspect()
/*     */   {
/* 332 */     return this.aspect;
/*     */   }
/*     */   
/*     */   public void setAspect(Aspect aspect) {
/* 336 */     this.aspect = aspect;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/EntityAspectOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */