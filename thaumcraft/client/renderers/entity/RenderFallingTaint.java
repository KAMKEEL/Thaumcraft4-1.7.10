/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.entities.EntityFallingTaint;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderFallingTaint
/*    */   extends Render
/*    */ {
/* 23 */   private RenderBlocks renderBlocks = new RenderBlocks();
/*    */   
/*    */   public RenderFallingTaint()
/*    */   {
/* 27 */     this.field_76989_e = 0.5F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void doRenderFalling(EntityFallingTaint entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 35 */     World world = entity.getWorld();
/*    */     
/* 37 */     if (world.func_147439_a(MathHelper.func_76128_c(entity.field_70165_t), MathHelper.func_76128_c(entity.field_70163_u), MathHelper.func_76128_c(entity.field_70161_v)) != entity.block)
/*    */     {
/*    */ 
/* 40 */       GL11.glPushMatrix();
/* 41 */       GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/* 42 */       Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
/* 43 */       GL11.glDisable(2896);
/*    */       
/*    */ 
/* 46 */       if (entity.block != null)
/*    */       {
/* 48 */         this.renderBlocks.func_147775_a(entity.block);
/* 49 */         this.renderBlocks.func_147749_a(entity.block, world, MathHelper.func_76128_c(entity.field_70165_t), MathHelper.func_76128_c(entity.field_70163_u), MathHelper.func_76128_c(entity.field_70161_v), entity.metadata);
/*    */       }
/*    */       
/* 52 */       GL11.glEnable(2896);
/* 53 */       GL11.glPopMatrix();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 65 */     doRenderFalling((EntityFallingTaint)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 70 */     return AbstractClientPlayer.field_110314_b;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderFallingTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */