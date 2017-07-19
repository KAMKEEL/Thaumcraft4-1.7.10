/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileEssentiaReservoir;
/*     */ 
/*     */ public class BlockEssentiaReservoir extends BlockContainer
/*     */ {
/*     */   public BlockEssentiaReservoir()
/*     */   {
/*  23 */     super(net.minecraft.block.material.Material.field_151573_f);
/*  24 */     func_149711_c(2.0F);
/*  25 */     func_149752_b(17.0F);
/*  26 */     func_149672_a(Block.field_149777_j);
/*  27 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  28 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*  31 */   public IIcon icon = null;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir) {
/*  35 */     this.icon = ir.func_94245_a("thaumcraft:essentiareservoir");
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon func_149691_a(int i, int md)
/*     */   {
/*  41 */     return this.icon;
/*     */   }
/*     */   
/*     */   public IIcon func_149673_e(IBlockAccess iblockaccess, int i, int j, int k, int side)
/*     */   {
/*  46 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  52 */     return ConfigBlocks.blockEssentiaReservoirRI;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  57 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149701_w()
/*     */   {
/*  68 */     return 1;
/*     */   }
/*     */   
/*     */   public int func_149692_a(int metadata)
/*     */   {
/*  73 */     return metadata;
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/*  78 */     if (metadata == 0) return new TileEssentiaReservoir();
/*  79 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/*  89 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149736_g(World world, int x, int y, int z, int rs)
/*     */   {
/*  95 */     TileEntity te = world.func_147438_o(x, y, z);
/*  96 */     if ((te != null) && ((te instanceof TileEssentiaReservoir))) {
/*  97 */       float r = ((TileEssentiaReservoir)te).essentia.visSize() / ((TileEssentiaReservoir)te).maxAmount;
/*  98 */       return MathHelper.func_76141_d(r * 14.0F) + (((TileEssentiaReservoir)te).essentia.visSize() > 0 ? 1 : 0);
/*     */     }
/* 100 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149749_a(World world, int x, int y, int z, Block par5, int par6)
/*     */   {
/* 106 */     TileEntity te = world.func_147438_o(x, y, z);
/* 107 */     if ((te != null) && ((te instanceof TileEssentiaReservoir))) {
/* 108 */       int sz = ((TileEssentiaReservoir)te).essentia.visSize() / 16;
/* 109 */       int q = 0;
/* 110 */       if (sz > 0) {
/* 111 */         world.func_72876_a(null, x + 0.5D, y + 0.5D, z + 0.5D, 1.0F, false);
/* 112 */         for (int a = 0; a < 50; a++) {
/* 113 */           int xx = x + world.field_73012_v.nextInt(5) - world.field_73012_v.nextInt(5);
/* 114 */           int yy = y + world.field_73012_v.nextInt(5) - world.field_73012_v.nextInt(5);
/* 115 */           int zz = z + world.field_73012_v.nextInt(5) - world.field_73012_v.nextInt(5);
/* 116 */           if (world.func_147437_c(xx, yy, zz)) {
/* 117 */             if (yy < y) {
/* 118 */               world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGoo, 8, 3);
/*     */             } else {
/* 120 */               world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGas, 8, 3);
/*     */             }
/* 122 */             if (q++ >= sz) break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 127 */     super.func_149749_a(world, x, y, z, par5, par6);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockEssentiaReservoir.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */