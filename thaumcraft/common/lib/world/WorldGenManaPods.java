/*    */ package thaumcraft.common.lib.world;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.feature.WorldGenerator;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.tiles.TileManaPod;
/*    */ 
/*    */ public class WorldGenManaPods extends WorldGenerator
/*    */ {
/*    */   public boolean func_76484_a(World par1World, Random par2Random, int x, int y, int z)
/*    */   {
/* 14 */     int l = x;
/* 15 */     for (int i1 = z; y < Math.min(128, par1World.func_72976_f(x, z)); y++) {
/* 16 */       if ((par1World.func_147437_c(x, y, z)) && (par1World.func_147437_c(x, y - 1, z)))
/*    */       {
/* 18 */         if (ConfigBlocks.blockManaPod.func_149707_d(par1World, x, y, z, 0))
/*    */         {
/* 20 */           par1World.func_147465_d(x, y, z, ConfigBlocks.blockManaPod, 2 + par2Random.nextInt(5), 2);
/*    */           
/*    */ 
/* 23 */           TileEntity tile = par1World.func_147438_o(x, y, z);
/* 24 */           if ((tile == null) || (!(tile instanceof TileManaPod))) break;
/* 25 */           ((TileManaPod)tile).checkGrowth(); break;
/*    */         }
/*    */         
/*    */       }
/*    */       else
/*    */       {
/* 31 */         x = l + par2Random.nextInt(4) - par2Random.nextInt(4);
/* 32 */         z = i1 + par2Random.nextInt(4) - par2Random.nextInt(4);
/*    */       }
/*    */     }
/*    */     
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/WorldGenManaPods.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */