/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.EntityFollowingItem;
/*     */ 
/*     */ public class BlockUtils
/*     */ {
/*     */   public static boolean harvestBlock(World world, EntityPlayer player, int x, int y, int z)
/*     */   {
/*  34 */     return harvestBlock(world, player, x, y, z, false, 0);
/*     */   }
/*     */   
/*     */   public static boolean harvestBlock(World world, EntityPlayer player, int x, int y, int z, boolean followItem, int color) {
/*  38 */     Block block = world.func_147439_a(x, y, z);
/*  39 */     int i1 = world.func_72805_g(x, y, z);
/*     */     
/*  41 */     if (block.func_149712_f(world, x, y, z) < 0.0F) {
/*  42 */       return false;
/*     */     }
/*     */     
/*  45 */     world.func_72926_e(2001, x, y, z, Block.func_149682_b(block) + (i1 << 12));
/*  46 */     boolean flag = false;
/*     */     
/*  48 */     if (player.field_71075_bZ.field_75098_d) {
/*  49 */       flag = removeBlock(world, x, y, z, player);
/*     */     } else {
/*  51 */       boolean flag1 = false;
/*  52 */       if (block != null) {
/*  53 */         flag1 = block.canHarvestBlock(player, i1);
/*     */       }
/*     */       
/*  56 */       flag = removeBlock(world, x, y, z, player);
/*  57 */       if ((flag) && (flag1)) {
/*  58 */         block.func_149636_a(world, player, x, y, z, i1);
/*     */         
/*  60 */         if (followItem) {
/*  61 */           ArrayList<Entity> entities = EntityUtils.getEntitiesInRange(world, x + 0.5D, y + 0.5D, z + 0.5D, player, EntityItem.class, 2.0D);
/*  62 */           if ((entities != null) && (entities.size() > 0)) {
/*  63 */             for (Entity e : entities)
/*  64 */               if ((!e.field_70128_L) && ((e instanceof EntityItem)) && (e.field_70173_aa == 0) && (!(e instanceof EntityFollowingItem)))
/*     */               {
/*  66 */                 EntityFollowingItem fi = new EntityFollowingItem(world, e.field_70165_t, e.field_70163_u, e.field_70161_v, ((EntityItem)e).func_92059_d().func_77946_l(), player, color);
/*     */                 
/*  68 */                 fi.field_70159_w = e.field_70159_w;
/*  69 */                 fi.field_70181_x = e.field_70181_x;
/*  70 */                 fi.field_70179_y = e.field_70179_y;
/*  71 */                 world.func_72838_d(fi);
/*  72 */                 e.func_70106_y();
/*     */               }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  78 */     return true;
/*     */   }
/*     */   
/*  81 */   static HashMap<Integer, ArrayList[]> blockEventCache = new HashMap();
/*     */   
/*     */   public static ArrayList[] getBlockEventList(WorldServer world) {
/*  84 */     if (!blockEventCache.containsKey(Integer.valueOf(world.field_73011_w.field_76574_g))) {
/*     */       try {
/*  86 */         blockEventCache.put(Integer.valueOf(world.field_73011_w.field_76574_g), (ArrayList[])ReflectionHelper.getPrivateValue(WorldServer.class, world, new String[] { "field_147490_S" }));
/*     */ 
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*     */ 
/*  92 */         return null;
/*     */       }
/*     */     }
/*     */     
/*  96 */     return (ArrayList[])blockEventCache.get(Integer.valueOf(world.field_73011_w.field_76574_g));
/*     */   }
/*     */   
/*     */   public static ItemStack createStackedBlock(Block block, int md) {
/* 100 */     ItemStack dropped = null;
/*     */     try {
/* 102 */       Method m = ReflectionHelper.findMethod(Block.class, block, new String[] { "createStackedBlock", "func_149644_j" }, new Class[] { Integer.TYPE });
/*     */       
/* 104 */       dropped = (ItemStack)m.invoke(block, new Object[] { Integer.valueOf(md) });
/*     */     } catch (Exception e) {
/* 106 */       Thaumcraft.log.warn("Could not invoke net.minecraft.block.Block method createStackedBlock");
/*     */     }
/* 108 */     return dropped;
/*     */   }
/*     */   
/*     */   public static void dropBlockAsItem(World world, int x, int y, int z, ItemStack stack, Block block)
/*     */   {
/*     */     try {
/* 114 */       Method m = ReflectionHelper.findMethod(Block.class, block, new String[] { "dropBlockAsItem", "func_149642_a" }, new Class[] { World.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, ItemStack.class });
/*     */       
/* 116 */       m.invoke(block, new Object[] { world, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z), stack });
/*     */     } catch (Exception e) {
/* 118 */       Thaumcraft.log.warn("Could not invoke net.minecraft.block.Block method createStackedBlock");
/*     */     }
/*     */   }
/*     */   
/*     */   public static void dropBlockAsItemWithChance(World world, Block block, int x, int y, int z, int meta, float dropchance, int fortune, EntityPlayer player)
/*     */   {
/* 124 */     if ((!world.field_72995_K) && (!world.restoringBlockSnapshots))
/*     */     {
/* 126 */       ArrayList<ItemStack> items = block.getDrops(world, x, y, z, meta, fortune);
/* 127 */       dropchance = net.minecraftforge.event.ForgeEventFactory.fireBlockHarvesting(items, world, block, x, y, z, meta, fortune, dropchance, false, player);
/*     */       
/* 129 */       for (ItemStack item : items)
/*     */       {
/* 131 */         if (world.field_73012_v.nextFloat() <= dropchance)
/*     */         {
/* 133 */           dropBlockAsItem(world, x, y, z, item, block);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void destroyBlockPartially(World world, int par1, int par2, int par3, int par4, int par5)
/*     */   {
/* 141 */     Iterator iterator = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b.iterator();
/*     */     
/*     */ 
/* 144 */     while (iterator.hasNext()) {
/* 145 */       EntityPlayerMP entityplayermp = (EntityPlayerMP)iterator.next();
/*     */       
/* 147 */       if ((entityplayermp != null) && (entityplayermp.field_70170_p == MinecraftServer.func_71276_C().func_130014_f_()) && (entityplayermp.func_145782_y() != par1))
/*     */       {
/*     */ 
/*     */ 
/* 151 */         double d0 = par2 - entityplayermp.field_70165_t;
/* 152 */         double d1 = par3 - entityplayermp.field_70163_u;
/* 153 */         double d2 = par4 - entityplayermp.field_70161_v;
/*     */         
/* 155 */         if (d0 * d0 + d1 * d1 + d2 * d2 < 1024.0D) {
/* 156 */           entityplayermp.field_71135_a.func_147359_a(new net.minecraft.network.play.server.S25PacketBlockBreakAnim(par1, par2, par3, par4, par5));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean removeBlock(World world, int par1, int par2, int par3, EntityPlayer player)
/*     */   {
/* 166 */     Block block = world.func_147439_a(par1, par2, par3);
/* 167 */     int l = world.func_72805_g(par1, par2, par3);
/*     */     
/* 169 */     if (block != null) {
/* 170 */       block.func_149681_a(world, par1, par2, par3, l, player);
/*     */     }
/*     */     
/* 173 */     boolean flag = (block != null) && (block.removedByPlayer(world, player, par1, par2, par3));
/*     */     
/*     */ 
/* 176 */     if ((block != null) && (flag)) {
/* 177 */       block.func_149664_b(world, par1, par2, par3, l);
/*     */     }
/*     */     
/* 180 */     return flag;
/*     */   }
/*     */   
/*     */   public static void findBlocks(World world, int x, int y, int z, Block block) {
/* 184 */     int count = 0;
/* 185 */     for (int xx = -2; xx <= 2; xx++)
/* 186 */       for (int yy = 2; yy >= -2; yy--)
/* 187 */         for (int zz = -2; zz <= 2; zz++) {
/* 188 */           if (Math.abs(lastx + xx - x) > 24)
/* 189 */             return;
/* 190 */           if (Math.abs(lasty + yy - y) > 48)
/* 191 */             return;
/* 192 */           if (Math.abs(lastz + zz - z) > 24)
/* 193 */             return;
/* 194 */           if ((world.func_147439_a(lastx + xx, lasty + yy, lastz + zz) == block) && (Utils.isWoodLog(world, lastx + xx, lasty + yy, lastz + zz)) && (block.func_149712_f(world, lastx + xx, lasty + yy, lastz + zz) >= 0.0F))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/* 199 */             double xd = lastx + xx - x;
/* 200 */             double yd = lasty + yy - y;
/* 201 */             double zd = lastz + zz - z;
/* 202 */             double d = xd * xd + yd * yd + zd * zd;
/* 203 */             if (d > lastdistance) {
/* 204 */               lastdistance = d;
/* 205 */               lastx += xx;
/* 206 */               lasty += yy;
/* 207 */               lastz += zz;
/* 208 */               findBlocks(world, x, y, z, block);
/* 209 */               return;
/*     */             }
/*     */           }
/*     */         }
/*     */   }
/*     */   
/* 215 */   static int lastx = 0;
/* 216 */   static int lasty = 0;
/* 217 */   static int lastz = 0;
/* 218 */   static double lastdistance = 0.0D;
/*     */   
/*     */   public static boolean breakFurthestBlock(World world, int x, int y, int z, Block block, EntityPlayer player)
/*     */   {
/* 222 */     return breakFurthestBlock(world, x, y, z, block, player, false, 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean breakFurthestBlock(World world, int x, int y, int z, Block block, EntityPlayer player, boolean followitem, int color)
/*     */   {
/* 228 */     lastx = x;
/* 229 */     lasty = y;
/* 230 */     lastz = z;
/* 231 */     lastdistance = 0.0D;
/*     */     
/* 233 */     findBlocks(world, x, y, z, block);
/*     */     
/* 235 */     boolean worked = harvestBlock(world, player, lastx, lasty, lastz, followitem, color);
/* 236 */     world.func_147471_g(x, y, z);
/* 237 */     if (worked) {
/* 238 */       world.func_147471_g(lastx, lasty, lastz);
/* 239 */       for (int xx = -3; xx <= 3; xx++) {
/* 240 */         for (int yy = -3; yy <= 3; yy++) {
/* 241 */           for (int zz = -3; zz <= 3; zz++) {
/* 242 */             world.func_147464_a(lastx + xx, lasty + yy, lastz + zz, world.func_147439_a(lastx + xx, lasty + yy, lastz + zz), 150 + world.field_73012_v.nextInt(150));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 251 */     return worked;
/*     */   }
/*     */   
/*     */ 
/*     */   public static MovingObjectPosition getTargetBlock(World world, double x, double y, double z, float yaw, float pitch, boolean par3, double range)
/*     */   {
/* 257 */     Vec3 var13 = Vec3.func_72443_a(x, y, z);
/* 258 */     float var14 = MathHelper.func_76134_b(-yaw * 0.017453292F - 3.1415927F);
/* 259 */     float var15 = MathHelper.func_76126_a(-yaw * 0.017453292F - 3.1415927F);
/* 260 */     float var16 = -MathHelper.func_76134_b(-pitch * 0.017453292F);
/* 261 */     float var17 = MathHelper.func_76126_a(-pitch * 0.017453292F);
/* 262 */     float var18 = var15 * var16;
/* 263 */     float var20 = var14 * var16;
/* 264 */     double var21 = range;
/* 265 */     Vec3 var23 = var13.func_72441_c(var18 * var21, var17 * var21, var20 * var21);
/*     */     
/* 267 */     return world.func_147447_a(var13, var23, par3, !par3, false);
/*     */   }
/*     */   
/*     */   public static MovingObjectPosition getTargetBlock(World world, Entity entity, boolean par3)
/*     */   {
/* 272 */     float var4 = 1.0F;
/* 273 */     float var5 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * var4;
/*     */     
/* 275 */     float var6 = entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * var4;
/*     */     
/* 277 */     double var7 = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * var4;
/*     */     
/* 279 */     double var9 = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * var4 + 1.62D - entity.field_70129_M;
/*     */     
/* 281 */     double var11 = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * var4;
/*     */     
/* 283 */     Vec3 var13 = Vec3.func_72443_a(var7, var9, var11);
/* 284 */     float var14 = MathHelper.func_76134_b(-var6 * 0.017453292F - 3.1415927F);
/* 285 */     float var15 = MathHelper.func_76126_a(-var6 * 0.017453292F - 3.1415927F);
/* 286 */     float var16 = -MathHelper.func_76134_b(-var5 * 0.017453292F);
/* 287 */     float var17 = MathHelper.func_76126_a(-var5 * 0.017453292F);
/* 288 */     float var18 = var15 * var16;
/* 289 */     float var20 = var14 * var16;
/* 290 */     double var21 = 10.0D;
/* 291 */     Vec3 var23 = var13.func_72441_c(var18 * var21, var17 * var21, var20 * var21);
/*     */     
/* 293 */     return world.func_147447_a(var13, var23, par3, !par3, false);
/*     */   }
/*     */   
/*     */   public static boolean isBlockAdjacentToAtleast(IBlockAccess world, int x, int y, int z, Block id, int md, int amount) {
/* 297 */     return isBlockAdjacentToAtleast(world, x, y, z, id, md, amount, 1);
/*     */   }
/*     */   
/*     */   public static boolean isBlockAdjacentToAtleast(IBlockAccess world, int x, int y, int z, Block id, int md, int amount, int range) {
/* 301 */     int count = 0;
/* 302 */     for (int xx = -range; xx <= range; xx++) {
/* 303 */       for (int yy = -range; yy <= range; yy++)
/* 304 */         for (int zz = -range; zz <= range; zz++)
/* 305 */           if ((xx != 0) || (yy != 0) || (zz != 0)) {
/* 306 */             if ((world.func_147439_a(x + xx, y + yy, z + zz) == id) && ((md == 32767) || (world.func_72805_g(x + xx, y + yy, z + zz) == md)))
/*     */             {
/* 308 */               count++;
/*     */             }
/* 310 */             if (count >= amount) return true;
/*     */           }
/*     */     }
/* 313 */     return count >= amount;
/*     */   }
/*     */   
/*     */   public static List<EntityItem> getContentsOfBlock(World world, int x, int y, int z) {
/* 317 */     List<EntityItem> list = world.func_72872_a(EntityItem.class, net.minecraft.util.AxisAlignedBB.func_72330_a(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
/*     */     
/* 319 */     return list;
/*     */   }
/*     */   
/*     */   public static int countExposedSides(World world, int x, int y, int z) {
/* 323 */     int count = 0;
/* 324 */     for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/* 325 */       if (world.func_147437_c(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)) count++;
/*     */     }
/* 327 */     return count;
/*     */   }
/*     */   
/*     */   public static boolean isBlockExposed(World world, int x, int y, int z)
/*     */   {
/* 332 */     if ((!world.func_147439_a(x, y, z + 1).func_149662_c()) || (!world.func_147439_a(x, y, z - 1).func_149662_c()) || (!world.func_147439_a(x + 1, y, z).func_149662_c()) || (!world.func_147439_a(x - 1, y, z).func_149662_c()) || (!world.func_147439_a(x, y + 1, z).func_149662_c()) || (!world.func_147439_a(x, y - 1, z).func_149662_c()))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 338 */       return true; }
/* 339 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isAdjacentToSolidBlock(World world, int x, int y, int z)
/*     */   {
/* 344 */     for (int a = 0; a < 6; a++) {
/* 345 */       ForgeDirection d = ForgeDirection.getOrientation(a);
/* 346 */       if (world.isSideSolid(x + d.offsetX, y + d.offsetY, z + d.offsetZ, d.getOpposite()))
/*     */       {
/* 348 */         return true; }
/*     */     }
/* 350 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean isBlockTouching(IBlockAccess world, int x, int y, int z, Block id)
/*     */   {
/* 356 */     if ((world.func_147439_a(x, y, z + 1) == id) || (world.func_147439_a(x, y, z - 1) == id) || (world.func_147439_a(x + 1, y, z) == id) || (world.func_147439_a(x - 1, y, z) == id) || (world.func_147439_a(x, y + 1, z) == id) || (world.func_147439_a(x, y - 1, z) == id))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 362 */       return true; }
/* 363 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean isBlockTouching(IBlockAccess world, int x, int y, int z, Block id, int md)
/*     */   {
/* 369 */     if (((world.func_147439_a(x, y, z + 1) == id) && (world.func_72805_g(x, y, z + 1) == md)) || ((world.func_147439_a(x, y, z - 1) == id) && (world.func_72805_g(x, y, z - 1) == md)) || ((world.func_147439_a(x + 1, y, z) == id) && (world.func_72805_g(x + 1, y, z) == md)) || ((world.func_147439_a(x - 1, y, z) == id) && (world.func_72805_g(x - 1, y, z) == md)) || ((world.func_147439_a(x, y + 1, z) == id) && (world.func_72805_g(x, y + 1, z) == md)) || ((world.func_147439_a(x, y - 1, z) == id) && (world.func_72805_g(x, y - 1, z) == md)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 381 */       return true; }
/* 382 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean isBlockTouchingOnSide(IBlockAccess world, int x, int y, int z, Block id, int md, int side)
/*     */   {
/* 388 */     if (((side > 3) && (world.func_147439_a(x, y, z + 1) == id) && (world.func_72805_g(x, y, z + 1) == md)) || ((side > 3) && (world.func_147439_a(x, y, z - 1) == id) && (world.func_72805_g(x, y, z - 1) == md)) || ((side > 1) && (side < 4) && (world.func_147439_a(x + 1, y, z) == id) && (world.func_72805_g(x + 1, y, z) == md)) || ((side > 1) && (side < 4) && (world.func_147439_a(x - 1, y, z) == id) && (world.func_72805_g(x - 1, y, z) == md)) || ((side > 1) && (world.func_147439_a(x, y + 1, z) == id) && (world.func_72805_g(x, y + 1, z) == md)) || ((side > 1) && (world.func_147439_a(x, y - 1, z) == id) && (world.func_72805_g(x, y - 1, z) == md)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 400 */       return true;
/*     */     }
/* 402 */     if (((side > 3) && (world.func_147439_a(x, y + 1, z + 1) == id) && (world.func_72805_g(x, y + 1, z + 1) == md)) || ((side > 3) && (world.func_147439_a(x, y + 1, z - 1) == id) && (world.func_72805_g(x, y + 1, z - 1) == md)) || ((side > 1) && (side < 4) && (world.func_147439_a(x + 1, y + 1, z) == id) && (world.func_72805_g(x + 1, y + 1, z) == md)) || ((side > 1) && (side < 4) && (world.func_147439_a(x - 1, y + 1, z) == id) && (world.func_72805_g(x - 1, y + 1, z) == md)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 412 */       return true;
/*     */     }
/* 414 */     if (((side > 3) && (world.func_147439_a(x, y - 1, z + 1) == id) && (world.func_72805_g(x, y - 1, z + 1) == md)) || ((side > 3) && (world.func_147439_a(x, y - 1, z - 1) == id) && (world.func_72805_g(x, y - 1, z - 1) == md)) || ((side > 1) && (side < 4) && (world.func_147439_a(x + 1, y - 1, z) == id) && (world.func_72805_g(x + 1, y - 1, z) == md)) || ((side > 1) && (side < 4) && (world.func_147439_a(x - 1, y - 1, z) == id) && (world.func_72805_g(x - 1, y - 1, z) == md)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 424 */       return true;
/*     */     }
/* 426 */     switch (side) {
/*     */     case 0: 
/* 428 */       if ((world.func_147439_a(x, y - 1, z) == id) && (world.func_72805_g(x, y - 1, z) == md))
/*     */       {
/* 430 */         return true;
/*     */       }
/*     */       break;
/*     */     case 1: 
/* 434 */       if ((world.func_147439_a(x, y + 1, z) == id) && (world.func_72805_g(x, y + 1, z) == md))
/*     */       {
/* 436 */         return true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       break;
/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 449 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/utils/BlockUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */