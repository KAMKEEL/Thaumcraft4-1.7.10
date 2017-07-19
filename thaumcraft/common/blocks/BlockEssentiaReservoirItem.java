/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.common.tiles.TileEssentiaReservoir;
/*    */ 
/*    */ 
/*    */ public class BlockEssentiaReservoirItem
/*    */   extends ItemBlock
/*    */ {
/*    */   public BlockEssentiaReservoirItem(Block par1)
/*    */   {
/* 17 */     super(par1);
/* 18 */     func_77656_e(0);
/* 19 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_77647_b(int par1)
/*    */   {
/* 25 */     return par1;
/*    */   }
/*    */   
/*    */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
/*    */   {
/* 30 */     boolean placed = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
/*    */     
/* 32 */     if (placed) {
/*    */       try {
/* 34 */         TileEssentiaReservoir ts = (TileEssentiaReservoir)world.func_147438_o(x, y, z);
/* 35 */         ts.facing = ForgeDirection.getOrientation(side).getOpposite();
/* 36 */         ts.func_70296_d();
/* 37 */         world.func_147471_g(x, y, z);
/*    */       }
/*    */       catch (Exception e) {}
/*    */     }
/* 41 */     return placed;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockEssentiaReservoirItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */