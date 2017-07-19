/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.items.baubles.ItemAmuletVis;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileAlchemyFurnace;
/*     */ import thaumcraft.common.tiles.TileFluxScrubber;
/*     */ import thaumcraft.common.tiles.TileFocalManipulator;
/*     */ import thaumcraft.common.tiles.TileInfusionMatrix;
/*     */ import thaumcraft.common.tiles.TileInfusionPillar;
/*     */ import thaumcraft.common.tiles.TileNodeConverter;
/*     */ import thaumcraft.common.tiles.TileNodeStabilizer;
/*     */ import thaumcraft.common.tiles.TilePedestal;
/*     */ import thaumcraft.common.tiles.TileSpa;
/*     */ import thaumcraft.common.tiles.TileWandPedestal;
/*     */ 
/*     */ public class BlockStoneDevice extends BlockContainer
/*     */ {
/*     */   public BlockStoneDevice()
/*     */   {
/*  55 */     super(Material.field_151576_e);
/*  56 */     func_149711_c(3.0F);
/*  57 */     func_149752_b(25.0F);
/*  58 */     func_149672_a(Block.field_149769_e);
/*  59 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  60 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*  63 */   public IIcon[] iconFurnace = new IIcon[5];
/*  64 */   public IIcon[] iconPedestal = new IIcon[2];
/*  65 */   public IIcon[] iconWandPedestal = new IIcon[2];
/*  66 */   public IIcon[] iconWandPedestalFocus = new IIcon[3];
/*  67 */   public IIcon[] iconSpa = new IIcon[2];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  72 */     this.iconPedestal[0] = ir.func_94245_a("thaumcraft:pedestal_side");
/*  73 */     this.iconPedestal[1] = ir.func_94245_a("thaumcraft:pedestal_top");
/*  74 */     this.iconWandPedestal[0] = ir.func_94245_a("thaumcraft:wandpedestal_side");
/*  75 */     this.iconWandPedestal[1] = ir.func_94245_a("thaumcraft:wandpedestal_top");
/*  76 */     this.iconWandPedestalFocus[0] = ir.func_94245_a("thaumcraft:wandpedestal_focus_side");
/*  77 */     this.iconWandPedestalFocus[1] = ir.func_94245_a("thaumcraft:wandpedestal_focus_top");
/*  78 */     this.iconWandPedestalFocus[2] = ir.func_94245_a("thaumcraft:wandpedestal_focus_bot");
/*  79 */     this.iconFurnace[0] = ir.func_94245_a("thaumcraft:al_furnace_side");
/*  80 */     this.iconFurnace[1] = ir.func_94245_a("thaumcraft:al_furnace_top");
/*  81 */     this.iconFurnace[2] = ir.func_94245_a("thaumcraft:al_furnace_front_off");
/*  82 */     this.iconFurnace[3] = ir.func_94245_a("thaumcraft:al_furnace_front_on");
/*  83 */     this.iconFurnace[4] = ir.func_94245_a("thaumcraft:al_furnace_top_filled");
/*  84 */     this.iconSpa[0] = ir.func_94245_a("thaumcraft:spa_side");
/*  85 */     this.iconSpa[1] = ir.func_94245_a("thaumcraft:spa_top");
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  90 */     return ConfigBlocks.blockStoneDeviceRI;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  96 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 102 */     return false;
/*     */   }
/*     */   
/*     */   public IIcon func_149691_a(int side, int md)
/*     */   {
/* 107 */     if (md == 0) {
/* 108 */       if (side == 1) return this.iconFurnace[1];
/* 109 */       if (side > 1) return this.iconFurnace[2];
/*     */     }
/* 111 */     else if (md == 1) {
/* 112 */       if (side <= 1) return this.iconPedestal[1];
/* 113 */       if (side > 1) { return this.iconPedestal[0];
/*     */       }
/*     */     }
/* 116 */     else if (md == 5) {
/* 117 */       if (side == 0) return this.iconPedestal[1];
/* 118 */       if (side == 1) return this.iconWandPedestal[1];
/* 119 */       if (side > 1) { return this.iconWandPedestal[0];
/*     */       }
/*     */     }
/* 122 */     else if (md == 8) {
/* 123 */       if (side == 0) return this.iconWandPedestalFocus[2];
/* 124 */       if (side == 1) return this.iconWandPedestalFocus[1];
/* 125 */       if (side > 1) return this.iconWandPedestalFocus[0];
/*     */     }
/* 127 */     else if (md == 12) {
/* 128 */       if (side == 0) return this.iconPedestal[1];
/* 129 */       if (side == 1) return this.iconSpa[1];
/* 130 */       if (side > 1) return this.iconSpa[0];
/*     */     }
/* 132 */     return this.iconPedestal[1];
/*     */   }
/*     */   
/*     */   public IIcon func_149673_e(IBlockAccess iblockaccess, int i, int j, int k, int side)
/*     */   {
/* 137 */     int metadata = iblockaccess.func_72805_g(i, j, k);
/* 138 */     if (metadata == 0) {
/* 139 */       TileEntity te = iblockaccess.func_147438_o(i, j, k);
/* 140 */       if (side == 1) {
/* 141 */         if ((te != null) && ((te instanceof TileAlchemyFurnace)) && (((TileAlchemyFurnace)te).vis > 0)) {
/* 142 */           return this.iconFurnace[4];
/*     */         }
/* 144 */         return this.iconFurnace[1];
/*     */       }
/* 146 */       if (side > 1)
/*     */       {
/* 148 */         if ((te != null) && ((te instanceof TileAlchemyFurnace)) && (((TileAlchemyFurnace)te).isBurning())) {
/* 149 */           return this.iconFurnace[3];
/*     */         }
/* 151 */         return this.iconFurnace[2];
/*     */       }
/*     */       
/*     */     }
/* 155 */     else if ((metadata == 1) || (metadata == 5) || (metadata == 8) || (metadata == 12)) {
/* 156 */       return super.func_149673_e(iblockaccess, i, j, k, side);
/*     */     }
/* 158 */     return this.iconFurnace[0];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 164 */     par3List.add(new ItemStack(par1, 1, 0));
/* 165 */     par3List.add(new ItemStack(par1, 1, 1));
/* 166 */     par3List.add(new ItemStack(par1, 1, 2));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 171 */     par3List.add(new ItemStack(par1, 1, 5));
/* 172 */     par3List.add(new ItemStack(par1, 1, 8));
/* 173 */     par3List.add(new ItemStack(par1, 1, 9));
/* 174 */     par3List.add(new ItemStack(par1, 1, 10));
/* 175 */     par3List.add(new ItemStack(par1, 1, 11));
/* 176 */     par3List.add(new ItemStack(par1, 1, 12));
/* 177 */     par3List.add(new ItemStack(par1, 1, 13));
/* 178 */     par3List.add(new ItemStack(par1, 1, 14));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World w, int i, int j, int k, Random r)
/*     */   {
/* 186 */     TileEntity te = w.func_147438_o(i, j, k);
/* 187 */     if ((te != null) && ((te instanceof TileAlchemyFurnace)) && 
/* 188 */       (((TileAlchemyFurnace)te).isBurning())) {
/* 189 */       float f = i + 0.5F;
/* 190 */       float f1 = j + 0.2F + r.nextFloat() * 5.0F / 16.0F;
/* 191 */       float f2 = k + 0.5F;
/* 192 */       float f3 = 0.52F;
/* 193 */       float f4 = r.nextFloat() * 0.5F - 0.25F;
/*     */       
/* 195 */       w.func_72869_a("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 196 */       w.func_72869_a("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/*     */       
/* 198 */       w.func_72869_a("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 199 */       w.func_72869_a("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/*     */       
/* 201 */       w.func_72869_a("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
/* 202 */       w.func_72869_a("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
/*     */       
/* 204 */       w.func_72869_a("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
/* 205 */       w.func_72869_a("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 212 */     int meta = world.func_72805_g(x, y, z);
/* 213 */     if (meta == 0) {
/* 214 */       TileEntity te = world.func_147438_o(x, y, z);
/* 215 */       if ((te != null) && ((te instanceof TileAlchemyFurnace)) && (((TileAlchemyFurnace)te).isBurning()))
/*     */       {
/* 217 */         return 12;
/*     */       }
/*     */     }
/* 220 */     else if (meta == 2) {
/* 221 */       return 10;
/*     */     }
/* 223 */     return super.getLightValue(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149692_a(int metadata)
/*     */   {
/* 229 */     return metadata == 4 ? 6 : metadata == 3 ? 7 : metadata;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int metadata, Random par2Random, int par3)
/*     */   {
/* 234 */     return (metadata == 3) || (metadata == 4) ? Item.func_150898_a(ConfigBlocks.blockCosmeticSolid) : super.func_149650_a(metadata, par2Random, par3);
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 239 */     if (metadata == 0) return new TileAlchemyFurnace();
/* 240 */     if (metadata == 1) return new TilePedestal();
/* 241 */     if (metadata == 2) return new TileInfusionMatrix();
/* 242 */     if (metadata == 3) return new TileInfusionPillar();
/* 243 */     if (metadata == 5) return new TileWandPedestal();
/* 244 */     if ((metadata == 9) || (metadata == 10)) return new TileNodeStabilizer();
/* 245 */     if (metadata == 11) return new TileNodeConverter();
/* 246 */     if (metadata == 12) return new TileSpa();
/* 247 */     if (metadata == 13) return new TileFocalManipulator();
/* 248 */     if (metadata == 14) return new TileFluxScrubber();
/* 249 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 254 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149736_g(World world, int x, int y, int z, int rs)
/*     */   {
/* 260 */     TileEntity te = world.func_147438_o(x, y, z);
/* 261 */     if ((te != null) && (((te instanceof TilePedestal)) || ((te instanceof TileAlchemyFurnace)))) {
/* 262 */       return Container.func_94526_b((IInventory)te);
/*     */     }
/* 264 */     if ((te != null) && ((te instanceof TileWandPedestal)) && (((TileWandPedestal)te).getAspects() != null) && (((TileWandPedestal)te).func_70301_a(0) != null) && ((((TileWandPedestal)te).func_70301_a(0).func_77973_b() instanceof ItemWandCasting)))
/*     */     {
/*     */ 
/*     */ 
/* 268 */       ItemWandCasting wand = (ItemWandCasting)((TileWandPedestal)te).func_70301_a(0).func_77973_b();
/* 269 */       float r = wand.getAllVis(((TileWandPedestal)te).func_70301_a(0)).visSize() / (wand.getMaxVis(((TileWandPedestal)te).func_70301_a(0)) * 6.0F);
/*     */       
/* 271 */       return MathHelper.func_76141_d(r * 14.0F) + 1;
/*     */     }
/* 273 */     return 0;
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 278 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/* 284 */     InventoryUtils.dropItems(par1World, par2, par3, par4);
/* 285 */     TileEntity tileEntity = par1World.func_147438_o(par2, par3, par4);
/* 286 */     if ((tileEntity != null) && ((tileEntity instanceof TileInfusionMatrix)) && 
/* 287 */       (((TileInfusionMatrix)tileEntity).crafting)) {
/* 288 */       par1World.func_72876_a(null, par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, 2.0F, true);
/*     */     }
/*     */     
/* 291 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block par5)
/*     */   {
/* 296 */     TileEntity te = world.func_147438_o(x, y, z);
/* 297 */     if ((te != null) && ((te instanceof TileAlchemyFurnace))) {
/* 298 */       ((TileAlchemyFurnace)te).getBellows();
/*     */     }
/* 300 */     else if ((te != null) && ((te instanceof TileNodeConverter))) {
/* 301 */       ((TileNodeConverter)te).checkStatus();
/*     */     } else {
/* 303 */       int metadata = world.func_72805_g(x, y, z);
/* 304 */       if (metadata == 1) {
/* 305 */         if (!world.func_147437_c(x, y + 1, z)) {
/* 306 */           InventoryUtils.dropItems(world, x, y, z);
/*     */         }
/*     */       }
/* 309 */       else if (metadata == 5) {
/* 310 */         if ((!world.func_147437_c(x, y + 1, z)) && ((world.func_147439_a(x, y + 1, z) != this) || (world.func_72805_g(x, y + 1, z) != 8)))
/*     */         {
/*     */ 
/* 313 */           InventoryUtils.dropItems(world, x, y, z);
/*     */         }
/*     */       }
/* 316 */       else if (metadata == 3) {
/* 317 */         if ((world.func_147439_a(x, y + 1, z) != this) || (world.func_72805_g(x, y + 1, z) != 4)) {
/* 318 */           func_149697_b(world, x, y, z, metadata, 0);
/* 319 */           world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 3);
/*     */         }
/*     */       }
/* 322 */       else if ((metadata == 4) && (
/* 323 */         (world.func_147439_a(x, y - 1, z) != this) || (world.func_72805_g(x, y - 1, z) != 3))) {
/* 324 */         func_149697_b(world, x, y, z, metadata, 0);
/* 325 */         world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 3);
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
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
/*     */   {
/* 338 */     if (world.field_72995_K) return true;
/* 339 */     int metadata = world.func_72805_g(x, y, z);
/* 340 */     TileEntity tileEntity = world.func_147438_o(x, y, z);
/*     */     
/* 342 */     if ((metadata == 0) && ((tileEntity instanceof TileAlchemyFurnace)) && (!player.func_70093_af())) {
/* 343 */       player.openGui(Thaumcraft.instance, 9, world, x, y, z);
/* 344 */       return true;
/*     */     }
/*     */     
/* 347 */     if ((metadata == 1) && ((tileEntity instanceof TilePedestal))) {
/* 348 */       TilePedestal ped = (TilePedestal)tileEntity;
/* 349 */       if (ped.func_70301_a(0) != null) {
/* 350 */         InventoryUtils.dropItemsAtEntity(world, x, y, z, player);
/*     */         
/* 352 */         world.func_72908_a(x, y, z, "random.pop", 0.2F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 1.5F);
/*     */         
/*     */ 
/* 355 */         return true;
/*     */       }
/* 357 */       if (player.func_71045_bC() != null) {
/* 358 */         ItemStack i = player.func_71045_bC().func_77946_l();
/* 359 */         i.field_77994_a = 1;
/* 360 */         ped.func_70299_a(0, i);
/* 361 */         player.func_71045_bC().field_77994_a -= 1;
/* 362 */         if (player.func_71045_bC().field_77994_a == 0) {
/* 363 */           player.func_70062_b(0, null);
/*     */         }
/* 365 */         player.field_71071_by.func_70296_d();
/*     */         
/* 367 */         world.func_72908_a(x, y, z, "random.pop", 0.2F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 1.6F);
/*     */         
/* 369 */         return true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 374 */     if (metadata == 8) {
/* 375 */       y--;
/* 376 */       metadata = world.func_72805_g(x, y, z);
/* 377 */       tileEntity = world.func_147438_o(x, y, z);
/*     */     }
/*     */     
/* 380 */     if ((metadata == 5) && ((tileEntity instanceof TileWandPedestal))) {
/* 381 */       if ((player.field_71071_by.func_70448_g() != null) && (player.field_71071_by.func_70448_g().func_77969_a(new ItemStack(this, 1, 8))))
/*     */       {
/* 383 */         return false; }
/* 384 */       TileWandPedestal ped = (TileWandPedestal)tileEntity;
/* 385 */       if (ped.func_70301_a(0) != null) {
/* 386 */         InventoryUtils.dropItemsAtEntity(world, x, y, z, player);
/* 387 */         world.func_147471_g(x, y, z);
/* 388 */         ped.func_70296_d();
/* 389 */         world.func_72908_a(x, y, z, "random.pop", 0.2F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 1.5F);
/*     */         
/*     */ 
/* 392 */         return true;
/*     */       }
/* 394 */       if ((player.func_71045_bC() != null) && (((player.func_71045_bC().func_77973_b() instanceof ItemWandCasting)) || ((player.func_71045_bC().func_77973_b() instanceof ItemAmuletVis))))
/*     */       {
/*     */ 
/* 397 */         ItemStack i = player.func_71045_bC().func_77946_l();
/* 398 */         i.field_77994_a = 1;
/* 399 */         ped.func_70299_a(0, i);
/* 400 */         player.func_71045_bC().field_77994_a -= 1;
/* 401 */         if (player.func_71045_bC().field_77994_a == 0) {
/* 402 */           player.func_70062_b(0, null);
/*     */         }
/* 404 */         player.field_71071_by.func_70296_d();
/* 405 */         world.func_147471_g(x, y, z);
/* 406 */         ped.func_70296_d();
/* 407 */         world.func_72908_a(x, y, z, "random.pop", 0.2F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 1.6F);
/*     */         
/* 409 */         return true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 414 */     if ((metadata == 12) && ((tileEntity instanceof TileSpa)) && (!player.func_70093_af()))
/*     */     {
/* 416 */       FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(player.field_71071_by.func_70448_g());
/* 417 */       if (fs != null) {
/* 418 */         int volume = fs.amount;
/* 419 */         TileSpa tile = (TileSpa)tileEntity;
/* 420 */         if ((tile.tank.getFluidAmount() < tile.tank.getCapacity()) && ((tile.tank.getFluid() == null) || (tile.tank.getFluid().isFluidEqual(fs))))
/*     */         {
/* 422 */           tile.fill(ForgeDirection.UNKNOWN, FluidContainerRegistry.getFluidForFilledItem(player.field_71071_by.func_70448_g()), true);
/* 423 */           ItemStack emptyContainer = null;
/* 424 */           FluidContainerRegistry.FluidContainerData[] fcs = FluidContainerRegistry.getRegisteredFluidContainerData();
/* 425 */           for (FluidContainerRegistry.FluidContainerData fcd : fcs) {
/* 426 */             if (fcd.filledContainer.func_77969_a(player.field_71071_by.func_70448_g())) {
/* 427 */               emptyContainer = fcd.emptyContainer.func_77946_l();
/*     */             }
/*     */           }
/*     */           
/* 431 */           player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
/* 432 */           if ((emptyContainer != null) && 
/* 433 */             (!player.field_71071_by.func_70441_a(emptyContainer))) {
/* 434 */             player.func_71019_a(emptyContainer, false);
/*     */           }
/*     */           
/* 437 */           player.field_71069_bz.func_75142_b();
/* 438 */           tile.func_70296_d();
/* 439 */           world.func_147471_g(x, y, z);
/* 440 */           world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "game.neutral.swim", 0.33F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F);
/*     */         }
/*     */       }
/*     */       else {
/* 444 */         player.openGui(Thaumcraft.instance, 19, world, x, y, z);
/*     */       }
/* 446 */       return true;
/*     */     }
/*     */     
/* 449 */     if ((metadata == 13) && ((tileEntity instanceof TileFocalManipulator)) && (!player.func_70093_af())) {
/* 450 */       if (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "FOCALMANIPULATION")) {
/* 451 */         player.openGui(Thaumcraft.instance, 20, world, x, y, z);
/* 452 */       } else if (!world.field_72995_K) {
/* 453 */         player.func_145747_a(new ChatComponentText(EnumChatFormatting.RED + net.minecraft.util.StatCollector.func_74838_a("tc.researchmissing")));
/*     */       }
/* 455 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 459 */     return super.func_149727_a(world, x, y, z, player, side, par7, par8, par9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
/*     */   {
/* 467 */     int metadata = world.func_72805_g(i, j, k);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 477 */     if (metadata == 5) {
/* 478 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
/* 479 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/* 480 */       func_149676_a(0.25F, 0.5F, 0.25F, 0.75F, 1.0F, 0.75F);
/* 481 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/* 482 */       func_149676_a(0.125F, 0.25F, 0.125F, 0.875F, 0.5F, 0.875F);
/* 483 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     }
/*     */     else {
/* 486 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 487 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int i, int j, int k)
/*     */   {
/* 493 */     int metadata = world.func_72805_g(i, j, k);
/*     */     
/* 495 */     if (metadata == 1) {
/* 496 */       func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.99F, 0.75F);
/*     */     }
/* 498 */     else if (metadata == 5) {
/* 499 */       func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
/*     */     }
/* 501 */     else if (metadata == 3) {
/* 502 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*     */     }
/* 504 */     else if (metadata == 4) {
/* 505 */       func_149676_a(0.0F, -1.0F, 0.0F, 1.0F, -0.5F, 1.0F);
/*     */     }
/* 507 */     else if (metadata == 8) {
/* 508 */       func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.4375F, 0.9375F);
/*     */     } else
/* 510 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 511 */     super.func_149719_a(world, i, j, k);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149696_a(World par1World, int par2, int par3, int par4, int par5, int par6)
/*     */   {
/* 518 */     if (par5 == 1)
/*     */     {
/* 520 */       if (par1World.field_72995_K) {
/* 521 */         Thaumcraft.proxy.blockSparkle(par1World, par2, par3, par4, 11960575, 2);
/* 522 */         par1World.func_72926_e(2001, par2, par3, par4, Block.func_149682_b(Blocks.field_150417_aV) + 0);
/*     */       }
/* 524 */       return true;
/*     */     }
/*     */     
/* 527 */     return super.func_149696_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/* 533 */     int meta = world.func_72805_g(x, y, z);
/* 534 */     if ((meta == 11) && (side == ForgeDirection.UP)) return true;
/* 535 */     if (meta == 12) return true;
/* 536 */     return super.isSideSolid(world, x, y, z, side);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockStoneDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */