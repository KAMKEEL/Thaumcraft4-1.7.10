/*     */ package thaumcraft.common.entities.projectile;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IProjectile;
/*     */ import net.minecraft.entity.monster.EntityEnderman;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSourceIndirect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.damagesource.DamageSourceIndirectThaumcraftEntity;
/*     */ 
/*     */ public class EntityPrimalArrow extends EntityArrow implements IProjectile, IEntityAdditionalSpawnData
/*     */ {
/*  33 */   private int xTile = -1;
/*  34 */   private int yTile = -1;
/*  35 */   private int zTile = -1;
/*  36 */   private Block inTile = net.minecraft.init.Blocks.field_150350_a;
/*  37 */   private int inData = 0;
/*  38 */   private boolean inGround = false;
/*     */   
/*     */   public int field_70252_j;
/*     */   
/*  42 */   private int ticksInAir = 0;
/*  43 */   private double damage = 2.1D;
/*     */   
/*     */   public int shootingEntityId;
/*     */   
/*     */   private int knockbackStrength;
/*  48 */   public int type = 0;
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  52 */     data.writeDouble(this.field_70159_w);
/*  53 */     data.writeDouble(this.field_70181_x);
/*  54 */     data.writeDouble(this.field_70179_y);
/*  55 */     data.writeFloat(this.field_70177_z);
/*  56 */     data.writeFloat(this.field_70125_A);
/*  57 */     data.writeByte(this.type);
/*  58 */     data.writeInt(this.shootingEntityId);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  63 */     this.field_70159_w = data.readDouble();
/*  64 */     this.field_70181_x = data.readDouble();
/*  65 */     this.field_70179_y = data.readDouble();
/*  66 */     this.field_70177_z = data.readFloat();
/*  67 */     this.field_70125_A = data.readFloat();
/*  68 */     this.field_70126_B = this.field_70177_z;
/*  69 */     this.field_70127_C = this.field_70125_A;
/*  70 */     this.type = data.readByte();
/*  71 */     this.shootingEntityId = data.readInt();
/*     */   }
/*     */   
/*     */   public EntityPrimalArrow(World par1World)
/*     */   {
/*  76 */     super(par1World);
/*  77 */     this.field_70155_l = 10.0D;
/*  78 */     func_70105_a(0.5F, 0.5F);
/*     */   }
/*     */   
/*     */   public EntityPrimalArrow(World par1World, double par2, double par4, double par6)
/*     */   {
/*  83 */     super(par1World);
/*  84 */     this.field_70155_l = 10.0D;
/*  85 */     func_70105_a(0.25F, 0.25F);
/*  86 */     func_70107_b(par2, par4, par6);
/*  87 */     this.field_70129_M = 0.0F;
/*     */   }
/*     */   
/*     */   public EntityPrimalArrow(World par1World, EntityLivingBase par2EntityLivingBase, float par3, int type)
/*     */   {
/*  92 */     super(par1World);
/*  93 */     this.field_70155_l = 10.0D;
/*  94 */     this.field_70250_c = par2EntityLivingBase;
/*  95 */     this.type = type;
/*  96 */     this.field_70251_a = 0;
/*  97 */     this.shootingEntityId = this.field_70250_c.func_145782_y();
/*  98 */     func_70105_a(0.5F, 0.5F);
/*  99 */     func_70012_b(par2EntityLivingBase.field_70165_t, par2EntityLivingBase.field_70163_u + par2EntityLivingBase.func_70047_e(), par2EntityLivingBase.field_70161_v, par2EntityLivingBase.field_70177_z, par2EntityLivingBase.field_70125_A);
/* 100 */     this.field_70165_t -= MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F;
/* 101 */     this.field_70163_u -= 0.10000000014901161D;
/* 102 */     this.field_70161_v -= MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F;
/* 103 */     Vec3 vec3d = par2EntityLivingBase.func_70676_i(1.0F);
/* 104 */     this.field_70165_t += vec3d.field_72450_a;
/* 105 */     this.field_70163_u += vec3d.field_72448_b;
/* 106 */     this.field_70161_v += vec3d.field_72449_c;
/*     */     
/* 108 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 109 */     this.field_70129_M = 0.0F;
/* 110 */     this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/* 111 */     this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
/* 112 */     this.field_70181_x = (-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F));
/* 113 */     func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, par3 * 1.5F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70100_b_(EntityPlayer par1EntityPlayer) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 125 */     super.func_70071_h_();
/*     */     
/* 127 */     if ((this.field_70127_C == 0.0F) && (this.field_70126_B == 0.0F))
/*     */     {
/* 129 */       float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 130 */       this.field_70126_B = (this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/* 131 */       this.field_70127_C = (this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0D / 3.141592653589793D));
/*     */     }
/*     */     
/* 134 */     Block i = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
/*     */     
/* 136 */     if (!i.isAir(this.field_70170_p, this.xTile, this.yTile, this.zTile))
/*     */     {
/* 138 */       i.func_149719_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
/* 139 */       AxisAlignedBB axisalignedbb = i.func_149668_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
/*     */       
/* 141 */       if ((axisalignedbb != null) && (axisalignedbb.func_72318_a(Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v))))
/*     */       {
/* 143 */         this.inGround = true;
/*     */       }
/*     */     }
/*     */     
/* 147 */     if (this.field_70249_b > 0)
/*     */     {
/* 149 */       this.field_70249_b -= 1;
/*     */     }
/*     */     
/* 152 */     if (this.inGround)
/*     */     {
/* 154 */       Block j = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
/* 155 */       int k = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
/*     */       
/* 157 */       if ((j == this.inTile) && (k == this.inData))
/*     */       {
/* 159 */         this.field_70252_j += 1;
/*     */         
/* 161 */         if (this.field_70252_j == 100)
/*     */         {
/* 163 */           func_70106_y();
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 168 */         this.inGround = false;
/* 169 */         this.field_70159_w *= this.field_70146_Z.nextFloat() * 0.2F;
/* 170 */         this.field_70181_x *= this.field_70146_Z.nextFloat() * 0.2F;
/* 171 */         this.field_70179_y *= this.field_70146_Z.nextFloat() * 0.2F;
/* 172 */         this.field_70252_j = 0;
/* 173 */         this.ticksInAir = 0;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 178 */       this.ticksInAir += 1;
/* 179 */       Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 180 */       Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/* 181 */       MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec3, vec31, false, true, false);
/* 182 */       vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 183 */       vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */       
/* 185 */       if (movingobjectposition != null)
/*     */       {
/* 187 */         vec31 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
/*     */       }
/*     */       
/* 190 */       Entity entity = null;
/* 191 */       List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
/* 192 */       double d0 = 0.0D;
/*     */       
/*     */ 
/*     */ 
/* 196 */       for (int l = 0; l < list.size(); l++)
/*     */       {
/* 198 */         Entity entity1 = (Entity)list.get(l);
/*     */         
/* 200 */         if ((entity1.func_70067_L()) && ((entity1.func_145782_y() != this.shootingEntityId) || (this.ticksInAir >= 5)))
/*     */         {
/* 202 */           float f1 = 0.3F;
/* 203 */           AxisAlignedBB axisalignedbb1 = entity1.field_70121_D.func_72314_b(f1, f1, f1);
/* 204 */           MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_72327_a(vec3, vec31);
/*     */           
/* 206 */           if (movingobjectposition1 != null)
/*     */           {
/* 208 */             double d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f);
/*     */             
/* 210 */             if ((d1 < d0) || (d0 == 0.0D))
/*     */             {
/* 212 */               entity = entity1;
/* 213 */               d0 = d1;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 219 */       if (entity != null)
/*     */       {
/* 221 */         movingobjectposition = new MovingObjectPosition(entity);
/*     */       }
/*     */       
/* 224 */       if ((movingobjectposition != null) && (movingobjectposition.field_72308_g != null) && ((movingobjectposition.field_72308_g instanceof EntityPlayer)))
/*     */       {
/* 226 */         EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.field_72308_g;
/*     */         
/* 228 */         if ((entityplayer.field_71075_bZ.field_75102_a) || (((this.field_70250_c instanceof EntityPlayer)) && (!((EntityPlayer)this.field_70250_c).func_96122_a(entityplayer))))
/*     */         {
/* 230 */           movingobjectposition = null;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 237 */       if (movingobjectposition != null)
/*     */       {
/* 239 */         if (movingobjectposition.field_72308_g != null)
/*     */         {
/*     */ 
/*     */ 
/* 243 */           if (inflictDamage(movingobjectposition))
/*     */           {
/* 245 */             if ((movingobjectposition.field_72308_g instanceof EntityLivingBase))
/*     */             {
/* 247 */               EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.field_72308_g;
/*     */               
/* 249 */               if (this.knockbackStrength > 0)
/*     */               {
/* 251 */                 float f3 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */                 
/* 253 */                 if (f3 > 0.0F)
/*     */                 {
/* 255 */                   movingobjectposition.field_72308_g.func_70024_g(this.field_70159_w * this.knockbackStrength * 0.6000000238418579D / f3, 0.1D, this.field_70179_y * this.knockbackStrength * 0.6000000238418579D / f3);
/*     */                 }
/*     */               }
/*     */               
/* 259 */               if ((this.field_70250_c != null) && ((this.field_70250_c instanceof EntityLivingBase)))
/*     */               {
/* 261 */                 EnchantmentHelper.func_151384_a(entitylivingbase, this.field_70250_c);
/* 262 */                 EnchantmentHelper.func_151385_b((EntityLivingBase)this.field_70250_c, entitylivingbase);
/*     */               }
/*     */               
/* 265 */               if ((this.field_70250_c != null) && (movingobjectposition.field_72308_g != this.field_70250_c) && ((movingobjectposition.field_72308_g instanceof EntityPlayer)) && ((this.field_70250_c instanceof EntityPlayerMP)))
/*     */               {
/* 267 */                 ((EntityPlayerMP)this.field_70250_c).field_71135_a.func_147359_a(new S2BPacketChangeGameState(6, 0.0F));
/*     */               }
/*     */             }
/*     */             
/* 271 */             func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/*     */             
/* 273 */             if (!(movingobjectposition.field_72308_g instanceof EntityEnderman))
/*     */             {
/* 275 */               func_70106_y();
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 280 */             this.field_70159_w *= -0.10000000149011612D;
/* 281 */             this.field_70181_x *= -0.10000000149011612D;
/* 282 */             this.field_70179_y *= -0.10000000149011612D;
/* 283 */             this.field_70177_z += 180.0F;
/* 284 */             this.field_70126_B += 180.0F;
/* 285 */             this.ticksInAir = 0;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 290 */           this.xTile = movingobjectposition.field_72311_b;
/* 291 */           this.yTile = movingobjectposition.field_72312_c;
/* 292 */           this.zTile = movingobjectposition.field_72309_d;
/* 293 */           this.inTile = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
/* 294 */           this.inData = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
/* 295 */           this.field_70159_w = ((float)(movingobjectposition.field_72307_f.field_72450_a - this.field_70165_t));
/* 296 */           this.field_70181_x = ((float)(movingobjectposition.field_72307_f.field_72448_b - this.field_70163_u));
/* 297 */           this.field_70179_y = ((float)(movingobjectposition.field_72307_f.field_72449_c - this.field_70161_v));
/* 298 */           float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 299 */           this.field_70165_t -= this.field_70159_w / f2 * 0.05000000074505806D;
/* 300 */           this.field_70163_u -= this.field_70181_x / f2 * 0.05000000074505806D;
/* 301 */           this.field_70161_v -= this.field_70179_y / f2 * 0.05000000074505806D;
/* 302 */           func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/* 303 */           this.inGround = true;
/* 304 */           this.field_70249_b = 7;
/* 305 */           func_70243_d(false);
/* 306 */           if (this.inTile.isAir(this.field_70170_p, this.xTile, this.yTile, this.zTile))
/*     */           {
/* 308 */             this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.yTile, this.zTile, this);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 313 */       if (func_70241_g())
/*     */       {
/* 315 */         for (l = 0; l < 4; l++)
/*     */         {
/* 317 */           this.field_70170_p.func_72869_a("crit", this.field_70165_t + this.field_70159_w * l / 4.0D, this.field_70163_u + this.field_70181_x * l / 4.0D, this.field_70161_v + this.field_70179_y * l / 4.0D, -this.field_70159_w, -this.field_70181_x + 0.2D, -this.field_70179_y);
/*     */         }
/*     */       }
/*     */       
/* 321 */       this.field_70165_t += this.field_70159_w;
/* 322 */       this.field_70163_u += this.field_70181_x;
/* 323 */       this.field_70161_v += this.field_70179_y;
/* 324 */       float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 325 */       this.field_70177_z = ((float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/*     */       
/* 327 */       for (this.field_70125_A = ((float)(Math.atan2(this.field_70181_x, f2) * 180.0D / 3.141592653589793D)); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {}
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 332 */       while (this.field_70125_A - this.field_70127_C >= 180.0F)
/*     */       {
/* 334 */         this.field_70127_C += 360.0F;
/*     */       }
/*     */       
/* 337 */       while (this.field_70177_z - this.field_70126_B < -180.0F)
/*     */       {
/* 339 */         this.field_70126_B -= 360.0F;
/*     */       }
/*     */       
/* 342 */       while (this.field_70177_z - this.field_70126_B >= 180.0F)
/*     */       {
/* 344 */         this.field_70126_B += 360.0F;
/*     */       }
/*     */       
/* 347 */       this.field_70125_A = (this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F);
/* 348 */       this.field_70177_z = (this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F);
/* 349 */       float f4 = 0.99F;
/* 350 */       float f1 = 0.05F;
/*     */       
/* 352 */       if (func_70090_H())
/*     */       {
/* 354 */         for (int j1 = 0; j1 < 4; j1++)
/*     */         {
/* 356 */           float f3 = 0.25F;
/* 357 */           this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f3, this.field_70163_u - this.field_70181_x * f3, this.field_70161_v - this.field_70179_y * f3, this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */         }
/*     */         
/* 360 */         f4 = 0.8F;
/*     */       }
/*     */       
/* 363 */       this.field_70159_w *= f4;
/* 364 */       this.field_70181_x *= f4;
/* 365 */       this.field_70179_y *= f4;
/* 366 */       this.field_70181_x -= f1;
/* 367 */       func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 368 */       func_145775_I();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean inflictDamage(MovingObjectPosition movingobjectposition)
/*     */   {
/* 375 */     float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 376 */     int i1 = MathHelper.func_76143_f(f2 * func_70242_d());
/* 377 */     int fire = (func_70027_ad()) && (this.type != 2) ? 5 : 0;
/* 378 */     if (func_70241_g())
/*     */     {
/* 380 */       i1 += this.field_70146_Z.nextInt(i1 / 2 + 2);
/*     */     }
/*     */     
/* 383 */     DamageSource damagesource = null;
/*     */     
/* 385 */     switch (this.type) {
/*     */     case 0: 
/* 387 */       if (this.field_70250_c == null)
/*     */       {
/* 389 */         damagesource = new DamageSourceIndirectThaumcraftEntity("airarrow", this, this).func_76348_h().func_82726_p().func_76349_b();
/*     */       }
/*     */       else
/*     */       {
/* 393 */         damagesource = new DamageSourceIndirectThaumcraftEntity("airarrow", this, this.field_70250_c).func_76348_h().func_82726_p().func_76349_b();
/*     */       }
/* 395 */       break;
/*     */     
/*     */     case 1: 
/* 398 */       fire += 5;
/* 399 */       if (this.field_70250_c == null)
/*     */       {
/* 401 */         damagesource = new DamageSourceIndirectThaumcraftEntity("firearrow", this, this).func_76361_j().func_76349_b();
/*     */       }
/*     */       else
/*     */       {
/* 405 */         damagesource = new DamageSourceIndirectThaumcraftEntity("firearrow", this, this.field_70250_c).func_76361_j().func_76349_b();
/*     */       }
/* 407 */       break;
/*     */     
/*     */     case 4: 
/* 410 */       if (this.field_70250_c == null)
/*     */       {
/* 412 */         damagesource = new DamageSourceIndirectThaumcraftEntity("orderarrow", this, this).func_76348_h().func_82726_p().func_76349_b();
/*     */       }
/*     */       else
/*     */       {
/* 416 */         damagesource = new DamageSourceIndirectThaumcraftEntity("orderarrow", this, this.field_70250_c).func_76348_h().func_82726_p().func_76349_b();
/*     */       }
/* 418 */       if (!(movingobjectposition.field_72308_g instanceof EntityLivingBase)) break label479;
/* 419 */       ((EntityLivingBase)movingobjectposition.field_72308_g).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 200, 4)); break;
/*     */     
/*     */ 
/*     */ 
/*     */     case 2: 
/* 424 */       if ((movingobjectposition.field_72308_g instanceof EntityLivingBase)) {
/* 425 */         ((EntityLivingBase)movingobjectposition.field_72308_g).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 200, 4));
/*     */       }
/*     */     
/*     */     case 5: 
/* 429 */       if ((this.type == 5) && ((movingobjectposition.field_72308_g instanceof EntityLivingBase))) {
/* 430 */         ((EntityLivingBase)movingobjectposition.field_72308_g).func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 100));
/*     */       }
/*     */       break;
/*     */     }
/* 434 */     if (this.field_70250_c == null)
/*     */     {
/* 436 */       damagesource = new EntityDamageSourceIndirect("arrow", this, this).func_76349_b();
/*     */     }
/*     */     else
/*     */     {
/* 440 */       damagesource = new EntityDamageSourceIndirect("arrow", this, this.field_70250_c).func_76349_b();
/*     */     }
/*     */     
/*     */     label479:
/*     */     
/* 445 */     if ((fire > 0) && (!(movingobjectposition.field_72308_g instanceof EntityEnderman)))
/*     */     {
/* 447 */       movingobjectposition.field_72308_g.func_70015_d(fire);
/*     */     }
/*     */     
/*     */ 
/* 451 */     return movingobjectposition.field_72308_g.func_70097_a(damagesource, i1);
/*     */   }
/*     */   
/*     */   public double func_70242_d()
/*     */   {
/* 456 */     switch (this.type) {
/* 457 */     case 3:  return this.damage * 1.5D;
/* 458 */     case 4:  return this.damage * 0.8D;
/* 459 */     case 5:  return this.damage * 0.8D;
/*     */     }
/* 461 */     return this.damage;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 468 */     super.func_70014_b(par1NBTTagCompound);
/* 469 */     par1NBTTagCompound.func_74774_a("type", (byte)this.type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 478 */     super.func_70037_a(par1NBTTagCompound);
/* 479 */     this.type = par1NBTTagCompound.func_74771_c("type");
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityPrimalArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */