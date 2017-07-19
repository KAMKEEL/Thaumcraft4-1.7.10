/*    */ package thaumcraft.common.entities.ai.misc;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ public class AIWander extends net.minecraft.entity.ai.EntityAIBase
/*    */ {
/*    */   private EntityCreature entity;
/*    */   private double xPosition;
/*    */   private double yPosition;
/*    */   private double zPosition;
/*    */   private double speed;
/*    */   private boolean field_179482_g;
/*    */   private static final String __OBFID = "CL_00001608";
/*    */   
/*    */   public AIWander(EntityCreature p_i1648_1_, double p_i1648_2_)
/*    */   {
/* 20 */     this.entity = p_i1648_1_;
/* 21 */     this.speed = p_i1648_2_;
/* 22 */     func_75248_a(1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 30 */     if (!this.field_179482_g)
/*    */     {
/* 32 */       if (this.entity.func_70654_ax() >= 100)
/*    */       {
/* 34 */         return false;
/*    */       }
/* 36 */       if (this.entity.func_70681_au().nextInt(120) != 0)
/*    */       {
/* 38 */         return false;
/*    */       }
/*    */     }
/*    */     
/* 42 */     Vec3 vec3 = RandomPositionGenerator.func_75463_a(this.entity, 10, 7);
/*    */     
/* 44 */     if (vec3 == null)
/*    */     {
/* 46 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 50 */     this.xPosition = vec3.field_72450_a;
/* 51 */     this.yPosition = vec3.field_72448_b;
/* 52 */     this.zPosition = vec3.field_72449_c;
/* 53 */     this.field_179482_g = false;
/* 54 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 63 */     return !this.entity.func_70661_as().func_75500_f();
/*    */   }
/*    */   
/*    */   public void setWander()
/*    */   {
/* 68 */     this.field_179482_g = true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 76 */     this.entity.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, this.speed);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/misc/AIWander.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */