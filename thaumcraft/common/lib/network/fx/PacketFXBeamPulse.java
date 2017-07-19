/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.entity.Entity;
/*    */ import thaumcraft.client.fx.beams.FXBeam;
/*    */ 
/*    */ public class PacketFXBeamPulse implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXBeamPulse, IMessage>
/*    */ {
/*    */   private int source;
/*    */   private int target;
/*    */   private int color;
/*    */   
/*    */   public PacketFXBeamPulse() {}
/*    */   
/*    */   public PacketFXBeamPulse(int source, int target, int color)
/*    */   {
/* 25 */     this.source = source;
/* 26 */     this.target = target;
/* 27 */     this.color = color;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 32 */     buffer.writeInt(this.source);
/* 33 */     buffer.writeInt(this.target);
/* 34 */     buffer.writeInt(this.color);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 39 */     this.source = buffer.readInt();
/* 40 */     this.target = buffer.readInt();
/* 41 */     this.color = buffer.readInt();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketFXBeamPulse message, MessageContext ctx)
/*    */   {
/* 47 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 48 */     WorldClient world = mc.field_71441_e;
/*    */     
/* 50 */     Entity var2 = getEntityByID(message.source, mc, world);
/* 51 */     Entity var3 = getEntityByID(message.target, mc, world);
/* 52 */     if ((var2 != null) && (var3 != null)) {
/* 53 */       Color c = new Color(message.color);
/* 54 */       FXBeam beamcon = new FXBeam(world, var2.field_70165_t, var2.field_70163_u + var2.func_70047_e(), var2.field_70161_v, var3, c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 20);
/*    */       
/*    */ 
/* 57 */       beamcon.blendmode = 771;
/* 58 */       beamcon.field_70130_N = 2.5F;
/* 59 */       beamcon.setType(1);
/*    */       
/* 61 */       beamcon.setReverse(true);
/* 62 */       beamcon.setPulse(true);
/* 63 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(beamcon);
/*    */     }
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   private Entity getEntityByID(int par1, Minecraft mc, WorldClient world)
/*    */   {
/* 71 */     return par1 == mc.field_71439_g.func_145782_y() ? mc.field_71439_g : world.func_73045_a(par1);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXBeamPulse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */