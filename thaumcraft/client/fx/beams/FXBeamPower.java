/*     */ package thaumcraft.client.fx.beams;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.nodes.IRevealer;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ public class FXBeamPower extends EntityFX
/*     */ {
/*  21 */   public int particle = 16;
/*     */   
/*  23 */   private double offset = 0.0D;
/*     */   
/*  25 */   private double tX = 0.0D;
/*  26 */   private double tY = 0.0D;
/*  27 */   private double tZ = 0.0D;
/*  28 */   private double ptX = 0.0D;
/*  29 */   private double ptY = 0.0D;
/*  30 */   private double ptZ = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FXBeamPower(World par1World, double px, double py, double pz, double tx, double ty, double tz, float red, float green, float blue, int age)
/*     */   {
/*  38 */     super(par1World, px, py, pz, 0.0D, 0.0D, 0.0D);
/*     */     
/*  40 */     this.field_70552_h = 0.5F;
/*  41 */     this.field_70553_i = 0.5F;
/*  42 */     this.field_70551_j = 0.5F;
/*  43 */     func_70105_a(0.02F, 0.02F);
/*  44 */     this.field_70145_X = true;
/*  45 */     this.field_70159_w = 0.0D;
/*  46 */     this.field_70181_x = 0.0D;
/*  47 */     this.field_70179_y = 0.0D;
/*  48 */     this.tX = tx;
/*  49 */     this.tY = ty;
/*  50 */     this.tZ = tz;
/*  51 */     this.prevYaw = this.rotYaw;
/*  52 */     this.prevPitch = this.rotPitch;
/*  53 */     this.field_70547_e = age;
/*     */     
/*  55 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  56 */     int visibleDistance = 50;
/*  57 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 25;
/*  58 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) this.field_70547_e = 0;
/*     */   }
/*     */   
/*     */   public void updateBeam(double xx, double yy, double zz, double x, double y, double z)
/*     */   {
/*  63 */     func_70107_b(xx, yy, zz);
/*  64 */     this.tX = x;
/*  65 */     this.tY = y;
/*  66 */     this.tZ = z;
/*  67 */     while (this.field_70547_e - this.field_70546_d < 4) { this.field_70547_e += 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  77 */     this.field_70169_q = this.field_70165_t;
/*  78 */     this.field_70167_r = (this.field_70163_u + this.offset);
/*  79 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  81 */     this.ptX = this.tX;
/*  82 */     this.ptY = this.tY;
/*  83 */     this.ptZ = this.tZ;
/*     */     
/*  85 */     this.prevYaw = this.rotYaw;
/*  86 */     this.prevPitch = this.rotPitch;
/*     */     
/*  88 */     float xd = (float)(this.field_70165_t - this.tX);
/*  89 */     float yd = (float)(this.field_70163_u - this.tY);
/*  90 */     float zd = (float)(this.field_70161_v - this.tZ);
/*  91 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*  92 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*  93 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/*  94 */     this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D));
/*  95 */     this.prevYaw = this.rotYaw;
/*  96 */     this.prevPitch = this.rotPitch;
/*     */     
/*     */ 
/*     */ 
/* 100 */     if (this.opacity > 0.3F) this.opacity -= 0.025F;
/* 101 */     if (this.opacity < 0.3F) { this.opacity = 0.3F;
/*     */     }
/* 103 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 105 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/* 110 */     this.field_70552_h = r;
/* 111 */     this.field_70553_i = g;
/* 112 */     this.field_70551_j = b;
/*     */   }
/*     */   
/* 115 */   private float length = 0.0F;
/* 116 */   private float rotYaw = 0.0F;
/* 117 */   private float rotPitch = 0.0F;
/* 118 */   private float prevYaw = 0.0F;
/* 119 */   private float prevPitch = 0.0F;
/* 120 */   private net.minecraft.entity.Entity targetEntity = null;
/*     */   
/* 122 */   private float opacity = 0.3F;
/*     */   
/* 124 */   public void setPulse(boolean pulse, float r, float g, float b) { setRGB(r, g, b);
/* 125 */     if (pulse) {
/* 126 */       this.opacity = 0.8F;
/*     */     }
/*     */   }
/*     */   
/* 130 */   private float prevSize = 0.0F;
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 135 */     tessellator.func_78381_a();
/*     */     
/* 137 */     GL11.glPushMatrix();
/* 138 */     float var9 = 1.0F;
/* 139 */     float slide = Minecraft.func_71410_x().field_71439_g.field_70173_aa;
/* 140 */     float size = 0.7F;
/*     */     
/* 142 */     UtilsFX.bindTexture("textures/misc/beam1.png");
/*     */     
/* 144 */     GL11.glTexParameterf(3553, 10242, 10497.0F);
/* 145 */     GL11.glTexParameterf(3553, 10243, 10497.0F);
/*     */     
/* 147 */     GL11.glDisable(2884);
/*     */     
/* 149 */     float var11 = slide + f;
/* 150 */     float var12 = -var11 * 0.2F - MathHelper.func_76141_d(-var11 * 0.1F);
/*     */     
/*     */ 
/* 153 */     GL11.glBlendFunc(770, 1);
/* 154 */     GL11.glDepthMask(false);
/*     */     
/* 156 */     float xx = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 157 */     float yy = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 158 */     float zz = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/* 159 */     GL11.glTranslated(xx, yy, zz);
/*     */     
/* 161 */     float ry = (float)(this.prevYaw + (this.rotYaw - this.prevYaw) * f);
/* 162 */     float rp = (float)(this.prevPitch + (this.rotPitch - this.prevPitch) * f);
/* 163 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 164 */     GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
/* 165 */     GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
/*     */     
/* 167 */     double var44 = -0.15D * size;
/* 168 */     double var17 = 0.15D * size;
/*     */     
/* 170 */     float opmod = 0.1F;
/* 171 */     EntityLivingBase v = FMLClientHandler.instance().getClient().field_71451_h;
/* 172 */     if (((v instanceof EntityPlayer)) && 
/* 173 */       (((EntityPlayer)v).field_71071_by.func_70440_f(3) != null) && ((((EntityPlayer)v).field_71071_by.func_70440_f(3).func_77973_b() instanceof IRevealer)))
/*     */     {
/* 175 */       opmod = 1.0F;
/*     */     }
/*     */     
/*     */ 
/* 179 */     for (int t = 0; t < 2; t++)
/*     */     {
/* 181 */       double var29 = this.length * var9;
/* 182 */       double var31 = 0.0D;
/* 183 */       double var33 = 1.0D;
/* 184 */       double var35 = -1.0F + var12 + t / 3.0F;
/* 185 */       double var37 = this.length * var9 + var35;
/*     */       
/* 187 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 188 */       tessellator.func_78382_b();
/* 189 */       tessellator.func_78380_c(200);
/* 190 */       tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.opacity * opmod);
/*     */       
/*     */ 
/*     */ 
/* 194 */       tessellator.func_78374_a(var44, var29, 0.0D, var33, var37);
/* 195 */       tessellator.func_78374_a(var44, 0.0D, 0.0D, var33, var35);
/* 196 */       tessellator.func_78374_a(var17, 0.0D, 0.0D, var31, var35);
/* 197 */       tessellator.func_78374_a(var17, var29, 0.0D, var31, var37);
/* 198 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*     */ 
/* 202 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 203 */     GL11.glDepthMask(true);
/* 204 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 206 */     GL11.glEnable(2884);
/* 207 */     GL11.glPopMatrix();
/*     */     
/* 209 */     renderFlare(tessellator, f, f1, f2, f3, f4, f5);
/*     */     
/* 211 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
/* 212 */     tessellator.func_78382_b();
/* 213 */     this.prevSize = size;
/*     */   }
/*     */   
/*     */   public void renderFlare(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 218 */     float opmod = 0.2F;
/* 219 */     EntityLivingBase v = FMLClientHandler.instance().getClient().field_71451_h;
/* 220 */     if (((v instanceof EntityPlayer)) && 
/* 221 */       (((EntityPlayer)v).field_71071_by.func_70440_f(3) != null) && ((((EntityPlayer)v).field_71071_by.func_70440_f(3).func_77973_b() instanceof IRevealer)))
/*     */     {
/* 223 */       opmod = 1.0F;
/*     */     }
/*     */     
/*     */ 
/* 227 */     GL11.glPushMatrix();
/* 228 */     GL11.glDepthMask(false);
/*     */     
/* 230 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 232 */     UtilsFX.bindTexture(thaumcraft.client.fx.ParticleEngine.particleTexture);
/*     */     
/* 234 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/* 235 */     int part = this.field_70546_d % 16;
/*     */     
/* 237 */     float var8 = part / 16.0F;
/* 238 */     float var9 = var8 + 0.0624375F;
/* 239 */     float var10 = 0.3125F;
/* 240 */     float var11 = var10 + 0.0624375F;
/* 241 */     float var12 = 0.66F * this.opacity;
/*     */     
/* 243 */     float var13 = (float)(this.ptX + (this.tX - this.ptX) * f - field_70556_an);
/* 244 */     float var14 = (float)(this.ptY + (this.tY - this.ptY) * f - field_70554_ao);
/* 245 */     float var15 = (float)(this.ptZ + (this.tZ - this.ptZ) * f - field_70555_ap);
/* 246 */     float var16 = 1.0F;
/*     */     
/* 248 */     tessellator.func_78382_b();
/* 249 */     tessellator.func_78380_c(200);
/* 250 */     tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.opacity * opmod);
/* 251 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/* 252 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/* 253 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/* 254 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */     
/* 256 */     tessellator.func_78381_a();
/*     */     
/*     */ 
/* 259 */     GL11.glBlendFunc(770, 771);
/* 260 */     GL11.glDepthMask(true);
/* 261 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/beams/FXBeamPower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */