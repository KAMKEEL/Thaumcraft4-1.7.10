/*     */ package thaumcraft.client.fx.beams;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import net.minecraft.client.Minecraft;
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
/*     */ public class FXBeam extends EntityFX
/*     */ {
/*  18 */   public int particle = 16;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FXBeam(World par1World, double x, double y, double z, double tx, double ty, double tz, float red, float green, float blue, int age)
/*     */   {
/*  26 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
/*     */     
/*  28 */     this.field_70552_h = red;
/*  29 */     this.field_70553_i = green;
/*  30 */     this.field_70551_j = blue;
/*     */     
/*  32 */     func_70105_a(0.02F, 0.02F);
/*  33 */     this.field_70145_X = true;
/*  34 */     this.field_70159_w = 0.0D;
/*  35 */     this.field_70181_x = 0.0D;
/*  36 */     this.field_70179_y = 0.0D;
/*  37 */     this.tX = tx;
/*  38 */     this.tY = ty;
/*  39 */     this.tZ = tz;
/*  40 */     float xd = (float)(this.field_70165_t - this.tX);
/*  41 */     float yd = (float)(this.field_70163_u - this.tY);
/*  42 */     float zd = (float)(this.field_70161_v - this.tZ);
/*  43 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*  44 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*  45 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/*  46 */     this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D));
/*  47 */     this.prevYaw = this.rotYaw;
/*  48 */     this.prevPitch = this.rotPitch;
/*     */     
/*  50 */     this.field_70547_e = age;
/*     */     
/*  52 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  53 */     int visibleDistance = 50;
/*  54 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 25;
/*  55 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) { this.field_70547_e = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public FXBeam(World par1World, double x, double y, double z, Entity entity, float red, float green, float blue, int age)
/*     */   {
/*  62 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
/*     */     
/*  64 */     this.field_70552_h = red;
/*  65 */     this.field_70553_i = green;
/*  66 */     this.field_70551_j = blue;
/*     */     
/*  68 */     func_70105_a(0.02F, 0.02F);
/*  69 */     this.field_70145_X = true;
/*  70 */     this.field_70159_w = 0.0D;
/*  71 */     this.field_70181_x = 0.0D;
/*  72 */     this.field_70179_y = 0.0D;
/*     */     
/*  74 */     this.targetEntity = entity;
/*     */     
/*  76 */     this.tX = this.targetEntity.field_70165_t;
/*  77 */     this.tY = (this.targetEntity.field_70163_u + this.targetEntity.func_70047_e() - this.targetEntity.field_70131_O / 2.0F);
/*  78 */     this.tZ = this.targetEntity.field_70161_v;
/*  79 */     float xd = (float)(this.field_70165_t - this.tX);
/*  80 */     float yd = (float)(this.field_70163_u - this.tY);
/*  81 */     float zd = (float)(this.field_70161_v - this.tZ);
/*  82 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*  83 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*  84 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/*  85 */     this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D));
/*  86 */     this.prevYaw = this.rotYaw;
/*  87 */     this.prevPitch = this.rotPitch;
/*     */     
/*  89 */     this.field_70547_e = age;
/*     */     
/*  91 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  92 */     int visibleDistance = 50;
/*  93 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 25;
/*  94 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) this.field_70547_e = 0;
/*     */   }
/*     */   
/*     */   public void updateBeam(double xs, double ys, double zs, double x, double y, double z) {
/*  98 */     this.movX = xs;
/*  99 */     this.movY = ys;
/* 100 */     this.movZ = zs;
/* 101 */     this.tX = x;
/* 102 */     this.tY = y;
/* 103 */     this.tZ = z;
/* 104 */     while (this.field_70547_e - this.field_70546_d < 4) this.field_70547_e += 1;
/* 105 */     this.updated = true;
/*     */   }
/*     */   
/* 108 */   boolean updated = false;
/* 109 */   double movX = 0.0D;
/* 110 */   double movY = 0.0D;
/* 111 */   double movZ = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 119 */     this.field_70169_q = this.field_70165_t;
/* 120 */     this.field_70167_r = this.field_70163_u;
/* 121 */     this.field_70166_s = this.field_70161_v;
/* 122 */     if (this.updated) {
/* 123 */       this.field_70165_t = this.movX;
/* 124 */       this.field_70163_u = this.movY;
/* 125 */       this.field_70161_v = this.movZ;
/* 126 */       this.updated = false;
/*     */     }
/*     */     
/* 129 */     this.prevYaw = this.rotYaw;
/* 130 */     this.prevPitch = this.rotPitch;
/*     */     
/* 132 */     if (this.targetEntity != null) {
/* 133 */       this.tX = this.targetEntity.field_70165_t;
/* 134 */       this.tY = (this.targetEntity.field_70163_u + this.targetEntity.func_70047_e() - this.targetEntity.field_70131_O / 2.0F);
/* 135 */       this.tZ = this.targetEntity.field_70161_v;
/*     */     }
/*     */     
/* 138 */     float xd = (float)(this.field_70165_t - this.tX);
/* 139 */     float yd = (float)(this.field_70163_u - this.tY);
/* 140 */     float zd = (float)(this.field_70161_v - this.tZ);
/*     */     
/* 142 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*     */     
/* 144 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*     */     
/* 146 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/* 147 */     this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D));
/*     */     
/* 149 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 151 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b)
/*     */   {
/* 157 */     this.field_70552_h = r;
/* 158 */     this.field_70553_i = g;
/* 159 */     this.field_70551_j = b;
/*     */   }
/*     */   
/* 162 */   private float length = 0.0F;
/* 163 */   private float rotYaw = 0.0F;
/* 164 */   private float rotPitch = 0.0F;
/* 165 */   private float prevYaw = 0.0F;
/* 166 */   private float prevPitch = 0.0F;
/* 167 */   private Entity targetEntity = null;
/* 168 */   private double tX = 0.0D;
/* 169 */   private double tY = 0.0D;
/* 170 */   private double tZ = 0.0D;
/*     */   
/* 172 */   private int type = 0;
/*     */   
/* 174 */   public void setType(int type) { this.type = type; }
/*     */   
/*     */ 
/* 177 */   private float endMod = 1.0F;
/*     */   
/* 179 */   public void setEndMod(float endMod) { this.endMod = endMod; }
/*     */   
/*     */ 
/* 182 */   private boolean reverse = false;
/*     */   
/* 184 */   public void setReverse(boolean reverse) { this.reverse = reverse; }
/*     */   
/*     */ 
/* 187 */   private boolean pulse = true;
/*     */   
/* 189 */   public void setPulse(boolean pulse) { this.pulse = pulse; }
/*     */   
/*     */ 
/* 192 */   private int rotationspeed = 5;
/*     */   
/* 194 */   public void setRotationspeed(int rotationspeed) { this.rotationspeed = rotationspeed; }
/*     */   
/*     */ 
/* 197 */   private float prevSize = 0.0F;
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 202 */     tessellator.func_78381_a();
/*     */     
/* 204 */     GL11.glPushMatrix();
/* 205 */     float var9 = 1.0F;
/* 206 */     float slide = Minecraft.func_71410_x().field_71439_g.field_70173_aa;
/* 207 */     float rot = (float)(this.field_70170_p.field_73011_w.getWorldTime() % (360 / this.rotationspeed) * this.rotationspeed) + this.rotationspeed * f;
/*     */     
/* 209 */     float size = this.field_70130_N;
/* 210 */     if (this.pulse) {
/* 211 */       size = Math.min(this.field_70546_d / 4.0F, this.field_70130_N);
/* 212 */       size = (float)(this.prevSize + (size - this.prevSize) * f);
/*     */     }
/*     */     
/* 215 */     float op = 0.4F;
/* 216 */     if ((this.pulse) && (this.field_70547_e - this.field_70546_d <= 4)) {
/* 217 */       op = 0.4F - (4 - (this.field_70547_e - this.field_70546_d)) * 0.1F;
/*     */     }
/* 219 */     switch (this.type) {
/* 220 */     default:  UtilsFX.bindTexture("textures/misc/beam.png"); break;
/* 221 */     case 1:  UtilsFX.bindTexture("textures/misc/beam1.png"); break;
/* 222 */     case 2:  UtilsFX.bindTexture("textures/misc/beam2.png"); break;
/* 223 */     case 3:  UtilsFX.bindTexture("textures/misc/beam3.png");
/*     */     }
/*     */     
/* 226 */     GL11.glTexParameterf(3553, 10242, 10497.0F);
/* 227 */     GL11.glTexParameterf(3553, 10243, 10497.0F);
/* 228 */     GL11.glDisable(2884);
/*     */     
/* 230 */     float var11 = slide + f;
/* 231 */     if (this.reverse) var11 *= -1.0F;
/* 232 */     float var12 = -var11 * 0.2F - MathHelper.func_76141_d(-var11 * 0.1F);
/*     */     
/* 234 */     GL11.glEnable(3042);
/* 235 */     GL11.glBlendFunc(770, this.blendmode);
/* 236 */     GL11.glDepthMask(false);
/*     */     
/* 238 */     float xx = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 239 */     float yy = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 240 */     float zz = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/* 241 */     GL11.glTranslated(xx, yy, zz);
/*     */     
/* 243 */     float ry = (float)(this.prevYaw + (this.rotYaw - this.prevYaw) * f);
/* 244 */     float rp = (float)(this.prevPitch + (this.rotPitch - this.prevPitch) * f);
/* 245 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 246 */     GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
/* 247 */     GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
/*     */     
/* 249 */     double var44 = -0.15D * size;
/* 250 */     double var17 = 0.15D * size;
/* 251 */     double var44b = -0.15D * size * this.endMod;
/* 252 */     double var17b = 0.15D * size * this.endMod;
/*     */     
/* 254 */     GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
/* 255 */     for (int t = 0; t < 3; t++)
/*     */     {
/* 257 */       double var29 = this.length * size / this.field_70130_N * var9;
/* 258 */       double var31 = 0.0D;
/* 259 */       double var33 = 1.0D;
/* 260 */       double var35 = -1.0F + var12 + t / 3.0F;
/* 261 */       double var37 = this.length * size / this.field_70130_N * var9 + var35;
/*     */       
/* 263 */       GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/* 264 */       tessellator.func_78382_b();
/* 265 */       tessellator.func_78380_c(200);
/* 266 */       tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op);
/* 267 */       tessellator.func_78374_a(var44b, var29, 0.0D, var33, var37);
/* 268 */       tessellator.func_78374_a(var44, 0.0D, 0.0D, var33, var35);
/* 269 */       tessellator.func_78374_a(var17, 0.0D, 0.0D, var31, var35);
/* 270 */       tessellator.func_78374_a(var17b, var29, 0.0D, var31, var37);
/* 271 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*     */ 
/* 275 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 276 */     GL11.glDepthMask(true);
/* 277 */     GL11.glDisable(3042);
/* 278 */     GL11.glEnable(2884);
/* 279 */     GL11.glPopMatrix();
/*     */     
/* 281 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
/*     */     
/* 283 */     tessellator.func_78382_b();
/* 284 */     this.prevSize = size;
/*     */   }
/*     */   
/* 287 */   public int blendmode = 1;
/* 288 */   public float field_70130_N = 1.0F;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/beams/FXBeam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */