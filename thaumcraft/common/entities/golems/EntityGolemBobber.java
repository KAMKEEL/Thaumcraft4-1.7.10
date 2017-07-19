/*     */ package thaumcraft.common.entities.golems;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ public class EntityGolemBobber extends Entity implements cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*  19 */   private int xTile = -1;
/*  20 */   private int yTile = -1;
/*  21 */   private int zTile = -1;
/*  22 */   private int inTile = 0;
/*  23 */   private int inData = 0;
/*  24 */   private boolean inGround = false;
/*  25 */   private boolean inBlock = false;
/*  26 */   public EntityGolemBase fisher = null;
/*     */   
/*     */   private int field_146045_ax;
/*     */   private int field_146040_ay;
/*     */   private float field_146054_aA;
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  34 */     data.writeDouble(this.field_70159_w);
/*  35 */     data.writeDouble(this.field_70181_x);
/*  36 */     data.writeDouble(this.field_70179_y);
/*  37 */     data.writeInt(this.fisher != null ? this.fisher.func_145782_y() : -1);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*  42 */     this.field_70159_w = data.readDouble();
/*  43 */     this.field_70181_x = data.readDouble();
/*  44 */     this.field_70179_y = data.readDouble();
/*  45 */     int fid = data.readInt();
/*  46 */     if (fid >= 0) {
/*  47 */       this.fisher = ((EntityGolemBase)this.field_70170_p.func_73045_a(fid));
/*     */     }
/*     */   }
/*     */   
/*     */   public EntityGolemBobber(World par1World)
/*     */   {
/*  53 */     super(par1World);
/*  54 */     func_70105_a(0.25F, 0.25F);
/*  55 */     this.field_70158_ak = true;
/*  56 */     this.field_70159_w = 0.0D;
/*  57 */     this.field_70181_x = 0.0D;
/*  58 */     this.field_70179_y = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EntityGolemBobber(World par1World, EntityGolemBase par2EntityLiving, int x, int y, int z)
/*     */   {
/*  65 */     super(par1World);
/*  66 */     func_70105_a(0.25F, 0.25F);
/*     */     
/*  68 */     this.fisher = par2EntityLiving;
/*  69 */     this.field_70158_ak = true;
/*     */     
/*  71 */     double d1 = x + 0.5D - this.fisher.field_70165_t;
/*  72 */     double d3 = y + 1 - this.fisher.field_70163_u;
/*  73 */     double d5 = z + 0.5D - this.fisher.field_70161_v;
/*  74 */     double d7 = MathHelper.func_76133_a(d1 * d1 + d3 * d3 + d5 * d5);
/*  75 */     double d9 = 0.1D;
/*  76 */     this.field_70159_w = (d1 * d9);
/*  77 */     this.field_70181_x = (d3 * d9 + MathHelper.func_76133_a(d7) * 0.08D);
/*  78 */     this.field_70179_y = (d5 * d9);
/*     */     
/*  80 */     func_70107_b(this.fisher.field_70165_t, this.fisher.field_70163_u, this.fisher.field_70161_v);
/*  81 */     this.field_70129_M = 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public boolean func_70112_a(double par1)
/*     */   {
/*  88 */     double d1 = this.field_70121_D.func_72320_b() * 4.0D;
/*  89 */     d1 *= 64.0D;
/*  90 */     return par1 < d1 * d1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  96 */     super.func_70071_h_();
/*     */     
/*  98 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 100 */       if ((this.fisher == null) || (!this.fisher.func_70089_S()))
/*     */       {
/* 102 */         func_70106_y();
/* 103 */         return;
/*     */       }
/*     */       
/* 106 */       if (this.field_70146_Z.nextFloat() < 0.02F) {
/* 107 */         ((WorldServer)this.field_70170_p).func_147487_a("splash", this.field_70165_t + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat(), this.field_70163_u + this.field_70146_Z.nextFloat(), this.field_70161_v + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat(), 2 + this.field_70146_Z.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 113 */     if (this.field_70173_aa++ > 4000) {
/* 114 */       func_70106_y();
/* 115 */       return;
/*     */     }
/*     */     
/* 118 */     if (this.inBlock)
/*     */     {
/* 120 */       this.inBlock = false;
/* 121 */       this.field_70159_w *= this.field_70146_Z.nextFloat() * 0.2F;
/* 122 */       this.field_70181_x *= this.field_70146_Z.nextFloat() * 0.2F;
/* 123 */       this.field_70179_y *= this.field_70146_Z.nextFloat() * 0.2F;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 129 */     Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 130 */     Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/* 131 */     MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec31, vec3);
/* 132 */     vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 133 */     vec3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */     
/* 135 */     if (movingobjectposition != null)
/*     */     {
/* 137 */       vec3 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
/*     */       
/* 139 */       if (movingobjectposition.field_72308_g == null)
/*     */       {
/* 141 */         this.inBlock = true;
/* 142 */         if (this.field_70170_p.func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d).func_149688_o() != Material.field_151586_h) {
/* 143 */           func_70106_y();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 150 */     if (!this.inBlock)
/*     */     {
/* 152 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 153 */       float f5 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 154 */       this.field_70177_z = ((float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D));
/*     */       
/* 156 */       for (this.field_70125_A = ((float)(Math.atan2(this.field_70181_x, f5) * 180.0D / 3.141592653589793D)); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {}
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 161 */       while (this.field_70125_A - this.field_70127_C >= 180.0F)
/*     */       {
/* 163 */         this.field_70127_C += 360.0F;
/*     */       }
/*     */       
/* 166 */       while (this.field_70177_z - this.field_70126_B < -180.0F)
/*     */       {
/* 168 */         this.field_70126_B -= 360.0F;
/*     */       }
/*     */       
/* 171 */       while (this.field_70177_z - this.field_70126_B >= 180.0F)
/*     */       {
/* 173 */         this.field_70126_B += 360.0F;
/*     */       }
/*     */       
/* 176 */       this.field_70125_A = (this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F);
/* 177 */       this.field_70177_z = (this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F);
/* 178 */       float f6 = 0.92F;
/*     */       
/* 180 */       if ((this.field_70122_E) || (this.field_70123_F))
/*     */       {
/* 182 */         f6 = 0.5F;
/*     */       }
/*     */       
/* 185 */       byte b0 = 5;
/* 186 */       double d10 = 0.0D;
/*     */       
/* 188 */       for (int j = 0; j < b0; j++)
/*     */       {
/* 190 */         double d3 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (j + 0) / b0 - 0.125D + 0.125D;
/* 191 */         double d4 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (j + 1) / b0 - 0.125D + 0.125D;
/* 192 */         AxisAlignedBB axisalignedbb1 = AxisAlignedBB.func_72330_a(this.field_70121_D.field_72340_a, d3, this.field_70121_D.field_72339_c, this.field_70121_D.field_72336_d, d4, this.field_70121_D.field_72334_f);
/*     */         
/* 194 */         if (this.field_70170_p.func_72830_b(axisalignedbb1, Material.field_151586_h))
/*     */         {
/* 196 */           d10 += 1.0D / b0;
/*     */         }
/*     */       }
/*     */       
/* 200 */       if ((!this.field_70170_p.field_72995_K) && (d10 > 0.0D))
/*     */       {
/* 202 */         WorldServer worldserver = (WorldServer)this.field_70170_p;
/* 203 */         int k = 1;
/*     */         
/* 205 */         if ((this.field_70146_Z.nextFloat() < 0.25F) && (this.field_70170_p.func_72951_B(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u) + 1, MathHelper.func_76128_c(this.field_70161_v))))
/*     */         {
/* 207 */           k = 2;
/*     */         }
/*     */         
/* 210 */         if ((this.field_70146_Z.nextFloat() < 0.5F) && (!this.field_70170_p.func_72937_j(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u) + 1, MathHelper.func_76128_c(this.field_70161_v))))
/*     */         {
/* 212 */           k--;
/*     */         }
/*     */         
/* 215 */         if (this.field_146045_ax > 0)
/*     */         {
/* 217 */           this.field_146045_ax -= 1;
/*     */           
/* 219 */           if (this.field_146045_ax <= 0)
/*     */           {
/* 221 */             this.field_146040_ay = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/* 233 */         else if (this.field_146040_ay > 0)
/*     */         {
/* 235 */           this.field_146040_ay -= k;
/* 236 */           float f1 = 0.15F;
/*     */           
/* 238 */           if (this.field_146040_ay < 20)
/*     */           {
/* 240 */             f1 = (float)(f1 + (20 - this.field_146040_ay) * 0.05D);
/*     */           }
/* 242 */           else if (this.field_146040_ay < 40)
/*     */           {
/* 244 */             f1 = (float)(f1 + (40 - this.field_146040_ay) * 0.02D);
/*     */           }
/* 246 */           else if (this.field_146040_ay < 60)
/*     */           {
/* 248 */             f1 = (float)(f1 + (60 - this.field_146040_ay) * 0.01D);
/*     */           }
/*     */           
/* 251 */           if (this.field_70146_Z.nextFloat() < f1)
/*     */           {
/* 253 */             float f7 = MathHelper.func_151240_a(this.field_70146_Z, 0.0F, 360.0F) * 0.017453292F;
/* 254 */             float f2 = MathHelper.func_151240_a(this.field_70146_Z, 25.0F, 60.0F);
/* 255 */             double d11 = this.field_70165_t + MathHelper.func_76126_a(f7) * f2 * 0.1F;
/* 256 */             double d5 = MathHelper.func_76128_c(this.field_70121_D.field_72338_b) + 1.0F;
/* 257 */             double d6 = this.field_70161_v + MathHelper.func_76134_b(f7) * f2 * 0.1F;
/* 258 */             worldserver.func_147487_a("splash", d11, d5, d6, 2 + this.field_70146_Z.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D);
/*     */           }
/*     */           
/* 261 */           if (this.field_146040_ay <= 0)
/*     */           {
/* 263 */             this.field_146054_aA = MathHelper.func_151240_a(this.field_70146_Z, 0.0F, 360.0F);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 268 */         if (this.field_146045_ax > 0)
/*     */         {
/* 270 */           this.field_70181_x -= this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat() * 0.2D;
/*     */         }
/*     */       }
/*     */       
/* 274 */       double d2 = d10 * 2.0D - 1.0D;
/* 275 */       this.field_70181_x += 0.03999999910593033D * d2;
/*     */       
/* 277 */       if (d10 > 0.0D)
/*     */       {
/* 279 */         f6 = (float)(f6 * 0.9D);
/* 280 */         this.field_70181_x *= 0.8D;
/*     */       }
/*     */       
/* 283 */       this.field_70159_w *= f6;
/* 284 */       this.field_70181_x *= f6;
/* 285 */       this.field_70179_y *= f6;
/* 286 */       func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70088_a() {}
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound var1) {}
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound var1) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/EntityGolemBobber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */