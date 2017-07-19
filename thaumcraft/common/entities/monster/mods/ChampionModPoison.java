/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ChampionModPoison implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount)
/*    */   {
/* 15 */     if (boss.field_70170_p.field_73012_v.nextFloat() < 0.4F) {
/* 16 */       target.func_70690_d(new net.minecraft.potion.PotionEffect(Potion.field_76436_u.field_76415_H, 100));
/*    */     }
/* 18 */     return amount;
/*    */   }
/*    */   
/*    */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 24 */     if (boss.field_70170_p.field_73012_v.nextBoolean()) return;
/* 25 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 26 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 27 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 29 */     Thaumcraft.proxy.drawGenericParticles(boss.field_70170_p, boss.field_70121_D.field_72340_a + w, boss.field_70121_D.field_72338_b + h, boss.field_70121_D.field_72339_c + d, 0.0D, 0.02D, 0.0D, 0.2F, 0.6F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.2F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.7F, false, 147, 4, 1, 8 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.5F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/mods/ChampionModPoison.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */