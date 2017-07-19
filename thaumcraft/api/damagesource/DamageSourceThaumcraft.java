/*    */ package thaumcraft.api.damagesource;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EntityDamageSource;
/*    */ 
/*    */ public class DamageSourceThaumcraft
/*    */   extends DamageSource
/*    */ {
/* 10 */   public static DamageSource taint = new DamageSourceThaumcraft("taint").func_76348_h().func_82726_p();
/* 11 */   public static DamageSource tentacle = new DamageSourceThaumcraft("tentacle");
/* 12 */   public static DamageSource swarm = new DamageSourceThaumcraft("swarm");
/* 13 */   public static DamageSource dissolve = new DamageSourceThaumcraft("dissolve").func_76348_h();
/*    */   
/*    */   protected DamageSourceThaumcraft(String par1Str) {
/* 16 */     super(par1Str);
/*    */   }
/*    */   
/*    */ 
/* 20 */   private boolean isUnblockable = false;
/* 21 */   private boolean isDamageAllowedInCreativeMode = false;
/* 22 */   private float hungerDamage = 0.3F;
/*    */   
/*    */ 
/*    */   private boolean fireDamage;
/*    */   
/*    */ 
/*    */   private boolean projectile;
/*    */   
/*    */ 
/*    */   private boolean difficultyScaled;
/*    */   
/*    */ 
/* 34 */   private boolean magicDamage = false;
/* 35 */   private boolean explosion = false;
/*    */   
/*    */   public static DamageSource causeSwarmDamage(EntityLivingBase par0EntityLiving)
/*    */   {
/* 39 */     return new EntityDamageSource("swarm", par0EntityLiving);
/*    */   }
/*    */   
/*    */   public static DamageSource causeTentacleDamage(EntityLivingBase par0EntityLiving)
/*    */   {
/* 44 */     return new EntityDamageSource("tentacle", par0EntityLiving);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/damagesource/DamageSourceThaumcraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */