/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.client.renderers.block.BlockRenderer;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.relics.ItemResonator;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.tiles.TileCentrifuge;
/*     */ import thaumcraft.common.tiles.TileEssentiaCrystalizer;
/*     */ import thaumcraft.common.tiles.TileTube;
/*     */ import thaumcraft.common.tiles.TileTubeBuffer;
/*     */ import thaumcraft.common.tiles.TileTubeFilter;
/*     */ import thaumcraft.common.tiles.TileTubeOneway;
/*     */ import thaumcraft.common.tiles.TileTubeRestrict;
/*     */ import thaumcraft.common.tiles.TileTubeValve;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTube
/*     */   extends BlockContainer
/*     */ {
/*     */   public BlockTube()
/*     */   {
/*  61 */     super(Material.field_151573_f);
/*  62 */     func_149711_c(0.5F);
/*  63 */     func_149752_b(5.0F);
/*  64 */     func_149672_a(Block.field_149777_j);
/*  65 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  66 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*  69 */   public IIcon[] icon = new IIcon[8];
/*     */   public IIcon iconValve;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir) {
/*  74 */     this.icon[0] = ir.func_94245_a("thaumcraft:pipe_1");
/*  75 */     this.icon[1] = ir.func_94245_a("thaumcraft:pipe_2");
/*  76 */     this.icon[2] = ir.func_94245_a("thaumcraft:pipe_3");
/*  77 */     this.icon[3] = ir.func_94245_a("thaumcraft:pipe_filter");
/*  78 */     this.icon[4] = ir.func_94245_a("thaumcraft:pipe_filter_core");
/*  79 */     this.icon[5] = ir.func_94245_a("thaumcraft:pipe_buffer");
/*  80 */     this.icon[6] = ir.func_94245_a("thaumcraft:pipe_restrict");
/*  81 */     this.icon[7] = ir.func_94245_a("thaumcraft:pipe_oneway");
/*  82 */     this.iconValve = ir.func_94245_a("thaumcraft:pipe_valve");
/*     */   }
/*     */   
/*     */   public IIcon func_149691_a(int i, int md)
/*     */   {
/*  87 */     return md == 5 ? this.icon[6] : md == 4 ? this.icon[5] : this.icon[0];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  93 */     par3List.add(new ItemStack(par1, 1, 0));
/*  94 */     par3List.add(new ItemStack(par1, 1, 1));
/*  95 */     par3List.add(new ItemStack(par1, 1, 2));
/*  96 */     par3List.add(new ItemStack(par1, 1, 3));
/*  97 */     par3List.add(new ItemStack(par1, 1, 4));
/*  98 */     par3List.add(new ItemStack(par1, 1, 5));
/*  99 */     par3List.add(new ItemStack(par1, 1, 6));
/* 100 */     par3List.add(new ItemStack(par1, 1, 7));
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 105 */     return ConfigBlocks.blockTubeRI;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 112 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 118 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z)
/*     */   {
/* 125 */     int metadata = world.func_72805_g(x, y, z);
/* 126 */     boolean noDoodads = (Minecraft.func_71410_x().field_71439_g == null) || (Minecraft.func_71410_x().field_71439_g.func_71045_bC() == null) || ((!(Minecraft.func_71410_x().field_71439_g.func_71045_bC().func_77973_b() instanceof ItemWandCasting)) && (!(Minecraft.func_71410_x().field_71439_g.func_71045_bC().func_77973_b() instanceof ItemResonator)));
/*     */     
/*     */ 
/*     */ 
/* 130 */     if (((metadata == 0) || (metadata == 1) || (metadata == 3) || (metadata == 5) || (metadata == 6)) && (noDoodads)) {
/* 131 */       float minx = BlockRenderer.W6;float maxx = BlockRenderer.W10;
/* 132 */       float miny = BlockRenderer.W5;float maxy = BlockRenderer.W11;
/* 133 */       float minz = BlockRenderer.W5;float maxz = BlockRenderer.W11;
/* 134 */       ForgeDirection fd = null;
/* 135 */       for (int side = 0; side < 6; side++) {
/* 136 */         fd = ForgeDirection.getOrientation(side);
/* 137 */         TileEntity te = ThaumcraftApiHelper.getConnectableTile(world, x, y, z, fd);
/* 138 */         if (te != null) {
/* 139 */           switch (side) {
/*     */           case 0: 
/* 141 */             miny = 0.0F; break;
/* 142 */           case 1:  maxy = 1.0F; break;
/* 143 */           case 2:  minz = 0.0F; break;
/* 144 */           case 3:  maxz = 1.0F; break;
/* 145 */           case 4:  minx = 0.0F; break;
/* 146 */           case 5:  maxx = 1.0F;
/*     */           }
/*     */         }
/*     */       }
/* 150 */       func_149676_a(minx, miny, minz, maxx, maxy, maxz);
/*     */     }
/* 152 */     if ((metadata == 4) && (noDoodads)) {
/* 153 */       func_149676_a(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
/*     */     }
/* 155 */     if (metadata == 7) {
/* 156 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/* 158 */     return super.func_149633_g(world, x, y, z);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int i, int j, int k)
/*     */   {
/* 163 */     int metadata = world.func_72805_g(i, j, k);
/* 164 */     if (metadata == 2) {
/* 165 */       func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
/*     */     }
/* 167 */     else if (metadata == 7) {
/* 168 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
/*     */   {
/* 175 */     int metadata = world.func_72805_g(i, j, k);
/*     */     
/* 177 */     if ((metadata == 0) || (metadata == 1) || (metadata == 3) || (metadata == 4) || (metadata == 5) || (metadata == 6))
/*     */     {
/* 179 */       float minx = BlockRenderer.W6;float maxx = BlockRenderer.W10;
/* 180 */       float miny = BlockRenderer.W6;float maxy = BlockRenderer.W10;
/* 181 */       float minz = BlockRenderer.W6;float maxz = BlockRenderer.W10;
/*     */       
/* 183 */       ForgeDirection fd = null;
/* 184 */       for (int side = 0; side < 6; side++) {
/* 185 */         fd = ForgeDirection.getOrientation(side);
/* 186 */         TileEntity te = ThaumcraftApiHelper.getConnectableTile(world, i, j, k, fd);
/* 187 */         if (te != null) {
/* 188 */           switch (side) {
/*     */           case 0: 
/* 190 */             miny = 0.0F; break;
/* 191 */           case 1:  maxy = 1.0F; break;
/* 192 */           case 2:  minz = 0.0F; break;
/* 193 */           case 3:  maxz = 1.0F; break;
/* 194 */           case 4:  minx = 0.0F; break;
/* 195 */           case 5:  maxx = 1.0F;
/*     */           }
/*     */           
/*     */         }
/*     */       }
/* 200 */       func_149676_a(minx, miny, minz, maxx, maxy, maxz);
/* 201 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     }
/*     */     else {
/* 204 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 205 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_149692_a(int metadata)
/*     */   {
/* 211 */     return metadata;
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 216 */     if (metadata == 0) return new TileTube();
/* 217 */     if (metadata == 1) return new TileTubeValve();
/* 218 */     if (metadata == 2) return new TileCentrifuge();
/* 219 */     if (metadata == 3) return new TileTubeFilter();
/* 220 */     if (metadata == 4) return new TileTubeBuffer();
/* 221 */     if (metadata == 5) return new TileTubeRestrict();
/* 222 */     if (metadata == 6) return new TileTubeOneway();
/* 223 */     if (metadata == 7) return new TileEssentiaCrystalizer();
/* 224 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 229 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 234 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149736_g(World world, int x, int y, int z, int rs)
/*     */   {
/* 240 */     TileEntity te = world.func_147438_o(x, y, z);
/* 241 */     if ((te != null) && ((te instanceof TileTubeBuffer))) {
/* 242 */       ((TileTubeBuffer)te).getClass();float r = ((TileTubeBuffer)te).aspects.visSize() / 8.0F;
/* 243 */       return MathHelper.func_76141_d(r * 14.0F) + (((TileTubeBuffer)te).aspects.visSize() > 0 ? 1 : 0);
/*     */     }
/* 245 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_149749_a(World world, int x, int y, int z, Block par5, int par6)
/*     */   {
/* 250 */     TileEntity te = world.func_147438_o(x, y, z);
/* 251 */     if ((te != null) && ((te instanceof TileTubeFilter)) && (((TileTubeFilter)te).aspectFilter != null) && 
/* 252 */       (!world.field_72995_K)) {
/* 253 */       world.func_72838_d(new EntityItem(world, x + 0.5F, y + 0.5F, z + 0.5F, new ItemStack(ConfigItems.itemResource, 1, 13)));
/*     */     }
/*     */     
/*     */ 
/* 257 */     super.func_149749_a(world, x, y, z, par5, par6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
/*     */   {
/* 264 */     int metadata = world.func_72805_g(x, y, z);
/* 265 */     if (metadata == 1) {
/* 266 */       if ((player.func_70694_bm() != null) && (((player.func_70694_bm().func_77973_b() instanceof ItemWandCasting)) || ((player.func_70694_bm().func_77973_b() instanceof ItemResonator)) || (player.func_70694_bm().func_77973_b() == Item.func_150898_a(this))))
/*     */       {
/*     */ 
/*     */ 
/* 270 */         return false; }
/* 271 */       TileEntity te = world.func_147438_o(x, y, z);
/* 272 */       if ((te instanceof TileTubeValve))
/*     */       {
/* 274 */         ((TileTubeValve)te).allowFlow = (!((TileTubeValve)te).allowFlow);
/* 275 */         world.func_147471_g(x, y, z);
/*     */         
/* 277 */         if (!world.field_72995_K) {
/* 278 */           world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:squeek", 0.7F, 0.9F + world.field_73012_v.nextFloat() * 0.2F);
/*     */         }
/* 280 */         return true;
/*     */       }
/*     */     }
/* 283 */     if (metadata == 3) {
/* 284 */       TileEntity te = world.func_147438_o(x, y, z);
/* 285 */       if ((te != null) && ((te instanceof TileTubeFilter)) && (player.func_70093_af()) && (((TileTubeFilter)te).aspectFilter != null))
/*     */       {
/* 287 */         ((TileTubeFilter)te).aspectFilter = null;
/* 288 */         world.func_147471_g(x, y, z);
/* 289 */         if (world.field_72995_K) {
/* 290 */           world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:page", 1.0F, 1.0F, false);
/*     */         } else {
/* 292 */           ForgeDirection fd = ForgeDirection.getOrientation(side);
/* 293 */           world.func_72838_d(new EntityItem(world, x + 0.5F + fd.offsetX / 3.0F, y + 0.5F, z + 0.5F + fd.offsetZ / 3.0F, new ItemStack(ConfigItems.itemResource, 1, 13)));
/*     */         }
/*     */         
/* 296 */         return true;
/*     */       }
/*     */       
/* 299 */       if ((te != null) && ((te instanceof TileTubeFilter)) && (player.func_70694_bm() != null) && (((TileTubeFilter)te).aspectFilter == null) && (player.func_70694_bm().func_77973_b() == ConfigItems.itemResource) && (player.func_70694_bm().func_77960_j() == 13))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 305 */         if (((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) != null) {
/* 306 */           ((TileTubeFilter)te).aspectFilter = ((IEssentiaContainerItem)(IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()).getAspects()[0];
/* 307 */           player.func_70694_bm().field_77994_a -= 1;
/* 308 */           world.func_147471_g(x, y, z);
/* 309 */           if (world.field_72995_K) {
/* 310 */             world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:page", 1.0F, 1.0F, false);
/*     */           }
/*     */         }
/*     */         
/* 314 */         return true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 319 */     return super.func_149727_a(world, x, y, z, player, side, par7, par8, par9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 324 */   private RayTracer rayTracer = new RayTracer();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void onBlockHighlight(DrawBlockHighlightEvent event)
/*     */   {
/* 330 */     if ((event.target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (event.player.field_70170_p.func_147439_a(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) == this) && (event.player.field_70170_p.func_72805_g(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) != 7) && (event.player.func_71045_bC() != null) && (((event.player.func_71045_bC().func_77973_b() instanceof ItemWandCasting)) || ((event.player.func_71045_bC().func_77973_b() instanceof ItemResonator))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 336 */       RayTracer.retraceBlock(event.player.field_70170_p, event.player, event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 start, Vec3 end)
/*     */   {
/* 344 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 345 */     if ((tile == null) || ((!(tile instanceof TileTube)) && (!(tile instanceof TileTubeBuffer)))) {
/* 346 */       return super.func_149731_a(world, x, y, z, start, end);
/*     */     }
/* 348 */     List<IndexedCuboid6> cuboids = new LinkedList();
/* 349 */     if ((tile instanceof TileTube)) {
/* 350 */       ((TileTube)tile).addTraceableCuboids(cuboids);
/* 351 */     } else if ((tile instanceof TileTubeBuffer)) {
/* 352 */       ((TileTubeBuffer)tile).addTraceableCuboids(cuboids);
/*     */     }
/*     */     
/* 355 */     return this.rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new BlockCoord(x, y, z), this);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockTube.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */