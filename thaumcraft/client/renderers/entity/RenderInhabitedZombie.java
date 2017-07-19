/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelZombieVillager;
/*    */ import net.minecraft.client.renderer.entity.RenderZombie;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderInhabitedZombie
/*    */   extends RenderZombie
/*    */ {
/* 21 */   private static final ResourceLocation t1 = new ResourceLocation("thaumcraft", "textures/models/czombie.png");
/*    */   private ModelBiped field_82434_o;
/*    */   private ModelZombieVillager field_82432_p;
/* 24 */   private int field_82431_q = 1;
/*    */   
/*    */ 
/*    */ 
/*    */   protected ResourceLocation func_110775_a(EntityZombie par1EntityZombie)
/*    */   {
/* 30 */     return t1;
/*    */   }
/*    */   
/*    */ 
/*    */   private void func_82427_a(EntityZombie par1EntityZombie)
/*    */   {
/* 36 */     if (par1EntityZombie.func_82231_m())
/*    */     {
/* 38 */       if (this.field_82431_q != this.field_82432_p.func_82897_a())
/*    */       {
/* 40 */         this.field_82432_p = new ModelZombieVillager();
/* 41 */         this.field_82431_q = this.field_82432_p.func_82897_a();
/* 42 */         this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
/* 43 */         this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
/*    */       }
/*    */       
/* 46 */       this.field_77045_g = this.field_82432_p;
/* 47 */       this.field_82423_g = this.field_82436_m;
/* 48 */       this.field_82425_h = this.field_82433_n;
/*    */     }
/*    */     else
/*    */     {
/* 52 */       this.field_77045_g = this.field_82434_o;
/* 53 */       this.field_82423_g = this.field_82437_k;
/* 54 */       this.field_82425_h = this.field_82435_l;
/*    */     }
/*    */     
/* 57 */     this.field_77071_a = ((ModelBiped)this.field_77045_g);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderInhabitedZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */