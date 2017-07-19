/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.crafting.IInfusionStabiliser;
/*     */ import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.container.InventoryFake;
/*     */ import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.lib.events.EssentiaHandler;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXInfusionSource;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class TileInfusionMatrix extends TileThaumcraft implements IWandable, IAspectContainer
/*     */ {
/*  58 */   private ArrayList<ChunkCoordinates> pedestals = new ArrayList();
/*  59 */   private int dangerCount = 0;
/*  60 */   public boolean active = false;
/*  61 */   public boolean crafting = false;
/*  62 */   public boolean checkSurroundings = true;
/*  63 */   public int symmetry = 0;
/*  64 */   public int instability = 0;
/*     */   
/*     */ 
/*  67 */   private AspectList recipeEssentia = new AspectList();
/*  68 */   private ArrayList<ItemStack> recipeIngredients = null;
/*  69 */   private Object recipeOutput = null;
/*  70 */   private String recipePlayer = null;
/*  71 */   private String recipeOutputLabel = null;
/*  72 */   private ItemStack recipeInput = null;
/*  73 */   private int recipeInstability = 0;
/*  74 */   private int recipeXP = 0;
/*  75 */   private int recipeType = 0;
/*     */   
/*     */   public class SourceFX { public ChunkCoordinates loc;
/*     */     public int ticks;
/*     */     
/*  80 */     public SourceFX(ChunkCoordinates loc, int ticks, int color) { this.loc = loc;
/*  81 */       this.ticks = ticks;
/*  82 */       this.color = color;
/*     */     }
/*     */     
/*     */ 
/*     */     public int color;
/*     */     
/*     */     public int entity;
/*     */   }
/*     */   
/*  91 */   public HashMap<String, SourceFX> sourceFX = new HashMap();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  96 */     return AxisAlignedBB.func_72330_a(this.field_145851_c - 1, this.field_145848_d - 1, this.field_145849_e - 1, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbtCompound)
/*     */   {
/* 104 */     this.active = nbtCompound.func_74767_n("active");
/* 105 */     this.crafting = nbtCompound.func_74767_n("crafting");
/* 106 */     this.instability = nbtCompound.func_74765_d("instability");
/* 107 */     this.recipeEssentia.readFromNBT(nbtCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbtCompound)
/*     */   {
/* 113 */     nbtCompound.func_74757_a("active", this.active);
/* 114 */     nbtCompound.func_74757_a("crafting", this.crafting);
/* 115 */     nbtCompound.func_74777_a("instability", (short)this.instability);
/* 116 */     this.recipeEssentia.writeToNBT(nbtCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 122 */     super.func_145839_a(nbtCompound);
/*     */     
/* 124 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("recipein", 10);
/* 125 */     this.recipeIngredients = new ArrayList();
/* 126 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 128 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 129 */       byte b0 = nbttagcompound1.func_74771_c("item");
/* 130 */       this.recipeIngredients.add(ItemStack.func_77949_a(nbttagcompound1));
/*     */     }
/*     */     
/* 133 */     String rot = nbtCompound.func_74779_i("rotype");
/* 134 */     if ((rot != null) && (rot.equals("@"))) {
/* 135 */       this.recipeOutput = ItemStack.func_77949_a(nbtCompound.func_74775_l("recipeout"));
/*     */     }
/* 137 */     else if (rot != null) {
/* 138 */       this.recipeOutputLabel = rot;
/* 139 */       this.recipeOutput = nbtCompound.func_74781_a("recipeout");
/*     */     }
/*     */     
/* 142 */     this.recipeInput = ItemStack.func_77949_a(nbtCompound.func_74775_l("recipeinput"));
/* 143 */     this.recipeInstability = nbtCompound.func_74762_e("recipeinst");
/* 144 */     this.recipeType = nbtCompound.func_74762_e("recipetype");
/* 145 */     this.recipeXP = nbtCompound.func_74762_e("recipexp");
/* 146 */     this.recipePlayer = nbtCompound.func_74779_i("recipeplayer");
/* 147 */     if (this.recipePlayer.isEmpty()) { this.recipePlayer = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 153 */     super.func_145841_b(nbtCompound);
/*     */     
/* 155 */     if ((this.recipeIngredients != null) && (this.recipeIngredients.size() > 0)) {
/* 156 */       NBTTagList nbttaglist = new NBTTagList();
/* 157 */       int count = 0;
/* 158 */       for (ItemStack stack : this.recipeIngredients)
/*     */       {
/* 160 */         if (stack != null)
/*     */         {
/* 162 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 163 */           nbttagcompound1.func_74774_a("item", (byte)count);
/* 164 */           stack.func_77955_b(nbttagcompound1);
/* 165 */           nbttaglist.func_74742_a(nbttagcompound1);
/* 166 */           count++;
/*     */         }
/*     */       }
/* 169 */       nbtCompound.func_74782_a("recipein", nbttaglist);
/*     */     }
/* 171 */     if ((this.recipeOutput != null) && ((this.recipeOutput instanceof ItemStack))) nbtCompound.func_74778_a("rotype", "@");
/* 172 */     if ((this.recipeOutput != null) && ((this.recipeOutput instanceof NBTBase))) {
/* 173 */       nbtCompound.func_74778_a("rotype", this.recipeOutputLabel);
/*     */     }
/*     */     
/* 176 */     if ((this.recipeOutput != null) && ((this.recipeOutput instanceof ItemStack))) {
/* 177 */       nbtCompound.func_74782_a("recipeout", ((ItemStack)this.recipeOutput).func_77955_b(new NBTTagCompound()));
/*     */     }
/* 179 */     if ((this.recipeOutput != null) && ((this.recipeOutput instanceof NBTBase))) {
/* 180 */       nbtCompound.func_74782_a("recipeout", (NBTBase)this.recipeOutput);
/*     */     }
/*     */     
/* 183 */     if (this.recipeInput != null) nbtCompound.func_74782_a("recipeinput", this.recipeInput.func_77955_b(new NBTTagCompound()));
/* 184 */     nbtCompound.func_74768_a("recipeinst", this.recipeInstability);
/* 185 */     nbtCompound.func_74768_a("recipetype", this.recipeType);
/* 186 */     nbtCompound.func_74768_a("recipexp", this.recipeXP);
/*     */     
/* 188 */     if (this.recipePlayer == null) {
/* 189 */       nbtCompound.func_74778_a("recipeplayer", "");
/*     */     } else {
/* 191 */       nbtCompound.func_74778_a("recipeplayer", this.recipePlayer);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canUpdate() {
/* 196 */     return true;
/*     */   }
/*     */   
/* 199 */   public int count = 0;
/* 200 */   public int craftCount = 0;
/*     */   public float startUp;
/* 202 */   private int countDelay = 10;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 206 */     super.func_145845_h();
/* 207 */     this.count += 1;
/* 208 */     if (this.checkSurroundings) {
/* 209 */       this.checkSurroundings = false;
/* 210 */       getSurroundings();
/*     */     }
/* 212 */     if (this.field_145850_b.field_72995_K) {
/* 213 */       doEffects();
/*     */     } else {
/* 215 */       if ((this.count % (this.crafting ? 20 : 100) == 0) && 
/* 216 */         (!validLocation())) {
/* 217 */         this.active = false;
/* 218 */         func_70296_d();
/* 219 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 220 */         return;
/*     */       }
/*     */       
/* 223 */       if ((this.active) && (this.crafting) && (this.count % this.countDelay == 0)) {
/* 224 */         craftCycle();
/* 225 */         func_70296_d();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 232 */   ArrayList<ItemStack> ingredients = new ArrayList();
/*     */   
/*     */   public boolean validLocation() {
/* 235 */     TileEntity te = null;
/* 236 */     te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
/* 237 */     if ((te == null) || (!(te instanceof TilePedestal))) return false;
/* 238 */     te = this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d - 2, this.field_145849_e + 1);
/* 239 */     if ((te == null) || (!(te instanceof TileInfusionPillar))) return false;
/* 240 */     te = this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d - 2, this.field_145849_e - 1);
/* 241 */     if ((te == null) || (!(te instanceof TileInfusionPillar))) return false;
/* 242 */     te = this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d - 2, this.field_145849_e - 1);
/* 243 */     if ((te == null) || (!(te instanceof TileInfusionPillar))) return false;
/* 244 */     te = this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d - 2, this.field_145849_e + 1);
/* 245 */     if ((te == null) || (!(te instanceof TileInfusionPillar))) return false;
/* 246 */     return true;
/*     */   }
/*     */   
/*     */   public void craftingStart(EntityPlayer player) {
/* 250 */     if (!validLocation()) {
/* 251 */       this.active = false;
/* 252 */       func_70296_d();
/* 253 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 254 */       return;
/*     */     }
/*     */     
/* 257 */     getSurroundings();
/* 258 */     TileEntity te = null;
/* 259 */     this.recipeInput = null;
/* 260 */     te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
/* 261 */     if ((te != null) && ((te instanceof TilePedestal))) {
/* 262 */       TilePedestal ped = (TilePedestal)te;
/* 263 */       if (ped.func_70301_a(0) != null) {
/* 264 */         this.recipeInput = ped.func_70301_a(0).func_77946_l();
/*     */       }
/*     */     }
/*     */     
/* 268 */     if (this.recipeInput == null) { return;
/*     */     }
/* 270 */     ArrayList<ItemStack> components = new ArrayList();
/* 271 */     for (ChunkCoordinates cc : this.pedestals) {
/* 272 */       te = this.field_145850_b.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
/* 273 */       if ((te != null) && ((te instanceof TilePedestal))) {
/* 274 */         TilePedestal ped = (TilePedestal)te;
/* 275 */         if (ped.func_70301_a(0) != null) {
/* 276 */           components.add(ped.func_70301_a(0).func_77946_l());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 281 */     if (components.size() == 0) { return;
/*     */     }
/*     */     
/* 284 */     InfusionRecipe recipe = ThaumcraftCraftingManager.findMatchingInfusionRecipe(components, this.recipeInput, player);
/*     */     
/*     */ 
/*     */ 
/* 288 */     if (recipe != null) {
/* 289 */       this.recipeType = 0;
/* 290 */       this.recipeIngredients = new ArrayList();
/* 291 */       if ((recipe instanceof InfusionRunicAugmentRecipe)) {
/* 292 */         for (ItemStack ing : ((InfusionRunicAugmentRecipe)recipe).getComponents(this.recipeInput)) {
/* 293 */           this.recipeIngredients.add(ing.func_77946_l());
/*     */         }
/*     */       } else {
/* 296 */         for (ItemStack ing : recipe.getComponents()) {
/* 297 */           this.recipeIngredients.add(ing.func_77946_l());
/*     */         }
/*     */       }
/* 300 */       if ((recipe.getRecipeOutput(this.recipeInput) instanceof Object[])) {
/* 301 */         Object[] obj = (Object[])recipe.getRecipeOutput(this.recipeInput);
/* 302 */         this.recipeOutputLabel = ((String)obj[0]);
/* 303 */         this.recipeOutput = ((NBTBase)obj[1]);
/*     */       } else {
/* 305 */         this.recipeOutput = recipe.getRecipeOutput(this.recipeInput);
/*     */       }
/* 307 */       this.recipeInstability = recipe.getInstability(this.recipeInput);
/* 308 */       this.recipeEssentia = recipe.getAspects(this.recipeInput).copy();
/* 309 */       this.recipePlayer = player.func_70005_c_();
/* 310 */       this.instability = (this.symmetry + this.recipeInstability);
/* 311 */       this.crafting = true;
/* 312 */       this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "thaumcraft:craftstart", 0.5F, 1.0F);
/* 313 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 314 */       func_70296_d();
/* 315 */       return;
/*     */     }
/*     */     
/*     */ 
/* 319 */     InfusionEnchantmentRecipe recipe2 = ThaumcraftCraftingManager.findMatchingInfusionEnchantmentRecipe(components, this.recipeInput, player);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 324 */     if (recipe2 != null) {
/* 325 */       this.recipeType = 1;
/* 326 */       this.recipeIngredients = new ArrayList();
/* 327 */       for (ItemStack ing : recipe2.components) {
/* 328 */         this.recipeIngredients.add(ing.func_77946_l());
/*     */       }
/* 330 */       this.recipeOutput = recipe2.getEnchantment();
/* 331 */       this.recipeInstability = recipe2.calcInstability(this.recipeInput);
/* 332 */       AspectList esscost = recipe2.aspects.copy();
/* 333 */       float essmod = recipe2.getEssentiaMod(this.recipeInput);
/* 334 */       for (Aspect as : esscost.getAspects()) {
/* 335 */         esscost.add(as, (int)(esscost.getAmount(as) * essmod));
/*     */       }
/* 337 */       this.recipeEssentia = esscost;
/* 338 */       this.recipeXP = recipe2.calcXP(this.recipeInput);
/*     */       
/* 340 */       this.instability = (this.symmetry + this.recipeInstability);
/* 341 */       this.crafting = true;
/* 342 */       this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "thaumcraft:craftstart", 0.5F, 1.0F);
/* 343 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 344 */       func_70296_d();
/* 345 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void craftCycle()
/*     */   {
/* 352 */     boolean valid = false;
/*     */     
/* 354 */     TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
/* 355 */     if ((te != null) && ((te instanceof TilePedestal))) {
/* 356 */       TilePedestal ped = (TilePedestal)te;
/* 357 */       if (ped.func_70301_a(0) != null) {
/* 358 */         ItemStack i2 = ped.func_70301_a(0).func_77946_l();
/* 359 */         if (this.recipeInput.func_77960_j() == 32767) {
/* 360 */           i2.func_77964_b(32767);
/*     */         }
/* 362 */         if (InventoryUtils.areItemStacksEqualForCrafting(i2, this.recipeInput, true, true, false)) { valid = true;
/*     */         }
/*     */       }
/*     */     }
/* 366 */     if ((!valid) || ((this.instability > 0) && (this.field_145850_b.field_73012_v.nextInt(500) <= this.instability)))
/*     */     {
/* 368 */       switch (this.field_145850_b.field_73012_v.nextInt(21)) {
/* 369 */       case 0: case 2: case 10: case 13:  inEvEjectItem(0); break;
/* 370 */       case 6: case 17:  inEvEjectItem(1); break;
/* 371 */       case 1: case 11:  inEvEjectItem(2); break;
/* 372 */       case 3: case 8: case 14:  inEvZap(false); break;
/* 373 */       case 5: case 16:  inEvHarm(false); break;
/* 374 */       case 12:  inEvZap(true); break;
/* 375 */       case 19:  inEvEjectItem(3); break;
/* 376 */       case 7:  inEvEjectItem(4); break;
/* 377 */       case 4: case 15:  inEvEjectItem(5); break;
/* 378 */       case 18:  inEvHarm(true); break;
/* 379 */       case 9:  this.field_145850_b.func_72876_a(null, this.field_145851_c + 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.5F, 1.5F + this.field_145850_b.field_73012_v.nextFloat(), false); break;
/* 380 */       case 20:  inEvWarp();
/*     */       }
/*     */       
/* 383 */       if (valid) { return;
/*     */       }
/*     */     }
/* 386 */     if (!valid) {
/* 387 */       this.instability = 0;
/* 388 */       this.crafting = false;
/* 389 */       this.recipeEssentia = new AspectList();
/* 390 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 391 */       this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "thaumcraft:craftfail", 1.0F, 0.6F);
/* 392 */       func_70296_d();
/* 393 */       return;
/*     */     }
/*     */     
/* 396 */     if ((this.recipeType == 1) && (this.recipeXP > 0)) {
/* 397 */       List<EntityPlayer> targets = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(10.0D, 10.0D, 10.0D));
/*     */       
/* 399 */       if ((targets != null) && (targets.size() > 0)) {
/* 400 */         for (EntityPlayer target : targets) {
/* 401 */           if (target.field_71068_ca > 0) {
/* 402 */             target.func_82242_a(-1);
/* 403 */             this.recipeXP -= 1;
/* 404 */             target.func_70097_a(DamageSource.field_76376_m, this.field_145850_b.field_73012_v.nextInt(2));
/* 405 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXInfusionSource(this.field_145851_c, this.field_145848_d, this.field_145849_e, (byte)0, (byte)0, (byte)0, target.func_145782_y()), new NetworkRegistry.TargetPoint(func_145831_w().field_73011_w.field_76574_g, this.field_145851_c, this.field_145848_d, this.field_145849_e, 32.0D));
/*     */             
/*     */ 
/*     */ 
/*     */ 
/* 410 */             this.field_145850_b.func_72956_a(target, "random.fizz", 1.0F, 2.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F);
/* 411 */             this.countDelay = 20;
/* 412 */             return;
/*     */           }
/*     */         }
/* 415 */         Aspect[] ingEss = this.recipeEssentia.getAspects();
/* 416 */         if ((ingEss != null) && (ingEss.length > 0) && (this.field_145850_b.field_73012_v.nextInt(3) == 0)) {
/* 417 */           Aspect as = ingEss[this.field_145850_b.field_73012_v.nextInt(ingEss.length)];
/* 418 */           this.recipeEssentia.add(as, 1);
/* 419 */           if (this.field_145850_b.field_73012_v.nextInt(50 - this.recipeInstability * 2) == 0) this.instability += 1;
/* 420 */           if (this.instability > 25) this.instability = 25;
/* 421 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 422 */           func_70296_d();
/*     */         }
/*     */       }
/* 425 */       return;
/*     */     }
/*     */     
/* 428 */     if ((this.recipeType == 1) && (this.recipeXP == 0)) { this.countDelay = 10;
/*     */     }
/* 430 */     if (this.recipeEssentia.visSize() > 0) {
/* 431 */       for (Aspect aspect : this.recipeEssentia.getAspects()) {
/* 432 */         if (this.recipeEssentia.getAmount(aspect) > 0) {
/* 433 */           if (EssentiaHandler.drainEssentia(this, aspect, ForgeDirection.UNKNOWN, 12)) {
/* 434 */             this.recipeEssentia.reduce(aspect, 1);
/* 435 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 436 */             func_70296_d();
/* 437 */             return;
/*     */           }
/*     */           
/*     */ 
/* 441 */           if (this.field_145850_b.field_73012_v.nextInt(100 - this.recipeInstability * 3) == 0) this.instability += 1;
/* 442 */           if (this.instability > 25) this.instability = 25;
/* 443 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 444 */           func_70296_d();
/*     */         }
/*     */       }
/* 447 */       this.checkSurroundings = true;
/* 448 */       return;
/*     */     }
/*     */     
/* 451 */     if (this.recipeIngredients.size() > 0) {
/* 452 */       for (int a = 0; a < this.recipeIngredients.size(); a++) {
/* 453 */         for (ChunkCoordinates cc : this.pedestals) {
/* 454 */           te = this.field_145850_b.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
/* 455 */           if ((te != null) && ((te instanceof TilePedestal)) && (((TilePedestal)te).func_70301_a(0) != null) && 
/* 456 */             (InfusionRecipe.areItemStacksEqual(((TilePedestal)te).func_70301_a(0), (ItemStack)this.recipeIngredients.get(a), true)))
/*     */           {
/*     */ 
/* 459 */             if (this.itemCount == 0) {
/* 460 */               this.itemCount = 5;
/* 461 */               PacketHandler.INSTANCE.sendToAllAround(new PacketFXInfusionSource(this.field_145851_c, this.field_145848_d, this.field_145849_e, (byte)(this.field_145851_c - cc.field_71574_a), (byte)(this.field_145848_d - cc.field_71572_b), (byte)(this.field_145849_e - cc.field_71573_c), 0), new NetworkRegistry.TargetPoint(func_145831_w().field_73011_w.field_76574_g, this.field_145851_c, this.field_145848_d, this.field_145849_e, 32.0D));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             }
/* 467 */             else if (this.itemCount-- <= 1)
/*     */             {
/* 469 */               ItemStack is = ((TilePedestal)te).func_70301_a(0).func_77973_b().getContainerItem(((TilePedestal)te).func_70301_a(0));
/*     */               
/* 471 */               ((TilePedestal)te).func_70299_a(0, is == null ? null : is.func_77946_l());
/* 472 */               this.recipeIngredients.remove(a);
/*     */             }
/* 474 */             return;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 479 */         Aspect[] ingEss = this.recipeEssentia.getAspects();
/* 480 */         if ((ingEss != null) && (ingEss.length > 0) && (this.field_145850_b.field_73012_v.nextInt(1 + a) == 0)) {
/* 481 */           Aspect as = ingEss[this.field_145850_b.field_73012_v.nextInt(ingEss.length)];
/* 482 */           this.recipeEssentia.add(as, 1);
/* 483 */           if (this.field_145850_b.field_73012_v.nextInt(50 - this.recipeInstability * 2) == 0) this.instability += 1;
/* 484 */           if (this.instability > 25) this.instability = 25;
/* 485 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 486 */           func_70296_d();
/*     */         }
/*     */       }
/*     */       
/* 490 */       return;
/*     */     }
/* 492 */     this.instability = 0;
/* 493 */     this.crafting = false;
/* 494 */     craftingFinish(this.recipeOutput, this.recipeOutputLabel);
/* 495 */     this.recipeOutput = null;
/* 496 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 497 */     func_70296_d();
/*     */   }
/*     */   
/*     */   private void inEvZap(boolean all)
/*     */   {
/* 502 */     List<Entity> targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(10.0D, 10.0D, 10.0D));
/*     */     
/* 504 */     if ((targets != null) && (targets.size() > 0))
/* 505 */       for (Entity target : targets) {
/* 506 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockZap(this.field_145851_c + 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.5F, (float)target.field_70165_t, (float)target.field_70163_u + target.field_70131_O / 2.0F, (float)target.field_70161_v), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, this.field_145851_c, this.field_145848_d, this.field_145849_e, 32.0D));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 511 */         target.func_70097_a(DamageSource.field_76376_m, 4 + this.field_145850_b.field_73012_v.nextInt(4));
/* 512 */         if (!all)
/*     */           break;
/*     */       }
/*     */   }
/*     */   
/*     */   private void inEvHarm(boolean all) {
/* 518 */     List<EntityLivingBase> targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(10.0D, 10.0D, 10.0D));
/*     */     
/* 520 */     if ((targets != null) && (targets.size() > 0))
/* 521 */       for (EntityLivingBase target : targets) {
/* 522 */         if (this.field_145850_b.field_73012_v.nextBoolean()) {
/* 523 */           target.func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 120, 0, false));
/*     */         } else {
/* 525 */           PotionEffect pe = new PotionEffect(Config.potionVisExhaustID, 2400, 0, true);
/* 526 */           pe.getCurativeItems().clear();
/* 527 */           target.func_70690_d(pe);
/*     */         }
/* 529 */         if (!all)
/*     */           break;
/*     */       }
/*     */   }
/*     */   
/*     */   private void inEvWarp() {
/* 535 */     List<EntityPlayer> targets = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(10.0D, 10.0D, 10.0D));
/*     */     
/* 537 */     if ((targets != null) && (targets.size() > 0)) {
/* 538 */       EntityPlayer target = (EntityPlayer)targets.get(this.field_145850_b.field_73012_v.nextInt(targets.size()));
/* 539 */       if (this.field_145850_b.field_73012_v.nextFloat() < 0.25F) {
/* 540 */         Thaumcraft.addStickyWarpToPlayer(target, 1);
/*     */       } else {
/* 542 */         Thaumcraft.addWarpToPlayer(target, 1 + this.field_145850_b.field_73012_v.nextInt(5), true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void inEvEjectItem(int type)
/*     */   {
/* 549 */     int q = 0;
/* 550 */     while ((q < 50) && (this.pedestals.size() > 0)) {
/* 551 */       ChunkCoordinates cc = (ChunkCoordinates)this.pedestals.get(this.field_145850_b.field_73012_v.nextInt(this.pedestals.size()));
/* 552 */       TileEntity te = this.field_145850_b.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
/* 553 */       if ((te != null) && ((te instanceof TilePedestal)) && (((TilePedestal)te).func_70301_a(0) != null))
/*     */       {
/* 555 */         if ((type < 3) || (type == 5)) {
/* 556 */           InventoryUtils.dropItems(this.field_145850_b, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
/*     */         } else {
/* 558 */           ((TilePedestal)te).func_70299_a(0, null);
/*     */         }
/* 560 */         if ((type == 1) || (type == 3)) {
/* 561 */           this.field_145850_b.func_147465_d(cc.field_71574_a, cc.field_71572_b + 1, cc.field_71573_c, ConfigBlocks.blockFluxGoo, 7, 3);
/* 562 */           this.field_145850_b.func_72908_a(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, "game.neutral.swim", 0.3F, 1.0F);
/*     */         }
/* 564 */         else if ((type == 2) || (type == 4)) {
/* 565 */           this.field_145850_b.func_147465_d(cc.field_71574_a, cc.field_71572_b + 1, cc.field_71573_c, ConfigBlocks.blockFluxGas, 7, 3);
/* 566 */           this.field_145850_b.func_72908_a(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, "random.fizz", 0.3F, 1.0F);
/*     */         }
/* 568 */         else if (type == 5) {
/* 569 */           this.field_145850_b.func_72876_a(null, cc.field_71574_a + 0.5F, cc.field_71572_b + 0.5F, cc.field_71573_c + 0.5F, 1.0F, false);
/*     */         }
/* 571 */         this.field_145850_b.func_147452_c(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockStoneDevice, 11, 0);
/* 572 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockZap(this.field_145851_c + 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.5F, cc.field_71574_a + 0.5F, cc.field_71572_b + 1.5F, cc.field_71573_c + 0.5F), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, this.field_145851_c, this.field_145848_d, this.field_145849_e, 32.0D));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 577 */         return;
/*     */       }
/* 579 */       q++;
/*     */     }
/*     */   }
/*     */   
/* 583 */   int itemCount = 0;
/*     */   
/*     */   public void craftingFinish(Object out, String label) {
/* 586 */     TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
/* 587 */     if ((te != null) && ((te instanceof TilePedestal))) {
/* 588 */       if ((out instanceof ItemStack)) {
/* 589 */         ((TilePedestal)te).setInventorySlotContentsFromInfusion(0, ((ItemStack)out).func_77946_l());
/*     */       }
/* 591 */       else if ((out instanceof NBTBase)) {
/* 592 */         ItemStack temp = ((TilePedestal)te).func_70301_a(0);
/* 593 */         NBTBase tag = (NBTBase)out;
/* 594 */         temp.func_77983_a(label, tag);
/* 595 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
/* 596 */         te.func_70296_d();
/*     */       }
/* 598 */       else if ((out instanceof Enchantment)) {
/* 599 */         ItemStack temp = ((TilePedestal)te).func_70301_a(0);
/* 600 */         Map enchantments = EnchantmentHelper.func_82781_a(temp);
/* 601 */         enchantments.put(Integer.valueOf(((Enchantment)out).field_77352_x), Integer.valueOf(EnchantmentHelper.func_77506_a(((Enchantment)out).field_77352_x, temp) + 1));
/*     */         
/*     */ 
/* 604 */         EnchantmentHelper.func_82782_a(enchantments, temp);
/* 605 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
/* 606 */         te.func_70296_d();
/*     */       }
/*     */       
/* 609 */       if (this.recipePlayer != null) {
/* 610 */         EntityPlayer p = this.field_145850_b.func_72924_a(this.recipePlayer);
/* 611 */         if (p != null) {
/* 612 */           FMLCommonHandler.instance().firePlayerCraftingEvent(p, ((TilePedestal)te).func_70301_a(0), new InventoryFake(this.recipeIngredients));
/*     */         }
/*     */       }
/*     */       
/* 616 */       this.recipeEssentia = new AspectList();
/* 617 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 618 */       func_70296_d();
/* 619 */       this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e, ConfigBlocks.blockStoneDevice, 12, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   private void getSurroundings() {
/* 624 */     ArrayList<ChunkCoordinates> stuff = new ArrayList();
/* 625 */     this.pedestals.clear();
/*     */     try
/*     */     {
/* 628 */       for (int xx = -12; xx <= 12; xx++) {
/* 629 */         for (int zz = -12; zz <= 12; zz++) {
/* 630 */           boolean skip = false;
/* 631 */           for (int yy = -5; yy <= 10; yy++) {
/* 632 */             if ((xx != 0) || (zz != 0)) {
/* 633 */               int x = this.field_145851_c + xx;
/* 634 */               int y = this.field_145848_d - yy;
/* 635 */               int z = this.field_145849_e + zz;
/*     */               
/* 637 */               TileEntity te = this.field_145850_b.func_147438_o(x, y, z);
/* 638 */               if ((!skip) && (yy > 0) && (Math.abs(xx) <= 8) && (Math.abs(zz) <= 8) && (te != null) && ((te instanceof TilePedestal)))
/*     */               {
/* 640 */                 this.pedestals.add(new ChunkCoordinates(x, y, z));
/* 641 */                 skip = true;
/*     */               } else {
/* 643 */                 Block bi = this.field_145850_b.func_147439_a(x, y, z);
/* 644 */                 if ((bi == Blocks.field_150465_bP) || (((bi instanceof IInfusionStabiliser)) && (((IInfusionStabiliser)bi).canStabaliseInfusion(func_145831_w(), x, y, z))))
/*     */                 {
/*     */ 
/* 647 */                   stuff.add(new ChunkCoordinates(x, y, z));
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 654 */       this.symmetry = 0;
/*     */       
/*     */ 
/* 657 */       for (ChunkCoordinates cc : this.pedestals) {
/* 658 */         boolean items = false;
/* 659 */         int x = this.field_145851_c - cc.field_71574_a;
/* 660 */         int z = this.field_145849_e - cc.field_71573_c;
/*     */         
/* 662 */         TileEntity te = this.field_145850_b.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
/* 663 */         if ((te != null) && ((te instanceof TilePedestal))) {
/* 664 */           this.symmetry += 2;
/* 665 */           if (((TilePedestal)te).func_70301_a(0) != null) {
/* 666 */             this.symmetry += 1;
/* 667 */             items = true;
/*     */           }
/*     */         }
/*     */         
/* 671 */         int xx = this.field_145851_c + x;
/* 672 */         int zz = this.field_145849_e + z;
/* 673 */         te = this.field_145850_b.func_147438_o(xx, cc.field_71572_b, zz);
/* 674 */         if ((te != null) && ((te instanceof TilePedestal))) {
/* 675 */           this.symmetry -= 2;
/* 676 */           if ((((TilePedestal)te).func_70301_a(0) != null) && (items)) {
/* 677 */             this.symmetry -= 1;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 682 */       float sym = 0.0F;
/* 683 */       for (ChunkCoordinates cc : stuff) {
/* 684 */         boolean items = false;
/* 685 */         int x = this.field_145851_c - cc.field_71574_a;
/* 686 */         int z = this.field_145849_e - cc.field_71573_c;
/*     */         
/* 688 */         Block bi = this.field_145850_b.func_147439_a(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
/* 689 */         if ((bi == Blocks.field_150465_bP) || (((bi instanceof IInfusionStabiliser)) && (((IInfusionStabiliser)bi).canStabaliseInfusion(func_145831_w(), cc.field_71574_a, cc.field_71572_b, cc.field_71573_c))))
/*     */         {
/*     */ 
/* 692 */           sym += 0.1F;
/*     */         }
/*     */         
/* 695 */         int xx = this.field_145851_c + x;
/* 696 */         int zz = this.field_145849_e + z;
/* 697 */         bi = this.field_145850_b.func_147439_a(xx, cc.field_71572_b, zz);
/* 698 */         if ((bi == Blocks.field_150465_bP) || (((bi instanceof IInfusionStabiliser)) && (((IInfusionStabiliser)bi).canStabaliseInfusion(func_145831_w(), cc.field_71574_a, cc.field_71572_b, cc.field_71573_c))))
/*     */         {
/*     */ 
/* 701 */           sym -= 0.2F;
/*     */         }
/*     */       }
/*     */       
/* 705 */       this.symmetry = ((int)(this.symmetry + sym));
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/* 713 */     if ((!world.field_72995_K) && (this.active) && (!this.crafting)) {
/* 714 */       craftingStart(player);
/* 715 */       return 0;
/*     */     }
/* 717 */     if ((!world.field_72995_K) && (!this.active) && (validLocation())) {
/* 718 */       this.active = true;
/* 719 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 720 */       func_70296_d();
/* 721 */       return 0;
/*     */     }
/* 723 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*     */   {
/* 729 */     return wandstack;
/*     */   }
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
/*     */   private void doEffects()
/*     */   {
/* 747 */     if (this.crafting) {
/* 748 */       if (this.craftCount == 0) {
/* 749 */         this.field_145850_b.func_72980_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, "thaumcraft:infuserstart", 0.5F, 1.0F, false);
/* 750 */       } else if (this.craftCount % 65 == 0) {
/* 751 */         this.field_145850_b.func_72980_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, "thaumcraft:infuser", 0.5F, 1.0F, false);
/*     */       }
/* 753 */       this.craftCount += 1;
/* 754 */       Thaumcraft.proxy.blockRunes(this.field_145850_b, this.field_145851_c, this.field_145848_d - 2, this.field_145849_e, 0.5F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, 0.1F, 0.7F + this.field_145850_b.field_73012_v.nextFloat() * 0.3F, 25, -0.03F);
/*     */     }
/* 756 */     else if (this.craftCount > 0) {
/* 757 */       this.craftCount -= 2;
/* 758 */       if (this.craftCount < 0) this.craftCount = 0;
/* 759 */       if (this.craftCount > 50) { this.craftCount = 50;
/*     */       }
/*     */     }
/* 762 */     if ((this.active) && (this.startUp != 1.0F)) {
/* 763 */       if (this.startUp < 1.0F) this.startUp += Math.max(this.startUp / 10.0F, 0.001F);
/* 764 */       if (this.startUp > 0.999D) { this.startUp = 1.0F;
/*     */       }
/*     */     }
/* 767 */     if ((!this.active) && (this.startUp > 0.0F)) {
/* 768 */       if (this.startUp > 0.0F) this.startUp -= this.startUp / 10.0F;
/* 769 */       if (this.startUp < 0.001D) this.startUp = 0.0F;
/*     */     }
/* 771 */     for (String fxk : (String[])this.sourceFX.keySet().toArray(new String[0])) {
/* 772 */       SourceFX fx = (SourceFX)this.sourceFX.get(fxk);
/* 773 */       if (fx.ticks <= 0) {
/* 774 */         this.sourceFX.remove(fxk);
/*     */       }
/*     */       else {
/* 777 */         if ((fx.loc.field_71574_a == this.field_145851_c) && (fx.loc.field_71572_b == this.field_145848_d) && (fx.loc.field_71573_c == this.field_145849_e)) {
/* 778 */           Entity player = this.field_145850_b.func_73045_a(fx.color);
/* 779 */           if (player != null) {
/* 780 */             for (int a = 0; a < Thaumcraft.proxy.particleCount(2); a++) {
/* 781 */               Thaumcraft.proxy.drawInfusionParticles4(this.field_145850_b, player.field_70165_t + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * player.field_70130_N, player.field_70121_D.field_72338_b + this.field_145850_b.field_73012_v.nextFloat() * player.field_70131_O, player.field_70161_v + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * player.field_70130_N, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */             }
/*     */             
/*     */           }
/*     */           
/*     */         }
/*     */         else
/*     */         {
/* 789 */           TileEntity tile = this.field_145850_b.func_147438_o(fx.loc.field_71574_a, fx.loc.field_71572_b, fx.loc.field_71573_c);
/* 790 */           if ((tile instanceof TilePedestal)) {
/* 791 */             ItemStack is = ((TilePedestal)tile).func_70301_a(0);
/* 792 */             if (is != null) {
/* 793 */               if (this.field_145850_b.field_73012_v.nextInt(3) == 0)
/*     */               {
/* 795 */                 Thaumcraft.proxy.drawInfusionParticles3(this.field_145850_b, fx.loc.field_71574_a + this.field_145850_b.field_73012_v.nextFloat(), fx.loc.field_71572_b + this.field_145850_b.field_73012_v.nextFloat() + 1.0F, fx.loc.field_71573_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */ 
/*     */               }
/*     */               else
/*     */               {
/*     */ 
/* 801 */                 Item bi = is.func_77973_b();
/* 802 */                 int md = is.func_77960_j();
/* 803 */                 if ((is.func_94608_d() == 0) && ((bi instanceof ItemBlock))) {
/* 804 */                   for (int a = 0; a < Thaumcraft.proxy.particleCount(2); a++) {
/* 805 */                     Thaumcraft.proxy.drawInfusionParticles2(this.field_145850_b, fx.loc.field_71574_a + this.field_145850_b.field_73012_v.nextFloat(), fx.loc.field_71572_b + this.field_145850_b.field_73012_v.nextFloat() + 1.0F, fx.loc.field_71573_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145851_c, this.field_145848_d, this.field_145849_e, Block.func_149634_a(bi), md);
/*     */                   }
/*     */                   
/*     */                 }
/*     */                 else
/*     */                 {
/* 811 */                   for (int a = 0; a < Thaumcraft.proxy.particleCount(2); a++) {
/* 812 */                     Thaumcraft.proxy.drawInfusionParticles1(this.field_145850_b, fx.loc.field_71574_a + 0.4F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, fx.loc.field_71572_b + 1.23F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, fx.loc.field_71573_c + 0.4F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, this.field_145851_c, this.field_145848_d, this.field_145849_e, bi, md);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 820 */             fx.ticks = 0;
/*     */           } }
/* 822 */         fx.ticks -= 1;
/* 823 */         this.sourceFX.put(fxk, fx);
/*     */       }
/*     */     }
/*     */     
/* 827 */     if ((this.crafting) && (this.instability > 0) && (this.field_145850_b.field_73012_v.nextInt(200) <= this.instability)) {
/* 828 */       Thaumcraft.proxy.nodeBolt(this.field_145850_b, this.field_145851_c + 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.5F, this.field_145851_c + 0.5F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 2.0F, this.field_145848_d + 0.5F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 2.0F, this.field_145849_e + 0.5F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 2.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/* 839 */     return this.recipeEssentia;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */   public int addToContainer(Aspect tag, int amount)
/*     */   {
/* 849 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tag, int amount)
/*     */   {
/* 854 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 859 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amount)
/*     */   {
/* 864 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 869 */     return false;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 874 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 879 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileInfusionMatrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */