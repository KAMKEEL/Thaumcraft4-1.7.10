/*    */ package thaumcraft.common.entities.ai.pech;
/*    */ 
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import thaumcraft.common.entities.monster.EntityPech;
/*    */ 
/*    */ public class AIPechTradePlayer
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityPech villager;
/*    */   
/*    */   public AIPechTradePlayer(EntityPech par1EntityVillager)
/*    */   {
/* 14 */     this.villager = par1EntityVillager;
/* 15 */     func_75248_a(5);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 23 */     if (!this.villager.func_70089_S())
/*    */     {
/* 25 */       return false;
/*    */     }
/* 27 */     if (this.villager.func_70090_H())
/*    */     {
/* 29 */       return false;
/*    */     }
/* 31 */     if (!this.villager.isTamed())
/*    */     {
/* 33 */       return false;
/*    */     }
/* 35 */     if (!this.villager.field_70122_E)
/*    */     {
/* 37 */       return false;
/*    */     }
/* 39 */     if (this.villager.field_70133_I)
/*    */     {
/* 41 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 45 */     return this.villager.trading;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 54 */     this.villager.func_70661_as().func_75499_g();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75251_c()
/*    */   {
/* 62 */     this.villager.trading = false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/pech/AIPechTradePlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */