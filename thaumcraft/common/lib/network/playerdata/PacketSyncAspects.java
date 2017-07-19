/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*    */ 
/*    */ public class PacketSyncAspects implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketSyncAspects, IMessage>
/*    */ {
/* 18 */   protected AspectList data = new AspectList();
/*    */   
/*    */   public PacketSyncAspects() {}
/*    */   
/*    */   public PacketSyncAspects(EntityPlayer player) {
/* 23 */     this.data = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(player.func_70005_c_());
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 28 */     if ((this.data != null) && (this.data.size() > 0)) {
/* 29 */       buffer.writeShort(this.data.size());
/* 30 */       for (Aspect a : this.data.getAspects()) if (a != null) {
/* 31 */           ByteBufUtils.writeUTF8String(buffer, a.getTag());
/* 32 */           buffer.writeShort(this.data.getAmount(a));
/*    */         }
/* 34 */     } else { buffer.writeShort(0);
/*    */     }
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 39 */     short size = buffer.readShort();
/* 40 */     this.data = new AspectList();
/* 41 */     for (int a = 0; a < size; a++) {
/* 42 */       String tag = ByteBufUtils.readUTF8String(buffer);
/* 43 */       short amount = buffer.readShort();
/* 44 */       this.data.add(Aspect.getAspect(tag), amount);
/*    */     }
/*    */   }
/*    */   
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public IMessage onMessage(PacketSyncAspects message, MessageContext ctx)
/*    */   {
/* 51 */     for (Aspect key : message.data.getAspects()) {
/* 52 */       Thaumcraft.proxy.getResearchManager().completeAspect(Minecraft.func_71410_x().field_71439_g, key, (short)message.data.getAmount(key));
/*    */     }
/* 54 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketSyncAspects.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */