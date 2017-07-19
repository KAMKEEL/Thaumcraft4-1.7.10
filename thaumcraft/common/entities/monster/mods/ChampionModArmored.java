/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ChampionModArmored implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase mob, EntityLivingBase target, DamageSource source, float amount)
/*    */   {
/* 13 */     if (!source.func_76363_c()) {
/* 14 */       float f1 = amount * 19.0F;
/* 15 */       amount = f1 / 25.0F;
/*    */     }
/* 17 */     return amount;
/*    */   }
/*    */   
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 22 */     if (boss.field_70170_p.field_73012_v.nextInt(4) != 0) return;
/* 23 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 24 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 25 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 27 */     thaumcraft.common.Thaumcraft.proxy.drawGenericParticles(boss.field_70170_p, boss.field_70121_D.field_72340_a + w, boss.field_70121_D.field_72338_b + h, boss.field_70121_D.field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.9F, 0.9F, 0.9F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.7F, false, 112, 9, 1, 5 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.6F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/mods/ChampionModArmored.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */