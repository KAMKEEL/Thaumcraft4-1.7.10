/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import net.minecraftforge.common.DimensionManager;
/*    */ import thaumcraft.common.entities.golems.ItemGolemBell;
/*    */ import thaumcraft.common.items.equipment.ItemElementalShovel;
/*    */ 
/*    */ public class PacketItemKeyToServer implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketItemKeyToServer, IMessage>
/*    */ {
/*    */   private int dim;
/*    */   private int playerid;
/*    */   private byte key;
/*    */   
/*    */   public PacketItemKeyToServer() {}
/*    */   
/*    */   public PacketItemKeyToServer(EntityPlayer player, int key)
/*    */   {
/* 24 */     this.dim = player.field_70170_p.field_73011_w.field_76574_g;
/* 25 */     this.playerid = player.func_145782_y();
/* 26 */     this.key = ((byte)key);
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 31 */     buffer.writeInt(this.dim);
/* 32 */     buffer.writeInt(this.playerid);
/* 33 */     buffer.writeByte(this.key);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 38 */     this.dim = buffer.readInt();
/* 39 */     this.playerid = buffer.readInt();
/* 40 */     this.key = buffer.readByte();
/*    */   }
/*    */   
/*    */   public IMessage onMessage(PacketItemKeyToServer message, MessageContext ctx)
/*    */   {
/* 45 */     World world = DimensionManager.getWorld(message.dim);
/* 46 */     if (world == null) { return null;
/*    */     }
/* 48 */     net.minecraft.entity.Entity player = world.func_73045_a(message.playerid);
/*    */     
/* 50 */     if ((player != null) && ((player instanceof EntityPlayer)) && (((EntityPlayer)player).func_70694_bm() != null))
/*    */     {
/*    */ 
/* 53 */       if ((message.key == 0) && ((((EntityPlayer)player).func_70694_bm().func_77973_b() instanceof ItemGolemBell))) {
/* 54 */         ItemGolemBell.resetMarkers(((EntityPlayer)player).func_70694_bm(), world, (EntityPlayer)player);
/*    */       }
/*    */       
/* 57 */       if ((message.key == 1) && ((((EntityPlayer)player).func_70694_bm().func_77973_b() instanceof thaumcraft.common.items.wands.ItemWandCasting))) {
/* 58 */         thaumcraft.common.items.wands.WandManager.toggleMisc(((EntityPlayer)player).func_70694_bm(), world, (EntityPlayer)player);
/*    */       }
/*    */       
/* 61 */       if ((message.key == 1) && ((((EntityPlayer)player).func_70694_bm().func_77973_b() instanceof ItemElementalShovel))) {
/* 62 */         ((ItemElementalShovel)((EntityPlayer)player).func_70694_bm().func_77973_b());byte b = ItemElementalShovel.getOrientation(((EntityPlayer)player).func_70694_bm());
/* 63 */         ((ItemElementalShovel)((EntityPlayer)player).func_70694_bm().func_77973_b());ItemElementalShovel.setOrientation(((EntityPlayer)player).func_70694_bm(), (byte)(b + 1));
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 68 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/misc/PacketItemKeyToServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */