/*     */ package thaumcraft.client.renderers.models;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ModelRendererTaintacle extends net.minecraft.client.model.ModelRenderer
/*     */ {
/*     */   private int textureOffsetX;
/*     */   private int textureOffsetY;
/*     */   private boolean compiled;
/*     */   private int displayList;
/*     */   private ModelBase baseModel;
/*     */   
/*     */   public ModelRendererTaintacle(ModelBase par1ModelBase)
/*     */   {
/*  19 */     super(par1ModelBase);
/*     */   }
/*     */   
/*     */   public ModelRendererTaintacle(ModelBase par1ModelBase, int par2, int par3)
/*     */   {
/*  24 */     this(par1ModelBase);
/*  25 */     func_78784_a(par2, par3);
/*     */   }
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
/*     */   @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*     */   public void render(float par1, float scale)
/*     */   {
/*  47 */     if (!this.field_78807_k)
/*     */     {
/*  49 */       if (this.field_78806_j)
/*     */       {
/*  51 */         if (!this.compiled)
/*     */         {
/*  53 */           compileDisplayList(par1);
/*     */         }
/*     */         
/*  56 */         GL11.glTranslatef(this.field_82906_o, this.field_82908_p, this.field_82907_q);
/*     */         
/*     */         int i;
/*  59 */         if ((this.field_78795_f == 0.0F) && (this.field_78796_g == 0.0F) && (this.field_78808_h == 0.0F))
/*     */         {
/*  61 */           if ((this.field_78800_c == 0.0F) && (this.field_78797_d == 0.0F) && (this.field_78798_e == 0.0F))
/*     */           {
/*  63 */             if (this.field_78805_m == null) {
/*  64 */               int j = 15728880;
/*  65 */               int k = j % 65536;
/*  66 */               int l = j / 65536;
/*  67 */               OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */             }
/*  69 */             GL11.glCallList(this.displayList);
/*     */             
/*  71 */             if (this.field_78805_m == null)
/*     */               break label554;
/*  73 */             for (i = 0; i < this.field_78805_m.size();)
/*     */             {
/*  75 */               GL11.glPushMatrix();
/*  76 */               GL11.glScalef(scale, scale, scale);
/*  77 */               ((ModelRendererTaintacle)this.field_78805_m.get(i)).render(par1, scale);
/*  78 */               GL11.glPopMatrix();i++; continue;
/*     */               
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  84 */               GL11.glTranslatef(this.field_78800_c * par1, this.field_78797_d * par1, this.field_78798_e * par1);
/*  85 */               if (this.field_78805_m == null) {
/*  86 */                 int j = 15728880;
/*  87 */                 int k = j % 65536;
/*  88 */                 int l = j / 65536;
/*  89 */                 OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */               }
/*  91 */               GL11.glCallList(this.displayList);
/*     */               
/*  93 */               if (this.field_78805_m != null)
/*     */               {
/*  95 */                 for (int i = 0; i < this.field_78805_m.size(); i++)
/*     */                 {
/*  97 */                   GL11.glPushMatrix();
/*  98 */                   GL11.glScalef(scale, scale, scale);
/*  99 */                   ((ModelRendererTaintacle)this.field_78805_m.get(i)).render(par1, scale);
/* 100 */                   GL11.glPopMatrix();
/*     */                 }
/*     */               }
/*     */               
/* 104 */               GL11.glTranslatef(-this.field_78800_c * par1, -this.field_78797_d * par1, -this.field_78798_e * par1); break;
/*     */               
/*     */ 
/*     */ 
/*     */ 
/* 109 */               GL11.glPushMatrix();
/* 110 */               GL11.glTranslatef(this.field_78800_c * par1, this.field_78797_d * par1, this.field_78798_e * par1);
/*     */               
/* 112 */               if (this.field_78808_h != 0.0F)
/*     */               {
/* 114 */                 GL11.glRotatef(this.field_78808_h * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */               }
/*     */               
/* 117 */               if (this.field_78796_g != 0.0F)
/*     */               {
/* 119 */                 GL11.glRotatef(this.field_78796_g * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */               }
/*     */               
/* 122 */               if (this.field_78795_f != 0.0F)
/*     */               {
/* 124 */                 GL11.glRotatef(this.field_78795_f * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */               }
/*     */               
/* 127 */               if (this.field_78805_m == null) {
/* 128 */                 int j = 15728880;
/* 129 */                 int k = j % 65536;
/* 130 */                 int l = j / 65536;
/* 131 */                 OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */               }
/* 133 */               GL11.glCallList(this.displayList);
/*     */               
/* 135 */               if (this.field_78805_m != null)
/*     */               {
/* 137 */                 for (int i = 0; i < this.field_78805_m.size(); i++)
/*     */                 {
/* 139 */                   GL11.glPushMatrix();
/* 140 */                   GL11.glScalef(scale, scale, scale);
/* 141 */                   ((ModelRendererTaintacle)this.field_78805_m.get(i)).render(par1, scale);
/* 142 */                   GL11.glPopMatrix();
/*     */                 }
/*     */               }
/*     */               
/* 146 */               GL11.glPopMatrix();
/*     */             } } }
/*     */         label554:
/* 149 */         GL11.glTranslatef(-this.field_82906_o, -this.field_82908_p, -this.field_82907_q);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*     */   private void compileDisplayList(float par1)
/*     */   {
/* 162 */     this.displayList = net.minecraft.client.renderer.GLAllocation.func_74526_a(1);
/* 163 */     GL11.glNewList(this.displayList, 4864);
/* 164 */     net.minecraft.client.renderer.Tessellator tessellator = net.minecraft.client.renderer.Tessellator.field_78398_a;
/*     */     
/* 166 */     for (int i = 0; i < this.field_78804_l.size(); i++)
/*     */     {
/* 168 */       ((net.minecraft.client.model.ModelBox)this.field_78804_l.get(i)).func_78245_a(tessellator, par1);
/*     */     }
/*     */     
/* 171 */     GL11.glEndList();
/* 172 */     this.compiled = true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelRendererTaintacle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */