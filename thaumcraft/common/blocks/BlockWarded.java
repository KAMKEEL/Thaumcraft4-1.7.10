/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.tiles.TileWarded;
/*     */ 
/*     */ public class BlockWarded extends BlockContainer
/*     */ {
/*     */   public IIcon icon;
/*     */   public IIcon iconRune;
/*     */   
/*     */   public BlockWarded()
/*     */   {
/*  38 */     super(net.minecraft.block.material.Material.field_151576_e);
/*  39 */     func_149672_a(field_149769_e);
/*  40 */     func_149649_H();
/*  41 */     func_149752_b(999.0F);
/*  42 */     func_149722_s();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  50 */     this.icon = ir.func_94245_a("thaumcraft:blank");
/*  51 */     this.iconRune = ir.func_94245_a("thaumcraft:runeborder");
/*     */   }
/*     */   
/*     */   public IIcon func_149691_a(int i, int m)
/*     */   {
/*  56 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  62 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
/*     */   {
/*  74 */     float f = (float)target.field_72307_f.field_72450_a - target.field_72311_b;
/*  75 */     float f1 = (float)target.field_72307_f.field_72448_b - target.field_72312_c;
/*  76 */     float f2 = (float)target.field_72307_f.field_72449_c - target.field_72309_d;
/*  77 */     Thaumcraft.proxy.blockWard(worldObj, target.field_72311_b, target.field_72312_c, target.field_72309_d, ForgeDirection.getOrientation(target.field_72310_e), f, f1, f2);
/*     */     
/*  79 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  84 */     return thaumcraft.common.config.ConfigBlocks.blockWardedRI;
/*     */   }
/*     */   
/*  87 */   int sc = 0;
/*     */   
/*     */   public Block getBlock(World world, int x, int y, int z) {
/*  90 */     if (this.sc > 5) {
/*  91 */       this.sc = 0;
/*  92 */       return Blocks.field_150348_b;
/*     */     }
/*  94 */     this.sc += 1;
/*  95 */     TileEntity tile = world.func_147438_o(x, y, z);
/*  96 */     if ((tile != null) && ((tile instanceof TileWarded))) {
/*  97 */       this.sc = 0;
/*  98 */       return ((TileWarded)tile).block;
/*     */     }
/* 100 */     return Blocks.field_150348_b;
/*     */   }
/*     */   
/*     */   public Block getBlock(IBlockAccess world, int x, int y, int z) {
/* 104 */     if (this.sc > 5) {
/* 105 */       this.sc = 0;
/* 106 */       return Blocks.field_150348_b;
/*     */     }
/* 108 */     this.sc += 1;
/* 109 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 110 */     if ((tile != null) && ((tile instanceof TileWarded))) {
/* 111 */       this.sc = 0;
/* 112 */       return ((TileWarded)tile).block;
/*     */     }
/* 114 */     return Blocks.field_150348_b;
/*     */   }
/*     */   
/*     */ 
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3)
/*     */   {
/* 120 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 125 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_149656_h()
/*     */   {
/* 132 */     return 2;
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 137 */     return new TileWarded();
/*     */   }
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
/*     */   {
/* 147 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
/*     */   {
/* 152 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int par5)
/*     */   {
/* 159 */     return getBlock(ba, x, y, z).func_149673_e(ba, x, y, z, par5);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess ba, int x, int y, int z)
/*     */   {
/* 165 */     return getBlock(ba, x, y, z).func_149720_d(ba, x, y, z);
/*     */   }
/*     */   
/*     */   public int func_149643_k(World world, int x, int y, int z)
/*     */   {
/* 170 */     return getBlock(world, x, y, z).func_149643_k(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149677_c(IBlockAccess ba, int x, int y, int z)
/*     */   {
/* 176 */     return getBlock(ba, x, y, z).func_149677_c(ba, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess ba, int x, int y, int z, int par5)
/*     */   {
/* 181 */     return getBlock(ba, x, y, z).func_149646_a(ba, x, y, z, par5);
/*     */   }
/*     */   
/*     */   public boolean func_149747_d(IBlockAccess ba, int x, int y, int z, int par5)
/*     */   {
/* 186 */     return getBlock(ba, x, y, z).func_149747_d(ba, x, y, z, par5);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World ba, int x, int y, int z)
/*     */   {
/* 191 */     return getBlock(ba, x, y, z).func_149633_g(ba, x, y, z);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World ba, int x, int y, int z)
/*     */   {
/* 196 */     return getBlock(ba, x, y, z).func_149668_a(ba, x, y, z);
/*     */   }
/*     */   
/*     */   public void func_149734_b(World ba, int x, int y, int z, Random par5Random)
/*     */   {
/* 201 */     getBlock(ba, x, y, z).func_149734_b(ba, x, y, z, par5Random);
/*     */   }
/*     */   
/*     */   public boolean func_149707_d(World ba, int x, int y, int z, int par5)
/*     */   {
/* 206 */     return getBlock(ba, x, y, z).func_149707_d(ba, x, y, z, par5);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149724_b(World ba, int x, int y, int z, Entity par5Entity)
/*     */   {
/* 212 */     getBlock(ba, x, y, z).func_149724_b(ba, x, y, z, par5Entity);
/*     */   }
/*     */   
/*     */   public void func_149699_a(World ba, int x, int y, int z, EntityPlayer par5EntityPlayer)
/*     */   {
/* 217 */     getBlock(ba, x, y, z).func_149699_a(ba, x, y, z, par5EntityPlayer);
/*     */   }
/*     */   
/*     */   public void func_149640_a(World ba, int x, int y, int z, Entity par5Entity, Vec3 par6Vec3)
/*     */   {
/* 222 */     getBlock(ba, x, y, z).func_149640_a(ba, x, y, z, par5Entity, par6Vec3);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess ba, int x, int y, int z)
/*     */   {
/* 227 */     getBlock(ba, x, y, z).func_149719_a(ba, x, y, z);
/*     */   }
/*     */   
/*     */   public void func_149743_a(World ba, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
/*     */   {
/* 232 */     getBlock(ba, x, y, z).func_149743_a(ba, x, y, z, aabb, list, entity);
/*     */   }
/*     */   
/*     */   public void func_149670_a(World ba, int x, int y, int z, Entity par5Entity)
/*     */   {
/* 237 */     getBlock(ba, x, y, z).func_149670_a(ba, x, y, z, par5Entity);
/*     */   }
/*     */   
/*     */   public void func_149746_a(World ba, int x, int y, int z, Entity par5Entity, float par6)
/*     */   {
/* 242 */     getBlock(ba, x, y, z).func_149746_a(ba, x, y, z, par5Entity, par6);
/*     */   }
/*     */   
/*     */   public Item func_149694_d(World ba, int x, int y, int z)
/*     */   {
/* 247 */     return getBlock(ba, x, y, z).func_149694_d(ba, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 253 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 254 */     if ((tile != null) && ((tile instanceof TileWarded))) {
/* 255 */       return ((TileWarded)tile).light;
/*     */     }
/* 257 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity)
/*     */   {
/* 264 */     return getBlock(world, x, y, z).isLadder(world, x, y, z, entity);
/*     */   }
/*     */   
/*     */   public boolean isNormalCube(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 269 */     return getBlock(world, x, y, z).isNormalCube(world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/* 274 */     return getBlock(world, x, y, z).isSideSolid(world, x, y, z, side);
/*     */   }
/*     */   
/*     */   public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 279 */     return getBlock(world, x, y, z).canSustainLeaves(world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
/*     */   {
/* 284 */     return getBlock(world, x, y, z).canPlaceTorchOnTop(world, x, y, z);
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 289 */     return getBlock(world, x, y, z).getPickBlock(target, world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean isFoliage(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 294 */     return getBlock(world, x, y, z).isFoliage(world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
/*     */   {
/* 299 */     return getBlock(world, x, y, z).canSustainPlant(world, x, y, z, direction, plant);
/*     */   }
/*     */   
/*     */   public boolean isFertile(World world, int x, int y, int z)
/*     */   {
/* 304 */     return getBlock(world, x, y, z).isFertile(world, x, y, z);
/*     */   }
/*     */   
/*     */   public int getLightOpacity(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 309 */     return getBlock(world, x, y, z).getLightOpacity(world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
/*     */   {
/* 314 */     return getBlock(world, x, y, z).isBeaconBase(world, x, y, z, beaconX, beaconY, beaconZ);
/*     */   }
/*     */   
/*     */   public float getEnchantPowerBonus(World world, int x, int y, int z)
/*     */   {
/* 319 */     return getBlock(world, x, y, z).getEnchantPowerBonus(world, x, y, z);
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
/*     */   public boolean canHarvestBlock(EntityPlayer player, int meta)
/*     */   {
/* 335 */     return true;
/*     */   }
/*     */   
/*     */   public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockWarded.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */