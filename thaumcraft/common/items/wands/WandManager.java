/*     */ package thaumcraft.common.items.wands;
/*     */ 
/*     */ import baubles.api.BaublesApi;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.TreeMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.IArchitect;
/*     */ import thaumcraft.api.IVisDiscountGear;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.api.nodes.NodeModifier;
/*     */ import thaumcraft.api.nodes.NodeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.items.baubles.ItemAmuletVis;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusTrade;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.tiles.TileEldritchAltar;
/*     */ import thaumcraft.common.tiles.TileInfusionMatrix;
/*     */ import thaumcraft.common.tiles.TileInfusionPillar;
/*     */ import thaumcraft.common.tiles.TileJarNode;
/*     */ import thaumcraft.common.tiles.TileNode;
/*     */ import thaumcraft.common.tiles.TilePedestal;
/*     */ import thaumcraft.common.tiles.TileThaumatorium;
/*     */ 
/*     */ public class WandManager implements thaumcraft.api.wands.IWandTriggerManager
/*     */ {
/*     */   public static float getTotalVisDiscount(EntityPlayer player, Aspect aspect)
/*     */   {
/*  51 */     int total = 0;
/*  52 */     if (player == null) { return 0.0F;
/*     */     }
/*  54 */     IInventory baubles = BaublesApi.getBaubles(player);
/*  55 */     for (int a = 0; a < 4; a++) {
/*  56 */       if ((baubles.func_70301_a(a) != null) && ((baubles.func_70301_a(a).func_77973_b() instanceof IVisDiscountGear))) {
/*  57 */         total += ((IVisDiscountGear)baubles.func_70301_a(a).func_77973_b()).getVisDiscount(baubles.func_70301_a(a), player, aspect);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  62 */     for (int a = 0; a < 4; a++) {
/*  63 */       if ((player.field_71071_by.func_70440_f(a) != null) && ((player.field_71071_by.func_70440_f(a).func_77973_b() instanceof IVisDiscountGear))) {
/*  64 */         total += ((IVisDiscountGear)player.field_71071_by.func_70440_f(a).func_77973_b()).getVisDiscount(player.field_71071_by.func_70440_f(a), player, aspect);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  69 */     if ((player.func_82165_m(Config.potionVisExhaustID)) || (player.func_82165_m(Config.potionInfVisExhaustID)))
/*     */     {
/*  71 */       int level1 = 0;
/*  72 */       int level2 = 0;
/*  73 */       if (player.func_82165_m(Config.potionVisExhaustID)) {
/*  74 */         level1 = player.func_70660_b(net.minecraft.potion.Potion.field_76425_a[Config.potionVisExhaustID]).func_76458_c();
/*     */       }
/*  76 */       if (player.func_82165_m(Config.potionInfVisExhaustID)) {
/*  77 */         level2 = player.func_70660_b(net.minecraft.potion.Potion.field_76425_a[Config.potionInfVisExhaustID]).func_76458_c();
/*     */       }
/*  79 */       total -= (Math.max(level1, level2) + 1) * 10;
/*     */     }
/*     */     
/*  82 */     return total / 100.0F;
/*     */   }
/*     */   
/*     */   public static boolean consumeVisFromInventory(EntityPlayer player, AspectList cost)
/*     */   {
/*  87 */     IInventory baubles = BaublesApi.getBaubles(player);
/*  88 */     for (int a = 0; a < 4; a++) {
/*  89 */       if ((baubles.func_70301_a(a) != null) && ((baubles.func_70301_a(a).func_77973_b() instanceof ItemAmuletVis))) {
/*  90 */         boolean done = ((ItemAmuletVis)baubles.func_70301_a(a).func_77973_b()).consumeAllVis(baubles.func_70301_a(a), player, cost, true, true);
/*  91 */         if (done) { return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  96 */     for (int a = player.field_71071_by.field_70462_a.length - 1; a >= 0; a--) {
/*  97 */       ItemStack item = player.field_71071_by.field_70462_a[a];
/*  98 */       if ((item != null) && ((item.func_77973_b() instanceof ItemWandCasting))) {
/*  99 */         boolean done = ((ItemWandCasting)item.func_77973_b()).consumeAllVis(item, player, cost, true, true);
/* 100 */         if (done) return true;
/*     */       }
/*     */     }
/* 103 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean createCrucible(ItemStack is, EntityPlayer player, World world, int x, int y, int z)
/*     */   {
/* 108 */     ItemWandCasting wand = (ItemWandCasting)is.func_77973_b();
/* 109 */     if (!world.field_72995_K) {
/* 110 */       world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/* 111 */       world.func_147468_f(x, y, z);
/* 112 */       world.func_147465_d(x, y, z, ConfigBlocks.blockMetalDevice, 0, 3);
/* 113 */       world.func_147459_d(x, y, z, world.func_147439_a(x, y, z));
/* 114 */       world.func_147471_g(x, y, z);
/* 115 */       world.func_147452_c(x, y, z, ConfigBlocks.blockMetalDevice, 1, 1);
/* 116 */       return true;
/*     */     }
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean createInfusionAltar(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z) {
/* 122 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 123 */     for (int xx = x - 2; xx <= x; xx++) {
/* 124 */       for (int yy = y - 2; yy <= y; yy++)
/* 125 */         for (int zz = z - 2; zz <= z; zz++)
/* 126 */           if ((fitInfusionAltar(world, xx, yy, zz)) && (wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.FIRE, 25).add(Aspect.EARTH, 25).add(Aspect.ORDER, 25).add(Aspect.AIR, 25).add(Aspect.ENTROPY, 25).add(Aspect.WATER, 25), true)))
/*     */           {
/*     */ 
/*     */ 
/* 130 */             if (!world.field_72995_K) {
/* 131 */               replaceInfusionAltar(world, xx, yy, zz);
/* 132 */               return true;
/*     */             }
/* 134 */             return false;
/*     */           }
/*     */     }
/* 137 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean fitInfusionAltar(World world, int x, int y, int z) {
/* 141 */     ItemStack br1 = new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6);
/* 142 */     ItemStack br2 = new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7);
/* 143 */     ItemStack bs = new ItemStack(ConfigBlocks.blockStoneDevice, 1, 2);
/* 144 */     ItemStack bp = new ItemStack(ConfigBlocks.blockStoneDevice, 1, 1);
/*     */     
/* 146 */     ItemStack[][][] blueprint = { { { null, null, null }, { null, bs, null }, { null, null, null } }, { { br1, null, br1 }, { null, null, null }, { br1, null, br1 } }, { { br2, null, br2 }, { null, null, null }, { br2, null, br2 } } };
/*     */     
/*     */ 
/* 149 */     for (int yy = 0; yy < 3; yy++) {
/* 150 */       for (int xx = 0; xx < 3; xx++)
/* 151 */         for (int zz = 0; zz < 3; zz++)
/* 152 */           if (blueprint[yy][xx][zz] == null) {
/* 153 */             if ((xx == 1) && (zz == 1) && (yy == 2)) {
/* 154 */               TileEntity t = world.func_147438_o(x + xx, y - yy + 2, z + zz);
/* 155 */               if ((t == null) || (!(t instanceof TilePedestal))) return false;
/*     */             }
/* 157 */             else if (!world.func_147437_c(x + xx, y - yy + 2, z + zz)) {
/* 158 */               return false;
/*     */             }
/* 160 */           } else { Block block = world.func_147439_a(x + xx, y - yy + 2, z + zz);
/* 161 */             int md = world.func_72805_g(x + xx, y - yy + 2, z + zz);
/* 162 */             if (!new ItemStack(block, 1, md).func_77969_a(blueprint[yy][xx][zz]))
/* 163 */               return false;
/*     */           }
/*     */     }
/* 166 */     return true;
/*     */   }
/*     */   
/*     */   public static void replaceInfusionAltar(World world, int x, int y, int z)
/*     */   {
/* 171 */     int[][][] blueprint = { { { 0, 0, 0 }, { 0, 9, 0 }, { 0, 0, 0 } }, { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } }, { { 2, 0, 3 }, { 0, 0, 0 }, { 4, 0, 5 } } };
/*     */     
/*     */ 
/* 174 */     for (int yy = 0; yy < 3; yy++) {
/* 175 */       for (int xx = 0; xx < 3; xx++)
/* 176 */         for (int zz = 0; zz < 3; zz++)
/* 177 */           if (blueprint[yy][xx][zz] != 0) {
/* 178 */             if (blueprint[yy][xx][zz] == 1) {
/* 179 */               world.func_147465_d(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 4, 3);
/* 180 */               world.func_147452_c(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 1, 0);
/*     */             }
/*     */             
/* 183 */             if ((blueprint[yy][xx][zz] > 1) && (blueprint[yy][xx][zz] < 9)) {
/* 184 */               world.func_147465_d(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 3, 3);
/* 185 */               TileInfusionPillar tip = (TileInfusionPillar)world.func_147438_o(x + xx, y - yy + 2, z + zz);
/* 186 */               tip.orientation = ((byte)blueprint[yy][xx][zz]);
/* 187 */               world.func_147471_g(x + xx, y - yy + 2, z + zz);
/* 188 */               world.func_147452_c(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 1, 0);
/*     */             }
/* 190 */             if (blueprint[yy][xx][zz] == 9) {
/* 191 */               TileInfusionMatrix tis = (TileInfusionMatrix)world.func_147438_o(x + xx, y - yy + 2, z + zz);
/* 192 */               tis.active = true;
/* 193 */               world.func_147471_g(x + xx, y - yy + 2, z + zz);
/*     */             }
/*     */           }
/*     */     }
/* 197 */     world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static boolean createNodeJar(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z) {
/* 201 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 202 */     for (int xx = x - 2; xx <= x; xx++) {
/* 203 */       for (int yy = y - 3; yy <= y; yy++)
/* 204 */         for (int zz = z - 2; zz <= z; zz++)
/* 205 */           if ((fitNodeJar(world, xx, yy, zz)) && (wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.FIRE, 70).add(Aspect.EARTH, 70).add(Aspect.ORDER, 70).add(Aspect.AIR, 70).add(Aspect.ENTROPY, 70).add(Aspect.WATER, 70), true)))
/*     */           {
/*     */ 
/*     */ 
/* 209 */             if (!world.field_72995_K) {
/* 210 */               replaceNodeJar(world, xx, yy, zz);
/* 211 */               return true;
/*     */             }
/* 213 */             return false;
/*     */           }
/*     */     }
/* 216 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean createThaumatorium(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side) {
/* 220 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 221 */     if ((world.func_147439_a(x, y + 1, z) != ConfigBlocks.blockMetalDevice) || (world.func_72805_g(x, y + 1, z) != 9) || (world.func_147439_a(x, y - 1, z) != ConfigBlocks.blockMetalDevice) || (world.func_72805_g(x, y - 1, z) != 0))
/*     */     {
/*     */ 
/*     */ 
/* 225 */       if ((world.func_147439_a(x, y - 1, z) == ConfigBlocks.blockMetalDevice) && (world.func_72805_g(x, y - 1, z) == 9) && (world.func_147439_a(x, y - 2, z) == ConfigBlocks.blockMetalDevice) && (world.func_72805_g(x, y - 2, z) == 0))
/*     */       {
/* 227 */         y -= 1;
/*     */       } else {
/* 229 */         return false;
/*     */       }
/*     */     }
/* 232 */     if (wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.FIRE, 15).add(Aspect.ORDER, 30).add(Aspect.WATER, 30), true))
/*     */     {
/* 234 */       if (!world.field_72995_K) {
/* 235 */         world.func_147465_d(x, y, z, ConfigBlocks.blockMetalDevice, 10, 0);
/* 236 */         world.func_147465_d(x, y + 1, z, ConfigBlocks.blockMetalDevice, 11, 0);
/*     */         
/* 238 */         TileEntity tile = world.func_147438_o(x, y, z);
/* 239 */         if ((tile != null) && ((tile instanceof TileThaumatorium))) {
/* 240 */           ((TileThaumatorium)tile).facing = net.minecraftforge.common.util.ForgeDirection.getOrientation(side);
/*     */         }
/*     */         
/* 243 */         world.func_147471_g(x, y, z);
/* 244 */         world.func_147471_g(x, y + 1, z);
/* 245 */         world.func_147444_c(x, y, z, ConfigBlocks.blockMetalDevice);
/* 246 */         world.func_147444_c(x, y + 1, z, ConfigBlocks.blockMetalDevice);
/*     */         
/* 248 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(x, y, z, 55537), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, y, z, 32.0D));
/*     */         
/*     */ 
/* 251 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(x, y + 1, z, 55537), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, y, z, 32.0D));
/*     */         
/*     */ 
/* 254 */         world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/* 255 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 259 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   static boolean containsMatch(boolean strict, List<ItemStack> inputs, ItemStack... targets)
/*     */   {
/* 265 */     for (ItemStack input : inputs)
/*     */     {
/* 267 */       for (ItemStack target : targets)
/*     */       {
/* 269 */         if (OreDictionary.itemMatches(input, target, strict))
/*     */         {
/* 271 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 275 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean fitNodeJar(World world, int x, int y, int z)
/*     */   {
/* 280 */     int[][][] blueprint = { { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } }, { { 2, 2, 2 }, { 2, 3, 2 }, { 2, 2, 2 } }, { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } } };
/*     */     
/*     */ 
/*     */ 
/* 284 */     for (int yy = 0; yy < 4; yy++)
/* 285 */       for (int xx = 0; xx < 3; xx++)
/* 286 */         for (int zz = 0; zz < 3; zz++) {
/* 287 */           Block block = world.func_147439_a(x + xx, y - yy + 2, z + zz);
/* 288 */           int md = world.func_72805_g(x + xx, y - yy + 2, z + zz);
/*     */           
/* 290 */           if (blueprint[yy][xx][zz] == 1) {
/* 291 */             if (!containsMatch(false, OreDictionary.getOres("slabWood"), new ItemStack[] { new ItemStack(block, 1, md) })) {
/* 292 */               return false;
/*     */             }
/*     */           }
/*     */           
/* 296 */           if ((blueprint[yy][xx][zz] == 2) && (block != Blocks.field_150359_w)) {
/* 297 */             return false;
/*     */           }
/*     */           
/* 300 */           if (blueprint[yy][xx][zz] == 3) {
/* 301 */             TileEntity tile = world.func_147438_o(x + xx, y - yy + 2, z + zz);
/* 302 */             if ((tile == null) || (!(tile instanceof INode)) || ((tile instanceof TileJarNode)))
/* 303 */               return false;
/*     */           }
/*     */         }
/* 306 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void replaceNodeJar(World world, int x, int y, int z)
/*     */   {
/* 312 */     if (world.field_72995_K) return;
/* 313 */     int[][][] blueprint = { { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } }, { { 2, 2, 2 }, { 2, 3, 2 }, { 2, 2, 2 } }, { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } } };
/*     */     
/*     */ 
/*     */ 
/* 317 */     for (int yy = 0; yy < 4; yy++) {
/* 318 */       for (int xx = 0; xx < 3; xx++)
/* 319 */         for (int zz = 0; zz < 3; zz++)
/* 320 */           if (blueprint[yy][xx][zz] == 3) {
/* 321 */             TileEntity tile = world.func_147438_o(x + xx, y - yy + 2, z + zz);
/* 322 */             INode node = (INode)tile;
/* 323 */             AspectList na = node.getAspects().copy();
/* 324 */             int nt = node.getNodeType().ordinal();
/* 325 */             int nm = -1;
/* 326 */             if (node.getNodeModifier() != null) {
/* 327 */               nm = node.getNodeModifier().ordinal();
/*     */             }
/* 329 */             if (world.field_73012_v.nextFloat() < 0.75F) {
/* 330 */               if (node.getNodeModifier() == null) {
/* 331 */                 nm = NodeModifier.PALE.ordinal();
/*     */               }
/* 333 */               else if (node.getNodeModifier() == NodeModifier.BRIGHT) {
/* 334 */                 nm = -1;
/*     */               }
/* 336 */               else if (node.getNodeModifier() == NodeModifier.PALE) {
/* 337 */                 nm = NodeModifier.FADING.ordinal();
/*     */               }
/*     */             }
/*     */             
/* 341 */             String nid = node.getId();
/* 342 */             node.setAspects(new AspectList());
/* 343 */             world.func_147475_p(x + xx, y - yy + 2, z + zz);
/* 344 */             world.func_147465_d(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockJar, 2, 3);
/* 345 */             tile = world.func_147438_o(x + xx, y - yy + 2, z + zz);
/* 346 */             TileJarNode jar = (TileJarNode)tile;
/* 347 */             jar.setAspects(na);
/* 348 */             if (nm >= 0) jar.setNodeModifier(NodeModifier.values()[nm]);
/* 349 */             jar.setNodeType(NodeType.values()[nt]);
/* 350 */             jar.setId(nid);
/* 351 */             world.func_147452_c(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockJar, 9, 0);
/*     */           } else {
/* 353 */             world.func_147468_f(x + xx, y - yy + 2, z + zz);
/*     */           }
/*     */     }
/* 356 */     world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static boolean createArcaneFurnace(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z) {
/* 360 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 361 */     for (int xx = x - 2; xx <= x; xx++) {
/* 362 */       for (int yy = y - 2; yy <= y; yy++)
/* 363 */         for (int zz = z - 2; zz <= z; zz++)
/* 364 */           if ((fitArcaneFurnace(world, xx, yy, zz)) && (wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.FIRE, 50).add(Aspect.EARTH, 50), true)))
/*     */           {
/*     */ 
/*     */ 
/* 368 */             if (!world.field_72995_K) {
/* 369 */               replaceArcaneFurnace(world, xx, yy, zz);
/* 370 */               return true;
/*     */             }
/* 372 */             return false;
/*     */           }
/*     */     }
/* 375 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean fitArcaneFurnace(World world, int x, int y, int z) {
/* 379 */     Block bo = Blocks.field_150343_Z;
/* 380 */     Block bn = Blocks.field_150385_bj;
/* 381 */     Block bf = Blocks.field_150411_aY;
/* 382 */     Block bl = Blocks.field_150353_l;
/*     */     
/* 384 */     Block[][][] blueprint = { { { bn, bo, bn }, { bo, Blocks.field_150350_a, bo }, { bn, bo, bn } }, { { bn, bo, bn }, { bo, bl, bo }, { bn, bo, bn } }, { { bn, bo, bn }, { bo, bo, bo }, { bn, bo, bn } } };
/*     */     
/*     */ 
/* 387 */     boolean fencefound = false;
/* 388 */     for (int yy = 0; yy < 3; yy++) {
/* 389 */       for (int xx = 0; xx < 3; xx++)
/* 390 */         for (int zz = 0; zz < 3; zz++) {
/* 391 */           Block block = world.func_147439_a(x + xx, y - yy + 2, z + zz);
/* 392 */           if (world.func_147437_c(x + xx, y - yy + 2, z + zz)) block = Blocks.field_150350_a;
/* 393 */           if (block != blueprint[yy][xx][zz])
/* 394 */             if ((yy == 1) && (!fencefound) && (block == bf) && (xx != zz) && ((xx == 1) || (zz == 1)))
/* 395 */               fencefound = true; else
/* 396 */               return false;
/*     */         }
/*     */     }
/* 399 */     return fencefound;
/*     */   }
/*     */   
/*     */   public static boolean replaceArcaneFurnace(World world, int x, int y, int z)
/*     */   {
/* 404 */     boolean fencefound = false;
/* 405 */     for (int yy = 0; yy < 3; yy++) {
/* 406 */       int step = 1;
/* 407 */       for (int zz = 0; zz < 3; zz++) {
/* 408 */         for (int xx = 0; xx < 3; xx++)
/*     */         {
/* 410 */           int md = step;
/* 411 */           if ((world.func_147439_a(x + xx, y + yy, z + zz) == Blocks.field_150353_l) || (world.func_147439_a(x + xx, y + yy, z + zz) == Blocks.field_150356_k))
/* 412 */             md = 0;
/* 413 */           if (world.func_147439_a(x + xx, y + yy, z + zz) == Blocks.field_150411_aY) md = 10;
/* 414 */           if (!world.func_147437_c(x + xx, y + yy, z + zz))
/*     */           {
/* 416 */             world.func_147465_d(x + xx, y + yy, z + zz, ConfigBlocks.blockArcaneFurnace, md, 0);
/* 417 */             world.func_147452_c(x + xx, y + yy, z + zz, ConfigBlocks.blockArcaneFurnace, 1, 4);
/*     */           }
/*     */           
/* 420 */           step++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 425 */     for (int yy = 0; yy < 3; yy++) {
/* 426 */       for (int zz = 0; zz < 3; zz++) {
/* 427 */         for (int xx = 0; xx < 3; xx++)
/*     */         {
/* 429 */           world.func_147471_g(x + xx, y + yy, z + zz); }
/*     */       }
/*     */     }
/* 432 */     world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/* 433 */     return fencefound;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean createThaumonomicon(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z)
/*     */   {
/* 439 */     if (!world.field_72995_K) {
/* 440 */       ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 441 */       if (wand.getFocus(itemstack) != null) return false;
/* 442 */       world.func_147468_f(x, y, z);
/* 443 */       EntitySpecialItem entityItem = new EntitySpecialItem(world, x + 0.5F, y + 0.3F, z + 0.5F, new ItemStack(thaumcraft.common.config.ConfigItems.itemThaumonomicon));
/*     */       
/*     */ 
/* 446 */       entityItem.field_70181_x = 0.0D;
/* 447 */       entityItem.field_70159_w = 0.0D;
/* 448 */       entityItem.field_70179_y = 0.0D;
/* 449 */       world.func_72838_d(entityItem);
/* 450 */       PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(x, y, z, 55537), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, y, z, 32.0D));
/*     */       
/*     */ 
/* 453 */       world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/* 454 */       return true;
/*     */     }
/*     */     
/* 457 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean createOculus(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side)
/*     */   {
/* 462 */     if (!world.field_72995_K) {
/* 463 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 464 */       TileEntity node = world.func_147438_o(x, y + 1, z);
/* 465 */       if ((tile != null) && (node != null) && ((tile instanceof TileEldritchAltar)) && (((TileEldritchAltar)tile).getEyes() == 4) && (!((TileEldritchAltar)tile).isOpen()) && ((node instanceof TileNode)) && (((TileNode)node).getNodeType() == NodeType.DARK) && (((TileEldritchAltar)tile).checkForMaze()))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 471 */         ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 472 */         if (wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.AIR, 100).add(Aspect.FIRE, 100).add(Aspect.EARTH, 100).add(Aspect.WATER, 100).add(Aspect.ORDER, 100).add(Aspect.ENTROPY, 100), true))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 478 */           world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/*     */           
/* 480 */           ((TileEldritchAltar)tile).setOpen(true);
/*     */           
/* 482 */           world.func_147468_f(x, y + 1, z);
/* 483 */           world.func_147449_b(x, y + 1, z, ConfigBlocks.blockEldritchPortal);
/* 484 */           tile.func_70296_d();
/* 485 */           world.func_147471_g(x, y, z);
/*     */         }
/*     */       }
/*     */     }
/* 489 */     return false;
/*     */   }
/*     */   
/*     */   public static void changeFocus(ItemStack is, World w, EntityPlayer player, String focus) {
/* 493 */     ItemWandCasting wand = (ItemWandCasting)is.func_77973_b();
/* 494 */     TreeMap<String, Integer> foci = new TreeMap();
/* 495 */     HashMap<Integer, Integer> pouches = new HashMap();
/* 496 */     int pouchcount = 0;
/* 497 */     ItemStack item = null;
/*     */     
/*     */ 
/* 500 */     IInventory baubles = BaublesApi.getBaubles(player);
/* 501 */     for (int a = 0; a < 4; a++) {
/* 502 */       if ((baubles.func_70301_a(a) != null) && ((baubles.func_70301_a(a).func_77973_b() instanceof ItemFocusPouch))) {
/* 503 */         pouchcount++;
/* 504 */         item = baubles.func_70301_a(a);
/* 505 */         pouches.put(Integer.valueOf(pouchcount), Integer.valueOf(a - 4));
/* 506 */         ItemStack[] inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
/* 507 */         for (int q = 0; q < inv.length; q++) {
/* 508 */           item = inv[q];
/* 509 */           if ((item != null) && ((item.func_77973_b() instanceof ItemFocusBasic))) {
/* 510 */             foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Integer.valueOf(q + pouchcount * 1000));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 517 */     for (int a = 0; a < 36; a++) {
/* 518 */       item = player.field_71071_by.field_70462_a[a];
/* 519 */       if ((item != null) && ((item.func_77973_b() instanceof ItemFocusBasic))) {
/* 520 */         foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Integer.valueOf(a));
/*     */       }
/* 522 */       if ((item != null) && ((item.func_77973_b() instanceof ItemFocusPouch))) {
/* 523 */         pouchcount++;
/* 524 */         pouches.put(Integer.valueOf(pouchcount), Integer.valueOf(a));
/* 525 */         ItemStack[] inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
/* 526 */         for (int q = 0; q < inv.length; q++) {
/* 527 */           item = inv[q];
/* 528 */           if ((item != null) && ((item.func_77973_b() instanceof ItemFocusBasic))) {
/* 529 */             foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Integer.valueOf(q + pouchcount * 1000));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 535 */     if ((focus.equals("REMOVE")) || (foci.size() == 0)) {
/* 536 */       if ((wand.getFocus(is) != null) && (
/* 537 */         (addFocusToPouch(player, wand.getFocusItem(is).func_77946_l(), pouches)) || (player.field_71071_by.func_70441_a(wand.getFocusItem(is).func_77946_l()))))
/*     */       {
/* 539 */         wand.setFocus(is, null);
/* 540 */         w.func_72956_a(player, "thaumcraft:cameraticks", 0.3F, 0.9F);
/*     */       }
/*     */       
/* 543 */       return; }
/* 544 */     if ((foci != null) && (foci.size() > 0) && (focus != null))
/*     */     {
/*     */ 
/* 547 */       String newkey = focus;
/* 548 */       if (foci.get(newkey) == null) newkey = (String)foci.higherKey(newkey);
/* 549 */       if ((newkey == null) || (foci.get(newkey) == null)) { newkey = (String)foci.firstKey();
/*     */       }
/* 551 */       if ((((Integer)foci.get(newkey)).intValue() < 1000) && (((Integer)foci.get(newkey)).intValue() >= 0)) {
/* 552 */         item = player.field_71071_by.field_70462_a[((Integer)foci.get(newkey)).intValue()].func_77946_l();
/*     */       } else {
/* 554 */         int pid = ((Integer)foci.get(newkey)).intValue() / 1000;
/* 555 */         if (pouches.containsKey(Integer.valueOf(pid))) {
/* 556 */           int pouchslot = ((Integer)pouches.get(Integer.valueOf(pid))).intValue();
/* 557 */           int focusslot = ((Integer)foci.get(newkey)).intValue() - pid * 1000;
/* 558 */           ItemStack tmp = null;
/* 559 */           if (pouchslot >= 0) {
/* 560 */             tmp = player.field_71071_by.field_70462_a[pouchslot].func_77946_l();
/*     */           } else {
/* 562 */             tmp = baubles.func_70301_a(pouchslot + 4).func_77946_l();
/*     */           }
/* 564 */           item = fetchFocusFromPouch(player, focusslot, tmp, pouchslot);
/*     */         }
/*     */       }
/*     */       
/* 568 */       if (item != null) {
/* 569 */         if ((((Integer)foci.get(newkey)).intValue() < 1000) && (((Integer)foci.get(newkey)).intValue() >= 0)) {
/* 570 */           player.field_71071_by.func_70299_a(((Integer)foci.get(newkey)).intValue(), null);
/*     */         }
/*     */         
/*     */       }
/*     */       else {
/* 575 */         return;
/*     */       }
/*     */       
/* 578 */       w.func_72956_a(player, "thaumcraft:cameraticks", 0.3F, 1.0F);
/*     */       
/*     */ 
/* 581 */       if ((wand.getFocus(is) != null) && (
/* 582 */         (addFocusToPouch(player, wand.getFocusItem(is).func_77946_l(), pouches)) || (player.field_71071_by.func_70441_a(wand.getFocusItem(is).func_77946_l()))))
/*     */       {
/* 584 */         wand.setFocus(is, null);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 589 */       if (wand.getFocus(is) == null) {
/* 590 */         wand.setFocus(is, item);
/*     */       }
/* 592 */       else if (!addFocusToPouch(player, item, pouches)) {
/* 593 */         player.field_71071_by.func_70441_a(item);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static ItemStack fetchFocusFromPouch(EntityPlayer player, int focusid, ItemStack pouch, int pouchslot)
/*     */   {
/* 600 */     ItemStack focus = null;
/* 601 */     ItemStack[] inv = ((ItemFocusPouch)pouch.func_77973_b()).getInventory(pouch);
/* 602 */     ItemStack contents = inv[focusid];
/* 603 */     if ((contents != null) && ((contents.func_77973_b() instanceof ItemFocusBasic))) {
/* 604 */       focus = contents.func_77946_l();
/* 605 */       inv[focusid] = null;
/* 606 */       ((ItemFocusPouch)pouch.func_77973_b()).setInventory(pouch, inv);
/* 607 */       if (pouchslot >= 0) {
/* 608 */         player.field_71071_by.func_70299_a(pouchslot, pouch);
/* 609 */         player.field_71071_by.func_70296_d();
/*     */       } else {
/* 611 */         IInventory baubles = BaublesApi.getBaubles(player);
/* 612 */         baubles.func_70299_a(pouchslot + 4, pouch);
/* 613 */         baubles.func_70296_d();
/*     */       }
/*     */     }
/* 616 */     return focus;
/*     */   }
/*     */   
/*     */   private static boolean addFocusToPouch(EntityPlayer player, ItemStack focus, HashMap<Integer, Integer> pouches)
/*     */   {
/* 621 */     IInventory baubles = BaublesApi.getBaubles(player);
/* 622 */     for (Integer pouchslot : pouches.values()) { ItemStack pouch;
/* 623 */       ItemStack pouch; if (pouchslot.intValue() >= 0) {
/* 624 */         pouch = player.field_71071_by.field_70462_a[pouchslot.intValue()];
/*     */       } else {
/* 626 */         pouch = baubles.func_70301_a(pouchslot.intValue() + 4);
/*     */       }
/* 628 */       ItemStack[] inv = ((ItemFocusPouch)pouch.func_77973_b()).getInventory(pouch);
/*     */       
/* 630 */       for (int q = 0; q < inv.length; q++) {
/* 631 */         ItemStack contents = inv[q];
/* 632 */         if (contents == null) {
/* 633 */           inv[q] = focus.func_77946_l();
/* 634 */           ((ItemFocusPouch)pouch.func_77973_b()).setInventory(pouch, inv);
/* 635 */           if (pouchslot.intValue() >= 0) {
/* 636 */             player.field_71071_by.func_70299_a(pouchslot.intValue(), pouch);
/* 637 */             player.field_71071_by.func_70296_d();
/*     */           } else {
/* 639 */             baubles.func_70299_a(pouchslot.intValue() + 4, pouch);
/* 640 */             baubles.func_70296_d();
/*     */           }
/* 642 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 646 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void toggleMisc(ItemStack itemstack, World world, EntityPlayer player)
/*     */   {
/* 652 */     if (!(itemstack.func_77973_b() instanceof ItemWandCasting)) return;
/* 653 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 654 */     if ((wand.getFocus(itemstack) != null) && ((wand.getFocus(itemstack) instanceof IArchitect)) && (wand.getFocus(itemstack).isUpgradedWith(wand.getFocusItem(itemstack), thaumcraft.api.wands.FocusUpgradeType.architect)))
/*     */     {
/* 656 */       int dim = getAreaDim(itemstack);
/* 657 */       IArchitect fa = (IArchitect)wand.getFocus(itemstack);
/* 658 */       if (player.func_70093_af()) {
/* 659 */         dim++;
/* 660 */         if (dim > ((wand.getFocusItem(itemstack).func_77973_b() instanceof ItemFocusTrade) ? 2 : 3)) dim = 0;
/* 661 */         setAreaDim(itemstack, dim);
/*     */       } else {
/* 663 */         int areax = getAreaX(itemstack);
/* 664 */         int areay = getAreaY(itemstack);
/* 665 */         int areaz = getAreaZ(itemstack);
/* 666 */         if (dim == 0) {
/* 667 */           areax++;
/* 668 */           areaz++;
/* 669 */           areay++;
/* 670 */         } else if (dim == 1) {
/* 671 */           areax++;
/* 672 */         } else if (dim == 2) {
/* 673 */           areaz++;
/* 674 */         } else if (dim == 3) {
/* 675 */           areay++;
/*     */         }
/* 677 */         if (areax > wand.getFocus(itemstack).getMaxAreaSize(wand.getFocusItem(itemstack))) {
/* 678 */           areax = 0;
/*     */         }
/* 680 */         if (areaz > wand.getFocus(itemstack).getMaxAreaSize(wand.getFocusItem(itemstack))) {
/* 681 */           areaz = 0;
/*     */         }
/* 683 */         if (areay > wand.getFocus(itemstack).getMaxAreaSize(wand.getFocusItem(itemstack))) {
/* 684 */           areay = 0;
/*     */         }
/* 686 */         setAreaX(itemstack, areax);
/* 687 */         setAreaY(itemstack, areay);
/* 688 */         setAreaZ(itemstack, areaz);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getAreaDim(ItemStack stack)
/*     */   {
/* 695 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("aread"))) {
/* 696 */       return stack.field_77990_d.func_74762_e("aread");
/*     */     }
/* 698 */     return 0;
/*     */   }
/*     */   
/*     */   public static int getAreaX(ItemStack stack) {
/* 702 */     ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
/* 703 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("areax"))) {
/* 704 */       int a = stack.field_77990_d.func_74762_e("areax");
/* 705 */       if (a > wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack))) {
/* 706 */         a = wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
/*     */       }
/* 708 */       return a;
/*     */     }
/* 710 */     return wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
/*     */   }
/*     */   
/*     */   public static int getAreaY(ItemStack stack) {
/* 714 */     ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
/* 715 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("areay"))) {
/* 716 */       int a = stack.field_77990_d.func_74762_e("areay");
/* 717 */       if (a > wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack))) {
/* 718 */         a = wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
/*     */       }
/* 720 */       return a;
/*     */     }
/* 722 */     return wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
/*     */   }
/*     */   
/*     */   public static int getAreaZ(ItemStack stack) {
/* 726 */     ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
/* 727 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("areaz"))) {
/* 728 */       int a = stack.field_77990_d.func_74762_e("areaz");
/* 729 */       if (a > wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack))) {
/* 730 */         a = wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
/*     */       }
/* 732 */       return a;
/*     */     }
/* 734 */     return wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
/*     */   }
/*     */   
/*     */   public static void setAreaX(ItemStack stack, int area) {
/* 738 */     if (stack.func_77942_o()) {
/* 739 */       stack.field_77990_d.func_74768_a("areax", area);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setAreaY(ItemStack stack, int area) {
/* 744 */     if (stack.func_77942_o()) {
/* 745 */       stack.field_77990_d.func_74768_a("areay", area);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setAreaZ(ItemStack stack, int area) {
/* 750 */     if (stack.func_77942_o()) {
/* 751 */       stack.field_77990_d.func_74768_a("areaz", area);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setAreaDim(ItemStack stack, int dim) {
/* 756 */     if (stack.func_77942_o()) {
/* 757 */       stack.field_77990_d.func_74768_a("aread", dim);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 762 */   static HashMap<Integer, Long> cooldownServer = new HashMap();
/* 763 */   static HashMap<Integer, Long> cooldownClient = new HashMap();
/*     */   
/*     */   static boolean isOnCooldown(EntityLivingBase entityLiving) {
/* 766 */     if ((entityLiving.field_70170_p.field_72995_K) && (cooldownClient.containsKey(Integer.valueOf(entityLiving.func_145782_y())))) {
/* 767 */       return ((Long)cooldownClient.get(Integer.valueOf(entityLiving.func_145782_y()))).longValue() > System.currentTimeMillis();
/*     */     }
/* 769 */     if ((!entityLiving.field_70170_p.field_72995_K) && (cooldownServer.containsKey(Integer.valueOf(entityLiving.func_145782_y())))) {
/* 770 */       return ((Long)cooldownServer.get(Integer.valueOf(entityLiving.func_145782_y()))).longValue() > System.currentTimeMillis();
/*     */     }
/* 772 */     return false;
/*     */   }
/*     */   
/*     */   public static float getCooldown(EntityLivingBase entityLiving) {
/* 776 */     if ((entityLiving.field_70170_p.field_72995_K) && (cooldownClient.containsKey(Integer.valueOf(entityLiving.func_145782_y())))) {
/* 777 */       return (float)(((Long)cooldownClient.get(Integer.valueOf(entityLiving.func_145782_y()))).longValue() - System.currentTimeMillis()) / 1000.0F;
/*     */     }
/* 779 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public static void setCooldown(EntityLivingBase entityLiving, int cd) {
/* 783 */     if (cd == 0) {
/* 784 */       cooldownClient.remove(Integer.valueOf(entityLiving.func_145782_y()));
/* 785 */       cooldownServer.remove(Integer.valueOf(entityLiving.func_145782_y()));
/*     */     }
/* 787 */     else if (entityLiving.field_70170_p.field_72995_K) {
/* 788 */       cooldownClient.put(Integer.valueOf(entityLiving.func_145782_y()), Long.valueOf(System.currentTimeMillis() + cd));
/*     */     } else {
/* 790 */       cooldownServer.put(Integer.valueOf(entityLiving.func_145782_y()), Long.valueOf(System.currentTimeMillis() + cd));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, int event)
/*     */   {
/* 797 */     switch (event) {
/* 798 */     case 0:  return createThaumonomicon(wand, player, world, x, y, z);
/* 799 */     case 1:  return createCrucible(wand, player, world, x, y, z);
/* 800 */     case 2:  if (ResearchManager.isResearchComplete(player.func_70005_c_(), "INFERNALFURNACE"))
/* 801 */         return createArcaneFurnace(wand, player, world, x, y, z);
/*     */       break;
/* 803 */     case 3:  if (ResearchManager.isResearchComplete(player.func_70005_c_(), "INFUSION"))
/* 804 */         return createInfusionAltar(wand, player, world, x, y, z);
/*     */       break;
/* 806 */     case 4:  if (ResearchManager.isResearchComplete(player.func_70005_c_(), "NODEJAR"))
/* 807 */         return createNodeJar(wand, player, world, x, y, z);
/*     */       break;
/* 809 */     case 5:  if (ResearchManager.isResearchComplete(player.func_70005_c_(), "THAUMATORIUM"))
/* 810 */         return createThaumatorium(wand, player, world, x, y, z, side);
/*     */       break;
/* 812 */     case 6:  if (ResearchManager.isResearchComplete(player.func_70005_c_(), "OCULUS"))
/* 813 */         return createOculus(wand, player, world, x, y, z, side);
/*     */       break;
/* 815 */     case 7:  if (ResearchManager.isResearchComplete(player.func_70005_c_(), "ADVALCHEMYFURNACE")) {
/* 816 */         return createAdvancedAlchemicalFurnace(wand, player, world, x, y, z, side);
/*     */       }
/*     */       break;
/*     */     }
/*     */     
/* 821 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   private static boolean createAdvancedAlchemicalFurnace(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side)
/*     */   {
/* 827 */     if (world.field_72995_K) return false;
/* 828 */     int[][][] blueprint = { { { 4, 4, 4 }, { 4, 3, 4 }, { 4, 4, 4 } }, { { 1, 2, 1 }, { 2, 0, 2 }, { 1, 2, 1 } } };
/*     */     
/* 830 */     for (int a = -1; a <= 1; a++) {
/* 831 */       for (int b = -1; b <= 1; b++) {
/* 832 */         for (int c = -1; c <= 1; c++)
/* 833 */           if ((world.func_147439_a(x + a, y + b, z + c) == ConfigBlocks.blockStoneDevice) && (world.func_72805_g(x + a, y + b, z + c) == 0))
/*     */           {
/*     */ 
/* 836 */             for (int aa = -1; aa <= 1; aa++) {
/* 837 */               for (int bb = 0; bb <= 1; bb++) {
/* 838 */                 for (int cc = -1; cc <= 1; cc++) {
/* 839 */                   if ((blueprint[bb][(aa + 1)][(cc + 1)] == 1) && ((world.func_147439_a(x + a + aa, y + b + bb, z + c + cc) != ConfigBlocks.blockMetalDevice) || (world.func_72805_g(x + a + aa, y + b + bb, z + c + cc) != 1)))
/*     */                   {
/*     */ 
/* 842 */                     return false;
/*     */                   }
/* 844 */                   if ((blueprint[bb][(aa + 1)][(cc + 1)] == 2) && ((world.func_147439_a(x + a + aa, y + b + bb, z + c + cc) != ConfigBlocks.blockMetalDevice) || (world.func_72805_g(x + a + aa, y + b + bb, z + c + cc) != 9)))
/*     */                   {
/*     */ 
/* 847 */                     return false;
/*     */                   }
/* 849 */                   if ((blueprint[bb][(aa + 1)][(cc + 1)] == 4) && ((world.func_147439_a(x + a + aa, y + b + bb, z + c + cc) != ConfigBlocks.blockMetalDevice) || (world.func_72805_g(x + a + aa, y + b + bb, z + c + cc) != 3)))
/*     */                   {
/*     */ 
/* 852 */                     return false;
/*     */                   }
/* 854 */                   if ((blueprint[bb][(aa + 1)][(cc + 1)] == 3) && ((world.func_147439_a(x + a + aa, y + b + bb, z + c + cc) != ConfigBlocks.blockStoneDevice) || (world.func_72805_g(x + a + aa, y + b + bb, z + c + cc) != 0)))
/*     */                   {
/*     */ 
/* 857 */                     return false; }
/*     */                 }
/*     */               }
/*     */             }
/* 861 */             ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 862 */             if (!wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.FIRE, 50).add(Aspect.WATER, 50).add(Aspect.ORDER, 50), true))
/*     */             {
/* 864 */               return false;
/*     */             }
/* 866 */             world.func_147449_b(x + a, y + b, z + c, ConfigBlocks.blockAlchemyFurnace);
/* 867 */             world.func_147465_d(x + a - 1, y + b, z + c, ConfigBlocks.blockAlchemyFurnace, 1, 3);
/* 868 */             world.func_147465_d(x + a + 1, y + b, z + c, ConfigBlocks.blockAlchemyFurnace, 1, 3);
/* 869 */             world.func_147465_d(x + a, y + b, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 1, 3);
/* 870 */             world.func_147465_d(x + a, y + b, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 1, 3);
/* 871 */             world.func_147465_d(x + a - 1, y + b, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 4, 3);
/* 872 */             world.func_147465_d(x + a + 1, y + b, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 4, 3);
/* 873 */             world.func_147465_d(x + a + 1, y + b, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 4, 3);
/* 874 */             world.func_147465_d(x + a - 1, y + b, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 4, 3);
/*     */             
/* 876 */             world.func_147465_d(x + a - 1, y + b + 1, z + c, ConfigBlocks.blockAlchemyFurnace, 3, 3);
/* 877 */             world.func_147465_d(x + a + 1, y + b + 1, z + c, ConfigBlocks.blockAlchemyFurnace, 3, 3);
/* 878 */             world.func_147465_d(x + a, y + b + 1, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 3, 3);
/* 879 */             world.func_147465_d(x + a, y + b + 1, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 3, 3);
/* 880 */             world.func_147465_d(x + a - 1, y + b + 1, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 2, 3);
/* 881 */             world.func_147465_d(x + a + 1, y + b + 1, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 2, 3);
/* 882 */             world.func_147465_d(x + a + 1, y + b + 1, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 2, 3);
/* 883 */             world.func_147465_d(x + a - 1, y + b + 1, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 2, 3);
/*     */             
/* 885 */             world.func_72908_a(x + a + 0.5D, y + b + 0.5D, z + c + 0.5D, "thaumcraft:wand", 1.0F, 1.0F);
/* 886 */             for (int aa = -1; aa <= 1; aa++) {
/* 887 */               for (int bb = 0; bb <= 1; bb++) {
/* 888 */                 for (int cc = -1; cc <= 1; cc++) {
/* 889 */                   PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(x + a + aa, y + b + bb, z + c + cc, 55537), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x + a, y + b, z + c, 32.0D));
/*     */                 }
/*     */               }
/*     */             }
/* 893 */             return true;
/*     */           }
/*     */       }
/*     */     }
/* 897 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/WandManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */