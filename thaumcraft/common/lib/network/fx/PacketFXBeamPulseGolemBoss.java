/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.client.particle.EffectRenderer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import thaumcraft.client.fx.beams.FXBeamGolemBoss;
/*    */ 
/*    */ public class PacketFXBeamPulseGolemBoss implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXBeamPulseGolemBoss, IMessage>
/*    */ {
/*    */   private int source;
/*    */   private int target;
/*    */   
/*    */   public PacketFXBeamPulseGolemBoss() {}
/*    */   
/*    */   public PacketFXBeamPulseGolemBoss(int source, int target)
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
/*    */   public IMessage onMessage(PacketFXBeamPulseGolemBoss message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 42 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 43 */     WorldClient world = mc.field_71441_e;
/*    */     
/* 45 */     EntityLivingBase var2 = (EntityLivingBase)getEntityByID(message.source, mc, world);
/* 46 */     EntityLivingBase var3 = (EntityLivingBase)getEntityByID(message.target, mc, world);
/* 47 */     if ((var2 != null) && (var3 != null))
/*    */     {
/* 49 */       FXBeamGolemBoss beamcon = new FXBeamGolemBoss(world, var2, var3, 0.07F, 0.376F, 0.325F, 20);
/* 50 */       beamcon.blendmode = 1;
/* 51 */       beamcon.field_70130_N = 3.0F;
/* 52 */       beamcon.setType(2);
/* 53 */       beamcon.setReverse(false);
/* 54 */       beamcon.setPulse(true);
/* 55 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(beamcon);
/*    */       
/* 57 */       FXBeamGolemBoss beamcon2 = new FXBeamGolemBoss(world, var2, var3, 1.0F, 0.5F, 0.5F, 20);
/* 58 */       beamcon2.blendmode = 1;
/* 59 */       beamcon2.field_70130_N = 1.5F;
/* 60 */       beamcon2.setType(1);
/* 61 */       beamcon2.setReverse(false);
/* 62 */       beamcon2.setPulse(true);
/* 63 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(beamcon2);
/*    */     }
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   private net.minecraft.entity.Entity getEntityByID(int par1, Minecraft mc, WorldClient world)
/*    */   {
/* 71 */     return par1 == mc.field_71439_g.func_145782_y() ? mc.field_71439_g : world.func_73045_a(par1);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXBeamPulseGolemBoss.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */