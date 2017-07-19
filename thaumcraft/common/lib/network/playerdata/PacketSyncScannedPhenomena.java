/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ 
/*    */ public class PacketSyncScannedPhenomena implements IMessage, IMessageHandler<PacketSyncScannedPhenomena, IMessage>
/*    */ {
/* 21 */   protected ArrayList<String> data = new ArrayList();
/*    */   
/*    */   public PacketSyncScannedPhenomena() {}
/*    */   
/* 25 */   public void toBytes(ByteBuf buffer) { if ((this.data != null) && (this.data.size() > 0)) {
/* 26 */       buffer.writeShort(this.data.size());
/* 27 */       for (String s : this.data) if (s != null)
/* 28 */           ByteBufUtils.writeUTF8String(buffer, s);
/*    */     } else {
/* 30 */       buffer.writeShort(0);
/*    */     }
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 35 */     short size = buffer.readShort();
/* 36 */     this.data = new ArrayList();
/* 37 */     for (int a = 0; a < size; a++) {
/* 38 */       this.data.add(ByteBufUtils.readUTF8String(buffer));
/*    */     }
/*    */   }
/*    */   
/*    */   public PacketSyncScannedPhenomena(EntityPlayer player) {
/* 43 */     this.data = ((ArrayList)Thaumcraft.proxy.getScannedPhenomena().get(player.func_70005_c_()));
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketSyncScannedPhenomena message, MessageContext ctx) {
/*    */     String key;
/* 49 */     for (Iterator i$ = message.data.iterator(); i$.hasNext(); Thaumcraft.proxy.getResearchManager().completeScannedPhenomena(Minecraft.func_71410_x().field_71439_g, key)) key = (String)i$.next();
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketSyncScannedPhenomena.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */