/*     */ package thaumcraft.common.lib.research;
/*     */ 
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.ThaumcraftApi.EntityTags;
/*     */ import thaumcraft.api.ThaumcraftApi.EntityTagsNBT;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.api.research.ScanResult;
/*     */ import thaumcraft.client.lib.PlayerNotifications;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class ScanManager implements thaumcraft.api.research.IScanEventHandler
/*     */ {
/*     */   public ScanResult scanPhenomena(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  50 */     return null;
/*     */   }
/*     */   
/*     */   private static int generateEntityHash(Entity entity) {
/*  54 */     String hash = EntityList.func_75621_b(entity);
/*  55 */     if (hash == null)
/*     */     {
/*  57 */       hash = "generic";
/*     */     }
/*  59 */     if ((entity instanceof EntityPlayer)) {
/*  60 */       hash = "player_" + ((EntityPlayer)entity).func_70005_c_();
/*     */     }
/*     */     
/*     */ 
/*  64 */     for (ThaumcraftApi.EntityTags et : ThaumcraftApi.scanEntities) {
/*  65 */       if (et.entityName.equals(hash)) {
/*  66 */         if ((et.nbts != null) && (et.nbts.length != 0))
/*     */         {
/*     */ 
/*  69 */           NBTTagCompound tc = new NBTTagCompound();
/*  70 */           entity.func_70109_d(tc);
/*     */           
/*  72 */           ThaumcraftApi.EntityTagsNBT[] arr$ = et.nbts;int len$ = arr$.length; for (int i$ = 0;; i$++) { if (i$ >= len$) break label228; ThaumcraftApi.EntityTagsNBT nbt = arr$[i$];
/*  73 */             if (!tc.func_74764_b(nbt.name)) break;
/*  74 */             Object val = Utils.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name);
/*  75 */             Class c = val.getClass();
/*     */             try {
/*  77 */               if (!c.cast(val).equals(c.cast(nbt.value))) break; } catch (Exception e) {}
/*  78 */             break;
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*  83 */           for (ThaumcraftApi.EntityTagsNBT nbt : et.nbts) {
/*  84 */             Object val = Utils.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name);
/*  85 */             Class c = val.getClass();
/*     */             try {
/*  87 */               hash = hash + nbt.name + c.cast(nbt.value);
/*     */             } catch (Exception e) {}
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     label228:
/*  94 */     if ((entity instanceof EntityLivingBase)) {
/*  95 */       EntityLivingBase le = (EntityLivingBase)entity;
/*  96 */       if (le.func_70631_g_()) { hash = hash + "CHILD";
/*     */       }
/*     */     }
/*  99 */     if (((entity instanceof EntityZombie)) && 
/* 100 */       (((EntityZombie)entity).func_82231_m())) { hash = hash + "VILLAGER";
/*     */     }
/*     */     
/* 103 */     if ((entity instanceof EntityCreeper)) {
/* 104 */       if (((EntityCreeper)entity).func_70832_p() == 1) hash = hash + "FLASHING";
/* 105 */       if (((EntityCreeper)entity).func_70830_n()) { hash = hash + "POWERED";
/*     */       }
/*     */     }
/* 108 */     if ((entity instanceof EntityGolemBase)) {
/* 109 */       hash = hash + "" + ((EntityGolemBase)entity).getGolemType().name();
/*     */     }
/*     */     
/* 112 */     return hash.hashCode();
/*     */   }
/*     */   
/*     */   public static int generateItemHash(Item item, int meta)
/*     */   {
/* 117 */     ItemStack t = new ItemStack(item, 1, meta);
/*     */     try {
/* 119 */       if ((t.func_77984_f()) || (!t.func_77981_g())) { meta = -1;
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */     
/* 124 */     if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(new Object[] { item, Integer.valueOf(meta) }))) {
/* 125 */       meta = ((int[])ThaumcraftApi.groupedObjectTags.get(Arrays.asList(new Object[] { item, Integer.valueOf(meta) })))[0];
/*     */     }
/*     */     String hash;
/*     */     try {
/* 129 */       GameRegistry.UniqueIdentifier ui = GameRegistry.findUniqueIdentifierFor(item);
/* 130 */       String hash; if (ui != null) {
/* 131 */         hash = ui.toString() + ":" + meta;
/*     */       } else
/* 133 */         hash = t.func_77977_a() + ":" + meta;
/*     */     } catch (Exception e) {
/* 135 */       hash = "oops:" + meta;
/*     */     }
/*     */     
/*     */ 
/* 139 */     if (!ThaumcraftApi.objectTags.containsKey(Arrays.asList(new Object[] { item, Integer.valueOf(meta) })))
/*     */     {
/* 141 */       Collection<List> col = ThaumcraftApi.objectTags.keySet();
/* 142 */       for (List l : col) {
/* 143 */         String name = ((Item)l.get(0)).func_77658_a();
/* 144 */         if (((Item.field_150901_e.func_82594_a(name) == item) || (Block.field_149771_c.func_82594_a(name) == Block.func_149634_a(item))) && ((l.get(1) instanceof int[])))
/*     */         {
/*     */ 
/* 147 */           int[] range = (int[])l.get(1);
/* 148 */           Arrays.sort(range);
/* 149 */           if (Arrays.binarySearch(range, meta) >= 0) {
/* 150 */             GameRegistry.UniqueIdentifier ui = GameRegistry.findUniqueIdentifierFor(item);
/* 151 */             if (ui != null) {
/* 152 */               hash = ui.toString();
/*     */             } else
/* 154 */               hash = "" + t.func_77977_a();
/* 155 */             for (int r : range) {
/* 156 */               hash = hash + ":" + r;
/*     */             }
/*     */             
/* 159 */             return hash.hashCode();
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 164 */       if ((!ThaumcraftApi.objectTags.containsKey(Arrays.asList(new Object[] { item, Integer.valueOf(-1) }))) && 
/* 165 */         (meta == -1)) {
/* 166 */         int index = 0;
/* 167 */         boolean found = false;
/*     */         do {
/* 169 */           found = ThaumcraftApi.objectTags.containsKey(Arrays.asList(new Object[] { item, Integer.valueOf(index) }));
/* 170 */           index++;
/* 171 */         } while ((index < 16) && (!found));
/* 172 */         if (found) {
/* 173 */           GameRegistry.UniqueIdentifier ui = GameRegistry.findUniqueIdentifierFor(item);
/* 174 */           if (ui != null) {
/* 175 */             hash = ui.toString() + ":" + index;
/*     */           } else {
/* 177 */             hash = t.func_77977_a() + ":" + index;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 182 */     return hash.hashCode();
/*     */   }
/*     */   
/*     */   public static AspectList generateEntityAspects(Entity entity)
/*     */   {
/* 187 */     AspectList tags = null;
/* 188 */     String s = null;
/*     */     try {
/* 190 */       s = EntityList.func_75621_b(entity);
/*     */     } catch (Throwable e) {
/* 192 */       try { s = entity.func_70005_c_();
/*     */       } catch (Throwable e2) {} }
/* 194 */     if (s == null)
/*     */     {
/* 196 */       s = "generic";
/*     */     }
/*     */     
/* 199 */     if ((entity instanceof EntityPlayer)) {
/* 200 */       s = "player_" + ((EntityPlayer)entity).func_70005_c_();
/* 201 */       tags = new AspectList();
/* 202 */       tags.add(Aspect.MAN, 4);
/*     */       
/* 204 */       if (((EntityPlayer)entity).func_70005_c_().equalsIgnoreCase("azanor")) {
/* 205 */         tags.add(Aspect.ELDRITCH, 20);
/*     */       }
/* 207 */       else if (((EntityPlayer)entity).func_70005_c_().equalsIgnoreCase("direwolf20")) {
/* 208 */         tags.add(Aspect.BEAST, 20);
/*     */       }
/* 210 */       else if (((EntityPlayer)entity).func_70005_c_().equalsIgnoreCase("pahimar")) {
/* 211 */         tags.add(Aspect.EXCHANGE, 20);
/*     */       }
/*     */       else {
/* 214 */         Random rand = new Random(s.hashCode());
/* 215 */         Aspect[] posa = (Aspect[])Aspect.aspects.values().toArray(new Aspect[0]);
/* 216 */         tags.add(posa[rand.nextInt(posa.length)], 4);
/* 217 */         tags.add(posa[rand.nextInt(posa.length)], 4);
/* 218 */         tags.add(posa[rand.nextInt(posa.length)], 4);
/*     */       }
/*     */     }
/*     */     else {
/* 222 */       for (ThaumcraftApi.EntityTags et : ThaumcraftApi.scanEntities) {
/* 223 */         if (et.entityName.equals(s)) {
/* 224 */           if ((et.nbts == null) || (et.nbts.length == 0)) {
/* 225 */             tags = et.aspects;
/*     */           } else {
/* 227 */             NBTTagCompound tc = new NBTTagCompound();
/* 228 */             entity.func_70109_d(tc);
/* 229 */             ThaumcraftApi.EntityTagsNBT[] arr$ = et.nbts;int len$ = arr$.length; for (int i$ = 0;; i$++) { if (i$ >= len$) break label416; ThaumcraftApi.EntityTagsNBT nbt = arr$[i$];
/* 230 */               if ((!tc.func_74764_b(nbt.name)) || 
/* 231 */                 (!Utils.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name).equals(nbt.value))) {
/*     */                 break;
/*     */               }
/*     */             }
/*     */             
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 243 */             tags = et.aspects;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     label416:
/* 249 */     return tags;
/*     */   }
/*     */   
/*     */   private static AspectList generateNodeAspects(World world, String node)
/*     */   {
/* 254 */     AspectList tags = new AspectList();
/*     */     
/* 256 */     ArrayList<Integer> loc = (ArrayList)thaumcraft.common.tiles.TileNode.locations.get(node);
/*     */     
/* 258 */     if ((loc != null) && (loc.size() > 0)) {
/* 259 */       int dim = ((Integer)loc.get(0)).intValue();
/* 260 */       int x = ((Integer)loc.get(1)).intValue();
/* 261 */       int y = ((Integer)loc.get(2)).intValue();
/* 262 */       int z = ((Integer)loc.get(3)).intValue();
/*     */       
/* 264 */       if (dim == world.field_73011_w.field_76574_g) {
/* 265 */         net.minecraft.tileentity.TileEntity tnb = world.func_147438_o(x, y, z);
/* 266 */         if ((tnb != null) && ((tnb instanceof INode))) {
/* 267 */           AspectList ta = ((INode)tnb).getAspects();
/* 268 */           for (Aspect a : ta.getAspectsSorted()) {
/* 269 */             tags.merge(a, Math.max(4, ta.getAmount(a) / 10));
/*     */           }
/* 271 */           switch (((INode)tnb).getNodeType()) {
/* 272 */           case UNSTABLE:  tags.merge(Aspect.ENTROPY, 4); break;
/* 273 */           case HUNGRY:  tags.merge(Aspect.HUNGER, 4); break;
/* 274 */           case TAINTED:  tags.merge(Aspect.TAINT, 4); break;
/* 275 */           case PURE:  tags.merge(Aspect.HEAL, 2);tags.add(Aspect.ORDER, 2); break;
/* 276 */           case DARK:  tags.merge(Aspect.DEATH, 2);tags.add(Aspect.DARKNESS, 2);
/*     */           }
/*     */           
/*     */         }
/*     */       }
/*     */     }
/* 282 */     return tags.size() > 0 ? tags : null;
/*     */   }
/*     */   
/*     */   public static boolean isValidScanTarget(EntityPlayer player, ScanResult scan, String prefix) {
/* 286 */     if (scan == null) { return false;
/*     */     }
/* 288 */     if ((prefix.equals("@")) && (!isValidScanTarget(player, scan, "#"))) { return false;
/*     */     }
/* 290 */     if (scan.type == 1) {
/* 291 */       if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(new Object[] { Item.func_150899_d(scan.id), Integer.valueOf(scan.meta) }))) {
/* 292 */         scan.meta = ((int[])ThaumcraftApi.groupedObjectTags.get(Arrays.asList(new Object[] { Item.func_150899_d(scan.id), Integer.valueOf(scan.meta) })))[0];
/*     */       }
/* 294 */       List<String> list = (List)Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_());
/* 295 */       if ((list != null) && (list.contains(prefix + generateItemHash(Item.func_150899_d(scan.id), scan.meta)))) {
/* 296 */         return false;
/*     */       }
/*     */     }
/* 299 */     else if (scan.type == 2) {
/* 300 */       if ((scan.entity instanceof EntityItem)) {
/* 301 */         EntityItem item = (EntityItem)scan.entity;
/* 302 */         ItemStack t = item.func_92059_d().func_77946_l();
/* 303 */         t.field_77994_a = 1;
/* 304 */         if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(new Object[] { t.func_77973_b(), Integer.valueOf(t.func_77960_j()) }))) {
/* 305 */           t.func_77964_b(((int[])ThaumcraftApi.groupedObjectTags.get(Arrays.asList(new Object[] { t.func_77973_b(), Integer.valueOf(t.func_77960_j()) })))[0]);
/*     */         }
/* 307 */         List<String> list = (List)Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_());
/* 308 */         if ((list != null) && (list.contains(prefix + generateItemHash(t.func_77973_b(), t.func_77960_j())))) {
/* 309 */           return false;
/*     */         }
/*     */       }
/*     */       else {
/* 313 */         List<String> list = (List)Thaumcraft.proxy.getScannedEntities().get(player.func_70005_c_());
/* 314 */         if ((list != null) && (list.contains(prefix + generateEntityHash(scan.entity)))) {
/* 315 */           return false;
/*     */         }
/*     */       }
/* 318 */     } else if (scan.type == 3) {
/* 319 */       List<String> list = (List)Thaumcraft.proxy.getScannedPhenomena().get(player.func_70005_c_());
/* 320 */       if ((list != null) && (list.contains(prefix + scan.phenomena))) {
/* 321 */         return false;
/*     */       }
/*     */     }
/* 324 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean hasBeenScanned(EntityPlayer player, ScanResult scan) {
/* 328 */     if (scan.type == 1) {
/* 329 */       if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(new Object[] { Item.func_150899_d(scan.id), Integer.valueOf(scan.meta) }))) {
/* 330 */         scan.meta = ((int[])ThaumcraftApi.groupedObjectTags.get(Arrays.asList(new Object[] { Item.func_150899_d(scan.id), Integer.valueOf(scan.meta) })))[0];
/*     */       }
/* 332 */       List<String> list = (List)Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_());
/* 333 */       if ((list != null) && ((list.contains("@" + generateItemHash(Item.func_150899_d(scan.id), scan.meta))) || (list.contains("#" + generateItemHash(Item.func_150899_d(scan.id), scan.meta)))))
/*     */       {
/*     */ 
/* 336 */         return true;
/*     */       }
/*     */     }
/* 339 */     else if (scan.type == 2) {
/* 340 */       if ((scan.entity instanceof EntityItem)) {
/* 341 */         EntityItem item = (EntityItem)scan.entity;
/* 342 */         ItemStack t = item.func_92059_d().func_77946_l();
/* 343 */         t.field_77994_a = 1;
/* 344 */         if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(new Object[] { t.func_77973_b(), Integer.valueOf(t.func_77960_j()) }))) {
/* 345 */           t.func_77964_b(((int[])ThaumcraftApi.groupedObjectTags.get(Arrays.asList(new Object[] { t.func_77973_b(), Integer.valueOf(t.func_77960_j()) })))[0]);
/*     */         }
/* 347 */         List<String> list = (List)Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_());
/* 348 */         if ((list != null) && ((list.contains("@" + generateItemHash(t.func_77973_b(), t.func_77960_j()))) || (list.contains("#" + generateItemHash(t.func_77973_b(), t.func_77960_j())))))
/*     */         {
/*     */ 
/* 351 */           return true;
/*     */         }
/*     */       }
/*     */       else {
/* 355 */         List<String> list = (List)Thaumcraft.proxy.getScannedEntities().get(player.func_70005_c_());
/* 356 */         if ((list != null) && ((list.contains("@" + generateEntityHash(scan.entity))) || (list.contains("#" + generateEntityHash(scan.entity)))))
/*     */         {
/*     */ 
/* 359 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 363 */     else if (scan.type == 3) {
/* 364 */       List<String> list = (List)Thaumcraft.proxy.getScannedPhenomena().get(player.func_70005_c_());
/* 365 */       if ((list != null) && ((list.contains("@" + scan.phenomena)) || (list.contains("#" + scan.phenomena))))
/*     */       {
/*     */ 
/* 368 */         return true; }
/*     */     }
/* 370 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean completeScan(EntityPlayer player, ScanResult scan, String prefix) {
/* 374 */     AspectList aspects = null;
/* 375 */     PlayerKnowledge rp = Thaumcraft.proxy.getPlayerKnowledge();
/* 376 */     boolean ret = false;
/*     */     
/* 378 */     boolean scannedByThaumometer = (prefix.equals("#")) && (!isValidScanTarget(player, scan, "@"));
/* 379 */     Object clue = null;
/* 380 */     if (scan.type == 1) {
/* 381 */       if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(new Object[] { Item.func_150899_d(scan.id), Integer.valueOf(scan.meta) }))) {
/* 382 */         scan.meta = ((int[])ThaumcraftApi.groupedObjectTags.get(Arrays.asList(new Object[] { Item.func_150899_d(scan.id), Integer.valueOf(scan.meta) })))[0];
/*     */       }
/* 384 */       aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(Item.func_150899_d(scan.id), 1, scan.meta));
/* 385 */       aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(Item.func_150899_d(scan.id), 1, scan.meta), aspects);
/* 386 */       if (((aspects == null) || (aspects.size() == 0)) && 
/* 387 */         (scan.id > 0)) {
/* 388 */         aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(Item.func_150899_d(scan.id), 1, scan.meta));
/* 389 */         aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(Item.func_150899_d(scan.id), 1, scan.meta), aspects);
/*     */       }
/*     */       
/* 392 */       if (validScan(aspects, player)) {
/* 393 */         clue = new ItemStack(Item.func_150899_d(scan.id), 1, scan.meta);
/* 394 */         Thaumcraft.proxy.getResearchManager().completeScannedObject(player, prefix + generateItemHash(Item.func_150899_d(scan.id), scan.meta));
/* 395 */         ret = true;
/*     */       }
/*     */     }
/* 398 */     else if (scan.type == 2) {
/* 399 */       if ((scan.entity instanceof EntityItem)) {
/* 400 */         EntityItem item = (EntityItem)scan.entity;
/* 401 */         ItemStack t = item.func_92059_d().func_77946_l();
/* 402 */         t.field_77994_a = 1;
/* 403 */         if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(new Object[] { t.func_77973_b(), Integer.valueOf(t.func_77960_j()) }))) {
/* 404 */           t.func_77964_b(((int[])ThaumcraftApi.groupedObjectTags.get(Arrays.asList(new Object[] { t.func_77973_b(), Integer.valueOf(t.func_77960_j()) })))[0]);
/*     */         }
/* 406 */         aspects = ThaumcraftCraftingManager.getObjectTags(t);
/* 407 */         aspects = ThaumcraftCraftingManager.getBonusTags(t, aspects);
/* 408 */         if (validScan(aspects, player)) {
/* 409 */           clue = item.func_92059_d();
/* 410 */           Thaumcraft.proxy.getResearchManager().completeScannedObject(player, prefix + generateItemHash(item.func_92059_d().func_77973_b(), item.func_92059_d().func_77960_j()));
/* 411 */           ret = true;
/*     */         }
/*     */       }
/*     */       else {
/* 415 */         aspects = generateEntityAspects(scan.entity);
/* 416 */         if (validScan(aspects, player)) {
/* 417 */           clue = EntityList.func_75621_b(scan.entity);
/* 418 */           Thaumcraft.proxy.getResearchManager().completeScannedEntity(player, prefix + generateEntityHash(scan.entity));
/* 419 */           ret = true;
/*     */         }
/*     */       }
/*     */     }
/* 423 */     else if ((scan.type == 3) && 
/* 424 */       (scan.phenomena.startsWith("NODE"))) {
/* 425 */       aspects = generateNodeAspects(player.field_70170_p, scan.phenomena.replace("NODE", ""));
/* 426 */       if (validScan(aspects, player)) {
/* 427 */         Thaumcraft.proxy.getResearchManager().completeScannedPhenomena(player, prefix + scan.phenomena);
/* 428 */         ret = true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 434 */     if ((!player.field_70170_p.field_72995_K) && (ret) && (aspects != null)) {
/* 435 */       AspectList aspectsFinal = new AspectList();
/* 436 */       for (Aspect aspect : aspects.getAspects()) {
/* 437 */         if (rp.hasDiscoveredParentAspects(player.func_70005_c_(), aspect)) {
/* 438 */           int amt = aspects.getAmount(aspect);
/* 439 */           if (scannedByThaumometer) amt = 0;
/* 440 */           if (prefix.equals("#")) amt++;
/* 441 */           int a = checkAndSyncAspectKnowledge(player, aspect, amt);
/* 442 */           if (a > 0) aspectsFinal.merge(aspect, a);
/*     */         }
/*     */       }
/* 445 */       if (clue != null) {
/* 446 */         ResearchManager.createClue(player.field_70170_p, player, clue, aspectsFinal);
/*     */       }
/*     */     }
/* 449 */     return ret;
/*     */   }
/*     */   
/*     */   public static int checkAndSyncAspectKnowledge(EntityPlayer player, Aspect aspect, int amount) {
/* 453 */     PlayerKnowledge rp = Thaumcraft.proxy.getPlayerKnowledge();
/* 454 */     int save = 0;
/* 455 */     if (!rp.hasDiscoveredAspect(player.func_70005_c_(), aspect)) {
/* 456 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketAspectDiscovery(aspect.getTag()), (EntityPlayerMP)player);
/* 457 */       amount += 2;
/* 458 */       save = amount;
/*     */     }
/* 460 */     if (rp.getAspectPoolFor(player.func_70005_c_(), aspect) >= Config.aspectTotalCap) {
/* 461 */       amount = (int)Math.sqrt(amount);
/*     */     }
/* 463 */     if ((amount > 1) && (rp.getAspectPoolFor(player.func_70005_c_(), aspect) >= Config.aspectTotalCap * 1.25F)) {
/* 464 */       amount = 1;
/*     */     }
/* 466 */     if (rp.addAspectPool(player.func_70005_c_(), aspect, (short)amount)) {
/* 467 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketAspectPool(aspect.getTag(), Short.valueOf((short)amount), Short.valueOf(rp.getAspectPoolFor(player.func_70005_c_(), aspect))), (EntityPlayerMP)player);
/*     */       
/*     */ 
/* 470 */       save = amount;
/*     */     }
/* 472 */     if (save > 0)
/* 473 */       Thaumcraft.proxy.getResearchManager().completeAspect(player, aspect, rp.getAspectPoolFor(player.func_70005_c_(), aspect));
/* 474 */     return save;
/*     */   }
/*     */   
/*     */   public static boolean validScan(AspectList aspects, EntityPlayer player)
/*     */   {
/* 479 */     PlayerKnowledge rp = Thaumcraft.proxy.getPlayerKnowledge();
/* 480 */     if ((aspects != null) && (aspects.size() > 0)) {
/* 481 */       for (Aspect aspect : aspects.getAspects()) {
/* 482 */         if ((aspect != null) && (!aspect.isPrimal()) && (!rp.hasDiscoveredParentAspects(player.func_70005_c_(), aspect)))
/*     */         {
/* 484 */           if (player.field_70170_p.field_72995_K) {
/* 485 */             for (Aspect parent : aspect.getComponents()) {
/* 486 */               if (!rp.hasDiscoveredAspect(player.func_70005_c_(), parent)) {
/* 487 */                 PlayerNotifications.addNotification(new ChatComponentTranslation(StatCollector.func_74838_a("tc.discoveryerror"), new Object[] { StatCollector.func_74838_a("tc.aspect.help." + parent.getTag()) }).func_150260_c());
/*     */                 
/*     */ 
/*     */ 
/*     */ 
/* 492 */                 break;
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 498 */           return false;
/*     */         }
/*     */       }
/*     */     } else {
/* 502 */       if (player.field_70170_p.field_72995_K)
/* 503 */         PlayerNotifications.addNotification(StatCollector.func_74838_a("tc.unknownobject"));
/* 504 */       return false;
/*     */     }
/* 506 */     return true;
/*     */   }
/*     */   
/*     */   public static AspectList getScanAspects(ScanResult scan, World world)
/*     */   {
/* 511 */     AspectList aspects = new AspectList();
/* 512 */     boolean ret = false;
/* 513 */     if (scan.type == 1) {
/* 514 */       if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(new Object[] { Item.func_150899_d(scan.id), Integer.valueOf(scan.meta) }))) {
/* 515 */         scan.meta = ((int[])ThaumcraftApi.groupedObjectTags.get(Arrays.asList(new Object[] { Item.func_150899_d(scan.id), Integer.valueOf(scan.meta) })))[0];
/*     */       }
/* 517 */       aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(Item.func_150899_d(scan.id), 1, scan.meta));
/* 518 */       aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(Item.func_150899_d(scan.id), 1, scan.meta), aspects);
/* 519 */       if (((aspects == null) || (aspects.size() == 0)) && 
/* 520 */         (scan.id > 0))
/*     */       {
/*     */ 
/*     */ 
/* 524 */         aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(Item.func_150899_d(scan.id), 1, scan.meta));
/* 525 */         aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(Item.func_150899_d(scan.id), 1, scan.meta), aspects);
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 531 */     else if (scan.type == 2) {
/* 532 */       if (((scan.entity instanceof EntityItem)) && (((EntityItem)scan.entity).func_92059_d() != null)) {
/* 533 */         EntityItem item = (EntityItem)scan.entity;
/* 534 */         ItemStack t = item.func_92059_d().func_77946_l();
/* 535 */         t.field_77994_a = 1;
/* 536 */         if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(new Object[] { t.func_77973_b(), Integer.valueOf(t.func_77960_j()) }))) {
/* 537 */           t.func_77964_b(((int[])ThaumcraftApi.groupedObjectTags.get(Arrays.asList(new Object[] { t.func_77973_b(), Integer.valueOf(t.func_77960_j()) })))[0]);
/*     */         }
/* 539 */         aspects = ThaumcraftCraftingManager.getObjectTags(t);
/* 540 */         aspects = ThaumcraftCraftingManager.getBonusTags(t, aspects);
/*     */       }
/*     */       else {
/* 543 */         aspects = generateEntityAspects(scan.entity);
/*     */       }
/*     */     }
/* 546 */     else if ((scan.type == 3) && 
/* 547 */       (scan.phenomena.startsWith("NODE"))) {
/* 548 */       aspects = generateNodeAspects(world, scan.phenomena.replace("NODE", ""));
/*     */     }
/*     */     
/* 551 */     return aspects;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/research/ScanManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */