/*    */ package thaumcraft.common.items.wands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.wands.IWandRodOnUpdate;
/*    */ 
/*    */ public class WandRodPrimalOnUpdate implements IWandRodOnUpdate
/*    */ {
/*    */   Aspect aspect;
/*    */   ArrayList<Aspect> primals;
/*    */   
/*    */   public WandRodPrimalOnUpdate(Aspect aspect)
/*    */   {
/* 16 */     this.aspect = aspect;
/*    */   }
/*    */   
/*    */   public WandRodPrimalOnUpdate() {
/* 20 */     this.aspect = null;
/* 21 */     this.primals = Aspect.getPrimalAspects();
/*    */   }
/*    */   
/*    */   public void onUpdate(ItemStack itemstack, EntityPlayer player)
/*    */   {
/* 26 */     if (this.aspect != null) {
/* 27 */       if ((player.field_70173_aa % 200 == 0) && 
/* 28 */         (((ItemWandCasting)itemstack.func_77973_b()).getVis(itemstack, this.aspect) < ((ItemWandCasting)itemstack.func_77973_b()).getMaxVis(itemstack) / 10)) {
/* 29 */         ((ItemWandCasting)itemstack.func_77973_b()).addVis(itemstack, this.aspect, 1, true);
/*    */       }
/*    */       
/*    */     }
/* 33 */     else if (player.field_70173_aa % 50 == 0) {
/* 34 */       ArrayList<Aspect> q = new ArrayList();
/* 35 */       for (Aspect as : this.primals) {
/* 36 */         if (((ItemWandCasting)itemstack.func_77973_b()).getVis(itemstack, as) < ((ItemWandCasting)itemstack.func_77973_b()).getMaxVis(itemstack) / 10) {
/* 37 */           q.add(as);
/*    */         }
/*    */       }
/* 40 */       if (q.size() > 0) {
/* 41 */         ((ItemWandCasting)itemstack.func_77973_b()).addVis(itemstack, (Aspect)q.get(player.field_70170_p.field_73012_v.nextInt(q.size())), 1, true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/WandRodPrimalOnUpdate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */