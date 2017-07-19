/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.items.ItemEssence;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileArcaneBore;
/*     */ import thaumcraft.common.tiles.TileArcaneBoreBase;
/*     */ import thaumcraft.common.tiles.TileArcanePressurePlate;
/*     */ import thaumcraft.common.tiles.TileBanner;
/*     */ import thaumcraft.common.tiles.TileBellows;
/*     */ import thaumcraft.common.tiles.TileOwned;
/*     */ import thaumcraft.common.tiles.TileSensor;
/*     */ 
/*     */ public class BlockWoodenDevice extends BlockContainer
/*     */ {
/*  47 */   private Random random = new Random();
/*     */   public IIcon iconDefault;
/*     */   
/*     */   public BlockWoodenDevice() {
/*  51 */     super(Material.field_151575_d);
/*  52 */     func_149711_c(2.5F);
/*  53 */     func_149752_b(10.0F);
/*  54 */     func_149672_a(field_149766_f);
/*  55 */     func_149675_a(true);
/*  56 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon iconSilverwood;
/*     */   public IIcon iconGreatwood;
/*  62 */   public IIcon[] iconAPPlate = new IIcon[3];
/*  63 */   public IIcon[] iconAEar = new IIcon[7];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir) {
/*  67 */     this.iconDefault = ir.func_94245_a("thaumcraft:woodplain");
/*  68 */     this.iconSilverwood = ir.func_94245_a("thaumcraft:planks_silverwood");
/*  69 */     this.iconGreatwood = ir.func_94245_a("thaumcraft:planks_greatwood");
/*  70 */     this.iconAPPlate[0] = ir.func_94245_a("thaumcraft:applate1");
/*  71 */     this.iconAPPlate[1] = ir.func_94245_a("thaumcraft:applate2");
/*  72 */     this.iconAPPlate[2] = ir.func_94245_a("thaumcraft:applate3");
/*  73 */     this.iconAEar[0] = ir.func_94245_a("thaumcraft:arcaneearsideon");
/*  74 */     this.iconAEar[1] = ir.func_94245_a("thaumcraft:arcaneearsideoff");
/*  75 */     this.iconAEar[2] = ir.func_94245_a("thaumcraft:arcaneearbottom");
/*  76 */     this.iconAEar[3] = ir.func_94245_a("thaumcraft:arcaneeartopon");
/*  77 */     this.iconAEar[4] = ir.func_94245_a("thaumcraft:arcaneeartopoff");
/*  78 */     this.iconAEar[5] = ir.func_94245_a("thaumcraft:arcaneearbellside");
/*  79 */     this.iconAEar[6] = ir.func_94245_a("thaumcraft:arcaneearbelltop");
/*     */   }
/*     */   
/*     */   public int tickRate()
/*     */   {
/*  84 */     return 20;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  93 */     par3List.add(new ItemStack(par1, 1, 0));
/*  94 */     par3List.add(new ItemStack(par1, 1, 1));
/*  95 */     par3List.add(new ItemStack(par1, 1, 2));
/*  96 */     par3List.add(new ItemStack(par1, 1, 4));
/*  97 */     par3List.add(new ItemStack(par1, 1, 5));
/*  98 */     par3List.add(new ItemStack(par1, 1, 6));
/*  99 */     par3List.add(new ItemStack(par1, 1, 7));
/* 100 */     par3List.add(new ItemStack(par1, 1, 8));
/* 101 */     for (int a = 0; a < 16; a++) {
/* 102 */       ItemStack banner = new ItemStack(par1, 1, 8);
/* 103 */       banner.func_77982_d(new NBTTagCompound());
/* 104 */       banner.field_77990_d.func_74774_a("color", (byte)a);
/* 105 */       par3List.add(banner);
/*     */     }
/*     */   }
/*     */   
/* 109 */   public int renderState = 0;
/*     */   
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/* 113 */     if (par2 == 0) {
/* 114 */       return this.iconDefault;
/*     */     }
/* 116 */     if (par2 == 6) {
/* 117 */       return this.iconGreatwood;
/*     */     }
/* 119 */     if (par2 == 7) {
/* 120 */       return this.iconSilverwood;
/*     */     }
/* 122 */     if ((par2 == 2) || (par2 == 3)) {
/* 123 */       return this.iconAPPlate[0];
/*     */     }
/*     */     
/*     */ 
/* 127 */     if (this.renderState == 0) {
/* 128 */       switch (par1) {
/* 129 */       case 0:  return this.iconAEar[2];
/* 130 */       case 1:  return this.iconAEar[4];
/*     */       }
/*     */     }
/* 133 */     else if (this.renderState == 1) {
/* 134 */       switch (par1) {
/* 135 */       case 0:  return this.iconAEar[2];
/* 136 */       case 1:  return this.iconAEar[3];
/*     */       }
/*     */     } else {
/* 139 */       if (par1 <= 1) return this.iconAEar[6]; return this.iconAEar[5];
/*     */     }
/*     */     
/* 142 */     return this.iconAEar[0];
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/* 148 */     int meta = world.func_72805_g(x, y, z);
/* 149 */     if ((meta == 2) || (meta == 3)) {
/* 150 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 151 */       if ((tile != null) && ((tile instanceof TileArcanePressurePlate))) {
/* 152 */         return this.iconAPPlate[((TileArcanePressurePlate)tile).setting];
/*     */       }
/*     */     }
/* 155 */     return super.func_149673_e(world, x, y, z, side);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 161 */     return par1 == 3 ? 2 : par1;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3)
/*     */   {
/* 166 */     if ((Config.wardedStone) && ((par1 == 2) || (par1 == 3))) return Item.func_150899_d(0);
/* 167 */     if (par1 == 8) return Item.func_150899_d(0);
/* 168 */     return super.func_149650_a(par1, par2Random, par3);
/*     */   }
/*     */   
/*     */   public float func_149712_f(World world, int x, int y, int z)
/*     */   {
/* 173 */     if (world.func_147439_a(x, y, z) != this) return super.func_149712_f(world, x, y, z);
/* 174 */     int md = world.func_72805_g(x, y, z);
/* 175 */     if ((md == 2) || (md == 3)) { return Config.wardedStone ? -1.0F : 2.0F;
/*     */     }
/* 177 */     return super.func_149712_f(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
/*     */   {
/* 184 */     if (world.func_147439_a(x, y, z) != this) { return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
/*     */     }
/* 186 */     int md = world.func_72805_g(x, y, z);
/* 187 */     if ((md == 2) || (md == 3)) return 999.0F;
/* 188 */     return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
/*     */   {
/* 194 */     if (world.func_147439_a(x, y, z) == this) {
/* 195 */       int md = world.func_72805_g(x, y, z);
/* 196 */       if ((md != 2) && (md != 3)) super.onBlockExploded(world, x, y, z, explosion);
/*     */     } else {
/* 198 */       super.onBlockExploded(world, x, y, z, explosion);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 205 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 211 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 216 */     return ConfigBlocks.blockWoodenDeviceRI;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
/*     */   {
/* 224 */     if (p_149633_1_.func_147439_a(p_149633_2_, p_149633_3_, p_149633_4_) != this) {
/* 225 */       return AxisAlignedBB.func_72330_a(p_149633_2_, p_149633_3_, p_149633_4_, p_149633_2_ + 1.0D, p_149633_3_ + 1.0D, p_149633_4_ + 1.0D);
/*     */     }
/* 227 */     return super.func_149633_g(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149719_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 234 */     if (par1iBlockAccess.func_147439_a(par2, par3, par4) != this) {
/* 235 */       super.func_149719_a(par1iBlockAccess, par2, par3, par4);
/* 236 */       return;
/*     */     }
/* 238 */     int meta = par1iBlockAccess.func_72805_g(par2, par3, par4);
/* 239 */     if (meta == 0) {
/* 240 */       func_149676_a(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
/*     */ 
/*     */     }
/* 243 */     else if (meta == 2) {
/* 244 */       float var6 = 0.0625F;
/* 245 */       func_149676_a(var6, 0.0F, var6, 1.0F - var6, 0.0625F, 1.0F - var6);
/*     */ 
/*     */     }
/* 248 */     else if (meta == 3) {
/* 249 */       float var6 = 0.0625F;
/* 250 */       func_149676_a(var6, 0.0F, var6, 1.0F - var6, 0.03125F, 1.0F - var6);
/*     */ 
/*     */     }
/* 253 */     else if (meta == 5) {
/* 254 */       ForgeDirection dir = ForgeDirection.UNKNOWN;
/* 255 */       TileEntity tile = par1iBlockAccess.func_147438_o(par2, par3, par4);
/* 256 */       if ((tile != null) && ((tile instanceof TileArcaneBore))) {
/* 257 */         dir = ((TileArcaneBore)tile).orientation;
/*     */       }
/* 259 */       func_149676_a(0 + (dir.offsetX < 0 ? -1 : 0), 0 + (dir.offsetY < 0 ? -1 : 0), 0 + (dir.offsetZ < 0 ? -1 : 0), 1 + (dir.offsetX > 0 ? 1 : 0), 1 + (dir.offsetY > 0 ? 1 : 0), 1 + (dir.offsetZ > 0 ? 1 : 0));
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 264 */     else if (meta == 8) {
/* 265 */       TileEntity tile = par1iBlockAccess.func_147438_o(par2, par3, par4);
/* 266 */       if ((tile != null) && ((tile instanceof TileBanner))) {
/* 267 */         if (((TileBanner)tile).getWall()) {
/* 268 */           switch (((TileBanner)tile).getFacing()) {
/* 269 */           case 0:  func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 0.25F); break;
/* 270 */           case 8:  func_149676_a(0.0F, 0.0F, 0.75F, 1.0F, 2.0F, 1.0F); break;
/* 271 */           case 12:  func_149676_a(0.0F, 0.0F, 0.0F, 0.25F, 2.0F, 1.0F); break;
/* 272 */           case 4:  func_149676_a(0.75F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
/*     */           }
/*     */         } else {
/* 275 */           func_149676_a(0.33F, 0.0F, 0.33F, 0.66F, 2.0F, 0.66F);
/*     */         }
/*     */       } else {
/* 278 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 283 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/* 285 */     super.func_149719_a(par1iBlockAccess, par2, par3, par4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
/*     */   {
/* 293 */     if (world.func_147439_a(i, j, k) != this) {
/* 294 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/* 295 */       return;
/*     */     }
/* 297 */     int meta = world.func_72805_g(i, j, k);
/* 298 */     if (meta == 0) {
/* 299 */       func_149676_a(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
/*     */ 
/*     */     }
/* 302 */     else if ((meta == 2) || (meta == 3) || (meta == 8)) {
/* 303 */       func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*     */ 
/*     */     }
/* 306 */     else if (meta == 5) {
/* 307 */       ForgeDirection dir = ForgeDirection.UNKNOWN;
/* 308 */       TileEntity tile = world.func_147438_o(i, j, k);
/* 309 */       if ((tile != null) && ((tile instanceof TileArcaneBore))) {
/* 310 */         dir = ((TileArcaneBore)tile).orientation;
/*     */       }
/* 312 */       func_149676_a(0 + (dir.offsetX < 0 ? -1 : 0), 0 + (dir.offsetY < 0 ? -1 : 0), 0 + (dir.offsetZ < 0 ? -1 : 0), 1 + (dir.offsetX > 0 ? 1 : 0), 1 + (dir.offsetY > 0 ? 1 : 0), 1 + (dir.offsetZ > 0 ? 1 : 0));
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 318 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/* 320 */     super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149695_a(World world, int x, int y, int z, Block par5)
/*     */   {
/* 326 */     int meta = world.func_72805_g(x, y, z);
/* 327 */     if (meta == 1) {
/* 328 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 329 */       if ((tile != null) && ((tile instanceof TileSensor))) {
/* 330 */         ((TileSensor)tile).updateTone();
/*     */       }
/*     */       
/*     */     }
/* 334 */     else if (meta == 5) {
/* 335 */       TileArcaneBore tile = (TileArcaneBore)world.func_147438_o(x, y, z);
/* 336 */       if ((tile != null) && ((tile instanceof TileArcaneBore))) {
/* 337 */         ForgeDirection d = tile.baseOrientation.getOpposite();
/* 338 */         Block block = world.func_147439_a(x + d.offsetX, y + d.offsetY, z + d.offsetZ);
/* 339 */         if ((block != ConfigBlocks.blockWoodenDevice) || (!block.isSideSolid(world, x + d.offsetX, y + d.offsetY, z + d.offsetZ, tile.baseOrientation))) {
/* 340 */           InventoryUtils.dropItems(world, x, y, z);
/* 341 */           func_149697_b(world, x, y, z, 5, 0);
/* 342 */           world.func_147468_f(x, y, z);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 347 */     super.func_149695_a(world, x, y, z, par5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/* 355 */     int meta = world.func_72805_g(x, y, z);
/* 356 */     if ((meta == 4) || (meta == 6) || (meta == 7)) return true;
/* 357 */     return super.isSideSolid(world, x, y, z, side);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9)
/*     */   {
/* 363 */     if (w.func_147439_a(x, y, z) != this) return false;
/* 364 */     int meta = w.func_72805_g(x, y, z);
/* 365 */     if ((meta == 4) || (meta == 6) || (meta == 7)) { return false;
/*     */     }
/* 367 */     if (w.field_72995_K) { return true;
/*     */     }
/* 369 */     if ((meta == 5) && ((p.field_71071_by.func_70448_g() == null) || (p.field_71071_by.func_70448_g() == null) || (!(p.field_71071_by.func_70448_g().func_77973_b() instanceof ItemWandCasting)))) {
/* 370 */       p.openGui(Thaumcraft.instance, 15, w, x, y, z);
/* 371 */       return true;
/*     */     }
/*     */     
/* 374 */     if (meta == 1) {
/* 375 */       TileSensor var6 = (TileSensor)w.func_147438_o(x, y, z);
/*     */       
/* 377 */       if (var6 != null)
/*     */       {
/* 379 */         var6.changePitch();
/* 380 */         var6.triggerNote(w, x, y, z, true);
/*     */       }
/*     */     }
/* 383 */     else if ((meta == 2) || (meta == 3)) {
/* 384 */       TileArcanePressurePlate var6 = (TileArcanePressurePlate)w.func_147438_o(x, y, z);
/*     */       
/* 386 */       if ((var6 != null) && ((var6.owner.equals(p.func_70005_c_())) || (var6.accessList.contains("1" + p.func_70005_c_()))))
/*     */       {
/* 388 */         TileArcanePressurePlate tmp243_241 = var6;tmp243_241.setting = ((byte)(tmp243_241.setting + 1));
/* 389 */         if (var6.setting > 2) var6.setting = 0;
/* 390 */         switch (var6.setting) {
/* 391 */         case 0:  p.func_145747_a(new ChatComponentTranslation("It will now trigger on everything.", new Object[0])); break;
/* 392 */         case 1:  p.func_145747_a(new ChatComponentTranslation("It will now trigger on everything except you.", new Object[0])); break;
/* 393 */         case 2:  p.func_145747_a(new ChatComponentTranslation("It will now trigger on just you.", new Object[0]));
/*     */         }
/* 395 */         w.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.1F, 0.9F);
/* 396 */         w.func_147471_g(x, y, z);
/* 397 */         var6.func_70296_d();
/*     */       }
/*     */     }
/* 400 */     else if ((meta == 8) && ((p.func_70093_af()) || ((p.field_71071_by.func_70448_g() != null) && ((p.field_71071_by.func_70448_g().func_77973_b() instanceof ItemEssence)))))
/*     */     {
/*     */ 
/* 403 */       TileBanner te = (TileBanner)w.func_147438_o(x, y, z);
/* 404 */       if (te != null)
/*     */       {
/* 406 */         if (te.getColor() >= 0) {
/* 407 */           if (p.func_70093_af()) {
/* 408 */             te.setAspect(null);
/*     */           }
/* 410 */           else if (((IEssentiaContainerItem)p.func_70694_bm().func_77973_b()).getAspects(p.func_70694_bm()) != null) {
/* 411 */             te.setAspect(((IEssentiaContainerItem)(IEssentiaContainerItem)p.func_70694_bm().func_77973_b()).getAspects(p.func_70694_bm()).getAspects()[0]);
/* 412 */             p.func_70694_bm().field_77994_a -= 1;
/*     */           }
/*     */           
/* 415 */           w.func_147471_g(x, y, z);
/* 416 */           te.func_70296_d();
/* 417 */           w.func_72908_a(x, y, z, "step.cloth", 1.0F, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/* 421 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
/*     */   {
/* 427 */     int md = par1World.func_72805_g(par2, par3, par4);
/* 428 */     if (md == 8) func_149697_b(par1World, par2, par3, par4, par5, 0);
/* 429 */     super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*     */   {
/* 434 */     int md = world.func_72805_g(x, y, z);
/* 435 */     if (md == 8) {
/* 436 */       ArrayList<ItemStack> drops = new ArrayList();
/* 437 */       TileEntity te = world.func_147438_o(x, y, z);
/* 438 */       if ((te != null) && ((te instanceof TileBanner))) {
/* 439 */         ItemStack drop = new ItemStack(this, 1, 8);
/* 440 */         if ((((TileBanner)te).getColor() >= 0) || (((TileBanner)te).getAspect() != null)) {
/* 441 */           drop.func_77982_d(new NBTTagCompound());
/* 442 */           if (((TileBanner)te).getAspect() != null) {
/* 443 */             drop.field_77990_d.func_74778_a("aspect", ((TileBanner)te).getAspect().getTag());
/*     */           }
/* 445 */           drop.field_77990_d.func_74774_a("color", ((TileBanner)te).getColor());
/*     */         }
/* 447 */         drops.add(drop);
/*     */       }
/* 449 */       return drops;
/*     */     }
/* 451 */     return super.getDrops(world, x, y, z, metadata, fortune);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149689_a(World w, int x, int y, int z, EntityLivingBase p, ItemStack s)
/*     */   {
/* 457 */     TileEntity tile = w.func_147438_o(x, y, z);
/* 458 */     if ((tile != null) && ((tile instanceof TileOwned)) && ((p instanceof EntityPlayer))) {
/* 459 */       ((TileOwned)tile).owner = ((EntityPlayer)p).func_70005_c_();
/* 460 */       tile.func_70296_d();
/*     */     }
/*     */     
/* 463 */     super.func_149689_a(w, x, y, z, p, s);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149726_b(World world, int x, int y, int z)
/*     */   {
/* 469 */     super.func_149726_b(world, x, y, z);
/* 470 */     if (world.func_147439_a(x, y, z) != this) return;
/* 471 */     int meta = world.func_72805_g(x, y, z);
/* 472 */     if (meta == 1) {
/* 473 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 474 */       if ((tile != null) && ((tile instanceof TileSensor))) {
/* 475 */         ((TileSensor)tile).updateTone();
/* 476 */         tile.func_70296_d();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/* 486 */     int meta = world.func_72805_g(x, y, z);
/* 487 */     if (meta == 0) return false;
/* 488 */     if ((meta == 1) || (meta == 2) || (meta == 3) || (meta == 4) || (meta == 5)) return true;
/* 489 */     return super.canConnectRedstone(world, x, y, z, side);
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 494 */     if (metadata == 0) return new TileBellows();
/* 495 */     if (metadata == 1) return new TileSensor();
/* 496 */     if (metadata == 2) return new TileArcanePressurePlate();
/* 497 */     if (metadata == 3) return new TileArcanePressurePlate();
/* 498 */     if (metadata == 4) return new TileArcaneBoreBase();
/* 499 */     if (metadata == 5) return new TileArcaneBore();
/* 500 */     if (metadata == 8) return new TileBanner();
/* 501 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */ 
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 507 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149696_a(World par1World, int par2, int par3, int par4, int par5, int par6)
/*     */   {
/* 512 */     float var7 = (float)Math.pow(2.0D, (par6 - 12) / 12.0D);
/* 513 */     if (par5 <= 4) {
/* 514 */       if (par5 >= 0) {
/* 515 */         String var8 = "harp";
/*     */         
/* 517 */         if (par5 == 1)
/*     */         {
/* 519 */           var8 = "bd";
/*     */         }
/*     */         
/* 522 */         if (par5 == 2)
/*     */         {
/* 524 */           var8 = "snare";
/*     */         }
/*     */         
/* 527 */         if (par5 == 3)
/*     */         {
/* 529 */           var8 = "hat";
/*     */         }
/*     */         
/* 532 */         if (par5 == 4)
/*     */         {
/* 534 */           var8 = "bassattack";
/*     */         }
/*     */         
/* 537 */         par1World.func_72908_a(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "note." + var8, 3.0F, var7);
/*     */       }
/* 539 */       par1World.func_72869_a("note", par2 + 0.5D, par3 + 1.2D, par4 + 0.5D, par6 / 24.0D, 0.0D, 0.0D);
/* 540 */       return true;
/*     */     }
/* 542 */     if (par5 == 99) {
/* 543 */       return super.func_149696_a(par1World, par2, par3, par4, par5, par6);
/*     */     }
/* 545 */     return super.func_149696_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/* 551 */     if (par1World.func_147439_a(par2, par3, par4) != this) return;
/* 552 */     if (!par1World.field_72995_K)
/*     */     {
/* 554 */       if (par1World.func_72805_g(par2, par3, par4) == 3)
/*     */       {
/* 556 */         setStateIfMobInteractsWithPlate(par1World, par2, par3, par4);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149670_a(World par1World, int par2, int par3, int par4, Entity par5Entity)
/*     */   {
/* 564 */     if (par1World.func_147439_a(par2, par3, par4) != this) { return;
/*     */     }
/* 566 */     if (!par1World.field_72995_K)
/*     */     {
/* 568 */       if (par1World.func_72805_g(par2, par3, par4) == 2)
/*     */       {
/* 570 */         setStateIfMobInteractsWithPlate(par1World, par2, par3, par4);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void setStateIfMobInteractsWithPlate(World world, int x, int y, int z)
/*     */   {
/* 577 */     boolean var5 = world.func_72805_g(x, y, z) == 3;
/* 578 */     boolean var6 = false;
/* 579 */     float var7 = 0.125F;
/* 580 */     List var8 = null;
/* 581 */     String username = "";
/* 582 */     byte setting = 0;
/* 583 */     ArrayList<String> accessList = new ArrayList();
/* 584 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 585 */     if ((tile != null) && ((tile instanceof TileArcanePressurePlate))) {
/* 586 */       setting = ((TileArcanePressurePlate)tile).setting;
/* 587 */       username = ((TileArcanePressurePlate)tile).owner;
/* 588 */       accessList = ((TileArcanePressurePlate)tile).accessList;
/*     */     }
/*     */     
/* 591 */     if (setting == 0)
/*     */     {
/* 593 */       var8 = world.func_72839_b((Entity)null, AxisAlignedBB.func_72330_a(x + var7, y, z + var7, x + 1 - var7, y + 0.25D, z + 1 - var7));
/*     */     }
/*     */     
/*     */ 
/* 597 */     if (setting == 1)
/*     */     {
/* 599 */       var8 = world.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a(x + var7, y, z + var7, x + 1 - var7, y + 0.25D, z + 1 - var7));
/*     */     }
/*     */     
/* 602 */     if (setting == 2)
/*     */     {
/* 604 */       var8 = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(x + var7, y, z + var7, x + 1 - var7, y + 0.25D, z + 1 - var7));
/*     */     }
/*     */     
/* 607 */     if (!var8.isEmpty())
/*     */     {
/* 609 */       Iterator var9 = var8.iterator();
/*     */       
/* 611 */       while (var9.hasNext())
/*     */       {
/* 613 */         Entity var10 = (Entity)var9.next();
/*     */         
/* 615 */         if (!var10.func_145773_az())
/*     */         {
/* 617 */           if (((setting != 1) || (!(var10 instanceof EntityPlayer)) || ((!((EntityPlayer)var10).func_70005_c_().equals(username)) && (!accessList.contains("0" + ((EntityPlayer)var10).func_70005_c_())) && (!accessList.contains("1" + ((EntityPlayer)var10).func_70005_c_())))) && (
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 623 */             (setting != 2) || (!(var10 instanceof EntityPlayer)) || (((EntityPlayer)var10).func_70005_c_().equals(username)) || (accessList.contains("0" + ((EntityPlayer)var10).func_70005_c_())) || (accessList.contains("1" + ((EntityPlayer)var10).func_70005_c_()))))
/*     */           {
/*     */ 
/*     */ 
/* 627 */             var6 = true;
/* 628 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 633 */     if ((var6) && (!var5))
/*     */     {
/* 635 */       world.func_72921_c(x, y, z, 3, 2);
/* 636 */       world.func_147459_d(x, y, z, this);
/* 637 */       world.func_147459_d(x, y - 1, z, this);
/* 638 */       world.func_147458_c(x, y, z, x, y, z);
/* 639 */       world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.2F, 0.6F);
/*     */     }
/*     */     
/* 642 */     if ((!var6) && (var5))
/*     */     {
/* 644 */       world.func_72921_c(x, y, z, 2, 2);
/* 645 */       world.func_147459_d(x, y, z, this);
/* 646 */       world.func_147459_d(x, y - 1, z, this);
/* 647 */       world.func_147458_c(x, y, z, x, y, z);
/* 648 */       world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.2F, 0.5F);
/*     */     }
/*     */     
/* 651 */     if (var6)
/*     */     {
/* 653 */       world.func_147464_a(x, y, z, this, tickRate());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/* 661 */     if (par6 == 3)
/*     */     {
/* 663 */       par1World.func_147459_d(par2, par3, par4, this);
/* 664 */       par1World.func_147459_d(par2, par3 - 1, par4, this);
/*     */ 
/*     */     }
/* 667 */     else if (par6 == 5) {
/* 668 */       InventoryUtils.dropItems(par1World, par2, par3, par4);
/*     */     }
/*     */     
/* 671 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */   public int func_149748_c(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/* 676 */     int meta = world.func_72805_g(x, y, z);
/* 677 */     if (meta == 1) {
/* 678 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 679 */       if ((tile != null) && ((tile instanceof TileSensor)))
/* 680 */         return ((TileSensor)tile).redstoneSignal > 0 ? 15 : 0;
/*     */     } else {
/* 682 */       return (side == 1) && (world.func_72805_g(x, y, z) == 3) ? 15 : world.func_72805_g(x, y, z) == 2 ? 0 : 0; }
/* 683 */     return super.func_149748_c(world, x, y, z, side);
/*     */   }
/*     */   
/*     */   public int func_149709_b(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/* 688 */     int meta = world.func_72805_g(x, y, z);
/* 689 */     if (meta == 1) {
/* 690 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 691 */       if ((tile != null) && ((tile instanceof TileSensor))) {
/* 692 */         return ((TileSensor)tile).redstoneSignal > 0 ? 15 : 0;
/*     */       }
/* 694 */     } else if (meta == 3) { return 15; }
/* 695 */     return super.func_149748_c(world, x, y, z, side);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149744_f()
/*     */   {
/* 701 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149656_h()
/*     */   {
/* 707 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getLightOpacity(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 713 */     int meta = world.func_72805_g(x, y, z);
/* 714 */     if ((meta == 6) || (meta == 7)) {
/* 715 */       return 255;
/*     */     }
/* 717 */     return super.getLightOpacity(world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
/*     */   {
/* 722 */     int meta = world.func_72805_g(x, y, z);
/* 723 */     if ((meta == 2) || (meta == 3)) {
/* 724 */       return false;
/*     */     }
/* 726 */     return super.canEntityDestroy(world, x, y, z, entity);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 732 */     int meta = world.func_72805_g(x, y, z);
/* 733 */     return (meta == 6) || (meta == 7) ? 20 : 0;
/*     */   }
/*     */   
/*     */   public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 738 */     int meta = world.func_72805_g(x, y, z);
/* 739 */     return (meta == 6) || (meta == 7) ? 5 : 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockWoodenDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */