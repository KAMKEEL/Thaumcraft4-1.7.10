/*     */ package thaumcraft.client.fx.other;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.monster.EntityCultist;
/*     */ 
/*     */ public class FXShieldRunes extends EntityFX
/*     */ {
/*     */   public FXShieldRunes(World world, double d, double d1, double d2, Entity target, int age, float yaw, float pitch)
/*     */   {
/*  23 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     
/*  25 */     this.field_70552_h = 1.0F;
/*  26 */     this.field_70553_i = 1.0F;
/*  27 */     this.field_70551_j = 1.0F;
/*  28 */     this.field_70545_g = 0.0F;
/*  29 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/*     */     
/*  31 */     this.field_70547_e = (age + this.field_70146_Z.nextInt(age / 2));
/*     */     
/*  33 */     this.field_70145_X = false;
/*     */     
/*  35 */     func_70105_a(0.01F, 0.01F);
/*     */     
/*  37 */     this.field_70145_X = true;
/*     */     
/*  39 */     this.field_70544_f = 1.0F;
/*     */     
/*  41 */     this.target = target;
/*  42 */     this.yaw = yaw;
/*  43 */     this.pitch = pitch;
/*     */     
/*  45 */     this.field_70169_q = (this.field_70165_t = target.field_70165_t);
/*  46 */     this.field_70167_r = (this.field_70163_u = (target.field_70121_D.field_72338_b + target.field_70121_D.field_72337_e) / 2.0D);
/*  47 */     this.field_70166_s = (this.field_70161_v = target.field_70161_v);
/*     */   }
/*     */   
/*  50 */   Entity target = null;
/*  51 */   float yaw = 0.0F;
/*  52 */   float pitch = 0.0F;
/*     */   
/*     */   private IModelCustom model;
/*  55 */   private static final ResourceLocation MODEL = new ResourceLocation("thaumcraft", "textures/models/hemis.obj");
/*     */   
/*     */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
/*  58 */     tessellator.func_78381_a();
/*  59 */     GL11.glPushMatrix();
/*  60 */     GL11.glDisable(2884);
/*     */     
/*  62 */     GL11.glEnable(3042);
/*  63 */     GL11.glBlendFunc(770, 1);
/*     */     
/*  65 */     if (this.model == null) {
/*  66 */       this.model = net.minecraftforge.client.model.AdvancedModelLoader.loadModel(MODEL);
/*     */     }
/*     */     
/*  69 */     float fade = (this.field_70546_d + f) / this.field_70547_e;
/*     */     
/*  71 */     float xx = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/*  72 */     float yy = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/*  73 */     float zz = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*  74 */     GL11.glTranslated(xx, yy, zz);
/*  75 */     float b = 1.0F;
/*  76 */     int frame = Math.min(15, (int)(14.0F * fade) + 1);
/*  77 */     if (((this.target instanceof EntityMob)) && (!(this.target instanceof EntityCultist))) {
/*  78 */       UtilsFX.bindTexture("textures/models/ripple" + frame + ".png");
/*  79 */       b = 0.5F;
/*     */     } else {
/*  81 */       UtilsFX.bindTexture("textures/models/hemis" + frame + ".png");
/*     */     }
/*  83 */     int i = 220;
/*  84 */     int j = i % 65536;
/*  85 */     int k = i / 65536;
/*  86 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/*  87 */     GL11.glRotatef(180.0F - this.yaw, 0.0F, 1.0F, 0.0F);
/*  88 */     GL11.glRotatef(-this.pitch, 1.0F, 0.0F, 0.0F);
/*  89 */     GL11.glScaled(0.4D * this.target.field_70131_O, 0.4D * this.target.field_70131_O, 0.4D * this.target.field_70131_O);
/*  90 */     GL11.glColor4f(b, b, b, Math.min(1.0F, (1.0F - fade) * 3.0F));
/*  91 */     this.model.renderAll();
/*     */     
/*  93 */     GL11.glDisable(3042);
/*     */     
/*  95 */     GL11.glEnable(2884);
/*  96 */     GL11.glPopMatrix();
/*     */     
/*  98 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
/*  99 */     tessellator.func_78382_b();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 105 */     this.field_70169_q = this.field_70165_t;
/* 106 */     this.field_70167_r = this.field_70163_u;
/* 107 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 109 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 110 */       func_70106_y();
/*     */     }
/*     */     
/* 113 */     this.field_70165_t = this.target.field_70165_t;
/* 114 */     this.field_70163_u = ((this.target.field_70121_D.field_72338_b + this.target.field_70121_D.field_72337_e) / 2.0D);
/* 115 */     this.field_70161_v = this.target.field_70161_v;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/other/FXShieldRunes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */