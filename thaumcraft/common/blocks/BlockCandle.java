/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.crafting.IInfusionStabiliser;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ public class BlockCandle extends Block implements IInfusionStabiliser
/*     */ {
/*     */   public IIcon icon;
/*     */   public IIcon iconStub;
/*     */   
/*     */   public BlockCandle()
/*     */   {
/*  28 */     super(net.minecraft.block.material.Material.field_151594_q);
/*  29 */     func_149711_c(0.1F);
/*  30 */     func_149672_a(field_149775_l);
/*  31 */     func_149647_a(Thaumcraft.tabTC);
/*  32 */     func_149715_a(0.95F);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  39 */     for (int var4 = 0; var4 < 16; var4++)
/*     */     {
/*  41 */       par3List.add(new ItemStack(par1, 1, var4));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  50 */     this.icon = ir.func_94245_a("thaumcraft:candle");
/*  51 */     this.iconStub = ir.func_94245_a("thaumcraft:candlestub");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/*  57 */     return this.icon;
/*     */   }
/*     */   
/*     */   public int func_149741_i(int par1)
/*     */   {
/*  62 */     return thaumcraft.common.lib.utils.Utils.colors[par1];
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4)
/*     */   {
/*  68 */     return World.func_147466_a(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/*  75 */     int var6 = par1World.func_72805_g(par2, par3, par4);
/*  76 */     boolean var7 = func_149742_c(par1World, par2, par3 - 1, par4);
/*     */     
/*  78 */     if (!var7)
/*     */     {
/*  80 */       func_149697_b(par1World, par2, par3, par4, var6, 0);
/*  81 */       par1World.func_147468_f(par2, par3, par4);
/*     */     }
/*     */     
/*  84 */     super.func_149695_a(par1World, par2, par3, par4, par5);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149707_d(World par1World, int par2, int par3, int par4, int par5)
/*     */   {
/*  90 */     return func_149742_c(par1World, par2, par3 - 1, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149720_d(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
/*     */   {
/*  96 */     int md = par1iBlockAccess.func_72805_g(par2, par3, par4);
/*  97 */     return thaumcraft.common.lib.utils.Utils.colors[md];
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 103 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149719_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 109 */     func_149676_a(0.375F, 0.0F, 0.375F, 0.625F, 0.5F, 0.625F);
/* 110 */     super.func_149719_a(par1iBlockAccess, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/* 115 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/* 121 */     return null;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 126 */     return ConfigBlocks.blockCandleRI;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 132 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 138 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/* 144 */     double var7 = par2 + 0.5F;
/* 145 */     double var9 = par3 + 0.7F;
/* 146 */     double var11 = par4 + 0.5F;
/*     */     
/* 148 */     par1World.func_72869_a("smoke", var7, var9, var11, 0.0D, 0.0D, 0.0D);
/* 149 */     par1World.func_72869_a("flame", var7, var9, var11, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canStabaliseInfusion(World world, int x, int y, int z)
/*     */   {
/* 155 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCandle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */