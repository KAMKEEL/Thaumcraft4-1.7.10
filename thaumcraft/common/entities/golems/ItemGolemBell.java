/*     */ package thaumcraft.common.entities.golems;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.nbt.NBTTagByteArray;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ 
/*     */ public class ItemGolemBell extends Item
/*     */ {
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemGolemBell()
/*     */   {
/*  35 */     func_77627_a(false);
/*  36 */     func_77637_a(Thaumcraft.tabTC);
/*  37 */     func_77625_d(1);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  44 */     this.icon = ir.func_94245_a("thaumcraft:ironbell");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  50 */     return this.icon;
/*     */   }
/*     */   
/*     */   public boolean func_77651_p()
/*     */   {
/*  55 */     return true;
/*     */   }
/*     */   
/*     */   public static int getGolemId(ItemStack stack)
/*     */   {
/*  60 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("golemid")))
/*  61 */       return stack.field_77990_d.func_74762_e("golemid");
/*  62 */     return -1;
/*     */   }
/*     */   
/*     */   public static int getGolemHomeFace(ItemStack stack) {
/*  66 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("golemhomeface")))
/*  67 */       return stack.field_77990_d.func_74762_e("golemhomeface");
/*  68 */     return -1;
/*     */   }
/*     */   
/*     */   public static ChunkCoordinates getGolemHomeCoords(ItemStack stack) {
/*  72 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("golemhomex"))) {
/*  73 */       return new ChunkCoordinates(stack.field_77990_d.func_74762_e("golemhomex"), stack.field_77990_d.func_74762_e("golemhomey"), stack.field_77990_d.func_74762_e("golemhomez"));
/*     */     }
/*     */     
/*     */ 
/*  77 */     return null;
/*     */   }
/*     */   
/*     */   public static ArrayList<Marker> getMarkers(ItemStack stack) {
/*  81 */     ArrayList<Marker> markers = new ArrayList();
/*  82 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("markers"))) {
/*  83 */       NBTTagList tl = stack.field_77990_d.func_150295_c("markers", 10);
/*  84 */       for (int i = 0; i < tl.func_74745_c(); i++)
/*     */       {
/*  86 */         NBTTagCompound nbttagcompound1 = tl.func_150305_b(i);
/*  87 */         int x = nbttagcompound1.func_74762_e("x");
/*  88 */         int y = nbttagcompound1.func_74762_e("y");
/*  89 */         int z = nbttagcompound1.func_74762_e("z");
/*  90 */         int dim = nbttagcompound1.func_74762_e("dim");
/*  91 */         byte s = nbttagcompound1.func_74771_c("side");
/*  92 */         byte c = nbttagcompound1.func_74771_c("color");
/*  93 */         markers.add(new Marker(x, y, z, (byte)dim, s, c));
/*     */       }
/*     */     }
/*  96 */     return markers;
/*     */   }
/*     */   
/*     */   public static void resetMarkers(ItemStack stack, World world, EntityPlayer player) {
/* 100 */     Entity golem = null;
/* 101 */     int gid = getGolemId(stack);
/* 102 */     if (gid > -1) {
/* 103 */       golem = world.func_73045_a(gid);
/* 104 */       if ((golem != null) && ((golem instanceof EntityGolemBase))) {
/* 105 */         stack.func_77983_a("markers", new NBTTagList());
/* 106 */         ((EntityGolemBase)golem).setMarkers(new ArrayList());
/* 107 */         world.func_72956_a(player, "random.orb", 0.7F, 1.0F + world.field_73012_v.nextFloat() * 0.1F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void changeMarkers(ItemStack stack, EntityPlayer player, World world, int par4, int par5, int par6, int side)
/*     */   {
/* 114 */     Entity golem = null;
/* 115 */     ArrayList<Marker> markers = getMarkers(stack);
/* 116 */     boolean markMultipleColors = false;
/* 117 */     int gid = getGolemId(stack);
/* 118 */     if (gid > -1) {
/* 119 */       golem = world.func_73045_a(gid);
/* 120 */       if ((golem != null) && ((golem instanceof EntityGolemBase)) && 
/* 121 */         (((EntityGolemBase)golem).getUpgradeAmount(4) > 0)) {
/* 122 */         markMultipleColors = true;
/*     */       }
/*     */     }
/*     */     
/* 126 */     int count = markers.size();
/* 127 */     int index = -1;
/* 128 */     int color = 0;
/* 129 */     if (!markMultipleColors) {
/* 130 */       index = markers.indexOf(new Marker(par4, par5, par6, world.field_73011_w.field_76574_g, (byte)side, (byte)-1));
/*     */     } else
/* 132 */       for (int a = -1; a < 16; a++) {
/* 133 */         index = markers.indexOf(new Marker(par4, par5, par6, world.field_73011_w.field_76574_g, (byte)side, (byte)a));
/* 134 */         color = a;
/* 135 */         if (index != -1)
/*     */           break;
/*     */       }
/* 138 */     if (index >= 0) {
/* 139 */       markers.remove(index);
/*     */       
/* 141 */       if ((markMultipleColors) && (!player.func_70093_af())) {
/* 142 */         color++;
/* 143 */         if (color <= 15) {
/* 144 */           markers.add(new Marker(par4, par5, par6, world.field_73011_w.field_76574_g, (byte)side, (byte)color));
/* 145 */           count++;
/* 146 */           if (world.field_72995_K) {
/* 147 */             String text = StatCollector.func_74838_a("tc.markerchange");
/*     */             
/* 149 */             if (color > -1) {
/* 150 */               text = text.replaceAll("%n", thaumcraft.client.lib.UtilsFX.colorNames[color]);
/*     */             } else {
/* 152 */               text = StatCollector.func_74838_a("tc.markerchangeany");
/*     */             }
/* 154 */             thaumcraft.client.lib.PlayerNotifications.addNotification(text);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 160 */       markers.add(new Marker(par4, par5, par6, world.field_73011_w.field_76574_g, (byte)side, (byte)-1));
/*     */     }
/* 162 */     if (count != markers.size()) {
/* 163 */       NBTTagList tl = new NBTTagList();
/* 164 */       for (Marker l : markers) {
/* 165 */         NBTTagCompound nbtc = new NBTTagCompound();
/* 166 */         nbtc.func_74768_a("x", l.x);
/* 167 */         nbtc.func_74768_a("y", l.y);
/* 168 */         nbtc.func_74768_a("z", l.z);
/* 169 */         nbtc.func_74768_a("dim", l.dim);
/* 170 */         nbtc.func_74774_a("side", l.side);
/* 171 */         nbtc.func_74774_a("color", l.color);
/* 172 */         tl.func_74742_a(nbtc);
/*     */       }
/* 174 */       stack.func_77983_a("markers", tl);
/*     */       
/* 176 */       if (gid > -1) {
/* 177 */         if ((golem == null) || (!(golem instanceof EntityGolemBase))) {
/* 178 */           stack.func_77978_p().func_82580_o("golemid");
/* 179 */           stack.func_77978_p().func_82580_o("markers");
/* 180 */           stack.func_77978_p().func_82580_o("golemhomex");
/* 181 */           stack.func_77978_p().func_82580_o("golemhomey");
/* 182 */           stack.func_77978_p().func_82580_o("golemhomez");
/* 183 */           stack.func_77978_p().func_82580_o("golemhomeface");
/*     */         } else {
/* 185 */           ((EntityGolemBase)golem).setMarkers(markers);
/*     */         }
/*     */       }
/*     */     }
/* 189 */     world.func_72908_a(par4, par5, par6, "random.orb", 0.7F, 1.0F + world.field_73012_v.nextFloat() * 0.1F);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int par4, int par5, int par6, int side, float par8, float par9, float par10)
/*     */   {
/* 195 */     MovingObjectPosition movingobjectposition = func_77621_a(world, player, true);
/*     */     
/* 197 */     if (movingobjectposition == null)
/*     */     {
/* 199 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 203 */     if (movingobjectposition.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK)
/*     */     {
/* 205 */       int i = movingobjectposition.field_72311_b;
/* 206 */       int j = movingobjectposition.field_72312_c;
/* 207 */       int k = movingobjectposition.field_72309_d;
/*     */       
/* 209 */       changeMarkers(stack, player, world, i, j, k, movingobjectposition.field_72310_e);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 218 */     if (world.field_72995_K) return false;
/* 219 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_111207_a(ItemStack stack, EntityPlayer player, EntityLivingBase target)
/*     */   {
/* 226 */     if ((target instanceof EntityGolemBase))
/*     */     {
/* 228 */       if (stack.func_77942_o()) {
/* 229 */         stack.func_77978_p().func_82580_o("golemid");
/* 230 */         stack.func_77978_p().func_82580_o("markers");
/* 231 */         stack.func_77978_p().func_82580_o("golemhomex");
/* 232 */         stack.func_77978_p().func_82580_o("golemhomey");
/* 233 */         stack.func_77978_p().func_82580_o("golemhomez");
/* 234 */         stack.func_77978_p().func_82580_o("golemhomeface");
/*     */       }
/*     */       
/* 237 */       if (target.field_70170_p.field_72995_K) {
/* 238 */         if (player != null) player.func_71038_i();
/*     */       } else {
/* 240 */         ArrayList<Marker> markers = ((EntityGolemBase)target).getMarkers();
/* 241 */         NBTTagList tl = new NBTTagList();
/* 242 */         for (Marker l : markers) {
/* 243 */           NBTTagCompound nbtc = new NBTTagCompound();
/* 244 */           nbtc.func_74768_a("x", l.x);
/* 245 */           nbtc.func_74768_a("y", l.y);
/* 246 */           nbtc.func_74768_a("z", l.z);
/* 247 */           nbtc.func_74768_a("dim", l.dim);
/* 248 */           nbtc.func_74774_a("side", l.side);
/* 249 */           nbtc.func_74774_a("color", l.color);
/* 250 */           tl.func_74742_a(nbtc);
/*     */         }
/*     */         
/* 253 */         stack.func_77983_a("markers", tl);
/* 254 */         stack.func_77978_p().func_74768_a("golemid", target.func_145782_y());
/* 255 */         stack.func_77978_p().func_74768_a("golemhomex", ((EntityGolemBase)target).func_110172_bL().field_71574_a);
/* 256 */         stack.func_77978_p().func_74768_a("golemhomey", ((EntityGolemBase)target).func_110172_bL().field_71572_b);
/* 257 */         stack.func_77978_p().func_74768_a("golemhomez", ((EntityGolemBase)target).func_110172_bL().field_71573_c);
/* 258 */         stack.func_77978_p().func_74768_a("golemhomeface", ((EntityGolemBase)target).homeFacing);
/* 259 */         target.field_70170_p.func_72956_a(target, "random.orb", 0.7F, 1.0F + target.field_70170_p.field_73012_v.nextFloat() * 0.1F);
/*     */         
/* 261 */         if ((player != null) && (player.field_71075_bZ.field_75098_d))
/*     */         {
/* 263 */           player.func_70062_b(0, stack.func_77946_l());
/*     */         }
/*     */       }
/* 266 */       return true;
/*     */     }
/* 268 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/*     */   {
/* 276 */     if (((entity instanceof EntityTravelingTrunk)) && (!entity.field_70128_L))
/*     */     {
/* 278 */       byte upgrade = (byte)((EntityTravelingTrunk)entity).getUpgrade();
/*     */       
/* 280 */       if ((upgrade == 3) && (!((EntityTravelingTrunk)entity).func_152113_b().equals(player.func_70005_c_()))) { return false;
/*     */       }
/* 282 */       if ((entity.field_70170_p.field_72995_K) && ((entity instanceof EntityLiving))) {
/* 283 */         ((EntityLiving)entity).func_70656_aK();
/* 284 */         return false;
/*     */       }
/*     */       
/* 287 */       ItemStack dropped = new ItemStack(ConfigItems.itemTrunkSpawner);
/*     */       
/* 289 */       if (player.func_70093_af()) {
/* 290 */         if ((upgrade > -1) && (entity.field_70170_p.field_73012_v.nextBoolean())) {
/* 291 */           ((EntityTravelingTrunk)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemUpgrade, 1, upgrade), 0.5F);
/*     */         }
/*     */       } else {
/* 294 */         if (((EntityTravelingTrunk)entity).func_94056_bM()) {
/* 295 */           dropped.func_151001_c(((EntityTravelingTrunk)entity).func_94057_bL());
/*     */         }
/*     */         
/* 298 */         dropped.func_77983_a("upgrade", new NBTTagByte(upgrade));
/*     */         
/* 300 */         if (upgrade == 4) {
/* 301 */           dropped.func_77983_a("inventory", ((EntityTravelingTrunk)entity).inventory.writeToNBT(new NBTTagList()));
/*     */         }
/*     */       }
/*     */       
/* 305 */       ((EntityTravelingTrunk)entity).func_70099_a(dropped, 0.5F);
/*     */       
/* 307 */       if ((upgrade != 4) || (player.func_70093_af())) {
/* 308 */         ((EntityTravelingTrunk)entity).inventory.dropAllItems();
/*     */       }
/*     */       
/* 311 */       entity.field_70170_p.func_72956_a(entity, "thaumcraft:zap", 0.5F, 1.0F);
/*     */       
/* 313 */       entity.func_70106_y();
/* 314 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 318 */     if (((entity instanceof EntityGolemBase)) && (!entity.field_70128_L))
/*     */     {
/* 320 */       if ((entity.field_70170_p.field_72995_K) && ((entity instanceof EntityLiving))) {
/* 321 */         ((EntityLiving)entity).func_70656_aK();
/* 322 */         return false;
/*     */       }
/*     */       
/* 325 */       int type = ((EntityGolemBase)entity).golemType.ordinal();
/* 326 */       String deco = ((EntityGolemBase)entity).decoration;
/* 327 */       byte core = ((EntityGolemBase)entity).getCore();
/* 328 */       byte[] upgrades = ((EntityGolemBase)entity).upgrades;
/* 329 */       boolean advanced = ((EntityGolemBase)entity).advanced;
/*     */       
/* 331 */       ItemStack dropped = new ItemStack(ConfigItems.itemGolemPlacer, 1, type);
/*     */       
/* 333 */       if (advanced) { dropped.func_77983_a("advanced", new NBTTagByte((byte)1));
/*     */       }
/* 335 */       if (player.func_70093_af()) {
/* 336 */         if (core > -1) {
/* 337 */           ((EntityGolemBase)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemCore, 1, core), 0.5F);
/*     */         }
/* 339 */         for (byte b : upgrades) {
/* 340 */           if ((b > -1) && (entity.field_70170_p.field_73012_v.nextBoolean()))
/* 341 */             ((EntityGolemBase)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemUpgrade, 1, b), 0.5F);
/*     */         }
/*     */       } else {
/* 344 */         if (((EntityGolemBase)entity).func_94056_bM()) {
/* 345 */           dropped.func_151001_c(((EntityGolemBase)entity).func_94057_bL());
/*     */         }
/*     */         
/* 348 */         if (deco.length() > 0) { dropped.func_77983_a("deco", new NBTTagString(deco));
/*     */         }
/* 350 */         if (core > -1) { dropped.func_77983_a("core", new NBTTagByte(core));
/*     */         }
/* 352 */         dropped.func_77983_a("upgrades", new NBTTagByteArray(upgrades));
/* 353 */         ArrayList<Marker> markers = ((EntityGolemBase)entity).getMarkers();
/* 354 */         NBTTagList tl = new NBTTagList();
/* 355 */         for (Marker l : markers) {
/* 356 */           NBTTagCompound nbtc = new NBTTagCompound();
/* 357 */           nbtc.func_74768_a("x", l.x);
/* 358 */           nbtc.func_74768_a("y", l.y);
/* 359 */           nbtc.func_74768_a("z", l.z);
/* 360 */           nbtc.func_74768_a("dim", l.dim);
/* 361 */           nbtc.func_74774_a("side", l.side);
/* 362 */           nbtc.func_74774_a("color", l.color);
/* 363 */           tl.func_74742_a(nbtc);
/*     */         }
/* 365 */         dropped.func_77983_a("markers", tl);
/* 366 */         dropped.func_77983_a("Inventory", ((EntityGolemBase)entity).inventory.writeToNBT(new NBTTagList()));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 371 */       ((EntityGolemBase)entity).func_70099_a(dropped, 0.5F);
/* 372 */       ((EntityGolemBase)entity).dropStuff();
/* 373 */       entity.field_70170_p.func_72956_a(entity, "thaumcraft:zap", 0.5F, 1.0F);
/*     */       
/* 375 */       entity.func_70106_y();
/* 376 */       return true;
/*     */     }
/* 378 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/ItemGolemBell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */