/*    */ package thaumcraft.common.entities.ai.combat;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.PlayerCapabilities;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class AIHurtByTarget
/*    */   extends AITarget
/*    */ {
/*    */   boolean field_75312_a;
/*    */   EntityCreature entityPathNavigate;
/*    */   
/*    */   public AIHurtByTarget(EntityCreature par1EntityLiving, boolean par2)
/*    */   {
/* 21 */     super(par1EntityLiving, 16.0F, false);
/* 22 */     this.field_75312_a = par2;
/* 23 */     func_75248_a(1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 31 */     return isSuitableTarget(this.taskOwner.func_70643_av(), false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 39 */     return (this.taskOwner.func_70643_av() != null) && (this.taskOwner.func_70643_av() != this.entityPathNavigate);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 47 */     this.taskOwner.func_70624_b(this.taskOwner.func_70643_av());
/*    */     
/*    */ 
/* 50 */     if (this.field_75312_a)
/*    */     {
/* 52 */       List var1 = this.taskOwner.field_70170_p.func_72872_a(this.taskOwner.getClass(), AxisAlignedBB.func_72330_a(this.taskOwner.field_70165_t, this.taskOwner.field_70163_u, this.taskOwner.field_70161_v, this.taskOwner.field_70165_t + 1.0D, this.taskOwner.field_70163_u + 1.0D, this.taskOwner.field_70161_v + 1.0D).func_72314_b(this.targetDistance, 4.0D, this.targetDistance));
/* 53 */       Iterator var2 = var1.iterator();
/*    */       
/* 55 */       while (var2.hasNext())
/*    */       {
/* 57 */         EntityLiving var3 = (EntityLiving)var2.next();
/*    */         
/* 59 */         if ((this.taskOwner != var3) && (var3.func_70638_az() == null))
/*    */         {
/* 61 */           var3.func_70624_b(this.taskOwner.func_70643_av());
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 66 */     super.func_75249_e();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75251_c()
/*    */   {
/* 74 */     if ((this.taskOwner.func_70638_az() != null) && ((this.taskOwner.func_70638_az() instanceof EntityPlayer)) && (((EntityPlayer)this.taskOwner.func_70638_az()).field_71075_bZ.field_75102_a))
/*    */     {
/*    */ 
/* 77 */       this.taskOwner.func_70624_b(null);
/* 78 */       super.func_75251_c();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AIHurtByTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */