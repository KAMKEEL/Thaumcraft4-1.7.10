/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ 
/*    */ public class TileEldritchNothing
/*    */   extends TileEntity
/*    */ {
/*    */   public boolean canUpdate()
/*    */   {
/* 13 */     return false;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 19 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileEldritchNothing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */