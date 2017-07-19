/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.entities.monster.EntityTaintSpider;
/*    */ 
/*    */ public class ChampionModInfested implements IChampionModifierEffect
/*    */ {
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount)
/*    */   {
/* 14 */     if ((boss.field_70170_p.field_73012_v.nextFloat() < 0.4F) && (!boss.field_70170_p.field_72995_K)) {
/* 15 */       EntityTaintSpider spiderling = new EntityTaintSpider(boss.field_70170_p);
/* 16 */       spiderling.func_70012_b(boss.field_70165_t, boss.field_70163_u + boss.field_70131_O / 2.0F, boss.field_70161_v, boss.field_70170_p.field_73012_v.nextFloat() * 360.0F, 0.0F);
/* 17 */       boss.field_70170_p.func_72838_d(spiderling);
/* 18 */       boss.func_85030_a("thaumcraft:gore", 0.5F, 1.0F);
/*    */     }
/* 20 */     return amount;
/*    */   }
/*    */   
/*    */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss)
/*    */   {
/* 26 */     if (boss.field_70170_p.field_73012_v.nextBoolean()) Thaumcraft.proxy.slimeJumpFX(boss, 0);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/mods/ChampionModInfested.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */