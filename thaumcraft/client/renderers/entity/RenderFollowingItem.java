/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderItem;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.common.entities.EntitySpecialItem;
/*    */ 
/*    */ 
/*    */ public class RenderFollowingItem
/*    */   extends Render
/*    */ {
/* 19 */   private RenderBlocks renderBlocks = new RenderBlocks();
/*    */   
/*    */ 
/* 22 */   private Random random = new Random();
/* 23 */   public boolean field_77024_a = true;
/*    */   
/*    */ 
/* 26 */   public float zLevel = 0.0F;
/*    */   
/*    */   public RenderFollowingItem() {
/* 29 */     this.field_76989_e = 0.15F;
/* 30 */     this.field_76987_f = 0.75F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void doRenderItem(EntitySpecialItem par1EntityItem, double par2, double par4, double par6, float par8, float pticks)
/*    */   {
/* 39 */     this.random.setSeed(187L);
/* 40 */     RenderItem ri = new RenderItem();
/* 41 */     ri.func_76976_a(RenderManager.field_78727_a);
/* 42 */     ItemStack var10 = par1EntityItem.func_92059_d();
/* 43 */     if (var10 != null) {
/* 44 */       EntityItem ei = new EntityItem(par1EntityItem.field_70170_p, par1EntityItem.field_70165_t, par1EntityItem.field_70163_u, par1EntityItem.field_70161_v, var10);
/*    */       
/*    */ 
/* 47 */       ei.field_70292_b = par1EntityItem.field_70292_b;
/* 48 */       ei.field_70290_d = par1EntityItem.field_70290_d;
/* 49 */       ri.func_76986_a(ei, par2, par4, par6, par8, pticks);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 63 */     doRenderItem((EntitySpecialItem)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 69 */     return AbstractClientPlayer.field_110314_b;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderFollowingItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */