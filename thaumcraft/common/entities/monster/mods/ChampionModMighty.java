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
/*    */ public class ChampionModMighty implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float ammount)
/*    */   {
/* 17 */     return 0.0F;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 23 */     if (boss.field_70170_p.field_73012_v.nextFloat() > 0.3F) return;
/* 24 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 25 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 26 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/* 27 */     int p = 176 + boss.field_70170_p.field_73012_v.nextInt(4) * 3;
/* 28 */     Thaumcraft.proxy.drawGenericParticles(boss.field_70170_p, boss.field_70121_D.field_72340_a + w, boss.field_70121_D.field_72338_b + h, boss.field_70121_D.field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.7F, false, p, 3, 1, 4 + boss.field_70170_p.field_73012_v.nextInt(3), 0, 1.0F + boss.field_70170_p.field_73012_v.nextFloat() * 0.3F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/mods/ChampionModMighty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */