/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.ColorizerGrass;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.entities.monster.EntityTaintSpore;
/*     */ import thaumcraft.common.lib.CustomSoundType;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class BlockTaintFibres
/*     */   extends Block
/*     */ {
/*     */   public BlockTaintFibres()
/*     */   {
/*  40 */     super(Config.taintMaterial);
/*  41 */     func_149711_c(1.0F);
/*  42 */     func_149752_b(5.0F);
/*  43 */     func_149672_a(new CustomSoundType("gore", 0.5F, 0.8F));
/*  44 */     func_149675_a(true);
/*  45 */     func_149647_a(Thaumcraft.tabTC);
/*  46 */     float f = 0.2F;
/*  47 */     func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
/*  48 */     this.currentPass = 1;
/*     */   }
/*     */   
/*  51 */   public IIcon[] iconOver = new IIcon[4];
/*  52 */   public IIcon[] icon = new IIcon[5];
/*     */   protected int currentPass;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  58 */     this.icon[0] = ir.func_94245_a("thaumcraft:taint_fibres");
/*  59 */     this.icon[1] = ir.func_94245_a("thaumcraft:taintgrass1");
/*  60 */     this.icon[2] = ir.func_94245_a("thaumcraft:taintgrass2");
/*  61 */     this.icon[3] = ir.func_94245_a("thaumcraft:taint_spore_stalk_1");
/*  62 */     this.icon[4] = ir.func_94245_a("thaumcraft:taint_spore_stalk_2");
/*  63 */     this.iconOver[0] = ir.func_94245_a("thaumcraft:blank");
/*  64 */     for (int a = 1; a < 4; a++) { this.iconOver[a] = ir.func_94245_a("thaumcraft:taint_over_" + a);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  70 */     for (int var4 = 0; var4 <= 3; var4++)
/*     */     {
/*  72 */       par3List.add(new ItemStack(par1, 1, var4));
/*     */     }
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  78 */     int md = world.func_72805_g(x, y, z);
/*  79 */     if (md == 2) return 8;
/*  80 */     if (md == 4) { return 10;
/*     */     }
/*  82 */     return super.getLightValue(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149635_D()
/*     */   {
/*  89 */     double d0 = 0.5D;
/*  90 */     double d1 = 1.0D;
/*  91 */     return ColorizerGrass.func_77480_a(d0, d1);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149741_i(int par1)
/*     */   {
/*  97 */     return func_149635_D();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 103 */     int l = 0;
/* 104 */     int i1 = 0;
/* 105 */     int j1 = 0;
/*     */     
/* 107 */     for (int k1 = -1; k1 <= 1; k1++)
/*     */     {
/* 109 */       for (int l1 = -1; l1 <= 1; l1++)
/*     */       {
/* 111 */         int i2 = par1IBlockAccess.func_72807_a(par2 + l1, par4 + k1).func_150558_b(par2, par3, par4);
/* 112 */         l += ((i2 & 0xFF0000) >> 16);
/* 113 */         i1 += ((i2 & 0xFF00) >> 8);
/* 114 */         j1 += (i2 & 0xFF);
/*     */       }
/*     */     }
/*     */     
/* 118 */     return (l / 9 & 0xFF) << 16 | (i1 / 9 & 0xFF) << 8 | j1 / 9 & 0xFF;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getOverlayBlockTexture(int x, int y, int z, int side)
/*     */   {
/* 124 */     Random r = new Random(side + y + x * z);
/* 125 */     if (r.nextInt(100) < 95) return this.iconOver[0];
/* 126 */     return this.iconOver[(r.nextInt(3) + 1)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/* 138 */     return this.icon[par2];
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149695_a(World world, int x, int y, int z, Block par5)
/*     */   {
/* 144 */     if (isOnlyAdjacentToTaint(world, x, y, z)) {
/* 145 */       world.func_147449_b(x, y, z, Blocks.field_150350_a);
/*     */     }
/* 147 */     super.func_149695_a(world, x, y, z, par5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149674_a(World world, int x, int y, int z, Random random)
/*     */   {
/* 156 */     if (!world.field_72995_K)
/*     */     {
/* 158 */       int md = world.func_72805_g(x, y, z);
/*     */       
/*     */ 
/* 161 */       taintBiomeSpread(world, x, y, z, random, this);
/*     */       
/*     */ 
/* 164 */       if (((md == 0) && (isOnlyAdjacentToTaint(world, x, y, z))) || (world.func_72807_a(x, z).field_76756_M != Config.biomeTaintID))
/*     */       {
/* 166 */         world.func_147449_b(x, y, z, Blocks.field_150350_a);
/* 167 */         return;
/*     */       }
/*     */       
/* 170 */       int xx = x + random.nextInt(3) - 1;
/* 171 */       int yy = y + random.nextInt(5) - 3;
/* 172 */       int zz = z + random.nextInt(3) - 1;
/*     */       
/* 174 */       if (world.func_72807_a(xx, zz).field_76756_M == Config.biomeTaintID) {
/* 175 */         Block bi = world.func_147439_a(xx, yy, zz);
/*     */         
/*     */ 
/* 178 */         if (!spreadFibres(world, xx, yy, zz))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 183 */           int adjacentTaint = getAdjacentTaint(world, xx, yy, zz);
/* 184 */           Material bm = world.func_147439_a(xx, yy, zz).func_149688_o();
/*     */           
/*     */ 
/* 187 */           if ((adjacentTaint >= 2) && (
/* 188 */             (Utils.isWoodLog(world, xx, yy, zz)) || (bm == Material.field_151572_C) || (bm == Material.field_151570_A)))
/*     */           {
/* 190 */             world.func_147465_d(xx, yy, zz, ConfigBlocks.blockTaint, 0, 3);
/* 191 */             world.func_147452_c(xx, yy, zz, ConfigBlocks.blockTaint, 1, 0);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 196 */           if ((adjacentTaint >= 3) && (bi != Blocks.field_150350_a) && (
/* 197 */             (bm == Material.field_151595_p) || (bm == Material.field_151578_c) || (bm == Material.field_151577_b) || (bm == Material.field_151571_B)))
/*     */           {
/* 199 */             world.func_147465_d(xx, yy, zz, ConfigBlocks.blockTaint, 1, 3);
/* 200 */             world.func_147452_c(xx, yy, zz, ConfigBlocks.blockTaint, 1, 0);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 205 */           if ((md == 3) && (Config.spawnTaintSpore) && (random.nextInt(10) == 0) && (world.func_147437_c(x, y + 1, z)))
/*     */           {
/*     */ 
/* 208 */             world.func_72921_c(x, y, z, 4, 3);
/* 209 */             EntityTaintSpore spore = new EntityTaintSpore(world);
/* 210 */             spore.func_70012_b(x + 0.5F, y + 1, z + 0.5F, 0.0F, 0.0F);
/* 211 */             world.func_72838_d(spore);
/*     */ 
/*     */           }
/* 214 */           else if (md == 4)
/*     */           {
/* 216 */             List<Entity> targets = world.func_72872_a(EntityTaintSpore.class, AxisAlignedBB.func_72330_a(x, y + 1, z, x + 1, y + 2, z + 1));
/*     */             
/* 218 */             if (targets.size() <= 0) {
/* 219 */               world.func_72921_c(x, y, z, 3, 3);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean spreadFibres(World world, int x, int y, int z) {
/* 228 */     Block bi = world.func_147439_a(x, y, z);
/* 229 */     if ((BlockUtils.isAdjacentToSolidBlock(world, x, y, z)) && (!isOnlyAdjacentToTaint(world, x, y, z)) && (!world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) && ((world.func_147437_c(x, y, z)) || (bi.isReplaceable(world, x, y, z)) || ((bi instanceof BlockFlower)) || (bi.isLeaves(world, x, y, z))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 237 */       if ((world.field_73012_v.nextInt(10) == 0) && (world.func_147437_c(x, y + 1, z)) && (world.isSideSolid(x, y - 1, z, ForgeDirection.UP)))
/*     */       {
/* 239 */         if (world.field_73012_v.nextInt(10) < 9) {
/* 240 */           world.func_147465_d(x, y, z, ConfigBlocks.blockTaintFibres, 1, 3);
/* 241 */         } else if (world.field_73012_v.nextInt(12) < 10)
/* 242 */           world.func_147465_d(x, y, z, ConfigBlocks.blockTaintFibres, 2, 3); else
/* 243 */           world.func_147465_d(x, y, z, ConfigBlocks.blockTaintFibres, 3, 3);
/*     */       } else {
/* 245 */         world.func_147465_d(x, y, z, ConfigBlocks.blockTaintFibres, 0, 3);
/*     */       }
/*     */       
/* 248 */       world.func_147452_c(x, y, z, ConfigBlocks.blockTaintFibres, 1, 0);
/*     */       
/* 250 */       return true;
/*     */     }
/* 252 */     return false;
/*     */   }
/*     */   
/*     */   public static void taintBiomeSpread(World world, int x, int y, int z, Random rand, Block block) {
/* 256 */     if (Config.taintSpreadRate > 0) {
/* 257 */       int xx = rand.nextInt(3) - 1;
/* 258 */       int zz = rand.nextInt(3) - 1;
/* 259 */       if (world.func_72807_a(x + xx, z + zz).field_76756_M != Config.biomeTaintID)
/*     */       {
/* 261 */         if ((rand.nextInt(Config.taintSpreadRate * 5) == 0) && (getAdjacentTaint(world, x, y, z) >= 2)) {
/* 262 */           Utils.setBiomeAt(world, x + xx, z + zz, ThaumcraftWorldGenerator.biomeTaint);
/* 263 */           world.func_147452_c(x, y, z, block, 1, 0);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getAdjacentTaint(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 271 */     int count = 0;
/* 272 */     for (int a = 0; a < 6; a++) {
/* 273 */       ForgeDirection d = ForgeDirection.getOrientation(a);
/* 274 */       int xx = x + d.offsetX;
/* 275 */       int yy = y + d.offsetY;
/* 276 */       int zz = z + d.offsetZ;
/* 277 */       Block bi = world.func_147439_a(xx, yy, zz);
/* 278 */       if ((bi == ConfigBlocks.blockTaint) || (bi == ConfigBlocks.blockTaintFibres)) {
/* 279 */         count++;
/*     */       }
/*     */     }
/* 282 */     return count;
/*     */   }
/*     */   
/*     */   public static boolean isOnlyAdjacentToTaint(World world, int x, int y, int z) {
/* 286 */     for (int a = 0; a < 6; a++) {
/* 287 */       ForgeDirection d = ForgeDirection.getOrientation(a);
/* 288 */       int xx = x + d.offsetX;
/* 289 */       int yy = y + d.offsetY;
/* 290 */       int zz = z + d.offsetZ;
/* 291 */       Block bi = world.func_147439_a(xx, yy, zz);
/* 292 */       if ((!world.func_147437_c(xx, yy, zz)) && (world.func_147439_a(xx, yy, zz).func_149688_o() != Config.taintMaterial))
/* 293 */         return false;
/*     */     }
/* 295 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Item func_149650_a(int md, Random rand, int fortune)
/*     */   {
/* 306 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149670_a(World world, int i, int j, int k, Entity entity)
/*     */   {
/* 314 */     int md = world.func_72805_g(i, j, k);
/* 315 */     if ((!world.field_72995_K) && ((entity instanceof EntityLivingBase)) && (!((EntityLivingBase)entity).func_70662_br()))
/*     */     {
/* 317 */       if (((entity instanceof EntityPlayer)) && (world.field_73012_v.nextInt(1000) == 0)) {
/* 318 */         ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 80, 0, false));
/*     */       }
/* 320 */       else if ((!(entity instanceof EntityPlayer)) && (world.field_73012_v.nextInt(500) == 0)) {
/* 321 */         ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 160, 0, false));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149696_a(World world, int x, int y, int z, int id, int cd)
/*     */   {
/* 331 */     if (id == 1)
/*     */     {
/* 333 */       if (world.field_72995_K) {
/* 334 */         world.func_72980_b(x, y, z, "thaumcraft:roots", 0.1F, 0.9F + world.field_73012_v.nextFloat() * 0.2F, false);
/*     */       }
/* 336 */       return true;
/*     */     }
/*     */     
/* 339 */     return super.func_149696_a(world, x, y, z, id, cd);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/* 345 */     return ConfigBlocks.blockTaintFibreRI;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canRenderInPass(int pass)
/*     */   {
/* 351 */     return pass == 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_149701_w()
/*     */   {
/* 358 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 366 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 372 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149655_b(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 380 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/* 389 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149719_a(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 396 */     int md = world.func_72805_g(x, y, z);
/*     */     
/* 398 */     if (md == 0) {
/* 399 */       float f = 0.0625F;
/*     */       try {
/* 401 */         for (int a = 0; a < 6; a++) {
/* 402 */           ForgeDirection side = ForgeDirection.getOrientation(a);
/* 403 */           if (world.isSideSolid(x + side.offsetX, y + side.offsetY, z + side.offsetZ, side.getOpposite(), false)) {
/* 404 */             switch (a) {
/* 405 */             case 0:  func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F); break;
/* 406 */             case 1:  func_149676_a(0.0F, 1.0F - f, 0.0F, 1.0F, 1.0F, 1.0F); break;
/* 407 */             case 2:  func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f); break;
/* 408 */             case 3:  func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F); break;
/* 409 */             case 4:  func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F); break;
/* 410 */             case 5:  func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */             }
/* 412 */             return;
/*     */           }
/*     */         }
/*     */       } catch (Throwable t) {}
/* 416 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
/*     */     } else {
/* 418 */       func_149676_a(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/* 424 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4)
/*     */   {
/* 429 */     boolean biome = par1World.func_72807_a(par2, par4).field_76756_M == Config.biomeTaintID;
/* 430 */     return (biome) && (super.func_149742_c(par1World, par2, par3, par4));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockTaintFibres.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */