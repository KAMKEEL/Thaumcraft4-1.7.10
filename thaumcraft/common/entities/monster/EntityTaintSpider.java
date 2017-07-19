/*    */ package thaumcraft.common.entities.monster;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.monster.EntitySpider;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.entities.ITaintedMob;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ public class EntityTaintSpider extends EntitySpider implements ITaintedMob
/*    */ {
/*    */   public EntityTaintSpider(World par1World)
/*    */   {
/* 18 */     super(par1World);
/* 19 */     func_70105_a(0.4F, 0.3F);
/* 20 */     this.field_70728_aV = 2;
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_110147_ax()
/*    */   {
/* 26 */     super.func_110147_ax();
/* 27 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5.0D);
/* 28 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
/*    */   }
/*    */   
/*    */   protected float func_70647_i()
/*    */   {
/* 33 */     return 0.7F;
/*    */   }
/*    */   
/*    */ 
/*    */   protected net.minecraft.entity.Entity func_70782_k()
/*    */   {
/* 39 */     double d0 = 12.0D;
/* 40 */     return this.field_70170_p.func_72856_b(this, d0);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public float spiderScaleAmount()
/*    */   {
/* 50 */     return 0.4F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public float func_70053_R()
/*    */   {
/* 57 */     return 0.1F;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected Item func_146068_u()
/*    */   {
/* 64 */     return ConfigItems.itemResource;
/*    */   }
/*    */   
/*    */   protected void func_70628_a(boolean flag, int i)
/*    */   {
/* 69 */     if (this.field_70170_p.field_73012_v.nextInt(6) == 0) {
/* 70 */       if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 71 */         func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0F);
/*    */       } else {
/* 73 */         func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0F);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityTaintSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */