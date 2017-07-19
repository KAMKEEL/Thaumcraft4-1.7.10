/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.api.TileThaumcraft;
/*    */ 
/*    */ 
/*    */ public class TileBrainbox
/*    */   extends TileThaumcraft
/*    */ {
/* 11 */   public ForgeDirection facing = ForgeDirection.UNKNOWN;
/*    */   
/*    */ 
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 16 */     this.facing = ForgeDirection.getOrientation(nbttagcompound.func_74771_c("facing"));
/*    */   }
/*    */   
/*    */ 
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 22 */     nbttagcompound.func_74774_a("facing", (byte)this.facing.ordinal());
/*    */   }
/*    */   
/*    */   public boolean canUpdate()
/*    */   {
/* 27 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileBrainbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */