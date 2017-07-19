/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.DimensionManager;
/*    */ import thaumcraft.api.research.ScanResult;
/*    */ 
/*    */ public class PacketScannedToServer implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketScannedToServer, IMessage>
/*    */ {
/*    */   private int playerid;
/*    */   private int dim;
/*    */   private byte type;
/*    */   private int id;
/*    */   private int md;
/*    */   private int entityid;
/*    */   private String phenomena;
/*    */   private String prefix;
/*    */   
/*    */   public PacketScannedToServer() {}
/*    */   
/*    */   public PacketScannedToServer(ScanResult scan, EntityPlayer player, String prefix)
/*    */   {
/* 28 */     this.playerid = player.func_145782_y();
/* 29 */     this.dim = player.field_70170_p.field_73011_w.field_76574_g;
/* 30 */     this.type = scan.type;
/* 31 */     this.id = scan.id;
/* 32 */     this.md = scan.meta;
/* 33 */     this.entityid = (scan.entity == null ? 0 : scan.entity.func_145782_y());
/* 34 */     this.phenomena = scan.phenomena;
/* 35 */     this.prefix = prefix;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 40 */     buffer.writeInt(this.playerid);
/* 41 */     buffer.writeInt(this.dim);
/* 42 */     buffer.writeByte(this.type);
/* 43 */     buffer.writeInt(this.id);
/* 44 */     buffer.writeInt(this.md);
/* 45 */     buffer.writeInt(this.entityid);
/* 46 */     ByteBufUtils.writeUTF8String(buffer, this.phenomena);
/* 47 */     ByteBufUtils.writeUTF8String(buffer, this.prefix);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 52 */     this.playerid = buffer.readInt();
/* 53 */     this.dim = buffer.readInt();
/* 54 */     this.type = buffer.readByte();
/* 55 */     this.id = buffer.readInt();
/* 56 */     this.md = buffer.readInt();
/* 57 */     this.entityid = buffer.readInt();
/* 58 */     this.phenomena = ByteBufUtils.readUTF8String(buffer);
/* 59 */     this.prefix = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketScannedToServer message, MessageContext ctx)
/*    */   {
/* 64 */     World world = DimensionManager.getWorld(message.dim);
/* 65 */     if (world == null) { return null;
/*    */     }
/* 67 */     Entity player = world.func_73045_a(message.playerid);
/*    */     
/* 69 */     Entity e = null;
/* 70 */     if (message.entityid != 0) {
/* 71 */       e = world.func_73045_a(message.entityid);
/*    */     }
/*    */     
/* 74 */     if ((player != null) && ((player instanceof EntityPlayer))) {
/* 75 */       thaumcraft.common.lib.research.ScanManager.completeScan((EntityPlayer)player, new ScanResult(message.type, message.id, message.md, e, message.phenomena), message.prefix);
/*    */     }
/*    */     
/* 78 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketScannedToServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */