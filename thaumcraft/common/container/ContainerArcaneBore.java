/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemPickaxe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
/*    */ import thaumcraft.common.tiles.TileArcaneBore;
/*    */ 
/*    */ public class ContainerArcaneBore extends Container
/*    */ {
/*    */   private TileArcaneBore tileEntity;
/*    */   
/*    */   public ContainerArcaneBore(InventoryPlayer iinventory, TileArcaneBore e)
/*    */   {
/* 19 */     this.tileEntity = e;
/* 20 */     func_75146_a(new SlotLimitedByClass(ItemFocusExcavation.class, e, 0, 26, 18));
/* 21 */     func_75146_a(new SlotLimitedByClass(ItemPickaxe.class, e, 1, 74, 18));
/* 22 */     bindPlayerInventory(iinventory);
/*    */   }
/*    */   
/*    */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/* 26 */     for (int i = 0; i < 3; i++) {
/* 27 */       for (int j = 0; j < 9; j++) {
/* 28 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 59 + i * 18));
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 33 */     for (int i = 0; i < 9; i++) {
/* 34 */       func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 117));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*    */   {
/* 41 */     return this.tileEntity.func_145831_w().func_147438_o(this.tileEntity.field_145851_c, this.tileEntity.field_145848_d, this.tileEntity.field_145849_e) == this.tileEntity;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*    */   {
/* 49 */     ItemStack stack = null;
/* 50 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*    */     
/*    */ 
/* 53 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/* 54 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 55 */       stack = stackInSlot.func_77946_l();
/*    */       
/*    */ 
/* 58 */       if (slot <= 1) {
/* 59 */         if (!func_75135_a(stackInSlot, 2, this.field_75151_b.size(), true)) {
/* 60 */           return null;
/*    */         }
/*    */       }
/* 63 */       else if (slot > 1) {
/* 64 */         if ((stackInSlot.func_77973_b() instanceof ItemFocusExcavation)) {
/* 65 */           if (!func_75135_a(stackInSlot, 0, 1, false)) {
/* 66 */             return null;
/*    */           }
/*    */           
/*    */         }
/* 70 */         else if (((stackInSlot.func_77973_b() instanceof ItemPickaxe)) && 
/* 71 */           (!func_75135_a(stackInSlot, 1, 2, false))) {
/* 72 */           return null;
/*    */ 
/*    */ 
/*    */         }
/*    */         
/*    */ 
/*    */ 
/*    */ 
/*    */       }
/* 81 */       else if (!func_75135_a(stackInSlot, 2, 38, false))
/*    */       {
/* 83 */         return null;
/*    */       }
/*    */       
/* 86 */       if (stackInSlot.field_77994_a == 0) {
/* 87 */         slotObject.func_75215_d(null);
/*    */       } else {
/* 89 */         slotObject.func_75218_e();
/*    */       }
/*    */       
/* 92 */       if (stackInSlot.field_77994_a == stack.field_77994_a)
/*    */       {
/* 94 */         return null;
/*    */       }
/*    */     }
/*    */     
/* 98 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerArcaneBore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */