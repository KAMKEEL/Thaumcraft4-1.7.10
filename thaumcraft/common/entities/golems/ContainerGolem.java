/*     */ package thaumcraft.common.entities.golems;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import thaumcraft.common.container.SlotGhost;
/*     */ import thaumcraft.common.entities.InventoryMob;
/*     */ 
/*     */ public class ContainerGolem extends thaumcraft.common.container.ContainerGhostSlots
/*     */ {
/*     */   public InventoryMob mobInv;
/*     */   public InventoryPlayer playerInv;
/*  16 */   public int currentScroll = 0;
/*  17 */   public int maxScroll = 0;
/*     */   
/*     */   public ContainerGolem(InventoryPlayer iinventory, InventoryMob iinventory1) {
/*  20 */     this.mobInv = iinventory1;
/*  21 */     this.playerInv = iinventory;
/*  22 */     ((EntityGolemBase)this.mobInv.ent).paused = true;
/*     */     
/*  24 */     if (ItemGolemCore.hasInventory(((EntityGolemBase)this.mobInv.ent).getCore())) bindGolemInventory();
/*  25 */     bindPlayerInventory();
/*     */   }
/*     */   
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/*  30 */     ((EntityGolemBase)this.mobInv.ent).paused = false;
/*     */   }
/*     */   
/*     */   protected void bindGolemInventory() {
/*  34 */     int slots = this.mobInv.slotCount;
/*  35 */     this.maxScroll = (slots / 6 - 1);
/*  36 */     for (int a = 0; a < Math.min(6, slots); a++) {
/*  37 */       if (((EntityGolemBase)this.mobInv.ent).getCore() == 0)
/*  38 */         func_75146_a(new SlotGhost(this.mobInv, a + this.currentScroll * 6, 100 + a / 2 * 28, 16 + a % 2 * 31));
/*  39 */       if (((EntityGolemBase)this.mobInv.ent).getCore() == 5) {
/*  40 */         func_75146_a(new thaumcraft.common.container.SlotGhostFluid(this.mobInv, a + this.currentScroll * 6, 100 + a / 2 * 28, 16 + a % 2 * 31));
/*     */       } else
/*  42 */         func_75146_a(new SlotGhost(this.mobInv, a + this.currentScroll * 6, 100 + a / 2 * 28, 16 + a % 2 * 31, 1));
/*     */     }
/*     */   }
/*     */   
/*     */   public void refreshInventory() {
/*  47 */     this.field_75153_a.clear();
/*  48 */     this.field_75151_b.clear();
/*  49 */     if (ItemGolemCore.hasInventory(((EntityGolemBase)this.mobInv.ent).getCore())) bindGolemInventory();
/*  50 */     bindPlayerInventory();
/*     */   }
/*     */   
/*     */   protected void bindPlayerInventory() {
/*  54 */     for (int i = 0; i < 3; i++) {
/*  55 */       for (int j = 0; j < 9; j++) {
/*  56 */         func_75146_a(new Slot(this.playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  61 */     for (int i = 0; i < 9; i++) {
/*  62 */       func_75146_a(new Slot(this.playerInv, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75140_a(EntityPlayer par1EntityPlayer, int button)
/*     */   {
/*  71 */     if ((button == 66) && (this.currentScroll > 0)) {
/*  72 */       this.currentScroll -= 1;
/*  73 */       refreshInventory();
/*     */     }
/*  75 */     if ((button == 67) && (this.currentScroll < this.maxScroll)) {
/*  76 */       this.currentScroll += 1;
/*  77 */       refreshInventory();
/*     */     }
/*     */     
/*     */ 
/*  81 */     if ((button >= 50) && (button <= 57)) {
/*  82 */       ((EntityGolemBase)this.mobInv.ent).setToggle(button - 50, ((EntityGolemBase)(EntityGolemBase)this.mobInv.ent).getToggles()[(button - 50)] == 0);
/*     */     }
/*     */     
/*     */ 
/*  86 */     int slots = this.mobInv.slotCount;
/*  87 */     if ((button >= 0) && (button < slots)) {
/*  88 */       int c = ((EntityGolemBase)this.mobInv.ent).getColors(button) - 1;
/*  89 */       if (c < -1) c = 15;
/*  90 */       ((EntityGolemBase)this.mobInv.ent).setColors(button, c);
/*     */     }
/*     */     
/*  93 */     if ((button >= slots) && (button < slots * 2)) {
/*  94 */       int c = ((EntityGolemBase)this.mobInv.ent).getColors(button - slots) + 1;
/*  95 */       if (c > 15) c = -1;
/*  96 */       ((EntityGolemBase)this.mobInv.ent).setColors(button - slots, c);
/*     */     }
/*     */     
/*     */ 
/* 100 */     this.mobInv.ent.field_70170_p.func_72908_a(this.mobInv.ent.field_70165_t, this.mobInv.ent.field_70163_u, this.mobInv.ent.field_70161_v, "random.click", 0.2F, 0.8F);
/* 101 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*     */   {
/* 108 */     ItemStack stack = null;
/* 109 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/* 110 */     int slots = this.mobInv.slotCount;
/*     */     
/*     */ 
/* 113 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/* 114 */       ItemStack stackInSlot = slotObject.func_75211_c();
/* 115 */       stack = stackInSlot.func_77946_l();
/*     */       
/*     */ 
/* 118 */       if (slot < slots) {
/* 119 */         if (!func_75135_a(stackInSlot, slots, this.field_75151_b.size(), true))
/*     */         {
/* 121 */           return null;
/*     */         }
/*     */       }
/* 124 */       else if (!func_75135_a(stackInSlot, 0, slots, false)) {
/* 125 */         return null;
/*     */       }
/*     */       
/* 128 */       if (stackInSlot.field_77994_a == 0) {
/* 129 */         slotObject.func_75215_d(null);
/*     */       } else {
/* 131 */         slotObject.func_75218_e();
/*     */       }
/*     */     }
/*     */     
/* 135 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer var1)
/*     */   {
/* 141 */     return this.mobInv.canInteractWith(var1);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/ContainerGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */