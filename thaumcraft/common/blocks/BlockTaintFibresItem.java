/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*    */ 
/*    */ public class BlockTaintFibresItem
/*    */   extends ItemBlock
/*    */ {
/*    */   public BlockTaintFibresItem(Block par1)
/*    */   {
/* 17 */     super(par1);
/* 18 */     func_77656_e(0);
/* 19 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_77647_b(int par1)
/*    */   {
/* 28 */     return par1;
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_82790_a(ItemStack par1ItemStack, int par2)
/*    */   {
/* 34 */     return ThaumcraftWorldGenerator.biomeTaint.field_76790_z;
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 40 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int meta)
/*    */   {
/* 46 */     return ((BlockTaintFibres)thaumcraft.common.config.ConfigBlocks.blockTaintFibres).icon[meta];
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockTaintFibresItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */