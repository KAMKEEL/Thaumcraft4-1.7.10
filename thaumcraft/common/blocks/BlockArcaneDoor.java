/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.IconFlipped;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.tiles.TileOwned;
/*     */ 
/*     */ public class BlockArcaneDoor extends BlockContainer
/*     */ {
/*     */   public IIcon[] icon;
/*     */   
/*     */   public BlockArcaneDoor()
/*     */   {
/*  36 */     super(Material.field_151573_f);
/*  37 */     func_149672_a(field_149777_j);
/*  38 */     func_149649_H();
/*  39 */     func_149752_b(999.0F);
/*  40 */     func_149711_c(Config.wardedStone ? -1.0F : 15.0F);
/*     */     
/*  42 */     float var3 = 0.5F;
/*  43 */     float var4 = 1.0F;
/*  44 */     func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  51 */     this.icon = new IIcon[4];
/*  52 */     this.icon[0] = ir.func_94245_a("thaumcraft:adoorbot");
/*  53 */     this.icon[1] = ir.func_94245_a("thaumcraft:adoortop");
/*  54 */     this.icon[2] = new IconFlipped(this.icon[0], true, false);
/*  55 */     this.icon[3] = new IconFlipped(this.icon[1], true, false);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/*  61 */     return this.icon[1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/*  71 */     if ((par5 != 0) && (par5 != 1))
/*     */     {
/*  73 */       int i1 = getFullMetadata(par1IBlockAccess, par2, par3, par4);
/*  74 */       int j1 = i1 & 0x3;
/*  75 */       boolean flag = (i1 & 0x4) != 0;
/*  76 */       boolean flag1 = false;
/*  77 */       boolean flag2 = (i1 & 0x8) != 0;
/*     */       
/*  79 */       if (flag)
/*     */       {
/*  81 */         if ((j1 == 0) && (par5 == 2))
/*     */         {
/*  83 */           flag1 = !flag1;
/*     */         }
/*  85 */         else if ((j1 == 1) && (par5 == 5))
/*     */         {
/*  87 */           flag1 = !flag1;
/*     */         }
/*  89 */         else if ((j1 == 2) && (par5 == 3))
/*     */         {
/*  91 */           flag1 = !flag1;
/*     */         }
/*  93 */         else if ((j1 == 3) && (par5 == 4))
/*     */         {
/*  95 */           flag1 = !flag1;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 100 */         if ((j1 == 0) && (par5 == 5))
/*     */         {
/* 102 */           flag1 = !flag1;
/*     */         }
/* 104 */         else if ((j1 == 1) && (par5 == 3))
/*     */         {
/* 106 */           flag1 = !flag1;
/*     */         }
/* 108 */         else if ((j1 == 2) && (par5 == 4))
/*     */         {
/* 110 */           flag1 = !flag1;
/*     */         }
/* 112 */         else if ((j1 == 3) && (par5 == 2))
/*     */         {
/* 114 */           flag1 = !flag1;
/*     */         }
/*     */         
/* 117 */         if ((i1 & 0x10) != 0)
/*     */         {
/* 119 */           flag1 = !flag1;
/*     */         }
/*     */       }
/*     */       
/* 123 */       return this.icon[(0 + 0)];
/*     */     }
/*     */     
/*     */ 
/* 127 */     return this.icon[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 138 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 155 */     int var5 = getFullMetadata(par1IBlockAccess, par2, par3, par4);
/* 156 */     return (var5 & 0x4) != 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 165 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/* 174 */     return 7;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4)
/*     */   {
/* 185 */     func_149719_a(par1World, par2, par3, par4);
/* 186 */     return super.func_149633_g(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/* 196 */     func_149719_a(par1World, par2, par3, par4);
/* 197 */     return super.func_149668_a(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 206 */     setDoorRotation(getFullMetadata(par1IBlockAccess, par2, par3, par4));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDoorOrientation(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 214 */     return getFullMetadata(par1IBlockAccess, par2, par3, par4) & 0x3;
/*     */   }
/*     */   
/*     */   public boolean isDoorOpen(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 219 */     return (getFullMetadata(par1IBlockAccess, par2, par3, par4) & 0x4) != 0;
/*     */   }
/*     */   
/*     */   private void setDoorRotation(int par1)
/*     */   {
/* 224 */     float var2 = 0.1875F;
/* 225 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
/* 226 */     int var3 = par1 & 0x3;
/* 227 */     boolean var4 = (par1 & 0x4) != 0;
/* 228 */     boolean var5 = (par1 & 0x10) != 0;
/*     */     
/* 230 */     if (var3 == 0)
/*     */     {
/* 232 */       if (var4)
/*     */       {
/* 234 */         if (!var5)
/*     */         {
/* 236 */           func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */         }
/*     */         else
/*     */         {
/* 240 */           func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */         
/*     */       }
/*     */       else {
/* 245 */         func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */       }
/*     */     }
/* 248 */     else if (var3 == 1)
/*     */     {
/* 250 */       if (var4)
/*     */       {
/* 252 */         if (!var5)
/*     */         {
/* 254 */           func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */         else
/*     */         {
/* 258 */           func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */         }
/*     */         
/*     */       }
/*     */       else {
/* 263 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */       }
/*     */     }
/* 266 */     else if (var3 == 2)
/*     */     {
/* 268 */       if (var4)
/*     */       {
/* 270 */         if (!var5)
/*     */         {
/* 272 */           func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */         else
/*     */         {
/* 276 */           func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */         }
/*     */         
/*     */       }
/*     */       else {
/* 281 */         func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */       }
/*     */     }
/* 284 */     else if (var3 == 3)
/*     */     {
/* 286 */       if (var4)
/*     */       {
/* 288 */         if (!var5)
/*     */         {
/* 290 */           func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */         }
/*     */         else
/*     */         {
/* 294 */           func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */         
/*     */       }
/*     */       else {
/* 299 */         func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149699_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9)
/*     */   {
/* 316 */     if (!w.field_72995_K) {
/* 317 */       TileEntity tile = w.func_147438_o(x, y, z);
/* 318 */       if ((tile != null) && ((tile instanceof TileOwned))) {
/* 319 */         if ((p.func_70005_c_().equals(((TileOwned)tile).owner)) || (((TileOwned)tile).accessList.contains("0" + p.func_70005_c_())) || (((TileOwned)tile).accessList.contains("1" + p.func_70005_c_())))
/*     */         {
/*     */ 
/*     */ 
/* 323 */           int var10 = getFullMetadata(w, x, y, z);
/* 324 */           int var11 = var10 & 0x7;
/* 325 */           var11 ^= 0x4;
/*     */           
/* 327 */           if ((var10 & 0x8) == 0)
/*     */           {
/* 329 */             w.func_72921_c(x, y, z, var11, 2);
/* 330 */             w.func_147458_c(x, y, z, x, y, z);
/* 331 */             playDoorSound(w, x, y, z);
/*     */           }
/*     */           else
/*     */           {
/* 335 */             w.func_72921_c(x, y - 1, z, var11, 2);
/* 336 */             w.func_147458_c(x, y - 1, z, x, y, z);
/* 337 */             playDoorSound(w, x, y, z);
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 342 */           p.func_145747_a(new ChatComponentTranslation("The door refuses to budge.", new Object[0]));
/* 343 */           w.func_72908_a(x, y, z, "thaumcraft:doorfail", 0.66F, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/* 347 */     return true;
/*     */   }
/*     */   
/*     */   private void playDoorSound(World w, int x, int y, int z)
/*     */   {
/* 352 */     if (Math.random() < 0.5D)
/*     */     {
/* 354 */       w.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.door_open", 1.0F, w.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */     }
/*     */     else
/*     */     {
/* 358 */       w.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.door_close", 1.0F, w.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onPoweredBlockChange(World par1World, int par2, int par3, int par4, boolean par5)
/*     */   {
/* 369 */     int var6 = getFullMetadata(par1World, par2, par3, par4);
/* 370 */     boolean var7 = (var6 & 0x4) != 0;
/*     */     
/* 372 */     if (var7 != par5)
/*     */     {
/* 374 */       int var8 = var6 & 0x7;
/* 375 */       var8 ^= 0x4;
/*     */       
/* 377 */       if ((var6 & 0x8) == 0)
/*     */       {
/* 379 */         par1World.func_72921_c(par2, par3, par4, var8, 2);
/* 380 */         par1World.func_147458_c(par2, par3, par4, par2, par3, par4);
/*     */       }
/*     */       else
/*     */       {
/* 384 */         par1World.func_72921_c(par2, par3 - 1, par4, var8, 2);
/* 385 */         par1World.func_147458_c(par2, par3 - 1, par4, par2, par3, par4);
/*     */       }
/*     */       
/* 388 */       par1World.func_72889_a((EntityPlayer)null, 1003, par2, par3, par4, 0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/* 399 */     int var6 = par1World.func_72805_g(par2, par3, par4);
/*     */     
/* 401 */     if (par5 == ConfigBlocks.blockWoodenDevice) {
/* 402 */       ArrayList<String> users = new ArrayList();
/* 403 */       TileEntity tile = par1World.func_147438_o(par2, par3, par4);
/* 404 */       if ((tile != null) && ((tile instanceof TileOwned))) {
/* 405 */         users.add(((TileOwned)tile).owner);
/* 406 */         for (String u : ((TileOwned)tile).accessList) {
/* 407 */           users.add(u.substring(1));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 412 */       int open = 0;
/*     */       TileOwned to;
/* 414 */       for (int a = 2; a <= 5; a++) {
/* 415 */         ForgeDirection dir = ForgeDirection.getOrientation(a);
/* 416 */         Block bi = par1World.func_147439_a(par2 + dir.offsetX, par3 + dir.offsetY, par4 + dir.offsetZ);
/* 417 */         int md = par1World.func_72805_g(par2 + dir.offsetX, par3 + dir.offsetY, par4 + dir.offsetZ);
/* 418 */         TileOwned to; if ((bi == ConfigBlocks.blockWoodenDevice) && (md == 3)) {
/* 419 */           to = (TileOwned)par1World.func_147438_o(par2 + dir.offsetX, par3 + dir.offsetY, par4 + dir.offsetZ);
/* 420 */           if ((to != null) && ((to instanceof TileOwned))) {
/* 421 */             for (String u : users) {
/* 422 */               if ((to.owner.equals(u)) || (to.accessList.contains(u))) {
/* 423 */                 open = 1;
/*     */                 break label442;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 429 */         else if ((bi == ConfigBlocks.blockWoodenDevice) && (md == 2)) {
/* 430 */           to = (TileOwned)par1World.func_147438_o(par2 + dir.offsetX, par3 + dir.offsetY, par4 + dir.offsetZ);
/* 431 */           if ((to != null) && ((to instanceof TileOwned)))
/*     */           {
/* 433 */             for (String u : users)
/* 434 */               if ((to.owner.equals(u)) || (to.accessList.contains(u))) {
/* 435 */                 open = -1;
/* 436 */                 break;
/*     */               }
/*     */           }
/*     */         }
/*     */       }
/*     */       label442:
/* 442 */       if (open != 0) onPoweredBlockChange(par1World, par2, par3, par4, open == 1);
/*     */     }
/* 444 */     else if ((var6 & 0x8) == 0)
/*     */     {
/* 446 */       boolean var7 = false;
/*     */       
/* 448 */       if (par1World.func_147439_a(par2, par3 + 1, par4) != this)
/*     */       {
/* 450 */         par1World.func_147468_f(par2, par3, par4);
/* 451 */         var7 = true;
/*     */       }
/*     */       
/* 454 */       if (var7)
/*     */       {
/* 456 */         if (!par1World.field_72995_K)
/*     */         {
/* 458 */           func_149697_b(par1World, par2, par3, par4, var6, 0);
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 464 */       if (par1World.func_147439_a(par2, par3 - 1, par4) != this)
/*     */       {
/* 466 */         par1World.func_147468_f(par2, par3, par4);
/*     */       }
/*     */       
/* 469 */       if ((par5 != net.minecraft.init.Blocks.field_150350_a) && (par5 != this))
/*     */       {
/* 471 */         func_149695_a(par1World, par2, par3 - 1, par4, par5);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3)
/*     */   {
/* 483 */     return (par1 & 0x8) != 0 ? Item.func_150899_d(0) : Config.wardedStone ? Item.func_150899_d(0) : ConfigItems.itemArcaneDoor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MovingObjectPosition func_149731_a(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3)
/*     */   {
/* 493 */     func_149719_a(par1World, par2, par3, par4);
/* 494 */     return super.func_149731_a(par1World, par2, par3, par4, par5Vec3, par6Vec3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4)
/*     */   {
/* 503 */     return par3 < 255;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_149656_h()
/*     */   {
/* 513 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getFullMetadata(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 521 */     int var5 = par1IBlockAccess.func_72805_g(par2, par3, par4);
/* 522 */     boolean var6 = (var5 & 0x8) != 0;
/*     */     int var8;
/*     */     int var7;
/*     */     int var8;
/* 526 */     if (var6)
/*     */     {
/* 528 */       int var7 = par1IBlockAccess.func_72805_g(par2, par3 - 1, par4);
/* 529 */       var8 = var5;
/*     */     }
/*     */     else
/*     */     {
/* 533 */       var7 = var5;
/* 534 */       var8 = par1IBlockAccess.func_72805_g(par2, par3 + 1, par4);
/*     */     }
/*     */     
/* 537 */     boolean var9 = (var8 & 0x1) != 0;
/* 538 */     return var7 & 0x7 | (var6 ? 8 : 0) | (var9 ? 16 : 0);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int m)
/*     */   {
/* 543 */     return new TileOwned();
/*     */   }
/*     */   
/*     */   public boolean canHarvestBlock(EntityPlayer player, int meta)
/*     */   {
/* 548 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
/*     */   {
/* 553 */     return false;
/*     */   }
/*     */   
/*     */   public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockArcaneDoor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */