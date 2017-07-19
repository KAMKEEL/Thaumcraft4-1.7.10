/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.client.lib.PlayerNotifications;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*    */ 
/*    */ public class PacketAspectPool implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketAspectPool, IMessage>
/*    */ {
/*    */   private String key;
/*    */   private Short amount;
/*    */   private Short total;
/*    */   
/*    */   public PacketAspectPool() {}
/*    */   
/*    */   public PacketAspectPool(String key, Short amount, Short total)
/*    */   {
/* 26 */     this.key = key;
/* 27 */     this.amount = amount;
/* 28 */     this.total = total;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 33 */     ByteBufUtils.writeUTF8String(buffer, this.key);
/* 34 */     buffer.writeShort(this.amount.shortValue());
/* 35 */     buffer.writeShort(this.total.shortValue());
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 40 */     this.key = ByteBufUtils.readUTF8String(buffer);
/* 41 */     this.amount = Short.valueOf(buffer.readShort());
/* 42 */     this.total = Short.valueOf(buffer.readShort());
/*    */   }
/*    */   
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public IMessage onMessage(PacketAspectPool message, MessageContext ctx)
/*    */   {
/* 48 */     if (Aspect.getAspect(message.key) != null) {
/* 49 */       boolean success = Thaumcraft.proxy.getPlayerKnowledge().setAspectPool(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), Aspect.getAspect(message.key), message.total.shortValue());
/* 50 */       if ((success) && (message.amount.shortValue() > 0)) {
/* 51 */         String text = net.minecraft.util.StatCollector.func_74838_a("tc.addaspectpool");
/* 52 */         text = text.replaceAll("%s", message.amount + "");
/* 53 */         text = text.replaceAll("%n", Aspect.getAspect(message.key).getName());
/* 54 */         PlayerNotifications.addNotification(text, Aspect.getAspect(message.key));
/* 55 */         for (int a = 0; a < message.amount.shortValue(); a++) PlayerNotifications.addAspectNotification(Aspect.getAspect(message.key));
/* 56 */         if (System.currentTimeMillis() > lastSound) {
/* 57 */           Minecraft.func_71410_x().field_71439_g.func_85030_a("random.orb", 0.1F, 0.9F + Minecraft.func_71410_x().field_71439_g.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/* 58 */           lastSound = System.currentTimeMillis() + 100L;
/*    */         }
/*    */       }
/*    */     }
/* 62 */     return null;
/*    */   }
/*    */   
/* 65 */   private static long lastSound = 0L;
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketAspectPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */