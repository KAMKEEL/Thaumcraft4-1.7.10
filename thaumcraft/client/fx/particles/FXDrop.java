/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLiquid;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.profiler.Profiler;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class FXDrop
/*     */   extends EntityFX
/*     */ {
/*     */   int bobTimer;
/*     */   
/*     */   public FXDrop(World par1World, double par2, double par4, double par6, float r, float g, float b)
/*     */   {
/*  32 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  33 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*     */     
/*  35 */     this.field_70552_h = r;
/*  36 */     this.field_70553_i = g;
/*  37 */     this.field_70551_j = b;
/*     */     
/*  39 */     func_70536_a(113);
/*     */     
/*  41 */     this.field_70545_g = 0.06F;
/*  42 */     this.bobTimer = 40;
/*  43 */     this.field_70547_e = ((int)(64.0D / (Math.random() * 0.8D + 0.2D)));
/*  44 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70070_b(float par1)
/*     */   {
/*  50 */     return 257;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70013_c(float par1)
/*     */   {
/*  59 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  68 */     this.field_70169_q = this.field_70165_t;
/*  69 */     this.field_70167_r = this.field_70163_u;
/*  70 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  78 */     this.field_70181_x -= this.field_70545_g;
/*     */     
/*  80 */     if (this.bobTimer-- > 0)
/*     */     {
/*  82 */       this.field_70159_w *= 0.02D;
/*  83 */       this.field_70181_x *= 0.02D;
/*  84 */       this.field_70179_y *= 0.02D;
/*  85 */       func_70536_a(113);
/*     */     }
/*     */     else
/*     */     {
/*  89 */       func_70536_a(112);
/*     */     }
/*     */     
/*  92 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/*  94 */     this.field_70159_w *= 0.9800000190734863D;
/*  95 */     this.field_70181_x *= 0.9800000190734863D;
/*  96 */     this.field_70179_y *= 0.9800000190734863D;
/*     */     
/*  98 */     if (this.field_70547_e-- <= 0)
/*     */     {
/* 100 */       func_70106_y();
/*     */     }
/*     */     
/* 103 */     if (this.field_70122_E)
/*     */     {
/*     */ 
/* 106 */       func_70536_a(114);
/*     */       
/* 108 */       this.field_70159_w *= 0.699999988079071D;
/* 109 */       this.field_70179_y *= 0.699999988079071D;
/*     */     }
/*     */     
/* 112 */     Material material = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)).func_149688_o();
/*     */     
/* 114 */     if ((material != Material.field_151592_s) && ((material.func_76224_d()) || (material.func_76220_a())))
/*     */     {
/* 116 */       double d0 = MathHelper.func_76128_c(this.field_70163_u) + 1 - BlockLiquid.func_149801_b(this.field_70170_p.func_72805_g(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)));
/*     */       
/* 118 */       if (this.field_70163_u < d0)
/*     */       {
/* 120 */         func_70106_y();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70091_d(double par1, double par3, double par5)
/*     */   {
/* 128 */     int x = MathHelper.func_76128_c(this.field_70165_t);
/* 129 */     int y = MathHelper.func_76128_c(this.field_70163_u);
/* 130 */     int z = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 132 */     if ((this.field_70145_X) || (this.field_70170_p.func_147439_a(x, y, z) == ConfigBlocks.blockJar))
/*     */     {
/* 134 */       this.field_70121_D.func_72317_d(par1, par3, par5);
/* 135 */       this.field_70165_t = ((this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D);
/* 136 */       this.field_70163_u = (this.field_70121_D.field_72338_b + this.field_70129_M - this.field_70139_V);
/* 137 */       this.field_70161_v = ((this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D);
/*     */       
/* 139 */       x = MathHelper.func_76128_c(this.field_70165_t);
/* 140 */       y = MathHelper.func_76128_c(this.field_70163_u);
/* 141 */       y = MathHelper.func_76128_c(this.field_70163_u);
/*     */       
/* 143 */       if (this.field_70170_p.func_147439_a(x, y + 1, z) == ConfigBlocks.blockJar) {
/* 144 */         this.field_70165_t = this.field_70169_q;
/* 145 */         this.field_70163_u = this.field_70167_r;
/* 146 */         this.field_70161_v = this.field_70166_s;
/* 147 */         this.field_70181_x = 0.0D;
/* 148 */         this.field_70122_E = true;
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 154 */       this.field_70170_p.field_72984_F.func_76320_a("move");
/* 155 */       this.field_70139_V *= 0.4F;
/* 156 */       double d3 = this.field_70165_t;
/* 157 */       double d4 = this.field_70163_u;
/* 158 */       double d5 = this.field_70161_v;
/*     */       
/* 160 */       if (this.field_70134_J)
/*     */       {
/* 162 */         this.field_70134_J = false;
/* 163 */         par1 *= 0.25D;
/* 164 */         par3 *= 0.05000000074505806D;
/* 165 */         par5 *= 0.25D;
/* 166 */         this.field_70159_w = 0.0D;
/* 167 */         this.field_70181_x = 0.0D;
/* 168 */         this.field_70179_y = 0.0D;
/*     */       }
/*     */       
/* 171 */       double d6 = par1;
/* 172 */       double d7 = par3;
/* 173 */       double d8 = par5;
/* 174 */       AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
/* 175 */       boolean flag = (this.field_70122_E) && (func_70093_af());
/*     */       
/* 177 */       if (flag)
/*     */       {
/*     */ 
/*     */ 
/* 181 */         for (double d9 = 0.05D; (par1 != 0.0D) && (this.field_70170_p.func_72945_a(this, this.field_70121_D.func_72325_c(par1, -1.0D, 0.0D)).isEmpty()); d6 = par1)
/*     */         {
/* 183 */           if ((par1 < d9) && (par1 >= -d9))
/*     */           {
/* 185 */             par1 = 0.0D;
/*     */           }
/* 187 */           else if (par1 > 0.0D)
/*     */           {
/* 189 */             par1 -= d9;
/*     */           }
/*     */           else
/*     */           {
/* 193 */             par1 += d9;
/*     */           }
/*     */         }
/* 197 */         for (; 
/* 197 */             (par5 != 0.0D) && (this.field_70170_p.func_72945_a(this, this.field_70121_D.func_72325_c(0.0D, -1.0D, par5)).isEmpty()); d8 = par5)
/*     */         {
/* 199 */           if ((par5 < d9) && (par5 >= -d9))
/*     */           {
/* 201 */             par5 = 0.0D;
/*     */           }
/* 203 */           else if (par5 > 0.0D)
/*     */           {
/* 205 */             par5 -= d9;
/*     */           }
/*     */           else
/*     */           {
/* 209 */             par5 += d9;
/*     */           }
/*     */         }
/*     */         
/* 213 */         while ((par1 != 0.0D) && (par5 != 0.0D) && (this.field_70170_p.func_72945_a(this, this.field_70121_D.func_72325_c(par1, -1.0D, par5)).isEmpty()))
/*     */         {
/* 215 */           if ((par1 < d9) && (par1 >= -d9))
/*     */           {
/* 217 */             par1 = 0.0D;
/*     */           }
/* 219 */           else if (par1 > 0.0D)
/*     */           {
/* 221 */             par1 -= d9;
/*     */           }
/*     */           else
/*     */           {
/* 225 */             par1 += d9;
/*     */           }
/*     */           
/* 228 */           if ((par5 < d9) && (par5 >= -d9))
/*     */           {
/* 230 */             par5 = 0.0D;
/*     */           }
/* 232 */           else if (par5 > 0.0D)
/*     */           {
/* 234 */             par5 -= d9;
/*     */           }
/*     */           else
/*     */           {
/* 238 */             par5 += d9;
/*     */           }
/*     */           
/* 241 */           d6 = par1;
/* 242 */           d8 = par5;
/*     */         }
/*     */       }
/*     */       
/* 246 */       List list = this.field_70170_p.func_72945_a(this, this.field_70121_D.func_72321_a(par1, par3, par5));
/*     */       
/* 248 */       for (int i = 0; i < list.size(); i++)
/*     */       {
/* 250 */         par3 = ((AxisAlignedBB)list.get(i)).func_72323_b(this.field_70121_D, par3);
/*     */       }
/*     */       
/* 253 */       this.field_70121_D.func_72317_d(0.0D, par3, 0.0D);
/*     */       
/* 255 */       if ((!this.field_70135_K) && (d7 != par3))
/*     */       {
/* 257 */         par5 = 0.0D;
/* 258 */         par3 = 0.0D;
/* 259 */         par1 = 0.0D;
/*     */       }
/*     */       
/* 262 */       boolean flag1 = (this.field_70122_E) || ((d7 != par3) && (d7 < 0.0D));
/*     */       
/*     */ 
/* 265 */       for (int j = 0; j < list.size(); j++)
/*     */       {
/* 267 */         par1 = ((AxisAlignedBB)list.get(j)).func_72316_a(this.field_70121_D, par1);
/*     */       }
/*     */       
/* 270 */       this.field_70121_D.func_72317_d(par1, 0.0D, 0.0D);
/*     */       
/* 272 */       if ((!this.field_70135_K) && (d6 != par1))
/*     */       {
/* 274 */         par5 = 0.0D;
/* 275 */         par3 = 0.0D;
/* 276 */         par1 = 0.0D;
/*     */       }
/*     */       
/* 279 */       for (j = 0; j < list.size(); j++)
/*     */       {
/* 281 */         par5 = ((AxisAlignedBB)list.get(j)).func_72322_c(this.field_70121_D, par5);
/*     */       }
/*     */       
/* 284 */       this.field_70121_D.func_72317_d(0.0D, 0.0D, par5);
/*     */       
/* 286 */       if ((!this.field_70135_K) && (d8 != par5))
/*     */       {
/* 288 */         par5 = 0.0D;
/* 289 */         par3 = 0.0D;
/* 290 */         par1 = 0.0D;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 298 */       if ((this.field_70138_W > 0.0F) && (flag1) && ((flag) || (this.field_70139_V < 0.05F)) && ((d6 != par1) || (d8 != par5)))
/*     */       {
/* 300 */         double d12 = par1;
/* 301 */         double d10 = par3;
/* 302 */         double d11 = par5;
/* 303 */         par1 = d6;
/* 304 */         par3 = this.field_70138_W;
/* 305 */         par5 = d8;
/* 306 */         AxisAlignedBB axisalignedbb1 = this.field_70121_D.func_72329_c();
/* 307 */         this.field_70121_D.func_72328_c(axisalignedbb);
/* 308 */         list = this.field_70170_p.func_72945_a(this, this.field_70121_D.func_72321_a(d6, par3, d8));
/*     */         
/* 310 */         for (int k = 0; k < list.size(); k++)
/*     */         {
/* 312 */           par3 = ((AxisAlignedBB)list.get(k)).func_72323_b(this.field_70121_D, par3);
/*     */         }
/*     */         
/* 315 */         this.field_70121_D.func_72317_d(0.0D, par3, 0.0D);
/*     */         
/* 317 */         if ((!this.field_70135_K) && (d7 != par3))
/*     */         {
/* 319 */           par5 = 0.0D;
/* 320 */           par3 = 0.0D;
/* 321 */           par1 = 0.0D;
/*     */         }
/*     */         
/* 324 */         for (k = 0; k < list.size(); k++)
/*     */         {
/* 326 */           par1 = ((AxisAlignedBB)list.get(k)).func_72316_a(this.field_70121_D, par1);
/*     */         }
/*     */         
/* 329 */         this.field_70121_D.func_72317_d(par1, 0.0D, 0.0D);
/*     */         
/* 331 */         if ((!this.field_70135_K) && (d6 != par1))
/*     */         {
/* 333 */           par5 = 0.0D;
/* 334 */           par3 = 0.0D;
/* 335 */           par1 = 0.0D;
/*     */         }
/*     */         
/* 338 */         for (k = 0; k < list.size(); k++)
/*     */         {
/* 340 */           par5 = ((AxisAlignedBB)list.get(k)).func_72322_c(this.field_70121_D, par5);
/*     */         }
/*     */         
/* 343 */         this.field_70121_D.func_72317_d(0.0D, 0.0D, par5);
/*     */         
/* 345 */         if ((!this.field_70135_K) && (d8 != par5))
/*     */         {
/* 347 */           par5 = 0.0D;
/* 348 */           par3 = 0.0D;
/* 349 */           par1 = 0.0D;
/*     */         }
/*     */         
/* 352 */         if ((!this.field_70135_K) && (d7 != par3))
/*     */         {
/* 354 */           par5 = 0.0D;
/* 355 */           par3 = 0.0D;
/* 356 */           par1 = 0.0D;
/*     */         }
/*     */         else
/*     */         {
/* 360 */           par3 = -this.field_70138_W;
/*     */           
/* 362 */           for (k = 0; k < list.size(); k++)
/*     */           {
/* 364 */             par3 = ((AxisAlignedBB)list.get(k)).func_72323_b(this.field_70121_D, par3);
/*     */           }
/*     */           
/* 367 */           this.field_70121_D.func_72317_d(0.0D, par3, 0.0D);
/*     */         }
/*     */         
/* 370 */         if (d12 * d12 + d11 * d11 >= par1 * par1 + par5 * par5)
/*     */         {
/* 372 */           par1 = d12;
/* 373 */           par3 = d10;
/* 374 */           par5 = d11;
/* 375 */           this.field_70121_D.func_72328_c(axisalignedbb1);
/*     */         }
/*     */       }
/*     */       
/* 379 */       this.field_70170_p.field_72984_F.func_76319_b();
/* 380 */       this.field_70170_p.field_72984_F.func_76320_a("rest");
/* 381 */       this.field_70165_t = ((this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D);
/* 382 */       this.field_70163_u = (this.field_70121_D.field_72338_b + this.field_70129_M - this.field_70139_V);
/* 383 */       this.field_70161_v = ((this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D);
/* 384 */       this.field_70123_F = ((d6 != par1) || (d8 != par5));
/* 385 */       this.field_70124_G = (d7 != par3);
/* 386 */       this.field_70122_E = ((d7 != par3) && (d7 < 0.0D));
/* 387 */       this.field_70132_H = ((this.field_70123_F) || (this.field_70124_G));
/* 388 */       func_70064_a(par3, this.field_70122_E);
/*     */       
/* 390 */       if (d6 != par1)
/*     */       {
/* 392 */         this.field_70159_w = 0.0D;
/*     */       }
/*     */       
/* 395 */       if (d7 != par3)
/*     */       {
/* 397 */         this.field_70181_x = 0.0D;
/*     */       }
/*     */       
/* 400 */       if (d8 != par5)
/*     */       {
/* 402 */         this.field_70179_y = 0.0D;
/*     */       }
/*     */       
/* 405 */       double d12 = this.field_70165_t - d3;
/* 406 */       double d10 = this.field_70163_u - d4;
/* 407 */       double d11 = this.field_70161_v - d5;
/*     */       
/* 409 */       if ((func_70041_e_()) && (!flag) && (this.field_70154_o == null))
/*     */       {
/* 411 */         int l = MathHelper.func_76128_c(this.field_70165_t);
/* 412 */         int k = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D - this.field_70129_M);
/* 413 */         int i1 = MathHelper.func_76128_c(this.field_70161_v);
/* 414 */         Block j1 = this.field_70170_p.func_147439_a(l, k, i1);
/*     */         
/* 416 */         if (j1.isAir(this.field_70170_p, l, k, i1))
/*     */         {
/* 418 */           int k1 = this.field_70170_p.func_147439_a(l, k - 1, i1).func_149645_b();
/*     */           
/* 420 */           if ((k1 == 11) || (k1 == 32) || (k1 == 21))
/*     */           {
/* 422 */             j1 = this.field_70170_p.func_147439_a(l, k - 1, i1);
/*     */           }
/*     */         }
/*     */         
/* 426 */         if (j1 != Blocks.field_150468_ap)
/*     */         {
/* 428 */           d10 = 0.0D;
/*     */         }
/*     */         
/* 431 */         this.field_70140_Q = ((float)(this.field_70140_Q + MathHelper.func_76133_a(d12 * d12 + d11 * d11) * 0.6D));
/* 432 */         this.field_82151_R = ((float)(this.field_82151_R + MathHelper.func_76133_a(d12 * d12 + d10 * d10 + d11 * d11) * 0.6D));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 439 */         func_145775_I();
/*     */       }
/*     */       catch (Throwable throwable)
/*     */       {
/* 443 */         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Checking entity tile collision");
/* 444 */         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being checked for collision");
/* 445 */         func_85029_a(crashreportcategory);
/* 446 */         throw new ReportedException(crashreport);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 452 */       this.field_70170_p.field_72984_F.func_76319_b();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXDrop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */