/*     */ package thaumcraft.api;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.internal.IInternalMethodHandler;
/*     */ 
/*     */ public class ThaumcraftApiHelper
/*     */ {
/*     */   public static AspectList cullTags(AspectList temp)
/*     */   {
/*  25 */     AspectList temp2 = new AspectList();
/*  26 */     for (Aspect tag : temp.getAspects()) {
/*  27 */       if (tag != null)
/*  28 */         temp2.add(tag, temp.getAmount(tag));
/*     */     }
/*  30 */     while ((temp2 != null) && (temp2.size() > 6)) {
/*  31 */       Aspect lowest = null;
/*  32 */       float low = 32767.0F;
/*  33 */       for (Aspect tag : temp2.getAspects())
/*  34 */         if (tag != null) {
/*  35 */           float ta = temp2.getAmount(tag);
/*  36 */           if (tag.isPrimal()) {
/*  37 */             ta *= 0.9F;
/*     */           } else {
/*  39 */             if (!tag.getComponents()[0].isPrimal()) {
/*  40 */               ta *= 1.1F;
/*  41 */               if (!tag.getComponents()[0].getComponents()[0].isPrimal()) {
/*  42 */                 ta *= 1.05F;
/*     */               }
/*  44 */               if (!tag.getComponents()[0].getComponents()[1].isPrimal()) {
/*  45 */                 ta *= 1.05F;
/*     */               }
/*     */             }
/*  48 */             if (!tag.getComponents()[1].isPrimal()) {
/*  49 */               ta *= 1.1F;
/*  50 */               if (!tag.getComponents()[1].getComponents()[0].isPrimal()) {
/*  51 */                 ta *= 1.05F;
/*     */               }
/*  53 */               if (!tag.getComponents()[1].getComponents()[1].isPrimal()) {
/*  54 */                 ta *= 1.05F;
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*  59 */           if (ta < low) {
/*  60 */             low = ta;
/*  61 */             lowest = tag;
/*     */           }
/*     */         }
/*  64 */       temp2.aspects.remove(lowest);
/*     */     }
/*  66 */     return temp2;
/*     */   }
/*     */   
/*     */   public static boolean areItemsEqual(ItemStack s1, ItemStack s2)
/*     */   {
/*  71 */     if ((s1.func_77984_f()) && (s2.func_77984_f()))
/*     */     {
/*  73 */       return s1.func_77973_b() == s2.func_77973_b();
/*     */     }
/*  75 */     return (s1.func_77973_b() == s2.func_77973_b()) && (s1.func_77960_j() == s2.func_77960_j());
/*     */   }
/*     */   
/*     */   public static boolean isResearchComplete(String username, String researchkey) {
/*  79 */     return ThaumcraftApi.internalMethods.isResearchComplete(username, researchkey);
/*     */   }
/*     */   
/*     */   public static boolean hasDiscoveredAspect(String username, Aspect aspect) {
/*  83 */     return ThaumcraftApi.internalMethods.hasDiscoveredAspect(username, aspect);
/*     */   }
/*     */   
/*     */   public static AspectList getDiscoveredAspects(String username) {
/*  87 */     return ThaumcraftApi.internalMethods.getDiscoveredAspects(username);
/*     */   }
/*     */   
/*     */   public static ItemStack getStackInRowAndColumn(Object instance, int row, int column) {
/*  91 */     return ThaumcraftApi.internalMethods.getStackInRowAndColumn(instance, row, column);
/*     */   }
/*     */   
/*     */   public static AspectList getObjectAspects(ItemStack is) {
/*  95 */     return ThaumcraftApi.internalMethods.getObjectAspects(is);
/*     */   }
/*     */   
/*     */   public static AspectList getBonusObjectTags(ItemStack is, AspectList ot) {
/*  99 */     return ThaumcraftApi.internalMethods.getBonusObjectTags(is, ot);
/*     */   }
/*     */   
/*     */   public static AspectList generateTags(net.minecraft.item.Item item, int meta) {
/* 103 */     return ThaumcraftApi.internalMethods.generateTags(item, meta);
/*     */   }
/*     */   
/*     */   public static boolean containsMatch(boolean strict, ItemStack[] inputs, ItemStack... targets)
/*     */   {
/* 108 */     for (ItemStack input : inputs)
/*     */     {
/* 110 */       for (ItemStack target : targets)
/*     */       {
/* 112 */         if (itemMatches(target, input, strict))
/*     */         {
/* 114 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean areItemStackTagsEqualForCrafting(ItemStack slotItem, ItemStack recipeItem)
/*     */   {
/* 123 */     if ((recipeItem == null) || (slotItem == null)) return false;
/* 124 */     if ((recipeItem.field_77990_d != null) && (slotItem.field_77990_d == null)) return false;
/* 125 */     if (recipeItem.field_77990_d == null) { return true;
/*     */     }
/* 127 */     Iterator iterator = recipeItem.field_77990_d.func_150296_c().iterator();
/* 128 */     while (iterator.hasNext())
/*     */     {
/* 130 */       String s = (String)iterator.next();
/* 131 */       if (slotItem.field_77990_d.func_74764_b(s)) {
/* 132 */         if (!slotItem.field_77990_d.func_74781_a(s).toString().equals(recipeItem.field_77990_d.func_74781_a(s).toString()))
/*     */         {
/* 134 */           return false;
/*     */         }
/*     */       } else {
/* 137 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 141 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean itemMatches(ItemStack target, ItemStack input, boolean strict)
/*     */   {
/* 146 */     if (((input == null) && (target != null)) || ((input != null) && (target == null)))
/*     */     {
/* 148 */       return false;
/*     */     }
/* 150 */     return (target.func_77973_b() == input.func_77973_b()) && (((target.func_77960_j() == 32767) && (!strict)) || (target.func_77960_j() == input.func_77960_j()));
/*     */   }
/*     */   
/*     */ 
/*     */   public static TileEntity getConnectableTile(World world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 156 */     TileEntity te = world.func_147438_o(x + face.offsetX, y + face.offsetY, z + face.offsetZ);
/* 157 */     if (((te instanceof IEssentiaTransport)) && (((IEssentiaTransport)te).isConnectable(face.getOpposite()))) {
/* 158 */       return te;
/*     */     }
/* 160 */     return null;
/*     */   }
/*     */   
/*     */   public static TileEntity getConnectableTile(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
/* 164 */     TileEntity te = world.func_147438_o(x + face.offsetX, y + face.offsetY, z + face.offsetZ);
/* 165 */     if (((te instanceof IEssentiaTransport)) && (((IEssentiaTransport)te).isConnectable(face.getOpposite()))) {
/* 166 */       return te;
/*     */     }
/* 168 */     return null;
/*     */   }
/*     */   
/* 171 */   private static HashMap<Integer, AspectList> allAspects = new HashMap();
/* 172 */   private static HashMap<Integer, AspectList> allCompoundAspects = new HashMap();
/*     */   
/*     */   public static AspectList getAllAspects(int amount) {
/* 175 */     if (allAspects.get(Integer.valueOf(amount)) == null) {
/* 176 */       AspectList al = new AspectList();
/* 177 */       for (Aspect aspect : Aspect.aspects.values()) {
/* 178 */         al.add(aspect, amount);
/*     */       }
/* 180 */       allAspects.put(Integer.valueOf(amount), al);
/*     */     }
/* 182 */     return (AspectList)allAspects.get(Integer.valueOf(amount));
/*     */   }
/*     */   
/*     */   public static AspectList getAllCompoundAspects(int amount) {
/* 186 */     if (allCompoundAspects.get(Integer.valueOf(amount)) == null) {
/* 187 */       AspectList al = new AspectList();
/* 188 */       for (Aspect aspect : Aspect.getCompoundAspects()) {
/* 189 */         al.add(aspect, amount);
/*     */       }
/* 191 */       allCompoundAspects.put(Integer.valueOf(amount), al);
/*     */     }
/* 193 */     return (AspectList)allCompoundAspects.get(Integer.valueOf(amount));
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
/*     */   public static boolean consumeVisFromWand(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit, boolean crafting)
/*     */   {
/* 210 */     return ThaumcraftApi.internalMethods.consumeVisFromWand(wand, player, cost, doit, crafting);
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
/*     */   public static boolean consumeVisFromWandCrafting(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit)
/*     */   {
/* 225 */     return ThaumcraftApi.internalMethods.consumeVisFromWandCrafting(wand, player, cost, doit);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean consumeVisFromInventory(EntityPlayer player, AspectList cost)
/*     */   {
/* 237 */     return ThaumcraftApi.internalMethods.consumeVisFromInventory(player, cost);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addWarpToPlayer(EntityPlayer player, int amount, boolean temporary)
/*     */   {
/* 248 */     ThaumcraftApi.internalMethods.addWarpToPlayer(player, amount, temporary);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addStickyWarpToPlayer(EntityPlayer player, int amount)
/*     */   {
/* 258 */     ThaumcraftApi.internalMethods.addStickyWarpToPlayer(player, amount);
/*     */   }
/*     */   
/*     */ 
/*     */   public static MovingObjectPosition rayTraceIgnoringSource(World world, Vec3 v1, Vec3 v2, boolean bool1, boolean bool2, boolean bool3)
/*     */   {
/* 264 */     if ((!Double.isNaN(v1.field_72450_a)) && (!Double.isNaN(v1.field_72448_b)) && (!Double.isNaN(v1.field_72449_c)))
/*     */     {
/* 266 */       if ((!Double.isNaN(v2.field_72450_a)) && (!Double.isNaN(v2.field_72448_b)) && (!Double.isNaN(v2.field_72449_c)))
/*     */       {
/* 268 */         int i = MathHelper.func_76128_c(v2.field_72450_a);
/* 269 */         int j = MathHelper.func_76128_c(v2.field_72448_b);
/* 270 */         int k = MathHelper.func_76128_c(v2.field_72449_c);
/* 271 */         int l = MathHelper.func_76128_c(v1.field_72450_a);
/* 272 */         int i1 = MathHelper.func_76128_c(v1.field_72448_b);
/* 273 */         int j1 = MathHelper.func_76128_c(v1.field_72449_c);
/* 274 */         Block block = world.func_147439_a(l, i1, j1);
/* 275 */         int k1 = world.func_72805_g(l, i1, j1);
/*     */         
/* 277 */         MovingObjectPosition movingobjectposition2 = null;
/* 278 */         k1 = 200;
/*     */         
/* 280 */         while (k1-- >= 0)
/*     */         {
/* 282 */           if ((Double.isNaN(v1.field_72450_a)) || (Double.isNaN(v1.field_72448_b)) || (Double.isNaN(v1.field_72449_c)))
/*     */           {
/* 284 */             return null;
/*     */           }
/*     */           
/* 287 */           if ((l != i) || (i1 != j) || (j1 != k))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/* 292 */             boolean flag6 = true;
/* 293 */             boolean flag3 = true;
/* 294 */             boolean flag4 = true;
/* 295 */             double d0 = 999.0D;
/* 296 */             double d1 = 999.0D;
/* 297 */             double d2 = 999.0D;
/*     */             
/* 299 */             if (i > l)
/*     */             {
/* 301 */               d0 = l + 1.0D;
/*     */             }
/* 303 */             else if (i < l)
/*     */             {
/* 305 */               d0 = l + 0.0D;
/*     */             }
/*     */             else
/*     */             {
/* 309 */               flag6 = false;
/*     */             }
/*     */             
/* 312 */             if (j > i1)
/*     */             {
/* 314 */               d1 = i1 + 1.0D;
/*     */             }
/* 316 */             else if (j < i1)
/*     */             {
/* 318 */               d1 = i1 + 0.0D;
/*     */             }
/*     */             else
/*     */             {
/* 322 */               flag3 = false;
/*     */             }
/*     */             
/* 325 */             if (k > j1)
/*     */             {
/* 327 */               d2 = j1 + 1.0D;
/*     */             }
/* 329 */             else if (k < j1)
/*     */             {
/* 331 */               d2 = j1 + 0.0D;
/*     */             }
/*     */             else
/*     */             {
/* 335 */               flag4 = false;
/*     */             }
/*     */             
/* 338 */             double d3 = 999.0D;
/* 339 */             double d4 = 999.0D;
/* 340 */             double d5 = 999.0D;
/* 341 */             double d6 = v2.field_72450_a - v1.field_72450_a;
/* 342 */             double d7 = v2.field_72448_b - v1.field_72448_b;
/* 343 */             double d8 = v2.field_72449_c - v1.field_72449_c;
/*     */             
/* 345 */             if (flag6)
/*     */             {
/* 347 */               d3 = (d0 - v1.field_72450_a) / d6;
/*     */             }
/*     */             
/* 350 */             if (flag3)
/*     */             {
/* 352 */               d4 = (d1 - v1.field_72448_b) / d7;
/*     */             }
/*     */             
/* 355 */             if (flag4)
/*     */             {
/* 357 */               d5 = (d2 - v1.field_72449_c) / d8;
/*     */             }
/*     */             
/* 360 */             boolean flag5 = false;
/*     */             
/*     */             byte b0;
/* 363 */             if ((d3 < d4) && (d3 < d5)) { byte b0;
/*     */               byte b0;
/* 365 */               if (i > l)
/*     */               {
/* 367 */                 b0 = 4;
/*     */               }
/*     */               else
/*     */               {
/* 371 */                 b0 = 5;
/*     */               }
/*     */               
/* 374 */               v1.field_72450_a = d0;
/* 375 */               v1.field_72448_b += d7 * d3;
/* 376 */               v1.field_72449_c += d8 * d3;
/*     */             }
/* 378 */             else if (d4 < d5) { byte b0;
/*     */               byte b0;
/* 380 */               if (j > i1)
/*     */               {
/* 382 */                 b0 = 0;
/*     */               }
/*     */               else
/*     */               {
/* 386 */                 b0 = 1;
/*     */               }
/*     */               
/* 389 */               v1.field_72450_a += d6 * d4;
/* 390 */               v1.field_72448_b = d1;
/* 391 */               v1.field_72449_c += d8 * d4;
/*     */             }
/*     */             else {
/*     */               byte b0;
/* 395 */               if (k > j1)
/*     */               {
/* 397 */                 b0 = 2;
/*     */               }
/*     */               else
/*     */               {
/* 401 */                 b0 = 3;
/*     */               }
/*     */               
/* 404 */               v1.field_72450_a += d6 * d5;
/* 405 */               v1.field_72448_b += d7 * d5;
/* 406 */               v1.field_72449_c = d2;
/*     */             }
/*     */             
/* 409 */             Vec3 vec32 = Vec3.func_72443_a(v1.field_72450_a, v1.field_72448_b, v1.field_72449_c);
/* 410 */             l = (int)(vec32.field_72450_a = MathHelper.func_76128_c(v1.field_72450_a));
/*     */             
/* 412 */             if (b0 == 5)
/*     */             {
/* 414 */               l--;
/* 415 */               vec32.field_72450_a += 1.0D;
/*     */             }
/*     */             
/* 418 */             i1 = (int)(vec32.field_72448_b = MathHelper.func_76128_c(v1.field_72448_b));
/*     */             
/* 420 */             if (b0 == 1)
/*     */             {
/* 422 */               i1--;
/* 423 */               vec32.field_72448_b += 1.0D;
/*     */             }
/*     */             
/* 426 */             j1 = (int)(vec32.field_72449_c = MathHelper.func_76128_c(v1.field_72449_c));
/*     */             
/* 428 */             if (b0 == 3)
/*     */             {
/* 430 */               j1--;
/* 431 */               vec32.field_72449_c += 1.0D;
/*     */             }
/*     */             
/* 434 */             Block block1 = world.func_147439_a(l, i1, j1);
/* 435 */             int l1 = world.func_72805_g(l, i1, j1);
/*     */             
/* 437 */             if ((!bool2) || (block1.func_149668_a(world, l, i1, j1) != null))
/*     */             {
/* 439 */               if (block1.func_149678_a(l1, bool1))
/*     */               {
/* 441 */                 MovingObjectPosition movingobjectposition1 = block1.func_149731_a(world, l, i1, j1, v1, v2);
/*     */                 
/* 443 */                 if (movingobjectposition1 != null)
/*     */                 {
/* 445 */                   return movingobjectposition1;
/*     */                 }
/*     */               }
/*     */               else
/*     */               {
/* 450 */                 movingobjectposition2 = new MovingObjectPosition(l, i1, j1, b0, v1, false);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 455 */         return bool3 ? movingobjectposition2 : null;
/*     */       }
/*     */       
/*     */ 
/* 459 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 464 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/ThaumcraftApiHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */