/*      */ package thaumcraft.common.lib.research;
/*      */ 
/*      */ import com.google.common.io.Files;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import cpw.mods.fml.common.ObfuscationReflectionHelper;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.inventory.Container;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.CompressedStreamTools;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagInt;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.server.management.ItemInWorldManager;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import net.minecraft.world.storage.IPlayerFileData;
/*      */ import net.minecraft.world.storage.SaveHandler;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import thaumcraft.api.IScribeTools;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.research.ResearchCategories;
/*      */ import thaumcraft.api.research.ResearchCategoryList;
/*      */ import thaumcraft.api.research.ResearchItem;
/*      */ import thaumcraft.common.CommonProxy;
/*      */ import thaumcraft.common.Thaumcraft;
/*      */ import thaumcraft.common.config.Config;
/*      */ import thaumcraft.common.config.ConfigItems;
/*      */ import thaumcraft.common.lib.events.EventHandlerRunic;
/*      */ import thaumcraft.common.lib.utils.HexUtils;
/*      */ import thaumcraft.common.lib.utils.HexUtils.Hex;
/*      */ 
/*      */ public class ResearchManager
/*      */ {
/*      */   public static boolean createClue(World world, EntityPlayer player, Object clue, AspectList aspects)
/*      */   {
/*   51 */     ArrayList<String> keys = new ArrayList();
/*   52 */     for (ResearchCategoryList rcl : ResearchCategories.researchCategories.values())
/*      */     {
/*   54 */       for (ResearchItem ri : rcl.research.values()) {
/*   55 */         boolean valid = (ri.tags != null) && (ri.tags.size() > 0) && ((ri.isLost()) || (ri.isHidden())) && (!isResearchComplete(player.func_70005_c_(), ri.key)) && (!isResearchComplete(player.func_70005_c_(), "@" + ri.key));
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   61 */         if (valid) {
/*   62 */           if (((clue instanceof ItemStack)) && (ri.getItemTriggers() != null) && (ri.getItemTriggers().length > 0)) {
/*   63 */             ItemStack[] arr$ = ri.getItemTriggers();int len$ = arr$.length; for (int i$ = 0;; i$++) { if (i$ >= len$) break label266; ItemStack stack = arr$[i$];
/*   64 */               if (thaumcraft.common.lib.utils.InventoryUtils.areItemStacksEqual(stack, (ItemStack)clue, true, true, false)) {
/*   65 */                 keys.add(ri.key);
/*   66 */                 break;
/*      */               }
/*      */             }
/*      */           }
/*   70 */           else if (((clue instanceof String)) && (ri.getEntityTriggers() != null) && (ri.getEntityTriggers().length > 0)) {
/*   71 */             String[] arr$ = ri.getEntityTriggers();int len$ = arr$.length; for (int i$ = 0;; i$++) { if (i$ >= len$) break label351; String entity = arr$[i$];
/*   72 */               if (clue.equals(entity)) {
/*   73 */                 keys.add(ri.key);
/*   74 */                 break;
/*      */               }
/*      */             }
/*      */           }
/*   78 */           if ((aspects != null) && (aspects.size() > 0) && (ri.getAspectTriggers() != null) && (ri.getAspectTriggers().length > 0)) {
/*   79 */             Aspect[] arr$ = ri.getAspectTriggers();int len$ = arr$.length; for (int i$ = 0;; i$++) { if (i$ >= len$) break label437; Aspect aspect = arr$[i$];
/*   80 */               if (aspects.getAmount(aspect) > 0) {
/*   81 */                 keys.add(ri.key);
/*   82 */                 break;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       } }
/*      */     label266:
/*      */     label351:
/*      */     label437:
/*   91 */     if (keys.size() > 0) {
/*   92 */       String key = (String)keys.get(world.field_73012_v.nextInt(keys.size()));
/*   93 */       thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketResearchComplete("@" + key), (EntityPlayerMP)player);
/*   94 */       Thaumcraft.proxy.getResearchManager().completeResearch(player, "@" + key);
/*   95 */       return true;
/*      */     }
/*      */     
/*   98 */     return false;
/*      */   }
/*      */   
/*      */   public static ItemStack createResearchNoteForPlayer(World world, EntityPlayer player, String key) {
/*  102 */     ItemStack note = null;
/*  103 */     boolean addslot = false;
/*  104 */     int slot = getResearchSlot(player, key);
/*  105 */     if (slot >= 0) {
/*  106 */       note = player.field_71071_by.func_70301_a(slot);
/*  107 */     } else if ((consumeInkFromPlayer(player, false)) && (player.field_71071_by.func_146026_a(net.minecraft.init.Items.field_151121_aF)))
/*      */     {
/*  109 */       consumeInkFromPlayer(player, true);
/*  110 */       note = createNote(new ItemStack(ConfigItems.itemResearchNotes), key, world);
/*  111 */       if (!player.field_71071_by.func_70441_a(note)) {
/*  112 */         player.func_71019_a(note, false);
/*      */       }
/*  114 */       player.field_71069_bz.func_75142_b();
/*      */     }
/*      */     
/*  117 */     return note;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  134 */   static ArrayList<ResearchItem> allHiddenResearch = null;
/*      */   
/*      */   public static String findHiddenResearch(EntityPlayer player) {
/*  137 */     if (allHiddenResearch == null) {
/*  138 */       allHiddenResearch = new ArrayList();
/*      */       
/*  140 */       Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
/*  141 */       for (ResearchCategoryList cat : rc) {
/*  142 */         Collection<ResearchItem> rl = cat.research.values();
/*  143 */         for (ResearchItem ri : rl) {
/*  144 */           if ((ri.isHidden()) && (ri.tags != null) && (ri.tags.size() > 0)) {
/*  145 */             allHiddenResearch.add(ri);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  151 */     ArrayList<String> keys = new ArrayList();
/*      */     
/*  153 */     for (ResearchItem research : allHiddenResearch) {
/*  154 */       if ((!isResearchComplete(player.func_70005_c_(), research.key)) && (doesPlayerHaveRequisites(player.func_70005_c_(), research.key)) && (
/*      */       
/*      */ 
/*  157 */         (research.getItemTriggers() != null) || (research.getEntityTriggers() != null) || (research.getAspectTriggers() != null)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  162 */         keys.add(research.key);
/*      */       }
/*      */     }
/*  165 */     Random rand = new Random(player.field_70170_p.func_72820_D() / 10L / 5L);
/*  166 */     if (keys.size() > 0) {
/*  167 */       int r = rand.nextInt(keys.size());
/*  168 */       return (String)keys.get(r); }
/*  169 */     return "FAIL";
/*      */   }
/*      */   
/*      */ 
/*  173 */   static ArrayList<ResearchItem> allValidResearch = null;
/*      */   private static final String RESEARCH_TAG = "THAUMCRAFT.RESEARCH";
/*      */   
/*  176 */   public static String findMatchingResearch(EntityPlayer player, Aspect aspect) { String randomMatch = null;
/*      */     
/*  178 */     if (allValidResearch == null) {
/*  179 */       allValidResearch = new ArrayList();
/*      */       
/*  181 */       Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
/*  182 */       for (ResearchCategoryList cat : rc) {
/*  183 */         Collection<ResearchItem> rl = cat.research.values();
/*  184 */         for (ResearchItem ri : rl) {
/*  185 */           boolean secondary = ((ri.isSecondary()) && (Config.researchDifficulty == 0)) || (Config.researchDifficulty == -1);
/*  186 */           if ((!secondary) && (!ri.isHidden()) && (!ri.isLost()) && (!ri.isAutoUnlock()) && (!ri.isVirtual()) && (!ri.isStub())) {
/*  187 */             allValidResearch.add(ri);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  193 */     ArrayList<String> keys = new ArrayList();
/*      */     
/*  195 */     for (ResearchItem research : allValidResearch) {
/*  196 */       if ((!isResearchComplete(player.func_70005_c_(), research.key)) && (doesPlayerHaveRequisites(player.func_70005_c_(), research.key)))
/*      */       {
/*      */ 
/*      */ 
/*  200 */         if (research.tags.getAmount(aspect) > 0) {
/*  201 */           keys.add(research.key);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  206 */     if (keys.size() > 0) {
/*  207 */       randomMatch = (String)keys.get(player.field_70170_p.field_73012_v.nextInt(keys.size()));
/*      */     }
/*      */     
/*  210 */     return randomMatch;
/*      */   }
/*      */   
/*      */   public static int getResearchSlot(EntityPlayer player, String key)
/*      */   {
/*  215 */     ItemStack[] inv = player.field_71071_by.field_70462_a;
/*  216 */     if ((inv == null) || (inv.length == 0))
/*  217 */       return -1;
/*  218 */     for (int a = 0; a < inv.length; a++) {
/*  219 */       if ((inv[a] != null) && (inv[a].func_77973_b() != null) && (inv[a].func_77973_b() == ConfigItems.itemResearchNotes) && (getData(inv[a]) != null) && (getData(inv[a]).key.equals(key)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  224 */         return a;
/*      */       }
/*      */     }
/*  227 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean consumeInkFromPlayer(EntityPlayer player, boolean doit) {
/*  231 */     ItemStack[] inv = player.field_71071_by.field_70462_a;
/*  232 */     for (int a = 0; a < inv.length; a++) {
/*  233 */       if ((inv[a] != null) && ((inv[a].func_77973_b() instanceof IScribeTools)) && (inv[a].func_77960_j() < inv[a].func_77958_k()))
/*      */       {
/*      */ 
/*  236 */         if (doit)
/*  237 */           inv[a].func_77972_a(1, player);
/*  238 */         return true;
/*      */       }
/*      */     }
/*  241 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean consumeInkFromTable(ItemStack stack, boolean doit)
/*      */   {
/*  246 */     if ((stack != null) && ((stack.func_77973_b() instanceof IScribeTools)) && (stack.func_77960_j() < stack.func_77958_k()))
/*      */     {
/*  248 */       if (doit)
/*  249 */         stack.func_77964_b(stack.func_77960_j() + 1);
/*  250 */       return true;
/*      */     }
/*  252 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean checkResearchCompletion(ItemStack contents, ResearchNoteData note, String username) {
/*  256 */     ArrayList<String> checked = new ArrayList();
/*  257 */     ArrayList<String> main = new ArrayList();
/*  258 */     ArrayList<String> remains = new ArrayList();
/*  259 */     for (HexUtils.Hex hex : note.hexes.values()) {
/*  260 */       if (((HexEntry)note.hexEntries.get(hex.toString())).type == 1) {
/*  261 */         main.add(hex.toString());
/*      */       }
/*      */     }
/*  264 */     for (HexUtils.Hex hex : note.hexes.values()) {
/*  265 */       if (((HexEntry)note.hexEntries.get(hex.toString())).type == 1) {
/*  266 */         main.remove(hex.toString());
/*  267 */         checkConnections(note, hex, checked, main, remains, username);
/*  268 */         break;
/*      */       }
/*      */     }
/*      */     
/*  272 */     if (main.size() == 0) {
/*  273 */       ArrayList<String> remove = new ArrayList();
/*  274 */       for (HexUtils.Hex hex : note.hexes.values()) {
/*  275 */         if ((((HexEntry)note.hexEntries.get(hex.toString())).type != 1) && (!remains.contains(hex.toString()))) {
/*  276 */           remove.add(hex.toString());
/*      */         }
/*      */       }
/*  279 */       for (String s : remove) {
/*  280 */         note.hexEntries.remove(s);
/*  281 */         note.hexes.remove(s);
/*      */       }
/*  283 */       note.complete = true;
/*  284 */       updateData(contents, note);
/*  285 */       return true;
/*      */     }
/*  287 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   private static void checkConnections(ResearchNoteData note, HexUtils.Hex hex, ArrayList<String> checked, ArrayList<String> main, ArrayList<String> remains, String username)
/*      */   {
/*  293 */     checked.add(hex.toString());
/*  294 */     for (int a = 0; a < 6; a++) {
/*  295 */       HexUtils.Hex target = hex.getNeighbour(a);
/*  296 */       if ((!checked.contains(target.toString())) && 
/*  297 */         (note.hexEntries.containsKey(target.toString())) && (((HexEntry)note.hexEntries.get(target.toString())).type >= 1)) {
/*  298 */         Aspect aspect1 = ((HexEntry)note.hexEntries.get(hex.toString())).aspect;
/*  299 */         Aspect aspect2 = ((HexEntry)note.hexEntries.get(target.toString())).aspect;
/*  300 */         if ((Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(username, aspect1)) && (Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(username, aspect2)) && (((!aspect1.isPrimal()) && ((aspect1.getComponents()[0] == aspect2) || (aspect1.getComponents()[1] == aspect2))) || ((!aspect2.isPrimal()) && ((aspect2.getComponents()[0] == aspect1) || (aspect2.getComponents()[1] == aspect1)))))
/*      */         {
/*      */ 
/*      */ 
/*  304 */           remains.add(target.toString());
/*  305 */           if (((HexEntry)note.hexEntries.get(target.toString())).type == 1) {
/*  306 */             main.remove(target.toString());
/*      */           }
/*  308 */           checkConnections(note, target, checked, main, remains, username);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static class HexEntry {
/*      */     public Aspect aspect;
/*      */     public int type;
/*      */     
/*      */     public HexEntry(Aspect aspect, int type) {
/*  319 */       this.aspect = aspect;
/*  320 */       this.type = type;
/*      */     }
/*      */   }
/*      */   
/*      */   public static ItemStack createNote(ItemStack stack, String key, World world)
/*      */   {
/*  326 */     ResearchItem rr = ResearchCategories.getResearch(key);
/*  327 */     Aspect primaryaspect = rr.getResearchPrimaryTag();
/*      */     
/*  329 */     if (primaryaspect == null) { return null;
/*      */     }
/*  331 */     if (stack.field_77990_d == null) { stack.func_77982_d(new NBTTagCompound());
/*      */     }
/*  333 */     stack.field_77990_d.func_74778_a("key", key);
/*  334 */     stack.field_77990_d.func_74768_a("color", primaryaspect.getColor());
/*  335 */     stack.field_77990_d.func_74757_a("complete", false);
/*  336 */     stack.field_77990_d.func_74768_a("copies", 0);
/*      */     
/*      */ 
/*  339 */     int radius = 1 + Math.min(3, rr.getComplexity());
/*      */     
/*      */ 
/*  342 */     HashMap<String, HexUtils.Hex> hexLocs = HexUtils.generateHexes(radius);
/*  343 */     ArrayList<HexUtils.Hex> outerRing = HexUtils.distributeRingRandomly(radius, rr.tags.size(), world.field_73012_v);
/*      */     
/*      */ 
/*  346 */     HashMap<String, HexEntry> hexEntries = new HashMap();
/*  347 */     HashMap<String, HexUtils.Hex> hexes = new HashMap();
/*  348 */     for (HexUtils.Hex hex : hexLocs.values()) {
/*  349 */       hexes.put(hex.toString(), hex);
/*  350 */       hexEntries.put(hex.toString(), new HexEntry(null, 0));
/*      */     }
/*      */     
/*      */ 
/*  354 */     int count = 0;
/*  355 */     for (HexUtils.Hex hex : outerRing) {
/*  356 */       hexes.put(hex.toString(), hex);
/*  357 */       hexEntries.put(hex.toString(), new HexEntry(rr.tags.getAspects()[count], 1));
/*  358 */       count++;
/*      */     }
/*      */     
/*      */ 
/*  362 */     if (rr.getComplexity() > 1) {
/*  363 */       int blanks = rr.getComplexity() * 2;
/*  364 */       HexUtils.Hex[] temp = (HexUtils.Hex[])hexes.values().toArray(new HexUtils.Hex[0]);
/*  365 */       while (blanks > 0) {
/*  366 */         int indx = world.field_73012_v.nextInt(temp.length);
/*  367 */         if ((hexEntries.get(temp[indx].toString()) != null) && (((HexEntry)hexEntries.get(temp[indx].toString())).type == 0))
/*      */         {
/*  369 */           boolean gtg = true;
/*  370 */           for (int n = 0; n < 6; n++) {
/*  371 */             HexUtils.Hex neighbour = temp[indx].getNeighbour(n);
/*  372 */             if ((hexes.containsKey(neighbour.toString())) && (((HexEntry)hexEntries.get(neighbour.toString())).type == 1)) {
/*  373 */               int cc = 0;
/*  374 */               for (int q = 0; q < 6; q++) {
/*  375 */                 if (hexes.containsKey(((HexUtils.Hex)hexes.get(neighbour.toString())).getNeighbour(q).toString())) {
/*  376 */                   cc++;
/*      */                 }
/*  378 */                 if (cc >= 2) break;
/*      */               }
/*  380 */               if (cc < 2) {
/*  381 */                 gtg = false;
/*  382 */                 break;
/*      */               }
/*      */             }
/*      */           }
/*  386 */           if (gtg)
/*      */           {
/*  388 */             hexes.remove(temp[indx].toString());
/*  389 */             hexEntries.remove(temp[indx].toString());
/*  390 */             temp = (HexUtils.Hex[])hexes.values().toArray(new HexUtils.Hex[0]);
/*  391 */             blanks--;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  398 */     NBTTagList gridtag = new NBTTagList();
/*  399 */     for (HexUtils.Hex hex : hexes.values()) {
/*  400 */       NBTTagCompound gt = new NBTTagCompound();
/*  401 */       gt.func_74774_a("hexq", (byte)hex.q);
/*  402 */       gt.func_74774_a("hexr", (byte)hex.r);
/*  403 */       gt.func_74774_a("type", (byte)((HexEntry)hexEntries.get(hex.toString())).type);
/*  404 */       if (((HexEntry)hexEntries.get(hex.toString())).aspect != null)
/*  405 */         gt.func_74778_a("aspect", ((HexEntry)hexEntries.get(hex.toString())).aspect.getTag());
/*  406 */       gridtag.func_74742_a(gt);
/*      */     }
/*  408 */     stack.field_77990_d.func_74782_a("hexgrid", gridtag);
/*      */     
/*  410 */     return stack;
/*      */   }
/*      */   
/*      */   public static ResearchNoteData getData(ItemStack stack) {
/*  414 */     if (stack == null) return null;
/*  415 */     ResearchNoteData data = new ResearchNoteData();
/*      */     
/*  417 */     if (stack.field_77990_d == null) { return null;
/*      */     }
/*  419 */     data.key = stack.field_77990_d.func_74779_i("key");
/*  420 */     data.color = stack.field_77990_d.func_74762_e("color");
/*  421 */     data.complete = stack.field_77990_d.func_74767_n("complete");
/*  422 */     data.copies = stack.field_77990_d.func_74762_e("copies");
/*      */     
/*  424 */     NBTTagList grid = stack.field_77990_d.func_150295_c("hexgrid", 10);
/*  425 */     data.hexEntries = new HashMap();
/*      */     
/*  427 */     for (int x = 0; x < grid.func_74745_c(); x++) {
/*  428 */       NBTTagCompound nbt = grid.func_150305_b(x);
/*  429 */       int q = nbt.func_74771_c("hexq");
/*  430 */       int r = nbt.func_74771_c("hexr");
/*  431 */       int type = nbt.func_74771_c("type");
/*  432 */       String tag = nbt.func_74779_i("aspect");
/*  433 */       Aspect aspect = tag != null ? Aspect.getAspect(tag) : null;
/*  434 */       HexUtils.Hex hex = new HexUtils.Hex(q, r);
/*  435 */       data.hexEntries.put(hex.toString(), new HexEntry(aspect, type));
/*  436 */       data.hexes.put(hex.toString(), hex);
/*      */     }
/*      */     
/*  439 */     return data;
/*      */   }
/*      */   
/*      */   public static void updateData(ItemStack stack, ResearchNoteData data)
/*      */   {
/*  444 */     if (stack.field_77990_d == null) {
/*  445 */       stack.func_77982_d(new NBTTagCompound());
/*      */     }
/*      */     
/*  448 */     stack.field_77990_d.func_74778_a("key", data.key);
/*  449 */     stack.field_77990_d.func_74768_a("color", data.color);
/*  450 */     stack.field_77990_d.func_74757_a("complete", data.complete);
/*  451 */     stack.field_77990_d.func_74768_a("copies", data.copies);
/*      */     
/*  453 */     NBTTagList gridtag = new NBTTagList();
/*  454 */     for (HexUtils.Hex hex : data.hexes.values()) {
/*  455 */       NBTTagCompound gt = new NBTTagCompound();
/*  456 */       gt.func_74774_a("hexq", (byte)hex.q);
/*  457 */       gt.func_74774_a("hexr", (byte)hex.r);
/*  458 */       gt.func_74774_a("type", (byte)((HexEntry)data.hexEntries.get(hex.toString())).type);
/*  459 */       if (((HexEntry)data.hexEntries.get(hex.toString())).aspect != null)
/*  460 */         gt.func_74778_a("aspect", ((HexEntry)data.hexEntries.get(hex.toString())).aspect.getTag());
/*  461 */       gridtag.func_74742_a(gt);
/*      */     }
/*  463 */     stack.field_77990_d.func_74782_a("hexgrid", gridtag);
/*      */   }
/*      */   
/*      */   public static boolean isResearchComplete(String playername, String key)
/*      */   {
/*  468 */     if ((!key.startsWith("@")) && (ResearchCategories.getResearch(key) == null))
/*  469 */       return false;
/*  470 */     List completed = getResearchForPlayer(playername);
/*  471 */     if ((completed != null) && (completed.size() > 0)) {
/*  472 */       return completed.contains(key);
/*      */     }
/*  474 */     return false;
/*      */   }
/*      */   
/*      */   public static ArrayList<String> getResearchForPlayer(String playername) {
/*  478 */     ArrayList<String> out = (ArrayList)Thaumcraft.proxy.getCompletedResearch().get(playername);
/*      */     
/*      */     try
/*      */     {
/*  482 */       if ((out == null) && (Thaumcraft.proxy.getClientWorld() == null) && (MinecraftServer.func_71276_C() != null))
/*      */       {
/*  484 */         Thaumcraft.proxy.getCompletedResearch().put(playername, new ArrayList());
/*  485 */         UUID id = UUID.nameUUIDFromBytes(("OfflinePlayer:" + playername).getBytes(com.google.common.base.Charsets.UTF_8));
/*  486 */         EntityPlayerMP entityplayermp = new EntityPlayerMP(MinecraftServer.func_71276_C(), MinecraftServer.func_71276_C().func_71218_a(0), new GameProfile(id, playername), new ItemInWorldManager(MinecraftServer.func_71276_C().func_71218_a(0)));
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  492 */         if (entityplayermp != null)
/*      */         {
/*  494 */           IPlayerFileData playerNBTManagerObj = MinecraftServer.func_71276_C().func_71218_a(0).func_72860_G().func_75756_e();
/*      */           
/*  496 */           SaveHandler sh = (SaveHandler)playerNBTManagerObj;
/*  497 */           File dir = (File)ObfuscationReflectionHelper.getPrivateValue(SaveHandler.class, sh, new String[] { "playersDirectory", "field_75771_c" });
/*  498 */           File file1 = new File(dir, id + ".thaum");
/*  499 */           File file2 = new File(dir, id + ".thaumbak");
/*  500 */           loadPlayerData(entityplayermp, file1, file2, false);
/*      */         }
/*      */         
/*  503 */         out = (ArrayList)Thaumcraft.proxy.getCompletedResearch().get(playername);
/*      */       }
/*      */     }
/*      */     catch (Exception e) {}
/*      */     
/*  508 */     return out;
/*      */   }
/*      */   
/*      */   public static ArrayList<String> getResearchForPlayerSafe(String playername) {
/*  512 */     return (ArrayList)Thaumcraft.proxy.getCompletedResearch().get(playername);
/*      */   }
/*      */   
/*      */   public static boolean doesPlayerHaveRequisites(String playername, String key) {
/*  516 */     boolean out = true;
/*  517 */     String[] parents = ResearchCategories.getResearch(key).parents;
/*  518 */     if ((parents != null) && (parents.length > 0)) {
/*  519 */       out = false;
/*  520 */       List<String> completed = getResearchForPlayer(playername);
/*  521 */       if ((completed != null) && (completed.size() > 0)) {
/*  522 */         out = true;
/*  523 */         for (String item : parents) {
/*  524 */           if (!completed.contains(item)) {
/*  525 */             return false;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  531 */     parents = ResearchCategories.getResearch(key).parentsHidden;
/*  532 */     if ((parents != null) && (parents.length > 0)) {
/*  533 */       out = false;
/*  534 */       List<String> completed = getResearchForPlayer(playername);
/*  535 */       if ((completed != null) && (completed.size() > 0)) {
/*  536 */         out = true;
/*  537 */         for (String item : parents) {
/*  538 */           if (!completed.contains(item)) {
/*  539 */             return false;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  545 */     return out;
/*      */   }
/*      */   
/*      */   public static Aspect getCombinationResult(Aspect aspect1, Aspect aspect2)
/*      */   {
/*  550 */     Collection<Aspect> aspects = Aspect.aspects.values();
/*  551 */     for (Aspect aspect : aspects) {
/*  552 */       if ((aspect.getComponents() != null) && (((aspect.getComponents()[0] == aspect1) && (aspect.getComponents()[1] == aspect2)) || ((aspect.getComponents()[0] == aspect2) && (aspect.getComponents()[1] == aspect1))))
/*      */       {
/*      */ 
/*      */ 
/*  556 */         return aspect;
/*      */       }
/*      */     }
/*  559 */     return null;
/*      */   }
/*      */   
/*      */   public static AspectList reduceToPrimals(AspectList al) {
/*  563 */     return reduceToPrimals(al, false);
/*      */   }
/*      */   
/*      */   public static AspectList reduceToPrimals(AspectList al, boolean merge) {
/*  567 */     AspectList out = new AspectList();
/*  568 */     for (Aspect aspect : al.getAspects()) {
/*  569 */       if (aspect != null) {
/*  570 */         if (aspect.isPrimal()) {
/*  571 */           if (merge) {
/*  572 */             out.merge(aspect, al.getAmount(aspect));
/*      */           } else
/*  574 */             out.add(aspect, al.getAmount(aspect));
/*      */         } else {
/*  576 */           AspectList send = new AspectList();
/*  577 */           send.add(aspect.getComponents()[0], al.getAmount(aspect));
/*  578 */           send.add(aspect.getComponents()[1], al.getAmount(aspect));
/*  579 */           send = reduceToPrimals(send, merge);
/*  580 */           for (Aspect a : send.getAspects()) {
/*  581 */             if (merge) {
/*  582 */               out.merge(a, send.getAmount(a));
/*      */             } else
/*  584 */               out.add(a, send.getAmount(a));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  589 */     return out;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean completeResearchUnsaved(String username, String key)
/*      */   {
/*  601 */     ArrayList<String> completed = getResearchForPlayerSafe(username);
/*  602 */     if ((completed == null) || (!completed.contains(key))) {
/*  603 */       if (completed == null) completed = new ArrayList();
/*  604 */       completed.add(key);
/*  605 */       Thaumcraft.proxy.getCompletedResearch().put(username, completed);
/*  606 */       return true;
/*      */     }
/*  608 */     return false;
/*      */   }
/*      */   
/*      */   public void completeResearch(EntityPlayer player, String key) {
/*  612 */     if (completeResearchUnsaved(player.func_70005_c_(), key))
/*      */     {
/*  614 */       int warp = thaumcraft.api.ThaumcraftApi.getWarp(key);
/*  615 */       if ((warp > 0) && (!Config.wuss) && 
/*  616 */         (!player.field_70170_p.field_72995_K)) {
/*  617 */         if (warp > 1) {
/*  618 */           int w2 = warp / 2;
/*  619 */           if (warp - w2 > 0) Thaumcraft.addWarpToPlayer(player, warp - w2, false);
/*  620 */           if (w2 > 0) Thaumcraft.addStickyWarpToPlayer(player, w2);
/*      */         } else {
/*  622 */           Thaumcraft.addWarpToPlayer(player, warp, false);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  627 */       scheduleSave(player);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean completeAspectUnsaved(String username, Aspect aspect, short amount)
/*      */   {
/*  634 */     if (aspect == null) return false;
/*  635 */     Thaumcraft.proxy.getPlayerKnowledge().addDiscoveredAspect(username, aspect);
/*  636 */     Thaumcraft.proxy.getPlayerKnowledge().setAspectPool(username, aspect, amount);
/*  637 */     return true;
/*      */   }
/*      */   
/*      */   public void completeAspect(EntityPlayer player, Aspect aspect, short amount) {
/*  641 */     if (completeAspectUnsaved(player.func_70005_c_(), aspect, amount)) {
/*  642 */       scheduleSave(player);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean completeScannedObjectUnsaved(String username, String object)
/*      */   {
/*  649 */     ArrayList<String> completed = (ArrayList)Thaumcraft.proxy.getScannedObjects().get(username);
/*      */     
/*  651 */     if (completed == null) completed = new ArrayList();
/*  652 */     if (!completed.contains(object)) {
/*  653 */       completed.add(object);
/*  654 */       String t = object.replaceFirst("#", "@");
/*  655 */       if ((object.startsWith("#")) && (completed.contains(t)) && 
/*  656 */         (completed.remove(t))) {}
/*  657 */       Thaumcraft.proxy.getScannedObjects().put(username, completed);
/*      */     }
/*  659 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean completeScannedEntityUnsaved(String username, String key) {
/*  663 */     ArrayList<String> completed = (ArrayList)Thaumcraft.proxy.getScannedEntities().get(username);
/*      */     
/*  665 */     if (completed == null) completed = new ArrayList();
/*  666 */     if (!completed.contains(key)) {
/*  667 */       completed.add(key);
/*  668 */       String t = key.replaceFirst("#", "@");
/*  669 */       if ((key.startsWith("#")) && (completed.contains(t)) && 
/*  670 */         (completed.remove(t))) {}
/*  671 */       Thaumcraft.proxy.getScannedEntities().put(username, completed);
/*      */     }
/*  673 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean completeScannedPhenomenaUnsaved(String username, String key) {
/*  677 */     ArrayList<String> completed = (ArrayList)Thaumcraft.proxy.getScannedPhenomena().get(username);
/*      */     
/*  679 */     if (completed == null) completed = new ArrayList();
/*  680 */     if (!completed.contains(key)) {
/*  681 */       completed.add(key);
/*  682 */       String t = key.replaceFirst("#", "@");
/*  683 */       if ((key.startsWith("#")) && (completed.contains(t)) && 
/*  684 */         (completed.remove(t))) {}
/*  685 */       Thaumcraft.proxy.getScannedPhenomena().put(username, completed);
/*      */     }
/*  687 */     return true;
/*      */   }
/*      */   
/*      */   public void completeScannedObject(EntityPlayer player, String object) {
/*  691 */     if (completeScannedObjectUnsaved(player.func_70005_c_(), object))
/*  692 */       scheduleSave(player);
/*      */   }
/*      */   
/*      */   public void completeScannedEntity(EntityPlayer player, String key) {
/*  696 */     if (completeScannedEntityUnsaved(player.func_70005_c_(), key))
/*  697 */       scheduleSave(player);
/*      */   }
/*      */   
/*      */   public void completeScannedPhenomena(EntityPlayer player, String key) {
/*  701 */     if (completeScannedPhenomenaUnsaved(player.func_70005_c_(), key)) {
/*  702 */       scheduleSave(player);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final String ASPECT_TAG = "THAUMCRAFT.ASPECTS";
/*      */   
/*      */ 
/*      */ 
/*      */   private static final String SCANNED_OBJ_TAG = "THAUMCRAFT.SCAN.OBJECTS";
/*      */   
/*      */ 
/*      */ 
/*      */   private static final String SCANNED_ENT_TAG = "THAUMCRAFT.SCAN.ENTITIES";
/*      */   
/*      */ 
/*      */ 
/*      */   private static final String SCANNED_PHE_TAG = "THAUMCRAFT.SCAN.PHENOMENA";
/*      */   
/*      */ 
/*      */   public static void loadPlayerData(EntityPlayer player, File file1, File file2, boolean legacy)
/*      */   {
/*      */     try
/*      */     {
/*  728 */       NBTTagCompound data = null;
/*  729 */       if ((file1 != null) && (file1.exists())) {
/*      */         try
/*      */         {
/*  732 */           FileInputStream fileinputstream = new FileInputStream(file1);
/*  733 */           data = CompressedStreamTools.func_74796_a(fileinputstream);
/*  734 */           fileinputstream.close();
/*      */         } catch (Exception e) {
/*  736 */           e.printStackTrace();
/*      */         }
/*      */       }
/*  739 */       if ((file1 == null) || (!file1.exists()) || (data == null) || (data.func_82582_d()))
/*      */       {
/*  741 */         Thaumcraft.log.warn("Thaumcraft data not found for " + player.func_70005_c_() + ". Trying to load backup Thaumcraft data.");
/*  742 */         if ((file2 != null) && (file2.exists())) {
/*      */           try
/*      */           {
/*  745 */             FileInputStream fileinputstream = new FileInputStream(file2);
/*  746 */             data = CompressedStreamTools.func_74796_a(fileinputstream);
/*  747 */             fileinputstream.close();
/*      */           } catch (Exception e) {
/*  749 */             e.printStackTrace();
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  754 */       if (data != null) {
/*  755 */         loadResearchNBT(data, player);
/*  756 */         loadAspectNBT(data, player);
/*  757 */         loadScannedNBT(data, player);
/*      */         
/*  759 */         if (data.func_74764_b("Thaumcraft.shielding")) {
/*  760 */           Thaumcraft.instance.runicEventHandler.runicCharge.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(data.func_74762_e("Thaumcraft.shielding")));
/*      */           
/*      */ 
/*  763 */           Thaumcraft.instance.runicEventHandler.isDirty = true;
/*      */         }
/*      */         
/*  766 */         if (data.func_74764_b("Thaumcraft.eldritch")) {
/*  767 */           int warp = data.func_74762_e("Thaumcraft.eldritch");
/*  768 */           if ((legacy) && (!data.func_74764_b("Thaumcraft.eldritch.sticky"))) {
/*  769 */             warp /= 2;
/*  770 */             Thaumcraft.proxy.getPlayerKnowledge().setWarpSticky(player.func_70005_c_(), warp);
/*      */           }
/*  772 */           Thaumcraft.proxy.getPlayerKnowledge().setWarpPerm(player.func_70005_c_(), warp);
/*      */         }
/*      */         
/*  775 */         if (data.func_74764_b("Thaumcraft.eldritch.temp")) {
/*  776 */           Thaumcraft.proxy.getPlayerKnowledge().setWarpTemp(player.func_70005_c_(), data.func_74762_e("Thaumcraft.eldritch.temp"));
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  781 */         if (data.func_74764_b("Thaumcraft.eldritch.sticky")) {
/*  782 */           Thaumcraft.proxy.getPlayerKnowledge().setWarpSticky(player.func_70005_c_(), data.func_74762_e("Thaumcraft.eldritch.sticky"));
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  787 */         if (data.func_74764_b("Thaumcraft.eldritch.counter")) {
/*  788 */           Thaumcraft.proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), data.func_74762_e("Thaumcraft.eldritch.counter"));
/*      */         }
/*      */         else
/*      */         {
/*  792 */           Thaumcraft.proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), 0);
/*      */         }
/*      */       }
/*      */       else {
/*  796 */         Collection<Aspect> aspects = Aspect.aspects.values();
/*  797 */         for (Aspect aspect : aspects) {
/*  798 */           if (aspect.getComponents() == null) {
/*  799 */             Thaumcraft.proxy.getResearchManager();completeAspectUnsaved(player.func_70005_c_(), aspect, (short)(15 + player.field_70170_p.field_73012_v.nextInt(5)));
/*      */           }
/*      */         }
/*      */         
/*  803 */         scheduleSave(player);
/*  804 */         Thaumcraft.log.info("Assigning initial aspects to " + player.func_70005_c_());
/*      */       }
/*      */     }
/*      */     catch (Exception exception1)
/*      */     {
/*  809 */       exception1.printStackTrace();
/*  810 */       Thaumcraft.log.fatal("Error loading Thaumcraft data");
/*      */     }
/*      */   }
/*      */   
/*      */   public static void loadResearchNBT(NBTTagCompound entityData, EntityPlayer player)
/*      */   {
/*  816 */     NBTTagList tagList = entityData.func_150295_c("THAUMCRAFT.RESEARCH", 10);
/*  817 */     for (int j = 0; j < tagList.func_74745_c(); j++) {
/*  818 */       NBTTagCompound rs = tagList.func_150305_b(j);
/*      */       
/*  820 */       if (rs.func_74764_b("key")) {
/*  821 */         completeResearchUnsaved(player.func_70005_c_(), rs.func_74779_i("key"));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static void loadAspectNBT(NBTTagCompound entityData, EntityPlayer player)
/*      */   {
/*  829 */     if (entityData.func_74764_b("THAUMCRAFT.ASPECTS")) {
/*  830 */       NBTTagList tagList = entityData.func_150295_c("THAUMCRAFT.ASPECTS", 10);
/*  831 */       for (int j = 0; j < tagList.func_74745_c(); j++) {
/*  832 */         NBTTagCompound rs = tagList.func_150305_b(j);
/*  833 */         if (rs.func_74764_b("key")) {
/*  834 */           Aspect aspect = Aspect.getAspect(rs.func_74779_i("key"));
/*  835 */           short amount = rs.func_74765_d("amount");
/*  836 */           if (aspect != null) {
/*  837 */             completeAspectUnsaved(player.func_70005_c_(), aspect, amount);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static void loadScannedNBT(NBTTagCompound entityData, EntityPlayer player)
/*      */   {
/*  847 */     NBTTagList tagList = entityData.func_150295_c("THAUMCRAFT.SCAN.OBJECTS", 10);
/*  848 */     for (int j = 0; j < tagList.func_74745_c(); j++) {
/*  849 */       NBTTagCompound rs = tagList.func_150305_b(j);
/*  850 */       if (rs.func_74764_b("key")) {
/*  851 */         completeScannedObjectUnsaved(player.func_70005_c_(), rs.func_74779_i("key"));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  856 */     tagList = entityData.func_150295_c("THAUMCRAFT.SCAN.ENTITIES", 10);
/*  857 */     for (int j = 0; j < tagList.func_74745_c(); j++) {
/*  858 */       NBTTagCompound rs = tagList.func_150305_b(j);
/*  859 */       if (rs.func_74764_b("key")) {
/*  860 */         completeScannedEntityUnsaved(player.func_70005_c_(), rs.func_74779_i("key"));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  865 */     tagList = entityData.func_150295_c("THAUMCRAFT.SCAN.PHENOMENA", 10);
/*  866 */     for (int j = 0; j < tagList.func_74745_c(); j++) {
/*  867 */       NBTTagCompound rs = tagList.func_150305_b(j);
/*  868 */       if (rs.func_74764_b("key")) {
/*  869 */         completeScannedPhenomenaUnsaved(player.func_70005_c_(), rs.func_74779_i("key"));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void scheduleSave(EntityPlayer player)
/*      */   {
/*  879 */     if (player.field_70170_p.field_72995_K) {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean savePlayerData(EntityPlayer player, File file1, File file2)
/*      */   {
/*  890 */     boolean success = true;
/*      */     try
/*      */     {
/*  893 */       NBTTagCompound data = new NBTTagCompound();
/*      */       
/*  895 */       saveResearchNBT(data, player);
/*  896 */       saveAspectNBT(data, player);
/*  897 */       saveScannedNBT(data, player);
/*      */       
/*  899 */       if (Thaumcraft.instance.runicEventHandler.runicCharge.containsKey(Integer.valueOf(player.func_145782_y()))) {
/*  900 */         data.func_74782_a("Thaumcraft.shielding", new NBTTagInt(((Integer)Thaumcraft.instance.runicEventHandler.runicCharge.get(Integer.valueOf(player.func_145782_y()))).intValue()));
/*      */       }
/*      */       
/*      */ 
/*  904 */       data.func_74782_a("Thaumcraft.eldritch", new NBTTagInt(Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.func_70005_c_())));
/*      */       
/*      */ 
/*  907 */       data.func_74782_a("Thaumcraft.eldritch.temp", new NBTTagInt(Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_())));
/*      */       
/*      */ 
/*  910 */       data.func_74782_a("Thaumcraft.eldritch.sticky", new NBTTagInt(Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_())));
/*      */       
/*      */ 
/*  913 */       data.func_74782_a("Thaumcraft.eldritch.counter", new NBTTagInt(Thaumcraft.proxy.getPlayerKnowledge().getWarpCounter(player.func_70005_c_())));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  928 */       if ((file1 != null) && (file1.exists())) {
/*      */         try {
/*  930 */           Files.copy(file1, file2);
/*      */         }
/*      */         catch (Exception e)
/*      */         {
/*  934 */           Thaumcraft.log.error("Could not backup old research file for player " + player.func_70005_c_());
/*      */         }
/*      */       }
/*      */       
/*      */       try
/*      */       {
/*  940 */         if (file1 != null)
/*      */         {
/*  942 */           FileOutputStream fileoutputstream = new FileOutputStream(file1);
/*  943 */           CompressedStreamTools.func_74799_a(data, fileoutputstream);
/*  944 */           fileoutputstream.close();
/*      */         }
/*      */       } catch (Exception e) {
/*  947 */         Thaumcraft.log.error("Could not save research file for player " + player.func_70005_c_());
/*  948 */         if (file1.exists()) {
/*      */           try {
/*  950 */             file1.delete();
/*      */           }
/*      */           catch (Exception e2) {}
/*      */         }
/*  954 */         success = false;
/*      */       }
/*      */     }
/*      */     catch (Exception exception1)
/*      */     {
/*  959 */       exception1.printStackTrace();
/*  960 */       Thaumcraft.log.fatal("Error saving Thaumcraft data");
/*  961 */       success = false;
/*      */     }
/*  963 */     return success;
/*      */   }
/*      */   
/*      */ 
/*      */   public static void saveResearchNBT(NBTTagCompound entityData, EntityPlayer player)
/*      */   {
/*  969 */     NBTTagList tagList = new NBTTagList();
/*      */     
/*  971 */     List res = getResearchForPlayer(player.func_70005_c_());
/*  972 */     if ((res != null) && (res.size() > 0)) {
/*  973 */       for (Object key : res) {
/*  974 */         if (((String)key != null) && ((((String)key).startsWith("@")) || (ResearchCategories.getResearch((String)key) != null)))
/*      */         {
/*      */ 
/*      */ 
/*  978 */           if (((String)key).startsWith("@")) {
/*  979 */             String k = ((String)key).substring(1);
/*  980 */             if (isResearchComplete(player.func_70005_c_(), k)) {}
/*      */ 
/*      */ 
/*      */           }
/*  984 */           else if ((ResearchCategories.getResearch((String)key) == null) || (!ResearchCategories.getResearch((String)key).isAutoUnlock()))
/*      */           {
/*      */ 
/*  987 */             NBTTagCompound f = new NBTTagCompound();
/*  988 */             f.func_74778_a("key", (String)key);
/*  989 */             tagList.func_74742_a(f);
/*      */           } }
/*      */       }
/*      */     }
/*  993 */     entityData.func_74782_a("THAUMCRAFT.RESEARCH", tagList);
/*      */   }
/*      */   
/*      */   public static void saveAspectNBT(NBTTagCompound entityData, EntityPlayer player) {
/*  997 */     NBTTagList tagList = new NBTTagList();
/*  998 */     AspectList res = (AspectList)Thaumcraft.proxy.getKnownAspects().get(player.func_70005_c_());
/*  999 */     if ((res != null) && (res.size() > 0)) {
/* 1000 */       for (Aspect aspect : res.getAspects())
/* 1001 */         if (aspect != null) {
/* 1002 */           NBTTagCompound f = new NBTTagCompound();
/* 1003 */           f.func_74778_a("key", aspect.getTag());
/* 1004 */           f.func_74777_a("amount", (short)res.getAmount(aspect));
/* 1005 */           tagList.func_74742_a(f);
/*      */         }
/*      */     }
/* 1008 */     entityData.func_74782_a("THAUMCRAFT.ASPECTS", tagList);
/*      */   }
/*      */   
/*      */   public static void saveScannedNBT(NBTTagCompound entityData, EntityPlayer player)
/*      */   {
/* 1013 */     NBTTagList tagList = new NBTTagList();
/* 1014 */     List<String> obj = (List)Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_());
/* 1015 */     if ((obj != null) && (obj.size() > 0))
/* 1016 */       for (String object : obj)
/* 1017 */         if (object != null) {
/* 1018 */           NBTTagCompound f = new NBTTagCompound();
/* 1019 */           f.func_74778_a("key", object);
/* 1020 */           tagList.func_74742_a(f);
/*      */         }
/* 1022 */     entityData.func_74782_a("THAUMCRAFT.SCAN.OBJECTS", tagList);
/*      */     
/*      */ 
/* 1025 */     tagList = new NBTTagList();
/* 1026 */     List<String> ent = (List)Thaumcraft.proxy.getScannedEntities().get(player.func_70005_c_());
/* 1027 */     if ((ent != null) && (ent.size() > 0))
/* 1028 */       for (String key : ent)
/* 1029 */         if (key != null) {
/* 1030 */           NBTTagCompound f = new NBTTagCompound();
/* 1031 */           f.func_74778_a("key", key);
/* 1032 */           tagList.func_74742_a(f);
/*      */         }
/* 1034 */     entityData.func_74782_a("THAUMCRAFT.SCAN.ENTITIES", tagList);
/*      */     
/*      */ 
/* 1037 */     tagList = new NBTTagList();
/* 1038 */     List<String> phe = (List)Thaumcraft.proxy.getScannedPhenomena().get(player.func_70005_c_());
/* 1039 */     if ((phe != null) && (phe.size() > 0))
/* 1040 */       for (String key : phe)
/* 1041 */         if (key != null) {
/* 1042 */           NBTTagCompound f = new NBTTagCompound();
/* 1043 */           f.func_74778_a("key", key);
/* 1044 */           tagList.func_74742_a(f);
/*      */         }
/* 1046 */     entityData.func_74782_a("THAUMCRAFT.SCAN.PHENOMENA", tagList);
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/research/ResearchManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */