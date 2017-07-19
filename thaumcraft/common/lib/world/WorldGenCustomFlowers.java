/*    */ package thaumcraft.common.lib.world;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.feature.WorldGenerator;
/*    */ 
/*    */ 
/*    */ public class WorldGenCustomFlowers
/*    */   extends WorldGenerator
/*    */ {
/*    */   private Block plantBlock;
/*    */   private int plantBlockMeta;
/*    */   
/*    */   public WorldGenCustomFlowers(Block bi, int md)
/*    */   {
/* 18 */     this.plantBlock = bi;
/* 19 */     this.plantBlockMeta = md;
/*    */   }
/*    */   
/*    */   public boolean func_76484_a(World world, Random par2Random, int par3, int par4, int par5)
/*    */   {
/* 24 */     for (int var6 = 0; var6 < 18; var6++)
/*    */     {
/* 26 */       int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
/* 27 */       int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
/* 28 */       int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
/*    */       
/* 30 */       if ((world.func_147437_c(var7, var8, var9)) && ((world.func_147439_a(var7, var8 - 1, var9) == Blocks.field_150349_c) || (world.func_147439_a(var7, var8 - 1, var9) == Blocks.field_150354_m)))
/*    */       {
/*    */ 
/*    */ 
/* 34 */         world.func_147465_d(var7, var8, var9, this.plantBlock, this.plantBlockMeta, 3);
/*    */       }
/*    */     }
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/WorldGenCustomFlowers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */