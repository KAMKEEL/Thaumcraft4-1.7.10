/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ public class TileCrystal extends thaumcraft.api.TileThaumcraft {
/*    */   public short orientation;
/*    */   
/*    */   public TileCrystal() {
/*  9 */     this.orientation = 1;
/*    */   }
/*    */   
/*    */ 
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 15 */     super.readCustomNBT(nbttagcompound);
/* 16 */     this.orientation = nbttagcompound.func_74765_d("orientation");
/*    */   }
/*    */   
/*    */ 
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 22 */     super.writeCustomNBT(nbttagcompound);
/* 23 */     nbttagcompound.func_74777_a("orientation", this.orientation);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileCrystal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */