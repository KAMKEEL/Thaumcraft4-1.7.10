/*    */ package thaumcraft.common.entities.ai.combat;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.ai.EntityAITarget;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.entities.monster.EntityCultistCleric;
/*    */ 
/*    */ public class AICultistHurtByTarget extends EntityAITarget
/*    */ {
/*    */   boolean entityCallsForHelp;
/*    */   private int field_142052_b;
/*    */   private static final String __OBFID = "CL_00001619";
/*    */   
/*    */   public AICultistHurtByTarget(EntityCreature p_i1660_1_, boolean p_i1660_2_)
/*    */   {
/* 20 */     super(p_i1660_1_, false);
/* 21 */     this.entityCallsForHelp = p_i1660_2_;
/* 22 */     func_75248_a(1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 30 */     int i = this.field_75299_d.func_142015_aE();
/* 31 */     return (i != this.field_142052_b) && (func_75296_a(this.field_75299_d.func_70643_av(), false));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 39 */     this.field_75299_d.func_70624_b(this.field_75299_d.func_70643_av());
/* 40 */     this.field_142052_b = this.field_75299_d.func_142015_aE();
/*    */     
/* 42 */     if (this.entityCallsForHelp)
/*    */     {
/* 44 */       double d0 = func_111175_f();
/* 45 */       List list = this.field_75299_d.field_70170_p.func_72872_a(thaumcraft.common.entities.monster.EntityCultist.class, AxisAlignedBB.func_72330_a(this.field_75299_d.field_70165_t, this.field_75299_d.field_70163_u, this.field_75299_d.field_70161_v, this.field_75299_d.field_70165_t + 1.0D, this.field_75299_d.field_70163_u + 1.0D, this.field_75299_d.field_70161_v + 1.0D).func_72314_b(d0, 10.0D, d0));
/* 46 */       Iterator iterator = list.iterator();
/*    */       
/* 48 */       while (iterator.hasNext())
/*    */       {
/* 50 */         EntityCreature entitycreature = (EntityCreature)iterator.next();
/*    */         
/* 52 */         if ((this.field_75299_d != entitycreature) && (entitycreature.func_70638_az() == null) && (!entitycreature.func_142014_c(this.field_75299_d.func_70643_av())))
/*    */         {
/*    */ 
/* 55 */           if (((entitycreature instanceof EntityCultistCleric)) && (((EntityCultistCleric)entitycreature).getIsRitualist()))
/*    */           {
/* 57 */             if (this.field_75299_d.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 58 */               ((EntityCultistCleric)entitycreature).setIsRitualist(false);
/* 59 */               entitycreature.func_70624_b(this.field_75299_d.func_70643_av());
/*    */             }
/*    */           } else {
/* 62 */             entitycreature.func_70624_b(this.field_75299_d.func_70643_av());
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 67 */     super.func_75249_e();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AICultistHurtByTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */