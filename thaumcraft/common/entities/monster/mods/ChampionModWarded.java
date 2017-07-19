/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ChampionModWarded implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase mob, EntityLivingBase target, DamageSource source, float amount)
/*    */   {
/* 14 */     if ((mob.field_70172_ad <= 0) && (mob.field_70173_aa % 25 == 0)) {
/* 15 */       int bh = (int)mob.func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
/* 16 */       if (mob.func_110139_bj() < bh)
/* 17 */         mob.func_110149_m(mob.func_110139_bj() + 1.0F);
/*    */     }
/* 19 */     return amount;
/*    */   }
/*    */   
/*    */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 25 */     if (boss.field_70170_p.field_73012_v.nextBoolean()) return;
/* 26 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 27 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 28 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 30 */     Thaumcraft.proxy.drawGenericParticles(boss.field_70170_p, boss.field_70121_D.field_72340_a + w, boss.field_70121_D.field_72338_b + h, boss.field_70121_D.field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.5F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.5F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.5F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.6F, true, 21, 4, 1, 4 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.3F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/mods/ChampionModWarded.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */