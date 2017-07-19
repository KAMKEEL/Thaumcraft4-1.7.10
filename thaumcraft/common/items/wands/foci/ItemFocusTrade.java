/*     */ package thaumcraft.common.items.wands.foci;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.BlockCoordinates;
/*     */ import thaumcraft.api.IArchitect;
/*     */ import thaumcraft.api.IArchitect.EnumAxis;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.lib.events.ServerTickEventsFML;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ public class ItemFocusTrade extends ItemFocusBasic implements IArchitect
/*     */ {
/*     */   public IIcon iconOrnament;
/*     */   
/*     */   public ItemFocusTrade()
/*     */   {
/*  42 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public String getSortingHelper(ItemStack itemstack)
/*     */   {
/*  47 */     return "BT" + super.getSortingHelper(itemstack);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  54 */     this.icon = ir.func_94245_a("thaumcraft:focus_trade");
/*  55 */     this.iconOrnament = ir.func_94245_a("thaumcraft:focus_trade_orn");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77618_c(int par1, int renderPass)
/*     */   {
/*  61 */     return renderPass == 1 ? this.icon : this.iconOrnament;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/*  68 */     return true;
/*     */   }
/*     */   
/*     */   public IIcon getOrnament(ItemStack itemstack)
/*     */   {
/*  73 */     return this.iconOrnament;
/*     */   }
/*     */   
/*     */   protected MovingObjectPosition getMovingObjectPositionFromPlayer(World par1World, EntityPlayer par2EntityPlayer)
/*     */   {
/*  78 */     float f = 1.0F;
/*  79 */     float f1 = par2EntityPlayer.field_70127_C + (par2EntityPlayer.field_70125_A - par2EntityPlayer.field_70127_C) * f;
/*  80 */     float f2 = par2EntityPlayer.field_70126_B + (par2EntityPlayer.field_70177_z - par2EntityPlayer.field_70126_B) * f;
/*  81 */     double d0 = par2EntityPlayer.field_70169_q + (par2EntityPlayer.field_70165_t - par2EntityPlayer.field_70169_q) * f;
/*  82 */     double d1 = par2EntityPlayer.field_70167_r + (par2EntityPlayer.field_70163_u - par2EntityPlayer.field_70167_r) * f + (par1World.field_72995_K ? par2EntityPlayer.func_70047_e() - par2EntityPlayer.getDefaultEyeHeight() : par2EntityPlayer.func_70047_e());
/*  83 */     double d2 = par2EntityPlayer.field_70166_s + (par2EntityPlayer.field_70161_v - par2EntityPlayer.field_70166_s) * f;
/*  84 */     Vec3 vec3 = Vec3.func_72443_a(d0, d1, d2);
/*  85 */     float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
/*  86 */     float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
/*  87 */     float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
/*  88 */     float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
/*  89 */     float f7 = f4 * f5;
/*  90 */     float f8 = f3 * f5;
/*  91 */     double d3 = 5.0D;
/*  92 */     if ((par2EntityPlayer instanceof EntityPlayerMP))
/*     */     {
/*  94 */       d3 = ((EntityPlayerMP)par2EntityPlayer).field_71134_c.getBlockReachDistance();
/*     */     }
/*  96 */     Vec3 vec31 = vec3.func_72441_c(f7 * d3, f6 * d3, f8 * d3);
/*  97 */     return par1World.func_72901_a(vec3, vec31, false);
/*     */   }
/*     */   
/*     */   public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition)
/*     */   {
/* 102 */     MovingObjectPosition mop = getMovingObjectPositionFromPlayer(world, player);
/*     */     
/*     */ 
/* 105 */     ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
/*     */     
/* 107 */     if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)) {
/* 108 */       int x = mop.field_72311_b;
/* 109 */       int y = mop.field_72312_c;
/* 110 */       int z = mop.field_72309_d;
/* 111 */       Block bi = world.func_147439_a(x, y, z);
/* 112 */       int md = world.func_72805_g(x, y, z);
/*     */       
/* 114 */       if (player.func_70093_af()) {
/* 115 */         if ((!world.field_72995_K) && (world.func_147438_o(x, y, z) == null)) {
/* 116 */           ItemStack isout = new ItemStack(bi, 1, md);
/*     */           try {
/* 118 */             if (bi != Blocks.field_150350_a) {
/* 119 */               ItemStack is = BlockUtils.createStackedBlock(bi, md);
/* 120 */               if (is != null) {
/* 121 */                 isout = is.func_77946_l();
/*     */               }
/*     */             }
/*     */           } catch (Exception e) {}
/* 125 */           storePickedBlock(itemstack, isout);
/*     */         } else {
/* 127 */           player.func_71038_i();
/*     */         }
/*     */       } else {
/* 130 */         ItemStack pb = getPickedBlock(itemstack);
/* 131 */         if ((pb != null) && (world.field_72995_K)) {
/* 132 */           player.func_71038_i();
/*     */         }
/* 134 */         else if ((pb != null) && (world.func_147438_o(x, y, z) == null) && (world.func_147439_a(x, y, z).func_149688_o() != Config.taintMaterial))
/*     */         {
/*     */ 
/* 137 */           if (isUpgradedWith(wand.getFocusItem(itemstack), FocusUpgradeType.architect)) {
/* 138 */             int sizeX = WandManager.getAreaX(itemstack);
/* 139 */             int sizeZ = WandManager.getAreaZ(itemstack);
/* 140 */             ArrayList<BlockCoordinates> blocks = getArchitectBlocks(itemstack, world, x, y, z, mop.field_72310_e, player);
/*     */             
/* 142 */             for (BlockCoordinates c : blocks) {
/* 143 */               ServerTickEventsFML.addSwapper(world, c.x, c.y, c.z, world.func_147439_a(c.x, c.y, c.z), world.func_72805_g(c.x, c.y, c.z), pb, 0, player, player.field_71071_by.field_70461_c);
/*     */             }
/*     */             
/*     */           }
/*     */           else
/*     */           {
/* 149 */             ServerTickEventsFML.addSwapper(world, x, y, z, world.func_147439_a(x, y, z), world.func_72805_g(x, y, z), pb, 3 + wand.getFocusEnlarge(itemstack), player, player.field_71071_by.field_70461_c);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 158 */     return itemstack;
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_150893_a(ItemStack itemstack, Block block)
/*     */   {
/* 164 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onEntitySwing(EntityLivingBase player, ItemStack stack)
/*     */   {
/* 170 */     if ((!player.field_70170_p.field_72995_K) && ((player instanceof EntityPlayer))) {
/* 171 */       ItemStack pb = getPickedBlock(stack);
/* 172 */       MovingObjectPosition mop = getMovingObjectPositionFromPlayer(player.field_70170_p, (EntityPlayer)player);
/* 173 */       if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)) {
/* 174 */         int x = mop.field_72311_b;
/* 175 */         int y = mop.field_72312_c;
/* 176 */         int z = mop.field_72309_d;
/* 177 */         if ((pb != null) && (player.field_70170_p.func_147438_o(x, y, z) == null) && (player.field_70170_p.func_147439_a(x, y, z).func_149688_o() != Config.taintMaterial))
/*     */         {
/* 179 */           ServerTickEventsFML.addSwapper(player.field_70170_p, x, y, z, player.field_70170_p.func_147439_a(x, y, z), player.field_70170_p.func_72805_g(x, y, z), pb, 0, (EntityPlayer)player, ((EntityPlayer)player).field_71071_by.field_70461_c);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 187 */     return super.onEntitySwing(player, stack);
/*     */   }
/*     */   
/*     */   public void storePickedBlock(ItemStack stack, ItemStack stackout)
/*     */   {
/* 192 */     NBTTagCompound item = new NBTTagCompound();
/* 193 */     stack.func_77983_a("picked", stackout.func_77955_b(item));
/*     */   }
/*     */   
/*     */   public ItemStack getPickedBlock(ItemStack stack)
/*     */   {
/* 198 */     ItemStack out = null;
/* 199 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("picked"))) {
/* 200 */       out = new ItemStack(Blocks.field_150350_a);
/* 201 */       out.func_77963_c(stack.field_77990_d.func_74775_l("picked"));
/*     */     }
/* 203 */     return out;
/*     */   }
/*     */   
/*     */   public int getFocusColor(ItemStack itemstack)
/*     */   {
/* 208 */     return 8747923;
/*     */   }
/*     */   
/* 211 */   private static final AspectList cost = new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.EARTH, 5).add(Aspect.ORDER, 5);
/* 212 */   private static AspectList cost2 = null;
/*     */   
/*     */   public AspectList getVisCost(ItemStack itemstack) {
/* 215 */     if (isUpgradedWith(itemstack, FocusUpgradeType.silktouch)) {
/* 216 */       if (cost2 == null) {
/* 217 */         cost2 = new AspectList().add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1);
/* 218 */         cost2.add(cost);
/*     */       }
/* 220 */       return cost2;
/*     */     }
/* 222 */     return cost;
/*     */   }
/*     */   
/*     */ 
/*     */   public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
/*     */   {
/* 228 */     switch (rank) {
/* 229 */     case 1:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge };
/* 230 */     case 2:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge };
/* 231 */     case 3:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.treasure, FocusUpgradeType.architect };
/* 232 */     case 4:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge };
/* 233 */     case 5:  return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.silktouch };
/*     */     }
/* 235 */     return null;
/*     */   }
/*     */   
/*     */   public int getMaxAreaSize(ItemStack focusstack)
/*     */   {
/* 240 */     return 3 + getUpgradeLevel(focusstack, FocusUpgradeType.enlarge) * 2;
/*     */   }
/*     */   
/* 243 */   ArrayList<BlockCoordinates> checked = new ArrayList();
/*     */   
/*     */   public ArrayList<BlockCoordinates> getArchitectBlocks(ItemStack stack, World world, int x, int y, int z, int side, EntityPlayer player)
/*     */   {
/* 247 */     ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
/* 248 */     ItemFocusBasic focus = wand.getFocus(stack);
/*     */     
/* 250 */     Block bi = world.func_147439_a(x, y, z);
/* 251 */     int md = world.func_72805_g(x, y, z);
/*     */     
/* 253 */     ArrayList<BlockCoordinates> out = new ArrayList();
/* 254 */     this.checked.clear();
/* 255 */     if ((side == 2) || (side == 3)) {
/* 256 */       checkNeighbours(world, x, y, z, bi, md, new BlockCoordinates(x, y, z), side, WandManager.getAreaZ(stack), WandManager.getAreaY(stack), WandManager.getAreaX(stack), out, player);
/*     */     }
/*     */     else {
/* 259 */       checkNeighbours(world, x, y, z, bi, md, new BlockCoordinates(x, y, z), side, WandManager.getAreaX(stack), WandManager.getAreaY(stack), WandManager.getAreaZ(stack), out, player);
/*     */     }
/*     */     
/* 262 */     return out;
/*     */   }
/*     */   
/*     */   public void checkNeighbours(World world, int x, int y, int z, Block bi, int md, BlockCoordinates pos, int side, int sizeX, int sizeY, int sizeZ, ArrayList<BlockCoordinates> list, EntityPlayer player)
/*     */   {
/* 267 */     if (this.checked.contains(pos)) return;
/* 268 */     this.checked.add(pos);
/* 269 */     switch (side) {
/*     */     case 0: case 1: 
/* 271 */       if (Math.abs(pos.x - x) > sizeX) return;
/* 272 */       if (Math.abs(pos.z - z) > sizeZ)
/*     */         return;
/*     */       break;
/* 275 */     case 2: case 3:  if (Math.abs(pos.x - x) > sizeX) return;
/* 276 */       if (Math.abs(pos.y - y) > sizeZ)
/*     */         return;
/*     */       break;
/* 279 */     case 4: case 5:  if (Math.abs(pos.y - y) > sizeX) return;
/* 280 */       if (Math.abs(pos.z - z) > sizeZ)
/*     */         return;
/*     */       break;
/*     */     }
/* 284 */     if ((world.func_147439_a(pos.x, pos.y, pos.z) == bi) && (world.func_72805_g(pos.x, pos.y, pos.z) == md) && (BlockUtils.isBlockExposed(world, pos.x, pos.y, pos.z)) && (!world.func_147437_c(pos.x, pos.y, pos.z)) && (world.func_147439_a(pos.x, pos.y, pos.z).func_149712_f(world, pos.x, pos.y, pos.z) >= 0.0F) && (world.func_72962_a(player, pos.x, pos.y, pos.z)))
/*     */     {
/*     */ 
/*     */ 
/* 288 */       list.add(pos);
/*     */     } else {
/* 290 */       return;
/*     */     }
/* 292 */     for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
/* 293 */       if ((dir.ordinal() != side) && (dir.getOpposite().ordinal() != side)) {
/* 294 */         BlockCoordinates cc = new BlockCoordinates(pos.x + dir.offsetX, pos.y + dir.offsetY, pos.z + dir.offsetZ);
/* 295 */         checkNeighbours(world, x, y, z, bi, md, cc, side, sizeX, sizeY, sizeZ, list, player);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean showAxis(ItemStack stack, World world, EntityPlayer player, int side, IArchitect.EnumAxis axis) {
/* 301 */     int dim = WandManager.getAreaDim(stack);
/* 302 */     switch (side) {
/*     */     case 0: case 1: 
/* 304 */       if (((axis == IArchitect.EnumAxis.X) && ((dim == 0) || (dim == 1))) || ((axis == IArchitect.EnumAxis.Z) && ((dim == 0) || (dim == 2)))) return true;
/*     */       break;
/*     */     case 2: case 3: 
/* 307 */       if (((axis == IArchitect.EnumAxis.Y) && ((dim == 0) || (dim == 1))) || ((axis == IArchitect.EnumAxis.X) && ((dim == 0) || (dim == 2)))) return true;
/*     */       break;
/*     */     case 4: case 5: 
/* 310 */       if (((axis == IArchitect.EnumAxis.Y) && ((dim == 0) || (dim == 1))) || ((axis == IArchitect.EnumAxis.Z) && ((dim == 0) || (dim == 2)))) return true;
/*     */       break;
/*     */     }
/* 313 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/foci/ItemFocusTrade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */