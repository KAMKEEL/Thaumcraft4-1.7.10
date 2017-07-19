/*     */ package thaumcraft.client.renderers.models.entities;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import thaumcraft.common.entities.monster.EntityFireBat;
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
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelFireBat
/*     */   extends ModelBase
/*     */ {
/*     */   private ModelRenderer batHead;
/*     */   private ModelRenderer batBody;
/*     */   private ModelRenderer batRightWing;
/*     */   private ModelRenderer batLeftWing;
/*     */   private ModelRenderer batOuterRightWing;
/*     */   private ModelRenderer batOuterLeftWing;
/*     */   
/*     */   public ModelFireBat()
/*     */   {
/*  33 */     this.field_78090_t = 64;
/*  34 */     this.field_78089_u = 64;
/*  35 */     this.batHead = new ModelRenderer(this, 0, 0);
/*  36 */     this.batHead.func_78789_a(-3.0F, -3.0F, -3.0F, 6, 6, 6);
/*  37 */     ModelRenderer var1 = new ModelRenderer(this, 24, 0);
/*  38 */     var1.func_78789_a(-4.0F, -6.0F, -2.0F, 3, 4, 1);
/*  39 */     this.batHead.func_78792_a(var1);
/*  40 */     ModelRenderer var2 = new ModelRenderer(this, 24, 0);
/*  41 */     var2.field_78809_i = true;
/*  42 */     var2.func_78789_a(1.0F, -6.0F, -2.0F, 3, 4, 1);
/*  43 */     this.batHead.func_78792_a(var2);
/*  44 */     this.batBody = new ModelRenderer(this, 0, 16);
/*  45 */     this.batBody.func_78789_a(-3.0F, 4.0F, -3.0F, 6, 12, 6);
/*  46 */     this.batBody.func_78784_a(0, 34).func_78789_a(-5.0F, 16.0F, 0.0F, 10, 6, 1);
/*  47 */     this.batRightWing = new ModelRenderer(this, 42, 0);
/*  48 */     this.batRightWing.func_78789_a(-12.0F, 1.0F, 1.5F, 10, 16, 1);
/*  49 */     this.batOuterRightWing = new ModelRenderer(this, 24, 16);
/*  50 */     this.batOuterRightWing.func_78793_a(-12.0F, 1.0F, 1.5F);
/*  51 */     this.batOuterRightWing.func_78789_a(-8.0F, 1.0F, 0.0F, 8, 12, 1);
/*  52 */     this.batLeftWing = new ModelRenderer(this, 42, 0);
/*  53 */     this.batLeftWing.field_78809_i = true;
/*  54 */     this.batLeftWing.func_78789_a(2.0F, 1.0F, 1.5F, 10, 16, 1);
/*  55 */     this.batOuterLeftWing = new ModelRenderer(this, 24, 16);
/*  56 */     this.batOuterLeftWing.field_78809_i = true;
/*  57 */     this.batOuterLeftWing.func_78793_a(12.0F, 1.0F, 1.5F);
/*  58 */     this.batOuterLeftWing.func_78789_a(0.0F, 1.0F, 0.0F, 8, 12, 1);
/*  59 */     this.batBody.func_78792_a(this.batRightWing);
/*  60 */     this.batBody.func_78792_a(this.batLeftWing);
/*  61 */     this.batRightWing.func_78792_a(this.batOuterRightWing);
/*  62 */     this.batLeftWing.func_78792_a(this.batOuterLeftWing);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getBatSize()
/*     */   {
/*  71 */     return 36;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  79 */     EntityFireBat var8 = (EntityFireBat)par1Entity;
/*     */     
/*  81 */     if (var8.getIsBatHanging())
/*     */     {
/*  83 */       this.batHead.field_78795_f = (par6 / 57.295776F);
/*  84 */       this.batHead.field_78796_g = (3.1415927F - par5 / 57.295776F);
/*  85 */       this.batHead.field_78808_h = 3.1415927F;
/*  86 */       this.batHead.func_78793_a(0.0F, -2.0F, 0.0F);
/*  87 */       this.batRightWing.func_78793_a(-3.0F, 0.0F, 3.0F);
/*  88 */       this.batLeftWing.func_78793_a(3.0F, 0.0F, 3.0F);
/*  89 */       this.batBody.field_78795_f = 3.1415927F;
/*  90 */       this.batRightWing.field_78795_f = -0.15707964F;
/*  91 */       this.batRightWing.field_78796_g = -1.2566371F;
/*  92 */       this.batOuterRightWing.field_78796_g = -1.7278761F;
/*  93 */       this.batLeftWing.field_78795_f = this.batRightWing.field_78795_f;
/*  94 */       this.batLeftWing.field_78796_g = (-this.batRightWing.field_78796_g);
/*  95 */       this.batOuterLeftWing.field_78796_g = (-this.batOuterRightWing.field_78796_g);
/*     */     }
/*     */     else
/*     */     {
/*  99 */       this.batHead.field_78795_f = (par6 / 57.295776F);
/* 100 */       this.batHead.field_78796_g = (par5 / 57.295776F);
/* 101 */       this.batHead.field_78808_h = 0.0F;
/* 102 */       this.batHead.func_78793_a(0.0F, 0.0F, 0.0F);
/* 103 */       this.batRightWing.func_78793_a(0.0F, 0.0F, 0.0F);
/* 104 */       this.batLeftWing.func_78793_a(0.0F, 0.0F, 0.0F);
/* 105 */       this.batBody.field_78795_f = (0.7853982F + MathHelper.func_76134_b(par4 * 0.1F) * 0.15F);
/* 106 */       this.batBody.field_78796_g = 0.0F;
/* 107 */       this.batRightWing.field_78796_g = (MathHelper.func_76134_b(par4 * 1.3F) * 3.1415927F * 0.25F);
/* 108 */       this.batLeftWing.field_78796_g = (-this.batRightWing.field_78796_g);
/* 109 */       this.batOuterRightWing.field_78796_g = (this.batRightWing.field_78796_g * 0.5F);
/* 110 */       this.batOuterLeftWing.field_78796_g = (-this.batRightWing.field_78796_g * 0.5F);
/*     */     }
/*     */     
/* 113 */     this.batHead.func_78785_a(par7);
/* 114 */     this.batBody.func_78785_a(par7);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/entities/ModelFireBat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */