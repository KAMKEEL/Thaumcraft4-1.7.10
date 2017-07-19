/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.IIcon;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.IScribeTools;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelResearchTable;
/*    */ import thaumcraft.common.blocks.BlockTable;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ import thaumcraft.common.lib.research.ResearchNoteData;
/*    */ import thaumcraft.common.tiles.TileResearchTable;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileResearchTableRenderer extends TileEntitySpecialRenderer
/*    */ {
/* 28 */   private ModelResearchTable tableModel = new ModelResearchTable();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileResearchTable table, double par2, double par4, double par6, float par8)
/*    */   {
/* 36 */     int md = 0;
/* 37 */     if (table.func_145831_w() != null) {
/* 38 */       md = table.func_145832_p();
/*    */     }
/*    */     
/* 41 */     GL11.glPushMatrix();
/* 42 */     UtilsFX.bindTexture("textures/models/restable.png");
/* 43 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.0F, (float)par6 + 0.5F);
/* 44 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 45 */     switch (md) {
/* 46 */     case 2:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 47 */     case 3:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 48 */     case 4:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*    */     }
/*    */     
/* 51 */     this.tableModel.renderAll();
/*    */     
/* 53 */     if ((table.func_70301_a(0) != null) && ((table.func_70301_a(0).func_77973_b() instanceof IScribeTools))) {
/* 54 */       this.tableModel.renderInkwell();
/* 55 */       GL11.glPushMatrix();
/* 56 */       GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/* 57 */       GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 58 */       GL11.glTranslatef(-0.17F, 0.1F, -0.15F);
/* 59 */       GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
/* 60 */       IIcon icon = ((BlockTable)ConfigBlocks.blockTable).iconQuill;
/* 61 */       float f1 = icon.func_94212_f();
/* 62 */       float f2 = icon.func_94206_g();
/* 63 */       float f3 = icon.func_94209_e();
/* 64 */       float f4 = icon.func_94210_h();
/* 65 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 66 */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 67 */       this.field_147501_a.field_147553_e.func_110577_a(TextureMap.field_110575_b);
/* 68 */       ItemRenderer.func_78439_a(tessellator, f1, f2, f3, f4, icon.func_94211_a(), icon.func_94216_b(), 0.025F);
/* 69 */       GL11.glPopMatrix();
/*    */     }
/*    */     
/* 72 */     for (int a = 0; a < 6; a++) {
/* 73 */       GL11.glPushMatrix();
/* 74 */       GL11.glTranslatef(0.1F, -0.01F - a * 0.015F, 0.35F);
/* 75 */       GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 76 */       GL11.glRotatef(15 + a % 3 * 2, 0.0F, 0.0F, 1.0F);
/* 77 */       GL11.glScalef(0.5F, 0.6F, 0.6F);
/* 78 */       UtilsFX.renderQuad("textures/misc/parchment.png", 771, 1.0F);
/* 79 */       GL11.glPopMatrix();
/*    */     }
/*    */     
/* 82 */     if ((table.func_70301_a(1) != null) && (table.func_70301_a(1).func_77973_b() == ConfigItems.itemResearchNotes)) {
/* 83 */       UtilsFX.bindTexture("textures/models/restable2.png");
/* 84 */       ResearchNoteData rd = ResearchManager.getData(table.func_70301_a(1));
/* 85 */       int color = 10066329;
/* 86 */       if (rd != null) color = rd.color;
/* 87 */       this.tableModel.renderScroll(color);
/*    */     }
/*    */     
/* 90 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 98 */     renderTileEntityAt((TileResearchTable)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileResearchTableRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */