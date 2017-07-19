/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.client.gui.GuiResearchBrowser;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*    */ 
/*    */ public class PacketAspectDiscovery implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketAspectDiscovery, IMessage>
/*    */ {
/*    */   private String key;
/*    */   
/*    */   public PacketAspectDiscovery() {}
/*    */   
/*    */   public PacketAspectDiscovery(String key)
/*    */   {
/* 24 */     this.key = key;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 29 */     ByteBufUtils.writeUTF8String(buffer, this.key);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 34 */     this.key = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */   
/*    */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public IMessage onMessage(PacketAspectDiscovery message, MessageContext ctx)
/*    */   {
/* 40 */     if (Aspect.getAspect(message.key) != null) {
/* 41 */       Thaumcraft.proxy.getPlayerKnowledge().addDiscoveredAspect(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), Aspect.getAspect(message.key));
/* 42 */       String text = net.minecraft.util.StatCollector.func_74838_a("tc.addaspectdiscovery");
/* 43 */       text = text.replaceAll("%n", Aspect.getAspect(message.key).getName());
/* 44 */       thaumcraft.client.lib.PlayerNotifications.addNotification("§6" + text, Aspect.getAspect(message.key));
/* 45 */       Minecraft.func_71410_x().field_71439_g.func_85030_a("random.orb", 0.2F, 0.5F + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat() * 0.2F);
/*    */       
/* 47 */       GuiResearchBrowser.highlightedItem.add("ASPECTS");
/* 48 */       GuiResearchBrowser.highlightedItem.add("BASICS");
/*    */     }
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketAspectDiscovery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */