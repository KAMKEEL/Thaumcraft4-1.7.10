/*    */ package thaumcraft.common.entities.ai.misc;
/*    */ 
/*    */ import net.minecraft.block.BlockDoor;
/*    */ import net.minecraft.block.BlockFenceGate;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*    */ 
/*    */ public class AIOpenDoor extends AIDoorInteract
/*    */ {
/*    */   boolean field_75361_i;
/*    */   int field_75360_j;
/*    */   
/*    */   public AIOpenDoor(EntityGolemBase par1EntityLiving, boolean par2)
/*    */   {
/* 17 */     super(par1EntityLiving);
/* 18 */     this.theEntity = par1EntityLiving;
/* 19 */     this.field_75361_i = par2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 27 */     return (this.field_75361_i) && (this.field_75360_j > 0) && (super.func_75253_b());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 36 */     this.field_75360_j = 20;
/* 37 */     if (this.targetDoor == Blocks.field_150466_ao) {
/* 38 */       ((BlockDoor)this.targetDoor).func_150014_a(this.theEntity.field_70170_p, this.entityPosX, this.entityPosY, this.entityPosZ, true);
/*    */     } else {
/* 40 */       int var10 = this.theEntity.field_70170_p.func_72805_g(this.entityPosX, this.entityPosY, this.entityPosZ);
/* 41 */       if (!BlockFenceGate.func_149896_b(var10)) {
/* 42 */         int var11 = (net.minecraft.util.MathHelper.func_76128_c(this.theEntity.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3) % 4;
/* 43 */         int var12 = BlockFenceGate.func_149895_l(var10);
/*    */         
/* 45 */         if (var12 == (var11 + 2) % 4)
/*    */         {
/* 47 */           var10 = var11;
/*    */         }
/*    */         
/* 50 */         this.theEntity.field_70170_p.func_147465_d(this.entityPosX, this.entityPosY, this.entityPosZ, this.targetDoor, var10 | 0x4, 3);
/* 51 */         this.theEntity.field_70170_p.func_72889_a((EntityPlayer)null, 1003, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75251_c()
/*    */   {
/* 61 */     if (this.field_75361_i)
/*    */     {
/* 63 */       if (this.targetDoor == Blocks.field_150466_ao) {
/* 64 */         ((BlockDoor)this.targetDoor).func_150014_a(this.theEntity.field_70170_p, this.entityPosX, this.entityPosY, this.entityPosZ, false);
/*    */       } else {
/* 66 */         int var10 = this.theEntity.field_70170_p.func_72805_g(this.entityPosX, this.entityPosY, this.entityPosZ);
/* 67 */         if (BlockFenceGate.func_149896_b(var10)) {
/* 68 */           this.theEntity.field_70170_p.func_147465_d(this.entityPosX, this.entityPosY, this.entityPosZ, this.targetDoor, var10 & 0xFFFFFFFB, 3);
/* 69 */           this.theEntity.field_70170_p.func_72889_a((EntityPlayer)null, 1003, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75246_d()
/*    */   {
/* 80 */     this.field_75360_j -= 1;
/* 81 */     super.func_75246_d();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/misc/AIOpenDoor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */