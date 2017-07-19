/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Block.SoundType;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.client.fx.particles.FXBoreParticles;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketFXBlockDig implements cpw.mods.fml.common.network.simpleimpl.IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXBlockDig, cpw.mods.fml.common.network.simpleimpl.IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private int bi;
/*    */   private int md;
/*    */   private byte dx;
/*    */   private byte dy;
/*    */   private byte dz;
/*    */   
/*    */   public PacketFXBlockDig() {}
/*    */   
/*    */   public PacketFXBlockDig(int x, int y, int z, byte xd, byte xy, byte xz, int bi, int md)
/*    */   {
/* 27 */     this.x = x;
/* 28 */     this.y = y;
/* 29 */     this.z = z;
/* 30 */     this.bi = bi;
/* 31 */     this.md = md;
/* 32 */     this.dx = xd;
/* 33 */     this.dy = xy;
/* 34 */     this.dz = xz;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 39 */     buffer.writeInt(this.x);
/* 40 */     buffer.writeInt(this.y);
/* 41 */     buffer.writeInt(this.z);
/* 42 */     buffer.writeInt(this.bi);
/* 43 */     buffer.writeInt(this.md);
/* 44 */     buffer.writeByte(this.dx);
/* 45 */     buffer.writeByte(this.dy);
/* 46 */     buffer.writeByte(this.dz);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 51 */     this.x = buffer.readInt();
/* 52 */     this.y = buffer.readInt();
/* 53 */     this.z = buffer.readInt();
/* 54 */     this.bi = buffer.readInt();
/* 55 */     this.md = buffer.readInt();
/* 56 */     this.dx = buffer.readByte();
/* 57 */     this.dy = buffer.readByte();
/* 58 */     this.dz = buffer.readByte();
/*    */   }
/*    */   
/*    */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public cpw.mods.fml.common.network.simpleimpl.IMessage onMessage(PacketFXBlockDig message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 64 */     net.minecraft.item.Item item = net.minecraft.item.Item.func_150899_d(message.bi);
/* 65 */     if ((new net.minecraft.item.ItemStack(item, 1, message.md).func_94608_d() == 0) && ((item instanceof net.minecraft.item.ItemBlock))) {
/* 66 */       Block block = Block.func_149729_e(message.bi);
/* 67 */       if (block != null) {
/* 68 */         for (int a = 0; a < Thaumcraft.proxy.particleCount(20); a++) {
/* 69 */           FXBoreParticles fb = new FXBoreParticles(Thaumcraft.proxy.getClientWorld(), message.dx + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.dy + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.dz + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.x + 0.5D, message.y + 0.5D, message.z + 0.5D, block, Thaumcraft.proxy.getClientWorld().field_73012_v.nextInt(6), message.md).func_70596_a(message.x, message.y, message.z);
/*    */           
/*    */ 
/*    */ 
/* 73 */           cpw.mods.fml.client.FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*    */         }
/* 75 */         Thaumcraft.proxy.getClientWorld().func_72980_b(message.dx + 0.5F, message.dy + 0.5F, message.dz + 0.5F, block.field_149762_H.func_150495_a(), (block.field_149762_H.func_150497_c() + 1.0F) / 2.0F, block.field_149762_H.func_150494_d() * 0.8F, false);
/*    */       }
/*    */       
/*    */     }
/*    */     else
/*    */     {
/* 81 */       for (int a = 0; a < Thaumcraft.proxy.particleCount(20); a++) {
/* 82 */         FXBoreParticles fb = new FXBoreParticles(Thaumcraft.proxy.getClientWorld(), message.dx + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.dy + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.dz + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.x + 0.5D, message.y + 0.5D, message.z + 0.5D, item, Thaumcraft.proxy.getClientWorld().field_73012_v.nextInt(6), message.md).func_70596_a(message.x, message.y, message.z);
/*    */         
/*    */ 
/*    */ 
/* 86 */         cpw.mods.fml.client.FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*    */       }
/* 88 */       Thaumcraft.proxy.getClientWorld().func_72980_b(message.dx + 0.5F, message.dy + 0.5F, message.dz + 0.5F, net.minecraft.init.Blocks.field_150348_b.field_149762_H.func_150495_a(), (net.minecraft.init.Blocks.field_150348_b.field_149762_H.func_150497_c() + 1.0F) / 2.0F, net.minecraft.init.Blocks.field_150348_b.field_149762_H.func_150494_d() * 0.8F, false);
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 93 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXBlockDig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */