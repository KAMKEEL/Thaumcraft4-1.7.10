/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EnumCreatureType;
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
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXSpark;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.ItemEldritchObject;
/*     */ import thaumcraft.common.tiles.TileEldritchAltar;
/*     */ import thaumcraft.common.tiles.TileEldritchCap;
/*     */ import thaumcraft.common.tiles.TileEldritchCrabSpawner;
/*     */ import thaumcraft.common.tiles.TileEldritchLock;
/*     */ import thaumcraft.common.tiles.TileEldritchObelisk;
/*     */ import thaumcraft.common.tiles.TileEldritchTrap;
/*     */ 
/*     */ public class BlockEldritch extends BlockContainer
/*     */ {
/*     */   public BlockEldritch()
/*     */   {
/*  42 */     super(Material.field_151576_e);
/*  43 */     func_149752_b(20000.0F);
/*  44 */     func_149711_c(50.0F);
/*  45 */     func_149672_a(field_149769_e);
/*  46 */     func_149675_a(true);
/*  47 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  48 */     func_149713_g(0);
/*  49 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*  52 */   public IIcon icon = null;
/*  53 */   public IIcon[] insIcon = new IIcon[9];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir) {
/*  57 */     this.icon = ir.func_94245_a("thaumcraft:obsidiantile");
/*  58 */     this.insIcon[0] = ir.func_94245_a("thaumcraft:es_i_1");
/*  59 */     this.insIcon[1] = ir.func_94245_a("thaumcraft:es_i_2");
/*  60 */     this.insIcon[2] = ir.func_94245_a("thaumcraft:deco_1");
/*  61 */     this.insIcon[3] = ir.func_94245_a("thaumcraft:deco_2");
/*  62 */     this.insIcon[4] = ir.func_94245_a("thaumcraft:deco_3");
/*  63 */     this.insIcon[5] = ir.func_94245_a("thaumcraft:es_5");
/*  64 */     this.insIcon[6] = ir.func_94245_a("thaumcraft:es_6");
/*  65 */     this.insIcon[7] = ir.func_94245_a("thaumcraft:es_7");
/*  66 */     this.insIcon[8] = ir.func_94245_a("thaumcraft:es_8");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/*  72 */     return meta == 10 ? this.insIcon[5] : meta == 9 ? ConfigBlocks.blockCosmeticSolid.func_149691_a(side, 14) : meta == 8 ? this.insIcon[3] : meta == 7 ? this.insIcon[4] : meta == 6 ? this.insIcon[2] : meta == 5 ? this.insIcon[1] : meta == 4 ? this.insIcon[0] : this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int side)
/*     */   {
/*  84 */     int md = ba.func_72805_g(x, y, z);
/*  85 */     if (md == 8) {
/*  86 */       TileEntity te = ba.func_147438_o(x, y, z);
/*  87 */       if (((te instanceof TileEldritchLock)) && 
/*  88 */         (((TileEldritchLock)te).getFacing() == side)) {
/*  89 */         return this.insIcon[3];
/*     */       }
/*  91 */       return this.insIcon[4];
/*     */     }
/*  93 */     if (md == 10) {
/*  94 */       String l = x + "" + y + "" + z;
/*  95 */       Random r1 = new Random(Math.abs(l.hashCode() * 100) + 1);
/*  96 */       int i = r1.nextInt(12345 + side) % 4;
/*  97 */       return this.insIcon[(5 + i)];
/*     */     }
/*  99 */     return super.func_149673_e(ba, x, y, z, side);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 106 */     par3List.add(new ItemStack(par1, 1, 4));
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 111 */     int meta = world.func_72805_g(x, y, z);
/* 112 */     if ((meta == 4) || (meta == 5) || (meta == 7)) return 12;
/* 113 */     if ((meta == 6) || (meta == 8)) return 5;
/* 114 */     if (meta == 9) return 4;
/* 115 */     if (meta == 10) return 0;
/* 116 */     return 8;
/*     */   }
/*     */   
/*     */   public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
/*     */   {
/* 121 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int i, int j, int k)
/*     */   {
/* 126 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 127 */     super.func_149719_a(world, i, j, k);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
/*     */   {
/* 133 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 134 */     super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */   }
/*     */   
/*     */   public boolean hasTileEntity(int metadata)
/*     */   {
/* 139 */     return (metadata == 0) || (metadata == 1) || (metadata == 3) || (metadata == 8) || (metadata == 9) || (metadata == 10);
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 144 */     if (metadata == 0) return new TileEldritchAltar();
/* 145 */     if (metadata == 1) return new TileEldritchObelisk();
/* 146 */     if (metadata == 3) return new TileEldritchCap();
/* 147 */     if (metadata == 8) return new TileEldritchLock();
/* 148 */     if (metadata == 9) return new TileEldritchCrabSpawner();
/* 149 */     if (metadata == 10) return new TileEldritchTrap();
/* 150 */     return null;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 155 */     return ConfigBlocks.blockEldritchRI;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 161 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 167 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public Item func_149650_a(int md, Random rand, int fortune)
/*     */   {
/* 173 */     return md == 5 ? ConfigItems.itemResource : md == 4 ? Item.func_150898_a(this) : Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   public int func_149692_a(int metadata)
/*     */   {
/* 178 */     return metadata == 2 ? 1 : metadata;
/*     */   }
/*     */   
/* 181 */   private Random rand = new Random();
/*     */   
/*     */   public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
/* 184 */     if ((metadata == 5) || (metadata == 10)) return MathHelper.func_76136_a(this.rand, 1, 4);
/* 185 */     if (metadata == 9) return MathHelper.func_76136_a(this.rand, 6, 10);
/* 186 */     return super.getExpDrop(world, metadata, fortune);
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int md, int fortune)
/*     */   {
/* 191 */     ArrayList<ItemStack> ret = new ArrayList();
/* 192 */     if (md == 5) {
/* 193 */       ret.add(new ItemStack(ConfigItems.itemResource, 1, 9));
/* 194 */       return ret;
/*     */     }
/*     */     
/* 197 */     return super.getDrops(world, x, y, z, md, fortune);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149749_a(World world, int x, int y, int z, Block block, int meta)
/*     */   {
/* 203 */     if ((!world.field_72995_K) && (meta < 4)) {
/* 204 */       for (int xx = x - 3; xx <= x + 3; xx++) {
/* 205 */         for (int yy = y - 2; yy <= y + 2; yy++) {
/* 206 */           for (int zz = z - 3; zz <= z + 3; zz++)
/* 207 */             if ((world.func_147439_a(xx, yy, zz) == this) && (world.func_72805_g(xx, yy, zz) < 4))
/* 208 */               world.func_147468_f(xx, yy, zz);
/*     */         }
/*     */       }
/* 211 */       world.func_72876_a(null, x + 0.5D, y + 0.5D, z + 0.5D, 1.0F, false);
/*     */     }
/*     */     
/* 214 */     super.func_149749_a(world, x, y, z, block, meta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float func_149712_f(World world, int x, int y, int z)
/*     */   {
/* 221 */     int meta = world.func_72805_g(x, y, z);
/* 222 */     if ((meta == 4) || (meta == 5)) return 2.0F;
/* 223 */     if (meta == 6) return 4.0F;
/* 224 */     if ((meta == 7) || (meta == 8)) return -1.0F;
/* 225 */     if ((meta == 9) || (meta == 10)) { return 15.0F;
/*     */     }
/* 227 */     return super.func_149712_f(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
/*     */   {
/* 234 */     int meta = world.func_72805_g(x, y, z);
/* 235 */     if ((meta == 4) || (meta == 5) || (meta == 9) || (meta == 10)) return 30.0F;
/* 236 */     if (meta == 6) return 100.0F;
/* 237 */     if ((meta == 7) || (meta == 8)) return Float.MAX_VALUE;
/* 238 */     return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
/*     */   {
/* 247 */     int metadata = world.func_72805_g(x, y, z);
/*     */     
/* 249 */     if ((metadata == 0) && (!world.field_72995_K) && (!player.func_70093_af()) && (player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof ItemEldritchObject)) && (player.func_70694_bm().func_77960_j() == 0))
/*     */     {
/*     */ 
/*     */ 
/* 253 */       TileEntity te = world.func_147438_o(x, y, z);
/* 254 */       if ((te != null) && ((te instanceof TileEldritchAltar))) {
/* 255 */         TileEldritchAltar tile = (TileEldritchAltar)te;
/* 256 */         if (tile.getEyes() < 4) {
/* 257 */           if (tile.getEyes() >= 2) {
/* 258 */             tile.setSpawner(true);
/* 259 */             tile.setSpawnType((byte)1);
/*     */           }
/* 261 */           tile.setEyes((byte)(tile.getEyes() + 1));
/* 262 */           tile.checkForMaze();
/* 263 */           player.func_70694_bm().field_77994_a -= 1;
/* 264 */           tile.func_70296_d();
/* 265 */           world.func_147471_g(x, y, z);
/* 266 */           world.func_72908_a(x, y, z, "thaumcraft:crystal", 0.2F, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 271 */     if ((metadata == 8) && (player.field_71071_by.func_70448_g() != null) && ((player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemEldritchObject)) && (player.field_71071_by.func_70448_g().func_77960_j() == 2))
/*     */     {
/*     */ 
/* 274 */       TileEntity te = world.func_147438_o(x, y, z);
/* 275 */       if ((te != null) && ((te instanceof TileEldritchLock)) && (((TileEldritchLock)te).count < 0)) {
/* 276 */         ((TileEldritchLock)te).count = 0;
/* 277 */         world.func_147471_g(x, y, z);
/* 278 */         te.func_70296_d();
/* 279 */         player.func_70694_bm().field_77994_a -= 1;
/* 280 */         world.func_72908_a(x, y, z, "thaumcraft:runicShieldCharge", 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */     
/* 284 */     return super.func_149727_a(world, x, y, z, player, side, par7, par8, par9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World w, int i, int j, int k, Random r)
/*     */   {
/* 292 */     int md = w.func_72805_g(i, j, k);
/* 293 */     if (md == 8) {
/* 294 */       TileEntity te = w.func_147438_o(i, j, k);
/* 295 */       if ((te == null) || (!(te instanceof TileEldritchLock)) || (((TileEldritchLock)te).count < 0)) return;
/* 296 */       FXSpark ef = new FXSpark(w, i + w.field_73012_v.nextFloat(), j + w.field_73012_v.nextFloat(), k + w.field_73012_v.nextFloat(), 0.5F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 301 */       ef.func_70538_b(0.65F + w.field_73012_v.nextFloat() * 0.1F, 1.0F, 1.0F);
/* 302 */       ef.func_82338_g(0.8F);
/* 303 */       ParticleEngine.instance.addEffect(w, ef);
/*     */     }
/* 305 */     else if (md == 10) {
/* 306 */       int x = i + r.nextInt(2) - r.nextInt(2);
/* 307 */       int y = j + r.nextInt(2) - r.nextInt(2);
/* 308 */       int z = k + r.nextInt(2) - r.nextInt(2);
/* 309 */       if (w.func_147437_c(x, y, z)) {
/* 310 */         Thaumcraft.proxy.blockRunes(w, x + r.nextFloat(), y + r.nextFloat(), z + r.nextFloat(), 0.5F + r.nextFloat() * 0.5F, r.nextFloat() * 0.3F, 0.9F + r.nextFloat() * 0.1F, 16 + r.nextInt(4), 0.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_)
/*     */   {
/* 319 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockEldritch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */