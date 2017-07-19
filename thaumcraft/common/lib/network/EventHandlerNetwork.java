/*    */ package thaumcraft.common.lib.network;
/*    */ 
/*    */ import cpw.mods.fml.common.FMLCommonHandler;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
/*    */ import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
/*    */ import cpw.mods.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
/*    */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import thaumcraft.client.gui.GuiResearchBrowser;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncResearch;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncScannedItems;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncWipe;
/*    */ 
/*    */ public class EventHandlerNetwork
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event)
/*    */   {
/* 31 */     Side side = FMLCommonHandler.instance().getEffectiveSide();
/* 32 */     if (side == Side.SERVER)
/*    */     {
/* 34 */       EntityPlayer p = event.player;
/* 35 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWipe(), (EntityPlayerMP)p);
/* 36 */       PacketHandler.INSTANCE.sendTo(new PacketSyncResearch(p), (EntityPlayerMP)p);
/* 37 */       PacketHandler.INSTANCE.sendTo(new PacketSyncScannedItems(p), (EntityPlayerMP)p);
/* 38 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncScannedEntities(p), (EntityPlayerMP)p);
/* 39 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncScannedPhenomena(p), (EntityPlayerMP)p);
/* 40 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncAspects(p), (EntityPlayerMP)p);
/* 41 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(p, (byte)0), (EntityPlayerMP)p);
/* 42 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(p, (byte)1), (EntityPlayerMP)p);
/* 43 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(p, (byte)2), (EntityPlayerMP)p);
/* 44 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.misc.PacketConfig(), (EntityPlayerMP)p);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SubscribeEvent
/*    */   public void clientLoggedIn(FMLNetworkEvent.ClientConnectedToServerEvent event)
/*    */   {
/* 69 */     if ((Thaumcraft.proxy.getClientWorld() != null) && (Minecraft.func_71410_x().field_71439_g != null)) {
/* 70 */       GuiResearchBrowser.completedResearch.put(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), new ArrayList());
/*    */       
/* 72 */       Thaumcraft.log.info("Resetting research to defaults.");
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void clientLogsOut(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
/*    */   {
/* 79 */     if (Thaumcraft.proxy.getClientWorld() != null) {
/* 80 */       Config.allowCheatSheet = Config.CallowCheatSheet;
/* 81 */       Config.wardedStone = Config.CwardedStone;
/* 82 */       Config.allowMirrors = Config.CallowMirrors;
/* 83 */       Config.hardNode = Config.ChardNode;
/* 84 */       Config.wuss = Config.Cwuss;
/* 85 */       Config.researchDifficulty = Config.CresearchDifficulty;
/* 86 */       Config.aspectTotalCap = Config.CaspectTotalCap;
/* 87 */       Thaumcraft.log.info("Restoring client configs.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/EventHandlerNetwork.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */