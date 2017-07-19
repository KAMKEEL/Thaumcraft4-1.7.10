/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class TileNodeStabilizer extends TileEntity
/*    */ {
/* 13 */   public int count = 0;
/* 14 */   public int lock = 0;
/*    */   
/*    */   public TileNodeStabilizer(int metadata) {
/* 17 */     this.lock = (metadata == 9 ? 1 : 2);
/*    */   }
/*    */   
/*    */ 
/*    */   public TileNodeStabilizer() {}
/*    */   
/*    */ 
/*    */   public boolean canUpdate()
/*    */   {
/* 26 */     return true;
/*    */   }
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 31 */     super.func_145845_h();
/* 32 */     if ((this.field_145850_b.field_72995_K) && (this.field_145848_d < this.field_145850_b.field_73011_w.getHeight() - 1)) {
/* 33 */       int md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 34 */       if ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == ConfigBlocks.blockAiry) && ((md == 0) || (md == 5)) && (!this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e)))
/*    */       {
/*    */ 
/* 37 */         if (this.count < 37) this.count += 1;
/*    */       }
/* 39 */       else if (this.count > 0) { this.count -= 1;
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 47 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 2, this.field_145849_e + 1);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileNodeStabilizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */