/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import net.minecraft.client.particle.EntityFX;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FXGeneric
/*    */   extends EntityFX
/*    */ {
/*    */   public FXGeneric(World world, double x, double y, double z, double xx, double yy, double zz)
/*    */   {
/* 14 */     super(world, x, y, z, xx, yy, zz);
/* 15 */     func_70105_a(0.1F, 0.1F);
/* 16 */     this.field_70145_X = false;
/* 17 */     this.field_70169_q = this.field_70165_t;
/* 18 */     this.field_70167_r = this.field_70163_u;
/* 19 */     this.field_70166_s = this.field_70161_v;
/* 20 */     this.field_70159_w = xx;
/* 21 */     this.field_70181_x = yy;
/* 22 */     this.field_70179_y = zz;
/* 23 */     this.field_70145_X = true;
/*    */   }
/*    */   
/* 26 */   boolean loop = false;
/*    */   
/*    */   public void setLoop(boolean loop) {
/* 29 */     this.loop = loop;
/*    */   }
/*    */   
/*    */   public void setScale(float scale) {
/* 33 */     this.field_70544_f = scale;
/*    */   }
/*    */   
/* 36 */   int delay = 0;
/*    */   
/*    */   public void setMaxAge(int max, int delay) {
/* 39 */     this.field_70547_e = max;
/* 40 */     this.field_70547_e += delay;
/* 41 */     this.delay = delay;
/*    */   }
/*    */   
/* 44 */   int startParticle = 0;
/* 45 */   int numParticles = 1;
/* 46 */   int particleInc = 1;
/*    */   
/*    */   public void setParticles(int startParticle, int numParticles, int particleInc) {
/* 49 */     this.startParticle = startParticle;
/* 50 */     this.numParticles = numParticles;
/* 51 */     this.particleInc = particleInc;
/* 52 */     func_70536_a(startParticle);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 58 */     super.func_70071_h_();
/*    */     
/* 60 */     if (this.loop) {
/* 61 */       func_70536_a(this.startParticle + this.field_70546_d / this.particleInc % this.numParticles);
/*    */     } else {
/* 63 */       float fs = this.field_70546_d / this.field_70547_e;
/* 64 */       func_70536_a((int)(this.startParticle + Math.min(this.numParticles * fs, this.numParticles - 1)));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 71 */     if (this.field_70546_d < this.delay) return;
/* 72 */     float t = this.field_82339_as;
/* 73 */     if ((this.field_70546_d <= 1) || (this.field_70546_d >= this.field_70547_e - 1)) this.field_82339_as = (t / 2.0F);
/* 74 */     super.func_70539_a(tessellator, f, f1, f2, f3, f4, f5);
/* 75 */     this.field_82339_as = t;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXGeneric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */