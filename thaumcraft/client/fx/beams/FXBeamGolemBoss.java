/*     */ package thaumcraft.client.fx.beams;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ public class FXBeamGolemBoss extends EntityFX
/*     */ {
/*  20 */   public int particle = 16;
/*  21 */   EntityLivingBase boss = null;
/*     */   
/*     */ 
/*     */ 
/*     */   public FXBeamGolemBoss(World par1World, EntityLivingBase boss, Entity entity, float red, float green, float blue, int age)
/*     */   {
/*  27 */     super(par1World, boss.field_70165_t, boss.field_70163_u, boss.field_70161_v, 0.0D, 0.0D, 0.0D);
/*  28 */     this.boss = boss;
/*  29 */     float f1 = MathHelper.func_76134_b(-boss.field_70761_aq * 0.017453292F - 3.1415927F);
/*  30 */     float f2 = MathHelper.func_76126_a(-boss.field_70761_aq * 0.017453292F - 3.1415927F);
/*  31 */     float f3 = -MathHelper.func_76134_b(-boss.field_70125_A * 0.017453292F);
/*  32 */     float f4 = MathHelper.func_76126_a(-boss.field_70125_A * 0.017453292F);
/*  33 */     Vec3 v = Vec3.func_72443_a(f2 * f3, f4, f1 * f3);
/*  34 */     this.field_70169_q = (this.field_70165_t = boss.field_70165_t + v.field_72450_a * 0.5D);
/*  35 */     this.field_70167_r = (this.field_70163_u = boss.field_70163_u + boss.func_70047_e());
/*  36 */     this.field_70166_s = (this.field_70161_v = boss.field_70161_v + v.field_72449_c * 0.5D);
/*     */     
/*  38 */     this.field_70552_h = red;
/*  39 */     this.field_70553_i = green;
/*  40 */     this.field_70551_j = blue;
/*     */     
/*  42 */     func_70105_a(0.02F, 0.02F);
/*  43 */     this.field_70145_X = true;
/*  44 */     this.field_70159_w = 0.0D;
/*  45 */     this.field_70181_x = 0.0D;
/*  46 */     this.field_70179_y = 0.0D;
/*     */     
/*  48 */     this.targetEntity = entity;
/*     */     
/*  50 */     this.tX = this.targetEntity.field_70169_q;
/*  51 */     this.tY = (this.targetEntity.field_70121_D.field_72338_b + this.targetEntity.field_70131_O / 2.0F);
/*  52 */     this.tZ = this.targetEntity.field_70166_s;
/*  53 */     float xd = (float)(this.field_70165_t - this.tX);
/*  54 */     float yd = (float)(this.field_70163_u - this.tY);
/*  55 */     float zd = (float)(this.field_70161_v - this.tZ);
/*  56 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*  57 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*  58 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/*  59 */     this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D));
/*  60 */     this.prevYaw = this.rotYaw;
/*  61 */     this.prevPitch = this.rotPitch;
/*     */     
/*  63 */     this.field_70547_e = age;
/*     */     
/*  65 */     EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
/*  66 */     int visibleDistance = 50;
/*  67 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 25;
/*  68 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) { this.field_70547_e = 0;
/*     */     }
/*     */   }
/*     */   
/*  72 */   double movX = 0.0D;
/*  73 */   double movY = 0.0D;
/*  74 */   double movZ = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  82 */     this.field_70169_q = this.field_70165_t;
/*  83 */     this.field_70167_r = this.field_70163_u;
/*  84 */     this.field_70166_s = this.field_70161_v;
/*  85 */     this.ptX = this.tX;
/*  86 */     this.ptY = this.tY;
/*  87 */     this.ptZ = this.tZ;
/*     */     
/*  89 */     float f1 = MathHelper.func_76134_b(-this.boss.field_70761_aq * 0.017453292F - 3.1415927F);
/*  90 */     float f2 = MathHelper.func_76126_a(-this.boss.field_70761_aq * 0.017453292F - 3.1415927F);
/*  91 */     float f3 = -MathHelper.func_76134_b(-this.boss.field_70125_A * 0.017453292F);
/*  92 */     float f4 = MathHelper.func_76126_a(-this.boss.field_70125_A * 0.017453292F);
/*  93 */     Vec3 v = Vec3.func_72443_a(f2 * f3, f4, f1 * f3);
/*  94 */     this.field_70165_t = (this.boss.field_70165_t + v.field_72450_a * 0.5D);
/*  95 */     this.field_70163_u = (this.boss.field_70163_u + this.boss.func_70047_e());
/*  96 */     this.field_70161_v = (this.boss.field_70161_v + v.field_72449_c * 0.5D);
/*     */     
/*  98 */     this.prevYaw = this.rotYaw;
/*  99 */     this.prevPitch = this.rotPitch;
/*     */     
/* 101 */     if (this.targetEntity != null) {
/* 102 */       this.tX = this.targetEntity.field_70169_q;
/* 103 */       this.tY = (this.targetEntity.field_70121_D.field_72338_b + this.targetEntity.field_70131_O / 2.0F);
/* 104 */       this.tZ = this.targetEntity.field_70166_s;
/*     */     }
/*     */     
/* 107 */     float xd = (float)(this.field_70165_t - this.tX);
/* 108 */     float yd = (float)(this.field_70163_u - this.tY);
/* 109 */     float zd = (float)(this.field_70161_v - this.tZ);
/*     */     
/* 111 */     this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*     */     
/*     */ 
/* 114 */     double var7 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*     */     
/* 116 */     this.rotYaw = ((float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D));
/* 117 */     this.rotPitch = ((float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D));
/*     */     
/* 119 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 121 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b)
/*     */   {
/* 127 */     this.field_70552_h = r;
/* 128 */     this.field_70553_i = g;
/* 129 */     this.field_70551_j = b;
/*     */   }
/*     */   
/* 132 */   private float length = 0.0F;
/* 133 */   private float rotYaw = 0.0F;
/* 134 */   private float rotPitch = 0.0F;
/* 135 */   private float prevYaw = 0.0F;
/* 136 */   private float prevPitch = 0.0F;
/* 137 */   private Entity targetEntity = null;
/* 138 */   private double tX = 0.0D;
/* 139 */   private double tY = 0.0D;
/* 140 */   private double tZ = 0.0D;
/* 141 */   private double ptX = 0.0D;
/* 142 */   private double ptY = 0.0D;
/* 143 */   private double ptZ = 0.0D;
/*     */   
/* 145 */   private int type = 0;
/*     */   
/* 147 */   public void setType(int type) { this.type = type; }
/*     */   
/*     */ 
/* 150 */   private float endMod = 1.0F;
/*     */   
/* 152 */   public void setEndMod(float endMod) { this.endMod = endMod; }
/*     */   
/*     */ 
/* 155 */   private boolean reverse = false;
/*     */   
/* 157 */   public void setReverse(boolean reverse) { this.reverse = reverse; }
/*     */   
/*     */ 
/* 160 */   private boolean pulse = true;
/*     */   
/* 162 */   public void setPulse(boolean pulse) { this.pulse = pulse; }
/*     */   
/*     */ 
/* 165 */   private int rotationspeed = 5;
/*     */   
/* 167 */   public void setRotationspeed(int rotationspeed) { this.rotationspeed = rotationspeed; }
/*     */   
/*     */ 
/* 170 */   private float prevSize = 0.0F;
/*     */   
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 175 */     tessellator.func_78381_a();
/*     */     
/* 177 */     GL11.glPushMatrix();
/* 178 */     float var9 = 1.0F;
/* 179 */     float slide = Minecraft.func_71410_x().field_71439_g.field_70173_aa;
/* 180 */     float rot = (float)(this.field_70170_p.field_73011_w.getWorldTime() % (360 / this.rotationspeed) * this.rotationspeed) + this.rotationspeed * f;
/*     */     
/* 182 */     float size = this.field_70130_N;
/* 183 */     if (this.pulse) {
/* 184 */       size = Math.min(this.field_70546_d / 4.0F, this.field_70130_N);
/* 185 */       size = (float)(this.prevSize + (size - this.prevSize) * f);
/*     */     }
/*     */     
/* 188 */     float op = 0.4F;
/* 189 */     if ((this.pulse) && (this.field_70547_e - this.field_70546_d <= 4)) {
/* 190 */       op = 0.4F - (4 - (this.field_70547_e - this.field_70546_d)) * 0.1F;
/*     */     }
/* 192 */     switch (this.type) {
/* 193 */     default:  UtilsFX.bindTexture("textures/misc/beam.png"); break;
/* 194 */     case 1:  UtilsFX.bindTexture("textures/misc/beam1.png"); break;
/* 195 */     case 2:  UtilsFX.bindTexture("textures/misc/beam2.png"); break;
/* 196 */     case 3:  UtilsFX.bindTexture("textures/misc/beam3.png");
/*     */     }
/*     */     
/* 199 */     GL11.glTexParameterf(3553, 10242, 10497.0F);
/* 200 */     GL11.glTexParameterf(3553, 10243, 10497.0F);
/* 201 */     GL11.glDisable(2884);
/*     */     
/* 203 */     float var11 = slide + f;
/* 204 */     if (this.reverse) var11 *= -1.0F;
/* 205 */     float var12 = -var11 * 0.2F - MathHelper.func_76141_d(-var11 * 0.1F);
/*     */     
/* 207 */     GL11.glEnable(3042);
/* 208 */     GL11.glBlendFunc(770, this.blendmode);
/* 209 */     GL11.glDepthMask(false);
/*     */     
/* 211 */     float xx = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 212 */     float yy = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 213 */     float zz = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/* 214 */     GL11.glTranslated(xx, yy, zz);
/*     */     
/* 216 */     float ry = (float)(this.prevYaw + (this.rotYaw - this.prevYaw) * f);
/* 217 */     float rp = (float)(this.prevPitch + (this.rotPitch - this.prevPitch) * f);
/* 218 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 219 */     GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
/* 220 */     GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
/*     */     
/* 222 */     double var44 = -0.15D * size;
/* 223 */     double var17 = 0.15D * size;
/* 224 */     double var44b = -0.15D * size * this.endMod;
/* 225 */     double var17b = 0.15D * size * this.endMod;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 230 */     GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
/* 231 */     for (int t = 0; t < 3; t++)
/*     */     {
/* 233 */       double var29 = this.length * size / this.field_70130_N * var9;
/* 234 */       double var31 = 0.0D;
/* 235 */       double var33 = 1.0D;
/* 236 */       double var35 = -1.0F + var12 + t / 3.0F;
/* 237 */       double var37 = this.length * size / this.field_70130_N * var9 + var35;
/*     */       
/* 239 */       GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/* 240 */       tessellator.func_78382_b();
/* 241 */       tessellator.func_78380_c(200);
/* 242 */       tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op);
/* 243 */       tessellator.func_78374_a(var44b, var29, 0.0D, var33, var37);
/* 244 */       tessellator.func_78374_a(var44, 0.0D, 0.0D, var33, var35);
/* 245 */       tessellator.func_78374_a(var17, 0.0D, 0.0D, var31, var35);
/* 246 */       tessellator.func_78374_a(var17b, var29, 0.0D, var31, var37);
/* 247 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*     */ 
/* 251 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 252 */     GL11.glDepthMask(true);
/* 253 */     GL11.glDisable(3042);
/* 254 */     GL11.glEnable(2884);
/* 255 */     GL11.glPopMatrix();
/*     */     
/* 257 */     renderImpact(tessellator, f, f1, f2, f3, f4, f5);
/*     */     
/* 259 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
/*     */     
/* 261 */     tessellator.func_78382_b();
/* 262 */     this.prevSize = size;
/*     */   }
/*     */   
/*     */ 
/*     */   public void renderImpact(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 268 */     GL11.glPushMatrix();
/* 269 */     GL11.glDepthMask(false);
/* 270 */     GL11.glEnable(3042);
/* 271 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 273 */     UtilsFX.bindTexture(thaumcraft.client.fx.ParticleEngine.particleTexture);
/*     */     
/* 275 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/* 276 */     int part = this.field_70546_d % 16;
/*     */     
/* 278 */     float var8 = part / 16.0F;
/* 279 */     float var9 = var8 + 0.0624375F;
/* 280 */     float var10 = 0.3125F;
/* 281 */     float var11 = var10 + 0.0624375F;
/* 282 */     float var12 = this.endMod / 4.0F * this.field_70130_N;
/*     */     
/* 284 */     float var13 = (float)(this.ptX + (this.tX - this.ptX) * f - field_70556_an);
/* 285 */     float var14 = (float)(this.ptY + (this.tY - this.ptY) * f - field_70554_ao);
/* 286 */     float var15 = (float)(this.ptZ + (this.tZ - this.ptZ) * f - field_70555_ap);
/*     */     
/*     */ 
/* 289 */     float var16 = 1.0F;
/*     */     
/* 291 */     tessellator.func_78382_b();
/* 292 */     tessellator.func_78380_c(200);
/* 293 */     tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F);
/* 294 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/* 295 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/* 296 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/* 297 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */     
/* 299 */     tessellator.func_78381_a();
/*     */     
/* 301 */     GL11.glDisable(3042);
/* 302 */     GL11.glDepthMask(true);
/* 303 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/* 307 */   public int blendmode = 1;
/* 308 */   public float field_70130_N = 1.0F;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/beams/FXBeamGolemBoss.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */