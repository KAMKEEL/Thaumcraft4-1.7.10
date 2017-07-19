/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class TileWardingStone extends TileEntity
/*    */ {
/* 16 */   int count = 0;
/*    */   
/*    */   public boolean gettingPower() {
/* 19 */     return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */   }
/*    */   
/*    */   public boolean canUpdate()
/*    */   {
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 29 */     if (!this.field_145850_b.field_72995_K) {
/* 30 */       if (this.count == 0) { this.count = this.field_145850_b.field_73012_v.nextInt(100);
/*    */       }
/* 32 */       if ((this.count % 5 == 0) && (!gettingPower())) {
/* 33 */         List<EntityLivingBase> targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 3, this.field_145849_e + 1).func_72314_b(0.1D, 0.1D, 0.1D));
/*    */         
/*    */ 
/*    */ 
/* 37 */         if (targets.size() > 0) {
/* 38 */           for (EntityLivingBase e : targets) {
/* 39 */             if ((!e.field_70122_E) && (!(e instanceof EntityPlayer))) {
/* 40 */               e.func_70024_g(-MathHelper.func_76126_a((e.field_70177_z + 180.0F) * 3.1415927F / 180.0F) * 0.2F, -0.1D, MathHelper.func_76134_b((e.field_70177_z + 180.0F) * 3.1415927F / 180.0F) * 0.2F);
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/* 45 */       if (++this.count % 100 == 0) {
/* 46 */         if (((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) != ConfigBlocks.blockAiry) || (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) != 3)) && (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).isReplaceable(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)))
/*    */         {
/*    */ 
/* 49 */           this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockAiry, 4, 3);
/*    */         }
/* 51 */         if (((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e) != ConfigBlocks.blockAiry) || (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e) != 3)) && (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e).isReplaceable(this.field_145850_b, this.field_145851_c, this.field_145848_d + 2, this.field_145849_e)))
/*    */         {
/*    */ 
/* 54 */           this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e, ConfigBlocks.blockAiry, 4, 3);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileWardingStone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */