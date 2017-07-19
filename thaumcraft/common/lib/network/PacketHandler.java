/*    */ package thaumcraft.common.lib.network;
/*    */ 
/*    */ import cpw.mods.fml.common.network.NetworkRegistry;
/*    */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXBeamPulse;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXBeamPulseGolemBoss;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXBlockArc;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXBlockBubble;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXBlockDig;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXInfusionSource;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXShield;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXSonic;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXVisDrain;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXWispZap;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXZap;
/*    */ import thaumcraft.common.lib.network.misc.PacketBiomeChange;
/*    */ import thaumcraft.common.lib.network.misc.PacketBoreDig;
/*    */ import thaumcraft.common.lib.network.misc.PacketConfig;
/*    */ import thaumcraft.common.lib.network.misc.PacketFlyToServer;
/*    */ import thaumcraft.common.lib.network.misc.PacketFocusChangeToServer;
/*    */ import thaumcraft.common.lib.network.misc.PacketItemKeyToServer;
/*    */ import thaumcraft.common.lib.network.misc.PacketMiscEvent;
/*    */ import thaumcraft.common.lib.network.misc.PacketNote;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketAspectCombinationToServer;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketAspectDiscovery;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketAspectPlaceToServer;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketPlayerCompleteToServer;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketRunicCharge;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketScannedToServer;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncAspects;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncResearch;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncScannedEntities;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncScannedItems;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncScannedPhenomena;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketSyncWipe;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketWarpMessage;
/*    */ 
/*    */ 
/*    */ public class PacketHandler
/*    */ {
/* 48 */   public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("Thaumcraft".toLowerCase());
/*    */   
/*    */   public static void init()
/*    */   {
/* 52 */     int idx = 0;
/*    */     
/*    */ 
/* 55 */     INSTANCE.registerMessage(PacketBiomeChange.class, PacketBiomeChange.class, idx++, Side.CLIENT);
/* 56 */     INSTANCE.registerMessage(PacketConfig.class, PacketConfig.class, idx++, Side.CLIENT);
/* 57 */     INSTANCE.registerMessage(PacketMiscEvent.class, PacketMiscEvent.class, idx++, Side.CLIENT);
/*    */     
/*    */ 
/* 60 */     INSTANCE.registerMessage(PacketSyncWipe.class, PacketSyncWipe.class, idx++, Side.CLIENT);
/* 61 */     INSTANCE.registerMessage(PacketSyncAspects.class, PacketSyncAspects.class, idx++, Side.CLIENT);
/* 62 */     INSTANCE.registerMessage(PacketSyncResearch.class, PacketSyncResearch.class, idx++, Side.CLIENT);
/* 63 */     INSTANCE.registerMessage(PacketSyncScannedItems.class, PacketSyncScannedItems.class, idx++, Side.CLIENT);
/* 64 */     INSTANCE.registerMessage(PacketSyncScannedEntities.class, PacketSyncScannedEntities.class, idx++, Side.CLIENT);
/* 65 */     INSTANCE.registerMessage(PacketSyncScannedPhenomena.class, PacketSyncScannedPhenomena.class, idx++, Side.CLIENT);
/* 66 */     INSTANCE.registerMessage(PacketResearchComplete.class, PacketResearchComplete.class, idx++, Side.CLIENT);
/* 67 */     INSTANCE.registerMessage(PacketAspectPool.class, PacketAspectPool.class, idx++, Side.CLIENT);
/* 68 */     INSTANCE.registerMessage(PacketAspectDiscovery.class, PacketAspectDiscovery.class, idx++, Side.CLIENT);
/* 69 */     INSTANCE.registerMessage(PacketScannedToServer.class, PacketScannedToServer.class, idx++, Side.SERVER);
/* 70 */     INSTANCE.registerMessage(PacketAspectCombinationToServer.class, PacketAspectCombinationToServer.class, idx++, Side.SERVER);
/* 71 */     INSTANCE.registerMessage(PacketPlayerCompleteToServer.class, PacketPlayerCompleteToServer.class, idx++, Side.SERVER);
/* 72 */     INSTANCE.registerMessage(PacketAspectPlaceToServer.class, PacketAspectPlaceToServer.class, idx++, Side.SERVER);
/*    */     
/*    */ 
/* 75 */     INSTANCE.registerMessage(PacketRunicCharge.class, PacketRunicCharge.class, idx++, Side.CLIENT);
/* 76 */     INSTANCE.registerMessage(PacketBoreDig.class, PacketBoreDig.class, idx++, Side.CLIENT);
/* 77 */     INSTANCE.registerMessage(PacketNote.class, PacketNote.class, idx++, Side.CLIENT);
/* 78 */     INSTANCE.registerMessage(PacketSyncWarp.class, PacketSyncWarp.class, idx++, Side.CLIENT);
/* 79 */     INSTANCE.registerMessage(PacketWarpMessage.class, PacketWarpMessage.class, idx++, Side.CLIENT);
/* 80 */     INSTANCE.registerMessage(PacketNote.class, PacketNote.class, idx++, Side.SERVER);
/* 81 */     INSTANCE.registerMessage(PacketItemKeyToServer.class, PacketItemKeyToServer.class, idx++, Side.SERVER);
/* 82 */     INSTANCE.registerMessage(PacketFocusChangeToServer.class, PacketFocusChangeToServer.class, idx++, Side.SERVER);
/* 83 */     INSTANCE.registerMessage(PacketFlyToServer.class, PacketFlyToServer.class, idx++, Side.SERVER);
/*    */     
/*    */ 
/* 86 */     INSTANCE.registerMessage(PacketFXBlockBubble.class, PacketFXBlockBubble.class, idx++, Side.CLIENT);
/* 87 */     INSTANCE.registerMessage(PacketFXBlockDig.class, PacketFXBlockDig.class, idx++, Side.CLIENT);
/* 88 */     INSTANCE.registerMessage(PacketFXBlockSparkle.class, PacketFXBlockSparkle.class, idx++, Side.CLIENT);
/* 89 */     INSTANCE.registerMessage(PacketFXBlockArc.class, PacketFXBlockArc.class, idx++, Side.CLIENT);
/* 90 */     INSTANCE.registerMessage(PacketFXBlockZap.class, PacketFXBlockZap.class, idx++, Side.CLIENT);
/* 91 */     INSTANCE.registerMessage(PacketFXEssentiaSource.class, PacketFXEssentiaSource.class, idx++, Side.CLIENT);
/* 92 */     INSTANCE.registerMessage(PacketFXInfusionSource.class, PacketFXInfusionSource.class, idx++, Side.CLIENT);
/* 93 */     INSTANCE.registerMessage(PacketFXShield.class, PacketFXShield.class, idx++, Side.CLIENT);
/* 94 */     INSTANCE.registerMessage(PacketFXSonic.class, PacketFXSonic.class, idx++, Side.CLIENT);
/* 95 */     INSTANCE.registerMessage(PacketFXWispZap.class, PacketFXWispZap.class, idx++, Side.CLIENT);
/* 96 */     INSTANCE.registerMessage(PacketFXZap.class, PacketFXZap.class, idx++, Side.CLIENT);
/* 97 */     INSTANCE.registerMessage(PacketFXVisDrain.class, PacketFXVisDrain.class, idx++, Side.CLIENT);
/* 98 */     INSTANCE.registerMessage(PacketFXBeamPulse.class, PacketFXBeamPulse.class, idx++, Side.CLIENT);
/* 99 */     INSTANCE.registerMessage(PacketFXBeamPulseGolemBoss.class, PacketFXBeamPulseGolemBoss.class, idx++, Side.CLIENT);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/PacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */