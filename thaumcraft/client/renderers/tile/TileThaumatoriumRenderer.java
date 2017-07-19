/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*    */ import net.minecraftforge.client.model.IModelCustom;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.crafting.CrucibleRecipe;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.tiles.TileThaumatorium;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileThaumatoriumRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private IModelCustom model;
/* 28 */   private static final ResourceLocation TM = new ResourceLocation("thaumcraft", "textures/models/thaumatorium.obj");
/*    */   
/* 30 */   public TileThaumatoriumRenderer() { this.model = AdvancedModelLoader.loadModel(TM); }
/*    */   
/*    */ 
/*    */ 
/* 34 */   EntityItem entityitem = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderTileEntityAt(TileThaumatorium tile, double par2, double par4, double par6, float par8)
/*    */   {
/* 42 */     GL11.glPushMatrix();
/* 43 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4, (float)par6 + 0.5F);
/* 44 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 45 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 47 */     UtilsFX.bindTexture("textures/models/thaumatorium.png");
/*    */     
/* 49 */     int md = 0;
/* 50 */     if (tile.func_145831_w() != null) {
/* 51 */       switch (tile.facing.ordinal()) {
/* 52 */       case 5:  GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F); break;
/* 53 */       case 3:  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F); break;
/* 54 */       case 2:  GL11.glRotatef(270.0F, 0.0F, 0.0F, 1.0F);
/*    */       }
/*    */       
/*    */     }
/* 58 */     this.model.renderAll();
/*    */     
/* 60 */     GL11.glPopMatrix();
/*    */     
/* 62 */     float ticks = Minecraft.func_71410_x().field_71451_h.field_70173_aa + par8;
/* 63 */     if ((tile != null) && (tile.func_145831_w() != null) && (tile.recipeHash != null) && (tile.recipeHash.size() > 0)) {
/* 64 */       int stack = Minecraft.func_71410_x().field_71451_h.field_70173_aa / 40 % tile.recipeHash.size();
/* 65 */       CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(((Integer)tile.recipeHash.get(stack)).intValue());
/* 66 */       if (recipe != null) {
/* 67 */         GL11.glPushMatrix();
/* 68 */         GL11.glTranslatef((float)par2 + 0.5F + tile.facing.offsetX / 1.99F, (float)par4 + 1.325F, (float)par6 + 0.5F + tile.facing.offsetZ / 1.99F);
/* 69 */         switch (tile.facing.ordinal()) {
/* 70 */         case 5:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 71 */         case 4:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 72 */         case 2:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*    */         }
/* 74 */         GL11.glScaled(0.75D, 0.75D, 0.75D);
/*    */         
/* 76 */         ItemStack is = recipe.getRecipeOutput().func_77946_l();
/* 77 */         is.field_77994_a = 1;
/* 78 */         this.entityitem = new EntityItem(tile.func_145831_w(), 0.0D, 0.0D, 0.0D, is);
/* 79 */         this.entityitem.field_70290_d = 0.0F;
/*    */         
/* 81 */         net.minecraft.client.renderer.entity.RenderItem.field_82407_g = true;
/* 82 */         RenderManager.field_78727_a.func_147940_a(this.entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 83 */         net.minecraft.client.renderer.entity.RenderItem.field_82407_g = false;
/* 84 */         GL11.glPopMatrix();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*    */   {
/* 91 */     renderTileEntityAt((TileThaumatorium)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileThaumatoriumRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */