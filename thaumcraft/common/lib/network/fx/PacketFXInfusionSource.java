/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.tiles.TileInfusionMatrix;
/*    */ import thaumcraft.common.tiles.TileInfusionMatrix.SourceFX;
/*    */ 
/*    */ public class PacketFXInfusionSource implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXInfusionSource, IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private byte dx;
/*    */   private byte dy;
/*    */   private byte dz;
/*    */   private int color;
/*    */   
/*    */   public PacketFXInfusionSource() {}
/*    */   
/*    */   public PacketFXInfusionSource(int x, int y, int z, byte dx, byte dy, byte dz, int color)
/*    */   {
/* 25 */     this.x = x;
/* 26 */     this.y = y;
/* 27 */     this.z = z;
/* 28 */     this.dx = dx;
/* 29 */     this.dy = dy;
/* 30 */     this.dz = dz;
/* 31 */     this.color = color;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 36 */     buffer.writeInt(this.x);
/* 37 */     buffer.writeInt(this.y);
/* 38 */     buffer.writeInt(this.z);
/* 39 */     buffer.writeInt(this.color);
/* 40 */     buffer.writeByte(this.dx);
/* 41 */     buffer.writeByte(this.dy);
/* 42 */     buffer.writeByte(this.dz);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 47 */     this.x = buffer.readInt();
/* 48 */     this.y = buffer.readInt();
/* 49 */     this.z = buffer.readInt();
/* 50 */     this.color = buffer.readInt();
/* 51 */     this.dx = buffer.readByte();
/* 52 */     this.dy = buffer.readByte();
/* 53 */     this.dz = buffer.readByte();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketFXInfusionSource message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 58 */     int tx = message.x - message.dx;
/* 59 */     int ty = message.y - message.dy;
/* 60 */     int tz = message.z - message.dz;
/* 61 */     String key = tx + ":" + ty + ":" + tz + ":" + message.color;
/* 62 */     net.minecraft.tileentity.TileEntity tile = Thaumcraft.proxy.getClientWorld().func_147438_o(message.x, message.y, message.z);
/* 63 */     if ((tile != null) && ((tile instanceof TileInfusionMatrix))) {
/* 64 */       int count = 15;
/* 65 */       if ((Thaumcraft.proxy.getClientWorld().func_147438_o(tx, ty, tz) != null) && ((Thaumcraft.proxy.getClientWorld().func_147438_o(tx, ty, tz) instanceof thaumcraft.common.tiles.TilePedestal)))
/*    */       {
/* 67 */         count = 60; }
/* 68 */       TileInfusionMatrix is = (TileInfusionMatrix)tile;
/* 69 */       if (is.sourceFX.containsKey(key)) {
/* 70 */         TileInfusionMatrix.SourceFX sf = (TileInfusionMatrix.SourceFX)is.sourceFX.get(key);
/* 71 */         sf.ticks = count;
/* 72 */         is.sourceFX.put(key, sf);
/*    */       } else {
/* 74 */         TileInfusionMatrix tmp232_230 = is;tmp232_230.getClass();is.sourceFX.put(key, new TileInfusionMatrix.SourceFX(tmp232_230, new net.minecraft.util.ChunkCoordinates(tx, ty, tz), count, message.color));
/*    */       }
/*    */     }
/*    */     
/* 78 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXInfusionSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */