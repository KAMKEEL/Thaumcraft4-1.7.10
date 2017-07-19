/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileOwned;
/*     */ 
/*     */ public class BlockCosmeticOpaque extends BlockContainer
/*     */ {
/*     */   public BlockCosmeticOpaque()
/*     */   {
/*  36 */     super(Material.field_151576_e);
/*  37 */     func_149752_b(5.0F);
/*  38 */     func_149711_c(1.5F);
/*  39 */     func_149672_a(Block.field_149769_e);
/*  40 */     func_149647_a(Thaumcraft.tabTC);
/*  41 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*  44 */   public IIcon[] icon = new IIcon[3];
/*     */   
/*  46 */   public static IIcon[] wardedGlassIcon = new IIcon[47];
/*     */   
/*     */   public int currentPass;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  53 */     this.icon[0] = ir.func_94245_a("thaumcraft:amberblock");
/*  54 */     this.icon[1] = ir.func_94245_a("thaumcraft:amberbrick");
/*  55 */     this.icon[2] = ir.func_94245_a("thaumcraft:amberblock_top");
/*  56 */     for (int a = 0; a < 47; a++) {
/*  57 */       wardedGlassIcon[a] = ir.func_94245_a("thaumcraft:warded_glass_" + (a + 1));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/*  63 */     if ((par2 == 0) && (par1 < 2)) return this.icon[2];
/*  64 */     if (par2 == 2) return wardedGlassIcon[0];
/*  65 */     return this.icon[par2];
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  70 */     return ConfigBlocks.blockCosmeticOpaqueRI;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
/*     */   {
/*  78 */     int md = worldObj.func_72805_g(target.field_72311_b, target.field_72312_c, target.field_72309_d);
/*  79 */     if (md == 2) {
/*  80 */       float f = (float)target.field_72307_f.field_72450_a - target.field_72311_b;
/*  81 */       float f1 = (float)target.field_72307_f.field_72448_b - target.field_72312_c;
/*  82 */       float f2 = (float)target.field_72307_f.field_72449_c - target.field_72309_d;
/*  83 */       Thaumcraft.proxy.blockWard(worldObj, target.field_72311_b, target.field_72312_c, target.field_72309_d, ForgeDirection.getOrientation(target.field_72310_e), f, f1, f2);
/*     */       
/*  85 */       return true; }
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/*  92 */     int md = world.func_72805_g(x, y, z);
/*  93 */     if (md == 2) {
/*  94 */       boolean[] bitMatrix = new boolean[8];
/*  95 */       if ((side == 0) || (side == 1)) {
/*  96 */         bitMatrix[0] = ((world.func_147439_a(x - 1, y, z - 1) == this) && (world.func_72805_g(x - 1, y, z - 1) == 2) ? 1 : false);
/*  97 */         bitMatrix[1] = ((world.func_147439_a(x, y, z - 1) == this) && (world.func_72805_g(x, y, z - 1) == 2) ? 1 : false);
/*  98 */         bitMatrix[2] = ((world.func_147439_a(x + 1, y, z - 1) == this) && (world.func_72805_g(x + 1, y, z - 1) == 2) ? 1 : false);
/*  99 */         bitMatrix[3] = ((world.func_147439_a(x - 1, y, z) == this) && (world.func_72805_g(x - 1, y, z) == 2) ? 1 : false);
/* 100 */         bitMatrix[4] = ((world.func_147439_a(x + 1, y, z) == this) && (world.func_72805_g(x + 1, y, z) == 2) ? 1 : false);
/* 101 */         bitMatrix[5] = ((world.func_147439_a(x - 1, y, z + 1) == this) && (world.func_72805_g(x - 1, y, z + 1) == 2) ? 1 : false);
/* 102 */         bitMatrix[6] = ((world.func_147439_a(x, y, z + 1) == this) && (world.func_72805_g(x, y, z + 1) == 2) ? 1 : false);
/* 103 */         bitMatrix[7] = ((world.func_147439_a(x + 1, y, z + 1) == this) && (world.func_72805_g(x + 1, y, z + 1) == 2) ? 1 : false);
/*     */       }
/* 105 */       if ((side == 2) || (side == 3)) {
/* 106 */         if (world.func_147439_a(x + (side == 2 ? 1 : -1), y + 1, z) == this) {} bitMatrix[0] = (world.func_72805_g(x + (side == 2 ? 1 : -1), y + 1, z) == 2 ? 1 : false);
/* 107 */         bitMatrix[1] = ((world.func_147439_a(x, y + 1, z) == this) && (world.func_72805_g(x, y + 1, z) == 2) ? 1 : false);
/* 108 */         if (world.func_147439_a(x + (side == 3 ? 1 : -1), y + 1, z) == this) {} bitMatrix[2] = (world.func_72805_g(x + (side == 3 ? 1 : -1), y + 1, z) == 2 ? 1 : false);
/* 109 */         if (world.func_147439_a(x + (side == 2 ? 1 : -1), y, z) == this) {} bitMatrix[3] = (world.func_72805_g(x + (side == 2 ? 1 : -1), y, z) == 2 ? 1 : false);
/* 110 */         if (world.func_147439_a(x + (side == 3 ? 1 : -1), y, z) == this) {} bitMatrix[4] = (world.func_72805_g(x + (side == 3 ? 1 : -1), y, z) == 2 ? 1 : false);
/* 111 */         if (world.func_147439_a(x + (side == 2 ? 1 : -1), y - 1, z) == this) {} bitMatrix[5] = (world.func_72805_g(x + (side == 2 ? 1 : -1), y - 1, z) == 2 ? 1 : false);
/* 112 */         bitMatrix[6] = ((world.func_147439_a(x, y - 1, z) == this) && (world.func_72805_g(x, y - 1, z) == 2) ? 1 : false);
/* 113 */         if (world.func_147439_a(x + (side == 3 ? 1 : -1), y - 1, z) == this) {} bitMatrix[7] = (world.func_72805_g(x + (side == 3 ? 1 : -1), y - 1, z) == 2 ? 1 : false);
/*     */       }
/* 115 */       if ((side == 4) || (side == 5)) {
/* 116 */         if (world.func_147439_a(x, y + 1, z + (side == 5 ? 1 : -1)) == this) {} bitMatrix[0] = (world.func_72805_g(x, y + 1, z + (side == 5 ? 1 : -1)) == 2 ? 1 : false);
/* 117 */         bitMatrix[1] = ((world.func_147439_a(x, y + 1, z) == this) && (world.func_72805_g(x, y + 1, z) == 2) ? 1 : false);
/* 118 */         if (world.func_147439_a(x, y + 1, z + (side == 4 ? 1 : -1)) == this) {} bitMatrix[2] = (world.func_72805_g(x, y + 1, z + (side == 4 ? 1 : -1)) == 2 ? 1 : false);
/* 119 */         if (world.func_147439_a(x, y, z + (side == 5 ? 1 : -1)) == this) {} bitMatrix[3] = (world.func_72805_g(x, y, z + (side == 5 ? 1 : -1)) == 2 ? 1 : false);
/* 120 */         if (world.func_147439_a(x, y, z + (side == 4 ? 1 : -1)) == this) {} bitMatrix[4] = (world.func_72805_g(x, y, z + (side == 4 ? 1 : -1)) == 2 ? 1 : false);
/* 121 */         if (world.func_147439_a(x, y - 1, z + (side == 5 ? 1 : -1)) == this) {} bitMatrix[5] = (world.func_72805_g(x, y - 1, z + (side == 5 ? 1 : -1)) == 2 ? 1 : false);
/* 122 */         bitMatrix[6] = ((world.func_147439_a(x, y - 1, z) == this) && (world.func_72805_g(x, y - 1, z) == 2) ? 1 : false);
/* 123 */         if (world.func_147439_a(x, y - 1, z + (side == 4 ? 1 : -1)) == this) {} bitMatrix[7] = (world.func_72805_g(x, y - 1, z + (side == 4 ? 1 : -1)) == 2 ? 1 : false);
/*     */       }
/* 125 */       int idBuilder = 0;
/* 126 */       for (int i = 0; i <= 7; i++) {
/* 127 */         idBuilder += (bitMatrix[i] != 0 ? 128 : i == 6 ? 64 : i == 5 ? 32 : i == 4 ? 16 : i == 3 ? 8 : i == 2 ? 4 : i == 1 ? 2 : i == 0 ? 1 : 0);
/*     */       }
/*     */       
/* 130 */       return (idBuilder > 255) || (idBuilder < 0) ? wardedGlassIcon[0] : wardedGlassIcon[thaumcraft.client.lib.UtilsFX.connectedTextureRefByID[idBuilder]];
/*     */     }
/*     */     
/* 133 */     return super.func_149673_e(world, x, y, z, side);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149701_w()
/*     */   {
/* 139 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean canRenderInPass(int pass)
/*     */   {
/* 144 */     this.currentPass = pass;
/* 145 */     return (pass == 1) || (pass == 0);
/*     */   }
/*     */   
/*     */   public int getLightOpacity(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 150 */     int md = world.func_72805_g(x, y, z);
/* 151 */     if (md <= 1) return 3;
/* 152 */     return super.getLightOpacity(world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 157 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/* 162 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 168 */     par3List.add(new ItemStack(par1, 1, 0));
/* 169 */     par3List.add(new ItemStack(par1, 1, 1));
/* 170 */     par3List.add(new ItemStack(par1, 1, 2));
/*     */   }
/*     */   
/*     */   public boolean isNormalCube(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 175 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/* 181 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/* 188 */     Block block = world.func_147439_a(x, y, z);
/*     */     
/* 190 */     if (world.func_72805_g(x, y, z) != world.func_72805_g(x - net.minecraft.util.Facing.field_71586_b[side], y - net.minecraft.util.Facing.field_71587_c[side], z - net.minecraft.util.Facing.field_71585_d[side]))
/*     */     {
/* 192 */       return true;
/*     */     }
/*     */     
/* 195 */     if (block == this)
/*     */     {
/* 197 */       return false;
/*     */     }
/*     */     
/* 200 */     return super.func_149646_a(world, x, y, z, side);
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random par1Random)
/*     */   {
/* 205 */     return 1;
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 210 */     return par1;
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 215 */     if (metadata == 2) return new TileOwned();
/* 216 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
/*     */   {
/* 221 */     int md = world.func_72805_g(x, y, z);
/* 222 */     if (md == 2) return false;
/* 223 */     return super.canEntityDestroy(world, x, y, z, entity);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
/*     */   {
/* 229 */     int md = world.func_72805_g(x, y, z);
/* 230 */     if (md != 2) { super.onBlockExploded(world, x, y, z, explosion);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149659_a(Explosion explosion)
/*     */   {
/* 236 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149689_a(World w, int x, int y, int z, EntityLivingBase p, ItemStack is)
/*     */   {
/* 242 */     TileEntity tile = w.func_147438_o(x, y, z);
/* 243 */     if ((tile != null) && ((tile instanceof TileOwned)) && ((p instanceof EntityPlayer))) {
/* 244 */       ((TileOwned)tile).owner = ((EntityPlayer)p).func_70005_c_();
/* 245 */       tile.func_70296_d();
/*     */     }
/* 247 */     super.func_149689_a(w, x, y, z, p, is);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int var2)
/*     */   {
/* 252 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_149712_f(World world, int x, int y, int z)
/*     */   {
/* 258 */     int md = world.func_72805_g(x, y, z);
/* 259 */     if (md == 2) { return Config.wardedStone ? -1.0F : 5.0F;
/*     */     }
/* 261 */     return super.func_149712_f(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */   public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
/*     */   {
/* 267 */     int md = world.func_72805_g(x, y, z);
/* 268 */     if (md == 2) return 999.0F;
/* 269 */     return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCosmeticOpaque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */