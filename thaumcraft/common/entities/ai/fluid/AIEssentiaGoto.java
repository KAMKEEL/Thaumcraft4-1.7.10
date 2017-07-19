/*    */ package thaumcraft.common.entities.ai.fluid;
/*    */ 
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import net.minecraft.util.ChunkCoordinates;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*    */ import thaumcraft.common.entities.golems.GolemHelper;
/*    */ 
/*    */ public class AIEssentiaGoto extends EntityAIBase
/*    */ {
/*    */   private EntityGolemBase theGolem;
/*    */   private double jarX;
/*    */   private double jarY;
/*    */   private double jarZ;
/*    */   private World theWorld;
/*    */   
/*    */   public AIEssentiaGoto(EntityGolemBase par1EntityCreature)
/*    */   {
/* 24 */     this.theGolem = par1EntityCreature;
/* 25 */     this.theWorld = par1EntityCreature.field_70170_p;
/* 26 */     func_75248_a(3);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 34 */     if ((this.theGolem.field_70173_aa % Config.golemDelay > 0) || (this.theGolem.essentia == null) || (this.theGolem.essentiaAmount == 0))
/*    */     {
/*    */ 
/* 37 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 41 */     ChunkCoordinates jarloc = GolemHelper.findJarWithRoom(this.theGolem);
/* 42 */     if (jarloc == null) return false;
/* 43 */     this.jarX = jarloc.field_71574_a;
/* 44 */     this.jarY = jarloc.field_71572_b;
/* 45 */     this.jarZ = jarloc.field_71573_c;
/* 46 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 55 */     return (this.count > 0) && (!this.theGolem.func_70661_as().func_75500_f());
/*    */   }
/*    */   
/*    */   public void func_75251_c()
/*    */   {
/* 60 */     this.count = 0;
/*    */   }
/*    */   
/*    */ 
/* 64 */   int count = 0;
/* 65 */   int prevX = 0;
/* 66 */   int prevY = 0;
/* 67 */   int prevZ = 0;
/*    */   
/*    */   public void func_75246_d()
/*    */   {
/* 71 */     this.count -= 1;
/* 72 */     if ((this.count == 0) && (this.prevX == MathHelper.func_76128_c(this.theGolem.field_70165_t)) && (this.prevY == MathHelper.func_76128_c(this.theGolem.field_70163_u)) && (this.prevZ == MathHelper.func_76128_c(this.theGolem.field_70161_v))) {
/* 73 */       Vec3 var2 = RandomPositionGenerator.func_75463_a(this.theGolem, 2, 1);
/*    */       
/* 75 */       if (var2 != null)
/*    */       {
/* 77 */         this.count = 20;
/* 78 */         this.theGolem.func_70661_as().func_75492_a(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c, this.theGolem.func_70689_ay());
/*    */       }
/*    */     }
/* 81 */     super.func_75246_d();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 89 */     this.count = 200;
/* 90 */     this.prevX = MathHelper.func_76128_c(this.theGolem.field_70165_t);
/* 91 */     this.prevY = MathHelper.func_76128_c(this.theGolem.field_70163_u);
/* 92 */     this.prevZ = MathHelper.func_76128_c(this.theGolem.field_70161_v);
/* 93 */     this.theGolem.func_70661_as().func_75492_a(this.jarX, this.jarY, this.jarZ, this.theGolem.func_70689_ay());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/fluid/AIEssentiaGoto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */