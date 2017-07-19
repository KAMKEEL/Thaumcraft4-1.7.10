/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.Config;
/*    */ 
/*    */ public class PacketConfig implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketConfig, IMessage>
/*    */ {
/*    */   boolean b1;
/*    */   boolean b2;
/*    */   boolean b3;
/*    */   boolean b4;
/*    */   boolean b5;
/*    */   byte by1;
/*    */   int bi2;
/*    */   
/*    */   public void toBytes(ByteBuf dos)
/*    */   {
/* 20 */     dos.writeBoolean(Config.allowCheatSheet);
/* 21 */     dos.writeBoolean(Config.wardedStone);
/* 22 */     dos.writeBoolean(Config.allowMirrors);
/* 23 */     dos.writeBoolean(Config.hardNode);
/* 24 */     dos.writeBoolean(Config.wuss);
/* 25 */     dos.writeByte(Config.researchDifficulty);
/* 26 */     dos.writeInt(Config.aspectTotalCap);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf dat)
/*    */   {
/* 31 */     this.b1 = dat.readBoolean();
/* 32 */     this.b2 = dat.readBoolean();
/* 33 */     this.b3 = dat.readBoolean();
/* 34 */     this.b4 = dat.readBoolean();
/* 35 */     this.b5 = dat.readBoolean();
/* 36 */     this.by1 = dat.readByte();
/* 37 */     this.bi2 = dat.readInt();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketConfig message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 42 */     Config.allowCheatSheet = message.b1;
/* 43 */     Config.wardedStone = message.b2;
/* 44 */     Config.allowMirrors = message.b3;
/* 45 */     Config.hardNode = message.b4;
/* 46 */     Config.wuss = message.b5;
/* 47 */     Config.researchDifficulty = message.by1;
/* 48 */     Config.aspectTotalCap = message.bi2;
/*    */     
/* 50 */     Thaumcraft.log.info("Client received server config settings.");
/* 51 */     Thaumcraft.log.info("CHEAT_SHEET[" + Config.allowCheatSheet + "], WARDED_STONE[" + Config.wardedStone + "], MIRRORS[" + Config.allowMirrors + "], HARD_NODES[" + Config.hardNode + "], WUSS_MODE[" + Config.wuss + "], RESEARCH_DIFFICULTY[" + Config.researchDifficulty + "], ASPECT_TOTAL_CAP[" + Config.aspectTotalCap);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 59 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/misc/PacketConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */