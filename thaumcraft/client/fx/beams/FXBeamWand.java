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
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ public class FXBeamWand extends EntityFX
/*     */ {
/*  21 */   public int particle = 16;
/*  22 */   EntityPlayer player = null;
/*  23 */   private double offset = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FXBeamWand(World par1World, EntityPlayer player, double tx, double ty, double tz, float red, float green, float blue, int age)
/*     */   {
/*  31 */     super(par1World, player.field_70165_t, player.field_70163_u, player.field_70161_v, 0.0D, 0.0D, 0.0D);
/*     */     
/*  33 */     if (player.func_145782_y() != Minecraft.func_71410_x().field_71451_h.func_145782_y()) {
/*  34 */       this.offset = (player.field_70131_O / 2.0F + 0.25D);
/*     */     }
/*  36 */     this.field_70552_h = red;
/*  37 */     this.field_70553_i = green;
/*  38 */     this.field_70551_j = blue;
/*  39 */     this.player = player;
/*  40 */     func_70105_a(0.02F, 0.02F);
/*  41 */     this.field_70145_X = true;
/*  42 */     this.field_70159_w = 0.0D;
/*  43 */     this.field_70181_x = 0.0D;
/*  44 */     this.field_70179_y = 0.0D;
/*  45 */     this.tX = tx;
/*  46 */     this.tY = ty;
/*  47 */     this.tZ = tz;
/*  48 */     float xd = (float)(player.field_70165_t - this.tX);
/*  49 */     float yd = (float)(player.field_70163_u + this.offset - this.tY);
/*  50 */     float zd = (float)(player.field_70161_v - this.tZ);
/*  51 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*  52 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*  53 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/*  54 */     this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D));
/*  55 */     this.prevYaw = this.rotYaw;
/*  56 */     this.prevPitch = this.rotPitch;
/*     */     
/*  58 */     this.field_70547_e = age;
/*     */     
/*  60 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  61 */     int visibleDistance = 50;
/*  62 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 25;
/*  63 */     if (renderentity.func_70011_f(player.field_70165_t, player.field_70163_u, player.field_70161_v) > visibleDistance) { this.field_70547_e = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateBeam(double x, double y, double z)
/*     */   {
/* 107 */     this.tX = x;
/* 108 */     this.tY = y;
/* 109 */     this.tZ = z;
/* 110 */     while (this.field_70547_e - this.field_70546_d < 4) { this.field_70547_e += 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 120 */     this.field_70169_q = this.player.field_70165_t;
/* 121 */     this.field_70167_r = (this.player.field_70163_u + this.offset);
/* 122 */     this.field_70166_s = this.player.field_70161_v;
/* 123 */     this.ptX = this.tX;
/* 124 */     this.ptY = this.tY;
/* 125 */     this.ptZ = this.tZ;
/*     */     
/* 127 */     this.prevYaw = this.rotYaw;
/* 128 */     this.prevPitch = this.rotPitch;
/*     */     
/* 130 */     float xd = (float)(this.player.field_70165_t - this.tX);
/* 131 */     float yd = (float)(this.player.field_70163_u + this.offset - this.tY);
/* 132 */     float zd = (float)(this.player.field_70161_v - this.tZ);
/*     */     
/* 134 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*     */     
/* 136 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*     */     
/* 138 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/*     */     
/*     */ 
/*     */ 
/* 142 */     for (this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D)); this.rotPitch - this.prevPitch < -180.0F; this.prevPitch -= 360.0F) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 147 */     while (this.rotPitch - this.prevPitch >= 180.0F)
/*     */     {
/* 149 */       this.prevPitch += 360.0F;
/*     */     }
/*     */     
/* 152 */     while (this.rotYaw - this.prevYaw < -180.0F)
/*     */     {
/* 154 */       this.prevYaw -= 360.0F;
/*     */     }
/*     */     
/* 157 */     while (this.rotYaw - this.prevYaw >= 180.0F)
/*     */     {
/* 159 */       this.prevYaw += 360.0F;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 164 */     if (this.impact > 0) { this.impact -= 1;
/*     */     }
/* 166 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 168 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/* 173 */     this.field_70552_h = r;
/* 174 */     this.field_70553_i = g;
/* 175 */     this.field_70551_j = b;
/*     */   }
/*     */   
/* 178 */   private float length = 0.0F;
/* 179 */   private float rotYaw = 0.0F;
/* 180 */   private float rotPitch = 0.0F;
/* 181 */   private float prevYaw = 0.0F;
/* 182 */   private float prevPitch = 0.0F;
/* 183 */   private Entity targetEntity = null;
/* 184 */   private double tX = 0.0D;
/* 185 */   private double tY = 0.0D;
/* 186 */   private double tZ = 0.0D;
/* 187 */   private double ptX = 0.0D;
/* 188 */   private double ptY = 0.0D;
/* 189 */   private double ptZ = 0.0D;
/*     */   
/* 191 */   private int type = 0;
/*     */   
/* 193 */   public void setType(int type) { this.type = type; }
/*     */   
/*     */ 
/* 196 */   private float endMod = 1.0F;
/*     */   
/* 198 */   public void setEndMod(float endMod) { this.endMod = endMod; }
/*     */   
/*     */ 
/* 201 */   private boolean reverse = false;
/*     */   
/* 203 */   public void setReverse(boolean reverse) { this.reverse = reverse; }
/*     */   
/*     */ 
/* 206 */   private boolean pulse = true;
/*     */   
/* 208 */   public void setPulse(boolean pulse) { this.pulse = pulse; }
/*     */   
/*     */ 
/* 211 */   private int rotationspeed = 5;
/*     */   
/* 213 */   public void setRotationspeed(int rotationspeed) { this.rotationspeed = rotationspeed; }
/*     */   
/*     */ 
/* 216 */   private float prevSize = 0.0F;
/*     */   
/*     */   public int impact;
/*     */   
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 222 */     tessellator.func_78381_a();
/*     */     
/* 224 */     GL11.glPushMatrix();
/* 225 */     float var9 = 1.0F;
/* 226 */     float slide = Minecraft.func_71410_x().field_71439_g.field_70173_aa;
/* 227 */     float rot = (float)(this.field_70170_p.field_73011_w.getWorldTime() % (360 / this.rotationspeed) * this.rotationspeed) + this.rotationspeed * f;
/*     */     
/* 229 */     float size = 1.0F;
/* 230 */     if (this.pulse) {
/* 231 */       size = Math.min(this.field_70546_d / 4.0F, 1.0F);
/* 232 */       size = (float)(this.prevSize + (size - this.prevSize) * f);
/*     */     }
/*     */     
/* 235 */     float op = 0.4F;
/* 236 */     if ((this.pulse) && (this.field_70547_e - this.field_70546_d <= 4)) {
/* 237 */       op = 0.4F - (4 - (this.field_70547_e - this.field_70546_d)) * 0.1F;
/*     */     }
/* 239 */     switch (this.type) {
/* 240 */     default:  UtilsFX.bindTexture("textures/misc/beam.png"); break;
/* 241 */     case 1:  UtilsFX.bindTexture("textures/misc/beam1.png"); break;
/* 242 */     case 2:  UtilsFX.bindTexture("textures/misc/beam2.png"); break;
/* 243 */     case 3:  UtilsFX.bindTexture("textures/misc/beam3.png");
/*     */     }
/*     */     
/* 246 */     GL11.glTexParameterf(3553, 10242, 10497.0F);
/* 247 */     GL11.glTexParameterf(3553, 10243, 10497.0F);
/*     */     
/* 249 */     GL11.glDisable(2884);
/*     */     
/* 251 */     float var11 = slide + f;
/* 252 */     if (this.reverse) var11 *= -1.0F;
/* 253 */     float var12 = -var11 * 0.2F - MathHelper.func_76141_d(-var11 * 0.1F);
/*     */     
/* 255 */     GL11.glEnable(3042);
/* 256 */     GL11.glBlendFunc(770, 1);
/* 257 */     GL11.glDepthMask(false);
/*     */     
/* 259 */     double prex = this.player.field_70169_q;
/* 260 */     double prey = this.player.field_70167_r + this.offset;
/* 261 */     double prez = this.player.field_70166_s;
/* 262 */     double px = this.player.field_70165_t;
/* 263 */     double py = this.player.field_70163_u + this.offset;
/* 264 */     double pz = this.player.field_70161_v;
/*     */     
/* 266 */     prex -= MathHelper.func_76134_b(this.player.field_70126_B / 180.0F * 3.141593F) * 0.066F;
/* 267 */     prey -= 0.06D;
/* 268 */     prez -= MathHelper.func_76126_a(this.player.field_70126_B / 180.0F * 3.141593F) * 0.04F;
/* 269 */     Vec3 vec3d = this.player.func_70676_i(1.0F);
/* 270 */     prex += vec3d.field_72450_a * 0.3D;
/* 271 */     prey += vec3d.field_72448_b * 0.3D;
/* 272 */     prez += vec3d.field_72449_c * 0.3D;
/*     */     
/* 274 */     px -= MathHelper.func_76134_b(this.player.field_70177_z / 180.0F * 3.141593F) * 0.066F;
/* 275 */     py -= 0.06D;
/* 276 */     pz -= MathHelper.func_76126_a(this.player.field_70177_z / 180.0F * 3.141593F) * 0.04F;
/* 277 */     vec3d = this.player.func_70676_i(1.0F);
/* 278 */     px += vec3d.field_72450_a * 0.3D;
/* 279 */     py += vec3d.field_72448_b * 0.3D;
/* 280 */     pz += vec3d.field_72449_c * 0.3D;
/*     */     
/* 282 */     float xx = (float)(prex + (px - prex) * f - field_70556_an);
/* 283 */     float yy = (float)(prey + (py - prey) * f - field_70554_ao);
/* 284 */     float zz = (float)(prez + (pz - prez) * f - field_70555_ap);
/* 285 */     GL11.glTranslated(xx, yy, zz);
/*     */     
/* 287 */     float ry = (float)(this.prevYaw + (this.rotYaw - this.prevYaw) * f);
/* 288 */     float rp = (float)(this.prevPitch + (this.rotPitch - this.prevPitch) * f);
/* 289 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 290 */     GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
/* 291 */     GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
/*     */     
/* 293 */     double var44 = -0.15D * size;
/* 294 */     double var17 = 0.15D * size;
/* 295 */     double var44b = -0.15D * size * this.endMod;
/* 296 */     double var17b = 0.15D * size * this.endMod;
/*     */     
/* 298 */     GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
/* 299 */     for (int t = 0; t < 3; t++)
/*     */     {
/* 301 */       double var29 = this.length * size * var9;
/* 302 */       double var31 = 0.0D;
/* 303 */       double var33 = 1.0D;
/* 304 */       double var35 = -1.0F + var12 + t / 3.0F;
/* 305 */       double var37 = this.length * size * var9 + var35;
/*     */       
/* 307 */       GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/* 308 */       tessellator.func_78382_b();
/* 309 */       tessellator.func_78380_c(200);
/* 310 */       tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op);
/* 311 */       tessellator.func_78374_a(var44b, var29, 0.0D, var33, var37);
/* 312 */       tessellator.func_78374_a(var44, 0.0D, 0.0D, var33, var35);
/* 313 */       tessellator.func_78374_a(var17, 0.0D, 0.0D, var31, var35);
/* 314 */       tessellator.func_78374_a(var17b, var29, 0.0D, var31, var37);
/* 315 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 320 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 321 */     GL11.glDepthMask(true);
/* 322 */     GL11.glDisable(3042);
/* 323 */     GL11.glEnable(2884);
/*     */     
/* 325 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/* 328 */     if (this.impact > 0) { renderImpact(tessellator, f, f1, f2, f3, f4, f5);
/*     */     }
/* 330 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
/* 331 */     tessellator.func_78382_b();
/* 332 */     this.prevSize = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderImpact(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 339 */     GL11.glPushMatrix();
/* 340 */     GL11.glDepthMask(false);
/* 341 */     GL11.glEnable(3042);
/* 342 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 344 */     UtilsFX.bindTexture(thaumcraft.client.fx.ParticleEngine.particleTexture);
/*     */     
/* 346 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/* 347 */     int part = this.field_70546_d % 16;
/*     */     
/* 349 */     float var8 = part / 16.0F;
/* 350 */     float var9 = var8 + 0.0624375F;
/* 351 */     float var10 = 0.3125F;
/* 352 */     float var11 = var10 + 0.0624375F;
/* 353 */     float var12 = this.endMod / 2.0F / (6 - this.impact);
/*     */     
/* 355 */     float var13 = (float)(this.ptX + (this.tX - this.ptX) * f - field_70556_an);
/* 356 */     float var14 = (float)(this.ptY + (this.tY - this.ptY) * f - field_70554_ao);
/* 357 */     float var15 = (float)(this.ptZ + (this.tZ - this.ptZ) * f - field_70555_ap);
/* 358 */     float var16 = 1.0F;
/*     */     
/* 360 */     tessellator.func_78382_b();
/* 361 */     tessellator.func_78380_c(200);
/* 362 */     tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F);
/* 363 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/* 364 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/* 365 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/* 366 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */     
/* 368 */     tessellator.func_78381_a();
/*     */     
/* 370 */     GL11.glDisable(3042);
/* 371 */     GL11.glDepthMask(true);
/* 372 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/beams/FXBeamWand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */