/*     */ package thaumcraft.client.fx.bolt;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.WRVector3;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ 
/*     */ public class FXLightningBolt
/*     */   extends EntityFX
/*     */ {
/*     */   public FXLightningBolt(World world, WRVector3 jammervec, WRVector3 targetvec, long seed)
/*     */   {
/*  26 */     super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  27 */     this.main = new FXLightningBoltCommon(world, jammervec, targetvec, seed);
/*  28 */     setupFromMain();
/*     */   }
/*     */   
/*     */ 
/*     */   public FXLightningBolt(World world, Entity detonator, Entity target, long seed)
/*     */   {
/*  34 */     super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  35 */     this.main = new FXLightningBoltCommon(world, detonator, target, seed);
/*  36 */     setupFromMain();
/*     */   }
/*     */   
/*     */   public FXLightningBolt(World world, Entity detonator, Entity target, long seed, int speed)
/*     */   {
/*  41 */     super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  42 */     this.main = new FXLightningBoltCommon(world, detonator, target, seed, speed);
/*  43 */     setupFromMain();
/*     */   }
/*     */   
/*     */   public FXLightningBolt(World world, TileEntity detonator, Entity target, long seed)
/*     */   {
/*  48 */     super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  49 */     this.main = new FXLightningBoltCommon(world, detonator, target, seed);
/*  50 */     setupFromMain();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FXLightningBolt(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration, float multi)
/*     */   {
/*  57 */     super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  58 */     this.main = new FXLightningBoltCommon(world, x1, y1, z1, x, y, z, seed, duration, multi);
/*  59 */     setupFromMain();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public FXLightningBolt(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration, float multi, int speed)
/*     */   {
/*  67 */     super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  68 */     this.main = new FXLightningBoltCommon(world, x1, y1, z1, x, y, z, seed, duration, multi, speed);
/*  69 */     setupFromMain();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public FXLightningBolt(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration)
/*     */   {
/*  77 */     super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  78 */     this.main = new FXLightningBoltCommon(world, x1, y1, z1, x, y, z, seed, duration, 1.0F);
/*  79 */     setupFromMain();
/*     */   }
/*     */   
/*     */   public FXLightningBolt(World world, TileEntity detonator, double x, double y, double z, long seed)
/*     */   {
/*  84 */     super(world, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  85 */     this.main = new FXLightningBoltCommon(world, detonator, x, y, z, seed);
/*  86 */     setupFromMain();
/*     */   }
/*     */   
/*     */   private void setupFromMain()
/*     */   {
/*  91 */     this.field_70546_d = this.main.particleMaxAge;
/*  92 */     func_70107_b(this.main.start.x, this.main.start.y, this.main.start.z);
/*  93 */     func_70016_h(0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public void defaultFractal()
/*     */   {
/*  98 */     this.main.defaultFractal();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void fractal(int splits, float amount, float splitchance, float splitlength, float splitangle)
/*     */   {
/* 105 */     this.main.fractal(splits, amount, splitchance, splitlength, splitangle);
/*     */   }
/*     */   
/*     */   public void finalizeBolt()
/*     */   {
/* 110 */     this.main.finalizeBolt();
/* 111 */     FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(this);
/*     */   }
/*     */   
/*     */   public void setType(int type)
/*     */   {
/* 116 */     this.type = type;
/* 117 */     this.main.type = type;
/*     */   }
/*     */   
/*     */   public void setMultiplier(float m) {
/* 121 */     this.main.multiplier = m;
/*     */   }
/*     */   
/*     */   public void setWidth(float m) {
/* 125 */     this.width = m;
/*     */   }
/*     */   
/* 128 */   private int type = 0;
/*     */   
/* 130 */   private float width = 0.03F;
/*     */   private FXLightningBoltCommon main;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 135 */     this.main.onUpdate();
/* 136 */     if (this.main.particleAge >= this.main.particleMaxAge) {
/* 137 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   private static WRVector3 getRelativeViewVector(WRVector3 pos) {
/* 142 */     EntityPlayer renderentity = FMLClientHandler.instance().getClient().field_71439_g;
/* 143 */     return new WRVector3((float)renderentity.field_70165_t - pos.x, (float)renderentity.field_70163_u - pos.y, (float)renderentity.field_70161_v - pos.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void renderBolt(Tessellator tessellator, float partialframe, float cosyaw, float cospitch, float sinyaw, float cossinpitch, int pass, float mainalpha)
/*     */   {
/* 150 */     WRVector3 playervec = new WRVector3(sinyaw * -cospitch, -cossinpitch / cosyaw, cosyaw * cospitch);
/* 151 */     float boltage = this.main.particleAge >= 0 ? this.main.particleAge / this.main.particleMaxAge : 0.0F;
/* 152 */     if (pass == 0) {
/* 153 */       mainalpha = (1.0F - boltage) * 0.4F;
/*     */     } else
/* 155 */       mainalpha = 1.0F - boltage * 0.5F;
/* 156 */     int renderlength = (int)((this.main.particleAge + partialframe + (int)(this.main.length * 3.0F)) / (int)(this.main.length * 3.0F) * this.main.numsegments0);
/* 157 */     for (Iterator iterator = this.main.segments.iterator(); iterator.hasNext();)
/*     */     {
/* 159 */       FXLightningBoltCommon.Segment rendersegment = (FXLightningBoltCommon.Segment)iterator.next();
/* 160 */       if (rendersegment.segmentno <= renderlength)
/*     */       {
/* 162 */         float width = this.width * (getRelativeViewVector(rendersegment.startpoint.point).length() / 5.0F + 1.0F) * (1.0F + rendersegment.light) * 0.5F;
/* 163 */         WRVector3 diff1 = WRVector3.crossProduct(playervec, rendersegment.prevdiff).scale(width / rendersegment.sinprev);
/* 164 */         WRVector3 diff2 = WRVector3.crossProduct(playervec, rendersegment.nextdiff).scale(width / rendersegment.sinnext);
/* 165 */         WRVector3 startvec = rendersegment.startpoint.point;
/* 166 */         WRVector3 endvec = rendersegment.endpoint.point;
/* 167 */         float rx1 = (float)(startvec.x - field_70556_an);
/* 168 */         float ry1 = (float)(startvec.y - field_70554_ao);
/* 169 */         float rz1 = (float)(startvec.z - field_70555_ap);
/* 170 */         float rx2 = (float)(endvec.x - field_70556_an);
/* 171 */         float ry2 = (float)(endvec.y - field_70554_ao);
/* 172 */         float rz2 = (float)(endvec.z - field_70555_ap);
/* 173 */         tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, mainalpha * rendersegment.light);
/* 174 */         tessellator.func_78374_a(rx2 - diff2.x, ry2 - diff2.y, rz2 - diff2.z, 0.5D, 0.0D);
/* 175 */         tessellator.func_78374_a(rx1 - diff1.x, ry1 - diff1.y, rz1 - diff1.z, 0.5D, 0.0D);
/* 176 */         tessellator.func_78374_a(rx1 + diff1.x, ry1 + diff1.y, rz1 + diff1.z, 0.5D, 1.0D);
/* 177 */         tessellator.func_78374_a(rx2 + diff2.x, ry2 + diff2.y, rz2 + diff2.z, 0.5D, 1.0D);
/* 178 */         if (rendersegment.next == null)
/*     */         {
/* 180 */           WRVector3 roundend = rendersegment.endpoint.point.copy().add(rendersegment.diff.copy().normalize().scale(width));
/* 181 */           float rx3 = (float)(roundend.x - field_70556_an);
/* 182 */           float ry3 = (float)(roundend.y - field_70554_ao);
/* 183 */           float rz3 = (float)(roundend.z - field_70555_ap);
/* 184 */           tessellator.func_78374_a(rx3 - diff2.x, ry3 - diff2.y, rz3 - diff2.z, 0.0D, 0.0D);
/* 185 */           tessellator.func_78374_a(rx2 - diff2.x, ry2 - diff2.y, rz2 - diff2.z, 0.5D, 0.0D);
/* 186 */           tessellator.func_78374_a(rx2 + diff2.x, ry2 + diff2.y, rz2 + diff2.z, 0.5D, 1.0D);
/* 187 */           tessellator.func_78374_a(rx3 + diff2.x, ry3 + diff2.y, rz3 + diff2.z, 0.0D, 1.0D);
/*     */         }
/* 189 */         if (rendersegment.prev == null)
/*     */         {
/* 191 */           WRVector3 roundend = rendersegment.startpoint.point.copy().sub(rendersegment.diff.copy().normalize().scale(width));
/* 192 */           float rx3 = (float)(roundend.x - field_70556_an);
/* 193 */           float ry3 = (float)(roundend.y - field_70554_ao);
/* 194 */           float rz3 = (float)(roundend.z - field_70555_ap);
/* 195 */           tessellator.func_78374_a(rx1 - diff1.x, ry1 - diff1.y, rz1 - diff1.z, 0.5D, 0.0D);
/* 196 */           tessellator.func_78374_a(rx3 - diff1.x, ry3 - diff1.y, rz3 - diff1.z, 0.0D, 0.0D);
/* 197 */           tessellator.func_78374_a(rx3 + diff1.x, ry3 + diff1.y, rz3 + diff1.z, 0.0D, 1.0D);
/* 198 */           tessellator.func_78374_a(rx1 + diff1.x, ry1 + diff1.y, rz1 + diff1.z, 0.5D, 1.0D);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float partialframe, float cosyaw, float cospitch, float sinyaw, float sinsinpitch, float cossinpitch)
/*     */   {
/* 209 */     EntityPlayer renderentity = FMLClientHandler.instance().getClient().field_71439_g;
/* 210 */     int visibleDistance = 100;
/* 211 */     if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) visibleDistance = 50;
/* 212 */     if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > visibleDistance) { return;
/*     */     }
/* 214 */     tessellator.func_78381_a();
/* 215 */     GL11.glPushMatrix();
/*     */     
/* 217 */     GL11.glDepthMask(false);
/* 218 */     GL11.glEnable(3042);
/*     */     
/* 220 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 1.0F);
/* 221 */     float ma = 1.0F;
/*     */     
/* 223 */     switch (this.type) {
/* 224 */     case 0:  this.field_70552_h = 0.6F;this.field_70553_i = 0.3F;this.field_70551_j = 0.6F;GL11.glBlendFunc(770, 1); break;
/* 225 */     case 1:  this.field_70552_h = 0.6F;this.field_70553_i = 0.6F;this.field_70551_j = 0.1F;GL11.glBlendFunc(770, 1); break;
/* 226 */     case 2:  this.field_70552_h = 0.1F;this.field_70553_i = 0.1F;this.field_70551_j = 0.6F;GL11.glBlendFunc(770, 1); break;
/* 227 */     case 3:  this.field_70552_h = 0.1F;this.field_70553_i = 1.0F;this.field_70551_j = 0.1F;GL11.glBlendFunc(770, 1); break;
/* 228 */     case 4:  this.field_70552_h = 0.6F;this.field_70553_i = 0.1F;this.field_70551_j = 0.1F;GL11.glBlendFunc(770, 1); break;
/* 229 */     case 5:  this.field_70552_h = 0.6F;this.field_70553_i = 0.2F;this.field_70551_j = 0.6F;GL11.glBlendFunc(770, 771); break;
/* 230 */     case 6:  this.field_70552_h = 0.75F;this.field_70553_i = 1.0F;this.field_70551_j = 1.0F;ma = 0.2F;GL11.glBlendFunc(770, 771);
/*     */     }
/*     */     
/* 233 */     UtilsFX.bindTexture("textures/misc/p_large.png");
/* 234 */     tessellator.func_78382_b();
/* 235 */     tessellator.func_78380_c(15728880);
/* 236 */     renderBolt(tessellator, partialframe, cosyaw, cospitch, sinyaw, cossinpitch, 0, ma);
/* 237 */     tessellator.func_78381_a();
/*     */     
/* 239 */     switch (this.type) {
/* 240 */     case 0:  this.field_70552_h = 1.0F;this.field_70553_i = 0.6F;this.field_70551_j = 1.0F; break;
/* 241 */     case 1:  this.field_70552_h = 1.0F;this.field_70553_i = 1.0F;this.field_70551_j = 0.1F; break;
/* 242 */     case 2:  this.field_70552_h = 0.1F;this.field_70553_i = 0.1F;this.field_70551_j = 1.0F; break;
/* 243 */     case 3:  this.field_70552_h = 0.1F;this.field_70553_i = 0.6F;this.field_70551_j = 0.1F; break;
/* 244 */     case 4:  this.field_70552_h = 1.0F;this.field_70553_i = 0.1F;this.field_70551_j = 0.1F; break;
/* 245 */     case 5:  this.field_70552_h = 0.0F;this.field_70553_i = 0.0F;this.field_70551_j = 0.0F;GL11.glBlendFunc(770, 771); break;
/* 246 */     case 6:  this.field_70552_h = 0.75F;this.field_70553_i = 1.0F;this.field_70551_j = 1.0F;ma = 0.2F;GL11.glBlendFunc(770, 771);
/*     */     }
/*     */     
/* 249 */     UtilsFX.bindTexture("textures/misc/p_small.png");
/* 250 */     tessellator.func_78382_b();
/* 251 */     tessellator.func_78380_c(15728880);
/* 252 */     renderBolt(tessellator, partialframe, cosyaw, cospitch, sinyaw, cossinpitch, 1, ma);
/* 253 */     tessellator.func_78381_a();
/*     */     
/* 255 */     GL11.glDisable(3042);
/* 256 */     GL11.glDepthMask(true);
/* 257 */     GL11.glPopMatrix();
/*     */     
/* 259 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
/*     */     
/* 261 */     tessellator.func_78382_b();
/*     */   }
/*     */   
/*     */   public int getRenderPass()
/*     */   {
/* 266 */     return 2;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/bolt/FXLightningBolt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */