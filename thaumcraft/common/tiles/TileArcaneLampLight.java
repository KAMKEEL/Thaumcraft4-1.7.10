/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class TileArcaneLampLight extends TileEntity
/*    */ {
/* 10 */   int x = Integer.MAX_VALUE;
/* 11 */   int y = Integer.MAX_VALUE;
/* 12 */   int z = Integer.MAX_VALUE;
/*    */   
/* 14 */   int count = 0;
/*    */   
/*    */   public boolean canUpdate()
/*    */   {
/* 18 */     return true;
/*    */   }
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 23 */     if (!this.field_145850_b.field_72995_K) {
/* 24 */       if (this.count == 0) this.count = this.field_145850_b.field_73012_v.nextInt(100);
/* 25 */       if ((++this.count % 100 == 0) && 
/* 26 */         (!(this.field_145850_b.func_147438_o(this.x, this.y, this.z) instanceof TileArcaneLamp))) {
/* 27 */         this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*    */   {
/* 36 */     super.func_145839_a(nbttagcompound);
/* 37 */     this.x = nbttagcompound.func_74762_e("sourceX");
/* 38 */     this.y = nbttagcompound.func_74762_e("sourceY");
/* 39 */     this.z = nbttagcompound.func_74762_e("sourceZ");
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*    */   {
/* 45 */     super.func_145841_b(nbttagcompound);
/* 46 */     nbttagcompound.func_74768_a("sourceX", this.x);
/* 47 */     nbttagcompound.func_74768_a("sourceY", this.y);
/* 48 */     nbttagcompound.func_74768_a("sourceZ", this.z);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileArcaneLampLight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */