/*    */ package thaumcraft.api.damagesource;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EntityDamageSourceIndirect;
/*    */ 
/*    */ public class DamageSourceIndirectThaumcraftEntity
/*    */   extends EntityDamageSourceIndirect
/*    */ {
/*    */   private boolean fireDamage;
/*    */   private float hungerDamage;
/*    */   private boolean isUnblockable;
/*    */   
/*    */   public DamageSourceIndirectThaumcraftEntity(String par1Str, Entity par2Entity, Entity par3Entity)
/*    */   {
/* 16 */     super(par1Str, par2Entity, par3Entity);
/*    */   }
/*    */   
/*    */ 
/*    */   public DamageSource func_76361_j()
/*    */   {
/* 22 */     this.fireDamage = true;
/* 23 */     return this;
/*    */   }
/*    */   
/*    */   public DamageSource func_76348_h()
/*    */   {
/* 28 */     this.isUnblockable = true;
/* 29 */     this.hungerDamage = 0.0F;
/* 30 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/damagesource/DamageSourceIndirectThaumcraftEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */