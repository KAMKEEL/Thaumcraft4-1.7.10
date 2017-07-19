/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketSyncWipe
/*    */   implements IMessage, IMessageHandler<PacketSyncWipe, IMessage>
/*    */ {
/*    */   public void toBytes(ByteBuf buffer) {}
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {}
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketSyncWipe message, MessageContext ctx)
/*    */   {
/* 32 */     Thaumcraft.proxy.getPlayerKnowledge().wipePlayerKnowledge(Minecraft.func_71410_x().field_71439_g.func_70005_c_());
/*    */     
/* 34 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketSyncWipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */