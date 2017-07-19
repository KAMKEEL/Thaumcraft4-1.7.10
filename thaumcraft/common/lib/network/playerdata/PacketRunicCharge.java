/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.events.EventHandlerRunic;
/*    */ 
/*    */ public class PacketRunicCharge implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketRunicCharge, IMessage>
/*    */ {
/*    */   private int id;
/*    */   private short amount;
/*    */   private short max;
/*    */   
/*    */   public PacketRunicCharge() {}
/*    */   
/*    */   public PacketRunicCharge(EntityPlayer player, Short amount, int max)
/*    */   {
/* 21 */     this.id = player.func_145782_y();
/* 22 */     this.amount = amount.shortValue();
/* 23 */     this.max = ((short)max);
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 28 */     buffer.writeInt(this.id);
/* 29 */     buffer.writeShort(this.amount);
/* 30 */     buffer.writeShort(this.max);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 35 */     this.id = buffer.readInt();
/* 36 */     this.amount = buffer.readShort();
/* 37 */     this.max = buffer.readShort();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketRunicCharge message, MessageContext ctx)
/*    */   {
/* 42 */     Thaumcraft.instance.runicEventHandler.runicCharge.put(Integer.valueOf(message.id), Integer.valueOf(message.amount));
/* 43 */     Thaumcraft.instance.runicEventHandler.runicInfo.put(Integer.valueOf(message.id), new Integer[] { Integer.valueOf(message.max), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0) });
/* 44 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketRunicCharge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */