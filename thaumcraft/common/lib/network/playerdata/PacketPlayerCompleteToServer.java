/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ import thaumcraft.api.research.ResearchItem;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.network.PacketHandler;
/*    */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ 
/*    */ public class PacketPlayerCompleteToServer implements IMessage, cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketPlayerCompleteToServer, IMessage>
/*    */ {
/*    */   private String key;
/*    */   private int dim;
/*    */   private String username;
/*    */   private byte type;
/*    */   
/*    */   public PacketPlayerCompleteToServer() {}
/*    */   
/*    */   public PacketPlayerCompleteToServer(String key, String username, int dim, byte type)
/*    */   {
/* 30 */     this.key = key;
/* 31 */     this.dim = dim;
/* 32 */     this.username = username;
/* 33 */     this.type = type;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 38 */     ByteBufUtils.writeUTF8String(buffer, this.key);
/* 39 */     buffer.writeInt(this.dim);
/* 40 */     ByteBufUtils.writeUTF8String(buffer, this.username);
/* 41 */     buffer.writeByte(this.type);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 46 */     this.key = ByteBufUtils.readUTF8String(buffer);
/* 47 */     this.dim = buffer.readInt();
/* 48 */     this.username = ByteBufUtils.readUTF8String(buffer);
/* 49 */     this.type = buffer.readByte();
/*    */   }
/*    */   
/*    */ 
/*    */   public IMessage onMessage(PacketPlayerCompleteToServer message, MessageContext ctx)
/*    */   {
/* 55 */     World world = net.minecraftforge.common.DimensionManager.getWorld(message.dim);
/*    */     
/* 57 */     if ((world == null) || ((ctx.getServerHandler().field_147369_b != null) && (!ctx.getServerHandler().field_147369_b.func_70005_c_().equals(message.username)))) {
/* 58 */       return null;
/*    */     }
/* 60 */     net.minecraft.entity.player.EntityPlayer player = world.func_72924_a(message.username);
/*    */     
/*    */ 
/* 63 */     if ((player != null) && (!ResearchManager.isResearchComplete(message.username, message.key))) {
/* 64 */       if (ResearchManager.doesPlayerHaveRequisites(message.username, message.key)) {
/* 65 */         if (message.type == 0) {
/* 66 */           for (thaumcraft.api.aspects.Aspect a : ResearchCategories.getResearch(message.key).tags.getAspects()) {
/* 67 */             Thaumcraft.proxy.playerKnowledge.addAspectPool(message.username, a, (short)-ResearchCategories.getResearch(message.key).tags.getAmount(a));
/*    */             
/* 69 */             ResearchManager.scheduleSave(player);
/* 70 */             PacketHandler.INSTANCE.sendTo(new PacketAspectPool(a.getTag(), Short.valueOf((short)-ResearchCategories.getResearch(message.key).tags.getAmount(a)), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(message.username, a))), (EntityPlayerMP)player);
/*    */           }
/*    */           
/*    */ 
/* 74 */           PacketHandler.INSTANCE.sendTo(new PacketResearchComplete(message.key), (EntityPlayerMP)player);
/* 75 */           Thaumcraft.proxy.getResearchManager().completeResearch(player, message.key);
/* 76 */           if (ResearchCategories.getResearch(message.key).siblings != null) {
/* 77 */             for (String sibling : ResearchCategories.getResearch(message.key).siblings)
/*    */             {
/* 79 */               if ((!ResearchManager.isResearchComplete(message.username, sibling)) && (ResearchManager.doesPlayerHaveRequisites(message.username, sibling)))
/*    */               {
/* 81 */                 PacketHandler.INSTANCE.sendTo(new PacketResearchComplete(sibling), (EntityPlayerMP)player);
/* 82 */                 Thaumcraft.proxy.getResearchManager().completeResearch(player, sibling);
/*    */               }
/*    */             }
/*    */           }
/* 86 */         } else if (message.type == 1) {
/* 87 */           ResearchManager.createResearchNoteForPlayer(world, player, message.key);
/*    */         }
/* 89 */         world.func_72956_a(player, "thaumcraft:learn", 0.75F, 1.0F);
/*    */       } else {
/* 91 */         player.func_145747_a(new net.minecraft.util.ChatComponentTranslation(net.minecraft.util.StatCollector.func_74838_a("tc.researcherror"), new Object[0]));
/*    */       }
/*    */     }
/* 94 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/network/playerdata/PacketPlayerCompleteToServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */