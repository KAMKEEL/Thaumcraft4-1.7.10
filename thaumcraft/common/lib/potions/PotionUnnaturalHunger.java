/*    */ package thaumcraft.common.lib.potions;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class PotionUnnaturalHunger extends Potion
/*    */ {
/* 14 */   public static PotionUnnaturalHunger instance = null;
/* 15 */   private int statusIconIndex = -1;
/*    */   
/*    */   public PotionUnnaturalHunger(int par1, boolean par2, int par3)
/*    */   {
/* 19 */     super(par1, par2, par3);
/* 20 */     func_76399_b(0, 0);
/*    */   }
/*    */   
/*    */   public static void init()
/*    */   {
/* 25 */     instance.func_76390_b("potion.unhunger");
/* 26 */     instance.func_76399_b(7, 1);
/* 27 */     instance.func_76404_a(0.25D);
/*    */   }
/*    */   
/*    */   public boolean func_76398_f()
/*    */   {
/* 32 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76392_e()
/*    */   {
/* 38 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
/* 39 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 42 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int par2)
/*    */   {
/* 46 */     if ((!target.field_70170_p.field_72995_K) && ((target instanceof EntityPlayer)))
/*    */     {
/* 48 */       ((EntityPlayer)target).func_71020_j(0.025F * (par2 + 1));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_76397_a(int par1, int par2)
/*    */   {
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/potions/PotionUnnaturalHunger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */