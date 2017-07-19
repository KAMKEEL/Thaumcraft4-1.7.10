/*    */ package thaumcraft.codechicken.lib.raytracer;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ public class ExtendedMOP
/*    */   extends MovingObjectPosition
/*    */   implements Comparable<ExtendedMOP>
/*    */ {
/*    */   public Object data;
/*    */   public double dist;
/*    */   
/*    */   public ExtendedMOP(Entity entity, Object data)
/*    */   {
/* 17 */     super(entity);
/* 18 */     setData(data);
/*    */   }
/*    */   
/*    */   public ExtendedMOP(int x, int y, int z, int side, Vec3 hit, Object data)
/*    */   {
/* 23 */     super(x, y, z, side, hit);
/* 24 */     setData(data);
/*    */   }
/*    */   
/*    */   public ExtendedMOP(MovingObjectPosition mop, Object data, double dist)
/*    */   {
/* 29 */     super(0, 0, 0, 0, mop.field_72307_f);
/* 30 */     this.field_72313_a = mop.field_72313_a;
/* 31 */     this.field_72311_b = mop.field_72311_b;
/* 32 */     this.field_72312_c = mop.field_72312_c;
/* 33 */     this.field_72309_d = mop.field_72309_d;
/* 34 */     this.field_72310_e = mop.field_72310_e;
/* 35 */     this.subHit = mop.subHit;
/* 36 */     setData(data);
/* 37 */     this.dist = dist;
/*    */   }
/*    */   
/*    */   public void setData(Object data)
/*    */   {
/* 42 */     if ((data instanceof Integer))
/* 43 */       this.subHit = ((Integer)data).intValue();
/* 44 */     this.data = data;
/*    */   }
/*    */   
/*    */ 
/*    */   public static <T> T getData(MovingObjectPosition mop)
/*    */   {
/* 50 */     if ((mop instanceof ExtendedMOP)) {
/* 51 */       return (T)((ExtendedMOP)mop).data;
/*    */     }
/* 53 */     return Integer.valueOf(mop.subHit);
/*    */   }
/*    */   
/*    */ 
/*    */   public int compareTo(ExtendedMOP o)
/*    */   {
/* 59 */     return this.dist < o.dist ? -1 : this.dist == o.dist ? 0 : 1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/raytracer/ExtendedMOP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */