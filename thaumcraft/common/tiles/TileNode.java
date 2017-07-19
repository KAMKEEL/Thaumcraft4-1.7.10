/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.WorldCoordinates;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.api.nodes.NodeModifier;
/*     */ import thaumcraft.api.nodes.NodeType;
/*     */ import thaumcraft.api.research.ScanResult;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.entities.EntityAspectOrb;
/*     */ import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
/*     */ import thaumcraft.common.items.ItemCompassStone;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.research.ScanManager;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class TileNode extends TileThaumcraft implements INode, thaumcraft.api.wands.IWandable
/*     */ {
/*  52 */   long lastActive = 0L;
/*     */   
/*  54 */   AspectList aspects = new AspectList();
/*  55 */   AspectList aspectsBase = new AspectList();
/*     */   
/*  57 */   public static HashMap<String, ArrayList<Integer>> locations = new HashMap();
/*     */   
/*  59 */   private NodeType nodeType = NodeType.NORMAL;
/*  60 */   private NodeModifier nodeModifier = null;
/*     */   
/*  62 */   int count = 0;
/*  63 */   int regeneration = -1;
/*  64 */   int wait = 0;
/*     */   
/*  66 */   String id = null;
/*     */   
/*  68 */   byte nodeLock = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   public String getId()
/*     */   {
/*  74 */     if (this.id == null) {
/*  75 */       this.id = generateId();
/*     */     }
/*  77 */     return this.id;
/*     */   }
/*     */   
/*     */   public String generateId() {
/*  81 */     this.id = (this.field_145850_b.field_73011_w.field_76574_g + ":" + this.field_145851_c + ":" + this.field_145848_d + ":" + this.field_145849_e);
/*     */     
/*  83 */     if ((this.field_145850_b != null) && (locations != null)) {
/*  84 */       ArrayList<Integer> t = new ArrayList();
/*  85 */       t.add(Integer.valueOf(this.field_145850_b.field_73011_w.field_76574_g));
/*  86 */       t.add(Integer.valueOf(this.field_145851_c));
/*  87 */       t.add(Integer.valueOf(this.field_145848_d));
/*  88 */       t.add(Integer.valueOf(this.field_145849_e));
/*  89 */       locations.put(this.id, t);
/*     */     }
/*     */     
/*  92 */     return this.id;
/*     */   }
/*     */   
/*     */   public void onChunkUnload()
/*     */   {
/*  97 */     if (locations != null) locations.remove(this.id);
/*  98 */     super.onChunkUnload();
/*     */   }
/*     */   
/*     */   public void func_145829_t()
/*     */   {
/* 103 */     super.func_145829_t();
/*     */   }
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 108 */     super.func_145845_h();
/* 109 */     if (this.id == null) { generateId();
/*     */     }
/* 111 */     boolean change = false;
/*     */     
/* 113 */     change = handleHungryNodeFirst(change);
/* 114 */     this.count += 1;
/* 115 */     checkLock();
/* 116 */     if (!this.field_145850_b.field_72995_K) {
/* 117 */       change = handleDischarge(change);
/* 118 */       change = handleRecharge(change);
/* 119 */       change = handleTaintNode(change);
/* 120 */       change = handleNodeStability(change);
/* 121 */       change = handleDarkNode(change);
/* 122 */       change = handlePureNode(change);
/* 123 */       change = handleHungryNodeSecond(change);
/* 124 */       if (change) {
/* 125 */         func_70296_d();
/* 126 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/* 129 */     else if ((getNodeType() == NodeType.DARK) && (this.count % 50 == 0)) {
/* 130 */       ItemCompassStone.sinisterNodes.put(new WorldCoordinates(this), Long.valueOf(System.currentTimeMillis()));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void nodeChange()
/*     */   {
/* 137 */     this.regeneration = -1;
/* 138 */     func_70296_d();
/* 139 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/* 144 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getDistanceTo(double par1, double par3, double par5)
/*     */   {
/* 150 */     double var7 = this.field_145851_c + 0.5D - par1;
/* 151 */     double var9 = this.field_145848_d + 0.5D - par3;
/* 152 */     double var11 = this.field_145849_e + 0.5D - par5;
/* 153 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/* 162 */     return -1;
/*     */   }
/*     */   
/*     */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*     */   {
/* 167 */     player.func_71008_a(wandstack, Integer.MAX_VALUE);
/* 168 */     ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
/* 169 */     wand.setObjectInUse(wandstack, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 170 */     return wandstack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/* 179 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public AspectList getAspectsBase()
/*     */   {
/* 184 */     return this.aspectsBase;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAspects(AspectList aspects)
/*     */   {
/* 193 */     this.aspects = aspects;
/* 194 */     this.aspectsBase = aspects.copy();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addToContainer(Aspect aspect, int amount)
/*     */   {
/* 204 */     int left = amount + this.aspects.getAmount(aspect) - this.aspectsBase.getAmount(aspect);
/* 205 */     left = left > 0 ? left : 0;
/* 206 */     this.aspects.add(aspect, amount - left);
/* 207 */     return left;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean takeFromContainer(Aspect aspect, int amount)
/*     */   {
/* 217 */     return this.aspects.reduce(aspect, amount);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Aspect takeRandomPrimalFromSource()
/*     */   {
/* 226 */     Aspect[] primals = this.aspects.getPrimalAspects();
/* 227 */     Aspect asp = primals[this.field_145850_b.field_73012_v.nextInt(primals.length)];
/* 228 */     if ((asp != null) && (this.aspects.reduce(asp, 1))) {
/* 229 */       return asp;
/*     */     }
/* 231 */     return null;
/*     */   }
/*     */   
/*     */   public Aspect chooseRandomFilteredFromSource(AspectList filter, boolean preserve)
/*     */   {
/* 236 */     int min = preserve ? 1 : 0;
/* 237 */     ArrayList<Aspect> validaspects = new ArrayList();
/* 238 */     for (Aspect prim : this.aspects.getAspects()) {
/* 239 */       if ((filter.getAmount(prim) > 0) && (this.aspects.getAmount(prim) > min)) validaspects.add(prim);
/*     */     }
/* 241 */     if (validaspects.size() == 0) return null;
/* 242 */     Aspect asp = (Aspect)validaspects.get(this.field_145850_b.field_73012_v.nextInt(validaspects.size()));
/* 243 */     if ((asp != null) && (this.aspects.getAmount(asp) > min)) {
/* 244 */       return asp;
/*     */     }
/* 246 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public NodeType getNodeType()
/*     */   {
/* 252 */     return this.nodeType;
/*     */   }
/*     */   
/*     */   public void setNodeType(NodeType nodeType)
/*     */   {
/* 257 */     this.nodeType = nodeType;
/*     */   }
/*     */   
/*     */   public void setNodeModifier(NodeModifier nodeModifier)
/*     */   {
/* 262 */     this.nodeModifier = nodeModifier;
/*     */   }
/*     */   
/*     */   public NodeModifier getNodeModifier()
/*     */   {
/* 267 */     return this.nodeModifier;
/*     */   }
/*     */   
/*     */   public int getNodeVisBase(Aspect aspect)
/*     */   {
/* 272 */     return this.aspectsBase.getAmount(aspect);
/*     */   }
/*     */   
/*     */   public void setNodeVisBase(Aspect aspect, short nodeVisBase)
/*     */   {
/* 277 */     if (this.aspectsBase.getAmount(aspect) < nodeVisBase) {
/* 278 */       this.aspectsBase.merge(aspect, nodeVisBase);
/*     */     } else {
/* 280 */       this.aspectsBase.reduce(aspect, this.aspectsBase.getAmount(aspect) - nodeVisBase);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 285 */   boolean catchUp = false;
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/* 289 */     super.func_145839_a(nbttagcompound);
/* 290 */     this.lastActive = nbttagcompound.func_74763_f("lastActive");
/*     */     
/* 292 */     AspectList al = new AspectList();
/* 293 */     NBTTagList tlist = nbttagcompound.func_150295_c("AspectsBase", 10);
/* 294 */     for (int j = 0; j < tlist.func_74745_c(); j++) {
/* 295 */       NBTTagCompound rs = tlist.func_150305_b(j);
/* 296 */       if (rs.func_74764_b("key")) {
/* 297 */         al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
/*     */       }
/*     */     }
/* 300 */     Short oldBase = Short.valueOf(nbttagcompound.func_74765_d("nodeVisBase"));
/* 301 */     this.aspectsBase = new AspectList();
/* 302 */     if ((oldBase.shortValue() > 0) && (al.size() == 0)) {
/* 303 */       for (Aspect a : this.aspects.getAspects()) {
/* 304 */         this.aspectsBase.merge(a, oldBase.shortValue());
/*     */       }
/*     */     } else {
/* 307 */       this.aspectsBase = al.copy();
/*     */     }
/*     */     
/* 310 */     int regen = 600;
/* 311 */     if (getNodeModifier() != null) {
/* 312 */       switch (getNodeModifier()) {
/* 313 */       case BRIGHT:  regen = 400; break;
/* 314 */       case PALE:  regen = 900; break;
/* 315 */       case FADING:  regen = 0;
/*     */       }
/*     */     }
/* 318 */     long ct = System.currentTimeMillis();
/* 319 */     int inc = regen * 75;
/* 320 */     if ((regen > 0) && (this.lastActive > 0L) && (ct > this.lastActive + inc)) {
/* 321 */       this.catchUp = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/* 328 */     super.func_145841_b(nbttagcompound);
/* 329 */     nbttagcompound.func_74772_a("lastActive", this.lastActive);
/*     */     
/* 331 */     NBTTagList tlist = new NBTTagList();
/* 332 */     nbttagcompound.func_74782_a("AspectsBase", tlist);
/* 333 */     for (Aspect aspect : this.aspectsBase.getAspects()) {
/* 334 */       if (aspect != null) {
/* 335 */         NBTTagCompound f = new NBTTagCompound();
/* 336 */         f.func_74778_a("key", aspect.getTag());
/* 337 */         f.func_74768_a("amount", this.aspectsBase.getAmount(aspect));
/* 338 */         tlist.func_74742_a(f);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 346 */     this.id = nbttagcompound.func_74779_i("nodeId");
/*     */     
/* 348 */     if ((this.field_145850_b != null) && (locations != null)) {
/* 349 */       ArrayList<Integer> t = new ArrayList();
/* 350 */       t.add(Integer.valueOf(this.field_145850_b.field_73011_w.field_76574_g));
/* 351 */       t.add(Integer.valueOf(this.field_145851_c));
/* 352 */       t.add(Integer.valueOf(this.field_145848_d));
/* 353 */       t.add(Integer.valueOf(this.field_145849_e));
/* 354 */       locations.put(this.id, t);
/*     */     }
/*     */     
/* 357 */     setNodeType(NodeType.values()[nbttagcompound.func_74771_c("type")]);
/* 358 */     byte mod = nbttagcompound.func_74771_c("modifier");
/* 359 */     if (mod >= 0) {
/* 360 */       setNodeModifier(NodeModifier.values()[mod]);
/*     */     } else {
/* 362 */       setNodeModifier(null);
/*     */     }
/* 364 */     this.aspects.readFromNBT(nbttagcompound);
/*     */     
/*     */ 
/* 367 */     String de = nbttagcompound.func_74779_i("drainer");
/* 368 */     if ((de != null) && (de.length() > 0) && (func_145831_w() != null)) {
/* 369 */       this.drainEntity = func_145831_w().func_72924_a(de);
/* 370 */       if (this.drainEntity != null) {
/* 371 */         this.drainCollision = new MovingObjectPosition(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, Vec3.func_72443_a(this.drainEntity.field_70165_t, this.drainEntity.field_70163_u, this.drainEntity.field_70161_v));
/*     */       }
/*     */     }
/*     */     
/* 375 */     this.drainColor = nbttagcompound.func_74762_e("draincolor");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 381 */     if (this.id == null) { this.id = generateId();
/*     */     }
/* 383 */     if ((this.field_145850_b != null) && (locations != null)) {
/* 384 */       ArrayList<Integer> t = new ArrayList();
/* 385 */       t.add(Integer.valueOf(this.field_145850_b.field_73011_w.field_76574_g));
/* 386 */       t.add(Integer.valueOf(this.field_145851_c));
/* 387 */       t.add(Integer.valueOf(this.field_145848_d));
/* 388 */       t.add(Integer.valueOf(this.field_145849_e));
/* 389 */       locations.put(this.id, t);
/*     */     }
/*     */     
/* 392 */     nbttagcompound.func_74778_a("nodeId", this.id);
/* 393 */     nbttagcompound.func_74774_a("type", (byte)getNodeType().ordinal());
/* 394 */     nbttagcompound.func_74774_a("modifier", getNodeModifier() == null ? -1 : (byte)getNodeModifier().ordinal());
/* 395 */     this.aspects.writeToNBT(nbttagcompound);
/*     */     
/* 397 */     if ((this.drainEntity != null) && ((this.drainEntity instanceof EntityPlayer))) {
/* 398 */       nbttagcompound.func_74778_a("drainer", this.drainEntity.func_70005_c_());
/*     */     }
/* 400 */     nbttagcompound.func_74768_a("draincolor", this.drainColor);
/*     */   }
/*     */   
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count)
/*     */   {
/* 405 */     boolean mfu = false;
/* 406 */     ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
/* 407 */     MovingObjectPosition movingobjectposition = thaumcraft.common.lib.utils.EntityUtils.getMovingObjectPositionFromPlayer(this.field_145850_b, player, true);
/*     */     
/* 409 */     if ((movingobjectposition == null) || (movingobjectposition.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK))
/*     */     {
/* 411 */       player.func_71034_by();
/*     */     } else {
/* 413 */       int i = movingobjectposition.field_72311_b;
/* 414 */       int j = movingobjectposition.field_72312_c;
/* 415 */       int k = movingobjectposition.field_72309_d;
/* 416 */       if ((i != this.field_145851_c) || (j != this.field_145848_d) || (k != this.field_145849_e)) { player.func_71034_by();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 421 */     if (count % 5 == 0) {
/* 422 */       int tap = 1;
/* 423 */       if (ResearchManager.isResearchComplete(player.func_70005_c_(), "NODETAPPER1")) tap++;
/* 424 */       if (ResearchManager.isResearchComplete(player.func_70005_c_(), "NODETAPPER2")) tap++;
/* 425 */       boolean preserve = (!player.func_70093_af()) && (ResearchManager.isResearchComplete(player.func_70005_c_(), "NODEPRESERVE")) && (!wand.getRod(wandstack).getTag().equals("wood")) && (!wand.getCap(wandstack).getTag().equals("iron"));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 430 */       boolean success = false;
/* 431 */       Aspect aspect = null;
/* 432 */       if ((aspect = chooseRandomFilteredFromSource(wand.getAspectsWithRoom(wandstack), preserve)) != null) {
/* 433 */         int amt = getAspects().getAmount(aspect);
/* 434 */         if (tap > amt) {
/* 435 */           tap = amt;
/*     */         }
/* 437 */         if ((preserve) && (tap == amt)) {
/* 438 */           tap--;
/*     */         }
/* 440 */         if (tap > 0) {
/* 441 */           int rem = wand.addVis(wandstack, aspect, tap, !this.field_145850_b.field_72995_K);
/* 442 */           if (rem < tap) {
/* 443 */             this.drainColor = aspect.getColor();
/* 444 */             if (!this.field_145850_b.field_72995_K) {
/* 445 */               takeFromContainer(aspect, tap - rem);
/* 446 */               mfu = true;
/*     */             }
/* 448 */             success = true;
/*     */           }
/*     */         }
/*     */       }
/* 452 */       if (success) {
/* 453 */         this.drainEntity = player;
/* 454 */         this.drainCollision = movingobjectposition;
/* 455 */         this.targetColor = new Color(this.drainColor);
/*     */       } else {
/* 457 */         this.drainEntity = null;
/* 458 */         this.drainCollision = null;
/*     */       }
/*     */       
/* 461 */       if (mfu) {
/* 462 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 463 */         func_70296_d();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 468 */     if (player.field_70170_p.field_72995_K) {
/* 469 */       int r = this.targetColor.getRed();
/* 470 */       int g = this.targetColor.getGreen();
/* 471 */       int b = this.targetColor.getBlue();
/* 472 */       int r2 = this.color.getRed() * 4;
/* 473 */       int g2 = this.color.getGreen() * 4;
/* 474 */       int b2 = this.color.getBlue() * 4;
/* 475 */       this.color = new Color((r + r2) / 5, (g + g2) / 5, (b + b2) / 5);
/*     */     }
/*     */   }
/*     */   
/* 479 */   public Entity drainEntity = null;
/* 480 */   public MovingObjectPosition drainCollision = null;
/* 481 */   public int drainColor = 16777215;
/* 482 */   public Color targetColor = new Color(16777215);
/* 483 */   public Color color = new Color(16777215);
/*     */   
/*     */ 
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count)
/*     */   {
/* 488 */     this.drainEntity = null;
/* 489 */     this.drainCollision = null;
/*     */   }
/*     */   
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 494 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 500 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amount)
/*     */   {
/* 506 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 512 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 518 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 523 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean handleHungryNodeFirst(boolean change)
/*     */   {
/* 531 */     if (getNodeType() == NodeType.HUNGRY) {
/* 532 */       if (this.field_145850_b.field_72995_K) {
/* 533 */         for (int a = 0; a < Thaumcraft.proxy.particleCount(1); a++) {
/* 534 */           int tx = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 535 */           int ty = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 536 */           int tz = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 537 */           if (ty > this.field_145850_b.func_72976_f(tx, tz)) ty = this.field_145850_b.func_72976_f(tx, tz);
/* 538 */           Vec3 v1 = Vec3.func_72443_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D);
/* 539 */           Vec3 v2 = Vec3.func_72443_a(tx + 0.5D, ty + 0.5D, tz + 0.5D);
/*     */           
/* 541 */           MovingObjectPosition mop = ThaumcraftApiHelper.rayTraceIgnoringSource(this.field_145850_b, v1, v2, true, false, false);
/*     */           
/* 543 */           if ((mop != null) && (func_145835_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) < 256.0D)) {
/* 544 */             tx = mop.field_72311_b;
/* 545 */             ty = mop.field_72312_c;
/* 546 */             tz = mop.field_72309_d;
/* 547 */             Block bi = this.field_145850_b.func_147439_a(tx, ty, tz);
/* 548 */             int md = this.field_145850_b.func_72805_g(tx, ty, tz);
/* 549 */             if (!bi.isAir(this.field_145850_b, tx, ty, tz)) {
/* 550 */               Thaumcraft.proxy.hungryNodeFX(this.field_145850_b, tx, ty, tz, this.field_145851_c, this.field_145848_d, this.field_145849_e, bi, md);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 559 */       if (Config.hardNode) {
/* 560 */         List ents = this.field_145850_b.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(15.0D, 15.0D, 15.0D));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 568 */         if ((ents != null) && (ents.size() > 0))
/* 569 */           for (Object ent : ents) {
/* 570 */             Entity eo = (Entity)ent;
/* 571 */             if ((!(eo instanceof EntityPlayer)) || (!((EntityPlayer)eo).field_71075_bZ.field_75102_a)) {
/* 572 */               if ((eo.func_70089_S()) && (!eo.func_85032_ar())) {
/* 573 */                 double d = getDistanceTo(eo.field_70165_t, eo.field_70163_u, eo.field_70161_v);
/* 574 */                 if (d < 2.0D) {
/* 575 */                   eo.func_70097_a(DamageSource.field_76380_i, 1.0F);
/* 576 */                   if ((!eo.func_70089_S()) && (!this.field_145850_b.field_72995_K)) {
/* 577 */                     ScanResult scan = new ScanResult((byte)2, 0, 0, eo, "");
/* 578 */                     AspectList al = ScanManager.getScanAspects(scan, this.field_145850_b);
/* 579 */                     if ((al != null) && (al.size() > 0)) {
/* 580 */                       al = ResearchManager.reduceToPrimals(al.copy());
/* 581 */                       if ((al != null) && (al.size() > 0)) {
/* 582 */                         Aspect a = al.getAspects()[this.field_145850_b.field_73012_v.nextInt(al.size())];
/* 583 */                         if (getAspects().getAmount(a) < getNodeVisBase(a)) {
/* 584 */                           addToContainer(a, 1);
/* 585 */                           change = true;
/*     */                         }
/* 587 */                         else if (this.field_145850_b.field_73012_v.nextInt(1 + getNodeVisBase(a) * 2) < al.getAmount(a)) {
/* 588 */                           this.aspectsBase.add(a, 1);
/* 589 */                           change = true;
/*     */                         }
/*     */                       }
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */               
/*     */ 
/* 598 */               double var3 = (this.field_145851_c + 0.5D - eo.field_70165_t) / 15.0D;
/* 599 */               double var5 = (this.field_145848_d + 0.5D - eo.field_70163_u) / 15.0D;
/* 600 */               double var7 = (this.field_145849_e + 0.5D - eo.field_70161_v) / 15.0D;
/* 601 */               double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
/* 602 */               double var11 = 1.0D - var9;
/*     */               
/* 604 */               if (var11 > 0.0D)
/*     */               {
/* 606 */                 var11 *= var11;
/* 607 */                 eo.field_70159_w += var3 / var9 * var11 * 0.15D;
/* 608 */                 eo.field_70181_x += var5 / var9 * var11 * 0.25D;
/* 609 */                 eo.field_70179_y += var7 / var9 * var11 * 0.15D;
/*     */               }
/*     */             }
/*     */           }
/*     */       }
/*     */     }
/* 615 */     return change;
/*     */   }
/*     */   
/*     */   private boolean handleDischarge(boolean change) {
/* 619 */     if ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) != ConfigBlocks.blockAiry) || (getLock() == 1)) { return change;
/*     */     }
/* 621 */     if (getNodeModifier() == NodeModifier.FADING) return change;
/* 622 */     boolean shiny = (getNodeType() == NodeType.HUNGRY) || (getNodeModifier() == NodeModifier.BRIGHT);
/* 623 */     int inc = getNodeModifier() == NodeModifier.PALE ? 3 : shiny ? 1 : getNodeModifier() == null ? 2 : 2;
/*     */     
/* 625 */     if (this.count % inc != 0) { return change;
/*     */     }
/* 627 */     int x = this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
/* 628 */     int y = this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
/* 629 */     int z = this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
/*     */     
/* 631 */     if ((getNodeModifier() == NodeModifier.PALE) && (this.field_145850_b.field_73012_v.nextBoolean())) { return change;
/*     */     }
/* 633 */     if ((x != 0) || (y != 0) || (z != 0)) {
/* 634 */       TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);
/* 635 */       if ((te != null) && ((te instanceof INode)) && (this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == ConfigBlocks.blockAiry))
/*     */       {
/*     */ 
/* 638 */         if (((te instanceof TileNode)) && (((TileNode)te).getLock() > 0)) { return change;
/*     */         }
/* 640 */         INode nd = (INode)te;
/*     */         
/* 642 */         int ndavg = (nd.getAspects().visSize() + nd.getAspectsBase().visSize()) / 2;
/* 643 */         int thisavg = (getAspects().visSize() + getAspectsBase().visSize()) / 2;
/* 644 */         if ((ndavg < thisavg) && (nd.getAspects().size() > 0)) {
/* 645 */           Aspect a = nd.getAspects().getAspects()[this.field_145850_b.field_73012_v.nextInt(nd.getAspects().size())];
/* 646 */           boolean u = false;
/* 647 */           if ((getAspects().getAmount(a) < getNodeVisBase(a)) && (nd.takeFromContainer(a, 1)))
/*     */           {
/* 649 */             addToContainer(a, 1);
/* 650 */             u = true;
/*     */           }
/* 652 */           else if (nd.takeFromContainer(a, 1)) {
/* 653 */             if (this.field_145850_b.field_73012_v.nextInt(1 + (int)(getNodeVisBase(a) / (shiny ? 1.5D : 1.0D))) == 0) {
/* 654 */               this.aspectsBase.add(a, 1);
/* 655 */               if ((getNodeModifier() == NodeModifier.PALE) && (this.field_145850_b.field_73012_v.nextInt(100) == 0)) {
/* 656 */                 setNodeModifier(null);
/* 657 */                 this.regeneration = -1;
/*     */               }
/* 659 */               if (this.field_145850_b.field_73012_v.nextInt(3) == 0)
/* 660 */                 nd.setNodeVisBase(a, (short)(nd.getNodeVisBase(a) - 1));
/*     */             }
/* 662 */             u = true;
/*     */           }
/*     */           
/* 665 */           if (u) {
/* 666 */             ((TileNode)te).wait = (((TileNode)te).regeneration / 2);
/* 667 */             this.field_145850_b.func_147471_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);
/* 668 */             te.func_70296_d();
/* 669 */             change = true;
/* 670 */             thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new thaumcraft.common.lib.network.fx.PacketFXBlockZap(this.field_145851_c + x + 0.5F, this.field_145848_d + y + 0.5F, this.field_145849_e + z + 0.5F, this.field_145851_c + 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.5F), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, this.field_145851_c, this.field_145848_d, this.field_145849_e, 32.0D));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 680 */     return change;
/*     */   }
/*     */   
/*     */   private boolean handleRecharge(boolean change)
/*     */   {
/* 685 */     if (this.regeneration < 0) {
/* 686 */       this.regeneration = 600;
/* 687 */       if (getNodeModifier() != null) {
/* 688 */         switch (getNodeModifier()) {
/* 689 */         case BRIGHT:  this.regeneration = 400; break;
/* 690 */         case PALE:  this.regeneration = 900; break;
/* 691 */         case FADING:  this.regeneration = 0;
/*     */         }
/*     */       }
/* 694 */       if (getLock() == 1) {
/* 695 */         this.regeneration *= 2;
/*     */       }
/*     */       
/* 698 */       if (getLock() == 2) {
/* 699 */         this.regeneration *= 20;
/*     */       }
/*     */     }
/*     */     
/* 703 */     if (this.catchUp) {
/* 704 */       this.catchUp = false;
/*     */       
/* 706 */       long ct = System.currentTimeMillis();
/* 707 */       int inc = this.regeneration * 75;
/*     */       
/* 709 */       int amt = inc > 0 ? (int)((ct - this.lastActive) / inc) : 0;
/* 710 */       if (amt > 0) {
/* 711 */         for (int a = 0; a < Math.min(amt, this.aspectsBase.visSize()); a++) {
/* 712 */           AspectList al = new AspectList();
/* 713 */           for (Aspect aspect : getAspects().getAspects()) {
/* 714 */             if (getAspects().getAmount(aspect) < getNodeVisBase(aspect)) {
/* 715 */               al.add(aspect, 1);
/*     */             }
/*     */           }
/* 718 */           if (al.size() > 0) {
/* 719 */             addToContainer(al.getAspects()[this.field_145850_b.field_73012_v.nextInt(al.size())], 1);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 726 */     if (this.count % 1200 == 0) {
/* 727 */       for (Aspect aspect : getAspects().getAspects()) {
/* 728 */         if (getAspects().getAmount(aspect) <= 0)
/*     */         {
/* 730 */           setNodeVisBase(aspect, (short)(getNodeVisBase(aspect) - 1));
/*     */           
/* 732 */           if ((this.field_145850_b.field_73012_v.nextInt(20) == 0) || (getNodeVisBase(aspect) <= 0)) {
/* 733 */             getAspects().remove(aspect);
/* 734 */             if (this.field_145850_b.field_73012_v.nextInt(5) == 0) {
/* 735 */               if (getNodeModifier() == NodeModifier.BRIGHT) {
/* 736 */                 setNodeModifier(null);
/*     */               }
/* 738 */               else if (getNodeModifier() == null) {
/* 739 */                 setNodeModifier(NodeModifier.PALE);
/*     */               }
/* 741 */               if ((getNodeModifier() == NodeModifier.PALE) && (this.field_145850_b.field_73012_v.nextInt(5) == 0)) {
/* 742 */                 setNodeModifier(NodeModifier.FADING);
/*     */               }
/*     */             }
/* 745 */             nodeChange();
/* 746 */             break;
/*     */           }
/* 748 */           nodeChange();
/*     */         }
/*     */       }
/*     */       
/* 752 */       if (getAspects().size() <= 0) {
/* 753 */         func_145843_s();
/* 754 */         if (func_145838_q() == ConfigBlocks.blockAiry) {
/* 755 */           this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 756 */         } else if (func_145838_q() == ConfigBlocks.blockMagicalLog)
/* 757 */           this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) - 1, 3);
/*     */       }
/*     */     }
/* 760 */     if (this.wait > 0) this.wait -= 1;
/* 761 */     if ((this.regeneration > 0) && (this.wait == 0) && (this.count % this.regeneration == 0)) {
/* 762 */       this.lastActive = System.currentTimeMillis();
/* 763 */       AspectList al = new AspectList();
/* 764 */       for (Aspect aspect : getAspects().getAspects()) {
/* 765 */         if (getAspects().getAmount(aspect) < getNodeVisBase(aspect)) {
/* 766 */           al.add(aspect, 1);
/*     */         }
/*     */       }
/* 769 */       if (al.size() > 0) {
/* 770 */         addToContainer(al.getAspects()[this.field_145850_b.field_73012_v.nextInt(al.size())], 1);
/* 771 */         change = true;
/*     */       }
/*     */     }
/* 774 */     return change;
/*     */   }
/*     */   
/*     */   private boolean handleTaintNode(boolean change)
/*     */   {
/* 779 */     if ((getNodeType() == NodeType.TAINTED) && (this.count % 50 == 0)) {
/* 780 */       int x = 0;int z = 0;int y = 0;
/* 781 */       x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
/* 782 */       z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
/* 783 */       BiomeGenBase bg = this.field_145850_b.func_72807_a(x, z);
/* 784 */       if (bg.field_76756_M != ThaumcraftWorldGenerator.biomeTaint.field_76756_M) {
/* 785 */         Utils.setBiomeAt(this.field_145850_b, x, z, ThaumcraftWorldGenerator.biomeTaint);
/*     */       }
/* 787 */       if ((Config.hardNode) && (this.field_145850_b.field_73012_v.nextBoolean())) {
/* 788 */         x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
/* 789 */         z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
/* 790 */         y = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
/* 791 */         if (!thaumcraft.common.blocks.BlockTaintFibres.spreadFibres(this.field_145850_b, x, y, z)) {}
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 797 */     else if ((getNodeType() != NodeType.PURE) && (getNodeType() != NodeType.TAINTED) && (this.count % 100 == 0)) {
/* 798 */       BiomeGenBase bg = this.field_145850_b.func_72807_a(this.field_145851_c, this.field_145849_e);
/* 799 */       if ((bg.field_76756_M == ThaumcraftWorldGenerator.biomeTaint.field_76756_M) && 
/* 800 */         (this.field_145850_b.field_73012_v.nextInt(500) == 0)) {
/* 801 */         setNodeType(NodeType.TAINTED);
/* 802 */         nodeChange();
/*     */       }
/*     */     }
/*     */     
/* 806 */     return change;
/*     */   }
/*     */   
/*     */   private boolean handleNodeStability(boolean change) {
/* 810 */     if (this.count % 100 == 0)
/*     */     {
/* 812 */       if ((getNodeType() == NodeType.UNSTABLE) && (this.field_145850_b.field_73012_v.nextBoolean()))
/*     */       {
/* 814 */         if (getLock() == 0) {
/* 815 */           Aspect aspect = null;
/* 816 */           if ((aspect = takeRandomPrimalFromSource()) != null) {
/* 817 */             EntityAspectOrb orb = new EntityAspectOrb(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, aspect, 1);
/*     */             
/* 819 */             this.field_145850_b.func_72838_d(orb);
/* 820 */             change = true;
/*     */           }
/*     */         }
/* 823 */         else if (this.field_145850_b.field_73012_v.nextInt(10000 / getLock()) == 42) {
/* 824 */           setNodeType(NodeType.NORMAL);
/* 825 */           change = true;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 830 */       if ((getNodeModifier() == NodeModifier.FADING) && (getLock() > 0) && (this.field_145850_b.field_73012_v.nextInt(12500 / getLock()) == 69))
/*     */       {
/* 832 */         setNodeModifier(NodeModifier.PALE);
/* 833 */         change = true;
/*     */       }
/*     */     }
/* 836 */     return change;
/*     */   }
/*     */   
/*     */   private boolean handlePureNode(boolean change)
/*     */   {
/* 841 */     int dimbl = ThaumcraftWorldGenerator.getDimBlacklist(this.field_145850_b.field_73011_w.field_76574_g);
/* 842 */     if ((this.field_145850_b.field_73011_w.field_76574_g != -1) && (this.field_145850_b.field_73011_w.field_76574_g != 1) && (dimbl != 0) && (dimbl != 2) && (getNodeType() == NodeType.PURE) && (this.count % 50 == 0))
/*     */     {
/*     */ 
/* 845 */       int x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
/* 846 */       int z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
/* 847 */       BiomeGenBase bg = this.field_145850_b.func_72807_a(x, z);
/* 848 */       int biobl = ThaumcraftWorldGenerator.getBiomeBlacklist(bg.field_76756_M);
/* 849 */       if ((biobl != 0) && (biobl != 2) && (bg.field_76756_M != ThaumcraftWorldGenerator.biomeMagicalForest.field_76756_M))
/*     */       {
/* 851 */         if (bg.field_76756_M == ThaumcraftWorldGenerator.biomeTaint.field_76756_M) {
/* 852 */           Utils.setBiomeAt(this.field_145850_b, x, z, ThaumcraftWorldGenerator.biomeMagicalForest);
/* 853 */         } else if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) == ConfigBlocks.blockMagicalLog) {
/* 854 */           Utils.setBiomeAt(this.field_145850_b, x, z, ThaumcraftWorldGenerator.biomeMagicalForest);
/*     */         }
/*     */       }
/*     */     }
/* 858 */     return change;
/*     */   }
/*     */   
/*     */   private boolean handleDarkNode(boolean change)
/*     */   {
/* 863 */     int dimbl = ThaumcraftWorldGenerator.getDimBlacklist(this.field_145850_b.field_73011_w.field_76574_g);
/* 864 */     int biobl = ThaumcraftWorldGenerator.getBiomeBlacklist(this.field_145850_b.func_72807_a(this.field_145851_c, this.field_145849_e).field_76756_M);
/* 865 */     if ((biobl != 0) && (biobl != 2) && (this.field_145850_b.field_73011_w.field_76574_g != -1) && (this.field_145850_b.field_73011_w.field_76574_g != 1) && (dimbl != 0) && (dimbl != 2) && (getNodeType() == NodeType.DARK) && (this.count % 50 == 0))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 870 */       int x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(12) - this.field_145850_b.field_73012_v.nextInt(12);
/* 871 */       int z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(12) - this.field_145850_b.field_73012_v.nextInt(12);
/* 872 */       BiomeGenBase bg = this.field_145850_b.func_72807_a(x, z);
/* 873 */       if (bg.field_76756_M != ThaumcraftWorldGenerator.biomeEerie.field_76756_M) {
/* 874 */         Utils.setBiomeAt(this.field_145850_b, x, z, ThaumcraftWorldGenerator.biomeEerie);
/*     */       }
/* 876 */       if ((Config.hardNode) && (this.field_145850_b.field_73012_v.nextBoolean()) && (this.field_145850_b.func_72977_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, 24.0D) != null))
/*     */       {
/* 878 */         EntityGiantBrainyZombie entity = new EntityGiantBrainyZombie(this.field_145850_b);
/* 879 */         if (entity != null)
/*     */         {
/* 881 */           int j = this.field_145850_b.func_72872_a(entity.getClass(), AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(10.0D, 6.0D, 10.0D)).size();
/*     */           
/* 883 */           if (j <= 3)
/*     */           {
/* 885 */             double d0 = this.field_145851_c + (this.field_145850_b.field_73012_v.nextDouble() - this.field_145850_b.field_73012_v.nextDouble()) * 5.0D;
/* 886 */             double d3 = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(3) - 1;
/* 887 */             double d4 = this.field_145849_e + (this.field_145850_b.field_73012_v.nextDouble() - this.field_145850_b.field_73012_v.nextDouble()) * 5.0D;
/* 888 */             EntityLiving entityliving = (entity instanceof EntityLiving) ? entity : null;
/* 889 */             entity.func_70012_b(d0, d3, d4, this.field_145850_b.field_73012_v.nextFloat() * 360.0F, 0.0F);
/*     */             
/* 891 */             if ((entityliving == null) || (entityliving.func_70601_bi()))
/*     */             {
/* 893 */               this.field_145850_b.func_72838_d(entityliving);
/* 894 */               this.field_145850_b.func_72926_e(2004, this.field_145851_c, this.field_145848_d, this.field_145849_e, 0);
/*     */               
/* 896 */               if (entityliving != null)
/*     */               {
/* 898 */                 entityliving.func_70656_aK();
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 906 */     return change;
/*     */   }
/*     */   
/*     */   private boolean handleHungryNodeSecond(boolean change)
/*     */   {
/* 911 */     if ((getNodeType() == NodeType.HUNGRY) && (this.count % 50 == 0))
/*     */     {
/* 913 */       int tx = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 914 */       int ty = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 915 */       int tz = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 916 */       if (ty > this.field_145850_b.func_72976_f(tx, tz)) { ty = this.field_145850_b.func_72976_f(tx, tz);
/*     */       }
/* 918 */       Vec3 v1 = Vec3.func_72443_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D);
/* 919 */       Vec3 v2 = Vec3.func_72443_a(tx + 0.5D, ty + 0.5D, tz + 0.5D);
/*     */       
/* 921 */       MovingObjectPosition mop = ThaumcraftApiHelper.rayTraceIgnoringSource(this.field_145850_b, v1, v2, true, false, false);
/*     */       
/* 923 */       if ((mop != null) && (func_145835_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) < 256.0D)) {
/* 924 */         tx = mop.field_72311_b;
/* 925 */         ty = mop.field_72312_c;
/* 926 */         tz = mop.field_72309_d;
/*     */         
/* 928 */         Block bi = this.field_145850_b.func_147439_a(tx, ty, tz);
/* 929 */         int md = this.field_145850_b.func_72805_g(tx, ty, tz);
/* 930 */         if (!bi.isAir(this.field_145850_b, tx, ty, tz)) {
/* 931 */           float h = bi.func_149712_f(this.field_145850_b, tx, ty, tz);
/* 932 */           if ((h >= 0.0F) && (h < 5.0F)) {
/* 933 */             this.field_145850_b.func_147480_a(tx, ty, tz, true);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 939 */     return change;
/*     */   }
/*     */   
/*     */   public byte getLock() {
/* 943 */     return this.nodeLock;
/*     */   }
/*     */   
/*     */   public void checkLock() {
/* 947 */     if (((this.count <= 1) || (this.count % 50 == 0)) && (this.field_145848_d > 0) && (func_145838_q() == ConfigBlocks.blockAiry)) {
/* 948 */       byte oldLock = this.nodeLock;
/* 949 */       this.nodeLock = 0;
/* 950 */       if ((!this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)) && 
/* 951 */         (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockStoneDevice)) {
/* 952 */         if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 9) {
/* 953 */           this.nodeLock = 1;
/*     */         }
/* 955 */         else if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 10) {
/* 956 */           this.nodeLock = 2;
/*     */         }
/*     */       }
/*     */       
/* 960 */       if (oldLock != this.nodeLock) {
/* 961 */         this.regeneration = -1;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */