/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.common.tiles.TileEssentiaCrystalizer;
/*    */ import thaumcraft.common.tiles.TileTube;
/*    */ 
/*    */ public class BlockTubeItem
/*    */   extends ItemBlock
/*    */ {
/*    */   public BlockTubeItem(Block par1)
/*    */   {
/* 18 */     super(par1);
/* 19 */     func_77656_e(0);
/* 20 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_77647_b(int par1)
/*    */   {
/* 26 */     return par1;
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 32 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
/*    */   {
/* 40 */     boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
/*    */     
/* 42 */     if (ret) {
/* 43 */       TileEntity te = world.func_147438_o(x, y, z);
/* 44 */       if ((te instanceof TileTube)) {
/* 45 */         ((TileTube)te).facing = ForgeDirection.getOrientation(side);
/*    */       }
/* 47 */       if ((te instanceof TileEssentiaCrystalizer)) {
/* 48 */         ((TileEssentiaCrystalizer)te).facing = ForgeDirection.getOrientation(side).getOpposite();
/*    */       }
/*    */     }
/*    */     
/* 52 */     return ret;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockTubeItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */