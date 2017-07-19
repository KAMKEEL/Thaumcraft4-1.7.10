/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXSpark;
/*     */ import thaumcraft.client.fx.particles.FXSparkle;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.ItemWispEssence;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ import thaumcraft.common.tiles.TileNitor;
/*     */ import thaumcraft.common.tiles.TileNode;
/*     */ import thaumcraft.common.tiles.TileNodeEnergized;
/*     */ import thaumcraft.common.tiles.TileNodeStabilizer;
/*     */ import thaumcraft.common.tiles.TileWardingStone;
/*     */ 
/*     */ public class BlockAiry extends BlockContainer
/*     */ {
/*     */   public IIcon blankIcon;
/*     */   
/*     */   public BlockAiry()
/*     */   {
/*  54 */     super(Config.airyMaterial);
/*  55 */     func_149672_a(new Block.SoundType("cloth", 0.0F, 1.0F));
/*  56 */     func_149647_a(Thaumcraft.tabTC);
/*  57 */     func_149675_a(true);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  64 */     this.blankIcon = ir.func_94245_a("thaumcraft:blank");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/*  70 */     return this.blankIcon;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
/*     */   {
/*  77 */     int md = worldObj.func_72805_g(target.field_72311_b, target.field_72312_c, target.field_72309_d);
/*  78 */     if (((md == 0) || (md == 5)) && (worldObj.field_73012_v.nextBoolean()))
/*  79 */       UtilsFX.infusedStoneSparkle(worldObj, target.field_72311_b, target.field_72312_c, target.field_72309_d, 0);
/*  80 */     return super.addHitEffects(worldObj, target, effectRenderer);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
/*     */   {
/*  86 */     if ((meta == 0) || (meta == 5)) {
/*  87 */       Thaumcraft.proxy.burst(world, x + 0.5D, y + 0.5D, z + 0.5D, 1.0F);
/*  88 */       world.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:craftfail", 1.0F, 1.0F, false);
/*     */     }
/*  90 */     return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
/*     */   }
/*     */   
/*     */   public float func_149712_f(World world, int x, int y, int z)
/*     */   {
/*  95 */     int md = world.func_72805_g(x, y, z);
/*  96 */     if ((md == 0) || (md == 5)) return 2.0F;
/*  97 */     if ((md == 10) || (md == 11)) return 100.0F;
/*  98 */     if (md == 12) return -1.0F;
/*  99 */     return super.func_149712_f(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
/*     */   {
/* 106 */     int md = world.func_72805_g(x, y, z);
/* 107 */     if ((md == 0) || (md == 5)) return 200.0F;
/* 108 */     if ((md == 10) || (md == 11)) return 50.0F;
/* 109 */     if (md == 12) return Float.MAX_VALUE;
/* 110 */     return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 116 */     int md = world.func_72805_g(x, y, z);
/* 117 */     if ((md == 1) || (md == 2) || (md == 3)) return 15;
/* 118 */     if ((md == 4) || (md == 12)) return 0;
/* 119 */     if ((md == 0) || (md == 5) || (md == 10) || (md == 11)) return 8;
/* 120 */     return super.getLightValue(world, x, y, z);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess ba, int x, int y, int z)
/*     */   {
/* 125 */     int md = ba.func_72805_g(x, y, z);
/* 126 */     if ((md == 3) || (md == 4) || (md == 10) || (md == 11) || (md == 12)) {
/* 127 */       func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*     */     } else {
/* 129 */       func_149676_a(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
/*     */     }
/* 131 */     super.func_149719_a(ba, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 136 */     int md = world.func_72805_g(x, y, z);
/* 137 */     if ((md == 2) || (md == 3) || (md == 4) || (md == 10) || (md == 11)) return true;
/* 138 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 143 */     int md = world.func_72805_g(x, y, z);
/* 144 */     if ((md == 2) || (md == 3) || (md == 4)) return true;
/* 145 */     return super.canBeReplacedByLeaves(world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean isLeaves(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 150 */     int md = world.func_72805_g(x, y, z);
/* 151 */     if ((md == 2) || (md == 3)) return true;
/* 152 */     return super.isLeaves(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
/*     */   {
/* 158 */     int metadata = world.func_72805_g(x, y, z);
/*     */     
/* 160 */     if ((metadata == 4) && (par7Entity != null) && ((par7Entity instanceof EntityLivingBase)) && (!(par7Entity instanceof EntityPlayer)))
/*     */     {
/* 162 */       int a = 1;
/* 163 */       if (world.func_147439_a(x, y - a, z) != ConfigBlocks.blockCosmeticSolid) a++;
/* 164 */       if (!world.func_72864_z(x, y - a, z)) {
/* 165 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 166 */         super.func_149743_a(world, x, y, z, par5AxisAlignedBB, par6List, par7Entity);
/*     */       }
/*     */     }
/* 169 */     else if (metadata == 12) {
/* 170 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 171 */       super.func_149743_a(world, x, y, z, par5AxisAlignedBB, par6List, par7Entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 177 */     int metadata = world.func_72805_g(x, y, z);
/* 178 */     if (metadata == 4) {
/* 179 */       int a = 1;
/* 180 */       while (a < 3) {
/* 181 */         TileEntity te = world.func_147438_o(x, y - a, z);
/* 182 */         if ((te != null) && ((te instanceof TileWardingStone))) {
/* 183 */           return te.func_145831_w().func_72864_z(x, y - a, z);
/*     */         }
/* 185 */         a++;
/*     */       }
/*     */     }
/* 188 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/* 194 */     int metadata = world.func_72805_g(x, y, z);
/* 195 */     if ((metadata != 4) && (metadata != 12)) return null;
/* 196 */     return super.func_149668_a(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4)
/*     */   {
/* 202 */     int md = par1World.func_72805_g(par2, par3, par4);
/* 203 */     if ((md == 0) || (md == 2) || (md == 3) || (md == 4) || (md == 5) || (md == 10) || (md == 11) || (md == 12)) return AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/* 204 */     return super.func_149633_g(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/* 209 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/* 216 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 222 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 228 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 234 */     return par1;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3)
/*     */   {
/* 239 */     return par1 == 1 ? ConfigItems.itemResource : Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   public Item func_149694_d(World world, int x, int y, int z)
/*     */   {
/* 244 */     int md = world.func_72805_g(x, y, z);
/* 245 */     if (md == 1) return ConfigItems.itemResource;
/* 246 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
/*     */   {
/* 252 */     if ((par5 == 0) && (!par1World.field_72995_K))
/*     */     {
/* 254 */       TileEntity te = par1World.func_147438_o(par2, par3, par4);
/* 255 */       if ((te != null) && ((te instanceof INode)) && (((INode)te).getAspects().size() > 0)) {
/* 256 */         for (thaumcraft.api.aspects.Aspect aspect : ((INode)te).getAspects().getAspects()) {
/* 257 */           for (int a = 0; a <= ((INode)te).getAspects().getAmount(aspect) / 10; a++) {
/* 258 */             if (((INode)te).getAspects().getAmount(aspect) >= 5) {
/* 259 */               ItemStack ess = new ItemStack(ConfigItems.itemWispEssence);
/* 260 */               AspectList al = new AspectList();
/* 261 */               ((ItemWispEssence)ess.func_77973_b()).setAspects(ess, new AspectList().add(aspect, 2));
/*     */               
/* 263 */               func_149642_a(par1World, par2, par3, par4, ess);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 269 */     super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World w, int i, int j, int k, Random r)
/*     */   {
/* 276 */     int md = w.func_72805_g(i, j, k);
/* 277 */     if (md == 1) {
/* 278 */       FXSparkle ef2 = new FXSparkle(w, i + 0.5F, j + 0.5F, k + 0.5F, i + 0.5F + (r.nextFloat() - r.nextFloat()) / 3.0F, j + 0.5F + (r.nextFloat() - r.nextFloat()) / 3.0F, k + 0.5F + (r.nextFloat() - r.nextFloat()) / 3.0F, 1.0F, 6, 3);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 286 */       ef2.setGravity(0.05F);
/* 287 */       ef2.field_70145_X = true;
/* 288 */       ParticleEngine.instance.addEffect(w, ef2);
/*     */ 
/*     */     }
/* 291 */     else if ((md == 2) && (r.nextInt(500) == 0)) {
/* 292 */       int x1 = i + r.nextInt(3) - r.nextInt(3);
/* 293 */       int y1 = j + r.nextInt(3) - r.nextInt(3);
/* 294 */       int z1 = k + r.nextInt(3) - r.nextInt(3);
/*     */       
/* 296 */       int x2 = x1 + r.nextInt(3) - r.nextInt(3);
/* 297 */       int y2 = y1 + r.nextInt(3) - r.nextInt(3);
/* 298 */       int z2 = z1 + r.nextInt(3) - r.nextInt(3);
/*     */       
/* 300 */       Thaumcraft.proxy.wispFX3(w, x1, y1, z1, x2, y2, z2, 0.1F + r.nextFloat() * 0.1F, 7, false, r.nextBoolean() ? -0.033F : 0.033F);
/*     */ 
/*     */ 
/*     */     }
/* 304 */     else if ((md == 10) || (md == 11))
/*     */     {
/* 306 */       float h = r.nextFloat() * 0.33F;
/* 307 */       FXSpark ef = new FXSpark(w, i + w.field_73012_v.nextFloat(), j + 0.1515F + h / 2.0F, k + w.field_73012_v.nextFloat(), 0.33F + h);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 312 */       if (md == 10) {
/* 313 */         ef.func_70538_b(0.65F + w.field_73012_v.nextFloat() * 0.1F, 1.0F, 1.0F);
/* 314 */         ef.func_82338_g(0.8F);
/*     */       } else {
/* 316 */         ef.func_70538_b(0.3F - w.field_73012_v.nextFloat() * 0.1F, 0.0F, 0.5F + w.field_73012_v.nextFloat() * 0.2F);
/*     */       }
/*     */       
/* 319 */       ParticleEngine.instance.addEffect(w, ef);
/*     */       
/* 321 */       if (r.nextInt(50) == 0) {
/* 322 */         w.func_72980_b(i, j, k, "thaumcraft:jacobs", 0.5F, 1.0F + (r.nextFloat() - r.nextFloat()) * 0.2F, false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 329 */     if (metadata == 0) return new TileNode();
/* 330 */     if (metadata == 1) return new TileNitor();
/* 331 */     if (metadata == 4) return new thaumcraft.common.tiles.TileWardingStoneFence();
/* 332 */     if (metadata == 5) return new TileNodeEnergized();
/* 333 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 338 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 345 */     par3List.add(new ItemStack(par1, 1, 0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
/*     */   {
/* 354 */     if ((stack.func_77960_j() == 0) && ((entity instanceof EntityPlayer))) {
/* 355 */       ThaumcraftWorldGenerator.createRandomNodeAt(world, x, y, z, world.field_73012_v, false, false, false);
/*     */     }
/*     */     
/* 358 */     super.func_149689_a(world, x, y, z, entity, stack);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isAir(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 364 */     int md = world.func_72805_g(x, y, z);
/* 365 */     if ((md == 2) || (md == 3) || (md == 10) || (md == 11)) return true;
/* 366 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*     */   {
/* 371 */     int md = world.func_72805_g(x, y, z);
/* 372 */     if (md == 5) {
/* 373 */       TileEntity te = world.func_147438_o(x, y - 1, z);
/* 374 */       if ((world.func_72864_z(x, y - 1, z)) || (te == null) || (!(te instanceof TileNodeStabilizer)))
/*     */       {
/* 376 */         explodify(world, x, y, z);
/*     */       } else {
/* 378 */         te = world.func_147438_o(x, y + 1, z);
/* 379 */         if ((te == null) || (!(te instanceof thaumcraft.common.tiles.TileNodeConverter))) {
/* 380 */           explodify(world, x, y, z);
/*     */         }
/*     */       }
/*     */     }
/* 384 */     super.func_149695_a(world, x, y, z, block);
/*     */   }
/*     */   
/*     */   public static void explodify(World world, int x, int y, int z) {
/* 388 */     if (!world.field_72995_K) {
/* 389 */       world.func_147468_f(x, y, z);
/* 390 */       world.func_72876_a(null, x + 0.5D, y + 0.5D, z + 0.5D, 3.0F, false);
/* 391 */       for (int a = 0; a < 50; a++) {
/* 392 */         int xx = x + world.field_73012_v.nextInt(8) - world.field_73012_v.nextInt(8);
/* 393 */         int yy = y + world.field_73012_v.nextInt(8) - world.field_73012_v.nextInt(8);
/* 394 */         int zz = z + world.field_73012_v.nextInt(8) - world.field_73012_v.nextInt(8);
/* 395 */         if (world.func_147437_c(xx, yy, zz)) {
/* 396 */           if (yy < y) {
/* 397 */             world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGoo, 8, 3);
/*     */           } else {
/* 399 */             world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGas, 8, 3);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 408 */     int md = world.func_72805_g(x, y, z);
/* 409 */     if (md == 10) {
/* 410 */       entity.func_70097_a(DamageSource.field_76376_m, 1 + world.field_73012_v.nextInt(2));
/* 411 */       entity.field_70159_w *= 0.8D;
/* 412 */       entity.field_70179_y *= 0.8D;
/* 413 */       if ((!world.field_72995_K) && (world.field_73012_v.nextInt(100) == 0)) {
/* 414 */         world.func_147468_f(x, y, z);
/*     */       }
/*     */     }
/* 417 */     else if ((md == 11) && (!(entity instanceof IEldritchMob))) {
/* 418 */       if (world.field_73012_v.nextInt(100) == 0) entity.func_70097_a(DamageSource.field_82727_n, 1.0F);
/* 419 */       entity.field_70159_w *= 0.66D;
/* 420 */       entity.field_70179_y *= 0.66D;
/* 421 */       if ((entity instanceof EntityPlayer)) {
/* 422 */         ((EntityPlayer)entity).func_71020_j(0.05F);
/*     */       }
/* 424 */       if ((entity instanceof EntityLivingBase)) {
/* 425 */         PotionEffect pe = new PotionEffect(Potion.field_76437_t.field_76415_H, 100, 1, true);
/* 426 */         ((EntityLivingBase)entity).func_70690_d(pe);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand)
/*     */   {
/* 433 */     super.func_149674_a(world, x, y, z, rand);
/* 434 */     int md = world.func_72805_g(x, y, z);
/* 435 */     if (((md == 10) || (md == 11)) && (!world.field_72995_K)) {
/* 436 */       world.func_147468_f(x, y, z);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockAiry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */