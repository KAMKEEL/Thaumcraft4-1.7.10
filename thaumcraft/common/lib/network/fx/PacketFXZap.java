/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import thaumcraft.client.fx.bolt.FXLightningBolt;
/*    */ 
/*    */ public class PacketFXZap implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXZap, IMessage>
/*    */ {
/*    */   private int source;
/*    */   private int target;
/*    */   
/*    */   public PacketFXZap() {}
/*    */   
/*    */   public PacketFXZap(int source, int target)
/*    */   {
/* 23 */     this.source = source;
/* 24 */     this.target = target;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 29 */     buffer.writeInt(this.source);
/* 30 */     buffer.writeInt(this.target);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 35 */     this.source = buffer.readInt();
/* 36 */     this.target = buffer.readInt();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketFXZap message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 42 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 43 */     WorldClient world = mc.field_71441_e;
/*    */     
/* 45 */     Entity var2 = getEntityByID(message.source, mc, world);
/* 46 */     Entity var3 = getEntityByID(message.target, mc, world);
/* 47 */     if ((var2 != null) && (var3 != null)) {
/* 48 */       FXLightningBolt bolt = new FXLightningBolt(world, var2.field_70165_t, var2.field_70121_D.field_72338_b + var2.field_70131_O / 2.0F, var2.field_70161_v, var3.field_70165_t, var3.field_70121_D.field_72338_b + var3.field_70131_O / 2.0F, var3.field_70161_v, world.field_73012_v.nextLong(), 6, 0.5F, 8);
/*    */       
/*    */ 
/*    */ 
/* 52 */       bolt.defaultFractal();
/* 53 */       bolt.setType(2);
/* 54 */       bolt.setWidth(0.125F);
/* 55 */       bolt.finalizeBolt();
/*    */     }
/* 57 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   private Entity getEntityByID(int par1, Minecraft mc, WorldClient world)
/*    */   {
/* 63 */     return par1 == mc.field_71439_g.func_145782_y() ? mc.field_71439_g : world.func_73045_a(par1);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXZap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */