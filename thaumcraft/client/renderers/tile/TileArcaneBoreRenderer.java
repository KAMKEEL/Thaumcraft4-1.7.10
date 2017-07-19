/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.ModelBore;
/*     */ import thaumcraft.client.renderers.models.ModelBoreEmit;
/*     */ import thaumcraft.client.renderers.models.ModelJar;
/*     */ import thaumcraft.common.tiles.TileArcaneBore;
/*     */ 
/*     */ 
/*     */ public class TileArcaneBoreRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private ModelBoreEmit modelEmit;
/*     */   private ModelBore modelBore;
/*     */   private ModelJar modelJar;
/*     */   
/*     */   public TileArcaneBoreRenderer()
/*     */   {
/*  27 */     this.modelEmit = new ModelBoreEmit();
/*  28 */     this.modelBore = new ModelBore();
/*  29 */     this.modelJar = new ModelJar();
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
/*     */   public void renderEntityAt(TileArcaneBore bore, double x, double y, double z, float fq)
/*     */   {
/*  49 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*     */     
/*  51 */     UtilsFX.bindTexture("textures/models/Bore.png");
/*     */     
/*  53 */     GL11.glPushMatrix();
/*  54 */     GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
/*     */     
/*     */ 
/*  57 */     GL11.glRotatef(bore.rotX - bore.vRadX + fq * bore.speedX, 0.0F, 1.0F, 0.0F);
/*     */     
/*  59 */     GL11.glPushMatrix();
/*  60 */     if (bore.baseOrientation.ordinal() == 0) GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*  61 */     GL11.glTranslatef(0.0F, -0.5F, 0.0F);
/*  62 */     this.modelBore.renderBase();
/*  63 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/*  66 */     GL11.glRotatef(bore.rotZ - bore.vRadZ + fq * bore.speedZ, 0.0F, 0.0F, 1.0F);
/*     */     
/*  68 */     GL11.glPushMatrix();
/*  69 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  70 */     GL11.glTranslatef(0.0F, -0.5F, 0.0F);
/*  71 */     this.modelBore.renderNozzle();
/*  72 */     GL11.glPopMatrix();
/*     */     
/*  74 */     GL11.glPushMatrix();
/*  75 */     GL11.glRotatef(bore.topRotation, 0.0F, 1.0F, 0.0F);
/*  76 */     GL11.glTranslatef(0.0F, 0.5F, 0.0F);
/*  77 */     this.modelEmit.render(bore.hasFocus);
/*  78 */     GL11.glPopMatrix();
/*     */     
/*  80 */     GL11.glPushMatrix();
/*  81 */     float rotation = Minecraft.func_71410_x().field_71451_h.field_70173_aa % 45 + fq;
/*  82 */     GL11.glTranslatef(0.0F, -0.17F, 0.0F);
/*  83 */     GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  84 */     GL11.glRotatef(-(rotation * 8.0F), 0.0F, 0.0F, 1.0F);
/*  85 */     GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
/*     */     
/*  87 */     UtilsFX.renderQuadCenteredFromTexture("textures/misc/vortex.png", 0.4F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/*  88 */     GL11.glPopMatrix();
/*     */     
/*  90 */     GL11.glPushMatrix();
/*  91 */     rotation = Minecraft.func_71410_x().field_71451_h.field_70173_aa % 45 + fq;
/*  92 */     GL11.glTranslatef(0.0F, -0.21F, 0.0F);
/*  93 */     GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  94 */     GL11.glRotatef(rotation * 8.0F, 0.0F, 0.0F, 1.0F);
/*  95 */     GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
/*  96 */     UtilsFX.renderQuadCenteredFromTexture("textures/misc/vortex.png", 0.3F, 1.0F, 1.0F, 1.0F, 200, 771, 0.8F);
/*  97 */     GL11.glPopMatrix();
/*     */     
/*  99 */     GL11.glPushMatrix();
/* 100 */     rotation = Minecraft.func_71410_x().field_71451_h.field_70173_aa % 45 + fq;
/* 101 */     GL11.glTranslatef(0.0F, -0.25F, 0.0F);
/* 102 */     GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 103 */     GL11.glRotatef(-(rotation * 8.0F), 0.0F, 0.0F, 1.0F);
/* 104 */     GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
/* 105 */     UtilsFX.renderQuadCenteredFromTexture("textures/misc/vortex.png", 0.2F, 1.0F, 1.0F, 1.0F, 200, 771, 0.8F);
/* 106 */     GL11.glPopMatrix();
/*     */     
/* 108 */     GL11.glPushMatrix();
/* 109 */     UtilsFX.bindTexture("textures/models/jar.png");
/* 110 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 111 */     GL11.glTranslatef(0.0F, 0.3F, 0.0F);
/* 112 */     GL11.glScalef(0.6F, 0.6F, 0.6F);
/* 113 */     GL11.glDepthMask(false);
/* 114 */     GL11.glEnable(3042);
/* 115 */     GL11.glBlendFunc(770, 771);
/* 116 */     this.modelJar.Core.func_78785_a(0.0625F);
/* 117 */     GL11.glDisable(3042);
/* 118 */     GL11.glDepthMask(true);
/* 119 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 120 */     GL11.glPopMatrix();
/*     */     
/* 122 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f)
/*     */   {
/* 129 */     renderEntityAt((TileArcaneBore)tileentity, d, d1, d2, f);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileArcaneBoreRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */