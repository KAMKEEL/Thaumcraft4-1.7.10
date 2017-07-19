/*    */ package thaumcraft.api.potions;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.damagesource.DamageSourceThaumcraft;
/*    */ import thaumcraft.api.entities.ITaintedMob;
/*    */ 
/*    */ public class PotionFluxTaint extends Potion
/*    */ {
/* 15 */   public static PotionFluxTaint instance = null;
/* 16 */   private int statusIconIndex = -1;
/*    */   
/*    */   public PotionFluxTaint(int par1, boolean par2, int par3)
/*    */   {
/* 20 */     super(par1, par2, par3);
/* 21 */     func_76399_b(0, 0);
/*    */   }
/*    */   
/*    */   public static void init()
/*    */   {
/* 26 */     instance.func_76390_b("potion.fluxtaint");
/* 27 */     instance.func_76399_b(3, 1);
/* 28 */     instance.func_76404_a(0.25D);
/*    */   }
/*    */   
/*    */   public boolean func_76398_f()
/*    */   {
/* 33 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76392_e()
/*    */   {
/* 39 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
/* 40 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 43 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int par2)
/*    */   {
/* 47 */     if ((target instanceof ITaintedMob)) {
/* 48 */       target.func_70691_i(1.0F);
/*    */     }
/* 50 */     else if ((!target.func_70662_br()) && (!(target instanceof EntityPlayer)))
/*    */     {
/* 52 */       target.func_70097_a(DamageSourceThaumcraft.taint, 1.0F);
/*    */ 
/*    */     }
/* 55 */     else if ((!target.func_70662_br()) && ((target.func_110138_aP() > 1.0F) || ((target instanceof EntityPlayer))))
/*    */     {
/* 57 */       target.func_70097_a(DamageSourceThaumcraft.taint, 1.0F);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean func_76397_a(int par1, int par2)
/*    */   {
/* 63 */     int k = 40 >> par2;
/* 64 */     return par1 % k == 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/potions/PotionFluxTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */