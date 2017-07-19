/*     */ package thaumcraft.client.renderers.models.entities;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelGolemAccessories
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer golemHeadFez;
/*     */   public ModelRenderer golemHeadGlasses;
/*     */   public ModelRenderer golemHeadHat;
/*     */   public ModelRenderer golemHeadHatRim;
/*     */   public ModelRenderer golemBowtie;
/*     */   public ModelRenderer golemDartgun;
/*     */   public ModelRenderer golemMace;
/*     */   public ModelRenderer golemVisor;
/*     */   public ModelRenderer golemPlate;
/*     */   public ModelRenderer golemPlateLeft;
/*     */   public ModelRenderer golemPlateRight;
/*     */   public ModelRenderer golemHeadJar;
/*     */   public ModelRenderer golemHeadBrain;
/*     */   public ModelRenderer golemEvilHead;
/*     */   
/*     */   public ModelGolemAccessories()
/*     */   {
/*  33 */     this(0.0F);
/*     */   }
/*     */   
/*     */   public ModelGolemAccessories(float par1)
/*     */   {
/*  38 */     this(par1, -7.0F);
/*     */   }
/*     */   
/*     */   public ModelGolemAccessories(float par1, float par2)
/*     */   {
/*  43 */     short var3 = 128;
/*  44 */     short var4 = 128;
/*     */     
/*  46 */     this.golemHeadFez = new ModelRenderer(this).func_78787_b(var3, var4);
/*  47 */     this.golemHeadFez.func_78793_a(0.0F, 0.0F + par2, -2.0F);
/*  48 */     this.golemHeadFez.func_78784_a(0, 94).func_78790_a(-4.5F, -15.0F, -6.0F, 9, 7, 9, par1);
/*     */     
/*  50 */     this.golemPlate = new ModelRenderer(this).func_78787_b(var3, var4);
/*  51 */     this.golemPlate.func_78793_a(0.0F, 0.0F + par2, 0.0F);
/*  52 */     this.golemPlate.func_78784_a(32, 40).func_78790_a(-6.5F, -1.0F, -7.0F, 13, 12, 13, par1);
/*     */     
/*  54 */     this.golemPlateLeft = new ModelRenderer(this).func_78787_b(var3, var4);
/*  55 */     this.golemPlateLeft.func_78793_a(0.0F, 0.0F + par2, 0.0F);
/*  56 */     this.golemPlateLeft.func_78784_a(0, 44).func_78790_a(-8.5F, -4.0F, -6.5F, 3, 6, 12, par1);
/*     */     
/*  58 */     this.golemPlateRight = new ModelRenderer(this).func_78787_b(var3, var4);
/*  59 */     this.golemPlateRight.field_78809_i = true;
/*  60 */     this.golemPlateRight.func_78793_a(0.0F, 0.0F + par2, 0.0F);
/*  61 */     this.golemPlateRight.func_78784_a(0, 44).func_78790_a(5.5F, -4.0F, -6.5F, 3, 6, 12, par1);
/*     */     
/*  63 */     this.golemHeadHat = new ModelRenderer(this).func_78787_b(var3, var4);
/*  64 */     this.golemHeadHat.func_78793_a(0.0F, 0.0F + par2, -2.0F);
/*  65 */     this.golemHeadHat.func_78784_a(0, 110).func_78790_a(-4.5F, -17.0F, -6.0F, 9, 9, 9, par1);
/*     */     
/*  67 */     this.golemHeadGlasses = new ModelRenderer(this).func_78787_b(var3, var4);
/*  68 */     this.golemHeadGlasses.func_78793_a(0.0F, 0.0F + par2, -2.0F);
/*  69 */     this.golemHeadGlasses.func_78784_a(0, 80).func_78790_a(-4.5F, -8.0F, -6.0F, 9, 4, 9, par1);
/*     */     
/*  71 */     this.golemVisor = new ModelRenderer(this).func_78787_b(var3, var4);
/*  72 */     this.golemVisor.func_78793_a(0.0F, 0.0F + par2, -2.0F);
/*  73 */     this.golemVisor.func_78784_a(0, 70).func_78790_a(-5.0F, -8.0F, -6.0F, 10, 5, 5, par1);
/*     */     
/*  75 */     this.golemHeadHatRim = new ModelRenderer(this).func_78787_b(var3, var4);
/*  76 */     this.golemHeadHatRim.func_78793_a(0.0F, 0.0F + par2, -2.0F);
/*  77 */     this.golemHeadHatRim.func_78784_a(36, 114).func_78790_a(-6.5F, -9.0F, -8.0F, 13, 1, 13, 0.0F);
/*     */     
/*  79 */     this.golemDartgun = new ModelRenderer(this).func_78787_b(var3, var4);
/*  80 */     this.golemDartgun.func_78793_a(0.0F, 0.0F + par2, 0.0F);
/*  81 */     this.golemDartgun.func_78784_a(80, 80).func_78790_a(7.9F, 7.5F, -3.5F, 6, 16, 7, par1);
/*     */     
/*  83 */     this.golemMace = new ModelRenderer(this).func_78787_b(var3, var4);
/*  84 */     this.golemMace.func_78793_a(0.0F, 0.0F + par2, 0.0F);
/*  85 */     this.golemMace.func_78784_a(80, 26).func_78790_a(-13.0F, 15.0F, -5.0F, 6, 8, 10, par1);
/*     */     
/*  87 */     this.golemBowtie = new ModelRenderer(this).func_78787_b(var3, var4);
/*  88 */     this.golemBowtie.func_78793_a(0.0F, 0.0F + par2, 0.0F);
/*  89 */     this.golemBowtie.func_78784_a(0, 0).func_78790_a(-8.5F, -2.0F, -6.5F, 17, 4, 12, par1);
/*     */     
/*     */ 
/*  92 */     this.golemHeadJar = new ModelRenderer(this).func_78787_b(var3, var4);
/*  93 */     this.golemHeadJar.func_78793_a(0.0F, 0.0F + par2, -2.0F);
/*  94 */     this.golemHeadJar.func_78784_a(96, 56).func_78790_a(-4.0F, -15.0F, -5.5F, 8, 4, 8, par1);
/*     */     
/*  96 */     this.golemHeadBrain = new ModelRenderer(this).func_78787_b(var3, var4);
/*  97 */     this.golemHeadBrain.func_78793_a(0.0F, 0.0F + par2, -2.0F);
/*  98 */     this.golemHeadBrain.func_78784_a(96, 70).func_78790_a(-3.5F, -14.0F, -5.0F, 7, 3, 7, par1);
/*     */     
/* 100 */     this.golemEvilHead = new ModelRenderer(this).func_78787_b(var3, var4);
/* 101 */     this.golemEvilHead.func_78793_a(0.0F, 0.0F + par2, -2.0F);
/* 102 */     this.golemEvilHead.func_78784_a(64, 65).func_78790_a(-4.0F, -9.0F, -5.5F, 8, 7, 8, par1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/* 112 */     EntityGolemBase en = (EntityGolemBase)par1Entity;
/* 113 */     setRotationAngles(par2, par3, par4, par5, par6, par7, en);
/* 114 */     GL11.glPushMatrix();
/* 115 */     GL11.glScaled(0.4D, 0.4D, 0.4D);
/*     */     
/* 117 */     String deco = en.getGolemDecoration();
/*     */     
/* 119 */     if ((deco != null) && (deco.contains("R"))) {
/* 120 */       this.golemDartgun.func_78785_a(par7);
/*     */     }
/* 122 */     GL11.glPushMatrix();
/* 123 */     if ((deco != null) && (deco.contains("F"))) {
/* 124 */       if (en.advanced) {
/* 125 */         GL11.glTranslatef(0.0F, -0.01F, 0.0F);
/*     */       }
/* 127 */       this.golemHeadFez.func_78785_a(par7);
/*     */     }
/* 129 */     if ((deco != null) && (deco.contains("H"))) {
/* 130 */       if (en.advanced) {
/* 131 */         GL11.glTranslatef(0.0F, -0.01F, 0.0F);
/*     */       }
/* 133 */       this.golemHeadHat.func_78785_a(par7);
/* 134 */       this.golemHeadHatRim.func_78785_a(par7);
/*     */     }
/* 136 */     GL11.glPopMatrix();
/* 137 */     if ((deco != null) && (deco.contains("B"))) {
/* 138 */       GL11.glEnable(3042);
/* 139 */       GL11.glBlendFunc(770, 771);
/* 140 */       this.golemBowtie.func_78785_a(par7);
/* 141 */       GL11.glDisable(3042);
/*     */     }
/* 143 */     if ((deco != null) && (deco.contains("P"))) {
/* 144 */       this.golemPlate.func_78785_a(par7);
/* 145 */       this.golemPlateLeft.func_78785_a(par7);
/* 146 */       this.golemPlateRight.func_78785_a(par7);
/*     */     }
/* 148 */     if ((deco != null) && (deco.contains("G"))) {
/* 149 */       this.golemHeadGlasses.func_78785_a(par7);
/*     */     }
/* 151 */     if ((deco != null) && (deco.contains("V"))) {
/* 152 */       this.golemVisor.func_78785_a(par7);
/*     */     }
/* 154 */     if ((deco != null) && (deco.contains("M"))) {
/* 155 */       this.golemMace.func_78785_a(par7);
/*     */     }
/*     */     
/* 158 */     if (en.advanced) {
/* 159 */       this.golemHeadBrain.func_78785_a(par7);
/* 160 */       GL11.glPushMatrix();
/* 161 */       GL11.glEnable(3042);
/* 162 */       GL11.glBlendFunc(770, 771);
/* 163 */       this.golemHeadJar.func_78785_a(par7);
/* 164 */       GL11.glDisable(3042);
/* 165 */       GL11.glPopMatrix();
/* 166 */       if (en.getCore() >= 0) {
/* 167 */         GL11.glPushMatrix();
/* 168 */         GL11.glScaled(1.01D, 1.0D, 1.01D);
/* 169 */         this.golemEvilHead.func_78785_a(par7);
/* 170 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */     
/* 174 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, EntityGolemBase en)
/*     */   {
/* 182 */     if ((en.getCore() == -1) || (en.bootup < 0.0F)) {
/* 183 */       this.golemHeadFez.field_78796_g = 0.0F;
/* 184 */       this.golemHeadFez.field_78795_f = 0.57595867F;
/*     */ 
/*     */     }
/* 187 */     else if (en.inactive) {
/* 188 */       this.golemHeadFez.field_78796_g = 0.0F;
/* 189 */       this.golemHeadFez.field_78795_f = 0.57595867F;
/*     */     }
/* 191 */     else if (en.bootup > 0.0F) {
/* 192 */       this.golemHeadFez.field_78796_g = 0.0F;
/* 193 */       this.golemHeadFez.field_78795_f = (en.bootup / 57.295776F);
/*     */     } else {
/* 195 */       this.golemHeadFez.field_78796_g = (par4 / 57.295776F);
/* 196 */       this.golemHeadFez.field_78795_f = (par5 / 57.295776F);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 201 */     this.golemHeadGlasses.field_78796_g = this.golemHeadFez.field_78796_g;
/* 202 */     this.golemHeadGlasses.field_78795_f = this.golemHeadFez.field_78795_f;
/* 203 */     this.golemHeadJar.field_78796_g = this.golemHeadFez.field_78796_g;
/* 204 */     this.golemHeadJar.field_78795_f = this.golemHeadFez.field_78795_f;
/* 205 */     this.golemHeadBrain.field_78796_g = this.golemHeadFez.field_78796_g;
/* 206 */     this.golemHeadBrain.field_78795_f = this.golemHeadFez.field_78795_f;
/* 207 */     this.golemEvilHead.field_78796_g = this.golemHeadFez.field_78796_g;
/* 208 */     this.golemEvilHead.field_78795_f = this.golemHeadFez.field_78795_f;
/* 209 */     this.golemVisor.field_78796_g = this.golemHeadFez.field_78796_g;
/* 210 */     this.golemVisor.field_78795_f = this.golemHeadFez.field_78795_f;
/* 211 */     this.golemHeadHat.field_78796_g = this.golemHeadFez.field_78796_g;
/* 212 */     this.golemHeadHat.field_78795_f = this.golemHeadFez.field_78795_f;
/* 213 */     this.golemHeadHatRim.field_78796_g = this.golemHeadFez.field_78796_g;
/* 214 */     this.golemHeadHatRim.field_78795_f = this.golemHeadFez.field_78795_f;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
/*     */   {
/* 225 */     EntityGolemBase var5 = (EntityGolemBase)par1EntityLiving;
/* 226 */     int var6 = var5.getActionTimer();
/*     */     
/* 228 */     if (var6 > 0)
/*     */     {
/* 230 */       this.golemDartgun.field_78795_f = (-2.0F + 1.5F * func_78172_a(var6 - par4, 10.0F));
/* 231 */       this.golemMace.field_78795_f = (-2.0F + 1.5F * func_78172_a(var6 - par4, 10.0F));
/*     */     }
/* 233 */     else if ((var5.leftArm > 0) || (var5.rightArm > 0)) {
/* 234 */       if (var5.leftArm > 0)
/*     */       {
/* 236 */         this.golemDartgun.field_78795_f = (-2.0F + 1.5F * func_78172_a(var5.leftArm - par4, 10.0F));
/*     */       }
/*     */       
/* 239 */       if (var5.rightArm > 0)
/*     */       {
/* 241 */         this.golemMace.field_78795_f = (-2.0F + 1.5F * func_78172_a(var5.rightArm - par4, 10.0F));
/*     */       }
/*     */       
/*     */     }
/* 245 */     else if (var5.getCarriedForDisplay() != null)
/*     */     {
/* 247 */       this.golemDartgun.field_78795_f = -1.0F;
/* 248 */       this.golemMace.field_78795_f = -1.0F;
/*     */     }
/*     */     else
/*     */     {
/* 252 */       this.golemDartgun.field_78795_f = ((-0.2F - 1.5F * func_78172_a(par2, 13.0F)) * par3);
/* 253 */       this.golemMace.field_78795_f = ((-0.2F + 1.5F * func_78172_a(par2, 13.0F)) * par3);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private float func_78172_a(float par1, float par2)
/*     */   {
/* 260 */     return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/entities/ModelGolemAccessories.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */