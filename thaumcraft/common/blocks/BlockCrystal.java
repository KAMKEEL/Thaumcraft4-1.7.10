/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.crafting.IInfusionStabiliser;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXSpark;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.tiles.TileCrystal;
/*     */ import thaumcraft.common.tiles.TileEldritchCrystal;
/*     */ 
/*     */ public class BlockCrystal
/*     */   extends BlockContainer implements IInfusionStabiliser
/*     */ {
/*  33 */   private Random random = new Random();
/*     */   public IIcon icon;
/*     */   
/*     */   public BlockCrystal() {
/*  37 */     super(Material.field_151592_s);
/*  38 */     func_149711_c(0.7F);
/*  39 */     func_149752_b(1.0F);
/*  40 */     func_149715_a(0.5F);
/*  41 */     func_149672_a(new CustomStepSound("crystal", 1.0F, 1.0F));
/*  42 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  49 */     for (int var4 = 0; var4 <= 6; var4++)
/*     */     {
/*  51 */       par3List.add(new ItemStack(par1, 1, var4));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  59 */     this.icon = ir.func_94245_a("thaumcraft:crystal");
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/*  65 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int i, int j, int k, Random random)
/*     */   {
/*  72 */     int md = world.func_72805_g(i, j, k);
/*  73 */     if ((md <= 6) && (random.nextInt(17) == 0)) {
/*  74 */       FXSpark ef = new FXSpark(world, i + 0.3D + world.field_73012_v.nextFloat() * 0.4F, j + 0.3D + world.field_73012_v.nextFloat() * 0.4F, k + 0.3D + world.field_73012_v.nextFloat() * 0.4F, 0.2F + random.nextFloat() * 0.1F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  79 */       Color c = new Color(md == 6 ? BlockCustomOreItem.colors[(random.nextInt(6) + 1)] : BlockCustomOreItem.colors[(md + 1)]);
/*     */       
/*  81 */       ef.func_70538_b(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
/*  82 */       ef.func_82338_g(0.8F);
/*  83 */       ParticleEngine.instance.addEffect(world, ef);
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_149720_d(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
/*     */   {
/*  89 */     int md = par1iBlockAccess.func_72805_g(par2, par3, par4);
/*  90 */     if (md < 6) return BlockCustomOreItem.colors[(md + 1)];
/*  91 */     if (md == 6) return BlockCustomOreItem.colors[(new Random().nextInt(6) + 1)];
/*  92 */     return super.func_149720_d(par1iBlockAccess, par2, par3, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  98 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 106 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 111 */     return ConfigBlocks.blockCrystalRI;
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 116 */     if (metadata <= 6) return new TileCrystal();
/* 117 */     if (metadata == 7) return new TileEldritchCrystal();
/* 118 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 123 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 130 */     return par1;
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int md, int fortune)
/*     */   {
/* 135 */     ArrayList<ItemStack> ret = new ArrayList();
/* 136 */     if (md < 6) {
/* 137 */       for (int t = 0; t < 6; t++) {
/* 138 */         ret.add(new ItemStack(ConfigItems.itemShard, 1, md));
/*     */       }
/* 140 */       return ret;
/*     */     }
/* 142 */     if (md == 6) {
/* 143 */       for (int t = 0; t < 6; t++) {
/* 144 */         ret.add(new ItemStack(ConfigItems.itemShard, 1, t));
/*     */       }
/* 146 */       return ret;
/*     */     }
/*     */     
/* 149 */     if (md == 7) {
/* 150 */       ret.add(new ItemStack(ConfigItems.itemShard, 1, 6));
/* 151 */       return ret;
/*     */     }
/*     */     
/* 154 */     return super.getDrops(world, x, y, z, md, fortune);
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int i, int j, int k, Block l)
/*     */   {
/* 159 */     super.func_149695_a(world, i, j, k, l);
/* 160 */     int md = world.func_72805_g(i, j, k);
/* 161 */     if ((md <= 6) && (checkIfAttachedToBlock(world, i, j, k)))
/*     */     {
/* 163 */       TileCrystal tes = (TileCrystal)world.func_147438_o(i, j, k);
/* 164 */       int i1 = tes.orientation;
/* 165 */       boolean flag = false;
/* 166 */       if ((!world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation(5))) && (i1 == 5)) flag = true;
/* 167 */       if ((!world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation(4))) && (i1 == 4)) flag = true;
/* 168 */       if ((!world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation(3))) && (i1 == 3)) flag = true;
/* 169 */       if ((!world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation(2))) && (i1 == 2)) flag = true;
/* 170 */       if ((!world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation(1))) && (i1 == 1)) flag = true;
/* 171 */       if ((!world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation(0))) && (i1 == 0)) flag = true;
/* 172 */       if (flag)
/*     */       {
/* 174 */         func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 0);
/* 175 */         world.func_147468_f(i, j, k);
/*     */       }
/* 177 */       return;
/*     */     }
/* 179 */     if (md == 7)
/*     */     {
/* 181 */       TileCrystal tes = (TileCrystal)world.func_147438_o(i, j, k);
/* 182 */       ForgeDirection fd = ForgeDirection.getOrientation(tes.orientation).getOpposite();
/*     */       
/* 184 */       if (world.func_147437_c(i + fd.offsetX, j + fd.offsetY, k + fd.offsetZ))
/*     */       {
/* 186 */         func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 0);
/* 187 */         world.func_147468_f(i, j, k);
/*     */       }
/* 189 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean checkIfAttachedToBlock(World world, int i, int j, int k)
/*     */   {
/* 195 */     if (!func_149742_c(world, i, j, k))
/*     */     {
/* 197 */       func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 0);
/* 198 */       world.func_147468_f(i, j, k);
/* 199 */       return false;
/*     */     }
/*     */     
/* 202 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149707_d(World world, int i, int j, int k, int l)
/*     */   {
/* 209 */     if ((l == 0) && (world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation(0)))) return true;
/* 210 */     if ((l == 1) && (world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation(1)))) return true;
/* 211 */     if ((l == 2) && (world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation(2)))) return true;
/* 212 */     if ((l == 3) && (world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation(3)))) return true;
/* 213 */     if ((l == 4) && (world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation(4)))) return true;
/* 214 */     return (l == 5) && (world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation(5)));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149742_c(World world, int i, int j, int k)
/*     */   {
/* 220 */     if (world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation(5))) return true;
/* 221 */     if (world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation(4))) return true;
/* 222 */     if (world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation(3))) return true;
/* 223 */     if (world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation(2))) return true;
/* 224 */     if (world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation(1))) return true;
/* 225 */     return world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation(0));
/*     */   }
/*     */   
/*     */   public boolean canStabaliseInfusion(World world, int x, int y, int z)
/*     */   {
/* 230 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCrystal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */