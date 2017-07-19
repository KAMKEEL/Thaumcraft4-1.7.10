/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class TileArcanePressurePlate
/*    */   extends TileOwned
/*    */ {
/* 11 */   public byte setting = 0;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean canUpdate()
/*    */   {
/* 19 */     return false;
/*    */   }
/*    */   
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 24 */     super.readCustomNBT(nbttagcompound);
/* 25 */     this.setting = nbttagcompound.func_74771_c("setting");
/*    */   }
/*    */   
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 30 */     super.writeCustomNBT(nbttagcompound);
/* 31 */     nbttagcompound.func_74774_a("setting", this.setting);
/*    */   }
/*    */   
/*    */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*    */   {
/* 36 */     super.onDataPacket(net, pkt);
/* 37 */     if ((this.field_145850_b != null) && (this.field_145850_b.field_72995_K)) {
/* 38 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileArcanePressurePlate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */