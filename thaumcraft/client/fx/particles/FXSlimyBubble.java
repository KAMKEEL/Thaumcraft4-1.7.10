/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.particle.EntityFX;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FXSlimyBubble
/*    */   extends EntityFX
/*    */ {
/*    */   public FXSlimyBubble(World world, double d, double d1, double d2, float f)
/*    */   {
/* 17 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*    */     
/* 19 */     this.field_70552_h = 1.0F;
/* 20 */     this.field_70553_i = 1.0F;
/* 21 */     this.field_70551_j = 1.0F;
/* 22 */     this.field_70545_g = 0.0F;
/* 23 */     this.field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
/* 24 */     this.field_70544_f = f;
/* 25 */     this.field_70547_e = (15 + world.field_73012_v.nextInt(5));
/* 26 */     this.field_70145_X = false;
/* 27 */     func_70105_a(0.01F, 0.01F);
/*    */   }
/*    */   
/* 30 */   int particle = 144;
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 36 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as);
/*    */     
/* 38 */     float var8 = this.particle % 16 / 16.0F;
/* 39 */     float var9 = var8 + 0.0625F;
/* 40 */     float var10 = this.particle / 16 / 16.0F;
/* 41 */     float var11 = var10 + 0.0625F;
/* 42 */     float var12 = this.field_70544_f;
/* 43 */     float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * f - field_70556_an);
/* 44 */     float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * f - field_70554_ao);
/* 45 */     float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * f - field_70555_ap);
/*    */     
/* 47 */     tessellator.func_78380_c(func_70070_b(f));
/*    */     
/* 49 */     tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
/* 50 */     tessellator.func_78374_a(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
/*    */     
/*    */ 
/*    */ 
/* 54 */     tessellator.func_78374_a(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
/*    */     
/*    */ 
/*    */ 
/* 58 */     tessellator.func_78374_a(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
/*    */     
/*    */ 
/*    */ 
/* 62 */     tessellator.func_78374_a(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int func_70537_b()
/*    */   {
/* 71 */     return 1;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 77 */     this.field_70169_q = this.field_70165_t;
/* 78 */     this.field_70167_r = this.field_70163_u;
/* 79 */     this.field_70166_s = this.field_70161_v;
/*    */     
/* 81 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 82 */       func_70106_y();
/*    */     }
/* 84 */     if (this.field_70546_d - 1 < 6) {
/* 85 */       this.particle = (144 + this.field_70546_d / 2);
/* 86 */       if (this.field_70546_d == 5) {
/* 87 */         this.field_70163_u += 0.1D;
/*    */       }
/*    */     }
/* 90 */     else if (this.field_70546_d < this.field_70547_e - 4) {
/* 91 */       this.field_70181_x += 0.005D;
/* 92 */       this.particle = (147 + this.field_70546_d % 4 / 2);
/*    */     } else {
/* 94 */       this.field_70181_x /= 2.0D;
/* 95 */       this.particle = (150 - (this.field_70547_e - this.field_70546_d) / 2);
/*    */     }
/*    */     
/* 98 */     this.field_70163_u += this.field_70181_x;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXSlimyBubble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */