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
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.visnet.VisNetHandler;
/*     */ import thaumcraft.client.renderers.block.BlockRenderer;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.items.ItemShard;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileAlembic;
/*     */ import thaumcraft.common.tiles.TileArcaneLamp;
/*     */ import thaumcraft.common.tiles.TileArcaneLampFertility;
/*     */ import thaumcraft.common.tiles.TileArcaneLampGrowth;
/*     */ import thaumcraft.common.tiles.TileBrainbox;
/*     */ import thaumcraft.common.tiles.TileCrucible;
/*     */ import thaumcraft.common.tiles.TileGrate;
/*     */ import thaumcraft.common.tiles.TileMagicWorkbenchCharger;
/*     */ import thaumcraft.common.tiles.TileThaumatorium;
/*     */ import thaumcraft.common.tiles.TileThaumatoriumTop;
/*     */ import thaumcraft.common.tiles.TileVisRelay;
/*     */ 
/*     */ public class BlockMetalDevice extends BlockContainer
/*     */ {
/*     */   public BlockMetalDevice()
/*     */   {
/*  62 */     super(Material.field_151573_f);
/*  63 */     func_149711_c(3.0F);
/*  64 */     func_149752_b(17.0F);
/*  65 */     func_149672_a(Block.field_149777_j);
/*  66 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  67 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*  70 */   public IIcon[] icon = new IIcon[23];
/*     */   public IIcon iconGlow;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir) {
/*  75 */     this.icon[0] = ir.func_94245_a("thaumcraft:metalbase");
/*  76 */     for (int a = 1; a <= 6; a++) this.icon[a] = ir.func_94245_a("thaumcraft:crucible" + a);
/*  77 */     this.icon[7] = ir.func_94245_a("thaumcraft:goldbase");
/*  78 */     this.icon[8] = ir.func_94245_a("thaumcraft:grate");
/*  79 */     this.icon[9] = ir.func_94245_a("thaumcraft:grate_hatch");
/*  80 */     this.icon[10] = ir.func_94245_a("thaumcraft:lamp_side");
/*  81 */     this.icon[11] = ir.func_94245_a("thaumcraft:lamp_top");
/*  82 */     this.icon[12] = ir.func_94245_a("thaumcraft:lamp_grow_side");
/*  83 */     this.icon[13] = ir.func_94245_a("thaumcraft:lamp_grow_top");
/*  84 */     this.icon[14] = ir.func_94245_a("thaumcraft:lamp_grow_side_off");
/*  85 */     this.icon[15] = ir.func_94245_a("thaumcraft:lamp_grow_top_off");
/*  86 */     this.icon[16] = ir.func_94245_a("thaumcraft:alchemyblock");
/*  87 */     this.icon[17] = ir.func_94245_a("thaumcraft:brainbox");
/*  88 */     this.icon[18] = ir.func_94245_a("thaumcraft:lamp_fert_side");
/*  89 */     this.icon[19] = ir.func_94245_a("thaumcraft:lamp_fert_top");
/*  90 */     this.icon[20] = ir.func_94245_a("thaumcraft:lamp_fert_side_off");
/*  91 */     this.icon[21] = ir.func_94245_a("thaumcraft:lamp_fert_top_off");
/*  92 */     this.icon[22] = ir.func_94245_a("thaumcraft:alchemyblockadv");
/*  93 */     this.iconGlow = ir.func_94245_a("thaumcraft:animatedglow");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IIcon func_149691_a(int i, int md)
/*     */   {
/* 100 */     if (md == 3) return this.icon[22];
/* 101 */     if (md == 7) return this.icon[10];
/* 102 */     if (md == 8) return this.icon[12];
/* 103 */     if ((md == 10) || (md == 9) || (md == 11)) return this.icon[16];
/* 104 */     if (md == 12) return this.icon[17];
/* 105 */     if (md == 13) return this.icon[18];
/* 106 */     if ((md == 14) || (md == 2)) return this.icon[0];
/* 107 */     return (md == 0) || (md == 1) || (md == 5) || (md == 6) ? this.icon[0] : this.icon[7];
/*     */   }
/*     */   
/*     */   public IIcon func_149673_e(IBlockAccess iblockaccess, int i, int j, int k, int side)
/*     */   {
/* 112 */     int metadata = iblockaccess.func_72805_g(i, j, k);
/* 113 */     if ((metadata == 5) || (metadata == 6)) {
/* 114 */       return this.icon[8];
/*     */     }
/* 116 */     if (metadata == 7) {
/* 117 */       if (side <= 1) return this.icon[11];
/* 118 */       return this.icon[10];
/*     */     }
/*     */     
/* 121 */     if (metadata == 8)
/*     */     {
/* 123 */       TileEntity te = iblockaccess.func_147438_o(i, j, k);
/* 124 */       if ((te != null) && ((te instanceof TileArcaneLampGrowth))) {
/* 125 */         if (((TileArcaneLampGrowth)te).charges > 0) {
/* 126 */           if (side <= 1) return this.icon[13];
/* 127 */           return this.icon[12];
/*     */         }
/* 129 */         if (side <= 1) return this.icon[15];
/* 130 */         return this.icon[14];
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 135 */     else if (metadata == 13) {
/* 136 */       TileEntity te = iblockaccess.func_147438_o(i, j, k);
/* 137 */       if ((te != null) && ((te instanceof TileArcaneLampFertility))) {
/* 138 */         if (((TileArcaneLampFertility)te).charges > 0) {
/* 139 */           if (side <= 1) return this.icon[19];
/* 140 */           return this.icon[18];
/*     */         }
/* 142 */         if (side <= 1) return this.icon[21];
/* 143 */         return this.icon[20];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 148 */       if ((metadata == 10) || (metadata == 9) || (metadata == 11)) { return this.icon[16];
/*     */       }
/* 150 */       if (metadata == 12) { return this.icon[17];
/*     */       }
/* 152 */       if (metadata == 3) return this.icon[22];
/*     */     }
/* 154 */     if (side == 1) return this.icon[1];
/* 155 */     if (side == 0) { return this.icon[2];
/*     */     }
/* 157 */     return this.icon[3];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 165 */     par3List.add(new ItemStack(par1, 1, 0));
/* 166 */     par3List.add(new ItemStack(par1, 1, 1));
/* 167 */     par3List.add(new ItemStack(par1, 1, 5));
/*     */     
/* 169 */     par3List.add(new ItemStack(par1, 1, 7));
/* 170 */     par3List.add(new ItemStack(par1, 1, 8));
/* 171 */     par3List.add(new ItemStack(par1, 1, 13));
/*     */     
/* 173 */     par3List.add(new ItemStack(par1, 1, 9));
/* 174 */     par3List.add(new ItemStack(par1, 1, 3));
/* 175 */     par3List.add(new ItemStack(par1, 1, 12));
/*     */     
/* 177 */     par3List.add(new ItemStack(par1, 1, 14));
/* 178 */     par3List.add(new ItemStack(par1, 1, 2));
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 183 */     return ConfigBlocks.blockMetalDeviceRI;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 189 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 195 */     return false;
/*     */   }
/*     */   
/* 198 */   private int delay = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149670_a(World world, int i, int j, int k, Entity entity)
/*     */   {
/* 208 */     if (!world.field_72995_K) {
/* 209 */       int metadata = world.func_72805_g(i, j, k);
/*     */       
/* 211 */       if (metadata == 0) {
/* 212 */         TileCrucible tile = (TileCrucible)world.func_147438_o(i, j, k);
/* 213 */         if ((tile != null) && ((entity instanceof EntityItem)) && (!(entity instanceof EntitySpecialItem)) && (tile.heat > 150) && (tile.tank.getFluidAmount() > 0))
/*     */         {
/*     */ 
/* 216 */           tile.attemptSmelt((EntityItem)entity);
/*     */         } else {
/* 218 */           this.delay += 1;
/* 219 */           if (this.delay < 10) return;
/* 220 */           this.delay = 0;
/* 221 */           if (((entity instanceof EntityLivingBase)) && (tile != null) && (tile.heat > 150) && (tile.tank.getFluidAmount() > 0))
/*     */           {
/*     */ 
/* 224 */             entity.func_70097_a(DamageSource.field_76372_a, 1.0F);
/* 225 */             world.func_72908_a(i, j, k, "random.fizz", 0.4F, 2.0F + world.field_73012_v.nextFloat() * 0.4F);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149719_a(IBlockAccess world, int i, int j, int k)
/*     */   {
/* 236 */     int metadata = world.func_72805_g(i, j, k);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 241 */     if ((metadata == 5) || (metadata == 6)) {
/* 242 */       func_149676_a(0.0F, 0.8125F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/* 244 */     else if ((metadata == 7) || (metadata == 8) || (metadata == 13)) {
/* 245 */       func_149676_a(BlockRenderer.W4, BlockRenderer.W2, BlockRenderer.W4, BlockRenderer.W12, BlockRenderer.W14, BlockRenderer.W12);
/*     */     }
/* 247 */     else if (metadata == 10) {
/* 248 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
/*     */     }
/* 250 */     else if (metadata == 11) {
/* 251 */       func_149676_a(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/* 253 */     else if (metadata == 12) {
/* 254 */       func_149676_a(BlockRenderer.W3, BlockRenderer.W3, BlockRenderer.W3, BlockRenderer.W13, BlockRenderer.W13, BlockRenderer.W13);
/*     */     }
/* 256 */     else if (metadata == 2) {
/* 257 */       func_149676_a(BlockRenderer.W5, 0.5F, BlockRenderer.W5, BlockRenderer.W11, 1.0F, BlockRenderer.W11);
/*     */     }
/* 259 */     else if (metadata == 14) {
/* 260 */       TileEntity te = world.func_147438_o(i, j, k);
/* 261 */       if ((te != null) && ((te instanceof TileVisRelay))) {
/* 262 */         switch (ForgeDirection.getOrientation(((TileVisRelay)te).orientation).getOpposite()) {
/*     */         case UP: 
/* 264 */           func_149676_a(BlockRenderer.W5, 0.5F, BlockRenderer.W5, BlockRenderer.W11, 1.0F, BlockRenderer.W11); break;
/*     */         case DOWN: 
/* 266 */           func_149676_a(BlockRenderer.W5, 0.0F, BlockRenderer.W5, BlockRenderer.W11, 0.5F, BlockRenderer.W11); break;
/*     */         case EAST: 
/* 268 */           func_149676_a(0.5F, BlockRenderer.W5, BlockRenderer.W5, 1.0F, BlockRenderer.W11, BlockRenderer.W11); break;
/*     */         case WEST: 
/* 270 */           func_149676_a(0.0F, BlockRenderer.W5, BlockRenderer.W5, 0.5F, BlockRenderer.W11, BlockRenderer.W11); break;
/*     */         case SOUTH: 
/* 272 */           func_149676_a(BlockRenderer.W5, BlockRenderer.W5, 0.5F, BlockRenderer.W11, BlockRenderer.W11, 1.0F); break;
/*     */         case NORTH: 
/* 274 */           func_149676_a(BlockRenderer.W5, BlockRenderer.W5, 0.0F, BlockRenderer.W11, BlockRenderer.W11, 0.5F);
/*     */         }
/*     */       }
/*     */     } else {
/* 278 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F); }
/* 279 */     super.func_149719_a(world, i, j, k);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
/*     */   {
/* 285 */     int metadata = world.func_72805_g(i, j, k);
/*     */     
/* 287 */     if (metadata == 0) {
/* 288 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
/* 289 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/* 290 */       float f = 0.125F;
/* 291 */       func_149676_a(0.0F, 0.0F, 0.0F, f, 0.85F, 1.0F);
/* 292 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/* 293 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.85F, f);
/* 294 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/* 295 */       func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 0.85F, 1.0F);
/* 296 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/* 297 */       func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 0.85F, 1.0F);
/* 298 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 304 */     else if (metadata == 2)
/*     */     {
/* 306 */       func_149676_a(BlockRenderer.W5, 0.5F, BlockRenderer.W5, BlockRenderer.W11, 1.0F, BlockRenderer.W11);
/* 307 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     }
/* 309 */     else if (metadata == 5) {
/* 310 */       if ((par7Entity != null) && (!(par7Entity instanceof EntityItem))) {
/* 311 */         func_149676_a(0.0F, 0.8125F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 312 */         super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */       }
/*     */     }
/* 315 */     else if (metadata == 6) {
/* 316 */       func_149676_a(0.0F, 0.8125F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 317 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     }
/* 319 */     else if ((metadata == 7) || (metadata == 8) || (metadata == 13)) {
/* 320 */       func_149676_a(BlockRenderer.W4, BlockRenderer.W2, BlockRenderer.W4, BlockRenderer.W12, BlockRenderer.W14, BlockRenderer.W12);
/* 321 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     }
/* 323 */     else if (metadata == 12) {
/* 324 */       func_149676_a(BlockRenderer.W3, BlockRenderer.W3, BlockRenderer.W3, BlockRenderer.W13, BlockRenderer.W13, BlockRenderer.W13);
/* 325 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     }
/* 327 */     else if (metadata == 14) {
/* 328 */       TileEntity te = world.func_147438_o(i, j, k);
/* 329 */       if ((te != null) && ((te instanceof TileVisRelay))) {
/* 330 */         switch (ForgeDirection.getOrientation(((TileVisRelay)te).orientation).getOpposite()) {
/*     */         case UP: 
/* 332 */           func_149676_a(BlockRenderer.W5, 0.5F, BlockRenderer.W5, BlockRenderer.W11, 1.0F, BlockRenderer.W11); break;
/*     */         case DOWN: 
/* 334 */           func_149676_a(BlockRenderer.W5, 0.0F, BlockRenderer.W5, BlockRenderer.W11, 0.5F, BlockRenderer.W11); break;
/*     */         case EAST: 
/* 336 */           func_149676_a(0.5F, BlockRenderer.W5, BlockRenderer.W5, 1.0F, BlockRenderer.W11, BlockRenderer.W11); break;
/*     */         case WEST: 
/* 338 */           func_149676_a(0.0F, BlockRenderer.W5, BlockRenderer.W5, 0.5F, BlockRenderer.W11, BlockRenderer.W11); break;
/*     */         case SOUTH: 
/* 340 */           func_149676_a(BlockRenderer.W5, BlockRenderer.W5, 0.5F, BlockRenderer.W11, BlockRenderer.W11, 1.0F); break;
/*     */         case NORTH: 
/* 342 */           func_149676_a(BlockRenderer.W5, BlockRenderer.W5, 0.0F, BlockRenderer.W11, BlockRenderer.W11, 0.5F);
/*     */         }
/* 344 */         super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */       }
/*     */     } else {
/* 347 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 348 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World w, int i, int j, int k, Random r)
/*     */   {
/* 355 */     if (r.nextInt(10) == 0) {
/* 356 */       TileEntity te = w.func_147438_o(i, j, k);
/* 357 */       if ((te != null) && ((te instanceof TileCrucible)) && 
/* 358 */         (((TileCrucible)te).tank.getFluidAmount() > 0) && (((TileCrucible)te).heat > 150)) {
/* 359 */         w.func_72980_b(i, j, k, "liquid.lavapop", 0.1F + r.nextFloat() * 0.1F, 1.2F + r.nextFloat() * 0.2F, false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_149692_a(int metadata)
/*     */   {
/* 368 */     if (metadata == 6) return 5;
/* 369 */     if ((metadata == 10) || (metadata == 11)) return 9;
/* 370 */     return metadata;
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 375 */     if (metadata == 0) return new TileCrucible();
/* 376 */     if (metadata == 5) return new TileGrate();
/* 377 */     if (metadata == 6) return new TileGrate();
/* 378 */     if (metadata == 1) return new TileAlembic();
/* 379 */     if (metadata == 7) return new TileArcaneLamp();
/* 380 */     if (metadata == 8) { return new TileArcaneLampGrowth();
/*     */     }
/* 382 */     if (metadata == 10) return new TileThaumatorium();
/* 383 */     if (metadata == 11) return new TileThaumatoriumTop();
/* 384 */     if (metadata == 12) { return new TileBrainbox();
/*     */     }
/* 386 */     if (metadata == 13) { return new TileArcaneLampFertility();
/*     */     }
/* 388 */     if (metadata == 14) return new TileVisRelay();
/* 389 */     if (metadata == 2) return new TileMagicWorkbenchCharger();
/* 390 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 395 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149736_g(World world, int x, int y, int z, int rs)
/*     */   {
/* 401 */     TileEntity te = world.func_147438_o(x, y, z);
/* 402 */     if ((te != null) && ((te instanceof TileThaumatorium))) {
/* 403 */       return Container.func_94526_b((IInventory)te);
/*     */     }
/* 405 */     if ((te != null) && ((te instanceof TileAlembic))) {
/* 406 */       float r = ((TileAlembic)te).amount / ((TileAlembic)te).maxAmount;
/* 407 */       return MathHelper.func_76141_d(r * 14.0F) + (((TileAlembic)te).amount > 0 ? 1 : 0);
/*     */     }
/* 409 */     if ((te != null) && ((te instanceof TileCrucible))) {
/* 410 */       ((TileCrucible)te).getClass();float r = ((TileCrucible)te).aspects.visSize() / 100.0F;
/* 411 */       return MathHelper.func_76141_d(r * 14.0F) + (((TileCrucible)te).aspects.visSize() > 0 ? 1 : 0);
/*     */     }
/* 413 */     return 0;
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 418 */     return null;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block nbid)
/*     */   {
/* 423 */     TileEntity te = world.func_147438_o(x, y, z);
/* 424 */     int md = world.func_72805_g(x, y, z);
/*     */     
/* 426 */     if ((te != null) && ((te instanceof TileCrucible))) {
/* 427 */       ((TileCrucible)te).getBellows();
/*     */     }
/*     */     
/* 430 */     if (!world.field_72995_K)
/*     */     {
/* 432 */       if ((te != null) && ((te instanceof TileAlembic))) {
/* 433 */         world.func_147471_g(x, y, z);
/*     */       }
/* 435 */       else if ((te != null) && ((te instanceof TileArcaneLamp))) {
/* 436 */         TileArcaneLamp telb = (TileArcaneLamp)te;
/* 437 */         if (world.func_147437_c(x + telb.facing.offsetX, y + telb.facing.offsetY, z + telb.facing.offsetZ))
/*     */         {
/*     */ 
/*     */ 
/* 441 */           func_149697_b(world, x, y, z, 7, 0);
/* 442 */           world.func_147468_f(x, y, z);
/*     */         }
/*     */       }
/* 445 */       else if ((te != null) && ((te instanceof TileArcaneLampGrowth))) {
/* 446 */         TileArcaneLampGrowth telb = (TileArcaneLampGrowth)te;
/* 447 */         if (world.func_147437_c(x + telb.facing.offsetX, y + telb.facing.offsetY, z + telb.facing.offsetZ))
/*     */         {
/*     */ 
/*     */ 
/* 451 */           func_149697_b(world, x, y, z, 8, 0);
/* 452 */           world.func_147468_f(x, y, z);
/*     */         }
/*     */       }
/* 455 */       else if ((te != null) && ((te instanceof TileBrainbox))) {
/* 456 */         TileBrainbox telb = (TileBrainbox)te;
/* 457 */         if (world.func_147437_c(x + telb.facing.offsetX, y + telb.facing.offsetY, z + telb.facing.offsetZ))
/*     */         {
/*     */ 
/*     */ 
/* 461 */           func_149697_b(world, x, y, z, 12, 0);
/* 462 */           world.func_147468_f(x, y, z);
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 472 */       else if ((te != null) && ((te instanceof TileVisRelay)) && (md == 14)) {
/* 473 */         TileVisRelay telb = (TileVisRelay)te;
/* 474 */         if (world.func_147437_c(x + ForgeDirection.getOrientation(telb.orientation).getOpposite().offsetX, y + ForgeDirection.getOrientation(telb.orientation).getOpposite().offsetY, z + ForgeDirection.getOrientation(telb.orientation).getOpposite().offsetZ))
/*     */         {
/*     */ 
/*     */ 
/* 478 */           func_149697_b(world, x, y, z, 14, 0);
/* 479 */           world.func_147468_f(x, y, z);
/*     */         }
/*     */       }
/* 482 */       else if (md == 10) {
/* 483 */         if ((world.func_147439_a(x, y + 1, z) != this) || (world.func_72805_g(x, y + 1, z) != 11) || (world.func_147439_a(x, y - 1, z) != this) || (world.func_72805_g(x, y - 1, z) != 0))
/*     */         {
/* 485 */           InventoryUtils.dropItems(world, x, y, z);
/* 486 */           world.func_147468_f(x, y, z);
/* 487 */           world.func_147465_d(x, y, z, this, 9, 3);
/* 488 */           return;
/*     */         }
/* 490 */         TileEntity tile = world.func_147438_o(x, y, z);
/* 491 */         if ((tile != null) && ((tile instanceof TileThaumatorium))) {
/* 492 */           ((TileThaumatorium)tile).getUpgrades();
/*     */         }
/*     */       }
/* 495 */       else if (md == 11) {
/* 496 */         if ((world.func_147439_a(x, y - 1, z) != this) || (world.func_72805_g(x, y - 1, z) != 10)) {
/* 497 */           world.func_147468_f(x, y, z);
/* 498 */           world.func_147465_d(x, y, z, this, 9, 3);
/* 499 */           return;
/*     */         }
/* 501 */         TileEntity tile = world.func_147438_o(x, y - 1, z);
/* 502 */         if ((tile != null) && ((tile instanceof TileThaumatorium))) {
/* 503 */           ((TileThaumatorium)tile).getUpgrades();
/*     */         }
/*     */       }
/*     */       
/* 507 */       boolean flag = world.func_72864_z(x, y, z);
/*     */       
/* 509 */       if ((flag) || (nbid.func_149744_f()))
/*     */       {
/* 511 */         onPoweredBlockChange(world, x, y, z, flag);
/*     */       }
/*     */     }
/*     */     
/* 515 */     super.func_149695_a(world, x, y, z, nbid);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/* 522 */     InventoryUtils.dropItems(par1World, par2, par3, par4);
/*     */     
/* 524 */     TileEntity te = par1World.func_147438_o(par2, par3, par4);
/* 525 */     if ((te != null) && ((te instanceof TileCrucible))) {
/* 526 */       ((TileCrucible)te).spillRemnants();
/*     */     }
/* 528 */     else if ((te != null) && ((te instanceof TileAlembic)) && (((TileAlembic)te).aspectFilter != null)) {
/* 529 */       par1World.func_72838_d(new EntityItem(par1World, par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, new ItemStack(ConfigItems.itemResource, 1, 13)));
/*     */ 
/*     */ 
/*     */     }
/* 533 */     else if ((te != null) && ((te instanceof TileArcaneLamp))) {
/* 534 */       ((TileArcaneLamp)te).removeLights();
/*     */     }
/*     */     
/* 537 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
/*     */   {
/* 558 */     int metadata = world.func_72805_g(x, y, z);
/*     */     
/* 560 */     if ((metadata == 0) && (!world.field_72995_K)) {
/* 561 */       FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(player.field_71071_by.func_70448_g());
/* 562 */       if ((fs != null) && (fs.isFluidEqual(new FluidStack(FluidRegistry.WATER, 1000)))) {
/* 563 */         int volume = fs.amount;
/* 564 */         TileEntity te = world.func_147438_o(x, y, z);
/* 565 */         if ((te != null) && ((te instanceof TileCrucible))) {
/* 566 */           TileCrucible tile = (TileCrucible)te;
/* 567 */           if (tile.tank.getFluidAmount() < tile.tank.getCapacity()) {
/* 568 */             tile.fill(ForgeDirection.UNKNOWN, FluidContainerRegistry.getFluidForFilledItem(player.field_71071_by.func_70448_g()), true);
/* 569 */             ItemStack emptyContainer = null;
/* 570 */             FluidContainerRegistry.FluidContainerData[] fcs = FluidContainerRegistry.getRegisteredFluidContainerData();
/* 571 */             for (FluidContainerRegistry.FluidContainerData fcd : fcs) {
/* 572 */               if (fcd.filledContainer.func_77969_a(player.field_71071_by.func_70448_g())) {
/* 573 */                 emptyContainer = fcd.emptyContainer.func_77946_l();
/*     */               }
/*     */             }
/*     */             
/* 577 */             player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
/* 578 */             if ((emptyContainer != null) && 
/* 579 */               (!player.field_71071_by.func_70441_a(emptyContainer))) {
/* 580 */               player.func_71019_a(emptyContainer, false);
/*     */             }
/*     */             
/* 583 */             player.field_71069_bz.func_75142_b();
/* 584 */             te.func_70296_d();
/* 585 */             world.func_147471_g(x, y, z);
/* 586 */             world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "game.neutral.swim", 0.33F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F);
/*     */           } else {
/* 588 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 594 */     if ((metadata == 1) && (!world.field_72995_K) && (!player.func_70093_af()) && (player.func_70694_bm() == null)) {
/* 595 */       TileEntity te = world.func_147438_o(x, y, z);
/* 596 */       if ((te != null) && ((te instanceof TileAlembic))) {
/* 597 */         TileAlembic tile = (TileAlembic)te;
/* 598 */         String msg = "";
/* 599 */         if ((tile.aspect == null) || (tile.amount == 0)) {
/* 600 */           msg = StatCollector.func_74838_a("tile.alembic.msg.1");
/* 601 */         } else if (tile.amount < tile.maxAmount * 0.4D) {
/* 602 */           msg = StatCollector.func_74838_a("tile.alembic.msg.2");
/* 603 */         } else if (tile.amount < tile.maxAmount * 0.8D) {
/* 604 */           msg = StatCollector.func_74838_a("tile.alembic.msg.3");
/* 605 */         } else if (tile.amount < tile.maxAmount) {
/* 606 */           msg = StatCollector.func_74838_a("tile.alembic.msg.4");
/* 607 */         } else if (tile.amount == tile.maxAmount) {
/* 608 */           msg = StatCollector.func_74838_a("tile.alembic.msg.5");
/*     */         }
/*     */         
/* 611 */         player.func_145747_a(new ChatComponentTranslation("§3" + msg, new Object[0]));
/* 612 */         world.func_72908_a(x, y, z, "thaumcraft:alembicknock", 0.2F, 1.0F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 618 */     if (metadata == 1) {
/* 619 */       TileEntity te = world.func_147438_o(x, y, z);
/* 620 */       if ((te != null) && ((te instanceof TileAlembic))) {
/* 621 */         if ((player.func_70093_af()) && (((TileAlembic)te).aspectFilter != null)) {
/* 622 */           ((TileAlembic)te).aspectFilter = null;
/* 623 */           world.func_147471_g(x, y, z);
/* 624 */           te.func_70296_d();
/* 625 */           if (world.field_72995_K) {
/* 626 */             world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:page", 1.0F, 1.1F, false);
/*     */           } else {
/* 628 */             ForgeDirection fd = ForgeDirection.getOrientation(side);
/* 629 */             world.func_72838_d(new EntityItem(world, x + 0.5F + fd.offsetX / 3.0F, y + 0.5F, z + 0.5F + fd.offsetZ / 3.0F, new ItemStack(ConfigItems.itemResource, 1, 13)));
/*     */           }
/*     */           
/* 632 */           return true;
/*     */         }
/*     */         
/* 635 */         if ((player.func_70093_af()) && (player.func_70694_bm() == null)) {
/* 636 */           ((TileAlembic)te).amount = 0;
/* 637 */           ((TileAlembic)te).aspect = null;
/* 638 */           if (world.field_72995_K) {
/* 639 */             world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:alembicknock", 0.2F, 1.0F, false);
/* 640 */             world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "game.neutral.swim", 0.5F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F, false);
/*     */           }
/*     */         }
/*     */         else {
/* 644 */           if ((player.func_70694_bm() != null) && (((TileAlembic)te).aspectFilter == null) && (player.func_70694_bm().func_77973_b() == ConfigItems.itemResource) && (player.func_70694_bm().func_77960_j() == 13))
/*     */           {
/*     */ 
/*     */ 
/* 648 */             if ((((TileAlembic)te).amount == 0) && (((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) == null)) {
/* 649 */               return true;
/*     */             }
/*     */             
/* 652 */             if ((((TileAlembic)te).amount == 0) && (((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) != null)) {
/* 653 */               ((TileAlembic)te).aspect = ((IEssentiaContainerItem)(IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()).getAspects()[0];
/*     */             }
/*     */             
/* 656 */             player.func_70694_bm().field_77994_a -= 1;
/* 657 */             ((TileAlembic)te).aspectFilter = ((TileAlembic)te).aspect;
/* 658 */             world.func_147471_g(x, y, z);
/* 659 */             te.func_70296_d();
/* 660 */             if (world.field_72995_K) {
/* 661 */               world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:page", 1.0F, 0.9F, false);
/*     */             }
/* 663 */             return true;
/*     */           }
/*     */           
/* 666 */           if ((player.func_70694_bm() != null) && (((TileAlembic)te).amount > 0) && ((player.func_70694_bm().func_77973_b() == ConfigItems.itemJarFilled) || (player.func_70694_bm().func_77969_a(new ItemStack(ConfigBlocks.blockJar, 1, 0))) || (player.func_70694_bm().func_77969_a(new ItemStack(ConfigBlocks.blockJar, 1, 3)))))
/*     */           {
/*     */ 
/*     */ 
/* 670 */             boolean doit = false;
/* 671 */             ItemStack drop = null;
/* 672 */             if ((player.func_70694_bm().func_77969_a(new ItemStack(ConfigBlocks.blockJar, 1, 0))) || (player.func_70694_bm().func_77969_a(new ItemStack(ConfigBlocks.blockJar, 1, 3))))
/*     */             {
/* 674 */               drop = new ItemStack(ConfigItems.itemJarFilled, 1, player.func_70694_bm().func_77960_j());
/* 675 */               doit = true;
/* 676 */               ((ItemJarFilled)drop.func_77973_b()).setAspects(drop, new AspectList().add(((TileAlembic)te).aspect, ((TileAlembic)te).amount));
/* 677 */               ((TileAlembic)te).amount = 0;
/* 678 */               ((TileAlembic)te).aspect = null;
/* 679 */               player.func_70694_bm().field_77994_a -= 1;
/* 680 */               if ((!player.field_71071_by.func_70441_a(drop)) && 
/* 681 */                 (!world.field_72995_K)) world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, drop));
/*     */             }
/*     */             else {
/* 684 */               drop = player.func_70694_bm();
/* 685 */               if (((((ItemJarFilled)drop.func_77973_b()).getAspects(drop) == null) || (((ItemJarFilled)drop.func_77973_b()).getAspects(drop).visSize() == 0) || (((ItemJarFilled)drop.func_77973_b()).getAspects(drop).getAmount(((TileAlembic)te).aspect) > 0)) && ((((ItemJarFilled)drop.func_77973_b()).getFilter(drop) == null) || (((ItemJarFilled)drop.func_77973_b()).getFilter(drop) == ((TileAlembic)te).aspect)))
/*     */               {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 691 */                 int amount = Math.min(((ItemJarFilled)drop.func_77973_b()).getAspects(drop) == null ? 64 : 64 - ((ItemJarFilled)drop.func_77973_b()).getAspects(drop).visSize(), ((TileAlembic)te).amount);
/*     */                 
/*     */ 
/*     */ 
/* 695 */                 if (drop.func_77960_j() == 3) amount = ((TileAlembic)te).amount;
/* 696 */                 if (amount > 0) {
/* 697 */                   ((TileAlembic)te).amount -= amount;
/* 698 */                   AspectList as = ((ItemJarFilled)drop.func_77973_b()).getAspects(drop);
/* 699 */                   if (as == null) as = new AspectList();
/* 700 */                   as.add(((TileAlembic)te).aspect, amount);
/* 701 */                   if (as.getAmount(((TileAlembic)te).aspect) > 64) {
/* 702 */                     int q = as.getAmount(((TileAlembic)te).aspect) - 64;
/* 703 */                     as.reduce(((TileAlembic)te).aspect, q);
/*     */                   }
/* 705 */                   ((ItemJarFilled)drop.func_77973_b()).setAspects(drop, as);
/* 706 */                   if (((TileAlembic)te).amount <= 0) {
/* 707 */                     ((TileAlembic)te).aspect = null;
/*     */                   }
/* 709 */                   doit = true;
/* 710 */                   player.func_70062_b(0, drop);
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/* 715 */             if (doit) {
/* 716 */               te.func_70296_d();
/* 717 */               world.func_147471_g(x, y, z);
/* 718 */               if (world.field_72995_K) {
/* 719 */                 world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "game.neutral.swim", 0.5F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F, false);
/*     */               }
/*     */             }
/* 722 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 727 */     if (metadata == 5) {
/* 728 */       world.func_72921_c(x, y, z, 6, 2);
/* 729 */       world.func_72889_a(player, 1003, x, y, z, 0);
/* 730 */       return true;
/*     */     }
/* 732 */     if (metadata == 6) {
/* 733 */       world.func_72921_c(x, y, z, 5, 2);
/* 734 */       world.func_72889_a(player, 1003, x, y, z, 0);
/* 735 */       return true;
/*     */     }
/*     */     
/* 738 */     if (world.field_72995_K) { return true;
/*     */     }
/* 740 */     if (metadata == 10) {
/* 741 */       TileEntity te = world.func_147438_o(x, y, z);
/* 742 */       if (((te instanceof TileThaumatorium)) && (!player.func_70093_af())) {
/* 743 */         player.openGui(Thaumcraft.instance, 3, world, x, y, z);
/* 744 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 748 */     if (metadata == 11) {
/* 749 */       TileEntity te = world.func_147438_o(x, y - 1, z);
/* 750 */       if (((te instanceof TileThaumatorium)) && (!player.func_70093_af())) {
/* 751 */         player.openGui(Thaumcraft.instance, 3, world, x, y - 1, z);
/* 752 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 756 */     if (((metadata == 14) || (metadata == 2)) && (!world.field_72995_K) && (!player.func_70093_af()) && (player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof ItemShard)))
/*     */     {
/* 758 */       TileEntity te = world.func_147438_o(x, y, z);
/* 759 */       if ((te != null) && ((te instanceof TileVisRelay))) {
/* 760 */         TileVisRelay tile = (TileVisRelay)te;
/* 761 */         byte c = (byte)player.func_70694_bm().func_77960_j();
/* 762 */         if ((c == tile.color) || (c == 6)) {
/* 763 */           tile.color = -1;
/*     */         } else {
/* 765 */           tile.color = c;
/*     */         }
/* 767 */         tile.removeThisNode();
/* 768 */         tile.nodeRefresh = true;
/* 769 */         tile.func_70296_d();
/* 770 */         world.func_147471_g(x, y, z);
/* 771 */         world.func_72908_a(x, y, z, "thaumcraft:crystal", 0.2F, 1.0F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 776 */     return super.func_149727_a(world, x, y, z, player, side, par7, par8, par9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onPoweredBlockChange(World par1World, int par2, int par3, int par4, boolean flag)
/*     */   {
/* 783 */     int l = par1World.func_72805_g(par2, par3, par4);
/*     */     
/* 785 */     if ((l == 5) && (flag))
/*     */     {
/* 787 */       par1World.func_72921_c(par2, par3, par4, 6, 2);
/* 788 */       par1World.func_72889_a((EntityPlayer)null, 1003, par2, par3, par4, 0);
/*     */     }
/* 790 */     else if ((l == 6) && (!flag))
/*     */     {
/* 792 */       par1World.func_72921_c(par2, par3, par4, 5, 2);
/* 793 */       par1World.func_72889_a((EntityPlayer)null, 1003, par2, par3, par4, 0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149689_a(World world, int par2, int par3, int par4, EntityLivingBase ent, ItemStack stack)
/*     */   {
/* 800 */     int l = MathHelper.func_76128_c(ent.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/* 801 */     if (stack.func_77960_j() == 1) {
/* 802 */       TileEntity tile = world.func_147438_o(par2, par3, par4);
/* 803 */       if ((tile instanceof TileAlembic)) {
/* 804 */         if (l == 0)
/*     */         {
/* 806 */           ((TileAlembic)tile).facing = 2;
/*     */         }
/*     */         
/* 809 */         if (l == 1)
/*     */         {
/* 811 */           ((TileAlembic)tile).facing = 5;
/*     */         }
/*     */         
/* 814 */         if (l == 2)
/*     */         {
/* 816 */           ((TileAlembic)tile).facing = 3;
/*     */         }
/*     */         
/* 819 */         if (l == 3)
/*     */         {
/* 821 */           ((TileAlembic)tile).facing = 4;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 829 */     int md = world.func_72805_g(x, y, z);
/* 830 */     if (md == 3) return 11;
/* 831 */     if (md == 7) return 15;
/* 832 */     if (md == 8) {
/* 833 */       TileEntity te = world.func_147438_o(x, y, z);
/* 834 */       if ((te != null) && ((te instanceof TileArcaneLampGrowth))) {
/* 835 */         if (((TileArcaneLampGrowth)te).charges > 0) {
/* 836 */           return 15;
/*     */         }
/* 838 */         return 8;
/*     */       }
/*     */       
/*     */     }
/* 842 */     else if (md == 13) {
/* 843 */       TileEntity te = world.func_147438_o(x, y, z);
/* 844 */       if ((te != null) && ((te instanceof TileArcaneLampFertility))) {
/* 845 */         if (((TileArcaneLampFertility)te).charges > 0) {
/* 846 */           return 15;
/*     */         }
/* 848 */         return 8;
/*     */       }
/*     */       
/*     */     }
/* 852 */     else if (md == 14) {
/* 853 */       TileEntity te = world.func_147438_o(x, y, z);
/* 854 */       if ((te != null) && ((te instanceof TileVisRelay))) {
/* 855 */         if (VisNetHandler.isNodeValid(((TileVisRelay)te).getParent())) {
/* 856 */           return 10;
/*     */         }
/* 858 */         return 2;
/*     */       }
/*     */     }
/*     */     
/* 862 */     return super.getLightValue(world, x, y, z);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockMetalDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */