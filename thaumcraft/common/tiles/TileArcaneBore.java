/*      */ package thaumcraft.common.tiles;
/*      */ 
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*      */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.Block.SoundType;
/*      */ import net.minecraft.enchantment.Enchantment;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.item.ItemPickaxe;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import net.minecraftforge.common.util.FakePlayer;
/*      */ import net.minecraftforge.common.util.FakePlayerFactory;
/*      */ import net.minecraftforge.common.util.ForgeDirection;
/*      */ import thaumcraft.api.IRepairable;
/*      */ import thaumcraft.api.IRepairableExtended;
/*      */ import thaumcraft.api.TileThaumcraft;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.visnet.VisNetHandler;
/*      */ import thaumcraft.api.wands.FocusUpgradeType;
/*      */ import thaumcraft.api.wands.ItemFocusBasic;
/*      */ import thaumcraft.common.CommonProxy;
/*      */ import thaumcraft.common.Thaumcraft;
/*      */ import thaumcraft.common.config.Config;
/*      */ import thaumcraft.common.config.ConfigBlocks;
/*      */ import thaumcraft.common.items.equipment.ItemElementalPickaxe;
/*      */ import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
/*      */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*      */ import thaumcraft.common.lib.network.PacketHandler;
/*      */ import thaumcraft.common.lib.network.misc.PacketBoreDig;
/*      */ import thaumcraft.common.lib.research.ResearchManager;
/*      */ import thaumcraft.common.lib.utils.BlockUtils;
/*      */ import thaumcraft.common.lib.utils.InventoryUtils;
/*      */ import thaumcraft.common.lib.utils.TCVec3;
/*      */ import thaumcraft.common.lib.utils.Utils;
/*      */ 
/*      */ public class TileArcaneBore extends TileThaumcraft implements IInventory, thaumcraft.api.wands.IWandable
/*      */ {
/*   58 */   public int spiral = 0;
/*   59 */   public float currentRadius = 0.0F;
/*   60 */   public int maxRadius = 2;
/*      */   
/*      */ 
/*   63 */   public float vRadX = 0.0F;
/*   64 */   public float vRadZ = 0.0F;
/*   65 */   public float tRadX = 0.0F;
/*   66 */   public float tRadZ = 0.0F;
/*   67 */   public float mRadX = 0.0F;
/*   68 */   public float mRadZ = 0.0F;
/*   69 */   private int count = 0;
/*   70 */   public int topRotation = 0;
/*   71 */   long soundDelay = 0L;
/*   72 */   Object beam1 = null;
/*   73 */   Object beam2 = null;
/*   74 */   int beamlength = 0;
/*      */   
/*   76 */   TileArcaneBoreBase base = null;
/*      */   
/*      */ 
/*   79 */   public ItemStack[] contents = new ItemStack[2];
/*   80 */   public int rotX = 0;
/*   81 */   public int rotZ = 0;
/*   82 */   public int tarX = 0;
/*   83 */   public int tarZ = 0;
/*   84 */   public int speedX = 0;
/*   85 */   public int speedZ = 0;
/*   86 */   public boolean hasFocus = false;
/*   87 */   public boolean hasPickaxe = false;
/*   88 */   int lastX = 0;
/*   89 */   int lastZ = 0;
/*   90 */   int lastY = 0;
/*   91 */   boolean toDig = false;
/*   92 */   int digX = 0;
/*   93 */   int digZ = 0;
/*   94 */   int digY = 0;
/*   95 */   Block digBlock = Blocks.field_150350_a;
/*   96 */   int digMd = 0;
/*   97 */   float radInc = 0.0F;
/*   98 */   int paused = 100;
/*   99 */   int maxPause = 100;
/*  100 */   long repairCounter = 0L;
/*  101 */   boolean first = true;
/*      */   
/*  103 */   public ForgeDirection orientation = ForgeDirection.getOrientation(1);
/*  104 */   public ForgeDirection baseOrientation = ForgeDirection.getOrientation(1);
/*      */   
/*  106 */   FakePlayer fakePlayer = null;
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean canUpdate()
/*      */   {
/*  112 */     return true;
/*      */   }
/*      */   
/*      */   public void func_145845_h()
/*      */   {
/*  117 */     super.func_145845_h();
/*      */     
/*  119 */     if ((!this.field_145850_b.field_72995_K) && (this.speedyTime < 20.0F)) {
/*  120 */       this.speedyTime += VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.ENTROPY, 100) / 5.0F;
/*      */       
/*  122 */       if ((this.speedyTime < 20.0F) && (this.base != null) && (this.base.drawEssentia())) {
/*  123 */         getClass();this.speedyTime += 20.0F;
/*      */       }
/*      */     }
/*      */     
/*  127 */     if ((!this.field_145850_b.field_72995_K) && (this.fakePlayer == null)) {
/*  128 */       this.fakePlayer = FakePlayerFactory.get((WorldServer)this.field_145850_b, new GameProfile((UUID)null, "FakeThaumcraftBore"));
/*      */     }
/*      */     
/*  131 */     if ((this.field_145850_b.field_72995_K) && (this.first)) {
/*  132 */       setOrientation(this.orientation, true);
/*  133 */       this.first = false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  141 */     if (this.rotX < this.tarX) {
/*  142 */       this.rotX += this.speedX;
/*  143 */       if (this.rotX < this.tarX) {
/*  144 */         this.speedX += 1;
/*      */       } else
/*  146 */         this.speedX = ((int)(this.speedX / 3.0F));
/*  147 */     } else if (this.rotX > this.tarX) {
/*  148 */       this.rotX += this.speedX;
/*  149 */       if (this.rotX > this.tarX) {
/*  150 */         this.speedX -= 1;
/*      */       } else
/*  152 */         this.speedX = ((int)(this.speedX / 3.0F));
/*      */     } else {
/*  154 */       this.speedX = 0;
/*      */     }
/*  156 */     if (this.rotZ < this.tarZ) {
/*  157 */       this.rotZ += this.speedZ;
/*  158 */       if (this.rotZ < this.tarZ) {
/*  159 */         this.speedZ += 1;
/*      */       } else
/*  161 */         this.speedZ = ((int)(this.speedZ / 3.0F));
/*  162 */     } else if (this.rotZ > this.tarZ) {
/*  163 */       this.rotZ += this.speedZ;
/*  164 */       if (this.rotZ > this.tarZ) {
/*  165 */         this.speedZ -= 1;
/*      */       } else
/*  167 */         this.speedZ = ((int)(this.speedZ / 3.0F));
/*      */     } else {
/*  169 */       this.speedZ = 0;
/*      */     }
/*  171 */     if ((gettingPower()) && (areItemsValid()))
/*      */     {
/*  173 */       dig();
/*      */ 
/*      */     }
/*  176 */     else if (this.field_145850_b.field_72995_K) {
/*  177 */       if (this.topRotation % 90 != 0)
/*  178 */         this.topRotation += Math.min(10, 90 - this.topRotation % 90);
/*  179 */       this.vRadX *= 0.9F;
/*  180 */       this.vRadZ *= 0.9F;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  185 */     if ((!this.field_145850_b.field_72995_K) && (this.hasPickaxe) && (func_70301_a(1) != null)) {
/*  186 */       if ((this.repairCounter++ % 40L == 0L) && (func_70301_a(1).func_77951_h())) {
/*  187 */         doRepair(func_70301_a(1), this.fakePlayer);
/*      */       }
/*      */       
/*  190 */       if ((this.repairCost != null) && (this.repairCost.size() > 0) && (this.repairCounter % 5L == 0L)) {
/*  191 */         for (Aspect a : this.repairCost.getAspects()) {
/*  192 */           if (this.currentRepairVis.getAmount(a) < this.repairCost.getAmount(a)) {
/*  193 */             this.currentRepairVis.add(a, VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, a, this.repairCost.getAmount(a)));
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  199 */       this.fakePlayer.field_70173_aa = ((int)this.repairCounter);
/*      */       try {
/*  201 */         func_70301_a(1).func_77945_a(this.field_145850_b, this.fakePlayer, 0, true);
/*      */       }
/*      */       catch (Exception e) {}
/*      */     }
/*      */   }
/*      */   
/*  207 */   private AspectList repairCost = new AspectList();
/*  208 */   private AspectList currentRepairVis = new AspectList();
/*      */   
/*      */   private void doRepair(ItemStack is, EntityPlayer player) {
/*  211 */     int level = EnchantmentHelper.func_77506_a(Config.enchRepair.field_77352_x, is);
/*  212 */     if (level <= 0) return;
/*  213 */     if (level > 2) {
/*  214 */       level = 2;
/*      */     }
/*      */     
/*  217 */     if ((is.func_77973_b() instanceof IRepairable))
/*      */     {
/*  219 */       AspectList cost = ThaumcraftCraftingManager.getObjectTags(is);
/*  220 */       if ((cost == null) || (cost.size() == 0)) return;
/*  221 */       cost = ResearchManager.reduceToPrimals(cost);
/*  222 */       for (Aspect a : cost.getAspects()) {
/*  223 */         if (a != null) {
/*  224 */           this.repairCost.merge(a, (int)Math.sqrt(cost.getAmount(a) * 2) * level);
/*      */         }
/*      */       }
/*  227 */       boolean doIt = true;
/*  228 */       if ((is.func_77973_b() instanceof IRepairableExtended)) {
/*  229 */         doIt = ((IRepairableExtended)is.func_77973_b()).doRepair(is, player, level);
/*      */       }
/*      */       
/*  232 */       if (doIt) {
/*  233 */         for (Aspect a : this.repairCost.getAspects()) {
/*  234 */           if (this.currentRepairVis.getAmount(a) < this.repairCost.getAmount(a)) {
/*  235 */             doIt = false;
/*  236 */             break;
/*      */           }
/*      */         }
/*      */       }
/*  240 */       if (doIt) {
/*  241 */         for (Aspect a : this.repairCost.getAspects()) {
/*  242 */           this.currentRepairVis.reduce(a, this.repairCost.getAmount(a));
/*      */         }
/*  244 */         is.func_77972_a(-level, player);
/*  245 */         func_70296_d();
/*      */       }
/*      */     } else {
/*  248 */       this.repairCost = new AspectList();
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean areItemsValid() {
/*  253 */     boolean notNearBroken = true;
/*  254 */     if ((this.hasPickaxe) && (func_70301_a(1).func_77960_j() + 1 >= func_70301_a(1).func_77958_k()))
/*      */     {
/*      */ 
/*  257 */       notNearBroken = false; }
/*  258 */     return (this.hasFocus) && (this.hasPickaxe) && (func_70301_a(1).func_77984_f()) && (notNearBroken);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*  263 */   public int fortune = 0;
/*  264 */   public int speed = 0;
/*  265 */   public int area = 0;
/*      */   
/*      */   public void func_70296_d()
/*      */   {
/*  269 */     super.func_70296_d();
/*      */     
/*  271 */     this.fortune = 0;
/*  272 */     this.area = 0;
/*  273 */     this.speed = 0;
/*      */     
/*  275 */     if ((func_70301_a(0) != null) && ((func_70301_a(0).func_77973_b() instanceof ItemFocusExcavation))) {
/*  276 */       this.fortune = ((ItemFocusExcavation)func_70301_a(0).func_77973_b()).getUpgradeLevel(func_70301_a(0), FocusUpgradeType.treasure);
/*  277 */       this.area = ((ItemFocusExcavation)func_70301_a(0).func_77973_b()).getUpgradeLevel(func_70301_a(0), FocusUpgradeType.enlarge);
/*  278 */       this.speed += ((ItemFocusExcavation)func_70301_a(0).func_77973_b()).getUpgradeLevel(func_70301_a(0), FocusUpgradeType.potency);
/*  279 */       this.hasFocus = true;
/*      */     } else {
/*  281 */       this.hasFocus = false;
/*      */     }
/*      */     
/*  284 */     if ((func_70301_a(1) != null) && ((func_70301_a(1).func_77973_b() instanceof ItemPickaxe)))
/*      */     {
/*  286 */       this.hasPickaxe = true;
/*  287 */       int f = EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, func_70301_a(1));
/*      */       
/*  289 */       if (f > this.fortune)
/*  290 */         this.fortune = f;
/*  291 */       this.speed += EnchantmentHelper.func_77506_a(Enchantment.field_77349_p.field_77352_x, func_70301_a(1));
/*      */     }
/*      */     else {
/*  294 */       this.hasPickaxe = false;
/*      */     }
/*      */   }
/*      */   
/*      */   private void dig()
/*      */   {
/*  300 */     if ((this.rotX != this.tarX) || (this.rotZ != this.tarZ)) {
/*  301 */       if (this.field_145850_b.field_72995_K) {
/*  302 */         if (this.topRotation % 90 != 0)
/*  303 */           this.topRotation += Math.min(10, 90 - this.topRotation % 90);
/*  304 */         this.vRadX *= 0.9F;
/*  305 */         this.vRadZ *= 0.9F;
/*      */       }
/*  307 */       return;
/*      */     }
/*      */     
/*  310 */     if (!this.field_145850_b.field_72995_K) {
/*  311 */       boolean dug = false;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  319 */       if (this.base == null) {
/*  320 */         this.base = ((TileArcaneBoreBase)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + this.baseOrientation.getOpposite().offsetY, this.field_145849_e));
/*      */       }
/*      */       
/*      */ 
/*  324 */       if (--this.count > 0)
/*  325 */         return;
/*  326 */       if (this.toDig) {
/*  327 */         this.toDig = false;
/*  328 */         Block bi = this.field_145850_b.func_147439_a(this.digX, this.digY, this.digZ);
/*  329 */         int md = this.field_145850_b.func_72805_g(this.digX, this.digY, this.digZ);
/*      */         int tfortune;
/*  331 */         boolean silktouch; if (!bi.isAir(this.field_145850_b, this.digX, this.digY, this.digZ)) {
/*  332 */           tfortune = this.fortune;
/*  333 */           silktouch = false;
/*      */           
/*  335 */           if ((func_70301_a(1) != null) && (EnchantmentHelper.func_77506_a(Enchantment.field_77348_q.field_77352_x, func_70301_a(1)) > 0) && (bi.canSilkHarvest(this.field_145850_b, null, this.digX, this.digY, this.digZ, md)))
/*      */           {
/*      */ 
/*      */ 
/*  339 */             silktouch = true;
/*  340 */             tfortune = 0;
/*      */           }
/*      */           
/*  343 */           if ((!silktouch) && (func_70301_a(0) != null) && (((ItemFocusExcavation)func_70301_a(0).func_77973_b()).isUpgradedWith(func_70301_a(0), FocusUpgradeType.silktouch)) && (bi.canSilkHarvest(this.field_145850_b, null, this.digX, this.digY, this.digZ, md)))
/*      */           {
/*      */ 
/*      */ 
/*  347 */             silktouch = true;
/*  348 */             tfortune = 0;
/*      */           }
/*      */           
/*  351 */           this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockWoodenDevice, 99, Block.func_149682_b(bi) + (md << 12));
/*      */           
/*      */ 
/*  354 */           ArrayList<ItemStack> items = new ArrayList();
/*  355 */           if (silktouch) {
/*  356 */             ItemStack dropped = BlockUtils.createStackedBlock(bi, md);
/*  357 */             if (dropped != null) {
/*  358 */               items.add(dropped);
/*      */             }
/*      */           } else {
/*  361 */             items = bi.getDrops(this.field_145850_b, this.digX, this.digY, this.digZ, md, tfortune);
/*      */           }
/*      */           
/*      */ 
/*  365 */           List<EntityItem> targets = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(this.digX, this.digY, this.digZ, this.digX + 1, this.digY + 1, this.digZ + 1).func_72314_b(1.0D, 1.0D, 1.0D));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  371 */           if (targets.size() > 0) {
/*  372 */             for (EntityItem e : targets) {
/*  373 */               items.add(e.func_92059_d().func_77946_l());
/*  374 */               e.func_70106_y();
/*      */             }
/*      */           }
/*  377 */           if (items.size() > 0) {
/*  378 */             for (ItemStack is : items) {
/*  379 */               ItemStack dropped = is.func_77946_l();
/*      */               
/*  381 */               if ((!silktouch) && (((func_70301_a(1) != null) && ((func_70301_a(1).func_77973_b() instanceof ItemElementalPickaxe))) || ((func_70301_a(0) != null) && ((func_70301_a(0).func_77973_b() instanceof ItemFocusBasic)) && (((ItemFocusBasic)func_70301_a(0).func_77973_b()).isUpgradedWith(func_70301_a(0), ItemFocusExcavation.dowsing)))))
/*      */               {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  388 */                 dropped = Utils.findSpecialMiningResult(is, 0.2F + tfortune * 0.075F, this.field_145850_b.field_73012_v);
/*      */               }
/*  390 */               if ((this.base != null) && ((this.base instanceof TileArcaneBoreBase)))
/*      */               {
/*  392 */                 TileEntity inventory = this.field_145850_b.func_147438_o(this.base.field_145851_c + this.base.orientation.offsetX, this.base.field_145848_d, this.base.field_145849_e + this.base.orientation.offsetZ);
/*      */                 
/*      */ 
/*  395 */                 if ((inventory != null) && ((inventory instanceof IInventory))) {
/*  396 */                   dropped = InventoryUtils.placeItemStackIntoInventory(dropped, (IInventory)inventory, this.base.orientation.getOpposite().ordinal(), true);
/*      */                 }
/*      */                 
/*      */ 
/*  400 */                 if (dropped != null) {
/*  401 */                   EntityItem ei = new EntityItem(this.field_145850_b, this.field_145851_c + 0.5D + this.base.orientation.offsetX * 0.66D, this.field_145848_d + 0.4D + this.baseOrientation.getOpposite().offsetY, this.field_145849_e + 0.5D + this.base.orientation.offsetZ * 0.66D, dropped.func_77946_l());
/*      */                   
/*      */ 
/*      */ 
/*  405 */                   ei.field_70159_w = (0.075F * this.base.orientation.offsetX);
/*  406 */                   ei.field_70181_x = 0.02500000037252903D;
/*  407 */                   ei.field_70179_y = (0.075F * this.base.orientation.offsetZ);
/*  408 */                   this.field_145850_b.func_72838_d(ei);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*  415 */         func_70299_a(1, InventoryUtils.damageItem(1, func_70301_a(1), this.field_145850_b));
/*      */         
/*  417 */         if (func_70301_a(1).field_77994_a <= 0) {
/*  418 */           func_70299_a(1, null);
/*      */         }
/*  420 */         this.field_145850_b.func_147468_f(this.digX, this.digY, this.digZ);
/*      */         
/*  422 */         if (this.base != null) {
/*  423 */           for (int lb = 2; lb < 6; lb++) {
/*  424 */             ForgeDirection lbd = ForgeDirection.getOrientation(lb);
/*  425 */             TileEntity lbte = this.field_145850_b.func_147438_o(this.base.field_145851_c + lbd.offsetX, this.base.field_145848_d, this.base.field_145849_e + lbd.offsetZ);
/*      */             
/*      */ 
/*  428 */             if ((lbte != null) && ((lbte instanceof TileArcaneLamp))) {
/*  429 */               int d = this.field_145850_b.field_73012_v.nextInt(32) * 2;
/*  430 */               int xx = this.field_145851_c + this.orientation.offsetX + this.orientation.offsetX * d;
/*      */               
/*  432 */               int yy = this.field_145848_d + this.orientation.offsetY + this.orientation.offsetY * d;
/*      */               
/*  434 */               int zz = this.field_145849_e + this.orientation.offsetZ + this.orientation.offsetZ * d;
/*      */               
/*  436 */               int p = d / 2 % 4;
/*  437 */               if (this.orientation.offsetX != 0) {
/*  438 */                 zz += ((p == 1) || (p == 3) ? 0 : p == 0 ? 3 : -3);
/*      */               } else {
/*  440 */                 xx += ((p == 1) || (p == 3) ? 0 : p == 0 ? 3 : -3);
/*      */               }
/*  442 */               if ((p == 3) && (this.orientation.offsetY == 0)) {
/*  443 */                 yy -= 2;
/*      */               }
/*      */               
/*  446 */               if ((!this.field_145850_b.func_147437_c(xx, yy, zz)) || (this.field_145850_b.func_147439_a(xx, yy, zz) == ConfigBlocks.blockAiry) || (this.field_145850_b.func_72957_l(xx, yy, zz) >= 15)) {
/*      */                 break;
/*      */               }
/*  449 */               this.field_145850_b.func_147465_d(xx, yy, zz, ConfigBlocks.blockAiry, 3, 3); break;
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  456 */         dug = true;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  461 */       findNextBlockToDig();
/*  462 */       if ((dug) && 
/*  463 */         (this.speedyTime > 0.0F)) {
/*  464 */         this.speedyTime -= 1.0F;
/*      */       }
/*      */     } else {
/*  467 */       this.paused += 1;
/*      */       
/*  469 */       if (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
/*  470 */         func_145843_s();
/*      */       }
/*  472 */       if ((this.paused < this.maxPause) && (this.soundDelay < System.currentTimeMillis())) {
/*  473 */         this.soundDelay = (System.currentTimeMillis() + 1200L + this.field_145850_b.field_73012_v.nextInt(100));
/*      */         
/*  475 */         this.field_145850_b.func_72980_b(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "thaumcraft:rumble", 0.25F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, false);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  480 */       if ((this.beamlength > 0) && (this.paused > this.maxPause))
/*  481 */         this.beamlength -= 1;
/*  482 */       if (this.toDig)
/*      */       {
/*  484 */         this.paused = 0;
/*  485 */         this.beamlength = 64;
/*  486 */         Block block = this.field_145850_b.func_147439_a(this.digX, this.digY, this.digZ);
/*  487 */         int md = this.field_145850_b.func_72805_g(this.digX, this.digY, this.digZ);
/*  488 */         if (block != null) {
/*  489 */           this.maxPause = (10 + Math.max(10 - this.speed, (int)(block.func_149712_f(this.field_145850_b, this.digX, this.digY, this.digZ) * 2.0F) - this.speed * 2));
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  494 */           this.maxPause = 20;
/*      */         }
/*      */         
/*  497 */         if (this.speedyTime <= 0.0F) {
/*  498 */           this.maxPause *= 4;
/*      */         }
/*  500 */         this.toDig = false;
/*      */         
/*  502 */         double xd = this.field_145851_c + 0.5D - (this.digX + 0.5D);
/*  503 */         double yd = this.field_145848_d + 0.5D - (this.digY + 0.5D);
/*  504 */         double zd = this.field_145849_e + 0.5D - (this.digZ + 0.5D);
/*      */         
/*  506 */         double var12 = MathHelper.func_76133_a(xd * xd + zd * zd);
/*      */         
/*      */ 
/*  509 */         float rx = (float)(Math.atan2(zd, xd) * 180.0D / 3.141592653589793D);
/*  510 */         float rz = (float)-(Math.atan2(yd, var12) * 180.0D / 3.141592653589793D) + 90.0F;
/*      */         
/*  512 */         this.tRadX = (MathHelper.func_76142_g(this.rotX) + rx);
/*      */         
/*  514 */         if (this.orientation.ordinal() == 5) {
/*  515 */           if (this.tRadX > 180.0F)
/*  516 */             this.tRadX -= 360.0F;
/*  517 */           if (this.tRadX < -180.0F) {
/*  518 */             this.tRadX += 360.0F;
/*      */           }
/*      */         }
/*  521 */         this.tRadZ = (rz - this.rotZ);
/*  522 */         if (this.orientation.ordinal() <= 1) {
/*  523 */           this.tRadZ += 180.0F;
/*      */           
/*  525 */           if (this.vRadX - this.tRadX >= 180.0F) {
/*  526 */             this.vRadX -= 360.0F;
/*      */           }
/*  528 */           if (this.vRadX - this.tRadX <= -180.0F) {
/*  529 */             this.vRadX += 360.0F;
/*      */           }
/*      */         }
/*      */         
/*  533 */         this.mRadX = Math.abs((this.vRadX - this.tRadX) / 6.0F);
/*  534 */         this.mRadZ = Math.abs((this.vRadZ - this.tRadZ) / 6.0F);
/*      */         
/*  536 */         if (this.speedyTime > 0.0F) {
/*  537 */           this.speedyTime -= 1.0F;
/*      */         }
/*      */       }
/*  540 */       if (this.paused < this.maxPause) {
/*  541 */         if (this.vRadX < this.tRadX) {
/*  542 */           this.vRadX += this.mRadX;
/*  543 */         } else if (this.vRadX > this.tRadX) {
/*  544 */           this.vRadX -= this.mRadX;
/*      */         }
/*      */         
/*  547 */         if (this.vRadZ < this.tRadZ) {
/*  548 */           this.vRadZ += this.mRadZ;
/*  549 */         } else if (this.vRadZ > this.tRadZ) {
/*  550 */           this.vRadZ -= this.mRadZ;
/*      */         }
/*      */       } else {
/*  553 */         this.vRadX *= 0.9F;
/*  554 */         this.vRadZ *= 0.9F;
/*      */       }
/*      */       
/*  557 */       this.mRadX *= 0.9F;
/*  558 */       this.mRadZ *= 0.9F;
/*      */       
/*  560 */       float vx = this.rotX + 90 - this.vRadX;
/*  561 */       float vz = this.rotZ + 90 - this.vRadZ;
/*  562 */       float var3 = 1.0F;
/*  563 */       float dX = MathHelper.func_76126_a(vx / 180.0F * 3.1415927F) * MathHelper.func_76134_b(vz / 180.0F * 3.1415927F) * var3;
/*      */       
/*  565 */       float dZ = MathHelper.func_76134_b(vx / 180.0F * 3.1415927F) * MathHelper.func_76134_b(vz / 180.0F * 3.1415927F) * var3;
/*      */       
/*  567 */       float dY = MathHelper.func_76126_a(vz / 180.0F * 3.1415927F) * var3;
/*      */       
/*  569 */       Vec3 var13 = Vec3.func_72443_a(this.field_145851_c + 0.5D + dX, this.field_145848_d + 0.5D + dY, this.field_145849_e + 0.5D + dZ);
/*      */       
/*  571 */       Vec3 var14 = Vec3.func_72443_a(this.field_145851_c + 0.5D + dX * this.beamlength, this.field_145848_d + 0.5D + dY * this.beamlength, this.field_145849_e + 0.5D + dZ * this.beamlength);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  576 */       MovingObjectPosition mop = this.field_145850_b.func_147447_a(var13, var14, false, true, false);
/*      */       
/*  578 */       int impact = 0;
/*  579 */       float length = 64.0F;
/*  580 */       double bx = var14.field_72450_a;
/*  581 */       double by = var14.field_72448_b;
/*  582 */       double bz = var14.field_72449_c;
/*  583 */       if (mop != null) {
/*  584 */         double xd = this.field_145851_c + 0.5D + dX - mop.field_72307_f.field_72450_a;
/*  585 */         double yd = this.field_145848_d + 0.5D + dY - mop.field_72307_f.field_72448_b;
/*  586 */         double zd = this.field_145849_e + 0.5D + dZ - mop.field_72307_f.field_72449_c;
/*      */         
/*  588 */         bx = mop.field_72307_f.field_72450_a;
/*  589 */         by = mop.field_72307_f.field_72448_b;
/*  590 */         bz = mop.field_72307_f.field_72449_c;
/*      */         
/*  592 */         length = MathHelper.func_76133_a(xd * xd + yd * yd + zd * zd) + 0.5F;
/*      */         
/*  594 */         impact = 5;
/*      */         
/*  596 */         int x = MathHelper.func_76128_c(bx);
/*  597 */         int y = MathHelper.func_76128_c(by);
/*  598 */         int z = MathHelper.func_76128_c(bz);
/*  599 */         if (!this.field_145850_b.func_147437_c(x, y, z)) {
/*  600 */           Thaumcraft.proxy.boreDigFx(this.field_145850_b, x, y, z, this.field_145851_c + this.orientation.offsetX, this.field_145848_d + this.orientation.offsetY, this.field_145849_e + this.orientation.offsetZ, this.field_145850_b.func_147439_a(x, y, z), this.field_145850_b.func_72805_g(x, y, z) >> 12 & 0xFF);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  608 */       this.topRotation += this.beamlength / 6;
/*  609 */       this.beam1 = Thaumcraft.proxy.beamBore(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, bx, by, bz, 1, 65382, true, impact > 0 ? 2.0F : 0.0F, this.beam1, impact);
/*      */       
/*      */ 
/*  612 */       this.beam2 = Thaumcraft.proxy.beamBore(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, bx, by, bz, 2, 16746581, false, impact > 0 ? 2.0F : 0.0F, this.beam2, impact);
/*      */       
/*      */ 
/*      */ 
/*  616 */       if ((this.field_145850_b.func_147437_c(this.digX, this.digY, this.digZ)) && (this.digBlock != Blocks.field_150350_a)) {
/*  617 */         this.field_145850_b.func_72980_b(this.digX + 0.5F, this.digY + 0.5F, this.digZ + 0.5F, this.digBlock.field_149762_H.func_150495_a(), (this.digBlock.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.digBlock.field_149762_H.func_150494_d() * 0.8F, false);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  622 */         for (int a = 0; a < Thaumcraft.proxy.particleCount(10); a++) {
/*  623 */           Thaumcraft.proxy.boreDigFx(this.field_145850_b, this.digX, this.digY, this.digZ, this.field_145851_c + this.orientation.offsetX, this.field_145848_d + this.orientation.offsetY, this.field_145849_e + this.orientation.offsetZ, this.digBlock, this.digMd >> 12 & 0xFF);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  629 */         this.digBlock = Blocks.field_150350_a;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*  634 */   int blockCount = 0;
/*      */   private float speedyTime;
/*      */   
/*      */   private void findNextBlockToDig() {
/*  638 */     if (this.radInc == 0.0F) {
/*  639 */       this.radInc = ((this.maxRadius + this.area) / 360.0F);
/*      */     }
/*  641 */     int x = this.lastX;
/*  642 */     int z = this.lastZ;
/*  643 */     int y = this.lastY;
/*      */     
/*  645 */     while ((x == this.lastX) && (z == this.lastZ) && (y == this.lastY)) {
/*  646 */       this.spiral += 2;
/*  647 */       if (this.spiral >= 360) {
/*  648 */         this.spiral -= 360;
/*      */       }
/*      */       
/*  651 */       this.currentRadius += this.radInc;
/*  652 */       if ((this.currentRadius > this.maxRadius + this.area) || (this.currentRadius < -(this.maxRadius + this.area)))
/*      */       {
/*  654 */         this.radInc *= -1.0F;
/*      */       }
/*      */       
/*  657 */       TCVec3 vsource = TCVec3.createVectorHelper(this.field_145851_c + this.orientation.offsetX + 0.5D, this.field_145848_d + this.orientation.offsetY + 0.5D, this.field_145849_e + this.orientation.offsetZ + 0.5D);
/*      */       
/*      */ 
/*  660 */       TCVec3 vtar = TCVec3.createVectorHelper(0.0D, this.currentRadius, 0.0D);
/*      */       
/*  662 */       vtar.rotateAroundZ(this.spiral / 180.0F * 3.1415927F);
/*  663 */       vtar.rotateAroundY(1.5707964F * this.orientation.offsetX);
/*      */       
/*  665 */       vtar.rotateAroundX(1.5707964F * this.orientation.offsetY);
/*      */       
/*  667 */       TCVec3 vres = vsource.addVector(vtar.xCoord, vtar.yCoord, vtar.zCoord);
/*      */       
/*      */ 
/*  670 */       x = MathHelper.func_76128_c(vres.xCoord);
/*  671 */       y = MathHelper.func_76128_c(vres.yCoord);
/*  672 */       z = MathHelper.func_76128_c(vres.zCoord);
/*      */     }
/*  674 */     this.lastX = x;
/*  675 */     this.lastZ = z;
/*  676 */     this.lastY = y;
/*      */     
/*  678 */     x += this.orientation.offsetX;
/*  679 */     y += this.orientation.offsetY;
/*  680 */     z += this.orientation.offsetZ;
/*      */     
/*  682 */     for (int depth = 0; depth < 64; depth++) {
/*  683 */       x += this.orientation.offsetX;
/*  684 */       y += this.orientation.offsetY;
/*  685 */       z += this.orientation.offsetZ;
/*      */       
/*  687 */       Block block = this.field_145850_b.func_147439_a(x, y, z);
/*  688 */       int md = this.field_145850_b.func_72805_g(x, y, z);
/*      */       
/*  690 */       if ((block != null) && (block.func_149712_f(this.field_145850_b, x, y, z) < 0.0F)) {
/*      */         break;
/*      */       }
/*      */       
/*  694 */       if ((!this.field_145850_b.func_147437_c(x, y, z)) && (block != null) && (block.func_149678_a(md, false)) && (block.func_149668_a(this.field_145850_b, x, y, z) != null))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  699 */         this.digX = x;
/*  700 */         this.digY = y;
/*  701 */         this.digZ = z;
/*      */         
/*  703 */         if (++this.blockCount > 2) {
/*  704 */           this.blockCount = 0;
/*      */         }
/*  706 */         this.count = Math.max(10 - this.speed, (int)(block.func_149712_f(this.field_145850_b, x, y, z) * 2.0F) - this.speed * 2);
/*      */         
/*      */ 
/*  709 */         if (this.speedyTime < 1.0F)
/*  710 */           this.count *= 4;
/*  711 */         this.toDig = true;
/*      */         
/*  713 */         Vec3 var13 = Vec3.func_72443_a(this.field_145851_c + 0.5D + this.orientation.offsetX, this.field_145848_d + 0.5D + this.orientation.offsetY, this.field_145849_e + 0.5D + this.orientation.offsetZ);
/*      */         
/*      */ 
/*      */ 
/*  717 */         Vec3 var14 = Vec3.func_72443_a(this.digX + 0.5D, this.digY + 0.5D, this.digZ + 0.5D);
/*      */         
/*      */ 
/*  720 */         MovingObjectPosition mop = this.field_145850_b.func_147447_a(var13, var14, false, true, false);
/*      */         
/*  722 */         if (mop != null) {
/*  723 */           block = this.field_145850_b.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*      */           
/*  725 */           md = this.field_145850_b.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*      */           
/*  727 */           if ((block.func_149712_f(this.field_145850_b, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) > -1.0F) && (block.func_149668_a(this.field_145850_b, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) != null))
/*      */           {
/*      */ 
/*      */ 
/*  731 */             this.count = Math.max(10 - this.speed, (int)(block.func_149712_f(this.field_145850_b, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) * 2.0F) - this.speed * 2);
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  737 */             if (this.speedyTime < 1.0F)
/*  738 */               this.count *= 4;
/*  739 */             this.digX = mop.field_72311_b;
/*  740 */             this.digY = mop.field_72312_c;
/*  741 */             this.digZ = mop.field_72309_d;
/*      */           }
/*      */         }
/*      */         
/*  745 */         sendDigEvent();
/*  746 */         break;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean gettingPower()
/*      */   {
/*  753 */     return (this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e)) || (this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d + this.baseOrientation.getOpposite().offsetY, this.field_145849_e));
/*      */   }
/*      */   
/*      */ 
/*      */   public void setOrientation(ForgeDirection or, boolean initial)
/*      */   {
/*  759 */     this.orientation = or;
/*  760 */     this.lastX = 0;
/*  761 */     this.lastZ = 0;
/*  762 */     switch (or.ordinal()) {
/*      */     case 0: 
/*  764 */       this.tarZ = 180;
/*  765 */       this.tarX = 0;
/*  766 */       break;
/*      */     case 1: 
/*  768 */       this.tarZ = 0;
/*  769 */       this.tarX = 0;
/*  770 */       break;
/*      */     case 2: 
/*  772 */       this.tarZ = 90;
/*  773 */       this.tarX = 270;
/*  774 */       break;
/*      */     case 3: 
/*  776 */       this.tarZ = 90;
/*  777 */       this.tarX = 90;
/*  778 */       break;
/*      */     case 4: 
/*  780 */       this.tarZ = 90;
/*  781 */       this.tarX = 0;
/*  782 */       break;
/*      */     case 5: 
/*  784 */       this.tarZ = 90;
/*  785 */       this.tarX = 180;
/*      */     }
/*      */     
/*      */     
/*  789 */     if (initial) {
/*  790 */       this.rotX = this.tarX;
/*  791 */       this.rotZ = this.tarZ;
/*      */     }
/*      */     
/*  794 */     this.toDig = false;
/*  795 */     this.radInc = 0.0F;
/*  796 */     this.paused = 100;
/*  797 */     this.tRadX = 0.0F;
/*  798 */     this.tRadZ = 0.0F;
/*  799 */     this.mRadX = 0.0F;
/*  800 */     this.mRadZ = 0.0F;
/*  801 */     this.digX = 0;
/*  802 */     this.digY = 0;
/*  803 */     this.digZ = 0;
/*  804 */     if (this.field_145850_b != null) {
/*  805 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */     }
/*      */   }
/*      */   
/*      */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/*  810 */     super.func_145839_a(nbttagcompound);
/*  811 */     this.speedyTime = nbttagcompound.func_74765_d("SpeedyTime");
/*  812 */     setOrientation(this.orientation, true);
/*      */   }
/*      */   
/*      */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*      */   {
/*  817 */     super.func_145841_b(nbttagcompound);
/*  818 */     nbttagcompound.func_74777_a("SpeedyTime", (short)(int)this.speedyTime);
/*      */   }
/*      */   
/*      */   public void readCustomNBT(NBTTagCompound nbttagcompound) {
/*  822 */     this.orientation = ForgeDirection.getOrientation(nbttagcompound.func_74762_e("orientation"));
/*      */     
/*  824 */     this.baseOrientation = ForgeDirection.getOrientation(nbttagcompound.func_74762_e("baseOrientation"));
/*      */     
/*      */ 
/*  827 */     NBTTagList var2 = nbttagcompound.func_150295_c("Inventory", 10);
/*  828 */     this.contents = new ItemStack[func_70302_i_()];
/*      */     
/*  830 */     for (int var3 = 0; var3 < var2.func_74745_c(); var3++) {
/*  831 */       NBTTagCompound var4 = var2.func_150305_b(var3);
/*  832 */       int var5 = var4.func_74771_c("Slot") & 0xFF;
/*      */       
/*  834 */       if ((var5 >= 0) && (var5 < this.contents.length)) {
/*  835 */         this.contents[var5] = ItemStack.func_77949_a(var4);
/*      */       }
/*      */     }
/*      */     
/*  839 */     func_70296_d();
/*      */   }
/*      */   
/*      */   public void writeCustomNBT(NBTTagCompound nbttagcompound) {
/*  843 */     nbttagcompound.func_74768_a("orientation", this.orientation.ordinal());
/*  844 */     nbttagcompound.func_74768_a("baseOrientation", this.baseOrientation.ordinal());
/*  845 */     NBTTagList var2 = new NBTTagList();
/*  846 */     for (int var3 = 0; var3 < this.contents.length; var3++) {
/*  847 */       if (this.contents[var3] != null) {
/*  848 */         NBTTagCompound var4 = new NBTTagCompound();
/*  849 */         var4.func_74774_a("Slot", (byte)var3);
/*  850 */         this.contents[var3].func_77955_b(var4);
/*  851 */         var2.func_74742_a(var4);
/*      */       }
/*      */     }
/*  854 */     nbttagcompound.func_74782_a("Inventory", var2);
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
/*      */   public boolean func_145842_c(int i, int j)
/*      */   {
/*  945 */     if (i == 99) {
/*      */       try {
/*  947 */         if ((this.field_145850_b.field_72995_K) && ((j & 0xFFF) > 0)) {
/*  948 */           Block var40 = Block.func_149729_e(j & 0xFFF);
/*  949 */           if (var40 != null) {
/*  950 */             this.field_145850_b.func_72980_b(this.digX + 0.5F, this.digY + 0.5F, this.digZ + 0.5F, var40.field_149762_H.func_150495_a(), (var40.field_149762_H.func_150497_c() + 1.0F) / 2.0F, var40.field_149762_H.func_150494_d() * 0.8F, false);
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  955 */             for (int a = 0; a < Thaumcraft.proxy.particleCount(10); a++) {
/*  956 */               Thaumcraft.proxy.boreDigFx(this.field_145850_b, this.digX, this.digY, this.digZ, this.field_145851_c + this.orientation.offsetX, this.field_145848_d + this.orientation.offsetY, this.field_145849_e + this.orientation.offsetZ, var40, j >> 12 & 0xFF);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       catch (Exception e) {}
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  966 */       return true;
/*      */     }
/*  968 */     return super.func_145842_c(i, j);
/*      */   }
/*      */   
/*      */   public void getDigEvent(int j) {
/*  972 */     int x = (j >> 16 & 0xFF) - 64;
/*  973 */     int y = (j >> 8 & 0xFF) - 64;
/*  974 */     int z = (j & 0xFF) - 64;
/*      */     
/*  976 */     this.digX = (this.field_145851_c + x);
/*  977 */     this.digY = (this.field_145848_d + y);
/*  978 */     this.digZ = (this.field_145849_e + z);
/*  979 */     this.toDig = true;
/*  980 */     this.digBlock = this.field_145850_b.func_147439_a(this.digX, this.digY, this.digZ);
/*  981 */     this.digMd = this.field_145850_b.func_72805_g(this.digX, this.digY, this.digZ);
/*      */   }
/*      */   
/*      */   public void sendDigEvent() {
/*  985 */     int x = this.digX - this.field_145851_c + 64;
/*  986 */     int y = this.digY - this.field_145848_d + 64;
/*  987 */     int z = this.digZ - this.field_145849_e + 64;
/*  988 */     int c = (x & 0xFF) << 16 | (y & 0xFF) << 8 | z & 0xFF;
/*      */     
/*  990 */     PacketHandler.INSTANCE.sendToAllAround(new PacketBoreDig(this.field_145851_c, this.field_145848_d, this.field_145849_e, c), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, this.field_145851_c, this.field_145848_d, this.field_145849_e, 64.0D));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int func_70302_i_()
/*      */   {
/*  998 */     return 2;
/*      */   }
/*      */   
/*      */   public ItemStack func_70301_a(int var1)
/*      */   {
/* 1003 */     return this.contents[var1];
/*      */   }
/*      */   
/*      */   public ItemStack func_70298_a(int var1, int var2)
/*      */   {
/* 1008 */     if (this.contents[var1] != null)
/*      */     {
/*      */ 
/* 1011 */       if (this.contents[var1].field_77994_a <= var2) {
/* 1012 */         ItemStack var3 = this.contents[var1];
/* 1013 */         this.contents[var1] = null;
/* 1014 */         func_70296_d();
/* 1015 */         return var3;
/*      */       }
/* 1017 */       ItemStack var3 = this.contents[var1].func_77979_a(var2);
/*      */       
/* 1019 */       if (this.contents[var1].field_77994_a == 0) {
/* 1020 */         this.contents[var1] = null;
/*      */       }
/*      */       
/* 1023 */       func_70296_d();
/* 1024 */       return var3;
/*      */     }
/*      */     
/* 1027 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */   public ItemStack func_70304_b(int var1)
/*      */   {
/* 1033 */     if (this.contents[var1] != null) {
/* 1034 */       ItemStack var2 = this.contents[var1];
/* 1035 */       this.contents[var1] = null;
/* 1036 */       return var2;
/*      */     }
/* 1038 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */   public void func_70299_a(int var1, ItemStack var2)
/*      */   {
/* 1044 */     this.contents[var1] = var2;
/*      */     
/* 1046 */     if ((var2 != null) && (var2.field_77994_a > func_70297_j_())) {
/* 1047 */       var2.field_77994_a = func_70297_j_();
/*      */     }
/*      */     
/* 1050 */     func_70296_d();
/*      */   }
/*      */   
/*      */   public String func_145825_b()
/*      */   {
/* 1055 */     return "Arcane Bore";
/*      */   }
/*      */   
/*      */   public int func_70297_j_()
/*      */   {
/* 1060 */     return 64;
/*      */   }
/*      */   
/*      */   public boolean func_70300_a(EntityPlayer var1)
/*      */   {
/* 1065 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_70295_k_() {}
/*      */   
/*      */ 
/*      */ 
/*      */   public void func_70305_f() {}
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean func_145818_k_()
/*      */   {
/* 1081 */     return false;
/*      */   }
/*      */   
/*      */   public boolean func_94041_b(int i, ItemStack itemstack)
/*      */   {
/* 1086 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*      */   {
/* 1092 */     setOrientation(ForgeDirection.getOrientation(side), false);
/* 1093 */     player.field_70170_p.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*      */     
/* 1095 */     player.func_71038_i();
/* 1096 */     func_70296_d();
/*      */     
/* 1098 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*      */   {
/* 1105 */     return null;
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
/*      */ 
/* 1123 */   private final int itemsPerVis = 20;
/*      */   
/*      */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*      */   
/*      */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileArcaneBore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */