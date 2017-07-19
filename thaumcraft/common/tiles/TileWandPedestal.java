/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.common.items.baubles.ItemAmuletVis;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ 
/*     */ public class TileWandPedestal extends TileThaumcraft implements net.minecraft.inventory.ISidedInventory, IAspectContainer
/*     */ {
/*  29 */   private static final int[] slots = { 0 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  34 */   private ItemStack[] inventory = new ItemStack[1];
/*     */   
/*     */   private String customName;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  41 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(2.0D, 2.0D, 2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  52 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  61 */     return this.inventory[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  73 */     if (this.inventory[par1] != null)
/*     */     {
/*     */ 
/*  76 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  77 */       if (this.inventory[par1].field_77994_a <= par2)
/*     */       {
/*  79 */         ItemStack itemstack = this.inventory[par1];
/*  80 */         this.inventory[par1] = null;
/*  81 */         func_70296_d();
/*  82 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/*  86 */       ItemStack itemstack = this.inventory[par1].func_77979_a(par2);
/*     */       
/*  88 */       if (this.inventory[par1].field_77994_a == 0)
/*     */       {
/*  90 */         this.inventory[par1] = null;
/*     */       }
/*  92 */       func_70296_d();
/*  93 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  98 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/* 109 */     if (this.inventory[par1] != null)
/*     */     {
/* 111 */       ItemStack itemstack = this.inventory[par1];
/* 112 */       this.inventory[par1] = null;
/* 113 */       func_70296_d();
/* 114 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/* 118 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 128 */     this.inventory[par1] = par2ItemStack;
/*     */     
/* 130 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 132 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/* 134 */     func_70296_d();
/* 135 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String func_145825_b()
/*     */   {
/* 145 */     return func_145818_k_() ? this.customName : "container.wandpedestal";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 155 */     return (this.customName != null) && (this.customName.length() > 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGuiDisplayName(String par1Str)
/*     */   {
/* 163 */     this.customName = par1Str;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 170 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 171 */     this.inventory = new ItemStack[func_70302_i_()];
/*     */     
/* 173 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 175 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 176 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/* 178 */       if ((b0 >= 0) && (b0 < this.inventory.length))
/*     */       {
/* 180 */         this.inventory[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 187 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 189 */     for (int i = 0; i < this.inventory.length; i++)
/*     */     {
/* 191 */       if (this.inventory[i] != null)
/*     */       {
/* 193 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 194 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 195 */         this.inventory[i].func_77955_b(nbttagcompound1);
/* 196 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/* 200 */     nbttagcompound.func_74782_a("Items", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 209 */     super.func_145839_a(nbtCompound);
/*     */     
/* 211 */     if (nbtCompound.func_74764_b("CustomName"))
/*     */     {
/* 213 */       this.customName = nbtCompound.func_74779_i("CustomName");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 224 */     super.func_145841_b(nbtCompound);
/*     */     
/* 226 */     if (func_145818_k_())
/*     */     {
/* 228 */       nbtCompound.func_74778_a("CustomName", this.customName);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 240 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*     */   {
/* 246 */     super.onDataPacket(net, pkt);
/* 247 */     if ((this.field_145850_b != null) && (this.field_145850_b.field_72995_K)) {
/* 248 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/* 254 */     return true;
/*     */   }
/*     */   
/* 257 */   int counter = 0;
/* 258 */   boolean somethingChanged = false;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 262 */     super.func_145845_h();
/* 263 */     if (this.nodes == null) {
/* 264 */       findNodes();
/*     */     }
/*     */     
/* 267 */     this.counter += 1;
/*     */     
/* 269 */     boolean recalc = false;
/* 270 */     if ((this.counter % 20 == 0) && (this.somethingChanged) && (this.nodes != null) && (this.nodes.size() > 0) && (func_70301_a(0) != null)) {
/* 271 */       this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q());
/* 272 */       this.somethingChanged = false;
/*     */     }
/* 274 */     if ((this.counter % 5 == 0) && (this.nodes != null) && (this.nodes.size() > 0) && (func_70301_a(0) != null)) {
/* 275 */       boolean hasThingy = false;
/* 276 */       if ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == thaumcraft.common.config.ConfigBlocks.blockStoneDevice) && (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == 8))
/*     */       {
/* 278 */         hasThingy = true;
/*     */       }
/* 280 */       if ((func_70301_a(0).func_77973_b() instanceof ItemWandCasting)) {
/* 281 */         ItemWandCasting wand = (ItemWandCasting)func_70301_a(0).func_77973_b();
/* 282 */         int min = 1;
/* 283 */         if ((wand.getCap(func_70301_a(0)).getTag().equals("iron")) || (wand.getRod(func_70301_a(0)).getTag().equals("wood")))
/*     */         {
/* 285 */           min = 0;
/*     */         }
/* 287 */         AspectList as = wand.getAspectsWithRoom(func_70301_a(0));
/* 288 */         this.draining = false;
/* 289 */         if ((as != null) && (as.size() > 0))
/*     */         {
/* 291 */           for (ChunkCoordinates co : this.nodes) {
/* 292 */             TileEntity te = this.field_145850_b.func_147438_o(co.field_71574_a, co.field_71572_b, co.field_71573_c);
/* 293 */             if ((te != null) && ((te instanceof INode)) && (!(te instanceof TileJarNode))) {
/* 294 */               INode node = (INode)te;
/*     */               
/* 296 */               for (Aspect aspect : as.getAspects()) {
/* 297 */                 if (node.getAspects().getAmount(aspect) > min)
/*     */                 {
/* 299 */                   wand.addVis(func_70301_a(0), aspect, 1, true);
/* 300 */                   node.takeFromContainer(aspect, 1);
/* 301 */                   this.somethingChanged = true;
/* 302 */                   this.draining = true;
/* 303 */                   if (!this.field_145850_b.field_72995_K) break label734;
/* 304 */                   this.drainX = co.field_71574_a;
/* 305 */                   this.drainY = co.field_71572_b;
/* 306 */                   this.drainZ = co.field_71573_c;
/* 307 */                   this.drainColor = aspect.getColor();
/*     */                   
/*     */ 
/*     */                   break label734;
/*     */                 }
/*     */               }
/*     */               
/* 314 */               if (hasThingy)
/*     */               {
/* 316 */                 for (Aspect aspect : node.getAspects().getAspects()) {
/* 317 */                   if ((aspect != null) && (!aspect.isPrimal())) {
/* 318 */                     AspectList primals = ResearchManager.reduceToPrimals(new AspectList().add(aspect, 1));
/* 319 */                     for (Aspect aspect2 : as.getAspects()) {
/* 320 */                       if ((primals.getAmount(aspect2) > 0) && (node.getAspects().getAmount(aspect) > min)) {
/* 321 */                         wand.addVis(func_70301_a(0), aspect2, 1, true);
/* 322 */                         node.takeFromContainer(aspect, 1);
/* 323 */                         this.somethingChanged = true;
/* 324 */                         this.draining = true;
/* 325 */                         if (!this.field_145850_b.field_72995_K) break label734;
/* 326 */                         this.drainX = co.field_71574_a;
/* 327 */                         this.drainY = co.field_71572_b;
/* 328 */                         this.drainZ = co.field_71573_c;
/* 329 */                         this.drainColor = aspect.getColor();
/*     */                         break label734;
/*     */                       }
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           label734:
/* 339 */           if (!this.draining) {
/* 340 */             recalc = true;
/*     */ 
/*     */ 
/*     */ 
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 355 */       else if ((func_70301_a(0).func_77973_b() instanceof ItemAmuletVis)) {
/* 356 */         ItemAmuletVis amulet = (ItemAmuletVis)func_70301_a(0).func_77973_b();
/* 357 */         int min = 1;
/*     */         
/* 359 */         AspectList as = amulet.getAspectsWithRoom(func_70301_a(0));
/* 360 */         this.draining = false;
/* 361 */         if ((as != null) && (as.size() > 0))
/*     */         {
/* 363 */           for (ChunkCoordinates co : this.nodes) {
/* 364 */             TileEntity te = this.field_145850_b.func_147438_o(co.field_71574_a, co.field_71572_b, co.field_71573_c);
/* 365 */             if ((te != null) && ((te instanceof INode)) && (!(te instanceof TileJarNode))) {
/* 366 */               INode node = (INode)te;
/*     */               
/* 368 */               for (Aspect aspect : as.getAspects()) {
/* 369 */                 if (node.getAspects().getAmount(aspect) > min)
/*     */                 {
/* 371 */                   amulet.addVis(func_70301_a(0), aspect, 1, true);
/* 372 */                   node.takeFromContainer(aspect, 1);
/* 373 */                   this.draining = true;
/* 374 */                   if (!this.field_145850_b.field_72995_K) break label1237;
/* 375 */                   this.drainX = co.field_71574_a;
/* 376 */                   this.drainY = co.field_71572_b;
/* 377 */                   this.drainZ = co.field_71573_c;
/* 378 */                   this.drainColor = aspect.getColor();
/*     */                   
/*     */ 
/*     */                   break label1237;
/*     */                 }
/*     */               }
/*     */               
/* 385 */               if (hasThingy)
/*     */               {
/* 387 */                 for (Aspect aspect : node.getAspects().getAspects()) {
/* 388 */                   if ((aspect != null) && (!aspect.isPrimal())) {
/* 389 */                     AspectList primals = ResearchManager.reduceToPrimals(new AspectList().add(aspect, 1));
/* 390 */                     for (Aspect aspect2 : as.getAspects()) {
/* 391 */                       if ((primals.getAmount(aspect2) > 0) && (node.getAspects().getAmount(aspect) > min)) {
/* 392 */                         amulet.addVis(func_70301_a(0), aspect2, 1, true);
/* 393 */                         node.takeFromContainer(aspect, 1);
/* 394 */                         this.draining = true;
/* 395 */                         if (!this.field_145850_b.field_72995_K) break label1237;
/* 396 */                         this.drainX = co.field_71574_a;
/* 397 */                         this.drainY = co.field_71572_b;
/* 398 */                         this.drainZ = co.field_71573_c;
/* 399 */                         this.drainColor = aspect.getColor();
/*     */                         break label1237;
/*     */                       }
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           label1237:
/* 409 */           if (!this.draining) {
/* 410 */             recalc = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
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
/* 426 */     if ((this.counter % 100 == 0) && ((recalc) || (this.nodes.size() == 0)))
/* 427 */       findNodes();
/*     */   }
/*     */   
/* 430 */   public boolean draining = false;
/* 431 */   public int drainX = 0;
/* 432 */   public int drainY = 0;
/* 433 */   public int drainZ = 0;
/* 434 */   public int drainColor = 0;
/*     */   
/* 436 */   ArrayList<ChunkCoordinates> nodes = null;
/*     */   
/*     */   private void findNodes() {
/* 439 */     this.nodes = new ArrayList();
/* 440 */     for (int xx = -8; xx <= 8; xx++) {
/* 441 */       for (int yy = -8; yy <= 8; yy++) {
/* 442 */         for (int zz = -8; zz <= 8; zz++) {
/* 443 */           TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c + xx, this.field_145848_d + yy, this.field_145849_e + zz);
/* 444 */           if ((te instanceof INode)) {
/* 445 */             this.nodes.add(new ChunkCoordinates(this.field_145851_c + xx, this.field_145848_d + yy, this.field_145849_e + zz));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 456 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70295_k_() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70305_f() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 471 */     return (par2ItemStack != null) && (((par2ItemStack.func_77973_b() instanceof ItemWandCasting)) || ((par2ItemStack.func_77973_b() instanceof ItemAmuletVis)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] func_94128_d(int par1)
/*     */   {
/* 483 */     return slots;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 493 */     return (func_70301_a(par1) == null) && (((par2ItemStack.func_77973_b() instanceof ItemWandCasting)) || ((par2ItemStack.func_77973_b() instanceof ItemAmuletVis)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 505 */     return true;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 510 */     if ((func_70301_a(0) != null) && ((func_70301_a(0).func_77973_b() instanceof ItemWandCasting)))
/*     */     {
/* 512 */       ItemWandCasting wand = (ItemWandCasting)func_70301_a(0).func_77973_b();
/* 513 */       AspectList al = wand.getAllVis(func_70301_a(0));
/* 514 */       AspectList out = new AspectList();
/* 515 */       for (Aspect a : al.getAspectsSorted()) {
/* 516 */         out.add(a, al.getAmount(a) / 100);
/*     */       }
/* 518 */       return out;
/*     */     }
/* 520 */     if ((func_70301_a(0) != null) && ((func_70301_a(0).func_77973_b() instanceof ItemAmuletVis)))
/*     */     {
/* 522 */       ItemAmuletVis amulet = (ItemAmuletVis)func_70301_a(0).func_77973_b();
/* 523 */       AspectList al = amulet.getAllVis(func_70301_a(0));
/* 524 */       AspectList out = new AspectList();
/* 525 */       for (Aspect a : al.getAspectsSorted()) {
/* 526 */         out.add(a, al.getAmount(a) / 100);
/*     */       }
/* 528 */       return out;
/*     */     }
/* 530 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public int addToContainer(Aspect tag, int amount)
/*     */   {
/* 542 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(Aspect tag, int amount)
/*     */   {
/* 548 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 554 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amount)
/*     */   {
/* 560 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 566 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 572 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 577 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileWandPedestal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */