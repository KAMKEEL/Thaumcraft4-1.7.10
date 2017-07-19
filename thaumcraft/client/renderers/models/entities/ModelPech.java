/*     */ package thaumcraft.client.renderers.models.entities;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
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
/*     */ public class ModelPech
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Body;
/*     */   ModelRenderer RightLeg;
/*     */   ModelRenderer LeftLeg;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Jowls;
/*     */   ModelRenderer LowerPack;
/*     */   ModelRenderer UpperPack;
/*     */   public ModelRenderer RightArm;
/*     */   ModelRenderer LeftArm;
/*     */   
/*     */   public ModelPech()
/*     */   {
/*  35 */     this.field_78090_t = 128;
/*  36 */     this.field_78089_u = 64;
/*     */     
/*  38 */     this.Body = new ModelRenderer(this, 34, 12);
/*  39 */     this.Body.func_78789_a(-3.0F, 0.0F, 0.0F, 6, 10, 6);
/*  40 */     this.Body.func_78793_a(0.0F, 9.0F, -3.0F);
/*  41 */     this.Body.func_78787_b(128, 64);
/*  42 */     this.Body.field_78809_i = true;
/*  43 */     setRotation(this.Body, 0.3129957F, 0.0F, 0.0F);
/*     */     
/*  45 */     this.RightLeg = new ModelRenderer(this, 35, 1);
/*  46 */     this.RightLeg.field_78809_i = true;
/*  47 */     this.RightLeg.func_78789_a(-2.9F, 0.0F, 0.0F, 3, 6, 3);
/*  48 */     this.RightLeg.func_78793_a(0.0F, 18.0F, 0.0F);
/*  49 */     this.RightLeg.func_78787_b(128, 64);
/*  50 */     this.RightLeg.field_78809_i = true;
/*  51 */     setRotation(this.RightLeg, 0.0F, 0.0F, 0.0F);
/*  52 */     this.RightLeg.field_78809_i = false;
/*     */     
/*  54 */     this.LeftLeg = new ModelRenderer(this, 35, 1);
/*  55 */     this.LeftLeg.func_78789_a(-0.1F, 0.0F, 0.0F, 3, 6, 3);
/*  56 */     this.LeftLeg.func_78793_a(0.0F, 18.0F, 0.0F);
/*  57 */     this.LeftLeg.func_78787_b(128, 64);
/*  58 */     this.LeftLeg.field_78809_i = true;
/*  59 */     setRotation(this.LeftLeg, 0.0F, 0.0F, 0.0F);
/*     */     
/*  61 */     this.Head = new ModelRenderer(this, 2, 11);
/*  62 */     this.Head.func_78789_a(-3.5F, -5.0F, -5.0F, 7, 5, 5);
/*  63 */     this.Head.func_78793_a(0.0F, 8.0F, 0.0F);
/*  64 */     this.Head.func_78787_b(128, 64);
/*  65 */     this.Head.field_78809_i = true;
/*  66 */     setRotation(this.Head, 0.0F, 0.0F, 0.0F);
/*     */     
/*  68 */     this.Jowls = new ModelRenderer(this, 1, 21);
/*  69 */     this.Jowls.func_78789_a(-4.0F, -1.0F, -6.0F, 8, 3, 5);
/*  70 */     this.Jowls.func_78793_a(0.0F, 8.0F, 0.0F);
/*  71 */     this.Jowls.func_78787_b(128, 64);
/*  72 */     this.Jowls.field_78809_i = true;
/*  73 */     setRotation(this.Jowls, 0.0F, 0.0F, 0.0F);
/*     */     
/*  75 */     this.LowerPack = new ModelRenderer(this, 0, 0);
/*  76 */     this.LowerPack.func_78789_a(-5.0F, 0.0F, 0.0F, 10, 5, 5);
/*  77 */     this.LowerPack.func_78793_a(0.0F, 10.0F, 3.5F);
/*  78 */     this.LowerPack.func_78787_b(128, 64);
/*  79 */     this.LowerPack.field_78809_i = true;
/*  80 */     setRotation(this.LowerPack, 0.3013602F, 0.0F, 0.0F);
/*     */     
/*  82 */     this.UpperPack = new ModelRenderer(this, 64, 1);
/*  83 */     this.UpperPack.func_78789_a(-7.5F, -14.0F, 0.0F, 15, 14, 11);
/*  84 */     this.UpperPack.func_78793_a(0.0F, 10.0F, 3.0F);
/*  85 */     this.UpperPack.func_78787_b(128, 64);
/*  86 */     this.UpperPack.field_78809_i = true;
/*  87 */     setRotation(this.UpperPack, 0.4537856F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*  90 */     this.RightArm = new ModelRenderer(this, 52, 2);
/*  91 */     this.RightArm.field_78809_i = true;
/*  92 */     this.RightArm.func_78789_a(-2.0F, 0.0F, -1.0F, 2, 6, 2);
/*  93 */     this.RightArm.func_78793_a(-3.0F, 10.0F, -1.0F);
/*  94 */     this.RightArm.func_78787_b(128, 64);
/*  95 */     this.RightArm.field_78809_i = true;
/*  96 */     setRotation(this.RightArm, 0.0F, 0.0F, 0.0F);
/*  97 */     this.RightArm.field_78809_i = false;
/*     */     
/*  99 */     this.LeftArm = new ModelRenderer(this, 52, 2);
/* 100 */     this.LeftArm.func_78789_a(0.0F, 0.0F, -1.0F, 2, 6, 2);
/* 101 */     this.LeftArm.func_78793_a(3.0F, 10.0F, -1.0F);
/* 102 */     this.LeftArm.func_78787_b(128, 64);
/* 103 */     this.LeftArm.field_78809_i = true;
/* 104 */     setRotation(this.LeftArm, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/* 109 */     func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
/* 110 */     this.Body.func_78785_a(par7);
/* 111 */     this.RightLeg.func_78785_a(par7);
/* 112 */     this.LeftLeg.func_78785_a(par7);
/* 113 */     this.Head.func_78785_a(par7);
/* 114 */     this.Jowls.func_78785_a(par7);
/* 115 */     this.LowerPack.func_78785_a(par7);
/* 116 */     this.UpperPack.func_78785_a(par7);
/* 117 */     this.RightArm.func_78785_a(par7);
/* 118 */     this.LeftArm.func_78785_a(par7);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 123 */     model.field_78795_f = x;
/* 124 */     model.field_78796_g = y;
/* 125 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*     */   {
/* 130 */     this.Head.field_78796_g = (par4 / 57.295776F);
/* 131 */     this.Head.field_78795_f = (par5 / 57.295776F);
/*     */     
/* 133 */     float mumble = 0.0F;
/* 134 */     if ((entity instanceof EntityPech)) {
/* 135 */       mumble = ((EntityPech)entity).mumble;
/*     */     }
/*     */     
/* 138 */     this.Jowls.field_78796_g = this.Head.field_78796_g;
/* 139 */     this.Jowls.field_78795_f = (this.Head.field_78795_f + (0.2617994F + MathHelper.func_76134_b(par1 * 0.6662F) * par2 * 0.25F) + 0.34906587F * Math.abs(MathHelper.func_76126_a(mumble)));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 144 */     this.RightArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
/* 145 */     this.LeftArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
/* 146 */     this.RightArm.field_78808_h = 0.0F;
/* 147 */     this.LeftArm.field_78808_h = 0.0F;
/* 148 */     this.RightLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
/* 149 */     this.LeftLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
/* 150 */     this.RightLeg.field_78796_g = 0.0F;
/* 151 */     this.LeftLeg.field_78796_g = 0.0F;
/*     */     
/* 153 */     this.LowerPack.field_78796_g = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.125F);
/* 154 */     this.LowerPack.field_78808_h = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.125F);
/*     */     
/* 156 */     if (this.field_78093_q)
/*     */     {
/* 158 */       this.RightArm.field_78795_f += -0.62831855F;
/* 159 */       this.LeftArm.field_78795_f += -0.62831855F;
/* 160 */       this.RightLeg.field_78795_f = -1.2566371F;
/* 161 */       this.LeftLeg.field_78795_f = -1.2566371F;
/* 162 */       this.RightLeg.field_78796_g = 0.31415927F;
/* 163 */       this.LeftLeg.field_78796_g = -0.31415927F;
/*     */     }
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
/* 176 */     this.RightArm.field_78796_g = 0.0F;
/* 177 */     this.LeftArm.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/* 181 */     if (this.field_78095_p > -9990.0F)
/*     */     {
/* 183 */       float f6 = this.field_78095_p;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 189 */       this.RightArm.field_78796_g += this.Body.field_78796_g;
/* 190 */       this.LeftArm.field_78796_g += this.Body.field_78796_g;
/* 191 */       this.LeftArm.field_78795_f += this.Body.field_78796_g;
/* 192 */       f6 = 1.0F - this.field_78095_p;
/* 193 */       f6 *= f6;
/* 194 */       f6 *= f6;
/* 195 */       f6 = 1.0F - f6;
/* 196 */       float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
/* 197 */       float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.Head.field_78795_f - 0.7F) * 0.75F;
/* 198 */       this.RightArm.field_78795_f = ((float)(this.RightArm.field_78795_f - (f7 * 1.2D + f8)));
/* 199 */       this.RightArm.field_78796_g += this.Body.field_78796_g * 2.0F;
/* 200 */       this.RightArm.field_78808_h = (MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F);
/*     */     }
/*     */     
/* 203 */     if (entity.func_70093_af())
/*     */     {
/*     */ 
/* 206 */       this.RightArm.field_78795_f += 0.4F;
/* 207 */       this.LeftArm.field_78795_f += 0.4F;
/*     */     }
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
/* 226 */     this.RightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 227 */     this.LeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 228 */     this.RightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 229 */     this.LeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/entities/ModelPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */