/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.TileThaumcraft;
/*    */ import thaumcraft.api.entities.IEldritchMob;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.utils.EntityUtils;
/*    */ 
/*    */ public class TileEldritchObelisk extends TileThaumcraft
/*    */ {
/*    */   public boolean canUpdate()
/*    */   {
/* 22 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 28 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 5, this.field_145849_e + 1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public double func_145833_n()
/*    */   {
/* 35 */     return 9216.0D;
/*    */   }
/*    */   
/*    */ 
/* 39 */   private int counter = 0;
/*    */   
/*    */ 
/*    */   public void func_145845_h()
/*    */   {
/* 44 */     if ((!this.field_145850_b.field_72995_K) && (this.counter % 20 == 0))
/*    */     {
/* 46 */       ArrayList<Entity> list = EntityUtils.getEntitiesInRange(func_145831_w(), this.field_145851_c + 0.5D, this.field_145848_d, this.field_145849_e + 0.5D, null, EntityLivingBase.class, 6.0D);
/* 47 */       if ((list != null) && (list.size() > 0)) {
/* 48 */         for (Entity e : list) {
/* 49 */           if (((e instanceof IEldritchMob)) && ((e instanceof EntityLivingBase)) && (!((EntityLivingBase)e).func_82165_m(Potion.field_76428_l.field_76415_H)))
/*    */             try
/*    */             {
/* 52 */               ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76420_g.func_76396_c(), 40, 0, true));
/* 53 */               ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76428_l.func_76396_c(), 40, 0, true));
/*    */             } catch (Exception e1) {}
/*    */         }
/*    */       }
/*    */     }
/* 58 */     if (this.field_145850_b.field_72995_K)
/*    */     {
/* 60 */       ArrayList<Entity> list = EntityUtils.getEntitiesInRange(func_145831_w(), this.field_145851_c + 0.5D, this.field_145848_d, this.field_145849_e + 0.5D, null, EntityLivingBase.class, 6.0D);
/* 61 */       if ((list != null) && (list.size() > 0)) {
/* 62 */         for (Entity e : list) {
/* 63 */           if (((e instanceof IEldritchMob)) && ((e instanceof EntityLivingBase))) {
/* 64 */             Thaumcraft.proxy.wispFX4(func_145831_w(), this.field_145851_c + 0.5D, this.field_145848_d + 1 + this.field_145850_b.field_73012_v.nextFloat() * 3.0F, this.field_145849_e + 0.5D, e, 5, true, 1.0F);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileEldritchObelisk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */