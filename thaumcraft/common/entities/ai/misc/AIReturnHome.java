/*     */ package thaumcraft.common.entities.ai.misc;
/*     */ 
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ public class AIReturnHome extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private double movePosX;
/*     */   private double movePosY;
/*     */   private double movePosZ;
/*  16 */   private int pathingDelay = 0;
/*  17 */   private int pathingDelayInc = 5;
/*     */   
/*     */   public AIReturnHome(EntityGolemBase par1EntityCreature)
/*     */   {
/*  21 */     this.theGolem = par1EntityCreature;
/*  22 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  30 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  31 */     if (this.pathingDelay > 0) this.pathingDelay -= 1;
/*  32 */     if ((this.pathingDelay > 0) || (this.theGolem.func_70092_e(home.field_71574_a + 0.5F, home.field_71572_b + 0.5F, home.field_71573_c + 0.5F) < 3.0D)) {
/*  33 */       return false;
/*     */     }
/*     */     
/*  36 */     this.movePosX = (home.field_71574_a + 0.5D);
/*  37 */     this.movePosY = (home.field_71572_b + 0.5D);
/*  38 */     this.movePosZ = (home.field_71573_c + 0.5D);
/*  39 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  47 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  48 */     return (this.pathingDelay <= 0) && (this.count > 0) && (!this.theGolem.func_70661_as().func_75500_f()) && (this.theGolem.func_70092_e(home.field_71574_a + 0.5F, home.field_71572_b + 0.5F, home.field_71573_c + 0.5F) >= 3.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  59 */   int count = 0;
/*  60 */   int prevX = 0;
/*  61 */   int prevY = 0;
/*  62 */   int prevZ = 0;
/*     */   
/*     */   public void func_75251_c() {}
/*     */   
/*  66 */   public void func_75246_d() { this.count -= 1;
/*  67 */     if ((this.count == 0) && (this.prevX == MathHelper.func_76128_c(this.theGolem.field_70165_t)) && (this.prevY == MathHelper.func_76128_c(this.theGolem.field_70163_u)) && (this.prevZ == MathHelper.func_76128_c(this.theGolem.field_70161_v))) {
/*  68 */       Vec3 var2 = net.minecraft.entity.ai.RandomPositionGenerator.func_75463_a(this.theGolem, 2, 1);
/*     */       
/*  70 */       if (var2 != null)
/*     */       {
/*  72 */         this.count = 20;
/*  73 */         boolean path = this.theGolem.func_70661_as().func_75492_a(var2.field_72450_a + 0.5D, var2.field_72448_b + 0.5D, var2.field_72449_c + 0.5D, this.theGolem.func_70689_ay());
/*  74 */         if (!path) {
/*  75 */           this.pathingDelay = this.pathingDelayInc;
/*  76 */           if (this.pathingDelayInc < 50) this.pathingDelayInc += 5;
/*     */         } else {
/*  78 */           this.pathingDelayInc = 5;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  84 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  92 */     this.count = 20;
/*  93 */     this.prevX = MathHelper.func_76128_c(this.theGolem.field_70165_t);
/*  94 */     this.prevY = MathHelper.func_76128_c(this.theGolem.field_70163_u);
/*  95 */     this.prevZ = MathHelper.func_76128_c(this.theGolem.field_70161_v);
/*  96 */     boolean path = this.theGolem.func_70661_as().func_75492_a(this.movePosX, this.movePosY, this.movePosZ, this.theGolem.func_70689_ay());
/*  97 */     if (!path) {
/*  98 */       this.pathingDelay = this.pathingDelayInc;
/*  99 */       if (this.pathingDelayInc < 50) this.pathingDelayInc += 5;
/*     */     } else {
/* 101 */       this.pathingDelayInc = 5;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/misc/AIReturnHome.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */