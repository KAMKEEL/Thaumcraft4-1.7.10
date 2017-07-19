/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.common.tiles.TileFluxScrubber;
/*    */ 
/*    */ public class BlockStoneDeviceItem extends ItemBlock
/*    */ {
/*    */   public BlockStoneDeviceItem(Block par1)
/*    */   {
/* 15 */     super(par1);
/* 16 */     func_77656_e(0);
/* 17 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public int func_77647_b(int par1)
/*    */   {
/* 24 */     return par1;
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 30 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
/*    */   {
/* 39 */     boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
/* 40 */     if (metadata == 14) {
/* 41 */       TileFluxScrubber tile = (TileFluxScrubber)world.func_147438_o(x, y, z);
/* 42 */       if ((tile != null) && ((tile instanceof TileFluxScrubber))) {
/* 43 */         tile.facing = ForgeDirection.getOrientation(side).getOpposite();
/* 44 */         tile.func_70296_d();
/* 45 */         world.func_147471_g(x, y, x);
/*    */       }
/*    */     }
/* 48 */     return ret;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockStoneDeviceItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */