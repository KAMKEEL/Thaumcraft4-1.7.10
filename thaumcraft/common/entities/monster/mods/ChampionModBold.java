/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.fx.particles.FXSpark;
/*    */ 
/*    */ public class ChampionModBold implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float ammount)
/*    */   {
/* 16 */     return 0.0F;
/*    */   }
/*    */   
/*    */   @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 22 */     if (boss.field_70170_p.field_73012_v.nextBoolean()) return;
/* 23 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 24 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 25 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O / 3.0F;
/*    */     
/* 27 */     FXSpark ef = new FXSpark(boss.field_70170_p, boss.field_70121_D.field_72340_a + w, boss.field_70121_D.field_72338_b + h, boss.field_70121_D.field_72339_c + d, 0.2F);
/* 28 */     ef.func_70538_b(0.3F - boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.0F, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/* 29 */     ParticleEngine.instance.addEffect(boss.field_70170_p, ef);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/mods/ChampionModBold.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */