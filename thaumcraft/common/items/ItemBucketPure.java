/*     */ package thaumcraft.common.items;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemBucketPure extends Item
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemBucketPure()
/*     */   {
/*  26 */     func_77637_a(Thaumcraft.tabTC);
/*  27 */     func_77627_a(false);
/*  28 */     func_77625_d(1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  37 */     this.icon = ir.func_94245_a("thaumcraft:bucket_pure");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  43 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
/*     */   {
/*  49 */     boolean flag = true;
/*  50 */     MovingObjectPosition movingobjectposition = func_77621_a(p_77659_2_, p_77659_3_, flag);
/*     */     
/*     */ 
/*  53 */     if (movingobjectposition == null) {
/*  54 */       return p_77659_1_;
/*     */     }
/*  56 */     if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  57 */       int i = movingobjectposition.field_72311_b;
/*  58 */       int j = movingobjectposition.field_72312_c;
/*  59 */       int k = movingobjectposition.field_72309_d;
/*     */       
/*  61 */       if (movingobjectposition.field_72310_e == 0)
/*     */       {
/*  63 */         j--;
/*     */       }
/*     */       
/*  66 */       if (movingobjectposition.field_72310_e == 1)
/*     */       {
/*  68 */         j++;
/*     */       }
/*     */       
/*  71 */       if (movingobjectposition.field_72310_e == 2)
/*     */       {
/*  73 */         k--;
/*     */       }
/*     */       
/*  76 */       if (movingobjectposition.field_72310_e == 3)
/*     */       {
/*  78 */         k++;
/*     */       }
/*     */       
/*  81 */       if (movingobjectposition.field_72310_e == 4)
/*     */       {
/*  83 */         i--;
/*     */       }
/*     */       
/*  86 */       if (movingobjectposition.field_72310_e == 5)
/*     */       {
/*  88 */         i++;
/*     */       }
/*     */       
/*  91 */       if (!p_77659_3_.func_82247_a(i, j, k, movingobjectposition.field_72310_e, p_77659_1_))
/*     */       {
/*  93 */         return p_77659_1_;
/*     */       }
/*     */       
/*  96 */       if ((tryPlaceContainedLiquid(p_77659_2_, i, j, k)) && (!p_77659_3_.field_71075_bZ.field_75098_d))
/*     */       {
/*  98 */         return new ItemStack(Items.field_151133_ar);
/*     */       }
/*     */     }
/*     */     
/* 102 */     return p_77659_1_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean tryPlaceContainedLiquid(World world, int x, int y, int z)
/*     */   {
/* 111 */     Material material = world.func_147439_a(x, y, z).func_149688_o();
/* 112 */     boolean flag = !material.func_76220_a();
/*     */     
/* 114 */     if ((!world.func_147437_c(x, y, z)) && (!flag)) {
/* 115 */       return false;
/*     */     }
/* 117 */     if ((!world.field_72995_K) && (flag) && (!material.func_76224_d())) {
/* 118 */       world.func_147480_a(x, y, z, true);
/*     */     }
/*     */     
/* 121 */     world.func_147465_d(x, y, z, thaumcraft.common.config.ConfigBlocks.blockFluidPure, 0, 3);
/*     */     
/* 123 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemBucketPure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */