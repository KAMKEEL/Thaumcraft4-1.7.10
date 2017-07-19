/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.common.entities.projectile.EntityPechBlast;
/*    */ 
/*    */ public class RenderPechBlast
/*    */   extends Render
/*    */ {
/*    */   public RenderPechBlast()
/*    */   {
/* 14 */     this.field_76989_e = 0.1F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void renderEntityAt(EntityPechBlast tg, double x, double y, double z, float fq) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1)
/*    */   {
/* 27 */     renderEntityAt((EntityPechBlast)entity, d, d1, d2, f);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 34 */     return AbstractClientPlayer.field_110314_b;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderPechBlast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */