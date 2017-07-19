/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketFXBlockBubble implements cpw.mods.fml.common.network.simpleimpl.IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXBlockBubble, cpw.mods.fml.common.network.simpleimpl.IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private int color;
/*    */   
/*    */   public PacketFXBlockBubble() {}
/*    */   
/*    */   public PacketFXBlockBubble(int x, int y, int z, int color)
/*    */   {
/* 20 */     this.x = x;
/* 21 */     this.y = y;
/* 22 */     this.z = z;
/* 23 */     this.color = color;
/*    */   }
/*    */   
/*    */ 
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 29 */     buffer.writeInt(this.x);
/* 30 */     buffer.writeInt(this.y);
/* 31 */     buffer.writeInt(this.z);
/* 32 */     buffer.writeInt(this.color);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 37 */     this.x = buffer.readInt();
/* 38 */     this.y = buffer.readInt();
/* 39 */     this.z = buffer.readInt();
/* 40 */     this.color = buffer.readInt();
/*    */   }
/*    */   
/*    */   public cpw.mods.fml.common.network.simpleimpl.IMessage onMessage(PacketFXBlockBubble message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 45 */     java.awt.Color c = new java.awt.Color(message.color);
/* 46 */     float r = c.getRed() / 255.0F;
/* 47 */     float g = c.getGreen() / 255.0F;
/* 48 */     float b = c.getBlue() / 255.0F;
/* 49 */     for (int a = 0; a < Thaumcraft.proxy.particleCount(1); a++) {
/* 50 */       Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), message.x, message.y + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.z + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), r, g, b);
/*    */       
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 56 */       Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), message.x + 1, message.y + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.z + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), r, g, b);
/*    */       
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 62 */       Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), message.x + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.y + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.z, r, g, b);
/*    */       
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 68 */       Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), message.x + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.y + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.z + 1, r, g, b);
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 74 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXBlockBubble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */