/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.tiles.TileMirror;
/*     */ import thaumcraft.common.tiles.TileMirrorEssentia;
/*     */ 
/*     */ public class BlockMirror extends BlockContainer
/*     */ {
/*     */   public IIcon icon;
/*     */   public IIcon iconEss;
/*     */   
/*     */   public BlockMirror()
/*     */   {
/*  35 */     super(net.minecraft.block.material.Material.field_151592_s);
/*  36 */     func_149711_c(1.0F);
/*  37 */     func_149752_b(10.0F);
/*  38 */     func_149672_a(new CustomStepSound("jar", 0.5F, 2.0F));
/*  39 */     func_149647_a(thaumcraft.common.Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  47 */     this.icon = ir.func_94245_a("thaumcraft:mirrorframe");
/*  48 */     this.iconEss = ir.func_94245_a("thaumcraft:mirrorframe2");
/*     */   }
/*     */   
/*     */   public IIcon func_149691_a(int i, int m)
/*     */   {
/*  53 */     return m < 6 ? this.icon : this.iconEss;
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/*  58 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  65 */     par3List.add(new ItemStack(par1, 1, 0));
/*  66 */     par3List.add(new ItemStack(par1, 1, 6));
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/*  71 */     if (metadata <= 5) new TileMirror();
/*  72 */     if ((metadata > 5) && (metadata <= 11)) return new TileMirrorEssentia();
/*  73 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/*  78 */     return new TileMirror();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  89 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  95 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
/*     */   {
/* 102 */     func_149697_b(par1World, par2, par3, par4, par5, 0);
/* 103 */     super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*     */   {
/* 108 */     ArrayList<ItemStack> drops = new ArrayList();
/* 109 */     int md = world.func_72805_g(x, y, z);
/* 110 */     if (md < 6) {
/* 111 */       TileMirror tm = (TileMirror)world.func_147438_o(x, y, z);
/* 112 */       ItemStack drop = new ItemStack(this, 1, 0);
/* 113 */       if ((tm != null) && ((tm instanceof TileMirror))) {
/* 114 */         if (tm.linked) {
/* 115 */           drop.func_77983_a("linkX", new NBTTagInt(tm.linkX));
/* 116 */           drop.func_77983_a("linkY", new NBTTagInt(tm.linkY));
/* 117 */           drop.func_77983_a("linkZ", new NBTTagInt(tm.linkZ));
/* 118 */           drop.func_77983_a("linkDim", new NBTTagInt(tm.linkDim));
/* 119 */           drop.func_77983_a("dimname", new NBTTagString(DimensionManager.getProvider(world.field_73011_w.field_76574_g).func_80007_l()));
/* 120 */           drop.func_77964_b(1);
/* 121 */           tm.invalidateLink();
/*     */         }
/* 123 */         drops.add(drop);
/*     */       }
/* 125 */       return drops;
/*     */     }
/* 127 */     TileMirrorEssentia tm = (TileMirrorEssentia)world.func_147438_o(x, y, z);
/* 128 */     ItemStack drop = new ItemStack(this, 1, 6);
/* 129 */     if ((tm != null) && ((tm instanceof TileMirrorEssentia))) {
/* 130 */       if (tm.linked) {
/* 131 */         drop.func_77983_a("linkX", new NBTTagInt(tm.linkX));
/* 132 */         drop.func_77983_a("linkY", new NBTTagInt(tm.linkY));
/* 133 */         drop.func_77983_a("linkZ", new NBTTagInt(tm.linkZ));
/* 134 */         drop.func_77983_a("linkDim", new NBTTagInt(tm.linkDim));
/* 135 */         drop.func_77983_a("dimname", new NBTTagString(DimensionManager.getProvider(world.field_73011_w.field_76574_g).func_80007_l()));
/* 136 */         drop.func_77964_b(7);
/* 137 */         tm.invalidateLink();
/*     */       }
/* 139 */       drops.add(drop);
/*     */     }
/* 141 */     return drops;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 148 */     int md = world.func_72805_g(x, y, z);
/* 149 */     if ((md < 6) && (!world.field_72995_K) && ((entity instanceof EntityItem)) && (!entity.field_70128_L) && (((EntityItem)entity).field_71088_bW == 0))
/*     */     {
/* 151 */       TileMirror taf = (TileMirror)world.func_147438_o(x, y, z);
/* 152 */       if (taf != null) {
/* 153 */         taf.transport((EntityItem)entity);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149660_a(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
/*     */   {
/* 161 */     if (par9 > 6) { par9 = 6;
/*     */     }
/* 163 */     else if ((par9 > 0) && (par9 < 6)) { par9 = 0;
/*     */     }
/* 165 */     return par9 + par5;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int i, int j, int k, Block l)
/*     */   {
/* 170 */     if (!world.field_72995_K)
/*     */     {
/* 172 */       int i1 = world.func_72805_g(i, j, k);
/* 173 */       boolean flag = false;
/* 174 */       if ((!world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation(5))) && (i1 % 6 == 5)) flag = true;
/* 175 */       if ((!world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation(4))) && (i1 % 6 == 4)) flag = true;
/* 176 */       if ((!world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation(3))) && (i1 % 6 == 3)) flag = true;
/* 177 */       if ((!world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation(2))) && (i1 % 6 == 2)) flag = true;
/* 178 */       if ((!world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation(1))) && (i1 % 6 == 1)) flag = true;
/* 179 */       if ((!world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation(0))) && (i1 % 6 == 0)) flag = true;
/* 180 */       if (flag)
/*     */       {
/* 182 */         func_149697_b(world, i, j, k, i1, 0);
/* 183 */         world.func_147468_f(i, j, k);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean checkIfAttachedToBlock(World world, int i, int j, int k)
/*     */   {
/* 190 */     if (!func_149742_c(world, i, j, k))
/*     */     {
/* 192 */       return false;
/*     */     }
/*     */     
/* 195 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149707_d(World world, int i, int j, int k, int l)
/*     */   {
/* 202 */     if ((l == 0) && (world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation(0)))) return true;
/* 203 */     if ((l == 1) && (world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation(1)))) return true;
/* 204 */     if ((l == 2) && (world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation(2)))) return true;
/* 205 */     if ((l == 3) && (world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation(3)))) return true;
/* 206 */     if ((l == 4) && (world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation(4)))) return true;
/* 207 */     return (l == 5) && (world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation(5)));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149742_c(World world, int i, int j, int k)
/*     */   {
/* 213 */     if (world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation(5))) return true;
/* 214 */     if (world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation(4))) return true;
/* 215 */     if (world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation(3))) return true;
/* 216 */     if (world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation(2))) return true;
/* 217 */     if (world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation(1))) return true;
/* 218 */     return world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
/*     */   {
/* 226 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/* 233 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4)
/*     */   {
/* 239 */     func_149719_a(par1World, par2, par3, par4);
/* 240 */     return super.func_149633_g(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 246 */     setBlockBoundsForBlockRender(par1IBlockAccess.func_72805_g(par2, par3, par4));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void setBlockBoundsForBlockRender(int par1)
/*     */   {
/* 258 */     float w = 0.0625F;
/* 259 */     switch (par1 % 6) {
/* 260 */     case 0:  func_149676_a(0.0F, 1.0F - w, 0.0F, 1.0F, 1.0F, 1.0F); break;
/* 261 */     case 1:  func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, w, 1.0F); break;
/* 262 */     case 2:  func_149676_a(0.0F, 0.0F, 1.0F - w, 1.0F, 1.0F, 1.0F); break;
/* 263 */     case 3:  func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, w); break;
/* 264 */     case 4:  func_149676_a(1.0F - w, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F); break;
/* 265 */     case 5:  func_149676_a(0.0F, 0.0F, 0.0F, w, 1.0F, 1.0F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */