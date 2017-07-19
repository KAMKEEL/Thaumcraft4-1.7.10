/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import thaumcraft.common.items.armor.Hover;
/*    */ 
/*    */ public class PacketFlyToServer implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFlyToServer, IMessage>
/*    */ {
/*    */   private int playerid;
/*    */   private boolean hover;
/*    */   
/*    */   public PacketFlyToServer() {}
/*    */   
/*    */   public PacketFlyToServer(EntityPlayer player, boolean hover)
/*    */   {
/* 18 */     this.playerid = player.func_145782_y();
/* 19 */     this.hover = hover;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 24 */     buffer.writeInt(this.playerid);
/* 25 */     buffer.writeBoolean(this.hover);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 30 */     this.playerid = buffer.readInt();
/* 31 */     this.hover = buffer.readBoolean();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketFlyToServer message, MessageContext ctx)
/*    */   {
/* 36 */     Hover.setHover(message.playerid, message.hover);
/* 37 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/misc/PacketFlyToServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */