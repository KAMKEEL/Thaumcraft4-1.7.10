/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileJarFillableVoid
/*    */   extends TileJarFillable
/*    */ {
/*    */   public int addToContainer(Aspect tt, int am)
/*    */   {
/* 14 */     boolean up = this.amount < this.maxAmount;
/* 15 */     if (am == 0) return am;
/* 16 */     if ((tt == this.aspect) || (this.amount == 0)) {
/* 17 */       this.aspect = tt;
/* 18 */       this.amount += am;
/* 19 */       am = 0;
/* 20 */       if (this.amount > this.maxAmount) this.amount = this.maxAmount;
/*    */     }
/* 22 */     if (up) {
/* 23 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 24 */       func_70296_d();
/*    */     }
/* 26 */     return am;
/*    */   }
/*    */   
/*    */   public int getMinimumSuction()
/*    */   {
/* 31 */     return this.aspectFilter != null ? 48 : 32;
/*    */   }
/*    */   
/*    */   public int getSuctionAmount(ForgeDirection loc)
/*    */   {
/* 36 */     if ((this.aspectFilter != null) && (this.amount < this.maxAmount)) {
/* 37 */       return 48;
/*    */     }
/* 39 */     return 32;
/*    */   }
/*    */   
/*    */ 
/* 43 */   int count = 0;
/*    */   
/*    */ 
/*    */   public void func_145845_h()
/*    */   {
/* 48 */     if ((!this.field_145850_b.field_72995_K) && (++this.count % 5 == 0)) {
/* 49 */       fillJar();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileJarFillableVoid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */