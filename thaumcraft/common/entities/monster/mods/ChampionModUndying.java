/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ChampionModUndying
/*    */   implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase mob, EntityLivingBase target, DamageSource source, float amount)
/*    */   {
/* 18 */     if (mob.field_70173_aa % 20 == 0) {
/* 19 */       mob.func_70691_i(1.0F);
/*    */     }
/* 21 */     return amount;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 27 */     if (boss.field_70170_p.field_73012_v.nextBoolean()) return;
/* 28 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 29 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 30 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 32 */     Thaumcraft.proxy.drawGenericParticles(boss.field_70170_p, boss.field_70121_D.field_72340_a + w, boss.field_70121_D.field_72338_b + h, boss.field_70121_D.field_72339_c + d, 0.0D, 0.03D, 0.0D, 0.1F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.1F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.9F, true, 21, 4, 1, 4 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.5F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/mods/ChampionModUndying.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */