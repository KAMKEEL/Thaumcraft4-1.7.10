/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import thaumcraft.client.lib.RenderEventHandler;
/*    */ 
/*    */ public class PacketMiscEvent implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketMiscEvent, IMessage>
/*    */ {
/*    */   private short type;
/*    */   public static final short WARP_EVENT = 0;
/*    */   public static final short MIST_EVENT = 1;
/*    */   public static final short MIST_EVENT_SHORT = 2;
/*    */   
/*    */   public PacketMiscEvent() {}
/*    */   
/*    */   public PacketMiscEvent(short type)
/*    */   {
/* 22 */     this.type = type;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 27 */     buffer.writeShort(this.type);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 32 */     this.type = buffer.readShort();
/*    */   }
/*    */   
/*    */   @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketMiscEvent message, MessageContext ctx)
/*    */   {
/* 38 */     EntityPlayer p = Minecraft.func_71410_x().field_71439_g;
/*    */     
/* 40 */     switch (message.type) {
/*    */     case 0: 
/* 42 */       thaumcraft.client.lib.ClientTickEventsFML.warpVignette = 100;
/* 43 */       p.field_70170_p.func_72980_b(p.field_70165_t, p.field_70163_u, p.field_70161_v, "thaumcraft:heartbeat", 1.0F, 1.0F, false);
/* 44 */       break;
/*    */     case 1: 
/* 46 */       RenderEventHandler.fogFiddled = true;
/* 47 */       RenderEventHandler.fogDuration = 2400;
/* 48 */       break;
/*    */     case 2: 
/* 50 */       RenderEventHandler.fogFiddled = true;
/* 51 */       if (RenderEventHandler.fogDuration < 200) {
/* 52 */         RenderEventHandler.fogDuration = 200;
/*    */       }
/*    */       
/*    */       break;
/*    */     }
/*    */     
/* 58 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/misc/PacketMiscEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */