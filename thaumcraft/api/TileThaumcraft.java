/*    */ package thaumcraft.api;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileThaumcraft
/*    */   extends TileEntity
/*    */ {
/*    */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*    */   {
/* 25 */     super.func_145839_a(nbttagcompound);
/* 26 */     readCustomNBT(nbttagcompound);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*    */   {
/* 37 */     super.func_145841_b(nbttagcompound);
/* 38 */     writeCustomNBT(nbttagcompound);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public Packet func_145844_m()
/*    */   {
/* 49 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 50 */     writeCustomNBT(nbttagcompound);
/* 51 */     return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 64537, nbttagcompound);
/*    */   }
/*    */   
/*    */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*    */   {
/* 56 */     super.onDataPacket(net, pkt);
/* 57 */     readCustomNBT(pkt.func_148857_g());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/TileThaumcraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */