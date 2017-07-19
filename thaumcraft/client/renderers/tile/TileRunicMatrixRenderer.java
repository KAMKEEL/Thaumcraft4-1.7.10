/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.ModelCube;
/*     */ import thaumcraft.common.tiles.TileInfusionMatrix;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileRunicMatrixRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  26 */   private ModelCube model = new ModelCube(0);
/*  27 */   private ModelCube model_over = new ModelCube(32);
/*  28 */   int type = 0;
/*     */   
/*  30 */   public TileRunicMatrixRenderer(int type) { this.type = type; }
/*     */   
/*     */   private void drawHalo(TileEntity is, double x, double y, double z, float par8, int count)
/*     */   {
/*  34 */     GL11.glPushMatrix();
/*  35 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*     */     
/*  37 */     int q = !FMLClientHandler.instance().getClient().field_71474_y.field_74347_j ? 10 : 20;
/*     */     
/*  39 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/*  41 */     RenderHelper.func_74518_a();
/*  42 */     float f1 = count / 500.0F;
/*  43 */     float f3 = 0.9F;
/*  44 */     float f2 = 0.0F;
/*     */     
/*  46 */     Random random = new Random(245L);
/*  47 */     GL11.glDisable(3553);
/*  48 */     GL11.glShadeModel(7425);
/*  49 */     GL11.glEnable(3042);
/*  50 */     GL11.glBlendFunc(770, 1);
/*  51 */     GL11.glDisable(3008);
/*  52 */     GL11.glEnable(2884);
/*  53 */     GL11.glDepthMask(false);
/*  54 */     GL11.glPushMatrix();
/*  55 */     for (int i = 0; i < q; i++)
/*     */     {
/*  57 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  58 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  59 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
/*  60 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  61 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  62 */       GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
/*  63 */       tessellator.func_78371_b(6);
/*  64 */       float fa = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
/*  65 */       float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
/*  66 */       fa /= 20.0F / (Math.min(count, 50) / 50.0F);
/*  67 */       f4 /= 20.0F / (Math.min(count, 50) / 50.0F);
/*  68 */       tessellator.func_78384_a(16777215, (int)(255.0F * (1.0F - f2)));
/*  69 */       tessellator.func_78377_a(0.0D, 0.0D, 0.0D);
/*  70 */       tessellator.func_78384_a(13369599, 0);
/*  71 */       tessellator.func_78377_a(-0.866D * f4, fa, -0.5F * f4);
/*  72 */       tessellator.func_78377_a(0.866D * f4, fa, -0.5F * f4);
/*  73 */       tessellator.func_78377_a(0.0D, fa, 1.0F * f4);
/*  74 */       tessellator.func_78377_a(-0.866D * f4, fa, -0.5F * f4);
/*  75 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*  78 */     GL11.glPopMatrix();
/*  79 */     GL11.glDepthMask(true);
/*  80 */     GL11.glDisable(2884);
/*  81 */     GL11.glDisable(3042);
/*  82 */     GL11.glShadeModel(7424);
/*  83 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  84 */     GL11.glEnable(3553);
/*  85 */     GL11.glEnable(3008);
/*  86 */     RenderHelper.func_74519_b();
/*  87 */     GL11.glBlendFunc(770, 771);
/*  88 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderInfusionMatrix(TileInfusionMatrix is, double par2, double par4, double par6, float par8)
/*     */   {
/*  97 */     GL11.glPushMatrix();
/*  98 */     UtilsFX.bindTexture("textures/models/infuser.png");
/*  99 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.5F, (float)par6 + 0.5F);
/* 100 */     float ticks = Minecraft.func_71410_x().field_71451_h.field_70173_aa + par8;
/*     */     
/*     */ 
/*     */ 
/* 104 */     if (is.func_145831_w() != null) {
/* 105 */       GL11.glRotatef(ticks % 360.0F * is.startUp, 0.0F, 1.0F, 0.0F);
/* 106 */       GL11.glRotatef(35.0F * is.startUp, 1.0F, 0.0F, 0.0F);
/* 107 */       GL11.glRotatef(45.0F * is.startUp, 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */     
/*     */ 
/* 111 */     float instability = Math.min(6.0F, 1.0F + is.instability * 0.66F * (Math.min(is.craftCount, 50) / 50.0F));
/*     */     
/* 113 */     float b1 = 0.0F;
/* 114 */     float b2 = 0.0F;
/* 115 */     float b3 = 0.0F;
/* 116 */     int aa = 0;
/* 117 */     int bb = 0;
/* 118 */     int cc = 0;
/* 119 */     for (int a = 0; a < 2; a++) {
/* 120 */       for (int b = 0; b < 2; b++) {
/* 121 */         for (int c = 0; c < 2; c++) {
/* 122 */           if (is.active) {
/* 123 */             b1 = MathHelper.func_76126_a((ticks + a * 10) / (15.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/* 124 */             b2 = MathHelper.func_76126_a((ticks + b * 10) / (14.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/* 125 */             b3 = MathHelper.func_76126_a((ticks + c * 10) / (13.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/*     */           }
/*     */           
/* 128 */           aa = a == 0 ? -1 : 1;
/* 129 */           bb = b == 0 ? -1 : 1;
/* 130 */           cc = c == 0 ? -1 : 1;
/*     */           
/* 132 */           GL11.glPushMatrix();
/* 133 */           GL11.glTranslatef(b1 + aa * 0.25F, b2 + bb * 0.25F, b3 + cc * 0.25F);
/* 134 */           if (a > 0) GL11.glRotatef(90.0F, a, 0.0F, 0.0F);
/* 135 */           if (b > 0) GL11.glRotatef(90.0F, 0.0F, b, 0.0F);
/* 136 */           if (c > 0) { GL11.glRotatef(90.0F, 0.0F, 0.0F, c);
/*     */           }
/* 138 */           GL11.glScaled(0.45D, 0.45D, 0.45D);
/*     */           
/* 140 */           this.model.render();
/*     */           
/*     */ 
/* 143 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/*     */     }
/* 147 */     if (is.active) {
/* 148 */       GL11.glPushMatrix();
/* 149 */       GL11.glAlphaFunc(516, 0.003921569F);
/* 150 */       GL11.glEnable(3042);
/* 151 */       GL11.glBlendFunc(770, 1);
/*     */       
/* 153 */       for (int a = 0; a < 2; a++) {
/* 154 */         for (int b = 0; b < 2; b++)
/* 155 */           for (int c = 0; c < 2; c++)
/*     */           {
/* 157 */             b1 = MathHelper.func_76126_a((ticks + a * 10) / (15.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/* 158 */             b2 = MathHelper.func_76126_a((ticks + b * 10) / (14.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/* 159 */             b3 = MathHelper.func_76126_a((ticks + c * 10) / (13.0F - instability / 2.0F)) * 0.01F * is.startUp * instability;
/*     */             
/* 161 */             aa = a == 0 ? -1 : 1;
/* 162 */             bb = b == 0 ? -1 : 1;
/* 163 */             cc = c == 0 ? -1 : 1;
/*     */             
/* 165 */             GL11.glPushMatrix();
/* 166 */             GL11.glTranslatef(b1 + aa * 0.25F, b2 + bb * 0.25F, b3 + cc * 0.25F);
/* 167 */             if (a > 0) GL11.glRotatef(90.0F, a, 0.0F, 0.0F);
/* 168 */             if (b > 0) GL11.glRotatef(90.0F, 0.0F, b, 0.0F);
/* 169 */             if (c > 0) { GL11.glRotatef(90.0F, 0.0F, 0.0F, c);
/*     */             }
/* 171 */             GL11.glScaled(0.45D, 0.45D, 0.45D);
/*     */             
/*     */ 
/* 174 */             int j = 15728880;
/* 175 */             int k = j % 65536;
/* 176 */             int l = j / 65536;
/* 177 */             OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/* 178 */             GL11.glColor4f(0.8F, 0.1F, 1.0F, (MathHelper.func_76126_a((ticks + a * 2 + b * 3 + c * 4) / 4.0F) * 0.1F + 0.2F) * is.startUp);
/* 179 */             this.model_over.render();
/*     */             
/*     */ 
/* 182 */             GL11.glPopMatrix();
/*     */           }
/*     */       }
/* 185 */       GL11.glDisable(3042);
/* 186 */       GL11.glAlphaFunc(516, 0.1F);
/* 187 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 190 */     GL11.glPopMatrix();
/*     */     
/* 192 */     if (is.crafting) {
/* 193 */       drawHalo(is, par2, par4, par6, par8, is.craftCount);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*     */   {
/* 199 */     switch (this.type) {
/* 200 */     case 0:  renderInfusionMatrix((TileInfusionMatrix)par1TileEntity, par2, par4, par6, par8); break;
/* 201 */     case 1:  func_147500_a((TileInfusionMatrix)par1TileEntity, par2, par4, par6, par8);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileRunicMatrixRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */