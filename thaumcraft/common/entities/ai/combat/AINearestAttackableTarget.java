/*    */ package thaumcraft.common.entities.ai.combat;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.command.IEntitySelector;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.EntityAITarget;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*    */ 
/*    */ public class AINearestAttackableTarget extends EntityAITarget
/*    */ {
/*    */   EntityGolemBase theGolem;
/*    */   EntityLivingBase target;
/*    */   int targetChance;
/*    */   private final IEntitySelector entitySelector;
/* 20 */   private float targetDistance = 0.0F;
/*    */   
/*    */   private AINearestAttackableTargetSorter theNearestAttackableTargetSorter;
/*    */   
/*    */ 
/*    */   public AINearestAttackableTarget(EntityGolemBase par1EntityLiving, int par4, boolean par5)
/*    */   {
/* 27 */     this(par1EntityLiving, 0.0F, par4, par5, false, (IEntitySelector)null);
/*    */   }
/*    */   
/*    */   public AINearestAttackableTarget(EntityGolemBase par1, float par3, int par4, boolean par5, boolean par6, IEntitySelector par7IEntitySelector)
/*    */   {
/* 32 */     super(par1, par5, par6);
/* 33 */     this.theGolem = par1;
/* 34 */     this.targetDistance = 0.0F;
/* 35 */     this.targetChance = par4;
/* 36 */     this.theNearestAttackableTargetSorter = new AINearestAttackableTargetSorter(this, par1);
/* 37 */     this.entitySelector = par7IEntitySelector;
/* 38 */     func_75248_a(3);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 46 */     this.targetDistance = this.theGolem.getRange();
/* 47 */     if ((this.targetChance > 0) && (this.field_75299_d.func_70681_au().nextInt(this.targetChance) != 0))
/*    */     {
/* 49 */       return false;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 54 */     List var5 = this.field_75299_d.field_70170_p.func_82733_a(EntityLivingBase.class, this.field_75299_d.field_70121_D.func_72314_b(this.targetDistance, 4.0D, this.targetDistance), this.entitySelector);
/*    */     
/* 56 */     java.util.Collections.sort(var5, this.theNearestAttackableTargetSorter);
/* 57 */     Iterator var2 = var5.iterator();
/*    */     
/* 59 */     while (var2.hasNext())
/*    */     {
/* 61 */       Entity var3 = (Entity)var2.next();
/* 62 */       EntityLivingBase var4 = (EntityLivingBase)var3;
/*    */       
/* 64 */       if (this.theGolem.isValidTarget(var3))
/*    */       {
/* 66 */         this.target = var4;
/* 67 */         return true;
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 72 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 81 */     this.field_75299_d.func_70624_b(this.target);
/* 82 */     super.func_75249_e();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AINearestAttackableTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */