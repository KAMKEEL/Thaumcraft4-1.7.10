/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ 
/*    */ public class PacketSyncResearch implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketSyncResearch, IMessage>
/*    */ {
/*    */   public PacketSyncResearch() {}
/*    */   
/*    */   public PacketSyncResearch(EntityPlayer player)
/*    */   {
/* 24 */     this.data = ResearchManager.getResearchForPlayer(player.func_70005_c_());
/*    */   }
/*    */   
/* 27 */   protected ArrayList<String> data = new ArrayList();
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 31 */     if ((this.data != null) && (this.data.size() > 0)) {
/* 32 */       buffer.writeShort(this.data.size());
/* 33 */       for (String s : this.data) if (s != null)
/* 34 */           ByteBufUtils.writeUTF8String(buffer, s);
/*    */     } else {
/* 36 */       buffer.writeShort(0);
/*    */     }
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 41 */     short size = buffer.readShort();
/* 42 */     this.data = new ArrayList();
/* 43 */     for (int a = 0; a < size; a++) {
/* 44 */       this.data.add(ByteBufUtils.readUTF8String(buffer));
/*    */     }
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketSyncResearch message, MessageContext ctx) {
/*    */     String key;
/* 51 */     for (Iterator i$ = message.data.iterator(); i$.hasNext(); Thaumcraft.proxy.getResearchManager().completeResearch(Minecraft.func_71410_x().field_71439_g, key)) key = (String)i$.next();
/* 52 */     thaumcraft.client.gui.GuiResearchBrowser.completedResearch.put(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), message.data);
/* 53 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketSyncResearch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */