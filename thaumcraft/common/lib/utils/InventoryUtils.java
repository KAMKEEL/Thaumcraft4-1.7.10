/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentDurability;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.tileentity.TileEntityHopper;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.tiles.TileArcaneWorkbench;
/*     */ import thaumcraft.common.tiles.TileResearchTable;
/*     */ 
/*     */ public class InventoryUtils
/*     */ {
/*     */   public static ItemStack placeItemStackIntoInventory(ItemStack stack, IInventory inventory, int side, boolean doit)
/*     */   {
/*  34 */     ItemStack itemstack = stack.func_77946_l();
/*  35 */     ItemStack itemstack1 = insertStack(inventory, itemstack, side, doit);
/*     */     
/*  37 */     if ((itemstack1 == null) || (itemstack1.field_77994_a == 0))
/*     */     {
/*  39 */       if (doit) inventory.func_70296_d();
/*  40 */       return null;
/*     */     }
/*  42 */     stack = itemstack1;
/*  43 */     return stack.func_77946_l();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ItemStack insertStack(IInventory inventory, ItemStack stack1, int side, boolean doit)
/*     */   {
/*  51 */     if (((inventory instanceof ISidedInventory)) && (side > -1))
/*     */     {
/*  53 */       ISidedInventory isidedinventory = (ISidedInventory)inventory;
/*  54 */       int[] aint = isidedinventory.func_94128_d(side);
/*     */       
/*     */ 
/*  57 */       if (aint != null) {
/*  58 */         for (int j = 0; (j < aint.length) && (stack1 != null) && (stack1.field_77994_a > 0); j++)
/*     */         {
/*  60 */           if ((inventory.func_70301_a(aint[j]) != null) && (inventory.func_70301_a(aint[j]).func_77969_a(stack1)))
/*  61 */             stack1 = attemptInsertion(inventory, stack1, aint[j], side, doit);
/*  62 */           if ((stack1 == null) || (stack1.field_77994_a == 0))
/*     */             break;
/*     */         }
/*     */       }
/*  66 */       if ((aint != null) && (stack1 != null) && (stack1.field_77994_a > 0)) {
/*  67 */         for (int j = 0; (j < aint.length) && (stack1 != null) && (stack1.field_77994_a > 0); j++)
/*     */         {
/*  69 */           stack1 = attemptInsertion(inventory, stack1, aint[j], side, doit);
/*  70 */           if ((stack1 == null) || (stack1.field_77994_a == 0))
/*     */             break;
/*     */         }
/*     */       }
/*     */     } else {
/*  75 */       int k = inventory.func_70302_i_();
/*     */       
/*     */ 
/*  78 */       for (int l = 0; (l < k) && (stack1 != null) && (stack1.field_77994_a > 0); l++)
/*     */       {
/*  80 */         if ((inventory.func_70301_a(l) != null) && (inventory.func_70301_a(l).func_77969_a(stack1)))
/*  81 */           stack1 = attemptInsertion(inventory, stack1, l, side, doit);
/*  82 */         if ((stack1 == null) || (stack1.field_77994_a == 0))
/*     */           break;
/*     */       }
/*  85 */       if ((stack1 != null) && (stack1.field_77994_a > 0))
/*     */       {
/*  87 */         TileEntityChest dc = null;
/*  88 */         if ((inventory instanceof TileEntity)) {
/*  89 */           dc = getDoubleChest((TileEntity)inventory);
/*  90 */           if (dc != null) {
/*  91 */             int k2 = dc.func_70302_i_();
/*     */             
/*  93 */             for (int l = 0; (l < k2) && (stack1 != null) && (stack1.field_77994_a > 0); l++)
/*     */             {
/*  95 */               if ((dc.func_70301_a(l) != null) && (dc.func_70301_a(l).func_77969_a(stack1)))
/*  96 */                 stack1 = attemptInsertion(dc, stack1, l, side, doit);
/*  97 */               if ((stack1 == null) || (stack1.field_77994_a == 0))
/*     */                 break;
/*     */             }
/*     */           }
/*     */         }
/* 102 */         if ((stack1 != null) && (stack1.field_77994_a > 0)) {
/* 103 */           for (int l = 0; (l < k) && (stack1 != null) && (stack1.field_77994_a > 0); l++)
/*     */           {
/* 105 */             stack1 = attemptInsertion(inventory, stack1, l, side, doit);
/* 106 */             if ((stack1 == null) || (stack1.field_77994_a == 0))
/*     */               break;
/*     */           }
/* 109 */           if ((stack1 != null) && (stack1.field_77994_a > 0) && (dc != null)) {
/* 110 */             int k2 = dc.func_70302_i_();
/* 111 */             for (int l = 0; (l < k2) && (stack1 != null) && (stack1.field_77994_a > 0); l++)
/*     */             {
/* 113 */               if ((dc.func_70301_a(l) != null) && (dc.func_70301_a(l).func_77969_a(stack1)))
/* 114 */                 stack1 = attemptInsertion(dc, stack1, l, side, doit);
/* 115 */               if ((stack1 == null) || (stack1.field_77994_a == 0))
/*     */                 break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 122 */     if ((stack1 != null) && (stack1.field_77994_a == 0))
/*     */     {
/* 124 */       stack1 = null;
/*     */     }
/*     */     
/* 127 */     return stack1;
/*     */   }
/*     */   
/*     */   private static ItemStack attemptInsertion(IInventory inventory, ItemStack stack, int slot, int side, boolean doit)
/*     */   {
/* 132 */     ItemStack slotStack = inventory.func_70301_a(slot);
/* 133 */     if (canInsertItemToInventory(inventory, stack, slot, side))
/*     */     {
/* 135 */       boolean flag = false;
/*     */       
/* 137 */       if (slotStack == null)
/*     */       {
/* 139 */         if (inventory.func_70297_j_() < stack.field_77994_a) {
/* 140 */           ItemStack in = stack.func_77979_a(inventory.func_70297_j_());
/* 141 */           if (doit) inventory.func_70299_a(slot, in);
/*     */         }
/*     */         else {
/* 144 */           if (doit) inventory.func_70299_a(slot, stack);
/* 145 */           stack = null;
/*     */         }
/* 147 */         flag = true;
/*     */       }
/* 149 */       else if (areItemStacksEqualStrict(slotStack, stack))
/*     */       {
/* 151 */         int k = Math.min(inventory.func_70297_j_() - slotStack.field_77994_a, stack.func_77976_d() - slotStack.field_77994_a);
/*     */         
/* 153 */         int l = Math.min(stack.field_77994_a, k);
/* 154 */         stack.field_77994_a -= l;
/* 155 */         if (doit) slotStack.field_77994_a += l;
/* 156 */         flag = l > 0;
/*     */       }
/*     */       
/* 159 */       if ((flag) && (doit))
/*     */       {
/* 161 */         if ((inventory instanceof TileEntityHopper))
/*     */         {
/* 163 */           ((TileEntityHopper)inventory).func_145896_c(8);
/* 164 */           inventory.func_70296_d();
/*     */         }
/*     */         
/* 167 */         inventory.func_70296_d();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 174 */     return stack;
/*     */   }
/*     */   
/*     */   public static ItemStack getFirstItemInInventory(IInventory inventory, int size, int side, boolean doit) {
/* 178 */     ItemStack stack1 = null;
/*     */     
/* 180 */     if (((inventory instanceof ISidedInventory)) && (side > -1))
/*     */     {
/* 182 */       ISidedInventory isidedinventory = (ISidedInventory)inventory;
/* 183 */       int[] aint = isidedinventory.func_94128_d(side);
/*     */       
/* 185 */       for (int j = 0; j < aint.length; j++)
/*     */       {
/* 187 */         if ((stack1 == null) && (inventory.func_70301_a(aint[j]) != null)) {
/* 188 */           stack1 = inventory.func_70301_a(aint[j]).func_77946_l();
/* 189 */           stack1.field_77994_a = size;
/*     */         }
/* 191 */         if (stack1 != null) stack1 = attemptExtraction(inventory, stack1, aint[j], side, false, false, false, doit);
/* 192 */         if (stack1 != null) {
/*     */           break;
/*     */         }
/*     */       }
/*     */     } else {
/* 197 */       int k = inventory.func_70302_i_();
/*     */       
/* 199 */       for (int l = 0; l < k; l++)
/*     */       {
/* 201 */         if ((stack1 == null) && (inventory.func_70301_a(l) != null)) {
/* 202 */           stack1 = inventory.func_70301_a(l).func_77946_l();
/* 203 */           stack1.field_77994_a = size;
/*     */         }
/* 205 */         if (stack1 != null) stack1 = attemptExtraction(inventory, stack1, l, side, false, false, false, doit);
/* 206 */         if (stack1 != null)
/*     */           break;
/*     */       }
/*     */     }
/* 210 */     if ((stack1 == null) || (stack1.field_77994_a == 0))
/*     */     {
/* 212 */       if (doit) inventory.func_70296_d();
/* 213 */       return null;
/*     */     }
/*     */     
/* 216 */     return stack1.func_77946_l();
/*     */   }
/*     */   
/*     */   public static boolean inventoryContains(IInventory inventory, ItemStack stack, int side, boolean useOre, boolean ignoreDamage, boolean ignoreNBT)
/*     */   {
/* 221 */     return extractStack(inventory, stack, side, useOre, ignoreDamage, ignoreNBT, false) != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ItemStack extractStack(IInventory inventory, ItemStack stack1, int side, boolean useOre, boolean ignoreDamage, boolean ignoreNBT, boolean doit)
/*     */   {
/* 229 */     ItemStack outStack = null;
/* 230 */     if (((inventory instanceof ISidedInventory)) && (side > -1))
/*     */     {
/* 232 */       ISidedInventory isidedinventory = (ISidedInventory)inventory;
/* 233 */       int[] aint = isidedinventory.func_94128_d(side);
/*     */       
/* 235 */       for (int j = 0; (j < aint.length) && (stack1 != null) && (stack1.field_77994_a > 0) && (outStack == null); j++)
/*     */       {
/* 237 */         outStack = attemptExtraction(inventory, stack1, aint[j], side, useOre, ignoreDamage, ignoreNBT, doit);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 242 */       int k = inventory.func_70302_i_();
/*     */       
/* 244 */       for (int l = 0; (l < k) && (stack1 != null) && (stack1.field_77994_a > 0) && (outStack == null); l++)
/*     */       {
/* 246 */         outStack = attemptExtraction(inventory, stack1, l, side, useOre, ignoreDamage, ignoreNBT, doit);
/*     */       }
/*     */     }
/*     */     
/* 250 */     if ((outStack == null) || (outStack.field_77994_a == 0))
/*     */     {
/* 252 */       return null;
/*     */     }
/*     */     
/* 255 */     return outStack.func_77946_l();
/*     */   }
/*     */   
/*     */   public static ItemStack attemptExtraction(IInventory inventory, ItemStack stack, int slot, int side, boolean useOre, boolean ignoreDamage, boolean ignoreNBT, boolean doit)
/*     */   {
/* 260 */     ItemStack slotStack = inventory.func_70301_a(slot);
/* 261 */     ItemStack outStack = stack.func_77946_l();
/*     */     
/* 263 */     if (canExtractItemFromInventory(inventory, slotStack, slot, side))
/*     */     {
/* 265 */       boolean flag = false;
/*     */       
/* 267 */       if (areItemStacksEqual(slotStack, stack, useOre, ignoreDamage, ignoreNBT))
/*     */       {
/* 269 */         outStack = slotStack.func_77946_l();
/* 270 */         outStack.field_77994_a = stack.field_77994_a;
/* 271 */         int k = stack.field_77994_a - slotStack.field_77994_a;
/*     */         
/* 273 */         if (k >= 0) {
/* 274 */           outStack.field_77994_a -= k;
/* 275 */           if (doit) {
/* 276 */             slotStack = null;
/* 277 */             inventory.func_70299_a(slot, null);
/*     */           }
/*     */         }
/* 280 */         else if (doit) {
/* 281 */           slotStack.field_77994_a -= outStack.field_77994_a;
/* 282 */           inventory.func_70299_a(slot, slotStack);
/*     */         }
/*     */         
/* 285 */         flag = true;
/*     */       }
/*     */       else
/*     */       {
/* 289 */         return null;
/*     */       }
/*     */       
/* 292 */       if ((flag) && (doit))
/*     */       {
/* 294 */         inventory.func_70296_d();
/*     */       }
/*     */     } else {
/* 297 */       return null;
/*     */     }
/*     */     
/* 300 */     return outStack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean canInsertItemToInventory(IInventory inventory, ItemStack stack1, int par2, int par3)
/*     */   {
/* 308 */     return (stack1 != null) && (inventory.func_94041_b(par2, stack1)) && ((!(inventory instanceof ISidedInventory)) || (((ISidedInventory)inventory).func_102007_a(par2, stack1, par3)));
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean canExtractItemFromInventory(IInventory inventory, ItemStack stack1, int par2, int par3)
/*     */   {
/* 314 */     return (stack1 != null) && ((!(inventory instanceof ISidedInventory)) || (((ISidedInventory)inventory).func_102008_b(par2, stack1, par3)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean compareMultipleItems(ItemStack c1, ItemStack[] c2)
/*     */   {
/* 325 */     if ((c1 == null) || (c1.field_77994_a <= 0)) return false;
/* 326 */     for (ItemStack is : c2) {
/* 327 */       if ((is != null) && (c1.func_77969_a(is)) && (ItemStack.func_77970_a(c1, is))) return true;
/*     */     }
/* 329 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean areItemStacksEqualStrict(ItemStack stack0, ItemStack stack1) {
/* 333 */     return areItemStacksEqual(stack0, stack1, false, false, false);
/*     */   }
/*     */   
/*     */   public static boolean areItemStacksEqualForCrafting(ItemStack stack0, ItemStack stack1, boolean useOre, boolean ignoreDamage, boolean ignoreNBT)
/*     */   {
/* 338 */     if ((stack0 == null) && (stack1 != null)) return false;
/* 339 */     if ((stack0 != null) && (stack1 == null)) return false;
/* 340 */     if ((stack0 == null) && (stack1 == null)) { return true;
/*     */     }
/* 342 */     if (useOre) {
/* 343 */       int od = OreDictionary.getOreID(stack0);
/* 344 */       if (od != -1) {
/* 345 */         ItemStack[] ores = (ItemStack[])OreDictionary.getOres(Integer.valueOf(od)).toArray(new ItemStack[0]);
/* 346 */         if (ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { stack1 }, ores)) {
/* 347 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 351 */     boolean t1 = true;
/* 352 */     if (!ignoreNBT) {
/* 353 */       t1 = ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(stack0, stack1);
/*     */     }
/*     */     
/* 356 */     boolean t2 = stack0.func_77960_j() != stack1.func_77960_j();
/* 357 */     if ((ignoreDamage) && (stack0.func_77984_f()) && (stack1.func_77984_f())) {
/* 358 */       t2 = false;
/*     */     }
/*     */     
/* 361 */     if ((t2) && (ignoreDamage) && ((stack0.func_77960_j() == 32767) || (stack1.func_77960_j() == 32767)))
/*     */     {
/* 363 */       t2 = false;
/*     */     }
/*     */     
/* 366 */     return stack0.field_77994_a > stack0.func_77976_d() ? false : t2 ? false : stack0.func_77973_b() != stack1.func_77973_b() ? false : t1;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean areItemStacksEqual(ItemStack stack0, ItemStack stack1, boolean useOre, boolean ignoreDamage, boolean ignoreNBT)
/*     */   {
/* 372 */     if ((stack0 == null) && (stack1 != null)) return false;
/* 373 */     if ((stack0 != null) && (stack1 == null)) return false;
/* 374 */     if ((stack0 == null) && (stack1 == null)) { return true;
/*     */     }
/* 376 */     if (useOre) {
/* 377 */       int od = OreDictionary.getOreID(stack0);
/* 378 */       if (od != -1) {
/* 379 */         ItemStack[] ores = (ItemStack[])OreDictionary.getOres(Integer.valueOf(od)).toArray(new ItemStack[0]);
/* 380 */         if (ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { stack1 }, ores)) {
/* 381 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 385 */     boolean t1 = true;
/* 386 */     if (!ignoreNBT) {
/* 387 */       t1 = ItemStack.func_77970_a(stack0, stack1);
/*     */     }
/*     */     
/* 390 */     boolean t2 = stack0.func_77960_j() != stack1.func_77960_j();
/* 391 */     if ((ignoreDamage) && (stack0.func_77984_f()) && (stack1.func_77984_f())) {
/* 392 */       t2 = false;
/*     */     }
/*     */     
/* 395 */     if ((t2) && (ignoreDamage) && ((stack0.func_77960_j() == 32767) || (stack1.func_77960_j() == 32767)))
/*     */     {
/* 397 */       t2 = false;
/*     */     }
/*     */     
/* 400 */     return t2 ? false : stack0.func_77973_b() != stack1.func_77973_b() ? false : t1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean consumeInventoryItem(EntityPlayer player, Item item, int md)
/*     */   {
/* 407 */     for (int var2 = 0; var2 < player.field_71071_by.field_70462_a.length; var2++)
/*     */     {
/* 409 */       if ((player.field_71071_by.field_70462_a[var2] != null) && (player.field_71071_by.field_70462_a[var2].func_77973_b() == item) && (player.field_71071_by.field_70462_a[var2].func_77960_j() == md))
/*     */       {
/*     */ 
/*     */ 
/* 413 */         if (--player.field_71071_by.field_70462_a[var2].field_77994_a <= 0)
/*     */         {
/* 415 */           player.field_71071_by.field_70462_a[var2] = null;
/*     */         }
/* 417 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 421 */     return false;
/*     */   }
/*     */   
/*     */   public static void dropItems(World world, int x, int y, int z) {
/* 425 */     Random rand = new Random();
/* 426 */     int md = world.func_72805_g(x, y, z);
/* 427 */     TileEntity tileEntity = world.func_147438_o(x, y, z);
/* 428 */     if (!(tileEntity instanceof IInventory)) {
/* 429 */       return;
/*     */     }
/* 431 */     IInventory inventory = (IInventory)tileEntity;
/*     */     
/* 433 */     for (int i = 0; i < inventory.func_70302_i_(); i++)
/* 434 */       if (((!(tileEntity instanceof TileResearchTable)) || (md != 15) || (i != 9)) && (
/* 435 */         (!(tileEntity instanceof TileArcaneWorkbench)) || (i != 9))) {
/* 436 */         ItemStack item = inventory.func_70301_a(i);
/*     */         
/* 438 */         if ((item != null) && (item.field_77994_a > 0)) {
/* 439 */           float rx = rand.nextFloat() * 0.8F + 0.1F;
/* 440 */           float ry = rand.nextFloat() * 0.8F + 0.1F;
/* 441 */           float rz = rand.nextFloat() * 0.8F + 0.1F;
/*     */           
/* 443 */           EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.func_77973_b(), item.field_77994_a, item.func_77960_j()));
/*     */           
/*     */ 
/*     */ 
/* 447 */           if (item.func_77942_o()) {
/* 448 */             entityItem.func_92059_d().func_77982_d((NBTTagCompound)item.func_77978_p().func_74737_b());
/*     */           }
/*     */           
/* 451 */           float factor = 0.05F;
/* 452 */           entityItem.field_70159_w = (rand.nextGaussian() * factor);
/* 453 */           entityItem.field_70181_x = (rand.nextGaussian() * factor + 0.20000000298023224D);
/* 454 */           entityItem.field_70179_y = (rand.nextGaussian() * factor);
/* 455 */           world.func_72838_d(entityItem);
/* 456 */           inventory.func_70299_a(i, null);
/*     */         }
/*     */       }
/*     */   }
/*     */   
/*     */   public static void dropItemsAtEntity(World world, int x, int y, int z, Entity entity) {
/* 462 */     Random rand = new Random();
/* 463 */     int md = world.func_72805_g(x, y, z);
/* 464 */     TileEntity tileEntity = world.func_147438_o(x, y, z);
/* 465 */     if (!(tileEntity instanceof IInventory)) {
/* 466 */       return;
/*     */     }
/* 468 */     IInventory inventory = (IInventory)tileEntity;
/*     */     
/* 470 */     for (int i = 0; i < inventory.func_70302_i_(); i++) {
/* 471 */       if (((!(tileEntity instanceof TileResearchTable)) || (md != 15) || (i != 9)) && (
/* 472 */         (!(tileEntity instanceof TileArcaneWorkbench)) || (i != 9))) {
/* 473 */         ItemStack item = inventory.func_70301_a(i);
/*     */         
/* 475 */         if ((item != null) && (item.field_77994_a > 0))
/*     */         {
/* 477 */           EntityItem entityItem = new EntityItem(world, entity.field_70165_t, entity.field_70163_u + entity.func_70047_e() / 2.0F, entity.field_70161_v, item.func_77946_l());
/*     */           
/*     */ 
/*     */ 
/* 481 */           world.func_72838_d(entityItem);
/* 482 */           inventory.func_70299_a(i, null);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static int isWandInHotbarWithRoom(Aspect aspect, int amount, EntityPlayer player) {
/* 489 */     for (int var2 = 0; var2 < InventoryPlayer.func_70451_h(); var2++)
/*     */     {
/* 491 */       if ((player.field_71071_by.field_70462_a[var2] != null) && ((player.field_71071_by.field_70462_a[var2].func_77973_b() instanceof ItemWandCasting)))
/*     */       {
/*     */ 
/* 494 */         ItemWandCasting wand = (ItemWandCasting)player.field_71071_by.field_70462_a[var2].func_77973_b();
/* 495 */         if (wand.addVis(player.field_71071_by.field_70462_a[var2], aspect, amount, false) < amount) {
/* 496 */           return var2;
/*     */         }
/*     */       }
/*     */     }
/* 500 */     return -1;
/*     */   }
/*     */   
/*     */   public static int isPlayerCarrying(EntityPlayer player, ItemStack stack)
/*     */   {
/* 505 */     for (int var2 = 0; var2 < player.field_71071_by.field_70462_a.length; var2++)
/*     */     {
/* 507 */       if ((player.field_71071_by.field_70462_a[var2] != null) && (player.field_71071_by.field_70462_a[var2].func_77969_a(stack)))
/*     */       {
/*     */ 
/* 510 */         return var2;
/*     */       }
/*     */     }
/*     */     
/* 514 */     return -1;
/*     */   }
/*     */   
/*     */   public static ItemStack damageItem(int par1, ItemStack stack, World worldObj) {
/* 518 */     if (stack.func_77984_f())
/*     */     {
/* 520 */       if (par1 > 0)
/*     */       {
/* 522 */         int var3 = EnchantmentHelper.func_77506_a(Enchantment.field_77347_r.field_77352_x, stack);
/* 523 */         int var4 = 0;
/*     */         
/* 525 */         for (int var5 = 0; (var3 > 0) && (var5 < par1); var5++)
/*     */         {
/* 527 */           if (EnchantmentDurability.func_92097_a(stack, var3, worldObj.field_73012_v))
/*     */           {
/* 529 */             var4++;
/*     */           }
/*     */         }
/*     */         
/* 533 */         par1 -= var4;
/*     */         
/* 535 */         if (par1 <= 0)
/*     */         {
/* 537 */           return stack;
/*     */         }
/*     */       }
/*     */       
/* 541 */       stack.func_77964_b(stack.func_77960_j() + par1);
/*     */       
/* 543 */       if (stack.func_77960_j() > stack.func_77958_k())
/*     */       {
/* 545 */         stack.field_77994_a -= 1;
/* 546 */         if (stack.field_77994_a < 0)
/*     */         {
/* 548 */           stack.field_77994_a = 0;
/*     */         }
/*     */         
/* 551 */         stack.func_77964_b(0);
/*     */       }
/*     */     }
/*     */     
/* 555 */     return stack;
/*     */   }
/*     */   
/*     */   public static void dropItemsWithChance(World world, int x, int y, int z, float chance, int fortune, ArrayList<ItemStack> items) {
/* 559 */     for (ItemStack item : items)
/*     */     {
/* 561 */       if ((world.field_73012_v.nextFloat() <= chance) && (item.field_77994_a > 0))
/*     */       {
/* 563 */         if ((!world.field_72995_K) && (world.func_82736_K().func_82766_b("doTileDrops")))
/*     */         {
/* 565 */           float var6 = 0.7F;
/* 566 */           double var7 = world.field_73012_v.nextFloat() * var6 + (1.0F - var6) * 0.5D;
/* 567 */           double var9 = world.field_73012_v.nextFloat() * var6 + (1.0F - var6) * 0.5D;
/* 568 */           double var11 = world.field_73012_v.nextFloat() * var6 + (1.0F - var6) * 0.5D;
/* 569 */           EntityItem var13 = new EntityItem(world, x + var7, y + var9, z + var11, item);
/* 570 */           var13.field_145804_b = 10;
/* 571 */           world.func_72838_d(var13);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static TileEntityChest getDoubleChest(TileEntity tile) {
/* 578 */     if ((tile != null) && ((tile instanceof TileEntityChest))) {
/* 579 */       if (((TileEntityChest)tile).field_145991_k != null) return ((TileEntityChest)tile).field_145991_k;
/* 580 */       if (((TileEntityChest)tile).field_145990_j != null) return ((TileEntityChest)tile).field_145990_j;
/* 581 */       if (((TileEntityChest)tile).field_145992_i != null) return ((TileEntityChest)tile).field_145992_i;
/* 582 */       if (((TileEntityChest)tile).field_145988_l != null) return ((TileEntityChest)tile).field_145988_l;
/*     */     }
/* 584 */     return null;
/*     */   }
/*     */   
/*     */   public static ItemStack cycleItemStack(Object input) {
/* 588 */     ItemStack it = null;
/* 589 */     if ((input instanceof ItemStack)) {
/* 590 */       it = (ItemStack)input;
/* 591 */       if ((it.func_77960_j() == 32767) && (it.func_77973_b().func_77614_k())) {
/* 592 */         List<ItemStack> q = new ArrayList();
/* 593 */         it.func_77973_b().func_150895_a(it.func_77973_b(), it.func_77973_b().func_77640_w(), q);
/* 594 */         if ((q != null) && (q.size() > 0)) {
/* 595 */           int md = (int)(System.currentTimeMillis() / 1000L % q.size());
/* 596 */           ItemStack it2 = new ItemStack(it.func_77973_b(), 1, md);
/* 597 */           it2.func_77982_d(it.func_77978_p());
/* 598 */           it = it2;
/*     */         }
/*     */       }
/* 601 */       else if ((it.func_77960_j() == 32767) && (it.func_77984_f())) {
/* 602 */         int md = (int)(System.currentTimeMillis() / 10L % it.func_77958_k());
/* 603 */         ItemStack it2 = new ItemStack(it.func_77973_b(), 1, md);
/* 604 */         it2.func_77982_d(it.func_77978_p());
/* 605 */         it = it2;
/*     */       }
/*     */       
/*     */     }
/* 609 */     else if ((input instanceof ArrayList)) {
/* 610 */       ArrayList<ItemStack> q = (ArrayList)input;
/* 611 */       if ((q != null) && (q.size() > 0)) {
/* 612 */         int idx = (int)(System.currentTimeMillis() / 1000L % q.size());
/* 613 */         it = cycleItemStack(q.get(idx));
/*     */       }
/*     */     }
/* 616 */     else if ((input instanceof String)) {
/* 617 */       ArrayList<ItemStack> q = OreDictionary.getOres((String)input);
/* 618 */       if ((q != null) && (q.size() > 0)) {
/* 619 */         int idx = (int)(System.currentTimeMillis() / 1000L % q.size());
/* 620 */         it = cycleItemStack(q.get(idx));
/*     */       }
/*     */     }
/*     */     
/* 624 */     return it;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/utils/InventoryUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */