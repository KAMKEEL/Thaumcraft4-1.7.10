/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockStairs;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCosmeticStairs
/*    */   extends BlockStairs
/*    */ {
/*    */   Block refBlock;
/*    */   int refMeta;
/*    */   
/*    */   public BlockCosmeticStairs(Block p_i45428_1_, int p_i45428_2_)
/*    */   {
/* 24 */     super(p_i45428_1_, p_i45428_2_);
/* 25 */     this.refBlock = p_i45428_1_;
/* 26 */     this.refMeta = p_i45428_2_;
/* 27 */     func_149713_g(0);
/* 28 */     func_149647_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int side)
/*    */   {
/* 34 */     if ((this.refBlock == ConfigBlocks.blockCosmeticSolid) && (this.refMeta == 11)) {
/* 35 */       return this.refBlock.func_149673_e(ba, x, y, z, side + 100);
/*    */     }
/* 37 */     return this.refBlock.func_149691_a(side, this.refMeta);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCosmeticStairs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */