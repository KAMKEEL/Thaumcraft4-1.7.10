/*     */ package thaumcraft.common.items.equipment;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemSpade;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.BlockCoordinates;
/*     */ import thaumcraft.api.IArchitect.EnumAxis;
/*     */ import thaumcraft.api.IRepairable;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class ItemElementalShovel extends ItemSpade implements IRepairable, thaumcraft.api.IArchitect
/*     */ {
/*  40 */   private static final Block[] isEffective = { Blocks.field_150349_c, Blocks.field_150346_d, Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150431_aC, Blocks.field_150433_aE, Blocks.field_150435_aG, Blocks.field_150458_ak, Blocks.field_150425_aM, Blocks.field_150391_bh };
/*     */   
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemElementalShovel(Item.ToolMaterial enumtoolmaterial)
/*     */   {
/*  46 */     super(enumtoolmaterial);
/*  47 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public Set<String> getToolClasses(ItemStack stack)
/*     */   {
/*  52 */     return ImmutableSet.of("shovel");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  60 */     this.icon = ir.func_94245_a("thaumcraft:elementalshovel");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  66 */     return this.icon;
/*     */   }
/*     */   
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  71 */     return EnumRarity.rare;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  77 */     return par2ItemStack.func_77969_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
/*     */   {
/*  86 */     int xm = ForgeDirection.getOrientation(side).offsetX;
/*  87 */     int ym = ForgeDirection.getOrientation(side).offsetY;
/*  88 */     int zm = ForgeDirection.getOrientation(side).offsetZ;
/*  89 */     Block bi = world.func_147439_a(x, y, z);
/*  90 */     int md = world.func_72805_g(x, y, z);
/*  91 */     TileEntity te = world.func_147438_o(x, y, z);
/*  92 */     if (te == null)
/*     */     {
/*  94 */       for (int aa = -1; aa <= 1; aa++) {
/*  95 */         for (int bb = -1; bb <= 1; bb++)
/*     */         {
/*  97 */           int xx = 0;
/*  98 */           int yy = 0;
/*  99 */           int zz = 0;
/*     */           
/* 101 */           byte o = getOrientation(itemstack);
/* 102 */           if (o == 1) {
/* 103 */             yy = bb;
/* 104 */             if (side <= 1) {
/* 105 */               int l = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/* 106 */               if ((l == 0) || (l == 2)) {
/* 107 */                 xx = aa;
/*     */               } else {
/* 109 */                 zz = aa;
/*     */               }
/* 111 */             } else if (side <= 3) {
/* 112 */               zz = aa;
/*     */             } else {
/* 114 */               xx = aa;
/*     */             }
/* 116 */           } else if (o == 2)
/*     */           {
/* 118 */             if (side <= 1) {
/* 119 */               int l = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/* 120 */               yy = bb;
/* 121 */               if ((l == 0) || (l == 2)) {
/* 122 */                 xx = aa;
/*     */               } else {
/* 124 */                 zz = aa;
/*     */               }
/*     */             } else {
/* 127 */               zz = bb;
/* 128 */               xx = aa;
/*     */             }
/*     */           }
/* 131 */           else if (side <= 1) {
/* 132 */             xx = aa;
/* 133 */             zz = bb;
/* 134 */           } else if (side <= 3) {
/* 135 */             xx = aa;
/* 136 */             yy = bb;
/*     */           } else {
/* 138 */             zz = aa;
/* 139 */             yy = bb;
/*     */           }
/*     */           
/*     */ 
/* 143 */           Block b2 = world.func_147439_a(x + xx + xm, y + yy + ym, z + zz + zm);
/*     */           
/* 145 */           if ((world.func_147437_c(x + xx + xm, y + yy + ym, z + zz + zm)) || (b2 == Blocks.field_150395_bd) || (b2 == Blocks.field_150329_H) || (b2.func_149688_o() == Material.field_151586_h) || (b2 == Blocks.field_150330_I) || (b2.isReplaceable(world, x + xx + xm, y + yy + ym, z + zz + zm)))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 152 */             if ((player.field_71075_bZ.field_75098_d) || (InventoryUtils.consumeInventoryItem(player, Item.func_150898_a(bi), md)))
/*     */             {
/* 154 */               world.func_72980_b(x + xx + xm, y + yy + ym, z + zz + zm, bi.field_149762_H.func_150496_b(), 0.6F, 0.9F + world.field_73012_v.nextFloat() * 0.2F, false);
/*     */               
/*     */ 
/* 157 */               world.func_147465_d(x + xx + xm, y + yy + ym, z + zz + zm, bi, md, 3);
/*     */               
/* 159 */               itemstack.func_77972_a(1, player);
/* 160 */               Thaumcraft.proxy.blockSparkle(world, x + xx + xm, y + yy + ym, z + zz + zm, 8401408, 4);
/*     */               
/* 162 */               player.func_71038_i();
/* 163 */             } else if ((bi == Blocks.field_150349_c) && ((player.field_71075_bZ.field_75098_d) || (InventoryUtils.consumeInventoryItem(player, Item.func_150898_a(Blocks.field_150346_d), 0))))
/*     */             {
/*     */ 
/* 166 */               world.func_72980_b(x + xx + xm, y + yy + ym, z + zz + zm, bi.field_149762_H.func_150496_b(), 0.6F, 0.9F + world.field_73012_v.nextFloat() * 0.2F, false);
/*     */               
/*     */ 
/* 169 */               world.func_147465_d(x + xx + xm, y + yy + ym, z + zz + zm, Blocks.field_150346_d, 0, 3);
/*     */               
/* 171 */               itemstack.func_77972_a(1, player);
/* 172 */               Thaumcraft.proxy.blockSparkle(world, x + xx + xm, y + yy + ym, z + zz + zm, 3, 4);
/*     */               
/* 174 */               player.func_71038_i();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 182 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isEffectiveAgainst(Block block) {
/* 186 */     for (int var3 = 0; var3 < isEffective.length; var3++) {
/* 187 */       if (isEffective[var3] == block) {
/* 188 */         return true;
/*     */       }
/*     */     }
/* 191 */     return false;
/*     */   }
/*     */   
/* 194 */   int side = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
/*     */   {
/* 200 */     MovingObjectPosition movingobjectposition = BlockUtils.getTargetBlock(player.field_70170_p, player, true);
/*     */     
/* 202 */     if ((movingobjectposition != null) && 
/* 203 */       (movingobjectposition.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK)) {
/* 204 */       this.side = movingobjectposition.field_72310_e;
/*     */     }
/*     */     
/* 207 */     return super.onBlockStartBreak(itemstack, X, Y, Z, player);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_150894_a(ItemStack stack, World world, Block bi, int x, int y, int z, EntityLivingBase ent)
/*     */   {
/* 213 */     if (ent.func_70093_af()) {
/* 214 */       return super.func_150894_a(stack, world, bi, x, y, z, ent);
/*     */     }
/* 216 */     if (!ent.field_70170_p.field_72995_K)
/*     */     {
/* 218 */       int md = world.func_72805_g(x, y, z);
/* 219 */       if ((ForgeHooks.isToolEffective(stack, bi, md)) || (isEffectiveAgainst(bi)))
/*     */       {
/*     */ 
/* 222 */         for (int aa = -1; aa <= 1; aa++)
/* 223 */           for (int bb = -1; bb <= 1; bb++)
/*     */           {
/* 225 */             int xx = 0;
/* 226 */             int yy = 0;
/* 227 */             int zz = 0;
/*     */             
/* 229 */             if (this.side <= 1) {
/* 230 */               xx = aa;
/* 231 */               zz = bb;
/* 232 */             } else if (this.side <= 3) {
/* 233 */               xx = aa;
/* 234 */               yy = bb;
/*     */             } else {
/* 236 */               zz = aa;
/* 237 */               yy = bb;
/*     */             }
/*     */             
/* 240 */             if ((!(ent instanceof EntityPlayer)) || (world.func_72962_a((EntityPlayer)ent, x + xx, y + yy, z + zz)))
/*     */             {
/*     */ 
/* 243 */               Block bl = world.func_147439_a(x + xx, y + yy, z + zz);
/* 244 */               md = world.func_72805_g(x + xx, y + yy, z + zz);
/*     */               
/* 246 */               if ((bl.func_149712_f(world, x + xx, y + yy, z + zz) >= 0.0F) && ((ForgeHooks.isToolEffective(stack, bl, md)) || (isEffectiveAgainst(bl))))
/*     */               {
/*     */ 
/* 249 */                 stack.func_77972_a(1, ent);
/* 250 */                 BlockUtils.harvestBlock(world, (EntityPlayer)ent, x + xx, y + yy, z + zz, true, 3);
/*     */               }
/*     */             }
/*     */           } }
/*     */     }
/* 255 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ArrayList<BlockCoordinates> getArchitectBlocks(ItemStack focusstack, World world, int x, int y, int z, int side, EntityPlayer player)
/*     */   {
/* 262 */     ArrayList<BlockCoordinates> b = new ArrayList();
/* 263 */     if (!player.func_70093_af()) { return b;
/*     */     }
/* 265 */     int xm = ForgeDirection.getOrientation(side).offsetX;
/* 266 */     int ym = ForgeDirection.getOrientation(side).offsetY;
/* 267 */     int zm = ForgeDirection.getOrientation(side).offsetZ;
/*     */     
/* 269 */     for (int aa = -1; aa <= 1; aa++) {
/* 270 */       for (int bb = -1; bb <= 1; bb++) {
/* 271 */         int xx = 0;
/* 272 */         int yy = 0;
/* 273 */         int zz = 0;
/* 274 */         byte o = getOrientation(focusstack);
/* 275 */         if (o == 1) {
/* 276 */           yy = bb;
/* 277 */           if (side <= 1) {
/* 278 */             int l = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/* 279 */             if ((l == 0) || (l == 2)) {
/* 280 */               xx = aa;
/*     */             } else {
/* 282 */               zz = aa;
/*     */             }
/* 284 */           } else if (side <= 3) {
/* 285 */             zz = aa;
/*     */           } else {
/* 287 */             xx = aa;
/*     */           }
/* 289 */         } else if (o == 2)
/*     */         {
/* 291 */           if (side <= 1) {
/* 292 */             int l = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/* 293 */             yy = bb;
/* 294 */             if ((l == 0) || (l == 2)) {
/* 295 */               xx = aa;
/*     */             } else {
/* 297 */               zz = aa;
/*     */             }
/*     */           } else {
/* 300 */             zz = bb;
/* 301 */             xx = aa;
/*     */           }
/*     */         }
/* 304 */         else if (side <= 1) {
/* 305 */           xx = aa;
/* 306 */           zz = bb;
/* 307 */         } else if (side <= 3) {
/* 308 */           xx = aa;
/* 309 */           yy = bb;
/*     */         } else {
/* 311 */           zz = aa;
/* 312 */           yy = bb;
/*     */         }
/*     */         
/*     */ 
/* 316 */         Block b2 = world.func_147439_a(x + xx + xm, y + yy + ym, z + zz + zm);
/* 317 */         if ((world.func_147437_c(x + xx + xm, y + yy + ym, z + zz + zm)) || (b2 == Blocks.field_150395_bd) || (b2 == Blocks.field_150329_H) || (b2.func_149688_o() == Material.field_151586_h) || (b2 == Blocks.field_150330_I) || (b2.isReplaceable(world, x + xx + xm, y + yy + ym, z + zz + zm)))
/*     */         {
/*     */ 
/*     */ 
/* 321 */           b.add(new BlockCoordinates(x + xx + xm, y + yy + ym, z + zz + zm));
/*     */         }
/*     */       }
/*     */     }
/* 325 */     return b;
/*     */   }
/*     */   
/*     */   public boolean showAxis(ItemStack stack, World world, EntityPlayer player, int side, IArchitect.EnumAxis axis)
/*     */   {
/* 330 */     return false;
/*     */   }
/*     */   
/*     */   public static byte getOrientation(ItemStack stack) {
/* 334 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("or"))) {
/* 335 */       return stack.field_77990_d.func_74771_c("or");
/*     */     }
/* 337 */     return 0;
/*     */   }
/*     */   
/*     */   public static void setOrientation(ItemStack stack, byte o) {
/* 341 */     if (!stack.func_77942_o()) stack.func_77982_d(new NBTTagCompound());
/* 342 */     if (stack.func_77942_o()) {
/* 343 */       stack.field_77990_d.func_74774_a("or", (byte)(o % 3));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemElementalShovel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */