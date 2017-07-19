/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.ColorizerGrass;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.entities.EntityFallingTaint;
/*     */ import thaumcraft.common.entities.monster.EntityTaintSporeSwarmer;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class BlockTaint extends Block
/*     */ {
/*     */   private IIcon iconCrust;
/*     */   private IIcon iconSoil;
/*     */   private IIcon iconFlesh;
/*     */   
/*     */   public BlockTaint()
/*     */   {
/*  42 */     super(Config.taintMaterial);
/*  43 */     func_149711_c(2.0F);
/*  44 */     func_149752_b(10.0F);
/*  45 */     func_149672_a(new thaumcraft.common.lib.CustomSoundType("gore", 0.5F, 0.8F));
/*  46 */     func_149675_a(true);
/*  47 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  54 */     for (int var4 = 0; var4 <= 2; var4++)
/*     */     {
/*  56 */       par3List.add(new ItemStack(par1, 1, var4));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister)
/*     */   {
/*  68 */     this.iconCrust = par1IconRegister.func_94245_a("thaumcraft:taint_crust");
/*  69 */     this.iconSoil = par1IconRegister.func_94245_a("thaumcraft:taint_soil");
/*  70 */     this.iconFlesh = par1IconRegister.func_94245_a("thaumcraft:fleshblock");
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int side)
/*     */   {
/*  77 */     int md = ba.func_72805_g(x, y, z);
/*  78 */     if (md == 0) {
/*  79 */       return this.iconCrust;
/*     */     }
/*  81 */     if (md == 1) {
/*  82 */       return this.iconSoil;
/*     */     }
/*  84 */     if (md == 2) {
/*  85 */       return this.iconFlesh;
/*     */     }
/*  87 */     return this.iconCrust;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int md)
/*     */   {
/*  99 */     if (md == 0) {
/* 100 */       return this.iconCrust;
/*     */     }
/* 102 */     if (md == 1) {
/* 103 */       return this.iconSoil;
/*     */     }
/* 105 */     if (md == 2) {
/* 106 */       return this.iconFlesh;
/*     */     }
/* 108 */     return this.iconCrust;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149635_D()
/*     */   {
/* 115 */     double d0 = 0.5D;
/* 116 */     double d1 = 1.0D;
/* 117 */     return ColorizerGrass.func_77480_a(d0, d1);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149741_i(int par1)
/*     */   {
/* 124 */     return par1 == 1 ? ThaumcraftWorldGenerator.biomeTaint.field_76790_z : super.func_149635_D();
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 131 */     int md = par1IBlockAccess.func_72805_g(par2, par3, par4);
/* 132 */     if (md != 1) { return super.func_149720_d(par1IBlockAccess, par2, par3, par4);
/*     */     }
/* 134 */     int l = 0;
/* 135 */     int i1 = 0;
/* 136 */     int j1 = 0;
/*     */     
/* 138 */     for (int k1 = -1; k1 <= 1; k1++)
/*     */     {
/* 140 */       for (int l1 = -1; l1 <= 1; l1++)
/*     */       {
/* 142 */         int i2 = par1IBlockAccess.func_72807_a(par2 + l1, par4 + k1).func_150558_b(par2, par3, par4);
/* 143 */         l += ((i2 & 0xFF0000) >> 16);
/* 144 */         i1 += ((i2 & 0xFF00) >> 8);
/* 145 */         j1 += (i2 & 0xFF);
/*     */       }
/*     */     }
/*     */     
/* 149 */     return (l / 9 & 0xFF) << 16 | (i1 / 9 & 0xFF) << 8 | j1 / 9 & 0xFF;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149674_a(World world, int x, int y, int z, Random random)
/*     */   {
/* 158 */     if (!world.field_72995_K)
/*     */     {
/* 160 */       int md = world.func_72805_g(x, y, z);
/* 161 */       if (md == 2) { return;
/*     */       }
/* 163 */       BlockTaintFibres.taintBiomeSpread(world, x, y, z, random, this);
/*     */       
/* 165 */       if (md == 0) {
/* 166 */         if (tryToFall(world, x, y, z, x, y, z)) {
/* 167 */           return;
/*     */         }
/* 169 */         if (world.func_147437_c(x, y + 1, z)) {
/* 170 */           boolean doIt = true;
/* 171 */           ForgeDirection dir = ForgeDirection.getOrientation(2 + random.nextInt(4));
/* 172 */           for (int a = 0; a < 4; a++) {
/* 173 */             if (!world.func_147437_c(x + dir.offsetX, y - a, z + dir.offsetZ)) {
/* 174 */               doIt = false;
/* 175 */               break;
/*     */             }
/* 177 */             if (world.func_147439_a(x, y - a, z) != this) {
/* 178 */               doIt = false;
/* 179 */               break;
/*     */             }
/*     */           }
/* 182 */           if ((doIt) && 
/* 183 */             (tryToFall(world, x, y, z, x + dir.offsetX, y, z + dir.offsetZ))) { return;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 190 */       int xx = x + random.nextInt(3) - 1;
/* 191 */       int yy = y + random.nextInt(5) - 3;
/* 192 */       int zz = z + random.nextInt(3) - 1;
/*     */       
/* 194 */       if (world.func_72807_a(xx, zz).field_76756_M == Config.biomeTaintID) {
/* 195 */         Block bi = world.func_147439_a(xx, yy, zz);
/*     */         
/*     */ 
/* 198 */         if ((!BlockTaintFibres.spreadFibres(world, xx, yy, zz)) || 
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 204 */           (md == 0)) {
/* 205 */           if ((Config.spawnTaintSpore) && (world.func_147437_c(x, y + 1, z)) && (random.nextInt(200) == 0))
/*     */           {
/*     */ 
/* 208 */             List<Entity> targets = world.func_72872_a(EntityTaintSporeSwarmer.class, AxisAlignedBB.func_72330_a(x, y, z, x + 1, y + 1, z + 1).func_72314_b(16.0D, 16.0D, 16.0D));
/*     */             
/* 210 */             if (targets.size() <= 0) {
/* 211 */               world.func_147468_f(x, y, z);
/* 212 */               EntityTaintSporeSwarmer spore = new EntityTaintSporeSwarmer(world);
/* 213 */               spore.func_70012_b(x + 0.5F, y, z + 0.5F, 0.0F, 0.0F);
/* 214 */               world.func_72838_d(spore);
/* 215 */               world.func_72956_a(spore, "thaumcraft:roots", 0.1F, 0.9F + world.field_73012_v.nextFloat() * 0.2F);
/*     */             }
/*     */           }
/*     */           else {
/* 219 */             boolean doIt = world.func_147439_a(x, y + 1, z) == this;
/* 220 */             if (doIt) {
/* 221 */               for (int a = 2; a < 6; a++) {
/* 222 */                 ForgeDirection dir = ForgeDirection.getOrientation(a);
/* 223 */                 if (world.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) != this) {
/* 224 */                   doIt = false;
/* 225 */                   break;
/*     */                 }
/*     */               }
/*     */             }
/* 229 */             if (doIt) {
/* 230 */               world.func_147465_d(x, y, z, ConfigBlocks.blockFluxGoo, ((BlockFluxGoo)ConfigBlocks.blockFluxGoo).getQuanta(), 3);
/*     */             }
/*     */             
/*     */           }
/*     */         }
/*     */       }
/* 236 */       else if ((md == 0) && (random.nextInt(20) == 0)) {
/* 237 */         world.func_147465_d(x, y, z, ConfigBlocks.blockFluxGoo, ((BlockFluxGoo)ConfigBlocks.blockFluxGoo).getQuanta(), 3);
/*     */ 
/*     */       }
/* 240 */       else if ((md == 1) && (random.nextInt(10) == 0)) {
/* 241 */         world.func_147465_d(x, y, z, Blocks.field_150346_d, 0, 3);
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
/*     */   public Item func_149650_a(int md, Random rand, int fortune)
/*     */   {
/* 254 */     return md == 2 ? Items.field_151078_bh : md == 1 ? Blocks.field_150346_d.func_149650_a(0, rand, fortune) : Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 259 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 267 */     return new ItemStack(this, 1, world.func_72805_g(x, y, z));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
/*     */   {
/* 273 */     if (metadata == 2) return true;
/* 274 */     return super.canSilkHarvest(world, player, x, y, z, metadata);
/*     */   }
/*     */   
/*     */   public int quantityDropped(int meta, int fortune, Random random)
/*     */   {
/* 279 */     if (meta == 2) { return 9;
/*     */     }
/* 281 */     return super.quantityDropped(meta, fortune, random);
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_149712_f(World world, int x, int y, int z)
/*     */   {
/* 287 */     int md = world.func_72805_g(x, y, z);
/* 288 */     if (md == 0) return 1.75F;
/* 289 */     if (md == 1) return 1.5F;
/* 290 */     if (md == 2) return 0.2F;
/* 291 */     return super.func_149712_f(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean canFallBelow(World par0World, int par1, int par2, int par3)
/*     */   {
/* 297 */     Block l = par0World.func_147439_a(par1, par2, par3);
/* 298 */     int md = par0World.func_72805_g(par1, par2, par3);
/* 299 */     for (int xx = -1; xx <= 1; xx++) for (int zz = -1; zz <= 1; zz++) for (int yy = -1; yy <= 1; yy++) {
/* 300 */           if (Utils.isWoodLog(par0World, par1 + xx, par2 + yy, par3 + zz)) {
/* 301 */             return false;
/*     */           }
/*     */         }
/* 304 */     if (l.isAir(par0World, par1, par2, par3))
/*     */     {
/* 306 */       return true;
/*     */     }
/* 308 */     if ((l == ConfigBlocks.blockFluxGoo) && (md >= 4))
/*     */     {
/* 310 */       return false;
/*     */     }
/* 312 */     if ((l == Blocks.field_150480_ab) || (l == ConfigBlocks.blockTaintFibres))
/*     */     {
/* 314 */       return true;
/*     */     }
/* 316 */     if (l.isReplaceable(par0World, par1, par2, par3))
/*     */     {
/* 318 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 322 */     return l.func_149688_o() == net.minecraft.block.material.Material.field_151586_h;
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean tryToFall(World par1World, int x, int y, int z, int x2, int y2, int z2)
/*     */   {
/* 328 */     int md = par1World.func_72805_g(x, y, z);
/* 329 */     if ((canFallBelow(par1World, x2, y2 - 1, z2)) && (y2 >= 0))
/*     */     {
/* 331 */       byte b0 = 32;
/*     */       
/* 333 */       if (par1World.func_72904_c(x2 - b0, y2 - b0, z2 - b0, x2 + b0, y2 + b0, z2 + b0))
/*     */       {
/* 335 */         if (!par1World.field_72995_K)
/*     */         {
/* 337 */           EntityFallingTaint entityfalling = new EntityFallingTaint(par1World, x2 + 0.5F, y2 + 0.5F, z2 + 0.5F, this, md, x, y, z);
/*     */           
/* 339 */           onStartFalling(entityfalling);
/* 340 */           par1World.func_72838_d(entityfalling);
/* 341 */           return true;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 346 */         par1World.func_147468_f(x, y, z);
/*     */         
/* 348 */         while ((canFallBelow(par1World, x2, y2 - 1, z2)) && (y2 > 0))
/*     */         {
/* 350 */           y2--;
/*     */         }
/*     */         
/* 353 */         if (y2 > 0)
/*     */         {
/* 355 */           par1World.func_147465_d(x, y, z, this, md, 3);
/*     */         }
/*     */       }
/*     */     }
/* 359 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149724_b(World world, int i, int j, int k, Entity entity)
/*     */   {
/* 366 */     int md = world.func_72805_g(i, j, k);
/* 367 */     if (md == 2) return;
/* 368 */     if ((!world.field_72995_K) && ((entity instanceof EntityLivingBase)) && (!((EntityLivingBase)entity).func_70662_br()))
/*     */     {
/* 370 */       if (((entity instanceof EntityPlayer)) && (world.field_73012_v.nextInt(100) == 0)) {
/* 371 */         ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 80, 0, false));
/*     */       }
/* 373 */       else if ((!(entity instanceof EntityPlayer)) && (world.field_73012_v.nextInt(20) == 0)) {
/* 374 */         ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 160, 0, false));
/*     */       }
/*     */     }
/* 377 */     super.func_149724_b(world, i, j, k, entity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void onStartFalling(EntityFallingTaint entityfalling) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onFinishFalling(World par1World, int par2, int par3, int par4, int par5) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int i, int j, int k, Random random)
/*     */   {
/* 396 */     int md = world.func_72805_g(i, j, k);
/* 397 */     if ((md == 0) && (world.func_147437_c(i, j - 1, k)) && (random.nextInt(10) == 0)) {
/* 398 */       Thaumcraft.proxy.dropletFX(world, i + 0.1F + world.field_73012_v.nextFloat() * 0.8F, j, k + 0.1F + world.field_73012_v.nextFloat() * 0.8F, 0.3F, 0.1F, 0.8F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149696_a(World world, int x, int y, int z, int id, int cd)
/*     */   {
/* 407 */     if (id == 1)
/*     */     {
/* 409 */       if (world.field_72995_K) {
/* 410 */         world.func_72980_b(x, y, z, "thaumcraft:roots", 0.1F, 0.9F + world.field_73012_v.nextFloat() * 0.2F, false);
/*     */       }
/* 412 */       return true;
/*     */     }
/*     */     
/* 415 */     return super.func_149696_a(world, x, y, z, id, cd);
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
/*     */ 
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/* 454 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */