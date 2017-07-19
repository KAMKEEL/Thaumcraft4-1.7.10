/*    */ package thaumcraft.common.lib.potions;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.config.Config;
/*    */ 
/*    */ public class PotionInfectiousVisExhaust extends Potion
/*    */ {
/* 15 */   public static PotionInfectiousVisExhaust instance = null;
/*    */   
/*    */ 
/* 18 */   private int statusIconIndex = -1;
/*    */   
/*    */   public PotionInfectiousVisExhaust(int par1, boolean par2, int par3) {
/* 21 */     super(par1, par2, par3);
/* 22 */     func_76399_b(0, 0);
/*    */   }
/*    */   
/*    */   public static void init() {
/* 26 */     instance.func_76390_b("potion.infvisexhaust");
/* 27 */     instance.func_76399_b(6, 1);
/* 28 */     instance.func_76404_a(0.25D);
/*    */   }
/*    */   
/*    */   public boolean func_76398_f()
/*    */   {
/* 33 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public int func_76392_e()
/*    */   {
/* 39 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
/* 40 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 43 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_76394_a(EntityLivingBase target, int par2)
/*    */   {
/* 49 */     List<EntityLivingBase> targets = target.field_70170_p.func_72872_a(EntityLivingBase.class, target.field_70121_D.func_72314_b(4.0D, 4.0D, 4.0D));
/*    */     
/* 51 */     if (targets.size() > 0) {
/* 52 */       for (EntityLivingBase e : targets) {
/* 53 */         if (!e.func_82165_m(Config.potionInfVisExhaustID)) {
/* 54 */           if (par2 > 0) {
/* 55 */             e.func_70690_d(new PotionEffect(Config.potionInfVisExhaustID, 6000, par2 - 1, false));
/*    */           }
/*    */           else
/*    */           {
/* 59 */             e.func_70690_d(new PotionEffect(Config.potionVisExhaustID, 6000, 0, false));
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean func_76397_a(int par1, int par2) {
/* 67 */     return par1 % 40 == 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/potions/PotionInfectiousVisExhaust.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */