/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.DamageSource;
/*    */ 
/*    */ 
/*    */ public class ChampionModDummy
/*    */   implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount)
/*    */   {
/* 12 */     return amount;
/*    */   }
/*    */   
/*    */   public void showFX(EntityLivingBase boss) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/mods/ChampionModDummy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */