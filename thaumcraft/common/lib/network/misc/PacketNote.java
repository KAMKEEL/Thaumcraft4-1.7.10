/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityNote;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.tiles.TileSensor;
/*    */ 
/*    */ public class PacketNote implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketNote, IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private int dim;
/*    */   private byte note;
/*    */   
/*    */   public PacketNote() {}
/*    */   
/*    */   public PacketNote(int x, int y, int z, int dim)
/*    */   {
/* 25 */     this.x = x;
/* 26 */     this.y = y;
/* 27 */     this.z = z;
/* 28 */     this.dim = dim;
/* 29 */     this.note = -1;
/*    */   }
/*    */   
/* 32 */   public PacketNote(int x, int y, int z, int dim, byte note) { this.x = x;
/* 33 */     this.y = y;
/* 34 */     this.z = z;
/* 35 */     this.dim = dim;
/* 36 */     this.note = note;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 41 */     buffer.writeInt(this.x);
/* 42 */     buffer.writeInt(this.y);
/* 43 */     buffer.writeInt(this.z);
/* 44 */     buffer.writeInt(this.dim);
/* 45 */     buffer.writeByte(this.note);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 50 */     this.x = buffer.readInt();
/* 51 */     this.y = buffer.readInt();
/* 52 */     this.z = buffer.readInt();
/* 53 */     this.dim = buffer.readInt();
/* 54 */     this.note = buffer.readByte();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketNote message, MessageContext ctx)
/*    */   {
/* 59 */     if (ctx.side == cpw.mods.fml.relauncher.Side.CLIENT) {
/* 60 */       if (message.note >= 0) {
/* 61 */         TileEntity tile = Thaumcraft.proxy.getClientWorld().func_147438_o(message.x, message.y, message.z);
/* 62 */         if ((tile != null) && ((tile instanceof TileEntityNote))) {
/* 63 */           ((TileEntityNote)tile).field_145879_a = message.note;
/*    */         }
/* 65 */         else if ((tile != null) && ((tile instanceof TileSensor))) {
/* 66 */           ((TileSensor)tile).note = message.note;
/*    */         }
/*    */       }
/*    */     }
/* 70 */     else if (message.note == -1) {
/* 71 */       World world = net.minecraftforge.common.DimensionManager.getWorld(message.dim);
/* 72 */       if (world == null) return null;
/* 73 */       TileEntity tile = world.func_147438_o(message.x, message.y, message.z);
/* 74 */       byte note = -1;
/* 75 */       if ((tile != null) && ((tile instanceof TileEntityNote))) {
/* 76 */         note = ((TileEntityNote)tile).field_145879_a;
/*    */       }
/* 78 */       else if ((tile != null) && ((tile instanceof TileSensor))) {
/* 79 */         note = ((TileSensor)tile).note;
/*    */       }
/*    */       
/* 82 */       if (note >= 0) {
/* 83 */         thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new PacketNote(message.x, message.y, message.z, message.dim, note), new cpw.mods.fml.common.network.NetworkRegistry.TargetPoint(message.dim, message.x, message.y, message.z, 8.0D));
/*    */       }
/*    */     }
/*    */     
/* 87 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/misc/PacketNote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */