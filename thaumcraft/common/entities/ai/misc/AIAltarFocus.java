/*    */ package thaumcraft.common.entities.ai.misc;
/*    */ 
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.util.ChunkCoordinates;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.entities.monster.EntityCultistCleric;
/*    */ 
/*    */ 
/*    */ public class AIAltarFocus
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCultistCleric entity;
/*    */   private World world;
/* 15 */   int field_48399_a = 0;
/*    */   
/*    */   public AIAltarFocus(EntityCultistCleric par1EntityLiving)
/*    */   {
/* 19 */     this.entity = par1EntityLiving;
/* 20 */     this.world = par1EntityLiving.field_70170_p;
/* 21 */     func_75248_a(7);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 30 */     if ((!this.entity.getIsRitualist()) || (this.entity.func_110172_bL() == null))
/*    */     {
/* 32 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 36 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75251_c() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 63 */     return (this.entity.getIsRitualist()) && (this.entity.func_110172_bL() != null);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75246_d()
/*    */   {
/* 72 */     if (this.entity.func_110172_bL() != null)
/*    */     {
/* 74 */       if ((this.entity.field_70173_aa % 40 == 0) && ((this.entity.func_110172_bL().func_71569_e((int)this.entity.field_70165_t, (int)this.entity.field_70163_u, (int)this.entity.field_70161_v) > 16.0F) || (this.world.func_147439_a(this.entity.func_110172_bL().field_71574_a, this.entity.func_110172_bL().field_71572_b, this.entity.func_110172_bL().field_71573_c) != ConfigBlocks.blockEldritch)))
/*    */       {
/*    */ 
/* 77 */         this.entity.setIsRitualist(false);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/misc/AIAltarFocus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */