/*    */ package thaumcraft.common.lib.potions;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PotionSunScorned extends Potion
/*    */ {
/* 16 */   public static PotionSunScorned instance = null;
/* 17 */   private int statusIconIndex = -1;
/*    */   
/*    */   public PotionSunScorned(int par1, boolean par2, int par3)
/*    */   {
/* 21 */     super(par1, par2, par3);
/* 22 */     func_76399_b(0, 0);
/*    */   }
/*    */   
/*    */   public static void init()
/*    */   {
/* 27 */     instance.func_76390_b("potion.sunscorned");
/* 28 */     instance.func_76399_b(6, 2);
/* 29 */     instance.func_76404_a(0.25D);
/*    */   }
/*    */   
/*    */   public boolean func_76398_f()
/*    */   {
/* 34 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76392_e()
/*    */   {
/* 40 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
/* 41 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 44 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int par2)
/*    */   {
/* 48 */     if (!target.field_70170_p.field_72995_K)
/*    */     {
/* 50 */       float f = target.func_70013_c(1.0F);
/* 51 */       if ((f > 0.5F) && (target.field_70170_p.field_73012_v.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) && (target.field_70170_p.func_72937_j(MathHelper.func_76128_c(target.field_70165_t), MathHelper.func_76128_c(target.field_70163_u), MathHelper.func_76128_c(target.field_70161_v))))
/*    */       {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 57 */         target.func_70015_d(4);
/*    */       }
/* 59 */       else if ((f < 0.25F) && (target.field_70170_p.field_73012_v.nextFloat() > f * 2.0F))
/*    */       {
/* 61 */         target.func_70691_i(1.0F);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_76397_a(int par1, int par2)
/*    */   {
/* 69 */     return par1 % 40 == 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/potions/PotionSunScorned.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */