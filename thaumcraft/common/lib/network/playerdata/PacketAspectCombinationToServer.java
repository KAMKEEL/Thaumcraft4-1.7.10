/*     */ package thaumcraft.common.lib.network.playerdata;
/*     */ 
/*     */ import cpw.mods.fml.common.network.ByteBufUtils;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.tiles.TileResearchTable;
/*     */ 
/*     */ public class PacketAspectCombinationToServer implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketAspectCombinationToServer, IMessage>
/*     */ {
/*     */   private int dim;
/*     */   private int playerid;
/*     */   private int x;
/*     */   private int y;
/*     */   private int z;
/*     */   Aspect aspect1;
/*     */   Aspect aspect2;
/*     */   boolean ab1;
/*     */   boolean ab2;
/*     */   
/*     */   public PacketAspectCombinationToServer() {}
/*     */   
/*     */   public PacketAspectCombinationToServer(EntityPlayer player, int x, int y, int z, Aspect aspect1, Aspect aspect2, boolean ab1, boolean ab2, boolean ret)
/*     */   {
/*  33 */     this.dim = player.field_70170_p.field_73011_w.field_76574_g;
/*  34 */     this.playerid = player.func_145782_y();
/*  35 */     this.x = x;
/*  36 */     this.y = y;
/*  37 */     this.z = z;
/*  38 */     this.aspect1 = aspect1;
/*  39 */     this.aspect2 = aspect2;
/*  40 */     this.ab1 = ab1;
/*  41 */     this.ab2 = ab2;
/*     */   }
/*     */   
/*     */ 
/*     */   public void toBytes(ByteBuf buffer)
/*     */   {
/*  47 */     buffer.writeInt(this.dim);
/*  48 */     buffer.writeInt(this.playerid);
/*  49 */     buffer.writeInt(this.x);
/*  50 */     buffer.writeInt(this.y);
/*  51 */     buffer.writeInt(this.z);
/*  52 */     ByteBufUtils.writeUTF8String(buffer, this.aspect1.getTag());
/*  53 */     ByteBufUtils.writeUTF8String(buffer, this.aspect2.getTag());
/*  54 */     buffer.writeBoolean(this.ab1);
/*  55 */     buffer.writeBoolean(this.ab2);
/*     */   }
/*     */   
/*     */ 
/*     */   public void fromBytes(ByteBuf buffer)
/*     */   {
/*  61 */     this.dim = buffer.readInt();
/*  62 */     this.playerid = buffer.readInt();
/*  63 */     this.x = buffer.readInt();
/*  64 */     this.y = buffer.readInt();
/*  65 */     this.z = buffer.readInt();
/*  66 */     this.aspect1 = Aspect.getAspect(ByteBufUtils.readUTF8String(buffer));
/*  67 */     this.aspect2 = Aspect.getAspect(ByteBufUtils.readUTF8String(buffer));
/*  68 */     this.ab1 = buffer.readBoolean();
/*  69 */     this.ab2 = buffer.readBoolean();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IMessage onMessage(PacketAspectCombinationToServer message, MessageContext ctx)
/*     */   {
/*  76 */     World world = net.minecraftforge.common.DimensionManager.getWorld(message.dim);
/*  77 */     if ((world == null) || ((ctx.getServerHandler().field_147369_b != null) && (ctx.getServerHandler().field_147369_b.func_145782_y() != message.playerid))) { return null;
/*     */     }
/*  79 */     Entity player = world.func_73045_a(message.playerid);
/*     */     
/*  81 */     if ((player != null) && ((player instanceof EntityPlayer)) && (message.aspect1 != null) && (message.aspect1 != null)) {
/*  82 */       Aspect combo = thaumcraft.common.lib.research.ResearchManager.getCombinationResult(message.aspect1, message.aspect2);
/*     */       
/*  84 */       if (((Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect1) > 0) || (message.ab1)) && ((Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect2) > 0) || (message.ab2)))
/*     */       {
/*     */ 
/*  87 */         TileEntity rt = player.field_70170_p.func_147438_o(message.x, message.y, message.z);
/*     */         
/*  89 */         if ((Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect1) <= 0) && (message.ab1)) {
/*  90 */           if ((rt != null) && ((rt instanceof TileResearchTable))) {
/*  91 */             ((TileResearchTable)rt).bonusAspects.remove(message.aspect1, 1);
/*  92 */             player.field_70170_p.func_147471_g(message.x, message.y, message.z);
/*  93 */             rt.func_70296_d();
/*     */           }
/*     */         } else {
/*  96 */           Thaumcraft.proxy.playerKnowledge.addAspectPool(((EntityPlayer)player).func_70005_c_(), message.aspect1, (short)-1);
/*  97 */           thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendTo(new PacketAspectPool(message.aspect1.getTag(), Short.valueOf((short)0), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect1))), (net.minecraft.entity.player.EntityPlayerMP)player);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 102 */         if ((Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect2) <= 0) && (message.ab2)) {
/* 103 */           if ((rt != null) && ((rt instanceof TileResearchTable))) {
/* 104 */             ((TileResearchTable)rt).bonusAspects.remove(message.aspect2, 1);
/* 105 */             player.field_70170_p.func_147471_g(message.x, message.y, message.z);
/* 106 */             rt.func_70296_d();
/*     */           }
/*     */         } else {
/* 109 */           Thaumcraft.proxy.playerKnowledge.addAspectPool(((EntityPlayer)player).func_70005_c_(), message.aspect2, (short)-1);
/* 110 */           thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendTo(new PacketAspectPool(message.aspect2.getTag(), Short.valueOf((short)0), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect2))), (net.minecraft.entity.player.EntityPlayerMP)player);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 115 */         if (combo != null) {
/* 116 */           thaumcraft.common.lib.research.ScanManager.checkAndSyncAspectKnowledge((EntityPlayer)player, combo, 1);
/*     */         }
/* 118 */         thaumcraft.common.lib.research.ResearchManager.scheduleSave((EntityPlayer)player);
/*     */       }
/*     */     }
/* 121 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketAspectCombinationToServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */