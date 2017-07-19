/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class TileEtherealBloom extends TileEntity
/*    */ {
/* 12 */   public int counter = 0;
/* 13 */   public int growthCounter = 0;
/*    */   
/*    */   public boolean canUpdate()
/*    */   {
/* 17 */     return true;
/*    */   }
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 22 */     super.func_145845_h();
/* 23 */     if (this.counter == 0) this.counter = this.field_145850_b.field_73012_v.nextInt(100);
/* 24 */     this.counter += 1;
/*    */     
/* 26 */     if ((!this.field_145850_b.field_72995_K) && (this.counter % 20 == 0))
/*    */     {
/* 28 */       int x = this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
/* 29 */       int z = this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
/*    */       
/* 31 */       if (((this.field_145850_b.func_72807_a(x + this.field_145851_c, z + this.field_145849_e).field_76756_M == Config.biomeTaintID) || (this.field_145850_b.func_72807_a(x + this.field_145851_c, z + this.field_145849_e).field_76756_M == Config.biomeEerieID) || (this.field_145850_b.func_72807_a(x + this.field_145851_c, z + this.field_145849_e).field_76756_M == Config.biomeMagicalForestID)) && (func_145835_a(x + this.field_145851_c + 0.5D, this.field_145848_d, z + this.field_145849_e + 0.5D) <= 81.0D))
/*    */       {
/*    */ 
/*    */ 
/*    */ 
/* 36 */         BiomeGenBase[] biomesForGeneration = null;
/* 37 */         biomesForGeneration = this.field_145850_b.func_72959_q().func_76933_b(biomesForGeneration, x + this.field_145851_c, z + this.field_145849_e, 1, 1);
/*    */         
/* 39 */         if ((biomesForGeneration != null) && (biomesForGeneration[0] != null)) {
/* 40 */           BiomeGenBase biome = biomesForGeneration[0];
/* 41 */           if (biome.field_76756_M == thaumcraft.common.lib.world.ThaumcraftWorldGenerator.biomeTaint.field_76756_M) {
/* 42 */             biome = BiomeGenBase.field_76772_c;
/*    */           }
/* 44 */           Utils.setBiomeAt(this.field_145850_b, x + this.field_145851_c, z + this.field_145849_e, biome);
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 50 */     if ((this.field_145850_b.field_72995_K) && 
/* 51 */       (this.growthCounter == 0)) {
/* 52 */       this.field_145850_b.func_72980_b(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "thaumcraft:roots", 1.0F, 0.6F, false);
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 57 */     this.growthCounter += 1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileEtherealBloom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */