/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class TileWardingStoneFence extends TileEntity
/*    */ {
/* 10 */   int count = 0;
/*    */   
/*    */   public boolean canUpdate()
/*    */   {
/* 14 */     return true;
/*    */   }
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 19 */     if (!this.field_145850_b.field_72995_K) {
/* 20 */       if (this.count == 0) this.count = this.field_145850_b.field_73012_v.nextInt(100);
/* 21 */       if ((++this.count % 100 == 0) && 
/* 22 */         ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) != ConfigBlocks.blockCosmeticSolid) || (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) != 3)) && ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e) != ConfigBlocks.blockCosmeticSolid) || (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e) != 3)))
/*    */       {
/*    */ 
/*    */ 
/* 26 */         this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileWardingStoneFence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */