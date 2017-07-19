/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.ModelBoreBase;
/*    */ import thaumcraft.common.tiles.TileArcaneBoreBase;
/*    */ import thaumcraft.common.tiles.TileArcaneLamp;
/*    */ import thaumcraft.common.tiles.TileArcaneLampFertility;
/*    */ import thaumcraft.common.tiles.TileArcaneLampGrowth;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileArcaneLampRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private ModelBoreBase model;
/*    */   
/*    */   public TileArcaneLampRenderer()
/*    */   {
/* 27 */     this.model = new ModelBoreBase();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f)
/*    */   {
/* 34 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*    */     
/* 36 */     if (tileentity.func_145831_w() != null) {
/* 37 */       ForgeDirection dir = ForgeDirection.DOWN;
/* 38 */       if ((tileentity instanceof TileArcaneLamp)) {
/* 39 */         dir = ((TileArcaneLamp)tileentity).facing;
/* 40 */       } else if ((tileentity instanceof TileArcaneLampGrowth)) {
/* 41 */         dir = ((TileArcaneLampGrowth)tileentity).facing;
/* 42 */       } else if ((tileentity instanceof TileArcaneLampFertility)) {
/* 43 */         dir = ((TileArcaneLampFertility)tileentity).facing;
/*    */       }
/* 45 */       GL11.glPushMatrix();
/* 46 */       UtilsFX.bindTexture("textures/models/Bore.png");
/* 47 */       if ((tileentity.func_145831_w().func_147438_o(tileentity.field_145851_c + dir.offsetX, tileentity.field_145848_d + dir.offsetY, tileentity.field_145849_e + dir.offsetZ) instanceof TileArcaneBoreBase))
/*    */       {
/*    */ 
/*    */ 
/* 51 */         GL11.glPushMatrix();
/* 52 */         GL11.glTranslatef((float)x + 0.5F + dir.offsetX, (float)y + dir.offsetY, (float)z + 0.5F + dir.offsetZ);
/*    */         
/*    */ 
/* 55 */         switch (dir.getOpposite().ordinal()) {
/* 56 */         case 0:  GL11.glTranslatef(-0.5F, 0.5F, 0.0F);
/* 57 */           GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F); break;
/* 58 */         case 1:  GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 59 */           GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F); break;
/* 60 */         case 2:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 61 */         case 3:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 62 */         case 4:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/* 63 */         case 5:  GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/*    */         }
/* 65 */         this.model.renderNozzle();
/* 66 */         GL11.glPopMatrix();
/*    */       }
/* 68 */       GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
/* 69 */       GL11.glPushMatrix();
/* 70 */       switch (dir.ordinal()) {
/* 71 */       case 0:  GL11.glTranslatef(-0.5F, 0.5F, 0.0F);
/* 72 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F); break;
/* 73 */       case 1:  GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 74 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F); break;
/* 75 */       case 2:  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 76 */       case 3:  GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 77 */       case 4:  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/* 78 */       case 5:  GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/*    */       }
/* 80 */       this.model.renderNozzle();
/* 81 */       GL11.glPopMatrix();
/* 82 */       GL11.glPopMatrix();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileArcaneLampRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */