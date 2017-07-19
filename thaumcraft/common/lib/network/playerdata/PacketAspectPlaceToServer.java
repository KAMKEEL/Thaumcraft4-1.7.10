/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.common.tiles.TileResearchTable;
/*    */ 
/*    */ public class PacketAspectPlaceToServer implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketAspectPlaceToServer, IMessage>
/*    */ {
/*    */   private int dim;
/*    */   private int playerid;
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   Aspect aspect;
/*    */   byte q;
/*    */   byte r;
/*    */   
/*    */   public PacketAspectPlaceToServer() {}
/*    */   
/*    */   public PacketAspectPlaceToServer(EntityPlayer player, byte q, byte r, int x, int y, int z, Aspect aspect)
/*    */   {
/* 27 */     this.dim = player.field_70170_p.field_73011_w.field_76574_g;
/* 28 */     this.playerid = player.func_145782_y();
/* 29 */     this.x = x;
/* 30 */     this.y = y;
/* 31 */     this.z = z;
/* 32 */     this.aspect = aspect;
/* 33 */     this.q = q;
/* 34 */     this.r = r;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 39 */     buffer.writeInt(this.dim);
/* 40 */     buffer.writeInt(this.playerid);
/* 41 */     buffer.writeInt(this.x);
/* 42 */     buffer.writeInt(this.y);
/* 43 */     buffer.writeInt(this.z);
/* 44 */     ByteBufUtils.writeUTF8String(buffer, this.aspect == null ? "null" : this.aspect.getTag());
/* 45 */     buffer.writeByte(this.q);
/* 46 */     buffer.writeByte(this.r);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 51 */     this.dim = buffer.readInt();
/* 52 */     this.playerid = buffer.readInt();
/* 53 */     this.x = buffer.readInt();
/* 54 */     this.y = buffer.readInt();
/* 55 */     this.z = buffer.readInt();
/* 56 */     this.aspect = Aspect.getAspect(ByteBufUtils.readUTF8String(buffer));
/* 57 */     this.q = buffer.readByte();
/* 58 */     this.r = buffer.readByte();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketAspectPlaceToServer message, MessageContext ctx)
/*    */   {
/* 63 */     World world = net.minecraftforge.common.DimensionManager.getWorld(message.dim);
/* 64 */     if ((world == null) || ((ctx.getServerHandler().field_147369_b != null) && (ctx.getServerHandler().field_147369_b.func_145782_y() != message.playerid))) {
/* 65 */       return null;
/*    */     }
/* 67 */     net.minecraft.entity.Entity player = world.func_73045_a(message.playerid);
/*    */     
/* 69 */     if (player == null) { return null;
/*    */     }
/* 71 */     net.minecraft.tileentity.TileEntity rt = world.func_147438_o(message.x, message.y, message.z);
/* 72 */     if ((rt != null) && ((rt instanceof TileResearchTable))) {
/* 73 */       ((TileResearchTable)rt).placeAspect(message.q, message.r, message.aspect, (EntityPlayer)player);
/*    */     }
/* 75 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketAspectPlaceToServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */