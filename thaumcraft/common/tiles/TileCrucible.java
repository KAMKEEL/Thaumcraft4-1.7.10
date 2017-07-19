/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import net.minecraftforge.fluids.FluidTankInfo;
/*     */ import net.minecraftforge.fluids.IFluidHandler;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.container.InventoryFake;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ 
/*     */ public class TileCrucible extends TileThaumcraft implements IFluidHandler, IWandable, IAspectContainer
/*     */ {
/*     */   public short heat;
/*  39 */   public AspectList aspects = new AspectList();
/*  40 */   public final int maxTags = 100;
/*  41 */   int bellows = -1;
/*  42 */   private int delay = 0;
/*     */   
/*     */ 
/*  45 */   public FluidTank tank = new FluidTank(FluidRegistry.WATER, 0, 1000);
/*     */   
/*     */ 
/*     */   public TileCrucible()
/*     */   {
/*  50 */     this.heat = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  56 */     this.heat = nbttagcompound.func_74765_d("Heat");
/*     */     
/*  58 */     this.tank.readFromNBT(nbttagcompound);
/*  59 */     if (nbttagcompound.func_74764_b("Empty")) { this.tank.setFluid(null);
/*     */     }
/*  61 */     this.aspects.readFromNBT(nbttagcompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  68 */     nbttagcompound.func_74777_a("Heat", this.heat);
/*     */     
/*  70 */     this.tank.writeToNBT(nbttagcompound);
/*     */     
/*  72 */     this.aspects.writeToNBT(nbttagcompound);
/*     */   }
/*     */   
/*     */ 
/*  76 */   private long counter = -100L;
/*     */   
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/*  81 */     this.counter += 1L;
/*  82 */     int prevheat = this.heat;
/*  83 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/*  85 */       if (this.bellows < 0) { getBellows();
/*     */       }
/*     */       
/*  88 */       if (this.tank.getFluidAmount() > 0) {
/*  89 */         Material mat = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e).func_149688_o();
/*  90 */         Block bi = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*  91 */         int md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*  92 */         if ((mat == Material.field_151587_i) || (mat == Material.field_151581_o) || ((bi == ConfigBlocks.blockAiry) && (md == 1))) {
/*  93 */           if (this.heat < 200) {
/*  94 */             this.heat = ((short)(this.heat + (1 + this.bellows * 2)));
/*  95 */             if ((prevheat < 151) && (this.heat >= 151)) {
/*  96 */               func_70296_d();
/*  97 */               this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */             }
/*     */           }
/* 100 */         } else if (this.heat > 0) {
/* 101 */           this.heat = ((short)(this.heat - 1));
/* 102 */           if (this.heat == 149) {
/* 103 */             func_70296_d();
/* 104 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */           }
/*     */         }
/* 107 */       } else if (this.heat > 0) {
/* 108 */         this.heat = ((short)(this.heat - 1));
/*     */       }
/*     */       
/*     */ 
/* 112 */       if ((tagAmount() > 100) && (this.counter % 5L == 0L)) {
/* 113 */         AspectList tt = takeRandomFromSource();
/* 114 */         spill();
/*     */       }
/*     */       
/*     */ 
/* 118 */       if ((this.counter > 100L) && (this.heat > 150)) {
/* 119 */         this.counter = 0L;
/* 120 */         if (tagAmount() > 0) {
/* 121 */           int s = this.aspects.getAspects().length;
/* 122 */           Aspect a = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(s)];
/* 123 */           if (a.isPrimal()) {
/* 124 */             a = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(s)];
/*     */           }
/* 126 */           this.tank.drain(2, true);
/*     */           
/* 128 */           this.aspects.remove(a, 1);
/*     */           
/*     */ 
/* 131 */           if (!a.isPrimal()) {
/* 132 */             if (this.field_145850_b.field_73012_v.nextBoolean()) {
/* 133 */               this.aspects.add(a.getComponents()[0], 1);
/*     */             } else {
/* 135 */               this.aspects.add(a.getComponents()[1], 1);
/*     */             }
/*     */           } else {
/* 138 */             spill();
/*     */           }
/*     */         }
/* 141 */         func_70296_d();
/* 142 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 148 */     else if (this.tank.getFluidAmount() > 0) {
/* 149 */       drawEffects();
/*     */     }
/*     */     
/*     */ 
/* 153 */     if ((this.field_145850_b.field_72995_K) && (prevheat < 151) && (this.heat >= 151)) {
/* 154 */       this.heat = ((short)(this.heat + 1));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 159 */   int prevcolor = 0;
/* 160 */   int prevx = 0;
/* 161 */   int prevy = 0;
/*     */   
/*     */   private void drawEffects()
/*     */   {
/* 165 */     if (this.heat > 150) {
/* 166 */       Thaumcraft.proxy.crucibleFroth(this.field_145850_b, this.field_145851_c + 0.2F + this.field_145850_b.field_73012_v.nextFloat() * 0.6F, this.field_145848_d + getFluidHeight(), this.field_145849_e + 0.2F + this.field_145850_b.field_73012_v.nextFloat() * 0.6F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 171 */       if (tagAmount() > 100) {
/* 172 */         for (int a = 0; a < 2; a++) {
/* 173 */           Thaumcraft.proxy.crucibleFrothDown(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat());
/* 174 */           Thaumcraft.proxy.crucibleFrothDown(this.field_145850_b, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat());
/* 175 */           Thaumcraft.proxy.crucibleFrothDown(this.field_145850_b, this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e);
/* 176 */           Thaumcraft.proxy.crucibleFrothDown(this.field_145850_b, this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 182 */     if ((this.field_145850_b.field_73012_v.nextInt(6) == 0) && (this.aspects.size() > 0))
/*     */     {
/* 184 */       int color = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.size())].getColor() + -16777216;
/* 185 */       int x = 5 + this.field_145850_b.field_73012_v.nextInt(22);
/* 186 */       int y = 5 + this.field_145850_b.field_73012_v.nextInt(22);
/* 187 */       this.delay = this.field_145850_b.field_73012_v.nextInt(10);
/* 188 */       this.prevcolor = color;
/* 189 */       this.prevx = x;
/* 190 */       this.prevy = y;
/*     */       
/* 192 */       Color c = new Color(color);
/* 193 */       float r = c.getRed() / 255.0F;
/* 194 */       float g = c.getGreen() / 255.0F;
/* 195 */       float b = c.getBlue() / 255.0F;
/*     */       
/* 197 */       Thaumcraft.proxy.crucibleBubble(this.field_145850_b, this.field_145851_c + x / 32.0F + 0.015625F, this.field_145848_d + 0.05F + getFluidHeight(), this.field_145849_e + y / 32.0F + 0.015625F, r, g, b);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void spill()
/*     */   {
/* 208 */     if (this.field_145850_b.field_73012_v.nextInt(4) == 0) {
/* 209 */       if (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
/* 210 */         if (this.field_145850_b.field_73012_v.nextBoolean()) {
/* 211 */           this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGas, 0, 3);
/*     */         } else {
/* 213 */           this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGoo, 0, 3);
/*     */         }
/*     */       } else {
/* 216 */         Block bi = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 217 */         int md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 218 */         if ((bi == ConfigBlocks.blockFluxGoo) && (md < 7)) {
/* 219 */           this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGoo, md + 1, 3);
/*     */         }
/* 221 */         else if ((bi == ConfigBlocks.blockFluxGas) && (md < 7)) {
/* 222 */           this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGas, md + 1, 3);
/*     */         } else {
/* 224 */           int x = -1 + this.field_145850_b.field_73012_v.nextInt(3);
/* 225 */           int y = -1 + this.field_145850_b.field_73012_v.nextInt(3);
/* 226 */           int z = -1 + this.field_145850_b.field_73012_v.nextInt(3);
/* 227 */           if (this.field_145850_b.func_147437_c(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z)) {
/* 228 */             if (this.field_145850_b.field_73012_v.nextBoolean()) {
/* 229 */               this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z, ConfigBlocks.blockFluxGas, 0, 3);
/*     */             } else {
/* 231 */               this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z, ConfigBlocks.blockFluxGoo, 0, 3);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void spillRemnants()
/*     */   {
/* 241 */     if ((this.tank.getFluidAmount() > 0) || (this.aspects.visSize() > 0)) {
/* 242 */       this.tank.setFluid(null);
/* 243 */       for (int a = 0; a < this.aspects.visSize() / 2; a++) {
/* 244 */         spill();
/*     */       }
/* 246 */       this.aspects = new AspectList();
/* 247 */       func_70296_d();
/* 248 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 249 */       this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMetalDevice, 2, 5);
/*     */     }
/*     */   }
/*     */   
/*     */   public void ejectItem(ItemStack items)
/*     */   {
/* 255 */     int stacks = 1;
/* 256 */     boolean first = true;
/*     */     do {
/* 258 */       ItemStack spitout = items.func_77946_l();
/* 259 */       if (spitout.field_77994_a > spitout.func_77976_d()) {
/* 260 */         spitout.field_77994_a = spitout.func_77976_d();
/*     */       }
/* 262 */       items.field_77994_a -= spitout.field_77994_a;
/*     */       
/* 264 */       EntitySpecialItem entityitem = new EntitySpecialItem(this.field_145850_b, this.field_145851_c + 0.5F, this.field_145848_d + 0.71F, this.field_145849_e + 0.5F, spitout);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 269 */       entityitem.field_70181_x = 0.10000000149011612D;
/* 270 */       entityitem.field_70159_w = (first ? 0.0D : (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01F);
/* 271 */       entityitem.field_70179_y = (first ? 0.0D : (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01F);
/*     */       
/* 273 */       this.field_145850_b.func_72838_d(entityitem);
/* 274 */       first = false;
/* 275 */     } while (items.field_77994_a > 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void attemptSmelt(EntityItem entity)
/*     */   {
/* 281 */     boolean bubble = false;
/* 282 */     boolean event = false;
/* 283 */     ItemStack item = entity.func_92059_d();
/* 284 */     NBTTagCompound itemData = entity.getEntityData();
/* 285 */     String username = itemData.func_74779_i("thrower");
/* 286 */     int stacksize = item.field_77994_a;
/* 287 */     for (int a = 0; a < stacksize; a++)
/*     */     {
/*     */ 
/*     */ 
/* 291 */       CrucibleRecipe rc = ThaumcraftCraftingManager.findMatchingCrucibleRecipe(username, this.aspects, item);
/*     */       
/* 293 */       if ((rc != null) && (this.tank.getFluidAmount() > 0)) {
/* 294 */         ItemStack out = rc.getRecipeOutput().func_77946_l();
/*     */         
/* 296 */         EntityPlayer p = this.field_145850_b.func_72924_a(username);
/* 297 */         if (p != null) {
/* 298 */           FMLCommonHandler.instance().firePlayerCraftingEvent(p, out, new InventoryFake(new ItemStack[] { item }));
/*     */         }
/*     */         
/* 301 */         this.aspects = rc.removeMatching(this.aspects);
/* 302 */         this.tank.drain(50, true);
/* 303 */         ejectItem(out);
/* 304 */         event = true;
/* 305 */         stacksize--;
/* 306 */         this.counter = -250L;
/*     */       }
/*     */       else {
/* 309 */         AspectList ot = ThaumcraftCraftingManager.getObjectTags(item);
/* 310 */         ot = ThaumcraftCraftingManager.getBonusTags(item, ot);
/* 311 */         if ((ot == null) || (ot.size() == 0)) {
/* 312 */           entity.field_70181_x = 0.3499999940395355D;
/* 313 */           entity.field_70159_w = ((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F);
/* 314 */           entity.field_70179_y = ((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F);
/* 315 */           this.field_145850_b.func_72956_a(entity, "random.pop", 0.2F, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.7F + 1.0F);
/*     */           
/*     */ 
/* 318 */           return;
/*     */         }
/* 320 */         for (Aspect tag : ot.getAspects()) {
/* 321 */           this.aspects.add(tag, ot.getAmount(tag));
/*     */         }
/*     */         
/* 324 */         bubble = true;
/* 325 */         stacksize--;
/* 326 */         this.counter = -150L;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 332 */     if (bubble) {
/* 333 */       this.field_145850_b.func_72956_a(entity, "thaumcraft:bubble", 0.2F, 1.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F);
/* 334 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 335 */       this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMetalDevice, 2, 1);
/*     */     }
/* 337 */     if (event) {
/* 338 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 339 */       this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMetalDevice, 2, 5);
/*     */     }
/*     */     
/* 342 */     if (stacksize <= 0) {
/* 343 */       entity.func_70106_y();
/*     */     } else {
/* 345 */       item.field_77994_a = stacksize;
/* 346 */       entity.func_92058_a(item);
/*     */     }
/* 348 */     func_70296_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public int tagAmount()
/*     */   {
/* 354 */     int tt = 0;
/* 355 */     if (this.aspects.size() > 0) {
/* 356 */       for (Aspect tag : this.aspects.getAspects()) {
/* 357 */         tt += this.aspects.getAmount(tag);
/*     */       }
/* 359 */       return tt;
/*     */     }
/* 361 */     return 0;
/*     */   }
/*     */   
/*     */   public float getFluidHeight()
/*     */   {
/* 366 */     float base = 0.3F + 0.5F * (this.tank.getFluidAmount() / this.tank.getCapacity());
/* 367 */     float out = base + tagAmount() / 100.0F * (1.0F - base);
/* 368 */     if (out > 1.0F) out = 1.001F;
/* 369 */     if (out == 1.0F) out = 0.9999F;
/* 370 */     return out;
/*     */   }
/*     */   
/*     */   public AspectList takeRandomFromSource()
/*     */   {
/* 375 */     AspectList output = new AspectList();
/* 376 */     if (this.aspects.size() > 0) {
/* 377 */       Aspect tag = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.getAspects().length)];
/* 378 */       output.add(tag, 1);
/* 379 */       this.aspects.remove(tag, 1);
/*     */     }
/* 381 */     func_70296_d();
/* 382 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 383 */     return output;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 391 */     if (i == 1)
/*     */     {
/* 393 */       if (this.field_145850_b.field_72995_K) {
/* 394 */         Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, 55537, 5);
/*     */       }
/* 396 */       return true;
/*     */     }
/*     */     
/* 399 */     if (i == 2)
/*     */     {
/* 401 */       Thaumcraft.proxy.crucibleBoilSound(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 402 */       if (this.field_145850_b.field_72995_K) {
/* 403 */         for (int q = 0; q < 10; q++) {
/* 404 */           int x = 5 + this.field_145850_b.field_73012_v.nextInt(22);
/* 405 */           int y = 5 + this.field_145850_b.field_73012_v.nextInt(22);
/* 406 */           Thaumcraft.proxy.crucibleBoil(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this, j);
/*     */         }
/*     */       }
/*     */       
/* 410 */       return true;
/*     */     }
/*     */     
/* 413 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */   public void getBellows() {
/* 417 */     this.bellows = 0;
/* 418 */     for (int a = 2; a < 6; a++) {
/* 419 */       ForgeDirection dir = ForgeDirection.getOrientation(a);
/* 420 */       int xx = this.field_145851_c + dir.offsetX;
/* 421 */       int zz = this.field_145849_e + dir.offsetZ;
/* 422 */       Block bi = this.field_145850_b.func_147439_a(xx, this.field_145848_d, zz);
/* 423 */       int md = this.field_145850_b.func_72805_g(xx, this.field_145848_d, zz);
/* 424 */       if ((bi == ConfigBlocks.blockWoodenDevice) && (md == 0)) {
/* 425 */         this.bellows += 1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
/*     */   {
/* 436 */     if ((resource != null) && (resource.fluidID != FluidRegistry.WATER.getID())) { return 0;
/*     */     }
/* 438 */     if (doFill) {
/* 439 */       func_70296_d();
/* 440 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/* 443 */     return this.tank.fill(resource, doFill);
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
/*     */   {
/* 449 */     if ((resource == null) || (!resource.isFluidEqual(this.tank.getFluid())))
/*     */     {
/* 451 */       return null;
/*     */     }
/* 453 */     if (doDrain) {
/* 454 */       func_70296_d();
/* 455 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/* 457 */     return this.tank.drain(resource.amount, doDrain);
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
/*     */   {
/* 463 */     return this.tank.drain(maxDrain, doDrain);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canFill(ForgeDirection from, Fluid fluid)
/*     */   {
/* 469 */     return (fluid != null) && (fluid.getID() == FluidRegistry.WATER.getID());
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canDrain(ForgeDirection from, Fluid fluid)
/*     */   {
/* 475 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public FluidTankInfo[] getTankInfo(ForgeDirection from)
/*     */   {
/* 481 */     return new FluidTankInfo[] { this.tank.getInfo() };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/* 488 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*     */   {
/* 494 */     if ((!world.field_72995_K) && (player.func_70093_af())) {
/* 495 */       spillRemnants();
/*     */     }
/* 497 */     return wandstack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/* 517 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1);
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 522 */     return this.aspects;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */   public int addToContainer(Aspect tag, int amount)
/*     */   {
/* 531 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(Aspect tag, int amount)
/*     */   {
/* 537 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 543 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amount)
/*     */   {
/* 549 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 555 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 561 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 566 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileCrucible.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */