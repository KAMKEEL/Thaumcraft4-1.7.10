/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*    */ 
/*    */ public class PacketSyncWarp implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketSyncWarp, IMessage>
/*    */ {
/* 17 */   protected int data = 0;
/* 18 */   protected byte type = 0;
/*    */   
/*    */   public PacketSyncWarp() {}
/*    */   
/*    */   public PacketSyncWarp(EntityPlayer player, byte type)
/*    */   {
/* 24 */     if (type == 0) this.data = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.func_70005_c_());
/* 25 */     if (type == 1) this.data = Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_());
/* 26 */     if (type == 2) this.data = Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_());
/* 27 */     this.type = type;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 32 */     buffer.writeInt(this.data);
/* 33 */     buffer.writeByte(this.type);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 38 */     this.data = buffer.readInt();
/* 39 */     this.type = buffer.readByte();
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketSyncWarp message, MessageContext ctx)
/*    */   {
/* 46 */     if (message.type == 0) {
/* 47 */       Thaumcraft.proxy.getPlayerKnowledge().setWarpPerm(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), message.data);
/*    */ 
/*    */     }
/* 50 */     else if (message.type == 1) {
/* 51 */       Thaumcraft.proxy.getPlayerKnowledge().setWarpSticky(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), message.data);
/*    */     }
/*    */     else {
/* 54 */       Thaumcraft.proxy.getPlayerKnowledge().setWarpTemp(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), message.data);
/*    */     }
/*    */     
/*    */ 
/* 58 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketSyncWarp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */