/*    */ package thaumcraft.common.entities.ai.combat;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.ai.EntityAITarget;
/*    */ 
/*    */ 
/*    */ public class AINearestAttackableTargetSorter
/*    */   implements Comparator
/*    */ {
/*    */   private Entity theEntity;
/*    */   final EntityAITarget parent;
/*    */   
/*    */   public AINearestAttackableTargetSorter(EntityAITarget par1EntityAINearestAttackableTarget, Entity par2Entity)
/*    */   {
/* 16 */     this.parent = par1EntityAINearestAttackableTarget;
/* 17 */     this.theEntity = par2Entity;
/*    */   }
/*    */   
/*    */   public int compareDistanceSq(Entity par1Entity, Entity par2Entity)
/*    */   {
/* 22 */     double var3 = this.theEntity.func_70068_e(par1Entity);
/* 23 */     double var5 = this.theEntity.func_70068_e(par2Entity);
/* 24 */     return var3 > var5 ? 1 : var3 < var5 ? -1 : 0;
/*    */   }
/*    */   
/*    */   public int compare(Object par1Obj, Object par2Obj)
/*    */   {
/* 29 */     return compareDistanceSq((Entity)par1Obj, (Entity)par2Obj);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AINearestAttackableTargetSorter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */