/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketSyncScannedEntities implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketSyncScannedEntities, IMessage>
/*    */ {
/*    */   public PacketSyncScannedEntities() {}
/*    */   
/*    */   public PacketSyncScannedEntities(EntityPlayer player)
/*    */   {
/* 22 */     this.data = ((ArrayList)Thaumcraft.proxy.getScannedEntities().get(player.func_70005_c_()));
/*    */   }
/*    */   
/* 25 */   protected ArrayList<String> data = new ArrayList();
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 29 */     if ((this.data != null) && (this.data.size() > 0)) {
/* 30 */       buffer.writeShort(this.data.size());
/* 31 */       for (String s : this.data) if (s != null)
/* 32 */           ByteBufUtils.writeUTF8String(buffer, s);
/*    */     } else {
/* 34 */       buffer.writeShort(0);
/*    */     }
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 39 */     short size = buffer.readShort();
/* 40 */     this.data = new ArrayList();
/* 41 */     for (int a = 0; a < size; a++) {
/* 42 */       this.data.add(ByteBufUtils.readUTF8String(buffer));
/*    */     }
/*    */   }
/*    */   
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public IMessage onMessage(PacketSyncScannedEntities message, MessageContext ctx) {
/*    */     String key;
/* 49 */     for (Iterator i$ = message.data.iterator(); i$.hasNext(); Thaumcraft.proxy.getResearchManager().completeScannedEntity(Minecraft.func_71410_x().field_71439_g, key)) key = (String)i$.next();
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketSyncScannedEntities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */