/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.ModelCrystal;
/*     */ import thaumcraft.common.tiles.TileCrystal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileCrystalRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private ModelCrystal model;
/*     */   
/*     */   public TileCrystalRenderer()
/*     */   {
/*  30 */     this.model = new ModelCrystal();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void translateFromOrientation(float x, float y, float z, int orientation)
/*     */   {
/*  37 */     if (orientation == 0)
/*     */     {
/*  39 */       GL11.glTranslatef(x + 0.5F, y + 1.3F, z + 0.5F);
/*  40 */       GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*  41 */     } else if (orientation == 1)
/*     */     {
/*  43 */       GL11.glTranslatef(x + 0.5F, y - 0.3F, z + 0.5F);
/*  44 */     } else if (orientation == 2)
/*     */     {
/*  46 */       GL11.glTranslatef(x + 0.5F, y + 0.5F, z + 1.3F);
/*  47 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  48 */     } else if (orientation == 3)
/*     */     {
/*  50 */       GL11.glTranslatef(x + 0.5F, y + 0.5F, z - 0.3F);
/*  51 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  52 */     } else if (orientation == 4)
/*     */     {
/*  54 */       GL11.glTranslatef(x + 1.3F, y + 0.5F, z + 0.5F);
/*  55 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  56 */     } else if (orientation == 5)
/*     */     {
/*  58 */       GL11.glTranslatef(x - 0.3F, y + 0.5F, z + 0.5F);
/*  59 */       GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void drawCrystal(int ori, float x, float y, float z, float a1, float a2, Random rand, int color, float size)
/*     */   {
/*  66 */     EntityPlayer p = Minecraft.func_71410_x().field_71439_g;
/*  67 */     float shade = MathHelper.func_76126_a((p.field_70173_aa + rand.nextInt(10)) / (5.0F + rand.nextFloat())) * 0.075F + 0.925F;
/*     */     
/*  69 */     Color c = new Color(color);
/*  70 */     float r = c.getRed() / 220.0F;
/*  71 */     float g = c.getGreen() / 220.0F;
/*  72 */     float b = c.getBlue() / 220.0F;
/*     */     
/*  74 */     GL11.glPushMatrix();
/*  75 */     GL11.glEnable(2977);
/*  76 */     GL11.glEnable(3042);
/*  77 */     GL11.glEnable(32826);
/*  78 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  80 */     translateFromOrientation(x, y, z, ori);
/*  81 */     GL11.glRotatef(a1, 0.0F, 1.0F, 0.0F);
/*  82 */     GL11.glRotatef(a2, 1.0F, 0.0F, 0.0F);
/*     */     
/*  84 */     GL11.glScalef((0.15F + rand.nextFloat() * 0.075F) * size, (0.5F + rand.nextFloat() * 0.1F) * size, (0.15F + rand.nextFloat() * 0.05F) * size);
/*     */     
/*  86 */     int var19 = (int)(210.0F * shade);
/*  87 */     int var20 = var19 % 65536;
/*  88 */     int var21 = var19 / 65536;
/*  89 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, var20 / 1.0F, var21 / 1.0F);
/*  90 */     GL11.glColor4f(r, g, b, 1.0F);
/*     */     
/*  92 */     this.model.render();
/*  93 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/*     */     
/*  95 */     GL11.glDisable(32826);
/*  96 */     GL11.glDisable(3042);
/*  97 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  98 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_147500_a(TileEntity te, double x, double y, double z, float f)
/*     */   {
/* 106 */     GL11.glPushMatrix();
/* 107 */     TileCrystal tco = (TileCrystal)te;
/* 108 */     int md = tco.func_145832_p();
/* 109 */     int color = thaumcraft.common.blocks.BlockCustomOreItem.colors[5];
/* 110 */     if (md != 6) {
/* 111 */       color = thaumcraft.common.blocks.BlockCustomOreItem.colors[(md + 1)];
/*     */     }
/*     */     
/* 114 */     UtilsFX.bindTexture("textures/models/crystal.png");
/* 115 */     Random rand = new Random(tco.func_145832_p() + tco.field_145851_c + tco.field_145848_d * tco.field_145849_e);
/* 116 */     drawCrystal(tco.orientation, (float)x, (float)y, (float)z, (rand.nextFloat() - rand.nextFloat()) * 5.0F, (rand.nextFloat() - rand.nextFloat()) * 5.0F, rand, color, 1.1F);
/*     */     
/*     */ 
/*     */ 
/* 120 */     for (int a = 1; a < 6; a++) {
/* 121 */       if (md == 6) color = thaumcraft.common.blocks.BlockCustomOreItem.colors[a];
/* 122 */       int angle1 = rand.nextInt(36) + 72 * a;
/* 123 */       int angle2 = 15 + rand.nextInt(15);
/* 124 */       drawCrystal(tco.orientation, (float)x, (float)y, (float)z, angle1, angle2, rand, color, 0.8F);
/*     */     }
/* 126 */     GL11.glPopMatrix();
/* 127 */     GL11.glDisable(3042);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileCrystalRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */