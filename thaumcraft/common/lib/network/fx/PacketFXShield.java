/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.particle.EffectRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.client.fx.other.FXShieldRunes;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class PacketFXShield implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketFXShield, IMessage>
/*    */ {
/*    */   private int source;
/*    */   private int target;
/*    */   
/*    */   public PacketFXShield() {}
/*    */   
/*    */   public PacketFXShield(int source, int target)
/*    */   {
/* 24 */     this.source = source;
/* 25 */     this.target = target;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 30 */     buffer.writeInt(this.source);
/* 31 */     buffer.writeInt(this.target);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 36 */     this.source = buffer.readInt();
/* 37 */     this.target = buffer.readInt();
/*    */   }
/*    */   
/*    */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public IMessage onMessage(PacketFXShield message, cpw.mods.fml.common.network.simpleimpl.MessageContext ctx)
/*    */   {
/* 43 */     Entity p = Thaumcraft.proxy.getClientWorld().func_73045_a(message.source);
/* 44 */     if ((p == null) || (Thaumcraft.proxy.getClientWorld() == null)) return null;
/* 45 */     float pitch = 0.0F;
/* 46 */     float yaw = 0.0F;
/* 47 */     if (message.target >= 0) {
/* 48 */       Entity t = Thaumcraft.proxy.getClientWorld().func_73045_a(message.target);
/* 49 */       if (t != null) {
/* 50 */         double d0 = p.field_70165_t - t.field_70165_t;
/* 51 */         double d1 = (p.field_70121_D.field_72338_b + p.field_70121_D.field_72337_e) / 2.0D - (t.field_70121_D.field_72338_b + t.field_70121_D.field_72337_e) / 2.0D;
/* 52 */         double d2 = p.field_70161_v - t.field_70161_v;
/* 53 */         double d3 = net.minecraft.util.MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/* 54 */         float f = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 55 */         float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
/* 56 */         pitch = f1;
/* 57 */         yaw = f;
/*    */       } else {
/* 59 */         pitch = 90.0F;
/* 60 */         yaw = 0.0F;
/*    */       }
/* 62 */       FXShieldRunes fb = new FXShieldRunes(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 8, yaw, pitch);
/*    */       
/*    */ 
/* 65 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*    */     }
/* 67 */     else if (message.target == -1) {
/* 68 */       FXShieldRunes fb = new FXShieldRunes(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 8, 0.0F, 90.0F);
/*    */       
/*    */ 
/* 71 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/* 72 */       fb = new FXShieldRunes(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 8, 0.0F, 270.0F);
/*    */       
/*    */ 
/* 75 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*    */     }
/* 77 */     else if (message.target == -2) {
/* 78 */       FXShieldRunes fb = new FXShieldRunes(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 8, 0.0F, 270.0F);
/*    */       
/*    */ 
/* 81 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*    */     }
/* 83 */     else if (message.target == -3) {
/* 84 */       FXShieldRunes fb = new FXShieldRunes(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 8, 0.0F, 90.0F);
/*    */       
/*    */ 
/* 87 */       FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(fb);
/*    */     }
/* 89 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/fx/PacketFXShield.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */