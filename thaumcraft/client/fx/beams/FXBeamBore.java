/*     */ package thaumcraft.client.fx.beams;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ public class FXBeamBore extends EntityFX
/*     */ {
/*  19 */   public int particle = 16;
/*     */   
/*  21 */   private double offset = 0.0D;
/*     */   
/*  23 */   private double tX = 0.0D;
/*  24 */   private double tY = 0.0D;
/*  25 */   private double tZ = 0.0D;
/*  26 */   private double ptX = 0.0D;
/*  27 */   private double ptY = 0.0D;
/*  28 */   private double ptZ = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FXBeamBore(World par1World, double px, double py, double pz, double tx, double ty, double tz, float red, float green, float blue, int age)
/*     */   {
/*  36 */     super(par1World, px, py, pz, 0.0D, 0.0D, 0.0D);
/*     */     
/*     */ 
/*  39 */     this.field_70552_h = red;
/*  40 */     this.field_70553_i = green;
/*  41 */     this.field_70551_j = blue;
/*  42 */     func_70105_a(0.02F, 0.02F);
/*  43 */     this.field_70145_X = true;
/*  44 */     this.field_70159_w = 0.0D;
/*  45 */     this.field_70181_x = 0.0D;
/*  46 */     this.field_70179_y = 0.0D;
/*  47 */     this.tX = tx;
/*  48 */     this.tY = ty;
/*  49 */     this.tZ = tz;
/*  50 */     this.prevYaw = this.rotYaw;
/*  51 */     this.prevPitch = this.rotPitch;
/*  52 */     this.field_70547_e = age;
/*     */     
/*  54 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  55 */     int visibleDistance = 50;
/*  56 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 25;
/*  57 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) this.field_70547_e = 0;
/*     */   }
/*     */   
/*     */   public void updateBeam(double x, double y, double z)
/*     */   {
/*  62 */     this.tX = x;
/*  63 */     this.tY = y;
/*  64 */     this.tZ = z;
/*  65 */     while (this.field_70547_e - this.field_70546_d < 4) { this.field_70547_e += 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  75 */     this.field_70169_q = this.field_70165_t;
/*  76 */     this.field_70167_r = (this.field_70163_u + this.offset);
/*  77 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  79 */     this.ptX = this.tX;
/*  80 */     this.ptY = this.tY;
/*  81 */     this.ptZ = this.tZ;
/*     */     
/*  83 */     this.prevYaw = this.rotYaw;
/*  84 */     this.prevPitch = this.rotPitch;
/*     */     
/*  86 */     float xd = (float)(this.field_70165_t - this.tX);
/*  87 */     float yd = (float)(this.field_70163_u - this.tY);
/*  88 */     float zd = (float)(this.field_70161_v - this.tZ);
/*  89 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*  90 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*  91 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/*  92 */     this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D));
/*  93 */     this.prevYaw = this.rotYaw;
/*  94 */     this.prevPitch = this.rotPitch;
/*     */     
/*  96 */     if (this.impact > 0) { this.impact -= 1;
/*     */     }
/*  98 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 100 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/* 105 */     this.field_70552_h = r;
/* 106 */     this.field_70553_i = g;
/* 107 */     this.field_70551_j = b;
/*     */   }
/*     */   
/* 110 */   private float length = 0.0F;
/* 111 */   private float rotYaw = 0.0F;
/* 112 */   private float rotPitch = 0.0F;
/* 113 */   private float prevYaw = 0.0F;
/* 114 */   private float prevPitch = 0.0F;
/* 115 */   private Entity targetEntity = null;
/*     */   
/* 117 */   private int type = 0;
/*     */   
/* 119 */   public void setType(int type) { this.type = type; }
/*     */   
/*     */ 
/* 122 */   private float endMod = 1.0F;
/*     */   
/* 124 */   public void setEndMod(float endMod) { this.endMod = endMod; }
/*     */   
/*     */ 
/* 127 */   private boolean reverse = false;
/*     */   
/* 129 */   public void setReverse(boolean reverse) { this.reverse = reverse; }
/*     */   
/*     */ 
/* 132 */   private boolean pulse = true;
/*     */   
/* 134 */   public void setPulse(boolean pulse) { this.pulse = pulse; }
/*     */   
/*     */ 
/* 137 */   private int rotationspeed = 5;
/*     */   
/* 139 */   public void setRotationspeed(int rotationspeed) { this.rotationspeed = rotationspeed; }
/*     */   
/*     */ 
/* 142 */   private float prevSize = 0.0F;
/*     */   
/*     */   public int impact;
/*     */   
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 148 */     tessellator.func_78381_a();
/*     */     
/* 150 */     GL11.glPushMatrix();
/* 151 */     float var9 = 1.0F;
/* 152 */     float slide = Minecraft.func_71410_x().field_71439_g.field_70173_aa;
/* 153 */     float rot = (float)(this.field_70170_p.field_73011_w.getWorldTime() % (360 / this.rotationspeed) * this.rotationspeed) + this.rotationspeed * f;
/*     */     
/* 155 */     float size = 1.0F;
/* 156 */     if (this.pulse) {
/* 157 */       size = Math.min(this.field_70546_d / 4.0F, 1.0F);
/* 158 */       size = (float)(this.prevSize + (size - this.prevSize) * f);
/*     */     }
/*     */     
/* 161 */     float op = 0.4F;
/* 162 */     if ((this.pulse) && (this.field_70547_e - this.field_70546_d <= 4)) {
/* 163 */       op = 0.4F - (4 - (this.field_70547_e - this.field_70546_d)) * 0.1F;
/*     */     }
/* 165 */     switch (this.type) {
/* 166 */     default:  UtilsFX.bindTexture("textures/misc/beam.png"); break;
/* 167 */     case 1:  UtilsFX.bindTexture("textures/misc/beam1.png"); break;
/* 168 */     case 2:  UtilsFX.bindTexture("textures/misc/beam2.png"); break;
/* 169 */     case 3:  UtilsFX.bindTexture("textures/misc/beam3.png");
/*     */     }
/*     */     
/* 172 */     GL11.glTexParameterf(3553, 10242, 10497.0F);
/* 173 */     GL11.glTexParameterf(3553, 10243, 10497.0F);
/*     */     
/* 175 */     GL11.glDisable(2884);
/*     */     
/* 177 */     float var11 = slide + f;
/* 178 */     if (this.reverse) var11 *= -1.0F;
/* 179 */     float var12 = -var11 * 0.2F - MathHelper.func_76141_d(-var11 * 0.1F);
/*     */     
/* 181 */     GL11.glEnable(3042);
/* 182 */     GL11.glBlendFunc(770, 1);
/* 183 */     GL11.glDepthMask(false);
/*     */     
/* 185 */     float xx = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 186 */     float yy = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 187 */     float zz = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/* 188 */     GL11.glTranslated(xx, yy, zz);
/*     */     
/* 190 */     float ry = (float)(this.prevYaw + (this.rotYaw - this.prevYaw) * f);
/* 191 */     float rp = (float)(this.prevPitch + (this.rotPitch - this.prevPitch) * f);
/* 192 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 193 */     GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
/* 194 */     GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
/*     */     
/* 196 */     double var44 = -0.15D * size;
/* 197 */     double var17 = 0.15D * size;
/* 198 */     double var44b = -0.15D * size * this.endMod;
/* 199 */     double var17b = 0.15D * size * this.endMod;
/*     */     
/* 201 */     GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
/* 202 */     for (int t = 0; t < 3; t++)
/*     */     {
/* 204 */       double var29 = this.length * size * var9;
/* 205 */       double var31 = 0.0D;
/* 206 */       double var33 = 1.0D;
/* 207 */       double var35 = -1.0F + var12 + t / 3.0F;
/* 208 */       double var37 = this.length * size * var9 + var35;
/*     */       
/* 210 */       GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/* 211 */       tessellator.func_78382_b();
/* 212 */       tessellator.func_78380_c(200);
/* 213 */       tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op);
/* 214 */       tessellator.func_78374_a(var44b, var29, 0.0D, var33, var37);
/* 215 */       tessellator.func_78374_a(var44, 0.0D, 0.0D, var33, var35);
/* 216 */       tessellator.func_78374_a(var17, 0.0D, 0.0D, var31, var35);
/* 217 */       tessellator.func_78374_a(var17b, var29, 0.0D, var31, var37);
/* 218 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*     */ 
/* 222 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 223 */     GL11.glDepthMask(true);
/* 224 */     GL11.glDisable(3042);
/* 225 */     GL11.glEnable(2884);
/* 226 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/* 229 */     if (this.impact > 0) { renderImpact(tessellator, f, f1, f2, f3, f4, f5);
/*     */     }
/* 231 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
/* 232 */     tessellator.func_78382_b();
/* 233 */     this.prevSize = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderImpact(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 240 */     GL11.glPushMatrix();
/* 241 */     GL11.glDepthMask(false);
/* 242 */     GL11.glEnable(3042);
/* 243 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 245 */     UtilsFX.bindTexture(thaumcraft.client.fx.ParticleEngine.particleTexture);
/*     */     
/* 247 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/* 248 */     int part = this.field_70546_d % 16;
/*     */     
/* 250 */     float var8 = part / 16.0F;
/* 251 */     float var9 = var8 + 0.0624375F;
/* 252 */     float var10 = 0.3125F;
/* 253 */     float var11 = var10 + 0.0624375F;
/* 254 */     float var12 = this.endMod / 2.0F / (6 - this.impact);
/*     */     
/* 256 */     float var13 = (float)(this.ptX + (this.tX - this.ptX) * f - field_70556_an);
/* 257 */     float var14 = (float)(this.ptY + (this.tY - this.ptY) * f - field_70554_ao);
/* 258 */     float var15 = (float)(this.ptZ + (this.tZ - this.ptZ) * f - field_70555_ap);
/* 259 */     float var16 = 1.0F;
/*     */     
/* 261 */     tessellator.func_78382_b();
/* 262 */     tessellator.func_78380_c(200);
/* 263 */     tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F);
/* 264 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/* 265 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/* 266 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/* 267 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */     
/* 269 */     tessellator.func_78381_a();
/*     */     
/* 271 */     GL11.glDisable(3042);
/* 272 */     GL11.glDepthMask(true);
/* 273 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/beams/FXBeamBore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */