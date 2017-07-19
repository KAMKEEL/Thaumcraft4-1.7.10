/*     */ package thaumcraft.client.renderers.models.entities;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ 
/*     */ public class ModelGolem
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer golemHead;
/*     */   public ModelRenderer golemBody;
/*     */   public ModelRenderer golemRightArm;
/*     */   public ModelRenderer golemLeftArm;
/*     */   public ModelRenderer golemRightLeg;
/*     */   public ModelRenderer golemLeftLeg;
/*     */   
/*     */   public ModelGolem(boolean p)
/*     */   {
/*  24 */     float f1 = 0.0F;
/*  25 */     float f2 = p ? -5.0F : 30.0F;
/*  26 */     short var3 = 128;
/*  27 */     short var4 = 128;
/*  28 */     this.golemHead = new ModelRenderer(this).func_78787_b(var3, var4);
/*  29 */     this.golemHead.func_78793_a(0.0F, 0.0F + f2, -2.0F);
/*  30 */     this.golemHead.func_78784_a(0, 0).func_78790_a(-4.0F, -11.0F, -5.5F, 8, 9, 8, f1);
/*     */     
/*  32 */     this.golemBody = new ModelRenderer(this).func_78787_b(var3, var4);
/*  33 */     this.golemBody.func_78793_a(0.0F, 0.0F + f2, 0.0F);
/*  34 */     this.golemBody.func_78784_a(0, 40).func_78790_a(-8.0F, -2.0F, -6.0F, 16, 12, 11, f1);
/*  35 */     this.golemBody.func_78784_a(0, 70).func_78790_a(-4.5F, 10.0F, -3.0F, 9, 5, 6, f1 + 0.5F);
/*     */     
/*  37 */     this.golemRightArm = new ModelRenderer(this).func_78787_b(var3, var4);
/*  38 */     this.golemRightArm.func_78793_a(0.0F, 0.0F + f2, 0.0F);
/*  39 */     this.golemRightArm.func_78784_a(60, 21).func_78790_a(-12.0F, -2.5F, -3.0F, 4, 25, 6, f1);
/*  40 */     this.golemLeftArm = new ModelRenderer(this).func_78787_b(var3, var4);
/*  41 */     this.golemLeftArm.field_78809_i = true;
/*  42 */     this.golemLeftArm.func_78793_a(0.0F, 0.0F + f2, 0.0F);
/*  43 */     this.golemLeftArm.func_78784_a(60, 21).func_78790_a(8.0F, -2.5F, -3.0F, 4, 25, 6, f1);
/*     */     
/*  45 */     this.golemRightLeg = new ModelRenderer(this, 0, 22).func_78787_b(var3, var4);
/*  46 */     this.golemRightLeg.func_78793_a(-4.0F, 18.0F + f2, 0.0F);
/*  47 */     this.golemRightLeg.func_78784_a(37, 0).func_78790_a(-3.5F, -3.0F, -3.0F, 6, 16, 5, f1);
/*  48 */     this.golemLeftLeg = new ModelRenderer(this, 0, 22).func_78787_b(var3, var4);
/*  49 */     this.golemLeftLeg.field_78809_i = true;
/*  50 */     this.golemLeftLeg.func_78784_a(37, 0).func_78793_a(5.0F, 18.0F + f2, 0.0F);
/*  51 */     this.golemLeftLeg.func_78790_a(-3.5F, -3.0F, -3.0F, 6, 16, 5, f1);
/*     */   }
/*     */   
/*  54 */   public int pass = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78088_a(Entity e, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  61 */     setRotationAngles(e, par2, par3, par4, par5, par6, par7);
/*     */     
/*  63 */     GL11.glPushMatrix();
/*  64 */     if (this.pass == 2) {
/*  65 */       GL11.glEnable(3042);
/*  66 */       GL11.glBlendFunc(770, 771);
/*  67 */       GL11.glAlphaFunc(516, 0.003921569F);
/*     */     }
/*     */     
/*  70 */     GL11.glScaled(0.4D, 0.4D, 0.4D);
/*  71 */     this.golemHead.func_78785_a(par7);
/*     */     
/*  73 */     this.golemBody.func_78785_a(par7);
/*  74 */     this.golemRightLeg.func_78785_a(par7);
/*  75 */     this.golemLeftLeg.func_78785_a(par7);
/*  76 */     this.golemRightArm.func_78785_a(par7);
/*  77 */     this.golemLeftArm.func_78785_a(par7);
/*  78 */     GL11.glScaled(1.0D, 1.0D, 1.0D);
/*  79 */     if (this.pass == 2) {
/*  80 */       GL11.glAlphaFunc(516, 0.1F);
/*  81 */       GL11.glDisable(3042);
/*     */     }
/*  83 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRotationAngles(Entity en, float par1, float par2, float par3, float par4, float par5, float par6)
/*     */   {
/*  93 */     float bu = 0.0F;
/*  94 */     int core = 0;
/*  95 */     boolean inactive = false;
/*  96 */     if ((en instanceof EntityGolemBase)) {
/*  97 */       core = ((EntityGolemBase)en).getCore();
/*  98 */       bu = ((EntityGolemBase)en).bootup;
/*  99 */       inactive = ((EntityGolemBase)en).inactive;
/*     */       
/*     */ 
/*     */ 
/* 103 */       if ((this.pass == 0) && (((EntityGolemBase)en).healing > 0)) {
/* 104 */         float h1 = ((EntityGolemBase)en).healing / 10.0F;
/* 105 */         float h2 = ((EntityGolemBase)en).healing / 5.0F;
/* 106 */         GL11.glColor3f(0.5F + h1, 0.9F + h2, 0.5F + h1);
/*     */       }
/*     */     }
/*     */     
/* 110 */     if ((core == -1) || (bu < 0.0F)) {
/* 111 */       this.golemHead.field_78796_g = 0.0F;
/* 112 */       this.golemHead.field_78795_f = 0.57595867F;
/* 113 */       this.golemRightLeg.field_78795_f = 0.0F;
/* 114 */       this.golemLeftLeg.field_78795_f = 0.0F;
/* 115 */       this.golemRightArm.field_78795_f = 0.0F;
/* 116 */       this.golemLeftArm.field_78795_f = 0.0F;
/* 117 */       this.golemRightLeg.field_78796_g = 0.0F;
/* 118 */       this.golemLeftLeg.field_78796_g = 0.0F;
/* 119 */       this.golemLeftArm.field_78808_h = 0.0F;
/* 120 */       this.golemRightArm.field_78808_h = 0.0F;
/*     */     } else {
/* 122 */       if (inactive) {
/* 123 */         this.golemHead.field_78796_g = 0.0F;
/* 124 */         this.golemHead.field_78795_f = 0.57595867F;
/*     */       }
/* 126 */       else if (bu > 0.0F) {
/* 127 */         this.golemHead.field_78796_g = 0.0F;
/* 128 */         this.golemHead.field_78795_f = (bu / 57.295776F);
/*     */       } else {
/* 130 */         this.golemHead.field_78796_g = (par4 / 57.295776F);
/* 131 */         this.golemHead.field_78795_f = (par5 / 57.295776F);
/*     */       }
/*     */       
/* 134 */       this.golemRightLeg.field_78795_f = (-1.5F * func_78172_a(par1, 13.0F) * par2);
/* 135 */       this.golemLeftLeg.field_78795_f = (1.5F * func_78172_a(par1, 13.0F) * par2);
/* 136 */       this.golemRightLeg.field_78796_g = 0.0F;
/* 137 */       this.golemLeftLeg.field_78796_g = 0.0F;
/* 138 */       this.golemLeftArm.field_78808_h = 0.0F;
/* 139 */       this.golemRightArm.field_78808_h = 0.0F;
/*     */       
/* 141 */       if (core == 6) {
/* 142 */         float s = (1.0F - (0.5F + Math.min(64, ((EntityGolemBase)en).getCarryLimit()) / 128.0F)) * 25.0F;
/* 143 */         this.golemLeftArm.field_78808_h = (s / 57.295776F);
/* 144 */         this.golemRightArm.field_78808_h = (-s / 57.295776F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
/*     */   {
/* 156 */     EntityGolemBase var5 = null;
/* 157 */     int var6 = 0;
/* 158 */     ItemStack carried = null;
/* 159 */     boolean bucket = false;
/* 160 */     int leftarm = 0;
/* 161 */     int rightarm = 0;
/* 162 */     if ((par1EntityLiving instanceof EntityGolemBase)) {
/* 163 */       var5 = (EntityGolemBase)par1EntityLiving;
/* 164 */       var6 = var5.getActionTimer();
/* 165 */       carried = var5.getCarriedForDisplay();
/* 166 */       bucket = var5.getCore() == 5;
/* 167 */       leftarm = var5.leftArm;
/* 168 */       rightarm = var5.rightArm;
/*     */     }
/*     */     
/* 171 */     if (var6 > 0)
/*     */     {
/* 173 */       this.golemRightArm.field_78795_f = (-2.0F + 1.5F * func_78172_a(var6 - par4, 5.0F));
/* 174 */       this.golemLeftArm.field_78795_f = (-2.0F + 1.5F * func_78172_a(var6 - par4, 5.0F));
/*     */ 
/*     */     }
/* 177 */     else if ((leftarm > 0) || (rightarm > 0)) {
/* 178 */       if (leftarm > 0)
/*     */       {
/* 180 */         this.golemLeftArm.field_78795_f = (-2.0F + 1.5F * func_78172_a(leftarm - par4, 20.0F));
/*     */       }
/*     */       
/* 183 */       if (rightarm > 0)
/*     */       {
/* 185 */         this.golemRightArm.field_78795_f = (-2.0F + 1.5F * func_78172_a(rightarm - par4, 20.0F));
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 190 */     else if ((carried != null) || (bucket))
/*     */     {
/* 192 */       this.golemRightArm.field_78795_f = -1.0F;
/* 193 */       this.golemLeftArm.field_78795_f = -1.0F;
/*     */     }
/*     */     else
/*     */     {
/* 197 */       this.golemRightArm.field_78795_f = ((-0.2F + 1.5F * func_78172_a(par2, 13.0F)) * par3);
/* 198 */       this.golemLeftArm.field_78795_f = ((-0.2F - 1.5F * func_78172_a(par2, 13.0F)) * par3);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private float func_78172_a(float par1, float par2)
/*     */   {
/* 205 */     return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/entities/ModelGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */