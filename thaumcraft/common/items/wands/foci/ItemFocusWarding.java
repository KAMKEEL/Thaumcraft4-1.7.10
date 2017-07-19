/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.BlockCoordinates;
/*     */ import thaumcraft.api.IArchitect;
/*     */ import thaumcraft.api.IArchitect.EnumAxis;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
/*     */ import thaumcraft.common.tiles.TileWarded;
/*     */ 
/*     */ public class ItemFocusWarding extends ItemFocusBasic implements IArchitect
/*     */ {
/*     */   public IIcon iconOrnament;
/*     */   
/*     */   public ItemFocusWarding()
/*     */   {
/*  39 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public String getSortingHelper(ItemStack itemstack)
/*     */   {
/*  44 */     return "BWA" + super.getSortingHelper(itemstack);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  51 */     this.depthIcon = ir.func_94245_a("thaumcraft:focus_warding_depth");
/*  52 */     this.icon = ir.func_94245_a("thaumcraft:focus_warding");
/*  53 */     this.iconOrnament = ir.func_94245_a("thaumcraft:focus_warding_orn");
/*     */   }
/*     */   
/*     */ 
/*  57 */   IIcon depthIcon = null;
/*     */   
/*     */   public IIcon getFocusDepthLayerIcon(ItemStack itemstack)
/*     */   {
/*  61 */     return this.depthIcon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77618_c(int par1, int renderPass)
/*     */   {
/*  67 */     return renderPass == 1 ? this.icon : this.iconOrnament;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/*  74 */     return true;
/*     */   }
/*     */   
/*     */   public IIcon getOrnament(ItemStack itemstack)
/*     */   {
/*  79 */     return this.iconOrnament;
/*     */   }
/*     */   
/*     */   public int getFocusColor(ItemStack itemstack)
/*     */   {
/*  84 */     return 16771535;
/*     */   }
/*     */   
/*  87 */   private static final AspectList cost = new AspectList().add(Aspect.EARTH, 25).add(Aspect.ORDER, 25).add(Aspect.WATER, 10);
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack)
/*     */   {
/*  91 */     return cost.copy();
/*     */   }
/*     */   
/*     */ 
/*  95 */   public static HashMap<String, Long> delay = new HashMap();
/*     */   
/*     */   public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop)
/*     */   {
/*  99 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/* 100 */     player.func_71038_i();
/*     */     
/* 102 */     if ((!world.field_72995_K) && (mop != null) && (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK)) {
/* 103 */       String key = mop.field_72311_b + ":" + mop.field_72312_c + ":" + mop.field_72309_d + ":" + world.field_73011_w.field_76574_g;
/* 104 */       if ((delay.containsKey(key)) && (((Long)delay.get(key)).longValue() > System.currentTimeMillis())) {
/* 105 */         return itemstack;
/*     */       }
/* 107 */       delay.put(key, Long.valueOf(System.currentTimeMillis() + 500L));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 112 */       TileEntity tt = world.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/* 113 */       boolean solid = world.func_147445_c(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, true);
/* 114 */       if ((tt == null) && (solid)) {
/* 115 */         ArrayList<BlockCoordinates> blocks = getArchitectBlocks(itemstack, world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, player);
/*     */         
/* 117 */         for (BlockCoordinates c : blocks) {
/* 118 */           if (!wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, false)) break;
/* 119 */           if ((world.func_147438_o(c.x, c.y, c.z) == null) && (world.func_147445_c(c.x, c.y, c.z, true)))
/*     */           {
/*     */ 
/* 122 */             Block bi = world.func_147439_a(c.x, c.y, c.z);
/* 123 */             int md = world.func_72805_g(c.x, c.y, c.z);
/* 124 */             int ll = bi.getLightValue(world, c.x, c.y, c.z);
/* 125 */             world.func_147465_d(c.x, c.y, c.z, thaumcraft.common.config.ConfigBlocks.blockWarded, md, 3);
/* 126 */             TileEntity tile = world.func_147438_o(c.x, c.y, c.z);
/* 127 */             if ((tile != null) && ((tile instanceof TileWarded))) {
/* 128 */               TileWarded tw = (TileWarded)tile;
/* 129 */               tw.block = bi;
/* 130 */               tw.blockMd = ((byte)md);
/* 131 */               tw.light = ((byte)ll);
/* 132 */               tw.owner = player.func_70005_c_().hashCode();
/* 133 */               world.func_147471_g(c.x, c.y, c.z);
/* 134 */               PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(c.x, c.y, c.z, 16556032), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, c.x, c.y, c.z, 32.0D));
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 139 */         world.func_72908_a(mop.field_72311_b + 0.5D, mop.field_72312_c + 0.5D, mop.field_72309_d + 0.5D, "thaumcraft:zap", 0.25F, 1.0F);
/*     */ 
/*     */       }
/* 142 */       else if ((tt != null) && ((tt instanceof TileWarded))) {
/* 143 */         TileWarded tw = (TileWarded)tt;
/* 144 */         if (tw.owner == player.func_70005_c_().hashCode())
/*     */         {
/* 146 */           ArrayList<BlockCoordinates> blocks = getArchitectBlocks(itemstack, world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, player);
/*     */           
/* 148 */           for (BlockCoordinates c : blocks) {
/* 149 */             TileEntity tile = world.func_147438_o(c.x, c.y, c.z);
/* 150 */             if ((tile != null) && ((tile instanceof TileWarded))) {
/* 151 */               TileWarded tw2 = (TileWarded)tile;
/* 152 */               if (tw2.owner == player.func_70005_c_().hashCode()) {
/* 153 */                 world.func_147465_d(c.x, c.y, c.z, tw2.block, tw2.blockMd, 3);
/* 154 */                 world.func_147471_g(c.x, c.y, c.z);
/* 155 */                 PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(c.x, c.y, c.z, 16556032), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, c.x, c.y, c.z, 32.0D));
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 161 */           world.func_72908_a(mop.field_72311_b + 0.5D, mop.field_72312_c + 0.5D, mop.field_72309_d + 0.5D, "thaumcraft:zap", 0.25F, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 167 */     return itemstack;
/*     */   }
/*     */   
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 172 */     switch (rank) {
/* 173 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal };
/* 174 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.architect };
/* 175 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge };
/* 176 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge };
/* 177 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge };
/*     */     }
/* 179 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
/*     */   {
/* 184 */     if (type.equals(FocusUpgradeType.enlarge)) {
/* 185 */       return isUpgradedWith(focusstack, FocusUpgradeType.architect);
/*     */     }
/* 187 */     return true;
/*     */   }
/*     */   
/*     */   public int getMaxAreaSize(ItemStack focusstack)
/*     */   {
/* 192 */     return 3 + getUpgradeLevel(focusstack, FocusUpgradeType.enlarge);
/*     */   }
/*     */   
/* 195 */   ArrayList<BlockCoordinates> checked = new ArrayList();
/*     */   
/*     */   public ArrayList<BlockCoordinates> getArchitectBlocks(ItemStack stack, World world, int x, int y, int z, int side, EntityPlayer player)
/*     */   {
/* 199 */     ArrayList<BlockCoordinates> out = new ArrayList();
/* 200 */     ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
/* 201 */     ItemFocusBasic focus = wand.getFocus(stack);
/* 202 */     this.checked.clear();
/* 203 */     boolean tiles = false;
/*     */     
/* 205 */     TileEntity tt = world.func_147438_o(x, y, z);
/* 206 */     boolean solid = world.func_147445_c(x, y, z, true);
/* 207 */     if ((tt != null) || (!solid))
/*     */     {
/*     */ 
/* 210 */       if ((tt != null) && ((tt instanceof TileWarded))) {
/* 211 */         tiles = true;
/*     */       }
/*     */     }
/* 214 */     int sizeX = 0;
/* 215 */     int sizeY = 0;
/* 216 */     int sizeZ = 0;
/* 217 */     if (isUpgradedWith(wand.getFocusItem(stack), FocusUpgradeType.architect)) {
/* 218 */       sizeX = WandManager.getAreaX(stack);
/* 219 */       sizeY = WandManager.getAreaY(stack);
/* 220 */       sizeZ = WandManager.getAreaZ(stack);
/*     */     }
/*     */     
/* 223 */     if ((side == 2) || (side == 3)) {
/* 224 */       checkNeighbours(world, x, y, z, new BlockCoordinates(x, y, z), side, sizeZ, sizeY, sizeX, out, player, tiles);
/*     */     } else {
/* 226 */       checkNeighbours(world, x, y, z, new BlockCoordinates(x, y, z), side, sizeX, sizeY, sizeZ, out, player, tiles);
/*     */     }
/* 228 */     return out;
/*     */   }
/*     */   
/*     */   public void checkNeighbours(World world, int x, int y, int z, BlockCoordinates pos, int side, int sizeX, int sizeY, int sizeZ, ArrayList<BlockCoordinates> list, EntityPlayer player, boolean tiles)
/*     */   {
/* 233 */     if (this.checked.contains(pos)) return;
/* 234 */     this.checked.add(pos);
/* 235 */     switch (side) {
/*     */     case 0: case 1: 
/* 237 */       if (Math.abs(pos.x - x) > sizeX) return;
/* 238 */       if (Math.abs(pos.z - z) > sizeZ) return;
/* 239 */       if (Math.abs(pos.y - y) > sizeY)
/*     */         return;
/*     */       break;
/* 242 */     case 2: case 3:  if (Math.abs(pos.x - x) > sizeX) return;
/* 243 */       if (Math.abs(pos.y - y) > sizeZ) return;
/* 244 */       if (Math.abs(pos.z - z) > sizeY)
/*     */         return;
/*     */       break;
/* 247 */     case 4: case 5:  if (Math.abs(pos.y - y) > sizeX) return;
/* 248 */       if (Math.abs(pos.z - z) > sizeZ) return;
/* 249 */       if (Math.abs(pos.x - x) > sizeY)
/*     */         return;
/*     */       break;
/*     */     }
/* 253 */     TileEntity tt = world.func_147438_o(pos.x, pos.y, pos.z);
/* 254 */     boolean solid = world.func_147445_c(pos.x, pos.y, pos.z, true);
/* 255 */     if ((tiles) && ((tt == null) || (!(tt instanceof TileWarded)))) {
/* 256 */       return;
/*     */     }
/* 258 */     if ((!tiles) && ((tt != null) || (!solid))) {
/* 259 */       return;
/*     */     }
/*     */     
/* 262 */     if ((tiles) && (tt != null) && ((tt instanceof TileWarded))) {
/* 263 */       TileWarded tw2 = (TileWarded)tt;
/* 264 */       if (tw2.owner != player.func_70005_c_().hashCode()) { return;
/*     */       }
/*     */     }
/* 267 */     if (!world.func_147437_c(pos.x, pos.y, pos.z)) {
/* 268 */       list.add(pos);
/*     */     } else {
/* 270 */       return;
/*     */     }
/* 272 */     for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/* 273 */       BlockCoordinates cc = new BlockCoordinates(pos.x + dir.offsetX, pos.y + dir.offsetY, pos.z + dir.offsetZ);
/* 274 */       checkNeighbours(world, x, y, z, cc, side, sizeX, sizeY, sizeZ, list, player, tiles);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean showAxis(ItemStack stack, World world, EntityPlayer player, int side, IArchitect.EnumAxis axis)
/*     */   {
/* 280 */     int dim = WandManager.getAreaDim(stack);
/* 281 */     if (dim == 0) return true;
/* 282 */     switch (side) {
/*     */     case 0: case 1: 
/* 284 */       if (((axis == IArchitect.EnumAxis.X) && (dim == 1)) || ((axis == IArchitect.EnumAxis.Z) && (dim == 2)) || ((axis == IArchitect.EnumAxis.Y) && (dim == 3))) return true;
/*     */       break;
/*     */     case 2: case 3: 
/* 287 */       if (((axis == IArchitect.EnumAxis.Y) && (dim == 1)) || ((axis == IArchitect.EnumAxis.X) && (dim == 2)) || ((axis == IArchitect.EnumAxis.Z) && (dim == 3))) return true;
/*     */       break;
/*     */     case 4: case 5: 
/* 290 */       if (((axis == IArchitect.EnumAxis.Y) && (dim == 1)) || ((axis == IArchitect.EnumAxis.Z) && (dim == 2)) || ((axis == IArchitect.EnumAxis.X) && (dim == 3))) return true;
/*     */       break;
/*     */     }
/* 293 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/foci/ItemFocusWarding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */