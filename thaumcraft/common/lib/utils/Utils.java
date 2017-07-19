/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import cpw.mods.fml.common.ObfuscationReflectionHelper;
/*     */ import cpw.mods.fml.common.eventhandler.Event.Result;
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.player.BonemealEvent;
/*     */ import thaumcraft.api.WorldCoordinates;
/*     */ import thaumcraft.api.internal.WeightedRandomLoot;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.equipment.ItemElementalAxe;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXVisDrain;
/*     */ import thaumcraft.common.lib.network.misc.PacketBiomeChange;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Utils
/*     */ {
/*     */   public static boolean isChunkLoaded(World world, int x, int z)
/*     */   {
/*  60 */     int xx = x >> 4;
/*  61 */     int zz = z >> 4;
/*  62 */     return world.func_72863_F().func_73149_a(xx, zz);
/*     */   }
/*     */   
/*     */   public static boolean useBonemealAtLoc(World world, EntityPlayer player, int x, int y, int z)
/*     */   {
/*  67 */     Block block = world.func_147439_a(x, y, z);
/*     */     
/*  69 */     BonemealEvent event = new BonemealEvent(player, world, block, x, y, z);
/*  70 */     if (MinecraftForge.EVENT_BUS.post(event)) {
/*  71 */       return false;
/*     */     }
/*     */     
/*  74 */     if (event.getResult() == Event.Result.ALLOW) {
/*  75 */       return true;
/*     */     }
/*     */     
/*  78 */     if ((block instanceof IGrowable)) {
/*  79 */       IGrowable igrowable = (IGrowable)block;
/*     */       
/*  81 */       if (igrowable.func_149851_a(world, x, y, z, world.field_72995_K)) {
/*  82 */         if ((!world.field_72995_K) && 
/*  83 */           (igrowable.func_149852_a(world, world.field_73012_v, x, y, z))) {
/*  84 */           igrowable.func_149853_b(world, world.field_73012_v, x, y, z);
/*     */         }
/*     */         
/*     */ 
/*  88 */         return true;
/*     */       }
/*     */     }
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean hasColor(byte[] colors) {
/*  95 */     for (byte col : colors)
/*  96 */       if (col >= 0)
/*  97 */         return true;
/*  98 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public static int getFirstUncoveredY(World world, int par1, int par2)
/*     */   {
/* 104 */     for (int var3 = 5; !world.func_147437_c(par1, var3 + 1, par2); var3++) {}
/*     */     
/*     */ 
/*     */ 
/* 108 */     return var3;
/*     */   }
/*     */   
/*     */   public static boolean isEETransmutionItem(Item item) {
/*     */     try {
/* 113 */       String itemClass = "com.pahimar.ee3.item.ITransmutationStone";
/* 114 */       Class ee = Class.forName(itemClass);
/* 115 */       if (ee.isAssignableFrom(item.getClass())) {
/* 116 */         return true;
/*     */       }
/*     */     }
/*     */     catch (Exception ex) {}
/* 120 */     return false;
/*     */   }
/*     */   
/*     */   public static void copyFile(File sourceFile, File destFile) throws IOException {
/* 124 */     if (!destFile.exists()) {
/* 125 */       destFile.createNewFile();
/*     */     }
/*     */     
/* 128 */     FileChannel source = null;
/* 129 */     FileChannel destination = null;
/*     */     try
/*     */     {
/* 132 */       source = new FileInputStream(sourceFile).getChannel();
/* 133 */       destination = new FileOutputStream(destFile).getChannel();
/* 134 */       destination.transferFrom(source, 0L, source.size());
/*     */     } finally {
/* 136 */       if (source != null) {
/* 137 */         source.close();
/*     */       }
/* 139 */       if (destination != null) {
/* 140 */         destination.close();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static int getFirstUncoveredBlockHeight(World world, int par1, int par2)
/*     */   {
/* 148 */     for (int var3 = 10; (!world.func_147437_c(par1, var3 + 1, par2)) || (var3 > 250); var3++) {}
/*     */     
/*     */ 
/*     */ 
/* 152 */     return var3;
/*     */   }
/*     */   
/* 155 */   public static HashMap<List<Object>, ItemStack> specialMiningResult = new HashMap();
/* 156 */   public static HashMap<List<Object>, Float> specialMiningChance = new HashMap();
/*     */   
/*     */   public static void addSpecialMiningResult(ItemStack in, ItemStack out, float chance) {
/* 159 */     specialMiningResult.put(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(in.func_77960_j()) }), out);
/* 160 */     specialMiningChance.put(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(in.func_77960_j()) }), Float.valueOf(chance));
/*     */   }
/*     */   
/*     */   public static ItemStack findSpecialMiningResult(ItemStack is, float chance, Random rand) {
/* 164 */     ItemStack dropped = is.func_77946_l();
/* 165 */     float r = rand.nextFloat();
/* 166 */     List ik = Arrays.asList(new Object[] { is.func_77973_b(), Integer.valueOf(is.func_77960_j()) });
/* 167 */     if ((specialMiningResult.containsKey(ik)) && (r <= chance * ((Float)specialMiningChance.get(ik)).floatValue())) {
/* 168 */       dropped = ((ItemStack)specialMiningResult.get(ik)).func_77946_l();
/* 169 */       dropped.field_77994_a *= is.field_77994_a;
/*     */     }
/* 171 */     return dropped;
/*     */   }
/*     */   
/*     */   public static float clamp_float(float par0, float par1, float par2) {
/* 175 */     return par0 > par2 ? par2 : par0 < par1 ? par1 : par0;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setBiomeAt(World world, int x, int z, BiomeGenBase biome)
/*     */   {
/* 194 */     if (biome == null)
/* 195 */       return;
/* 196 */     Chunk chunk = world.func_72938_d(x, z);
/* 197 */     byte[] array = chunk.func_76605_m();
/* 198 */     array[((z & 0xF) << 4 | x & 0xF)] = ((byte)(biome.field_76756_M & 0xFF));
/* 199 */     chunk.func_76616_a(array);
/* 200 */     if (!world.field_72995_K) {
/* 201 */       PacketHandler.INSTANCE.sendToAllAround(new PacketBiomeChange(x, z, (short)biome.field_76756_M), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, world.func_72976_f(x, z), z, 32.0D));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 208 */   public static final String[] colorNames = { "White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };
/*     */   
/*     */ 
/*     */ 
/* 212 */   public static final int[] colors = { 15790320, 15435844, 12801229, 6719955, 14602026, 4312372, 14188952, 4408131, 10526880, 2651799, 8073150, 2437522, 5320730, 3887386, 11743532, 1973019 };
/*     */   
/*     */ 
/*     */   public static boolean isWoodLog(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 217 */     Block bi = world.func_147439_a(x, y, z);
/* 218 */     int md = world.func_72805_g(x, y, z);
/* 219 */     if (bi == Blocks.field_150350_a)
/* 220 */       return false;
/* 221 */     if (bi.canSustainLeaves(world, x, y, z))
/* 222 */       return true;
/* 223 */     if (ItemElementalAxe.oreDictLogs.contains(Arrays.asList(new Object[] { bi, Integer.valueOf(md) })))
/* 224 */       return true;
/* 225 */     return false;
/*     */   }
/*     */   
/*     */   public static void resetFloatCounter(EntityPlayerMP player) {
/*     */     try {
/* 230 */       ObfuscationReflectionHelper.setPrivateValue(NetHandlerPlayServer.class, player.field_71135_a, Integer.valueOf(0), new String[] { "floatingTickCount", "field_147365_f" });
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean getBit(int value, int bit)
/*     */   {
/* 238 */     return (value & 1 << bit) != 0;
/*     */   }
/*     */   
/*     */   public static int setBit(int value, int bit) {
/* 242 */     return value | 1 << bit;
/*     */   }
/*     */   
/*     */   public static int clearBit(int value, int bit) {
/* 246 */     return value & (1 << bit ^ 0xFFFFFFFF);
/*     */   }
/*     */   
/*     */   public static int toggleBit(int value, int bit) {
/* 250 */     return value ^ 1 << bit;
/*     */   }
/*     */   
/*     */   public static byte pack(boolean[] vals) {
/* 254 */     byte result = 0;
/* 255 */     for (boolean bit : vals)
/* 256 */       result = (byte)(result << 1 | (bit ? 1 : 0) & 0x1);
/* 257 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean[] unpack(byte val) {
/* 261 */     boolean[] result = new boolean[8];
/* 262 */     for (int i = 0; i < 8; i++) {
/* 263 */       result[i] = ((byte)(val >> 7 - i & 0x1) == 1 ? 1 : false);
/*     */     }
/* 265 */     return result;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Object getNBTDataFromId(NBTTagCompound nbt, byte id, String key)
/*     */   {
/* 287 */     switch (id) {
/*     */     case 1: 
/* 289 */       return Byte.valueOf(nbt.func_74771_c(key));
/*     */     case 2: 
/* 291 */       return Short.valueOf(nbt.func_74765_d(key));
/*     */     case 3: 
/* 293 */       return Integer.valueOf(nbt.func_74762_e(key));
/*     */     case 4: 
/* 295 */       return Long.valueOf(nbt.func_74763_f(key));
/*     */     case 5: 
/* 297 */       return Float.valueOf(nbt.func_74760_g(key));
/*     */     case 6: 
/* 299 */       return Double.valueOf(nbt.func_74769_h(key));
/*     */     case 7: 
/* 301 */       return nbt.func_74770_j(key);
/*     */     case 8: 
/* 303 */       return nbt.func_74779_i(key);
/*     */     case 9: 
/* 305 */       return nbt.func_150295_c(key, 10);
/*     */     case 10: 
/* 307 */       return nbt.func_74781_a(key);
/*     */     case 11: 
/* 309 */       return nbt.func_74759_k(key);
/*     */     }
/* 311 */     return null;
/*     */   }
/*     */   
/*     */ 
/* 315 */   public static HashMap<WorldCoordinates, Long> effectBuffer = new HashMap();
/*     */   
/*     */   public static void generateVisEffect(int dim, int x, int y, int z, int x2, int y2, int z2, int color)
/*     */   {
/* 319 */     WorldCoordinates wc = new WorldCoordinates(x, y, z, dim);
/* 320 */     Long time = Long.valueOf(System.currentTimeMillis());
/* 321 */     Random rand = new Random(time.longValue());
/* 322 */     if (effectBuffer.containsKey(wc)) {
/* 323 */       if (((Long)effectBuffer.get(wc)).longValue() < time.longValue()) {
/* 324 */         effectBuffer.remove(wc);
/*     */       }
/*     */     } else {
/* 327 */       effectBuffer.put(wc, Long.valueOf(time.longValue() + 500L + rand.nextInt(100)));
/* 328 */       PacketHandler.INSTANCE.sendToAllAround(new PacketFXVisDrain(x, y, z, x2, y2, z2, color), new NetworkRegistry.TargetPoint(dim, x, y, z, 64.0D));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static <T, E> void setPrivateFinalValue(Class<? super T> classToAccess, T instance, E value, String... fieldNames)
/*     */   {
/* 336 */     Field field = ReflectionHelper.findField(classToAccess, ObfuscationReflectionHelper.remapFieldNames(classToAccess.getName(), fieldNames));
/*     */     
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 342 */       Field modifiersField = Field.class.getDeclaredField("modifiers");
/* 343 */       modifiersField.setAccessible(true);
/* 344 */       modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
/*     */       
/*     */ 
/* 347 */       field.set(instance, value);
/*     */     } catch (Exception e) {
/* 349 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isLyingInCone(double[] x, double[] t, double[] b, float aperture)
/*     */   {
/* 363 */     double halfAperture = aperture / 2.0F;
/*     */     
/*     */ 
/* 366 */     double[] apexToXVect = dif(t, x);
/*     */     
/*     */ 
/* 369 */     double[] axisVect = dif(t, b);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 376 */     boolean isInInfiniteCone = dotProd(apexToXVect, axisVect) / magn(apexToXVect) / magn(axisVect) > Math.cos(halfAperture);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 382 */     if (!isInInfiniteCone) {
/* 383 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 388 */     boolean isUnderRoundCap = dotProd(apexToXVect, axisVect) / magn(axisVect) < magn(axisVect);
/*     */     
/* 390 */     return isUnderRoundCap;
/*     */   }
/*     */   
/*     */   public static double dotProd(double[] a, double[] b) {
/* 394 */     return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
/*     */   }
/*     */   
/*     */   public static double[] dif(double[] a, double[] b) {
/* 398 */     return new double[] { a[0] - b[0], a[1] - b[1], a[2] - b[2] };
/*     */   }
/*     */   
/*     */   public static double magn(double[] a) {
/* 402 */     return Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[2] * a[2]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Vec3 calculateVelocity(Vec3 from, Vec3 to, double heightGain, double gravity)
/*     */   {
/* 409 */     double endGain = to.field_72448_b - from.field_72448_b;
/* 410 */     double horizDist = Math.sqrt(distanceSquared2d(from, to));
/*     */     
/*     */ 
/* 413 */     double gain = heightGain;
/* 414 */     double maxGain = gain > endGain + gain ? gain : endGain + gain;
/*     */     
/*     */ 
/* 417 */     double a = -horizDist * horizDist / (4.0D * maxGain);
/* 418 */     double b = horizDist;
/* 419 */     double c = -endGain;
/*     */     
/* 421 */     double slope = -b / (2.0D * a) - Math.sqrt(b * b - 4.0D * a * c) / (2.0D * a);
/*     */     
/*     */ 
/* 424 */     double vy = Math.sqrt(maxGain * gravity);
/*     */     
/*     */ 
/* 427 */     double vh = vy / slope;
/*     */     
/*     */ 
/* 430 */     double dx = to.field_72450_a - from.field_72450_a;
/* 431 */     double dz = to.field_72449_c - from.field_72449_c;
/* 432 */     double mag = Math.sqrt(dx * dx + dz * dz);
/* 433 */     double dirx = dx / mag;
/* 434 */     double dirz = dz / mag;
/*     */     
/*     */ 
/* 437 */     double vx = vh * dirx;
/* 438 */     double vz = vh * dirz;
/*     */     
/* 440 */     return Vec3.func_72443_a(vx, vy, vz);
/*     */   }
/*     */   
/*     */   public static double distanceSquared2d(Vec3 from, Vec3 to)
/*     */   {
/* 445 */     double dx = to.field_72450_a - from.field_72450_a;
/* 446 */     double dz = to.field_72449_c - from.field_72449_c;
/* 447 */     return dx * dx + dz * dz;
/*     */   }
/*     */   
/*     */   public static double distanceSquared3d(Vec3 from, Vec3 to) {
/* 451 */     double dx = to.field_72450_a - from.field_72450_a;
/* 452 */     double dy = to.field_72448_b - from.field_72448_b;
/* 453 */     double dz = to.field_72449_c - from.field_72449_c;
/* 454 */     return dx * dx + dy * dy + dz * dz;
/*     */   }
/*     */   
/*     */   public static ItemStack generateLoot(int rarity, Random rand) {
/* 458 */     ItemStack is = null;
/* 459 */     if ((rarity > 0) && (rand.nextFloat() < 0.025F * rarity)) {
/* 460 */       is = genGear(rarity, rand);
/* 461 */       if (is == null) is = generateLoot(rarity, rand);
/*     */     } else {
/* 463 */       switch (rarity) {
/* 464 */       default:  is = ((WeightedRandomLoot)WeightedRandom.func_76271_a(rand, WeightedRandomLoot.lootBagCommon)).item; break;
/* 465 */       case 1:  is = ((WeightedRandomLoot)WeightedRandom.func_76271_a(rand, WeightedRandomLoot.lootBagUncommon)).item; break;
/* 466 */       case 2:  is = ((WeightedRandomLoot)WeightedRandom.func_76271_a(rand, WeightedRandomLoot.lootBagRare)).item;
/*     */       }
/*     */       
/*     */     }
/* 470 */     if (is.func_77973_b() == Items.field_151122_aG) {
/* 471 */       EnchantmentHelper.func_77504_a(rand, is, (int)(5.0F + rarity * 0.75F * rand.nextInt(18)));
/*     */     }
/*     */     
/* 474 */     return is.func_77946_l();
/*     */   }
/*     */   
/*     */   private static ItemStack genGear(int rarity, Random rand) {
/* 478 */     ItemStack is = null;
/*     */     
/* 480 */     int quality = rand.nextInt(2);
/* 481 */     if (rand.nextFloat() < 0.2F) quality++;
/* 482 */     if (rand.nextFloat() < 0.15F) quality++;
/* 483 */     if (rand.nextFloat() < 0.1F) quality++;
/* 484 */     if (rand.nextFloat() < 0.095F) quality++;
/* 485 */     if (rand.nextFloat() < 0.095F) { quality++;
/*     */     }
/* 487 */     Item item = getGearItemForSlot(rand.nextInt(5), quality);
/* 488 */     if (item != null) {
/* 489 */       is = new ItemStack(item, 1, rand.nextInt(1 + item.func_77612_l() / 6));
/*     */     } else {
/* 491 */       return null;
/*     */     }
/*     */     
/* 494 */     if (rand.nextInt(4) < rarity) {
/* 495 */       EnchantmentHelper.func_77504_a(rand, is, (int)(5.0F + rarity * 0.75F * rand.nextInt(18)));
/*     */     }
/* 497 */     return is.func_77946_l();
/*     */   }
/*     */   
/*     */   private static Item getGearItemForSlot(int slot, int quality)
/*     */   {
/* 502 */     switch (slot)
/*     */     {
/*     */     case 4: 
/* 505 */       if (quality == 0) return Items.field_151024_Q;
/* 506 */       if (quality == 1) return Items.field_151169_ag;
/* 507 */       if (quality == 2) return Items.field_151020_U;
/* 508 */       if (quality == 3) return Items.field_151028_Y;
/* 509 */       if (quality == 4) return ConfigItems.itemHelmetThaumium;
/* 510 */       if (quality == 5) return Items.field_151161_ac;
/* 511 */       if (quality == 6) return ConfigItems.itemHelmetVoid;
/*     */     case 3: 
/* 513 */       if (quality == 0) return Items.field_151027_R;
/* 514 */       if (quality == 1) return Items.field_151171_ah;
/* 515 */       if (quality == 2) return Items.field_151023_V;
/* 516 */       if (quality == 3) return Items.field_151030_Z;
/* 517 */       if (quality == 4) return ConfigItems.itemChestThaumium;
/* 518 */       if (quality == 5) return Items.field_151163_ad;
/* 519 */       if (quality == 6) return ConfigItems.itemChestVoid;
/*     */     case 2: 
/* 521 */       if (quality == 0) return Items.field_151026_S;
/* 522 */       if (quality == 1) return Items.field_151149_ai;
/* 523 */       if (quality == 2) return Items.field_151022_W;
/* 524 */       if (quality == 3) return Items.field_151165_aa;
/* 525 */       if (quality == 4) return ConfigItems.itemLegsThaumium;
/* 526 */       if (quality == 5) return Items.field_151173_ae;
/* 527 */       if (quality == 6) return ConfigItems.itemLegsVoid;
/*     */     case 1: 
/* 529 */       if (quality == 0) return Items.field_151021_T;
/* 530 */       if (quality == 1) return Items.field_151151_aj;
/* 531 */       if (quality == 2) return Items.field_151029_X;
/* 532 */       if (quality == 3) return Items.field_151167_ab;
/* 533 */       if (quality == 4) return ConfigItems.itemBootsThaumium;
/* 534 */       if (quality == 5) return Items.field_151175_af;
/* 535 */       if (quality == 6) return ConfigItems.itemBootsVoid;
/*     */     case 0: 
/* 537 */       if (quality == 0) return Items.field_151036_c;
/* 538 */       if (quality == 1) return Items.field_151040_l;
/* 539 */       if (quality == 2) return Items.field_151006_E;
/* 540 */       if (quality == 3) return Items.field_151010_B;
/* 541 */       if (quality == 4) return ConfigItems.itemSwordThaumium;
/* 542 */       if (quality == 5) return Items.field_151048_u;
/* 543 */       if (quality == 6) return ConfigItems.itemSwordVoid;
/*     */       break; }
/* 545 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/utils/Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */