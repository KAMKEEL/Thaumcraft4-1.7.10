/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.particle.EntitySpellParticleFX;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.visnet.VisNetHandler;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class TileMirror extends TileThaumcraft implements IInventory
/*     */ {
/*  31 */   public boolean linked = false;
/*     */   
/*     */   public int linkX;
/*     */   
/*     */   public int linkY;
/*     */   public int linkZ;
/*     */   public int linkDim;
/*     */   public int instability;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  42 */     return true;
/*     */   }
/*     */   
/*     */   public void restoreLink() {
/*  46 */     if (isDestinationValid()) {
/*  47 */       World targetWorld = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
/*  48 */       if (targetWorld == null) return;
/*  49 */       TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/*  50 */       if ((te != null) && ((te instanceof TileMirror))) {
/*  51 */         TileMirror tm = (TileMirror)te;
/*  52 */         tm.linked = true;
/*  53 */         tm.linkX = this.field_145851_c;
/*  54 */         tm.linkY = this.field_145848_d;
/*  55 */         tm.linkZ = this.field_145849_e;
/*  56 */         tm.linkDim = this.field_145850_b.field_73011_w.field_76574_g;
/*  57 */         targetWorld.func_147471_g(tm.field_145851_c, tm.field_145848_d, tm.field_145849_e);
/*  58 */         this.linked = true;
/*  59 */         func_70296_d();
/*  60 */         tm.func_70296_d();
/*  61 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void invalidateLink() {
/*  67 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/*  68 */     if (targetWorld == null) return;
/*  69 */     if (!Utils.isChunkLoaded(targetWorld, this.linkX, this.linkZ)) return;
/*  70 */     TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/*  71 */     if ((te != null) && ((te instanceof TileMirror))) {
/*  72 */       TileMirror tm = (TileMirror)te;
/*  73 */       tm.linked = false;
/*  74 */       func_70296_d();
/*  75 */       tm.func_70296_d();
/*  76 */       targetWorld.func_147471_g(this.linkX, this.linkY, this.linkZ);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isLinkValid()
/*     */   {
/*  82 */     if (!this.linked) return false;
/*  83 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/*  84 */     if (targetWorld == null) {
/*  85 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  90 */     TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/*  91 */     if ((te == null) || (!(te instanceof TileMirror))) {
/*  92 */       this.linked = false;
/*  93 */       func_70296_d();
/*  94 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  95 */       return false;
/*     */     }
/*  97 */     TileMirror tm = (TileMirror)te;
/*  98 */     if (!tm.linked) {
/*  99 */       this.linked = false;
/* 100 */       func_70296_d();
/* 101 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 102 */       return false;
/*     */     }
/* 104 */     if ((tm.linkX != this.field_145851_c) || (tm.linkY != this.field_145848_d) || (tm.linkZ != this.field_145849_e) || (tm.linkDim != this.field_145850_b.field_73011_w.field_76574_g))
/*     */     {
/*     */ 
/* 107 */       this.linked = false;
/* 108 */       func_70296_d();
/* 109 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 110 */       return false;
/*     */     }
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isLinkValidSimple() {
/* 116 */     if (!this.linked) return false;
/* 117 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/* 118 */     if (targetWorld == null) {
/* 119 */       return false;
/*     */     }
/*     */     
/* 122 */     TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/* 123 */     if ((te == null) || (!(te instanceof TileMirror))) {
/* 124 */       return false;
/*     */     }
/* 126 */     TileMirror tm = (TileMirror)te;
/* 127 */     if (!tm.linked) {
/* 128 */       return false;
/*     */     }
/* 130 */     if ((tm.linkX != this.field_145851_c) || (tm.linkY != this.field_145848_d) || (tm.linkZ != this.field_145849_e) || (tm.linkDim != this.field_145850_b.field_73011_w.field_76574_g))
/*     */     {
/*     */ 
/* 133 */       return false;
/*     */     }
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isDestinationValid() {
/* 139 */     World targetWorld = DimensionManager.getWorld(this.linkDim);
/* 140 */     if (targetWorld == null) {
/* 141 */       return false;
/*     */     }
/*     */     
/* 144 */     TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/* 145 */     if ((te == null) || (!(te instanceof TileMirror))) {
/* 146 */       this.linked = false;
/* 147 */       func_70296_d();
/* 148 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 149 */       return false;
/*     */     }
/*     */     
/* 152 */     TileMirror tm = (TileMirror)te;
/* 153 */     if (tm.isLinkValid()) {
/* 154 */       return false;
/*     */     }
/* 156 */     return true;
/*     */   }
/*     */   
/*     */   public boolean transport(EntityItem ie) {
/* 160 */     ItemStack items = ie.func_92059_d();
/* 161 */     if ((!this.linked) || (!isLinkValid())) return false;
/* 162 */     World world = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
/* 163 */     TileEntity target = world.func_147438_o(this.linkX, this.linkY, this.linkZ);
/* 164 */     if ((target != null) && ((target instanceof TileMirror))) {
/* 165 */       ((TileMirror)target).addStack(items);
/* 166 */       addInstability(null, items.field_77994_a);
/* 167 */       ie.func_70106_y();
/* 168 */       func_70296_d();
/* 169 */       target.func_70296_d();
/* 170 */       this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMirror, 1, 0);
/* 171 */       return true;
/*     */     }
/* 173 */     return false;
/*     */   }
/*     */   
/*     */   public void eject() {
/* 177 */     if ((this.outputStacks.size() > 0) && (this.count > 20)) {
/* 178 */       int i = this.field_145850_b.field_73012_v.nextInt(this.outputStacks.size());
/* 179 */       if (this.outputStacks.get(i) != null) {
/* 180 */         ItemStack outItem = ((ItemStack)this.outputStacks.get(i)).func_77946_l();
/* 181 */         outItem.field_77994_a = 1;
/* 182 */         if (spawnItem(outItem)) {
/* 183 */           ((ItemStack)this.outputStacks.get(i)).field_77994_a -= 1;
/* 184 */           addInstability(null, 1);
/* 185 */           this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMirror, 1, 0);
/* 186 */           if (((ItemStack)this.outputStacks.get(i)).field_77994_a <= 0) {
/* 187 */             this.outputStacks.remove(i);
/*     */           }
/* 189 */           func_70296_d();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean spawnItem(ItemStack stack) {
/*     */     try {
/* 197 */       ForgeDirection face = ForgeDirection.getOrientation(func_145832_p());
/* 198 */       EntityItem ie2 = new EntityItem(this.field_145850_b, this.field_145851_c + 0.5D - face.offsetX * 0.3D, this.field_145848_d + 0.5D - face.offsetY * 0.3D, this.field_145849_e + 0.5D - face.offsetZ * 0.3D, stack);
/*     */       
/*     */ 
/*     */ 
/* 202 */       ie2.field_70159_w = (face.offsetX * 0.15F);
/* 203 */       ie2.field_70181_x = (face.offsetY * 0.15F);
/* 204 */       ie2.field_70179_y = (face.offsetZ * 0.15F);
/* 205 */       ie2.field_71088_bW = 20;
/* 206 */       this.field_145850_b.func_72838_d(ie2);
/* 207 */       return true;
/*     */     } catch (Exception e) {}
/* 209 */     return false;
/*     */   }
/*     */   
/*     */   protected void addInstability(World targetWorld, int amt)
/*     */   {
/* 214 */     this.instability += amt;
/* 215 */     if (targetWorld != null) {
/* 216 */       TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
/* 217 */       if ((te != null) && ((te instanceof TileMirror))) {
/* 218 */         ((TileMirror)te).instability += amt;
/* 219 */         if (((TileMirror)te).instability < 0) ((TileMirror)te).instability = 0;
/* 220 */         te.func_70296_d();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 229 */     super.readCustomNBT(nbttagcompound);
/* 230 */     this.linked = nbttagcompound.func_74767_n("linked");
/* 231 */     this.linkX = nbttagcompound.func_74762_e("linkX");
/* 232 */     this.linkY = nbttagcompound.func_74762_e("linkY");
/* 233 */     this.linkZ = nbttagcompound.func_74762_e("linkZ");
/* 234 */     this.linkDim = nbttagcompound.func_74762_e("linkDim");
/* 235 */     this.instability = nbttagcompound.func_74762_e("instability");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 241 */     super.writeCustomNBT(nbttagcompound);
/* 242 */     nbttagcompound.func_74757_a("linked", this.linked);
/* 243 */     nbttagcompound.func_74768_a("linkX", this.linkX);
/* 244 */     nbttagcompound.func_74768_a("linkY", this.linkY);
/* 245 */     nbttagcompound.func_74768_a("linkZ", this.linkZ);
/* 246 */     nbttagcompound.func_74768_a("linkDim", this.linkDim);
/* 247 */     nbttagcompound.func_74768_a("instability", this.instability);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 257 */     if (i == 1)
/*     */     {
/* 259 */       if (this.field_145850_b.field_72995_K)
/*     */       {
/* 261 */         ForgeDirection face = ForgeDirection.getOrientation(func_145832_p());
/* 262 */         for (int q = 0; q < Thaumcraft.proxy.particleCount(1); q++)
/*     */         {
/* 264 */           double xx = this.field_145851_c + 0.33D + this.field_145850_b.field_73012_v.nextFloat() * 0.33F - face.offsetX / 2.0D;
/* 265 */           double yy = this.field_145848_d + 0.33D + this.field_145850_b.field_73012_v.nextFloat() * 0.33F - face.offsetY / 2.0D;
/* 266 */           double zz = this.field_145849_e + 0.33D + this.field_145850_b.field_73012_v.nextFloat() * 0.33F - face.offsetZ / 2.0D;
/*     */           
/* 268 */           EntitySpellParticleFX var21 = new EntitySpellParticleFX(this.field_145850_b, xx, yy, zz, 0.0D, 0.0D, 0.0D);
/*     */           
/* 270 */           var21.field_70159_w = (face.offsetX * 0.05D);
/* 271 */           var21.field_70181_x = (face.offsetY * 0.05D);
/* 272 */           var21.field_70179_y = (face.offsetZ * 0.05D);
/* 273 */           var21.func_82338_g(0.5F);
/* 274 */           var21.func_70538_b(0.0F, 0.0F, 0.0F);
/* 275 */           Minecraft.func_71410_x().field_71452_i.func_78873_a(var21);
/*     */         }
/*     */       }
/* 278 */       return true;
/*     */     }
/* 280 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/* 283 */   int count = 0;
/* 284 */   int inc = 40;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 288 */     super.func_145845_h();
/*     */     
/* 290 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/* 292 */       int tickrate = this.instability / 50;
/* 293 */       if ((tickrate == 0) || (this.count % (tickrate * tickrate) == 0)) { eject();
/*     */       }
/* 295 */       checkInstability();
/*     */       
/* 297 */       if (this.count++ % this.inc == 0) {
/* 298 */         if (!isLinkValidSimple()) {
/* 299 */           if (this.inc < 600) this.inc += 20;
/* 300 */           restoreLink();
/*     */         } else {
/* 302 */           this.inc = 40;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkInstability() {
/* 309 */     if ((this.instability > 0) && (this.count % 20 == 0)) {
/* 310 */       this.instability -= 1;
/* 311 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/* 313 */     if (this.instability > 0) {
/* 314 */       int amt = VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.ORDER, Math.min(this.instability, 1));
/* 315 */       if (amt > 0) {
/* 316 */         World targetWorld = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
/* 317 */         addInstability(targetWorld, -amt);
/*     */       }
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
/*     */ 
/* 331 */   private ArrayList<ItemStack> outputStacks = new ArrayList();
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 336 */     super.func_145839_a(nbtCompound);
/* 337 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
/* 338 */     this.outputStacks = new ArrayList();
/*     */     
/* 340 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 342 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 343 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/* 345 */       this.outputStacks.add(ItemStack.func_77949_a(nbttagcompound1));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 355 */     super.func_145841_b(nbtCompound);
/* 356 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 358 */     for (int i = 0; i < this.outputStacks.size(); i++)
/*     */     {
/* 360 */       if ((this.outputStacks.get(i) != null) && (((ItemStack)this.outputStacks.get(i)).field_77994_a > 0)) {
/* 361 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 362 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 363 */         ((ItemStack)this.outputStacks.get(i)).func_77955_b(nbttagcompound1);
/* 364 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/* 368 */     nbtCompound.func_74782_a("Items", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/* 374 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/* 380 */     return null;
/*     */   }
/*     */   
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/* 385 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/* 391 */     return null;
/*     */   }
/*     */   
/*     */   public void addStack(ItemStack stack) {
/* 395 */     this.outputStacks.add(stack);
/* 396 */     func_70296_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 402 */     World world = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
/* 403 */     TileEntity target = world.func_147438_o(this.linkX, this.linkY, this.linkZ);
/* 404 */     if ((target != null) && ((target instanceof TileMirror))) {
/* 405 */       ((TileMirror)target).addStack(par2ItemStack.func_77946_l());
/* 406 */       addInstability(null, par2ItemStack.field_77994_a);
/* 407 */       this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMirror, 1, 0);
/*     */     } else {
/* 409 */       spawnItem(par2ItemStack.func_77946_l());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_145825_b()
/*     */   {
/* 416 */     return "container.mirror";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 422 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 428 */     return 64;
/*     */   }
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer var1)
/*     */   {
/* 433 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70295_k_() {}
/*     */   
/*     */ 
/*     */   public void func_70305_f() {}
/*     */   
/*     */   public boolean func_94041_b(int var1, ItemStack var2)
/*     */   {
/* 444 */     World world = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
/* 445 */     TileEntity target = world.func_147438_o(this.linkX, this.linkY, this.linkZ);
/* 446 */     if ((target != null) && ((target instanceof TileMirror))) {
/* 447 */       return true;
/*     */     }
/* 449 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */