/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*    */ import net.minecraftforge.client.model.IModelCustom;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.entities.projectile.EntityFrostShard;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderFrostShard
/*    */   extends Render
/*    */ {
/*    */   private IModelCustom model;
/* 24 */   private static final ResourceLocation ORB = new ResourceLocation("thaumcraft", "textures/models/orb.obj");
/*    */   
/*    */   public RenderFrostShard() {
/* 27 */     this.model = AdvancedModelLoader.loadModel(ORB);
/*    */   }
/*    */   
/*    */   public void renderShard(EntityFrostShard shard, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 32 */     func_110777_b(shard);
/* 33 */     GL11.glPushMatrix();
/* 34 */     GL11.glEnable(32826);
/* 35 */     GL11.glEnable(3042);
/* 36 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 38 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/*    */     
/* 40 */     Random rnd = new Random(shard.func_145782_y());
/*    */     
/* 42 */     GL11.glRotatef(shard.field_70126_B + (shard.field_70177_z - shard.field_70126_B) * par9, 0.0F, 1.0F, 0.0F);
/* 43 */     GL11.glRotatef(shard.field_70127_C + (shard.field_70125_A - shard.field_70127_C) * par9, 0.0F, 0.0F, 1.0F);
/*    */     
/* 45 */     GL11.glPushMatrix();
/* 46 */     float base = shard.getDamage() * 0.1F;
/* 47 */     GL11.glScalef(base + rnd.nextFloat() * 0.1F, base + rnd.nextFloat() * 0.1F, base + rnd.nextFloat() * 0.1F);
/*    */     
/* 49 */     this.model.renderAll();
/* 50 */     GL11.glPopMatrix();
/*    */     
/* 52 */     GL11.glDisable(3042);
/*    */     
/* 54 */     GL11.glDisable(32826);
/* 55 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 61 */     renderShard((EntityFrostShard)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/* 64 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/blocks/frostshard.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 67 */     return rl;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderFrostShard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */