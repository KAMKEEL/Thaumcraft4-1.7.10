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
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class PotionThaumarhia extends Potion
/*    */ {
/* 17 */   public static PotionThaumarhia instance = null;
/* 18 */   private int statusIconIndex = -1;
/*    */   
/*    */   public PotionThaumarhia(int par1, boolean par2, int par3)
/*    */   {
/* 22 */     super(par1, par2, par3);
/* 23 */     func_76399_b(0, 0);
/*    */   }
/*    */   
/*    */   public static void init()
/*    */   {
/* 28 */     instance.func_76390_b("potion.thaumarhia");
/* 29 */     instance.func_76399_b(7, 2);
/* 30 */     instance.func_76404_a(0.25D);
/*    */   }
/*    */   
/*    */   public boolean func_76398_f()
/*    */   {
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76392_e()
/*    */   {
/* 41 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
/* 42 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 45 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int par2)
/*    */   {
/* 49 */     if ((!target.field_70170_p.field_72995_K) && (target.field_70170_p.field_73012_v.nextInt(15) == 0))
/*    */     {
/* 51 */       int x = MathHelper.func_76128_c(target.field_70165_t);
/* 52 */       int y = MathHelper.func_76128_c(target.field_70163_u);
/* 53 */       int z = MathHelper.func_76128_c(target.field_70161_v);
/* 54 */       if (target.field_70170_p.func_147437_c(x, y, z)) {
/* 55 */         target.field_70170_p.func_147449_b(x, y, z, ConfigBlocks.blockFluxGoo);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_76397_a(int par1, int par2)
/*    */   {
/* 63 */     return par1 % 20 == 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/potions/PotionThaumarhia.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */