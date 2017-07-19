/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import thaumcraft.api.TileThaumcraft;
/*    */ 
/*    */ public class TileInfusionPillar
/*    */   extends TileThaumcraft
/*    */ {
/* 12 */   public byte orientation = 0;
/*    */   
/*    */   public boolean canUpdate()
/*    */   {
/* 16 */     return false;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 22 */     return AxisAlignedBB.func_72330_a(this.field_145851_c - 1, this.field_145848_d - 1, this.field_145849_e - 1, this.field_145851_c + 1, this.field_145848_d + 2, this.field_145849_e + 1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 30 */     this.orientation = nbttagcompound.func_74771_c("orientation");
/*    */   }
/*    */   
/*    */ 
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 36 */     nbttagcompound.func_74774_a("orientation", this.orientation);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileInfusionPillar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */