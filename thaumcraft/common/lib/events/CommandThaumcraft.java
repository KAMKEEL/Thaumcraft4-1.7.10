/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategoryList;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ 
/*     */ public class CommandThaumcraft extends net.minecraft.command.CommandBase
/*     */ {
/*     */   private List aliases;
/*     */   
/*     */   public CommandThaumcraft()
/*     */   {
/*  27 */     this.aliases = new java.util.ArrayList();
/*  28 */     this.aliases.add("thaumcraft");
/*  29 */     this.aliases.add("thaum");
/*  30 */     this.aliases.add("tc");
/*     */   }
/*     */   
/*     */   public String func_71517_b()
/*     */   {
/*  35 */     return "thaumcraft";
/*     */   }
/*     */   
/*     */   public String func_71518_a(ICommandSender icommandsender)
/*     */   {
/*  40 */     return "/thaumcraft <action> [<player> [<params>]]";
/*     */   }
/*     */   
/*     */   public List func_71514_a()
/*     */   {
/*  45 */     return this.aliases;
/*     */   }
/*     */   
/*     */   public int func_82362_a() {
/*  49 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */   public List func_71516_a(ICommandSender icommandsender, String[] astring)
/*     */   {
/*  55 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_82358_a(String[] astring, int i)
/*     */   {
/*  60 */     return i == 1;
/*     */   }
/*     */   
/*     */   public void func_71515_b(ICommandSender icommandsender, String[] astring)
/*     */   {
/*  65 */     if (astring.length == 0) {
/*  66 */       icommandsender.func_145747_a(new ChatComponentTranslation("§cInvalid arguments", new Object[0]));
/*     */       
/*  68 */       icommandsender.func_145747_a(new ChatComponentTranslation("§cUse /thaumcraft help to get help", new Object[0]));
/*     */       
/*  70 */       return;
/*     */     }
/*  72 */     if (astring[0].equalsIgnoreCase("help")) {
/*  73 */       icommandsender.func_145747_a(new ChatComponentTranslation("§3You can also use /thaum or /tc instead of /thaumcraft.", new Object[0]));
/*     */       
/*     */ 
/*  76 */       icommandsender.func_145747_a(new ChatComponentTranslation("§3Use this to give research to a player.", new Object[0]));
/*     */       
/*  78 */       icommandsender.func_145747_a(new ChatComponentTranslation("  /thaumcraft research <list|player> <all|reset|<research>>", new Object[0]));
/*     */       
/*     */ 
/*  81 */       icommandsender.func_145747_a(new ChatComponentTranslation("§3Use this to give aspect research points to a player.", new Object[0]));
/*     */       
/*     */ 
/*  84 */       icommandsender.func_145747_a(new ChatComponentTranslation("  /thaumcraft aspect <player> <aspect|all> <amount>", new Object[0]));
/*     */       
/*  86 */       icommandsender.func_145747_a(new ChatComponentTranslation("§3Use this to give set a players warp level.", new Object[0]));
/*     */       
/*  88 */       icommandsender.func_145747_a(new ChatComponentTranslation("  /thaumcraft warp <player> <add|set> <amount> <PERM|TEMP>", new Object[0]));
/*     */       
/*  90 */       icommandsender.func_145747_a(new ChatComponentTranslation("  not specifying perm or temp will just add normal warp", new Object[0]));
/*     */     }
/*  92 */     else if (astring.length >= 2) {
/*  93 */       if ((astring[0].equalsIgnoreCase("research")) && (astring[1].equalsIgnoreCase("list")))
/*     */       {
/*  95 */         listResearch(icommandsender);
/*     */       }
/*     */       else {
/*  98 */         EntityPlayerMP entityplayermp = func_82359_c(icommandsender, astring[1]);
/*     */         
/*     */ 
/* 101 */         if (astring[0].equalsIgnoreCase("research")) {
/* 102 */           if (astring.length == 3) {
/* 103 */             if (astring[2].equalsIgnoreCase("all")) {
/* 104 */               giveAllResearch(icommandsender, entityplayermp);
/* 105 */             } else if (astring[2].equalsIgnoreCase("reset")) {
/* 106 */               resetResearch(icommandsender, entityplayermp);
/*     */             } else {
/* 108 */               giveResearch(icommandsender, entityplayermp, astring[2]);
/*     */             }
/*     */           }
/*     */           else {
/* 112 */             icommandsender.func_145747_a(new ChatComponentTranslation("§cInvalid arguments", new Object[0]));
/*     */             
/*     */ 
/* 115 */             icommandsender.func_145747_a(new ChatComponentTranslation("§cUse /thaumcraft research <list|player> <all|reset|<research>>", new Object[0]));
/*     */           }
/*     */           
/*     */         }
/* 119 */         else if (astring[0].equalsIgnoreCase("aspect")) {
/* 120 */           if (astring.length == 4) {
/* 121 */             int i = func_71528_a(icommandsender, astring[3], 1);
/* 122 */             giveAspect(icommandsender, entityplayermp, astring[2], i);
/*     */           }
/*     */           else {
/* 125 */             icommandsender.func_145747_a(new ChatComponentTranslation("§cInvalid arguments", new Object[0]));
/*     */             
/*     */ 
/* 128 */             icommandsender.func_145747_a(new ChatComponentTranslation("§cUse /thaumcraft aspect <player> <aspect|all> <amount>", new Object[0]));
/*     */           }
/*     */           
/*     */         }
/* 132 */         else if (astring[0].equalsIgnoreCase("warp")) {
/* 133 */           if ((astring.length >= 4) && (astring[2].equalsIgnoreCase("set"))) {
/* 134 */             int i = func_71528_a(icommandsender, astring[3], 0);
/* 135 */             setWarp(icommandsender, entityplayermp, i, astring.length == 5 ? astring[4] : "");
/*     */           }
/* 137 */           else if ((astring.length >= 4) && (astring[2].equalsIgnoreCase("add"))) {
/* 138 */             int i = func_71532_a(icommandsender, astring[3], -100, 100);
/* 139 */             addWarp(icommandsender, entityplayermp, i, astring.length == 5 ? astring[4] : "");
/*     */           }
/*     */           else {
/* 142 */             icommandsender.func_145747_a(new ChatComponentTranslation("§cInvalid arguments", new Object[0]));
/*     */             
/*     */ 
/* 145 */             icommandsender.func_145747_a(new ChatComponentTranslation("§cUse /thaumcraft warp <player> <add|set> <amount> <PERM|TEMP>", new Object[0]));
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 150 */           icommandsender.func_145747_a(new ChatComponentTranslation("§cInvalid arguments", new Object[0]));
/*     */           
/* 152 */           icommandsender.func_145747_a(new ChatComponentTranslation("§cUse /thaumcraft help to get help", new Object[0]));
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 159 */       icommandsender.func_145747_a(new ChatComponentTranslation("§cInvalid arguments", new Object[0]));
/*     */       
/* 161 */       icommandsender.func_145747_a(new ChatComponentTranslation("§cUse /thaumcraft help to get help", new Object[0]));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void giveAspect(ICommandSender icommandsender, EntityPlayerMP player, String string, int i)
/*     */   {
/* 170 */     if (string.equalsIgnoreCase("all")) {
/* 171 */       for (Aspect aspect : Aspect.aspects.values()) {
/* 172 */         Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), aspect, (short)i);
/*     */       }
/*     */       
/* 175 */       ResearchManager.scheduleSave(player);
/* 176 */       player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " gave you " + i + " of all the aspects.", new Object[0]));
/*     */       
/*     */ 
/* 179 */       icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/*     */       
/* 181 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncAspects(player), player);
/*     */     }
/*     */     else {
/* 184 */       Aspect aspect = Aspect.getAspect(string);
/* 185 */       if (aspect == null) {
/* 186 */         for (Aspect a : Aspect.aspects.values()) {
/* 187 */           if (string.equalsIgnoreCase(a.getName())) {
/* 188 */             aspect = a;
/* 189 */             break;
/*     */           }
/*     */         }
/*     */       }
/* 193 */       if (aspect != null)
/*     */       {
/* 195 */         Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), aspect, (short)i);
/*     */         
/* 197 */         ResearchManager.scheduleSave(player);
/* 198 */         PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncAspects(player), player);
/*     */         
/*     */ 
/* 201 */         player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " gave you " + i + " " + aspect.getName(), new Object[0]));
/*     */         
/*     */ 
/* 204 */         icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/*     */       }
/*     */       else {
/* 207 */         icommandsender.func_145747_a(new ChatComponentTranslation("§cAspect does not exist.", new Object[0]));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void setWarp(ICommandSender icommandsender, EntityPlayerMP player, int i, String type)
/*     */   {
/* 215 */     if (type.equalsIgnoreCase("PERM")) {
/* 216 */       Thaumcraft.proxy.playerKnowledge.setWarpPerm(player.func_70005_c_(), i);
/* 217 */       ResearchManager.scheduleSave(player);
/* 218 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)0), player);
/*     */     }
/* 220 */     else if (type.equalsIgnoreCase("TEMP")) {
/* 221 */       Thaumcraft.proxy.playerKnowledge.setWarpTemp(player.func_70005_c_(), i);
/* 222 */       ResearchManager.scheduleSave(player);
/* 223 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)2), player);
/*     */     } else {
/* 225 */       Thaumcraft.proxy.playerKnowledge.setWarpSticky(player.func_70005_c_(), i);
/* 226 */       ResearchManager.scheduleSave(player);
/* 227 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)1), player);
/*     */     }
/* 229 */     player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " set your warp to " + i, new Object[0]));
/*     */     
/*     */ 
/* 232 */     icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/*     */   }
/*     */   
/*     */ 
/*     */   private void addWarp(ICommandSender icommandsender, EntityPlayerMP player, int i, String type)
/*     */   {
/* 238 */     if (type.equalsIgnoreCase("PERM")) {
/* 239 */       Thaumcraft.proxy.playerKnowledge.addWarpPerm(player.func_70005_c_(), i);
/* 240 */       ResearchManager.scheduleSave(player);
/* 241 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)0), player);
/* 242 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketWarpMessage(player, (byte)0, i), player);
/*     */     }
/* 244 */     else if (type.equalsIgnoreCase("TEMP")) {
/* 245 */       Thaumcraft.proxy.playerKnowledge.addWarpTemp(player.func_70005_c_(), i);
/* 246 */       ResearchManager.scheduleSave(player);
/* 247 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)2), player);
/* 248 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketWarpMessage(player, (byte)2, i), player);
/*     */     } else {
/* 250 */       Thaumcraft.proxy.playerKnowledge.addWarpSticky(player.func_70005_c_(), i);
/* 251 */       ResearchManager.scheduleSave(player);
/* 252 */       PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte)1), player);
/* 253 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketWarpMessage(player, (byte)1, i), player);
/*     */     }
/*     */     
/* 256 */     player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " added " + i + " warp to your total.", new Object[0]));
/*     */     
/* 258 */     icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/*     */   }
/*     */   
/*     */   private void listResearch(ICommandSender icommandsender)
/*     */   {
/* 263 */     Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
/*     */     
/* 265 */     for (ResearchCategoryList cat : rc) {
/* 266 */       Collection<ResearchItem> rl = cat.research.values();
/* 267 */       for (ResearchItem ri : rl) {
/* 268 */         icommandsender.func_145747_a(new ChatComponentTranslation("§5" + ri.key, new Object[0]));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   void giveResearch(ICommandSender icommandsender, EntityPlayerMP player, String research)
/*     */   {
/* 276 */     if (ResearchCategories.getResearch(research) != null) {
/* 277 */       giveRecursiveResearch(player, research);
/* 278 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncResearch(player), player);
/*     */       
/* 280 */       player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " gave you " + research + " research and its requisites.", new Object[0]));
/*     */       
/*     */ 
/* 283 */       icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/*     */     }
/*     */     else {
/* 286 */       icommandsender.func_145747_a(new ChatComponentTranslation("§cResearch does not exist.", new Object[0]));
/*     */     }
/*     */   }
/*     */   
/*     */   void giveRecursiveResearch(EntityPlayerMP player, String research)
/*     */   {
/* 292 */     if (!ResearchManager.isResearchComplete(player.func_70005_c_(), research))
/*     */     {
/* 294 */       Thaumcraft.proxy.getResearchManager().completeResearch(player, research);
/*     */       
/* 296 */       if (ResearchCategories.getResearch(research).parents != null) {
/* 297 */         for (String rsi : ResearchCategories.getResearch(research).parents)
/* 298 */           giveRecursiveResearch(player, rsi);
/*     */       }
/* 300 */       if (ResearchCategories.getResearch(research).parentsHidden != null) {
/* 301 */         for (String rsi : ResearchCategories.getResearch(research).parentsHidden)
/* 302 */           giveRecursiveResearch(player, rsi);
/*     */       }
/* 304 */       if (ResearchCategories.getResearch(research).siblings != null) {
/* 305 */         for (String rsi : ResearchCategories.getResearch(research).siblings)
/* 306 */           giveRecursiveResearch(player, rsi);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   void giveAllResearch(ICommandSender icommandsender, EntityPlayerMP player) {
/* 312 */     Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
/*     */     
/* 314 */     for (ResearchCategoryList cat : rc) {
/* 315 */       Collection<ResearchItem> rl = cat.research.values();
/* 316 */       for (ResearchItem ri : rl) {
/* 317 */         if (!ResearchManager.isResearchComplete(player.func_70005_c_(), ri.key))
/*     */         {
/* 319 */           Thaumcraft.proxy.getResearchManager().completeResearch(player, ri.key);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 324 */     player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " has given you all research.", new Object[0]));
/*     */     
/*     */ 
/* 327 */     icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/*     */     
/* 329 */     PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncResearch(player), player);
/*     */   }
/*     */   
/*     */   void resetResearch(ICommandSender icommandsender, EntityPlayerMP player) {
/* 333 */     Thaumcraft.proxy.getPlayerKnowledge().researchCompleted.remove(player.func_70005_c_());
/*     */     
/*     */ 
/* 336 */     Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
/*     */     
/* 338 */     for (ResearchCategoryList cat : rc) {
/* 339 */       Collection<ResearchItem> res = cat.research.values();
/* 340 */       for (ResearchItem ri : res) {
/* 341 */         if (ri.isAutoUnlock()) {
/* 342 */           Thaumcraft.proxy.getResearchManager().completeResearch(player, ri.key);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 348 */     player.func_145747_a(new ChatComponentTranslation("§5" + icommandsender.func_70005_c_() + " has reset you research.", new Object[0]));
/*     */     
/*     */ 
/* 351 */     icommandsender.func_145747_a(new ChatComponentTranslation("§5Success!", new Object[0]));
/*     */     
/* 353 */     PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncResearch(player), player);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/events/CommandThaumcraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */