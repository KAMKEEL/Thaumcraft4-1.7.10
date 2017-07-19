/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelTubeValve;
/*    */ import thaumcraft.common.blocks.BlockTube;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.tiles.TileTubeValve;
/*    */ 
/*    */ 
/*    */ public class TileTubeValveRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private ModelTubeValve model;
/*    */   
/*    */   public TileTubeValveRenderer()
/*    */   {
/* 27 */     this.model = new ModelTubeValve();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void renderEntityAt(TileTubeValve valve, double x, double y, double z, float fq)
/*    */   {
/* 34 */     UtilsFX.bindTexture("textures/models/valve.png");
/*    */     
/* 36 */     GL11.glPushMatrix();
/*    */     
/* 38 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*    */     
/* 40 */     if (valve.facing.offsetY == 0) {
/* 41 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*    */     } else {
/* 43 */       GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 44 */       GL11.glRotatef(90.0F, valve.facing.offsetY, 0.0F, 0.0F);
/*    */     }
/* 46 */     GL11.glRotatef(90.0F, valve.facing.offsetX, valve.facing.offsetY, valve.facing.offsetZ);
/*    */     
/*    */ 
/* 49 */     GL11.glRotated(-valve.rotation * 1.5D, 0.0D, 1.0D, 0.0D);
/* 50 */     GL11.glTranslated(0.0D, -(valve.rotation / 360.0F) * 0.12F, 0.0D);
/*    */     
/* 52 */     GL11.glPushMatrix();
/* 53 */     this.model.render();
/* 54 */     GL11.glPopMatrix();
/* 55 */     renderValve();
/*    */     
/* 57 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   void renderValve() {
/* 61 */     GL11.glPushMatrix();
/* 62 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 63 */     GL11.glTranslatef(-0.25F, -0.25F, -0.25F);
/* 64 */     GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 65 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 66 */     IIcon icon = ((BlockTube)ConfigBlocks.blockTube).iconValve;
/* 67 */     float f1 = icon.func_94212_f();
/* 68 */     float f2 = icon.func_94206_g();
/* 69 */     float f3 = icon.func_94209_e();
/* 70 */     float f4 = icon.func_94210_h();
/* 71 */     this.field_147501_a.field_147553_e.func_110577_a(TextureMap.field_110575_b);
/* 72 */     ItemRenderer.func_78439_a(tessellator, f1, f2, f3, f4, icon.func_94211_a(), icon.func_94216_b(), 0.1F);
/* 73 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f)
/*    */   {
/* 80 */     renderEntityAt((TileTubeValve)tileentity, d, d1, d2, f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileTubeValveRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */