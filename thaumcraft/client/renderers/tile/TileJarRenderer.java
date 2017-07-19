/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.ModelBrain;
/*     */ import thaumcraft.client.renderers.models.ModelJar;
/*     */ import thaumcraft.common.blocks.BlockJar;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileJar;
/*     */ import thaumcraft.common.tiles.TileJarBrain;
/*     */ import thaumcraft.common.tiles.TileJarFillable;
/*     */ import thaumcraft.common.tiles.TileJarNode;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileJarRenderer extends net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
/*     */ {
/*  32 */   private ModelJar model = new ModelJar();
/*  33 */   private ModelBrain brain = new ModelBrain();
/*     */   
/*  35 */   private TileNodeRenderer tnr = new TileNodeRenderer();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderTileEntityAt(TileJar tile, double x, double y, double z, float f)
/*     */   {
/*  43 */     if ((tile instanceof TileJarNode)) {
/*  44 */       GL11.glPushMatrix();
/*  45 */       GL11.glTranslatef(0.0F, -0.1F, 0.0F);
/*  46 */       this.tnr.func_147500_a(tile, x, y, z, f);
/*  47 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*  50 */     GL11.glPushMatrix();
/*  51 */     GL11.glDisable(2884);
/*     */     
/*  53 */     GL11.glTranslatef((float)x + 0.5F, (float)y + 0.01F, (float)z + 0.5F);
/*  54 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */     
/*  56 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  58 */     if ((tile instanceof TileJarBrain)) {
/*  59 */       renderBrain((TileJarBrain)tile, x, y, z, f);
/*     */     }
/*  61 */     else if ((tile instanceof TileJarFillable)) {
/*  62 */       if (((TileJarFillable)tile).amount > 0)
/*  63 */         renderLiquid((TileJarFillable)tile, x, y, z, f);
/*  64 */       if (((TileJarFillable)tile).aspectFilter != null) {
/*  65 */         GL11.glPushMatrix();
/*     */         
/*  67 */         switch (((TileJarFillable)tile).facing) {
/*  68 */         case 3:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/*  69 */         case 5:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/*  70 */         case 4:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
/*     */         }
/*     */         
/*  73 */         float rot = (((TileJarFillable)tile).aspectFilter.getTag().hashCode() + tile.field_145851_c + ((TileJarFillable)tile).facing) % 4 - 2;
/*     */         
/*  75 */         GL11.glPushMatrix();
/*  76 */         GL11.glTranslatef(0.0F, -0.4F, 0.315F);
/*  77 */         if (Config.crooked) GL11.glRotatef(rot, 0.0F, 0.0F, 1.0F);
/*  78 */         UtilsFX.renderQuadCenteredFromTexture("textures/models/label.png", 0.5F, 1.0F, 1.0F, 1.0F, -99, 771, 1.0F);
/*  79 */         GL11.glPopMatrix();
/*     */         
/*  81 */         GL11.glPushMatrix();
/*  82 */         GL11.glTranslatef(0.0F, -0.4F, 0.316F);
/*  83 */         if (Config.crooked) GL11.glRotatef(rot, 0.0F, 0.0F, 1.0F);
/*  84 */         GL11.glScaled(0.021D, 0.021D, 0.021D);
/*  85 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */         
/*     */ 
/*  88 */         UtilsFX.drawTag(-8, -8, ((TileJarFillable)tile).aspectFilter);
/*  89 */         GL11.glPopMatrix();
/*     */         
/*  91 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */     
/*  95 */     func_147499_a(tile.getTexture());
/*     */     
/*  97 */     if (((tile instanceof TileJarNode)) && (((TileJarNode)tile).animate > 0L)) {
/*  98 */       long time = System.currentTimeMillis();
/*  99 */       if (((TileJarNode)tile).animate > time) {
/* 100 */         float size = 1.0F + 2.0F * ((float)(((TileJarNode)tile).animate - time) / 1000.0F);
/* 101 */         GL11.glScalef(size, size, size);
/*     */       } else {
/* 103 */         ((TileJarNode)tile).animate = 0L;
/*     */       }
/*     */     }
/*     */     
/* 107 */     GL11.glEnable(2884);
/* 108 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void renderLiquid(TileJarFillable te, double x, double y, double z, float f)
/*     */   {
/* 113 */     if (this.field_147501_a.field_147553_e == null) { return;
/*     */     }
/* 115 */     GL11.glPushMatrix();
/* 116 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */     
/* 118 */     World world = te.func_145831_w();
/* 119 */     RenderBlocks renderBlocks = new RenderBlocks();
/*     */     
/* 121 */     GL11.glDisable(2896);
/*     */     
/* 123 */     float level = te.amount / te.maxAmount * 0.625F;
/*     */     
/* 125 */     Tessellator t = Tessellator.field_78398_a;
/*     */     
/* 127 */     renderBlocks.func_147782_a(0.25D, 0.0625D, 0.25D, 0.75D, 0.0625D + level, 0.75D);
/*     */     
/* 129 */     t.func_78382_b();
/*     */     
/* 131 */     if (te.aspect != null) {
/* 132 */       t.func_78378_d(te.aspect.getColor());
/*     */     }
/* 134 */     int bright = 200;
/* 135 */     if (te.func_145831_w() != null) {
/* 136 */       bright = Math.max(200, ConfigBlocks.blockJar.func_149677_c(te.func_145831_w(), te.field_145851_c, te.field_145848_d, te.field_145849_e));
/*     */     }
/*     */     
/* 139 */     t.func_78380_c(bright);
/*     */     
/* 141 */     IIcon icon = ((BlockJar)ConfigBlocks.blockJar).iconLiquid;
/*     */     
/* 143 */     this.field_147501_a.field_147553_e.func_110577_a(TextureMap.field_110575_b);
/*     */     
/* 145 */     renderBlocks.func_147768_a(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, icon);
/* 146 */     renderBlocks.func_147806_b(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, icon);
/* 147 */     renderBlocks.func_147761_c(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, icon);
/* 148 */     renderBlocks.func_147734_d(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, icon);
/* 149 */     renderBlocks.func_147798_e(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, icon);
/* 150 */     renderBlocks.func_147764_f(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, icon);
/*     */     
/* 152 */     t.func_78381_a();
/*     */     
/*     */ 
/* 155 */     GL11.glEnable(2896);
/* 156 */     GL11.glPopMatrix();
/*     */     
/* 158 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderBrain(TileJarBrain te, double x, double y, double z, float f)
/*     */   {
/* 165 */     float bob = MathHelper.func_76126_a(Minecraft.func_71410_x().field_71439_g.field_70173_aa / 14.0F) * 0.03F + 0.03F;
/* 166 */     GL11.glPushMatrix();
/* 167 */     GL11.glTranslatef(0.0F, -0.8F + bob, 0.0F);
/*     */     
/* 169 */     for (float f2 = te.rota - te.rotb; f2 >= 3.141593F; f2 -= 6.283185F) {}
/* 170 */     while (f2 < -3.141593F) f2 += 6.283185F;
/* 171 */     float f3 = te.rotb + f2 * f;
/* 172 */     GL11.glRotatef(f3 * 180.0F / 3.141593F, 0.0F, 1.0F, 0.0F);
/* 173 */     GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */     
/* 175 */     UtilsFX.bindTexture("textures/models/brain2.png");
/* 176 */     GL11.glScalef(0.4F, 0.4F, 0.4F);
/* 177 */     this.brain.render();
/* 178 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 179 */     GL11.glPopMatrix();
/* 180 */     UtilsFX.bindTexture("textures/models/jarbrine.png");
/* 181 */     this.model.renderBrine();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*     */   {
/* 189 */     renderTileEntityAt((TileJar)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileJarRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */