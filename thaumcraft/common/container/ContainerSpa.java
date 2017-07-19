/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.items.ItemBathSalts;
/*    */ import thaumcraft.common.tiles.TileSpa;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContainerSpa
/*    */   extends Container
/*    */ {
/*    */   private TileSpa spa;
/*    */   private int lastBreakTime;
/*    */   
/*    */   public ContainerSpa(InventoryPlayer par1InventoryPlayer, TileSpa tileEntity)
/*    */   {
/* 29 */     this.spa = tileEntity;
/* 30 */     func_75146_a(new SlotLimitedByClass(ItemBathSalts.class, tileEntity, 0, 65, 31));
/*    */     
/*    */ 
/* 33 */     for (int i = 0; i < 3; i++)
/*    */     {
/* 35 */       for (int j = 0; j < 9; j++)
/*    */       {
/* 37 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*    */       }
/*    */     }
/*    */     
/* 41 */     for (i = 0; i < 9; i++)
/*    */     {
/* 43 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75140_a(EntityPlayer p, int button)
/*    */   {
/* 52 */     if (button == 1) {
/* 53 */       this.spa.toggleMix();
/*    */     }
/* 55 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*    */   {
/* 62 */     return this.spa.func_70300_a(par1EntityPlayer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*    */   {
/* 70 */     ItemStack stack = null;
/* 71 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*    */     
/*    */ 
/* 74 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/* 75 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 76 */       stack = stackInSlot.func_77946_l();
/*    */       
/* 78 */       if (slot == 0) {
/* 79 */         if ((!this.spa.func_94041_b(slot, stackInSlot)) || (!func_75135_a(stackInSlot, 1, this.field_75151_b.size(), true)))
/*    */         {
/*    */ 
/* 82 */           return null;
/*    */         }
/*    */         
/*    */       }
/* 86 */       else if ((!this.spa.func_94041_b(slot, stackInSlot)) || (!func_75135_a(stackInSlot, 0, 1, false)))
/*    */       {
/* 88 */         return null;
/*    */       }
/*    */       
/* 91 */       if (stackInSlot.field_77994_a == 0) {
/* 92 */         slotObject.func_75215_d(null);
/*    */       } else {
/* 94 */         slotObject.func_75218_e();
/*    */       }
/*    */     }
/*    */     
/* 98 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerSpa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */