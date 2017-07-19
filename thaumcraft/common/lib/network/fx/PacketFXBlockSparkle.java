/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketFXBlockSparkle implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXBlockSparkle, IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private int color;
/*    */   
/*    */   public PacketFXBlockSparkle() {}
/*    */   
/*    */   public PacketFXBlockSparkle(int x, int y, int z, int color)
/*    */   {
/* 18 */     this.x = x;
/* 19 */     this.y = y;
/* 20 */     this.z = z;
/* 21 */     this.color = color;
/*    */   }
/*    */   
/*    */ 
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 27 */     buffer.writeInt(this.x);
/* 28 */     buffer.writeInt(this.y);
/* 29 */     buffer.writeInt(this.z);
/* 30 */     buffer.writeInt(this.color);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 35 */     this.x = buffer.readInt();
/* 36 */     this.y = buffer.readInt();
/* 37 */     this.z = buffer.readInt();
/* 38 */     this.color = buffer.readInt();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketFXBlockSparkle message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 43 */     Thaumcraft.proxy.blockSparkle(Thaumcraft.proxy.getClientWorld(), message.x, message.y, message.z, message.color, 7);
/* 44 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXBlockSparkle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */