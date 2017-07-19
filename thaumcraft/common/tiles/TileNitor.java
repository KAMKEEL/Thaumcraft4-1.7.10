/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileNitor
/*    */   extends TileEntity
/*    */ {
/*    */   public boolean canUpdate()
/*    */   {
/* 17 */     return true;
/*    */   }
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 22 */     super.func_145845_h();
/*    */     
/* 24 */     if (this.field_145850_b.field_72995_K)
/*    */     {
/* 26 */       if (this.field_145850_b.field_73012_v.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
/* 27 */         Thaumcraft.proxy.wispFX3(this.field_145850_b, this.field_145851_c + 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.5F, this.field_145851_c + 0.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F, this.field_145848_d + 0.5F, this.field_145849_e + 0.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F, 0.5F, 4, true, -0.025F);
/*    */       }
/*    */       
/*    */ 
/*    */ 
/* 32 */       if (this.field_145850_b.field_73012_v.nextInt(15 - Thaumcraft.proxy.particleCount(4)) == 0) {
/* 33 */         Thaumcraft.proxy.wispFX3(this.field_145850_b, this.field_145851_c + 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.5F, this.field_145851_c + 0.4F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, this.field_145848_d + 0.5F, this.field_145849_e + 0.4F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, 0.25F, 1, true, -0.02F);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileNitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */