/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLeaves;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.ColorizerFoliage;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ 
/*     */ public class BlockMagicalLeaves
/*     */   extends Block
/*     */   implements IShearable
/*     */ {
/*  34 */   public static final String[] leafType = { "greatwood", "silverwood" };
/*     */   int[] adjacentTreeBlocks;
/*     */   
/*     */   public BlockMagicalLeaves()
/*     */   {
/*  39 */     super(Material.field_151584_j);
/*  40 */     func_149675_a(true);
/*  41 */     func_149647_a(Thaumcraft.tabTC);
/*  42 */     func_149711_c(0.2F);
/*  43 */     func_149713_g(1);
/*  44 */     func_149672_a(field_149779_h);
/*     */   }
/*     */   
/*     */ 
/*  48 */   public IIcon[] icon = new IIcon[4];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir) {
/*  52 */     this.icon[0] = ir.func_94245_a("thaumcraft:greatwoodleaves");
/*  53 */     this.icon[1] = ir.func_94245_a("thaumcraft:greatwoodleaveslow");
/*  54 */     this.icon[2] = ir.func_94245_a("thaumcraft:silverwoodleaves");
/*  55 */     this.icon[3] = ir.func_94245_a("thaumcraft:silverwoodleaveslow");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/*  64 */     int idx = !Blocks.field_150362_t.func_149662_c() ? 0 : 1;
/*  65 */     return (par2 & 0x1) == 1 ? this.icon[(idx + 2)] : this.icon[idx];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  76 */     par3List.add(new ItemStack(par1, 1, 0));
/*  77 */     par3List.add(new ItemStack(par1, 1, 1));
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
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/*  91 */     Block var6 = par1IBlockAccess.func_147439_a(par2, par3, par4);
/*  92 */     return (Blocks.field_150362_t.func_149662_c()) && (var6 == this) ? false : super.func_149646_a(par1IBlockAccess, par2, par3, par4, par5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149635_D()
/*     */   {
/* 100 */     double var1 = 0.5D;
/* 101 */     double var3 = 1.0D;
/* 102 */     return ColorizerFoliage.func_77470_a(var1, var3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149741_i(int par1)
/*     */   {
/* 112 */     return (par1 & 0x1) == 0 ? ColorizerFoliage.func_77468_c() : 8952234;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 124 */     int var5 = par1IBlockAccess.func_72805_g(par2, par3, par4);
/*     */     
/* 126 */     if ((var5 & 0x1) == 1)
/*     */     {
/* 128 */       return 8952234;
/*     */     }
/*     */     
/*     */ 
/* 132 */     int var6 = 0;
/* 133 */     int var7 = 0;
/* 134 */     int var8 = 0;
/*     */     
/* 136 */     for (int var9 = -1; var9 <= 1; var9++)
/*     */     {
/* 138 */       for (int var10 = -1; var10 <= 1; var10++)
/*     */       {
/* 140 */         int var11 = par1IBlockAccess.func_72807_a(par2 + var10, par4 + var9).func_150571_c(par2, par3, par4);
/* 141 */         var6 += ((var11 & 0xFF0000) >> 16);
/* 142 */         var7 += ((var11 & 0xFF00) >> 8);
/* 143 */         var8 += (var11 & 0xFF);
/*     */       }
/*     */     }
/*     */     
/* 147 */     return (var6 / 9 & 0xFF) << 16 | (var7 / 9 & 0xFF) << 8 | var8 / 9 & 0xFF;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 153 */     if ((world.func_72805_g(x, y, z) & 0x1) == 1) { return 7;
/*     */     }
/* 155 */     return super.getLightValue(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/* 164 */     byte var7 = 1;
/* 165 */     int var8 = var7 + 1;
/*     */     
/* 167 */     if (par1World.func_72904_c(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
/*     */     {
/* 169 */       for (int var9 = -var7; var9 <= var7; var9++)
/*     */       {
/* 171 */         for (int var10 = -var7; var10 <= var7; var10++)
/*     */         {
/* 173 */           for (int var11 = -var7; var11 <= var7; var11++)
/*     */           {
/* 175 */             Block var12 = par1World.func_147439_a(par2 + var9, par3 + var10, par4 + var11);
/*     */             
/* 177 */             if (var12 != Blocks.field_150350_a)
/*     */             {
/* 179 */               var12.beginLeavesDecay(par1World, par2 + var9, par3 + var10, par4 + var11);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/* 193 */     if (!par1World.field_72995_K)
/*     */     {
/* 195 */       int var6 = par1World.func_72805_g(par2, par3, par4);
/*     */       
/* 197 */       if (((var6 & 0x8) != 0) && ((var6 & 0x4) == 0))
/*     */       {
/* 199 */         byte var7 = 4;
/* 200 */         int var8 = var7 + 1;
/* 201 */         byte var9 = 32;
/* 202 */         int var10 = var9 * var9;
/* 203 */         int var11 = var9 / 2;
/*     */         
/* 205 */         if (this.adjacentTreeBlocks == null)
/*     */         {
/* 207 */           this.adjacentTreeBlocks = new int[var9 * var9 * var9];
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 212 */         if (par1World.func_72904_c(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 218 */           for (int var12 = -var7; var12 <= var7; var12++)
/*     */           {
/* 220 */             for (int var13 = -var7; var13 <= var7; var13++)
/*     */             {
/* 222 */               for (int var14 = -var7; var14 <= var7; var14++)
/*     */               {
/* 224 */                 Block block = par1World.func_147439_a(par2 + var12, par3 + var13, par4 + var14);
/*     */                 
/* 226 */                 if ((block != null) && (block.canSustainLeaves(par1World, par2 + var12, par3 + var13, par4 + var14)))
/*     */                 {
/* 228 */                   this.adjacentTreeBlocks[((var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11)] = 0;
/*     */                 }
/* 230 */                 else if ((block != null) && (block.isLeaves(par1World, par2 + var12, par3 + var13, par4 + var14)))
/*     */                 {
/* 232 */                   this.adjacentTreeBlocks[((var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11)] = -2;
/*     */                 }
/*     */                 else
/*     */                 {
/* 236 */                   this.adjacentTreeBlocks[((var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11)] = -1;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/* 241 */           int var15 = 0;
/* 242 */           for (var12 = 1; var12 <= 4; var12++)
/*     */           {
/* 244 */             for (int var13 = -var7; var13 <= var7; var13++)
/*     */             {
/* 246 */               for (int var14 = -var7; var14 <= var7; var14++)
/*     */               {
/* 248 */                 for (var15 = -var7; var15 <= var7; var15++)
/*     */                 {
/* 250 */                   if (this.adjacentTreeBlocks[((var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11)] == var12 - 1)
/*     */                   {
/* 252 */                     if (this.adjacentTreeBlocks[((var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11)] == -2)
/*     */                     {
/* 254 */                       this.adjacentTreeBlocks[((var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11)] = var12;
/*     */                     }
/*     */                     
/* 257 */                     if (this.adjacentTreeBlocks[((var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11)] == -2)
/*     */                     {
/* 259 */                       this.adjacentTreeBlocks[((var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11)] = var12;
/*     */                     }
/*     */                     
/* 262 */                     if (this.adjacentTreeBlocks[((var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11)] == -2)
/*     */                     {
/* 264 */                       this.adjacentTreeBlocks[((var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11)] = var12;
/*     */                     }
/*     */                     
/* 267 */                     if (this.adjacentTreeBlocks[((var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11)] == -2)
/*     */                     {
/* 269 */                       this.adjacentTreeBlocks[((var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11)] = var12;
/*     */                     }
/*     */                     
/* 272 */                     if (this.adjacentTreeBlocks[((var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1))] == -2)
/*     */                     {
/* 274 */                       this.adjacentTreeBlocks[((var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1))] = var12;
/*     */                     }
/*     */                     
/* 277 */                     if (this.adjacentTreeBlocks[((var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1)] == -2)
/*     */                     {
/* 279 */                       this.adjacentTreeBlocks[((var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1)] = var12;
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 288 */         int var12 = this.adjacentTreeBlocks[(var11 * var10 + var11 * var9 + var11)];
/*     */         
/* 290 */         if (var12 >= 0)
/*     */         {
/* 292 */           par1World.func_147465_d(par2, par3, par4, this, var6 & 0xFFFFFFF7, 3);
/*     */         }
/*     */         else
/*     */         {
/* 296 */           removeLeaves(par1World, par2, par3, par4);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/* 309 */     if ((par1World.func_72951_B(par2, par3 + 1, par4)) && (!World.func_147466_a(par1World, par2, par3 - 1, par4)) && (par5Random.nextInt(15) == 1))
/*     */     {
/* 311 */       double var6 = par2 + par5Random.nextFloat();
/* 312 */       double var8 = par3 - 0.05D;
/* 313 */       double var10 = par4 + par5Random.nextFloat();
/* 314 */       par1World.func_72869_a("dripWater", var6, var8, var10, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/* 317 */     int md = par1World.func_72805_g(par2, par3, par4);
/* 318 */     if (((md & 0x1) == 1) && (par5Random.nextInt(500) == 0)) {
/* 319 */       Thaumcraft.proxy.sparkle(par2 + 0.5F + par1World.field_73012_v.nextFloat() - par1World.field_73012_v.nextFloat(), par3 + 0.5F + par1World.field_73012_v.nextFloat() - par1World.field_73012_v.nextFloat(), par4 + 0.5F + par1World.field_73012_v.nextFloat() - par1World.field_73012_v.nextFloat(), 2.0F, 7, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void removeLeaves(World par1World, int par2, int par3, int par4)
/*     */   {
/* 330 */     func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
/* 331 */     par1World.func_147468_f(par2, par3, par4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149690_a(World par1World, int par2, int par3, int par4, int meta, float par6, int par7)
/*     */   {
/* 340 */     if ((!par1World.field_72995_K) && ((meta & 0x8) != 0) && ((meta & 0x4) == 0))
/*     */     {
/* 342 */       if (((meta & 0x1) == 0) && (par1World.field_73012_v.nextInt(200) == 0))
/*     */       {
/* 344 */         func_149642_a(par1World, par2, par3, par4, new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0));
/*     */ 
/*     */       }
/* 347 */       else if (((meta & 0x1) == 1) && (par1World.field_73012_v.nextInt(250) == 0))
/*     */       {
/* 349 */         func_149642_a(par1World, par2, par3, par4, new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1));
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
/*     */   public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
/*     */   {
/* 363 */     super.func_149636_a(par1World, par2EntityPlayer, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 372 */     return par1 & 0x1;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149745_a(Random par1Random)
/*     */   {
/* 378 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3)
/*     */   {
/* 384 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 394 */     return Blocks.field_150362_t.func_149662_c();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
/*     */   {
/* 401 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
/*     */   {
/* 407 */     ArrayList<ItemStack> ret = new ArrayList();
/* 408 */     ret.add(new ItemStack(this, 1, world.func_72805_g(x, y, z) & 0x3));
/*     */     
/* 410 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */   public void beginLeavesDecay(World world, int x, int y, int z)
/*     */   {
/* 416 */     world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) | 0x8, 4);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isLeaves(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 422 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 428 */     int md = world.func_72805_g(x, y, z);
/* 429 */     return new ItemStack(this, 1, md & 0x1);
/*     */   }
/*     */   
/*     */   public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 434 */     return 60;
/*     */   }
/*     */   
/*     */   public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 439 */     return 30;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockMagicalLeaves.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */