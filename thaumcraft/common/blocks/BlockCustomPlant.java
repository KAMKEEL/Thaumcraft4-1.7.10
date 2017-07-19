/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockBush;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.EnumPlantType;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXWisp;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.world.WorldGenGreatwoodTrees;
/*     */ import thaumcraft.common.lib.world.WorldGenSilverwoodTrees;
/*     */ import thaumcraft.common.tiles.TileEtherealBloom;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCustomPlant
/*     */   extends BlockBush
/*     */ {
/*     */   public BlockCustomPlant()
/*     */   {
/*  39 */     super(Material.field_151585_k);
/*  40 */     func_149672_a(field_149779_h);
/*  41 */     float var3 = 0.4F;
/*  42 */     func_149647_a(Thaumcraft.tabTC);
/*  43 */     func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  48 */   public IIcon[] icon = new IIcon[6];
/*     */   public IIcon iconLeaves;
/*     */   public IIcon iconStalk;
/*     */   IIcon blank;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir) {
/*  55 */     this.icon[0] = ir.func_94245_a("thaumcraft:greatwoodsapling");
/*  56 */     this.icon[1] = ir.func_94245_a("thaumcraft:silverwoodsapling");
/*  57 */     this.icon[2] = ir.func_94245_a("thaumcraft:shimmerleaf");
/*  58 */     this.icon[3] = ir.func_94245_a("thaumcraft:cinderpearl");
/*  59 */     this.icon[4] = ir.func_94245_a("thaumcraft:purifier_seed");
/*  60 */     this.icon[5] = ir.func_94245_a("thaumcraft:manashroom");
/*  61 */     this.iconLeaves = ir.func_94245_a("thaumcraft:purifier_leaves");
/*  62 */     this.iconStalk = ir.func_94245_a("thaumcraft:purifier_stalk");
/*  63 */     this.blank = ir.func_94245_a("thaumcraft:blank");
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/*  70 */     if ((par2 == 4) && (par1 == 0)) return this.blank;
/*  71 */     return par2 < this.icon.length ? this.icon[par2] : null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  77 */     for (int var4 = 0; var4 <= 5; var4++)
/*     */     {
/*  79 */       par3List.add(new ItemStack(par1, 1, var4));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasTileEntity(int metadata)
/*     */   {
/*  85 */     if (metadata == 4) return true;
/*  86 */     return super.hasTileEntity(metadata);
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/*  91 */     if (metadata == 4) return new TileEtherealBloom();
/*  92 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/*  97 */     return par1;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3)
/*     */   {
/* 102 */     return Item.func_150898_a(this);
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 108 */     int md = world.func_72805_g(x, y, z);
/* 109 */     if (md == 3) return EnumPlantType.Desert;
/* 110 */     if (md == 4) return EnumPlantType.Cave;
/* 111 */     return EnumPlantType.Plains;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4)
/*     */   {
/* 117 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149674_a(World world, int i, int j, int k, Random random)
/*     */   {
/* 126 */     if (!world.field_72995_K)
/*     */     {
/* 128 */       super.func_149674_a(world, i, j, k, random);
/*     */       
/* 130 */       int l = world.func_72805_g(i, j, k);
/*     */       
/* 132 */       if ((l == 0) && (world.func_72957_l(i, j + 1, k) >= 9) && (random.nextInt(25) == 0))
/*     */       {
/* 134 */         growGreatTree(world, i, j, k, random);
/*     */ 
/*     */       }
/* 137 */       else if ((l == 1) && (world.func_72957_l(i, j + 1, k) >= 9) && (random.nextInt(50) == 0))
/*     */       {
/* 139 */         growSilverTree(world, i, j, k, random);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void growGreatTree(World world, int i, int j, int k, Random random)
/*     */   {
/* 147 */     if ((world == null) || (world.field_73011_w == null)) return;
/* 148 */     if (world.field_72995_K) return;
/* 149 */     world.func_147468_f(i, j, k);
/* 150 */     WorldGenGreatwoodTrees obj = new WorldGenGreatwoodTrees(true);
/*     */     
/* 152 */     if (!obj.generate(world, random, i, j, k, false))
/*     */     {
/* 154 */       world.func_147465_d(i, j, k, this, 0, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void growSilverTree(World world, int i, int j, int k, Random random)
/*     */   {
/* 160 */     if ((world == null) || (world.field_73011_w == null)) return;
/* 161 */     if (world.field_72995_K) return;
/* 162 */     world.func_147468_f(i, j, k);
/* 163 */     WorldGenSilverwoodTrees obj = new WorldGenSilverwoodTrees(true, 7, 5);
/*     */     
/* 165 */     if (!obj.func_76484_a(world, random, i, j, k))
/*     */     {
/* 167 */       world.func_147465_d(i, j, k, this, 1, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 173 */     int md = world.func_72805_g(x, y, z);
/* 174 */     if ((md == 1) || (md == 2) || (md == 3) || (md == 5)) { return 8;
/*     */     }
/* 176 */     if (md == 4) { return 15;
/*     */     }
/* 178 */     return super.getLightValue(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 185 */     int md = world.func_72805_g(x, y, z);
/* 186 */     if ((md == 5) && ((entity instanceof EntityLivingBase))) {
/* 187 */       ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 200, 0));
/*     */     }
/* 189 */     super.func_149670_a(world, x, y, z, entity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int i, int j, int k, Random random)
/*     */   {
/* 198 */     int md = world.func_72805_g(i, j, k);
/*     */     
/* 200 */     if ((md == 2) && (random.nextInt(3) == 0)) {
/* 201 */       float cr = 0.3F + world.field_73012_v.nextFloat() * 0.3F;
/* 202 */       float cg = 0.7F + world.field_73012_v.nextFloat() * 0.3F;
/* 203 */       float cb = 0.7F + world.field_73012_v.nextFloat() * 0.3F;
/* 204 */       float xr = i + 0.5F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1F;
/* 205 */       float yr = j + 0.5F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.15F;
/* 206 */       float zr = k + 0.5F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1F;
/*     */       
/* 208 */       FXWisp ef = new FXWisp(world, xr, yr, zr, 0.2F, cr, cg, cb);
/*     */       
/* 210 */       ef.tinkle = false;
/* 211 */       ParticleEngine.instance.addEffect(world, ef);
/*     */ 
/*     */     }
/* 214 */     else if ((md == 3) && (random.nextBoolean())) {
/* 215 */       float xr = i + 0.5F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1F;
/* 216 */       float yr = j + 0.6F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1F;
/* 217 */       float zr = k + 0.5F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1F;
/* 218 */       world.func_72869_a("smoke", xr, yr, zr, 0.0D, 0.0D, 0.0D);
/* 219 */       world.func_72869_a("flame", xr, yr, zr, 0.0D, 0.0D, 0.0D);
/*     */ 
/*     */     }
/* 222 */     else if ((md == 5) && (random.nextInt(3) == 0)) {
/* 223 */       float xr = i + 0.5F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.4F;
/* 224 */       float yr = j + 0.3F;
/* 225 */       float zr = k + 0.5F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.4F;
/* 226 */       FXWisp ef = new FXWisp(world, xr, yr, zr, 0.1F, 0.5F, 0.3F, 0.8F);
/* 227 */       ef.tinkle = false;
/* 228 */       ef.shrink = true;
/* 229 */       ef.setGravity(0.015F);
/* 230 */       ParticleEngine.instance.addEffect(world, ef);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 236 */     return 100;
/*     */   }
/*     */   
/*     */   public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 241 */     return 60;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCustomPlant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */