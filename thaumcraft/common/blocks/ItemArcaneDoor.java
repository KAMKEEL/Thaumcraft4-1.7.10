/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.tiles.TileOwned;
/*     */ 
/*     */ public class ItemArcaneDoor extends Item
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemArcaneDoor()
/*     */   {
/*  23 */     this.field_77777_bU = 1;
/*  24 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  32 */     this.icon = ir.func_94245_a("thaumcraft:arcanedoor");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  37 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
/*     */   {
/*  46 */     if (par7 != 1)
/*     */     {
/*  48 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  52 */     par5++;
/*  53 */     Block var11 = thaumcraft.common.config.ConfigBlocks.blockArcaneDoor;
/*     */     
/*  55 */     if ((player.func_82247_a(par4, par5, par6, par7, stack)) && (player.func_82247_a(par4, par5 + 1, par6, par7, stack)))
/*     */     {
/*  57 */       if (!var11.func_149742_c(world, par4, par5, par6))
/*     */       {
/*  59 */         return false;
/*     */       }
/*     */       
/*     */ 
/*  63 */       int var12 = MathHelper.func_76128_c((player.field_70177_z + 180.0F) * 4.0F / 360.0F - 0.5D) & 0x3;
/*  64 */       placeDoorBlock(world, par4, par5, par6, var12, var11, player);
/*  65 */       stack.field_77994_a -= 1;
/*  66 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  71 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void placeDoorBlock(World world, int x, int y, int z, int par4, Block par5Block, EntityPlayer player)
/*     */   {
/*  78 */     byte var6 = 0;
/*  79 */     byte var7 = 0;
/*     */     
/*  81 */     if (par4 == 0)
/*     */     {
/*  83 */       var7 = 1;
/*     */     }
/*     */     
/*  86 */     if (par4 == 1)
/*     */     {
/*  88 */       var6 = -1;
/*     */     }
/*     */     
/*  91 */     if (par4 == 2)
/*     */     {
/*  93 */       var7 = -1;
/*     */     }
/*     */     
/*  96 */     if (par4 == 3)
/*     */     {
/*  98 */       var6 = 1;
/*     */     }
/*     */     
/* 101 */     int var8 = (world.func_147445_c(x - var6, y, z - var7, false) ? 1 : 0) + (world.func_147445_c(x - var6, y + 1, z - var7, false) ? 1 : 0);
/* 102 */     int var9 = (world.func_147445_c(x + var6, y, z + var7, false) ? 1 : 0) + (world.func_147445_c(x + var6, y + 1, z + var7, false) ? 1 : 0);
/* 103 */     boolean var10 = (world.func_147439_a(x - var6, y, z - var7) == par5Block) || (world.func_147439_a(x - var6, y + 1, z - var7) == par5Block);
/* 104 */     boolean var11 = (world.func_147439_a(x + var6, y, z + var7) == par5Block) || (world.func_147439_a(x + var6, y + 1, z + var7) == par5Block);
/* 105 */     boolean var12 = false;
/*     */     
/* 107 */     if ((var10) && (!var11))
/*     */     {
/* 109 */       var12 = true;
/*     */     }
/* 111 */     else if (var9 > var8)
/*     */     {
/* 113 */       var12 = true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 118 */     world.func_147465_d(x, y, z, par5Block, par4, 2);
/* 119 */     TileOwned tad = (TileOwned)world.func_147438_o(x, y, z);
/* 120 */     tad.owner = player.func_70005_c_();
/*     */     
/* 122 */     world.func_147465_d(x, y + 1, z, par5Block, 0x8 | (var12 ? 1 : 0), 2);
/* 123 */     TileOwned tad2 = (TileOwned)world.func_147438_o(x, y + 1, z);
/* 124 */     tad2.owner = player.func_70005_c_();
/*     */     
/*     */ 
/*     */ 
/* 128 */     world.func_147459_d(x, y, z, par5Block);
/* 129 */     world.func_147459_d(x, y + 1, z, par5Block);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/ItemArcaneDoor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */