/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.particle.EntityFX;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class FXBreaking extends EntityFX
/*    */ {
/*    */   public void setParticleMaxAge(int particleMaxAge)
/*    */   {
/* 17 */     this.field_70547_e = particleMaxAge;
/*    */   }
/*    */   
/*    */   public FXBreaking(World par1World, double par2, double par4, double par6, Item par8Item)
/*    */   {
/* 22 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/* 23 */     func_110125_a(par8Item.func_77617_a(0));
/* 24 */     this.field_70552_h = (this.field_70553_i = this.field_70551_j = 1.0F);
/* 25 */     this.field_70545_g = net.minecraft.init.Blocks.field_150431_aC.field_149763_I;
/* 26 */     this.field_70544_f /= 2.0F;
/*    */   }
/*    */   
/*    */   public FXBreaking(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, Item par14Item)
/*    */   {
/* 31 */     this(par1World, par2, par4, par6, par14Item);
/* 32 */     this.field_70159_w *= 0.10000000149011612D;
/* 33 */     this.field_70181_x *= 0.10000000149011612D;
/* 34 */     this.field_70179_y *= 0.10000000149011612D;
/* 35 */     this.field_70159_w += par8;
/* 36 */     this.field_70181_x += par10;
/* 37 */     this.field_70179_y += par12;
/*    */   }
/*    */   
/*    */   public int func_70537_b()
/*    */   {
/* 42 */     return 2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
/*    */   {
/* 49 */     float f6 = (this.field_94054_b + this.field_70548_b / 4.0F) / 16.0F;
/* 50 */     float f7 = f6 + 0.015609375F;
/* 51 */     float f8 = (this.field_94055_c + this.field_70549_c / 4.0F) / 16.0F;
/* 52 */     float f9 = f8 + 0.015609375F;
/* 53 */     float f10 = 0.1F * this.field_70544_f;
/* 54 */     float fade = 1.0F - this.field_70546_d / this.field_70547_e;
/* 55 */     f10 *= fade;
/* 56 */     if (this.field_70550_a != null)
/*    */     {
/* 58 */       f6 = this.field_70550_a.func_94214_a(this.field_70548_b / 4.0F * 16.0F);
/* 59 */       f7 = this.field_70550_a.func_94214_a((this.field_70548_b + 1.0F) / 4.0F * 16.0F);
/* 60 */       f8 = this.field_70550_a.func_94207_b(this.field_70549_c / 4.0F * 16.0F);
/* 61 */       f9 = this.field_70550_a.func_94207_b((this.field_70549_c + 1.0F) / 4.0F * 16.0F);
/*    */     }
/*    */     
/* 64 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * par2 - field_70556_an);
/* 65 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * par2 - field_70554_ao);
/* 66 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * par2 - field_70555_ap);
/* 67 */     float f14 = 1.0F;
/*    */     
/* 69 */     par1Tessellator.func_78369_a(f14 * this.field_70552_h, f14 * this.field_70553_i, f14 * this.field_70551_j, this.field_82339_as * fade);
/* 70 */     par1Tessellator.func_78374_a(f11 - par3 * f10 - par6 * f10, f12 - par4 * f10, f13 - par5 * f10 - par7 * f10, f6, f9);
/* 71 */     par1Tessellator.func_78374_a(f11 - par3 * f10 + par6 * f10, f12 + par4 * f10, f13 - par5 * f10 + par7 * f10, f6, f8);
/* 72 */     par1Tessellator.func_78374_a(f11 + par3 * f10 + par6 * f10, f12 + par4 * f10, f13 + par5 * f10 + par7 * f10, f7, f8);
/* 73 */     par1Tessellator.func_78374_a(f11 + par3 * f10 - par6 * f10, f12 - par4 * f10, f13 + par5 * f10 - par7 * f10, f7, f9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/particles/FXBreaking.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */