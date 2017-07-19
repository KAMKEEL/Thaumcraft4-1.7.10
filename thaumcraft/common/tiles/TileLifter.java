/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.passive.EntityHorse;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class TileLifter extends TileEntity
/*    */ {
/* 16 */   private int counter = 0;
/* 17 */   public int rangeAbove = 0;
/* 18 */   public boolean requiresUpdate = true;
/* 19 */   public boolean lastPowerState = false;
/*    */   
/*    */   public boolean canUpdate()
/*    */   {
/* 23 */     return true;
/*    */   }
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 28 */     super.func_145845_h();
/* 29 */     this.counter += 1;
/* 30 */     if ((this.requiresUpdate) || (this.counter % 100 == 0))
/*    */     {
/* 32 */       this.lastPowerState = gettingPower();
/*    */       
/* 34 */       this.requiresUpdate = false;
/*    */       
/* 36 */       int max = 10;
/* 37 */       int count = 1;
/* 38 */       while ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - count, this.field_145849_e) == ConfigBlocks.blockLifter) && (!this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d - count, this.field_145849_e)))
/*    */       {
/* 40 */         count++;
/* 41 */         max += 10;
/*    */       }
/* 43 */       this.rangeAbove = 0;
/* 44 */       while ((this.rangeAbove < max) && (!this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1 + this.rangeAbove, this.field_145849_e).func_149662_c())) {
/* 45 */         this.rangeAbove += 1;
/*    */       }
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 51 */     if ((this.rangeAbove > 0) && (!gettingPower())) {
/* 52 */       List<Entity> targets = this.field_145850_b.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1 + this.rangeAbove, this.field_145849_e + 1));
/*    */       
/*    */ 
/*    */ 
/* 56 */       if (targets.size() > 0) {
/* 57 */         for (Entity e : targets) {
/* 58 */           if (((e instanceof EntityItem)) || (e.func_70104_M()) || ((e instanceof EntityHorse))) {
/* 59 */             if (Thaumcraft.proxy.isShiftKeyDown()) {
/* 60 */               if (e.field_70181_x < 0.0D) e.field_70181_x *= 0.8999999761581421D;
/*    */             }
/* 62 */             else if (e.field_70181_x < 0.3499999940395355D) { e.field_70181_x += 0.10000000149011612D;
/*    */             }
/* 64 */             e.field_70143_R = 0.0F;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean gettingPower() {
/* 72 */     return (this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e)) || (this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileLifter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */