/*     */ package thaumcraft.client.renderers.models.entities;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import thaumcraft.common.entities.monster.EntityWatcher;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelWatcher extends ModelBase
/*     */ {
/*     */   private ModelRenderer guardianBody;
/*     */   private ModelRenderer guardianEye;
/*     */   private ModelRenderer[] guardianSpines;
/*     */   private ModelRenderer[] guardianTail;
/*     */   private static final String __OBFID = "CL_00002628";
/*     */   
/*     */   public ModelWatcher()
/*     */   {
/*  24 */     this.field_78090_t = 64;
/*  25 */     this.field_78089_u = 64;
/*  26 */     this.guardianSpines = new ModelRenderer[12];
/*  27 */     this.guardianBody = new ModelRenderer(this);
/*  28 */     this.guardianBody.func_78784_a(0, 0).func_78789_a(-6.0F, 10.0F, -8.0F, 12, 12, 16);
/*  29 */     this.guardianBody.func_78784_a(0, 28).func_78789_a(-8.0F, 10.0F, -6.0F, 2, 12, 12);
/*  30 */     this.guardianBody.field_78809_i = true;
/*  31 */     this.guardianBody.func_78784_a(0, 28).func_78789_a(6.0F, 10.0F, -6.0F, 2, 12, 12);
/*  32 */     this.guardianBody.field_78809_i = false;
/*  33 */     this.guardianBody.func_78784_a(16, 40).func_78789_a(-6.0F, 8.0F, -6.0F, 12, 2, 12);
/*  34 */     this.guardianBody.func_78784_a(16, 40).func_78789_a(-6.0F, 22.0F, -6.0F, 12, 2, 12);
/*     */     
/*  36 */     for (int i = 0; i < this.guardianSpines.length; i++)
/*     */     {
/*  38 */       this.guardianSpines[i] = new ModelRenderer(this, 0, 0);
/*  39 */       this.guardianSpines[i].func_78789_a(-1.0F, -4.5F, -1.0F, 2, 9, 2);
/*  40 */       this.guardianBody.func_78792_a(this.guardianSpines[i]);
/*     */     }
/*     */     
/*  43 */     this.guardianEye = new ModelRenderer(this, 8, 0);
/*  44 */     this.guardianEye.func_78789_a(-1.0F, 15.0F, 0.0F, 2, 2, 1);
/*  45 */     this.guardianBody.func_78792_a(this.guardianEye);
/*  46 */     this.guardianTail = new ModelRenderer[3];
/*  47 */     this.guardianTail[0] = new ModelRenderer(this, 40, 0);
/*  48 */     this.guardianTail[0].func_78789_a(-2.0F, 14.0F, 7.0F, 4, 4, 8);
/*  49 */     this.guardianTail[1] = new ModelRenderer(this, 0, 54);
/*  50 */     this.guardianTail[1].func_78789_a(0.0F, 14.0F, 0.0F, 3, 3, 7);
/*  51 */     this.guardianTail[2] = new ModelRenderer(this);
/*  52 */     this.guardianTail[2].func_78784_a(41, 32).func_78789_a(0.0F, 14.0F, 0.0F, 2, 2, 6);
/*  53 */     this.guardianTail[2].func_78784_a(25, 19).func_78789_a(1.0F, 10.5F, 3.0F, 1, 9, 9);
/*  54 */     this.guardianBody.func_78792_a(this.guardianTail[0]);
/*  55 */     this.guardianTail[0].func_78792_a(this.guardianTail[1]);
/*  56 */     this.guardianTail[1].func_78792_a(this.guardianTail[2]);
/*     */   }
/*     */   
/*     */   public int func_178706_a()
/*     */   {
/*  61 */     return 54;
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
/*     */   {
/*  66 */     func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*  67 */     this.guardianBody.func_78785_a(p_78088_7_);
/*     */   }
/*     */   
/*     */   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*     */   {
/*  72 */     EntityWatcher entityguardian = (EntityWatcher)p_78087_7_;
/*  73 */     float f6 = p_78087_3_ - entityguardian.field_70173_aa;
/*  74 */     this.guardianBody.field_78796_g = (p_78087_4_ / 57.295776F);
/*  75 */     this.guardianBody.field_78795_f = (p_78087_5_ / 57.295776F);
/*  76 */     float[] afloat = { 1.75F, 0.25F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.75F, 0.0F, 0.0F };
/*  77 */     float[] afloat1 = { 0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 1.75F, 1.25F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F };
/*  78 */     float[] afloat2 = { 0.0F, 0.0F, 0.25F, 1.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.75F, 1.25F };
/*  79 */     float[] afloat3 = { 0.0F, 0.0F, 8.0F, -8.0F, -8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F, 8.0F, -8.0F };
/*  80 */     float[] afloat4 = { -8.0F, -8.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F };
/*  81 */     float[] afloat5 = { 8.0F, -8.0F, 0.0F, 0.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F };
/*  82 */     float f7 = (1.0F - entityguardian.func_175469_o(f6)) * 0.55F;
/*     */     
/*  84 */     for (int i = 0; i < 12; i++)
/*     */     {
/*  86 */       this.guardianSpines[i].field_78795_f = (3.1415927F * afloat[i]);
/*  87 */       this.guardianSpines[i].field_78796_g = (3.1415927F * afloat1[i]);
/*  88 */       this.guardianSpines[i].field_78808_h = (3.1415927F * afloat2[i]);
/*  89 */       this.guardianSpines[i].field_78800_c = (afloat3[i] * (1.0F + MathHelper.func_76134_b(p_78087_3_ * 1.5F + i) * 0.01F - f7));
/*  90 */       this.guardianSpines[i].field_78797_d = (16.0F + afloat4[i] * (1.0F + MathHelper.func_76134_b(p_78087_3_ * 1.5F + i) * 0.01F - f7));
/*  91 */       this.guardianSpines[i].field_78798_e = (afloat5[i] * (1.0F + MathHelper.func_76134_b(p_78087_3_ * 1.5F + i) * 0.01F - f7));
/*     */     }
/*     */     
/*  94 */     this.guardianEye.field_78798_e = -8.25F;
/*  95 */     Object object = Minecraft.func_71410_x().field_71451_h;
/*     */     
/*  97 */     if (entityguardian.func_175474_cn())
/*     */     {
/*  99 */       object = entityguardian.getTargetedEntity();
/*     */     }
/*     */     
/* 102 */     if (object != null)
/*     */     {
/* 104 */       Vec3 vec3 = getPositionEyes((Entity)object, 0.0F);
/* 105 */       Vec3 vec31 = getPositionEyes(p_78087_7_, 0.0F);
/* 106 */       double d0 = vec3.field_72448_b - vec31.field_72448_b;
/*     */       
/* 108 */       if (d0 > 0.0D)
/*     */       {
/* 110 */         this.guardianEye.field_78797_d = 0.0F;
/*     */       }
/*     */       else
/*     */       {
/* 114 */         this.guardianEye.field_78797_d = 1.0F;
/*     */       }
/*     */       
/* 117 */       Vec3 vec32 = entityguardian.func_70676_i(0.0F);
/* 118 */       vec32 = Vec3.func_72443_a(vec32.field_72450_a, 0.0D, vec32.field_72449_c);
/* 119 */       Vec3 vec33 = Vec3.func_72443_a(vec31.field_72450_a - vec3.field_72450_a, 0.0D, vec31.field_72449_c - vec3.field_72449_c).func_72432_b();
/* 120 */       vec33.func_72442_b(1.5707964F);
/* 121 */       double d1 = vec32.func_72430_b(vec33);
/* 122 */       this.guardianEye.field_78800_c = (MathHelper.func_76129_c((float)Math.abs(d1)) * 2.0F * (float)Math.signum(d1));
/*     */     }
/*     */     
/* 125 */     this.guardianEye.field_78806_j = true;
/* 126 */     float f8 = entityguardian.func_175471_a(f6);
/* 127 */     this.guardianTail[0].field_78796_g = (MathHelper.func_76126_a(f8) * 3.1415927F * 0.05F);
/* 128 */     this.guardianTail[1].field_78796_g = (MathHelper.func_76126_a(f8) * 3.1415927F * 0.1F);
/* 129 */     this.guardianTail[1].field_78800_c = -1.5F;
/* 130 */     this.guardianTail[1].field_78797_d = 0.5F;
/* 131 */     this.guardianTail[1].field_78798_e = 14.0F;
/* 132 */     this.guardianTail[2].field_78796_g = (MathHelper.func_76126_a(f8) * 3.1415927F * 0.15F);
/* 133 */     this.guardianTail[2].field_78800_c = 0.5F;
/* 134 */     this.guardianTail[2].field_78797_d = 0.5F;
/* 135 */     this.guardianTail[2].field_78798_e = 6.0F;
/*     */   }
/*     */   
/*     */   private Vec3 getPositionEyes(Entity e, float p_174824_1_)
/*     */   {
/* 140 */     if (p_174824_1_ == 1.0F)
/*     */     {
/* 142 */       return Vec3.func_72443_a(e.field_70165_t, e.field_70163_u + e.func_70047_e(), e.field_70161_v);
/*     */     }
/*     */     
/*     */ 
/* 146 */     double d0 = e.field_70169_q + (e.field_70165_t - e.field_70169_q) * p_174824_1_;
/* 147 */     double d1 = e.field_70167_r + (e.field_70163_u - e.field_70167_r) * p_174824_1_ + e.func_70047_e();
/* 148 */     double d2 = e.field_70166_s + (e.field_70161_v - e.field_70166_s) * p_174824_1_;
/* 149 */     return Vec3.func_72443_a(d0, d1, d2);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/entities/ModelWatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */