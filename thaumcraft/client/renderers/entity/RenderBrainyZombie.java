/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelZombieVillager;
/*    */ import net.minecraft.client.renderer.entity.RenderZombie;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderBrainyZombie
/*    */   extends RenderZombie
/*    */ {
/* 20 */   private static final ResourceLocation field_110865_p = new ResourceLocation("thaumcraft", "textures/models/bzombie.png");
/* 21 */   private static final ResourceLocation field_110864_q = new ResourceLocation("thaumcraft", "textures/models/bzombievil.png");
/*    */   private ModelBiped field_82434_o;
/*    */   private ModelZombieVillager field_82432_p;
/* 24 */   private int field_82431_q = 1;
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(EntityZombie par1EntityZombie)
/*    */   {
/* 29 */     return par1EntityZombie.func_82231_m() ? field_110864_q : field_110865_p;
/*    */   }
/*    */   
/*    */   protected void preRenderScale(EntityGiantBrainyZombie z, float par2)
/*    */   {
/* 34 */     GL11.glScalef(z.getAnger(), z.getAnger(), z.getAnger());
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*    */   {
/* 40 */     if ((par1EntityLiving instanceof EntityGiantBrainyZombie)) {
/* 41 */       preRenderScale((EntityGiantBrainyZombie)par1EntityLiving, par2);
/*    */     }
/*    */   }
/*    */   
/*    */   private void func_82427_a(EntityZombie par1EntityZombie) {
/* 46 */     if (par1EntityZombie.func_82231_m())
/*    */     {
/* 48 */       if (this.field_82431_q != this.field_82432_p.func_82897_a())
/*    */       {
/* 50 */         this.field_82432_p = new ModelZombieVillager();
/* 51 */         this.field_82431_q = this.field_82432_p.func_82897_a();
/* 52 */         this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
/* 53 */         this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
/*    */       }
/*    */       
/* 56 */       this.field_77045_g = this.field_82432_p;
/* 57 */       this.field_82423_g = this.field_82436_m;
/* 58 */       this.field_82425_h = this.field_82433_n;
/*    */     }
/*    */     else
/*    */     {
/* 62 */       this.field_77045_g = this.field_82434_o;
/* 63 */       this.field_82423_g = this.field_82437_k;
/* 64 */       this.field_82425_h = this.field_82435_l;
/*    */     }
/*    */     
/* 67 */     this.field_77071_a = ((ModelBiped)this.field_77045_g);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderBrainyZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */