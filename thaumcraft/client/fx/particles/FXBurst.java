/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.tile.TileNodeRenderer;
/*     */ 
/*     */ 
/*     */ public class FXBurst
/*     */   extends EntityFX
/*     */ {
/*     */   public FXBurst(World world, double d, double d1, double d2, float f)
/*     */   {
/*  18 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     
/*  20 */     this.field_70552_h = 1.0F;
/*  21 */     this.field_70553_i = 1.0F;
/*  22 */     this.field_70551_j = 1.0F;
/*  23 */     this.field_70545_g = 0.0F;
/*  24 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*  25 */     this.field_70544_f *= f;
/*  26 */     this.field_70547_e = 31;
/*  27 */     this.field_70145_X = false;
/*  28 */     func_70105_a(0.01F, 0.01F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  36 */     tessellator.func_78381_a();
/*  37 */     GL11.glPushMatrix();
/*     */     
/*     */ 
/*  40 */     GL11.glDepthMask(false);
/*  41 */     GL11.glEnable(3042);
/*  42 */     GL11.glBlendFunc(770, 1);
/*     */     
/*  44 */     UtilsFX.bindTexture(TileNodeRenderer.nodetex);
/*     */     
/*  46 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*     */     
/*  48 */     float var8 = this.field_70546_d % 32 / 32.0F;
/*  49 */     float var9 = var8 + 0.03125F;
/*  50 */     float var10 = 0.96875F;
/*  51 */     float var11 = 1.0F;
/*  52 */     float var12 = this.field_70544_f;
/*     */     
/*  54 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  55 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  56 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*  57 */     float var16 = 1.0F;
/*     */     
/*  59 */     tessellator.func_78382_b();
/*  60 */     tessellator.func_78380_c(240);
/*     */     
/*  62 */     tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0F);
/*  63 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/*  64 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/*  65 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/*  66 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*     */     
/*  68 */     tessellator.func_78381_a();
/*     */     
/*  70 */     GL11.glDisable(3042);
/*  71 */     GL11.glDepthMask(true);
/*     */     
/*  73 */     GL11.glPopMatrix();
/*  74 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
/*  75 */     tessellator.func_78382_b();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  82 */     this.field_70169_q = this.field_70165_t;
/*  83 */     this.field_70167_r = this.field_70163_u;
/*  84 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  86 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/*  88 */       func_70106_y();
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
/*     */   public void setGravity(float value)
/*     */   {
/* 101 */     this.field_70545_g = value;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXBurst.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */