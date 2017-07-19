/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.entity.Entity;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketFXWispZap implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXWispZap, IMessage>
/*    */ {
/*    */   private int source;
/*    */   private int target;
/*    */   
/*    */   public PacketFXWispZap() {}
/*    */   
/*    */   public PacketFXWispZap(int source, int target)
/*    */   {
/* 22 */     this.source = source;
/* 23 */     this.target = target;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 28 */     buffer.writeInt(this.source);
/* 29 */     buffer.writeInt(this.target);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 34 */     this.source = buffer.readInt();
/* 35 */     this.target = buffer.readInt();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketFXWispZap message, MessageContext ctx)
/*    */   {
/* 40 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 41 */     WorldClient world = mc.field_71441_e;
/*    */     
/* 43 */     Entity var2 = getEntityByID(message.source, mc, world);
/* 44 */     Entity var3 = getEntityByID(message.target, mc, world);
/* 45 */     if ((var2 != null) && (var3 != null))
/* 46 */       Thaumcraft.proxy.bolt(var2.field_70170_p, var2, var3);
/* 47 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   private Entity getEntityByID(int par1, Minecraft mc, WorldClient world)
/*    */   {
/* 53 */     return par1 == mc.field_71439_g.func_145782_y() ? mc.field_71439_g : world.func_73045_a(par1);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXWispZap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */