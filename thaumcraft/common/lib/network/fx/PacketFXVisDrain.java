/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.awt.Color;
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.fx.particles.FXVisSparkle;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketFXVisDrain implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXVisDrain, IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private int color;
/*    */   private int dx;
/*    */   private int dy;
/*    */   private int dz;
/*    */   
/*    */   public PacketFXVisDrain() {}
/*    */   
/*    */   public PacketFXVisDrain(int x, int y, int z, int xd, int xy, int xz, int color)
/*    */   {
/* 28 */     this.x = x;
/* 29 */     this.y = y;
/* 30 */     this.z = z;
/* 31 */     this.color = color;
/* 32 */     this.dx = (xd - x);
/* 33 */     this.dy = (xy - y);
/* 34 */     this.dz = (xz - z);
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 39 */     buffer.writeShort(this.x);
/* 40 */     buffer.writeShort(this.y);
/* 41 */     buffer.writeShort(this.z);
/* 42 */     buffer.writeByte(this.color);
/* 43 */     buffer.writeByte(this.dx);
/* 44 */     buffer.writeByte(this.dy);
/* 45 */     buffer.writeByte(this.dz);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 50 */     this.x = buffer.readShort();
/* 51 */     this.y = buffer.readShort();
/* 52 */     this.z = buffer.readShort();
/* 53 */     this.color = ((Aspect)Aspect.getPrimalAspects().get(buffer.readByte())).getColor();
/* 54 */     this.dx = (this.x + buffer.readByte());
/* 55 */     this.dy = (this.y + buffer.readByte());
/* 56 */     this.dz = (this.z + buffer.readByte());
/*    */   }
/*    */   
/*    */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public IMessage onMessage(PacketFXVisDrain message, MessageContext ctx)
/*    */   {
/* 62 */     World worldObj = Thaumcraft.proxy.getClientWorld();
/* 63 */     FXVisSparkle fb = new FXVisSparkle(worldObj, message.dx + 0.4D + worldObj.field_73012_v.nextFloat() * 0.2F, message.dy + 0.4D + worldObj.field_73012_v.nextFloat() * 0.2F, message.dz + 0.4D + worldObj.field_73012_v.nextFloat() * 0.2F, message.x + worldObj.field_73012_v.nextFloat(), message.y + worldObj.field_73012_v.nextFloat(), message.z + worldObj.field_73012_v.nextFloat());
/*    */     
/*    */ 
/* 66 */     Color c = new Color(message.color);
/* 67 */     fb.func_70538_b(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/* 68 */     ParticleEngine.instance.addEffect(worldObj, fb);
/*    */     
/* 70 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXVisDrain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */