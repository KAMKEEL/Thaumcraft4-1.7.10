/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.api.TileThaumcraft;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class TileArcaneLamp extends TileThaumcraft
/*    */ {
/* 12 */   public ForgeDirection facing = ForgeDirection.getOrientation(0);
/*    */   
/*    */   public boolean canUpdate()
/*    */   {
/* 16 */     return true;
/*    */   }
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 21 */     if (!this.field_145850_b.field_72995_K) {
/* 22 */       int x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 23 */       int y = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 24 */       int z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 25 */       if (y > this.field_145850_b.func_72976_f(x, z) + 4) y = this.field_145850_b.func_72976_f(x, z) + 4;
/* 26 */       if (y < 5) y = 5;
/* 27 */       if ((this.field_145850_b.func_147437_c(x, y, z)) && (this.field_145850_b.func_147439_a(x, y, z) != ConfigBlocks.blockAiry) && (this.field_145850_b.func_72957_l(x, y, z) < 9))
/*    */       {
/* 29 */         this.field_145850_b.func_147465_d(x, y, z, ConfigBlocks.blockAiry, 3, 3);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 37 */     this.facing = ForgeDirection.getOrientation(nbttagcompound.func_74762_e("orientation"));
/*    */   }
/*    */   
/*    */ 
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 43 */     nbttagcompound.func_74768_a("orientation", this.facing.ordinal());
/*    */   }
/*    */   
/*    */   public void removeLights() {
/* 47 */     for (int x = -15; x <= 15; x++) {
/* 48 */       for (int y = -15; y <= 15; y++) {
/* 49 */         for (int z = -15; z <= 15; z++) {
/* 50 */           if ((this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == ConfigBlocks.blockAiry) && (this.field_145850_b.func_72805_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == 3))
/*    */           {
/* 52 */             this.field_145850_b.func_147468_f(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileArcaneLamp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */