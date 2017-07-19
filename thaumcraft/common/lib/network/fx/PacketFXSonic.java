/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.client.fx.other.FXSonic;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketFXSonic implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXSonic, IMessage>
/*    */ {
/*    */   private int source;
/*    */   
/*    */   public PacketFXSonic() {}
/*    */   
/*    */   public PacketFXSonic(int source)
/*    */   {
/* 22 */     this.source = source;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 27 */     buffer.writeInt(this.source);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 32 */     this.source = buffer.readInt();
/*    */   }
/*    */   
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public IMessage onMessage(PacketFXSonic message, MessageContext ctx)
/*    */   {
/* 38 */     Entity p = Thaumcraft.proxy.getClientWorld().func_73045_a(message.source);
/* 39 */     if (p != null) {
/* 40 */       FXSonic fb = new FXSonic(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 10);
/*    */       
/*    */ 
/* 43 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*    */     }
/* 45 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXSonic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */