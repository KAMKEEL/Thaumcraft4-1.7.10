/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import cpw.mods.fml.common.FMLCommonHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.server.management.ServerConfigurationManager;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.lib.network.PacketHandler;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ import thaumcraft.common.lib.world.dim.TeleporterThaumcraft;
/*    */ 
/*    */ public class TileEldritchPortal extends TileEntity
/*    */ {
/*    */   public boolean canUpdate()
/*    */   {
/* 27 */     return true;
/*    */   }
/*    */   
/*    */   public double func_145833_n()
/*    */   {
/* 32 */     return 9216.0D;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 38 */     return AxisAlignedBB.func_72330_a(this.field_145851_c - 1, this.field_145848_d - 1, this.field_145849_e - 1, this.field_145851_c + 2, this.field_145848_d + 2, this.field_145849_e + 2);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 44 */   public int opencount = -1;
/* 45 */   private int count = 0;
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 49 */     this.count += 1;
/* 50 */     if ((this.field_145850_b.field_72995_K) && ((this.count % 250 == 0) || (this.count == 0))) {
/* 51 */       this.field_145850_b.func_72980_b(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "thaumcraft:evilportal", 1.0F, 1.0F, false);
/*    */     }
/* 53 */     if ((this.field_145850_b.field_72995_K) && (this.opencount < 30)) {
/* 54 */       this.opencount += 1;
/*    */     }
/* 56 */     if ((!this.field_145850_b.field_72995_K) && (this.count % 5 == 0)) {
/* 57 */       List ents = this.field_145850_b.func_72872_a(EntityPlayerMP.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(0.5D, 1.0D, 0.5D));
/*    */       
/*    */ 
/*    */ 
/*    */ 
/* 62 */       if (ents.size() > 0) {
/* 63 */         for (Object e : ents) {
/* 64 */           EntityPlayerMP player = (EntityPlayerMP)e;
/* 65 */           if ((player.field_70154_o == null) && (player.field_70153_n == null))
/*    */           {
/*    */ 
/* 68 */             MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();
/*    */             
/* 70 */             if (player.field_71088_bW > 0)
/*    */             {
/* 72 */               player.field_71088_bW = 100;
/*    */             }
/* 74 */             else if (player.field_71093_bK != Config.dimensionOuterId)
/*    */             {
/* 76 */               player.field_71088_bW = 100;
/*    */               
/* 78 */               player.field_71133_b.func_71203_ab().transferPlayerToDimension(player, Config.dimensionOuterId, new TeleporterThaumcraft(mServer.func_71218_a(Config.dimensionOuterId)));
/*    */               
/*    */ 
/*    */ 
/* 82 */               if (!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "ENTEROUTER")) {
/* 83 */                 PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("ENTEROUTER"), player);
/* 84 */                 Thaumcraft.proxy.getResearchManager().completeResearch(player, "ENTEROUTER");
/*    */               }
/*    */             }
/*    */             else
/*    */             {
/* 89 */               player.field_71088_bW = 100;
/* 90 */               player.field_71133_b.func_71203_ab().transferPlayerToDimension(player, 0, new TeleporterThaumcraft(mServer.func_71218_a(0)));
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileEldritchPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */