/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class FXBoreParticles extends EntityFX
/*     */ {
/*     */   private Block blockInstance;
/*     */   private Item itemInstance;
/*     */   private int metadata;
/*     */   private int side;
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   
/*     */   public FXBoreParticles(World par1World, double par2, double par4, double par6, double tx, double ty, double tz, Block par14Block, int par15, int par16)
/*     */   {
/*  29 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  30 */     this.blockInstance = par14Block;
/*  31 */     func_110125_a(par14Block.func_149691_a(par15, par16));
/*     */     
/*  33 */     this.field_70545_g = par14Block.field_149763_I;
/*  34 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 0.6F);
/*  35 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.3F + 0.4F);
/*  36 */     this.side = par15;
/*     */     
/*  38 */     this.targetX = tx;
/*  39 */     this.targetY = ty;
/*  40 */     this.targetZ = tz;
/*     */     
/*  42 */     double dx = tx - this.field_70165_t;
/*  43 */     double dy = ty - this.field_70163_u;
/*  44 */     double dz = tz - this.field_70161_v;
/*  45 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 3.0F);
/*  46 */     if (base < 1) base = 1;
/*  47 */     this.field_70547_e = (base / 2 + this.field_70146_Z.nextInt(base));
/*     */     
/*  49 */     float f3 = 0.01F;
/*  50 */     this.field_70159_w = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*  51 */     this.field_70181_x = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*  52 */     this.field_70179_y = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*     */     
/*     */ 
/*  55 */     this.field_70545_g = 0.2F;
/*  56 */     this.field_70145_X = false;
/*     */     
/*  58 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  59 */     int visibleDistance = 64;
/*  60 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 32;
/*  61 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) this.field_70547_e = 0;
/*     */   }
/*     */   
/*     */   public FXBoreParticles(World par1World, double par2, double par4, double par6, double tx, double ty, double tz, Item item, int par15, int par16)
/*     */   {
/*  66 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  67 */     this.itemInstance = item;
/*  68 */     func_110125_a(item.func_77617_a(par16));
/*  69 */     this.metadata = par16;
/*  70 */     this.field_70545_g = Blocks.field_150431_aC.field_149763_I;
/*  71 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 0.6F);
/*  72 */     this.field_70544_f = (this.field_70146_Z.nextFloat() * 0.3F + 0.4F);
/*  73 */     this.side = par15;
/*     */     
/*  75 */     this.targetX = tx;
/*  76 */     this.targetY = ty;
/*  77 */     this.targetZ = tz;
/*     */     
/*  79 */     double dx = tx - this.field_70165_t;
/*  80 */     double dy = ty - this.field_70163_u;
/*  81 */     double dz = tz - this.field_70161_v;
/*  82 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 3.0F);
/*  83 */     if (base < 1) base = 1;
/*  84 */     this.field_70547_e = (base / 2 + this.field_70146_Z.nextInt(base));
/*     */     
/*  86 */     float f3 = 0.01F;
/*  87 */     this.field_70159_w = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*  88 */     this.field_70181_x = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*  89 */     this.field_70179_y = ((float)this.field_70170_p.field_73012_v.nextGaussian() * f3);
/*     */     
/*     */ 
/*  92 */     this.field_70545_g = 0.2F;
/*  93 */     this.field_70145_X = false;
/*     */     
/*  95 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  96 */     int visibleDistance = 64;
/*  97 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 32;
/*  98 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) this.field_70547_e = 0;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 103 */     this.field_70169_q = this.field_70165_t;
/* 104 */     this.field_70167_r = this.field_70163_u;
/* 105 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*     */ 
/*     */ 
/* 109 */     if ((this.field_70546_d++ >= this.field_70547_e) || ((MathHelper.func_76128_c(this.field_70165_t) == MathHelper.func_76128_c(this.targetX)) && (MathHelper.func_76128_c(this.field_70163_u) == MathHelper.func_76128_c(this.targetY)) && (MathHelper.func_76128_c(this.field_70161_v) == MathHelper.func_76128_c(this.targetZ))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 114 */       func_70106_y();
/* 115 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 120 */     if (!this.field_70145_X) pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 121 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 123 */     this.field_70159_w *= 0.985D;
/* 124 */     this.field_70181_x *= 0.985D;
/* 125 */     this.field_70179_y *= 0.985D;
/*     */     
/* 127 */     double dx = this.targetX - this.field_70165_t;
/* 128 */     double dy = this.targetY - this.field_70163_u;
/* 129 */     double dz = this.targetZ - this.field_70161_v;
/* 130 */     double d13 = 0.3D;
/* 131 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 133 */     if (d11 < 4.0D) {
/* 134 */       this.field_70544_f *= 0.9F;
/* 135 */       d13 = 0.6D;
/*     */     }
/*     */     
/* 138 */     dx /= d11;
/* 139 */     dy /= d11;
/* 140 */     dz /= d11;
/*     */     
/* 142 */     this.field_70159_w += dx * d13;
/* 143 */     this.field_70181_x += dy * d13;
/* 144 */     this.field_70179_y += dz * d13;
/*     */     
/* 146 */     this.field_70159_w = MathHelper.func_76131_a((float)this.field_70159_w, -0.35F, 0.35F);
/* 147 */     this.field_70181_x = MathHelper.func_76131_a((float)this.field_70181_x, -0.35F, 0.35F);
/* 148 */     this.field_70179_y = MathHelper.func_76131_a((float)this.field_70179_y, -0.35F, 0.35F);
/*     */   }
/*     */   
/*     */ 
/*     */   public FXBoreParticles func_70596_a(int par1, int par2, int par3)
/*     */   {
/* 154 */     if ((this.blockInstance != null) && (this.field_70170_p.func_147439_a(par1, par2, par3) == this.blockInstance)) {
/* 155 */       if ((this.blockInstance == Blocks.field_150349_c) && (this.side != 1))
/*     */       {
/* 157 */         return this;
/*     */       }
/*     */       
/*     */ 
/*     */       try
/*     */       {
/* 163 */         int var4 = this.blockInstance.func_149720_d(this.field_70170_p, par1, par2, par3);
/*     */         
/* 165 */         this.field_70552_h *= (var4 >> 16 & 0xFF) / 255.0F;
/* 166 */         this.field_70553_i *= (var4 >> 8 & 0xFF) / 255.0F;
/* 167 */         this.field_70551_j *= (var4 & 0xFF) / 255.0F;
/*     */       } catch (Exception e) {}
/* 169 */       return this;
/*     */     }
/*     */     try
/*     */     {
/* 173 */       int var4 = this.itemInstance.func_82790_a(new net.minecraft.item.ItemStack(this.itemInstance, 1, this.metadata), 0);
/* 174 */       this.field_70552_h *= (var4 >> 16 & 0xFF) / 255.0F;
/* 175 */       this.field_70553_i *= (var4 >> 8 & 0xFF) / 255.0F;
/* 176 */       this.field_70551_j *= (var4 & 0xFF) / 255.0F;
/*     */     } catch (Exception e) {}
/* 178 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FXBoreParticles applyRenderColor(int par1)
/*     */   {
/* 187 */     if (this.blockInstance != null) {
/* 188 */       if (this.blockInstance == Blocks.field_150349_c)
/*     */       {
/* 190 */         return this;
/*     */       }
/*     */       
/*     */ 
/* 194 */       int var2 = this.blockInstance.func_149741_i(par1);
/* 195 */       this.field_70552_h *= (var2 >> 16 & 0xFF) / 255.0F;
/* 196 */       this.field_70553_i *= (var2 >> 8 & 0xFF) / 255.0F;
/* 197 */       this.field_70551_j *= (var2 & 0xFF) / 255.0F;
/* 198 */       return this;
/*     */     }
/*     */     
/* 201 */     int var4 = this.itemInstance.func_82790_a(new net.minecraft.item.ItemStack(this.itemInstance, 1, this.metadata), this.metadata);
/* 202 */     this.field_70552_h *= (var4 >> 16 & 0xFF) / 255.0F;
/* 203 */     this.field_70553_i *= (var4 >> 8 & 0xFF) / 255.0F;
/* 204 */     this.field_70551_j *= (var4 & 0xFF) / 255.0F;
/* 205 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70537_b()
/*     */   {
/* 211 */     return this.blockInstance != null ? 1 : 2;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/* 217 */     float f6 = (this.field_94054_b + this.field_70548_b / 4.0F) / 16.0F;
/* 218 */     float f7 = f6 + 0.015609375F;
/* 219 */     float f8 = (this.field_94055_c + this.field_70549_c / 4.0F) / 16.0F;
/* 220 */     float f9 = f8 + 0.015609375F;
/* 221 */     float f10 = 0.1F * this.field_70544_f;
/*     */     
/* 223 */     if (this.field_70550_a != null)
/*     */     {
/* 225 */       f6 = this.field_70550_a.func_94214_a(this.field_70548_b / 4.0F * 16.0F);
/* 226 */       f7 = this.field_70550_a.func_94214_a((this.field_70548_b + 1.0F) / 4.0F * 16.0F);
/* 227 */       f8 = this.field_70550_a.func_94207_b(this.field_70549_c / 4.0F * 16.0F);
/* 228 */       f9 = this.field_70550_a.func_94207_b((this.field_70549_c + 1.0F) / 4.0F * 16.0F);
/*     */     }
/*     */     
/* 231 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * par2 - field_70556_an);
/* 232 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * par2 - field_70554_ao);
/* 233 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * par2 - field_70555_ap);
/* 234 */     float f14 = 1.0F;
/* 235 */     par1Tessellator.func_78386_a(f14 * this.field_70552_h, f14 * this.field_70553_i, f14 * this.field_70551_j);
/* 236 */     par1Tessellator.func_78374_a(f11 - par3 * f10 - par6 * f10, f12 - par4 * f10, f13 - par5 * f10 - par7 * f10, f6, f9);
/* 237 */     par1Tessellator.func_78374_a(f11 - par3 * f10 + par6 * f10, f12 + par4 * f10, f13 - par5 * f10 + par7 * f10, f6, f8);
/* 238 */     par1Tessellator.func_78374_a(f11 + par3 * f10 + par6 * f10, f12 + par4 * f10, f13 + par5 * f10 + par7 * f10, f7, f8);
/* 239 */     par1Tessellator.func_78374_a(f11 + par3 * f10 - par6 * f10, f12 - par4 * f10, f13 + par5 * f10 - par7 * f10, f7, f9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean pushOutOfBlocks(double par1, double par3, double par5)
/*     */   {
/* 246 */     int var7 = MathHelper.func_76128_c(par1);
/* 247 */     int var8 = MathHelper.func_76128_c(par3);
/* 248 */     int var9 = MathHelper.func_76128_c(par5);
/* 249 */     double var10 = par1 - var7;
/* 250 */     double var12 = par3 - var8;
/* 251 */     double var14 = par5 - var9;
/*     */     
/* 253 */     if ((!this.field_70170_p.func_147437_c(var7, var8, var9)) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */     {
/* 255 */       boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, true);
/* 256 */       boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, true);
/* 257 */       boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, true);
/* 258 */       boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, true);
/* 259 */       boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, true);
/* 260 */       boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, true);
/* 261 */       byte var22 = -1;
/* 262 */       double var23 = 9999.0D;
/*     */       
/* 264 */       if ((var16) && (var10 < var23))
/*     */       {
/* 266 */         var23 = var10;
/* 267 */         var22 = 0;
/*     */       }
/*     */       
/* 270 */       if ((var17) && (1.0D - var10 < var23))
/*     */       {
/* 272 */         var23 = 1.0D - var10;
/* 273 */         var22 = 1;
/*     */       }
/*     */       
/* 276 */       if ((var18) && (var12 < var23))
/*     */       {
/* 278 */         var23 = var12;
/* 279 */         var22 = 2;
/*     */       }
/*     */       
/* 282 */       if ((var19) && (1.0D - var12 < var23))
/*     */       {
/* 284 */         var23 = 1.0D - var12;
/* 285 */         var22 = 3;
/*     */       }
/*     */       
/* 288 */       if ((var20) && (var14 < var23))
/*     */       {
/* 290 */         var23 = var14;
/* 291 */         var22 = 4;
/*     */       }
/*     */       
/* 294 */       if ((var21) && (1.0D - var14 < var23))
/*     */       {
/* 296 */         var23 = 1.0D - var14;
/* 297 */         var22 = 5;
/*     */       }
/*     */       
/* 300 */       float var25 = this.field_70146_Z.nextFloat() * 0.05F + 0.025F;
/* 301 */       float var26 = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F;
/*     */       
/* 303 */       if (var22 == 0)
/*     */       {
/* 305 */         this.field_70159_w = (-var25);
/* 306 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 309 */       if (var22 == 1)
/*     */       {
/* 311 */         this.field_70159_w = var25;
/* 312 */         this.field_70181_x = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 315 */       if (var22 == 2)
/*     */       {
/* 317 */         this.field_70181_x = (-var25);
/* 318 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 321 */       if (var22 == 3)
/*     */       {
/* 323 */         this.field_70181_x = var25;
/* 324 */         this.field_70159_w = (this.field_70179_y = var26);
/*     */       }
/*     */       
/* 327 */       if (var22 == 4)
/*     */       {
/* 329 */         this.field_70179_y = (-var25);
/* 330 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 333 */       if (var22 == 5)
/*     */       {
/* 335 */         this.field_70179_y = var25;
/* 336 */         this.field_70181_x = (this.field_70159_w = var26);
/*     */       }
/*     */       
/* 339 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 343 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXBoreParticles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */