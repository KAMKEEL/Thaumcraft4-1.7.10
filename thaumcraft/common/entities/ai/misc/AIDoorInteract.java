/*     */ package thaumcraft.common.entities.ai.misc;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AIDoorInteract
/*     */   extends EntityAIBase
/*     */ {
/*     */   protected EntityGolemBase theEntity;
/*     */   protected int entityPosX;
/*     */   protected int entityPosY;
/*     */   protected int entityPosZ;
/*     */   protected Block targetDoor;
/*     */   boolean hasStoppedDoorInteraction;
/*     */   float entityPositionX;
/*     */   float entityPositionZ;
/*     */   
/*     */   public AIDoorInteract(EntityGolemBase par1EntityLiving)
/*     */   {
/*  29 */     this.theEntity = par1EntityLiving;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  37 */     if (!this.theEntity.field_70123_F)
/*     */     {
/*  39 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  43 */     PathNavigate var1 = this.theEntity.func_70661_as();
/*  44 */     PathEntity var2 = var1.func_75505_d();
/*     */     
/*  46 */     if ((var2 != null) && (!var2.func_75879_b()) && (var1.func_75507_c()))
/*     */     {
/*  48 */       for (int var3 = 0; var3 < Math.min(var2.func_75873_e() + 2, var2.func_75874_d()); var3++)
/*     */       {
/*  50 */         PathPoint var4 = var2.func_75877_a(var3);
/*  51 */         this.entityPosX = var4.field_75839_a;
/*  52 */         this.entityPosY = var4.field_75837_b;
/*  53 */         this.entityPosZ = var4.field_75838_c;
/*     */         
/*  55 */         if (this.theEntity.func_70092_e(this.entityPosX, this.theEntity.field_70163_u, this.entityPosZ) <= 2.25D)
/*     */         {
/*  57 */           this.targetDoor = findUsableDoor(this.entityPosX, this.entityPosY, this.entityPosZ);
/*     */           
/*  59 */           if ((this.targetDoor != null) && (this.targetDoor != Blocks.field_150350_a))
/*     */           {
/*  61 */             this.count = 200;
/*  62 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  67 */       this.entityPosX = MathHelper.func_76128_c(this.theEntity.field_70165_t);
/*  68 */       this.entityPosY = MathHelper.func_76128_c(this.theEntity.field_70163_u);
/*  69 */       this.entityPosZ = MathHelper.func_76128_c(this.theEntity.field_70161_v);
/*  70 */       this.targetDoor = findUsableDoor(this.entityPosX, this.entityPosY, this.entityPosZ);
/*  71 */       if ((this.targetDoor != null) && (this.targetDoor != Blocks.field_150350_a)) this.count = 200;
/*  72 */       return (this.targetDoor != null) && (this.targetDoor != Blocks.field_150350_a);
/*     */     }
/*     */     
/*     */ 
/*  76 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  86 */     return (this.count > 0) && (!this.hasStoppedDoorInteraction);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  94 */     this.count = 100;
/*  95 */     this.hasStoppedDoorInteraction = false;
/*  96 */     this.entityPositionX = ((float)(this.entityPosX + 0.5F - this.theEntity.field_70165_t));
/*  97 */     this.entityPositionZ = ((float)(this.entityPosZ + 0.5F - this.theEntity.field_70161_v));
/*     */   }
/*     */   
/* 100 */   int count = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/* 107 */     this.count -= 1;
/* 108 */     float var1 = (float)(this.entityPosX + 0.5F - this.theEntity.field_70165_t);
/* 109 */     float var2 = (float)(this.entityPosZ + 0.5F - this.theEntity.field_70161_v);
/* 110 */     float var3 = this.entityPositionX * var1 + this.entityPositionZ * var2;
/*     */     
/* 112 */     if (var3 < 0.0F)
/*     */     {
/* 114 */       this.hasStoppedDoorInteraction = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Block findUsableDoor(int par1, int par2, int par3)
/*     */   {
/* 123 */     Block var4 = this.theEntity.field_70170_p.func_147439_a(par1, par2, par3);
/* 124 */     return (var4 != Blocks.field_150466_ao) && (var4 != Blocks.field_150396_be) ? Block.func_149729_e(0) : var4;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/misc/AIDoorInteract.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */