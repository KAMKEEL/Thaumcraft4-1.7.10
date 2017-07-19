/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketFXBlockArc implements cpw.mods.fml.common.network.simpleimpl.IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXBlockArc, cpw.mods.fml.common.network.simpleimpl.IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private int source;
/*    */   
/*    */   public PacketFXBlockArc() {}
/*    */   
/*    */   public PacketFXBlockArc(int x, int y, int z, int source)
/*    */   {
/* 20 */     this.x = x;
/* 21 */     this.y = y;
/* 22 */     this.z = z;
/* 23 */     this.source = source;
/*    */   }
/*    */   
/*    */ 
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 29 */     buffer.writeInt(this.x);
/* 30 */     buffer.writeInt(this.y);
/* 31 */     buffer.writeInt(this.z);
/* 32 */     buffer.writeInt(this.source);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 37 */     this.x = buffer.readInt();
/* 38 */     this.y = buffer.readInt();
/* 39 */     this.z = buffer.readInt();
/* 40 */     this.source = buffer.readInt();
/*    */   }
/*    */   
/*    */   public cpw.mods.fml.common.network.simpleimpl.IMessage onMessage(PacketFXBlockArc message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 45 */     Entity p = Thaumcraft.proxy.getClientWorld().func_73045_a(message.source);
/* 46 */     if (p != null) {
/* 47 */       float r = 0.3F - Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat() * 0.1F;
/* 48 */       float g = 0.0F;
/* 49 */       float b = 0.5F + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat() * 0.2F;
/* 50 */       if ((p instanceof thaumcraft.common.entities.monster.boss.EntityCultistPortal)) {
/* 51 */         r = 0.5F + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat() * 0.2F;
/* 52 */         g = 0.0F;
/* 53 */         b = 0.0F;
/*    */       }
/* 55 */       Thaumcraft.proxy.arcLightning(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70121_D.field_72338_b + p.field_70131_O / 2.0F, p.field_70161_v, message.x + 0.5D, message.y + 1, message.z + 0.5D, r, g, b, 0.5F);
/*    */     }
/*    */     
/*    */ 
/* 59 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXBlockArc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */