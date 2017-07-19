/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ public class PacketFXBlockZap implements cpw.mods.fml.common.network.simpleimpl.IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXBlockZap, cpw.mods.fml.common.network.simpleimpl.IMessage> {
/*    */   private float x;
/*    */   private float y;
/*    */   private float z;
/*    */   private float dx;
/*    */   private float dy;
/*    */   private float dz;
/*    */   
/*    */   public PacketFXBlockZap() {}
/*    */   
/*    */   public PacketFXBlockZap(float x, float y, float z, float dx, float dy, float dz) {
/* 16 */     this.x = x;
/* 17 */     this.y = y;
/* 18 */     this.z = z;
/* 19 */     this.dx = dx;
/* 20 */     this.dy = dy;
/* 21 */     this.dz = dz;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 26 */     buffer.writeFloat(this.x);
/* 27 */     buffer.writeFloat(this.y);
/* 28 */     buffer.writeFloat(this.z);
/* 29 */     buffer.writeFloat(this.dx);
/* 30 */     buffer.writeFloat(this.dy);
/* 31 */     buffer.writeFloat(this.dz);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 36 */     this.x = buffer.readFloat();
/* 37 */     this.y = buffer.readFloat();
/* 38 */     this.z = buffer.readFloat();
/* 39 */     this.dx = buffer.readFloat();
/* 40 */     this.dy = buffer.readFloat();
/* 41 */     this.dz = buffer.readFloat();
/*    */   }
/*    */   
/*    */   public cpw.mods.fml.common.network.simpleimpl.IMessage onMessage(PacketFXBlockZap message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 46 */     thaumcraft.common.Thaumcraft.proxy.nodeBolt(thaumcraft.common.Thaumcraft.proxy.getClientWorld(), message.x, message.y, message.z, message.dx, message.dy, message.dz);
/* 47 */     thaumcraft.common.Thaumcraft.proxy.getClientWorld().func_72980_b(message.x, message.y, message.z, "thaumcraft:zap", 0.1F, 1.0F + thaumcraft.common.Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat() * 0.2F, false);
/* 48 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXBlockZap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */