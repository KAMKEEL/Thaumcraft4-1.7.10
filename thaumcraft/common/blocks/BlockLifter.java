/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileLifter;
/*     */ 
/*     */ public class BlockLifter extends BlockContainer
/*     */ {
/*  22 */   private Random random = new Random();
/*     */   public IIcon iconTop;
/*     */   public IIcon iconBottom;
/*     */   
/*  26 */   public BlockLifter() { super(Material.field_151575_d);
/*  27 */     func_149711_c(2.5F);
/*  28 */     func_149752_b(15.0F);
/*  29 */     func_149672_a(field_149766_f);
/*  30 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon iconSide;
/*     */   
/*     */   public IIcon iconGlow;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  40 */     this.iconTop = ir.func_94245_a("thaumcraft:liftertop");
/*  41 */     this.iconBottom = ir.func_94245_a("thaumcraft:arcaneearbottom");
/*  42 */     this.iconSide = ir.func_94245_a("thaumcraft:lifterside");
/*  43 */     this.iconGlow = ir.func_94245_a("thaumcraft:animatedglow");
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/*  49 */     if (par1 == 0) return this.iconBottom;
/*  50 */     if (par1 == 1) return this.iconTop;
/*  51 */     return this.iconSide;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  57 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  62 */     return ConfigBlocks.blockLifterRI;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World w, int i, int j, int k, Random r)
/*     */   {
/*  68 */     TileEntity te = w.func_147438_o(i, j, k);
/*  69 */     if ((te != null) && ((te instanceof TileLifter)) && (!((TileLifter)te).gettingPower()) && 
/*  70 */       (((TileLifter)te).rangeAbove > 0)) {
/*  71 */       Thaumcraft.proxy.sparkle(i + 0.2F + r.nextFloat() * 0.6F, j + 1, k + 0.2F + r.nextFloat() * 0.6F, 1.0F, 3, -0.3F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/*  78 */     return (side != ForgeDirection.UP) && (side != ForgeDirection.DOWN);
/*     */   }
/*     */   
/*     */   public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/*  83 */     return side > 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149726_b(World world, int x, int y, int z)
/*     */   {
/*  90 */     updateLifterStack(world, x, y, z);
/*  91 */     super.func_149726_b(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149749_a(World world, int x, int y, int z, Block par5, int par6)
/*     */   {
/*  98 */     updateLifterStack(world, x, y, z);
/*  99 */     super.func_149749_a(world, x, y, z, par5, par6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149695_a(World world, int x, int y, int z, Block par5)
/*     */   {
/* 107 */     TileEntity te = world.func_147438_o(x, y, z);
/* 108 */     if ((te != null) && ((te instanceof TileLifter)) && (((TileLifter)te).gettingPower() != ((TileLifter)te).lastPowerState)) {
/* 109 */       updateLifterStack(world, x, y, z);
/*     */     }
/*     */     
/* 112 */     super.func_149695_a(world, x, y, z, par5);
/*     */   }
/*     */   
/*     */ 
/*     */   private void updateLifterStack(World worldObj, int xCoord, int yCoord, int zCoord)
/*     */   {
/* 118 */     int count = 1;
/* 119 */     while (worldObj.func_147439_a(xCoord, yCoord - count, zCoord) == this) {
/* 120 */       TileEntity te = worldObj.func_147438_o(xCoord, yCoord - count, zCoord);
/* 121 */       if ((te != null) && ((te instanceof TileLifter))) ((TileLifter)te).requiresUpdate = true;
/* 122 */       count++;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 130 */     return new TileLifter();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockLifter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */