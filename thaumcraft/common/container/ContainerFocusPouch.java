/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.items.wands.ItemFocusPouch;
/*     */ 
/*     */ public class ContainerFocusPouch extends Container
/*     */ {
/*     */   private World worldObj;
/*     */   private int posX;
/*     */   private int posY;
/*     */   private int posZ;
/*     */   private int blockSlot;
/*  20 */   public IInventory input = new InventoryFocusPouch(this);
/*  21 */   ItemStack pouch = null;
/*  22 */   EntityPlayer player = null;
/*     */   
/*     */ 
/*     */   public ContainerFocusPouch(InventoryPlayer iinventory, World par2World, int par3, int par4, int par5)
/*     */   {
/*  27 */     this.worldObj = par2World;
/*  28 */     this.posX = par3;
/*  29 */     this.posY = par4;
/*  30 */     this.posZ = par5;
/*  31 */     this.player = iinventory.field_70458_d;
/*  32 */     this.pouch = iinventory.func_70448_g();
/*  33 */     this.blockSlot = (iinventory.field_70461_c + 45);
/*     */     
/*  35 */     for (int a = 0; a < 18; a++) {
/*  36 */       func_75146_a(new SlotLimitedByClass(thaumcraft.api.wands.ItemFocusBasic.class, this.input, a, 37 + a % 6 * 18, 51 + a / 6 * 18));
/*     */     }
/*  38 */     bindPlayerInventory(iinventory);
/*     */     
/*  40 */     if (!par2World.field_72995_K) {
/*     */       try {
/*  42 */         ((InventoryFocusPouch)this.input).stackList = ((ItemFocusPouch)this.pouch.func_77973_b()).getInventory(this.pouch);
/*     */       }
/*     */       catch (Exception e) {}
/*     */     }
/*  46 */     func_75130_a(this.input);
/*     */   }
/*     */   
/*     */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/*  50 */     for (int i = 0; i < 3; i++) {
/*  51 */       for (int j = 0; j < 9; j++) {
/*  52 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 151 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  57 */     for (int i = 0; i < 9; i++) {
/*  58 */       func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 209));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*     */   {
/*  86 */     if (slot == this.blockSlot) return null;
/*  87 */     ItemStack stack = null;
/*  88 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */     
/*     */ 
/*  91 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/*  92 */       ItemStack stackInSlot = slotObject.func_75211_c();
/*  93 */       stack = stackInSlot.func_77946_l();
/*     */       
/*  95 */       if (slot < 18) {
/*  96 */         if ((!this.input.func_94041_b(slot, stackInSlot)) || (!func_75135_a(stackInSlot, 18, this.field_75151_b.size(), true)))
/*     */         {
/*     */ 
/*  99 */           return null;
/*     */         }
/*     */       }
/* 102 */       else if ((!this.input.func_94041_b(slot, stackInSlot)) || (!func_75135_a(stackInSlot, 0, 18, false)))
/*     */       {
/* 104 */         return null;
/*     */       }
/*     */       
/* 107 */       if (stackInSlot.field_77994_a == 0) {
/* 108 */         slotObject.func_75215_d(null);
/*     */       } else {
/* 110 */         slotObject.func_75218_e();
/*     */       }
/*     */     }
/*     */     
/* 114 */     return stack;
/*     */   }
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer var1)
/*     */   {
/* 119 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
/*     */   {
/* 125 */     if (par1 == this.blockSlot) return null;
/* 126 */     return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 132 */     super.func_75134_a(par1EntityPlayer);
/* 133 */     if (!this.worldObj.field_72995_K)
/*     */     {
/* 135 */       ((ItemFocusPouch)this.pouch.func_77973_b()).setInventory(this.pouch, ((InventoryFocusPouch)this.input).stackList);
/*     */       
/* 137 */       if (this.player == null) return;
/* 138 */       if ((this.player.func_70694_bm() != null) && (this.player.func_70694_bm().func_77969_a(this.pouch)))
/* 139 */         this.player.func_70062_b(0, this.pouch);
/* 140 */       this.player.field_71071_by.func_70296_d();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerFocusPouch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */