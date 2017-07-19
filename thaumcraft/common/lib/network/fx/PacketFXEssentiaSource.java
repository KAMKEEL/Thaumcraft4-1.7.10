/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.HashMap;
/*    */ import thaumcraft.common.lib.events.EssentiaHandler;
/*    */ 
/*    */ public class PacketFXEssentiaSource implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXEssentiaSource, IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private byte dx;
/*    */   private byte dy;
/*    */   private byte dz;
/*    */   private int color;
/*    */   
/*    */   public PacketFXEssentiaSource() {}
/*    */   
/*    */   public PacketFXEssentiaSource(int x, int y, int z, byte dx, byte dy, byte dz, int color)
/*    */   {
/* 22 */     this.x = x;
/* 23 */     this.y = y;
/* 24 */     this.z = z;
/* 25 */     this.dx = dx;
/* 26 */     this.dy = dy;
/* 27 */     this.dz = dz;
/* 28 */     this.color = color;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 33 */     buffer.writeInt(this.x);
/* 34 */     buffer.writeInt(this.y);
/* 35 */     buffer.writeInt(this.z);
/* 36 */     buffer.writeInt(this.color);
/* 37 */     buffer.writeByte(this.dx);
/* 38 */     buffer.writeByte(this.dy);
/* 39 */     buffer.writeByte(this.dz);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 44 */     this.x = buffer.readInt();
/* 45 */     this.y = buffer.readInt();
/* 46 */     this.z = buffer.readInt();
/* 47 */     this.color = buffer.readInt();
/* 48 */     this.dx = buffer.readByte();
/* 49 */     this.dy = buffer.readByte();
/* 50 */     this.dz = buffer.readByte();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketFXEssentiaSource message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 55 */     int tx = message.x - message.dx;
/* 56 */     int ty = message.y - message.dy;
/* 57 */     int tz = message.z - message.dz;
/* 58 */     String key = message.x + ":" + message.y + ":" + message.z + ":" + tx + ":" + ty + ":" + tz + ":" + message.color;
/* 59 */     if (EssentiaHandler.sourceFX.containsKey(key)) {
/* 60 */       thaumcraft.common.lib.events.EssentiaHandler.EssentiaSourceFX sf = (thaumcraft.common.lib.events.EssentiaHandler.EssentiaSourceFX)EssentiaHandler.sourceFX.get(key);
/* 61 */       sf.ticks = 15;
/* 62 */       EssentiaHandler.sourceFX.remove(key);
/* 63 */       EssentiaHandler.sourceFX.put(key, sf);
/*    */     } else {
/* 65 */       EssentiaHandler.sourceFX.put(key, new thaumcraft.common.lib.events.EssentiaHandler.EssentiaSourceFX(new net.minecraft.util.ChunkCoordinates(message.x, message.y, message.z), new net.minecraft.util.ChunkCoordinates(tx, ty, tz), 15, message.color));
/*    */     }
/*    */     
/* 68 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXEssentiaSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */