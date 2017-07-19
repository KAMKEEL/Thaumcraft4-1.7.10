/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileEldritchPortal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockEldritchPortal
/*     */   extends Block
/*     */ {
/*     */   public IIcon blankIcon;
/*     */   
/*     */   public BlockEldritchPortal()
/*     */   {
/*  32 */     super(Config.airyMaterial);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  40 */     this.blankIcon = ir.func_94245_a("thaumcraft:blank");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/*  46 */     return this.blankIcon;
/*     */   }
/*     */   
/*     */   public float func_149712_f(World world, int x, int y, int z)
/*     */   {
/*  51 */     return -1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
/*     */   {
/*  58 */     return 200000.0F;
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  63 */     return 15;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  74 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  79 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  85 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4)
/*     */   {
/*  96 */     return AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/* 101 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 106 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 112 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 118 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 124 */     return par1;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3)
/*     */   {
/* 129 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 134 */     return new TileEldritchPortal();
/*     */   }
/*     */   
/*     */   public boolean hasTileEntity(int metadata)
/*     */   {
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block p_149695_5_)
/*     */   {
/* 144 */     if ((world.func_147439_a(x, y + 1, z) != ConfigBlocks.blockEldritch) || (world.func_147439_a(x, y - 1, z) != ConfigBlocks.blockEldritch))
/*     */     {
/* 146 */       world.func_147468_f(x, y, z);
/*     */     }
/*     */     
/* 149 */     super.func_149695_a(world, x, y, z, p_149695_5_);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockEldritchPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */