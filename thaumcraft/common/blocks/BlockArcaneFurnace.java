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
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.tiles.TileArcaneFurnace;
/*     */ import thaumcraft.common.tiles.TileArcaneFurnaceNozzle;
/*     */ 
/*     */ public class BlockArcaneFurnace extends BlockContainer
/*     */ {
/*     */   public BlockArcaneFurnace()
/*     */   {
/*  37 */     super(Material.field_151576_e);
/*  38 */     func_149711_c(10.0F);
/*  39 */     func_149752_b(500.0F);
/*  40 */     func_149715_a(0.2F);
/*  41 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*  44 */   public IIcon[] icon = new IIcon[27];
/*     */   
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  48 */     for (int a = 0; a < 27; a++) {
/*  49 */       if ((a != 8) && (a != 24)) {
/*  50 */         this.icon[a] = ir.func_94245_a("thaumcraft:furnace" + a);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
/*  57 */     return calculateTexture(world, x, y, z, side);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon calculateTexture(IBlockAccess world, int x, int y, int z, int side) {
/*  62 */     int meta = world.func_72805_g(x, y, z);
/*  63 */     int level = calculateLevel(world, x, y, z);
/*  64 */     int add = 0;
/*  65 */     if (BlockUtils.isBlockTouchingOnSide(world, x, y, z, this, 10, side)) { add = 3;
/*     */     }
/*  67 */     switch (side) {
/*     */     case 0: case 1: 
/*  69 */       if ((side == 1) && (level == 18)) {
/*  70 */         switch (meta) {
/*  71 */         case 2:  return this.icon[16];
/*  72 */         case 4:  return this.icon[17];
/*  73 */         case 6:  return this.icon[26];
/*  74 */         case 8:  return this.icon[25];
/*     */         }
/*     */       }
/*  77 */       if (add != 3) {
/*  78 */         if (meta == 5) return this.icon[10];
/*  79 */         if ((meta - 1) % 3 + (meta - 1) / 3 * 9 < 0) return null;
/*  80 */         return this.icon[((meta - 1) % 3 + (meta - 1) / 3 * 9)];
/*     */       }
/*     */       break;
/*  83 */     case 2:  switch (meta) {
/*  84 */       default:  if (level != 9) return this.icon[7]; return this.icon[6];
/*  85 */       case 1:  return this.icon[(2 + level + add)];
/*  86 */       case 2:  return this.icon[(1 + level + add)]; }
/*  87 */       return this.icon[(0 + level + add)];
/*     */     
/*     */     case 3: 
/*  90 */       switch (meta) {
/*  91 */       default:  if (level != 9) return this.icon[7]; return this.icon[6];
/*  92 */       case 7:  return this.icon[(0 + level + add)];
/*  93 */       case 8:  return this.icon[(1 + level + add)]; }
/*  94 */       return this.icon[(2 + level + add)];
/*     */     
/*     */     case 4: 
/*  97 */       switch (meta) {
/*  98 */       default:  if (level != 9) return this.icon[7]; return this.icon[6];
/*  99 */       case 1:  return this.icon[(0 + level + add)];
/* 100 */       case 4:  return this.icon[(1 + level + add)]; }
/* 101 */       return this.icon[(2 + level + add)];
/*     */     
/*     */     case 5: 
/* 104 */       switch (meta) {
/* 105 */       default:  if (level != 9) return this.icon[7]; return this.icon[6];
/* 106 */       case 3:  return this.icon[(2 + level + add)];
/* 107 */       case 6:  return this.icon[(1 + level + add)]; }
/* 108 */       return this.icon[(0 + level + add)];
/*     */     }
/*     */     
/* 111 */     return add == 0 ? this.icon[7] : this.icon[6];
/*     */   }
/*     */   
/*     */   public int calculateLevel(IBlockAccess world, int x, int y, int z) {
/* 115 */     int meta = world.func_72805_g(x, y, z);
/* 116 */     int metaA = world.func_72805_g(x, y + 1, z);
/* 117 */     if ((metaA == 10) || (metaA == 0)) metaA = meta;
/* 118 */     int metaB = world.func_72805_g(x, y - 1, z);
/* 119 */     if ((metaB == 10) || (metaB == 0)) metaB = meta;
/* 120 */     Block blockA = world.func_147439_a(x, y + 1, z);
/* 121 */     Block blockB = world.func_147439_a(x, y - 1, z);
/* 122 */     if ((meta == metaA) && (meta == metaB) && (this == blockA) && (this == blockB))
/* 123 */       return 9;
/* 124 */     if ((meta == metaA) && (this == blockA) && ((meta != metaB) || (this != blockB)))
/* 125 */       return 18;
/* 126 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 132 */     int meta = world.func_72805_g(x, y, z);
/* 133 */     if ((meta == 0) || (meta == 10)) return 13;
/* 134 */     return super.getLightValue(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149719_a(IBlockAccess world, int i, int j, int k)
/*     */   {
/* 140 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AxisAlignedBB func_149633_g(World w, int i, int j, int k)
/*     */   {
/* 147 */     int meta = w.func_72805_g(i, j, k);
/* 148 */     if (meta == 0) return AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
/* 149 */     return AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
/*     */   {
/* 155 */     int md = world.func_72805_g(i, j, k);
/* 156 */     if (md == 10) {
/* 157 */       if ((world.func_147439_a(i - 1, j, k) == this) && (world.func_72805_g(i - 1, j, k) == 0))
/*     */       {
/*     */ 
/* 160 */         func_149676_a(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
/*     */       }
/* 162 */       else if ((world.func_147439_a(i + 1, j, k) == this) && (world.func_72805_g(i + 1, j, k) == 0))
/*     */       {
/*     */ 
/* 165 */         func_149676_a(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */       }
/* 167 */       else if ((world.func_147439_a(i, j, k - 1) == this) && (world.func_72805_g(i, j, k - 1) == 0))
/*     */       {
/*     */ 
/* 170 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
/*     */       }
/*     */       else {
/* 173 */         func_149676_a(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
/*     */       }
/* 175 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     }
/* 177 */     else if (md != 0) {
/* 178 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 179 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     } else {
/* 181 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
/* 182 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 189 */     int meta = world.func_72805_g(x, y, z);
/* 190 */     if (meta == 0) {
/* 191 */       if (entity.field_70165_t < x + 0.3F) entity.field_70159_w += 9.999999747378752E-5D;
/* 192 */       if (entity.field_70165_t > x + 0.7F) entity.field_70159_w -= 9.999999747378752E-5D;
/* 193 */       if (entity.field_70161_v < z + 0.3F) entity.field_70179_y += 9.999999747378752E-5D;
/* 194 */       if (entity.field_70161_v > z + 0.7F) entity.field_70179_y -= 9.999999747378752E-5D;
/* 195 */       if ((entity instanceof EntityItem)) {
/* 196 */         entity.field_70181_x = 0.02500000037252903D;
/* 197 */         if (entity.field_70122_E) {
/* 198 */           TileArcaneFurnace taf = (TileArcaneFurnace)world.func_147438_o(x, y, z);
/* 199 */           if (taf.addItemsToInventory(((EntityItem)entity).func_92059_d())) {
/* 200 */             entity.func_70106_y();
/*     */           }
/*     */         }
/*     */       }
/* 204 */       else if (((entity instanceof EntityLivingBase)) && 
/* 205 */         (!entity.func_70045_F()))
/*     */       {
/* 207 */         entity.func_70097_a(DamageSource.field_76371_c, 3.0F);
/* 208 */         entity.func_70015_d(10);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void restoreBlocks(World par1World, int par2, int par3, int par4)
/*     */   {
/* 217 */     for (int yy = -1; yy <= 1; yy++) {
/* 218 */       for (int xx = -1; xx <= 1; xx++) {
/* 219 */         for (int zz = -1; zz <= 1; zz++) {
/* 220 */           Block block = par1World.func_147439_a(par2 + xx, par3 + yy, par4 + zz);
/* 221 */           int md = par1World.func_72805_g(par2 + xx, par3 + yy, par4 + zz);
/* 222 */           if (block == this) {
/* 223 */             block = Block.func_149634_a(func_149650_a(md, new Random(), 0));
/* 224 */             par1World.func_147465_d(par2 + xx, par3 + yy, par4 + zz, block, 0, 3);
/* 225 */             par1World.func_147459_d(par2 + xx, par3 + yy, par4 + zz, par1World.func_147439_a(par2 + xx, par3 + yy, par4 + zz));
/* 226 */             par1World.func_147471_g(par2 + xx, par3 + yy, par4 + zz);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/* 236 */     int meta = par1World.func_72805_g(par2, par3, par4);
/* 237 */     if (meta == 0) {
/* 238 */       for (int yy = -1; yy <= 1; yy++) {
/* 239 */         for (int xx = -1; xx <= 1; xx++) {
/* 240 */           for (int zz = -1; zz <= 1; zz++) {
/* 241 */             if (((yy == 1) || (yy == 0)) && (zz == 0) && (xx == 0)) break;
/* 242 */             Block block = par1World.func_147439_a(par2 + xx, par3 + yy, par4 + zz);
/* 243 */             if (block != this) {
/* 244 */               restoreBlocks(par1World, par2, par3, par4);
/* 245 */               par1World.func_147468_f(par2, par3, par4);
/* 246 */               par1World.func_147459_d(par2, par3, par4, par1World.func_147439_a(par2, par3, par4));
/* 247 */               par1World.func_147471_g(par2, par3, par4);
/* 248 */               return;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 254 */     super.func_149695_a(par1World, par2, par3, par4, par5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149725_f(World world, int x, int y, int z, int meta)
/*     */   {
/* 261 */     if ((meta == 0) && (!world.field_72995_K)) {
/* 262 */       TileEntity te = world.func_147438_o(x, y, z);
/* 263 */       if ((te != null) && ((te instanceof TileArcaneFurnace))) {
/* 264 */         Entity newentity = EntityList.func_75620_a("Blaze", world);
/* 265 */         newentity.func_70012_b(x + 0.5F, y + 1.0F, z + 0.5F, 0.0F, 0.0F);
/* 266 */         ((EntityLivingBase)newentity).func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 6000, 2));
/* 267 */         ((EntityLivingBase)newentity).func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 12000, 0));
/* 268 */         world.func_72838_d(newentity);
/*     */       }
/*     */     }
/* 271 */     super.func_149725_f(world, x, y, z, meta);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/* 277 */     if (par1World.func_72805_g(par2, par3, par4) == 0)
/*     */     {
/* 279 */       restoreBlocks(par1World, par2, par3, par4);
/*     */     }
/*     */     
/* 282 */     for (int yy = -1; yy <= 1; yy++) {
/* 283 */       for (int xx = -1; xx <= 1; xx++)
/* 284 */         for (int zz = -1; zz <= 1; zz++)
/* 285 */           par1World.func_147459_d(par2 + xx, par3 + yy, par4 + zz, this);
/*     */     }
/* 287 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Item func_149650_a(int meta, Random par2Random, int par3)
/*     */   {
/* 296 */     return (meta % 2 == 0) || (meta == 5) ? Item.func_150898_a(Blocks.field_150343_Z) : meta == 10 ? Item.func_150898_a(Blocks.field_150411_aY) : meta == 0 ? Item.func_150899_d(0) : Item.func_150898_a(Blocks.field_150385_bj);
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/* 301 */     if (world.func_72805_g(x, y, z) == 0) return false;
/* 302 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/* 308 */     return ConfigBlocks.blockArcaneFurnaceRI;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 313 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 319 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/* 327 */     if ((par1World.func_72805_g(par2, par3, par4) == 0) && (par1World.func_147439_a(par2, par3 + 1, par4).func_149688_o() == Material.field_151579_a) && (!par1World.func_147439_a(par2, par3 + 1, par4).func_149662_c()))
/*     */     {
/*     */ 
/*     */ 
/* 331 */       for (int a = 0; a < 3; a++) {
/* 332 */         double var7 = par2 + par5Random.nextFloat();
/* 333 */         double var8 = par3 + 1.0F + par5Random.nextFloat() * 0.5F;
/* 334 */         double var9 = par4 + par5Random.nextFloat();
/* 335 */         par1World.func_72869_a("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 344 */     if (metadata == 0) return new TileArcaneFurnace();
/* 345 */     if ((metadata == 2) || (metadata == 4) || (metadata == 5) || (metadata == 6) || (metadata == 8)) return new TileArcaneFurnaceNozzle();
/* 346 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 351 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149696_a(World par1World, int par2, int par3, int par4, int par5, int par6)
/*     */   {
/* 358 */     if (par5 == 1)
/*     */     {
/* 360 */       if (par1World.field_72995_K) Thaumcraft.proxy.blockSparkle(par1World, par2, par3, par4, 16736256, 5);
/* 361 */       return true;
/*     */     }
/*     */     
/* 364 */     return super.func_149696_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockArcaneFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */