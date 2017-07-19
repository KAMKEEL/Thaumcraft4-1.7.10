/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPistonBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileArcaneBore;
/*     */ import thaumcraft.common.tiles.TileArcaneBoreBase;
/*     */ import thaumcraft.common.tiles.TileBanner;
/*     */ import thaumcraft.common.tiles.TileBellows;
/*     */ 
/*     */ public class BlockWoodenDeviceItem extends ItemBlock
/*     */ {
/*     */   public BlockWoodenDeviceItem(Block par1)
/*     */   {
/*  25 */     super(par1);
/*  26 */     func_77656_e(0);
/*  27 */     func_77627_a(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_77647_b(int par1)
/*     */   {
/*  34 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack stack)
/*     */   {
/*  40 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("color"))) {
/*  41 */       return super.func_77658_a() + "." + stack.func_77960_j() + "." + stack.field_77990_d.func_74771_c("color");
/*     */     }
/*  43 */     return super.func_77658_a() + "." + stack.func_77960_j();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
/*     */   {
/*  51 */     boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
/*  52 */     if (ret) {
/*  53 */       if (metadata == 0) {
/*  54 */         TileEntity tile = world.func_147438_o(x, y, z);
/*  55 */         if ((tile != null) && ((tile instanceof TileBellows))) {
/*  56 */           ForgeDirection dir = ForgeDirection.getOrientation(side).getOpposite();
/*  57 */           ((TileBellows)tile).orientation = ((byte)dir.ordinal());
/*  58 */           int xx = x + dir.offsetX;
/*  59 */           int yy = y + dir.offsetY;
/*  60 */           int zz = z + dir.offsetZ;
/*  61 */           Block bi = world.func_147439_a(xx, yy, zz);
/*  62 */           if ((bi == Blocks.field_150460_al) || (bi == Blocks.field_150470_am)) {
/*  63 */             ((TileBellows)tile).onVanillaFurnace = true;
/*     */           }
/*  65 */           tile.func_70296_d();
/*  66 */           world.func_147471_g(x, y, x);
/*     */         }
/*     */       }
/*  69 */       if (metadata == 4) {
/*  70 */         TileArcaneBoreBase tile = (TileArcaneBoreBase)world.func_147438_o(x, y, z);
/*  71 */         if ((tile != null) && ((tile instanceof TileArcaneBoreBase))) {
/*  72 */           int var6 = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*  73 */           switch (var6) {
/*  74 */           case 0:  tile.orientation = ForgeDirection.getOrientation(2); break;
/*  75 */           case 1:  tile.orientation = ForgeDirection.getOrientation(5); break;
/*  76 */           case 2:  tile.orientation = ForgeDirection.getOrientation(3); break;
/*  77 */           case 3:  tile.orientation = ForgeDirection.getOrientation(4);
/*     */           }
/*  79 */           tile.func_70296_d();
/*  80 */           world.func_147471_g(x, y, x);
/*     */         }
/*     */       }
/*  83 */       if (metadata == 5) {
/*  84 */         TileArcaneBore tile = (TileArcaneBore)world.func_147438_o(x, y, z);
/*  85 */         if ((tile != null) && ((tile instanceof TileArcaneBore))) {
/*  86 */           tile.baseOrientation = ForgeDirection.getOrientation(side);
/*  87 */           int var6 = BlockPistonBase.func_150071_a(world, x, y, z, player);
/*  88 */           tile.orientation = ForgeDirection.getOrientation(var6);
/*  89 */           world.func_147471_g(x, y, x);
/*  90 */           tile.func_70296_d();
/*     */         }
/*     */       }
/*  93 */       if (metadata == 8) {
/*  94 */         TileBanner tile = (TileBanner)world.func_147438_o(x, y, z);
/*  95 */         if (tile != null) {
/*  96 */           if (side <= 1) {
/*  97 */             int i = MathHelper.func_76128_c((player.field_70177_z + 180.0F) * 16.0F / 360.0F + 0.5D) & 0xF;
/*  98 */             tile.setFacing((byte)i);
/*     */           } else {
/* 100 */             tile.setWall(true);
/*     */             
/* 102 */             int i = 0;
/*     */             
/* 104 */             if (side == 2)
/*     */             {
/* 106 */               i = 8;
/*     */             }
/*     */             
/* 109 */             if (side == 4)
/*     */             {
/* 111 */               i = 4;
/*     */             }
/*     */             
/* 114 */             if (side == 5)
/*     */             {
/* 116 */               i = 12;
/*     */             }
/*     */             
/* 119 */             tile.setFacing((byte)i);
/*     */           }
/*     */           
/* 122 */           if (stack.func_77942_o()) {
/* 123 */             if (stack.field_77990_d.func_74779_i("aspect") != null) {
/* 124 */               tile.setAspect(Aspect.getAspect(stack.field_77990_d.func_74779_i("aspect")));
/*     */             }
/* 126 */             if (stack.field_77990_d.func_74764_b("color")) {
/* 127 */               tile.setColor(stack.field_77990_d.func_74771_c("color"));
/*     */             }
/*     */           }
/* 130 */           tile.func_70296_d();
/* 131 */           world.func_147471_g(x, y, z);
/*     */         }
/*     */       }
/*     */     }
/* 135 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_150936_a(World world, int x, int y, int z, int side, EntityPlayer par6EntityPlayer, ItemStack par7ItemStack)
/*     */   {
/* 143 */     if (par7ItemStack.func_77960_j() == 5) {
/* 144 */       if (side > 1) return false;
/* 145 */       if ((world.func_147439_a(x, y, z) != ConfigBlocks.blockWoodenDevice) && (world.func_72805_g(x, y, z) != 4)) { return false;
/*     */       }
/*     */     }
/* 148 */     return super.func_150936_a(world, x, y, z, side, par6EntityPlayer, par7ItemStack);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockWoodenDeviceItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */