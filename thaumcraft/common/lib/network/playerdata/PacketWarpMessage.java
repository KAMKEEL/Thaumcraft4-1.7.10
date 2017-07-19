/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import thaumcraft.client.lib.PlayerNotifications;
/*    */ 
/*    */ public class PacketWarpMessage implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketWarpMessage, IMessage>
/*    */ {
/* 16 */   protected int data = 0;
/* 17 */   protected byte type = 0;
/*    */   
/*    */   public PacketWarpMessage() {}
/*    */   
/*    */   public PacketWarpMessage(EntityPlayer player, byte type, int change)
/*    */   {
/* 23 */     this.data = change;
/* 24 */     this.type = type;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 29 */     buffer.writeInt(this.data);
/* 30 */     buffer.writeByte(this.type);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 35 */     this.data = buffer.readInt();
/* 36 */     this.type = buffer.readByte();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketWarpMessage message, MessageContext ctx)
/*    */   {
/* 42 */     if (message.data != 0) {
/* 43 */       if ((message.type == 0) && (message.data > 0)) {
/* 44 */         String text = StatCollector.func_74838_a("tc.addwarp");
/* 45 */         if (message.data < 0) {
/* 46 */           text = StatCollector.func_74838_a("tc.removewarp");
/*    */         } else {
/* 48 */           Minecraft.func_71410_x().field_71439_g.func_85030_a("thaumcraft:whispers", 0.5F, 1.0F);
/*    */         }
/* 50 */         PlayerNotifications.addNotification(text);
/*    */ 
/*    */       }
/* 53 */       else if (message.type == 1) {
/* 54 */         String text = StatCollector.func_74838_a("tc.addwarpsticky");
/* 55 */         if (message.data < 0) {
/* 56 */           text = StatCollector.func_74838_a("tc.removewarpsticky");
/*    */         } else {
/* 58 */           Minecraft.func_71410_x().field_71439_g.func_85030_a("thaumcraft:whispers", 0.5F, 1.0F);
/*    */         }
/* 60 */         PlayerNotifications.addNotification(text);
/*    */       }
/* 62 */       else if (message.data > 0) {
/* 63 */         String text = StatCollector.func_74838_a("tc.addwarptemp");
/* 64 */         if (message.data < 0) {
/* 65 */           text = StatCollector.func_74838_a("tc.removewarptemp");
/*    */         }
/* 67 */         PlayerNotifications.addNotification(text);
/*    */       }
/*    */     }
/*    */     
/* 71 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketWarpMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */