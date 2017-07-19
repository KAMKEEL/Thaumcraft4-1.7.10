/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class PacketBiomeChange implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketBiomeChange, IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int z;
/*    */   private short biome;
/*    */   
/*    */   public PacketBiomeChange() {}
/*    */   
/*    */   public PacketBiomeChange(int x, int z, short biome)
/*    */   {
/* 19 */     this.x = x;
/* 20 */     this.z = z;
/* 21 */     this.biome = biome;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 26 */     buffer.writeInt(this.x);
/* 27 */     buffer.writeInt(this.z);
/* 28 */     buffer.writeShort(this.biome);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 33 */     this.x = buffer.readInt();
/* 34 */     this.z = buffer.readInt();
/* 35 */     this.biome = buffer.readShort();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketBiomeChange message, MessageContext ctx)
/*    */   {
/* 40 */     Utils.setBiomeAt(Thaumcraft.proxy.getClientWorld(), message.x, message.z, net.minecraft.world.biome.BiomeGenBase.func_150568_d(message.biome));
/* 41 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/misc/PacketBiomeChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */