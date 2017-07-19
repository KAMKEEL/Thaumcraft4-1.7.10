/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.NetHandlerPlayServer;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.items.wands.ItemWandCasting;
/*    */ 
/*    */ public class PacketFocusChangeToServer implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFocusChangeToServer, IMessage>
/*    */ {
/*    */   private int dim;
/*    */   private int playerid;
/*    */   private String focus;
/*    */   
/*    */   public PacketFocusChangeToServer() {}
/*    */   
/*    */   public PacketFocusChangeToServer(EntityPlayer player, String focus)
/*    */   {
/* 23 */     this.dim = player.field_70170_p.field_73011_w.field_76574_g;
/* 24 */     this.playerid = player.func_145782_y();
/* 25 */     this.focus = focus;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 30 */     buffer.writeInt(this.dim);
/* 31 */     buffer.writeInt(this.playerid);
/* 32 */     ByteBufUtils.writeUTF8String(buffer, this.focus);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 37 */     this.dim = buffer.readInt();
/* 38 */     this.playerid = buffer.readInt();
/* 39 */     this.focus = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketFocusChangeToServer message, MessageContext ctx)
/*    */   {
/* 44 */     World world = net.minecraftforge.common.DimensionManager.getWorld(message.dim);
/* 45 */     if ((world == null) || ((ctx.getServerHandler().field_147369_b != null) && (ctx.getServerHandler().field_147369_b.func_145782_y() != message.playerid))) { return null;
/*    */     }
/* 47 */     net.minecraft.entity.Entity player = world.func_73045_a(message.playerid);
/*    */     
/* 49 */     if ((player != null) && ((player instanceof EntityPlayer)) && (((EntityPlayer)player).func_70694_bm() != null))
/*    */     {
/* 51 */       if (((((EntityPlayer)player).func_70694_bm().func_77973_b() instanceof ItemWandCasting)) && (!((ItemWandCasting)((EntityPlayer)player).func_70694_bm().func_77973_b()).isSceptre(((EntityPlayer)player).func_70694_bm())))
/*    */       {
/* 53 */         thaumcraft.common.items.wands.WandManager.changeFocus(((EntityPlayer)player).func_70694_bm(), world, (EntityPlayer)player, message.focus); }
/*    */     }
/* 55 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/misc/PacketFocusChangeToServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */