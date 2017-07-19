/*     */ package thaumcraft.common.entities.golems;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.BlockFluidBase;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidHandler;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.InventoryMob;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileEssentiaReservoir;
/*     */ import thaumcraft.common.tiles.TileJarFillable;
/*     */ import thaumcraft.common.tiles.TileJarFillableVoid;
/*     */ 
/*     */ public class GolemHelper
/*     */ {
/*     */   public static final double ADJACENT_RANGE = 4.0D;
/*     */   
/*     */   public static ArrayList<IInventory> getMarkedContainers(World world, EntityGolemBase golem)
/*     */   {
/*  34 */     ArrayList<IInventory> results = new ArrayList();
/*  35 */     for (Marker marker : golem.getMarkers()) {
/*  36 */       TileEntity te = world.func_147438_o(marker.x, marker.y, marker.z);
/*  37 */       if ((marker.dim == world.field_73011_w.field_76574_g) && (te != null) && ((te instanceof IInventory)))
/*     */       {
/*  39 */         results.add((IInventory)te);
/*  40 */         if (InventoryUtils.getDoubleChest(te) != null) {
/*  41 */           results.add(InventoryUtils.getDoubleChest(te));
/*     */         }
/*     */       }
/*     */     }
/*  45 */     return results;
/*     */   }
/*     */   
/*     */   public static ArrayList<IInventory> getMarkedContainersAdjacentToGolem(World world, EntityGolemBase golem) {
/*  49 */     ArrayList<IInventory> results = new ArrayList();
/*  50 */     for (IInventory inventory : getMarkedContainers(world, golem)) {
/*  51 */       TileEntity te = (TileEntity)inventory;
/*  52 */       if (golem.func_70092_e(te.field_145851_c + 0.5D, te.field_145848_d + 0.5D, te.field_145849_e + 0.5D) < 4.0D) {
/*  53 */         results.add(inventory);
/*  54 */         if (InventoryUtils.getDoubleChest(te) != null) {
/*  55 */           results.add(InventoryUtils.getDoubleChest(te));
/*     */         }
/*     */       }
/*     */     }
/*  59 */     return results;
/*     */   }
/*     */   
/*     */ 
/*     */   public static ArrayList<ChunkCoordinates> getMarkedBlocksAdjacentToGolem(World world, EntityGolemBase golem, byte color)
/*     */   {
/*  65 */     ArrayList<ChunkCoordinates> results = new ArrayList();
/*  66 */     ArrayList<Marker> markers = golem.getMarkers();
/*  67 */     for (Marker marker : markers) {
/*  68 */       if (((marker.color == color) || (color == -1)) && ((golem.field_70170_p.func_147438_o(marker.x, marker.y, marker.z) == null) || (!(golem.field_70170_p.func_147438_o(marker.x, marker.y, marker.z) instanceof IInventory))))
/*     */       {
/*     */ 
/*  71 */         if (golem.func_70092_e(marker.x + 0.5D, marker.y + 0.5D, marker.z + 0.5D) < 4.0D) {
/*  72 */           results.add(new ChunkCoordinates(marker.x, marker.y, marker.z));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  77 */     return results;
/*     */   }
/*     */   
/*     */   public static ArrayList<IInventory> getContainersWithRoom(World world, EntityGolemBase golem, byte color) {
/*  81 */     ArrayList<IInventory> results = new ArrayList();
/*     */     
/*  83 */     for (Iterator i$ = getMarkedContainers(world, golem).iterator(); i$.hasNext();) { inventory = (IInventory)i$.next();
/*  84 */       boolean hasRoom = false;
/*  85 */       for (Integer side : getMarkedSides(golem, (TileEntity)inventory, color)) {
/*  86 */         ItemStack result = InventoryUtils.placeItemStackIntoInventory(golem.getCarried(), inventory, side.intValue(), false);
/*     */         
/*     */ 
/*  89 */         if (!ItemStack.func_77989_b(result, golem.itemCarried)) {
/*  90 */           results.add(inventory);
/*     */           
/*  92 */           break;
/*     */         }
/*     */         
/*  95 */         if (InventoryUtils.getDoubleChest((TileEntity)inventory) != null) {
/*  96 */           result = InventoryUtils.placeItemStackIntoInventory(golem.getCarried(), InventoryUtils.getDoubleChest((TileEntity)inventory), side.intValue(), false);
/*     */           
/*  98 */           if (!ItemStack.func_77989_b(result, golem.itemCarried)) {
/*  99 */             results.add(InventoryUtils.getDoubleChest((TileEntity)inventory));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     IInventory inventory;
/* 105 */     return results;
/*     */   }
/*     */   
/*     */   public static ArrayList<IInventory> getContainersWithRoom(World world, EntityGolemBase golem, byte color, ItemStack itemToMatch)
/*     */   {
/* 110 */     ArrayList results = new ArrayList();
/* 111 */     Iterator i$ = getMarkedContainers(world, golem).iterator();
/*     */     
/* 113 */     while (i$.hasNext())
/*     */     {
/* 115 */       IInventory inventory = (IInventory)i$.next();
/* 116 */       boolean hasRoom = false;
/* 117 */       Iterator i$1 = getMarkedSides(golem, (TileEntity)inventory, color).iterator();
/*     */       
/* 119 */       while (i$1.hasNext())
/*     */       {
/* 121 */         Integer side = (Integer)i$1.next();
/* 122 */         ItemStack result = InventoryUtils.placeItemStackIntoInventory(itemToMatch, inventory, side.intValue(), false);
/*     */         
/* 124 */         if (!ItemStack.func_77989_b(result, itemToMatch))
/*     */         {
/* 126 */           results.add(inventory);
/* 127 */           break;
/*     */         }
/*     */         
/* 130 */         if (InventoryUtils.getDoubleChest((TileEntity)inventory) != null)
/*     */         {
/* 132 */           result = InventoryUtils.placeItemStackIntoInventory(itemToMatch, InventoryUtils.getDoubleChest((TileEntity)inventory), side.intValue(), false);
/*     */           
/* 134 */           if (!ItemStack.func_77989_b(result, itemToMatch))
/*     */           {
/* 136 */             results.add(InventoryUtils.getDoubleChest((TileEntity)inventory));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 142 */     return results;
/*     */   }
/*     */   
/*     */   public static List<Integer> getMarkedSides(EntityGolemBase golem, TileEntity tile, byte color) {
/* 146 */     return getMarkedSides(golem, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, tile.func_145831_w().field_73011_w.field_76574_g, color);
/*     */   }
/*     */   
/*     */   public static List<Integer> getMarkedSides(EntityGolemBase golem, int x, int y, int z, int dim, byte color)
/*     */   {
/* 151 */     List<Integer> out = new ArrayList();
/* 152 */     ArrayList<Marker> gm = golem.getMarkers();
/* 153 */     if ((gm == null) || (gm.size() == 0)) return out;
/* 154 */     for (int a = 0; a < 6; a++) {
/* 155 */       Marker marker = new Marker(x, y, z, dim, (byte)a, color);
/* 156 */       if (contained(gm, marker)) {
/* 157 */         out.add(Integer.valueOf(a));
/*     */       }
/*     */     }
/* 160 */     return out;
/*     */   }
/*     */   
/*     */   public static boolean contained(ArrayList<Marker> l, Marker m) {
/* 164 */     for (Marker mark : l) {
/* 165 */       if (m.equalsFuzzy(mark)) return true;
/*     */     }
/* 167 */     return false;
/*     */   }
/*     */   
/*     */   public static ArrayList<IInventory> getContainersWithGoods(World world, EntityGolemBase golem, ItemStack goods, byte color) {
/* 171 */     ArrayList<IInventory> results = new ArrayList();
/*     */     
/* 173 */     for (IInventory inventory : getMarkedContainers(world, golem)) {
/*     */       try {
/* 175 */         for (Integer side : getMarkedSides(golem, (TileEntity)inventory, color)) {
/* 176 */           if (InventoryUtils.extractStack(inventory, goods, side.intValue(), golem.checkOreDict(), golem.ignoreDamage(), golem.ignoreNBT(), false) != null) {
/* 177 */             results.add(inventory);
/* 178 */             break;
/*     */           }
/* 180 */           if ((InventoryUtils.getDoubleChest((TileEntity)inventory) != null) && 
/* 181 */             (InventoryUtils.extractStack(InventoryUtils.getDoubleChest((TileEntity)inventory), goods, side.intValue(), golem.checkOreDict(), golem.ignoreDamage(), golem.ignoreNBT(), false) != null))
/*     */           {
/* 183 */             results.add(InventoryUtils.getDoubleChest((TileEntity)inventory));
/* 184 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Exception e) {}
/*     */     }
/*     */     
/* 191 */     return results;
/*     */   }
/*     */   
/*     */   public static ArrayList<ItemStack> getMissingItems(EntityGolemBase golem) {
/* 195 */     ForgeDirection facing = ForgeDirection.getOrientation(golem.homeFacing);
/* 196 */     ChunkCoordinates home = golem.func_110172_bL();
/* 197 */     int cX = home.field_71574_a - facing.offsetX;
/* 198 */     int cY = home.field_71572_b - facing.offsetY;
/* 199 */     int cZ = home.field_71573_c - facing.offsetZ;
/* 200 */     int slotCount = golem.inventory.slotCount;
/* 201 */     if (golem.getToggles()[0] != 0) {
/* 202 */       ArrayList<ItemStack> qr = new ArrayList();
/* 203 */       for (int q = 0; q < slotCount; q++) {
/* 204 */         ItemStack toCheck = golem.inventory.inventory[q];
/* 205 */         if (toCheck != null) {
/* 206 */           ItemStack ret = toCheck.func_77946_l();
/* 207 */           qr.add(ret);
/*     */         } }
/* 209 */       return qr;
/*     */     }
/*     */     
/* 212 */     TileEntity tile = golem.field_70170_p.func_147438_o(cX, cY, cZ);
/* 213 */     if (tile == null) return null;
/* 214 */     ArrayList<ItemStack> qr = new ArrayList();
/*     */     label552:
/* 216 */     for (int q = 0; q < slotCount; q++) {
/* 217 */       ItemStack toCheck = golem.inventory.inventory[q];
/* 218 */       if (toCheck != null) {
/* 219 */         int foundAmount = 0;
/*     */         
/* 221 */         boolean repeat = true;
/* 222 */         boolean didRepeat = false;
/* 223 */         while (repeat) {
/* 224 */           if (didRepeat) { repeat = false;
/*     */           }
/* 226 */           if (((tile instanceof ISidedInventory)) && (facing.ordinal() > -1))
/*     */           {
/* 228 */             ISidedInventory isidedinventory = (ISidedInventory)tile;
/* 229 */             int[] aint = isidedinventory.func_94128_d(facing.ordinal());
/*     */             
/* 231 */             for (int j = 0; j < aint.length; j++)
/*     */             {
/* 233 */               if (InventoryUtils.areItemStacksEqual(((ISidedInventory)tile).func_70301_a(aint[j]), toCheck, golem.checkOreDict(), golem.ignoreDamage(), golem.ignoreNBT()))
/*     */               {
/* 235 */                 foundAmount += ((ISidedInventory)tile).func_70301_a(aint[j]).field_77994_a;
/* 236 */                 if (foundAmount >= golem.inventory.getAmountNeededSmart(((ISidedInventory)tile).func_70301_a(aint[j]), golem.getUpgradeAmount(5) > 0)) {
/*     */                   break label552;
/*     */                 }
/*     */               }
/*     */             }
/*     */           } else {
/* 242 */             if (!(tile instanceof IInventory))
/*     */               break;
/* 244 */             int k = ((IInventory)tile).func_70302_i_();
/*     */             
/* 246 */             for (int l = 0; l < k; l++)
/*     */             {
/* 248 */               if (InventoryUtils.areItemStacksEqual(((IInventory)tile).func_70301_a(l), toCheck, golem.checkOreDict(), golem.ignoreDamage(), golem.ignoreNBT()))
/*     */               {
/* 250 */                 foundAmount += ((IInventory)tile).func_70301_a(l).field_77994_a;
/* 251 */                 if (foundAmount >= golem.inventory.getAmountNeededSmart(((IInventory)tile).func_70301_a(l), golem.getUpgradeAmount(5) > 0)) {
/*     */                   break label552;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 258 */           if ((!didRepeat) && (InventoryUtils.getDoubleChest(tile) != null)) {
/* 259 */             tile = InventoryUtils.getDoubleChest(tile);
/* 260 */             didRepeat = true;
/* 261 */           } else { repeat = false;
/*     */           }
/*     */         }
/*     */         
/* 265 */         ItemStack ret = toCheck.func_77946_l();
/* 266 */         ret.field_77994_a -= foundAmount;
/*     */         
/* 268 */         qr.add(ret);
/*     */       }
/*     */     }
/* 271 */     return qr;
/*     */   }
/*     */   
/*     */ 
/* 275 */   static HashMap<String, TileJarFillable> jarlist = new HashMap();
/*     */   
/*     */   public static ChunkCoordinates findJarWithRoom(EntityGolemBase golem)
/*     */   {
/* 279 */     ChunkCoordinates dest = null;
/* 280 */     World world = golem.field_70170_p;
/* 281 */     float dmod = golem.getRange();
/* 282 */     dmod *= dmod;
/* 283 */     ArrayList<TileEntity> jars = new ArrayList();
/* 284 */     ArrayList<TileEntity> others = new ArrayList();
/*     */     
/* 286 */     for (Marker marker : golem.getMarkers()) {
/* 287 */       TileEntity te = world.func_147438_o(marker.x, marker.y, marker.z);
/* 288 */       if ((marker.dim == world.field_73011_w.field_76574_g) && (te != null) && ((te instanceof TileJarFillable))) {
/* 289 */         if (te.func_145835_a(golem.func_110172_bL().field_71574_a, golem.func_110172_bL().field_71572_b, golem.func_110172_bL().field_71573_c) <= dmod)
/*     */         {
/*     */ 
/* 292 */           jars.add((TileJarFillable)te);
/*     */         }
/* 294 */       } else if ((marker.dim == world.field_73011_w.field_76574_g) && (te != null) && ((te instanceof TileEssentiaReservoir))) {
/* 295 */         TileEssentiaReservoir res = (TileEssentiaReservoir)te;
/* 296 */         if ((res.getSuctionAmount(res.facing) > 0) && ((res.getSuctionType(res.facing) == null) || (res.getSuctionType(res.facing) == golem.essentia)) && (te.func_145835_a(golem.func_110172_bL().field_71574_a, golem.func_110172_bL().field_71572_b, golem.func_110172_bL().field_71573_c) <= dmod))
/*     */         {
/*     */ 
/* 299 */           others.add(te);
/*     */         }
/* 301 */       } else if ((marker.dim == world.field_73011_w.field_76574_g) && (te != null) && ((te instanceof IEssentiaTransport)))
/*     */       {
/* 303 */         IEssentiaTransport trans = (IEssentiaTransport)te;
/* 304 */         if ((golem.essentia != null) && (golem.essentiaAmount > 0) && (trans.canInputFrom(ForgeDirection.getOrientation(marker.side))) && (trans.getSuctionAmount(ForgeDirection.getOrientation(marker.side)) > 0) && ((trans.getSuctionType(ForgeDirection.getOrientation(marker.side)) == null) || (trans.getSuctionType(ForgeDirection.getOrientation(marker.side)) == golem.essentia)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 309 */           if (te.func_145835_a(golem.func_110172_bL().field_71574_a, golem.func_110172_bL().field_71572_b, golem.func_110172_bL().field_71573_c) <= dmod)
/*     */           {
/*     */ 
/* 312 */             others.add(te);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 317 */     if (jars.size() > 0) {
/* 318 */       jarlist.clear();
/* 319 */       for (TileEntity jar : jars) {
/* 320 */         jarlist.put(jar.field_145851_c + ":" + jar.field_145848_d + ":" + jar.field_145849_e, (TileJarFillable)jar);
/* 321 */         getConnectedJars((TileJarFillable)jar);
/*     */       }
/*     */     }
/* 324 */     else if (others.size() == 0) { return null;
/*     */     }
/* 326 */     jars = new ArrayList();
/*     */     
/*     */ 
/* 329 */     for (TileEntity te : others) {
/* 330 */       jars.add(te);
/*     */     }
/*     */     
/*     */ 
/* 334 */     for (TileJarFillable jar : jarlist.values()) {
/* 335 */       if ((jar.aspect != null) && (jar.amount > 0) && (jar.amount < jar.maxAmount) && (jar.aspectFilter != null))
/*     */       {
/* 337 */         if ((golem.essentia != null) && (golem.essentiaAmount > 0) && (jar.aspect.equals(golem.essentia)) && (jar.doesContainerAccept(golem.essentia)))
/*     */         {
/* 339 */           jars.add(jar);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 345 */     if (jars.size() == 0) {
/* 346 */       for (TileJarFillable jar : jarlist.values()) {
/* 347 */         if (((jar.aspect == null) || (jar.amount == 0)) && (jar.aspectFilter != null) && (jar.doesContainerAccept(golem.essentia)))
/*     */         {
/*     */ 
/* 350 */           jars.add(jar);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 355 */     if (jars.size() == 0) {
/* 356 */       for (TileJarFillable jar : jarlist.values()) {
/* 357 */         if ((jar.aspect != null) && (jar.amount >= jar.maxAmount) && ((jar instanceof TileJarFillableVoid)) && (jar.aspectFilter != null) && 
/* 358 */           (golem.essentia != null) && (golem.essentiaAmount > 0) && (jar.aspect.equals(golem.essentia)) && (jar.doesContainerAccept(golem.essentia)))
/*     */         {
/* 360 */           jars.add(jar);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 366 */     if (jars.size() == 0) {
/* 367 */       for (TileJarFillable jar : jarlist.values()) {
/* 368 */         if ((jar.aspect != null) && (jar.amount > 0) && (jar.amount < jar.maxAmount) && (jar.aspectFilter == null))
/*     */         {
/* 370 */           if ((golem.essentia != null) && (golem.essentiaAmount > 0) && (jar.aspect.equals(golem.essentia)) && (jar.doesContainerAccept(golem.essentia)))
/*     */           {
/*     */ 
/* 373 */             jars.add(jar);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 380 */     if (jars.size() == 0) {
/* 381 */       for (TileJarFillable jar : jarlist.values()) {
/* 382 */         if (((jar.aspect == null) || (jar.amount == 0)) && (jar.aspectFilter == null) && (!(jar instanceof TileJarFillableVoid)) && (jar.doesContainerAccept(golem.essentia)))
/*     */         {
/*     */ 
/*     */ 
/* 386 */           jars.add(jar);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 391 */     if (jars.size() == 0) {
/* 392 */       for (TileJarFillable jar : jarlist.values()) {
/* 393 */         if ((jar.aspect != null) && ((jar instanceof TileJarFillableVoid)) && (jar.aspectFilter == null) && 
/* 394 */           (golem.essentia != null) && (golem.essentiaAmount > 0) && (jar.aspect.equals(golem.essentia)) && (jar.doesContainerAccept(golem.essentia)))
/*     */         {
/* 396 */           jars.add(jar);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 402 */     if (jars.size() == 0) {
/* 403 */       for (TileJarFillable jar : jarlist.values()) {
/* 404 */         if (((jar.aspect == null) || (jar.amount == 0)) && (jar.aspectFilter == null) && ((jar instanceof TileJarFillableVoid)) && (jar.doesContainerAccept(golem.essentia)))
/*     */         {
/*     */ 
/* 407 */           jars.add(jar);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 412 */     double dist = Double.MAX_VALUE;
/* 413 */     for (TileEntity jar : jars) {
/* 414 */       double d = jar.func_145835_a(golem.func_110172_bL().field_71574_a, golem.func_110172_bL().field_71572_b, golem.func_110172_bL().field_71573_c);
/*     */       
/*     */ 
/* 417 */       if ((jar instanceof TileJarFillableVoid)) d += dmod;
/* 418 */       if (d < dist) {
/* 419 */         dist = d;
/* 420 */         dest = new ChunkCoordinates(jar.field_145851_c, jar.field_145848_d, jar.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/* 424 */     jarlist.clear();
/* 425 */     return dest;
/*     */   }
/*     */   
/*     */   private static void getConnectedJars(TileJarFillable jar)
/*     */   {
/* 430 */     World world = jar.func_145831_w();
/* 431 */     for (int dir = 0; dir < 6; dir++) {
/* 432 */       ForgeDirection fd = ForgeDirection.getOrientation(dir);
/* 433 */       int xx = jar.field_145851_c + fd.offsetX;
/* 434 */       int yy = jar.field_145848_d + fd.offsetY;
/* 435 */       int zz = jar.field_145849_e + fd.offsetZ;
/*     */       
/* 437 */       if (!jarlist.containsKey(xx + ":" + yy + ":" + zz))
/*     */       {
/* 439 */         TileEntity te = world.func_147438_o(xx, yy, zz);
/* 440 */         if ((te != null) && ((te instanceof TileJarFillable))) {
/* 441 */           jarlist.put(te.field_145851_c + ":" + te.field_145848_d + ":" + te.field_145849_e, (TileJarFillable)te);
/* 442 */           getConnectedJars((TileJarFillable)te);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 448 */   private static ArrayList<Integer> reggedLiquids = null;
/*     */   
/*     */   public static ArrayList<Integer> getReggedLiquids() {
/* 451 */     if (reggedLiquids == null) {
/* 452 */       reggedLiquids = new ArrayList();
/*     */       
/* 454 */       for (Integer f : FluidRegistry.getRegisteredFluidIDs().values()) {
/* 455 */         reggedLiquids.add(f);
/*     */       }
/*     */     }
/* 458 */     return reggedLiquids;
/*     */   }
/*     */   
/*     */   public static ArrayList<FluidStack> getMissingLiquids(EntityGolemBase golem)
/*     */   {
/* 463 */     ArrayList<FluidStack> out = new ArrayList();
/*     */     
/*     */ 
/* 466 */     ForgeDirection facing = ForgeDirection.getOrientation(golem.homeFacing);
/* 467 */     ChunkCoordinates home = golem.func_110172_bL();
/* 468 */     int cX = home.field_71574_a - facing.offsetX;
/* 469 */     int cY = home.field_71572_b - facing.offsetY;
/* 470 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/* 472 */     TileEntity tile = golem.field_70170_p.func_147438_o(cX, cY, cZ);
/* 473 */     IFluidHandler fluidhandler; if ((tile != null) && ((tile instanceof IFluidHandler))) {
/* 474 */       fluidhandler = (IFluidHandler)tile;
/*     */       
/*     */ 
/* 477 */       for (Integer id : getReggedLiquids())
/*     */       {
/* 479 */         if ((golem.fluidCarried == null) || (golem.fluidCarried.amount <= 0) || (golem.fluidCarried.fluidID == id.intValue()))
/*     */         {
/*     */ 
/* 482 */           if (fluidhandler.canFill(facing, FluidRegistry.getFluid(id.intValue())))
/*     */           {
/* 484 */             FluidStack fs = new FluidStack(FluidRegistry.getFluid(id.intValue()), Integer.MAX_VALUE);
/* 485 */             if (golem.inventory.hasSomething()) {
/* 486 */               FluidStack fis = null;
/* 487 */               boolean found = false;
/* 488 */               for (int a = 0; a < golem.inventory.slotCount; a++) {
/* 489 */                 fis = net.minecraftforge.fluids.FluidContainerRegistry.getFluidForFilledItem(golem.inventory.func_70301_a(a));
/* 490 */                 if ((fis != null) && (fis.isFluidEqual(fs))) {
/* 491 */                   found = true;
/* 492 */                   break;
/*     */                 }
/*     */               }
/* 495 */               if (!found) {}
/*     */             }
/*     */             else
/*     */             {
/* 499 */               out.add(new FluidStack(id.intValue(), Integer.MAX_VALUE));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 505 */     return out;
/*     */   }
/*     */   
/*     */   public static net.minecraft.util.Vec3 findPossibleLiquid(FluidStack ls, EntityGolemBase golem)
/*     */   {
/* 510 */     ForgeDirection facing = ForgeDirection.getOrientation(golem.homeFacing);
/* 511 */     ChunkCoordinates home = golem.func_110172_bL();
/* 512 */     int cX = home.field_71574_a - facing.offsetX;
/* 513 */     int cY = home.field_71572_b - facing.offsetY;
/* 514 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/* 516 */     float dmod = golem.getRange();
/*     */     
/* 518 */     ChunkCoordinates v = null;
/*     */     
/* 520 */     ArrayList<IFluidHandler> fluidhandlers = getMarkedFluidHandlers(ls, golem.field_70170_p, golem);
/* 521 */     double dd = Double.MAX_VALUE;
/* 522 */     if (fluidhandlers != null) {
/* 523 */       for (IFluidHandler fluidhandler : fluidhandlers)
/* 524 */         if (fluidhandler != null) {
/* 525 */           TileEntity tile = (TileEntity)fluidhandler;
/* 526 */           double d = golem.func_70092_e(tile.field_145851_c + 0.5D, tile.field_145848_d + 0.5D, tile.field_145849_e + 0.5D);
/* 527 */           if ((d <= dmod * dmod) && (d < dd)) {
/* 528 */             dd = d;
/* 529 */             v = new ChunkCoordinates(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
/*     */           }
/*     */         }
/*     */     }
/* 533 */     if (v == null) {
/* 534 */       ArrayList<ChunkCoordinates> inworld = getMarkedFluidBlocks(ls, golem.field_70170_p, golem);
/* 535 */       dd = Double.MAX_VALUE;
/* 536 */       if (inworld != null) {
/* 537 */         for (ChunkCoordinates coord : inworld) {
/* 538 */           if (coord != null) {
/* 539 */             double d = golem.func_70092_e(coord.field_71574_a + 0.5D, coord.field_71572_b + 0.5D, coord.field_71573_c + 0.5D);
/* 540 */             if ((d <= dmod * dmod) && (d < dd)) {
/* 541 */               dd = d;
/* 542 */               v = new ChunkCoordinates(coord.field_71574_a, coord.field_71572_b, coord.field_71573_c);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 549 */     if (v != null) {
/* 550 */       return net.minecraft.util.Vec3.func_72443_a(v.field_71574_a, v.field_71572_b, v.field_71573_c);
/*     */     }
/*     */     
/*     */ 
/* 554 */     return null;
/*     */   }
/*     */   
/*     */   public static ArrayList<Marker> getMarkedFluidHandlersAdjacentToGolem(FluidStack ls, World world, EntityGolemBase golem) {
/* 558 */     ArrayList<Marker> results = new ArrayList();
/* 559 */     for (Marker marker : golem.getMarkers()) {
/* 560 */       TileEntity te = world.func_147438_o(marker.x, marker.y, marker.z);
/* 561 */       if ((marker.dim == world.field_73011_w.field_76574_g) && (te != null) && ((te instanceof IFluidHandler)) && (golem.func_70092_e(te.field_145851_c + 0.5D, te.field_145848_d + 0.5D, te.field_145849_e + 0.5D) < 4.0D))
/*     */       {
/*     */ 
/*     */ 
/* 565 */         FluidStack fs = ((IFluidHandler)te).drain(ForgeDirection.getOrientation(marker.side), new FluidStack(ls.getFluid(), 1), false);
/*     */         
/* 567 */         if ((fs != null) && (fs.amount > 0))
/* 568 */           results.add(marker);
/*     */       }
/*     */     }
/* 571 */     return results;
/*     */   }
/*     */   
/*     */   public static ArrayList<IFluidHandler> getMarkedFluidHandlers(FluidStack ls, World world, EntityGolemBase golem) {
/* 575 */     ArrayList<IFluidHandler> results = new ArrayList();
/* 576 */     for (Marker marker : golem.getMarkers()) {
/* 577 */       TileEntity te = world.func_147438_o(marker.x, marker.y, marker.z);
/* 578 */       if ((marker.dim == world.field_73011_w.field_76574_g) && (te != null) && ((te instanceof IFluidHandler)))
/*     */       {
/*     */ 
/* 581 */         FluidStack fs = ((IFluidHandler)te).drain(ForgeDirection.getOrientation(marker.side), new FluidStack(ls.getFluid(), 1), false);
/*     */         
/* 583 */         if ((fs != null) && (fs.amount > 0))
/* 584 */           results.add((IFluidHandler)te);
/*     */       }
/*     */     }
/* 587 */     return results;
/*     */   }
/*     */   
/*     */   public static ArrayList<ChunkCoordinates> getMarkedFluidBlocks(FluidStack ls, World world, EntityGolemBase golem) {
/* 591 */     ArrayList<ChunkCoordinates> results = new ArrayList();
/* 592 */     for (Marker marker : golem.getMarkers()) {
/* 593 */       net.minecraft.block.Block bi = world.func_147439_a(marker.x, marker.y, marker.z);
/* 594 */       if ((marker.dim == world.field_73011_w.field_76574_g) && (FluidRegistry.getFluid(ls.fluidID).getBlock() == bi))
/*     */       {
/*     */ 
/* 597 */         if (((bi instanceof BlockFluidBase)) && (((BlockFluidBase)bi).canDrain(world, marker.x, marker.y, marker.z)))
/*     */         {
/* 599 */           results.add(new ChunkCoordinates(marker.x, marker.y, marker.z));
/*     */ 
/*     */         }
/* 602 */         else if ((ls.fluidID == FluidRegistry.WATER.getID()) || (ls.fluidID == FluidRegistry.LAVA.getID())) {
/* 603 */           int wmd = world.func_72805_g(marker.x, marker.y, marker.z);
/* 604 */           if (((FluidRegistry.lookupFluidForBlock(bi) == FluidRegistry.WATER) && (ls.fluidID == FluidRegistry.WATER.getID())) || ((FluidRegistry.lookupFluidForBlock(bi) == FluidRegistry.LAVA) && (ls.fluidID == FluidRegistry.LAVA.getID()) && (wmd == 0)))
/*     */           {
/*     */ 
/*     */ 
/* 608 */             results.add(new ChunkCoordinates(marker.x, marker.y, marker.z));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 613 */     return results;
/*     */   }
/*     */   
/*     */   public static ArrayList<ItemStack> getItemsNeeded(EntityGolemBase golem, boolean fuzzy)
/*     */   {
/* 618 */     ArrayList<ItemStack> needed = null;
/* 619 */     switch (golem.getCore()) {
/*     */     case 1: 
/* 621 */       needed = golem.inventory.getItemsNeeded(golem.getUpgradeAmount(5) > 0);
/* 622 */       if (needed.size() == 0) return null;
/* 623 */       return filterEmptyCore(golem, needed);
/*     */     case 8: 
/* 625 */       needed = golem.inventory.getItemsNeeded(golem.getUpgradeAmount(5) > 0);
/* 626 */       if (needed.size() == 0) return null;
/* 627 */       return filterUseCore(golem, needed);
/*     */     case 10: 
/* 629 */       needed = getItemsInHomeContainer(golem);
/* 630 */       return filterSortCore(golem, needed);
/*     */     }
/*     */     
/* 633 */     return needed;
/*     */   }
/*     */   
/*     */   private static ArrayList<ItemStack> filterEmptyCore(EntityGolemBase golem, ArrayList<ItemStack> in) {
/* 637 */     ArrayList<ItemStack> out = new ArrayList();
/* 638 */     for (ItemStack itemToMatch : in) {
/* 639 */       if ((!isOnTimeOut(golem, itemToMatch)) && (findSomethingEmptyCore(golem, itemToMatch))) {
/* 640 */         out.add(itemToMatch);
/*     */       }
/*     */     }
/* 643 */     return out;
/*     */   }
/*     */   
/*     */   private static ArrayList<ItemStack> filterUseCore(EntityGolemBase golem, ArrayList<ItemStack> in) {
/* 647 */     ArrayList<ItemStack> out = new ArrayList();
/* 648 */     for (ItemStack itemToMatch : in) {
/* 649 */       if ((!isOnTimeOut(golem, itemToMatch)) && (findSomethingUseCore(golem, itemToMatch))) {
/* 650 */         out.add(itemToMatch);
/*     */       }
/*     */     }
/* 653 */     return out;
/*     */   }
/*     */   
/*     */   private static ArrayList<ItemStack> filterSortCore(EntityGolemBase golem, ArrayList<ItemStack> in) {
/* 657 */     ArrayList<ItemStack> out = new ArrayList();
/* 658 */     for (ItemStack itemToMatch : in) {
/* 659 */       if ((!isOnTimeOut(golem, itemToMatch)) && (findSomethingSortCore(golem, itemToMatch))) {
/* 660 */         out.add(itemToMatch);
/*     */       }
/*     */     }
/* 663 */     return out;
/*     */   }
/*     */   
/*     */   public static boolean findSomethingUseCore(EntityGolemBase golem, ItemStack itemToMatch)
/*     */   {
/* 668 */     ArrayList matchingColors = golem.getColorsMatching(itemToMatch);
/* 669 */     Iterator i$ = matchingColors.iterator();
/*     */     
/* 671 */     while (i$.hasNext())
/*     */     {
/* 673 */       byte col = ((Byte)i$.next()).byteValue();
/* 674 */       ArrayList markers = golem.getMarkers();
/* 675 */       Iterator i$1 = markers.iterator();
/*     */       
/* 677 */       while (i$1.hasNext())
/*     */       {
/* 679 */         Marker marker = (Marker)i$1.next();
/*     */         
/* 681 */         if (((marker.color == col) || (col == -1)) && ((golem.getToggles()[0] == 0) || (golem.field_70170_p.func_147437_c(marker.x, marker.y, marker.z))) && ((golem.getToggles()[0] != 0) || (!golem.field_70170_p.func_147437_c(marker.x, marker.y, marker.z))))
/*     */         {
/*     */ 
/*     */ 
/* 685 */           ForgeDirection opp = ForgeDirection.getOrientation(marker.side);
/*     */           
/* 687 */           if (golem.field_70170_p.func_147437_c(marker.x + opp.offsetX, marker.y + opp.offsetY, marker.z + opp.offsetZ))
/*     */           {
/* 689 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 694 */     itemTimeout.add(new SortingItemTimeout(golem.func_145782_y(), itemToMatch.func_77946_l(), System.currentTimeMillis() + Config.golemIgnoreDelay));
/* 695 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean findSomethingEmptyCore(EntityGolemBase golem, ItemStack itemToMatch) {
/* 699 */     ArrayList matchingColors = golem.getColorsMatching(itemToMatch);
/* 700 */     Iterator i$ = matchingColors.iterator();
/*     */     
/*     */ 
/*     */ 
/* 704 */     while (i$.hasNext())
/*     */     {
/* 706 */       byte color = ((Byte)i$.next()).byteValue();
/* 707 */       ArrayList markers = getContainersWithRoom(golem.field_70170_p, golem, color, itemToMatch);
/*     */       
/* 709 */       if (markers.size() != 0)
/*     */       {
/* 711 */         ForgeDirection i$1 = ForgeDirection.getOrientation(golem.homeFacing);
/* 712 */         ChunkCoordinates marker = golem.func_110172_bL();
/* 713 */         int cX = marker.field_71574_a - i$1.offsetX;
/* 714 */         int cY = marker.field_71572_b - i$1.offsetY;
/* 715 */         int cZ = marker.field_71573_c - i$1.offsetZ;
/* 716 */         double range = Double.MAX_VALUE;
/* 717 */         float dmod = golem.getRange();
/* 718 */         Iterator i$2 = markers.iterator();
/* 719 */         while (i$2.hasNext())
/*     */         {
/* 721 */           IInventory te = (IInventory)i$2.next();
/* 722 */           double distance = golem.func_70092_e(((TileEntity)te).field_145851_c + 0.5D, ((TileEntity)te).field_145848_d + 0.5D, ((TileEntity)te).field_145849_e + 0.5D);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 727 */           if ((distance < range) && (distance <= dmod * dmod) && ((((TileEntity)te).field_145851_c != cX) || (((TileEntity)te).field_145848_d != cY) || (((TileEntity)te).field_145849_e != cZ)))
/*     */           {
/*     */ 
/*     */ 
/* 731 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 737 */     i$ = matchingColors.iterator();
/*     */     
/* 739 */     while (i$.hasNext())
/*     */     {
/* 741 */       byte color = ((Byte)i$.next()).byteValue();
/* 742 */       ArrayList markers = golem.getMarkers();
/* 743 */       Iterator i$3 = markers.iterator();
/*     */       
/* 745 */       while (i$3.hasNext())
/*     */       {
/* 747 */         Marker marker1 = (Marker)i$3.next();
/*     */         
/* 749 */         if (((marker1.color == color) || (color == -1)) && ((golem.field_70170_p.func_147438_o(marker1.x, marker1.y, marker1.z) == null) || (!(golem.field_70170_p.func_147438_o(marker1.x, marker1.y, marker1.z) instanceof IInventory))))
/*     */         {
/*     */ 
/*     */ 
/* 753 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 757 */     itemTimeout.add(new SortingItemTimeout(golem.func_145782_y(), itemToMatch.func_77946_l(), System.currentTimeMillis() + Config.golemIgnoreDelay));
/* 758 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean findSomethingSortCore(EntityGolemBase golem, ItemStack itemToMatch)
/*     */   {
/* 763 */     ArrayList markers = getContainersWithRoom(golem.field_70170_p, golem, (byte)-1, itemToMatch);
/*     */     
/* 765 */     if (markers.size() != 0)
/*     */     {
/* 767 */       ForgeDirection i$1 = ForgeDirection.getOrientation(golem.homeFacing);
/* 768 */       ChunkCoordinates marker = golem.func_110172_bL();
/* 769 */       int cX = marker.field_71574_a - i$1.offsetX;
/* 770 */       int cY = marker.field_71572_b - i$1.offsetY;
/* 771 */       int cZ = marker.field_71573_c - i$1.offsetZ;
/* 772 */       double range = Double.MAX_VALUE;
/* 773 */       float dmod = golem.getRange();
/* 774 */       Iterator i$2 = markers.iterator();
/* 775 */       IInventory te; Iterator i$; while (i$2.hasNext())
/*     */       {
/* 777 */         te = (IInventory)i$2.next();
/* 778 */         double distance = golem.func_70092_e(((TileEntity)te).field_145851_c + 0.5D, ((TileEntity)te).field_145848_d + 0.5D, ((TileEntity)te).field_145849_e + 0.5D);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 783 */         if ((distance < range) && (distance <= dmod * dmod) && ((((TileEntity)te).field_145851_c != cX) || (((TileEntity)te).field_145848_d != cY) || (((TileEntity)te).field_145849_e != cZ)))
/*     */         {
/*     */ 
/*     */ 
/* 787 */           for (i$ = getMarkedSides(golem, (TileEntity)te, (byte)-1).iterator(); i$.hasNext();) { int side = ((Integer)i$.next()).intValue();
/* 788 */             if (InventoryUtils.inventoryContains(te, itemToMatch, side, golem.checkOreDict(), golem.ignoreDamage(), golem.ignoreNBT())) {
/* 789 */               return true;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 795 */     itemTimeout.add(new SortingItemTimeout(golem.func_145782_y(), itemToMatch.func_77946_l(), System.currentTimeMillis() + Config.golemIgnoreDelay));
/* 796 */     return false;
/*     */   }
/*     */   
/* 799 */   static ArrayList<SortingItemTimeout> itemTimeout = new ArrayList();
/*     */   
/*     */   public static class SortingItemTimeout implements Comparable {
/* 802 */     ItemStack stack = null;
/* 803 */     int golemId = 0;
/* 804 */     long time = 0L;
/*     */     
/* 806 */     public SortingItemTimeout(int golemId, ItemStack stack, long time) { this.stack = stack;
/* 807 */       this.golemId = golemId;
/* 808 */       this.time = time;
/*     */     }
/*     */     
/*     */     public int compareTo(Object arg0) {
/* 812 */       return equals(arg0) ? 0 : -1;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 816 */       if ((obj instanceof SortingItemTimeout)) {
/* 817 */         SortingItemTimeout t = (SortingItemTimeout)obj;
/* 818 */         if (this.golemId != t.golemId) return false;
/* 819 */         if (!this.stack.func_77969_a(t.stack)) return false;
/* 820 */         if (!ItemStack.func_77970_a(this.stack, t.stack)) return false;
/*     */       }
/* 822 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isOnTimeOut(EntityGolemBase golem, ItemStack stack)
/*     */   {
/* 828 */     SortingItemTimeout tos = new SortingItemTimeout(golem.func_145782_y(), stack, 0L);
/* 829 */     if (itemTimeout.contains(tos)) {
/* 830 */       int q = itemTimeout.indexOf(tos);
/* 831 */       SortingItemTimeout tos2 = (SortingItemTimeout)itemTimeout.get(q);
/* 832 */       if (System.currentTimeMillis() < tos2.time) {
/* 833 */         return true;
/*     */       }
/* 835 */       itemTimeout.remove(q);
/*     */     }
/*     */     
/* 838 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean validTargetForItem(EntityGolemBase golem, ItemStack stack) {
/* 842 */     if (isOnTimeOut(golem, stack)) { return false;
/*     */     }
/* 844 */     ForgeDirection facing = ForgeDirection.getOrientation(golem.homeFacing);
/* 845 */     ChunkCoordinates home = golem.func_110172_bL();
/* 846 */     int cX = home.field_71574_a - facing.offsetX;
/* 847 */     int cY = home.field_71572_b - facing.offsetY;
/* 848 */     int cZ = home.field_71573_c - facing.offsetZ;
/* 849 */     switch (golem.getCore()) {
/*     */     case 1: 
/* 851 */       return findSomethingEmptyCore(golem, stack);
/*     */     case 8: 
/* 853 */       return findSomethingUseCore(golem, stack);
/*     */     case 10: 
/* 855 */       return findSomethingSortCore(golem, stack);
/*     */     }
/* 857 */     TileEntity tile = golem.field_70170_p.func_147438_o(cX, cY, cZ);
/* 858 */     ArrayList<ItemStack> neededList = getItemsNeeded(golem, golem.getUpgradeAmount(5) > 0);
/* 859 */     if ((neededList != null) && (neededList.size() > 0)) {
/* 860 */       for (ItemStack ss : neededList) {
/* 861 */         if (InventoryUtils.areItemStacksEqual(ss, golem.itemCarried, golem.checkOreDict(), golem.ignoreDamage(), golem.ignoreNBT()))
/*     */         {
/* 863 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 868 */     itemTimeout.add(new SortingItemTimeout(golem.func_145782_y(), stack.func_77946_l(), System.currentTimeMillis() + Config.golemIgnoreDelay));
/* 869 */     return false;
/*     */   }
/*     */   
/*     */   public static ItemStack getFirstItemUsingTimeout(EntityGolemBase golem, IInventory inventory, int side, boolean doit) {
/* 873 */     ItemStack stack1 = null;
/*     */     
/* 875 */     if (((inventory instanceof ISidedInventory)) && (side > -1))
/*     */     {
/* 877 */       ISidedInventory isidedinventory = (ISidedInventory)inventory;
/* 878 */       int[] aint = isidedinventory.func_94128_d(side);
/*     */       
/* 880 */       for (int j = 0; j < aint.length; j++)
/*     */       {
/* 882 */         if ((stack1 == null) && (inventory.func_70301_a(aint[j]) != null)) {
/* 883 */           if (!isOnTimeOut(golem, inventory.func_70301_a(aint[j]))) {
/* 884 */             stack1 = inventory.func_70301_a(aint[j]).func_77946_l();
/* 885 */             stack1.field_77994_a = golem.getCarrySpace();
/*     */           }
/* 887 */         } else { if (stack1 != null) stack1 = InventoryUtils.attemptExtraction(inventory, stack1, aint[j], side, false, false, false, doit);
/* 888 */           if (stack1 != null)
/*     */             break;
/*     */         }
/*     */       }
/*     */     } else {
/* 893 */       int k = inventory.func_70302_i_();
/*     */       
/* 895 */       for (int l = 0; l < k; l++)
/*     */       {
/* 897 */         if ((stack1 == null) && (inventory.func_70301_a(l) != null)) {
/* 898 */           if (!isOnTimeOut(golem, inventory.func_70301_a(l))) {
/* 899 */             stack1 = inventory.func_70301_a(l).func_77946_l();
/* 900 */             stack1.field_77994_a = golem.getCarrySpace();
/*     */           }
/* 902 */         } else { if (stack1 != null) stack1 = InventoryUtils.attemptExtraction(inventory, stack1, l, side, false, false, false, doit);
/* 903 */           if (stack1 != null)
/*     */             break;
/*     */         } }
/*     */     }
/* 907 */     if ((stack1 == null) || (stack1.field_77994_a == 0))
/*     */     {
/* 909 */       if (doit) inventory.func_70296_d();
/* 910 */       return null;
/*     */     }
/*     */     
/* 913 */     return stack1.func_77946_l();
/*     */   }
/*     */   
/*     */   public static ArrayList<ItemStack> getItemsInHomeContainer(EntityGolemBase golem) {
/* 917 */     ForgeDirection facing = ForgeDirection.getOrientation(golem.homeFacing);
/* 918 */     ChunkCoordinates home = golem.func_110172_bL();
/* 919 */     int cX = home.field_71574_a - facing.offsetX;
/* 920 */     int cY = home.field_71572_b - facing.offsetY;
/* 921 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/* 923 */     TileEntity tile = golem.field_70170_p.func_147438_o(cX, cY, cZ);
/* 924 */     if ((tile == null) || (!(tile instanceof IInventory))) return null;
/* 925 */     int[] aint = null;
/* 926 */     ArrayList<ItemStack> out = new ArrayList();
/*     */     
/* 928 */     IInventory inv = (IInventory)tile;
/* 929 */     if (((tile instanceof ISidedInventory)) && (facing.ordinal() > -1))
/*     */     {
/* 931 */       aint = ((ISidedInventory)inv).func_94128_d(facing.ordinal());
/*     */     } else {
/* 933 */       aint = new int[inv.func_70302_i_()];
/* 934 */       for (int a = 0; a < inv.func_70302_i_(); a++) {
/* 935 */         aint[a] = a;
/*     */       }
/*     */     }
/*     */     
/* 939 */     if ((aint != null) && (aint.length > 0)) {
/* 940 */       for (int j = 0; j < aint.length; j++)
/*     */       {
/* 942 */         if (inv.func_70301_a(aint[j]) != null) {
/* 943 */           out.add(inv.func_70301_a(aint[j]).func_77946_l());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 948 */     return out;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/GolemHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */