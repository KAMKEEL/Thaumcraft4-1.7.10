/*    */ package thaumcraft.common.config;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ 
/*    */ public class MaterialTaint extends Material
/*    */ {
/*    */   private int mobilityFlag;
/*    */   
/*    */   public MaterialTaint(net.minecraft.block.material.MapColor par1MapColor) {
/* 10 */     super(par1MapColor);
/* 11 */     func_76219_n();
/* 12 */     func_76221_f();
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_76220_a()
/*    */   {
/* 18 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_76222_j()
/*    */   {
/* 24 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_76228_b()
/*    */   {
/* 33 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_76230_c()
/*    */   {
/* 42 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   protected Material func_76221_f()
/*    */   {
/* 48 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected Material func_76219_n()
/*    */   {
/* 55 */     this.mobilityFlag = 1;
/* 56 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_76227_m()
/*    */   {
/* 64 */     return this.mobilityFlag;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/config/MaterialTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */