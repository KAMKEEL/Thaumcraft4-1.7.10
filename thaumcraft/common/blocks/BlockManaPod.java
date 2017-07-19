/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import thaumcraft.api.WorldCoordinates;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.client.renderers.block.BlockRenderer;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.ItemManaBean;
/*     */ import thaumcraft.common.tiles.TileManaPod;
/*     */ 
/*     */ public class BlockManaPod extends Block
/*     */ {
/*     */   public BlockManaPod()
/*     */   {
/*  36 */     super(Material.field_151585_k);
/*  37 */     func_149675_a(true);
/*  38 */     this.field_149782_v = 0.5F;
/*     */   }
/*     */   
/*     */ 
/*  42 */   public IIcon[] icon = new IIcon[3];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister)
/*     */   {
/*  51 */     this.icon[0] = par1IconRegister.func_94245_a("thaumcraft:manapod_stem_0");
/*  52 */     this.icon[1] = par1IconRegister.func_94245_a("thaumcraft:manapod_stem_1");
/*  53 */     this.icon[2] = par1IconRegister.func_94245_a("thaumcraft:manapod_stem_2");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_149712_f(World world, int x, int y, int z)
/*     */   {
/*  61 */     float md = 8 - world.func_72805_g(x, y, z);
/*  62 */     return super.func_149712_f(world, x, y, z) / md;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int meta)
/*     */   {
/*  70 */     return meta == 1 ? this.icon[1] : meta == 0 ? this.icon[0] : this.icon[2];
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  76 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  82 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  88 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
/*     */   {
/*  94 */     func_149719_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
/*  95 */     return super.func_149668_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
/*     */   {
/* 104 */     int l = p_149719_1_.func_72805_g(p_149719_2_, p_149719_3_, p_149719_4_);
/* 105 */     switch (l) {
/* 106 */     case 0:  func_149676_a(0.25F, BlockRenderer.W12, 0.25F, 0.75F, 1.0F, 0.75F); break;
/* 107 */     case 1:  func_149676_a(0.25F, BlockRenderer.W10, 0.25F, 0.75F, 1.0F, 0.75F); break;
/* 108 */     case 2:  func_149676_a(0.25F, BlockRenderer.W8, 0.25F, 0.75F, 1.0F, 0.75F); break;
/* 109 */     case 3:  func_149676_a(0.25F, BlockRenderer.W6, 0.25F, 0.75F, 1.0F, 0.75F); break;
/* 110 */     case 4:  func_149676_a(0.25F, BlockRenderer.W5, 0.25F, 0.75F, 1.0F, 0.75F); break;
/* 111 */     case 5:  func_149676_a(0.25F, BlockRenderer.W4, 0.25F, 0.75F, 1.0F, 0.75F); break;
/* 112 */     case 6:  func_149676_a(0.25F, BlockRenderer.W3, 0.25F, 0.75F, 1.0F, 0.75F); break;
/* 113 */     case 7:  func_149676_a(0.25F, BlockRenderer.W2, 0.25F, 0.75F, 1.0F, 0.75F);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
/*     */   {
/* 121 */     func_149719_a(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
/* 122 */     return super.func_149633_g(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/* 131 */     if (!func_149718_j(par1World, par2, par3, par4)) {
/* 132 */       func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
/*     */       
/* 134 */       par1World.func_147468_f(par2, par3, par4);
/* 135 */     } else if (par1World.field_73012_v.nextInt(30) == 0) {
/* 136 */       TileEntity tile = par1World.func_147438_o(par2, par3, par4);
/* 137 */       if ((tile != null) && ((tile instanceof TileManaPod))) {
/* 138 */         ((TileManaPod)tile).checkGrowth();
/*     */       }
/* 140 */       st.remove(new WorldCoordinates(par2, par3, par4, par1World.field_73011_w.field_76574_g));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149718_j(World par1World, int par2, int par3, int par4)
/*     */   {
/* 150 */     BiomeGenBase biome = par1World.func_72807_a(par2, par4);
/* 151 */     boolean magicBiome = false;
/* 152 */     if (biome != null) {
/* 153 */       magicBiome = BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MAGICAL);
/*     */     }
/* 155 */     Block i1 = par1World.func_147439_a(par2, par3 + 1, par4);
/*     */     
/* 157 */     return (magicBiome) && ((i1 == Blocks.field_150364_r) || (i1 == Blocks.field_150363_s) || (i1 == ConfigBlocks.blockMagicalLog));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149707_d(World world, int x, int y, int z, int side)
/*     */   {
/* 163 */     BiomeGenBase biome = world.func_72807_a(x, z);
/* 164 */     boolean magicBiome = false;
/* 165 */     if (biome != null)
/* 166 */       magicBiome = BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MAGICAL);
/* 167 */     Block i1 = world.func_147439_a(x, y + 1, z);
/* 168 */     boolean b = (i1 == Blocks.field_150364_r) || (i1 == Blocks.field_150363_s) || (i1 == ConfigBlocks.blockMagicalLog);
/*     */     
/* 170 */     return (side == 0) && (b) && (magicBiome);
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 175 */     return world.func_72805_g(x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/* 186 */     if (!func_149718_j(par1World, par2, par3, par4)) {
/* 187 */       func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
/*     */       
/* 189 */       par1World.func_147468_f(par2, par3, par4);
/*     */     }
/*     */   }
/*     */   
/* 193 */   static HashMap<WorldCoordinates, Aspect> st = new HashMap();
/*     */   
/*     */   public void func_149749_a(World world, int x, int y, int z, Block block, int meta)
/*     */   {
/* 197 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 198 */     if ((tile != null) && ((tile instanceof TileManaPod)) && (((TileManaPod)tile).aspect != null)) {
/* 199 */       st.put(new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g), ((TileManaPod)tile).aspect);
/*     */     }
/* 201 */     super.func_149749_a(world, x, y, z, block, meta);
/*     */   }
/*     */   
/*     */ 
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*     */   {
/* 207 */     ArrayList<ItemStack> dropped = new ArrayList();
/*     */     
/* 209 */     if (metadata < 2) { return dropped;
/*     */     }
/* 211 */     byte b0 = 1;
/*     */     
/* 213 */     if ((metadata == 7) && (world.field_73012_v.nextFloat() > 0.33F)) {
/* 214 */       b0 = 2;
/*     */     }
/*     */     
/* 217 */     Aspect aspect = Aspect.PLANT;
/* 218 */     if (st.containsKey(new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g))) {
/* 219 */       aspect = (Aspect)st.get(new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g));
/*     */     } else {
/* 221 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 222 */       if ((tile != null) && ((tile instanceof TileManaPod)) && (((TileManaPod)tile).aspect != null)) {
/* 223 */         aspect = ((TileManaPod)tile).aspect;
/*     */       }
/*     */     }
/*     */     
/* 227 */     for (int k1 = 0; k1 < b0; k1++) {
/* 228 */       ItemStack i = new ItemStack(ConfigItems.itemManaBean);
/* 229 */       ((ItemManaBean)i.func_77973_b()).setAspects(i, new AspectList().add(aspect, 1));
/* 230 */       dropped.add(i);
/*     */     }
/*     */     
/* 233 */     st.remove(new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g));
/*     */     
/* 235 */     return dropped;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
/*     */   {
/* 242 */     return ConfigItems.itemManaBean;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3)
/*     */   {
/* 247 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149655_b(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 254 */     return true;
/*     */   }
/*     */   
/*     */   public boolean hasTileEntity(int metadata)
/*     */   {
/* 259 */     return true;
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 264 */     return new TileManaPod();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockManaPod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */