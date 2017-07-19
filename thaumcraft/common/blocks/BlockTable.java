/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileArcaneWorkbench;
/*     */ import thaumcraft.common.tiles.TileDeconstructionTable;
/*     */ import thaumcraft.common.tiles.TileResearchTable;
/*     */ import thaumcraft.common.tiles.TileTable;
/*     */ 
/*     */ public class BlockTable extends BlockContainer implements thaumcraft.api.wands.IWandable
/*     */ {
/*     */   public IIcon icon;
/*     */   public IIcon iconQuill;
/*     */   
/*     */   public BlockTable()
/*     */   {
/*  38 */     super(net.minecraft.block.material.Material.field_151575_d);
/*  39 */     func_149711_c(2.5F);
/*  40 */     func_149672_a(field_149766_f);
/*  41 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  49 */     this.icon = ir.func_94245_a("thaumcraft:woodplain");
/*  50 */     this.iconQuill = ir.func_94245_a("thaumcraft:tablequill");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  55 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/*  61 */     if (side == ForgeDirection.UP) return true;
/*  62 */     return super.isSideSolid(world, x, y, z, side);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  68 */     par3List.add(new ItemStack(par1, 1, 0));
/*  69 */     par3List.add(new ItemStack(par1, 1, 14));
/*  70 */     par3List.add(new ItemStack(par1, 1, 15));
/*     */   }
/*     */   
/*     */ 
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/*  76 */     if (((metadata <= 1) || (metadata >= 6)) && (metadata < 14))
/*  77 */       return new TileTable();
/*  78 */     if (metadata == 14)
/*  79 */       return new TileDeconstructionTable();
/*  80 */     if (metadata == 15) {
/*  81 */       return new TileArcaneWorkbench();
/*     */     }
/*  83 */     return new TileResearchTable();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack is)
/*     */   {
/*  90 */     int md = par1World.func_72805_g(par2, par3, par4);
/*  91 */     if (md < 14) {
/*  92 */       int var7 = MathHelper.func_76128_c(par5EntityLiving.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*     */       
/*  94 */       int out = var7 == 3 ? 1 : var7 == 2 ? 0 : var7 == 1 ? 1 : var7 == 0 ? 0 : 0;
/*     */       
/*  96 */       par1World.func_147465_d(par2, par3, par4, this, out, 3);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 103 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 109 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 114 */     return ConfigBlocks.blockTableRI;
/*     */   }
/*     */   
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/* 119 */     InventoryUtils.dropItems(par1World, par2, par3, par4);
/* 120 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */   public int func_149643_k(World par1World, int par2, int par3, int par4)
/*     */   {
/* 125 */     int md = par1World.func_72805_g(par2, par3, par4);
/* 126 */     if ((md >= 2) && (md <= 9)) return 2;
/* 127 */     return super.func_149643_k(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 132 */     if (par1 == 14) return 14;
/* 133 */     if (par1 == 15) { return 15;
/*     */     }
/* 135 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block par5)
/*     */   {
/* 140 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 141 */     int md = world.func_72805_g(x, y, z);
/*     */     
/* 143 */     if ((tile != null) && ((tile instanceof TileResearchTable))) {
/* 144 */       int mm = world.func_72805_g(x + ForgeDirection.getOrientation(md).offsetX, y + ForgeDirection.getOrientation(md).offsetY, z + ForgeDirection.getOrientation(md).offsetZ);
/*     */       
/*     */ 
/*     */ 
/* 148 */       if (mm < 6) {
/* 149 */         InventoryUtils.dropItems(world, x, y, z);
/* 150 */         world.func_147455_a(x, y, z, new TileTable());
/* 151 */         world.func_147465_d(x, y, z, this, 0, 3);
/*     */       }
/*     */       
/*     */     }
/* 155 */     else if ((md >= 6) && (md < 14)) {
/* 156 */       TileEntity tile2 = world.func_147438_o(x + ForgeDirection.getOrientation(md - 4).offsetX, y + ForgeDirection.getOrientation(md - 4).offsetY, z + ForgeDirection.getOrientation(md - 4).offsetZ);
/*     */       
/*     */ 
/*     */ 
/* 160 */       if ((tile2 == null) || (!(tile2 instanceof TileResearchTable))) {
/* 161 */         world.func_147465_d(x, y, z, this, 0, 3);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 166 */     super.func_149695_a(world, x, y, z, par5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
/*     */   {
/* 173 */     TileEntity tileEntity = world.func_147438_o(x, y, z);
/* 174 */     int md = world.func_72805_g(x, y, z);
/*     */     
/* 176 */     if ((md <= 1) || (tileEntity == null) || (player.func_70093_af())) {
/* 177 */       return false;
/*     */     }
/* 179 */     if (world.field_72995_K) return true;
/* 180 */     if ((tileEntity instanceof TileArcaneWorkbench)) {
/* 181 */       player.openGui(Thaumcraft.instance, 13, world, x, y, z);
/* 182 */       return true;
/*     */     }
/* 184 */     if ((tileEntity instanceof TileDeconstructionTable)) {
/* 185 */       player.openGui(Thaumcraft.instance, 8, world, x, y, z);
/* 186 */       return true;
/*     */     }
/* 188 */     if ((tileEntity instanceof TileResearchTable)) {
/* 189 */       player.openGui(Thaumcraft.instance, 10, world, x, y, z);
/*     */     } else {
/* 191 */       for (int a = 2; a < 6; a++) {
/* 192 */         TileEntity tile = world.func_147438_o(x + ForgeDirection.getOrientation(a).offsetX, y + ForgeDirection.getOrientation(a).offsetY, z + ForgeDirection.getOrientation(a).offsetZ);
/*     */         
/*     */ 
/*     */ 
/* 196 */         if ((tile != null) && ((tile instanceof TileResearchTable))) {
/* 197 */           player.openGui(Thaumcraft.instance, 10, world, x + ForgeDirection.getOrientation(a).offsetX, y + ForgeDirection.getOrientation(a).offsetY, z + ForgeDirection.getOrientation(a).offsetZ);
/*     */           
/*     */ 
/*     */ 
/* 201 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 205 */     return true;
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 210 */     return null;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z)
/*     */   {
/* 215 */     int md = world.func_72805_g(x, y, z);
/* 216 */     switch (md) {
/* 217 */     case 5: case 9:  return AxisAlignedBB.func_72330_a(x + this.field_149759_B, y + this.field_149760_C, z + this.field_149754_D, x + this.field_149755_E + 1.0D, y + this.field_149756_F, z + this.field_149757_G);
/*     */     case 4: 
/*     */     case 8: 
/* 220 */       return AxisAlignedBB.func_72330_a(x + this.field_149759_B - 1.0D, y + this.field_149760_C, z + this.field_149754_D, x + this.field_149755_E, y + this.field_149756_F, z + this.field_149757_G);
/*     */     case 3: 
/*     */     case 7: 
/* 223 */       return AxisAlignedBB.func_72330_a(x + this.field_149759_B, y + this.field_149760_C, z + this.field_149754_D, x + this.field_149755_E, y + this.field_149756_F, z + this.field_149757_G + 1.0D);
/*     */     case 2: 
/*     */     case 6: 
/* 226 */       return AxisAlignedBB.func_72330_a(x + this.field_149759_B, y + this.field_149760_C, z + this.field_149754_D - 1.0D, x + this.field_149755_E, y + this.field_149756_F, z + this.field_149757_G);
/*     */     }
/*     */     
/*     */     
/*     */ 
/* 231 */     return super.func_149633_g(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/* 240 */     if (md <= 1) {
/* 241 */       ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
/* 242 */       world.func_147465_d(x, y, z, ConfigBlocks.blockTable, 15, 3);
/* 243 */       world.func_147455_a(x, y, z, new TileArcaneWorkbench());
/* 244 */       TileArcaneWorkbench tawb = (TileArcaneWorkbench)world.func_147438_o(x, y, z);
/* 245 */       if ((tawb != null) && (!wand.isStaff(wandstack))) {
/* 246 */         tawb.func_70299_a(10, wandstack.func_77946_l());
/* 247 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */       }
/* 249 */       tawb.func_70296_d();
/* 250 */       world.func_147471_g(x, y, z);
/*     */       
/* 252 */       world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.15F, 0.5F);
/* 253 */       return 0;
/*     */     }
/* 255 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*     */   {
/* 261 */     return null;
/*     */   }
/*     */   
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */