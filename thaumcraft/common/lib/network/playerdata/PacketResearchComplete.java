/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ import thaumcraft.api.research.ResearchItem;
/*    */ import thaumcraft.client.gui.GuiResearchBrowser;
/*    */ import thaumcraft.client.gui.GuiResearchPopup;
/*    */ import thaumcraft.client.lib.ClientTickEventsFML;
/*    */ import thaumcraft.client.lib.PlayerNotifications;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketResearchComplete implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketResearchComplete, IMessage>
/*    */ {
/*    */   private String key;
/*    */   
/*    */   public PacketResearchComplete() {}
/*    */   
/*    */   public PacketResearchComplete(String key)
/*    */   {
/* 28 */     this.key = key;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 33 */     ByteBufUtils.writeUTF8String(buffer, this.key);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 38 */     this.key = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */   
/*    */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public IMessage onMessage(PacketResearchComplete message, MessageContext ctx)
/*    */   {
/* 44 */     if ((message.key != null) && (message.key.length() > 0)) {
/* 45 */       Thaumcraft.proxy.getResearchManager().completeResearch(Minecraft.func_71410_x().field_71439_g, message.key);
/* 46 */       if (message.key.startsWith("@")) {
/* 47 */         String text = net.minecraft.util.StatCollector.func_74838_a("tc.addclue");
/* 48 */         PlayerNotifications.addNotification("§a" + text);
/* 49 */         Minecraft.func_71410_x().field_71439_g.func_85030_a("thaumcraft:learn", 0.2F, 1.0F + Minecraft.func_71410_x().field_71439_g.field_70170_p.field_73012_v.nextFloat() * 0.1F);
/* 50 */       } else if (!ResearchCategories.getResearch(message.key).isVirtual()) {
/* 51 */         ClientTickEventsFML.researchPopup.queueResearchInformation(ResearchCategories.getResearch(message.key));
/* 52 */         GuiResearchBrowser.highlightedItem.add(message.key);
/* 53 */         GuiResearchBrowser.highlightedItem.add(ResearchCategories.getResearch(message.key).category);
/*    */       }
/* 55 */       if ((Minecraft.func_71410_x().field_71462_r instanceof GuiResearchBrowser)) {
/* 56 */         ArrayList<String> al = (ArrayList)GuiResearchBrowser.completedResearch.get(Minecraft.func_71410_x().field_71439_g.func_70005_c_());
/* 57 */         if (al == null) al = new ArrayList();
/* 58 */         al.add(message.key);
/* 59 */         GuiResearchBrowser.completedResearch.put(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), al);
/* 60 */         ((GuiResearchBrowser)Minecraft.func_71410_x().field_71462_r).updateResearch();
/*    */       }
/*    */     }
/* 63 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketResearchComplete.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */