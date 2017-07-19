/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.tiles.TileCrystal;
/*    */ 
/*    */ public class BlockCrystalItem
/*    */   extends ItemBlock
/*    */ {
/*    */   public BlockCrystalItem(Block par1)
/*    */   {
/* 15 */     super(par1);
/* 16 */     func_77656_e(0);
/* 17 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_77647_b(int par1)
/*    */   {
/* 23 */     return par1;
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 29 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */   
/*    */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
/*    */   {
/* 34 */     boolean placed = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
/*    */     
/* 36 */     if ((placed) && (metadata <= 7)) {
/*    */       try {
/* 38 */         TileCrystal ts = (TileCrystal)world.func_147438_o(x, y, z);
/* 39 */         ts.orientation = ((short)side);
/*    */       }
/*    */       catch (Exception e) {}
/*    */     }
/* 43 */     return placed;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCrystalItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */