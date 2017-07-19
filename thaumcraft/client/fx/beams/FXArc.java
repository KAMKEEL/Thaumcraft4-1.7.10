/*     */ package thaumcraft.client.fx.beams;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ public class FXArc
/*     */   extends EntityFX
/*     */ {
/*  20 */   public int particle = 16;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FXArc(World par1World, double x, double y, double z, double tx, double ty, double tz, float red, float green, float blue, double hg)
/*     */   {
/*  28 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
/*     */     
/*  30 */     this.field_70552_h = red;
/*  31 */     this.field_70553_i = green;
/*  32 */     this.field_70551_j = blue;
/*     */     
/*  34 */     func_70105_a(0.02F, 0.02F);
/*  35 */     this.field_70145_X = true;
/*  36 */     this.field_70159_w = 0.0D;
/*  37 */     this.field_70181_x = 0.0D;
/*  38 */     this.field_70179_y = 0.0D;
/*  39 */     this.tX = (tx - x);
/*  40 */     this.tY = (ty - y);
/*  41 */     this.tZ = (tz - z);
/*     */     
/*     */ 
/*     */ 
/*  45 */     this.field_70547_e = 1;
/*     */     
/*  47 */     double xx = 0.0D;
/*  48 */     double yy = 0.0D;
/*  49 */     double zz = 0.0D;
/*     */     
/*  51 */     double gravity = 0.115D;
/*  52 */     double noise = 0.25D;
/*     */     
/*  54 */     Vec3 vs = Vec3.func_72443_a(xx, yy, zz);
/*  55 */     Vec3 ve = Vec3.func_72443_a(this.tX, this.tY, this.tZ);
/*  56 */     Vec3 vc = Vec3.func_72443_a(xx, yy, zz);
/*     */     
/*  58 */     this.length = ((float)ve.func_72433_c());
/*  59 */     Vec3 vv = Utils.calculateVelocity(vs, ve, hg, gravity);
/*  60 */     double l = Utils.distanceSquared3d(Vec3.func_72443_a(0.0D, 0.0D, 0.0D), vv);
/*     */     
/*  62 */     this.points.add(vs);
/*     */     
/*  64 */     int c = 0;
/*  65 */     while ((Utils.distanceSquared3d(ve, vc) > l) && (c < 50))
/*     */     {
/*  67 */       Vec3 vt = vc.func_72441_c(vv.field_72450_a, vv.field_72448_b, vv.field_72449_c);
/*  68 */       vc = Vec3.func_72443_a(vt.field_72450_a, vt.field_72448_b, vt.field_72449_c);
/*  69 */       vt.field_72450_a += (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * noise;
/*  70 */       vt.field_72448_b += (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * noise;
/*  71 */       vt.field_72449_c += (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * noise;
/*  72 */       this.points.add(vt);
/*  73 */       vv.field_72448_b -= gravity / 1.9D;
/*  74 */       c++;
/*     */     }
/*     */     
/*  77 */     this.points.add(ve);
/*     */   }
/*     */   
/*  80 */   ArrayList<Vec3> points = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  89 */     this.field_70169_q = this.field_70165_t;
/*  90 */     this.field_70167_r = this.field_70163_u;
/*  91 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  93 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/*  95 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b)
/*     */   {
/* 101 */     this.field_70552_h = r;
/* 102 */     this.field_70553_i = g;
/* 103 */     this.field_70551_j = b;
/*     */   }
/*     */   
/* 106 */   private Entity targetEntity = null;
/* 107 */   private double tX = 0.0D;
/* 108 */   private double tY = 0.0D;
/* 109 */   private double tZ = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 115 */     tessellator.func_78381_a();
/*     */     
/* 117 */     GL11.glPushMatrix();
/* 118 */     double ePX = this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an;
/* 119 */     double ePY = this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao;
/* 120 */     double ePZ = this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap;
/* 121 */     GL11.glTranslated(ePX, ePY, ePZ);
/* 122 */     float size = 0.25F;
/*     */     
/* 124 */     UtilsFX.bindTexture("textures/misc/beamh.png");
/*     */     
/* 126 */     GL11.glDepthMask(false);
/* 127 */     GL11.glEnable(3042);
/* 128 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 130 */     GL11.glDisable(2884);
/*     */     
/* 132 */     tessellator.func_78371_b(5);
/* 133 */     tessellator.func_78380_c(200);
/* 134 */     tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8F);
/*     */     
/* 136 */     float f9 = 0.0F;
/* 137 */     float f10 = 1.0F;
/* 138 */     for (int c = 0; c < this.points.size(); c++) {
/* 139 */       Vec3 v = (Vec3)this.points.get(c);
/* 140 */       float f13 = c / this.length;
/* 141 */       double dx = v.field_72450_a;
/* 142 */       double dy = v.field_72448_b;
/* 143 */       double dz = v.field_72449_c;
/*     */       
/* 145 */       tessellator.func_78374_a(dx, dy - size, dz, f13, f10);
/* 146 */       tessellator.func_78374_a(dx, dy + size, dz, f13, f9);
/*     */     }
/*     */     
/* 149 */     tessellator.func_78381_a();
/*     */     
/* 151 */     tessellator.func_78371_b(5);
/* 152 */     tessellator.func_78380_c(200);
/* 153 */     tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8F);
/* 154 */     for (int c = 0; c < this.points.size(); c++) {
/* 155 */       Vec3 v = (Vec3)this.points.get(c);
/* 156 */       float f13 = c / this.length;
/* 157 */       double dx = v.field_72450_a;
/* 158 */       double dy = v.field_72448_b;
/* 159 */       double dz = v.field_72449_c;
/* 160 */       tessellator.func_78374_a(dx - size, dy, dz - size, f13, f10);
/* 161 */       tessellator.func_78374_a(dx + size, dy, dz + size, f13, f9);
/*     */     }
/*     */     
/* 164 */     tessellator.func_78381_a();
/*     */     
/* 166 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 167 */     GL11.glEnable(2884);
/* 168 */     GL11.glDisable(3042);
/* 169 */     GL11.glDepthMask(true);
/*     */     
/* 171 */     GL11.glPopMatrix();
/*     */     
/* 173 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
/*     */     
/* 175 */     tessellator.func_78382_b();
/*     */   }
/*     */   
/* 178 */   public int blendmode = 1;
/* 179 */   public float length = 1.0F;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/beams/FXArc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */