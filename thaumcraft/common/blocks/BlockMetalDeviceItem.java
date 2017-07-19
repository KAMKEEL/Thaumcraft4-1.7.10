/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileArcaneLamp;
/*     */ import thaumcraft.common.tiles.TileArcaneLampFertility;
/*     */ import thaumcraft.common.tiles.TileArcaneLampGrowth;
/*     */ import thaumcraft.common.tiles.TileVisRelay;
/*     */ 
/*     */ public class BlockMetalDeviceItem extends ItemBlock
/*     */ {
/*     */   public BlockMetalDeviceItem(Block par1)
/*     */   {
/*  21 */     super(par1);
/*  22 */     func_77656_e(0);
/*  23 */     func_77627_a(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_77647_b(int par1)
/*     */   {
/*  30 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack par1ItemStack)
/*     */   {
/*  36 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
/*     */   {
/*  43 */     if ((stack.func_77960_j() == 0) || (stack.func_77960_j() == 1) || (stack.func_77960_j() == 2) || (stack.func_77960_j() == 3) || (stack.func_77960_j() == 5) || (stack.func_77960_j() == 6) || (stack.func_77960_j() == 7) || (stack.func_77960_j() == 8) || (stack.func_77960_j() == 9) || (stack.func_77960_j() == 13) || (stack.func_77960_j() == 14))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  54 */       return super.func_77648_a(stack, player, world, x, y, z, side, par8, par9, par10);
/*     */     }
/*  56 */     Block bi = world.func_147439_a(x, y, z);
/*  57 */     int md = world.func_72805_g(x, y, z);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  65 */     if (stack.func_77960_j() == 12) {
/*  66 */       if ((bi == ConfigBlocks.blockMetalDevice) && ((md == 10) || (md == 11)))
/*  67 */         return super.func_77648_a(stack, player, world, x, y, z, side, par8, par9, par10);
/*  68 */       return false;
/*     */     }
/*     */     
/*  71 */     if ((bi == ConfigBlocks.blockMetalDevice) && (md == 0))
/*     */     {
/*  73 */       if ((side == 0) || (side == 1))
/*     */       {
/*     */ 
/*  76 */         return false;
/*     */       }
/*     */       
/*  79 */       if (side == 2)
/*     */       {
/*  81 */         z--;
/*     */       }
/*     */       
/*  84 */       if (side == 3)
/*     */       {
/*  86 */         z++;
/*     */       }
/*     */       
/*  89 */       if (side == 4)
/*     */       {
/*  91 */         x--;
/*     */       }
/*     */       
/*  94 */       if (side == 5)
/*     */       {
/*  96 */         x++;
/*     */       }
/*     */     }
/*     */     
/* 100 */     if (stack.field_77994_a == 0)
/*     */     {
/* 102 */       return false;
/*     */     }
/* 104 */     if (!player.func_82247_a(x, y, z, side, stack))
/*     */     {
/* 106 */       return false;
/*     */     }
/* 108 */     if ((y == 255) && (this.field_150939_a.func_149688_o().func_76220_a()))
/*     */     {
/* 110 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 114 */     Block var11 = world.func_147439_a(x, y, z);
/* 115 */     if ((world.func_147437_c(x, y, z)) || (var11.isReplaceable(world, x, y, z)) || (var11 == Blocks.field_150395_bd) || (var11 == Blocks.field_150329_H) || (var11 == Blocks.field_150330_I) || (var11 == Blocks.field_150431_aC))
/*     */     {
/*     */ 
/*     */ 
/* 119 */       for (int a = 2; a < 6; a++) {
/* 120 */         ForgeDirection dir = ForgeDirection.getOrientation(a);
/* 121 */         int xx = x + dir.offsetX;
/* 122 */         int yy = y + dir.offsetY;
/* 123 */         int zz = z + dir.offsetZ;
/* 124 */         Block bid = world.func_147439_a(xx, yy, zz);
/* 125 */         int meta = world.func_72805_g(xx, yy, zz);
/* 126 */         if ((bid == ConfigBlocks.blockMetalDevice) && (meta == 0) && 
/* 127 */           (placeBlockAt(stack, player, world, x, y, z, side, par8, par9, par10, stack.func_77960_j())))
/*     */         {
/* 129 */           world.func_72908_a(x + 0.5F, y + 0.5F, z + 0.5F, this.field_150939_a.field_149762_H.func_150498_e(), (this.field_150939_a.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.field_150939_a.field_149762_H.func_150494_d() * 0.8F);
/* 130 */           stack.field_77994_a -= 1;
/* 131 */           world.func_147465_d(x, y, z, ConfigBlocks.blockMetalDevice, dir.getOpposite().ordinal() - 1, 3);
/* 132 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 139 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
/*     */   {
/* 147 */     boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
/* 148 */     if (metadata == 7) {
/* 149 */       TileArcaneLamp tile = (TileArcaneLamp)world.func_147438_o(x, y, z);
/* 150 */       if ((tile != null) && ((tile instanceof TileArcaneLamp))) {
/* 151 */         tile.facing = ForgeDirection.getOrientation(side).getOpposite();
/* 152 */         world.func_147471_g(x, y, x);
/*     */       }
/*     */     }
/* 155 */     else if (metadata == 8) {
/* 156 */       TileArcaneLampGrowth tile = (TileArcaneLampGrowth)world.func_147438_o(x, y, z);
/* 157 */       if ((tile != null) && ((tile instanceof TileArcaneLampGrowth))) {
/* 158 */         tile.facing = ForgeDirection.getOrientation(side).getOpposite();
/* 159 */         world.func_147471_g(x, y, x);
/*     */       }
/*     */     }
/* 162 */     else if (metadata == 12) {
/* 163 */       thaumcraft.common.tiles.TileBrainbox tile = (thaumcraft.common.tiles.TileBrainbox)world.func_147438_o(x, y, z);
/* 164 */       if ((tile != null) && ((tile instanceof thaumcraft.common.tiles.TileBrainbox))) {
/* 165 */         tile.facing = ForgeDirection.getOrientation(side).getOpposite();
/* 166 */         world.func_147471_g(x, y, x);
/*     */       }
/*     */     }
/* 169 */     else if (metadata == 13) {
/* 170 */       TileArcaneLampFertility tile = (TileArcaneLampFertility)world.func_147438_o(x, y, z);
/* 171 */       if ((tile != null) && ((tile instanceof TileArcaneLampFertility))) {
/* 172 */         tile.facing = ForgeDirection.getOrientation(side).getOpposite();
/* 173 */         world.func_147471_g(x, y, x);
/*     */       }
/*     */     }
/* 176 */     else if (metadata == 14) {
/* 177 */       TileVisRelay tile = (TileVisRelay)world.func_147438_o(x, y, z);
/* 178 */       if ((tile != null) && ((tile instanceof TileVisRelay))) {
/* 179 */         tile.orientation = ((short)side);
/* 180 */         world.func_147471_g(x, y, x);
/*     */       }
/*     */     }
/* 183 */     return ret;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockMetalDeviceItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */