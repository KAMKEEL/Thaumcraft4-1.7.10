/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ChampionModWarp implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount)
/*    */   {
/* 16 */     if ((boss.field_70170_p.field_73012_v.nextFloat() < 0.33F) && ((target instanceof EntityPlayer))) {
/* 17 */       Thaumcraft.addWarpToPlayer((EntityPlayer)target, 1 + boss.field_70170_p.field_73012_v.nextInt(3), true);
/*    */     }
/* 19 */     return amount;
/*    */   }
/*    */   
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 25 */     if (boss.field_70170_p.field_73012_v.nextBoolean()) return;
/* 26 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 27 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 28 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/*    */     
/* 30 */     Thaumcraft.proxy.drawGenericParticles(boss.field_70170_p, boss.field_70121_D.field_72340_a + w, boss.field_70121_D.field_72338_b + h, boss.field_70121_D.field_72339_c + d, 0.0D, 0.0D, 0.0D, 0.8F + boss.field_70170_p.field_73012_v.nextFloat() * 0.2F, 0.0F, 0.9F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.7F, true, 72, 8, 1, 10 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.6F + boss.field_70170_p.field_73012_v.nextFloat() * 0.4F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/mods/ChampionModWarp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */