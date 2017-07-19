/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.EnumPlantType;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCustomPlantItem
/*     */   extends ItemBlock
/*     */ {
/*     */   public BlockCustomPlantItem(Block par1)
/*     */   {
/*  26 */     super(par1);
/*  27 */     func_77656_e(0);
/*  28 */     func_77627_a(true);
/*     */   }
/*     */   
/*  31 */   public IIcon[] icon = new IIcon[6];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir) {
/*  35 */     this.icon[0] = ir.func_94245_a("thaumcraft:greatwoodsapling");
/*  36 */     this.icon[1] = ir.func_94245_a("thaumcraft:silverwoodsapling");
/*  37 */     this.icon[2] = ir.func_94245_a("thaumcraft:shimmerleaf");
/*  38 */     this.icon[3] = ir.func_94245_a("thaumcraft:cinderpearl");
/*  39 */     this.icon[4] = ir.func_94245_a("thaumcraft:purifier_seed");
/*  40 */     this.icon[5] = ir.func_94245_a("thaumcraft:manashroom");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int meta)
/*     */   {
/*  46 */     return this.icon[meta];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_77647_b(int par1)
/*     */   {
/*  53 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack par1ItemStack)
/*     */   {
/*  59 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
/*     */   {
/*  65 */     if (side != 1)
/*     */     {
/*  67 */       return false;
/*     */     }
/*  69 */     if ((player.func_82247_a(x, y, z, side, stack)) && (player.func_82247_a(x, y + 1, z, side, stack)))
/*     */     {
/*     */ 
/*  72 */       if ((world.func_147439_a(x, y, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, new CustomPlantTypes(stack.func_77960_j()))) && (world.func_147437_c(x, y + 1, z)))
/*     */       {
/*  74 */         world.func_147465_d(x, y + 1, z, ConfigBlocks.blockCustomPlant, stack.func_77960_j(), 3);
/*  75 */         world.func_72908_a(x + 0.5F, y + 1.5F, z + 0.5F, ConfigBlocks.blockCustomPlant.field_149762_H.func_150498_e(), (ConfigBlocks.blockCustomPlant.field_149762_H.func_150497_c() + 1.0F) / 2.0F, ConfigBlocks.blockCustomPlant.field_149762_H.func_150494_d() * 0.8F);
/*  76 */         stack.field_77994_a -= 1;
/*  77 */         return true;
/*     */       }
/*     */       
/*     */ 
/*  81 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   private class CustomPlantTypes
/*     */     implements IPlantable
/*     */   {
/*  92 */     int md = 0;
/*     */     
/*     */     public CustomPlantTypes(int md) {
/*  95 */       this.md = md;
/*     */     }
/*     */     
/*     */     public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
/*     */     {
/* 100 */       if (this.md == 3) return EnumPlantType.Desert;
/* 101 */       if ((this.md == 4) || (this.md == 5)) return EnumPlantType.Cave;
/* 102 */       return EnumPlantType.Plains;
/*     */     }
/*     */     
/*     */     public Block getPlant(IBlockAccess world, int x, int y, int z)
/*     */     {
/* 107 */       return ConfigBlocks.blockCustomPlant;
/*     */     }
/*     */     
/*     */     public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
/*     */     {
/* 112 */       return this.md;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCustomPlantItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */