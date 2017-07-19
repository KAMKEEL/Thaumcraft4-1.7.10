/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import thaumcraft.common.tiles.TileArcaneBore;
/*    */ 
/*    */ public class PacketBoreDig implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketBoreDig, IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private int digloc;
/*    */   
/*    */   public PacketBoreDig() {}
/*    */   
/*    */   public PacketBoreDig(int x, int y, int z, int digloc)
/*    */   {
/* 18 */     this.x = x;
/* 19 */     this.y = y;
/* 20 */     this.z = z;
/* 21 */     this.digloc = digloc;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 26 */     buffer.writeInt(this.x);
/* 27 */     buffer.writeInt(this.y);
/* 28 */     buffer.writeInt(this.z);
/* 29 */     buffer.writeInt(this.digloc);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 34 */     this.x = buffer.readInt();
/* 35 */     this.y = buffer.readInt();
/* 36 */     this.z = buffer.readInt();
/* 37 */     this.digloc = buffer.readInt();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketBoreDig message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 42 */     net.minecraft.tileentity.TileEntity tile = thaumcraft.common.Thaumcraft.proxy.getClientWorld().func_147438_o(message.x, message.y, message.z);
/* 43 */     if ((tile != null) && ((tile instanceof TileArcaneBore))) {
/* 44 */       ((TileArcaneBore)tile).getDigEvent(message.digloc);
/*    */     }
/* 46 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/misc/PacketBoreDig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */