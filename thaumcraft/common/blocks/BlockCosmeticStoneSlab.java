/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockSlab;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class BlockCosmeticStoneSlab
/*    */   extends BlockSlab
/*    */ {
/* 23 */   public static final String[] types = { "arcane", "eldritch" };
/*    */   
/*    */   public BlockCosmeticStoneSlab(boolean p_i45437_1_)
/*    */   {
/* 27 */     super(p_i45437_1_, Material.field_151576_e);
/* 28 */     func_149647_a(Thaumcraft.tabTC);
/* 29 */     func_149713_g(0);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
/*    */   {
/* 39 */     return (p_149691_2_ & 0x7) == 1 ? ConfigBlocks.blockCosmeticSolid.func_149691_a(p_149691_1_, 11) : (p_149691_2_ & 0x7) == 0 ? ConfigBlocks.blockCosmeticSolid.func_149691_a(p_149691_1_, 7) : super.func_149691_a(p_149691_1_, p_149691_2_);
/*    */   }
/*    */   
/*    */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*    */   {
/* 44 */     return Item.func_150898_a(ConfigBlocks.blockSlabStone);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected ItemStack func_149644_j(int p_149644_1_)
/*    */   {
/* 54 */     return new ItemStack(Item.func_150898_a(ConfigBlocks.blockSlabStone), 1, p_149644_1_ & 0x7);
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_150002_b(int p_150002_1_)
/*    */   {
/* 60 */     if ((p_150002_1_ < 0) || (p_150002_1_ >= types.length))
/*    */     {
/* 62 */       p_150002_1_ = 0;
/*    */     }
/*    */     
/* 65 */     return super.func_149739_a() + "." + types[p_150002_1_];
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
/*    */   {
/* 75 */     if (p_149666_1_ != Item.func_150898_a(ConfigBlocks.blockDoubleSlabStone))
/*    */     {
/* 77 */       for (int i = 0; i < types.length; i++)
/*    */       {
/* 79 */         p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister p_149651_1_) {}
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   private static boolean func_150003_a(Block p_150003_0_)
/*    */   {
/* 90 */     return p_150003_0_ == ConfigBlocks.blockSlabStone;
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
/*    */   {
/* 97 */     return this == ConfigBlocks.blockDoubleSlabStone ? Item.func_150898_a(ConfigBlocks.blockSlabStone) : func_150003_a(this) ? Item.func_150898_a(this) : Item.func_150898_a(Blocks.field_150333_U);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCosmeticStoneSlab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */