/*    */ package thaumcraft.common.entities.ai.combat;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.ai.EntityAITarget;
/*    */ 
/*    */ 
/*    */ public class AIOldestAttackableTargetSorter
/*    */   implements Comparator
/*    */ {
/*    */   private Entity theEntity;
/*    */   final EntityAITarget parent;
/*    */   
/*    */   public AIOldestAttackableTargetSorter(EntityAITarget par1EntityAIOldestAttackableTarget, Entity par2Entity)
/*    */   {
/* 16 */     this.parent = par1EntityAIOldestAttackableTarget;
/* 17 */     this.theEntity = par2Entity;
/*    */   }
/*    */   
/*    */   public int compareAge(Entity par1Entity, Entity par2Entity)
/*    */   {
/* 22 */     int var3 = par1Entity.field_70173_aa;
/* 23 */     int var5 = par2Entity.field_70173_aa;
/* 24 */     return var3 < var5 ? 1 : var3 > var5 ? -1 : 0;
/*    */   }
/*    */   
/*    */   public int compare(Object par1Obj, Object par2Obj)
/*    */   {
/* 29 */     return compareAge((Entity)par1Obj, (Entity)par2Obj);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AIOldestAttackableTargetSorter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */