/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BlockHole extends BlockContainer
/*     */ {
/*     */   public IIcon icon;
/*     */   public IIcon icon2;
/*     */   
/*     */   public BlockHole()
/*     */   {
/*  28 */     super(net.minecraft.block.material.Material.field_151576_e);
/*  29 */     func_149722_s();
/*  30 */     func_149752_b(6000000.0F);
/*  31 */     func_149672_a(Block.field_149778_k);
/*  32 */     func_149715_a(0.7F);
/*  33 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  34 */     func_149675_a(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  42 */     this.icon = ir.func_94245_a("thaumcraft:blank");
/*  43 */     this.icon2 = ir.func_94245_a("thaumcraft:empty");
/*     */   }
/*     */   
/*     */   public IIcon func_149691_a(int i, int m)
/*     */   {
/*  48 */     return m == 15 ? this.icon2 : this.icon;
/*     */   }
/*     */   
/*     */   public net.minecraft.item.ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/*  53 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {}
/*     */   
/*     */ 
/*     */   public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection o)
/*     */   {
/*  64 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149719_a(IBlockAccess iblockaccess, int i, int j, int k)
/*     */   {
/*  75 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World w, int i, int j, int k)
/*     */   {
/*  80 */     return AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  87 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  94 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public TileEntity func_149915_a(World var1, int var2)
/*     */   {
/* 100 */     return null;
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
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3)
/*     */   {
/* 117 */     return Item.func_150899_d(0);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockHole.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */